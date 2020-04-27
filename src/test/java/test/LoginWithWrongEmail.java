package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utils.Constant;

public class LoginWithWrongEmail {

	private WebDriver driver;
	private LoginPage objLoginPage;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", Constant.PATH_CHROMED_RIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void cloceBrowser() {
		driver.quit();
	}

	@Test
	public void loginWithWrongEmail() throws Exception {
		driver.get(Constant.URL_LOGIN_PAGE);
		objLoginPage = new LoginPage(driver);
		objLoginPage.setEmail("wrongemail");
		objLoginPage.setPassword("Abcde123");
		objLoginPage.clickLogin();
		Assert.assertTrue(objLoginPage.wrongUsernameMessage().contains("Nieprawid³owa nazwa u¿ytkownika"));
	}
}
