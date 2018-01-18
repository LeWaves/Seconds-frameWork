package com.lt.integrate.frame.demo.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;


import com.lt.integrate.frame.demo.R;
import com.lt.integrate.frame.scrollview.recycleview.RecycleViewItemLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/5/31.
 */

public class ThirdActivity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    private ThirdAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        init();
    }

    private void init() {
        initRecycleView();
    }

    private void initRecycleView() {
        for(int a=0; a<50 ; a++){
            list.add("这个是假设的数据"+a);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));

        RecycleViewItemLine recycleViewItemLine = new RecycleViewItemLine(this, LinearLayout.HORIZONTAL);
        recyclerView.addItemDecoration(recycleViewItemLine);
        recyclerView.setAdapter(adapter = new ThirdAdapter(this,list));
    }
}
