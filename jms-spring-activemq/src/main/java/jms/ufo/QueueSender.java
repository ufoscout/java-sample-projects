package jms.ufo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueSender
{
	
	@Autowired
    private JmsTemplate jmsTemplate;

    public void send( final String message )
    {
        getJmsTemplate().convertAndSend( message );
    }
    
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
}