package com.anton.bot.telegramfamilybot.bot;

import com.anton.bot.telegramfamilybot.utils.ListKeyMap;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.anton.bot.telegramfamilybot.utils.StringsValue.*;

public enum BotState {

    BOT_START {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, ListKeyMap.getMailMenu());
        }

        @Override
        public void handleInput(BotContext context) {
            context.setResponse(context.getInput());
        }

        @Override
        public BotState nextState(BotContext context) {
            switch (context.getResponse()) {
                case INCOME:
                    return BOT_CATEGORY_IN;
            }
            return null;
        }
    },

    //
    BOT_CATEGORY_IN {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, ListKeyMap.getInComeMenu());
        }

        @Override
        public void handleInput(BotContext context) {

        }

            @Override
        public BotState nextState(BotContext context) {
            return null;
        }


    },
    BOT_FINISH {
        @Override
        protected void sendMessage(BotContext context, ReplyKeyboardMarkup markup) {
            super.sendMessage(context, markup);
        }

        @Override
        public boolean isInputNeeded() {
            return super.isInputNeeded();
        }

        @Override
        protected void sendMessage(BotContext context, String text) {
            super.sendMessage(context, text);
        }

        @Override
        protected BotState nextStep(String message) {
            return super.nextStep(message);
        }

        @Override
        public void enter(BotContext context) {

        }

        @Override
        public BotState nextState(BotContext context) {
            return null;
        }

        @Override
        public void handleInput(BotContext context) {
            super.handleInput(context);
        }
    };

    private final boolean inputNeeded; //указывает, нужно ли ожтдать ввода от пользователя

    protected void sendMessage(BotContext context, ReplyKeyboardMarkup markup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Выбери категорию доходов");
        sendMessage.setReplyMarkup(markup);
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

    protected BotState nextStep(String message) {
        switch (message) {
            case INCOME:
                return BOT_CATEGORY_IN;
            case WAGE:
//                return inComeFinance.editSumOfWage(message);
            case SNOWBOARD:
            case PRIZE:
            case ELSE:
//                greetingMessage(message);
            default:
//                System.out.println("Price: " + message.getText());
//                return greetingMessage(message);
                return BOT_FINISH;
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

    private static BotState byId(int id) {
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
