package com.karlsen.telegram.service;

import com.karlsen.telegram.entity.Idiom;
import com.karlsen.telegram.utils.IdiomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IdiomService {
    private Map<String, Idiom> idiomMap;

    public IdiomService() {
        this.idiomMap = IdiomUtils.getIdiomMap();
    }

    public String getIdiom(String firstCode) {
        String result = "亲，恭喜完成本次成语接龙";
        List<String> idiomList = idiomMap.keySet().parallelStream().filter(item -> item.startsWith(firstCode)).collect(Collectors.toList());
        if (!idiomList.isEmpty()) {
            int index = new Random().nextInt(idiomList.size());
            result = idiomMap.get(idiomList.get(index)).toString();
        }
        return result;
    }
}
