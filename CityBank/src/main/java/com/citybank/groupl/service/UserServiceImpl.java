package com.citybank.groupl.service;

import org.springframework.stereotype.Service;

import com.citybank.groupl.DAO.AddressDao;
import com.citybank.groupl.DAO.LoginDao;
import com.citybank.groupl.DAO.UserDao;
import com.citybank.groupl.bean.Address;
import com.citybank.groupl.bean.Login;
import com.citybank.groupl.bean.User;

/**
 * @since 11-07-2021
 * @author Group L - Kriti, Jatin, Varun, Sonia
 * @serial 1.0
 * @summary Service class that implements UserService methods. It makes use of
 *          UserDao class to register a user and validate user login.
 * 
 */

/*
 * Date - 11-07-2021 Author - Kriti, Jatin, Varun, Sonia Description - Service
 * class that implements UserService methods. It makes use of UserDao class to
 * register a user and validate user login.
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	
	public UserDao userDao;
	

	public AddressDao addressDao;
	
	
	public LoginDao loginDao;
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public AddressDao getAddressDao() {
		return addressDao;
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * This method calls the register method from UserDao class to create a new user
	 * and returns number of rows affected.
	 * 
	 * @param User This is the parameter that represents User model to be
	 *             registered.
	 * @return int This returns the number of rows affected as 0 or 1.
	 */
	public int register(User user) {
		Login login = saveLogin(user.getLogin());
		Address address = saveAddress(user.getAddress());
		if(login!=null && address!=null) {
			user.setAddress(address);
			user.setLogin(login);
			return userDao.register(user);
		}
		else {
			return 0;
		}
	}

	/**
	 * This method calls the validateUser method from UserDao class to validate user
	 * login and returns corresponding User model.
	 * 
	 * @param Login This is the parameter that represents Login model to be
	 *              validated.
	 * @return User This returns the corresponding User model.
	 */
	public User validateUser(Login login) {
		if(isValidLogin(login)) {
			return userDao.getUserByLogin(login);
		}
		return null;
	}

	public Address saveAddress(Address address) {
		// TODO Auto-generated method stub
		return addressDao.saveAddress(address);
	}

	public Login saveLogin(Login login) {
		// TODO Auto-generated method stub
		return loginDao.saveLogin(login);
	}

	public boolean isValidLogin(Login login) {
		// TODO Auto-generated method stub
		return loginDao.isValidLogin(login);
	}

}