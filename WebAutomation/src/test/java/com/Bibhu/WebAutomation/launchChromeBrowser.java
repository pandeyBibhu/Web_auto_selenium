package com.Bibhu.WebAutomation;
import com.Bibhu.ObjectRepository.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import org.testng.asserts.*;

import com.Bibhu.ObjectRepository.loginPageRepository;

public class launchChromeBrowser {
	static WebDriver driver;
	SoftAssert soft=new SoftAssert();
	static loginPageRepository objloginPage;
	static String url="https://www.rahulshettyacademy.com/practice-project";
	@Test
 public void launchBrowser() throws InterruptedException {
	 
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bibhu\\WebAutomation\\chromedriver-win32\\chromedriver.exe");
		driver= new ChromeDriver(); 
		driver.manage().window().maximize();
		Thread.sleep(2000);
		System.out.println("Wait completed");
	
 }
	@Test(dependsOnMethods = "launchBrowser")
	public void launchApplication() throws InterruptedException {
		driver.get(url);
		objloginPage=new loginPageRepository(driver);
		objloginPage.loginNow("Bibhu", "Bibhupandey763@gmail.com");
	}
	
	
	@Test(dependsOnMethods = "launchApplication")
	public void verifyHomePageTitle() {
		String title=driver.getTitle();
		System.out.println(title);
		soft.assertEquals("GreenKart - veg and fruits kart", title,"PASSED");

	}
	@Test(enabled=true,dependsOnMethods = "launchApplication",description="Verify cart after adding item")
	public void selectProduct() throws InterruptedException {
		Thread.sleep(5000);
	    objloginPage=new loginPageRepository(driver);
		objloginPage.ClickOnSearchButton().click();
		objloginPage.ClickOnSearchButton().sendKeys("Cucumber");
		Thread.sleep(2000);
		objloginPage.clickOnAddToCartButton().click();
		
	}
	@Test(dependsOnMethods = "selectProduct")
	public void VerifyItemAddedToCart(){
		objloginPage=new loginPageRepository(driver);
		objloginPage.clickOnCart().click();
		System.out.println("Total item in cart:::"+objloginPage.checkTotalItemsInCart().size());
		soft.assertEquals(1,objloginPage.checkTotalItemsInCart().size());
	}
	@AfterClass
	public void closeSession() throws InterruptedException {
		Thread.sleep(10000);
		driver.close();
		
	}
	@AfterTest
	public void assertAllTest() {
		soft.assertAll("All Test Cases Executed successfully");
	}
	
}
