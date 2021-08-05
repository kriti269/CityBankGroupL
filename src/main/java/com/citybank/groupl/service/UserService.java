package com.citybank.groupl.service;

import java.util.List;

import com.citybank.groupl.bean.Address;
import com.citybank.groupl.bean.Login;
import com.citybank.groupl.bean.User;

/**
 * @since 11-07-2021
 * @author Group L - Kriti, Jatin, Varun, Sonia
 * @serial 1.0
 * @summary Interface that declares two methods to register a user and validate
 *          user login.
 * 
 */

/*
 * Date - 11-07-2021 Author - Kriti, Jatin, Varun, Sonia Description - Interface
 * that declares two methods to register a user and validate user login.
 * 
 */

public interface UserService {
	int register(User user);

	boolean isValidLogin(Login login);
	
	Address saveAddress(Address address);
	
	Login saveLogin(Login login);
	
	User validateUser(Login login);
	
	List<User> getAllUsers();
}
