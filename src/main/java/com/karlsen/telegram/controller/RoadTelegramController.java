package com.karlsen.telegram.controller;

import com.karlsen.telegram.bots.RoadIdiomBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.GetUpdates;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("road")
public class RoadTelegramController {
    @Resource
    private RoadIdiomBot roadIdiomBot;

    @GetMapping("send")
    public Object send(String message) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId("-471727083");
            sendMessage.setText(message);
            roadIdiomBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @GetMapping("getUpdate")
    public Object getUpdate() {
        try {
            GetUpdates getUpdates = new GetUpdates();
            getUpdates.setLimit(100);
            getUpdates.setOffset(0);
            return roadIdiomBot.execute(getUpdates);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
