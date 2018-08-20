package bcm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcm.dao.AuthDao;
import bcm.domain.BcmUser;
import bcm.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired AuthDao authDao;
	
	@Override
	public int add(BcmUser user) {
		return authDao.insert(user);
	}
	
	@Override
	public int identify(String email) {
		return authDao.identifyEamil(email);
	}
	
	@Override
	public BcmUser getMember(BcmUser user) {
		return authDao.findByEmailAndPassword(user);
	}
}
