package com.rahulshetty.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class StandAloneTeest {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Login
		driver.findElement(By.id("userEmail")).sendKeys("prasadtest@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();

		// Select all cart items
		// Add To Cart
		String productName = "ZARA COAT 3";
		List<WebElement> items = driver.findElements(By.className("mb-3"));

		WebElement girlDress = items.stream().filter(element -> element.findElement(By.cssSelector("b")).getText().equals(productName)).findAny()
				.orElse(null);

		girlDress.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		By cartMSG = By.id("toast-container");
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartMSG));
		driver.findElement(cartMSG);
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']"))));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='/dashboard/cart']")));
		// Cart Items
		driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();

		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean exists = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equals(productName));
		Assert.assertTrue(exists);

		driver.findElement(By.cssSelector(".totalRow  button")).click();

		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click(); //xpath://button[@class,'ta-item'][2]
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//Confirmation Page
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".action__submit")));
		String confirmationMSG = driver.findElement(By.cssSelector(".action__submit")).getText();
		System.out.println(confirmationMSG);
		Assert.assertTrue(confirmationMSG.equalsIgnoreCase("PLACE ORDER"));
		
		

	}
}
