package com.mridang.updates;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;

/*
 * This class is the activity which contains the preferences
 */
public class WidgetSettings extends PreferenceActivity {

	/*
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 */
	@SuppressWarnings("ConstantConditions")
    @Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getActionBar().setIcon(R.drawable.ic_dashclock);
		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	/*
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {

		super.onPostCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);

		SharedPreferences speSettings = PreferenceManager.getDefaultSharedPreferences(this);
		PreferenceCategory catExclude = (PreferenceCategory) findPreference("exclude");
		Integer intFlags = PackageManager.GET_META_DATA | PackageManager.GET_SHARED_LIBRARY_FILES
				| PackageManager.GET_SIGNATURES;
		for (PackageInfo pkgPackage : getApplicationContext().getPackageManager().getInstalledPackages(intFlags)) {

			if ((pkgPackage.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
				continue;
			}

			CheckBoxPreference cbpApplication = new CheckBoxPreference(this);
			cbpApplication.setKey(pkgPackage.packageName);
			cbpApplication.setTitle(pkgPackage.applicationInfo.loadLabel(getPackageManager()).toString());
			cbpApplication.setSummary(pkgPackage.packageName);
			cbpApplication.setChecked(speSettings.getBoolean(pkgPackage.packageName, false));
			catExclude.addPreference(cbpApplication);

		}

	}

}