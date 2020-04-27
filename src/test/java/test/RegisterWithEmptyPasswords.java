package test;

import org.testng.annotations.Test;

import pageObjects.RegisterPage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utils.Constant;

public class RegisterWithEmptyPasswords {

	private WebDriver driver;
	private RegisterPage objRegisterPage;

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
	public void registerWithEmptyPasswords() throws Exception {
		driver.get(Constant.URL_REGISTER_PAGE);
		objRegisterPage = new RegisterPage(driver);
		objRegisterPage.setEmail("test@test.com");
		objRegisterPage.setName("Rafal");
		objRegisterPage.tickCheckboxes();
		objRegisterPage.clickRegister();
		Assert.assertTrue(objRegisterPage.passwordErrorMessage().contains("Has³o jest wymaganym polem"));
	}
}
