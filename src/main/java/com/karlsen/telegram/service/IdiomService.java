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
    private String lastCode = null;

    public IdiomService() {
        this.idiomMap = IdiomUtils.getIdiomMap();
    }

    public String playIdiom(String answer) {
        String result = "亲，请以【" + lastCode + "】开头噢";
        if (!checkIdiom(answer)) {
            lastCode = answer.substring(answer.length() - 1);
            return result;
        }
        return getIdiom(answer);
    }

    public synchronized String getIdiom(String answer) {
        String result = "亲，恭喜完成本次成语接龙。\n\n继续请输入龙头开始游戏";
        String firstCode = answer.substring(answer.length() - 1);
        List<String> idiomList = idiomMap.keySet().parallelStream().filter(item -> item.startsWith(firstCode)).collect(Collectors.toList());
        if (!idiomList.isEmpty()) {
            int index = new Random().nextInt(idiomList.size());
            String idiom = idiomList.get(index);
            return idiomMap.get(idiom).toString();
        }
        lastCode = null;
        return result;
    }

    private boolean checkIdiom(String answer) {
        if (lastCode != null) {
            return answer.startsWith(lastCode);
        }
        return true;
    }
}
