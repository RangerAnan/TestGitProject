package com.test.mi.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.test.mi.testproject.git.TestGitActivity;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private ListView listView;
    private String[] item = {"TestGit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initData();
    }

    private void initView() {
        listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.lv_main_item, R.id.tv_main, item);
        listView.setAdapter(adapter);
    }


    private void initData() {
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent();
        if (position == 0) {
            intent.setClass(MainActivity.this, TestGitActivity.class);
        }
        startActivity(intent);
    }
}
