package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.model.types.CommandData;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class DataToKeyboardBuilder {

    public static List<Pair<?, ?>> buildTextDataToKeyboardEnterOrFromSavedTag() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        return textDataToKeyboard;
    }

    public static List<Pair<?, ?>> buildTextDataToKeyboardAddTagOrFinish() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.START.getDescription(),
                CommandData.START.getValue()
                )
        );
        return textDataToKeyboard;
    }

    public static List<Pair<?, ?>> buildTextDataToKeyboardAddTagMenu() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();

        return textDataToKeyboard;
    }

    public static List<Pair<?, ?>> buildTextDataToKeyboardAddThemeMenu() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();

        return textDataToKeyboard;
    }

    public static List<Pair<?, ?>> buildTextDataToKeyboardCreateThemeFinish() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.START.getDescription(),
                CommandData.START.getValue()
                )
        );
        return textDataToKeyboard;
    }

    public static List<Pair<?, ?>> buildTextDataToKeyboardCreateTagFinish() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.START.getDescription(),
                CommandData.START.getValue()
                )
        );
        return textDataToKeyboard;
    }

    public static List<Pair<?, ?>> buildTextDataToKeyboardCreateArticleFinish() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.START.getDescription(),
                CommandData.START.getValue()
                )
        );
        return textDataToKeyboard;
    }

    public static List<Pair<?, ?>> buildTextDataToKeyboardViewArticlesLink(final String url) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();

        return textDataToKeyboard;
    }

    public static List<Pair<?, ?>> buildTextDataToKeyboardViewArticles(final Long articleId) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();

        return textDataToKeyboard;
    }

    public static List<Pair<?, ?>> buildTextDataToKeyboardGetArticleFinish() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.START.getDescription(),
                CommandData.START.getValue()
                )
        );
        return textDataToKeyboard;
    }

    public static List<Pair<?, ?>> buildTextDataToKeyboardGetArticleByAllMenu() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.FINISH.getDescription(),
                CommandData.FINISH.getValue()
                )
        );
        return textDataToKeyboard;
    }

    public static List<Pair<?, ?>> buildTextDataToKeyboardGetArticleMenu() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.START.getDescription(),
                CommandData.START.getValue()
                )
        );
        return textDataToKeyboard;
    }
}
