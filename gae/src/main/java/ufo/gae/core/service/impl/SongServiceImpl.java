package ufo.gae.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ufo.gae.core.dao.SongDao;
import ufo.gae.core.domain.Song;
import ufo.gae.core.service.SongService;
import ufo.gae.core.service.validation.ValidationService;

@Component
public class SongServiceImpl implements SongService {

	@Resource
	private ValidationService validationService;
	@Resource
	private SongDao songDao;

	@Override
	public Song save(Song song) {
		validationService.validator(song).validateThrowException();
		songDao.saveOrUpdateNow(song);
		return song;
	}

}
