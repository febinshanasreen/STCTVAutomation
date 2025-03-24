package com.stctv.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class PageActions {

	protected WebDriver driver;
	protected FluentWait<WebDriver> waiter, invisibleWaiter;
	protected SoftAssert softAssert;
	WebDriverWait wait;

	public PageActions(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		int waitTimeout = Integer.parseInt(TestProperties.getProperty("waitTimeout"));
		waiter = new FluentWait<>(driver).ignoring(NoSuchElementException.class, WebDriverException.class)
				.withTimeout(Duration.ofSeconds(waitTimeout)).pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class).ignoring(TimeoutException.class);

		softAssert = new SoftAssert();
	}

	public void ActionsClickElement(WebElement element) {
		WaitUntilClickable(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}

	public void ClearTextboxWithBackspace(WebElement element) {
		WaitUntilClickable(element);

		String fieldValue = element.getAttribute("value");

		try {
			for (int i = 0; i < fieldValue.length(); i++) {
				element.sendKeys(Keys.BACK_SPACE);
			}
		} catch (Exception e) {
			doSoftAssertFailure("Failed adding characters to field");
		}

	}

	public void ClickElement(WebElement element) {
		SteadyWait(element);
		retryingFindClick(element);
	}

	public void DomWait() {

		try {
			this.waiter.until(
					d -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
		} catch (Exception e) {
			doSoftAssertFailure("Failed waiting for DOM load to complete");
		}

	}

	public void doSoftAssertFailure(String msg) {
		System.out.println(msg);
		softAssert.assertTrue(false, msg);
	}

	/**
	 * Check if an element exists
	 * 
	 * @param element the element to look for
	 * @return true if found/false if not
	 */
	public boolean Exists(WebElement element) {
		try {
			WaitUntilVisible(element);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String GetElementText(WebElement element) {

		try {
			String elementText = WaitUntilVisible(element).getText();

			return elementText;
		} catch (Exception e) {
			doSoftAssertFailure("Failed to get text from element");
			return "";
		}

	}

	public void JavaScriptClickElement(WebElement element) {
		WaitUntilClickable(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void JavaScriptSetTextBox(WebElement element, String value) {
		WaitUntilVisible(element);

		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].value='" + value + "';", element);
		} catch (Exception e) {
			doSoftAssertFailure(String.format("Could not set element text to '%s'", value));
		}

	}

	public void GetAssertFailures() {
		softAssert.assertAll();
	}

	public WebElement MoveToElement(WebElement element) {
		WaitUntilVisible(element);

		try {
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
			javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			doSoftAssertFailure("Failed to move to element");
		}

		return element;
	}

	public void retryingFindClick(WebElement element) {
		int maxRetries = 100;
		int attempts = 0;
		while (attempts < maxRetries) {
			try {
				WaitUntilEnabled(element).click();
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}

	public void ScrollToView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void ScrollUp() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-250)");
	}

	public void ScrollToTop() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void SetTextBox(WebElement element, String textToAdd) {
		WaitUntilVisible(element);

		MoveToElement(element).clear();

		try {
			element.click();
//			element.clear();
			ClearTextboxWithBackspace(element);
			element.sendKeys(textToAdd);
		} catch (Exception e) {
			doSoftAssertFailure(String.format("Could not set element text to '%s'", textToAdd));
		}

	}

	public Boolean SteadyWait(final List<WebElement> element) {
		String domWait = TestProperties.getProperty("domWait").toLowerCase();
		String steadyWait = TestProperties.getProperty("domWait").toLowerCase();

		if (domWait.equals("y"))
			DomWait();

		if (steadyWait.equals("y")) {
			return OverrideExpectedCondition(element.get(0));
		} else {
			return true;
		}
	}

	public Boolean SteadyWait(final WebElement element) {
		String domWait = TestProperties.getProperty("domWait").toLowerCase();
		String steadyWait = TestProperties.getProperty("steadyWait").toLowerCase();

		if (domWait.equals("y"))
			DomWait();

		if (steadyWait.equals("y")) {
			return OverrideExpectedCondition(element);
		} else {
			return true;
		}
	}

	public Boolean OverrideExpectedCondition(WebElement element) {
		return waiter.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				try {
					element.isDisplayed();
					return true;
				} catch (StaleElementReferenceException e) {
					doSoftAssertFailure("Failed waiting for element to be ready");
					return false;
				}
			}
		});
	}

	public WebElement WaitUntilClickable(WebElement element) {
		WaitUntilEnabled(element);

		try {
			this.waiter.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			doSoftAssertFailure("WaitUntilClickable -> Element did not become clickable");
		}

		return element;
	}

	private WebElement WaitUntilEnabled(WebElement element) {
		SteadyWait(element);

		try {
			this.waiter.until(v -> element.isEnabled() && element.isDisplayed());

		} catch (Exception e) {
			doSoftAssertFailure("WaitUntilPresent -> Element was not enabled");
		}

		return element;
	}

	public WebElement WaitUntilVisible(WebElement element) {

		SteadyWait(element);

		try {
			this.waiter.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			doSoftAssertFailure("WaitUntilVisible -> Element did not become visible");
		}

		return element;
	}
	
	public void chooseFromList(List<WebElement> list, String value) {
		for(WebElement element : list) {
			if(element.getText().equalsIgnoreCase(value)) {
				ClickElement(element);
				break;
			}
		}
	}
	
	public List<String> getListofTexts(List<WebElement> list) {
		List<String> textList = new ArrayList<>();
		list.forEach(element->{
			textList.add(element.getText());
		});
		
		return textList;
	}
	
	

	public void shortWait() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
