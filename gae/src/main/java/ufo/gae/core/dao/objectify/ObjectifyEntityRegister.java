package ufo.gae.core.dao.objectify;

import org.springframework.stereotype.Component;

import ufo.gae.core.domain.Song;

import com.googlecode.objectify.ObjectifyService;

@Component
public class ObjectifyEntityRegister {

	public ObjectifyEntityRegister() {
		ObjectifyService.factory().register(Song.class);
	}

}
