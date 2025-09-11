package org.luke.common.util;

public class IdUtil {

    public static String generateProductId(int nProduct) {
        return String.format("product-%d", RandomUtil.generateRandomInt(0, nProduct - 1));
    }

    public static String generateTransId(int i) {
        return String.format("trans-%d", i);
    }

    public static String generateUserId(int nUser) {
        return String.format("user-%d", RandomUtil.generateRandomInt(0, nUser - 1));
    }
}
