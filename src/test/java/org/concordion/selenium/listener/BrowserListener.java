package org.concordion.selenium.listener;

public interface BrowserListener {

    public abstract void pageUpdated(PageUpdatedEvent contentUpdatedEvent);

    public abstract void pageLoaded(PageLoadedEvent pageLoadedEvent);

}
