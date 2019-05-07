package com.mobisystems.office.a;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static final Object cMq = new Object();
  private static a cMr = null;
  private static long cMs = 0L;
  private static BroadcastReceiver cMt = null;
  
  /* Error */
  private static int a(ContentResolver paramContentResolver, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_0
    //   7: new 38	java/lang/StringBuilder
    //   10: dup
    //   11: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   14: ldc 41
    //   16: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: aload_1
    //   20: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: ldc 47
    //   25: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   31: invokestatic 57	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   34: aconst_null
    //   35: aconst_null
    //   36: aconst_null
    //   37: aconst_null
    //   38: invokevirtual 63	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   41: astore_0
    //   42: aload_0
    //   43: ifnull +127 -> 170
    //   46: aload_0
    //   47: invokeinterface 69 1 0
    //   52: ifeq +118 -> 170
    //   55: aload_0
    //   56: invokeinterface 73 1 0
    //   61: iconst_1
    //   62: if_icmpne +108 -> 170
    //   65: aload_0
    //   66: iconst_0
    //   67: invokeinterface 77 2 0
    //   72: istore_3
    //   73: iload_3
    //   74: iflt +10 -> 84
    //   77: iload_3
    //   78: istore_2
    //   79: iload_3
    //   80: iconst_2
    //   81: if_icmple +5 -> 86
    //   84: iconst_m1
    //   85: istore_2
    //   86: aload_0
    //   87: ifnull +9 -> 96
    //   90: aload_0
    //   91: invokeinterface 80 1 0
    //   96: iload_2
    //   97: ireturn
    //   98: astore_0
    //   99: aconst_null
    //   100: astore_0
    //   101: aload_0
    //   102: ifnull +66 -> 168
    //   105: aload_0
    //   106: invokeinterface 80 1 0
    //   111: iconst_m1
    //   112: ireturn
    //   113: astore_1
    //   114: aload 5
    //   116: astore_0
    //   117: aload_0
    //   118: astore 4
    //   120: aload_1
    //   121: invokevirtual 83	java/lang/Throwable:printStackTrace	()V
    //   124: aload_0
    //   125: ifnull +43 -> 168
    //   128: aload_0
    //   129: invokeinterface 80 1 0
    //   134: iconst_m1
    //   135: ireturn
    //   136: astore_0
    //   137: aload 4
    //   139: ifnull +10 -> 149
    //   142: aload 4
    //   144: invokeinterface 80 1 0
    //   149: aload_0
    //   150: athrow
    //   151: astore_1
    //   152: aload_0
    //   153: astore 4
    //   155: aload_1
    //   156: astore_0
    //   157: goto -20 -> 137
    //   160: astore_1
    //   161: goto -44 -> 117
    //   164: astore_1
    //   165: goto -64 -> 101
    //   168: iconst_m1
    //   169: ireturn
    //   170: iconst_m1
    //   171: istore_2
    //   172: goto -86 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	175	0	paramContentResolver	ContentResolver
    //   0	175	1	paramString	String
    //   78	94	2	i	int
    //   72	10	3	j	int
    //   1	153	4	localContentResolver	ContentResolver
    //   4	111	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   6	42	98	java/lang/SecurityException
    //   6	42	113	java/lang/Throwable
    //   6	42	136	finally
    //   120	124	136	finally
    //   46	73	151	finally
    //   46	73	160	java/lang/Throwable
    //   46	73	164	java/lang/SecurityException
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
    boolean bool2 = false;
    boolean bool1 = bool2;
    int j;
    int i;
    if (paramPackageInfo.activities != null)
    {
      paramPackageInfo = paramPackageInfo.activities;
      j = paramPackageInfo.length;
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        Object localObject = paramPackageInfo[i];
        if ((localObject.exported) && ("com.mobisystems.office.GoPremium.GoPremium".equals(localObject.name))) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static void aeM()
  {
    synchronized (cMq)
    {
      cMr = null;
      return;
    }
  }
  
  private static String[] cA(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    Object localObject = paramContext.getInstalledPackages(0);
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((localPackageInfo != null) && ((localPackageInfo.packageName.startsWith("com.mobisystems.office")) || (localPackageInfo.packageName.startsWith("com.mobisystems.editor")))) {
          try
          {
            localPackageInfo = paramContext.getPackageInfo(localPackageInfo.packageName, 9);
            if (a(localPackageInfo)) {
              a(localPackageInfo, localArrayList);
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
        }
      }
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
  
  public static void cy(Context paramContext)
  {
    cMt = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent) {}
    };
    IntentFilter localIntentFilter = new IntentFilter("com.mobisystems.office.license.change");
    if (paramContext != null) {
      paramContext.registerReceiver(cMt, localIntentFilter);
    }
  }
  
  public static final a cz(Context paramContext)
  {
    int i = 0;
    for (;;)
    {
      a localA;
      synchronized (cMq)
      {
        if ((cMr != null) && (System.nanoTime() - cMs < 1800000000000L))
        {
          paramContext = new a(cMr);
          return paramContext;
        }
        localA = new a();
        localA.cMu = -1;
        ContentResolver localContentResolver = paramContext.getContentResolver();
        try
        {
          paramContext = cA(paramContext);
          int j = paramContext.length;
          if (i < j)
          {
            String str = paramContext[i];
            int k = a(localContentResolver, str);
            if (k <= localA.cMu) {
              break label219;
            }
            localA.cMu = k;
            localA.packageName = str;
          }
        }
        catch (Throwable paramContext)
        {
          paramContext = new String[0];
          continue;
          if (localA.cMu == -1) {
            localA.cMu = 0;
          }
          if (localA.packageName != null) {
            paramContext = localA.packageName;
          }
        }
      }
      try
      {
        localA.packageName = paramContext.substring(0, paramContext.length() - ".license".length());
        cMr = localA;
        cMs = System.nanoTime();
        paramContext = new a(cMr);
        return paramContext;
        paramContext = finally;
        throw paramContext;
      }
      catch (Throwable paramContext)
      {
        for (;;)
        {
          localA.packageName = null;
        }
      }
      label219:
      i += 1;
    }
  }
  
  public static class a
  {
    public int cMu = -1;
    public String packageName;
    
    a() {}
    
    a(a paramA)
    {
      this.cMu = paramA.cMu;
      if (paramA.packageName != null) {
        this.packageName = new String(paramA.packageName);
      }
    }
  }
}
