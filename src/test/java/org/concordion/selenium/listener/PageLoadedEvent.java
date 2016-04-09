package org.concordion.selenium.listener;

public class PageLoadedEvent {
    private String pageName;
    private String description;

    public PageLoadedEvent(String pageName, String description) {
        this.pageName = pageName;
        this.description = description;
    }

    public String getPageName() {
        return pageName;
    }

    public String getDescription() {
        return description;
    }
}