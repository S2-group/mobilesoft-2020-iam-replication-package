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
import android.text.TextUtils;
import ch.qos.logback.classic.Logger;
import com.garmin.android.framework.util.designpattern.Factory;
import com.garmin.android.framework.util.inject.Configuration;
import com.garmin.android.framework.util.inject.Injector;
import com.garmin.android.gncs.GNCSApplicationInfo;
import com.garmin.android.gncs.GNCSNotificationInfo;
import com.garmin.android.gncs.GNCSNotificationInfo.NotificationType;
import com.garmin.android.gncs.GNCSPhoneCallListener;
import com.garmin.android.gncs.GNCSUtil;
import com.garmin.android.gncs.handlers.GNCSApplicationHandler;
import com.garmin.android.util.GNCSCoreUtil;
import com.garmin.glogger.Glogger;
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
    boolean bool2 = false;
    if (paramContext == null) {
      return false;
    }
    if (paramContext.requestedPermissions == null) {
      return false;
    }
    paramContext = paramContext.requestedPermissions;
    int i3 = paramContext.length;
    int i1 = 0;
    int j = i1;
    int i = j;
    int k = i;
    int i2;
    int m;
    int n;
    for (;;)
    {
      i2 = i;
      m = k;
      n = j;
      if (i1 >= i3) {
        break;
      }
      paramList = paramContext[i1];
      if (paramList.equals("android.permission.RECEIVE_MMS"))
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
      if (paramList.equals("android.permission.RECEIVE_SMS"))
      {
        if ((k != 0) && (i != 0))
        {
          n = 1;
          i2 = i;
          m = k;
          break;
        }
        j = 1;
      }
      if (paramList.equals("android.permission.READ_SMS"))
      {
        if ((k != 0) && (j != 0))
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
    boolean bool1 = bool2;
    if (m != 0)
    {
      bool1 = bool2;
      if (n != 0)
      {
        bool1 = bool2;
        if (i2 != 0) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public void enablePackage(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramContext = new StringBuilder();
      paramContext.append("Permanently enabling package ");
      paramContext.append(paramString);
      GNCSCoreUtil.tracelog(paramContext.toString());
      this.blockedPackageInfoMap.remove(paramString);
      if (!installedPackages.contains(paramString)) {
        installedPackages.add(paramString);
      }
    }
    else
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Permanently disabling package ");
      localStringBuilder.append(paramString);
      GNCSCoreUtil.tracelog(localStringBuilder.toString());
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
  
  public GNCSNotificationInfo.NotificationType getNotificationTypeForPackage(Context paramContext, String paramString1, String paramString2)
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
    return this.blockedPackageInfoMap.containsKey(paramString) ^ true;
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
        //   10: astore 6
        //   12: aload 6
        //   14: ldc 38
        //   16: iconst_0
        //   17: invokeinterface 44 3 0
        //   22: ifne +1546 -> 1568
        //   25: invokestatic 50	com/garmin/android/gncs/settings/GNCSConfig:getInstance	()Lcom/garmin/android/gncs/settings/GNCSConfig;
        //   28: invokevirtual 54	com/garmin/android/gncs/settings/GNCSConfig:performOptOutUpgrade	()Z
        //   31: ifeq +1537 -> 1568
        //   34: aload_0
        //   35: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   38: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   41: invokeinterface 64 1 0
        //   46: ifne +1522 -> 1568
        //   49: aconst_null
        //   50: astore 4
        //   52: aload 6
        //   54: ldc 66
        //   56: aconst_null
        //   57: invokeinterface 70 3 0
        //   62: astore 8
        //   64: aload_0
        //   65: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   68: invokevirtual 74	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
        //   71: astore 7
        //   73: aload 7
        //   75: iconst_0
        //   76: invokevirtual 80	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
        //   79: astore 5
        //   81: aload 5
        //   83: astore 4
        //   85: aload 8
        //   87: ifnull +1199 -> 1286
        //   90: new 82	java/lang/StringBuilder
        //   93: dup
        //   94: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   97: astore 5
        //   99: aload 5
        //   101: ldc 85
        //   103: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   106: pop
        //   107: aload 5
        //   109: aload 8
        //   111: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: pop
        //   115: ldc 91
        //   117: aload 5
        //   119: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   122: invokestatic 101	com/garmin/android/util/GNCSCoreUtil:tracelog	(Ljava/lang/String;Ljava/lang/String;)V
        //   125: aload_0
        //   126: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   129: aload_0
        //   130: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   133: aload 8
        //   135: invokevirtual 105	com/garmin/android/gncs/settings/GNCSSettingsOptOut:fromJSON	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/Map;
        //   138: astore 5
        //   140: aload 5
        //   142: ldc 107
        //   144: invokeinterface 111 2 0
        //   149: ifeq +64 -> 213
        //   152: aload_0
        //   153: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   156: aload_0
        //   157: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   160: getstatic 117	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:INCOMING_CALL	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   163: invokestatic 121	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$000	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;)Ljava/util/List;
        //   166: invokeinterface 127 1 0
        //   171: astore 8
        //   173: aload 8
        //   175: invokeinterface 132 1 0
        //   180: ifeq +33 -> 213
        //   183: aload 8
        //   185: invokeinterface 136 1 0
        //   190: checkcast 138	com/garmin/android/gncs/GNCSApplicationInfo
        //   193: astore 9
        //   195: aload 5
        //   197: aload 9
        //   199: getfield 142	com/garmin/android/gncs/GNCSApplicationInfo:packageName	Ljava/lang/String;
        //   202: aload 9
        //   204: invokeinterface 146 3 0
        //   209: pop
        //   210: goto -37 -> 173
        //   213: aload 5
        //   215: ldc -108
        //   217: invokeinterface 111 2 0
        //   222: ifeq +64 -> 286
        //   225: aload_0
        //   226: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   229: aload_0
        //   230: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   233: getstatic 151	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:MISSED_CALL	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   236: invokestatic 121	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$000	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;)Ljava/util/List;
        //   239: invokeinterface 127 1 0
        //   244: astore 8
        //   246: aload 8
        //   248: invokeinterface 132 1 0
        //   253: ifeq +33 -> 286
        //   256: aload 8
        //   258: invokeinterface 136 1 0
        //   263: checkcast 138	com/garmin/android/gncs/GNCSApplicationInfo
        //   266: astore 9
        //   268: aload 5
        //   270: aload 9
        //   272: getfield 142	com/garmin/android/gncs/GNCSApplicationInfo:packageName	Ljava/lang/String;
        //   275: aload 9
        //   277: invokeinterface 146 3 0
        //   282: pop
        //   283: goto -37 -> 246
        //   286: aload 5
        //   288: ldc -103
        //   290: invokeinterface 111 2 0
        //   295: ifeq +64 -> 359
        //   298: aload_0
        //   299: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   302: aload_0
        //   303: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   306: getstatic 156	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:VOICEMAIL	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   309: invokestatic 121	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$000	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;)Ljava/util/List;
        //   312: invokeinterface 127 1 0
        //   317: astore 8
        //   319: aload 8
        //   321: invokeinterface 132 1 0
        //   326: ifeq +33 -> 359
        //   329: aload 8
        //   331: invokeinterface 136 1 0
        //   336: checkcast 138	com/garmin/android/gncs/GNCSApplicationInfo
        //   339: astore 9
        //   341: aload 5
        //   343: aload 9
        //   345: getfield 142	com/garmin/android/gncs/GNCSApplicationInfo:packageName	Ljava/lang/String;
        //   348: aload 9
        //   350: invokeinterface 146 3 0
        //   355: pop
        //   356: goto -37 -> 319
        //   359: aload 5
        //   361: ldc -98
        //   363: invokeinterface 111 2 0
        //   368: ifeq +64 -> 432
        //   371: aload_0
        //   372: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   375: aload_0
        //   376: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   379: getstatic 161	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:SMS	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   382: invokestatic 121	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$000	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;)Ljava/util/List;
        //   385: invokeinterface 127 1 0
        //   390: astore 8
        //   392: aload 8
        //   394: invokeinterface 132 1 0
        //   399: ifeq +33 -> 432
        //   402: aload 8
        //   404: invokeinterface 136 1 0
        //   409: checkcast 138	com/garmin/android/gncs/GNCSApplicationInfo
        //   412: astore 9
        //   414: aload 5
        //   416: aload 9
        //   418: getfield 142	com/garmin/android/gncs/GNCSApplicationInfo:packageName	Ljava/lang/String;
        //   421: aload 9
        //   423: invokeinterface 146 3 0
        //   428: pop
        //   429: goto -37 -> 392
        //   432: aload 5
        //   434: ldc -93
        //   436: invokeinterface 111 2 0
        //   441: ifeq +64 -> 505
        //   444: aload_0
        //   445: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   448: aload_0
        //   449: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   452: getstatic 166	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:SCHEDULE	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   455: invokestatic 121	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$000	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;)Ljava/util/List;
        //   458: invokeinterface 127 1 0
        //   463: astore 8
        //   465: aload 8
        //   467: invokeinterface 132 1 0
        //   472: ifeq +33 -> 505
        //   475: aload 8
        //   477: invokeinterface 136 1 0
        //   482: checkcast 138	com/garmin/android/gncs/GNCSApplicationInfo
        //   485: astore 9
        //   487: aload 5
        //   489: aload 9
        //   491: getfield 142	com/garmin/android/gncs/GNCSApplicationInfo:packageName	Ljava/lang/String;
        //   494: aload 9
        //   496: invokeinterface 146 3 0
        //   501: pop
        //   502: goto -37 -> 465
        //   505: getstatic 171	com/garmin/android/gncs/settings/GNCSSettings:defaultPackageMap	Ljava/util/Map;
        //   508: invokeinterface 175 1 0
        //   513: invokeinterface 178 1 0
        //   518: astore 8
        //   520: aload 8
        //   522: invokeinterface 132 1 0
        //   527: ifeq +149 -> 676
        //   530: aload 8
        //   532: invokeinterface 136 1 0
        //   537: checkcast 180	java/lang/String
        //   540: astore 9
        //   542: aload 5
        //   544: aload 9
        //   546: invokeinterface 111 2 0
        //   551: ifne +79 -> 630
        //   554: new 82	java/lang/StringBuilder
        //   557: dup
        //   558: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   561: astore 10
        //   563: aload 10
        //   565: ldc -74
        //   567: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   570: pop
        //   571: aload 10
        //   573: aload 9
        //   575: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   578: pop
        //   579: aload 10
        //   581: ldc -72
        //   583: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   586: pop
        //   587: ldc 91
        //   589: aload 10
        //   591: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   594: invokestatic 101	com/garmin/android/util/GNCSCoreUtil:tracelog	(Ljava/lang/String;Ljava/lang/String;)V
        //   597: aload_0
        //   598: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   601: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   604: aload 9
        //   606: aload_0
        //   607: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   610: aload_0
        //   611: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   614: aload 9
        //   616: aload 7
        //   618: invokevirtual 188	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   621: invokeinterface 146 3 0
        //   626: pop
        //   627: goto -107 -> 520
        //   630: new 82	java/lang/StringBuilder
        //   633: dup
        //   634: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   637: astore 10
        //   639: aload 10
        //   641: ldc -66
        //   643: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   646: pop
        //   647: aload 10
        //   649: aload 9
        //   651: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   654: pop
        //   655: aload 10
        //   657: ldc -64
        //   659: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   662: pop
        //   663: ldc 91
        //   665: aload 10
        //   667: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   670: invokestatic 101	com/garmin/android/util/GNCSCoreUtil:tracelog	(Ljava/lang/String;Ljava/lang/String;)V
        //   673: goto -153 -> 520
        //   676: aload 4
        //   678: ifnull +579 -> 1257
        //   681: aload 7
        //   683: sipush 4096
        //   686: invokevirtual 195	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
        //   689: astore 8
        //   691: aload 4
        //   693: invokeinterface 127 1 0
        //   698: astore 4
        //   700: aload 4
        //   702: invokeinterface 132 1 0
        //   707: ifeq +550 -> 1257
        //   710: aload 4
        //   712: invokeinterface 136 1 0
        //   717: checkcast 197	android/content/pm/ApplicationInfo
        //   720: getfield 198	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
        //   723: astore 9
        //   725: getstatic 171	com/garmin/android/gncs/settings/GNCSSettings:defaultPackageMap	Ljava/util/Map;
        //   728: aload 9
        //   730: invokeinterface 111 2 0
        //   735: ifeq +49 -> 784
        //   738: new 82	java/lang/StringBuilder
        //   741: dup
        //   742: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   745: astore 10
        //   747: aload 10
        //   749: ldc -56
        //   751: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   754: pop
        //   755: aload 10
        //   757: aload 9
        //   759: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   762: pop
        //   763: aload 10
        //   765: ldc -54
        //   767: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   770: pop
        //   771: ldc 91
        //   773: aload 10
        //   775: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   778: invokestatic 101	com/garmin/android/util/GNCSCoreUtil:tracelog	(Ljava/lang/String;Ljava/lang/String;)V
        //   781: goto -81 -> 700
        //   784: aload_0
        //   785: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   788: aload 9
        //   790: invokestatic 208	com/garmin/android/gncs/GNCSUtil:isDialerPackage	(Landroid/content/Context;Ljava/lang/String;)Z
        //   793: istore_3
        //   794: aload 8
        //   796: ifnonnull +8 -> 804
        //   799: iconst_0
        //   800: istore_2
        //   801: goto +21 -> 822
        //   804: aload_0
        //   805: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   808: aload_0
        //   809: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   812: aload 8
        //   814: aload 9
        //   816: aload 7
        //   818: invokestatic 212	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$100	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Landroid/content/pm/PackageManager;)Z
        //   821: istore_2
        //   822: getstatic 171	com/garmin/android/gncs/settings/GNCSSettings:defaultPackageMap	Ljava/util/Map;
        //   825: aload 9
        //   827: invokeinterface 216 2 0
        //   832: checkcast 113	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType
        //   835: astore 10
        //   837: aload 10
        //   839: ifnull +738 -> 1577
        //   842: aload 10
        //   844: getstatic 166	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:SCHEDULE	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   847: if_acmpne +730 -> 1577
        //   850: iconst_1
        //   851: istore_1
        //   852: goto +3 -> 855
        //   855: iload_3
        //   856: ifeq +91 -> 947
        //   859: aload 5
        //   861: ldc 107
        //   863: invokeinterface 111 2 0
        //   868: ifne +79 -> 947
        //   871: new 82	java/lang/StringBuilder
        //   874: dup
        //   875: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   878: astore 10
        //   880: aload 10
        //   882: ldc -74
        //   884: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   887: pop
        //   888: aload 10
        //   890: aload 9
        //   892: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   895: pop
        //   896: aload 10
        //   898: ldc -38
        //   900: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   903: pop
        //   904: ldc 91
        //   906: aload 10
        //   908: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   911: invokestatic 101	com/garmin/android/util/GNCSCoreUtil:tracelog	(Ljava/lang/String;Ljava/lang/String;)V
        //   914: aload_0
        //   915: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   918: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   921: aload 9
        //   923: aload_0
        //   924: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   927: aload_0
        //   928: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   931: aload 9
        //   933: aload 7
        //   935: invokevirtual 188	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   938: invokeinterface 146 3 0
        //   943: pop
        //   944: goto -244 -> 700
        //   947: iload_2
        //   948: ifeq +91 -> 1039
        //   951: aload 5
        //   953: ldc -98
        //   955: invokeinterface 111 2 0
        //   960: ifne +79 -> 1039
        //   963: new 82	java/lang/StringBuilder
        //   966: dup
        //   967: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   970: astore 10
        //   972: aload 10
        //   974: ldc -74
        //   976: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   979: pop
        //   980: aload 10
        //   982: aload 9
        //   984: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   987: pop
        //   988: aload 10
        //   990: ldc -36
        //   992: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   995: pop
        //   996: ldc 91
        //   998: aload 10
        //   1000: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1003: invokestatic 101	com/garmin/android/util/GNCSCoreUtil:tracelog	(Ljava/lang/String;Ljava/lang/String;)V
        //   1006: aload_0
        //   1007: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1010: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   1013: aload 9
        //   1015: aload_0
        //   1016: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1019: aload_0
        //   1020: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1023: aload 9
        //   1025: aload 7
        //   1027: invokevirtual 188	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   1030: invokeinterface 146 3 0
        //   1035: pop
        //   1036: goto -336 -> 700
        //   1039: iload_1
        //   1040: ifeq +91 -> 1131
        //   1043: aload 5
        //   1045: ldc -93
        //   1047: invokeinterface 111 2 0
        //   1052: ifne +79 -> 1131
        //   1055: new 82	java/lang/StringBuilder
        //   1058: dup
        //   1059: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   1062: astore 10
        //   1064: aload 10
        //   1066: ldc -74
        //   1068: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1071: pop
        //   1072: aload 10
        //   1074: aload 9
        //   1076: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1079: pop
        //   1080: aload 10
        //   1082: ldc -34
        //   1084: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1087: pop
        //   1088: ldc 91
        //   1090: aload 10
        //   1092: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1095: invokestatic 101	com/garmin/android/util/GNCSCoreUtil:tracelog	(Ljava/lang/String;Ljava/lang/String;)V
        //   1098: aload_0
        //   1099: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1102: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   1105: aload 9
        //   1107: aload_0
        //   1108: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1111: aload_0
        //   1112: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1115: aload 9
        //   1117: aload 7
        //   1119: invokevirtual 188	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   1122: invokeinterface 146 3 0
        //   1127: pop
        //   1128: goto -428 -> 700
        //   1131: aload 5
        //   1133: aload 9
        //   1135: invokeinterface 111 2 0
        //   1140: ifne +79 -> 1219
        //   1143: new 82	java/lang/StringBuilder
        //   1146: dup
        //   1147: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   1150: astore 10
        //   1152: aload 10
        //   1154: ldc -74
        //   1156: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1159: pop
        //   1160: aload 10
        //   1162: aload 9
        //   1164: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1167: pop
        //   1168: aload 10
        //   1170: ldc -32
        //   1172: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1175: pop
        //   1176: ldc 91
        //   1178: aload 10
        //   1180: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1183: invokestatic 101	com/garmin/android/util/GNCSCoreUtil:tracelog	(Ljava/lang/String;Ljava/lang/String;)V
        //   1186: aload_0
        //   1187: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1190: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   1193: aload 9
        //   1195: aload_0
        //   1196: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1199: aload_0
        //   1200: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1203: aload 9
        //   1205: aload 7
        //   1207: invokevirtual 188	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   1210: invokeinterface 146 3 0
        //   1215: pop
        //   1216: goto -516 -> 700
        //   1219: new 82	java/lang/StringBuilder
        //   1222: dup
        //   1223: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   1226: astore 10
        //   1228: aload 10
        //   1230: ldc -66
        //   1232: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1235: pop
        //   1236: aload 10
        //   1238: aload 9
        //   1240: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1243: pop
        //   1244: ldc 91
        //   1246: aload 10
        //   1248: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1251: invokestatic 101	com/garmin/android/util/GNCSCoreUtil:tracelog	(Ljava/lang/String;Ljava/lang/String;)V
        //   1254: goto -554 -> 700
        //   1257: aload 6
        //   1259: invokeinterface 228 1 0
        //   1264: astore 4
        //   1266: aload 4
        //   1268: ldc 66
        //   1270: invokeinterface 234 2 0
        //   1275: pop
        //   1276: aload 4
        //   1278: invokeinterface 237 1 0
        //   1283: goto +258 -> 1541
        //   1286: ldc 91
        //   1288: ldc -17
        //   1290: invokestatic 101	com/garmin/android/util/GNCSCoreUtil:tracelog	(Ljava/lang/String;Ljava/lang/String;)V
        //   1293: aload 7
        //   1295: sipush 4096
        //   1298: invokevirtual 195	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
        //   1301: astore 5
        //   1303: aload 4
        //   1305: invokeinterface 127 1 0
        //   1310: astore 4
        //   1312: aload 4
        //   1314: invokeinterface 132 1 0
        //   1319: ifeq +211 -> 1530
        //   1322: aload 4
        //   1324: invokeinterface 136 1 0
        //   1329: checkcast 197	android/content/pm/ApplicationInfo
        //   1332: getfield 198	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
        //   1335: astore 8
        //   1337: getstatic 171	com/garmin/android/gncs/settings/GNCSSettings:defaultPackageMap	Ljava/util/Map;
        //   1340: aload 8
        //   1342: invokeinterface 111 2 0
        //   1347: ifeq +6 -> 1353
        //   1350: goto -38 -> 1312
        //   1353: aload_0
        //   1354: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1357: aload 8
        //   1359: invokestatic 208	com/garmin/android/gncs/GNCSUtil:isDialerPackage	(Landroid/content/Context;Ljava/lang/String;)Z
        //   1362: istore_3
        //   1363: aload 5
        //   1365: ifnonnull +8 -> 1373
        //   1368: iconst_0
        //   1369: istore_2
        //   1370: goto +21 -> 1391
        //   1373: aload_0
        //   1374: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1377: aload_0
        //   1378: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1381: aload 5
        //   1383: aload 8
        //   1385: aload 7
        //   1387: invokestatic 212	com/garmin/android/gncs/settings/GNCSSettingsOptOut:access$100	(Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Landroid/content/pm/PackageManager;)Z
        //   1390: istore_2
        //   1391: getstatic 171	com/garmin/android/gncs/settings/GNCSSettings:defaultPackageMap	Ljava/util/Map;
        //   1394: aload 8
        //   1396: invokeinterface 216 2 0
        //   1401: checkcast 113	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType
        //   1404: astore 9
        //   1406: aload 9
        //   1408: ifnull +174 -> 1582
        //   1411: aload 9
        //   1413: getstatic 166	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:SCHEDULE	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   1416: if_acmpne +8 -> 1424
        //   1419: iconst_1
        //   1420: istore_1
        //   1421: goto +163 -> 1584
        //   1424: aload 9
        //   1426: getstatic 161	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:SMS	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   1429: if_acmpne +10 -> 1439
        //   1432: iconst_0
        //   1433: istore_1
        //   1434: iconst_1
        //   1435: istore_2
        //   1436: goto +148 -> 1584
        //   1439: aload 9
        //   1441: getstatic 117	com/garmin/android/gncs/GNCSNotificationInfo$NotificationType:INCOMING_CALL	Lcom/garmin/android/gncs/GNCSNotificationInfo$NotificationType;
        //   1444: if_acmpne +138 -> 1582
        //   1447: iconst_0
        //   1448: istore_1
        //   1449: iconst_1
        //   1450: istore_3
        //   1451: goto +133 -> 1584
        //   1454: new 82	java/lang/StringBuilder
        //   1457: dup
        //   1458: invokespecial 83	java/lang/StringBuilder:<init>	()V
        //   1461: astore 9
        //   1463: aload 9
        //   1465: ldc -15
        //   1467: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1470: pop
        //   1471: aload 9
        //   1473: aload 8
        //   1475: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1478: pop
        //   1479: aload 9
        //   1481: ldc -13
        //   1483: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1486: pop
        //   1487: ldc 91
        //   1489: aload 9
        //   1491: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   1494: invokestatic 101	com/garmin/android/util/GNCSCoreUtil:tracelog	(Ljava/lang/String;Ljava/lang/String;)V
        //   1497: aload_0
        //   1498: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1501: getfield 58	com/garmin/android/gncs/settings/GNCSSettingsOptOut:blockedPackageInfoMap	Ljava/util/Map;
        //   1504: aload 8
        //   1506: aload_0
        //   1507: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1510: aload_0
        //   1511: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1514: aload 8
        //   1516: aload 7
        //   1518: invokevirtual 188	com/garmin/android/gncs/settings/GNCSSettingsOptOut:loadPackage	(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/PackageManager;)Lcom/garmin/android/gncs/GNCSApplicationInfo;
        //   1521: invokeinterface 146 3 0
        //   1526: pop
        //   1527: goto -215 -> 1312
        //   1530: aload_0
        //   1531: getfield 19	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:this$0	Lcom/garmin/android/gncs/settings/GNCSSettingsOptOut;
        //   1534: aload_0
        //   1535: getfield 21	com/garmin/android/gncs/settings/GNCSSettingsOptOut$1:val$context	Landroid/content/Context;
        //   1538: invokevirtual 246	com/garmin/android/gncs/settings/GNCSSettingsOptOut:save	(Landroid/content/Context;)V
        //   1541: aload 6
        //   1543: invokeinterface 228 1 0
        //   1548: astore 4
        //   1550: aload 4
        //   1552: ldc 38
        //   1554: iconst_1
        //   1555: invokeinterface 250 3 0
        //   1560: pop
        //   1561: aload 4
        //   1563: invokeinterface 237 1 0
        //   1568: return
        //   1569: astore 4
        //   1571: return
        //   1572: astore 5
        //   1574: goto -1489 -> 85
        //   1577: iconst_0
        //   1578: istore_1
        //   1579: goto -724 -> 855
        //   1582: iconst_0
        //   1583: istore_1
        //   1584: iload_3
        //   1585: ifne -273 -> 1312
        //   1588: iload_2
        //   1589: ifne -277 -> 1312
        //   1592: iload_1
        //   1593: ifeq -139 -> 1454
        //   1596: goto -284 -> 1312
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1599	0	this	1
        //   851	742	1	i	int
        //   800	789	2	bool1	boolean
        //   793	792	3	bool2	boolean
        //   50	1512	4	localObject1	Object
        //   1569	1	4	localException1	Exception
        //   79	1303	5	localObject2	Object
        //   1572	1	5	localException2	Exception
        //   10	1532	6	localSharedPreferences	SharedPreferences
        //   71	1446	7	localPackageManager	PackageManager
        //   62	1453	8	localObject3	Object
        //   193	1297	9	localObject4	Object
        //   561	686	10	localObject5	Object
        // Exception table:
        //   from	to	target	type
        //   0	49	1569	java/lang/Exception
        //   52	73	1569	java/lang/Exception
        //   90	173	1569	java/lang/Exception
        //   173	210	1569	java/lang/Exception
        //   213	246	1569	java/lang/Exception
        //   246	283	1569	java/lang/Exception
        //   286	319	1569	java/lang/Exception
        //   319	356	1569	java/lang/Exception
        //   359	392	1569	java/lang/Exception
        //   392	429	1569	java/lang/Exception
        //   432	465	1569	java/lang/Exception
        //   465	502	1569	java/lang/Exception
        //   505	520	1569	java/lang/Exception
        //   520	627	1569	java/lang/Exception
        //   630	673	1569	java/lang/Exception
        //   681	700	1569	java/lang/Exception
        //   700	781	1569	java/lang/Exception
        //   784	794	1569	java/lang/Exception
        //   804	822	1569	java/lang/Exception
        //   822	837	1569	java/lang/Exception
        //   842	850	1569	java/lang/Exception
        //   859	944	1569	java/lang/Exception
        //   951	1036	1569	java/lang/Exception
        //   1043	1128	1569	java/lang/Exception
        //   1131	1216	1569	java/lang/Exception
        //   1219	1254	1569	java/lang/Exception
        //   1257	1283	1569	java/lang/Exception
        //   1286	1312	1569	java/lang/Exception
        //   1312	1350	1569	java/lang/Exception
        //   1353	1363	1569	java/lang/Exception
        //   1373	1391	1569	java/lang/Exception
        //   1391	1406	1569	java/lang/Exception
        //   1411	1419	1569	java/lang/Exception
        //   1424	1432	1569	java/lang/Exception
        //   1439	1447	1569	java/lang/Exception
        //   1454	1527	1569	java/lang/Exception
        //   1530	1541	1569	java/lang/Exception
        //   1541	1568	1569	java/lang/Exception
        //   73	81	1572	java/lang/Exception
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
        Glogger.getLogger("GNCSTrace").debug("Failed to get list of app due to transaction too large exception");
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
  
  public boolean shouldSendToDevice(GNCSNotificationInfo paramGNCSNotificationInfo)
  {
    if (paramGNCSNotificationInfo == null) {
      return false;
    }
    if ((!installedPackages.contains(paramGNCSNotificationInfo.packageName)) && (paramGNCSNotificationInfo.type != GNCSNotificationInfo.NotificationType.INCOMING_CALL) && (paramGNCSNotificationInfo.type != GNCSNotificationInfo.NotificationType.MISSED_CALL) && (paramGNCSNotificationInfo.type != GNCSNotificationInfo.NotificationType.VOICEMAIL) && (!GNCSUtil.isDialerPackage(null, paramGNCSNotificationInfo.packageName)))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Not sending to device because package ");
      ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
      ((StringBuilder)localObject).append(" is not one of our identified installed packages.");
      GNCSCoreUtil.tracelog(((StringBuilder)localObject).toString());
      return false;
    }
    Object localObject = paramGNCSNotificationInfo.packageName;
    int i = paramGNCSNotificationInfo.notificationId;
    int j = paramGNCSNotificationInfo.priority;
    String str = paramGNCSNotificationInfo.category;
    int k = paramGNCSNotificationInfo.notificationFlags;
    int m = ((GNCSPhoneCallListener)Injector.singletonOf(GNCSPhoneCallListener.class)).getCallState();
    Iterator localIterator = this.dialerPackageList.iterator();
    i = 0;
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
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Not sending notification for package ");
      ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
      ((StringBuilder)localObject).append(" because the user has disabled it");
      GNCSCoreUtil.tracelog(((StringBuilder)localObject).toString());
    }
    for (;;)
    {
      bool1 = false;
      break;
      if (GNCSConfig.getInstance().isRestrictedType(paramGNCSNotificationInfo.type))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Not sending notification for type ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.type.name());
        ((StringBuilder)localObject).append(" because it is restricted in the configuration.");
        GNCSCoreUtil.tracelog(((StringBuilder)localObject).toString());
      }
      else if ((i != 0) && (m != 1) && (m != 4))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it is the dialer and not one of the states we care about.  Current state is ");
        ((StringBuilder)localObject).append(m);
        GNCSCoreUtil.tracelog(((StringBuilder)localObject).toString());
      }
      else if (TextUtils.equals(str, "sys"))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it is a system notification");
        GNCSCoreUtil.tracelog(((StringBuilder)localObject).toString());
      }
      else if (((k & 0x40) != 0) && (j < 1))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it for a foreground service");
        GNCSCoreUtil.tracelog(((StringBuilder)localObject).toString());
      }
      else if (((k & 0x100) != 0) && ((k & 0x2) != 0))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it is a local only and an ongoing event notification");
        GNCSCoreUtil.tracelog(((StringBuilder)localObject).toString());
      }
      else if (((k & 0x2) != 0) && (j < 1))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it is an ongoing event that is not high priority");
        GNCSCoreUtil.tracelog(((StringBuilder)localObject).toString());
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
        GNCSCoreUtil.tracelog("Not using standard dialer application on 4.4.4 phones");
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Not sending notification for package ");
        ((StringBuilder)localObject).append(paramGNCSNotificationInfo.packageName);
        ((StringBuilder)localObject).append(" because it has a package name of 'android'");
        GNCSCoreUtil.tracelog(((StringBuilder)localObject).toString());
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
