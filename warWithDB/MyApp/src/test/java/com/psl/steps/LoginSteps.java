package com.psl.steps;

import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class LoginSteps { 
       
	private  HtmlUnitDriver driver = new HtmlUnitDriver();	
	@Given("the user is at login page")
	public void init() {
		System.out.println("jbehave steps 3");
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/MyApp/LoginPage.jsp");
	
	}
	
	@When("the username is passed as $uname")
	 public void enterUname(String uname) throws InterruptedException {
	
		driver.findElement(By.name("uname")).clear();
		driver.findElement(By.name("uname")).sendKeys(uname);
	
	}
	
	@When("the password is passed as $pwd")
	 public void enterPwd(String pwd) throws InterruptedException {
		
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(pwd);
	
	}
	
	@When("login button is clicked")
	 public void login() throws InterruptedException {
		
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("commit")).submit();
	
	}

	@Then("the page title is $title")
	public void verifyTitle(String title) {
	
		Assert.assertEquals(driver.getTitle(), title);
		//driver.quit();
	
	}

}
