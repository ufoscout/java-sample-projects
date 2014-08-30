package ufo.hazelcast.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

	private static final String SHARED_LIST_NAME = "shared_list";
	@Resource
	private CacheManager cacheManager;

	@Override
	public List<String> getSharedList() {
		return Collections.unmodifiableList(sharedList());
	}

	@Override
	public void addToSharedList(String value) {
		List<String> list = sharedList();
		list.add(value);
		cacheManager.getCache(CACHE_NAME).put(SHARED_LIST_NAME, list);
	}

	@Override
	public void put(String key, Object value) {
		cacheManager.getCache(CACHE_NAME).put(key, value);
	}

	@Override
	public void evict(String key) {
		cacheManager.getCache(CACHE_NAME).evict(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> clazz) {
		ValueWrapper result = cacheManager.getCache(CACHE_NAME).get(key);
		if (result != null) {
			return (T) result.get();
		}
		return null;
	}

	@Override
	public void evictAll() {
		cacheManager.getCache(CACHE_NAME).clear();
	}

	private List<String> sharedList() {
		@SuppressWarnings("unchecked")
		List<String> list = get(SHARED_LIST_NAME, List.class);
		if (list==null) {
			list = new ArrayList<String>();
			put(SHARED_LIST_NAME, list);
		}
		return list;
	}

}
