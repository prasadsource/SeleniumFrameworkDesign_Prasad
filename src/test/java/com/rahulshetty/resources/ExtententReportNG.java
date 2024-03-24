package com.rahulshetty.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtententReportNG {

	public static ExtentReports getReportObject() {
		File filePath = new File(System.getProperty("user.dir")+"\\reports\\index.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
		sparkReporter.config().setReportName("Web Automation Report");
		sparkReporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Tester", "Rahul Shetty");
		return extentReports;
	}
}
