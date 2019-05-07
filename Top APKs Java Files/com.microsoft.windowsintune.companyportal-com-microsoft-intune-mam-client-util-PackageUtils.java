package com.microsoft.intune.mam.client.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import com.microsoft.intune.mam.client.app.AppUtils;
import com.microsoft.intune.mam.log.MAMLogger;
import com.microsoft.intune.mam.log.MAMLoggerProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PackageUtils
{
  private static final String[] CRM_APP_NAMES = { "com.microsoft.crm.crmphone", "com.microsoft.crm.crmtablet" };
  private static final String FTEST_APP_NAME = "com.microsoft.intune.mam.functionaltestapp";
  public static final String INTUNE_MAM_MANIFEST = "intune_mam_manifest";
  private static final MAMLogger LOGGER = MAMLoggerProvider.getLogger(PackageUtils.class);
  private static final String OUTLOOK_APP_NAME = "com.microsoft.office.outlook";
  private static final Pattern PACKAGE_VERSION_PATTERN = Pattern.compile("(?:[a-z0-9]*/)?([0-9][0-9\\.]*).*");
  private static final String POWER_BI_APP_NAME = "com.microsoft.powerbim";
  private static final String[] WXP_APP_NAMES = { "com.microsoft.office.word", "com.microsoft.office.excel", "com.microsoft.office.powerpoint", "com.microsoft.office.officehub", "com.microsoft.office.onenote" };
  private static final String YAMMER_APP_NAME = "com.yammer";
  private static String mAppWrapperVersion;
  private static boolean mDidCheckAppWrapperVersion;
  
  private PackageUtils() {}
  
  public static long getAppUpdateTime(String paramString, Context paramContext)
  {
    try
    {
      long l = paramContext.getPackageManager().getPackageInfo(paramString, 0).lastUpdateTime;
      return l;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return 0L;
  }
  
  public static LVersion getAppVersion(String paramString, Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      Object localObject1 = PACKAGE_VERSION_PATTERN.matcher(paramContext.versionName);
      Object localObject2;
      if (!((Matcher)localObject1).matches())
      {
        localObject1 = LOGGER;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Unable to parse app version string: '");
        ((StringBuilder)localObject2).append(paramContext.versionName);
        ((StringBuilder)localObject2).append("' for {0}");
        ((MAMLogger)localObject1).severe(((StringBuilder)localObject2).toString(), paramString);
        return LVersion.ZERO;
      }
      paramContext = ((Matcher)localObject1).group(1);
      try
      {
        localObject1 = new LVersion(paramContext);
        return localObject1;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localObject2 = LOGGER;
        Level localLevel = Level.SEVERE;
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("Unable to build Version from: '");
        localStringBuilder2.append(paramContext);
        localStringBuilder2.append("' for {0}");
        ((MAMLogger)localObject2).log(localLevel, localStringBuilder2.toString(), localNumberFormatException, paramString);
        return LVersion.ZERO;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      StringBuilder localStringBuilder1;
      for (;;) {}
    }
    paramContext = LOGGER;
    localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("Unable to get version for app ");
    localStringBuilder1.append(paramString);
    localStringBuilder1.append(" because the package does not appear to exist");
    paramContext.warning(localStringBuilder1.toString());
    return LVersion.ZERO;
  }
  
  /* Error */
  public static String getAppWrapperVersion(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 172	com/microsoft/intune/mam/client/util/PackageUtils:mDidCheckAppWrapperVersion	Z
    //   6: ifeq +12 -> 18
    //   9: getstatic 174	com/microsoft/intune/mam/client/util/PackageUtils:mAppWrapperVersion	Ljava/lang/String;
    //   12: astore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: aload_0
    //   19: invokestatic 178	com/microsoft/intune/mam/client/util/PackageUtils:getIntuneMAMManifest	(Landroid/content/Context;)Lorg/w3c/dom/Document;
    //   22: astore_0
    //   23: aload_0
    //   24: ifnonnull +8 -> 32
    //   27: ldc 2
    //   29: monitorexit
    //   30: aconst_null
    //   31: areturn
    //   32: invokestatic 184	javax/xml/xpath/XPathFactory:newInstance	()Ljavax/xml/xpath/XPathFactory;
    //   35: invokevirtual 188	javax/xml/xpath/XPathFactory:newXPath	()Ljavax/xml/xpath/XPath;
    //   38: ldc -66
    //   40: aload_0
    //   41: invokeinterface 196 3 0
    //   46: astore_0
    //   47: aload_0
    //   48: invokevirtual 199	java/lang/String:isEmpty	()Z
    //   51: istore_1
    //   52: iload_1
    //   53: ifeq +12 -> 65
    //   56: iconst_1
    //   57: putstatic 172	com/microsoft/intune/mam/client/util/PackageUtils:mDidCheckAppWrapperVersion	Z
    //   60: ldc 2
    //   62: monitorexit
    //   63: aconst_null
    //   64: areturn
    //   65: aload_0
    //   66: putstatic 174	com/microsoft/intune/mam/client/util/PackageUtils:mAppWrapperVersion	Ljava/lang/String;
    //   69: iconst_1
    //   70: putstatic 172	com/microsoft/intune/mam/client/util/PackageUtils:mDidCheckAppWrapperVersion	Z
    //   73: ldc 2
    //   75: monitorexit
    //   76: aload_0
    //   77: areturn
    //   78: astore_0
    //   79: goto +25 -> 104
    //   82: astore_0
    //   83: getstatic 40	com/microsoft/intune/mam/client/util/PackageUtils:LOGGER	Lcom/microsoft/intune/mam/log/MAMLogger;
    //   86: getstatic 153	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   89: ldc -55
    //   91: aload_0
    //   92: invokevirtual 204	com/microsoft/intune/mam/log/MAMLogger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   95: iconst_1
    //   96: putstatic 172	com/microsoft/intune/mam/client/util/PackageUtils:mDidCheckAppWrapperVersion	Z
    //   99: ldc 2
    //   101: monitorexit
    //   102: aconst_null
    //   103: areturn
    //   104: iconst_1
    //   105: putstatic 172	com/microsoft/intune/mam/client/util/PackageUtils:mDidCheckAppWrapperVersion	Z
    //   108: aload_0
    //   109: athrow
    //   110: astore_0
    //   111: ldc 2
    //   113: monitorexit
    //   114: aload_0
    //   115: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	paramContext	Context
    //   51	2	1	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   32	52	78	finally
    //   65	69	78	finally
    //   83	95	78	finally
    //   32	52	82	javax/xml/xpath/XPathExpressionException
    //   65	69	82	javax/xml/xpath/XPathExpressionException
    //   3	13	110	finally
    //   18	23	110	finally
    //   56	60	110	finally
    //   69	73	110	finally
    //   95	99	110	finally
    //   104	110	110	finally
  }
  
  /* Error */
  private static org.w3c.dom.Document getIntuneMAMManifest(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 214	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   4: ldc 13
    //   6: ldc -40
    //   8: aload_0
    //   9: invokevirtual 219	android/content/Context:getPackageName	()Ljava/lang/String;
    //   12: invokevirtual 225	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   15: istore_1
    //   16: iload_1
    //   17: ifne +16 -> 33
    //   20: getstatic 40	com/microsoft/intune/mam/client/util/PackageUtils:LOGGER	Lcom/microsoft/intune/mam/log/MAMLogger;
    //   23: getstatic 153	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   26: ldc -29
    //   28: invokevirtual 230	com/microsoft/intune/mam/log/MAMLogger:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
    //   31: aconst_null
    //   32: areturn
    //   33: aload_0
    //   34: invokevirtual 214	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   37: iload_1
    //   38: invokevirtual 234	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   41: astore_0
    //   42: invokestatic 239	javax/xml/parsers/DocumentBuilderFactory:newInstance	()Ljavax/xml/parsers/DocumentBuilderFactory;
    //   45: invokevirtual 243	javax/xml/parsers/DocumentBuilderFactory:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   48: aload_0
    //   49: invokevirtual 249	javax/xml/parsers/DocumentBuilder:parse	(Ljava/io/InputStream;)Lorg/w3c/dom/Document;
    //   52: astore_2
    //   53: aload_0
    //   54: invokestatic 255	com/microsoft/intune/mam/client/util/IOUtils:safeCloseAndLog	(Ljava/io/Closeable;)Ljava/io/IOException;
    //   57: pop
    //   58: aload_2
    //   59: areturn
    //   60: astore_2
    //   61: goto +24 -> 85
    //   64: astore_2
    //   65: getstatic 40	com/microsoft/intune/mam/client/util/PackageUtils:LOGGER	Lcom/microsoft/intune/mam/log/MAMLogger;
    //   68: getstatic 153	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   71: ldc_w 257
    //   74: aload_2
    //   75: invokevirtual 204	com/microsoft/intune/mam/log/MAMLogger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   78: aload_0
    //   79: invokestatic 255	com/microsoft/intune/mam/client/util/IOUtils:safeCloseAndLog	(Ljava/io/Closeable;)Ljava/io/IOException;
    //   82: pop
    //   83: aconst_null
    //   84: areturn
    //   85: aload_0
    //   86: invokestatic 255	com/microsoft/intune/mam/client/util/IOUtils:safeCloseAndLog	(Ljava/io/Closeable;)Ljava/io/IOException;
    //   89: pop
    //   90: aload_2
    //   91: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	paramContext	Context
    //   15	23	1	i	int
    //   52	7	2	localDocument	org.w3c.dom.Document
    //   60	1	2	localObject	Object
    //   64	27	2	localSAXException	org.xml.sax.SAXException
    // Exception table:
    //   from	to	target	type
    //   42	53	60	finally
    //   65	78	60	finally
    //   42	53	64	org/xml/sax/SAXException
    //   42	53	64	java/io/IOException
    //   42	53	64	javax/xml/parsers/ParserConfigurationException
  }
  
  public static Collection<String> getMAMPackages(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(4).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (isMAMPackage(localPackageInfo)) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public static boolean isAppEnabled(Context paramContext, String paramString)
  {
    try
    {
      boolean bool = paramContext.getPackageManager().getApplicationInfo(paramString, 0).enabled;
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isAppWrappedPackage(Context paramContext)
  {
    return getAppWrapperVersion(paramContext) != null;
  }
  
  public static boolean isCRMPackage(Context paramContext)
  {
    paramContext = paramContext.getPackageName();
    String[] arrayOfString = CRM_APP_NAMES;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (paramContext.equals(arrayOfString[i])) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isEdgePackage(Context paramContext)
  {
    return AppUtils.isEdgePackage(paramContext);
  }
  
  public static boolean isFtestPackage(Context paramContext)
  {
    return "com.microsoft.intune.mam.functionaltestapp".equals(paramContext.getPackageName());
  }
  
  public static boolean isInstalledToSDCard(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    boolean bool = false;
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.flags;
      if ((i & 0x40000) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      LOGGER.log(Level.SEVERE, "Unable to get package info about ourself!", paramContext);
    }
    return false;
  }
  
  public static boolean isMAMPackage(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return false;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 4);
      return isMAMPackage(paramContext);
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private static boolean isMAMPackage(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {
      return false;
    }
    if (paramPackageInfo.services == null) {
      return false;
    }
    paramPackageInfo = paramPackageInfo.services;
    int j = paramPackageInfo.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramPackageInfo[i];
      if ((localObject != null) && ("com.microsoft.intune.mam.client.notification.MAMNotificationReceiverService".equals(localObject.name))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isOutlookPackage(Context paramContext)
  {
    return paramContext.getPackageName().startsWith("com.microsoft.office.outlook");
  }
  
  public static boolean isPowerBIPackage(Context paramContext)
  {
    return "com.microsoft.powerbim".equals(paramContext.getPackageName());
  }
  
  public static boolean isWXPOfficePackage(Context paramContext)
  {
    paramContext = paramContext.getPackageName();
    String[] arrayOfString = WXP_APP_NAMES;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (paramContext.equals(arrayOfString[i])) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isXamarinApp(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {
      return false;
    }
    if (paramPackageInfo.providers == null) {
      return false;
    }
    paramPackageInfo = paramPackageInfo.providers;
    int j = paramPackageInfo.length;
    int i = 0;
    while (i < j)
    {
      if ("mono.MonoRuntimeProvider".equals(paramPackageInfo[i].name)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isYammerPackage(Context paramContext)
  {
    return paramContext.getPackageName().startsWith("com.yammer");
  }
  
  static void resetDidCheckAppWrapperVersion()
  {
    mDidCheckAppWrapperVersion = false;
  }
}
