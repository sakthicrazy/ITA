package com.ita.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ita.utils.BaseSteps;
import com.ita.pageObjects.LoginPage;

public class InvalidLogin extends BaseSteps {

	public WebDriver driver;

	private static Logger logger = LogManager.getLogger(InvalidLogin.class.getSimpleName());
	LoginPage LoginPage = new LoginPage(driver);

	@BeforeTest
	public void initialize() throws IOException, InterruptedException {

		driver = initializeTheDriver();

	}

	@Test
	public void InvalidLoginTest() {

		/* Without entering username and password */
		
		BaseSteps.waitForElementToBeVisible(driver, LoginPage.username);
		driver.findElement(LoginPage.username).clear();
		driver.findElement(LoginPage.password).clear();
		driver.findElement(LoginPage.loginButton).click();

		BaseSteps.waitForElementToBeVisible(driver, LoginPage.usernameEmpty);
		String unameEmptyText = driver.findElement(LoginPage.usernameEmpty).getAttribute("innerHTML");
		String unameEmptyTextMessage = "User name cannot be empty.";
		if (unameEmptyText.contains(unameEmptyTextMessage)) {
			logger.info("User name cannot be empty Message displayed");
			Assert.assertTrue(true);
		} else {
			logger.error("User name cannot be empty. Message NOT displayed");
			Assert.assertTrue(false);
		}

		String pwordEmptyText = driver.findElement(LoginPage.passwordEmpty).getAttribute("innerHTML");
		String pwordEmptyTextMessage = "Password cannot be empty.";
		if (pwordEmptyText.contains(pwordEmptyTextMessage)) {
			logger.info("Password cannot be empty Message displayed");
			Assert.assertTrue(true);
		} else {
			logger.error("Password cannot be empty Message NOT displayed");
			Assert.assertTrue(false);
		}
		
		
		/* User enters valid username and wrong password */
		
		driver.findElement(LoginPage.username).clear();

		driver.findElement(LoginPage.username).sendKeys(prop.getProperty("validUsername"));

		BaseSteps.waitForElementToBeVisible(driver, LoginPage.password);

		driver.findElement(LoginPage.password).clear();

		driver.findElement(LoginPage.password).sendKeys(prop.getProperty("invalidPassword"));

		BaseSteps.waitForElementToBeVisible(driver, LoginPage.loginButton);

		driver.findElement(LoginPage.loginButton).click();

		BaseSteps.waitForElementToBeVisible(driver, LoginPage.incorrect);

		String invalidMessageText = driver.findElement(LoginPage.incorrect).getAttribute("innerHTML");

		String eMessage = "User Name or Password incorrect";

		if (invalidMessageText.contains(eMessage)) {
			logger.info("User Name or Password incorrect Message displayed");
			Assert.assertTrue(true);
		} else {
			logger.error("User Name or Password incorrect Message NOT displayed");
			Assert.assertTrue(false);
		}
		
		/* User enters Invalid Username and Invalid password */
		
		BaseSteps.waitForElementToBeVisible(driver, LoginPage.username);
		driver.findElement(LoginPage.username).clear();

		driver.findElement(LoginPage.username).sendKeys(prop.getProperty("invalidUsername"));

		BaseSteps.waitForElementToBeVisible(driver, LoginPage.password);

		driver.findElement(LoginPage.password).clear();

		driver.findElement(LoginPage.password).sendKeys(prop.getProperty("invalidPassword"));

		BaseSteps.waitForElementToBeVisible(driver, LoginPage.loginButton);

		driver.findElement(LoginPage.loginButton).click();

		BaseSteps.waitForElementToBeVisible(driver, LoginPage.incorrect);

		String incorrectText = driver.findElement(LoginPage.incorrect).getAttribute("innerHTML");

		String errorMsg = "User Name or Password incorrect";

		if (incorrectText.contains(errorMsg)) {

			logger.info("User Name or Password incorrect Message displayed");
			Assert.assertTrue(true);
		} else {

			logger.error("User Name or Password incorrect Message NOT displayed");
			Assert.assertTrue(false);

		}

	}

}
