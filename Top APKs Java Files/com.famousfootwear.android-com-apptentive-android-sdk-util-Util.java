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
  
  private static Account a(AccountManager paramAccountManager)
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
  
  private static List<PackageInfo> a(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(4096);
  }
  
  private static String b(Context paramContext)
  {
    paramContext = a(AccountManager.get(paramContext));
    if (paramContext == null) {
      return null;
    }
    return paramContext.name;
  }
  
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
    return Math.round(paramContext.getResources().getDisplayMetrics().density * paramInt);
  }
  
  public static float dipsToPixelsFloat(Context paramContext, int paramInt)
  {
    return paramContext.getResources().getDisplayMetrics().density * paramInt;
  }
  
  public static void ensureClosed(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  public static String[] getAllUserAccountEmailAddresses(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if (packageHasPermission(paramContext, "android.permission.GET_ACCOUNTS")) {
      paramContext = AccountManager.get(paramContext);
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getAccountsByType("com.google");
        int j = paramContext.length;
        i = 0;
        if (i < j) {
          continue;
        }
      }
      catch (VerifyError paramContext)
      {
        int i;
        continue;
      }
      return (String[])localArrayList.toArray(new String[localArrayList.size()]);
      localArrayList.add(paramContext[i].name);
      i += 1;
    }
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
      paramString = null;
    }
    do
    {
      return paramString;
      paramString = paramContext.toString();
      paramContext = paramString;
      if (paramString.endsWith("'")) {
        paramContext = paramString.substring(0, paramString.length() - 1);
      }
      paramString = paramContext;
    } while (!paramContext.startsWith("'"));
    return paramContext.substring(1, paramContext.length());
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
      paramContext = b(paramContext);
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
    paramContext = a(paramContext).iterator();
    for (;;)
    {
      if (!paramContext.hasNext()) {
        return false;
      }
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
  }
  
  public static Integer parseCacheControlHeader(String paramString)
  {
    int j;
    int i;
    if (paramString != null)
    {
      paramString = paramString.split(",");
      j = paramString.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {
        return null;
      }
      Object localObject = paramString[i].trim();
      if (((String)localObject).startsWith("max-age="))
      {
        localObject = ((String)localObject).split("=");
        if (localObject.length == 2) {
          localObject = localObject[1];
        }
      }
      try
      {
        int k = Integer.parseInt((String)localObject);
        return Integer.valueOf(k);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Throwable localThrowable;
        for (;;) {}
      }
      Log.e("Error parsing cache expiration as number: %s", localThrowable, new Object[] { localObject });
      i += 1;
    }
  }
  
  /* Error */
  public static Date parseIso8601Date(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 376	java/lang/String:trim	()Ljava/lang/String;
    //   4: ldc_w 398
    //   7: ldc_w 400
    //   10: invokevirtual 404	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   13: ldc_w 406
    //   16: ldc_w 408
    //   19: invokevirtual 404	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   22: astore 4
    //   24: aload 4
    //   26: astore_3
    //   27: aload 4
    //   29: aload 4
    //   31: invokevirtual 228	java/lang/String:length	()I
    //   34: iconst_3
    //   35: isub
    //   36: invokevirtual 412	java/lang/String:charAt	(I)C
    //   39: bipush 58
    //   41: if_icmpne +44 -> 85
    //   44: aload 4
    //   46: ldc_w 414
    //   49: invokevirtual 417	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   52: istore_1
    //   53: new 419	java/lang/StringBuilder
    //   56: dup
    //   57: aload 4
    //   59: iconst_0
    //   60: iload_1
    //   61: invokevirtual 232	java/lang/String:substring	(II)Ljava/lang/String;
    //   64: invokestatic 422	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   67: invokespecial 423	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   70: aload 4
    //   72: iload_1
    //   73: iconst_1
    //   74: iadd
    //   75: invokevirtual 426	java/lang/String:substring	(I)Ljava/lang/String;
    //   78: invokevirtual 430	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 431	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore_3
    //   85: aload_3
    //   86: bipush 46
    //   88: invokevirtual 434	java/lang/String:lastIndexOf	(I)I
    //   91: istore_2
    //   92: aload_3
    //   93: bipush 43
    //   95: invokevirtual 434	java/lang/String:lastIndexOf	(I)I
    //   98: iconst_m1
    //   99: if_icmpeq +120 -> 219
    //   102: aload_3
    //   103: bipush 43
    //   105: invokevirtual 434	java/lang/String:lastIndexOf	(I)I
    //   108: istore_1
    //   109: iload_2
    //   110: iconst_m1
    //   111: if_icmpeq +196 -> 307
    //   114: aload_3
    //   115: iconst_0
    //   116: iload_2
    //   117: iconst_1
    //   118: iadd
    //   119: invokevirtual 232	java/lang/String:substring	(II)Ljava/lang/String;
    //   122: astore 4
    //   124: aload_3
    //   125: iload_2
    //   126: iconst_1
    //   127: iadd
    //   128: iload_1
    //   129: invokevirtual 232	java/lang/String:substring	(II)Ljava/lang/String;
    //   132: astore 5
    //   134: aload_3
    //   135: iload_1
    //   136: invokevirtual 426	java/lang/String:substring	(I)Ljava/lang/String;
    //   139: astore_3
    //   140: ldc_w 436
    //   143: iconst_1
    //   144: anewarray 4	java/lang/Object
    //   147: dup
    //   148: iconst_0
    //   149: aload 5
    //   151: aastore
    //   152: invokestatic 439	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   155: ldc_w 408
    //   158: ldc_w 441
    //   161: invokevirtual 404	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   164: astore 5
    //   166: new 419	java/lang/StringBuilder
    //   169: dup
    //   170: aload 4
    //   172: invokestatic 422	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   175: invokespecial 423	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   178: aload 5
    //   180: invokevirtual 430	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: aload_3
    //   184: invokevirtual 430	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: invokevirtual 431	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   190: astore_3
    //   191: aload_3
    //   192: astore_0
    //   193: aload_0
    //   194: ldc_w 443
    //   197: invokevirtual 447	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   200: ifeq +63 -> 263
    //   203: new 70	java/text/SimpleDateFormat
    //   206: dup
    //   207: ldc 11
    //   209: invokespecial 73	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   212: aload_0
    //   213: invokevirtual 450	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   216: astore_3
    //   217: aload_3
    //   218: areturn
    //   219: aload_3
    //   220: bipush 45
    //   222: invokevirtual 434	java/lang/String:lastIndexOf	(I)I
    //   225: istore_1
    //   226: goto -117 -> 109
    //   229: astore_3
    //   230: new 419	java/lang/StringBuilder
    //   233: dup
    //   234: ldc_w 452
    //   237: invokespecial 423	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   240: aload_0
    //   241: invokevirtual 430	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: invokevirtual 431	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: aload_3
    //   248: iconst_0
    //   249: anewarray 4	java/lang/Object
    //   252: invokestatic 178	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   255: new 75	java/util/Date
    //   258: dup
    //   259: invokespecial 453	java/util/Date:<init>	()V
    //   262: areturn
    //   263: new 70	java/text/SimpleDateFormat
    //   266: dup
    //   267: ldc 8
    //   269: invokespecial 73	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   272: aload_0
    //   273: invokevirtual 450	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   276: astore_3
    //   277: aload_3
    //   278: areturn
    //   279: astore_3
    //   280: new 419	java/lang/StringBuilder
    //   283: dup
    //   284: ldc_w 455
    //   287: invokespecial 423	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   290: aload_0
    //   291: invokevirtual 430	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: invokevirtual 431	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   297: aload_3
    //   298: iconst_0
    //   299: anewarray 4	java/lang/Object
    //   302: invokestatic 178	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   305: aconst_null
    //   306: areturn
    //   307: aload_3
    //   308: astore_0
    //   309: goto -116 -> 193
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	312	0	paramString	String
    //   52	174	1	i	int
    //   91	37	2	j	int
    //   26	194	3	localObject	Object
    //   229	19	3	localException	Exception
    //   276	2	3	localDate	Date
    //   279	29	3	localParseException	java.text.ParseException
    //   22	149	4	str1	String
    //   132	47	5	str2	String
    // Exception table:
    //   from	to	target	type
    //   27	85	229	java/lang/Exception
    //   85	109	229	java/lang/Exception
    //   114	191	229	java/lang/Exception
    //   219	226	229	java/lang/Exception
    //   193	217	279	java/text/ParseException
    //   263	277	279	java/text/ParseException
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
