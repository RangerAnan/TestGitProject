package com.test.mi.testproject.git;

import android.widget.TextView;

import com.test.mi.testproject.base.BaseActivity;
import com.test.mi.testproject.R;
import com.test.mi.testproject.domain.TestEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.greenrobot.event.EventBus;

public class TestGitActivity extends BaseActivity {


    private TextView git_tv1;
    private TextView git_tv2;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_git;
    }

    @Override
    protected void initView() {
        git_tv1 = (TextView) findViewById(R.id.git_tv1);
        git_tv2 = (TextView) findViewById(R.id.git_tv2);
    }

    @Override
    protected void initData() {

        String date = "2017-12-27T02:39:40.161Z";
        date = date.replace("Z", " UTC");   //注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        try {
            Date d = format.parse(date);
            git_tv1.setText(d + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(new TestEvent(0));
        super.onDestroy();
        EventBus.getDefault().post(new TestEvent(10));
    }
}
