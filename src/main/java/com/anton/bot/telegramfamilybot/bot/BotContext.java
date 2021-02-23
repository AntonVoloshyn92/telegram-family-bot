package com.anton.bot.telegramfamilybot.bot;

import com.anton.bot.telegramfamilybot.model.User;

public class BotContext {
    private final Bot bot;
    private final User user;
    private final String input;
    private String response;
    //?
    public static BotContext of(Bot bot, User user, String text){
        return new BotContext(bot,user,text);
    }//?

    public BotContext(Bot bot, User user, String input) {
        this.bot = bot;
        this.user = user;
        this.input = input;
    }

    public Bot getBot() {
        return bot;
    }

    public User getUser() {
        return user;
    }

    public String getInput() {
        return input;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
