package com.stctv.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.stctv.Listeners.ReportListener;
import com.stctv.context.DriverManager;
import com.stctv.pageObjects.LandingPage;
import com.stctv.pageObjects.PlansPage;
import com.stctv.utils.TestProperties;

@Listeners({ ReportListener.class })
public class BaseTest {

	WebDriver driver = null;

	@BeforeMethod
	public void initializeDriver() throws IOException {
		TestProperties.loadAllProperties();
		String browserName =TestProperties.getProperty("browserName");
		System.out.println(browserName);

	   String env = TestProperties.getProperty("Environment");
		System.out.println(env);
		String URL =  TestProperties.getProperty(env);
		System.out.println("Executing in : " + env);

		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		else {
			System.out.println("not a valid browser");
		}
		initPages();
		DriverManager.setDriver(driver);

		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		DriverManager.getDriver().manage().window().maximize();

		DriverManager.getDriver().get(URL);

	}

	/**
	 * Initializing pages
	 */

	public LandingPage landingPage;
	public PlansPage plansPage;
	

	public void initPages() {

		landingPage = new LandingPage(driver);
		plansPage = new PlansPage(driver);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
