package com.extrainfov2.etc;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.nhnent.perftest.AppProfiling.IOnLoadInformation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ExtraData
{
  static boolean adinfoNo = false;
  static IOnLoadPackageInfo mListener;
  static Map<String, String> processesInfo = new HashMap();
  
  static
  {
    mListener = null;
  }
  
  public ExtraData() {}
  
  public static void collect(Context paramContext, AppProfiling.IOnLoadInformation paramIOnLoadInformation)
  {
    boolean bool = false;
    try
    {
      Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
      bool = true;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    requestInfo(bool, paramContext, paramIOnLoadInformation);
  }
  
  public static String getPackageNames(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject1 = localPackageManager.getInstalledApplications(128);
    getProcesses(paramContext);
    paramContext = new String();
    Iterator localIterator = ((List)localObject1).iterator();
    localObject1 = paramContext;
    if (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      String str = localApplicationInfo.packageName;
      Object localObject2 = new SimpleDateFormat("yyyyMMddHHmmss");
      Date localDate = installTimeFromPackageManager(localPackageManager, str);
      localObject1 = "";
      if (localDate != null) {
        localObject1 = ((SimpleDateFormat)localObject2).format(localDate);
      }
      localObject2 = str;
      if (localApplicationInfo.packageName.length() > 128)
      {
        localObject2 = str.substring(0, 128);
        localObject2 = (String)localObject2 + "_128";
      }
      localObject2 = (String)localObject2 + ":";
      localObject1 = (String)localObject2 + (String)localObject1;
      localObject1 = (String)localObject1 + ":";
      if (processesInfo.containsKey(localApplicationInfo.packageName) != true) {
        break label326;
      }
      localObject1 = (String)localObject1 + (String)processesInfo.get(localApplicationInfo.packageName);
    }
    label326:
    for (;;)
    {
      localObject1 = (String)localObject1 + ",";
      paramContext = paramContext + (String)localObject1;
      if (paramContext.length() > 39800)
      {
        localObject1 = paramContext + "App500";
        return localObject1;
      }
      break;
    }
  }
  
  /* Error */
  private static long getProcessStartTime(int paramInt)
  {
    // Byte code:
    //   0: new 124	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   7: ldc -90
    //   9: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   12: iload_0
    //   13: invokevirtual 169	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   16: ldc -85
    //   18: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   24: astore 4
    //   26: new 173	java/io/File
    //   29: dup
    //   30: aload 4
    //   32: invokespecial 174	java/io/File:<init>	(Ljava/lang/String;)V
    //   35: astore_3
    //   36: aload_3
    //   37: invokevirtual 177	java/io/File:exists	()Z
    //   40: ifeq +11 -> 51
    //   43: aload_3
    //   44: invokevirtual 180	java/io/File:isDirectory	()Z
    //   47: iconst_1
    //   48: if_icmpne +5 -> 53
    //   51: lconst_0
    //   52: lreturn
    //   53: new 74	java/lang/String
    //   56: dup
    //   57: invokespecial 75	java/lang/String:<init>	()V
    //   60: astore_3
    //   61: aconst_null
    //   62: astore 6
    //   64: aconst_null
    //   65: astore 5
    //   67: new 182	java/io/BufferedReader
    //   70: dup
    //   71: new 184	java/io/FileReader
    //   74: dup
    //   75: aload 4
    //   77: invokespecial 185	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   80: invokespecial 188	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   83: astore 4
    //   85: aload 4
    //   87: invokevirtual 191	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   90: astore 5
    //   92: aload 4
    //   94: ifnull +8 -> 102
    //   97: aload 4
    //   99: invokevirtual 194	java/io/BufferedReader:close	()V
    //   102: aload 5
    //   104: astore_3
    //   105: aload_3
    //   106: aload_3
    //   107: ldc -60
    //   109: invokevirtual 200	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   112: invokevirtual 203	java/lang/String:substring	(I)Ljava/lang/String;
    //   115: ldc -51
    //   117: invokevirtual 209	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   120: bipush 20
    //   122: aaload
    //   123: invokestatic 215	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   126: ldc2_w 216
    //   129: lmul
    //   130: invokestatic 223	com/nhnent/perftest/AppProfiling:testEtc	()J
    //   133: ldiv
    //   134: lstore_1
    //   135: lload_1
    //   136: lreturn
    //   137: astore_3
    //   138: aload 5
    //   140: astore_3
    //   141: goto -36 -> 105
    //   144: astore_3
    //   145: aload 5
    //   147: astore_3
    //   148: goto -43 -> 105
    //   151: astore 4
    //   153: aconst_null
    //   154: astore 4
    //   156: aload 4
    //   158: ifnull +8 -> 166
    //   161: aload 4
    //   163: invokevirtual 194	java/io/BufferedReader:close	()V
    //   166: goto -61 -> 105
    //   169: astore 4
    //   171: goto -66 -> 105
    //   174: astore 4
    //   176: goto -71 -> 105
    //   179: astore 4
    //   181: aload 5
    //   183: astore 4
    //   185: aload 4
    //   187: ifnull +8 -> 195
    //   190: aload 4
    //   192: invokevirtual 194	java/io/BufferedReader:close	()V
    //   195: goto -90 -> 105
    //   198: astore 4
    //   200: goto -95 -> 105
    //   203: astore 4
    //   205: goto -100 -> 105
    //   208: astore_3
    //   209: aload 6
    //   211: astore 4
    //   213: aload 4
    //   215: ifnull +8 -> 223
    //   218: aload 4
    //   220: invokevirtual 194	java/io/BufferedReader:close	()V
    //   223: aload_3
    //   224: athrow
    //   225: astore_3
    //   226: new 158	java/io/IOException
    //   229: dup
    //   230: aload_3
    //   231: invokespecial 226	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   234: athrow
    //   235: astore_3
    //   236: lconst_0
    //   237: lreturn
    //   238: astore_3
    //   239: new 158	java/io/IOException
    //   242: dup
    //   243: aload_3
    //   244: invokespecial 226	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   247: athrow
    //   248: astore_3
    //   249: lconst_0
    //   250: lreturn
    //   251: astore 4
    //   253: goto -30 -> 223
    //   256: astore 4
    //   258: goto -35 -> 223
    //   261: astore_3
    //   262: goto -49 -> 213
    //   265: astore 5
    //   267: goto -82 -> 185
    //   270: astore 5
    //   272: goto -116 -> 156
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	275	0	paramInt	int
    //   134	2	1	l	long
    //   35	72	3	localObject1	Object
    //   137	1	3	localIOException1	IOException
    //   140	1	3	localObject2	Object
    //   144	1	3	localNullPointerException1	NullPointerException
    //   147	1	3	localObject3	Object
    //   208	16	3	localObject4	Object
    //   225	6	3	localNumberFormatException	NumberFormatException
    //   235	1	3	localIOException2	IOException
    //   238	6	3	localIndexOutOfBoundsException	IndexOutOfBoundsException
    //   248	1	3	localIOException3	IOException
    //   261	1	3	localObject5	Object
    //   24	74	4	localObject6	Object
    //   151	1	4	localFileNotFoundException1	java.io.FileNotFoundException
    //   154	8	4	localObject7	Object
    //   169	1	4	localIOException4	IOException
    //   174	1	4	localNullPointerException2	NullPointerException
    //   179	1	4	localIOException5	IOException
    //   183	8	4	localObject8	Object
    //   198	1	4	localIOException6	IOException
    //   203	1	4	localNullPointerException3	NullPointerException
    //   211	8	4	localObject9	Object
    //   251	1	4	localIOException7	IOException
    //   256	1	4	localNullPointerException4	NullPointerException
    //   65	117	5	str	String
    //   265	1	5	localIOException8	IOException
    //   270	1	5	localFileNotFoundException2	java.io.FileNotFoundException
    //   62	148	6	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   97	102	137	java/io/IOException
    //   97	102	144	java/lang/NullPointerException
    //   67	85	151	java/io/FileNotFoundException
    //   161	166	169	java/io/IOException
    //   161	166	174	java/lang/NullPointerException
    //   67	85	179	java/io/IOException
    //   190	195	198	java/io/IOException
    //   190	195	203	java/lang/NullPointerException
    //   67	85	208	finally
    //   105	135	225	java/lang/NumberFormatException
    //   226	235	235	java/io/IOException
    //   105	135	238	java/lang/IndexOutOfBoundsException
    //   239	248	248	java/io/IOException
    //   218	223	251	java/io/IOException
    //   218	223	256	java/lang/NullPointerException
    //   85	92	261	finally
    //   85	92	265	java/io/IOException
    //   85	92	270	java/io/FileNotFoundException
  }
  
  public static void getProcesses(Context paramContext)
  {
    if (Build.VERSION.SDK_INT <= 21)
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      if (paramContext != null) {}
    }
    for (;;)
    {
      return;
      Object localObject1 = paramContext.iterator();
      Object localObject2;
      SimpleDateFormat localSimpleDateFormat;
      long l1;
      long l2;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject1).next();
        localSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        l1 = getProcessStartTime(((ActivityManager.RunningAppProcessInfo)localObject2).pid);
        l2 = SystemClock.elapsedRealtime();
        paramContext = "";
        if (l1 != 0L) {
          paramContext = localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis() - (l2 - l1)));
        }
        processesInfo.put(localObject2.pkgList[0], paramContext);
      }
      continue;
      localObject1 = new File("/proc").listFiles();
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        paramContext = localObject1[i];
        if (paramContext.isDirectory()) {}
        for (;;)
        {
          try
          {
            k = Integer.parseInt(paramContext.getName());
          }
          catch (NumberFormatException paramContext)
          {
            int k;
            continue;
          }
          try
          {
            localObject2 = readFile(String.format("/proc/%d/cmdline", new Object[] { Integer.valueOf(k) })).trim();
            if ((!((String)localObject2).isEmpty()) && (((String)localObject2).indexOf('/') == -1) && (((String)localObject2).indexOf(':') == -1) && (((String)localObject2).indexOf('.') != -1))
            {
              localSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
              l1 = getProcessStartTime(k);
              l2 = SystemClock.elapsedRealtime();
              paramContext = "";
              if (l1 != 0L) {
                paramContext = localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis() - (l2 - l1)));
              }
              processesInfo.put(localObject2, paramContext);
            }
          }
          catch (IOException paramContext) {}
        }
        i += 1;
      }
    }
  }
  
  private static Date installTimeFromPackageManager(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager = paramPackageManager.getPackageInfo(paramString, 0);
      paramPackageManager = new Date(PackageInfo.class.getField("firstInstallTime").getLong(paramPackageManager));
      return paramPackageManager;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      return null;
    }
    catch (SecurityException paramPackageManager)
    {
      return null;
    }
    catch (IllegalArgumentException paramPackageManager)
    {
      for (;;) {}
    }
    catch (NoSuchFieldException paramPackageManager)
    {
      for (;;) {}
    }
    catch (IllegalAccessException paramPackageManager)
    {
      for (;;) {}
    }
  }
  
  public static void onComplete(Context paramContext, AppProfiling.IOnLoadInformation paramIOnLoadInformation, String paramString, boolean paramBoolean)
  {
    if (paramIOnLoadInformation == null) {
      return;
    }
    paramIOnLoadInformation.onComplete(paramString, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date()), getPackageNames(paramContext));
  }
  
  public static String readFile(String paramString)
    throws IOException
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localBufferedReader = new BufferedReader(new FileReader(paramString));
      if (localObject == null) {
        break label84;
      }
    }
    finally
    {
      try
      {
        localObject = localBufferedReader.readLine();
        paramString = "";
        while (localObject != null)
        {
          localStringBuilder.append(paramString).append((String)localObject);
          paramString = "\n";
          localObject = localBufferedReader.readLine();
        }
        paramString = localStringBuilder.toString();
        if (localBufferedReader != null) {
          localBufferedReader.close();
        }
        return paramString;
      }
      finally
      {
        for (;;)
        {
          BufferedReader localBufferedReader;
          Object localObject = localBufferedReader;
        }
      }
      paramString = finally;
      localObject = null;
    }
    ((BufferedReader)localObject).close();
    label84:
    throw paramString;
  }
  
  private static void requestInfo(boolean paramBoolean, Context paramContext, final AppProfiling.IOnLoadInformation paramIOnLoadInformation)
  {
    if ((!paramBoolean) || (adinfoNo == true))
    {
      if (paramIOnLoadInformation == null) {
        return;
      }
      requestPackageInfo(new IOnLoadPackageInfo()
      {
        public void onComplete(String paramAnonymousString, boolean paramAnonymousBoolean)
        {
          ExtraData.onComplete(this.val$context, paramIOnLoadInformation, paramAnonymousString, paramAnonymousBoolean);
        }
      });
      return;
    }
    AdInfo.initialize(paramContext);
    AdInfo.requestAdvertisingId(new AdInfo.IOnLoadAdvertisingPolicy()
    {
      public void onComplete(String paramAnonymousString, boolean paramAnonymousBoolean)
      {
        ExtraData.onComplete(this.val$context, paramIOnLoadInformation, paramAnonymousString, paramAnonymousBoolean);
      }
    });
  }
  
  public static void requestPackageInfo(IOnLoadPackageInfo paramIOnLoadPackageInfo)
  {
    setOnLoadPackageInfo(paramIOnLoadPackageInfo);
    new PackageInfoCollector(null).start();
  }
  
  public static void setOnLoadPackageInfo(IOnLoadPackageInfo paramIOnLoadPackageInfo)
  {
    if (mListener != paramIOnLoadPackageInfo) {
      mListener = null;
    }
    mListener = paramIOnLoadPackageInfo;
  }
  
  public static abstract interface IOnLoadPackageInfo
  {
    public abstract void onComplete(String paramString, boolean paramBoolean);
  }
  
  private static class PackageInfoCollector
    extends Thread
  {
    private PackageInfoCollector() {}
    
    public void run()
    {
      if (ExtraData.mListener != null) {
        ExtraData.mListener.onComplete("GooglePlayServiceLibraryNotExist", false);
      }
    }
  }
}
