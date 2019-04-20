package ismisepaul.trainingmode;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        final ListPreference listPref_launchApp =
                (ListPreference) findPreference("pref_selectAppToLaunch");

        setListPrefLaunchApp(listPref_launchApp, getApplicationContext());
        setDialogInfo(listPref_launchApp, getApplicationContext());
        setMenuIcon(listPref_launchApp, getApplicationContext());

        /*When app was changed the icon and app name was not updated. This listener detects when
         * a change has been made to the selected app that launches when the user begins their
          * workout. It then starts a new settings activity so that the icon and app name is updated
          * and kills the previous settings activity by calling finish() at the end*/
        listPref_launchApp.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference pref, Object newThing) {
                Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(intent);
                finish();
                return true;

            }
        });


        listPref_launchApp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                setListPrefLaunchApp(listPref_launchApp, getApplicationContext());
                setMenuIcon(listPref_launchApp, getApplicationContext());
                setDialogInfo(listPref_launchApp, getApplicationContext());
                return true;

            }
        });
    }


    /*Set the Icon and Name of app on the popup dialog */
    protected static void setDialogInfo(ListPreference lp, Context context){

        final PackageManager pm = context.getPackageManager();
        //Set the Dialog title to the currently selected app icon and name
        try {
            lp.setDialogIcon(pm.getApplicationIcon(lp.getValue()));
            lp.setDialogTitle("\"" + lp.getEntry() + "\"" + " launches when beginning workout");

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*Set the Icon and Name of app beside the setting option */
    protected static void setMenuIcon(ListPreference lp, Context context){
        final PackageManager pm = context.getPackageManager();
        final String SETTING_LAUNCH_APP_TITLE = "Change App that launches";

        try {
            if(lp.getEntry().toString() != null) {
                lp.setTitle(SETTING_LAUNCH_APP_TITLE);
                lp.setIcon(pm.getApplicationIcon(lp.getValue()));
             }

        } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
        } catch (NullPointerException ee){
            ee.printStackTrace();
        }

    }

    protected static void setListPrefLaunchApp(ListPreference listPreference, Context context) {
        final PackageManager pm = context.getPackageManager();
        final int flags = PackageManager.GET_META_DATA |
                PackageManager.GET_SHARED_LIBRARY_FILES;

        List<ApplicationInfo> packages = pm.getInstalledApplications(flags);
        List<String> app_name = new ArrayList<String>();
        List<String> package_names = new ArrayList<String>();
        //Key Values for App name and package name e.g. key:MapMyFitness,value:com.mapmyfitness
        Map<String, String> mapApps = new HashMap<String, String>();


        for (ApplicationInfo appInfo : packages) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                // System application
            } else {
                // User installed applications
                String label = appInfo.loadLabel(context.getPackageManager()).toString();
                String packageName = appInfo.packageName;

                if(label != null)
                    mapApps.put(label, packageName);
            }

        }
        //Sort the keys i.e. app_name for user convenience
        SortedSet<String> keys = new TreeSet<String>(mapApps.keySet());
        for (String key: keys){
            String value = mapApps.get(key);
            app_name.add(key);
            package_names.add(value);
        }

        //convert to charSequence so that the data can be added to listPreference
        final CharSequence[] cs_app_name =
                app_name.toArray(new CharSequence[app_name.size()]);
        final CharSequence[] cs_package_names =
                package_names.toArray(new CharSequence[app_name.size()]);

        listPreference.setEntries(cs_app_name);
        listPreference.setEntryValues(cs_package_names);


    }


}
