package com.kauf.marketing;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Iterator;
import java.util.List;

public class UserInfos
{
  public UserInfos() {}
  
  /* Error */
  @android.annotation.SuppressLint({"NewApi"})
  public static StringBuilder UserParams(Activity paramActivity)
  {
    // Byte code:
    //   0: new 22	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 23	java/lang/StringBuilder:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: new 22	java/lang/StringBuilder
    //   12: dup
    //   13: ldc 25
    //   15: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   18: getstatic 34	com/kauf/settings/MyApplication:googlePlayServicesAdId	Ljava/lang/String;
    //   21: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   24: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   27: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   30: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload_2
    //   41: new 22	java/lang/StringBuilder
    //   44: dup
    //   45: ldc 59
    //   47: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   50: getstatic 63	com/kauf/settings/MyApplication:googlePlayServicesAdLimitTracking	Z
    //   53: invokevirtual 66	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   56: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   59: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: aload_2
    //   64: new 22	java/lang/StringBuilder
    //   67: dup
    //   68: ldc 68
    //   70: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   73: aload_0
    //   74: invokevirtual 74	android/app/Activity:getTitle	()Ljava/lang/CharSequence;
    //   77: invokeinterface 77 1 0
    //   82: ldc 79
    //   84: ldc 81
    //   86: invokevirtual 87	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   89: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   92: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   95: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   98: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   104: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: pop
    //   108: aload_2
    //   109: new 22	java/lang/StringBuilder
    //   112: dup
    //   113: ldc 89
    //   115: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   118: aload_0
    //   119: invokevirtual 92	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   122: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   125: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   128: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   131: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   137: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: pop
    //   141: aload_2
    //   142: new 22	java/lang/StringBuilder
    //   145: dup
    //   146: ldc 94
    //   148: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   151: invokestatic 100	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   154: invokevirtual 103	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   157: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   160: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   163: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   166: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   172: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: pop
    //   176: aload_2
    //   177: new 22	java/lang/StringBuilder
    //   180: dup
    //   181: ldc 105
    //   183: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   186: aload_0
    //   187: invokestatic 109	com/kauf/marketing/UserInfos:getVersionName	(Landroid/app/Activity;)Ljava/lang/String;
    //   190: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   193: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   196: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   199: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   205: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: pop
    //   209: aload_2
    //   210: new 22	java/lang/StringBuilder
    //   213: dup
    //   214: ldc 111
    //   216: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   219: aload_0
    //   220: invokevirtual 115	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   223: aload_0
    //   224: invokevirtual 92	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   227: iconst_1
    //   228: invokevirtual 121	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   231: getfield 127	android/content/pm/PackageInfo:versionCode	I
    //   234: invokevirtual 130	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   237: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   240: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: pop
    //   244: aload_0
    //   245: invokevirtual 134	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   248: ldc -120
    //   250: invokestatic 142	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   253: astore_3
    //   254: aload_3
    //   255: astore_1
    //   256: aload_3
    //   257: ifnonnull +6 -> 263
    //   260: ldc 81
    //   262: astore_1
    //   263: aload_2
    //   264: new 22	java/lang/StringBuilder
    //   267: dup
    //   268: ldc -112
    //   270: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   273: aload_1
    //   274: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   277: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   280: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   283: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   289: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   292: pop
    //   293: aload_2
    //   294: new 22	java/lang/StringBuilder
    //   297: dup
    //   298: ldc -110
    //   300: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   303: aload_1
    //   304: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   307: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   310: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   313: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   319: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   322: pop
    //   323: aload_2
    //   324: new 22	java/lang/StringBuilder
    //   327: dup
    //   328: ldc -108
    //   330: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   333: getstatic 153	android/os/Build$VERSION:INCREMENTAL	Ljava/lang/String;
    //   336: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   339: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   342: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   345: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   351: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   354: pop
    //   355: aload_2
    //   356: new 22	java/lang/StringBuilder
    //   359: dup
    //   360: ldc -101
    //   362: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   365: getstatic 158	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   368: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   371: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   374: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   377: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   383: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: pop
    //   387: aload_2
    //   388: new 22	java/lang/StringBuilder
    //   391: dup
    //   392: ldc -96
    //   394: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   397: getstatic 163	android/os/Build$VERSION:SDK_INT	I
    //   400: invokestatic 167	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   403: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   406: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   409: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   412: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   418: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   421: pop
    //   422: aload_2
    //   423: new 22	java/lang/StringBuilder
    //   426: dup
    //   427: ldc -87
    //   429: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   432: getstatic 174	android/os/Build:BOARD	Ljava/lang/String;
    //   435: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   438: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   441: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   444: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   450: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: pop
    //   454: aload_2
    //   455: new 22	java/lang/StringBuilder
    //   458: dup
    //   459: ldc -80
    //   461: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   464: getstatic 179	android/os/Build:BRAND	Ljava/lang/String;
    //   467: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   470: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   473: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   476: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   479: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   482: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: pop
    //   486: aload_2
    //   487: new 22	java/lang/StringBuilder
    //   490: dup
    //   491: ldc -75
    //   493: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   496: getstatic 184	android/os/Build:DEVICE	Ljava/lang/String;
    //   499: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   502: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   505: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   508: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   511: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   514: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   517: pop
    //   518: aload_2
    //   519: new 22	java/lang/StringBuilder
    //   522: dup
    //   523: ldc -70
    //   525: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   528: getstatic 189	android/os/Build:DISPLAY	Ljava/lang/String;
    //   531: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   534: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   537: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   540: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   546: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   549: pop
    //   550: aload_2
    //   551: new 22	java/lang/StringBuilder
    //   554: dup
    //   555: ldc -65
    //   557: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   560: getstatic 194	android/os/Build:FINGERPRINT	Ljava/lang/String;
    //   563: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   566: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   569: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   572: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   578: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   581: pop
    //   582: aload_2
    //   583: new 22	java/lang/StringBuilder
    //   586: dup
    //   587: ldc -60
    //   589: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   592: getstatic 199	android/os/Build:HOST	Ljava/lang/String;
    //   595: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   598: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   601: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   604: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   607: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   610: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   613: pop
    //   614: aload_2
    //   615: new 22	java/lang/StringBuilder
    //   618: dup
    //   619: ldc -55
    //   621: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   624: getstatic 204	android/os/Build:ID	Ljava/lang/String;
    //   627: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   630: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   633: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   636: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   639: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   642: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   645: pop
    //   646: aload_2
    //   647: new 22	java/lang/StringBuilder
    //   650: dup
    //   651: ldc -50
    //   653: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   656: getstatic 209	android/os/Build:MODEL	Ljava/lang/String;
    //   659: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   662: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   665: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   668: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   671: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   674: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   677: pop
    //   678: aload_2
    //   679: new 22	java/lang/StringBuilder
    //   682: dup
    //   683: ldc -45
    //   685: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   688: getstatic 214	android/os/Build:PRODUCT	Ljava/lang/String;
    //   691: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   694: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   697: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   700: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   703: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   706: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   709: pop
    //   710: aload_2
    //   711: new 22	java/lang/StringBuilder
    //   714: dup
    //   715: ldc -40
    //   717: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   720: getstatic 219	android/os/Build:TAGS	Ljava/lang/String;
    //   723: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   726: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   729: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   732: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   735: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   738: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   741: pop
    //   742: aload_2
    //   743: new 22	java/lang/StringBuilder
    //   746: dup
    //   747: ldc -35
    //   749: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   752: getstatic 225	android/os/Build:TIME	J
    //   755: invokestatic 228	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   758: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   761: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   764: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   767: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   770: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   773: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   776: pop
    //   777: aload_2
    //   778: new 22	java/lang/StringBuilder
    //   781: dup
    //   782: ldc -26
    //   784: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   787: getstatic 233	android/os/Build:TYPE	Ljava/lang/String;
    //   790: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   793: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   796: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   799: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   802: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   805: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   808: pop
    //   809: aload_2
    //   810: new 22	java/lang/StringBuilder
    //   813: dup
    //   814: ldc -21
    //   816: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   819: getstatic 238	android/os/Build:USER	Ljava/lang/String;
    //   822: invokestatic 40	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   825: invokevirtual 44	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   828: invokestatic 50	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   831: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   834: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   837: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   840: pop
    //   841: aload_2
    //   842: ldc -16
    //   844: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   847: pop
    //   848: new 242	android/util/DisplayMetrics
    //   851: dup
    //   852: invokespecial 243	android/util/DisplayMetrics:<init>	()V
    //   855: astore_1
    //   856: aload_0
    //   857: invokevirtual 247	android/app/Activity:getWindowManager	()Landroid/view/WindowManager;
    //   860: invokeinterface 253 1 0
    //   865: aload_1
    //   866: invokevirtual 259	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   869: aload_2
    //   870: new 22	java/lang/StringBuilder
    //   873: dup
    //   874: ldc_w 261
    //   877: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   880: aload_1
    //   881: getfield 264	android/util/DisplayMetrics:widthPixels	I
    //   884: invokevirtual 130	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   887: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   890: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   893: pop
    //   894: aload_2
    //   895: new 22	java/lang/StringBuilder
    //   898: dup
    //   899: ldc_w 266
    //   902: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   905: aload_1
    //   906: getfield 269	android/util/DisplayMetrics:heightPixels	I
    //   909: invokevirtual 130	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   912: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   915: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   918: pop
    //   919: getstatic 163	android/os/Build$VERSION:SDK_INT	I
    //   922: bipush 17
    //   924: if_icmplt +74 -> 998
    //   927: new 242	android/util/DisplayMetrics
    //   930: dup
    //   931: invokespecial 243	android/util/DisplayMetrics:<init>	()V
    //   934: astore_3
    //   935: aload_0
    //   936: invokevirtual 247	android/app/Activity:getWindowManager	()Landroid/view/WindowManager;
    //   939: invokeinterface 253 1 0
    //   944: aload_3
    //   945: invokevirtual 272	android/view/Display:getRealMetrics	(Landroid/util/DisplayMetrics;)V
    //   948: aload_2
    //   949: new 22	java/lang/StringBuilder
    //   952: dup
    //   953: ldc_w 274
    //   956: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   959: aload_3
    //   960: getfield 264	android/util/DisplayMetrics:widthPixels	I
    //   963: invokevirtual 130	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   966: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   969: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   972: pop
    //   973: aload_2
    //   974: new 22	java/lang/StringBuilder
    //   977: dup
    //   978: ldc_w 276
    //   981: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   984: aload_3
    //   985: getfield 269	android/util/DisplayMetrics:heightPixels	I
    //   988: invokevirtual 130	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   991: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   994: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   997: pop
    //   998: aload_2
    //   999: new 22	java/lang/StringBuilder
    //   1002: dup
    //   1003: ldc_w 278
    //   1006: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1009: aload_1
    //   1010: getfield 282	android/util/DisplayMetrics:density	F
    //   1013: invokevirtual 285	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   1016: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1019: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1022: pop
    //   1023: aload_2
    //   1024: new 22	java/lang/StringBuilder
    //   1027: dup
    //   1028: ldc_w 287
    //   1031: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1034: aload_0
    //   1035: invokestatic 293	com/kauf/util/Device:isTablet	(Landroid/content/Context;)Z
    //   1038: invokevirtual 66	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   1041: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1044: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1047: pop
    //   1048: aload_2
    //   1049: new 22	java/lang/StringBuilder
    //   1052: dup
    //   1053: ldc_w 295
    //   1056: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1059: aload_0
    //   1060: invokestatic 298	com/kauf/util/Device:isTV	(Landroid/content/Context;)Z
    //   1063: invokevirtual 66	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   1066: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1069: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1072: pop
    //   1073: aload_2
    //   1074: new 22	java/lang/StringBuilder
    //   1077: dup
    //   1078: ldc_w 300
    //   1081: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1084: getstatic 305	com/kauf/settings/User:AGE	I
    //   1087: invokevirtual 130	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1090: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1093: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1096: pop
    //   1097: aload_2
    //   1098: new 22	java/lang/StringBuilder
    //   1101: dup
    //   1102: ldc_w 307
    //   1105: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1108: getstatic 310	com/kauf/settings/User:GENDER	Ljava/lang/String;
    //   1111: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1114: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1117: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1120: pop
    //   1121: aload_2
    //   1122: astore_0
    //   1123: aload_2
    //   1124: invokevirtual 314	java/lang/StringBuilder:length	()I
    //   1127: ifle +9 -> 1136
    //   1130: aload_2
    //   1131: iconst_0
    //   1132: invokevirtual 317	java/lang/StringBuilder:deleteCharAt	(I)Ljava/lang/StringBuilder;
    //   1135: astore_0
    //   1136: aload_0
    //   1137: areturn
    //   1138: astore_1
    //   1139: getstatic 323	java/lang/System:out	Ljava/io/PrintStream;
    //   1142: new 22	java/lang/StringBuilder
    //   1145: dup
    //   1146: ldc_w 325
    //   1149: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1152: aload_1
    //   1153: invokevirtual 328	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1156: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1159: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1162: invokevirtual 333	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1165: goto -921 -> 244
    //   1168: astore_1
    //   1169: goto -328 -> 841
    //   1172: astore_1
    //   1173: goto -364 -> 809
    //   1176: astore_1
    //   1177: goto -400 -> 777
    //   1180: astore_1
    //   1181: goto -439 -> 742
    //   1184: astore_1
    //   1185: goto -475 -> 710
    //   1188: astore_1
    //   1189: goto -511 -> 678
    //   1192: astore_1
    //   1193: goto -547 -> 646
    //   1196: astore_1
    //   1197: goto -583 -> 614
    //   1200: astore_1
    //   1201: goto -619 -> 582
    //   1204: astore_1
    //   1205: goto -655 -> 550
    //   1208: astore_1
    //   1209: goto -691 -> 518
    //   1212: astore_1
    //   1213: goto -727 -> 486
    //   1216: astore_1
    //   1217: goto -763 -> 454
    //   1220: astore_1
    //   1221: goto -799 -> 422
    //   1224: astore_1
    //   1225: goto -838 -> 387
    //   1228: astore_1
    //   1229: goto -874 -> 355
    //   1232: astore_1
    //   1233: goto -910 -> 323
    //   1236: astore_3
    //   1237: goto -944 -> 293
    //   1240: astore_1
    //   1241: goto -997 -> 244
    //   1244: astore_1
    //   1245: goto -1036 -> 209
    //   1248: astore_1
    //   1249: goto -1073 -> 176
    //   1252: astore_1
    //   1253: goto -1112 -> 141
    //   1256: astore_1
    //   1257: goto -1149 -> 108
    //   1260: astore_1
    //   1261: goto -1221 -> 40
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1264	0	paramActivity	Activity
    //   255	755	1	localObject1	Object
    //   1138	15	1	localException	Exception
    //   1168	1	1	localUnsupportedEncodingException1	java.io.UnsupportedEncodingException
    //   1172	1	1	localUnsupportedEncodingException2	java.io.UnsupportedEncodingException
    //   1176	1	1	localUnsupportedEncodingException3	java.io.UnsupportedEncodingException
    //   1180	1	1	localUnsupportedEncodingException4	java.io.UnsupportedEncodingException
    //   1184	1	1	localUnsupportedEncodingException5	java.io.UnsupportedEncodingException
    //   1188	1	1	localUnsupportedEncodingException6	java.io.UnsupportedEncodingException
    //   1192	1	1	localUnsupportedEncodingException7	java.io.UnsupportedEncodingException
    //   1196	1	1	localUnsupportedEncodingException8	java.io.UnsupportedEncodingException
    //   1200	1	1	localUnsupportedEncodingException9	java.io.UnsupportedEncodingException
    //   1204	1	1	localUnsupportedEncodingException10	java.io.UnsupportedEncodingException
    //   1208	1	1	localUnsupportedEncodingException11	java.io.UnsupportedEncodingException
    //   1212	1	1	localUnsupportedEncodingException12	java.io.UnsupportedEncodingException
    //   1216	1	1	localUnsupportedEncodingException13	java.io.UnsupportedEncodingException
    //   1220	1	1	localUnsupportedEncodingException14	java.io.UnsupportedEncodingException
    //   1224	1	1	localUnsupportedEncodingException15	java.io.UnsupportedEncodingException
    //   1228	1	1	localUnsupportedEncodingException16	java.io.UnsupportedEncodingException
    //   1232	1	1	localUnsupportedEncodingException17	java.io.UnsupportedEncodingException
    //   1240	1	1	localNameNotFoundException	PackageManager.NameNotFoundException
    //   1244	1	1	localUnsupportedEncodingException18	java.io.UnsupportedEncodingException
    //   1248	1	1	localUnsupportedEncodingException19	java.io.UnsupportedEncodingException
    //   1252	1	1	localUnsupportedEncodingException20	java.io.UnsupportedEncodingException
    //   1256	1	1	localUnsupportedEncodingException21	java.io.UnsupportedEncodingException
    //   1260	1	1	localUnsupportedEncodingException22	java.io.UnsupportedEncodingException
    //   7	1124	2	localStringBuilder	StringBuilder
    //   253	732	3	localObject2	Object
    //   1236	1	3	localUnsupportedEncodingException23	java.io.UnsupportedEncodingException
    // Exception table:
    //   from	to	target	type
    //   209	244	1138	java/lang/Exception
    //   809	841	1168	java/io/UnsupportedEncodingException
    //   777	809	1172	java/io/UnsupportedEncodingException
    //   742	777	1176	java/io/UnsupportedEncodingException
    //   710	742	1180	java/io/UnsupportedEncodingException
    //   678	710	1184	java/io/UnsupportedEncodingException
    //   646	678	1188	java/io/UnsupportedEncodingException
    //   614	646	1192	java/io/UnsupportedEncodingException
    //   582	614	1196	java/io/UnsupportedEncodingException
    //   550	582	1200	java/io/UnsupportedEncodingException
    //   518	550	1204	java/io/UnsupportedEncodingException
    //   486	518	1208	java/io/UnsupportedEncodingException
    //   454	486	1212	java/io/UnsupportedEncodingException
    //   422	454	1216	java/io/UnsupportedEncodingException
    //   387	422	1220	java/io/UnsupportedEncodingException
    //   355	387	1224	java/io/UnsupportedEncodingException
    //   323	355	1228	java/io/UnsupportedEncodingException
    //   293	323	1232	java/io/UnsupportedEncodingException
    //   263	293	1236	java/io/UnsupportedEncodingException
    //   209	244	1240	android/content/pm/PackageManager$NameNotFoundException
    //   176	209	1244	java/io/UnsupportedEncodingException
    //   141	176	1248	java/io/UnsupportedEncodingException
    //   108	141	1252	java/io/UnsupportedEncodingException
    //   63	108	1256	java/io/UnsupportedEncodingException
    //   8	40	1260	java/io/UnsupportedEncodingException
  }
  
