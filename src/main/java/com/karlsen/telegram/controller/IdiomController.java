package com.karlsen.telegram.controller;

import com.karlsen.telegram.entity.Idiom;
import com.karlsen.telegram.service.IdiomService;
import com.karlsen.telegram.utils.IdiomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("file")
public class IdiomController {
    @Resource
    private IdiomService idiomService;

    @GetMapping("getIdiomMap")
    public Map<String, Idiom> getIdiomMap() {
        return IdiomUtils.getIdiomMap();
    }

    @GetMapping("getIdiom")
    public List<String> getIdiom(String answer) {
        return idiomService.matchIdiom(answer);
    }
}
