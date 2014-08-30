package ufo.gae.core.dao.impl;

import org.springframework.stereotype.Component;

import ufo.gae.core.dao.SongLyricDao;
import ufo.gae.core.domain.SongLyric;

@Component
public class SongLyricDaoImpl extends DaoReadWriteImpl<SongLyric> implements SongLyricDao {

	@Override
	protected Class<SongLyric> getEntityType() {
		return SongLyric.class;
	}

}
