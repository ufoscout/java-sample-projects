package ufo.hazelcast.service;


public interface SpringCacheable {

	<T> T get(String key, Class<T> clazz);

	void saveToMySimpleDB(String key, Object value);

	void evict(String key);

	void evictAll();
}
