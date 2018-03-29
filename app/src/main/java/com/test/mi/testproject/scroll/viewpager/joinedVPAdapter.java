package com.test.mi.testproject.scroll.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.mi.testproject.R;

import java.util.List;

/**
 * Created by fcl on 18.3.28
 * desc:
 */

public class joinedVPAdapter extends PagerAdapter {

    public List<String> imageList;
    public Context mContext;

    public joinedVPAdapter(List<String> imageList, Context mContext) {
        this.imageList = imageList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.vp_item_joined_lv, container, false);
        container.addView(view);
        return view;
    }
}
