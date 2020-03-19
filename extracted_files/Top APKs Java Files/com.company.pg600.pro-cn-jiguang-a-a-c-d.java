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
    //   0: iconst_0
    //   1: istore_2
    //   2: new 12	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 16	java/util/ArrayList:<init>	()V
    //   9: astore 4
    //   11: aload_0
    //   12: invokevirtual 22	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   15: iconst_0
    //   16: invokevirtual 28	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   19: astore 5
    //   21: aload 5
    //   23: invokeinterface 34 1 0
    //   28: istore_3
    //   29: iload_2
    //   30: iload_3
    //   31: if_icmplt +6 -> 37
    //   34: aload 4
    //   36: areturn
    //   37: aload 5
    //   39: iload_2
    //   40: invokeinterface 38 2 0
    //   45: checkcast 40	android/content/pm/PackageInfo
    //   48: astore 6
    //   50: new 42	cn/jiguang/a/a/c/h
    //   53: dup
    //   54: invokespecial 43	cn/jiguang/a/a/c/h:<init>	()V
    //   57: astore 7
    //   59: aload 7
    //   61: aload 6
    //   63: getfield 47	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   66: aload_0
    //   67: invokevirtual 22	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   70: invokevirtual 53	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   73: invokeinterface 59 1 0
    //   78: putfield 62	cn/jiguang/a/a/c/h:a	Ljava/lang/String;
    //   81: aload 7
    //   83: aload 6
    //   85: getfield 65	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   88: putfield 68	cn/jiguang/a/a/c/h:b	Ljava/lang/String;
    //   91: aload 7
    //   93: aload 6
    //   95: getfield 71	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   98: putfield 74	cn/jiguang/a/a/c/h:c	Ljava/lang/String;
    //   101: aload 7
    //   103: aload 6
    //   105: getfield 78	android/content/pm/PackageInfo:versionCode	I
    //   108: putfield 81	cn/jiguang/a/a/c/h:d	I
    //   111: aload 7
    //   113: aload 6
    //   115: getfield 47	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   118: invokestatic 86	cn/jiguang/g/d:a	(Landroid/content/pm/ApplicationInfo;)I
    //   121: putfield 89	cn/jiguang/a/a/c/h:e	I
    //   124: aload 4
    //   126: aload 7
    //   128: invokevirtual 93	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   131: pop
    //   132: iload_2
    //   133: iconst_1
    //   134: iadd
    //   135: istore_2
    //   136: goto -115 -> 21
    //   139: astore_0
    //   140: aload 4
    //   142: areturn
    //   143: astore_0
    //   144: aload 4
    //   146: areturn
    //   147: astore 6
    //   149: goto -17 -> 132
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	152	0	paramContext	Context
    //   0	152	1	paramBoolean	boolean
    //   1	135	2	i	int
    //   28	4	3	j	int
    //   9	136	4	localArrayList	ArrayList
    //   19	19	5	localList	java.util.List
    //   48	66	6	localPackageInfo	PackageInfo
    //   147	1	6	localThrowable	Throwable
    //   57	70	7	localH	h
    // Exception table:
    //   from	to	target	type
    //   11	21	139	java/lang/Throwable
    //   21	29	139	java/lang/Throwable
    //   37	132	139	java/lang/Throwable
    //   11	21	143	java/lang/IndexOutOfBoundsException
    //   21	29	143	java/lang/IndexOutOfBoundsException
    //   37	132	143	java/lang/IndexOutOfBoundsException
    //   37	132	147	java/lang/Throwable
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
      for (;;)
      {
        if (!((Iterator)localObject).hasNext()) {
          return paramContext;
        }
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
