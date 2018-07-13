package com.test.mi.testproject.recycleView;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.facebook.stetho.inspector.elements.Document;
import com.test.mi.testproject.R;
import com.test.mi.testproject.base.BaseActivity;
import com.test.mi.testproject.recycleView.itemDecoration.StickyItemDecoration;
import com.test.mi.testproject.recycleView.model.Performer;
import com.test.mi.testproject.recycleView.model.PerformerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.布局管理器LayoutManager:控制显示方式
 * 2.使用ItemAnimator为RecylerView去添加Item移除、添加的动画效果:
 * 3.自定义ItemDecoration:改变每个子view的offset或修改子view本身来达到装饰子view或区分子view的目的
 */
public class RecycleItemActivity extends BaseActivity implements MyRecycleAdapter.OnItemClickListener {

    private RecyclerView recycleView;
    private MyRecycleAdapter myRecycleAdapter;
    private List<Performer> data;

    private Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycle_item;
    }

    @Override
    protected void initView() {
//        设置点击区域外消失属性
        setFinishOnTouchOutside(true);

        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));

        if(Build.VERSION.SDK_INT >= 21) {
            mToolbar.setElevation(0.0F);
        }
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void initData() {
        data = PerformerUtil.getInstance().getData();
        myRecycleAdapter = new MyRecycleAdapter(data, mContext);
        recycleView.setAdapter(myRecycleAdapter);

        //set click
        myRecycleAdapter.setOnItemClickListener(this);

        //添加分割线
        recycleView.addItemDecoration(new StickyItemDecoration());
//        recycleView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));

//        recycleView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onItemClick(int position, Performer performer, int size) {
        //1.新增
//        data.add(position, performer);
//        myRecycleAdapter.notifyDataSetChanged();

        //2.新增
//        data.add(position, performer);
//        myRecycleAdapter.notifyItemInserted(position);

        //3.更新
//        performer.setName("xxx");
//        myRecycleAdapter.notifyItemChanged(position, performer);

        //4.批量更新
        //insert/remove方法仅仅是做界面动画的效果，并没有进行数据与界面的绑定。
        // 所以还需要notifyItemRangeChanged(position,mDatas.size()-position);//通知数据与界面重新绑定

//        performer.setName(performer.getName() + "1");
//        data.add(position, performer);
//        performer.setName(performer.getName() + "2");
//        data.add(position, performer);
//        myRecycleAdapter.notifyItemRangeInserted(position, 2);
//        Log.i(getTag(), "--onItemClick--size:" + size + "--position:" + position);

    }
}
