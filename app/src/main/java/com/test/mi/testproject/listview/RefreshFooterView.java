package com.test.mi.testproject.listview;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.test.mi.testproject.R;

/**
 * Created by fcl on 2017/11/15
 * desc:
 */

public class RefreshFooterView extends RelativeLayout implements RefreshFooter {

    private LinearLayout mFooter;
    private ProgressBar mFooterProgrssBar;
    private TextView mFooterText;

    protected boolean mLoadmoreFinished = false;
    private Context context;

    private String tag = "RefreshFooterView";

    protected int mPaddingTop = 20;
    protected int mPaddingBottom = 20;

    private RefreshKernel kernel;

    public RefreshFooterView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        View footer = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_footer, null, false);
        mFooter = footer.findViewById(R.id.result_footer);
        mFooterProgrssBar = footer.findViewById(R.id.pb_load_more);
        mFooterText = footer.findViewById(R.id.tv_load_more);

        addView(footer);
        setGravity(Gravity.CENTER);
    }

    @Override
    public void onPullingUp(float percent, int offset, int footerHeight, int extendHeight) {

    }

    @Override
    public void onPullReleasing(float percent, int offset, int footerHeight, int extendHeight) {

    }

    /**
     * 设置数据全部加载完成，将不能再次触发加载功能
     */
    @Override
    public boolean setLoadmoreFinished(boolean finished) {
        if (mLoadmoreFinished != finished) {
            mLoadmoreFinished = finished;
            if (finished) {
                mFooterText.setText(context.getResources().getString(R.string.pull_to_load_more_complete));
                mFooterProgrssBar.setVisibility(View.GONE);
            } else {
                mFooterText.setText(context.getResources().getString(R.string.pull_to_load_more));
            }
        }
        return true;
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {
        if (colors.length > 0) {
            if (!(getBackground() instanceof BitmapDrawable)) {
                setPrimaryColor(colors[0]);
            }
        }
    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
        Log.i(tag, "onInitialized---");
        this.kernel = kernel;
        kernel.requestDrawBackgoundForFooter(ContextCompat.getColor(context, R.color.btn_orange1));
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {

    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        Log.i(tag, "onFinish--success:" + success);
        return 0;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        Log.i(tag, "onFinish--newState:" + newState.toString() + "---mLoadmoreFinished:" + mLoadmoreFinished);
        if (!mLoadmoreFinished) {
            switch (newState) {
                case None:
//                    restoreRefreshLayoutBackground();
//                    mArrowView.setVisibility(VISIBLE);
                case PullToUpLoad:
//                    mTitleText.setText(REFRESH_FOOTER_PULLUP);
//                    mArrowView.animate().rotation(180);
                    break;
                case Loading:
//                    mArrowView.setVisibility(GONE);
//                    mTitleText.setText(REFRESH_FOOTER_LOADING);
                    break;
                case ReleaseToLoad:
//                    mTitleText.setText(REFRESH_FOOTER_RELEASE);
//                    mArrowView.animate().rotation(0);
//                    replaceRefreshLayoutBackground(refreshLayout);
                    break;
                case Refreshing:
//                    mTitleText.setText(REFRESH_FOOTER_REFRESHING);
//                    mProgressView.setVisibility(GONE);
//                    mArrowView.setVisibility(GONE);
                    break;
                default:
                    break;
            }
        }
    }

    public void setPrimaryColor(int color) {
        setBackgroundColor(color);
        if (kernel != null) {
            kernel.requestDrawBackgoundForFooter(color);
        }
    }
}
