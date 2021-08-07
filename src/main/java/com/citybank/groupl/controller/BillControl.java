package com.citybank.groupl.controller;

import java.util.ArrayList;
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
import com.citybank.groupl.bean.AccountType;
import com.citybank.groupl.bean.Bill;
import com.citybank.groupl.bean.User;
import com.citybank.groupl.service.AccountService;
import com.citybank.groupl.service.BillService;

@Controller
public class BillControl {
	@Autowired
	public BillService billService;
	
	@Autowired
	public AccountService accountService;

	public BillService getBillService() {
		return billService;
	}

	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@RequestMapping(value="/paybills", method = RequestMethod.GET)
	public ModelAndView getPayBill(HttpServletRequest request, HttpServletResponse response) {
		int userId = (Integer) request.getSession().getAttribute("user_id");
		List<Account> allUserAccounts = accountService.getAllUserAccounts(userId);
		List<Account> billAccounts = new ArrayList<Account>();
		for(Account account: allUserAccounts) {
			if(!account.getAccountType().getTypeName().equals("Fixed Deposit")) {
				billAccounts.add(account);
			}
		}
		ModelAndView mav = new ModelAndView("paybills");
		mav.addObject("accounts", billAccounts);
		return mav;
	}
	
	@RequestMapping(value="/payBill", method = RequestMethod.POST)
	public ModelAndView payBill(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, String> billDetails) {
		ModelAndView mav = new ModelAndView("paybills");
		Bill bill = new Bill();
		String amountVal = billDetails.get("amount");
		String accountIdVal = billDetails.get("accountId");
		int userId = (Integer) request.getSession().getAttribute("user_id");
		List<Account> allUserAccounts = accountService.getAllUserAccounts(userId);
		List<Account> billAccounts = new ArrayList<Account>();
		for(Account account: allUserAccounts) {
			if(!account.getAccountType().getTypeName().equals("Fixed Deposit")) {
				billAccounts.add(account);
			}
		}
		mav.addObject("accounts", billAccounts);
		
		Account account = new Account();
		if(!accountIdVal.equals("")) {
			account.setAccountId(Integer.parseInt(accountIdVal));
			bill.setAccount(account);
		}
		bill.setMerchantName(billDetails.get("merchantName"));
		bill.setMerchantAccount(billDetails.get("merchantAccNo"));
		String[] resp = billService.addBill(bill, amountVal, userId);
		if(!resp[0].equals("false") && Integer.parseInt(resp[0]) != 0) {
			for(String key: billDetails.keySet()) {
				billDetails.put(key, "");
			}
			mav.addObject("bill", billDetails);
			mav.addObject("message","Bill Paid Successfully!");
		}
		else {
			mav.addObject("bill", billDetails);
			mav.addObject("errorMessage",resp[1]);
		}
		
		return mav;	
	}
}
