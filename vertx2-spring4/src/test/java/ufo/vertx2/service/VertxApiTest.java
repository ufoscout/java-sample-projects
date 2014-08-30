package ufo.vertx2.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.junit.Test;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;

import ufo.vertx2.VertxBaseTest;

public class VertxApiTest extends VertxBaseTest {

	@Resource
	private VertxService vertxService;

	@Test
	public void testNotNull() {
		assertNotNull(vertxService);
	}

	@Test
	  public void testSendingSimpleHelloWorldMessageWithReply() throws InterruptedException {

		final String echoAddress = "echo.address-" + UUID.randomUUID().toString();
		vertxService.eventBus().registerHandler(echoAddress, new Handler<Message<String>>() {
			@Override
			public void handle(Message<String> message) {
				getLogger().debug(echoAddress + " handler received message [" + message.body() + "]");
				message.reply(message.body() + message.body());
			}
		});

		int threadsQuantity = 1000;
		final CountDownLatch countDownLatch = new CountDownLatch(threadsQuantity);

		Date now = new Date();

		for (int i=0; i<threadsQuantity; i++) {
			final String echoMessage = "HelloWorld-" + UUID.randomUUID().toString();
			new Thread(new Runnable() {
				@Override
				public void run() {
					vertxService.eventBus().send(echoAddress, echoMessage, new Handler<Message<String>>(){
				        @Override
						public void handle(Message<String> message) {
				          assertNotNull(message);
				          getLogger().debug(echoAddress + " sender received replay message [" + message.body() + "]");
				          assertEquals(echoMessage + echoMessage, message.body());
				          countDownLatch.countDown();
				        }
				      }
				    );
				}
			}).start();
		}
		countDownLatch.await();
		getLogger().info("execution time for [{}] events: {}ms", threadsQuantity, new Date().getTime() - now.getTime());

	  }
}
