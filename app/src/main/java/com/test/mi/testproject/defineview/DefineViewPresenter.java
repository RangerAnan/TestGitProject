package com.test.mi.testproject.defineview;

import com.qsmaxmin.qsbase.mvp.presenter.QsPresenter;

import java.util.ArrayList;

/**
 * Created by fcl on 18.4.2
 * desc:
 */

public class DefineViewPresenter extends QsPresenter<DefineViewActivity> {


    public ArrayList<DefineViewModel> getData() {
        ArrayList<DefineViewModel> dataList = new ArrayList<>();
        setData(dataList);
        return dataList;
    }


    /**
     * 添加数据
     */
    private void setData(ArrayList<DefineViewModel> dataList) {

        dataList.add(new DefineViewModel(DefineViewModel.VIEW_TYPE_ONE, "自定义控件", "", ""));

        dataList.add(new DefineViewModel(DefineViewModel.VIEW_TYPE_TWO, "竖向滚动", "", ""));

    }
}
