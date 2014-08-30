package jms.ufo.counter;

import javax.jms.Message;
import javax.jms.MessageListener;

public class CounterMessageListener implements MessageListener {
	
	public void onMessage(final Message message) {

		for (int i = 0; i < 10; i++) {
			System.out.println("Counter is now: " + Counter.increase());
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
