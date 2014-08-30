package db.domain;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


public interface ICabinDao {

	@Transactional
	void insert(Cabin cabin);
	
	@Transactional
	void update(Cabin cabin);
	
	@Transactional
	void delete(int id);
	
	@Transactional(readOnly = true)
	int getMaxId();
	
	@Transactional(readOnly = true)
	Cabin getById(int id); 
	
	@Transactional(readOnly = true)
	List<Cabin> getAllCabin();  

	@Transactional(readOnly = true)
	boolean existCabin(int id);
}
