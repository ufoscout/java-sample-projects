package transaction;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface Transaction {

	void goTransactionOK(int id);
	
	void goTransactionNOK(int id);
}
