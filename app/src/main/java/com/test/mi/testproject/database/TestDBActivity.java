package com.test.mi.testproject.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.test.mi.testproject.R;
import com.test.mi.testproject.domain.PersonModel;

public class TestDBActivity extends AppCompatActivity {


    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        tv1 = findViewById(R.id.tv1);

        insertData();
        showSelectData();


    }

    private void insertData() {
        PersonModel model = new PersonModel();
        model.name = "大锤";
        model.dateTime = System.currentTimeMillis();
        PersonDaoManager.getInstance().insert(model);
    }

    private void showSelectData() {
        PersonModel model = PersonDaoManager.getInstance().getModel();
        if (model == null) {
            return;
        }
        tv1.setText("name:" + model.name + ",age:" + model.age + ",sex:" + model.sex + ",time:" + model.dateTime);
    }
}
