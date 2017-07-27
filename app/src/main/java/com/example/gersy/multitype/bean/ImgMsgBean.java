package com.example.gersy.multitype.bean;

import com.gersion.library.inter.IMultiLayout;

/**
 * Created by gersy on 2017/7/26.
 */

public class ImgMsgBean implements IMultiLayout{
    public String userName;
    public int age;

    int layoutId;
    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
}
