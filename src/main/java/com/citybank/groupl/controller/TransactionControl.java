package com.citybank.groupl.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.citybank.groupl.bean.Transaction;
import com.citybank.groupl.service.TransactionService;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary This is the transaction controller for handling transaction related
 *          requests. The transaction service is autowired. It contains setters
 *          for for transaction service. The methods is viewAllTransactions to
 *          view the list of transactions done by user.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - This is
 * the transaction controller for handling transaction related requests. The
 * transaction service is autowired. It contains setters for for transaction
 * service. The methods is viewAllTransactions to view the list of transactions
 * done by user.
 */

@Controller
public class TransactionControl {

	@Autowired
	public TransactionService transactionService;

	// setter for transaction service
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@RequestMapping(value = "/viewAllTransactions", method = RequestMethod.GET)
	public ModelAndView viewAllTransactions(HttpServletRequest request, HttpServletResponse response) {
		// getting user id from session
		int userId = (Integer) request.getSession().getAttribute("user_id");
		// getting list of transactions for user from transaction service
		List<Transaction> listOfTransactions = transactionService.viewAllTransactions(userId);
		// setting view name
		ModelAndView mav = new ModelAndView("transactionDetails");
		// setting view model attribute
		mav.addObject("listOfTransactions", listOfTransactions);
		return mav;
	}
}
