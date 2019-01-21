package com.psl.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	public boolean TestCase(String username, String password) {
		Statement stmt = null;

		String searchQuery = "select * from users where uname='"
				+ username + "' AND password='" + password + "'";
		try {
			// connecting to the DB
			// currentCon = ConnectionManager.getConnection();
			currentCon = ConnectionManager.getConnectionForPostgreSQL();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean userExists = rs.next();

			if (!userExists) {
				return false;
			} else {
				return true;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static LoginBean login(LoginBean bean) {
		Statement stmt = null;
		String username = bean.getUsername();
		String password = bean.getPassword();
		String searchQuery = "select * from users where uname='" + username
				+ "' AND password='" + password + "'";

		try {
			 // connecting to the DB
			currentCon = ConnectionManager.getConnectionForPostgreSQL();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean userExists = rs.next();

			if (!userExists) {
				bean.setValid(false);
			} else if (userExists) {
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				bean.setValid(true);
			} 

		/*	if (username.equals("test1") && password.equals("pwd")) {
				String firstName = "test1";
				String lastName = "user";
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				bean.setValid(true);
				}
			else {
				
				bean.setValid(false);		
			} 
	*/
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bean;
	}
	
	public static boolean validateFields(String username,String password){
		
		String regexpChars = "^[a-zA-Z0-9]{1,}$";
		Matcher matcher,matcher1;
		
		if(username.length()<1 || username.equals("")){
			return false;
		}
		if(password.length()<1 || password.equals("")){
			return false;
		}
		
		// to be uncommented for Special Character handling
		Pattern pattern = Pattern.compile(regexpChars);
		matcher = pattern.matcher(username);
		matcher1 = pattern.matcher(password);
		if(!matcher.matches() || !matcher1.matches())
		{
		   return false;
		}
		
		return true;
	}
	
	/*public static void main(String args[]){
		LoginDAO obj = new LoginDAO();
		boolean result = obj.validateFields("", "dfgd");
		System.out.println("validation is :"+result);
	}*/
}
