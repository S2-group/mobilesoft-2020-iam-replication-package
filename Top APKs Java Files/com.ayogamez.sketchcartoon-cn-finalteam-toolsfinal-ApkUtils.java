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
    //   7: astore 4
    //   9: new 27	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 28	java/lang/StringBuilder:<init>	()V
    //   16: astore_0
    //   17: aload_0
    //   18: ldc 30
    //   20: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: pop
    //   30: aload_0
    //   31: invokevirtual 38	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   34: astore 7
    //   36: ldc 40
    //   38: astore_3
    //   39: aconst_null
    //   40: astore 6
    //   42: aconst_null
    //   43: astore_0
    //   44: new 42	java/util/zip/ZipFile
    //   47: dup
    //   48: aload 4
    //   50: invokespecial 45	java/util/zip/ZipFile:<init>	(Ljava/lang/String;)V
    //   53: astore 5
    //   55: aload 5
    //   57: invokevirtual 49	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   60: astore 4
    //   62: aload_3
    //   63: astore_0
    //   64: aload 4
    //   66: invokeinterface 55 1 0
    //   71: ifeq +28 -> 99
    //   74: aload 4
    //   76: invokeinterface 59 1 0
    //   81: checkcast 61	java/util/zip/ZipEntry
    //   84: invokevirtual 64	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   87: astore_0
    //   88: aload_0
    //   89: aload 7
    //   91: invokevirtual 70	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   94: istore_2
    //   95: iload_2
    //   96: ifeq -34 -> 62
    //   99: aload_0
    //   100: astore 4
    //   102: aload 5
    //   104: ifnull +76 -> 180
    //   107: aload_0
    //   108: astore 4
    //   110: aload 5
    //   112: invokevirtual 73	java/util/zip/ZipFile:close	()V
    //   115: aload_0
    //   116: astore 4
    //   118: goto +62 -> 180
    //   121: astore_0
    //   122: aload_0
    //   123: invokevirtual 76	java/io/IOException:printStackTrace	()V
    //   126: goto +54 -> 180
    //   129: astore_0
    //   130: goto +87 -> 217
    //   133: astore 4
    //   135: goto +18 -> 153
    //   138: astore_1
    //   139: aload_0
    //   140: astore 5
    //   142: aload_1
    //   143: astore_0
    //   144: goto +73 -> 217
    //   147: astore 4
    //   149: aload 6
    //   151: astore 5
    //   153: aload 5
    //   155: astore_0
    //   156: aload 4
    //   158: invokevirtual 76	java/io/IOException:printStackTrace	()V
    //   161: aload_3
    //   162: astore 4
    //   164: aload 5
    //   166: ifnull +14 -> 180
    //   169: aload_3
    //   170: astore 4
    //   172: aload 5
    //   174: invokevirtual 73	java/util/zip/ZipFile:close	()V
    //   177: aload_3
    //   178: astore 4
    //   180: aload 4
    //   182: aload_1
    //   183: invokevirtual 80	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   186: astore_3
    //   187: ldc 40
    //   189: astore_1
    //   190: aload_1
    //   191: astore_0
    //   192: aload_3
    //   193: ifnull +22 -> 215
    //   196: aload_1
    //   197: astore_0
    //   198: aload_3
    //   199: arraylength
    //   200: iconst_2
    //   201: if_icmplt +14 -> 215
    //   204: aload 4
    //   206: aload 7
    //   208: invokevirtual 84	java/lang/String:length	()I
    //   211: invokevirtual 88	java/lang/String:substring	(I)Ljava/lang/String;
    //   214: astore_0
    //   215: aload_0
    //   216: areturn
    //   217: aload 5
    //   219: ifnull +16 -> 235
    //   222: aload 5
    //   224: invokevirtual 73	java/util/zip/ZipFile:close	()V
    //   227: goto +8 -> 235
    //   230: astore_1
    //   231: aload_1
    //   232: invokevirtual 76	java/io/IOException:printStackTrace	()V
    //   235: aload_0
    //   236: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	237	0	paramContext	Context
    //   0	237	1	paramString	String
    //   94	2	2	bool	boolean
    //   38	161	3	localObject1	Object
    //   7	110	4	localObject2	Object
    //   133	1	4	localIOException1	java.io.IOException
    //   147	10	4	localIOException2	java.io.IOException
    //   162	43	4	localObject3	Object
    //   53	170	5	localObject4	Object
    //   40	110	6	localObject5	Object
    //   34	173	7	str	String
    // Exception table:
    //   from	to	target	type
    //   110	115	121	java/io/IOException
    //   172	177	121	java/io/IOException
    //   55	62	129	finally
    //   64	95	129	finally
    //   55	62	133	java/io/IOException
    //   64	95	133	java/io/IOException
    //   44	55	138	finally
    //   156	161	138	finally
    //   44	55	147	java/io/IOException
    //   222	227	230	java/io/IOException
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
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null) {
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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramString);
    paramContext.startActivity(new Intent("android.intent.action.DELETE", Uri.parse(localStringBuilder.toString())));
  }
}
