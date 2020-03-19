package com.tencent.mm.sdk.platformtools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import java.lang.reflect.Method;

public final class ay
{
  /* Error */
  private static Intent H(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 14	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore 5
    //   6: aload 5
    //   8: iconst_0
    //   9: invokevirtual 20	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   12: astore 6
    //   14: aload 6
    //   16: ifnull +279 -> 295
    //   19: aload 6
    //   21: invokeinterface 26 1 0
    //   26: ifle +269 -> 295
    //   29: ldc 28
    //   31: new 30	java/lang/StringBuilder
    //   34: dup
    //   35: ldc 32
    //   37: invokespecial 36	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   40: aload 6
    //   42: invokeinterface 26 1 0
    //   47: invokevirtual 40	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   50: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokestatic 50	com/tencent/mm/sdk/platformtools/y:az	(Ljava/lang/String;Ljava/lang/String;)V
    //   56: iconst_0
    //   57: istore_2
    //   58: aload 6
    //   60: invokeinterface 26 1 0
    //   65: istore_3
    //   66: iload_2
    //   67: iload_3
    //   68: if_icmpge +227 -> 295
    //   71: ldc 28
    //   73: new 30	java/lang/StringBuilder
    //   76: dup
    //   77: ldc 52
    //   79: invokespecial 36	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   82: aload 6
    //   84: iload_2
    //   85: invokeinterface 56 2 0
    //   90: checkcast 58	android/content/pm/PackageInfo
    //   93: getfield 62	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   96: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: invokestatic 50	com/tencent/mm/sdk/platformtools/y:az	(Ljava/lang/String;Ljava/lang/String;)V
    //   105: new 67	android/content/Intent
    //   108: dup
    //   109: invokespecial 70	android/content/Intent:<init>	()V
    //   112: astore 7
    //   114: aload 7
    //   116: aload 6
    //   118: iload_2
    //   119: invokeinterface 56 2 0
    //   124: checkcast 58	android/content/pm/PackageInfo
    //   127: getfield 62	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   130: invokevirtual 74	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   133: pop
    //   134: aload 5
    //   136: aload 7
    //   138: iconst_0
    //   139: invokevirtual 78	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   142: astore 8
    //   144: aload 8
    //   146: ifnull +125 -> 271
    //   149: aload 8
    //   151: invokeinterface 26 1 0
    //   156: istore_3
    //   157: iload_3
    //   158: ifle +129 -> 287
    //   161: ldc 28
    //   163: new 30	java/lang/StringBuilder
    //   166: dup
    //   167: ldc 80
    //   169: invokespecial 36	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   172: iload_3
    //   173: invokevirtual 40	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   176: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   179: invokestatic 50	com/tencent/mm/sdk/platformtools/y:az	(Ljava/lang/String;Ljava/lang/String;)V
    //   182: iconst_0
    //   183: istore 4
    //   185: iload 4
    //   187: iload_3
    //   188: if_icmpge +99 -> 287
    //   191: aload 8
    //   193: iload 4
    //   195: invokeinterface 56 2 0
    //   200: checkcast 82	android/content/pm/ResolveInfo
    //   203: getfield 86	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   206: astore 7
    //   208: aload 7
    //   210: getfield 91	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   213: aload_1
    //   214: invokevirtual 97	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   217: ifeq +59 -> 276
    //   220: new 67	android/content/Intent
    //   223: dup
    //   224: ldc 99
    //   226: invokespecial 100	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   229: astore 8
    //   231: aload 8
    //   233: new 102	android/content/ComponentName
    //   236: dup
    //   237: aload 7
    //   239: getfield 103	android/content/pm/ActivityInfo:packageName	Ljava/lang/String;
    //   242: aload 7
    //   244: getfield 91	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   247: invokespecial 105	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   250: invokevirtual 109	android/content/Intent:setComponent	(Landroid/content/ComponentName;)Landroid/content/Intent;
    //   253: pop
    //   254: aload 8
    //   256: ldc 111
    //   258: invokevirtual 114	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   261: pop
    //   262: aload_0
    //   263: aload 8
    //   265: invokevirtual 118	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   268: aload 8
    //   270: areturn
    //   271: iconst_0
    //   272: istore_3
    //   273: goto -116 -> 157
    //   276: iload 4
    //   278: iconst_1
    //   279: iadd
    //   280: istore 4
    //   282: goto -97 -> 185
    //   285: astore 7
    //   287: iload_2
    //   288: iconst_1
    //   289: iadd
    //   290: istore_2
    //   291: goto -233 -> 58
    //   294: astore_0
    //   295: aconst_null
    //   296: areturn
    //   297: astore 7
    //   299: goto -12 -> 287
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	302	0	paramContext	Context
    //   0	302	1	paramString	String
    //   57	234	2	i	int
    //   65	208	3	j	int
    //   183	98	4	k	int
    //   4	131	5	localPackageManager	android.content.pm.PackageManager
    //   12	105	6	localList	java.util.List
    //   112	131	7	localObject1	Object
    //   285	1	7	localException1	Exception
    //   297	1	7	localException2	Exception
    //   142	127	8	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   71	144	285	java/lang/Exception
    //   149	157	285	java/lang/Exception
    //   0	14	294	java/lang/Exception
    //   19	56	294	java/lang/Exception
    //   58	66	294	java/lang/Exception
    //   161	182	297	java/lang/Exception
    //   191	268	297	java/lang/Exception
  }
  
