package org.concordion.selenium.listener;

public class PageUpdatedEvent {
    private String pageName;
    private String description;

    public PageUpdatedEvent(String pageName, String description) {
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