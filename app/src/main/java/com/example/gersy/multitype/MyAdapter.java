package com.example.gersy.multitype;

import com.example.gersy.multitype.bean.TeacherBean;
import com.example.gersy.multitype.bean.UserBean;
import com.gersion.library.adapter.MultiTypeAdapter;
import com.gersion.library.viewholder.BaseViewHolder;

/**
 * Created by gersy on 2017/7/26.
 */

public class MyAdapter extends MultiTypeAdapter {
    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        if (item instanceof UserBean) {
            UserBean userBean = (UserBean) item;
            helper.setText(R.id.tv_name, userBean.userName);
        }else if (item instanceof TeacherBean){
            TeacherBean teacherBean = (TeacherBean) item;
            helper.setText(R.id.tv_name, teacherBean.userName);
        }
    }
}
