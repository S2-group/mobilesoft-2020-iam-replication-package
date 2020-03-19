package com.qisi.utils.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class p
{
  public static int a(Context paramContext)
  {
    String str = paramContext.getPackageName();
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(str, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }
  
  public static void a(Context paramContext, String paramString)
  {
    paramContext.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString)));
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      if (TextUtils.isEmpty(paramString2))
      {
        paramString1 = paramContext.getPackageManager().getLaunchIntentForPackage(paramString1);
        if (paramString1 != null)
        {
          paramString1.addFlags(268435456);
          paramContext.startActivity(paramString1);
          return true;
        }
      }
      else
      {
        Intent localIntent = new Intent();
        localIntent.setComponent(new ComponentName(paramString1, paramString2));
        localIntent.addFlags(268435456);
        paramContext.startActivity(localIntent);
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static String b(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return null;
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = (String)paramContext.getApplicationLabel(paramContext.getApplicationInfo(paramString, 0));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  /* Error */
  public static List<PackageInfo> b(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokevirtual 18	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: astore_2
    //   8: aload_2
    //   9: iconst_0
    //   10: invokevirtual 113	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   13: astore_0
    //   14: ldc 2
    //   16: monitorexit
    //   17: aload_0
    //   18: areturn
    //   19: astore_0
    //   20: aload_0
    //   21: invokevirtual 96	java/lang/Exception:printStackTrace	()V
    //   24: new 115	java/util/ArrayList
    //   27: dup
    //   28: invokespecial 116	java/util/ArrayList:<init>	()V
    //   31: astore_3
    //   32: aconst_null
    //   33: astore_0
    //   34: invokestatic 122	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   37: ldc 124
    //   39: invokevirtual 128	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   42: astore 4
    //   44: new 130	java/io/BufferedReader
    //   47: dup
    //   48: new 132	java/io/InputStreamReader
    //   51: dup
    //   52: aload 4
    //   54: invokevirtual 138	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   57: invokespecial 141	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   60: invokespecial 144	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   63: astore_1
    //   64: aload_1
    //   65: astore_0
    //   66: aload_1
    //   67: invokevirtual 147	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   70: astore 5
    //   72: aload 5
    //   74: ifnull +72 -> 146
    //   77: aload_1
    //   78: astore_0
    //   79: aload_3
    //   80: aload_2
    //   81: aload 5
    //   83: aload 5
    //   85: bipush 58
    //   87: invokevirtual 151	java/lang/String:indexOf	(I)I
    //   90: iconst_1
    //   91: iadd
    //   92: invokevirtual 155	java/lang/String:substring	(I)Ljava/lang/String;
    //   95: iconst_1
    //   96: invokevirtual 24	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   99: invokeinterface 161 2 0
    //   104: pop
    //   105: goto -41 -> 64
    //   108: astore_2
    //   109: aload_1
    //   110: astore_0
    //   111: aload_2
    //   112: invokevirtual 96	java/lang/Exception:printStackTrace	()V
    //   115: aload_3
    //   116: astore_0
    //   117: aload_1
    //   118: ifnull -104 -> 14
    //   121: aload_1
    //   122: invokevirtual 164	java/io/BufferedReader:close	()V
    //   125: aload_3
    //   126: astore_0
    //   127: goto -113 -> 14
    //   130: astore_0
    //   131: aload_0
    //   132: invokevirtual 96	java/lang/Exception:printStackTrace	()V
    //   135: aload_3
    //   136: astore_0
    //   137: goto -123 -> 14
    //   140: astore_0
    //   141: ldc 2
    //   143: monitorexit
    //   144: aload_0
    //   145: athrow
    //   146: aload_1
    //   147: astore_0
    //   148: aload 4
    //   150: invokevirtual 168	java/lang/Process:waitFor	()I
    //   153: pop
    //   154: aload_3
    //   155: astore_0
    //   156: aload_1
    //   157: ifnull -143 -> 14
    //   160: aload_1
    //   161: invokevirtual 164	java/io/BufferedReader:close	()V
    //   164: aload_3
    //   165: astore_0
    //   166: goto -152 -> 14
    //   169: astore_0
    //   170: aload_0
    //   171: invokevirtual 96	java/lang/Exception:printStackTrace	()V
    //   174: aload_3
    //   175: astore_0
    //   176: goto -162 -> 14
    //   179: astore_2
    //   180: aload_0
    //   181: astore_1
    //   182: aload_2
    //   183: astore_0
    //   184: aload_1
    //   185: ifnull +7 -> 192
    //   188: aload_1
    //   189: invokevirtual 164	java/io/BufferedReader:close	()V
    //   192: aload_0
    //   193: athrow
    //   194: astore_1
    //   195: aload_1
    //   196: invokevirtual 96	java/lang/Exception:printStackTrace	()V
    //   199: goto -7 -> 192
    //   202: astore_2
    //   203: aload_0
    //   204: astore_1
    //   205: aload_2
    //   206: astore_0
    //   207: goto -23 -> 184
    //   210: astore_2
    //   211: aconst_null
    //   212: astore_1
    //   213: goto -104 -> 109
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	216	0	paramContext	Context
    //   63	126	1	localObject1	Object
    //   194	2	1	localException1	Exception
    //   204	9	1	localContext	Context
    //   7	74	2	localPackageManager	PackageManager
    //   108	4	2	localException2	Exception
    //   179	4	2	localObject2	Object
    //   202	4	2	localObject3	Object
    //   210	1	2	localException3	Exception
    //   31	144	3	localArrayList	java.util.ArrayList
    //   42	107	4	localProcess	Process
    //   70	14	5	str	String
    // Exception table:
    //   from	to	target	type
    //   8	14	19	java/lang/Exception
    //   66	72	108	java/lang/Exception
    //   79	105	108	java/lang/Exception
    //   148	154	108	java/lang/Exception
    //   121	125	130	java/lang/Exception
    //   3	8	140	finally
    //   8	14	140	finally
    //   20	32	140	finally
    //   121	125	140	finally
    //   131	135	140	finally
    //   160	164	140	finally
    //   170	174	140	finally
    //   188	192	140	finally
    //   192	194	140	finally
    //   195	199	140	finally
    //   160	164	169	java/lang/Exception
    //   34	64	179	finally
    //   188	192	194	java/lang/Exception
    //   66	72	202	finally
    //   79	105	202	finally
    //   111	115	202	finally
    //   148	154	202	finally
    //   34	64	210	java/lang/Exception
  }
  
  public static int c(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return -1;
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getPackageInfo(paramString, 16384).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      m.a(paramContext, false);
    }
    return -1;
  }
  
  public static Set<String> c(Context paramContext)
  {
    HashSet localHashSet;
    try
    {
      localHashSet = new HashSet();
      paramContext = b(paramContext).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if (localPackageInfo.packageName.startsWith("com.ikeyboard.theme")) {
          localHashSet.add(localPackageInfo.packageName);
        }
      }
    }
    finally {}
    return localHashSet;
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
        if (paramContext == null) {
          continue;
        }
        return true;
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          paramContext = null;
        }
      }
    }
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
}
