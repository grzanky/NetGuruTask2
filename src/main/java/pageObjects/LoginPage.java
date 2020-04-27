package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	String wrongUsernameMessage;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username-1628")
	WebElement email;

	@FindBy(id = "user_password-1628")
	WebElement password;

	@FindBy(id = "um-submit-btn")
	WebElement loginButton;
	
	@FindBy(xpath="//*[@id=\"tekst-block_5d55b589d7a6d\"]/div/div/div/div/div/form/p[1]")
	WebElement wrongUsernameElement;

	public void setEmail(String userEmail) {
		email.click();
		email.sendKeys(userEmail);
	}

	public void setPassword(String userPassword) {
		email.click();
		password.sendKeys(userPassword);
	}

	public void clickLogin() throws Exception {
		safeJavaScriptClick(loginButton);
	}
	
	public String wrongUsernameMessage() {
		return wrongUsernameMessage = wrongUsernameElement.getText();
	}
	
	public void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}
	}

}
