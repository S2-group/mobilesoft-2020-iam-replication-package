package io.wecloud.message.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import io.wecloud.message.bean.AppInfo;
import io.wecloud.message.bussiness.AppInfoComparator;
import io.wecloud.message.log.LogUtil;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AppUtil
{
  public static final String CACHEDIR = Environment.getExternalStorageDirectory().getPath() + "/.goproduct/";
  private static final String GOID_FILE = "goid";
  private static final String GO_STATIC_ACTION = "com.jiubang.gau.ACTION_GOSTATICSDK";
  private static final String GO_STATIC_ID = "go_static_id";
  private static final String LOG_TAG = AppUtil.class.getSimpleName();
  
  public AppUtil() {}
  
  public static void createDir(String paramString)
  {
    paramString = new File(paramString);
    if (paramString.exists()) {
      return;
    }
    File localFile = paramString.getParentFile();
    if (!localFile.exists()) {
      createDir(localFile.getAbsolutePath());
    }
    paramString.mkdir();
  }
  
  public static File createNewFile(String paramString, boolean paramBoolean)
  {
    paramString = new File(paramString);
    if ((!paramBoolean) && (paramString.exists())) {
      paramString.delete();
    }
    if (!paramString.exists()) {}
    try
    {
      createDir(paramString.getParentFile().getAbsolutePath());
      paramString.createNewFile();
      return paramString;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString;
  }
  
  public static String genTargetIntentFilter(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    return "io.wecloud.message.service.AIDL";
  }
  
  public static String getAppKey(Context paramContext, String paramString)
  {
    try
    {
      if (paramContext.getPackageName().equals(paramString)) {}
      while (paramContext != null)
      {
        return getMetaValue(paramContext, "APPKEY");
        paramContext = getRemoteContext(paramContext, paramString);
      }
      return null;
    }
    catch (Throwable paramContext) {}
  }
  
  public static int getApplicationIcon(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        int i = paramContext.getApplicationInfo().icon;
        return i;
      }
      catch (Throwable paramContext) {}
    }
    return -1;
  }
  
  public static ArrayList getAppsWithPushSDK(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Intent localIntent = new Intent("io.wecloud.message.action.PUSH_SERVICE");
    paramContext = paramContext.getPackageManager().queryIntentServices(localIntent, 4).iterator();
    while (paramContext.hasNext()) {
      localArrayList.add(((ResolveInfo)paramContext.next()).serviceInfo.packageName);
    }
    return localArrayList;
  }
  
  public static String getChannelName(Context paramContext, String paramString)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject1 = localObject3;
    for (;;)
    {
      try
      {
        Object localObject2;
        if (paramContext.getPackageName().equals(paramString))
        {
          localObject2 = paramContext;
          paramContext = localObject4;
          if (localObject2 == null) {
            break;
          }
          localObject1 = localObject3;
          paramContext = ((Context)localObject2).getPackageManager().getApplicationInfo(paramString, 128);
          if (paramContext != null)
          {
            localObject1 = localObject3;
            paramString = paramContext.metaData;
            paramContext = localObject4;
            if (paramString == null) {
              break;
            }
            localObject1 = localObject3;
            paramContext = localObject4;
            if (!paramString.containsKey("Channel")) {
              break;
            }
            localObject1 = localObject3;
            localObject2 = paramString.getString("Channel");
            localObject1 = localObject2;
            paramContext = (Context)localObject2;
            if (!TextUtils.isEmpty((CharSequence)localObject2)) {
              break;
            }
            localObject1 = localObject2;
            return paramString.getInt("Channel") + "";
          }
        }
        else
        {
          localObject1 = localObject3;
          localObject2 = getRemoteContext(paramContext, paramString);
          continue;
        }
        paramString = null;
      }
      catch (Throwable paramContext)
      {
        return localObject1;
      }
    }
    return paramContext;
  }
  
  public static String getDoUploadTaskPkgName(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      Object localObject = new Intent("io.wecloud.message.action.PUSH_SERVICE");
      localObject = paramContext.getPackageManager().queryIntentServices((Intent)localObject, 2).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = ((ResolveInfo)((Iterator)localObject).next()).serviceInfo.packageName;
        AppInfo localAppInfo2 = new AppInfo();
        localAppInfo2.mPushSdkVersion = getVerCodeFromPkg(paramContext, str);
        localAppInfo2.mPackageName = str;
        localArrayList.add(localAppInfo2);
      }
      AppInfo localAppInfo1;
      return ((AppInfo)localArrayList.get(0)).mPackageName;
    }
    catch (Throwable localThrowable)
    {
      localAppInfo1 = new AppInfo();
      localAppInfo1.mPushSdkVersion = 103;
      localAppInfo1.mPackageName = paramContext.getPackageName();
      localArrayList.add(localAppInfo1);
      Collections.sort(localArrayList, new AppInfoComparator());
    }
  }
  
  public static ArrayList getInstalledApps(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      if (i < paramContext.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
        if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0) {
          localArrayList.add(localPackageInfo.packageName);
        }
        for (;;)
        {
          i += 1;
          break;
          if ((localPackageInfo.applicationInfo.flags & 0x80) != 0) {
            localArrayList.add(localPackageInfo.packageName);
          }
        }
      }
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  public static String getMetaValue(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {
      return null;
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
        if (paramContext != null)
        {
          paramContext = paramContext.metaData;
          if (paramContext == null) {
            break;
          }
          paramContext = paramContext.getString(paramString);
          return paramContext;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        return null;
      }
      paramContext = null;
    }
  }
  
  public static Context getRemoteContext(Context paramContext, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null)
    {
      localObject1 = localObject2;
      if (TextUtils.isEmpty(paramString)) {}
    }
    try
    {
      localObject1 = paramContext.createPackageContext(paramString, 3);
      return localObject1;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static int getResIdByName(Context paramContext, String paramString1, String paramString2)
  {
    int j = -1;
    int i = j;
    String str;
    if (paramContext != null)
    {
      i = j;
      if (!TextUtils.isEmpty(paramString1))
      {
        i = j;
        if (!TextUtils.isEmpty(paramString2)) {
          str = paramString1.trim() + ":" + paramString2;
        }
      }
    }
    try
    {
      i = paramContext.getResources().getIdentifier(str, null, null);
      return i;
    }
    catch (Exception paramContext)
    {
      LogUtil.e(LOG_TAG, "get resId EXCEPTION, pkgName = " + paramString1 + " recName = " + paramString2, paramContext);
    }
    return -1;
  }
  
  public static boolean getSharedPushServiceValue(Context paramContext, String paramString)
  {
    if (paramContext.getPackageName().equals(paramString)) {}
    for (;;)
    {
      return false;
      try
      {
        paramContext = getRemoteContext(paramContext, paramString);
        if (paramContext != null)
        {
          paramContext = getMetaValue(paramContext, "ShareChannel");
          if (!TextUtils.isEmpty(paramContext))
          {
            boolean bool = paramContext.equalsIgnoreCase("on");
            if (bool) {
              return true;
            }
          }
        }
      }
      catch (Throwable paramContext)
      {
        LogUtil.e(LOG_TAG, "Exception occured, while getting the configuration of sharing push service, pkgname = " + paramString, paramContext);
      }
    }
    return false;
  }
  
  @SuppressLint({"WorldReadableFiles"})
  public static String getToken(Context paramContext)
  {
    localObject2 = null;
    try
    {
      localObject1 = paramContext.getSharedPreferences("go_static_id" + paramContext.getPackageName(), 1).getString("go_static_id", null);
      if (localObject1 != null) {
        break label148;
      }
      localObject2 = localObject1;
      localObject1 = readGoidFromSdcard();
      localObject2 = localObject1;
      localObject1 = localObject2;
      if (localObject2 == null) {
        break label148;
      }
      i = 1;
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        localException1.printStackTrace();
        int i = 0;
      }
    }
    localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = System.currentTimeMillis() + Machine.getAndroidId(paramContext);
    }
    paramContext.getSharedPreferences("go_static_id" + paramContext.getPackageName(), 1).edit().putString("go_static_id", (String)localObject1).commit();
    localObject2 = localObject1;
    if (i == 0)
    {
      saveGoidToSdcard((String)localObject1);
      localObject2 = localObject1;
    }
    label148:
    do
    {
      return localObject2;
      localObject2 = localObject1;
    } while (localObject1 != null);
    for (;;)
    {
      try
      {
        Object localObject3 = paramContext.getPackageManager().queryIntentActivities(new Intent("com.jiubang.gau.ACTION_GOSTATICSDK"), 0);
        localObject2 = localObject1;
        if (localObject3 != null)
        {
          localObject2 = localObject1;
          if (!((List)localObject3).isEmpty())
          {
            localObject3 = ((List)localObject3).iterator();
            localObject2 = localObject1;
            if (!((Iterator)localObject3).hasNext()) {
              continue;
            }
            localObject2 = localObject1;
            String str = ((ResolveInfo)((Iterator)localObject3).next()).activityInfo.packageName;
            if (str == null) {
              continue;
            }
            localObject2 = localObject1;
            if (str.equals(paramContext.getPackageName())) {
              continue;
            }
            localObject2 = localObject1;
            Context localContext = paramContext.createPackageContext(str, 2);
            if (localContext == null) {
              continue;
            }
            localObject2 = localObject1;
            localObject1 = localContext.getSharedPreferences("go_static_id" + str, 1).getString("go_static_id", null);
            localObject2 = localObject1;
            localObject1 = localObject2;
            if (localObject2 == null) {
              continue;
            }
          }
        }
      }
      catch (Exception localException2)
      {
        localObject2 = localObject1;
        continue;
        localObject2 = localObject1;
        continue;
      }
      i = 0;
      break;
    }
  }
  
  public static int getVerCodeFromPkg(Context paramContext, String paramString)
  {
    int i = -1;
    try
    {
      paramContext = getRemoteContext(paramContext, paramString);
      if (paramContext != null)
      {
        i = getResIdByName(paramContext, paramString, "raw/version_code");
        paramContext = paramContext.getResources().openRawResource(i);
        byte[] arrayOfByte = new byte[paramContext.available()];
        paramContext.read(arrayOfByte);
        i = Integer.parseInt(new String(arrayOfByte));
      }
      return i;
    }
    catch (Throwable paramContext)
    {
      LogUtil.e(LOG_TAG, "Exception occured, while getting version code from app, pkgname = " + paramString, paramContext);
    }
    return -1;
  }
  
  public static int getVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return 1;
  }
  
  public static String getVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "1";
  }
  
  /* Error */
  private static String readGoidFromSdcard()
  {
    // Byte code:
    //   0: new 39	java/io/File
    //   3: dup
    //   4: new 28	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 31	java/lang/StringBuilder:<init>	()V
    //   11: getstatic 53	io/wecloud/message/utils/AppUtil:CACHEDIR	Ljava/lang/String;
    //   14: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: ldc 9
    //   19: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
    //   28: astore_3
    //   29: aload_3
    //   30: invokevirtual 63	java/io/File:exists	()Z
    //   33: ifeq +216 -> 249
    //   36: sipush 1024
    //   39: newarray byte
    //   41: astore_2
    //   42: new 408	java/io/FileInputStream
    //   45: dup
    //   46: aload_3
    //   47: invokespecial 411	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   50: astore 5
    //   52: aload 5
    //   54: astore_3
    //   55: aload 5
    //   57: aload_2
    //   58: invokevirtual 412	java/io/FileInputStream:read	([B)I
    //   61: istore_1
    //   62: iload_1
    //   63: ifle +181 -> 244
    //   66: aload 5
    //   68: astore_3
    //   69: iload_1
    //   70: newarray byte
    //   72: astore 4
    //   74: iconst_0
    //   75: istore_0
    //   76: goto +175 -> 251
    //   79: aload 5
    //   81: astore_3
    //   82: new 107	java/lang/String
    //   85: dup
    //   86: aload 4
    //   88: ldc_w 414
    //   91: invokespecial 417	java/lang/String:<init>	([BLjava/lang/String;)V
    //   94: astore 6
    //   96: aload 5
    //   98: astore_3
    //   99: aload 6
    //   101: invokevirtual 271	java/lang/String:trim	()Ljava/lang/String;
    //   104: pop
    //   105: aload 6
    //   107: astore_2
    //   108: aload 4
    //   110: ifnull +67 -> 177
    //   113: aload 6
    //   115: astore 4
    //   117: aload 5
    //   119: astore_3
    //   120: aload 6
    //   122: ldc_w 419
    //   125: invokevirtual 422	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   128: ifeq +18 -> 146
    //   131: aload 5
    //   133: astore_3
    //   134: aload 6
    //   136: ldc_w 419
    //   139: ldc -47
    //   141: invokevirtual 425	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   144: astore 4
    //   146: aload 4
    //   148: astore_2
    //   149: aload 5
    //   151: astore_3
    //   152: aload 4
    //   154: ldc_w 427
    //   157: invokevirtual 422	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   160: ifeq +17 -> 177
    //   163: aload 5
    //   165: astore_3
    //   166: aload 4
    //   168: ldc_w 427
    //   171: ldc -47
    //   173: invokevirtual 425	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   176: astore_2
    //   177: aload 5
    //   179: invokevirtual 430	java/io/FileInputStream:close	()V
    //   182: aload_2
    //   183: areturn
    //   184: astore_3
    //   185: aload_3
    //   186: invokevirtual 431	java/io/IOException:printStackTrace	()V
    //   189: aload_2
    //   190: areturn
    //   191: astore 4
    //   193: aconst_null
    //   194: astore_2
    //   195: aload_2
    //   196: astore_3
    //   197: aload 4
    //   199: invokevirtual 431	java/io/IOException:printStackTrace	()V
    //   202: aload_2
    //   203: invokevirtual 430	java/io/FileInputStream:close	()V
    //   206: aconst_null
    //   207: areturn
    //   208: astore_2
    //   209: aload_2
    //   210: invokevirtual 431	java/io/IOException:printStackTrace	()V
    //   213: aconst_null
    //   214: areturn
    //   215: astore_2
    //   216: aconst_null
    //   217: astore_3
    //   218: aload_3
    //   219: invokevirtual 430	java/io/FileInputStream:close	()V
    //   222: aload_2
    //   223: athrow
    //   224: astore_3
    //   225: aload_3
    //   226: invokevirtual 431	java/io/IOException:printStackTrace	()V
    //   229: goto -7 -> 222
    //   232: astore_2
    //   233: goto -15 -> 218
    //   236: astore 4
    //   238: aload 5
    //   240: astore_2
    //   241: goto -46 -> 195
    //   244: aconst_null
    //   245: astore_2
    //   246: goto -69 -> 177
    //   249: aconst_null
    //   250: areturn
    //   251: iload_0
    //   252: iload_1
    //   253: if_icmpge -174 -> 79
    //   256: aload 4
    //   258: iload_0
    //   259: aload_2
    //   260: iload_0
    //   261: baload
    //   262: bastore
    //   263: iload_0
    //   264: iconst_1
    //   265: iadd
    //   266: istore_0
    //   267: goto -16 -> 251
    // Local variable table:
    //   start	length	slot	name	signature
    //   75	192	0	i	int
    //   61	193	1	j	int
    //   41	162	2	localObject1	Object
    //   208	2	2	localIOException1	java.io.IOException
    //   215	8	2	localObject2	Object
    //   232	1	2	localObject3	Object
    //   240	20	2	localObject4	Object
    //   28	138	3	localObject5	Object
    //   184	2	3	localIOException2	java.io.IOException
    //   196	23	3	localObject6	Object
    //   224	2	3	localIOException3	java.io.IOException
    //   72	95	4	localObject7	Object
    //   191	7	4	localIOException4	java.io.IOException
    //   236	21	4	localIOException5	java.io.IOException
    //   50	189	5	localFileInputStream	java.io.FileInputStream
    //   94	41	6	str	String
    // Exception table:
    //   from	to	target	type
    //   177	182	184	java/io/IOException
    //   42	52	191	java/io/IOException
    //   202	206	208	java/io/IOException
    //   42	52	215	finally
    //   218	222	224	java/io/IOException
    //   55	62	232	finally
    //   69	74	232	finally
    //   82	96	232	finally
    //   99	105	232	finally
    //   120	131	232	finally
    //   134	146	232	finally
    //   152	163	232	finally
    //   166	177	232	finally
    //   197	202	232	finally
    //   55	62	236	java/io/IOException
    //   69	74	236	java/io/IOException
    //   82	96	236	java/io/IOException
    //   99	105	236	java/io/IOException
    //   120	131	236	java/io/IOException
    //   134	146	236	java/io/IOException
    //   152	163	236	java/io/IOException
    //   166	177	236	java/io/IOException
  }
  
  /* Error */
  private static void saveGoidToSdcard(String paramString)
  {
    // Byte code:
    //   0: new 39	java/io/File
    //   3: dup
    //   4: new 28	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 31	java/lang/StringBuilder:<init>	()V
    //   11: getstatic 53	io/wecloud/message/utils/AppUtil:CACHEDIR	Ljava/lang/String;
    //   14: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: ldc 9
    //   19: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
    //   28: astore_1
    //   29: aload_1
    //   30: invokevirtual 69	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   33: iconst_0
    //   34: invokestatic 435	io/wecloud/message/utils/AppUtil:createNewFile	(Ljava/lang/String;Z)Ljava/io/File;
    //   37: pop
    //   38: new 437	java/io/FileOutputStream
    //   41: dup
    //   42: aload_1
    //   43: invokespecial 438	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   46: astore_2
    //   47: aload_2
    //   48: astore_1
    //   49: aload_2
    //   50: aload_0
    //   51: ldc_w 414
    //   54: invokevirtual 442	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   57: invokevirtual 445	java/io/FileOutputStream:write	([B)V
    //   60: aload_2
    //   61: ifnull +7 -> 68
    //   64: aload_2
    //   65: invokevirtual 446	java/io/FileOutputStream:close	()V
    //   68: return
    //   69: astore_0
    //   70: aload_0
    //   71: invokevirtual 431	java/io/IOException:printStackTrace	()V
    //   74: return
    //   75: astore_0
    //   76: aconst_null
    //   77: astore_2
    //   78: aload_2
    //   79: astore_1
    //   80: aload_0
    //   81: invokevirtual 447	java/io/FileNotFoundException:printStackTrace	()V
    //   84: aload_2
    //   85: ifnull -17 -> 68
    //   88: aload_2
    //   89: invokevirtual 446	java/io/FileOutputStream:close	()V
    //   92: return
    //   93: astore_0
    //   94: aload_0
    //   95: invokevirtual 431	java/io/IOException:printStackTrace	()V
    //   98: return
    //   99: astore_0
    //   100: aconst_null
    //   101: astore_2
    //   102: aload_2
    //   103: astore_1
    //   104: aload_0
    //   105: invokevirtual 431	java/io/IOException:printStackTrace	()V
    //   108: aload_2
    //   109: ifnull -41 -> 68
    //   112: aload_2
    //   113: invokevirtual 446	java/io/FileOutputStream:close	()V
    //   116: return
    //   117: astore_0
    //   118: aload_0
    //   119: invokevirtual 431	java/io/IOException:printStackTrace	()V
    //   122: return
    //   123: astore_0
    //   124: aconst_null
    //   125: astore_1
    //   126: aload_1
    //   127: ifnull +7 -> 134
    //   130: aload_1
    //   131: invokevirtual 446	java/io/FileOutputStream:close	()V
    //   134: aload_0
    //   135: athrow
    //   136: astore_1
    //   137: aload_1
    //   138: invokevirtual 431	java/io/IOException:printStackTrace	()V
    //   141: goto -7 -> 134
    //   144: astore_0
    //   145: goto -19 -> 126
    //   148: astore_0
    //   149: goto -47 -> 102
    //   152: astore_0
    //   153: goto -75 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	156	0	paramString	String
    //   28	103	1	localObject	Object
    //   136	2	1	localIOException	java.io.IOException
    //   46	67	2	localFileOutputStream	java.io.FileOutputStream
    // Exception table:
    //   from	to	target	type
    //   64	68	69	java/io/IOException
    //   38	47	75	java/io/FileNotFoundException
    //   88	92	93	java/io/IOException
    //   38	47	99	java/io/IOException
    //   112	116	117	java/io/IOException
    //   38	47	123	finally
    //   130	134	136	java/io/IOException
    //   49	60	144	finally
    //   80	84	144	finally
    //   104	108	144	finally
    //   49	60	148	java/io/IOException
    //   49	60	152	java/io/FileNotFoundException
  }
}
