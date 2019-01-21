package com.psl.testng;



import org.openqa.selenium.By;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.junit.experimental.categories.Category;

public class InventoryDtlsSelTest{
	
	public static HtmlUnitDriver driver 	  		= new HtmlUnitDriver();
	
  @Test
  public void testInvDtls() throws InterruptedException {

	  System.out.println("in method 2");
		driver.findElement(By.name("uname")).sendKeys("test1");
		driver.findElement(By.name("password")).sendKeys("pwd");
		Thread.sleep(2000);
		driver.findElement(By.name("commit")).submit();
		Assert.assertEquals("Login Success", driver.getTitle());
		driver.findElement(By.name("commit")).click();
		Assert.assertEquals("Inventory Details",driver.getTitle());
		
		driver.findElement(By.id("idlist1")).click();
		String price1 = driver.findElement(By.xpath("//div/form/div[1]/table/tbody/tr[2]/td[4]")).getText() ;
		Thread.sleep(2000);
		driver.findElement(By.id("idlist2")).click();
		String price2 = driver.findElement(By.xpath("//div/form/div[2]/table/tbody/tr[2]/td[4]")).getText() ;
		
		Assert.assertEquals(true, Integer.parseInt(price1) < 10000);
		Assert.assertEquals(true, Integer.parseInt(price2) > 10000);
		
		Thread.sleep(2000);
		driver.findElement(By.name("Back")).click();
		Assert.assertEquals("Login Form",driver.getTitle());
			
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  
	  driver.get("http://localhost:8080/MyApp/LoginPage.jsp");

  }

  @AfterMethod
  public void afterMethod() {
	  
	  try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  
  }
  
  @AfterTest
  public void afterTest(){
	  driver.close();
  }
  
}

