package ru.silicum.sdet.enums;

public enum City {
    DELHI("Delhi"),
    GURGAON("Gurhaon"),
    NOIDA("Noida"),
    AGRA("Agra"),
    LUCKNOW("Lucknow"),
    MERRUT("Merrut"),
    KARNAL("Karnal"),
    PANIPAT("Panipat"),
    JAIPUR("Jaipur"),
    JAISELMER("Jaiselmer");

    public final String name;

    City(String name) {
        this.name = name;
    }
}
