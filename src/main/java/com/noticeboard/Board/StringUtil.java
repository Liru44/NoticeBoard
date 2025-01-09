package com.noticeboard.Board;

public class StringUtil {
    public static boolean isNotEmpty(String str) {
        return str != null || !(str.trim().isEmpty());
    }
}
