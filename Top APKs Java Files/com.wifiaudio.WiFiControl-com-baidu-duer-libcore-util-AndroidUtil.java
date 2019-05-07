package com.baidu.duer.libcore.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Process;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndroidUtil
{
  private static String ChineseMobilePattern = "^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$";
  
  public AndroidUtil() {}
  
  private static int adjustFontSize(int paramInt1, int paramInt2)
  {
    if (paramInt1 > paramInt2) {}
    for (;;)
    {
      paramInt1 = paramInt1 * 6 / 320;
      if (paramInt1 >= 15) {
        break;
      }
      return 15;
      paramInt1 = paramInt2;
    }
    return paramInt1;
  }
  
  public static int adjustFontSize(Activity paramActivity)
  {
    paramActivity = DisplayUtil.getDisplayMetrics(paramActivity);
    if (paramActivity != null) {
      return adjustFontSize(paramActivity.widthPixels, paramActivity.heightPixels);
    }
    return adjustFontSize(320, 480);
  }
  
  public static String collectionToString(Collection<String> paramCollection)
  {
    if (paramCollection == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramCollection = paramCollection.iterator();
    int i = 0;
    if (paramCollection.hasNext())
    {
      String str = (String)paramCollection.next();
      if (i != 0) {
        localStringBuilder.append(",");
      }
      for (;;)
      {
        localStringBuilder.append(str);
        break;
        i = 1;
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String encodeStr(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      return paramString1;
    }
    return URLDecoder.decode(paramString1, paramString2);
  }
  
  public static String encodeURL(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      return null;
    }
    paramString1 = paramString1.replaceAll(" ", "%20");
    StringBuilder localStringBuilder1 = new StringBuilder();
    StringBuilder localStringBuilder2 = new StringBuilder();
    int i = 0;
    if (i < paramString1.length())
    {
      char c = paramString1.charAt(i);
      if (c > 'ÿ') {
        localStringBuilder2.append(c);
      }
      for (;;)
      {
        i += 1;
        break;
        if (localStringBuilder2.length() != 0)
        {
          localStringBuilder1.append(URLEncoder.encode(localStringBuilder2.toString(), paramString2));
          localStringBuilder2.delete(0, localStringBuilder2.length());
        }
        localStringBuilder1.append(c);
      }
    }
    return localStringBuilder1.toString();
  }
  
  public static String filterHTML(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (!paramString.equalsIgnoreCase("null"))) {
      return Html.fromHtml(paramString).toString();
    }
    return "";
  }
  
  public static String filterNULL(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (!paramString.equalsIgnoreCase("null"))) {
      return paramString;
    }
    return "";
  }
  
  public static Bitmap getClipBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (paramBitmap != null) {
      try
      {
        int i = paramBitmap.getWidth();
        int j = paramBitmap.getHeight();
        if ((i != 0) && (j != 0) && (paramInt1 != 0))
        {
          if (paramInt2 == 0) {
            return null;
          }
          float f = Float.valueOf(paramInt1).floatValue() / Float.valueOf(i).floatValue();
          if (Float.valueOf(paramInt2).floatValue() / Float.valueOf(j).floatValue() <= f)
          {
            paramInt1 = i * paramInt2 / paramInt1;
            return Bitmap.createBitmap(paramBitmap, 0, (j - paramInt1) / 2, i, paramInt1);
          }
          paramInt1 = paramInt1 * j / paramInt2;
          paramBitmap = Bitmap.createBitmap(paramBitmap, (i - paramInt1) / 2, 0, paramInt1, j);
          return paramBitmap;
        }
      }
      catch (OutOfMemoryError paramBitmap)
      {
        ConsoleLogger.printErrorInfo(TimeUtil.class, paramBitmap.getMessage());
      }
    }
    return null;
  }
  
  public static String getCpuName()
  {
    try
    {
      Object localObject = new BufferedReader(new FileReader("/proc/cpuinfo")).readLine().split(":\\s+", 2);
      int i = 0;
      while (i < localObject.length) {
        i += 1;
      }
      localObject = localObject[1];
      return localObject;
    }
    catch (IOException localIOException)
    {
      return null;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      for (;;) {}
    }
  }
  
  public static String getExtCacheDir(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getExternalCacheDir();
      if (paramContext == null) {
        return null;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
    return paramContext.getAbsolutePath();
  }
  
  public static int getProcessId(Context paramContext, String paramString)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext == null) {
      return -1;
    }
    if (paramString == null) {
      return -1;
    }
    paramContext = paramContext.getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (paramString.equals(localRunningAppProcessInfo.processName)) {
        return localRunningAppProcessInfo.pid;
      }
    }
    return -1;
  }
  
  public static String getProcessName(Context paramContext, int paramInt)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getRunningAppProcesses();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if ((localRunningAppProcessInfo != null) && (localRunningAppProcessInfo.pid == paramInt)) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return null;
  }
  
  public static int getRandomNumber(int paramInt)
  {
    paramInt = Math.abs(paramInt);
    if (paramInt > 0)
    {
      paramInt = (int)Math.pow(10.0D, paramInt - 1);
      double d1 = Math.random();
      double d2 = paramInt;
      return (int)(paramInt + d1 * 9.0D * d2);
    }
    return 0;
  }
  
  public static int getResource(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
  }
  
  public static String getScreenResolution(Context paramContext)
  {
    float[] arrayOfFloat = new float[2];
    arrayOfFloat[0] = paramContext.getResources().getDisplayMetrics().widthPixels;
    arrayOfFloat[1] = paramContext.getResources().getDisplayMetrics().heightPixels;
    paramContext = new StringBuilder();
    paramContext.append(arrayOfFloat[0]).append('x').append(arrayOfFloat[1]);
    return paramContext.toString();
  }
  
  public static String getSign(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(64).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.equals(paramString)) {
        return localPackageInfo.signatures[0].toCharsString();
      }
    }
    return null;
  }
  
  public static String getStringSeparatedByComma(List<String> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        String str = (String)paramList.next();
        if (str != null)
        {
          localStringBuilder.append(str);
          localStringBuilder.append(',');
        }
      }
      localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
      return localStringBuilder.toString();
    }
    return null;
  }
  
  public static String getTopActivity(Activity paramActivity)
  {
    paramActivity = ((ActivityManager)paramActivity.getSystemService("activity")).getRunningTasks(1);
    if (paramActivity != null) {
      return ((ActivityManager.RunningTaskInfo)paramActivity.get(0)).topActivity.toString();
    }
    return null;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    Object localObject2 = null;
    boolean bool = false;
    Object localObject1 = localObject2;
    if (!TextUtils.isEmpty(paramString)) {}
    try
    {
      localObject1 = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (localObject1 != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        localObject1 = localObject2;
      }
    }
  }
  
  public static boolean isExistProcess(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext()) {
      if (paramString.equals(((ActivityManager.RunningAppProcessInfo)paramContext.next()).processName)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isMainProcess(Context paramContext)
  {
    String str = getProcessName(paramContext, Process.myPid());
    return paramContext.getPackageName().equals(str);
  }
  
  public static boolean isMobile(String paramString)
  {
    boolean bool = false;
    if (!TextUtils.isEmpty(paramString)) {
      bool = Pattern.compile(ChineseMobilePattern).matcher(paramString).matches();
    }
    return bool;
  }
  
  public static boolean isNotificationEnabled(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 19) {
      return true;
    }
    Object localObject1 = (AppOpsManager)paramContext.getSystemService("appops");
    Object localObject2 = paramContext.getApplicationInfo();
    paramContext = paramContext.getApplicationContext().getPackageName();
    int i = ((ApplicationInfo)localObject2).uid;
    try
    {
      localObject2 = Class.forName(AppOpsManager.class.getName());
      localObject1 = (Integer)((Class)localObject2).getMethod("checkOpNoThrow", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localObject1, new Object[] { (Integer)((Class)localObject2).getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class), Integer.valueOf(i), paramContext });
      paramContext = (Context)localObject1;
      if (localObject1 == null) {
        paramContext = Integer.valueOf(0);
      }
      i = paramContext.intValue();
      if (i == 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
      return false;
    }
    catch (Exception paramContext)
    {
      ConsoleLogger.printErrorInfo(TimeUtil.class, paramContext.getMessage());
    }
  }
  
  public static boolean isPhone(String paramString)
  {
    boolean bool = false;
    Pattern localPattern2;
    if (!TextUtils.isEmpty(paramString))
    {
      Pattern localPattern1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");
      localPattern2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");
      if (paramString.length() > 9) {
        bool = localPattern1.matcher(paramString).matches();
      }
    }
    else
    {
      return bool;
    }
    return localPattern2.matcher(paramString).matches();
  }
  
  public static boolean isRunningForeground(Context paramContext)
  {
    String str = ((ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
    return (!TextUtils.isEmpty(str)) && (str.equals(paramContext.getPackageName()));
  }
  
  public static String removeImageTitles(String paramString)
  {
    String str = paramString;
    if (paramString != null) {
      str = paramString.replaceAll("\\{\\{\\d+\\}\\}.*\n", "");
    }
    return str;
  }
  
  public static void setDialogText(View paramView)
  {
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      int j = paramView.getChildCount();
      int i = 0;
      while (i < j)
      {
        setDialogText(paramView.getChildAt(i));
        i += 1;
      }
    }
    if ((paramView instanceof TextView)) {
      ((TextView)paramView).setTextColor(-1);
    }
  }
  
  public static String[] splitStr(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return null;
    }
    int i = 0;
    String str = paramString1;
    if (paramString1.endsWith(paramString2))
    {
      str = paramString1 + "0";
      i = 1;
    }
    paramString1 = str.split(",");
    if ((paramString1 != null) && (paramString1.length > 0) && (i != 0)) {
      paramString1[(paramString1.length - 1)] = "";
    }
    return paramString1;
  }
}
