package com.company.pg600.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"SimpleDateFormat"})
public class AndroidUtils
{
  public AndroidUtils() {}
  
  public static int dip2px(Context paramContext, float paramFloat)
  {
    new DisplayMetrics();
    return (int)(paramContext.getApplicationContext().getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if (paramDrawable.getOpacity() != -1) {}
    for (Object localObject = Bitmap.Config.ARGB_8888;; localObject = Bitmap.Config.RGB_565)
    {
      localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
      Canvas localCanvas = new Canvas((Bitmap)localObject);
      paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
      paramDrawable.draw(localCanvas);
      return localObject;
    }
  }
  
  public static String getDateStr(String paramString)
  {
    return new SimpleDateFormat("HH时mm分").format(new Date(Long.valueOf(paramString).longValue()));
  }
  
  public static String getDateToString(String paramString1, String paramString2)
  {
    paramString2 = new SimpleDateFormat(paramString2);
    if (paramString1 == null) {
      return null;
    }
    return paramString2.format(new Date(Long.valueOf(paramString1).longValue()));
  }
  
  public static String getDencityType(Context paramContext)
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "M";
    arrayOfString[1] = "H";
    arrayOfString[2] = "LH";
    arrayOfString[3] = "XLH";
    new DisplayMetrics();
    paramContext = paramContext.getApplicationContext().getResources().getDisplayMetrics();
    if ((paramContext.heightPixels == 1920) && (paramContext.widthPixels == 1080)) {
      return arrayOfString[3];
    }
    switch (paramContext.densityDpi)
    {
    default: 
      return arrayOfString[1];
    case 120: 
    case 160: 
      return arrayOfString[0];
    case 240: 
      return arrayOfString[1];
    }
    return arrayOfString[2];
  }
  
  public static float getDeviceDisplayDensity(Context paramContext)
  {
    new DisplayMetrics();
    return paramContext.getApplicationContext().getResources().getDisplayMetrics().density;
  }
  
  public static float getDeviceHight(Context paramContext)
  {
    new DisplayMetrics();
    return paramContext.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
  }
  
  public static float getDeviceScaledDensity(Context paramContext)
  {
    new DisplayMetrics();
    return paramContext.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
  }
  
  public static float getDeviceWidth(Context paramContext)
  {
    new DisplayMetrics();
    return paramContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
  }
  
