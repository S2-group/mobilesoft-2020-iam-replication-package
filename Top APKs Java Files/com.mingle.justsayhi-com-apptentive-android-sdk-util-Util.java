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
import android.content.res.Resources.Theme;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
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
  
  public static void calculateListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int j = 0;
    int i = 0;
    while (i < localListAdapter.getCount())
    {
      localObject = localListAdapter.getView(i, null, paramListView);
      ((View)localObject).measure(0, 0);
      j += ((View)localObject).getMeasuredHeight();
      i += 1;
    }
    Object localObject = paramListView.getLayoutParams();
    paramListView.getDividerHeight();
    localListAdapter.getCount();
    i = ((ViewGroup.LayoutParams)localObject).height;
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
    AccountManager localAccountManager = AccountManager.get(paramContext);
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (Build.VERSION.SDK_INT > 3)
    {
      localObject1 = localObject2;
      if (packageHasPermission(paramContext, "android.permission.GET_ACCOUNTS")) {
        localObject1 = localAccountManager.getAccountsByType("com.google");
      }
    }
    if (localObject1 != null)
    {
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        localArrayList.add(localObject1[i].name);
        i += 1;
      }
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
  
  public static Drawable getCompatDrawable(Context paramContext, int paramInt)
  {
    try
    {
      if (Build.VERSION.SDK_INT < 21) {
        return paramContext.getResources().getDrawable(paramInt);
      }
      paramContext = paramContext.getResources().getDrawable(paramInt, paramContext.getTheme());
      return paramContext;
    }
    catch (Exception paramContext) {}
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
  
  public static Integer getMajorOsVersion()
  {
    try
    {
      String[] arrayOfString = Build.VERSION.RELEASE.split("\\.");
      if ((arrayOfString != null) && (arrayOfString.length != 0))
      {
        int i = Integer.parseInt(arrayOfString[0]);
        return Integer.valueOf(i);
      }
    }
    catch (Exception localException)
    {
      Log.w("Error getting major OS version", localException, new Object[0]);
    }
    return Integer.valueOf(-1);
  }
  
  public static Object getPackageMetaData(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.get(paramString);
      return paramContext;
    }
    catch (Exception paramContext) {}
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
  
  public static StateListDrawable getSelectableImageButtonBackground(int paramInt)
  {
    ColorDrawable localColorDrawable = new ColorDrawable(paramInt);
    StateListDrawable localStateListDrawable = new StateListDrawable();
    localStateListDrawable.addState(new int[] { 16842919 }, localColorDrawable);
    localStateListDrawable.addState(new int[] { 16843518 }, localColorDrawable);
    return localStateListDrawable;
  }
  
  public static int getStatusBarHeight(Window paramWindow)
  {
    Rect localRect = new Rect();
    paramWindow.getDecorView().getWindowVisibleDisplayFrame(localRect);
    return localRect.top;
  }
  
  public static int getThemeColor(Context paramContext, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(paramInt, localTypedValue, true)) {
      return localTypedValue.data;
    }
    return 0;
  }
  
  public static int getThemeColorFromAttrOrRes(Context paramContext, int paramInt1, int paramInt2)
  {
    int i = getThemeColor(paramContext, paramInt1);
    paramInt1 = i;
    if (i == 0) {
      paramInt1 = paramContext.getResources().getColor(paramInt2);
    }
    return paramInt1;
  }
  
  private static String getUserEmail(Context paramContext)
  {
    AccountManager localAccountManager = AccountManager.get(paramContext);
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (Build.VERSION.SDK_INT > 3)
    {
      localObject1 = localObject2;
      if (packageHasPermission(paramContext, "android.permission.GET_ACCOUNTS")) {
        localObject1 = localAccountManager.getAccountsByType("com.google");
      }
    }
    if ((localObject1 != null) && (localObject1.length > 0))
    {
      paramContext = localObject1[(localObject1.length - 1)];
      if (paramContext != null) {
        return paramContext.name;
      }
    }
    return null;
  }
  
  public static int getUtcOffset()
  {
    return TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000;
  }
  
  public static void hideSoftKeyboard(Context paramContext, View paramView)
  {
    if (paramView != null) {
      ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
  }
  
  public static boolean isEmailValid(String paramString)
  {
    return paramString.matches("^[^\\s@]+@[^\\s@]+$");
  }
  
  public static boolean isEmpty(CharSequence paramCharSequence)
  {
    return (paramCharSequence == null) || (paramCharSequence.length() == 0);
  }
  
  public static boolean isNetworkConnectionPresent(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    return (paramContext != null) && (paramContext.getActiveNetworkInfo() != null);
  }
  
  public static int lighter(int paramInt, float paramFloat)
  {
    int i = (int)((Color.red(paramInt) * (1.0F - paramFloat) / 255.0F + paramFloat) * 255.0F);
    int j = (int)((Color.green(paramInt) * (1.0F - paramFloat) / 255.0F + paramFloat) * 255.0F);
    int k = (int)((Color.blue(paramInt) * (1.0F - paramFloat) / 255.0F + paramFloat) * 255.0F);
    return Color.argb(Color.alpha(paramInt), i, j, k);
  }
  
  public static boolean packageHasPermission(Context paramContext, String paramString)
  {
    return packageHasPermission(paramContext, paramContext.getApplicationContext().getPackageName(), paramString);
  }
  
  public static boolean packageHasPermission(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    paramContext = getPermissions(paramContext).iterator();
    for (;;)
    {
      boolean bool1 = bool2;
      Object localObject;
      int j;
      int i;
      if (paramContext.hasNext())
      {
        localObject = (PackageInfo)paramContext.next();
        if ((((PackageInfo)localObject).packageName.equals(paramString1)) && (((PackageInfo)localObject).requestedPermissions != null))
        {
          localObject = ((PackageInfo)localObject).requestedPermissions;
          j = localObject.length;
          i = 0;
        }
      }
      else
      {
        while (i < j)
        {
          if (localObject[i].equals(paramString2))
          {
            bool1 = true;
            return bool1;
          }
          i += 1;
        }
      }
    }
  }
  
  public static Integer parseCacheControlHeader(String paramString)
  {
    if (paramString != null)
    {
      paramString = paramString.substring(paramString.indexOf("[") + 1, paramString.lastIndexOf("]")).split(",");
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
  
  /* Error */
  public static Date parseIso8601Date(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 512	java/lang/String:trim	()Ljava/lang/String;
    //   4: ldc_w 524
    //   7: ldc_w 526
    //   10: invokevirtual 530	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   13: ldc_w 532
    //   16: ldc_w 534
    //   19: invokevirtual 530	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   22: astore 4
    //   24: aload 4
    //   26: astore_3
    //   27: aload 4
    //   29: aload 4
    //   31: invokevirtual 296	java/lang/String:length	()I
    //   34: iconst_3
    //   35: isub
    //   36: invokevirtual 538	java/lang/String:charAt	(I)C
    //   39: bipush 58
    //   41: if_icmpne +44 -> 85
    //   44: aload 4
    //   46: ldc_w 540
    //   49: invokevirtual 507	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   52: istore_1
    //   53: new 542	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 543	java/lang/StringBuilder:<init>	()V
    //   60: aload 4
    //   62: iconst_0
    //   63: iload_1
    //   64: invokevirtual 300	java/lang/String:substring	(II)Ljava/lang/String;
    //   67: invokevirtual 547	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload 4
    //   72: iload_1
    //   73: iconst_1
    //   74: iadd
    //   75: invokevirtual 550	java/lang/String:substring	(I)Ljava/lang/String;
    //   78: invokevirtual 547	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 551	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore_3
    //   85: aload_3
    //   86: bipush 46
    //   88: invokevirtual 553	java/lang/String:lastIndexOf	(I)I
    //   91: istore_2
    //   92: aload_3
    //   93: bipush 43
    //   95: invokevirtual 553	java/lang/String:lastIndexOf	(I)I
    //   98: iconst_m1
    //   99: if_icmpeq +124 -> 223
    //   102: aload_3
    //   103: bipush 43
    //   105: invokevirtual 553	java/lang/String:lastIndexOf	(I)I
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
    //   122: invokevirtual 300	java/lang/String:substring	(II)Ljava/lang/String;
    //   125: astore 4
    //   127: aload_3
    //   128: iload_2
    //   129: iconst_1
    //   130: iadd
    //   131: iload_1
    //   132: invokevirtual 300	java/lang/String:substring	(II)Ljava/lang/String;
    //   135: astore 5
    //   137: aload_3
    //   138: iload_1
    //   139: invokevirtual 550	java/lang/String:substring	(I)Ljava/lang/String;
    //   142: astore_3
    //   143: ldc_w 555
    //   146: iconst_1
    //   147: anewarray 4	java/lang/Object
    //   150: dup
    //   151: iconst_0
    //   152: aload 5
    //   154: aastore
    //   155: invokestatic 558	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   158: ldc_w 534
    //   161: ldc_w 560
    //   164: invokevirtual 530	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   167: astore 5
    //   169: new 542	java/lang/StringBuilder
    //   172: dup
    //   173: invokespecial 543	java/lang/StringBuilder:<init>	()V
    //   176: aload 4
    //   178: invokevirtual 547	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: aload 5
    //   183: invokevirtual 547	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: aload_3
    //   187: invokevirtual 547	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 551	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: astore 4
    //   195: aload 4
    //   197: ldc_w 562
    //   200: invokevirtual 565	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   203: ifeq +67 -> 270
    //   206: new 70	java/text/SimpleDateFormat
    //   209: dup
    //   210: ldc 11
    //   212: invokespecial 73	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   215: aload 4
    //   217: invokevirtual 568	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   220: astore_0
    //   221: aload_0
    //   222: areturn
    //   223: aload_3
    //   224: bipush 45
    //   226: invokevirtual 553	java/lang/String:lastIndexOf	(I)I
    //   229: istore_1
    //   230: goto -121 -> 109
    //   233: astore_3
    //   234: new 542	java/lang/StringBuilder
    //   237: dup
    //   238: invokespecial 543	java/lang/StringBuilder:<init>	()V
    //   241: ldc_w 570
    //   244: invokevirtual 547	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: aload_0
    //   248: invokevirtual 547	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: invokevirtual 551	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   254: aload_3
    //   255: iconst_0
    //   256: anewarray 4	java/lang/Object
    //   259: invokestatic 206	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   262: new 75	java/util/Date
    //   265: dup
    //   266: invokespecial 571	java/util/Date:<init>	()V
    //   269: areturn
    //   270: new 70	java/text/SimpleDateFormat
    //   273: dup
    //   274: ldc 8
    //   276: invokespecial 73	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   279: aload 4
    //   281: invokevirtual 568	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   284: astore_0
    //   285: aload_0
    //   286: areturn
    //   287: astore_0
    //   288: new 542	java/lang/StringBuilder
    //   291: dup
    //   292: invokespecial 543	java/lang/StringBuilder:<init>	()V
    //   295: ldc_w 573
    //   298: invokevirtual 547	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: aload 4
    //   303: invokevirtual 547	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: invokevirtual 551	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: aload_0
    //   310: iconst_0
    //   311: anewarray 4	java/lang/Object
    //   314: invokestatic 206	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   317: aconst_null
    //   318: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	319	0	paramString	String
    //   52	178	1	i	int
    //   91	40	2	j	int
    //   26	198	3	localObject1	Object
    //   233	22	3	localException	Exception
    //   22	280	4	localObject2	Object
    //   135	47	5	str	String
    // Exception table:
    //   from	to	target	type
    //   27	85	233	java/lang/Exception
    //   85	109	233	java/lang/Exception
    //   117	195	233	java/lang/Exception
    //   223	230	233	java/lang/Exception
    //   195	221	287	java/text/ParseException
    //   270	285	287	java/text/ParseException
  }
  
  public static Integer parseWebColorAsAndroidColor(String paramString)
  {
    if (paramString.length() == 9) {}
    for (boolean bool = true;; bool = false) {
      try
      {
        Integer localInteger = Integer.valueOf(Color.parseColor(paramString));
        paramString = localInteger;
        if (Boolean.valueOf(bool).booleanValue())
        {
          int i = localInteger.intValue();
          int j = localInteger.intValue();
          paramString = Integer.valueOf(i >>> 8 | (j & 0xFF) << 24);
        }
        return paramString;
      }
      catch (IllegalArgumentException paramString) {}
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
  
  /* Error */
  public static String readStringFromInputStream(java.io.InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 542	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 543	java/lang/StringBuilder:<init>	()V
    //   12: astore 5
    //   14: sipush 8196
    //   17: newarray char
    //   19: astore 6
    //   21: new 612	java/io/InputStreamReader
    //   24: dup
    //   25: aload_0
    //   26: aload_1
    //   27: invokespecial 615	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   30: astore_0
    //   31: aload_0
    //   32: aload 6
    //   34: iconst_0
    //   35: sipush 8196
    //   38: invokevirtual 621	java/io/Reader:read	([CII)I
    //   41: istore_2
    //   42: iload_2
    //   43: ifge +13 -> 56
    //   46: aload_0
    //   47: invokestatic 623	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   50: aload 5
    //   52: invokevirtual 551	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: areturn
    //   56: aload 5
    //   58: aload 6
    //   60: iconst_0
    //   61: iload_2
    //   62: invokevirtual 626	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   65: pop
    //   66: goto -35 -> 31
    //   69: astore_1
    //   70: aload_0
    //   71: invokestatic 623	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   74: goto -24 -> 50
    //   77: astore_1
    //   78: aload_3
    //   79: astore_0
    //   80: aload_0
    //   81: invokestatic 623	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   84: aload_1
    //   85: athrow
    //   86: astore_1
    //   87: goto -7 -> 80
    //   90: astore_0
    //   91: aload 4
    //   93: astore_0
    //   94: goto -24 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	paramInputStream	java.io.InputStream
    //   0	97	1	paramString	String
    //   41	21	2	i	int
    //   1	78	3	localObject1	Object
    //   3	89	4	localObject2	Object
    //   12	45	5	localStringBuilder	StringBuilder
    //   19	40	6	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   31	42	69	java/lang/Exception
    //   56	66	69	java/lang/Exception
    //   21	31	77	finally
    //   31	42	86	finally
    //   56	66	86	finally
    //   21	31	90	java/lang/Exception
  }
  
  public static void setBackground(View paramView, int paramInt)
  {
    setBackground(paramView, getCompatDrawable(paramView.getContext(), paramInt));
  }
  
  public static void setBackground(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      paramView.setBackgroundDrawable(paramDrawable);
      return;
    }
    paramView.setBackground(paramDrawable);
  }
  
  public static void showSoftKeyboard(Activity paramActivity, View paramView)
  {
    if (paramActivity.getCurrentFocus() != null) {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).showSoftInput(paramView, 0);
    }
  }
  
  public static String stackTraceAsString(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
    return localStringWriter.toString();
  }
}
