package com.test.mi.testproject.scroll;

import android.annotation.SuppressLint;

import com.test.mi.testproject.R;
import com.test.mi.testproject.base.BaseActivity;
import com.test.mi.testproject.scroll.listview.MyListView;
import com.test.mi.testproject.scroll.listview.VPJoinAdapter;
import com.test.mi.testproject.scroll.model.VPJoinModel;

import java.util.ArrayList;
import java.util.List;

public class VPJoinListViewActivity extends BaseActivity {

    private MyListView mListView;
    private VPJoinAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vpjoin_list_view;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {
        mListView = (MyListView) findViewById(R.id.listView);
    }

    @Override
    protected void initData() {
        adapter = new VPJoinAdapter(getData(), mContext);
        mListView.setAdapter(adapter);
    }


    private List<VPJoinModel> getData() {
        List<VPJoinModel> listData = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            VPJoinModel model = new VPJoinModel();
            model.name = "data " + i;
            for (int j = 0; j < 5; j++) {
                List<String> imageList = new ArrayList<>();
                imageList.add("img" + j);
                model.imageList = imageList;
            }
            listData.add(model);
        }
        return listData;
    }
}
