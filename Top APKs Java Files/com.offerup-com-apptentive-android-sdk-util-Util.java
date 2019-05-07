package com.apptentive.android.sdk.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
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
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import com.apptentive.android.sdk.ApptentiveInternal;
import com.apptentive.android.sdk.ApptentiveLog;
import com.apptentive.android.sdk.R.attr;
import com.apptentive.android.sdk.R.integer;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class Util
{
  public static final String PSEUDO_ISO8601_DATE_FORMAT = "yyyy-MM-dd HH:mm:ssZ";
  public static final String PSEUDO_ISO8601_DATE_FORMAT_MILLIS = "yyyy-MM-dd HH:mm:ss.SSSZ";
  
  public Util() {}
  
  public static int alphaMixColors(int paramInt1, int paramInt2)
  {
    double d2 = (paramInt1 >>> 24) / 255.0D;
    double d3 = (paramInt2 >>> 24) / 255.0D;
    double d1 = (1.0D - d3) * d2 + d3;
    d2 = d2 * (1.0D - d3) / d1;
    d3 = d2 / d1;
    int i = (int)(d1 * 255.0D);
    int j = (int)((paramInt1 >> 16 & 0xFF) * d2 + (paramInt2 >> 16 & 0xFF) * d3);
    int k = (int)((paramInt1 >> 8 & 0xFF) * d2 + (paramInt2 >> 8 & 0xFF) * d3);
    return (int)(d2 * (paramInt1 & 0xFF) + d3 * (paramInt2 & 0xFF)) & 0xFF | (i & 0xFF) << 24 | (j & 0xFF) << 16 | (k & 0xFF) << 8;
  }
  
  public static int brighter(int paramInt, float paramFloat)
  {
    int i = (int)((Color.red(paramInt) * (1.0F - paramFloat) / 255.0F + paramFloat) * 255.0F);
    int j = (int)((Color.green(paramInt) * (1.0F - paramFloat) / 255.0F + paramFloat) * 255.0F);
    int k = (int)((Color.blue(paramInt) * (1.0F - paramFloat) / 255.0F + paramFloat) * 255.0F);
    return Color.argb(Color.alpha(paramInt), i, j, k);
  }
  
  public static void calculateListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int i = 0;
    while (i < localListAdapter.getCount())
    {
      localObject = localListAdapter.getView(i, null, paramListView);
      ((View)localObject).measure(0, 0);
      ((View)localObject).getMeasuredHeight();
      i += 1;
    }
    Object localObject = paramListView.getLayoutParams();
    paramListView.getDividerHeight();
    localListAdapter.getCount();
    i = ((ViewGroup.LayoutParams)localObject).height;
  }
  
  public static boolean canLaunchIntent(Context paramContext, Intent paramIntent)
  {
    if (paramContext == null) {}
    while (paramIntent.resolveActivity(paramContext.getPackageManager()) == null) {
      return false;
    }
    return true;
  }
  
  public static Activity castContextToActivity(Context paramContext)
  {
    for (;;)
    {
      if (paramContext == null) {
        return null;
      }
      if ((paramContext instanceof Activity)) {
        return (Activity)paramContext;
      }
      if (!(paramContext instanceof ContextWrapper)) {
        break;
      }
      paramContext = ((ContextWrapper)paramContext).getBaseContext();
    }
    return null;
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
    //   3: new 134	java/io/File
    //   6: dup
    //   7: aload_0
    //   8: invokespecial 137	java/io/File:<init>	(Ljava/lang/String;)V
    //   11: invokevirtual 141	java/io/File:exists	()Z
    //   14: ifeq +124 -> 138
    //   17: new 143	java/io/FileInputStream
    //   20: dup
    //   21: aload_0
    //   22: invokespecial 144	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   25: astore_0
    //   26: new 146	java/io/FileOutputStream
    //   29: dup
    //   30: aload_1
    //   31: invokespecial 147	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   34: astore_1
    //   35: sipush 1444
    //   38: newarray byte
    //   40: astore 4
    //   42: iconst_0
    //   43: istore_2
    //   44: aload_0
    //   45: aload 4
    //   47: invokevirtual 153	java/io/InputStream:read	([B)I
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
    //   65: invokevirtual 157	java/io/FileOutputStream:write	([BII)V
    //   68: goto -24 -> 44
    //   71: astore 4
    //   73: aload_0
    //   74: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   77: aload_1
    //   78: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   81: iconst_0
    //   82: ireturn
    //   83: aload_0
    //   84: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   87: aload_1
    //   88: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   91: iload_2
    //   92: ireturn
    //   93: astore 4
    //   95: aconst_null
    //   96: astore_1
    //   97: aconst_null
    //   98: astore_0
    //   99: aload_0
    //   100: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   103: aload_1
    //   104: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
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
  public static com.apptentive.android.sdk.model.StoredFile createLocalStoredFile(java.io.InputStream paramInputStream, String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: new 134	java/io/File
    //   9: dup
    //   10: aload_2
    //   11: invokespecial 137	java/io/File:<init>	(Ljava/lang/String;)V
    //   14: astore 5
    //   16: aload 5
    //   18: invokevirtual 141	java/io/File:exists	()Z
    //   21: ifeq +9 -> 30
    //   24: aload 5
    //   26: invokevirtual 168	java/io/File:delete	()Z
    //   29: pop
    //   30: new 146	java/io/FileOutputStream
    //   33: dup
    //   34: aload 5
    //   36: invokespecial 171	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   39: astore 5
    //   41: new 173	java/io/BufferedOutputStream
    //   44: dup
    //   45: aload 5
    //   47: invokespecial 176	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   50: astore 6
    //   52: new 178	com/apptentive/android/sdk/util/CountingOutputStream
    //   55: dup
    //   56: aload 6
    //   58: invokespecial 179	com/apptentive/android/sdk/util/CountingOutputStream:<init>	(Ljava/io/OutputStream;)V
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
    //   101: invokevirtual 182	java/io/InputStream:read	([BII)I
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
    //   131: invokevirtual 183	com/apptentive/android/sdk/util/CountingOutputStream:write	([BII)V
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
    //   154: ldc -71
    //   156: iconst_0
    //   157: anewarray 4	java/lang/Object
    //   160: invokestatic 191	com/apptentive/android/sdk/ApptentiveLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   163: aload_0
    //   164: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   167: aload_1
    //   168: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   171: aload 5
    //   173: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   176: aconst_null
    //   177: areturn
    //   178: aload 5
    //   180: astore 9
    //   182: aload 6
    //   184: astore 8
    //   186: aload 10
    //   188: astore 7
    //   190: new 193	java/lang/StringBuilder
    //   193: dup
    //   194: ldc -61
    //   196: invokespecial 196	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   199: aload 10
    //   201: invokevirtual 199	com/apptentive/android/sdk/util/CountingOutputStream:getBytesWritten	()I
    //   204: sipush 1024
    //   207: idiv
    //   208: invokevirtual 203	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   211: ldc -51
    //   213: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: invokevirtual 211	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   219: iconst_0
    //   220: anewarray 4	java/lang/Object
    //   223: invokestatic 214	com/apptentive/android/sdk/ApptentiveLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   226: aload 10
    //   228: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   231: aload 6
    //   233: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   236: aload 5
    //   238: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   241: new 216	com/apptentive/android/sdk/model/StoredFile
    //   244: dup
    //   245: invokespecial 217	com/apptentive/android/sdk/model/StoredFile:<init>	()V
    //   248: astore_0
    //   249: aload_0
    //   250: aload_1
    //   251: invokevirtual 220	com/apptentive/android/sdk/model/StoredFile:setSourceUriOrPath	(Ljava/lang/String;)V
    //   254: aload_0
    //   255: aload_2
    //   256: invokevirtual 223	com/apptentive/android/sdk/model/StoredFile:setLocalFilePath	(Ljava/lang/String;)V
    //   259: aload_0
    //   260: aload_3
    //   261: invokevirtual 226	com/apptentive/android/sdk/model/StoredFile:setMimeType	(Ljava/lang/String;)V
    //   264: aload_0
    //   265: areturn
    //   266: astore_0
    //   267: aconst_null
    //   268: astore_1
    //   269: aconst_null
    //   270: astore_2
    //   271: aconst_null
    //   272: astore 5
    //   274: aload_2
    //   275: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   278: aload_1
    //   279: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   282: aload 5
    //   284: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   287: aload_0
    //   288: athrow
    //   289: astore_0
    //   290: aconst_null
    //   291: astore_2
    //   292: aconst_null
    //   293: astore_1
    //   294: goto -20 -> 274
    //   297: astore_0
    //   298: aconst_null
    //   299: astore_2
    //   300: aload 6
    //   302: astore_1
    //   303: goto -29 -> 274
    //   306: astore_0
    //   307: aload 9
    //   309: astore 5
    //   311: aload 8
    //   313: astore_1
    //   314: aload 7
    //   316: astore_2
    //   317: goto -43 -> 274
    //   320: astore_0
    //   321: aconst_null
    //   322: astore 5
    //   324: aconst_null
    //   325: astore_1
    //   326: aconst_null
    //   327: astore_0
    //   328: goto -184 -> 144
    //   331: astore_0
    //   332: aconst_null
    //   333: astore_1
    //   334: aconst_null
    //   335: astore_0
    //   336: goto -192 -> 144
    //   339: astore_0
    //   340: aconst_null
    //   341: astore_0
    //   342: aload 6
    //   344: astore_1
    //   345: goto -201 -> 144
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	348	0	paramInputStream	java.io.InputStream
    //   0	348	1	paramString1	String
    //   0	348	2	paramString2	String
    //   0	348	3	paramString3	String
    //   104	26	4	i	int
    //   14	309	5	localObject1	Object
    //   50	293	6	localBufferedOutputStream	java.io.BufferedOutputStream
    //   73	242	7	localObject2	Object
    //   69	243	8	localObject3	Object
    //   65	243	9	localObject4	Object
    //   61	166	10	localCountingOutputStream	CountingOutputStream
    //   80	47	11	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   75	82	137	java/io/IOException
    //   94	106	137	java/io/IOException
    //   124	134	137	java/io/IOException
    //   190	226	137	java/io/IOException
    //   6	30	266	finally
    //   30	41	266	finally
    //   41	52	289	finally
    //   52	63	297	finally
    //   75	82	306	finally
    //   94	106	306	finally
    //   124	134	306	finally
    //   154	163	306	finally
    //   190	226	306	finally
    //   6	30	320	java/io/IOException
    //   30	41	320	java/io/IOException
    //   41	52	331	java/io/IOException
    //   52	63	339	java/io/IOException
  }
  
  /* Error */
  public static com.apptentive.android.sdk.model.StoredFile createLocalStoredFile(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: invokestatic 235	com/apptentive/android/sdk/ApptentiveInternal:getInstance	()Lcom/apptentive/android/sdk/ApptentiveInternal;
    //   3: invokevirtual 238	com/apptentive/android/sdk/ApptentiveInternal:getApplicationContext	()Landroid/content/Context;
    //   6: astore_3
    //   7: aload_0
    //   8: invokestatic 244	android/webkit/URLUtil:isContentUrl	(Ljava/lang/String;)Z
    //   11: ifeq +37 -> 48
    //   14: aload_3
    //   15: ifnull +33 -> 48
    //   18: aload_0
    //   19: invokestatic 250	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   22: astore 4
    //   24: aload_3
    //   25: invokevirtual 254	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   28: aload 4
    //   30: invokevirtual 260	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   33: astore_3
    //   34: aload_3
    //   35: aload_0
    //   36: aload_1
    //   37: aload_2
    //   38: invokestatic 262	com/apptentive/android/sdk/util/Util:createLocalStoredFile	(Ljava/io/InputStream;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/apptentive/android/sdk/model/StoredFile;
    //   41: astore_0
    //   42: aload_3
    //   43: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   46: aload_0
    //   47: areturn
    //   48: new 143	java/io/FileInputStream
    //   51: dup
    //   52: new 134	java/io/File
    //   55: dup
    //   56: aload_0
    //   57: invokespecial 137	java/io/File:<init>	(Ljava/lang/String;)V
    //   60: invokespecial 263	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   63: astore_3
    //   64: goto -30 -> 34
    //   67: astore_0
    //   68: aconst_null
    //   69: astore_3
    //   70: aload_3
    //   71: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   74: aconst_null
    //   75: areturn
    //   76: astore_0
    //   77: aconst_null
    //   78: astore_3
    //   79: aload_3
    //   80: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   83: aload_0
    //   84: athrow
    //   85: astore_0
    //   86: goto -7 -> 79
    //   89: astore_0
    //   90: goto -20 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramString1	String
    //   0	93	1	paramString2	String
    //   0	93	2	paramString3	String
    //   6	74	3	localObject	Object
    //   22	7	4	localUri	Uri
    // Exception table:
    //   from	to	target	type
    //   0	14	67	java/io/FileNotFoundException
    //   18	34	67	java/io/FileNotFoundException
    //   48	64	67	java/io/FileNotFoundException
    //   0	14	76	finally
    //   18	34	76	finally
    //   48	64	76	finally
    //   34	42	85	finally
    //   34	42	89	java/io/FileNotFoundException
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
  
  public static int dimmer(int paramInt, float paramFloat)
  {
    return Color.argb((int)(Color.alpha(paramInt) * paramFloat), Color.red(paramInt), Color.green(paramInt), Color.blue(paramInt));
  }
  
  public static float dipsToPixels(@NonNull Context paramContext, float paramFloat)
  {
    return paramContext.getResources().getDisplayMetrics().density * paramFloat;
  }
  
  public static float dipsToPixelsFloat(@NonNull Context paramContext, int paramInt)
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
      ApptentiveLog.e("Error getting app version code.", paramContext, new Object[0]);
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
      ApptentiveLog.e("Error getting app version name.", paramContext, new Object[0]);
    }
    return null;
  }
  
  public static Drawable getCompatDrawable(Context paramContext, int paramInt)
  {
    try
    {
      paramContext = ContextCompat.getDrawable(paramContext, paramInt);
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
    if (localObject1 == null)
    {
      localObject2 = localObject1;
      if (paramContext != null) {
        localObject2 = paramContext.getCacheDir();
      }
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
      ApptentiveLog.w("Error getting major OS version", localException, new Object[0]);
    }
    return Integer.valueOf(-1);
  }
  
  public static String getMimeTypeFromUri(Context paramContext, Uri paramUri)
  {
    if (paramContext != null) {
      return paramContext.getContentResolver().getType(paramUri);
    }
    return null;
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
  
  private static List<PackageInfo> getPermissions(@NonNull Context paramContext)
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
  
  public static int getResourceIdFromAttribute(Resources.Theme paramTheme, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    if (paramTheme.resolveAttribute(paramInt, localTypedValue, true)) {
      return localTypedValue.resourceId;
    }
    return 0;
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
    if (paramContext == null) {
      return 0;
    }
    return getThemeColor(paramContext.getTheme(), paramInt);
  }
  
  public static int getThemeColor(Resources.Theme paramTheme, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    if (paramTheme.resolveAttribute(paramInt, localTypedValue, true)) {
      return localTypedValue.data;
    }
    return 0;
  }
  
  public static int getThemeColorFromAttrOrRes(Context paramContext, int paramInt1, int paramInt2)
  {
    int i = getThemeColor(paramContext, paramInt1);
    paramInt1 = i;
    if (i == 0) {
      paramInt1 = ContextCompat.getColor(paramContext, paramInt2);
    }
    return paramInt1;
  }
  
  public static int getUtcOffset()
  {
    return TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000;
  }
  
  public static boolean hasPermission(Context paramContext, String paramString)
  {
    if (paramContext == null) {}
    while (paramContext.checkCallingOrSelfPermission(paramString) != 0) {
      return false;
    }
    return true;
  }
  
  public static void hideSoftKeyboard(Context paramContext, View paramView)
  {
    if ((paramContext != null) && (paramView != null)) {
      ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
  }
  
  public static boolean isEmailValid(String paramString)
  {
    return paramString.matches("^[^\\s@]+@[^\\s@]+$");
  }
  
  public static boolean isMimeTypeImage(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return paramString.substring(0, paramString.indexOf("/")).equalsIgnoreCase("Image");
  }
  
  public static boolean isNetworkConnectionPresent()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)ApptentiveInternal.getInstance().getApplicationContext().getSystemService("connectivity");
    return (localConnectivityManager != null) && (localConnectivityManager.getActiveNetworkInfo() != null);
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
        ApptentiveLog.e("Activity not found to open attachment: ", paramContext, new Object[0]);
        return false;
      }
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
    } while (!canLaunchIntent(paramContext, paramString1));
    paramContext.startActivity(paramString1);
    return false;
  }
  
  public static boolean packageHasPermission(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return false;
    }
    return packageHasPermission(paramContext, paramContext.getApplicationContext().getPackageName(), paramString);
  }
  
  public static boolean packageHasPermission(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext == null) {
      return false;
    }
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
      ApptentiveLog.e("Error parsing cache expiration as number: %s", localThrowable, new Object[] { localObject1 });
      i += 1;
    }
  }
  
  /* Error */
  public static Date parseIso8601Date(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 816	java/lang/String:trim	()Ljava/lang/String;
    //   4: ldc_w 828
    //   7: ldc_w 830
    //   10: invokevirtual 834	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   13: ldc_w 836
    //   16: ldc_w 838
    //   19: invokevirtual 834	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   22: astore 4
    //   24: aload 4
    //   26: astore_3
    //   27: aload 4
    //   29: aload 4
    //   31: invokevirtual 527	java/lang/String:length	()I
    //   34: iconst_3
    //   35: isub
    //   36: invokevirtual 842	java/lang/String:charAt	(I)C
    //   39: bipush 58
    //   41: if_icmpne +44 -> 85
    //   44: aload 4
    //   46: ldc_w 407
    //   49: invokevirtual 411	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   52: istore_1
    //   53: new 193	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 323	java/lang/StringBuilder:<init>	()V
    //   60: aload 4
    //   62: iconst_0
    //   63: iload_1
    //   64: invokevirtual 530	java/lang/String:substring	(II)Ljava/lang/String;
    //   67: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload 4
    //   72: iload_1
    //   73: iconst_1
    //   74: iadd
    //   75: invokevirtual 414	java/lang/String:substring	(I)Ljava/lang/String;
    //   78: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 211	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore_3
    //   85: aload_3
    //   86: bipush 46
    //   88: invokevirtual 844	java/lang/String:lastIndexOf	(I)I
    //   91: istore_2
    //   92: aload_3
    //   93: bipush 43
    //   95: invokevirtual 844	java/lang/String:lastIndexOf	(I)I
    //   98: iconst_m1
    //   99: if_icmpeq +124 -> 223
    //   102: aload_3
    //   103: bipush 43
    //   105: invokevirtual 844	java/lang/String:lastIndexOf	(I)I
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
    //   122: invokevirtual 530	java/lang/String:substring	(II)Ljava/lang/String;
    //   125: astore 4
    //   127: aload_3
    //   128: iload_2
    //   129: iconst_1
    //   130: iadd
    //   131: iload_1
    //   132: invokevirtual 530	java/lang/String:substring	(II)Ljava/lang/String;
    //   135: astore 5
    //   137: aload_3
    //   138: iload_1
    //   139: invokevirtual 414	java/lang/String:substring	(I)Ljava/lang/String;
    //   142: astore_3
    //   143: ldc_w 846
    //   146: iconst_1
    //   147: anewarray 4	java/lang/Object
    //   150: dup
    //   151: iconst_0
    //   152: aload 5
    //   154: aastore
    //   155: invokestatic 128	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   158: ldc_w 838
    //   161: ldc_w 848
    //   164: invokevirtual 834	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   167: astore 5
    //   169: new 193	java/lang/StringBuilder
    //   172: dup
    //   173: invokespecial 323	java/lang/StringBuilder:<init>	()V
    //   176: aload 4
    //   178: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: aload 5
    //   183: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: aload_3
    //   187: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 211	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: astore 4
    //   195: aload 4
    //   197: ldc_w 850
    //   200: invokevirtual 853	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   203: ifeq +64 -> 267
    //   206: new 277	java/text/SimpleDateFormat
    //   209: dup
    //   210: ldc 11
    //   212: invokespecial 278	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   215: aload 4
    //   217: invokevirtual 855	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   220: astore_0
    //   221: aload_0
    //   222: areturn
    //   223: aload_3
    //   224: bipush 45
    //   226: invokevirtual 844	java/lang/String:lastIndexOf	(I)I
    //   229: istore_1
    //   230: goto -121 -> 109
    //   233: astore_3
    //   234: new 193	java/lang/StringBuilder
    //   237: dup
    //   238: ldc_w 857
    //   241: invokespecial 196	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   244: aload_0
    //   245: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: invokevirtual 211	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   251: aload_3
    //   252: iconst_0
    //   253: anewarray 4	java/lang/Object
    //   256: invokestatic 370	com/apptentive/android/sdk/ApptentiveLog:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   259: new 280	java/util/Date
    //   262: dup
    //   263: invokespecial 858	java/util/Date:<init>	()V
    //   266: areturn
    //   267: new 277	java/text/SimpleDateFormat
    //   270: dup
    //   271: ldc 8
    //   273: invokespecial 278	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   276: aload 4
    //   278: invokevirtual 855	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   281: astore_0
    //   282: aload_0
    //   283: areturn
    //   284: astore_0
    //   285: new 193	java/lang/StringBuilder
    //   288: dup
    //   289: ldc_w 860
    //   292: invokespecial 196	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   295: aload 4
    //   297: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: invokevirtual 211	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   303: aload_0
    //   304: iconst_0
    //   305: anewarray 4	java/lang/Object
    //   308: invokestatic 370	com/apptentive/android/sdk/ApptentiveLog:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   311: aconst_null
    //   312: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	313	0	paramString	String
    //   52	178	1	i	int
    //   91	40	2	j	int
    //   26	198	3	localObject1	Object
    //   233	19	3	localException	Exception
    //   22	274	4	localObject2	Object
    //   135	47	5	str	String
    // Exception table:
    //   from	to	target	type
    //   27	85	233	java/lang/Exception
    //   85	109	233	java/lang/Exception
    //   117	195	233	java/lang/Exception
    //   223	230	233	java/lang/Exception
    //   195	221	284	java/text/ParseException
    //   267	282	284	java/text/ParseException
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
  
  public static int pixelsToDips(@NonNull Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return Math.round(paramInt / f);
  }
  
  public static void printDebugInfo(Context paramContext)
  {
    Point localPoint = getScreenSize(paramContext);
    ApptentiveLog.d("Screen size: PX=%dx%d DP=%dx%d", new Object[] { Integer.valueOf(localPoint.x), Integer.valueOf(localPoint.y), Integer.valueOf(pixelsToDips(paramContext, localPoint.x)), Integer.valueOf(pixelsToDips(paramContext, localPoint.y)) });
  }
  
  /* Error */
  public static String readStringFromInputStream(java.io.InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 193	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 323	java/lang/StringBuilder:<init>	()V
    //   9: astore 4
    //   11: sipush 8196
    //   14: newarray char
    //   16: astore 5
    //   18: new 902	java/io/InputStreamReader
    //   21: dup
    //   22: aload_0
    //   23: aload_1
    //   24: invokespecial 905	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   27: astore_0
    //   28: aload_0
    //   29: aload 5
    //   31: iconst_0
    //   32: sipush 8196
    //   35: invokevirtual 910	java/io/Reader:read	([CII)I
    //   38: istore_2
    //   39: iload_2
    //   40: iflt +27 -> 67
    //   43: aload 4
    //   45: aload 5
    //   47: iconst_0
    //   48: iload_2
    //   49: invokevirtual 913	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   52: pop
    //   53: goto -25 -> 28
    //   56: astore_1
    //   57: aload_0
    //   58: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   61: aload 4
    //   63: invokevirtual 211	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: areturn
    //   67: aload_0
    //   68: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   71: goto -10 -> 61
    //   74: astore_1
    //   75: aload_3
    //   76: astore_0
    //   77: aload_0
    //   78: invokestatic 161	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: goto -7 -> 77
    //   87: astore_0
    //   88: aconst_null
    //   89: astore_0
    //   90: goto -33 -> 57
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramInputStream	java.io.InputStream
    //   0	93	1	paramString	String
    //   38	11	2	i	int
    //   1	75	3	localObject	Object
    //   9	53	4	localStringBuilder	StringBuilder
    //   16	30	5	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   28	39	56	java/lang/Exception
    //   43	53	56	java/lang/Exception
    //   18	28	74	finally
    //   28	39	83	finally
    //   43	53	83	finally
    //   18	28	87	java/lang/Exception
  }
  
  public static void replaceDefaultFont(Context paramContext, String paramString)
  {
    Object localObject = null;
    Typeface localTypeface = Typeface.createFromAsset(paramContext.getAssets(), paramString);
    TypedValue localTypedValue = new TypedValue();
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramContext = localObject;
      if (ApptentiveInternal.getInstance().getApptentiveTheme().resolveAttribute(R.attr.apptentiveFontFamilyDefault, localTypedValue, true))
      {
        paramContext = new HashMap();
        paramContext.put(localTypedValue.string.toString(), localTypeface);
      }
      paramString = paramContext;
      if (ApptentiveInternal.getInstance().getApptentiveTheme().resolveAttribute(R.attr.apptentiveFontFamilyMediumDefault, localTypedValue, true))
      {
        paramString = paramContext;
        if (paramContext == null) {
          paramString = new HashMap();
        }
        paramString.put(localTypedValue.string.toString(), localTypeface);
      }
      if (paramString == null) {}
    }
    while (!ApptentiveInternal.getInstance().getApptentiveTheme().resolveAttribute(R.attr.apptentiveTypefaceDefault, localTypedValue, true)) {
      try
      {
        paramContext = Typeface.class.getDeclaredField("sSystemFontMap");
        paramContext.setAccessible(true);
        paramContext.set(null, paramString);
        return;
      }
      catch (NoSuchFieldException paramContext)
      {
        ApptentiveLog.e("Exception replacing system font", paramContext, new Object[0]);
        return;
      }
      catch (IllegalAccessException paramContext)
      {
        ApptentiveLog.e("Exception replacing system font", paramContext, new Object[0]);
        return;
      }
    }
    paramString = "DEFAULT";
    if (localTypedValue.data == paramContext.getResources().getInteger(R.integer.apptentive_typeface_monospace)) {
      paramString = "MONOSPACE";
    }
    try
    {
      paramContext = Typeface.class.getDeclaredField(paramString);
      paramContext.setAccessible(true);
      paramContext.set(null, localTypeface);
      return;
    }
    catch (NoSuchFieldException paramContext)
    {
      for (;;)
      {
        ApptentiveLog.e("Exception replacing system font", paramContext, new Object[0]);
        return;
        if (localTypedValue.data == paramContext.getResources().getInteger(R.integer.apptentive_typeface_serif)) {
          paramString = "SERIF";
        } else if (localTypedValue.data == paramContext.getResources().getInteger(R.integer.apptentive_typeface_sans)) {
          paramString = "SANS_SERIF";
        }
      }
    }
    catch (IllegalAccessException paramContext)
    {
      ApptentiveLog.e("Exception replacing system font", paramContext, new Object[0]);
    }
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
    if ((paramActivity != null) && (paramActivity.getCurrentFocus() != null)) {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).showSoftInput(paramView, 0);
    }
  }
  
  public static String stackTraceAsString(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
    return localStringWriter.toString();
  }
  
  public static String trim(String paramString)
  {
    if (paramString != null) {
      return paramString.trim();
    }
    return null;
  }
}
