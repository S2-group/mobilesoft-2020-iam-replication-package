package com.airwatch.tunnel.util;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.TelephonyManager;
import com.airwatch.tunnel.client.Application;
import com.airwatch.tunnel.client.StateStore;
import com.airwatch.tunnel.client.TunnelService;
import com.airwatch.tunnel.client.fsm.TunnelEventsContract;
import com.airwatch.tunnel.client.fsm.TunnelEventsContract.EventType;
import com.airwatch.tunnel.client.fsm.i;
import com.airwatch.tunnel.model.NativeServerConfig;
import com.airwatch.tunnel.model.TrafficRuleInfo;
import com.airwatch.tunnel.model.UidInfo;
import com.airwatch.tunnel.sdk.BaseSdkApplication;
import com.airwatch.util.y;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class GeneralUtil
{
  static final NativeServerConfig a = new NativeServerConfig();
  private static int b = Build.VERSION.SDK_INT;
  private static int c = 5;
  private static String d;
  
  private GeneralUtil() {}
  
  @TargetApi(26)
  public static NotificationChannel a(Context paramContext)
  {
    Object localObject;
    if (d())
    {
      NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
      localObject = localNotificationManager.getNotificationChannel("VMwareTunnelNotifications");
      if (localObject == null)
      {
        localObject = paramContext.getString(2131231176);
        paramContext = paramContext.getString(2131231173);
        localObject = new NotificationChannel("VMwareTunnelNotifications", (CharSequence)localObject, 3);
        ((NotificationChannel)localObject).setDescription(paramContext);
        localNotificationManager.createNotificationChannel((NotificationChannel)localObject);
        return localObject;
      }
    }
    else
    {
      return null;
    }
    return localObject;
  }
  
  public static String a()
  {
    Object localObject2 = Application.o().getPackageManager();
    Object localObject1 = Application.o().getPackageName();
    try
    {
      localObject2 = ((PackageManager)localObject2).getPackageInfo((String)localObject1, 0).applicationInfo.dataDir;
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        b.b("GeneralUtil: Got an exception when searching for data directory.  Exc: " + localNameNotFoundException, localNameNotFoundException);
      }
    }
    b.b("GeneralUtil: Application data directory is: " + (String)localObject1);
    return localObject1;
  }
  
  public static String a(Context paramContext, String paramString)
  {
    return Build.MODEL + ' ' + paramString;
  }
  
  /* Error */
  public static void a(java.io.File paramFile1, java.io.File paramFile2)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_1
    //   3: invokevirtual 151	java/io/File:exists	()Z
    //   6: ifne +8 -> 14
    //   9: aload_1
    //   10: invokevirtual 154	java/io/File:createNewFile	()Z
    //   13: pop
    //   14: new 156	java/io/FileInputStream
    //   17: dup
    //   18: aload_0
    //   19: invokespecial 159	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   22: astore_0
    //   23: new 161	java/io/FileOutputStream
    //   26: dup
    //   27: aload_1
    //   28: invokespecial 162	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   31: astore_1
    //   32: sipush 1024
    //   35: newarray byte
    //   37: astore_3
    //   38: aload_0
    //   39: aload_3
    //   40: invokevirtual 168	java/io/InputStream:read	([B)I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +38 -> 83
    //   48: aload_1
    //   49: aload_3
    //   50: iconst_0
    //   51: iload_2
    //   52: invokevirtual 174	java/io/OutputStream:write	([BII)V
    //   55: goto -17 -> 38
    //   58: astore 4
    //   60: aload_0
    //   61: astore_3
    //   62: aload 4
    //   64: astore_0
    //   65: aload_3
    //   66: ifnull +7 -> 73
    //   69: aload_3
    //   70: invokevirtual 177	java/io/InputStream:close	()V
    //   73: aload_1
    //   74: ifnull +7 -> 81
    //   77: aload_1
    //   78: invokevirtual 178	java/io/OutputStream:close	()V
    //   81: aload_0
    //   82: athrow
    //   83: aload_0
    //   84: ifnull +7 -> 91
    //   87: aload_0
    //   88: invokevirtual 177	java/io/InputStream:close	()V
    //   91: aload_1
    //   92: ifnull +7 -> 99
    //   95: aload_1
    //   96: invokevirtual 178	java/io/OutputStream:close	()V
    //   99: return
    //   100: astore_0
    //   101: aconst_null
    //   102: astore_1
    //   103: goto -38 -> 65
    //   106: astore 4
    //   108: aconst_null
    //   109: astore_1
    //   110: aload_0
    //   111: astore_3
    //   112: aload 4
    //   114: astore_0
    //   115: goto -50 -> 65
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	118	0	paramFile1	java.io.File
    //   0	118	1	paramFile2	java.io.File
    //   43	9	2	i	int
    //   1	111	3	localObject1	Object
    //   58	5	4	localObject2	Object
    //   106	7	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   32	38	58	finally
    //   38	44	58	finally
    //   48	55	58	finally
    //   14	23	100	finally
    //   23	32	106	finally
  }
  
  public static void a(boolean paramBoolean)
  {
    if (!paramBoolean) {
      throw new IllegalArgumentException("Got an illegal argument.");
    }
  }
  
  public static boolean a(NativeServerConfig paramNativeServerConfig)
  {
    List localList = paramNativeServerConfig.getWhitelistedPackagesList();
    paramNativeServerConfig = paramNativeServerConfig.getTrafficRules();
    TrafficRuleInfo localTrafficRuleInfo = new TrafficRuleInfo();
    localTrafficRuleInfo.setAction(5);
    localTrafficRuleInfo.setSequenceNumber(paramNativeServerConfig.size() + 1);
    localTrafficRuleInfo.addDomain("*");
    Object localObject1 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    localObject1 = Application.o().getPackageManager().queryIntentActivities((Intent)localObject1, 0).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = ((ResolveInfo)((Iterator)localObject1).next()).activityInfo.packageName;
      if ((a((String)localObject2)) && ((!localList.contains(localObject2)) || (d((String)localObject2))))
      {
        localObject2 = c((String)localObject2);
        if (localObject2 != null) {
          localTrafficRuleInfo.addPkgInfo((UidInfo)localObject2);
        }
      }
    }
    if (!localTrafficRuleInfo.getPackagesInfo().isEmpty()) {
      return paramNativeServerConfig.add(localTrafficRuleInfo);
    }
    return false;
  }
  
  public static boolean a(Object paramObject)
  {
    return paramObject != null;
  }
  
  public static boolean a(String paramString)
  {
    return (paramString != null) && (paramString.trim().length() > 0);
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null)) {
      return true;
    }
    if (paramString1 == null) {
      return false;
    }
    return paramString1.equals(paramString2);
  }
  
  public static boolean a(List<String> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      if (b((String)paramList.next())) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean a(List<NativeServerConfig> paramList1, List<NativeServerConfig> paramList2)
  {
    if ((paramList1 == null) && (paramList2 == null)) {
      return true;
    }
    if ((paramList1 == null) || (paramList2 == null)) {
      return false;
    }
    if (paramList1.size() != paramList2.size()) {
      return false;
    }
    Collections.sort(paramList1, a);
    Collections.sort(paramList2, a);
    paramList1 = paramList1.listIterator();
    paramList2 = paramList2.listIterator();
    while ((paramList1.hasNext()) && (paramList2.hasNext()))
    {
      NativeServerConfig localNativeServerConfig = (NativeServerConfig)paramList1.next();
      if (localNativeServerConfig.compare(localNativeServerConfig, (NativeServerConfig)paramList2.next()) != 0) {
        return false;
      }
    }
    return true;
  }
  
  public static int b(List<? extends Comparable> paramList1, List<? extends Comparable> paramList2)
  {
    boolean bool2 = true;
    int j = 0;
    if (paramList1 != null)
    {
      bool1 = true;
      a(bool1);
      if (paramList2 == null) {
        break label56;
      }
    }
    label56:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      a(bool1);
      if ((paramList1.size() != 0) || (paramList2.size() != 0)) {
        break label62;
      }
      return 0;
      bool1 = false;
      break;
    }
    label62:
    if ((paramList1.size() > 0) && (paramList2.size() > 0)) {
      a(((Comparable)paramList1.get(0)).getClass().equals(((Comparable)paramList2.get(0)).getClass()));
    }
    int i = paramList1.size() - paramList2.size();
    if (i != 0) {
      return i;
    }
    int k = paramList1.size();
    Collections.sort(paramList1);
    Collections.sort(paramList2);
    for (;;)
    {
      if (j < k)
      {
        i = ((Comparable)paramList1.get(j)).compareTo(paramList2.get(j));
        if (i == 0) {}
      }
      else
      {
        return i;
      }
      j += 1;
    }
  }
  
  public static boolean b()
  {
    return b <= 19;
  }
  
  public static boolean b(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString != null)
    {
      bool1 = bool2;
      if (paramString.trim().length() > 0)
      {
        Intent localIntent = new Intent();
        localIntent.setPackage(paramString.trim());
        paramString = Application.o().getPackageManager().queryIntentActivities(localIntent, 0);
        bool1 = bool2;
        if (paramString != null)
        {
          bool1 = bool2;
          if (!paramString.isEmpty()) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public static UidInfo c(String paramString)
  {
    Iterator localIterator = Application.o().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.packageName.equals(paramString)) {
        return new UidInfo(localApplicationInfo.uid, localApplicationInfo.packageName, null);
      }
    }
    return null;
  }
  
  public static boolean c()
  {
    return b >= 23;
  }
  
  public static boolean d()
  {
    return b >= 26;
  }
  
  public static boolean d(String paramString)
  {
    return ("com.airwatch.tunnel".equalsIgnoreCase(paramString)) || ("com.airwatch.androidagent".equalsIgnoreCase(paramString)) || ("com.airwatch.workspace".equalsIgnoreCase(paramString));
  }
  
  public static String e()
  {
    return "4.1.0.22".substring(0, c);
  }
  
  public static void e(String paramString)
  {
    LocalBroadcastManager localLocalBroadcastManager = LocalBroadcastManager.getInstance(Application.o());
    Intent localIntent = new Intent(TunnelService.a);
    localIntent.putExtra("notificationText", paramString);
    localLocalBroadcastManager.sendBroadcast(localIntent);
  }
  
  public static String f()
  {
    int i = y.a("4.1.0.22", ".", 3);
    if (i > 0) {
      return "4.1.0.22".substring(i + 1);
    }
    return String.valueOf(36);
  }
  
  public static String g()
  {
    Date localDate = new Date(1537526353233L);
    return android.text.format.DateFormat.getMediumDateFormat(Application.o()).format(localDate);
  }
  
  public static String h()
  {
    if (d == null)
    {
      d = i();
      if (d == null) {
        d = j();
      }
    }
    return d;
  }
  
  private static String i()
  {
    try
    {
      Object localObject = (TelephonyManager)Application.n().getSystemService("phone");
      String str = ((TelephonyManager)localObject).getSimCountryIso();
      if ((str != null) && (str.length() == 2)) {
        return str.toUpperCase(Locale.US);
      }
      if (((TelephonyManager)localObject).getPhoneType() != 2)
      {
        localObject = ((TelephonyManager)localObject).getNetworkCountryIso();
        if ((localObject != null) && (((String)localObject).length() == 2))
        {
          localObject = ((String)localObject).toUpperCase(Locale.US);
          return localObject;
        }
      }
    }
    catch (Exception localException)
    {
      b.b("Location iso code unable to be determined from telephony manager");
    }
    return null;
  }
  
  private static String j()
  {
    return Locale.getDefault().getCountry().toUpperCase(Locale.US);
  }
  
  public static void noTamperingDetected()
  {
    b.b("No Tampering Detected in the JAVA layer");
  }
  
  public static void tamperingDetected()
  {
    b.d("Detected Tampering in JAVA layer, Posting a TAMPERING DETECTED Event to the FSM Layer");
    i localI = StateStore.getInstance().getEventSender();
    if (localI != null)
    {
      localI.a(TunnelEventsContract.a(TunnelEventsContract.EventType.W));
      return;
    }
    b.c("tamperingDetected: Event Sender is null");
  }
  
  public static enum LineEndingType
  {
    private LineEndingType() {}
  }
}
