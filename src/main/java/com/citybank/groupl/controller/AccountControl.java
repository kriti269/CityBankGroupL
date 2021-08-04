package com.citybank.groupl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.service.AccountService;


@Controller
public class AccountControl {
	
	@Autowired
	AccountService accountService;

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
	
	@RequestMapping(value="/openAccount", method = RequestMethod.POST)
	public ModelAndView addAccountForUser(HttpServletRequest request, HttpServletResponse response,
		 @RequestParam("accountTypeId") int accountId) {
		int userId = (Integer) request.getSession().getAttribute("user_id");
		int result = accountService.addAccount(userId, accountId);
		ModelAndView mav = new ModelAndView("login");
			
		return mav;
	}
	
	@RequestMapping(value="/deposit", method= RequestMethod.POST)
	public ModelAndView depositAmount(HttpServletRequest request, HttpServletResponse response,
		 @RequestBody Map<String, String> depositDetails) {
		int userId = (Integer) request.getSession().getAttribute("user_id");
		int depositAccount = Integer.parseInt(depositDetails.get("account_id"));
		double depositAmount = Double.parseDouble(depositDetails.get("amount"));
		int updated = accountService.depositAmount(userId, depositAccount, depositAmount);
		ModelAndView mav = null;
		if(updated==1) {
			mav = new ModelAndView("redirect:/getUserAccounts");
			mav.addObject("success-deposit","Amount deposited successfully!");
		}
		else {
			mav = new ModelAndView("redirect:/getUserAccounts");
			mav.addObject("error-deposit","Unable to complete deposit request!");
		}
		return mav;
	}
	
	@RequestMapping(value="/withdraw", method= RequestMethod.POST)
	public ModelAndView withdrawAmount(HttpServletRequest request, HttpServletResponse response,
		 @RequestBody Map<String, String> withdrawDetails) {
		int userId = (Integer) request.getSession().getAttribute("user_id");
		int depositAccount = Integer.parseInt(withdrawDetails.get("account_id"));
		double depositAmount = Double.parseDouble(withdrawDetails.get("amount"));
		int updated = accountService.withdrawAmount(userId, depositAccount, depositAmount);
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
