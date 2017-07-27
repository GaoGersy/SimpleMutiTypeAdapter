package com.example.gersy.multitype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toMultiBean(View v){
        startActivity(new Intent(this,MultiBeanActivity.class));
    }

    public void toMultiLayout(View v){
        startActivity(new Intent(this,MultiLayoutActivity.class));
    }
}
