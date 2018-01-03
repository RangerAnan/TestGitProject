package com.test.mi.testproject.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.test.mi.testproject.GlobalApplication;
import com.test.mi.testproject.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Toast管理
 */
public class ToastUtil {

    public static Toast toast;

    public static void showToastShort(String toastText) {
        if (toast == null) {
            toast = Toast.makeText(GlobalApplication.getContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(toastText);
        toast.show();
    }

    public static void showToastLong(String toastText) {
        if (toast == null) {
            toast = Toast.makeText(GlobalApplication.getContext(), "", Toast.LENGTH_LONG);
        }
        toast.setText(toastText);
        toast.show();
    }

    public static void showToastShort(int res) {
        showToastShort(GlobalApplication.getContext().getString(res));
    }

    public static void showToastLong(int res) {
        showToastLong(GlobalApplication.getContext().getString(res));
    }


    private static void execToast(final Toast toast, final int timerNumber) {
        if (timerNumber <= 0) {
            toast.cancel();
        } else {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    execToast(toast, timerNumber - 1);
                }
            }, 1000);
        }
    }

}