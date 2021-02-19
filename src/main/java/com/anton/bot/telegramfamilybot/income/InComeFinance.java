package com.anton.bot.telegramfamilybot.income;

import com.anton.bot.telegramfamilybot.OperationInComeObject;
import com.anton.bot.telegramfamilybot.utils.StringsValue;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class InComeFinance {


    public SendMessage createChoiceInCome(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Выбери категорию расходов");
        sendMessage.setReplyMarkup(getInComeMenu());
        sendMessage.setChatId(message.getChatId());
        return sendMessage;
    }


    private ReplyKeyboardMarkup getInComeMenu() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(StringsValue.WAGE);
        row1.add(StringsValue.SNOWBOARD);

        KeyboardRow row2 = new KeyboardRow();
        row1.add(StringsValue.PRIZE);
        row1.add(StringsValue.ELSE);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        keyboardRowList.add(row1);
        keyboardRowList.add(row2);

        markup.setKeyboard(keyboardRowList);
        return markup;
    }


    //todo save into table Operation in came finance
    public void saveInComeOperation(OperationInComeObject operationInComeObject) {
        System.out.println();
    }

    public SendMessage sendMessageToUser(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Введите сумму: ");
        sendMessage.setChatId(message.getChatId());
        return sendMessage;
    }
}
