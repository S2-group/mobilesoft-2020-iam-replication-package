package org.groebl.sms;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import org.groebl.sms.ui.settings.SettingsFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BluetoothApps extends PreferenceFragment {

    private SharedPreferences mSharedPref;
    private PreferenceCategory mWhiteList;
    private Set<String> mWhiteListEntries;
    private ColorFilter mGrayscaleFilter;


    class AppPreference extends CheckBoxPreference {
        private String mPkgName;
        public String getPkgName() {
            return mPkgName;
        }
        public void setPkgName(String mPkgName) { this.mPkgName = mPkgName; }
        public AppPreference(Context context) {
            super(context);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0f);
        float[] matrix = colorMatrix.getArray();
        matrix[18] = 0.5f;
        mGrayscaleFilter = new ColorMatrixColorFilter(colorMatrix);

        new LoadApplications(getActivity().getApplicationContext()).execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }


    private class LoadApplications extends AsyncTask<Void, Integer, Boolean> {

        private ProgressDialog pDialog = new ProgressDialog(getActivity());
        List<AppPreference> prefs = new ArrayList<>();

        public LoadApplications(Context context){
            Context mContext = context;
        }

        @Override
        protected void onPreExecute() {
            pDialog.setMessage(getString(R.string.pref_bluetooth_apps_loading));
            pDialog.setOnCancelListener(dialog -> this.cancel(true));
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Set<String> isSelected = new HashSet<>();
            mSharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            PackageManager mPackageManager = getActivity().getPackageManager();

            Set<String> entries = mSharedPref.getStringSet(SettingsFragment.BLUETOOTH_SELECTAPPS, new HashSet<>());
            if (entries.isEmpty()) {
                mWhiteListEntries = new HashSet<>();
            } else {
                mWhiteListEntries = new HashSet<>(entries);
            }

            List<PackageInfo> packs = mPackageManager.getInstalledPackages(0);

            for (int i = 0; i < packs.size(); i++) {
                if(isCancelled()){
                    break;
                }

                AppPreference pref = new AppPreference(getActivity());
                android.content.pm.PackageInfo p = packs.get(i);

                pref.setTitle(p.applicationInfo.loadLabel(mPackageManager).toString());
                Drawable icon = p.applicationInfo.loadIcon(mPackageManager);
                pref.setPkgName(p.packageName);
                if (mWhiteListEntries.contains(p.packageName)) {
                    pref.setDefaultValue(true);
                    isSelected.add(p.packageName);
                } else {
                    pref.setDefaultValue(false);
                    icon.setColorFilter(mGrayscaleFilter);
                }
                pref.setIcon(icon);

                if (!BuildConfig.APPLICATION_ID.equalsIgnoreCase(p.packageName)) prefs.add(pref);
            }

            if (!isCancelled()){
                Collections.sort(prefs, (a, b) -> a.getTitle().toString().toLowerCase().compareTo(b.getTitle().toString().toLowerCase()));

                SharedPreferences.Editor editor = mSharedPref.edit();
                editor.putStringSet(SettingsFragment.BLUETOOTH_SELECTAPPS, isSelected);
                editor.apply();
            }

            return null;
        }

        @Override
        protected void onCancelled() {
            pDialog.hide();
            getFragmentManager().popBackStack();
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Boolean finished){
            addPreferencesFromResource(R.xml.settings_bluetooth_apps);
            mWhiteList = (PreferenceCategory) findPreference(getString(R.string.cat_applist));
            mWhiteList.setTitle(R.string.pref_bluetooth_apps_title);

            for (AppPreference pref : prefs) {
                mWhiteList.addPreference(pref);
            }


            if (pDialog.isShowing()){
                pDialog.dismiss();
            }
        }

    }

    private void editEntry(AppPreference pref) {

        String pkg = pref.getPkgName();
        boolean disabled = !pref.isChecked();
        Drawable icon = pref.getIcon();
        if (pref.isChecked()) {
            icon.setColorFilter(null);
        } else {
            icon.setColorFilter(mGrayscaleFilter);
        }
        ArrayList<String> newlist = new ArrayList<>(mWhiteListEntries);
        boolean isWhiteListed = newlist.contains(pkg);
        if (disabled && !isWhiteListed) {
            return;
        } else if (disabled) { //
            newlist.remove(pkg);
        } else if (!disabled && isWhiteListed) {
            return;
        } else if (!disabled) {
            newlist.add(pkg);
        }


        mWhiteListEntries = new HashSet<>(newlist);
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putStringSet(SettingsFragment.BLUETOOTH_SELECTAPPS, mWhiteListEntries);
        editor.apply();
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, final Preference preference) {
        if (!isAdded()) { return false; }

        String key = preference.getKey() != null ? preference.getKey() : "";

        switch(key) {
            case SettingsFragment.BLUETOOTH_SELECT_ALL:
                for (int i = 0; i < mWhiteList.getPreferenceCount(); i++) {
                    AppPreference pref = (AppPreference) mWhiteList.getPreference(i);
                    pref.setChecked(true);
                    editEntry(pref);
                }
                break;
            case SettingsFragment.BLUETOOTH_SELECT_NONE:
                for (int i = 0; i < mWhiteList.getPreferenceCount(); i++) {
                    AppPreference pref = (AppPreference) mWhiteList.getPreference(i);
                    pref.setChecked(false);
                    editEntry(pref);
                }
                break;
            default:
                AppPreference pref = (AppPreference) preference;
                editEntry(pref);
        }

        return true;
    }
}