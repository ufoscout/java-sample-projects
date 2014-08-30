package ufo.hazelcast.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import ufo.hazelcast.BaseTest;
import ufo.hazelcast.service.SpringCacheable;
import ufo.hazelcast.service.SpringCacheableImpl;

public class SpringCacheableImplTest extends BaseTest {

	@Resource
	private SpringCacheable springCacheable;

	@Test
	public void test() {
		assertEquals(0, SpringCacheableImpl.mySimpleDBCalls);

		springCacheable.saveToMySimpleDB("key1", "HelloFirstWorld");
		springCacheable.saveToMySimpleDB("key2", "HelloSecondWorld");

		assertEquals("HelloFirstWorld" , springCacheable.get("key1", String.class) );
		assertEquals(1, SpringCacheableImpl.mySimpleDBCalls);
		assertEquals("HelloFirstWorld" , springCacheable.get("key1", String.class) );
		assertEquals(1, SpringCacheableImpl.mySimpleDBCalls);

		assertEquals("HelloSecondWorld" , springCacheable.get("key2", String.class) );
		assertEquals(2, SpringCacheableImpl.mySimpleDBCalls);
		assertEquals("HelloSecondWorld" , springCacheable.get("key2", String.class) );
		assertEquals(2, SpringCacheableImpl.mySimpleDBCalls);

		springCacheable.evict("key1");
		springCacheable.get("key1", String.class);
		assertEquals(3, SpringCacheableImpl.mySimpleDBCalls);
		springCacheable.get("key1", String.class);
		assertEquals(3, SpringCacheableImpl.mySimpleDBCalls);
		springCacheable.get("key2", String.class);
		assertEquals(3, SpringCacheableImpl.mySimpleDBCalls);

		springCacheable.evictAll();
		springCacheable.get("key2", String.class);
		assertEquals(4, SpringCacheableImpl.mySimpleDBCalls);
	}

}
