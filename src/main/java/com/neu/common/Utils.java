package com.neu.common;

import java.sql.Timestamp;
import java.util.Calendar;
/**
 * 工具类
 */
public class Utils {

    public static Timestamp currentTime() {
        return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }

    /**
     *
     * @return UUID
     */
    public static String getUUID() {
        String uuidResult = "";
        String uuidTemp = "";
        uuidTemp = java.util.UUID.randomUUID().toString();
        uuidResult = uuidTemp.replaceAll("-", "");
        System.out.println( uuidResult);
        return uuidResult;
    }
    public static long createTimestamp() {
        return System.currentTimeMillis();
    }

}
