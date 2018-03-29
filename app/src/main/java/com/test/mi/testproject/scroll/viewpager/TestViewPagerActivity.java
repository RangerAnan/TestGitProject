package com.test.mi.testproject.scroll.viewpager;

import android.support.v4.view.ViewPager;

import com.test.mi.testproject.R;
import com.test.mi.testproject.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class TestViewPagerActivity extends BaseActivity {


    private ViewPager viewpager;
    private List<String> listData = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.lv_vp_join_lv;
    }

    @Override
    protected void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);

    }

    @Override
    protected void initData() {
        viewpager.setAdapter(new joinedVPAdapter(getData(), mContext));
        viewpager.setOffscreenPageLimit(1);
    }

    private List getData() {
        for (int i = 0; i < 5; i++) {
            listData.add("img " + i);
        }
        return listData;
    }
}
