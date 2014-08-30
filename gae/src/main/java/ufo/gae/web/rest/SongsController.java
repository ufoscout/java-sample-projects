package ufo.gae.web.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import ufo.gae.core.dao.SongDao;
import ufo.gae.core.domain.Song;

@Controller
@Path("songs")
public class SongsController {

	@Resource
	private SongDao songDao;

	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Song> findAll() {
		System.out.println("songDao is " + songDao);
		return songDao.findAll();
	}

	@Path("/artist/{artist}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Song> findAll(@PathParam("artist") String artist) {
		return songDao.findByArtist(artist);
	}

}
