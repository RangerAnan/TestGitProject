package com.test.mi.testproject.thread;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.QsActivity;
import com.qsmaxmin.qsbase.mvp.presenter.Presenter;
import com.test.mi.testproject.R;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@Presenter(ThreadPersenter.class)
public class TestThreadActivity extends QsActivity<ThreadPersenter> {

    @Bind(R.id.thread_tv1)
    TextView thread_tv1;


    @Override
    public int layoutId() {
        return R.layout.activity_test_thread;
    }

    @Override
    public void initData(Bundle bundle) {

        //
        final ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setDaemon(true);
                thread.setName("Thread--001");
                return thread;
            }
        });

        final long startTime = System.currentTimeMillis();
        poolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long endTime = System.currentTimeMillis();
                final long time = (endTime - startTime) / 1000;
                Log.i(initTag(), "time:" + time);
                if (time >= 5) {
                    poolExecutor.shutdown();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            thread_tv1.setText(time + "");
                        }
                    });
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
