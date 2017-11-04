package com.test.mi.testproject.git;

import android.widget.TextView;

import com.test.mi.testproject.BaseActivity;
import com.test.mi.testproject.R;

public class TestGitActivity extends BaseActivity {


    private TextView git_tv1;
    private TextView git_tv2;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_git;
    }

    @Override
    protected void initView() {
        git_tv1 = findViewById(R.id.git_tv1);
        git_tv2 = findViewById(R.id.git_tv2);
    }

    @Override
    protected void initData() {

    }
}
