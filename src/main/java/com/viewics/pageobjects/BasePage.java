package com.viewics.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * @author sheetal.mgaikwad@gmail.com
 *
 */
public class BasePage {

	private WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void quit() {
		driver.quit();
	}

	public void close() {
		driver.close();
	}

	public void maximizeWindows() {
		driver.manage().window().maximize();
	}

	public void refreshBrowser() throws Exception {
		driver.navigate().refresh();
	}

	public void setURL(String url) {
		Reporter.log("Opening URL: " + url, true);
		driver.get(url);
	}

	public boolean isWebElementDisplayed(WebElement element) {
		try {
			if (element.isDisplayed())
				return true;
		} catch (Exception e) {
		}
		return false;
	}
}
