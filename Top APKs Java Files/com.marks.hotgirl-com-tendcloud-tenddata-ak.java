package com.tendcloud.tenddata;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.cardemulation.CardEmulation;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class ak
{
  public static final String a = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
  public static final String b = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";
  public static final String c = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq";
  private static final int d = 3600000;
  private static final FileFilter e = new al();
  private static BroadcastReceiver f = new am();
  
  public ak() {}
  
  /* Error */
  public static int a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: iconst_m1
    //   6: istore_1
    //   7: aload_0
    //   8: invokestatic 48	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   11: ifeq +5 -> 16
    //   14: iload_1
    //   15: ireturn
    //   16: new 50	java/io/FileReader
    //   19: dup
    //   20: aload_0
    //   21: invokespecial 53	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   24: astore_0
    //   25: new 55	java/io/BufferedReader
    //   28: dup
    //   29: aload_0
    //   30: invokespecial 58	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   33: astore_3
    //   34: aload_3
    //   35: invokevirtual 62	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   38: invokevirtual 67	java/lang/String:trim	()Ljava/lang/String;
    //   41: invokestatic 72	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   44: istore_2
    //   45: aload_0
    //   46: ifnull +7 -> 53
    //   49: aload_0
    //   50: invokevirtual 75	java/io/FileReader:close	()V
    //   53: iload_2
    //   54: istore_1
    //   55: aload_3
    //   56: ifnull -42 -> 14
    //   59: aload_3
    //   60: invokevirtual 76	java/io/BufferedReader:close	()V
    //   63: iload_2
    //   64: ireturn
    //   65: astore_0
    //   66: iload_2
    //   67: ireturn
    //   68: astore_0
    //   69: aconst_null
    //   70: astore_0
    //   71: aload_3
    //   72: ifnull +7 -> 79
    //   75: aload_3
    //   76: invokevirtual 75	java/io/FileReader:close	()V
    //   79: aload_0
    //   80: ifnull -66 -> 14
    //   83: aload_0
    //   84: invokevirtual 76	java/io/BufferedReader:close	()V
    //   87: iconst_m1
    //   88: ireturn
    //   89: astore_0
    //   90: iconst_m1
    //   91: ireturn
    //   92: astore_3
    //   93: aconst_null
    //   94: astore_0
    //   95: aload_0
    //   96: ifnull +7 -> 103
    //   99: aload_0
    //   100: invokevirtual 75	java/io/FileReader:close	()V
    //   103: aload 4
    //   105: ifnull +8 -> 113
    //   108: aload 4
    //   110: invokevirtual 76	java/io/BufferedReader:close	()V
    //   113: aload_3
    //   114: athrow
    //   115: astore_0
    //   116: goto -63 -> 53
    //   119: astore_3
    //   120: goto -41 -> 79
    //   123: astore_0
    //   124: goto -21 -> 103
    //   127: astore_0
    //   128: goto -15 -> 113
    //   131: astore_3
    //   132: goto -37 -> 95
    //   135: astore 5
    //   137: aload_3
    //   138: astore 4
    //   140: aload 5
    //   142: astore_3
    //   143: goto -48 -> 95
    //   146: astore_3
    //   147: aconst_null
    //   148: astore 4
    //   150: aload_0
    //   151: astore_3
    //   152: aload 4
    //   154: astore_0
    //   155: goto -84 -> 71
    //   158: astore 4
    //   160: aload_0
    //   161: astore 4
    //   163: aload_3
    //   164: astore_0
    //   165: aload 4
    //   167: astore_3
    //   168: goto -97 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	171	0	paramString	String
    //   6	49	1	i	int
    //   44	23	2	j	int
    //   4	72	3	localBufferedReader	BufferedReader
    //   92	22	3	localObject1	Object
    //   119	1	3	localThrowable1	Throwable
    //   131	7	3	localObject2	Object
    //   142	1	3	localObject3	Object
    //   146	1	3	localThrowable2	Throwable
    //   151	17	3	str1	String
    //   1	152	4	localObject4	Object
    //   158	1	4	localThrowable3	Throwable
    //   161	5	4	str2	String
    //   135	6	5	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   59	63	65	java/lang/Throwable
    //   16	25	68	java/lang/Throwable
    //   83	87	89	java/lang/Throwable
    //   16	25	92	finally
    //   49	53	115	java/lang/Throwable
    //   75	79	119	java/lang/Throwable
    //   99	103	123	java/lang/Throwable
    //   108	113	127	java/lang/Throwable
    //   25	34	131	finally
    //   34	45	135	finally
    //   25	34	146	java/lang/Throwable
    //   34	45	158	java/lang/Throwable
  }
  
  public static String a()
  {
    return "Android+" + Build.VERSION.RELEASE;
  }
  
  private static String a(String paramString1, String paramString2)
  {
    try
    {
      String str = paramString1.toLowerCase();
      if ((!str.startsWith("unknown")) && (!str.startsWith("alps")) && (!str.startsWith("android")) && (!str.startsWith("sprd")) && (!str.startsWith("spreadtrum")) && (!str.startsWith("rockchip")) && (!str.startsWith("wondermedia")) && (!str.startsWith("mtk")) && (!str.startsWith("mt65")) && (!str.startsWith("nvidia")) && (!str.startsWith("brcm")) && (!str.startsWith("marvell")))
      {
        boolean bool = paramString2.toLowerCase().contains(str);
        if (!bool) {}
      }
      else
      {
        paramString1 = null;
      }
      return paramString1;
    }
    catch (Throwable paramString1) {}
    return null;
  }
  
  /* Error */
  private static List a(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 135	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_3
    //   5: aload_3
    //   6: iload_1
    //   7: invokevirtual 141	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   10: astore_0
    //   11: aload_0
    //   12: areturn
    //   13: astore_0
    //   14: new 143	java/util/ArrayList
    //   17: dup
    //   18: invokespecial 144	java/util/ArrayList:<init>	()V
    //   21: astore 4
    //   23: aconst_null
    //   24: astore_0
    //   25: invokestatic 150	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   28: ldc -104
    //   30: invokevirtual 156	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   33: astore 5
    //   35: new 55	java/io/BufferedReader
    //   38: dup
    //   39: new 158	java/io/InputStreamReader
    //   42: dup
    //   43: aload 5
    //   45: invokevirtual 164	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   48: invokespecial 167	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   51: invokespecial 58	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   54: astore_2
    //   55: aload_2
    //   56: astore_0
    //   57: aload_2
    //   58: invokevirtual 62	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   61: astore 6
    //   63: aload 6
    //   65: ifnull +60 -> 125
    //   68: aload_2
    //   69: astore_0
    //   70: aload 4
    //   72: aload_3
    //   73: aload 6
    //   75: aload 6
    //   77: bipush 58
    //   79: invokevirtual 171	java/lang/String:indexOf	(I)I
    //   82: iconst_1
    //   83: iadd
    //   84: invokevirtual 175	java/lang/String:substring	(I)Ljava/lang/String;
    //   87: iload_1
    //   88: invokevirtual 179	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   91: invokeinterface 185 2 0
    //   96: pop
    //   97: goto -42 -> 55
    //   100: astore_3
    //   101: aload_2
    //   102: astore_0
    //   103: aload_3
    //   104: invokestatic 191	com/tendcloud/tenddata/cf:postSDKError	(Ljava/lang/Throwable;)V
    //   107: aload 4
    //   109: astore_0
    //   110: aload_2
    //   111: ifnull -100 -> 11
    //   114: aload_2
    //   115: invokevirtual 76	java/io/BufferedReader:close	()V
    //   118: aload 4
    //   120: areturn
    //   121: astore_0
    //   122: aload 4
    //   124: areturn
    //   125: aload_2
    //   126: astore_0
    //   127: aload 5
    //   129: invokevirtual 195	java/lang/Process:waitFor	()I
    //   132: pop
    //   133: aload 4
    //   135: astore_0
    //   136: aload_2
    //   137: ifnull -126 -> 11
    //   140: aload_2
    //   141: invokevirtual 76	java/io/BufferedReader:close	()V
    //   144: aload 4
    //   146: areturn
    //   147: astore_0
    //   148: aload 4
    //   150: areturn
    //   151: astore_3
    //   152: aload_0
    //   153: astore_2
    //   154: aload_3
    //   155: astore_0
    //   156: aload_2
    //   157: ifnull +7 -> 164
    //   160: aload_2
    //   161: invokevirtual 76	java/io/BufferedReader:close	()V
    //   164: aload_0
    //   165: athrow
    //   166: astore_2
    //   167: goto -3 -> 164
    //   170: astore_3
    //   171: aload_0
    //   172: astore_2
    //   173: aload_3
    //   174: astore_0
    //   175: goto -19 -> 156
    //   178: astore_3
    //   179: aconst_null
    //   180: astore_2
    //   181: goto -80 -> 101
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	184	0	paramContext	Context
    //   0	184	1	paramInt	int
    //   54	107	2	localObject1	Object
    //   166	1	2	localThrowable1	Throwable
    //   172	9	2	localContext	Context
    //   4	69	3	localPackageManager	PackageManager
    //   100	4	3	localThrowable2	Throwable
    //   151	4	3	localObject2	Object
    //   170	4	3	localObject3	Object
    //   178	1	3	localThrowable3	Throwable
    //   21	128	4	localArrayList	java.util.ArrayList
    //   33	95	5	localProcess	Process
    //   61	15	6	str	String
    // Exception table:
    //   from	to	target	type
    //   5	11	13	java/lang/Throwable
    //   57	63	100	java/lang/Throwable
    //   70	97	100	java/lang/Throwable
    //   127	133	100	java/lang/Throwable
    //   114	118	121	java/lang/Throwable
    //   140	144	147	java/lang/Throwable
    //   25	55	151	finally
    //   160	164	166	java/lang/Throwable
    //   57	63	170	finally
    //   70	97	170	finally
    //   103	107	170	finally
    //   127	133	170	finally
    //   25	55	178	java/lang/Throwable
  }
  
  public static JSONObject a(Context paramContext)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("nfcStatus", b(paramContext));
      localJSONObject.put("appsRegistedHCE", q(paramContext));
      localJSONObject.put("ssMode", r(paramContext));
      return localJSONObject;
    }
    catch (Throwable paramContext)
    {
      cf.postSDKError(paramContext);
    }
    return null;
  }
  
  public static JSONObject a(Context paramContext, JSONObject paramJSONObject)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      if (paramContext != null)
      {
        int i = paramContext.widthPixels;
        int j = paramContext.heightPixels;
        paramJSONObject.put("pixel", Math.min(i, j) + "*" + Math.max(i, j) + "*" + paramContext.densityDpi);
        paramJSONObject.put("densityDpi", paramContext.densityDpi);
      }
      return paramJSONObject;
    }
    catch (Throwable paramContext) {}
    return paramJSONObject;
  }
  
  public static int b(Context paramContext)
  {
    if (paramContext == null) {
      return 0;
    }
    int i;
    try
    {
      if (bc.a(10))
      {
        NfcAdapter localNfcAdapter = ((NfcManager)paramContext.getSystemService("nfc")).getDefaultAdapter();
        if (localNfcAdapter != null) {
          if (!localNfcAdapter.isEnabled())
          {
            i = 1;
          }
          else
          {
            if (bc.a(19))
            {
              boolean bool = paramContext.getPackageManager().hasSystemFeature("android.hardware.nfc.hce");
              if (bool) {
                return 3;
              }
            }
            i = 2;
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      cf.postSDKError(paramContext);
      i = 0;
    }
    return i;
  }
  
  private static int b(String paramString)
  {
    String str = "";
    try
    {
      Matcher localMatcher = Pattern.compile("([0-9]+)").matcher(paramString);
      paramString = str;
      if (localMatcher.find()) {
        paramString = localMatcher.toMatchResult().group(0);
      }
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception paramString)
    {
      cf.postSDKError(paramString);
    }
    return 0;
  }
  
  public static String b()
  {
    try
    {
      String str = Build.ID;
      return str;
    }
    catch (Throwable localThrowable) {}
    return "";
  }
  
  public static JSONObject b(Context paramContext, JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("brightness", p(paramContext));
      return paramJSONObject;
    }
    catch (Throwable paramContext) {}
    return paramJSONObject;
  }
  
  public static String c()
  {
    return Build.MANUFACTURER.trim();
  }
  
  public static String c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      if (paramContext != null)
      {
        int i = paramContext.widthPixels;
        int j = paramContext.heightPixels;
        paramContext = Math.min(i, j) + "*" + Math.max(i, j) + "*" + paramContext.densityDpi;
        return paramContext;
      }
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  private static String c(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (;;)
    {
      try
      {
        paramString = new FileReader(paramString);
        try
        {
          char[] arrayOfChar = new char['Ѐ'];
          localBufferedReader = new BufferedReader(paramString, 1024);
          int i = localBufferedReader.read(arrayOfChar, 0, 1024);
          if (-1 != i)
          {
            localStringBuffer.append(new String(arrayOfChar, 0, i));
            continue;
            return localStringBuffer.toString();
          }
        }
        catch (IOException paramString) {}
      }
      catch (Throwable paramString)
      {
        BufferedReader localBufferedReader;
        continue;
      }
      localBufferedReader.close();
      paramString.close();
    }
  }
  
  public static String d()
  {
    return Build.BRAND.trim();
  }
  
  public static JSONObject d(Context paramContext)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("mobile", l(paramContext));
      localJSONObject.put("wifi", g(paramContext));
      localJSONObject.put("gps", f(paramContext));
      localJSONObject.put("telephone", k(paramContext));
      localJSONObject.put("nfc", i(paramContext));
      localJSONObject.put("bluetooth", h(paramContext));
      localJSONObject.put("otg", e(paramContext));
      localJSONObject.put("insertEarphones", j(paramContext));
      return localJSONObject;
    }
    catch (Throwable paramContext)
    {
      cf.postSDKError(paramContext);
    }
    return null;
  }
  
  public static String e()
  {
    return Build.MODEL.trim();
  }
  
  public static boolean e(Context paramContext)
  {
    boolean bool2 = false;
    Context localContext = paramContext;
    boolean bool1;
    if (paramContext == null)
    {
      bool1 = bool2;
      if (ab.g == null) {
        break label50;
      }
      localContext = ab.g;
    }
    try
    {
      paramContext = localContext.getPackageManager();
      bool1 = bool2;
      if (paramContext != null)
      {
        boolean bool3 = paramContext.hasSystemFeature("android.hardware.usb.host");
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      label50:
      return bool1;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  public static int f()
  {
    return TimeZone.getDefault().getRawOffset() / 3600000;
  }
  
  public static boolean f(Context paramContext)
  {
    boolean bool2 = false;
    Context localContext = paramContext;
    boolean bool1;
    if (paramContext == null)
    {
      bool1 = bool2;
      if (ab.g == null) {
        break label50;
      }
      localContext = ab.g;
    }
    try
    {
      paramContext = localContext.getPackageManager();
      bool1 = bool2;
      if (paramContext != null)
      {
        boolean bool3 = paramContext.hasSystemFeature("android.hardware.location.gps");
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      label50:
      return bool1;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  public static String g()
  {
    try
    {
      String str = Build.MODEL.trim();
      Object localObject2 = a(Build.MANUFACTURER.trim(), str);
      Object localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        localObject1 = a(Build.BRAND.trim(), str);
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = "";
      }
      localObject1 = (String)localObject2 + ":" + str;
      return localObject1;
    }
    catch (Throwable localThrowable) {}
    return "";
  }
  
  public static boolean g(Context paramContext)
  {
    boolean bool2 = false;
    Context localContext = paramContext;
    boolean bool1;
    if (paramContext == null)
    {
      bool1 = bool2;
      if (ab.g == null) {
        break label50;
      }
      localContext = ab.g;
    }
    try
    {
      paramContext = localContext.getPackageManager();
      bool1 = bool2;
      if (paramContext != null)
      {
        boolean bool3 = paramContext.hasSystemFeature("android.hardware.wifi");
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      label50:
      return bool1;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  public static int h()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static boolean h(Context paramContext)
  {
    boolean bool2 = false;
    Context localContext = paramContext;
    boolean bool1;
    if (paramContext == null)
    {
      bool1 = bool2;
      if (ab.g == null) {
        break label50;
      }
      localContext = ab.g;
    }
    try
    {
      paramContext = localContext.getPackageManager();
      bool1 = bool2;
      if (paramContext != null)
      {
        boolean bool3 = paramContext.hasSystemFeature("android.hardware.bluetooth");
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      label50:
      return bool1;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  public static String i()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static boolean i(Context paramContext)
  {
    boolean bool2 = false;
    Context localContext = paramContext;
    boolean bool1;
    if (paramContext == null)
    {
      bool1 = bool2;
      if (ab.g == null) {
        break label50;
      }
      localContext = ab.g;
    }
    try
    {
      paramContext = localContext.getPackageManager();
      bool1 = bool2;
      if (paramContext != null)
      {
        boolean bool3 = paramContext.hasSystemFeature("android.hardware.nfc");
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      label50:
      return bool1;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  public static String j()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static boolean j(Context paramContext)
  {
    bool = false;
    Context localContext = paramContext;
    if (paramContext == null)
    {
      if (ab.g == null) {
        break label38;
      }
      localContext = ab.g;
    }
    try
    {
      paramContext = (AudioManager)localContext.getSystemService("audio");
      if (paramContext == null) {
        break label46;
      }
      bool = paramContext.isWiredHeadsetOn();
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        label38:
        bool = false;
        continue;
        bool = false;
      }
    }
    return bool;
  }
  
  public static String k()
  {
    return Locale.getDefault().getCountry();
  }
  
  public static boolean k(Context paramContext)
  {
    bool = false;
    Context localContext = paramContext;
    if (paramContext == null)
    {
      if (ab.g == null) {
        break label44;
      }
      localContext = ab.g;
    }
    for (;;)
    {
      try
      {
        paramContext = (TelephonyManager)localContext.getSystemService("phone");
        if (paramContext == null) {
          continue;
        }
        int i = paramContext.getPhoneType();
        if (i == 0) {
          continue;
        }
        bool = true;
      }
      catch (Throwable paramContext)
      {
        label44:
        bool = false;
        continue;
        bool = false;
        continue;
      }
      return bool;
      bool = false;
    }
  }
  
  public static boolean l(Context paramContext)
  {
    boolean bool = false;
    Context localContext = paramContext;
    if (paramContext == null)
    {
      if (ab.g == null) {
        break label29;
      }
      localContext = ab.g;
    }
    try
    {
      bool = localContext.getPackageManager().hasSystemFeature("android.hardware.telephony");
      label29:
      return bool;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  /* Error */
  public static String[] l()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: iconst_4
    //   3: anewarray 64	java/lang/String
    //   6: astore_3
    //   7: iconst_0
    //   8: istore_0
    //   9: iload_0
    //   10: iconst_4
    //   11: if_icmpge +16 -> 27
    //   14: aload_3
    //   15: iload_0
    //   16: ldc_w 293
    //   19: aastore
    //   20: iload_0
    //   21: iconst_1
    //   22: iadd
    //   23: istore_0
    //   24: goto -15 -> 9
    //   27: new 143	java/util/ArrayList
    //   30: dup
    //   31: invokespecial 144	java/util/ArrayList:<init>	()V
    //   34: astore 4
    //   36: new 50	java/io/FileReader
    //   39: dup
    //   40: ldc_w 461
    //   43: invokespecial 53	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   46: astore 5
    //   48: new 55	java/io/BufferedReader
    //   51: dup
    //   52: aload 5
    //   54: sipush 1024
    //   57: invokespecial 342	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   60: astore 6
    //   62: aload 6
    //   64: invokevirtual 62	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   67: astore 7
    //   69: aload 7
    //   71: ifnull +132 -> 203
    //   74: aload 4
    //   76: aload 7
    //   78: invokeinterface 185 2 0
    //   83: pop
    //   84: goto -22 -> 62
    //   87: astore 7
    //   89: aload 6
    //   91: invokevirtual 76	java/io/BufferedReader:close	()V
    //   94: aload 5
    //   96: invokevirtual 75	java/io/FileReader:close	()V
    //   99: iconst_0
    //   100: istore_0
    //   101: iload_0
    //   102: ifeq +161 -> 263
    //   105: aload 4
    //   107: invokeinterface 464 1 0
    //   112: istore_2
    //   113: iconst_0
    //   114: istore_0
    //   115: iload_0
    //   116: iconst_3
    //   117: if_icmpge +146 -> 263
    //   120: iconst_3
    //   121: anewarray 64	java/lang/String
    //   124: dup
    //   125: iconst_0
    //   126: ldc_w 466
    //   129: aastore
    //   130: dup
    //   131: iconst_1
    //   132: ldc_w 468
    //   135: aastore
    //   136: dup
    //   137: iconst_2
    //   138: ldc_w 470
    //   141: aastore
    //   142: iload_0
    //   143: aaload
    //   144: invokestatic 301	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   147: astore 5
    //   149: iconst_0
    //   150: istore_1
    //   151: iload_1
    //   152: iload_2
    //   153: if_icmpge +103 -> 256
    //   156: aload 5
    //   158: aload 4
    //   160: iload_1
    //   161: invokeinterface 474 2 0
    //   166: checkcast 64	java/lang/String
    //   169: invokevirtual 305	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   172: astore 6
    //   174: aload 6
    //   176: invokevirtual 310	java/util/regex/Matcher:find	()Z
    //   179: ifeq +17 -> 196
    //   182: aload_3
    //   183: iload_0
    //   184: aload 6
    //   186: invokevirtual 314	java/util/regex/Matcher:toMatchResult	()Ljava/util/regex/MatchResult;
    //   189: iconst_1
    //   190: invokeinterface 319 2 0
    //   195: aastore
    //   196: iload_1
    //   197: iconst_1
    //   198: iadd
    //   199: istore_1
    //   200: goto -49 -> 151
    //   203: aload 6
    //   205: invokevirtual 76	java/io/BufferedReader:close	()V
    //   208: aload 5
    //   210: invokevirtual 75	java/io/FileReader:close	()V
    //   213: iload_1
    //   214: istore_0
    //   215: goto -114 -> 101
    //   218: astore 5
    //   220: iload_1
    //   221: istore_0
    //   222: goto -121 -> 101
    //   225: astore 5
    //   227: iconst_0
    //   228: istore_0
    //   229: goto -128 -> 101
    //   232: astore 4
    //   234: aload 6
    //   236: invokevirtual 76	java/io/BufferedReader:close	()V
    //   239: aload 5
    //   241: invokevirtual 75	java/io/FileReader:close	()V
    //   244: aload 4
    //   246: athrow
    //   247: astore 4
    //   249: aload 4
    //   251: invokestatic 191	com/tendcloud/tenddata/cf:postSDKError	(Ljava/lang/Throwable;)V
    //   254: aload_3
    //   255: areturn
    //   256: iload_0
    //   257: iconst_1
    //   258: iadd
    //   259: istore_0
    //   260: goto -145 -> 115
    //   263: aload_3
    //   264: iconst_3
    //   265: ldc 10
    //   267: invokestatic 476	com/tendcloud/tenddata/ak:c	(Ljava/lang/String;)Ljava/lang/String;
    //   270: aastore
    //   271: aload_3
    //   272: areturn
    //   273: astore 5
    //   275: goto -31 -> 244
    // Local variable table:
    //   start	length	slot	name	signature
    //   8	252	0	i	int
    //   1	220	1	j	int
    //   112	42	2	k	int
    //   6	266	3	arrayOfString	String[]
    //   34	125	4	localArrayList	java.util.ArrayList
    //   232	13	4	localObject1	Object
    //   247	3	4	localThrowable1	Throwable
    //   46	163	5	localObject2	Object
    //   218	1	5	localIOException1	IOException
    //   225	15	5	localIOException2	IOException
    //   273	1	5	localIOException3	IOException
    //   60	175	6	localObject3	Object
    //   67	10	7	str	String
    //   87	1	7	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   62	69	87	java/lang/Throwable
    //   74	84	87	java/lang/Throwable
    //   203	213	218	java/io/IOException
    //   89	99	225	java/io/IOException
    //   62	69	232	finally
    //   74	84	232	finally
    //   36	62	247	java/lang/Throwable
    //   89	99	247	java/lang/Throwable
    //   105	113	247	java/lang/Throwable
    //   120	149	247	java/lang/Throwable
    //   156	196	247	java/lang/Throwable
    //   203	213	247	java/lang/Throwable
    //   234	244	247	java/lang/Throwable
    //   244	247	247	java/lang/Throwable
    //   263	271	247	java/lang/Throwable
    //   234	244	273	java/io/IOException
  }
  
  public static int m(Context paramContext)
  {
    int j = -1;
    Context localContext = paramContext;
    int i;
    if (paramContext == null) {
      i = j;
    }
    try
    {
      if (ab.g != null)
      {
        localContext = ab.g;
        paramContext = localContext.getResources().getDisplayMetrics();
        i = j;
        if (paramContext != null) {
          i = paramContext.widthPixels;
        }
      }
      return i;
    }
    catch (Throwable paramContext) {}
    return -1;
  }
  
  public static JSONObject m()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("name", l()[2]);
      localJSONObject.put("coreNum", n());
      localJSONObject.put("maxFreq", a("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"));
      localJSONObject.put("minFreq", a("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"));
      localJSONObject.put("curFreq", a("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"));
      return localJSONObject;
    }
    catch (Throwable localThrowable)
    {
      cf.postSDKError(localThrowable);
    }
    return null;
  }
  
  public static int n()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(e).length;
      return i;
    }
    catch (Throwable localThrowable) {}
    return 1;
  }
  
  public static int n(Context paramContext)
  {
    int j = -1;
    Context localContext = paramContext;
    int i;
    if (paramContext == null) {
      i = j;
    }
    try
    {
      if (ab.g != null)
      {
        localContext = ab.g;
        paramContext = localContext.getResources().getDisplayMetrics();
        i = j;
        if (paramContext != null) {
          i = paramContext.heightPixels;
        }
      }
      return i;
    }
    catch (Throwable paramContext) {}
    return -1;
  }
  
  public static int o(Context paramContext)
  {
    int j = -1;
    Context localContext = paramContext;
    int i;
    if (paramContext == null) {
      i = j;
    }
    try
    {
      if (ab.g != null)
      {
        localContext = ab.g;
        paramContext = localContext.getResources().getDisplayMetrics();
        i = j;
        if (paramContext != null) {
          i = paramContext.densityDpi;
        }
      }
      return i;
    }
    catch (Throwable paramContext) {}
    return -1;
  }
  
  public static String[] o()
  {
    return null;
  }
  
  public static int p(Context paramContext)
  {
    int i = -1;
    Context localContext = paramContext;
    if (paramContext == null)
    {
      if (ab.g == null) {
        break label29;
      }
      localContext = ab.g;
    }
    try
    {
      i = Settings.System.getInt(localContext.getContentResolver(), "screen_brightness");
      label29:
      return i;
    }
    catch (Throwable paramContext) {}
    return -1;
  }
  
  public static int[] p()
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    try
    {
      if ("mounted".equals(Environment.getExternalStorageState()))
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        int i = localStatFs.getBlockSize();
        int j = localStatFs.getBlockCount();
        int k = localStatFs.getAvailableBlocks();
        arrayOfInt[0] = (j * (i / 512) / 2);
        arrayOfInt[1] = (i / 512 * k / 2);
      }
      return arrayOfInt;
    }
    catch (Throwable localThrowable) {}
    return arrayOfInt;
  }
  
  private static JSONArray q(Context paramContext)
  {
    if (!bc.a(19)) {
      return null;
    }
    try
    {
      JSONArray localJSONArray = new JSONArray();
      Object localObject1 = a(paramContext, 4);
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        label163:
        while (((Iterator)localObject1).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
          if (localPackageInfo != null)
          {
            ServiceInfo[] arrayOfServiceInfo = localPackageInfo.services;
            if (arrayOfServiceInfo != null)
            {
              int j = arrayOfServiceInfo.length;
              int i = 0;
              for (;;)
              {
                if (i >= j) {
                  break label163;
                }
                Object localObject2 = arrayOfServiceInfo[i];
                try
                {
                  localObject2 = paramContext.getPackageManager().getServiceInfo(new ComponentName(((ServiceInfo)localObject2).packageName, ((ServiceInfo)localObject2).name), 128).metaData;
                  if ((localObject2 != null) && (((Bundle)localObject2).containsKey("android.nfc.cardemulation.host_apdu_service"))) {
                    localJSONArray.put(localPackageInfo.packageName);
                  }
                }
                catch (Throwable localThrowable)
                {
                  i += 1;
                }
              }
            }
          }
        }
      }
      return localJSONArray;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  /* Error */
  public static int[] q()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: iconst_2
    //   3: newarray int
    //   5: astore_2
    //   6: aload_2
    //   7: iconst_0
    //   8: iconst_0
    //   9: iastore
    //   10: aload_2
    //   11: iconst_1
    //   12: iconst_0
    //   13: iastore
    //   14: iconst_4
    //   15: newarray int
    //   17: astore 5
    //   19: iconst_0
    //   20: istore_0
    //   21: iload_0
    //   22: iconst_4
    //   23: if_icmpge +15 -> 38
    //   26: aload 5
    //   28: iload_0
    //   29: iconst_0
    //   30: iastore
    //   31: iload_0
    //   32: iconst_1
    //   33: iadd
    //   34: istore_0
    //   35: goto -14 -> 21
    //   38: new 50	java/io/FileReader
    //   41: dup
    //   42: ldc_w 604
    //   45: invokespecial 53	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   48: astore_3
    //   49: new 55	java/io/BufferedReader
    //   52: dup
    //   53: aload_3
    //   54: sipush 1024
    //   57: invokespecial 342	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   60: astore 4
    //   62: iload_1
    //   63: istore_0
    //   64: iload_0
    //   65: iconst_4
    //   66: if_icmpge +22 -> 88
    //   69: aload 5
    //   71: iload_0
    //   72: aload 4
    //   74: invokevirtual 62	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   77: invokestatic 606	com/tendcloud/tenddata/ak:b	(Ljava/lang/String;)I
    //   80: iastore
    //   81: iload_0
    //   82: iconst_1
    //   83: iadd
    //   84: istore_0
    //   85: goto -21 -> 64
    //   88: aload_2
    //   89: iconst_0
    //   90: aload 5
    //   92: iconst_0
    //   93: iaload
    //   94: iastore
    //   95: aload 5
    //   97: iconst_1
    //   98: iaload
    //   99: istore_0
    //   100: aload 5
    //   102: iconst_2
    //   103: iaload
    //   104: istore_1
    //   105: aload_2
    //   106: iconst_1
    //   107: aload 5
    //   109: iconst_3
    //   110: iaload
    //   111: iload_0
    //   112: iload_1
    //   113: iadd
    //   114: iadd
    //   115: iastore
    //   116: aload 4
    //   118: invokevirtual 76	java/io/BufferedReader:close	()V
    //   121: aload_3
    //   122: invokevirtual 75	java/io/FileReader:close	()V
    //   125: aload_2
    //   126: areturn
    //   127: astore 5
    //   129: aload 4
    //   131: invokevirtual 76	java/io/BufferedReader:close	()V
    //   134: aload_3
    //   135: invokevirtual 75	java/io/FileReader:close	()V
    //   138: aload_2
    //   139: areturn
    //   140: astore_3
    //   141: aload_2
    //   142: areturn
    //   143: astore 5
    //   145: aload 4
    //   147: invokevirtual 76	java/io/BufferedReader:close	()V
    //   150: aload_3
    //   151: invokevirtual 75	java/io/FileReader:close	()V
    //   154: aload 5
    //   156: athrow
    //   157: astore_3
    //   158: aload_3
    //   159: invokestatic 191	com/tendcloud/tenddata/cf:postSDKError	(Ljava/lang/Throwable;)V
    //   162: aload_2
    //   163: areturn
    //   164: astore_3
    //   165: goto -11 -> 154
    //   168: astore_3
    //   169: aload_2
    //   170: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   20	94	0	i	int
    //   1	113	1	j	int
    //   5	165	2	arrayOfInt1	int[]
    //   48	87	3	localFileReader	FileReader
    //   140	11	3	localIOException1	IOException
    //   157	2	3	localThrowable	Throwable
    //   164	1	3	localIOException2	IOException
    //   168	1	3	localIOException3	IOException
    //   60	86	4	localBufferedReader	BufferedReader
    //   17	91	5	arrayOfInt2	int[]
    //   127	1	5	localIOException4	IOException
    //   143	12	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   69	81	127	java/io/IOException
    //   129	138	140	java/io/IOException
    //   69	81	143	finally
    //   38	62	157	java/lang/Throwable
    //   116	125	157	java/lang/Throwable
    //   129	138	157	java/lang/Throwable
    //   145	154	157	java/lang/Throwable
    //   154	157	157	java/lang/Throwable
    //   145	154	164	java/io/IOException
    //   116	125	168	java/io/IOException
  }
  
  private static int r(Context paramContext)
  {
    try
    {
      if (bc.a(19))
      {
        int i = CardEmulation.getInstance(((NfcManager)paramContext.getSystemService("nfc")).getDefaultAdapter()).getSelectionModeForCategory("payment");
        return i;
      }
    }
    catch (Throwable paramContext)
    {
      cf.postSDKError(paramContext);
    }
    return -1;
  }
  
  public static int[] r()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
      int i = localStatFs.getBlockCount() * (localStatFs.getBlockSize() / 512) / 2;
      int j = localStatFs.getAvailableBlocks();
      j = localStatFs.getBlockSize() / 512 * j / 2;
      localStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
      int k = localStatFs.getBlockCount() * (localStatFs.getBlockSize() / 512) / 2;
      int m = localStatFs.getAvailableBlocks();
      m = localStatFs.getBlockSize() / 512 * m / 2;
      return new int[] { i, j, k, m };
    }
    catch (Throwable localThrowable)
    {
      cf.postSDKError(localThrowable);
    }
    return null;
  }
  
  public static int s()
  {
    int i = 0;
    try
    {
      Object localObject = c("/sys/class/power_supply/battery/full_bat");
      localObject = Pattern.compile("\\s*([0-9]+)").matcher((CharSequence)localObject);
      if (((Matcher)localObject).find()) {
        i = Integer.parseInt(((Matcher)localObject).toMatchResult().group(0));
      }
      return i;
    }
    catch (Exception localException) {}
    return 0;
  }
  
  public static class a
  {
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    
    public a() {}
  }
}
