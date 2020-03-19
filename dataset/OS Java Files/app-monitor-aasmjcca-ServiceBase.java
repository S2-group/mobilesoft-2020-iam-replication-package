package com.cs442project.appmonitor;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.util.Log;

public class ServiceBase extends Service
{

	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		 ActivityManager am = (ActivityManager) this.getSystemService(Activity.ACTIVITY_SERVICE);
			String packageName = am.getRunningTasks(1).get(0).topActivity.getPackageName();
			Log.v(ServiceBase.class.getName(), "Top Activity Name :: "+packageName);
			
			 List<PackageInfo> packageInfoList = this.getPackageManager().getInstalledPackages(0);
			    for (int i = 0; i < packageInfoList.size(); i++)
			    {
			        PackageInfo packageInfo = packageInfoList.get(i);
			        if(packageInfo.packageName.equals(packageName))
			        {
			            String appName = packageInfo.applicationInfo.loadLabel(this.getPackageManager()).toString();
			            System.out.println("You are using "+appName+" app.");
			        }
			    }
			
			if (packageName.contains("com")) {
				Log.v(ServiceBase.class.getName(), "Matched !!!");
			}
			else{
				Log.v(ServiceBase.class.getName(), "Not Matched !!!");
			}
		return super.onStartCommand(intent, flags, startId);
	}

}
