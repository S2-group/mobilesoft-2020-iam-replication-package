package com.nhn.android.search.appmanager;

import android.a.a.a;
import android.a.a.a.a;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.RemoteException;
import android.os.StatFs;
import android.text.TextUtils;
import com.nhn.android.system.SystemInfo;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public final class d
{
  public static int a(Context paramContext)
  {
    return b(paramContext).size();
  }
  
  public static PackageInfo a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 128);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static String a(float paramFloat)
  {
    Object localObject;
    if (paramFloat == 0.0F) {
      localObject = "0";
    }
    String str;
    do
    {
      do
      {
        return localObject;
        str = String.format("%.2f", new Object[] { Float.valueOf(paramFloat) });
        localObject = str;
      } while (str.charAt(str.length() - 1) != '0');
      str = str.substring(0, str.length() - 1);
      localObject = str;
    } while (str.charAt(str.length() - 1) != '0');
    return str.substring(0, str.length() - 2);
  }
  
  public static boolean a(Context paramContext, final String paramString, c paramC)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, a.class }).invoke(paramContext, new Object[] { paramString, new a.a()
      {
        public void a(PackageStats paramAnonymousPackageStats, boolean paramAnonymousBoolean)
          throws RemoteException
        {
          if (paramAnonymousBoolean == true)
          {
            long l1 = paramAnonymousPackageStats.codeSize;
            long l2 = paramAnonymousPackageStats.dataSize;
            long l3 = paramAnonymousPackageStats.cacheSize;
            this.a.a(paramString, l1 + l2 + l3);
            return;
          }
          this.a.a(paramString, -1L);
        }
      } });
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  private static boolean a(Context paramContext, String paramString, List<ComponentName> paramList)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramList == null)) {
      return false;
    }
    int i = 0;
    while (i < paramList.size())
    {
      if (paramString.equals(((ComponentName)paramList.get(i)).getPackageName())) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static int[] a()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
    long l2;
    long l1;
    long l4;
    if (Build.VERSION.SDK_INT >= 18)
    {
      l2 = localStatFs.getBlockCountLong() * localStatFs.getBlockSizeLong();
      l1 = localStatFs.getAvailableBlocksLong() * localStatFs.getBlockSizeLong();
      l4 = l1;
      l3 = l2;
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        l4 = l1;
        l3 = l2;
        if (!SystemInfo.isExternalStorageEmulated())
        {
          localStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
          if (Build.VERSION.SDK_INT < 18) {
            break label207;
          }
          l4 = localStatFs.getBlockCountLong() * localStatFs.getBlockSizeLong();
        }
      }
    }
    for (long l3 = localStatFs.getAvailableBlocksLong() * localStatFs.getBlockSizeLong();; l3 = localStatFs.getAvailableBlocks() * localStatFs.getBlockSize())
    {
      l2 += l4;
      l4 = l1 + l3;
      l3 = l2;
      return new int[] { (int)(l3 / 1048576L), (int)(l4 / 1048576L), (int)((l3 - l4) / 1048576L) };
      l2 = localStatFs.getBlockCount() * localStatFs.getBlockSize();
      l1 = localStatFs.getAvailableBlocks() * localStatFs.getBlockSize();
      break;
      label207:
      l4 = localStatFs.getBlockCount() * localStatFs.getBlockSize();
    }
  }
  
  public static List<ApplicationInfo> b(Context paramContext)
  {
    new ArrayList();
    List localList1 = paramContext.getPackageManager().getInstalledApplications(128);
    List localList2 = ((DevicePolicyManager)paramContext.getSystemService("device_policy")).getActiveAdmins();
    int i = localList1.size() - 1;
    if (i >= 0)
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localList1.get(i);
      if ((localApplicationInfo.flags & 0x1) != 0) {
        localList1.remove(i);
      }
      for (;;)
      {
        i -= 1;
        break;
        if ((localApplicationInfo.packageName != null) && (localApplicationInfo.packageName.equals(paramContext.getPackageName()))) {
          localList1.remove(i);
        } else if ((localApplicationInfo.packageName != null) && ((localApplicationInfo.packageName.startsWith("sec_container_1")) || (a(paramContext, localApplicationInfo.packageName, localList2)))) {
          localList1.remove(i);
        }
      }
    }
    return localList1;
  }
  
  /* Error */
  public static int[] c(Context paramContext)
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore 5
    //   3: aload_0
    //   4: ldc -28
    //   6: invokevirtual 195	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   9: checkcast 230	android/app/ActivityManager
    //   12: astore_0
    //   13: new 232	android/app/ActivityManager$MemoryInfo
    //   16: dup
    //   17: invokespecial 233	android/app/ActivityManager$MemoryInfo:<init>	()V
    //   20: astore 9
    //   22: aload_0
    //   23: aload 9
    //   25: invokevirtual 237	android/app/ActivityManager:getMemoryInfo	(Landroid/app/ActivityManager$MemoryInfo;)V
    //   28: getstatic 145	android/os/Build$VERSION:SDK_INT	I
    //   31: bipush 16
    //   33: if_icmple +38 -> 71
    //   36: aload 9
    //   38: getfield 241	android/app/ActivityManager$MemoryInfo:totalMem	J
    //   41: lstore_1
    //   42: lload_1
    //   43: lstore_3
    //   44: lload_1
    //   45: lconst_0
    //   46: lcmp
    //   47: ifne +178 -> 225
    //   50: lload_1
    //   51: lstore_3
    //   52: lload_1
    //   53: aload 9
    //   55: getfield 244	android/app/ActivityManager$MemoryInfo:availMem	J
    //   58: lcmp
    //   59: ifge +166 -> 225
    //   62: lload_1
    //   63: ldc2_w 245
    //   66: ladd
    //   67: lstore_1
    //   68: goto -18 -> 50
    //   71: aconst_null
    //   72: astore_0
    //   73: aconst_null
    //   74: astore 8
    //   76: new 248	java/io/BufferedReader
    //   79: dup
    //   80: new 250	java/io/FileReader
    //   83: dup
    //   84: ldc -4
    //   86: invokespecial 253	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   89: sipush 4096
    //   92: invokespecial 256	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   95: astore 7
    //   97: aload 7
    //   99: invokevirtual 259	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   102: astore_0
    //   103: lload 5
    //   105: lstore_3
    //   106: aload_0
    //   107: ifnull +40 -> 147
    //   110: aload_0
    //   111: ldc_w 261
    //   114: invokevirtual 265	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   117: astore_0
    //   118: lload 5
    //   120: lstore_3
    //   121: aload_0
    //   122: ifnull +25 -> 147
    //   125: lload 5
    //   127: lstore_3
    //   128: aload_0
    //   129: arraylength
    //   130: iconst_1
    //   131: if_icmplt +16 -> 147
    //   134: aload_0
    //   135: iconst_1
    //   136: aaload
    //   137: invokestatic 271	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   140: lstore_1
    //   141: lload_1
    //   142: ldc2_w 272
    //   145: lmul
    //   146: lstore_3
    //   147: lload_3
    //   148: lstore_1
    //   149: aload 7
    //   151: ifnull -109 -> 42
    //   154: aload 7
    //   156: invokevirtual 276	java/io/BufferedReader:close	()V
    //   159: lload_3
    //   160: lstore_1
    //   161: goto -119 -> 42
    //   164: astore_0
    //   165: lload_3
    //   166: lstore_1
    //   167: goto -125 -> 42
    //   170: astore_0
    //   171: aload 8
    //   173: astore 7
    //   175: aload_0
    //   176: astore 8
    //   178: aload 7
    //   180: astore_0
    //   181: aload 8
    //   183: invokestatic 282	com/nhn/android/log/Logger:printStackTrace	(Ljava/lang/Exception;)V
    //   186: lload 5
    //   188: lstore_1
    //   189: aload 7
    //   191: ifnull -149 -> 42
    //   194: aload 7
    //   196: invokevirtual 276	java/io/BufferedReader:close	()V
    //   199: lload 5
    //   201: lstore_1
    //   202: goto -160 -> 42
    //   205: astore_0
    //   206: lload 5
    //   208: lstore_1
    //   209: goto -167 -> 42
    //   212: astore 7
    //   214: aload_0
    //   215: ifnull +7 -> 222
    //   218: aload_0
    //   219: invokevirtual 276	java/io/BufferedReader:close	()V
    //   222: aload 7
    //   224: athrow
    //   225: aload 9
    //   227: getfield 244	android/app/ActivityManager$MemoryInfo:availMem	J
    //   230: lstore_1
    //   231: iconst_3
    //   232: newarray int
    //   234: dup
    //   235: iconst_0
    //   236: lload_3
    //   237: ldc2_w 170
    //   240: ldiv
    //   241: l2i
    //   242: iastore
    //   243: dup
    //   244: iconst_1
    //   245: aload 9
    //   247: getfield 244	android/app/ActivityManager$MemoryInfo:availMem	J
    //   250: ldc2_w 170
    //   253: ldiv
    //   254: l2i
    //   255: iastore
    //   256: dup
    //   257: iconst_2
    //   258: lload_3
    //   259: lload_1
    //   260: lsub
    //   261: ldc2_w 170
    //   264: ldiv
    //   265: l2i
    //   266: iastore
    //   267: areturn
    //   268: astore_0
    //   269: goto -47 -> 222
    //   272: astore 8
    //   274: aload 7
    //   276: astore_0
    //   277: aload 8
    //   279: astore 7
    //   281: goto -67 -> 214
    //   284: astore 8
    //   286: goto -108 -> 178
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	289	0	paramContext	Context
    //   41	219	1	l1	long
    //   43	216	3	l2	long
    //   1	206	5	l3	long
    //   95	100	7	localObject1	Object
    //   212	63	7	localObject2	Object
    //   279	1	7	localObject3	Object
    //   74	108	8	localContext	Context
    //   272	6	8	localObject4	Object
    //   284	1	8	localException	Exception
    //   20	226	9	localMemoryInfo	ActivityManager.MemoryInfo
    // Exception table:
    //   from	to	target	type
    //   154	159	164	java/io/IOException
    //   76	97	170	java/lang/Exception
    //   194	199	205	java/io/IOException
    //   76	97	212	finally
    //   181	186	212	finally
    //   218	222	268	java/io/IOException
    //   97	103	272	finally
    //   110	118	272	finally
    //   128	141	272	finally
    //   97	103	284	java/lang/Exception
    //   110	118	284	java/lang/Exception
    //   128	141	284	java/lang/Exception
  }
  
  public static int d(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    paramContext.getMemoryInfo(localMemoryInfo);
    return (int)(localMemoryInfo.availMem / 1048576L);
  }
  
  public static void e(Context paramContext)
  {
    ActivityManager localActivityManager = f(paramContext);
    Object localObject = g(paramContext);
    List localList = h(paramContext);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if ((localList.contains(str) != true) && (!paramContext.getPackageName().equals(str)) && (!b.a(str)) && (!a.a(str))) {
        try
        {
          localActivityManager.killBackgroundProcesses(str);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
  }
  
  private static ActivityManager f(Context paramContext)
  {
    return (ActivityManager)paramContext.getSystemService("activity");
  }
  
  private static List<String> g(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = f(paramContext).getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      Object localObject1 = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if ((localObject1 != null) && (((ActivityManager.RunningAppProcessInfo)localObject1).pkgList != null) && (((ActivityManager.RunningAppProcessInfo)localObject1).pkgList.length != 0) && (((ActivityManager.RunningAppProcessInfo)localObject1).pkgList != null))
      {
        localObject1 = ((ActivityManager.RunningAppProcessInfo)localObject1).pkgList;
        int j = localObject1.length;
        int i = 0;
        while (i < j)
        {
          Object localObject2 = localObject1[i];
          if (!localArrayList.contains(localObject2)) {
            localArrayList.add(localObject2);
          }
          i += 1;
        }
      }
    }
    return localArrayList;
  }
  
  private static List<String> h(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0);
    if (!paramContext.isEmpty())
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        localObject = ((ResolveInfo)paramContext.next()).activityInfo.packageName;
        if (!localArrayList.contains(localObject)) {
          localArrayList.add(localObject);
        }
      }
    }
    return localArrayList;
  }
  
  private static final class a
  {
    private static final String[] a = { "com.android.providers.downloads.ui" };
    
    public static boolean a(String paramString)
    {
      String[] arrayOfString = a;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (arrayOfString[i].equals(paramString) == true) {
          return true;
        }
        i += 1;
      }
      return false;
    }
  }
  
  private static final class b
  {
    private static final String[] a = { "com.samsung.sec.android.MusicPlayer", "com.sec.android.app.music", "com.google.android.music", "com.htc.music", "com.pantech.app.music", "com.android.music", "com.motorola.cmp", "com.miui.player", "com.sonyericsson.music", "com.android.mediacenter", "com.lge.music", "com.arcsoft.music_player" };
    
    public static boolean a(String paramString)
    {
      String[] arrayOfString = a;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (arrayOfString[i].equals(paramString) == true) {
          return true;
        }
        i += 1;
      }
      return false;
    }
  }
  
  public static abstract interface c
  {
    public abstract void a(String paramString, long paramLong);
  }
}
