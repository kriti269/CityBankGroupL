package com.citybank.groupl.service;

import java.util.List;

import com.citybank.groupl.bean.Address;
import com.citybank.groupl.bean.Login;
import com.citybank.groupl.bean.User;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary This is an Interface that declares methods to register a user, check
 *          a valid user login, save address of user, validate that user and
 *          process login and get all the list of users.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - This is
 * an Interface that declares methods to register a user, check a valid user
 * login, save address of user, validate that user and process login and get all
 * the list of users.
 */

public interface UserService {
	int register(User user);

	boolean isValidLogin(Login login);
	
	Address saveAddress(Address address);
	
	Login saveLogin(Login login);
	
	User validateUser(Login login);
	
	List<User> getAllUsers();
}
