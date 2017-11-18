package com.test.mi.testproject.database;

/**
 * Created by fcl on 2017/11/18
 * desc:
 */

public class SQLManager {

    private static SQLManager instance;

    public static SQLManager getInstance() {
        if (instance == null) {
            instance = new SQLManager();
        }
        return instance;
    }


    public static final String personTableName = "person";
    public static final String personId = "personId";
    public static final String name = "name";
    public static final String age = "age";
    public static final String sex = "sex";
    public static final String dateTime = "dateTime";

    public String createPersonTable() {
        return "create table " + personTableName + "("
                + personId + " INTEGER primary key autoincrement,"
                + name + " text   null,"
                + age + " INTEGER DEFAULT(0)   null,"
                + sex + " text   null,"
                + dateTime + " INTEGER DEFAULT(0)   null,"
                + " UNIQUE(" + personId + "))";

    }

}
