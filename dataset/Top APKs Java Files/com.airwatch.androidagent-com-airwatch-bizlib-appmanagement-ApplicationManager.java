package com.airwatch.bizlib.appmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.content.LocalBroadcastManager;
import com.airwatch.bizlib.b.d;
import com.airwatch.bizlib.provisioning.ProvisioningDownloadManager;
import com.airwatch.bizlib.provisioning.ProvisioningEnums.ResultCode;
import com.airwatch.util.n;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

public abstract class ApplicationManager
  implements d
{
  private static final HashMap b = new HashMap();
  private static final HashMap e = new HashMap();
  private com.airwatch.bizlib.d.a a;
  protected com.airwatch.bizlib.c.e c;
  protected Context d;
  
  public ApplicationManager(Context paramContext, com.airwatch.bizlib.c.e paramE)
  {
    this.d = paramContext;
    this.c = paramE;
    this.a = com.airwatch.bizlib.d.a.a(paramContext);
  }
  
  public ApplicationManager(Context paramContext, com.airwatch.bizlib.c.e paramE, ApplicationManager.DownloadAPI paramDownloadAPI)
  {
    this.d = paramContext;
    this.c = paramE;
    com.airwatch.bizlib.d.a.a();
    if (paramDownloadAPI == ApplicationManager.DownloadAPI.a) {
      this.a = com.airwatch.bizlib.d.b.b(paramContext);
    }
    while (paramDownloadAPI != ApplicationManager.DownloadAPI.b) {
      return;
    }
    this.a = com.airwatch.bizlib.d.c.b(paramContext);
  }
  
  public static void a(Context paramContext, Class paramClass)
  {
    paramClass = new Intent(paramContext, paramClass);
    paramClass.putExtra("sendappList", true);
    paramContext.startService(paramClass);
  }
  
  private void a(ApplicationInformation paramApplicationInformation, boolean paramBoolean)
  {
    n.b("ApplicationManager -> handleApkCorruption-- isCorrupted " + paramBoolean);
    paramApplicationInformation.a(ApplicationInformation.ApplicationState.h.j);
    this.c.a(paramApplicationInformation);
    this.c.c(paramApplicationInformation.e());
    paramApplicationInformation = new File(paramApplicationInformation.b());
    if (paramApplicationInformation.exists()) {
      paramApplicationInformation.delete();
    }
  }
  
  private boolean a(File paramFile)
  {
    if (!paramFile.exists()) {}
    while (this.d.getPackageManager().getPackageArchiveInfo(paramFile.getAbsolutePath(), 4096) == null) {
      return true;
    }
    return false;
  }
  
  private boolean a(String paramString1, String paramString2)
  {
    boolean bool2 = false;
    boolean bool1;
    if ((paramString2 == null) || (paramString2.length() == 0)) {
      bool1 = true;
    }
    int i;
    int j;
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (paramString1 == null);
        bool1 = bool2;
      } while (paramString1.length() == 0);
      i = m(paramString2);
      j = t(paramString1);
      if (i < 0) {
        break;
      }
      bool1 = bool2;
    } while (j <= i);
    return true;
  }
  
  /* Error */
  private boolean a(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString, com.airwatch.bizlib.b.b paramB)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 6
    //   3: aload_1
    //   4: ifnull +26 -> 30
    //   7: aload_1
    //   8: invokevirtual 151	java/lang/String:length	()I
    //   11: ifle +19 -> 30
    //   14: new 120	java/io/File
    //   17: dup
    //   18: aload_1
    //   19: invokespecial 123	java/io/File:<init>	(Ljava/lang/String;)V
    //   22: invokevirtual 127	java/io/File:exists	()Z
    //   25: ifne +5 -> 30
    //   28: iconst_1
    //   29: ireturn
    //   30: aload 4
    //   32: aload_2
    //   33: invokestatic 162	com/airwatch/bizlib/appmanagement/ApplicationInformation:a	([Ljava/lang/String;Ljava/lang/String;)Z
    //   36: istore 7
    //   38: aload_0
    //   39: aload_1
    //   40: invokevirtual 166	com/airwatch/bizlib/appmanagement/ApplicationManager:l	(Ljava/lang/String;)Ljava/lang/String;
    //   43: astore 4
    //   45: aload_0
    //   46: aload_2
    //   47: invokespecial 170	com/airwatch/bizlib/appmanagement/ApplicationManager:s	(Ljava/lang/String;)Z
    //   50: ifeq +61 -> 111
    //   53: aload_1
    //   54: ifnull +57 -> 111
    //   57: aload_1
    //   58: invokevirtual 151	java/lang/String:length	()I
    //   61: ifne +50 -> 111
    //   64: new 106	com/airwatch/bizlib/appmanagement/ApplicationInformation
    //   67: dup
    //   68: aload_0
    //   69: getfield 31	com/airwatch/bizlib/appmanagement/ApplicationManager:d	Landroid/content/Context;
    //   72: getstatic 172	com/airwatch/bizlib/appmanagement/ApplicationInformation$ApplicationState:d	Lcom/airwatch/bizlib/appmanagement/ApplicationInformation$ApplicationState;
    //   75: aload_1
    //   76: aload_2
    //   77: iload 7
    //   79: aload 4
    //   81: iconst_0
    //   82: aload_3
    //   83: invokespecial 175	com/airwatch/bizlib/appmanagement/ApplicationInformation:<init>	(Landroid/content/Context;Lcom/airwatch/bizlib/appmanagement/ApplicationInformation$ApplicationState;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZLjava/lang/String;)V
    //   86: astore_1
    //   87: iconst_1
    //   88: istore 6
    //   90: aload_0
    //   91: aload_1
    //   92: invokevirtual 177	com/airwatch/bizlib/appmanagement/ApplicationManager:b	(Lcom/airwatch/bizlib/appmanagement/ApplicationInformation;)V
    //   95: aload_0
    //   96: monitorenter
    //   97: iload 6
    //   99: ifeq +38 -> 137
    //   102: aload_0
    //   103: monitorexit
    //   104: iconst_1
    //   105: ireturn
    //   106: astore_1
    //   107: aload_0
    //   108: monitorexit
    //   109: aload_1
    //   110: athrow
    //   111: new 106	com/airwatch/bizlib/appmanagement/ApplicationInformation
    //   114: dup
    //   115: aload_0
    //   116: getfield 31	com/airwatch/bizlib/appmanagement/ApplicationManager:d	Landroid/content/Context;
    //   119: getstatic 179	com/airwatch/bizlib/appmanagement/ApplicationInformation$ApplicationState:a	Lcom/airwatch/bizlib/appmanagement/ApplicationInformation$ApplicationState;
    //   122: aload_1
    //   123: aload_2
    //   124: iload 7
    //   126: aload 4
    //   128: iconst_0
    //   129: aload_3
    //   130: invokespecial 175	com/airwatch/bizlib/appmanagement/ApplicationInformation:<init>	(Landroid/content/Context;Lcom/airwatch/bizlib/appmanagement/ApplicationInformation$ApplicationState;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZLjava/lang/String;)V
    //   133: astore_1
    //   134: goto -44 -> 90
    //   137: aload 5
    //   139: ifnull +57 -> 196
    //   142: aload 5
    //   144: invokeinterface 183 1 0
    //   149: ifeq +47 -> 196
    //   152: aload_0
    //   153: new 120	java/io/File
    //   156: dup
    //   157: aload_1
    //   158: invokevirtual 122	com/airwatch/bizlib/appmanagement/ApplicationInformation:b	()Ljava/lang/String;
    //   161: invokespecial 123	java/io/File:<init>	(Ljava/lang/String;)V
    //   164: invokespecial 185	com/airwatch/bizlib/appmanagement/ApplicationManager:a	(Ljava/io/File;)Z
    //   167: istore 7
    //   169: aload_0
    //   170: aload_1
    //   171: invokevirtual 188	com/airwatch/bizlib/appmanagement/ApplicationManager:a	(Lcom/airwatch/bizlib/appmanagement/ApplicationInformation;)Z
    //   174: ifne +22 -> 196
    //   177: aload_1
    //   178: invokevirtual 190	com/airwatch/bizlib/appmanagement/ApplicationInformation:d	()Z
    //   181: ifne +15 -> 196
    //   184: iload 7
    //   186: ifeq +10 -> 196
    //   189: aload_0
    //   190: aload_1
    //   191: iload 7
    //   193: invokespecial 192	com/airwatch/bizlib/appmanagement/ApplicationManager:a	(Lcom/airwatch/bizlib/appmanagement/ApplicationInformation;Z)V
    //   196: aload_0
    //   197: monitorexit
    //   198: iconst_1
    //   199: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	200	0	this	ApplicationManager
    //   0	200	1	paramString1	String
    //   0	200	2	paramString2	String
    //   0	200	3	paramString3	String
    //   0	200	4	paramArrayOfString	String[]
    //   0	200	5	paramB	com.airwatch.bizlib.b.b
    //   1	97	6	i	int
    //   36	156	7	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   102	104	106	finally
    //   107	109	106	finally
    //   142	184	106	finally
    //   189	196	106	finally
    //   196	198	106	finally
  }
  
  public static String[] m()
  {
    return new String[] { "com.airwatch.browser", "com.airwatch.email", "com.airwatch.contentlocker", "com.airwatch.chat", "com.airwatch.androidvideo", "com.airwatch.vpn", "com.airwatch.tunnel", "com.airwatch.lockdown.launcher" };
  }
  
  public static boolean o(String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {}
    for (;;)
    {
      return false;
      String[] arrayOfString = m();
      int i = 0;
      while (i < 8)
      {
        if (arrayOfString[i].equalsIgnoreCase(paramString)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  private boolean s(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() == 0)) {
      return false;
    }
    try
    {
      this.d.getPackageManager().getPackageInfo(paramString, 0);
      return true;
    }
    catch (Exception paramString)
    {
      n.a("Appllication is not present in device");
    }
    return false;
  }
  
  private int t(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    do
    {
      PackageManager localPackageManager;
      do
      {
        return -1;
        localPackageManager = this.d.getPackageManager();
        paramString = new File(paramString);
      } while (!paramString.exists());
      paramString = localPackageManager.getPackageArchiveInfo(paramString.getAbsolutePath(), 0);
    } while (paramString == null);
    return paramString.versionCode;
  }
  
  private String u(String paramString)
  {
    int i = 0;
    n.a("Checking to see if " + paramString + " is installed.");
    String str = "0";
    Object localObject2 = this.d.getPackageManager();
    Object localObject1 = str;
    try
    {
      Object localObject3 = ((PackageManager)localObject2).getPackageInfo(paramString, 0);
      localObject2 = str;
      if (localObject3 != null)
      {
        localObject2 = str;
        localObject1 = str;
        if (((PackageInfo)localObject3).versionName != null)
        {
          localObject1 = str;
          localObject2 = new StringBuilder();
          localObject1 = str;
          localObject3 = ((PackageInfo)localObject3).versionName.split("\\.");
          localObject1 = str;
          int j = localObject3.length;
          while (i < j)
          {
            localObject1 = str;
            ((StringBuilder)localObject2).append(localObject3[i]);
            i += 1;
          }
          localObject1 = str;
          localObject2 = ((StringBuilder)localObject2).toString();
          localObject1 = localObject2;
          n.a(paramString + " " + (String)localObject2 + " is installed.");
        }
      }
      return localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      n.a(paramString + " not installed.");
    }
    return localObject1;
  }
  
  public final ProvisioningEnums.ResultCode a(Context paramContext, com.airwatch.bizlib.b.c paramC, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, Vector paramVector, String paramString4, boolean paramBoolean3, com.airwatch.bizlib.b.g paramG, boolean paramBoolean4, int paramInt, long paramLong)
  {
    String str1 = paramContext.getFilesDir().toString() + "/" + paramString2 + "_" + paramString3 + ".apk";
    String str2 = paramContext.getFilesDir().toString() + "/cache/" + paramString2 + "_" + paramString3 + ".apk";
    ProvisioningEnums.ResultCode localResultCode = ProvisioningEnums.ResultCode.a;
    paramC = ProvisioningDownloadManager.a(paramContext, paramC, paramG);
    if (paramBoolean1)
    {
      paramContext = new File(str2);
      if ((paramContext.exists()) && (!paramBoolean4)) {
        paramContext.delete();
      }
      return paramC.a(paramString1, str2, paramVector, paramString4, paramInt, paramLong);
    }
    paramG = new File(str1);
    if ((paramG.exists()) && (!paramBoolean4)) {
      paramG.delete();
    }
    if (paramBoolean2)
    {
      if (!new File(str2).renameTo(paramG)) {
        break label381;
      }
      paramC = ProvisioningEnums.ResultCode.b;
    }
    for (;;)
    {
      paramString1 = new File(str1);
      paramString1.setReadable(true, false);
      paramString1.setWritable(true, false);
      paramString1.setExecutable(true, false);
      if (paramC == ProvisioningEnums.ResultCode.b)
      {
        paramC = ProvisioningEnums.ResultCode.y;
        paramString1 = new ApplicationInformation(paramContext, ApplicationInformation.ApplicationState.a, str1, paramString2, false, paramString3, paramBoolean3, null);
        paramString1.l();
        paramBoolean1 = a(paramString1);
        if (paramBoolean1) {}
        for (paramContext = "success";; paramContext = "failure")
        {
          n.a(String.format("Application: %s, installation result: %s", new Object[] { paramString2, paramContext }));
          if (!paramBoolean1) {
            break label379;
          }
          paramString1.a(4);
          this.c.a(paramString1);
          return ProvisioningEnums.ResultCode.b;
          paramC = paramC.a(paramString1, str1, paramVector, paramString4, paramInt, paramLong);
          break;
        }
      }
      paramG.delete();
      return paramC;
      label379:
      return paramC;
      label381:
      paramC = localResultCode;
    }
  }
  
  public final String a(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString1.length() == 0))
    {
      n.d("Download failed!  Download URL is missing.");
      return "";
    }
    if ((paramString2 == null) || (paramString2.length() == 0))
    {
      n.d("Download failed!  Package identifier is missing.");
      return "";
    }
    if ((paramString3 == null) || (paramString3.length() == 0))
    {
      n.d("Download failed!  User agent is missing.");
      return "";
    }
    paramString1 = new h(paramString1, paramString2);
    n.a("Downloading " + paramString2 + "...");
    if (paramString1.a(this.d, paramString3))
    {
      if (paramString1.b() != 200) {
        return "";
      }
      if (a(paramString1.a(), paramString2))
      {
        n.a("Download complete. Preparing to install.");
        return paramString1.a();
      }
      n.a("Package already installed.  Removing apk.");
      paramString1 = new File(paramString1.a());
      if (paramString1.exists()) {
        paramString1.delete();
      }
      return "skip";
    }
    n.d("Download " + paramString2 + " failed!");
    return "";
  }
  
  public final String a(String[] paramArrayOfString, String paramString, com.airwatch.bizlib.b.b paramB, Intent paramIntent)
  {
    long l = paramIntent.getLongExtra("extra_download_id", -1L);
    if (l == -1L) {}
    do
    {
      do
      {
        return "";
        paramIntent = this.c.a("apk_app_download_id", String.valueOf(l), "=");
        if (paramIntent.size() > 0)
        {
          String str = ((g)paramIntent.get(0)).b;
          this.a.a(new a(this, (g)paramIntent.get(0), paramArrayOfString, paramString, paramB));
          return str;
        }
        n.a("DM: processDownloadManagerIntent download id is not present in DB, so deleting temp file ");
        paramArrayOfString = this.a.a(l);
      } while (paramArrayOfString == null);
      paramArrayOfString = new File(paramArrayOfString);
    } while (!paramArrayOfString.exists());
    paramArrayOfString.delete();
    return "";
  }
  
  public final void a(com.airwatch.bizlib.b.b paramB)
  {
    for (;;)
    {
      Object localObject;
      File localFile;
      try
      {
        n.f("ApplicationManager.installPendingApplications");
        localObject = this.c.a();
        if ((localObject == null) || (paramB == null) || (!paramB.b())) {
          break;
        }
        paramB = ((List)localObject).iterator();
        if (!paramB.hasNext()) {
          break;
        }
        localObject = (ApplicationInformation)paramB.next();
        int i = ((ApplicationInformation)localObject).f();
        String str1 = ((ApplicationInformation)localObject).e();
        if (i == ApplicationInformation.ApplicationState.d.j) {
          continue;
        }
        if ((i == ApplicationInformation.ApplicationState.a.j) || ((i == ApplicationInformation.ApplicationState.b.j) && (!s(str1))))
        {
          String str2 = ((ApplicationInformation)localObject).b();
          if ((str2 == null) || (str2.length() == 0)) {
            continue;
          }
          localFile = new File(str2);
          if (!localFile.exists()) {
            continue;
          }
          if (!a(str2, str1)) {
            break label352;
          }
          bool1 = a(localFile);
          boolean bool2 = a((ApplicationInformation)localObject);
          n.a(String.format("Application: %s, installation result: %s", new Object[] { ((ApplicationInformation)localObject).e(), Boolean.valueOf(bool2) }));
          if (bool2)
          {
            ((ApplicationInformation)localObject).a(ApplicationInformation.ApplicationState.b.j);
            this.c.a((ApplicationInformation)localObject);
          }
        }
        else
        {
          if ((!s(str1)) || (a(((ApplicationInformation)localObject).b(), str1))) {
            break label366;
          }
          bool1 = true;
          n.a("ApplicationManager: isInstalledOrUpgraded returning   : " + bool1);
          if (bool1)
          {
            n.a(String.format("Updating Application as installed: %s in Db", new Object[] { ((ApplicationInformation)localObject).e() }));
            ((ApplicationInformation)localObject).a(ApplicationInformation.ApplicationState.d.j);
            this.c.a((ApplicationInformation)localObject);
          }
          n.g("ApplicationManager.installPendingApplications");
          continue;
        }
        if (!bool1) {
          continue;
        }
      }
      finally {}
      a((ApplicationInformation)localObject, bool1);
      continue;
      try
      {
        label352:
        localFile.delete();
      }
      catch (Exception localException) {}
      continue;
      label366:
      boolean bool1 = false;
    }
  }
  
  public void a(com.airwatch.bizlib.b.f paramF, Class paramClass, String paramString1, String paramString2)
  {
    com.airwatch.core.f.a(paramString2);
    com.airwatch.core.f.a(paramString1);
    n.f("AgentApplicationManager updateInstallState");
    Iterator localIterator = this.c.a().iterator();
    ApplicationInformation localApplicationInformation;
    while (localIterator.hasNext())
    {
      localApplicationInformation = (ApplicationInformation)localIterator.next();
      if (paramString2.toLowerCase().equals(localApplicationInformation.e().toLowerCase()))
      {
        n.a(String.format("AgentApplicationManager Package to update %s", new Object[] { localApplicationInformation.e() }));
        if ((!paramString1.equals("android.intent.action.PACKAGE_ADDED")) && (!paramString1.equals("android.intent.action.PACKAGE_REPLACED"))) {
          break label171;
        }
        paramF = new File(localApplicationInformation.b());
        if (paramF.exists()) {
          paramF.delete();
        }
        localApplicationInformation.a(ApplicationInformation.ApplicationState.d);
        b(localApplicationInformation);
        this.c.c(localApplicationInformation.e());
      }
    }
    for (;;)
    {
      a(this.d, paramClass);
      return;
      label171:
      if (paramString1.equals("android.intent.action.PACKAGE_REMOVED"))
      {
        localApplicationInformation.a(ApplicationInformation.ApplicationState.c);
        b(localApplicationInformation);
      }
      else
      {
        if (paramString1.equals("Install Cancelled"))
        {
          if (localApplicationInformation.d()) {
            localApplicationInformation.a(ApplicationInformation.ApplicationState.e);
          }
          for (;;)
          {
            b(localApplicationInformation);
            break;
            localApplicationInformation.a(ApplicationInformation.ApplicationState.a);
          }
        }
        if (paramString1.equals("Uninstall Cancelled")) {
          paramF.b(localApplicationInformation.c(), localApplicationInformation.e());
        }
      }
    }
  }
  
  public abstract void a(String[] paramArrayOfString);
  
  public final void a(String[] paramArrayOfString, String paramString, com.airwatch.bizlib.b.b paramB)
  {
    Iterator localIterator = this.c.a("download_status", String.valueOf(ApplicationInformation.ApplicationState.a.j), "<>").iterator();
    while (localIterator.hasNext())
    {
      g localG = (g)localIterator.next();
      if (!localG.a.equals(ApplicationInformation.ApplicationState.g))
      {
        localG.a = ApplicationInformation.ApplicationState.g;
        this.c.a(localG);
      }
      n.a("Apk: adding DB pending  Applicaitons :" + localG);
      this.a.b(this.c, new a(this, localG, paramArrayOfString, paramString, paramB));
    }
  }
  
  public abstract boolean a(ApplicationInformation paramApplicationInformation);
  
  protected final boolean a(ApplicationInformation paramApplicationInformation, com.airwatch.bizlib.b.f paramF)
  {
    com.airwatch.core.f.a(paramApplicationInformation);
    if ((paramApplicationInformation.d()) && (f())) {
      paramF.a(paramApplicationInformation.c(), paramApplicationInformation.e());
    }
    for (;;)
    {
      return true;
      n.d("Public application install failed. This device does not support Google Mobile Services");
      paramApplicationInformation.a(ApplicationInformation.ApplicationState.c);
      b(paramApplicationInformation);
    }
  }
  
  public final boolean a(a paramA)
  {
    g localG = paramA.b();
    if ((localG.e == null) || (localG.e.length() == 0))
    {
      n.a("DM: Application download failed. resetting db entry");
      localG.a = ApplicationInformation.ApplicationState.h;
      localG.g = -1L;
      this.c.a(localG);
      return false;
    }
    if (a(paramA.b().e, paramA.b().b))
    {
      n.a("DM: Application Download complete. Preparing to install.");
      if (a(new File(paramA.b().e)))
      {
        a(new ApplicationInformation(this.d, ApplicationInformation.ApplicationState.a, paramA.b().e, localG.b, false, u(localG.b)), true);
        return false;
      }
      return true;
    }
    n.a("DM: Application  already installed.  Removing apk.");
    paramA = new File(paramA.b().e);
    if (paramA.exists()) {
      paramA.delete();
    }
    this.c.c(localG.b);
    paramA = new ApplicationInformation(this.d, ApplicationInformation.ApplicationState.d, "", localG.b, false, u(localG.b));
    paramA.l();
    this.c.a(paramA);
    return false;
  }
  
  public final boolean a(a paramA, boolean paramBoolean)
  {
    if ((paramA == null) || (!paramBoolean)) {}
    g localG;
    do
    {
      return false;
      localG = paramA.b();
    } while ((localG.e == null) || (localG.e.length() <= 0));
    localG.a = ApplicationInformation.ApplicationState.a;
    this.c.a(localG);
    LocalBroadcastManager.getInstance(this.d).sendBroadcast(new Intent("com.airwatch.agent.action.APK_DOWNLOAD_DONE"));
    String str = (String)b.get(localG.b);
    return a(localG.e, localG.b, str, paramA.c(), paramA.e());
  }
  
  public abstract boolean a(String paramString);
  
  public final boolean a(String paramString1, String paramString2, Context paramContext)
  {
    Object localObject1 = this.c.a(paramString1);
    if ((localObject1 != null) && (((ApplicationInformation)localObject1).m().equalsIgnoreCase(paramString2)) && (((ApplicationInformation)localObject1).a() == ApplicationInformation.ApplicationState.d)) {}
    for (;;)
    {
      Object localObject2;
      int j;
      int i;
      int i2;
      int k;
      int m;
      int i3;
      try
      {
        paramContext = paramContext.getPackageManager();
        localObject1 = paramContext.getInstalledApplications(128).iterator();
        if (!((Iterator)localObject1).hasNext()) {
          break label310;
        }
        localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
        if (!((ApplicationInfo)localObject2).packageName.equals(paramString1)) {
          continue;
        }
        paramContext = paramContext.getPackageInfo(((ApplicationInfo)localObject2).packageName, 0).versionName;
        paramString1 = new StringBuilder();
        paramContext = paramContext.split("\\.");
        int i1 = paramContext.length;
        j = 0;
        i = 0;
        if (j >= i1) {
          break label254;
        }
        localObject2 = paramContext[j];
        i += 1;
        localObject1 = new StringBuilder();
        localObject2 = ((String)localObject2).getBytes();
        i2 = localObject2.length;
        k = 0;
        m = 0;
      }
      catch (Exception paramString1)
      {
        label198:
        n.d("Exception occurred while verifying applications with package manager", paramString1);
        return false;
      }
      ((StringBuilder)localObject1).append((char)i3);
      int n = k + 1;
      break label361;
      if (paramString1.length() != 0) {
        paramString1.append(".");
      }
      if (((StringBuilder)localObject1).length() != 0)
      {
        paramString1.append(((StringBuilder)localObject1).toString());
      }
      else
      {
        paramString1.append("0");
        break label374;
        label254:
        k = i;
      }
      label310:
      label361:
      label372:
      label374:
      do
      {
        if (k < 3)
        {
          i = 0;
          while (i < 3 - k)
          {
            paramString1.append(".0");
            i += 1;
          }
        }
        boolean bool = paramString1.toString().equals(paramString2);
        return bool;
        return false;
        for (;;)
        {
          if (m >= i2) {
            break label372;
          }
          i3 = localObject2[m];
          if ((i3 < 48) || (i3 > 57)) {
            break label198;
          }
          if (k != 0) {
            break;
          }
          n = k;
          if (i3 >= 49) {
            break;
          }
          m += 1;
          k = n;
        }
        break label198;
        k = i;
      } while (i == 3);
      j += 1;
    }
  }
  
  public final boolean a(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString, String paramString4, boolean paramBoolean, com.airwatch.bizlib.b.b paramB, int paramInt)
  {
    for (;;)
    {
      Object localObject2;
      try
      {
        com.airwatch.core.f.a(paramString2);
        localObject2 = this.d;
        localObject2 = com.airwatch.c.a.a(com.airwatch.core.a.f, (Context)localObject2);
        if ((localObject2 != null) && ((localObject2 instanceof Map)))
        {
          localObject2 = ((Map)localObject2).entrySet().iterator();
          if (((Iterator)localObject2).hasNext())
          {
            if (!((String)((Map.Entry)((Iterator)localObject2).next()).getValue()).trim().equalsIgnoreCase(paramString2.trim())) {
              continue;
            }
            n.b("Package " + paramString2 + "is blacklisted");
            i = 1;
            if (i == 0) {
              continue;
            }
            paramBoolean = true;
            return paramBoolean;
          }
        }
        int i = 0;
        continue;
        if ((paramString1.length() == 0) && (paramString2.length() > 0))
        {
          paramBoolean = a(paramString1, paramString2, paramString3, paramArrayOfString, paramB);
          continue;
        }
        if (paramBoolean)
        {
          paramString1 = new g(ApplicationInformation.ApplicationState.g.j, paramString2, paramString1, System.currentTimeMillis(), "");
          if (paramString2 != null)
          {
            if (paramString2.length() != 0) {
              break label601;
            }
            break label587;
            label211:
            localObject2 = this.c.a(paramString2);
            if (!s(paramString2)) {
              break label619;
            }
            if (paramInt > m(paramString2))
            {
              paramInt = 1;
              break label590;
            }
            if (localObject2 != null) {
              break label613;
            }
            localObject2 = new ApplicationInformation(this.d, ApplicationInformation.ApplicationState.d, "", paramString2, false, u(paramString2));
            ((ApplicationInformation)localObject2).l();
            this.c.a((ApplicationInformation)localObject2);
            break label613;
            label292:
            localObject2 = ((ApplicationInformation)localObject2).b();
            if ((localObject2 == null) || (((String)localObject2).length() == 0) || (!new File((String)localObject2).exists())) {
              break label630;
            }
            i = t((String)localObject2);
            if (i == -1) {
              break label636;
            }
            if (paramInt <= i) {
              break label642;
            }
            break label636;
            if (paramString2 == null) {
              break label648;
            }
            if (paramString2.length() == 0)
            {
              break label648;
              Object localObject1;
              paramString1.g = localObject1;
              a(paramArrayOfString, paramString4, paramB);
              this.c.a(paramString1);
              n.a("Apk: Processing Applicaiton :" + paramString2);
              b.put(paramString2, paramString3);
              paramBoolean = this.a.a(this.c, new a(this, paramString1, paramArrayOfString, paramString4, paramB));
              continue;
            }
            localObject2 = this.c.a("apk_package_name", String.valueOf(paramString2), "=");
            if (((List)localObject2).size() <= 0) {
              break label656;
            }
            localObject2 = (g)((List)localObject2).get(0);
            if (ApplicationInformation.ApplicationState.a.equals(((g)localObject2).a)) {
              break label656;
            }
            l = ((g)localObject2).g;
            continue;
          }
        }
        else
        {
          paramString1 = a(paramString1, paramString2, paramString4);
          if (paramString1.equals("skip"))
          {
            paramBoolean = true;
            continue;
          }
          if ((paramString1 != null) && (paramString1.length() > 0))
          {
            paramBoolean = a(paramString1, paramString2, paramString3, paramArrayOfString, paramB);
            continue;
          }
          n.a("Console version is less than 6.5");
          paramBoolean = false;
          continue;
        }
        paramInt = 0;
      }
      finally {}
      for (;;)
      {
        label587:
        label590:
        if (paramInt != 0) {
          break label646;
        }
        paramBoolean = true;
        break;
        label601:
        if (paramInt != -1) {
          break label211;
        }
        paramInt = 1;
        continue;
        label613:
        paramInt = 0;
        continue;
        label619:
        if (localObject2 != null) {
          break label292;
        }
        paramInt = 1;
        continue;
        label630:
        paramInt = 1;
        continue;
        label636:
        paramInt = 1;
        continue;
        label642:
        paramInt = 0;
      }
      label646:
      continue;
      label648:
      long l = -1L;
      continue;
      label656:
      l = -1L;
    }
  }
  
  public abstract boolean a(List paramList, boolean paramBoolean);
  
  public boolean a_(String paramString)
  {
    return false;
  }
  
  public final long b(String paramString1, String paramString2)
  {
    return this.c.d(paramString1, paramString2);
  }
  
  protected final void b(ApplicationInformation paramApplicationInformation)
  {
    try
    {
      n.f("AppManager PersistAppData for generic android - entry");
      this.c.a(paramApplicationInformation);
      n.f("AppManager PersistAppData for generic android - exit");
      return;
    }
    finally
    {
      paramApplicationInformation = finally;
      throw paramApplicationInformation;
    }
  }
  
  public boolean b()
  {
    return false;
  }
  
  public boolean b(String paramString)
  {
    return true;
  }
  
  public abstract void c();
  
  public abstract boolean c(String paramString);
  
  public abstract void d();
  
  public boolean d(String paramString)
  {
    return true;
  }
  
  public abstract void e();
  
  protected boolean f()
  {
    return com.google.android.gms.common.e.a(this.d) != 1;
  }
  
  public final boolean f(String paramString)
  {
    return s(paramString);
  }
  
  public void g()
  {
    n.f("ApplicationManager removeAllApp");
    Object localObject = this.c.a();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        a(((ApplicationInformation)((Iterator)localObject).next()).e());
      }
    }
    this.c.b();
  }
  
  public final boolean g(String paramString)
  {
    n.f("Compare packageNAme with Managed Apps list");
    Object localObject = this.c.a();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        if (((ApplicationInformation)((Iterator)localObject).next()).e().equals(paramString)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public final void h(String paramString)
  {
    this.c.b(paramString);
  }
  
  public boolean h()
  {
    return true;
  }
  
  public final void i(String paramString)
  {
    this.c.c(paramString);
  }
  
  public final ApplicationInformation j(String paramString)
  {
    n.f("ApplicationManager queryAppData");
    return this.c.a(paramString);
  }
  
  public final List j()
  {
    return this.c.a();
  }
  
  protected final String k(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0) || (!new File(paramString).exists())) {
      return "App";
    }
    try
    {
      PackageManager localPackageManager = this.d.getPackageManager();
      PackageInfo localPackageInfo = localPackageManager.getPackageArchiveInfo(paramString, 0);
      localPackageInfo.applicationInfo.sourceDir = paramString;
      localPackageInfo.applicationInfo.publicSourceDir = paramString;
      paramString = (String)localPackageInfo.applicationInfo.loadLabel(localPackageManager);
      return paramString;
    }
    catch (Exception paramString)
    {
      n.d("Unable to extract app name from apk");
    }
    return "App";
  }
  
  public final List k()
  {
    List localList = this.c.a();
    Object localObject1 = this.c;
    n.f("ApplicationDbAdapter.getAppListFromdb");
    Object localObject2 = ((com.airwatch.bizlib.c.e)localObject1).a(null, null, null);
    localObject1 = new ArrayList();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      g localG = (g)((Iterator)localObject2).next();
      ((List)localObject1).add(new ApplicationInformation("", localG.b, localG.a.j, localG.f, false, false, "", "", null));
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (ApplicationInformation)((Iterator)localObject1).next();
      if (!localList.contains(localObject2))
      {
        localList.add(localObject2);
      }
      else if (((ApplicationInformation)localObject2).a().equals(ApplicationInformation.ApplicationState.g))
      {
        localList.remove(localObject2);
        localList.add(localObject2);
      }
    }
    return localList;
  }
  
  public final String l(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    do
    {
      PackageManager localPackageManager;
      do
      {
        return "";
        localPackageManager = this.d.getPackageManager();
        paramString = new File(paramString);
      } while (!paramString.exists());
      paramString = localPackageManager.getPackageArchiveInfo(paramString.getAbsolutePath(), 0);
    } while (paramString == null);
    return paramString.versionName;
  }
  
  public final List l()
  {
    com.airwatch.bizlib.c.e localE = this.c;
    n.f("ApplicationDbAdapter.getInstalledAppList");
    return localE.a("appstate", "4");
  }
  
  public final int m(String paramString)
  {
    int i = -1;
    Object localObject = this.d.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(paramString, 0);
      if (localObject != null) {
        i = ((PackageInfo)localObject).versionCode;
      }
      return i;
    }
    catch (Exception localException)
    {
      n.a(paramString + " not installed.");
    }
    return -1;
  }
  
  public final com.airwatch.bizlib.c.e n()
  {
    return this.c;
  }
  
  public final boolean n(String paramString)
  {
    return (this.c.a(paramString) != null) || (!this.c.a("apk_package_name", paramString, "=").isEmpty());
  }
  
  public final void o()
  {
    this.a.b();
  }
  
  public final int p(String paramString)
  {
    return this.c.e("packagename", paramString);
  }
  
  public final List q(String paramString)
  {
    return this.c.b("appvpnuuid", "packagename", paramString);
  }
  
  public final List r(String paramString)
  {
    return this.c.b("packagename", "appvpnuuid", paramString);
  }
}
