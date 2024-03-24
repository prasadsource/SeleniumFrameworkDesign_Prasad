package com.rahulshetty.pageobjects;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.rahulshetty.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	
	public static final String  PRODUCTS_LOCATOR = "mb-3";
	
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = ProductCatalogue.PRODUCTS_LOCATOR)
	List<WebElement> products;

	By productLocator = By.cssSelector("b");
	By addToCartLocator = By.cssSelector(".card-body button:last-of-type");
	By cartMSGLocator = By.id("toast-container");
	By cartMSGDisappearLocator = By.className("ng-animating");
	
	public List<WebElement>  getProductsList() {
		waitForElementToAppear(By.className(PRODUCTS_LOCATOR));
		return products;
		
	}
	
	public Optional<WebElement> getProductByName(String productName) {
		return products.stream()
				.filter(element -> element.findElement(productLocator).getText().equals(productName))
				.findAny();	
	}
	
	public void addProductsToCart(WebElement girlDress) {
		girlDress.findElement(addToCartLocator).click();
		waitForElementToAppear(cartMSGLocator);
		waitForElementToDisappear(cartMSGDisappearLocator);
	}

}
