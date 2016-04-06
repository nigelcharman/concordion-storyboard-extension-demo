package org.concordion.ext.demo;

import org.concordion.api.AfterSpecification;
import org.concordion.api.BeforeSpecification;
import org.concordion.api.FailFast;
import org.concordion.api.extension.Extension;
import org.concordion.ext.StoryboardExtension;
import org.concordion.ext.driver.web.Browser;
import org.concordion.ext.driver.web.SeleniumScreenshotTaker;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A base class for Google search tests that opens up the Google site at the Google search page, and closes the browser once the test is complete.
 */
@RunWith(ConcordionRunner.class)
@FailFast
public abstract class AcceptanceTest {

	private Browser browser = new Browser();
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Extension
	private final StoryboardExtension storyboard = new StoryboardExtension();

	public Logger getLogger() {
		return logger;
	}

	public StoryboardExtension getStoryboard() {
		return storyboard;
	}

	public boolean isBrowserOpen() {
		return browser.isOpen();
	}

	public Browser getBrowser() {
		if (!browser.isOpen()) {
			browser.open();
		}
		
		if (!storyboard.hasScreenshotTaker()) {
			storyboard.setScreenshotTaker(new SeleniumScreenshotTaker(browser.getDriver()));
		}

		return browser;
	}

	public void closeBrowser() {
		if (browser.isOpen()) {
			browser.close();
			storyboard.setScreenshotTaker(null);
		}
	}

	@BeforeSpecification
	public void startUpTest() {
		logger.info("Initialising the acceptance test class {} on thread {}", this.getClass().getSimpleName(), Thread.currentThread().getName());
	}

	@AfterSpecification
	public void tearDownTest() {
		closeBrowser();

		logger.info("Tearing down the acceptance test class on thread {}", Thread.currentThread().getName());
	}
}
