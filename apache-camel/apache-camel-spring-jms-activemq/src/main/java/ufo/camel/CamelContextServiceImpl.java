package ufo.camel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.ConnectionFactory;

import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CamelContextServiceImpl implements CamelContextService {

	public static String CONN_FACTORY_NAME = "test-jms";

	@Autowired
	private ApplicationContext context;
	@Autowired
	private ConnectionFactory connectionFactory;
	private CamelContext camelContext;

	@PostConstruct
	public void init() {
		try {
			camelContext = new SpringCamelContext(context);
			camelContext.addComponent(CONN_FACTORY_NAME, JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
			camelContext.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PreDestroy
	public void tearDown() {
		try {
			camelContext.stop();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public CamelContext getContext() {
		return camelContext;
	}

}
