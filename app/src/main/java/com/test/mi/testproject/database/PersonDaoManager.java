package com.test.mi.testproject.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.test.mi.testproject.domain.PersonModel;

import java.util.ArrayList;

/**
 * Created by fcl on 2017/11/18
 * desc:
 */

public class PersonDaoManager {

    public static PersonDaoManager instance;
    //调用该方法，才会创建表，回调onCreate
    private final SQLiteDatabase writableDatabase;
    private final SQLiteDatabase readableDatabase;
    public DBHelper dbHelper;

    private static final byte[] mDBLock = new byte[0];

    public PersonDaoManager() {
        dbHelper = new DBHelper();
        readableDatabase = dbHelper.getReadableDatabase();
        writableDatabase = dbHelper.getWritableDatabase();
    }

    public static synchronized PersonDaoManager getInstance() {
        if (instance == null) {
            instance = new PersonDaoManager();
        }
        return instance;
    }


    public PersonModel getModel() {
        ArrayList<PersonModel> list = new ArrayList<>();
        String sql = "select * from " + SQLManager.personTableName + " order by " + SQLManager.dateTime + " desc";
        Cursor cursor = null;
        try {
            if (dbHelper == null) {
                return null;
            }
            cursor = readableDatabase.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                list.add(cursorToModle(cursor));
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    private PersonModel cursorToModle(Cursor cursor) {
        PersonModel model = new PersonModel();
        model.name = cursor.getString(cursor.getColumnIndex(SQLManager.name));
        model.age = cursor.getInt(cursor.getColumnIndex(SQLManager.age));
        model.sex = cursor.getString(cursor.getColumnIndex(SQLManager.sex));
        model.dateTime = cursor.getInt(cursor.getColumnIndex(SQLManager.dateTime));
        return model;
    }


    public void insert(PersonModel model) {
        String sql = " replace into " + SQLManager.personTableName + " ( name, dateTime" + ") values( '"
                + model.name + "','" + model.dateTime + "')";
        execSQL(sql);
    }

    public void execSQL(String sql) {
        synchronized (mDBLock) {
            if (dbHelper == null) {
                return;
            }
            writableDatabase.execSQL(sql, new String[]{});
        }
    }
}
