package ufo.lucene4.service;

import org.apache.lucene.facet.taxonomy.TaxonomyWriter;
import org.apache.lucene.index.IndexWriter;

public class LuceneWriters {

    private final IndexWriter indexWriter;

    private final TaxonomyWriter taxonomyWriter;

    public LuceneWriters(IndexWriter indexWriter, final TaxonomyWriter taxonomyWriter) {
        this.indexWriter = indexWriter;
        this.taxonomyWriter = taxonomyWriter;
    }

    public IndexWriter getIndexWriter() {
        return indexWriter;
    }

    public TaxonomyWriter getTaxonomyWriter() {
        return taxonomyWriter;
    }
}
