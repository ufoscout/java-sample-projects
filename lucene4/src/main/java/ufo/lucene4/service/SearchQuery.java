package ufo.lucene4.service;

import java.util.ArrayList;
import java.util.List;

import ufo.lucene4.domain.Genre;

public class SearchQuery {

	public String keywords = "";
	public final List<String> authors = new ArrayList<>();
	public final List<Genre> genres = new ArrayList<>();

}
