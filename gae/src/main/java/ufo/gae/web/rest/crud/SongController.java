package ufo.gae.web.rest.crud;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import ufo.gae.core.dao.SongDao;
import ufo.gae.core.domain.Song;
import ufo.gae.core.service.SongService;

@Controller
@Path("/crud/song")
public class SongController {

	@Resource
	private SongDao songDao;
	@Resource
	private SongService songService;

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Song find(@PathParam("id") final long id) {
		return songDao.findById(id);
	}

	//	@POST
	//	@Consumes({ MediaType.APPLICATION_JSON })
	//	@Produces({ MediaType.APPLICATION_JSON })
	//	public Song save(final Song song) {
	//		songDao.saveOrUpdateNow(song);
	//		return song;
	//	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Song save(final Song song) {
		return songService.save(song);
	}

	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public void delete(final Song song) {
		songDao.deleteNow(song);
	}

}
