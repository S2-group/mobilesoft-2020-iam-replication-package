package com.ovuline.ovia.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.ovuline.ovia.R.string;
import com.ovuline.ovia.ui.activity.BaseActivity;
import com.ovuline.ovia.ui.view.OviaSnackbar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class Utils
{
  public static int a()
  {
    Random localRandom = new Random();
    return Color.argb(255, localRandom.nextInt(256), localRandom.nextInt(256), localRandom.nextInt(256));
  }
  
  public static Uri a(View paramView)
  {
    return a(paramView, 0);
  }
  
  public static Uri a(View paramView, int paramInt)
  {
    int i = 0;
    if (paramView == null) {
      return null;
    }
    paramView.setDrawingCacheEnabled(true);
    paramView.buildDrawingCache(true);
    Bitmap localBitmap = paramView.getDrawingCache();
    localBitmap = ThumbnailUtils.extractThumbnail(localBitmap, localBitmap.getWidth() - paramInt, localBitmap.getHeight());
    paramView.setDrawingCacheEnabled(false);
    paramView = Environment.getExternalStoragePublicDirectory("Ovia");
    if (!paramView.exists()) {
      paramView.mkdir();
    }
    File[] arrayOfFile = paramView.listFiles();
    int j = arrayOfFile.length;
    paramInt = i;
    while (paramInt < j)
    {
      arrayOfFile[paramInt].delete();
      paramInt += 1;
    }
    paramView = new File(paramView, System.currentTimeMillis() + ".jpg");
    try
    {
      localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(paramView));
      paramView = Uri.fromFile(paramView);
      return paramView;
    }
    catch (FileNotFoundException paramView)
    {
      paramView.printStackTrace();
    }
    return null;
  }
  
  public static String a(Integer... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = 1;
    int i = 0;
    if (i < paramVarArgs.length)
    {
      if (j == 0) {
        localStringBuilder.append(",");
      }
      for (;;)
      {
        localStringBuilder.append(paramVarArgs[i].toString());
        i += 1;
        break;
        j = 0;
      }
    }
    return localStringBuilder.toString();
  }
  
  public static void a(Activity paramActivity)
  {
    a(paramActivity, paramActivity.getCurrentFocus());
  }
  
  public static void a(Activity paramActivity, View paramView)
  {
    paramActivity = (InputMethodManager)paramActivity.getSystemService("input_method");
    if ((paramView != null) && (paramView.getWindowToken() != null)) {
      paramActivity.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
  }
  
  public static void a(Activity paramActivity, EditText paramEditText)
  {
    ((InputMethodManager)paramActivity.getSystemService("input_method")).showSoftInput(paramEditText, 1);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString));
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + paramString)));
    }
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      String str = paramContext.getSimCountryIso();
      if (!TextUtils.isEmpty(str)) {
        return str.equalsIgnoreCase("us");
      }
      if (paramContext.getPhoneType() != 2)
      {
        paramContext = paramContext.getNetworkCountryIso();
        if (!TextUtils.isEmpty(paramContext))
        {
          boolean bool = paramContext.equalsIgnoreCase("us");
          return bool;
        }
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return false;
      paramString = Uri.parse(paramString);
    } while ((!paramString.getScheme().equals("http")) && (!paramString.getScheme().equals("https")));
    return true;
  }
  
  public static String b(Context paramContext)
  {
    return paramContext.getString(paramContext.getApplicationInfo().labelRes);
  }
  
  public static void b(Context paramContext, String paramString)
  {
    Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(1).iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localApplicationInfo = (ApplicationInfo)localIterator.next();
    } while ((localApplicationInfo == null) || (!localApplicationInfo.packageName.equals(paramString)));
    for (int i = 1;; i = 0)
    {
      if (i != 0) {
        try
        {
          paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          a(paramContext, paramString);
          return;
        }
      }
      a(paramContext, paramString);
      return;
    }
  }
  
  public static boolean b()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static boolean b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Uri.parse(paramString).getScheme().contains("ovia");
  }
  
  public static boolean c()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    if (a(paramString)) {
      if (c(paramString)) {
        a(paramContext, Uri.parse(paramString).getQueryParameter("id"));
      }
    }
    do
    {
      return true;
      return false;
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      try
      {
        if (!(paramContext instanceof Activity)) {
          paramString.addFlags(268435456);
        }
        paramContext.startActivity(paramString);
        return true;
      }
      catch (ActivityNotFoundException paramString) {}
    } while (!(paramContext instanceof BaseActivity));
    OviaSnackbar.a((BaseActivity)paramContext, R.string.cant_perform_action, -1).b();
    return true;
  }
  
  public static boolean c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Uri.parse(paramString).getHost().equals("play.google.com");
  }
  
  public static int d(String paramString)
  {
    paramString = new StringTokenizer(paramString, ",");
    return Color.rgb(Integer.valueOf(paramString.nextToken()).intValue(), Integer.valueOf(paramString.nextToken()).intValue(), Integer.valueOf(paramString.nextToken()).intValue());
  }
  
  public static String e(String paramString)
  {
    return Base64.encodeToString(paramString.getBytes(), 2);
  }
  
  public static boolean f(String paramString)
  {
    try
    {
      Integer.parseInt(paramString);
      return true;
    }
    catch (NumberFormatException paramString)
    {
      return false;
    }
    catch (NullPointerException paramString) {}
    return false;
  }
}
