package com.test.mi.testproject.recycleView.itemDecoration;

import android.view.View;

/**
 * Created by fcl on 18.3.29
 * desc:
 */

public class ExampleStickyView implements StickyView {

    @Override
    public boolean isStickyView(View view) {
        return (Boolean) view.getTag();
    }

    @Override
    public int getStickViewType() {
        return 11;
    }
}