  public static String getOwnAppsInstalled(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
        boolean bool = paramContext.hasNext();
        if (bool) {
          continue;
        }
      }
      catch (Exception paramContext)
      {
        ApplicationInfo localApplicationInfo;
        continue;
      }
      return localStringBuilder.toString();
      localApplicationInfo = (ApplicationInfo)paramContext.next();
      if ((localApplicationInfo.packageName.startsWith("com.kauf.")) || (localApplicationInfo.packageName.startsWith("com.wonderfulgames."))) {
        localStringBuilder.append(localApplicationInfo.packageName + "|");
      }
    }
  }
  
  public static String getVersionName(Activity paramActivity)
  {
    try
    {
      paramActivity = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0).versionName;
      return paramActivity;
    }
    catch (PackageManager.NameNotFoundException paramActivity) {}
    return "0.0";
  }
  
  public static int numberOwnAppsInstalled(Context paramContext)
  {
    j = 0;
    int i = 0;
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      for (;;)
      {
        j = i;
        if (!paramContext.hasNext()) {
          return i;
        }
        j = i;
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        j = i;
        if (!localApplicationInfo.packageName.startsWith("com.kauf."))
        {
          j = i;
          boolean bool = localApplicationInfo.packageName.startsWith("com.wonderfulgames.");
          if (!bool) {}
        }
        else
        {
          i += 1;
        }
      }
      return j;
    }
    catch (Exception paramContext) {}
  }
}
