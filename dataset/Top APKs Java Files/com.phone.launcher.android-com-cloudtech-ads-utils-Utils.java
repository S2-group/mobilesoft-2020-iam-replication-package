package com.cloudtech.ads.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings.Secure;
import android.support.annotation.Keep;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.cloudtech.ads.config.Const;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

public class Utils
{
  private static String a = "";
  private static String b = "";
  private static String c = "";
  private static final AtomicInteger d = new AtomicInteger(1);
  private static boolean e = false;
  private static boolean f = false;
  private static boolean g = false;
  
  public Utils() {}
  
  public static int a(Context paramContext, int paramInt)
  {
    return (int)paramContext.getResources().getDisplayMetrics().density * paramInt;
  }
  
  public static Drawable a(int paramInt)
  {
    return ContextHolder.getGlobalAppContext().getResources().getDrawable(paramInt);
  }
  
  public static String a()
  {
    List localList = b();
    if (localList.size() > 0)
    {
      Log.i("NGP", "getPkgBloomFilter: " + localList.toString());
      return b.a(localList);
    }
    return "";
  }
  
  /* Error */
  @android.annotation.SuppressLint({"MissingPermission"})
  public static String a(Context paramContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: aload_0
    //   3: ifnull +11 -> 14
    //   6: ldc 118
    //   8: invokestatic 121	com/cloudtech/ads/utils/Utils:b	(Ljava/lang/String;)Z
    //   11: ifeq +30 -> 41
    //   14: ldc 123
    //   16: iconst_1
    //   17: anewarray 4	java/lang/Object
    //   20: dup
    //   21: iconst_0
    //   22: ldc 118
    //   24: aastore
    //   25: invokestatic 129	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   28: invokestatic 133	com/cloudtech/ads/utils/YeLog:e	(Ljava/lang/String;)V
    //   31: iconst_0
    //   32: istore_1
    //   33: iload_1
    //   34: ifne +25 -> 59
    //   37: aconst_null
    //   38: astore_3
    //   39: aload_3
    //   40: areturn
    //   41: aload_0
    //   42: ldc 118
    //   44: invokestatic 138	android/os/Process:myPid	()I
    //   47: invokestatic 141	android/os/Process:myUid	()I
    //   50: invokevirtual 145	android/content/Context:checkPermission	(Ljava/lang/String;II)I
    //   53: ifne -22 -> 31
    //   56: goto -23 -> 33
    //   59: aload_0
    //   60: ldc -109
    //   62: invokevirtual 151	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   65: checkcast 153	android/telephony/TelephonyManager
    //   68: astore_2
    //   69: aload_2
    //   70: ifnull +61 -> 131
    //   73: aload_2
    //   74: invokevirtual 156	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   77: astore_2
    //   78: aload_2
    //   79: astore_3
    //   80: aload_2
    //   81: invokestatic 162	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   84: ifeq -45 -> 39
    //   87: aload_0
    //   88: invokevirtual 166	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   91: ldc -88
    //   93: invokestatic 174	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   96: astore_0
    //   97: aload_0
    //   98: areturn
    //   99: astore_0
    //   100: ldc 20
    //   102: astore_2
    //   103: new 86	java/lang/StringBuilder
    //   106: dup
    //   107: ldc -80
    //   109: invokespecial 91	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   112: aload_0
    //   113: invokevirtual 179	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   116: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   122: invokestatic 133	com/cloudtech/ads/utils/YeLog:e	(Ljava/lang/String;)V
    //   125: aload_2
    //   126: areturn
    //   127: astore_0
    //   128: goto -25 -> 103
    //   131: ldc 20
    //   133: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	paramContext	Context
    //   1	33	1	i	int
    //   68	58	2	localObject1	Object
    //   38	42	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   59	69	99	java/lang/Exception
    //   73	78	99	java/lang/Exception
    //   80	97	127	java/lang/Exception
  }
  
