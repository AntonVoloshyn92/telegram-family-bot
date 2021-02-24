package com.anton.bot.telegramfamilybot;

import com.anton.bot.telegramfamilybot.bot.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


@SpringBootApplication
public class TelegramFamilyBotApplication {

    public static void main(String[] args)  {
        ApiContextInitializer.init();
        SpringApplication.run(TelegramFamilyBotApplication.class, args);

    }

}
