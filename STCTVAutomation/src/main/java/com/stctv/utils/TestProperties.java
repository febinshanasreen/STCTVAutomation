package com.stctv.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.stctv.context.Constants;

public class TestProperties {

	/** The Constant props. */
	private static final Properties props = new Properties();


	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	public static void loadAllProperties() {
		try {
			FileInputStream Locator;
			Locator = new FileInputStream(Constants.configFILEPATH);
			props.load(Locator);
		} catch (IOException e) {
			
		}
	}

	
	public static void putProperty(String key, String value) {
		props.put(key, value);
	}

	public static void setProperty(String key, String value) {
		props.setProperty(key, value);
	}
}
