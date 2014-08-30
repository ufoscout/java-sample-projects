package test.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import test.BaseTest;

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
