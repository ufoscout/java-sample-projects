package ufo.hazelcast.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SpringCacheableImpl implements SpringCacheable {

	public static int mySimpleDBCalls = 0;
	private final Map<String, Object> mySimpleDB = new HashMap<String, Object>();

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value=CacheService.CACHE_NAME, key="#key")
	public <T> T get(String key, Class<T> clazz) {
		mySimpleDBCalls++;
		return (T) mySimpleDB.get(key);
	}

	@Override
	public void saveToMySimpleDB(String key, Object value) {
		mySimpleDB.put(key, value);
	}

	@Override
	@CacheEvict(value = CacheService.CACHE_NAME)
	public void evict(String key) {
		// do nothing
	}

	@Override
	@CacheEvict(value = CacheService.CACHE_NAME, allEntries = true)
	public void evictAll() {
		// do nothing
	}

}
