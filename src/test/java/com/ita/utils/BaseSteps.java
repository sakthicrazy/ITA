package com.ita.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.ita.pageObjects.LoginPage;
import com.paulhammant.ngwebdriver.NgWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSteps {

	public WebDriver driver;
	private static Logger logger = LogManager.getLogger("GetLogger");
	NgWebDriver ngWebDriver;
	JavascriptExecutor jsDriver;
	public Properties prop;
	
	
	public WebDriver initializeTheDriver() throws IOException
	{
		prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\data.properties");
		prop.load(fis);

		String browserName=prop.getProperty("browser");
				
		if(browserName.contains("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			jsDriver = (JavascriptExecutor)driver;
			NgWebDriver ngWebDriver = new NgWebDriver(jsDriver);
			ngWebDriver.waitForAngularRequestsToFinish();
			driver.manage().window().maximize();
		}
		else if(browserName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			jsDriver = (JavascriptExecutor)driver;
			NgWebDriver ngWebDriver = new NgWebDriver(jsDriver);
			ngWebDriver.waitForAngularRequestsToFinish();
			driver.manage().window().maximize();
		}
		else if(browserName.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			jsDriver = (JavascriptExecutor)driver;
			NgWebDriver ngWebDriver = new NgWebDriver(jsDriver);
			ngWebDriver.waitForAngularRequestsToFinish();
			driver.manage().window().maximize();
		}
		else if(browserName.equals("ie"))
		{
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			jsDriver = (JavascriptExecutor)driver;
			NgWebDriver ngWebDriver = new NgWebDriver(jsDriver);
			ngWebDriver.waitForAngularRequestsToFinish();
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		return driver;
		
	}
	
	
	
	@BeforeSuite
	public void openBrowser() throws IOException {
		System.out.println("Started Running");
	}
		

	@AfterSuite
	public void closeBrowser() {
		if (driver != null) {
		      System.out.println("Closing the driver");
		      driver.quit();
		   }
	}
	

	public static void waitForElementToBeVisible(WebDriver driver, By by) {
		
		try {
			(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOfElementLocated(by));
		}
		catch (Exception e) {
			logger.error("The element was not visible with in the wait time:"+ e.getMessage());
			System.out.println("The element was not visible with in the wait time   :" + e.getMessage());
			
		}
	}
	
	public static void sendKeys(WebElement elem, String input) {
		try {
			elem.clear();
			elem.sendKeys(input);
		} catch (Exception e) {
			logger.error("Error in sendKeys:"+ e.getMessage());
		}
	}





	}
