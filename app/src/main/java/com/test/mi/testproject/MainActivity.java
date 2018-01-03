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

import com.test.mi.testproject.colormatrix.TestColorMatrixActivity;
import com.test.mi.testproject.database.TestDBActivity;
import com.test.mi.testproject.domain.StudentModel;
import com.test.mi.testproject.domain.TestEvent;
import com.test.mi.testproject.git.TestGitActivity;
import com.test.mi.testproject.jsoup.TestJsoupActivity;
import com.test.mi.testproject.listview.TestListViewActivty;
import com.test.mi.testproject.thread.TestThreadActivity;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private ListView listView;
    private String[] item = {"TestGit", "TestMatrix", "TestListView", "TestSQLite", "TestJsoup", "TestGlide", "TestThread"};

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

    }


    private void initData() {
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
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
}
