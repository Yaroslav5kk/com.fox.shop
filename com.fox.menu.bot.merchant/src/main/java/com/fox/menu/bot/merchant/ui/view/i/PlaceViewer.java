package com.fox.menu.bot.merchant.ui.view.i;

import com.fox.menu.protocol.PlaceModel;

public interface PlaceViewer {
    String checkOfAvailableTitle();

    String beginBeck();

    String successConfirmedPlace();

    String emptyAvailablePlacesTitle();

    String view(PlaceModel place);
}
