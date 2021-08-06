package com.citybank.groupl.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;
import com.citybank.groupl.bean.Address;
import com.citybank.groupl.bean.Login;
import com.citybank.groupl.bean.User;

/**
 * @since 11-07-2021
 * @author Group L - Kriti, Jatin, Varun, Sonia
 * @serial 1.0
 * @summary DAO class for User model. It creates a new user in the database and
 *          validates user credentials including user name and password from
 *          database.
 */

/*
 * Date - 11-07-2021 Author - Kriti, Jatin, Varun, Sonia Description - DAO class
 * for User model. It creates a new user in the database and validates user
 * credentials including user name and password from database.
 */
@Component
public class UserDao {
	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * This method takes a User model object and inserts a corresponding row in the
	 * database table.
	 * 
	 * @param User This is the parameter that represents User model.
	 * @return int This returns the number of rows affected.
	 */
	public int register(final User user) {
		// create SQL query and initialize result to 0
		String sql = "insert into user(login_id, first_name, last_name, email,"
				+ " phone_number, gender, address_id, is_admin) values(?,?,?,?,?,?,?,?)";
		int result = 0;
		try {
			// insert the row by using PreparedStatementSetter object to set model
			// attributes inside SQL query
			
			result = jdbcTemplate.update(sql, new PreparedStatementSetter() {

				public void setValues(PreparedStatement ps) throws SQLException {
					
					ps.setString(1, user.getLogin().getLoginId());
					ps.setString(2, user.getFirstName());
					ps.setString(3, user.getLastName());
					ps.setString(4, user.getEmail());
					ps.setLong(5, user.getPhoneNumber());
					ps.setString(6, user.getGender());
					ps.setLong(7, user.getAddress().getAddressId());
					ps.setBoolean(8, user.getIsAdmin());
				}
			});
		} catch (Exception e) {
			// prints if any exception occurs
			System.out.println("Error occurred while registering user. " + e.getMessage());
		}
		// returns number of rows affected as either 0 or 1
		return result;
	}

	public User getUserByLogin(Login login) {
		String sql = "select * from user where login_id=?";
		User user = null;
		try {
			// executes the query and maps the row to User model.
			user = jdbcTemplate.queryForObject(sql, new Object[] { login.getLoginId() },
					new int[] { java.sql.Types.VARCHAR },
					new BeanPropertyRowMapper<User>(User.class));
			user.setLogin(login);
			return user;
		} catch (Exception e) {
			// prints any exception occurred
			System.out.println("Error occurred while logging in. " + e.getMessage());
		}
		return user;
		
	}
	
	public List<User> getAllUsers(){
		String sql = "select * from user join login on user.login_id=login.login_id join address on user.address_id = address.address_id";
		List<User> allUsersList = null;
		try {
			allUsersList = jdbcTemplate.query(sql, new UserRowMapper());
		}
		catch(Exception ex) {
			System.out.println("Error getting users:"+ ex.getMessage());
		}
		return allUsersList;
	}
	
	public class UserRowMapper implements RowMapper<User> {
	    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	        User user = (new BeanPropertyRowMapper<User>(User.class)).mapRow(rs,rowNum);
	        Address address = (new BeanPropertyRowMapper<Address>(Address.class)).mapRow(rs,rowNum);
	        Login login = (new BeanPropertyRowMapper<Login>(Login.class)).mapRow(rs,rowNum);
	        user.setAddress(address);
	        user.setLogin(login);
	        return user ;
	    }
	}

}
