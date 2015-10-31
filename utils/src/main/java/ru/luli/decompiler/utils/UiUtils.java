package ru.luli.decompiler.utils;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class UiUtils {
    private static Display display;

    public static void setDisplay (Activity act) {
        display = act.getWindowManager().getDefaultDisplay();
    }

    public static int getWidth(int percent) {
        if (display!=null) {
            Point point = new Point();
            display.getSize(point);
            return point.x * percent / 100;
        }
        return 0;
    }

    public static int getHight(int percent) {
        if (display!=null) {
            Point point = new Point();
            display.getSize(point);
            return point.y * percent / 100;
        }
        return 0;
    }


}
