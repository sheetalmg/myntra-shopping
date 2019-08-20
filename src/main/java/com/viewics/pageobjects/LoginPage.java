package com.viewics.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.TestException;

/**
 * @author sheetal.mgaikwad@gmail.com
 *
 */
public class LoginPage extends BasePage{
	
	WebDriver driver;
	public static final int PAGELOAD_TIME=30;

	@FindBy(xpath = "//*[@class='login-title' and contains(text(),'Login to Myntra')]")
	private WebElement lbl_loginLabel;

	@FindBy(xpath = "//*[@class='login-user-input-email login-user-input']")
	private WebElement txt_userName;

	@FindBy(xpath = "//*[@type='password']")
	private WebElement txt_password;

	@FindBy(xpath = "//*[@class='login-login-button']")
	private WebElement btn_Login;

	@FindBy(xpath = "//*[@class='desktop-userTitle' and contains(text(),'Profile')]")
	private WebElement gotoProfile;

	@FindBy(xpath = "//*[@class='desktop-infoEmail']")
	private WebElement txt_Login;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public boolean isLoginPageLoaded() {
		boolean flag=false;
		try{
			getDriver().switchTo().defaultContent();
			WebDriverWait wait = new WebDriverWait (getDriver(), 30);
			wait.until(ExpectedConditions.elementToBeClickable(lbl_loginLabel));
			flag= true;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TestException("Failed to load login page", e);
		}
		return flag;
	}

	public void enterUsername(String username) throws Exception{
		if(txt_userName.isDisplayed()){
			txt_userName.sendKeys(username);
		}
	}

	public void enterPassword(String password) throws Exception{
		if(txt_password.isDisplayed())
			txt_password.sendKeys(password);
	}

	public void clickSignIn() throws Exception{
		WebDriverWait wait = new WebDriverWait (getDriver(), 30);
		try{
			if(btn_Login.isEnabled()){
				btn_Login.click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='login-title' and contains(text(),'Login to Myntra')]")));
				Reporter.log("SignIn button clicked",true);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TestException("Failed to click sign in button", e);
		}
	}

	public void goToProfile() throws Exception{
		try{
			gotoProfile.click();
			Reporter.log("Profile option clicked sucessfully",true);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TestException("Failed to navigate to Profile", e);
		}
	}
	
	public String isUserLoggedIn() throws Exception{
		return txt_Login.getText();
	}
	
}
