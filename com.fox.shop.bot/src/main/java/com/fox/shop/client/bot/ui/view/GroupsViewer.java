package com.fox.shop.client.bot.ui.view;

import com.fox.shop.protocol.ProductGroupModel;

import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.bold;

public class GroupsViewer {

    public static String viewProductGroup(final ProductGroupModel productGroupModel) {
        return bold(productGroupModel.getName()).toString();
    }
}
