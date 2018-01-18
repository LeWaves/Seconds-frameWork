package com.lt.integrate.frame;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * Created by iclick on 2018/1/10.
 */

public class IPermissionQueue {

    public static void SendMutiPermissionQueue(Context activity, List<PermissionItem> permissions,
                              String title,String msg,int color,int animStyleId,int styleId,
                              PermissionCallback  mCallback ){
        HiPermission.create(activity)
                .permissions(permissions)
                .title(title)
                .msg(msg)
                .filterColor(color)
                .animStyle(animStyleId)
                .style(styleId)
                .checkMutiPermission(mCallback);
    }

    public static void SendMutiPermissionQueue(Context activity, List<PermissionItem> permissions,
                                               String msg,int color,int animStyleId,int styleId,
                                               PermissionCallback  mCallback ){
        HiPermission.create(activity)
                .permissions(permissions)
                .msg(msg)
                .filterColor(color)
                .animStyle(animStyleId)
                .style(styleId)
                .checkMutiPermission(mCallback);
    }

    public static void SendMutiPermissionQueue(Context activity, List<PermissionItem> permissions,
                                               int color,int animStyleId,int styleId,
                                               PermissionCallback  mCallback ){
        HiPermission.create(activity)
                .permissions(permissions)
                .filterColor(color)
                .animStyle(animStyleId)
                .style(styleId)
                .checkMutiPermission(mCallback);
    }

    public static void SendMutiPermissionQueue(Context activity, List<PermissionItem> permissions,
                                               int animStyleId,int styleId,
                                               PermissionCallback  mCallback ){
        HiPermission.create(activity)
                .permissions(permissions)
                .animStyle(animStyleId)
                .style(styleId)
                .checkMutiPermission(mCallback);
    }

    public static void SendMutiPermissionQueue(Context activity, List<PermissionItem> permissions,
                                               int styleId,
                                               PermissionCallback  mCallback ){
        HiPermission.create(activity)
                .permissions(permissions)
                .style(styleId)
                .checkMutiPermission(mCallback);
    }

    public static void SendMutiPermissionQueue(Context activity, List<PermissionItem> permissions,
                                               PermissionCallback  mCallback ){
        HiPermission.create(activity)
                .permissions(permissions)
                .checkMutiPermission(mCallback);
    }

    public static void SendSingleMutiPermissionQueue(Context activity, String permissions,PermissionCallback  mCallback ){
        HiPermission.create(activity)
                .checkSinglePermission(permissions,mCallback);
    }
    public static void SendSingleMutiPermissionQueue(Context activity, String permissions,int styleId,PermissionCallback  mCallback ){
        HiPermission.create(activity)
                .style(styleId)
                .checkSinglePermission(permissions,mCallback);
    }
    public static void SendSingleMutiPermissionQueue(Context activity, String permissions,int animStyleId,int styleId,PermissionCallback  mCallback ){
        HiPermission.create(activity)
                .animStyle(animStyleId)
                .style(styleId)
                .checkSinglePermission(permissions,mCallback);
    }
}
