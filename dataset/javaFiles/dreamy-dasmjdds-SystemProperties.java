package de.dreamy.system;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import de.dreamy.DreamyDaydream;
import de.dreamy.util.Strings;

/**
 * Provides access to device and system properties
 */
public class SystemProperties {

    private static final String SCREENSAVER_ENABLED = "screensaver_enabled";
    private static final String SCREENSAVER_COMPONENTS = "screensaver_components";
    private static float dpScale = 0f;
    private final Context context;

    @Inject
    public SystemProperties(Context context) {
        this.context = context;
    }

    /**
     * Gets the name of the current carrier
     *
     * @return A string with the carrier's name
     */
    public String getCarrierName() {
        final List<String> carrierNames = new ArrayList<>();
        final SubscriptionManager subscriptionManager = SubscriptionManager.from(context);
        for (SubscriptionInfo subscriptionInfo : subscriptionManager.getActiveSubscriptionInfoList()) {
            carrierNames.add(subscriptionInfo.getCarrierName().toString());
        }
        Log.d("Dreamy", "carrier names: " + carrierNames);
        return Strings.join(carrierNames, " | ");
    }


    /**
     * Gets the name of the currently connected wifi
     *
     * @return the name of the currently connected wifi. NULL if no wifi connected
     */
    public String getCurrentWifi() {
        final ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Network networks[] = connManager.getAllNetworks();
        for (final Network singleNetwork : networks) {
            final NetworkInfo networkInfo = connManager.getNetworkInfo(singleNetwork);
            if (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) {
                if (!networkInfo.isConnected()) {
                    continue;
                }
                if (networkInfo.isConnected()) {
                    final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    final WifiInfo connectionInfo = wifiManager.getConnectionInfo();

                    if (connectionInfo != null) {
                        String ssid = connectionInfo.getSSID();
                        String quotes = String.valueOf('"');
                        if (ssid.startsWith(quotes)) {
                            ssid = ssid.substring(1, ssid.length());
                        }
                        if (ssid.endsWith(quotes)) {
                            ssid = ssid.substring(0, ssid.length() - 1);
                        }
                        return ssid;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks if the app has a certain permission
     *
     * @param permission permission string, use constants defined in {@link Manifest.permission}
     * @return {@code ture} if this app has the requested permission, {@code false} otherwise
     */
    public boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Gets whether Daydream is enabled in the Android settings menu.
     *
     * @return {@code true} if Daydream is enabled
     */
    public boolean isDaydreamEnabled() {
        return 1 == Settings.Secure.getInt(context.getContentResolver(), SCREENSAVER_ENABLED, -1);
    }

    /**
     * Checks if Dreamy is selected as the Daydream application
     *
     * @return {@code true} if Dreamy is selected as Daydream
     */
    public boolean isDreamySelected() {
        final String names = Settings.Secure.getString(context.getContentResolver(), SCREENSAVER_COMPONENTS);
        return names != null && DreamyDaydream.class.getName().equals(componentsFromString(names)[0].getClassName());
    }

    public List<AppData> getInstalledApps() {
        final PackageManager pm = context.getPackageManager();
        final List<AppData> result = new ArrayList<>();
        for (ApplicationInfo applicationInfo : pm.getInstalledApplications(0)) {
            final String appName = applicationInfo.loadLabel(pm).toString();
            final Drawable icon = applicationInfo.loadIcon(pm);
            result.add(new AppData(appName, applicationInfo.packageName, icon));
        }
        Collections.sort(result, new Comparator<AppData>() {
            @Override
            public int compare(AppData l, AppData r) {
                return l.appName.compareToIgnoreCase(r.appName);
            }
        });
        return result;
    }

    public float getDPScale() {
        if (dpScale == 0f) {
            dpScale = context.getResources().getDisplayMetrics().density;
        }
        return dpScale;
    }

    // Copied from Android source code. Gets the ComponentNames for a given name
    private ComponentName[] componentsFromString(String names) {
        String[] namesArray = names.split(",");
        ComponentName[] componentNames = new ComponentName[namesArray.length];
        for (int i = 0; i < namesArray.length; i++) {
            componentNames[i] = ComponentName.unflattenFromString(namesArray[i]);
        }
        return componentNames;
    }

    public static class AppData {
        public final String appName;
        public final String packageName;
        public final Drawable icon;

        private AppData(String appName, String packageName, Drawable icon) {
            this.appName = appName;
            this.packageName = packageName;
            this.icon = icon;
        }
    }
}
