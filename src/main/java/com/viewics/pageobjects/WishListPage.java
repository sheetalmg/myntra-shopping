package com.viewics.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

/**
 * @author sheetal.mgaikwad@gmail.com
 *
 */
public class WishListPage extends BasePage {

	public WishListPage(WebDriver driver) {
		super(driver);
	}
	public static final int PAGELOAD_TIME = 30;

	@FindBy(xpath = "//*[@class='itemcard-moveToBag itemcard-boldFont']")
	private WebElement lbl_movetobag;

	@FindBy(xpath = "//*[@class='sizeselect-sizeButton']")
	private WebElement select_Size;

	@FindBy(xpath = "//*[@class='itemcard-moveToBag itemcard-boldFont' and text()='DONE']")
	private WebElement lbl_done;

	@FindBy(xpath = "//*[@class='itemdetails-itemDetailsLabel']")
	private WebElement itemInWishlist;

	@FindBy(xpath = "//*[@class='wishlistEmpty-heading' and text()='YOUR WISHLIST IS EMPTY']")
	private WebElement lbl_emptywishlist;

	public boolean isItemListed() throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), PAGELOAD_TIME);
		getDriver().manage().timeouts().implicitlyWait(PAGELOAD_TIME, TimeUnit.SECONDS);
		getDriver().switchTo().defaultContent();
		wait.until(ExpectedConditions.elementToBeClickable(itemInWishlist));
		return itemInWishlist.isDisplayed();
	}

	public void moveToBag() throws Exception {
		try {
			if (lbl_movetobag.isDisplayed())
				lbl_movetobag.click();
		} catch (Exception e) {
			e.printStackTrace();
			throw new TestException("Failed to click on gotowishlist", e);

		}
	}

	public void selectItemSize() throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), PAGELOAD_TIME);
			wait.until(ExpectedConditions.elementToBeClickable(select_Size));
			if (select_Size.isEnabled())
				select_Size.click();
		} catch (Exception e) {
			e.printStackTrace();
			throw new TestException("Failed to click on gotowishlist", e);

		}
	}
	public void clickDone() throws Exception {
		try {
			if (lbl_done.isDisplayed())
				lbl_done.click();
		} catch (Exception e) {
			e.printStackTrace();
			throw new TestException("Failed to click on done option", e);

		}
	}
	
	public boolean isWishlistEmpty() throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), PAGELOAD_TIME);
		getDriver().manage().timeouts().implicitlyWait(PAGELOAD_TIME, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.textToBePresentInElement(lbl_emptywishlist, "YOUR WISHLIST IS EMPTY"));
		return lbl_emptywishlist.isDisplayed();
	}
}
