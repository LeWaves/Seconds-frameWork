package com.lt.integrate.frame;

import android.util.Log;

import com.android.volley.Request;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;

/**
 * Created by iclick on 2017/12/7.
 */

public class IOkHttpClient {
    public static final int GET = Request.Method.GET;
    public static final int POST = Request.Method.POST;
    /**
     * 支持HTTPS。按下面的注释进行
     *
     * @return
     */
    public static  OkHttpClient getUnsafeOkHttpsClient() {
        try {
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            X509TrustManager trustManager = (X509TrustManager) tm[0];
            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .hostnameVerifier(new TrustAnyHostnameVerifier())
                    .sslSocketFactory(ssf,trustManager)
                    .build();

            return client;

        } catch (Exception e) {
            Log.i("iax","  初始化  HTTPS client  Exception :"+e.toString());
            throw new RuntimeException(e);
        }
    }

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);

            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:"
                        + Arrays.toString(trustManagers));
            }
            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .sslSocketFactory(sslSocketFactory, trustManager)
                    .build();
            return client;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 不进行主机名确认
     */
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * 信任所有主机 对于任何证书都不做SSL检测
     * 安全验证机制，而Android采用的是X509验证
     */
    private static class MyX509TrustManager implements X509TrustManager {

        public X509Certificate[] getAcceptedIssuers() {
            return  new X509Certificate[0];
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }
    /**
     * 检测是否https
     */
    public static boolean isHttps(String url) {
        return url.startsWith("https");
    }
}
