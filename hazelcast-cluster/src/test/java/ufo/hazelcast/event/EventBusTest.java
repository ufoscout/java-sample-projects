package ufo.hazelcast.event;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ufo.hazelcast.BaseTest;
import ufo.hazelcast.eventbus.EventBus;
import ufo.hazelcast.eventbus.EventData;
import ufo.hazelcast.eventbus.EventHandler;
import ufo.hazelcast.eventbus.EventResponse;

import com.hazelcast.core.HazelcastInstance;

public class EventBusTest extends BaseTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private HazelcastInstance hazelcastInstance;

	@Test
	public void testEventReply() throws InterruptedException {
		final EventBus eventBus = new EventBus(hazelcastInstance);

		final String eventName = "testEvent-" + UUID.randomUUID().toString();

		eventBus.register(eventName, new EventHandler<String, String>() {
			@Override
			public String process(EventData<String> eventData) {
				logger.debug("message: [{}]", eventData.args);
				return eventData.args + eventData.args;
			}
		});

		int threadsQuantity = 10000;
		final CountDownLatch countDownLatch = new CountDownLatch(threadsQuantity);

		Date now = new Date();

		for (int i=0; i<threadsQuantity; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					final String message = "hello world [" + new Random().nextInt() + "]";
					eventBus.publish(eventName, new EventResponse<String>() {
						@Override
						public void receive(String response) {
							logger.debug("Received reply : [{}]", response);
							assertEquals(message + message, response);
							countDownLatch.countDown();
						}
					}, message);
				}
			}).start();
		}
		countDownLatch.await();
		logger.info("execution time for [{}] events: {}ms", threadsQuantity, new Date().getTime() - now.getTime());
	}

}
