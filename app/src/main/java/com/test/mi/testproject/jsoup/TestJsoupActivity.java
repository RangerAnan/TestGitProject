package com.test.mi.testproject.jsoup;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.QsActivity;
import com.qsmaxmin.qsbase.mvp.presenter.Presenter;
import com.test.mi.testproject.R;

@Presenter(JsoupPrensenter.class)
public class TestJsoupActivity extends QsActivity<JsoupPrensenter> {

    @Bind(R.id.tv1)
    TextView tv1;

    @Bind(R.id.tv2)
    TextView tv2;

    @Override
    public int layoutId() {
        return R.layout.activity_test_jsoup;
    }

    @Override
    public void initData(Bundle bundle) {
        String title = getPresenter().getHtmlTitle();
        Log.i(initTag(), "title:" + title);
        tv1.setText(title);
        String content = getPresenter().getHtmlContent();
        tv2.setText(content);
        Log.i(initTag(), "content:" + content);
    }
}
