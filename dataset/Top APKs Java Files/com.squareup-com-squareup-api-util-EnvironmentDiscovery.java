package com.squareup.api.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import timber.log.Timber;

public abstract interface EnvironmentDiscovery
{
  public static final EnvironmentDiscovery NONE = -..Lambda.EnvironmentDiscovery.htuD8IihE48TCMGE4JotCNXHyrQ.INSTANCE;
  
  public abstract String getEnvironment();
  
  public static class Root
    implements EnvironmentDiscovery
  {
    private static final String ROOT_DATA_WRITEABLE = "rdw";
    private static final String ROOT_RO_PRESENT = "rro";
    private static final String ROOT_SU_PACKAGES = "rpk";
    private static final String ROOT_SU_PRESENT = "rsu";
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final String environment;
    
    public Root(Context paramContext)
    {
      this.environment = getRootFlags(paramContext);
    }
    
    private static String checkForDataWriteable()
    {
      boolean bool = new File("/data").canWrite();
      Timber.d("Can write to data? %s", new Object[] { Boolean.valueOf(bool) });
      if (bool) {
        return "rdw";
      }
      return "";
    }
    
    /* Error */
    private static String checkForRoSecure()
    {
      // Byte code:
      //   0: ldc 75
      //   2: astore_1
      //   3: aconst_null
      //   4: astore 5
      //   6: aconst_null
      //   7: astore 4
      //   9: ldc 82
      //   11: invokestatic 87	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   14: ldc 89
      //   16: iconst_1
      //   17: anewarray 84	java/lang/Class
      //   20: dup
      //   21: iconst_0
      //   22: ldc 91
      //   24: aastore
      //   25: invokevirtual 95	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   28: aconst_null
      //   29: iconst_1
      //   30: anewarray 4	java/lang/Object
      //   33: dup
      //   34: iconst_0
      //   35: ldc 97
      //   37: aastore
      //   38: invokevirtual 103	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   41: checkcast 91	java/lang/String
      //   44: astore_2
      //   45: goto +177 -> 222
      //   48: astore_0
      //   49: aload_0
      //   50: astore_3
      //   51: aload_0
      //   52: instanceof 105
      //   55: ifeq +11 -> 66
      //   58: aload_0
      //   59: checkcast 105	java/lang/reflect/InvocationTargetException
      //   62: invokevirtual 109	java/lang/reflect/InvocationTargetException:getTargetException	()Ljava/lang/Throwable;
      //   65: astore_3
      //   66: ldc 111
      //   68: iconst_1
      //   69: anewarray 4	java/lang/Object
      //   72: dup
      //   73: iconst_0
      //   74: aload_3
      //   75: invokevirtual 114	java/lang/Throwable:getMessage	()Ljava/lang/String;
      //   78: aastore
      //   79: invokestatic 73	timber/log/Timber:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   82: invokestatic 120	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
      //   85: ldc 122
      //   87: invokevirtual 126	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
      //   90: astore_2
      //   91: new 128	java/io/BufferedReader
      //   94: dup
      //   95: new 130	java/io/InputStreamReader
      //   98: dup
      //   99: aload_2
      //   100: invokevirtual 136	java/lang/Process:getInputStream	()Ljava/io/InputStream;
      //   103: getstatic 35	com/squareup/api/util/EnvironmentDiscovery$Root:UTF_8	Ljava/nio/charset/Charset;
      //   106: invokespecial 139	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
      //   109: invokespecial 142	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   112: astore 4
      //   114: aload 4
      //   116: invokevirtual 145	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   119: astore_0
      //   120: aload 4
      //   122: invokevirtual 148	java/io/BufferedReader:close	()V
      //   125: aload_2
      //   126: ifnull +7 -> 133
      //   129: aload_2
      //   130: invokevirtual 151	java/lang/Process:destroy	()V
      //   133: aload 4
      //   135: invokevirtual 148	java/io/BufferedReader:close	()V
      //   138: aload_0
      //   139: astore_2
      //   140: goto +82 -> 222
      //   143: aload_0
      //   144: astore_1
      //   145: aload 4
      //   147: astore_0
      //   148: goto +18 -> 166
      //   151: astore_0
      //   152: aload 4
      //   154: astore_1
      //   155: goto +6 -> 161
      //   158: astore_0
      //   159: aconst_null
      //   160: astore_1
      //   161: goto +88 -> 249
      //   164: aconst_null
      //   165: astore_0
      //   166: goto +17 -> 183
      //   169: astore_0
      //   170: aconst_null
      //   171: astore_1
      //   172: aload 5
      //   174: astore_2
      //   175: goto +74 -> 249
      //   178: aconst_null
      //   179: astore_0
      //   180: aload 4
      //   182: astore_2
      //   183: ldc -103
      //   185: iconst_1
      //   186: anewarray 4	java/lang/Object
      //   189: dup
      //   190: iconst_0
      //   191: aload_3
      //   192: invokevirtual 114	java/lang/Throwable:getMessage	()Ljava/lang/String;
      //   195: aastore
      //   196: invokestatic 73	timber/log/Timber:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   199: aload_2
      //   200: ifnull +7 -> 207
      //   203: aload_2
      //   204: invokevirtual 151	java/lang/Process:destroy	()V
      //   207: aload_1
      //   208: astore_2
      //   209: aload_0
      //   210: ifnull +12 -> 222
      //   213: aload_0
      //   214: invokevirtual 148	java/io/BufferedReader:close	()V
      //   217: aload_1
      //   218: astore_2
      //   219: goto +3 -> 222
      //   222: aload_2
      //   223: ifnull +18 -> 241
      //   226: ldc -101
      //   228: aload_2
      //   229: invokevirtual 158	java/lang/String:trim	()Ljava/lang/String;
      //   232: invokevirtual 162	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   235: ifeq +6 -> 241
      //   238: ldc 14
      //   240: areturn
      //   241: ldc 75
      //   243: areturn
      //   244: astore_3
      //   245: aload_0
      //   246: astore_1
      //   247: aload_3
      //   248: astore_0
      //   249: aload_2
      //   250: ifnull +7 -> 257
      //   253: aload_2
      //   254: invokevirtual 151	java/lang/Process:destroy	()V
      //   257: aload_1
      //   258: ifnull +7 -> 265
      //   261: aload_1
      //   262: invokevirtual 148	java/io/BufferedReader:close	()V
      //   265: aload_0
      //   266: athrow
      //   267: astore_0
      //   268: goto -90 -> 178
      //   271: astore_0
      //   272: goto -108 -> 164
      //   275: astore_0
      //   276: aload 4
      //   278: astore_0
      //   279: goto -113 -> 166
      //   282: astore_1
      //   283: goto -140 -> 143
      //   286: astore_1
      //   287: goto -149 -> 138
      //   290: astore_0
      //   291: aload_1
      //   292: astore_2
      //   293: goto -71 -> 222
      //   296: astore_1
      //   297: goto -32 -> 265
      // Local variable table:
      //   start	length	slot	name	signature
      //   48	11	0	localThrowable1	Throwable
      //   119	29	0	localObject1	Object
      //   151	1	0	localObject2	Object
      //   158	1	0	localObject3	Object
      //   165	1	0	localObject4	Object
      //   169	1	0	localObject5	Object
      //   179	87	0	localObject6	Object
      //   267	1	0	localIOException1	java.io.IOException
      //   271	1	0	localIOException2	java.io.IOException
      //   275	1	0	localIOException3	java.io.IOException
      //   278	1	0	localBufferedReader1	java.io.BufferedReader
      //   290	1	0	localIOException4	java.io.IOException
      //   2	260	1	localObject7	Object
      //   282	1	1	localIOException5	java.io.IOException
      //   286	6	1	localIOException6	java.io.IOException
      //   296	1	1	localIOException7	java.io.IOException
      //   44	249	2	localObject8	Object
      //   50	142	3	localThrowable2	Throwable
      //   244	4	3	localObject9	Object
      //   7	270	4	localBufferedReader2	java.io.BufferedReader
      //   4	169	5	localObject10	Object
      // Exception table:
      //   from	to	target	type
      //   9	45	48	java/lang/Throwable
      //   114	120	151	finally
      //   120	125	151	finally
      //   91	114	158	finally
      //   82	91	169	finally
      //   183	199	244	finally
      //   82	91	267	java/io/IOException
      //   91	114	271	java/io/IOException
      //   114	120	275	java/io/IOException
      //   120	125	282	java/io/IOException
      //   133	138	286	java/io/IOException
      //   213	217	290	java/io/IOException
      //   261	265	296	java/io/IOException
    }
    
    private static String checkForSu()
    {
      String[] arrayOfString = new String[8];
      arrayOfString[0] = "/data/local/bin/su";
      arrayOfString[1] = "/data/local/xbin/su";
      arrayOfString[2] = "/data/local/su";
      arrayOfString[3] = "/system/bin/failsafe/su";
      arrayOfString[4] = "/system/bin/su";
      arrayOfString[5] = "/system/sd/su";
      arrayOfString[6] = "/system/xbin/su";
      arrayOfString[7] = "/sbin/su";
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = arrayOfString[i];
        boolean bool = new File(str).exists();
        Timber.d("%s exists? %s", new Object[] { str, Boolean.valueOf(bool) });
        if (bool) {
          return "rsu";
        }
        i += 1;
      }
      return "";
    }
    
