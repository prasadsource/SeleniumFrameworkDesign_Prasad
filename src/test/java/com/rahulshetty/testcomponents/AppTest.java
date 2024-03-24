package com.rahulshetty.testcomponents;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rahulshetty.pageobjects.CartPage;
import com.rahulshetty.pageobjects.ConfirmationPage;
import com.rahulshetty.pageobjects.OrdersHistoryPage;
import com.rahulshetty.pageobjects.PaymentPage;
import com.rahulshetty.pageobjects.ProductCatalogue;

import rahulshettyacademy.data.DataReader;

/**
 * Hello world!
 *
 */
public class AppTest extends BaseTest {

	String productName = "ZARA COAT 3";
	
	@Test(enabled = true, retryAnalyzer = Retry.class)
	public void checkTest() {
		Assert.assertTrue(false);
	}

	@Test(dataProvider = "getData", groups = { "Purchase" },retryAnalyzer = Retry.class)
	public void submitOrderTest(HashMap<String, String> map) throws IOException {

		// Select all cart items
		// Add To Cart
		ProductCatalogue productCatalogue = landingPage.loginApplication(map.get("email"), map.get("password"));
		productCatalogue.getProductsList();

		WebElement girlDress = productCatalogue.getProductByName(map.get("productName")).orElse(null);

		Assert.assertNotNull(girlDress);

		productCatalogue.addProductsToCart(girlDress);

		CartPage cart = productCatalogue.gotoCartPage();
		cart.isProductFoundByName(map.get("productName"));
		Boolean exists = cart.isProductFoundByName(map.get("productName"));
		Assert.assertTrue(exists);

		PaymentPage paymentPage = cart.checkoutCart();
		paymentPage.fillShippingCountry("ind");
		paymentPage.placeOrder();

		// Confirmation Page
		ConfirmationPage confirmationPage = paymentPage.placeOrder();
		String success = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(success.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "submitOrderTest" })
	public void historyOrder() {
		landingPage.loginApplication("prasadtest@gmail.com", "Test@123");
		OrdersHistoryPage ordersHistoryPage = landingPage.gotoOrdersPage();
		String ordereProductName = ordersHistoryPage.getLatestOrderProductName();
		Assert.assertEquals(ordereProductName, productName);
	}

	@DataProvider
	public Object[][] getData() throws IOException{
		/*		Map<String, String> map1 = new HashMap<>();
				map1.put("email", "prasadtest@gmail.com");
				map1.put("password","Test@123");
				map1.put("productName", "ZARA COAT 3");*/
		List<Map<String,String>> data = DataReader.getDataReaderToMap("/src/main/java/rahulshettyacademy/data/PurchaseOrder.json");

		return new Object[][] {{data.get(0)}};
		}
}
