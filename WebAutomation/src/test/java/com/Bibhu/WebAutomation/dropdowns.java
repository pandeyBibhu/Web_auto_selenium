package com.Bibhu.WebAutomation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class dropdowns {
	static WebDriver driver;
	static String url = "https://www.rahulshettyacademy.com/practice-project";
	SoftAssert soft = new SoftAssert();

	@Test(alwaysRun = true,description="Lanching the chrome browser")
	public void launchBrowser() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Bibhu\\WebAutomation\\chromedriver-win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000);
		System.out.println("Wait completed");

	}

	@Test(enabled=true,dependsOnMethods = "launchBrowser",description="Launching the application")
	public static void launchApplication() throws InterruptedException {
		try {
			driver.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement name = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		name.sendKeys("Bibhu Pandey");
		WebElement email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		email.sendKeys("bibhupandey763@gmail.com");

		WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"agreeTerms\"]"));
		checkbox.click();

		WebElement joinNow = driver.findElement(By.xpath("//*[@id=\"form-submit\"]"));
		joinNow.click();
		Thread.sleep(5000);

		WebElement selectProject = driver.findElement(By.xpath("//*[@id=\"project-container\"]/div/div[1]/a[2]"));
		selectProject.click();
	}
	
	@Test(enabled = true,dependsOnMethods="launchApplication",description = "verify dropdown title")
	public void dropdownTitle() throws InterruptedException{
		WebElement dropDownTitle=driver.findElement(By.xpath("/html/body/div[1]/div[3]/fieldset/legend"));
		System.out.println(dropDownTitle.getText());
		soft.assertEquals("Dropdown Example",dropDownTitle.getText());
		WebElement selectDropdown=driver.findElement(By.xpath("//*[@id=\"dropdown-class-example\"]"));
		Select select=new Select(selectDropdown);
	
		/*
		 * Print ecah element available in dropdown by available methods
		 */
	     select.selectByIndex(3);	
		Thread.sleep(2000);
	

		
	}
	@Test(enabled = true,dependsOnMethods = "launchApplication",description="print all the options available in Dropdown")
	public void SelectAllOptionsDropdown() {
		WebElement selectDropdown=driver.findElement(By.xpath("//*[@id=\"dropdown-class-example\"]"));
		Select select=new Select(selectDropdown);
		
		List<WebElement>list=select.getOptions();
		for(WebElement option:list) {
//			option.getText();
			System.out.println(option.getText());
		}
	}
//	@AfterTest
//	public void closeAll() {
//		driver.close();
//		driver.quit();
//	}
}
