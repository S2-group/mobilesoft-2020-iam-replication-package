package com.suyashsrijan.forcedoze;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import eu.chainfire.libsuperuser.Shell;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class ForceDozeService extends Service {

    private static String CHANNEL_STATS = "CHANNEL_STATS";
    private static String CHANNEL_TIPS = "CHANNEL_TIPS";

    private static Shell.Interactive rootSession;
    private static Shell.Interactive nonRootSession;
    boolean isSuAvailable = false;
    boolean disableWhenCharging = true;
    boolean enableSensors = false;
    boolean useAutoRotateAndBrightnessFix = false;
    boolean showPersistentNotif = true;
    boolean ignoreLockscreenTimeout = false;
    boolean useXposedSensorWorkaround = false;
    boolean useNonRootSensorWorkaround = false;
    boolean turnOffWiFiInDoze = false;
    boolean turnOffDataInDoze = false;
    boolean wasWiFiTurnedOn = false;
    boolean wasMobileDataTurnedOn = false;
    boolean maintenance = false;
    boolean setPendingDozeEnterAlarm = false;
    int dozeEnterDelay = 0;
    Timer enterDozeTimer;
    Timer disableSensorsTimer;
    Timer enableSensorsTimer;
    DozeReceiver localDozeReceiver;
    ReloadSettingsReceiver reloadSettingsReceiver;
    PendingIntentDozeReceiver pendingIntentDozeReceiver;
    ReloadNotificationBlocklistReceiver reloadNotificationBlocklistReceiver;
    ReloadAppsBlocklistReceiver reloadAppsBlocklistReceiver;
    NotificationCompat.Builder mStatsBuilder;
    PendingIntent reenterDozePendingIntent;
    PowerManager pm;
    AlarmManager alarmManager;
    PowerManager.WakeLock tempWakeLock;
    Set<String> dozeUsageData;
    Set<String> dozeNotificationBlocklist;
    Set<String> dozeAppBlocklist;
    String sensorWhitelistPackage = "";
    String state = "";
    Long timeEnterDoze = 0L;
    Long timeExitDoze = 0L;
    String lastScreenOff = "Unknown";
    int lastDozeEnterBatteryLife = 0;
    int lastDozeExitBatteryLife = 0;
    String TAG = "ForceDozeService";
    String lastKnownState = "null";

    public ForceDozeService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        localDozeReceiver = new DozeReceiver();
        reloadSettingsReceiver = new ReloadSettingsReceiver();
        reloadNotificationBlocklistReceiver = new ReloadNotificationBlocklistReceiver();
        reloadAppsBlocklistReceiver = new ReloadAppsBlocklistReceiver();
        pendingIntentDozeReceiver = new PendingIntentDozeReceiver();
        enterDozeTimer = new Timer();
        enableSensorsTimer = new Timer();
        disableSensorsTimer = new Timer();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence statsName = getString(R.string.notification_channel_stats_name);
            String statsDescription = getString(R.string.notification_channel_stats_description);
            int statsImportance = NotificationManager.IMPORTANCE_MIN;
            NotificationChannel statsChannel = new NotificationChannel(CHANNEL_STATS, statsName, statsImportance);
            statsChannel.setDescription(statsDescription);

            CharSequence tipsName = getString(R.string.notification_channel_tips_name);
            String tipsDescription = getString(R.string.notification_channel_tips_description);
            int tipsImportance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel tipsChannel = new NotificationChannel(CHANNEL_TIPS, tipsName, tipsImportance);
            tipsChannel.setDescription(tipsDescription);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(statsChannel);
            notificationManager.createNotificationChannel(tipsChannel);
        }

        mStatsBuilder = new NotificationCompat.Builder(this, CHANNEL_STATS);
        pm = (PowerManager) getSystemService(POWER_SERVICE);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED);
        if (Utils.isDeviceRunningOnN()) {
            filter.addAction("android.os.action.LIGHT_DEVICE_IDLE_MODE_CHANGED");
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(reloadSettingsReceiver, new IntentFilter("reload-settings"));
        LocalBroadcastManager.getInstance(this).registerReceiver(reloadNotificationBlocklistReceiver, new IntentFilter("reload-notification-blocklist"));
        LocalBroadcastManager.getInstance(this).registerReceiver(reloadAppsBlocklistReceiver, new IntentFilter("reload-app-blocklist"));
        LocalBroadcastManager.getInstance(this).registerReceiver(pendingIntentDozeReceiver, new IntentFilter("reenter-doze"));
        this.registerReceiver(localDozeReceiver, filter);
        turnOffDataInDoze = getDefaultSharedPreferences(getApplicationContext()).getBoolean("turnOffDataInDoze", false);
        turnOffWiFiInDoze = getDefaultSharedPreferences(getApplicationContext()).getBoolean("turnOffWiFiInDoze", false);
        ignoreLockscreenTimeout = getDefaultSharedPreferences(getApplicationContext()).getBoolean("ignoreLockscreenTimeout", true);
        useXposedSensorWorkaround = getDefaultSharedPreferences(getApplicationContext()).getBoolean("useXposedSensorWorkaround", false);
        useNonRootSensorWorkaround = getDefaultSharedPreferences(getApplicationContext()).getBoolean("useNonRootSensorWorkaround", false);
        dozeEnterDelay = getDefaultSharedPreferences(getApplicationContext()).getInt("dozeEnterDelay", 0);
        useAutoRotateAndBrightnessFix = getDefaultSharedPreferences(getApplicationContext()).getBoolean("autoRotateAndBrightnessFix", false);
        sensorWhitelistPackage = getDefaultSharedPreferences(getApplicationContext()).getString("sensorWhitelistPackage", "");
        enableSensors = getDefaultSharedPreferences(getApplicationContext()).getBoolean("enableSensors", false);
        disableWhenCharging = getDefaultSharedPreferences(getApplicationContext()).getBoolean("disableWhenCharging", true);
        isSuAvailable = getDefaultSharedPreferences(getApplicationContext()).getBoolean("isSuAvailable", false);
        showPersistentNotif = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("showPersistentNotif", true);
        dozeUsageData = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getStringSet("dozeUsageDataAdvanced", new LinkedHashSet<String>());
        dozeNotificationBlocklist = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getStringSet("notificationBlockList", new LinkedHashSet<String>());
        dozeAppBlocklist = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getStringSet("dozeAppBlockList", new LinkedHashSet<String>());

        if (!Utils.isDumpPermissionGranted(getApplicationContext())) {
            if (isSuAvailable) {
                grantDumpPermission();
            }
        }

        if (Utils.isDeviceRunningOnN()) {
            if (!Utils.isSecureSettingsPermissionGranted(getApplicationContext())) {
                if (isSuAvailable) {
                    grantSecureSettingsPermission();
                }
            }
        }

        if (!Utils.isReadPhoneStatePermissionGranted(getApplicationContext())) {
            if (isSuAvailable) {
                grantReadPhoneStatePermission();
            }
        }

        // To initialize root shell/shell on service start
        if (isSuAvailable) {
            executeCommandWithRoot("whoami");
        } else {
            executeCommand("whoami");
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Stopping service and enabling sensors");
        this.unregisterReceiver(localDozeReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(reloadSettingsReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(pendingIntentDozeReceiver);
        if (!enableSensors) {
            executeCommand("dumpsys sensorservice enable");
        }
        if (rootSession != null) {
            rootSession.close();
            rootSession = null;
        }
        if (nonRootSession != null) {
            nonRootSession.close();
            nonRootSession = null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG, "Service has now started");
        if (showPersistentNotif) {
            showPersistentNotification();
        }
        addSelfToDozeWhitelist();
        lastKnownState = getDeviceIdleState();
        return START_STICKY;
    }

    public void reloadSettings() {
        Log.i(TAG, "ForceDoze settings reloaded ----------------------------------");
        dozeUsageData = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getStringSet("dozeUsageDataAdvanced", new LinkedHashSet<String>());
        Log.i(TAG, "dozeUsageData: " + "Total Entries -> " + dozeUsageData.size());
        turnOffDataInDoze = getDefaultSharedPreferences(getApplicationContext()).getBoolean("turnOffDataInDoze", false);
        Log.i(TAG, "turnOffDataInDoze: " + turnOffDataInDoze);
        turnOffWiFiInDoze = getDefaultSharedPreferences(getApplicationContext()).getBoolean("turnOffWiFiInDoze", false);
        Log.i(TAG, "turnOffWiFiInDoze: " + turnOffWiFiInDoze);
        ignoreLockscreenTimeout = getDefaultSharedPreferences(getApplicationContext()).getBoolean("ignoreLockscreenTimeout", false);
        Log.i(TAG, "ignoreLockscreenTimeout: " + ignoreLockscreenTimeout);
        dozeEnterDelay = getDefaultSharedPreferences(getApplicationContext()).getInt("dozeEnterDelay", 0);
        Log.i(TAG, "dozeEnterDelay: " + dozeEnterDelay);
        useAutoRotateAndBrightnessFix = getDefaultSharedPreferences(getApplicationContext()).getBoolean("autoRotateAndBrightnessFix", false);
        Log.i(TAG, "useAutoRotateAndBrightnessFix: " + useAutoRotateAndBrightnessFix);
        sensorWhitelistPackage = getDefaultSharedPreferences(getApplicationContext()).getString("sensorWhitelistPackage", "");
        Log.i(TAG, "sensorWhitelistPackage: " + sensorWhitelistPackage);
        enableSensors = getDefaultSharedPreferences(getApplicationContext()).getBoolean("enableSensors", false);
        Log.i(TAG, "enableSensors: " + enableSensors);
        disableWhenCharging = getDefaultSharedPreferences(getApplicationContext()).getBoolean("disableWhenCharging", true);
        Log.i(TAG, "disableWhenCharging: " + disableWhenCharging);
        showPersistentNotif = getDefaultSharedPreferences(getApplicationContext()).getBoolean("showPersistentNotif", false);
        Log.i(TAG, "showPersistentNotif: " + showPersistentNotif);
        useXposedSensorWorkaround = getDefaultSharedPreferences(getApplicationContext()).getBoolean("useXposedSensorWorkaround", false);
        Log.i(TAG, "useXposedSensorWorkaround: " + useXposedSensorWorkaround);
        useNonRootSensorWorkaround = getDefaultSharedPreferences(getApplicationContext()).getBoolean("useNonRootSensorWorkaround", false);
        Log.i(TAG, "useNonRootSensorWorkaround: " + useNonRootSensorWorkaround);
        Log.i(TAG, "ForceDoze settings reloaded ----------------------------------");
    }

    public void reloadNotificationBlockList() {
        Log.i(TAG, "Notification blocklist reloaded ----------------------------------");
        dozeNotificationBlocklist = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getStringSet("notificationBlockList", new LinkedHashSet<String>());
        Log.i(TAG, "notificationBlockList: " + dozeNotificationBlocklist.size() + " items");
        Log.i(TAG, "Notification blocklist reloaded ----------------------------------");
    }

    public void reloadAppsBlockList() {
        Log.i(TAG, "Apps blocklist reloaded ----------------------------------");
        dozeAppBlocklist = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getStringSet("dozeAppBlockList", new LinkedHashSet<String>());
        Log.i(TAG, "dozeAppBlockList: " + dozeAppBlocklist.size() + " items");
        Log.i(TAG, "Apps blocklist reloaded ----------------------------------");
    }

    public void grantDumpPermission() {
        Log.i(TAG, "Granting android.permission.DUMP to com.suyashsrijan.forcedoze");
        executeCommandWithRoot("pm grant com.suyashsrijan.forcedoze android.permission.DUMP");
    }

    public void grantSecureSettingsPermission() {
        Log.i(TAG, "Granting android.permission.WRITRE_SECURE_SETTINGS to com.suyashsrijan.forcedoze");
        executeCommandWithRoot("pm grant com.suyashsrijan.forcedoze android.permission.WRITE_SECURE_SETTINGS");
    }

    public void grantReadPhoneStatePermission() {
        Log.i(TAG, "Granting android.permission.READ_PHONE_STATE to com.suyashsrijan.forcedoze");
        executeCommandWithRoot("pm grant com.suyashsrijan.forcedoze android.permission.READ_PHONE_STATE");
    }

    public void addSelfToDozeWhitelist() {
        Log.i(TAG, "Checking self-whitelist capability....");
        Log.i(TAG, "Nougat: " + Utils.isDeviceRunningOnN());
        Log.i(TAG, "SU available: " + isSuAvailable);
        String packageName = getPackageName();
        if (!pm.isIgnoringBatteryOptimizations(packageName)) {
            if (!Utils.isDeviceRunningOnN()) {
                Log.i(TAG, "Adding service to Doze whitelist for stability");
                executeCommand("dumpsys deviceidle whitelist +com.suyashsrijan.forcedoze");
            } else if (Utils.isDeviceRunningOnN() && isSuAvailable) {
                Log.i(TAG, "Adding service to Doze whitelist for stability");
                executeCommandWithRoot("dumpsys deviceidle whitelist +com.suyashsrijan.forcedoze");
            } else {
                Log.i(TAG, "Service cannot be added to Doze whitelist because user is on Nougat. Showing notification...");
                Intent notificationIntent = new Intent();
                notificationIntent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 0,
                        notificationIntent, 0);
                Notification n = new NotificationCompat.Builder(this, CHANNEL_TIPS)
                        .setContentTitle("ForceDoze")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("ForceDoze needs to be added to the Doze whitelist in order to work reliably. Please click on this notification to open the battery optimisation view, click on 'ForceDoze' and select 'Don\'t' Optimize'"))
                        .setSmallIcon(R.drawable.ic_battery_health)
                        .setPriority(1)
                        .setContentIntent(intent)
                        .setOngoing(false).build();
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(8765, n);
            }
        } else {
            Log.i(TAG, "Service already in Doze whitelist for stability");
        }
    }

    public void enterDoze(Context context) {
        if (!getDeviceIdleState().equals("IDLE")) {
            if (!Utils.isScreenOn(context)) {
                lastKnownState = "IDLE";
                if (tempWakeLock != null) {
                    if (tempWakeLock.isHeld()) {
                        Log.i(TAG, "Releasing ForceDozeTempWakelock");
                        tempWakeLock.release();
                    }
                }

                if (dozeAppBlocklist.size() != 0) {
                    Log.i(TAG, "Disabling apps that are in the Doze app blocklist");
                    for (String pkg : dozeAppBlocklist) {
                        setPackageState(pkg, false);
                    }
                }

                if (dozeNotificationBlocklist.size() != 0) {
                    Log.i(TAG, "Disabling notifications for apps in the Notification blocklist");
                    for (String pkg : dozeNotificationBlocklist) {
                        if (!dozeAppBlocklist.contains(pkg)) {
                            setNotificationEnabledForPackage(pkg, false);
                        }
                    }
                }
                timeEnterDoze = System.currentTimeMillis();
                if (Utils.isConnectedToCharger(getApplicationContext())) {
                    lastDozeEnterBatteryLife = 0;
                } else {
                    lastDozeEnterBatteryLife = Utils.getBatteryLevel(getApplicationContext());
                }
                Log.i(TAG, "Entering Doze");
                if (Utils.isDeviceRunningOnN()) {
                    if (isSuAvailable) {
                        executeCommandWithRoot("dumpsys deviceidle force-idle deep");
                    } else {
                        Log.i(TAG, "Unrooted device, putting custom values in device_idle_constants...");
                        Settings.Global.putString(getContentResolver(), "device_idle_constants", "inactive_to=600000,light_after_inactive_to=300000,idle_after_inactive_to=5100,sensing_to=5100,locating_to=5100,location_accuracy=10000");
                    }
                } else {
                    executeCommand("dumpsys deviceidle force-idle");
                }
                lastScreenOff = Utils.getDateCurrentTimeZone(System.currentTimeMillis());

                dozeUsageData.add(Long.toString(System.currentTimeMillis()).concat(",").concat(Float.toString(Utils.isConnectedToCharger(getApplicationContext()) ? 0.0f : Utils.getBatteryLevel(getApplicationContext()))).concat(",").concat("ENTER"));
                saveDozeDataStats();

                if (!useXposedSensorWorkaround) {
                    if (!enableSensors) {
                        disableSensorsTimer = new Timer();
                        disableSensorsTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Log.i(TAG, "Disabling motion sensors");
                                if (sensorWhitelistPackage.equals("")) {
                                    executeCommand("dumpsys sensorservice restrict");
                                } else {
                                    Log.i(TAG, "Package " + sensorWhitelistPackage + " is whitelisted from sensorservice");
                                    Log.i(TAG, "Note: Packages that get whitelisted are supposed to request sensor access again, if the app doesn't work, email the dev of that app!");
                                    executeCommand("dumpsys sensorservice restrict " + sensorWhitelistPackage);
                                }
                            }
                        }, 2000);
                    } else {
                        Log.i(TAG, "Not disabling motion sensors because enableSensors=true");
                    }
                } else {
                    Log.i(TAG, "Xposed Sensor workaround selected, not disabling sensors");
                }

                if (turnOffWiFiInDoze) {
                    wasWiFiTurnedOn = Utils.isWiFiEnabled(context);
                    Log.i(TAG, "wasWiFiTurnedOn: " + wasWiFiTurnedOn);
                    if (wasWiFiTurnedOn) {
                        Log.i(TAG, "Disabling WiFi");
                        disableWiFi();
                    }

                }

                if (turnOffDataInDoze) {
                    wasMobileDataTurnedOn = Utils.isMobileDataEnabled(context);
                    Log.i(TAG, "wasDataTurnedOn: " + wasMobileDataTurnedOn);
                    if (wasMobileDataTurnedOn) {
                        Log.i(TAG, "Disabling mobile data");
                        disableMobileData();
                    }
                }

            } else {
                Log.i(TAG, "Screen is on, skip entering Doze");
            }
        } else {
            Log.i(TAG, "enterDoze() received but skipping because device is already Dozing");
        }
    }

    public void exitDoze() {
        timeExitDoze = System.currentTimeMillis();
        if (Utils.isConnectedToCharger(getApplicationContext())) {
            lastDozeExitBatteryLife = 0;
        } else {
            lastDozeExitBatteryLife = Utils.getBatteryLevel(getApplicationContext());
        }
        lastKnownState = "ACTIVE";
        if (Utils.isDeviceRunningOnN()) {
            if (isSuAvailable) {
                executeCommandWithRoot("dumpsys deviceidle unforce");
            } else {
                Settings.Global.putString(getContentResolver(), "device_idle_constants", null);
            }
        } else {
            executeCommand("dumpsys deviceidle step");
        }

        Log.i(TAG, "Current Doze state: " + getDeviceIdleState());

        dozeUsageData.add(Long.toString(System.currentTimeMillis()).concat(",").concat(Float.toString(Utils.isConnectedToCharger(getApplicationContext()) ? 0.0f : Utils.getBatteryLevel(getApplicationContext()))).concat(",").concat("EXIT"));
        saveDozeDataStats();

        if (dozeAppBlocklist.size() != 0) {
            Log.i(TAG, "Re-enabling apps that are in the Doze app blocklist");
            for (String pkg : dozeAppBlocklist) {
                setPackageState(pkg, true);
            }
        }

        if (dozeNotificationBlocklist.size() != 0) {
            Log.i(TAG, "Re-enabling notifications for apps in the Notification blocklist");
            for (String pkg : dozeNotificationBlocklist) {
                if (!dozeAppBlocklist.contains(pkg)) {
                    setNotificationEnabledForPackage(pkg, true);
                }
            }
        }

        if (!useXposedSensorWorkaround) {
            if (!enableSensors) {
                enableSensorsTimer = new Timer();
                enableSensorsTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Log.i(TAG, "Re-enabling motion sensors");
                        executeCommand("dumpsys sensorservice enable");
                        autoRotateBrightnessFix();
                    }
                }, 2000);
            }
        }

        if (useNonRootSensorWorkaround) {
            try {
                if (reenterDozePendingIntent != null) {
                    reenterDozePendingIntent.cancel();
                    alarmManager.cancel(reenterDozePendingIntent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Timer updateNotif = new Timer();
        updateNotif.schedule(new TimerTask() {
            @Override
            public void run() {
                if (showPersistentNotif) {
                    updatePersistentNotification(lastScreenOff, Utils.diffInMins(timeEnterDoze, timeExitDoze), (lastDozeEnterBatteryLife - lastDozeExitBatteryLife));
                }
            }
        }, 2000);

    }

    public void executeCommand(final String command) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (nonRootSession != null) {
                    nonRootSession.addCommand(command, 0, new Shell.OnCommandResultListener() {
                        @Override
                        public void onCommandResult(int commandCode, int exitCode, List<String> output) {
                            printShellOutput(output);
                        }
                    });
                } else {
                    nonRootSession = new Shell.Builder().
                            useSH().
                            setWantSTDERR(true).
                            setWatchdogTimeout(5).
                            setMinimalLogging(true).
                            open(new Shell.OnCommandResultListener() {
                                @Override
                                public void onCommandResult(int commandCode, int exitCode, List<String> output) {
                                    if (exitCode != Shell.OnCommandResultListener.SHELL_RUNNING) {
                                        Log.i(TAG, "Error opening shell: exitCode " + exitCode);
                                    } else {
                                        nonRootSession.addCommand(command, 0, new Shell.OnCommandResultListener() {
                                            @Override
                                            public void onCommandResult(int commandCode, int exitCode, List<String> output) {
                                                printShellOutput(output);
                                            }
                                        });
                                    }
                                }
                            });
                }
            }
        });
    }

    public void executeCommandWithRoot(final String command) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (rootSession != null) {
                    rootSession.addCommand(command, 0, new Shell.OnCommandResultListener() {
                        @Override
                        public void onCommandResult(int commandCode, int exitCode, List<String> output) {
                            printShellOutput(output);
                        }
                    });
                } else {
                    rootSession = new Shell.Builder().
                            useSU().
                            setWantSTDERR(true).
                            setWatchdogTimeout(5).
                            setMinimalLogging(true).
                            open(new Shell.OnCommandResultListener() {
                                @Override
                                public void onCommandResult(int commandCode, int exitCode, List<String> output) {
                                    if (exitCode != Shell.OnCommandResultListener.SHELL_RUNNING) {
                                        Log.i(TAG, "Error opening root shell: exitCode " + exitCode);
                                    } else {
                                        rootSession.addCommand(command, 0, new Shell.OnCommandResultListener() {
                                            @Override
                                            public void onCommandResult(int commandCode, int exitCode, List<String> output) {
                                                printShellOutput(output);
                                            }
                                        });
                                    }
                                }
                            });
                }
            }
        });
    }

    public void printShellOutput(List<String> output) {
        if (output != null && !output.isEmpty()) {
            for (String s : output) {
                Log.i(TAG, s);
            }
        }
    }

    public void saveDozeDataStats() {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("dozeUsageDataAdvanced");
        editor.apply();
        editor.putStringSet("dozeUsageDataAdvanced", dozeUsageData);
        editor.apply();
    }

    public void autoRotateBrightnessFix() {
        if (useAutoRotateAndBrightnessFix && Utils.isWriteSettingsPermissionGranted(getApplicationContext())) {
            Log.i(TAG, "Executing auto-rotate fix by doing a toggle");
            Log.i(TAG, "Current value: " + Boolean.toString(Utils.isAutoRotateEnabled(getApplicationContext())) + " to " + Boolean.toString(!Utils.isAutoRotateEnabled(getApplicationContext())));
            Utils.setAutoRotateEnabled(getApplicationContext(), !Utils.isAutoRotateEnabled(getApplicationContext()));
            try {
                Log.i(TAG, "Sleeping for 100ms");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Log.e(TAG, e.toString());
            }
            Log.i(TAG, "Current value: " + Boolean.toString(Utils.isAutoRotateEnabled(getApplicationContext())) + " to " + Boolean.toString(!Utils.isAutoRotateEnabled(getApplicationContext())));
            Utils.setAutoRotateEnabled(getApplicationContext(), !Utils.isAutoRotateEnabled(getApplicationContext()));
            try {
                Log.i(TAG, "Sleeping for 100ms");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Log.e(TAG, e.toString());
            }
            Log.i(TAG, "Executing auto-brightness fix by doing a toggle");
            Log.i(TAG, "Current value: " + Boolean.toString(Utils.isAutoBrightnessEnabled(getApplicationContext())) + " to " + Boolean.toString(!Utils.isAutoBrightnessEnabled(getApplicationContext())));
            Utils.setAutoBrightnessEnabled(getApplicationContext(), !Utils.isAutoBrightnessEnabled(getApplicationContext()));
            try {
                Log.i(TAG, "Sleeping for 100ms");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Log.e(TAG, e.toString());
            }
            Log.i(TAG, "Current value: " + Boolean.toString(Utils.isAutoBrightnessEnabled(getApplicationContext())) + " to " + Boolean.toString(!Utils.isAutoBrightnessEnabled(getApplicationContext())));
            Utils.setAutoBrightnessEnabled(getApplicationContext(), !Utils.isAutoBrightnessEnabled(getApplicationContext()));
        }
    }

    public void showPersistentNotification() {
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 0,
                notificationIntent, 0);
        Notification n = mStatsBuilder
                .setStyle(
                        new NotificationCompat.BigTextStyle()
                                .bigText(getString(R.string.stats_no_data)))
                .setSmallIcon(R.drawable.ic_battery_health)
                .setPriority(-2)
                .setContentIntent(intent)
                .setOngoing(true)
                .build();
        startForeground(1234, n);
    }

    public void updatePersistentNotification(String lastScreenOff, int timeSpentDozing, int batteryUsage) {
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 0,
                notificationIntent, 0);
        Notification n = mStatsBuilder
                .setStyle(
                        new NotificationCompat.BigTextStyle()
                                .bigText(getString(R.string.stats_long_text, lastScreenOff, timeSpentDozing, batteryUsage))
                                .setSummaryText(getString(R.string.stats_summary_text, batteryUsage)))
                .setShowWhen(false)
                .setSmallIcon(R.drawable.ic_battery_health)
                .setPriority(-2)
                .setContentIntent(intent)
                .setOngoing(true)
                .build();
        startForeground(1234, n);
    }

    public void setMobileNetwork(Context context, int targetState) {

        if (!Utils.isReadPhoneStatePermissionGranted(context)) {
            grantReadPhoneStatePermission();
        }

        String command;
        try {
            String transactionCode = getTransactionCode(context);
            SubscriptionManager mSubscriptionManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
            for (int i = 0; i < mSubscriptionManager.getActiveSubscriptionInfoCountMax(); i++) {
                if (transactionCode != null && transactionCode.length() > 0) {
                    int subscriptionId = mSubscriptionManager.getActiveSubscriptionInfoList().get(i).getSubscriptionId();
                    command = "service call phone " + transactionCode + " i32 " + subscriptionId + " i32 " + targetState;
                    List<String> output = Shell.SU.run(command);
                    if (output != null) {
                        for (String s : output) {
                            Log.i(TAG, s);
                        }
                    } else {
                        Log.i(TAG, "Error occurred while executing command (" + command + ")");
                    }
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Failed to toggle mobile data: " + e.getMessage());
        }
    }

    private static String getTransactionCode(Context context) {
        try {
            final TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final Class<?> mTelephonyClass = Class.forName(mTelephonyManager.getClass().getName());
            final Method mTelephonyMethod = mTelephonyClass.getDeclaredMethod("getITelephony");
            mTelephonyMethod.setAccessible(true);
            final Object mTelephonyStub = mTelephonyMethod.invoke(mTelephonyManager);
            final Class<?> mTelephonyStubClass = Class.forName(mTelephonyStub.getClass().getName());
            final Class<?> mClass = mTelephonyStubClass.getDeclaringClass();
            final Field field = mClass.getDeclaredField("TRANSACTION_setDataEnabled");
            field.setAccessible(true);
            return String.valueOf(field.getInt(null));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setNotificationEnabledForPackage(String packageName, boolean enabled) {
        int command = 0;
        try {
            Field field = Class.forName("android.app.INotificationManager").getDeclaredClasses()[0].getDeclaredField("TRANSACTION_setNotificationsEnabledForPackage");
            field.setAccessible(true);
            command = field.getInt(null);
        } catch (ClassNotFoundException e) {
            Log.i(TAG, e.toString());
        } catch (NoSuchFieldException e2) {
            Log.i(TAG, e2.toString());
        } catch (IllegalAccessException e3) {
            Log.i(TAG, e3.toString());
        }

        ArrayList<PackageInfo> packageInfos = new ArrayList<>(getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA));

        for (PackageInfo p : packageInfos) {
            if (p.packageName.equals(packageName)) {
                Log.i(TAG, (enabled ? "Turning on " : "Turning off ") + "notifications for " + packageName);
                String exec = String.format(Locale.US, "service call notification %d s16 %s i32 %d i32 %d", command, packageName, p.applicationInfo.uid, enabled ? 1 : 0);
                executeCommandWithRoot(exec);
            }
        }
    }

    public void setPackageState(String packageName, boolean enabled) {
        Log.i(TAG, (enabled ? "Enabling " : "Disabling ") + packageName);
        executeCommandWithRoot("pm " + (enabled ? "enable " : "disable ") + packageName);
    }

    public String getDeviceIdleState() {
        Log.i(TAG, "Fetching Device Idle state...");
        if (Utils.isDeviceRunningOnN()) {
            if (isSuAvailable) {
                if (rootSession != null) {
                    rootSession.addCommand("dumpsys deviceidle", 0, new Shell.OnCommandResultListener() {
                        @Override
                        public void onCommandResult(int commandCode, int exitCode, List<String> output) {
                            if (output != null && !output.isEmpty()) {
                                String outputString = TextUtils.join(", ", output);
                                if (outputString.contains("mState=ACTIVE")) {
                                    state = "ACTIVE";
                                } else if (outputString.contains("mState=INACTIVE")) {
                                    state = "INACTIVE";
                                } else if (outputString.contains("mState=IDLE_PENDING")) {
                                    state = "IDLE_PENDING";
                                } else if (outputString.contains("mState=SENSING")) {
                                    state = "SENSING";
                                } else if (outputString.contains("mState=LOCATING")) {
                                    state = "LOCATING";
                                } else if (outputString.contains("mState=IDLE")) {
                                    state = "IDLE";
                                } else if (outputString.contains("mState=IDLE_MAINTENANCE")) {
                                    state = "IDLE_MAINTENANCE";
                                } else if (outputString.contains("mState=PRE_IDLE")) {
                                    state = "PRE_IDLE";
                                } else if (outputString.contains("mState=WAITING_FOR_NETWORK")) {
                                    state = "WAITING_FOR_NETWORK";
                                } else if (outputString.contains("mState=OVERRIDE")) {
                                    state = "OVERRIDE";
                                }
                            } else {
                                if (pm.isDeviceIdleMode()) {
                                    state = "IDLE";
                                } else {
                                    state = "ACTIVE";
                                }
                            }
                        }
                    });
                }
            } else {
                if (pm.isDeviceIdleMode()) {
                    state = "IDLE";
                } else {
                    state = "ACTIVE";
                }
            }
        } else {
            List<String> output = Shell.SH.run("dumpsys deviceidle");
            String outputString = TextUtils.join(", ", output);
            if (outputString.contains("mState=ACTIVE")) {
                state = "ACTIVE";
            } else if (outputString.contains("mState=INACTIVE")) {
                state = "INACTIVE";
            } else if (outputString.contains("mState=IDLE_PENDING")) {
                state = "IDLE_PENDING";
            } else if (outputString.contains("mState=SENSING")) {
                state = "SENSING";
            } else if (outputString.contains("mState=LOCATING")) {
                state = "LOCATING";
            } else if (outputString.contains("mState=IDLE")) {
                state = "IDLE";
            } else if (outputString.contains("mState=IDLE_MAINTENANCE")) {
                state = "IDLE_MAINTENANCE";
            }
        }

        return state;
    }


    public void disableMobileData() {
        setMobileNetwork(getApplicationContext(), 0);
    }

    public void enableMobileData() {
        setMobileNetwork(getApplicationContext(), 1);
    }

    public void disableWiFi() {
        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);
    }

    public void enableWiFi() {
        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true);
    }

    class ReloadSettingsReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "User changed a setting, loading new settings into service");
            reloadSettings();
        }
    }

    class ReloadNotificationBlocklistReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "User modified Notification blocklist, loading new packages into service");
            reloadNotificationBlockList();
        }
    }

    class ReloadAppsBlocklistReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "User modified Doze app blocklist, loading new packages into service");
            reloadAppsBlockList();
        }
    }

    class PendingIntentDozeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "Pending intent broadcast received");
            setPendingDozeEnterAlarm = false;
            if (Utils.isDeviceRunningOnN()) {
                if (isSuAvailable) {
                    executeCommandWithRoot("dumpsys deviceidle force-idle deep");
                } else {
                    Settings.Global.putString(getContentResolver(), "device_idle_constants", "inactive_to=600000,light_after_inactive_to=300000,idle_after_inactive_to=5100,sensing_to=5100,locating_to=5100,location_accuracy=10000");
                }
            } else {
                executeCommand("dumpsys deviceidle force-idle");
            }
        }
    }

    class DozeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            int time = Settings.Secure.getInt(getContentResolver(), "lock_screen_lock_after_timeout", 5000);
            if (time == 0) {
                time = 1000;
            }
            int delay = dozeEnterDelay * 60 * 1000;
            time = time + delay;

            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.i(TAG, "Screen ON received");
                Log.i(TAG, "Last known Doze state: " + lastKnownState);

                if (tempWakeLock != null) {
                    if (tempWakeLock.isHeld()) {
                        Log.i(TAG, "Releasing ForceDozeTempWakelock");
                        tempWakeLock.release();
                    }
                }

                if (turnOffWiFiInDoze) {
                    Log.i(TAG, "wasWiFiTurnedOn: " + wasWiFiTurnedOn);
                    if (wasWiFiTurnedOn) {
                        Log.i(TAG, "Enabling WiFi");
                        enableWiFi();
                        wasWiFiTurnedOn = false;
                    }

                }

                if (turnOffDataInDoze) {
                    Log.i(TAG, "wasDataTurnedOn: " + wasMobileDataTurnedOn);
                    if (wasMobileDataTurnedOn) {
                        Log.i(TAG, "Enabling mobile data");
                        enableMobileData();
                        wasMobileDataTurnedOn = false;
                    }
                }

                if (!getDeviceIdleState().equals("ACTIVE") || !lastKnownState.equals("ACTIVE")) {
                    Log.i(TAG, "Exiting Doze");
                    exitDoze();
                } else {
                    if (ignoreLockscreenTimeout) {
                        Log.i(TAG, "Cancelling enterDoze() because user turned on screen and " + Integer.toString(delay) + "ms has not passed OR disableWhenCharging=true");
                    } else {
                        Log.i(TAG, "Cancelling enterDoze() because user turned on screen and " + Integer.toString(time) + "ms has not passed OR disableWhenCharging=true");
                    }
                    enterDozeTimer.cancel();
                }

            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i(TAG, "Screen OFF received");
                if (Utils.isConnectedToCharger(getApplicationContext()) && disableWhenCharging) {
                    Log.i(TAG, "Connected to charger and disableWhenCharging=true, skip entering Doze");
                } else if (Utils.isUserInCommunicationCall(context)) {
                    Log.i(TAG, "User is in a VOIP call or an audio/video chat, skip entering Doze");
                } else if (Utils.isUserInCall(context)) {
                    Log.i(TAG, "User is in a phone call, skip entering Doze");
                } else {
                    Log.i(TAG, "Doze delay: " + delay + "ms");
                    if (ignoreLockscreenTimeout) {
                        if (dozeEnterDelay == 0) {
                            Log.i(TAG, "Ignoring lockscreen timeout value and entering Doze immediately");
                            enterDoze(context);
                        } else {
                            Log.i(TAG, "Waiting for " + Integer.toString(delay) + "ms and then entering Doze");
                            tempWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ForceDozeTempWakelock");
                            Log.i(TAG, "Acquiring temporary wakelock (ForceDozeTempWakelock)");
                            tempWakeLock.acquire();
                            enterDozeTimer = new Timer();
                            enterDozeTimer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    enterDoze(context);
                                }
                            }, delay);
                        }
                    } else {
                        Log.i(TAG, "Waiting for " + Integer.toString(time) + "ms and then entering Doze");
                        if (Utils.isLockscreenTimeoutValueTooHigh(getContentResolver())) {
                            tempWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ForceDozeTempWakelock");
                            Log.i(TAG, "Acquiring temporary wakelock (ForceDozeTempWakelock)");
                            tempWakeLock.acquire();
                        }
                        enterDozeTimer = new Timer();
                        enterDozeTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                enterDoze(context);
                            }
                        }, time);
                    }

                }
            } else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                if ((getDeviceIdleState().equals("IDLE") || !Utils.isScreenOn(context)) && disableWhenCharging) {
                    Log.i(TAG, "Charger connected, exiting Doze mode");
                    enterDozeTimer.cancel();
                    exitDoze();
                }
            } else if (intent.getAction().equals(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED)) {
                if (!Utils.isScreenOn(context)) {
                    Log.i(TAG, "ACTION_DEVICE_IDLE_MODE_CHANGED received");
                    lastKnownState = getDeviceIdleState();
                    Log.i(TAG, "Current (Deep) state: " + getDeviceIdleState());
                }

                if (!Utils.isScreenOn(context) && getDeviceIdleState().equals("IDLE_MAINTENANCE")) {
                    if (!maintenance) {
                        Log.i(TAG, "Device exited Doze for maintenance");
                        dozeUsageData.add(Long.toString(System.currentTimeMillis()).concat(",").concat(Float.toString(Utils.getBatteryLevel(getApplicationContext()))).concat(",").concat("EXIT_MAINTENANCE"));
                        saveDozeDataStats();

                        if (turnOffDataInDoze) {
                            if (wasMobileDataTurnedOn) {
                                Log.i(TAG, "Enabling mobile data");
                                enableMobileData();
                                wasMobileDataTurnedOn = false;
                            }
                        }
                        if (turnOffWiFiInDoze) {
                            if (wasWiFiTurnedOn) {
                                Log.i(TAG, "Enabling WiFi");
                                enableWiFi();
                                wasWiFiTurnedOn = false;
                            }
                        }

                        maintenance = true;
                    }
                } else if (!Utils.isScreenOn(context) && getDeviceIdleState().equals("IDLE")) {
                    if (maintenance) {
                        Log.i(TAG, "Device entered Doze after maintenance");
                        dozeUsageData.add(Long.toString(System.currentTimeMillis()).concat(",").concat(Float.toString(Utils.getBatteryLevel(getApplicationContext()))).concat(",").concat("ENTER_MAINTENANCE"));
                        saveDozeDataStats();

                        if (turnOffWiFiInDoze) {
                            wasWiFiTurnedOn = Utils.isWiFiEnabled(context);
                            if (wasWiFiTurnedOn) {
                                Log.i(TAG, "Disabling WiFi");
                                disableWiFi();
                            }

                        }

                        if (turnOffDataInDoze) {
                            wasMobileDataTurnedOn = Utils.isMobileDataEnabled(context);
                            if (wasMobileDataTurnedOn) {
                                Log.i(TAG, "Disabling mobile data");
                                disableMobileData();
                            }
                        }

                        maintenance = false;
                    }
                }

                if (useNonRootSensorWorkaround) {
                    if (!setPendingDozeEnterAlarm) {
                        if (!Utils.isScreenOn(context) && (!getDeviceIdleState().equals("IDLE") || !getDeviceIdleState().equals("IDLE_MAINTENANCE"))) {
                            Log.i(TAG, "Device gone out of Doze, scheduling pendingIntent for enterDoze in 15 mins");
                            reenterDozePendingIntent = PendingIntent.getBroadcast(context, 1, new Intent(context, ReenterDoze.class), PendingIntent.FLAG_CANCEL_CURRENT);
                            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 900000, reenterDozePendingIntent);
                            setPendingDozeEnterAlarm = true;
                        }
                    }
                }
            } else if (intent.getAction().equals("android.os.action.LIGHT_DEVICE_IDLE_MODE_CHANGED")) {
                if (!Utils.isScreenOn(context)) {
                    Log.i(TAG, "LIGHT_DEVICE_IDLE_MODE_CHANGED received");
                    Log.i(TAG, "Current (Light) state: " + getDeviceIdleState());
                    lastKnownState = getDeviceIdleState();
                }
            }
        }
    }

}
