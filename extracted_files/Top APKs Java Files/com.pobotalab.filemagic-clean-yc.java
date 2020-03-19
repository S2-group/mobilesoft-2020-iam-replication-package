package clean;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Base64;
import com.av.sign.utils.QuickZipNative;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class yc
{
  private static yj a = new yj("AppUtils");
  private static List<PackageInfo> b = new ArrayList();
  private static HashMap<String, String> c = new HashMap();
  private static byte[] d = { 36, 97, 107, 101, 121, 64 };
  
  public static PackageInfo a(Context paramContext, String paramString)
  {
    if (yn.a(paramString)) {
      return null;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 4160);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  /* Error */
  public static PackageInfo a(PackageManager paramPackageManager, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 74	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: istore_2
    //   5: iload_2
    //   6: ifeq +5 -> 11
    //   9: aconst_null
    //   10: areturn
    //   11: aload_0
    //   12: aload_1
    //   13: iconst_0
    //   14: invokevirtual 77	android/content/pm/PackageManager:getPackageArchiveInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   17: astore_0
    //   18: aload_0
    //   19: areturn
    //   20: astore_0
    //   21: aconst_null
    //   22: areturn
    //   23: astore_0
    //   24: aconst_null
    //   25: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	26	0	paramPackageManager	PackageManager
    //   0	26	1	paramString	String
    //   4	2	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   0	5	20	java/lang/Throwable
    //   11	18	20	java/lang/Throwable
    //   11	18	23	java/lang/Exception
  }
  
  public static String a(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {
      return null;
    }
    String str = d(paramPackageInfo.applicationInfo.publicSourceDir);
    paramPackageInfo = paramPackageInfo.packageName;
    if (!yn.a(str))
    {
      if (yn.a(paramPackageInfo)) {
        return null;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramPackageInfo);
      localStringBuilder.append(str);
      return yi.b(localStringBuilder.toString());
    }
    return null;
  }
  
  public static List<PackageInfo> a(Context paramContext)
  {
    Object localObject = c(paramContext);
    paramContext = new ArrayList();
    if (localObject == null) {
      return paramContext;
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo != null) && (d(localPackageInfo))) {
        paramContext.add(localPackageInfo);
      }
    }
    return paramContext;
  }
  
  public static void a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    if (b != null) {
      if (b.size() <= 0) {
        return;
      }
    }
    try
    {
      Iterator localIterator = b.iterator();
      while (localIterator.hasNext()) {
        if (paramString.equalsIgnoreCase(((PackageInfo)localIterator.next()).packageName)) {
          localIterator.remove();
        }
      }
      return;
    }
    catch (Exception paramString) {}
    return;
  }
  
  /* Error */
  public static List<PackageInfo> b(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: new 28	java/util/ArrayList
    //   6: dup
    //   7: invokespecial 30	java/util/ArrayList:<init>	()V
    //   10: putstatic 32	clean/yc:b	Ljava/util/List;
    //   13: aload_0
    //   14: invokevirtual 155	android/content/Context:getPackageName	()Ljava/lang/String;
    //   17: astore_1
    //   18: aload_0
    //   19: invokevirtual 60	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   22: astore_0
    //   23: aload_0
    //   24: iconst_0
    //   25: invokevirtual 159	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   28: invokeinterface 120 1 0
    //   33: astore_2
    //   34: aload_2
    //   35: invokeinterface 126 1 0
    //   40: ifeq +61 -> 101
    //   43: aload_2
    //   44: invokeinterface 130 1 0
    //   49: checkcast 80	android/content/pm/PackageInfo
    //   52: astore_3
    //   53: aload_3
    //   54: ifnull -20 -> 34
    //   57: aload_3
    //   58: getfield 96	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   61: invokestatic 54	clean/yn:a	(Ljava/lang/CharSequence;)Z
    //   64: ifne -30 -> 34
    //   67: aload_3
    //   68: getfield 96	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   71: aload_1
    //   72: invokevirtual 149	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   75: ifne -41 -> 34
    //   78: getstatic 32	clean/yc:b	Ljava/util/List;
    //   81: aload_0
    //   82: aload_3
    //   83: getfield 96	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   86: sipush 4160
    //   89: invokevirtual 66	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   92: invokeinterface 137 2 0
    //   97: pop
    //   98: goto -64 -> 34
    //   101: getstatic 32	clean/yc:b	Ljava/util/List;
    //   104: astore_0
    //   105: ldc 2
    //   107: monitorexit
    //   108: aload_0
    //   109: areturn
    //   110: astore_0
    //   111: ldc 2
    //   113: monitorexit
    //   114: aload_0
    //   115: athrow
    //   116: astore_0
    //   117: goto -16 -> 101
    //   120: astore_3
    //   121: goto -87 -> 34
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	paramContext	Context
    //   17	55	1	str	String
    //   33	11	2	localIterator	Iterator
    //   52	31	3	localPackageInfo	PackageInfo
    //   120	1	3	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   3	13	110	finally
    //   13	34	110	finally
    //   34	53	110	finally
    //   57	98	110	finally
    //   101	105	110	finally
    //   13	34	116	java/lang/Exception
    //   34	53	116	java/lang/Exception
    //   57	98	120	java/lang/Exception
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    boolean bool1 = yn.a(paramString);
    boolean bool2 = false;
    if (bool1) {
      return false;
    }
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(100).iterator();
    ActivityManager.RunningTaskInfo localRunningTaskInfo;
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
      localRunningTaskInfo = (ActivityManager.RunningTaskInfo)paramContext.next();
    } while ((!localRunningTaskInfo.topActivity.getPackageName().equals(paramString)) && (!localRunningTaskInfo.baseActivity.getPackageName().equals(paramString)));
    bool1 = true;
    return bool1;
  }
  
  public static boolean b(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  public static boolean b(String paramString)
  {
    if (yn.a(paramString)) {
      return false;
    }
    paramString = yh.b(paramString);
    return (!yn.a(paramString)) && (paramString.equalsIgnoreCase("apk"));
  }
  
  public static String c(Context paramContext, String paramString)
  {
    if (yn.a(paramString)) {
      return null;
    }
    if (!b(paramString)) {
      return null;
    }
    if (c(paramString))
    {
      Object localObject = c(paramContext);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
          if ((localPackageInfo != null) && (localPackageInfo.applicationInfo != null) && (paramString.equalsIgnoreCase(localPackageInfo.applicationInfo.publicSourceDir))) {
            return localPackageInfo.packageName;
          }
        }
      }
    }
    try
    {
      paramContext = a(paramContext.getPackageManager(), paramString).packageName;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  /* Error */
  public static List<PackageInfo> c(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 32	clean/yc:b	Ljava/util/List;
    //   6: ifnull +23 -> 29
    //   9: getstatic 32	clean/yc:b	Ljava/util/List;
    //   12: invokeinterface 143 1 0
    //   17: ifle +12 -> 29
    //   20: getstatic 32	clean/yc:b	Ljava/util/List;
    //   23: astore_0
    //   24: ldc 2
    //   26: monitorexit
    //   27: aload_0
    //   28: areturn
    //   29: aload_0
    //   30: invokevirtual 155	android/content/Context:getPackageName	()Ljava/lang/String;
    //   33: astore_1
    //   34: aload_0
    //   35: invokevirtual 60	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   38: astore_0
    //   39: aload_0
    //   40: iconst_0
    //   41: invokevirtual 159	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   44: invokeinterface 120 1 0
    //   49: astore_2
    //   50: aload_2
    //   51: invokeinterface 126 1 0
    //   56: ifeq +61 -> 117
    //   59: aload_2
    //   60: invokeinterface 130 1 0
    //   65: checkcast 80	android/content/pm/PackageInfo
    //   68: astore_3
    //   69: aload_3
    //   70: ifnull -20 -> 50
    //   73: aload_3
    //   74: getfield 96	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   77: invokestatic 54	clean/yn:a	(Ljava/lang/CharSequence;)Z
    //   80: ifne -30 -> 50
    //   83: aload_3
    //   84: getfield 96	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   87: aload_1
    //   88: invokevirtual 149	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   91: ifne -41 -> 50
    //   94: getstatic 32	clean/yc:b	Ljava/util/List;
    //   97: aload_0
    //   98: aload_3
    //   99: getfield 96	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   102: sipush 4160
    //   105: invokevirtual 66	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   108: invokeinterface 137 2 0
    //   113: pop
    //   114: goto -64 -> 50
    //   117: getstatic 32	clean/yc:b	Ljava/util/List;
    //   120: astore_0
    //   121: ldc 2
    //   123: monitorexit
    //   124: aload_0
    //   125: areturn
    //   126: astore_0
    //   127: ldc 2
    //   129: monitorexit
    //   130: aload_0
    //   131: athrow
    //   132: astore_0
    //   133: goto -16 -> 117
    //   136: astore_3
    //   137: goto -87 -> 50
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	paramContext	Context
    //   33	55	1	str	String
    //   49	11	2	localIterator	Iterator
    //   68	31	3	localPackageInfo	PackageInfo
    //   136	1	3	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   3	24	126	finally
    //   29	50	126	finally
    //   50	69	126	finally
    //   73	114	126	finally
    //   117	121	126	finally
    //   29	50	132	java/lang/Exception
    //   50	69	132	java/lang/Exception
    //   73	114	136	java/lang/Exception
  }
  
  public static boolean c(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x80) != 0;
  }
  
  public static boolean c(String paramString)
  {
    boolean bool2 = yn.a(paramString);
    boolean bool1 = false;
    if (bool2) {
      return false;
    }
    if (paramString.startsWith("/data/local/tmp")) {
      return false;
    }
    if ((paramString.startsWith("/system/")) || (paramString.startsWith("/data/")) || (paramString.startsWith("/vender/"))) {
      bool1 = true;
    }
    return bool1;
  }
  
  public static PackageInfo d(Context paramContext, String paramString)
  {
    if (yn.a(paramString)) {
      return null;
    }
    try
    {
      paramContext = a(paramContext.getPackageManager(), paramString);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String d(String paramString)
  {
    if (yn.a(paramString)) {
      return null;
    }
    String str = (String)c.get(paramString);
    if (!TextUtils.isEmpty(str)) {
      return str;
    }
    str = QuickZipNative.a(paramString);
    if (!yn.a(str)) {}
    try
    {
      c.put(paramString, str);
      return str;
    }
    catch (Exception paramString) {}
    return str;
  }
  
  public static List<String> d(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        str = paramContext.getPackageName();
        paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
        if (paramContext.hasNext())
        {
          localPackageInfo = (PackageInfo)paramContext.next();
          if (localPackageInfo == null) {
            continue;
          }
        }
      }
      catch (Exception paramContext)
      {
        String str;
        PackageInfo localPackageInfo;
        return localArrayList;
      }
      try
      {
        if ((yn.a(localPackageInfo.packageName)) || (localPackageInfo.packageName.equalsIgnoreCase(str))) {
          continue;
        }
        localArrayList.add(localPackageInfo.packageName);
      }
      catch (Exception localException) {}
      return localArrayList;
    }
  }
  
  public static boolean d(PackageInfo paramPackageInfo)
  {
    return (!b(paramPackageInfo)) && (!c(paramPackageInfo));
  }
  
  public static String e(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      if (yn.a(paramString)) {
        return null;
      }
      String str = d(paramString);
      paramContext = c(paramContext, paramString);
      if (!yn.a(str))
      {
        if (yn.a(paramContext)) {
          return null;
        }
        paramString = new StringBuilder();
        paramString.append(paramContext);
        paramString.append(str);
        return yi.b(paramString.toString());
      }
      return null;
    }
    return null;
  }
  
  public static String e(String paramString)
  {
    return f(d(paramString));
  }
  
  public static List<String> e(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        str = paramContext.getPackageName();
        paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
        if (paramContext.hasNext())
        {
          localPackageInfo = (PackageInfo)paramContext.next();
          if (localPackageInfo == null) {
            continue;
          }
        }
      }
      catch (Exception paramContext)
      {
        String str;
        PackageInfo localPackageInfo;
        return localArrayList;
      }
      try
      {
        if ((yn.a(localPackageInfo.packageName)) || (localPackageInfo.packageName.equalsIgnoreCase(str)) || (!d(localPackageInfo))) {
          continue;
        }
        localArrayList.add(localPackageInfo.packageName);
      }
      catch (Exception localException) {}
      return localArrayList;
    }
  }
  
  public static String f(String paramString)
  {
    if (yn.a(paramString)) {
      return null;
    }
    byte[] arrayOfByte1 = paramString.getBytes();
    byte[] arrayOfByte2 = d;
    int j = 0;
    int i = 0;
    while (j < paramString.length())
    {
      arrayOfByte1[j] = ((byte)(arrayOfByte1[j] ^ arrayOfByte2[i]));
      int k = i + 1;
      i = k;
      if (k == arrayOfByte2.length) {
        i = 0;
      }
      j += 1;
    }
    return new String(Base64.encode(arrayOfByte1, 2));
  }
}
