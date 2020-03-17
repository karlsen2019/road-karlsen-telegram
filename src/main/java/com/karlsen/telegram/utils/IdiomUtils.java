package com.karlsen.telegram.utils;

import com.google.common.collect.Maps;
import com.karlsen.telegram.entity.Idiom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class IdiomUtils {
    private IdiomUtils() {
    }

    public static Map<String, Idiom> getIdiomMap() {
        HashMap<String, Idiom> result = Maps.newHashMap();
        try {
            Resource resource = new ClassPathResource("data.txt");
            try (InputStream inputStream = resource.getInputStream()) {
                try (InputStreamReader reader = new InputStreamReader(inputStream)) {
                    try (BufferedReader br = new BufferedReader(reader)) {
                        String line;
                        line = br.readLine();
                        while (line != null) {
                            String[] split = line.split("\t");
                            String code = split[0];
                            String spell = split[1];
                            String desc = split[2];
                            result.put(code, Idiom.builder().code(code).spell(spell).desc(desc).build());
                            line = br.readLine();
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}
