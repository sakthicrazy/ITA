package com.ita.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.paulhammant.ngwebdriver.ByAngular;

public class HomePage {

	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}


	
	public By logo =  By.xpath("//div[contains(@class,'logo')]//img");
	public By dashboardText = By.xpath("//a[contains(text(),'Dashboard')]");
	public By appDomain = By.xpath("//input[@formcontrolname='applicationDomain']");
	public By appName = By.xpath("//input[@formcontrolname='applicationName']");
	public By projectID = By.xpath("//input[@formcontrolname='projectId']");
	public By browseFile = ByAngular.buttonText("Browse Files");
	public By submit = ByAngular.buttonText("Submit");
	
	
}
