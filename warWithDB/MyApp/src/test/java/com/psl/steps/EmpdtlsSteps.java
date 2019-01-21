package com.psl.steps;

import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class EmpdtlsSteps { 
       
	private  HtmlUnitDriver driver = new HtmlUnitDriver();
	
	@Given("the user login successfully")
	public void login() {
		System.out.println("jbehave steps 1");
	
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
	
	@Then("Heading is $heading")
	 public void enterHeading(String p_heading) throws InterruptedException {
		
		String heading = driver.findElement(By.xpath("//section/div/h1")).getText() ;
		Assert.assertEquals(heading, p_heading);
	
	}
	
	@Then("SubHeading1 is $text")
	 public void enterSubHeading1(String p_subheading1) throws InterruptedException {
		
		String subheading1 = driver.findElement(By.xpath("//section/div/table/tbody/tr/th[1]")).getText() ;
		Assert.assertEquals(subheading1, p_subheading1);
		
	}
	
	@Then("SubHeading2 is $text")
	 public void enterSubHeading2(String p_subheading2) throws InterruptedException {
		
		String subheading2 = driver.findElement(By.xpath("//section/div/table/tbody/tr/th[2]")).getText() ;
		Assert.assertEquals(subheading2, p_subheading2);
	
	}
	
	@Then("SubHeading3 is $text")
	 public void enterSubHeading3(String p_subheading3) throws InterruptedException {
		
		String subheading3 = driver.findElement(By.xpath("//section/div/table/tbody/tr/th[3]")).getText() ;
		Assert.assertEquals(subheading3, p_subheading3);
		
	}
	
	@Then("on clicking submit button next page title is $title")
	public void verifyTitle(String title) {
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("commit")).click();
		Assert.assertEquals(driver.getTitle(), title);
		driver.quit();
	
	}
	
}
