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
import org.springframework.web.servlet.ModelAndView;

import com.citybank.groupl.bean.BillPayment;
import com.citybank.groupl.service.BillService;

@Controller
public class BillPaymentControl {
	@Autowired
	public BillService billService;

	public BillService getBillService() {
		return billService;
	}

	public void setBillService(BillService billService) {
		this.billService = billService;
	}
	
	@RequestMapping(value="/viewBillPayments", method = RequestMethod.GET)
	public ModelAndView getBillPayments(HttpServletRequest request, HttpServletResponse response) {
		int user_id = (Integer) request.getSession().getAttribute("user_id");
		List<BillPayment> billPaymentsList = billService.getBillPayments(user_id);
		ModelAndView mav = new ModelAndView("billPayments");
		mav.addObject("listOfBillPayments", billPaymentsList);
		return mav;
	}
}
