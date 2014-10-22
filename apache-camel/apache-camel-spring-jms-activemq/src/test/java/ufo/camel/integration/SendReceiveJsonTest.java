package ufo.camel.integration;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.UUID;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import ufo.camel.CamelConfig;
import ufo.camel.CamelContextService;
import ufo.camel.service.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CamelConfig.class })
public class SendReceiveJsonTest {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private CamelContextService camelContextService;

	private String getUserUri;
	private String saveUserUri;

	@Before
	public void setUp() throws Exception {
		final String queueSuffix = UUID.randomUUID().toString();

		getUserUri = "test-jms:queue:getUser" + queueSuffix;
		saveUserUri = "test-jms:queue:saveUser" + queueSuffix;

		CamelContext camelContext = camelContextService.getContext();

		camelContext.addRoutes(new RouteBuilder() {

			@Override
			public void configure() {

				//GET USER
				from(getUserUri)
				.setExchangePattern(ExchangePattern.InOut)
				.beanRef("USER_SERVICE", "getUser")
				.marshal().json(JsonLibrary.Jackson);


				//SAVE USER
				from("direct:saveUser")
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal().json(JsonLibrary.Jackson)
				.to(saveUserUri);

				from(saveUserUri)
				.setExchangePattern(ExchangePattern.InOnly)
				.unmarshal().json(JsonLibrary.Jackson, User.class)
				.beanRef("USER_SERVICE", "saveUser");

			}

		});

	}

	@Test
	public void testUserJsonFlow() throws Exception {

		CamelContext camelContext = camelContextService.getContext();

		User user = new User();
		user.age = new Random().nextInt(100);
		user.firstname = "ufo";
		user.lastname = "scout";
		user.username = UUID.randomUUID().toString();

		ProducerTemplate template = camelContext.createProducerTemplate();
		template.start();

		logger.info("Sending user: {}", user.username);
		template.sendBody("direct:saveUser", user);
		logger.info("User {} sent", user.username);

		Thread.sleep(1000);

		logger.info("Getting user: {}", user.username);
		String jsonUserLoaded = template.requestBody(getUserUri, user.username, String.class);
		logger.info("Found user: {}", jsonUserLoaded);

		assertNotNull(jsonUserLoaded);
		User userLoaded = mapper.readValue(jsonUserLoaded, User.class);

		assertEquals(user.age, userLoaded.age);
		assertEquals(user.firstname, userLoaded.firstname);
		assertEquals(user.lastname, userLoaded.lastname);
		assertEquals(user.username, userLoaded.username);

		template.stop();

	}

}
