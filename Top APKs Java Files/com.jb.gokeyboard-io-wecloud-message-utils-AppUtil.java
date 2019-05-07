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
  
  public static ArrayList<String> getAppsWithPushSDK(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Intent localIntent = new Intent("io.wecloud.message.action.PUSH_SERVICE");
    paramContext = paramContext.getPackageManager().queryIntentServices(localIntent, 4).iterator();
    for (;;)
    {
      if (!paramContext.hasNext()) {
        return localArrayList;
      }
      localArrayList.add(((ResolveInfo)paramContext.next()).serviceInfo.packageName);
    }
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
            return paramString.getInt("Channel");
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
    for (;;)
    {
      try
      {
        localObject = new Intent("io.wecloud.message.action.PUSH_SERVICE");
        localObject = paramContext.getPackageManager().queryIntentServices((Intent)localObject, 2).iterator();
        boolean bool = ((Iterator)localObject).hasNext();
        if (bool) {
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        Object localObject;
        String str;
        AppInfo localAppInfo2;
        AppInfo localAppInfo1 = new AppInfo();
        localAppInfo1.mPushSdkVersion = 103;
        localAppInfo1.mPackageName = paramContext.getPackageName();
        localArrayList.add(localAppInfo1);
        continue;
      }
      Collections.sort(localArrayList, new AppInfoComparator());
      return ((AppInfo)localArrayList.get(0)).mPackageName;
      str = ((ResolveInfo)((Iterator)localObject).next()).serviceInfo.packageName;
      localAppInfo2 = new AppInfo();
      localAppInfo2.mPushSdkVersion = getVerCodeFromPkg(paramContext, str);
      localAppInfo2.mPackageName = str;
      localArrayList.add(localAppInfo2);
    }
  }
  
  public static ArrayList<String> getInstalledApps(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    int i;
    if (paramContext != null)
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      i = 0;
      if (i < paramContext.size()) {}
    }
    else
    {
      Collections.sort(localArrayList);
      return localArrayList;
    }
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
    localObject1 = null;
    for (;;)
    {
      try
      {
        localObject2 = paramContext.getSharedPreferences("go_static_id" + paramContext.getPackageName(), 1).getString("go_static_id", null);
        if (localObject2 != null) {
          continue;
        }
        localObject1 = localObject2;
        localObject2 = readGoidFromSdcard();
        localObject1 = localObject2;
        if (localObject2 == null) {
          continue;
        }
        i = 1;
        localObject1 = localObject2;
      }
      catch (Exception localException1)
      {
        Object localObject2;
        Object localObject4;
        localException1.printStackTrace();
        int i = 0;
        continue;
        continue;
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = System.currentTimeMillis() + Machine.getAndroidId(paramContext);
      }
      paramContext.getSharedPreferences("go_static_id" + paramContext.getPackageName(), 1).edit().putString("go_static_id", (String)localObject2).commit();
      localObject4 = localObject2;
      if (i == 0)
      {
        saveGoidToSdcard((String)localObject2);
        localObject4 = localObject2;
      }
      return localObject4;
      localObject1 = localObject2;
      localObject4 = localObject1;
      if (localObject1 == null) {}
      try
      {
        localObject2 = paramContext.getPackageManager().queryIntentActivities(new Intent("com.jiubang.gau.ACTION_GOSTATICSDK"), 0);
        if ((localObject2 == null) || (((List)localObject2).isEmpty())) {
          continue;
        }
        localObject4 = ((List)localObject2).iterator();
        localObject2 = localObject1;
      }
      catch (Exception localException2)
      {
        String str;
        Context localContext;
        continue;
        Object localObject3 = localObject1;
        continue;
        i = 0;
      }
      localObject1 = localObject2;
      if (!((Iterator)localObject4).hasNext())
      {
        localObject1 = localObject2;
        i = 0;
      }
      else
      {
        localObject1 = localObject2;
        str = ((ResolveInfo)((Iterator)localObject4).next()).activityInfo.packageName;
        if (str != null)
        {
          localObject1 = localObject2;
          if (!str.equals(paramContext.getPackageName()))
          {
            localObject1 = localObject2;
            localContext = paramContext.createPackageContext(str, 2);
            if (localContext != null)
            {
              localObject1 = localObject2;
              localObject2 = localContext.getSharedPreferences("go_static_id" + str, 1).getString("go_static_id", null);
              localObject1 = localObject2;
              if (localObject1 == null) {
                continue;
              }
              i = 0;
            }
          }
        }
      }
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
    //   0: new 36	java/io/File
    //   3: dup
    //   4: new 28	java/lang/StringBuilder
    //   7: dup
    //   8: getstatic 60	io/wecloud/message/utils/AppUtil:CACHEDIR	Ljava/lang/String;
    //   11: invokestatic 45	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   14: invokespecial 49	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   17: ldc 9
    //   19: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 58	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokespecial 65	java/io/File:<init>	(Ljava/lang/String;)V
    //   28: astore_3
    //   29: aload_3
    //   30: invokevirtual 69	java/io/File:exists	()Z
    //   33: ifeq +234 -> 267
    //   36: sipush 1024
    //   39: newarray byte
    //   41: astore_2
    //   42: new 412	java/io/FileInputStream
    //   45: dup
    //   46: aload_3
    //   47: invokespecial 415	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   50: astore 5
    //   52: aload 5
    //   54: astore_3
    //   55: aload 5
    //   57: aload_2
    //   58: invokevirtual 416	java/io/FileInputStream:read	([B)I
    //   61: istore_1
    //   62: iload_1
    //   63: ifle +199 -> 262
    //   66: aload 5
    //   68: astore_3
    //   69: iload_1
    //   70: newarray byte
    //   72: astore 4
    //   74: iconst_0
    //   75: istore_0
    //   76: iload_0
    //   77: iload_1
    //   78: if_icmplt +110 -> 188
    //   81: aload 5
    //   83: astore_3
    //   84: new 41	java/lang/String
    //   87: dup
    //   88: aload 4
    //   90: ldc_w 418
    //   93: invokespecial 421	java/lang/String:<init>	([BLjava/lang/String;)V
    //   96: astore 6
    //   98: aload 5
    //   100: astore_3
    //   101: aload 6
    //   103: invokevirtual 275	java/lang/String:trim	()Ljava/lang/String;
    //   106: pop
    //   107: aload 6
    //   109: astore_2
    //   110: aload 4
    //   112: ifnull +69 -> 181
    //   115: aload 6
    //   117: astore 4
    //   119: aload 5
    //   121: astore_3
    //   122: aload 6
    //   124: ldc_w 423
    //   127: invokevirtual 426	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   130: ifeq +19 -> 149
    //   133: aload 5
    //   135: astore_3
    //   136: aload 6
    //   138: ldc_w 423
    //   141: ldc_w 428
    //   144: invokevirtual 431	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   147: astore 4
    //   149: aload 4
    //   151: astore_2
    //   152: aload 5
    //   154: astore_3
    //   155: aload 4
    //   157: ldc_w 433
    //   160: invokevirtual 426	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   163: ifeq +18 -> 181
    //   166: aload 5
    //   168: astore_3
    //   169: aload 4
    //   171: ldc_w 433
    //   174: ldc_w 428
    //   177: invokevirtual 431	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   180: astore_2
    //   181: aload 5
    //   183: invokevirtual 436	java/io/FileInputStream:close	()V
    //   186: aload_2
    //   187: areturn
    //   188: aload 4
    //   190: iload_0
    //   191: aload_2
    //   192: iload_0
    //   193: baload
    //   194: bastore
    //   195: iload_0
    //   196: iconst_1
    //   197: iadd
    //   198: istore_0
    //   199: goto -123 -> 76
    //   202: astore 4
    //   204: aconst_null
    //   205: astore_2
    //   206: aload_2
    //   207: astore_3
    //   208: aload 4
    //   210: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   213: aload_2
    //   214: invokevirtual 436	java/io/FileInputStream:close	()V
    //   217: aconst_null
    //   218: areturn
    //   219: astore_2
    //   220: aload_2
    //   221: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   224: aconst_null
    //   225: areturn
    //   226: astore_2
    //   227: aconst_null
    //   228: astore_3
    //   229: aload_3
    //   230: invokevirtual 436	java/io/FileInputStream:close	()V
    //   233: aload_2
    //   234: athrow
    //   235: astore_3
    //   236: aload_3
    //   237: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   240: goto -7 -> 233
    //   243: astore_3
    //   244: aload_3
    //   245: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   248: aload_2
    //   249: areturn
    //   250: astore_2
    //   251: goto -22 -> 229
    //   254: astore 4
    //   256: aload 5
    //   258: astore_2
    //   259: goto -53 -> 206
    //   262: aconst_null
    //   263: astore_2
    //   264: goto -83 -> 181
    //   267: aconst_null
    //   268: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   75	124	0	i	int
    //   61	18	1	j	int
    //   41	173	2	localObject1	Object
    //   219	2	2	localIOException1	java.io.IOException
    //   226	23	2	str1	String
    //   250	1	2	localObject2	Object
    //   258	6	2	localObject3	Object
    //   28	202	3	localObject4	Object
    //   235	2	3	localIOException2	java.io.IOException
    //   243	2	3	localIOException3	java.io.IOException
    //   72	117	4	localObject5	Object
    //   202	7	4	localIOException4	java.io.IOException
    //   254	1	4	localIOException5	java.io.IOException
    //   50	207	5	localFileInputStream	java.io.FileInputStream
    //   96	41	6	str2	String
    // Exception table:
    //   from	to	target	type
    //   42	52	202	java/io/IOException
    //   213	217	219	java/io/IOException
    //   42	52	226	finally
    //   229	233	235	java/io/IOException
    //   181	186	243	java/io/IOException
    //   55	62	250	finally
    //   69	74	250	finally
    //   84	98	250	finally
    //   101	107	250	finally
    //   122	133	250	finally
    //   136	149	250	finally
    //   155	166	250	finally
    //   169	181	250	finally
    //   208	213	250	finally
    //   55	62	254	java/io/IOException
    //   69	74	254	java/io/IOException
    //   84	98	254	java/io/IOException
    //   101	107	254	java/io/IOException
    //   122	133	254	java/io/IOException
    //   136	149	254	java/io/IOException
    //   155	166	254	java/io/IOException
    //   169	181	254	java/io/IOException
  }
  
  /* Error */
  private static void saveGoidToSdcard(String paramString)
  {
    // Byte code:
    //   0: new 36	java/io/File
    //   3: dup
    //   4: new 28	java/lang/StringBuilder
    //   7: dup
    //   8: getstatic 60	io/wecloud/message/utils/AppUtil:CACHEDIR	Ljava/lang/String;
    //   11: invokestatic 45	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   14: invokespecial 49	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   17: ldc 9
    //   19: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 58	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokespecial 65	java/io/File:<init>	(Ljava/lang/String;)V
    //   28: astore_1
    //   29: aload_1
    //   30: invokevirtual 75	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   33: iconst_0
    //   34: invokestatic 441	io/wecloud/message/utils/AppUtil:createNewFile	(Ljava/lang/String;Z)Ljava/io/File;
    //   37: pop
    //   38: new 443	java/io/FileOutputStream
    //   41: dup
    //   42: aload_1
    //   43: invokespecial 444	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   46: astore_2
    //   47: aload_2
    //   48: astore_1
    //   49: aload_2
    //   50: aload_0
    //   51: ldc_w 418
    //   54: invokevirtual 448	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   57: invokevirtual 451	java/io/FileOutputStream:write	([B)V
    //   60: aload_2
    //   61: ifnull +7 -> 68
    //   64: aload_2
    //   65: invokevirtual 452	java/io/FileOutputStream:close	()V
    //   68: return
    //   69: astore_0
    //   70: aconst_null
    //   71: astore_2
    //   72: aload_2
    //   73: astore_1
    //   74: aload_0
    //   75: invokevirtual 453	java/io/FileNotFoundException:printStackTrace	()V
    //   78: aload_2
    //   79: ifnull -11 -> 68
    //   82: aload_2
    //   83: invokevirtual 452	java/io/FileOutputStream:close	()V
    //   86: return
    //   87: astore_0
    //   88: aload_0
    //   89: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   92: return
    //   93: astore_0
    //   94: aconst_null
    //   95: astore_2
    //   96: aload_2
    //   97: astore_1
    //   98: aload_0
    //   99: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   102: aload_2
    //   103: ifnull -35 -> 68
    //   106: aload_2
    //   107: invokevirtual 452	java/io/FileOutputStream:close	()V
    //   110: return
    //   111: astore_0
    //   112: aload_0
    //   113: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   116: return
    //   117: astore_0
    //   118: aconst_null
    //   119: astore_1
    //   120: aload_1
    //   121: ifnull +7 -> 128
    //   124: aload_1
    //   125: invokevirtual 452	java/io/FileOutputStream:close	()V
    //   128: aload_0
    //   129: athrow
    //   130: astore_1
    //   131: aload_1
    //   132: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   135: goto -7 -> 128
    //   138: astore_0
    //   139: aload_0
    //   140: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   143: return
    //   144: astore_0
    //   145: goto -25 -> 120
    //   148: astore_0
    //   149: goto -53 -> 96
    //   152: astore_0
    //   153: goto -81 -> 72
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	156	0	paramString	String
    //   28	97	1	localObject	Object
    //   130	2	1	localIOException	java.io.IOException
    //   46	61	2	localFileOutputStream	java.io.FileOutputStream
    // Exception table:
    //   from	to	target	type
    //   38	47	69	java/io/FileNotFoundException
    //   82	86	87	java/io/IOException
    //   38	47	93	java/io/IOException
    //   106	110	111	java/io/IOException
    //   38	47	117	finally
    //   124	128	130	java/io/IOException
    //   64	68	138	java/io/IOException
    //   49	60	144	finally
    //   74	78	144	finally
    //   98	102	144	finally
    //   49	60	148	java/io/IOException
    //   49	60	152	java/io/FileNotFoundException
  }
}
