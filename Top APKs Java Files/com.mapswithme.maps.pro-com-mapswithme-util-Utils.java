package com.mapswithme.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog.Builder;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;
import com.mapswithme.maps.MwmApplication;
import com.mapswithme.maps.activity.CustomNavigateUpListener;
import com.mapswithme.maps.uber.UberLinks;
import com.mapswithme.util.statistics.AlohaHelper;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class Utils
{
  private static final String TAG = "Utils";
  
  private Utils() {}
  
  public static Object[] asObjectArray(Object... paramVarArgs)
  {
    return paramVarArgs;
  }
  
  public static Uri buildMailUri(String paramString1, String paramString2, String paramString3)
  {
    return Uri.parse("mailto:" + Uri.encode(paramString1) + "?subject=" + Uri.encode(paramString2) + "&body=" + Uri.encode(paramString3));
  }
  
  public static void callPhone(@NonNull Context paramContext, @NonNull String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.DIAL");
    localIntent.setData(Uri.parse("tel:" + paramString));
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      AlohaHelper.logException(paramContext);
    }
  }
  
  public static void checkConnection(final Context paramContext, @StringRes final int paramInt, final Proc<Boolean> paramProc)
  {
    if (ConnectionState.isConnected())
    {
      paramProc.invoke(Boolean.valueOf(true));
      return;
    }
    Object local1Holder = new Object()
    {
      boolean accepted;
    };
    new AlertDialog.Builder(paramContext).setMessage(paramInt).setNegativeButton(17039360, null).setPositiveButton(2131231010, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.val$holder.accepted = true;
        Utils.checkConnection(paramContext, paramInt, paramProc);
      }
    }).setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (!this.val$holder.accepted) {
          paramProc.invoke(Boolean.valueOf(false));
        }
      }
    }).show();
  }
  
  public static void checkNotNull(Object paramObject)
  {
    if (paramObject == null) {
      throw new NullPointerException("Argument here must not be NULL");
    }
  }
  
  public static void closeStream(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable)
    {
      Log.e("Utils", "Can't close stream", paramCloseable);
    }
  }
  
  public static void copyTextToClipboard(Context paramContext, String paramString)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("maps.me: " + paramString, paramString));
  }
  
  public static SpannableStringBuilder formatUnitsText(Context paramContext, @DimenRes int paramInt1, @DimenRes int paramInt2, String paramString1, String paramString2)
  {
    paramString2 = new SpannableStringBuilder(paramString1).append(" ").append(paramString2);
    paramString2.setSpan(new AbsoluteSizeSpan(UiUtils.dimen(paramContext, paramInt1), false), 0, paramString1.length(), 33);
    paramString2.setSpan(new AbsoluteSizeSpan(UiUtils.dimen(paramContext, paramInt2), false), paramString1.length(), paramString2.length(), 33);
    return paramString2;
  }
  
  private static String getDeviceModel()
  {
    String str2 = Build.MODEL;
    String str1 = str2;
    if (!str2.startsWith(Build.MANUFACTURER)) {
      str1 = Build.MANUFACTURER + " " + str2;
    }
    return str1;
  }
  
  public static String getInstallationId()
  {
    String str2 = MwmApplication.get().getSharedPreferences("ALOHALYTICS", 0).getString("UNIQUE_ID", null);
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = "";
    }
    return str1;
  }
  
  public static boolean hasAnyGoogleStoreInstalled()
  {
    boolean bool2 = false;
    Iterator localIterator = MwmApplication.get().getPackageManager().getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    do
    {
      bool1 = bool2;
      if (!localIterator.hasNext()) {
        break;
      }
      localPackageInfo = (PackageInfo)localIterator.next();
    } while ((!localPackageInfo.packageName.equals("com.google.market")) && (!localPackageInfo.packageName.equals("com.android.vending")));
    boolean bool1 = true;
    return bool1;
  }
  
  public static boolean isAmazonDevice()
  {
    return "Amazon".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static boolean isInstalledAfter(long paramLong)
  {
    boolean bool = false;
    try
    {
      long l = MwmApplication.get().getPackageManager().getPackageInfo("com.mapswithme.maps.pro", 0).firstInstallTime;
      if (l > paramLong) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return false;
  }
  
  public static boolean isIntentAvailable(Intent paramIntent)
  {
    return MwmApplication.get().getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  public static boolean isIntentSupported(Context paramContext, Intent paramIntent)
  {
    boolean bool = false;
    if (paramContext.getPackageManager().resolveActivity(paramIntent, 0) != null) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isPackageInstalled(String paramString)
  {
    PackageManager localPackageManager = MwmApplication.get().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static boolean isUberInstalled(@NonNull Activity paramActivity)
  {
    try
    {
      paramActivity.getPackageManager().getPackageInfo("com.ubercab", 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramActivity) {}
    return false;
  }
  
  public static void keepScreenOn(boolean paramBoolean, Window paramWindow)
  {
    if (paramBoolean)
    {
      paramWindow.addFlags(128);
      return;
    }
    paramWindow.clearFlags(128);
  }
  
  public static void launchPackage(Activity paramActivity, String paramString)
  {
    paramString = paramActivity.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramString != null) {
      paramActivity.startActivity(paramString);
    }
  }
  
  public static void launchUber(@NonNull Activity paramActivity, @NonNull UberLinks paramUberLinks)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    if (isUberInstalled(paramActivity))
    {
      localIntent.setFlags(268435456);
      localIntent.setData(Uri.parse(paramUberLinks.getDeepLink()));
    }
    for (;;)
    {
      paramActivity.startActivity(localIntent);
      return;
      localIntent.setData(Uri.parse(paramUberLinks.getUniversalLink()));
    }
  }
  
  public static <K, V> String mapPrettyPrint(Map<K, V> paramMap)
  {
    if (paramMap == null) {
      return "[null]";
    }
    if (paramMap.isEmpty()) {
      return "[]";
    }
    Object localObject1 = "";
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      localObject2 = localObject2 + "=" + paramMap.get(localObject2);
      if (((String)localObject1).length() > 0) {
        localObject1 = TextUtils.join(",", new Object[] { localObject1, localObject2 });
      } else {
        localObject1 = localObject2;
      }
    }
    return "[" + (String)localObject1 + "]";
  }
  
  public static void navigateToParent(@NonNull Activity paramActivity)
  {
    if ((paramActivity instanceof CustomNavigateUpListener))
    {
      ((CustomNavigateUpListener)paramActivity).customOnNavigateUp();
      return;
    }
    NavUtils.navigateUpFromSameTask(paramActivity);
  }
  
  public static void navigateToParent(@NonNull Activity paramActivity, @NonNull Bundle paramBundle)
  {
    if ((paramActivity instanceof CustomNavigateUpListener))
    {
      ((CustomNavigateUpListener)paramActivity).customOnNavigateUp();
      return;
    }
    Intent localIntent = NavUtils.getParentActivityIntent(paramActivity);
    localIntent.putExtras(paramBundle);
    NavUtils.navigateUpTo(paramActivity, localIntent);
  }
  
  public static void openAppInMarket(Activity paramActivity, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.addFlags(1207959552);
    if (Build.VERSION.SDK_INT >= 21) {
      paramString.addFlags(524288);
    }
    for (;;)
    {
      try
      {
        paramActivity.startActivity(paramString);
        return;
      }
      catch (ActivityNotFoundException paramActivity)
      {
        AlohaHelper.logException(paramActivity);
      }
      paramString.addFlags(268435456);
    }
  }
  
  public static String saveLogToFile()
  {
    String str = MwmApplication.getSettingsPath() + "log.txt";
    Object localObject1 = new File(str);
    char[] arrayOfChar = null;
    localObject4 = null;
    for (;;)
    {
      try
      {
        localObject1 = new FileWriter((File)localObject1);
      }
      catch (IOException localIOException1)
      {
        int j;
        int i;
        Object localObject3;
        Object localObject2 = localObject4;
        continue;
      }
      try
      {
        ((FileWriter)localObject1).write("Android version: " + Build.VERSION.SDK_INT + "\n");
        ((FileWriter)localObject1).write("Device: " + getDeviceModel() + "\n");
        ((FileWriter)localObject1).write("App version: com.mapswithme.maps.pro 7.1.3-Google\n");
        ((FileWriter)localObject1).write("Installation ID: " + getInstallationId() + "\n");
        ((FileWriter)localObject1).write("Locale : " + Locale.getDefault());
        ((FileWriter)localObject1).write("\nNetworks : ");
        localObject4 = ((ConnectivityManager)MwmApplication.get().getSystemService("connectivity")).getAllNetworkInfo();
        j = localObject4.length;
        i = 0;
        if (i < j)
        {
          ((FileWriter)localObject1).write(localObject4[i].toString());
          i += 1;
        }
        else
        {
          ((FileWriter)localObject1).write("\n\n");
          localObject4 = new InputStreamReader(Runtime.getRuntime().exec("logcat -d -v time").getInputStream());
          try
          {
            arrayOfChar = new char['✐'];
            i = ((InputStreamReader)localObject4).read(arrayOfChar, 0, arrayOfChar.length);
            if (i == -1)
            {
              ((InputStreamReader)localObject4).close();
              ((FileWriter)localObject1).close();
              return str;
            }
            ((FileWriter)localObject1).write(arrayOfChar, 0, i);
            continue;
            closeStream((Closeable)localObject1);
          }
          catch (IOException localIOException2)
          {
            localObject3 = localObject4;
          }
        }
      }
      catch (IOException localIOException3) {}
    }
    closeStream(localObject3);
    return null;
  }
  
  public static void sendSupportMail(Activity paramActivity, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setFlags(268435456);
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "googleplay@maps.me" });
    localIntent.putExtra("android.intent.extra.SUBJECT", "[7.1.3-Google] " + paramString);
    localIntent.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + saveLogToFile()));
    localIntent.putExtra("android.intent.extra.TEXT", "");
    localIntent.setType("message/rfc822");
    try
    {
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramActivity)
    {
      AlohaHelper.logException(paramActivity);
    }
  }
  
  public static void sendTo(@NonNull Context paramContext, @NonNull String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO");
    localIntent.setData(buildMailUri(paramString, "", ""));
    paramContext.startActivity(localIntent);
  }
  
  public static void showFacebookPage(Activity paramActivity)
  {
    try
    {
      paramActivity.getPackageManager().getPackageInfo("com.facebook.katana", 0);
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("fb://profile/111923085594432")));
      return;
    }
    catch (Exception localException)
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.facebook.com/MapsWithMe")));
    }
  }
  
  public static void showTwitterPage(Activity paramActivity)
  {
    paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/MAPS_ME")));
  }
  
  public static void toastShortcut(Context paramContext, int paramInt)
  {
    toastShortcut(paramContext, paramContext.getString(paramInt));
  }
  
  public static void toastShortcut(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 1).show();
  }
  
  public static abstract interface Proc<T>
  {
    public abstract void invoke(@NonNull T paramT);
  }
}
