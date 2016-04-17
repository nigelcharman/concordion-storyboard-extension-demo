package demo;

import java.io.IOException;

import org.concordion.ext.service.listener.SuccessResponseEvent;

import demo.driver.google.web.GoogleResultsPage;
import demo.driver.google.web.GoogleSearchPage;
import demo.driver.http.HttpDriver;

/**
 * A fixture class for the StoryboardDemo.md specification.
 * <p>
 * This adds the Storyboard Extension to Concordion to add a storyboard to each example.
 * <p>
 * Two examples are included, a browser UI example using WebDriver, and a basic web service performing a HTTP GET.
 * <p>
 * Run this class as a JUnit test to produce the Concordion results.
 */
public class StoryboardDemoFixture extends FixtureBase {

    private GoogleSearchPage searchPage;
    private GoogleResultsPage resultsPage;

    /**
     * Searches for the specified topic, and waits for the results page to load.
     */
    public void searchFor(final String topic) {
        searchPage = new GoogleSearchPage(getBrowser(), getBrowserListener());
        resultsPage = searchPage.searchFor(topic);
    }

    /**
     * Returns the result from Google calculation.
     */
    public String getCalculatorResult() {
        return resultsPage.getCalculatorResult();
    }

    public boolean makeRestCall(String url) throws IOException {
        String responseMessage = new HttpDriver().get(url);
        getServiceListener().successResponse(new SuccessResponseEvent(responseMessage));

        return !responseMessage.isEmpty();
    }
    
    public String searchForTopic(String topic) {
    	getStoryboard()
    		.addSectionContainer(topic)
    		.setSkipFinalScreenshotForCurrentExample();
    	
    	String result = new GoogleSearchPage(getBrowser(), getBrowserListener()).searchFor(topic).getCalculatorResult();
    	
    	getStoryboard()
    		.addScreenshot("Example Completed", "")
    		.closeContainer();
    	
    	return result;
    }
}
