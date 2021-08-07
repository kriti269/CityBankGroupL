package com.citybank.groupl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.Bill;
import com.citybank.groupl.service.BillService;

@Controller
public class BillControl {
	@Autowired
	public BillService billService;

	public BillService getBillService() {
		return billService;
	}

	public void setBillService(BillService billService) {
		this.billService = billService;
	}
	
	@RequestMapping(value="/payBill", method = RequestMethod.POST)
	public ModelAndView payBill(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Map<String, String> billDetails) {
		Bill bill = new Bill();
		Account account = new Account();
		account.setAccountId(Integer.parseInt(billDetails.get("user_account")));
		bill.setAccount(account);
		bill.setMerchantName(billDetails.get("merchant_name"));
		bill.setMerchantAccount(billDetails.get("merchant_account"));
		double amount = Double.parseDouble(billDetails.get("amount"));
		bill = billService.addBill(bill, amount);
		ModelAndView mav = null;
		if(bill.getBillId()!=0) {
			mav = new ModelAndView("redirect:/getAllBills");
		}
		else {
			mav = new ModelAndView("paybill");
			mav.addObject("bill",bill);
			mav.addObject("error","Unable to add bill.");
		}
		return mav;	
	}
	

}
