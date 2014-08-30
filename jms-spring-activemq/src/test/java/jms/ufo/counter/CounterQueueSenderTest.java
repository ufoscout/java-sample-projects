package jms.ufo.counter;

import static org.junit.Assert.*;
import jms.ufo.BaseTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CounterQueueSenderTest extends BaseTest {

	@Autowired
	private CounterQueueSender counterQueueSender;
	
	@Test
	public void test() throws InterruptedException {
		assertNotNull(counterQueueSender);
		assertNotNull(counterQueueSender.getJmsTemplate());
		
		System.out.println("sending message");
		counterQueueSender.startCounter();
		System.out.println("message sent");
		Thread.sleep(600);
		int count = Counter.count();
		System.out.println("Count is: " + count);
		assertTrue(count>0);
	}

}
