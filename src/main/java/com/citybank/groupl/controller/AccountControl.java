package com.citybank.groupl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;
import com.citybank.groupl.bean.User;
import com.citybank.groupl.service.AccountService;
import com.citybank.groupl.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;


@Controller
public class AccountControl {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	
	
	@RequestMapping(value = "/getUserAccounts", method = RequestMethod.GET)
	public ModelAndView getUserAccounts(HttpServletRequest request, HttpServletResponse response) {
		int userId = (Integer) request.getSession().getAttribute("user_id");
		List<Account> accounts = accountService.getAllUserAccounts(userId);
		// set view name
		ModelAndView mav = new ModelAndView("userAccount");

		
		// set model
		mav.addObject("accounts", accounts);
		return mav;
	}
	
	@RequestMapping(value="/addAccount", method = RequestMethod.GET)
	public ModelAndView addAccount(HttpServletRequest request, HttpServletResponse response) {
		List<User> allUsers = userService.getAllUsers();
		List<User> customers = new ArrayList<User>();
		for(User user: allUsers) {
			if(!user.getIsAdmin()) {
				customers.add(user);
			}
		}
		List<AccountType> allAccountTypes = accountService.getAllAccountTypes();
		ModelAndView mav = new ModelAndView("addAccount");
		mav.addObject("all_users",customers);
		mav.addObject("all_account_types", allAccountTypes);
		return mav;
	}
	
	@RequestMapping(value="/openAccount", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addAccountForUser(HttpServletRequest request, HttpServletResponse response,
		 @RequestBody Map<String, String> accountDetails) {
		int rows = accountService.addAccount(Integer.parseInt(accountDetails.get("user_id")),
				Integer.parseInt(accountDetails.get("account_type_id")));
		Map<String,String> result = new HashMap<String, String>();
		if(rows==0) {
			result.put("response", "error");
		}
		else {
			result.put("response", "success");
		}
		return new JSONObject(result).toString();
	}
	
	@RequestMapping(value="/deposit", method= RequestMethod.POST)
	public ModelAndView depositAmount(HttpServletRequest request, HttpServletResponse response,
		 @RequestParam Map<String, String> depositDetails) {
		int userId = (Integer) request.getSession().getAttribute("user_id");
		int depositAccount = Integer.parseInt(depositDetails.get("account_id"));
		double depositAmount = Double.parseDouble(depositDetails.get("amount"));
		int updated = accountService.depositAmount(userId, depositAccount, depositAmount);
		ModelAndView mav = null;

		if(updated==1) {
			mav = new ModelAndView("redirect:/getUserAccounts");
			
		}
		else {
			mav = new ModelAndView("redirect:/getUserAccounts");
			
		}
		return mav;
	}
	
	@RequestMapping(value="/withdraw", method= RequestMethod.POST)
	public ModelAndView withdrawAmount(HttpServletRequest request, HttpServletResponse response,
		 @RequestParam Map<String, String> withdrawDetails) {
		int userId = (Integer) request.getSession().getAttribute("user_id");
		int withdrawAccount = Integer.parseInt(withdrawDetails.get("account_id"));
		double withdrawAmount = Double.parseDouble(withdrawDetails.get("amount"));
		int updated = accountService.withdrawAmount(userId, withdrawAccount, withdrawAmount);
		ModelAndView mav = null;
		if(updated==1) {
			mav = new ModelAndView("redirect:/getUserAccounts");
			mav.addObject("success-deposit","Amount withdrawn successfully!");
		}
		else {
			mav = new ModelAndView("redirect:/getUserAccounts");
			mav.addObject("error-deposit","Unable to complete withdrawal request!");
		}
		return mav;
	}
	
	@RequestMapping(value="/transferFunds", method= RequestMethod.POST)
	public ModelAndView transferFunds(HttpServletRequest request, HttpServletResponse response,
		 @RequestBody Map<String, String> transferDetails) {
		int userId = (Integer) request.getSession().getAttribute("user_id");
		int fromAccount = Integer.parseInt(transferDetails.get("fromAccount"));
		int toAccount = Integer.parseInt(transferDetails.get("toAccount"));
		double depositAmount = Double.parseDouble(transferDetails.get("amount"));
		int updated = accountService.transferFunds(userId,fromAccount, toAccount, depositAmount);
		ModelAndView mav = null;
		if(updated==1) {
			mav = new ModelAndView("redirect:/getUserAccounts");
			mav.addObject("success-deposit","Amount withdrawn successfully!");
		}
		else {
			mav = new ModelAndView("redirect:/getUserAccounts");
			mav.addObject("error-deposit","Unable to complete withdrawal request!");
		}
		return mav;
	}

}
