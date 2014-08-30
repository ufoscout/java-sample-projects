package ufo.hazelcast.event;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ufo.hazelcast.BaseTest;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

public class HazelcastTopicTest extends BaseTest {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private HazelcastInstance hazelcastInstance;
	@Test
	public void testTopic() throws InterruptedException {
		
		String eventName = "event-" + UUID.randomUUID();
		ITopic<String> topic = hazelcastInstance.getTopic(eventName);
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		final List<String> entries = new ArrayList<>();
		
		topic.addMessageListener(new MessageListener<String>() {
			@Override
			public void onMessage(Message<String> message) {
				logger.info("Topic listener, message received: [{}]", message.getMessageObject());
				entries.add(message.getMessageObject());
				countDownLatch.countDown();
			}
		});
		
		String message = "Hello-" + UUID.randomUUID();
		
		topic.publish(message);
		
		countDownLatch.await(5, TimeUnit.SECONDS);
		assertEquals( 1 , entries.size());
		assertEquals(message , entries.get(0));
		
	}

}
