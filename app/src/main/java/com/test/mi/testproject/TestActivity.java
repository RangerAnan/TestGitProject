package com.test.mi.testproject;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fcl on 18.5.18
 * desc:
 */

public class TestActivity extends AppCompatActivity {
    private Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View rootView = View.inflate(this, R.layout.activity_test_toolbar, null);

        mToolbar = rootView.findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= 21) {
            mToolbar.setElevation(0);
        }
        setSupportActionBar(mToolbar);

        setContentView(rootView);
    }
}
