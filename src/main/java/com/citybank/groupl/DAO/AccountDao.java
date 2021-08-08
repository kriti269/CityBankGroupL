package com.citybank.groupl.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;
import com.citybank.groupl.bean.User;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary All database operations related to account table are performed in
 *          Account Dao.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - All
 * database operations related to account table are performed in Account Dao.
 */

public class AccountDao {
	
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//gets list of all accounts related to a customer 
	//from account table and order them by account types
	public List<Account> getAllUserAccounts(final int userId) {
		String sql = "select * from account a join account_type at on "
			+ "a.account_type_id = at.account_type_id where a.user_id=? order by at.type_name";
		List<Account> listOfAccounts = null;
		try {
			// executes the query and maps the row to User model.
			listOfAccounts = jdbcTemplate.query(sql, new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, userId);
				}
			}, new AccountRowMapper());
			return listOfAccounts;
		} catch (Exception e) {
			// prints any exception occurred
			System.out.println("Error occurred while logging in. " + e.getMessage());
		}
		// returns the validated User
		return listOfAccounts;
		
	}
	
	//Add account inserts a new account into the account
	//table for given user and account type. Returns 
	//the number of rows updated
	public int addAccount(final int userId, final int accountTypeId) {
		String sql = "insert into account(user_id,account_type_id) values(?,?) ";
		try {
			int result = 0;
			//checking that if account already exists
			Account account = getUserAccount(userId, accountTypeId);
			if(account == null) {
				result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
					
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, userId);
						ps.setInt(2, accountTypeId);
						
					}
				
				});
			}
			return result;
		}
		catch (Exception e) {
			// prints any exception occurred
			System.out.println("Error occurred while adding user account. " + e.getMessage());
		}
		return 0;
	}
	
	//Account Row mapper iImplements mapRow function of 
	//RowMapper to map Account rows with User and AccountType 
	//nested objects
	public class AccountRowMapper implements RowMapper<Account> {
	    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Account account = (new BeanPropertyRowMapper<Account>(Account.class)).mapRow(rs,rowNum);
	        User user = (new BeanPropertyRowMapper<User>(User.class)).mapRow(rs,rowNum);
	        AccountType accountType = (new BeanPropertyRowMapper<AccountType>(AccountType.class)).mapRow(rs,rowNum);
	        //set user for an account
	        account.setUser(user);
	        //set account type for an account
	        account.setAccountType(accountType);
	        return account ;
	    }
	}
	
	//Get account details based on user id and account type id
	public Account getUserAccount(final int userId, final int accountTypeId) {
		String sql = "Select * from account where user_id=? and account_type_id= ?";
		Account account = null;
		try {
			account = jdbcTemplate.queryForObject(sql,new Object[] {userId, accountTypeId  },
					new int[] { java.sql.Types.INTEGER, java.sql.Types.INTEGER },
					new BeanPropertyRowMapper<Account>(Account.class));
					
			return account;
		}
		catch (Exception e) {
			// prints any exception occurred
			System.out.println("Error occurred while getting user account. " + e.getMessage());
		}
		return account;
	}
	
	//Get account based on account id
	public Account getAccount(final int accountId) {
		String sql = "Select * from account where account_id=?";
		Account account = null;
		try {
			account = jdbcTemplate.queryForObject(sql,new Object[] { accountId  },
					new int[] { java.sql.Types.INTEGER },
					new BeanPropertyRowMapper<Account>(Account.class));
					
			return account;
		}
		catch (Exception e) {
			// prints any exception occurred
			System.out.println("Error occurred while getting user account. " + e.getMessage());
		}
		return account;
	}
	
	//Update account row with added balance
	public int depositAmount(int userId, int accountId, double amount) {
		String sql = "Update account set balance=balance+? where user_id=? and account_id=?";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {amount, userId, accountId  }, 
					new int[] { java.sql.Types.DOUBLE, java.sql.Types.INTEGER, java.sql.Types.INTEGER });
		}
		catch(Exception ex) {
			// prints any exception occurred
			System.out.println("Error occurred while depositing to account. " + ex.getMessage());
		}
		return result;
	}
	
	//Update account row with deducted balance
	public int withdrawAmount(int userId, int accountId, double amount) {
		String sql = "Update account set balance=balance-? where user_id=? and account_id=?";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {amount, userId, accountId  }, 
					new int[] { java.sql.Types.DOUBLE, java.sql.Types.INTEGER, java.sql.Types.INTEGER });
		}
		catch(Exception ex) {
			// prints any exception occurred
			System.out.println("Error occurred while withdrawing to account. " + ex.getMessage());
		}
		return result;
	}
	
	

}


