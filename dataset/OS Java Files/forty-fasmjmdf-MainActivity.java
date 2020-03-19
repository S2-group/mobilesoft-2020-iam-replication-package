package me.drori.forty;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String FIRST_RUN_PREFERENCE = "first_run";
    public final static String CALENDAR_PREFERENCE_LIST = "list_calendar";
    private final static int FORTY_PERMISSIONS_REQUEST_CODE = 42;
    private final static String PREFERENCE_FRAGMENT_TAG = "preference_fragment_tag";
    private final static String PRIVACY_POLICY_ADDRESS = "https://github.com/yuvaldrori/Forty/blob/master/PRIVACY.md";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPref.getBoolean(FIRST_RUN_PREFERENCE, true) && !hasPermission()) {
            sharedPref.edit().putBoolean(FIRST_RUN_PREFERENCE, false).apply();
            askPermissions(null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setScreen(hasPermission());
        TextView textView = (TextView) findViewById(R.id.supported_apps);
        if (!supportedAppInstalled()) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        removeFragment();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case FORTY_PERMISSIONS_REQUEST_CODE:
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        askPermissions(null);
                    }
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (notificationAccess()) {
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        }
    }

    private void removeFragment() {
        Fragment preferenceFragment = getSupportFragmentManager().findFragmentByTag(PREFERENCE_FRAGMENT_TAG);
        if (preferenceFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(preferenceFragment).commit();
        }
    }

    private boolean supportedAppInstalled() {
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> applications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo app : applications) {
            if (FortyNotificationListenerService.SUPPORTED_APPS.contains(app.packageName)) {
                return true;
            }
        }
        return false;
    }

    private boolean notificationAccess() {
        ContentResolver contentResolver = this.getContentResolver();
        String enabledNotificationListeners = Settings.Secure.getString(contentResolver, "enabled_notification_listeners");
        String packageName = this.getPackageName();

        // check to see if the enabledNotificationListeners String contains our package name
        return (enabledNotificationListeners == null || !enabledNotificationListeners.contains(packageName));
    }

    public void askPermissions(View view) {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CALENDAR,
                        Manifest.permission.WRITE_CALENDAR},
                FORTY_PERMISSIONS_REQUEST_CODE);
    }

    public void openPrivacyPolicyLink(View view) {
        Uri webpage = Uri.parse(PRIVACY_POLICY_ADDRESS);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private boolean hasPermission() {
        int permissionCheckCalendarWrite = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR);
        int permissionCheckCalendarRead = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALENDAR);
        return !(permissionCheckCalendarRead != PackageManager.PERMISSION_GRANTED ||
                permissionCheckCalendarWrite != PackageManager.PERMISSION_GRANTED ||
                notificationAccess());
    }

    private void setScreen(boolean granted) {
        Button permissionsButton = (Button)findViewById(R.id.permissions_button);
        if (granted) {
            permissionsButton.setVisibility(View.GONE);
            getFragmentManager().beginTransaction()
                    .add(R.id.main, new SettingsFragment(), PREFERENCE_FRAGMENT_TAG)
                    .commit();
        } else {
            permissionsButton.setVisibility(View.VISIBLE);
            permissionsButton.setText(R.string.permissions_button_text);
            removeFragment();
        }
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(null);
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);

            final ListPreference listPreference = (ListPreference) findPreference(CALENDAR_PREFERENCE_LIST);
            Calendar calendar = new Calendar(getActivity());
            List<Pair<String, String>> calendars = calendar.getCalendars();
            List<String> calendar_names = new ArrayList<>();
            List<String> calendar_ids = new ArrayList<>();
            for (Pair<String, String> tup : calendars) {
                calendar_names.add(tup.first);
                calendar_ids.add(tup.second);
            }
            final CharSequence[] entries = calendar_names.toArray(new CharSequence[calendar_names.size()]);
            final CharSequence[] values = calendar_ids.toArray(new CharSequence[calendar_ids.size()]);
            listPreference.setEntries(entries);
            listPreference.setEntryValues(values);
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String calendar_id = sharedPref.getString(CALENDAR_PREFERENCE_LIST, "");
            if (calendar_ids.contains(calendar_id)) {
                listPreference.setDefaultValue(calendar_id);
            } else {
                listPreference.setDefaultValue(calendar_ids.get(0));
                listPreference.setSummary(R.string.calendar_settings_summary_none);
            }
            listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    listPreference.setSummary(R.string.calendar_settings_summary);
                    return true;
                }
            });
        }
    }
}
