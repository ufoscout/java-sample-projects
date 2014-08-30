package transaction;

import java.util.Date;

import db.domain.Cabin;
import db.domain.ICabinDao;

public class Transaction1 implements Transaction {
	
	private ICabinDao cabinDao;
	
	public void goTransactionOK(int id) {
		System.out.println("BEGIN 1");
		createCabin(id+1, "testOK-" + new Date().getTime());
		System.out.println("BEGIN 2 - END 1");
		createCabin(id+2, "testOK-" + new Date().getTime());
		System.out.println("END 2");
	}
	
	public void goTransactionNOK(int id) {
		System.out.println("BEGIN 1");
		createCabin(id+1, "testOK-" + new Date().getTime());
		System.out.println("BEGIN 2 - END 1");
		createCabin(id+2, "testOK-" + new Date().getTime());
		System.out.println("BEGIN 3 - END 2");
		createCabin(id+2, "testOK-" + new Date().getTime());
		System.out.println("END 3");
	}
	
	public void createCabin(int maxId, String name) {
		Cabin newCabin = new Cabin();
		newCabin.setBedCount(maxId);
		newCabin.setDeckLevel(maxId);
		newCabin.setId(maxId);
		newCabin.setName(name);
		newCabin.setShipId(maxId);
		getCabinDao().insert(newCabin);
	}

	public void setCabinDao(ICabinDao cabinDao) {
		this.cabinDao = cabinDao;
	}

	public ICabinDao getCabinDao() {
		return cabinDao;
	}
	
}
