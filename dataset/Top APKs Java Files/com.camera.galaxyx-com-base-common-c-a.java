package com.base.common.c;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.base.common.c.d;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
{
  public static Application a;
  public static boolean b = false;
  public static boolean c = false;
  public static boolean d = false;
  public static int e = 0;
  public static int f = 0;
  public static int g = 0;
  public static String h = "";
  public static String[] i = { "com.brmobile.kirakira", "com.camera.galaxyx" };
  public static String[] j = { "KiraKira", "S9 Camera" };
  public static int[] k = { c.d.product_icon_camera_x, c.d.product_icon_s9_camera };
  public static int l = 0;
  public static ArrayList<com.base.common.a> m = new ArrayList();
  public static boolean n = false;
  public static boolean o = false;
  public static boolean p = false;
  public static boolean q = false;
  public static boolean r = false;
  public static boolean s = false;
  public static boolean t = false;
  public static boolean u = false;
  public static boolean v = false;
  public static boolean w = false;
  
  public static int a(String paramString)
  {
    try
    {
      int i1 = new ExifInterface(paramString).getAttributeInt("Orientation", 1);
      switch (i1)
      {
      case 4: 
      case 5: 
      case 7: 
      default: 
        return 0;
      case 6: 
        return 90;
      case 3: 
        return 180;
      }
      return 270;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }
  
  public static Bitmap a(int paramInt, Bitmap paramBitmap)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(paramInt);
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
  }
  
  public static ArrayList<com.base.common.a> a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    paramContext.getInstalledPackages(8192);
    Iterator localIterator = paramContext.getInstalledApplications(1024).iterator();
    label146:
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      int i1;
      if ((localApplicationInfo.flags & 0x80) != 0) {
        i1 = 1;
      }
      for (;;)
      {
        if (i1 == 0) {
          break label146;
        }
        com.base.common.a localA = new com.base.common.a();
        localA.a = localApplicationInfo.loadIcon(paramContext);
        localA.b = localApplicationInfo.loadLabel(paramContext).toString();
        localA.c = localApplicationInfo.packageName;
        localArrayList.add(localA);
        break;
        if ((localApplicationInfo.flags & 0x1) == 0) {
          i1 = 1;
        } else {
          i1 = 0;
        }
      }
    }
    return localArrayList;
  }
  
  public static boolean b(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null) {
        return paramContext.isAvailable();
      }
      return false;
    }
    return false;
  }
}
