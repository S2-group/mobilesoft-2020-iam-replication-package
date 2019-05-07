package com.apptentive.android.sdk.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.apptentive.android.sdk.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Util
{
  public static final String PSEUDO_ISO8601_DATE_FORMAT = "yyyy-MM-dd HH:mm:ssZ";
  public static final String PSEUDO_ISO8601_DATE_FORMAT_MILLIS = "yyyy-MM-dd HH:mm:ss.SSSZ";
  
  public Util() {}
  
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
  
  public static void ensureClosed(InputStream paramInputStream)
  {
    if (paramInputStream != null) {}
    try
    {
      paramInputStream.close();
      return;
    }
    catch (IOException paramInputStream) {}
  }
  
  public static void ensureClosed(OutputStream paramOutputStream)
  {
    if (paramOutputStream != null) {}
    try
    {
      paramOutputStream.close();
      return;
    }
    catch (IOException paramOutputStream) {}
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
  
  private static String getEmail(Context paramContext)
  {
    paramContext = getAccount(AccountManager.get(paramContext));
    if (paramContext == null) {
      return null;
    }
    return paramContext.name;
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
  
  /* Error */
  public static Date parseIso8601Date(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 273	java/lang/String:trim	()Ljava/lang/String;
    //   4: ldc_w 306
    //   7: ldc_w 308
    //   10: invokevirtual 312	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   13: ldc_w 314
    //   16: ldc_w 316
    //   19: invokevirtual 312	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   22: astore 4
    //   24: aload 4
    //   26: astore_3
    //   27: aload 4
    //   29: aload 4
    //   31: invokevirtual 208	java/lang/String:length	()I
    //   34: iconst_3
    //   35: isub
    //   36: invokevirtual 320	java/lang/String:charAt	(I)C
    //   39: bipush 58
    //   41: if_icmpne +44 -> 85
    //   44: aload 4
    //   46: ldc_w 322
    //   49: invokevirtual 325	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   52: istore_1
    //   53: new 327	java/lang/StringBuilder
    //   56: dup
    //   57: aload 4
    //   59: iconst_0
    //   60: iload_1
    //   61: invokevirtual 331	java/lang/String:substring	(II)Ljava/lang/String;
    //   64: invokestatic 334	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   67: invokespecial 335	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   70: aload 4
    //   72: iload_1
    //   73: iconst_1
    //   74: iadd
    //   75: invokevirtual 338	java/lang/String:substring	(I)Ljava/lang/String;
    //   78: invokevirtual 342	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 345	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore_3
    //   85: aload_3
    //   86: bipush 46
    //   88: invokevirtual 348	java/lang/String:lastIndexOf	(I)I
    //   91: istore_2
    //   92: aload_3
    //   93: bipush 43
    //   95: invokevirtual 348	java/lang/String:lastIndexOf	(I)I
    //   98: iconst_m1
    //   99: if_icmpeq +124 -> 223
    //   102: aload_3
    //   103: bipush 43
    //   105: invokevirtual 348	java/lang/String:lastIndexOf	(I)I
    //   108: istore_1
    //   109: aload_3
    //   110: astore 4
    //   112: iload_2
    //   113: iconst_m1
    //   114: if_icmpeq +81 -> 195
    //   117: aload_3
    //   118: iconst_0
    //   119: iload_2
    //   120: iconst_1
    //   121: iadd
    //   122: invokevirtual 331	java/lang/String:substring	(II)Ljava/lang/String;
    //   125: astore 4
    //   127: aload_3
    //   128: iload_2
    //   129: iconst_1
    //   130: iadd
    //   131: iload_1
    //   132: invokevirtual 331	java/lang/String:substring	(II)Ljava/lang/String;
    //   135: astore 5
    //   137: aload_3
    //   138: iload_1
    //   139: invokevirtual 338	java/lang/String:substring	(I)Ljava/lang/String;
    //   142: astore_3
    //   143: ldc_w 350
    //   146: iconst_1
    //   147: anewarray 4	java/lang/Object
    //   150: dup
    //   151: iconst_0
    //   152: aload 5
    //   154: aastore
    //   155: invokestatic 353	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   158: ldc_w 316
    //   161: ldc_w 355
    //   164: invokevirtual 312	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   167: astore 5
    //   169: new 327	java/lang/StringBuilder
    //   172: dup
    //   173: aload 4
    //   175: invokestatic 334	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   178: invokespecial 335	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   181: aload 5
    //   183: invokevirtual 342	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: aload_3
    //   187: invokevirtual 342	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 345	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: astore 4
    //   195: aload 4
    //   197: ldc_w 357
    //   200: invokevirtual 361	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   203: ifeq +64 -> 267
    //   206: new 20	java/text/SimpleDateFormat
    //   209: dup
    //   210: ldc 11
    //   212: invokespecial 23	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   215: aload 4
    //   217: invokevirtual 364	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   220: astore_0
    //   221: aload_0
    //   222: areturn
    //   223: aload_3
    //   224: bipush 45
    //   226: invokevirtual 348	java/lang/String:lastIndexOf	(I)I
    //   229: istore_1
    //   230: goto -121 -> 109
    //   233: astore_3
    //   234: new 327	java/lang/StringBuilder
    //   237: dup
    //   238: ldc_w 366
    //   241: invokespecial 335	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   244: aload_0
    //   245: invokevirtual 342	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: invokevirtual 345	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   251: aload_3
    //   252: iconst_0
    //   253: anewarray 4	java/lang/Object
    //   256: invokestatic 298	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   259: new 25	java/util/Date
    //   262: dup
    //   263: invokespecial 367	java/util/Date:<init>	()V
    //   266: areturn
    //   267: new 20	java/text/SimpleDateFormat
    //   270: dup
    //   271: ldc 8
    //   273: invokespecial 23	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   276: aload 4
    //   278: invokevirtual 364	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   281: astore_0
    //   282: aload_0
    //   283: areturn
    //   284: astore_0
    //   285: new 327	java/lang/StringBuilder
    //   288: dup
    //   289: ldc_w 369
    //   292: invokespecial 335	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   295: aload 4
    //   297: invokevirtual 342	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: invokevirtual 345	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   303: aload_0
    //   304: iconst_0
    //   305: anewarray 4	java/lang/Object
    //   308: invokestatic 298	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   311: aconst_null
    //   312: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	313	0	paramString	String
    //   52	178	1	i	int
    //   91	40	2	j	int
    //   26	198	3	localObject1	Object
    //   233	19	3	localException	Exception
    //   22	274	4	localObject2	Object
    //   135	47	5	str	String
    // Exception table:
    //   from	to	target	type
    //   27	85	233	java/lang/Exception
    //   85	109	233	java/lang/Exception
    //   117	195	233	java/lang/Exception
    //   223	230	233	java/lang/Exception
    //   195	221	284	java/text/ParseException
    //   267	282	284	java/text/ParseException
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
}
