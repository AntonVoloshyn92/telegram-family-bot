package com.anton.bot.telegramfamilybot.model;

import com.anton.bot.telegramfamilybot.utils.StringsValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
//todo set table name and create table in postgresSQL
public class OperationInComeObject {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Getter
    @Setter
    private String operationType;

    @Getter
    @Setter
    private int coastOperation;

    @Getter
    @Setter
    private int month;

    @Getter
    @Setter
    private int year;

    public OperationInComeObject(String operationType, int coastOperation, int month) {
        this.operationType = operationType;
        this.coastOperation = coastOperation;
        this.month = month;
//        this.month = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        //todo set auto getDate and getMonth when we try save date in to DB
    }

    public OperationInComeObject() {

    }

    @Override
    public String toString() {
        return "operationType: " + operationType
                + "coastOperation: " + coastOperation
                + "month: " + month;
    }
}
