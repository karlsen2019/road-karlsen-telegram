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
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText(telegramService.getMessage(update.getMessage().getText().substring(1)));
            return sendMessage;
        }
        return null;
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
