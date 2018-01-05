package com.lt.integrate.frame.http;


import com.android.volley.toolbox.HurlStack;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import okhttp3.OkUrlFactory;

/**
 * Created by Administrator on 2017/5/10.
 */
public class OkHttpStack extends HurlStack {
    private final OkUrlFactory okUrlFactory;


    public OkHttpStack(OkUrlFactory okUrlFactory) {
        if (okUrlFactory == null) {
            throw new NullPointerException("Client must not be null.");
        }
        this.okUrlFactory = okUrlFactory;
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        return okUrlFactory.open(url);
    }


}

