package com.lt.integrate.frame.demo;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lt.integrate.frame.IPermissionQueue;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * Created by iclick on 2018/1/10.
 */

public class PermissionActivity extends AppCompatActivity {

    List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
    int stylePosition =0;
    int styleId =-1,animStyleId =-1;
    String permission = Manifest.permission.READ_PHONE_STATE;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_activity_layout);
        if(permissionItems.size()> 0){
            permissionItems.clear();
        }
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "Camera", R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "Phone", R.drawable.permission_ic_phone));
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "Storage", R.drawable.permission_ic_storage));

        findViewById(R.id.onBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void onAllPermissionView(View v){
        IPermissionQueue.SendMutiPermissionQueue(this,permissionItems,"Permission","Please allow access to Permission",R.color.colorPrimary,
                R.style.PermissionAnimFade, R.style.PermissionDefaultNormalStyle,new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onDeny(String permission, int position) {

                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });


    }

    public void onAllPermissionStyleView(View v){
        stylePosition++;
        if(stylePosition%3 == 0){
            styleId = R.style.PermissionAnimFade;
            animStyleId= R.style.PermissionDefaultNormalStyle;
        }else if(stylePosition%3 == 1){
            styleId = R.style.PermissionAnimModal;
            animStyleId= R.style.PermissionDefaultGreenStyle;
        }else if(stylePosition%3 == 2){
            styleId = R.style.PermissionAnimScale;
            animStyleId= R.style.PermissionDefaultBlueStyle;
        }
        IPermissionQueue.SendMutiPermissionQueue(this,permissionItems,"Permission","Please allow access to Permission",R.color.colorPrimary,
                animStyleId,styleId,new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onDeny(String permission, int position) {

                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });
    }

    public void onSiglePermissionStyleView(View v){
        IPermissionQueue.SendSingleMutiPermissionQueue(this,permission,R.style.PermissionAnimFade, R.style.PermissionDefaultNormalStyle,new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onDeny(String permission, int position) {

                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });
    }
}
