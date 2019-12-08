package com.heil.accountbook.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String getCurrentTime() {
        return new SimpleDateFormat("MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
    }
}
