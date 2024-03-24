package com.rahulshetty.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rahulshetty.abstractcomponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	
	WebDriver driver;
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By countryyDropDownLoactor = By.cssSelector(".ta-results");
	
	@FindBy(css = ".action__submit")
	WebElement placeOrder;
	By placeOrderLocator = By.cssSelector(".btnn.action__submit.ng-star-inserted");
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement suggestCountry;
	
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement chooseCountry;
	
	public void fillShippingCountry(String countryName) {
		Actions actions = new Actions(driver);
		actions.sendKeys(suggestCountry, countryName).build().perform();
		waitForElementToAppear(countryyDropDownLoactor);
		chooseCountry.click(); 
	}

	public ConfirmationPage placeOrder() {
		Actions actions = new Actions(driver);
		actions.click(placeOrder).build().perform();
		//placeOrder.click();
		return new ConfirmationPage(driver);
	}
}
