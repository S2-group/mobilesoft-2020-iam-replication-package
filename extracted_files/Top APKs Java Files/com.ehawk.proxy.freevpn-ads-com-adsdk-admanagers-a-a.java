package ads.com.adsdk.admanagers.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class a
{
  public static int a(Context paramContext, String paramString1, String paramString2)
  {
    int j = 0;
    paramContext = paramContext.getPackageName();
    try
    {
      Class[] arrayOfClass = Class.forName(paramContext + ".R").getClasses();
      Object localObject = null;
      int i = 0;
      for (;;)
      {
        paramContext = localObject;
        if (i < arrayOfClass.length)
        {
          if (arrayOfClass[i].getName().split("\\$")[1].equals(paramString1)) {
            paramContext = arrayOfClass[i];
          }
        }
        else
        {
          i = j;
          if (paramContext != null) {
            i = paramContext.getField(paramString2).getInt(paramContext);
          }
          return i;
        }
        i += 1;
      }
      return 0;
    }
    catch (ClassNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return 0;
    }
    catch (IllegalArgumentException paramContext)
    {
      paramContext.printStackTrace();
      return 0;
    }
    catch (SecurityException paramContext)
    {
      paramContext.printStackTrace();
      return 0;
    }
    catch (IllegalAccessException paramContext)
    {
      paramContext.printStackTrace();
      return 0;
    }
    catch (NoSuchFieldException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static long a(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    long l1 = 0L;
    l2 = l1;
    if (paramInputStream != null)
    {
      l2 = l1;
      if (paramOutputStream != null)
      {
        byte[] arrayOfByte = new byte['Ѐ'];
        try
        {
          for (;;)
          {
            int i = paramInputStream.read(arrayOfByte);
            l2 = l1;
            if (i == -1) {
              break;
            }
            paramOutputStream.write(arrayOfByte, 0, i);
            l1 += i;
          }
          return l2;
        }
        catch (IOException paramInputStream)
        {
          paramInputStream.printStackTrace();
          l2 = l1;
        }
      }
    }
  }
  
  public static List a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    int i = 0;
    while (i < paramContext.size())
    {
      localArrayList.add(((ApplicationInfo)paramContext.get(i)).packageName);
      i += 1;
    }
    return localArrayList;
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext.getSharedPreferences("admin", 0).edit().putBoolean(paramString, true).commit();
  }
  
  public static void a(String paramString, ads.com.adsdk.admanagers.e.b paramB)
  {
    new Thread(new b(paramString, paramB)).start();
  }
  
  public static byte[] a(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    a(paramInputStream, localByteArrayOutputStream);
    paramInputStream = localByteArrayOutputStream.toByteArray();
    try
    {
      localByteArrayOutputStream.close();
      return paramInputStream;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return paramInputStream;
  }
  
  public static int b(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i + 10;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 1;
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.getSharedPreferences("admin", 0).edit().putString(paramString1, paramString2).commit();
  }
}
