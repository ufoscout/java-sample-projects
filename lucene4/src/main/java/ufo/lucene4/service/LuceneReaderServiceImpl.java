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
import org.apache.lucene.facet.params.FacetIndexingParams;
import org.apache.lucene.facet.params.FacetSearchParams;
import org.apache.lucene.facet.search.CountFacetRequest;
import org.apache.lucene.facet.search.DrillDownQuery;
import org.apache.lucene.facet.search.DrillSideways;
import org.apache.lucene.facet.search.DrillSideways.DrillSidewaysResult;
import org.apache.lucene.facet.search.FacetRequest;
import org.apache.lucene.facet.search.FacetResult;
import org.apache.lucene.facet.search.FacetResultNode;
import org.apache.lucene.facet.taxonomy.CategoryPath;
import org.apache.lucene.facet.taxonomy.TaxonomyReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.TotalHitCountCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ufo.lucene4.domain.Genre;

/**
 *
 * @author ufo
 */
@Service
public class LuceneReaderServiceImpl implements LuceneReaderService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private LuceneService luceneService;

	@Override
	public int getIndexSize() {
		final SearcherManager searcherManager = luceneService.getSearcherManager();
		IndexSearcher searcher = null;
		try {
			searcher = searcherManager.acquire();
			final IndexReader indexReader = searcher.getIndexReader();
			return indexReader.numDocs();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			luceneService.release(searcher);
		}
	}

	@Override
	public void refreshReaders() {
		try {
			luceneService.getSearcherManager().maybeRefresh();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int count(final SearchQuery searchQuery) {
		IndexSearcher searcher = null;
		try {
			final TotalHitCountCollector collector = new TotalHitCountCollector();
			searcher = luceneService.getSearcherManager().acquire();
			searcher.search(buildQuery(searchQuery).drillDownQuery, collector);
			return collector.getTotalHits();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		} finally {
			luceneService.release(searcher);
		}
	}

	@Override
	public SearchResult search(final SearchQuery searchQuery, final int maxResults, final int offset) {
		SearchResult searchResult = new SearchResult();
		IndexSearcher searcher = null;
		try {
			searcher = luceneService.getSearcherManager().acquire();
			TaxonomyReader taxonomyReader = luceneService.getTaxonomyReader();

			BuildQueryResult buildQueryResult = buildQuery(searchQuery);

			int numHits = maxResults + offset;
			final TopFieldCollector topFieldCollector = buildTopFieldCollector(numHits);

			final DrillSideways drillSideways = new DrillSideways(searcher, taxonomyReader);
			DrillSidewaysResult drillSidewaysResult = drillSideways.search(null, buildQueryResult.drillDownQuery, 500, buildQueryResult.facetSearchParams);

			for (FacetResult facetResult : drillSidewaysResult.facetResults) {
				FacetResultNode facetResultNode = facetResult.getFacetResultNode();
				logger.debug("From DrillSideways -> Found category [{}] value [{}]", facetResultNode.label.components[0], facetResultNode.value);

				for (FacetResultNode subFacetResultNode : facetResultNode.subResults) {
					logger.debug("From DrillSideways -> Found category [{}][{}] value [{}]", new Object[]{subFacetResultNode.label.components[0], subFacetResultNode.label.components[1], subFacetResultNode.value});
					String facetType = subFacetResultNode.label.components[0];
					if (DocFields.GENRE.equals(facetType)) {
						logger.debug("Adding genre value to the facet results");
						searchResult.genreFacet.put( Genre.valueOf(subFacetResultNode.label.components[1]), Integer.valueOf( (int) subFacetResultNode.value ));
					} else if (DocFields.AUTHOR.equals(facetType)) {
						logger.debug("Adding author value to the facet results");
						searchResult.authorFacet.put(subFacetResultNode.label.components[1], Integer.valueOf( (int) subFacetResultNode.value ));
					}

				}

			}

			searcher.search(buildQueryResult.drillDownQuery, topFieldCollector);

			searchResult.totalResultsCount = topFieldCollector.getTotalHits();

			for (final ScoreDoc score : topFieldCollector.topDocs(offset, maxResults).scoreDocs) {
				final Document document = searcher.doc(score.doc);
				searchResult.bookIds.add(Long.valueOf(document.get(DocFields.ID)));
			}

			return searchResult;
		} catch (final IOException e) {
			throw new RuntimeException(e);
		} finally {
			luceneService.release(searcher);
		}
	}

	private BuildQueryResult buildQuery(final SearchQuery searchQuery) throws IOException {

		Query query = buildKeywordQuery(searchQuery);

		//Drilldown categories
		final FacetIndexingParams facetIndexingParams = new FacetIndexingParams();
		final List<FacetRequest> facetRequests = new ArrayList<>();
		facetRequests.add(new CountFacetRequest(new CategoryPath(DocFields.AUTHOR), Integer.MAX_VALUE));
		facetRequests.add(new CountFacetRequest(new CategoryPath(DocFields.GENRE), Integer.MAX_VALUE));
		FacetSearchParams facetSearchParams = new FacetSearchParams(facetIndexingParams, facetRequests);
		DrillDownQuery drillDownQuery = new DrillDownQuery(facetSearchParams.indexingParams, query);

		for (Genre genre : searchQuery.genres) {
			logger.debug("DrillDown to genre [{}]", genre.name());
			drillDownQuery.add(new CategoryPath(DocFields.GENRE, genre.name()));
		}

		for (String author : searchQuery.authors) {
			logger.debug("DrillDown to author [{}]", author);
			drillDownQuery.add(new CategoryPath(DocFields.AUTHOR, author));
		}

		return new BuildQueryResult(drillDownQuery, facetSearchParams);

	}

	private Query buildKeywordQuery(final SearchQuery searchQuery) {
		if ((searchQuery.keywords == null) || searchQuery.keywords.trim().isEmpty()) {
			return new MatchAllDocsQuery();
		}

		final Occur orOperator = BooleanClause.Occur.SHOULD;
		final Occur andOperator = BooleanClause.Occur.MUST;

		final BooleanQuery query = new BooleanQuery();

		for (String keyword : searchQuery.keywords.split(" ")) {
			if (!keyword.isEmpty()) {
				logger.info("Search for keyword [{}]", keyword);

				final BooleanQuery keywordQuery = new BooleanQuery();

				int fuzzyFactor = 1;
				Query authorQuery = new FuzzyQuery(new Term(DocFields.AUTHOR, keyword), fuzzyFactor);
				authorQuery.setBoost(1.0f);
				keywordQuery.add(authorQuery, orOperator);

				Query titleQuery = new FuzzyQuery(new Term(DocFields.TITLE, keyword), fuzzyFactor);
				titleQuery.setBoost(1.0f);
				keywordQuery.add(titleQuery, orOperator);

				Query textQuery = new TermQuery(new Term(DocFields.TEXT, keyword));
				textQuery.setBoost(0.5f);
				keywordQuery.add(textQuery, orOperator);

				query.add(keywordQuery, andOperator);
			}
		}
		return query;
	}

	private TopFieldCollector buildTopFieldCollector(int numHits) throws IOException {
		boolean fillFields = true;
		boolean trackDocScores = false;
		boolean trackMaxScore = false;
		boolean docsScoredInOrder = false;
		return TopFieldCollector.create(Sort.RELEVANCE, numHits, fillFields, trackDocScores, trackMaxScore, docsScoredInOrder);
	}


	private class BuildQueryResult {

		private final DrillDownQuery drillDownQuery;
		private final FacetSearchParams facetSearchParams;

		public BuildQueryResult(DrillDownQuery drillDownQuery, FacetSearchParams facetSearchParams) {
			this.drillDownQuery = drillDownQuery;
			this.facetSearchParams = facetSearchParams;
		}

	}
}
