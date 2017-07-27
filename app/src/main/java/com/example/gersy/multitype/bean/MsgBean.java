package com.example.gersy.multitype.bean;

import com.example.gersy.multitype.R;
import com.gersion.library.inter.IMultiLayout;

/**
 * Created by gersy on 2017/7/27.
 */

public class MsgBean implements IMultiLayout {
    public boolean left = true;
    public String msg;
    public int type;//0:左边的文字 1：右边的文字 2：左边的图片 3：右边的图片 4：左边的定位 5：右边的定位 6：时间线
    int layoutId;
    @Override
    public int getLayoutId() {
        if (type==1){
            return R.layout.item_right_text;
        }else if (type==2){
            return R.layout.item_left_img;
        }else if (type==3){
            return R.layout.item_right_img;
        }else if (type==4){
            return R.layout.item_left_location;
        }else if (type==5){
            return R.layout.item_right_location;
        }else if (type==6){
            return R.layout.item_time_line;
        }
        return R.layout.item_left_text;
    }

    @Override
    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
}
