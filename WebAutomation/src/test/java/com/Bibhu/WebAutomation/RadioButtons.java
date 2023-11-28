package com.Bibhu.WebAutomation;

import com.Bibhu.PageFactory.*;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.asserts.*;

public class RadioButtons {
	static WebDriver driver;
	static RadioButtonFactory objRadioBtnFactory;
	static String url = "https://www.rahulshettyacademy.com/practice-project";
	SoftAssert soft = new SoftAssert();

	@Test
	public void launchBrowser() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Bibhu\\WebAutomation\\chromedriver-win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000);
		System.out.println("Wait completed");

	}

	@Test(enabled = true, dependsOnMethods = "launchBrowser", description = "Verify if Application is getting launched")
	public static void launchApplication() throws InterruptedException {
		driver.get(url);
		objRadioBtnFactory = new RadioButtonFactory(driver);
		objRadioBtnFactory.setName("Bibhu Pandey");
		objRadioBtnFactory.setEmail("Bibhupandey763@gmail.com");
		objRadioBtnFactory.selectCheckbox();
		objRadioBtnFactory.clickOnJoinNowButton();
		Thread.sleep(5000);
		objRadioBtnFactory.selectProject();
	}

	@Test(enabled = true, dependsOnMethods = "launchApplication", description = "verify titile of radioButton link")
	public void verifyRadioButtonPageTitle() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("Title of the page is::" + objRadioBtnFactory.getTitleOfRadioButton());
		soft.assertEquals("Radio Button Example", objRadioBtnFactory.getTitleOfRadioButton());
	}

	@Test(dependsOnMethods = "launchApplication")
	public void RadioButtonOperations() {
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		objRadioBtnFactory = new RadioButtonFactory(driver);

		

		boolean isSelected = objRadioBtnFactory.selectRadioButton().isSelected();
		System.out.println("isSelected::" + isSelected);
		if (!isSelected) {
			objRadioBtnFactory.selectRadioButton().click();
			System.out.println("Radio button is selected now");
		} else {
			System.out.println("Radio button already selected");
		}
		soft.assertEquals(objRadioBtnFactory.selectRadioButton().isSelected(), true);

	}

	@AfterTest
	public void assertAllTestMethods() {
		soft.assertAll("All test methods executed successfully");
	}

	@AfterClass
	public void closeDriver() {
		driver.close();
	}

}
