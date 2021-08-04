package com.citybank.groupl.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.citybank.groupl.bean.Transaction;
import com.citybank.groupl.service.TransactionService;

@Controller
public class TransactionControl {
	
	@Autowired
	public TransactionService transactionService;
	
	@RequestMapping(value="/viewAllTransactions", method= RequestMethod.GET)
	public ModelAndView transferFunds(HttpServletRequest request, HttpServletResponse response,
		 @RequestParam int accountId) {
		List<Transaction> listOfTransactions = transactionService.viewAllTransactions(accountId);
		ModelAndView mav = new ModelAndView("transactionDetails");
		mav.addObject("listOfTransactions", listOfTransactions);
		return mav;
	}

}
