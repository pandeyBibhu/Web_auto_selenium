package com.Bibhu.WebAutomation;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.asserts.*;

public class checkBox {
	static WebDriver driver;
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

	@Test(dependsOnMethods = "launchBrowser")
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

	@Test(enabled = true, dependsOnMethods = "launchApplication", description = "Verify title of checkbox")
	public void verifyTitleOfCheckbox() {
		WebElement titleofCheckBox = driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/fieldset/legend"));
		System.out.println(titleofCheckBox.getText());

		soft.assertEquals(titleofCheckBox.getText(), "Checkbox Example");
	}

	@Test(enabled = true, dependsOnMethods = "launchApplication", description = "Verify checkbox selected or deselected")
	public void checkBoxOperations() throws InterruptedException {
		WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"checkBoxOption1\"]"));

		boolean isSelected = checkBox.isSelected();
		if (!isSelected) {
			checkBox.click();
		}
		System.out.println("Wait for 2 minutes");

		Thread.sleep(5000);
		checkBox.click();
		if (isSelected == false) {
			System.out.println("Checkbox is unchecked");
		}
	}

	@AfterSuite
	public void closeAll() {
		driver.quit();
	}

}
