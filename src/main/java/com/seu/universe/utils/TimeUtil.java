package com.seu.universe.utils;

import java.sql.Timestamp;

public class TimeUtil {

    public static Timestamp convertStringToTimeStamp(String timeStr) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(timeStr);
            return ts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }
}
