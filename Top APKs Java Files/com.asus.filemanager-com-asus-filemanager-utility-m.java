package com.asus.filemanager.utility;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.drm.DrmManagerClient;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.storage.StorageManager;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Files;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.asus.filemanager.activity.AnalyzerDupFilesActivity;
import com.asus.filemanager.activity.FileListFragment;
import com.asus.filemanager.activity.FileManagerActivity;
import com.asus.filemanager.d.d;
import com.asus.filemanager.dialog.bq;
import com.asus.filemanager.dialog.di;
import com.asus.filemanager.e.b;
import com.asus.filemanager.functionaldirectory.FunctionalDirectoryUtility;
import com.asus.filemanager.functionaldirectory.FunctionalDirectoryUtility.DirectoryType;
import com.asus.filemanager.functionaldirectory.c;
import com.asus.filemanager.provider.OpenFileProvider;
import com.asus.filemanager.provider.i;
import com.asus.remote.utility.RemoteVFile;
import com.asus.service.cloudstorage.common.MsgObj;
import com.asus.service.cloudstorage.common.MsgObj.FileObj;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class m
{
  public static String a;
  public static String b;
  public static String c;
  public static String d;
  public static boolean e = Environment.isExternalStorageEmulated();
  static List<String> f = Arrays.asList(new String[] { "ASUS_A001", "ASUS_Z017D_1", "ASUS_Z012D", "Z016", "Z016_1" });
  private static final String g = m.class.getSimpleName();
  private static final boolean h = l.a;
  private static int i = 0;
  
  static
  {
    c = "currentFileInfo";
    d = "attachOpFileInfo";
    e = true;
    if (Build.VERSION.SDK_INT > 16)
    {
      a = Environment.getExternalStorageDirectory().toString();
      b = bz.a().toString();
    }
  }
  
  public m() {}
  
  public static int a(Context paramContext)
  {
    int j = paramContext.getSharedPreferences(d, 0).getInt("file_sortType", 5);
    i.a(j);
    return j;
  }
  
  public static long a(VFile paramVFile, boolean paramBoolean)
  {
    long l1;
    if (paramVFile.isFile()) {
      if (paramBoolean) {
        l1 = 0L;
      }
    }
    long l2;
    do
    {
      return l1;
      return paramVFile.length();
      l2 = paramVFile.length();
      l1 = l2;
    } while (paramVFile.g_() == null);
    paramVFile = paramVFile.g_();
    int k = paramVFile.length;
    int j = 0;
    for (;;)
    {
      l1 = l2;
      if (j >= k) {
        break;
      }
      l1 = a(paramVFile[j], paramBoolean);
      j += 1;
      l2 = l1 + l2;
    }
  }
  
  public static long a(VFile[] paramArrayOfVFile)
  {
    long l = 0L;
    int j = 0;
    while ((paramArrayOfVFile != null) && (j < paramArrayOfVFile.length) && (paramArrayOfVFile[j] != null))
    {
      l += a(paramArrayOfVFile[j], false);
      j += 1;
    }
    return l;
  }
  
  private static Uri a(int paramInt, String paramString)
  {
    if ((bz.a(paramInt)) || ("audio/3gpp".equals(paramString)))
    {
      if (h) {
        Log.d(g, "isAudioFileType : true");
      }
      return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    }
    if (bz.b(paramInt))
    {
      if (h) {
        Log.d(g, "isImageFileType : true");
      }
      return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }
    if (bz.c(paramInt))
    {
      if (h) {
        Log.d(g, "isVideoFileType : true");
      }
      return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    }
    if (h) {
      Log.d(g, "isFileType : true");
    }
    return MediaStore.Files.getContentUri("external");
  }
  
  public static Uri a(Context paramContext, VFile paramVFile, String paramString, boolean paramBoolean)
  {
    bool = FunctionalDirectoryUtility.a().a(FunctionalDirectoryUtility.DirectoryType.HiddenZone, paramVFile);
    Object localObject3 = null;
    localObject2 = null;
    localObject1 = localObject2;
    if (!bool)
    {
      localObject1 = localObject2;
      localObject2 = localObject3;
    }
    try
    {
      if (d(paramVFile))
      {
        localObject2 = localObject3;
        paramContext = a(paramContext, paramVFile, paramString, paramBoolean);
        localObject1 = paramContext;
        localObject2 = paramContext;
        if (h)
        {
          localObject2 = paramContext;
          Log.i(g, " *** uri *** content : " + paramContext);
          localObject1 = paramContext;
        }
      }
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        localObject1 = localObject2;
        continue;
        paramString = OpenFileProvider.a(new File(paramVFile.getAbsolutePath()), bool);
        paramContext = paramString;
        if (h)
        {
          Log.i(g, " *** uri *** N : " + paramString);
          paramContext = paramString;
          continue;
          paramContext = OpenFileProvider.a(paramVFile, bool);
          Log.i(g, " *** uri *** open uri for file: " + paramContext);
        }
      }
    }
    paramContext = (Context)localObject1;
    if (Build.VERSION.SDK_INT >= 24)
    {
      if (localObject1 == null) {
        break label313;
      }
      paramContext = (Context)localObject1;
      if (!((Uri)localObject1).getScheme().isEmpty())
      {
        paramContext = (Context)localObject1;
        if (!((Uri)localObject1).getScheme().equalsIgnoreCase("content"))
        {
          paramString = OpenFileProvider.a(new File(paramVFile.getAbsolutePath()), bool);
          paramContext = paramString;
          if (h)
          {
            Log.i(g, " *** uri *** N : " + paramString);
            paramContext = paramString;
          }
        }
      }
    }
    paramString = paramContext;
    if (paramContext == null)
    {
      if ((bool) || (Build.VERSION.SDK_INT >= 23)) {
        break label369;
      }
      paramContext = Uri.fromFile(paramVFile);
      Log.i(g, " *** uri *** < M: " + paramContext);
      paramString = paramContext;
      if (h)
      {
        Log.d(g, "u fromFile");
        paramString = paramContext;
      }
    }
    if (h) {
      Log.i(g, " *** uri *** : " + paramString);
    }
    return paramString;
  }
  
  /* Error */
  private static Uri a(Context paramContext, File paramFile, Uri paramUri, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aload_2
    //   4: getstatic 158	android/provider/MediaStore$Audio$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   7: invokevirtual 264	android/net/Uri:equals	(Ljava/lang/Object;)Z
    //   10: ifeq +233 -> 243
    //   13: iload_3
    //   14: ifeq +229 -> 243
    //   17: iconst_1
    //   18: istore 4
    //   20: aload_1
    //   21: invokestatic 267	com/asus/filemanager/utility/m:b	(Ljava/io/File;)Ljava/lang/String;
    //   24: astore 5
    //   26: aload_2
    //   27: invokevirtual 271	android/net/Uri:buildUpon	()Landroid/net/Uri$Builder;
    //   30: ldc_w 273
    //   33: ldc_w 275
    //   36: invokevirtual 281	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   39: invokevirtual 285	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   42: astore 8
    //   44: iload 4
    //   46: ifeq +221 -> 267
    //   49: iconst_5
    //   50: anewarray 76	java/lang/String
    //   53: astore 6
    //   55: aload 6
    //   57: iconst_0
    //   58: ldc_w 287
    //   61: aastore
    //   62: aload 6
    //   64: iconst_1
    //   65: ldc_w 289
    //   68: aastore
    //   69: aload 6
    //   71: iconst_2
    //   72: ldc_w 291
    //   75: aastore
    //   76: aload 6
    //   78: iconst_3
    //   79: ldc_w 293
    //   82: aastore
    //   83: aload 6
    //   85: iconst_4
    //   86: ldc_w 295
    //   89: aastore
    //   90: aload_0
    //   91: invokevirtual 299	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   94: aload 8
    //   96: aload 6
    //   98: ldc_w 301
    //   101: iconst_1
    //   102: anewarray 76	java/lang/String
    //   105: dup
    //   106: iconst_0
    //   107: aload 5
    //   109: aastore
    //   110: aconst_null
    //   111: invokevirtual 307	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   114: astore 5
    //   116: aload 7
    //   118: astore_0
    //   119: aload 5
    //   121: ifnull +120 -> 241
    //   124: aload 5
    //   126: invokeinterface 313 1 0
    //   131: ifle +182 -> 313
    //   134: aload 5
    //   136: invokeinterface 316 1 0
    //   141: ifeq +172 -> 313
    //   144: iload 4
    //   146: ifeq +137 -> 283
    //   149: aload 5
    //   151: aload 5
    //   153: ldc_w 291
    //   156: invokeinterface 320 2 0
    //   161: invokeinterface 323 2 0
    //   166: ifne +43 -> 209
    //   169: aload 5
    //   171: aload 5
    //   173: ldc_w 289
    //   176: invokeinterface 320 2 0
    //   181: invokeinterface 323 2 0
    //   186: ifne +23 -> 209
    //   189: aload 5
    //   191: aload 5
    //   193: ldc_w 293
    //   196: invokeinterface 320 2 0
    //   201: invokeinterface 323 2 0
    //   206: ifeq +77 -> 283
    //   209: aload 5
    //   211: aload 5
    //   213: ldc_w 295
    //   216: invokeinterface 320 2 0
    //   221: invokeinterface 323 2 0
    //   226: ifne +57 -> 283
    //   229: aload_1
    //   230: invokestatic 251	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
    //   233: astore_0
    //   234: aload 5
    //   236: invokeinterface 326 1 0
    //   241: aload_0
    //   242: areturn
    //   243: iconst_0
    //   244: istore 4
    //   246: goto -226 -> 20
    //   249: astore 5
    //   251: getstatic 28	com/asus/filemanager/utility/m:g	Ljava/lang/String;
    //   254: ldc_w 328
    //   257: invokestatic 331	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   260: pop
    //   261: aconst_null
    //   262: astore 5
    //   264: goto -238 -> 26
    //   267: iconst_1
    //   268: anewarray 76	java/lang/String
    //   271: astore 6
    //   273: aload 6
    //   275: iconst_0
    //   276: ldc_w 287
    //   279: aastore
    //   280: goto -190 -> 90
    //   283: aload 5
    //   285: iconst_0
    //   286: invokeinterface 335 2 0
    //   291: astore_0
    //   292: aload_2
    //   293: invokevirtual 271	android/net/Uri:buildUpon	()Landroid/net/Uri$Builder;
    //   296: aload_0
    //   297: invokevirtual 339	android/net/Uri$Builder:appendPath	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   300: invokevirtual 285	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   303: astore_0
    //   304: aload 5
    //   306: invokeinterface 326 1 0
    //   311: aload_0
    //   312: areturn
    //   313: aload 5
    //   315: invokeinterface 326 1 0
    //   320: aconst_null
    //   321: areturn
    //   322: astore_0
    //   323: aload 5
    //   325: invokeinterface 326 1 0
    //   330: aload_0
    //   331: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	332	0	paramContext	Context
    //   0	332	1	paramFile	File
    //   0	332	2	paramUri	Uri
    //   0	332	3	paramBoolean	boolean
    //   18	227	4	j	int
    //   24	211	5	localObject1	Object
    //   249	1	5	localIOException	IOException
    //   262	62	5	localObject2	Object
    //   53	221	6	arrayOfString	String[]
    //   1	116	7	localObject3	Object
    //   42	53	8	localUri	Uri
    // Exception table:
    //   from	to	target	type
    //   20	26	249	java/io/IOException
    //   124	144	322	finally
    //   149	209	322	finally
    //   209	234	322	finally
    //   283	304	322	finally
  }
  
  private static Uri a(Context paramContext, File paramFile, String paramString, boolean paramBoolean)
  {
    Object localObject = null;
    if (paramString != null)
    {
      paramString = a(bz.b(paramString), paramString);
      if (!MediaStore.Files.getContentUri("external").toString().equals(paramString.toString())) {
        break label78;
      }
    }
    label78:
    for (paramString = Uri.fromFile(paramFile);; paramString = a(paramContext, paramFile, paramString, paramBoolean))
    {
      localObject = paramString;
      if (paramString == null)
      {
        if (h) {
          Log.d(g, "someting erro and try to query MediaStore.Files");
        }
        localObject = a(paramContext, paramFile, MediaStore.Files.getContentUri("external"), paramBoolean);
      }
      return localObject;
    }
  }
  
  public static VFile a(Activity paramActivity, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramActivity != null)
    {
      paramActivity = paramActivity.getSharedPreferences(paramString, 0);
      int j = paramActivity.getInt("file_type", -1);
      paramActivity = paramActivity.getString("file_path", "");
      localObject1 = localObject2;
      if (j == 0)
      {
        localObject1 = localObject2;
        if (paramActivity != null)
        {
          localObject1 = localObject2;
          if (paramActivity.length() > 0) {
            localObject1 = new LocalVFile(paramActivity);
          }
        }
      }
    }
    return localObject1;
  }
  
  public static n a(com.asus.filemanager.d.a paramA)
  {
    n localN1 = new n();
    if ((paramA == null) || (i > 32)) {
      return localN1;
    }
    if (paramA.c())
    {
      i += 1;
      localN1.a = paramA.e();
      localN1.b = paramA.f();
      localN1.c = 0;
      localN1.d = 1;
      localN1.e = 0.0D;
      paramA = paramA.h();
      if (paramA != null)
      {
        int j = 0;
        if ((j < paramA.length) && (paramA[j] != null))
        {
          n localN2 = a(paramA[j]);
          if ((localN1.a) && (localN2.a))
          {
            bool = true;
            label125:
            localN1.a = bool;
            if ((!localN1.b) || (!localN2.b)) {
              break label221;
            }
          }
          label221:
          for (boolean bool = true;; bool = false)
          {
            localN1.b = bool;
            localN1.c += localN2.c;
            localN1.d += localN2.d;
            double d1 = localN1.e;
            localN2.e += d1;
            j += 1;
            break;
            bool = false;
            break label125;
          }
        }
      }
      i -= 1;
    }
    for (;;)
    {
      return localN1;
      localN1.a = paramA.e();
      localN1.b = paramA.f();
      localN1.c = 1;
      localN1.d = 0;
      localN1.e = paramA.d();
    }
  }
  
  public static String a(ContentResolver paramContentResolver, Uri paramUri)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    paramUri = paramContentResolver.query(paramUri, new String[] { "_data" }, null, null, null);
    paramContentResolver = localObject2;
    if (paramUri != null) {}
    try
    {
      int j = paramUri.getColumnIndexOrThrow("_data");
      paramContentResolver = localObject1;
      if (paramUri.moveToFirst()) {
        paramContentResolver = paramUri.getString(j);
      }
      return paramContentResolver;
    }
    finally
    {
      paramUri.close();
    }
  }
  
  public static String a(Context paramContext, double paramDouble, int paramInt)
  {
    return Formatter.formatFileSize(paramContext, paramDouble);
  }
  
  private static String a(Context paramContext, String paramString, File paramFile)
  {
    localObject = paramString;
    for (;;)
    {
      try
      {
        paramFile = b(paramFile);
        localObject = paramString;
        Uri localUri = MediaStore.Files.getContentUri("external").buildUpon().appendQueryParameter("limit", "1").build();
        localObject = paramString;
        paramFile = paramContext.getContentResolver().query(localUri, new String[] { "mime_type" }, "_data=?", new String[] { paramFile }, null);
        paramContext = paramString;
        if (paramFile != null) {
          paramContext = paramString;
        }
      }
      catch (Exception paramContext)
      {
        Log.e(g, paramContext.toString());
        paramContext = (Context)localObject;
        continue;
      }
      try
      {
        if (paramFile.getCount() > 0)
        {
          paramContext = paramString;
          if (paramFile.moveToFirst()) {
            paramContext = paramFile.getString(paramFile.getColumnIndex("mime_type"));
          }
        }
        localObject = paramContext;
        paramFile.close();
        Log.d(g, "the vedio type is : " + paramContext);
        return paramContext;
      }
      finally
      {
        localObject = paramString;
        paramFile.close();
        localObject = paramString;
      }
    }
  }
  
  private static String a(Context paramContext, VFile[] paramArrayOfVFile)
  {
    int j = 0;
    Object localObject1 = "*/*";
    Object localObject2 = localObject1;
    Object localObject3;
    if (j < paramArrayOfVFile.length)
    {
      localObject3 = bz.a(paramArrayOfVFile[j].getName());
      if (!"video/mp4".equals(localObject3))
      {
        localObject2 = localObject3;
        if (!"video/3gpp".equals(localObject3)) {}
      }
      else
      {
        localObject2 = a(paramContext, (String)localObject3, paramArrayOfVFile[0]);
      }
      localObject3 = localObject2;
      if (localObject2 == null) {
        localObject3 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramArrayOfVFile[j].h_().toLowerCase());
      }
      if (localObject3 != null) {
        break label136;
      }
    }
    label136:
    label197:
    for (localObject2 = "*/*";; localObject2 = "*/*")
    {
      if (h) {
        Log.d(g, "return result : " + (String)localObject2);
      }
      return localObject2;
      if (j == 0) {}
      for (;;)
      {
        j += 1;
        localObject1 = localObject3;
        break;
        localObject1 = ((String)localObject1).substring(0, ((String)localObject1).indexOf("/"));
        if (!((String)localObject3).startsWith((String)localObject1)) {
          break label197;
        }
        localObject3 = (String)localObject1 + "/*";
      }
    }
  }
  
  public static String a(File paramFile)
  {
    try
    {
      String str = b(paramFile);
      return str;
    }
    catch (IOException localIOException) {}
    return paramFile.getAbsolutePath();
  }
  
  public static String a(String paramString)
  {
    String str = paramString;
    int j;
    if (e)
    {
      str = paramString;
      if (b != null)
      {
        str = paramString;
        if (paramString.startsWith(b))
        {
          j = b.length();
          if (paramString.length() != j) {
            break label49;
          }
          str = a;
        }
      }
    }
    return str;
    label49:
    return a + paramString.substring(j);
  }
  
  public static void a(Activity paramActivity, int paramInt)
  {
    paramActivity = paramActivity.getSharedPreferences(d, 0).edit();
    paramActivity.putInt("file_sortType", paramInt);
    paramActivity.commit();
    i.a(paramInt);
  }
  
  public static void a(Activity paramActivity, VFile paramVFile, String paramString)
  {
    if (paramVFile != null)
    {
      paramActivity = paramActivity.getSharedPreferences(paramString, 0).edit();
      if (paramVFile.f_() != 0) {
        break label59;
      }
      paramActivity.putString("file_path", paramVFile.getAbsolutePath());
    }
    for (;;)
    {
      paramActivity.putInt("file_type", paramVFile.f_());
      paramActivity.commit();
      return;
      label59:
      paramActivity.putString("file_path", "");
    }
  }
  
  public static void a(Activity paramActivity, VFile paramVFile, boolean paramBoolean1, boolean paramBoolean2)
  {
    a(paramActivity, paramVFile, paramBoolean1, paramBoolean2, true, false);
  }
  
  public static void a(Activity paramActivity, VFile paramVFile, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    a(paramActivity, paramVFile, paramBoolean1, paramBoolean2, paramBoolean3, false);
  }
  
  public static void a(Activity paramActivity, VFile paramVFile, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    if ((paramVFile == null) || (paramActivity == null)) {
      return;
    }
    if (!paramVFile.exists())
    {
      bn.a(paramActivity, 2131231259);
      return;
    }
    Object localObject1;
    Object localObject4;
    Object localObject5;
    if (!paramBoolean2)
    {
      localObject1 = paramVFile.getName();
      localObject4 = paramVFile.getPath();
      if (localObject1 != null)
      {
        localObject5 = paramActivity.getContentResolver();
        long l = System.currentTimeMillis();
        if (com.asus.filemanager.provider.q.b((ContentResolver)localObject5, (String)localObject1, (String)localObject4, l) == 0)
        {
          com.asus.filemanager.provider.q.a((ContentResolver)localObject5, (String)localObject1, (String)localObject4, l);
          if (com.asus.filemanager.provider.q.a((ContentResolver)localObject5) > 100L) {
            com.asus.filemanager.provider.q.b((ContentResolver)localObject5);
          }
        }
      }
    }
    if ((paramActivity instanceof FileManagerActivity)) {}
    for (boolean bool = ((FileManagerActivity)paramActivity).G();; bool = paramBoolean1)
    {
      String str;
      Object localObject2;
      label528:
      Intent localIntent;
      if (!bool)
      {
        str = paramVFile.h_().toLowerCase();
        if (!FunctionalDirectoryUtility.a().a(FunctionalDirectoryUtility.DirectoryType.HiddenZone, paramVFile))
        {
          if ("zip".equals(str))
          {
            localObject1 = new di(paramVFile, paramVFile.f(), 0L, paramActivity.getString(2131231779), null);
            if ((paramActivity instanceof FileManagerActivity)) {
              ((FileManagerActivity)paramActivity).a(15, localObject1);
            }
          }
          else if ("rar".equals(str))
          {
            localObject1 = new di(paramVFile, paramVFile.f(), 0L, paramActivity.getString(2131231779), null);
            if ((paramActivity instanceof FileManagerActivity))
            {
              ((FileManagerActivity)paramActivity).a(28, localObject1);
              return;
            }
          }
          if ((str != null) && (str.indexOf("sn") != -1)) {
            try
            {
              localObject1 = new Intent("android.intent.action.VIEW");
              ((Intent)localObject1).setDataAndType(Uri.fromFile(paramVFile), "*.sn*");
              paramActivity.startActivity((Intent)localObject1);
              return;
            }
            catch (Exception localException1)
            {
              Log.w(g, "sne file : " + localException1.getMessage() + " can't be handled");
            }
          }
        }
        if (a(paramVFile))
        {
          localObject2 = new DrmManagerClient(paramActivity).getOriginalMimeType(paramVFile.getAbsolutePath());
          Log.i(g, "mediaFile is a drm file, and original mime type = " + (String)localObject2);
          localObject4 = localObject2;
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {}
        }
        for (localObject4 = bz.a(paramVFile.getName());; localObject4 = bz.a(paramVFile.getName()))
        {
          if (!"video/mp4".equals(localObject4))
          {
            localObject2 = localObject4;
            if (!"video/3gpp".equals(localObject4)) {}
          }
          else
          {
            localObject2 = a(paramActivity.getApplicationContext(), (String)localObject4, paramVFile);
          }
          localObject5 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
          if ((localObject2 != null) || (localObject5 != null)) {
            break label528;
          }
          if (!(paramActivity instanceof FileManagerActivity)) {
            break;
          }
          ((FileManagerActivity)paramActivity).a(25, paramVFile);
          return;
        }
        if ((paramActivity instanceof AnalyzerDupFilesActivity))
        {
          bq.a(paramVFile).show(paramActivity.getFragmentManager(), "OpenTypeDialogFragment");
          return;
        }
        bn.a(paramActivity, 2131231259);
        return;
        localObject4 = a(paramActivity.getApplicationContext(), paramVFile, (String)localObject2, true);
        localIntent = new Intent("android.intent.action.VIEW");
        if (!paramVFile.getParent().equals(paramActivity.getExternalCacheDir() + "/.pfile")) {
          break label1751;
        }
      }
      label1751:
      for (int m = 1;; m = 0)
      {
        localIntent.setFlags(3);
        Object localObject6;
        if (localObject2 != null) {
          localObject6 = localObject2;
        }
        for (;;)
        {
          int j;
          try
          {
            if ((((String)localObject2).startsWith("image")) && (!paramBoolean2) && (paramBoolean3))
            {
              localObject6 = localObject2;
              if (!((String)localObject2).endsWith("gif"))
              {
                if (!paramBoolean4)
                {
                  localObject6 = localObject2;
                  if (Build.VERSION.SDK_INT < 23) {
                    continue;
                  }
                }
                localObject6 = localObject2;
                Uri localUri = Uri.fromFile(paramVFile);
                localObject4 = localUri;
              }
            }
          }
          catch (Exception localException2)
          {
            int k;
            di localDi;
            localObject3 = localObject4;
            localObject4 = localDi;
            continue;
            Object localObject7;
            continue;
          }
          try
          {
            localIntent.setClassName("com.asus.filemanager", "com.asus.filemanager.activity.ViewPagerActivity");
            localIntent.putExtra("IS_SHOW_SINGLE_FILE", paramBoolean4);
            localObject6 = localObject4;
            localObject4 = localObject2;
            localObject2 = localObject6;
          }
          catch (Exception localException4)
          {
            localObject7 = localObject3;
            localObject3 = localObject4;
            localObject4 = localObject7;
            continue;
            continue;
            paramVFile = (VFile)localObject5;
            continue;
          }
          try
          {
            Log.i(g, "mediaFile_mime open file *** u: " + ((Uri)localObject2).getScheme() + "://" + ((Uri)localObject2).getHost() + " *** mediaFile_mime:" + (String)localObject4);
            localIntent.setDataAndType((Uri)localObject2, (String)localObject4);
            if (m != 0)
            {
              paramActivity.startActivityForResult(localIntent, m);
              j = 1;
              k = j;
              if (j == 0)
              {
                k = j;
                if (localObject5 == null) {}
              }
            }
          }
          catch (Exception localException3) {}
          try
          {
            if (((String)localObject5).startsWith("video"))
            {
              Log.d(g, "is video file: " + (String)localObject5);
              localObject5 = "video/*";
              localObject4 = localObject5;
            }
          }
          catch (Exception paramVFile)
          {
            localObject4 = localObject5;
          }
          try
          {
            localIntent.putExtra("android.intent.extra.TITLE", paramVFile.getName());
            paramVFile = (VFile)localObject5;
            localObject4 = paramVFile;
            Log.i(g, "mimeUtils_mime open file *** u: " + ((Uri)localObject2).getScheme() + "://" + ((Uri)localObject2).getHost() + " *** mimeUtils_mime:" + paramVFile);
            localObject4 = paramVFile;
            localIntent.setDataAndType((Uri)localObject2, paramVFile);
            if (m == 0) {
              continue;
            }
            localObject4 = paramVFile;
            paramActivity.startActivityForResult(localIntent, m);
            k = 1;
            if (k != 0) {
              break;
            }
            if ((paramActivity instanceof FileManagerActivity)) {
              ((FileManagerActivity)paramActivity).a(true);
            }
            bn.a(paramActivity, 2131231259);
            return;
          }
          catch (Exception paramVFile)
          {
            continue;
          }
          localObject6 = localObject2;
          if (((String)localObject2).startsWith("video"))
          {
            localObject6 = localObject2;
            Log.d(g, "is video file: " + (String)localObject2);
            localObject2 = "video/*";
            localObject6 = localObject2;
            localIntent.putExtra("android.intent.extra.TITLE", paramVFile.getName());
            localObject6 = localObject2;
            localIntent.addFlags(524288);
            localObject6 = "video/*";
            localObject2 = localObject4;
            localObject4 = localObject6;
          }
          else
          {
            localObject6 = localObject2;
            localIntent.addFlags(524288);
            localObject6 = localObject2;
            localObject2 = localObject4;
            localObject4 = localObject6;
            continue;
            paramActivity.startActivity(localIntent);
            continue;
            if ("epub".equals(str))
            {
              localDi = new di(paramVFile, paramVFile.f(), 0L, paramActivity.getString(2131231779), null);
              if ((paramActivity instanceof FileManagerActivity))
              {
                ((FileManagerActivity)paramActivity).a(15, localDi);
                return;
              }
            }
            Log.w(g, "mediaFile_mime : " + (String)localObject4 + " can't be handled");
            j = 0;
            continue;
            if ((((String)localObject5).equals("application/vnd.android.package-archive")) && (((Build.VERSION.SDK_INT >= 23) && (Build.VERSION.SDK_INT < 24)) || ((paramVFile instanceof c)))) {
              if (!b(paramVFile).startsWith(Environment.getExternalStorageDirectory().getCanonicalPath()))
              {
                localObject2 = new VFile(paramActivity.getApplicationContext().getExternalCacheDir(), paramVFile.getName());
                if (!((VFile)localObject2).exists())
                {
                  new g(paramActivity, paramVFile, (VFile)localObject2, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4).execute(new VFile[0]);
                  return;
                  Log.w(g, "mimeUtils_mime : " + (String)localObject4 + " can't be handled");
                  k = 0;
                  continue;
                }
                paramVFile = (VFile)localObject2;
                localObject2 = paramVFile;
                if ((paramVFile instanceof c)) {
                  localObject2 = ((c)paramVFile).a();
                }
                localObject2 = Uri.fromFile((File)localObject2);
                paramVFile = (VFile)localObject5;
                continue;
                localObject4 = paramVFile;
                paramActivity.startActivity(localIntent);
                continue;
                localObject5 = paramVFile.h_().toLowerCase();
                localObject4 = a(paramActivity.getApplicationContext(), paramVFile, bz.a(paramVFile.getName()), false);
                localObject2 = localObject4;
                if (localObject5 != null)
                {
                  localObject2 = localObject4;
                  if (((String)localObject5).indexOf("sn") != -1) {
                    localObject2 = Uri.fromFile(paramVFile);
                  }
                }
                Log.i(g, "FileProvider open file *** u: " + ((Uri)localObject2).getScheme() + "://" + ((Uri)localObject2).getHost() + " *** extName:" + (String)localObject5);
                localObject4 = new Intent();
                ((Intent)localObject4).setData((Uri)localObject2);
                ((Intent)localObject4).setFlags(3);
                if ((paramActivity.getIntent().getBooleanExtra("return-data", false)) && (paramActivity.getIntent().getType().contains("image")))
                {
                  localObject2 = s.a(paramVFile.getAbsolutePath(), 96, 96);
                  if (localObject2 != null) {
                    ((Intent)localObject4).putExtra("data", (Parcelable)localObject2);
                  }
                }
                paramVFile = paramVFile.i();
                if ((paramVFile == null) || (!paramVFile.exists()))
                {
                  paramVFile = new LocalVFile(FileListFragment.c);
                  if ((paramActivity.getExternalCacheDir() != null) && (!paramVFile.getAbsolutePath().startsWith(paramActivity.getExternalCacheDir().getAbsolutePath()))) {
                    a(paramActivity, paramVFile, d);
                  }
                  paramActivity.setResult(-1, (Intent)localObject4);
                  paramActivity.finish();
                  return;
                }
                paramVFile = new LocalVFile(paramVFile);
                continue;
              }
            }
            Object localObject3 = localObject4;
            j = 0;
          }
        }
      }
    }
  }
  
  public static void a(Activity paramActivity, RemoteVFile paramRemoteVFile, int paramInt)
  {
    com.asus.remote.utility.q.a(paramActivity).a(paramRemoteVFile.w(), paramRemoteVFile, null, paramRemoteVFile.J(), 26, "");
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2, int paramInt, String paramString3)
  {
    if ((paramString1 != null) && (paramString1.length() > 0))
    {
      localObject = paramString1.substring(paramString1.lastIndexOf(".") + 1, paramString1.length());
      if (localObject != null)
      {
        localObject = ((String)localObject).toLowerCase();
        str1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension((String)localObject);
        if (str1 != null)
        {
          localObject = new Intent();
          ((Intent)localObject).setAction("android.intent.action.VIEW");
          localBundle = new Bundle();
          localBundle.putInt("cloudtype_key", paramInt);
          ((Intent)localObject).putExtra("MUSIC_FILE_NAME_KEY", paramString1);
          localBundle.putString("cloud_account_key", paramString3);
          ((Intent)localObject).putExtra("params_key", localBundle);
          if ((!str1.startsWith("audio/")) && (!str1.equals("application/x-flac"))) {}
        }
      }
    }
    while (paramActivity == null)
    {
      Object localObject;
      Bundle localBundle;
      for (;;)
      {
        try
        {
          String str1;
          j = paramActivity.getPackageManager().getPackageInfo("com.asus.music", 0).versionCode;
          if (j >= 14062700) {
            ((Intent)localObject).setAction("asus.intent.action.FM_AUDIO_PREVIEW");
          }
          ((Intent)localObject).setDataAndType(Uri.parse(paramString2), "audio/*");
          if (paramActivity == null) {
            break;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          try
          {
            Log.d(g, "" + localObject);
            paramActivity.startActivity((Intent)localObject);
            return;
          }
          catch (ActivityNotFoundException paramString1)
          {
            int j;
            bn.a(paramActivity, 2131231259);
            return;
          }
          localNameNotFoundException = localNameNotFoundException;
          localNameNotFoundException.printStackTrace();
          j = 14062700;
          continue;
        }
        if (localNameNotFoundException.startsWith("video/"))
        {
          ((Intent)localObject).setDataAndType(Uri.parse(paramString2), "video/*");
          ((Intent)localObject).putExtra("android.intent.extra.TITLE", paramString1);
        }
      }
      String str2 = bz.a(paramString1);
      if (str2 != null)
      {
        localObject = new Intent();
        ((Intent)localObject).setAction("android.intent.action.VIEW");
        localBundle = new Bundle();
        localBundle.putInt("cloudtype_key", paramInt);
        ((Intent)localObject).putExtra("MUSIC_FILE_NAME_KEY", paramString1);
        localBundle.putString("cloud_account_key", paramString3);
        ((Intent)localObject).putExtra("params_key", localBundle);
        if ((str2.startsWith("audio/")) || (str2.equals("application/x-flac"))) {}
        for (;;)
        {
          try
          {
            paramInt = paramActivity.getPackageManager().getPackageInfo("com.asus.music", 0).versionCode;
            if (paramInt >= 14062700) {
              ((Intent)localObject).setAction("asus.intent.action.FM_AUDIO_PREVIEW");
            }
            ((Intent)localObject).setDataAndType(Uri.parse(paramString2), "audio/*");
            if (paramActivity == null) {
              break;
            }
            try
            {
              Log.d(g, "" + localObject);
              paramActivity.startActivity((Intent)localObject);
              return;
            }
            catch (ActivityNotFoundException paramString1)
            {
              bn.a(paramActivity, 2131231259);
              return;
            }
          }
          catch (PackageManager.NameNotFoundException paramString1)
          {
            paramString1.printStackTrace();
            paramInt = 14062700;
            continue;
          }
          if (str2.startsWith("video/"))
          {
            ((Intent)localObject).setDataAndType(Uri.parse(paramString2), "video/*");
            ((Intent)localObject).putExtra("android.intent.extra.TITLE", paramString1);
          }
        }
      }
    }
    bn.a(paramActivity, 2131231259);
  }
  
  public static void a(Activity paramActivity, VFile[] paramArrayOfVFile)
  {
    Intent localIntent = new Intent();
    ArrayList localArrayList = new ArrayList();
    if ((paramArrayOfVFile != null) && (paramArrayOfVFile.length > 0))
    {
      int k = paramArrayOfVFile.length;
      int j = 0;
      while (j < k)
      {
        VFile localVFile = paramArrayOfVFile[j];
        String str2 = bz.a(localVFile.getName());
        String str1;
        if (!"video/mp4".equals(str2))
        {
          str1 = str2;
          if (!"video/3gpp".equals(str2)) {}
        }
        else
        {
          str1 = a(paramActivity.getApplicationContext(), str2, localVFile);
        }
        localArrayList.add(a(paramActivity.getApplicationContext(), localVFile, str1, true));
        j += 1;
      }
    }
    localIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", localArrayList);
    paramActivity.setResult(-1, localIntent);
    paramActivity.finish();
  }
  
  public static void a(Activity paramActivity, VFile[] paramArrayOfVFile, boolean paramBoolean)
  {
    Object localObject3;
    Object localObject2;
    Object localObject1;
    if (paramArrayOfVFile.length == 1)
    {
      localObject3 = new Intent("android.intent.action.SEND");
      ((Intent)localObject3).setFlags(3);
      localObject2 = bz.a(paramArrayOfVFile[0].getName());
      if (("video/mp4".equals(localObject2)) || ("video/3gpp".equals(localObject2)))
      {
        localObject1 = a(paramActivity.getApplicationContext(), (String)localObject2, paramArrayOfVFile[0]);
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramArrayOfVFile[0].h_().toLowerCase());
        }
        localObject1 = a(paramActivity.getApplicationContext(), paramArrayOfVFile[0], (String)localObject2, false);
        Log.d("Jack", "*******************u = *************" + localObject1);
        ((Intent)localObject3).putExtra("android.intent.extra.STREAM", (Parcelable)localObject1);
        if (localObject2 == null) {
          break label280;
        }
        if (!((String)localObject2).equals("text/plain")) {
          break label218;
        }
        ((Intent)localObject3).setType((String)localObject2);
      }
    }
    label176:
    for (paramArrayOfVFile = (VFile[])localObject3;; paramArrayOfVFile = (VFile[])localObject1)
    {
      try
      {
        paramActivity.startActivity(Intent.createChooser(paramArrayOfVFile, paramActivity.getString(2131230805)));
        return;
      }
      catch (Exception paramActivity)
      {
        label218:
        int j;
        label280:
        int k;
        Log.d(g, "=share File fail==" + paramActivity);
      }
      localObject1 = localObject2;
      if (!"application/epub+zip".equals(localObject2)) {
        break;
      }
      localObject1 = "application/zip";
      break;
      j = ((String)localObject2).indexOf("/");
      if (j > 0)
      {
        paramArrayOfVFile = ((String)localObject2).substring(0, j);
        ((Intent)localObject3).setType(paramArrayOfVFile + "/*");
        break label176;
      }
      ((Intent)localObject3).setType("*/*");
      break label176;
      if ("mts".equals(paramArrayOfVFile[0].h_().toLowerCase()))
      {
        ((Intent)localObject3).setType("video/*");
        break label176;
      }
      ((Intent)localObject3).setType("*/*");
      break label176;
      localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
      ((Intent)localObject1).setFlags(3);
      localObject2 = a(paramActivity.getApplicationContext(), paramArrayOfVFile);
      ((Intent)localObject1).setType((String)localObject2);
      localObject3 = new ArrayList();
      k = paramArrayOfVFile.length;
      j = 0;
      while (j < k)
      {
        VFile localVFile = paramArrayOfVFile[j];
        ((ArrayList)localObject3).add(a(paramActivity.getApplicationContext(), localVFile, (String)localObject2, false));
        j += 1;
      }
      ((Intent)localObject1).putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)localObject3);
    }
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int j = 1;; j = 0) {
      try
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("TIP_STATUS", Integer.valueOf(j));
        Uri localUri = Uri.parse("content://com.asus.vzwhelp/tipSettings/com.asus.filemanager");
        paramContext.getContentResolver().update(localUri, localContentValues, null, null);
        return;
      }
      catch (IllegalArgumentException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static void a(MsgObj paramMsgObj1, MsgObj paramMsgObj2)
  {
    if (paramMsgObj1 != null)
    {
      paramMsgObj1 = paramMsgObj1.b().c();
      paramMsgObj2 = paramMsgObj2.e();
      int k = paramMsgObj2.length;
      int j = 0;
      if (j < k)
      {
        Object localObject = paramMsgObj2[j].a();
        localObject = paramMsgObj1 + File.separator + (String)localObject;
        Log.d(g, "fullPath:" + (String)localObject);
        localObject = new LocalVFile((String)localObject);
        if ((localObject != null) && (((VFile)localObject).exists()))
        {
          if (((VFile)localObject).isDirectory()) {
            break label135;
          }
          i.c((VFile)localObject, true);
        }
        for (;;)
        {
          j += 1;
          break;
          label135:
          i.a((VFile)localObject, true);
        }
      }
    }
  }
  
  public static void a(Long paramLong, File paramFile)
  {
    for (paramFile = paramFile.getParentFile(); !paramFile.exists(); paramFile = paramFile.getParentFile()) {}
    if (paramLong.longValue() > paramFile.getUsableSpace()) {
      throw new u("Usable space: " + paramFile.getUsableSpace() + ", but need: " + paramLong);
    }
  }
  
  public static boolean a()
  {
    boolean bool = false;
    if ("mounted".equals(Environment.getExternalStorageState())) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean a(Context paramContext, File paramFile)
    throws IOException
  {
    paramContext = bz.a((StorageManager)paramContext.getSystemService("storage"));
    int k = paramContext.length;
    int j = 0;
    while (j < k)
    {
      String str = bz.b(paramContext[j]);
      if (b(paramFile).startsWith(str)) {
        return true;
      }
      j += 1;
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if (((PackageInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean a(Intent paramIntent)
  {
    return paramIntent.getBooleanExtra("intent.key.lowstorage", false);
  }
  
  public static boolean a(VFile paramVFile)
  {
    paramVFile = paramVFile.h_().toLowerCase();
    return ("dcf".compareTo(paramVFile) == 0) || ("fl".compareTo(paramVFile) == 0);
  }
  
  public static int b(Context paramContext, String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      String str = paramString.substring(paramString.lastIndexOf(".") + 1, paramString.length());
      if (str != null)
      {
        str = str.toLowerCase();
        str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
        if (str != null) {
          if ((!str.startsWith("audio/")) || (!a(paramContext, "com.asus.music"))) {}
        }
      }
      do
      {
        return 1;
        if (str.startsWith("video/")) {
          return 2;
        }
        paramString = bz.a(paramString);
        if (paramString == null) {
          break;
        }
      } while ((paramString.startsWith("audio/")) && (a(paramContext, "com.asus.music")));
      if (paramString.startsWith("video/")) {
        return 2;
      }
    }
    return -1;
  }
  
  public static n b(VFile paramVFile)
  {
    n localN = new n();
    if ((paramVFile == null) || (i > 32)) {
      return localN;
    }
    boolean bool = d.a().b(paramVFile.getAbsolutePath());
    Object localObject = null;
    if (bool)
    {
      com.asus.filemanager.d.a localA = d.a().d(paramVFile.getAbsolutePath());
      localObject = localA;
      if (localA != null)
      {
        localObject = localA;
        if (!paramVFile.k()) {
          return a(localA);
        }
      }
    }
    if (paramVFile.isDirectory())
    {
      i += 1;
      int j;
      if ((bool) && (localObject != null))
      {
        localN.a = ((com.asus.filemanager.d.a)localObject).e();
        localN.b = ((com.asus.filemanager.d.a)localObject).f();
        localN.c = 0;
        localN.d = 1;
        localN.e = paramVFile.length();
        paramVFile = paramVFile.g_();
        if (paramVFile == null) {
          break label322;
        }
        j = 0;
        label160:
        if ((j >= paramVFile.length) || (paramVFile[j] == null)) {
          break label322;
        }
        localObject = b(paramVFile[j]);
        if ((!localN.a) || (!((n)localObject).a)) {
          break label310;
        }
        bool = true;
        label199:
        localN.a = bool;
        if ((!localN.b) || (!((n)localObject).b)) {
          break label316;
        }
      }
      label310:
      label316:
      for (bool = true;; bool = false)
      {
        localN.b = bool;
        localN.c += ((n)localObject).c;
        localN.d += ((n)localObject).d;
        double d1 = localN.e;
        localN.e = (((n)localObject).e + d1);
        j += 1;
        break label160;
        localN.a = paramVFile.canRead();
        localN.b = paramVFile.canWrite();
        break;
        bool = false;
        break label199;
      }
      label322:
      i -= 1;
      return localN;
    }
    if ((bool) && (localObject != null)) {
      localN.a = ((com.asus.filemanager.d.a)localObject).e();
    }
    for (localN.b = ((com.asus.filemanager.d.a)localObject).f();; localN.b = paramVFile.canWrite())
    {
      localN.c = 1;
      localN.d = 0;
      localN.e = paramVFile.length();
      break;
      localN.a = paramVFile.canRead();
    }
  }
  
  public static String b(ContentResolver paramContentResolver, Uri paramUri)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    paramUri = paramContentResolver.query(paramUri, new String[] { "_display_name" }, null, null, null);
    paramContentResolver = localObject2;
    if (paramUri != null) {}
    try
    {
      int j = paramUri.getColumnIndexOrThrow("_display_name");
      paramContentResolver = localObject1;
      if (paramUri.moveToFirst()) {
        paramContentResolver = paramUri.getString(j);
      }
      return paramContentResolver;
    }
    finally
    {
      paramUri.close();
    }
  }
  
  public static String b(File paramFile)
    throws IOException
  {
    if (Build.VERSION.SDK_INT > 16) {
      return c(paramFile);
    }
    return paramFile.getCanonicalPath();
  }
  
  public static String b(String paramString)
  {
    String str;
    if (paramString.startsWith("/sdcard")) {
      str = paramString.replaceFirst("/sdcard", Environment.getExternalStorageDirectory().toString());
    }
    do
    {
      do
      {
        return str;
        str = paramString;
      } while (!com.asus.filemanager.e.a.u);
      str = paramString;
    } while (!paramString.startsWith("/Removable"));
    return paramString.replaceFirst("/Removable", "/storage");
  }
  
  public static void b()
  {
    try
    {
      if (f.contains(com.asus.filemanager.e.a.y)) {
        Runtime.getRuntime().exec("sync");
      }
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static boolean b(Context paramContext)
  {
    return paramContext.getSharedPreferences("MyPrefsFile", 0).getBoolean("mShowHidden", false);
  }
  
  public static VFile[] b(VFile[] paramArrayOfVFile)
  {
    if ((paramArrayOfVFile == null) || (paramArrayOfVFile.length == 0)) {
      return paramArrayOfVFile;
    }
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    while ((j < paramArrayOfVFile.length) && (paramArrayOfVFile[j] != null))
    {
      if (!paramArrayOfVFile[j].isHidden()) {
        localArrayList.add(paramArrayOfVFile[j]);
      }
      j += 1;
    }
    return (VFile[])localArrayList.toArray(new VFile[localArrayList.size()]);
  }
  
  public static int c(Context paramContext, String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      paramContext = paramString.substring(paramString.lastIndexOf(".") + 1, paramString.length());
      if (paramContext != null)
      {
        paramContext = paramContext.toLowerCase();
        paramContext = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramContext);
        if (paramContext != null) {
          if (!paramContext.startsWith("audio/")) {}
        }
      }
      do
      {
        return 1;
        if (paramContext.startsWith("video/")) {
          return 2;
        }
        paramContext = bz.a(paramString);
        if (paramContext == null) {
          break;
        }
      } while (paramContext.startsWith("audio/"));
      if (paramContext.startsWith("video/")) {
        return 2;
      }
    }
    return -1;
  }
  
  public static String c(File paramFile)
    throws IOException
  {
    return a(paramFile.getCanonicalPath());
  }
  
  public static String c(String paramString)
  {
    String str;
    if (paramString.startsWith(Environment.getExternalStorageDirectory().toString())) {
      str = paramString.replaceFirst(Environment.getExternalStorageDirectory().toString(), "/sdcard");
    }
    do
    {
      do
      {
        return str;
        str = paramString;
      } while (!com.asus.filemanager.e.a.t);
      if (paramString.startsWith("/storage/MicroSD")) {
        return paramString.replaceFirst("/storage/MicroSD", "/Removable/MicroSD");
      }
      if (paramString.startsWith("/storage/sdcard1")) {
        return paramString.replaceFirst("/storage/sdcard1", "/Removable/MicroSD");
      }
      if ((paramString.startsWith("/storage/USBdisk1")) || (paramString.startsWith("/storage/USBdisk2")) || (paramString.startsWith("/storage/USBdisk3")) || (paramString.startsWith("/storage/USBdisk4"))) {
        break;
      }
      str = paramString;
    } while (!paramString.startsWith("/storage/USBdisk5"));
    return paramString.replaceFirst("/storage", "/Removable");
  }
  
  public static void c(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences(d, 0).edit();
    paramContext.putBoolean("first_startup", false);
    paramContext.commit();
  }
  
  public static boolean d(Context paramContext)
  {
    return paramContext.getSharedPreferences(d, 0).getBoolean("first_startup", true);
  }
  
  public static boolean d(File paramFile)
    throws IOException
  {
    return b.b(b(paramFile));
  }
  
  public static boolean e(Context paramContext)
  {
    if (!com.asus.filemanager.e.a.x) {
      return false;
    }
    Uri localUri = Uri.parse("content://com.asus.vzwhelp/tipSettings/com.asus.filemanager");
    paramContext = paramContext.getContentResolver().query(localUri, null, null, null, null);
    boolean bool;
    if (paramContext != null)
    {
      paramContext.moveToFirst();
      if (paramContext.getInt(1) == 1)
      {
        bool = true;
        paramContext.close();
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      break;
      bool = false;
    }
  }
  
  public static boolean e(File paramFile)
  {
    if (paramFile.isDirectory()) {
      return f(paramFile);
    }
    return g(paramFile);
  }
  
  public static void f(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences(d, 0).edit();
    paramContext.putBoolean("first_sdpermission", false);
    paramContext.commit();
  }
  
  public static boolean f(File paramFile)
  {
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile != null)
    {
      int k = arrayOfFile.length;
      int j = 0;
      while (j < k)
      {
        if (!e(arrayOfFile[j])) {
          return false;
        }
        j += 1;
      }
    }
    return g(paramFile);
  }
  
  public static boolean g(Context paramContext)
  {
    return true;
  }
  
  public static boolean g(File paramFile)
  {
    return paramFile.delete();
  }
}
