package com.sportsoutclass.outclassdl;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * This Class provides an Alert Dialog to the user for him to rate the app after
 * a certain number of days and launches
 */
class AppRater {
    private final static String APP_TITLE = "DL Calculator";// App Name
    private static int DAYS_UNTIL_PROMPT;//Min number of days
    private final static int LAUNCHES_UNTIL_PROMPT = 3;//Min number of launches
    private static final String GooglePlayStorePackageNameOld = "com.google.market";
    private static final String GooglePlayStorePackageNameNew = "com.android.vending";

    static void app_launched(Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("appRater", 0);
        if (prefs.getBoolean("doNotShowAgain", false)) {
            return;
        }
        SharedPreferences.Editor editor = prefs.edit();

        // Increment launch counter
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        DAYS_UNTIL_PROMPT = prefs.getInt("days_to_remind", 3);
        Log.v("Remind Date at Start:", String.valueOf(DAYS_UNTIL_PROMPT));

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstLaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstLaunch", date_firstLaunch);
        }

        // Wait at least n days before opening
        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= date_firstLaunch +
                    (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
                showRateDialog(mContext, editor);
            }
        }

        editor.apply();
    }

    private static void showRateDialog(final Context mContext, final SharedPreferences.Editor editor) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle("Rate " + APP_TITLE);
        dialog.setMessage("If you enjoy using " + APP_TITLE + ", please take a moment to rate it. \n\nThank you for your support!");
        dialog.setPositiveButton("Rate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean googlePlay = checkWhichStore(mContext);
                Log.v("googlePlay: ", String.valueOf(googlePlay));
                // getPackageName() from Context or Activity object
                final String appPackageName = mContext.getPackageName();
                Uri uri = Uri.parse((googlePlay ? "market://details?id=" : "amzn://apps/android?p=") + appPackageName);
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

                PackageManager packageManager = mContext.getPackageManager();
                List activities = packageManager.queryIntentActivities(goToMarket,
                        PackageManager.MATCH_DEFAULT_ONLY);
                //Checking if there are activities that can handle going to market
                //required for crash safety
                boolean isIntentSafe = activities.size() > 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }else{
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                if(isIntentSafe){
                    try {
                        mContext.startActivity(goToMarket);
                    } catch (android.content.ActivityNotFoundException a) {
                        try {
                            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse((googlePlay ? "https://play.google.com/store/apps/details?id=" : "http://www.amazon.com/gp/mas/dl/android?p=") + appPackageName)));
                        } catch (ActivityNotFoundException e2) {
                            Toast.makeText(mContext, "You don't have any app that can open this link", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                editor.putBoolean("doNotShowAgain", true);
                editor.commit();
                dialog.dismiss();
            }
        });

        dialog.setNeutralButton("Remind Me Later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DAYS_UNTIL_PROMPT = DAYS_UNTIL_PROMPT + 7;
                editor.putInt("days_to_remind", DAYS_UNTIL_PROMPT);
                Log.v("Remind Date set again:", String.valueOf(DAYS_UNTIL_PROMPT));
                editor.commit();
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("No Thanks", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (editor != null) {
                    editor.putBoolean("doNotShowAgain", true);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    /**
     * Checks if Google play is installed or not in the device
     *
     * @return true if installed
     */
    private static boolean checkWhichStore(Context ctx) {
        PackageManager packageManager = ctx.getPackageManager();
        boolean googlePlayStoreInstalled = false;
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(GooglePlayStorePackageNameOld) ||
                    packageInfo.packageName.equals(GooglePlayStorePackageNameNew)) {
                googlePlayStoreInstalled = true;
                break;
            }
        }
        return googlePlayStoreInstalled;
    }
}
