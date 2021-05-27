package com.ita.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	static ExtentReports extent;
	public static ExtentReports getReportObject()
	{
		String path=System.getProperty("user.dir")+"\\reports\\ExtentReport.html";
		ExtentSparkReporter e=new ExtentSparkReporter(path);
		extent=new ExtentReports();
		extent.attachReporter(e);
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", "Windows");
		return extent;
	}

}
