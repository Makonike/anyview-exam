package com.zxd.www.global.util;

import java.util.Random;

/**
 * @author Makonike
 * @date 2021-10-12 20:09
 **/
public class StringUtil {

    private static final char[] SALT_POOL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public static String generateSalt(int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char aChar = SALT_POOL[new Random().nextInt(SALT_POOL.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }


}
