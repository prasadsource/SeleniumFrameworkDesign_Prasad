package com.rahulshetty.errorvalidations;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rahulshetty.pageobjects.CartPage;
import com.rahulshetty.pageobjects.ProductCatalogue;
import com.rahulshetty.testcomponents.BaseTest;

public class ErrorValidation extends BaseTest {


	
	@Test(groups = "errorHandling")
	public void invalidCredentials() {
		landingPage.loginApplication("prasadtest11@gmail.com", "Test@123");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
	
	@Test
	public void wrongProductSelected() {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("prasadtest2@gmail.com", "Test@123");
		productCatalogue.getProductsList();

		WebElement girlDress = productCatalogue.getProductByName(productName).orElse(null);
		productCatalogue.addProductsToCart(girlDress);
		CartPage cart = productCatalogue.gotoCartPage();
		cart.isProductFoundByName(productName);
		Boolean exists = cart.isProductFoundByName("ZARA COAT 33");
		Assert.assertFalse(exists);
		}

}
