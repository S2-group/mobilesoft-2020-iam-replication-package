package tmsdk.common;

import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tmsdk.bg.module.wificonnect.ISharedPreferenceFactory;
import tmsdk.common.module.software.AppEntity;
import tmsdkobf.bs;
import tmsdkobf.bt;
import tmsdkobf.bx;
import tmsdkobf.bz;
import tmsdkobf.cf;
import tmsdkobf.dc;
import tmsdkobf.ek;
import tmsdkobf.eo;

public final class TMSDKContext
{
  public static final String CON_APP_BUILD_TYPE = "app_build_type";
  public static final String CON_CHANNEL = "channel";
  public static final String CON_CVERSION = "cversion";
  public static final String CON_HOST_URL = "host_url";
  public static final String CON_HOTFIX = "hotfix";
  public static final String CON_LC = "lc";
  public static final String CON_PKGKEY = "pkgkey";
  public static final String CON_PLATFORM = "platform";
  public static final String CON_PRE_LIB_PATH = "pre_lib_path";
  public static final String CON_PRODUCT = "product";
  public static final String CON_PVERSION = "pversion";
  public static final String CON_SDK_LIBNAME = "sdk_libname";
  public static final String CON_SOFTVERSION = "softversion";
  public static final String CON_SUB_PLATFORM = "sub_platform";
  private static final String SDK_VERSION = "3.3.0";
  private static final String SDK_VERSION_INFO = "3.3.0 20161028162037";
  private static final String SDK_VERSION_MFR = "3.3.0";
  private static final String TAG = "TMSDKContext";
  public static final String TCP_SERVER_ADDRESS = "tcp_server_address";
  private static final String TMS_LIB_VERSION = "2.0.8-mfr";
  public static final String USE_IP_LIST = "use_ip_list";
  private static SystemInterfaceDelegate mSystemDelegate;
  private static Context sApplication;
  private static Map<String, String> sEnvMap = new HashMap();
  
  static
  {
    sEnvMap.put("sdk_libname", "Tmsdk-2.0.8-mfr");
    sEnvMap.put("pre_lib_path", null);
    sEnvMap.put("softversion", "3.3.0");
    sEnvMap.put("host_url", "http://pmir.3g.qq.com");
    sEnvMap.put("tcp_server_address", "mazu.3g.qq.com");
    sEnvMap.put("use_ip_list", "true");
    sEnvMap.put("lc", "0F90F8C65A02CAEC");
    sEnvMap.put("channel", "null");
    sEnvMap.put("platform", "default");
    sEnvMap.put("pversion", "3");
    sEnvMap.put("cversion", "3");
    sEnvMap.put("hotfix", "0");
    sEnvMap.put("sub_platform", String.valueOf(201));
    sEnvMap.put("product", String.valueOf(70));
    sEnvMap.put("pkgkey", "null");
  }
  
  public TMSDKContext() {}
  
  public static boolean checkLisence()
  {
    return bx.S().Q();
  }
  
  private static native int doRegisterNatives(int paramInt, Class<?> paramClass);
  
  public static Context getApplicaionContext()
  {
    return sApplication.getApplicationContext();
  }
  
  public static boolean getBooleanFromEnvMap(String paramString)
  {
    try
    {
      paramString = (String)sEnvMap.get(paramString);
      if (!TextUtils.isEmpty(paramString))
      {
        boolean bool = Boolean.valueOf(paramString).booleanValue();
        return bool;
      }
      return false;
    }
    finally {}
  }
  
  public static int getIntFromEnvMap(String paramString)
  {
    try
    {
      paramString = (String)sEnvMap.get(paramString);
      if (!TextUtils.isEmpty(paramString))
      {
        int i = Integer.valueOf(paramString).intValue();
        return i;
      }
      return 0;
    }
    finally {}
  }
  
  public static String getSDKVersionInfo()
  {
    return "3.3.0 20161028162037";
  }
  
