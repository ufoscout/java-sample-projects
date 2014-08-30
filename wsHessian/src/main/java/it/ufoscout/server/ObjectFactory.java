package it.ufoscout.server;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ObjectFactory {

	public static Map<BigInteger, List<BigInteger>> getObject(long mapItems, long listItems){
		Map<BigInteger, List<BigInteger>> map = new HashMap<BigInteger, List<BigInteger>>();
		
		for (int i=0;i<mapItems; i++) {
			BigInteger bigInt = BigInteger.valueOf( new Random().nextLong() );
			List<BigInteger> listBigInt = new ArrayList<BigInteger>();
			for (int j=0; j<listItems; j++) {
				listBigInt.add( BigInteger.valueOf( new Random().nextLong() ) );
			}
			map.put(bigInt, listBigInt);
		}
		return map;
	}
}
