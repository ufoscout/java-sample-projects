package jms.ufo.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class CounterQueueSender
{
	
	@Autowired
	@Qualifier("counterJmsTemplate")
	private JmsTemplate jmsTemplate;

    public void startCounter( )
    {
        getJmsTemplate().convertAndSend( "counterQueue", "" );
    }
    
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
}