  public static String getStrFromEnvMap(String paramString)
  {
    try
    {
      String str2 = (String)sEnvMap.get(paramString);
      String str1 = str2;
      if (paramString.equals("softversion")) {
        if (str2 != null)
        {
          str1 = str2;
          if (!str2.contains("0.0.0")) {}
        }
        else
        {
          paramString = getApplicaionContext();
          paramString = TMServiceFactory.getSystemInfoService().f(paramString.getPackageName(), 8);
          str1 = str2;
          if (paramString != null) {
            str1 = paramString.getVersion();
          }
        }
      }
      return str1;
    }
    finally {}
  }
  
  public static final SystemInterfaceDelegate getSystemInterfaceDelegate()
  {
    return mSystemDelegate;
  }
  
  /* Error */
  public static void init(Context paramContext, ITMSApplicaionConfig paramITMSApplicaionConfig, SystemInterfaceDelegate paramSystemInterfaceDelegate)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 215	java/lang/RuntimeException
    //   7: dup
    //   8: ldc -39
    //   10: invokespecial 220	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_2
    //   15: ifnonnull +13 -> 28
    //   18: new 215	java/lang/RuntimeException
    //   21: dup
    //   22: ldc -34
    //   24: invokespecial 220	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   27: athrow
    //   28: aload_2
    //   29: putstatic 209	tmsdk/common/TMSDKContext:mSystemDelegate	Ltmsdk/common/TMSDKContext$SystemInterfaceDelegate;
    //   32: ldc -32
    //   34: new 226	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   41: ldc -27
    //   43: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: invokestatic 235	tmsdk/common/TMSDKContext:getSDKVersionInfo	()Ljava/lang/String;
    //   49: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual 238	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: invokestatic 244	tmsdkobf/eo:e	(Ljava/lang/String;Ljava/lang/Object;)V
    //   58: aload_0
    //   59: invokevirtual 139	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   62: putstatic 134	tmsdk/common/TMSDKContext:sApplication	Landroid/content/Context;
    //   65: invokestatic 246	tmsdk/common/TMSDKContext:checkLisence	()Z
    //   68: ifne +17 -> 85
    //   71: aconst_null
    //   72: putstatic 134	tmsdk/common/TMSDKContext:sApplication	Landroid/content/Context;
    //   75: new 215	java/lang/RuntimeException
    //   78: dup
    //   79: ldc -8
    //   81: invokespecial 220	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   84: athrow
    //   85: ldc 47
    //   87: invokestatic 250	tmsdk/common/TMSDKContext:getStrFromEnvMap	(Ljava/lang/String;)Ljava/lang/String;
    //   90: invokevirtual 253	java/lang/String:trim	()Ljava/lang/String;
    //   93: ldc -1
    //   95: invokevirtual 259	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   98: astore_0
    //   99: aload_0
    //   100: arraylength
    //   101: iconst_3
    //   102: if_icmplt +45 -> 147
    //   105: getstatic 85	tmsdk/common/TMSDKContext:sEnvMap	Ljava/util/Map;
    //   108: ldc 41
    //   110: aload_0
    //   111: iconst_0
    //   112: aaload
    //   113: invokeinterface 93 3 0
    //   118: pop
    //   119: getstatic 85	tmsdk/common/TMSDKContext:sEnvMap	Ljava/util/Map;
    //   122: ldc 17
    //   124: aload_0
    //   125: iconst_1
    //   126: aaload
    //   127: invokeinterface 93 3 0
    //   132: pop
    //   133: getstatic 85	tmsdk/common/TMSDKContext:sEnvMap	Ljava/util/Map;
    //   136: ldc 23
    //   138: aload_0
    //   139: iconst_2
    //   140: aaload
    //   141: invokeinterface 93 3 0
    //   146: pop
    //   147: ldc 2
    //   149: monitorenter
    //   150: invokestatic 125	tmsdkobf/bx:S	()Ltmsdkobf/bx;
    //   153: invokevirtual 262	tmsdkobf/bx:R	()Ljava/lang/String;
    //   156: astore_2
    //   157: getstatic 85	tmsdk/common/TMSDKContext:sEnvMap	Ljava/util/Map;
    //   160: astore_3
    //   161: aload_2
    //   162: ifnonnull +89 -> 251
    //   165: ldc 103
    //   167: astore_0
    //   168: aload_3
    //   169: ldc 14
    //   171: aload_0
    //   172: invokeinterface 93 3 0
    //   177: pop
    //   178: ldc_w 264
    //   181: new 226	java/lang/StringBuilder
    //   184: dup
    //   185: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   188: ldc_w 266
    //   191: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: aload_2
    //   195: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: invokevirtual 238	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   201: invokestatic 269	tmsdkobf/eo:a	(Ljava/lang/String;Ljava/lang/Object;)V
    //   204: aload_1
    //   205: ifnull +22 -> 227
    //   208: aload_1
    //   209: new 80	java/util/HashMap
    //   212: dup
    //   213: getstatic 85	tmsdk/common/TMSDKContext:sEnvMap	Ljava/util/Map;
    //   216: invokespecial 272	java/util/HashMap:<init>	(Ljava/util/Map;)V
    //   219: invokeinterface 278 2 0
    //   224: putstatic 85	tmsdk/common/TMSDKContext:sEnvMap	Ljava/util/Map;
    //   227: ldc 2
    //   229: monitorexit
    //   230: invokestatic 283	tmsdkobf/bt:J	()V
    //   233: iconst_0
    //   234: ldc_w 285
    //   237: invokestatic 289	tmsdk/common/TMSDKContext:registerNatives	(ILjava/lang/Class;)V
    //   240: invokestatic 292	tmsdkobf/bt:N	()I
    //   243: pop
    //   244: invokestatic 295	tmsdk/common/TMSDKContext:start	()V
    //   247: invokestatic 300	tmsdkobf/cf:ao	()V
    //   250: return
    //   251: aload_2
    //   252: astore_0
    //   253: goto -85 -> 168
    //   256: astore_0
    //   257: ldc 2
    //   259: monitorexit
    //   260: aload_0
    //   261: athrow
    //   262: astore_0
    //   263: ldc 60
    //   265: ldc_w 302
    //   268: aload_0
    //   269: invokestatic 305	tmsdkobf/eo:a	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Throwable;)V
    //   272: goto -39 -> 233
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	275	0	paramContext	Context
    //   0	275	1	paramITMSApplicaionConfig	ITMSApplicaionConfig
    //   0	275	2	paramSystemInterfaceDelegate	SystemInterfaceDelegate
    //   160	9	3	localMap	Map
    // Exception table:
    //   from	to	target	type
    //   150	161	256	finally
    //   168	204	256	finally
    //   208	227	256	finally
    //   227	230	256	finally
    //   257	260	256	finally
    //   230	233	262	java/io/IOException
  }
  
  public static void registerNatives(int paramInt, Class<?> paramClass)
  {
    bt.K();
    paramInt = doRegisterNatives(paramInt, paramClass);
    if (paramInt != 0) {
      throw new UnsatisfiedLinkError("Failed to register " + paramClass.toString() + "(err=" + paramInt + ")");
    }
  }
  
  public static void reportChannelInfo() {}
  
  public static void setAutoConnectionSwitch(boolean paramBoolean)
  {
    bt.setAutoConnectionSwitch(paramBoolean);
    if ((sApplication != null) && (paramBoolean))
    {
      reportChannelInfo();
      cf.aq();
    }
  }
  
  public static void setIntToEnvMap(String paramString, int paramInt)
  {
    try
    {
      sEnvMap.put(paramString, String.valueOf(paramInt));
      return;
    }
    finally {}
  }
  
  public static void setStrToEnvMap(String paramString1, String paramString2)
  {
    try
    {
      sEnvMap.put(paramString1, paramString2);
      return;
    }
    finally {}
  }
  
  public static void setTMSDKExternalSharedPreferences(ISharedPreferenceFactory paramISharedPreferenceFactory)
  {
    bz.a(paramISharedPreferenceFactory);
  }
  
  public static void setTMSDKLogEnable(boolean paramBoolean)
  {
    eo.q(paramBoolean);
  }
  
  private static void start()
  {
    new dc("tms").a("reportlc", false, true);
    if (bt.I())
    {
      reportChannelInfo();
      cf.aq();
    }
  }
  
  public static abstract interface SystemInterfaceDelegate
  {
    public abstract List<PackageInfo> getInstalledPackages(int paramInt);
    
    public abstract void notify(int paramInt, Notification paramNotification);
  }
}
