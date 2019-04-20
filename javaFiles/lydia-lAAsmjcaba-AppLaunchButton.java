package com.autosenseapp.buttons.appButtons;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ArrayAdapter;
import com.autosenseapp.buttons.BaseButton;
import com.autosenseapp.databases.Button;
import com.autosenseapp.includes.AppInfo;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by eric on 2014-06-22.
 */
public class AppLaunchButton extends BaseButton {

	private static final String TAG = AppLaunchButton.class.getSimpleName();

	private ArrayAdapter<AppInfo> adapter;
	private Context context;

	public AppLaunchButton(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public void onClick(View view, Button button) {
		try {
			Intent launch = Intent.parseUri(button.getExtraData(), Intent.URI_INTENT_SCHEME);
			context.startActivity(launch);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasExtraData() {
		return true;
	}

	@Override
	public ArrayAdapter<AppInfo> getAdapterData() {
		List<AppInfo> apps = getInstalledPackages();
		adapter = new ArrayAdapter<AppInfo>(context, android.R.layout.simple_spinner_dropdown_item, apps);
		return adapter;
	}

	public Drawable getIcon(String data) {
		try {
			PackageManager packageManager = context.getPackageManager();
			Intent intent = Intent.parseUri(data, Intent.URI_INTENT_SCHEME);
			intent.setType("image/png");
			List<ResolveInfo> matches = packageManager.queryIntentActivities(intent, 0);
			return matches.get(0).loadIcon(packageManager);
		} catch (URISyntaxException e) {
		} catch (IndexOutOfBoundsException e) {
			// No icons available
		}
		return null;
	}

	@Override
	public String getExtraData(int position) {
		AppInfo appInfo = adapter.getItem(position);
		return appInfo.getLaunchIntentUri();
	}

	// returns an array of appinfos of the installed packages we can launch
	private List<AppInfo> getInstalledPackages() {
		// rare time when the package manager dies the app will crash.  this should fix it, at least stop the crash.
		try {
			PackageManager packageManager = context.getPackageManager();
			// create our new arrays
			List<AppInfo> appInfos = new ArrayList<AppInfo>();
			// get the list of all installed apps
			List<PackageInfo> installedApps = packageManager.getInstalledPackages(0);
			// get a list of activities with the "Action Main" intent
			List<ResolveInfo> activityList = packageManager.queryIntentActivities(new Intent(Intent.ACTION_MAIN, null), 0);

			// loop over the installed apps, and get the package info
			for (PackageInfo info : installedApps) {
				// create a new appinfo object
				AppInfo appInfo = new AppInfo();
				// we set package name here because we test on it later
				appInfo.setPackageName(info.packageName);

				// loop over all the activites with the "main intent"
				for (ResolveInfo resolveInfo : activityList) {
					// if the current packages matches one of the activities
					if (info.packageName.equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
						// set the attributes needed
						appInfo.setClassName(resolveInfo.activityInfo.name);
						appInfo.setAppName(info.applicationInfo.loadLabel(packageManager).toString());
						appInfo.setVersionName(info.versionName);
						appInfo.setVersionCode(info.versionCode);
						appInfo.setIcon(info.applicationInfo.loadIcon(packageManager));

						// create a new intent to stuff into the appinfo object
						Intent launchIntent = new Intent();
						ComponentName component = new ComponentName(appInfo.getPackageName(), appInfo.getClassName());
						launchIntent.setComponent(component);
						launchIntent.setAction(Intent.ACTION_MAIN);
						appInfo.setLaunchIntent(launchIntent);

						// add the object to the array that will be returned
						appInfos.add(appInfo);
						break;
					}
				}
			}

			// sort the list by appname ignoring case
			Collections.sort(appInfos, new Comparator<AppInfo>() {
				@Override
				public int compare(AppInfo appInfo, AppInfo appInfo2) {
					return appInfo.getAppName().compareToIgnoreCase(appInfo2.getAppName());
				}
			});

			// return the list
			return appInfos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
