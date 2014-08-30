package ufo.test.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import ufo.test.BaseTest;
import ufo.test.aop.Actions;

public class DoSomethingServiceImplTest extends BaseTest {

	@Resource
	private DoSomethingService dds;
	@Resource
	private Actions actions;
	
	@Before
	public void setUp() {
		assertNotNull(dds);
		assertNotNull(actions);
		actions.calls.clear();
	}
	
	@Test
	public void testDoSomethingOne() {
		dds.doSomethingOne();
		assertEquals(1, actions.calls.size());
		assertEquals("doSomethingOne", actions.calls.get(0));
	}
	
	@Test
	public void testDoSomethingTwo() {
		String result = dds.doSomethingTwo("hello");
		assertEquals("hello", result);
		assertEquals(1, actions.calls.size());
		assertEquals("doSomethingTwo", actions.calls.get(0));
	}
	
	@Test
	public void testDoSomethingThree() {
		String result = dds.doSomethingThree("hello");
		assertNull(result);
		assertEquals(1, actions.calls.size());
		assertEquals("doSomethingThree", actions.calls.get(0));
	}

}
