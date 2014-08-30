/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufo.gae.core.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 *
 * @author ufo
 */
@Entity
public class Song {

	@Id
	private Long id;
	private Long lyricId;
	@Index
	@NotNull(message="notNull")
	@Size(min = 3, message="minLenght3")
	private String title;
	@Index
	@NotNull(message="notNull")
	@Size(min = 3, message="minLenght3")
	private String artist;
	//		@Size(min = 4, message="minLenght4")
	@Min(value=1900, message="minSize1900")
	private Integer year;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getLyricId() {
		return lyricId;
	}

	public void setLyricId(Long lyricId) {
		this.lyricId = lyricId;
	}

}
