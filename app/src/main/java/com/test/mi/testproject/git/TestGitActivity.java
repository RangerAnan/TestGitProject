package com.test.mi.testproject.git;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.test.mi.testproject.base.BaseActivity;
import com.test.mi.testproject.R;
import com.test.mi.testproject.constant.ARouterConstant;
import com.test.mi.testproject.domain.TestEvent;

import junit.framework.Test;

import org.aspectj.runtime.internal.AroundClosure;
import org.junit.rules.TestName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;

import de.greenrobot.event.EventBus;

@Route(path = ARouterConstant.TestGitActivity)
public class TestGitActivity extends BaseActivity implements OnClickListener {


    ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };
    private TextView git_tv1;
    private TextView git_tv2;
    private LinearLayout containerView;
    private String threadName;
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            threadName += threadLocal.get();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    git_tv2.setText(threadName);
                }
            });
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_git;
    }

    @Override
    protected void initView() {
        git_tv1 = (TextView) findViewById(R.id.git_tv1);
        git_tv2 = (TextView) findViewById(R.id.git_tv2);
        containerView = (LinearLayout) findViewById(R.id.containerView);

        //添加view
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.view_title, null,false);
        containerView.addView(inflate);
    }

    @Override
    protected void initData() {

        //test1 date
        String date = "2017-12-27T02:39:40.161Z";
        date = date.replace("Z", " UTC");   //注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        try {
            Date d = format.parse(date);
            git_tv1.setText(d + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //
        threadName = threadLocal.get() + "/";
        thread.start();


        //test2 enum
        for (TestEnum testEnum : TestEnum.values()) {
            Log.i(getTag(), testEnum.name());
        }

        Log.i(getTag(), TestEnum.singleChat.getDesc());
        Log.i(getTag(), TestEnum.groupChat.getNum() + "");

        EnumSet<TestEnum> testEnums = EnumSet.allOf(TestEnum.class);
        for (TestEnum testEnum : testEnums) {
            Log.i(getTag(), testEnum.name());
        }

        //set click
        git_tv2.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(new TestEvent(0));
        super.onDestroy();
        EventBus.getDefault().post(new TestEvent(10));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.git_tv2:
                ARouter.getInstance().build(ARouterConstant.TestListViewActivty)
                        .withString("name", "张三").withInt("age", 28).withBoolean("sex", true).navigation();
                break;
            default:
                break;

        }
    }
}
