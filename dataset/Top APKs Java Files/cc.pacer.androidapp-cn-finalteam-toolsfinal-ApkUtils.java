package cn.finalteam.toolsfinal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApkUtils
{
  public ApkUtils() {}
  
  /* Error */
  public static String getChannelFromApk(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 19	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   4: getfield 25	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   7: astore_0
    //   8: new 27	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 28	java/lang/StringBuilder:<init>	()V
    //   15: ldc 30
    //   17: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: aload_1
    //   21: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: invokevirtual 38	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   27: astore 6
    //   29: new 40	java/util/zip/ZipFile
    //   32: dup
    //   33: aload_0
    //   34: invokespecial 43	java/util/zip/ZipFile:<init>	(Ljava/lang/String;)V
    //   37: astore 4
    //   39: aload 4
    //   41: astore_0
    //   42: aload 4
    //   44: invokevirtual 47	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   47: astore 5
    //   49: aload 4
    //   51: astore_0
    //   52: aload 5
    //   54: invokeinterface 53 1 0
    //   59: ifeq +162 -> 221
    //   62: aload 4
    //   64: astore_0
    //   65: aload 5
    //   67: invokeinterface 57 1 0
    //   72: checkcast 59	java/util/zip/ZipEntry
    //   75: invokevirtual 62	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   78: astore_3
    //   79: aload 4
    //   81: astore_0
    //   82: aload_3
    //   83: aload 6
    //   85: invokevirtual 68	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   88: istore_2
    //   89: iload_2
    //   90: ifeq -41 -> 49
    //   93: aload_3
    //   94: astore_0
    //   95: aload 4
    //   97: ifnull +10 -> 107
    //   100: aload 4
    //   102: invokevirtual 71	java/util/zip/ZipFile:close	()V
    //   105: aload_3
    //   106: astore_0
    //   107: aload_0
    //   108: aload_1
    //   109: invokevirtual 75	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   112: astore_1
    //   113: aload_1
    //   114: ifnull +98 -> 212
    //   117: aload_1
    //   118: arraylength
    //   119: iconst_2
    //   120: if_icmplt +92 -> 212
    //   123: aload_0
    //   124: aload 6
    //   126: invokevirtual 79	java/lang/String:length	()I
    //   129: invokevirtual 83	java/lang/String:substring	(I)Ljava/lang/String;
    //   132: areturn
    //   133: astore_0
    //   134: aload_0
    //   135: invokevirtual 86	java/io/IOException:printStackTrace	()V
    //   138: aload_3
    //   139: astore_0
    //   140: goto -33 -> 107
    //   143: astore 5
    //   145: aconst_null
    //   146: astore_3
    //   147: aload_3
    //   148: astore_0
    //   149: aload 5
    //   151: invokevirtual 86	java/io/IOException:printStackTrace	()V
    //   154: aload_3
    //   155: ifnull +60 -> 215
    //   158: aload_3
    //   159: invokevirtual 71	java/util/zip/ZipFile:close	()V
    //   162: ldc 88
    //   164: astore_0
    //   165: goto -58 -> 107
    //   168: astore_0
    //   169: aload_0
    //   170: invokevirtual 86	java/io/IOException:printStackTrace	()V
    //   173: ldc 88
    //   175: astore_0
    //   176: goto -69 -> 107
    //   179: astore_1
    //   180: aconst_null
    //   181: astore_0
    //   182: aload_0
    //   183: ifnull +7 -> 190
    //   186: aload_0
    //   187: invokevirtual 71	java/util/zip/ZipFile:close	()V
    //   190: aload_1
    //   191: athrow
    //   192: astore_0
    //   193: aload_0
    //   194: invokevirtual 86	java/io/IOException:printStackTrace	()V
    //   197: goto -7 -> 190
    //   200: astore_1
    //   201: goto -19 -> 182
    //   204: astore 5
    //   206: aload 4
    //   208: astore_3
    //   209: goto -62 -> 147
    //   212: ldc 88
    //   214: areturn
    //   215: ldc 88
    //   217: astore_0
    //   218: goto -111 -> 107
    //   221: ldc 88
    //   223: astore_3
    //   224: goto -131 -> 93
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	227	0	paramContext	Context
    //   0	227	1	paramString	String
    //   88	2	2	bool	boolean
    //   78	146	3	localObject	Object
    //   37	170	4	localZipFile	java.util.zip.ZipFile
    //   47	19	5	localEnumeration	java.util.Enumeration
    //   143	7	5	localIOException1	java.io.IOException
    //   204	1	5	localIOException2	java.io.IOException
    //   27	98	6	str	String
    // Exception table:
    //   from	to	target	type
    //   100	105	133	java/io/IOException
    //   29	39	143	java/io/IOException
    //   158	162	168	java/io/IOException
    //   29	39	179	finally
    //   186	190	192	java/io/IOException
    //   42	49	200	finally
    //   52	62	200	finally
    //   65	79	200	finally
    //   82	89	200	finally
    //   149	154	200	finally
    //   42	49	204	java/io/IOException
    //   52	62	204	java/io/IOException
    //   65	79	204	java/io/IOException
    //   82	89	204	java/io/IOException
  }
  
  public static void install(Context paramContext, File paramFile)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  public static boolean isAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static void uninstall(Context paramContext, String paramString)
  {
    paramContext.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString)));
  }
}
