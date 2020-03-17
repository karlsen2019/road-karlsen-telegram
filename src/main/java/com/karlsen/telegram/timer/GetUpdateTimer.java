package com.karlsen.telegram.timer;

import com.karlsen.telegram.bots.RoadIdiomBot;
import com.karlsen.telegram.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
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
    @Resource
    private TelegramService telegramService;
    private int offSet = 0;

    @Scheduled(fixedRate = 1500)
    public void getUpdatesTimer() {
        try {
            Update update = getUpdate();
            if (update == null) {
                return;
            }
            BotApiMethod botApiMethod = roadIdiomBot.onWebhookUpdateReceived(update);
            if (botApiMethod != null) {
                roadIdiomBot.execute(botApiMethod);
            }
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }

    private Update getUpdate() {
        List<Update> updates = telegramService.getUpdates(offSet + 1);
        if (updates.isEmpty()) {
            return null;
        }
        riseUpdateId(updates);
        return updates.get(0);
    }

    private void riseUpdateId(List<Update> updates) {
        offSet = updates.get(0).getUpdateId();
    }
}
