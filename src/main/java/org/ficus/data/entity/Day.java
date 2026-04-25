package org.ficus.data.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    private static final Map<String, String> DAY_TITLES = new LinkedHashMap<>();

    static {
        DAY_TITLES.put("MONDAY", "Понедельник");
        DAY_TITLES.put("TUESDAY", "Вторник");
        DAY_TITLES.put("WEDNESDAY", "Среда");
        DAY_TITLES.put("THURSDAY", "Четверг");
        DAY_TITLES.put("FRIDAY", "Пятница");
        DAY_TITLES.put("SATURDAY", "Суббота");
        DAY_TITLES.put("SUNDAY", "Воскресенье");
    }

    public static Map<String, String> getDayTitles() {
        return DAY_TITLES;
    }
}
