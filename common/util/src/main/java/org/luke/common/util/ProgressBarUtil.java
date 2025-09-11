package org.luke.common.util;

public class ProgressBarUtil {

    public static int getPercentageProgress(int cur, int total) {
        return (cur + total - 1)/total;
    }
}
