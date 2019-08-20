package com.viewics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.viewics.pageobjects.BasePage;
import com.viewics.pageobjects.CartItemPage;
import com.viewics.pageobjects.HomePage;
import com.viewics.pageobjects.LoginPage;
import com.viewics.pageobjects.WishListPage;

/**
 * @author sheetal.mgaikwad@gmail.com
 *
 */
public class MyntraController {
	
	public MyntraController(WebDriver driver) {
		this.driver = driver;
	}

	private BasePage basePage;
	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public BasePage getBasePage() {
		return basePage;
	}

	public void setBasePage(BasePage basePage) {
		this.basePage = basePage;
	}
	public LoginPage loginPage() {
		return this.initPage(LoginPage.class);
	}

	public HomePage homePage() {
		return this.initPage(HomePage.class);
	}

	public WishListPage wishListPage() {
		return this.initPage(WishListPage.class);
	}
	
	public CartItemPage cartItemPage() {
		return this.initPage(CartItemPage.class);
	}

	private <T extends BasePage> T initPage(Class<T> t) {
		T page = PageFactory.initElements(getDriver(), t);
		// page.setWebDriverClient((WebDriverClient)
		// clients.get("webDriverClient"));
		try {
			// page.getWebDriverClient().setImplicitWait(100);
			// page.getWebDriverClient().setExplicitWait(1);
			// if(!page.isAt()){
			// page.switchToPageIFrame();
			// }
		} catch (Exception e) {
			// Reporter.log(page.getClass().getSimpleName() +
			// ".switchToPageIframe() failed!", true);
			e.printStackTrace();
		} finally {
			// page.getWebDriverClient().resetImplicitWait();
			// page.getWebDriverClient().resetExplicitWait();
		}
		return page;
	}
	

}
