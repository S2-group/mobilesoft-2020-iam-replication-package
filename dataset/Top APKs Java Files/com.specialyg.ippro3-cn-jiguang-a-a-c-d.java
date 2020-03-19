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
    //   13: astore 5
    //   15: iconst_0
    //   16: istore_2
    //   17: aload 5
    //   19: iconst_0
    //   20: invokevirtual 28	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   23: astore 5
    //   25: aload 5
    //   27: invokeinterface 34 1 0
    //   32: istore_3
    //   33: iload_2
    //   34: iload_3
    //   35: if_icmpge +105 -> 140
    //   38: aload 5
    //   40: iload_2
    //   41: invokeinterface 38 2 0
    //   46: checkcast 40	android/content/pm/PackageInfo
    //   49: astore 6
    //   51: new 42	cn/jiguang/a/a/c/h
    //   54: dup
    //   55: invokespecial 43	cn/jiguang/a/a/c/h:<init>	()V
    //   58: astore 7
    //   60: aload 7
    //   62: aload 6
    //   64: getfield 47	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   67: aload_0
    //   68: invokevirtual 22	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   71: invokevirtual 53	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   74: invokeinterface 59 1 0
    //   79: putfield 62	cn/jiguang/a/a/c/h:a	Ljava/lang/String;
    //   82: aload 7
    //   84: aload 6
    //   86: getfield 65	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   89: putfield 68	cn/jiguang/a/a/c/h:b	Ljava/lang/String;
    //   92: aload 7
    //   94: aload 6
    //   96: getfield 71	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   99: putfield 74	cn/jiguang/a/a/c/h:c	Ljava/lang/String;
    //   102: aload 7
    //   104: aload 6
    //   106: getfield 78	android/content/pm/PackageInfo:versionCode	I
    //   109: putfield 81	cn/jiguang/a/a/c/h:d	I
    //   112: aload 7
    //   114: aload 6
    //   116: getfield 47	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   119: invokestatic 86	cn/jiguang/g/d:a	(Landroid/content/pm/ApplicationInfo;)I
    //   122: putfield 89	cn/jiguang/a/a/c/h:e	I
    //   125: aload 4
    //   127: aload 7
    //   129: invokevirtual 93	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   132: pop
    //   133: iload_2
    //   134: iconst_1
    //   135: iadd
    //   136: istore_2
    //   137: goto -112 -> 25
    //   140: aload 4
    //   142: areturn
    //   143: astore_0
    //   144: aload 4
    //   146: areturn
    //   147: astore 6
    //   149: goto -16 -> 133
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	152	0	paramContext	Context
    //   0	152	1	paramBoolean	boolean
    //   16	121	2	i	int
    //   32	4	3	j	int
    //   7	138	4	localArrayList	ArrayList
    //   13	26	5	localObject	Object
    //   49	66	6	localPackageInfo	PackageInfo
    //   147	1	6	localThrowable	Throwable
    //   58	70	7	localH	h
    // Exception table:
    //   from	to	target	type
    //   9	15	143	java/lang/IndexOutOfBoundsException
    //   9	15	143	java/lang/Throwable
    //   17	25	143	java/lang/IndexOutOfBoundsException
    //   17	25	143	java/lang/Throwable
    //   25	33	143	java/lang/IndexOutOfBoundsException
    //   25	33	143	java/lang/Throwable
    //   38	133	143	java/lang/IndexOutOfBoundsException
    //   38	133	147	java/lang/Throwable
  }
  
  public static String[] a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 4096).requestedPermissions;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
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
    return paramContext;
  }
}
