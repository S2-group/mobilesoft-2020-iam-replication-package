package com.ts.common.internal.core.collection.impl;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.ts.common.api.core.encryption.Encryptor;
import com.ts.common.internal.core.logger.Log;
import javax.inject.Inject;

public class PackagesCollector
  extends CollectorBase
{
  private static final String TAG = Log.getLogTag(PackagesCollector.class);
  private Encryptor mEncryptor;
  private PackageManager mPackageManager = null;
  
  @Inject
  public PackagesCollector(PackageManager paramPackageManager, Encryptor paramEncryptor)
  {
    this.mPackageManager = paramPackageManager;
    this.mEncryptor = paramEncryptor;
  }
  
  private static boolean isSystemPackage(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  public String getID()
  {
    return "installed_packages";
  }
  
  public String toString()
  {
    return PackagesCollector.class.getSimpleName();
  }
  
  /* Error */
  public boolean update(com.ts.common.api.core.collection.Information paramInformation, com.ts.common.api.core.collection.Collector.CollectorCallback paramCollectorCallback, java.util.Map<String, Object> paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 28	com/ts/common/internal/core/collection/impl/PackagesCollector:mPackageManager	Landroid/content/pm/PackageManager;
    //   4: sipush 128
    //   7: invokevirtual 61	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   10: astore_2
    //   11: aload_2
    //   12: ifnull +446 -> 458
    //   15: aload_2
    //   16: invokeinterface 67 1 0
    //   21: ifle +437 -> 458
    //   24: new 69	java/util/ArrayList
    //   27: dup
    //   28: aload_2
    //   29: invokeinterface 67 1 0
    //   34: invokespecial 72	java/util/ArrayList:<init>	(I)V
    //   37: astore_3
    //   38: aload_2
    //   39: invokeinterface 76 1 0
    //   44: astore_2
    //   45: aload_2
    //   46: invokeinterface 82 1 0
    //   51: ifeq +389 -> 440
    //   54: aload_2
    //   55: invokeinterface 86 1 0
    //   60: checkcast 35	android/content/pm/ApplicationInfo
    //   63: astore 4
    //   65: aload 4
    //   67: invokestatic 88	com/ts/common/internal/core/collection/impl/PackagesCollector:isSystemPackage	(Landroid/content/pm/ApplicationInfo;)Z
    //   70: ifne -25 -> 45
    //   73: getstatic 20	com/ts/common/internal/core/collection/impl/PackagesCollector:TAG	Ljava/lang/String;
    //   76: new 90	java/lang/StringBuilder
    //   79: dup
    //   80: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   83: ldc 93
    //   85: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload 4
    //   90: getfield 100	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   93: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokestatic 106	com/ts/common/internal/core/logger/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   102: pop
    //   103: aload_3
    //   104: aload_0
    //   105: getfield 30	com/ts/common/internal/core/collection/impl/PackagesCollector:mEncryptor	Lcom/ts/common/api/core/encryption/Encryptor;
    //   108: aload 4
    //   110: getfield 100	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   113: invokeinterface 112 2 0
    //   118: invokeinterface 116 2 0
    //   123: pop
    //   124: goto -79 -> 45
    //   127: astore_2
    //   128: getstatic 20	com/ts/common/internal/core/collection/impl/PackagesCollector:TAG	Ljava/lang/String;
    //   131: new 90	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   138: ldc 118
    //   140: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aload_2
    //   144: invokevirtual 121	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   147: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 124	com/ts/common/internal/core/logger/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   156: pop
    //   157: aconst_null
    //   158: astore_3
    //   159: aconst_null
    //   160: astore 6
    //   162: new 69	java/util/ArrayList
    //   165: dup
    //   166: iconst_0
    //   167: invokespecial 72	java/util/ArrayList:<init>	(I)V
    //   170: astore 5
    //   172: aload_3
    //   173: astore_2
    //   174: invokestatic 130	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   177: ldc -124
    //   179: invokevirtual 136	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   182: astore 4
    //   184: aload_3
    //   185: astore_2
    //   186: new 138	java/io/BufferedReader
    //   189: dup
    //   190: new 140	java/io/InputStreamReader
    //   193: dup
    //   194: aload 4
    //   196: invokevirtual 146	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   199: invokespecial 149	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   202: invokespecial 152	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   205: astore_3
    //   206: aload_3
    //   207: invokevirtual 155	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   210: astore_2
    //   211: aload_2
    //   212: ifnull +125 -> 337
    //   215: aload_2
    //   216: aload_2
    //   217: bipush 58
    //   219: invokevirtual 161	java/lang/String:indexOf	(I)I
    //   222: iconst_1
    //   223: iadd
    //   224: invokevirtual 165	java/lang/String:substring	(I)Ljava/lang/String;
    //   227: astore_2
    //   228: aload 5
    //   230: aload_0
    //   231: getfield 28	com/ts/common/internal/core/collection/impl/PackagesCollector:mPackageManager	Landroid/content/pm/PackageManager;
    //   234: aload_2
    //   235: sipush 128
    //   238: invokevirtual 169	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   241: invokeinterface 116 2 0
    //   246: pop
    //   247: goto -41 -> 206
    //   250: astore 4
    //   252: aload_3
    //   253: astore_2
    //   254: getstatic 20	com/ts/common/internal/core/collection/impl/PackagesCollector:TAG	Ljava/lang/String;
    //   257: new 90	java/lang/StringBuilder
    //   260: dup
    //   261: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   264: ldc -85
    //   266: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: aload 4
    //   271: invokevirtual 121	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   274: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   280: invokestatic 124	com/ts/common/internal/core/logger/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   283: pop
    //   284: aload 5
    //   286: astore_2
    //   287: aload_3
    //   288: ifnull -277 -> 11
    //   291: aload_3
    //   292: invokevirtual 174	java/io/BufferedReader:close	()V
    //   295: aload 5
    //   297: astore_2
    //   298: goto -287 -> 11
    //   301: astore_2
    //   302: getstatic 20	com/ts/common/internal/core/collection/impl/PackagesCollector:TAG	Ljava/lang/String;
    //   305: new 90	java/lang/StringBuilder
    //   308: dup
    //   309: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   312: ldc -80
    //   314: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: aload_2
    //   318: invokevirtual 177	java/io/IOException:getMessage	()Ljava/lang/String;
    //   321: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   327: invokestatic 124	com/ts/common/internal/core/logger/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   330: pop
    //   331: aload 5
    //   333: astore_2
    //   334: goto -323 -> 11
    //   337: aload 4
    //   339: invokevirtual 180	java/lang/Process:waitFor	()I
    //   342: pop
    //   343: aload 5
    //   345: astore_2
    //   346: aload_3
    //   347: ifnull -336 -> 11
    //   350: aload_3
    //   351: invokevirtual 174	java/io/BufferedReader:close	()V
    //   354: aload 5
    //   356: astore_2
    //   357: goto -346 -> 11
    //   360: astore_2
    //   361: getstatic 20	com/ts/common/internal/core/collection/impl/PackagesCollector:TAG	Ljava/lang/String;
    //   364: new 90	java/lang/StringBuilder
    //   367: dup
    //   368: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   371: ldc -80
    //   373: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: aload_2
    //   377: invokevirtual 177	java/io/IOException:getMessage	()Ljava/lang/String;
    //   380: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   383: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   386: invokestatic 124	com/ts/common/internal/core/logger/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   389: pop
    //   390: aload 5
    //   392: astore_2
    //   393: goto -382 -> 11
    //   396: astore_1
    //   397: aload_2
    //   398: ifnull +7 -> 405
    //   401: aload_2
    //   402: invokevirtual 174	java/io/BufferedReader:close	()V
    //   405: aload_1
    //   406: athrow
    //   407: astore_2
    //   408: getstatic 20	com/ts/common/internal/core/collection/impl/PackagesCollector:TAG	Ljava/lang/String;
    //   411: new 90	java/lang/StringBuilder
    //   414: dup
    //   415: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   418: ldc -80
    //   420: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: aload_2
    //   424: invokevirtual 177	java/io/IOException:getMessage	()Ljava/lang/String;
    //   427: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   433: invokestatic 124	com/ts/common/internal/core/logger/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   436: pop
    //   437: goto -32 -> 405
    //   440: aload_3
    //   441: invokeinterface 67 1 0
    //   446: ifle +12 -> 458
    //   449: aload_1
    //   450: aload_0
    //   451: invokevirtual 182	com/ts/common/internal/core/collection/impl/PackagesCollector:getID	()Ljava/lang/String;
    //   454: aload_3
    //   455: invokevirtual 188	com/ts/common/api/core/collection/Information:putList	(Ljava/lang/String;Ljava/util/List;)V
    //   458: getstatic 20	com/ts/common/internal/core/collection/impl/PackagesCollector:TAG	Ljava/lang/String;
    //   461: ldc -66
    //   463: invokestatic 106	com/ts/common/internal/core/logger/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   466: pop
    //   467: iconst_1
    //   468: ireturn
    //   469: astore_1
    //   470: aload_3
    //   471: astore_2
    //   472: goto -75 -> 397
    //   475: astore 4
    //   477: aload 6
    //   479: astore_3
    //   480: goto -228 -> 252
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	483	0	this	PackagesCollector
    //   0	483	1	paramInformation	com.ts.common.api.core.collection.Information
    //   0	483	2	paramCollectorCallback	com.ts.common.api.core.collection.Collector.CollectorCallback
    //   0	483	3	paramMap	java.util.Map<String, Object>
    //   63	132	4	localObject1	Object
    //   250	88	4	localException1	Exception
    //   475	1	4	localException2	Exception
    //   170	221	5	localArrayList	java.util.ArrayList
    //   160	318	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   0	11	127	java/lang/Exception
    //   206	211	250	java/lang/Exception
    //   215	247	250	java/lang/Exception
    //   337	343	250	java/lang/Exception
    //   291	295	301	java/io/IOException
    //   350	354	360	java/io/IOException
    //   174	184	396	finally
    //   186	206	396	finally
    //   254	284	396	finally
    //   401	405	407	java/io/IOException
    //   206	211	469	finally
    //   215	247	469	finally
    //   337	343	469	finally
    //   174	184	475	java/lang/Exception
    //   186	206	475	java/lang/Exception
  }
}
