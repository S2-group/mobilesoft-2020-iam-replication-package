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
  
  public static int alphaMixColors(int paramInt1, int paramInt2)
  {
    double d2 = (paramInt1 >> 24 & 0xFF) / 255.0D;
    double d3 = (paramInt2 >> 24 & 0xFF) / 255.0D;
    double d1 = d3 + (1.0D - d3) * d2;
    d2 = (1.0D - d3) * d2 / d1;
    d3 = d2 / d1;
    return ((int)(255.0D * d1) & 0xFF) << 24 | ((int)((paramInt1 >> 16 & 0xFF) * d2 + (paramInt2 >> 16 & 0xFF) * d3) & 0xFF) << 16 | ((int)((paramInt1 >> 8 & 0xFF) * d2 + (paramInt2 >> 8 & 0xFF) * d3) & 0xFF) << 8 | ((int)((paramInt1 & 0xFF) * d2 + (paramInt2 & 0xFF) * d3) & 0xFF) << 0;
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
    int j = 0;
    int i = 0;
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
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 9
    //   6: aconst_null
    //   7: astore 8
    //   9: aconst_null
    //   10: astore 4
    //   12: aconst_null
    //   13: astore 6
    //   15: aconst_null
    //   16: astore 7
    //   18: iconst_0
    //   19: istore_2
    //   20: iconst_0
    //   21: istore_3
    //   22: new 124	java/io/File
    //   25: dup
    //   26: aload_0
    //   27: invokespecial 127	java/io/File:<init>	(Ljava/lang/String;)V
    //   30: invokevirtual 131	java/io/File:exists	()Z
    //   33: ifeq +75 -> 108
    //   36: new 133	java/io/FileInputStream
    //   39: dup
    //   40: aload_0
    //   41: invokespecial 134	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   44: astore_0
    //   45: new 136	java/io/FileOutputStream
    //   48: dup
    //   49: aload_1
    //   50: invokespecial 137	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   53: astore_1
    //   54: sipush 1444
    //   57: newarray byte
    //   59: astore 4
    //   61: iload_3
    //   62: istore_2
    //   63: aload_0
    //   64: aload 4
    //   66: invokevirtual 143	java/io/InputStream:read	([B)I
    //   69: istore_3
    //   70: iload_3
    //   71: iconst_m1
    //   72: if_icmpeq +30 -> 102
    //   75: iload_2
    //   76: iload_3
    //   77: iadd
    //   78: istore_2
    //   79: aload_1
    //   80: aload 4
    //   82: iconst_0
    //   83: iload_3
    //   84: invokevirtual 147	java/io/FileOutputStream:write	([BII)V
    //   87: goto -24 -> 63
    //   90: astore 4
    //   92: aload_0
    //   93: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   96: aload_1
    //   97: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   100: iconst_0
    //   101: ireturn
    //   102: aload_0
    //   103: astore 5
    //   105: aload_1
    //   106: astore 4
    //   108: aload 5
    //   110: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   113: aload 4
    //   115: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   118: iload_2
    //   119: ireturn
    //   120: astore_1
    //   121: aload 9
    //   123: astore_0
    //   124: aload 6
    //   126: astore 4
    //   128: aload_0
    //   129: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   132: aload 4
    //   134: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   137: aload_1
    //   138: athrow
    //   139: astore_1
    //   140: aload 6
    //   142: astore 4
    //   144: goto -16 -> 128
    //   147: astore 5
    //   149: aload_1
    //   150: astore 4
    //   152: aload 5
    //   154: astore_1
    //   155: goto -27 -> 128
    //   158: astore_0
    //   159: aload 7
    //   161: astore_1
    //   162: aload 8
    //   164: astore_0
    //   165: goto -73 -> 92
    //   168: astore_1
    //   169: aload 7
    //   171: astore_1
    //   172: goto -80 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	175	0	paramString1	String
    //   0	175	1	paramString2	String
    //   19	100	2	i	int
    //   21	63	3	j	int
    //   10	71	4	arrayOfByte	byte[]
    //   90	1	4	localException	Exception
    //   106	45	4	localObject1	Object
    //   1	108	5	str	String
    //   147	6	5	localObject2	Object
    //   13	128	6	localObject3	Object
    //   16	154	7	localObject4	Object
    //   7	156	8	localObject5	Object
    //   4	118	9	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   54	61	90	java/lang/Exception
    //   63	70	90	java/lang/Exception
    //   79	87	90	java/lang/Exception
    //   22	45	120	finally
    //   45	54	139	finally
    //   54	61	147	finally
    //   63	70	147	finally
    //   79	87	147	finally
    //   22	45	158	java/lang/Exception
    //   45	54	168	java/lang/Exception
  }
  
  /* Error */
  public static com.apptentive.android.sdk.model.StoredFile createLocalStoredFile(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 6
    //   6: aload 6
    //   8: astore 4
    //   10: aload 7
    //   12: astore 5
    //   14: aload_1
    //   15: invokestatic 161	android/webkit/URLUtil:isContentUrl	(Ljava/lang/String;)Z
    //   18: ifeq +59 -> 77
    //   21: aload_0
    //   22: ifnull +55 -> 77
    //   25: aload 6
    //   27: astore 4
    //   29: aload 7
    //   31: astore 5
    //   33: aload_1
    //   34: invokestatic 167	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   37: astore 8
    //   39: aload 6
    //   41: astore 4
    //   43: aload 7
    //   45: astore 5
    //   47: aload_0
    //   48: invokevirtual 171	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   51: aload 8
    //   53: invokevirtual 177	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   56: astore_0
    //   57: aload_0
    //   58: astore 4
    //   60: aload_0
    //   61: astore 5
    //   63: aload_0
    //   64: aload_1
    //   65: aload_2
    //   66: aload_3
    //   67: invokestatic 180	com/apptentive/android/sdk/util/Util:createLocalStoredFile	(Ljava/io/InputStream;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/apptentive/android/sdk/model/StoredFile;
    //   70: astore_1
    //   71: aload_0
    //   72: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   75: aload_1
    //   76: areturn
    //   77: aload 6
    //   79: astore 4
    //   81: aload 7
    //   83: astore 5
    //   85: new 133	java/io/FileInputStream
    //   88: dup
    //   89: new 124	java/io/File
    //   92: dup
    //   93: aload_1
    //   94: invokespecial 127	java/io/File:<init>	(Ljava/lang/String;)V
    //   97: invokespecial 183	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   100: astore_0
    //   101: goto -44 -> 57
    //   104: astore_0
    //   105: aload 4
    //   107: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   110: aconst_null
    //   111: areturn
    //   112: astore_0
    //   113: aload 5
    //   115: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   118: aload_0
    //   119: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	paramContext	Context
    //   0	120	1	paramString1	String
    //   0	120	2	paramString2	String
    //   0	120	3	paramString3	String
    //   8	98	4	localObject1	Object
    //   12	102	5	localObject2	Object
    //   4	74	6	localObject3	Object
    //   1	81	7	localObject4	Object
    //   37	15	8	localUri	Uri
    // Exception table:
    //   from	to	target	type
    //   14	21	104	java/io/FileNotFoundException
    //   33	39	104	java/io/FileNotFoundException
    //   47	57	104	java/io/FileNotFoundException
    //   63	71	104	java/io/FileNotFoundException
    //   85	101	104	java/io/FileNotFoundException
    //   14	21	112	finally
    //   33	39	112	finally
    //   47	57	112	finally
    //   63	71	112	finally
    //   85	101	112	finally
  }
  
  /* Error */
  public static com.apptentive.android.sdk.model.StoredFile createLocalStoredFile(java.io.InputStream paramInputStream, String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aconst_null
    //   7: astore 5
    //   9: aconst_null
    //   10: astore 10
    //   12: aconst_null
    //   13: astore 9
    //   15: aconst_null
    //   16: astore 14
    //   18: aconst_null
    //   19: astore 13
    //   21: aconst_null
    //   22: astore 11
    //   24: aconst_null
    //   25: astore 15
    //   27: aconst_null
    //   28: astore 12
    //   30: aload 14
    //   32: astore 6
    //   34: aload 5
    //   36: astore 7
    //   38: aload 15
    //   40: astore 8
    //   42: new 124	java/io/File
    //   45: dup
    //   46: aload_2
    //   47: invokespecial 127	java/io/File:<init>	(Ljava/lang/String;)V
    //   50: astore 16
    //   52: aload 14
    //   54: astore 6
    //   56: aload 5
    //   58: astore 7
    //   60: aload 15
    //   62: astore 8
    //   64: aload 16
    //   66: invokevirtual 131	java/io/File:exists	()Z
    //   69: ifeq +21 -> 90
    //   72: aload 14
    //   74: astore 6
    //   76: aload 5
    //   78: astore 7
    //   80: aload 15
    //   82: astore 8
    //   84: aload 16
    //   86: invokevirtual 188	java/io/File:delete	()Z
    //   89: pop
    //   90: aload 14
    //   92: astore 6
    //   94: aload 5
    //   96: astore 7
    //   98: aload 15
    //   100: astore 8
    //   102: new 136	java/io/FileOutputStream
    //   105: dup
    //   106: aload 16
    //   108: invokespecial 189	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   111: astore 5
    //   113: new 191	java/io/BufferedOutputStream
    //   116: dup
    //   117: aload 5
    //   119: invokespecial 194	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   122: astore 6
    //   124: new 196	com/apptentive/android/sdk/util/CountingOutputStream
    //   127: dup
    //   128: aload 6
    //   130: invokespecial 197	com/apptentive/android/sdk/util/CountingOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   133: astore 7
    //   135: sipush 2048
    //   138: newarray byte
    //   140: astore 8
    //   142: aload_0
    //   143: aload 8
    //   145: iconst_0
    //   146: sipush 2048
    //   149: invokevirtual 200	java/io/InputStream:read	([BII)I
    //   152: istore 4
    //   154: iload 4
    //   156: iconst_m1
    //   157: if_icmpeq +58 -> 215
    //   160: aload 7
    //   162: aload 8
    //   164: iconst_0
    //   165: iload 4
    //   167: invokevirtual 201	com/apptentive/android/sdk/util/CountingOutputStream:write	([BII)V
    //   170: goto -28 -> 142
    //   173: astore_0
    //   174: aload 5
    //   176: astore_0
    //   177: aload 6
    //   179: astore_1
    //   180: aload 7
    //   182: astore_2
    //   183: aload_1
    //   184: astore 6
    //   186: aload_2
    //   187: astore 7
    //   189: aload_0
    //   190: astore 8
    //   192: ldc -53
    //   194: iconst_0
    //   195: anewarray 4	java/lang/Object
    //   198: invokestatic 209	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   201: aload_2
    //   202: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   205: aload_1
    //   206: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   209: aload_0
    //   210: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   213: aconst_null
    //   214: areturn
    //   215: new 211	java/lang/StringBuilder
    //   218: dup
    //   219: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   222: ldc -42
    //   224: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: aload 7
    //   229: invokevirtual 221	com/apptentive/android/sdk/util/CountingOutputStream:getBytesWritten	()I
    //   232: sipush 1024
    //   235: idiv
    //   236: invokevirtual 224	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   239: ldc -30
    //   241: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: iconst_0
    //   248: anewarray 4	java/lang/Object
    //   251: invokestatic 232	com/apptentive/android/sdk/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   254: aload 7
    //   256: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   259: aload 6
    //   261: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   264: aload 5
    //   266: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   269: new 234	com/apptentive/android/sdk/model/StoredFile
    //   272: dup
    //   273: invokespecial 235	com/apptentive/android/sdk/model/StoredFile:<init>	()V
    //   276: astore_0
    //   277: aload_0
    //   278: aload_1
    //   279: invokevirtual 238	com/apptentive/android/sdk/model/StoredFile:setSourceUriOrPath	(Ljava/lang/String;)V
    //   282: aload_0
    //   283: aload_2
    //   284: invokevirtual 241	com/apptentive/android/sdk/model/StoredFile:setLocalFilePath	(Ljava/lang/String;)V
    //   287: aload_0
    //   288: aload_3
    //   289: invokevirtual 244	com/apptentive/android/sdk/model/StoredFile:setMimeType	(Ljava/lang/String;)V
    //   292: aload_0
    //   293: areturn
    //   294: astore_2
    //   295: aload 8
    //   297: astore_1
    //   298: aload 6
    //   300: astore_0
    //   301: aload 7
    //   303: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   306: aload_0
    //   307: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   310: aload_1
    //   311: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   314: aload_2
    //   315: athrow
    //   316: astore_2
    //   317: aload 5
    //   319: astore_1
    //   320: aload 13
    //   322: astore_0
    //   323: aload 10
    //   325: astore 7
    //   327: goto -26 -> 301
    //   330: astore_2
    //   331: aload 5
    //   333: astore_1
    //   334: aload 6
    //   336: astore_0
    //   337: aload 10
    //   339: astore 7
    //   341: goto -40 -> 301
    //   344: astore_2
    //   345: aload 5
    //   347: astore_1
    //   348: aload 6
    //   350: astore_0
    //   351: goto -50 -> 301
    //   354: astore_0
    //   355: aload 11
    //   357: astore_1
    //   358: aload 9
    //   360: astore_2
    //   361: aload 12
    //   363: astore_0
    //   364: goto -181 -> 183
    //   367: astore_0
    //   368: aload 5
    //   370: astore_0
    //   371: aload 11
    //   373: astore_1
    //   374: aload 9
    //   376: astore_2
    //   377: goto -194 -> 183
    //   380: astore_0
    //   381: aload 5
    //   383: astore_0
    //   384: aload 6
    //   386: astore_1
    //   387: aload 9
    //   389: astore_2
    //   390: goto -207 -> 183
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	393	0	paramInputStream	java.io.InputStream
    //   0	393	1	paramString1	String
    //   0	393	2	paramString2	String
    //   0	393	3	paramString3	String
    //   152	14	4	i	int
    //   7	375	5	localFileOutputStream	java.io.FileOutputStream
    //   32	353	6	localObject1	Object
    //   36	304	7	localObject2	Object
    //   40	256	8	localObject3	Object
    //   13	375	9	localObject4	Object
    //   10	328	10	localObject5	Object
    //   22	350	11	localObject6	Object
    //   28	334	12	localObject7	Object
    //   19	302	13	localObject8	Object
    //   16	75	14	localObject9	Object
    //   25	74	15	localObject10	Object
    //   50	57	16	localFile	File
    // Exception table:
    //   from	to	target	type
    //   135	142	173	java/io/IOException
    //   142	154	173	java/io/IOException
    //   160	170	173	java/io/IOException
    //   215	254	173	java/io/IOException
    //   42	52	294	finally
    //   64	72	294	finally
    //   84	90	294	finally
    //   102	113	294	finally
    //   192	201	294	finally
    //   113	124	316	finally
    //   124	135	330	finally
    //   135	142	344	finally
    //   142	154	344	finally
    //   160	170	344	finally
    //   215	254	344	finally
    //   42	52	354	java/io/IOException
    //   64	72	354	java/io/IOException
    //   84	90	354	java/io/IOException
    //   102	113	354	java/io/IOException
    //   113	124	367	java/io/IOException
    //   124	135	380	java/io/IOException
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
  
  public static int dipsToPixels(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return Math.round(paramInt * f);
  }
  
  public static float dipsToPixelsFloat(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return paramInt * f;
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
    if (paramString2 == null) {}
    for (paramString1 = "apptentive-api-file-" + paramString1;; paramString1 = paramString2) {
      return new File(getDiskCacheDir(paramContext), paramString1).getPath();
    }
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
    if (!hasPermission(paramContext, "android.permission.READ_EXTERNAL_STORAGE")) {}
    do
    {
      do
      {
        return 0L;
        paramUri = paramContext.getContentResolver().query(paramUri, null, null, null, null);
      } while ((paramUri == null) || (!paramUri.moveToFirst()));
      String str = paramUri.getString(0);
      str = str.substring(str.lastIndexOf(":") + 1);
      paramUri.close();
      paramContext = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, "_id = ? ", new String[] { str }, null);
    } while ((paramContext == null) || (!paramContext.moveToFirst()));
    long l = paramContext.getLong(paramContext.getColumnIndex("date_added"));
    paramContext.close();
    return l;
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
    if ((("mounted".equals(Environment.getExternalStorageState())) || (!Environment.isExternalStorageRemovable())) && (hasPermission(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE")))
    {
      paramString1 = new File(paramString2);
      if (paramString1.exists())
      {
        Object localObject = paramString1.getName();
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
        if (copyFile(paramString2, str) == 0) {
          return false;
        }
        paramString1.setDataAndType(Uri.fromFile((File)localObject), paramString3);
        try
        {
          paramContext.startActivity(paramString1);
          return true;
        }
        catch (ActivityNotFoundException paramContext)
        {
          Log.e("Activity not found to open attachment: ", paramContext, new Object[0]);
        }
      }
    }
    for (;;)
    {
      return false;
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
      if (canLaunchIntent(paramContext, paramString1)) {
        paramContext.startActivity(paramString1);
      }
    }
  }
  
  public static boolean packageHasPermission(Context paramContext, String paramString)
  {
    return packageHasPermission(paramContext, paramContext.getApplicationContext().getPackageName(), paramString);
  }
  
  public static boolean packageHasPermission(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    paramContext = getPermissions(paramContext).iterator();
    for (;;)
    {
      boolean bool1 = bool2;
      Object localObject;
      int j;
      int i;
      if (paramContext.hasNext())
      {
        localObject = (PackageInfo)paramContext.next();
        if ((((PackageInfo)localObject).packageName.equals(paramString1)) && (((PackageInfo)localObject).requestedPermissions != null))
        {
          localObject = ((PackageInfo)localObject).requestedPermissions;
          j = localObject.length;
          i = 0;
        }
      }
      else
      {
        while (i < j)
        {
          if (localObject[i].equals(paramString2))
          {
            bool1 = true;
            return bool1;
          }
          i += 1;
        }
      }
    }
  }
  
  public static Integer parseCacheControlHeader(String paramString)
  {
    if (paramString != null)
    {
      paramString = paramString.substring(paramString.indexOf("[") + 1, paramString.lastIndexOf("]")).split(",");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramString[i].trim();
        if (((String)localObject).startsWith("max-age="))
        {
          localObject = ((String)localObject).split("=");
          if (localObject.length == 2)
          {
            localObject = localObject[1];
            try
            {
              int k = Integer.parseInt((String)localObject);
              return Integer.valueOf(k);
            }
            catch (NumberFormatException localNumberFormatException)
            {
              Log.e("Error parsing cache expiration as number: %s", localNumberFormatException, new Object[] { localObject });
            }
          }
        }
        i += 1;
      }
    }
    return null;
  }
  
  /* Error */
  public static Date parseIso8601Date(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 807	java/lang/String:trim	()Ljava/lang/String;
    //   4: ldc_w 819
    //   7: ldc_w 821
    //   10: invokevirtual 825	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   13: ldc_w 827
    //   16: ldc_w 829
    //   19: invokevirtual 825	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   22: astore 4
    //   24: aload 4
    //   26: astore_3
    //   27: aload 4
    //   29: aload 4
    //   31: invokevirtual 520	java/lang/String:length	()I
    //   34: iconst_3
    //   35: isub
    //   36: invokevirtual 833	java/lang/String:charAt	(I)C
    //   39: bipush 58
    //   41: if_icmpne +44 -> 85
    //   44: aload 4
    //   46: ldc_w 402
    //   49: invokevirtual 406	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   52: istore_1
    //   53: new 211	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   60: aload 4
    //   62: iconst_0
    //   63: iload_1
    //   64: invokevirtual 523	java/lang/String:substring	(II)Ljava/lang/String;
    //   67: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload 4
    //   72: iload_1
    //   73: iconst_1
    //   74: iadd
    //   75: invokevirtual 409	java/lang/String:substring	(I)Ljava/lang/String;
    //   78: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore_3
    //   85: aload_3
    //   86: bipush 46
    //   88: invokevirtual 835	java/lang/String:lastIndexOf	(I)I
    //   91: istore_2
    //   92: aload_3
    //   93: bipush 43
    //   95: invokevirtual 835	java/lang/String:lastIndexOf	(I)I
    //   98: iconst_m1
    //   99: if_icmpeq +124 -> 223
    //   102: aload_3
    //   103: bipush 43
    //   105: invokevirtual 835	java/lang/String:lastIndexOf	(I)I
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
    //   122: invokevirtual 523	java/lang/String:substring	(II)Ljava/lang/String;
    //   125: astore 4
    //   127: aload_3
    //   128: iload_2
    //   129: iconst_1
    //   130: iadd
    //   131: iload_1
    //   132: invokevirtual 523	java/lang/String:substring	(II)Ljava/lang/String;
    //   135: astore 5
    //   137: aload_3
    //   138: iload_1
    //   139: invokevirtual 409	java/lang/String:substring	(I)Ljava/lang/String;
    //   142: astore_3
    //   143: ldc_w 837
    //   146: iconst_1
    //   147: anewarray 4	java/lang/Object
    //   150: dup
    //   151: iconst_0
    //   152: aload 5
    //   154: aastore
    //   155: invokestatic 118	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   158: ldc_w 829
    //   161: ldc_w 839
    //   164: invokevirtual 825	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   167: astore 5
    //   169: new 211	java/lang/StringBuilder
    //   172: dup
    //   173: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   176: aload 4
    //   178: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: aload 5
    //   183: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: aload_3
    //   187: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: astore 4
    //   195: aload 4
    //   197: ldc_w 841
    //   200: invokevirtual 844	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   203: ifeq +67 -> 270
    //   206: new 258	java/text/SimpleDateFormat
    //   209: dup
    //   210: ldc 11
    //   212: invokespecial 259	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   215: aload 4
    //   217: invokevirtual 846	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   220: astore_0
    //   221: aload_0
    //   222: areturn
    //   223: aload_3
    //   224: bipush 45
    //   226: invokevirtual 835	java/lang/String:lastIndexOf	(I)I
    //   229: istore_1
    //   230: goto -121 -> 109
    //   233: astore_3
    //   234: new 211	java/lang/StringBuilder
    //   237: dup
    //   238: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   241: ldc_w 848
    //   244: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: aload_0
    //   248: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   254: aload_3
    //   255: iconst_0
    //   256: anewarray 4	java/lang/Object
    //   259: invokestatic 354	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   262: new 261	java/util/Date
    //   265: dup
    //   266: invokespecial 849	java/util/Date:<init>	()V
    //   269: areturn
    //   270: new 258	java/text/SimpleDateFormat
    //   273: dup
    //   274: ldc 8
    //   276: invokespecial 259	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   279: aload 4
    //   281: invokevirtual 846	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   284: astore_0
    //   285: aload_0
    //   286: areturn
    //   287: astore_0
    //   288: new 211	java/lang/StringBuilder
    //   291: dup
    //   292: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   295: ldc_w 851
    //   298: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: aload 4
    //   303: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: aload_0
    //   310: iconst_0
    //   311: anewarray 4	java/lang/Object
    //   314: invokestatic 354	com/apptentive/android/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
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
          paramString = Integer.valueOf(i >>> 8 | (j & 0xFF) << 24);
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
    //   2: aconst_null
    //   3: astore 4
    //   5: new 211	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   12: astore 5
    //   14: sipush 8196
    //   17: newarray char
    //   19: astore 6
    //   21: new 887	java/io/InputStreamReader
    //   24: dup
    //   25: aload_0
    //   26: aload_1
    //   27: invokespecial 890	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   30: astore_0
    //   31: aload_0
    //   32: aload 6
    //   34: iconst_0
    //   35: sipush 8196
    //   38: invokevirtual 895	java/io/Reader:read	([CII)I
    //   41: istore_2
    //   42: iload_2
    //   43: ifge +13 -> 56
    //   46: aload_0
    //   47: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   50: aload 5
    //   52: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: areturn
    //   56: aload 5
    //   58: aload 6
    //   60: iconst_0
    //   61: iload_2
    //   62: invokevirtual 898	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   65: pop
    //   66: goto -35 -> 31
    //   69: astore_1
    //   70: aload_0
    //   71: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   74: goto -24 -> 50
    //   77: astore_1
    //   78: aload_3
    //   79: astore_0
    //   80: aload_0
    //   81: invokestatic 151	com/apptentive/android/sdk/util/Util:ensureClosed	(Ljava/io/Closeable;)V
    //   84: aload_1
    //   85: athrow
    //   86: astore_1
    //   87: goto -7 -> 80
    //   90: astore_0
    //   91: aload 4
    //   93: astore_0
    //   94: goto -24 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	paramInputStream	java.io.InputStream
    //   0	97	1	paramString	String
    //   41	21	2	i	int
    //   1	78	3	localObject1	Object
    //   3	89	4	localObject2	Object
    //   12	45	5	localStringBuilder	StringBuilder
    //   19	40	6	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   31	42	69	java/lang/Exception
    //   56	66	69	java/lang/Exception
    //   21	31	77	finally
    //   31	42	86	finally
    //   56	66	86	finally
    //   21	31	90	java/lang/Exception
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
