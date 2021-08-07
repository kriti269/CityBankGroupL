package com.citybank.groupl.service;

import com.citybank.groupl.DAO.AccountDao;
import com.citybank.groupl.DAO.BillDao;
import com.citybank.groupl.DAO.BillPaymentDao;
import com.citybank.groupl.DAO.TransactionDao;
import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.Bill;

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
		if(bill.getUserAccount() != null) {
			accountVal = String.valueOf(bill.getUserAccount().getAccountId());
		}
		String[] response = checkValidBillPayment(amountVal, accountVal, userId);
		if(response[0].equals("false")) {
			return response;
		}
		int accountId = bill.getUserAccount().getAccountId();
		double amount = Double.parseDouble(amountVal);
		bill = billDao.addBill(bill);
		accountDao.withdrawAmount(userId, accountId, amount);
		int id = 0;
		id = transactionDao.saveTransaction(accountId, amount, "Debit");
		payBill(bill.getBillId(), id);
		response[0] = String.valueOf(bill.getBillId());
		return response;
	}

	public int payBill(int billId, int transactionId) {
		return billPaymentDao.payBill(billId, transactionId);
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
			  Account userAccount = accountDao.getAccount(account_id_val);
			  if(userAccount.getBalance() < amount_val) {
				  response[0] = "false";
				  response[1] = "Unable to pay bill. Check Balance!";
			  }
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
}
