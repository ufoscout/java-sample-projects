package ufo.hazelcast.event;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ufo.hazelcast.BaseTest;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

public class HazelcastQueueCallbackTest extends BaseTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private HazelcastInstance hazelcastInstance;
	private final Executor pollersExecutor = Executors.newFixedThreadPool(10);
	private final Executor responseExecutor = Executors.newFixedThreadPool(10);

	@Test
	public void testTopic() throws InterruptedException {

		final String requestName = "request-" + UUID.randomUUID();
		final String responseName = "response-" + UUID.randomUUID();

		final IQueue<String> requestQueue = hazelcastInstance.getQueue(requestName);
		final IQueue<String> responseQueue = hazelcastInstance.getQueue(responseName);

		final CountDownLatch countDownLatch = new CountDownLatch(2);
		final List<String> entries = new ArrayList<>();

		pollersExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                        	String message = requestQueue.take();
                        	responseQueue.add("response-" + message);
                        	entries.add(message);
                        	logger.info("Received request [{}]", message);
                        	countDownLatch.countDown();
                        } catch (InterruptedException e) {
                            logger.info("Take process was interrupted. Possible reason - shutdown is in process", e);
                            break;
                        }
                    }
                }
            });

		responseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                    	String message = responseQueue.take();
                    	logger.info("Received response [{}]", message);
                    	entries.add(message);
                    	countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        logger.info("Take process was interrupted. Possible reason - shutdown is in process", e);
                        break;
                    }
                }
            }
        });

		String message = "Hello-" + UUID.randomUUID();

		requestQueue.add(message);

		countDownLatch.await(5, TimeUnit.SECONDS);
		assertEquals( 2 , entries.size());
		assertEquals(message , entries.get(0));
		assertEquals("response-" + message , entries.get(1));

	}

}
