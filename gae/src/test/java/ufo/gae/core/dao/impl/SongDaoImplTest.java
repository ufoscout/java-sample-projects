package ufo.gae.core.dao.impl;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

import ufo.gae.core.GaeBaseTest;
import ufo.gae.core.dao.SongDao;
import ufo.gae.core.domain.Song;

public class SongDaoImplTest extends GaeBaseTest {

	@Resource
	private SongDao songDao;

	@Test
	public void testCRUD() {
		final Song newSong = new Song();
		newSong.setTitle("title-" + UUID.randomUUID());
		newSong.setArtist("artist-" + UUID.randomUUID());

		ObjectifyService.ofy().transact(new VoidWork() {
			@Override
			public void vrun() {
				songDao.saveOrUpdateNow(newSong);

				System.out.println("Song id is " + newSong.getId());
				assertTrue(newSong.getId()>0);

				Song songFindById = songDao.findById(newSong.getId());
				assertEquals(newSong.getId(), songFindById.getId());
				assertEquals(newSong.getTitle(), songFindById.getTitle());
				assertEquals(newSong.getArtist(), songFindById.getArtist());
			}
		});

		List<Song> songsFoundByTitle = songDao.findByTitle(newSong.getTitle());
		List<Song> songsFoundByArtist = songDao.findByArtist(newSong.getArtist());
		assertFalse(songsFoundByTitle.isEmpty());
		assertFalse(songsFoundByArtist.isEmpty());

		assertEquals(newSong.getId(), songsFoundByTitle.get(0).getId());
		assertEquals(newSong.getTitle(), songsFoundByTitle.get(0).getTitle());
		assertEquals(newSong.getArtist(), songsFoundByTitle.get(0).getArtist());

		assertEquals(newSong.getId(), songsFoundByArtist.get(0).getId());
		assertEquals(newSong.getTitle(), songsFoundByArtist.get(0).getTitle());
		assertEquals(newSong.getArtist(), songsFoundByArtist.get(0).getArtist());
	}

}