  public static boolean aP(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    try
    {
      boolean bool = paramContext.isConnected();
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static String aQ(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return "NON_NETWORK";
    }
    paramContext = paramContext.getActiveNetworkInfo();
    if (paramContext == null) {
      return "NON_NETWORK";
    }
    if (paramContext.getType() == 1) {
      return "WIFI";
    }
    y.e("MicroMsg.NetStatusUtil", "activeNetInfo extra=%s, type=%d", new Object[] { paramContext.getExtraInfo(), Integer.valueOf(paramContext.getType()) });
    if (paramContext.getExtraInfo() != null) {
      return paramContext.getExtraInfo();
    }
    return "MOBILE";
  }
  
  public static int aR(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return 0;
    }
    paramContext = paramContext.getSimOperator();
    if ((paramContext == null) || (paramContext.length() < 5)) {
      return 0;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      int j = paramContext.length();
      int i = j;
      if (j > 6) {
        i = 6;
      }
      for (;;)
      {
        if (j < i) {
          if (!Character.isDigit(paramContext.charAt(j)))
          {
            if (localStringBuilder.length() <= 0) {
              break label119;
            }
          }
          else
          {
            localStringBuilder.append(paramContext.charAt(j));
            break label119;
          }
        }
        i = Integer.valueOf(localStringBuilder.toString()).intValue();
        return i;
        j = 0;
        continue;
        label119:
        j += 1;
      }
      return 0;
    }
    catch (Exception paramContext) {}
  }
  
