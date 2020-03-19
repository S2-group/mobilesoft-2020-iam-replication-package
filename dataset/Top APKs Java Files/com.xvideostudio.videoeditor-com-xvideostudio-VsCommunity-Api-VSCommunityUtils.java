package com.xvideostudio.VsCommunity.Api;

import android.content.ContentResolver;
import android.content.ContentValues;
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
import com.google.android.youtube.player.YouTubeIntents;
import com.umeng.a.b;
import com.xvideostudio.videoeditor.VideoEditorApplication;
import com.xvideostudio.videoeditor.i.a;
import com.xvideostudio.videoeditor.service.YoutubeVideoQueryService;
import com.xvideostudio.videoeditor.tool.l;
import com.xvideostudio.videoeditor.util.e;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
  private static String androidId = null;
  private static String imsi = null;
  
  public VSCommunityUtils() {}
  
  private static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if (paramDrawable.getOpacity() != -1) {}
    for (Object localObject = Bitmap.Config.ARGB_8888;; localObject = Bitmap.Config.RGB_565)
    {
      localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
      Canvas localCanvas = new Canvas((Bitmap)localObject);
      paramDrawable.setBounds(0, 0, i, j);
      paramDrawable.draw(localCanvas);
      return localObject;
    }
  }
  
  private static List<PackageInfo> getAllApps(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
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
    return c.a(getsysNoMiao() + String.valueOf(new Random().nextInt(100000)) + e.z(), "UTF-8");
  }
  
  public static String getRequestID(Context paramContext)
  {
    return c.a(getsysNoMiao() + String.valueOf(new Random().nextInt(100000)) + e.z(), "UTF-8");
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
    for (;;)
    {
      try
      {
        Uri localUri = MediaStore.Video.Media.getContentUri("external");
        String[] arrayOfString = new String[1];
        arrayOfString[0] = "_id";
        paramString = localContentResolver.query(localUri, arrayOfString, "_data LIKE ?", new String[] { paramString }, null);
        paramString.moveToFirst();
        int i = paramString.getColumnIndex(arrayOfString[0]);
        com.xvideostudio.videoeditor.tool.k.d("cxs", "columnIndex=" + i);
        if (paramString.getCount() == 0) {
          return null;
        }
        long l = paramString.getLong(i);
        paramString.close();
        if (l == -1L) {
          continue;
        }
        paramString = localUri.toString() + "/" + l;
      }
      catch (Exception paramString)
      {
        paramString = null;
        continue;
        paramString = null;
        continue;
      }
      try
      {
        com.xvideostudio.videoeditor.tool.k.b("cxs", "videoUriStr=" + paramString);
        return paramString;
      }
      catch (Exception localException) {}
    }
    l.a(paramContext.getResources().getString(2131297045), -1, 1);
    b.a(paramContext, "SHARE_VIA_YOUTUBE_FAIL");
    return paramString;
  }
  
  private static String getsysNoMiao()
  {
    return String.valueOf(System.nanoTime());
  }
  
  public static boolean isConnectNetWork(Context paramContext, boolean paramBoolean)
  {
    boolean bool2;
    if (paramContext == null)
    {
      bool2 = false;
      return bool2;
    }
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo != null) {}
    for (boolean bool1 = localNetworkInfo.isAvailable();; bool1 = false)
    {
      bool2 = bool1;
      if (bool1) {
        break;
      }
      bool2 = bool1;
      if (!paramBoolean) {
        break;
      }
      l.a(paramContext.getResources().getString(2131296860));
      return bool1;
    }
  }
  
  public static void upLoadVideoAndStartYoutubeService(Context paramContext, String paramString, File paramFile, int paramInt)
  {
    Object localObject1 = new ContentValues(4);
    ((ContentValues)localObject1).put("date_added", Long.valueOf(System.currentTimeMillis() / 1000L));
    ((ContentValues)localObject1).put("mime_type", "video/mp4");
    com.xvideostudio.videoeditor.tool.k.b("cxs", "share path = " + paramString);
    ((ContentValues)localObject1).put("_data", paramString);
    Object localObject2 = paramContext.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, (ContentValues)localObject1);
    localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = getVideoContentUriFromFilePath(paramContext, paramString);
      if (localObject1 == null)
      {
        l.a(paramContext.getResources().getString(2131297045), -1, 1);
        return;
      }
      localObject1 = Uri.parse((String)localObject1);
    }
    localObject2 = "#videoshowapp " + VideoEditorApplication.b;
    paramContext.grantUriPermission("com.google.android.youtube", (Uri)localObject1, 3);
    localObject1 = YouTubeIntents.createUploadIntent(paramContext, (Uri)localObject1);
    ((Intent)localObject1).putExtra("android.intent.extra.TEXT", (String)localObject2);
    paramContext.startActivity((Intent)localObject1);
    VideoEditorApplication.d = true;
    localObject1 = new Intent();
    ((Intent)localObject1).setClass(paramContext, YoutubeVideoQueryService.class);
    ((Intent)localObject1).putExtra("contestId", VideoEditorApplication.a);
    ((Intent)localObject1).putExtra("contestName", VideoEditorApplication.b);
    ((Intent)localObject1).putExtra("videoSize", com.xvideostudio.videoeditor.util.k.d(paramString));
    ((Intent)localObject1).putExtra("videoLength", a.d(paramFile.getAbsolutePath()));
    ((Intent)localObject1).putExtra("videoFrom", paramInt);
    ((Intent)localObject1).putExtra("videoPath", paramString);
    paramContext.startService((Intent)localObject1);
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
