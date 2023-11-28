package com.Bibhu.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RadioButtonFactory {
	WebDriver driver;
	@FindBy(xpath = "//*[@id=\"name\"]")
	WebElement name;

	@FindBy(xpath = "//*[@id=\"email\"]")
	WebElement email;

	@FindBy(xpath = "//*[@id=\"agreeTerms\"]")
	WebElement checkbox;

	@FindBy(xpath = "//*[@id=\"form-submit\"]")
	WebElement joinNow;

	@FindBy(xpath = "//*[@id=\"project-container\"]/div/div[1]/a[1]")
	WebElement selectProject;

	@FindBy(xpath = "/html/body/h1")
	WebElement title;

	@FindBy(xpath = "//*[@id=\"radio-btn-example\"]/fieldset/legend")
	WebElement titleOfRadioButton;
	
	@FindBy(xpath = "//*[@id=\"radio-btn-example\"]/fieldset/label[1]/input")
	WebElement raioButton;

	public RadioButtonFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
     public WebElement selectRadioButton() {
    	 return raioButton;
     }
	public String getTitleOfRadioButton() {
		return titleOfRadioButton.getText();
	}

	public String getTitleOfCurrentPage() {
		return title.getText();
	}

	public void setName(String name1) {
		name.sendKeys(name1);
	}

	public void setEmail(String email1) {
		email.sendKeys(email1);
	}

	public void selectCheckbox() {
		checkbox.click();

	}

	public void clickOnJoinNowButton() {
		joinNow.click();
	}

	public void selectProject() {
		selectProject.click();
	}

}
