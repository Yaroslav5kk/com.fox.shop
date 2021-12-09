package com.fox.menu.bot.merchant.ui.view;

public class HtmlElementGenerator {

    public static StringBuilder bold(final String input) {
        return new StringBuilder("<b>").append(input).append("</b>");
    }

    public static StringBuilder strong(final String input) {
        return new StringBuilder("<strong>").append(input).append("</strong>");
    }

    public static StringBuilder italic(final String input) {
        return new StringBuilder("<i>").append(input).append("</i>");
    }

    public static StringBuilder underline(final String input) {
        return new StringBuilder("<u>").append(input).append("</u>");
    }

    public static StringBuilder del(final String input) {
        return new StringBuilder("<del>").append(input).append("</del>");
    }

    public static StringBuilder href(final String url, final String message) {
        return new StringBuilder("<a href='").append(url).append("'>").append(message).append("</a>");
    }

    public static StringBuilder hrefGoogleMaps(
            final String longitude,
            final String latitude,
            final String message
    ) {
        return new StringBuilder("<a href='https://www.google.com/maps/search/?api=1&query=")
                .append(longitude)
                .append(", ")
                .append(latitude)
                .append("'>")
                .append(message)
                .append("</a>");
    }

    public static StringBuilder code(final String input) {
        return new StringBuilder("<code>").append(input).append("</code>");
    }

    public static StringBuilder br() {
        return new StringBuilder("<br/>");
    }

}