  public static String aS(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return "";
    }
    y.e("MicroMsg.NetStatusUtil", "getISPName ISPName=%s", new Object[] { paramContext.getSimOperatorName() });
    if (paramContext.getSimOperatorName().length() <= 100) {
      return paramContext.getSimOperatorName();
    }
    return paramContext.getSimOperatorName().substring(0, 100);
  }
  
  public static boolean aT(Context paramContext)
  {
    try
    {
      int i = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo().getType();
      return i != 1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean aU(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext.getType() == 1) {
        return false;
      }
      if (paramContext.getSubtype() != 2)
      {
        int i = paramContext.getSubtype();
        if (i != 1) {}
      }
      else
      {
        return true;
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean aV(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext.getType() == 1) {
        return false;
      }
      int i = paramContext.getSubtype();
      if (i >= 13) {
        return true;
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean aW(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext.getType() == 1) {
        return false;
      }
      if (paramContext.getSubtype() >= 5)
      {
        int i = paramContext.getSubtype();
        if (i < 13) {
          return true;
        }
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean aX(Context paramContext)
  {
    return au(paramContext) == 0;
  }
  
  public static WifiInfo aY(Context paramContext)
  {
    try
    {
      Object localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (localObject == null) {
        return null;
      }
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      if ((localObject != null) && (1 == ((NetworkInfo)localObject).getType()))
      {
        paramContext = (WifiManager)paramContext.getSystemService("wifi");
        if (paramContext == null) {
          return null;
        }
        paramContext = paramContext.getConnectionInfo();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      return null;
    }
    return null;
  }
  
  public static int aZ(Context paramContext)
  {
    int i;
    if (Build.VERSION.SDK_INT >= 14) {
      try
      {
        Object localObject = Class.forName("android.app.ActivityManagerNative");
        localObject = ((Class)localObject).getMethod("getDefault", new Class[0]).invoke(localObject, new Object[0]);
        i = ((Integer)localObject.getClass().getMethod("getProcessLimit", new Class[0]).invoke(localObject, new Object[0])).intValue();
        if (i == 0) {
          return 1;
        }
      }
      catch (Exception localException) {}
    }
    try
    {
      i = Settings.System.getInt(paramContext.getContentResolver(), "wifi_sleep_policy", 2);
      if (i != 2)
      {
        int j = au(paramContext);
        if (j == 0) {}
      }
      else
      {
        return 0;
      }
      if ((i == 1) || (i == 0)) {
        return 3;
      }
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static int au(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return -1;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    if (paramContext == null) {
      return -1;
    }
    if (paramContext.getType() == 1) {
      return 0;
    }
    y.e("MicroMsg.NetStatusUtil", "activeNetInfo extra=%s, type=%d", new Object[] { paramContext.getExtraInfo(), Integer.valueOf(paramContext.getType()) });
    if (paramContext.getExtraInfo() != null)
    {
      if (paramContext.getExtraInfo().equalsIgnoreCase("uninet")) {
        return 1;
      }
      if (paramContext.getExtraInfo().equalsIgnoreCase("uniwap")) {
        return 2;
      }
      if (paramContext.getExtraInfo().equalsIgnoreCase("3gwap")) {
        return 3;
      }
      if (paramContext.getExtraInfo().equalsIgnoreCase("3gnet")) {
        return 4;
      }
      if (paramContext.getExtraInfo().equalsIgnoreCase("cmwap")) {
        return 5;
      }
      if (paramContext.getExtraInfo().equalsIgnoreCase("cmnet")) {
        return 6;
      }
      if (paramContext.getExtraInfo().equalsIgnoreCase("ctwap")) {
        return 7;
      }
      if (paramContext.getExtraInfo().equalsIgnoreCase("ctnet")) {
        return 8;
      }
      if (paramContext.getExtraInfo().equalsIgnoreCase("LTE")) {
        return 10;
      }
    }
    return 9;
  }
  
  public static boolean jp(int paramInt)
  {
    return (paramInt == 2) || (paramInt == 5) || (paramInt == 7) || (paramInt == 3);
  }
  
  public static boolean jq(int paramInt)
  {
    return (paramInt == 2) || (paramInt == 1) || (paramInt == 3);
  }
  
  public static void n(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
    default: 
      return;
    case 2: 
      try
      {
        Intent localIntent1 = new Intent("/");
        localIntent1.setComponent(new ComponentName("com.android.providers.subscribedfeeds", "com.android.settings.ManageAccountsSettings"));
        localIntent1.setAction("android.intent.action.VIEW");
        paramContext.startActivity(localIntent1);
        return;
      }
      catch (Exception localException1)
      {
        try
        {
          Intent localIntent2 = new Intent("/");
          localIntent2.setComponent(new ComponentName("com.htc.settings.accountsync", "com.htc.settings.accountsync.ManageAccountsSettings"));
          localIntent2.setAction("android.intent.action.VIEW");
          paramContext.startActivity(localIntent2);
          return;
        }
        catch (Exception localException2)
        {
          H(paramContext, "ManageAccountsSettings");
          return;
        }
      }
    case 1: 
      try
      {
        Intent localIntent3 = new Intent("/");
        localIntent3.setComponent(new ComponentName("com.android.settings", "com.android.settings.DevelopmentSettings"));
        localIntent3.setAction("android.intent.action.VIEW");
        paramContext.startActivity(localIntent3);
        return;
      }
      catch (Exception localException3)
      {
        H(paramContext, "DevelopmentSettings");
        return;
      }
    }
    try
    {
      Intent localIntent4 = new Intent();
      localIntent4.setAction("android.settings.WIFI_IP_SETTINGS");
      paramContext.startActivity(localIntent4);
      return;
    }
    catch (Exception localException4)
    {
      H(paramContext, "AdvancedSettings");
    }
  }
}
