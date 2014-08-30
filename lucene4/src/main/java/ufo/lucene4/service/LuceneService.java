/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.lucene4.service;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.facet.taxonomy.TaxonomyReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.SearcherManager;

/**
 *
 * @author ufo
 */
public interface LuceneService {

    Analyzer getAnalyzer();

    SearcherManager getSearcherManager();

    TaxonomyReader getTaxonomyReader();

    LuceneWriters getWriters();

	void closeWriters(LuceneWriters writers);

	void commitWriters(LuceneWriters writers);

	/**
	 * Release the {@link IndexSearcher} if not null
	 * @param searcher
	 */
	void release(IndexSearcher searcher);

}
