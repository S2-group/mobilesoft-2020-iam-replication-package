package clean;

import android.app.usage.StorageStats;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ux
{
  private static Method a;
  private static Method b;
  private static boolean c = false;
  private static Method d;
  private static boolean e = false;
  private static int f = 2;
  
  public ux() {}
  
  public static List<String> a(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        localArrayList.add(((PackageInfo)paramContext.next()).packageName);
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static void a(PackageManager paramPackageManager, long paramLong, IPackageDataObserver paramIPackageDataObserver)
  {
    if (paramPackageManager == null) {
      return;
    }
    for (;;)
    {
      try
      {
        boolean bool = e;
        if (bool) {}
      }
      finally
      {
        try
        {
          int i;
          Object localObject = new Object[2];
          localObject[0] = Long.valueOf(paramLong);
          localObject[1] = paramIPackageDataObserver;
          if (f == 3)
          {
            localObject = new Object[3];
            localObject[0] = null;
            localObject[1] = Long.valueOf(paramLong);
            localObject[2] = paramIPackageDataObserver;
          }
          d.invoke(paramPackageManager, (Object[])localObject);
          return;
        }
        catch (Exception paramPackageManager) {}
        paramPackageManager = finally;
      }
      try
      {
        localObject = paramPackageManager.getClass().getDeclaredMethods();
        i = 0;
        if (i < localObject.length)
        {
          if ("freeStorageAndNotify".equals(localObject[i].getName()))
          {
            d = localObject[i];
            f = d.getParameterTypes().length;
            d.setAccessible(true);
          }
          i += 1;
        }
      }
      catch (Exception localException) {}
    }
    e = true;
    if (d != null) {}
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext == null) {
        return false;
      }
      int i;
      if ((paramContext.applicationInfo.flags & 0x1) == 0) {
        i = paramContext.applicationInfo.flags;
      }
      return (i & 0x80) != 0;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  /* Error */
  public static boolean a(PackageManager paramPackageManager, String paramString, int paramInt, Context paramContext, android.content.pm.IPackageStatsObserver paramIPackageStatsObserver, boolean paramBoolean, a paramA)
  {
    // Byte code:
    //   0: getstatic 141	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 26
    //   5: if_icmplt +66 -> 71
    //   8: iload 5
    //   10: ifeq +59 -> 69
    //   13: aload_3
    //   14: ldc -113
    //   16: invokevirtual 147	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   19: checkcast 149	android/app/usage/StorageStatsManager
    //   22: astore_0
    //   23: aload_0
    //   24: getstatic 155	android/os/storage/StorageManager:UUID_DEFAULT	Ljava/util/UUID;
    //   27: aload_1
    //   28: invokestatic 161	android/os/Process:myUserHandle	()Landroid/os/UserHandle;
    //   31: invokevirtual 165	android/app/usage/StorageStatsManager:queryStatsForPackage	(Ljava/util/UUID;Ljava/lang/String;Landroid/os/UserHandle;)Landroid/app/usage/StorageStats;
    //   34: astore_0
    //   35: aload_0
    //   36: ifnull +19 -> 55
    //   39: aload 6
    //   41: ifnull +14 -> 55
    //   44: aload 6
    //   46: aload_1
    //   47: aload_0
    //   48: invokeinterface 168 3 0
    //   53: iconst_1
    //   54: ireturn
    //   55: iconst_0
    //   56: ireturn
    //   57: astore_0
    //   58: aload_0
    //   59: invokevirtual 171	java/io/IOException:printStackTrace	()V
    //   62: iconst_0
    //   63: ireturn
    //   64: astore_0
    //   65: aload_0
    //   66: invokevirtual 172	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   69: iconst_0
    //   70: ireturn
    //   71: aload_0
    //   72: ifnonnull +5 -> 77
    //   75: iconst_0
    //   76: ireturn
    //   77: ldc 2
    //   79: monitorenter
    //   80: getstatic 174	clean/ux:c	Z
    //   83: istore 5
    //   85: iload 5
    //   87: ifne +202 -> 289
    //   90: aload_0
    //   91: invokevirtual 76	java/lang/Object:getClass	()Ljava/lang/Class;
    //   94: invokevirtual 82	java/lang/Class:getDeclaredMethods	()[Ljava/lang/reflect/Method;
    //   97: astore_3
    //   98: iconst_0
    //   99: istore 7
    //   101: iload 7
    //   103: aload_3
    //   104: arraylength
    //   105: if_icmpge +76 -> 181
    //   108: ldc -80
    //   110: aload_3
    //   111: iload 7
    //   113: aaload
    //   114: invokevirtual 90	java/lang/reflect/Method:getName	()Ljava/lang/String;
    //   117: invokevirtual 95	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   120: ifeq +256 -> 376
    //   123: aload_3
    //   124: iload 7
    //   126: aaload
    //   127: invokevirtual 101	java/lang/reflect/Method:getParameterTypes	()[Ljava/lang/Class;
    //   130: arraylength
    //   131: iconst_2
    //   132: if_icmpne +20 -> 152
    //   135: aload_3
    //   136: iload 7
    //   138: aaload
    //   139: putstatic 178	clean/ux:b	Ljava/lang/reflect/Method;
    //   142: getstatic 178	clean/ux:b	Ljava/lang/reflect/Method;
    //   145: iconst_1
    //   146: invokevirtual 107	java/lang/reflect/Method:setAccessible	(Z)V
    //   149: goto +32 -> 181
    //   152: aload_3
    //   153: iload 7
    //   155: aaload
    //   156: invokevirtual 101	java/lang/reflect/Method:getParameterTypes	()[Ljava/lang/Class;
    //   159: arraylength
    //   160: iconst_3
    //   161: if_icmpne +215 -> 376
    //   164: aload_3
    //   165: iload 7
    //   167: aaload
    //   168: putstatic 180	clean/ux:a	Ljava/lang/reflect/Method;
    //   171: getstatic 180	clean/ux:a	Ljava/lang/reflect/Method;
    //   174: iconst_1
    //   175: invokevirtual 107	java/lang/reflect/Method:setAccessible	(Z)V
    //   178: goto +3 -> 181
    //   181: getstatic 178	clean/ux:b	Ljava/lang/reflect/Method;
    //   184: ifnonnull +101 -> 285
    //   187: getstatic 180	clean/ux:a	Ljava/lang/reflect/Method;
    //   190: ifnonnull +95 -> 285
    //   193: iconst_0
    //   194: istore 7
    //   196: iload 7
    //   198: aload_3
    //   199: arraylength
    //   200: if_icmpge +85 -> 285
    //   203: ldc -74
    //   205: aload_3
    //   206: iload 7
    //   208: aaload
    //   209: invokevirtual 90	java/lang/reflect/Method:getName	()Ljava/lang/String;
    //   212: invokevirtual 95	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   215: ifeq +61 -> 276
    //   218: aload_3
    //   219: iload 7
    //   221: aaload
    //   222: invokevirtual 101	java/lang/reflect/Method:getParameterTypes	()[Ljava/lang/Class;
    //   225: arraylength
    //   226: iconst_2
    //   227: if_icmpne +20 -> 247
    //   230: aload_3
    //   231: iload 7
    //   233: aaload
    //   234: putstatic 178	clean/ux:b	Ljava/lang/reflect/Method;
    //   237: getstatic 178	clean/ux:b	Ljava/lang/reflect/Method;
    //   240: iconst_1
    //   241: invokevirtual 107	java/lang/reflect/Method:setAccessible	(Z)V
    //   244: goto +41 -> 285
    //   247: aload_3
    //   248: iload 7
    //   250: aaload
    //   251: invokevirtual 101	java/lang/reflect/Method:getParameterTypes	()[Ljava/lang/Class;
    //   254: arraylength
    //   255: iconst_3
    //   256: if_icmpne +20 -> 276
    //   259: aload_3
    //   260: iload 7
    //   262: aaload
    //   263: putstatic 180	clean/ux:a	Ljava/lang/reflect/Method;
    //   266: getstatic 180	clean/ux:a	Ljava/lang/reflect/Method;
    //   269: iconst_1
    //   270: invokevirtual 107	java/lang/reflect/Method:setAccessible	(Z)V
    //   273: goto +12 -> 285
    //   276: iload 7
    //   278: iconst_1
    //   279: iadd
    //   280: istore 7
    //   282: goto -86 -> 196
    //   285: iconst_1
    //   286: putstatic 174	clean/ux:c	Z
    //   289: ldc 2
    //   291: monitorexit
    //   292: getstatic 180	clean/ux:a	Ljava/lang/reflect/Method;
    //   295: ifnull +33 -> 328
    //   298: getstatic 180	clean/ux:a	Ljava/lang/reflect/Method;
    //   301: aload_0
    //   302: iconst_3
    //   303: anewarray 4	java/lang/Object
    //   306: dup
    //   307: iconst_0
    //   308: aload_1
    //   309: aastore
    //   310: dup
    //   311: iconst_1
    //   312: iload_2
    //   313: invokestatic 187	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   316: aastore
    //   317: dup
    //   318: iconst_2
    //   319: aload 4
    //   321: aastore
    //   322: invokevirtual 117	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   325: pop
    //   326: iconst_1
    //   327: ireturn
    //   328: getstatic 178	clean/ux:b	Ljava/lang/reflect/Method;
    //   331: ifnull +26 -> 357
    //   334: getstatic 178	clean/ux:b	Ljava/lang/reflect/Method;
    //   337: aload_0
    //   338: iconst_2
    //   339: anewarray 4	java/lang/Object
    //   342: dup
    //   343: iconst_0
    //   344: aload_1
    //   345: aastore
    //   346: dup
    //   347: iconst_1
    //   348: aload 4
    //   350: aastore
    //   351: invokevirtual 117	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   354: pop
    //   355: iconst_1
    //   356: ireturn
    //   357: iconst_0
    //   358: ireturn
    //   359: astore_0
    //   360: ldc 2
    //   362: monitorexit
    //   363: aload_0
    //   364: athrow
    //   365: astore_3
    //   366: goto -81 -> 285
    //   369: astore_3
    //   370: goto -42 -> 328
    //   373: astore_0
    //   374: iconst_0
    //   375: ireturn
    //   376: iload 7
    //   378: iconst_1
    //   379: iadd
    //   380: istore 7
    //   382: goto -281 -> 101
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	385	0	paramPackageManager	PackageManager
    //   0	385	1	paramString	String
    //   0	385	2	paramInt	int
    //   0	385	3	paramContext	Context
    //   0	385	4	paramIPackageStatsObserver	android.content.pm.IPackageStatsObserver
    //   0	385	5	paramBoolean	boolean
    //   0	385	6	paramA	a
    //   99	282	7	i	int
    // Exception table:
    //   from	to	target	type
    //   23	35	57	java/io/IOException
    //   44	53	57	java/io/IOException
    //   23	35	64	android/content/pm/PackageManager$NameNotFoundException
    //   44	53	64	android/content/pm/PackageManager$NameNotFoundException
    //   80	85	359	finally
    //   90	98	359	finally
    //   101	149	359	finally
    //   152	178	359	finally
    //   181	193	359	finally
    //   196	244	359	finally
    //   247	273	359	finally
    //   285	289	359	finally
    //   289	292	359	finally
    //   360	363	359	finally
    //   90	98	365	java/lang/Exception
    //   101	149	365	java/lang/Exception
    //   152	178	365	java/lang/Exception
    //   181	193	365	java/lang/Exception
    //   196	244	365	java/lang/Exception
    //   247	273	365	java/lang/Exception
    //   298	326	369	java/lang/Exception
    //   334	355	373	java/lang/Exception
  }
  
  public static List<PackageInfo> b(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        int i = localPackageInfo.applicationInfo.flags;
        ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
        if ((i & 0x1) == 0)
        {
          i = localPackageInfo.applicationInfo.flags;
          localApplicationInfo = localPackageInfo.applicationInfo;
          if ((i & 0x80) == 0) {
            localArrayList.add(localPackageInfo);
          }
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static abstract interface a
  {
    public abstract void a(String paramString, StorageStats paramStorageStats);
  }
}