  /* Error */
  private static ArrayList<String> a(String[] paramArrayOfString)
  {
    // Byte code:
    //   0: new 183	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 184	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: ldc -70
    //   10: ldc -68
    //   12: invokestatic 191	com/cloudtech/ads/utils/YeLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   15: invokestatic 197	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   18: aload_0
    //   19: invokevirtual 201	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   22: astore_0
    //   23: new 203	java/io/BufferedWriter
    //   26: dup
    //   27: new 205	java/io/OutputStreamWriter
    //   30: dup
    //   31: aload_0
    //   32: invokevirtual 211	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   35: invokespecial 214	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   38: invokespecial 217	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   41: pop
    //   42: new 219	java/io/BufferedReader
    //   45: dup
    //   46: new 221	java/io/InputStreamReader
    //   49: dup
    //   50: aload_0
    //   51: invokevirtual 225	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   54: invokespecial 228	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   57: invokespecial 231	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   60: astore_0
    //   61: aload_0
    //   62: invokevirtual 234	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   65: astore_2
    //   66: aload_2
    //   67: ifnull +38 -> 105
    //   70: ldc -70
    //   72: new 86	java/lang/StringBuilder
    //   75: dup
    //   76: ldc -20
    //   78: invokespecial 91	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   81: aload_2
    //   82: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: invokestatic 191	com/cloudtech/ads/utils/YeLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   91: aload_1
    //   92: aload_2
    //   93: invokevirtual 240	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   96: pop
    //   97: goto -36 -> 61
    //   100: astore_0
    //   101: aload_0
    //   102: invokevirtual 243	java/lang/Exception:printStackTrace	()V
    //   105: ldc -70
    //   107: new 86	java/lang/StringBuilder
    //   110: dup
    //   111: ldc -11
    //   113: invokespecial 91	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   116: aload_1
    //   117: invokevirtual 248	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   120: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   123: invokestatic 191	com/cloudtech/ads/utils/YeLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   126: aload_1
    //   127: areturn
    //   128: astore_0
    //   129: aconst_null
    //   130: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	paramArrayOfString	String[]
    //   7	120	1	localArrayList	ArrayList
    //   65	28	2	str	String
    // Exception table:
    //   from	to	target	type
    //   61	66	100	java/lang/Exception
    //   70	97	100	java/lang/Exception
    //   8	23	128	java/lang/Exception
  }
  
  public static List<String> a(List<String> paramList)
  {
    return a(paramList, false, false);
  }
  
  public static List<String> a(List<String> paramList, boolean paramBoolean1, boolean paramBoolean2)
  {
    ArrayList localArrayList = new ArrayList();
    long l2 = System.currentTimeMillis();
    long l1 = l2;
    if (paramBoolean1)
    {
      YeLog.d("isNormal::" + paramBoolean2);
      if (!paramBoolean2) {
        break label145;
      }
      if (l2 % 1000L / 100L % 2L != 0L) {
        break label139;
      }
    }
    label139:
    for (int i = 0;; i = 100)
    {
      l1 = i + l2;
      i = 0;
      while (i < paramList.size())
      {
        String str = (String)paramList.get(i);
        localArrayList.add(str + "&ts=" + l1);
        i += 1;
      }
    }
    label145:
    if (l2 % 1000L / 100L % 2L == 0L) {}
    for (i = 100;; i = 0)
    {
      l1 = i + l2;
      break;
    }
    return localArrayList;
  }
  
  public static void a(View paramView)
  {
    if (paramView == null) {}
    do
    {
      return;
      paramView = paramView.getParent();
    } while ((paramView == null) || ((paramView instanceof AdapterView)));
    ((ViewGroup)paramView).removeAllViews();
  }
  
  private static void a(ViewGroup paramViewGroup, List<View> paramList)
  {
    int i = 0;
    while (i < paramViewGroup.getChildCount())
    {
      View localView = paramViewGroup.getChildAt(i);
      paramList.add(localView);
      if ((localView instanceof ViewGroup)) {
        a((ViewGroup)localView, paramList);
      }
      i += 1;
    }
  }
  
