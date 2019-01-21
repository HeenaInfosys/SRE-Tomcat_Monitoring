package com.psl.testng;



import java.util.concurrent.TimeUnit;

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

public class EmployeeDBPageSelTest{
	
	public static HtmlUnitDriver driver 	  		= new HtmlUnitDriver();
	
  @Test
  public void testEmpDtls() throws InterruptedException {
	  
	  System.out.println("in method 1");

		driver.findElement(By.name("uname")).sendKeys("test1");
		driver.findElement(By.name("password")).sendKeys("pwd");
		Thread.sleep(2000);
		driver.findElement(By.name("commit")).submit();
		Assert.assertEquals("Login Success", driver.getTitle());
		
		String heading = driver.findElement(By.xpath("//section/div/h1")).getText() ;
		String subheading1 = driver.findElement(By.xpath("//section/div/table/tbody/tr/th[1]")).getText() ;
		String subheading2 = driver.findElement(By.xpath("//section/div/table/tbody/tr/th[2]")).getText() ;
		String subheading3 = driver.findElement(By.xpath("//section/div/table/tbody/tr/th[3]")).getText() ;
		Assert.assertEquals("Employees Database", heading);
		Assert.assertEquals("Firstname", subheading1);
		Assert.assertEquals("Lastname", subheading2);
		Assert.assertEquals("Points", subheading3);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("idlist1")).click();
		
		driver.findElement(By.name("manufacturer")).sendKeys("BMW");
		driver.findElement(By.name("carname")).sendKeys("320d");
		driver.findElement(By.name("color")).sendKeys("Black");
		driver.findElement(By.name("price")).sendKeys("6000");
		
		driver.findElement(By.name("add")).submit();
		
		Assert.assertEquals("Inventory Details",driver.getTitle());
			
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

