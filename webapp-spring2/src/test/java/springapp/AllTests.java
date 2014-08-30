package springapp;


import springapp.domain.ProductTest;
import springapp.repository.JdbcProductDaoTests;
import springapp.service.SimpleProductManagerTest;
import springapp.web.InventoryControllerTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author Francesco Cina'
 *
 * 20/nov/2009
 */
public class AllTests extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AllTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite("TEST SUITE FOR Springapp");
        //$JUnit-BEGIN$
        
        suite.addTestSuite( InventoryControllerTest.class );
        suite.addTestSuite( ProductTest.class );
        suite.addTestSuite( SimpleProductManagerTest.class );
        suite.addTestSuite( JdbcProductDaoTests.class );
        
        
        return suite;
    }
}
