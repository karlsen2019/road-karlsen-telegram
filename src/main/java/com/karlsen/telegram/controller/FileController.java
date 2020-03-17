package com.karlsen.telegram.controller;

import com.karlsen.telegram.entity.Idiom;
import com.karlsen.telegram.utils.IdiomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("file")
public class FileController {
    @GetMapping("getIdiomMap")
    public Map<String, Idiom> getIdiomMap() {
        return IdiomUtils.getIdiomMap();
    }
}
