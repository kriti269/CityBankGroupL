package com.citybank.groupl.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.citybank.groupl.bean.User;
import com.citybank.groupl.service.UserService;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary This is the user controller for handling user related requests. The
 *          user service is autowired. It contains setters for for user service.
 *          The methods are addUser for new user registration done by admin and
 *          viewAllUsers to get the list of users for administrator.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - This is
 * the user controller for handling user related requests. The user service is
 * autowired. It contains setters for for user service. The methods are addUser
 * for new user registration done by admin and viewAllUsers to get the list of
 * users for administrator.
 */

@Controller
public class UserControl {
	@Autowired
	UserService userService;

	// setter for user service
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) {
		// calls register method of UserService
		// class to create new user in the database.
		ModelAndView mav = null;
		// calling register method of user service
		int rowsInserted = userService.register(user);
		if (rowsInserted == 0) {
			// set view as default welcome page
			mav = new ModelAndView("welcome");
			// set register error message
			mav.addObject("message", "Unable to register! Please Try Again Later!!");
		} else {
			// setting redirect to add account page once
			// user is successfully added.
			mav = new ModelAndView("redirect:/addAccount");
		}

		return mav;
	}

	@RequestMapping(value = "/viewAllUsers", method = RequestMethod.GET)
	public ModelAndView viewAllUsers(HttpServletRequest request, HttpServletResponse response) {
		// getting the user details from session
		boolean isAdmin = (Boolean) request.getSession().getAttribute("is_admin");
		ModelAndView mav = null;
		// checking that user is admin or not
		if (isAdmin) {
			// getting list of users from user service
			List<User> listOfAllUsers = userService.getAllUsers();
			// setting view name
			mav = new ModelAndView("users");
			// setting view model attribute
			mav.addObject("users_list", listOfAllUsers);
		}

		return mav;
	}
}
