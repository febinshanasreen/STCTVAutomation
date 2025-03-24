package com.stctv.manager;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.stctv.context.Constants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ExtentReportManager {
	/** The extent reports. */
	private volatile static ExtentReports extentReports;


	/** The map. */
	private static Map<Long, ExtentTest> map = new HashMap<>();
	
	
	/**
	 * Gets the current test.
	 *
	 * @return the current test
	 */
	public synchronized static ExtentTest getCurrentTest() {
		return map.get(Thread.currentThread().getId());
	}
	

	/**
	 * Gets the extent reports.
	 *
	 * @return the extent reports
	 * @throws IOException
	 */
	public static ExtentReports getExtentReports() {
		
		if (extentReports == null) {
			synchronized(ExtentReportManager.class) {
				System.out.println("**************************************");
				ExtentSparkReporter reporter = new ExtentSparkReporter(Constants.EXTENTREPORT_DIR+"/"+"AutomationResult."+getTimestamp()+".html");
				extentReports = new ExtentReports();
				reporter.config().setReportName(Constants.PROJECT_NAME);
				try {
					reporter.loadXMLConfig(new File(Constants.EXTENT_CONFIG_PATH));
				} catch (IOException e) {
					e.printStackTrace();
				}
				extentReports.attachReporter(reporter);
				processReportEnvironments();
			}
			}
			
		return extentReports;
	}




	public synchronized static void startTest(String testName, String desc) {
		ExtentTest test = getExtentReports().createTest(testName, desc);
		map.put(Thread.currentThread().getId(), test);
	}
	
	
	public static void processReportEnvironments() {
	    extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
	}
	
	public static String getTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return Constants.sdf.format(timestamp);
				
}

}