package io.pure.sixgrid.preference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.ListPreference;
import android.widget.Toast;

import io.pure.sixgrid.MainActivity;
import io.pure.sixgrid.R;

import java.util.List;

public class CustomizeGrid extends PreferenceActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.customize);
		
		PackageManager pm = getPackageManager();
		List<ApplicationInfo> appList = pm.getInstalledApplications(PackageManager.GET_META_DATA);
		int count = appList.size();
		String[] appLabels = new String[count];
		String[] pkgNames = new String[count];
		for (int i = 0; i < count ; i++) {
			ApplicationInfo app = appList.get(i);
			appLabels[i] = pm.getApplicationLabel(app).toString();
			pkgNames[i] = app.packageName;
		}
		
		ListPreference one = (ListPreference)findPreference("pkgnameone");
		one.setEntries(appLabels);
		one.setEntryValues(pkgNames);
		
		ListPreference two = (ListPreference)findPreference("pkgnametwo");
		two.setEntries(appLabels);
		two.setEntryValues(pkgNames);
		
		ListPreference three = (ListPreference)findPreference("pkgnamethree");
		three.setEntries(appLabels);
		three.setEntryValues(pkgNames);
		
		ListPreference four = (ListPreference)findPreference("pkgnamefour");
		four.setEntries(appLabels);
		four.setEntryValues(pkgNames);
		
		ListPreference five = (ListPreference)findPreference("pkgnamefive");
		five.setEntries(appLabels);
		five.setEntryValues(pkgNames);
		
		ListPreference six = (ListPreference)findPreference("pkgnamesix");
		six.setEntries(appLabels);
		six.setEntryValues(pkgNames);
	}

	@Override
	public void onBackPressed()
	{
		Intent main = new Intent(CustomizeGrid.this, MainActivity.class);
		main.putExtra("var", true);
		startActivity(main);
		overridePendingTransition(R.anim.slide_up_bottom, R.anim.slide_down_bottom);
		finish();
		Toast.makeText(getApplicationContext(), "Make sure that all values are filled and none are empty or else you may encounter graphical glitches with grids.", Toast.LENGTH_LONG).show();
	}
}
