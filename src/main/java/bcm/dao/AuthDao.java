package bcm.dao;

import bcm.domain.BcmUser;

public interface AuthDao {

	int insert(BcmUser user);

	int identifyEamil(String email);

	BcmUser findByEmailAndPassword(BcmUser user);
	
}
