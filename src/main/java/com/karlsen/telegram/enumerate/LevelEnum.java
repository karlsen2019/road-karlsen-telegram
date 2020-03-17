package com.karlsen.telegram.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LevelEnum {

    LEVEL0(0, "level0"),
    LEVEL1(1, "level1"),
    LEVEL2(2, "level2"),
    LEVEL3(3, "level3"),
    LEVEL4(4, "level4"),
    LEVEL5(5, "level5"),
    LEVEL6(6, "level6"),
    LEVEL7(7, "level7"),
    LEVEL8(8, "level8"),
    LEVEL9(9, "level9"),
    LEVEL10(10, "level10");

    private Integer level;
    private String desc;

    public static LevelEnum getLevel(int level) {
        return LevelEnum.valueOf("LEVEL" + level);
    }
}
