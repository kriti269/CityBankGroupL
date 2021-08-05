package com.citybank.groupl.controller;

import java.io.IOException;
import java.util.List;

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

@Controller
public class UserControl {
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
	 * This method handles GET request for register page and returns the view
	 * welcome.jsp and child jsp page register.jsp with new object model User.
	 * 
	 * @param HttpServletRequest  This is the parameter that handles request
	 *                            information
	 * @param HttpServletResponse This is the parameter used to modify and send
	 *                            appropriate response
	 * @return ModelAndView This returns the User model inside register view
	 */
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView showWelcome(HttpServletRequest request, HttpServletResponse response) {
		boolean isAdmin = (Boolean) request.getSession().getAttribute("is_admin");
		ModelAndView mav = null;
		// check user is administrator
		if(isAdmin) {
			mav = new ModelAndView("welcome");
			// set model
			mav.addObject("user", new User());
		}
		
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
			session.setAttribute("login_id", user.getLogin().getLoginId());
			session.setAttribute("is_admin", user.getIsAdmin());
			// set view welcome.jsp
			
			// set user's first name as object
			if(user.getIsAdmin()) {
				mav = new ModelAndView("welcome");
				// set model
				mav.addObject("user", new User());
			}
			else {
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
		//remove logged in user details
		session.setAttribute("user_id", null);
		session.setAttribute("is_admin", null);
		// set view welcome.jsp
		RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/index.jsp");
		RequetsDispatcherObj.forward(request, response);
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
			mav = new ModelAndView("welcome");
			// set register error message
			mav.addObject("message", "Unable to register! Please Try Again Later!!");
		}
		else {
			// set view users.jsp
			List<User> listOfAllUsers = userService.getAllUsers();
			mav = new ModelAndView("users");
			mav.addObject("users_list",listOfAllUsers);
		}
		
		return mav;
	}
	
	@RequestMapping(value="/viewAllUsers", method=RequestMethod.GET)
	public ModelAndView viewAllUsers(HttpServletRequest request, HttpServletResponse response){
		boolean isAdmin = (Boolean) request.getSession().getAttribute("is_admin");
		ModelAndView mav = null; 
		if(isAdmin) {
			List<User> listOfAllUsers = userService.getAllUsers();
			mav = new ModelAndView("users");
			mav.addObject("users_list",listOfAllUsers);
		}
		
		return mav;
	}
}
