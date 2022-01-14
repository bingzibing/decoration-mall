package com.mydecoration.decorationmall.common.utils;

public class RedisKeyUtil {
    public static String getDeliverAmountKey(String configId) {
        return String.format("MI-OTA:DELIVER:%s", configId);
    }
}
