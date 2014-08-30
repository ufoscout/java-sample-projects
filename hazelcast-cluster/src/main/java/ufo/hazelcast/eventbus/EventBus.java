package ufo.hazelcast.eventbus;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.hazelcast.core.MultiMap;

public class EventBus {

	private final String NODES_MAP = "cluster-nodes";
	private final String REQUEST_QUEUE_PREFIX = "request-";
	private final String RESPONSE_QUEUE_PREFIX = "response-";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String nodeId;
	private final HazelcastInstance hazelcastInstance;
	private final Map<String, EventHandler> eventHandlers = new HashMap<>();
	private final ConcurrentMap<String, EventResponse> responseHandlers = new ConcurrentHashMap<>();
	private final MultiMap<String, String> nodeEventsMap;

	public EventBus(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;
		nodeId = hazelcastInstance.getCluster().getLocalMember().getUuid();
		nodeEventsMap = this.hazelcastInstance.getMultiMap(NODES_MAP);
	}

	public <I,O> void register(final String eventName, final EventHandler<I,O> eventHandler) {

		eventHandlers.put(eventName, eventHandler);

		MessageListener<EventData<I>> requestMessageListener = new MessageListener<EventData<I>>() {
			@Override
			public void onMessage(Message<EventData<I>> message) {
				EventData<I> requestEventData = message.getMessageObject();
            	EventData<O> responseEventData = processRequest(requestEventData, eventHandler ); //new EventData<>(requestEventData.eventId, response);
            	hazelcastInstance.getTopic(getResponseTopicName(eventName, requestEventData.eventId.nodeId)).publish(responseEventData);
			}
		};
		ITopic<EventData<I>> requestTopic = hazelcastInstance.getTopic(getRequestTopicName(eventName, nodeId));
		requestTopic.addMessageListener(requestMessageListener);

		MessageListener<EventData<O>> responseMessageListener = new MessageListener<EventData<O>>() {
			@Override
			public void onMessage(Message<EventData<O>> message) {
				EventData<O> eventData = message.getMessageObject();
            	logger.debug("Event Response received. Event name: [{}], sender id: [{}]", eventData.eventId.eventName, eventData.eventId.nodeId);
            	EventResponse<O> eventResponse = responseHandlers.remove(eventData.eventId.eventId);
            	processResponse(eventData, eventResponse);
			}
		};
		ITopic<EventData<O>> responseTopic = hazelcastInstance.getTopic(getResponseTopicName(eventName, nodeId));
		responseTopic.addMessageListener(responseMessageListener);

		nodeEventsMap.put(eventName, nodeId);
	}

	public <I,O> void publish(String eventName, EventResponse<O> eventResponse, I args ) {
		EventId eventId = new EventId(nodeId, eventName, UUID.randomUUID().toString());
		EventData<I> eventData = new EventData<>(eventId, args);
		logger.debug("Publish event. Event name: [{}], sender id: [{}]", eventId.eventName, eventId.nodeId);

		String randomNodeId = chooseNode(eventName);

		if (randomNodeId.equals(nodeId)) {
			EventData<O> responseEventData = processRequest(eventData, eventHandlers.get(eventName));
			processResponse(responseEventData, eventResponse);
		} else {
			responseHandlers.put(eventId.eventId, eventResponse);
			hazelcastInstance.getTopic(getRequestTopicName(eventName, chooseNode(eventName))).publish(eventData);
		}
	}

	/**
	 *
	 * @return the nodeId of a cluster node that can handle the event
	 */
	private String chooseNode(String eventName) {
		//TODO apply Strategy to choose the node (Round Robin?)
		return nodeEventsMap.get(eventName).iterator().next();
	}

	String getRequestTopicName(String eventName, String nodeId) {
		return REQUEST_QUEUE_PREFIX + nodeId + "-" + eventName;
	}

	String getResponseTopicName(String eventName, String nodeId) {
		return RESPONSE_QUEUE_PREFIX + nodeId + "-" + eventName;
	}

	private <I,O> EventData<O> processRequest(EventData<I> requestEventData, final EventHandler<I,O> eventHandler) {
    	logger.debug("Event Received. Event name: [{}], sender id: [{}]", requestEventData.eventId.eventName, requestEventData.eventId.nodeId);
    	O response = eventHandler.process(requestEventData);
    	return new EventData<>(requestEventData.eventId, response);
	}

	private <O> void processResponse(EventData<O> eventData, EventResponse<O> eventResponse) {
		eventResponse.receive(eventData.args);
	}
}
