package com.lt.integrate.frame.demo;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lt.integrate.frame.file.HttpDownloadUtils;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * Created by iclick on 2017/12/29.
 */

public class LoadingActivity extends AppCompatActivity {
    LinearLayout downloadingLayout;
    TextView downloadSize,downloadProgress,downloadStatus;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout_activity);
        initView();
    }

    private void initView() {
        downloadSize = (TextView) findViewById(R.id.downloadSize);
        downloadProgress = (TextView) findViewById(R.id.downloadProgress);
        downloadStatus = (TextView) findViewById(R.id.downloadStatus);
        downloadingLayout = (LinearLayout) findViewById(R.id.downloadingLayout);
        findViewById(R.id.onBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void DownLoadingClick(View v){
        initPremissionList();
    }

    /**
     * 判断哪些权限未授予
     */

    private void initPremissionList(){
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "Storage", R.drawable.permission_ic_storage));
        HiPermission.create(this)
                .permissions(permissionItems)
                .msg("Please allow access to Permission")
                .animStyle(R.style.PermissionAnimFade)//set default animation
                .style(R.style.PermissionDefaultNormalStyle)//set default style
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {
                        String url = "https://shouji.ssl.qihucdn.com/171129/b4b935b61c56e3886234d700cf167667/com.qihoo.appstore_300070142.apk";
                        String filePath = getApplicationContext().getPackageName();
                        downloadingLayout.setVisibility(View.VISIBLE);
                        downloadFilesMultipart(url,filePath);
                    }

                    @Override
                    public void onDeny(String permission, int position) {

                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });


    }



    public  void downloadFilesMultipart(String url, final String pathName) {
        HttpDownloadUtils.download(url,pathName, new HttpDownloadUtils.OnDownloadListener() {
            @Override
            public void onDownloadSuccess() {
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);//发送消息
            }

            @Override
            public void onDownloading(int progress) {
                Message msg = new Message();
                msg.what = 2;
                msg.arg1=progress;
                handler.sendMessage(msg);//发送消息
            }

            @Override
            public void onDownloadFailed() {
                Message msg = new Message();
                msg.what = -1;
                handler.sendMessage(msg);//发送消息
            }
        });

    }
    /**
     * 当Handler被创建会关联到创建它的当前线程的消息队列，该类用于往消息队列发送消息
     * 消息队列中的消息由当前线程内部进行处理
     */
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    downloadStatus.setText("下载状态："+"下载成功");
                    downloadSize.setText("文件大小："+HttpDownloadUtils.getDownloadSize());
                    break;
                case 2 :
                    downloadProgress.setText("文件下载进度 ："+msg.arg1+"%");

                    break;
                case -1:
                    downloadStatus.setText("下载状态："+"下载失败");
                    break;
            }
        }
    };


}
