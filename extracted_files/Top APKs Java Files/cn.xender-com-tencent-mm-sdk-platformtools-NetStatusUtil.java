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
import android.telephony.TelephonyManager;
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
  public static final int CMNET = 6;
  public static final int CMWAP = 5;
  public static final int CTNET = 8;
  public static final int CTWAP = 7;
  public static final int LTE = 10;
  public static final int MOBILE = 9;
  public static final int NET_3G = 4;
  public static final int NON_NETWORK = -1;
  public static final int NO_SIM_OPERATOR = 0;
  public static final int POLICY_NONE = 0;
  public static final int POLICY_REJECT_METERED_BACKGROUND = 1;
  public static final int TBACKGROUND_DATA_LIMITED = 2;
  public static final int TBACKGROUND_NOT_LIMITED = 0;
  public static final int TBACKGROUND_PROCESS_LIMITED = 1;
  public static final int TBACKGROUND_WIFI_LIMITED = 3;
  public static final int UNINET = 1;
  public static final int UNIWAP = 2;
  public static final int WAP_3G = 3;
  public static final int WIFI = 0;
  
  public NetStatusUtil() {}
  
  private static Intent a(Context paramContext, String paramString)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      List localList = localPackageManager.getInstalledPackages(0);
      if ((localList != null) && (localList.size() > 0))
      {
        Log.e("MicroMsg.NetStatusUtil", "package  size" + localList.size());
        int i = 0;
        for (;;)
        {
          int j = localList.size();
          if (i >= j) {
            break;
          }
          try
          {
            Log.e("MicroMsg.NetStatusUtil", "package " + ((PackageInfo)localList.get(i)).packageName);
            Object localObject1 = new Intent();
            ((Intent)localObject1).setPackage(((PackageInfo)localList.get(i)).packageName);
            Object localObject2 = localPackageManager.queryIntentActivities((Intent)localObject1, 0);
            if (localObject2 != null) {
              j = ((List)localObject2).size();
            }
            for (;;)
            {
              if (j > 0) {
                try
                {
                  Log.e("MicroMsg.NetStatusUtil", "activityName count " + j);
                  int k = 0;
                  for (;;)
                  {
                    if (k >= j) {
                      break label292;
                    }
                    localObject1 = ((ResolveInfo)((List)localObject2).get(k)).activityInfo;
                    if (((ActivityInfo)localObject1).name.contains(paramString))
                    {
                      localObject2 = new Intent("/");
                      ((Intent)localObject2).setComponent(new ComponentName(((ActivityInfo)localObject1).packageName, ((ActivityInfo)localObject1).name));
                      ((Intent)localObject2).setAction("android.intent.action.VIEW");
                      paramContext.startActivity((Intent)localObject2);
                      return localObject2;
                      j = 0;
                      break;
                    }
                    k += 1;
                  }
                  i += 1;
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
  
  public static boolean checkFromXml(int paramInt)
  {
    try
    {
      runRootCommand();
      NodeList localNodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File("/data/system/netpolicy.xml"))).getDocumentElement().getElementsByTagName("uid-policy");
      int i = 0;
      while (i < localNodeList.getLength())
      {
        Object localObject = (Element)localNodeList.item(i);
        String str = ((Element)localObject).getAttribute("uid");
        localObject = ((Element)localObject).getAttribute("policy");
        Log.e("MicroMsg.NetStatusUtil", "uid is " + str + "  policy is " + (String)localObject);
        if (str.equals(Integer.toString(paramInt)))
        {
          if (Integer.parseInt((String)localObject) == 1) {
            return true;
          }
          int j = Integer.parseInt((String)localObject);
          if (j == 0) {
            return false;
          }
        }
        i += 1;
      }
      return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void dumpNetStatus(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      Log.e("MicroMsg.NetStatusUtil", "isAvailable " + paramContext.isAvailable());
      Log.e("MicroMsg.NetStatusUtil", "isConnected " + paramContext.isConnected());
      Log.e("MicroMsg.NetStatusUtil", "isRoaming " + paramContext.isRoaming());
      Log.e("MicroMsg.NetStatusUtil", "isFailover " + paramContext.isFailover());
      Log.e("MicroMsg.NetStatusUtil", "getSubtypeName " + paramContext.getSubtypeName());
      Log.e("MicroMsg.NetStatusUtil", "getExtraInfo " + paramContext.getExtraInfo());
      Log.e("MicroMsg.NetStatusUtil", "activeNetInfo " + paramContext.toString());
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static int getBackgroundLimitType(Context paramContext)
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
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    try
    {
      i = getWifiSleeepPolicy(paramContext);
      if (i != 2)
      {
        int j = getNetType(paramContext);
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
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int getISPCode(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return 0;
    }
    paramContext = paramContext.getSimOperator();
    if ((paramContext == null) || (paramContext.length() < 5)) {
      return 0;
    }
    paramContext = paramContext.substring(0, 5);
    Log.d("MicroMsg.NetStatusUtil", "getISPCode MCC_MNC=%s", new Object[] { paramContext });
    return Integer.valueOf(paramContext).intValue();
  }
  
  public static String getISPName(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return "";
    }
    Log.d("MicroMsg.NetStatusUtil", "getISPName ISPName=%s", new Object[] { paramContext.getSimOperatorName() });
    if (paramContext.getSimOperatorName().length() <= 100) {
      return paramContext.getSimOperatorName();
    }
    return paramContext.getSimOperatorName().substring(0, 100);
  }
  
  public static int getNetType(Context paramContext)
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
    Log.d("MicroMsg.NetStatusUtil", "activeNetInfo extra=%s, type=%d", new Object[] { paramContext.getExtraInfo(), Integer.valueOf(paramContext.getType()) });
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
  
  public static String getNetTypeString(Context paramContext)
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
    Log.d("MicroMsg.NetStatusUtil", "activeNetInfo extra=%s, type=%d", new Object[] { paramContext.getExtraInfo(), Integer.valueOf(paramContext.getType()) });
    if (paramContext.getExtraInfo() != null) {
      return paramContext.getExtraInfo();
    }
    return "MOBILE";
  }
  
  public static int getWifiSleeepPolicy(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "wifi_sleep_policy", 2);
  }
  
  public static int guessNetSpeed(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext.getType() == 1) {
        return 102400;
      }
      int i = paramContext.getSubtype();
      switch (i)
      {
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    return 102400;
    return 4096;
    return 8192;
    return 102400;
  }
  
  public static boolean is2G(int paramInt)
  {
    return (paramInt == 1) || (paramInt == 2) || (paramInt == 5) || (paramInt == 6) || (paramInt == 7) || (paramInt == 8);
  }
  
  public static boolean is2G(Context paramContext)
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
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean is3G(int paramInt)
  {
    return (paramInt == 3) || (paramInt == 4);
  }
  
  public static boolean is3G(Context paramContext)
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
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean is4G(int paramInt)
  {
    return paramInt == 10;
  }
  
  public static boolean is4G(Context paramContext)
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
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isConnected(Context paramContext)
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
  
  public static boolean isImmediatelyDestroyActivities(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "always_finish_activities", 0) != 0;
  }
  
  public static boolean isLimited(int paramInt)
  {
    return (paramInt == 2) || (paramInt == 1) || (paramInt == 3);
  }
  
  public static boolean isMobile(int paramInt)
  {
    return (is3G(paramInt)) || (is2G(paramInt)) || (is4G(paramInt));
  }
  
  public static boolean isMobile(Context paramContext)
  {
    int i = getNetType(paramContext);
    return (is3G(i)) || (is2G(i)) || (is4G(i));
  }
  
  public static boolean isRestrictBacground(Context paramContext)
  {
    int i = paramContext.getApplicationInfo().uid;
    try
    {
      Object localObject = Class.forName("android.net.NetworkPolicyManager");
      paramContext = ((Class)localObject).getMethod("getSystemService", new Class[] { Context.class }).invoke(localObject, new Object[] { paramContext });
      localObject = ((Class)localObject).getDeclaredField("mService");
      ((Field)localObject).setAccessible(true);
      paramContext = ((Field)localObject).get(paramContext);
      int j = ((Integer)paramContext.getClass().getMethod("getUidPolicy", new Class[] { Integer.TYPE }).invoke(paramContext, new Object[] { Integer.valueOf(i) })).intValue();
      Log.e("MicroMsg.NetStatusUtil", "policy is " + j);
      if (j == 1) {
        return true;
      }
      if (j == 0) {
        return false;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return checkFromXml(i);
  }
  
  public static boolean isWap(int paramInt)
  {
    return (paramInt == 2) || (paramInt == 5) || (paramInt == 7) || (paramInt == 3);
  }
  
  public static boolean isWap(Context paramContext)
  {
    return isWap(getNetType(paramContext));
  }
  
  public static boolean isWifi(int paramInt)
  {
    return paramInt == 0;
  }
  
  public static boolean isWifi(Context paramContext)
  {
    return isWifi(getNetType(paramContext));
  }
  
  /* Error */
  public static boolean runRootCommand()
  {
    // Byte code:
    //   0: invokestatic 477	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   3: ldc_w 479
    //   6: invokevirtual 483	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   9: astore_0
    //   10: new 485	java/io/DataOutputStream
    //   13: dup
    //   14: aload_0
    //   15: invokevirtual 491	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   18: invokespecial 494	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   21: astore_1
    //   22: aload_1
    //   23: ldc_w 496
    //   26: invokevirtual 499	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   29: aload_1
    //   30: invokevirtual 502	java/io/DataOutputStream:flush	()V
    //   33: aload_0
    //   34: invokevirtual 505	java/lang/Process:waitFor	()I
    //   37: pop
    //   38: aload_1
    //   39: invokevirtual 508	java/io/DataOutputStream:close	()V
    //   42: aload_0
    //   43: ifnull +7 -> 50
    //   46: aload_0
    //   47: invokevirtual 511	java/lang/Process:destroy	()V
    //   50: iconst_1
    //   51: ireturn
    //   52: astore_0
    //   53: aload_0
    //   54: invokevirtual 155	java/lang/Exception:printStackTrace	()V
    //   57: goto -7 -> 50
    //   60: astore_0
    //   61: aconst_null
    //   62: astore_1
    //   63: aconst_null
    //   64: astore_2
    //   65: ldc 65
    //   67: new 67	java/lang/StringBuilder
    //   70: dup
    //   71: ldc_w 513
    //   74: invokespecial 72	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   77: aload_0
    //   78: invokevirtual 516	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   81: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokestatic 518	com/tencent/mm/sdk/platformtools/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   90: aload_1
    //   91: ifnull +7 -> 98
    //   94: aload_1
    //   95: invokevirtual 508	java/io/DataOutputStream:close	()V
    //   98: aload_2
    //   99: ifnull +7 -> 106
    //   102: aload_2
    //   103: invokevirtual 511	java/lang/Process:destroy	()V
    //   106: iconst_0
    //   107: ireturn
    //   108: astore_0
    //   109: aload_0
    //   110: invokevirtual 155	java/lang/Exception:printStackTrace	()V
    //   113: goto -7 -> 106
    //   116: astore_0
    //   117: aconst_null
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_2
    //   121: aload_1
    //   122: ifnull +7 -> 129
    //   125: aload_1
    //   126: invokevirtual 508	java/io/DataOutputStream:close	()V
    //   129: aload_2
    //   130: ifnull +7 -> 137
    //   133: aload_2
    //   134: invokevirtual 511	java/lang/Process:destroy	()V
    //   137: aload_0
    //   138: athrow
    //   139: astore_1
    //   140: aload_1
    //   141: invokevirtual 155	java/lang/Exception:printStackTrace	()V
    //   144: goto -7 -> 137
    //   147: astore_1
    //   148: aload_0
    //   149: astore_2
    //   150: aload_1
    //   151: astore_0
    //   152: aconst_null
    //   153: astore_1
    //   154: goto -33 -> 121
    //   157: astore_3
    //   158: aload_0
    //   159: astore_2
    //   160: aload_3
    //   161: astore_0
    //   162: goto -41 -> 121
    //   165: astore_0
    //   166: goto -45 -> 121
    //   169: astore_1
    //   170: aload_0
    //   171: astore_2
    //   172: aload_1
    //   173: astore_0
    //   174: aconst_null
    //   175: astore_1
    //   176: goto -111 -> 65
    //   179: astore_3
    //   180: aload_0
    //   181: astore_2
    //   182: aload_3
    //   183: astore_0
    //   184: goto -119 -> 65
    // Local variable table:
    //   start	length	slot	name	signature
    //   9	38	0	localProcess	Process
    //   52	2	0	localException1	Exception
    //   60	18	0	localException2	Exception
    //   108	2	0	localException3	Exception
    //   116	33	0	localObject1	Object
    //   151	11	0	localObject2	Object
    //   165	6	0	localObject3	Object
    //   173	11	0	localObject4	Object
    //   21	105	1	localDataOutputStream	java.io.DataOutputStream
    //   139	2	1	localException4	Exception
    //   147	4	1	localObject5	Object
    //   153	1	1	localObject6	Object
    //   169	4	1	localException5	Exception
    //   175	1	1	localObject7	Object
    //   64	118	2	localObject8	Object
    //   157	4	3	localObject9	Object
    //   179	4	3	localException6	Exception
    // Exception table:
    //   from	to	target	type
    //   38	42	52	java/lang/Exception
    //   46	50	52	java/lang/Exception
    //   0	10	60	java/lang/Exception
    //   94	98	108	java/lang/Exception
    //   102	106	108	java/lang/Exception
    //   0	10	116	finally
    //   125	129	139	java/lang/Exception
    //   133	137	139	java/lang/Exception
    //   10	22	147	finally
    //   22	38	157	finally
    //   65	90	165	finally
    //   10	22	169	java/lang/Exception
    //   22	38	179	java/lang/Exception
  }
  
  public static void startSettingItent(Context paramContext, int paramInt)
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
}
