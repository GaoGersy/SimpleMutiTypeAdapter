package com.example.gersy.multitype;

import com.example.gersy.multitype.bean.MsgBean;
import com.gersion.library.adapter.MultiTypeAdapter;
import com.gersion.library.inter.IMultiLayout;
import com.gersion.library.viewholder.BaseViewHolder;

/**
 * Created by gersy on 2017/7/26.
 */

public class MultiLayoutAdapter extends
        MultiTypeAdapter<IMultiLayout,BaseViewHolder<IMultiLayout>> {

    @Override
    protected void convert(BaseViewHolder helper, IMultiLayout item) {
        MsgBean bean = (MsgBean) item;
        int type = bean.type;
        if (type==0||type==1){
            helper.setText(R.id.tv_name,bean.msg);
        }else if (type==6){
            helper.setText(R.id.tv_name,TimeUtil.getNowDatetime());
        }
    }
}
