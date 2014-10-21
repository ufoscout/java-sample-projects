package ufo.camel.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component(value="RECEIVE_ONLY_CONSUMER_SERVICE")
public class ReceiveOnlyConsumerServiceImpl implements ReceiveOnlyConsumerService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final List<String> consumedValues = new ArrayList<>();

	@Autowired
	private ApplicationContext context;

	@PostConstruct
	public void init() {
		logger.info("{} started", getClass().getSimpleName());
	}

	@Override
	@Consume(uri="test-jms:queue:valueConsumer")
	public void valueConsumer(String value) {
		logger.info("--------------------------------------------------------------");
		logger.info("Received: " + value);
		logger.info("AppContext is: " + context);
		logger.info("--------------------------------------------------------------");
		consumedValues.add(value);
	}

	@Override
	public List<String> getConsumedValues() {
		return consumedValues;
	}

}
