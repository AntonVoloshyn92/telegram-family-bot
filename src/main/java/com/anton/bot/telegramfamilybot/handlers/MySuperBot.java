package com.anton.bot.telegramfamilybot.handlers;

import com.anton.bot.telegramfamilybot.OperationInComeObject;
import com.anton.bot.telegramfamilybot.income.InComeFinance;
import com.anton.bot.telegramfamilybot.utils.StringsValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.anton.bot.telegramfamilybot.utils.StringsValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.anton.bot.telegramfamilybot.utils.StringsValue.*;


public class MySuperBot extends TelegramLongPollingBot {

    private final String userName = "simple_family_finance_bot";
    private final String botToken = "1685117157:AAFTkP0R_UgA_pVU8NwOlpPLO9YndhKH6m8";
    private InComeFinance inComeFinance;

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            execute(getResponseMessage(message));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage getResponseMessage(Message message) {
        switch (message.getText()) {
            case INCOME:
                inComeFinance = new InComeFinance();
                return inComeFinance.createChoiceInCome(message);
            case WAGE:
                return inComeFinance.sendMessageToUser(message);
            case SNOWBOARD:
            case PRIZE:
            case ELSE:
                greetingMessage(message);
//                inComeFinance.saveInComeOperation(new OperationInComeObject());
            default:
                System.out.println("Price: " + message.getText());
                return greetingMessage(message);
        }
    }

    private ReplyKeyboardMarkup getMailMenu() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        KeyboardRow row1 = new KeyboardRow();
        row1.add(INCOME);
        row1.add(OUTCOME);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        keyboardRowList.add(row1);

        markup.setKeyboard(keyboardRowList);
        return markup;
    }

/*    private SendMessage getCurrentTimeResponse(Message message) {
        SendMessage response = new SendMessage();
        response.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        response.setChatId(message.getChatId());
        response.setReplyMarkup(getMailMenu());
        return response;
    }*/

    private SendMessage greetingMessage(Message message) {
        SendMessage response = new SendMessage();
        response.setText("Спасибо :)");
        response.setChatId(message.getChatId());
        response.setReplyMarkup(getMailMenu());
        return response;
    }


}
