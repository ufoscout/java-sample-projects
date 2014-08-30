package springapp.domain;

import springapp.BaseTest;

public class ProductTest extends BaseTest {

    private Product product;

    protected void setUp() throws Exception {
    	super.setUp();
        product = new Product();
    }
    
	protected void tearDown() throws Exception {
		super.tearDown();
	}

    public void testSetAndGetDescription() {
        String testDescription = "aDescription";
        assertNull(product.getDescription());
        product.setDescription(testDescription);
        assertEquals(testDescription, product.getDescription());
    }

    public void testSetAndGetPrice() {
        double testPrice = 100.00;
        product.setPrice(testPrice);
        assertEquals(testPrice, product.getPrice(), 0);
    }

}
