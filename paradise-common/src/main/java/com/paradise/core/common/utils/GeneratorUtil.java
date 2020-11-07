package com.paradise.core.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 常见随机数序列生成
 *
 * @author Paradise
 */
public class GeneratorUtil {
    private final static String USER_NAME_FORMAT = "U%05d";

    /**
     * 得到from到to的随机数，包括to
     */
    private static int getRandomNumber(int from, int to) {
        float a = from + (to - from) * (new Random().nextFloat());
        int b = (int) a;
        return ((a - b) > 0.5 ? 1 : 0) + b;
    }

    public static String getNonceString(int len) {
        String seed = "23456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < len; i++) {
            tmp.append(seed.charAt(getRandomNumber(0, 33)));
        }
        return tmp.toString();
    }

    public static String getNonceNumberString(int len) {
        String seed = "1234567890";
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < len; i++) {
            tmp.append(seed.charAt(getRandomNumber(0, 9)));
        }
        return tmp.toString();
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String getOrderId(String prefix) {
        String body = String.valueOf(System.currentTimeMillis());
        return prefix + body + getRandomNumber(10, 99);
    }

    /**
     * 注册成功生成推广码
     *
     * @return 推广码
     */
    public static String generatePromotionCode(Long id) {
        return String.format(USER_NAME_FORMAT, id) + GeneratorUtil.getNonceString(2);
    }
}