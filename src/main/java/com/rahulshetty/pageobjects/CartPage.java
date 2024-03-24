package com.rahulshetty.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rahulshetty.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css = ".totalRow  button")
	WebElement checkoutButton;
	
	public Boolean isProductFoundByName(String productName) {
		//new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".cartSection h3"))));
		//waitForElementToAppear(By.cssSelector(".cartSection h3"));
		return cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
	}

	public PaymentPage checkoutCart() {
		checkoutButton.click();
		return new PaymentPage(driver);
		
	}
}
