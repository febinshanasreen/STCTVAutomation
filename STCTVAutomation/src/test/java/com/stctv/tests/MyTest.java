package com.stctv.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.stctv.enums.Locators;
import com.stctv.utils.CommonAssertions;

public class MyTest extends BaseTest	{

	
	static final List<String> expectedPlansTypeList = Arrays.asList("Basic", "Premium");
	@Test
	public void firstTest() {
	
		landingPage.switchToEnglishSection().switchCountry(Locators.Bahrain);
		verifyPriceAndCurrency(Locators.Bahrain, "3", "BHD/month");
	    verifyPriceAndCurrency(Locators.Kuwait, "2.5", "KWD/month");
	    verifyPriceAndCurrency(Locators.Ksa, "15", "SAR/month");
	}
	
	private void verifyPriceAndCurrency(Locators country, String expectedPrice, String expectedCurrency) {
	    landingPage.switchCountry(country);
	    String actualPrice = (String) plansPage.verifyItemsofPlansPage(Locators.Price);
	    String actualCurrency = (String) plansPage.verifyItemsofPlansPage(Locators.Currency);
	     ArrayList<String> actualPlansTypeList = (ArrayList<String>) plansPage.verifyItemsofPlansPage(Locators.PlansType);
	  
	    CommonAssertions.validateLists(actualPlansTypeList, expectedPlansTypeList, "Plans Matched", "Plans UnMatched");
	    CommonAssertions.verifyEquals(actualPrice, expectedPrice, 
	        "Verified that price for " + country + " is equal to " + expectedPrice, "Price differs");
	    CommonAssertions.verifyEquals(actualCurrency, expectedCurrency, 
	        "Verified that currency for " + country + " is " + expectedCurrency, "Currency not matching");
	}


}
