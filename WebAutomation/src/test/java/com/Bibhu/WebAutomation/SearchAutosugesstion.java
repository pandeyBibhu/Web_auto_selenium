package com.Bibhu.WebAutomation;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.asserts.*;

public class SearchAutosugesstion {
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

	@Test(dependsOnMethods = "launchApplication")
	public void searchAutoSuggestion() throws InterruptedException {
		WebElement searchTitle = driver.findElement(By.xpath("//*[@id=\"select-class-example\"]/fieldset/legend"));
		System.out.println(searchTitle.getText());
		soft.assertEquals(searchTitle.getText(), "Suggession Class Example");

		WebElement searchPlaceHolder = driver.findElement(By.xpath("//*[@id=\"autocomplete\"]"));
		System.out.println("placeHolderText is::" + searchPlaceHolder.getAttribute("placeholder"));
		soft.assertEquals(searchPlaceHolder.getAttribute("placeholder"), "Type to Select Countries");

		searchPlaceHolder.click();
		searchPlaceHolder.sendKeys("India");
		Thread.sleep(5000);
		List<WebElement> selectFromList = driver.findElements(By.xpath("//*[@id=\"ui-id-1\"]//li//div"));
		for (int i = 0; i < selectFromList.size(); i++) {
			if (selectFromList.get(i).getText().equalsIgnoreCase("India")) {
				selectFromList.get(i).click();
				System.out.println("Clicked on auto-suggested Input");
			} else {
				System.out.println("Searched string not found");
			}
		}
		soft.assertAll();
	}

	@AfterSuite
	public void closeAll() {
		driver.close();
		driver.quit();
	}

}
