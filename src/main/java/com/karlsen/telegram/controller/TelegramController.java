package com.karlsen.telegram.controller;

import com.karlsen.telegram.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("road")
public class TelegramController {
    @Resource
    private TelegramService telegramService;

    @GetMapping("send")
    public void send(String message) {
        telegramService.sendMessage(message, "-471727083");
    }

    @GetMapping("getUpdates")
    public List<Update> getUpdates() {
        return telegramService.getUpdates(0);
    }
}
