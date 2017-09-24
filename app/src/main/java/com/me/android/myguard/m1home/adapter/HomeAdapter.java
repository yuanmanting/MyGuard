package com.me.android.myguard.m1home.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.me.android.myguard.R;

/**
 * Created by HP on 2017/9/24.
 */

public class HomeAdapter extends BaseAdapter{
    int[] imageId={R.drawable.safe,R.drawable.callmsgsafe,R.drawable.app,R.drawable.trojan,
    R.drawable.sysoptimize,R.drawable.taskmanager,R.drawable.netmanager,R.drawable.atools,
    R.drawable.settings};
    String[] names={"手机防盗","通讯卫士","软件管家","手机杀毒",
            "缓存处理","流量统计","高级工具","设置中心"};
    private Context context;
    public  HomeAdapter(Context context){
        this.context=context;
    }
}
