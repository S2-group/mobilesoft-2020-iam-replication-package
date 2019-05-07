package com.apptentive.android.sdk.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.apptentive.android.sdk.Log;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public class Util
{
  public static final String PSEUDO_ISO8601_DATE_FORMAT = "yyyy-MM-dd HH:mm:ssZ";
  public static final String PSEUDO_ISO8601_DATE_FORMAT_MILLIS = "yyyy-MM-dd HH:mm:ss.SSSZ";
  
  public Util() {}
  
  public static double currentTimeSeconds()
  {
    return System.currentTimeMillis() / 1000.0D;
  }
  
  public static String dateToIso8601String(long paramLong)
  {
    return dateToString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ"), new Date(paramLong));
  }
  
  public static String dateToString(DateFormat paramDateFormat, Date paramDate)
  {
    return paramDateFormat.format(paramDate);
  }
  
  public static int dipsToPixels(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return Math.round(paramInt * f);
  }
  
  public static float dipsToPixelsFloat(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return paramInt * f;
  }
  
  public static void ensureClosed(Closeable paramCloseable)
  {
    if (paramCloseable != null) {
      try
      {
        paramCloseable.close();
        return;
      }
      catch (IOException paramCloseable) {}
    }
  }
  
  private static Account getAccount(AccountManager paramAccountManager)
  {
    Object localObject = null;
    try
    {
      Account[] arrayOfAccount = paramAccountManager.getAccountsByType("com.google");
      paramAccountManager = localObject;
      if (arrayOfAccount.length > 0) {
        paramAccountManager = arrayOfAccount[(arrayOfAccount.length - 1)];
      }
      return paramAccountManager;
    }
    catch (VerifyError paramAccountManager) {}
    return null;
  }
  
  public static String[] getAllUserAccountEmailAddresses(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if (packageHasPermission(paramContext, "android.permission.GET_ACCOUNTS"))
    {
      paramContext = AccountManager.get(paramContext);
      try
      {
        paramContext = paramContext.getAccountsByType("com.google");
        int j = paramContext.length;
        int i = 0;
        while (i < j)
        {
          localArrayList.add(paramContext[i].name);
          i += 1;
        }
      }
      catch (VerifyError paramContext) {}
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.e("Error getting app version code.", paramContext, new Object[0]);
    }
    return -1;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.e("Error getting app version name.", paramContext, new Object[0]);
    }
    return null;
  }
  
  private static String getEmail(Context paramContext)
  {
    paramContext = getAccount(AccountManager.get(paramContext));
    if (paramContext == null) {
      return null;
    }
    return paramContext.name;
  }
  
  public static String getInstallerPackageName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstallerPackageName(paramContext.getPackageName());
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static Object getPackageMetaData(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.get(paramString);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static boolean getPackageMetaDataBoolean(Context paramContext, String paramString)
  {
    try
    {
      boolean bool = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getBoolean(paramString, false);
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static String getPackageMetaDataSingleQuotedString(Context paramContext, String paramString)
  {
    paramContext = getPackageMetaData(paramContext, paramString);
    if (paramContext == null) {
      return null;
    }
    paramString = paramContext.toString();
    paramContext = paramString;
    if (paramString.endsWith("'")) {
      paramContext = paramString.substring(0, paramString.length() - 1);
    }
    paramString = paramContext;
    if (paramContext.startsWith("'")) {
      paramString = paramContext.substring(1, paramContext.length());
    }
    return paramString;
  }
  
  private static List<PackageInfo> getPermissions(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(4096);
  }
  
  public static Point getScreenSize(Context paramContext)
  {
    Point localPoint = new Point();
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    localPoint.set(paramContext.getWidth(), paramContext.getHeight());
    return localPoint;
  }
  
  public static int getStatusBarHeight(Window paramWindow)
  {
    Rect localRect = new Rect();
    paramWindow.getDecorView().getWindowVisibleDisplayFrame(localRect);
    return localRect.top;
  }
  
  public static String getUserEmail(Context paramContext)
  {
    if (packageHasPermission(paramContext, "android.permission.GET_ACCOUNTS"))
    {
      paramContext = getEmail(paramContext);
      if (paramContext != null) {
        return paramContext;
      }
    }
    return null;
  }
  
  public static int getUtcOffset()
  {
    return TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000;
  }
  
  public static void hideSoftKeyboard(Activity paramActivity, View paramView)
  {
    if (paramView != null) {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
  }
  
  public static boolean isEmailValid(String paramString)
  {
    return paramString.matches("^[^\\s@]+@[^\\s@]+$");
  }
  
  public static boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  public static boolean isNetworkConnectionPresent(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    return (paramContext != null) && (paramContext.getActiveNetworkInfo() != null);
  }
  
  public static boolean packageHasPermission(Context paramContext, String paramString)
  {
    return packageHasPermission(paramContext, paramContext.getApplicationContext().getPackageName(), paramString);
  }
  
  public static boolean packageHasPermission(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = getPermissions(paramContext).iterator();
    while (paramContext.hasNext())
    {
      Object localObject = (PackageInfo)paramContext.next();
      if ((((PackageInfo)localObject).packageName.equals(paramString1)) && (((PackageInfo)localObject).requestedPermissions != null))
      {
        localObject = ((PackageInfo)localObject).requestedPermissions;
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          if (localObject[i].equals(paramString2)) {
            return true;
          }
          i += 1;
        }
      }
    }
    return false;
  }
  
  public static Integer parseCacheControlHeader(String paramString)
  {
    if (paramString != null)
    {
      paramString = paramString.split(",");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramString[i].trim();
        if (((String)localObject).startsWith("max-age="))
        {
          localObject = ((String)localObject).split("=");
          if (localObject.length == 2)
          {
            localObject = localObject[1];
            try
            {
              int k = Integer.parseInt((String)localObject);
              return Integer.valueOf(k);
            }
            catch (NumberFormatException localNumberFormatException)
            {
              Log.e("Error parsing cache expiration as number: %s", localNumberFormatException, new Object[] { localObject });
            }
          }
        }
        i += 1;
      }
    }
    return null;
  }
  
  public static Date parseIso8601Date(String paramString)
  {
    Object localObject2 = paramString.trim().replace("Z", "+00:00").replace("T", " ");
    Object localObject1 = localObject2;
    try
    {
      int i;
      if (((String)localObject2).charAt(((String)localObject2).length() - 3) == ':')
      {
        i = ((String)localObject2).lastIndexOf(":");
        localObject1 = ((String)localObject2).substring(0, i) + ((String)localObject2).substring(i + 1);
      }
      int j = ((String)localObject1).lastIndexOf('.');
      if (((String)localObject1).lastIndexOf('+') != -1) {
        i = ((String)localObject1).lastIndexOf('+');
      } else {
        i = ((String)localObject1).lastIndexOf('-');
      }
      localObject2 = localObject1;
      if (j != -1)
      {
        localObject2 = ((String)localObject1).substring(0, j + 1);
        String str = ((String)localObject1).substring(j + 1, i);
        localObject1 = ((String)localObject1).substring(i);
        str = String.format("%-3s", new Object[] { str }).replace(" ", "0");
        localObject2 = (String)localObject2 + str + (String)localObject1;
      }
    }
    catch (Exception localException)
    {
      Log.e("Error parsing date: " + paramString, localException, new Object[0]);
      return new Date();
    }
    try
    {
      if (((String)localObject2).contains("."))
      {
        paramString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ").parse((String)localObject2);
        return paramString;
      }
      paramString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ").parse((String)localObject2);
      return paramString;
    }
    catch (ParseException paramString)
    {
      Log.e("Exception parsing date: " + (String)localObject2, paramString, new Object[0]);
    }
    return null;
  }
  
  public static int pixelsToDips(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return Math.round(paramInt / f);
  }
  
  public static void printDebugInfo(Context paramContext)
  {
    Point localPoint = getScreenSize(paramContext);
    Log.e("Screen size: PX=%dx%d DP=%dx%d", new Object[] { Integer.valueOf(localPoint.x), Integer.valueOf(localPoint.y), Integer.valueOf(pixelsToDips(paramContext, localPoint.x)), Integer.valueOf(pixelsToDips(paramContext, localPoint.y)) });
  }
  
  public static String secondsToDisplayString(String paramString, Double paramDouble)
  {
    return dateToString(new SimpleDateFormat(paramString), new Date(Math.round(paramDouble.doubleValue() * 1000.0D))).replace("PM", "pm").replace("AM", "am");
  }
  
  public static String stackTraceAsString(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
    return localStringWriter.toString();
  }
}
