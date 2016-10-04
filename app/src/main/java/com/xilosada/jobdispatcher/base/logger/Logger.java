package com.xilosada.jobdispatcher.base.logger;

import android.util.Log;

import java.util.Date;

/**
 * Created by xabierlosada on 04/10/16.
 */

public class Logger {

    public static void time(String message) {
        Log.i("Show time", generateLog(message));
    }

    public static String generateLog(String message) {
        return new StringBuilder()
                .append(String.valueOf(System.currentTimeMillis()))
                .append("\n")
                .append(new Date().toString())
                .append("\n")
                .append(Thread.currentThread().getName())
                .append("\n")
                .append(message)
                .append("\n")
                .toString();

    }
}
