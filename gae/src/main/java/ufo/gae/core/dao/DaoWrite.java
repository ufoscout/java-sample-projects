package ufo.gae.core.dao;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;

public interface DaoWrite<T> {

	Result<Key<T>> saveOrUpdate(T entity);

	Key<T> saveOrUpdateNow(T entity);

	void delete(T entity);

	void deleteNow(T entity);

}
