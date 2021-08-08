package com.citybank.groupl.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary This is the login controller for handling user login related
 *          requests. The user service is autowired. It contains setters for for
 *          user service. The methods are showLogin to show login page to the
 *          users, showWelcome to show welcome page to users after successful
 *          login, loginProcess to validate credentails provided by user upon
 *          login, logout to logout the user.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - This is
 * the login controller for handling user login related requests. The user
 * service is autowired. It contains setters for user service. The methods are
 * showLogin to show login page to the users, showWelcome to show welcome page
 * to users after successful login, loginProcess to validate credentails
 * provided by user upon login, logout to logout the user.
 */

@Controller
public class LoginControl {
	@Autowired
	UserService userService;

	// setter for user service
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		// set view name
		ModelAndView mav = new ModelAndView("login");
		// set model
		mav.addObject("login", new Login());
		return mav;
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView showWelcome(HttpServletRequest request, HttpServletResponse response) {
		// get user role from session
		boolean isAdmin = (Boolean) request.getSession().getAttribute("is_admin");
		ModelAndView mav = null;
		// check user is administrator
		if (isAdmin) {
			// show admin home page to user
			mav = new ModelAndView("welcome");
			// set view model attribute
			mav.addObject("user", new User());
		}

		return mav;
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login) {
		ModelAndView mav = null;
		HttpSession session = request.getSession();
		// call validateUser method of UserService class to validate user credentials
		User user = userService.validateUser(login);
		// check if user found
		if (user != null) {
			// setting session attributes with login details
			session.setAttribute("user_id", user.getUserId());
			session.setAttribute("login_id", user.getLogin().getLoginId());
			session.setAttribute("is_admin", user.getIsAdmin());

			if (user.getIsAdmin()) {
				// set view welcome.jsp
				// set user's first name as object
				mav = new ModelAndView("welcome");
				// set model attribute
				mav.addObject("user", new User());
			} else {
				// setting redirect view user account if not admin
				mav = new ModelAndView("redirect:/getUserAccounts");
			}
		} else {
			// set view as login.jsp
			mav = new ModelAndView("login");
			// set login error message
			mav.addObject("message", "Username or Password is wrong!!");
		}
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// remove logged in user details
		session.setAttribute("user_id", null);
		session.setAttribute("is_admin", null);
		// set view as index page
		RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/index.jsp");
		RequetsDispatcherObj.forward(request, response);
	}
}
