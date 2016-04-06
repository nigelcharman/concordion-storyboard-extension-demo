package org.concordion.ext.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.concordion.ext.driver.page.GoogleResultsPage;
import org.concordion.ext.driver.page.GoogleSearchPage;
import org.concordion.ext.driver.web.SeleniumScreenshotTaker;
import org.concordion.ext.storyboard.CardResult;
import org.concordion.ext.storyboard.StockCardImage;
import org.omg.PortableInterceptor.SUCCESSFUL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A fixture class for the ScreenshotDemo.html specification.
 * <p>
 * This adds the Screenshot Extension to Concordion to include screenshots on error in the Concordion output. By default this extension uses java.awt.Robot to
 * take the screenshot.
 * <p>
 * To include just the browser screen in the results, we configure the extension using the {@link ScreenshotExtensionFactory} and
 * {@link SeleniumScreenshotTaker} to take screenshots using WebDriver's TakesScreenshot interface.
 * <p>
 * Run this class as a JUnit test to produce the Concordion results. The test is expected to fail, since Google Calculator doesn't special case the answer to
 * life, the universe and everything.
 */
public class StoryboardDemo extends AcceptanceTest {

	private GoogleSearchPage searchPage;
	private GoogleResultsPage resultsPage;

	/**
	 * Searches for the specified topic, and waits for the results page to load.
	 */
	public void searchFor(final String topic) {
		searchPage = new GoogleSearchPage(this);
		resultsPage = searchPage.searchFor(topic);
	}

	/**
	 * Returns the result from Google calculation.
	 */
	public String getCalculatorResult() {
		return resultsPage.getCalculatorResult();
	}
	
	public boolean makeRestCall(String url) throws IOException {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		
		StringBuilder response = new StringBuilder();
		
		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String line;
			
			while ((line = in.readLine()) != null) {
				response.append(line + "\n");
			}
		}

		getStoryboard().addNotification("Response", "Click image to see response", response.toString(), "json", StockCardImage.JSON, CardResult.SUCCESS);
		
		return response.length() > 0;
	}
}
