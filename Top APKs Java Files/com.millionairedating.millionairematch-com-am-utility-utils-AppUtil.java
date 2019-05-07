package com.am.utility.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Xml;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.am.utility.model.AbAppProcessInfo;
import com.am.utility.model.AbCPUInfo;
import com.am.utility.model.AbProcessInfo;
import com.am.utility.model.AbPsRow;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;

public class AppUtil
{
  public static List<String[]> mProcessList = null;
  
  public AppUtil() {}
  
  public static void closeSoftInput(Context paramContext)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)paramContext.getSystemService("input_method");
    if ((localInputMethodManager != null) && (((Activity)paramContext).getCurrentFocus() != null)) {
      localInputMethodManager.hideSoftInputFromWindow(((Activity)paramContext).getCurrentFocus().getWindowToken(), 2);
    }
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramContext = paramContext.getApplicationContext().getPackageManager().getInstalledApplications(8192).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if (paramString.equals(localApplicationInfo.processName)) {
        return localApplicationInfo;
      }
    }
    return null;
  }
  
  public static long getAvailMemory(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    paramContext.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem;
  }
  
  public static AbCPUInfo getCPUInfo()
  {
    try
    {
      AbCPUInfo localAbCPUInfo = parseCPUInfo(runCommandTopN1());
      return localAbCPUInfo;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public static String getIMEI(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext.getDeviceId() == null) {
      return null;
    }
    return paramContext.getDeviceId();
  }
  
  public static String getIMSI(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext.getSubscriberId() == null) {
      return null;
    }
    return paramContext.getSubscriberId();
  }
  
  public static String getMac(Context paramContext)
  {
    paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
    if (paramContext.getMacAddress() == null) {
      return null;
    }
    return paramContext.getMacAddress();
  }
  
  public static AbProcessInfo getMemInfo(int paramInt)
  {
    AbProcessInfo localAbProcessInfo = new AbProcessInfo();
    if (mProcessList == null) {
      mProcessList = getProcessRunningInfo();
    }
    int j = mProcessList.size();
    int i = 0;
    String[] arrayOfString;
    long l;
    if (i < j)
    {
      arrayOfString = (String[])mProcessList.get(i);
      String str = arrayOfString[0];
      if (str == null) {}
      while (Integer.parseInt(str) != paramInt)
      {
        i += 1;
        break;
      }
      localAbProcessInfo.pid = Integer.parseInt(arrayOfString[0]);
      localAbProcessInfo.cpu = arrayOfString[2];
      localAbProcessInfo.status = arrayOfString[3];
      localAbProcessInfo.threadsCount = arrayOfString[4];
      l = 0L;
      if (arrayOfString[6].indexOf("M") == -1) {
        break label186;
      }
      l = Long.parseLong(arrayOfString[6].replace("M", "")) * 1000L * 1024L;
    }
    for (;;)
    {
      localAbProcessInfo.memory = l;
      localAbProcessInfo.uid = arrayOfString[8];
      localAbProcessInfo.processName = arrayOfString[9];
      return localAbProcessInfo;
      label186:
      if (arrayOfString[6].indexOf("K") != -1) {
        l = Long.parseLong(arrayOfString[6].replace("K", "")) * 1000L;
      } else if (arrayOfString[6].indexOf("G") != -1) {
        l = Long.parseLong(arrayOfString[6].replace("G", "")) * 1000L * 1024L * 1024L;
      }
    }
  }
  
  public static AbProcessInfo getMemInfo(String paramString)
  {
    AbProcessInfo localAbProcessInfo = new AbProcessInfo();
    if (mProcessList == null) {
      mProcessList = getProcessRunningInfo();
    }
    Iterator localIterator = mProcessList.iterator();
    String[] arrayOfString;
    long l;
    while (localIterator.hasNext())
    {
      arrayOfString = (String[])localIterator.next();
      String str = arrayOfString[9];
      if ((str != null) && (str.equals(paramString)))
      {
        localAbProcessInfo.pid = Integer.parseInt(arrayOfString[0]);
        localAbProcessInfo.cpu = arrayOfString[2];
        localAbProcessInfo.status = arrayOfString[3];
        localAbProcessInfo.threadsCount = arrayOfString[4];
        l = 0L;
        if (arrayOfString[6].indexOf("M") == -1) {
          break label212;
        }
        l = Long.parseLong(arrayOfString[6].replace("M", "")) * 1000L * 1024L;
      }
    }
    for (;;)
    {
      localAbProcessInfo.memory = l;
      localAbProcessInfo.uid = arrayOfString[8];
      localAbProcessInfo.processName = arrayOfString[9];
      if (localAbProcessInfo.memory == 0L) {
        Loger.d("##" + paramString + ",top -n 1未找到");
      }
      return localAbProcessInfo;
      label212:
      if (arrayOfString[6].indexOf("K") != -1) {
        l = Long.parseLong(arrayOfString[6].replace("K", "")) * 1000L;
      } else if (arrayOfString[6].indexOf("G") != -1) {
        l = Long.parseLong(arrayOfString[6].replace("G", "")) * 1000L * 1024L * 1024L;
      }
    }
  }
  
  public static int getNumCores()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new FileFilter()
      {
        public boolean accept(File paramAnonymousFile)
        {
          return Pattern.matches("cpu[0-9]", paramAnonymousFile.getName());
        }
      }).length;
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 1;
  }
  
  public static PackageInfo getPackageInfo(Context paramContext)
  {
    try
    {
      String str = paramContext.getPackageName();
      paramContext = paramContext.getPackageManager().getPackageInfo(str, 1);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getPhoneNumber(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if ((paramContext.getLine1Number() == null) || (paramContext.getLine1Number().length() < 11)) {
      return null;
    }
    return paramContext.getLine1Number();
  }
  
  public static List<String[]> getProcessRunningInfo()
  {
    try
    {
      List localList = parseProcessRunningInfo(runCommandTopN1());
      return localList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public static AbPsRow getPsRow(String paramString)
  {
    Iterator localIterator = ps().iterator();
    while (localIterator.hasNext())
    {
      AbPsRow localAbPsRow = (AbPsRow)localIterator.next();
      if (paramString.equals(localAbPsRow.cmd)) {
        return localAbPsRow;
      }
    }
    return null;
  }
  
  public static String getQQNumber(Context paramContext)
  {
    Object localObject1 = null;
    getRootPermission(paramContext);
    Object localObject2 = new File("/data/data/com.tencent.mobileqq/shared_prefs/Last_Login.xml");
    getRootPermission("/data/data/com.tencent.mobileqq/shared_prefs/Last_Login.xml");
    paramContext = localObject1;
    if (((File)localObject2).canRead()) {}
    for (;;)
    {
      try
      {
        paramContext = new FileInputStream((File)localObject2);
        localObject2 = Xml.newPullParser();
        ((XmlPullParser)localObject2).setInput(paramContext, "UTF-8");
        i = ((XmlPullParser)localObject2).getEventType();
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return null;
      }
      int i = ((XmlPullParser)localObject2).next();
      if ((!"map".equals(((XmlPullParser)localObject2).getName())) || (("string".equals(((XmlPullParser)localObject2).getName())) && (((XmlPullParser)localObject2).getAttributeValue(null, "name").equals("uin"))))
      {
        paramContext = ((XmlPullParser)localObject2).nextText();
        return paramContext;
        paramContext = localObject1;
        if (i != 1) {
          switch (i)
          {
          }
        }
      }
    }
  }
  
  public static boolean getRootPermission(Context paramContext)
  {
    return getRootPermission(paramContext.getPackageCodePath());
  }
  
  /* Error */
  public static boolean getRootPermission(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore 4
    //   8: aconst_null
    //   9: astore 5
    //   11: aload_3
    //   12: astore_2
    //   13: aload 6
    //   15: astore_1
    //   16: new 225	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 226	java/lang/StringBuilder:<init>	()V
    //   23: ldc_w 346
    //   26: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: aload_0
    //   30: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: astore 7
    //   38: aload_3
    //   39: astore_2
    //   40: aload 6
    //   42: astore_1
    //   43: invokestatic 352	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   46: ldc_w 354
    //   49: invokevirtual 358	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   52: astore_0
    //   53: aload_0
    //   54: astore_2
    //   55: aload_0
    //   56: astore_1
    //   57: new 360	java/io/DataOutputStream
    //   60: dup
    //   61: aload_0
    //   62: invokevirtual 366	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   65: invokespecial 369	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   68: astore_3
    //   69: aload_3
    //   70: new 225	java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial 226	java/lang/StringBuilder:<init>	()V
    //   77: aload 7
    //   79: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: ldc_w 371
    //   85: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   91: invokevirtual 374	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   94: aload_3
    //   95: ldc_w 376
    //   98: invokevirtual 374	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   101: aload_3
    //   102: invokevirtual 379	java/io/DataOutputStream:flush	()V
    //   105: aload_0
    //   106: invokevirtual 382	java/lang/Process:waitFor	()I
    //   109: pop
    //   110: aload_3
    //   111: ifnull +7 -> 118
    //   114: aload_3
    //   115: invokevirtual 385	java/io/DataOutputStream:close	()V
    //   118: aload_0
    //   119: invokevirtual 388	java/lang/Process:destroy	()V
    //   122: iconst_1
    //   123: ireturn
    //   124: astore_0
    //   125: aload_0
    //   126: invokevirtual 125	java/lang/Exception:printStackTrace	()V
    //   129: goto -7 -> 122
    //   132: astore_0
    //   133: aload_2
    //   134: astore_0
    //   135: aload 5
    //   137: astore_1
    //   138: aload_1
    //   139: ifnull +7 -> 146
    //   142: aload_1
    //   143: invokevirtual 385	java/io/DataOutputStream:close	()V
    //   146: aload_0
    //   147: invokevirtual 388	java/lang/Process:destroy	()V
    //   150: iconst_0
    //   151: ireturn
    //   152: astore_0
    //   153: aload_0
    //   154: invokevirtual 125	java/lang/Exception:printStackTrace	()V
    //   157: iconst_0
    //   158: ireturn
    //   159: astore_0
    //   160: aload_1
    //   161: astore_2
    //   162: aload 4
    //   164: astore_1
    //   165: aload_1
    //   166: ifnull +7 -> 173
    //   169: aload_1
    //   170: invokevirtual 385	java/io/DataOutputStream:close	()V
    //   173: aload_2
    //   174: invokevirtual 388	java/lang/Process:destroy	()V
    //   177: aload_0
    //   178: athrow
    //   179: astore_1
    //   180: aload_1
    //   181: invokevirtual 125	java/lang/Exception:printStackTrace	()V
    //   184: goto -7 -> 177
    //   187: astore 4
    //   189: aload_3
    //   190: astore_1
    //   191: aload_0
    //   192: astore_2
    //   193: aload 4
    //   195: astore_0
    //   196: goto -31 -> 165
    //   199: astore_1
    //   200: aload_3
    //   201: astore_1
    //   202: goto -64 -> 138
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	205	0	paramString	String
    //   15	155	1	localObject1	Object
    //   179	2	1	localException1	Exception
    //   190	1	1	localDataOutputStream1	java.io.DataOutputStream
    //   199	1	1	localException2	Exception
    //   201	1	1	localDataOutputStream2	java.io.DataOutputStream
    //   12	181	2	localObject2	Object
    //   4	197	3	localDataOutputStream3	java.io.DataOutputStream
    //   6	157	4	localObject3	Object
    //   187	7	4	localObject4	Object
    //   9	127	5	localObject5	Object
    //   1	40	6	localObject6	Object
    //   36	42	7	str	String
    // Exception table:
    //   from	to	target	type
    //   114	118	124	java/lang/Exception
    //   118	122	124	java/lang/Exception
    //   16	38	132	java/lang/Exception
    //   43	53	132	java/lang/Exception
    //   57	69	132	java/lang/Exception
    //   142	146	152	java/lang/Exception
    //   146	150	152	java/lang/Exception
    //   16	38	159	finally
    //   43	53	159	finally
    //   57	69	159	finally
    //   169	173	179	java/lang/Exception
    //   173	177	179	java/lang/Exception
    //   69	110	187	finally
    //   69	110	199	java/lang/Exception
  }
  
  public static List<AbAppProcessInfo> getRunningAppProcesses(Context paramContext)
  {
    localObject2 = null;
    for (;;)
    {
      try
      {
        localObject3 = (ActivityManager)paramContext.getSystemService("activity");
        localPackageManager = paramContext.getApplicationContext().getPackageManager();
        localObject1 = new ArrayList();
        try
        {
          localObject2 = ((ActivityManager)localObject3).getRunningAppProcesses();
          if (mProcessList != null) {
            mProcessList.clear();
          }
          mProcessList = getProcessRunningInfo();
          localObject2 = ((List)localObject2).iterator();
          if (!((Iterator)localObject2).hasNext()) {
            continue;
          }
          localObject4 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
          localObject3 = new AbAppProcessInfo(((ActivityManager.RunningAppProcessInfo)localObject4).processName, ((ActivityManager.RunningAppProcessInfo)localObject4).pid, ((ActivityManager.RunningAppProcessInfo)localObject4).uid);
          Object localObject5 = getApplicationInfo(paramContext, ((ActivityManager.RunningAppProcessInfo)localObject4).processName);
          if (localObject5 == null) {
            continue;
          }
          Drawable localDrawable = ((ApplicationInfo)localObject5).loadIcon(localPackageManager);
          localObject5 = ((ApplicationInfo)localObject5).loadLabel(localPackageManager).toString();
          ((AbAppProcessInfo)localObject3).icon = localDrawable;
          ((AbAppProcessInfo)localObject3).appName = ((String)localObject5);
          localObject4 = getMemInfo(((ActivityManager.RunningAppProcessInfo)localObject4).processName);
          ((AbAppProcessInfo)localObject3).memory = ((AbProcessInfo)localObject4).memory;
          ((AbAppProcessInfo)localObject3).cpu = ((AbProcessInfo)localObject4).cpu;
          ((AbAppProcessInfo)localObject3).status = ((AbProcessInfo)localObject4).status;
          ((AbAppProcessInfo)localObject3).threadsCount = ((AbProcessInfo)localObject4).threadsCount;
          ((List)localObject1).add(localObject3);
          continue;
          paramContext.printStackTrace();
        }
        catch (Exception paramContext) {}
      }
      catch (Exception paramContext)
      {
        Object localObject3;
        PackageManager localPackageManager;
        Object localObject4;
        Object localObject1 = localObject2;
        continue;
      }
      return localObject1;
      if (((ActivityManager.RunningAppProcessInfo)localObject4).processName.indexOf(":") != -1) {
        ((AbAppProcessInfo)localObject3).icon = getApplicationInfo(paramContext, localObject4.processName.split(":")[0]).loadIcon(localPackageManager);
      }
      ((AbAppProcessInfo)localObject3).appName = ((ActivityManager.RunningAppProcessInfo)localObject4).processName;
    }
    return localObject1;
  }
  
  public static String getSSID(Context paramContext)
  {
    paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
    if (paramContext.getSSID() == null) {
      return null;
    }
    return paramContext.getSSID();
  }
  
  public static long getTotalMemory(Context paramContext)
  {
    long l2 = 0L;
    long l1 = l2;
    try
    {
      paramContext = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      l1 = l2;
      String[] arrayOfString = paramContext.readLine().split("\\s+");
      l1 = l2;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = arrayOfString[i];
        l1 = l2;
        Loger.d(str + "\t");
        i += 1;
      }
      l1 = l2;
      l2 = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
      l1 = l2;
      paramContext.close();
      return l2;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return l1;
  }
  
  /* Error */
  public static boolean importDatabase(Context paramContext, String paramString, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 11
    //   3: aconst_null
    //   4: astore 12
    //   6: aconst_null
    //   7: astore 13
    //   9: aconst_null
    //   10: astore 8
    //   12: aconst_null
    //   13: astore 10
    //   15: aconst_null
    //   16: astore 9
    //   18: iconst_0
    //   19: istore 4
    //   21: aload 10
    //   23: astore 6
    //   25: aload 11
    //   27: astore 5
    //   29: aload 12
    //   31: astore 7
    //   33: new 246	java/io/File
    //   36: dup
    //   37: new 225	java/lang/StringBuilder
    //   40: dup
    //   41: invokespecial 226	java/lang/StringBuilder:<init>	()V
    //   44: ldc_w 478
    //   47: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: aload_0
    //   51: invokevirtual 260	android/content/Context:getPackageName	()Ljava/lang/String;
    //   54: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc_w 480
    //   60: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload_1
    //   64: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokespecial 250	java/io/File:<init>	(Ljava/lang/String;)V
    //   73: astore 14
    //   75: aload 13
    //   77: astore_1
    //   78: aload 10
    //   80: astore 6
    //   82: aload 11
    //   84: astore 5
    //   86: aload 12
    //   88: astore 7
    //   90: aload 14
    //   92: invokevirtual 483	java/io/File:exists	()Z
    //   95: ifne +181 -> 276
    //   98: aload 10
    //   100: astore 6
    //   102: aload 11
    //   104: astore 5
    //   106: aload 12
    //   108: astore 7
    //   110: aload 14
    //   112: invokevirtual 487	java/io/File:getParentFile	()Ljava/io/File;
    //   115: invokevirtual 483	java/io/File:exists	()Z
    //   118: ifne +24 -> 142
    //   121: aload 10
    //   123: astore 6
    //   125: aload 11
    //   127: astore 5
    //   129: aload 12
    //   131: astore 7
    //   133: aload 14
    //   135: invokevirtual 487	java/io/File:getParentFile	()Ljava/io/File;
    //   138: invokevirtual 490	java/io/File:mkdirs	()Z
    //   141: pop
    //   142: aload 10
    //   144: astore 6
    //   146: aload 11
    //   148: astore 5
    //   150: aload 12
    //   152: astore 7
    //   154: aload 14
    //   156: invokevirtual 493	java/io/File:createNewFile	()Z
    //   159: pop
    //   160: aload 10
    //   162: astore 6
    //   164: aload 11
    //   166: astore 5
    //   168: aload 12
    //   170: astore 7
    //   172: aload_0
    //   173: invokevirtual 497	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   176: iload_2
    //   177: invokevirtual 503	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   180: astore_0
    //   181: aload 10
    //   183: astore 6
    //   185: aload_0
    //   186: astore 5
    //   188: aload_0
    //   189: astore 7
    //   191: new 505	java/io/FileOutputStream
    //   194: dup
    //   195: aload 14
    //   197: invokespecial 506	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   200: astore_1
    //   201: sipush 1024
    //   204: newarray byte
    //   206: astore 5
    //   208: aload_0
    //   209: aload 5
    //   211: invokevirtual 512	java/io/InputStream:read	([B)I
    //   214: istore_2
    //   215: iload_2
    //   216: ifle +51 -> 267
    //   219: aload_1
    //   220: aload 5
    //   222: iconst_0
    //   223: iload_2
    //   224: invokevirtual 516	java/io/FileOutputStream:write	([BII)V
    //   227: goto -19 -> 208
    //   230: astore 8
    //   232: aload_1
    //   233: astore 6
    //   235: aload_0
    //   236: astore 5
    //   238: aload 8
    //   240: invokevirtual 125	java/lang/Exception:printStackTrace	()V
    //   243: aload_1
    //   244: ifnull +7 -> 251
    //   247: aload_1
    //   248: invokevirtual 517	java/io/FileOutputStream:close	()V
    //   251: iload 4
    //   253: istore_3
    //   254: aload_0
    //   255: ifnull +10 -> 265
    //   258: aload_0
    //   259: invokevirtual 518	java/io/InputStream:close	()V
    //   262: iload 4
    //   264: istore_3
    //   265: iload_3
    //   266: ireturn
    //   267: aload_1
    //   268: invokevirtual 519	java/io/FileOutputStream:flush	()V
    //   271: aload_1
    //   272: astore 8
    //   274: aload_0
    //   275: astore_1
    //   276: iconst_1
    //   277: istore_3
    //   278: aload 8
    //   280: ifnull +8 -> 288
    //   283: aload 8
    //   285: invokevirtual 517	java/io/FileOutputStream:close	()V
    //   288: aload_1
    //   289: ifnull -24 -> 265
    //   292: aload_1
    //   293: invokevirtual 518	java/io/InputStream:close	()V
    //   296: iconst_1
    //   297: ireturn
    //   298: astore_0
    //   299: iconst_1
    //   300: ireturn
    //   301: astore_0
    //   302: aload 6
    //   304: ifnull +8 -> 312
    //   307: aload 6
    //   309: invokevirtual 517	java/io/FileOutputStream:close	()V
    //   312: aload 5
    //   314: ifnull +8 -> 322
    //   317: aload 5
    //   319: invokevirtual 518	java/io/InputStream:close	()V
    //   322: aload_0
    //   323: athrow
    //   324: astore_0
    //   325: goto -37 -> 288
    //   328: astore_1
    //   329: goto -78 -> 251
    //   332: astore_0
    //   333: iconst_0
    //   334: ireturn
    //   335: astore_1
    //   336: goto -24 -> 312
    //   339: astore_1
    //   340: goto -18 -> 322
    //   343: astore 7
    //   345: aload_1
    //   346: astore 6
    //   348: aload_0
    //   349: astore 5
    //   351: aload 7
    //   353: astore_0
    //   354: goto -52 -> 302
    //   357: astore 8
    //   359: aload 9
    //   361: astore_1
    //   362: aload 7
    //   364: astore_0
    //   365: goto -133 -> 232
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	368	0	paramContext	Context
    //   0	368	1	paramString	String
    //   0	368	2	paramInt	int
    //   253	25	3	bool1	boolean
    //   19	244	4	bool2	boolean
    //   27	323	5	localObject1	Object
    //   23	324	6	localObject2	Object
    //   31	159	7	localObject3	Object
    //   343	20	7	localObject4	Object
    //   10	1	8	localObject5	Object
    //   230	9	8	localException1	Exception
    //   272	12	8	str	String
    //   357	1	8	localException2	Exception
    //   16	344	9	localObject6	Object
    //   13	169	10	localObject7	Object
    //   1	164	11	localObject8	Object
    //   4	165	12	localObject9	Object
    //   7	69	13	localObject10	Object
    //   73	123	14	localFile	File
    // Exception table:
    //   from	to	target	type
    //   201	208	230	java/lang/Exception
    //   208	215	230	java/lang/Exception
    //   219	227	230	java/lang/Exception
    //   267	271	230	java/lang/Exception
    //   292	296	298	java/lang/Exception
    //   33	75	301	finally
    //   90	98	301	finally
    //   110	121	301	finally
    //   133	142	301	finally
    //   154	160	301	finally
    //   172	181	301	finally
    //   191	201	301	finally
    //   238	243	301	finally
    //   283	288	324	java/lang/Exception
    //   247	251	328	java/lang/Exception
    //   258	262	332	java/lang/Exception
    //   307	312	335	java/lang/Exception
    //   317	322	339	java/lang/Exception
    //   201	208	343	finally
    //   208	215	343	finally
    //   219	227	343	finally
    //   267	271	343	finally
    //   33	75	357	java/lang/Exception
    //   90	98	357	java/lang/Exception
    //   110	121	357	java/lang/Exception
    //   133	142	357	java/lang/Exception
    //   154	160	357	java/lang/Exception
    //   172	181	357	java/lang/Exception
    //   191	201	357	java/lang/Exception
  }
  
  public static void installApk(Context paramContext, File paramFile)
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    paramContext.startActivity(localIntent);
  }
  
  public static boolean isGpsEnabled(Context paramContext)
  {
    return ((LocationManager)paramContext.getSystemService("location")).isProviderEnabled("gps");
  }
  
  public static boolean isMobile(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.getType() == 0);
  }
  
  @Deprecated
  public static boolean isNetworkAvailable(Context paramContext)
  {
    boolean bool2 = false;
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        paramContext = paramContext.getActiveNetworkInfo();
        bool1 = bool2;
        if (paramContext != null)
        {
          bool1 = bool2;
          if (paramContext.isConnected())
          {
            paramContext = paramContext.getState();
            NetworkInfo.State localState = NetworkInfo.State.CONNECTED;
            bool1 = bool2;
            if (paramContext == localState) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isServiceRunning(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (paramContext.hasNext()) {
      if (paramString.equals(((ActivityManager.RunningServiceInfo)paramContext.next()).service.getClassName())) {
        bool = true;
      }
    }
    return bool;
  }
  
  public static void killProcesses(Context paramContext, int paramInt, String paramString)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    try
    {
      if (paramString.indexOf(":") == -1) {}
      for (paramContext = paramString;; paramContext = paramString.split(":")[0])
      {
        localActivityManager.killBackgroundProcesses(paramContext);
        paramString = localActivityManager.getClass().getDeclaredMethod("forceStopPackage", new Class[] { String.class });
        paramString.setAccessible(true);
        paramString.invoke(localActivityManager, new Object[] { paramContext });
        return;
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static AbCPUInfo parseCPUInfo(String paramString)
  {
    AbCPUInfo localAbCPUInfo = new AbCPUInfo();
    paramString = paramString.split("[\n]+");
    int i = 0;
    while (i < paramString.length)
    {
      String[] arrayOfString1 = paramString[i];
      if ((arrayOfString1.indexOf("User") != -1) && (arrayOfString1.indexOf("System") != -1))
      {
        arrayOfString1 = arrayOfString1.trim().split(",");
        int j = 0;
        if (j < arrayOfString1.length)
        {
          String[] arrayOfString2 = arrayOfString1[j].trim().split(" ");
          if (j == 0) {
            localAbCPUInfo.User = arrayOfString2[1];
          }
          for (;;)
          {
            j += 1;
            break;
            if (j == 1) {
              localAbCPUInfo.System = arrayOfString2[1];
            } else if (j == 2) {
              localAbCPUInfo.IOW = arrayOfString2[1];
            } else if (j == 3) {
              localAbCPUInfo.IRQ = arrayOfString2[1];
            }
          }
        }
      }
      i += 1;
    }
    return localAbCPUInfo;
  }
  
  public static List<String[]> parseProcessRunningInfo(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    paramString = paramString.split("[\n]+");
    int i = 0;
    if (i < paramString.length)
    {
      String[] arrayOfString = paramString[i];
      int k;
      if (arrayOfString.indexOf("PID") == -1)
      {
        k = j;
        if (j == 1)
        {
          arrayOfString = arrayOfString.trim().split("[ ]+");
          k = j;
          if (arrayOfString.length == 10)
          {
            if (!arrayOfString[9].startsWith("/system/bin/")) {
              break label99;
            }
            k = j;
          }
        }
      }
      for (;;)
      {
        i += 1;
        j = k;
        break;
        label99:
        localArrayList.add(arrayOfString);
        k = j;
        continue;
        k = 1;
      }
    }
    return localArrayList;
  }
  
  public static List<AbPsRow> ps()
  {
    new ArrayList();
    String[] arrayOfString = runScript("ps").split("\n");
    ArrayList localArrayList = new ArrayList();
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      AbPsRow localAbPsRow = new AbPsRow(arrayOfString[i]);
      if (localAbPsRow.pid != null) {
        localArrayList.add(localAbPsRow);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static String runCommand(String[] paramArrayOfString, String paramString)
  {
    String str = "";
    Loger.d("#" + paramArrayOfString);
    Object localObject = str;
    try
    {
      paramArrayOfString = new ProcessBuilder(paramArrayOfString);
      if (paramString != null)
      {
        localObject = str;
        paramArrayOfString.directory(new File(paramString));
      }
      localObject = str;
      paramArrayOfString.redirectErrorStream(true);
      localObject = str;
      paramString = paramArrayOfString.start().getInputStream();
      localObject = str;
      byte[] arrayOfByte = new byte['Ѐ'];
      for (paramArrayOfString = str;; paramArrayOfString = paramArrayOfString + str)
      {
        localObject = paramArrayOfString;
        if (paramString.read(arrayOfByte) == -1) {
          break;
        }
        localObject = paramArrayOfString;
        str = new String(arrayOfByte);
        localObject = paramArrayOfString;
      }
      localObject = paramArrayOfString;
      paramString.close();
      return paramArrayOfString;
    }
    catch (Exception paramArrayOfString)
    {
      paramArrayOfString.printStackTrace();
    }
    return localObject;
  }
  
  public static String runCommandTopN1()
  {
    try
    {
      String str = runCommand(new String[] { "/system/bin/top", "-n", "1" }, "/system/bin/");
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public static String runScript(final String paramString)
  {
    Object localObject2;
    try
    {
      localObject2 = Runtime.getRuntime().exec(paramString);
      localObject1 = new StringBuilder();
      Thread localThread = new Thread(new Runnable()
      {
        public void run()
        {
          BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(this.val$m_process.getInputStream()), 8192);
          try
          {
            for (;;)
            {
              String str = localBufferedReader.readLine();
              if (str == null) {
                break;
              }
              this.val$sbread.append(str).append("\n");
            }
            try
            {
              localIOException2.close();
              throw localObject;
            }
            catch (IOException localIOException3)
            {
              for (;;)
              {
                localIOException3.printStackTrace();
              }
            }
          }
          catch (IOException localIOException4)
          {
            localIOException4 = localIOException4;
            localIOException4.printStackTrace();
            for (;;)
            {
              try
              {
                localBufferedReader.close();
                return;
              }
              catch (IOException localIOException2)
              {
                localIOException2.printStackTrace();
                return;
              }
              try
              {
                localBufferedReader.close();
                return;
              }
              catch (IOException localIOException1)
              {
                localIOException1.printStackTrace();
                return;
              }
            }
          }
          finally {}
        }
      });
      localThread.start();
      paramString = new StringBuilder();
      localObject2 = new Thread(new Runnable()
      {
        public void run()
        {
          BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(this.val$m_process.getErrorStream()), 8192);
          try
          {
            for (;;)
            {
              String str = localBufferedReader.readLine();
              if (str == null) {
                break;
              }
              paramString.append(str).append("\n");
            }
            try
            {
              localIOException2.close();
              throw localObject;
            }
            catch (IOException localIOException3)
            {
              for (;;)
              {
                localIOException3.printStackTrace();
              }
            }
          }
          catch (IOException localIOException4)
          {
            localIOException4 = localIOException4;
            localIOException4.printStackTrace();
            for (;;)
            {
              try
              {
                localBufferedReader.close();
                return;
              }
              catch (IOException localIOException2)
              {
                localIOException2.printStackTrace();
                return;
              }
              try
              {
                localBufferedReader.close();
                return;
              }
              catch (IOException localIOException1)
              {
                localIOException1.printStackTrace();
                return;
              }
            }
          }
          finally {}
        }
      });
      ((Thread)localObject2).start();
      while (localThread.isAlive()) {
        Thread.sleep(50L);
      }
      if (!((Thread)localObject2).isAlive()) {
        break label100;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    ((Thread)localObject2).interrupt();
    label100:
    Object localObject1 = ((StringBuilder)localObject1).toString();
    paramString = paramString.toString();
    paramString = (String)localObject1 + paramString;
    return paramString;
  }
  
  public static void showSoftInput(Context paramContext)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).toggleSoftInput(0, 2);
  }
  
  public static boolean stopRunningService(Context paramContext, String paramString)
  {
    Object localObject = null;
    boolean bool = false;
    try
    {
      paramString = new Intent(paramContext, Class.forName(paramString));
      if (paramString != null) {
        bool = paramContext.stopService(paramString);
      }
      return bool;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = localObject;
      }
    }
  }
  
  public static void uninstallApk(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.DELETE");
    localIntent.setData(Uri.parse("package:" + paramString));
    paramContext.startActivity(localIntent);
  }
}
