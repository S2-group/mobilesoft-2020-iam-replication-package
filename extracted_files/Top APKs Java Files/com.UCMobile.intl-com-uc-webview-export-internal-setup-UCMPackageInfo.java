package com.uc.webview.export.internal.setup;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Pair;
import com.uc.webview.export.annotations.Api;
import com.uc.webview.export.cyclone.UCCyclone;
import com.uc.webview.export.cyclone.UCCyclone.MessageDigestType;
import com.uc.webview.export.cyclone.UCElapseTime;
import com.uc.webview.export.cyclone.UCKnownException;
import com.uc.webview.export.internal.SDKFactory;
import com.uc.webview.export.internal.interfaces.IWaStat.WaStat;
import com.uc.webview.export.internal.utility.j;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Api
public class UCMPackageInfo
{
  public static final String[] ARCHS = { "armeabi-v7a", "armeabi", "x86" };
  public static final String BROWSER_IF_DEX_FILE_USING_SO_SUFFIX = "libbrowser_if_jar_kj_uc.so";
  public static final String BROWSER_IF_FOR_EXPORT_FILE_USING_SO_SUFFIX = "libbrowser_if_for_export_jar_kj_uc.so";
  public static final String CORE_CLASS_LOADER_IMPL_CLASS = "com.uc.webkit.sdk.CoreClassPreLoaderImpl";
  public static final String CORE_FACTORY_IMPL_CLASS = "com.uc.webkit.sdk.CoreFactoryImpl";
  public static final String CORE_IMPL_DEX_FILE_USING_SO_SUFFIX = "libcore_jar_kj_uc.so";
  public static final String RES_PAKS_DIR_NAME = "paks";
  public static final String SDK_SHELL_DEX_FILE_USING_SO_SUFFIX = "libsdk_shell_jar_kj_uc.so";
  private static final String[] a = { "webviewuc" };
  private static final String[] b = { "imagehelper" };
  public static final int compareVersion = 10027;
  public static final int deleteTempDecFiles = 10039;
  public static final int deleteUCMSDKDir = 10044;
  public static final int expectCreateDirFile2P = 10035;
  public static final int expectDirFile1F = 10032;
  public static final int expectDirFile1S = 10033;
  public static final int getDataDirHash = 10012;
  public static final int getDecompressRoot = 10003;
  public static final int getDir = 10001;
  public static final int getFlagRoot = 10005;
  public static final int getKernalJarCpyRoot = 10009;
  public static final int getKernalJarLnkRoot = 10007;
  public static final int getKernalResCpyRoot = 10010;
  public static final int getKernalResLnkRoot = 10008;
  public static final int getKernalShareJarCpyRoot = 10047;
  public static final int getKernalShareJarLnkRoot = 10046;
  public static final int getKernelFileIfMultiCoreFromDir = 10028;
  public static final int getKernelFiles = 10022;
  public static final int getKernelResFiles = 10024;
  public static final int getLibFilter = 10023;
  public static final int getOdexRoot = 10004;
  public static final int getRepairApolloRoot = 10045;
  public static final int getRepairRoot = 10006;
  public static final int getUnExistsFilePath = 10021;
  public static final int getUpdateRoot = 10002;
  public static final int getVersion = 10040;
  public static final int hadInstallUCMobile = 10026;
  public static final int initUCMBuildInfo = 10041;
  public static final int isDirShouldBeDeleted = 10043;
  public static final int isThickSDK = 10011;
  public static final int makeDirDeleteFlg = 10042;
  public static final int sortByLastModified = 10025;
  public final Pair<String, String> browserIFModule;
  private Context c;
  public final String coreCode;
  public final Pair<String, String> coreImplModule;
  public final String dataDir;
  public final String disabledFilePath;
  public final boolean isSpecified;
  public ClassLoader mCoreClassLoader = null;
  public ClassLoader mSdkShellClassLoader = null;
  public final String mainLibrary;
  public final String pkgName;
  public final String resDirPath;
  public final Pair<String, String> sdkShellModule;
  public final String soDirPath;
  
  public UCMPackageInfo(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean1, String paramString5, String paramString6, String paramString7, String paramString8, boolean paramBoolean2)
  {
    String str = a(paramString2);
    paramString2 = a(paramString3);
    paramString3 = a(paramString4);
    paramString5 = a(paramString5);
    paramString6 = a(paramString6);
    paramString4 = a(paramString7);
    paramString7 = a(paramString8);
    this.c = paramContext.getApplicationContext();
    this.pkgName = paramString1;
    this.soDirPath = str;
    paramString1 = paramString2;
    if (paramBoolean2) {
      paramString1 = a(paramContext, paramString3, paramString2);
    }
    if (paramString1 == null)
    {
      paramString2 = null;
      this.resDirPath = paramString2;
      this.isSpecified = paramBoolean1;
      if (paramString3 == null) {
        break label542;
      }
      if (paramString7 != null) {
        break label511;
      }
      paramString1 = (File)invoke(10004, new Object[] { paramContext });
      label140:
      if ((paramContext == null) || (j.a(paramString3)) || (paramString3.indexOf(paramContext.getPackageName()) <= 0)) {
        break label524;
      }
      paramString2 = paramString3.substring(paramString3.indexOf(paramContext.getPackageName()), paramString3.length());
      label184:
      paramString2 = (File)invoke(10035, new Object[] { paramString1, (String)invoke(10012, new Object[] { paramString2 }) });
      this.dataDir = paramString3;
      this.disabledFilePath = (paramString3 + "/e1df430e25e9dacb26ead508abb3413f");
      paramString1 = paramString5;
      if (paramBoolean2) {
        paramString1 = b(paramContext, paramString3, paramString5);
      }
      this.sdkShellModule = new Pair(paramString1, paramString2.getAbsolutePath());
      if (!paramBoolean2) {
        break label530;
      }
      paramString1 = b(paramContext, paramString3, paramString6);
      label299:
      this.browserIFModule = new Pair(paramString1, paramString2.getAbsolutePath());
      if (!paramBoolean2) {
        break label536;
      }
      paramString1 = b(paramContext, paramString3, paramString4);
      label329:
      this.coreImplModule = new Pair(paramString1, paramString2.getAbsolutePath());
      label345:
      if (!j.a(str)) {
        break label582;
      }
    }
    int j;
    int i;
    label511:
    label524:
    label530:
    label536:
    label542:
    label582:
    for (paramString2 = a(paramContext.getApplicationInfo().nativeLibraryDir);; paramString2 = str)
    {
      paramString5 = a;
      j = paramString5.length;
      i = 0;
      for (paramString1 = null; i < j; paramString1 = paramString3)
      {
        paramString4 = paramString5[i];
        paramString6 = new File(paramString2, "lib" + paramString4 + ".so");
        paramString3 = paramString1;
        if (paramString6.exists()) {
          if (paramString6.lastModified() > 0L)
          {
            paramString3 = paramString1;
            if (paramString6.lastModified() <= 0L) {}
          }
          else
          {
            paramString3 = paramString4;
          }
        }
        i += 1;
      }
      paramString2 = paramString1;
      if (paramString1.endsWith("/")) {
        break;
      }
      paramString2 = paramString1 + "/";
      break;
      paramString1 = new File(paramString7);
      break label140;
      paramString2 = paramString3;
      break label184;
      paramString1 = paramString6;
      break label299;
      paramString1 = paramString4;
      break label329;
      this.dataDir = null;
      this.disabledFilePath = ((String)invoke(10021, new Object[0]));
      this.sdkShellModule = null;
      this.browserIFModule = null;
      this.coreImplModule = null;
      break label345;
    }
    if ((paramString1 == null) && (!j.a(str))) {
      throw new UCSetupException(3001, String.format("Main so file U4 [%s] not exists.", new Object[] { "webviewuc" }));
    }
    this.mainLibrary = paramString1;
    if ("webviewuc".equals(this.mainLibrary)) {
      paramString1 = "u4";
    }
    for (;;)
    {
      this.coreCode = paramString1;
      if (!this.coreCode.equals("u4")) {
        break;
      }
      paramString1 = b;
      j = paramString1.length;
      i = 0;
      while (i < j)
      {
        paramString4 = paramString1[i];
        paramString3 = new File(paramContext.getApplicationInfo().nativeLibraryDir, "lib" + paramString4 + ".so");
        paramString4 = new File(paramString2, "lib" + paramString4 + ".so");
        if (paramString3.exists())
        {
          long l = paramString3.lastModified();
          if ((!paramString4.exists()) || (paramString4.lastModified() < l)) {
            j.a(paramString3, paramString4, paramString4, false);
          }
        }
        i += 1;
      }
      if (this.mainLibrary == null) {
        paramString1 = "null";
      } else {
        paramString1 = "error";
      }
    }
  }
  
