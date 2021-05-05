package com.apereg.cn.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    private static Random random = new Random();

    /* Constructor privado ya que es una clase estatica. */
    private Utils() {}

    public static double generateRandom(int min, int max) {
        return min + (max - min) * random.nextDouble();
    }

}
