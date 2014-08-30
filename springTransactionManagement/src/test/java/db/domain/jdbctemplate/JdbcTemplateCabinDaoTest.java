package db.domain.jdbctemplate;

import java.util.Date;

import db.domain.Cabin;
import db.domain.ICabinDao;
import spring.Application;
import test.BaseTest;

public class JdbcTemplateCabinDaoTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSimpleJdbcTemplate() throws Exception {
		Application application = new Application();
		ICabinDao cabinDao = application.getBean("jdbcTemplateCabinDao", ICabinDao.class);
		
		assertNotNull(cabinDao);
		int maxId = cabinDao.getMaxId();
		System.out.println("max id:" + maxId);
		
		String name = "test-" + new Date().getTime();
		Cabin newCabin = new Cabin();
		newCabin.setBedCount(maxId+1);
		newCabin.setDeckLevel(maxId+1);
		newCabin.setId(maxId+1);
		newCabin.setName(name);
		newCabin.setShipId(maxId+1);
		
		cabinDao.insert(newCabin);
		
		StringBuffer maxId1 = new StringBuffer();
		GetMaxIdThread maxIdThread = new GetMaxIdThread(maxId1,cabinDao);
		maxIdThread.start();
		maxIdThread.join();
		
		assertEquals( (maxId+1)+"", maxId1.toString());
		
		cabinDao.delete(maxId+1);
		
		StringBuffer maxId2 = new StringBuffer();
		maxIdThread = new GetMaxIdThread(maxId2,cabinDao);
		maxIdThread.start();
		maxIdThread.join();
		
		assertEquals( maxId+"", maxId2.toString());
		
	}
}
