package com.intsig.camscanner.a;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;
import com.intsig.camscanner.ScannerApplication;
import com.intsig.camscanner.service.ImageRegisterService;
import com.intsig.datastruct.EduMsg;
import com.intsig.nativelib.PinyinUtil;
import com.intsig.payment.am;
import com.intsig.scanner.ScannerEngine;
import com.intsig.scanner.ScannerUtils;
import com.intsig.tsapp.LoginActivity;
import com.intsig.tsapp.collaborate.ao;
import com.intsig.tsapp.sync.av;
import com.intsig.util.bg;
import com.intsig.util.bo;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class j
{
  private static boolean a = false;
  private static Properties b;
  
  /* Error */
  public static android.graphics.Bitmap a(Resources paramResources, int paramInt, android.graphics.Bitmap.Config paramConfig)
  {
    // Byte code:
    //   0: new 21	android/graphics/BitmapFactory$Options
    //   3: dup
    //   4: invokespecial 24	android/graphics/BitmapFactory$Options:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: aload_2
    //   10: putfield 28	android/graphics/BitmapFactory$Options:inPreferredConfig	Landroid/graphics/Bitmap$Config;
    //   13: aload_3
    //   14: iconst_1
    //   15: putfield 31	android/graphics/BitmapFactory$Options:inInputShareable	Z
    //   18: aload_3
    //   19: iconst_1
    //   20: putfield 34	android/graphics/BitmapFactory$Options:inPurgeable	Z
    //   23: aload_0
    //   24: iload_1
    //   25: invokevirtual 40	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   28: astore_2
    //   29: aload_2
    //   30: astore_0
    //   31: aload_2
    //   32: invokestatic 46	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   35: astore_3
    //   36: aload_3
    //   37: astore_0
    //   38: aload_2
    //   39: ifnull +69 -> 108
    //   42: aload_2
    //   43: invokevirtual 51	java/io/InputStream:close	()V
    //   46: aload_3
    //   47: areturn
    //   48: astore_2
    //   49: goto +61 -> 110
    //   52: astore_3
    //   53: goto +16 -> 69
    //   56: astore_3
    //   57: goto +34 -> 91
    //   60: astore_2
    //   61: aconst_null
    //   62: astore_0
    //   63: goto +47 -> 110
    //   66: astore_3
    //   67: aconst_null
    //   68: astore_2
    //   69: aload_2
    //   70: astore_0
    //   71: ldc 53
    //   73: aload_3
    //   74: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   77: aload_2
    //   78: ifnull +28 -> 106
    //   81: aload_2
    //   82: invokevirtual 51	java/io/InputStream:close	()V
    //   85: goto +21 -> 106
    //   88: astore_3
    //   89: aconst_null
    //   90: astore_2
    //   91: aload_2
    //   92: astore_0
    //   93: ldc 53
    //   95: aload_3
    //   96: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   99: aload_2
    //   100: ifnull +6 -> 106
    //   103: goto -22 -> 81
    //   106: aconst_null
    //   107: astore_0
    //   108: aload_0
    //   109: areturn
    //   110: aload_0
    //   111: ifnull +7 -> 118
    //   114: aload_0
    //   115: invokevirtual 51	java/io/InputStream:close	()V
    //   118: aload_2
    //   119: athrow
    //   120: astore_0
    //   121: aload_3
    //   122: areturn
    //   123: astore_0
    //   124: goto -18 -> 106
    //   127: astore_0
    //   128: goto -10 -> 118
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	paramResources	Resources
    //   0	131	1	paramInt	int
    //   0	131	2	paramConfig	android.graphics.Bitmap.Config
    //   7	40	3	localObject	Object
    //   52	1	3	localRuntimeException1	RuntimeException
    //   56	1	3	localOutOfMemoryError1	OutOfMemoryError
    //   66	8	3	localRuntimeException2	RuntimeException
    //   88	34	3	localOutOfMemoryError2	OutOfMemoryError
    // Exception table:
    //   from	to	target	type
    //   31	36	48	finally
    //   71	77	48	finally
    //   93	99	48	finally
    //   31	36	52	java/lang/RuntimeException
    //   31	36	56	java/lang/OutOfMemoryError
    //   23	29	60	finally
    //   23	29	66	java/lang/RuntimeException
    //   23	29	88	java/lang/OutOfMemoryError
    //   42	46	120	java/lang/Exception
    //   81	85	123	java/lang/Exception
    //   114	118	127	java/lang/Exception
  }
  
  /* Error */
  public static android.net.Uri a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 61	java/io/File
    //   3: dup
    //   4: getstatic 67	com/intsig/util/a:j	Ljava/lang/String;
    //   7: invokespecial 70	java/io/File:<init>	(Ljava/lang/String;)V
    //   10: astore 11
    //   12: aload 11
    //   14: new 72	com/intsig/camscanner/a/r
    //   17: dup
    //   18: invokespecial 73	com/intsig/camscanner/a/r:<init>	()V
    //   21: invokevirtual 77	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
    //   24: astore 10
    //   26: aload 11
    //   28: invokevirtual 81	java/io/File:exists	()Z
    //   31: ifeq +11 -> 42
    //   34: aload 11
    //   36: invokevirtual 84	java/io/File:isFile	()Z
    //   39: ifeq +52 -> 91
    //   42: aload 11
    //   44: invokevirtual 87	java/io/File:delete	()Z
    //   47: pop
    //   48: aload 11
    //   50: invokevirtual 90	java/io/File:mkdirs	()Z
    //   53: istore 4
    //   55: new 92	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   62: astore 6
    //   64: aload 6
    //   66: ldc 95
    //   68: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: pop
    //   72: aload 6
    //   74: iload 4
    //   76: invokevirtual 102	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: ldc 53
    //   82: aload 6
    //   84: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokestatic 112	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   90: pop
    //   91: iconst_4
    //   92: anewarray 61	java/io/File
    //   95: astore 9
    //   97: invokestatic 116	com/intsig/r/j:a	()Ljava/lang/String;
    //   100: astore 12
    //   102: new 92	java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   109: astore 6
    //   111: aload 6
    //   113: ldc 118
    //   115: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: aload 6
    //   121: aload 12
    //   123: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: pop
    //   127: ldc 53
    //   129: aload 6
    //   131: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   137: invokestatic 124	com/intsig/r/j:d	()V
    //   140: aload 12
    //   142: invokestatic 130	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   145: istore 4
    //   147: aconst_null
    //   148: astore 6
    //   150: iload 4
    //   152: ifeq +11 -> 163
    //   155: aload 9
    //   157: iconst_0
    //   158: aconst_null
    //   159: aastore
    //   160: goto +16 -> 176
    //   163: aload 9
    //   165: iconst_0
    //   166: new 61	java/io/File
    //   169: dup
    //   170: aload 12
    //   172: invokespecial 70	java/io/File:<init>	(Ljava/lang/String;)V
    //   175: aastore
    //   176: invokestatic 134	com/intsig/util/bo:d	()Ljava/lang/String;
    //   179: astore 13
    //   181: aload 13
    //   183: invokestatic 130	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   186: ifne +19 -> 205
    //   189: aload 9
    //   191: iconst_1
    //   192: new 61	java/io/File
    //   195: dup
    //   196: aload 13
    //   198: invokespecial 70	java/io/File:<init>	(Ljava/lang/String;)V
    //   201: aastore
    //   202: goto +8 -> 210
    //   205: aload 9
    //   207: iconst_1
    //   208: aconst_null
    //   209: aastore
    //   210: new 92	java/lang/StringBuilder
    //   213: dup
    //   214: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   217: astore 7
    //   219: aload 7
    //   221: aload_0
    //   222: invokevirtual 140	android/content/Context:getFilesDir	()Ljava/io/File;
    //   225: invokevirtual 143	java/io/File:getParentFile	()Ljava/io/File;
    //   228: invokevirtual 146	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   231: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: aload 7
    //   237: ldc -108
    //   239: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: pop
    //   243: aload 7
    //   245: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   248: astore 7
    //   250: new 150	java/text/SimpleDateFormat
    //   253: dup
    //   254: ldc -104
    //   256: invokespecial 153	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   259: new 155	java/util/Date
    //   262: dup
    //   263: invokespecial 156	java/util/Date:<init>	()V
    //   266: invokevirtual 160	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   269: astore 8
    //   271: new 92	java/lang/StringBuilder
    //   274: dup
    //   275: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   278: astore 14
    //   280: aload 14
    //   282: ldc -94
    //   284: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: pop
    //   288: aload 14
    //   290: aload 8
    //   292: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: pop
    //   296: aload 14
    //   298: ldc -94
    //   300: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: pop
    //   304: aload 14
    //   306: aload_1
    //   307: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: pop
    //   311: aload 14
    //   313: ldc -94
    //   315: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: pop
    //   319: aload 7
    //   321: aload 14
    //   323: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   326: invokestatic 167	com/intsig/utils/p:d	(Ljava/lang/String;Ljava/lang/String;)Z
    //   329: pop
    //   330: aload 9
    //   332: iconst_2
    //   333: new 61	java/io/File
    //   336: dup
    //   337: aload 7
    //   339: invokespecial 70	java/io/File:<init>	(Ljava/lang/String;)V
    //   342: aastore
    //   343: aload_0
    //   344: invokestatic 172	com/intsig/q/d:b	(Landroid/content/Context;)V
    //   347: aload_0
    //   348: invokestatic 175	com/intsig/q/d:a	(Landroid/content/Context;)Ljava/lang/String;
    //   351: astore_1
    //   352: aload 9
    //   354: iconst_3
    //   355: new 61	java/io/File
    //   358: dup
    //   359: aload_1
    //   360: invokespecial 70	java/io/File:<init>	(Ljava/lang/String;)V
    //   363: aastore
    //   364: aload 10
    //   366: ifnull +41 -> 407
    //   369: aload 9
    //   371: arraylength
    //   372: aload 10
    //   374: arraylength
    //   375: iadd
    //   376: anewarray 61	java/io/File
    //   379: astore_0
    //   380: aload 9
    //   382: iconst_0
    //   383: aload_0
    //   384: iconst_0
    //   385: aload 9
    //   387: arraylength
    //   388: invokestatic 181	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   391: aload 10
    //   393: iconst_0
    //   394: aload_0
    //   395: aload 9
    //   397: arraylength
    //   398: aload 10
    //   400: arraylength
    //   401: invokestatic 181	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   404: goto +17 -> 421
    //   407: aload 9
    //   409: iconst_0
    //   410: aconst_null
    //   411: iconst_0
    //   412: aload 9
    //   414: arraylength
    //   415: invokestatic 181	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   418: aload 6
    //   420: astore_0
    //   421: new 92	java/lang/StringBuilder
    //   424: dup
    //   425: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   428: astore 6
    //   430: aload 6
    //   432: ldc -73
    //   434: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   437: pop
    //   438: aload 6
    //   440: aload 13
    //   442: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: pop
    //   446: aload 6
    //   448: ldc -71
    //   450: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: pop
    //   454: aload 6
    //   456: aload 7
    //   458: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   461: pop
    //   462: aload 6
    //   464: ldc -69
    //   466: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   469: pop
    //   470: aload 6
    //   472: aload 12
    //   474: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: pop
    //   478: aload 6
    //   480: ldc -67
    //   482: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: pop
    //   486: aload 6
    //   488: aload_1
    //   489: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   492: pop
    //   493: ldc 53
    //   495: aload 6
    //   497: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   500: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   503: new 92	java/lang/StringBuilder
    //   506: dup
    //   507: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   510: astore_1
    //   511: aload_1
    //   512: ldc -65
    //   514: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   517: pop
    //   518: aload_1
    //   519: aload 8
    //   521: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   524: pop
    //   525: aload_1
    //   526: ldc -63
    //   528: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: pop
    //   532: new 61	java/io/File
    //   535: dup
    //   536: aload 11
    //   538: aload_1
    //   539: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   542: invokespecial 196	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   545: astore_1
    //   546: aload_0
    //   547: aload_1
    //   548: iconst_1
    //   549: invokestatic 199	com/intsig/camscanner/a/j:a	([Ljava/io/File;Ljava/io/File;Z)Z
    //   552: istore 4
    //   554: aload 10
    //   556: ifnull +72 -> 628
    //   559: aload 10
    //   561: arraylength
    //   562: istore_3
    //   563: iconst_0
    //   564: istore_2
    //   565: iload_2
    //   566: iload_3
    //   567: if_icmpge +61 -> 628
    //   570: aload 10
    //   572: iload_2
    //   573: aaload
    //   574: astore_0
    //   575: aload_0
    //   576: invokevirtual 87	java/io/File:delete	()Z
    //   579: pop
    //   580: new 92	java/lang/StringBuilder
    //   583: dup
    //   584: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   587: astore 6
    //   589: aload 6
    //   591: ldc -55
    //   593: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   596: pop
    //   597: aload 6
    //   599: aload_0
    //   600: invokevirtual 146	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   603: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   606: pop
    //   607: ldc 53
    //   609: aload 6
    //   611: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   614: invokestatic 204	com/intsig/q/e:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   617: iload_2
    //   618: iconst_1
    //   619: iadd
    //   620: istore_2
    //   621: goto -56 -> 565
    //   624: astore_0
    //   625: goto +42 -> 667
    //   628: iload 4
    //   630: istore 5
    //   632: aload 9
    //   634: iconst_0
    //   635: aaload
    //   636: ifnull +17 -> 653
    //   639: aload 9
    //   641: iconst_0
    //   642: aaload
    //   643: astore_0
    //   644: aload_0
    //   645: invokevirtual 87	java/io/File:delete	()Z
    //   648: pop
    //   649: iload 4
    //   651: istore 5
    //   653: invokestatic 206	com/intsig/r/j:c	()V
    //   656: goto +36 -> 692
    //   659: astore_0
    //   660: goto +146 -> 806
    //   663: astore_0
    //   664: iconst_0
    //   665: istore 4
    //   667: ldc 53
    //   669: aload_0
    //   670: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   673: iload 4
    //   675: istore 5
    //   677: aload 9
    //   679: iconst_0
    //   680: aaload
    //   681: ifnull -28 -> 653
    //   684: aload 9
    //   686: iconst_0
    //   687: aaload
    //   688: astore_0
    //   689: goto -45 -> 644
    //   692: invokestatic 211	com/intsig/camscanner/ScannerApplication:m	()Z
    //   695: ifne +51 -> 746
    //   698: iload 5
    //   700: ifeq +46 -> 746
    //   703: aload_1
    //   704: invokevirtual 146	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   707: aload_1
    //   708: invokevirtual 146	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   711: invokestatic 216	com/intsig/encryptfile/ISEncryptFile:EncryptFileToRSAAESFile	(Ljava/lang/String;Ljava/lang/String;)I
    //   714: istore_2
    //   715: new 92	java/lang/StringBuilder
    //   718: dup
    //   719: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   722: astore_0
    //   723: aload_0
    //   724: ldc -38
    //   726: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   729: pop
    //   730: aload_0
    //   731: iload_2
    //   732: invokevirtual 221	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   735: pop
    //   736: ldc 53
    //   738: aload_0
    //   739: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   742: invokestatic 223	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   745: pop
    //   746: new 92	java/lang/StringBuilder
    //   749: dup
    //   750: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   753: astore_0
    //   754: aload_0
    //   755: ldc -94
    //   757: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   760: pop
    //   761: aload_0
    //   762: aload 8
    //   764: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   767: pop
    //   768: aload_0
    //   769: ldc -31
    //   771: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   774: pop
    //   775: aload_0
    //   776: iload 5
    //   778: invokevirtual 102	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   781: pop
    //   782: aload_0
    //   783: aload_1
    //   784: invokevirtual 146	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   787: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   790: pop
    //   791: aload 7
    //   793: aload_0
    //   794: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   797: invokestatic 167	com/intsig/utils/p:d	(Ljava/lang/String;Ljava/lang/String;)Z
    //   800: pop
    //   801: aload_1
    //   802: invokestatic 228	com/intsig/utils/p:b	(Ljava/io/File;)Landroid/net/Uri;
    //   805: areturn
    //   806: aload 9
    //   808: iconst_0
    //   809: aaload
    //   810: ifnull +11 -> 821
    //   813: aload 9
    //   815: iconst_0
    //   816: aaload
    //   817: invokevirtual 87	java/io/File:delete	()Z
    //   820: pop
    //   821: invokestatic 206	com/intsig/r/j:c	()V
    //   824: aload_0
    //   825: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	826	0	paramContext	Context
    //   0	826	1	paramString	String
    //   564	168	2	i	int
    //   562	6	3	j	int
    //   53	621	4	bool1	boolean
    //   630	147	5	bool2	boolean
    //   62	548	6	localStringBuilder1	StringBuilder
    //   217	575	7	localObject	Object
    //   269	494	8	str1	String
    //   95	719	9	arrayOfFile1	File[]
    //   24	547	10	arrayOfFile2	File[]
    //   10	527	11	localFile	File
    //   100	373	12	str2	String
    //   179	262	13	str3	String
    //   278	44	14	localStringBuilder2	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   559	563	624	java/lang/Exception
    //   575	617	624	java/lang/Exception
    //   546	554	659	finally
    //   559	563	659	finally
    //   575	617	659	finally
    //   667	673	659	finally
    //   546	554	663	java/lang/Exception
  }
  
  public static com.intsig.d.k a(Context paramContext, String paramString, boolean paramBoolean, int paramInt)
  {
    paramContext = new com.intsig.d.k(paramContext);
    paramContext.i(paramInt);
    if (paramString != null) {
      paramContext.a(paramString);
    }
    paramContext.setCancelable(paramBoolean);
    return paramContext;
  }
  
  public static String a(long paramLong)
  {
    double d = paramLong / 1024L;
    String str;
    if (d < 1024.0D)
    {
      str = "%.0fKB";
    }
    else
    {
      d /= 1024.0D;
      if (d < 1024.0D)
      {
        str = "%.2fMB";
      }
      else
      {
        d /= 1024.0D;
        str = "%.2fGB";
      }
    }
    return String.format(str, new Object[] { Double.valueOf(d) });
  }
  
  public static String a(Resources paramResources)
  {
    String str1 = paramResources.getConfiguration().locale.getLanguage();
    String str2 = paramResources.getConfiguration().locale.getCountry();
    paramResources = new StringBuilder();
    paramResources.append("");
    paramResources.append(str1);
    str1 = paramResources.toString();
    paramResources = str1;
    if (str1.equals("zh"))
    {
      if ((str2 != null) && (str2.equals("TW")))
      {
        paramResources = new StringBuilder();
        paramResources.append(str1);
        paramResources.append("-tw");
        return paramResources.toString();
      }
      paramResources = new StringBuilder();
      paramResources.append(str1);
      paramResources.append("-cn");
      paramResources = paramResources.toString();
    }
    return paramResources;
  }
  
  /* Error */
  public static String a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore_2
    //   5: aconst_null
    //   6: astore_1
    //   7: aconst_null
    //   8: astore 5
    //   10: ldc_w 304
    //   13: invokestatic 310	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   16: astore_3
    //   17: new 312	java/io/FileInputStream
    //   20: dup
    //   21: aload_0
    //   22: invokespecial 313	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   25: astore_0
    //   26: aload_2
    //   27: astore_1
    //   28: aload_0
    //   29: astore_2
    //   30: new 315	java/security/DigestInputStream
    //   33: dup
    //   34: aload_0
    //   35: aload_3
    //   36: invokespecial 318	java/security/DigestInputStream:<init>	(Ljava/io/InputStream;Ljava/security/MessageDigest;)V
    //   39: astore_3
    //   40: ldc_w 319
    //   43: newarray byte
    //   45: astore_1
    //   46: aload_3
    //   47: aload_1
    //   48: invokevirtual 323	java/security/DigestInputStream:read	([B)I
    //   51: ifle +6 -> 57
    //   54: goto -8 -> 46
    //   57: aload_3
    //   58: invokevirtual 327	java/security/DigestInputStream:getMessageDigest	()Ljava/security/MessageDigest;
    //   61: invokevirtual 331	java/security/MessageDigest:digest	()[B
    //   64: invokestatic 334	com/intsig/camscanner/a/j:a	([B)Ljava/lang/String;
    //   67: astore_1
    //   68: aload_3
    //   69: ifnull +18 -> 87
    //   72: aload_3
    //   73: invokevirtual 335	java/security/DigestInputStream:close	()V
    //   76: goto +11 -> 87
    //   79: astore_2
    //   80: ldc_w 337
    //   83: aload_2
    //   84: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   87: aload_0
    //   88: ifnull +17 -> 105
    //   91: aload_0
    //   92: invokevirtual 338	java/io/FileInputStream:close	()V
    //   95: aload_1
    //   96: areturn
    //   97: astore_0
    //   98: ldc_w 337
    //   101: aload_0
    //   102: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   105: aload_1
    //   106: areturn
    //   107: astore_2
    //   108: aload_3
    //   109: astore_1
    //   110: aload_2
    //   111: astore_3
    //   112: goto +162 -> 274
    //   115: astore 4
    //   117: goto +37 -> 154
    //   120: astore 4
    //   122: goto +94 -> 216
    //   125: astore 4
    //   127: aload 5
    //   129: astore_3
    //   130: goto +24 -> 154
    //   133: astore 4
    //   135: aload 6
    //   137: astore_3
    //   138: goto +78 -> 216
    //   141: astore_3
    //   142: aconst_null
    //   143: astore_0
    //   144: goto +130 -> 274
    //   147: astore 4
    //   149: aconst_null
    //   150: astore_0
    //   151: aload 5
    //   153: astore_3
    //   154: aload_3
    //   155: astore_1
    //   156: aload_0
    //   157: astore_2
    //   158: ldc_w 340
    //   161: aload 4
    //   163: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   166: aload_3
    //   167: ifnull +18 -> 185
    //   170: aload_3
    //   171: invokevirtual 335	java/security/DigestInputStream:close	()V
    //   174: goto +11 -> 185
    //   177: astore_1
    //   178: ldc_w 337
    //   181: aload_1
    //   182: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   185: aload_0
    //   186: ifnull +19 -> 205
    //   189: aload_0
    //   190: invokevirtual 338	java/io/FileInputStream:close	()V
    //   193: ldc_w 342
    //   196: areturn
    //   197: astore_0
    //   198: ldc_w 337
    //   201: aload_0
    //   202: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   205: ldc_w 342
    //   208: areturn
    //   209: astore 4
    //   211: aconst_null
    //   212: astore_0
    //   213: aload 6
    //   215: astore_3
    //   216: aload_3
    //   217: astore_1
    //   218: aload_0
    //   219: astore_2
    //   220: ldc_w 344
    //   223: aload 4
    //   225: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   228: aload_3
    //   229: ifnull +18 -> 247
    //   232: aload_3
    //   233: invokevirtual 335	java/security/DigestInputStream:close	()V
    //   236: goto +11 -> 247
    //   239: astore_1
    //   240: ldc_w 337
    //   243: aload_1
    //   244: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   247: aload_0
    //   248: ifnull +19 -> 267
    //   251: aload_0
    //   252: invokevirtual 338	java/io/FileInputStream:close	()V
    //   255: ldc_w 342
    //   258: areturn
    //   259: astore_0
    //   260: ldc_w 337
    //   263: aload_0
    //   264: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   267: ldc_w 342
    //   270: areturn
    //   271: astore_3
    //   272: aload_2
    //   273: astore_0
    //   274: aload_1
    //   275: ifnull +18 -> 293
    //   278: aload_1
    //   279: invokevirtual 335	java/security/DigestInputStream:close	()V
    //   282: goto +11 -> 293
    //   285: astore_1
    //   286: ldc_w 337
    //   289: aload_1
    //   290: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   293: aload_0
    //   294: ifnull +18 -> 312
    //   297: aload_0
    //   298: invokevirtual 338	java/io/FileInputStream:close	()V
    //   301: goto +11 -> 312
    //   304: astore_0
    //   305: ldc_w 337
    //   308: aload_0
    //   309: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   312: aload_3
    //   313: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	314	0	paramString	String
    //   6	150	1	localObject1	Object
    //   177	5	1	localException1	Exception
    //   217	1	1	localObject2	Object
    //   239	40	1	localException2	Exception
    //   285	5	1	localException3	Exception
    //   4	26	2	str1	String
    //   79	5	2	localException4	Exception
    //   107	4	2	localObject3	Object
    //   157	116	2	str2	String
    //   16	122	3	localObject4	Object
    //   141	1	3	localObject5	Object
    //   153	80	3	localObject6	Object
    //   271	42	3	localObject7	Object
    //   115	1	4	localNoSuchAlgorithmException1	NoSuchAlgorithmException
    //   120	1	4	localOutOfMemoryError1	OutOfMemoryError
    //   125	1	4	localNoSuchAlgorithmException2	NoSuchAlgorithmException
    //   133	1	4	localOutOfMemoryError2	OutOfMemoryError
    //   147	15	4	localNoSuchAlgorithmException3	NoSuchAlgorithmException
    //   209	15	4	localOutOfMemoryError3	OutOfMemoryError
    //   8	144	5	localObject8	Object
    //   1	213	6	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   72	76	79	java/lang/Exception
    //   91	95	97	java/lang/Exception
    //   40	46	107	finally
    //   46	54	107	finally
    //   57	68	107	finally
    //   40	46	115	java/security/NoSuchAlgorithmException
    //   46	54	115	java/security/NoSuchAlgorithmException
    //   57	68	115	java/security/NoSuchAlgorithmException
    //   40	46	120	java/lang/OutOfMemoryError
    //   46	54	120	java/lang/OutOfMemoryError
    //   57	68	120	java/lang/OutOfMemoryError
    //   30	40	125	java/security/NoSuchAlgorithmException
    //   30	40	133	java/lang/OutOfMemoryError
    //   10	26	141	finally
    //   10	26	147	java/security/NoSuchAlgorithmException
    //   170	174	177	java/lang/Exception
    //   189	193	197	java/lang/Exception
    //   10	26	209	java/lang/OutOfMemoryError
    //   232	236	239	java/lang/Exception
    //   251	255	259	java/lang/Exception
    //   30	40	271	finally
    //   158	166	271	finally
    //   220	228	271	finally
    //   278	282	285	java/lang/Exception
    //   297	301	304	java/lang/Exception
  }
  
  public static String a(String paramString, Context paramContext)
  {
    try
    {
      paramString = paramContext.getPackageManager().getPackageInfo(paramString, 64).signatures;
      if ((paramString != null) && (paramString.length != 0)) {
        return b(paramString[0].toByteArray());
      }
      return "pack not found";
    }
    catch (Exception paramString)
    {
      com.intsig.q.e.b("AppUtil", paramString);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramContext = new StringBuilder();
      paramContext.append("NameNotFoundException: ");
      paramContext.append(paramString.getMessage());
      com.intsig.q.e.c("AppUtil", paramContext.toString());
    }
    return null;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    if (paramString1 != null) {
      try
      {
        paramString1 = new String(paramString1.getBytes(paramString2));
        return paramString1;
      }
      catch (UnsupportedEncodingException paramString1)
      {
        com.intsig.q.e.b("AppUtil", "UnsupportedEncodingException", paramString1);
        return null;
      }
    }
    return null;
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar1 = new char[16];
    char[] tmp8_6 = arrayOfChar1;
    tmp8_6[0] = 48;
    char[] tmp14_8 = tmp8_6;
    tmp14_8[1] = 49;
    char[] tmp20_14 = tmp14_8;
    tmp20_14[2] = 50;
    char[] tmp26_20 = tmp20_14;
    tmp26_20[3] = 51;
    char[] tmp32_26 = tmp26_20;
    tmp32_26[4] = 52;
    char[] tmp38_32 = tmp32_26;
    tmp38_32[5] = 53;
    char[] tmp44_38 = tmp38_32;
    tmp44_38[6] = 54;
    char[] tmp51_44 = tmp44_38;
    tmp51_44[7] = 55;
    char[] tmp58_51 = tmp51_44;
    tmp58_51[8] = 56;
    char[] tmp65_58 = tmp58_51;
    tmp65_58[9] = 57;
    char[] tmp72_65 = tmp65_58;
    tmp72_65[10] = 65;
    char[] tmp79_72 = tmp72_65;
    tmp79_72[11] = 66;
    char[] tmp86_79 = tmp79_72;
    tmp86_79[12] = 67;
    char[] tmp93_86 = tmp86_79;
    tmp93_86[13] = 68;
    char[] tmp100_93 = tmp93_86;
    tmp100_93[14] = 69;
    char[] tmp107_100 = tmp100_93;
    tmp107_100[15] = 70;
    tmp107_100;
    int i = 0;
    char[] arrayOfChar2 = new char[paramArrayOfByte.length * 2];
    int k = paramArrayOfByte.length;
    int j = 0;
    while (i < k)
    {
      int m = paramArrayOfByte[i];
      int n = j + 1;
      arrayOfChar2[j] = arrayOfChar1[(m >>> 4 & 0xF)];
      j = n + 1;
      arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar2);
  }
  
  public static void a(Activity paramActivity)
  {
    if (!a()) {
      com.intsig.utils.n.a(paramActivity, 1);
    }
  }
  
  public static void a(Activity paramActivity, int paramInt)
  {
    if (paramActivity != null)
    {
      if (f.s) {
        return;
      }
      if (f.m)
      {
        am.a(paramActivity, paramActivity.getString(2131691183), paramActivity.getString(2131690896), "market://details?id=com.intsig.lic.camscanner", ((ScannerApplication)paramActivity.getApplication()).t, paramActivity.getResources().getBoolean(2131034121));
        cf.h(paramActivity);
        return;
      }
      new ch(paramActivity).executeOnExecutor(com.intsig.utils.l.a(), new Void[0]);
    }
  }
  
  public static void a(Activity paramActivity, int paramInt, boolean paramBoolean)
  {
    Intent localIntent = new Intent(paramActivity, LoginActivity.class);
    if (paramBoolean)
    {
      if (com.intsig.a.c().e()) {
        localIntent.putExtra("extra_enable_privilege", true);
      }
      localIntent.putExtra(com.intsig.util.a.r, paramBoolean);
    }
    paramActivity.startActivityForResult(localIntent, paramInt);
  }
  
  public static void a(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    if (paramActivity == null) {
      return;
    }
    paramActivity = new com.intsig.d.c(paramActivity).d(2131690668).b(paramString).c(2131689742, new p(paramActivity)).a(paramBoolean).a();
    try
    {
      paramActivity.show();
      return;
    }
    catch (Exception paramActivity)
    {
      com.intsig.q.e.a("AppUtil", paramActivity);
    }
  }
  
  public static void a(Context paramContext)
  {
    c.a = paramContext.getResources().getBoolean(2131034117) ^ true;
    c.b = paramContext.getResources().getBoolean(2131034118);
    c.c = paramContext.getResources().getBoolean(2131034119);
    c.d = paramContext.getResources().getBoolean(2131034116);
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    if (paramContext != null)
    {
      int i = paramContext.getResources().getDimensionPixelSize(2131165789);
      paramContext = Toast.makeText(paramContext, paramInt, 0);
      paramContext.setGravity(49, 0, i);
      paramContext.show();
    }
  }
  
  public static void a(Context paramContext, EduMsg paramEduMsg, x paramX)
  {
    if ((paramEduMsg != null) && (!paramEduMsg.a()))
    {
      a(paramContext, paramEduMsg.a, paramEduMsg.b, paramEduMsg.c, paramEduMsg.d, paramX, 2131690929, 2131689597);
      return;
    }
    paramX.a();
  }
  
  public static void a(Context paramContext, com.intsig.datastruct.r paramR, x paramX)
  {
    com.intsig.datastruct.r localR;
    if (paramR != null)
    {
      localR = paramR;
      if (!paramR.e()) {}
    }
    else
    {
      paramR = e.a();
      localR = new com.intsig.datastruct.r(paramContext.getString(2131689693), paramContext.getString(2131689692), paramContext.getString(2131689691), paramR.a(paramContext));
    }
    a(paramContext, localR.a(), localR.b(), localR.c(), localR.d(), paramX, 2131689621, 17039360);
  }
  
  private static void a(Context paramContext, com.intsig.purchase.n paramN)
  {
    if (bo.c(paramContext))
    {
      com.intsig.d.k localK = new com.intsig.d.k(paramContext);
      localK.show();
      localK.a(paramContext.getString(2131691101));
      com.intsig.purchase.l.a(paramContext, new u(localK, paramN, paramContext));
      return;
    }
    Toast.makeText(paramContext, 2131690971, 1).show();
  }
  
  public static void a(Context paramContext, CharSequence paramCharSequence, String paramString)
  {
    android.text.ClipboardManager localClipboardManager = (android.text.ClipboardManager)paramContext.getSystemService("clipboard");
    if (localClipboardManager != null) {
      try
      {
        localClipboardManager.setText(paramCharSequence);
        Toast.makeText(paramContext, paramString, 0).show();
        return;
      }
      catch (Exception paramContext)
      {
        com.intsig.q.e.b("AppUtil", "go2CopyLink Exception ", paramContext);
      }
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    new v("copyThenRegister2Gallery", paramString1, paramString2, paramContext).start();
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, x paramX, int paramInt1, int paramInt2)
  {
    try
    {
      new com.intsig.d.c(paramContext).a(paramString1).a(false).b(paramString2).b(paramInt2, new m(paramX)).c(paramInt1, new l(paramString3, paramString4, paramX)).a().show();
      return;
    }
    catch (Exception paramContext)
    {
      com.intsig.q.e.b("AppUtil", paramContext);
    }
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    localSharedPreferences.edit().putBoolean("key_is_sync_opened", paramBoolean).commit();
    if (av.y(paramContext)) {
      localSharedPreferences.edit().putBoolean("KEY_SYNC", true).commit();
    }
  }
  
  public static void a(DialogInterface paramDialogInterface, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      c(paramDialogInterface, true);
      paramDialogInterface.dismiss();
      return;
    }
    c(paramDialogInterface, false);
    new k(paramDialogInterface).start();
  }
  
  public static void a(boolean paramBoolean) {}
  
  public static boolean a()
  {
    return (c.d) || (c.b);
  }
  
  public static boolean a(Context paramContext, String paramString, CharSequence paramCharSequence)
  {
    paramContext = (android.content.ClipboardManager)paramContext.getSystemService("clipboard");
    try
    {
      paramContext.setPrimaryClip(ClipData.newPlainText(paramString, paramCharSequence));
      return true;
    }
    catch (RuntimeException paramContext)
    {
      com.intsig.q.e.b("AppUtil", paramContext);
    }
    return false;
  }
  
  /* Error */
  public static boolean a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 735	com/intsig/camscanner/a/j:f	(Ljava/lang/String;)Ljava/lang/String;
    //   4: astore_1
    //   5: new 92	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   12: astore 7
    //   14: aload 7
    //   16: ldc_w 737
    //   19: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: pop
    //   23: aload 7
    //   25: aload_1
    //   26: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: pop
    //   30: ldc 53
    //   32: aload 7
    //   34: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   40: aload_1
    //   41: invokestatic 130	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   44: istore 6
    //   46: iconst_0
    //   47: istore 5
    //   49: iload 6
    //   51: ifeq +5 -> 56
    //   54: iconst_0
    //   55: ireturn
    //   56: new 92	java/lang/StringBuilder
    //   59: dup
    //   60: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   63: astore 7
    //   65: aload 7
    //   67: aload_0
    //   68: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: pop
    //   72: aload 7
    //   74: ldc_w 739
    //   77: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: pop
    //   81: aload 7
    //   83: aload_2
    //   84: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload 7
    //   90: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: astore 7
    //   95: aconst_null
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_0
    //   99: new 741	java/util/zip/ZipInputStream
    //   102: dup
    //   103: new 312	java/io/FileInputStream
    //   106: dup
    //   107: new 61	java/io/File
    //   110: dup
    //   111: aload_1
    //   112: invokespecial 70	java/io/File:<init>	(Ljava/lang/String;)V
    //   115: invokespecial 744	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   118: invokespecial 747	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   121: astore_1
    //   122: aload_1
    //   123: invokevirtual 751	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   126: astore_2
    //   127: aload_2
    //   128: ifnull +268 -> 396
    //   131: aload_2
    //   132: invokevirtual 756	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   135: astore_0
    //   136: aload_2
    //   137: invokevirtual 759	java/util/zip/ZipEntry:isDirectory	()Z
    //   140: ifeq +37 -> 177
    //   143: new 92	java/lang/StringBuilder
    //   146: dup
    //   147: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   150: astore_2
    //   151: aload_2
    //   152: ldc_w 760
    //   155: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: pop
    //   159: aload_2
    //   160: aload_0
    //   161: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: pop
    //   165: ldc 53
    //   167: aload_2
    //   168: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   171: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   174: goto -52 -> 122
    //   177: aload_0
    //   178: invokestatic 130	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   181: ifne +24 -> 205
    //   184: aload_0
    //   185: ldc_w 762
    //   188: invokevirtual 765	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   191: ifeq +14 -> 205
    //   194: ldc 53
    //   196: ldc_w 767
    //   199: invokestatic 204	com/intsig/q/e:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   202: goto -80 -> 122
    //   205: aload_0
    //   206: aload_3
    //   207: invokevirtual 771	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   210: ifeq -88 -> 122
    //   213: ldc 53
    //   215: aload_0
    //   216: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   219: new 61	java/io/File
    //   222: dup
    //   223: aload 7
    //   225: invokespecial 70	java/io/File:<init>	(Ljava/lang/String;)V
    //   228: astore_2
    //   229: aload_2
    //   230: invokevirtual 81	java/io/File:exists	()Z
    //   233: ifne +55 -> 288
    //   236: aload_2
    //   237: invokevirtual 90	java/io/File:mkdirs	()Z
    //   240: istore 6
    //   242: new 92	java/lang/StringBuilder
    //   245: dup
    //   246: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   249: astore 8
    //   251: aload 8
    //   253: ldc_w 773
    //   256: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: pop
    //   260: aload 8
    //   262: iload 6
    //   264: invokevirtual 102	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   267: pop
    //   268: aload 8
    //   270: aload_2
    //   271: invokevirtual 146	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   274: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: pop
    //   278: ldc 53
    //   280: aload 8
    //   282: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   285: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   288: aload_0
    //   289: invokestatic 775	com/intsig/camscanner/a/j:e	(Ljava/lang/String;)Ljava/lang/String;
    //   292: astore_0
    //   293: ldc 53
    //   295: aload_0
    //   296: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   299: new 61	java/io/File
    //   302: dup
    //   303: aload_2
    //   304: aload_0
    //   305: invokespecial 196	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   308: astore_0
    //   309: new 777	java/io/FileOutputStream
    //   312: dup
    //   313: aload_0
    //   314: invokespecial 778	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   317: astore_2
    //   318: sipush 4096
    //   321: newarray byte
    //   323: astore 8
    //   325: aload_1
    //   326: aload 8
    //   328: invokevirtual 779	java/util/zip/ZipInputStream:read	([B)I
    //   331: istore 4
    //   333: iload 4
    //   335: iconst_m1
    //   336: if_icmpeq +19 -> 355
    //   339: aload_2
    //   340: aload 8
    //   342: iconst_0
    //   343: iload 4
    //   345: invokevirtual 783	java/io/FileOutputStream:write	([BII)V
    //   348: aload_2
    //   349: invokevirtual 786	java/io/FileOutputStream:flush	()V
    //   352: goto -27 -> 325
    //   355: aload_2
    //   356: invokevirtual 787	java/io/FileOutputStream:close	()V
    //   359: new 92	java/lang/StringBuilder
    //   362: dup
    //   363: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   366: astore_2
    //   367: aload_2
    //   368: ldc_w 789
    //   371: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   374: pop
    //   375: aload_2
    //   376: aload_0
    //   377: invokevirtual 146	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   380: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   383: pop
    //   384: ldc 53
    //   386: aload_2
    //   387: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   390: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   393: goto -271 -> 122
    //   396: aload_1
    //   397: invokestatic 792	com/intsig/utils/p:a	(Ljava/io/Closeable;)V
    //   400: iconst_1
    //   401: istore 5
    //   403: goto +36 -> 439
    //   406: astore_0
    //   407: goto +114 -> 521
    //   410: astore_2
    //   411: goto +16 -> 427
    //   414: astore_2
    //   415: aload_0
    //   416: astore_1
    //   417: aload_2
    //   418: astore_0
    //   419: goto +102 -> 521
    //   422: astore_0
    //   423: aload_2
    //   424: astore_1
    //   425: aload_0
    //   426: astore_2
    //   427: aload_1
    //   428: astore_0
    //   429: ldc 53
    //   431: aload_2
    //   432: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   435: aload_1
    //   436: invokestatic 792	com/intsig/utils/p:a	(Ljava/io/Closeable;)V
    //   439: new 92	java/lang/StringBuilder
    //   442: dup
    //   443: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   446: astore_0
    //   447: aload_0
    //   448: ldc_w 794
    //   451: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   454: pop
    //   455: aload_0
    //   456: iload 5
    //   458: invokevirtual 102	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   461: pop
    //   462: ldc 53
    //   464: aload_0
    //   465: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   468: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   471: iload 5
    //   473: istore 6
    //   475: iload 5
    //   477: ifeq +9 -> 486
    //   480: iconst_1
    //   481: invokestatic 797	com/intsig/camscanner/a/j:b	(Z)Z
    //   484: istore 6
    //   486: new 92	java/lang/StringBuilder
    //   489: dup
    //   490: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   493: astore_0
    //   494: aload_0
    //   495: ldc_w 799
    //   498: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   501: pop
    //   502: aload_0
    //   503: iload 6
    //   505: invokevirtual 102	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   508: pop
    //   509: ldc 53
    //   511: aload_0
    //   512: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   515: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   518: iload 6
    //   520: ireturn
    //   521: aload_1
    //   522: invokestatic 792	com/intsig/utils/p:a	(Ljava/io/Closeable;)V
    //   525: aload_0
    //   526: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	527	0	paramString1	String
    //   0	527	1	paramString2	String
    //   0	527	2	paramString3	String
    //   0	527	3	paramString4	String
    //   331	13	4	i	int
    //   47	429	5	bool1	boolean
    //   44	475	6	bool2	boolean
    //   12	212	7	localObject1	Object
    //   249	92	8	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   122	127	406	finally
    //   131	174	406	finally
    //   177	202	406	finally
    //   205	288	406	finally
    //   288	325	406	finally
    //   325	333	406	finally
    //   339	352	406	finally
    //   355	393	406	finally
    //   122	127	410	java/lang/Exception
    //   131	174	410	java/lang/Exception
    //   177	202	410	java/lang/Exception
    //   205	288	410	java/lang/Exception
    //   288	325	410	java/lang/Exception
    //   325	333	410	java/lang/Exception
    //   339	352	410	java/lang/Exception
    //   355	393	410	java/lang/Exception
    //   99	122	414	finally
    //   429	435	414	finally
    //   99	122	422	java/lang/Exception
  }
  
  /* Error */
  public static boolean a(File[] paramArrayOfFile, File paramFile, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: aconst_null
    //   4: astore 7
    //   6: new 801	java/util/zip/ZipOutputStream
    //   9: dup
    //   10: new 777	java/io/FileOutputStream
    //   13: dup
    //   14: aload_1
    //   15: invokespecial 778	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   18: invokespecial 804	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   21: astore 8
    //   23: sipush 4096
    //   26: newarray byte
    //   28: astore 7
    //   30: aload_0
    //   31: arraylength
    //   32: istore 5
    //   34: iconst_0
    //   35: istore_3
    //   36: iconst_1
    //   37: istore 4
    //   39: iload_3
    //   40: iload 5
    //   42: if_icmpge +188 -> 230
    //   45: aload_0
    //   46: iload_3
    //   47: aaload
    //   48: astore 9
    //   50: aload 9
    //   52: ifnull +278 -> 330
    //   55: iload_2
    //   56: ifeq +71 -> 127
    //   59: iload 4
    //   61: iconst_1
    //   62: if_icmpne +65 -> 127
    //   65: aload 9
    //   67: invokevirtual 146	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   70: invokestatic 807	com/intsig/encryptfile/ISEncryptFile:FileEncryptedByISCrypter	(Ljava/lang/String;)Z
    //   73: iconst_1
    //   74: if_icmpne +31 -> 105
    //   77: new 809	java/io/BufferedInputStream
    //   80: dup
    //   81: aload 9
    //   83: invokevirtual 146	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   86: invokestatic 813	com/intsig/encryptfile/ISEncryptFile:ISDecryptFileInStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   89: invokespecial 814	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   92: astore_1
    //   93: ldc 53
    //   95: ldc_w 816
    //   98: invokestatic 818	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   101: pop
    //   102: goto +35 -> 137
    //   105: new 312	java/io/FileInputStream
    //   108: dup
    //   109: aload 9
    //   111: invokespecial 744	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   114: astore_1
    //   115: ldc 53
    //   117: ldc_w 820
    //   120: invokestatic 818	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   123: pop
    //   124: goto +13 -> 137
    //   127: new 312	java/io/FileInputStream
    //   130: dup
    //   131: aload 9
    //   133: invokespecial 744	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   136: astore_1
    //   137: new 92	java/lang/StringBuilder
    //   140: dup
    //   141: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   144: astore 10
    //   146: aload 10
    //   148: iload 4
    //   150: invokevirtual 221	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   153: pop
    //   154: aload 10
    //   156: ldc_w 822
    //   159: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: aload 10
    //   165: aload 9
    //   167: invokevirtual 823	java/io/File:getName	()Ljava/lang/String;
    //   170: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload 8
    //   176: new 753	java/util/zip/ZipEntry
    //   179: dup
    //   180: aload 10
    //   182: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: invokespecial 824	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   188: invokevirtual 828	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   191: aload_1
    //   192: aload 7
    //   194: invokevirtual 829	java/io/InputStream:read	([B)I
    //   197: istore 6
    //   199: iload 6
    //   201: iconst_m1
    //   202: if_icmpeq +16 -> 218
    //   205: aload 8
    //   207: aload 7
    //   209: iconst_0
    //   210: iload 6
    //   212: invokevirtual 830	java/util/zip/ZipOutputStream:write	([BII)V
    //   215: goto -24 -> 191
    //   218: aload_1
    //   219: invokevirtual 51	java/io/InputStream:close	()V
    //   222: aload 8
    //   224: invokevirtual 833	java/util/zip/ZipOutputStream:closeEntry	()V
    //   227: goto +103 -> 330
    //   230: aload 8
    //   232: invokevirtual 836	java/util/zip/ZipOutputStream:finish	()V
    //   235: aload 8
    //   237: ifnull +18 -> 255
    //   240: aload 8
    //   242: invokevirtual 837	java/util/zip/ZipOutputStream:close	()V
    //   245: goto +10 -> 255
    //   248: astore_0
    //   249: ldc 53
    //   251: aload_0
    //   252: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   255: iconst_1
    //   256: ireturn
    //   257: astore_0
    //   258: goto +50 -> 308
    //   261: astore_1
    //   262: aload 8
    //   264: astore_0
    //   265: goto +15 -> 280
    //   268: astore_0
    //   269: aload 7
    //   271: astore 8
    //   273: goto +35 -> 308
    //   276: astore_1
    //   277: aload 9
    //   279: astore_0
    //   280: aload_0
    //   281: astore 7
    //   283: ldc 53
    //   285: aload_1
    //   286: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   289: aload_0
    //   290: ifnull +16 -> 306
    //   293: aload_0
    //   294: invokevirtual 837	java/util/zip/ZipOutputStream:close	()V
    //   297: iconst_0
    //   298: ireturn
    //   299: astore_0
    //   300: ldc 53
    //   302: aload_0
    //   303: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   306: iconst_0
    //   307: ireturn
    //   308: aload 8
    //   310: ifnull +18 -> 328
    //   313: aload 8
    //   315: invokevirtual 837	java/util/zip/ZipOutputStream:close	()V
    //   318: goto +10 -> 328
    //   321: astore_1
    //   322: ldc 53
    //   324: aload_1
    //   325: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   328: aload_0
    //   329: athrow
    //   330: iload 4
    //   332: iconst_1
    //   333: iadd
    //   334: istore 4
    //   336: iload_3
    //   337: iconst_1
    //   338: iadd
    //   339: istore_3
    //   340: goto -301 -> 39
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	343	0	paramArrayOfFile	File[]
    //   0	343	1	paramFile	File
    //   0	343	2	paramBoolean	boolean
    //   35	305	3	i	int
    //   37	298	4	j	int
    //   32	11	5	k	int
    //   197	14	6	m	int
    //   4	278	7	localObject1	Object
    //   21	293	8	localObject2	Object
    //   1	277	9	localFile	File
    //   144	37	10	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   240	245	248	java/lang/Exception
    //   23	34	257	finally
    //   65	102	257	finally
    //   105	124	257	finally
    //   127	137	257	finally
    //   137	191	257	finally
    //   191	199	257	finally
    //   205	215	257	finally
    //   218	227	257	finally
    //   230	235	257	finally
    //   23	34	261	java/lang/Exception
    //   65	102	261	java/lang/Exception
    //   105	124	261	java/lang/Exception
    //   127	137	261	java/lang/Exception
    //   137	191	261	java/lang/Exception
    //   191	199	261	java/lang/Exception
    //   205	215	261	java/lang/Exception
    //   218	227	261	java/lang/Exception
    //   230	235	261	java/lang/Exception
    //   6	23	268	finally
    //   283	289	268	finally
    //   6	23	276	java/lang/Exception
    //   293	297	299	java/lang/Exception
    //   313	318	321	java/lang/Exception
  }
  
  public static CharSequence b()
  {
    StringBuilder localStringBuilder1 = new StringBuilder(64);
    Runtime localRuntime = Runtime.getRuntime();
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\nRuntime Max=");
    localStringBuilder2.append(localRuntime.maxMemory() / 1024L / 1024L);
    localStringBuilder2.append("M");
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" Total=");
    localStringBuilder2.append(localRuntime.totalMemory() / 1024L / 1024L);
    localStringBuilder2.append("M");
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(" Free=");
    localStringBuilder2.append(localRuntime.freeMemory() / 1024L);
    localStringBuilder2.append("K\n");
    localStringBuilder1.append(localStringBuilder2.toString());
    return localStringBuilder1;
  }
  
  public static CharSequence b(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder(256);
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    paramContext.getMemoryInfo(localMemoryInfo);
    localStringBuilder.append("availMem: ");
    localStringBuilder.append(bg.a(localMemoryInfo.availMem));
    localStringBuilder.append(" threshold: ");
    localStringBuilder.append(bg.a(localMemoryInfo.threshold));
    localStringBuilder.append(" lowMemory: ");
    if (localMemoryInfo.lowMemory) {
      paramContext = "Yes";
    } else {
      paramContext = "No";
    }
    localStringBuilder.append(paramContext);
    localStringBuilder.append(b());
    return localStringBuilder;
  }
  
  public static String b(Context paramContext, boolean paramBoolean)
  {
    long l = av.o(paramContext);
    String str2 = "";
    String str1 = str2;
    if (l > 0L)
    {
      Cursor localCursor = paramContext.getContentResolver().query(ContentUris.withAppendedId(com.intsig.camscanner.provider.w.a, l), new String[] { "sync_time" }, null, null, null);
      str1 = str2;
      if (localCursor != null)
      {
        str1 = str2;
        if (localCursor.moveToFirst())
        {
          str1 = str2;
          if (localCursor.getLong(0) > 0L) {
            str1 = paramContext.getString(2131690305, new Object[] { new SimpleDateFormat("MM-dd HH:mm").format(Long.valueOf(localCursor.getLong(0))) });
          }
        }
        localCursor.close();
      }
    }
    return str1;
  }
  
  public static String b(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      paramString = paramString.toCharArray();
      int j = 0;
      byte[] arrayOfByte = new byte[paramString.length];
      int i = 0;
      while (i < paramString.length)
      {
        arrayOfByte[i] = ((byte)paramString[i]);
        i += 1;
      }
      paramString = ((MessageDigest)localObject).digest(arrayOfByte);
      localObject = new StringBuilder();
      i = j;
      while (i < paramString.length)
      {
        j = paramString[i] & 0xFF;
        if (j < 16) {
          ((StringBuilder)localObject).append("0");
        }
        ((StringBuilder)localObject).append(Integer.toHexString(j));
        i += 1;
      }
      return ((StringBuilder)localObject).toString();
    }
    catch (Exception paramString)
    {
      com.intsig.q.e.b("AppUtil", paramString.toString());
    }
    return "";
  }
  
  public static String b(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte);
      paramArrayOfByte = a(localMessageDigest.digest());
      return paramArrayOfByte;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      for (;;) {}
    }
    com.intsig.q.e.c("AppUtil", "can't find MD5 alg");
    return null;
  }
  
  public static void b(Activity paramActivity)
  {
    if (!c.b) {
      return;
    }
    paramActivity.getWindow().setFlags(2, 2);
    WindowManager.LayoutParams localLayoutParams = paramActivity.getWindow().getAttributes();
    localLayoutParams.height = paramActivity.getResources().getDimensionPixelSize(2131165425);
    localLayoutParams.width = paramActivity.getResources().getDimensionPixelSize(2131165427);
    localLayoutParams.dimAmount = 0.5F;
    paramActivity.getWindow().setAttributes(localLayoutParams);
  }
  
  public static void b(Activity paramActivity, int paramInt)
  {
    a(paramActivity, new t(paramActivity, paramInt));
  }
  
  public static void b(Context paramContext, String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("key_last_account_storage", paramString).commit();
  }
  
  public static boolean b(boolean paramBoolean)
  {
    String[] arrayOfString = new String[8];
    arrayOfString[0] = com.intsig.util.a.a;
    arrayOfString[1] = com.intsig.util.a.b;
    arrayOfString[2] = com.intsig.util.a.c;
    arrayOfString[3] = com.intsig.util.a.d;
    arrayOfString[4] = com.intsig.util.a.e;
    arrayOfString[5] = com.intsig.util.a.f;
    arrayOfString[6] = com.intsig.util.a.g;
    arrayOfString[7] = com.intsig.util.a.h;
    int k = arrayOfString.length;
    int j = 0;
    paramBoolean = true;
    int i = j;
    while (i < k)
    {
      String str = arrayOfString[i];
      Object localObject = new File(str);
      if ((((File)localObject).exists()) && (((File)localObject).isFile()))
      {
        try
        {
          localObject = a(str);
          paramBoolean = new String[] { "D846536FDE04C7BB697117E8B80BCF34", "0636C9AD91F3CEA137CEFBA01D1ED31B", "94A8A13A4864E175143DBF06951BA24C", "8CD9B12618069AE97A97D84FAF0DF1EC", "7245AFFB3460891AC8EB839A96F6C847", "36409967A7232A2A09EDE88999780525", "3E22CDE45D82581AC10A3850BB4D7F6E", "7B943055F766E2B375D9C6C4DEFB7B82" }[j].equalsIgnoreCase((String)localObject);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("checkLibs: ");
          localStringBuilder.append(paramBoolean);
          localStringBuilder.append(", ");
          localStringBuilder.append(str);
          localStringBuilder.append(" = ");
          localStringBuilder.append((String)localObject);
          com.intsig.q.e.b("AppUtil", localStringBuilder.toString());
        }
        catch (IOException localIOException)
        {
          com.intsig.q.e.b("AppUtil", localIOException);
          paramBoolean = false;
        }
        if (!paramBoolean) {
          return paramBoolean;
        }
        j += 1;
        i += 1;
      }
      else
      {
        paramBoolean = false;
      }
    }
    return paramBoolean;
  }
  
  public static DialogInterface.OnClickListener c()
  {
    return new q();
  }
  
  public static com.intsig.d.k c(Context paramContext)
  {
    return a(paramContext, paramContext.getString(2131691101), false, 0);
  }
  
  public static String c(String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    return str.replaceAll("[?*/\":<>|%#\\\\]", "");
  }
  
  public static void c(Activity paramActivity)
  {
    com.intsig.d.c localC = new com.intsig.d.c(paramActivity).d(2131691008);
    int i;
    if (l()) {
      i = 2131691006;
    } else {
      i = 2131691005;
    }
    paramActivity = localC.e(i).c(2131691007, new o(paramActivity)).a();
    paramActivity.setCancelable(false);
    paramActivity.show();
  }
  
  public static void c(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, ImageRegisterService.class);
    localIntent.putExtra("ImageRegisterService.fileName", paramString);
    paramContext.startService(localIntent);
  }
  
  public static void c(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (av.z(paramContext))
      {
        ScannerApplication.c(true);
        return;
      }
      ScannerApplication.c(av.d());
      return;
    }
    ScannerApplication.c(av.d());
  }
  
  private static void c(DialogInterface paramDialogInterface, boolean paramBoolean)
  {
    try
    {
      Field localField = paramDialogInterface.getClass().getSuperclass().getDeclaredField("mShowing");
      localField.setAccessible(true);
      localField.set(paramDialogInterface, Boolean.valueOf(paramBoolean));
      return;
    }
    catch (Exception paramDialogInterface)
    {
      com.intsig.q.e.b("AppUtil", "Exception", paramDialogInterface);
    }
  }
  
  public static void d(Context paramContext)
  {
    String str = ScannerApplication.j;
    paramContext = str;
    if (TextUtils.isEmpty(str)) {
      paramContext = "000000000000000";
    }
    try
    {
      com.intsig.util.r.g(com.intsig.f.c.a(paramContext, "com.intsig.payment.Util"));
      return;
    }
    catch (Exception paramContext)
    {
      com.intsig.q.e.b("AppUtil", "Exception", paramContext);
    }
  }
  
  public static boolean d()
  {
    boolean bool = TextUtils.equals(Locale.CHINA.getCountry(), Locale.getDefault().getCountry());
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("isLocalCn ");
    localStringBuilder.append(bool);
    localStringBuilder.append(", ");
    localStringBuilder.append(Locale.CHINA.getCountry());
    localStringBuilder.append(", default = ");
    localStringBuilder.append(Locale.getDefault().getCountry());
    com.intsig.q.e.b("AppUtil", localStringBuilder.toString());
    return bool;
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    bool2 = false;
    bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (TextUtils.isEmpty(paramString)) {}
    }
    try
    {
      try
      {
        paramContext.getPackageManager().getPackageInfo(paramString, 0);
        bool1 = true;
      }
      catch (RuntimeException paramContext)
      {
        com.intsig.q.e.b("AppUtil", "RuntimeException", paramContext);
        bool1 = bool2;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        bool1 = bool2;
      }
    }
    paramContext = new StringBuilder();
    paramContext.append(paramString);
    paramContext.append(" isInstallApp = ");
    paramContext.append(bool1);
    com.intsig.q.e.b("AppUtil", paramContext.toString());
    return bool1;
  }
  
  public static boolean d(String paramString)
  {
    return (Locale.CHINESE.toString().equals(paramString)) || (Locale.TRADITIONAL_CHINESE.toString().equals(paramString)) || (Locale.ENGLISH.toString().equals(paramString)) || (Locale.KOREAN.toString().equals(paramString)) || (Locale.JAPANESE.toString().equals(paramString)) || ("es".equalsIgnoreCase(paramString)) || (Locale.FRENCH.toString().equals(paramString)) || (Locale.GERMAN.toString().equals(paramString));
  }
  
  public static int e(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramString = new StringBuilder();
      paramString.append("getAppVersion NameNotFoundException ");
      paramString.append(paramContext);
      com.intsig.q.e.c("AppUtil", paramString.toString());
    }
    return -1;
  }
  
  public static String e(String paramString)
  {
    if (paramString != null)
    {
      int i = paramString.lastIndexOf("/");
      if (i != -1) {
        return paramString.substring(i + 1, paramString.length());
      }
    }
    return null;
  }
  
  public static boolean e()
  {
    String str3 = com.intsig.util.r.n();
    String str2 = ScannerApplication.j;
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = "000000000000000";
    }
    try
    {
      boolean bool = str3.equals(com.intsig.f.c.a(str1, "com.intsig.payment.Util"));
      return bool;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean e(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("reg_success", false);
  }
  
  public static String f()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    File localFile = new File(com.intsig.util.a.a);
    if ((localFile.exists()) && (localFile.isFile()))
    {
      localStringBuilder.append(" qrcode:");
      localStringBuilder.append(localFile.length());
    }
    else
    {
      localStringBuilder.append(" qrcode:");
      localStringBuilder.append("0");
    }
    localFile = new File(com.intsig.util.a.b);
    if ((localFile.exists()) && (localFile.isFile()))
    {
      localStringBuilder.append(" pdf:");
      localStringBuilder.append(localFile.length());
    }
    else
    {
      localStringBuilder.append(" pdf:");
      localStringBuilder.append("0");
    }
    localFile = new File(com.intsig.util.a.c);
    if ((localFile.exists()) && (localFile.isFile()))
    {
      localStringBuilder.append(" scanner:");
      localStringBuilder.append(localFile.length());
    }
    else
    {
      localStringBuilder.append(" scanner:");
      localStringBuilder.append("0");
    }
    localFile = new File(com.intsig.util.a.d);
    if ((localFile.exists()) && (localFile.isFile()))
    {
      localStringBuilder.append(" ink:");
      localStringBuilder.append(localFile.length());
    }
    else
    {
      localStringBuilder.append(" ink:");
      localStringBuilder.append("0");
    }
    localFile = new File(com.intsig.util.a.e);
    if ((localFile.exists()) && (localFile.isFile()))
    {
      localStringBuilder.append(" angle:");
      localStringBuilder.append(localFile.length());
    }
    else
    {
      localStringBuilder.append(" angle:");
      localStringBuilder.append("0");
    }
    localStringBuilder.append("\n");
    localStringBuilder.append(com.intsig.util.a.c);
    return localStringBuilder.toString();
  }
  
  private static String f(String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("/data/app/");
    ((StringBuilder)localObject).append(paramString);
    paramString = ((StringBuilder)localObject).toString();
    int i = 1;
    while (i < 100)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("-");
      ((StringBuilder)localObject).append(i);
      ((StringBuilder)localObject).append(".apk");
      localObject = new File(((StringBuilder)localObject).toString());
      if (((File)localObject).exists())
      {
        paramString = ((File)localObject).getAbsolutePath();
        break label107;
      }
      i += 1;
    }
    paramString = null;
    label107:
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getPackagePath() apkPath=");
    ((StringBuilder)localObject).append(paramString);
    com.intsig.q.e.b("AppUtil", ((StringBuilder)localObject).toString());
    return paramString;
  }
  
  public static boolean f(Context paramContext)
  {
    return d(paramContext, "com.android.vending");
  }
  
  public static boolean f(Context paramContext, String paramString)
  {
    if (paramContext == null)
    {
      com.intsig.q.e.b("AppUtil", "checkAppIsInstall context = null ");
      return false;
    }
    if (TextUtils.isEmpty(paramString))
    {
      com.intsig.q.e.b("AppUtil", "checkAppIsInstall packageName is empty ");
      return false;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      int i = 0;
      while (i < paramContext.size())
      {
        if (paramString.equals(((PackageInfo)paramContext.get(i)).packageName))
        {
          com.intsig.q.e.b("AppUtil", "checkAppIsInstall packageName is installed ");
          return true;
        }
        i += 1;
      }
    }
    com.intsig.q.e.b("AppUtil", "checkAppIsInstall packageName is not installed ");
    return false;
  }
  
  /* Error */
  public static int g()
  {
    // Byte code:
    //   0: new 1282	java/io/BufferedReader
    //   3: dup
    //   4: new 1284	java/io/FileReader
    //   7: dup
    //   8: ldc_w 1286
    //   11: invokespecial 1287	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   14: sipush 4096
    //   17: invokespecial 1290	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   20: astore_3
    //   21: aload_3
    //   22: astore_2
    //   23: aload_3
    //   24: invokevirtual 1293	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   27: astore 4
    //   29: aload_3
    //   30: astore_2
    //   31: ldc 53
    //   33: aload 4
    //   35: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   38: aload_3
    //   39: astore_2
    //   40: aload 4
    //   42: ldc_w 1295
    //   45: invokevirtual 1299	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   48: iconst_1
    //   49: aaload
    //   50: invokestatic 1302	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   53: invokevirtual 1305	java/lang/Integer:intValue	()I
    //   56: sipush 1024
    //   59: idiv
    //   60: istore_1
    //   61: iload_1
    //   62: istore_0
    //   63: aload_3
    //   64: ifnull +71 -> 135
    //   67: aload_3
    //   68: invokevirtual 1306	java/io/BufferedReader:close	()V
    //   71: iload_1
    //   72: istore_0
    //   73: goto +62 -> 135
    //   76: astore_2
    //   77: ldc 53
    //   79: aload_2
    //   80: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   83: iload_1
    //   84: istore_0
    //   85: goto +50 -> 135
    //   88: astore 4
    //   90: goto +13 -> 103
    //   93: astore_2
    //   94: aconst_null
    //   95: astore_3
    //   96: goto +79 -> 175
    //   99: astore 4
    //   101: aconst_null
    //   102: astore_3
    //   103: aload_3
    //   104: astore_2
    //   105: ldc 53
    //   107: ldc_w 1308
    //   110: aload 4
    //   112: invokestatic 392	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   115: aload_3
    //   116: ifnull +17 -> 133
    //   119: aload_3
    //   120: invokevirtual 1306	java/io/BufferedReader:close	()V
    //   123: goto +10 -> 133
    //   126: astore_2
    //   127: ldc 53
    //   129: aload_2
    //   130: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   133: iconst_0
    //   134: istore_0
    //   135: new 92	java/lang/StringBuilder
    //   138: dup
    //   139: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   142: astore_2
    //   143: aload_2
    //   144: ldc_w 1310
    //   147: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: pop
    //   151: aload_2
    //   152: iload_0
    //   153: invokevirtual 221	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   156: pop
    //   157: ldc 53
    //   159: aload_2
    //   160: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokestatic 121	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: iload_0
    //   167: ireturn
    //   168: astore 4
    //   170: aload_2
    //   171: astore_3
    //   172: aload 4
    //   174: astore_2
    //   175: aload_3
    //   176: ifnull +17 -> 193
    //   179: aload_3
    //   180: invokevirtual 1306	java/io/BufferedReader:close	()V
    //   183: goto +10 -> 193
    //   186: astore_3
    //   187: ldc 53
    //   189: aload_3
    //   190: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   193: aload_2
    //   194: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   62	105	0	i	int
    //   60	24	1	j	int
    //   22	18	2	localObject1	Object
    //   76	4	2	localException1	Exception
    //   93	1	2	localObject2	Object
    //   104	1	2	localObject3	Object
    //   126	4	2	localException2	Exception
    //   142	52	2	localObject4	Object
    //   20	160	3	localObject5	Object
    //   186	4	3	localException3	Exception
    //   27	14	4	str	String
    //   88	1	4	localException4	Exception
    //   99	12	4	localException5	Exception
    //   168	5	4	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   67	71	76	java/lang/Exception
    //   23	29	88	java/lang/Exception
    //   31	38	88	java/lang/Exception
    //   40	61	88	java/lang/Exception
    //   0	21	93	finally
    //   0	21	99	java/lang/Exception
    //   119	123	126	java/lang/Exception
    //   23	29	168	finally
    //   31	38	168	finally
    //   40	61	168	finally
    //   105	115	168	finally
    //   179	183	186	java/lang/Exception
  }
  
  public static boolean g(Context paramContext)
  {
    if (av.y(paramContext)) {
      return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("key_is_sync_opened", false);
    }
    a(paramContext, false);
    return false;
  }
  
  public static boolean h()
  {
    try
    {
      Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
      int j = Camera.getNumberOfCameras();
      int i = 0;
      while (i < j)
      {
        Camera.getCameraInfo(i, localCameraInfo);
        int k = localCameraInfo.facing;
        if (k == 0) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (Exception localException)
    {
      com.intsig.q.e.b("AppUtil", "Exception", localException);
    }
  }
  
  public static boolean h(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("KEY_SYNC", false);
  }
  
  public static String i(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString(paramContext.getString(2131691206), paramContext.getString(2131691333));
  }
  
  public static boolean i()
  {
    return a;
  }
  
  public static String j()
  {
    Locale localLocale = Locale.getDefault();
    if (localLocale.equals(Locale.SIMPLIFIED_CHINESE)) {
      return "file:///android_asset/vip/index_zh.html";
    }
    if ((!localLocale.equals(Locale.TRADITIONAL_CHINESE)) && (!localLocale.equals(Locale.CHINESE)))
    {
      if ((!localLocale.equals(Locale.GERMAN)) && (!localLocale.equals(Locale.GERMANY)))
      {
        if ((!localLocale.equals(Locale.FRENCH)) && (!localLocale.equals(Locale.FRANCE)))
        {
          if ((!localLocale.equals(Locale.JAPAN)) && (!localLocale.equals(Locale.JAPANESE)))
          {
            if ((!localLocale.equals(Locale.KOREA)) && (!localLocale.equals(Locale.KOREAN)))
            {
              if ((!localLocale.equals(Locale.ITALIAN)) && (!localLocale.equals(Locale.ITALY)))
              {
                if ("ar".equals(localLocale.getLanguage())) {
                  return "file:///android_asset/vip/index_ar.html";
                }
                if (("pt".equals(localLocale.getLanguage())) && ("br".equalsIgnoreCase(localLocale.getCountry()))) {
                  return "file:///android_asset/vip/index_br.html";
                }
                if ("pl".equals(localLocale.getLanguage())) {
                  return "file:///android_asset/vip/index_pl.html";
                }
                if ("es".equals(localLocale.getLanguage())) {
                  return "file:///android_asset/vip/index_es.html";
                }
                if ("pt".equals(localLocale.getLanguage())) {
                  return "file:///android_asset/vip/index_pt.html";
                }
                if ("ru".equals(localLocale.getLanguage())) {
                  return "file:///android_asset/vip/index_ru.html";
                }
                if ("tr".equals(localLocale.getLanguage())) {
                  return "file:///android_asset/vip/index_tr.html";
                }
                return "file:///android_asset/vip/index_en.html";
              }
              return "file:///android_asset/vip/index_it.html";
            }
            return "file:///android_asset/vip/index_ko.html";
          }
          return "file:///android_asset/vip/index_ja.html";
        }
        return "file:///android_asset/vip/index_fr.html";
      }
      return "file:///android_asset/vip/index_de.html";
    }
    return "file:///android_asset/vip/index_tw.html";
  }
  
  public static String j(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("key_last_account_storage", null);
  }
  
  public static String k(Context paramContext)
  {
    return b(paramContext, false);
  }
  
  /* Error */
  public static boolean k()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: iconst_0
    //   3: istore_0
    //   4: iconst_0
    //   5: istore_1
    //   6: getstatic 1401	com/intsig/camscanner/a/j:b	Ljava/util/Properties;
    //   9: ifnonnull +223 -> 232
    //   12: new 1403	java/util/Properties
    //   15: dup
    //   16: invokespecial 1404	java/util/Properties:<init>	()V
    //   19: putstatic 1401	com/intsig/camscanner/a/j:b	Ljava/util/Properties;
    //   22: new 312	java/io/FileInputStream
    //   25: dup
    //   26: new 61	java/io/File
    //   29: dup
    //   30: invokestatic 1409	android/os/Environment:getRootDirectory	()Ljava/io/File;
    //   33: ldc_w 1411
    //   36: invokespecial 196	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   39: invokespecial 744	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   42: astore 4
    //   44: aload 4
    //   46: astore 5
    //   48: aload 4
    //   50: astore_3
    //   51: getstatic 1401	com/intsig/camscanner/a/j:b	Ljava/util/Properties;
    //   54: aload 4
    //   56: invokevirtual 1414	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   59: goto +3 -> 62
    //   62: aload 4
    //   64: astore 5
    //   66: aload 4
    //   68: astore_3
    //   69: getstatic 1401	com/intsig/camscanner/a/j:b	Ljava/util/Properties;
    //   72: ldc_w 1416
    //   75: aconst_null
    //   76: invokevirtual 1419	java/util/Properties:getProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   79: ifnonnull +49 -> 128
    //   82: aload 4
    //   84: astore 5
    //   86: aload 4
    //   88: astore_3
    //   89: getstatic 1401	com/intsig/camscanner/a/j:b	Ljava/util/Properties;
    //   92: ldc_w 1421
    //   95: aconst_null
    //   96: invokevirtual 1419	java/util/Properties:getProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   99: ifnonnull +29 -> 128
    //   102: aload 4
    //   104: astore 5
    //   106: aload 4
    //   108: astore_3
    //   109: getstatic 1401	com/intsig/camscanner/a/j:b	Ljava/util/Properties;
    //   112: ldc_w 1423
    //   115: aconst_null
    //   116: invokevirtual 1419	java/util/Properties:getProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   119: astore 6
    //   121: iload_1
    //   122: istore_0
    //   123: aload 6
    //   125: ifnull +5 -> 130
    //   128: iconst_1
    //   129: istore_0
    //   130: iload_0
    //   131: istore_1
    //   132: aload 4
    //   134: ifnull +64 -> 198
    //   137: iload_0
    //   138: istore_1
    //   139: aload 4
    //   141: invokevirtual 51	java/io/InputStream:close	()V
    //   144: iload_0
    //   145: ireturn
    //   146: astore_3
    //   147: ldc 53
    //   149: aload_3
    //   150: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   153: iload_1
    //   154: ireturn
    //   155: astore 4
    //   157: goto +15 -> 172
    //   160: astore_3
    //   161: aconst_null
    //   162: astore 4
    //   164: goto +44 -> 208
    //   167: astore 4
    //   169: aconst_null
    //   170: astore 5
    //   172: aload 5
    //   174: astore_3
    //   175: ldc 53
    //   177: aload 4
    //   179: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   182: iload_0
    //   183: istore_1
    //   184: aload 5
    //   186: ifnull +12 -> 198
    //   189: iload_2
    //   190: istore_1
    //   191: aload 5
    //   193: invokevirtual 51	java/io/InputStream:close	()V
    //   196: iload_0
    //   197: istore_1
    //   198: iload_1
    //   199: ireturn
    //   200: astore 5
    //   202: aload_3
    //   203: astore 4
    //   205: aload 5
    //   207: astore_3
    //   208: aload 4
    //   210: ifnull +20 -> 230
    //   213: aload 4
    //   215: invokevirtual 51	java/io/InputStream:close	()V
    //   218: goto +12 -> 230
    //   221: astore 4
    //   223: ldc 53
    //   225: aload 4
    //   227: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   230: aload_3
    //   231: athrow
    //   232: aconst_null
    //   233: astore 4
    //   235: goto -173 -> 62
    // Local variable table:
    //   start	length	slot	name	signature
    //   3	194	0	bool1	boolean
    //   5	194	1	bool2	boolean
    //   1	189	2	bool3	boolean
    //   50	59	3	localFileInputStream1	java.io.FileInputStream
    //   146	4	3	localException1	Exception
    //   160	1	3	localObject1	Object
    //   174	57	3	localObject2	Object
    //   42	98	4	localFileInputStream2	java.io.FileInputStream
    //   155	1	4	localIOException1	IOException
    //   162	1	4	localObject3	Object
    //   167	11	4	localIOException2	IOException
    //   203	11	4	localObject4	Object
    //   221	5	4	localException2	Exception
    //   233	1	4	localObject5	Object
    //   46	146	5	localFileInputStream3	java.io.FileInputStream
    //   200	6	5	localObject6	Object
    //   119	5	6	str	String
    // Exception table:
    //   from	to	target	type
    //   139	144	146	java/lang/Exception
    //   191	196	146	java/lang/Exception
    //   51	59	155	java/io/IOException
    //   69	82	155	java/io/IOException
    //   89	102	155	java/io/IOException
    //   109	121	155	java/io/IOException
    //   6	44	160	finally
    //   6	44	167	java/io/IOException
    //   51	59	200	finally
    //   69	82	200	finally
    //   89	102	200	finally
    //   109	121	200	finally
    //   175	182	200	finally
    //   213	218	221	java/lang/Exception
  }
  
  public static void l(Context paramContext)
  {
    a(paramContext, new s(paramContext));
  }
  
  /* Error */
  public static boolean l()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 7
    //   6: iconst_0
    //   7: istore_3
    //   8: iconst_0
    //   9: istore_2
    //   10: iconst_0
    //   11: istore_1
    //   12: aload 7
    //   14: astore 5
    //   16: getstatic 1401	com/intsig/camscanner/a/j:b	Ljava/util/Properties;
    //   19: ifnonnull +234 -> 253
    //   22: aload 7
    //   24: astore 5
    //   26: new 1403	java/util/Properties
    //   29: dup
    //   30: invokespecial 1404	java/util/Properties:<init>	()V
    //   33: putstatic 1401	com/intsig/camscanner/a/j:b	Ljava/util/Properties;
    //   36: aload 7
    //   38: astore 5
    //   40: new 312	java/io/FileInputStream
    //   43: dup
    //   44: new 61	java/io/File
    //   47: dup
    //   48: invokestatic 1409	android/os/Environment:getRootDirectory	()Ljava/io/File;
    //   51: ldc_w 1411
    //   54: invokespecial 196	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   57: invokespecial 744	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   60: astore 8
    //   62: aload 8
    //   64: astore 6
    //   66: aload 8
    //   68: astore 7
    //   70: getstatic 1401	com/intsig/camscanner/a/j:b	Ljava/util/Properties;
    //   73: aload 8
    //   75: invokevirtual 1414	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   78: aload 8
    //   80: astore 5
    //   82: goto +3 -> 85
    //   85: aload 5
    //   87: astore 6
    //   89: aload 5
    //   91: astore 7
    //   93: getstatic 1401	com/intsig/camscanner/a/j:b	Ljava/util/Properties;
    //   96: ldc_w 1421
    //   99: aconst_null
    //   100: invokevirtual 1419	java/util/Properties:getProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   103: astore 8
    //   105: iload_1
    //   106: istore_0
    //   107: aload 8
    //   109: ifnull +30 -> 139
    //   112: aload 5
    //   114: astore 6
    //   116: aload 5
    //   118: astore 7
    //   120: aload 8
    //   122: ldc_w 1428
    //   125: invokevirtual 1064	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   128: istore 4
    //   130: iload_1
    //   131: istore_0
    //   132: iload 4
    //   134: ifeq +5 -> 139
    //   137: iconst_1
    //   138: istore_0
    //   139: iload_0
    //   140: istore_1
    //   141: aload 5
    //   143: ifnull +83 -> 226
    //   146: iload_0
    //   147: istore_1
    //   148: aload 5
    //   150: invokevirtual 51	java/io/InputStream:close	()V
    //   153: iload_0
    //   154: ireturn
    //   155: astore 5
    //   157: ldc 53
    //   159: aload 5
    //   161: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   164: iload_1
    //   165: ireturn
    //   166: astore 5
    //   168: goto +60 -> 228
    //   171: astore 5
    //   173: aload 7
    //   175: astore 6
    //   177: aload 5
    //   179: astore 7
    //   181: goto +18 -> 199
    //   184: astore 7
    //   186: aload 5
    //   188: astore 6
    //   190: aload 7
    //   192: astore 5
    //   194: goto +34 -> 228
    //   197: astore 7
    //   199: aload 6
    //   201: astore 5
    //   203: ldc 53
    //   205: aload 7
    //   207: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   210: iload_2
    //   211: istore_1
    //   212: aload 6
    //   214: ifnull +12 -> 226
    //   217: iload_3
    //   218: istore_1
    //   219: aload 6
    //   221: invokevirtual 51	java/io/InputStream:close	()V
    //   224: iload_2
    //   225: istore_1
    //   226: iload_1
    //   227: ireturn
    //   228: aload 6
    //   230: ifnull +20 -> 250
    //   233: aload 6
    //   235: invokevirtual 51	java/io/InputStream:close	()V
    //   238: goto +12 -> 250
    //   241: astore 6
    //   243: ldc 53
    //   245: aload 6
    //   247: invokestatic 58	com/intsig/q/e:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   250: aload 5
    //   252: athrow
    //   253: aconst_null
    //   254: astore 5
    //   256: goto -171 -> 85
    // Local variable table:
    //   start	length	slot	name	signature
    //   106	48	0	bool1	boolean
    //   11	216	1	bool2	boolean
    //   9	216	2	bool3	boolean
    //   7	211	3	bool4	boolean
    //   128	5	4	bool5	boolean
    //   14	135	5	localObject1	Object
    //   155	5	5	localException1	Exception
    //   166	1	5	localObject2	Object
    //   171	16	5	localIOException1	IOException
    //   192	63	5	localObject3	Object
    //   1	233	6	localObject4	Object
    //   241	5	6	localException2	Exception
    //   4	176	7	localObject5	Object
    //   184	7	7	localObject6	Object
    //   197	9	7	localIOException2	IOException
    //   60	61	8	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   148	153	155	java/lang/Exception
    //   219	224	155	java/lang/Exception
    //   70	78	166	finally
    //   93	105	166	finally
    //   120	130	166	finally
    //   70	78	171	java/io/IOException
    //   93	105	171	java/io/IOException
    //   120	130	171	java/io/IOException
    //   16	22	184	finally
    //   26	36	184	finally
    //   40	62	184	finally
    //   203	210	184	finally
    //   16	22	197	java/io/IOException
    //   26	36	197	java/io/IOException
    //   40	62	197	java/io/IOException
    //   233	238	241	java/lang/Exception
  }
  
  public static void m(Context paramContext)
  {
    if (!g(paramContext))
    {
      n(paramContext);
      return;
    }
    av.e(paramContext, "com.intsig.camscanner_SYNC_MANUNAL");
  }
  
  public static void n(Context paramContext)
  {
    new com.intsig.d.c(paramContext).d(2131691420).e(2131690317).c(2131691270, new w(paramContext)).b(17039360, null).a().show();
  }
  
  public static boolean o(Context paramContext)
  {
    return (d(paramContext, "com.intsig.BizCardReader")) || (d(paramContext, "com.intsig.BCRLite")) || (d(paramContext, "com.intsig.BCRLite_cn")) || (d(paramContext, "com.intsig.BCRLatam"));
  }
  
  public static String p(Context paramContext)
  {
    String str;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.google.android.gms", 0);
      int i = paramContext.versionCode;
      str = paramContext.versionName;
      try
      {
        paramContext = new StringBuilder();
        paramContext.append("getGooglePlayServiceVersion = ");
        paramContext.append(str);
        paramContext.append(", code = ");
        paramContext.append(i);
        com.intsig.q.e.b("AppUtil", paramContext.toString());
        return str;
      }
      catch (PackageManager.NameNotFoundException paramContext) {}
      localStringBuilder = new StringBuilder();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      str = null;
    }
    StringBuilder localStringBuilder;
    localStringBuilder.append("getAppVersion NameNotFoundException ");
    localStringBuilder.append(paramContext);
    com.intsig.q.e.c("AppUtil", localStringBuilder.toString());
    return str;
  }
  
  public static CharSequence q(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder(1024);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    int j = Math.min(localDisplayMetrics.heightPixels, localDisplayMetrics.widthPixels);
    int i = Math.max(localDisplayMetrics.heightPixels, localDisplayMetrics.widthPixels);
    int k = Math.max(j, 960);
    j = Math.max(i, 1280);
    localStringBuilder.append("\nMax pixels:");
    localStringBuilder.append(bg.a(4915200L));
    localStringBuilder.append("\nMin Width:");
    localStringBuilder.append(k);
    localStringBuilder.append("\nMax Width:");
    localStringBuilder.append(j);
    localStringBuilder.append("\nMax pixels(New):");
    i = j * j * 4;
    localStringBuilder.append(bg.a(i));
    localStringBuilder.append("\nMax Memory:");
    localStringBuilder.append(bg.a(Runtime.getRuntime().maxMemory()));
    float f = i * 3 * 100.0F / (float)Runtime.getRuntime().maxMemory();
    localStringBuilder.append("\nRatio(New):");
    localStringBuilder.append(String.format("%.3f%%", new Object[] { Float.valueOf(f) }));
    i = j;
    if (f > 25.0F)
    {
      if (f > 35.0F) {
        i = (int)(j - 50.0F * (localDisplayMetrics.density + 2.0F));
      }
      for (;;)
      {
        break;
        if (f > 30.0F)
        {
          i = (int)(j - 50.0F * (localDisplayMetrics.density + 1.0F));
        }
        else
        {
          i = j;
          if (f <= 25.0F) {
            break;
          }
          i = (int)(j - 50.0F * localDisplayMetrics.density);
        }
      }
      localStringBuilder.append("\nMax Width(Adjust):");
      localStringBuilder.append(i);
      localStringBuilder.append("\nMax pixels(Adjust):");
      j = i * i * 4;
      localStringBuilder.append(bg.a(j));
      f = j * 3 * 100.0F / (float)Runtime.getRuntime().maxMemory();
      localStringBuilder.append("\nRatio(Adjust):");
      localStringBuilder.append(String.format("%.3f%%", new Object[] { Float.valueOf(f) }));
    }
    f = 1.47456E9F / (float)Runtime.getRuntime().maxMemory();
    localStringBuilder.append("\nRatio(Old):");
    localStringBuilder.append(String.format("%.3f%%", new Object[] { Float.valueOf(f) }));
    c.e = k;
    c.f = i;
    localStringBuilder.append("\n\n");
    localStringBuilder.append("AppConfig.MIN_SIDE_LENGTH ");
    localStringBuilder.append(c.e);
    localStringBuilder.append("\n");
    localStringBuilder.append("AppConfig.MAX_DISPLAY_WIDTH ");
    localStringBuilder.append(c.f);
    return localStringBuilder;
  }
  
  public static boolean r(Context paramContext)
  {
    boolean bool2 = com.intsig.util.r.m();
    boolean bool1 = bool2;
    if (bool2) {
      bool1 = TextUtils.isEmpty(ao.b(paramContext));
    }
    return bool1;
  }
  
  public static void s(Context paramContext)
  {
    String str2 = paramContext.getPackageName();
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("/data/data/");
    ((StringBuilder)localObject1).append(str2);
    Object localObject2 = ((StringBuilder)localObject1).toString();
    localObject1 = localObject2;
    if (Build.VERSION.SDK_INT >= 23)
    {
      String str1 = paramContext.getApplicationInfo().dataDir;
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("privateLisPath=");
      ((StringBuilder)localObject1).append(str1);
      com.intsig.q.e.b("AppUtil", ((StringBuilder)localObject1).toString());
      localObject1 = localObject2;
      if (!TextUtils.isEmpty(str1))
      {
        localObject1 = localObject2;
        if (!str1.contains((CharSequence)localObject2))
        {
          localObject1 = localObject2;
          if (str1.contains(str2)) {
            localObject1 = str1;
          }
        }
      }
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("/lib/");
    com.intsig.util.a.a(((StringBuilder)localObject2).toString());
    if (!b(false))
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("/");
      ((StringBuilder)localObject2).append("libs");
      ((StringBuilder)localObject2).append("/");
      com.intsig.util.a.a(((StringBuilder)localObject2).toString());
      if ((com.intsig.util.r.u()) && (b(false)))
      {
        x(paramContext);
        return;
      }
      new n("initEnginePaths", (String)localObject1, str2, paramContext).start();
      return;
    }
    x(paramContext);
  }
  
  public static String t(Context paramContext)
  {
    try
    {
      paramContext = (android.content.ClipboardManager)paramContext.getSystemService("clipboard");
      if ((paramContext.hasPrimaryClip()) && (paramContext.getPrimaryClipDescription().hasMimeType("text/plain")))
      {
        paramContext = paramContext.getPrimaryClip().getItemAt(0).getText().toString();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      com.intsig.q.e.a("AppUtil", paramContext);
    }
    return "";
  }
  
  public static String u(Context paramContext)
  {
    paramContext = new StringBuilder();
    paramContext.append("http://www.camscanner.com/mobile/camcard?language=");
    paramContext.append(com.intsig.h.f.b());
    Object localObject = paramContext.toString();
    paramContext = (Context)localObject;
    if (av.d())
    {
      paramContext = new StringBuilder();
      paramContext.append((String)localObject);
      paramContext.append("&type=premium");
      paramContext = paramContext.toString();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getCCDownloadHintWebviewUrl = ");
    ((StringBuilder)localObject).append(paramContext);
    com.intsig.q.e.b("AppUtil", ((StringBuilder)localObject).toString());
    return paramContext;
  }
  
  public static long v(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    paramContext.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem;
  }
  
  private static void x(Context paramContext)
  {
    
    int i;
    try
    {
      String str = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString("com.intsig.camscanner.scanner");
      i = ScannerEngine.initScannerEngine(paramContext, str);
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("metaValue=");
      localStringBuilder2.append(str);
      com.intsig.q.e.b("AppUtil", localStringBuilder2.toString());
    }
    catch (Exception localException)
    {
      com.intsig.q.e.b("AppUtil", localException);
      i = ScannerEngine.initScannerEngine(paramContext, "79421895ae6636767d7300455003-PnzFpnaare");
    }
    if (i != 0) {
      a = true;
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("resultCode=");
    localStringBuilder1.append(i);
    com.intsig.q.e.b("AppUtil", localStringBuilder1.toString());
    PinyinUtil.init(paramContext);
  }
}
