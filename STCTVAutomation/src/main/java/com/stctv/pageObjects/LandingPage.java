package com.stctv.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.stctv.enums.Locators;
import com.stctv.utils.ReportUtil;

public class LandingPage extends BasePage{
     WebDriver driver;
     
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath="//*[contains(@class,'head-links')]//*[@class='hide-mobile'] | //span[contains(text(),'English')]")
	private WebElement englishLink;
	
	@FindBy(xpath="//*[contains(text(),'Choose Your Plan')]")
	private WebElement choosePlanHeading;
	
	@FindBy(xpath="//*[@data-test-id='header-country-switch']")
	private WebElement switchCountryBtn;
	
	@FindBy(xpath="//*[contains(@data-testid,'country-picker')]//p")
	private List<WebElement> countries;
	
	
	
	public LandingPage switchToEnglishSection() {
		ClickElement(englishLink);
		WaitUntilVisible(choosePlanHeading);
		ReportUtil.addScreenShot("Switched to English Section");
		return this;
	}
	
	public void switchCountry(Locators countryName) {
		ClickElement(switchCountryBtn);
		shortWait();
		switch(countryName) {
		case Bahrain:
		   chooseFromList(countries, countryName.toString().toLowerCase());
		   break;
		case Kuwait:
			chooseFromList(countries, countryName.toString().toLowerCase());
			break;
		case Ksa:
			chooseFromList(countries, countryName.toString().toLowerCase());
			break;
		default : 
			
		}
		ReportUtil.addScreenShot("Switching to : " + countryName.toString());
	}

	

	

	
	
	
}
