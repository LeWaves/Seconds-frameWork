package com.lt.integrate.frame.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by iclick on 2017/9/21.
 */

public class HttpJSONRequest {
    /**
     * 请求类型。如: GET,POST等
     */
    private int method;

    /**
     * 请求的URL
     */
    private String url;

    /**
     * 请求的参数
     */
    private JSONObject requestParameter;
    private Map<String, String> requestHeaderParameter;

    /**
     * 请求成功的响应
     */
    private RequestNetWork requestNetWork;

    public HttpJSONRequest(Context context,int method, String url,JSONObject parameter, RequestNetWork requestNetWork, String requestTag) {
        initRequestQueue(context,url);
        this.method = method;
        this.url = url;
        this.requestNetWork = requestNetWork;

        this.requestParameter = parameter;

        createJsonObjectRequest(requestTag);
    }

    private void createJsonObjectRequest(String requestTag) {
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                method,
                url,
                requestParameter,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        requestNetWork.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestNetWork.onFailure(error);

                    }
                }
        );

        mJsonObjectRequest.setTag(requestTag);
        RequestManager.getRequestQueue().add(mJsonObjectRequest);
    }
    public HttpJSONRequest(Context context,int method, String url,JSONObject parameter, RequestNetWork requestNetWork) {
        initRequestQueue(context,url);
        this.method = method;
        this.url = url;
        this.requestNetWork = requestNetWork;

        this.requestParameter = parameter;

        createJsonObjectRequest();
    }

    private void createJsonObjectRequest() {
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                method,
                url,
                requestParameter,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        requestNetWork.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestNetWork.onFailure(error);

                    }
                }
        );

        RequestManager.getRequestQueue().add(mJsonObjectRequest);
    }



    public HttpJSONRequest(Context context,int method, String url, RequestNetWork requestNetWork, String requestTag) {
        initRequestQueue(context,url);
        this.method = method;
        this.url = url;
        this.requestNetWork = requestNetWork;

        createGetJsonObjectRequest(requestTag);
    }

    private void createGetJsonObjectRequest(String requestTag) {
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                method,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        requestNetWork.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestNetWork.onFailure(error);

                    }
                }
        );

        mJsonObjectRequest.setTag(requestTag);
        RequestManager.getRequestQueue().add(mJsonObjectRequest);
    }




    public HttpJSONRequest(Context context,int method, String url, RequestNetWork requestNetWork) {
        initRequestQueue(context,url);
        this.method = method;
        this.url = url;
        this.requestNetWork = requestNetWork;

        createGetJsonObjectRequest();
    }

    private void createGetJsonObjectRequest() {
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                method,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        requestNetWork.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestNetWork.onFailure(error);

                    }
                }
        );

        RequestManager.getRequestQueue().add(mJsonObjectRequest);
    }
    public HttpJSONRequest(int method, String url, Map<String, String> header, JSONObject parameter, RequestNetWork requestNetWork) {
        this.method = method;
        this.url = url;
        this.requestNetWork = requestNetWork;
        this.requestParameter = parameter;
        this.requestHeaderParameter = header;
        createHeaderJsonObjectRequest();
    }

    private void createHeaderJsonObjectRequest() {
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                method,
                url,
                requestParameter,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        requestNetWork.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestNetWork.onFailure(error);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                if (requestHeaderParameter != null) {
                    params.putAll(requestHeaderParameter);
                }
                return params;
            }
        };
        mJsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5* 1000, 1, 1.0f));
        RequestManager.getRequestQueue().add(mJsonObjectRequest);
    }

    private void initRequestQueue(Context context, String url){
       if (RequestManager.getRequestQueueNoThrowable() == null) {
           RequestManager.init(context,url);
       }
   }

    public interface RequestNetWork {
        void onSuccess(JSONObject response);
        void onFailure(VolleyError error);
    }

}
