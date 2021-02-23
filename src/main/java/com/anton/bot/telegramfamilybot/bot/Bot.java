package com.anton.bot.telegramfamilybot.bot;

import com.anton.bot.telegramfamilybot.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
@PropertySource("classpath:telegram.properties")
public class Bot extends TelegramLongPollingBot {

    @Value("&{bot.userName}")
    private String userName;

    @Value("${bot.botToken}")
    private String botToken;

    private BotState state;
    private BotContext context;
    private User user;

    public Bot() {
//        botState = BotState.BOT_START;
    }

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            final String text = message.getText();
            final long chatId = message.getChatId();

            if (state == null) {
                state = BotState.getInitialState();
                user = new User(chatId, state.ordinal());
                context = BotContext.of(this, user, text);
            } else {

                context = BotContext.of(this, user, text);
                state.handleInput(context);


            }

            do {
                state.enter(context);
                state = state.nextState(context);
            } while (!state.isInputNeeded());
            //todo save user in DB


        }
    }
}
