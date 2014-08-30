package ufo.gae.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import ufo.gae.core.dao.SongDao;
import ufo.gae.core.domain.Song;

@Component
public class SongDaoImpl extends DaoReadWriteImpl<Song> implements SongDao {

	@Override
	protected Class<Song> getEntityType() {
		return Song.class;
	}

	@Override
	public List<Song> findByTitle(String title) {
		return ofy().load().type(Song.class).filter("title", title).list();
	}

	@Override
	public List<Song> findByArtist(String artist) {
		return ofy().load().type(Song.class).filter("artist", artist).list();
	}

	@Override
	public List<Song> findAll() {
		return ofy().load().type(Song.class).list();
	}

}
