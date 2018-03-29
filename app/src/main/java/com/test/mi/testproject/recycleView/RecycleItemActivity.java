package com.test.mi.testproject.recycleView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.facebook.stetho.inspector.elements.Document;
import com.test.mi.testproject.R;
import com.test.mi.testproject.base.BaseActivity;
import com.test.mi.testproject.recycleView.itemDecoration.StickyItemDecoration;
import com.test.mi.testproject.recycleView.model.PerformerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.布局管理器LayoutManager:控制显示方式
 * 2.使用ItemAnimator为RecylerView去添加Item移除、添加的动画效果
 * 3.自定义ItemDecoration:控制item间的间隔
 */
public class RecycleItemActivity extends BaseActivity implements MyRecycleAdapter.OnItemClickListener {

    private RecyclerView recycleView;
    private MyRecycleAdapter myRecycleAdapter;
    private List<String> listData = new ArrayList<>();


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
        myRecycleAdapter = new MyRecycleAdapter(PerformerUtil.getInstance().getData(), mContext);
        recycleView.setAdapter(myRecycleAdapter);

        //set click
        myRecycleAdapter.setOnItemClickListener(this);

        //添加分割线
        recycleView.addItemDecoration(new StickyItemDecoration());
//        recycleView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
    }


    @Override
    public void onItemClick(int position, String name) {
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
    }
}
