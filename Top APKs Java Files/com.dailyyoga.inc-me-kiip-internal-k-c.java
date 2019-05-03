package me.kiip.internal.k;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings.Secure;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;

public class c
{
  private static final String[] a = { "9774d56d682e549c", "dead00beef" };
  
  private static String a()
  {
    try
    {
      Object localObject = SecureRandom.getInstance("SHA1PRNG");
      String str = b("");
      ((SecureRandom)localObject).setSeed((str + System.nanoTime() + new SecureRandom().nextLong()).getBytes());
      localObject = Long.toHexString(((SecureRandom)localObject).nextLong());
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {}
    return null;
  }
  
  @SuppressLint({"WorldReadableFiles"})
  public static String a(Context paramContext)
  {
    Object localObject1 = b(paramContext, null);
    Object localObject2 = localObject1;
    SharedPreferences localSharedPreferences;
    if (localObject1 == null)
    {
      localSharedPreferences = paramContext.getSharedPreferences("me.kiip.sdk", 1);
      if (!localSharedPreferences.contains("android_id"))
      {
        Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(128).iterator();
        if (!localIterator.hasNext()) {
          break label156;
        }
        localObject2 = (ApplicationInfo)localIterator.next();
      }
    }
    label156:
    for (;;)
    {
      try
      {
        localObject2 = paramContext.createPackageContext(((ApplicationInfo)localObject2).packageName, 0).getSharedPreferences("me.kiip.sdk", 1).getString("android_id", null);
        localObject1 = localObject2;
        localObject2 = localObject1;
        if (localObject1 != null)
        {
          paramContext = (Context)localObject1;
          if (localObject1 == null) {
            paramContext = a();
          }
          localObject1 = localSharedPreferences.edit();
          ((SharedPreferences.Editor)localObject1).putString("android_id", paramContext);
          ((SharedPreferences.Editor)localObject1).commit();
          localObject2 = localSharedPreferences.getString("android_id", "unknown");
          return localObject2;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Object localObject3 = localObject1;
        localObject1 = localObject3;
      }
      break;
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  static boolean a(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return false;
    }
    String[] arrayOfString = a;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label44;
      }
      if (arrayOfString[i].equals(paramString)) {
        break;
      }
      i += 1;
    }
    label44:
    return true;
  }
  
  private static String b(Context paramContext, String paramString)
  {
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if (!a(paramContext)) {
      return paramString;
    }
    return paramContext;
  }
  
  private static String b(String paramString)
  {
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties");
      localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "ro.serialno", paramString });
      return localObject;
    }
    catch (Exception localException) {}
    return paramString;
  }
}
