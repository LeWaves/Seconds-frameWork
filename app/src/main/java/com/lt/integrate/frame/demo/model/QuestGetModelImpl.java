package com.lt.integrate.frame.demo.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.lt.integrate.frame.IOkHttpClient;
import com.lt.integrate.frame.demo.listener.HttpsListenerInterface;
import com.lt.integrate.frame.demo.utils.AppUtils;
import com.lt.integrate.frame.http.HttpJSONRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iclick on 2017/9/26.
 */

public class QuestGetModelImpl implements QuestGetModel {

    @Override
    public void loadQuestGetData(Context context, String url,final HttpsListenerInterface listener) {
         if(AppUtils.isNetworkAvailable(context)){

             new HttpJSONRequest(context, IOkHttpClient.GET, url, new HttpJSONRequest.RequestNetWork() {
                 @Override
                 public void onSuccess(JSONObject response) {
                     Log.i("iax","onSuccess JSONObject :"+response.toString());
                     try {
                         List<Object> itemList = new ArrayList<>();
                         itemList.clear();
                         JSONObject obj = new JSONObject(response.toString());

                         if(obj.has("result")){
                             JSONObject resultObj = obj.getJSONObject("result");

                             if(resultObj.has("data")){
                                 JSONArray mArray = resultObj.getJSONArray("data") ;
                                 if(mArray.length() > 0){
                                     for(int i = 0 ; i < mArray.length() ; i++){
                                         JSONObject itemObj = mArray.getJSONObject(i);
                                         ItemObject bean = new ItemObject();
                                         if(itemObj.has("title")){
                                             bean.setTitle(itemObj.getString("title"));
                                         }
                                         if(itemObj.has("url")){
                                             bean.setWebUrl(itemObj.getString("url"));
                                         }

                                         if(itemObj.has("thumbnail_pic_s")) {
                                             if(!itemObj.isNull("thumbnail_pic_s")) {
                                                 bean.setImgUrl(itemObj.getString("thumbnail_pic_s"));
                                             }
                                         }
                                         if(itemObj.has("thumbnail_pic_s03")) {
                                             if(!itemObj.isNull("thumbnail_pic_s03")) {
                                                 bean.setImgUrl(itemObj.getString("thumbnail_pic_s03"));
                                             }
                                         }
                                         if(itemObj.has("thumbnail_pic_s02")) {
                                             if(!itemObj.isNull("thumbnail_pic_s02")) {
                                                 bean.setImgUrl(itemObj.getString("thumbnail_pic_s02"));
                                             }
                                         }

                                         itemList.add(bean);

                                     }
                                     listener.onSucessHttps(itemList,null);
                                 }
                             }
                         }
                     } catch (JSONException e) {
                         e.printStackTrace();
                         Log.i("iax","解析数据异常:"+e.toString());
                     }
                 }

                 @Override
                 public void onFailure(VolleyError error) {
                     listener.onErrorHttps("解析异常");
                 }
             });



         }else{
             listener.onErrorHttps("网络异常");
         }
    }
}
