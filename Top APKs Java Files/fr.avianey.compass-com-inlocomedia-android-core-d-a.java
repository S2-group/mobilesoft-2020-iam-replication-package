package com.inlocomedia.android.core.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.inlocomedia.android.core.a.e;
import com.inlocomedia.android.core.c.z;
import com.inlocomedia.android.core.c.z.a;
import com.inlocomedia.android.core.e.s;
import com.inlocomedia.android.core.e.y;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class a
{
  public static final String a = Build.MODEL;
  public static final String b = Build.MANUFACTURER;
  public static final String c = Build.DEVICE;
  public static final int d = Build.VERSION.SDK_INT;
  private static final String e = e.a(a.class);
  
  private a() {}
  
  private static String a()
  {
    StringBuilder localStringBuilder = new StringBuilder("ILM-ID-");
    localStringBuilder.append(UUID.randomUUID().toString());
    return localStringBuilder.toString();
  }
  
  public static String a(Context paramContext)
  {
    String str2 = s.c(paramContext);
    String str1 = str2;
    if (str2 == null) {
      str1 = d(paramContext);
    }
    return str1;
  }
  
  public static HashSet<String> a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    HashSet localHashSet = new HashSet();
    try
    {
      List localList = paramContext.getPackageManager().getInstalledApplications(0);
      Object localObject = localList;
      if (paramBoolean2)
      {
        localObject = localList;
        if (!a(paramContext, localList)) {
          localObject = f(paramContext);
        }
      }
      localObject = ((List)localObject).iterator();
      for (;;)
      {
        paramContext = localHashSet;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        paramContext = (ApplicationInfo)((Iterator)localObject).next();
        if (((paramContext.flags & 0x1) == 0) || (!paramBoolean1)) {
          localHashSet.add(paramContext.packageName);
        }
      }
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    paramContext = null;
    return paramContext;
  }
  
  public static List<String> a(TelephonyManager paramTelephonyManager)
  {
    try
    {
      paramTelephonyManager = a(paramTelephonyManager.getSimOperatorName());
      return paramTelephonyManager;
    }
    catch (Exception paramTelephonyManager)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static List<String> a(String paramString)
  {
    if (com.inlocomedia.android.core.e.a.a(paramString)) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramString = paramString.split(",");
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString[i];
      if ((!localObject.isEmpty()) && (!localObject.equals("null"))) {
        localArrayList.add(localObject);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private static boolean a(Context paramContext, List<ApplicationInfo> paramList)
  {
    if (paramList == null) {
      return false;
    }
    paramContext = paramContext.getPackageName();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      if (paramContext.equals(((ApplicationInfo)paramList.next()).packageName)) {
        return true;
      }
    }
    return false;
  }
  
  public static List<String> b(TelephonyManager paramTelephonyManager)
  {
    try
    {
      paramTelephonyManager = a(paramTelephonyManager.getNetworkOperatorName());
      return paramTelephonyManager;
    }
    catch (Exception paramTelephonyManager)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static boolean b(Context paramContext)
  {
    return !s.d(paramContext);
  }
  
  public static String c(Context paramContext)
  {
    return s.c(paramContext);
  }
  
  public static List<String> c(TelephonyManager paramTelephonyManager)
  {
    try
    {
      paramTelephonyManager = a(paramTelephonyManager.getSimCountryIso());
      return paramTelephonyManager;
    }
    catch (Exception paramTelephonyManager)
    {
      for (;;) {}
    }
    return null;
  }
  
  @SuppressLint({"CommitPrefEdits"})
  public static String d(Context paramContext)
  {
    z localZ = z.a(paramContext);
    String str = localZ.a("7OLmdmz8vjKtQQKjEuy0").b("zbAsYg5vOUMV3SWbst7A");
    paramContext = str;
    if (str == null)
    {
      paramContext = a();
      localZ.a("7OLmdmz8vjKtQQKjEuy0").b("zbAsYg5vOUMV3SWbst7A", paramContext).a();
    }
    return paramContext;
  }
  
  public static List<String> d(TelephonyManager paramTelephonyManager)
  {
    try
    {
      paramTelephonyManager = a(paramTelephonyManager.getNetworkCountryIso());
      return paramTelephonyManager;
    }
    catch (Exception paramTelephonyManager)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String e(Context paramContext)
  {
    paramContext = y.a(Settings.Secure.getString(paramContext.getContentResolver(), "android_id"));
    if (paramContext != null) {
      return paramContext.toUpperCase(Locale.US);
    }
    return null;
  }
  
  /* Error */
  private static List<ApplicationInfo> f(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 44	com/inlocomedia/android/core/d/a:e	Ljava/lang/String;
    //   3: invokestatic 244	com/inlocomedia/android/core/e/a:b	(Ljava/lang/String;)V
    //   6: aload_0
    //   7: invokevirtual 89	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   10: astore_3
    //   11: new 151	java/util/ArrayList
    //   14: dup
    //   15: invokespecial 152	java/util/ArrayList:<init>	()V
    //   18: astore_2
    //   19: aconst_null
    //   20: astore_1
    //   21: new 246	java/io/BufferedReader
    //   24: dup
    //   25: new 248	java/io/InputStreamReader
    //   28: dup
    //   29: invokestatic 254	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   32: ldc_w 256
    //   35: invokevirtual 260	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   38: invokevirtual 266	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   41: invokespecial 269	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   44: invokespecial 272	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   47: astore_0
    //   48: aload_0
    //   49: invokevirtual 275	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   52: astore_1
    //   53: aload_1
    //   54: ifnull +30 -> 84
    //   57: aload_2
    //   58: aload_3
    //   59: aload_1
    //   60: aload_1
    //   61: bipush 58
    //   63: invokevirtual 279	java/lang/String:indexOf	(I)I
    //   66: iconst_1
    //   67: iadd
    //   68: invokevirtual 283	java/lang/String:substring	(I)Ljava/lang/String;
    //   71: iconst_0
    //   72: invokevirtual 287	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   75: invokeinterface 288 2 0
    //   80: pop
    //   81: goto -33 -> 48
    //   84: aload_0
    //   85: invokevirtual 291	java/io/BufferedReader:close	()V
    //   88: aload_2
    //   89: areturn
    //   90: astore_2
    //   91: aload_0
    //   92: astore_1
    //   93: aload_2
    //   94: astore_0
    //   95: goto +4 -> 99
    //   98: astore_0
    //   99: aload_1
    //   100: ifnull +7 -> 107
    //   103: aload_1
    //   104: invokevirtual 291	java/io/BufferedReader:close	()V
    //   107: aload_0
    //   108: athrow
    //   109: aconst_null
    //   110: astore_0
    //   111: aload_0
    //   112: ifnull +7 -> 119
    //   115: aload_0
    //   116: invokevirtual 291	java/io/BufferedReader:close	()V
    //   119: aconst_null
    //   120: areturn
    //   121: astore_0
    //   122: goto -13 -> 109
    //   125: astore_1
    //   126: goto -15 -> 111
    //   129: astore_0
    //   130: aload_2
    //   131: areturn
    //   132: astore_1
    //   133: goto -26 -> 107
    //   136: astore_0
    //   137: goto -18 -> 119
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	paramContext	Context
    //   20	84	1	localObject	Object
    //   125	1	1	localException	Exception
    //   132	1	1	localIOException	java.io.IOException
    //   18	71	2	localArrayList	ArrayList
    //   90	41	2	localList	List<ApplicationInfo>
    //   10	49	3	localPackageManager	PackageManager
    // Exception table:
    //   from	to	target	type
    //   48	53	90	finally
    //   57	81	90	finally
    //   21	48	98	finally
    //   21	48	121	java/lang/Exception
    //   48	53	125	java/lang/Exception
    //   57	81	125	java/lang/Exception
    //   84	88	129	java/io/IOException
    //   103	107	132	java/io/IOException
    //   115	119	136	java/io/IOException
  }
}
