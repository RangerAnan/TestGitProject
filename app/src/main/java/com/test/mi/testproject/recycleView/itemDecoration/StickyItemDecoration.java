package com.test.mi.testproject.recycleView.itemDecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fcl on 18.3.29
 * desc:吸附的原理就是在RecycleView顶部手动绘制一个view，和其中的一个item类型一模一样，且随着滑动，动态更新数据。
 */

public class StickyItemDecoration extends RecyclerView.ItemDecoration {


    /**
     * 吸附的itemView
     */
    private View mStickyItemView;

    /**
     * 吸附itemView 距离顶部
     */
    private int mStickyItemViewMarginTop;

    /**
     * 吸附itemView 高度
     */
    private int mStickyItemViewHeight;

    /**
     * 通过它获取到需要吸附view的相关信息
     */
    private StickyView mStickyView;

    /**
     * 滚动过程中当前的UI是否可以找到吸附的view
     */
    private boolean mCurrentUIFindStickView;

    /**
     * adapter
     */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;

    /**
     * viewHolder
     */
    private RecyclerView.ViewHolder mViewHolder;

    /**
     * position list
     */
    private List<Integer> mStickyPositionList = new ArrayList<>();

    /**
     * layout manager
     */
    private LinearLayoutManager mLayoutManager;

    /**
     * 绑定数据的position
     */
    private int mBindDataPosition = -1;

    /**
     * paint
     */
    private Paint mPaint;

    public StickyItemDecoration() {
        mStickyView = new ExampleStickyView();
        initPaint();
    }

    /**
     * init paint
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        mLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
        mCurrentUIFindStickView = false;

        for (int m = 0, size = parent.getChildCount(); m < size; m++) {
            View view = parent.getChildAt(m);

            /**
             * 如果是吸附的view
             */
            if (mStickyView.isStickyView(view)) {
                //当前UI当中是否找到了需要吸附的View，此时设置为true
                mCurrentUIFindStickView = true;
                //这个方法是得到吸附View的viewHolder
                getStickyViewHolder(parent);
                //缓存需要吸附的View在列表当中的下标position
                cacheStickyViewPosition(m);

                //如果当前吸附的view距离 顶部小于等于0，然后给吸附的View绑定数据，计算View的宽高
                if (view.getTop() <= 0) {
                    bindDataForStickyView(mLayoutManager.findFirstVisibleItemPosition(), parent.getMeasuredWidth());
                } else {
                    //如果大于0，从position缓存中取得当前的position，然后绑定数据，计算View的宽高
                    if (mStickyPositionList.size() > 0) {
                        if (mStickyPositionList.size() == 1) {
                            bindDataForStickyView(mStickyPositionList.get(0), parent.getMeasuredWidth());
                        } else {
                            int currentPosition = getStickyViewPositionOfRecyclerView(m);
                            int indexOfCurrentPosition = mStickyPositionList.lastIndexOf(currentPosition);
                            bindDataForStickyView(mStickyPositionList.get(indexOfCurrentPosition - 1), parent.getMeasuredWidth());
                        }
                    }
                }
                //计算吸附的View距离顶部的高度
                if (view.getTop() > 0 && view.getTop() <= mStickyItemViewHeight) {
                    mStickyItemViewMarginTop = mStickyItemViewHeight - view.getTop();
                } else {
                    mStickyItemViewMarginTop = 0;
                }

                //绘制吸附的View
                drawStickyItemView(c);
                break;
            }
        }

        //如果在当前的列表视图中没有找到需要吸附的View
        if (!mCurrentUIFindStickView) {
            mStickyItemViewMarginTop = 0;
            //如果已经滑动到底部了，就绑定最后一个缓存的position的View，这种情况一般出现在快速滑动列表的时候吸附View出现错乱，所以需要绑定一下
            if (mLayoutManager.findFirstVisibleItemPosition() + parent.getChildCount() == parent.getAdapter().getItemCount()) {
                bindDataForStickyView(mStickyPositionList.get(mStickyPositionList.size() - 1), parent.getMeasuredWidth());
            }
            //绘制View
            drawStickyItemView(c);
        }
    }

    /**
     * 给StickyView绑定数据
     *
     * @param position
     */
    private void bindDataForStickyView(int position, int width) {
        if (mBindDataPosition == position) {
            return;
        }

        mBindDataPosition = position;
        mAdapter.onBindViewHolder(mViewHolder, mBindDataPosition);
        measureLayoutStickyItemView(width);
        mStickyItemViewHeight = mViewHolder.itemView.getBottom() - mViewHolder.itemView.getTop();
    }

    /**
     * 缓存吸附的view position
     *
     * @param m
     */
    private void cacheStickyViewPosition(int m) {
        int position = getStickyViewPositionOfRecyclerView(m);
        if (!mStickyPositionList.contains(position)) {
            mStickyPositionList.add(position);
        }
    }

    /**
     * 得到吸附view在RecyclerView中 的position
     *
     * @param m
     * @return
     */
    private int getStickyViewPositionOfRecyclerView(int m) {
        return mLayoutManager.findFirstVisibleItemPosition() + m;
    }

    /**
     * 得到吸附viewHolder
     *
     * @param recyclerView
     */
    private void getStickyViewHolder(RecyclerView recyclerView) {
        if (mAdapter != null) {
            return;
        }
        mAdapter = recyclerView.getAdapter();
        mViewHolder = mAdapter.onCreateViewHolder(recyclerView, mStickyView.getStickViewType());
        mStickyItemView = mViewHolder.itemView;
    }

    /**
     * 计算布局吸附的itemView
     *
     * @param parentWidth
     */
    private void measureLayoutStickyItemView(int parentWidth) {
        if (!mStickyItemView.isLayoutRequested()) {
            return;
        }

        int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        int heightSpec;

        ViewGroup.LayoutParams layoutParams = mStickyItemView.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }

        mStickyItemView.measure(widthSpec, heightSpec);
        mStickyItemView.layout(0, 0, mStickyItemView.getMeasuredWidth(), mStickyItemView.getMeasuredHeight());
    }

    /**
     * 绘制吸附的itemView
     *
     * @param canvas
     */
    private void drawStickyItemView(Canvas canvas) {
        int saveCount = canvas.save();
        canvas.translate(0, -mStickyItemViewMarginTop);
        mStickyItemView.draw(canvas);
        canvas.restoreToCount(saveCount);
    }
}
