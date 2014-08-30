package it.ufoscout.core;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface ISimpleHessian {

	Map<BigInteger, List<BigInteger>> getObject(long mapItems, long listItems);

	public long setObject(Map<BigInteger, List<BigInteger>> map);
	
}
