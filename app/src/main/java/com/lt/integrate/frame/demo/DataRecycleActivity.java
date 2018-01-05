package com.lt.integrate.frame.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;
import com.lt.integrate.frame.demo.listener.OnItemClickListener;
import com.lt.integrate.frame.demo.model.ItemObject;
import com.lt.integrate.frame.demo.presenter.QuestGetPrestener;
import com.lt.integrate.frame.demo.presenter.QuestGetPrestenerImpl;
import com.lt.integrate.frame.demo.view.QuestGetView;
import com.lt.integrate.frame.demo.view.adapter.BaseObjectAdapter;
import com.lt.integrate.frame.file.HttpUploadFileRequest;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DataRecycleActivity extends AppCompatActivity implements QuestGetView,OnItemClickListener{
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    BaseObjectAdapter  adapter;
    String[] urls = new String[]{Constants.HOST_yule_api, Constants.HOST_shishang_api};
    QuestGetPrestener questPrestener;
    int indexPostion =-1;
    List<Object> itemlist;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mContext = this.getApplicationContext();
        questPrestener = new QuestGetPrestenerImpl(this);
        itemlist = new ArrayList<>();
        pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setPushRefreshEnable(false);
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                initData();
            }

            @Override
            public void onLoadMore() {

            }
        });

        adapter = new BaseObjectAdapter(this);
        adapter.setOnItemClickListener(this);

        initData();
        findViewById(R.id.onBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

   private void initData(){
       indexPostion++;
       if(indexPostion>urls.length-1){
           indexPostion =0 ;
       }
      questPrestener.loadQuestGetPrestenerData(this,urls[indexPostion]);
   }


    @Override
    public void onHttpSucesss(List<Object> list, String data) {
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        if(itemlist!= null){
            itemlist.clear();
        }
        if(list == null || list.size() == 0){
            return;
        }
        itemlist.addAll(list);
        if(itemlist.size() > 0){
            adapter.setObjectData(itemlist);
            pullLoadMoreRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onHttpError(String error) {

    }

    @Override
    public void onItemClickListener(View view, int position) {
        ItemObject objectItem = (ItemObject) itemlist.get(position);
        if(objectItem !=null) {
            Intent it = new Intent(this, DetailsActivity.class);
            it.putExtra("url", objectItem.getWebUrl());
            startActivity(it);
        }
    }
}
