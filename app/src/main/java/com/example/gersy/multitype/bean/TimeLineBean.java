package com.example.gersy.multitype.bean;

import com.example.gersy.multitype.R;
import com.gersion.library.inter.IMultiLayout;

/**
 * Created by gersy on 2017/7/26.
 */

public class TimeLineBean implements IMultiLayout{
    int layoutId = R.layout.item_time_line;
    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
}
