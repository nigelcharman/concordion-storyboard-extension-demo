package org.concordion.ext.storyboard.listener;

import org.concordion.ext.StoryboardExtension;
import org.concordion.ext.service.listener.ServiceListener;
import org.concordion.ext.service.listener.SuccessResponseEvent;
import org.concordion.ext.storyboard.CardResult;
import org.concordion.ext.storyboard.StockCardImage;

public class StorycardCreatingServiceListener implements ServiceListener {

    private StoryboardExtension storyboard;

    public StorycardCreatingServiceListener(StoryboardExtension storyboard) {
        this.storyboard = storyboard;
    }

    @Override
    public void successResponse(SuccessResponseEvent parameterObject) {
        storyboard.addNotification("Response", "Click image to see response", parameterObject.getResponseMessage(),
                "json", StockCardImage.JSON, CardResult.SUCCESS);
    }
}
