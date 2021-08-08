package com.citybank.groupl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citybank.groupl.DAO.AddressDao;
import com.citybank.groupl.DAO.LoginDao;
import com.citybank.groupl.DAO.UserDao;
import com.citybank.groupl.bean.Address;
import com.citybank.groupl.bean.Login;
import com.citybank.groupl.bean.User;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary This is an class that implements user service. It has userDao,
 *          address dao and login dao references. It also contains getters and
 *          setter for the same.It contains methods for registering a new user,
 *          a valid user login, save address of user, validate that user and
 *          process login and get all the list of users.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - This is
 * an class that implements user service. It has userDao, address dao and login
 * dao references. It also contains getters and setter for the same.It contains
 * methods for registering a new user, a valid user login, save address of user,
 * validate that user and process login and get all the list of users.
 */

@Service
public class UserServiceImpl implements UserService {

	public UserDao userDao;

	public AddressDao addressDao;

	public LoginDao loginDao;

	// getter for user dao
	public UserDao getUserDao() {
		return userDao;
	}

	// getter for address dao
	public AddressDao getAddressDao() {
		return addressDao;
	}

	// getter for login dao
	public LoginDao getLoginDao() {
		return loginDao;
	}

	// setter for user dao
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// setter for address dao
	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	// setter for login dao
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public int register(User user) {
		// method call to save an
		// entry in database login table
		Login login = saveLogin(user.getLogin());
		// method call to save address
		// of user in database address table
		Address address = saveAddress(user.getAddress());
		// checking if login and address details are
		// successfully saved
		if (login != null && address != null) {
			// saving user address
			user.setAddress(address);
			// saving user login
			user.setLogin(login);
			// registering user
			return userDao.register(user);
		} else {
			return 0;
		}
	}

	public User validateUser(Login login) {
		// checking user login is valid
		if (isValidLogin(login)) {
			// getting user details from login
			return userDao.getUserByLogin(login);
		}
		return null;
	}

	public Address saveAddress(Address address) {
		// saving address details
		return addressDao.saveAddress(address);
	}

	public Login saveLogin(Login login) {
		// saving user login details
		return loginDao.saveLogin(login);
	}

	public boolean isValidLogin(Login login) {
		// checking login by user is valid or not
		return loginDao.isValidLogin(login);
	}

	public List<User> getAllUsers() {
		// getting list of users
		return userDao.getAllUsers();
	}

}