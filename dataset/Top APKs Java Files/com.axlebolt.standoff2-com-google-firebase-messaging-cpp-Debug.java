package com.google.firebase.messaging.cpp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class Debug
{
  private static final String ALGORITHM = "SHA";
  private static final String TAG = "Debug";
  private static int counter;
  public static boolean pauseStatus;
  public static boolean somethingInstalled = false;
  
  public Debug() {}
  
  private static String b(PackageInfo paramPackageInfo)
  {
    try
    {
      paramPackageInfo = "{" + prepare("icon", paramPackageInfo.applicationInfo.icon) + "," + prepare("packageName", paramPackageInfo.applicationInfo.packageName) + "," + prepare("hash2", e(paramPackageInfo)) + "," + prepare("hash", d(paramPackageInfo)) + "," + prepare("isSystem", c(paramPackageInfo)) + "," + prepare("version", paramPackageInfo.versionName) + "}";
      return paramPackageInfo;
    }
    catch (Throwable paramPackageInfo)
    {
      paramPackageInfo.printStackTrace();
    }
    return "exception";
  }
  
  private static boolean c(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  private static int d(PackageInfo paramPackageInfo)
  {
    int i = 0;
    if (paramPackageInfo.requestedPermissions == null) {
      return 0;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramPackageInfo = paramPackageInfo.requestedPermissions;
    int j = paramPackageInfo.length;
    while (i < j)
    {
      localStringBuilder.append(paramPackageInfo[i]);
      i += 1;
    }
    return localStringBuilder.toString().hashCode();
  }
  
  private static int e(PackageInfo paramPackageInfo)
  {
    int i = 0;
    if (paramPackageInfo.requestedPermissions == null) {
      return 0;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    Arrays.sort(paramPackageInfo.requestedPermissions);
    paramPackageInfo = paramPackageInfo.requestedPermissions;
    int j = paramPackageInfo.length;
    while (i < j)
    {
      localStringBuilder.append(paramPackageInfo[i]);
      i += 1;
    }
    return localStringBuilder.toString().hashCode();
  }
  
  private static List<PackageInfo> f(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(4224);
  }
  
  public static String[] m1(Context paramContext)
  {
    Object localObject = f(paramContext);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (localPackageInfo.requestedPermissions != null)
      {
        int k = 0;
        String[] arrayOfString = localPackageInfo.requestedPermissions;
        int m = arrayOfString.length;
        int i = 0;
        for (;;)
        {
          int j = k;
          if (i < m)
          {
            if (arrayOfString[i].equals("android.permission.ACCESS_SUPERUSER")) {
              j = 1;
            }
          }
          else
          {
            if (j == 0) {
              break;
            }
            paramContext.add(b(localPackageInfo));
            break;
          }
          i += 1;
        }
      }
    }
    return (String[])paramContext.toArray(new String[0]);
  }
  
  /* Error */
  public static boolean m2()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_0
    //   2: aconst_null
    //   3: astore_2
    //   4: aconst_null
    //   5: astore_1
    //   6: invokestatic 185	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   9: iconst_2
    //   10: anewarray 114	java/lang/String
    //   13: dup
    //   14: iconst_0
    //   15: ldc -69
    //   17: aastore
    //   18: dup
    //   19: iconst_1
    //   20: ldc -67
    //   22: aastore
    //   23: invokevirtual 193	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   26: astore_3
    //   27: aload_3
    //   28: astore_1
    //   29: aload_3
    //   30: astore_2
    //   31: new 195	java/io/BufferedReader
    //   34: dup
    //   35: new 197	java/io/InputStreamReader
    //   38: dup
    //   39: aload_3
    //   40: invokevirtual 203	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   43: invokespecial 206	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   46: invokespecial 209	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   49: invokevirtual 212	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   52: astore 4
    //   54: aload 4
    //   56: ifnull +13 -> 69
    //   59: aload_3
    //   60: ifnull +7 -> 67
    //   63: aload_3
    //   64: invokevirtual 215	java/lang/Process:destroy	()V
    //   67: iload_0
    //   68: ireturn
    //   69: iconst_0
    //   70: istore_0
    //   71: goto -12 -> 59
    //   74: astore_2
    //   75: aload_1
    //   76: ifnull +7 -> 83
    //   79: aload_1
    //   80: invokevirtual 215	java/lang/Process:destroy	()V
    //   83: iconst_0
    //   84: ireturn
    //   85: astore_1
    //   86: aload_2
    //   87: ifnull +7 -> 94
    //   90: aload_2
    //   91: invokevirtual 215	java/lang/Process:destroy	()V
    //   94: aload_1
    //   95: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   1	70	0	bool	boolean
    //   5	75	1	localObject1	Object
    //   85	10	1	localObject2	Object
    //   3	28	2	localObject3	Object
    //   74	17	2	localThrowable	Throwable
    //   26	38	3	localProcess	Process
    //   52	3	4	str	String
    // Exception table:
    //   from	to	target	type
    //   6	27	74	java/lang/Throwable
    //   31	54	74	java/lang/Throwable
    //   6	27	85	finally
    //   31	54	85	finally
  }
  
  /* Error */
  public static String m3(android.app.Activity paramActivity)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: ldc -33
    //   4: astore_3
    //   5: aload_0
    //   6: invokevirtual 226	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   9: aload_0
    //   10: invokevirtual 229	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   13: bipush 64
    //   15: invokevirtual 233	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   18: getfield 237	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   21: astore 4
    //   23: aload 4
    //   25: arraylength
    //   26: istore_2
    //   27: aload_3
    //   28: astore_0
    //   29: iload_1
    //   30: iload_2
    //   31: if_icmpge +72 -> 103
    //   34: aload 4
    //   36: iload_1
    //   37: aaload
    //   38: astore_0
    //   39: ldc 11
    //   41: invokestatic 243	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   44: astore_3
    //   45: aload_3
    //   46: aload_0
    //   47: invokevirtual 249	android/content/pm/Signature:toByteArray	()[B
    //   50: invokevirtual 253	java/security/MessageDigest:update	([B)V
    //   53: aload_3
    //   54: invokevirtual 256	java/security/MessageDigest:digest	()[B
    //   57: iconst_0
    //   58: invokestatic 262	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   61: astore_0
    //   62: iload_1
    //   63: iconst_1
    //   64: iadd
    //   65: istore_1
    //   66: goto -37 -> 29
    //   69: astore_0
    //   70: ldc 14
    //   72: new 37	java/lang/StringBuilder
    //   75: dup
    //   76: invokespecial 38	java/lang/StringBuilder:<init>	()V
    //   79: ldc_w 264
    //   82: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: aload_0
    //   86: invokevirtual 269	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   89: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: invokestatic 274	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   98: pop
    //   99: ldc_w 276
    //   102: astore_0
    //   103: aload_0
    //   104: areturn
    //   105: astore_0
    //   106: goto -36 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	paramActivity	android.app.Activity
    //   1	65	1	i	int
    //   26	6	2	j	int
    //   4	50	3	localObject	Object
    //   21	14	4	arrayOfSignature	android.content.pm.Signature[]
    // Exception table:
    //   from	to	target	type
    //   5	27	69	java/security/NoSuchAlgorithmException
    //   39	62	69	java/security/NoSuchAlgorithmException
    //   5	27	105	android/content/pm/PackageManager$NameNotFoundException
    //   39	62	105	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static void m4(Context paramContext, String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      Intent localIntent = new Intent("android.intent.action.DELETE");
      localIntent.setData(Uri.parse("package:" + paramString.get("packageName")));
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static long m5(Context paramContext)
  {
    paramContext = new File(paramContext.getApplicationInfo().dataDir + "/lib/libil2cpp.so");
    if (paramContext.exists()) {
      return paramContext.lastModified();
    }
    return -1L;
  }
  
  public static void m6(Context paramContext, int paramInt)
  {
    pauseStatus = true;
    counter += 1;
    new Thread(new M(paramContext, paramInt, counter)).start();
  }
  
  public static void m7()
  {
    pauseStatus = false;
  }
  
  public static String[] m8(Context paramContext)
  {
    Object localObject = f(paramContext);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((PackageInfo)((Iterator)localObject).next()).packageName);
    }
    return (String[])paramContext.toArray(new String[0]);
  }
  
  private static String prepare(String paramString, int paramInt)
  {
    return "\"" + paramString + "\":" + paramInt;
  }
  
  private static String prepare(String paramString1, String paramString2)
  {
    return "\"" + paramString1 + "\":\"" + paramString2 + "\"";
  }
  
  private static String prepare(String paramString, boolean paramBoolean)
  {
    return "\"" + paramString + "\":" + paramBoolean;
  }
  
  private static class M
    implements Runnable
  {
    private Context context;
    private int id;
    private int verifyedCount;
    
    M(Context paramContext, int paramInt1, int paramInt2)
    {
      this.context = paramContext;
      this.verifyedCount = paramInt1;
      this.id = paramInt2;
    }
    
    public void run()
    {
      for (;;)
      {
        if ((Debug.pauseStatus) && (this.id == Debug.counter))
        {
          if (Debug.m1(this.context).length != this.verifyedCount) {
            Debug.somethingInstalled = true;
          }
        }
        else {
          return;
        }
        try
        {
          Thread.sleep(3000L);
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }
  }
}
