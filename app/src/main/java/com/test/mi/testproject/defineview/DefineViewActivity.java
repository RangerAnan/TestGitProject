package com.test.mi.testproject.defineview;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.QsActivity;
import com.qsmaxmin.qsbase.mvp.presenter.Presenter;
import com.test.mi.testproject.R;
import com.test.mi.testproject.recycleView.MyRecycleAdapter;
import com.test.mi.testproject.recycleView.model.Performer;

import java.util.ArrayList;

/**
 * Created by fcl on 18.4.2
 * desc:
 */

@Presenter(DefineViewPresenter.class)
public class DefineViewActivity extends QsActivity<DefineViewPresenter> implements DefineViewRecycleAdapter.OnItemClickListener {

    @Bind(R.id.recycleView)
    RecyclerView recycleView;

    private ArrayList<DefineViewModel> mData;
    private DefineViewRecycleAdapter adapter;

    @Override
    public int layoutId() {
        return R.layout.activity_test_define_view;
    }

    @Override
    public void initData(Bundle bundle) {
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mData = getPresenter().getData();

        adapter = new DefineViewRecycleAdapter(mData, getContext());
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
        recycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }


    @Override
    public void onItemClick(int position, DefineViewModel model, int size) {

    }
}
