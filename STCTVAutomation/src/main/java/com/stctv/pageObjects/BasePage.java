package com.stctv.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.stctv.utils.PageActions;

public class BasePage extends PageActions {

	WebDriver driver;

	public BasePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	
	
	
	
	
	
	
	
}
