package ufo.gae.core.dao.impl;

import com.googlecode.objectify.ObjectifyService;
import ufo.gae.core.dao.DaoRead;
import ufo.gae.core.dao.DaoWrite;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Result;

public abstract class DaoReadWriteImpl<T> implements DaoRead<T>, DaoWrite<T> {

	@Override
	public final T findById(long id) {
		return ofy().load().type(getEntityType()).id(id).get();
	}

	@Override
	public final Result<Key<T>> saveOrUpdate(T entity) {
		return ofy().save().entity(entity);
	}

	@Override
	public final Key<T> saveOrUpdateNow(T entity) {
		return ofy().save().entity(entity).now();
	}

	@Override
	public final void delete(T entity) {
		ofy().delete().entity(entity);
	}

	@Override
	public final void deleteNow(T entity) {
		ofy().delete().entity(entity).now();
	}

	protected final Objectify ofy(){
		return ObjectifyService.ofy();
	}

	protected abstract Class<T> getEntityType();

}
