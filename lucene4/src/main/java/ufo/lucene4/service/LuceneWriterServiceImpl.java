/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufo.lucene4.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.facet.index.FacetFields;
import org.apache.lucene.facet.taxonomy.CategoryPath;
import org.apache.lucene.search.NumericRangeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ufo.lucene4.domain.Book;

/**
 *
 * @author ufo
 */
@Service
public class LuceneWriterServiceImpl implements LuceneWriterService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private LuceneService luceneService;

    @Override
    public void addToIndex(List<Book> books) {
    	LuceneWriters writers = luceneService.getWriters();
    	final FacetFields categoryDocBuilder = new FacetFields(writers.getTaxonomyWriter());
        try {
        	for(Book book : books) {
        		logger.info("Indexing book with ID [{}]", book.id);
        		writers.getIndexWriter().deleteDocuments(NumericRangeQuery.newLongRange(DocFields.ID, book.id, book.id, true, true));

        		Document doc = new Document();
        		final List<CategoryPath> categories = new ArrayList<CategoryPath>();

        		doc.add(new LongField(DocFields.ID, book.id, Store.YES));
        		doc.add(new TextField(DocFields.TITLE, book.title, Store.YES));
        		doc.add(new TextField(DocFields.TEXT, book.text, Store.NO));

        		doc.add(new TextField(DocFields.AUTHOR, book.author, Store.YES));
        		categories.add(new CategoryPath(DocFields.AUTHOR, book.author));

        		doc.add(new StringField(DocFields.GENRE, book.genre.name(), Store.NO));
        		categories.add(new CategoryPath(DocFields.GENRE, book.genre.name()));

        		categoryDocBuilder.addFields(doc, categories);
        		writers.getIndexWriter().addDocument(doc);

        	}
        } catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
        	luceneService.closeWriters(writers);
        }
    }

    @Override
    public void deleteAll() {
    	LuceneWriters writers = luceneService.getWriters();
        try {
        	writers.getIndexWriter().deleteAll();
        } catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
        	luceneService.closeWriters(writers);
        }
    }

}
