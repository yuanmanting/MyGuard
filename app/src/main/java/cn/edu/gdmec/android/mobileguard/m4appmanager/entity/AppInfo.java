package cn.edu.gdmec.android.mobileguard.m4appmanager.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by HP on 2017/11/6.
 */

public class AppInfo {
//    应用程序包名
    public String packageName;
//    应用程序图标
    public Drawable icon;
//    应用程序名称
    public String appName;
//    应用程序路径
    public String apkPath;
//    应用程序大小
    public long appSize;
//    是否是手机储存
    public boolean isInRoom;
//    是否是用户应用
    public boolean isUserApp;
//    是否选中，默认都为false
    public boolean isSelected=false;
    public String version;
    public long InstallTime;
    public String signature;
    public String permissions;
    //添加activity
    public String activityName;
    public String getAppLocation(boolean isInRoom){
        if (isInRoom){
            return "手机内存";
        }else{
            return "外部存储";
        }
    }
    /**应用程序是否加锁*/
    public boolean isLock;

}
