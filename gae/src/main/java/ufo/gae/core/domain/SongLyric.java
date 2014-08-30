/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufo.gae.core.domain;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 *
 * @author ufo
 */
@Entity
public class SongLyric {

	static {
		ObjectifyService.factory().register(SongLyric.class);
	}

	@Id
	private Long id;
	private String lyric;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

}
