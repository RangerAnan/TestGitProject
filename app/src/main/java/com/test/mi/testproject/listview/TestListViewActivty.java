package com.test.mi.testproject.listview;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
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
import com.test.mi.testproject.colormatrix.TestColorMatrixActivity;
import com.test.mi.testproject.constant.ARouterConstant;

import java.util.ArrayList;

/**
 * Created by fcl on 2017/11/13
 * desc:
 */

@Route(path = ARouterConstant.TestListViewActivty)
public class TestListViewActivty extends BaseActivity implements OnRefreshListener, OnRefreshLoadmoreListener, OnClickListener {

    private ListView listView;
    private SmartRefreshLayout refreshLayout;

    private int index = 0;
    private ArrayAdapter<String> adapter;

    @Autowired
    public String name;
    @Autowired
    public int age;
    @Autowired
    public boolean sex;

    private ArrayList<String> itemListData = new ArrayList<>();
    private Button btn_add;

    private int preViewVisiblePosition = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_smart_refresh;
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listView);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        btn_add = (Button) findViewById(R.id.btn_add);

        ARouter.getInstance().inject(this);
        btn_add.setOnClickListener(this);

        refreshLayout.setEnableOverScrollBounce(false);                                 //去掉回弹
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setEnableLoadmore(false);

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
        itemListData.add(0, "name:" + name + "/age:" + age + "/sex:" + sex);
        ArrayList<String> addData = addData(itemListData.size());
        itemListData.addAll(addData);
        adapter = new ArrayAdapter<>(mContext, R.layout.lv_main_item, R.id.tv_main, itemListData);

        listView.setAdapter(adapter);
    }

    private ArrayList<String> addData(int size) {
        ArrayList<String> itemListData = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            itemListData.add("data " + (i + size));
        }
        return itemListData;
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
                        ArrayList<String> addData = addData(itemListData.size());
                        preViewVisiblePosition = addData.size();
                        itemListData.addAll(0, addData);
                        adapter.notifyDataSetChanged();

                        if (getRefreshLayout().isRefreshing()) {
                            getRefreshLayout().finishRefresh();
                        }
                        if (getRefreshLayout().isLoading()) {
                            getRefreshLayout().finishLoadmore();
                        }

                        //定位到为刷新之前的item位置
                        View childAt = listView.getChildAt(preViewVisiblePosition);
//                        int top = childAt.getTop();
//                        Log.i(getTag(), "--top:" + top + "---firstVisiblePosition:" + preViewVisiblePosition);
//                        listView.setSelectionFromTop((preViewVisiblePosition - 1), 100);

                    }
                });
            }
        }, 1000);
    }

    public SmartRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                listView.setSelection(preViewVisiblePosition - 1);
                break;
            default:
                break;
        }
    }
}
