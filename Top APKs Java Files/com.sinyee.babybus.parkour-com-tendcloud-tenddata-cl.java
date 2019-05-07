package com.tendcloud.tenddata;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.cardemulation.CardEmulation;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.io.BufferedReader;
import java.io.File;
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

public class cl
{
  private static final int a = 3600000;
  
  public cl() {}
  
  private static int a(String paramString)
  {
    String str = "";
    try
    {
      Matcher localMatcher = Pattern.compile("([0-9]+)").matcher(paramString);
      paramString = str;
      if (localMatcher.find()) {
        paramString = localMatcher.toMatchResult().group(0);
      }
      int i = Integer.valueOf(paramString).intValue();
      return i;
    }
    catch (Exception paramString) {}
    return 0;
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
    //   1: invokevirtual 123	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_3
    //   5: aload_3
    //   6: iload_1
    //   7: invokevirtual 129	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   10: astore_0
    //   11: aload_0
    //   12: areturn
    //   13: astore_0
    //   14: new 131	java/util/ArrayList
    //   17: dup
    //   18: invokespecial 132	java/util/ArrayList:<init>	()V
    //   21: astore 4
    //   23: aconst_null
    //   24: astore_0
    //   25: invokestatic 138	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   28: ldc -116
    //   30: invokevirtual 144	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   33: astore 5
    //   35: new 146	java/io/BufferedReader
    //   38: dup
    //   39: new 148	java/io/InputStreamReader
    //   42: dup
    //   43: aload 5
    //   45: invokevirtual 154	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   48: invokespecial 157	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   51: invokespecial 160	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   54: astore_2
    //   55: aload_2
    //   56: astore_0
    //   57: aload_2
    //   58: invokevirtual 163	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   61: astore 6
    //   63: aload 6
    //   65: ifnull +64 -> 129
    //   68: aload_2
    //   69: astore_0
    //   70: aload 4
    //   72: aload_3
    //   73: aload 6
    //   75: aload 6
    //   77: bipush 58
    //   79: invokevirtual 167	java/lang/String:indexOf	(I)I
    //   82: iconst_1
    //   83: iadd
    //   84: invokevirtual 170	java/lang/String:substring	(I)Ljava/lang/String;
    //   87: iload_1
    //   88: invokevirtual 174	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   91: invokeinterface 180 2 0
    //   96: pop
    //   97: goto -42 -> 55
    //   100: astore_3
    //   101: aload_2
    //   102: astore_0
    //   103: aload_3
    //   104: invokevirtual 183	java/lang/Throwable:printStackTrace	()V
    //   107: aload 4
    //   109: astore_0
    //   110: aload_2
    //   111: ifnull -100 -> 11
    //   114: aload_2
    //   115: invokevirtual 186	java/io/BufferedReader:close	()V
    //   118: aload 4
    //   120: areturn
    //   121: astore_0
    //   122: aload_0
    //   123: invokevirtual 183	java/lang/Throwable:printStackTrace	()V
    //   126: aload 4
    //   128: areturn
    //   129: aload_2
    //   130: astore_0
    //   131: aload 5
    //   133: invokevirtual 189	java/lang/Process:waitFor	()I
    //   136: pop
    //   137: aload 4
    //   139: astore_0
    //   140: aload_2
    //   141: ifnull -130 -> 11
    //   144: aload_2
    //   145: invokevirtual 186	java/io/BufferedReader:close	()V
    //   148: aload 4
    //   150: areturn
    //   151: astore_0
    //   152: aload_0
    //   153: invokevirtual 183	java/lang/Throwable:printStackTrace	()V
    //   156: aload 4
    //   158: areturn
    //   159: astore_3
    //   160: aload_0
    //   161: astore_2
    //   162: aload_3
    //   163: astore_0
    //   164: aload_2
    //   165: ifnull +7 -> 172
    //   168: aload_2
    //   169: invokevirtual 186	java/io/BufferedReader:close	()V
    //   172: aload_0
    //   173: athrow
    //   174: astore_2
    //   175: aload_2
    //   176: invokevirtual 183	java/lang/Throwable:printStackTrace	()V
    //   179: goto -7 -> 172
    //   182: astore_3
    //   183: aload_0
    //   184: astore_2
    //   185: aload_3
    //   186: astore_0
    //   187: goto -23 -> 164
    //   190: astore_3
    //   191: aconst_null
    //   192: astore_2
    //   193: goto -92 -> 101
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	196	0	paramContext	Context
    //   0	196	1	paramInt	int
    //   54	115	2	localObject1	Object
    //   174	2	2	localThrowable1	Throwable
    //   184	9	2	localContext	Context
    //   4	69	3	localPackageManager	PackageManager
    //   100	4	3	localThrowable2	Throwable
    //   159	4	3	localObject2	Object
    //   182	4	3	localObject3	Object
    //   190	1	3	localThrowable3	Throwable
    //   21	136	4	localArrayList	java.util.ArrayList
    //   33	99	5	localProcess	Process
    //   61	15	6	str	String
    // Exception table:
    //   from	to	target	type
    //   5	11	13	java/lang/Throwable
    //   57	63	100	java/lang/Throwable
    //   70	97	100	java/lang/Throwable
    //   131	137	100	java/lang/Throwable
    //   114	118	121	java/lang/Throwable
    //   144	148	151	java/lang/Throwable
    //   25	55	159	finally
    //   168	172	174	java/lang/Throwable
    //   57	63	182	finally
    //   70	97	182	finally
    //   103	107	182	finally
    //   131	137	182	finally
    //   25	55	190	java/lang/Throwable
  }
  
