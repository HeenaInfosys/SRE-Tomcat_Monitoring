package com.psl.testng;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.psl.main.InventoryDAO;
import com.psl.main.LoginDAO;


public class AddInvDtlsCase {

	@Test
	// test for blank fields
	public void testForValidFields() throws Exception {
		
		boolean result = false ;
		System.out.println("Testing for valid fields");
		result = InventoryDAO.validateFields("BMW");
        Assert.assertEquals(result, true);
        result = InventoryDAO.validatePrice("10000");
        Assert.assertEquals(result, true);
	}
	
	@Test
	//test blank fields
	public void testBlankFields() throws Exception {
		
		boolean result = false ;
		System.out.println("Testing for blank fields");
		result = InventoryDAO.validateFields("");
        Assert.assertEquals(result, false);
        result = InventoryDAO.validatePrice("");
        Assert.assertEquals(result, false);
	}
	
	@Test
	//test blank password
	public void testSplChar() throws Exception {
		
		boolean result = false ;
		System.out.println("Testing special characters");
		result = InventoryDAO.validateFields("asdsdf%$&^");
        Assert.assertEquals(result, false);
        result = InventoryDAO.validatePrice("100%$&^");
        Assert.assertEquals(result, false);
	}
	
	@Test
	//test price range
	public void testPriceRange() throws Exception {
		
		boolean result = false ;
		System.out.println("Testing price range");
		result = InventoryDAO.validatePrice("0");
        Assert.assertEquals(result, false);
	}

	
}
