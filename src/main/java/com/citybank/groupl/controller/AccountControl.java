package com.citybank.groupl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;
import com.citybank.groupl.bean.User;
import com.citybank.groupl.service.AccountService;
import com.citybank.groupl.service.UserService;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary This is the account controller for handling account related
 *          requests. The account service and user service are autowired. It
 *          contains setters for account and user service. The methods are
 *          getUserAccounts to view all the user accounts on home page,
 *          addAccount to show add account page to user, openaccount to open an
 *          account for user from admin panel, deposit Amount for depositing
 *          amount in user's account, withdrawAmount to withdraw an amount from
 *          user's account and transferAmount to transfer funds from one account
 *          type to another user account type.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - This is
 * the account controller for handling account related requests. The account
 * service and user service are autowired. It contains setters for account and
 * user service. The methods are getUserAccounts to view all the user accounts
 * on home page, addAccount to show add account page to user, openaccount to
 * open an account for user from admin panel, deposit Amount for depositing
 * amount in user's account, withdrawAmount to withdraw an amount from user's
 * account and transferAmount to transfer funds from one account type to another
 * user account type.
 */

@Controller
public class AccountControl {

	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;

	// setter for user service
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// setter for account service
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(value = "/getUserAccounts", method = RequestMethod.GET)
	public ModelAndView getUserAccounts(HttpServletRequest request, HttpServletResponse response) {
		// getting user id from session storage
		int userId = (Integer) request.getSession().getAttribute("user_id");
		// Get all account for a user
		List<Account> accounts = accountService.getAllUserAccounts(userId);
		// set view name
		ModelAndView mav = new ModelAndView("userAccount");
		HttpSession session = request.getSession();
		// check that alert message exists in session
		if (session.getAttribute("alertMessage") != null) {
			// set model attribute to show alert message on jsp page
			mav.addObject("alertMessage", session.getAttribute("alertMessage"));
			// remove session attribute once alert is shown to user
			session.removeAttribute("alertMessage");
			// set model attribute to show alert message on jsp page
			mav.addObject("alertMessageType", session.getAttribute("alertMessageType"));
			// remove session attribute once alert is shown to user
			session.removeAttribute("alertMessageType");
		}
		// set model attribute
		mav.addObject("accounts", accounts);
		return mav;
	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.GET)
	public ModelAndView addAccount(HttpServletRequest request, HttpServletResponse response) {
		// getting all the users for selection
		List<User> allUsers = userService.getAllUsers();
		// filtering customers from all users
		List<User> customers = new ArrayList<User>();
		for (User user : allUsers) {
			if (!user.getIsAdmin()) {
				customers.add(user);
			}
		}
		// getting all account types from database
		List<AccountType> allAccountTypes = accountService.getAllAccountTypes();
		// set view name
		ModelAndView mav = new ModelAndView("addAccount");
		// setting view model attributes
		mav.addObject("all_users", customers);
		mav.addObject("all_account_types", allAccountTypes);
		return mav;
	}

	@RequestMapping(value = "/openAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addAccountForUser(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Map<String, String> accountDetails) {
		// getting selected user id and account type id to add an account type for a
		// user
		int rows = accountService.addAccount(Integer.parseInt(accountDetails.get("user_id")),
				Integer.parseInt(accountDetails.get("account_type_id")));
		Map<String, String> result = new HashMap<String, String>();
		// returning response with error and success types to jsp page Ajax call
		// response
		if (rows == 0) {
			result.put("response", "error");
		} else {
			result.put("response", "success");
		}
		// returning the response object to ajax call
		return new JSONObject(result).toString();
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public ModelAndView depositAmount(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, String> depositDetails) {
		// getting user id from session
		int userId = (Integer) request.getSession().getAttribute("user_id");
		// getting user account id to deposit an amount
		int depositAccount = Integer.parseInt(depositDetails.get("account_id"));
		// getting amount value to be deposited
		double depositAmount = Double.parseDouble(depositDetails.get("amount"));
		// calling deposit amount service
		String[] resp = accountService.depositAmount(userId, depositAccount, depositAmount);
		ModelAndView mav = null;
		// setting view name
		mav = new ModelAndView("redirect:/getUserAccounts");
		HttpSession session = request.getSession();
		// setting session variables for alert messages based
		// on error and success of deposit amount method
		if (resp[0].equals("false")) {
			session.setAttribute("alertMessageType", "error");
		} else {
			session.setAttribute("alertMessageType", "success");
		}

		session.setAttribute("alertMessage", resp[1]);
		return mav;
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public ModelAndView withdrawAmount(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, String> withdrawDetails) {
		// getting the user id from session
		int userId = (Integer) request.getSession().getAttribute("user_id");
		// getting the account id from which amount will be withdrawn
		int withdrawAccount = Integer.parseInt(withdrawDetails.get("account_id"));
		// getting the amount to be withdrawn
		double withdrawAmount = Double.parseDouble(withdrawDetails.get("amount"));
		// calling account service method to withdraw amount from user account
		String[] resp = accountService.withdrawAmount(userId, withdrawAccount, withdrawAmount);
		ModelAndView mav = null;
		// setting view name
		mav = new ModelAndView("redirect:/getUserAccounts");
		HttpSession session = request.getSession();
		// setting session variables for alert messages based
		// on error and success of deposit amount method
		if (resp[0].equals("false")) {
			session.setAttribute("alertMessageType", "error");
		} else {
			session.setAttribute("alertMessageType", "success");
		}

		session.setAttribute("alertMessage", resp[1]);
		return mav;
	}

	@RequestMapping(value = "/transferFunds", method = RequestMethod.POST)
	public ModelAndView transferFunds(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, String> transferDetails) {
		// getting user id from the session
		int userId = (Integer) request.getSession().getAttribute("user_id");
		// getting account type from which amount will be tranferred to another account
		int fromAccount = Integer.parseInt(transferDetails.get("fromAccount"));
		// getting account type in which amount will be added
		int toAccount = Integer.parseInt(transferDetails.get("toAccount"));
		// getting the amount which is being transferred
		double depositAmount = Double.parseDouble(transferDetails.get("amount"));
		// calling the account service to transfer the funds from one account to another
		String[] resp = accountService.transferFunds(userId, fromAccount, toAccount, depositAmount);
		ModelAndView mav = null;
		// setting view name
		mav = new ModelAndView("redirect:/getUserAccounts");
		HttpSession session = request.getSession();
		// setting session variables for alert messages based
		// on error and success of deposit amount method
		if (resp[0].equals("false")) {
			session.setAttribute("alertMessageType", "error");
		} else {
			session.setAttribute("alertMessageType", "success");
		}

		session.setAttribute("alertMessage", resp[1]);
		return mav;
	}

}
