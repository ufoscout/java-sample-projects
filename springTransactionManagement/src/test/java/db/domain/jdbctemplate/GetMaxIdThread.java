package db.domain.jdbctemplate;

import db.domain.ICabinDao;

public class GetMaxIdThread extends Thread {

	private final StringBuffer maxId;
	private final ICabinDao cabinDao;

	GetMaxIdThread(StringBuffer maxId, ICabinDao cabinDao) {
		this.maxId = maxId;
		this.cabinDao = cabinDao;
	}
	
	public void run() {
		maxId.append( cabinDao.getMaxId() );
	}

}
