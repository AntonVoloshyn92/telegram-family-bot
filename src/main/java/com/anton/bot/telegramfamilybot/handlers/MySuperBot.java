package com.anton.bot.telegramfamilybot.handlers;

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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class MySuperBot extends TelegramLongPollingBot {

    private final String WHAT_TIME_REQUEST = "What the time?";
    private final String WHAT_DATE_REQUEST = "What the date?";
    private final String WHAT_GB_REQUEST = "What is the capital of GB?";
    private final String ORDER_PIZZA = "What is the capital of GB?";

//    @Autowired
//    @Value("@{application.userName}")
//    private String userName;//= "simple_family_finance_bot";
//
//    @Autowired
//    @Value("@{application.botToken}")
//    private String botToken;//= "1685117157:AAFTkP0R_UgA_pVU8NwOlpPLO9YndhKH6m8";

    @Override
    public String getBotUsername() {
        return "simple_family_finance_bot";
//        return userName;
    }

    @Override
    public String getBotToken() {
        return "1685117157:AAFTkP0R_UgA_pVU8NwOlpPLO9YndhKH6m8";
//        return botToken;
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
            case WHAT_TIME_REQUEST:
                return getCurrentTimeResponse(message);
            default:
                return greetingMessage(message);
        }
    }

    private ReplyKeyboardMarkup getMailMenu() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        KeyboardRow row1 = new KeyboardRow();
        row1.add(WHAT_TIME_REQUEST);
        row1.add(WHAT_DATE_REQUEST);

        KeyboardRow row2 = new KeyboardRow();
        row1.add(WHAT_GB_REQUEST);
        row1.add(ORDER_PIZZA);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        keyboardRowList.add(row1);
        keyboardRowList.add(row2);

        markup.setKeyboard(keyboardRowList);
        return markup;
    }

    private SendMessage getCurrentTimeResponse(Message message) {
        SendMessage response = new SendMessage();
        response.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        response.setChatId(message.getChatId());
        response.setReplyMarkup(getMailMenu());
        return response;
    }

    private SendMessage greetingMessage(Message message) {
        SendMessage response = new SendMessage();
        response.setText("Hello, " + message.getFrom().getFirstName());
        response.setChatId(message.getChatId());
        response.setReplyMarkup(getMailMenu());
        return response;
    }

    private SendMessage detOrderPizzaResponse(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setReplyMarkup();
        return sendMessage; 
    }
}
