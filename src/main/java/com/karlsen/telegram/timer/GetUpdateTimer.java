package com.karlsen.telegram.timer;

import com.karlsen.telegram.bots.RoadIdiomBot;
import com.karlsen.telegram.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.Resource;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class GetUpdateTimer {
    @Resource
    private RoadIdiomBot roadIdiomBot;
    @Resource
    private TelegramService telegramService;
    private int offSet = 0;

    @Scheduled(fixedRate = 2000)
    public void getUpdatesTimer() {
        roadIdiomBot.onWebhookUpdateReceived(getUpdate());
    }

    private Update getUpdate() {
        List<Update> updates = telegramService.getUpdates(offSet + 1);
        if (!updates.isEmpty()) {
            offSet = updates.get(0).getUpdateId();
            return updates.get(0);
        }
        return null;
    }
}
