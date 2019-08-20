package com.viewics.common.controls;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.viewics.MyntraController;

/**
 * @author sheetal.mgaikwad@gmail.com
 *
 */
public class BaseTest {

	public static final String PROJECT_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/";
	public static final String CHROME_DRIVER = "chromedriver.exe";
	public static final int PAGELOAD_TIME = 30;
	public static final String MYNTRA_URL = "https://www.myntra.com/";

	private WebDriver driver;

	public WebDriver getDriver() {
		System.out.println("Hello : " + PROJECT_PATH);
		System.setProperty("webdriver.chrome.driver", PROJECT_PATH
				+ CHROME_DRIVER);
		if (driver == null) {
			driver = new ChromeDriver();
		}
		return driver;
	}

//	public void setDriver(WebDriver driver) {
//		this.driver = driver;
//	}

	protected MyntraController myntraController;

	public MyntraController getMyntraController() {
		return myntraController;
	}

	public void setMyntraController(MyntraController myntraController) {
		this.myntraController = myntraController;
	}
	
	protected void navigationTo(String url) {		
		getDriver().navigate().to(url);
	}

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		try {
			myntraController = new MyntraController(getDriver());
			getDriver().manage().window().maximize();
			navigationTo(MYNTRA_URL);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@AfterClass
	public void close() {
//		driver.quit();
	}
}
