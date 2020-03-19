package cn.jiguang.a.a.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public final class d
{
  /* Error */
  public static ArrayList<h> a(Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: new 12	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 16	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: aload_0
    //   10: invokevirtual 22	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: iconst_0
    //   14: invokevirtual 28	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   17: astore 5
    //   19: iconst_0
    //   20: istore_2
    //   21: aload 5
    //   23: invokeinterface 34 1 0
    //   28: istore_3
    //   29: iload_2
    //   30: iload_3
    //   31: if_icmpge +106 -> 137
    //   34: aload 5
    //   36: iload_2
    //   37: invokeinterface 38 2 0
    //   42: checkcast 40	android/content/pm/PackageInfo
    //   45: astore 6
    //   47: new 42	cn/jiguang/a/a/c/h
    //   50: dup
    //   51: invokespecial 43	cn/jiguang/a/a/c/h:<init>	()V
    //   54: astore 7
    //   56: aload 7
    //   58: aload 6
    //   60: getfield 47	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   63: aload_0
    //   64: invokevirtual 22	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   67: invokevirtual 53	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   70: invokeinterface 59 1 0
    //   75: putfield 62	cn/jiguang/a/a/c/h:a	Ljava/lang/String;
    //   78: aload 7
    //   80: aload 6
    //   82: getfield 65	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   85: putfield 68	cn/jiguang/a/a/c/h:b	Ljava/lang/String;
    //   88: aload 7
    //   90: aload 6
    //   92: getfield 71	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   95: putfield 74	cn/jiguang/a/a/c/h:c	Ljava/lang/String;
    //   98: aload 7
    //   100: aload 6
    //   102: getfield 78	android/content/pm/PackageInfo:versionCode	I
    //   105: putfield 81	cn/jiguang/a/a/c/h:d	I
    //   108: aload 7
    //   110: aload 6
    //   112: getfield 47	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   115: invokestatic 86	cn/jiguang/g/d:a	(Landroid/content/pm/ApplicationInfo;)I
    //   118: putfield 89	cn/jiguang/a/a/c/h:e	I
    //   121: aload 4
    //   123: aload 7
    //   125: invokevirtual 93	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   128: pop
    //   129: iload_2
    //   130: iconst_1
    //   131: iadd
    //   132: istore_2
    //   133: goto -112 -> 21
    //   136: astore_0
    //   137: aload 4
    //   139: areturn
    //   140: astore_0
    //   141: aload 4
    //   143: areturn
    //   144: astore 6
    //   146: goto -17 -> 129
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	149	0	paramContext	Context
    //   0	149	1	paramBoolean	boolean
    //   20	113	2	i	int
    //   28	4	3	j	int
    //   7	135	4	localArrayList	ArrayList
    //   17	18	5	localList	java.util.List
    //   45	66	6	localPackageInfo	PackageInfo
    //   144	1	6	localThrowable	Throwable
    //   54	70	7	localH	h
    // Exception table:
    //   from	to	target	type
    //   9	19	136	java/lang/Throwable
    //   21	29	136	java/lang/Throwable
    //   9	19	140	java/lang/IndexOutOfBoundsException
    //   21	29	140	java/lang/IndexOutOfBoundsException
    //   34	129	140	java/lang/IndexOutOfBoundsException
    //   34	129	144	java/lang/Throwable
  }
  
  public static String[] a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 4096).requestedPermissions;
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static JSONArray b(Context paramContext)
  {
    Object localObject = a(paramContext, true);
    paramContext = new JSONArray();
    try
    {
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        h localH = (h)((Iterator)localObject).next();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("name", localH.a);
        localJSONObject.put("pkg", localH.b);
        localJSONObject.put("ver_name", localH.c);
        localJSONObject.put("ver_code", localH.d);
        localJSONObject.put("install_type", localH.e);
        paramContext.put(localJSONObject);
      }
      return paramContext;
    }
    catch (Throwable localThrowable) {}
  }
}
