package spike;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import junit.framework.TestCase;

public class graphTest extends TestCase {

	GraphDatabaseService graphDb;
	
	protected void setUp() throws Exception {
		super.setUp();
		graphDb = new EmbeddedGraphDatabase( "target/graphdb" );
	}

	protected void tearDown() throws Exception {
		graphDb.shutdown();
		super.tearDown();
	}
	
	public void testGraph() throws Exception {
		Transaction tx = graphDb.beginTx();
		try
		{
		   // all Neo4j operations that work with the graph
		   // ...
		   tx.success();
		}
		finally
		{
		   tx.finish();
		}
	}

}
