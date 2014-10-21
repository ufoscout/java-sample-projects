package ufo.camel.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component(value="REQUEST_REPLY_CONSUMER_SERVICE")
public class RequestReplyConsumerServiceImpl implements RequestReplyConsumerService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ApplicationContext context;

	@PostConstruct
	public void init() {
		logger.info("{} started", getClass().getSimpleName());
	}

	@Override
	public int multiply(int value) {
		logger.info("--------------------------------------------------------------");
		logger.info("Received: " + value);
		logger.info("--------------------------------------------------------------");
		return value*value;
	}

}
