package com.test.mi.testproject.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by fcl on 2017/11/4
 * desc:
 */

public abstract class BaseActivity extends SwipeBackActivity {

    public Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackLayout();
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
        return getClass().getSimpleName();
    }


    private void setSwipeBackLayout() {
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        //滑动删除的效果只能从边界滑动才有效果，如果要扩大touch的范围，可以调用这个方法
        swipeBackLayout.setEdgeSize(200);
    }


}
