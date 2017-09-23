package com.wind.bean;

/**
 * 主题枚举
 */
public enum Theme {

    BEAUTYEYE("BeautyEye"),

    WEBLAF("Weblaf"),

    WINDOW("Window"),

    NIMBUS("Nimbus"),

    METAL("Metal"),

    MOTIF("Motif"),

    DARCULA("Darcula"),

    DEAUFT("Deauft");

    Theme(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