  public static PackageInfo getPackageInfo(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo != null) && (localPackageInfo.packageName != null) && (localPackageInfo.packageName.equals(paramString))) {
        return localPackageInfo;
      }
    }
    return null;
  }
  
  public static long getStringToDate(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
    paramString2 = new Date();
    try
    {
      paramString1 = localSimpleDateFormat.parse(paramString1);
      return paramString1.getTime();
    }
    catch (ParseException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
        paramString1 = paramString2;
      }
    }
  }
  
  public static int getVersionCode(Context paramContext)
  {
    return getPackageInfo(paramContext, paramContext.getPackageName()).versionCode;
  }
  
  public static String getVersionName(Context paramContext)
  {
    return getPackageInfo(paramContext, paramContext.getPackageName()).versionName;
  }
  
  public static boolean isEmail(String paramString)
  {
    boolean bool = false;
    if (Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}").matcher(paramString).matches()) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isMobileNO(String paramString)
  {
    return Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[^4,\\D]))\\d{8}$").matcher(paramString).matches();
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    boolean bool3 = false;
    boolean bool1 = false;
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    boolean bool2 = bool3;
    if (paramContext != null)
    {
      paramContext = paramContext.getAllNetworkInfo();
      bool2 = bool3;
      if (paramContext != null)
      {
        int i = 0;
        for (;;)
        {
          bool2 = bool1;
          if (i >= paramContext.length) {
            break;
          }
          if (paramContext[i].isConnected()) {
            bool1 = true;
          }
          i += 1;
        }
      }
    }
    return bool2;
  }
  
  public static void setGridViewHeightBasedOnChildren(GridView paramGridView, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject = paramGridView.getAdapter();
    if (localObject == null) {
      return;
    }
    if (paramGridView.getCount() % paramInt1 == 0) {}
    for (int i = paramGridView.getCount() / paramInt1;; i = paramGridView.getCount() / paramInt1 + 1)
    {
      int j = 0;
      int k;
      for (paramInt1 = 0; j < i; paramInt1 = k + paramInt1)
      {
        View localView = ((ListAdapter)localObject).getView(j, null, paramGridView);
        localView.measure(0, 0);
        k = localView.getMeasuredHeight();
        j += 1;
      }
    }
    if (i > 1) {
      paramInt1 = (i - 1) * paramInt2 + paramInt1;
    }
    for (;;)
    {
      localObject = paramGridView.getLayoutParams();
      ((ViewGroup.LayoutParams)localObject).height = (paramInt1 + paramInt3);
      paramGridView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      return;
    }
  }
  
  public static void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int k = localListAdapter.getCount();
    int i = 0;
    int j = 0;
    while (i < k)
    {
      localObject = localListAdapter.getView(i, null, paramListView);
      ((View)localObject).measure(0, 0);
      j += ((View)localObject).getMeasuredHeight();
      i += 1;
    }
    Object localObject = paramListView.getLayoutParams();
    ((ViewGroup.LayoutParams)localObject).height = (paramListView.getDividerHeight() * (localListAdapter.getCount() - 1) + j);
    paramListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
  }
  
  public static int sp2px(Context paramContext, float paramFloat)
  {
    new DisplayMetrics();
    return (int)(paramContext.getApplicationContext().getResources().getDisplayMetrics().scaledDensity * paramFloat + 0.5F);
  }
  
  public static byte[] toByteArray(Object paramObject)
  {
    Object localObject2 = null;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    Object localObject1 = localObject2;
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
      localObject1 = localObject2;
      localObjectOutputStream.writeObject(paramObject);
      localObject1 = localObject2;
      localObjectOutputStream.flush();
      localObject1 = localObject2;
      paramObject = localByteArrayOutputStream.toByteArray();
      localObject1 = paramObject;
      localObjectOutputStream.close();
      localObject1 = paramObject;
      localByteArrayOutputStream.close();
      return paramObject;
    }
    catch (IOException paramObject)
    {
      paramObject.printStackTrace();
    }
    return localObject1;
  }
  
  public static String toStringArray(Object paramObject)
  {
    Object localObject2 = null;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    Object localObject1 = localObject2;
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
      localObject1 = localObject2;
      localObjectOutputStream.writeObject(paramObject);
      localObject1 = localObject2;
      localObjectOutputStream.flush();
      localObject1 = localObject2;
      paramObject = localByteArrayOutputStream.toString();
      localObject1 = paramObject;
      localObjectOutputStream.close();
      localObject1 = paramObject;
      localByteArrayOutputStream.close();
      return paramObject;
    }
    catch (IOException paramObject)
    {
      paramObject.printStackTrace();
    }
    return localObject1;
  }
  
  public Object toObject(byte[] paramArrayOfByte)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject1 = localObject3;
    Object localObject2 = localObject4;
    try
    {
      ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
      localObject1 = localObject3;
      localObject2 = localObject4;
      ObjectInputStream localObjectInputStream = new ObjectInputStream(localByteArrayInputStream);
      localObject1 = localObject3;
      localObject2 = localObject4;
      paramArrayOfByte = localObjectInputStream.readObject();
      localObject1 = paramArrayOfByte;
      localObject2 = paramArrayOfByte;
      localObjectInputStream.close();
      localObject1 = paramArrayOfByte;
      localObject2 = paramArrayOfByte;
      localByteArrayInputStream.close();
      return paramArrayOfByte;
    }
    catch (ClassNotFoundException paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
      return localObject1;
    }
    catch (IOException paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return localObject2;
  }
}
