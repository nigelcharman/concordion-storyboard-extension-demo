package demo.driver.google.web;

import org.concordion.selenium.Browser;
import org.concordion.selenium.listener.BrowserListener;
import org.concordion.selenium.listener.PageLoadedEvent;
import org.concordion.selenium.listener.PageUpdatedEvent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * A WebDriver Page Object corresponding to the Google Search Page.
 */
public class GoogleSearchPage {

    @CacheLookup
    @FindBy(name = "q")
    private WebElement queryBox;

    @CacheLookup
    @FindBy(name = "btnG")
    private WebElement submitButton;

    @FindBy(className = "nonExistent")
    private WebElement nonExistentLink;

    private final BrowserListener listener;
    private final Browser browser;

    /**
     * Opens the Google Search Page.
     */
    public GoogleSearchPage(Browser browser, BrowserListener listener) {
        this.browser = browser;
        this.listener = listener;

        WebDriver driver = browser.getDriver();
        PageFactory.initElements(driver, this);
        driver.get("http://www.google.com");

        listener.pageLoaded(new PageLoadedEvent(this.getClass().getSimpleName(), "Opened Google's web page"));
    }

    /**
     * Searches for the specified string and opens the results page, waiting for the page to fully load.
     */
    public GoogleResultsPage searchFor(String query) {
        queryBox.sendKeys(query);
        queryBox.sendKeys(Keys.ESCAPE);
        String description = "Entered search text, and about to click search button";
        listener.pageUpdated(new PageUpdatedEvent(this.getClass().getSimpleName(), description));
        submitButton.click();
        return new GoogleResultsPage(browser, listener);
    }
}