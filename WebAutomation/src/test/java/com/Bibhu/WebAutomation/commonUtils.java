package com.Bibhu.WebAutomation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class commonUtils {
	public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='2px solid red'", element); // Change border to red, modify as needed

        // Wait for a short duration to see the highlighted element (optional)
        try {
            Thread.sleep(5000); // Highlighted for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Remove the highlight
        js.executeScript("arguments[0].style.border=''", element); // Reset border style
    }

}
