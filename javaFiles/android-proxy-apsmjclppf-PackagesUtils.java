package com.lechucksoftware.proxy.proxysettings.feedbackutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

import com.lechucksoftware.proxy.proxysettings.App;
import com.lechucksoftware.proxy.proxysettings.R;

public class PackagesUtils
{
    private static final String TAG = PackagesUtils.class.getSimpleName();
    private static ArrayList<PInfo> packages;
	
	public static ArrayList<PInfo> getPackages(Context callerContext)
	{
		if (packages == null)
		{
            /* false = no system packages */
            App.getTraceUtils().startTrace(TAG, "getInstalledApps()", Log.INFO);
			ArrayList<PInfo> apps = getInstalledApps(callerContext, false);
            App.getTraceUtils().stopTrace(TAG, "getInstalledApps()", Log.INFO);
			packages = apps;
		}
			
		return packages;
	}

	private static ArrayList<PInfo> getInstalledApps(Context callerContext, boolean getSysPackages)
	{
		ArrayList<PInfo> res = new ArrayList<PInfo>();

		// Only get applications that can be launched.. 
		
//		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
//		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//		final List pkgAppsList = callerContext.getPackageManager().queryIntentActivities(mainIntent, 0);
        App.getTraceUtils().startTrace(TAG, "getInstalledPackages()", Log.INFO);
		List<PackageInfo> packs = callerContext.getPackageManager().getInstalledPackages(0);
        App.getTraceUtils().stopTrace(TAG, "getInstalledPackages()", Log.INFO);

		for (int i = 0; i < packs.size(); i++)
		{
			PackageInfo p = packs.get(i);
			
			if ((!getSysPackages) && (p.versionName == null))
			{
				continue;
			}
			
			if (isDisabledPackage(callerContext, p.packageName))
			{
				continue;
			}
			
			PInfo newInfo = new PInfo();
			newInfo.appname = p.applicationInfo.loadLabel(callerContext.getPackageManager()).toString();
			newInfo.pname = p.packageName;
			newInfo.versionName = p.versionName;
			newInfo.versionCode = p.versionCode;
//			newInfo.icon = p.applicationInfo.loadIcon(callerContext.getPackageManager());
            newInfo.applicationInfo = p.applicationInfo;
			res.add(newInfo);
		}
		
		Collections.sort(res, new PackagesComparator());
		
		return res;
	}

	private static boolean isDisabledPackage(Context callerContext, String packageName)
	{
		for (String s : callerContext.getResources().getStringArray(R.array.feedback_ignored_packages))
		{
			if (s.equals(packageName))
			{
				return true;
			}
		}
		
		return false;
	}
}
