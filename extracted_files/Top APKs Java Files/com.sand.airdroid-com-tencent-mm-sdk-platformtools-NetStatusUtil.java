package com.tencent.mm.sdk.platformtools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class NetStatusUtil
{
  public static final int a = -1;
  public static final int b = 0;
  public static final int c = 1;
  public static final int d = 2;
  public static final int e = 3;
  public static final int f = 4;
  public static final int g = 5;
  public static final int h = 6;
  public static final int i = 7;
  public static final int j = 8;
  public static final int k = 9;
  public static final int l = 10;
  public static final int m = 0;
  public static final int n = 1;
  public static final int o = 0;
  public static final int p = 1;
  public static final int q = 2;
  public static final int r = 3;
  
  public NetStatusUtil() {}
  
  private static Intent a(Context paramContext, String paramString)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      List localList = localPackageManager.getInstalledPackages(0);
      if ((localList != null) && (localList.size() > 0))
      {
        Log.b("MicroMsg.NetStatusUtil", "package  size" + localList.size());
        int i1 = 0;
        for (;;)
        {
          int i2 = localList.size();
          if (i1 >= i2) {
            break;
          }
          try
          {
            Log.b("MicroMsg.NetStatusUtil", "package " + ((PackageInfo)localList.get(i1)).packageName);
            Object localObject1 = new Intent();
            ((Intent)localObject1).setPackage(((PackageInfo)localList.get(i1)).packageName);
            Object localObject2 = localPackageManager.queryIntentActivities((Intent)localObject1, 0);
            if (localObject2 != null) {
              i2 = ((List)localObject2).size();
            }
            for (;;)
            {
              if (i2 > 0) {
                try
                {
                  Log.b("MicroMsg.NetStatusUtil", "activityName count " + i2);
                  int i3 = 0;
                  for (;;)
                  {
                    if (i3 >= i2) {
                      break label292;
                    }
                    localObject1 = ((ResolveInfo)((List)localObject2).get(i3)).activityInfo;
                    if (((ActivityInfo)localObject1).name.contains(paramString))
                    {
                      localObject2 = new Intent("/");
                      ((Intent)localObject2).setComponent(new ComponentName(((ActivityInfo)localObject1).packageName, ((ActivityInfo)localObject1).name));
                      ((Intent)localObject2).setAction("android.intent.action.VIEW");
                      paramContext.startActivity((Intent)localObject2);
                      return localObject2;
                      i2 = 0;
                      break;
                    }
                    i3 += 1;
                  }
                  i1 += 1;
                }
                catch (Exception localException1)
                {
                  localException1.printStackTrace();
                }
              }
            }
          }
          catch (Exception localException2)
          {
            for (;;)
            {
              label292:
              localException2.printStackTrace();
            }
          }
        }
      }
      return null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void a(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      Log.b("MicroMsg.NetStatusUtil", "isAvailable " + paramContext.isAvailable());
      Log.b("MicroMsg.NetStatusUtil", "isConnected " + paramContext.isConnected());
      Log.b("MicroMsg.NetStatusUtil", "isRoaming " + paramContext.isRoaming());
      Log.b("MicroMsg.NetStatusUtil", "isFailover " + paramContext.isFailover());
      Log.b("MicroMsg.NetStatusUtil", "getSubtypeName " + paramContext.getSubtypeName());
      Log.b("MicroMsg.NetStatusUtil", "getExtraInfo " + paramContext.getExtraInfo());
      Log.b("MicroMsg.NetStatusUtil", "activeNetInfo " + paramContext.toString());
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void a(Context paramContext, int paramInt)
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
          a(paramContext, "ManageAccountsSettings");
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
        a(paramContext, "DevelopmentSettings");
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
      a(paramContext, "AdvancedSettings");
    }
  }
  
  /* Error */
  private static boolean a()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: invokestatic 233	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   7: ldc -21
    //   9: invokevirtual 239	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   12: astore_0
    //   13: new 241	java/io/DataOutputStream
    //   16: dup
    //   17: aload_0
    //   18: invokevirtual 247	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   21: invokespecial 250	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   24: astore_1
    //   25: aload_1
    //   26: ldc -4
    //   28: invokevirtual 255	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   31: aload_1
    //   32: invokevirtual 258	java/io/DataOutputStream:flush	()V
    //   35: aload_0
    //   36: invokevirtual 261	java/lang/Process:waitFor	()I
    //   39: pop
    //   40: aload_1
    //   41: invokevirtual 264	java/io/DataOutputStream:close	()V
    //   44: aload_0
    //   45: ifnull +7 -> 52
    //   48: aload_0
    //   49: invokevirtual 267	java/lang/Process:destroy	()V
    //   52: iconst_1
    //   53: ireturn
    //   54: astore_0
    //   55: aload_0
    //   56: invokevirtual 152	java/lang/Exception:printStackTrace	()V
    //   59: goto -7 -> 52
    //   62: astore_0
    //   63: aconst_null
    //   64: astore_1
    //   65: ldc 63
    //   67: new 65	java/lang/StringBuilder
    //   70: dup
    //   71: ldc_w 269
    //   74: invokespecial 70	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   77: aload_0
    //   78: invokevirtual 272	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   81: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokestatic 274	com/tencent/mm/sdk/platformtools/Log:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   90: aload_1
    //   91: ifnull +7 -> 98
    //   94: aload_1
    //   95: invokevirtual 264	java/io/DataOutputStream:close	()V
    //   98: aload_2
    //   99: ifnull +7 -> 106
    //   102: aload_2
    //   103: invokevirtual 267	java/lang/Process:destroy	()V
    //   106: iconst_0
    //   107: ireturn
    //   108: astore_0
    //   109: aload_0
    //   110: invokevirtual 152	java/lang/Exception:printStackTrace	()V
    //   113: goto -7 -> 106
    //   116: astore_0
    //   117: aconst_null
    //   118: astore_3
    //   119: aload_1
    //   120: astore_2
    //   121: aload_3
    //   122: astore_1
    //   123: aload_1
    //   124: ifnull +7 -> 131
    //   127: aload_1
    //   128: invokevirtual 264	java/io/DataOutputStream:close	()V
    //   131: aload_2
    //   132: ifnull +7 -> 139
    //   135: aload_2
    //   136: invokevirtual 267	java/lang/Process:destroy	()V
    //   139: aload_0
    //   140: athrow
    //   141: astore_1
    //   142: aload_1
    //   143: invokevirtual 152	java/lang/Exception:printStackTrace	()V
    //   146: goto -7 -> 139
    //   149: astore_3
    //   150: aconst_null
    //   151: astore_1
    //   152: aload_0
    //   153: astore_2
    //   154: aload_3
    //   155: astore_0
    //   156: goto -33 -> 123
    //   159: astore_3
    //   160: aload_0
    //   161: astore_2
    //   162: aload_3
    //   163: astore_0
    //   164: goto -41 -> 123
    //   167: astore_0
    //   168: goto -45 -> 123
    //   171: astore_3
    //   172: aconst_null
    //   173: astore_1
    //   174: aload_0
    //   175: astore_2
    //   176: aload_3
    //   177: astore_0
    //   178: goto -113 -> 65
    //   181: astore_3
    //   182: aload_0
    //   183: astore_2
    //   184: aload_3
    //   185: astore_0
    //   186: goto -121 -> 65
    // Local variable table:
    //   start	length	slot	name	signature
    //   12	37	0	localProcess	Process
    //   54	2	0	localException1	Exception
    //   62	16	0	localException2	Exception
    //   108	2	0	localException3	Exception
    //   116	37	0	localObject1	Object
    //   155	9	0	localObject2	Object
    //   167	8	0	localObject3	Object
    //   177	9	0	localObject4	Object
    //   1	127	1	localObject5	Object
    //   141	2	1	localException4	Exception
    //   151	23	1	localObject6	Object
    //   3	181	2	localObject7	Object
    //   118	4	3	localObject8	Object
    //   149	6	3	localObject9	Object
    //   159	4	3	localObject10	Object
    //   171	6	3	localException5	Exception
    //   181	4	3	localException6	Exception
    // Exception table:
    //   from	to	target	type
    //   40	44	54	java/lang/Exception
    //   48	52	54	java/lang/Exception
    //   4	13	62	java/lang/Exception
    //   94	98	108	java/lang/Exception
    //   102	106	108	java/lang/Exception
    //   4	13	116	finally
    //   127	131	141	java/lang/Exception
    //   135	139	141	java/lang/Exception
    //   13	25	149	finally
    //   25	40	159	finally
    //   65	90	167	finally
    //   13	25	171	java/lang/Exception
    //   25	40	181	java/lang/Exception
  }
  
  private static boolean a(int paramInt)
  {
    return (paramInt == 2) || (paramInt == 5) || (paramInt == 7) || (paramInt == 3);
  }
  
  private static boolean b(int paramInt)
  {
    return (paramInt == 1) || (paramInt == 2) || (paramInt == 5) || (paramInt == 6) || (paramInt == 7) || (paramInt == 8);
  }
  
  private static boolean b(Context paramContext)
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
  
  private static String c(Context paramContext)
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
    Log.e("MicroMsg.NetStatusUtil", "activeNetInfo.getExtraInfo()  " + paramContext.getExtraInfo());
    Log.e("MicroMsg.NetStatusUtil", "activeNetInfo.getType()  " + paramContext.getType());
    if (paramContext.getExtraInfo() != null) {
      return paramContext.getExtraInfo();
    }
    return "MOBILE";
  }
  
  private static boolean c(int paramInt)
  {
    return paramInt == 10;
  }
  
  private static int d(Context paramContext)
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
    Log.e("MicroMsg.NetStatusUtil", "activeNetInfo.getExtraInfo()  " + paramContext.getExtraInfo());
    Log.e("MicroMsg.NetStatusUtil", "activeNetInfo.getType()  " + paramContext.getType());
    if (paramContext.getExtraInfo() != null)
    {
      if (paramContext.getExtraInfo().equals("uninet")) {
        return 1;
      }
      if (paramContext.getExtraInfo().equals("uniwap")) {
        return 2;
      }
      if (paramContext.getExtraInfo().equals("3gwap")) {
        return 3;
      }
      if (paramContext.getExtraInfo().equals("3gnet")) {
        return 4;
      }
      if (paramContext.getExtraInfo().equals("cmwap")) {
        return 5;
      }
      if (paramContext.getExtraInfo().equals("cmnet")) {
        return 6;
      }
      if (paramContext.getExtraInfo().equals("ctwap")) {
        return 7;
      }
      if (paramContext.getExtraInfo().equals("ctnet")) {
        return 8;
      }
      if (paramContext.getExtraInfo().equals("LTE")) {
        return 10;
      }
    }
    return 9;
  }
  
  private static boolean d(int paramInt)
  {
    return (paramInt == 3) || (paramInt == 4);
  }
  
  private static boolean e(int paramInt)
  {
    return (d(paramInt)) || (b(paramInt)) || (c(paramInt));
  }
  
  private static boolean e(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext.getType() == 1) {
        return false;
      }
      if (paramContext.getSubtype() != 2)
      {
        int i1 = paramContext.getSubtype();
        if (i1 != 1) {}
      }
      else
      {
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private static boolean f(int paramInt)
  {
    return paramInt == 0;
  }
  
  private static boolean f(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext.getType() == 1) {
        return false;
      }
      int i1 = paramContext.getSubtype();
      if (i1 >= 13) {
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private static boolean g(int paramInt)
  {
    return (paramInt == 2) || (paramInt == 1) || (paramInt == 3);
  }
  
  private static boolean g(Context paramContext)
  {
    int i1 = d(paramContext);
    return (i1 == 2) || (i1 == 5) || (i1 == 7) || (i1 == 3);
  }
  
  private static boolean h(int paramInt)
  {
    try
    {
      a();
      NodeList localNodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File("/data/system/netpolicy.xml"))).getDocumentElement().getElementsByTagName("uid-policy");
      int i1 = 0;
      while (i1 < localNodeList.getLength())
      {
        Object localObject = (Element)localNodeList.item(i1);
        String str = ((Element)localObject).getAttribute("uid");
        localObject = ((Element)localObject).getAttribute("policy");
        Log.b("MicroMsg.NetStatusUtil", "uid is " + str + "  policy is " + (String)localObject);
        if (str.equals(Integer.toString(paramInt)))
        {
          if (Integer.parseInt((String)localObject) == 1) {
            return true;
          }
          int i2 = Integer.parseInt((String)localObject);
          if (i2 == 0) {
            return false;
          }
        }
        i1 += 1;
      }
      return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private static boolean h(Context paramContext)
  {
    int i1 = d(paramContext);
    return (d(i1)) || (b(i1)) || (c(i1));
  }
  
  private static boolean i(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext.getType() == 1) {
        return false;
      }
      if (paramContext.getSubtype() >= 5)
      {
        int i1 = paramContext.getSubtype();
        if (i1 < 13) {
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private static boolean j(Context paramContext)
  {
    return d(paramContext) == 0;
  }
  
  private static int k(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "wifi_sleep_policy", 2);
  }
  
  private static int l(Context paramContext)
  {
    int i1;
    if (Build.VERSION.SDK_INT >= 14) {
      try
      {
        Object localObject = Class.forName("android.app.ActivityManagerNative");
        localObject = ((Class)localObject).getMethod("getDefault", new Class[0]).invoke(localObject, new Object[0]);
        i1 = ((Integer)localObject.getClass().getMethod("getProcessLimit", new Class[0]).invoke(localObject, new Object[0])).intValue();
        if (i1 == 0) {
          return 1;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    try
    {
      i1 = Settings.System.getInt(paramContext.getContentResolver(), "wifi_sleep_policy", 2);
      if (i1 == 2) {
        return 0;
      }
      if ((i1 == 1) || (i1 == 0)) {
        return 3;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  private static boolean m(Context paramContext)
  {
    int i1 = paramContext.getApplicationInfo().uid;
    try
    {
      Object localObject = Class.forName("android.net.NetworkPolicyManager");
      paramContext = ((Class)localObject).getMethod("getSystemService", new Class[] { Context.class }).invoke(localObject, new Object[] { paramContext });
      localObject = ((Class)localObject).getDeclaredField("mService");
      ((Field)localObject).setAccessible(true);
      paramContext = ((Field)localObject).get(paramContext);
      int i2 = ((Integer)paramContext.getClass().getMethod("getUidPolicy", new Class[] { Integer.TYPE }).invoke(paramContext, new Object[] { Integer.valueOf(i1) })).intValue();
      Log.b("MicroMsg.NetStatusUtil", "policy is " + i2);
      if (i2 == 1) {
        return true;
      }
      if (i2 == 0) {
        return false;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return h(i1);
  }
  
  private static boolean n(Context paramContext)
  {
    boolean bool = false;
    if (Settings.System.getInt(paramContext.getContentResolver(), "always_finish_activities", 0) != 0) {
      bool = true;
    }
    return bool;
  }
}
