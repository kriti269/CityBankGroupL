package com.citybank.groupl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.citybank.groupl.bean.Login;
import com.citybank.groupl.bean.User;
import com.citybank.groupl.service.UserService;

/**
 * @since 11-07-2021
 * @author Group L - Kriti, Jatin, Varun, Sonia
 * @serial 1.0
 * @summary Controller class for Login URLs. It handles login page request and
 *          facilitates user login.
 */

/*
 * Date - 11-07-2021 Author - Kriti, Jatin, Varun, Sonia Description -
 * Controller class for Login URLs. It handles login page request and
 * facilitates user login.
 */

@Controller
public class LoginControl {

	@Autowired
	UserService userService;
	
	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * This method handles GET request for login page and returns the view login.jsp
	 * with new object model Login.
	 * 
	 * @param HttpServletRequest  This is the parameter that handles request
	 *                            information
	 * @param HttpServletResponse This is the parameter used to modify and send
	 *                            appropriate response
	 * @return ModelAndView This returns the Login model and view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		// set view name
		ModelAndView mav = new ModelAndView("login");
		// set model
		mav.addObject("login", new Login());
		return mav;
	}

	/**
	 * This method handles POST request to validate user credentials and returns the
	 * view welcome.jsp with user details set inside the model. If user is not
	 * found, it returns to login.jsp with the error message.
	 * 
	 * @param HttpServletRequest  This is the parameter that handles request
	 *                            information
	 * @param HttpServletResponse This is the parameter used to modify and send
	 *                            appropriate response
	 * @return ModelAndView This returns the login or welcome view and model based
	 *         on validation
	 */
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login) {
		ModelAndView mav = null;
		HttpSession session = request.getSession();
		// call validateUser method of UserService class to validate user credentials
		 User user = userService.validateUser(login);
		// check if user found
		if (user != null) {
			session.setAttribute("user_id", user.getUserId());
			// set view welcome.jsp
			mav = new ModelAndView("welcome");
			// set user's first name as object
			
			mav.addObject("firstname", user.getLogin().getLoginId());
		} else {
			// set view as login.jsp
			mav = new ModelAndView("login");
			// set login error message
			mav.addObject("message", "Username or Password is wrong!!");
		}

		return mav;
	}

}
