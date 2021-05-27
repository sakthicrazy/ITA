package com.ita.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ita.pageObjects.LoginPage;
import com.ita.utils.BaseSteps;

public class LoginTest extends BaseSteps {
	LoginPage LoginPage = new LoginPage(driver);

	
	@Test
	public void validLoginTest() throws InterruptedException {
		LoginPage = new LoginPage(driver);
		LoginPage.validLogin(driver);
	}
	
	
}
