package com.cs442project.appmonitor;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.util.Log;
import android.widget.Toast;

public class TopActivityReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		// TODO Auto-generated method stub
		String msg_for_me = intent.getStringExtra("some_msg");
        Log.i(MainActivity.class.getName(), msg_for_me);
        ActivityManager am = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
		String packageName = am.getRunningTasks(1).get(0).topActivity.getPackageName();
		 List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(0);
		    for (int i = 0; i < packageInfoList.size(); i++)
		    {
		        PackageInfo packageInfo = packageInfoList.get(i);
		        if(packageInfo.packageName.equals(packageName))
		        {
		            String appName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
		            System.out.println("You are using "+appName+" app.");
		            Toast.makeText(context, "You are using "+appName+" app.", Toast.LENGTH_SHORT).show();
		        }
		    }
	}

	
}
