package com.gulag.neo;

import junit.framework.TestCase;

import com.gulag.neo.model.Person;
import static com.gulag.neo.NeoPersistanceUtility.*;

public class NeoTest extends TestCase {

	public void testNeo4j() throws Exception {
		
		Person p = new Person("gilad", "g");
		Person o = new Person("other", "o");
		p.addFriend(o);
		o.addFoe(p);
		
		
		log("the id for the new person = "+save(p));
		
		shutdown();
		

	}


}
