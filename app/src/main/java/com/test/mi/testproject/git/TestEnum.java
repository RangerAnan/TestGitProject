package com.test.mi.testproject.git;

/**
 * Created by fcl on 18.1.15.
 */

public enum TestEnum {

    singleChat(10, "aa"), groupChat(20, "bb"), publicChat(30, "cc");

    private int mNum;
    private String mDesc;

    private TestEnum(int num, String desc) {
        this.mNum = num;
        this.mDesc = desc;
    }

    public int getNum() {
        return mNum;
    }

    public String getDesc() {
        return mDesc;
    }
}
