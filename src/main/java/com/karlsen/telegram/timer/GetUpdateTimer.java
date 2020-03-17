package com.karlsen.telegram.timer;

import com.karlsen.telegram.bots.RoadIdiomBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updates.GetUpdates;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.Resource;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class GetUpdateTimer {
    @Resource
    private RoadIdiomBot roadIdiomBot;

    private int offSet = 0;


    @Scheduled(fixedRate = 2000)
    public void getUpdatesTimer() {
        roadIdiomBot.onWebhookUpdateReceived(getUpdate());
    }

    private Update getUpdate() {
        try {
            GetUpdates getUpdates = new GetUpdates();
            getUpdates.setLimit(100);
            getUpdates.setOffset(offSet + 1);
            List<Update> updates = roadIdiomBot.execute(getUpdates);
            if (!updates.isEmpty()) {
                offSet = updates.get(0).getUpdateId();
                return updates.get(0);
            }
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
