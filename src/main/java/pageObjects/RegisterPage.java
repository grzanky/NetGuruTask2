package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	String passwordErrorMessage;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user_email-1627")
	WebElement email;
	
	@FindBy(id = "first_name-1627")
	WebElement name;
	
	@FindBy(id = "user_password-1627")
	WebElement password;

	@FindBy(id = "confirm_user_password-1627")
	WebElement confirmPassword;
	
	@FindBy(xpath = "//*[@id=\"tekst-block_5d558148a0110\"]/div/div/div/div/div/form/div[1]/div/div[6]/div/label/span[1]")
	WebElement firstCheckbox;
	
	@FindBy(xpath = "//*[@id=\"tekst-block_5d558148a0110\"]/div/div/div/div/div/form/div[1]/div/div[7]/div/label/span[1]")
	WebElement secondCheckbox;
	
	@FindBy(id = "um-submit-btn")
	WebElement registerButton;
	
	@FindBy(xpath="//*[@id=\"tekst-block_5d558148a0110\"]/div/div/div/div/div/form/div[1]/div/div[4]/div[3]")
	WebElement passwordErrorElement;

	public void setEmail(String userEmail) throws Exception {
		safeJavaScriptClick(email);
		email.sendKeys(userEmail);
	}

	public void setName(String userName) throws Exception {
		safeJavaScriptClick(name);
		name.sendKeys(userName);
	}
	
	public void setPassword(String userPassword) throws Exception {
		safeJavaScriptClick(password);
		password.sendKeys(userPassword);
	}
	
	public void tickCheckboxes() throws Exception {
		safeJavaScriptClick(firstCheckbox);
		safeJavaScriptClick(secondCheckbox);
	}

	public void clickRegister() throws Exception {
		safeJavaScriptClick(registerButton);
	}
	
	public String passwordErrorMessage() {
		return passwordErrorMessage = passwordErrorElement.getText();
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


