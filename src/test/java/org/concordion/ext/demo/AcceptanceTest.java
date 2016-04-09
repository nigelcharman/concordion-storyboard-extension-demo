package org.concordion.ext.demo;

import org.concordion.api.AfterExample;
import org.concordion.api.AfterSpecification;
import org.concordion.api.AfterSuite;
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

	@ConcordionScoped(Scope.SPECIFICATION)
    private ScopedObjectHolder<Browser> browserHolder = new ScopedObjectHolder<Browser>() {
        @Override
        public Browser create() {
            Browser browser = new Browser();
            return browser;
        }

        @Override
        protected void destroy(Browser browser) {
            storyboard.setScreenshotTaker(null);       
            browserHolder.get().close();
        };
    };
    
	
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
		return browserHolder.get().isOpen();
	}

	public WebDriver getBrowser() {
		if (!browserHolder.get().isOpen()) {
			browserHolder.get().open();
		}
		
		if (!storyboard.hasScreenshotTaker()) {
			storyboard.setScreenshotTaker(new SeleniumScreenshotTaker(browserHolder.get().getDriver()));
		}

		return browserHolder.get().getDriver();
	}

	public void closeBrowser() {
		if (browserHolder.get().isOpen()) {
			browserHolder.get().close();
			storyboard.setScreenshotTaker(null);
		}
	}
	
	@AfterExample
	public void after() {
		storyboard.setScreenshotTaker(null);
	}
}
