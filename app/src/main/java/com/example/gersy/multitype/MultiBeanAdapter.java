package com.example.gersy.multitype;

import com.example.gersy.multitype.bean.TextMsgBean;
import com.example.gersy.multitype.bean.TimeLineBean;
import com.gersion.library.adapter.MultiTypeAdapter;
import com.gersion.library.viewholder.BaseViewHolder;

/**
 * Created by gersy on 2017/7/26.
 */

public class MultiBeanAdapter extends MultiTypeAdapter {
    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        if (item instanceof TextMsgBean) {
            TextMsgBean userBean = (TextMsgBean) item;
            helper.setText(R.id.tv_name, userBean.userName);
        }else if (item instanceof TimeLineBean){
            helper.setText(R.id.tv_name,TimeUtil.getNowDatetime());
        }
    }
}
