package jms.ufo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueListener implements MessageListener
{
    public void onMessage( final Message message )
    {
        if ( message instanceof TextMessage )
        {
            final TextMessage textMessage = (TextMessage) message;
            try
            {
            	Thread.sleep(500);
            	System.out.println("Listener activated. Received message: ");
                System.out.println( "[" + textMessage.getText() + "]" );
            }
            catch (final JMSException e)
            {
                e.printStackTrace();
            } catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
}
