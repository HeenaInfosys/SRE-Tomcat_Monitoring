package com.psl.steps;

import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AddInvDtlsSteps { 
       
	private  HtmlUnitDriver driver = new HtmlUnitDriver();
	
	@Given("the user is at Employee Details Page")
	public void login() {
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/MyApp/LoginPage.jsp");
		driver.findElement(By.name("uname")).clear();
		driver.findElement(By.name("uname")).sendKeys("test1");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("pwd");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("commit")).submit();
		Assert.assertEquals(driver.getTitle(), "Login Success");
		
	}
	
	@When("the user click Add car details")
	 public void verifyPage() {
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("idlist1")).click();
	
	}
	
	@When("enters Manufacturer as $manufacturer")
	 public void enterManufacturer(String p_manufacturer) {
		
		driver.findElement(By.name("manufacturer")).sendKeys(p_manufacturer);
	
	}
	
	@When("enters Car Name as $carname")
	 public void enterCarName(String p_carname) {
		
		driver.findElement(By.name("carname")).sendKeys(p_carname);
	
	}
	
	@When("enters Color as $color")
	 public void enterColor(String p_color) {
		
		driver.findElement(By.name("color")).sendKeys(p_color);
	
	}
	
	@When("enters price as $price")
	 public void enterPrice(String p_price) {
		
		driver.findElement(By.name("price")).sendKeys(p_price);
	
	}
	
	@Then("on clicking Add button, entered details : $manufacturer , $carname , $color , $price should be present at Inventory details Page")
	 public void enterSubHeading1(
			 String p_manufacturer
		,	 String p_carname
		,	 String p_color
		,    String p_price      ) {
		
		driver.findElement(By.name("add")).submit();
		Assert.assertEquals(driver.getTitle(), "Inventory Details");
		
		String l_manufacturer = driver.findElement(By.xpath("//div/form/div[1]/table/tbody/tr[5]/td[1]")).getText() ;
		String l_carname = driver.findElement(By.xpath("//div/form/div[1]/table/tbody/tr[5]/td[2]")).getText() ;
		String l_color = driver.findElement(By.xpath("//div/form/div[1]/table/tbody/tr[5]/td[3]")).getText() ;
		String l_price = driver.findElement(By.xpath("//div/form/div[1]/table/tbody/tr[5]/td[4]")).getText() ;
		
		
	}
	
	@Then("under price<10000 USD range")
	 public void enterSubHeading2() throws InterruptedException {
		
		String l_price = driver.findElement(By.xpath("//div/form/div[1]/table/tbody/tr[5]/td[4]")).getText() ;
		Assert.assertTrue(Integer.parseInt(l_price) < 10000);
		driver.quit();
	}
	
}
