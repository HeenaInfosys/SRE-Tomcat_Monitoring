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

public class LoginPageSuccessSelTest{
	
	public static HtmlUnitDriver driver 	  		= new HtmlUnitDriver();
	
  @Test(priority = 0)
  public void testLoginSuccess() throws InterruptedException {
	  
	  System.out.println("in method 3");

		driver.findElement(By.name("uname")).sendKeys("test1");
		driver.findElement(By.name("password")).sendKeys("pwd");
		Thread.sleep(2000);
		driver.findElement(By.name("commit")).submit();
		Assert.assertEquals("Login Success", driver.getTitle());

  }
  @Test (priority = 1)
  public void testLoginIncorrect() throws InterruptedException {

		driver.findElement(By.name("uname")).sendKeys("test");
		driver.findElement(By.name("password")).sendKeys("pwd");
		Thread.sleep(2000);
		driver.findElement(By.name("commit")).submit();
		Assert.assertEquals("Login Failure", driver.getTitle());

}
  @Test (priority = 2)
  public void testLoginBlank() throws InterruptedException {

		driver.findElement(By.name("uname")).sendKeys(" ");
		driver.findElement(By.name("password")).sendKeys("pwd");
		Thread.sleep(2000);
		driver.findElement(By.name("commit")).submit();
		Assert.assertEquals("Login Failure", driver.getTitle());

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

