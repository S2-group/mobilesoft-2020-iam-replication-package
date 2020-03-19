package com.quick.easyswipe.swipe.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import com.quick.easyswipe.swipe.c;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class b
{
  private static ArrayList<String> a = new ArrayList();
  private static long b;
  
  public static ArrayList<String> a()
  {
    if (com.quick.easyswipe.swipe.common.b.a) {
      Log.v("easy-swipe", "loadRecentAppList");
    }
    b = System.currentTimeMillis();
    ArrayList localArrayList = a(c.a().l());
    HashSet localHashSet = new HashSet();
    int i = localArrayList.size() - 1;
    if (i >= 0)
    {
      String str2 = (String)localArrayList.get(i);
      String str1 = str2;
      if (str2.contains(":")) {
        str1 = str2.split(":")[0];
      }
      if ((localHashSet.contains(str1)) || (!a(str1))) {
        localArrayList.remove(i);
      }
      for (;;)
      {
        i -= 1;
        break;
        localHashSet.add(str1);
      }
    }
    return localArrayList;
  }
  
  /* Error */
  public static ArrayList<String> a(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: new 13	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 16	java/util/ArrayList:<init>	()V
    //   9: astore 5
    //   11: aload_0
    //   12: invokevirtual 102	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   15: iconst_0
    //   16: invokevirtual 108	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   19: astore 6
    //   21: invokestatic 114	com/quick/easyswipe/swipe/i:d	()Z
    //   24: istore 4
    //   26: aload_0
    //   27: invokestatic 119	com/quick/easyswipe/swipe/common/b/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   30: astore_0
    //   31: iconst_0
    //   32: istore_3
    //   33: iload_3
    //   34: aload 6
    //   36: invokeinterface 122 1 0
    //   41: if_icmpge +141 -> 182
    //   44: aload 6
    //   46: iload_3
    //   47: invokeinterface 123 2 0
    //   52: checkcast 125	android/content/pm/PackageInfo
    //   55: astore 7
    //   57: aload 7
    //   59: getfield 129	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   62: getfield 135	android/content/pm/ApplicationInfo:flags	I
    //   65: iconst_1
    //   66: iand
    //   67: ifne +89 -> 156
    //   70: aload 7
    //   72: getfield 129	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   75: getfield 135	android/content/pm/ApplicationInfo:flags	I
    //   78: sipush 128
    //   81: iand
    //   82: ifne +74 -> 156
    //   85: aload 7
    //   87: getfield 129	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   90: getfield 135	android/content/pm/ApplicationInfo:flags	I
    //   93: ldc -120
    //   95: iand
    //   96: ifne +60 -> 156
    //   99: aload_0
    //   100: ifnull +38 -> 138
    //   103: aload_0
    //   104: aload 7
    //   106: getfield 140	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   109: invokevirtual 143	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   112: ifeq +26 -> 138
    //   115: iload 4
    //   117: ifne +57 -> 174
    //   120: aload 5
    //   122: aload 7
    //   124: getfield 140	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   127: invokevirtual 144	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   130: pop
    //   131: iload_1
    //   132: iconst_1
    //   133: iadd
    //   134: istore_1
    //   135: goto +39 -> 174
    //   138: aload 5
    //   140: aload 7
    //   142: getfield 140	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   145: invokevirtual 144	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   148: pop
    //   149: iload_1
    //   150: iconst_1
    //   151: iadd
    //   152: istore_1
    //   153: goto +21 -> 174
    //   156: iload_1
    //   157: istore_2
    //   158: iload_3
    //   159: iconst_1
    //   160: iadd
    //   161: istore_3
    //   162: iload_2
    //   163: istore_1
    //   164: goto -131 -> 33
    //   167: astore_0
    //   168: aconst_null
    //   169: areturn
    //   170: astore_0
    //   171: aload 5
    //   173: areturn
    //   174: iload_1
    //   175: istore_2
    //   176: iload_1
    //   177: bipush 8
    //   179: if_icmpne -21 -> 158
    //   182: aload 5
    //   184: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	185	0	paramContext	Context
    //   1	179	1	i	int
    //   157	19	2	j	int
    //   32	130	3	k	int
    //   24	92	4	bool	boolean
    //   9	174	5	localArrayList	ArrayList
    //   19	26	6	localList	java.util.List
    //   55	86	7	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   2	11	167	java/lang/Exception
    //   11	31	170	java/lang/Exception
    //   33	99	170	java/lang/Exception
    //   103	115	170	java/lang/Exception
    //   120	131	170	java/lang/Exception
    //   138	149	170	java/lang/Exception
  }
  
  public static ArrayList<String> a(boolean paramBoolean)
  {
    ArrayList localArrayList = a;
    if (!paramBoolean) {}
    try
    {
      if ((a.size() == 0) || (System.currentTimeMillis() - b > 600000L))
      {
        a.clear();
        a.addAll(a());
      }
      return (ArrayList)a.clone();
    }
    finally {}
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramString) != null;
  }
  
  public static boolean a(String paramString)
  {
    return (b(paramString) != null) && (!TextUtils.isEmpty(b(c.a().l(), paramString)));
  }
  
  public static Bitmap b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    try
    {
      paramString = com.quick.easyswipe.swipe.common.b.b.a().a(paramString, c.a().l());
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static String b(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return null;
      try
      {
        paramContext = paramContext.getPackageManager();
        paramString = paramContext.getApplicationInfo(paramString, 8192);
        if (paramString != null)
        {
          paramContext = paramString.loadLabel(paramContext).toString();
          return paramContext;
        }
      }
      catch (Exception paramContext) {}
    }
    return null;
  }
}
