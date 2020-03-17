package com.karlsen.telegram.bots;

import com.karlsen.telegram.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.Resource;

@Slf4j
@Component
public class RoadIdiomBot extends TelegramWebhookBot {
    @Value("${road.idiom.botUserName:road_karlsen_bot}")
    private String botUserName;
    @Value("${road.idiom.botToken:1073331134:AAHju52QpqobCedRSjH9l2-oNCTjdsgzNY8}")
    private String botToken;

    @Resource
    private TelegramService telegramService;

    private BotApiMethod getBotApiMethod(Update update) {
        String message = getMessage(update);
        if (message == null) {
            return null;
        }
        return createSendMessage(update, message);
    }

    private SendMessage createSendMessage(Update update, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(message);
        return sendMessage;
    }

    private String getMessage(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return null;
        }
        String text = update.getMessage().getText();
        if (text.startsWith("/")) {
            text = update.getMessage().getText().substring(1);
        }
        return telegramService.getMessage(text);
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return getBotApiMethod(update);
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return null;
    }
}
