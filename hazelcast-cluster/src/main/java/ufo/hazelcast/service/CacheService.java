package ufo.hazelcast.service;

import java.util.List;

/**
 * In this service the Infinispan cache is accessed directly.
 * This service is used by the web app console to be tested in the browser.
 * @author ufo
 *
 */
public interface CacheService {

	String CACHE_NAME = "default_direct_cache";

	void put(String key, Object value);

	void evict(String key);

	<T> T get(String key, Class<T> clazz);

	void evictAll();

	List<String> getSharedList();

	void addToSharedList(String value);
}
