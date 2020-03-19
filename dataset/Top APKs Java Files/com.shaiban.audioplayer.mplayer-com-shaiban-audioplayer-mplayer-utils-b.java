package com.shaiban.audioplayer.mplayer.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.widget.Toast;
import com.shaiban.audioplayer.mplayer.h.f;
import java.util.Iterator;
import java.util.List;

public class b
{
  public static String a(Context paramContext, int paramInt)
  {
    if (paramInt == 1) {}
    for (paramInt = 2131690065;; paramInt = 2131690066) {
      return paramContext.getString(paramInt);
    }
  }
  
  public static void a(Activity paramActivity)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.putExtra("android.intent.extra.SUBJECT", "Audio Beats Feedback");
    localIntent.putExtra("android.intent.extra.TEXT", b(paramActivity));
    localIntent.setData(Uri.parse("mailto:audiobeatsapp@gmail.com"));
    try
    {
      paramActivity.startActivity(Intent.createChooser(localIntent, "Send mail..."));
      f.a(paramActivity).a("Report Developer Mail");
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    Toast.makeText(paramActivity, "There are no email clients installed.", 0).show();
    f.a(paramActivity).a("Report Developer Mail Failed");
  }
  
  public static void a(Context paramContext)
  {
    try
    {
      ((AudioManager)paramContext.getSystemService("audio")).adjustStreamVolume(3, 0, 1);
      return;
    }
    catch (Throwable paramContext)
    {
      a.b(paramContext);
    }
  }
  
  public static boolean a()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
  
  public static boolean a(Activity paramActivity, String paramString)
  {
    paramActivity = paramActivity.getPackageManager().getInstalledApplications(0).iterator();
    while (paramActivity.hasNext()) {
      if (((ApplicationInfo)paramActivity.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    return (paramString != null) && (!paramString.isEmpty()) && (!"null".equals(paramString));
  }
  
  public static String b(Activity paramActivity)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\n\nVersion code: ");
    localStringBuilder2.append(String.valueOf(332));
    localStringBuilder2.append("\n");
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("Premium : ");
    localStringBuilder2.append(i.a(paramActivity).j());
    localStringBuilder2.append("\n");
    localStringBuilder1.append(localStringBuilder2.toString());
    paramActivity = new StringBuilder();
    paramActivity.append("OS: ");
    paramActivity.append(String.valueOf(Build.VERSION.SDK_INT));
    paramActivity.append("\n");
    localStringBuilder1.append(paramActivity.toString());
    paramActivity = new StringBuilder();
    paramActivity.append("Device: ");
    paramActivity.append(String.valueOf(Build.DEVICE));
    paramActivity.append("\n");
    localStringBuilder1.append(paramActivity.toString());
    paramActivity = new StringBuilder();
    paramActivity.append("Model:  ");
    paramActivity.append(String.valueOf(Build.MODEL));
    paramActivity.append("\n");
    localStringBuilder1.append(paramActivity.toString());
    paramActivity = new StringBuilder();
    paramActivity.append("Manufacturer: ");
    paramActivity.append(String.valueOf(Build.MANUFACTURER));
    paramActivity.append("\n\n\n\n");
    localStringBuilder1.append(paramActivity.toString());
    return localStringBuilder1.toString();
  }
  
  public static String b(Context paramContext, int paramInt)
  {
    if (paramInt == 1) {}
    for (paramInt = 2131689919;; paramInt = 2131689926) {
      return paramContext.getString(paramInt);
    }
  }
  
  public static void b(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.SUBJECT", "Audio Beats Player");
      String str = paramContext.getString(2131689575);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(" https://goo.gl/cdmLLz");
      localIntent.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
      paramContext.startActivity(Intent.createChooser(localIntent, "choose one"));
      f.a(paramContext).a("Share App");
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean b()
  {
    return false;
  }
  
  public static int c(Context paramContext, int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getResources().getDisplayMetrics());
  }
  
  public static void c(Context paramContext)
  {
    f.a(paramContext).a("Audio Beats Classic");
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=mp3player.audioplayer.audiobeats"));
    localIntent.addFlags(1476395008);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    localIntent = new Intent("android.intent.action.VIEW", Uri.parse("www.audiobeats.org"));
    break label75;
    localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=mp3player.audioplayer.audiobeats"));
    label75:
    paramContext.startActivity(localIntent);
  }
  
  public static boolean d(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnectedOrConnecting());
  }
}
