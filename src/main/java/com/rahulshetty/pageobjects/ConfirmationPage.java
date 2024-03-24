package com.rahulshetty.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.rahulshetty.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage() {
		waitForElementToAppear(By.cssSelector(".hero-primary"));
		return confirmationMessage.getText();
	}
}