  public static JSONObject a(Context paramContext)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("nfc-status", b(paramContext));
      localJSONObject.put("appsRegistedHCE", d(paramContext));
      localJSONObject.put("ssMode", e(paramContext));
      return localJSONObject;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static int b(Context paramContext)
  {
    if (paramContext == null) {
      return 0;
    }
    NfcAdapter localNfcAdapter = ((NfcManager)paramContext.getSystemService("nfc")).getDefaultAdapter();
    int i;
    if (localNfcAdapter != null) {
      if (!localNfcAdapter.isEnabled()) {
        i = 1;
      }
    }
    for (;;)
    {
      return i;
      if ((da.a(19)) && (paramContext.getPackageManager().hasSystemFeature("android.hardware.nfc.hce")))
      {
        i = 3;
      }
      else
      {
        i = 2;
        continue;
        i = 0;
      }
    }
  }
  
  public static String b()
  {
    return Build.MANUFACTURER.trim();
  }
  
  private static String b(String paramString)
  {
    BufferedReader localBufferedReader = null;
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject1 = localBufferedReader;
    try
    {
      FileReader localFileReader = new FileReader(paramString);
      localObject1 = localBufferedReader;
      Object localObject2 = localObject4;
      char[] arrayOfChar;
      int i;
      return localObject2;
    }
    catch (Throwable paramString)
    {
      try
      {
        arrayOfChar = new char['Ѐ'];
        localObject1 = localBufferedReader;
        localObject2 = localObject4;
        localBufferedReader = new BufferedReader(localFileReader, 1024);
        for (paramString = localObject3;; paramString = paramString + new String(arrayOfChar, 0, i))
        {
          localObject1 = paramString;
          localObject2 = paramString;
          i = localBufferedReader.read(arrayOfChar, 0, 1024);
          if (-1 == i) {
            break;
          }
          localObject1 = paramString;
          localObject2 = paramString;
        }
        localObject1 = paramString;
        localObject2 = paramString;
        localBufferedReader.close();
        localObject1 = paramString;
        localObject2 = paramString;
        localFileReader.close();
        return paramString;
      }
      catch (IOException paramString) {}
      paramString = paramString;
      return localObject1;
    }
  }
  
