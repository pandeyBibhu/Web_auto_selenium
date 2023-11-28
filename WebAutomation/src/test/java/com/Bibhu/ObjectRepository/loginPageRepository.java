package com.Bibhu.ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Bibhu.WebAutomation.checkBox;

public class loginPageRepository {
	static WebDriver driver;
	static By name = By.xpath("//*[@id=\"name\"]");
	static By email = By.xpath("//*[@id=\"email\"]");
	static By checkbox = By.xpath("//*[@id=\"agreeTerms\"]");
	static By joinNow = By.xpath("//*[@id=\"form-submit\"]");
	static By selectProject = By.xpath("//*[@id=\"project-container\"]/div/div[1]/a[1]");

	static By searchButton = By.xpath("//*[@id=\"root\"]/div/header/div/div[2]/form/input");
	static By addToCart = By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/div[3]/button");
	static By cart = By.xpath("//*[@id=\"root\"]/div/header/div/div[3]/a[4]/img");
	static By totalItemsInCart = By.xpath("//*[@id=\"root\"]/div/header/div/div[3]/div[1]/table/tbody/tr[1]");

	public loginPageRepository(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement clickOnCart() {
		return driver.findElement(cart);
	}

	public List<WebElement> checkTotalItemsInCart() {
		return driver.findElements(totalItemsInCart);
	}

	public WebElement clickOnAddToCartButton() {
		return driver.findElement(addToCart);

	}

	public WebElement ClickOnSearchButton() {
		return driver.findElement(searchButton);
	}

	public static void setName(String uname) {
		driver.findElement(name).sendKeys(uname);

	}

	public static void setEmail(String Uemail) {
		driver.findElement(email).sendKeys(Uemail);
	}

	public static WebElement selectCheckbox() {
		return driver.findElement(checkbox);
	}

	public static WebElement clickONJoinNow() {
		return driver.findElement(joinNow);
	}

	public static WebElement selectProject() {
		return driver.findElement(selectProject);
	}

	public void loginNow(String userName, String Email) throws InterruptedException {
		setName(userName);
		setEmail(Email);
		Thread.sleep(2000);
		selectCheckbox().click();
		clickONJoinNow().click();
		Thread.sleep(5000);
		selectProject().click();

	}

}
