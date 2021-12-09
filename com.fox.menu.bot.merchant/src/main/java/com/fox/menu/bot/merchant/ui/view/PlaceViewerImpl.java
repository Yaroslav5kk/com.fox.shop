package com.fox.menu.bot.merchant.ui.view;

import com.fox.menu.bot.merchant.ui.view.i.PlaceViewer;
import com.fox.menu.protocol.PlaceModel;
import org.springframework.stereotype.Component;

import static com.fox.menu.bot.merchant.ui.view.HtmlElementGenerator.bold;
import static com.fox.menu.bot.merchant.ui.view.HtmlElementGenerator.italic;

@Component
public class PlaceViewerImpl implements PlaceViewer {

    @Override
    public String checkOfAvailableTitle() {
        return bold("please confirm available places")
                .toString();
    }

    @Override
    public String beginBeck() {
        return bold("please chose action")
                .toString();
    }

    @Override
    public String successConfirmedPlace() {
        return bold("success confirmed place")
                .toString();
    }

    @Override
    public String emptyAvailablePlacesTitle() {
        return bold("place enter available place id")
                .toString();
    }

    @Override
    public String view(final PlaceModel place) {
        return bold("id on map: ").append(italic(place.getIdOnMap()))
                .append("\n")
                .append(bold("location: ")).append(italic(place.getLocationType().getValue()))
                .append("\n")
                .append(bold("description: ")).append(italic(place.getDescription()))
                .toString();
    }
}
