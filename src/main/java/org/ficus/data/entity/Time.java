package org.ficus.data.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Time {
    NINE,
    TEN,
    ELEVEN,
    TWELVE,
    THIRTEEN,
    FOURTEEN,
    FIFTEEN,
    SIXTEEN,
    SEVENTEEN,
    EIGHTEEN;

    private static final Map<String, String> TIME_TITLES = new LinkedHashMap<>();

    static {
        TIME_TITLES.put("NINE", "9:00");
        TIME_TITLES.put("TEN", "10:00");
        TIME_TITLES.put("ELEVEN", "11:00");
        TIME_TITLES.put("TWELVE", "12:00");
        TIME_TITLES.put("THIRTEEN", "13:00");
        TIME_TITLES.put("FOURTEEN", "14:00");
        TIME_TITLES.put("FIFTEEN", "15:00");
        TIME_TITLES.put("SIXTEEN", "16:00");
        TIME_TITLES.put("SEVENTEEN", "17:00");
        TIME_TITLES.put("EIGHTEEN", "18:00");
    }

    public static Map<String, String> getTimeTitles() {
        return TIME_TITLES;
    }
}
