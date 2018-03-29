package com.test.mi.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.test.mi.testproject.colormatrix.TestColorMatrixActivity;
import com.test.mi.testproject.constant.ARouterConstant;
import com.test.mi.testproject.database.TestDBActivity;
import com.test.mi.testproject.domain.StudentModel;
import com.test.mi.testproject.domain.TestEvent;
import com.test.mi.testproject.git.TestGitActivity;
import com.test.mi.testproject.jsoup.TestJsoupActivity;
import com.test.mi.testproject.listview.TestListViewActivty;
import com.test.mi.testproject.recycleView.RecycleItemActivity;
import com.test.mi.testproject.scroll.VPJoinListViewActivity;
import com.test.mi.testproject.scroll.viewpager.TestViewPagerActivity;
import com.test.mi.testproject.thread.TestThreadActivity;
import com.test.mi.testproject.webview.WebViewActivity;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, BottomNavigationBar.OnTabSelectedListener {

    private ListView listView;
    private String[] item = {"TestGit", "TestMatrix", "TestListView", "TestSQLite", "TestJsoup", "TestGlide", "TestThread", "TestWebView", "lv嵌套vp",
            "TestVP", "RecycleItem"};
    private BottomNavigationBar bottom_navigation_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();

        initData();


    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.lv_main_item, R.id.tv_main, item);
        listView.setAdapter(adapter);

        //tab
        bottom_navigation_bar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottom_navigation_bar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher_znt, "消息"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher_zhu, "圈子"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher_dan, "蛋场"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher_yu, "渔场"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher_tian, "我"))
                .initialise();

        bottom_navigation_bar.setTabSelectedListener(this);
        bottom_navigation_bar.setMode(BottomNavigationBar.MODE_FIXED);
    }


    private void initData() {
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (position == 0) {
            ARouter.getInstance().build(ARouterConstant.TestGitActivity).navigation();
            return;
        }

        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(MainActivity.this, TestGitActivity.class);
                break;
            case 1:
                intent.setClass(MainActivity.this, TestColorMatrixActivity.class);
                break;
            case 2:
                intent.setClass(MainActivity.this, TestListViewActivty.class);
                break;
            case 3:
                intent.setClass(MainActivity.this, TestDBActivity.class);
                break;
            case 4:
                intent.setClass(MainActivity.this, TestJsoupActivity.class);
                break;
            case 5:
                intent.setClass(MainActivity.this, TestJsoupActivity.class);
                break;
            case 6:
                StudentModel studentModel = new StudentModel();

                studentModel.name = "张三";
                studentModel.age = 18;
                studentModel.isJoined = false;
                intent.putExtra("data", studentModel);
                intent.setClass(MainActivity.this, TestThreadActivity.class);
                break;
            case 7:
                intent.setClass(MainActivity.this, WebViewActivity.class);
                break;
            case 8:
                intent.setClass(MainActivity.this, VPJoinListViewActivity.class);
                break;
            case 9:
                intent.setClass(MainActivity.this, TestViewPagerActivity.class);
                break;
            case 10:
                intent.setClass(MainActivity.this, RecycleItemActivity.class);
                break;
            default:
                intent.setClass(MainActivity.this, MainActivity.class);
                break;
        }
        startActivity(intent);
    }

    public void onEventMainThread(TestEvent event) {
        Log.i("onEventMainThread", "---LoginMsgEvent--event.type:" + event.type);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTabSelected(int position) {
        Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
