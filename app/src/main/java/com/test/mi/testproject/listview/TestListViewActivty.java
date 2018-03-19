package com.test.mi.testproject.listview;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
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
import com.test.mi.testproject.constant.ARouterConstant;

import java.util.ArrayList;

/**
 * Created by fcl on 2017/11/13
 * desc:
 */

@Route(path = ARouterConstant.TestListViewActivty)
public class TestListViewActivty extends BaseActivity implements OnRefreshListener, OnRefreshLoadmoreListener {

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_smart_refresh;
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listView);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);

        ARouter.getInstance().inject(this);

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
        ArrayList<String> itemList = getData();
        itemList.add(0, "name:" + name + "/age:" + age + "/sex:" + sex);
        adapter = new ArrayAdapter<>(mContext, R.layout.lv_main_item, R.id.tv_main, itemList);

        //add footer
        EmptyView emptyView = new EmptyView(mContext);

        listView.addFooterView(emptyView);
        listView.setAdapter(adapter);

        //计算高度
        int height = listView.getHeight();
        int childCount = listView.getChildCount();

        Log.i(getTag(), "height:" + height);
        ListView.LayoutParams params = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, 800);
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setLayoutParams(params);
    }

    private ArrayList<String> getData() {
        ArrayList<String> itemList = new ArrayList<>();
//        for (int i = index; i < index + 10; i++) {
//            itemList.add("data  " + i);
//            if (i == index + 9) {
//                index = i;
//                break;
//            }
//        }
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


    private int getListHeight(ListView listView) {

        //对适配器的数据进行判空操作
        if (adapter == null) {
            return 0;
        }

        //设置ListView的底部高度
        int totalHeight = 40;
        //遍历适配器数据，获取到每一个item的高度并进行统计
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);    //获取到ListView中item对应的view
            listItem.measure(0, 0);                                //获取到view的高度
            totalHeight += listItem.getMeasuredHeight();        //获取到所有item的总高度
        }

        //获取到总高度：item总高度+线条总高度（线条比item数少一个）
        totalHeight = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        return totalHeight;
    }
}
