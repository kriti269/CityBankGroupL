package com.citybank.groupl.DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;
import com.citybank.groupl.bean.Transaction;
import com.citybank.groupl.bean.User;

public class TransactionDao {

	public JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int saveTransaction(int accountId, double amount, String txType) {
		String sql = "insert into transaction(account_id, tx_type, amount) values(?,?,?);";
		int result = 0;
		try {
			List<SqlParameter> listParams = Arrays.asList(new SqlParameter(Types.INTEGER, "account_id"),
					new SqlParameter(Types.VARCHAR, "tx_type"),
					new SqlParameter(Types.DOUBLE, "amount"));

			PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sql, listParams) {
				{
					setReturnGeneratedKeys(true);
					setGeneratedKeysColumnNames("transaction_id");
				}
			};
			
			List txParams = Arrays.asList(accountId,txType, amount);
			PreparedStatementCreator psc = pscf.newPreparedStatementCreator(txParams);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(psc, keyHolder);
			return Integer.parseInt(keyHolder.getKey().toString());
		}
		catch(Exception ex) {
			System.out.println("Error occurred while saving transaction: "+ ex.getMessage());
		}
		return result;
	}
	
	public List<Transaction> viewAllTransactions(int userId){
		String sql = "select * from banking_system.transaction t join account a on t.account_id=a.account_id "
				+ "join account_type at on a.account_type_id=at.account_type_id "
				+ "where t.account_id in (select account_id from account where user_id=?) "
				+ " order by t.tx_date_time desc";
		List<Transaction>  allTransactions = null;
		try {
			allTransactions = jdbcTemplate.query(sql, new Object[] {userId},
					new int[] {java.sql.Types.INTEGER}, new TransactionRowMapper());
		}
		catch(Exception ex) {
			System.out.println("Error occurred while saving transaction: "+ ex.getMessage());
		}
		return allTransactions;
	}
	
	public class TransactionRowMapper implements RowMapper<Transaction> {
	    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Transaction transaction = (new BeanPropertyRowMapper<Transaction>(Transaction.class)).mapRow(rs,rowNum);
	        Account account = (new BeanPropertyRowMapper<Account>(Account.class)).mapRow(rs,rowNum);
	        AccountType accountType = (new BeanPropertyRowMapper<AccountType>(AccountType.class)).mapRow(rs,rowNum);
	        account.setAccountType(accountType);
	        transaction.setAccount(account);
	        return transaction ;
	    }
	}

}
