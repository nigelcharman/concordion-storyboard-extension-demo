package org.concordion.ext.storyboard.listener;

import org.concordion.ext.StoryboardExtension;
import org.concordion.selenium.listener.BrowserListener;
import org.concordion.selenium.listener.PageLoadedEvent;
import org.concordion.selenium.listener.PageUpdatedEvent;

public class StorycardCreatingBrowserListener implements BrowserListener {

    private StoryboardExtension storyboard;

    public StorycardCreatingBrowserListener(StoryboardExtension storyboard) {
        this.storyboard = storyboard;
    }

    @Override
    public void pageLoaded(PageLoadedEvent pageLoadedEvent) {
        storyboard.addScreenshot(pageLoadedEvent.getPageName(), pageLoadedEvent.getDescription());
    }

    @Override
    public void pageUpdated(PageUpdatedEvent contentUpdatedEvent) {
        storyboard.addScreenshot(contentUpdatedEvent.getPageName(), contentUpdatedEvent.getDescription());
    }
}
