package bcm.service;

import bcm.domain.BcmUser;

public interface AuthService {

	int add(BcmUser user);
	
	int identify(String email);

	BcmUser getMember(BcmUser user);
}
