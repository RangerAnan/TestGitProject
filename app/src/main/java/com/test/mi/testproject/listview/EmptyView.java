package com.test.mi.testproject.listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.test.mi.testproject.R;

/**
 * Created by fcl on 18.3.10
 * desc:
 */

public class EmptyView extends LinearLayout {


    public EmptyView(Context context) {
        super(context);
        initView(context);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {

        View emptyView = View.inflate(context, R.layout.view_empty, null);
        addView(emptyView);
    }
}
