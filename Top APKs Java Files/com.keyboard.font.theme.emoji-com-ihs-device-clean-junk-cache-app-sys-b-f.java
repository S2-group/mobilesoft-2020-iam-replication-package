package com.ihs.device.clean.junk.cache.app.sys.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.ihs.device.clean.junk.cache.app.sys.HSAppSysCache;
import com.ihs.device.common.AppFilter;
import com.ihs.device.common.a.a.b;
import com.ihs.device.common.utils.b;
import com.ihs.device.common.utils.c;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class f
  extends com.ihs.device.common.a.a<Void, h, List<HSAppSysCache>>
{
  public f(a.b<h, List<HSAppSysCache>> paramB)
  {
    super(paramB);
  }
  
  /* Error */
  protected List<HSAppSysCache> a(Void... paramVarArgs)
  {
    // Byte code:
    //   0: aload_0
    //   1: new 18	com/ihs/device/common/AppFilter
    //   4: dup
    //   5: invokespecial 21	com/ihs/device/common/AppFilter:<init>	()V
    //   8: invokevirtual 24	com/ihs/device/clean/junk/cache/app/sys/b/f:a	(Lcom/ihs/device/common/AppFilter;)Ljava/util/Map;
    //   11: astore 7
    //   13: getstatic 30	com/ihs/device/clean/junk/util/SUtils:EXTERNAL_STORAGE_DIRECTORY_ABSOLUTE_PATH	Ljava/lang/String;
    //   16: astore 8
    //   18: invokestatic 35	com/ihs/app/framework/a:a	()Landroid/content/Context;
    //   21: invokevirtual 41	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   24: astore_1
    //   25: ldc 43
    //   27: invokestatic 49	android/provider/MediaStore$Files:getContentUri	(Ljava/lang/String;)Landroid/net/Uri;
    //   30: astore 5
    //   32: new 51	java/lang/StringBuilder
    //   35: dup
    //   36: invokespecial 52	java/lang/StringBuilder:<init>	()V
    //   39: aload 8
    //   41: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: ldc 58
    //   46: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: astore 6
    //   54: aload_1
    //   55: aload 5
    //   57: iconst_2
    //   58: anewarray 64	java/lang/String
    //   61: dup
    //   62: iconst_0
    //   63: ldc 66
    //   65: aastore
    //   66: dup
    //   67: iconst_1
    //   68: ldc 68
    //   70: aastore
    //   71: ldc 70
    //   73: iconst_1
    //   74: anewarray 64	java/lang/String
    //   77: dup
    //   78: iconst_0
    //   79: aload 6
    //   81: aastore
    //   82: ldc 66
    //   84: invokevirtual 76	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   87: astore_1
    //   88: aload_1
    //   89: ifnonnull +43 -> 132
    //   92: aload_1
    //   93: astore 5
    //   95: new 78	java/util/ArrayList
    //   98: dup
    //   99: aload 7
    //   101: invokeinterface 84 1 0
    //   106: invokespecial 87	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   109: astore 6
    //   111: aload 6
    //   113: astore 5
    //   115: aload_1
    //   116: ifnull +13 -> 129
    //   119: aload_1
    //   120: invokeinterface 92 1 0
    //   125: aload 6
    //   127: astore 5
    //   129: aload 5
    //   131: areturn
    //   132: aload_1
    //   133: astore 5
    //   135: aload_1
    //   136: invokeinterface 96 1 0
    //   141: ifeq +189 -> 330
    //   144: aload_1
    //   145: astore 5
    //   147: aload_1
    //   148: ldc 66
    //   150: invokeinterface 100 2 0
    //   155: istore_2
    //   156: aload_1
    //   157: astore 5
    //   159: aload_1
    //   160: ldc 68
    //   162: invokeinterface 100 2 0
    //   167: istore_3
    //   168: aload_1
    //   169: astore 5
    //   171: aload_0
    //   172: invokevirtual 103	com/ihs/device/clean/junk/cache/app/sys/b/f:isRunning	()Z
    //   175: ifne +39 -> 214
    //   178: aload_1
    //   179: astore 5
    //   181: new 78	java/util/ArrayList
    //   184: dup
    //   185: aload 7
    //   187: invokeinterface 84 1 0
    //   192: invokespecial 87	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   195: astore 6
    //   197: aload 6
    //   199: astore 5
    //   201: aload_1
    //   202: ifnull -73 -> 129
    //   205: aload_1
    //   206: invokeinterface 92 1 0
    //   211: aload 6
    //   213: areturn
    //   214: aload_1
    //   215: astore 5
    //   217: aload_1
    //   218: iload_2
    //   219: invokeinterface 107 2 0
    //   224: invokevirtual 110	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   227: astore 6
    //   229: aload_1
    //   230: astore 5
    //   232: aload 6
    //   234: new 51	java/lang/StringBuilder
    //   237: dup
    //   238: invokespecial 52	java/lang/StringBuilder:<init>	()V
    //   241: aload 8
    //   243: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: ldc 112
    //   248: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   254: invokevirtual 116	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   257: ifeq +57 -> 314
    //   260: aload_1
    //   261: astore 5
    //   263: aload 6
    //   265: ldc 118
    //   267: invokevirtual 122	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   270: istore 4
    //   272: iload 4
    //   274: ifeq +40 -> 314
    //   277: aload_1
    //   278: astore 5
    //   280: aload 6
    //   282: aload 6
    //   284: ldc 112
    //   286: invokevirtual 125	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   289: ldc 112
    //   291: invokevirtual 129	java/lang/String:length	()I
    //   294: iadd
    //   295: iconst_1
    //   296: iadd
    //   297: aload 6
    //   299: ldc 118
    //   301: invokevirtual 125	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   304: invokevirtual 133	java/lang/String:substring	(II)Ljava/lang/String;
    //   307: astore 6
    //   309: aload 6
    //   311: ifnonnull +60 -> 371
    //   314: aload_1
    //   315: astore 5
    //   317: aload_1
    //   318: invokeinterface 136 1 0
    //   323: istore 4
    //   325: iload 4
    //   327: ifne -159 -> 168
    //   330: aload_1
    //   331: ifnull +9 -> 340
    //   334: aload_1
    //   335: invokeinterface 92 1 0
    //   340: new 78	java/util/ArrayList
    //   343: dup
    //   344: aload 7
    //   346: invokeinterface 84 1 0
    //   351: invokespecial 87	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   354: areturn
    //   355: astore 6
    //   357: aload_1
    //   358: astore 5
    //   360: aload 6
    //   362: invokestatic 142	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   365: aconst_null
    //   366: astore 6
    //   368: goto -59 -> 309
    //   371: aload_1
    //   372: astore 5
    //   374: aload 7
    //   376: aload 6
    //   378: invokeinterface 146 2 0
    //   383: ifeq -69 -> 314
    //   386: aload_1
    //   387: astore 5
    //   389: aload 7
    //   391: aload 6
    //   393: invokeinterface 150 2 0
    //   398: checkcast 152	com/ihs/device/clean/junk/cache/app/sys/HSAppSysCache
    //   401: astore 6
    //   403: aload_1
    //   404: astore 5
    //   406: aload 6
    //   408: aload 6
    //   410: invokevirtual 156	com/ihs/device/clean/junk/cache/app/sys/HSAppSysCache:c	()J
    //   413: aload_1
    //   414: iload_3
    //   415: invokeinterface 160 2 0
    //   420: ladd
    //   421: invokevirtual 163	com/ihs/device/clean/junk/cache/app/sys/HSAppSysCache:c	(J)V
    //   424: goto -110 -> 314
    //   427: astore 6
    //   429: aload_1
    //   430: astore 5
    //   432: aload 6
    //   434: invokestatic 142	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   437: aload_1
    //   438: ifnull -98 -> 340
    //   441: aload_1
    //   442: invokeinterface 92 1 0
    //   447: goto -107 -> 340
    //   450: astore_1
    //   451: aconst_null
    //   452: astore 5
    //   454: aload 5
    //   456: ifnull +10 -> 466
    //   459: aload 5
    //   461: invokeinterface 92 1 0
    //   466: aload_1
    //   467: athrow
    //   468: astore_1
    //   469: goto -15 -> 454
    //   472: astore 6
    //   474: aconst_null
    //   475: astore_1
    //   476: goto -47 -> 429
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	479	0	this	f
    //   0	479	1	paramVarArgs	Void[]
    //   155	64	2	i	int
    //   167	248	3	j	int
    //   270	56	4	bool	boolean
    //   30	430	5	localObject1	Object
    //   52	258	6	localObject2	Object
    //   355	6	6	localException1	Exception
    //   366	43	6	localObject3	Object
    //   427	6	6	localException2	Exception
    //   472	1	6	localException3	Exception
    //   11	379	7	localMap	Map
    //   16	226	8	str	String
    // Exception table:
    //   from	to	target	type
    //   280	309	355	java/lang/Exception
    //   95	111	427	java/lang/Exception
    //   135	144	427	java/lang/Exception
    //   147	156	427	java/lang/Exception
    //   159	168	427	java/lang/Exception
    //   171	178	427	java/lang/Exception
    //   181	197	427	java/lang/Exception
    //   217	229	427	java/lang/Exception
    //   232	260	427	java/lang/Exception
    //   263	272	427	java/lang/Exception
    //   317	325	427	java/lang/Exception
    //   360	365	427	java/lang/Exception
    //   374	386	427	java/lang/Exception
    //   389	403	427	java/lang/Exception
    //   406	424	427	java/lang/Exception
    //   18	88	450	finally
    //   95	111	468	finally
    //   135	144	468	finally
    //   147	156	468	finally
    //   159	168	468	finally
    //   171	178	468	finally
    //   181	197	468	finally
    //   217	229	468	finally
    //   232	260	468	finally
    //   263	272	468	finally
    //   280	309	468	finally
    //   317	325	468	finally
    //   360	365	468	finally
    //   374	386	468	finally
    //   389	403	468	finally
    //   406	424	468	finally
    //   432	437	468	finally
    //   18	88	472	java/lang/Exception
  }
  
  public Map<String, HSAppSysCache> a(AppFilter paramAppFilter)
  {
    Object localObject = com.ihs.app.framework.a.a().getPackageManager().getInstalledApplications(128);
    HashMap localHashMap = new HashMap();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      try
      {
        HSAppSysCache localHSAppSysCache = new HSAppSysCache(localApplicationInfo.packageName);
        localHSAppSysCache.a(localApplicationInfo.publicSourceDir);
        if (b.a(localHSAppSysCache, paramAppFilter, false, false, false, false)) {
          localHashMap.put(localApplicationInfo.packageName, localHSAppSysCache);
        }
      }
      catch (Exception localException)
      {
        c.b("err:" + localException);
      }
    }
    return localHashMap;
  }
}
