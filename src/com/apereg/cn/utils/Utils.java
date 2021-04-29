package com.apereg.cn.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    /* Constructor privado ya que es una clase estatica. */
    private Utils() {}

    public static int generateRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
