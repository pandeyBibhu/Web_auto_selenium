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

public class TabHandle {
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

	@Test(enabled=true,dependsOnMethods="launchApplication",description="Verify the title of window link")
	public void titleofWindowsTab() {
		WebElement titleOfWindowTab=driver.findElement(By.xpath("/html/body/div[2]/div[2]/fieldset/legend"));
		System.out.println(titleOfWindowTab.getText());
		soft.assertEquals(titleOfWindowTab.getText(),"Switch Tab Example");
	}
	@Test(enabled=true,dependsOnMethods="launchApplication",description="Verify multiple windows operation")
	public void windowsTabOperations() throws InterruptedException {
		WebElement linkTobeClick=driver.findElement(By.xpath("//*[@id=\"opentab\"]"));
		linkTobeClick.click();
		Thread.sleep(5000);
		String parentWindow=driver.getWindowHandle();//get the parent window 
		String parentTitle=driver.getTitle();
		String parentURL=driver.getCurrentUrl();
		//get all windows
		Set<String> allWindows=driver.getWindowHandles();
		for(String handle:allWindows) {
			if(!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		String childTabUrl=driver.getCurrentUrl();
		soft.assertNotEquals(childTabUrl,parentURL,"parent and child tab URL is different");
		Thread.sleep(5000);
		String childTextTitle=driver.getTitle();
		soft.assertEquals(childTextTitle,childTextTitle);
		driver.close();
		driver.switchTo().window(parentWindow);
		
		soft.assertNotEquals(parentTitle, childTextTitle, "Titles are different");
		
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
