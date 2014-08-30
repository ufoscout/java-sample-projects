package ufo.hazelcast.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import ufo.hazelcast.BaseTest;
import ufo.hazelcast.service.CacheService;

public class CacheServiceImplTest extends BaseTest {

	@Resource
	private CacheService cacheService;
	
	@Test
	public void test() {
		cacheService.put("first", "firstValue");
		
		assertEquals("firstValue", cacheService.get("first", String.class));
		
		cacheService.evictAll();
		
		assertEquals(null, cacheService.get("first", String.class));
	}

}
