package com.apptentive.android.sdk.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.apptentive.android.sdk.Log;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public class Util
{
  public static final String PSEUDO_ISO8601_DATE_FORMAT = "yyyy-MM-dd HH:mm:ssZ";
  public static final String PSEUDO_ISO8601_DATE_FORMAT_MILLIS = "yyyy-MM-dd HH:mm:ss.SSSZ";
  
  public Util() {}
  
  public static void calculateListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int i = 0;
    int j = 0;
    while (i < localListAdapter.getCount())
    {
      localObject = localListAdapter.getView(i, null, paramListView);
      ((View)localObject).measure(0, 0);
      j += ((View)localObject).getMeasuredHeight();
      i += 1;
    }
    Object localObject = paramListView.getLayoutParams();
    paramListView.getDividerHeight();
    localListAdapter.getCount();
    i = ((ViewGroup.LayoutParams)localObject).height;
  }
  
  public static boolean canLaunchIntent(Context paramContext, Intent paramIntent)
  {
    return paramIntent.resolveActivity(paramContext.getPackageManager()) != null;
  }
  
  public static String classToString(Object paramObject)
  {
    if (paramObject == null) {
      return "null";
    }
    return String.format("%s(%s)", new Object[] { paramObject.getClass().getSimpleName(), paramObject });
  }
  
  /* Error */
  public static int copyFile(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 98	java/io/File
    //   6: dup
    //   7: aload_0
    //   8: invokespecial 101	java/io/File:<init>	(Ljava/lang/String;)V
    //   11: invokevirtual 105	java/io/File:exists	()Z
    //   14: ifeq +124 -> 138
    //   17: new 107	java/io/FileInputStream
    //   20: dup
    //   21: aload_0
    //   22: invokespecial 108	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   25: astore_0
    //   26: new 110	java/io/FileOutputStream
    //   29: dup
    //   30: aload_1
    //   31: invokespecial 111	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   34: astore_1
    //   35: sipush 1444
    //   38: newarray byte
    //   40: astore 4
    //   42: iconst_0
    //   43: istore_2
    //   44: aload_0
    //   45: aload 4
    //   47: invokevirtual 117	java/io/InputStream:read	([B)I
    //   50: istore_3
    //   51: iload_3
    //   52: iconst_m1
    //   53: if_icmpeq +30 -> 83
    //   56: iload_2
    //   57: iload_3
    //   58: iadd
    //   59: istore_2
    //   60: aload_1
    //   61: aload 4
    //   63: iconst_0
    //   64: iload_3
    //   65: invokevirtual 121	java/io/FileOutputStream:write	([BII)V
    //   68: goto -24 -> 44
    //   71: astore 4
    //   73: aload_0
    //   74: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   77: aload_1
    //   78: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   81: iconst_0
    //   82: ireturn
    //   83: aload_0
    //   84: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   87: aload_1
    //   88: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   91: iload_2
    //   92: ireturn
    //   93: astore 4
    //   95: aconst_null
    //   96: astore_1
    //   97: aconst_null
    //   98: astore_0
    //   99: aload_0
    //   100: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   103: aload_1
    //   104: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   107: aload 4
    //   109: athrow
    //   110: astore 4
    //   112: aconst_null
    //   113: astore_1
    //   114: goto -15 -> 99
    //   117: astore 4
    //   119: goto -20 -> 99
    //   122: astore_0
    //   123: aconst_null
    //   124: astore_0
    //   125: aload 4
    //   127: astore_1
    //   128: goto -55 -> 73
    //   131: astore_1
    //   132: aload 4
    //   134: astore_1
    //   135: goto -62 -> 73
    //   138: aconst_null
    //   139: astore_0
    //   140: iconst_0
    //   141: istore_2
    //   142: aconst_null
    //   143: astore_1
    //   144: goto -61 -> 83
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	147	0	paramString1	String
    //   0	147	1	paramString2	String
    //   43	99	2	i	int
    //   50	15	3	j	int
    //   1	61	4	arrayOfByte	byte[]
    //   71	1	4	localException	Exception
    //   93	15	4	localObject1	Object
    //   110	1	4	localObject2	Object
    //   117	16	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   35	42	71	java/lang/Exception
    //   44	51	71	java/lang/Exception
    //   60	68	71	java/lang/Exception
    //   3	26	93	finally
    //   26	35	110	finally
    //   35	42	117	finally
    //   44	51	117	finally
    //   60	68	117	finally
    //   3	26	122	java/lang/Exception
    //   26	35	131	java/lang/Exception
  }
  
  /* Error */
  public static com.apptentive.android.sdk.model.StoredFile createLocalStoredFile(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 135	android/webkit/URLUtil:isContentUrl	(Ljava/lang/String;)Z
    //   4: ifeq +37 -> 41
    //   7: aload_0
    //   8: ifnull +33 -> 41
    //   11: aload_1
    //   12: invokestatic 141	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   15: astore 4
    //   17: aload_0
    //   18: invokevirtual 145	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   21: aload 4
    //   23: invokevirtual 151	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   26: astore_0
    //   27: aload_0
    //   28: aload_1
    //   29: aload_2
    //   30: aload_3
    //   31: invokestatic 154	com/apptentive/android/sdk/util/Util:createLocalStoredFile	(Ljava/io/InputStream;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/apptentive/android/sdk/model/StoredFile;
    //   34: astore_1
    //   35: aload_0
    //   36: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   39: aload_1
    //   40: areturn
    //   41: new 107	java/io/FileInputStream
    //   44: dup
    //   45: new 98	java/io/File
    //   48: dup
    //   49: aload_1
    //   50: invokespecial 101	java/io/File:<init>	(Ljava/lang/String;)V
    //   53: invokespecial 157	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   56: astore_0
    //   57: goto -30 -> 27
    //   60: astore_0
    //   61: aconst_null
    //   62: astore_0
    //   63: aload_0
    //   64: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   67: aconst_null
    //   68: areturn
    //   69: astore_0
    //   70: aconst_null
    //   71: astore_2
    //   72: aload_0
    //   73: astore_1
    //   74: aload_2
    //   75: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   78: aload_1
    //   79: athrow
    //   80: astore_1
    //   81: aload_0
    //   82: astore_2
    //   83: goto -9 -> 74
    //   86: astore_1
    //   87: goto -24 -> 63
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	paramContext	Context
    //   0	90	1	paramString1	String
    //   0	90	2	paramString2	String
    //   0	90	3	paramString3	String
    //   15	7	4	localUri	Uri
    // Exception table:
    //   from	to	target	type
    //   0	7	60	java/io/FileNotFoundException
    //   11	27	60	java/io/FileNotFoundException
    //   41	57	60	java/io/FileNotFoundException
    //   0	7	69	finally
    //   11	27	69	finally
    //   41	57	69	finally
    //   27	35	80	finally
    //   27	35	86	java/io/FileNotFoundException
  }
  
  /* Error */
  public static com.apptentive.android.sdk.model.StoredFile createLocalStoredFile(java.io.InputStream paramInputStream, String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: new 98	java/io/File
    //   9: dup
    //   10: aload_2
    //   11: invokespecial 101	java/io/File:<init>	(Ljava/lang/String;)V
    //   14: astore 5
    //   16: aload 5
    //   18: invokevirtual 105	java/io/File:exists	()Z
    //   21: ifeq +9 -> 30
    //   24: aload 5
    //   26: invokevirtual 162	java/io/File:delete	()Z
    //   29: pop
    //   30: new 110	java/io/FileOutputStream
    //   33: dup
    //   34: aload 5
    //   36: invokespecial 163	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   39: astore 5
    //   41: new 165	java/io/BufferedOutputStream
    //   44: dup
    //   45: aload 5
    //   47: invokespecial 168	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   50: astore 6
    //   52: new 170	com/apptentive/android/sdk/util/CountingOutputStream
    //   55: dup
    //   56: aload 6
    //   58: invokespecial 171	com/apptentive/android/sdk/util/CountingOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   61: astore 10
    //   63: aload 5
    //   65: astore 9
    //   67: aload 6
    //   69: astore 8
    //   71: aload 10
    //   73: astore 7
    //   75: sipush 2048
    //   78: newarray byte
    //   80: astore 11
    //   82: aload 5
    //   84: astore 9
    //   86: aload 6
    //   88: astore 8
    //   90: aload 10
    //   92: astore 7
    //   94: aload_0
    //   95: aload 11
    //   97: iconst_0
    //   98: sipush 2048
    //   101: invokevirtual 174	java/io/InputStream:read	([BII)I
    //   104: istore 4
    //   106: iload 4
    //   108: iconst_m1
    //   109: if_icmpeq +69 -> 178
    //   112: aload 5
    //   114: astore 9
    //   116: aload 6
    //   118: astore 8
    //   120: aload 10
    //   122: astore 7
    //   124: aload 10
    //   126: aload 11
    //   128: iconst_0
    //   129: iload 4
    //   131: invokevirtual 175	com/apptentive/android/sdk/util/CountingOutputStream:write	([BII)V
    //   134: goto -52 -> 82
    //   137: astore_0
    //   138: aload 10
    //   140: astore_0
    //   141: aload 6
    //   143: astore_1
    //   144: aload 5
    //   146: astore 9
    //   148: aload_1
    //   149: astore 8
    //   151: aload_0
    //   152: astore 7
    //   154: ldc -79
    //   156: iconst_0
    //   157: anewarray 4	java/lang/Object
    //   160: invokestatic 183	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   163: aload_0
    //   164: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   167: aload_1
    //   168: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   171: aload 5
    //   173: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   176: aconst_null
    //   177: areturn
    //   178: aload 5
    //   180: astore 9
    //   182: aload 6
    //   184: astore 8
    //   186: aload 10
    //   188: astore 7
    //   190: new 185	java/lang/StringBuilder
    //   193: dup
    //   194: invokespecial 186	java/lang/StringBuilder:<init>	()V
    //   197: ldc -68
    //   199: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: aload 10
    //   204: invokevirtual 195	com/apptentive/android/sdk/util/CountingOutputStream:getBytesWritten	()I
    //   207: sipush 1024
    //   210: idiv
    //   211: invokevirtual 198	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   214: ldc -56
    //   216: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: invokevirtual 203	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   222: iconst_0
    //   223: anewarray 4	java/lang/Object
    //   226: invokestatic 206	com/apptentive/android/sdk/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   229: aload 10
    //   231: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   234: aload 6
    //   236: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   239: aload 5
    //   241: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   244: new 208	com/apptentive/android/sdk/model/StoredFile
    //   247: dup
    //   248: invokespecial 209	com/apptentive/android/sdk/model/StoredFile:<init>	()V
    //   251: astore_0
    //   252: aload_0
    //   253: aload_1
    //   254: invokevirtual 212	com/apptentive/android/sdk/model/StoredFile:setSourceUriOrPath	(Ljava/lang/String;)V
    //   257: aload_0
    //   258: aload_2
    //   259: invokevirtual 215	com/apptentive/android/sdk/model/StoredFile:setLocalFilePath	(Ljava/lang/String;)V
    //   262: aload_0
    //   263: aload_3
    //   264: invokevirtual 218	com/apptentive/android/sdk/model/StoredFile:setMimeType	(Ljava/lang/String;)V
    //   267: aload_0
    //   268: areturn
    //   269: astore_0
    //   270: aconst_null
    //   271: astore_1
    //   272: aconst_null
    //   273: astore_2
    //   274: aconst_null
    //   275: astore 5
    //   277: aload_2
    //   278: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   281: aload_1
    //   282: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   285: aload 5
    //   287: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   290: aload_0
    //   291: athrow
    //   292: astore_0
    //   293: aconst_null
    //   294: astore_2
    //   295: aconst_null
    //   296: astore_1
    //   297: goto -20 -> 277
    //   300: astore_0
    //   301: aconst_null
    //   302: astore_2
    //   303: aload 6
    //   305: astore_1
    //   306: goto -29 -> 277
    //   309: astore_0
    //   310: aload 9
    //   312: astore 5
    //   314: aload 8
    //   316: astore_1
    //   317: aload 7
    //   319: astore_2
    //   320: goto -43 -> 277
    //   323: astore_0
    //   324: aconst_null
    //   325: astore 5
    //   327: aconst_null
    //   328: astore_1
    //   329: aconst_null
    //   330: astore_0
    //   331: goto -187 -> 144
    //   334: astore_0
    //   335: aconst_null
    //   336: astore_1
    //   337: aconst_null
    //   338: astore_0
    //   339: goto -195 -> 144
    //   342: astore_0
    //   343: aconst_null
    //   344: astore_0
    //   345: aload 6
    //   347: astore_1
    //   348: goto -204 -> 144
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	351	0	paramInputStream	java.io.InputStream
    //   0	351	1	paramString1	String
    //   0	351	2	paramString2	String
    //   0	351	3	paramString3	String
    //   104	26	4	i	int
    //   14	312	5	localObject1	Object
    //   50	296	6	localBufferedOutputStream	java.io.BufferedOutputStream
    //   73	245	7	localObject2	Object
    //   69	246	8	localObject3	Object
    //   65	246	9	localObject4	Object
    //   61	169	10	localCountingOutputStream	CountingOutputStream
    //   80	47	11	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   75	82	137	java/io/IOException
    //   94	106	137	java/io/IOException
    //   124	134	137	java/io/IOException
    //   190	229	137	java/io/IOException
    //   6	30	269	finally
    //   30	41	269	finally
    //   41	52	292	finally
    //   52	63	300	finally
    //   75	82	309	finally
    //   94	106	309	finally
    //   124	134	309	finally
    //   154	163	309	finally
    //   190	229	309	finally
    //   6	30	323	java/io/IOException
    //   30	41	323	java/io/IOException
    //   41	52	334	java/io/IOException
    //   52	63	342	java/io/IOException
  }
  
  public static double currentTimeSeconds()
  {
    return System.currentTimeMillis() / 1000.0D;
  }
  
  public static String dateToIso8601String(long paramLong)
  {
    return dateToString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ"), new Date(paramLong));
  }
  
  public static String dateToString(DateFormat paramDateFormat, Date paramDate)
  {
    return paramDateFormat.format(paramDate);
  }
  
  public static int dipsToPixels(Context paramContext, int paramInt)
  {
    return Math.round(paramContext.getResources().getDisplayMetrics().density * paramInt);
  }
  
  public static float dipsToPixelsFloat(Context paramContext, int paramInt)
  {
    return paramContext.getResources().getDisplayMetrics().density * paramInt;
  }
  
  public static void ensureClosed(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  public static String generateCacheFileFullPath(Uri paramUri, File paramFile, long paramLong)
  {
    return new File(paramFile, md5(paramUri.toString() + Long.toString(paramLong))).getPath();
  }
  
  public static String generateCacheFileFullPath(String paramString, File paramFile)
  {
    return new File(paramFile, md5(paramString)).getPath();
  }
  
  public static String generateCacheFilePathFromNonceOrPrefix(Context paramContext, String paramString1, String paramString2)
  {
    String str = paramString2;
    if (paramString2 == null) {
      str = "apptentive-api-file-" + paramString1;
    }
    return new File(getDiskCacheDir(paramContext), str).getPath();
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.e("Error getting app version code.", paramContext, new Object[0]);
    }
    return -1;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.e("Error getting app version name.", paramContext, new Object[0]);
    }
    return null;
  }
  
  public static Drawable getCompatDrawable(Context paramContext, int paramInt)
  {
    try
    {
      if (Build.VERSION.SDK_INT < 21) {
        return paramContext.getResources().getDrawable(paramInt);
      }
      paramContext = paramContext.getResources().getDrawable(paramInt, paramContext.getTheme());
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static long getContentCreationTime(Context paramContext, Uri paramUri)
  {
    if (!hasPermission(paramContext, "android.permission.READ_EXTERNAL_STORAGE")) {
      return 0L;
    }
    paramUri = paramContext.getContentResolver().query(paramUri, null, null, null, null);
    if ((paramUri != null) && (paramUri.moveToFirst()))
    {
      String str = paramUri.getString(0);
      str = str.substring(str.lastIndexOf(":") + 1);
      paramUri.close();
      paramContext = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, "_id = ? ", new String[] { str }, null);
      if ((paramContext != null) && (paramContext.moveToFirst()))
      {
        long l = paramContext.getLong(paramContext.getColumnIndex("date_added"));
        paramContext.close();
        return l;
      }
    }
    return 0L;
  }
  
  public static File getDiskCacheDir(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1;
    if (!"mounted".equals(Environment.getExternalStorageState()))
    {
      localObject1 = localObject2;
      if (Environment.isExternalStorageRemovable()) {}
    }
    else
    {
      localObject1 = localObject2;
      if (hasPermission(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE")) {
        localObject1 = paramContext.getExternalCacheDir();
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = paramContext.getCacheDir();
    }
    return localObject2;
  }
  
  public static String getInstallerPackageName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstallerPackageName(paramContext.getPackageName());
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static Integer getMajorOsVersion()
  {
    try
    {
      String[] arrayOfString = Build.VERSION.RELEASE.split("\\.");
      if ((arrayOfString != null) && (arrayOfString.length != 0))
      {
        int i = Integer.parseInt(arrayOfString[0]);
        return Integer.valueOf(i);
      }
    }
    catch (Exception localException)
    {
      Log.w("Error getting major OS version", localException, new Object[0]);
    }
    return Integer.valueOf(-1);
  }
  
  public static String getMimeTypeFromUri(Context paramContext, Uri paramUri)
  {
    return paramContext.getContentResolver().getType(paramUri);
  }
  
  public static Object getPackageMetaData(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.get(paramString);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static boolean getPackageMetaDataBoolean(Context paramContext, String paramString)
  {
    try
    {
      boolean bool = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getBoolean(paramString, false);
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static String getPackageMetaDataSingleQuotedString(Context paramContext, String paramString)
  {
    paramContext = getPackageMetaData(paramContext, paramString);
    if (paramContext == null) {
      paramString = null;
    }
    do
    {
      return paramString;
      paramString = paramContext.toString();
      paramContext = paramString;
      if (paramString.endsWith("'")) {
        paramContext = paramString.substring(0, paramString.length() - 1);
      }
      paramString = paramContext;
    } while (!paramContext.startsWith("'"));
    return paramContext.substring(1, paramContext.length());
  }
  
  private static List<PackageInfo> getPermissions(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(4096);
  }
  
  public static String getRealFilePathFromUri(Context paramContext, Uri paramUri)
  {
    if (!hasPermission(paramContext, "android.permission.READ_EXTERNAL_STORAGE")) {}
    do
    {
      do
      {
        return null;
        paramUri = paramContext.getContentResolver().query(paramUri, null, null, null, null);
      } while ((paramUri == null) || (!paramUri.moveToFirst()));
      String str = paramUri.getString(0);
      str = str.substring(str.lastIndexOf(":") + 1);
      paramUri.close();
      paramContext = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, "_id = ? ", new String[] { str }, null);
    } while ((paramContext == null) || (!paramContext.moveToFirst()));
    paramUri = paramContext.getString(paramContext.getColumnIndex("_data"));
    paramContext.close();
    return paramUri;
  }
  
  public static Point getScreenSize(Context paramContext)
  {
    Point localPoint = new Point();
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    localPoint.set(paramContext.getWidth(), paramContext.getHeight());
    return localPoint;
  }
  
  public static StateListDrawable getSelectableImageButtonBackground(int paramInt)
  {
    ColorDrawable localColorDrawable = new ColorDrawable(paramInt);
    StateListDrawable localStateListDrawable = new StateListDrawable();
    localStateListDrawable.addState(new int[] { 16842919 }, localColorDrawable);
    localStateListDrawable.addState(new int[] { 16843518 }, localColorDrawable);
    return localStateListDrawable;
  }
  
  public static int getStatusBarHeight(Window paramWindow)
  {
    Rect localRect = new Rect();
    paramWindow.getDecorView().getWindowVisibleDisplayFrame(localRect);
    return localRect.top;
  }
  
  public static int getThemeColor(Context paramContext, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(paramInt, localTypedValue, true)) {
      return localTypedValue.data;
    }
    return 0;
  }
  
  public static int getThemeColorFromAttrOrRes(Context paramContext, int paramInt1, int paramInt2)
  {
    int i = getThemeColor(paramContext, paramInt1);
    paramInt1 = i;
    if (i == 0) {
      paramInt1 = paramContext.getResources().getColor(paramInt2);
    }
    return paramInt1;
  }
  
  public static int getUtcOffset()
  {
    return TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000;
  }
  
  public static boolean hasPermission(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static void hideSoftKeyboard(Context paramContext, View paramView)
  {
    if (paramView != null) {
      ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
  }
  
  public static boolean isEmailValid(String paramString)
  {
    return paramString.matches("^[^\\s@]+@[^\\s@]+$");
  }
  
  public static boolean isEmpty(CharSequence paramCharSequence)
  {
    return (paramCharSequence == null) || (paramCharSequence.length() == 0);
  }
  
  public static boolean isMimeTypeImage(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return paramString.substring(0, paramString.indexOf("/")).equalsIgnoreCase("Image");
  }
  
  public static boolean isNetworkConnectionPresent(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    return (paramContext != null) && (paramContext.getActiveNetworkInfo() != null);
  }
  
  public static int lighter(int paramInt, float paramFloat)
  {
    int i = (int)((Color.red(paramInt) * (1.0F - paramFloat) / 255.0F + paramFloat) * 255.0F);
    int j = (int)((Color.green(paramInt) * (1.0F - paramFloat) / 255.0F + paramFloat) * 255.0F);
    int k = (int)((Color.blue(paramInt) * (1.0F - paramFloat) / 255.0F + paramFloat) * 255.0F);
    return Color.argb(Color.alpha(paramInt), i, j, k);
  }
  
  private static String md5(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuilder();
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        ((StringBuilder)localObject).append(Integer.toHexString(paramString[i] & 0xFF));
        i += 1;
      }
      paramString = ((StringBuilder)localObject).toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static boolean openFileAttachment(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Object localObject;
    if ((("mounted".equals(Environment.getExternalStorageState())) || (!Environment.isExternalStorageRemovable())) && (hasPermission(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE")))
    {
      paramString1 = new File(paramString2);
      if (paramString1.exists())
      {
        localObject = paramString1.getName();
        paramString1 = new Intent();
        paramString1.setAction("android.intent.action.VIEW");
        File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "apptentive-received");
        if (!localFile.exists()) {
          localFile.mkdir();
        }
        localObject = new File(localFile, (String)localObject);
        String str = ((File)localObject).getPath();
        if (!((File)localObject).exists())
        {
          String[] arrayOfString = localFile.list();
          if (arrayOfString != null)
          {
            int i = 0;
            while (i < arrayOfString.length)
            {
              new File(localFile, arrayOfString[i]).delete();
              i += 1;
            }
          }
        }
        if (copyFile(paramString2, str) != 0) {
          break label187;
        }
      }
    }
    label187:
    do
    {
      return false;
      paramString1.setDataAndType(Uri.fromFile((File)localObject), paramString3);
      try
      {
        paramContext.startActivity(paramString1);
        return true;
      }
      catch (ActivityNotFoundException paramContext)
      {
        Log.e("Activity not found to open attachment: ", paramContext, new Object[0]);
        return false;
      }
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
    } while (!canLaunchIntent(paramContext, paramString1));
    paramContext.startActivity(paramString1);
    return false;
  }
  
  public static boolean packageHasPermission(Context paramContext, String paramString)
  {
    return packageHasPermission(paramContext, paramContext.getApplicationContext().getPackageName(), paramString);
  }
  
  public static boolean packageHasPermission(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = getPermissions(paramContext).iterator();
    while (paramContext.hasNext())
    {
      Object localObject = (PackageInfo)paramContext.next();
      if ((((PackageInfo)localObject).packageName.equals(paramString1)) && (((PackageInfo)localObject).requestedPermissions != null))
      {
        localObject = ((PackageInfo)localObject).requestedPermissions;
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          if (localObject[i].equals(paramString2)) {
            return true;
          }
          i += 1;
        }
      }
    }
    return false;
  }
  
  public static Integer parseCacheControlHeader(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    int j;
    int i;
    if (paramString != null)
    {
      paramString = paramString.substring(paramString.indexOf("[") + 1, paramString.lastIndexOf("]")).split(",");
      j = paramString.length;
      i = 0;
    }
    for (;;)
    {
      localObject1 = localObject2;
      if (i < j)
      {
        localObject1 = paramString[i].trim();
        if (!((String)localObject1).startsWith("max-age=")) {
          break label126;
        }
        localObject1 = ((String)localObject1).split("=");
        if (localObject1.length != 2) {
          break label126;
        }
        localObject1 = localObject1[1];
      }
      try
      {
        int k = Integer.parseInt((String)localObject1);
        localObject1 = Integer.valueOf(k);
        return localObject1;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Throwable localThrowable;
        label126:
        for (;;) {}
      }
      Log.e("Error parsing cache expiration as number: %s", localThrowable, new Object[] { localObject1 });
      i += 1;
    }
  }
  
  /* Error */
  public static Date parseIso8601Date(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 800	java/lang/String:trim	()Ljava/lang/String;
    //   4: ldc_w 812
    //   7: ldc_w 814
    //   10: invokevirtual 818	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   13: ldc_w 820
    //   16: ldc_w 822
    //   19: invokevirtual 818	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   22: astore 4
    //   24: aload 4
    //   26: astore_3
    //   27: aload 4
    //   29: aload 4
    //   31: invokevirtual 493	java/lang/String:length	()I
    //   34: iconst_3
    //   35: isub
    //   36: invokevirtual 826	java/lang/String:charAt	(I)C
    //   39: bipush 58
    //   41: if_icmpne +44 -> 85
    //   44: aload 4
    //   46: ldc_w 375
    //   49: invokevirtual 379	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   52: istore_1
    //   53: new 185	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 186	java/lang/StringBuilder:<init>	()V
    //   60: aload 4
    //   62: iconst_0
    //   63: iload_1
    //   64: invokevirtual 496	java/lang/String:substring	(II)Ljava/lang/String;
    //   67: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload 4
    //   72: iload_1
    //   73: iconst_1
    //   74: iadd
    //   75: invokevirtual 382	java/lang/String:substring	(I)Ljava/lang/String;
    //   78: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 203	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore_3
    //   85: aload_3
    //   86: bipush 46
    //   88: invokevirtual 828	java/lang/String:lastIndexOf	(I)I
    //   91: istore_2
    //   92: aload_3
    //   93: bipush 43
    //   95: invokevirtual 828	java/lang/String:lastIndexOf	(I)I
    //   98: iconst_m1
    //   99: if_icmpeq +124 -> 223
    //   102: aload_3
    //   103: bipush 43
    //   105: invokevirtual 828	java/lang/String:lastIndexOf	(I)I
    //   108: istore_1
    //   109: aload_3
    //   110: astore 4
    //   112: iload_2
    //   113: iconst_m1
    //   114: if_icmpeq +81 -> 195
    //   117: aload_3
    //   118: iconst_0
    //   119: iload_2
    //   120: iconst_1
    //   121: iadd
    //   122: invokevirtual 496	java/lang/String:substring	(II)Ljava/lang/String;
    //   125: astore 4
    //   127: aload_3
    //   128: iload_2
    //   129: iconst_1
    //   130: iadd
    //   131: iload_1
    //   132: invokevirtual 496	java/lang/String:substring	(II)Ljava/lang/String;
    //   135: astore 5
    //   137: aload_3
    //   138: iload_1
    //   139: invokevirtual 382	java/lang/String:substring	(I)Ljava/lang/String;
    //   142: astore_3
    //   143: ldc_w 830
    //   146: iconst_1
    //   147: anewarray 4	java/lang/Object
    //   150: dup
    //   151: iconst_0
    //   152: aload 5
    //   154: aastore
    //   155: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   158: ldc_w 822
    //   161: ldc_w 832
    //   164: invokevirtual 818	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   167: astore 5
    //   169: new 185	java/lang/StringBuilder
    //   172: dup
    //   173: invokespecial 186	java/lang/StringBuilder:<init>	()V
    //   176: aload 4
    //   178: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: aload 5
    //   183: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: aload_3
    //   187: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 203	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: astore 4
    //   195: aload 4
    //   197: ldc_w 834
    //   200: invokevirtual 837	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   203: ifeq +67 -> 270
    //   206: new 232	java/text/SimpleDateFormat
    //   209: dup
    //   210: ldc 11
    //   212: invokespecial 233	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   215: aload 4
    //   217: invokevirtual 839	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   220: astore_0
    //   221: aload_0
    //   222: areturn
    //   223: aload_3
    //   224: bipush 45
    //   226: invokevirtual 828	java/lang/String:lastIndexOf	(I)I
    //   229: istore_1
    //   230: goto -121 -> 109
    //   233: astore_3
    //   234: new 185	java/lang/StringBuilder
    //   237: dup
    //   238: invokespecial 186	java/lang/StringBuilder:<init>	()V
    //   241: ldc_w 841
    //   244: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: aload_0
    //   248: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: invokevirtual 203	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   254: aload_3
    //   255: iconst_0
    //   256: anewarray 4	java/lang/Object
    //   259: invokestatic 327	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   262: new 235	java/util/Date
    //   265: dup
    //   266: invokespecial 842	java/util/Date:<init>	()V
    //   269: areturn
    //   270: new 232	java/text/SimpleDateFormat
    //   273: dup
    //   274: ldc 8
    //   276: invokespecial 233	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   279: aload 4
    //   281: invokevirtual 839	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   284: astore_0
    //   285: aload_0
    //   286: areturn
    //   287: astore_0
    //   288: new 185	java/lang/StringBuilder
    //   291: dup
    //   292: invokespecial 186	java/lang/StringBuilder:<init>	()V
    //   295: ldc_w 844
    //   298: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: aload 4
    //   303: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: invokevirtual 203	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: aload_0
    //   310: iconst_0
    //   311: anewarray 4	java/lang/Object
    //   314: invokestatic 327	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   317: aconst_null
    //   318: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	319	0	paramString	String
    //   52	178	1	i	int
    //   91	40	2	j	int
    //   26	198	3	localObject1	Object
    //   233	22	3	localException	Exception
    //   22	280	4	localObject2	Object
    //   135	47	5	str	String
    // Exception table:
    //   from	to	target	type
    //   27	85	233	java/lang/Exception
    //   85	109	233	java/lang/Exception
    //   117	195	233	java/lang/Exception
    //   223	230	233	java/lang/Exception
    //   195	221	287	java/text/ParseException
    //   270	285	287	java/text/ParseException
  }
  
  public static Integer parseWebColorAsAndroidColor(String paramString)
  {
    if (paramString.length() == 9) {}
    for (boolean bool = true;; bool = false) {
      try
      {
        Integer localInteger = Integer.valueOf(Color.parseColor(paramString));
        paramString = localInteger;
        if (Boolean.valueOf(bool).booleanValue())
        {
          int i = localInteger.intValue();
          int j = localInteger.intValue();
          paramString = Integer.valueOf((j & 0xFF) << 24 | i >>> 8);
        }
        return paramString;
      }
      catch (IllegalArgumentException paramString) {}
    }
    return null;
  }
  
  public static int pixelsToDips(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return Math.round(paramInt / f);
  }
  
  public static void printDebugInfo(Context paramContext)
  {
    Point localPoint = getScreenSize(paramContext);
    Log.e("Screen size: PX=%dx%d DP=%dx%d", new Object[] { Integer.valueOf(localPoint.x), Integer.valueOf(localPoint.y), Integer.valueOf(pixelsToDips(paramContext, localPoint.x)), Integer.valueOf(pixelsToDips(paramContext, localPoint.y)) });
  }
  
  /* Error */
  public static String readStringFromInputStream(java.io.InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 185	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 186	java/lang/StringBuilder:<init>	()V
    //   9: astore 4
    //   11: sipush 8196
    //   14: newarray char
    //   16: astore 5
    //   18: new 880	java/io/InputStreamReader
    //   21: dup
    //   22: aload_0
    //   23: aload_1
    //   24: invokespecial 883	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   27: astore_0
    //   28: aload_0
    //   29: aload 5
    //   31: iconst_0
    //   32: sipush 8196
    //   35: invokevirtual 888	java/io/Reader:read	([CII)I
    //   38: istore_2
    //   39: iload_2
    //   40: ifge +13 -> 53
    //   43: aload_0
    //   44: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   47: aload 4
    //   49: invokevirtual 203	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: areturn
    //   53: aload 4
    //   55: aload 5
    //   57: iconst_0
    //   58: iload_2
    //   59: invokevirtual 891	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: goto -35 -> 28
    //   66: astore_1
    //   67: aload_0
    //   68: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   71: goto -24 -> 47
    //   74: astore_1
    //   75: aload_3
    //   76: astore_0
    //   77: aload_0
    //   78: invokestatic 125	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: goto -7 -> 77
    //   87: astore_0
    //   88: aconst_null
    //   89: astore_0
    //   90: goto -23 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramInputStream	java.io.InputStream
    //   0	93	1	paramString	String
    //   38	21	2	i	int
    //   1	75	3	localObject	Object
    //   9	45	4	localStringBuilder	StringBuilder
    //   16	40	5	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   28	39	66	java/lang/Exception
    //   53	63	66	java/lang/Exception
    //   18	28	74	finally
    //   28	39	83	finally
    //   53	63	83	finally
    //   18	28	87	java/lang/Exception
  }
  
  public static void setBackground(View paramView, int paramInt)
  {
    setBackground(paramView, getCompatDrawable(paramView.getContext(), paramInt));
  }
  
  public static void setBackground(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      paramView.setBackgroundDrawable(paramDrawable);
      return;
    }
    paramView.setBackground(paramDrawable);
  }
  
  public static void showSoftKeyboard(Activity paramActivity, View paramView)
  {
    if (paramActivity.getCurrentFocus() != null) {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).showSoftInput(paramView, 0);
    }
  }
  
  public static String stackTraceAsString(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
    return localStringWriter.toString();
  }
}
