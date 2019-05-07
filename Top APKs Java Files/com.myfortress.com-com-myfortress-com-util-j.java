package com.myfortress.com.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@SuppressLint({"SimpleDateFormat"})
public class j
{
  public static float a(Context paramContext, float paramFloat)
  {
    try
    {
      float f = paramContext.getResources().getDisplayMetrics().density;
      return f * paramFloat + 0.5F;
    }
    catch (Exception paramContext) {}
    return 0.0F;
  }
  
  public static Bitmap a(String paramString, int paramInt1, int paramInt2)
  {
    if (paramString != null) {}
    for (;;)
    {
      Object localObject;
      int i;
      int j;
      try
      {
        if (("".equals(paramString)) || (paramString.length() < 1)) {
          break label156;
        }
        localObject = new Hashtable();
        ((Hashtable)localObject).put(EncodeHintType.CHARACTER_SET, "utf-8");
        localObject = new QRCodeWriter().encode(paramString, BarcodeFormat.QR_CODE, paramInt1, paramInt2, (Map)localObject);
        paramString = new int[paramInt1 * paramInt2];
        i = 0;
      }
      catch (WriterException paramString)
      {
        paramString.printStackTrace();
        return null;
      }
      if (j < paramInt1)
      {
        if (((BitMatrix)localObject).get(j, i)) {
          paramString[(i * paramInt1 + j)] = -16777216;
        } else {
          paramString[(i * paramInt1 + j)] = -1;
        }
      }
      else
      {
        i += 1;
        label156:
        while (i >= paramInt2)
        {
          localObject = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.RGB_565);
          ((Bitmap)localObject).setPixels(paramString, 0, paramInt1, 0, 0, paramInt1, paramInt2);
          return localObject;
          return null;
        }
        j = 0;
        continue;
      }
      j += 1;
    }
  }
  
  public static String a()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {
      return Environment.getExternalStorageDirectory().toString();
    }
    return null;
  }
  
  public static void a(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 0).show();
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnected();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static String[] a(String paramString1, String paramString2)
  {
    int j = 0;
    int i = 0;
    Calendar localCalendar = Calendar.getInstance();
    if ((paramString1 != null) && (!paramString1.equals("")))
    {
      localCalendar.set(1, Integer.parseInt(paramString1));
      localCalendar.set(2, Integer.parseInt(paramString2) - 1);
      paramString1 = new String[localCalendar.getActualMaximum(5)];
      if (i < paramString1.length)
      {
        if (i + 1 < 10) {
          paramString1[i] = ("0" + (i + 1));
        }
        for (;;)
        {
          i += 1;
          break;
          paramString1[i] = ("" + (i + 1));
        }
      }
      return paramString1;
    }
    paramString1 = new String[localCalendar.getActualMaximum(5)];
    i = j;
    if (i < paramString1.length)
    {
      if (i + 1 < 10) {
        paramString1[i] = ("0" + (i + 1));
      }
      for (;;)
      {
        i += 1;
        break;
        paramString1[i] = ("" + (i + 1));
      }
    }
    return paramString1;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static String[] b()
  {
    String[] arrayOfString = new String[10];
    Time localTime = new Time();
    localTime.setToNow();
    int j = localTime.year;
    int i = 0;
    while (i < arrayOfString.length)
    {
      arrayOfString[i] = (j - i + "");
      i += 1;
    }
    return arrayOfString;
  }
  
  public static String[] c()
  {
    String[] arrayOfString = new String[12];
    int i = 0;
    if (i < arrayOfString.length)
    {
      if (i + 1 < 10) {
        arrayOfString[i] = ("0" + (i + 1));
      }
      for (;;)
      {
        i += 1;
        break;
        arrayOfString[i] = ("" + (i + 1));
      }
    }
    return arrayOfString;
  }
}
