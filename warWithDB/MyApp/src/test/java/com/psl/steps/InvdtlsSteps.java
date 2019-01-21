package com.psl.steps;

import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class InvdtlsSteps { 
       
	private  HtmlUnitDriver driver = new HtmlUnitDriver();
	
	@Given("the user login successfully")
	public void login() {
		
		System.out.println("jbehave steps 2");
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/MyApp/LoginPage.jsp");
		driver.findElement(By.name("uname")).clear();
		driver.findElement(By.name("uname")).sendKeys("test1");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("pwd");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("commit")).submit();
	
	}
	
	@When("the user is at Employee Detail screen")
	 public void verifyPage() throws InterruptedException {

		Assert.assertEquals(driver.getTitle(), "Login Success");
	
	}
	
	@When("he clicks on submit button")
	 public void submitPage() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("commit")).click();
	
	}
	
	@Then("page title is $title")
	public void verifyTitle(String title) {
	
		Assert.assertEquals(driver.getTitle(), title);
	
	}
	
	@Then("on clicking button for cars having price < 10000, corresponding car details are displayed")
	public void displayCar1() {
	
		String price1 = driver.findElement(By.xpath("//div/form/div[1]/table/tbody/tr[2]/td[4]")).getText() ;
		Assert.assertTrue(Integer.parseInt(price1) < 10000);
	
	}
	
	@Then("on clicking button for cars having price > 10000, corresponding car details are displayed")
	public void displayCar2() {
	
		String price2 = driver.findElement(By.xpath("//div/form/div[2]/table/tbody/tr[2]/td[4]")).getText() ;
		Assert.assertTrue(Integer.parseInt(price2) > 10000);
	
	}
	
	@Then("on clicking back button user is navigated to login page again having title as $title")
	public void verifyNavigation(String title) {
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("Back")).click();
		Assert.assertEquals(driver.getTitle(), title);
		driver.quit();
	
	}

}
