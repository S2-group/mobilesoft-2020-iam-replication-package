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
    //   7: astore_3
    //   8: new 27	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 28	java/lang/StringBuilder:<init>	()V
    //   15: ldc 30
    //   17: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: aload_1
    //   21: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: invokevirtual 38	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   27: astore 6
    //   29: ldc 40
    //   31: astore 4
    //   33: aconst_null
    //   34: astore_0
    //   35: aconst_null
    //   36: astore 5
    //   38: new 42	java/util/zip/ZipFile
    //   41: dup
    //   42: aload_3
    //   43: invokespecial 45	java/util/zip/ZipFile:<init>	(Ljava/lang/String;)V
    //   46: astore_3
    //   47: aload_3
    //   48: invokevirtual 49	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   51: astore 5
    //   53: aload 4
    //   55: astore_0
    //   56: aload 5
    //   58: invokeinterface 55 1 0
    //   63: ifeq +28 -> 91
    //   66: aload 5
    //   68: invokeinterface 59 1 0
    //   73: checkcast 61	java/util/zip/ZipEntry
    //   76: invokevirtual 64	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   79: astore_0
    //   80: aload_0
    //   81: aload 6
    //   83: invokevirtual 70	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   86: istore_2
    //   87: iload_2
    //   88: ifeq -35 -> 53
    //   91: aload_3
    //   92: ifnull +125 -> 217
    //   95: aload_3
    //   96: invokevirtual 73	java/util/zip/ZipFile:close	()V
    //   99: aload_0
    //   100: aload_1
    //   101: invokevirtual 77	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   104: astore 4
    //   106: ldc 40
    //   108: astore_3
    //   109: aload_3
    //   110: astore_1
    //   111: aload 4
    //   113: ifnull +22 -> 135
    //   116: aload_3
    //   117: astore_1
    //   118: aload 4
    //   120: arraylength
    //   121: iconst_2
    //   122: if_icmplt +13 -> 135
    //   125: aload_0
    //   126: aload 6
    //   128: invokevirtual 81	java/lang/String:length	()I
    //   131: invokevirtual 85	java/lang/String:substring	(I)Ljava/lang/String;
    //   134: astore_1
    //   135: aload_1
    //   136: areturn
    //   137: astore_3
    //   138: aload_3
    //   139: invokevirtual 88	java/io/IOException:printStackTrace	()V
    //   142: goto -43 -> 99
    //   145: astore_0
    //   146: aload 5
    //   148: astore_3
    //   149: aload_0
    //   150: astore 5
    //   152: aload_3
    //   153: astore_0
    //   154: aload 5
    //   156: invokevirtual 88	java/io/IOException:printStackTrace	()V
    //   159: aload 4
    //   161: astore_0
    //   162: aload_3
    //   163: ifnull -64 -> 99
    //   166: aload_3
    //   167: invokevirtual 73	java/util/zip/ZipFile:close	()V
    //   170: aload 4
    //   172: astore_0
    //   173: goto -74 -> 99
    //   176: astore_0
    //   177: aload_0
    //   178: invokevirtual 88	java/io/IOException:printStackTrace	()V
    //   181: aload 4
    //   183: astore_0
    //   184: goto -85 -> 99
    //   187: astore_1
    //   188: aload_0
    //   189: ifnull +7 -> 196
    //   192: aload_0
    //   193: invokevirtual 73	java/util/zip/ZipFile:close	()V
    //   196: aload_1
    //   197: athrow
    //   198: astore_0
    //   199: aload_0
    //   200: invokevirtual 88	java/io/IOException:printStackTrace	()V
    //   203: goto -7 -> 196
    //   206: astore_1
    //   207: aload_3
    //   208: astore_0
    //   209: goto -21 -> 188
    //   212: astore 5
    //   214: goto -62 -> 152
    //   217: goto -118 -> 99
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	220	0	paramContext	Context
    //   0	220	1	paramString	String
    //   86	2	2	bool	boolean
    //   7	110	3	localObject1	Object
    //   137	2	3	localIOException1	java.io.IOException
    //   148	60	3	localObject2	Object
    //   31	151	4	localObject3	Object
    //   36	119	5	localObject4	Object
    //   212	1	5	localIOException2	java.io.IOException
    //   27	100	6	str	String
    // Exception table:
    //   from	to	target	type
    //   95	99	137	java/io/IOException
    //   38	47	145	java/io/IOException
    //   166	170	176	java/io/IOException
    //   38	47	187	finally
    //   154	159	187	finally
    //   192	196	198	java/io/IOException
    //   47	53	206	finally
    //   56	87	206	finally
    //   47	53	212	java/io/IOException
    //   56	87	212	java/io/IOException
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
