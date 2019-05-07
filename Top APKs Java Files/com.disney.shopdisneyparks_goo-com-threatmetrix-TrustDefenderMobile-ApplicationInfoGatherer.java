package com.threatmetrix.TrustDefenderMobile;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

class ApplicationInfoGatherer
{
  private static final String TAG = ApplicationInfoGatherer.class.getSimpleName();
  
  ApplicationInfoGatherer() {}
  
  private static void checkInstalledPackages(Context paramContext)
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
  {
    try
    {
      Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(Integer.MAX_VALUE);
      PackageManager localPackageManager = paramContext.getPackageManager();
      localObject = ((List)localObject).iterator();
      if (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)((Iterator)localObject).next();
        if (localRunningTaskInfo.baseActivity != null) {}
        for (paramContext = localRunningTaskInfo.baseActivity.toShortString();; paramContext = "none")
        {
          new StringBuilder("Application executed : ").append(paramContext).append("\t\t ID: ").append(localRunningTaskInfo.id);
          paramContext = localRunningTaskInfo.baseActivity.getPackageName();
          if (localPackageManager == null) {
            break;
          }
          try
          {
            paramContext = localPackageManager.getPackageInfo(paramContext, 0).applicationInfo;
            if (paramContext == null) {
              break;
            }
            getHashForPackage(paramContext.sourceDir);
          }
          catch (PackageManager.NameNotFoundException paramContext)
          {
            paramContext = TAG;
          }
          break;
        }
      }
      return;
    }
    catch (SecurityException paramContext)
    {
      Log.e(TAG, "No perms: ", paramContext);
    }
  }
  
  static String checkThisPackage(Context paramContext)
  {
    paramContext = paramContext.getApplicationInfo();
    if (paramContext != null)
    {
      paramContext = paramContext.sourceDir;
      String str = TAG;
      new StringBuilder("sourceDir: ").append(paramContext);
      return getHashForPackage(paramContext);
    }
    return null;
  }
  
  /* Error */
  private static String getHashForPackage(String paramString)
  {
    // Byte code:
    //   0: getstatic 150	com/threatmetrix/TrustDefenderMobile/NativeGatherer:INSTANCE	Lcom/threatmetrix/TrustDefenderMobile/NativeGatherer;
    //   3: invokevirtual 153	com/threatmetrix/TrustDefenderMobile/NativeGatherer:isAvailable	()Z
    //   6: ifeq +11 -> 17
    //   9: getstatic 150	com/threatmetrix/TrustDefenderMobile/NativeGatherer:INSTANCE	Lcom/threatmetrix/TrustDefenderMobile/NativeGatherer;
    //   12: aload_0
    //   13: invokevirtual 156	com/threatmetrix/TrustDefenderMobile/NativeGatherer:hashFile	(Ljava/lang/String;)Ljava/lang/String;
    //   16: areturn
    //   17: getstatic 16	com/threatmetrix/TrustDefenderMobile/ApplicationInfoGatherer:TAG	Ljava/lang/String;
    //   20: astore_2
    //   21: ldc -98
    //   23: invokestatic 164	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   26: astore_2
    //   27: new 166	java/io/FileInputStream
    //   30: dup
    //   31: aload_0
    //   32: invokespecial 167	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   35: astore_3
    //   36: aconst_null
    //   37: astore_0
    //   38: sipush 8192
    //   41: newarray byte
    //   43: astore 4
    //   45: aload_3
    //   46: aload 4
    //   48: invokevirtual 173	java/io/InputStream:read	([B)I
    //   51: istore_1
    //   52: iload_1
    //   53: ifle +75 -> 128
    //   56: aload_2
    //   57: aload 4
    //   59: iconst_0
    //   60: iload_1
    //   61: invokevirtual 177	java/security/MessageDigest:update	([BII)V
    //   64: goto -19 -> 45
    //   67: astore_2
    //   68: getstatic 16	com/threatmetrix/TrustDefenderMobile/ApplicationInfoGatherer:TAG	Ljava/lang/String;
    //   71: ldc -77
    //   73: aload_2
    //   74: invokestatic 130	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   77: pop
    //   78: aload_3
    //   79: invokevirtual 182	java/io/InputStream:close	()V
    //   82: getstatic 16	com/threatmetrix/TrustDefenderMobile/ApplicationInfoGatherer:TAG	Ljava/lang/String;
    //   85: astore_2
    //   86: new 89	java/lang/StringBuilder
    //   89: dup
    //   90: ldc -72
    //   92: invokespecial 94	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   95: aload_0
    //   96: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: pop
    //   100: aload_0
    //   101: areturn
    //   102: astore_0
    //   103: getstatic 16	com/threatmetrix/TrustDefenderMobile/ApplicationInfoGatherer:TAG	Ljava/lang/String;
    //   106: ldc -70
    //   108: aload_0
    //   109: invokestatic 130	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   112: pop
    //   113: aconst_null
    //   114: areturn
    //   115: astore_0
    //   116: getstatic 16	com/threatmetrix/TrustDefenderMobile/ApplicationInfoGatherer:TAG	Ljava/lang/String;
    //   119: ldc -68
    //   121: aload_0
    //   122: invokestatic 130	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   125: pop
    //   126: aconst_null
    //   127: areturn
    //   128: ldc -66
    //   130: iconst_1
    //   131: anewarray 4	java/lang/Object
    //   134: dup
    //   135: iconst_0
    //   136: new 192	java/math/BigInteger
    //   139: dup
    //   140: iconst_1
    //   141: aload_2
    //   142: invokevirtual 196	java/security/MessageDigest:digest	()[B
    //   145: invokespecial 199	java/math/BigInteger:<init>	(I[B)V
    //   148: bipush 16
    //   150: invokevirtual 203	java/math/BigInteger:toString	(I)Ljava/lang/String;
    //   153: aastore
    //   154: invokestatic 209	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   157: bipush 32
    //   159: bipush 48
    //   161: invokevirtual 213	java/lang/String:replace	(CC)Ljava/lang/String;
    //   164: astore_2
    //   165: aload_2
    //   166: astore_0
    //   167: aload_3
    //   168: invokevirtual 182	java/io/InputStream:close	()V
    //   171: goto -89 -> 82
    //   174: astore_2
    //   175: getstatic 16	com/threatmetrix/TrustDefenderMobile/ApplicationInfoGatherer:TAG	Ljava/lang/String;
    //   178: ldc -41
    //   180: aload_2
    //   181: invokestatic 130	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   184: pop
    //   185: goto -103 -> 82
    //   188: astore_2
    //   189: getstatic 16	com/threatmetrix/TrustDefenderMobile/ApplicationInfoGatherer:TAG	Ljava/lang/String;
    //   192: ldc -41
    //   194: aload_2
    //   195: invokestatic 130	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   198: pop
    //   199: goto -117 -> 82
    //   202: astore_0
    //   203: aload_3
    //   204: invokevirtual 182	java/io/InputStream:close	()V
    //   207: aload_0
    //   208: athrow
    //   209: astore_2
    //   210: getstatic 16	com/threatmetrix/TrustDefenderMobile/ApplicationInfoGatherer:TAG	Ljava/lang/String;
    //   213: ldc -41
    //   215: aload_2
    //   216: invokestatic 130	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   219: pop
    //   220: goto -13 -> 207
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	223	0	paramString	String
    //   51	10	1	i	int
    //   20	37	2	localObject	Object
    //   67	7	2	localIOException1	java.io.IOException
    //   85	81	2	str	String
    //   174	7	2	localIOException2	java.io.IOException
    //   188	7	2	localIOException3	java.io.IOException
    //   209	7	2	localIOException4	java.io.IOException
    //   35	169	3	localFileInputStream	java.io.FileInputStream
    //   43	15	4	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   45	52	67	java/io/IOException
    //   56	64	67	java/io/IOException
    //   128	165	67	java/io/IOException
    //   21	27	102	java/security/NoSuchAlgorithmException
    //   27	36	115	java/io/FileNotFoundException
    //   167	171	174	java/io/IOException
    //   78	82	188	java/io/IOException
    //   45	52	202	finally
    //   56	64	202	finally
    //   68	78	202	finally
    //   128	165	202	finally
    //   203	207	209	java/io/IOException
  }
}
