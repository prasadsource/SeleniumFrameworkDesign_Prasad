package com.rahulshetty.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.rahulshetty.resources.ExtententReportNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extentReports = ExtententReportNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest>  threadLocal = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		test =  extentReports.createTest(result.getMethod().getMethodName());
		threadLocal.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		threadLocal.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		threadLocal.get().fail(result.getThrowable());
		String filePath;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
			threadLocal.get().addScreenCaptureFromPath(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		threadLocal.get().skip("skipped due to dependency fail");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}
	

}
