package com.garmin.android.gncs.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.garmin.android.framework.util.designpattern.Factory;
import com.garmin.android.framework.util.inject.Configuration;
import com.garmin.android.framework.util.inject.Injector;
import com.garmin.android.g.a;
import com.garmin.android.gncs.GNCSApplicationInfo;
import com.garmin.android.gncs.GNCSNotificationInfo;
import com.garmin.android.gncs.GNCSNotificationInfo.NotificationType;
import com.garmin.android.gncs.GNCSPhoneCallListener;
import com.garmin.android.gncs.GNCSUtil;
import com.garmin.android.gncs.handlers.GNCSApplicationHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class GNCSSettingsOptOut
  extends GNCSSettings
{
  public static final String PREF_NOTIFICATIONS = "blocked_notifications";
  protected static final List<String> installedPackages = new CopyOnWriteArrayList();
  protected Map<String, GNCSApplicationInfo> blockedPackageInfoMap = new ConcurrentHashMap();
  protected List<String> dialerPackageList = new ArrayList();
  private Handler handler;
  private HandlerThread handlerThread = new HandlerThread("Reload Packages - Smart Notifications");
  protected List<String> smsPackageList = new ArrayList();
  
  protected GNCSSettingsOptOut()
  {
    this.handlerThread.start();
    this.handler = new Handler(this.handlerThread.getLooper());
  }
  
  private List<GNCSApplicationInfo> getDefaultPackagesForNotificationType(Context paramContext, GNCSNotificationInfo.NotificationType paramNotificationType)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = defaultPackageMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (defaultPackageMap.get(str) == paramNotificationType) {
        localArrayList.add(new GNCSApplicationInfo(str, ((GNCSUtil)Injector.singletonOf(GNCSUtil.class)).getPackageDisplayName(paramContext, str), paramNotificationType, true));
      }
    }
    return localArrayList;
  }
  
  private boolean isSmsPackage(Context paramContext, List<PackageInfo> paramList, String paramString, PackageManager paramPackageManager)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      paramContext = (PackageInfo)paramList.next();
      if (paramContext.packageName.equals(paramString)) {
        break label42;
      }
    }
    paramContext = null;
    label42:
    if (paramContext == null) {
      return false;
    }
    if (paramContext.requestedPermissions == null) {
      return false;
    }
    paramContext = paramContext.requestedPermissions;
    int i3 = paramContext.length;
    int i1 = 0;
    int k = 0;
    int i = 0;
    int j = 0;
    int m;
    int i2;
    int n;
    for (;;)
    {
      m = k;
      i2 = i;
      n = j;
      if (i1 >= i3) {
        break;
      }
      paramList = paramContext[i1];
      if (paramList.equals("android.permission.RECEIVE_MMS"))
      {
        if ((k != 0) && (i != 0))
        {
          n = 1;
          m = k;
          i2 = i;
          break;
        }
        j = 1;
      }
      if (paramList.equals("android.permission.RECEIVE_SMS"))
      {
        if ((j != 0) && (i != 0))
        {
          m = 1;
          i2 = i;
          n = j;
          break;
        }
        k = 1;
      }
      if (paramList.equals("android.permission.READ_SMS"))
      {
        if ((j != 0) && (k != 0))
        {
          i2 = 1;
          m = k;
          n = j;
          break;
        }
        i = 1;
      }
      i1 += 1;
    }
    return (n != 0) && (m != 0) && (i2 != 0);
  }
  
  public void enablePackage(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      a.a("Permanently enabling package ".concat(String.valueOf(paramString)));
      this.blockedPackageInfoMap.remove(paramString);
      if (!installedPackages.contains(paramString)) {
        installedPackages.add(paramString);
      }
    }
    else
    {
      a.a("Permanently disabling package ".concat(String.valueOf(paramString)));
      this.blockedPackageInfoMap.put(paramString, new GNCSApplicationInfo(paramString, ((GNCSUtil)Injector.singletonOf(GNCSUtil.class)).getPackageDisplayName(paramContext, paramString), getNotificationTypeForPackage(paramContext, paramString, ""), false));
      installedPackages.remove(paramString);
    }
  }
  
  public long getLastUserNotified(Context paramContext)
  {
    return paramContext.getSharedPreferences("gncs", 4).getLong("lastNotified", 0L);
  }
  
  public List<String> getLoadedPackages()
  {
    return new ArrayList();
  }
  
  public GNCSNotificationInfo.NotificationType getNotificationTypeForPackage(@NonNull Context paramContext, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!paramString2.isEmpty()))
    {
      int i = -1;
      switch (paramString2.hashCode())
      {
      default: 
        break;
      case 1984153269: 
        if (paramString2.equals("service")) {
          i = 7;
        }
        break;
      case 1052964649: 
        if (paramString2.equals("transport")) {
          i = 10;
        }
        break;
      case 106940687: 
        if (paramString2.equals("promo")) {
          i = 4;
        }
        break;
      case 96891546: 
        if (paramString2.equals("event")) {
          i = 12;
        }
        break;
      case 96619420: 
        if (paramString2.equals("email")) {
          i = 11;
        }
        break;
      case 92895825: 
        if (paramString2.equals("alarm")) {
          i = 0;
        }
        break;
      case 3045982: 
        if (paramString2.equals("call")) {
          i = 1;
        }
        break;
      case 114381: 
        if (paramString2.equals("sys")) {
          i = 9;
        }
        break;
      case 108417: 
        if (paramString2.equals("msg")) {
          i = 13;
        }
        break;
      case 100709: 
        if (paramString2.equals("err")) {
          i = 2;
        }
        break;
      case -518602638: 
        if (paramString2.equals("reminder")) {
          i = 6;
        }
        break;
      case -892481550: 
        if (paramString2.equals("status")) {
          i = 8;
        }
        break;
      case -897050771: 
        if (paramString2.equals("social")) {
          i = 14;
        }
        break;
      case -1001078227: 
        if (paramString2.equals("progress")) {
          i = 3;
        }
        break;
      case -1028636743: 
        if (paramString2.equals("recommendation")) {
          i = 5;
        }
        break;
      }
      switch (i)
      {
      default: 
        break;
      case 14: 
        return GNCSNotificationInfo.NotificationType.SOCIAL;
      case 13: 
        return GNCSNotificationInfo.NotificationType.SMS;
      case 12: 
        return GNCSNotificationInfo.NotificationType.SCHEDULE;
      case 11: 
        return GNCSNotificationInfo.NotificationType.EMAIL;
      }
    }
    if (defaultPackageMap.containsKey(paramString1)) {
      return (GNCSNotificationInfo.NotificationType)defaultPackageMap.get(paramString1);
    }
    if (GNCSUtil.isDialerPackage(paramContext, paramString1)) {}
    try
    {
      switch (((GNCSPhoneCallListener)Injector.singletonOf(GNCSPhoneCallListener.class)).getCallState())
      {
      case 4: 
        return GNCSNotificationInfo.NotificationType.MISSED_CALL;
      }
    }
    catch (Exception paramString2)
    {
      for (;;) {}
    }
    return GNCSNotificationInfo.NotificationType.INCOMING_CALL;
    paramString2 = GNCSNotificationInfo.NotificationType.OTHER;
    return paramString2;
    if (GNCSUtil.isDefaultSmsPackage(paramContext, paramString1)) {
      return GNCSNotificationInfo.NotificationType.SMS;
    }
    if (this.smsPackageList.contains(paramString1)) {
      return GNCSNotificationInfo.NotificationType.SMS;
    }
    return GNCSNotificationInfo.NotificationType.OTHER;
  }
  
  public List<GNCSApplicationInfo> getPackagesForNotificationType(GNCSNotificationInfo.NotificationType paramNotificationType)
  {
    return new ArrayList();
  }
  
  public boolean hasPackagesForNotificationType(GNCSNotificationInfo.NotificationType paramNotificationType)
  {
    return false;
  }
  
  public boolean isActive(String paramString)
  {
    return true;
  }
  
  public boolean isEnabled(String paramString)
  {
    return !this.blockedPackageInfoMap.containsKey(paramString);
  }
  
  public void performUpgrade(final Context paramContext)
  {
    this.handler.post(new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   4: ldc 30
        //   6: iconst_4
        //   7: invokevirtual 36	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //   10: astore 8
        //   12: aload 8
        //   14: ldc 38
        //   16: iconst_0
        //   17: invokeinterface 44 3 0
        //   22: ifne +1486 -> 1508
        //   25: invokestatic 50	com/garmin/android/gncs/settings/GNCSConfig:getInstance	()Lcom/garmin/android/gncs/settings/GNCSConfig;
        //   28: invokevirtual 54	com/garmin/android/gncs/settings/GNCSConfig:performOptOutUpgrade	()Z
        //   31: ifeq +1477 -> 1508
        //   34: aload_0
        //   35: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   38: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   41: invokeinterface 64 1 0
        //   46: ifne +1462 -> 1508
        //   49: aconst_null
        //   50: astore 6
        //   52: aload 8
        //   54: ldc 66
        //   56: aconst_null
        //   57: invokeinterface 70 3 0
        //   62: astore 10
        //   64: aload_0
        //   65: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   68: invokevirtual 74	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
        //   71: astore 9
        //   73: aload 9
        //   75: iconst_0
        //   76: invokevirtual 80	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
        //   79: astore 7
        //   81: aload 7
        //   83: astore 6
        //   85: aload 10
        //   87: ifnull +1117 -> 1204
        //   90: ldc 82
        //   92: ldc 84
        //   94: aload 10
        //   96: invokestatic 90	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
        //   99: invokevirtual 94	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
        //   102: invokestatic 100	com/garmin/android/g/a:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   105: aload_0
        //   106: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   109: aload_0
        //   110: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   113: aload 10
        //   115: invokevirtual 104	com/garmin/android/gncs/settings/GNCSSettingsOptOut:fromJSON	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/Map;
        //   118: astore 7
        //   120: aload 7
        //   122: ldc 106
        //   124: invokeinterface 110 2 0
        //   129: ifeq +64 -> 193
        //   132: aload_0
        //   133: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   136: aload_0
        //   137: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   140: getstatic 116	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:INCOMING_CALL	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   143: invokestatic 120	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$000	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;)Ljava/util/List;
        //   146: invokeinterface 126 1 0
        //   151: astore 10
        //   153: aload 10
        //   155: invokeinterface 131 1 0
        //   160: ifeq +33 -> 193
        //   163: aload 10
        //   165: invokeinterface 135 1 0
        //   170: checkcast 137	com/garmin/android/gncs/GNCSApplicationInfo
        //   173: astore 11
        //   175: aload 7
        //   177: aload 11
        //   179: getfield 141	com/garmin/android/gncs/GNCSApplicationInfo:packageName	Ljava/lang/String;
        //   182: aload 11
        //   184: invokeinterface 145 3 0
        //   189: pop
        //   190: goto -37 -> 153
        //   193: aload 7
        //   195: ldc -109
        //   197: invokeinterface 110 2 0
        //   202: ifeq +64 -> 266
        //   205: aload_0
        //   206: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   209: aload_0
        //   210: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   213: getstatic 150	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:MISSED_CALL	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   216: invokestatic 120	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$000	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;)Ljava/util/List;
        //   219: invokeinterface 126 1 0
        //   224: astore 10
        //   226: aload 10
        //   228: invokeinterface 131 1 0
        //   233: ifeq +33 -> 266
        //   236: aload 10
        //   238: invokeinterface 135 1 0
        //   243: checkcast 137	com/garmin/android/gncs/GNCSApplicationInfo
        //   246: astore 11
        //   248: aload 7
        //   250: aload 11
        //   252: getfield 141	com/garmin/android/gncs/GNCSApplicationInfo:packageName	Ljava/lang/String;
        //   255: aload 11
        //   257: invokeinterface 145 3 0
        //   262: pop
        //   263: goto -37 -> 226
        //   266: aload 7
        //   268: ldc -104
        //   270: invokeinterface 110 2 0
        //   275: ifeq +64 -> 339
        //   278: aload_0
        //   279: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   282: aload_0
        //   283: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   286: getstatic 155	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:VOICEMAIL	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   289: invokestatic 120	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$000	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;)Ljava/util/List;
        //   292: invokeinterface 126 1 0
        //   297: astore 10
        //   299: aload 10
        //   301: invokeinterface 131 1 0
        //   306: ifeq +33 -> 339
        //   309: aload 10
        //   311: invokeinterface 135 1 0
        //   316: checkcast 137	com/garmin/android/gncs/GNCSApplicationInfo
        //   319: astore 11
        //   321: aload 7
        //   323: aload 11
        //   325: getfield 141	com/garmin/android/gncs/GNCSApplicationInfo:packageName	Ljava/lang/String;
        //   328: aload 11
        //   330: invokeinterface 145 3 0
        //   335: pop
        //   336: goto -37 -> 299
        //   339: aload 7
        //   341: ldc -99
        //   343: invokeinterface 110 2 0
        //   348: ifeq +64 -> 412
        //   351: aload_0
        //   352: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   355: aload_0
        //   356: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   359: getstatic 160	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:SMS	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   362: invokestatic 120	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$000	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;)Ljava/util/List;
        //   365: invokeinterface 126 1 0
        //   370: astore 10
        //   372: aload 10
        //   374: invokeinterface 131 1 0
        //   379: ifeq +33 -> 412
        //   382: aload 10
        //   384: invokeinterface 135 1 0
        //   389: checkcast 137	com/garmin/android/gncs/GNCSApplicationInfo
        //   392: astore 11
        //   394: aload 7
        //   396: aload 11
        //   398: getfield 141	com/garmin/android/gncs/GNCSApplicationInfo:packageName	Ljava/lang/String;
        //   401: aload 11
        //   403: invokeinterface 145 3 0
        //   408: pop
        //   409: goto -37 -> 372
        //   412: aload 7
        //   414: ldc -94
        //   416: invokeinterface 110 2 0
        //   421: ifeq +64 -> 485
        //   424: aload_0
        //   425: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   428: aload_0
        //   429: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   432: getstatic 165	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:SCHEDULE	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   435: invokestatic 120	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$000	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;)Ljava/util/List;
        //   438: invokeinterface 126 1 0
        //   443: astore 10
        //   445: aload 10
        //   447: invokeinterface 131 1 0
        //   452: ifeq +33 -> 485
        //   455: aload 10
        //   457: invokeinterface 135 1 0
        //   462: checkcast 137	com/garmin/android/gncs/GNCSApplicationInfo
        //   465: astore 11
        //   467: aload 7
        //   469: aload 11
        //   471: getfield 141	com/garmin/android/gncs/GNCSApplicationInfo:packageName	Ljava/lang/String;
        //   474: aload 11
        //   476: invokeinterface 145 3 0
        //   481: pop
        //   482: goto -37 -> 445
        //   485: getstatic 170	com/garmin/android/gncs/settings/GNCSSettings:defaultPackageMap	Ljava/util/Map;
        //   488: invokeinterface 174 1 0
        //   493: invokeinterface 177 1 0
        //   498: astore 10
        //   500: aload 10
        //   502: invokeinterface 131 1 0
        //   507: ifeq +137 -> 644
        //   510: aload 10
        //   512: invokeinterface 135 1 0
        //   517: checkcast 86	java/lang/String
        //   520: astore 11
        //   522: aload 7
        //   524: aload 11
        //   526: invokeinterface 110 2 0
        //   531: ifne +73 -> 604
        //   534: new 179	java/lang/StringBuilder
        //   537: dup
        //   538: ldc -75
        //   540: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   543: astore 12
        //   545: aload 12
        //   547: aload 11
        //   549: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   552: pop
        //   553: aload 12
        //   555: ldc -66
        //   557: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   560: pop
        //   561: ldc 82
        //   563: aload 12
        //   565: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   568: invokestatic 100	com/garmin/android/g/a:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   571: aload_0
        //   572: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   575: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   578: aload 11
        //   580: aload_0
        //   581: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   584: aload_0
        //   585: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   588: aload 11
        //   590: aload 9
        //   592: invokevirtual 198	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   595: invokeinterface 145 3 0
        //   600: pop
        //   601: goto -101 -> 500
        //   604: new 179	java/lang/StringBuilder
        //   607: dup
        //   608: ldc -56
        //   610: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   613: astore 12
        //   615: aload 12
        //   617: aload 11
        //   619: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   622: pop
        //   623: aload 12
        //   625: ldc -54
        //   627: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   630: pop
        //   631: ldc 82
        //   633: aload 12
        //   635: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   638: invokestatic 100	com/garmin/android/g/a:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   641: goto -141 -> 500
        //   644: aload 6
        //   646: ifnull +529 -> 1175
        //   649: aload 9
        //   651: sipush 4096
        //   654: invokevirtual 205	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
        //   657: astore 10
        //   659: aload 6
        //   661: invokeinterface 126 1 0
        //   666: astore 6
        //   668: aload 6
        //   670: invokeinterface 131 1 0
        //   675: ifeq +500 -> 1175
        //   678: aload 6
        //   680: invokeinterface 135 1 0
        //   685: checkcast 207	android/content/pm/ApplicationInfo
        //   688: getfield 208	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
        //   691: astore 11
        //   693: getstatic 170	com/garmin/android/gncs/settings/GNCSSettings:defaultPackageMap	Ljava/util/Map;
        //   696: aload 11
        //   698: invokeinterface 110 2 0
        //   703: ifeq +43 -> 746
        //   706: new 179	java/lang/StringBuilder
        //   709: dup
        //   710: ldc -46
        //   712: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   715: astore 12
        //   717: aload 12
        //   719: aload 11
        //   721: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   724: pop
        //   725: aload 12
        //   727: ldc -44
        //   729: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   732: pop
        //   733: ldc 82
        //   735: aload 12
        //   737: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   740: invokestatic 100	com/garmin/android/g/a:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   743: goto -75 -> 668
        //   746: aload_0
        //   747: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   750: aload 11
        //   752: invokestatic 218	com/garmin/android/gncs/GNCSUtil:isDialerPackage	(Landroid/content/Context;Ljava/lang/String;)Z
        //   755: istore_3
        //   756: aload 10
        //   758: ifnonnull +8 -> 766
        //   761: iconst_0
        //   762: istore_2
        //   763: goto +21 -> 784
        //   766: aload_0
        //   767: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   770: aload_0
        //   771: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   774: aload 10
        //   776: aload 11
        //   778: aload 9
        //   780: invokestatic 222	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$100	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Landroid/content/pm/PackageManager;)Z
        //   783: istore_2
        //   784: getstatic 170	com/garmin/android/gncs/settings/GNCSSettings:defaultPackageMap	Ljava/util/Map;
        //   787: aload 11
        //   789: invokeinterface 226 2 0
        //   794: checkcast 112	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType
        //   797: astore 12
        //   799: aload 12
        //   801: ifnull +716 -> 1517
        //   804: aload 12
        //   806: getstatic 165	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:SCHEDULE	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   809: if_acmpne +708 -> 1517
        //   812: iconst_1
        //   813: istore_1
        //   814: goto +3 -> 817
        //   817: iload_3
        //   818: ifeq +85 -> 903
        //   821: aload 7
        //   823: ldc 106
        //   825: invokeinterface 110 2 0
        //   830: ifne +73 -> 903
        //   833: new 179	java/lang/StringBuilder
        //   836: dup
        //   837: ldc -75
        //   839: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   842: astore 12
        //   844: aload 12
        //   846: aload 11
        //   848: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   851: pop
        //   852: aload 12
        //   854: ldc -28
        //   856: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   859: pop
        //   860: ldc 82
        //   862: aload 12
        //   864: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   867: invokestatic 100	com/garmin/android/g/a:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   870: aload_0
        //   871: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   874: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   877: aload 11
        //   879: aload_0
        //   880: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   883: aload_0
        //   884: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   887: aload 11
        //   889: aload 9
        //   891: invokevirtual 198	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   894: invokeinterface 145 3 0
        //   899: pop
        //   900: goto -232 -> 668
        //   903: iload_2
        //   904: ifeq +85 -> 989
        //   907: aload 7
        //   909: ldc -99
        //   911: invokeinterface 110 2 0
        //   916: ifne +73 -> 989
        //   919: new 179	java/lang/StringBuilder
        //   922: dup
        //   923: ldc -75
        //   925: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   928: astore 12
        //   930: aload 12
        //   932: aload 11
        //   934: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   937: pop
        //   938: aload 12
        //   940: ldc -26
        //   942: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   945: pop
        //   946: ldc 82
        //   948: aload 12
        //   950: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   953: invokestatic 100	com/garmin/android/g/a:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   956: aload_0
        //   957: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   960: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   963: aload 11
        //   965: aload_0
        //   966: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   969: aload_0
        //   970: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   973: aload 11
        //   975: aload 9
        //   977: invokevirtual 198	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   980: invokeinterface 145 3 0
        //   985: pop
        //   986: goto -318 -> 668
        //   989: iload_1
        //   990: ifeq +85 -> 1075
        //   993: aload 7
        //   995: ldc -94
        //   997: invokeinterface 110 2 0
        //   1002: ifne +73 -> 1075
        //   1005: new 179	java/lang/StringBuilder
        //   1008: dup
        //   1009: ldc -75
        //   1011: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   1014: astore 12
        //   1016: aload 12
        //   1018: aload 11
        //   1020: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1023: pop
        //   1024: aload 12
        //   1026: ldc -24
        //   1028: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1031: pop
        //   1032: ldc 82
        //   1034: aload 12
        //   1036: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1039: invokestatic 100	com/garmin/android/g/a:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   1042: aload_0
        //   1043: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1046: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   1049: aload 11
        //   1051: aload_0
        //   1052: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1055: aload_0
        //   1056: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1059: aload 11
        //   1061: aload 9
        //   1063: invokevirtual 198	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   1066: invokeinterface 145 3 0
        //   1071: pop
        //   1072: goto -404 -> 668
        //   1075: aload 7
        //   1077: aload 11
        //   1079: invokeinterface 110 2 0
        //   1084: ifne +73 -> 1157
        //   1087: new 179	java/lang/StringBuilder
        //   1090: dup
        //   1091: ldc -75
        //   1093: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   1096: astore 12
        //   1098: aload 12
        //   1100: aload 11
        //   1102: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1105: pop
        //   1106: aload 12
        //   1108: ldc -22
        //   1110: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1113: pop
        //   1114: ldc 82
        //   1116: aload 12
        //   1118: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1121: invokestatic 100	com/garmin/android/g/a:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   1124: aload_0
        //   1125: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1128: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   1131: aload 11
        //   1133: aload_0
        //   1134: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1137: aload_0
        //   1138: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1141: aload 11
        //   1143: aload 9
        //   1145: invokevirtual 198	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   1148: invokeinterface 145 3 0
        //   1153: pop
        //   1154: goto -486 -> 668
        //   1157: ldc 82
        //   1159: ldc -56
        //   1161: aload 11
        //   1163: invokestatic 90	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
        //   1166: invokevirtual 94	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
        //   1169: invokestatic 100	com/garmin/android/g/a:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   1172: goto -504 -> 668
        //   1175: aload 8
        //   1177: invokeinterface 238 1 0
        //   1182: astore 6
        //   1184: aload 6
        //   1186: ldc 66
        //   1188: invokeinterface 244 2 0
        //   1193: pop
        //   1194: aload 6
        //   1196: invokeinterface 247 1 0
        //   1201: goto +280 -> 1481
        //   1204: ldc 82
        //   1206: ldc -7
        //   1208: invokestatic 100	com/garmin/android/g/a:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   1211: aload 9
        //   1213: sipush 4096
        //   1216: invokevirtual 205	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
        //   1219: astore 7
        //   1221: aload 6
        //   1223: invokeinterface 126 1 0
        //   1228: astore 6
        //   1230: aload 6
        //   1232: invokeinterface 131 1 0
        //   1237: ifeq +233 -> 1470
        //   1240: aload 6
        //   1242: invokeinterface 135 1 0
        //   1247: checkcast 207	android/content/pm/ApplicationInfo
        //   1250: getfield 208	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
        //   1253: astore 10
        //   1255: getstatic 170	com/garmin/android/gncs/settings/GNCSSettings:defaultPackageMap	Ljava/util/Map;
        //   1258: aload 10
        //   1260: invokeinterface 110 2 0
        //   1265: ifne -35 -> 1230
        //   1268: aload_0
        //   1269: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1272: aload 10
        //   1274: invokestatic 218	com/garmin/android/gncs/GNCSUtil:isDialerPackage	(Landroid/content/Context;Ljava/lang/String;)Z
        //   1277: istore 5
        //   1279: aload 7
        //   1281: ifnonnull +8 -> 1289
        //   1284: iconst_0
        //   1285: istore_2
        //   1286: goto +21 -> 1307
        //   1289: aload_0
        //   1290: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1293: aload_0
        //   1294: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1297: aload 7
        //   1299: aload 10
        //   1301: aload 9
        //   1303: invokestatic 222	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$100	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Landroid/content/pm/PackageManager;)Z
        //   1306: istore_2
        //   1307: getstatic 170	com/garmin/android/gncs/settings/GNCSSettings:defaultPackageMap	Ljava/util/Map;
        //   1310: aload 10
        //   1312: invokeinterface 226 2 0
        //   1317: checkcast 112	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType
        //   1320: astore 11
        //   1322: iload 5
        //   1324: istore_3
        //   1325: iload_2
        //   1326: istore 4
        //   1328: aload 11
        //   1330: ifnull +192 -> 1522
        //   1333: aload 11
        //   1335: getstatic 165	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:SCHEDULE	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   1338: if_acmpne +11 -> 1349
        //   1341: iconst_1
        //   1342: istore_1
        //   1343: iload 5
        //   1345: istore_3
        //   1346: goto +42 -> 1388
        //   1349: aload 11
        //   1351: getstatic 160	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:SMS	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   1354: if_acmpne +12 -> 1366
        //   1357: iconst_1
        //   1358: istore 4
        //   1360: iload 5
        //   1362: istore_3
        //   1363: goto +159 -> 1522
        //   1366: iload 5
        //   1368: istore_3
        //   1369: iload_2
        //   1370: istore 4
        //   1372: aload 11
        //   1374: getstatic 116	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:INCOMING_CALL	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   1377: if_acmpne +145 -> 1522
        //   1380: iconst_1
        //   1381: istore_3
        //   1382: iload_2
        //   1383: istore 4
        //   1385: goto +137 -> 1522
        //   1388: iload_3
        //   1389: ifne -159 -> 1230
        //   1392: iload_2
        //   1393: ifne -163 -> 1230
        //   1396: iload_1
        //   1397: ifne -167 -> 1230
        //   1400: new 179	java/lang/StringBuilder
        //   1403: dup
        //   1404: ldc -5
        //   1406: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   1409: astore 11
        //   1411: aload 11
        //   1413: aload 10
        //   1415: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1418: pop
        //   1419: aload 11
        //   1421: ldc -3
        //   1423: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1426: pop
        //   1427: ldc 82
        //   1429: aload 11
        //   1431: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1434: invokestatic 100	com/garmin/android/g/a:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   1437: aload_0
        //   1438: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1441: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   1444: aload 10
        //   1446: aload_0
        //   1447: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1450: aload_0
        //   1451: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1454: aload 10
        //   1456: aload 9
        //   1458: invokevirtual 198	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   1461: invokeinterface 145 3 0
        //   1466: pop
        //   1467: goto -237 -> 1230
        //   1470: aload_0
        //   1471: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1474: aload_0
        //   1475: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1478: invokevirtual 256	com/garmin/android/gncs/settings/GNCSSettingsOptOut:save	(Landroid/content/Context;)V
        //   1481: aload 8
        //   1483: invokeinterface 238 1 0
        //   1488: astore 6
        //   1490: aload 6
        //   1492: ldc 38
        //   1494: iconst_1
        //   1495: invokeinterface 260 3 0
        //   1500: pop
        //   1501: aload 6
        //   1503: invokeinterface 247 1 0
        //   1508: return
        //   1509: astore 6
        //   1511: return
        //   1512: astore 7
        //   1514: goto -1429 -> 85
        //   1517: iconst_0
        //   1518: istore_1
        //   1519: goto -702 -> 817
        //   1522: iconst_0
        //   1523: istore_1
        //   1524: iload 4
        //   1526: istore_2
        //   1527: goto -139 -> 1388
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1530	0	this	1
        //   813	711	1	i	int
        //   762	765	2	bool1	boolean
        //   755	634	3	bool2	boolean
        //   1326	199	4	bool3	boolean
        //   1277	90	5	bool4	boolean
        //   50	1452	6	localObject1	Object
        //   1509	1	6	localException1	Exception
        //   79	1219	7	localObject2	Object
        //   1512	1	7	localException2	Exception
        //   10	1472	8	localSharedPreferences	SharedPreferences
        //   71	1386	9	localPackageManager	PackageManager
        //   62	1393	10	localObject3	Object
        //   173	1257	11	localObject4	Object
        //   543	574	12	localObject5	Object
        // Exception table:
        //   from	to	target	type
        //   0	49	1509	java/lang/Exception
        //   52	73	1509	java/lang/Exception
        //   90	153	1509	java/lang/Exception
        //   153	190	1509	java/lang/Exception
        //   193	226	1509	java/lang/Exception
        //   226	263	1509	java/lang/Exception
        //   266	299	1509	java/lang/Exception
        //   299	336	1509	java/lang/Exception
        //   339	372	1509	java/lang/Exception
        //   372	409	1509	java/lang/Exception
        //   412	445	1509	java/lang/Exception
        //   445	482	1509	java/lang/Exception
        //   485	500	1509	java/lang/Exception
        //   500	601	1509	java/lang/Exception
        //   604	641	1509	java/lang/Exception
        //   649	668	1509	java/lang/Exception
        //   668	743	1509	java/lang/Exception
        //   746	756	1509	java/lang/Exception
        //   766	784	1509	java/lang/Exception
        //   784	799	1509	java/lang/Exception
        //   804	812	1509	java/lang/Exception
        //   821	900	1509	java/lang/Exception
        //   907	986	1509	java/lang/Exception
        //   993	1072	1509	java/lang/Exception
        //   1075	1154	1509	java/lang/Exception
        //   1157	1172	1509	java/lang/Exception
        //   1175	1201	1509	java/lang/Exception
        //   1204	1230	1509	java/lang/Exception
        //   1230	1279	1509	java/lang/Exception
        //   1289	1307	1509	java/lang/Exception
        //   1307	1322	1509	java/lang/Exception
        //   1333	1341	1509	java/lang/Exception
        //   1349	1357	1509	java/lang/Exception
        //   1372	1380	1509	java/lang/Exception
        //   1400	1467	1509	java/lang/Exception
        //   1470	1481	1509	java/lang/Exception
        //   1481	1508	1509	java/lang/Exception
        //   73	81	1512	java/lang/Exception
      }
    });
  }
  
  public void reloadPackages(final Context paramContext)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        Object localObject2 = paramContext.getPackageManager();
        Object localObject1 = new ArrayList();
        Object localObject3 = new Intent("android.intent.action.MAIN", null);
        ((Intent)localObject3).addCategory("android.intent.category.LAUNCHER");
        try
        {
          localObject2 = ((PackageManager)localObject2).queryIntentActivities((Intent)localObject3, 0).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (ResolveInfo)((Iterator)localObject2).next();
            if (!((List)localObject1).contains(((ResolveInfo)localObject3).activityInfo.packageName)) {
              ((List)localObject1).add(((ResolveInfo)localObject3).activityInfo.packageName);
            }
          }
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
        com.garmin.a.c.a("GNCSTrace").d("Failed to get list of app due to transaction too large exception");
        ((List)localObject1).add("com.garmin.gncs.phone.incoming");
        ((List)localObject1).add("com.garmin.gncs.phone.missed");
        ((List)localObject1).add("com.garmin.gncs.sms");
        ((List)localObject1).add("com.garmin.gncs.voicemail");
        ((List)localObject1).add("com.garmin.gncs.calendar");
        ((List)localObject1).add("com.android.server.telecom");
        GNCSSettingsOptOut.installedPackages.clear();
        GNCSSettingsOptOut.installedPackages.addAll((Collection)localObject1);
        localObject1 = paramContext.getSharedPreferences("gncs", 4).getString("blocked_notifications", null);
        if (localObject1 != null) {
          GNCSSettingsOptOut.this.blockedPackageInfoMap = GNCSSettingsOptOut.this.fromJSON(paramContext, (String)localObject1);
        }
        GNCSSettingsOptOut.this.dialerPackageList.clear();
        localObject1 = GNCSUtil.loadDialerPackages(paramContext).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ResolveInfo)((Iterator)localObject1).next();
          GNCSSettingsOptOut.this.dialerPackageList.add(((ResolveInfo)localObject2).activityInfo.packageName);
        }
        GNCSSettingsOptOut.this.smsPackageList.clear();
        GNCSSettingsOptOut.this.smsPackageList.addAll(GNCSUtil.loadSmsPackages(paramContext));
        GNCSConfig.getInstance().applyRemoteConfiguration();
        GNCSSettingsOptOut.this.save(paramContext);
      }
    });
  }
  
  public void remove(Context paramContext, GNCSApplicationInfo paramGNCSApplicationInfo)
  {
    this.blockedPackageInfoMap.remove(paramGNCSApplicationInfo.packageName);
    save(paramContext);
    Intent localIntent = new Intent("com.garmin.android.gncs.NOTIFICATION_FOR_PACKAGE_DISABLED");
    localIntent.putExtra("packageName", paramGNCSApplicationInfo.packageName);
    paramContext.sendBroadcast(localIntent);
  }
  
  public void save(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("gncs", 4).edit();
    paramContext.putString("blocked_notifications", toJSON(this.blockedPackageInfoMap));
    paramContext.commit();
  }
  
  public void save(Context paramContext, GNCSApplicationInfo paramGNCSApplicationInfo)
  {
    updateApplicationState(paramGNCSApplicationInfo);
    Intent localIntent;
    if (paramGNCSApplicationInfo.enabled)
    {
      localIntent = new Intent("com.garmin.android.gncs.NOTIFICATION_FOR_PACKAGE_ENABLED");
      localIntent.putExtra("packageName", paramGNCSApplicationInfo.packageName);
      paramContext.sendBroadcast(localIntent);
    }
    else
    {
      localIntent = new Intent("com.garmin.android.gncs.NOTIFICATION_FOR_PACKAGE_DISABLED");
      localIntent.putExtra("packageName", paramGNCSApplicationInfo.packageName);
      paramContext.sendBroadcast(localIntent);
    }
    save(paramContext);
  }
  
  public void setLastUserNotified(Context paramContext, long paramLong)
  {
    paramContext = paramContext.getSharedPreferences("gncs", 4).edit();
    paramContext.putLong("lastNotified", paramLong);
    paramContext.commit();
  }
  
  public boolean shouldSendToDevice(@NonNull GNCSNotificationInfo paramGNCSNotificationInfo)
  {
    if (paramGNCSNotificationInfo == null) {
      return false;
    }
    if ((!installedPackages.contains(paramGNCSNotificationInfo.packageName)) && (paramGNCSNotificationInfo.type != GNCSNotificationInfo.NotificationType.INCOMING_CALL) && (paramGNCSNotificationInfo.type != GNCSNotificationInfo.NotificationType.MISSED_CALL) && (paramGNCSNotificationInfo.type != GNCSNotificationInfo.NotificationType.VOICEMAIL) && (!GNCSUtil.isDialerPackage(null, paramGNCSNotificationInfo.packageName)))
    {
      localObject = new StringBuilder("Not sending to device because package ");
      ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
      ((StringBuilder)localObject).append(" is not one of our identified installed packages.");
      a.a(((StringBuilder)localObject).toString());
      return false;
    }
    Object localObject = paramGNCSNotificationInfo.packageName;
    int j = paramGNCSNotificationInfo.priority;
    String str = paramGNCSNotificationInfo.category;
    int k = paramGNCSNotificationInfo.notificationFlags;
    int m = ((GNCSPhoneCallListener)Injector.singletonOf(GNCSPhoneCallListener.class)).getCallState();
    Iterator localIterator = this.dialerPackageList.iterator();
    int i = 0;
    boolean bool1;
    for (;;)
    {
      bool1 = localIterator.hasNext();
      bool2 = true;
      if (!bool1) {
        break;
      }
      if (((String)localIterator.next()).equals(paramGNCSNotificationInfo.packageName)) {
        i = 1;
      }
    }
    if (!GNCSSettings.getSettings().isEnabled(paramGNCSNotificationInfo.packageName))
    {
      localObject = new StringBuilder("Not sending notification for package ");
      ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
      ((StringBuilder)localObject).append(" because the user has disabled it");
      a.a(((StringBuilder)localObject).toString());
    }
    for (;;)
    {
      bool1 = false;
      break;
      if (GNCSConfig.getInstance().isRestrictedType(paramGNCSNotificationInfo.type))
      {
        localObject = new StringBuilder("Not sending notification for type ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.type.name());
        ((StringBuilder)localObject).append(" because it is restricted in the configuration.");
        a.a(((StringBuilder)localObject).toString());
      }
      else if ((i != 0) && (m != 1) && (m != 4))
      {
        localObject = new StringBuilder("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it is the dialer and not one of the states we care about.  Current state is ");
        ((StringBuilder)localObject).append(m);
        a.a(((StringBuilder)localObject).toString());
      }
      else if (TextUtils.equals(str, "sys"))
      {
        localObject = new StringBuilder("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it is a system notification");
        a.a(((StringBuilder)localObject).toString());
      }
      else if (((k & 0x40) != 0) && (j <= 0))
      {
        localObject = new StringBuilder("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it for a foreground service");
        a.a(((StringBuilder)localObject).toString());
      }
      else if (((k & 0x100) != 0) && ((k & 0x2) != 0))
      {
        localObject = new StringBuilder("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it is a local only and an ongoing event notification");
        a.a(((StringBuilder)localObject).toString());
      }
      else if (((k & 0x2) != 0) && (j <= 0))
      {
        localObject = new StringBuilder("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it is an ongoing event that is not high priority");
        a.a(((StringBuilder)localObject).toString());
      }
      else if ((!((String)localObject).equals("android")) && (!((String)localObject).equals("com.android.systemui")) && (!((String)localObject).startsWith("com.android.providers")))
      {
        if (!((String)localObject).equals("com.google.android.dialer"))
        {
          bool1 = bool2;
          if (!((String)localObject).equals("com.android.phone")) {
            break;
          }
        }
        bool1 = bool2;
        if (Build.VERSION.SDK_INT > 19) {
          break;
        }
        a.a("Not using standard dialer application on 4.4.4 phones");
      }
      else
      {
        localObject = new StringBuilder("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it has a package name of 'android'");
        a.a(((StringBuilder)localObject).toString());
      }
    }
    localObject = GNCSApplicationHandler.getApplicationHandler(paramGNCSNotificationInfo.packageName, 0);
    boolean bool2 = bool1;
    if (localObject != null) {
      bool2 = ((GNCSApplicationHandler)localObject).shouldSendToDevice(paramGNCSNotificationInfo, bool1);
    }
    return bool2;
  }
  
  public void updateApplicationState(GNCSApplicationInfo paramGNCSApplicationInfo)
  {
    if (paramGNCSApplicationInfo.enabled)
    {
      this.blockedPackageInfoMap.remove(paramGNCSApplicationInfo.packageName);
      return;
    }
    this.blockedPackageInfoMap.put(paramGNCSApplicationInfo.packageName, paramGNCSApplicationInfo);
  }
  
  public static class InjectorConfiguration
    extends Configuration
  {
    public InjectorConfiguration() {}
    
    public void configure()
    {
      bindSingleton(GNCSSettingsOptOut.class, new Factory()
      {
        public GNCSSettingsOptOut create()
        {
          return new GNCSSettingsOptOut();
        }
      });
    }
  }
}
