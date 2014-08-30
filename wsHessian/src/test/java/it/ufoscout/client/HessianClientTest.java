package it.ufoscout.client;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import it.ufoscout.core.ISimpleHessian;
import it.ufoscout.server.ObjectFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import junit.framework.TestCase;
import test.EmbeddedJettyServer;

/**
 * 
 * @author Francesco Cina'
 *
 * 28/feb/2011
 */
public class HessianClientTest extends TestCase {

	private EmbeddedJettyServer server;
	private ISimpleHessian simpleHessian;
	
	int loopTimes = 11;
	int baseItemsNumber = 2;

	protected void setUp() throws Exception {
		super.setUp();
		server = EmbeddedJettyServer.instance();
		
		HessianProxyFactory factory = new HessianProxyFactory();
		factory.setHessian2Reply(true);
		factory.setHessian2Request(true);
		simpleHessian = (ISimpleHessian) factory.create(server.getBaseWebUrl() + "/hessian");
		assertNotNull(simpleHessian);
		System.gc();
	}

	protected void tearDown() throws Exception {
		System.gc();
		super.tearDown();
	}
	
	public void testClientGet() throws Exception {
		for (int i=0; i<loopTimes; i++) {
			long howManyMapItems = baseItemsNumber;
			long howManyListItems = baseItemsNumber;
			howManyMapItems = (long) Math.pow(howManyMapItems, i);
			howManyListItems = (long) Math.pow(howManyListItems, i);
			System.out.println("CLIENT: start setObject with " + howManyMapItems + " " + howManyListItems );
			long one = new Date().getTime();
			Map<BigInteger, List<BigInteger>> result = simpleHessian.getObject(howManyMapItems, howManyListItems);
			long two = new Date().getTime();
			System.out.println("CLIENT: end getObject in " + (two-one) + "ms" );
			assertEquals( howManyMapItems ,  result.size());
			assertEquals( howManyListItems ,  result.entrySet().iterator().next().getValue().size());
		}
	}
	
	public void testClientSet() throws Exception {
		
		for (int i=0; i<loopTimes; i++) {
			long howManyMapItems = baseItemsNumber;
			long howManyListItems = baseItemsNumber;
			howManyMapItems = (long) Math.pow(howManyMapItems, i);
			howManyListItems = (long) Math.pow(howManyListItems, i);
			System.out.println("CLIENT: ObjectFactory.getObject() asked for " + howManyMapItems + " " + howManyListItems);
			long one = new Date().getTime();
			Map<BigInteger, List<BigInteger>> object = ObjectFactory.getObject(howManyMapItems, howManyMapItems);
			long two = new Date().getTime();
			System.out.println("CLIENT: ObjectFactory.getObject() object created in " + (two - one) + "ms. Start send it.");
			long result = simpleHessian.setObject(object);
			long three = new Date().getTime();
			System.out.println("CLIENT: end getObject in " + (three-two) + "ms" );
			assertEquals( howManyMapItems * howManyListItems ,  result);
		}
	}

}
