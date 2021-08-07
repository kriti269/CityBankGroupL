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
			mav = new ModelAndView("redirect:/addAccount");
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
