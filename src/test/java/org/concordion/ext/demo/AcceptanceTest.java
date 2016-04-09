package org.concordion.ext.demo;

import org.concordion.api.AfterExample;
import org.concordion.api.AfterSpecification;
import org.concordion.api.BeforeExample;
import org.concordion.api.BeforeSpecification;
import org.concordion.api.ConcordionScoped;
import org.concordion.api.FailFast;
import org.concordion.api.Scope;
import org.concordion.api.ScopedObjectHolder;
import org.concordion.api.extension.Extension;
import org.concordion.ext.StoryboardExtension;
import org.concordion.ext.driver.web.Browser;
import org.concordion.ext.driver.web.SeleniumScreenshotTaker;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A base class for Google search tests that opens up the Google site at the Google search page, and closes the browser once the test is complete.
 */
@RunWith(ConcordionRunner.class)
@FailFast
public abstract class AcceptanceTest {

//	@ConcordionScoped(Scope.SPECIFICATION)
//	private ScopedObjectHolder<Browser> browser = new ScopedObjectHolder<Browser>() {
//		@Override
//		public Browser create() {
//			return new Browser();
//		}
//	};
	
	private Browser browser = new Browser();
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Extension
	private StoryboardExtension storyboard = new StoryboardExtension();

	public Logger getLogger() {
		return logger;
	}

	public StoryboardExtension getStoryboard() {
		return storyboard;
	}

	public boolean isBrowserOpen() {
		return browser.isOpen();
	}

	public WebDriver getBrowser() {
		if (!browser.isOpen()) {
			browser.open();
		}
		
		if (!storyboard.hasScreenshotTaker()) {
			logger.info("SET STORYBOARD SCREENSHOT TAKER");
			storyboard.setScreenshotTaker(new SeleniumScreenshotTaker(browser.getDriver()), Scope.EXAMPLE);
		}

		return browser.getDriver();
	}

	public void closeBrowser() {
		if (browser.isOpen()) {
			logger.info("CLOSE BROWSER");
			browser.close();
			storyboard.removeScreenshotTaker();
		}
	}

	@BeforeExample
	public void before() {
		logger.info("BEFORE EXAMPLE: BROWSER OPEN = {}", browser.isOpen());
	}
	
	@AfterExample
	public void after() {
		logger.info("AFTER EXAMPLE: BROWSER OPEN = {}", browser.isOpen());
	}
	@BeforeSpecification
	public void startUpTest() {
		logger.info("Initialising the acceptance test class {} on thread {}", this.getClass().getSimpleName(), Thread.currentThread().getName());
	}

	@AfterSpecification
	public void tearDownTest() {
		logger.info("AFTER SPECIFICATION: BROWSER OPEN = {}", browser.isOpen());
		closeBrowser();

		logger.info("Tearing down the acceptance test class on thread {}", Thread.currentThread().getName());
	}
	
	
}
