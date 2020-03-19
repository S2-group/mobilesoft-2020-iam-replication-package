package com.xvideostudio.VsCommunity.Api;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore.Video.Media;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import b.a.a.a.c;
import com.umeng.analytics.MobclickAgent;
import com.xvideostudio.videoeditor.tool.j;
import com.xvideostudio.videoeditor.tool.k;
import com.xvideostudio.videoeditor.util.f;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class VSCommunityUtils
{
  public static final int CONTEST_VIDEO_FROM_SHARE = 0;
  public static final int CONTEST_VIDEO_FROM_VIDEO_SELECT = 1;
  public static final int CONTEST_VIDEO_UPLOADING = 0;
  public static final int CONTEST_VIDEO_UPLOAD_FAIL = 2;
  public static final int CONTEST_VIDEO_UPLOAD_ORIGIN = -1;
  public static final int CONTEST_VIDEO_UPLOAD_SUCESS = 1;
  private static String androidId;
  private static String imsi;
  
  public VSCommunityUtils() {}
  
  private static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if (paramDrawable.getOpacity() != -1) {
      localObject = Bitmap.Config.ARGB_8888;
    } else {
      localObject = Bitmap.Config.RGB_565;
    }
    Object localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
    Canvas localCanvas = new Canvas((Bitmap)localObject);
    paramDrawable.setBounds(0, 0, i, j);
    paramDrawable.draw(localCanvas);
    return localObject;
  }
  
  private static List<PackageInfo> getAllApps(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    while (i < paramContext.size())
    {
      localArrayList.add((PackageInfo)paramContext.get(i));
      i += 1;
    }
    return localArrayList;
  }
  
  private static String getAndroidId(Context paramContext)
  {
    if (androidId == null) {
      androidId = Settings.System.getString(paramContext.getContentResolver(), "android_id");
    }
    return androidId;
  }
  
  public static ResolveInfo getApp(Context paramContext, String paramString)
  {
    Object localObject = new Intent("android.intent.action.SEND");
    ((Intent)localObject).setType("video/*");
    localObject = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0);
    paramContext.getPackageManager();
    getAllApps(paramContext);
    paramContext = ((List)localObject).iterator();
    while (paramContext.hasNext())
    {
      localObject = (ResolveInfo)paramContext.next();
      ActivityInfo localActivityInfo = ((ResolveInfo)localObject).activityInfo;
      if ((localActivityInfo.packageName.contains(paramString)) || (localActivityInfo.name.contains(paramString))) {
        return localObject;
      }
    }
    return null;
  }
  
  private static String getIMSI(Context paramContext)
  {
    if (imsi == null) {
      imsi = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
    }
    return imsi;
  }
  
  private static String getRandomString(int paramInt)
  {
    Random localRandom = new Random();
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramInt)
    {
      localStringBuffer.append(String.valueOf(localRandom.nextInt(100000)));
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static String getRequestID()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getsysNoMiao());
    localStringBuilder.append(String.valueOf(new Random().nextInt(100000)));
    localStringBuilder.append(f.w());
    return c.a(localStringBuilder.toString(), "UTF-8");
  }
  
  public static String getRequestID(Context paramContext)
  {
    paramContext = new StringBuilder();
    paramContext.append(getsysNoMiao());
    paramContext.append(String.valueOf(new Random().nextInt(100000)));
    paramContext.append(f.w());
    return c.a(paramContext.toString(), "UTF-8");
  }
  
  public static String getResponse(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    return new String(localByteArrayOutputStream.toByteArray());
  }
  
  public static String getVideoContentUriFromFilePath(Context paramContext, String paramString)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    StringBuilder localStringBuilder = null;
    Object localObject2 = null;
    Object localObject1 = localStringBuilder;
    try
    {
      Uri localUri = MediaStore.Video.Media.getContentUri("external");
      localObject1 = localStringBuilder;
      Object localObject3 = new String[1];
      localObject3[0] = "_id";
      localObject1 = localStringBuilder;
      paramString = localContentResolver.query(localUri, (String[])localObject3, "_data LIKE ?", new String[] { paramString }, null);
      localObject1 = localStringBuilder;
      paramString.moveToFirst();
      localObject1 = localStringBuilder;
      int i = paramString.getColumnIndex(localObject3[0]);
      localObject1 = localStringBuilder;
      localObject3 = new StringBuilder();
      localObject1 = localStringBuilder;
      ((StringBuilder)localObject3).append("columnIndex=");
      localObject1 = localStringBuilder;
      ((StringBuilder)localObject3).append(i);
      localObject1 = localStringBuilder;
      j.d("cxs", ((StringBuilder)localObject3).toString());
      localObject1 = localStringBuilder;
      if (paramString.getCount() == 0) {
        return null;
      }
      localObject1 = localStringBuilder;
      long l = paramString.getLong(i);
      localObject1 = localStringBuilder;
      paramString.close();
      paramString = localObject2;
      if (l != -1L)
      {
        localObject1 = localStringBuilder;
        paramString = new StringBuilder();
        localObject1 = localStringBuilder;
        paramString.append(localUri.toString());
        localObject1 = localStringBuilder;
        paramString.append("/");
        localObject1 = localStringBuilder;
        paramString.append(l);
        localObject1 = localStringBuilder;
        paramString = paramString.toString();
      }
      localObject1 = paramString;
      localStringBuilder = new StringBuilder();
      localObject1 = paramString;
      localStringBuilder.append("videoUriStr=");
      localObject1 = paramString;
      localStringBuilder.append(paramString);
      localObject1 = paramString;
      j.b("cxs", localStringBuilder.toString());
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    k.a(paramContext.getResources().getString(2131690417), -1, 1);
    MobclickAgent.onEvent(paramContext, "SHARE_VIA_YOUTUBE_FAIL");
    return localObject1;
  }
  
  private static String getsysNoMiao()
  {
    return String.valueOf(System.nanoTime());
  }
  
  public static boolean isConnectNetWork(Context paramContext, boolean paramBoolean)
  {
    boolean bool = false;
    if (paramContext == null) {
      return false;
    }
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo != null) {
      bool = localNetworkInfo.isAvailable();
    }
    if ((!bool) && (paramBoolean)) {
      k.a(paramContext.getResources().getString(2131690196));
    }
    return bool;
  }
  
  public static Bitmap zoomDrawable(Drawable paramDrawable, int paramInt1, int paramInt2)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    paramDrawable = drawableToBitmap(paramDrawable);
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(paramInt1 / i, paramInt2 / j);
    return Bitmap.createBitmap(paramDrawable, 0, 0, i, j, localMatrix, true);
  }
}
