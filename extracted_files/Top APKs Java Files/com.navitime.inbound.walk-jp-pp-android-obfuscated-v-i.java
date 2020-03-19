package jp.pp.android.obfuscated.v;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class i
{
  private final Context a;
  private final SharedPreferences b;
  private final String c;
  
  public i(Context paramContext)
  {
    this.a = paramContext;
    this.c = this.a.getPackageName();
    this.b = this.a.getSharedPreferences(this.c + ".rsid", 1);
  }
  
  private static Object a(Class<?> paramClass, Object paramObject, String paramString, Object... paramVarArgs)
  {
    try
    {
      paramObject = new Class[paramVarArgs.length];
      int i = 0;
      while (i < paramVarArgs.length)
      {
        paramObject[i] = paramVarArgs[i].getClass();
        i += 1;
      }
      paramClass = paramClass.getDeclaredMethod(paramString, paramObject);
      paramClass.setAccessible(true);
      paramClass = paramClass.invoke(null, paramVarArgs);
      return paramClass;
    }
    catch (InvocationTargetException paramClass)
    {
      return null;
    }
    catch (IllegalAccessException paramClass)
    {
      return null;
    }
    catch (IllegalArgumentException paramClass)
    {
      return null;
    }
    catch (NoSuchMethodException paramClass)
    {
      return null;
    }
    catch (SecurityException paramClass) {}
    return null;
  }
  
  /* Error */
  private final String b()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 17	jp/pp/android/obfuscated/v/i:a	Landroid/content/Context;
    //   4: invokevirtual 85	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: iconst_0
    //   8: invokevirtual 91	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   11: astore_2
    //   12: aload_2
    //   13: invokeinterface 97 1 0
    //   18: astore 4
    //   20: aconst_null
    //   21: astore_2
    //   22: aload 4
    //   24: invokeinterface 103 1 0
    //   29: ifeq +108 -> 137
    //   32: aload 4
    //   34: invokeinterface 107 1 0
    //   39: checkcast 109	android/content/pm/ApplicationInfo
    //   42: astore_3
    //   43: aload_0
    //   44: getfield 17	jp/pp/android/obfuscated/v/i:a	Landroid/content/Context;
    //   47: aload_3
    //   48: getfield 112	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   51: iconst_0
    //   52: invokevirtual 116	android/content/Context:createPackageContext	(Ljava/lang/String;I)Landroid/content/Context;
    //   55: new 27	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 28	java/lang/StringBuilder:<init>	()V
    //   62: aload_3
    //   63: getfield 112	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   66: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: ldc 34
    //   71: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   77: iconst_1
    //   78: invokevirtual 41	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   81: ldc 118
    //   83: ldc 120
    //   85: invokeinterface 126 3 0
    //   90: astore_3
    //   91: aload_3
    //   92: astore_2
    //   93: aload_2
    //   94: ldc 120
    //   96: invokevirtual 132	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   99: istore_1
    //   100: iload_1
    //   101: ifne +25 -> 126
    //   104: aload_2
    //   105: areturn
    //   106: astore_2
    //   107: ldc 2
    //   109: invokevirtual 135	java/lang/Class:getName	()Ljava/lang/String;
    //   112: ldc -119
    //   114: aconst_null
    //   115: aload_2
    //   116: aload_0
    //   117: getfield 17	jp/pp/android/obfuscated/v/i:a	Landroid/content/Context;
    //   120: invokestatic 143	jp/pp/android/tccm/logging/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;Landroid/content/Context;)V
    //   123: ldc 120
    //   125: areturn
    //   126: goto -104 -> 22
    //   129: astore_3
    //   130: goto -108 -> 22
    //   133: astore_3
    //   134: goto -4 -> 130
    //   137: aload_2
    //   138: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	i
    //   99	2	1	bool	boolean
    //   11	94	2	localObject1	Object
    //   106	32	2	localException	Exception
    //   42	50	3	localObject2	Object
    //   129	1	3	localNameNotFoundException1	android.content.pm.PackageManager.NameNotFoundException
    //   133	1	3	localNameNotFoundException2	android.content.pm.PackageManager.NameNotFoundException
    //   18	15	4	localIterator	java.util.Iterator
    // Exception table:
    //   from	to	target	type
    //   0	12	106	java/lang/Exception
    //   93	100	129	android/content/pm/PackageManager$NameNotFoundException
    //   43	91	133	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public final String a()
  {
    Object localObject1 = this.b.getString("recruit_share_id", "");
    if (!((String)localObject1).equals("")) {
      return localObject1;
    }
    Object localObject2 = b();
    localObject1 = localObject2;
    if (((String)localObject2).equals("")) {}
    try
    {
      a(Class.forName("r2android.core.security.PRNGFixes"), null, "apply", new Object[0]);
      localObject1 = UUID.randomUUID().toString();
      localObject2 = this.b.edit();
      ((SharedPreferences.Editor)localObject2).putString("recruit_share_id", (String)localObject1);
      ((SharedPreferences.Editor)localObject2).commit();
      return localObject1;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
  }
}
