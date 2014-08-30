package ufo.springreactor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static reactor.event.selector.Selectors.$;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import reactor.core.Reactor;
import reactor.event.Event;
import reactor.function.Consumer;

@ContextConfiguration(classes = SpringConfig.class)
public class ReactorPublishTest extends BaseTest {

    @Autowired
    private Reactor reactor;

	@Test
	public void testPublishMessageToAllListener() throws InterruptedException {
		final String echoAddress = "echo.address-" + UUID.randomUUID().toString();
		final String echoMessage = "HelloWorld-" + UUID.randomUUID().toString();
		final AtomicBoolean oneCalled = new AtomicBoolean(false);
		final AtomicBoolean twoCalled = new AtomicBoolean(false);
		final CountDownLatch countDownLatch = new CountDownLatch(2);

	    reactor.on($(echoAddress), new Consumer<Event<String>>() {
			@Override
			public void accept(Event<String> event) {
				getLogger().info(echoAddress + " handler ONE received message [" + event.getData() + "]");
				oneCalled.set(true);
				countDownLatch.countDown();
			}
		});

	    reactor.on($(echoAddress), new Consumer<Event<String>>() {
			@Override
			public void accept(Event<String> event) {
				getLogger().info(echoAddress + " handler TWO received message [" + event.getData() + "]");
				twoCalled.set(true);
				countDownLatch.countDown();
			}
		});

	    reactor.notify(echoAddress, Event.wrap(echoMessage));
	    countDownLatch.await();
	    assertTrue(oneCalled.get());
	    assertTrue(twoCalled.get());
	}


	@Test
	public void testPublishMessageToOneSingleReceiver() throws InterruptedException {
		final String echoAddress = "echo.address-" + UUID.randomUUID().toString();
		final String echoMessage = "HelloWorld-" + UUID.randomUUID().toString();
		final AtomicBoolean oneCalled = new AtomicBoolean(false);
		final AtomicBoolean twoCalled = new AtomicBoolean(false);
		final CountDownLatch countDownLatch = new CountDownLatch(2);

	    reactor.on($(echoAddress), new Consumer<Event<String>>() {
			@Override
			public void accept(Event<String> event) {
				getLogger().info(echoAddress + " handler ONE received message [" + event.getData() + "]");
				oneCalled.set(true);
				countDownLatch.countDown();
			}
		});

	    reactor.on($(echoAddress), new Consumer<Event<String>>() {
			@Override
			public void accept(Event<String> event) {
				getLogger().info(echoAddress + " handler TWO received message [" + event.getData() + "]");
				twoCalled.set(true);
				countDownLatch.countDown();
			}
		});

		reactor.sendAndReceive(echoAddress, Event.wrap(echoMessage), new Consumer<Event<String>>() {
			@Override
			public void accept(Event<String> t) {
				getLogger().info("Received reply: ");
			}
		});

	    countDownLatch.await();
	    assertTrue(oneCalled.get());
	    assertFalse(twoCalled.get());
	}

}
