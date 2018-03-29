package com.test.mi.testproject.recycleView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.test.mi.testproject.R;
import com.test.mi.testproject.base.BaseActivity;

public class RecycleItemActivity extends BaseActivity {

    private RecyclerView recycleView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycle_item;
    }

    @Override
    protected void initView() {
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {

    }
}
