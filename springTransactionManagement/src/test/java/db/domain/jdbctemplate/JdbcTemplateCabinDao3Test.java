package db.domain.jdbctemplate;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import db.domain.Cabin;
import db.domain.ICabinDao;
import test.BaseTest;
import transaction.Transaction1;

public class JdbcTemplateCabinDao3Test extends BaseTest {

	private ICabinDao cabinDao;

	protected void setUp() throws Exception {
		super.setUp();
		cabinDao = new JdbcTemplateCabinDao();
		((JdbcTemplateCabinDao) cabinDao ).setDataSource( application.getDatasource() );
		assertNotNull(cabinDao);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testTransactionOK() throws Exception {
		int maxId = cabinDao.getMaxId();
		System.out.println("max id:" + maxId);
		
		PlatformTransactionManager platformTransactionManager = application.getBean("txManager", PlatformTransactionManager.class);
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
		
			Transaction1 tran = new Transaction1();
			tran.setCabinDao(cabinDao);
			tran.goTransactionOK(maxId);

			platformTransactionManager.commit(status);
		
		assertTrue(cabinDao.existCabin(maxId+1));
		assertTrue(cabinDao.existCabin(maxId+2));
	}
	
	public void testTransactionNOK() throws Exception {
		int maxId = cabinDao.getMaxId();
		System.out.println("max id:" + maxId);
		
		try {
			Transaction1 tran = new Transaction1();
			tran.setCabinDao(cabinDao);
			tran.goTransactionNOK(maxId);
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
