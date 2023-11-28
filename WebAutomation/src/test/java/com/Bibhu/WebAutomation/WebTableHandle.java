package com.Bibhu.WebAutomation;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.asserts.*;

import net.bytebuddy.asm.Advice.Argument;

public class WebTableHandle {
	static WebDriver driver;
	static String url = "https://www.rahulshettyacademy.com/practice-project";
	SoftAssert soft = new SoftAssert();

	@Test(alwaysRun = true, description = "Browser launching")
	public void launchBrowser() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Bibhu\\WebAutomation\\chromedriver-win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000);
		System.out.println("Wait completed");

	}

	@Test(enabled = true, dependsOnMethods = "launchBrowser", description = "launching the Application and project")
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

	@Test(enabled = true, dependsOnMethods = "launchApplication", description = "Verify the title of WebTable Link")
	public void titleOfWebLink() {
		WebElement titleOfWebTable = driver.findElement(By.xpath("/html/body/div[3]/div[1]/fieldset/legend"));
		System.out.println(titleOfWebTable.getText());

		soft.assertEquals(titleOfWebTable.getText(), "Web Table Example");
	}

	@Test(enabled = true, dependsOnMethods = "launchApplication", description = "Perform operation on web table")
	public void webTableOperation() throws InterruptedException {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,500)");

		Thread.sleep(5000);

		System.out.println("Printing total Headers details::\n");

		List<WebElement> headers = driver.findElements(By.xpath("//table[@name=\"courses\"]//tbody/tr/th"));
		for (WebElement header : headers) {
			System.out.println(header.getText() + "\n");
		}
		
		List<WebElement> rowsElements=driver.findElements(By.xpath("//table[@name=\"courses\"]//tbody/tr/td[1]"));
		System.out.println("print first cell(columns) elements:");
		for(WebElement columnsElements:rowsElements) {
			System.out.println(columnsElements.getText());
			
		}
		System.out.println("\n");
		List<WebElement> rowsElements1=driver.findElements(By.xpath("//table[@name=\"courses\"]//tbody/tr/td[2]"));
		System.out.println("print Third cell(columns) elements:");
		for(WebElement columnsElements:rowsElements1) {
			System.out.println(columnsElements.getText());
			
		}
		System.out.println("\n");
		List<WebElement> rowsElements2=driver.findElements(By.xpath("//table[@name=\"courses\"]//tbody/tr/td[3]"));
		System.out.println("print Second cell(columns) elements:");
		for(WebElement columnsElements:rowsElements2) {
			System.out.println(columnsElements.getText());
			
		}
		System.out.println("\n");
	}

	@AfterTest
	public void assertAfterEachTest() {
		soft.assertAll();
	}

	@AfterSuite
	public void closeAll() {
		driver.quit();
	}

}
