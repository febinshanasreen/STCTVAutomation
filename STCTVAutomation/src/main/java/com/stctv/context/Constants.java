package com.stctv.context;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Constants {

	public static final String workingDirectory = System.getProperty("user.dir");
	public static final String configFILEPATH= workingDirectory+"/src/main/resources/Configurations/config.properties";
	 
		public final static String EXTENTREPORT_DIR = workingDirectory + "/ExtentReports";
	public final static String EXTENT_CONFIG_PATH = String
				.format("%s/src/test/resources/extentConfig/extent-config.xml", workingDirectory);

	public final static String PROJECT_NAME = "STCTV Automation Framework";
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	

}
