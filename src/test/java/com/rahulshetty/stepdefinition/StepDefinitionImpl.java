package com.rahulshetty.stepdefinition;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.rahulshetty.pageobjects.CartPage;
import com.rahulshetty.pageobjects.ConfirmationPage;
import com.rahulshetty.pageobjects.LandingPage;
import com.rahulshetty.pageobjects.PaymentPage;
import com.rahulshetty.pageobjects.ProductCatalogue;
import com.rahulshetty.testcomponents.BaseTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	
	
	ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on the Ecommerce site")
	public void I_landed_on_the_Ecommerce_site() throws IOException {
		 landingPage= launchApp();
	}
	
	@Given("^Logged in with username with (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("I add the product (.+) from the cart")
	public void I_add_the_product_from_the_cart(String productName) {
		WebElement girlDress = productCatalogue.getProductByName(productName).orElse(null);
		productCatalogue.addProductsToCart(girlDress);
	}
	
	@When("AND Checkout (.+) and submit the order")
	public void checkout_submit_order(String productName) {
		CartPage cart = productCatalogue.gotoCartPage();
		cart.isProductFoundByName(productName);
		Boolean exists = cart.isProductFoundByName(productName);
		Assert.assertTrue(exists);

		PaymentPage paymentPage = cart.checkoutCart();
		paymentPage.fillShippingCountry("ind");
		paymentPage.placeOrder();

		// Confirmation Page
		confirmationPage = paymentPage.placeOrder();
	}
	
	@Then("{string } message is displayed on the Confirmation Page.")
	public void message_is_displayed_confirmationPage(String string){
		String success = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(success.equalsIgnoreCase("Thankyou for the order."));
	}
}
