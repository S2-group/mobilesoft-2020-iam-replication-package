package jp.pplogger.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class c
{
  private Context a;
  private SharedPreferences b;
  private String c;
  
  public c(Context paramContext)
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
  
  private final String b()
  {
    Iterator localIterator = this.a.getPackageManager().getInstalledApplications(0).iterator();
    Object localObject1 = null;
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject2 = (ApplicationInfo)localIterator.next();
        try
        {
          localObject2 = this.a.createPackageContext(((ApplicationInfo)localObject2).packageName, 0).getSharedPreferences(((ApplicationInfo)localObject2).packageName + ".rsid", 1).getString("recruit_share_id", "");
          localObject1 = localObject2;
          try
          {
            boolean bool = localObject1.equals("");
            if (!bool) {
              return localObject1;
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException1) {}
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException2)
        {
          for (;;) {}
        }
      }
    }
    return localObject1;
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