  private static UCMPackageInfo a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    boolean bool1 = j.a(paramString2);
    boolean bool2 = j.a(paramString3);
    boolean bool3 = j.a(paramString4);
    if ((bool1) && (!((Boolean)invoke(10011, new Object[0])).booleanValue()))
    {
      if ((bool2) && (bool3)) {
        return null;
      }
      throw new UCSetupException(3002, "No ucm dex file specified.");
    }
    Object localObject2;
    String str2;
    if (!bool1)
    {
      localObject2 = (File)invoke(10033, new Object[] { paramString2 });
      str2 = UCCyclone.expectFile((File)localObject2, "core.jar").getAbsolutePath();
    }
    label192:
    for (;;)
    {
      try
      {
        str1 = UCCyclone.expectFile((File)localObject2, "sdk_shell.jar").getAbsolutePath();
      }
      catch (Throwable localThrowable1)
      {
        try
        {
          String str1;
          localObject2 = UCCyclone.expectFile((File)localObject2, "browser_if.jar").getAbsolutePath();
          if (paramString1 != null) {
            break label192;
          }
          paramString1 = "specified";
          return new com.uc.webview.export.internal.utility.UCMPackageInfo(paramContext, paramString1, paramString3, paramString4, paramString2, true, str1, (String)localObject2, str2, paramString5, false);
          localThrowable1 = localThrowable1;
          localObject1 = null;
          continue;
        }
        catch (Throwable localThrowable2)
        {
          localObject3 = null;
          continue;
        }
      }
      Object localObject1 = null;
      Object localObject3 = null;
      str2 = null;
    }
  }
  
  private static String a(Context paramContext, String paramString1, String paramString2)
  {
    if (j.a(paramString2)) {
      return null;
    }
    File localFile3 = (File)invoke(10033, new Object[] { paramString2 });
    String[] arrayOfString = (String[])invoke(10024, new Object[] { localFile3 });
    if ((arrayOfString == null) || (arrayOfString.length == 0)) {
      return null;
    }
    paramString2 = (String)invoke(10012, new Object[] { paramString1 });
    paramString1 = (File)invoke(10035, new Object[] { (File)invoke(10008, new Object[] { paramContext }), paramString2 });
    paramString2 = (File)invoke(10035, new Object[] { (File)invoke(10010, new Object[] { paramContext }), paramString2 });
    File localFile1 = (File)invoke(10035, new Object[] { paramString1, "paks" });
    File localFile2 = (File)invoke(10035, new Object[] { paramString2, "paks" });
    File[] arrayOfFile2 = new File[arrayOfString.length];
    File[] arrayOfFile1 = new File[arrayOfString.length];
    File[] arrayOfFile3 = new File[arrayOfString.length];
    UCElapseTime localUCElapseTime = new UCElapseTime();
    int i = 0;
    if (i < arrayOfString.length)
    {
      paramContext = arrayOfString[i];
      boolean bool = paramContext.endsWith("_pak_kr_uc.so");
      String str = paramContext.substring(3, paramContext.length() - 9);
      int j = str.lastIndexOf('_');
      str = str.substring(0, j) + '.' + str.substring(j + 1);
      File localFile4 = new File(localFile3, paramContext);
      label342:
      File localFile5;
      if (bool)
      {
        paramContext = localFile1;
        localFile5 = new File(paramContext, str);
        if (!bool) {
          break label402;
        }
      }
      label402:
      for (paramContext = localFile2;; paramContext = paramString2)
      {
        paramContext = new File(paramContext, str);
        arrayOfFile2[i] = localFile4;
        arrayOfFile1[i] = localFile5;
        arrayOfFile3[i] = paramContext;
        i += 1;
        break;
        paramContext = paramString1;
        break label342;
      }
    }
    paramContext = j.a(arrayOfFile2, arrayOfFile1, arrayOfFile3);
    new StringBuilder("getLnkOrCpyResDirFromSO: file count:").append(arrayOfString.length).append(" time:").append(localUCElapseTime.getMilis());
    if (paramContext[0] == arrayOfFile1[0])
    {
      i = 1;
      if (i != 0) {
        break label485;
      }
    }
    label485:
    for (i = 0;; i = 1)
    {
      if (i == 0)
      {
        return paramString2.getAbsolutePath();
        i = 0;
        break;
      }
      return paramString1.getAbsolutePath();
    }
  }
  
  private static String a(String paramString)
  {
    String str = paramString;
    if (!j.a(paramString))
    {
      File localFile = new File(paramString);
      str = paramString;
      if (localFile.exists()) {
        str = localFile.getAbsolutePath();
      }
    }
    return str;
  }
  
  private static List<UCMPackageInfo> a(Context paramContext, File paramFile, List<UCMPackageInfo> paramList)
  {
    int k = 0;
    File localFile3;
    File localFile4;
    File localFile5;
    File localFile2;
    int i;
    label118:
    String[] arrayOfString;
    int m;
    int j;
    if (paramList != null)
    {
      if ((!paramFile.exists()) || (!paramFile.isDirectory())) {
        return paramList;
      }
      localFile3 = new File(paramFile, "sdk_shell.jar");
      localFile4 = new File(paramFile, "browser_if.jar");
      localFile5 = new File(paramFile, "core.jar");
      localFile2 = new File(paramFile, "lib");
      if ((!localFile5.exists()) || ((SDKFactory.n) && (!localFile3.exists())) || ((SDKFactory.n) && (!localFile4.exists())) || (!localFile2.isDirectory())) {
        break label298;
      }
      i = 1;
      if (i != 0)
      {
        arrayOfString = ARCHS;
        m = arrayOfString.length;
        j = 0;
      }
    }
    for (;;)
    {
      File localFile1 = localFile2;
      if (j < m)
      {
        localFile1 = new File(localFile2, arrayOfString[j]);
        if (!localFile1.isDirectory()) {}
      }
      else
      {
        localFile2 = new File(paramFile, "assets");
        if (i != 0) {
          paramList.add(new com.uc.webview.export.internal.utility.UCMPackageInfo(paramContext, "specified", localFile1.getAbsolutePath(), localFile2.getAbsolutePath(), paramFile.getAbsolutePath(), true, localFile3.getAbsolutePath(), localFile4.getAbsolutePath(), localFile5.getAbsolutePath(), null, false));
        }
        paramFile = paramFile.listFiles();
        if (paramFile == null) {
          return paramList;
        }
        j = paramFile.length;
        i = k;
        while (i < j)
        {
          localFile1 = paramFile[i];
          if (localFile1.isDirectory()) {
            a(paramContext, localFile1, paramList);
          }
          i += 1;
        }
        paramList = new ArrayList();
        break;
        label298:
        i = 0;
        break label118;
      }
      j += 1;
    }
    return paramList;
  }
  
  static List<UCMPackageInfo> a(Context paramContext, ConcurrentHashMap<String, Object> paramConcurrentHashMap)
  {
    try
    {
      Object localObject2 = new ArrayList();
      Object localObject3 = (String)paramConcurrentHashMap.get("dexFilePath");
      Object localObject1 = localObject2;
      if (!j.a((String)localObject3)) {
        localObject1 = a(paramContext, new File((String)localObject3), (List)localObject2);
      }
      localObject2 = (String)paramConcurrentHashMap.get("set_odex_path");
      if (localObject2 == null) {
        localObject2 = ((File)invoke(10004, new Object[] { paramContext })).getAbsolutePath();
      }
      for (;;)
      {
        if (((List)localObject1).size() == 0)
        {
          localObject3 = a(paramContext, null, (String)paramConcurrentHashMap.get("dexFilePath"), (String)paramConcurrentHashMap.get("soFilePath"), (String)paramConcurrentHashMap.get("resFilePath"), (String)localObject2);
          if (localObject3 != null) {
            ((List)localObject1).add(localObject3);
          }
          boolean bool = ((Boolean)invoke(10011, new Object[0])).booleanValue();
          if (!bool) {}
        }
        for (;;)
        {
          return localObject1;
          String str = (String)paramConcurrentHashMap.get("ucmKrlDir");
          localObject3 = localObject1;
          if (!j.a(str)) {
            localObject3 = a(paramContext, new File(str), (List)localObject1);
          }
          paramConcurrentHashMap = (String)paramConcurrentHashMap.get("ucmLibDir");
          if (!j.a(paramConcurrentHashMap)) {
            ((List)localObject3).add(c(paramContext, paramConcurrentHashMap, (String)localObject2));
          }
          localObject1 = (List)invoke(10025, new Object[] { localObject3 });
        }
      }
    }
    finally {}
  }
  
  private static void a(long paramLong)
  {
    IWaStat.WaStat.statAKV(new Pair("sc_lsuk", new ce(paramLong)));
  }
  
  private static String b(Context paramContext, String paramString1, String paramString2)
  {
    if (j.a(paramString2)) {
      str1 = null;
    }
    File localFile;
    String str2;
    do
    {
      do
      {
        return str1;
        localFile = new File(paramString2);
        str2 = localFile.getName();
        str1 = paramString2;
      } while (!str2.startsWith("lib"));
      str1 = paramString2;
    } while (!str2.endsWith("_jar_kj_uc.so"));
    paramString2 = str2.substring(3, str2.length() - 13) + ".jar";
    String str1 = (String)invoke(10012, new Object[] { paramString1 });
    paramString1 = (File)invoke(10035, new Object[] { (File)invoke(10007, new Object[] { paramContext }), str1 });
    paramContext = (File)invoke(10035, new Object[] { (File)invoke(10009, new Object[] { paramContext }), str1 });
    return j.a(localFile, new File(paramString1, paramString2), new File(paramContext, paramString2), false).getAbsolutePath();
  }
  
  private static UCMPackageInfo c(Context paramContext, String paramString1, String paramString2)
  {
    for (;;)
    {
      File localFile = (File)invoke(10033, new Object[] { paramString1 });
      paramString1 = null;
      Object localObject1 = null;
      try
      {
        str = UCCyclone.expectFile(localFile, "libcore_jar_kj_uc.so").getAbsolutePath();
        for (;;)
        {
          try
          {
            localObject2 = UCCyclone.expectFile(localFile, "libsdk_shell_jar_kj_uc.so").getAbsolutePath();
            paramString1 = (String)localObject2;
          }
          catch (Throwable localThrowable2)
          {
            Object localObject2;
            int k;
            int i;
            int m;
            int j;
            continue;
          }
          try
          {
            localObject2 = UCCyclone.expectFile(localFile, "libbrowser_if_jar_kj_uc.so").getAbsolutePath();
            localObject1 = localObject2;
          }
          catch (Throwable localThrowable1) {}
        }
        return new com.uc.webview.export.internal.utility.UCMPackageInfo(paramContext, "specified", localFile.getAbsolutePath(), localFile.getAbsolutePath(), localFile.getAbsolutePath(), true, paramString1, (String)localObject1, str, paramString2, true);
      }
      catch (UCKnownException paramString1)
      {
        String str;
        localObject1 = localFile.listFiles();
        if (localObject1 != null)
        {
          localObject2 = ARCHS;
          k = localObject2.length;
          i = 0;
          for (;;)
          {
            if (i >= k) {
              break label207;
            }
            localFile = localObject2[i];
            m = localObject1.length;
            j = 0;
            for (;;)
            {
              if (j >= m) {
                break label200;
              }
              str = localObject1[j];
              if ((localFile.equals(str.getName())) && (str.isDirectory()))
              {
                paramString1 = str.getAbsolutePath();
                break;
              }
              j += 1;
            }
            label200:
            i += 1;
          }
        }
        label207:
        throw paramString1;
      }
    }
  }
  
  public static boolean checkNeedDecompress(Context paramContext, String paramString1, String paramString2)
  {
    if (!new File(paramString2).exists()) {
      return false;
    }
    ArrayList localArrayList = new ArrayList();
    paramString1 = a(paramContext, new File(paramString1), localArrayList).iterator();
    while (paramString1.hasNext()) {
      if (!bt.a(paramContext, ((UCMPackageInfo)paramString1.next()).dataDir, paramString2)) {
        return false;
      }
    }
    return true;
  }
  
  /* Error */
  public static Object invoke(int paramInt, Object... paramVarArgs)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aload 7
    //   5: astore 6
    //   7: iload_0
    //   8: tableswitch	default:+204->212, 10001:+211->219, 10002:+542->550, 10003:+573->581, 10004:+604->612, 10005:+635->643, 10006:+666->674, 10007:+697->705, 10008:+759->767, 10009:+790->798, 10010:+852->860, 10011:+914->922, 10012:+931->939, 10013:+208->216, 10014:+208->216, 10015:+208->216, 10016:+208->216, 10017:+208->216, 10018:+208->216, 10019:+208->216, 10020:+208->216, 10021:+951->959, 10022:+971->979, 10023:+988->996, 10024:+996->1004, 10025:+1013->1021, 10026:+1047->1055, 10027:+1121->1129, 10028:+1232->1240, 10029:+208->216, 10030:+208->216, 10031:+208->216, 10032:+1314->1322, 10033:+1358->1366, 10034:+208->216, 10035:+1388->1396, 10036:+208->216, 10037:+208->216, 10038:+208->216, 10039:+1863->1871, 10040:+1411->1419, 10041:+1677->1685, 10042:+485->493, 10043:+405->413, 10044:+270->278, 10045:+883->891, 10046:+728->736, 10047:+821->829
    //   212: aload 7
    //   214: astore 6
    //   216: aload 6
    //   218: areturn
    //   219: aload_1
    //   220: iconst_0
    //   221: aaload
    //   222: checkcast 151	android/content/Context
    //   225: astore 6
    //   227: aload_1
    //   228: arraylength
    //   229: iconst_2
    //   230: if_icmplt +43 -> 273
    //   233: aload_1
    //   234: iconst_1
    //   235: aaload
    //   236: checkcast 121	java/lang/String
    //   239: astore_1
    //   240: aload 6
    //   242: ldc_w 454
    //   245: iconst_0
    //   246: invokevirtual 457	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   249: astore 7
    //   251: aload 7
    //   253: astore 6
    //   255: aload_1
    //   256: ifnull -40 -> 216
    //   259: new 174	java/io/File
    //   262: dup
    //   263: aload 7
    //   265: aload_1
    //   266: invokespecial 335	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   269: invokestatic 461	com/uc/webview/export/cyclone/UCCyclone:expectCreateDirFile	(Ljava/io/File;)Ljava/io/File;
    //   272: areturn
    //   273: aconst_null
    //   274: astore_1
    //   275: goto -35 -> 240
    //   278: aload_1
    //   279: iconst_0
    //   280: aaload
    //   281: checkcast 151	android/content/Context
    //   284: astore 8
    //   286: aload_1
    //   287: iconst_1
    //   288: aaload
    //   289: checkcast 174	java/io/File
    //   292: astore 9
    //   294: aload_1
    //   295: iconst_2
    //   296: aaload
    //   297: checkcast 174	java/io/File
    //   300: astore 10
    //   302: aload 9
    //   304: invokevirtual 464	java/io/File:isFile	()Z
    //   307: ifeq +65 -> 372
    //   310: aconst_null
    //   311: astore_1
    //   312: iconst_1
    //   313: istore_0
    //   314: aload 7
    //   316: astore 6
    //   318: iload_0
    //   319: ifeq -103 -> 216
    //   322: aload 9
    //   324: iconst_0
    //   325: aload 10
    //   327: invokestatic 468	com/uc/webview/export/cyclone/UCCyclone:recursiveDelete	(Ljava/io/File;ZLjava/lang/Object;)V
    //   330: aload 7
    //   332: astore 6
    //   334: aload 9
    //   336: invokevirtual 250	java/io/File:exists	()Z
    //   339: ifne -123 -> 216
    //   342: aload 7
    //   344: astore 6
    //   346: aload_1
    //   347: ifnull -131 -> 216
    //   350: sipush 10042
    //   353: iconst_2
    //   354: anewarray 4	java/lang/Object
    //   357: dup
    //   358: iconst_0
    //   359: aload 8
    //   361: aastore
    //   362: dup
    //   363: iconst_1
    //   364: aload_1
    //   365: aastore
    //   366: invokestatic 172	com/uc/webview/export/internal/setup/UCMPackageInfo:invoke	(I[Ljava/lang/Object;)Ljava/lang/Object;
    //   369: pop
    //   370: aconst_null
    //   371: areturn
    //   372: aload 9
    //   374: invokevirtual 355	java/io/File:isDirectory	()Z
    //   377: ifeq +1633 -> 2010
    //   380: sipush 10043
    //   383: iconst_2
    //   384: anewarray 4	java/lang/Object
    //   387: dup
    //   388: iconst_0
    //   389: aload 8
    //   391: aastore
    //   392: dup
    //   393: iconst_1
    //   394: aload 9
    //   396: aastore
    //   397: invokestatic 172	com/uc/webview/export/internal/setup/UCMPackageInfo:invoke	(I[Ljava/lang/Object;)Ljava/lang/Object;
    //   400: checkcast 174	java/io/File
    //   403: astore_1
    //   404: aload_1
    //   405: ifnull +1600 -> 2005
    //   408: iconst_1
    //   409: istore_0
    //   410: goto -96 -> 314
    //   413: aload_1
    //   414: iconst_0
    //   415: aaload
    //   416: checkcast 151	android/content/Context
    //   419: astore 6
    //   421: aload_1
    //   422: iconst_1
    //   423: aaload
    //   424: checkcast 174	java/io/File
    //   427: astore_1
    //   428: new 174	java/io/File
    //   431: dup
    //   432: new 174	java/io/File
    //   435: dup
    //   436: sipush 10005
    //   439: iconst_1
    //   440: anewarray 4	java/lang/Object
    //   443: dup
    //   444: iconst_0
    //   445: aload 6
    //   447: aastore
    //   448: invokestatic 172	com/uc/webview/export/internal/setup/UCMPackageInfo:invoke	(I[Ljava/lang/Object;)Ljava/lang/Object;
    //   451: checkcast 174	java/io/File
    //   454: ldc_w 470
    //   457: invokespecial 335	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   460: invokestatic 461	com/uc/webview/export/cyclone/UCCyclone:expectCreateDirFile	(Ljava/io/File;)Ljava/io/File;
    //   463: aload_1
    //   464: invokevirtual 218	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   467: invokestatic 473	com/uc/webview/export/cyclone/UCCyclone:getSourceHash	(Ljava/lang/String;)Ljava/lang/String;
    //   470: invokespecial 335	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   473: invokestatic 461	com/uc/webview/export/cyclone/UCCyclone:expectCreateDirFile	(Ljava/io/File;)Ljava/io/File;
    //   476: astore_1
    //   477: aload 7
    //   479: astore 6
    //   481: aload_1
    //   482: invokevirtual 477	java/io/File:list	()[Ljava/lang/String;
    //   485: arraylength
    //   486: bipush 12
    //   488: if_icmpge -272 -> 216
    //   491: aload_1
    //   492: areturn
    //   493: aload_1
    //   494: iconst_1
    //   495: aaload
    //   496: checkcast 174	java/io/File
    //   499: astore_1
    //   500: invokestatic 482	java/lang/System:currentTimeMillis	()J
    //   503: lstore_3
    //   504: aload 7
    //   506: astore 6
    //   508: new 174	java/io/File
    //   511: dup
    //   512: aload_1
    //   513: lload_3
    //   514: invokestatic 486	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   517: invokespecial 335	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   520: invokevirtual 489	java/io/File:createNewFile	()Z
    //   523: ifne -307 -> 216
    //   526: new 452	java/lang/Exception
    //   529: dup
    //   530: ldc_w 491
    //   533: invokespecial 492	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   536: athrow
    //   537: astore_1
    //   538: new 431	com/uc/webview/export/cyclone/UCKnownException
    //   541: dup
    //   542: sipush 1006
    //   545: aload_1
    //   546: invokespecial 495	com/uc/webview/export/cyclone/UCKnownException:<init>	(ILjava/lang/Throwable;)V
    //   549: athrow
    //   550: aload_1
    //   551: iconst_0
    //   552: aaload
    //   553: checkcast 151	android/content/Context
    //   556: astore 6
    //   558: iconst_2
    //   559: anewarray 4	java/lang/Object
    //   562: astore_1
    //   563: aload_1
    //   564: iconst_0
    //   565: aload 6
    //   567: aastore
    //   568: aload_1
    //   569: iconst_1
    //   570: ldc_w 497
    //   573: aastore
    //   574: sipush 10001
    //   577: istore_0
    //   578: goto -575 -> 3
    //   581: aload_1
    //   582: iconst_0
    //   583: aaload
    //   584: checkcast 151	android/content/Context
    //   587: astore 6
    //   589: iconst_2
    //   590: anewarray 4	java/lang/Object
    //   593: astore_1
    //   594: aload_1
    //   595: iconst_0
    //   596: aload 6
    //   598: aastore
    //   599: aload_1
    //   600: iconst_1
    //   601: ldc_w 499
    //   604: aastore
    //   605: sipush 10001
    //   608: istore_0
    //   609: goto -606 -> 3
    //   612: aload_1
    //   613: iconst_0
    //   614: aaload
    //   615: checkcast 151	android/content/Context
    //   618: astore 6
    //   620: iconst_2
    //   621: anewarray 4	java/lang/Object
    //   624: astore_1
    //   625: aload_1
    //   626: iconst_0
    //   627: aload 6
    //   629: aastore
    //   630: aload_1
    //   631: iconst_1
    //   632: ldc_w 501
    //   635: aastore
    //   636: sipush 10001
    //   639: istore_0
    //   640: goto -637 -> 3
    //   643: aload_1
    //   644: iconst_0
    //   645: aaload
    //   646: checkcast 151	android/content/Context
    //   649: astore 6
    //   651: iconst_2
    //   652: anewarray 4	java/lang/Object
    //   655: astore_1
    //   656: aload_1
    //   657: iconst_0
    //   658: aload 6
    //   660: aastore
    //   661: aload_1
    //   662: iconst_1
    //   663: ldc_w 503
    //   666: aastore
    //   667: sipush 10001
    //   670: istore_0
    //   671: goto -668 -> 3
    //   674: aload_1
    //   675: iconst_0
    //   676: aaload
    //   677: checkcast 151	android/content/Context
    //   680: astore 6
    //   682: iconst_2
    //   683: anewarray 4	java/lang/Object
    //   686: astore_1
    //   687: aload_1
    //   688: iconst_0
    //   689: aload 6
    //   691: aastore
    //   692: aload_1
    //   693: iconst_1
    //   694: ldc_w 505
    //   697: aastore
    //   698: sipush 10001
    //   701: istore_0
    //   702: goto -699 -> 3
    //   705: aload_1
    //   706: iconst_0
    //   707: aaload
    //   708: checkcast 151	android/content/Context
    //   711: astore 6
    //   713: iconst_2
    //   714: anewarray 4	java/lang/Object
    //   717: astore_1
    //   718: aload_1
    //   719: iconst_0
    //   720: aload 6
    //   722: aastore
    //   723: aload_1
    //   724: iconst_1
    //   725: ldc_w 507
    //   728: aastore
    //   729: sipush 10001
    //   732: istore_0
    //   733: goto -730 -> 3
    //   736: aload_1
    //   737: iconst_0
    //   738: aaload
    //   739: checkcast 151	android/content/Context
    //   742: astore 6
    //   744: iconst_2
    //   745: anewarray 4	java/lang/Object
    //   748: astore_1
    //   749: aload_1
    //   750: iconst_0
    //   751: aload 6
    //   753: aastore
    //   754: aload_1
    //   755: iconst_1
    //   756: ldc_w 509
    //   759: aastore
    //   760: sipush 10001
    //   763: istore_0
    //   764: goto -761 -> 3
    //   767: aload_1
    //   768: iconst_0
    //   769: aaload
    //   770: checkcast 151	android/content/Context
    //   773: astore 6
    //   775: iconst_2
    //   776: anewarray 4	java/lang/Object
    //   779: astore_1
    //   780: aload_1
    //   781: iconst_0
    //   782: aload 6
    //   784: aastore
    //   785: aload_1
    //   786: iconst_1
    //   787: ldc_w 511
    //   790: aastore
    //   791: sipush 10001
    //   794: istore_0
    //   795: goto -792 -> 3
    //   798: aload_1
    //   799: iconst_0
    //   800: aaload
    //   801: checkcast 151	android/content/Context
    //   804: astore 6
    //   806: iconst_2
    //   807: anewarray 4	java/lang/Object
    //   810: astore_1
    //   811: aload_1
    //   812: iconst_0
    //   813: aload 6
    //   815: aastore
    //   816: aload_1
    //   817: iconst_1
    //   818: ldc_w 513
    //   821: aastore
    //   822: sipush 10001
    //   825: istore_0
    //   826: goto -823 -> 3
    //   829: aload_1
    //   830: iconst_0
    //   831: aaload
    //   832: checkcast 151	android/content/Context
    //   835: astore 6
    //   837: iconst_2
    //   838: anewarray 4	java/lang/Object
    //   841: astore_1
    //   842: aload_1
    //   843: iconst_0
    //   844: aload 6
    //   846: aastore
    //   847: aload_1
    //   848: iconst_1
    //   849: ldc_w 515
    //   852: aastore
    //   853: sipush 10001
    //   856: istore_0
    //   857: goto -854 -> 3
    //   860: aload_1
    //   861: iconst_0
    //   862: aaload
    //   863: checkcast 151	android/content/Context
    //   866: astore 6
    //   868: iconst_2
    //   869: anewarray 4	java/lang/Object
    //   872: astore_1
    //   873: aload_1
    //   874: iconst_0
    //   875: aload 6
    //   877: aastore
    //   878: aload_1
    //   879: iconst_1
    //   880: ldc_w 517
    //   883: aastore
    //   884: sipush 10001
    //   887: istore_0
    //   888: goto -885 -> 3
    //   891: aload_1
    //   892: iconst_0
    //   893: aaload
    //   894: checkcast 151	android/content/Context
    //   897: astore 6
    //   899: iconst_2
    //   900: anewarray 4	java/lang/Object
    //   903: astore_1
    //   904: aload_1
    //   905: iconst_0
    //   906: aload 6
    //   908: aastore
    //   909: aload_1
    //   910: iconst_1
    //   911: ldc_w 519
    //   914: aastore
    //   915: sipush 10001
    //   918: istore_0
    //   919: goto -916 -> 3
    //   922: ldc 23
    //   924: invokestatic 525	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   927: pop
    //   928: iconst_1
    //   929: invokestatic 528	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   932: areturn
    //   933: astore_1
    //   934: iconst_0
    //   935: invokestatic 528	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   938: areturn
    //   939: aload_1
    //   940: iconst_0
    //   941: aaload
    //   942: checkcast 121	java/lang/String
    //   945: invokevirtual 531	java/lang/String:hashCode	()I
    //   948: invokestatic 533	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   951: bipush 45
    //   953: bipush 95
    //   955: invokevirtual 537	java/lang/String:replace	(CC)Ljava/lang/String;
    //   958: areturn
    //   959: new 199	java/lang/StringBuilder
    //   962: dup
    //   963: ldc_w 539
    //   966: invokespecial 241	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   969: invokestatic 482	java/lang/System:currentTimeMillis	()J
    //   972: invokevirtual 351	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   975: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   978: areturn
    //   979: aload_1
    //   980: iconst_0
    //   981: aaload
    //   982: checkcast 174	java/io/File
    //   985: new 541	com/uc/webview/export/internal/setup/by
    //   988: dup
    //   989: invokespecial 542	com/uc/webview/export/internal/setup/by:<init>	()V
    //   992: invokevirtual 545	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
    //   995: areturn
    //   996: new 547	com/uc/webview/export/internal/setup/bz
    //   999: dup
    //   1000: invokespecial 548	com/uc/webview/export/internal/setup/bz:<init>	()V
    //   1003: areturn
    //   1004: aload_1
    //   1005: iconst_0
    //   1006: aaload
    //   1007: checkcast 174	java/io/File
    //   1010: new 550	com/uc/webview/export/internal/setup/ca
    //   1013: dup
    //   1014: invokespecial 551	com/uc/webview/export/internal/setup/ca:<init>	()V
    //   1017: invokevirtual 554	java/io/File:list	(Ljava/io/FilenameFilter;)[Ljava/lang/String;
    //   1020: areturn
    //   1021: aload_1
    //   1022: iconst_0
    //   1023: aaload
    //   1024: checkcast 364	java/util/List
    //   1027: astore_1
    //   1028: aload_1
    //   1029: ifnull +24 -> 1053
    //   1032: aload_1
    //   1033: invokeinterface 392 1 0
    //   1038: iconst_1
    //   1039: if_icmple +14 -> 1053
    //   1042: aload_1
    //   1043: new 556	com/uc/webview/export/internal/setup/cb
    //   1046: dup
    //   1047: invokespecial 557	com/uc/webview/export/internal/setup/cb:<init>	()V
    //   1050: invokestatic 563	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   1053: aload_1
    //   1054: areturn
    //   1055: aload_1
    //   1056: iconst_0
    //   1057: aaload
    //   1058: checkcast 151	android/content/Context
    //   1061: invokevirtual 567	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1064: bipush 64
    //   1066: invokevirtual 573	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   1069: invokeinterface 437 1 0
    //   1074: astore_1
    //   1075: aload_1
    //   1076: invokeinterface 442 1 0
    //   1081: ifeq +918 -> 1999
    //   1084: aload_1
    //   1085: invokeinterface 446 1 0
    //   1090: checkcast 575	android/content/pm/PackageInfo
    //   1093: astore 6
    //   1095: aload 6
    //   1097: getfield 578	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   1100: ldc_w 580
    //   1103: invokevirtual 425	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   1106: ifeq -31 -> 1075
    //   1109: aload 6
    //   1111: getfield 584	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   1114: getfield 587	android/content/pm/ApplicationInfo:enabled	Z
    //   1117: ifeq -42 -> 1075
    //   1120: iconst_1
    //   1121: istore 5
    //   1123: iload 5
    //   1125: invokestatic 528	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1128: areturn
    //   1129: aload_1
    //   1130: iconst_0
    //   1131: aaload
    //   1132: checkcast 121	java/lang/String
    //   1135: astore 7
    //   1137: aload_1
    //   1138: iconst_1
    //   1139: aaload
    //   1140: checkcast 121	java/lang/String
    //   1143: astore 6
    //   1145: aload_1
    //   1146: iconst_2
    //   1147: aaload
    //   1148: checkcast 121	java/lang/String
    //   1151: astore_1
    //   1152: aload 7
    //   1154: ldc_w 589
    //   1157: invokevirtual 593	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   1160: astore 7
    //   1162: aload 6
    //   1164: ldc_w 589
    //   1167: invokevirtual 593	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   1170: astore 6
    //   1172: aload 7
    //   1174: iconst_0
    //   1175: aaload
    //   1176: invokestatic 595	com/uc/webview/export/internal/utility/j:c	(Ljava/lang/String;)I
    //   1179: aload 6
    //   1181: iconst_0
    //   1182: aaload
    //   1183: invokestatic 595	com/uc/webview/export/internal/utility/j:c	(Ljava/lang/String;)I
    //   1186: if_icmplt +37 -> 1223
    //   1189: aload 7
    //   1191: iconst_1
    //   1192: aaload
    //   1193: invokestatic 595	com/uc/webview/export/internal/utility/j:c	(Ljava/lang/String;)I
    //   1196: aload 6
    //   1198: iconst_1
    //   1199: aaload
    //   1200: invokestatic 595	com/uc/webview/export/internal/utility/j:c	(Ljava/lang/String;)I
    //   1203: if_icmplt +20 -> 1223
    //   1206: aload 7
    //   1208: iconst_2
    //   1209: aaload
    //   1210: invokestatic 595	com/uc/webview/export/internal/utility/j:c	(Ljava/lang/String;)I
    //   1213: aload 6
    //   1215: iconst_2
    //   1216: aaload
    //   1217: invokestatic 595	com/uc/webview/export/internal/utility/j:c	(Ljava/lang/String;)I
    //   1220: if_icmpge +15 -> 1235
    //   1223: ldc_w 597
    //   1226: aload_1
    //   1227: invokestatic 602	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1230: iconst_0
    //   1231: invokestatic 528	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1234: areturn
    //   1235: iconst_1
    //   1236: invokestatic 528	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1239: areturn
    //   1240: aload_1
    //   1241: iconst_0
    //   1242: aaload
    //   1243: checkcast 121	java/lang/String
    //   1246: astore_1
    //   1247: getstatic 607	com/uc/webview/export/Build:PACK_TYPE	I
    //   1250: bipush 34
    //   1252: if_icmpeq +15 -> 1267
    //   1255: aload 7
    //   1257: astore 6
    //   1259: getstatic 607	com/uc/webview/export/Build:PACK_TYPE	I
    //   1262: bipush 43
    //   1264: if_icmpne -1048 -> 216
    //   1267: sipush 10022
    //   1270: iconst_1
    //   1271: anewarray 4	java/lang/Object
    //   1274: dup
    //   1275: iconst_0
    //   1276: sipush 10033
    //   1279: iconst_1
    //   1280: anewarray 4	java/lang/Object
    //   1283: dup
    //   1284: iconst_0
    //   1285: aload_1
    //   1286: aastore
    //   1287: invokestatic 172	com/uc/webview/export/internal/setup/UCMPackageInfo:invoke	(I[Ljava/lang/Object;)Ljava/lang/Object;
    //   1290: checkcast 174	java/io/File
    //   1293: aastore
    //   1294: invokestatic 172	com/uc/webview/export/internal/setup/UCMPackageInfo:invoke	(I[Ljava/lang/Object;)Ljava/lang/Object;
    //   1297: checkcast 609	[Ljava/io/File;
    //   1300: astore_1
    //   1301: aload 7
    //   1303: astore 6
    //   1305: aload_1
    //   1306: ifnull -1090 -> 216
    //   1309: aload 7
    //   1311: astore 6
    //   1313: aload_1
    //   1314: arraylength
    //   1315: ifle -1099 -> 216
    //   1318: aload_1
    //   1319: iconst_0
    //   1320: aaload
    //   1321: areturn
    //   1322: aload_1
    //   1323: iconst_0
    //   1324: aaload
    //   1325: checkcast 174	java/io/File
    //   1328: astore_1
    //   1329: aload_1
    //   1330: invokevirtual 250	java/io/File:exists	()Z
    //   1333: ifne +31 -> 1364
    //   1336: new 262	com/uc/webview/export/internal/setup/UCSetupException
    //   1339: dup
    //   1340: sipush 1002
    //   1343: ldc_w 611
    //   1346: iconst_1
    //   1347: anewarray 4	java/lang/Object
    //   1350: dup
    //   1351: iconst_0
    //   1352: aload_1
    //   1353: invokevirtual 218	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1356: aastore
    //   1357: invokestatic 268	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1360: invokespecial 271	com/uc/webview/export/internal/setup/UCSetupException:<init>	(ILjava/lang/String;)V
    //   1363: athrow
    //   1364: aload_1
    //   1365: areturn
    //   1366: sipush 10032
    //   1369: iconst_1
    //   1370: anewarray 4	java/lang/Object
    //   1373: dup
    //   1374: iconst_0
    //   1375: new 174	java/io/File
    //   1378: dup
    //   1379: aload_1
    //   1380: iconst_0
    //   1381: aaload
    //   1382: checkcast 121	java/lang/String
    //   1385: invokespecial 260	java/io/File:<init>	(Ljava/lang/String;)V
    //   1388: aastore
    //   1389: invokestatic 172	com/uc/webview/export/internal/setup/UCMPackageInfo:invoke	(I[Ljava/lang/Object;)Ljava/lang/Object;
    //   1392: checkcast 174	java/io/File
    //   1395: areturn
    //   1396: new 174	java/io/File
    //   1399: dup
    //   1400: aload_1
    //   1401: iconst_0
    //   1402: aaload
    //   1403: checkcast 174	java/io/File
    //   1406: aload_1
    //   1407: iconst_1
    //   1408: aaload
    //   1409: checkcast 121	java/lang/String
    //   1412: invokespecial 335	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   1415: invokestatic 461	com/uc/webview/export/cyclone/UCCyclone:expectCreateDirFile	(Ljava/io/File;)Ljava/io/File;
    //   1418: areturn
    //   1419: new 174	java/io/File
    //   1422: dup
    //   1423: aload_1
    //   1424: iconst_0
    //   1425: aaload
    //   1426: checkcast 121	java/lang/String
    //   1429: ldc_w 613
    //   1432: invokespecial 246	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   1435: astore_1
    //   1436: aload 7
    //   1438: astore 6
    //   1440: aload_1
    //   1441: invokevirtual 250	java/io/File:exists	()Z
    //   1444: ifeq -1228 -> 216
    //   1447: new 615	java/io/FileReader
    //   1450: dup
    //   1451: aload_1
    //   1452: invokespecial 618	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   1455: astore 6
    //   1457: new 620	java/io/BufferedReader
    //   1460: dup
    //   1461: aload 6
    //   1463: invokespecial 623	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   1466: astore 10
    //   1468: aload 10
    //   1470: astore 8
    //   1472: aload 6
    //   1474: astore 7
    //   1476: aload 10
    //   1478: invokevirtual 626	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   1481: astore_1
    //   1482: aload_1
    //   1483: ifnull +129 -> 1612
    //   1486: aload 10
    //   1488: astore 8
    //   1490: aload 6
    //   1492: astore 7
    //   1494: aload_1
    //   1495: invokevirtual 629	java/lang/String:trim	()Ljava/lang/String;
    //   1498: astore_1
    //   1499: aload 10
    //   1501: astore 8
    //   1503: aload 6
    //   1505: astore 7
    //   1507: aload_1
    //   1508: ldc_w 256
    //   1511: invokevirtual 425	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   1514: ifeq +37 -> 1551
    //   1517: aload 10
    //   1519: astore 8
    //   1521: aload 6
    //   1523: astore 7
    //   1525: aload_1
    //   1526: ldc_w 256
    //   1529: invokevirtual 259	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   1532: istore 5
    //   1534: iload 5
    //   1536: ifeq +44 -> 1580
    //   1539: aload 10
    //   1541: invokevirtual 632	java/io/BufferedReader:close	()V
    //   1544: aload 6
    //   1546: invokevirtual 633	java/io/FileReader:close	()V
    //   1549: aload_1
    //   1550: areturn
    //   1551: aload 10
    //   1553: astore 8
    //   1555: aload 6
    //   1557: astore 7
    //   1559: new 199	java/lang/StringBuilder
    //   1562: dup
    //   1563: ldc_w 256
    //   1566: invokespecial 241	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1569: aload_1
    //   1570: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1573: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1576: astore_1
    //   1577: goto -60 -> 1517
    //   1580: aload 10
    //   1582: astore 8
    //   1584: aload 6
    //   1586: astore 7
    //   1588: new 199	java/lang/StringBuilder
    //   1591: dup
    //   1592: invokespecial 200	java/lang/StringBuilder:<init>	()V
    //   1595: aload_1
    //   1596: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1599: ldc_w 256
    //   1602: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1605: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1608: astore_1
    //   1609: goto -70 -> 1539
    //   1612: aload 10
    //   1614: invokevirtual 632	java/io/BufferedReader:close	()V
    //   1617: aload 6
    //   1619: invokevirtual 633	java/io/FileReader:close	()V
    //   1622: aconst_null
    //   1623: areturn
    //   1624: astore_1
    //   1625: aconst_null
    //   1626: areturn
    //   1627: astore 9
    //   1629: aconst_null
    //   1630: astore_1
    //   1631: aconst_null
    //   1632: astore 6
    //   1634: aload_1
    //   1635: astore 8
    //   1637: aload 6
    //   1639: astore 7
    //   1641: ldc_w 635
    //   1644: ldc_w 636
    //   1647: aload 9
    //   1649: invokestatic 640	com/uc/webview/export/internal/utility/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1652: aload_1
    //   1653: invokevirtual 632	java/io/BufferedReader:close	()V
    //   1656: aload 6
    //   1658: invokevirtual 633	java/io/FileReader:close	()V
    //   1661: aconst_null
    //   1662: areturn
    //   1663: astore_1
    //   1664: aconst_null
    //   1665: areturn
    //   1666: astore_1
    //   1667: aconst_null
    //   1668: astore 8
    //   1670: aconst_null
    //   1671: astore 6
    //   1673: aload 8
    //   1675: invokevirtual 632	java/io/BufferedReader:close	()V
    //   1678: aload 6
    //   1680: invokevirtual 633	java/io/FileReader:close	()V
    //   1683: aload_1
    //   1684: athrow
    //   1685: aload_1
    //   1686: iconst_0
    //   1687: aaload
    //   1688: checkcast 642	java/lang/ClassLoader
    //   1691: astore 6
    //   1693: aload 6
    //   1695: ifnonnull +145 -> 1840
    //   1698: ldc_w 644
    //   1701: invokestatic 525	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1704: astore_1
    //   1705: aload_1
    //   1706: ldc_w 646
    //   1709: invokevirtual 650	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   1712: aconst_null
    //   1713: invokevirtual 653	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1716: invokevirtual 654	java/lang/Object:toString	()Ljava/lang/String;
    //   1719: astore 8
    //   1721: aload_1
    //   1722: ldc_w 656
    //   1725: invokevirtual 650	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   1728: aconst_null
    //   1729: invokevirtual 653	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1732: invokevirtual 654	java/lang/Object:toString	()Ljava/lang/String;
    //   1735: astore 9
    //   1737: aload 8
    //   1739: putstatic 659	com/uc/webview/export/Build:UCM_VERSION	Ljava/lang/String;
    //   1742: aload 9
    //   1744: putstatic 662	com/uc/webview/export/Build:UCM_SUPPORT_SDK_MIN	Ljava/lang/String;
    //   1747: aload_1
    //   1748: ldc_w 664
    //   1751: invokevirtual 650	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   1754: aconst_null
    //   1755: invokevirtual 668	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
    //   1758: putstatic 672	com/uc/webview/export/Build$Version:API_LEVEL	I
    //   1761: ldc_w 674
    //   1764: iconst_0
    //   1765: aload 6
    //   1767: invokestatic 677	java/lang/Class:forName	(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;
    //   1770: astore_1
    //   1771: aload_1
    //   1772: ldc_w 679
    //   1775: invokevirtual 650	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   1778: aconst_null
    //   1779: invokevirtual 653	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1782: invokevirtual 654	java/lang/Object:toString	()Ljava/lang/String;
    //   1785: putstatic 681	com/uc/webview/export/Build:CORE_VERSION	Ljava/lang/String;
    //   1788: aload_1
    //   1789: ldc_w 683
    //   1792: invokevirtual 650	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   1795: aconst_null
    //   1796: invokevirtual 653	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1799: invokevirtual 654	java/lang/Object:toString	()Ljava/lang/String;
    //   1802: putstatic 686	com/uc/webview/export/Build:CORE_TIME	Ljava/lang/String;
    //   1805: aload 7
    //   1807: astore 6
    //   1809: sipush 10052
    //   1812: iconst_0
    //   1813: anewarray 4	java/lang/Object
    //   1816: invokestatic 687	com/uc/webview/export/internal/SDKFactory:invoke	(I[Ljava/lang/Object;)Ljava/lang/Object;
    //   1819: checkcast 293	java/lang/Boolean
    //   1822: invokevirtual 296	java/lang/Boolean:booleanValue	()Z
    //   1825: ifeq -1609 -> 216
    //   1828: new 689	com/uc/webview/export/internal/setup/cc
    //   1831: dup
    //   1832: invokespecial 690	com/uc/webview/export/internal/setup/cc:<init>	()V
    //   1835: invokestatic 693	com/uc/webview/export/internal/utility/j:a	(Ljava/util/Map;)V
    //   1838: aconst_null
    //   1839: areturn
    //   1840: ldc_w 644
    //   1843: iconst_0
    //   1844: aload 6
    //   1846: invokestatic 677	java/lang/Class:forName	(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;
    //   1849: astore_1
    //   1850: goto -145 -> 1705
    //   1853: astore_1
    //   1854: ldc_w 597
    //   1857: ldc_w 695
    //   1860: aload_1
    //   1861: invokestatic 697	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1864: iconst_1
    //   1865: putstatic 672	com/uc/webview/export/Build$Version:API_LEVEL	I
    //   1868: goto -107 -> 1761
    //   1871: aload_1
    //   1872: iconst_0
    //   1873: aaload
    //   1874: checkcast 151	android/content/Context
    //   1877: invokevirtual 701	android/content/Context:getCacheDir	()Ljava/io/File;
    //   1880: new 703	com/uc/webview/export/internal/setup/cd
    //   1883: dup
    //   1884: invokespecial 704	com/uc/webview/export/internal/setup/cd:<init>	()V
    //   1887: invokevirtual 545	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
    //   1890: astore_1
    //   1891: aload 7
    //   1893: astore 6
    //   1895: aload_1
    //   1896: ifnull -1680 -> 216
    //   1899: aload 7
    //   1901: astore 6
    //   1903: aload_1
    //   1904: arraylength
    //   1905: ifle -1689 -> 216
    //   1908: aload_1
    //   1909: arraylength
    //   1910: istore_2
    //   1911: iconst_0
    //   1912: istore_0
    //   1913: aload 7
    //   1915: astore 6
    //   1917: iload_0
    //   1918: iload_2
    //   1919: if_icmpge -1703 -> 216
    //   1922: aload_1
    //   1923: iload_0
    //   1924: aaload
    //   1925: iconst_0
    //   1926: aconst_null
    //   1927: invokestatic 468	com/uc/webview/export/cyclone/UCCyclone:recursiveDelete	(Ljava/io/File;ZLjava/lang/Object;)V
    //   1930: iload_0
    //   1931: iconst_1
    //   1932: iadd
    //   1933: istore_0
    //   1934: goto -21 -> 1913
    //   1937: astore 7
    //   1939: goto -395 -> 1544
    //   1942: astore 6
    //   1944: goto -395 -> 1549
    //   1947: astore_1
    //   1948: goto -331 -> 1617
    //   1951: astore_1
    //   1952: goto -296 -> 1656
    //   1955: astore 7
    //   1957: goto -279 -> 1678
    //   1960: astore 6
    //   1962: goto -279 -> 1683
    //   1965: astore_1
    //   1966: goto -161 -> 1805
    //   1969: astore_1
    //   1970: aconst_null
    //   1971: astore 8
    //   1973: goto -300 -> 1673
    //   1976: astore_1
    //   1977: aload 7
    //   1979: astore 6
    //   1981: goto -308 -> 1673
    //   1984: astore 9
    //   1986: aconst_null
    //   1987: astore_1
    //   1988: goto -354 -> 1634
    //   1991: astore 9
    //   1993: aload 10
    //   1995: astore_1
    //   1996: goto -362 -> 1634
    //   1999: iconst_0
    //   2000: istore 5
    //   2002: goto -879 -> 1123
    //   2005: iconst_0
    //   2006: istore_0
    //   2007: goto -1693 -> 314
    //   2010: aconst_null
    //   2011: astore_1
    //   2012: iconst_0
    //   2013: istore_0
    //   2014: goto -1700 -> 314
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2017	0	paramInt	int
    //   0	2017	1	paramVarArgs	Object[]
    //   1910	10	2	i	int
    //   503	11	3	l	long
    //   1121	880	5	bool	boolean
    //   5	1911	6	localObject1	Object
    //   1942	1	6	localException1	Exception
    //   1960	1	6	localException2	Exception
    //   1979	1	6	localException3	Exception
    //   1	1913	7	localObject2	Object
    //   1937	1	7	localException4	Exception
    //   1955	23	7	localException5	Exception
    //   284	1688	8	localObject3	Object
    //   292	103	9	localFile	File
    //   1627	21	9	localException6	Exception
    //   1735	8	9	str	String
    //   1984	1	9	localException7	Exception
    //   1991	1	9	localException8	Exception
    //   300	1694	10	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   508	537	537	java/lang/Throwable
    //   922	928	933	java/lang/Throwable
    //   1617	1622	1624	java/lang/Exception
    //   1447	1457	1627	java/lang/Exception
    //   1656	1661	1663	java/lang/Exception
    //   1447	1457	1666	finally
    //   1698	1705	1853	java/lang/Exception
    //   1705	1761	1853	java/lang/Exception
    //   1840	1850	1853	java/lang/Exception
    //   1539	1544	1937	java/lang/Exception
    //   1544	1549	1942	java/lang/Exception
    //   1612	1617	1947	java/lang/Exception
    //   1652	1656	1951	java/lang/Exception
    //   1673	1678	1955	java/lang/Exception
    //   1678	1683	1960	java/lang/Exception
    //   1761	1805	1965	java/lang/Exception
    //   1457	1468	1969	finally
    //   1476	1482	1976	finally
    //   1494	1499	1976	finally
    //   1507	1517	1976	finally
    //   1525	1534	1976	finally
    //   1559	1577	1976	finally
    //   1588	1609	1976	finally
    //   1641	1652	1976	finally
    //   1457	1468	1984	java/lang/Exception
    //   1476	1482	1991	java/lang/Exception
    //   1494	1499	1991	java/lang/Exception
    //   1507	1517	1991	java/lang/Exception
    //   1525	1534	1991	java/lang/Exception
    //   1559	1577	1991	java/lang/Exception
    //   1588	1609	1991	java/lang/Exception
  }
  
  /* Error */
  public static List<UCMPackageInfo> listFromSharedApps(Context paramContext, ConcurrentHashMap<String, Object> paramConcurrentHashMap)
  {
    // Byte code:
    //   0: lconst_1
    //   1: lstore 6
    //   3: ldc_w 707
    //   6: invokestatic 712	com/uc/webview/export/extension/UCCore:getParam	(Ljava/lang/String;)Ljava/lang/String;
    //   9: astore 15
    //   11: ldc_w 714
    //   14: invokestatic 712	com/uc/webview/export/extension/UCCore:getParam	(Ljava/lang/String;)Ljava/lang/String;
    //   17: astore_1
    //   18: aload 15
    //   20: invokestatic 179	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
    //   23: ifeq +8 -> 31
    //   26: ldc2_w 715
    //   29: lstore 6
    //   31: lload 6
    //   33: lstore 4
    //   35: aload_1
    //   36: invokestatic 179	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
    //   39: ifeq +11 -> 50
    //   42: lload 6
    //   44: ldc2_w 717
    //   47: lor
    //   48: lstore 4
    //   50: aload 15
    //   52: invokestatic 179	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
    //   55: ifeq +101 -> 156
    //   58: new 375	java/util/ArrayList
    //   61: dup
    //   62: invokespecial 376	java/util/ArrayList:<init>	()V
    //   65: astore 16
    //   67: lload 4
    //   69: lstore 6
    //   71: lload 4
    //   73: lstore 8
    //   75: aload_1
    //   76: invokestatic 179	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
    //   79: istore 12
    //   81: iload 12
    //   83: ifeq +79 -> 162
    //   86: ldc2_w 719
    //   89: lstore 6
    //   91: lload 6
    //   93: lstore 8
    //   95: new 262	com/uc/webview/export/internal/setup/UCSetupException
    //   98: dup
    //   99: sipush 2013
    //   102: ldc_w 722
    //   105: invokespecial 271	com/uc/webview/export/internal/setup/UCSetupException:<init>	(ILjava/lang/String;)V
    //   108: athrow
    //   109: astore_0
    //   110: lload 8
    //   112: lstore 6
    //   114: ldc_w 597
    //   117: ldc_w 724
    //   120: aload_0
    //   121: invokestatic 727	com/uc/webview/export/internal/utility/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   124: lload 8
    //   126: invokestatic 729	com/uc/webview/export/internal/setup/UCMPackageInfo:a	(J)V
    //   129: ldc_w 597
    //   132: new 199	java/lang/StringBuilder
    //   135: dup
    //   136: ldc_w 731
    //   139: invokespecial 241	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   142: lload 8
    //   144: invokevirtual 351	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   147: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   150: invokestatic 602	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   153: aload 16
    //   155: areturn
    //   156: aload 15
    //   158: astore_1
    //   159: goto -101 -> 58
    //   162: lload 4
    //   164: lstore 6
    //   166: lload 4
    //   168: lstore 8
    //   170: aload_1
    //   171: ldc_w 733
    //   174: invokevirtual 593	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   177: astore 17
    //   179: lload 4
    //   181: lstore 6
    //   183: lload 4
    //   185: lstore 8
    //   187: aload 17
    //   189: arraylength
    //   190: istore_3
    //   191: iconst_0
    //   192: istore_2
    //   193: iload_2
    //   194: iload_3
    //   195: if_icmpge +1047 -> 1242
    //   198: aload 17
    //   200: iload_2
    //   201: aaload
    //   202: astore_1
    //   203: lload 4
    //   205: lstore 8
    //   207: lload 4
    //   209: lstore 6
    //   211: lload 4
    //   213: lstore 10
    //   215: aload_1
    //   216: invokestatic 179	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
    //   219: ifne +1099 -> 1318
    //   222: lload 4
    //   224: lstore 8
    //   226: lload 4
    //   228: lstore 6
    //   230: aload 15
    //   232: invokestatic 179	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
    //   235: ifne +33 -> 268
    //   238: lload 4
    //   240: lstore 8
    //   242: lload 4
    //   244: lstore 6
    //   246: aload_1
    //   247: aload_0
    //   248: invokevirtual 183	android/content/Context:getPackageName	()Ljava/lang/String;
    //   251: invokevirtual 277	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   254: ifeq +14 -> 268
    //   257: lload 4
    //   259: ldc2_w 734
    //   262: lor
    //   263: lstore 10
    //   265: goto +1053 -> 1318
    //   268: lload 4
    //   270: lstore 8
    //   272: lload 4
    //   274: lstore 6
    //   276: aload 15
    //   278: invokestatic 179	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
    //   281: ifne +1031 -> 1312
    //   284: lload 4
    //   286: lstore 8
    //   288: lload 4
    //   290: lstore 6
    //   292: new 199	java/lang/StringBuilder
    //   295: dup
    //   296: ldc_w 737
    //   299: invokespecial 241	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   302: aload_1
    //   303: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: ldc_w 739
    //   309: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   312: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   315: astore 14
    //   317: lload 4
    //   319: lstore 8
    //   321: lload 4
    //   323: lstore 6
    //   325: new 174	java/io/File
    //   328: dup
    //   329: aload 14
    //   331: ldc_w 741
    //   334: invokespecial 246	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   337: invokestatic 744	com/uc/webview/export/internal/setup/UCMPackageInfo$a:a	(Ljava/io/File;)Ljava/util/List;
    //   340: astore 14
    //   342: lload 4
    //   344: lstore 8
    //   346: lload 4
    //   348: lstore 6
    //   350: aload 14
    //   352: invokeinterface 392 1 0
    //   357: ifne +14 -> 371
    //   360: lload 4
    //   362: ldc2_w 745
    //   365: lor
    //   366: lstore 10
    //   368: goto +950 -> 1318
    //   371: lload 4
    //   373: lstore 8
    //   375: lload 4
    //   377: lstore 6
    //   379: new 174	java/io/File
    //   382: dup
    //   383: ldc_w 737
    //   386: invokespecial 260	java/io/File:<init>	(Ljava/lang/String;)V
    //   389: astore 18
    //   391: lload 4
    //   393: lstore 8
    //   395: lload 4
    //   397: lstore 6
    //   399: aload 14
    //   401: invokeinterface 437 1 0
    //   406: astore 19
    //   408: lload 4
    //   410: lstore 8
    //   412: lload 4
    //   414: lstore 6
    //   416: lload 4
    //   418: lstore 10
    //   420: aload 19
    //   422: invokeinterface 442 1 0
    //   427: ifeq +891 -> 1318
    //   430: lload 4
    //   432: lstore 8
    //   434: lload 4
    //   436: lstore 6
    //   438: aload 19
    //   440: invokeinterface 446 1 0
    //   445: checkcast 6	com/uc/webview/export/internal/setup/UCMPackageInfo$a
    //   448: astore 23
    //   450: lload 4
    //   452: lstore 8
    //   454: lload 4
    //   456: lstore 6
    //   458: aload 23
    //   460: getfield 748	com/uc/webview/export/internal/setup/UCMPackageInfo$a:c	Ljava/lang/String;
    //   463: invokestatic 179	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
    //   466: ifne +839 -> 1305
    //   469: lload 4
    //   471: lstore 8
    //   473: lload 4
    //   475: lstore 6
    //   477: new 174	java/io/File
    //   480: dup
    //   481: aload 23
    //   483: getfield 748	com/uc/webview/export/internal/setup/UCMPackageInfo$a:c	Ljava/lang/String;
    //   486: invokespecial 260	java/io/File:<init>	(Ljava/lang/String;)V
    //   489: astore 20
    //   491: lload 4
    //   493: lstore 8
    //   495: lload 4
    //   497: lstore 6
    //   499: new 174	java/io/File
    //   502: dup
    //   503: aload 23
    //   505: getfield 750	com/uc/webview/export/internal/setup/UCMPackageInfo$a:d	Ljava/lang/String;
    //   508: invokespecial 260	java/io/File:<init>	(Ljava/lang/String;)V
    //   511: astore 21
    //   513: lload 4
    //   515: lstore 8
    //   517: lload 4
    //   519: lstore 6
    //   521: new 174	java/io/File
    //   524: dup
    //   525: aload 23
    //   527: getfield 752	com/uc/webview/export/internal/setup/UCMPackageInfo$a:e	Ljava/lang/String;
    //   530: invokespecial 260	java/io/File:<init>	(Ljava/lang/String;)V
    //   533: astore 22
    //   535: lload 4
    //   537: lstore 8
    //   539: lload 4
    //   541: lstore 6
    //   543: aload 23
    //   545: getfield 754	com/uc/webview/export/internal/setup/UCMPackageInfo$a:a	Ljava/lang/String;
    //   548: astore 14
    //   550: lload 4
    //   552: lstore 8
    //   554: lload 4
    //   556: lstore 6
    //   558: aload 23
    //   560: getfield 756	com/uc/webview/export/internal/setup/UCMPackageInfo$a:b	Ljava/lang/String;
    //   563: astore 23
    //   565: lload 4
    //   567: lstore 8
    //   569: lload 4
    //   571: lstore 6
    //   573: sipush 10027
    //   576: iconst_3
    //   577: anewarray 4	java/lang/Object
    //   580: dup
    //   581: iconst_0
    //   582: aload 14
    //   584: aastore
    //   585: dup
    //   586: iconst_1
    //   587: getstatic 759	com/uc/webview/export/Build$Version:SUPPORT_U4_MIN	Ljava/lang/String;
    //   590: aastore
    //   591: dup
    //   592: iconst_2
    //   593: ldc_w 761
    //   596: aastore
    //   597: invokestatic 172	com/uc/webview/export/internal/setup/UCMPackageInfo:invoke	(I[Ljava/lang/Object;)Ljava/lang/Object;
    //   600: checkcast 293	java/lang/Boolean
    //   603: invokevirtual 296	java/lang/Boolean:booleanValue	()Z
    //   606: istore 12
    //   608: lload 4
    //   610: lstore 8
    //   612: lload 4
    //   614: lstore 6
    //   616: sipush 10027
    //   619: iconst_3
    //   620: anewarray 4	java/lang/Object
    //   623: dup
    //   624: iconst_0
    //   625: getstatic 763	com/uc/webview/export/Build$Version:NAME	Ljava/lang/String;
    //   628: aastore
    //   629: dup
    //   630: iconst_1
    //   631: aload 23
    //   633: aastore
    //   634: dup
    //   635: iconst_2
    //   636: ldc_w 765
    //   639: aastore
    //   640: invokestatic 172	com/uc/webview/export/internal/setup/UCMPackageInfo:invoke	(I[Ljava/lang/Object;)Ljava/lang/Object;
    //   643: checkcast 293	java/lang/Boolean
    //   646: invokevirtual 296	java/lang/Boolean:booleanValue	()Z
    //   649: istore 13
    //   651: iload 12
    //   653: ifeq +139 -> 792
    //   656: iload 13
    //   658: ifeq +134 -> 792
    //   661: lload 4
    //   663: lstore 8
    //   665: lload 4
    //   667: lstore 6
    //   669: ldc_w 597
    //   672: ldc_w 767
    //   675: invokestatic 602	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   678: lload 4
    //   680: lstore 8
    //   682: lload 4
    //   684: lstore 6
    //   686: aload 20
    //   688: invokevirtual 250	java/io/File:exists	()Z
    //   691: ifeq +390 -> 1081
    //   694: lload 4
    //   696: lstore 8
    //   698: lload 4
    //   700: lstore 6
    //   702: aload 21
    //   704: invokevirtual 250	java/io/File:exists	()Z
    //   707: ifeq +374 -> 1081
    //   710: lload 4
    //   712: lstore 8
    //   714: lload 4
    //   716: lstore 6
    //   718: aload 22
    //   720: invokevirtual 250	java/io/File:exists	()Z
    //   723: istore 12
    //   725: iload 12
    //   727: ifeq +354 -> 1081
    //   730: lload 4
    //   732: ldc2_w 768
    //   735: lor
    //   736: lstore 4
    //   738: aload 20
    //   740: aload 18
    //   742: invokestatic 774	com/uc/webview/export/internal/setup/aw:a	(Ljava/io/File;Ljava/io/File;)Z
    //   745: ifne +170 -> 915
    //   748: ldc_w 597
    //   751: new 199	java/lang/StringBuilder
    //   754: dup
    //   755: ldc_w 776
    //   758: invokespecial 241	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   761: aload 20
    //   763: invokevirtual 218	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   766: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   769: ldc_w 778
    //   772: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   775: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   778: invokestatic 780	com/uc/webview/export/internal/utility/Log:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   781: lload 4
    //   783: ldc2_w 781
    //   786: lor
    //   787: lstore 4
    //   789: goto -381 -> 408
    //   792: lload 4
    //   794: lstore 8
    //   796: lload 4
    //   798: lstore 6
    //   800: ldc_w 597
    //   803: new 199	java/lang/StringBuilder
    //   806: dup
    //   807: ldc_w 784
    //   810: invokespecial 241	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   813: aload 14
    //   815: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   818: ldc_w 786
    //   821: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   824: getstatic 759	com/uc/webview/export/Build$Version:SUPPORT_U4_MIN	Ljava/lang/String;
    //   827: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   830: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   833: invokestatic 602	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   836: lload 4
    //   838: lstore 8
    //   840: lload 4
    //   842: lstore 6
    //   844: ldc_w 597
    //   847: new 199	java/lang/StringBuilder
    //   850: dup
    //   851: ldc_w 788
    //   854: invokespecial 241	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   857: aload 23
    //   859: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   862: ldc_w 790
    //   865: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   868: getstatic 763	com/uc/webview/export/Build$Version:NAME	Ljava/lang/String;
    //   871: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   874: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   877: invokestatic 602	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   880: goto -472 -> 408
    //   883: astore_0
    //   884: lload 6
    //   886: invokestatic 729	com/uc/webview/export/internal/setup/UCMPackageInfo:a	(J)V
    //   889: ldc_w 597
    //   892: new 199	java/lang/StringBuilder
    //   895: dup
    //   896: ldc_w 731
    //   899: invokespecial 241	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   902: lload 6
    //   904: invokevirtual 351	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   907: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   910: invokestatic 602	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   913: aload_0
    //   914: athrow
    //   915: aload 21
    //   917: aload 18
    //   919: invokestatic 792	com/uc/webview/export/internal/setup/aw:b	(Ljava/io/File;Ljava/io/File;)Z
    //   922: ifne +47 -> 969
    //   925: ldc_w 597
    //   928: new 199	java/lang/StringBuilder
    //   931: dup
    //   932: ldc_w 794
    //   935: invokespecial 241	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   938: aload 21
    //   940: invokevirtual 218	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   943: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   946: ldc_w 778
    //   949: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   952: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   955: invokestatic 780	com/uc/webview/export/internal/utility/Log:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   958: lload 4
    //   960: ldc2_w 795
    //   963: lor
    //   964: lstore 4
    //   966: goto -558 -> 408
    //   969: aload 22
    //   971: aload 18
    //   973: invokestatic 798	com/uc/webview/export/internal/setup/aw:c	(Ljava/io/File;Ljava/io/File;)Z
    //   976: ifne +47 -> 1023
    //   979: ldc_w 597
    //   982: new 199	java/lang/StringBuilder
    //   985: dup
    //   986: ldc_w 800
    //   989: invokespecial 241	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   992: aload 22
    //   994: invokevirtual 218	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   997: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1000: ldc_w 778
    //   1003: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1006: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1009: invokestatic 780	com/uc/webview/export/internal/utility/Log:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1012: lload 4
    //   1014: ldc2_w 801
    //   1017: lor
    //   1018: lstore 4
    //   1020: goto -612 -> 408
    //   1023: aload 15
    //   1025: invokestatic 179	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
    //   1028: ifne +47 -> 1075
    //   1031: aload_1
    //   1032: astore 14
    //   1034: aload 16
    //   1036: aload_0
    //   1037: aload 14
    //   1039: aload 20
    //   1041: invokevirtual 218	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1044: aload 21
    //   1046: invokevirtual 218	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1049: aload 22
    //   1051: invokevirtual 218	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1054: aconst_null
    //   1055: invokestatic 398	com/uc/webview/export/internal/setup/UCMPackageInfo:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/uc/webview/export/internal/setup/UCMPackageInfo;
    //   1058: invokeinterface 367 2 0
    //   1063: pop
    //   1064: lload 4
    //   1066: ldc2_w 803
    //   1069: lor
    //   1070: lstore 4
    //   1072: goto -664 -> 408
    //   1075: aconst_null
    //   1076: astore 14
    //   1078: goto -44 -> 1034
    //   1081: lload 4
    //   1083: lstore 8
    //   1085: lload 4
    //   1087: lstore 6
    //   1089: ldc_w 597
    //   1092: new 199	java/lang/StringBuilder
    //   1095: dup
    //   1096: invokespecial 200	java/lang/StringBuilder:<init>	()V
    //   1099: aload 20
    //   1101: invokevirtual 218	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1104: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1107: ldc_w 806
    //   1110: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1113: aload 21
    //   1115: invokevirtual 218	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1118: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1121: ldc_w 806
    //   1124: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1127: aload 22
    //   1129: invokevirtual 218	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1132: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1135: ldc_w 808
    //   1138: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1141: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1144: invokestatic 780	com/uc/webview/export/internal/utility/Log:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1147: lload 4
    //   1149: lstore 8
    //   1151: lload 4
    //   1153: lstore 6
    //   1155: lload 4
    //   1157: lstore 10
    //   1159: aload 20
    //   1161: invokevirtual 250	java/io/File:exists	()Z
    //   1164: ifne +11 -> 1175
    //   1167: lload 4
    //   1169: ldc2_w 809
    //   1172: lor
    //   1173: lstore 10
    //   1175: lload 10
    //   1177: lstore 8
    //   1179: lload 10
    //   1181: lstore 6
    //   1183: aload 21
    //   1185: invokevirtual 250	java/io/File:exists	()Z
    //   1188: istore 12
    //   1190: iload 12
    //   1192: ifne +106 -> 1298
    //   1195: ldc2_w 811
    //   1198: lload 10
    //   1200: lor
    //   1201: lstore 4
    //   1203: lload 4
    //   1205: lstore 6
    //   1207: lload 4
    //   1209: lstore 8
    //   1211: aload 22
    //   1213: invokevirtual 250	java/io/File:exists	()Z
    //   1216: istore 12
    //   1218: lload 4
    //   1220: lstore 6
    //   1222: iload 12
    //   1224: ifne +11 -> 1235
    //   1227: lload 4
    //   1229: ldc2_w 813
    //   1232: lor
    //   1233: lstore 6
    //   1235: lload 6
    //   1237: lstore 4
    //   1239: goto -831 -> 408
    //   1242: lload 4
    //   1244: invokestatic 729	com/uc/webview/export/internal/setup/UCMPackageInfo:a	(J)V
    //   1247: ldc_w 597
    //   1250: new 199	java/lang/StringBuilder
    //   1253: dup
    //   1254: ldc_w 731
    //   1257: invokespecial 241	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1260: lload 4
    //   1262: invokevirtual 351	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1265: invokevirtual 209	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1268: invokestatic 602	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1271: aload 16
    //   1273: areturn
    //   1274: astore_0
    //   1275: goto -391 -> 884
    //   1278: astore_0
    //   1279: lload 4
    //   1281: lstore 6
    //   1283: goto -399 -> 884
    //   1286: astore_0
    //   1287: goto -1177 -> 110
    //   1290: astore_0
    //   1291: lload 4
    //   1293: lstore 8
    //   1295: goto -1185 -> 110
    //   1298: lload 10
    //   1300: lstore 4
    //   1302: goto -99 -> 1203
    //   1305: lload 4
    //   1307: lstore 6
    //   1309: goto -74 -> 1235
    //   1312: aload_1
    //   1313: astore 14
    //   1315: goto -998 -> 317
    //   1318: iload_2
    //   1319: iconst_1
    //   1320: iadd
    //   1321: istore_2
    //   1322: lload 10
    //   1324: lstore 4
    //   1326: goto -1133 -> 193
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1329	0	paramContext	Context
    //   0	1329	1	paramConcurrentHashMap	ConcurrentHashMap<String, Object>
    //   192	1130	2	i	int
    //   190	6	3	j	int
    //   33	1292	4	l1	long
    //   1	1307	6	l2	long
    //   73	1221	8	l3	long
    //   213	1110	10	l4	long
    //   79	1144	12	bool1	boolean
    //   649	8	13	bool2	boolean
    //   315	999	14	localObject1	Object
    //   9	1015	15	str	String
    //   65	1207	16	localArrayList	ArrayList
    //   177	22	17	arrayOfString	String[]
    //   389	583	18	localFile1	File
    //   406	33	19	localIterator	Iterator
    //   489	671	20	localFile2	File
    //   511	673	21	localFile3	File
    //   533	679	22	localFile4	File
    //   448	410	23	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   95	109	109	java/lang/Exception
    //   215	222	109	java/lang/Exception
    //   230	238	109	java/lang/Exception
    //   246	257	109	java/lang/Exception
    //   276	284	109	java/lang/Exception
    //   292	317	109	java/lang/Exception
    //   325	342	109	java/lang/Exception
    //   350	360	109	java/lang/Exception
    //   379	391	109	java/lang/Exception
    //   399	408	109	java/lang/Exception
    //   420	430	109	java/lang/Exception
    //   438	450	109	java/lang/Exception
    //   458	469	109	java/lang/Exception
    //   477	491	109	java/lang/Exception
    //   499	513	109	java/lang/Exception
    //   521	535	109	java/lang/Exception
    //   543	550	109	java/lang/Exception
    //   558	565	109	java/lang/Exception
    //   573	608	109	java/lang/Exception
    //   616	651	109	java/lang/Exception
    //   669	678	109	java/lang/Exception
    //   686	694	109	java/lang/Exception
    //   702	710	109	java/lang/Exception
    //   718	725	109	java/lang/Exception
    //   800	836	109	java/lang/Exception
    //   844	880	109	java/lang/Exception
    //   1089	1147	109	java/lang/Exception
    //   1159	1167	109	java/lang/Exception
    //   1183	1190	109	java/lang/Exception
    //   95	109	883	finally
    //   114	124	883	finally
    //   215	222	883	finally
    //   230	238	883	finally
    //   246	257	883	finally
    //   276	284	883	finally
    //   292	317	883	finally
    //   325	342	883	finally
    //   350	360	883	finally
    //   379	391	883	finally
    //   399	408	883	finally
    //   420	430	883	finally
    //   438	450	883	finally
    //   458	469	883	finally
    //   477	491	883	finally
    //   499	513	883	finally
    //   521	535	883	finally
    //   543	550	883	finally
    //   558	565	883	finally
    //   573	608	883	finally
    //   616	651	883	finally
    //   669	678	883	finally
    //   686	694	883	finally
    //   702	710	883	finally
    //   718	725	883	finally
    //   800	836	883	finally
    //   844	880	883	finally
    //   1089	1147	883	finally
    //   1159	1167	883	finally
    //   1183	1190	883	finally
    //   75	81	1274	finally
    //   170	179	1274	finally
    //   187	191	1274	finally
    //   1211	1218	1274	finally
    //   738	781	1278	finally
    //   915	958	1278	finally
    //   969	1012	1278	finally
    //   1023	1031	1278	finally
    //   1034	1064	1278	finally
    //   75	81	1286	java/lang/Exception
    //   170	179	1286	java/lang/Exception
    //   187	191	1286	java/lang/Exception
    //   1211	1218	1286	java/lang/Exception
    //   738	781	1290	java/lang/Exception
    //   915	958	1290	java/lang/Exception
    //   969	1012	1290	java/lang/Exception
    //   1023	1031	1290	java/lang/Exception
    //   1034	1064	1290	java/lang/Exception
  }
  
  public String getDirAlias(Context paramContext)
  {
    if ((this.coreImplModule != null) && (this.coreImplModule.first != null))
    {
      String str = (String)this.coreImplModule.first;
      if (str != null)
      {
        if (str.startsWith(((File)invoke(10003, new Object[] { paramContext })).getAbsolutePath())) {
          return "dec";
        }
        if (str.startsWith(((File)invoke(10002, new Object[] { paramContext })).getAbsolutePath())) {
          return "upd";
        }
        if (str.startsWith(((File)invoke(10007, new Object[] { paramContext })).getAbsolutePath())) {
          return "kjl";
        }
        if (str.startsWith(((File)invoke(10009, new Object[] { paramContext })).getAbsolutePath())) {
          return "kjc";
        }
        if (str.startsWith(((File)invoke(10006, new Object[] { paramContext })).getAbsolutePath())) {
          return "rep";
        }
        return "oth";
      }
    }
    return "nul";
  }
  
  public Map<String, String> getFileHashs()
  {
    int i = 0;
    HashMap localHashMap = new HashMap(16);
    Object localObject1;
    if ((this.coreImplModule != null) && (this.coreImplModule.first != null))
    {
      localObject1 = new File((String)this.coreImplModule.first);
      localHashMap.put(((File)localObject1).getName(), UCCyclone.hashFileContents((File)localObject1, UCCyclone.MessageDigestType.MD5));
    }
    for (;;)
    {
      label121:
      label175:
      Object localObject2;
      if ((this.browserIFModule != null) && (this.browserIFModule.first != null))
      {
        localObject1 = new File((String)this.browserIFModule.first);
        localHashMap.put(((File)localObject1).getName(), UCCyclone.hashFileContents((File)localObject1, UCCyclone.MessageDigestType.MD5));
        if ((this.sdkShellModule == null) || (this.sdkShellModule.first == null)) {
          break label404;
        }
        localObject1 = new File((String)this.sdkShellModule.first);
        localHashMap.put(((File)localObject1).getName(), UCCyclone.hashFileContents((File)localObject1, UCCyclone.MessageDigestType.MD5));
        localObject2 = this.soDirPath;
        localObject1 = localObject2;
        if (j.a((String)localObject2)) {
          localObject1 = this.c.getApplicationInfo().nativeLibraryDir;
        }
        if (localObject1 == null) {
          break label506;
        }
        localObject1 = new File((String)localObject1);
        if (!((File)localObject1).isDirectory()) {
          break label489;
        }
      }
      try
      {
        if (this.mSdkShellClassLoader == null) {
          break label472;
        }
        localObject2 = Class.forName("com.uc.webview.browser.shell.NativeLibraries", true, this.mSdkShellClassLoader);
        if (localObject2 != null)
        {
          localObject2 = ((Class)localObject2).getDeclaredField("LIBRARIES");
          ((Field)localObject2).setAccessible(true);
          localObject2 = (String[][])((Field)localObject2).get(null);
          if (localObject2 == null) {
            break label455;
          }
          int j = localObject2.length;
          label284:
          if (i < j)
          {
            String str2 = localObject2[i];
            String str1 = str2[0];
            j.d(str2[1]);
            str2 = str2[2];
            String str3 = UCCyclone.hashFileContents(new File((File)localObject1, str1), UCCyclone.MessageDigestType.MD5);
            if ((!j.a(str2)) && (!str2.equals(str3))) {
              localHashMap.put(str1, str3);
            }
            for (;;)
            {
              i += 1;
              break label284;
              localHashMap.put("core", "null");
              break;
              localHashMap.put("browser_if", "null");
              break label121;
              label404:
              localHashMap.put("sdk_shell", "null");
              break label175;
              localHashMap.put(str1, "ok");
            }
          }
        }
        return localHashMap;
      }
      catch (Throwable localThrowable)
      {
        localHashMap.put("NativeLibraries", "exception");
      }
    }
    label455:
    localHashMap.put("NativeLibraries", "null");
    return localHashMap;
    label472:
    localHashMap.put("sdk_shell_cl", "null");
    return localHashMap;
    label489:
    localHashMap.put("so_dir", "null");
    return localHashMap;
    label506:
    localHashMap.put("so_path", "null");
    return localHashMap;
  }
  
  public static final class a
  {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    
    public a() {}
    
    /* Error */
    public static List<a> a(File paramFile)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_2
      //   2: new 24	java/util/ArrayList
      //   5: dup
      //   6: invokespecial 25	java/util/ArrayList:<init>	()V
      //   9: astore 4
      //   11: aload_0
      //   12: ifnull +10 -> 22
      //   15: aload_0
      //   16: invokevirtual 31	java/io/File:exists	()Z
      //   19: ifne +32 -> 51
      //   22: ldc 33
      //   24: new 35	java/lang/StringBuilder
      //   27: dup
      //   28: ldc 37
      //   30: invokespecial 40	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   33: aload_0
      //   34: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   37: ldc 46
      //   39: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   42: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   45: invokestatic 59	com/uc/webview/export/internal/utility/Log:i	(Ljava/lang/String;Ljava/lang/String;)V
      //   48: aload 4
      //   50: areturn
      //   51: new 61	java/io/FileInputStream
      //   54: dup
      //   55: aload_0
      //   56: invokespecial 64	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   59: astore_3
      //   60: new 66	java/io/ByteArrayOutputStream
      //   63: dup
      //   64: invokespecial 67	java/io/ByteArrayOutputStream:<init>	()V
      //   67: astore_0
      //   68: sipush 1024
      //   71: newarray byte
      //   73: astore_2
      //   74: aload_3
      //   75: aload_2
      //   76: invokevirtual 71	java/io/FileInputStream:read	([B)I
      //   79: istore_1
      //   80: iload_1
      //   81: ifle +44 -> 125
      //   84: aload_0
      //   85: aload_2
      //   86: iconst_0
      //   87: iload_1
      //   88: invokevirtual 75	java/io/ByteArrayOutputStream:write	([BII)V
      //   91: goto -17 -> 74
      //   94: astore 4
      //   96: aload_3
      //   97: astore_2
      //   98: aload 4
      //   100: astore_3
      //   101: ldc 33
      //   103: ldc 77
      //   105: aload_3
      //   106: invokestatic 80	com/uc/webview/export/internal/utility/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   109: aload_2
      //   110: invokestatic 86	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
      //   113: aload_0
      //   114: invokestatic 86	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
      //   117: new 24	java/util/ArrayList
      //   120: dup
      //   121: invokespecial 25	java/util/ArrayList:<init>	()V
      //   124: areturn
      //   125: new 88	org/json/JSONArray
      //   128: dup
      //   129: new 90	java/lang/String
      //   132: dup
      //   133: aload_0
      //   134: invokevirtual 94	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   137: invokespecial 97	java/lang/String:<init>	([B)V
      //   140: invokespecial 98	org/json/JSONArray:<init>	(Ljava/lang/String;)V
      //   143: astore_2
      //   144: iconst_0
      //   145: istore_1
      //   146: iload_1
      //   147: aload_2
      //   148: invokevirtual 102	org/json/JSONArray:length	()I
      //   151: if_icmpge +184 -> 335
      //   154: aload_2
      //   155: iload_1
      //   156: invokevirtual 106	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
      //   159: astore 6
      //   161: new 2	com/uc/webview/export/internal/setup/UCMPackageInfo$a
      //   164: dup
      //   165: invokespecial 107	com/uc/webview/export/internal/setup/UCMPackageInfo$a:<init>	()V
      //   168: astore 5
      //   170: aload 5
      //   172: aload 6
      //   174: ldc 109
      //   176: invokevirtual 115	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   179: putfield 117	com/uc/webview/export/internal/setup/UCMPackageInfo$a:a	Ljava/lang/String;
      //   182: aload 5
      //   184: aload 6
      //   186: ldc 119
      //   188: invokevirtual 115	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   191: putfield 121	com/uc/webview/export/internal/setup/UCMPackageInfo$a:b	Ljava/lang/String;
      //   194: aload 6
      //   196: ldc 123
      //   198: invokevirtual 127	org/json/JSONObject:has	(Ljava/lang/String;)Z
      //   201: ifeq +20 -> 221
      //   204: aload 5
      //   206: aload 6
      //   208: ldc 123
      //   210: invokevirtual 130	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   213: ldc -124
      //   215: invokevirtual 115	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   218: putfield 134	com/uc/webview/export/internal/setup/UCMPackageInfo$a:c	Ljava/lang/String;
      //   221: aload 6
      //   223: ldc -120
      //   225: invokevirtual 127	org/json/JSONObject:has	(Ljava/lang/String;)Z
      //   228: ifeq +20 -> 248
      //   231: aload 5
      //   233: aload 6
      //   235: ldc -120
      //   237: invokevirtual 130	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   240: ldc -124
      //   242: invokevirtual 115	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   245: putfield 138	com/uc/webview/export/internal/setup/UCMPackageInfo$a:d	Ljava/lang/String;
      //   248: aload 6
      //   250: ldc -116
      //   252: invokevirtual 127	org/json/JSONObject:has	(Ljava/lang/String;)Z
      //   255: ifeq +20 -> 275
      //   258: aload 5
      //   260: aload 6
      //   262: ldc -116
      //   264: invokevirtual 130	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   267: ldc -124
      //   269: invokevirtual 115	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   272: putfield 142	com/uc/webview/export/internal/setup/UCMPackageInfo$a:e	Ljava/lang/String;
      //   275: aload 6
      //   277: ldc -112
      //   279: invokevirtual 127	org/json/JSONObject:has	(Ljava/lang/String;)Z
      //   282: ifeq +36 -> 318
      //   285: aload 6
      //   287: ldc -112
      //   289: invokevirtual 130	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   292: astore 6
      //   294: aload 5
      //   296: aload 6
      //   298: ldc -124
      //   300: invokevirtual 115	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   303: putfield 146	com/uc/webview/export/internal/setup/UCMPackageInfo$a:f	Ljava/lang/String;
      //   306: aload 5
      //   308: aload 6
      //   310: ldc -108
      //   312: invokevirtual 115	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   315: putfield 150	com/uc/webview/export/internal/setup/UCMPackageInfo$a:g	Ljava/lang/String;
      //   318: aload 4
      //   320: aload 5
      //   322: invokeinterface 156 2 0
      //   327: pop
      //   328: iload_1
      //   329: iconst_1
      //   330: iadd
      //   331: istore_1
      //   332: goto -186 -> 146
      //   335: aload_3
      //   336: invokestatic 86	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
      //   339: aload_0
      //   340: invokestatic 86	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
      //   343: aload 4
      //   345: areturn
      //   346: astore_2
      //   347: aconst_null
      //   348: astore_0
      //   349: aconst_null
      //   350: astore_3
      //   351: aload_3
      //   352: invokestatic 86	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
      //   355: aload_0
      //   356: invokestatic 86	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
      //   359: aload_2
      //   360: athrow
      //   361: astore_2
      //   362: aconst_null
      //   363: astore_0
      //   364: goto -13 -> 351
      //   367: astore_2
      //   368: goto -17 -> 351
      //   371: astore 4
      //   373: aload_2
      //   374: astore_3
      //   375: aload 4
      //   377: astore_2
      //   378: goto -27 -> 351
      //   381: astore_3
      //   382: aconst_null
      //   383: astore_0
      //   384: goto -283 -> 101
      //   387: astore 4
      //   389: aconst_null
      //   390: astore_0
      //   391: aload_3
      //   392: astore_2
      //   393: aload 4
      //   395: astore_3
      //   396: goto -295 -> 101
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	399	0	paramFile	File
      //   79	253	1	i	int
      //   1	154	2	localObject1	Object
      //   346	14	2	localObject2	Object
      //   361	1	2	localObject3	Object
      //   367	7	2	localObject4	Object
      //   377	16	2	localObject5	Object
      //   59	316	3	localObject6	Object
      //   381	11	3	localException1	Exception
      //   395	1	3	localException2	Exception
      //   9	40	4	localArrayList	ArrayList
      //   94	250	4	localException3	Exception
      //   371	5	4	localObject7	Object
      //   387	7	4	localException4	Exception
      //   168	153	5	localA	a
      //   159	150	6	localJSONObject	org.json.JSONObject
      // Exception table:
      //   from	to	target	type
      //   68	74	94	java/lang/Exception
      //   74	80	94	java/lang/Exception
      //   84	91	94	java/lang/Exception
      //   125	144	94	java/lang/Exception
      //   146	221	94	java/lang/Exception
      //   221	248	94	java/lang/Exception
      //   248	275	94	java/lang/Exception
      //   275	318	94	java/lang/Exception
      //   318	328	94	java/lang/Exception
      //   51	60	346	finally
      //   60	68	361	finally
      //   68	74	367	finally
      //   74	80	367	finally
      //   84	91	367	finally
      //   125	144	367	finally
      //   146	221	367	finally
      //   221	248	367	finally
      //   248	275	367	finally
      //   275	318	367	finally
      //   318	328	367	finally
      //   101	109	371	finally
      //   51	60	381	java/lang/Exception
      //   60	68	387	java/lang/Exception
    }
    
    /* Error */
    public static boolean a(List<a> paramList, File paramFile)
    {
      // Byte code:
      //   0: aload_0
      //   1: invokeinterface 162 1 0
      //   6: ifne +35 -> 41
      //   9: ldc 33
      //   11: new 35	java/lang/StringBuilder
      //   14: dup
      //   15: ldc -92
      //   17: invokespecial 40	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   20: aload_0
      //   21: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   24: ldc -90
      //   26: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   29: aload_1
      //   30: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   33: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   36: invokestatic 59	com/uc/webview/export/internal/utility/Log:i	(Ljava/lang/String;Ljava/lang/String;)V
      //   39: iconst_0
      //   40: ireturn
      //   41: aconst_null
      //   42: astore 4
      //   44: aconst_null
      //   45: astore_3
      //   46: aload 4
      //   48: astore_2
      //   49: new 88	org/json/JSONArray
      //   52: dup
      //   53: invokespecial 167	org/json/JSONArray:<init>	()V
      //   56: astore 5
      //   58: aload 4
      //   60: astore_2
      //   61: aload_0
      //   62: invokeinterface 171 1 0
      //   67: astore 6
      //   69: aload 4
      //   71: astore_2
      //   72: aload 6
      //   74: invokeinterface 176 1 0
      //   79: ifeq +343 -> 422
      //   82: aload 4
      //   84: astore_2
      //   85: aload 6
      //   87: invokeinterface 180 1 0
      //   92: checkcast 2	com/uc/webview/export/internal/setup/UCMPackageInfo$a
      //   95: astore_0
      //   96: aload 4
      //   98: astore_2
      //   99: new 111	org/json/JSONObject
      //   102: dup
      //   103: invokespecial 181	org/json/JSONObject:<init>	()V
      //   106: astore 7
      //   108: aload 4
      //   110: astore_2
      //   111: aload 5
      //   113: aload 7
      //   115: invokevirtual 185	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   118: pop
      //   119: aload 4
      //   121: astore_2
      //   122: aload 7
      //   124: ldc 109
      //   126: aload_0
      //   127: getfield 117	com/uc/webview/export/internal/setup/UCMPackageInfo$a:a	Ljava/lang/String;
      //   130: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   133: pop
      //   134: aload 4
      //   136: astore_2
      //   137: aload 7
      //   139: ldc 119
      //   141: aload_0
      //   142: getfield 121	com/uc/webview/export/internal/setup/UCMPackageInfo$a:b	Ljava/lang/String;
      //   145: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   148: pop
      //   149: aload 4
      //   151: astore_2
      //   152: aload_0
      //   153: getfield 134	com/uc/webview/export/internal/setup/UCMPackageInfo$a:c	Ljava/lang/String;
      //   156: invokestatic 192	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
      //   159: ifne +43 -> 202
      //   162: aload 4
      //   164: astore_2
      //   165: new 111	org/json/JSONObject
      //   168: dup
      //   169: invokespecial 181	org/json/JSONObject:<init>	()V
      //   172: astore 8
      //   174: aload 4
      //   176: astore_2
      //   177: aload 7
      //   179: ldc 123
      //   181: aload 8
      //   183: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   186: pop
      //   187: aload 4
      //   189: astore_2
      //   190: aload 8
      //   192: ldc -124
      //   194: aload_0
      //   195: getfield 134	com/uc/webview/export/internal/setup/UCMPackageInfo$a:c	Ljava/lang/String;
      //   198: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   201: pop
      //   202: aload 4
      //   204: astore_2
      //   205: aload_0
      //   206: getfield 138	com/uc/webview/export/internal/setup/UCMPackageInfo$a:d	Ljava/lang/String;
      //   209: invokestatic 192	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
      //   212: ifne +43 -> 255
      //   215: aload 4
      //   217: astore_2
      //   218: new 111	org/json/JSONObject
      //   221: dup
      //   222: invokespecial 181	org/json/JSONObject:<init>	()V
      //   225: astore 8
      //   227: aload 4
      //   229: astore_2
      //   230: aload 7
      //   232: ldc -120
      //   234: aload 8
      //   236: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   239: pop
      //   240: aload 4
      //   242: astore_2
      //   243: aload 8
      //   245: ldc -124
      //   247: aload_0
      //   248: getfield 138	com/uc/webview/export/internal/setup/UCMPackageInfo$a:d	Ljava/lang/String;
      //   251: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   254: pop
      //   255: aload 4
      //   257: astore_2
      //   258: aload_0
      //   259: getfield 142	com/uc/webview/export/internal/setup/UCMPackageInfo$a:e	Ljava/lang/String;
      //   262: invokestatic 192	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
      //   265: ifne +43 -> 308
      //   268: aload 4
      //   270: astore_2
      //   271: new 111	org/json/JSONObject
      //   274: dup
      //   275: invokespecial 181	org/json/JSONObject:<init>	()V
      //   278: astore 8
      //   280: aload 4
      //   282: astore_2
      //   283: aload 7
      //   285: ldc -116
      //   287: aload 8
      //   289: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   292: pop
      //   293: aload 4
      //   295: astore_2
      //   296: aload 8
      //   298: ldc -124
      //   300: aload_0
      //   301: getfield 142	com/uc/webview/export/internal/setup/UCMPackageInfo$a:e	Ljava/lang/String;
      //   304: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   307: pop
      //   308: aload 4
      //   310: astore_2
      //   311: aload_0
      //   312: getfield 146	com/uc/webview/export/internal/setup/UCMPackageInfo$a:f	Ljava/lang/String;
      //   315: invokestatic 192	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
      //   318: ifne -249 -> 69
      //   321: aload 4
      //   323: astore_2
      //   324: new 111	org/json/JSONObject
      //   327: dup
      //   328: invokespecial 181	org/json/JSONObject:<init>	()V
      //   331: astore 8
      //   333: aload 4
      //   335: astore_2
      //   336: aload 7
      //   338: ldc -112
      //   340: aload 8
      //   342: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   345: pop
      //   346: aload 4
      //   348: astore_2
      //   349: aload 8
      //   351: ldc -124
      //   353: aload_0
      //   354: getfield 146	com/uc/webview/export/internal/setup/UCMPackageInfo$a:f	Ljava/lang/String;
      //   357: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   360: pop
      //   361: aload 4
      //   363: astore_2
      //   364: aload_0
      //   365: getfield 150	com/uc/webview/export/internal/setup/UCMPackageInfo$a:g	Ljava/lang/String;
      //   368: invokestatic 192	com/uc/webview/export/internal/utility/j:a	(Ljava/lang/String;)Z
      //   371: ifeq +40 -> 411
      //   374: ldc -62
      //   376: astore_0
      //   377: aload 4
      //   379: astore_2
      //   380: aload 8
      //   382: ldc -108
      //   384: aload_0
      //   385: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   388: pop
      //   389: goto -320 -> 69
      //   392: astore_1
      //   393: aload_3
      //   394: astore_0
      //   395: aload_0
      //   396: astore_2
      //   397: ldc 33
      //   399: ldc -60
      //   401: aload_1
      //   402: invokestatic 80	com/uc/webview/export/internal/utility/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   405: aload_0
      //   406: invokestatic 86	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
      //   409: iconst_0
      //   410: ireturn
      //   411: aload 4
      //   413: astore_2
      //   414: aload_0
      //   415: getfield 150	com/uc/webview/export/internal/setup/UCMPackageInfo$a:g	Ljava/lang/String;
      //   418: astore_0
      //   419: goto -42 -> 377
      //   422: aload 4
      //   424: astore_2
      //   425: new 198	java/io/FileOutputStream
      //   428: dup
      //   429: aload_1
      //   430: invokespecial 199	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   433: astore_0
      //   434: aload_0
      //   435: aload 5
      //   437: invokevirtual 200	org/json/JSONArray:toString	()Ljava/lang/String;
      //   440: invokevirtual 203	java/lang/String:getBytes	()[B
      //   443: invokevirtual 205	java/io/FileOutputStream:write	([B)V
      //   446: aload_0
      //   447: invokestatic 86	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
      //   450: iconst_1
      //   451: ireturn
      //   452: astore_0
      //   453: aload_2
      //   454: invokestatic 86	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
      //   457: aload_0
      //   458: athrow
      //   459: astore_1
      //   460: aload_0
      //   461: astore_2
      //   462: aload_1
      //   463: astore_0
      //   464: goto -11 -> 453
      //   467: astore_1
      //   468: goto -73 -> 395
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	471	0	paramList	List<a>
      //   0	471	1	paramFile	File
      //   48	414	2	localObject1	Object
      //   45	349	3	localObject2	Object
      //   42	381	4	localObject3	Object
      //   56	380	5	localJSONArray	org.json.JSONArray
      //   67	19	6	localIterator	Iterator
      //   106	231	7	localJSONObject1	org.json.JSONObject
      //   172	209	8	localJSONObject2	org.json.JSONObject
      // Exception table:
      //   from	to	target	type
      //   49	58	392	java/lang/Exception
      //   61	69	392	java/lang/Exception
      //   72	82	392	java/lang/Exception
      //   85	96	392	java/lang/Exception
      //   99	108	392	java/lang/Exception
      //   111	119	392	java/lang/Exception
      //   122	134	392	java/lang/Exception
      //   137	149	392	java/lang/Exception
      //   152	162	392	java/lang/Exception
      //   165	174	392	java/lang/Exception
      //   177	187	392	java/lang/Exception
      //   190	202	392	java/lang/Exception
      //   205	215	392	java/lang/Exception
      //   218	227	392	java/lang/Exception
      //   230	240	392	java/lang/Exception
      //   243	255	392	java/lang/Exception
      //   258	268	392	java/lang/Exception
      //   271	280	392	java/lang/Exception
      //   283	293	392	java/lang/Exception
      //   296	308	392	java/lang/Exception
      //   311	321	392	java/lang/Exception
      //   324	333	392	java/lang/Exception
      //   336	346	392	java/lang/Exception
      //   349	361	392	java/lang/Exception
      //   364	374	392	java/lang/Exception
      //   380	389	392	java/lang/Exception
      //   414	419	392	java/lang/Exception
      //   425	434	392	java/lang/Exception
      //   49	58	452	finally
      //   61	69	452	finally
      //   72	82	452	finally
      //   85	96	452	finally
      //   99	108	452	finally
      //   111	119	452	finally
      //   122	134	452	finally
      //   137	149	452	finally
      //   152	162	452	finally
      //   165	174	452	finally
      //   177	187	452	finally
      //   190	202	452	finally
      //   205	215	452	finally
      //   218	227	452	finally
      //   230	240	452	finally
      //   243	255	452	finally
      //   258	268	452	finally
      //   271	280	452	finally
      //   283	293	452	finally
      //   296	308	452	finally
      //   311	321	452	finally
      //   324	333	452	finally
      //   336	346	452	finally
      //   349	361	452	finally
      //   364	374	452	finally
      //   380	389	452	finally
      //   397	405	452	finally
      //   414	419	452	finally
      //   425	434	452	finally
      //   434	446	459	finally
      //   434	446	467	java/lang/Exception
    }
  }
}
