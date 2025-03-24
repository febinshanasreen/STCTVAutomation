package com.stctv.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.stctv.enums.Locators;

public class PlansPage extends BasePage {

	WebDriver driver;

	public PlansPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@data-test-id='tier-title']")
	private List<WebElement> plansType;

	@FindBy(xpath = "//*[@data-test-id='tier-price']/b")
	private WebElement price;

	@FindBy(xpath = "//*[@data-test-id='tier-price']/i")
	private WebElement currency;

	public Object verifyItemsofPlansPage(Locators name) {
			switch(name) {
			case PlansType :
				return getListofTexts(plansType);
			case Currency :
				return GetElementText(currency);	
			case Price:
				return GetElementText(price);
			default :
				return  null;
			}
			
			
		}

}
