package com.example.gersy.multitype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gersy.multitype.bean.MsgBean;

import java.util.ArrayList;
import java.util.Random;

public class MultiLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        MultiLayoutAdapter adapter = new MultiLayoutAdapter();
        adapter.registerMultiLayout(R.layout.item_right_text);
        adapter.registerMultiLayout(R.layout.item_left_text);
        adapter.registerMultiLayout(R.layout.item_left_img);
        adapter.registerMultiLayout(R.layout.item_right_img);
        adapter.registerMultiLayout(R.layout.item_left_location);
        adapter.registerMultiLayout(R.layout.item_right_location);
        adapter.registerMultiLayout(R.layout.item_time_line);
        mRecyclerView.setAdapter(adapter);
        ArrayList list = new ArrayList();

        for (int i=0;i<100;i++){
            Random random = new Random();
            int type = random.nextInt(7);
            MsgBean msgBean = new MsgBean();
            msgBean.type = type;
            msgBean.msg = "文字消息"+i;
            list.add(msgBean);
        }

        adapter.setItems(list);
    }
}
