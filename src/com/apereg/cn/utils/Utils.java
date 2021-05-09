package com.apereg.cn.utils;

import java.util.Random;

public class Utils {

    private static final Random random = new Random();

    /* Constructor privado ya que es una clase estatica. */
    private Utils() {}

    public static double generateRandom(int min, int max) {
        return min + (max - min) * random.nextDouble();
    }

}
