package demo;

import org.concordion.api.AfterExample;
import org.concordion.api.ConcordionScoped;
import org.concordion.api.FailFast;
import org.concordion.api.Scope;
import org.concordion.api.ScopedObjectHolder;
import org.concordion.api.extension.Extension;
import org.concordion.ext.StoryboardExtension;
import org.concordion.ext.service.listener.ServiceListener;
import org.concordion.ext.storyboard.listener.StorycardCreatingBrowserListener;
import org.concordion.ext.storyboard.listener.StorycardCreatingServiceListener;
import org.concordion.integration.junit4.ConcordionRunner;
import org.concordion.selenium.Browser;
import org.concordion.selenium.SeleniumScreenshotTaker;
import org.concordion.selenium.listener.BrowserListener;
import org.junit.runner.RunWith;

/**
 * A base class for Concordion fixtures using the Storyboard extension.
 * <p/>
 * Manages the lifecycle of Selenium WebDriver browsers and supplies a listener that adds storycards on page events.
 * <p/>
 * Also supplies a listener that adds storycards on web service events.
 */
@RunWith(ConcordionRunner.class)
@FailFast
public abstract class FixtureBase {

    @Extension
    private StoryboardExtension storyboard = new StoryboardExtension();

    @ConcordionScoped(Scope.SPECIFICATION)
    private ScopedObjectHolder<Browser> browserHolder = new ScopedObjectHolder<Browser>() {
        @Override
        public Browser create() {
            Browser browser = new Browser();
            return browser;
        }

        @Override
        protected void destroy(Browser browser) {
            storyboard.removeScreenshotTaker();
            browser.close();
        };
    };

    protected Browser getBrowser() {
        Browser browser = browserHolder.get();
        
        if (!storyboard.hasScreenshotTaker()) {
        	storyboard.setScreenshotTaker(new SeleniumScreenshotTaker(browser));
        }
        
        return browser;
    }
    
    protected StoryboardExtension getStoryboard() {
    	return storyboard;
    }
    
    @AfterExample
    private final void afterExample() {
    	// Remove screenshot taker so that next example will not get the example 
    	// completed screenshot unless it has used the browser 
    	storyboard.removeScreenshotTaker();
    }

    protected BrowserListener getBrowserListener() {
        return new StorycardCreatingBrowserListener(storyboard);
    }

    protected ServiceListener getServiceListener() {
        return new StorycardCreatingServiceListener(storyboard);
    }
}
