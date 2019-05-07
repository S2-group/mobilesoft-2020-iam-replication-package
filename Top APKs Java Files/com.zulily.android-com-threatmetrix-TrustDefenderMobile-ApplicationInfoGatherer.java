package com.threatmetrix.TrustDefenderMobile;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Iterator;
import java.util.List;

class ApplicationInfoGatherer
{
  private static final String TAG = StringUtils.getLogTag(ApplicationInfoGatherer.class);
  
  ApplicationInfoGatherer() {}
  
  private static void checkInstalledPackages(Context paramContext)
    throws InterruptedException
  {
    paramContext = paramContext.getPackageManager();
    if (paramContext != null)
    {
      paramContext = paramContext.getInstalledApplications(0).iterator();
      while (paramContext.hasNext()) {
        getHashForPackage(((ApplicationInfo)paramContext.next()).sourceDir);
      }
    }
  }
  
  private static void checkRunningPackages(Context paramContext)
    throws InterruptedException
  {
    try
    {
      Object localObject1 = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(Integer.MAX_VALUE);
      paramContext = paramContext.getPackageManager();
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (ActivityManager.RunningTaskInfo)((Iterator)localObject1).next();
        if (((ActivityManager.RunningTaskInfo)localObject2).baseActivity != null) {
          ((ActivityManager.RunningTaskInfo)localObject2).baseActivity.toShortString();
        }
        int i = ((ActivityManager.RunningTaskInfo)localObject2).id;
        localObject2 = ((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName();
        if (paramContext != null) {
          try
          {
            localObject2 = paramContext.getPackageInfo((String)localObject2, 0).applicationInfo;
            if (localObject2 != null) {
              getHashForPackage(((ApplicationInfo)localObject2).sourceDir);
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
        }
      }
      return;
    }
    catch (SecurityException paramContext) {}
  }
  
  static String checkThisPackage(Context paramContext)
    throws InterruptedException
  {
    paramContext = paramContext.getApplicationInfo();
    if (paramContext != null) {
      return getHashForPackage(paramContext.sourceDir);
    }
    return null;
  }
  
  /* Error */
  private static String getHashForPackage(String paramString)
    throws InterruptedException
  {
    // Byte code:
    //   0: invokestatic 126	com/threatmetrix/TrustDefenderMobile/NativeGatherer:getInstance	()Lcom/threatmetrix/TrustDefenderMobile/NativeGatherer;
    //   3: invokevirtual 129	com/threatmetrix/TrustDefenderMobile/NativeGatherer:isAvailable	()Z
    //   6: ifeq +11 -> 17
    //   9: invokestatic 126	com/threatmetrix/TrustDefenderMobile/NativeGatherer:getInstance	()Lcom/threatmetrix/TrustDefenderMobile/NativeGatherer;
    //   12: aload_0
    //   13: invokevirtual 132	com/threatmetrix/TrustDefenderMobile/NativeGatherer:hashFile	(Ljava/lang/String;)Ljava/lang/String;
    //   16: areturn
    //   17: ldc -122
    //   19: invokestatic 139	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   22: astore_2
    //   23: new 141	java/io/FileInputStream
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 144	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   31: astore_0
    //   32: sipush 8192
    //   35: newarray byte
    //   37: astore_3
    //   38: aload_0
    //   39: aload_3
    //   40: invokevirtual 150	java/io/InputStream:read	([B)I
    //   43: istore_1
    //   44: iload_1
    //   45: ifle +23 -> 68
    //   48: aload_2
    //   49: aload_3
    //   50: iconst_0
    //   51: iload_1
    //   52: invokevirtual 154	java/security/MessageDigest:update	([BII)V
    //   55: goto -17 -> 38
    //   58: astore_2
    //   59: aload_0
    //   60: invokevirtual 157	java/io/InputStream:close	()V
    //   63: aconst_null
    //   64: areturn
    //   65: astore_0
    //   66: aconst_null
    //   67: areturn
    //   68: ldc -97
    //   70: iconst_1
    //   71: anewarray 4	java/lang/Object
    //   74: dup
    //   75: iconst_0
    //   76: new 161	java/math/BigInteger
    //   79: dup
    //   80: iconst_1
    //   81: aload_2
    //   82: invokevirtual 165	java/security/MessageDigest:digest	()[B
    //   85: invokespecial 168	java/math/BigInteger:<init>	(I[B)V
    //   88: bipush 16
    //   90: invokevirtual 172	java/math/BigInteger:toString	(I)Ljava/lang/String;
    //   93: aastore
    //   94: invokestatic 178	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   97: bipush 32
    //   99: bipush 48
    //   101: invokevirtual 182	java/lang/String:replace	(CC)Ljava/lang/String;
    //   104: astore_2
    //   105: aload_0
    //   106: invokevirtual 157	java/io/InputStream:close	()V
    //   109: aload_2
    //   110: areturn
    //   111: astore_0
    //   112: aload_2
    //   113: areturn
    //   114: astore_2
    //   115: aload_0
    //   116: invokevirtual 157	java/io/InputStream:close	()V
    //   119: aload_2
    //   120: athrow
    //   121: astore_0
    //   122: goto -3 -> 119
    //   125: astore_0
    //   126: aconst_null
    //   127: areturn
    //   128: astore_0
    //   129: aconst_null
    //   130: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	paramString	String
    //   43	9	1	i	int
    //   22	27	2	localMessageDigest	java.security.MessageDigest
    //   58	24	2	localIOException	java.io.IOException
    //   104	9	2	str	String
    //   114	6	2	localObject	Object
    //   37	13	3	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   38	44	58	java/io/IOException
    //   48	55	58	java/io/IOException
    //   68	105	58	java/io/IOException
    //   59	63	65	java/io/IOException
    //   105	109	111	java/io/IOException
    //   38	44	114	finally
    //   48	55	114	finally
    //   68	105	114	finally
    //   115	119	121	java/io/IOException
    //   23	32	125	java/io/FileNotFoundException
    //   17	23	128	java/security/NoSuchAlgorithmException
  }
}
