package it.ufoscout.server;

import it.ufoscout.core.ISimpleHessian;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.caucho.hessian.server.HessianServlet;

/**
 * 
 * @author Francesco Cina'
 *
 * 28/feb/2011
 */
public class SimpleHessianServlet extends HessianServlet implements ISimpleHessian {

	private static final long serialVersionUID = 1L;
	
	public Map<BigInteger, List<BigInteger>> getObject(long mapItems, long listItems) {
		System.out.println("SERVLET: getObject() asked for " + mapItems + " " + listItems);
		long one = new Date().getTime();
		Map<BigInteger, List<BigInteger>> object = ObjectFactory.getObject(mapItems, listItems);
		long two = new Date().getTime();
		System.out.println("SERVLET: getObject() object created in " + (two - one) + "ms. Start send it.");
		return object;		
	}

	public long setObject(Map<BigInteger, List<BigInteger>> map) {
		long mapSize = map.size();
		long listSize = 0;
		System.out.print("SERVLET: setObject() asked with map " + mapSize);
		if (map.size()>0) {
			listSize = map.entrySet().iterator().next().getValue().size();
		}
		System.out.println(" " + listSize);
		return mapSize * listSize;
	}
}
