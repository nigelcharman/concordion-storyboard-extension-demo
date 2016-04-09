package org.concordion.ext.driver.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Manages the browser session.
 */
public class Browser {
	private WebDriver driver = null;

	public void quit() {
		driver.quit();
	}

	public boolean isOpen() {
		return driver != null;
	}
	
	public WebDriver open() {
		if (driver == null) {
			driver = new FirefoxDriver();
//			
//			EventFiringWebDriver efwd = new EventFiringWebDriver(driver);
//			efwd.register(new SeleniumEventLogger());
//			driver = efwd;	
		}
		
		return driver;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void close() {
		driver.quit();
		driver = null;
	}
	
	
	@Override
	protected void finalize() throws Throwable {
		if (isOpen()){
			close();
		}
		
		super.finalize();
	}
}
