package com.intsig.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera.Size;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.intsig.business.folders.OfflineFolder.OperatingDirection;
import com.intsig.camscanner.ScannerApplication;
import com.intsig.camscanner.a.j;
import com.intsig.camscanner.a.y;
import com.intsig.camscanner.dw;
import com.intsig.camscanner.provider.i;
import com.intsig.camscanner.provider.k;
import com.intsig.camscanner.provider.o;
import com.intsig.camscanner.settings.NameResult;
import com.intsig.camscanner.settings.NameResult.NamePart;
import com.intsig.nativelib.PinyinUtil;
import com.intsig.pdfengine.PDF_Util;
import com.intsig.q.e;
import com.intsig.scanner.ScannerEngine;
import com.intsig.tsapp.b;
import com.intsig.tsapp.sync.av;
import com.intsig.utils.NotificationHelper;
import com.intsig.utils.s;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONException;

public class bo
{
  public static int a;
  static SimpleDateFormat b = new SimpleDateFormat("yyMMdd");
  private static String[] c = { "GT-i7500", "GT-i5700", "SHW-M100S", "GT-i9000", "T939", "GT-i5500", "i5801", "i8520", "GT-i8500", "P1000", "EPIC 4G", "i897", "SHW-M13OL", "SHW-M130K" };
  
  public static int a(int paramInt)
  {
    int j = -1;
    int i = paramInt;
    if (paramInt == -1) {
      i = -1;
    }
    i %= 360;
    if ((i >= 25) && (i <= 335))
    {
      if ((i > 65) && (i < 115)) {
        return 90;
      }
      if ((i > 155) && (i < 205)) {
        return 180;
      }
      paramInt = j;
      if (i > 245)
      {
        paramInt = j;
        if (i < 295) {
          return 270;
        }
      }
    }
    else
    {
      paramInt = 0;
    }
    return paramInt;
  }
  
