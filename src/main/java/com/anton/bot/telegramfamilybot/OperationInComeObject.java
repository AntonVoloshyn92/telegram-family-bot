package com.anton.bot.telegramfamilybot;

import com.anton.bot.telegramfamilybot.utils.StringsValue;
import lombok.Getter;
import lombok.Setter;

public class OperationInComeObject {

    @Getter
    @Setter
    private StringsValue operationType;

    @Getter
    @Setter
    private int coastOperation;

    @Getter
    @Setter
    private int month;

    //todo add year field

    public OperationInComeObject(StringsValue operationType, int coastOperation, int month) {
        this.operationType = operationType;
        this.coastOperation = coastOperation;
        this.month = month;
    }

    @Override
    public String toString() {
        return "operationType: " + operationType
                + "coastOperation: " + coastOperation
                + "month: " + month;
    }
}
