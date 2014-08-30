package groovy.simple;

import static org.junit.Assert.*;

import org.junit.Test;

public class JettyTest {

	@Test
	public void test() throws Exception {
		assertFalse ( EmbeddedJettyServer.instance().getBaseWebUrl().isEmpty() );
	}

}