    private static String checkForSuPackages(Context paramContext)
    {
      HashSet localHashSet = new HashSet();
      localHashSet.add("com.z4mod.z4root");
      localHashSet.add("com.noshufou.android.su");
      localHashSet.add("com.cyanogenmod.cmparts");
      paramContext = paramContext.getPackageManager().getInstalledApplications(0);
      if (paramContext == null) {
        return "";
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        if (localHashSet.contains(((ApplicationInfo)paramContext.next()).packageName)) {
          return "rpk";
        }
      }
      return "";
    }
    
    private static String getRootFlags(Context paramContext)
    {
      String[] arrayOfString = new String[4];
      arrayOfString[0] = checkForSu();
      arrayOfString[1] = checkForDataWriteable();
      arrayOfString[2] = checkForRoSecure();
      arrayOfString[3] = checkForSuPackages(paramContext);
      paramContext = new StringBuilder();
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = arrayOfString[i];
        if (!isBlank(str))
        {
          if (paramContext.length() > 0) {
            paramContext.append(" ");
          }
          paramContext.append(str);
        }
        i += 1;
      }
      paramContext = paramContext.toString();
      Timber.d("Root string is '%s'", new Object[] { paramContext });
      return paramContext;
    }
    
    private static boolean isBlank(CharSequence paramCharSequence)
    {
      return (paramCharSequence == null) || (paramCharSequence.toString().trim().length() == 0);
    }
    
    public String getEnvironment()
    {
      return this.environment;
    }
  }
}
