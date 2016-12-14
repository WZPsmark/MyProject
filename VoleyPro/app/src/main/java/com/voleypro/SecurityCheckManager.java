package com.voleypro;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by smark on 2016/12/14.
 */

public class SecurityCheckManager {

    private Context mContext;


    private SecurityCheckManager(Context context){
        this.mContext=context;

    }

    private static SecurityCheckManager gSecurityCheckManager;


    static public SecurityCheckManager createInstence(Context context){
        if(gSecurityCheckManager==null){
            gSecurityCheckManager = new SecurityCheckManager(context);
        }
        return gSecurityCheckManager;
    }

    static public SecurityCheckManager getInstanc(){
        return gSecurityCheckManager;

    }

    public  boolean checkPermission(String permissionName){
        PackageManager pkm = mContext.getPackageManager();
        return pkm.checkPermission(permissionName,mContext.getPackageName())==PackageManager.PERMISSION_GRANTED;
    }
}
