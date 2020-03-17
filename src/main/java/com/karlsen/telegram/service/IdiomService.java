package com.karlsen.telegram.service;

import com.karlsen.telegram.entity.Idiom;
import com.karlsen.telegram.enumerate.LevelEnum;
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
    private int totalCount = 0;

    public IdiomService() {
        this.idiomMap = IdiomUtils.getIdiomMap();
    }

    public String playIdiom(String answer) {
        String result = "错，请以【" + lastCode + "】开头";
        if (!checkIdiom(answer)) {
            return result;
        }
        totalCount++;
        return getIdiom(answer);
    }

    public synchronized String getIdiom(String answer) {
        String result = getLevelInfo();
        List<String> idiomList = matchIdiom(answer);
        if (!idiomList.isEmpty()) {
            int index = new Random().nextInt(idiomList.size());
            String idiom = idiomList.get(index);
            lastCode = idiom.substring(idiom.length() - 1);
            return idiomMap.get(idiom).toString();
        }
        reset();
        return result;
    }

    private String getLevelInfo() {
        LevelEnum levelEnum = LevelEnum.getLevel(totalCount / 10);
        StringBuilder result = new StringBuilder();
        result.append("太厉害啦，恭喜完成本次成语接龙！！！！");
        result.append("\n");
        result.append("\n");
        result.append("本次回答总次数为：").append(totalCount);
        result.append("\n");
        result.append("本次等级为：").append(levelEnum.getDesc());
        result.append("\n");
        result.append("\n");
        result.append("输入龙头继续成语接龙：");
        return result.toString();
    }

    public List<String> matchIdiom(String answer) {
        String firstCode = answer.substring(answer.length() - 1);
        return idiomMap.keySet().parallelStream().filter(item -> item.startsWith(firstCode)).collect(Collectors.toList());
    }

    private void reset() {
        lastCode = null;
        totalCount = 0;
    }

    private boolean checkIdiom(String answer) {
        if (lastCode != null) {
            return answer.startsWith(lastCode);
        }
        return true;
    }
}
