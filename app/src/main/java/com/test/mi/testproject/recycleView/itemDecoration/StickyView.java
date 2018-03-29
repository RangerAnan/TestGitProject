package com.test.mi.testproject.recycleView.itemDecoration;

import android.view.View;

/**
 * Created by fcl on 18.3.29
 * desc:
 */

public interface StickyView {


    /**
     * 是否是吸附view
     * @param view
     * @return
     */
    boolean isStickyView(View view);

    /**
     * 得到吸附view的itemType
     * @return
     */
    int getStickViewType();
}
