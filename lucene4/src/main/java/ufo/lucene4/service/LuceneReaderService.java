/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.lucene4.service;


/**
 *
 * @author ufo
 */
public interface LuceneReaderService {

	/**
	 * Return the number of documents in the index
	 * @return
	 */
	int getIndexSize();

	/**
	 * It refresh the lucene indexes if needed
	 */
	void refreshReaders();

	int count(SearchQuery searchQuery);

	SearchResult search(SearchQuery searchQuery, final int maxResults, final int offset);

}
