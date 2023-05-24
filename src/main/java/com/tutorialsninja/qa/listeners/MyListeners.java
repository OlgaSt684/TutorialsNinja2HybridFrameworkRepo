package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utils.ExtentReporter;
import com.tutorialninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extenetTest;
	String testName;

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extenetTest = extentReport.createTest(testName);
		extenetTest.log(Status.INFO, testName + " started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extenetTest.log(Status.PASS, testName + " got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {

			e.printStackTrace();
		}
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, testName);
		
		extenetTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extenetTest.log(Status.INFO, result.getThrowable());
		extenetTest.log(Status.FAIL, testName + " got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extenetTest.log(Status.INFO, result.getThrowable());
		extenetTest.log(Status.SKIP, testName + " got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
        File extentReoprt = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReoprt.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
