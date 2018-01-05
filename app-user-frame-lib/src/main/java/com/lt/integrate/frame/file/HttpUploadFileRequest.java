package com.lt.integrate.frame.file;


import com.lt.integrate.frame.IOkHttpClient;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by iclick on 2017/9/22.
 */

public class HttpUploadFileRequest {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");
    private  static OkHttpClient client ;

    /**
     * 上传文件
     */
    public final static void uploadImgAndParameter(Map<String, Object> map,
                                                    String url, final RequestUploadListener listener) {
        if(IOkHttpClient.isHttps(url)){
            client = IOkHttpClient.getUnsafeOkHttpsClient();
        }else{
            client =IOkHttpClient.getUnsafeOkHttpClient();
        }

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    if (entry.getValue() instanceof File) {
                        File f = (File) entry.getValue();
                        builder.addFormDataPart(entry.getKey(), f.getName(),
                                RequestBody.create(MEDIA_TYPE_PNG, f));
                    } else {
                        builder.addFormDataPart(entry.getKey(), entry
                                .getValue().toString());
                    }
                }

            }
        }
        // 创建RequestBody
        RequestBody body = builder.build();
        final okhttp3.Request request = new okhttp3.Request.Builder().url(url)// 地址
                .post(body)// 添加请求体
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onResponse(Call call, final Response response)
                    throws IOException {
                final String data = response.body().string();
                listener.onSuccess(data);
                call.cancel();// 上传成功取消请求释放内存
            }

            @Override
            public void onFailure(Call call, final IOException e) {
                listener.onFailure(e.toString());
                call.cancel();// 上传失败取消请求释放内存
            }

        });

    }
    public interface RequestUploadListener {
        void onSuccess(String response);
        void onFailure(String error);
    }
}
