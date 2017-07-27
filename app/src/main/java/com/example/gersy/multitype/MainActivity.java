package com.example.gersy.multitype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gersy.multitype.bean.UserBean;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter adapter = new MyAdapter();
        adapter.registerMultiLayout(R.layout.item_user);
        adapter.registerMultiLayout(R.layout.item_teacher);
        adapter.registerMultiLayout(R.layout.item_double_icon);
        mRecyclerView.setAdapter(adapter);
        ArrayList list = new ArrayList();
//        for (int i=0;i<20;i++){
//            if (i%2==0) {
//                TeacherBean bean = new TeacherBean();
//                bean.userName = "用户" + i;
//                list.add(bean);
//            }else if (i%3==0){
//                DoubleIconBean bean = new DoubleIconBean();
//                list.add(bean);
//            }else{
//                TeacherBean bean = new TeacherBean();
//                bean.userName = "老师" + i;
//                list.add(bean);
//            }
//        }

        for (int i=0;i<20;i++){
            if (i%2==0) {
                UserBean userBean = new UserBean();
                userBean.setLayoutId(R.layout.item_user);
                userBean.userName = "用户" + i;
                list.add(userBean);
            }else if (i%3==0){
                UserBean userBean = new UserBean();
                userBean.setLayoutId(R.layout.item_double_icon);
                userBean.userName = "两个图" + i;
                list.add(userBean);
            }else{
                UserBean userBean = new UserBean();
                userBean.setLayoutId(R.layout.item_teacher);
                userBean.userName = "老师" + i;
                list.add(userBean);
            }
        }


        adapter.setItems(list);
    }
}
