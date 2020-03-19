package play.amusing.net.sdk.myinter;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebView;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import play.amusing.net.sdk.b.a;

public final class cc
{
  private static final String[] a = { "com.android.chrome", "com.android.browser", "com.sec.android.app.sbrowser", "com.UCMobile" };
  
  public static int a()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return i;
    }
    catch (Exception localException)
    {
      bw.a(localException);
    }
    return 8;
  }
  
  @SuppressLint({"TrulyRandom"})
  public static int a(int paramInt)
  {
    return new SecureRandom().nextInt(paramInt);
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static String a(String paramString)
  {
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return paramString;
      }
      if (!paramString.startsWith("A"))
      {
        Object localObject = new StringBuffer("A");
        ((StringBuffer)localObject).append(paramString);
        localObject = ((StringBuffer)localObject).toString();
        return localObject;
      }
    }
    catch (Exception localException)
    {
      bw.a(localException);
    }
    return paramString;
  }
  
  /* Error */
  public static List<a> a(List<a> paramList, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aload_0
    //   5: areturn
    //   6: aload_0
    //   7: invokeinterface 100 1 0
    //   12: iconst_1
    //   13: if_icmpgt +5 -> 18
    //   16: aload_0
    //   17: areturn
    //   18: iload_1
    //   19: ifne +5 -> 24
    //   22: aload_0
    //   23: areturn
    //   24: new 102	java/util/ArrayList
    //   27: dup
    //   28: invokespecial 103	java/util/ArrayList:<init>	()V
    //   31: astore 4
    //   33: new 102	java/util/ArrayList
    //   36: dup
    //   37: invokespecial 103	java/util/ArrayList:<init>	()V
    //   40: astore 5
    //   42: aload_0
    //   43: invokeinterface 100 1 0
    //   48: iload_1
    //   49: if_icmpge +206 -> 255
    //   52: aload_0
    //   53: invokeinterface 100 1 0
    //   58: istore_2
    //   59: goto +198 -> 257
    //   62: iload_3
    //   63: iload_2
    //   64: if_icmpge +25 -> 89
    //   67: aload 4
    //   69: aload_0
    //   70: iload_3
    //   71: invokeinterface 107 2 0
    //   76: invokeinterface 111 2 0
    //   81: pop
    //   82: iload_3
    //   83: iconst_1
    //   84: iadd
    //   85: istore_3
    //   86: goto -24 -> 62
    //   89: aload 4
    //   91: invokestatic 117	java/util/Collections:shuffle	(Ljava/util/List;)V
    //   94: iload_1
    //   95: istore_2
    //   96: aload_0
    //   97: invokeinterface 100 1 0
    //   102: iload_1
    //   103: if_icmpgt +40 -> 143
    //   106: aload 5
    //   108: invokeinterface 120 1 0
    //   113: aload 5
    //   115: aload 4
    //   117: invokeinterface 124 2 0
    //   122: pop
    //   123: aload 4
    //   125: invokeinterface 100 1 0
    //   130: ifle +10 -> 140
    //   133: aload 4
    //   135: invokeinterface 120 1 0
    //   140: aload 5
    //   142: areturn
    //   143: iload_2
    //   144: aload_0
    //   145: invokeinterface 100 1 0
    //   150: if_icmpge +25 -> 175
    //   153: aload 5
    //   155: aload_0
    //   156: iload_2
    //   157: invokeinterface 107 2 0
    //   162: invokeinterface 111 2 0
    //   167: pop
    //   168: iload_2
    //   169: iconst_1
    //   170: iadd
    //   171: istore_2
    //   172: goto -29 -> 143
    //   175: aload 5
    //   177: iconst_0
    //   178: aload 4
    //   180: invokeinterface 127 3 0
    //   185: pop
    //   186: aload 4
    //   188: invokeinterface 100 1 0
    //   193: ifle +10 -> 203
    //   196: aload 4
    //   198: invokeinterface 120 1 0
    //   203: aload 5
    //   205: areturn
    //   206: astore_0
    //   207: goto +29 -> 236
    //   210: astore 5
    //   212: aload 5
    //   214: invokestatic 35	play/amusing/net/sdk/myinter/bw:a	(Ljava/lang/Exception;)V
    //   217: aload 4
    //   219: invokeinterface 100 1 0
    //   224: ifle +10 -> 234
    //   227: aload 4
    //   229: invokeinterface 120 1 0
    //   234: aload_0
    //   235: areturn
    //   236: aload 4
    //   238: invokeinterface 100 1 0
    //   243: ifle +10 -> 253
    //   246: aload 4
    //   248: invokeinterface 120 1 0
    //   253: aload_0
    //   254: athrow
    //   255: iload_1
    //   256: istore_2
    //   257: iconst_0
    //   258: istore_3
    //   259: goto -197 -> 62
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	262	0	paramList	List<a>
    //   0	262	1	paramInt	int
    //   58	199	2	i	int
    //   62	197	3	j	int
    //   31	216	4	localArrayList1	ArrayList
    //   40	164	5	localArrayList2	ArrayList
    //   210	3	5	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   42	59	206	finally
    //   67	82	206	finally
    //   89	94	206	finally
    //   96	123	206	finally
    //   143	168	206	finally
    //   175	186	206	finally
    //   212	217	206	finally
    //   42	59	210	java/lang/Exception
    //   67	82	210	java/lang/Exception
    //   89	94	210	java/lang/Exception
    //   96	123	210	java/lang/Exception
    //   143	168	210	java/lang/Exception
    //   175	186	210	java/lang/Exception
  }
  
  public static void a(Context paramContext, a paramA)
  {
    bi.a(paramContext).a(paramA);
  }
  
  public static void a(WebView paramWebView)
  {
    if (paramWebView != null) {
      try
      {
        paramWebView.removeAllViews();
        paramWebView.destroy();
        bw.a("release webview");
        return;
      }
      catch (Exception paramWebView)
      {
        bw.a(paramWebView);
      }
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean a(String paramString, List<String> paramList, String[] paramArrayOfString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    if (paramArrayOfString != null)
    {
      if (paramArrayOfString.length == 0) {
        return false;
      }
      if (paramList != null)
      {
        if (paramList.size() == 0) {
          return false;
        }
        int i = 0;
        try
        {
          while (i < paramArrayOfString.length)
          {
            if (paramString.startsWith(paramArrayOfString[i]))
            {
              int j = 0;
              while (j < paramList.size())
              {
                boolean bool = ((String)paramList.get(j)).startsWith(paramArrayOfString[i]);
                if (bool) {
                  return true;
                }
                j += 1;
              }
            }
            i += 1;
          }
          return false;
        }
        catch (Exception paramString)
        {
          bw.a(paramString);
        }
      }
    }
  }
  
  public static boolean a(List<?> paramList)
  {
    return (paramList == null) || (paramList.size() <= 0);
  }
  
  public static int[] a(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    return new int[] { paramContext.widthPixels, paramContext.heightPixels };
  }
  
  public static BroadcastReceiver b()
  {
    try
    {
      BroadcastReceiver localBroadcastReceiver = (BroadcastReceiver)e().getConstructor(new Class[0]).newInstance(new Object[0]);
      return localBroadcastReceiver;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static void b(Context paramContext)
  {
    Object localObject2 = bj.b(paramContext);
    Object localObject1 = bj.a(paramContext);
    StringBuilder localStringBuilder = new StringBuilder("init appID ");
    localStringBuilder.append((String)localObject2);
    Log.d("paxr.log", localStringBuilder.toString());
    localObject2 = new StringBuilder("init slotID ");
    ((StringBuilder)localObject2).append((String)localObject1);
    Log.d("paxr.log", ((StringBuilder)localObject2).toString());
    paramContext = new ce(paramContext);
    localObject1 = new StringBuilder("sim nation ");
    ((StringBuilder)localObject1).append(paramContext.a(false));
    Log.d("paxr.log", ((StringBuilder)localObject1).toString());
    paramContext = new StringBuilder("version ");
    paramContext.append(ce.b(false));
    Log.d("paxr.log", paramContext.toString());
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      paramString.addFlags(67108864);
      paramString.addFlags(268435456);
      if (a() > 10) {
        paramString.addFlags(32768);
      }
      paramContext.startActivity(paramString);
      return true;
    }
    catch (Exception paramContext)
    {
      bw.a(paramContext);
    }
    return false;
  }
  
  public static String[] b(String paramString)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = paramString.split("\\|");
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      bw.a(paramString);
    }
    return null;
  }
  
  public static int c(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      bw.a(paramContext);
    }
    return 0;
  }
  
  public static String c()
  {
    try
    {
      long l = System.currentTimeMillis();
      String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(l));
      return str;
    }
    catch (Exception localException)
    {
      bw.a(localException);
    }
    return "";
  }
  
  public static String c(String paramString)
  {
    try
    {
      paramString = Base64.decode(paramString.getBytes(), 2);
      byte[] arrayOfByte = new byte[2];
      arrayOfByte[0] = paramString[0];
      arrayOfByte[1] = paramString[(paramString.length - 1)];
      Object localObject = new StringBuilder("DECODE KEY 0:");
      ((StringBuilder)localObject).append(arrayOfByte[0]);
      bw.a(((StringBuilder)localObject).toString());
      localObject = new StringBuilder("DECODE KEY 1:");
      ((StringBuilder)localObject).append(arrayOfByte[1]);
      bw.a(((StringBuilder)localObject).toString());
      localObject = new byte[paramString.length - 2];
      System.arraycopy(paramString, 1, localObject, 0, localObject.length);
      int i = 0;
      while (i < localObject.length)
      {
        localObject[i] = ((byte)(localObject[i] ^ arrayOfByte[0] ^ arrayOfByte[1]));
        i += 1;
      }
      paramString = new String((byte[])localObject);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static boolean c(Context paramContext)
  {
    try
    {
      boolean bool = TextUtils.isEmpty(bj.d(paramContext));
      return !bool;
    }
    catch (Exception paramContext)
    {
      bw.a(paramContext);
    }
    return false;
  }
  
  public static boolean d()
  {
    try
    {
      int i = a();
      if (i >= 19) {
        return true;
      }
    }
    catch (Exception localException)
    {
      bw.a(localException);
    }
    return false;
  }
  
  public static boolean d(Context paramContext)
  {
    boolean bool = bk.b(paramContext, "s_switch", false);
    if (bool) {
      paramContext = "sdk closed";
    } else {
      paramContext = "sdk open";
    }
    bw.a(paramContext);
    return bool;
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).applicationInfo.flags;
      return (i & 0x1) != 0;
    }
    catch (Exception paramContext)
    {
      bw.a(paramContext);
    }
    return false;
  }
  
  private static Class<?> e()
  {
    try
    {
      localClass = Class.forName("play.amusing.net.sdk.receiver.VReceiver");
    }
    catch (Exception localException)
    {
      Class localClass;
      Object localObject;
      for (;;) {}
    }
    localClass = null;
    if (localClass == null)
    {
      localObject = "receiver not found";
    }
    else
    {
      localObject = new StringBuilder("receiver name = ");
      ((StringBuilder)localObject).append(localClass.getName());
      localObject = ((StringBuilder)localObject).toString();
    }
    bw.a((String)localObject);
    return localClass;
  }
  
  public static String e(Context paramContext, String paramString)
  {
    String str1 = "";
    Object localObject1 = str1;
    try
    {
      paramContext = paramContext.getClassLoader();
      localObject1 = str1;
      Object localObject2 = new StringBuilder("META-INF/");
      localObject1 = str1;
      ((StringBuilder)localObject2).append(paramString);
      localObject1 = str1;
      paramString = paramContext.getResourceAsStream(((StringBuilder)localObject2).toString());
      paramContext = str1;
      if (paramString != null)
      {
        localObject1 = str1;
        localObject2 = new InputStreamReader(paramString);
        localObject1 = str1;
        paramContext = new StringBuilder();
        localObject1 = str1;
        BufferedReader localBufferedReader = new BufferedReader((Reader)localObject2);
        for (;;)
        {
          localObject1 = str1;
          String str2 = localBufferedReader.readLine();
          if (str2 == null) {
            break;
          }
          localObject1 = str1;
          paramContext.append(str2);
        }
        localObject1 = str1;
        paramContext = paramContext.toString();
        localObject1 = paramContext;
        localBufferedReader.close();
        localObject1 = paramContext;
        ((InputStreamReader)localObject2).close();
        localObject1 = paramContext;
        paramString.close();
      }
      return paramContext;
    }
    catch (Exception paramContext) {}
    return localObject1;
  }
  
  public static boolean e(Context paramContext)
  {
    int i;
    if (an.a(paramContext, "flag_48h", 0) == 1) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      return true;
    }
    long l = bk.b(paramContext, "add_app_time", -1L);
    if ((l > 0L) && (System.currentTimeMillis() - l > 172800000L))
    {
      bw.a("last 48h no app ins");
      return false;
    }
    return true;
  }
  
  public static String f(Context paramContext, String paramString)
  {
    String str = "";
    try
    {
      paramString = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 129).metaData.get(paramString);
      paramContext = str;
      if (paramString != null) {
        paramContext = paramString.toString();
      }
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static List<String> f(Context paramContext)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    try
    {
      paramContext = paramContext.getPackageManager();
      int i = 0;
      List localList = paramContext.getInstalledPackages(0);
      paramContext = localObject1;
      if (localList != null)
      {
        paramContext = localObject1;
        if (localList.size() > 0)
        {
          paramContext = new ArrayList();
          try
          {
            while (i < localList.size())
            {
              paramContext.add(((PackageInfo)localList.get(i)).packageName);
              i += 1;
            }
            return paramContext;
          }
          catch (Exception localException1) {}
          bw.a(localException2);
        }
      }
    }
    catch (Exception localException2)
    {
      paramContext = localObject2;
    }
    return paramContext;
  }
  
  public static void g(Context paramContext, String paramString)
  {
    try
    {
      if (q.c(paramContext, paramString)) {
        return;
      }
      dp localDp = new dp();
      localDp.a = paramString;
      localDp.e = "100-100";
      localDp.d = "1-23";
      localDp.b = 20;
      localDp.c = 86400L;
      q.a(paramContext, localDp);
      return;
    }
    catch (Exception paramContext)
    {
      bw.a(paramContext);
    }
  }
  
  public static boolean g(Context paramContext)
  {
    try
    {
      Object localObject = e();
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 2).receivers;
      localObject = ((Class)localObject).getName();
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        if (paramContext[i].name.equals(localObject))
        {
          bw.a("receiver in manifest is true");
          return true;
        }
        i += 1;
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    bw.a("isReceiverInManifest Exception");
    bw.a("receiver in manifest is false");
    return false;
  }
  
  public static boolean h(Context paramContext)
  {
    boolean bool = false;
    try
    {
      if (d())
      {
        paramContext = new StringBuffer(paramContext.getExternalFilesDir("").getAbsolutePath());
        paramContext.append("/Download/");
        File localFile = new File(paramContext.toString());
        if (!localFile.exists()) {
          localFile.mkdirs();
        }
        paramContext.append("temp.bat");
        bool = new File(paramContext.toString()).createNewFile();
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static int i(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      bw.a(paramContext);
    }
    return 0;
  }
}
