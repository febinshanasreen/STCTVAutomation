package com.stctv.context;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriver() {
		if(driverInstance.get()==null) {
	        throw new IllegalArgumentException("Alert!!Driver is not found!!!");
		}
		else {
			return driverInstance.get();
		}
		
	}
	
	
	public static  void setDriver(WebDriver driver) {
		driverInstance.set(driver);
	}
}
