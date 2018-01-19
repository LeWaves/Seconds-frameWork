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


    /**
     * @param  activity Context
     *@param  permissions list 多个权限
     *@param  title   弹窗title
     *@param  msg    弹窗描述
     *@param  color   颜色
     *@param  animStyleId  动画风格
     *@param  styleId  风格
     *@param  mCallback 申请回调接口
     * */

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

}