  public static void a(StringBuilder paramStringBuilder, Map<String, String> paramMap, boolean paramBoolean)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = (String)paramMap.get(str1);
      if ((!b(str1)) && (!b(str2)) && (!str2.equals("null")))
      {
        if (paramBoolean)
        {
          paramBoolean = false;
          paramStringBuilder.append("?");
        }
        for (;;)
        {
          paramStringBuilder.append(d(str1));
          paramStringBuilder.append("=");
          paramStringBuilder.append(d(str2));
          break;
          paramStringBuilder.append("&");
        }
      }
    }
  }
  
  public static boolean a(String paramString)
  {
    return (paramString != null) && (!"".equals(paramString));
  }
  
  @Keep
  public static void appendUrlParameter(StringBuilder paramStringBuilder, Map<String, String> paramMap)
  {
    a(paramStringBuilder, paramMap, true);
  }
  
  public static int b(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, ContextHolder.getGlobalAppContext().getResources().getDisplayMetrics());
  }
  
  public static String b(List<String> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (!b(str))
      {
        localStringBuilder.append(str);
        localStringBuilder.append(",");
      }
    }
    return localStringBuilder.substring(0, localStringBuilder.length() - 1);
  }
  
  public static List<String> b()
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = new File(Const.e).list(new FilenameFilter()
    {
      public final boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return (paramAnonymousString.startsWith("CM_")) && (h.c(paramAnonymousString));
      }
    });
    if ((arrayOfString != null) && (arrayOfString.length > 0))
    {
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        localArrayList.add(arrayOfString[i].substring(3));
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public static List<View> b(View paramView)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramView);
    if ((paramView instanceof ViewGroup)) {
      a((ViewGroup)paramView, localArrayList);
    }
    return localArrayList;
  }
  
  public static boolean b(String paramString)
  {
    return (paramString == null) || ("".equals(paramString));
  }
  
  public static int[] b(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    if (paramContext == null) {
      return new int[] { 0, 0 };
    }
    return new int[] { paramContext.widthPixels, paramContext.heightPixels };
  }
  
  public static BitmapDrawable c()
  {
    Object localObject = Base64.decode("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", 0);
    localObject = BitmapFactory.decodeByteArray((byte[])localObject, 0, localObject.length);
    if (localObject == null) {
      return null;
    }
    return new BitmapDrawable((Bitmap)localObject);
  }
  
  public static void c(String paramString)
  {
    paramString = new File(Const.e + "CM_" + paramString);
    if (paramString.exists()) {
      paramString.delete();
    }
  }
  
  public static boolean c(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo("com.android.vending", 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  @Keep
  public static Context checkAndSaveContext(Context paramContext)
  {
    ContextHolder.init(paramContext);
    return ContextHolder.getGlobalAppContext();
  }
  
  public static String d(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkCountryIso();
  }
  
  private static String d(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new UnsupportedOperationException(paramString);
    }
  }
  
  private static boolean d()
  {
    try
    {
      if (new File("/system/app/Superuser.apk").exists())
      {
        YeLog.i("Utils", "/system/app/Superuser.apk exist");
        return true;
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static int e(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
    {
      paramContext = localConnectivityManager.getActiveNetworkInfo();
      if (paramContext != null) {
        return paramContext.getType();
      }
      return 8;
    }
    return 8;
  }
  
  private static boolean e()
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "/system/bin/";
    arrayOfString[1] = "/system/xbin/";
    arrayOfString[2] = "/system/sbin/";
    arrayOfString[3] = "/sbin/";
    arrayOfString[4] = "/vendor/bin/";
    int i = 0;
    for (;;)
    {
      if (i < 5) {
        try
        {
          if (new File(arrayOfString[i] + "su").exists())
          {
            YeLog.i("Utils", "find su in : " + arrayOfString[i]);
            return true;
          }
          i += 1;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
    return false;
  }
  
  public static String f(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(a))
        {
          paramContext = a;
          return paramContext;
        }
        paramContext = m(paramContext);
        if ((paramContext == null) || (TextUtils.isEmpty(paramContext))) {
          break label92;
        }
        if (!paramContext.equals("null")) {
          break label63;
        }
      }
      finally {}
      a = paramContext;
      continue;
      label63:
      int i = Math.min(3, paramContext.length());
      if (paramContext == null)
      {
        paramContext = "";
      }
      else
      {
        paramContext = paramContext.substring(0, i);
        continue;
        label92:
        paramContext = "";
      }
    }
  }
  
  public static String g(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(b))
        {
          paramContext = b;
          return paramContext;
        }
        paramContext = m(paramContext);
        if ((paramContext == null) || (TextUtils.isEmpty(paramContext))) {
          break label91;
        }
        if (!paramContext.equals("null")) {
          break label63;
        }
      }
      finally {}
      b = paramContext;
      continue;
      label63:
      int i = Math.min(3, paramContext.length());
      if (paramContext == null)
      {
        paramContext = "";
      }
      else
      {
        paramContext = paramContext.substring(i);
        continue;
        label91:
        paramContext = "";
      }
    }
  }
  
  @Keep
  public static int generateViewId()
  {
    int k;
    int i;
    do
    {
      k = d.get();
      int j = k + 1;
      i = j;
      if (j > 16777215) {
        i = 1;
      }
    } while (!d.compareAndSet(k, i));
    return k;
  }
  
  @Keep
  public static String getAndroidId(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      YeLog.e(String.format("[msg=get AndroidId][result=fail]", new Object[0]));
    }
    return "";
  }
  
  @Keep
  public static String getAppPackageName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).packageName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      YeLog.e("工具-应用包名 Error=" + paramContext.getMessage());
    }
    return "";
  }
  
  @Keep
  public static String getProcessName(Context paramContext, int paramInt)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.pid == paramInt) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return null;
  }
  
  public static String h(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }
  
  public static List<String> i(Context paramContext)
  {
    localArrayList = new ArrayList();
    YeLog.d("getInstalledApps::");
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if ((localApplicationInfo.flags & 0x1) == 0) {
          localArrayList.add(localApplicationInfo.packageName);
        }
      }
      return localArrayList;
    }
    catch (Throwable paramContext)
    {
      YeLog.d("getInstalledApps::" + paramContext.getMessage());
    }
  }
  
  @Keep
  public static boolean isNetEnable(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnected();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      YeLog.e("工具-NetIsOn Error=" + paramContext.getMessage());
    }
    return false;
  }
  
  public static String j(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Intent localIntent = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    int i;
    String str;
    label49:
    int j;
    if (localIntent.getIntExtra("status", -1) == 2)
    {
      i = 1;
      if (i == 0) {
        break label169;
      }
      str = "1";
      localStringBuilder.append(str);
      localStringBuilder.append(",");
      localStringBuilder.append(String.valueOf(localIntent.getIntExtra("level", -1)));
      localStringBuilder.append(",");
      j = localIntent.getIntExtra("plugged", -1);
      if (j != 2) {
        break label176;
      }
      i = 1;
      label109:
      if (j != 1) {
        break label181;
      }
      j = 1;
      label116:
      if (i == 0) {
        break label186;
      }
      str = "1";
    }
    for (;;)
    {
      label124:
      localStringBuilder.append(str);
      localStringBuilder.append(",");
      if (n(paramContext)) {}
      for (paramContext = "0";; paramContext = "1")
      {
        localStringBuilder.append(paramContext);
        return localStringBuilder.toString();
        i = 0;
        break;
        label169:
        str = "0";
        break label49;
        label176:
        i = 0;
        break label109;
        label181:
        j = 0;
        break label116;
        label186:
        if (j == 0) {
          break label204;
        }
        str = "2";
        break label124;
      }
      label204:
      str = "0";
    }
  }
  
  public static int k(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Throwable paramContext)
    {
      YeLog.e(paramContext.getMessage());
    }
    return Integer.MAX_VALUE;
  }
  
  public static int l(Context paramContext)
  {
    int m = 0;
    if (o(paramContext)) {}
    for (int j = 4;; j = 0)
    {
      int i;
      if (g) {
        i = 1;
      }
      for (;;)
      {
        k = j;
        if (i != 0) {
          k = j | 0x2;
        }
        i = k;
        if (p(paramContext)) {
          i = k | 0x1;
        }
        return i;
        localObject = Build.TAGS;
        if ((localObject != null) && (((String)localObject).contains("test-keys"))) {
          YeLog.i("Utils", "buildTags=" + (String)localObject);
        }
        for (i = 1;; i = 0)
        {
          if (i == 0) {
            break label105;
          }
          i = 1;
          break;
        }
        label105:
        if (d())
        {
          i = 1;
        }
        else
        {
          if (!e()) {
            break;
          }
          i = 1;
        }
      }
      Object localObject = a(new String[] { "/system/xbin/which", "su" });
      if (localObject != null) {
        YeLog.i("Utils", "execResult=" + ((ArrayList)localObject).toString());
      }
      for (int k = 1;; k = 0)
      {
        i = m;
        if (k == 0) {
          break;
        }
        i = 1;
        break;
        YeLog.i("Utils", "execResult=null");
      }
    }
  }
  
  private static String m(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    String str = paramContext.getNetworkOperator();
    if ((paramContext.getPhoneType() == 2) && (paramContext.getSimState() == 5)) {
      return paramContext.getSimOperator();
    }
    return str;
  }
  
  private static boolean n(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getApplicationInfo().flags;
      if ((i & 0x2) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      YeLog.e(paramContext.getMessage());
    }
    return false;
  }
  
  private static boolean o(Context paramContext)
  {
    Object localObject;
    if (!e) {
      localObject = null;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      if ((paramContext.applicationInfo.flags & 0x1) != 0)
      {
        bool = true;
        e = bool;
        return e;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
        continue;
        boolean bool = false;
      }
    }
  }
  
  @Keep
  public static List<String> optStringArrayHelper(JSONObject paramJSONObject, String... paramVarArgs)
  {
    int j = 0;
    if (paramJSONObject == null)
    {
      paramJSONObject = Collections.EMPTY_LIST;
      return paramJSONObject;
    }
    int i = 0;
    while (i < paramVarArgs.length - 1)
    {
      paramJSONObject = paramJSONObject.optJSONObject(paramVarArgs[i]);
      if (paramJSONObject == null) {
        return Collections.EMPTY_LIST;
      }
      i += 1;
    }
    if (paramJSONObject == null) {
      return Collections.EMPTY_LIST;
    }
    JSONArray localJSONArray = paramJSONObject.optJSONArray(paramVarArgs[(paramVarArgs.length - 1)]);
    if (localJSONArray == null) {
      return Collections.EMPTY_LIST;
    }
    paramVarArgs = new ArrayList();
    i = j;
    for (;;)
    {
      paramJSONObject = paramVarArgs;
      if (i >= localJSONArray.length()) {
        break;
      }
      paramVarArgs.add(localJSONArray.optString(i));
      i += 1;
    }
  }
  
  @Keep
  public static String optStringHelper(JSONObject paramJSONObject, String... paramVarArgs)
  {
    if (paramJSONObject == null) {
      paramJSONObject = null;
    }
    for (;;)
    {
      paramVarArgs = paramJSONObject;
      if (paramJSONObject == null) {
        paramVarArgs = "";
      }
      return paramVarArgs;
      int i = 0;
      for (;;)
      {
        if (i >= paramVarArgs.length - 1) {
          break label51;
        }
        paramJSONObject = paramJSONObject.optJSONObject(paramVarArgs[i]);
        if (paramJSONObject == null)
        {
          paramJSONObject = null;
          break;
        }
        i += 1;
      }
      label51:
      if (paramJSONObject == null) {
        paramJSONObject = null;
      } else {
        paramJSONObject = paramJSONObject.optString(paramVarArgs[(paramVarArgs.length - 1)]);
      }
    }
  }
  
  private static boolean p(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1;
    if (f) {
      bool1 = true;
    }
    String str;
    do
    {
      return bool1;
      str = paramContext.getPackageName();
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_notification_listeners");
      bool1 = bool2;
    } while (TextUtils.isEmpty(paramContext));
    paramContext = paramContext.split(":");
    int i = 0;
    for (;;)
    {
      bool1 = bool2;
      if (i >= paramContext.length) {
        break;
      }
      ComponentName localComponentName = ComponentName.unflattenFromString(paramContext[i]);
      if ((localComponentName != null) && (TextUtils.equals(str, localComponentName.getPackageName()))) {
        return true;
      }
      i += 1;
    }
  }
  
  @Keep
  public static String stream2String(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    paramInputStream = localByteArrayOutputStream.toString();
    localByteArrayOutputStream.close();
    return paramInputStream;
  }
}
