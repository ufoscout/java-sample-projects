package ufo.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ufo.test.aop.UfoAnnotation;

@Service
public class DoSomethingServiceImpl implements DoSomethingService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	@UfoAnnotation("one")
	public void doSomethingOne() {
		logger.info("Called");

	}

	@Override
	@UfoAnnotation("two")
	public String doSomethingTwo(String value) {
		logger.info("Called");
		return value;
	}

	@Override
	@UfoAnnotation(value = "three", noReturn=true )
	public String doSomethingThree(String value) {
		logger.info("Called");
		return value;
	}

}
