package com.citybank.groupl.service;

import com.citybank.groupl.DAO.AccountDao;
import java.util.List;

import com.citybank.groupl.DAO.BillDao;
import com.citybank.groupl.DAO.BillPaymentDao;
import com.citybank.groupl.DAO.TransactionDao;
import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.Bill;
import com.citybank.groupl.bean.BillPayment;

public class BillServiceImpl implements BillService{
	
	BillDao billDao;
	BillPaymentDao billPaymentDao;
	TransactionDao transactionDao;
	AccountDao accountDao;
	
	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	public BillDao getBillDao() {
		return billDao;
	}

	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	public BillPaymentDao getBillPaymentDao() {
		return billPaymentDao;
	}

	public void setBillPaymentDao(BillPaymentDao billPaymentDao) {
		this.billPaymentDao = billPaymentDao;
	}
	
	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}


	public String[] addBill(Bill bill, String amountVal, int userId) {
		String accountVal = "";
		if(bill.getAccount() != null) {
			accountVal = String.valueOf(bill.getAccount().getAccountId());
		}
		String[] response = checkValidBillPayment(amountVal, accountVal, userId);
		if(response[0].equals("false")) {
			return response;
		}
		int accountId = bill.getAccount().getAccountId();
		double amount = Double.parseDouble(amountVal);
		bill = billDao.addBill(bill);
		int result = accountDao.withdrawAmount(userId, accountId, amount);
		if(result == 0) {
			response[0] = "false";
			response[1] = "Unable to pay bill. Error occurred.";
			return response;
		}
		int id = 0;
		id = transactionDao.saveTransaction(accountId, amount, "Debit");
		payBill(bill.getBillId(), id);
		response[0] = String.valueOf(bill.getBillId());
		return response;
	}

	public int payBill(int billId, int transactionId) {
		return billPaymentDao.payBill(billId, transactionId);
	}

	public List<BillPayment> getBillPayments(int user_id) {
		return billPaymentDao.getBillPayments(user_id);
	}
	
	private String[] checkValidBillPayment(String amountVal, String accountIdVal, int userId) {
		String[] response = new String[2];
		response[0] = "true";
		
		if(accountIdVal.equals("")) {
			response[0] = "false";
			response[1] = "Unable to pay bill. Invalid Account Selection.";
			return response;
		}
				
		try
		{
		  double amount_val = Double.parseDouble(amountVal);
		  int account_id_val = Integer.parseInt(accountIdVal);
		  if(amount_val > 0 && account_id_val != 0) {
			  response = checkValidWithdrawal(amount_val, account_id_val);
		  } else {
			  response[0] = "false";
			  response[1] = "Unable to pay bill. Invalid amount value.";
		  }
		}
		catch(NumberFormatException e)
		{
			response[0] = "false";
			response[1] = "Unable to pay bill. Invalid amount value.";
		}
		
		return response;
	}
	
	private String[] checkValidWithdrawal(double amount_val, int account_id_val) {
		String[] response = new String[2];
		Account userAccount = accountDao.getAccount(account_id_val);
		  if(userAccount.getBalance() < amount_val) {
			  response[0] = "false";
			  response[1] = "Unable to pay bill. Check Balance!";
		  } else {
			  response[0] = "true";
		  }
		  return response;
	}
}
