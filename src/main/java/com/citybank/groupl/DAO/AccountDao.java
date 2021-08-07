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
import com.citybank.groupl.bean.Login;
import com.citybank.groupl.bean.User;

public class AccountDao {
	
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Account> getAllUserAccounts(final int userId) {
		String sql = "select * from account a join account_type at on a.account_type_id = at.account_type_id where a.user_id=?";
		List<Account> listOfAccounts = null;
		try {
			// executes the query and maps the row to User model.
			listOfAccounts = jdbcTemplate.query(sql, new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement ps) throws SQLException {
					// TODO Auto-generated method stub
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
	
	public int addAccount(final int userId, final int accountId) {
		String sql = "insert into account(user_id,account_type_id) values(?,?) ";
		try {
			int result = 0;
			//checking that if account already exists
			Account account = getUserAccount(userId, accountId);
			if(account == null) {
				result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
					
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, userId);
						ps.setInt(2, accountId);
						
					}
				
				});
			}
			return result;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public class AccountRowMapper implements RowMapper<Account> {
	    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Account account = (new BeanPropertyRowMapper<Account>(Account.class)).mapRow(rs,rowNum);
	        User user = (new BeanPropertyRowMapper<User>(User.class)).mapRow(rs,rowNum);
	        AccountType accountType = (new BeanPropertyRowMapper<AccountType>(AccountType.class)).mapRow(rs,rowNum);
	        account.setUser(user);
	        account.setAccountType(accountType);
	        return account ;
	    }
	}
	
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
			// TODO: handle exception
			System.out.println("Error occurred while getting user account. " + e.getMessage());
		}
		return account;
	}
	
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
			// TODO: handle exception
			System.out.println("Error occurred while getting user account. " + e.getMessage());
		}
		return account;
	}
	
	public int depositAmount(int userId, int accountId, double amount) {
		String sql = "Update account set balance=balance+? where user_id=? and account_id=?";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {amount, userId, accountId  }, 
					new int[] { java.sql.Types.DOUBLE, java.sql.Types.INTEGER, java.sql.Types.INTEGER });
		}
		catch(Exception ex) {
			System.out.println("Error occurred while depositing to account. " + ex.getMessage());
		}
		return result;
	}
	
	public int withdrawAmount(int userId, int accountId, double amount) {
		String sql = "Update account set balance=balance-? where user_id=? and account_id=?";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {amount, userId, accountId  }, 
					new int[] { java.sql.Types.DOUBLE, java.sql.Types.INTEGER, java.sql.Types.INTEGER });
		}
		catch(Exception ex) {
			System.out.println("Error occurred while withdrawing to account. " + ex.getMessage());
		}
		return result;
	}
	
	public List<AccountType> getAllAccountTypes(){
		String sql = "select * from account_type";
		List<AccountType> accountTypes = null;
		try {
			accountTypes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AccountType>(AccountType.class));
		}
		catch(Exception ex) {
			System.out.println("Unable to get all account types: " + ex.getMessage());
		}
		return accountTypes;
	}

}


