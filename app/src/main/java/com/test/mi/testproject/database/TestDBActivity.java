package com.test.mi.testproject.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.test.mi.testproject.R;
import com.test.mi.testproject.domain.PersonModel;

public class TestDBActivity extends AppCompatActivity implements OnClickListener {


    private TextView tv1;
    private TextView importData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        tv1 = (TextView) findViewById(R.id.tv1);
        importData = (TextView) findViewById(R.id.importData);

//        insertData();
//        showSelectData();

        //导入数据
        insertBatchData();

        importData.setOnClickListener(this);
    }

    private void insertData() {
        PersonModel model = new PersonModel();
        model.name = "大锤";
        model.dateTime = System.currentTimeMillis();
        PersonDaoManager.getInstance().insert(model);
    }

    private void insertBatchData() {
        for (int i = 0; i < 30; i++) {
            PersonModel model = new PersonModel();
            model.name = "大锤" + i;
            model.age = i;
            Log.i("DBHelper", "insertBatchData  " + i);
            PersonDaoManager.getInstance().insert(model);
        }
    }

    private void showSelectData() {
        PersonModel model = PersonDaoManager.getInstance().getModel();
        if (model == null) {
            return;
        }
        tv1.setText("name:" + model.name + ",age:" + model.age + ",sex:" + model.sex + ",time:" + model.dateTime);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.importData:
                //1.修改表结构
                //2.导数据
                break;
            default:
                break;
        }
    }
}
