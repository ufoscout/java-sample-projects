package springapp;

import junit.framework.TestCase;


/**
 * 
 * @author Francesco Cina'
 *
 * 23/ott/2010
 */
public abstract class BaseTest extends TestCase {

    
    public BaseTest() {
    }

    public BaseTest(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        System.out.println("===================================================================");
        System.out.println("INIZIO TEST " + getName());
        System.out.println("===================================================================");

    }



	protected void tearDown() throws Exception {
        super.tearDown();

        System.out.println("===================================================================");
        System.out.println("FINE TEST " + getName());
        System.out.println("===================================================================");
    }

}