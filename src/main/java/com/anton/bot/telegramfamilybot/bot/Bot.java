package com.anton.bot.telegramfamilybot.bot;

import com.anton.bot.telegramfamilybot.model.User;
import com.anton.bot.telegramfamilybot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashSet;
import java.util.Set;


@Component
@PropertySource("classpath:telegram.properties")
public class Bot extends TelegramLongPollingBot {

    @Value("&{bot.userName}")
    private String userName;

    @Value("${bot.botToken}")
    private String botToken;

    private final UserService userService;

    public Bot(UserService userService) {
        this.userService = userService;
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
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        Message message = update.getMessage();
        final String text = message.getText();
        final long chatId = message.getChatId();

        User user = userService.findByChatId(chatId);

        BotContext context;
        BotState state;


        if (user == null) {
            state = BotState.getInitialState();
            user = new User(chatId, state.ordinal(), message.getFrom().getFirstName());
            userService.addUser(user);

            context = BotContext.of(this, user, text);
            state.enter(context);
        } else {
            context = BotContext.of(this, user, text);
            state = BotState.byId(user.getStateId());
        }

        state.handleInput(context);

        do {
            state = state.nextState(context);
            state.enter(context);
        } while (!state.isInputNeeded());

        user.setStateId(state.ordinal());
        userService.updateUser(user);


    }
}
