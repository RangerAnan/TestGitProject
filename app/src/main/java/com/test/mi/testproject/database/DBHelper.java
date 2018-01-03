package com.test.mi.testproject.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.test.mi.testproject.GlobalApplication;
import com.test.mi.testproject.constant.ProConstant;

/**
 * Created by fcl on 2017/11/18
 * desc:
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper() {
        super(GlobalApplication.getContext(), ProConstant.dbName, null, ProConstant.DB_VERSION);
        Log.i("DBHelper", "构造方法");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBHelper", "onCreate");
        db.execSQL(SQLManager.getInstance().createPersonTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBHelper", "oldVersion：" + oldVersion + "--newVersion:" + newVersion);
        if (oldVersion < newVersion) {
            try {
                //update
                addColumn(db);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void addColumn(SQLiteDatabase db) throws InterruptedException {
        Thread.sleep(10000);
        for (int i = 400; i < 600; i++) {
            String addColumnsSql = "ALTER TABLE  " + SQLManager.personTableName + "  ADD COLUMN " + SQLManager.test + i + " text   null;";
            Log.i("DBHelper", "addColumn " + i);
            db.execSQL(addColumnsSql);
        }
    }
}
