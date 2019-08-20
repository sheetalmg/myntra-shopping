package com.viewics.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

/**
 * @author sheetal.mgaikwad@gmail.com
 *
 */
public class HomePage extends BasePage{
	public static final int PAGELOAD_TIME=60;
	
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(xpath = "//*[@class='desktop-searchBar']")
	private WebElement sbox;
	
	@FindBy(xpath="//*[@class='notify-info-message']")
	private WebElement popup_wishlist;
	
	@FindBy(xpath="//*[@class='desktop-userTitle' and contains(text(),'Wishlist')]")
	private WebElement gotowishlist;
	
	@FindBy (xpath="//*[@class='itemdetails-itemDetailsLabel']")
	private WebElement itemInWishlist;
	

	private String  item="//*[@title='%s']";
	private String  btn_wishlist="//a[contains(@href,'%p')]/following-sibling::div/span[@class='product-actionsButton product-wishlist']";

	public void addToWishlist(String value1,String value2) throws Exception{
		Actions hoverItem=new Actions(getDriver());
		String item_xpath=item.replace("%s", value1);
		WebElement element = getDriver().findElement(By.xpath(item_xpath));
		
		hoverItem.moveToElement(element).build().perform();
		String btn_wishlist_xpath=btn_wishlist.replace("%p", value2);
		WebElement btn_wishlist = getDriver().findElement(By.xpath(btn_wishlist_xpath));
		btn_wishlist.click();
	}
	
	public boolean isSearchDisplayed() throws Exception{
		return sbox.isDisplayed();
	}
	
	public boolean isSearchEnabled() throws Exception{
		return sbox.isEnabled();
	}
	
	public void enterSearchText(String value) throws Exception{
		WebDriverWait wait = new WebDriverWait (getDriver(), PAGELOAD_TIME);
		wait.until(ExpectedConditions.visibilityOf(sbox));

		sbox.sendKeys(value);
		sbox.sendKeys(Keys.ENTER);
	}
	
	public String verifyNotify() throws Exception{
		String text=null;
		WebDriverWait wait = new WebDriverWait (getDriver(),PAGELOAD_TIME);
		wait.until(ExpectedConditions.visibilityOf(popup_wishlist));
		text=popup_wishlist.getText();
		return text;
	}
	
	public void goToWishlist() throws Exception{
		try{
			if(gotowishlist.isDisplayed())
				gotowishlist.click();
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TestException("Failed to click on gotowishlist",e);
			
		}
	}

	public boolean isItemListed(String value) throws Exception{
		WebDriverWait wait = new WebDriverWait (getDriver(), PAGELOAD_TIME);
		getDriver().switchTo().defaultContent();
		String item_xpath=item.replace("%s", value);
		WebElement element = getDriver().findElement(By.xpath(item_xpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();
	}
}
