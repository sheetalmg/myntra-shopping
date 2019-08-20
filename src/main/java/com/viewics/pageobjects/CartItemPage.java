package com.viewics.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.TestException;

/**
 * @author sheetal.mgaikwad@gmail.com
 *
 */
public class CartItemPage extends BasePage {

	public CartItemPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@class='desktop-userTitle' and contains(text(),'Bag')]")
	private WebElement gotobag;

	@FindBy(xpath = "//*[@class='itemContainer-base-itemLink' and text()='Lavie Yellow Solid Sling Bag']")
	private WebElement lbl_bagitem;

	public void goToCart() throws Exception{
		try {
			if (gotobag.isDisplayed()) 
				gotobag.click();
		} catch (Exception e) {
			e.printStackTrace();
			throw new TestException("Failed to click on Bag option", e);
		}
	}
	
	public boolean isItemListedInCart() throws Exception {
		try {
			if (lbl_bagitem.isDisplayed()) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TestException("product item selected is not listed in cart", e);
		}
	}

}
