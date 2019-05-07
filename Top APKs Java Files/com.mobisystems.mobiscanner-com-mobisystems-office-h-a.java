package com.mobisystems.office.h;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private static final Object a = new Object();
  private static a.a b;
  private static long c;
  private static BroadcastReceiver d;
  
  /* Error */
  private static int a(ContentResolver paramContentResolver, String paramString)
  {
    // Byte code:
    //   0: iconst_m1
    //   1: istore_3
    //   2: aconst_null
    //   3: astore 6
    //   5: aconst_null
    //   6: astore 7
    //   8: aload 7
    //   10: astore 5
    //   12: new 29	java/lang/StringBuilder
    //   15: dup
    //   16: ldc 31
    //   18: invokespecial 34	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   21: astore 8
    //   23: aload 7
    //   25: astore 5
    //   27: aload 8
    //   29: aload_1
    //   30: invokevirtual 38	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: pop
    //   34: aload 7
    //   36: astore 5
    //   38: aload 8
    //   40: ldc 40
    //   42: invokevirtual 38	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: aload 7
    //   48: astore 5
    //   50: aload_0
    //   51: aload 8
    //   53: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 50	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   59: aconst_null
    //   60: aconst_null
    //   61: aconst_null
    //   62: aconst_null
    //   63: invokevirtual 56	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   66: astore_0
    //   67: iload_3
    //   68: istore_2
    //   69: aload_0
    //   70: ifnull +72 -> 142
    //   73: iload_3
    //   74: istore_2
    //   75: aload_0
    //   76: invokeinterface 62 1 0
    //   81: ifeq +61 -> 142
    //   84: iload_3
    //   85: istore_2
    //   86: aload_0
    //   87: invokeinterface 66 1 0
    //   92: iconst_1
    //   93: if_icmpne +49 -> 142
    //   96: aload_0
    //   97: iconst_0
    //   98: invokeinterface 70 2 0
    //   103: istore 4
    //   105: iload_3
    //   106: istore_2
    //   107: iload 4
    //   109: iflt +33 -> 142
    //   112: iload 4
    //   114: iconst_2
    //   115: if_icmple +8 -> 123
    //   118: iload_3
    //   119: istore_2
    //   120: goto +22 -> 142
    //   123: iload 4
    //   125: istore_2
    //   126: goto +16 -> 142
    //   129: astore_1
    //   130: aload_0
    //   131: astore 5
    //   133: aload_1
    //   134: astore_0
    //   135: goto +52 -> 187
    //   138: astore_1
    //   139: goto +26 -> 165
    //   142: iload_2
    //   143: istore 4
    //   145: aload_0
    //   146: ifnull +69 -> 215
    //   149: aload_0
    //   150: invokeinterface 73 1 0
    //   155: iload_2
    //   156: ireturn
    //   157: astore_0
    //   158: goto +29 -> 187
    //   161: astore_1
    //   162: aload 6
    //   164: astore_0
    //   165: aload_0
    //   166: astore 5
    //   168: aload_1
    //   169: invokestatic 78	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   172: iload_3
    //   173: istore 4
    //   175: aload_0
    //   176: ifnull +39 -> 215
    //   179: aload_0
    //   180: invokeinterface 73 1 0
    //   185: iconst_m1
    //   186: ireturn
    //   187: aload 5
    //   189: ifnull +10 -> 199
    //   192: aload 5
    //   194: invokeinterface 73 1 0
    //   199: aload_0
    //   200: athrow
    //   201: aconst_null
    //   202: astore_0
    //   203: iload_3
    //   204: istore 4
    //   206: aload_0
    //   207: ifnull +8 -> 215
    //   210: iload_3
    //   211: istore_2
    //   212: goto -63 -> 149
    //   215: iload 4
    //   217: ireturn
    //   218: astore_0
    //   219: goto -18 -> 201
    //   222: astore_1
    //   223: goto -20 -> 203
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	226	0	paramContentResolver	ContentResolver
    //   0	226	1	paramString	String
    //   68	144	2	i	int
    //   1	210	3	j	int
    //   103	113	4	k	int
    //   10	183	5	localObject1	Object
    //   3	160	6	localObject2	Object
    //   6	41	7	localObject3	Object
    //   21	31	8	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   75	84	129	finally
    //   86	105	129	finally
    //   75	84	138	java/lang/Throwable
    //   86	105	138	java/lang/Throwable
    //   12	23	157	finally
    //   27	34	157	finally
    //   38	46	157	finally
    //   50	67	157	finally
    //   168	172	157	finally
    //   12	23	161	java/lang/Throwable
    //   27	34	161	java/lang/Throwable
    //   38	46	161	java/lang/Throwable
    //   50	67	161	java/lang/Throwable
    //   12	23	218	java/lang/SecurityException
    //   27	34	218	java/lang/SecurityException
    //   38	46	218	java/lang/SecurityException
    //   50	67	218	java/lang/SecurityException
    //   75	84	222	java/lang/SecurityException
    //   86	105	222	java/lang/SecurityException
  }
  
  public static void a()
  {
    synchronized (a)
    {
      b = null;
      return;
    }
  }
  
  public static void a(Context paramContext)
  {
    d = new a.1();
    IntentFilter localIntentFilter = new IntentFilter("com.mobisystems.office.license.change");
    if (paramContext != null) {
      paramContext.registerReceiver(d, localIntentFilter);
    }
  }
  
  private static void a(PackageInfo paramPackageInfo, ArrayList<String> paramArrayList)
  {
    if (paramPackageInfo.providers != null)
    {
      paramPackageInfo = paramPackageInfo.providers;
      int j = paramPackageInfo.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramPackageInfo[i];
        if ((localObject != null) && (localObject.authority != null) && (localObject.authority.endsWith(".license"))) {
          paramArrayList.add(localObject.authority);
        }
        i += 1;
      }
    }
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo.activities != null)
    {
      paramPackageInfo = paramPackageInfo.activities;
      int j = paramPackageInfo.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramPackageInfo[i];
        if ((localObject.exported) && ("com.mobisystems.office.GoPremium.GoPremium".equals(localObject.name))) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static final a.a b(Context paramContext)
  {
    synchronized (a)
    {
      if ((b != null) && (System.nanoTime() - c < 1800000000000L))
      {
        paramContext = new a.a(b);
        return paramContext;
      }
      a.a localA = new a.a();
      localA.a = -1;
      ContentResolver localContentResolver = paramContext.getContentResolver();
      try
      {
        paramContext = c(paramContext);
      }
      catch (Throwable paramContext)
      {
        for (;;)
        {
          int j;
          int i;
          String str;
          int k;
          continue;
          i += 1;
        }
      }
      paramContext = new String[0];
      j = paramContext.length;
      i = 0;
      if (i < j)
      {
        str = paramContext[i];
        k = a(localContentResolver, str);
        if (k > localA.a)
        {
          localA.a = k;
          localA.b = str;
        }
      }
      else
      {
        if (localA.a == -1) {
          localA.a = 0;
        }
        if (localA.b != null) {
          paramContext = localA.b;
        }
        try
        {
          localA.b = paramContext.substring(0, paramContext.length() - 8);
        }
        catch (Throwable paramContext)
        {
          for (;;) {}
        }
        localA.b = null;
        b = localA;
        c = System.nanoTime();
        paramContext = new a.a(b);
        return paramContext;
      }
    }
  }
  
  private static String[] c(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    Object localObject = paramContext.getInstalledPackages(0);
    if (localObject != null) {
      localObject = ((List)localObject).iterator();
    }
    for (;;)
    {
      PackageInfo localPackageInfo;
      if (((Iterator)localObject).hasNext())
      {
        localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((localPackageInfo == null) || ((!localPackageInfo.packageName.startsWith("com.mobisystems.office")) && (!localPackageInfo.packageName.startsWith("com.mobisystems.editor")))) {}
      }
      else
      {
        try
        {
          localPackageInfo = paramContext.getPackageInfo(localPackageInfo.packageName, 9);
          if (!a(localPackageInfo)) {
            continue;
          }
          a(localPackageInfo, localArrayList);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
        return (String[])localArrayList.toArray(new String[localArrayList.size()]);
      }
    }
  }
}
