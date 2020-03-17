package com.karlsen.telegram.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Idiom {
    private String code;
    private String spell;
    private String desc;

    @Override
    public String toString() {
        return code + "\n\n拼音：" + spell + "\n释义：" + desc;
    }
}
