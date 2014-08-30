package ufo.lucene4.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import ufo.lucene4.Lucene4BaseTest;
import ufo.lucene4.domain.Book;
import ufo.lucene4.domain.Genre;

public class LuceneServiceTest extends Lucene4BaseTest {

	@Resource
	private LuceneWriterService luceneWriterService;
	@Resource
	private LuceneReaderService luceneReaderService;

	@Before
	public void setUp() {
		luceneWriterService.deleteAll();
	}

	@Test
	public void testAddBooksToIndex() {

		assertEquals(0, luceneReaderService.getIndexSize());

		List<Book> books = new ArrayList<>();
		books.add(new Book(0l, "title", "text", "author", Genre.ROMANCE));
		books.add(new Book(1l, "title", "text", "author", Genre.ROMANCE));
		books.add(new Book(2l, "title", "text", "author", Genre.ROMANCE));
		books.add(new Book(3l, "title", "text", "author", Genre.ROMANCE));
		books.add(new Book(4l, "title", "text", "author", Genre.ROMANCE));
		luceneWriterService.addToIndex(books);
		luceneWriterService.addToIndex(books);

		luceneReaderService.refreshReaders();
		assertEquals(books.size(), luceneReaderService.getIndexSize());

		luceneWriterService.deleteAll();
		luceneReaderService.refreshReaders();
		assertEquals(0, luceneReaderService.getIndexSize());
	}

	@Test
	public void testSearchForBooks() {

		List<Book> books = new ArrayList<>();
		books.add(new Book(0l, "The Adventures of Huckleberry Finn", "", "Mark Twain", Genre.ADVENTURE));
		books.add(new Book(1l, "The Adventures of Tom Sawyer", "", "Mark Twain", Genre.ADVENTURE));
		books.add(new Book(2l, "The Great Gatsby", "", "F. Scott Fitzgerald", Genre.ROMANCE));
		books.add(new Book(3l, "The Curious Case of Benjamin Button", "", "F. Scott Fitzgerald", Genre.NOVEL));
		books.add(new Book(4l, "Tender Is the Night", "", "F. Scott Fitzgerald", Genre.ROMANCE));
		books.add(new Book(5l, "Infinite Jest", "", "David Foster Wallace", Genre.NOVEL));
		books.add(new Book(6l, "This is water", "", "David Foster Wallace", Genre.ROMANCE));
		books.add(new Book(7l, "The Broom of the System", "", "David Foster Wallace", Genre.NOVEL));
		books.add(new Book(8l, "The pale king", "", "David Foster Wallace", Genre.NOVEL));

		luceneWriterService.addToIndex(books);
		luceneReaderService.refreshReaders();

		SearchQuery searchQuery = new SearchQuery();
		searchQuery.keywords = "Wallace";
		int count = luceneReaderService.count(searchQuery);

		getLogger().info("Found [{}] results", count);
		assertTrue(count > 0);

		SearchResult searchResults = luceneReaderService.search(searchQuery, 1000, 0);
		assertEquals(count, searchResults.totalResultsCount);
		assertEquals(count, searchResults.bookIds.size());

		assertTrue(searchResults.bookIds.contains(6l));
		assertTrue(searchResults.bookIds.contains(7l));
		assertTrue(searchResults.bookIds.contains(8l));

	}


	@Test
	public void testSearchWithFacetsDrillSideways() {

		List<Book> books = new ArrayList<>();
		books.add(new Book(0l, "The Adventures of Huckleberry Finn", "", "Mark Twain", Genre.NOVEL));
		books.add(new Book(1l, "The Adventures of Tom Sawyer", "", "Mark Twain", Genre.ADVENTURE));
		books.add(new Book(2l, "The Great Gatsby", "", "F. Scott Fitzgerald", Genre.ROMANCE));
		books.add(new Book(3l, "The Curious Case of Benjamin Button", "", "F. Scott Fitzgerald", Genre.ROMANCE));
		books.add(new Book(4l, "Tender Is the Night", "", "F. Scott Fitzgerald", Genre.ROMANCE));
		books.add(new Book(5l, "Infinite Jest", "", "David Foster Wallace", Genre.NOVEL));
		books.add(new Book(6l, "This is water", "", "David Foster Wallace", Genre.ROMANCE));
		books.add(new Book(7l, "The Broom of the System", "", "David Foster Wallace", Genre.NOVEL));
		books.add(new Book(8l, "The pale king", "", "David Foster Wallace", Genre.NOVEL));

		luceneWriterService.addToIndex(books);
		luceneReaderService.refreshReaders();

		SearchQuery searchQuery = new SearchQuery();
		searchQuery.genres.add(Genre.NOVEL);
		searchQuery.authors.add("David Foster Wallace");
		int count = luceneReaderService.count(searchQuery);

		SearchResult searchResults = luceneReaderService.search(searchQuery, 1000, 0);
		assertEquals(count, searchResults.totalResultsCount);
		assertEquals(count, searchResults.bookIds.size());

		assertFalse(searchResults.bookIds.contains(6l));
		assertTrue(searchResults.bookIds.contains(7l));
		assertTrue(searchResults.bookIds.contains(8l));

		assertTrue( searchResults.authorFacet.containsKey("David Foster Wallace") );
		assertTrue( searchResults.authorFacet.get("David Foster Wallace").intValue() > 0 );
		assertTrue( searchResults.authorFacet.containsKey("Mark Twain") );
		assertTrue( searchResults.authorFacet.get("Mark Twain").intValue() > 0 );

		assertTrue( searchResults.genreFacet.containsKey(Genre.NOVEL) );
		assertTrue( searchResults.genreFacet.get(Genre.NOVEL).intValue() > 0 );
		assertTrue( searchResults.genreFacet.containsKey(Genre.ROMANCE) );
		assertTrue( searchResults.genreFacet.get(Genre.ROMANCE).intValue() > 0 );

	}

}