  public static String c()
  {
    return Build.BRAND.trim();
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
  
  public static String d()
  {
    return Build.MODEL.trim();
  }
  
  private static JSONArray d(Context paramContext)
  {
    if (!da.a(19)) {
      return null;
    }
    try
    {
      JSONArray localJSONArray = new JSONArray();
      paramContext = a(paramContext, 4).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        ServiceInfo[] arrayOfServiceInfo = localPackageInfo.services;
        if (arrayOfServiceInfo != null)
        {
          int j = arrayOfServiceInfo.length;
          int i = 0;
          while (i < j)
          {
            Bundle localBundle = arrayOfServiceInfo[i].metaData;
            if ((localBundle != null) && (localBundle.containsKey("android.nfc.cardemulation.host_apdu_service"))) {
              localJSONArray.put(localPackageInfo.packageName);
            }
            i += 1;
          }
        }
      }
      return localJSONArray;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static int e()
  {
    return TimeZone.getDefault().getRawOffset() / 3600000;
  }
  
  private static int e(Context paramContext)
  {
    try
    {
      if (da.a(19))
      {
        int i = CardEmulation.getInstance(((NfcManager)paramContext.getSystemService("nfc")).getDefaultAdapter()).getSelectionModeForCategory("payment");
        return i;
      }
    }
    catch (Throwable paramContext)
    {
      return -1;
    }
    return -1;
  }
  
  public static String f()
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
  
  public static int g()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String h()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String i()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static String j()
  {
    return Locale.getDefault().getCountry();
  }
  
  /* Error */
  public static String[] k()
  {
    // Byte code:
    //   0: iconst_4
    //   1: anewarray 81	java/lang/String
    //   4: astore_3
    //   5: iconst_0
    //   6: istore_0
    //   7: iload_0
    //   8: iconst_4
    //   9: if_icmpge +15 -> 24
    //   12: aload_3
    //   13: iload_0
    //   14: ldc 19
    //   16: aastore
    //   17: iload_0
    //   18: iconst_1
    //   19: iadd
    //   20: istore_0
    //   21: goto -14 -> 7
    //   24: new 131	java/util/ArrayList
    //   27: dup
    //   28: invokespecial 132	java/util/ArrayList:<init>	()V
    //   31: astore 4
    //   33: new 257	java/io/FileReader
    //   36: dup
    //   37: ldc_w 410
    //   40: invokespecial 260	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   43: astore 5
    //   45: new 146	java/io/BufferedReader
    //   48: dup
    //   49: aload 5
    //   51: sipush 1024
    //   54: invokespecial 263	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   57: astore 6
    //   59: aload 6
    //   61: invokevirtual 163	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   64: astore 7
    //   66: aload 7
    //   68: ifnull +132 -> 200
    //   71: aload 4
    //   73: aload 7
    //   75: invokeinterface 180 2 0
    //   80: pop
    //   81: goto -22 -> 59
    //   84: astore 7
    //   86: aload 6
    //   88: invokevirtual 186	java/io/BufferedReader:close	()V
    //   91: aload 5
    //   93: invokevirtual 271	java/io/FileReader:close	()V
    //   96: iconst_0
    //   97: istore_0
    //   98: iload_0
    //   99: ifeq +159 -> 258
    //   102: aload 4
    //   104: invokeinterface 413 1 0
    //   109: istore_2
    //   110: iconst_0
    //   111: istore_0
    //   112: iload_0
    //   113: iconst_3
    //   114: if_icmpge +144 -> 258
    //   117: iconst_3
    //   118: anewarray 81	java/lang/String
    //   121: dup
    //   122: iconst_0
    //   123: ldc_w 415
    //   126: aastore
    //   127: dup
    //   128: iconst_1
    //   129: ldc_w 417
    //   132: aastore
    //   133: dup
    //   134: iconst_2
    //   135: ldc_w 419
    //   138: aastore
    //   139: iload_0
    //   140: aaload
    //   141: invokestatic 27	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   144: astore 5
    //   146: iconst_0
    //   147: istore_1
    //   148: iload_1
    //   149: iload_2
    //   150: if_icmpge +101 -> 251
    //   153: aload 5
    //   155: aload 4
    //   157: iload_1
    //   158: invokeinterface 423 2 0
    //   163: checkcast 81	java/lang/String
    //   166: invokevirtual 31	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   169: astore 6
    //   171: aload 6
    //   173: invokevirtual 37	java/util/regex/Matcher:find	()Z
    //   176: ifeq +17 -> 193
    //   179: aload_3
    //   180: iload_0
    //   181: aload 6
    //   183: invokevirtual 41	java/util/regex/Matcher:toMatchResult	()Ljava/util/regex/MatchResult;
    //   186: iconst_1
    //   187: invokeinterface 47 2 0
    //   192: aastore
    //   193: iload_1
    //   194: iconst_1
    //   195: iadd
    //   196: istore_1
    //   197: goto -49 -> 148
    //   200: aload 6
    //   202: invokevirtual 186	java/io/BufferedReader:close	()V
    //   205: aload 5
    //   207: invokevirtual 271	java/io/FileReader:close	()V
    //   210: iconst_1
    //   211: istore_0
    //   212: goto -114 -> 98
    //   215: astore 5
    //   217: iconst_1
    //   218: istore_0
    //   219: goto -121 -> 98
    //   222: astore 5
    //   224: iconst_0
    //   225: istore_0
    //   226: goto -128 -> 98
    //   229: astore 7
    //   231: aload 6
    //   233: invokevirtual 186	java/io/BufferedReader:close	()V
    //   236: aload 5
    //   238: invokevirtual 271	java/io/FileReader:close	()V
    //   241: aload 7
    //   243: athrow
    //   244: astore 5
    //   246: iconst_0
    //   247: istore_0
    //   248: goto -150 -> 98
    //   251: iload_0
    //   252: iconst_1
    //   253: iadd
    //   254: istore_0
    //   255: goto -143 -> 112
    //   258: aload_3
    //   259: iconst_3
    //   260: ldc_w 425
    //   263: invokestatic 427	com/tendcloud/tenddata/cl:b	(Ljava/lang/String;)Ljava/lang/String;
    //   266: aastore
    //   267: aload_3
    //   268: areturn
    //   269: astore 5
    //   271: iconst_1
    //   272: istore_0
    //   273: goto -175 -> 98
    //   276: astore 5
    //   278: goto -37 -> 241
    // Local variable table:
    //   start	length	slot	name	signature
    //   6	267	0	i	int
    //   147	50	1	j	int
    //   109	42	2	k	int
    //   4	264	3	arrayOfString	String[]
    //   31	125	4	localArrayList	java.util.ArrayList
    //   43	163	5	localObject1	Object
    //   215	1	5	localIOException1	IOException
    //   222	15	5	localIOException2	IOException
    //   244	1	5	localThrowable1	Throwable
    //   269	1	5	localThrowable2	Throwable
    //   276	1	5	localIOException3	IOException
    //   57	175	6	localObject2	Object
    //   64	10	7	str	String
    //   84	1	7	localThrowable3	Throwable
    //   229	13	7	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   59	66	84	java/lang/Throwable
    //   71	81	84	java/lang/Throwable
    //   200	210	215	java/io/IOException
    //   86	96	222	java/io/IOException
    //   59	66	229	finally
    //   71	81	229	finally
    //   33	59	244	java/lang/Throwable
    //   86	96	244	java/lang/Throwable
    //   231	241	244	java/lang/Throwable
    //   241	244	244	java/lang/Throwable
    //   200	210	269	java/lang/Throwable
    //   231	241	276	java/io/IOException
  }
  
  public static String[] l()
  {
    return null;
  }
  
  /* Error */
  public static int[] m()
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
    //   38: new 257	java/io/FileReader
    //   41: dup
    //   42: ldc_w 432
    //   45: invokespecial 260	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   48: astore_3
    //   49: new 146	java/io/BufferedReader
    //   52: dup
    //   53: aload_3
    //   54: sipush 1024
    //   57: invokespecial 263	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   60: astore 4
    //   62: iload_1
    //   63: istore_0
    //   64: iload_0
    //   65: iconst_4
    //   66: if_icmpge +22 -> 88
    //   69: aload 5
    //   71: iload_0
    //   72: aload 4
    //   74: invokevirtual 163	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   77: invokestatic 434	com/tendcloud/tenddata/cl:a	(Ljava/lang/String;)I
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
    //   118: invokevirtual 186	java/io/BufferedReader:close	()V
    //   121: aload_3
    //   122: invokevirtual 271	java/io/FileReader:close	()V
    //   125: aload_2
    //   126: areturn
    //   127: astore 5
    //   129: aload 4
    //   131: invokevirtual 186	java/io/BufferedReader:close	()V
    //   134: aload_3
    //   135: invokevirtual 271	java/io/FileReader:close	()V
    //   138: aload_2
    //   139: areturn
    //   140: astore_3
    //   141: aload_2
    //   142: areturn
    //   143: astore 5
    //   145: aload 4
    //   147: invokevirtual 186	java/io/BufferedReader:close	()V
    //   150: aload_3
    //   151: invokevirtual 271	java/io/FileReader:close	()V
    //   154: aload 5
    //   156: athrow
    //   157: astore_3
    //   158: aload_2
    //   159: areturn
    //   160: astore_3
    //   161: goto -7 -> 154
    //   164: astore_3
    //   165: aload_2
    //   166: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   20	94	0	i	int
    //   1	113	1	j	int
    //   5	161	2	arrayOfInt1	int[]
    //   48	87	3	localFileReader	FileReader
    //   140	11	3	localIOException1	IOException
    //   157	1	3	localThrowable	Throwable
    //   160	1	3	localIOException2	IOException
    //   164	1	3	localIOException3	IOException
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
    //   145	154	160	java/io/IOException
    //   116	125	164	java/io/IOException
  }
  
  public static int[] n()
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
    catch (Throwable localThrowable) {}
    return null;
  }
  
  public static int o()
  {
    int i = 0;
    try
    {
      Object localObject = b("/sys/class/power_supply/battery/full_bat");
      localObject = Pattern.compile("\\s*([0-9]+)").matcher((CharSequence)localObject);
      if (((Matcher)localObject).find()) {
        i = Integer.valueOf(((Matcher)localObject).toMatchResult().group(0)).intValue();
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
