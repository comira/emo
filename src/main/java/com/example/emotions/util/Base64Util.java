package com.example.emotions.util;

import java.util.Base64;

public class Base64Util {
    public static String encode(byte[] buff) {
        return Base64.getEncoder().encodeToString(buff);
    }
}
