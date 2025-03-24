package com.stctv.utils;

import java.util.List;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class CommonAssertions {

	
	public static void validateTrue(boolean flag, String failedMSG) {
		
		Assert.assertTrue(flag, failedMSG);
	}
	
	
	public static void validateFalse(boolean flag, String failedMSG) {
		Assert.assertFalse(flag, failedMSG);
	}
	
	public static void verifyEquals(String actualText, String expectedText, String passMsg, String failMsg) {
		try {
			Assert.assertEquals(actualText.toLowerCase(), expectedText.toLowerCase());
			ReportUtil.addScreenShot(Status.PASS, passMsg );
		} catch (AssertionError ae) {
			ReportUtil.addScreenShot(Status.FAIL, failMsg);
			ReportUtil.logMessage("Text Assertion Error Text", ae.getMessage());
			Assert.assertEquals(actualText, expectedText);
		}
	}
	
	
	public static <T> void validateLists(List<T> actualList, List<T> expectedList, String validationMessage, String msgIfValidationFails) {
		Assert.assertEquals(actualList, expectedList, "Lists do not match: " + expectedList + " != " + actualList);
		ReportUtil.logMessage(Status.PASS, "Actual list:\n" + actualList.toString()+" "+
				"Expected list:\n" + expectedList.toString());
	}
}
