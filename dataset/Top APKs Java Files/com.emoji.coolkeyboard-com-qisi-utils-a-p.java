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
    //   0: aload_0
    //   1: invokevirtual 18	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_2
    //   5: aload_2
    //   6: iconst_0
    //   7: invokevirtual 113	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   10: astore_0
    //   11: aload_0
    //   12: areturn
    //   13: astore_0
    //   14: aload_0
    //   15: invokevirtual 96	java/lang/Exception:printStackTrace	()V
    //   18: new 115	java/util/ArrayList
    //   21: dup
    //   22: invokespecial 116	java/util/ArrayList:<init>	()V
    //   25: astore_3
    //   26: aconst_null
    //   27: astore_0
    //   28: invokestatic 122	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   31: ldc 124
    //   33: invokevirtual 128	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   36: astore 4
    //   38: new 130	java/io/BufferedReader
    //   41: dup
    //   42: new 132	java/io/InputStreamReader
    //   45: dup
    //   46: aload 4
    //   48: invokevirtual 138	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   51: invokespecial 141	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   54: invokespecial 144	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   57: astore_1
    //   58: aload_1
    //   59: astore_0
    //   60: aload_1
    //   61: invokevirtual 147	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   64: astore 5
    //   66: aload 5
    //   68: ifnull +60 -> 128
    //   71: aload_1
    //   72: astore_0
    //   73: aload_3
    //   74: aload_2
    //   75: aload 5
    //   77: aload 5
    //   79: bipush 58
    //   81: invokevirtual 151	java/lang/String:indexOf	(I)I
    //   84: iconst_1
    //   85: iadd
    //   86: invokevirtual 155	java/lang/String:substring	(I)Ljava/lang/String;
    //   89: iconst_1
    //   90: invokevirtual 24	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   93: invokeinterface 161 2 0
    //   98: pop
    //   99: goto -41 -> 58
    //   102: astore_2
    //   103: aload_1
    //   104: astore_0
    //   105: aload_2
    //   106: invokevirtual 96	java/lang/Exception:printStackTrace	()V
    //   109: aload_3
    //   110: astore_0
    //   111: aload_1
    //   112: ifnull -101 -> 11
    //   115: aload_1
    //   116: invokevirtual 164	java/io/BufferedReader:close	()V
    //   119: aload_3
    //   120: areturn
    //   121: astore_0
    //   122: aload_0
    //   123: invokevirtual 96	java/lang/Exception:printStackTrace	()V
    //   126: aload_3
    //   127: areturn
    //   128: aload_1
    //   129: astore_0
    //   130: aload 4
    //   132: invokevirtual 168	java/lang/Process:waitFor	()I
    //   135: pop
    //   136: aload_3
    //   137: astore_0
    //   138: aload_1
    //   139: ifnull -128 -> 11
    //   142: aload_1
    //   143: invokevirtual 164	java/io/BufferedReader:close	()V
    //   146: aload_3
    //   147: areturn
    //   148: astore_0
    //   149: aload_0
    //   150: invokevirtual 96	java/lang/Exception:printStackTrace	()V
    //   153: aload_3
    //   154: areturn
    //   155: astore_2
    //   156: aload_0
    //   157: astore_1
    //   158: aload_2
    //   159: astore_0
    //   160: aload_1
    //   161: ifnull +7 -> 168
    //   164: aload_1
    //   165: invokevirtual 164	java/io/BufferedReader:close	()V
    //   168: aload_0
    //   169: athrow
    //   170: astore_1
    //   171: aload_1
    //   172: invokevirtual 96	java/lang/Exception:printStackTrace	()V
    //   175: goto -7 -> 168
    //   178: astore_2
    //   179: aload_0
    //   180: astore_1
    //   181: aload_2
    //   182: astore_0
    //   183: goto -23 -> 160
    //   186: astore_2
    //   187: aconst_null
    //   188: astore_1
    //   189: goto -86 -> 103
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	192	0	paramContext	Context
    //   57	108	1	localObject1	Object
    //   170	2	1	localException1	Exception
    //   180	9	1	localContext	Context
    //   4	71	2	localPackageManager	PackageManager
    //   102	4	2	localException2	Exception
    //   155	4	2	localObject2	Object
    //   178	4	2	localObject3	Object
    //   186	1	2	localException3	Exception
    //   25	129	3	localArrayList	java.util.ArrayList
    //   36	95	4	localProcess	Process
    //   64	14	5	str	String
    // Exception table:
    //   from	to	target	type
    //   5	11	13	java/lang/Exception
    //   60	66	102	java/lang/Exception
    //   73	99	102	java/lang/Exception
    //   130	136	102	java/lang/Exception
    //   115	119	121	java/lang/Exception
    //   142	146	148	java/lang/Exception
    //   28	58	155	finally
    //   164	168	170	java/lang/Exception
    //   60	66	178	finally
    //   73	99	178	finally
    //   105	109	178	finally
    //   130	136	178	finally
    //   28	58	186	java/lang/Exception
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
    HashSet localHashSet = new HashSet();
    paramContext = b(paramContext).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.startsWith("com.ikeyboard.theme")) {
        localHashSet.add(localPackageInfo.packageName);
      }
    }
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
