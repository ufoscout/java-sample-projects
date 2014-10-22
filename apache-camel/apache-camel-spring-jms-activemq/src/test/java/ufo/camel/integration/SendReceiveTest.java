package ufo.camel.integration;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Producer;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.Synchronization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ufo.camel.CamelConfig;
import ufo.camel.CamelContextService;
import ufo.camel.service.ReceiveOnlyConsumerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CamelConfig.class })
public class SendReceiveTest {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CamelContextService camelContextService;
	@Autowired
	private ReceiveOnlyConsumerService springConsumerService;

	@Test
	public void testSimpleQueue() throws Exception {
		final String queueName = UUID.randomUUID().toString();
		CamelContext camelContext = camelContextService.getContext();
		assertNotNull(camelContext);

		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() {
				// Writes the input to files in the target folder
				from("test-jms:queue:" + queueName).to("file://target");
			}
		});

		ProducerTemplate template = camelContext.createProducerTemplate();

		for (int i = 0; i < 10; i++) {
			template.sendBody("test-jms:queue:" + queueName, "Test Message: " + i);
		}

		Thread.sleep(500);
	}

	@Test
	public void testSendAsynch() throws Exception {
		springConsumerService.getConsumedValues().clear();

		final String queueName = UUID.randomUUID().toString();
		CamelContext camelContext = camelContextService.getContext();
		ProducerTemplate template = camelContext.createProducerTemplate();

		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() {
				// Writes the input to files in the target folder
				from("test-jms:queue:" + queueName).beanRef("RECEIVE_ONLY_CONSUMER_SERVICE", "valueConsumer");
			}
		});

		template.sendBody("test-jms:queue:" + queueName, "Hello");

		Thread.sleep(500);

		assertFalse(springConsumerService.getConsumedValues().isEmpty());
		assertTrue(springConsumerService.getConsumedValues().contains("Hello"));

	}

	@Test
	public void testSynchRequestReply() throws Exception {

		final String queueName = UUID.randomUUID().toString();
		CamelContext camelContext = camelContextService.getContext();

		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() {
				// Writes the input to files in the target folder
				from("test-jms:queue:" + queueName)
				.setExchangePattern(ExchangePattern.InOut)
				.beanRef("REQUEST_REPLY_CONSUMER_SERVICE", "multiply");
			}
		});

		ProducerTemplate template = camelContext.createProducerTemplate();
		template.start();
		int value = new Random().nextInt(10);
		final Date now = new Date();
		logger.info("Sending value: {}", value);
		int response = template.requestBody("test-jms:queue:" + queueName, value, Integer.class);
		logger.info("received resposne: {} in {}ms", response, (new Date().getTime() - now.getTime()));
		template.stop();

		assertEquals(value * value, response);

	}


	@Test
	public void testASynchRequestReply() throws Exception {

		final String queueName = UUID.randomUUID().toString();
		CamelContext camelContext = camelContextService.getContext();

		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() {
				// Writes the input to files in the target folder
				from("test-jms:queue:" + queueName+ "?exchangePattern=InOut")
				.setExchangePattern(ExchangePattern.InOut)
				.beanRef("REQUEST_REPLY_CONSUMER_SERVICE", "multiply");
			}
		});

		final ProducerTemplate template = camelContext.createProducerTemplate();
		template.start();

		final int value = new Random().nextInt(10);
		final AtomicInteger result = new AtomicInteger(0);
		final CountDownLatch latch = new CountDownLatch(1);

		final Date now = new Date();
		logger.info("Sending value: {}", value);
		template.asyncCallbackRequestBody("test-jms:queue:" + queueName, value, new Synchronization() {

			@Override
			public void onComplete(Exchange exchange) {
				result.set( exchange.getOut().getBody(Integer.class) );
				logger.info("onComplete called. received: {} in {}ms", result.get(), (new Date().getTime() - now.getTime()));
				try {
					template.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				latch.countDown();
			}

			@Override
			public void onFailure(Exchange exchange) {
				logger.warn("Failure");
				try {
					template.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});

		latch.await();

		assertEquals(value * value, result.get());

	}


	//This test is the same as the testSynchConsumer; just more verbose...
	@Test
	public void testSynchRequestReplyEndpointInvocation() throws Exception {
		final String queueName = UUID.randomUUID().toString();
		CamelContext camelContext = camelContextService.getContext();

		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() {
				// Writes the input to files in the target folder
				from("test-jms:queue:" + queueName).beanRef("REQUEST_REPLY_CONSUMER_SERVICE", "multiply");
			}
		});

		// get the endpoint from the camel context
		Endpoint endpoint = camelContext.getEndpoint("test-jms:queue:" + queueName);

		// create the exchange used for the communication
		// we use the in out pattern for a synchronized exchange where we expect
		// a response
		Exchange exchange = endpoint.createExchange(ExchangePattern.InOut);
		// set the input on the in body
		// must you correct type to match the expected type of an Integer object
		int value = new Random().nextInt(10);
		exchange.getIn().setBody(value);

		// to send the exchange we need an producer to do it for us
		Producer producer = endpoint.createProducer();
		// start the producer so it can operate
		producer.start();

		// let the producer process the exchange where it does all the work in
		// this one line of code
		producer.process(exchange);

		// get the response from the out body and cast it to an integer
		int response = exchange.getOut().getBody(Integer.class);

		assertEquals("Get a wrong response.", value * value, response);

		// stop the producer after usage
		producer.stop();
	}

}
