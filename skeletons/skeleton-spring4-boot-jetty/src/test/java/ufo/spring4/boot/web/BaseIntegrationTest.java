package ufo.spring4.boot.web;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ufo.spring4.boot.web.config.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest({"server.port=0", "management.port=0"})
public abstract class BaseIntegrationTest {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
    protected EmbeddedWebApplicationContext server;
    @Value("${local.server.port}")
    protected int serverPort;
    protected String contextPath;
	protected String serverUrl;

	@Before
	public void setUpBeforeAllTests() {
		String contextPath = server.getServletContext().getContextPath();
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length());
		}
		serverUrl = "http://localhost:" + serverPort + contextPath;
	}

}
