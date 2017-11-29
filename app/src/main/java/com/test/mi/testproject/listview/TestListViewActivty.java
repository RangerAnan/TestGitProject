package com.test.mi.testproject.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.test.mi.testproject.R;
import com.test.mi.testproject.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by fcl on 2017/11/13
 * desc:
 */

public class TestListViewActivty extends BaseActivity implements OnRefreshListener, OnRefreshLoadmoreListener {

    private ListView listView;
    private SmartRefreshLayout refreshLayout;

    private int index = 0;
    private ArrayAdapter<String> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_smart_refresh;
    }

    @Override
    protected void initView() {
        listView =  (ListView)findViewById(R.id.listView);
        refreshLayout =  (SmartRefreshLayout)findViewById(R.id.refreshLayout);

        refreshLayout.setEnableOverScrollBounce(false);                                 //去掉回弹
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);

        refreshLayout.setDisableContentWhenRefresh(true);
        refreshLayout.setDisableContentWhenLoading(true);

        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                ClassicsHeader header = new ClassicsHeader(context);
                header.setSpinnerStyle(SpinnerStyle.Translate);
                header.setPrimaryColor(getResources().getColor(R.color.bg_common));
                header.setAccentColor(getResources().getColor(R.color.cl_gray2));
                header.setTextSizeTitle(14);
                return header;
            }
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                ClassicsFooter footer = new ClassicsFooter(context);
                footer.setSpinnerStyle(SpinnerStyle.Translate);
                footer.setAccentColor(getResources().getColor(R.color.cl_gray2));
//                RefreshFooterView footer = new RefreshFooterView(context);
                footer.setPrimaryColor(getResources().getColor(R.color.bg_common));
                footer.setPrimaryColors(getResources().getColor(R.color.btn_orange1));
                return footer;
            }
        });

    }

    @Override
    protected void initData() {
        ArrayList<String> itemList = getData();
        adapter = new ArrayAdapter<>(mContext, R.layout.lv_main_item, R.id.tv_main, itemList);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> getData() {
        ArrayList<String> itemList = new ArrayList<>();
        for (int i = index; i < index + 10; i++) {
            itemList.add("data  " + i);
            if (i == index + 9) {
                index = i;
                break;
            }
        }
        return itemList;
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        if (index >= 30) {
            getRefreshLayout().getRefreshFooter().setLoadmoreFinished(true);
        } else {
            initData();
        }
        finishRefreshAndLoad();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        finishRefreshAndLoad();
    }

    public void finishRefreshAndLoad() {
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (getRefreshLayout().isRefreshing()) {
                            getRefreshLayout().finishRefresh();
                        }
                        if (getRefreshLayout().isLoading()) {
                            getRefreshLayout().finishLoadmore();
                        }
                    }
                });
            }
        }, 1000);
    }

    public SmartRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }
}
