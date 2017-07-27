package com.example.gersy.multitype.bean;

import com.example.gersy.multitype.R;
import com.gersion.library.inter.IMultiLayout;

/**
 * Created by gersy on 2017/7/26.
 */

public class DoubleIconBean implements IMultiLayout{
    int layoutId = R.layout.item_double_icon;
    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
}
