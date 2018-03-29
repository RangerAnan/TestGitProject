package com.test.mi.testproject.scroll.listview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.mi.testproject.R;
import com.test.mi.testproject.base.BaseViewHolder;
import com.test.mi.testproject.base.ListBaseAdapter;
import com.test.mi.testproject.scroll.model.VPJoinModel;
import com.test.mi.testproject.scroll.viewpager.MyViewPager;

import java.util.List;

/**
 * Created by fcl on 18.3.27
 * desc:
 */

public class VPJoinAdapter extends ListBaseAdapter<VPJoinModel> {
    public Context mContext;

    public VPJoinAdapter(List dataList, Context mContext) {
        super(dataList);
        this.mContext = mContext;
    }

    @Override
    public int getLayout() {
        return R.layout.lv_vp_join_lv;
    }

    @Override
    public void setData(BaseViewHolder holder, VPJoinModel o, int position) {
        MyViewPager viewpager = holder.getView(R.id.viewpager);
        viewpager.setAdapter(new joinedVPAdapter(o.imageList));
        viewpager.setOffscreenPageLimit(1);

        TextView tvTitle = holder.getView(R.id.tvTitle);
        tvTitle.setText(o.name);
    }


    public class joinedVPAdapter extends PagerAdapter {
        public List<String> imageList;

        public joinedVPAdapter(List<String> imageList) {
            this.imageList = imageList;
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
}
