package com.ita.pageObjects;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

import com.ita.tests.InvalidLogin;
import com.ita.utils.BaseSteps;

public class LoginPage extends BaseSteps {

	public WebDriver driver;
	private static Logger logger = LogManager.getLogger(LoginPage.class.getSimpleName());
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
	}
	

	public static By username = By.cssSelector("#username");
	public static By password = By.xpath("//input[@formcontrolname='password']");
	public static By loginButton = By.xpath("//button[text()='Login']");
	public static By incorrect = By.xpath("//div[@class='text_error']//form-inline-error//div");
	public static By usernameEmpty = By.xpath("(//div[@class='formGroup']//form-inline-error//div)[1]");
	public static By passwordEmpty = By.xpath("(//div[@class='formGroup']//form-inline-error//div)[2]");
	
	public void validLogin(WebDriver driver) throws InterruptedException {

		try {
			
			BaseSteps.waitForElementToBeVisible(driver, LoginPage.username);
			System.out.println("tryinggggg 1111111");
			driver.findElement(LoginPage.username).clear();

			driver.findElement(LoginPage.username).sendKeys("C0llab");

			BaseSteps.waitForElementToBeVisible(driver, LoginPage.password);

			driver.findElement(LoginPage.password).clear();

			driver.findElement(LoginPage.password).sendKeys("C0llabAdmin!");

			BaseSteps.waitForElementToBeVisible(driver, LoginPage.loginButton);

			driver.findElement(LoginPage.loginButton).click();
			Thread.sleep(3000);

			logger.info("Logged into Application ");
		} catch (Exception e) {
			System.out.println("Error occured in logintoApplicationFunction  :" + e.getMessage());
			logger.error("Error occured in logintoApplicationFunction");
			Assert.fail("Error occured in logging into Application Function");
		}
	}
		


}
