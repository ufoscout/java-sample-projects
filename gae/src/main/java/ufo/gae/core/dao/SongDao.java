package ufo.gae.core.dao;

import java.util.List;

import ufo.gae.core.domain.Song;

public interface SongDao extends DaoRead<Song>, DaoWrite<Song> {

	List<Song> findAll();

	List<Song> findByTitle(String title);

	List<Song> findByArtist(String artist);

}
