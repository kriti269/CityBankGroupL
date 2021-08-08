package com.citybank.groupl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.Bill;
import com.citybank.groupl.service.AccountService;
import com.citybank.groupl.service.BillService;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary This is the bill controller for handling billing related requests.
 *          The account service and bill service are autowired. It contains
 *          getters and setters for account and bill services. The methods are
 *          getPayBill to show the pay bill page to the user, payBill to pay
 *          utility bills and deduct money from user's account.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - This is
 * the bill controller for handling billing related requests. The account
 * service and bill service are autowired. It contains getters and setters for
 * account and bill services. The methods are getPayBill to show the pay bill
 * page to the user, payBill to pay utility bills and deduct money from user's
 * account.
 */

@Controller
public class BillControl {
	@Autowired
	public BillService billService;

	@Autowired
	public AccountService accountService;

	// getter for bill service
	public BillService getBillService() {
		return billService;
	}

	// setter for bill service
	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	// getter for account service
	public AccountService getAccountService() {
		return accountService;
	}

	// setter for account service
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(value = "/paybills", method = RequestMethod.GET)
	public ModelAndView getPayBill(HttpServletRequest request, HttpServletResponse response) {
		// getting the user id from session
		int userId = (Integer) request.getSession().getAttribute("user_id");
		// getting list of accounts for a user
		List<Account> allUserAccounts = accountService.getAllUserAccounts(userId);
		// filtering accounts from which bill payments can be made
		List<Account> billAccounts = new ArrayList<Account>();
		for (Account account : allUserAccounts) {
			if (!account.getAccountType().getTypeName().equals("Fixed Deposit")) {
				billAccounts.add(account);
			}
		}
		// setting view name
		ModelAndView mav = new ModelAndView("paybills");
		// setting view model attribute
		mav.addObject("accounts", billAccounts);
		return mav;
	}

	@RequestMapping(value = "/payBill", method = RequestMethod.POST)
	public ModelAndView payBill(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, String> billDetails) {
		// setting view name
		ModelAndView mav = new ModelAndView("paybills");
		// initializing bill object
		Bill bill = new Bill();
		// getting bill amount to be paid
		String amountVal = billDetails.get("amount");
		// getting account id value from which bill will be paid
		String accountIdVal = billDetails.get("accountId");
		// getting user id from session
		int userId = (Integer) request.getSession().getAttribute("user_id");
		// getting user accounts for a user
		List<Account> allUserAccounts = accountService.getAllUserAccounts(userId);
		List<Account> billAccounts = new ArrayList<Account>();
		// filtering user account from which bill payments can be made
		for (Account account : allUserAccounts) {
			if (!account.getAccountType().getTypeName().equals("Fixed Deposit")) {
				billAccounts.add(account);
			}
		}
		// setting view name
		mav.addObject("accounts", billAccounts);

		// initializing account object
		Account account = new Account();
		// checking that provided account id is not empty
		if (!accountIdVal.equals("")) {
			account.setAccountId(Integer.parseInt(accountIdVal));
			bill.setAccount(account);
		}
		// setting merchant name in bill object
		bill.setMerchantName(billDetails.get("merchantName"));
		// setting merchant account number in bill object
		bill.setMerchantAccount(billDetails.get("merchantAccNo"));
		// paying bill from user account
		String[] resp = billService.addBill(bill, amountVal, userId);
		// validating that bill is paid or errors are generated
		if (!resp[0].equals("false") && Integer.parseInt(resp[0]) != 0) {
			// removing attributes from bill page on success
			for (String key : billDetails.keySet()) {
				billDetails.put(key, "");
			}
			// setting model attributes
			mav.addObject("bill", billDetails);
			mav.addObject("message", "Bill Paid Successfully!");
		} else {
			// setting bill details model attribute back on page
			mav.addObject("bill", billDetails);
			// setting error message to show to the user
			mav.addObject("errorMessage", resp[1]);
		}

		return mav;
	}
}
