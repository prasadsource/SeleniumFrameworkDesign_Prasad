package com.rahulshetty.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rahulshetty.pageobjects.CartPage;
import com.rahulshetty.pageobjects.OrdersHistoryPage;

public class AbstractComponent {

	WebDriver driver;
	
	@FindBy(css = "[routerlink*='/dashboard/cart']")
	WebElement cartButton;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement ordersButton;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToAppear(By findBy) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement element) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(By findBy) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(findBy)));
	}
	
	public void waitForElementToBeClickable(By findBy) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(findBy)));
	}
	
	public void waitForElementToBeClickable(WebElement element) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public CartPage gotoCartPage() {
		/*waitForElementToAppear(cartButtonLocator);
		waitForElementToBeClickable(cartButtonLocator);*/
		Actions actions = new Actions(driver);
		actions.moveToElement(cartButton).click().build().perform();
		return new CartPage(driver);
	}
	
	public OrdersHistoryPage gotoOrdersPage() {
		Actions actions = new Actions(driver);
		actions.moveToElement(ordersButton).click().build().perform();
		return new OrdersHistoryPage(driver);
	}
	
	
}
