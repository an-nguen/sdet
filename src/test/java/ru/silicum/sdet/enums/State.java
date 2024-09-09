package ru.silicum.sdet.enums;

public enum State {
    NCR("NCR", new City[]{City.DELHI, City.GURGAON, City.NOIDA}),
    UTTAR_PRADESH("Uttar Pradesh", new City[]{City.AGRA, City.LUCKNOW, City.MERRUT}),
    HARYANA("Haryana", new City[]{City.KARNAL, City.PANIPAT}),
    RAJASTHAN("Rajasthan", new City[]{City.JAIPUR, City.JAISELMER});

    public final String stateName;
    public final City[] cities;

    State(String stateName, City[] cities) {
        this.stateName = stateName;
        this.cities = cities;
    }
}
