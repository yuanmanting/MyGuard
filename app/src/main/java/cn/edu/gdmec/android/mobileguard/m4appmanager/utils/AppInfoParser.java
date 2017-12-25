package cn.edu.gdmec.android.mobileguard.m4appmanager.utils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.gdmec.android.mobileguard.m4appmanager.entity.AppInfo;

/**
 * Created by HP on 2017/11/6.
 */

public class AppInfoParser {
    public static List<AppInfo> getAppInfos(Context context){
        PackageManager pm=context.getPackageManager();
            List<PackageInfo> packInfos=pm.getInstalledPackages(PackageManager.GET_SIGNATURES+PackageManager.GET_PERMISSIONS+PackageManager.GET_ACTIVITIES);
        List<AppInfo> appinfos=new ArrayList<AppInfo>();
        for (PackageInfo packInfo:packInfos){
            AppInfo appinfo=new AppInfo();
            String packname=packInfo.packageName;
            appinfo.packageName=packname;
            Drawable icon=packInfo.applicationInfo.loadIcon(pm);
            appinfo.icon=icon;
            String appname=packInfo.applicationInfo.loadLabel(pm).toString();
            appinfo.appName=appname;
            String apkPath=packInfo.applicationInfo.sourceDir;
            appinfo.apkPath=apkPath;
            File file=new File(apkPath);
            long appSize=file.length();
            appinfo.appSize=appSize;

            int flags=packInfo.applicationInfo.flags;
            if ((ApplicationInfo.FLAG_EXTERNAL_STORAGE & flags) != 0){
                appinfo.isInRoom=false;
            }else{
                appinfo.isInRoom=true;
            }
            if ((ApplicationInfo.FLAG_SYSTEM & flags )!=0){
                appinfo.isUserApp=false;
            }else{
                appinfo.isUserApp=true;
            }
            String version=packInfo.versionName;
            /*appinfo.version=version;
            appinfo.InstallTime=new Date(packInfo.firstInstallTime).toLocaleString();
            try{
                PackageInfo packinfo=pm.getPackageInfo(packname,PackageManager.GET_SIGNATURES);
                byte[] ss=packinfo.signatures[0].toByteArray();
                CertificateFactory of=CertificateFactory.getInstance("X509");
                X509Certificate cert=(X509Certificate) of.generateCertificate(new
                        ByteArrayInputStream(ss));
                if (cert != null){
                    appinfo.signature=cert.getIssuerDN().toString();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            PackageInfo packinfo1=null;
            try {
                packinfo1=pm.getPackageInfo(packname,PackageManager.GET_PERMISSIONS);
                if (packinfo1.requestedPermissions !=null){
                    for (String pio:packinfo1.requestedPermissions){
                        appinfo.permissions=appinfo.permissions+pio+"\n";
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            // 添加activity活动
            PackageInfo packinfo3;
            try{
                packinfo3=pm.getPackageInfo(packname,PackageManager.GET_ACTIVITIES);
                ActivityInfo[] activityInfos=packinfo3.activities;
                if(activityInfos!=null){
                    for(ActivityInfo info:activityInfos){
                        appinfo.activityName=appinfo.activityName+info.name+"\n";
                    }
                }
            }catch (PackageManager.NameNotFoundException e){
                e.printStackTrace();
            }*/
           appinfo.InstallTime=packInfo.firstInstallTime;
            StringBuilder sb=new StringBuilder();
            if(packInfo.requestedPermissions!=null){
                for (String per:packInfo.requestedPermissions){
                    sb.append(per+"\n");
                }
                appinfo.permissions=sb.toString();
            }
            sb.delete(0,sb.length());
            if(packInfo.activities!=null){
                for (ActivityInfo activityInfo:packInfo.activities){
                    sb.append(activityInfo.name+"\n");
                }
                appinfo.activityName=sb.toString();
            }
            final Signature[] arrsignatures=packInfo.signatures;
            for (final Signature sig:arrsignatures){
                final byte[] rawCert=sig.toByteArray();
                InputStream certStream=new ByteArrayInputStream(rawCert);
                try{
                    CertificateFactory certFactory=CertificateFactory.getInstance("X509");
                    X509Certificate x509Cert=(X509Certificate)certFactory.generateCertificate(certStream);
                    appinfo.signature="Certificate  issuer:"+x509Cert.getIssuerDN()+"\n";

                }catch (CertificateException e){

                }
            }
           appinfos.add(appinfo);
            appinfo=null;
        }
        return appinfos;
    }

}
