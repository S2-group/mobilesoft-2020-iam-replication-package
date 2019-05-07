package com.xvideostudio.videoeditor.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import android.support.v4.content.FileProvider;
import com.umeng.analytics.MobclickAgent;
import com.xvideostudio.videoeditor.l.a;
import com.xvideostudio.videoeditor.o.b;
import com.xvideostudio.videoeditor.tool.i;
import com.xvideostudio.videoeditor.tool.j;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class l
{
  public static final String a;
  public static final String b;
  private static String[] c = { "_data", "_data" };
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(d());
    localStringBuilder.append("/data/");
    a = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(a);
    localStringBuilder.append("ins.dat");
    b = localStringBuilder.toString();
  }
  
  public static double a(long paramLong)
  {
    return w.a(paramLong * 1.0D / 1048576.0D, 2, 4);
  }
  
  public static long a(String paramString, boolean paramBoolean)
  {
    if (paramString != null) {}
    try
    {
      paramString = new File(paramString);
      if (paramString.exists())
      {
        long l = b(paramString);
        if ((paramBoolean) && (l <= 0L)) {
          paramString.delete();
        }
        return l;
      }
      return -1L;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return -2L;
    return -3L;
  }
  
  public static Bitmap a(Context paramContext, int paramInt)
  {
    return BitmapFactory.decodeResource(paramContext.getResources(), paramInt);
  }
  
  public static Bitmap a(Bitmap paramBitmap1, Bitmap paramBitmap2, boolean paramBoolean)
  {
    if (paramBitmap1 != null)
    {
      if (paramBitmap2 == null) {
        return null;
      }
      Object localObject1;
      if (paramBoolean)
      {
        localObject1 = paramBitmap1;
      }
      else
      {
        localObject2 = paramBitmap1.getConfig();
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = Bitmap.Config.ARGB_8888;
        }
        localObject1 = paramBitmap1.copy((Bitmap.Config)localObject1, true);
      }
      Object localObject2 = new Canvas((Bitmap)localObject1);
      ((Canvas)localObject2).drawBitmap(paramBitmap1, 0.0F, 0.0F, null);
      ((Canvas)localObject2).drawBitmap(paramBitmap2, 0.0F, 0.0F, null);
      ((Canvas)localObject2).save(31);
      ((Canvas)localObject2).restore();
      return localObject1;
    }
    return null;
  }
  
  public static Bitmap a(String paramString, BitmapFactory.Options paramOptions)
  {
    try
    {
      paramString = BitmapFactory.decodeFile(paramString, paramOptions);
      return paramString;
    }
    catch (OutOfMemoryError paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(File.separator);
    localStringBuilder.append("udisk");
    return localStringBuilder.toString();
  }
  
  public static String a(long paramLong1, int paramInt, long paramLong2)
  {
    if (paramLong2 == 1024L)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(w.a(paramLong1 * 1.0D / 1024.0D, paramInt, 4));
      localStringBuilder.append("KB");
      return localStringBuilder.toString();
    }
    if (paramLong2 == 1048576L)
    {
      if (paramLong1 >= 1048576L)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(w.a(paramLong1 * 1.0D / 1048576.0D, paramInt, 4));
        localStringBuilder.append("MB");
        return localStringBuilder.toString();
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(w.a(paramLong1 * 1.0D / 1024.0D, paramInt, 4));
      localStringBuilder.append("KB");
      return localStringBuilder.toString();
    }
    if (paramLong2 == 1073741824L)
    {
      if (paramLong1 >= 1073741824L)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(w.a(paramLong1 * 1.0D / 1.073741824E9D, paramInt, 4));
        localStringBuilder.append("GB");
        return localStringBuilder.toString();
      }
      if (paramLong1 >= 1048576L)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(w.a(paramLong1 * 1.0D / 1048576.0D, paramInt, 4));
        localStringBuilder.append("MB");
        return localStringBuilder.toString();
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(w.a(paramLong1 * 1.0D / 1024.0D, paramInt, 4));
      localStringBuilder.append("KB");
      return localStringBuilder.toString();
    }
    if (paramLong2 == 1099511627776L)
    {
      if (paramLong1 >= 1099511627776L)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(w.a(paramLong1 * 1.0D / 1.099511627776E12D, paramInt, 4));
        localStringBuilder.append("TB");
        return localStringBuilder.toString();
      }
      if (paramLong1 >= 1073741824L)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(w.a(paramLong1 * 1.0D / 1.073741824E9D, paramInt, 4));
        localStringBuilder.append("GB");
        return localStringBuilder.toString();
      }
      if (paramLong1 >= 1048576L)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(w.a(paramLong1 * 1.0D / 1048576.0D, paramInt, 4));
        localStringBuilder.append("MB");
        return localStringBuilder.toString();
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(w.a(paramLong1 * 1.0D / 1024.0D, paramInt, 4));
      localStringBuilder.append("KB");
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramLong1);
    localStringBuilder.append("B");
    return localStringBuilder.toString();
  }
  
  public static String a(long paramLong1, long paramLong2)
  {
    return a(paramLong1, 2, paramLong2);
  }
  
  @SuppressLint({"NewApi"})
  public static String a(Context paramContext, Uri paramUri)
  {
    int i = 0;
    Object localObject3 = null;
    if (paramUri == null) {
      return null;
    }
    if ((Build.VERSION.SDK_INT >= 19) && (DocumentsContract.isDocumentUri(paramContext, paramUri)))
    {
      Object localObject2 = DocumentsContract.getDocumentId(paramUri);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = "";
      }
      localObject2 = ((String)localObject1).split(":");
      if (localObject2.length > 1) {
        localObject1 = localObject2[1];
      }
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "_data";
      if (paramUri.toString().contains("video")) {
        localObject2 = paramContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, arrayOfString, "_id=?", new String[] { localObject1 }, null);
      } else {
        localObject2 = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, arrayOfString, "_id=?", new String[] { localObject1 }, null);
      }
      i = ((Cursor)localObject2).getColumnIndex(arrayOfString[0]);
      localObject1 = localObject3;
      if (((Cursor)localObject2).moveToFirst()) {
        localObject1 = ((Cursor)localObject2).getString(i);
      }
      if (!((Cursor)localObject2).isClosed()) {
        ((Cursor)localObject2).close();
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = c(paramContext, paramUri);
      }
      return localObject2;
    }
    if (paramUri.toString().contains("file://"))
    {
      paramContext = paramUri.toString().split("file://")[1];
      if (!a(paramContext)) {
        try
        {
          paramUri = URLDecoder.decode(paramContext, "UTF-8");
          return paramUri;
        }
        catch (Exception paramUri)
        {
          paramUri.printStackTrace();
        }
      }
      return paramContext;
    }
    Object localObject1 = paramUri;
    if (paramUri.toString().contains("flg=")) {
      localObject1 = Uri.parse(paramUri.toString().split("flg=")[0]);
    }
    if (localObject1 == null) {
      return null;
    }
    try
    {
      localObject1 = paramContext.getContentResolver().query((Uri)localObject1, c, null, null, null);
      if (localObject1 != null)
      {
        int j = 0;
        while (i < c.length)
        {
          j = ((Cursor)localObject1).getColumnIndexOrThrow(c[i]);
          i += 1;
        }
        ((Cursor)localObject1).moveToFirst();
        paramContext = ((Cursor)localObject1).getString(j);
        paramUri = paramContext;
        try
        {
          if (((Cursor)localObject1).isClosed()) {
            return paramUri;
          }
          ((Cursor)localObject1).close();
          return paramContext;
        }
        catch (Exception paramUri) {}
      }
      else
      {
        return null;
      }
    }
    catch (Exception paramUri)
    {
      paramContext = null;
      paramUri.printStackTrace();
      paramUri = paramContext;
    }
    return paramUri;
  }
  
  public static String a(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, paramString, paramArrayOfString, null);
      if (paramContext != null) {
        try
        {
          if (paramContext.moveToFirst())
          {
            paramUri = paramContext.getString(paramContext.getColumnIndexOrThrow("_data"));
            if (paramContext != null) {
              paramContext.close();
            }
            return paramUri;
          }
        }
        finally
        {
          break label80;
        }
      }
      if (paramContext != null) {
        paramContext.close();
      }
      return null;
    }
    finally
    {
      paramContext = null;
      label80:
      if (paramContext != null) {
        paramContext.close();
      }
    }
  }
  
  private static String a(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128);
      if ((paramContext != null) && (paramContext.metaData != null)) {
        paramContext = paramContext.metaData.get(paramString);
      } else {
        paramContext = null;
      }
      return paramContext.toString();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static void a(Context paramContext, String paramString, int paramInt)
    throws IOException
  {
    paramString = new BufferedOutputStream(new FileOutputStream(paramString));
    paramContext = new BufferedInputStream(paramContext.getResources().openRawResource(paramInt));
    byte[] arrayOfByte = new byte[32768];
    for (;;)
    {
      paramInt = paramContext.read(arrayOfByte);
      if (paramInt <= 0) {
        break;
      }
      paramString.write(arrayOfByte, 0, paramInt);
    }
    paramString.flush();
    paramString.close();
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      String[] arrayOfString = paramContext.getResources().getAssets().list(paramString1);
      File localFile = new File(paramString2);
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
      int i = 0;
      while (i < arrayOfString.length)
      {
        Object localObject1 = arrayOfString[i];
        try
        {
          Object localObject2;
          Object localObject3;
          if (!((String)localObject1).contains("."))
          {
            if (paramString1.length() == 0)
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append(paramString2);
              ((StringBuilder)localObject2).append((String)localObject1);
              ((StringBuilder)localObject2).append(File.separator);
              a(paramContext, (String)localObject1, ((StringBuilder)localObject2).toString(), paramString3);
            }
            else
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append(paramString1);
              ((StringBuilder)localObject2).append("/");
              ((StringBuilder)localObject2).append((String)localObject1);
              localObject2 = ((StringBuilder)localObject2).toString();
              localObject3 = new StringBuilder();
              ((StringBuilder)localObject3).append(paramString2);
              ((StringBuilder)localObject3).append((String)localObject1);
              ((StringBuilder)localObject3).append(File.separator);
              a(paramContext, (String)localObject2, ((StringBuilder)localObject3).toString(), paramString3);
            }
          }
          else if ((paramString3 == null) || (paramString3.equalsIgnoreCase((String)localObject1)))
          {
            localObject2 = new File(localFile, (String)localObject1);
            if (((File)localObject2).exists()) {
              ((File)localObject2).delete();
            }
            if (paramString1.length() != 0)
            {
              localObject3 = paramContext.getAssets();
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append(paramString1);
              localStringBuilder.append(File.separator);
              localStringBuilder.append((String)localObject1);
              localObject1 = ((AssetManager)localObject3).open(localStringBuilder.toString());
            }
            else
            {
              localObject1 = paramContext.getAssets().open((String)localObject1);
            }
            localObject2 = new FileOutputStream((File)localObject2);
            localObject3 = new byte['Ѐ'];
            for (;;)
            {
              int j = ((InputStream)localObject1).read((byte[])localObject3);
              if (j <= 0) {
                break;
              }
              ((OutputStream)localObject2).write((byte[])localObject3, 0, j);
            }
            ((InputStream)localObject1).close();
            ((OutputStream)localObject2).close();
          }
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          localFileNotFoundException.printStackTrace();
        }
        i += 1;
      }
      return;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void a(String paramString1, String paramString2)
  {
    paramString1 = new File(paramString1);
    paramString1.renameTo(new File(paramString2));
    paramString1.delete();
  }
  
  public static void a(List<String> paramList1, List<String> paramList2, String paramString, boolean paramBoolean)
  {
    LinkedList localLinkedList = new LinkedList();
    Object localObject = new File(paramString).listFiles();
    if (localObject == null) {
      return;
    }
    int i = 0;
    String str2;
    String str1;
    while (i < localObject.length)
    {
      if (localObject[i].isDirectory())
      {
        localLinkedList.add(localObject[i]);
      }
      else
      {
        str2 = localObject[i].getAbsolutePath();
        str1 = localObject[i].getName();
        paramString = str1;
        if (!paramBoolean) {
          paramString = j(str1);
        }
        paramList1.add(str2);
        paramList2.add(paramString);
      }
      i += 1;
    }
    while (!localLinkedList.isEmpty())
    {
      paramString = (File)localLinkedList.removeFirst();
      if (paramString.isDirectory())
      {
        localObject = paramString.listFiles();
        if (localObject != null)
        {
          i = 0;
          while (i < localObject.length)
          {
            if (localObject[i].isDirectory())
            {
              localLinkedList.add(localObject[i]);
            }
            else
            {
              str2 = localObject[i].getAbsolutePath();
              str1 = localObject[i].getName();
              paramString = str1;
              if (!paramBoolean) {
                paramString = j(str1);
              }
              paramList1.add(str2);
              paramList2.add(paramString);
            }
            i += 1;
          }
        }
      }
      else
      {
        localObject = paramString.getAbsolutePath();
        str1 = paramString.getName();
        paramString = str1;
        if (!paramBoolean) {
          paramString = j(str1);
        }
        paramList1.add(localObject);
        paramList2.add(paramString);
      }
    }
  }
  
  public static boolean a(Context paramContext, int paramInt, String paramString)
  {
    try
    {
      paramString = new BufferedOutputStream(new FileOutputStream(paramString));
      paramContext = new BufferedInputStream(paramContext.getResources().openRawResource(paramInt));
      byte[] arrayOfByte = new byte[32768];
      for (;;)
      {
        paramInt = paramContext.read(arrayOfByte);
        if (paramInt <= 0) {
          break;
        }
        paramString.write(arrayOfByte, 0, paramInt);
      }
      paramString.flush();
      paramString.close();
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getResources().getAssets().list(paramString1);
      int i = 0;
      while (i < paramContext.length)
      {
        if (paramString2.equalsIgnoreCase(paramContext[i])) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (e(paramString) >= 2147483648L)
    {
      j.a(2131689567, -1, 1);
      if (paramBoolean)
      {
        MobclickAgent.onEvent(paramContext, "NOT_SUPPORTED_2G_EDITOR_CHOOSE");
        return true;
      }
      MobclickAgent.onEvent(paramContext, "NOT_SUPPORTED_2G_OUT_ACTIVITY");
      return true;
    }
    return false;
  }
  
  public static boolean a(Bitmap paramBitmap, String paramString, int paramInt)
  {
    if (paramString != null)
    {
      if (paramBitmap == null) {
        return false;
      }
      try
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("filePath:");
        localStringBuilder.append(paramString);
        i.b("saveBitmapToSdCardJPG", localStringBuilder.toString());
        paramString = new FileOutputStream(paramString);
        bool = paramBitmap.compress(Bitmap.CompressFormat.JPEG, paramInt, paramString);
        if (paramString != null) {}
        paramBitmap.printStackTrace();
      }
      catch (Exception paramBitmap)
      {
        try
        {
          paramString.close();
          paramBitmap = new StringBuilder();
          paramBitmap.append("saveRet:");
          paramBitmap.append(bool);
          i.b("saveBitmapToSdCardJPG", paramBitmap.toString());
          return bool;
        }
        catch (Exception paramBitmap)
        {
          boolean bool;
          for (;;) {}
        }
        paramBitmap = paramBitmap;
        bool = false;
      }
      return bool;
    }
    return false;
  }
  
  public static boolean a(Bitmap paramBitmap, String paramString, int paramInt1, int paramInt2)
  {
    if (a(paramBitmap, paramString, paramInt1)) {
      return b(paramString, paramInt2);
    }
    return false;
  }
  
  public static boolean a(Uri paramUri)
  {
    return "com.android.externalstorage.documents".equals(paramUri.getAuthority());
  }
  
  public static boolean a(File paramFile)
  {
    int i = 0;
    if (paramFile == null) {
      return false;
    }
    if (paramFile.isFile()) {
      return paramFile.delete();
    }
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      if ((arrayOfFile != null) && (arrayOfFile.length != 0))
      {
        while (i < arrayOfFile.length)
        {
          a(arrayOfFile[i]);
          i += 1;
        }
        return paramFile.delete();
      }
      return paramFile.delete();
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    if ((paramString != null) && (!paramString.equals(""))) {
      return new File(paramString).exists();
    }
    return false;
  }
  
  public static boolean a(String paramString, int paramInt)
  {
    try
    {
      paramString = new FileOutputStream(new File(paramString));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramInt);
      localStringBuilder.append("");
      paramString.write(localStringBuilder.toString().getBytes());
      paramString.close();
      return true;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  /* Error */
  public static boolean a(String paramString1, String paramString2, android.os.Handler paramHandler)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +505 -> 506
    //   4: aload_1
    //   5: ifnonnull +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: new 55	java/io/File
    //   13: dup
    //   14: aload_0
    //   15: invokespecial 58	java/io/File:<init>	(Ljava/lang/String;)V
    //   18: astore 10
    //   20: aload 10
    //   22: invokevirtual 62	java/io/File:exists	()Z
    //   25: ifne +5 -> 30
    //   28: iconst_1
    //   29: ireturn
    //   30: lconst_0
    //   31: lstore 6
    //   33: new 512	java/io/FileInputStream
    //   36: dup
    //   37: aload 10
    //   39: invokespecial 513	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   42: astore_0
    //   43: new 309	java/io/FileOutputStream
    //   46: dup
    //   47: aload_1
    //   48: invokespecial 310	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   51: astore 9
    //   53: aload_0
    //   54: astore_1
    //   55: aload 9
    //   57: astore 8
    //   59: ldc_w 325
    //   62: newarray byte
    //   64: astore 11
    //   66: iconst_0
    //   67: istore_3
    //   68: aload_0
    //   69: astore_1
    //   70: aload 9
    //   72: astore 8
    //   74: aload_0
    //   75: aload 11
    //   77: invokevirtual 380	java/io/InputStream:read	([B)I
    //   80: istore 4
    //   82: iload 4
    //   84: iconst_m1
    //   85: if_icmpeq +232 -> 317
    //   88: lload 6
    //   90: iload 4
    //   92: i2l
    //   93: ladd
    //   94: lstore 6
    //   96: aload_0
    //   97: astore_1
    //   98: aload 9
    //   100: astore 8
    //   102: new 13	java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial 16	java/lang/StringBuilder:<init>	()V
    //   109: astore 12
    //   111: aload_0
    //   112: astore_1
    //   113: aload 9
    //   115: astore 8
    //   117: aload 12
    //   119: lload 6
    //   121: invokevirtual 163	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   124: pop
    //   125: aload_0
    //   126: astore_1
    //   127: aload 9
    //   129: astore 8
    //   131: aload 12
    //   133: ldc -66
    //   135: invokevirtual 24	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: pop
    //   139: aload_0
    //   140: astore_1
    //   141: aload 9
    //   143: astore 8
    //   145: aconst_null
    //   146: aload 12
    //   148: invokevirtual 29	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: invokestatic 463	com/xvideostudio/videoeditor/tool/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   154: aload_0
    //   155: astore_1
    //   156: aload 9
    //   158: astore 8
    //   160: aload 9
    //   162: aload 11
    //   164: iconst_0
    //   165: iload 4
    //   167: invokevirtual 514	java/io/FileOutputStream:write	([BII)V
    //   170: aload_0
    //   171: astore_1
    //   172: aload 9
    //   174: astore 8
    //   176: ldc2_w 515
    //   179: lload 6
    //   181: lmul
    //   182: aload 10
    //   184: invokevirtual 519	java/io/File:length	()J
    //   187: ldiv
    //   188: l2i
    //   189: istore 5
    //   191: iload_3
    //   192: istore 4
    //   194: iload_3
    //   195: iload 5
    //   197: if_icmpeq +114 -> 311
    //   200: aload_2
    //   201: ifnull +106 -> 307
    //   204: aload_0
    //   205: astore_1
    //   206: aload 9
    //   208: astore 8
    //   210: new 521	android/os/Message
    //   213: dup
    //   214: invokespecial 522	android/os/Message:<init>	()V
    //   217: astore 12
    //   219: aload_0
    //   220: astore_1
    //   221: aload 9
    //   223: astore 8
    //   225: new 296	android/os/Bundle
    //   228: dup
    //   229: invokespecial 523	android/os/Bundle:<init>	()V
    //   232: astore 13
    //   234: aload_0
    //   235: astore_1
    //   236: aload 9
    //   238: astore 8
    //   240: aload 13
    //   242: ldc_w 525
    //   245: lload 6
    //   247: invokevirtual 529	android/os/Bundle:putLong	(Ljava/lang/String;J)V
    //   250: aload_0
    //   251: astore_1
    //   252: aload 9
    //   254: astore 8
    //   256: aload 13
    //   258: ldc_w 531
    //   261: aload 10
    //   263: invokevirtual 519	java/io/File:length	()J
    //   266: invokevirtual 529	android/os/Bundle:putLong	(Ljava/lang/String;J)V
    //   269: aload_0
    //   270: astore_1
    //   271: aload 9
    //   273: astore 8
    //   275: aload 12
    //   277: iconst_0
    //   278: putfield 534	android/os/Message:what	I
    //   281: aload_0
    //   282: astore_1
    //   283: aload 9
    //   285: astore 8
    //   287: aload 12
    //   289: aload 13
    //   291: invokevirtual 538	android/os/Message:setData	(Landroid/os/Bundle;)V
    //   294: aload_0
    //   295: astore_1
    //   296: aload 9
    //   298: astore 8
    //   300: aload_2
    //   301: aload 12
    //   303: invokevirtual 544	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   306: pop
    //   307: iload 5
    //   309: istore 4
    //   311: iload 4
    //   313: istore_3
    //   314: goto -246 -> 68
    //   317: aload_0
    //   318: ifnull +15 -> 333
    //   321: aload_0
    //   322: invokevirtual 384	java/io/InputStream:close	()V
    //   325: goto +8 -> 333
    //   328: astore_0
    //   329: aload_0
    //   330: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   333: aload 9
    //   335: ifnull +20 -> 355
    //   338: aload 9
    //   340: invokevirtual 545	java/io/FileOutputStream:flush	()V
    //   343: aload 9
    //   345: invokevirtual 474	java/io/FileOutputStream:close	()V
    //   348: iconst_1
    //   349: ireturn
    //   350: astore_0
    //   351: aload_0
    //   352: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   355: iconst_1
    //   356: ireturn
    //   357: astore_1
    //   358: aload_0
    //   359: astore_2
    //   360: aload 9
    //   362: astore_0
    //   363: aload_1
    //   364: astore 9
    //   366: goto +37 -> 403
    //   369: astore_1
    //   370: goto +17 -> 387
    //   373: astore 9
    //   375: aconst_null
    //   376: astore_1
    //   377: aload_0
    //   378: astore_2
    //   379: aload_1
    //   380: astore_0
    //   381: goto +22 -> 403
    //   384: astore_1
    //   385: aconst_null
    //   386: astore_0
    //   387: aconst_null
    //   388: astore 8
    //   390: aload_0
    //   391: astore_2
    //   392: aload_1
    //   393: astore_0
    //   394: goto +71 -> 465
    //   397: astore 9
    //   399: aconst_null
    //   400: astore_2
    //   401: aload_2
    //   402: astore_0
    //   403: aload_2
    //   404: astore_1
    //   405: aload_0
    //   406: astore 8
    //   408: aconst_null
    //   409: ldc_w 547
    //   412: invokestatic 463	com/xvideostudio/videoeditor/tool/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   415: aload_2
    //   416: astore_1
    //   417: aload_0
    //   418: astore 8
    //   420: aload 9
    //   422: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   425: aload_2
    //   426: ifnull +15 -> 441
    //   429: aload_2
    //   430: invokevirtual 384	java/io/InputStream:close	()V
    //   433: goto +8 -> 441
    //   436: astore_1
    //   437: aload_1
    //   438: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   441: aload_0
    //   442: ifnull +18 -> 460
    //   445: aload_0
    //   446: invokevirtual 545	java/io/FileOutputStream:flush	()V
    //   449: aload_0
    //   450: invokevirtual 474	java/io/FileOutputStream:close	()V
    //   453: iconst_0
    //   454: ireturn
    //   455: astore_0
    //   456: aload_0
    //   457: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   460: iconst_0
    //   461: ireturn
    //   462: astore_0
    //   463: aload_1
    //   464: astore_2
    //   465: aload_2
    //   466: ifnull +15 -> 481
    //   469: aload_2
    //   470: invokevirtual 384	java/io/InputStream:close	()V
    //   473: goto +8 -> 481
    //   476: astore_1
    //   477: aload_1
    //   478: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   481: aload 8
    //   483: ifnull +21 -> 504
    //   486: aload 8
    //   488: invokevirtual 545	java/io/FileOutputStream:flush	()V
    //   491: aload 8
    //   493: invokevirtual 474	java/io/FileOutputStream:close	()V
    //   496: goto +8 -> 504
    //   499: astore_1
    //   500: aload_1
    //   501: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   504: aload_0
    //   505: athrow
    //   506: iconst_0
    //   507: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	508	0	paramString1	String
    //   0	508	1	paramString2	String
    //   0	508	2	paramHandler	android.os.Handler
    //   67	247	3	i	int
    //   80	232	4	j	int
    //   189	119	5	k	int
    //   31	215	6	l	long
    //   57	435	8	localObject1	Object
    //   51	314	9	localObject2	Object
    //   373	1	9	localException1	Exception
    //   397	24	9	localException2	Exception
    //   18	244	10	localFile	File
    //   64	99	11	arrayOfByte	byte[]
    //   109	193	12	localObject3	Object
    //   232	58	13	localBundle	Bundle
    // Exception table:
    //   from	to	target	type
    //   321	325	328	java/lang/Exception
    //   338	348	350	java/lang/Exception
    //   59	66	357	java/lang/Exception
    //   74	82	357	java/lang/Exception
    //   102	111	357	java/lang/Exception
    //   117	125	357	java/lang/Exception
    //   131	139	357	java/lang/Exception
    //   145	154	357	java/lang/Exception
    //   160	170	357	java/lang/Exception
    //   176	191	357	java/lang/Exception
    //   210	219	357	java/lang/Exception
    //   225	234	357	java/lang/Exception
    //   240	250	357	java/lang/Exception
    //   256	269	357	java/lang/Exception
    //   275	281	357	java/lang/Exception
    //   287	294	357	java/lang/Exception
    //   300	307	357	java/lang/Exception
    //   43	53	369	finally
    //   43	53	373	java/lang/Exception
    //   33	43	384	finally
    //   33	43	397	java/lang/Exception
    //   429	433	436	java/lang/Exception
    //   445	453	455	java/lang/Exception
    //   59	66	462	finally
    //   74	82	462	finally
    //   102	111	462	finally
    //   117	125	462	finally
    //   131	139	462	finally
    //   145	154	462	finally
    //   160	170	462	finally
    //   176	191	462	finally
    //   210	219	462	finally
    //   225	234	462	finally
    //   240	250	462	finally
    //   256	269	462	finally
    //   275	281	462	finally
    //   287	294	462	finally
    //   300	307	462	finally
    //   408	415	462	finally
    //   420	425	462	finally
    //   469	473	476	java/lang/Exception
    //   486	496	499	java/lang/Exception
  }
  
  public static boolean a(String paramString1, String paramString2, boolean paramBoolean)
  {
    File localFile = new File(paramString1);
    boolean bool = localFile.exists();
    int i = 0;
    if (!bool) {
      return false;
    }
    String str = k(paramString1);
    Object localObject = paramString2;
    if (paramBoolean)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString2);
      ((StringBuilder)localObject).append(File.separator);
      ((StringBuilder)localObject).append(str);
      localObject = ((StringBuilder)localObject).toString();
    }
    if (((String)localObject).equals(paramString1)) {
      return false;
    }
    paramString1 = new File((String)localObject);
    if (!paramString1.exists()) {
      paramString1.mkdirs();
    }
    paramString1 = localFile.listFiles();
    if (paramString1.length == 0) {
      return true;
    }
    int j = paramString1.length;
    bool = false;
    while (i < j)
    {
      paramString2 = paramString1[i];
      if (paramString2.isFile()) {
        bool = d(paramString2.getAbsolutePath(), (String)localObject);
      } else if (paramString2.isDirectory()) {
        bool = a(paramString2.getAbsolutePath(), (String)localObject, paramBoolean);
      }
      if (!bool) {
        return bool;
      }
      i += 1;
    }
    return bool;
  }
  
  public static long b(File paramFile)
    throws Exception
  {
    if (paramFile.isFile()) {
      return paramFile.length();
    }
    long l = 0L;
    paramFile = paramFile.listFiles();
    int i = 0;
    while (i < paramFile.length)
    {
      if (paramFile[i].isDirectory()) {}
      for (l += b(paramFile[i]);; l += paramFile[i].length()) {
        break;
      }
      i += 1;
    }
    return l;
  }
  
  public static String b()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public static String b(Context paramContext, Uri paramUri)
  {
    if (paramUri == null) {
      return null;
    }
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
      int i = paramContext.getColumnIndexOrThrow("_data");
      paramContext.moveToFirst();
      paramUri = paramContext.getString(i);
      if (!paramContext.isClosed()) {
        paramContext.close();
      }
      return paramUri;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = a(paramContext, paramString1);
    paramContext = paramString1;
    if (paramString1 == null) {
      paramContext = paramString2;
    }
    return paramContext;
  }
  
  public static void b(String paramString1, String paramString2, boolean paramBoolean)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString2);
    ((StringBuilder)localObject).append("\n");
    paramString2 = ((StringBuilder)localObject).toString();
    try
    {
      paramString1 = new File(paramString1);
      if (!paramBoolean) {
        paramString1.createNewFile();
      } else if (!paramString1.exists()) {
        paramString1.createNewFile();
      }
      localObject = new RandomAccessFile(paramString1, "rw");
      ((RandomAccessFile)localObject).seek(paramString1.length());
      ((RandomAccessFile)localObject).write(paramString2.getBytes());
      ((RandomAccessFile)localObject).close();
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static boolean b(Bitmap paramBitmap, String paramString, int paramInt)
  {
    if (paramString != null)
    {
      if (paramBitmap == null) {
        return false;
      }
      try
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("filePath:");
        localStringBuilder.append(paramString);
        i.b("saveBitmapToSdCardPNG", localStringBuilder.toString());
        paramString = new FileOutputStream(paramString);
        bool = paramBitmap.compress(Bitmap.CompressFormat.PNG, paramInt, paramString);
        if (paramString != null) {}
        paramBitmap.printStackTrace();
      }
      catch (Exception paramBitmap)
      {
        try
        {
          paramString.close();
          paramBitmap = new StringBuilder();
          paramBitmap.append("saveRet:");
          paramBitmap.append(bool);
          i.b("saveBitmapToSdCardPNG", paramBitmap.toString());
          return bool;
        }
        catch (Exception paramBitmap)
        {
          boolean bool;
          for (;;) {}
        }
        paramBitmap = paramBitmap;
        bool = false;
      }
      return bool;
    }
    return false;
  }
  
  public static boolean b(Bitmap paramBitmap, String paramString, int paramInt1, int paramInt2)
  {
    if (b(paramBitmap, paramString, paramInt1)) {
      return b(paramString, paramInt2);
    }
    return false;
  }
  
  public static boolean b(Uri paramUri)
  {
    return "com.android.providers.downloads.documents".equals(paramUri.getAuthority());
  }
  
  public static boolean b(String paramString)
  {
    paramString = new File(paramString);
    if ((paramString.exists()) && (paramString.isFile())) {
      paramString.delete();
    }
    if (!paramString.exists()) {
      return paramString.mkdirs();
    }
    return true;
  }
  
  private static boolean b(String paramString, int paramInt)
  {
    try
    {
      paramString = new ExifInterface(paramString);
      paramInt = a.a(paramInt);
      if (paramInt != 0)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramInt);
        localStringBuilder.append("");
        paramString.setAttribute("Orientation", localStringBuilder.toString());
        paramString.saveAttributes();
      }
      return true;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  /* Error */
  public static boolean b(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +366 -> 367
    //   4: aload_1
    //   5: ifnonnull +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: new 55	java/io/File
    //   13: dup
    //   14: aload_0
    //   15: invokespecial 58	java/io/File:<init>	(Ljava/lang/String;)V
    //   18: astore_0
    //   19: aload_0
    //   20: invokevirtual 62	java/io/File:exists	()Z
    //   23: ifne +5 -> 28
    //   26: iconst_0
    //   27: ireturn
    //   28: new 512	java/io/FileInputStream
    //   31: dup
    //   32: aload_0
    //   33: invokespecial 513	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   36: astore 4
    //   38: new 55	java/io/File
    //   41: dup
    //   42: aload_1
    //   43: invokespecial 58	java/io/File:<init>	(Ljava/lang/String;)V
    //   46: invokevirtual 609	java/io/File:getParentFile	()Ljava/io/File;
    //   49: astore_0
    //   50: aload_0
    //   51: invokevirtual 62	java/io/File:exists	()Z
    //   54: ifne +8 -> 62
    //   57: aload_0
    //   58: invokevirtual 353	java/io/File:mkdirs	()Z
    //   61: pop
    //   62: new 309	java/io/FileOutputStream
    //   65: dup
    //   66: aload_1
    //   67: invokespecial 310	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   70: astore_0
    //   71: aload_0
    //   72: astore_1
    //   73: aload 4
    //   75: astore 5
    //   77: ldc_w 325
    //   80: newarray byte
    //   82: astore 6
    //   84: iconst_0
    //   85: istore_2
    //   86: aload_0
    //   87: astore_1
    //   88: aload 4
    //   90: astore 5
    //   92: aload 4
    //   94: aload 6
    //   96: invokevirtual 380	java/io/InputStream:read	([B)I
    //   99: istore_3
    //   100: iload_3
    //   101: iconst_m1
    //   102: if_icmpeq +81 -> 183
    //   105: iload_2
    //   106: iload_3
    //   107: iadd
    //   108: istore_2
    //   109: aload_0
    //   110: astore_1
    //   111: aload 4
    //   113: astore 5
    //   115: new 13	java/lang/StringBuilder
    //   118: dup
    //   119: invokespecial 16	java/lang/StringBuilder:<init>	()V
    //   122: astore 7
    //   124: aload_0
    //   125: astore_1
    //   126: aload 4
    //   128: astore 5
    //   130: aload 7
    //   132: iload_2
    //   133: invokevirtual 502	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   136: pop
    //   137: aload_0
    //   138: astore_1
    //   139: aload 4
    //   141: astore 5
    //   143: aload 7
    //   145: ldc -66
    //   147: invokevirtual 24	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: pop
    //   151: aload_0
    //   152: astore_1
    //   153: aload 4
    //   155: astore 5
    //   157: aconst_null
    //   158: aload 7
    //   160: invokevirtual 29	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokestatic 463	com/xvideostudio/videoeditor/tool/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: aload_0
    //   167: astore_1
    //   168: aload 4
    //   170: astore 5
    //   172: aload_0
    //   173: aload 6
    //   175: iconst_0
    //   176: iload_3
    //   177: invokevirtual 514	java/io/FileOutputStream:write	([BII)V
    //   180: goto -94 -> 86
    //   183: aload 4
    //   185: ifnull +16 -> 201
    //   188: aload 4
    //   190: invokevirtual 384	java/io/InputStream:close	()V
    //   193: goto +8 -> 201
    //   196: astore_1
    //   197: aload_1
    //   198: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   201: aload_0
    //   202: ifnull +19 -> 221
    //   205: aload_0
    //   206: invokevirtual 545	java/io/FileOutputStream:flush	()V
    //   209: aload_0
    //   210: invokevirtual 474	java/io/FileOutputStream:close	()V
    //   213: goto +8 -> 221
    //   216: astore_0
    //   217: aload_0
    //   218: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   221: iconst_1
    //   222: ireturn
    //   223: astore 6
    //   225: goto +32 -> 257
    //   228: astore_0
    //   229: aconst_null
    //   230: astore_1
    //   231: goto +94 -> 325
    //   234: astore 6
    //   236: aconst_null
    //   237: astore_0
    //   238: goto +19 -> 257
    //   241: astore_0
    //   242: aconst_null
    //   243: astore_1
    //   244: aload_1
    //   245: astore 4
    //   247: goto +78 -> 325
    //   250: astore 6
    //   252: aconst_null
    //   253: astore_0
    //   254: aload_0
    //   255: astore 4
    //   257: aload_0
    //   258: astore_1
    //   259: aload 4
    //   261: astore 5
    //   263: aconst_null
    //   264: ldc_w 547
    //   267: invokestatic 463	com/xvideostudio/videoeditor/tool/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   270: aload_0
    //   271: astore_1
    //   272: aload 4
    //   274: astore 5
    //   276: aload 6
    //   278: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   281: aload 4
    //   283: ifnull +16 -> 299
    //   286: aload 4
    //   288: invokevirtual 384	java/io/InputStream:close	()V
    //   291: goto +8 -> 299
    //   294: astore_1
    //   295: aload_1
    //   296: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   299: aload_0
    //   300: ifnull +18 -> 318
    //   303: aload_0
    //   304: invokevirtual 545	java/io/FileOutputStream:flush	()V
    //   307: aload_0
    //   308: invokevirtual 474	java/io/FileOutputStream:close	()V
    //   311: iconst_0
    //   312: ireturn
    //   313: astore_0
    //   314: aload_0
    //   315: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   318: iconst_0
    //   319: ireturn
    //   320: astore_0
    //   321: aload 5
    //   323: astore 4
    //   325: aload 4
    //   327: ifnull +18 -> 345
    //   330: aload 4
    //   332: invokevirtual 384	java/io/InputStream:close	()V
    //   335: goto +10 -> 345
    //   338: astore 4
    //   340: aload 4
    //   342: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   345: aload_1
    //   346: ifnull +19 -> 365
    //   349: aload_1
    //   350: invokevirtual 545	java/io/FileOutputStream:flush	()V
    //   353: aload_1
    //   354: invokevirtual 474	java/io/FileOutputStream:close	()V
    //   357: goto +8 -> 365
    //   360: astore_1
    //   361: aload_1
    //   362: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   365: aload_0
    //   366: athrow
    //   367: iconst_0
    //   368: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	369	0	paramString1	String
    //   0	369	1	paramString2	String
    //   85	48	2	i	int
    //   99	78	3	j	int
    //   36	295	4	localObject1	Object
    //   338	3	4	localException1	Exception
    //   75	247	5	localObject2	Object
    //   82	92	6	arrayOfByte	byte[]
    //   223	1	6	localException2	Exception
    //   234	1	6	localException3	Exception
    //   250	27	6	localException4	Exception
    //   122	37	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   188	193	196	java/lang/Exception
    //   205	213	216	java/lang/Exception
    //   77	84	223	java/lang/Exception
    //   92	100	223	java/lang/Exception
    //   115	124	223	java/lang/Exception
    //   130	137	223	java/lang/Exception
    //   143	151	223	java/lang/Exception
    //   157	166	223	java/lang/Exception
    //   172	180	223	java/lang/Exception
    //   38	62	228	finally
    //   62	71	228	finally
    //   38	62	234	java/lang/Exception
    //   62	71	234	java/lang/Exception
    //   28	38	241	finally
    //   28	38	250	java/lang/Exception
    //   286	291	294	java/lang/Exception
    //   303	311	313	java/lang/Exception
    //   77	84	320	finally
    //   92	100	320	finally
    //   115	124	320	finally
    //   130	137	320	finally
    //   143	151	320	finally
    //   157	166	320	finally
    //   172	180	320	finally
    //   263	270	320	finally
    //   276	281	320	finally
    //   330	335	338	java/lang/Exception
    //   349	357	360	java/lang/Exception
  }
  
  @TargetApi(19)
  public static String c(Context paramContext, Uri paramUri)
  {
    if (paramContext != null) {
      if (paramUri == null) {
        return null;
      }
    }
    for (;;)
    {
      try
      {
        if ((Build.VERSION.SDK_INT >= 19) && (DocumentsContract.isDocumentUri(paramContext, paramUri)))
        {
          String[] arrayOfString;
          if (a(paramUri))
          {
            arrayOfString = DocumentsContract.getDocumentId(paramUri).split(":");
            if ("primary".equalsIgnoreCase(arrayOfString[0]))
            {
              paramContext = new StringBuilder();
              paramContext.append(b.b());
              paramContext.append("/");
              paramContext.append(arrayOfString[1]);
              return paramContext.toString();
            }
            paramContext = new StringBuilder();
            paramContext.append(b.a());
            paramContext.append("/");
            paramContext.append(arrayOfString[1]);
            paramUri = paramContext.toString();
            paramContext = paramUri;
            if (a(paramUri)) {
              break label415;
            }
            paramContext = new StringBuilder();
            paramContext.append("/storage/sdcard1/");
            paramContext.append(arrayOfString[1]);
            paramUri = paramContext.toString();
            paramContext = paramUri;
            if (a(paramUri)) {
              break label415;
            }
            paramContext = new StringBuilder();
            paramContext.append(b.b());
            paramContext.append("/");
            paramContext.append(arrayOfString[1]);
            paramUri = paramContext.toString();
            paramContext = paramUri;
            if (a(paramUri)) {
              break label415;
            }
            return null;
          }
          if (b(paramUri))
          {
            paramUri = DocumentsContract.getDocumentId(paramUri);
            return a(paramContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(paramUri).longValue()), null, null);
          }
          if (c(paramUri))
          {
            arrayOfString = DocumentsContract.getDocumentId(paramUri).split(":");
            paramUri = arrayOfString[0];
            if ("image".equals(paramUri))
            {
              paramUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            }
            else if ("video".equals(paramUri))
            {
              paramUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            }
            else
            {
              if (!"audio".equals(paramUri)) {
                break label417;
              }
              paramUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return a(paramContext, paramUri, "_id=?", new String[] { arrayOfString[1] });
          }
          return DocumentsContract.getDocumentId(paramUri);
        }
        if ("content".equalsIgnoreCase(paramUri.getScheme()))
        {
          if (d(paramUri)) {
            return paramUri.getLastPathSegment();
          }
          return a(paramContext, paramUri, null, null);
        }
        if ("file".equalsIgnoreCase(paramUri.getScheme()))
        {
          paramContext = paramUri.getPath();
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
      return null;
      label415:
      return paramContext;
      label417:
      paramUri = null;
    }
  }
  
  public static boolean c()
  {
    String str = Environment.getExternalStorageState();
    return ("mounted".equals(str)) || ("mounted_ro".equals(str));
  }
  
  public static boolean c(Uri paramUri)
  {
    return "com.android.providers.media.documents".equals(paramUri.getAuthority());
  }
  
  public static boolean c(File paramFile)
  {
    return paramFile.delete();
  }
  
  public static boolean c(String paramString)
  {
    paramString = new File(paramString);
    try
    {
      boolean bool = paramString.createNewFile();
      return bool;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static boolean c(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    boolean bool2 = localFile.exists();
    boolean bool1 = false;
    if (!bool2) {
      return false;
    }
    if (localFile.isFile()) {
      return d(paramString1, paramString2);
    }
    if (localFile.isDirectory()) {
      bool1 = a(paramString1, paramString2, true);
    }
    return bool1;
  }
  
  public static String d()
  {
    String str;
    if (c()) {
      str = b();
    } else {
      str = a();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append(b.c);
    return localStringBuilder.toString();
  }
  
  public static String d(Context paramContext, Uri paramUri)
  {
    try
    {
      Object localObject1 = paramContext.getPackageManager().getInstalledPackages(8);
      if (localObject1 != null)
      {
        String str = FileProvider.class.getName();
        localObject1 = ((List)localObject1).iterator();
        label311:
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = ((PackageInfo)((Iterator)localObject1).next()).providers;
          if (localObject2 != null)
          {
            int j = localObject2.length;
            int i = 0;
            for (;;)
            {
              if (i >= j) {
                break label311;
              }
              Object localObject3 = localObject2[i];
              if (paramUri.getAuthority().equals(((ProviderInfo)localObject3).authority))
              {
                if (!((ProviderInfo)localObject3).name.equalsIgnoreCase(str)) {
                  break;
                }
                try
                {
                  localObject2 = FileProvider.class.getDeclaredMethod("getPathStrategy", new Class[] { Context.class, String.class });
                  ((Method)localObject2).setAccessible(true);
                  localObject2 = ((Method)localObject2).invoke(null, new Object[] { paramContext, paramUri.getAuthority() });
                  if (localObject2 == null) {
                    break;
                  }
                  localObject3 = new StringBuilder();
                  ((StringBuilder)localObject3).append(FileProvider.class.getName());
                  ((StringBuilder)localObject3).append("$PathStrategy");
                  localObject3 = Class.forName(((StringBuilder)localObject3).toString()).getDeclaredMethod("getFileForUri", new Class[] { Uri.class });
                  ((Method)localObject3).setAccessible(true);
                  localObject2 = ((Method)localObject3).invoke(localObject2, new Object[] { paramUri });
                  if (!(localObject2 instanceof File)) {
                    break;
                  }
                  localObject2 = ((File)localObject2).getAbsolutePath();
                  return localObject2;
                }
                catch (ClassNotFoundException localClassNotFoundException)
                {
                  localClassNotFoundException.printStackTrace();
                }
                catch (IllegalAccessException localIllegalAccessException)
                {
                  localIllegalAccessException.printStackTrace();
                }
                catch (InvocationTargetException localInvocationTargetException)
                {
                  localInvocationTargetException.printStackTrace();
                }
                catch (NoSuchMethodException localNoSuchMethodException)
                {
                  localNoSuchMethodException.printStackTrace();
                }
                break;
              }
              i += 1;
            }
          }
        }
      }
      return null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean d(Uri paramUri)
  {
    return "com.google.android.apps.photos.content".equals(paramUri.getAuthority());
  }
  
  public static boolean d(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    return a(new File(paramString));
  }
  
  public static boolean d(String paramString1, String paramString2)
  {
    if (!new File(paramString1).exists()) {
      return false;
    }
    Object localObject = paramString1.substring(paramString1.lastIndexOf(File.separator));
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString2);
    localStringBuilder.append((String)localObject);
    localObject = localStringBuilder.toString();
    if (((String)localObject).equals(paramString1)) {
      return false;
    }
    localObject = new File((String)localObject);
    if ((((File)localObject).exists()) && (((File)localObject).isFile())) {
      return false;
    }
    new File(paramString2).mkdirs();
    try
    {
      paramString1 = new FileInputStream(paramString1);
      paramString2 = new FileOutputStream((File)localObject);
      localObject = new byte['Ѐ'];
      for (;;)
      {
        int i = paramString1.read((byte[])localObject);
        if (i == -1) {
          break;
        }
        paramString2.write((byte[])localObject, 0, i);
      }
      paramString1.close();
      paramString2.close();
      return true;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
  
  public static long e(String paramString)
  {
    return a(paramString, false);
  }
  
  public static void e()
  {
    b(d());
    b(a);
  }
  
  public static boolean e(String paramString1, String paramString2)
  {
    if (!c(paramString1, paramString2)) {
      return false;
    }
    return m(paramString1);
  }
  
  public static String f(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      int i = paramString.lastIndexOf('.');
      if ((i > -1) && (i < paramString.length() - 1)) {
        return paramString.substring(i + 1);
      }
    }
    return paramString;
  }
  
  public static String g(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0)) {
      try
      {
        int i = paramString.lastIndexOf('.');
        int j = paramString.lastIndexOf(File.separator);
        if ((i > -1) && (i < paramString.length() - 1)) {
          return paramString.substring(j + 1, i);
        }
        if ((j > -1) && (j < paramString.length()))
        {
          String str = paramString.substring(j + 1, paramString.length());
          return str;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return paramString;
  }
  
  public static String h(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      int i = paramString.lastIndexOf(File.separator);
      if ((i > -1) && (i < paramString.length())) {
        return paramString.substring(0, i);
      }
      return null;
    }
    return null;
  }
  
  public static String i(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      int i = paramString.lastIndexOf(File.separator);
      if ((i > -1) && (i < paramString.length())) {
        return paramString.substring(i + 1, paramString.length());
      }
      return null;
    }
    return null;
  }
  
  public static String j(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      int i = paramString.lastIndexOf('.');
      if ((i > -1) && (i < paramString.length())) {
        return paramString.substring(0, i);
      }
    }
    return paramString;
  }
  
  public static String k(String paramString)
  {
    String str = paramString;
    if (paramString.endsWith(File.separator)) {
      str = paramString.substring(0, paramString.lastIndexOf(File.separator));
    }
    return str.substring(str.lastIndexOf(File.separator) + 1);
  }
  
  public static String l(String paramString)
  {
    if (paramString.endsWith(File.separator)) {
      return paramString.substring(0, paramString.lastIndexOf(File.separator));
    }
    return paramString.substring(0, paramString.lastIndexOf(File.separator));
  }
  
  public static boolean m(String paramString)
  {
    boolean bool = false;
    if (paramString == null) {
      return false;
    }
    paramString = new File(paramString);
    if (!paramString.exists()) {
      return false;
    }
    if (paramString.isDirectory()) {
      return n(paramString.getAbsolutePath());
    }
    if (paramString.isFile()) {
      bool = c(paramString);
    }
    return bool;
  }
  
  public static boolean n(String paramString)
  {
    paramString = new File(paramString);
    boolean bool2 = paramString.isDirectory();
    boolean bool1 = true;
    if (!bool2) {
      return true;
    }
    File[] arrayOfFile = paramString.listFiles();
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      File localFile = arrayOfFile[i];
      if (localFile.isFile()) {
        bool1 = c(localFile);
      } else if (localFile.isDirectory()) {
        bool1 = n(localFile.getAbsolutePath());
      }
      if (!bool1) {
        break;
      }
      i += 1;
    }
    return paramString.delete();
  }
  
  public static int o(String paramString)
  {
    try
    {
      paramString = new RandomAccessFile(paramString, "r");
      byte[] arrayOfByte = new byte[(int)paramString.length()];
      paramString.readFully(arrayOfByte);
      paramString.close();
      int i = Integer.valueOf(new String(arrayOfByte)).intValue();
      return i;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }
  
  public static boolean p(String paramString)
  {
    return (paramString.contains("'")) || (paramString.contains("\"")) || (paramString.contains("/")) || (paramString.contains("\\")) || (paramString.contains(":")) || (paramString.contains("*")) || (paramString.contains("?")) || (paramString.contains("<")) || (paramString.contains(">")) || (paramString.contains("|"));
  }
}
