package com.anton.bot.telegramfamilybot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static com.anton.bot.telegramfamilybot.utils.StringsValue.INCOME;
import static com.anton.bot.telegramfamilybot.utils.StringsValue.OUTCOME;

public class ListKeyMap {
    public static ReplyKeyboardMarkup getMailMenu() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        KeyboardRow row1 = new KeyboardRow();
        row1.add(INCOME);
        row1.add(OUTCOME);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        keyboardRowList.add(row1);

        markup.setKeyboard(keyboardRowList);
        return markup;
    }

    public static ReplyKeyboardMarkup getInComeMenu() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(StringsValue.WAGE);
        row1.add(StringsValue.SNOWBOARD);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(StringsValue.PRIZE);
        row2.add(StringsValue.ELSE);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        keyboardRowList.add(row1);
        keyboardRowList.add(row2);

        markup.setKeyboard(keyboardRowList);
        return markup;
    }
}
