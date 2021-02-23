package com.anton.bot.telegramfamilybot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long chatId;

    @Getter
    @Setter
    private Integer stateId;

    @Getter
    @Setter
    private String name;

    public User() {

    }

    public User(Long chatId, Integer stateId) {
        this.chatId = chatId;
        this.stateId = stateId;
    }
}
