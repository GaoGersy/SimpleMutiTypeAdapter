package com.example.gersy.multitype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gersy.multitype.bean.TimeLineBean;
import com.example.gersy.multitype.bean.ImgMsgBean;
import com.example.gersy.multitype.bean.TextMsgBean;

import java.util.ArrayList;

public class MultiBeanActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_bean);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        MultiBeanAdapter adapter = new MultiBeanAdapter();
        adapter.registerMultiBean(TextMsgBean.class,R.layout.item_right_text);
        adapter.registerMultiBean(ImgMsgBean.class,R.layout.item_left_img);
        adapter.registerMultiBean(TimeLineBean.class,R.layout.item_time_line);
        mRecyclerView.setAdapter(adapter);
        ArrayList list = new ArrayList();

        for (int i=0;i<100;i++){
            if (i%2==0) {
                ImgMsgBean bean = new ImgMsgBean();
                bean.userName = "用户" + i;
                list.add(bean);
            }else if (i%3==0){
                TimeLineBean bean = new TimeLineBean();
                list.add(bean);
            }else{
                TextMsgBean bean = new TextMsgBean();
                bean.userName = "老师" + i;
                list.add(bean);
            }
        }
        adapter.setItems(list);
    }
}
