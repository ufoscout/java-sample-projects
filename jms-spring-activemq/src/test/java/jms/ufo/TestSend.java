package jms.ufo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestSend extends BaseTest {

	@Autowired
	private QueueSender queueSender;
	
	@Test
	public void test() throws InterruptedException {
		assertNotNull(queueSender);
		assertNotNull(queueSender.getJmsTemplate());
		
		String message = "The smarter message I can write";
		System.out.println("sending message");
		queueSender.send(message);
		System.out.println("message sent");
		Thread.sleep(250);
	}

}
