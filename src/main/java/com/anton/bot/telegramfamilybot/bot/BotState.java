package com.anton.bot.telegramfamilybot.bot;

import com.anton.bot.telegramfamilybot.model.OperationInComeObject;
import com.anton.bot.telegramfamilybot.utils.ListKeyMap;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.anton.bot.telegramfamilybot.utils.StringsValue.*;

public enum BotState {

    BOT_START {

        private BotState next;

        @Override
        public void enter(BotContext context) {
            sendMessage(context, ListKeyMap.getMailMenu(), "тип операции");
        }

        @Override
        public void handleInput(BotContext context) {
            context.getUser().setChoice(context.getInput());
            switch (context.getInput()) {
                case INCOME:
                    next = BOT_CATEGORY_IN;
                    break;
            }
        }


        @Override
        public BotState nextState(BotContext context) {
            return next;
        }
    },

    //
    BOT_CATEGORY_IN {

        private BotState next;

        @Override
        public void enter(BotContext context) {
            sendMessage(context, ListKeyMap.getInComeMenu(), "введи категорию расходов");
        }

        @Override
        public void handleInput(BotContext context) {
            OperationInComeObject operationInComeObject = new OperationInComeObject();
            switch (context.getInput()) {
                case WAGE:
                    next = BOT_FINISH;
                    operationInComeObject.setOperationType(WAGE);
                    context.setOperationInComeObject(operationInComeObject);
                    break;
                case SNOWBOARD:
                    next = BOT_FINISH;
                    operationInComeObject.setOperationType(SNOWBOARD);
                    context.setOperationInComeObject(operationInComeObject);
                    break;
                case PRIZE:
                    next = BOT_FINISH;
                    operationInComeObject.setOperationType(PRIZE);
                    context.setOperationInComeObject(operationInComeObject);
                    break;
                case ELSE:
                    operationInComeObject.setOperationType(ELSE);
                    context.setOperationInComeObject(operationInComeObject);
                    next = BOT_FINISH;
                    break;
            }
        }

        @Override
        public BotState nextState(BotContext context) {
            return next;
        }


    },
    BOT_FINISH() {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, "откорой клавиатуру\n " +
                    "введи сумму");
        }

        @Override
        public void handleInput(BotContext context) {
//           int sum = Integer.parseInt(context.getInput());
//           context.getOperationInComeObject().setCoastOperation(sum);
//           //todo save InComeObject in to DB
        }

        @Override
        public BotState nextState(BotContext context) {
            return APPROVED;
        }
    },

    APPROVED(false) {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, "Thanks YOU!");
        }

        @Override
        public BotState nextState(BotContext context) {
            return BOT_START;
        }
    };

    private final boolean inputNeeded; //указывает, нужно ли ожтдать ввода от пользователя

    protected void sendMessage(BotContext context, ReplyKeyboardMarkup markup, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText(text);
        sendMessage.setChatId(context.getUser().getChatId());

        try {
            context.getBot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public boolean isInputNeeded() {
        return inputNeeded;
    }

    protected void sendMessage(BotContext context, String text) {
        SendMessage message = new SendMessage()
                .setChatId(context.getUser().getChatId())
                .setText(text);
        try {
            context.getBot().execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private static BotState[] states;

    BotState() {
        this.inputNeeded = true;
    }

    BotState(boolean inputNeeded) {
        this.inputNeeded = inputNeeded;
    }

    public static BotState getInitialState() {
        return BotState.BOT_START;
    }

    public static BotState byId(int id) {
        if (states == null) {
            states = BotState.values();
        }
        return states[id];
    }

    public abstract void enter(BotContext context);

    public abstract BotState nextState(BotContext context);

    public void handleInput(BotContext context) {
        //default method
    }


}
