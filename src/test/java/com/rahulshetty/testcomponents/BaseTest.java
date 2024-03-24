package com.rahulshetty.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.rahulshetty.pageobjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties properties = new Properties();
		String browserExternal = System.getProperty("browser");
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
				+ "//src//test//java//com//rahulshetty//resources//GlobalData.properties");
		properties.load(fileInputStream);
		String browser = browserExternal != null ? browserExternal : (String) properties.get("browser");

		if ("chrome".equalsIgnoreCase(browser)) {
			ChromeOptions chromeOptions = new ChromeOptions();
			if (browser.contains("browser")) {
				chromeOptions.addArguments("headless");
			}
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
		} else if ("firefox".equalsIgnoreCase(browser)) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (browser.contains("headless")) {
				firefoxOptions.addArguments("--headless");
			}
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if ("edge".equalsIgnoreCase(browser)) {
			EdgeOptions options = new EdgeOptions();
			if (browser.contains("headless")) {
				options.addArguments("--headless");
			}
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
		}

		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1440, 900));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApp() throws IOException {
		initializeDriver();
		landingPage = new LandingPage(driver);
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "/" + testCaseName + ".png");
		FileUtils.copyFile(srcFile, destFile);
		return destFile.getPath();
	}

}
