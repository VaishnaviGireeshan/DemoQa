package com.vaishnavi.DemoQA.sampletest;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwitchParentToChild {
	WebDriver wd;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		wd = new ChromeDriver();
		wait = new WebDriverWait(wd, Duration.ofSeconds(10));
		wd.get("https://demoqa.com/browser-windows");
		wd.manage().window().maximize();
	}

	@Test
	public void tabs() {
		// Identify the parent window
		String parentWindow = wd.getWindowHandle();
		WebElement newTabBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("tabButton")));
		newTabBtn.click();
		// Find all the handles associated with the webdriver instance
		Set<String> allHandles = wd.getWindowHandles();

		for (String handle : allHandles) {
			if (!parentWindow.equals(handle)) {
				wd.switchTo().window(handle);
			}
		}

		WebElement text = wd.findElement(By.id("sampleHeading"));
		System.out.println(text.getText()+" Working fine");

		// Switch to parent window
		wd.switchTo().window(parentWindow);
	}

	@AfterMethod
	public void tearDown() {
		wd.quit();
	}
}
