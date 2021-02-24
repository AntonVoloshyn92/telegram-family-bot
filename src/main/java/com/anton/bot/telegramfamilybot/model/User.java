package com.anton.bot.telegramfamilybot.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Data
@Table(name = "users")
public class User implements Serializable {

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
    private int stateId;

    @Getter
    @Setter
    private String name;


    @Getter
    @Setter
    private String choice;

    public User() {

    }

    public User(Long chatId, int stateId) {
        this.chatId = chatId;
        this.stateId = stateId;
    }

    public User(Long chatId, int stateId, String name) {
        this.chatId = chatId;
        this.stateId = stateId;
        this.name = name;
    }
}
