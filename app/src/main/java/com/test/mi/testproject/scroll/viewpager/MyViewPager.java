package com.test.mi.testproject.scroll.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by fcl on 18.3.28
 * desc:
 */

public class MyViewPager extends ViewPager {

    public String tag = "MyViewPager";
    private float startX;
    private float startY;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(tag, "onTouchEvent--" + super.onTouchEvent(ev));

      /*  getParent().requestDisallowInterceptTouchEvent(true);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = getX();
                return super.onTouchEvent(ev);

            case MotionEvent.ACTION_MOVE:
                float moveX = getX();
                int abs = (int) Math.abs(moveX - startX);
                Log.i(tag, "ACTION_MOVE--" + super.onTouchEvent(ev) + "---abs:" + abs);
//                if (abs > 10) {
//                }
                return true;

            case MotionEvent.ACTION_UP:
                Log.i(tag, "ACTION_UP--" + super.onTouchEvent(ev));
                return super.onTouchEvent(ev);

            default:
                return super.onTouchEvent(ev);
        }*/
        return super.onTouchEvent(ev);
    }


   /* @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = getX();
                startY = getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_MOVE:
                float moveX = getX();
                int abs = (int) Math.abs(moveX - startX);
                if (abs > (Math.abs(getY() - startY))) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            default:
                return super.dispatchTouchEvent(ev);
        }
        return true;
    }*/
}
