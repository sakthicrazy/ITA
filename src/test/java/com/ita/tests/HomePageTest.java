package com.ita.tests;



import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ita.pageObjects.HomePage;
import com.ita.pageObjects.LoginPage;
import com.ita.utils.BaseSteps;
import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class HomePageTest extends BaseSteps{
	
	HomePage home = new HomePage(driver);
	private static Logger logger = LogManager.getLogger(HomePageTest.class.getSimpleName());
	LoginPage LoginPage = new LoginPage(driver);
	NgWebDriver ngWebDriver;
	JavascriptExecutor jsDriver;

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeTheDriver();

	}

	@Test(priority=1)
	public void verifyHomePage() throws InterruptedException {
		
		LoginPage.validLogin(driver);
		Thread.sleep(10000);
		jsDriver = (JavascriptExecutor)driver;
		NgWebDriver ngWebDriver = new NgWebDriver(jsDriver);
		ngWebDriver.waitForAngularRequestsToFinish();
		Thread.sleep(10000);
		BaseSteps.waitForElementToBeVisible(driver, home.logo);
		WebElement Logo = driver.findElement(home.logo);
		
		
		if(Logo.isDisplayed()) {
			
			logger.info("AVA logo displayed");
			Assert.assertTrue(true);
		}
		else
		{
			
			logger.info("AVA logo is NOT displayed");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=2)
	public void addFileandSubmit() throws InterruptedException {
		
		BaseSteps.waitForElementToBeVisible(driver, home.appDomain);
		WebElement appdom = driver.findElement(home.appDomain);
		BaseSteps.sendKeys(appdom, "Test");
		WebElement appNam = driver.findElement(home.appName);
		BaseSteps.sendKeys(appNam, "Test");
		WebElement proID = driver.findElement(home.projectID);
		BaseSteps.sendKeys(proID, "Test");
		WebElement browse = driver.findElement(home.browseFile);
		browse.click();
		Thread.sleep(4000);
		try {
	        //Setting clipboard with file location
			StringSelection ss = new StringSelection(prop.getProperty("filepath"));
		     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	      
	        Robot robot = new Robot();
	        robot.delay(250);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.delay(90);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	    } catch (Exception exp) {
	        exp.printStackTrace();
	        logger.error("File uploaded failed");
	    }
		Thread.sleep(5000);
		BaseSteps.waitForElementToBeVisible(driver, home.submit);
		//driver.findElement(home.submit).click();
		logger.info("File uploaded successfully");
	}

}
