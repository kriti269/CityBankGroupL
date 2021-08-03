package com.citybank.groupl.controller;


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
 * @since 11-07-2021
 * @author Group L - Kriti, Jatin, Varun, Sonia
 * @serial 1.0
 * @summary Controller class for user registration URLs. Displays registration
 *          page and registers a user.
 * 
 */

/*
 * Date - 11-07-2021 Author - Kriti, Jatin, Varun, Sonia Description -
 * Controller class for user registration URLs. Displays registration page and
 * registers a user.
 */

@Controller
public class RegisterControl {
	@Autowired
	public UserService userService;

	/**
	 * This method handles GET request for register page and returns the view
	 * register.jsp with new object model User.
	 * 
	 * @param HttpServletRequest  This is the parameter that handles request
	 *                            information
	 * @param HttpServletResponse This is the parameter used to modify and send
	 *                            appropriate response
	 * @return ModelAndView This returns the User model inside register view
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		// set view name
		ModelAndView mav = new ModelAndView("register");
		// set model
		mav.addObject("user", new User());

		return mav;
	}

	/**
	 * This method handles POST request to register a user and returns the view
	 * welcome.jsp with user details.
	 * 
	 * @param HttpServletRequest  This is the parameter that handles request
	 *                            information
	 * @param HttpServletResponse This is the parameter used to modify and send
	 *                            appropriate response
	 * @return ModelAndView This returns the user's first name inside welcome view
	 */
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) {
		// calls register method of UserService class to create new user in the
		// database.
		ModelAndView mav = null;
		int rowsInserted = userService.register(user);
		if(rowsInserted==0) {
			// set view as register.jsp
			mav = new ModelAndView("register");
			// set register error message
			mav.addObject("message", "Unable to register! Please Try Again Later!!");
		}
		else {
			// set view welcome.jsp
			mav = new ModelAndView("welcome");
			// set user's first name as object
			mav.addObject("firstname", user.getFirstName());
		}
		
		return mav;
	}
}
