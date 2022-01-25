package com.fox.shop.client.bot.ui.view;

public class SearchViewer {

    public static String searchTitle() {
        return HtmlElementGenerator.bold("Available methods of search:").
                append("\n").
                append(HtmlElementGenerator.italic("---Select from product group.")).
                append("\n").
                append(HtmlElementGenerator.italic("---Enter that you want.")).
                toString();
    }

    public static String searchMerchantTitle() {
        return HtmlElementGenerator.bold("Enter that you want.").
            toString();
    }

    public static String searchProductTitle() {
        return HtmlElementGenerator.bold("Напиши, що бажаєш, а ми підберемо! \uD83D\uDE09").
            toString();
    }
}
