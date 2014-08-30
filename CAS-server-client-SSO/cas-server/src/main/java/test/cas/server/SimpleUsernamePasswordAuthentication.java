package test.cas.server;

import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Authenticate if username and password are equals
 * @author ufo
 *
 */
public class SimpleUsernamePasswordAuthentication extends AbstractUsernamePasswordAuthenticationHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected boolean authenticateUsernamePasswordInternal(UsernamePasswordCredentials credentials)
			throws AuthenticationException {

		logger.info("Try to login username [{}] & password [{}]" , new Object[]{credentials.getUsername(), credentials.getPassword()});
		boolean result =  credentials.getUsername().equals(credentials.getPassword());
		logger.info("Authentication result = {}", result);
		return result;
	}


}
