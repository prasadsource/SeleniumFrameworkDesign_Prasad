package com.rahulshetty.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rahulshetty.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String username,String pass) {
		userEmail.sendKeys(username);
		password.sendKeys(pass);
		submit.click();
		return new ProductCatalogue(driver);
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
