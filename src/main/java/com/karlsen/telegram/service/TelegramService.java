package com.karlsen.telegram.service;

import com.google.common.collect.Lists;
import com.karlsen.telegram.bots.RoadIdiomBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.GetUpdates;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class TelegramService {
    @Resource
    private RoadIdiomBot roadIdiomBot;

    public void sendMessage(String message, String chatId) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(message);
            roadIdiomBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }

    public List<Update> getUpdates(Integer offSet) {
        try {
            GetUpdates getUpdates = new GetUpdates();
            getUpdates.setLimit(100);
            getUpdates.setOffset(offSet);
            return roadIdiomBot.execute(getUpdates);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
        return Lists.newArrayList();
    }
}
