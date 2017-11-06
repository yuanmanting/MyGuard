package cn.edu.gdmec.android.mobileguard.m4appmanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.edu.gdmec.android.mobileguard.m4appmanager.entity.AppInfo;

/**
 * Created by HP on 2017/11/6.
 */

public class AppManagerAdapter extends BaseAdapter{
    private List<AppInfo> UserAppInfos;
    private List<AppInfo> SystemAppInfos;
    private Context context;
    @Override
    public int getCount() {
        return UserAppInfos.size()+SystemAppInfos.size()+2;
    }

    @Override
    public Object getItem(int i) {
        if (i==0){
            return null;
        }else if(i==(UserAppInfos.size()+1)){
            return null;
        }
        AppInfo appInfo;
        if (i<(UserAppInfos.size()+1)){
            appInfo=UserAppInfos.get(i-1);
        }else{
            int location=i-UserAppInfos.size()-2;
            appInfo=SystemAppInfos.get(location);
        }
        return appInfo;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return null;
    }
}
