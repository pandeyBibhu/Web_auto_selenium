package com.Bibhu.WebAutomation;

import static org.testng.Assert.assertEquals;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.asserts.*;

public class confirmationPopHandle {
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

	@Test(enabled=true,dependsOnMethods="launchApplication",description="Verify the title of Alert")
	public void titleOfAlertPop() {
		WebElement titleOfAlert=driver.findElement(By.xpath("//legend[contains(text(),'Switch To Alert Example')]"));
		System.out.println(titleOfAlert.getText());
		
		soft.assertEquals(titleOfAlert.getText(),"Switch To Alert Example");
	}
	@Test(enabled=true,dependsOnMethods="launchApplication",description="Confirmation pop up operations accept/cancel")
	public void confirmationpopUpOperations() throws InterruptedException {
		WebElement textBox=driver.findElement(By.xpath("//fieldset//input[@id='name']"));
		
		textBox.sendKeys("Bibhu");
		Thread.sleep(5000);
		WebElement confirm=driver.findElement(By.xpath("//fieldset//input[@id='confirmbtn']"));
		System.out.println(confirm.getText());
		confirm.click();
		Thread.sleep(5000);
		//if(confirm.getText().equals("Hello Bibhu, Are you sure you want to confirm?")) {
			
			Thread.sleep(5000);
			
			driver.switchTo().alert().accept();
			System.out.println("Clicked on OK button to accept the Input");
	
		Thread.sleep(10000);
		textBox.clear();
		confirm.click();
		driver.switchTo().alert().dismiss();
		System.out.println("Clicked on cancel button");
		
		
		
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
