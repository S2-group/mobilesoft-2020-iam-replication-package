package com.symantec.android;

import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class b
{
  private static final Pattern b = Pattern.compile("^(package:)(.+)$");
  private final Context a;
  
  public b(Context paramContext)
  {
    this.a = paramContext.getApplicationContext();
  }
  
  /* Error */
  private Collection<String> a(boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: new 38	java/util/ArrayList
    //   9: dup
    //   10: invokespecial 39	java/util/ArrayList:<init>	()V
    //   13: astore 7
    //   15: iload_1
    //   16: ifeq +149 -> 165
    //   19: ldc 41
    //   21: astore_3
    //   22: invokestatic 47	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   25: aload_3
    //   26: invokevirtual 51	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   29: astore_3
    //   30: new 53	java/io/BufferedReader
    //   33: dup
    //   34: new 55	java/io/InputStreamReader
    //   37: dup
    //   38: aload_3
    //   39: invokevirtual 61	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   42: ldc 63
    //   44: invokestatic 69	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   47: invokespecial 72	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
    //   50: invokespecial 75	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   53: astore 4
    //   55: aload 4
    //   57: invokevirtual 79	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   60: astore 5
    //   62: aload 5
    //   64: ifnull +107 -> 171
    //   67: getstatic 20	com/symantec/android/b:b	Ljava/util/regex/Pattern;
    //   70: aload 5
    //   72: invokevirtual 83	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   75: astore 5
    //   77: aload 5
    //   79: invokevirtual 89	java/util/regex/Matcher:matches	()Z
    //   82: ifeq -27 -> 55
    //   85: aload 5
    //   87: iconst_2
    //   88: invokevirtual 93	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   91: astore 5
    //   93: iload_2
    //   94: ifne +18 -> 112
    //   97: aload 5
    //   99: aload_0
    //   100: getfield 33	com/symantec/android/b:a	Landroid/content/Context;
    //   103: invokevirtual 96	android/content/Context:getPackageName	()Ljava/lang/String;
    //   106: invokevirtual 102	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   109: ifne -54 -> 55
    //   112: aload 7
    //   114: aload 5
    //   116: invokeinterface 107 2 0
    //   121: pop
    //   122: goto -67 -> 55
    //   125: astore 6
    //   127: aload 4
    //   129: astore 5
    //   131: aload 6
    //   133: astore 4
    //   135: ldc 109
    //   137: ldc 111
    //   139: aload 4
    //   141: invokestatic 116	com/symantec/symlog/b:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   144: aload 5
    //   146: ifnull +8 -> 154
    //   149: aload 5
    //   151: invokevirtual 119	java/io/BufferedReader:close	()V
    //   154: aload_3
    //   155: ifnull +7 -> 162
    //   158: aload_3
    //   159: invokevirtual 122	java/lang/Process:destroy	()V
    //   162: aload 7
    //   164: areturn
    //   165: ldc 124
    //   167: astore_3
    //   168: goto -146 -> 22
    //   171: aload 4
    //   173: ifnull +8 -> 181
    //   176: aload 4
    //   178: invokevirtual 119	java/io/BufferedReader:close	()V
    //   181: aload_3
    //   182: ifnull -20 -> 162
    //   185: aload_3
    //   186: invokevirtual 122	java/lang/Process:destroy	()V
    //   189: aload 7
    //   191: areturn
    //   192: astore 4
    //   194: aconst_null
    //   195: astore_3
    //   196: aload 6
    //   198: astore 5
    //   200: aload 5
    //   202: ifnull +8 -> 210
    //   205: aload 5
    //   207: invokevirtual 119	java/io/BufferedReader:close	()V
    //   210: aload_3
    //   211: ifnull +7 -> 218
    //   214: aload_3
    //   215: invokevirtual 122	java/lang/Process:destroy	()V
    //   218: aload 4
    //   220: athrow
    //   221: astore 4
    //   223: goto -42 -> 181
    //   226: astore 4
    //   228: goto -74 -> 154
    //   231: astore 5
    //   233: goto -23 -> 210
    //   236: astore 4
    //   238: aload 6
    //   240: astore 5
    //   242: goto -42 -> 200
    //   245: astore 6
    //   247: aload 4
    //   249: astore 5
    //   251: aload 6
    //   253: astore 4
    //   255: goto -55 -> 200
    //   258: astore 4
    //   260: goto -60 -> 200
    //   263: astore 4
    //   265: aconst_null
    //   266: astore_3
    //   267: goto -132 -> 135
    //   270: astore 4
    //   272: goto -137 -> 135
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	275	0	this	b
    //   0	275	1	paramBoolean1	boolean
    //   0	275	2	paramBoolean2	boolean
    //   21	246	3	localObject1	Object
    //   53	124	4	localObject2	Object
    //   192	27	4	localObject3	Object
    //   221	1	4	localIOException1	java.io.IOException
    //   226	1	4	localIOException2	java.io.IOException
    //   236	12	4	localObject4	Object
    //   253	1	4	localObject5	Object
    //   258	1	4	localObject6	Object
    //   263	1	4	localIOException3	java.io.IOException
    //   270	1	4	localIOException4	java.io.IOException
    //   4	202	5	localObject7	Object
    //   231	1	5	localIOException5	java.io.IOException
    //   240	10	5	localObject8	Object
    //   1	1	6	localObject9	Object
    //   125	114	6	localIOException6	java.io.IOException
    //   245	7	6	localObject10	Object
    //   13	177	7	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   55	62	125	java/io/IOException
    //   67	93	125	java/io/IOException
    //   97	112	125	java/io/IOException
    //   112	122	125	java/io/IOException
    //   22	30	192	finally
    //   176	181	221	java/io/IOException
    //   149	154	226	java/io/IOException
    //   205	210	231	java/io/IOException
    //   30	55	236	finally
    //   55	62	245	finally
    //   67	93	245	finally
    //   97	112	245	finally
    //   112	122	245	finally
    //   135	144	258	finally
    //   22	30	263	java/io/IOException
    //   30	55	270	java/io/IOException
  }
  
  private static void a(IPackageDataObserver paramIPackageDataObserver)
  {
    try
    {
      paramIPackageDataObserver.onRemoveCompleted(null, false);
      return;
    }
    catch (RemoteException paramIPackageDataObserver) {}
  }
  
  private static void a(IPackageStatsObserver paramIPackageStatsObserver)
  {
    try
    {
      paramIPackageStatsObserver.onGetStatsCompleted(null, false);
      return;
    }
    catch (RemoteException paramIPackageStatsObserver) {}
  }
  
  private boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x81) != 0;
  }
  
  @SuppressLint({"NewApi"})
  private void b(String paramString, IPackageStatsObserver paramIPackageStatsObserver)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      try
      {
        PackageManager localPackageManager = this.a.getPackageManager();
        int i = ((Integer)UserHandle.class.getDeclaredMethod("myUserId", new Class[0]).invoke(localPackageManager, new Object[0])).intValue();
        localPackageManager.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, Integer.TYPE, IPackageStatsObserver.class }).invoke(localPackageManager, new Object[] { paramString, Integer.valueOf(i), paramIPackageStatsObserver });
        return;
      }
      catch (NoSuchMethodException paramString)
      {
        a(paramIPackageStatsObserver);
        return;
      }
      catch (IllegalAccessException paramString)
      {
        a(paramIPackageStatsObserver);
        return;
      }
      catch (InvocationTargetException paramString)
      {
        a(paramIPackageStatsObserver);
        return;
      }
    }
    a(paramIPackageStatsObserver);
  }
  
  @SuppressLint({"NewApi"})
  private boolean b(ApplicationInfo paramApplicationInfo)
  {
    return (Build.VERSION.SDK_INT >= 17) && ((paramApplicationInfo.flags & 0x1000000) != 0);
  }
  
  private boolean c(ApplicationInfo paramApplicationInfo)
  {
    return TextUtils.equals(this.a.getPackageName(), paramApplicationInfo.packageName);
  }
  
  public Collection<String> a()
  {
    return a(false, false, false);
  }
  
  public Collection<String> a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = this.a.getPackageManager().getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((paramBoolean1) || (!a(localApplicationInfo))) && ((paramBoolean2) || (!b(localApplicationInfo))) && ((paramBoolean3) || (!c(localApplicationInfo)))) {
          localArrayList.add(localApplicationInfo.packageName);
        }
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localArrayList.clear();
      com.symantec.symlog.b.b("PackageManagerHelper", "Exception caught while fetching installed applications from API ", localException);
      if (localArrayList.isEmpty()) {
        return a(paramBoolean1, paramBoolean3);
      }
    }
  }
  
  public void a(long paramLong, IPackageDataObserver paramIPackageDataObserver)
  {
    int i = 1;
    try
    {
      PackageManager localPackageManager = this.a.getPackageManager();
      localPackageManager.getClass().getMethod("freeStorageAndNotify", new Class[] { Long.TYPE, IPackageDataObserver.class }).invoke(localPackageManager, new Object[] { Long.valueOf(paramLong), paramIPackageDataObserver });
      if (i == 0) {
        a(paramIPackageDataObserver);
      }
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        com.symantec.symlog.b.a("PackageManagerHelper", "Reflection method not found " + localNoSuchMethodException);
        i = 0;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        com.symantec.symlog.b.a("PackageManagerHelper", "Access to method is not allowed " + localIllegalAccessException);
        i = 0;
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        com.symantec.symlog.b.a("PackageManagerHelper", "Exception is thrown by called method " + localInvocationTargetException);
        i = 0;
      }
    }
  }
  
  public void a(String paramString, IPackageStatsObserver paramIPackageStatsObserver)
  {
    try
    {
      PackageManager localPackageManager = this.a.getPackageManager();
      localPackageManager.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class }).invoke(localPackageManager, new Object[] { paramString, paramIPackageStatsObserver });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      b(paramString, paramIPackageStatsObserver);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      b(paramString, paramIPackageStatsObserver);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      b(paramString, paramIPackageStatsObserver);
    }
  }
  
  public boolean a(String paramString)
  {
    return this.a.getSharedPreferences("com.symantec.cleansweep.uninstalledApps", 0).getBoolean(paramString, false);
  }
  
  public void b(String paramString)
  {
    SharedPreferences.Editor localEditor = this.a.getSharedPreferences("com.symantec.cleansweep.uninstalledApps", 0).edit();
    localEditor.putBoolean(paramString, true);
    localEditor.apply();
  }
  
  public boolean c(String paramString)
  {
    List localList = ((DevicePolicyManager)this.a.getSystemService("device_policy")).getActiveAdmins();
    if (localList == null) {}
    for (;;)
    {
      return false;
      if (!localList.isEmpty())
      {
        int i = 0;
        while (i < localList.size())
        {
          if (paramString.equals(((ComponentName)localList.get(i)).getPackageName())) {
            return true;
          }
          i += 1;
        }
      }
    }
  }
  
  public String d(String paramString)
  {
    try
    {
      Object localObject = this.a.getPackageManager();
      localObject = ((PackageManager)localObject).getApplicationLabel(((PackageManager)localObject).getApplicationInfo(paramString, 0)).toString();
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      com.symantec.symlog.b.c("PackageManagerHelper", "failed to resolve app name for package name = " + paramString, localNameNotFoundException);
    }
    return null;
  }
  
  public Drawable e(String paramString)
  {
    try
    {
      Object localObject = this.a.getPackageManager();
      localObject = ((PackageManager)localObject).getApplicationIcon(((PackageManager)localObject).getApplicationInfo(paramString, 0));
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      com.symantec.symlog.b.c("PackageManagerHelper", "failed to resolve icon for package name = " + paramString, localNameNotFoundException);
    }
    return null;
  }
}
