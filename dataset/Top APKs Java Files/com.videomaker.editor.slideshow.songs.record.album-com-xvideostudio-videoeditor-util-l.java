package com.xvideostudio.videoeditor.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
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
import com.umeng.analytics.MobclickAgent;
import com.xvideostudio.videoeditor.f.a;
import com.xvideostudio.videoeditor.i.c;
import com.xvideostudio.videoeditor.tool.i;
import com.xvideostudio.videoeditor.tool.j;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

public class l
{
  public static final String a = d() + "/data/";
  public static final String b = a + "ins.dat";
  private static String[] c = { "_data", "_data" };
  
  public static double a(long paramLong)
  {
    return w.a(paramLong * 1.0D / 1048576.0D, 2, 4);
  }
  
  public static long a(String paramString, boolean paramBoolean)
  {
    if (paramString != null) {
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
        return -2L;
      }
    }
    return -3L;
  }
  
  public static Bitmap a(Context paramContext, int paramInt)
  {
    return BitmapFactory.decodeResource(paramContext.getResources(), paramInt);
  }
  
  public static Bitmap a(Bitmap paramBitmap1, Bitmap paramBitmap2, boolean paramBoolean)
  {
    if ((paramBitmap1 == null) || (paramBitmap2 == null)) {
      return null;
    }
    if (paramBoolean) {}
    for (Object localObject1 = paramBitmap1;; localObject1 = paramBitmap1.copy((Bitmap.Config)localObject1, true))
    {
      Object localObject2 = new Canvas((Bitmap)localObject1);
      ((Canvas)localObject2).drawBitmap(paramBitmap1, 0.0F, 0.0F, null);
      ((Canvas)localObject2).drawBitmap(paramBitmap2, 0.0F, 0.0F, null);
      ((Canvas)localObject2).save(31);
      ((Canvas)localObject2).restore();
      return localObject1;
      localObject2 = paramBitmap1.getConfig();
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = Bitmap.Config.ARGB_8888;
      }
    }
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
    return File.separator + "udisk";
  }
  
  public static String a(long paramLong1, int paramInt, long paramLong2)
  {
    if (paramLong2 == 1024L) {
      return w.a(paramLong1 * 1.0D / 1024.0D, paramInt, 4) + "KB";
    }
    if (paramLong2 == 1048576L)
    {
      if (paramLong1 >= 1048576L) {
        return w.a(paramLong1 * 1.0D / 1048576.0D, paramInt, 4) + "MB";
      }
      return w.a(paramLong1 * 1.0D / 1024.0D, paramInt, 4) + "KB";
    }
    if (paramLong2 == 1073741824L)
    {
      if (paramLong1 >= 1073741824L) {
        return w.a(paramLong1 * 1.0D / 1.073741824E9D, paramInt, 4) + "GB";
      }
      if (paramLong1 >= 1048576L) {
        return w.a(paramLong1 * 1.0D / 1048576.0D, paramInt, 4) + "MB";
      }
      return w.a(paramLong1 * 1.0D / 1024.0D, paramInt, 4) + "KB";
    }
    if (paramLong2 == 1099511627776L)
    {
      if (paramLong1 >= 1099511627776L) {
        return w.a(paramLong1 * 1.0D / 1.099511627776E12D, paramInt, 4) + "TB";
      }
      if (paramLong1 >= 1073741824L) {
        return w.a(paramLong1 * 1.0D / 1.073741824E9D, paramInt, 4) + "GB";
      }
      if (paramLong1 >= 1048576L) {
        return w.a(paramLong1 * 1.0D / 1048576.0D, paramInt, 4) + "MB";
      }
      return w.a(paramLong1 * 1.0D / 1024.0D, paramInt, 4) + "KB";
    }
    return paramLong1 + "B";
  }
  
  public static String a(long paramLong1, long paramLong2)
  {
    return a(paramLong1, 2, paramLong2);
  }
  
  @SuppressLint({"NewApi"})
  public static String a(Context paramContext, Uri paramUri)
  {
    Object localObject2 = null;
    Object localObject4 = null;
    Object localObject3 = null;
    int j = 0;
    Object localObject1;
    if (paramUri == null)
    {
      localObject1 = localObject3;
      return localObject1;
    }
    if ((Build.VERSION.SDK_INT >= 19) && (DocumentsContract.isDocumentUri(paramContext, paramUri)))
    {
      localObject3 = DocumentsContract.getDocumentId(paramUri);
      localObject1 = localObject3;
      if (localObject3 == null) {
        localObject1 = "";
      }
      localObject3 = ((String)localObject1).split(":");
      if (localObject3.length <= 1) {
        break label440;
      }
      localObject1 = localObject3[1];
    }
    label440:
    for (;;)
    {
      localObject3 = new String[1];
      localObject3[0] = "_data";
      if (paramUri.toString().contains("video")) {}
      int i;
      for (localObject1 = paramContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, (String[])localObject3, "_id=?", new String[] { localObject1 }, null);; localObject1 = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[])localObject3, "_id=?", new String[] { localObject1 }, null))
      {
        i = ((Cursor)localObject1).getColumnIndex(localObject3[0]);
        if (((Cursor)localObject1).moveToFirst()) {
          localObject2 = ((Cursor)localObject1).getString(i);
        }
        if (!((Cursor)localObject1).isClosed()) {
          ((Cursor)localObject1).close();
        }
        localObject1 = localObject2;
        if (localObject2 != null) {
          break;
        }
        return c(paramContext, paramUri);
      }
      if (paramUri.toString().contains("file://"))
      {
        paramUri = paramUri.toString().split("file://")[1];
        paramContext = paramUri;
        if (!a(paramUri)) {}
        try
        {
          paramContext = URLDecoder.decode(paramUri, "UTF-8");
          return paramContext;
        }
        catch (Exception paramContext)
        {
          for (;;)
          {
            paramContext.printStackTrace();
            paramContext = paramUri;
          }
        }
      }
      if (paramUri.toString().contains("flg=")) {}
      for (localObject2 = Uri.parse(paramUri.toString().split("flg=")[0]);; localObject2 = paramUri)
      {
        localObject1 = localObject3;
        if (localObject2 == null) {
          break;
        }
        paramUri = localObject4;
        try
        {
          localObject2 = paramContext.getContentResolver().query((Uri)localObject2, c, null, null, null);
          localObject1 = localObject3;
          if (localObject2 == null) {
            break;
          }
          i = 0;
          for (;;)
          {
            paramUri = localObject4;
            if (i >= c.length) {
              break;
            }
            paramUri = localObject4;
            j = ((Cursor)localObject2).getColumnIndexOrThrow(c[i]);
            i += 1;
          }
          paramUri = localObject4;
          ((Cursor)localObject2).moveToFirst();
          paramUri = localObject4;
          paramContext = ((Cursor)localObject2).getString(j);
          localObject1 = paramContext;
          paramUri = paramContext;
          if (((Cursor)localObject2).isClosed()) {
            break;
          }
          paramUri = paramContext;
          ((Cursor)localObject2).close();
          return paramContext;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return paramUri;
        }
      }
    }
  }
  
  /* Error */
  public static String a(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: invokevirtual 209	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: aload_1
    //   8: iconst_1
    //   9: anewarray 37	java/lang/String
    //   12: dup
    //   13: iconst_0
    //   14: ldc 39
    //   16: aastore
    //   17: aload_2
    //   18: aload_3
    //   19: aconst_null
    //   20: invokevirtual 223	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   23: astore_1
    //   24: aload_1
    //   25: ifnull +39 -> 64
    //   28: aload_1
    //   29: invokeinterface 232 1 0
    //   34: ifeq +30 -> 64
    //   37: aload_1
    //   38: aload_1
    //   39: ldc 39
    //   41: invokeinterface 270 2 0
    //   46: invokeinterface 236 2 0
    //   51: astore_0
    //   52: aload_1
    //   53: ifnull +9 -> 62
    //   56: aload_1
    //   57: invokeinterface 242 1 0
    //   62: aload_0
    //   63: areturn
    //   64: aload_1
    //   65: ifnull +9 -> 74
    //   68: aload_1
    //   69: invokeinterface 242 1 0
    //   74: aconst_null
    //   75: areturn
    //   76: astore_0
    //   77: aload 4
    //   79: astore_1
    //   80: aload_1
    //   81: ifnull +9 -> 90
    //   84: aload_1
    //   85: invokeinterface 242 1 0
    //   90: aload_0
    //   91: athrow
    //   92: astore_0
    //   93: goto -13 -> 80
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	paramContext	Context
    //   0	96	1	paramUri	Uri
    //   0	96	2	paramString	String
    //   0	96	3	paramArrayOfString	String[]
    //   1	77	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	24	76	finally
    //   28	52	92	finally
  }
  
  private static String a(Context paramContext, String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = paramContext.getPackageManager();
    try
    {
      localObject2 = ((PackageManager)localObject2).getApplicationInfo(paramContext.getPackageName(), 128);
      paramContext = localObject1;
      if (localObject2 != null)
      {
        paramContext = localObject1;
        if (((ApplicationInfo)localObject2).metaData != null) {
          paramContext = ((ApplicationInfo)localObject2).metaData.get(paramString);
        }
      }
      return paramContext.toString();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = a(paramContext, paramString1);
    if (paramContext == null) {
      return paramString2;
    }
    return paramContext;
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
    if (localObject == null) {}
    for (;;)
    {
      return;
      int i = 0;
      String str2;
      String str1;
      if (i < localObject.length)
      {
        if (localObject[i].isDirectory()) {
          localLinkedList.add(localObject[i]);
        }
        for (;;)
        {
          i += 1;
          break;
          str2 = localObject[i].getAbsolutePath();
          str1 = localObject[i].getName();
          paramString = str1;
          if (!paramBoolean) {
            paramString = j(str1);
          }
          paramList1.add(str2);
          paramList2.add(paramString);
        }
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
            label161:
            if (i < localObject.length)
            {
              if (!localObject[i].isDirectory()) {
                break label200;
              }
              localLinkedList.add(localObject[i]);
            }
            for (;;)
            {
              i += 1;
              break label161;
              break;
              label200:
              str2 = localObject[i].getAbsolutePath();
              str1 = localObject[i].getName();
              paramString = str1;
              if (!paramBoolean) {
                paramString = j(str1);
              }
              paramList1.add(str2);
              paramList2.add(paramString);
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
  
  public static boolean a(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (e(paramString) >= 2147483648L)
    {
      j.a(2131689594, -1, 1);
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
    boolean bool2 = false;
    if ((paramString == null) || (paramBitmap == null)) {
      return false;
    }
    boolean bool1 = bool2;
    try
    {
      i.b("saveBitmapToSdCardJPG", "filePath:" + paramString);
      bool1 = bool2;
      paramString = new FileOutputStream(paramString);
      bool1 = bool2;
      bool2 = paramBitmap.compress(Bitmap.CompressFormat.JPEG, paramInt, paramString);
      if (paramString != null)
      {
        bool1 = bool2;
        paramString.close();
      }
      bool1 = bool2;
      i.b("saveBitmapToSdCardJPG", "saveRet:" + bool2);
      return bool2;
    }
    catch (Exception paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
    return bool1;
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
    if (paramFile == null) {}
    do
    {
      return false;
      if (paramFile.isFile()) {
        return paramFile.delete();
      }
    } while (!paramFile.isDirectory());
    File[] arrayOfFile = paramFile.listFiles();
    if ((arrayOfFile == null) || (arrayOfFile.length == 0)) {
      return paramFile.delete();
    }
    while (i < arrayOfFile.length)
    {
      a(arrayOfFile[i]);
      i += 1;
    }
    return paramFile.delete();
  }
  
  public static boolean a(String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {
      return false;
    }
    return new File(paramString).exists();
  }
  
  public static boolean a(String paramString, int paramInt)
  {
    try
    {
      paramString = new FileOutputStream(new File(paramString));
      paramString.write((paramInt + "").getBytes());
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
    //   1: ifnull +7 -> 8
    //   4: aload_1
    //   5: ifnonnull +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: new 55	java/io/File
    //   13: dup
    //   14: aload_0
    //   15: invokespecial 58	java/io/File:<init>	(Ljava/lang/String;)V
    //   18: astore 9
    //   20: aload 9
    //   22: invokevirtual 62	java/io/File:exists	()Z
    //   25: ifne +5 -> 30
    //   28: iconst_1
    //   29: ireturn
    //   30: aconst_null
    //   31: astore_0
    //   32: aconst_null
    //   33: astore 8
    //   35: lconst_0
    //   36: lstore 5
    //   38: new 468	java/io/FileInputStream
    //   41: dup
    //   42: aload 9
    //   44: invokespecial 469	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   47: astore 7
    //   49: new 312	java/io/FileOutputStream
    //   52: dup
    //   53: aload_1
    //   54: invokespecial 313	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   57: astore_1
    //   58: ldc_w 328
    //   61: newarray byte
    //   63: astore_0
    //   64: iconst_0
    //   65: istore_3
    //   66: aload 7
    //   68: aload_0
    //   69: invokevirtual 472	java/io/InputStream:read	([B)I
    //   72: istore 4
    //   74: iload 4
    //   76: iconst_m1
    //   77: if_icmpeq +138 -> 215
    //   80: lload 5
    //   82: iload 4
    //   84: i2l
    //   85: ladd
    //   86: lstore 5
    //   88: aconst_null
    //   89: new 13	java/lang/StringBuilder
    //   92: dup
    //   93: invokespecial 16	java/lang/StringBuilder:<init>	()V
    //   96: lload 5
    //   98: invokevirtual 163	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   101: ldc -66
    //   103: invokevirtual 24	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: invokevirtual 29	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   109: invokestatic 416	com/xvideostudio/videoeditor/tool/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   112: aload_1
    //   113: aload_0
    //   114: iconst_0
    //   115: iload 4
    //   117: invokevirtual 473	java/io/FileOutputStream:write	([BII)V
    //   120: ldc2_w 474
    //   123: lload 5
    //   125: lmul
    //   126: aload 9
    //   128: invokevirtual 479	java/io/File:length	()J
    //   131: ldiv
    //   132: l2i
    //   133: istore 4
    //   135: iload_3
    //   136: iload 4
    //   138: if_icmpeq +251 -> 389
    //   141: iload 4
    //   143: istore_3
    //   144: aload_2
    //   145: ifnull +67 -> 212
    //   148: new 481	android/os/Message
    //   151: dup
    //   152: invokespecial 482	android/os/Message:<init>	()V
    //   155: astore 8
    //   157: new 296	android/os/Bundle
    //   160: dup
    //   161: invokespecial 483	android/os/Bundle:<init>	()V
    //   164: astore 10
    //   166: aload 10
    //   168: ldc_w 485
    //   171: lload 5
    //   173: invokevirtual 489	android/os/Bundle:putLong	(Ljava/lang/String;J)V
    //   176: aload 10
    //   178: ldc_w 491
    //   181: aload 9
    //   183: invokevirtual 479	java/io/File:length	()J
    //   186: invokevirtual 489	android/os/Bundle:putLong	(Ljava/lang/String;J)V
    //   189: aload 8
    //   191: iconst_0
    //   192: putfield 494	android/os/Message:what	I
    //   195: aload 8
    //   197: aload 10
    //   199: invokevirtual 498	android/os/Message:setData	(Landroid/os/Bundle;)V
    //   202: aload_2
    //   203: aload 8
    //   205: invokevirtual 504	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   208: pop
    //   209: iload 4
    //   211: istore_3
    //   212: goto -146 -> 66
    //   215: aload 7
    //   217: ifnull +8 -> 225
    //   220: aload 7
    //   222: invokevirtual 505	java/io/InputStream:close	()V
    //   225: aload_1
    //   226: ifnull +11 -> 237
    //   229: aload_1
    //   230: invokevirtual 506	java/io/FileOutputStream:flush	()V
    //   233: aload_1
    //   234: invokevirtual 427	java/io/FileOutputStream:close	()V
    //   237: iconst_1
    //   238: ireturn
    //   239: astore_0
    //   240: aload_0
    //   241: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   244: goto -19 -> 225
    //   247: astore_0
    //   248: aload_0
    //   249: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   252: goto -15 -> 237
    //   255: astore_2
    //   256: aload 8
    //   258: astore_1
    //   259: aconst_null
    //   260: ldc_w 508
    //   263: invokestatic 416	com/xvideostudio/videoeditor/tool/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   266: aload_2
    //   267: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   270: aload_0
    //   271: ifnull +7 -> 278
    //   274: aload_0
    //   275: invokevirtual 505	java/io/InputStream:close	()V
    //   278: aload_1
    //   279: ifnull -271 -> 8
    //   282: aload_1
    //   283: invokevirtual 506	java/io/FileOutputStream:flush	()V
    //   286: aload_1
    //   287: invokevirtual 427	java/io/FileOutputStream:close	()V
    //   290: iconst_0
    //   291: ireturn
    //   292: astore_0
    //   293: aload_0
    //   294: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   297: iconst_0
    //   298: ireturn
    //   299: astore_0
    //   300: aload_0
    //   301: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   304: goto -26 -> 278
    //   307: astore_0
    //   308: aconst_null
    //   309: astore 7
    //   311: aconst_null
    //   312: astore_1
    //   313: aload 7
    //   315: ifnull +8 -> 323
    //   318: aload 7
    //   320: invokevirtual 505	java/io/InputStream:close	()V
    //   323: aload_1
    //   324: ifnull +11 -> 335
    //   327: aload_1
    //   328: invokevirtual 506	java/io/FileOutputStream:flush	()V
    //   331: aload_1
    //   332: invokevirtual 427	java/io/FileOutputStream:close	()V
    //   335: aload_0
    //   336: athrow
    //   337: astore_2
    //   338: aload_2
    //   339: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   342: goto -19 -> 323
    //   345: astore_1
    //   346: aload_1
    //   347: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   350: goto -15 -> 335
    //   353: astore_0
    //   354: aconst_null
    //   355: astore_1
    //   356: goto -43 -> 313
    //   359: astore_0
    //   360: goto -47 -> 313
    //   363: astore_2
    //   364: aload_0
    //   365: astore 7
    //   367: aload_2
    //   368: astore_0
    //   369: goto -56 -> 313
    //   372: astore_2
    //   373: aload 7
    //   375: astore_0
    //   376: aload 8
    //   378: astore_1
    //   379: goto -120 -> 259
    //   382: astore_2
    //   383: aload 7
    //   385: astore_0
    //   386: goto -127 -> 259
    //   389: goto -177 -> 212
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	392	0	paramString1	String
    //   0	392	1	paramString2	String
    //   0	392	2	paramHandler	android.os.Handler
    //   65	147	3	i	int
    //   72	138	4	j	int
    //   36	136	5	l	long
    //   47	337	7	localObject	Object
    //   33	344	8	localMessage	android.os.Message
    //   18	164	9	localFile	File
    //   164	34	10	localBundle	Bundle
    // Exception table:
    //   from	to	target	type
    //   220	225	239	java/lang/Exception
    //   229	237	247	java/lang/Exception
    //   38	49	255	java/lang/Exception
    //   282	290	292	java/lang/Exception
    //   274	278	299	java/lang/Exception
    //   38	49	307	finally
    //   318	323	337	java/lang/Exception
    //   327	335	345	java/lang/Exception
    //   49	58	353	finally
    //   58	64	359	finally
    //   66	74	359	finally
    //   88	135	359	finally
    //   148	209	359	finally
    //   259	270	363	finally
    //   49	58	372	java/lang/Exception
    //   58	64	382	java/lang/Exception
    //   66	74	382	java/lang/Exception
    //   88	135	382	java/lang/Exception
    //   148	209	382	java/lang/Exception
  }
  
  public static boolean a(String paramString1, String paramString2, boolean paramBoolean)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    File localFile = new File(paramString1);
    if (!localFile.exists()) {}
    String str1;
    int j;
    int i;
    label117:
    do
    {
      do
      {
        return bool2;
        String str2 = k(paramString1);
        str1 = paramString2;
        if (paramBoolean) {
          str1 = paramString2 + File.separator + str2;
        }
      } while (str1.equals(paramString1));
      paramString1 = new File(str1);
      if (!paramString1.exists()) {
        paramString1.mkdirs();
      }
      paramString1 = localFile.listFiles();
      if (paramString1.length == 0) {
        return true;
      }
      j = paramString1.length;
      i = 0;
      bool2 = bool1;
    } while (i >= j);
    paramString2 = paramString1[i];
    if (paramString2.isFile()) {
      bool1 = d(paramString2.getAbsolutePath(), str1);
    }
    for (;;)
    {
      bool2 = bool1;
      if (!bool1) {
        break;
      }
      i += 1;
      break label117;
      if (paramString2.isDirectory()) {
        bool1 = a(paramString2.getAbsolutePath(), str1, paramBoolean);
      }
    }
  }
  
  public static long b(File paramFile)
    throws Exception
  {
    long l2;
    if (paramFile.isFile()) {
      l2 = paramFile.length();
    }
    long l1;
    int i;
    do
    {
      return l2;
      l1 = 0L;
      paramFile = paramFile.listFiles();
      i = 0;
      l2 = l1;
    } while (i >= paramFile.length);
    if (paramFile[i].isDirectory()) {}
    for (l1 += b(paramFile[i]);; l1 += paramFile[i].length())
    {
      i += 1;
      break;
    }
  }
  
  public static String b()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public static String b(Context paramContext, Uri paramUri)
  {
    if (paramUri == null) {
      paramContext = null;
    }
    for (;;)
    {
      return paramContext;
      try
      {
        Cursor localCursor = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
        int i = localCursor.getColumnIndexOrThrow("_data");
        localCursor.moveToFirst();
        paramUri = localCursor.getString(i);
        paramContext = paramUri;
        if (!localCursor.isClosed())
        {
          localCursor.close();
          return paramUri;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return null;
  }
  
  public static void b(String paramString1, String paramString2, boolean paramBoolean)
  {
    paramString2 = paramString2 + "\n";
    try
    {
      paramString1 = new File(paramString1);
      if (!paramBoolean) {
        paramString1.createNewFile();
      }
      for (;;)
      {
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(paramString1, "rw");
        localRandomAccessFile.seek(paramString1.length());
        localRandomAccessFile.write(paramString2.getBytes());
        localRandomAccessFile.close();
        return;
        if (!paramString1.exists()) {
          paramString1.createNewFile();
        }
      }
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static boolean b(Bitmap paramBitmap, String paramString, int paramInt)
  {
    boolean bool2 = false;
    if ((paramString == null) || (paramBitmap == null)) {
      return false;
    }
    boolean bool1 = bool2;
    try
    {
      i.b("saveBitmapToSdCardPNG", "filePath:" + paramString);
      bool1 = bool2;
      paramString = new FileOutputStream(paramString);
      bool1 = bool2;
      bool2 = paramBitmap.compress(Bitmap.CompressFormat.PNG, paramInt, paramString);
      if (paramString != null)
      {
        bool1 = bool2;
        paramString.close();
      }
      bool1 = bool2;
      i.b("saveBitmapToSdCardPNG", "saveRet:" + bool2);
      return bool2;
    }
    catch (Exception paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
    return bool1;
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
        paramString.setAttribute("Orientation", paramInt + "");
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
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_0
    //   7: ifnull +7 -> 14
    //   10: aload_1
    //   11: ifnonnull +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: new 55	java/io/File
    //   19: dup
    //   20: aload_0
    //   21: invokespecial 58	java/io/File:<init>	(Ljava/lang/String;)V
    //   24: astore_0
    //   25: aload_0
    //   26: invokevirtual 62	java/io/File:exists	()Z
    //   29: ifeq -15 -> 14
    //   32: new 468	java/io/FileInputStream
    //   35: dup
    //   36: aload_0
    //   37: invokespecial 469	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   40: astore 4
    //   42: new 55	java/io/File
    //   45: dup
    //   46: aload_1
    //   47: invokespecial 58	java/io/File:<init>	(Ljava/lang/String;)V
    //   50: invokevirtual 572	java/io/File:getParentFile	()Ljava/io/File;
    //   53: astore_0
    //   54: aload_0
    //   55: invokevirtual 62	java/io/File:exists	()Z
    //   58: ifne +8 -> 66
    //   61: aload_0
    //   62: invokevirtual 515	java/io/File:mkdirs	()Z
    //   65: pop
    //   66: new 312	java/io/FileOutputStream
    //   69: dup
    //   70: aload_1
    //   71: invokespecial 313	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   74: astore_0
    //   75: ldc_w 328
    //   78: newarray byte
    //   80: astore_1
    //   81: iconst_0
    //   82: istore_2
    //   83: aload 4
    //   85: aload_1
    //   86: invokevirtual 472	java/io/InputStream:read	([B)I
    //   89: istore_3
    //   90: iload_3
    //   91: iconst_m1
    //   92: if_icmpeq +92 -> 184
    //   95: iload_2
    //   96: iload_3
    //   97: iadd
    //   98: istore_2
    //   99: aconst_null
    //   100: new 13	java/lang/StringBuilder
    //   103: dup
    //   104: invokespecial 16	java/lang/StringBuilder:<init>	()V
    //   107: iload_2
    //   108: invokevirtual 458	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   111: ldc -66
    //   113: invokevirtual 24	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 29	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: invokestatic 416	com/xvideostudio/videoeditor/tool/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   122: aload_0
    //   123: aload_1
    //   124: iconst_0
    //   125: iload_3
    //   126: invokevirtual 473	java/io/FileOutputStream:write	([BII)V
    //   129: goto -46 -> 83
    //   132: astore 5
    //   134: aload_0
    //   135: astore_1
    //   136: aload 4
    //   138: astore_0
    //   139: aload 5
    //   141: astore 4
    //   143: aconst_null
    //   144: ldc_w 508
    //   147: invokestatic 416	com/xvideostudio/videoeditor/tool/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   150: aload 4
    //   152: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   155: aload_0
    //   156: ifnull +7 -> 163
    //   159: aload_0
    //   160: invokevirtual 505	java/io/InputStream:close	()V
    //   163: aload_1
    //   164: ifnull -150 -> 14
    //   167: aload_1
    //   168: invokevirtual 506	java/io/FileOutputStream:flush	()V
    //   171: aload_1
    //   172: invokevirtual 427	java/io/FileOutputStream:close	()V
    //   175: iconst_0
    //   176: ireturn
    //   177: astore_0
    //   178: aload_0
    //   179: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   182: iconst_0
    //   183: ireturn
    //   184: aload 4
    //   186: ifnull +8 -> 194
    //   189: aload 4
    //   191: invokevirtual 505	java/io/InputStream:close	()V
    //   194: aload_0
    //   195: ifnull +11 -> 206
    //   198: aload_0
    //   199: invokevirtual 506	java/io/FileOutputStream:flush	()V
    //   202: aload_0
    //   203: invokevirtual 427	java/io/FileOutputStream:close	()V
    //   206: iconst_1
    //   207: ireturn
    //   208: astore_1
    //   209: aload_1
    //   210: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   213: goto -19 -> 194
    //   216: astore_0
    //   217: aload_0
    //   218: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   221: goto -15 -> 206
    //   224: astore_0
    //   225: aload_0
    //   226: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   229: goto -66 -> 163
    //   232: astore_0
    //   233: aconst_null
    //   234: astore 4
    //   236: aload 6
    //   238: astore_1
    //   239: aload 4
    //   241: ifnull +8 -> 249
    //   244: aload 4
    //   246: invokevirtual 505	java/io/InputStream:close	()V
    //   249: aload_1
    //   250: ifnull +11 -> 261
    //   253: aload_1
    //   254: invokevirtual 506	java/io/FileOutputStream:flush	()V
    //   257: aload_1
    //   258: invokevirtual 427	java/io/FileOutputStream:close	()V
    //   261: aload_0
    //   262: athrow
    //   263: astore 4
    //   265: aload 4
    //   267: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   270: goto -21 -> 249
    //   273: astore_1
    //   274: aload_1
    //   275: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   278: goto -17 -> 261
    //   281: astore_0
    //   282: aload 6
    //   284: astore_1
    //   285: goto -46 -> 239
    //   288: astore 5
    //   290: aload_0
    //   291: astore_1
    //   292: aload 5
    //   294: astore_0
    //   295: goto -56 -> 239
    //   298: astore 5
    //   300: aload_0
    //   301: astore 4
    //   303: aload 5
    //   305: astore_0
    //   306: goto -67 -> 239
    //   309: astore 4
    //   311: aconst_null
    //   312: astore_0
    //   313: aload 5
    //   315: astore_1
    //   316: goto -173 -> 143
    //   319: astore_1
    //   320: aload 4
    //   322: astore_0
    //   323: aload_1
    //   324: astore 4
    //   326: aload 5
    //   328: astore_1
    //   329: goto -186 -> 143
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	332	0	paramString1	String
    //   0	332	1	paramString2	String
    //   82	26	2	i	int
    //   89	37	3	j	int
    //   40	205	4	localObject1	Object
    //   263	3	4	localException1	Exception
    //   301	1	4	str1	String
    //   309	12	4	localException2	Exception
    //   324	1	4	str2	String
    //   4	1	5	localObject2	Object
    //   132	8	5	localException3	Exception
    //   288	5	5	localObject3	Object
    //   298	29	5	localObject4	Object
    //   1	282	6	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   75	81	132	java/lang/Exception
    //   83	90	132	java/lang/Exception
    //   99	129	132	java/lang/Exception
    //   167	175	177	java/lang/Exception
    //   189	194	208	java/lang/Exception
    //   198	206	216	java/lang/Exception
    //   159	163	224	java/lang/Exception
    //   32	42	232	finally
    //   244	249	263	java/lang/Exception
    //   253	261	273	java/lang/Exception
    //   42	66	281	finally
    //   66	75	281	finally
    //   75	81	288	finally
    //   83	90	288	finally
    //   99	129	288	finally
    //   143	155	298	finally
    //   32	42	309	java/lang/Exception
    //   42	66	319	java/lang/Exception
    //   66	75	319	java/lang/Exception
  }
  
  @TargetApi(19)
  public static String c(Context paramContext, Uri paramUri)
  {
    if ((paramContext == null) || (paramUri == null))
    {
      paramContext = null;
      return paramContext;
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
            if ("primary".equalsIgnoreCase(arrayOfString[0])) {
              return c.b() + "/" + arrayOfString[1];
            }
            paramUri = c.a() + "/" + arrayOfString[1];
            paramContext = paramUri;
            if (a(paramUri)) {
              break;
            }
            paramUri = "/storage/sdcard1/" + arrayOfString[1];
            paramContext = paramUri;
            if (a(paramUri)) {
              break;
            }
            paramUri = c.b() + "/" + arrayOfString[1];
            paramContext = paramUri;
            if (a(paramUri)) {
              break;
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
              return a(paramContext, paramUri, "_id=?", new String[] { arrayOfString[1] });
            }
            if ("video".equals(paramUri))
            {
              paramUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
              continue;
            }
            if (!"audio".equals(paramUri)) {
              break label382;
            }
            paramUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            continue;
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
      label382:
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
    if (!localFile.exists()) {}
    do
    {
      return false;
      if (localFile.isFile()) {
        return d(paramString1, paramString2);
      }
    } while (!localFile.isDirectory());
    return a(paramString1, paramString2, true);
  }
  
  public static String d()
  {
    if (c()) {}
    for (String str = b();; str = a()) {
      return str + c.c;
    }
  }
  
  /* Error */
  public static String d(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 279	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: bipush 8
    //   6: invokevirtual 664	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   9: astore 5
    //   11: aload 5
    //   13: ifnull +258 -> 271
    //   16: ldc_w 666
    //   19: invokevirtual 669	java/lang/Class:getName	()Ljava/lang/String;
    //   22: astore 4
    //   24: aload 5
    //   26: invokeinterface 673 1 0
    //   31: astore 5
    //   33: aload 5
    //   35: invokeinterface 678 1 0
    //   40: ifeq +231 -> 271
    //   43: aload 5
    //   45: invokeinterface 681 1 0
    //   50: checkcast 683	android/content/pm/PackageInfo
    //   53: getfield 687	android/content/pm/PackageInfo:providers	[Landroid/content/pm/ProviderInfo;
    //   56: astore 6
    //   58: aload 6
    //   60: ifnull -27 -> 33
    //   63: aload 6
    //   65: arraylength
    //   66: istore_3
    //   67: iconst_0
    //   68: istore_2
    //   69: iload_2
    //   70: iload_3
    //   71: if_icmpge -38 -> 33
    //   74: aload 6
    //   76: iload_2
    //   77: aaload
    //   78: astore 7
    //   80: aload_1
    //   81: invokevirtual 444	android/net/Uri:getAuthority	()Ljava/lang/String;
    //   84: aload 7
    //   86: getfield 692	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   89: invokevirtual 447	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   92: ifeq +211 -> 303
    //   95: aload 7
    //   97: getfield 695	android/content/pm/ProviderInfo:name	Ljava/lang/String;
    //   100: aload 4
    //   102: invokevirtual 581	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   105: ifeq -72 -> 33
    //   108: ldc_w 666
    //   111: ldc_w 697
    //   114: iconst_2
    //   115: anewarray 668	java/lang/Class
    //   118: dup
    //   119: iconst_0
    //   120: ldc 77
    //   122: aastore
    //   123: dup
    //   124: iconst_1
    //   125: ldc 37
    //   127: aastore
    //   128: invokevirtual 701	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   131: astore 6
    //   133: aload 6
    //   135: iconst_1
    //   136: invokevirtual 707	java/lang/reflect/Method:setAccessible	(Z)V
    //   139: aload 6
    //   141: aconst_null
    //   142: iconst_2
    //   143: anewarray 4	java/lang/Object
    //   146: dup
    //   147: iconst_0
    //   148: aload_0
    //   149: aastore
    //   150: dup
    //   151: iconst_1
    //   152: aload_1
    //   153: invokevirtual 444	android/net/Uri:getAuthority	()Ljava/lang/String;
    //   156: aastore
    //   157: invokevirtual 711	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   160: astore 6
    //   162: aload 6
    //   164: ifnull -131 -> 33
    //   167: new 13	java/lang/StringBuilder
    //   170: dup
    //   171: invokespecial 16	java/lang/StringBuilder:<init>	()V
    //   174: ldc_w 666
    //   177: invokevirtual 669	java/lang/Class:getName	()Ljava/lang/String;
    //   180: invokevirtual 24	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: ldc_w 713
    //   186: invokevirtual 24	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: invokevirtual 29	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: invokestatic 717	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   195: ldc_w 719
    //   198: iconst_1
    //   199: anewarray 668	java/lang/Class
    //   202: dup
    //   203: iconst_0
    //   204: ldc -58
    //   206: aastore
    //   207: invokevirtual 701	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   210: astore 7
    //   212: aload 7
    //   214: iconst_1
    //   215: invokevirtual 707	java/lang/reflect/Method:setAccessible	(Z)V
    //   218: aload 7
    //   220: aload 6
    //   222: iconst_1
    //   223: anewarray 4	java/lang/Object
    //   226: dup
    //   227: iconst_0
    //   228: aload_1
    //   229: aastore
    //   230: invokevirtual 711	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   233: astore 6
    //   235: aload 6
    //   237: instanceof 55
    //   240: ifeq -207 -> 33
    //   243: aload 6
    //   245: checkcast 55	java/io/File
    //   248: invokevirtual 364	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   251: astore 6
    //   253: aload 6
    //   255: areturn
    //   256: astore 6
    //   258: aload 6
    //   260: invokevirtual 720	java/lang/NoSuchMethodException:printStackTrace	()V
    //   263: goto -230 -> 33
    //   266: astore_0
    //   267: aload_0
    //   268: invokevirtual 261	java/lang/Exception:printStackTrace	()V
    //   271: aconst_null
    //   272: areturn
    //   273: astore 6
    //   275: aload 6
    //   277: invokevirtual 721	java/lang/reflect/InvocationTargetException:printStackTrace	()V
    //   280: goto -247 -> 33
    //   283: astore 6
    //   285: aload 6
    //   287: invokevirtual 722	java/lang/IllegalAccessException:printStackTrace	()V
    //   290: goto -257 -> 33
    //   293: astore 6
    //   295: aload 6
    //   297: invokevirtual 723	java/lang/ClassNotFoundException:printStackTrace	()V
    //   300: goto -267 -> 33
    //   303: iload_2
    //   304: iconst_1
    //   305: iadd
    //   306: istore_2
    //   307: goto -238 -> 69
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	310	0	paramContext	Context
    //   0	310	1	paramUri	Uri
    //   68	239	2	i	int
    //   66	6	3	j	int
    //   22	79	4	str	String
    //   9	35	5	localObject1	Object
    //   56	198	6	localObject2	Object
    //   256	3	6	localNoSuchMethodException	NoSuchMethodException
    //   273	3	6	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   283	3	6	localIllegalAccessException	IllegalAccessException
    //   293	3	6	localClassNotFoundException	ClassNotFoundException
    //   78	141	7	localMethod	java.lang.reflect.Method
    // Exception table:
    //   from	to	target	type
    //   108	162	256	java/lang/NoSuchMethodException
    //   167	253	256	java/lang/NoSuchMethodException
    //   0	11	266	java/lang/Exception
    //   16	33	266	java/lang/Exception
    //   33	58	266	java/lang/Exception
    //   63	67	266	java/lang/Exception
    //   80	108	266	java/lang/Exception
    //   108	162	266	java/lang/Exception
    //   167	253	266	java/lang/Exception
    //   258	263	266	java/lang/Exception
    //   275	280	266	java/lang/Exception
    //   285	290	266	java/lang/Exception
    //   295	300	266	java/lang/Exception
    //   108	162	273	java/lang/reflect/InvocationTargetException
    //   167	253	273	java/lang/reflect/InvocationTargetException
    //   108	162	283	java/lang/IllegalAccessException
    //   167	253	283	java/lang/IllegalAccessException
    //   108	162	293	java/lang/ClassNotFoundException
    //   167	253	293	java/lang/ClassNotFoundException
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
    if (!new File(paramString1).exists()) {}
    Object localObject;
    do
    {
      do
      {
        return false;
        localObject = paramString1.substring(paramString1.lastIndexOf(File.separator));
        localObject = paramString2 + (String)localObject;
      } while (((String)localObject).equals(paramString1));
      localObject = new File((String)localObject);
    } while ((((File)localObject).exists()) && (((File)localObject).isFile()));
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
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      return false;
    }
    paramString2.close();
    return true;
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
    if (!c(paramString1, paramString2)) {}
    while (!m(paramString1)) {
      return false;
    }
    return true;
  }
  
  public static String f(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.length() > 0)
      {
        int i = paramString.lastIndexOf('.');
        str = paramString;
        if (i > -1)
        {
          str = paramString;
          if (i < paramString.length() - 1) {
            str = paramString.substring(i + 1);
          }
        }
      }
    }
    return str;
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
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      localObject1 = localObject2;
      if (paramString.length() > 0)
      {
        int i = paramString.lastIndexOf(File.separator);
        localObject1 = localObject2;
        if (i > -1)
        {
          localObject1 = localObject2;
          if (i < paramString.length()) {
            localObject1 = paramString.substring(0, i);
          }
        }
      }
    }
    return localObject1;
  }
  
  public static String i(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      localObject1 = localObject2;
      if (paramString.length() > 0)
      {
        int i = paramString.lastIndexOf(File.separator);
        localObject1 = localObject2;
        if (i > -1)
        {
          localObject1 = localObject2;
          if (i < paramString.length()) {
            localObject1 = paramString.substring(i + 1, paramString.length());
          }
        }
      }
    }
    return localObject1;
  }
  
  public static String j(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.length() > 0)
      {
        int i = paramString.lastIndexOf('.');
        str = paramString;
        if (i > -1)
        {
          str = paramString;
          if (i < paramString.length()) {
            str = paramString.substring(0, i);
          }
        }
      }
    }
    return str;
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
    if (paramString == null) {}
    do
    {
      do
      {
        return false;
        paramString = new File(paramString);
      } while (!paramString.exists());
      if (paramString.isDirectory()) {
        return n(paramString.getAbsolutePath());
      }
    } while (!paramString.isFile());
    return c(paramString);
  }
  
  public static boolean n(String paramString)
  {
    boolean bool = true;
    paramString = new File(paramString);
    if (!paramString.isDirectory()) {
      return true;
    }
    File[] arrayOfFile = paramString.listFiles();
    int j = arrayOfFile.length;
    int i = 0;
    for (;;)
    {
      File localFile;
      if (i < j)
      {
        localFile = arrayOfFile[i];
        if (!localFile.isFile()) {
          break label66;
        }
        bool = c(localFile);
      }
      while (!bool)
      {
        return paramString.delete();
        label66:
        if (localFile.isDirectory()) {
          bool = n(localFile.getAbsolutePath());
        }
      }
      i += 1;
    }
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
