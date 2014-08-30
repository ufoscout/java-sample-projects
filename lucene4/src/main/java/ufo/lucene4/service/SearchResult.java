package ufo.lucene4.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ufo.lucene4.domain.Genre;

public class SearchResult {

	public int totalResultsCount;
	public final List<Long> bookIds = new ArrayList<>();

	public final Map<Genre, Integer> genreFacet = new HashMap<Genre, Integer>();
	public final Map<String, Integer> authorFacet = new HashMap<String, Integer>();

}
