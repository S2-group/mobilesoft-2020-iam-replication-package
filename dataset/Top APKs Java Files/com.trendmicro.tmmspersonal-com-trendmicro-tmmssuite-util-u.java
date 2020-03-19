package com.trendmicro.tmmssuite.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.KeyguardManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.app.usage.UsageStats;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.StatFs;
import android.provider.DocumentsContract;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.provider.DocumentFile;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.b.a.a.a;
import com.trendmicro.optimizer.ui.MemoryShortCutActivity;
import com.trendmicro.tmmssuite.antimalware.d.e;
import com.trendmicro.tmmssuite.consumer.antispam.h;
import com.trendmicro.tmmssuite.consumer.main.ui.TmmsSuiteComMainEntry;
import com.trendmicro.tmmssuite.core.sys.b;
import com.trendmicro.tmmssuite.core.sys.c;
import com.trendmicro.tmmssuite.core.util.i;
import com.trendmicro.tmmssuite.service.PreferenceHelper;
import com.trendmicro.tmmssuite.wtp.browseroper.f;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class u
{
  public static int a;
  
  static
  {
    if ((Build.VERSION.SDK_INT >= 23) && (!h())) {}
    for (int i = 2131624129;; i = 2131624039)
    {
      a = i;
      return;
    }
  }
  
  public static double a(double paramDouble1, double paramDouble2)
  {
    return Math.log(paramDouble1) / Math.log(paramDouble2);
  }
  
  public static int a(long paramLong1, long paramLong2)
  {
    int i = 0;
    if (paramLong1 < paramLong2) {
      i = -1;
    }
    while (paramLong1 <= paramLong2) {
      return i;
    }
    return 1;
  }
  
  public static int a(Activity paramActivity, boolean paramBoolean, int paramInt)
  {
    return a(paramActivity, paramBoolean, paramInt, true);
  }
  
  public static int a(Activity paramActivity, boolean paramBoolean1, int paramInt, boolean paramBoolean2)
  {
    if (Build.VERSION.SDK_INT < 19) {}
    int i;
    do
    {
      return -1;
      i = paramInt;
      if (paramInt <= 0) {
        i = a;
      }
      if (paramBoolean1)
      {
        a(paramActivity, true);
        paramActivity = new com.b.a.a(paramActivity);
        paramActivity.a(true);
        paramActivity.a(i);
        return paramActivity.a().b();
      }
    } while (Build.VERSION.SDK_INT < 21);
    Window localWindow = paramActivity.getWindow();
    if ((Build.VERSION.SDK_INT >= 23) && (!h()))
    {
      if (!paramBoolean2) {
        break label158;
      }
      localWindow.getDecorView().setSystemUiVisibility(8192);
    }
    for (;;)
    {
      WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
      localLayoutParams.flags |= 0x80000000;
      localLayoutParams.flags &= 0xFBFFFFFF;
      localWindow.setAttributes(localLayoutParams);
      localWindow.setStatusBarColor(paramActivity.getResources().getColor(i));
      return -1;
      label158:
      localWindow.getDecorView().setSystemUiVisibility(1);
    }
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static long a(String paramString)
  {
    long l2 = 0L;
    long l1 = l2;
    try
    {
      StatFs localStatFs = new StatFs(paramString);
      l1 = l2;
      long l3 = localStatFs.getBlockCount() - localStatFs.getAvailableBlocks();
      l1 = l2;
      l2 = localStatFs.getBlockSize() * l3;
      l1 = l2;
      c.a(paramString + ", SD card used size: " + l2);
      return l2;
    }
    catch (Exception paramString) {}
    return l1;
  }
  
  public static Uri a(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = null;
    int i = 0;
    try
    {
      String str = g.a(paramContext.getResources().getConfiguration().locale.toString());
      paramContext = localObject;
      if (!TextUtils.isEmpty(paramString2)) {
        paramString2 = paramString2.split("\\|");
      }
      for (;;)
      {
        paramContext = localObject;
        if (i < paramString2.length)
        {
          if (paramString2[i].split("#")[0].equals(paramString1)) {
            paramContext = Uri.parse("http://www.trendmicro.com/vinfo/us/productredirection/" + str + "/malware/" + paramString1 + "/TMMS");
          }
        }
        else {
          return paramContext;
        }
        i += 1;
      }
      return null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static NotificationCompat.Builder a(NotificationCompat.Builder paramBuilder, String paramString1, String paramString2, PendingIntent paramPendingIntent)
  {
    paramBuilder.setContentIntent(paramPendingIntent);
    if (paramString1 != null) {
      paramBuilder.setContentTitle(paramString1);
    }
    if (paramString2 != null) {
      paramBuilder.setContentText(paramString2);
    }
    return paramBuilder;
  }
  
  public static NotificationCompat.Builder a(a paramA, NotificationCompat.Builder paramBuilder, Context paramContext)
  {
    int i = 0;
    int j;
    switch (1.a[paramA.ordinal()])
    {
    default: 
      j = 0;
    }
    while (Build.VERSION.SDK_INT >= 21)
    {
      paramBuilder.setSmallIcon(i);
      paramBuilder.setColor(paramContext.getResources().getColor(2131624070));
      return paramBuilder;
      j = 2130837950;
      i = 2130837891;
      continue;
      j = 2130837895;
      i = 2130837893;
      continue;
      j = 2130837894;
      i = 2130837892;
    }
    paramBuilder.setSmallIcon(j);
    return paramBuilder;
  }
  
  public static Pair<Integer, Integer> a(ImageView paramImageView)
  {
    int j = paramImageView.getHeight();
    int i = paramImageView.getWidth();
    int k = paramImageView.getDrawable().getIntrinsicHeight();
    int m = paramImageView.getDrawable().getIntrinsicWidth();
    if (j * m <= i * k) {
      i = m * j / k;
    }
    for (;;)
    {
      return Pair.create(Integer.valueOf(j), Integer.valueOf(i));
      j = k * i / m;
    }
  }
  
  private static b a(List<b> paramList, String paramString)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      b localB = (b)paramList.next();
      if (localB.a.equals(paramString)) {
        return localB;
      }
    }
    return null;
  }
  
  public static String a(int paramInt, Context paramContext)
  {
    Object localObject1 = "";
    if (paramInt == 0) {
      paramContext = paramContext.getString(com.trendmicro.tmmssuite.ext.wtp.a.a.a[paramInt]);
    }
    do
    {
      return paramContext;
      int i = 0;
      while (i < 4)
      {
        int j = paramInt << i * 8 >> 24;
        c.c("createMultCategoryWTP resType=" + paramInt + ", resTypeItem=" + j);
        Object localObject2 = localObject1;
        if (j != 0)
        {
          localObject2 = localObject1;
          if (j < com.trendmicro.tmmssuite.ext.wtp.a.a.a.length) {
            localObject2 = (String)localObject1 + String.format("%s, ", new Object[] { paramContext.getString(com.trendmicro.tmmssuite.ext.wtp.a.a.a[j]) });
          }
        }
        i += 1;
        localObject1 = localObject2;
      }
      paramContext = (Context)localObject1;
    } while (((String)localObject1).length() <= 2);
    return ((String)localObject1).substring(0, ((String)localObject1).length() - 2);
  }
  
  public static String a(long paramLong)
  {
    return android.text.format.DateFormat.getDateFormat((Context)b.a(com.trendmicro.tmmssuite.core.app.a.a)).format(Long.valueOf(paramLong));
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      localObject = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.dataDir;
      paramContext = (Context)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        paramContext = paramContext.getFilesDir().getParent();
      }
    }
    localObject = paramContext;
    if (!paramContext.endsWith("/")) {
      localObject = paramContext + "/";
    }
    return localObject;
  }
  
  public static String a(Context paramContext, int paramInt)
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
  
  public static String a(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Object... paramVarArgs)
  {
    if (paramVarArgs != null)
    {
      Object[] arrayOfObject = new Object[paramVarArgs.length + 1];
      arrayOfObject[0] = NumberFormat.getNumberInstance().format(paramInt4);
      System.arraycopy(paramVarArgs, 0, arrayOfObject, 1, paramVarArgs.length);
      paramVarArgs = arrayOfObject;
    }
    for (;;)
    {
      switch (paramInt4)
      {
      default: 
        return String.format(paramContext.getResources().getString(paramInt3), paramVarArgs);
        paramVarArgs = new Object[1];
        paramVarArgs[0] = NumberFormat.getNumberInstance().format(paramInt4);
      }
    }
    return String.format(paramContext.getResources().getString(paramInt1), paramVarArgs);
    return String.format(paramContext.getResources().getString(paramInt2), paramVarArgs);
  }
  
  public static String a(Cursor paramCursor)
  {
    String str = a(paramCursor, "VirusName");
    e localE = new e(str);
    int i = b(paramCursor, "MarsPrivacyRiskLevel");
    if (!TextUtils.isEmpty(str))
    {
      if (localE.e())
      {
        if (i == 3) {
          return "HIGH_PRIVACY;HIGH_VULNERABILITY";
        }
        return "HIGH_VULNERABILITY";
      }
      if (localE.d()) {
        return "Fake";
      }
      if (localE.c()) {
        return "PUA";
      }
      return "Threat";
    }
    str = a(paramCursor, "MarsMediumVul");
    paramCursor = a(paramCursor, "MarsLowVul");
    if (i > 0)
    {
      if (i == 3) {
        return "HIGH_PRIVACY";
      }
      if ((!TextUtils.isEmpty(str)) || (!TextUtils.isEmpty(paramCursor))) {
        return "Privacy;Vulnerability";
      }
      return "Privacy";
    }
    return "Vulnerability";
  }
  
  public static String a(Cursor paramCursor, String paramString)
  {
    return paramCursor.getString(paramCursor.getColumnIndex(paramString));
  }
  
  /* Error */
  public static String a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: ldc_w 499
    //   6: astore_2
    //   7: new 403	java/io/File
    //   10: dup
    //   11: aload_0
    //   12: invokespecial 500	java/io/File:<init>	(Ljava/lang/String;)V
    //   15: astore_0
    //   16: aload_2
    //   17: astore 4
    //   19: aload_0
    //   20: invokevirtual 503	java/io/File:exists	()Z
    //   23: ifeq +112 -> 135
    //   26: aload_2
    //   27: astore 4
    //   29: aload_0
    //   30: invokevirtual 506	java/io/File:isFile	()Z
    //   33: ifeq +102 -> 135
    //   36: new 508	java/io/FileInputStream
    //   39: dup
    //   40: aload_0
    //   41: invokespecial 511	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   44: astore_3
    //   45: new 513	java/io/LineNumberReader
    //   48: dup
    //   49: new 515	java/io/InputStreamReader
    //   52: dup
    //   53: aload_3
    //   54: invokespecial 518	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   57: invokespecial 521	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   60: astore 4
    //   62: aload_2
    //   63: astore_0
    //   64: aload 4
    //   66: invokevirtual 524	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   69: astore_2
    //   70: aload_2
    //   71: ifnull +40 -> 111
    //   74: iload_1
    //   75: ifeq +11 -> 86
    //   78: aload_2
    //   79: invokevirtual 527	java/lang/String:trim	()Ljava/lang/String;
    //   82: astore_0
    //   83: goto -19 -> 64
    //   86: new 143	java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   93: aload_0
    //   94: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: aload_2
    //   98: invokevirtual 527	java/lang/String:trim	()Ljava/lang/String;
    //   101: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   107: astore_0
    //   108: goto -44 -> 64
    //   111: aload 4
    //   113: ifnull +8 -> 121
    //   116: aload 4
    //   118: invokevirtual 530	java/io/LineNumberReader:close	()V
    //   121: aload_0
    //   122: astore 4
    //   124: aload_3
    //   125: ifnull +10 -> 135
    //   128: aload_3
    //   129: invokevirtual 533	java/io/InputStream:close	()V
    //   132: aload_0
    //   133: astore 4
    //   135: aload 4
    //   137: areturn
    //   138: astore_2
    //   139: aload_2
    //   140: invokevirtual 534	java/io/IOException:printStackTrace	()V
    //   143: aload_0
    //   144: areturn
    //   145: astore_3
    //   146: aconst_null
    //   147: astore_0
    //   148: aload 5
    //   150: astore_2
    //   151: ldc_w 536
    //   154: astore 4
    //   156: aload_3
    //   157: invokevirtual 216	java/lang/Exception:printStackTrace	()V
    //   160: aload_0
    //   161: ifnull +7 -> 168
    //   164: aload_0
    //   165: invokevirtual 530	java/io/LineNumberReader:close	()V
    //   168: aload_2
    //   169: ifnull -34 -> 135
    //   172: aload_2
    //   173: invokevirtual 533	java/io/InputStream:close	()V
    //   176: ldc_w 536
    //   179: areturn
    //   180: astore_0
    //   181: aload_0
    //   182: invokevirtual 534	java/io/IOException:printStackTrace	()V
    //   185: ldc_w 536
    //   188: areturn
    //   189: astore_2
    //   190: aconst_null
    //   191: astore_3
    //   192: aconst_null
    //   193: astore_0
    //   194: aload_0
    //   195: ifnull +7 -> 202
    //   198: aload_0
    //   199: invokevirtual 530	java/io/LineNumberReader:close	()V
    //   202: aload_3
    //   203: ifnull +7 -> 210
    //   206: aload_3
    //   207: invokevirtual 533	java/io/InputStream:close	()V
    //   210: aload_2
    //   211: athrow
    //   212: astore_0
    //   213: aload_0
    //   214: invokevirtual 534	java/io/IOException:printStackTrace	()V
    //   217: goto -7 -> 210
    //   220: astore_2
    //   221: aconst_null
    //   222: astore_0
    //   223: goto -29 -> 194
    //   226: astore_2
    //   227: aload 4
    //   229: astore_0
    //   230: goto -36 -> 194
    //   233: astore 4
    //   235: aload_2
    //   236: astore_3
    //   237: aload 4
    //   239: astore_2
    //   240: goto -46 -> 194
    //   243: astore 4
    //   245: aconst_null
    //   246: astore_0
    //   247: aload_3
    //   248: astore_2
    //   249: aload 4
    //   251: astore_3
    //   252: goto -101 -> 151
    //   255: astore_0
    //   256: aload_3
    //   257: astore_2
    //   258: aload_0
    //   259: astore_3
    //   260: aload 4
    //   262: astore_0
    //   263: goto -112 -> 151
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	266	0	paramString	String
    //   0	266	1	paramBoolean	boolean
    //   6	92	2	str	String
    //   138	2	2	localIOException	IOException
    //   150	23	2	localObject1	Object
    //   189	22	2	localObject2	Object
    //   220	1	2	localObject3	Object
    //   226	10	2	localObject4	Object
    //   239	19	2	localObject5	Object
    //   44	85	3	localFileInputStream	java.io.FileInputStream
    //   145	12	3	localException1	Exception
    //   191	69	3	localObject6	Object
    //   17	211	4	localObject7	Object
    //   233	5	4	localObject8	Object
    //   243	18	4	localException2	Exception
    //   1	148	5	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   116	121	138	java/io/IOException
    //   128	132	138	java/io/IOException
    //   36	45	145	java/lang/Exception
    //   164	168	180	java/io/IOException
    //   172	176	180	java/io/IOException
    //   36	45	189	finally
    //   198	202	212	java/io/IOException
    //   206	210	212	java/io/IOException
    //   45	62	220	finally
    //   64	70	226	finally
    //   78	83	226	finally
    //   86	108	226	finally
    //   156	160	233	finally
    //   45	62	243	java/lang/Exception
    //   64	70	255	java/lang/Exception
    //   78	83	255	java/lang/Exception
    //   86	108	255	java/lang/Exception
  }
  
  private static String a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    Mac localMac = Mac.getInstance("HmacSHA1");
    localMac.init(new SecretKeySpec(paramArrayOfByte1, "HmacSHA1"));
    paramArrayOfByte1 = new String(Base64.encodeBase64(localMac.doFinal(paramArrayOfByte2)));
    Log.e("xxx:", paramArrayOfByte1);
    return paramArrayOfByte1;
  }
  
  public static List<b> a(List<String> paramList, BufferedReader paramBufferedReader)
  {
    localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    try
    {
      for (;;)
      {
        Object localObject1 = paramBufferedReader.readLine();
        if (localObject1 == null) {
          break;
        }
        localObject1 = ((String)localObject1).split("\\s+");
        int i = localObject1.length;
        Object localObject2 = localObject1[(i - 1)];
        if ((!localObject2.contains(":")) && (paramList.contains(localObject2)) && (!localArrayList2.contains(localObject2)))
        {
          b localB = new b();
          localB.a = localObject1[(i - 1)];
          localB.b = localObject1[4];
          localB.c = new int[1];
          localB.c[0] = Integer.parseInt(localObject1[1]);
          localArrayList2.add(localObject2);
          localArrayList1.add(localB);
        }
      }
      return localArrayList1;
    }
    catch (IOException paramList)
    {
      paramList.printStackTrace();
    }
  }
  
  @TargetApi(21)
  public static List<b> a(List<String> paramList, List<UsageStats> paramList1)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    label318:
    label320:
    label400:
    label402:
    label405:
    for (;;)
    {
      Object localObject2;
      Object localObject3;
      b localB;
      try
      {
        Object localObject1 = ((ActivityManager)((Context)b.a(com.trendmicro.tmmssuite.core.app.a.a)).getSystemService("activity")).getRunningServices(200);
        if ((localObject1 != null) && (((List)localObject1).size() > 0))
        {
          localObject1 = ((List)localObject1).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ActivityManager.RunningServiceInfo)((Iterator)localObject1).next();
            localObject3 = ((ActivityManager.RunningServiceInfo)localObject2).service.getPackageName();
            localB = a(localArrayList1, (String)localObject3);
            if (localB != null) {
              break label318;
            }
            localB = new b();
            localB.a = ((String)localObject3);
            localB.b = "";
            localB.c = new int[1];
            localB.c[0] = ((ActivityManager.RunningServiceInfo)localObject2).pid;
            localArrayList2.add(localB.a);
            localArrayList1.add(localB);
            continue;
          }
        }
        if (paramList1 == null) {
          break label400;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      paramList1 = paramList1.iterator();
      for (;;)
      {
        if (paramList1.hasNext())
        {
          String str = ((UsageStats)paramList1.next()).getPackageName();
          if ((!str.contains(":")) && (paramList.contains(str)) && (!localArrayList2.contains(str)))
          {
            localObject2 = new b();
            ((b)localObject2).a = str;
            ((b)localObject2).b = "";
            ((b)localObject2).c = new int[1];
            ((b)localObject2).c[0] = 0;
            localArrayList2.add(str);
            localArrayList1.add(localObject2);
            continue;
            i = 0;
            if (i >= localB.c.length) {
              break label402;
            }
            if (localB.c[i] != ((ActivityManager.RunningServiceInfo)localObject2).pid) {
              break;
            }
          }
        }
      }
      for (int i = 1;; i = 0)
      {
        if (i != 0) {
          break label405;
        }
        localObject3 = Arrays.copyOf(localB.c, localB.c.length + 1);
        localObject3[localB.c.length] = ((ActivityManager.RunningServiceInfo)localObject2).pid;
        localB.c = ((int[])localObject3);
        break;
        i += 1;
        break label320;
        return localArrayList1;
      }
    }
  }
  
  public static void a()
  {
    c.c("clear keys!");
    Object localObject = (Context)b.a(com.trendmicro.tmmssuite.core.app.a.a);
    if (localObject == null)
    {
      c.b("context is null");
      return;
    }
    SharedPreferences.Editor localEditor = ((Context)localObject).getSharedPreferences("NETWORK_PREF", 0).edit();
    localEditor.remove("EXPIREDATE");
    localEditor.remove("LICENSE_STATUS");
    localEditor.remove("BIZ_TYPE");
    localEditor.remove("AUTORENEW");
    localEditor.remove("ENCRYPT_PASSWORD");
    localEditor.remove("ENCRYPT_PASSWORD_EX");
    localEditor.remove("SUPERKEY");
    localEditor.remove("SUPERKEY_EX");
    localEditor.remove("AUTH_KEY");
    localEditor.remove("REGISTRATION_ID");
    localEditor.commit();
    localObject = ((Context)localObject).getSharedPreferences("share_preference", 0).edit();
    ((SharedPreferences.Editor)localObject).remove("Password");
    ((SharedPreferences.Editor)localObject).commit();
    new File(a(h.a()) + "config/wtpsettings").delete();
  }
  
  public static void a(int paramInt)
  {
    long l = paramInt;
    try
    {
      Thread.sleep(l);
      return;
    }
    catch (Exception localException) {}
  }
  
  public static void a(Activity paramActivity)
  {
    if (paramActivity.getResources().getBoolean(2131558402)) {
      paramActivity.setRequestedOrientation(1);
    }
  }
  
  public static void a(Activity paramActivity, int paramInt, boolean paramBoolean)
  {
    if (paramActivity == null) {}
    do
    {
      return;
      paramActivity = (ImageView)paramActivity.findViewById(paramInt);
    } while (paramActivity == null);
    if (paramBoolean)
    {
      paramActivity.setVisibility(0);
      return;
    }
    paramActivity.setVisibility(8);
  }
  
  @TargetApi(19)
  public static void a(Activity paramActivity, boolean paramBoolean)
  {
    paramActivity = paramActivity.getWindow();
    WindowManager.LayoutParams localLayoutParams = paramActivity.getAttributes();
    if (paramBoolean) {}
    for (localLayoutParams.flags |= 0x4000000;; localLayoutParams.flags &= 0xFBFFFFFF)
    {
      paramActivity.setAttributes(localLayoutParams);
      return;
    }
  }
  
  public static void a(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 == 0) {
      return;
    }
    a(paramContext, paramInt1, paramContext.getResources().getString(paramInt2), paramInt3);
  }
  
  public static void a(Context paramContext, int paramInt1, String paramString, int paramInt2)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    View localView = LayoutInflater.from(paramContext).inflate(2130968870, null);
    ((ImageView)localView.findViewById(2131689557)).setImageResource(paramInt1);
    ((TextView)localView.findViewById(2131689998)).setText(paramString);
    paramContext = new Toast(paramContext);
    paramContext.setView(localView);
    paramContext.setDuration(paramInt2);
    paramContext.setGravity(81, 0, paramContext.getYOffset());
    paramContext.show();
  }
  
  public static void a(Context paramContext, BroadcastReceiver paramBroadcastReceiver)
  {
    if (m())
    {
      c.b("unregisterLocalBroadcastReceiverForZ992");
      LocalBroadcastManager.getInstance(paramContext).unregisterReceiver(paramBroadcastReceiver);
    }
  }
  
  public static void a(Context paramContext, BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter)
  {
    if (m())
    {
      c.b("registerLocalBroadcastReceiverForZ992");
      LocalBroadcastManager.getInstance(paramContext).registerReceiver(paramBroadcastReceiver, paramIntentFilter);
    }
  }
  
  public static void a(Context paramContext, Intent paramIntent, String paramString, int paramInt)
  {
    paramIntent.setFlags(268435456);
    if (j())
    {
      b(paramContext, paramIntent, paramString, paramInt);
      return;
    }
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    if (paramInt > 0) {
      localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramContext, paramInt));
    }
    localIntent.putExtra("duplicate", false);
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void a(Context paramContext, TextView paramTextView1, TextView paramTextView2, TextView paramTextView3, String paramString, int paramInt)
  {
    paramContext = paramString.split(Integer.toString(paramInt));
    paramTextView2.setText(Integer.toString(paramInt));
    if (paramContext.length > 1)
    {
      paramTextView1.setText(paramContext[0].trim());
      paramTextView3.setText(paramContext[1].trim());
      return;
    }
    paramTextView3.setText(paramContext[0].trim());
  }
  
  public static void a(Context paramContext, String paramString, TextView paramTextView)
  {
    int i = 0;
    String[] arrayOfString = paramString.split(":");
    int j;
    if (arrayOfString.length > 1)
    {
      j = arrayOfString[0].length() + 1;
      i = paramString.length() - 1;
    }
    for (;;)
    {
      paramString = new SpannableString(paramString);
      paramString.setSpan(new ForegroundColorSpan(paramContext.getResources().getColor(2131624091)), j, i + 1, 33);
      paramTextView.setText(paramString);
      return;
      j = 0;
    }
  }
  
  public static void a(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    if ((paramContext == null) || ((paramList1 == null) && (paramList2 == null))) {
      return;
    }
    for (;;)
    {
      ApplicationInfo localApplicationInfo;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledApplications(8704);
        if (paramContext == null) {
          break;
        }
        Iterator localIterator = paramContext.iterator();
        if (!localIterator.hasNext()) {
          break label138;
        }
        localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo.sourceDir == null) {
          continue;
        }
        if ((localApplicationInfo.flags & 0x1) != 1) {
          break label119;
        }
        if (paramList2 == null) {
          continue;
        }
        paramList2.add(localApplicationInfo.packageName);
        continue;
        if (paramList1 == null) {
          break;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        if (paramList2 != null) {
          paramList2.clear();
        }
      }
      paramList1.clear();
      return;
      label119:
      if (paramList1 != null) {
        paramList1.add(localApplicationInfo.packageName);
      }
    }
    label138:
    paramContext.clear();
  }
  
  @SuppressLint({"NewApi"})
  public static void a(WebView paramWebView)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      paramWebView.removeJavascriptInterface("searchBoxJavaBridge_");
    }
  }
  
  public static void a(ListView paramListView)
  {
    ListAdapter localListAdapter;
    if (paramListView != null)
    {
      localListAdapter = paramListView.getAdapter();
      if (localListAdapter != null) {}
    }
    else
    {
      return;
    }
    int k = localListAdapter.getCount();
    int i = 0;
    int j = 0;
    while (i < k)
    {
      localObject = localListAdapter.getView(i, null, paramListView);
      ((View)localObject).measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
      j += ((View)localObject).getMeasuredHeight();
      i += 1;
    }
    Object localObject = paramListView.getLayoutParams();
    if (localObject != null)
    {
      ((ViewGroup.LayoutParams)localObject).height = (paramListView.getDividerHeight() * (localListAdapter.getCount() - 1) + j);
      paramListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
    }
    paramListView.requestLayout();
  }
  
  public static boolean a(Context paramContext, Uri paramUri, String paramString)
  {
    if (Build.VERSION.SDK_INT < 19) {
      return false;
    }
    paramUri = DocumentFile.fromTreeUri(paramContext, paramUri);
    String[] arrayOfString = paramString.split("/");
    int i = 2;
    Uri localUri = null;
    paramString = paramUri;
    paramUri = localUri;
    label285:
    label291:
    for (;;)
    {
      DocumentFile localDocumentFile = paramString.findFile(arrayOfString[i]);
      if ((localDocumentFile != null) && (localDocumentFile.isDirectory()))
      {
        c.c("Found uri " + localDocumentFile.getName());
        paramString = localDocumentFile;
        c.c("Found dir " + paramString.getName() + " with size " + paramString.length() + " URI " + paramString.getUri());
        i += 1;
        if (i < arrayOfString.length) {
          break label291;
        }
        if (paramUri == null) {
          break label285;
        }
      }
      for (;;)
      {
        try
        {
          bool = DocumentsContract.deleteDocument(paramContext.getContentResolver(), paramUri);
        }
        catch (Exception paramContext)
        {
          bool = false;
        }
        try
        {
          c.c("Found uri delete " + bool + ",uri " + paramUri);
          return bool;
        }
        catch (Exception paramContext)
        {
          for (;;) {}
        }
        localUri = paramUri;
        if (localDocumentFile != null)
        {
          localUri = paramUri;
          if (i == arrayOfString.length - 1)
          {
            localUri = localDocumentFile.getUri();
            c.c("Found uri " + localDocumentFile + ",uri " + localUri);
          }
        }
        paramUri = localUri;
        break;
        paramContext.printStackTrace();
        continue;
        boolean bool = false;
      }
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {}
    for (;;)
    {
      return false;
      int i = TimeZone.getDefault().getRawOffset();
      long l3 = new Date().getTime();
      long l4 = i;
      try
      {
        long l1 = Long.valueOf(paramString + "000").longValue();
        long l2 = l1 + i;
        long l5 = l2 % 86400000L;
        l1 = l2;
        if (l5 != 0L) {
          l1 = l2 + (86400000L - l5);
        }
        if (l1 - (l4 + l3) < 0L) {
          return true;
        }
      }
      catch (Exception paramContext) {}
    }
    return false;
  }
  
  public static boolean a(View paramView, int paramInt)
  {
    if (paramView == null) {}
    while (paramView.getVisibility() == paramInt) {
      return false;
    }
    paramView.setVisibility(paramInt);
    return true;
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    c.c(Build.MANUFACTURER + " , " + Build.MODEL);
    if ((!TextUtils.isEmpty(Build.MANUFACTURER)) && (!TextUtils.isEmpty(paramString1)) && (!Build.MANUFACTURER.equalsIgnoreCase(paramString1))) {}
    do
    {
      return false;
      if ((TextUtils.isEmpty(Build.MODEL)) || (TextUtils.isEmpty(paramString2))) {
        break;
      }
    } while (!Build.MODEL.startsWith(paramString2));
    return true;
    return true;
  }
  
  public static int b(Context paramContext, float paramFloat)
  {
    if (paramFloat == 0.0F) {
      return 0;
    }
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static int b(Cursor paramCursor, String paramString)
  {
    return paramCursor.getInt(paramCursor.getColumnIndex(paramString));
  }
  
  public static String b()
  {
    return "Android";
  }
  
  public static String b(int paramInt)
  {
    if (paramInt == 0) {
      return "0";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < 4)
    {
      int j = paramInt >> i * 8 & 0xFF;
      if (j != 0) {
        localStringBuffer.append(String.valueOf(j)).append('|');
      }
      i += 1;
    }
    if (localStringBuffer.length() > 1)
    {
      localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
      return localStringBuffer.toString();
    }
    return "0";
  }
  
  public static String b(long paramLong)
  {
    return android.text.format.DateFormat.getTimeFormat((Context)b.a(com.trendmicro.tmmssuite.core.app.a.a)).format(Long.valueOf(paramLong));
  }
  
  public static String b(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext != null)
    {
      paramContext = paramContext.getSimCountryIso();
      if (paramContext != null) {
        return paramContext.toUpperCase(Locale.ENGLISH);
      }
      return "";
    }
    return "";
  }
  
  public static String b(String paramString)
  {
    if (paramString == null) {
      str = "";
    }
    int k;
    do
    {
      return str;
      k = paramString.indexOf("@");
      str = paramString;
    } while (k <= 0);
    int j = k / 2;
    int i = j;
    if (j < 0) {
      i = 0;
    }
    String str = paramString.substring(0, i);
    while (i < k)
    {
      str = str + "*";
      i += 1;
    }
    j = paramString.indexOf(".", k + 1);
    i = j;
    if (j < 0) {
      i = paramString.length();
    }
    j = k + 1 + (i - k - 1) / 2;
    str = str + paramString.substring(k, j);
    while (j < i)
    {
      str = str + "*";
      j += 1;
    }
    return str + paramString.substring(i, paramString.length());
  }
  
  public static List<ResolveInfo> b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setPackage(paramString);
    paramContext = paramContext.queryIntentActivities(localIntent, 0);
    if (paramContext != null) {
      return paramContext;
    }
    return new ArrayList();
  }
  
  public static void b(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 == 0) {
      return;
    }
    b(paramContext, paramInt1, paramContext.getResources().getString(paramInt2), paramInt3);
  }
  
  public static void b(Context paramContext, int paramInt1, String paramString, int paramInt2)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    View localView = LayoutInflater.from(paramContext).inflate(2130968787, null);
    ((ImageView)localView.findViewById(2131689557)).setImageResource(paramInt1);
    ((TextView)localView.findViewById(2131689998)).setText(paramString);
    paramContext = new Toast(paramContext);
    paramContext.setView(localView);
    paramContext.setDuration(paramInt2);
    paramContext.setGravity(80, 0, paramContext.getYOffset());
    paramContext.show();
  }
  
  @RequiresApi(api=26)
  public static void b(Context paramContext, Intent paramIntent, String paramString, int paramInt)
  {
    ShortcutManager localShortcutManager = (ShortcutManager)paramContext.getSystemService(ShortcutManager.class);
    if (paramIntent.getAction() == null) {
      paramIntent.setAction("android.intent.action.VIEW");
    }
    paramString = new ShortcutInfo.Builder(paramContext, paramString).setShortLabel(paramString).setLongLabel(paramString);
    if (paramInt > 0) {}
    for (paramContext = Icon.createWithResource(paramContext, paramInt);; paramContext = null)
    {
      paramContext = paramString.setIcon(paramContext).setIntent(paramIntent).build();
      if (localShortcutManager.isRequestPinShortcutSupported()) {
        localShortcutManager.requestPinShortcut(paramContext, null);
      }
      return;
    }
  }
  
  public static void b(Context paramContext, String paramString, TextView paramTextView)
  {
    Object localObject = paramString.split(":");
    int i;
    int j;
    if (localObject.length > 1)
    {
      i = localObject[0].length() + 1;
      j = paramString.length() - 1;
    }
    for (;;)
    {
      localObject = new SpannableString(paramString);
      ForegroundColorSpan localForegroundColorSpan = new ForegroundColorSpan(paramContext.getResources().getColor(2131624024));
      j += 1;
      ((SpannableString)localObject).setSpan(localForegroundColorSpan, i, j, 33);
      ((SpannableString)localObject).setSpan(new AbsoluteSizeSpan(14, true), i, j, 33);
      i = 0;
      int n = 1;
      int k = 0;
      int i1 = 0;
      if (i < paramString.length())
      {
        int i2 = n;
        j = i1;
        int m = k;
        if (Character.isDigit(paramString.charAt(i)))
        {
          if (n == 0) {
            break label176;
          }
          i2 = 0;
          j = i;
        }
        for (m = i;; m = k)
        {
          i += 1;
          n = i2;
          i1 = j;
          k = m;
          break;
          label176:
          j = i1 + 1;
          i2 = n;
        }
      }
      ((SpannableString)localObject).setSpan(new ForegroundColorSpan(paramContext.getResources().getColor(2131624051)), k, i1 + 1, 33);
      paramTextView.setText((CharSequence)localObject);
      return;
      j = 0;
      i = 0;
    }
  }
  
  public static boolean b(Activity paramActivity)
  {
    return paramActivity.getResources().getConfiguration().orientation == 1;
  }
  
  public static boolean b(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return false;
    }
    return Pattern.compile(paramString2).matcher(paramString1).find();
  }
  
  public static int c(int paramInt)
  {
    String str = a("/sys/class/thermal/thermal_zone" + paramInt + "/temp", true);
    try
    {
      paramInt = Integer.parseInt(str);
      return paramInt;
    }
    catch (Exception localException) {}
    return 65236;
  }
  
  public static long c(Cursor paramCursor, String paramString)
  {
    return paramCursor.getLong(paramCursor.getColumnIndex(paramString));
  }
  
  public static String c()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String c(long paramLong)
  {
    return a(paramLong) + " " + b(paramLong);
  }
  
  public static String c(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    switch (paramInt3)
    {
    default: 
      return String.format(paramContext.getResources().getString(paramInt2), new Object[] { Integer.valueOf(paramInt3) });
    }
    return String.format(paramContext.getResources().getString(paramInt1), new Object[] { Integer.valueOf(paramInt3) });
  }
  
  public static String c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    int i;
    do
    {
      return paramString;
      i = paramString.indexOf("_#");
    } while (i <= 0);
    return paramString.substring(0, i);
  }
  
  public static String c(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = a(paramString1.getBytes(), paramString2.getBytes("UTF-8"));
      return paramString1;
    }
    catch (GeneralSecurityException paramString1)
    {
      return "";
    }
    catch (UnsupportedEncodingException paramString1) {}
    return "";
  }
  
  public static void c(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse("market://details?id=" + paramString));
      localIntent.setPackage("com.android.vending");
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void c(Context paramContext, String paramString, TextView paramTextView)
  {
    int n = 1;
    int i1 = 0;
    int k = 0;
    int i = 0;
    if (i < paramString.length())
    {
      int i2;
      int j;
      int m;
      if ((Character.isDigit(paramString.charAt(i))) || ((n == 0) && (paramString.charAt(i) == ','))) {
        if (n != 0)
        {
          i2 = 0;
          j = i;
          m = i;
        }
      }
      do
      {
        for (;;)
        {
          i += 1;
          n = i2;
          i1 = j;
          k = m;
          break;
          j = i1 + 1;
          i2 = n;
          m = k;
        }
        i2 = n;
        j = i1;
        m = k;
      } while (n != 0);
    }
    paramString = new SpannableString(paramString);
    paramString.setSpan(new ForegroundColorSpan(paramContext.getResources().getColor(2131624010)), k, i1 + 1, 33);
    paramTextView.setText(paramString);
  }
  
  public static boolean c(Activity paramActivity)
  {
    boolean bool3 = true;
    int i = paramActivity.getWindowManager().getDefaultDisplay().getRotation();
    boolean bool2;
    if (i >= 2)
    {
      bool1 = true;
      bool2 = bool1;
      if (b(paramActivity))
      {
        bool2 = bool1;
        if (i % 2 == 1) {
          if (bool1) {
            break label57;
          }
        }
      }
    }
    label57:
    for (boolean bool1 = bool3;; bool1 = false)
    {
      bool2 = bool1;
      return bool2;
      bool1 = false;
      break;
    }
  }
  
  public static boolean c(Context paramContext)
  {
    return PreferenceHelper.getInstance(paramContext).getEulaAccepted();
  }
  
  public static double d(int paramInt)
  {
    return e(c(paramInt));
  }
  
  public static int d(Activity paramActivity)
  {
    boolean bool = c(paramActivity);
    if (b(paramActivity))
    {
      if (bool) {
        return 9;
      }
      return 1;
    }
    if (bool) {
      return 8;
    }
    return 0;
  }
  
  public static String d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while (paramString.length() <= 5) {
      return paramString;
    }
    return paramString.substring(0, 5);
  }
  
  public static void d(Context paramContext, String paramString, TextView paramTextView)
  {
    int n = 1;
    int i1 = 0;
    int k = 0;
    int i = 0;
    if (i < paramString.length())
    {
      int i2 = n;
      int j = i1;
      int m = k;
      if (Character.isDigit(paramString.charAt(i)))
      {
        if (n == 0) {
          break label75;
        }
        i2 = 0;
        j = i;
      }
      for (m = i;; m = k)
      {
        i += 1;
        n = i2;
        i1 = j;
        k = m;
        break;
        label75:
        j = i1 + 1;
        i2 = n;
      }
    }
    paramString = new SpannableString(paramString);
    paramString.setSpan(new ForegroundColorSpan(paramContext.getResources().getColor(2131623969)), k, i1 + 1, 33);
    paramTextView.setText(paramString);
  }
  
  public static boolean d()
  {
    return (a("Sony", null)) && (Build.VERSION.SDK_INT >= 23);
  }
  
  public static boolean d(Context paramContext)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if ((!localRunningAppProcessInfo.processName.startsWith("com.trendmicro")) && (!localRunningAppProcessInfo.processName.startsWith("com.tm.vpn"))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent(paramString);
      localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramContext)
    {
      c.b("go to setting exception: " + paramString);
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static double e(int paramInt)
  {
    if (paramInt < 200) {
      return paramInt;
    }
    double d = a(paramInt, 10.0D);
    return Math.pow(10.0D, d - (int)d + 1.0D);
  }
  
  public static int e(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static void e(Activity paramActivity)
  {
    paramActivity.setRequestedOrientation(d(paramActivity));
  }
  
  @RequiresApi(api=26)
  public static void e(Context paramContext, String paramString)
  {
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    if (localNotificationManager.getNotificationChannel(paramString) != null) {
      return;
    }
    String str = paramContext.getString(2131296452);
    paramContext = paramContext.getString(2131296452);
    paramString = new NotificationChannel(paramString, str, 4);
    paramString.setDescription(paramContext);
    paramString.enableLights(true);
    paramString.setLightColor(-65536);
    paramString.enableVibration(false);
    localNotificationManager.createNotificationChannel(paramString);
  }
  
  public static boolean e()
  {
    return a("samsung", "SM-G93");
  }
  
  private static String f(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(8);
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
        if ((arrayOfProviderInfo != null) && (arrayOfProviderInfo.length > 0))
        {
          int j = arrayOfProviderInfo.length;
          int i = 0;
          while (i < j)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            c.c(localProviderInfo.authority + ", read: " + localProviderInfo.readPermission + ", write: " + localProviderInfo.writePermission);
            if (paramString.equals(localProviderInfo.readPermission)) {
              return localProviderInfo.authority;
            }
            if (paramString.equals(localProviderInfo.writePermission)) {
              return localProviderInfo.authority;
            }
            i += 1;
          }
        }
      }
    }
    return null;
  }
  
  public static void f(Activity paramActivity)
  {
    try
    {
      paramActivity.finish();
      Object localObject;
      if (paramActivity.getIntent().getIntExtra("Trigger", -1) >= 0)
      {
        localObject = new Intent(paramActivity, TmmsSuiteComMainEntry.class);
        ((Intent)localObject).putExtra("KEY_START_ANIM", false);
        paramActivity.startActivity((Intent)localObject);
        return;
      }
      if (Build.VERSION.SDK_INT >= 22)
      {
        localObject = paramActivity.getReferrer();
        if ((localObject != null) && (!((Uri)localObject).toString().equals("android-app://" + paramActivity.getPackageName())))
        {
          localObject = new Intent(paramActivity, TmmsSuiteComMainEntry.class);
          ((Intent)localObject).putExtra("KEY_START_ANIM", false);
          paramActivity.startActivity((Intent)localObject);
          return;
        }
      }
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static boolean f()
  {
    return a("samsung", "SM-G95");
  }
  
  /* Error */
  public static boolean f(Context paramContext)
  {
    // Byte code:
    //   0: ldc_w 1394
    //   3: invokestatic 213	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   6: astore_1
    //   7: aload_0
    //   8: invokevirtual 966	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   11: aload_1
    //   12: iconst_1
    //   13: anewarray 191	java/lang/String
    //   16: dup
    //   17: iconst_0
    //   18: ldc_w 1396
    //   21: aastore
    //   22: aconst_null
    //   23: aconst_null
    //   24: aconst_null
    //   25: invokevirtual 1402	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnull +141 -> 171
    //   33: new 143	java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   40: ldc_w 1404
    //   43: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: aload_3
    //   47: invokeinterface 1405 1 0
    //   52: invokevirtual 321	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   55: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: invokestatic 326	com/trendmicro/tmmssuite/core/sys/c:c	(Ljava/lang/String;)V
    //   61: aload_3
    //   62: invokeinterface 1408 1 0
    //   67: ifeq +150 -> 217
    //   70: aload_3
    //   71: aload_3
    //   72: ldc_w 1396
    //   75: invokeinterface 493 2 0
    //   80: invokeinterface 494 2 0
    //   85: astore_1
    //   86: aload_1
    //   87: astore_0
    //   88: new 143	java/lang/StringBuilder
    //   91: dup
    //   92: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   95: ldc_w 1410
    //   98: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: aload_1
    //   102: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: invokestatic 162	com/trendmicro/tmmssuite/core/sys/c:a	(Ljava/lang/String;)V
    //   111: aload_1
    //   112: astore_2
    //   113: aload_1
    //   114: astore_0
    //   115: aload_1
    //   116: invokestatic 187	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   119: ifne +35 -> 154
    //   122: aload_1
    //   123: astore_0
    //   124: aload_1
    //   125: invokestatic 1415	com/trendmicro/tmmssuite/encrypt/TmEncrypt:decryptStr	(Ljava/lang/String;)Ljava/lang/String;
    //   128: astore_2
    //   129: aload_2
    //   130: astore_0
    //   131: new 143	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 145	java/lang/StringBuilder:<init>	()V
    //   138: ldc_w 1417
    //   141: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: aload_2
    //   145: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: invokestatic 162	com/trendmicro/tmmssuite/core/sys/c:a	(Ljava/lang/String;)V
    //   154: aload_2
    //   155: astore_0
    //   156: aload_3
    //   157: invokeinterface 1418 1 0
    //   162: aload_0
    //   163: invokestatic 187	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   166: ifeq +32 -> 198
    //   169: iconst_0
    //   170: ireturn
    //   171: ldc_w 1420
    //   174: invokestatic 641	com/trendmicro/tmmssuite/core/sys/c:b	(Ljava/lang/String;)V
    //   177: aconst_null
    //   178: astore_0
    //   179: goto -17 -> 162
    //   182: astore_1
    //   183: aconst_null
    //   184: astore_0
    //   185: ldc_w 1422
    //   188: invokestatic 641	com/trendmicro/tmmssuite/core/sys/c:b	(Ljava/lang/String;)V
    //   191: aload_1
    //   192: invokevirtual 216	java/lang/Exception:printStackTrace	()V
    //   195: goto -33 -> 162
    //   198: aload_0
    //   199: invokevirtual 527	java/lang/String:trim	()Ljava/lang/String;
    //   202: ldc_w 1424
    //   205: invokevirtual 201	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   208: ireturn
    //   209: astore_1
    //   210: goto -25 -> 185
    //   213: astore_1
    //   214: goto -29 -> 185
    //   217: aconst_null
    //   218: astore_0
    //   219: goto -63 -> 156
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	222	0	paramContext	Context
    //   6	119	1	localObject1	Object
    //   182	10	1	localException1	Exception
    //   209	1	1	localException2	Exception
    //   213	1	1	localException3	Exception
    //   112	43	2	localObject2	Object
    //   28	129	3	localCursor	Cursor
    // Exception table:
    //   from	to	target	type
    //   0	29	182	java/lang/Exception
    //   33	86	182	java/lang/Exception
    //   171	177	182	java/lang/Exception
    //   88	111	209	java/lang/Exception
    //   115	122	209	java/lang/Exception
    //   124	129	209	java/lang/Exception
    //   131	154	209	java/lang/Exception
    //   156	162	213	java/lang/Exception
  }
  
  public static String g(Context paramContext)
  {
    return c(paramContext.getResources().getConfiguration().locale.toString());
  }
  
  public static boolean g()
  {
    return a("samsung", "Galaxy Nexus");
  }
  
  public static boolean h()
  {
    return a("motorola", "XT156");
  }
  
  public static boolean h(Context paramContext)
  {
    boolean bool = false;
    int i = Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps", 0);
    c.c("Settings.Secure.INSTALL_NON_MARKET_APPS: " + i);
    if (i != 0) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean i()
  {
    return a("LGE", "LG-D801");
  }
  
  public static boolean i(Context paramContext)
  {
    int i;
    if (Build.VERSION.SDK_INT >= 17)
    {
      i = Settings.Global.getInt(paramContext.getContentResolver(), "adb_enabled", 0);
      c.c("Settings.Global.ADB_ENABLED: " + i);
      if (i == 0) {}
    }
    do
    {
      return true;
      return false;
      i = Settings.Secure.getInt(paramContext.getContentResolver(), "adb_enabled", 0);
      c.c("Settings.Secure.ADB_ENABLED: " + i);
    } while (i != 0);
    return false;
  }
  
  public static boolean j()
  {
    return Build.VERSION.SDK_INT >= 26;
  }
  
  public static boolean j(Context paramContext)
  {
    DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager)paramContext.getSystemService("device_policy");
    if (Build.VERSION.SDK_INT >= 17)
    {
      i = Settings.Secure.getInt(paramContext.getContentResolver(), "require_password_to_decrypt", 0);
      c.c("Settings.Global.REQUIRE_PASSWORD_TO_DECRYPT: " + i);
    }
    int i = localDevicePolicyManager.getStorageEncryptionStatus();
    c.c("getStorageEncryptionStatus: " + i);
    return i >= 2;
  }
  
  public static boolean k()
  {
    if (Build.VERSION.SDK_INT < 16) {}
    while ((a("HTC", "HTC")) && (Build.VERSION.SDK_INT < 18)) {
      return false;
    }
    return true;
  }
  
  public static boolean k(Context paramContext)
  {
    try
    {
      Class localClass = Class.forName("com.android.internal.widget.LockPatternUtils");
      paramContext = localClass.getConstructor(new Class[] { Context.class }).newInstance(new Object[] { paramContext.getApplicationContext() });
      boolean bool = ((Boolean)localClass.getMethod("isSecure", new Class[0]).invoke(paramContext, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean l()
  {
    return (e()) || (f()) || ((a("samsung", null)) && (Build.VERSION.SDK_INT >= 23));
  }
  
  public static boolean l(Context paramContext)
  {
    boolean bool = false;
    if (Build.VERSION.SDK_INT < 23)
    {
      if ((Settings.System.getInt(paramContext.getContentResolver(), "lock_pattern_autolock", 0) != 0) || (k(paramContext))) {
        bool = true;
      }
      return bool;
    }
    return ((KeyguardManager)paramContext.getSystemService("keyguard")).isDeviceSecure();
  }
  
  public static boolean m()
  {
    return a("ZTE", "Z992");
  }
  
  public static boolean m(Context paramContext)
  {
    return d(paramContext, "android.settings.APPLICATION_DEVELOPMENT_SETTINGS");
  }
  
  public static boolean n()
  {
    List localList = i.a((Context)b.a(com.trendmicro.tmmssuite.core.app.a.a), true);
    String[] arrayOfString = f.m;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (localList.contains(arrayOfString[i])) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean n(Context paramContext)
  {
    return d(paramContext, "android.settings.SECURITY_SETTINGS");
  }
  
  public static boolean o()
  {
    return (r()) || (s()) || (t());
  }
  
  public static boolean o(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.app.action.SET_NEW_PASSWORD");
      localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static double p()
  {
    return Math.max(Math.max(d(9), d(10)), Math.max(d(11), d(12)));
  }
  
  public static boolean p(Context paramContext)
  {
    com.trendmicro.tmmssuite.d.a.c(paramContext);
    try
    {
      Intent localIntent = new Intent("android.app.action.START_ENCRYPTION");
      localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static void q(Context paramContext)
  {
    if (r(paramContext)) {
      return;
    }
    Intent localIntent = new Intent(paramContext, MemoryShortCutActivity.class);
    String str = paramContext.getString(2131297194);
    if (j()) {}
    for (int i = 2130838126;; i = 2130838125)
    {
      a(paramContext, localIntent, str, i);
      return;
    }
  }
  
  public static boolean q()
  {
    PowerManager localPowerManager = (PowerManager)((Context)b.a(com.trendmicro.tmmssuite.core.app.a.a)).getSystemService("power");
    if (Build.VERSION.SDK_INT < 20) {
      return localPowerManager.isScreenOn();
    }
    return localPowerManager.isInteractive();
  }
  
  private static boolean r()
  {
    String str = Build.TAGS;
    c.c("buildTags:" + str);
    return (str != null) && (str.contains("test-keys"));
  }
  
  public static boolean r(Context paramContext)
  {
    for (;;)
    {
      try
      {
        localContentResolver = paramContext.getContentResolver();
        localObject2 = f(paramContext, "com.google.android.launcher.permission.READ_SETTINGS");
        localObject1 = localObject2;
        if (localObject2 != null) {
          break label211;
        }
        localObject1 = f(paramContext, "com.android.launcher.permission.READ_SETTINGS");
      }
      catch (Exception paramContext)
      {
        ContentResolver localContentResolver;
        paramContext.printStackTrace();
      }
      c.e("hasSystemTunerShortcut authority: " + (String)localObject2);
      Object localObject1 = Uri.parse("content://" + (String)localObject2 + "/favorites?notify=true");
      paramContext = paramContext.getString(2131297194).trim();
      paramContext = localContentResolver.query((Uri)localObject1, new String[] { "title", "iconResource" }, "title=?", new String[] { paramContext }, null);
      c.b("hasSystemTunerShortcut Cursor: " + paramContext);
      if (paramContext != null) {
        c.b("hasSystemTunerShortcut count: " + paramContext.getCount());
      }
      if ((paramContext != null) && (paramContext.getCount() > 0))
      {
        paramContext.close();
        return true;
      }
      return false;
      label211:
      Object localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = "com.android.launcher.settings";
      }
    }
  }
  
  private static boolean s()
  {
    String[] arrayOfString = new String[10];
    arrayOfString[0] = "/system/app/Superuser.apk";
    arrayOfString[1] = "/sbin/su";
    arrayOfString[2] = "/system/bin/su";
    arrayOfString[3] = "/system/xbin/su";
    arrayOfString[4] = "/data/local/xbin/su";
    arrayOfString[5] = "/data/local/bin/su";
    arrayOfString[6] = "/system/sd/xbin/su";
    arrayOfString[7] = "/system/bin/failsafe/su";
    arrayOfString[8] = "/data/local/su";
    arrayOfString[9] = "/su/bin/su";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (new File(arrayOfString[i]).exists()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean s(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    if (paramContext != null)
    {
      boolean bool = paramContext.isConnected();
      Log.d("TMMS Utils", "Netowrk status is " + bool);
      return bool;
    }
    return false;
  }
  
  /* Error */
  private static boolean t()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: invokestatic 1674	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   5: iconst_2
    //   6: anewarray 191	java/lang/String
    //   9: dup
    //   10: iconst_0
    //   11: ldc_w 1676
    //   14: aastore
    //   15: dup
    //   16: iconst_1
    //   17: ldc_w 1678
    //   20: aastore
    //   21: invokevirtual 1682	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   24: astore_0
    //   25: aload_0
    //   26: astore_1
    //   27: new 579	java/io/BufferedReader
    //   30: dup
    //   31: new 515	java/io/InputStreamReader
    //   34: dup
    //   35: aload_0
    //   36: invokevirtual 1688	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   39: invokespecial 518	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   42: invokespecial 1689	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   45: invokevirtual 580	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   48: astore_2
    //   49: aload_2
    //   50: ifnull +13 -> 63
    //   53: aload_0
    //   54: ifnull +7 -> 61
    //   57: aload_0
    //   58: invokevirtual 1692	java/lang/Process:destroy	()V
    //   61: iconst_1
    //   62: ireturn
    //   63: aload_0
    //   64: ifnull +7 -> 71
    //   67: aload_0
    //   68: invokevirtual 1692	java/lang/Process:destroy	()V
    //   71: iconst_0
    //   72: ireturn
    //   73: astore_0
    //   74: aconst_null
    //   75: astore_0
    //   76: aload_0
    //   77: ifnull +7 -> 84
    //   80: aload_0
    //   81: invokevirtual 1692	java/lang/Process:destroy	()V
    //   84: iconst_0
    //   85: ireturn
    //   86: astore_0
    //   87: aload_1
    //   88: ifnull +7 -> 95
    //   91: aload_1
    //   92: invokevirtual 1692	java/lang/Process:destroy	()V
    //   95: aload_0
    //   96: athrow
    //   97: astore_1
    //   98: goto -22 -> 76
    // Local variable table:
    //   start	length	slot	name	signature
    //   24	44	0	localProcess	Process
    //   73	1	0	localThrowable1	Throwable
    //   75	6	0	localObject1	Object
    //   86	10	0	localObject2	Object
    //   1	91	1	localObject3	Object
    //   97	1	1	localThrowable2	Throwable
    //   48	2	2	str	String
    // Exception table:
    //   from	to	target	type
    //   2	25	73	java/lang/Throwable
    //   2	25	86	finally
    //   27	49	86	finally
    //   27	49	97	java/lang/Throwable
  }
  
  public static boolean t(Context paramContext)
  {
    return NotificationManagerCompat.from(paramContext).areNotificationsEnabled();
  }
  
  public static enum a
  {
    private a() {}
  }
  
  public static class b
  {
    public String a;
    public String b;
    public int[] c;
    
    public b() {}
  }
}