  public static int a(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 > paramInt3) {
      return paramInt3;
    }
    if (paramInt1 < paramInt2) {
      return paramInt2;
    }
    return paramInt1;
  }
  
  public static int a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int i = b(paramOptions, paramInt1, paramInt2);
    if (i <= 8)
    {
      paramInt1 = 1;
      for (;;)
      {
        paramInt2 = paramInt1;
        if (paramInt1 >= i) {
          break;
        }
        paramInt1 <<= 1;
      }
    }
    paramInt2 = 8 * ((i + 7) / 8);
    return paramInt2;
  }
  
  public static long a(long paramLong, Context paramContext)
  {
    return a(paramContext, paramLong, null);
  }
  
  public static long a(long paramLong, int[] paramArrayOfInt, Context paramContext)
  {
    long l2 = 0L;
    long l1 = l2;
    if (paramLong > 0L)
    {
      l1 = l2;
      if (paramContext != null)
      {
        l1 = l2;
        if (paramArrayOfInt != null)
        {
          paramContext = paramContext.getContentResolver().query(o.a(paramLong), new String[] { "_data" }, null, null, "page_num ASC");
          l1 = l2;
          if (paramContext != null)
          {
            l1 = 0L;
            int i = 0;
            while (i < paramArrayOfInt.length)
            {
              l2 = l1;
              if (paramContext.moveToPosition(paramArrayOfInt[i]))
              {
                Object localObject = paramContext.getString(0);
                l2 = l1;
                if (!TextUtils.isEmpty((CharSequence)localObject))
                {
                  localObject = new File((String)localObject);
                  l2 = l1;
                  if (((File)localObject).exists()) {
                    l2 = l1 + ((File)localObject).length();
                  }
                }
              }
              i += 1;
              l1 = l2;
            }
            paramContext.close();
          }
        }
      }
    }
    paramArrayOfInt = new StringBuilder();
    paramArrayOfInt.append("Doc id:");
    paramArrayOfInt.append(paramLong);
    paramArrayOfInt.append(" ,getDocImageSize");
    paramArrayOfInt.append(l1);
    e.b("Util", paramArrayOfInt.toString());
    return l1;
  }
  
  public static long a(Context paramContext, long paramLong, String paramString)
  {
    long l1 = 0L;
    long l2 = l1;
    if (paramLong > 0L)
    {
      l2 = l1;
      if (paramContext != null)
      {
        if (TextUtils.isEmpty(paramString)) {}
        StringBuilder localStringBuilder;
        for (paramString = null;; paramString = localStringBuilder.toString())
        {
          break;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("page_num in ");
          localStringBuilder.append(paramString);
        }
        paramContext = paramContext.getContentResolver().query(o.a(paramLong), new String[] { "_data" }, paramString, null, "page_num ASC");
        l2 = l1;
        if (paramContext != null)
        {
          while (paramContext.moveToNext())
          {
            paramString = paramContext.getString(0);
            if (!TextUtils.isEmpty(paramString))
            {
              paramString = new File(paramString);
              if (paramString.exists()) {
                l1 += paramString.length();
              }
            }
          }
          paramContext.close();
          l2 = l1;
        }
      }
    }
    paramContext = new StringBuilder();
    paramContext.append("Doc id:");
    paramContext.append(paramLong);
    paramContext.append(" ,getOneDocAllImageSize:");
    paramContext.append(l2);
    e.b("Util", paramContext.toString());
    return l2;
  }
  
  public static long a(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getContentResolver().query(o.a, new String[] { "_data" }, null, null, null);
    if (paramContext == null) {
      return -1L;
    }
    int i = paramContext.getCount();
    long l2 = 0L;
    if (i == 0)
    {
      paramContext.close();
      return 0L;
    }
    long l1;
    if (paramBoolean)
    {
      paramContext.moveToFirst();
      do
      {
        Object localObject = paramContext.getString(0);
        if (localObject == null)
        {
          l1 = l2;
        }
        else
        {
          localObject = new File((String)localObject);
          l1 = l2;
          if (((File)localObject).exists()) {
            l1 = l2 + ((File)localObject).length();
          }
        }
        l2 = l1;
      } while (paramContext.moveToNext());
    }
    else
    {
      l1 = 524288L * paramContext.getCount();
    }
    paramContext.close();
    paramContext = new StringBuilder();
    paramContext.append("allImageSize: ");
    paramContext.append(l1);
    e.b("Util", paramContext.toString());
    return l1;
  }
  
  public static long a(File paramFile)
  {
    long l2 = 0L;
    if (paramFile == null) {
      return 0L;
    }
    if (!paramFile.isDirectory()) {
      return 0L;
    }
    paramFile = paramFile.listFiles();
    int j = paramFile.length;
    int i = 0;
    while (i < j)
    {
      File localFile = paramFile[i];
      long l1;
      if (localFile.isFile())
      {
        l1 = l2 + localFile.length();
      }
      else
      {
        l1 = l2;
        if (localFile.isDirectory()) {
          l1 = l2 + localFile.length() + a(localFile);
        }
      }
      i += 1;
      l2 = l1;
    }
    paramFile = new StringBuilder();
    paramFile.append("dirSize: ");
    paramFile.append(l2);
    e.d("Util", paramFile.toString());
    return l2;
  }
  
  public static Bitmap a(String paramString, float paramFloat)
  {
    return a(paramString, ScannerApplication.k, true, paramFloat);
  }
  
  public static Bitmap a(String paramString, int paramInt)
  {
    return s.a(paramString, ScannerApplication.k, false, 1.0F, paramInt);
  }
  
  public static Bitmap a(String paramString, int paramInt1, int paramInt2)
  {
    return a(paramString, paramInt1, paramInt2, ScannerApplication.k);
  }
  
  public static Bitmap a(String paramString, int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    return s.a(paramString, paramInt1, paramInt2, paramConfig, true);
  }
  
  public static Bitmap a(String paramString, int paramInt1, int paramInt2, Bitmap.Config paramConfig, boolean paramBoolean)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      System.gc();
      localOptions.inSampleSize = 1;
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      if ((!localOptions.mCancel) && (localOptions.outWidth != -1))
      {
        if (localOptions.outHeight == -1) {
          return null;
        }
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("loadBitmap(orginal) path:");
        localStringBuilder2.append(paramString);
        localStringBuilder2.append(" ");
        localStringBuilder2.append(localOptions.outWidth);
        localStringBuilder2.append("x");
        localStringBuilder2.append(localOptions.outHeight);
        e.b("Util", localStringBuilder2.toString());
        localOptions.inSampleSize = a(localOptions, paramInt1, paramInt2);
        localOptions.inJustDecodeBounds = false;
        localOptions.inDither = false;
        localOptions.inPreferredConfig = paramConfig;
        paramConfig = BitmapFactory.decodeFile(paramString, localOptions);
        if (paramConfig == null) {
          return null;
        }
        if (paramBoolean) {
          try
          {
            paramInt1 = s.c(paramString);
            if (paramInt1 != 1)
            {
              paramString = new Matrix();
              paramString.postRotate(s.a(paramInt1));
              paramString = Bitmap.createBitmap(paramConfig, 0, 0, paramConfig.getWidth(), paramConfig.getHeight(), paramString, true);
              if ((paramString != null) && (!paramString.equals(paramConfig)))
              {
                paramConfig.recycle();
                paramConfig = paramString;
              }
            }
          }
          catch (Exception localException)
          {
            paramString = paramConfig;
            paramConfig = localException;
            break label279;
          }
          catch (OutOfMemoryError localOutOfMemoryError)
          {
            paramString = paramConfig;
            paramConfig = localOutOfMemoryError;
            break label320;
          }
        }
        return paramConfig;
      }
      return null;
    }
    catch (Exception paramConfig)
    {
      paramString = localOutOfMemoryError;
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("image read error:");
      localStringBuilder1.append(paramConfig);
      e.c("Util", localStringBuilder1.toString());
      return paramString;
    }
    catch (OutOfMemoryError paramConfig)
    {
      label279:
      paramString = localObject2;
      label320:
      e.b("Util", "loadBitmap OOM:", paramConfig);
      System.gc();
    }
    return paramString;
  }
  
  public static Bitmap a(String paramString, Bitmap.Config paramConfig)
  {
    return a(paramString, paramConfig, false, 1.0F);
  }
  
  public static Bitmap a(String paramString, Bitmap.Config paramConfig, boolean paramBoolean, float paramFloat)
  {
    return s.a(paramString, ScannerApplication.k, paramBoolean, paramFloat, 1);
  }
  
  public static Camera.Size a(Activity paramActivity, List<Camera.Size> paramList, double paramDouble, boolean paramBoolean)
  {
    Object localObject1 = null;
    Object localObject4 = null;
    if (paramList != null)
    {
      if (paramList.size() == 0) {
        return null;
      }
      Object localObject3 = new StringBuilder();
      double d3;
      if (paramBoolean)
      {
        localObject2 = (Camera.Size)paramList.get(0);
        d2 = 1048576.0D;
        i = com.intsig.camscanner.b.a.b(paramActivity);
        paramActivity = new StringBuilder();
        paramActivity.append("CAPTURE_SIZE_LIMIT=");
        paramActivity.append(i);
        e.b("Util", paramActivity.toString());
        d1 = 0.01D;
        j = 0;
        d3 = 0.0D;
        localObject1 = localObject3;
        paramActivity = (Activity)localObject4;
        while ((d1 < 0.05D) && (j == 0))
        {
          localObject4 = paramList.iterator();
          while (((Iterator)localObject4).hasNext())
          {
            localObject3 = (Camera.Size)((Iterator)localObject4).next();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(" (");
            localStringBuilder.append(((Camera.Size)localObject3).width);
            localStringBuilder.append(", ");
            localStringBuilder.append(((Camera.Size)localObject3).height);
            localStringBuilder.append(") ");
            ((StringBuilder)localObject1).append(localStringBuilder.toString());
            double d5 = ((Camera.Size)localObject3).width * ((Camera.Size)localObject3).height;
            if (d5 <= i)
            {
              double d4 = d3;
              if (d5 > d3)
              {
                d4 = d5;
                localObject2 = localObject3;
              }
              if ((d5 > d2) && (Math.abs(((Camera.Size)localObject3).width * 1.0D / ((Camera.Size)localObject3).height - paramDouble) < d1))
              {
                paramActivity = (Activity)localObject3;
                d2 = d5;
              }
              d3 = d4;
            }
          }
          if (paramActivity == null) {
            d1 += 0.01D;
          } else {
            j = 1;
          }
        }
        if (j != 0) {
          localObject2 = paramActivity;
        }
        paramActivity = new StringBuilder();
        paramActivity.append("picture size: ");
        paramActivity.append(((StringBuilder)localObject1).toString());
        e.d("Util", paramActivity.toString());
        return localObject2;
      }
      Object localObject2 = paramActivity.getWindowManager().getDefaultDisplay();
      int j = Math.min(((Display)localObject2).getHeight(), ((Display)localObject2).getWidth());
      int i = j;
      if (j <= 0) {
        i = ((WindowManager)paramActivity.getSystemService("window")).getDefaultDisplay().getHeight();
      }
      double d1 = 0.01D;
      double d2 = Double.MAX_VALUE;
      j = 0;
      paramActivity = (Activity)localObject1;
      while ((d1 < 0.04D) && (j == 0))
      {
        localObject2 = paramList.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject1 = (Camera.Size)((Iterator)localObject2).next();
          localObject4 = new StringBuilder();
          ((StringBuilder)localObject4).append(" (");
          ((StringBuilder)localObject4).append(((Camera.Size)localObject1).width);
          ((StringBuilder)localObject4).append(", ");
          ((StringBuilder)localObject4).append(((Camera.Size)localObject1).height);
          ((StringBuilder)localObject4).append(") ");
          ((StringBuilder)localObject3).append(((StringBuilder)localObject4).toString());
          if (Math.abs(((Camera.Size)localObject1).width * 1.0D / ((Camera.Size)localObject1).height - paramDouble) <= d1)
          {
            d3 = Math.abs(((Camera.Size)localObject1).height - i);
            if (d3 < d2)
            {
              d2 = d3;
              paramActivity = (Activity)localObject1;
            }
          }
        }
        if (paramActivity == null) {
          d1 += 0.01D;
        } else {
          j = 1;
        }
      }
      localObject1 = paramActivity;
      if (paramActivity == null)
      {
        localObject2 = paramList.iterator();
        paramDouble = Double.MAX_VALUE;
        for (;;)
        {
          localObject1 = paramActivity;
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          paramList = (Camera.Size)((Iterator)localObject2).next();
          d1 = Math.abs(paramList.height - i);
          if (d1 < paramDouble)
          {
            paramActivity = paramList;
            paramDouble = d1;
          }
        }
      }
      paramActivity = new StringBuilder();
      paramActivity.append("preview size: ");
      paramActivity.append(((StringBuilder)localObject3).toString());
      e.d("Util", paramActivity.toString());
      return localObject1;
    }
    return null;
  }
  
  public static Uri a(Context paramContext, com.intsig.datastruct.a paramA)
  {
    if (paramA == null)
    {
      e.b("Util", "insertEmptyDoc docProperty == null");
      return null;
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("DocProperty docData:");
    ((StringBuilder)localObject1).append(paramA);
    e.b("Util", ((StringBuilder)localObject1).toString());
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("title", paramA.a);
    localContentValues.put("type", Integer.valueOf(paramA.j));
    if (paramA.h) {
      localContentValues.put("folder_type", Integer.valueOf(1));
    } else {
      localContentValues.put("folder_type", Integer.valueOf(0));
    }
    localContentValues.put("title_sort_index", PinyinUtil.getPinyinOf(paramA.a));
    if (!TextUtils.isEmpty(paramA.b))
    {
      localContentValues.put("team_token", paramA.b);
      localContentValues.put("permission", Integer.valueOf(paramA.d));
      localContentValues.put("creator_user_id", paramA.e);
      localContentValues.put("creator_account", av.l(paramContext));
      localContentValues.put("creator_nickname", y.d(paramContext, paramA.e, paramA.b));
      localContentValues.put("upload_time", Long.valueOf(r.q(paramContext, paramA.b) + 1L));
    }
    Object localObject3;
    Object localObject2;
    if (TextUtils.isEmpty(paramA.c))
    {
      localContentValues.putNull("sync_dir_id");
    }
    else
    {
      localContentValues.put("sync_dir_id", paramA.c);
      localObject3 = new ContentValues();
      long l;
      if (TextUtils.isEmpty(paramA.b)) {
        l = com.intsig.tsapp.sync.f.a().g(paramContext);
      } else {
        l = r.q(paramContext, paramA.b);
      }
      ((ContentValues)localObject3).put("upload_time", Long.valueOf(l + 1L));
      if (paramA.i == OfflineFolder.OperatingDirection.IN)
      {
        ((ContentValues)localObject3).put("folder_type", Integer.valueOf(1));
      }
      else if (paramA.i == OfflineFolder.OperatingDirection.OUT)
      {
        ((ContentValues)localObject3).put("folder_type", Integer.valueOf(0));
        ((ContentValues)localObject3).put("sync_state", Integer.valueOf(1));
      }
      else if (paramA.h)
      {
        ((ContentValues)localObject3).put("folder_type", Integer.valueOf(1));
      }
      else
      {
        ((ContentValues)localObject3).put("folder_type", Integer.valueOf(0));
        ((ContentValues)localObject3).put("sync_state", Integer.valueOf(3));
      }
      localObject1 = "sync_dir_id=?";
      localObject2 = new String[1];
      localObject2[0] = paramA.c;
      if (!TextUtils.isEmpty(paramA.b))
      {
        localObject1 = "sync_dir_id=? and team_token=?";
        localObject2 = new String[2];
        localObject2[0] = paramA.c;
        localObject2[1] = paramA.b;
      }
      int i = paramContext.getContentResolver().update(i.a, (ContentValues)localObject3, (String)localObject1, (String[])localObject2);
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("insertEmptyDoc update dir num=");
      ((StringBuilder)localObject1).append(i);
      e.b("Util", ((StringBuilder)localObject1).toString());
    }
    if (!TextUtils.isEmpty(paramA.f))
    {
      localContentValues.put("_data", paramA.f);
      localContentValues.put("pdf_state", Integer.valueOf(1));
      localContentValues.put("sync_state", Integer.valueOf(0));
    }
    if (paramA.g)
    {
      localObject1 = paramContext.getString(2131690688);
      localContentValues.put("title", (String)localObject1);
      localContentValues.put("title_sort_index", PinyinUtil.getPinyinOf((String)localObject1));
      localContentValues.put("sync_doc_id", y.t(paramContext));
    }
    try
    {
      localObject1 = paramContext.getContentResolver().insert(k.a, localContentValues);
      try
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("insertEmptyDoc uri :");
        ((StringBuilder)localObject2).append(localObject1);
        ((StringBuilder)localObject2).append(", title = ");
        ((StringBuilder)localObject2).append(paramA.a);
        ((StringBuilder)localObject2).append(", pdf path = ");
        ((StringBuilder)localObject2).append(paramA.f);
        e.b("Util", ((StringBuilder)localObject2).toString());
      }
      catch (Exception localException1) {}
      localObject3 = new StringBuilder();
    }
    catch (Exception localException2)
    {
      localObject1 = null;
    }
    ((StringBuilder)localObject3).append("insertEmptyDoc title ");
    ((StringBuilder)localObject3).append(paramA.a);
    e.b("Util", ((StringBuilder)localObject3).toString(), localException2);
    if (localObject1 == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("insertEmptyDoc uri == null context: ");
      localStringBuilder.append(paramContext);
      localStringBuilder.append("\n docData:");
      localStringBuilder.append(paramA);
      e.c("Util", localStringBuilder.toString());
      try
      {
        if ((paramContext instanceof Activity)) {
          ((Activity)paramContext).finish();
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        e.b("Util", "insertEmptyDoc null url finish activity error: ", paramContext);
      }
    }
    localContentValues.clear();
    com.intsig.camscanner.control.s.a = Boolean.valueOf(true);
    return localObject1;
  }
  
  private static Pair<Float, Float> a(float paramFloat1, float paramFloat2)
  {
    return new Pair(Float.valueOf(paramFloat2), Float.valueOf(paramFloat1));
  }
  
  public static String a()
  {
    return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
  }
  
  private static String a(int paramInt1, int paramInt2)
  {
    switch (paramInt2)
    {
    default: 
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(" ");
      localStringBuilder.append(paramInt1);
      return localStringBuilder.toString();
    case 1: 
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("(");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append(")");
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" ");
    localStringBuilder.append(paramInt1);
    return localStringBuilder.toString();
  }
  
  public static String a(long paramLong, String paramString1, String paramString2, Context paramContext)
  {
    if (paramLong > 0L)
    {
      Uri localUri = ContentUris.withAppendedId(k.a, paramLong);
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("title", paramString1);
      localContentValues.put("title_sort_index", PinyinUtil.getPinyinOf(paramString1));
      if (!TextUtils.isEmpty(paramString2))
      {
        paramString1 = PDF_Util.createPdfPath(paramContext, paramLong, paramString1);
        localContentValues.put("_data", paramString1);
        new File(paramString2).renameTo(new File(paramString1));
      }
      else
      {
        paramString1 = null;
      }
      if (paramContext.getContentResolver().update(localUri, localContentValues, null, null) == 0)
      {
        paramString2 = new StringBuilder();
        paramString2.append("updateDocTitle file may be deleted id = ");
        paramString2.append(paramLong);
        e.c("Util", paramString2.toString());
        return paramString1;
      }
      av.b(paramContext, paramLong, 3, true);
      b.a(paramContext, paramLong);
      return paramString1;
    }
    return null;
  }
  
  public static String a(Context paramContext)
  {
    return a(paramContext, null, false, false);
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    return a(paramContext, null, false, paramInt);
  }
  
  public static String a(Context paramContext, String paramString, int paramInt)
  {
    return a(paramContext, paramString, paramInt, null, false);
  }
  
  public static String a(Context paramContext, String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return a(paramContext, paramString2, paramBoolean, false);
    }
    String str;
    if (paramBoolean)
    {
      if (TextUtils.isEmpty(paramString2))
      {
        str = " upper(title)=? and sync_dir_id IS NULL";
        paramString2 = new String[1];
      }
      else
      {
        localObject1 = new String[2];
        localObject1[1] = paramString2;
        paramString2 = paramString1;
        str = " upper(title)=? and sync_dir_id =? ";
        i = 0;
        break label87;
      }
    }
    else
    {
      str = " upper(title)=? and _id >0 ";
      paramString2 = new String[1];
    }
    int i = 0;
    Object localObject2 = paramString1;
    Object localObject1 = paramString2;
    for (paramString2 = (String)localObject2;; paramString2 = paramString2.toString())
    {
      label87:
      localObject1[0] = paramString2.toUpperCase();
      localObject2 = paramContext.getContentResolver().query(k.a, null, str, (String[])localObject1, null);
      if (localObject2 == null) {
        break label186;
      }
      int j = ((Cursor)localObject2).getCount();
      ((Cursor)localObject2).close();
      if (j <= 0) {
        break;
      }
      i += 1;
      paramString2 = new StringBuilder();
      paramString2.append(paramString1);
      paramString2.append(a(i, paramInt));
    }
    if (i > 0) {
      label186:
      if (i > 0) {
        paramString1 = paramString2;
      }
    }
    return paramString1;
  }
  
  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    return a(paramContext, paramString1, paramString2, false);
  }
  
  public static String a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    long l1 = System.currentTimeMillis();
    int[] arrayOfInt = d(paramString1);
    if (paramBoolean) {
      i = com.intsig.camscanner.b.a.a();
    } else {
      i = com.intsig.camscanner.b.a.c(paramContext);
    }
    float f2 = (float)Math.sqrt(i * 1.0F / (arrayOfInt[0] * 1.0F * arrayOfInt[1]));
    int i = s.d(paramString1);
    float f1 = f2;
    if (f2 > 1.0F) {
      f1 = 1.0F;
    }
    paramContext = new StringBuilder();
    paramContext.append("compressImageFile()  old size ");
    paramContext.append(com.intsig.utils.p.b(paramString1) / 1024L);
    paramContext.append("KB");
    e.c("Util", paramContext.toString());
    if (TextUtils.isEmpty(paramString2))
    {
      i = ScannerEngine.scaleImage(paramString1, i, f1, 80, null);
      paramContext = new StringBuilder();
      paramContext.append("compressImageFile()  result:");
      paramContext.append(i);
      paramContext.append(" scale= ");
      paramContext.append(f1);
      e.d("Util", paramContext.toString());
      paramContext = new StringBuilder();
      paramContext.append("compressImageFile()  new size ");
      paramContext.append(com.intsig.utils.p.b(paramString1) / 1024L);
      paramContext.append("KB");
      e.c("Util", paramContext.toString());
      if (i >= 0) {
        break label345;
      }
    }
    else
    {
      i = ScannerEngine.scaleImage(paramString1, i, f1, 80, paramString2);
      paramContext = new StringBuilder();
      paramContext.append("compressImageFile()  result:");
      paramContext.append(i);
      e.d("Util", paramContext.toString());
      paramContext = new StringBuilder();
      paramContext.append("compressImageFile()  new size ");
      paramContext.append(com.intsig.utils.p.b(paramString2) / 1024L);
      paramContext.append("KB");
      e.c("Util", paramContext.toString());
      if (i >= 0)
      {
        paramString1 = paramString2;
        break label345;
      }
    }
    paramString1 = null;
    label345:
    long l2 = System.currentTimeMillis();
    paramContext = new StringBuilder();
    paramContext.append("compressImageFile()  consume ");
    paramContext.append(l2 - l1);
    paramContext.append("ms");
    e.c("Util", paramContext.toString());
    return paramString1;
  }
  
  public static String a(Context paramContext, String paramString, boolean paramBoolean, int paramInt)
  {
    return a(paramContext, paramString, paramBoolean, b(paramContext, paramInt));
  }
  
  public static String a(Context paramContext, String paramString1, boolean paramBoolean, String paramString2)
  {
    Object localObject = "";
    String[] arrayOfString;
    if (paramBoolean)
    {
      if (TextUtils.isEmpty(paramString1))
      {
        paramString1 = "title=? and sync_dir_id IS NULL";
        arrayOfString = new String[1];
      }
      else
      {
        arrayOfString = new String[2];
        arrayOfString[1] = paramString1;
        paramString1 = "title=? and sync_dir_id =? ";
      }
    }
    else
    {
      paramString1 = "title=? and _id >0 ";
      arrayOfString = new String[1];
    }
    int i = 0;
    for (;;)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString2);
      localStringBuilder.append((String)localObject);
      arrayOfString[0] = localStringBuilder.toString();
      localObject = paramContext.getContentResolver().query(k.a, new String[] { "_id" }, paramString1, arrayOfString, null);
      if (localObject == null) {
        return arrayOfString[0];
      }
      if (((Cursor)localObject).getCount() < 1)
      {
        ((Cursor)localObject).close();
        return arrayOfString[0];
      }
      ((Cursor)localObject).close();
      i += 1;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(" (");
      ((StringBuilder)localObject).append(i);
      ((StringBuilder)localObject).append(")");
      localObject = ((StringBuilder)localObject).toString();
    }
  }
  
  public static String a(Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    return a(paramContext, paramString, paramBoolean1, c(paramContext, paramBoolean2));
  }
  
  /* Error */
  public static String a(@android.support.annotation.NonNull java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 727	java/io/BufferedInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 730	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   8: astore_3
    //   9: new 732	java/io/InputStreamReader
    //   12: dup
    //   13: aload_3
    //   14: invokespecial 733	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   17: astore_2
    //   18: new 118	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   25: astore_0
    //   26: sipush 2048
    //   29: newarray char
    //   31: astore 4
    //   33: aload_2
    //   34: aload 4
    //   36: invokevirtual 737	java/io/InputStreamReader:read	([C)I
    //   39: istore_1
    //   40: iload_1
    //   41: iconst_m1
    //   42: if_icmpeq +15 -> 57
    //   45: aload_0
    //   46: aload 4
    //   48: iconst_0
    //   49: iload_1
    //   50: invokevirtual 740	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: goto -21 -> 33
    //   57: aload_0
    //   58: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: astore_0
    //   62: aload_3
    //   63: invokestatic 743	com/intsig/util/bo:a	(Ljava/io/Closeable;)V
    //   66: aload_2
    //   67: invokestatic 743	com/intsig/util/bo:a	(Ljava/io/Closeable;)V
    //   70: aload_0
    //   71: areturn
    //   72: astore_0
    //   73: goto +14 -> 87
    //   76: astore_0
    //   77: aconst_null
    //   78: astore_2
    //   79: goto +8 -> 87
    //   82: astore_0
    //   83: aconst_null
    //   84: astore_3
    //   85: aload_3
    //   86: astore_2
    //   87: aload_3
    //   88: invokestatic 743	com/intsig/util/bo:a	(Ljava/io/Closeable;)V
    //   91: aload_2
    //   92: invokestatic 743	com/intsig/util/bo:a	(Ljava/io/Closeable;)V
    //   95: aload_0
    //   96: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	paramInputStream	java.io.InputStream
    //   39	11	1	i	int
    //   17	75	2	localObject	Object
    //   8	80	3	localBufferedInputStream	java.io.BufferedInputStream
    //   31	16	4	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   18	33	72	finally
    //   33	40	72	finally
    //   45	54	72	finally
    //   57	62	72	finally
    //   9	18	76	finally
    //   0	9	82	finally
  }
  
  public static String a(String paramString)
  {
    if (paramString != null)
    {
      int i = paramString.lastIndexOf("/");
      if ((i > -1) && (i < paramString.length()))
      {
        paramString = paramString.substring(i + 1);
        break label38;
      }
    }
    paramString = null;
    label38:
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("getFileName name=");
    localStringBuilder.append(paramString);
    e.b("Util", localStringBuilder.toString());
    return paramString;
  }
  
  public static ArrayList<String> a(Context paramContext, ArrayList<Long> paramArrayList)
  {
    if ((paramArrayList != null) && (!paramArrayList.isEmpty()))
    {
      ArrayList localArrayList = new ArrayList();
      ContentResolver localContentResolver = paramContext.getContentResolver();
      paramArrayList = paramArrayList.iterator();
      for (;;)
      {
        paramContext = localArrayList;
        if (!paramArrayList.hasNext()) {
          break;
        }
        paramContext = (Long)paramArrayList.next();
        paramContext = localContentResolver.query(ContentUris.withAppendedId(k.a, paramContext.longValue()), new String[] { "sync_doc_id", "sync_state" }, null, null, null);
        if (paramContext != null)
        {
          if (paramContext.moveToFirst())
          {
            int i = paramContext.getInt(1);
            if ((i != 2) && (i != 5)) {
              localArrayList.add(paramContext.getString(0));
            }
          }
          paramContext.close();
        }
      }
    }
    paramContext = null;
    return paramContext;
  }
  
  public static void a(int paramInt1, String paramString, int paramInt2, Context paramContext, Class paramClass)
  {
    paramClass = new Intent(paramContext, paramClass);
    if (paramInt2 == 2131690539) {
      paramInt1 = 1;
    } else if (paramInt2 == 2131689592) {
      paramInt1 = 2;
    } else {
      paramInt1 = 0;
    }
    paramClass.putExtra("task_type", paramInt1);
    paramClass = PendingIntent.getActivity(paramContext, 0, paramClass, 0);
    paramContext = NotificationHelper.getInstance().getNotification(paramContext.getText(paramInt2).toString(), paramString, paramClass).setStyle(new NotificationCompat.BigTextStyle().bigText(paramString)).build();
    paramContext.tickerText = paramString;
    paramContext.priority = 2;
    NotificationHelper.getInstance().notify(paramInt2, paramContext);
  }
  
  public static void a(long paramLong1, long paramLong2, Context paramContext)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("document_id", Long.valueOf(paramLong1));
    localContentValues.put("tag_id", Long.valueOf(paramLong2));
    paramContext.getContentResolver().insert(com.intsig.camscanner.provider.p.a, localContentValues);
    localContentValues.clear();
  }
  
  public static void a(Activity paramActivity, DialogInterface.OnClickListener paramOnClickListener)
  {
    if (j.h())
    {
      if ((j.k()) && (j.l()))
      {
        j.c(paramActivity);
        return;
      }
      a(paramActivity, paramActivity.getString(2131691013), paramActivity.getString(2131690223), paramOnClickListener);
      return;
    }
    a(paramActivity, paramActivity.getString(2131691013), paramActivity.getString(2131690225), paramOnClickListener);
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener)
  {
    bp localBp = new bp(paramActivity);
    if ((paramActivity != null) && (!paramActivity.isFinishing()))
    {
      paramString1 = new com.intsig.d.c(paramActivity).a(false).a(new bq()).a(paramString1).b(paramString2);
      if (paramOnClickListener != null) {
        paramActivity = paramOnClickListener;
      } else {
        paramActivity = localBp;
      }
      paramString1.d(2131691097, paramActivity).b();
      return;
    }
    e.b("Util", "showFatalErrorAndFinish activity has finished!");
  }
  
  public static void a(Bitmap paramBitmap)
  {
    if ((paramBitmap != null) && (!paramBitmap.isRecycled())) {
      paramBitmap.recycle();
    }
  }
  
  public static void a(RectF paramRectF, Rect paramRect)
  {
    paramRect.left = Math.round(paramRectF.left);
    paramRect.top = Math.round(paramRectF.top);
    paramRect.right = Math.round(paramRectF.right);
    paramRect.bottom = Math.round(paramRectF.bottom);
  }
  
  private static void a(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  /* Error */
  public static void a(byte[] paramArrayOfByte, String paramString, float paramFloat1, float paramFloat2)
  {
    // Byte code:
    //   0: invokestatic 663	java/lang/System:currentTimeMillis	()J
    //   3: lstore 11
    //   5: aconst_null
    //   6: astore 18
    //   8: aconst_null
    //   9: astore 17
    //   11: aconst_null
    //   12: astore 16
    //   14: new 945	java/io/ByteArrayInputStream
    //   17: dup
    //   18: aload_0
    //   19: invokespecial 948	java/io/ByteArrayInputStream:<init>	([B)V
    //   22: astore 15
    //   24: getstatic 953	android/os/Build$VERSION:SDK_INT	I
    //   27: bipush 24
    //   29: if_icmplt +17 -> 46
    //   32: new 955	android/media/ExifInterface
    //   35: dup
    //   36: aload 15
    //   38: invokespecial 956	android/media/ExifInterface:<init>	(Ljava/io/InputStream;)V
    //   41: astore 16
    //   43: goto +19 -> 62
    //   46: aload_0
    //   47: aload_1
    //   48: invokestatic 959	com/intsig/util/bo:a	([BLjava/lang/String;)Z
    //   51: pop
    //   52: new 955	android/media/ExifInterface
    //   55: dup
    //   56: aload_1
    //   57: invokespecial 960	android/media/ExifInterface:<init>	(Ljava/lang/String;)V
    //   60: astore 16
    //   62: aload 16
    //   64: ldc_w 962
    //   67: iconst_1
    //   68: invokevirtual 966	android/media/ExifInterface:getAttributeInt	(Ljava/lang/String;I)I
    //   71: invokestatic 266	com/intsig/utils/s:a	(I)I
    //   74: istore 6
    //   76: aload 16
    //   78: ldc_w 968
    //   81: iconst_0
    //   82: invokevirtual 966	android/media/ExifInterface:getAttributeInt	(Ljava/lang/String;I)I
    //   85: istore 7
    //   87: aload 16
    //   89: ldc_w 970
    //   92: iconst_0
    //   93: invokevirtual 966	android/media/ExifInterface:getAttributeInt	(Ljava/lang/String;I)I
    //   96: istore 8
    //   98: new 118	java/lang/StringBuilder
    //   101: dup
    //   102: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   105: astore 16
    //   107: aload 16
    //   109: ldc_w 972
    //   112: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: pop
    //   116: aload 16
    //   118: iload 6
    //   120: invokevirtual 248	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   123: pop
    //   124: aload 16
    //   126: ldc_w 974
    //   129: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: pop
    //   133: aload 16
    //   135: iload 7
    //   137: invokevirtual 248	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   140: pop
    //   141: aload 16
    //   143: ldc_w 976
    //   146: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: pop
    //   150: aload 16
    //   152: iload 8
    //   154: invokevirtual 248	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   157: pop
    //   158: ldc -123
    //   160: aload 16
    //   162: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   165: invokestatic 142	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   168: iload 6
    //   170: bipush 90
    //   172: if_icmpeq +17 -> 189
    //   175: fload_2
    //   176: fstore 5
    //   178: fload_3
    //   179: fstore 4
    //   181: iload 6
    //   183: sipush 270
    //   186: if_icmpne +44 -> 230
    //   189: fload_2
    //   190: fload_3
    //   191: invokestatic 978	com/intsig/util/bo:a	(FF)Landroid/util/Pair;
    //   194: astore 16
    //   196: aload 16
    //   198: getfield 982	android/util/Pair:first	Ljava/lang/Object;
    //   201: checkcast 587	java/lang/Float
    //   204: invokevirtual 986	java/lang/Float:floatValue	()F
    //   207: fstore 5
    //   209: aload 16
    //   211: getfield 989	android/util/Pair:second	Ljava/lang/Object;
    //   214: checkcast 587	java/lang/Float
    //   217: invokevirtual 986	java/lang/Float:floatValue	()F
    //   220: fstore 4
    //   222: ldc -123
    //   224: ldc_w 991
    //   227: invokestatic 142	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   230: iload 8
    //   232: i2f
    //   233: fstore_2
    //   234: fload 5
    //   236: fload_2
    //   237: fmul
    //   238: fstore_3
    //   239: iload 7
    //   241: i2f
    //   242: fstore 5
    //   244: fload 4
    //   246: fload 5
    //   248: fmul
    //   249: fstore 4
    //   251: fload_2
    //   252: fload_3
    //   253: fsub
    //   254: fconst_2
    //   255: fdiv
    //   256: f2i
    //   257: istore 7
    //   259: fload_2
    //   260: fload_3
    //   261: fadd
    //   262: fconst_2
    //   263: fdiv
    //   264: f2i
    //   265: istore 8
    //   267: fload 5
    //   269: fload 4
    //   271: fsub
    //   272: fconst_2
    //   273: fdiv
    //   274: f2i
    //   275: istore 9
    //   277: fload 5
    //   279: fload 4
    //   281: fadd
    //   282: fconst_2
    //   283: fdiv
    //   284: f2i
    //   285: istore 10
    //   287: aload_0
    //   288: iconst_0
    //   289: aload_0
    //   290: arraylength
    //   291: iconst_0
    //   292: invokestatic 997	android/graphics/BitmapRegionDecoder:newInstance	([BIIZ)Landroid/graphics/BitmapRegionDecoder;
    //   295: astore_0
    //   296: new 213	android/graphics/BitmapFactory$Options
    //   299: dup
    //   300: invokespecial 214	android/graphics/BitmapFactory$Options:<init>	()V
    //   303: astore 16
    //   305: aload_0
    //   306: new 918	android/graphics/Rect
    //   309: dup
    //   310: iload 7
    //   312: iload 9
    //   314: iload 8
    //   316: iload 10
    //   318: invokespecial 1000	android/graphics/Rect:<init>	(IIII)V
    //   321: aload 16
    //   323: invokevirtual 1004	android/graphics/BitmapRegionDecoder:decodeRegion	(Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   326: iload 6
    //   328: i2f
    //   329: invokestatic 1007	com/intsig/utils/s:a	(Landroid/graphics/Bitmap;F)Landroid/graphics/Bitmap;
    //   332: astore 16
    //   334: aload_0
    //   335: invokevirtual 1008	android/graphics/BitmapRegionDecoder:recycle	()V
    //   338: aload 16
    //   340: ifnull +55 -> 395
    //   343: new 1010	java/io/FileOutputStream
    //   346: dup
    //   347: new 104	java/io/File
    //   350: dup
    //   351: aload_1
    //   352: invokespecial 105	java/io/File:<init>	(Ljava/lang/String;)V
    //   355: invokespecial 1013	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   358: astore_0
    //   359: aload 16
    //   361: getstatic 1019	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   364: bipush 100
    //   366: aload_0
    //   367: invokevirtual 1023	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   370: pop
    //   371: aload_0
    //   372: invokevirtual 1026	java/io/FileOutputStream:flush	()V
    //   375: aload 16
    //   377: invokevirtual 289	android/graphics/Bitmap:recycle	()V
    //   380: goto +17 -> 397
    //   383: astore_1
    //   384: goto +258 -> 642
    //   387: astore_1
    //   388: goto +50 -> 438
    //   391: astore_1
    //   392: goto +62 -> 454
    //   395: aconst_null
    //   396: astore_0
    //   397: aload 15
    //   399: ifnull +18 -> 417
    //   402: aload 15
    //   404: invokevirtual 1029	java/io/InputStream:close	()V
    //   407: goto +10 -> 417
    //   410: astore_1
    //   411: ldc -123
    //   413: aload_1
    //   414: invokestatic 1032	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   417: aload_0
    //   418: ifnull +175 -> 593
    //   421: aload_0
    //   422: invokevirtual 1033	java/io/FileOutputStream:close	()V
    //   425: goto +168 -> 593
    //   428: astore_0
    //   429: aload 17
    //   431: astore_1
    //   432: goto +218 -> 650
    //   435: astore_1
    //   436: aconst_null
    //   437: astore_0
    //   438: aload 15
    //   440: astore 16
    //   442: aload_1
    //   443: astore 17
    //   445: aload_0
    //   446: astore 15
    //   448: goto +34 -> 482
    //   451: astore_1
    //   452: aconst_null
    //   453: astore_0
    //   454: aload 15
    //   456: astore 16
    //   458: aload_1
    //   459: astore 17
    //   461: aload_0
    //   462: astore 15
    //   464: goto +83 -> 547
    //   467: astore_0
    //   468: aconst_null
    //   469: astore 15
    //   471: aload 17
    //   473: astore_1
    //   474: goto +176 -> 650
    //   477: astore 17
    //   479: aconst_null
    //   480: astore 15
    //   482: aload 16
    //   484: astore_1
    //   485: aload 15
    //   487: astore_0
    //   488: ldc -123
    //   490: aload 17
    //   492: invokestatic 1032	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   495: aload 16
    //   497: ifnull +18 -> 515
    //   500: aload 16
    //   502: invokevirtual 1029	java/io/InputStream:close	()V
    //   505: goto +10 -> 515
    //   508: astore_0
    //   509: ldc -123
    //   511: aload_0
    //   512: invokestatic 1032	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   515: aload 15
    //   517: ifnull +76 -> 593
    //   520: aload 15
    //   522: invokevirtual 1033	java/io/FileOutputStream:close	()V
    //   525: goto +68 -> 593
    //   528: astore_0
    //   529: ldc -123
    //   531: aload_0
    //   532: invokestatic 1035	com/intsig/q/e:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   535: goto +58 -> 593
    //   538: astore 17
    //   540: aconst_null
    //   541: astore 15
    //   543: aload 18
    //   545: astore 16
    //   547: aload 16
    //   549: astore_1
    //   550: aload 15
    //   552: astore_0
    //   553: ldc -123
    //   555: ldc_w 1037
    //   558: aload 17
    //   560: invokestatic 301	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   563: aload 16
    //   565: ifnull +18 -> 583
    //   568: aload 16
    //   570: invokevirtual 1029	java/io/InputStream:close	()V
    //   573: goto +10 -> 583
    //   576: astore_0
    //   577: ldc -123
    //   579: aload_0
    //   580: invokestatic 1032	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   583: aload 15
    //   585: ifnull +8 -> 593
    //   588: aload 15
    //   590: invokevirtual 1033	java/io/FileOutputStream:close	()V
    //   593: invokestatic 663	java/lang/System:currentTimeMillis	()J
    //   596: lstore 13
    //   598: new 118	java/lang/StringBuilder
    //   601: dup
    //   602: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   605: astore_0
    //   606: aload_0
    //   607: ldc_w 1039
    //   610: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   613: pop
    //   614: aload_0
    //   615: lload 13
    //   617: lload 11
    //   619: lsub
    //   620: invokevirtual 129	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   623: pop
    //   624: ldc -123
    //   626: aload_0
    //   627: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   630: invokestatic 142	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   633: return
    //   634: astore 16
    //   636: aload_1
    //   637: astore 15
    //   639: aload 16
    //   641: astore_1
    //   642: aload_0
    //   643: astore 16
    //   645: aload_1
    //   646: astore_0
    //   647: aload 16
    //   649: astore_1
    //   650: aload 15
    //   652: ifnull +20 -> 672
    //   655: aload 15
    //   657: invokevirtual 1029	java/io/InputStream:close	()V
    //   660: goto +12 -> 672
    //   663: astore 15
    //   665: ldc -123
    //   667: aload 15
    //   669: invokestatic 1032	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   672: aload_1
    //   673: ifnull +17 -> 690
    //   676: aload_1
    //   677: invokevirtual 1033	java/io/FileOutputStream:close	()V
    //   680: goto +10 -> 690
    //   683: astore_1
    //   684: ldc -123
    //   686: aload_1
    //   687: invokestatic 1035	com/intsig/q/e:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   690: aload_0
    //   691: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	692	0	paramArrayOfByte	byte[]
    //   0	692	1	paramString	String
    //   0	692	2	paramFloat1	float
    //   0	692	3	paramFloat2	float
    //   179	101	4	f1	float
    //   176	102	5	f2	float
    //   74	253	6	i	int
    //   85	226	7	j	int
    //   96	219	8	k	int
    //   275	38	9	m	int
    //   285	32	10	n	int
    //   3	615	11	l1	long
    //   596	20	13	l2	long
    //   22	634	15	localObject1	Object
    //   663	5	15	localIOException1	IOException
    //   12	557	16	localObject2	Object
    //   634	6	16	localObject3	Object
    //   643	5	16	arrayOfByte	byte[]
    //   9	463	17	str	String
    //   477	14	17	localIOException2	IOException
    //   538	21	17	localFileNotFoundException	java.io.FileNotFoundException
    //   6	538	18	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   359	380	383	finally
    //   359	380	387	java/io/IOException
    //   359	380	391	java/io/FileNotFoundException
    //   402	407	410	java/io/IOException
    //   24	43	428	finally
    //   46	62	428	finally
    //   62	168	428	finally
    //   189	230	428	finally
    //   251	338	428	finally
    //   343	359	428	finally
    //   24	43	435	java/io/IOException
    //   46	62	435	java/io/IOException
    //   62	168	435	java/io/IOException
    //   189	230	435	java/io/IOException
    //   251	338	435	java/io/IOException
    //   343	359	435	java/io/IOException
    //   24	43	451	java/io/FileNotFoundException
    //   46	62	451	java/io/FileNotFoundException
    //   62	168	451	java/io/FileNotFoundException
    //   189	230	451	java/io/FileNotFoundException
    //   251	338	451	java/io/FileNotFoundException
    //   343	359	451	java/io/FileNotFoundException
    //   14	24	467	finally
    //   14	24	477	java/io/IOException
    //   500	505	508	java/io/IOException
    //   421	425	528	java/lang/Exception
    //   520	525	528	java/lang/Exception
    //   588	593	528	java/lang/Exception
    //   14	24	538	java/io/FileNotFoundException
    //   568	573	576	java/io/IOException
    //   488	495	634	finally
    //   553	563	634	finally
    //   655	660	663	java/io/IOException
    //   676	680	683	java/lang/Exception
  }
  
  public static boolean a(Context paramContext, long paramLong)
  {
    Uri localUri = ContentUris.withAppendedId(k.a, paramLong);
    paramContext = paramContext.getContentResolver();
    boolean bool1 = false;
    boolean bool2 = false;
    paramContext = paramContext.query(localUri, new String[] { "state", "_data" }, null, null, null);
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.moveToFirst()) {
        if ((paramContext.getInt(0) != 1) && (!TextUtils.isEmpty(paramContext.getString(1))))
        {
          bool1 = bool2;
          if (com.intsig.utils.p.c(paramContext.getString(1))) {}
        }
        else
        {
          bool1 = true;
        }
      }
      paramContext.close();
    }
    return bool1;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramString = d(paramString);
    if (paramString == null) {
      return false;
    }
    return paramString[0] * paramString[1] > com.intsig.camscanner.b.a.c(paramContext);
  }
  
  public static boolean a(Bitmap paramBitmap, float paramFloat1, float paramFloat2)
  {
    if (paramBitmap == null) {
      return false;
    }
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f = 1.0F * i / j;
    return (f > paramFloat2) || (f < paramFloat1);
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    return a(Base64.decode(paramString1, 0), paramString2);
  }
  
  public static boolean a(String paramString1, String paramString2, Context paramContext, DialogInterface paramDialogInterface)
  {
    return a(paramString1, paramString2, paramContext, paramDialogInterface, false, -1L, 2131691303);
  }
  
  public static boolean a(String paramString1, String paramString2, Context paramContext, DialogInterface paramDialogInterface, long paramLong)
  {
    return a(paramString1, paramString2, paramContext, paramDialogInterface, false, paramLong, -1);
  }
  
  public static boolean a(String paramString1, String paramString2, Context paramContext, DialogInterface paramDialogInterface, boolean paramBoolean, long paramLong, int paramInt)
  {
    boolean bool = true;
    if (paramBoolean) {
      return true;
    }
    Object localObject2;
    StringBuilder localStringBuilder;
    if (TextUtils.isEmpty(paramString1)) {
      if (paramLong > 0L)
      {
        localObject1 = "title=? and sync_dir_id IS NULL and _id <>? ";
        localObject2 = new String[2];
        localObject2[0] = paramString2;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramLong);
        localStringBuilder.append("");
        localObject2[1] = localStringBuilder.toString();
      }
    }
    for (;;)
    {
      break;
      localObject1 = "title=? and sync_dir_id IS NULL";
      localObject2 = new String[1];
      localObject2[0] = paramString2;
      continue;
      if (paramLong > 0L)
      {
        localObject1 = "sync_dir_id=? and title=? and _id <>? ";
        localObject2 = new String[3];
        localObject2[0] = paramString1;
        localObject2[1] = paramString2;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramLong);
        localStringBuilder.append("");
        localObject2[2] = localStringBuilder.toString();
      }
      else
      {
        localObject1 = "sync_dir_id=? and title=?";
        localObject2 = new String[2];
        localObject2[0] = paramString1;
        localObject2[1] = paramString2;
      }
    }
    Object localObject1 = paramContext.getContentResolver().query(k.a, new String[] { "title", "sync_dir_id" }, (String)localObject1, (String[])localObject2, null);
    paramBoolean = bool;
    if (localObject1 != null)
    {
      if (((Cursor)localObject1).getCount() == 0) {
        paramBoolean = true;
      } else {
        paramBoolean = false;
      }
      if (!paramBoolean)
      {
        if (((Cursor)localObject1).moveToFirst())
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("checkDocTitle=false newSyncDirId=");
          ((StringBuilder)localObject2).append(paramString1);
          ((StringBuilder)localObject2).append(" newDocTile=");
          ((StringBuilder)localObject2).append(paramString2);
          ((StringBuilder)localObject2).append(" oldSyncDirId=");
          ((StringBuilder)localObject2).append(((Cursor)localObject1).getString(1));
          ((StringBuilder)localObject2).append(" oldDocTitle=");
          ((StringBuilder)localObject2).append(((Cursor)localObject1).getString(0));
          e.b("Util", ((StringBuilder)localObject2).toString());
        }
        else
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("checkDocTitle=false newSyncDirId=");
          ((StringBuilder)localObject2).append(paramString1);
          ((StringBuilder)localObject2).append(" newDocTile=");
          ((StringBuilder)localObject2).append(paramString2);
          e.b("Util", ((StringBuilder)localObject2).toString());
        }
        if (paramInt > 0) {
          Toast.makeText(paramContext, paramInt, 0).show();
        }
        if (paramDialogInterface != null) {
          j.a(paramDialogInterface, false);
        }
      }
      ((Cursor)localObject1).close();
    }
    return paramBoolean;
  }
  
  public static boolean a(String paramString1, String paramString2, String paramString3, Context paramContext, DialogInterface paramDialogInterface)
  {
    boolean bool3 = TextUtils.isEmpty(paramString2);
    boolean bool1 = true;
    boolean bool2 = true;
    if (bool3)
    {
      if (TextUtils.isEmpty(paramString1))
      {
        paramString1 = "sync_state != ? and sync_state != ? and parent_sync_id IS NULL and title=?";
        paramString2 = new String[] { "2", "5", paramString3 };
      }
      else
      {
        paramString2 = new String[4];
        paramString2[0] = "2";
        paramString2[1] = "5";
        paramString2[2] = paramString3;
        paramString2[3] = paramString1;
        paramString1 = "sync_state != ? and sync_state != ? and parent_sync_id IS NULL and title =? and team_token =?";
      }
    }
    else {
      for (;;)
      {
        break;
        if (TextUtils.isEmpty(paramString1))
        {
          paramString1 = new String[4];
          paramString1[0] = "2";
          paramString1[1] = "5";
          paramString1[2] = paramString2;
          paramString1[3] = paramString3;
          paramString3 = "sync_state != ? and sync_state != ? and parent_sync_id=? and title=?";
          paramString2 = paramString1;
          paramString1 = paramString3;
        }
        else
        {
          String str = "sync_state != ? and sync_state != ? and parent_sync_id=? and title =? and team_token =?";
          paramString2 = new String[] { "2", "5", paramString2, paramString3, paramString1 };
          paramString1 = str;
        }
      }
    }
    paramString1 = paramContext.getContentResolver().query(i.a, null, paramString1, paramString2, null);
    if (paramString1 != null)
    {
      if (paramString1.getCount() == 0) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
      if (!bool1)
      {
        paramString2 = new StringBuilder();
        paramString2.append("checkFolderTitle=");
        paramString2.append(bool1);
        e.b("Util", paramString2.toString());
        Toast.makeText(paramContext, 2131690426, 0).show();
        if (paramDialogInterface != null) {
          j.a(paramDialogInterface, false);
        }
      }
      paramString1.close();
    }
    return bool1;
  }
  
  /* Error */
  public static boolean a(byte[] paramArrayOfByte, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aconst_null
    //   7: astore_3
    //   8: new 1010	java/io/FileOutputStream
    //   11: dup
    //   12: new 104	java/io/File
    //   15: dup
    //   16: aload_1
    //   17: invokespecial 105	java/io/File:<init>	(Ljava/lang/String;)V
    //   20: invokespecial 1013	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   23: astore_1
    //   24: aload_1
    //   25: aload_0
    //   26: invokevirtual 1108	java/io/FileOutputStream:write	([B)V
    //   29: aload_1
    //   30: invokevirtual 1026	java/io/FileOutputStream:flush	()V
    //   33: iconst_1
    //   34: istore_2
    //   35: aload_1
    //   36: ifnull +105 -> 141
    //   39: aload_1
    //   40: invokevirtual 1033	java/io/FileOutputStream:close	()V
    //   43: iconst_1
    //   44: ireturn
    //   45: astore_0
    //   46: ldc -123
    //   48: aload_0
    //   49: invokestatic 1035	com/intsig/q/e:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   52: iconst_1
    //   53: ireturn
    //   54: astore_0
    //   55: aload_1
    //   56: astore_3
    //   57: goto +86 -> 143
    //   60: astore_3
    //   61: aload_1
    //   62: astore_0
    //   63: aload_3
    //   64: astore_1
    //   65: goto +19 -> 84
    //   68: astore_3
    //   69: aload_1
    //   70: astore_0
    //   71: aload_3
    //   72: astore_1
    //   73: goto +37 -> 110
    //   76: astore_0
    //   77: goto +66 -> 143
    //   80: astore_1
    //   81: aload 4
    //   83: astore_0
    //   84: aload_0
    //   85: astore_3
    //   86: ldc -123
    //   88: ldc_w 1110
    //   91: aload_1
    //   92: invokestatic 301	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   95: aload_0
    //   96: ifnull +43 -> 139
    //   99: aload_0
    //   100: invokevirtual 1033	java/io/FileOutputStream:close	()V
    //   103: goto +36 -> 139
    //   106: astore_1
    //   107: aload 5
    //   109: astore_0
    //   110: aload_0
    //   111: astore_3
    //   112: ldc -123
    //   114: ldc_w 1037
    //   117: aload_1
    //   118: invokestatic 301	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   121: aload_0
    //   122: ifnull +17 -> 139
    //   125: aload_0
    //   126: invokevirtual 1033	java/io/FileOutputStream:close	()V
    //   129: goto +10 -> 139
    //   132: astore_0
    //   133: ldc -123
    //   135: aload_0
    //   136: invokestatic 1035	com/intsig/q/e:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ireturn
    //   143: aload_3
    //   144: ifnull +17 -> 161
    //   147: aload_3
    //   148: invokevirtual 1033	java/io/FileOutputStream:close	()V
    //   151: goto +10 -> 161
    //   154: astore_1
    //   155: ldc -123
    //   157: aload_1
    //   158: invokestatic 1035	com/intsig/q/e:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   161: aload_0
    //   162: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	163	0	paramArrayOfByte	byte[]
    //   0	163	1	paramString	String
    //   34	108	2	bool	boolean
    //   7	50	3	str	String
    //   60	4	3	localIOException	IOException
    //   68	4	3	localFileNotFoundException	java.io.FileNotFoundException
    //   85	63	3	arrayOfByte	byte[]
    //   1	81	4	localObject1	Object
    //   4	104	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   39	43	45	java/lang/Exception
    //   24	33	54	finally
    //   24	33	60	java/io/IOException
    //   24	33	68	java/io/FileNotFoundException
    //   8	24	76	finally
    //   86	95	76	finally
    //   112	121	76	finally
    //   8	24	80	java/io/IOException
    //   8	24	106	java/io/FileNotFoundException
    //   99	103	132	java/lang/Exception
    //   125	129	132	java/lang/Exception
    //   147	151	154	java/lang/Exception
  }
  
  public static float[] a(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt != null)
    {
      int i = 0;
      float[] arrayOfFloat2 = new float[paramArrayOfInt.length];
      int k = paramArrayOfInt.length;
      int j = 0;
      for (;;)
      {
        arrayOfFloat1 = arrayOfFloat2;
        if (i >= k) {
          break;
        }
        arrayOfFloat2[j] = paramArrayOfInt[i];
        j += 1;
        i += 1;
      }
    }
    e.b("Util", "intArrary2FloatArray null");
    float[] arrayOfFloat1 = null;
    return arrayOfFloat1;
  }
  
  public static int[] a(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    return a(paramBitmap, paramInt1, paramInt2, true);
  }
  
  public static int[] a(Bitmap paramBitmap, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    float f3 = paramBitmap.getWidth();
    float f4 = paramBitmap.getHeight();
    float f1 = 1.0F * f3;
    float f2 = f1 / f4;
    float f7 = paramInt1;
    float f8 = 1.0F * f7;
    float f6 = paramInt2;
    float f9 = f8 / f6;
    float f5 = 0.0F;
    if ((f7 <= f3) && (f6 <= f4))
    {
      if (paramBoolean)
      {
        f1 = (f3 - f7) / 2.0F;
        f2 = (f4 - f6) / 2.0F;
        f3 = f7;
        f4 = f6;
        break label230;
      }
      if (f2 > f9)
      {
        f1 = f8 * f4 / f6;
        f2 = (f3 - f1) / 2.0F;
        break label211;
      }
      f1 = 1.0F * f6 * f3 / f7;
      f2 = (f4 - f1) / 2.0F;
    }
    else
    {
      if (f2 >= f9) {
        break label191;
      }
      f1 /= f9;
      f2 = 1.0F * (f4 - f1) / 2.0F;
    }
    f4 = f1;
    f1 = f5;
    break label230;
    label191:
    f1 = 1.0F * f4 * f9;
    f2 = 1.0F * (f3 - f1) / 2.0F;
    label211:
    f6 = 0.0F;
    f5 = f2;
    f3 = f1;
    f2 = f6;
    f1 = f5;
    label230:
    return new int[] { (int)f1, (int)f2, (int)f3, (int)f4 };
  }
  
  public static int[] a(byte[] paramArrayOfByte)
  {
    Object localObject = new BitmapFactory.Options();
    ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
    ((BitmapFactory.Options)localObject).inSampleSize = 1;
    BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, (BitmapFactory.Options)localObject);
    if ((!((BitmapFactory.Options)localObject).mCancel) && (((BitmapFactory.Options)localObject).outWidth != -1) && (((BitmapFactory.Options)localObject).outHeight != -1)) {
      return new int[] { ((BitmapFactory.Options)localObject).outWidth, ((BitmapFactory.Options)localObject).outHeight };
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getImageBound error ");
    ((StringBuilder)localObject).append(paramArrayOfByte);
    e.c("Util", ((StringBuilder)localObject).toString());
    return null;
  }
  
  public static long[] a(ArrayList<Long> paramArrayList)
  {
    long[] arrayOfLong = new long[paramArrayList.size()];
    int i = 0;
    int j = arrayOfLong.length;
    while (i < j)
    {
      arrayOfLong[i] = ((Long)paramArrayList.get(i)).longValue();
      i += 1;
    }
    return arrayOfLong;
  }
  
  public static long[] a(List<Long> paramList)
  {
    if (paramList != null)
    {
      long[] arrayOfLong = new long[paramList.size()];
      int i = 0;
      Iterator localIterator = paramList.iterator();
      for (;;)
      {
        paramList = arrayOfLong;
        if (!localIterator.hasNext()) {
          break;
        }
        arrayOfLong[i] = ((Long)localIterator.next()).longValue();
        i += 1;
      }
    }
    e.b("Util", "longList2LongArray null");
    paramList = null;
    return paramList;
  }
  
  private static int b(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    double d1 = paramOptions.outWidth;
    double d2 = paramOptions.outHeight;
    int i;
    if (paramInt2 == -1) {
      i = 1;
    } else {
      i = (int)Math.ceil(Math.sqrt(d1 * d2 / paramInt2));
    }
    int j;
    double d3;
    if (paramInt1 == -1)
    {
      j = 128;
    }
    else
    {
      d3 = paramInt1;
      j = (int)Math.min(Math.floor(d1 / d3), Math.floor(d2 / d3));
    }
    if ((com.intsig.camscanner.a.c.a) && (j == 0))
    {
      float f1 = (float)Math.sqrt(d1 * d2 / paramInt2);
      d3 = paramInt1;
      float f2 = (float)Math.min(d1 / d3, d2 / d3);
      if ((f1 >= 0.5F) && (f2 <= 0.5F)) {
        return 2;
      }
    }
    if (j < i) {
      return i;
    }
    if ((paramInt2 == -1) && (paramInt1 == -1)) {
      return 1;
    }
    if (paramInt1 == -1) {
      return i;
    }
    return j;
  }
  
  public static Bitmap b(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    return b(paramBitmap, paramInt1, paramInt2, true);
  }
  
  public static Bitmap b(Bitmap paramBitmap, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramBitmap == null) {
      return null;
    }
    Object localObject1 = a(paramBitmap, paramInt1, paramInt2, paramBoolean);
    try
    {
      localObject1 = Bitmap.createBitmap(paramBitmap, localObject1[0], localObject1[1], localObject1[2], localObject1[3], null, true);
      localObject2 = localObject1;
      if (localObject1 == null) {
        break label112;
      }
      localObject2 = localObject1;
      try
      {
        if (localObject1.equals(paramBitmap)) {
          break label112;
        }
        paramBitmap.recycle();
        return localObject1;
      }
      catch (Exception paramBitmap) {}catch (OutOfMemoryError paramBitmap) {}
      e.b("Util", "cropImage", paramBitmap);
    }
    catch (Exception paramBitmap)
    {
      localObject1 = null;
      e.b("Util", "cropImage", paramBitmap);
      return localObject1;
    }
    catch (OutOfMemoryError paramBitmap)
    {
      localObject1 = null;
    }
    System.gc();
    Object localObject2 = localObject1;
    label112:
    return localObject2;
  }
  
  public static Bitmap b(String paramString, int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    return a(paramString, paramInt1, paramInt2, paramConfig, true);
  }
  
  public static CharSequence b(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    int i = y.k(paramContext);
    int j = y.b(paramContext, true);
    int k = y.g(paramContext);
    paramContext = new StringBuilder();
    paramContext.append("dbImageCount = ");
    paramContext.append(k);
    localStringBuilder.append(paramContext.toString());
    paramContext = new StringBuilder();
    paramContext.append("dbDocCount = ");
    paramContext.append(i);
    localStringBuilder.append(paramContext.toString());
    paramContext = new StringBuilder();
    paramContext.append("dbTeamDocCount = ");
    paramContext.append(j - i);
    localStringBuilder.append(paramContext.toString());
    paramContext = new br();
    Object localObject = new File(t.g()).listFiles(paramContext);
    i = 0;
    if (localObject != null) {
      i = 0 + localObject.length;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("\n.images count = ");
    ((StringBuilder)localObject).append(i);
    localStringBuilder.append(((StringBuilder)localObject).toString());
    paramContext = new File(t.h()).listFiles(paramContext);
    j = i;
    if (paramContext != null)
    {
      j = i + paramContext.length;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(" images count = ");
      ((StringBuilder)localObject).append(paramContext.length);
      localStringBuilder.append(((StringBuilder)localObject).toString());
    }
    paramContext = new StringBuilder();
    paramContext.append(" total count = ");
    paramContext.append(j);
    localStringBuilder.append(paramContext.toString());
    return localStringBuilder;
  }
  
  public static String b(int paramInt)
  {
    Object localObject = Calendar.getInstance();
    if (paramInt == 1) {
      paramInt = ((Calendar)localObject).get(1);
    } else if (paramInt == 2) {
      paramInt = ((Calendar)localObject).get(2) + 1;
    } else if (paramInt == 3) {
      paramInt = ((Calendar)localObject).get(5);
    } else if (paramInt == 4) {
      paramInt = ((Calendar)localObject).get(11);
    } else if (paramInt == 5) {
      paramInt = ((Calendar)localObject).get(12);
    } else if (paramInt == 6) {
      paramInt = ((Calendar)localObject).get(13);
    } else {
      paramInt = 0;
    }
    if (paramInt < 10)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("0");
      ((StringBuilder)localObject).append(paramInt);
      return ((StringBuilder)localObject).toString();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).append(paramInt);
    return ((StringBuilder)localObject).toString();
  }
  
  public static String b(Context paramContext, int paramInt)
  {
    Object localObject = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getString(paramInt));
    localStringBuilder.append(" ");
    localStringBuilder.append((String)localObject);
    paramContext = localStringBuilder.toString();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getSpecificDocName docName:");
    ((StringBuilder)localObject).append(paramContext);
    e.b("Util", ((StringBuilder)localObject).toString());
    return paramContext;
  }
  
  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject1;
    if (TextUtils.isEmpty(paramString1))
    {
      localObject1 = "title=? and sync_dir_id IS NULL";
      paramString1 = new String[1];
    }
    else
    {
      localObject1 = new String[2];
      localObject1[1] = paramString1;
      localObject2 = "title=? and sync_dir_id =? ";
      paramString1 = (String)localObject1;
      localObject1 = localObject2;
    }
    Object localObject2 = "";
    int i = 0;
    for (;;)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString2);
      localStringBuilder.append((String)localObject2);
      paramString1[0] = localStringBuilder.toString();
      localObject2 = paramContext.getContentResolver().query(k.a, new String[] { "_id" }, (String)localObject1, paramString1, null);
      if (localObject2 == null) {
        return paramString1[0];
      }
      if (((Cursor)localObject2).getCount() < 1)
      {
        ((Cursor)localObject2).close();
        return paramString1[0];
      }
      ((Cursor)localObject2).close();
      i += 1;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(" (");
      ((StringBuilder)localObject2).append(i);
      ((StringBuilder)localObject2).append(")");
      localObject2 = ((StringBuilder)localObject2).toString();
    }
  }
  
  public static String b(Context paramContext, boolean paramBoolean)
  {
    return a(paramContext, null, false, paramBoolean);
  }
  
  public static String b(String paramString)
  {
    if (paramString != null)
    {
      int i = paramString.lastIndexOf("/");
      if (i != -1)
      {
        i = paramString.substring(0, i).lastIndexOf("/");
        if (i != -1) {
          return paramString.substring(i + 1);
        }
      }
    }
    return null;
  }
  
  public static List<Long> b(Context paramContext, long paramLong)
  {
    paramContext = paramContext.getContentResolver();
    Object localObject1 = com.intsig.camscanner.provider.p.a;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("document_id=");
    ((StringBuilder)localObject2).append(paramLong);
    localObject2 = ((StringBuilder)localObject2).toString();
    paramContext = paramContext.query((Uri)localObject1, new String[] { "tag_id", "tag_sync_id" }, (String)localObject2, null, null);
    if (paramContext != null)
    {
      localObject1 = new ArrayList();
      while (paramContext.moveToNext())
      {
        paramLong = paramContext.getLong(0);
        if (paramLong > 0L)
        {
          ((List)localObject1).add(Long.valueOf(paramLong));
        }
        else
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("getDosTagIds tag id <0, tag sync id=");
          ((StringBuilder)localObject2).append(paramContext.getString(1));
          e.b("Util", ((StringBuilder)localObject2).toString());
        }
      }
      paramContext.close();
      return localObject1;
    }
    e.b("Util", "getDosTagIds, Fail to get the current tag id!");
    return null;
  }
  
  @Deprecated
  public static boolean b()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    paramString = d(paramString);
    boolean bool = false;
    if (paramString == null)
    {
      e.b("Util", "size == null");
      return false;
    }
    long l1 = System.currentTimeMillis();
    long l2 = j.v(paramContext);
    paramContext = new StringBuilder();
    paramContext.append("enableScale costTime=");
    paramContext.append(System.currentTimeMillis() - l1);
    paramContext.append(" availeMemory=");
    paramContext.append(l2);
    paramContext.append(" size=");
    paramContext.append(Arrays.toString(paramString));
    e.b("Util", paramContext.toString());
    if (12L * paramString[0] * paramString[1] < l2) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean b(String paramString1, String paramString2)
  {
    return (!TextUtils.isEmpty(paramString1)) && (paramString1.endsWith(paramString2));
  }
  
  public static boolean b(String paramString1, String paramString2, Context paramContext, DialogInterface paramDialogInterface, long paramLong)
  {
    return a(paramString1, paramString2, paramContext, paramDialogInterface, false, paramLong, 2131691303);
  }
  
  public static byte[] b(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      return null;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
    paramBitmap.recycle();
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static int c(Context paramContext, int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getResources().getDisplayMetrics());
  }
  
  public static Bitmap c(String paramString)
  {
    return a(paramString, ScannerApplication.k);
  }
  
  public static String c(Context paramContext, String paramString)
  {
    return a(paramContext, paramString, 0);
  }
  
  public static String c(Context paramContext, boolean paramBoolean)
  {
    String str = "";
    Object localObject2 = r.aU(paramContext);
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("getMyDocName nameFormat:");
    ((StringBuilder)localObject1).append((String)localObject2);
    e.b("Util", ((StringBuilder)localObject1).toString());
    if (TextUtils.isEmpty((CharSequence)localObject2))
    {
      str = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
      if (paramBoolean) {
        paramContext = paramContext.getString(2131689966);
      } else {
        paramContext = paramContext.getString(2131691089);
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramContext);
      ((StringBuilder)localObject1).append(" ");
      ((StringBuilder)localObject1).append(str);
      return ((StringBuilder)localObject1).toString();
    }
    localObject1 = str;
    for (;;)
    {
      try
      {
        NameResult.NamePart[] arrayOfNamePart = new NameResult((String)localObject2).nameParts;
        i = 0;
        localObject1 = str;
        localObject2 = str;
        if (i >= arrayOfNamePart.length) {
          break label424;
        }
        localObject1 = str;
        int j = arrayOfNamePart[i].type;
        Object localObject3;
        if (j == 0)
        {
          localObject1 = str;
          localObject2 = arrayOfNamePart[i].name;
          localObject1 = str;
          localObject3 = new StringBuilder();
          localObject1 = str;
          ((StringBuilder)localObject3).append(str);
          localObject1 = str;
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject1 = str;
          str = ((StringBuilder)localObject3).toString();
        }
        else if (j == 7)
        {
          localObject1 = str;
          localObject3 = y.a(paramContext, r.b(), true);
          localObject2 = localObject3;
          localObject1 = str;
          if (TextUtils.isEmpty((CharSequence)localObject3))
          {
            localObject1 = str;
            localObject2 = paramContext.getString(2131689836);
          }
          localObject1 = str;
          localObject3 = new StringBuilder();
          localObject1 = str;
          ((StringBuilder)localObject3).append(str);
          localObject1 = str;
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject1 = str;
          str = ((StringBuilder)localObject3).toString();
        }
        else
        {
          localObject1 = str;
          localObject2 = new StringBuilder();
          localObject1 = str;
          ((StringBuilder)localObject2).append(str);
          localObject1 = str;
          ((StringBuilder)localObject2).append(b(j));
          localObject1 = str;
          str = ((StringBuilder)localObject2).toString();
        }
      }
      catch (JSONException paramContext)
      {
        int i;
        e.a("Util", paramContext);
        localObject2 = localObject1;
      }
      i += 1;
      continue;
      label424:
      return localObject2;
    }
  }
  
  public static boolean c()
  {
    return Build.VERSION.SDK_INT >= 14;
  }
  
  public static boolean c(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    if (paramContext == null) {
      return false;
    }
    boolean bool = paramContext.isAvailable();
    paramContext = new StringBuilder();
    paramContext.append("NetState = ");
    paramContext.append(bool);
    e.d("Util", paramContext.toString());
    return bool;
  }
  
  public static byte[] c(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      return null;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static Uri d(Context paramContext, String paramString)
  {
    return a(paramContext, new com.intsig.datastruct.a(paramString, null, null, false));
  }
  
  public static String d()
  {
    File[] arrayOfFile = new File(t.k()).listFiles(new bs());
    if ((arrayOfFile != null) && (arrayOfFile.length > 0))
    {
      Arrays.sort(arrayOfFile, new bt());
      return arrayOfFile[0].getAbsolutePath();
    }
    return null;
  }
  
  public static String d(Context paramContext)
  {
    StringBuilder localStringBuilder1 = new StringBuilder(256);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("APP: ");
    localStringBuilder2.append(paramContext.getString(2131690896));
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\nPackageName: ");
    localStringBuilder2.append(paramContext.getPackageName());
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" Version: ");
    localStringBuilder2.append(paramContext.getString(2131690897));
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" LOG_PATH: ");
    localStringBuilder2.append(a.k);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\nDevice");
    localStringBuilder2.append(Build.DEVICE);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" Board: ");
    localStringBuilder2.append(Build.BOARD);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" Brand: ");
    localStringBuilder2.append(Build.BRAND);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\nDisplay: ");
    localStringBuilder2.append(Build.DISPLAY);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" Manufature: ");
    localStringBuilder2.append(Build.MANUFACTURER);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" Model: ");
    localStringBuilder2.append(Build.MODEL);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" RELEASE: ");
    localStringBuilder2.append(Build.VERSION.RELEASE);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\nLocale: ");
    localStringBuilder2.append(Locale.getDefault().toString());
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder1.append(TimeZone.getDefault().getDisplayName());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\nVendor: ");
    localStringBuilder2.append(com.intsig.camscanner.a.f.J);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" Full: ");
    localStringBuilder2.append(ScannerApplication.f());
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" Pay: ");
    localStringBuilder2.append(ScannerApplication.h());
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\nAlwaysFinishActivities: ");
    localStringBuilder2.append(dw.a(paramContext.getContentResolver()));
    localStringBuilder1.append(localStringBuilder2.toString());
    boolean bool = r.d();
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" KEY_SAVE_TO_GALLERY: ");
    localStringBuilder2.append(bool);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\nNetType: ");
    localStringBuilder2.append(e(paramContext));
    localStringBuilder1.append(localStringBuilder2.toString());
    return localStringBuilder1.toString();
  }
  
  public static int[] d(String paramString)
  {
    Object localObject = new BitmapFactory.Options();
    ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
    ((BitmapFactory.Options)localObject).inSampleSize = 1;
    BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject);
    if ((!((BitmapFactory.Options)localObject).mCancel) && (((BitmapFactory.Options)localObject).outWidth != -1) && (((BitmapFactory.Options)localObject).outHeight != -1)) {
      return new int[] { ((BitmapFactory.Options)localObject).outWidth, ((BitmapFactory.Options)localObject).outHeight };
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getImageBound error ");
    ((StringBuilder)localObject).append(paramString);
    e.c("Util", ((StringBuilder)localObject).toString());
    return null;
  }
  
  public static String e(Context paramContext)
  {
    String str2 = "NONE";
    String str1 = str2;
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      str1 = str2;
      if (paramContext != null)
      {
        str1 = str2;
        if (paramContext.isConnected())
        {
          if (paramContext.getType() == 1) {
            return "WIFI";
          }
          str1 = str2;
          if (paramContext.getType() == 0) {
            str1 = "MOBILE";
          }
        }
      }
    }
    return str1;
  }
  
  public static boolean e(String paramString)
  {
    Object localObject = paramString;
    if (!paramString.endsWith(File.separator))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(File.separator);
      localObject = ((StringBuilder)localObject).toString();
    }
    paramString = new File((String)localObject);
    if (paramString.exists())
    {
      if (!paramString.isDirectory()) {
        return false;
      }
      localObject = paramString.listFiles();
      if (localObject != null)
      {
        int j = localObject.length;
        boolean bool2 = true;
        int i = 0;
        for (;;)
        {
          bool1 = bool2;
          if (i >= j) {
            break;
          }
          if (localObject[i].isFile())
          {
            bool2 = localObject[i].delete();
            bool1 = bool2;
            if (!bool2)
            {
              bool1 = bool2;
              break;
            }
          }
          else
          {
            bool1 = bool2;
            if (localObject[i].isDirectory())
            {
              bool2 = e(localObject[i].getAbsolutePath());
              bool1 = bool2;
              if (!bool2)
              {
                bool1 = bool2;
                break;
              }
            }
          }
          i += 1;
          bool2 = bool1;
        }
      }
      boolean bool1 = true;
      if (!bool1) {
        return false;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString.getAbsolutePath());
      ((StringBuilder)localObject).append(System.currentTimeMillis());
      localObject = new File(((StringBuilder)localObject).toString());
      paramString.renameTo((File)localObject);
      return ((File)localObject).delete();
    }
    return false;
  }
  
  public static boolean f(Context paramContext)
  {
    return (ScannerApplication.h()) || (av.d()) || (ScannerApplication.i());
  }
  
  public static boolean f(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return new File(paramString).exists();
  }
  
  public static int g(Context paramContext)
  {
    if (a > 0) {
      return a;
    }
    if (paramContext == null) {
      throw new NullPointerException("context == null ");
    }
    int j = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    int i = 0;
    if (j > 0) {
      i = paramContext.getResources().getDimensionPixelSize(j);
    }
    if (i <= 0) {
      i = c(paramContext, 24);
    }
    a = i;
    return i;
  }
  
  /* Error */
  public static String g(String paramString)
  {
    // Byte code:
    //   0: new 118	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   7: astore 5
    //   9: new 118	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   16: astore_1
    //   17: aload_1
    //   18: ldc_w 1488
    //   21: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload_1
    //   26: aload_0
    //   27: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: ldc -123
    //   33: aload_1
    //   34: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokestatic 142	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   40: invokestatic 1494	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   43: aload_0
    //   44: invokevirtual 1498	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   47: invokevirtual 1504	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   50: astore_1
    //   51: new 1506	java/io/BufferedReader
    //   54: dup
    //   55: new 732	java/io/InputStreamReader
    //   58: dup
    //   59: aload_1
    //   60: invokespecial 733	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   63: invokespecial 1509	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   66: astore_0
    //   67: aload_0
    //   68: astore_2
    //   69: aload_1
    //   70: astore_3
    //   71: aload_0
    //   72: invokevirtual 1512	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   75: astore 4
    //   77: aload_0
    //   78: astore_3
    //   79: aload_1
    //   80: astore_2
    //   81: aload 4
    //   83: ifnull +109 -> 192
    //   86: aload_0
    //   87: astore_2
    //   88: aload_1
    //   89: astore_3
    //   90: new 118	java/lang/StringBuilder
    //   93: dup
    //   94: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   97: astore 6
    //   99: aload_0
    //   100: astore_2
    //   101: aload_1
    //   102: astore_3
    //   103: aload 6
    //   105: aload 4
    //   107: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: pop
    //   111: aload_0
    //   112: astore_2
    //   113: aload_1
    //   114: astore_3
    //   115: aload 6
    //   117: ldc_w 1514
    //   120: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: pop
    //   124: aload_0
    //   125: astore_2
    //   126: aload_1
    //   127: astore_3
    //   128: aload 5
    //   130: aload 6
    //   132: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: pop
    //   139: goto -72 -> 67
    //   142: astore 4
    //   144: goto +30 -> 174
    //   147: astore_0
    //   148: aconst_null
    //   149: astore_2
    //   150: goto +59 -> 209
    //   153: astore 4
    //   155: aconst_null
    //   156: astore_0
    //   157: goto +17 -> 174
    //   160: astore_0
    //   161: aconst_null
    //   162: astore_1
    //   163: aload_1
    //   164: astore_2
    //   165: goto +44 -> 209
    //   168: astore 4
    //   170: aconst_null
    //   171: astore_0
    //   172: aload_0
    //   173: astore_1
    //   174: aload_0
    //   175: astore_2
    //   176: aload_1
    //   177: astore_3
    //   178: ldc -123
    //   180: ldc_w 1516
    //   183: aload 4
    //   185: invokestatic 301	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   188: aload_1
    //   189: astore_2
    //   190: aload_0
    //   191: astore_3
    //   192: aload_2
    //   193: invokestatic 1517	com/intsig/utils/p:a	(Ljava/io/Closeable;)V
    //   196: aload_3
    //   197: invokestatic 1517	com/intsig/utils/p:a	(Ljava/io/Closeable;)V
    //   200: aload 5
    //   202: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   205: areturn
    //   206: astore_0
    //   207: aload_3
    //   208: astore_1
    //   209: aload_1
    //   210: invokestatic 1517	com/intsig/utils/p:a	(Ljava/io/Closeable;)V
    //   213: aload_2
    //   214: invokestatic 1517	com/intsig/utils/p:a	(Ljava/io/Closeable;)V
    //   217: aload_0
    //   218: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	219	0	paramString	String
    //   16	194	1	localObject1	Object
    //   68	146	2	localObject2	Object
    //   70	138	3	localObject3	Object
    //   75	31	4	str	String
    //   142	1	4	localException1	Exception
    //   153	1	4	localException2	Exception
    //   168	16	4	localException3	Exception
    //   7	194	5	localStringBuilder1	StringBuilder
    //   97	34	6	localStringBuilder2	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   71	77	142	java/lang/Exception
    //   90	99	142	java/lang/Exception
    //   103	111	142	java/lang/Exception
    //   115	124	142	java/lang/Exception
    //   128	139	142	java/lang/Exception
    //   51	67	147	finally
    //   51	67	153	java/lang/Exception
    //   40	51	160	finally
    //   40	51	168	java/lang/Exception
    //   71	77	206	finally
    //   90	99	206	finally
    //   103	111	206	finally
    //   115	124	206	finally
    //   128	139	206	finally
    //   178	188	206	finally
  }
  
  public static String h(String paramString)
  {
    String str = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" ");
    localStringBuilder.append(str);
    return localStringBuilder.toString();
  }
  
  public static ArrayList<String> h(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager();
      int i = 0;
      paramContext = paramContext.getInstalledPackages(0);
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    e.b("Util", "get install app failed");
    return localArrayList;
  }
}
