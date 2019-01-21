package com.psl.testng;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.psl.main.LoginDAO;


public class LoginCase {

	@Test
	//test valid user
	public void testValidUser() throws Exception {
		
		boolean result = false ;
		System.out.println("Testing Valid User");
		result = LoginDAO.validateFields("test1", "pwd");
        Assert.assertEquals(result, true);
	}
	
	@Test
	//test blank username
	public void testBlankUser() throws Exception {
		
		boolean result = false ;
		System.out.println("Testing blank Username");
		result = LoginDAO.validateFields("", "pwd");
        Assert.assertEquals(result, false);
	}
	
	@Test
	//test blank password
	public void testBlankPwd() throws Exception {
		
		boolean result = false ;
		System.out.println("Testing blank password");
		result = LoginDAO.validateFields("test1", "");
        Assert.assertEquals(result, false);
	}
	
	@Test
	//test special character in username
	public void testSplCharUser() throws Exception {
		
		boolean result = false ;
		System.out.println("Testing special characters in username");
		result = LoginDAO.validateFields("test1&*^%$#@", "pwd");
        Assert.assertEquals(result, false);
	}
	
	@Test
	//test special character in password
	public void testSplCharPwd() throws Exception {
		
		boolean result = false ;
		System.out.println("Testing special characters in password");
		result = LoginDAO.validateFields("test1", "pwd^&*#$%");
        Assert.assertEquals(result, false);
	}
	
}
