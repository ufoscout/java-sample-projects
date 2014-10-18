package ufo.spring4.boot.web.rest;

import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ufo.spring4.boot.web.BaseIntegrationTest;
import static org.junit.Assert.*;

public class HelloWorldControllerIntegrationTest extends BaseIntegrationTest {

	@Test
	public void testHelloWorldReply() {
		RestTemplate rest = new TestRestTemplate();
	    ResponseEntity<String> response = rest.getForEntity(serverUrl + "/hello", String.class);
	    assertEquals( HttpStatus.OK, response.getStatusCode());

	    logger.info("Received: [{}]", response.getBody());

	    assertTrue(response.getBody().contains("Hello World"));

	}

}
