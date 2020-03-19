package com.mobvista.msdk.base.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import com.mobvista.msdk.b.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;

public final class h
{
  static List<String> a;
  private static String b = "[一-龥]";
  private static Pattern c = Pattern.compile("[一-龥]");
  private static int d = 1;
  private static boolean e = true;
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static Drawable a(Bitmap paramBitmap)
  {
    if (paramBitmap != null) {
      return new BitmapDrawable(paramBitmap);
    }
    return null;
  }
  
  public static String a(JSONArray paramJSONArray)
  {
    if (paramJSONArray == null) {
      return "";
    }
    Object localObject = com.mobvista.msdk.base.c.a.c().k();
    b.a();
    com.mobvista.msdk.b.a localA = b.b((String)localObject);
    localObject = localA;
    if (localA == null)
    {
      b.a();
      localObject = b.b();
    }
    int j = ((com.mobvista.msdk.b.a)localObject).f();
    if (paramJSONArray.length() > j)
    {
      localObject = new JSONArray();
      int i = 0;
      for (;;)
      {
        if (i < j) {
          try
          {
            ((JSONArray)localObject).put(paramJSONArray.get(i));
            i += 1;
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              localJSONException.printStackTrace();
            }
          }
        }
      }
      return ((JSONArray)localObject).toString();
    }
    return paramJSONArray.toString();
  }
  
  public static boolean a()
  {
    return e;
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = "wifi".equals(paramContext.getTypeName().toLowerCase(Locale.US));
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (a == null) {
      a = new ArrayList();
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager();
        if ((com.mobvista.msdk.base.c.a.b != null) && (com.mobvista.msdk.base.c.a.b.size() != 0)) {
          break label166;
        }
        com.mobvista.msdk.base.c.a.b = new ArrayList();
        paramContext = paramContext.getInstalledPackages(0);
        i = 0;
        if (i >= paramContext.size()) {
          break label166;
        }
        com.mobvista.msdk.base.c.a.b.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
        continue;
        if (i < com.mobvista.msdk.base.c.a.b.size())
        {
          paramContext = (String)com.mobvista.msdk.base.c.a.b.get(i);
          a.add(paramContext);
          i += 1;
          continue;
        }
        boolean bool = a.contains(paramString);
        return bool;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
      return a.contains(paramString);
      label166:
      int i = 0;
    }
  }
  
  public static <T extends String> boolean a(T paramT)
  {
    return (paramT == null) || (paramT.length() == 0);
  }
  
  public static boolean a(String paramString, Context paramContext)
  {
    try
    {
      if (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0)
      {
        e.a("CommonUtils", "Permission " + paramString + " is granted");
        return true;
      }
      e.a("CommonUtils", "Permission " + paramString + " is NOT granted");
      return false;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static int b()
  {
    int i = d;
    d = i + 1;
    return i;
  }
  
  public static int b(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().scaledDensity * paramFloat + 0.5F);
  }
  
  public static boolean b(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      return paramContext != null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static <T extends String> boolean b(T paramT)
  {
    return (paramT != null) && (paramT.length() > 0);
  }
  
  public static void c(Context paramContext)
  {
    try
    {
      if ((a(paramContext, "com.instagram.android")) || (a(paramContext, "com.facebook.katana")))
      {
        e = true;
        return;
      }
      e = false;
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static float d(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }
}
