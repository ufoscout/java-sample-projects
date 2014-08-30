/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.lucene4.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.facet.taxonomy.TaxonomyReader;
import org.apache.lucene.facet.taxonomy.TaxonomyWriter;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyReader;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyWriter;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author ufo
 */
@Service
public class LuceneServiceImpl implements LuceneService {

	private final String relativeLuceneIndexesFolderPath = "luceneIndexes";
	private final String relativeLuceneTaxonomiesFolderPath = "luceneTaxonomies";
	private final Analyzer analyser = new StandardAnalyzer(LuceneConstants.LUCENE_VERSION);
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${lucene.basefolder}")
	private String baseLuceneFolderPath;
	private File indexDirectory;
	private File taxonomyDirectory;
	private SearcherManager searcherManager;

	@PostConstruct
	private void init() throws IOException {
		indexDirectory = new File(baseLuceneFolderPath, relativeLuceneIndexesFolderPath);
		indexDirectory.mkdirs();
		taxonomyDirectory = new File(baseLuceneFolderPath, relativeLuceneTaxonomiesFolderPath);
		taxonomyDirectory.mkdirs();
		System.out.println("-----------------------");
		System.out.println(baseLuceneFolderPath);
		System.out.println(indexDirectory.getAbsolutePath());
		System.out.println("-----------------------");
	}

	@Override
	public Analyzer getAnalyzer() {
		return analyser;
	}

	@Override
	public LuceneWriters getWriters() {
		try {
			final Map<String, Analyzer> analyzerPerField = new HashMap<String, Analyzer>();
			analyzerPerField.put(DocFields.ID, analyser);
			analyzerPerField.put(DocFields.TITLE, analyser);
			analyzerPerField.put(DocFields.TEXT, analyser);
			analyzerPerField.put(DocFields.AUTHOR, analyser);
			final PerFieldAnalyzerWrapper wrapper = new PerFieldAnalyzerWrapper(analyser, analyzerPerField);
			final IndexWriterConfig config = new IndexWriterConfig(LuceneConstants.LUCENE_VERSION, wrapper);

			final IndexWriter indexWriter = new IndexWriter(FSDirectory.open(indexDirectory), config);
			final TaxonomyWriter taxonomyWriter = new DirectoryTaxonomyWriter(FSDirectory.open(taxonomyDirectory));
			return new LuceneWriters(indexWriter, taxonomyWriter);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void closeWriters(LuceneWriters writers) {
		try {
			writers.getIndexWriter().close();
		} catch (Exception e) {
			logger.error("Error closing lucene indexWriter", e);
		}
		try {
			writers.getTaxonomyWriter().close();
		} catch (Exception e) {
			logger.error("Error closing lucene taxonomyWriter", e);
		}

	}

	@Override
	public void commitWriters(LuceneWriters writers) {
		try {
			writers.getIndexWriter().commit();
		} catch (Exception e) {
			logger.error("Error committing lucene indexWriter", e);
		}
		try {
			writers.getTaxonomyWriter().commit();
		} catch (Exception e) {
			logger.error("Error committing lucene taxonomyWriter", e);
		}
	}

	@Override
	public SearcherManager getSearcherManager() {
		if(searcherManager==null) {
			try {
				searcherManager = new SearcherManager(FSDirectory.open(indexDirectory), new SearcherFactory());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return searcherManager;
	}

	@Override
	public void release(IndexSearcher searcher) {
		try {
			if (searcher!=null) {
				searcherManager.release(searcher);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private TaxonomyReader taxonomyReader;
	@Override
	public TaxonomyReader getTaxonomyReader() {
		try {
			if (taxonomyReader==null) {
				taxonomyReader = new DirectoryTaxonomyReader(FSDirectory.open(taxonomyDirectory));
			}
			TaxonomyReader newTaxonomyReader = TaxonomyReader.openIfChanged(taxonomyReader);
			taxonomyReader = (newTaxonomyReader!=null) ? newTaxonomyReader : taxonomyReader;
			return  taxonomyReader;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
