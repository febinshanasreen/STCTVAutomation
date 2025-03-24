package com.stctv.utils;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.stctv.context.DriverManager;
import com.stctv.manager.ExtentReportManager;



public class ReportUtil {

	public static void addScreenShot(Status status, String message) {
		String path =  getScreenshot();
		ExtentReportManager.getCurrentTest().log(status, message, MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
	}


	public static void addScreenShot(String message) {
		String path =  getScreenshot();
		ExtentReportManager.getCurrentTest().log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
		
	}

	
	public static String getScreenshot() {
	    TakesScreenshot scrshot = (TakesScreenshot) DriverManager.getDriver();
	    // Capture screenshot as byte array
	    byte[] screenshotBytes = scrshot.getScreenshotAs(OutputType.BYTES);
	    // Encode byte array to Base64
	    String base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);
	    // Pass the Base64 string to MediaEntityBuilder
	    return base64Screenshot;
	}

	public static void logMessage(Status status, String details) {
		ExtentReportManager.getCurrentTest().log(status, details);

	}


	public static void logMessage(String message, String details) {
		ExtentReportManager.getCurrentTest().log(Status.INFO,  message +" "+details);
		
	}
	

}
