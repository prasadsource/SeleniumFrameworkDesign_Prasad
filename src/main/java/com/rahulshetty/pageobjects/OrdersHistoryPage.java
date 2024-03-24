package com.rahulshetty.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rahulshetty.abstractcomponents.AbstractComponent;

public class OrdersHistoryPage extends AbstractComponent {

	WebDriver driver;

	@FindBy (css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public OrdersHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getLatestOrderProductName() {
		return productNames.get(0).getText();
	}
}
