package org.luke.common.util;

import java.util.Random;

public class RandomUtil {

    public static long generateRandomLong() {
        Random random = new Random();
        return random.nextLong();
    }

    public static int generateRandomInt() {
        Random random = new Random();
        return random.nextInt();
    }

    public static int generateRandomInt(int min, int max) {
        Random random = new Random();
        int bound = max - min + 1;
        int result = random.nextInt() >>> 1;
        return min + (result % bound);
    }

    public static <T extends Enum<?>> T getRandomEnum(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        int index = generateRandomInt(0, values.length - 1);
        return values[index];
    }
}
