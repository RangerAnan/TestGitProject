package com.test.mi.testproject.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by fcl on 2017/11/4
 * desc:
 */

public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = BaseActivity.this;
        initView();
        initData();
    }

    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化data
     */
    protected abstract void initData();


    protected String getTag() {
        return BaseActivity.class.getSimpleName();
    }

}
