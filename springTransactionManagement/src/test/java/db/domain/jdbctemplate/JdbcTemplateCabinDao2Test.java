package db.domain.jdbctemplate;

import db.domain.Cabin;
import db.domain.ICabinDao;
import test.BaseTest;
import transaction.Transaction;

public class JdbcTemplateCabinDao2Test extends BaseTest {

	private ICabinDao cabinDao;

	protected void setUp() throws Exception {
		super.setUp();
		cabinDao = application.getBean("jdbcTemplateCabinDao", ICabinDao.class);
		assertNotNull(cabinDao);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testTransactionOK() throws Exception {
		int maxId = cabinDao.getMaxId();
		System.out.println("max id:" + maxId);
		
		try {
			application.getBean("transaction1", Transaction.class).goTransactionOK(maxId);
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	
		
		assertTrue(cabinDao.existCabin(maxId+1));
		assertTrue(cabinDao.existCabin(maxId+2));
	}
	
	public void testTransactionNOK() throws Exception {
		int maxId = cabinDao.getMaxId();
		System.out.println("max id:" + maxId);
		
		try {
			application.getBean("transaction1", Transaction.class).goTransactionNOK(maxId);
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		assertFalse(cabinDao.existCabin(maxId+1));
		assertFalse(cabinDao.existCabin(maxId+2));
	}
	
	public void createCabin(int maxId, String name) throws Exception {
		Cabin newCabin = new Cabin();
		newCabin.setBedCount(maxId);
		newCabin.setDeckLevel(maxId);
		newCabin.setId(maxId);
		newCabin.setName(name);
		newCabin.setShipId(maxId);
		cabinDao.insert(newCabin);
	}
	
}
