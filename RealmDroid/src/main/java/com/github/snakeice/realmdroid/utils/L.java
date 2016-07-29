package com.github.snakeice.realmdroid.utils;

import android.util.Log;

/**
 * Logs
 * Created by rodrigo on 24/07/16.
 */
public final class L {
    public static void w(String msg) {
        String fileName = new Exception().getStackTrace()[1].getFileName();
        Log.w(fileName, msg);
    }

    public static void w(String msg, Throwable throwable) {
        String fileName = throwable.getStackTrace()[1].getFileName();
        Log.w(fileName, msg);
    }

    public static void i(String msg) {
        String fileName = new Exception().getStackTrace()[1].getFileName();
        Log.i(fileName, msg);
    }

    public static void i(String msg, Throwable throwable) {
        String fileName = throwable.getStackTrace()[1].getFileName();
        Log.i(fileName, msg);
    }

    public static void d(String msg) {
        String fileName = new Exception().getStackTrace()[1].getFileName();
        Log.d(fileName, msg);
    }

    public static void d(String msg, Throwable throwable) {
        String fileName = throwable.getStackTrace()[1].getFileName();
        Log.d(fileName, msg);
    }

    public static void e(String msg) {
        String fileName = new Exception().getStackTrace()[1].getFileName();
        Log.e(fileName, msg);
    }

    public static void e(String msg, Throwable throwable) {
        String fileName = throwable.getStackTrace()[1].getFileName();
        Log.e(fileName, msg);
    }
}
