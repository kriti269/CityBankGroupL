package com.citybank.groupl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.citybank.groupl.bean.BillPayment;
import com.citybank.groupl.service.BillService;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary This is the bill payment controller for handling bill payment
 *          related requests. The bill service is autowired. It contains getters
 *          and setters for bill service. The method is getBillPayments. It will
 *          get list of bill payments done by user.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - This is
 * the bill payment controller for handling bill payment related requests. The
 * bill service is autowired. It contains getters and setters for bill service.
 * The method is getBillPayments. It will get list of bill payments done by
 * user.
 */

@Controller
public class BillPaymentControl {
	@Autowired
	public BillService billService;

	// getter for bill service
	public BillService getBillService() {
		return billService;
	}

	// setter for bill service
	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	@RequestMapping(value = "/viewBillPayments", method = RequestMethod.GET)
	public ModelAndView getBillPayments(HttpServletRequest request, HttpServletResponse response) {
		// getting the user id from session
		int user_id = (Integer) request.getSession().getAttribute("user_id");
		// getting bill payments done by user from bill service
		List<BillPayment> billPaymentsList = billService.getBillPayments(user_id);
		// setting view name
		ModelAndView mav = new ModelAndView("billPayments");
		// setting model view attributes
		mav.addObject("listOfBillPayments", billPaymentsList);
		return mav;
	}
}
