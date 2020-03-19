package com.projectsexception.myapplist.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;

import com.projectsexception.myapplist.R;
import com.projectsexception.myapplist.model.AppInfo;
import com.projectsexception.util.AndroidUtils;
import com.projectsexception.util.CustomLog;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class AppUtil {

    private static final String SCHEME = "package";
    private static final String APP_PKG_NAME_21 = "com.android.settings.ApplicationPkgName";
    private static final String APP_PKG_NAME_22 = "pkg";
    private static final String APP_DETAILS_PACKAGE_NAME = "com.android.settings";
    private static final String APP_DETAILS_CLASS_NAME = "com.android.settings.InstalledAppDetails";
    private static final String APP_GOOGLE_PLAY = "com.android.vending";

    public static ArrayList<AppInfo> loadAppInfoList(PackageManager packageManager, boolean hideSystemApps) {
        List<ApplicationInfo> applicationInfoList = null;
        try {
            applicationInfoList = packageManager.getInstalledApplications(0);
        } catch (Exception e) {
            CustomLog.getInstance().error("AppUtil", "Error loading applications", e);
        }
        if (applicationInfoList == null) {
            applicationInfoList = new ArrayList<ApplicationInfo>();
        }

        // Create corresponding array of entries and load their labels.
        ArrayList<AppInfo> entries = new ArrayList<AppInfo>();
        AppInfo entry;
        for (ApplicationInfo applicationInfo : applicationInfoList) {
            if (!hideSystemApps || !isSystemPackage(applicationInfo)) {
                entry = createAppInfo(packageManager, applicationInfo);
                entries.add(entry);
            }
        }
        return entries;
    }

    public static ArrayList<AppInfo> loadAppInfoListSecure(PackageManager packageManager, boolean hideSystemApps) {
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        if (packageInfoList == null) {
            packageInfoList = new ArrayList<PackageInfo>();
        }
        ArrayList<AppInfo> entries = new ArrayList<AppInfo>();
        AppInfo entry;
        for (PackageInfo packageInfo : packageInfoList) {
            if (packageInfo.versionName == null) {
                continue;
            }
            try {
                ApplicationInfo appInfo = packageManager.getApplicationInfo(packageInfo.packageName, 0);
                if (!hideSystemApps || !isSystemPackage(appInfo)) {
                    entry = createAppInfo(packageManager, appInfo);
                    entries.add(entry);
                }
            } catch (NameNotFoundException e) {
                CustomLog.getInstance().warn("AppUtil", e);
            }
        }
        return entries;
    }

    public static AppInfo loadAppInfo(PackageManager mPm, String packageName) {
        ApplicationInfo applicationInfo = loadApplicationInfo(mPm, packageName);
        AppInfo appInfo = null;
        if (applicationInfo != null) {
            appInfo = createAppInfo(mPm, applicationInfo);
        }
        return appInfo;
    }

    public static ApplicationInfo loadApplicationInfo(PackageManager mPm, String packageName) {
        try {
            return mPm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static Drawable loadApplicationIcon(PackageManager mPm, String packageName) {
        ApplicationInfo applicationInfo = loadApplicationInfo(mPm, packageName);
        Drawable icon = null;
        if (applicationInfo != null) {
            try {
                icon = applicationInfo.loadIcon(mPm);
            } catch (OutOfMemoryError error) {
                CustomLog.getInstance().warn("AppUtil", "Unable to load application icon");
            }
        }
        return icon;
    }

    public static PackageInfo loadPackageInfo(PackageManager mPm, String packageName) {
        try {
            return mPm.getPackageInfo(packageName,
                    PackageManager.GET_META_DATA | PackageManager.GET_PERMISSIONS);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static boolean isGooglePlayAvailable(PackageManager mPm) {
        return loadPackageInfo(mPm, APP_GOOGLE_PLAY) != null;
    }

    public static boolean isFromGooglePlay(PackageManager mPm, String packageName) {
        String installPM = mPm.getInstallerPackageName(packageName);
        if (installPM == null) {
            // Definitely not from Google Play
            return false;
        } else if (installPM.equals("com.google.android.feedback") || installPM.equals("com.android.vending")) {
            // Installed from the Google Play
            return true;
        }
        return false;
    }

    public static boolean isRunning(Context context, String packageName) {
        boolean running = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> procInfos = am.getRunningAppProcesses();
        if (procInfos != null) {
            for (RunningAppProcessInfo procInfo : procInfos) {
                if (procInfo.processName.equals(packageName)) {
                    running = true;
                    break;
                }
            }
        }
        return running;
    }

    private static boolean isSystemPackage(ApplicationInfo pkgInfo) {
        return ((pkgInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM);
    }

    public static AppInfo createAppInfo(PackageManager mPm, ApplicationInfo applicationInfo) {
        AppInfo entry = new AppInfo();
        entry.setPackageName(applicationInfo.packageName);
        CharSequence label = applicationInfo.loadLabel(mPm);
        if (label == null) {
            label = applicationInfo.name == null ? "" : applicationInfo.name;
        }
        entry.setName(label.toString());
        entry.setInstalled(true);
        return entry;
    }

    public static void showInstalledAppDetails(Context context, String packageName) {
        Intent intent = new Intent();
        if (AndroidUtils.isGingerbreadOrHigher()) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts(SCHEME, packageName, null);
            intent.setData(uri);
        } else {
            final String appPkgName;
            if (AndroidUtils.isFroyoOrHigher()) {
                appPkgName = APP_PKG_NAME_22;
            } else {
                appPkgName = APP_PKG_NAME_21;
            }
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName(APP_DETAILS_PACKAGE_NAME, APP_DETAILS_CLASS_NAME);
            intent.putExtra(appPkgName, packageName);
        }
        context.startActivity(intent);
    }

    public static void showPlayGoogleApp(Activity activity, String packageName, boolean isForResult) {
        String url = activity.getString(R.string.play_google, packageName);
        try {
            if (isForResult) {
                activity.startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(url)), 1);
            } else {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        } catch (Exception e) {
            Crouton.makeText(activity, R.string.problem_no_google_play, Style.ALERT).show();
        }
    }

    public static String appInfoToHTML(Context ctx, List<AppInfo> lst, boolean footer, boolean htmlTags) {
        final StringBuilder sb = new StringBuilder();
        if (htmlTags) {
            sb.append("<html><body>");
        }
        if (lst != null) {
            boolean first = true;
            for (AppInfo appInfo : lst) {
                if (first) {
                    first = false;
                } else {
                    sb.append("<br/>\n");
                }
                sb.append(ctx.getString(R.string.play_google_web_html, appInfo.getPackageName(), appInfo.getName()));
            }
        }
        if (footer) {
            sb.append("<br/>\n<br/>\n");
            sb.append(ctx.getString(R.string.share_file_html, ctx.getPackageName()));
        }
        if (htmlTags) {
            sb.append("</body></html>");
        }
        return sb.toString();
    }

    public static String appInfoToText(Context ctx, List<AppInfo> lst, boolean footer) {
        final StringBuilder sb = new StringBuilder();
        if (lst != null) {
            boolean first = true;
            for (AppInfo appInfo : lst) {
                if (first) {
                    first = false;
                } else {
                    sb.append("\n");
                }
                sb.append(ctx.getString(R.string.play_google_web_text, appInfo.getPackageName(), appInfo.getName()));
            }
        }
        if (footer) {
            sb.append("\n\n");
            sb.append(ctx.getString(R.string.share_file_text, ctx.getPackageName()));
        }
        return sb.toString();
    }

    public static String appInfoToForum(Context ctx, List<AppInfo> lst, boolean footer) {
        final StringBuilder sb = new StringBuilder();
        if (lst != null) {
            boolean first = true;
            for (AppInfo appInfo : lst) {
                if (first) {
                    first = false;
                } else {
                    sb.append("\n");
                }
                sb.append(ctx.getString(R.string.play_google_web_forum, appInfo.getPackageName(), appInfo.getName()));
            }
        }
        if (footer) {
            sb.append("\n\n");
            sb.append(ctx.getString(R.string.share_file_forum, ctx.getPackageName()));
        }
        return sb.toString();
    }

}
