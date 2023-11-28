package com.Bibhu.WebAutomation;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.asserts.*;

public class IframesHandle {
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

	@Test(enabled = true, dependsOnMethods = "launchApplication", description = "Verify title of iframe link")
	public void verifyTitleofIframe() throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,1500)");
		Thread.sleep(5000);
		WebElement titleOfIframe = driver.findElement(By.xpath("/html/body/div[5]/fieldset/legend"));
		System.out.println(titleOfIframe.getText());
		soft.assertEquals(titleOfIframe.getText(), "iFrame Example");
		}

	@Test(enabled = true, dependsOnMethods = "launchApplication", description = "Verify the IfrmeOperation")
	public void iframeOperation() throws InterruptedException {
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,1500)");
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//fieldset/iframe")));
		
		
		WebElement frame = driver.findElement(By.xpath("//div//fieldset/iframe"));
		commonUtils.highlightElement(driver, frame);
		driver.switchTo().frame(frame);
		Thread.sleep(5000);
		
		executor.executeScript("window.scrollBy(0,100)");
		
		Thread.sleep(5000);
		
//		executor.executeScript("arguments[0].scrollTop = arguments[1];", frame, 500);
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
