package com.codename1.impl.android;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.Parcelable;
import android.os.Vibrator;
import android.provider.MediaStore.Images.Media;
import android.provider.Settings.Secure;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.Html;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JavascriptInterface;
import android.webkit.MimeTypeMap;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TimePicker;
import com.codename1.f.m;
import com.codename1.f.t;
import com.codename1.f.u;
import com.codename1.p.ab;
import com.codename1.p.ad;
import com.codename1.p.ag;
import com.codename1.p.an;
import com.codename1.p.at;
import com.codename1.p.au;
import com.codename1.p.au.b;
import com.codename1.p.b.a.a;
import com.codename1.p.l;
import com.codename1.p.p;
import com.codename1.p.q;
import com.codename1.p.v;
import com.codename1.p.w;
import com.codename1.p.x;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

public class c
  extends com.codename1.impl.a
  implements k
{
  private static int A;
  private static int B;
  private static HashSet<Context> H;
  private static String I;
  private static c J;
  private static boolean K;
  private static boolean L;
  private static CookieManager R;
  private static final char[] S = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  private static boolean T;
  private static boolean U;
  private static final String[] V = { "android.NotificationChannel.id", "android.NotificationChannel.name", "android.NotificationChannel.description", "android.NotificationChannel.importance", "android.NotificationChannel.enableLights", "android.NotificationChannel.lightColor", "android.NotificationChannel.enableVibration", "android.NotificationChannel.vibrationPattern" };
  private static Map<String, String> W;
  public static final Thread.UncaughtExceptionHandler a = new Thread.UncaughtExceptionHandler()
  {
    public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
    {
      if (m.f())
      {
        m.a("Uncaught exception in thread " + paramAnonymousThread.getName());
        m.a(paramAnonymousThrowable);
        m.b();
      }
    }
  };
  static int[] b = { 42085 };
  static CodenameOneActivity f;
  public static boolean j = true;
  public static boolean k = false;
  static final Map<View, e> l;
  public static com.codename1.a.a m;
  private static Context u;
  private static View y;
  private static View z;
  private boolean C = true;
  private boolean D;
  private MediaRecorder E = null;
  private boolean F;
  private boolean G = true;
  private b M;
  private int N = -1;
  private boolean O;
  private boolean P;
  private int Q = 0;
  private com.codename1.g.d X;
  private com.codename1.p.k.c Y;
  private com.codename1.j.a Z;
  g c = null;
  h d;
  protected int e;
  RelativeLayout g;
  final Vector h = new Vector();
  int i;
  boolean n;
  private final char[] o = new char[1];
  private final Rect p = new Rect();
  private Vibrator q = null;
  private boolean r = false;
  private int s;
  private int t;
  private com.codename1.p.k.b v;
  private int w = -1;
  private HashMap x;
  
  static
  {
    H = new HashSet();
    I = "CN1$AndroidNotificationCategories";
    l = new HashMap();
  }
  
  public c() {}
  
  private static Map<String, String> D(String paramString)
  {
    String[] arrayOfString = paramString.split("&");
    HashMap localHashMap = new HashMap();
    int i2 = arrayOfString.length;
    int i1 = 0;
    if (i1 < i2)
    {
      Object localObject = arrayOfString[i1];
      int i3 = ((String)localObject).indexOf("=");
      String str;
      if (i3 > 0)
      {
        paramString = ((String)localObject).substring(0, i3);
        str = ((String)localObject).substring(i3 + 1);
        localObject = paramString;
      }
      for (paramString = str;; paramString = "")
      {
        for (;;)
        {
          try
          {
            str = URLDecoder.decode((String)localObject, "UTF-8");
            localObject = str;
          }
          catch (Exception localException2)
          {
            continue;
          }
          try
          {
            str = URLDecoder.decode(paramString, "UTF-8");
            paramString = str;
          }
          catch (Exception localException1) {}
        }
        localHashMap.put(localObject, paramString);
        i1 += 1;
        break;
      }
    }
    return localHashMap;
  }
  
  private Typeface E(String paramString)
  {
    if ("native:MainThin".equals(paramString)) {
      return Typeface.create("sans-serif-thin", 0);
    }
    if ("native:MainLight".equals(paramString)) {
      return Typeface.create("sans-serif-light", 0);
    }
    if ("native:MainRegular".equals(paramString)) {
      return Typeface.create("sans-serif", 0);
    }
    if ("native:MainBold".equals(paramString)) {
      return Typeface.create("sans-serif-condensed", 1);
    }
    if ("native:MainBlack".equals(paramString)) {
      return Typeface.create("sans-serif-black", 1);
    }
    if ("native:ItalicThin".equals(paramString)) {
      return Typeface.create("sans-serif-thin", 2);
    }
    if ("native:ItalicLight".equals(paramString)) {
      return Typeface.create("sans-serif-thin", 2);
    }
    if ("native:ItalicRegular".equals(paramString)) {
      return Typeface.create("sans-serif", 2);
    }
    if ("native:ItalicBold".equals(paramString)) {
      return Typeface.create("sans-serif-condensed", 3);
    }
    if ("native:ItalicBlack".equals(paramString)) {
      return Typeface.create("sans-serif-black", 3);
    }
    throw new IllegalArgumentException("Unsupported native font type: " + paramString);
  }
  
  private String F(String paramString)
  {
    String str1 = null;
    String str2 = MimeTypeMap.getFileExtensionFromUrl(paramString);
    if (str2 != null) {
      str1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str2);
    }
    str2 = str1;
    if (str1 == null) {}
    try
    {
      paramString = Uri.parse(paramString);
      str2 = ba().getContentResolver().getType(paramString);
      return str2;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return str1;
  }
  
  /* Error */
  private Intent G(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 458
    //   4: invokevirtual 462	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   7: ifeq +9 -> 16
    //   10: aload_1
    //   11: iconst_1
    //   12: invokestatic 468	android/content/Intent:parseUri	(Ljava/lang/String;I)Landroid/content/Intent;
    //   15: areturn
    //   16: aload_1
    //   17: ldc_w 470
    //   20: invokevirtual 462	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   23: ifne +13 -> 36
    //   26: aload_1
    //   27: ldc_w 472
    //   30: invokevirtual 462	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   33: ifeq +17 -> 50
    //   36: ldc_w 474
    //   39: ldc_w 476
    //   42: invokestatic 479	com/codename1/impl/android/c:d	(Ljava/lang/String;Ljava/lang/String;)Z
    //   45: ifne +5 -> 50
    //   48: aconst_null
    //   49: areturn
    //   50: new 464	android/content/Intent
    //   53: dup
    //   54: invokespecial 480	android/content/Intent:<init>	()V
    //   57: astore_3
    //   58: aload_3
    //   59: ldc_w 482
    //   62: invokevirtual 485	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   65: pop
    //   66: aload_1
    //   67: ldc_w 470
    //   70: invokevirtual 462	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   73: ifeq +193 -> 266
    //   76: new 487	java/io/File
    //   79: dup
    //   80: aload_1
    //   81: invokespecial 488	java/io/File:<init>	(Ljava/lang/String;)V
    //   84: astore 4
    //   86: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   89: new 401	java/lang/StringBuilder
    //   92: dup
    //   93: invokespecial 402	java/lang/StringBuilder:<init>	()V
    //   96: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   99: invokevirtual 491	android/content/Context:getPackageName	()Ljava/lang/String;
    //   102: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: ldc_w 493
    //   108: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: invokevirtual 412	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: aload 4
    //   116: invokestatic 498	android/support/v4/content/FileProvider:a	(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;)Landroid/net/Uri;
    //   119: astore_2
    //   120: getstatic 503	android/os/Build$VERSION:SDK_INT	I
    //   123: bipush 21
    //   125: if_icmpge +114 -> 239
    //   128: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   131: invokevirtual 507	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   134: aload_3
    //   135: ldc_w 508
    //   138: invokevirtual 514	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   141: invokeinterface 520 1 0
    //   146: astore 4
    //   148: aload 4
    //   150: invokeinterface 526 1 0
    //   155: ifeq +84 -> 239
    //   158: aload 4
    //   160: invokeinterface 530 1 0
    //   165: checkcast 532	android/content/pm/ResolveInfo
    //   168: getfield 536	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   171: getfield 541	android/content/pm/ActivityInfo:packageName	Ljava/lang/String;
    //   174: astore 5
    //   176: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   179: aload 5
    //   181: aload_2
    //   182: iconst_3
    //   183: invokevirtual 545	android/content/Context:grantUriPermission	(Ljava/lang/String;Landroid/net/Uri;I)V
    //   186: goto -38 -> 148
    //   189: astore_1
    //   190: aload_1
    //   191: invokestatic 550	com/codename1/f/m:a	(Ljava/lang/Throwable;)V
    //   194: aconst_null
    //   195: areturn
    //   196: astore_2
    //   197: aload 4
    //   199: invokestatic 553	com/codename1/impl/android/c:b	(Ljava/io/File;)Ljava/io/File;
    //   202: astore_2
    //   203: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   206: new 401	java/lang/StringBuilder
    //   209: dup
    //   210: invokespecial 402	java/lang/StringBuilder:<init>	()V
    //   213: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   216: invokevirtual 491	android/content/Context:getPackageName	()Ljava/lang/String;
    //   219: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: ldc_w 493
    //   225: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: invokevirtual 412	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   231: aload_2
    //   232: invokestatic 498	android/support/v4/content/FileProvider:a	(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;)Landroid/net/Uri;
    //   235: astore_2
    //   236: goto -116 -> 120
    //   239: aload_3
    //   240: ldc_w 554
    //   243: invokevirtual 558	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   246: pop
    //   247: aload_0
    //   248: aload_1
    //   249: invokespecial 560	com/codename1/impl/android/c:F	(Ljava/lang/String;)Ljava/lang/String;
    //   252: astore_1
    //   253: aload_1
    //   254: ifnull +231 -> 485
    //   257: aload_3
    //   258: aload_2
    //   259: aload_1
    //   260: invokevirtual 564	android/content/Intent:setDataAndType	(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;
    //   263: pop
    //   264: aload_3
    //   265: areturn
    //   266: aload_1
    //   267: ldc_w 472
    //   270: invokevirtual 462	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   273: ifeq +204 -> 477
    //   276: new 487	java/io/File
    //   279: dup
    //   280: aload_0
    //   281: aload_1
    //   282: invokespecial 566	com/codename1/impl/android/c:I	(Ljava/lang/String;)Ljava/lang/String;
    //   285: invokespecial 488	java/io/File:<init>	(Ljava/lang/String;)V
    //   288: astore 4
    //   290: getstatic 572	java/lang/System:out	Ljava/io/PrintStream;
    //   293: new 401	java/lang/StringBuilder
    //   296: dup
    //   297: invokespecial 402	java/lang/StringBuilder:<init>	()V
    //   300: ldc_w 574
    //   303: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: aload 4
    //   308: invokevirtual 578	java/io/File:length	()J
    //   311: invokevirtual 581	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   314: invokevirtual 412	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   317: invokevirtual 586	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   320: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   323: new 401	java/lang/StringBuilder
    //   326: dup
    //   327: invokespecial 402	java/lang/StringBuilder:<init>	()V
    //   330: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   333: invokevirtual 491	android/content/Context:getPackageName	()Ljava/lang/String;
    //   336: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: ldc_w 493
    //   342: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: invokevirtual 412	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   348: aload 4
    //   350: invokestatic 498	android/support/v4/content/FileProvider:a	(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;)Landroid/net/Uri;
    //   353: astore_2
    //   354: getstatic 503	android/os/Build$VERSION:SDK_INT	I
    //   357: bipush 21
    //   359: if_icmpge +107 -> 466
    //   362: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   365: invokevirtual 507	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   368: aload_3
    //   369: ldc_w 508
    //   372: invokevirtual 514	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   375: invokeinterface 520 1 0
    //   380: astore 4
    //   382: aload 4
    //   384: invokeinterface 526 1 0
    //   389: ifeq +77 -> 466
    //   392: aload 4
    //   394: invokeinterface 530 1 0
    //   399: checkcast 532	android/content/pm/ResolveInfo
    //   402: getfield 536	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   405: getfield 541	android/content/pm/ActivityInfo:packageName	Ljava/lang/String;
    //   408: astore 5
    //   410: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   413: aload 5
    //   415: aload_2
    //   416: iconst_3
    //   417: invokevirtual 545	android/content/Context:grantUriPermission	(Ljava/lang/String;Landroid/net/Uri;I)V
    //   420: goto -38 -> 382
    //   423: astore_2
    //   424: aload 4
    //   426: invokestatic 553	com/codename1/impl/android/c:b	(Ljava/io/File;)Ljava/io/File;
    //   429: astore_2
    //   430: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   433: new 401	java/lang/StringBuilder
    //   436: dup
    //   437: invokespecial 402	java/lang/StringBuilder:<init>	()V
    //   440: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   443: invokevirtual 491	android/content/Context:getPackageName	()Ljava/lang/String;
    //   446: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   449: ldc_w 493
    //   452: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   455: invokevirtual 412	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   458: aload_2
    //   459: invokestatic 498	android/support/v4/content/FileProvider:a	(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;)Landroid/net/Uri;
    //   462: astore_2
    //   463: goto -109 -> 354
    //   466: aload_3
    //   467: ldc_w 554
    //   470: invokevirtual 558	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   473: pop
    //   474: goto -227 -> 247
    //   477: aload_1
    //   478: invokestatic 436	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   481: astore_2
    //   482: goto -235 -> 247
    //   485: aload_3
    //   486: aload_2
    //   487: invokevirtual 590	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   490: pop
    //   491: aload_3
    //   492: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	493	0	this	c
    //   0	493	1	paramString	String
    //   119	63	2	localUri	Uri
    //   196	1	2	localException1	Exception
    //   202	214	2	localObject1	Object
    //   423	1	2	localException2	Exception
    //   429	58	2	localObject2	Object
    //   57	435	3	localIntent	Intent
    //   84	341	4	localObject3	Object
    //   174	240	5	str	String
    // Exception table:
    //   from	to	target	type
    //   0	16	189	java/lang/Exception
    //   16	36	189	java/lang/Exception
    //   36	48	189	java/lang/Exception
    //   50	86	189	java/lang/Exception
    //   120	148	189	java/lang/Exception
    //   148	186	189	java/lang/Exception
    //   197	236	189	java/lang/Exception
    //   239	247	189	java/lang/Exception
    //   247	253	189	java/lang/Exception
    //   257	264	189	java/lang/Exception
    //   266	320	189	java/lang/Exception
    //   354	382	189	java/lang/Exception
    //   382	420	189	java/lang/Exception
    //   424	463	189	java/lang/Exception
    //   466	474	189	java/lang/Exception
    //   477	482	189	java/lang/Exception
    //   485	491	189	java/lang/Exception
    //   86	120	196	java/lang/Exception
    //   320	354	423	java/lang/Exception
  }
  
  private String H(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.startsWith("/")) {
        str = "file://" + paramString;
      }
    }
    return str;
  }
  
  private String I(String paramString)
  {
    String str = paramString;
    if (paramString.startsWith("file://")) {
      str = paramString.substring(7);
    }
    return str;
  }
  
  private String J(String paramString)
  {
    Object localObject1 = new com.codename1.f.h(paramString);
    Object localObject2 = new File(new File(ba().getCacheDir(), "intent_files"), "Attachment");
    if ((!((File)localObject2).exists()) && (!((File)localObject2).mkdirs()))
    {
      Log.d(q.c().a("AppName", "CodenameOne"), "failed to create directory");
      return null;
    }
    Object localObject3 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    localObject2 = new File(((File)localObject2).getPath() + File.separator + "IMG_" + (String)localObject3 + "_" + ((com.codename1.f.h)localObject1).a());
    ((File)localObject2).getParentFile().mkdirs();
    localObject1 = FileProvider.a(ba(), ba().getPackageName() + ".provider", (File)localObject2);
    try
    {
      paramString = com.codename1.f.i.a().f(paramString);
      localObject2 = new FileOutputStream((File)localObject2);
      localObject3 = new byte['Ѐ'];
      for (;;)
      {
        int i1 = paramString.read((byte[])localObject3);
        if (i1 <= -1) {
          break;
        }
        ((OutputStream)localObject2).write((byte[])localObject3, 0, i1);
      }
      return ((Uri)localObject1).toString();
    }
    catch (IOException paramString)
    {
      Logger.getLogger(c.class.getName()).log(Level.SEVERE, null, paramString);
    }
    for (;;)
    {
      paramString.close();
      ((OutputStream)localObject2).close();
    }
  }
  
  private static void V(Object paramObject)
  {
    if ((J != null) && (((paramObject instanceof CodenameOneActivity)) || (J.c == null))) {
      J.b(paramObject);
    }
    q.a(paramObject);
    q.c().a(new Runnable()
    {
      public void run()
      {
        q.c().e(new Runnable()
        {
          public void run()
          {
            try
            {
              Thread.sleep(50L);
              return;
            }
            catch (Exception localException) {}
          }
        });
        if ((!q.b()) || (q.c().q())) {}
        v localV;
        do
        {
          return;
          localV = q.c().z();
        } while (localV == null);
        localV.J();
      }
    });
  }
  
  static Path a(com.codename1.p.c.i paramI)
  {
    return a(paramI, new Path());
  }
  
  static Path a(com.codename1.p.c.i paramI, Path paramPath)
  {
    paramPath.rewind();
    paramI = paramI.g();
    float[] arrayOfFloat = new float[6];
    if (!paramI.a())
    {
      switch (paramI.a(arrayOfFloat))
      {
      }
      for (;;)
      {
        paramI.b();
        break;
        paramPath.moveTo(arrayOfFloat[0], arrayOfFloat[1]);
        continue;
        paramPath.lineTo(arrayOfFloat[0], arrayOfFloat[1]);
        continue;
        paramPath.quadTo(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3]);
        continue;
        paramPath.cubicTo(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3], arrayOfFloat[4], arrayOfFloat[5]);
        continue;
        paramPath.close();
      }
    }
    return paramPath;
  }
  
  static Bundle a(com.codename1.i.a paramA)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("NOTIF_ID", paramA.e());
    localBundle.putString("NOTIF_TITLE", paramA.c());
    localBundle.putString("NOTIF_BODY", paramA.b());
    localBundle.putString("NOTIF_SOUND", paramA.d());
    localBundle.putString("NOTIF_IMAGE", paramA.f());
    localBundle.putInt("NOTIF_NUMBER", paramA.a());
    return localBundle;
  }
  
  static com.codename1.i.a a(Bundle paramBundle)
  {
    com.codename1.i.a localA = new com.codename1.i.a();
    localA.d(paramBundle.getString("NOTIF_ID"));
    localA.b(paramBundle.getString("NOTIF_TITLE"));
    localA.a(paramBundle.getString("NOTIF_BODY"));
    localA.c(paramBundle.getString("NOTIF_SOUND"));
    localA.e(paramBundle.getString("NOTIF_IMAGE"));
    localA.a(paramBundle.getInt("NOTIF_NUMBER"));
    return localA;
  }
  
  private String a(ContentResolver paramContentResolver, Uri paramUri)
  {
    Object localObject = null;
    paramUri = paramContentResolver.query(paramUri, null, null, null, null);
    paramUri.moveToFirst();
    int i1 = paramUri.getColumnIndex("_display_name");
    paramContentResolver = localObject;
    if (i1 >= 0)
    {
      paramContentResolver = paramUri.getString(i1);
      paramUri.close();
    }
    return paramContentResolver;
  }
  
  private String a(Uri paramUri)
  {
    new File(paramUri.getPath());
    Object localObject3 = paramUri.getScheme();
    Object localObject1 = ba().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "_data" }, null, null, null);
    ((Cursor)localObject1).moveToFirst();
    Object localObject2 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex(new String[] { "_data" }[0]));
    ((Cursor)localObject1).close();
    InputStream localInputStream;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!"content".equals(localObject3)) {}
    }
    else
    {
      localObject1 = localObject2;
      try
      {
        localInputStream = ba().getContentResolver().openInputStream(paramUri);
        localObject1 = localObject2;
        if (localInputStream != null)
        {
          localObject1 = localObject2;
          String str = new File(paramUri.toString()).getName();
          localObject1 = localObject2;
          if (str != null)
          {
            localObject1 = localObject2;
            localObject3 = aM();
            paramUri = (Uri)localObject3;
            localObject1 = localObject2;
            if (((String)localObject3).endsWith("/"))
            {
              localObject1 = localObject2;
              paramUri = ((String)localObject3).substring(0, ((String)localObject3).length() - 1);
            }
            localObject1 = localObject2;
            paramUri = paramUri + at() + str;
            localObject1 = paramUri;
            localObject2 = a(new File(I(paramUri)));
            localObject1 = paramUri;
            localObject3 = new byte['Ѐ'];
            for (;;)
            {
              localObject1 = paramUri;
              int i1 = localInputStream.read((byte[])localObject3);
              if (i1 <= -1) {
                break;
              }
              localObject1 = paramUri;
              ((OutputStream)localObject2).write((byte[])localObject3, 0, i1);
            }
          }
        }
        return localObject1;
      }
      catch (Exception paramUri)
      {
        paramUri.printStackTrace();
      }
    }
    localObject1 = paramUri;
    ((OutputStream)localObject2).close();
    localObject1 = paramUri;
    localInputStream.close();
    return paramUri;
  }
  
  private static String a(Uri paramUri, Context paramContext)
  {
    paramUri = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
    int i1 = paramUri.getColumnIndexOrThrow("_data");
    paramUri.moveToFirst();
    paramContext = paramUri.getString(i1);
    paramUri.close();
    return paramContext;
  }
  
  public static String a(String paramString1, String paramString2, Context paramContext)
  {
    if (q.b()) {
      paramString2 = q.c().a(paramString1, paramString2);
    }
    do
    {
      return paramString2;
      paramString1 = (String)d(paramContext).get(paramString1);
    } while (paramString1 == null);
    return paramString1;
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    int i2 = paramArrayOfByte.length;
    StringBuilder localStringBuilder = new StringBuilder(i2 * 3 - 1);
    int i1 = 0;
    while (i1 < i2)
    {
      if (i1 > 0) {
        localStringBuilder.append(' ');
      }
      localStringBuilder.append(S[(paramArrayOfByte[i1] >> 4 & 0xF)]);
      localStringBuilder.append(S[(paramArrayOfByte[i1] & 0xF)]);
      i1 += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static void a(NotificationManager paramNotificationManager, NotificationCompat.Builder paramBuilder, Context paramContext)
  {
    int i1 = 0;
    if (Build.VERSION.SDK_INT >= 26) {}
    try
    {
      String str = a("android.NotificationChannel.id", "cn1-channel", paramContext);
      Object localObject1 = a("android.NotificationChannel.name", "Notifications", paramContext);
      Object localObject2 = a("android.NotificationChannel.description", "Remote notifications", paramContext);
      int i2 = Integer.parseInt(a("android.NotificationChannel.importance", "4", paramContext));
      Class localClass = Class.forName("android.app.NotificationChannel");
      localObject1 = localClass.getConstructor(new Class[] { String.class, CharSequence.class, Integer.TYPE }).newInstance(new Object[] { str, localObject1, Integer.valueOf(i2) });
      localClass.getMethod("setDescription", new Class[] { String.class }).invoke(localObject1, new Object[] { localObject2 });
      localClass.getMethod("enableLights", new Class[] { Boolean.TYPE }).invoke(localObject1, new Object[] { Boolean.valueOf(Boolean.parseBoolean(a("android.NotificationChannel.enableLights", "true", paramContext))) });
      localClass.getMethod("setLightColor", new Class[] { Integer.TYPE }).invoke(localObject1, new Object[] { Integer.valueOf(Integer.parseInt(a("android.NotificationChannel.lightColor", "-65536", paramContext))) });
      localClass.getMethod("enableVibration", new Class[] { Boolean.TYPE }).invoke(localObject1, new Object[] { Boolean.valueOf(Boolean.parseBoolean(a("android.NotificationChannel.enableVibration", "false", paramContext))) });
      paramContext = a("android.NotificationChannel.vibrationPattern", null, paramContext);
      if (paramContext != null)
      {
        paramContext = paramContext.split(",");
        i2 = paramContext.length;
        localObject2 = new long[i2];
        while (i1 < i2)
        {
          localObject2[i1] = Long.parseLong(paramContext[i1].trim());
          i1 += 1;
        }
        localClass.getMethod("setVibrationPattern", new Class[] { [J.class }).invoke(localObject1, new Object[] { localObject2 });
      }
      NotificationManager.class.getMethod("createNotificationChannel", new Class[] { localClass }).invoke(paramNotificationManager, new Object[] { localObject1 });
      try
      {
        paramBuilder.getClass().getMethod("setChannelId", new Class[] { String.class }).invoke(paramBuilder, new Object[] { str });
        return;
      }
      catch (Exception paramNotificationManager)
      {
        Logger.getLogger(c.class.getName()).log(Level.SEVERE, null, paramNotificationManager);
        return;
      }
      return;
    }
    catch (ClassNotFoundException paramNotificationManager)
    {
      Logger.getLogger(c.class.getName()).log(Level.SEVERE, null, paramNotificationManager);
      return;
    }
    catch (NoSuchMethodException paramNotificationManager)
    {
      Logger.getLogger(c.class.getName()).log(Level.SEVERE, null, paramNotificationManager);
      return;
    }
    catch (SecurityException paramNotificationManager)
    {
      Logger.getLogger(c.class.getName()).log(Level.SEVERE, null, paramNotificationManager);
      return;
    }
    catch (IllegalAccessException paramNotificationManager)
    {
      Logger.getLogger(c.class.getName()).log(Level.SEVERE, null, paramNotificationManager);
      return;
    }
    catch (IllegalArgumentException paramNotificationManager)
    {
      Logger.getLogger(c.class.getName()).log(Level.SEVERE, null, paramNotificationManager);
      return;
    }
    catch (InvocationTargetException paramNotificationManager)
    {
      Logger.getLogger(c.class.getName()).log(Level.SEVERE, null, paramNotificationManager);
      return;
    }
    catch (InstantiationException paramNotificationManager)
    {
      Logger.getLogger(c.class.getName()).log(Level.SEVERE, null, paramNotificationManager);
    }
  }
  
  public static void a(Context paramContext)
  {
    while (L)
    {
      System.out.println("Waiting for deinitializing to complete before starting a new initialization");
      try
      {
        Thread.sleep(30L);
      }
      catch (Exception localException) {}
    }
    if ((K) && (J != null)) {
      J.c();
    }
    synchronized (H)
    {
      H.add(paramContext);
      if (J == null)
      {
        q.a(paramContext);
        return;
      }
      V(paramContext);
    }
  }
  
  public static void a(CodenameOneActivity paramCodenameOneActivity)
  {
    f = paramCodenameOneActivity;
  }
  
  /* Error */
  public static void a(com.codename1.n.c paramC)
    throws IOException
  {
    // Byte code:
    //   0: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   3: astore 9
    //   5: iconst_0
    //   6: istore_1
    //   7: new 487	java/io/File
    //   10: dup
    //   11: new 401	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 402	java/lang/StringBuilder:<init>	()V
    //   18: getstatic 1035	com/codename1/impl/android/c:f	Lcom/codename1/impl/android/CodenameOneActivity;
    //   21: invokevirtual 1048	com/codename1/impl/android/CodenameOneActivity:getFilesDir	()Ljava/io/File;
    //   24: invokevirtual 1051	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   27: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: ldc_w 470
    //   33: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: getstatic 241	com/codename1/impl/android/c:I	Ljava/lang/String;
    //   39: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 412	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokespecial 488	java/io/File:<init>	(Ljava/lang/String;)V
    //   48: astore 10
    //   50: aload 10
    //   52: invokevirtual 611	java/io/File:exists	()Z
    //   55: ifne +5 -> 60
    //   58: iconst_1
    //   59: istore_1
    //   60: iload_1
    //   61: istore_2
    //   62: iload_1
    //   63: ifne +46 -> 109
    //   66: aload 9
    //   68: invokevirtual 507	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   71: aload 9
    //   73: invokevirtual 1054	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   76: invokevirtual 491	android/content/Context:getPackageName	()Ljava/lang/String;
    //   79: sipush 4096
    //   82: invokevirtual 1058	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   85: getfield 1063	android/content/pm/PackageInfo:lastUpdateTime	J
    //   88: lstore 5
    //   90: aload 10
    //   92: invokevirtual 1066	java/io/File:lastModified	()J
    //   95: lstore 7
    //   97: iload_1
    //   98: istore_2
    //   99: lload 5
    //   101: lload 7
    //   103: lcmp
    //   104: ifle +5 -> 109
    //   107: iconst_1
    //   108: istore_2
    //   109: iload_2
    //   110: ifne +16 -> 126
    //   113: return
    //   114: astore 10
    //   116: aload 10
    //   118: invokevirtual 879	java/lang/Exception:printStackTrace	()V
    //   121: iload_1
    //   122: istore_2
    //   123: goto -14 -> 109
    //   126: invokestatic 440	com/codename1/impl/android/c:ba	()Landroid/content/Context;
    //   129: getstatic 241	com/codename1/impl/android/c:I	Ljava/lang/String;
    //   132: iconst_0
    //   133: invokevirtual 1070	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   136: astore 10
    //   138: aload_0
    //   139: invokeinterface 1075 1 0
    //   144: astore_0
    //   145: invokestatic 1080	javax/xml/parsers/DocumentBuilderFactory:newInstance	()Ljavax/xml/parsers/DocumentBuilderFactory;
    //   148: astore 11
    //   150: aload 11
    //   152: invokevirtual 1084	javax/xml/parsers/DocumentBuilderFactory:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   155: astore 11
    //   157: aload 11
    //   159: invokevirtual 1090	javax/xml/parsers/DocumentBuilder:newDocument	()Lorg/w3c/dom/Document;
    //   162: astore 11
    //   164: aload 11
    //   166: ldc_w 1092
    //   169: invokeinterface 1098 2 0
    //   174: astore 12
    //   176: aload 11
    //   178: aload 12
    //   180: invokeinterface 1102 2 0
    //   185: pop
    //   186: aload_0
    //   187: arraylength
    //   188: istore_3
    //   189: iconst_0
    //   190: istore_1
    //   191: iload_1
    //   192: iload_3
    //   193: if_icmpge +421 -> 614
    //   196: aload_0
    //   197: iload_1
    //   198: aaload
    //   199: astore 14
    //   201: aload 11
    //   203: ldc_w 1104
    //   206: invokeinterface 1098 2 0
    //   211: astore 13
    //   213: aload 11
    //   215: ldc_w 1106
    //   218: invokeinterface 1110 2 0
    //   223: astore 15
    //   225: aload 15
    //   227: aload 14
    //   229: invokevirtual 1113	com/codename1/n/b:b	()Ljava/lang/String;
    //   232: invokeinterface 1118 2 0
    //   237: aload 13
    //   239: aload 15
    //   241: invokeinterface 1124 2 0
    //   246: pop
    //   247: aload 14
    //   249: invokevirtual 1127	com/codename1/n/b:a	()[Lcom/codename1/n/a;
    //   252: astore 14
    //   254: aload 14
    //   256: arraylength
    //   257: istore 4
    //   259: iconst_0
    //   260: istore_2
    //   261: iload_2
    //   262: iload 4
    //   264: if_icmpge +333 -> 597
    //   267: aload 14
    //   269: iload_2
    //   270: aaload
    //   271: astore 15
    //   273: aload 11
    //   275: ldc_w 1129
    //   278: invokeinterface 1098 2 0
    //   283: astore 16
    //   285: aload 11
    //   287: ldc_w 1106
    //   290: invokeinterface 1110 2 0
    //   295: astore 17
    //   297: aload 17
    //   299: aload 15
    //   301: invokevirtual 1132	com/codename1/n/a:a	()Ljava/lang/String;
    //   304: invokeinterface 1118 2 0
    //   309: aload 16
    //   311: aload 17
    //   313: invokeinterface 1124 2 0
    //   318: pop
    //   319: aload 11
    //   321: ldc_w 1134
    //   324: invokeinterface 1110 2 0
    //   329: astore 17
    //   331: aload 15
    //   333: invokevirtual 1135	com/codename1/n/a:b	()Ljava/lang/String;
    //   336: ifnull +236 -> 572
    //   339: aload 17
    //   341: aload 15
    //   343: invokevirtual 1135	com/codename1/n/a:b	()Ljava/lang/String;
    //   346: invokeinterface 1118 2 0
    //   351: aload 16
    //   353: aload 17
    //   355: invokeinterface 1124 2 0
    //   360: pop
    //   361: aload 15
    //   363: invokevirtual 1136	com/codename1/n/a:c	()Ljava/lang/String;
    //   366: ifnull +76 -> 442
    //   369: aload 11
    //   371: ldc_w 1138
    //   374: invokeinterface 1110 2 0
    //   379: astore 17
    //   381: aload 15
    //   383: invokevirtual 1136	com/codename1/n/a:c	()Ljava/lang/String;
    //   386: astore 18
    //   388: aload 17
    //   390: new 401	java/lang/StringBuilder
    //   393: dup
    //   394: invokespecial 402	java/lang/StringBuilder:<init>	()V
    //   397: ldc_w 354
    //   400: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: aload 9
    //   405: invokevirtual 1142	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   408: aload 18
    //   410: ldc_w 1144
    //   413: aload 9
    //   415: invokevirtual 491	android/content/Context:getPackageName	()Ljava/lang/String;
    //   418: invokevirtual 1150	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   421: invokevirtual 1153	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   424: invokevirtual 412	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   427: invokeinterface 1118 2 0
    //   432: aload 16
    //   434: aload 17
    //   436: invokeinterface 1124 2 0
    //   441: pop
    //   442: aload 15
    //   444: invokevirtual 1154	com/codename1/n/a:d	()Ljava/lang/String;
    //   447: ifnull +37 -> 484
    //   450: aload 11
    //   452: ldc_w 1156
    //   455: invokeinterface 1110 2 0
    //   460: astore 17
    //   462: aload 17
    //   464: aload 15
    //   466: invokevirtual 1154	com/codename1/n/a:d	()Ljava/lang/String;
    //   469: invokeinterface 1118 2 0
    //   474: aload 16
    //   476: aload 17
    //   478: invokeinterface 1124 2 0
    //   483: pop
    //   484: aload 15
    //   486: invokevirtual 1157	com/codename1/n/a:e	()Ljava/lang/String;
    //   489: ifnull +37 -> 526
    //   492: aload 11
    //   494: ldc_w 1159
    //   497: invokeinterface 1110 2 0
    //   502: astore 17
    //   504: aload 17
    //   506: aload 15
    //   508: invokevirtual 1157	com/codename1/n/a:e	()Ljava/lang/String;
    //   511: invokeinterface 1118 2 0
    //   516: aload 16
    //   518: aload 17
    //   520: invokeinterface 1124 2 0
    //   525: pop
    //   526: aload 13
    //   528: aload 16
    //   530: invokeinterface 1160 2 0
    //   535: pop
    //   536: iload_2
    //   537: iconst_1
    //   538: iadd
    //   539: istore_2
    //   540: goto -279 -> 261
    //   543: astore_0
    //   544: ldc 2
    //   546: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   549: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   552: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   555: aconst_null
    //   556: aload_0
    //   557: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   560: new 594	java/io/IOException
    //   563: dup
    //   564: ldc_w 1162
    //   567: aload_0
    //   568: invokespecial 1165	java/io/IOException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   571: athrow
    //   572: aload 17
    //   574: aload 15
    //   576: invokevirtual 1132	com/codename1/n/a:a	()Ljava/lang/String;
    //   579: invokeinterface 1118 2 0
    //   584: goto -233 -> 351
    //   587: astore 17
    //   589: aload 17
    //   591: invokevirtual 879	java/lang/Exception:printStackTrace	()V
    //   594: goto -152 -> 442
    //   597: aload 12
    //   599: aload 13
    //   601: invokeinterface 1160 2 0
    //   606: pop
    //   607: iload_1
    //   608: iconst_1
    //   609: iadd
    //   610: istore_1
    //   611: goto -420 -> 191
    //   614: invokestatic 1170	javax/xml/transform/TransformerFactory:newInstance	()Ljavax/xml/transform/TransformerFactory;
    //   617: invokevirtual 1174	javax/xml/transform/TransformerFactory:newTransformer	()Ljavax/xml/transform/Transformer;
    //   620: new 1176	javax/xml/transform/dom/DOMSource
    //   623: dup
    //   624: aload 11
    //   626: invokespecial 1179	javax/xml/transform/dom/DOMSource:<init>	(Lorg/w3c/dom/Node;)V
    //   629: new 1181	javax/xml/transform/stream/StreamResult
    //   632: dup
    //   633: aload 10
    //   635: invokespecial 1184	javax/xml/transform/stream/StreamResult:<init>	(Ljava/io/OutputStream;)V
    //   638: invokevirtual 1190	javax/xml/transform/Transformer:transform	(Ljavax/xml/transform/Source;Ljavax/xml/transform/Result;)V
    //   641: return
    //   642: astore_0
    //   643: new 594	java/io/IOException
    //   646: dup
    //   647: ldc_w 1192
    //   650: aload_0
    //   651: invokespecial 1165	java/io/IOException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   654: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	655	0	paramC	com.codename1.n.c
    //   6	605	1	i1	int
    //   61	479	2	i2	int
    //   188	6	3	i3	int
    //   257	8	4	i4	int
    //   88	12	5	l1	long
    //   95	7	7	l2	long
    //   3	411	9	localContext	Context
    //   48	43	10	localFile	File
    //   114	3	10	localException1	Exception
    //   136	498	10	localFileOutputStream	FileOutputStream
    //   148	477	11	localObject1	Object
    //   174	424	12	localElement1	org.w3c.dom.Element
    //   211	389	13	localElement2	org.w3c.dom.Element
    //   199	69	14	arrayOfA	com.codename1.n.a[]
    //   223	352	15	localObject2	Object
    //   283	246	16	localElement3	org.w3c.dom.Element
    //   295	278	17	localAttr	org.w3c.dom.Attr
    //   587	3	17	localException2	Exception
    //   386	23	18	str	String
    // Exception table:
    //   from	to	target	type
    //   66	97	114	java/lang/Exception
    //   150	157	543	javax/xml/parsers/ParserConfigurationException
    //   388	442	587	java/lang/Exception
    //   614	641	642	java/lang/Exception
  }
  
  public static void a(final com.codename1.n.d paramD, final Context paramContext)
  {
    if (paramD != null) {
      for (;;)
      {
        try
        {
          localObject1 = paramContext.openFileInput("CN1$AndroidPendingNotifications");
          if (localObject1 == null) {
            return;
          }
          DataInputStream localDataInputStream = new DataInputStream((InputStream)localObject1);
          int i2 = localDataInputStream.readByte();
          int i1 = 0;
          if (i1 < i2)
          {
            if (localDataInputStream.readBoolean())
            {
              localObject1 = localDataInputStream.readUTF();
              if ("99".equals(localObject1))
              {
                localObject2 = D(localDataInputStream.readUTF());
                String str1 = (String)((Map)localObject2).get("type");
                localObject1 = (String)((Map)localObject2).get("body");
                str3 = (String)((Map)localObject2).get("category");
                str2 = (String)((Map)localObject2).get("image");
                localObject2 = localObject1;
                localObject1 = str1;
                localDataInputStream.readLong();
                q.c().a(new Runnable()
                {
                  public void run()
                  {
                    q.c().b("pendingPush", "true");
                    q.c().b("pushType", this.a);
                    c.a(this.b, str2, this.a, str3, paramContext);
                    if ((this.a != null) && (("3".equals(this.a)) || ("6".equals(this.a))))
                    {
                      String[] arrayOfString = this.b.split(";");
                      paramD.a(arrayOfString[0]);
                      paramD.a(arrayOfString[1]);
                    }
                    for (;;)
                    {
                      q.c().b("pendingPush", null);
                      return;
                      if ((this.a != null) && ("101".equals(this.a))) {
                        paramD.a(this.b.substring(this.b.indexOf(" ") + 1));
                      } else {
                        paramD.a(this.b);
                      }
                    }
                  }
                });
                i1 += 1;
                continue;
              }
              Object localObject2 = localDataInputStream.readUTF();
              final String str2 = null;
              final String str3 = null;
              continue;
            }
          }
          else
          {
            paramContext.deleteFile("CN1$AndroidPendingNotifications");
            return;
          }
        }
        catch (IOException paramD)
        {
          return;
        }
        Object localObject1 = null;
      }
    }
  }
  
  public static void a(File paramFile1, File paramFile2)
    throws IOException
  {
    paramFile1 = new FileInputStream(paramFile1);
    try
    {
      paramFile2 = new FileOutputStream(paramFile2);
      try
      {
        byte[] arrayOfByte = new byte['ᾠ'];
        for (;;)
        {
          int i1 = paramFile1.read(arrayOfByte);
          if (i1 <= 0) {
            break;
          }
          paramFile2.write(arrayOfByte, 0, i1);
        }
        paramFile2 = finally;
      }
      finally {}
    }
    finally
    {
      paramFile1.close();
    }
    paramFile1.close();
  }
  
  public static void a(Runnable paramRunnable)
  {
    if (aW() == null) {
      throw new RuntimeException("Cannot run on UI thread because getActivity() is null.  This generally means we are running inside a service in the background so UI access is disabled.");
    }
    final boolean[] arrayOfBoolean = new boolean[1];
    aW().runOnUiThread(new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 18	com/codename1/impl/android/c$6:a	Ljava/lang/Runnable;
        //   4: invokeinterface 29 1 0
        //   9: aload_0
        //   10: getfield 20	com/codename1/impl/android/c$6:b	[Z
        //   13: astore_1
        //   14: aload_1
        //   15: monitorenter
        //   16: aload_0
        //   17: getfield 20	com/codename1/impl/android/c$6:b	[Z
        //   20: iconst_0
        //   21: iconst_1
        //   22: bastore
        //   23: aload_0
        //   24: getfield 20	com/codename1/impl/android/c$6:b	[Z
        //   27: invokevirtual 32	java/lang/Object:notify	()V
        //   30: aload_1
        //   31: monitorexit
        //   32: return
        //   33: astore_1
        //   34: aload_1
        //   35: invokestatic 37	com/codename1/f/m:a	(Ljava/lang/Throwable;)V
        //   38: goto -29 -> 9
        //   41: astore_2
        //   42: aload_1
        //   43: monitorexit
        //   44: aload_2
        //   45: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	46	0	this	6
        //   33	10	1	localThrowable	Throwable
        //   41	4	2	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   0	9	33	java/lang/Throwable
        //   16	32	41	finally
        //   42	44	41	finally
      }
    });
    q.c().e(new Runnable()
    {
      public void run()
      {
        synchronized (this.a)
        {
          for (;;)
          {
            int i = this.a[0];
            if (i != 0) {
              break;
            }
            try
            {
              this.a.wait();
            }
            catch (InterruptedException localInterruptedException) {}
          }
          return;
        }
      }
    });
  }
  
  public static void a(String paramString1, String paramString2, String paramString3, String paramString4, Context paramContext)
  {
    String str = null;
    com.codename1.n.e.a();
    for (;;)
    {
      try
      {
        i1 = Integer.parseInt(paramString3);
        if (!(paramContext instanceof Activity)) {
          break label337;
        }
        paramString3 = (Activity)paramContext;
        Bundle localBundle = paramString3.getIntent().getExtras();
        if (localBundle == null) {
          break label337;
        }
        paramString2 = localBundle.getString("pushActionId");
        localBundle.remove("pushActionId");
        if ((paramString2 == null) || (!com.codename1.impl.android.a.a.a.b())) {
          break label326;
        }
        paramString3 = com.codename1.impl.android.a.a.a.a(paramString3.getIntent());
        if (paramString3 == null) {
          break label326;
        }
        paramString3 = paramString3.getCharSequence(paramString2 + "$Result");
        if (paramString3 == null) {
          break label326;
        }
        str = paramString3.toString();
        paramString3 = paramString2;
        paramString2 = str;
        PushNotificationService.a(paramContext);
        com.codename1.n.e.a(i1);
        com.codename1.n.e.c(paramString4);
        if (paramString3 != null) {
          com.codename1.n.e.e(paramString3);
        }
        if (paramString2 != null) {
          com.codename1.n.e.f(paramString2);
        }
        switch (i1)
        {
        default: 
          return;
        }
      }
      catch (Throwable paramString2)
      {
        int i1 = 1;
        continue;
        com.codename1.n.e.b(paramString1);
        return;
      }
      com.codename1.n.e.d(paramString1);
      return;
      paramString1 = paramString1.split(";");
      com.codename1.n.e.d(paramString1[1]);
      com.codename1.n.e.b(paramString1[0]);
      return;
      paramString1 = paramString1.split(";");
      com.codename1.n.e.a(paramString1[0]);
      com.codename1.n.e.b(paramString1[1]);
      return;
      com.codename1.n.e.b(paramString1.substring(paramString1.indexOf(" ") + 1));
      com.codename1.n.e.a(1);
      return;
      paramString1 = paramString1.split(";");
      com.codename1.n.e.a(paramString1[1]);
      com.codename1.n.e.b(paramString1[2]);
      com.codename1.n.e.a(2);
      return;
      label326:
      str = null;
      paramString3 = paramString2;
      paramString2 = str;
      continue;
      label337:
      paramString2 = null;
      paramString3 = str;
    }
  }
  
  private static void a(HttpURLConnection paramHttpURLConnection)
  {
    if (T) {
      return;
    }
    if (U)
    {
      paramHttpURLConnection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
      return;
    }
    try
    {
      Field localField = HttpURLConnection.class.getDeclaredField("methods");
      Object localObject = Field.class.getDeclaredField("modifiers");
      ((Field)localObject).setAccessible(true);
      ((Field)localObject).setInt(localField, localField.getModifiers() & 0xFFFFFFEF);
      localField.setAccessible(true);
      localObject = new LinkedHashSet(Arrays.asList((String[])localField.get(null)));
      ((Set)localObject).addAll(Arrays.asList(new String[] { "PATCH" }));
      localField.set(null, (String[])((Set)localObject).toArray(new String[0]));
      T = true;
      return;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      U = true;
      paramHttpURLConnection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      U = true;
      paramHttpURLConnection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
    }
  }
  
  public static boolean a(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 23) {}
    do
    {
      String str;
      do
      {
        return true;
        str = q.c().a(paramString1, paramString2);
      } while (android.support.v4.content.b.b(ba(), paramString1) == 0);
      if (aW() == null) {
        return false;
      }
      if ((!paramBoolean) && (android.support.v4.app.a.a(aW(), paramString1)))
      {
        if (p.a("Requires permission", str, "Ask again", "Don't Ask")) {
          return a(paramString1, paramString2, true);
        }
        return false;
      }
      aW().c(true);
      aW().d(true);
      android.support.v4.app.a.a(aW(), new String[] { paramString1 }, 1);
      q.c().e(new Runnable()
      {
        public void run()
        {
          while (c.aW().l()) {
            try
            {
              Thread.sleep(50L);
            }
            catch (InterruptedException localInterruptedException)
            {
              localInterruptedException.printStackTrace();
            }
          }
        }
      });
    } while (android.support.v4.content.b.b(aW(), paramString1) == 0);
    return false;
  }
  
  public static CodenameOneActivity aW()
  {
    return f;
  }
  
  static boolean aX()
  {
    return (y != null) || (z != null);
  }
  
  public static c aY()
  {
    return J;
  }
  
  public static void aZ()
  {
    if (J != null) {
      J.a(null);
    }
  }
  
  private static File b(File paramFile)
    throws IOException
  {
    File localFile = new File(ba().getCacheDir(), "intent_files");
    if ((!localFile.exists()) && (!localFile.mkdirs()))
    {
      Log.d(q.c().a("AppName", "CodenameOne"), "failed to create directory");
      return null;
    }
    localFile = new File(localFile, "tmp-" + System.currentTimeMillis() + paramFile.getName());
    a(paramFile, localFile);
    return localFile;
  }
  
  public static void b(Context paramContext)
  {
    synchronized (H)
    {
      H.remove(paramContext);
      if (H.isEmpty()) {
        bb();
      }
      while ((J == null) || (aW() == null)) {
        return;
      }
      J.c();
    }
  }
  
  public static Context ba()
  {
    CodenameOneActivity localCodenameOneActivity = aW();
    if (localCodenameOneActivity != null) {
      return localCodenameOneActivity;
    }
    return u;
  }
  
  public static void bb()
  {
    if (L) {
      return;
    }
    L = true;
    K = true;
    q.c().a(new Runnable()
    {
      public void run()
      {
        q.a();
        c.h(false);
      }
    });
  }
  
  public static void bg()
  {
    f(false);
  }
  
  private boolean bl()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  private void bm()
  {
    if ((aW() != null) && (this.c == null))
    {
      this.g = new RelativeLayout(aW());
      this.g.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
      this.g.setFocusable(false);
      aW().getWindow().setBackgroundDrawable(null);
      if (!j) {
        break label387;
      }
      if (Build.VERSION.SDK_INT >= 14) {
        break label354;
      }
      this.c = new e(aW(), this);
    }
    for (;;)
    {
      this.c.getAndroidView().setVisibility(0);
      this.g.addView(this.c.getAndroidView());
      this.c.getAndroidView().setVisibility(0);
      int i1 = aW().getResources().getIdentifier("main", "layout", aW().getApplicationInfo().packageName);
      RelativeLayout localRelativeLayout = (RelativeLayout)LayoutInflater.from(aW()).inflate(i1, null);
      RelativeLayout.LayoutParams localLayoutParams1;
      RelativeLayout.LayoutParams localLayoutParams2;
      if (z != null)
      {
        localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams1.addRule(10);
        localLayoutParams1.addRule(14);
        localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        localLayoutParams2.setMargins(0, 0, A, 0);
        this.g.setLayoutParams(localLayoutParams2);
        localRelativeLayout.addView(z, localLayoutParams1);
      }
      localRelativeLayout.addView(this.g);
      if (y != null)
      {
        localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams1.addRule(12);
        localLayoutParams1.addRule(14);
        localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        localLayoutParams2.setMargins(0, 0, 0, B);
        this.g.setLayoutParams(localLayoutParams2);
        localRelativeLayout.addView(y, localLayoutParams1);
      }
      aW().setContentView(localRelativeLayout);
      if (!this.c.getAndroidView().hasFocus()) {
        this.c.getAndroidView().requestFocus();
      }
      return;
      label354:
      aW().getWindow().setFlags(16777216, 16777216);
      this.c = new a(aW(), this);
      continue;
      label387:
      aW().getWindow().setFlags(16777216, 16777216);
      this.G = true;
      this.c = new a(aW(), this);
    }
  }
  
  private b bn()
  {
    int i2 = 100;
    int i1;
    if (this.M == null)
    {
      if (d() != 0) {
        break label51;
      }
      i1 = 100;
      if (e() != 0) {
        break label59;
      }
    }
    for (;;)
    {
      this.M = ((b)s(Bitmap.createBitmap(i1, i2, Bitmap.Config.ARGB_8888)));
      return this.M;
      label51:
      i1 = d();
      break;
      label59:
      i2 = e();
    }
  }
  
  /* Error */
  private String bo()
  {
    // Byte code:
    //   0: ldc_w 1569
    //   3: invokestatic 1572	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull +6 -> 14
    //   11: aload_1
    //   12: areturn
    //   13: astore_1
    //   14: invokestatic 1242	com/codename1/impl/android/c:aW	()Lcom/codename1/impl/android/CodenameOneActivity;
    //   17: ifnonnull +7 -> 24
    //   20: ldc_w 1574
    //   23: areturn
    //   24: ldc_w 1576
    //   27: iconst_2
    //   28: anewarray 686	java/lang/Class
    //   31: dup
    //   32: iconst_0
    //   33: ldc_w 442
    //   36: aastore
    //   37: dup
    //   38: iconst_1
    //   39: ldc_w 1578
    //   42: aastore
    //   43: invokevirtual 1581	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   46: astore_1
    //   47: aload_1
    //   48: iconst_1
    //   49: invokevirtual 1582	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   52: aload_1
    //   53: iconst_2
    //   54: anewarray 942	java/lang/Object
    //   57: dup
    //   58: iconst_0
    //   59: invokestatic 1242	com/codename1/impl/android/c:aW	()Lcom/codename1/impl/android/CodenameOneActivity;
    //   62: aastore
    //   63: dup
    //   64: iconst_1
    //   65: aconst_null
    //   66: aastore
    //   67: invokevirtual 952	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   70: checkcast 1576	android/webkit/WebSettings
    //   73: invokevirtual 1585	android/webkit/WebSettings:getUserAgentString	()Ljava/lang/String;
    //   76: astore_2
    //   77: aload_1
    //   78: iconst_0
    //   79: invokevirtual 1582	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   82: aload_2
    //   83: areturn
    //   84: astore_1
    //   85: new 1587	java/lang/StringBuffer
    //   88: dup
    //   89: invokespecial 1588	java/lang/StringBuffer:<init>	()V
    //   92: astore_1
    //   93: invokestatic 1592	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   96: invokevirtual 1593	java/lang/Thread:getName	()Ljava/lang/String;
    //   99: ldc_w 1492
    //   102: invokevirtual 1596	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   105: ifeq +43 -> 148
    //   108: new 1578	android/webkit/WebView
    //   111: dup
    //   112: invokestatic 1242	com/codename1/impl/android/c:aW	()Lcom/codename1/impl/android/CodenameOneActivity;
    //   115: invokespecial 1597	android/webkit/WebView:<init>	(Landroid/content/Context;)V
    //   118: astore_2
    //   119: aload_1
    //   120: aload_2
    //   121: invokevirtual 1601	android/webkit/WebView:getSettings	()Landroid/webkit/WebSettings;
    //   124: invokevirtual 1585	android/webkit/WebSettings:getUserAgentString	()Ljava/lang/String;
    //   127: invokevirtual 1604	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   130: pop
    //   131: aload_2
    //   132: invokevirtual 1607	android/webkit/WebView:destroy	()V
    //   135: aload_1
    //   136: invokevirtual 1608	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   139: areturn
    //   140: astore_2
    //   141: aload_1
    //   142: iconst_0
    //   143: invokevirtual 1582	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   146: aload_2
    //   147: athrow
    //   148: iconst_1
    //   149: newarray boolean
    //   151: astore_2
    //   152: new 44	com/codename1/impl/android/c$21
    //   155: dup
    //   156: aload_0
    //   157: aload_1
    //   158: aload_2
    //   159: invokespecial 1611	com/codename1/impl/android/c$21:<init>	(Lcom/codename1/impl/android/c;Ljava/lang/StringBuffer;[Z)V
    //   162: astore_3
    //   163: aload_3
    //   164: getstatic 226	com/codename1/impl/android/c:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   167: invokevirtual 1615	java/lang/Thread:setUncaughtExceptionHandler	(Ljava/lang/Thread$UncaughtExceptionHandler;)V
    //   170: aload_3
    //   171: invokevirtual 1618	java/lang/Thread:start	()V
    //   174: aload_2
    //   175: iconst_0
    //   176: baload
    //   177: ifne -42 -> 135
    //   180: aload_2
    //   181: monitorenter
    //   182: aload_2
    //   183: ldc2_w 1619
    //   186: invokevirtual 1623	java/lang/Object:wait	(J)V
    //   189: aload_2
    //   190: monitorexit
    //   191: goto -17 -> 174
    //   194: astore_1
    //   195: aload_2
    //   196: monitorexit
    //   197: aload_1
    //   198: athrow
    //   199: astore_3
    //   200: goto -11 -> 189
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	203	0	this	c
    //   6	6	1	str	String
    //   13	1	1	localException1	Exception
    //   46	32	1	localConstructor	Constructor
    //   84	1	1	localException2	Exception
    //   92	66	1	localStringBuffer	StringBuffer
    //   194	4	1	localObject1	Object
    //   76	56	2	localObject2	Object
    //   140	7	2	localObject3	Object
    //   151	45	2	arrayOfBoolean	boolean[]
    //   162	9	3	local21	21
    //   199	1	3	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   0	7	13	java/lang/Exception
    //   24	52	84	java/lang/Exception
    //   77	82	84	java/lang/Exception
    //   141	148	84	java/lang/Exception
    //   52	77	140	finally
    //   182	189	194	finally
    //   189	191	194	finally
    //   195	197	194	finally
    //   182	189	199	java/lang/InterruptedException
  }
  
  private static CookieManager bp()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return CookieManager.getInstance();
    }
    if (R == null)
    {
      CookieSyncManager.createInstance(ba());
      R = CookieManager.getInstance();
    }
    return CookieManager.getInstance();
  }
  
  /* Error */
  private String[] bq()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 7
    //   6: aconst_null
    //   7: astore 5
    //   9: iconst_0
    //   10: istore_1
    //   11: invokestatic 1645	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   14: invokevirtual 647	java/io/File:getPath	()Ljava/lang/String;
    //   17: astore_3
    //   18: aload_3
    //   19: iconst_0
    //   20: aload_3
    //   21: invokevirtual 868	java/lang/String:length	()I
    //   24: iconst_1
    //   25: isub
    //   26: invokevirtual 335	java/lang/String:substring	(II)Ljava/lang/String;
    //   29: astore 9
    //   31: new 1647	java/io/BufferedReader
    //   34: dup
    //   35: new 1649	java/io/FileReader
    //   38: dup
    //   39: ldc_w 1651
    //   42: invokespecial 1652	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   45: invokespecial 1655	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   48: astore 4
    //   50: new 1657	java/util/ArrayList
    //   53: dup
    //   54: invokespecial 1658	java/util/ArrayList:<init>	()V
    //   57: astore 8
    //   59: aload 4
    //   61: invokevirtual 1661	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   64: astore 10
    //   66: aload 10
    //   68: ifnull +174 -> 242
    //   71: aload 10
    //   73: ldc_w 1663
    //   76: invokevirtual 1667	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   79: ifne +25 -> 104
    //   82: aload 10
    //   84: ldc_w 1669
    //   87: invokevirtual 1667	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   90: ifne +14 -> 104
    //   93: aload 10
    //   95: ldc_w 1671
    //   98: invokevirtual 1667	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   101: ifeq -42 -> 59
    //   104: new 1673	java/util/StringTokenizer
    //   107: dup
    //   108: aload 10
    //   110: ldc_w 1307
    //   113: invokespecial 1675	java/util/StringTokenizer:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   116: astore 11
    //   118: aload 11
    //   120: invokevirtual 1678	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   123: pop
    //   124: aload 11
    //   126: invokevirtual 1678	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   129: astore 11
    //   131: aload 11
    //   133: ldc_w 1680
    //   136: invokevirtual 331	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   139: iconst_m1
    //   140: if_icmpne -81 -> 59
    //   143: aload 11
    //   145: aload 9
    //   147: invokevirtual 462	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   150: iconst_1
    //   151: if_icmpne +33 -> 184
    //   154: aload 8
    //   156: aload 11
    //   158: invokevirtual 1681	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   161: pop
    //   162: goto -103 -> 59
    //   165: astore_3
    //   166: aload 5
    //   168: astore_3
    //   169: aload 4
    //   171: ifnull +11 -> 182
    //   174: aload 4
    //   176: invokevirtual 1682	java/io/BufferedReader:close	()V
    //   179: aload 5
    //   181: astore_3
    //   182: aload_3
    //   183: areturn
    //   184: aload 10
    //   186: ldc_w 1663
    //   189: invokevirtual 1667	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   192: ifeq -133 -> 59
    //   195: aload 10
    //   197: ldc_w 1669
    //   200: invokevirtual 1667	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   203: ifeq -144 -> 59
    //   206: aload 8
    //   208: aload 11
    //   210: invokevirtual 1681	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   213: pop
    //   214: goto -155 -> 59
    //   217: astore_3
    //   218: aload 6
    //   220: astore 5
    //   222: aload 5
    //   224: astore_3
    //   225: aload 4
    //   227: ifnull -45 -> 182
    //   230: aload 4
    //   232: invokevirtual 1682	java/io/BufferedReader:close	()V
    //   235: aload 5
    //   237: areturn
    //   238: astore_3
    //   239: aload 5
    //   241: areturn
    //   242: aload 8
    //   244: invokevirtual 1685	java/util/ArrayList:size	()I
    //   247: istore_2
    //   248: iload_2
    //   249: iconst_2
    //   250: if_icmpge +33 -> 283
    //   253: iconst_1
    //   254: anewarray 266	java/lang/String
    //   257: dup
    //   258: iconst_0
    //   259: aload_3
    //   260: aastore
    //   261: astore 5
    //   263: aload 5
    //   265: astore_3
    //   266: aload 4
    //   268: ifnull -86 -> 182
    //   271: aload 4
    //   273: invokevirtual 1682	java/io/BufferedReader:close	()V
    //   276: aload 5
    //   278: areturn
    //   279: astore_3
    //   280: aload 5
    //   282: areturn
    //   283: iload_2
    //   284: anewarray 266	java/lang/String
    //   287: astore_3
    //   288: iload_1
    //   289: iload_2
    //   290: if_icmpge +104 -> 394
    //   293: aload_3
    //   294: iload_1
    //   295: aload 8
    //   297: iload_1
    //   298: invokevirtual 1688	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   301: checkcast 266	java/lang/String
    //   304: aastore
    //   305: iload_1
    //   306: iconst_1
    //   307: iadd
    //   308: istore_1
    //   309: goto -21 -> 288
    //   312: astore_3
    //   313: aconst_null
    //   314: astore 4
    //   316: aload 7
    //   318: astore 5
    //   320: aload 5
    //   322: astore_3
    //   323: aload 4
    //   325: ifnull -143 -> 182
    //   328: aload 4
    //   330: invokevirtual 1682	java/io/BufferedReader:close	()V
    //   333: aload 5
    //   335: areturn
    //   336: astore_3
    //   337: aload 5
    //   339: areturn
    //   340: astore_3
    //   341: aload 5
    //   343: areturn
    //   344: astore_3
    //   345: aload 7
    //   347: astore 5
    //   349: goto -29 -> 320
    //   352: astore 5
    //   354: aload_3
    //   355: astore 5
    //   357: goto -37 -> 320
    //   360: astore_3
    //   361: aconst_null
    //   362: astore 4
    //   364: aload 6
    //   366: astore 5
    //   368: goto -146 -> 222
    //   371: astore 5
    //   373: aload_3
    //   374: astore 5
    //   376: goto -154 -> 222
    //   379: astore_3
    //   380: aconst_null
    //   381: astore 4
    //   383: goto -217 -> 166
    //   386: astore 5
    //   388: aload_3
    //   389: astore 5
    //   391: goto -225 -> 166
    //   394: aload_3
    //   395: astore 5
    //   397: goto -134 -> 263
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	400	0	this	c
    //   10	299	1	i1	int
    //   247	44	2	i2	int
    //   17	4	3	str1	String
    //   165	1	3	localFileNotFoundException1	FileNotFoundException
    //   168	15	3	localObject1	Object
    //   217	1	3	localIOException1	IOException
    //   224	1	3	localObject2	Object
    //   238	22	3	localIOException2	IOException
    //   265	1	3	localObject3	Object
    //   279	1	3	localIOException3	IOException
    //   287	7	3	arrayOfString	String[]
    //   312	1	3	localObject4	Object
    //   322	1	3	localObject5	Object
    //   336	1	3	localIOException4	IOException
    //   340	1	3	localIOException5	IOException
    //   344	11	3	localObject6	Object
    //   360	14	3	localIOException6	IOException
    //   379	16	3	localFileNotFoundException2	FileNotFoundException
    //   48	334	4	localBufferedReader	java.io.BufferedReader
    //   7	341	5	localObject7	Object
    //   352	1	5	localObject8	Object
    //   355	12	5	localObject9	Object
    //   371	1	5	localIOException7	IOException
    //   374	1	5	localObject10	Object
    //   386	1	5	localFileNotFoundException3	FileNotFoundException
    //   389	7	5	localObject11	Object
    //   1	364	6	localObject12	Object
    //   4	342	7	localObject13	Object
    //   57	239	8	localArrayList	ArrayList
    //   29	117	9	str2	String
    //   64	132	10	str3	String
    //   116	93	11	localObject14	Object
    // Exception table:
    //   from	to	target	type
    //   50	59	165	java/io/FileNotFoundException
    //   59	66	165	java/io/FileNotFoundException
    //   71	104	165	java/io/FileNotFoundException
    //   104	162	165	java/io/FileNotFoundException
    //   184	214	165	java/io/FileNotFoundException
    //   242	248	165	java/io/FileNotFoundException
    //   283	288	165	java/io/FileNotFoundException
    //   50	59	217	java/io/IOException
    //   59	66	217	java/io/IOException
    //   71	104	217	java/io/IOException
    //   104	162	217	java/io/IOException
    //   184	214	217	java/io/IOException
    //   242	248	217	java/io/IOException
    //   283	288	217	java/io/IOException
    //   230	235	238	java/io/IOException
    //   271	276	279	java/io/IOException
    //   31	50	312	finally
    //   328	333	336	java/io/IOException
    //   174	179	340	java/io/IOException
    //   50	59	344	finally
    //   59	66	344	finally
    //   71	104	344	finally
    //   104	162	344	finally
    //   184	214	344	finally
    //   242	248	344	finally
    //   283	288	344	finally
    //   293	305	352	finally
    //   31	50	360	java/io/IOException
    //   293	305	371	java/io/IOException
    //   31	50	379	java/io/FileNotFoundException
    //   293	305	386	java/io/FileNotFoundException
  }
  
  private static boolean br()
  {
    boolean bool2 = false;
    String[] arrayOfString = V;
    int i2 = arrayOfString.length;
    int i1 = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i1 < i2)
      {
        String str = arrayOfString[i1];
        if (q.c().a(str, null) != null) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i1 += 1;
    }
  }
  
  private boolean bs()
  {
    return f(ba());
  }
  
  /* Error */
  public static Map<String, String> d(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 1695	com/codename1/impl/android/c:W	Ljava/util/Map;
    //   3: ifnonnull +138 -> 141
    //   6: new 243	java/util/HashMap
    //   9: dup
    //   10: invokespecial 244	java/util/HashMap:<init>	()V
    //   13: putstatic 1695	com/codename1/impl/android/c:W	Ljava/util/Map;
    //   16: aload_0
    //   17: ldc_w 1697
    //   20: invokevirtual 1200	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   23: astore_3
    //   24: aload_3
    //   25: ifnonnull +41 -> 66
    //   28: aload_3
    //   29: astore_0
    //   30: getstatic 1695	com/codename1/impl/android/c:W	Ljava/util/Map;
    //   33: astore 4
    //   35: aload_3
    //   36: ifnull +7 -> 43
    //   39: aload_3
    //   40: invokevirtual 709	java/io/InputStream:close	()V
    //   43: aload 4
    //   45: areturn
    //   46: astore_0
    //   47: ldc 2
    //   49: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   52: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   55: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   58: aconst_null
    //   59: aload_0
    //   60: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   63: aload 4
    //   65: areturn
    //   66: aload_3
    //   67: astore_0
    //   68: new 1202	java/io/DataInputStream
    //   71: dup
    //   72: aload_3
    //   73: invokespecial 1205	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   76: astore 4
    //   78: aload_3
    //   79: astore_0
    //   80: aload 4
    //   82: invokevirtual 1700	java/io/DataInputStream:readInt	()I
    //   85: istore_2
    //   86: iconst_0
    //   87: istore_1
    //   88: iload_1
    //   89: iload_2
    //   90: if_icmpge +43 -> 133
    //   93: aload_3
    //   94: astore_0
    //   95: aload 4
    //   97: invokevirtual 1215	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   100: astore 5
    //   102: aload_3
    //   103: astore_0
    //   104: aload 4
    //   106: invokevirtual 1215	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   109: astore 6
    //   111: aload_3
    //   112: astore_0
    //   113: getstatic 1695	com/codename1/impl/android/c:W	Ljava/util/Map;
    //   116: aload 5
    //   118: aload 6
    //   120: invokeinterface 352 3 0
    //   125: pop
    //   126: iload_1
    //   127: iconst_1
    //   128: iadd
    //   129: istore_1
    //   130: goto -42 -> 88
    //   133: aload_3
    //   134: ifnull +7 -> 141
    //   137: aload_3
    //   138: invokevirtual 709	java/io/InputStream:close	()V
    //   141: getstatic 1695	com/codename1/impl/android/c:W	Ljava/util/Map;
    //   144: areturn
    //   145: astore_0
    //   146: ldc 2
    //   148: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   151: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   154: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   157: aconst_null
    //   158: aload_0
    //   159: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   162: goto -21 -> 141
    //   165: astore 4
    //   167: aconst_null
    //   168: astore_3
    //   169: aload_3
    //   170: astore_0
    //   171: ldc 2
    //   173: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   176: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   179: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   182: aconst_null
    //   183: aload 4
    //   185: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   188: aload_3
    //   189: ifnull -48 -> 141
    //   192: aload_3
    //   193: invokevirtual 709	java/io/InputStream:close	()V
    //   196: goto -55 -> 141
    //   199: astore_0
    //   200: ldc 2
    //   202: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   205: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   208: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   211: aconst_null
    //   212: aload_0
    //   213: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   216: goto -75 -> 141
    //   219: astore_3
    //   220: aconst_null
    //   221: astore_0
    //   222: aload_0
    //   223: ifnull +7 -> 230
    //   226: aload_0
    //   227: invokevirtual 709	java/io/InputStream:close	()V
    //   230: aload_3
    //   231: athrow
    //   232: astore_0
    //   233: ldc 2
    //   235: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   238: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   241: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   244: aconst_null
    //   245: aload_0
    //   246: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   249: goto -19 -> 230
    //   252: astore_3
    //   253: goto -31 -> 222
    //   256: astore 4
    //   258: goto -89 -> 169
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	261	0	paramContext	Context
    //   87	43	1	i1	int
    //   85	6	2	i2	int
    //   23	170	3	localFileInputStream	FileInputStream
    //   219	12	3	localObject1	Object
    //   252	1	3	localObject2	Object
    //   33	72	4	localObject3	Object
    //   165	19	4	localIOException1	IOException
    //   256	1	4	localIOException2	IOException
    //   100	17	5	str1	String
    //   109	10	6	str2	String
    // Exception table:
    //   from	to	target	type
    //   39	43	46	java/lang/Throwable
    //   137	141	145	java/lang/Throwable
    //   6	24	165	java/io/IOException
    //   192	196	199	java/lang/Throwable
    //   6	24	219	finally
    //   226	230	232	java/lang/Throwable
    //   30	35	252	finally
    //   68	78	252	finally
    //   80	86	252	finally
    //   95	102	252	finally
    //   104	111	252	finally
    //   113	126	252	finally
    //   171	188	252	finally
    //   30	35	256	java/io/IOException
    //   68	78	256	java/io/IOException
    //   80	86	256	java/io/IOException
    //   95	102	256	java/io/IOException
    //   104	111	256	java/io/IOException
    //   113	126	256	java/io/IOException
  }
  
  public static boolean d(String paramString1, String paramString2)
  {
    return a(paramString1, paramString2, false);
  }
  
  /* Error */
  public static void e(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: invokestatic 1703	com/codename1/impl/android/c:br	()Z
    //   5: ifeq +140 -> 145
    //   8: aload_0
    //   9: invokestatic 889	com/codename1/impl/android/c:d	(Landroid/content/Context;)Ljava/util/Map;
    //   12: astore 4
    //   14: getstatic 284	com/codename1/impl/android/c:V	[Ljava/lang/String;
    //   17: astore_3
    //   18: aload_3
    //   19: arraylength
    //   20: istore_2
    //   21: iload_1
    //   22: iload_2
    //   23: if_icmpge +90 -> 113
    //   26: aload_3
    //   27: iload_1
    //   28: aaload
    //   29: astore 5
    //   31: invokestatic 619	com/codename1/p/q:c	()Lcom/codename1/p/q;
    //   34: aload 5
    //   36: aconst_null
    //   37: invokevirtual 625	com/codename1/p/q:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   40: astore 6
    //   42: aload 6
    //   44: ifnull +15 -> 59
    //   47: aload 4
    //   49: aload 5
    //   51: aload 6
    //   53: invokeinterface 352 3 0
    //   58: pop
    //   59: ldc_w 971
    //   62: invokestatic 619	com/codename1/p/q:c	()Lcom/codename1/p/q;
    //   65: new 401	java/lang/StringBuilder
    //   68: dup
    //   69: invokespecial 402	java/lang/StringBuilder:<init>	()V
    //   72: aload 5
    //   74: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: ldc_w 1705
    //   80: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: invokevirtual 412	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: aconst_null
    //   87: invokevirtual 625	com/codename1/p/q:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   90: invokevirtual 363	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   93: ifeq +13 -> 106
    //   96: aload 4
    //   98: aload 5
    //   100: invokeinterface 1707 2 0
    //   105: pop
    //   106: iload_1
    //   107: iconst_1
    //   108: iadd
    //   109: istore_1
    //   110: goto -89 -> 21
    //   113: aload_0
    //   114: ldc_w 1697
    //   117: iconst_0
    //   118: invokevirtual 1070	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   121: astore_0
    //   122: aload_0
    //   123: ifnonnull +41 -> 164
    //   126: aload_0
    //   127: astore_3
    //   128: getstatic 572	java/lang/System:out	Ljava/io/PrintStream;
    //   131: ldc_w 1709
    //   134: invokevirtual 586	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   137: aload_0
    //   138: ifnull +7 -> 145
    //   141: aload_0
    //   142: invokevirtual 710	java/io/OutputStream:close	()V
    //   145: return
    //   146: astore_0
    //   147: ldc 2
    //   149: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   152: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   155: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   158: aconst_null
    //   159: aload_0
    //   160: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   163: return
    //   164: aload_0
    //   165: astore_3
    //   166: new 1711	java/io/DataOutputStream
    //   169: dup
    //   170: aload_0
    //   171: invokespecial 1712	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   174: astore 5
    //   176: aload_0
    //   177: astore_3
    //   178: aload 5
    //   180: aload 4
    //   182: invokeinterface 1713 1 0
    //   187: invokevirtual 1716	java/io/DataOutputStream:writeInt	(I)V
    //   190: aload_0
    //   191: astore_3
    //   192: aload 4
    //   194: invokeinterface 1720 1 0
    //   199: invokeinterface 1721 1 0
    //   204: astore 6
    //   206: aload_0
    //   207: astore_3
    //   208: aload 6
    //   210: invokeinterface 526 1 0
    //   215: ifeq +85 -> 300
    //   218: aload_0
    //   219: astore_3
    //   220: aload 6
    //   222: invokeinterface 530 1 0
    //   227: checkcast 266	java/lang/String
    //   230: astore 7
    //   232: aload_0
    //   233: astore_3
    //   234: aload 5
    //   236: aload 7
    //   238: invokevirtual 1724	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   241: aload_0
    //   242: astore_3
    //   243: aload 5
    //   245: aload 4
    //   247: aload 7
    //   249: invokeinterface 893 2 0
    //   254: checkcast 266	java/lang/String
    //   257: invokevirtual 1724	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   260: goto -54 -> 206
    //   263: astore_3
    //   264: getstatic 572	java/lang/System:out	Ljava/io/PrintStream;
    //   267: ldc_w 1726
    //   270: invokevirtual 586	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   273: aload_0
    //   274: ifnull -129 -> 145
    //   277: aload_0
    //   278: invokevirtual 710	java/io/OutputStream:close	()V
    //   281: return
    //   282: astore_0
    //   283: ldc 2
    //   285: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   288: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   291: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   294: aconst_null
    //   295: aload_0
    //   296: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   299: return
    //   300: aload_0
    //   301: astore_3
    //   302: aconst_null
    //   303: putstatic 1695	com/codename1/impl/android/c:W	Ljava/util/Map;
    //   306: aload_0
    //   307: ifnull -162 -> 145
    //   310: aload_0
    //   311: invokevirtual 710	java/io/OutputStream:close	()V
    //   314: return
    //   315: astore_0
    //   316: ldc 2
    //   318: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   321: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   324: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   327: aconst_null
    //   328: aload_0
    //   329: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   332: return
    //   333: astore 4
    //   335: aconst_null
    //   336: astore_0
    //   337: aload_0
    //   338: astore_3
    //   339: ldc 2
    //   341: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   344: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   347: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   350: aconst_null
    //   351: aload 4
    //   353: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   356: aload_0
    //   357: ifnull -212 -> 145
    //   360: aload_0
    //   361: invokevirtual 710	java/io/OutputStream:close	()V
    //   364: return
    //   365: astore_0
    //   366: ldc 2
    //   368: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   371: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   374: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   377: aconst_null
    //   378: aload_0
    //   379: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   382: return
    //   383: astore_0
    //   384: aconst_null
    //   385: astore_3
    //   386: aload_3
    //   387: ifnull +7 -> 394
    //   390: aload_3
    //   391: invokevirtual 710	java/io/OutputStream:close	()V
    //   394: aload_0
    //   395: athrow
    //   396: astore_3
    //   397: ldc 2
    //   399: invokevirtual 689	java/lang/Class:getName	()Ljava/lang/String;
    //   402: invokestatic 695	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   405: getstatic 701	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   408: aconst_null
    //   409: aload_3
    //   410: invokevirtual 705	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   413: goto -19 -> 394
    //   416: astore_0
    //   417: goto -31 -> 386
    //   420: astore 4
    //   422: aload_0
    //   423: astore_3
    //   424: aload 4
    //   426: astore_0
    //   427: goto -41 -> 386
    //   430: astore 4
    //   432: goto -95 -> 337
    //   435: astore_0
    //   436: aconst_null
    //   437: astore_0
    //   438: goto -174 -> 264
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	441	0	paramContext	Context
    //   1	109	1	i1	int
    //   20	4	2	i2	int
    //   17	226	3	localObject1	Object
    //   263	1	3	localFileNotFoundException	FileNotFoundException
    //   301	90	3	localContext1	Context
    //   396	14	3	localThrowable	Throwable
    //   423	1	3	localContext2	Context
    //   12	234	4	localMap	Map
    //   333	19	4	localIOException1	IOException
    //   420	5	4	localObject2	Object
    //   430	1	4	localIOException2	IOException
    //   29	215	5	localObject3	Object
    //   40	181	6	localObject4	Object
    //   230	18	7	str	String
    // Exception table:
    //   from	to	target	type
    //   141	145	146	java/lang/Throwable
    //   128	137	263	java/io/FileNotFoundException
    //   166	176	263	java/io/FileNotFoundException
    //   178	190	263	java/io/FileNotFoundException
    //   192	206	263	java/io/FileNotFoundException
    //   208	218	263	java/io/FileNotFoundException
    //   220	232	263	java/io/FileNotFoundException
    //   234	241	263	java/io/FileNotFoundException
    //   243	260	263	java/io/FileNotFoundException
    //   302	306	263	java/io/FileNotFoundException
    //   277	281	282	java/lang/Throwable
    //   310	314	315	java/lang/Throwable
    //   113	122	333	java/io/IOException
    //   360	364	365	java/lang/Throwable
    //   113	122	383	finally
    //   390	394	396	java/lang/Throwable
    //   128	137	416	finally
    //   166	176	416	finally
    //   178	190	416	finally
    //   192	206	416	finally
    //   208	218	416	finally
    //   220	232	416	finally
    //   234	241	416	finally
    //   243	260	416	finally
    //   302	306	416	finally
    //   339	356	416	finally
    //   264	273	420	finally
    //   128	137	430	java/io/IOException
    //   166	176	430	java/io/IOException
    //   178	190	430	java/io/IOException
    //   192	206	430	java/io/IOException
    //   208	218	430	java/io/IOException
    //   220	232	430	java/io/IOException
    //   234	241	430	java/io/IOException
    //   243	260	430	java/io/IOException
    //   302	306	430	java/io/IOException
    //   113	122	435	java/io/FileNotFoundException
  }
  
  private void e(String paramString1, String paramString2)
  {
    paramString1 = ba().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "_data", "datetaken", "_size", "_id" }, "_id>?", new String[] { paramString1 }, "_id DESC");
    if (paramString1.getCount() > 1) {
      while (paramString1.moveToNext())
      {
        int i1 = paramString1.getInt(paramString1.getColumnIndex("_id"));
        String str = paramString1.getString(paramString1.getColumnIndex("_data"));
        paramString1.getLong(paramString1.getColumnIndex("datetaken"));
        paramString1.getLong(paramString1.getColumnIndex("_size"));
        if (str.contentEquals(paramString2)) {
          ba().getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_id=?", new String[] { Long.toString(i1) });
        }
      }
    }
    paramString1.close();
  }
  
  /* Error */
  public static void f(boolean paramBoolean)
  {
    // Byte code:
    //   0: invokestatic 1242	com/codename1/impl/android/c:aW	()Lcom/codename1/impl/android/CodenameOneActivity;
    //   3: ifnonnull +4 -> 7
    //   6: return
    //   7: iconst_1
    //   8: newarray boolean
    //   10: astore_1
    //   11: aload_1
    //   12: iconst_0
    //   13: iconst_0
    //   14: bastore
    //   15: invokestatic 1242	com/codename1/impl/android/c:aW	()Lcom/codename1/impl/android/CodenameOneActivity;
    //   18: new 42	com/codename1/impl/android/c$20
    //   21: dup
    //   22: iload_0
    //   23: aload_1
    //   24: invokespecial 1764	com/codename1/impl/android/c$20:<init>	(Z[Z)V
    //   27: invokevirtual 1253	com/codename1/impl/android/CodenameOneActivity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   30: aload_1
    //   31: iconst_0
    //   32: baload
    //   33: ifne -27 -> 6
    //   36: aload_1
    //   37: monitorenter
    //   38: aload_1
    //   39: invokevirtual 1766	java/lang/Object:wait	()V
    //   42: aload_1
    //   43: monitorexit
    //   44: return
    //   45: astore_2
    //   46: aload_1
    //   47: monitorexit
    //   48: aload_2
    //   49: athrow
    //   50: astore_2
    //   51: goto -9 -> 42
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	54	0	paramBoolean	boolean
    //   10	37	1	arrayOfBoolean	boolean[]
    //   45	4	2	localObject	Object
    //   50	1	2	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   38	42	45	finally
    //   42	44	45	finally
    //   46	48	45	finally
    //   38	42	50	java/lang/InterruptedException
  }
  
  public static boolean f(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.packageName.equals("com.google.market")) || (localPackageInfo.packageName.equals("com.android.vending"))) {
        return true;
      }
    }
    return false;
  }
  
  /* Error */
  public static void g(boolean paramBoolean)
  {
    // Byte code:
    //   0: invokestatic 619	com/codename1/p/q:c	()Lcom/codename1/p/q;
    //   3: invokevirtual 1777	com/codename1/p/q:q	()Z
    //   6: ifeq +111 -> 117
    //   9: iconst_1
    //   10: newarray boolean
    //   12: astore 4
    //   14: new 942	java/lang/Object
    //   17: dup
    //   18: invokespecial 1778	java/lang/Object:<init>	()V
    //   21: astore_3
    //   22: getstatic 713	com/codename1/impl/android/c:J	Lcom/codename1/impl/android/c;
    //   25: invokevirtual 1782	com/codename1/impl/android/c:bi	()Lcom/codename1/a/a;
    //   28: astore 5
    //   30: invokestatic 1417	java/lang/System:currentTimeMillis	()J
    //   33: ldc2_w 1783
    //   36: ladd
    //   37: lstore_1
    //   38: aload 5
    //   40: ifnull +22 -> 62
    //   43: invokestatic 619	com/codename1/p/q:c	()Lcom/codename1/p/q;
    //   46: new 30	com/codename1/impl/android/c$16
    //   49: dup
    //   50: aload 5
    //   52: lload_1
    //   53: aload_3
    //   54: aload 4
    //   56: invokespecial 1787	com/codename1/impl/android/c$16:<init>	(Lcom/codename1/a/a;JLjava/lang/Object;[Z)V
    //   59: invokevirtual 723	com/codename1/p/q:a	(Ljava/lang/Runnable;)V
    //   62: iload_0
    //   63: ifeq +54 -> 117
    //   66: aload 4
    //   68: iconst_0
    //   69: baload
    //   70: ifne +47 -> 117
    //   73: aload_3
    //   74: monitorenter
    //   75: aload_3
    //   76: ldc2_w 1788
    //   79: invokevirtual 1623	java/lang/Object:wait	(J)V
    //   82: aload_3
    //   83: monitorexit
    //   84: aload 4
    //   86: iconst_0
    //   87: baload
    //   88: ifne +12 -> 100
    //   91: getstatic 572	java/lang/System:out	Ljava/io/PrintStream;
    //   94: ldc_w 1791
    //   97: invokevirtual 586	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   100: invokestatic 1417	java/lang/System:currentTimeMillis	()J
    //   103: lload_1
    //   104: lcmp
    //   105: ifle -43 -> 62
    //   108: getstatic 572	java/lang/System:out	Ljava/io/PrintStream;
    //   111: ldc_w 1793
    //   114: invokevirtual 586	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   117: return
    //   118: astore 4
    //   120: aload_3
    //   121: monitorexit
    //   122: aload 4
    //   124: athrow
    //   125: astore 5
    //   127: goto -45 -> 82
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	130	0	paramBoolean	boolean
    //   37	67	1	l1	long
    //   21	100	3	localObject1	Object
    //   12	73	4	arrayOfBoolean	boolean[]
    //   118	5	4	localObject2	Object
    //   28	23	5	localA	com.codename1.a.a
    //   125	1	5	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   75	82	118	finally
    //   82	84	118	finally
    //   120	122	118	finally
    //   75	82	125	java/lang/Exception
  }
  
  private void j(boolean paramBoolean)
  {
    synchronized (this.h)
    {
      int i2 = this.h.size();
      int i1 = 0;
      while (i1 < i2)
      {
        ((e)this.h.get(i1)).e(paramBoolean);
        i1 += 1;
      }
      return;
    }
  }
  
  public int A()
  {
    return 42084;
  }
  
  public void A(String paramString)
  {
    Intent localIntent = new Intent(ba(), LocalNotificationPublisher.class);
    localIntent.setAction(ba().getApplicationInfo().packageName + "." + paramString);
    paramString = PendingIntent.getBroadcast(ba(), 0, localIntent, 134217728);
    ((AlarmManager)ba().getSystemService("alarm")).cancel(paramString);
  }
  
  protected InputStream B(String paramString)
    throws FileNotFoundException
  {
    return new FileInputStream(I(paramString));
  }
  
  public Object B(Object paramObject)
  {
    return new SoftReference(paramObject);
  }
  
  public boolean B()
  {
    return ba().getPackageManager().hasSystemFeature("android.hardware.touchscreen");
  }
  
  protected OutputStream C(String paramString)
    throws FileNotFoundException
  {
    return new FileOutputStream(I(paramString));
  }
  
  public Object C(Object paramObject)
  {
    paramObject = (SoftReference)paramObject;
    if (paramObject != null) {
      return paramObject.get();
    }
    return null;
  }
  
  public void C()
  {
    if (this.c == null) {}
    do
    {
      return;
      this.c.getAndroidView().setVisibility(0);
    } while (!(this.c instanceof a));
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(1000L);
          ((a)c.this.c).setPaintViewOnBuffer(false);
          return;
        }
        catch (Exception localException) {}
      }
    }).start();
  }
  
  public ag D(final Object paramObject)
  {
    if (aW() == null) {
      return null;
    }
    final d[] arrayOfD = new d[1];
    final Throwable[] arrayOfThrowable = new Throwable[1];
    final Object localObject = new Object();
    aW().runOnUiThread(new Runnable()
    {
      public void run()
      {
        synchronized (localObject)
        {
          try
          {
            WebView local1 = new WebView(c.aW())
            {
              public boolean onKeyDown(int paramAnonymous2Int, KeyEvent paramAnonymous2KeyEvent)
              {
                switch (paramAnonymous2Int)
                {
                }
                do
                {
                  return super.onKeyDown(paramAnonymous2Int, paramAnonymous2KeyEvent);
                  q.c().f(42084);
                  return true;
                } while (q.c().M() == 10);
                q.c().f(42085);
                return true;
              }
              
              public boolean onKeyUp(int paramAnonymous2Int, KeyEvent paramAnonymous2KeyEvent)
              {
                switch (paramAnonymous2Int)
                {
                }
                do
                {
                  return super.onKeyUp(paramAnonymous2Int, paramAnonymous2KeyEvent);
                  q.c().g(42084);
                  return true;
                } while (q.c().M() == 10);
                q.c().f(42085);
                return true;
              }
            };
            local1.setOnTouchListener(new View.OnTouchListener()
            {
              public boolean onTouch(View paramAnonymous2View, MotionEvent paramAnonymous2MotionEvent)
              {
                switch (paramAnonymous2MotionEvent.getAction())
                {
                }
                for (;;)
                {
                  return false;
                  if (!paramAnonymous2View.hasFocus()) {
                    paramAnonymous2View.requestFocus();
                  }
                }
              }
            });
            local1.getSettings().setDomStorageEnabled(true);
            local1.requestFocus(130);
            local1.setFocusableInTouchMode(true);
            arrayOfD[0] = new c.d(c.this, local1, c.aW(), paramObject);
            localObject.notify();
            return;
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              arrayOfThrowable[0] = localThrowable;
              localObject.notify();
            }
          }
        }
      }
    });
    while ((arrayOfD[0] == null) && (arrayOfThrowable[0] == null)) {
      q.c().e(new Runnable()
      {
        public void run()
        {
          synchronized (localObject)
          {
            if (arrayOfD[0] == null)
            {
              Throwable localThrowable = arrayOfThrowable[0];
              if (localThrowable != null) {}
            }
            try
            {
              localObject.wait(20L);
              return;
            }
            catch (InterruptedException localInterruptedException)
            {
              for (;;)
              {
                localInterruptedException.printStackTrace();
              }
            }
          }
        }
      });
    }
    if (arrayOfThrowable[0] != null) {
      throw new RuntimeException(arrayOfThrowable[0]);
    }
    return arrayOfD[0];
  }
  
  public void E(Object paramObject)
  {
    try
    {
      super.E(paramObject);
      if ((paramObject != null) && ((paramObject instanceof RandomAccessFile))) {
        ((RandomAccessFile)paramObject).close();
      }
      return;
    }
    catch (Throwable paramObject)
    {
      paramObject.printStackTrace();
    }
  }
  
  public int F(Object paramObject)
  {
    return ((HttpURLConnection)paramObject).getContentLength();
  }
  
  public boolean F()
  {
    return true;
  }
  
  public OutputStream G(Object paramObject)
    throws IOException
  {
    if ((paramObject instanceof String))
    {
      String str = (String)paramObject;
      paramObject = str;
      if (str.startsWith("file://")) {
        paramObject = str.substring(7);
      }
      return new com.codename1.f.c(C(paramObject), paramObject);
    }
    return new com.codename1.f.c(((URLConnection)paramObject).getOutputStream(), paramObject.toString());
  }
  
  public boolean G()
  {
    return true;
  }
  
  public InputStream H(Object paramObject)
    throws IOException
  {
    if ((paramObject instanceof String))
    {
      String str = (String)paramObject;
      paramObject = str;
      if (str.startsWith("file://")) {
        paramObject = str.substring(7);
      }
      return new com.codename1.f.b(B(paramObject), paramObject);
    }
    if ((paramObject instanceof HttpURLConnection))
    {
      paramObject = (HttpURLConnection)paramObject;
      if (paramObject.getResponseCode() < 400) {
        return new com.codename1.f.b(paramObject.getInputStream());
      }
      return new com.codename1.f.b(paramObject.getErrorStream());
    }
    return new com.codename1.f.b(((URLConnection)paramObject).getInputStream());
  }
  
  public boolean H()
  {
    return false;
  }
  
  public int I(Object paramObject)
    throws IOException
  {
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramObject;
    if ("head".equalsIgnoreCase(localHttpURLConnection.getRequestMethod())) {
      localHttpURLConnection.setRequestProperty("Accept-Encoding", "");
    }
    return ((HttpURLConnection)paramObject).getResponseCode();
  }
  
  public Object I()
  {
    if (this.c != null)
    {
      this.M = null;
      return this.c.getGraphics();
    }
    return bn();
  }
  
  public String J(Object paramObject)
    throws IOException
  {
    return ((HttpURLConnection)paramObject).getResponseMessage();
  }
  
  protected int L()
  {
    return 1000000;
  }
  
  public Object L(Object paramObject)
  {
    f localF = f.a();
    localF.a(((f)paramObject).e());
    if (localF.c()) {
      return localF;
    }
    return null;
  }
  
  public void M(Object paramObject)
    throws au.b
  {
    if (!((f)paramObject).c()) {
      throw new au.b();
    }
  }
  
  public void N(Object paramObject)
  {
    ((f)paramObject).b();
  }
  
  public int O()
  {
    if (q.c().F().b()) {
      return 3;
    }
    return 2;
  }
  
  public boolean P()
  {
    return true;
  }
  
  public boolean Q()
  {
    return true;
  }
  
  public boolean S()
  {
    return true;
  }
  
  public boolean U()
  {
    return true;
  }
  
  public boolean V()
  {
    return true;
  }
  
  public boolean W()
  {
    return true;
  }
  
  public boolean X()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.setFlags(268435456);
    localIntent.putExtra("WaitForResult", Boolean.FALSE);
    ba().startActivity(localIntent);
    return true;
  }
  
  public boolean Y()
  {
    return (aW() == null) || (aW().g());
  }
  
  public int a(int paramInt, boolean paramBoolean)
  {
    return (int)(ba().getResources().getDisplayMetrics().density * 160.0F * (paramInt / 25.4F));
  }
  
  public int a(Object paramObject, char paramChar)
  {
    this.o[0] = paramChar;
    if (paramObject == null) {}
    float f1;
    for (paramObject = this.d;; paramObject = (Paint)((h)paramObject).d)
    {
      f1 = paramObject.measureText(this.o, 0, 1);
      if (f1 - (int)f1 <= 0.0F) {
        break;
      }
      return (int)(f1 + 1.0F);
    }
    return (int)f1;
  }
  
  public int a(Object paramObject, String paramString)
  {
    if (paramObject == null) {}
    float f1;
    for (paramObject = this.d;; paramObject = (Paint)((h)paramObject).d)
    {
      f1 = paramObject.measureText(paramString);
      if (f1 - (int)f1 <= 0.0F) {
        break;
      }
      return (int)(f1 + 1.0F);
    }
    return (int)f1;
  }
  
  public int a(Object paramObject, char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (paramObject == null) {}
    float f1;
    for (paramObject = this.d;; paramObject = (Paint)((h)paramObject).d)
    {
      f1 = paramObject.measureText(paramArrayOfChar, paramInt1, paramInt2);
      if (f1 - (int)f1 <= 0.0F) {
        break;
      }
      return (int)(f1 + 1.0F);
    }
    return (int)f1;
  }
  
  public x a(x paramX, float paramFloat)
  {
    try
    {
      Object localObject1 = Bitmap.createBitmap((Bitmap)paramX.c());
      Object localObject2 = RenderScript.create(ba());
      ScriptIntrinsicBlur localScriptIntrinsicBlur = ScriptIntrinsicBlur.create((RenderScript)localObject2, Element.U8_4((RenderScript)localObject2));
      Allocation localAllocation = Allocation.createFromBitmap((RenderScript)localObject2, (Bitmap)paramX.c());
      localObject2 = Allocation.createFromBitmap((RenderScript)localObject2, (Bitmap)localObject1);
      localScriptIntrinsicBlur.setRadius(paramFloat);
      localScriptIntrinsicBlur.setInput(localAllocation);
      localScriptIntrinsicBlur.forEach((Allocation)localObject2);
      ((Allocation)localObject2).copyTo((Bitmap)localObject1);
      localObject1 = new i((Bitmap)localObject1);
      return localObject1;
    }
    catch (Throwable localThrowable)
    {
      this.n = true;
    }
    return paramX;
  }
  
  public InputStream a(Class paramClass, String paramString)
  {
    paramClass = paramString;
    Object localObject = paramString;
    try
    {
      if (paramString.startsWith("/"))
      {
        localObject = paramString;
        paramClass = paramString.substring(1);
      }
      localObject = paramClass;
      paramClass = ba().getAssets().open(paramClass);
      return paramClass;
    }
    catch (IOException paramClass)
    {
      Log.i("Codename One", "Resource not found: " + (String)localObject);
    }
    return null;
  }
  
  protected OutputStream a(File paramFile)
    throws FileNotFoundException
  {
    return new FileOutputStream(paramFile);
  }
  
  public Object a(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return f.a(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public Object a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return f.a(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public Object a(int paramInt1, int paramInt2, int paramInt3)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    ((b)s(localBitmap)).c(paramInt3);
    return localBitmap;
  }
  
  public Object a(int paramInt, final l paramL, final Object paramObject1, final Object paramObject2)
  {
    if (aW() == null) {
      return null;
    }
    final boolean[] arrayOfBoolean = new boolean[1];
    Object localObject = new boolean[1];
    if (be()) {
      f(true);
    }
    if (paramInt == 2)
    {
      paramL = new c(paramObject1, (boolean[])localObject, arrayOfBoolean);
      aW().runOnUiThread(new Runnable()
      {
        public void run()
        {
          int i = ((Integer)paramObject1).intValue() / 60;
          int j = ((Integer)paramObject1).intValue();
          TimePickerDialog local1 = new TimePickerDialog(c.aW(), paramL, i, j % 60, true)
          {
            public void cancel()
            {
              super.cancel();
              c.13.this.c[0] = true;
              c.13.this.d[0] = true;
            }
            
            public void dismiss()
            {
              super.dismiss();
              c.13.this.c[0] = true;
            }
          };
          local1.setOnCancelListener(paramL);
          local1.show();
        }
      });
      q.c().e(paramL);
      if (arrayOfBoolean[0] != 0) {
        return null;
      }
      return new Integer(paramL.a);
    }
    if (paramInt == 1)
    {
      paramL = Calendar.getInstance();
      if (paramObject1 != null) {
        paramL.setTime((Date)paramObject1);
      }
      paramObject1 = new a(paramObject1, (boolean[])localObject, arrayOfBoolean);
      aW().runOnUiThread(new Runnable()
      {
        public void run()
        {
          DatePickerDialog local1 = new DatePickerDialog(c.aW(), paramObject1, paramL.get(1), paramL.get(2), paramL.get(5))
          {
            public void cancel()
            {
              super.cancel();
              c.14.this.c[0] = true;
              c.14.this.d[0] = true;
            }
            
            public void dismiss()
            {
              super.dismiss();
              c.14.this.c[0] = true;
            }
          };
          local1.setOnCancelListener(paramObject1);
          local1.show();
        }
      });
      q.c().e(paramObject1);
      return paramObject1.a;
    }
    if (paramInt == 4)
    {
      paramObject2 = (String[])paramObject2;
      localObject = new b((boolean[])localObject, arrayOfBoolean);
      paramInt = 0;
      for (;;)
      {
        if (paramInt < paramObject2.length)
        {
          if (paramObject2[paramInt].equals(paramObject1)) {
            ((b)localObject).a = paramInt;
          }
        }
        else
        {
          if ((((b)localObject).a == -1) && (paramObject2.length > 0)) {
            ((b)localObject).a = 0;
          }
          aW().runOnUiThread(new Runnable()
          {
            public void run()
            {
              Object localObject = new NumberPicker(c.aW());
              if (paramL.u("showKeyboard") == null) {
                ((NumberPicker)localObject).setDescendantFocusability(393216);
              }
              ((NumberPicker)localObject).setMinValue(0);
              ((NumberPicker)localObject).setMaxValue(paramObject2.length - 1);
              ((NumberPicker)localObject).setDisplayedValues(paramObject2);
              ((NumberPicker)localObject).setOnValueChangedListener(this.c);
              if (this.c.a > -1) {
                ((NumberPicker)localObject).setValue(this.c.a);
              }
              RelativeLayout localRelativeLayout = new RelativeLayout(c.aW());
              RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(50, 50);
              RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
              localLayoutParams2.addRule(14);
              localRelativeLayout.setLayoutParams(localLayoutParams1);
              localRelativeLayout.addView((View)localObject, localLayoutParams2);
              localObject = new AlertDialog.Builder(c.aW());
              ((AlertDialog.Builder)localObject).setView(localRelativeLayout);
              ((AlertDialog.Builder)localObject).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  c.15.this.c.b();
                }
              }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  paramAnonymous2DialogInterface.cancel();
                  c.15.this.c.a();
                }
              });
              ((AlertDialog.Builder)localObject).create().show();
            }
          });
          q.c().e((Runnable)localObject);
          if (arrayOfBoolean[0] == 0) {
            break;
          }
          return null;
        }
        paramInt += 1;
      }
      if (((b)localObject).a < 0) {
        return null;
      }
      return paramObject2[localObject.a];
    }
    return null;
  }
  
  public Object a(InputStream paramInputStream)
    throws IOException
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
    return BitmapFactory.decodeStream(paramInputStream, null, localOptions);
  }
  
  public Object a(Object paramObject, float paramFloat, int paramInt)
  {
    paramObject = (h)paramObject;
    Object localObject = (h)paramObject.d;
    ((h)localObject).setAntiAlias(true);
    localObject = ((h)localObject).getTypeface();
    if (((paramInt & 0x1) != 0) || (((Typeface)localObject).isBold())) {}
    for (int i1 = 1;; i1 = 0)
    {
      int i2;
      if ((paramInt & 0x2) == 0)
      {
        i2 = i1;
        if (!((Typeface)localObject).isItalic()) {}
      }
      else
      {
        i2 = i1 | 0x2;
      }
      localObject = new h(Typeface.create((Typeface)localObject, i2));
      ((h)localObject).setTextSize(paramFloat);
      ((h)localObject).setAntiAlias(true);
      return new h(0, paramInt, 0, localObject, paramObject.e, paramFloat, paramInt);
    }
  }
  
  public Object a(Object paramObject, int paramInt1, int paramInt2)
  {
    return Bitmap.createScaledBitmap((Bitmap)paramObject, paramInt1, paramInt2, false);
  }
  
  public Object a(String paramString1, String paramString2)
  {
    if (paramString1.startsWith("native:"))
    {
      paramString1 = E(paramString1);
      if (!paramString1.isBold()) {
        break label150;
      }
    }
    label150:
    for (int i1 = 1;; i1 = 0)
    {
      if (paramString1.isItalic()) {
        i1 |= 0x2;
      }
      for (;;)
      {
        paramString1 = new h(paramString1);
        paramString1.setAntiAlias(true);
        paramString1.setSubpixelText(true);
        return new h(0, i1, 0, paramString1, paramString2, 0.0F, 0);
        paramString1 = Typeface.createFromAsset(ba().getAssets(), paramString2);
        if (paramString1 == null) {
          throw new RuntimeException("Font not found: " + paramString2);
        }
        paramString1 = new h(paramString1);
        paramString1.setAntiAlias(true);
        paramString1.setSubpixelText(true);
        return new h(0, 0, 0, paramString1, paramString2, 0.0F, 0);
      }
    }
  }
  
  public Object a(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    return a(paramString, paramBoolean1, paramBoolean2, this.w);
  }
  
  public Object a(String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
    throws IOException
  {
    paramString = new URL(paramString);
    CookieHandler.setDefault(null);
    paramString = paramString.openConnection();
    HttpURLConnection localHttpURLConnection;
    if ((paramString instanceof HttpURLConnection))
    {
      localHttpURLConnection = (HttpURLConnection)paramString;
      localHttpURLConnection.setUseCaches(false);
      localHttpURLConnection.setDefaultUseCaches(false);
      localHttpURLConnection.setInstanceFollowRedirects(false);
      if (paramInt > -1) {
        localHttpURLConnection.setConnectTimeout(paramInt);
      }
      if (paramBoolean1)
      {
        if (paramInt <= -1) {
          break label110;
        }
        localHttpURLConnection.setReadTimeout(paramInt);
      }
    }
    for (;;)
    {
      if (Build.VERSION.SDK_INT > 13) {
        localHttpURLConnection.setRequestProperty("Connection", "close");
      }
      paramString.setDoInput(paramBoolean1);
      paramString.setDoOutput(paramBoolean2);
      return paramString;
      label110:
      localHttpURLConnection.setReadTimeout(10000);
    }
  }
  
  public Object a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
    try
    {
      BitmapFactory.Options.class.getField("inPurgeable").set(localOptions, Boolean.valueOf(true));
      return BitmapFactory.decodeByteArray(paramArrayOfByte, paramInt1, paramInt2, localOptions);
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public Object a(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    return Bitmap.createBitmap(paramArrayOfInt, paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
  }
  
  public String a(String paramString, Object paramObject)
    throws IOException
  {
    return ((HttpURLConnection)paramObject).getHeaderField(paramString);
  }
  
  protected void a(int paramInt1, int paramInt2)
  {
    super.a(paramInt1, paramInt2);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.p.set(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4);
    if (this.c != null) {
      this.c.a(this.p);
    }
  }
  
  public void a(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 6) {
      ((k)this.Z).a(paramInt1, paramInt2, paramIntent);
    }
    do
    {
      Object localObject1;
      do
      {
        return;
        if (paramInt2 == -1)
        {
          if (paramInt1 != 1) {
            break;
          }
          try
          {
            localObject1 = com.codename1.q.i.a((String)t.a().f("imageUri"), ";");
            paramIntent = (String)((Vector)localObject1).get(0);
            localObject1 = (String)((Vector)localObject1).get(1);
            t.a().a("imageUri");
            e((String)localObject1, paramIntent);
            this.v.a(new com.codename1.p.b.a(paramIntent));
            return;
          }
          catch (Exception paramIntent)
          {
            paramIntent.printStackTrace();
          }
        }
        if ((String)t.a().f("imageUri") != null) {
          t.a().a("imageUri");
        }
      } while (this.v == null);
      this.v.a(null);
      return;
      if (paramInt1 == 2)
      {
        paramIntent = (String)t.a().f("videoUri");
        t.a().a("videoUri");
        this.v.a(new com.codename1.p.b.a(paramIntent));
        return;
      }
      if (paramInt1 == 3)
      {
        paramIntent = a(paramIntent.getData(), ba());
        this.v.a(new com.codename1.p.b.a(paramIntent));
        return;
      }
      Object localObject3;
      Object localObject4;
      byte[] arrayOfByte;
      if (paramInt1 == 7)
      {
        if (Build.VERSION.SDK_INT >= 16)
        {
          if (paramIntent.getClipData() != null)
          {
            localObject1 = new ArrayList();
            paramInt2 = paramIntent.getClipData().getItemCount();
            paramInt1 = 0;
            while (paramInt1 < paramInt2)
            {
              localObject3 = a(paramIntent.getClipData().getItemAt(paramInt1).getUri());
              if (localObject3 != null) {
                ((ArrayList)localObject1).add(localObject3);
              }
              paramInt1 += 1;
            }
            this.v.a(new com.codename1.p.b.a(((ArrayList)localObject1).toArray(new String[((ArrayList)localObject1).size()])));
          }
        }
        else
        {
          m.a(new RuntimeException("OPEN_GALLERY_MULTI requires android sdk 16 (jelly bean) or higher"));
          this.v.a(null);
        }
        localObject3 = paramIntent.getData();
        localObject4 = paramIntent.getScheme();
        localObject1 = new String[1];
        localObject1[0] = "_data";
        paramIntent = ba().getContentResolver().query((Uri)localObject3, (String[])localObject1, null, null, null);
        if (paramIntent == null)
        {
          this.v.a(null);
          return;
        }
        paramIntent.moveToFirst();
        localObject1 = paramIntent.getString(paramIntent.getColumnIndex(localObject1[0]));
        paramIntent.close();
        paramIntent = (Intent)localObject1;
        if (localObject1 == null)
        {
          paramIntent = (Intent)localObject1;
          if ("content".equals(localObject4))
          {
            paramIntent = (Intent)localObject1;
            try
            {
              localObject4 = ba().getContentResolver().openInputStream((Uri)localObject3);
              paramIntent = (Intent)localObject1;
              if (localObject4 != null)
              {
                paramIntent = (Intent)localObject1;
                localObject3 = a(ba().getContentResolver(), (Uri)localObject3);
                paramIntent = (Intent)localObject1;
                if (localObject3 != null)
                {
                  paramIntent = (Intent)localObject1;
                  localObject1 = aM() + at() + (String)localObject3;
                  paramIntent = (Intent)localObject1;
                  localObject3 = a(new File(I((String)localObject1)));
                  paramIntent = (Intent)localObject1;
                  arrayOfByte = new byte['Ѐ'];
                  for (;;)
                  {
                    paramIntent = (Intent)localObject1;
                    paramInt1 = ((InputStream)localObject4).read(arrayOfByte);
                    if (paramInt1 <= -1) {
                      break;
                    }
                    paramIntent = (Intent)localObject1;
                    ((OutputStream)localObject3).write(arrayOfByte, 0, paramInt1);
                  }
                }
              }
            }
            catch (Exception localException1)
            {
              localException1.printStackTrace();
            }
          }
        }
        for (;;)
        {
          tmp629_626[0] = paramIntent;
          this.v.a(new com.codename1.p.b.a(tmp629_626));
          return;
          paramIntent = localException1;
          ((OutputStream)localObject3).close();
          paramIntent = localException1;
          ((InputStream)localObject4).close();
          paramIntent = localException1;
        }
      }
      if (paramInt1 == 5)
      {
        localObject3 = paramIntent.getData();
        localObject4 = paramIntent.getScheme();
        Object localObject2 = new String[1];
        localObject2[0] = "_data";
        paramIntent = ba().getContentResolver().query((Uri)localObject3, (String[])localObject2, null, null, null);
        if (paramIntent == null)
        {
          this.v.a(null);
          return;
        }
        paramIntent.moveToFirst();
        localObject2 = paramIntent.getString(paramIntent.getColumnIndex(localObject2[0]));
        paramIntent.close();
        paramIntent = (Intent)localObject2;
        if (localObject2 == null)
        {
          paramIntent = (Intent)localObject2;
          if ("content".equals(localObject4))
          {
            paramIntent = (Intent)localObject2;
            try
            {
              localObject4 = ba().getContentResolver().openInputStream((Uri)localObject3);
              paramIntent = (Intent)localObject2;
              if (localObject4 != null)
              {
                paramIntent = (Intent)localObject2;
                localObject3 = a(ba().getContentResolver(), (Uri)localObject3);
                paramIntent = (Intent)localObject2;
                if (localObject3 != null)
                {
                  paramIntent = (Intent)localObject2;
                  localObject2 = aM() + at() + (String)localObject3;
                  paramIntent = (Intent)localObject2;
                  localObject3 = a(new File(I((String)localObject2)));
                  paramIntent = (Intent)localObject2;
                  arrayOfByte = new byte['Ѐ'];
                  for (;;)
                  {
                    paramIntent = (Intent)localObject2;
                    paramInt1 = ((InputStream)localObject4).read(arrayOfByte);
                    if (paramInt1 <= -1) {
                      break;
                    }
                    paramIntent = (Intent)localObject2;
                    ((OutputStream)localObject3).write(arrayOfByte, 0, paramInt1);
                  }
                }
              }
              this.v.a(new com.codename1.p.b.a(paramIntent));
            }
            catch (Exception localException2)
            {
              localException2.printStackTrace();
            }
          }
        }
        for (;;)
        {
          return;
          paramIntent = localException2;
          ((OutputStream)localObject3).close();
          paramIntent = localException2;
          ((InputStream)localObject4).close();
          paramIntent = localException2;
        }
      }
    } while (this.v == null);
    this.v.a(new com.codename1.p.b.a("ok"));
  }
  
  public void a(com.codename1.i.a paramA, long paramLong, int paramInt)
  {
    Object localObject1 = new Intent(ba(), LocalNotificationPublisher.class);
    ((Intent)localObject1).setAction(ba().getApplicationInfo().packageName + "." + paramA.e());
    ((Intent)localObject1).putExtra(LocalNotificationPublisher.a, a(paramA));
    Object localObject2 = new Intent();
    if (aW() != null) {
      ((Intent)localObject2).setComponent(aW().getComponentName());
    }
    ((Intent)localObject2).putExtra("LocalNotificationID", paramA.e());
    if (("$$$CN1_BACKGROUND_FETCH$$$".equals(paramA.e())) && (bi() != null))
    {
      Object localObject3 = AndroidNativeUtil.getContext();
      Intent localIntent = new Intent((Context)localObject3, BackgroundFetchHandler.class);
      localIntent.setData(Uri.parse("http://codenameone.com/a?" + bi().getClass().getName()));
      localObject3 = PendingIntent.getService((Context)localObject3, 0, localIntent, 134217728);
      ((Intent)localObject1).putExtra(LocalNotificationPublisher.c, (Parcelable)localObject3);
      localObject2 = PendingIntent.getActivity(ba(), 0, (Intent)localObject2, 134217728);
      ((Intent)localObject1).putExtra(LocalNotificationPublisher.b, (Parcelable)localObject2);
      localObject1 = PendingIntent.getBroadcast(ba(), 0, (Intent)localObject1, 134217728);
      localObject2 = (AlarmManager)ba().getSystemService("alarm");
      if (!"$$$CN1_BACKGROUND_FETCH$$$".equals(paramA.e())) {
        break label333;
      }
      ((AlarmManager)localObject2).setRepeating(0, paramLong, aT() * 1000, (PendingIntent)localObject1);
    }
    label333:
    do
    {
      return;
      ((Intent)localObject2).setData(Uri.parse("http://codenameone.com/a?LocalNotificationID=" + Uri.encode(paramA.e())));
      break;
      if (paramInt == 0)
      {
        ((AlarmManager)localObject2).set(0, paramLong, (PendingIntent)localObject1);
        return;
      }
      if (paramInt == 1)
      {
        ((AlarmManager)localObject2).setRepeating(0, paramLong, 60000L, (PendingIntent)localObject1);
        return;
      }
      if (paramInt == 3)
      {
        ((AlarmManager)localObject2).setInexactRepeating(0, paramLong, 1800000L, (PendingIntent)localObject1);
        return;
      }
      if (paramInt == 4)
      {
        ((AlarmManager)localObject2).setInexactRepeating(0, paramLong, 86400000L, (PendingIntent)localObject1);
        return;
      }
    } while (paramInt != 5);
    ((AlarmManager)localObject2).setRepeating(0, paramLong, 604800000L, (PendingIntent)localObject1);
  }
  
  public void a(ag paramAg)
  {
    ((d)paramAg).j();
  }
  
  public void a(ag paramAg, String paramString)
  {
    a(paramAg, paramString, null);
  }
  
  public void a(ag paramAg, String paramString1, String paramString2)
  {
    ((d)paramAg).a(paramString1, paramString2);
  }
  
  public void a(ag paramAg, String paramString, Map<String, String> paramMap)
  {
    String str = paramString;
    if (paramString.startsWith("jar:"))
    {
      str = paramString.substring(6);
      paramString = str;
      if (str.indexOf("/") != 0) {
        paramString = "/" + str;
      }
      str = "file:///android_asset" + paramString;
    }
    paramAg = (d)paramAg;
    if (d.a(paramAg).a(str)) {
      paramAg.a(str, paramMap);
    }
  }
  
  public void a(OutputStream paramOutputStream)
  {
    if ((paramOutputStream != null) && ((paramOutputStream instanceof FileOutputStream))) {}
    try
    {
      paramOutputStream = ((FileOutputStream)paramOutputStream).getFD();
      if (paramOutputStream != null) {
        paramOutputStream.sync();
      }
      return;
    }
    catch (IOException paramOutputStream)
    {
      paramOutputStream.printStackTrace();
    }
  }
  
  public void a(Object paramObject, float paramFloat1, float paramFloat2)
  {
    ((b)paramObject).a(paramFloat1, paramFloat2);
  }
  
  public void a(Object paramObject, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    paramObject = (f)paramObject;
    paramObject.d();
    paramObject.b(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public void a(Object paramObject, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    ((f)paramObject).c(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public void a(Object paramObject, float paramFloat, int paramInt1, int paramInt2)
  {
    ((b)paramObject).a(paramFloat, paramInt1, paramInt2);
  }
  
  public void a(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ((b)paramObject).b(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void a(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte paramByte)
  {
    ((b)paramObject).a(paramInt1, paramInt2, paramInt3, paramInt4, paramByte);
  }
  
  public void a(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    ((b)paramObject).e(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }
  
  public void a(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (!j)
    {
      super.a(paramObject, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramFloat1, paramFloat2, paramFloat3);
      return;
    }
    ((b)paramObject).a(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramFloat1, paramFloat2, paramFloat3);
  }
  
  public void a(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean)
  {
    if (!j)
    {
      super.a(paramObject, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramBoolean);
      return;
    }
    ((b)paramObject).a(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramBoolean);
  }
  
  public void a(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, com.codename1.p.g.f paramF)
  {
    if ((!j) || (this.D))
    {
      super.a(paramObject, paramInt1, paramInt2, paramInt3, paramInt4, paramF);
      return;
    }
    ((b)paramObject).a(paramInt1, paramInt2, paramInt3, paramInt4, paramF);
  }
  
  public void a(Object paramObject1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, com.codename1.p.g.f paramF, String paramString, Object paramObject2, Object paramObject3, int paramInt5, int paramInt6, boolean paramBoolean1, boolean paramBoolean2, int paramInt7, int paramInt8, boolean paramBoolean3, int paramInt9, boolean paramBoolean4, int paramInt10)
  {
    if (a.a)
    {
      super.a(paramObject1, paramInt1, paramInt2, paramInt3, paramInt4, paramF, paramString, paramObject2, paramObject3, paramInt5, paramInt6, paramBoolean1, paramBoolean2, paramInt7, paramInt8, paramBoolean3, paramInt9, paramBoolean4, paramInt10);
      return;
    }
    ((b)paramObject1).a(paramInt1, paramInt2, paramInt3, paramInt4, paramF, paramString, (Bitmap)paramObject2, (Bitmap)paramObject3, paramInt5, paramInt6, paramBoolean1, paramBoolean2, paramInt7, paramInt8, paramBoolean3, paramInt9, paramBoolean4, paramInt10);
  }
  
  public void a(Object paramObject, au paramAu)
  {
    b localB = (b)paramObject;
    paramObject = localB.h();
    if (paramObject == null)
    {
      if (paramAu == null) {}
      for (paramObject = au.b();; paramObject = paramAu.h())
      {
        localB.a(paramObject);
        return;
      }
    }
    if (paramAu == null) {
      paramObject.d();
    }
    for (;;)
    {
      localB.a(paramObject);
      return;
      paramObject.b(paramAu);
    }
  }
  
  public void a(Object paramObject, com.codename1.p.c.i paramI)
  {
    ((b)paramObject).a(paramI);
  }
  
  public void a(Object paramObject, com.codename1.p.c.i paramI, an paramAn)
  {
    ((b)paramObject).a(a(paramI), paramAn);
  }
  
  public void a(Object paramObject1, Object paramObject2)
  {
    if (paramObject2 == null) {
      paramObject2 = this.d;
    }
    for (;;)
    {
      if ((paramObject2 instanceof h))
      {
        ((b)paramObject1).a((h)((h)paramObject2).d);
        return;
      }
      ((b)paramObject1).a((h)paramObject2);
      return;
    }
  }
  
  public void a(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2)
  {
    ((b)paramObject1).a(paramObject2, paramInt1, paramInt2);
  }
  
  public void a(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ((b)paramObject1).b(paramObject2, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void a(Object paramObject, String paramString, int paramInt1, int paramInt2)
  {
    ((b)paramObject).a(paramString, paramInt1, paramInt2);
  }
  
  public void a(Object paramObject, String paramString1, String paramString2)
  {
    ((URLConnection)paramObject).setRequestProperty(paramString1, paramString2);
  }
  
  public void a(Object paramObject, boolean paramBoolean)
  {
    ((b)paramObject).i().setAntiAlias(paramBoolean);
  }
  
  public void a(Object paramObject, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    ((f)paramObject).a(paramArrayOfFloat1, paramArrayOfFloat2);
  }
  
  public void a(Object paramObject, int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    ((Bitmap)paramObject).getPixels(paramArrayOfInt, paramInt1, paramInt4, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  public void a(Object paramObject, int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean)
  {
    ((b)paramObject).a(paramArrayOfInt, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramBoolean);
  }
  
  public void a(Object paramObject, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    ((b)paramObject).a(paramArrayOfInt1, paramArrayOfInt2, paramInt);
  }
  
  public void a(String paramString, com.codename1.p.b.b paramB)
  {
    if (paramB != null)
    {
      this.v = new com.codename1.p.k.b();
      this.v.a(paramB);
    }
    try
    {
      localIntent = G(paramString);
      if (localIntent == null) {
        return;
      }
      if ((paramB != null) && (aW() != null))
      {
        aW().startActivityForResult(localIntent, 4);
        return;
      }
    }
    catch (Exception paramB)
    {
      Intent localIntent;
      m.a(paramB);
      try
      {
        if (be()) {
          f(true);
        }
        ba().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      ba().startActivity(localIntent);
    }
  }
  
  public void a(Throwable paramThrowable, Writer paramWriter)
  {
    paramThrowable.printStackTrace(new PrintWriter(paramWriter));
  }
  
  public void a(Vector paramVector)
  {
    aJ();
  }
  
  public void a(boolean paramBoolean)
  {
    super.a(paramBoolean);
    if ((paramBoolean) && ((this.c instanceof a))) {
      ((a)this.c).a();
    }
  }
  
  protected void a(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    super.a(paramArrayOfInt1, paramArrayOfInt2);
  }
  
  public void a(com.codename1.f.f[] paramArrayOfF)
  {
    if (aP())
    {
      a(paramArrayOfF, true);
      return;
    }
    super.a(paramArrayOfF);
  }
  
  public void a(com.codename1.f.f[] paramArrayOfF, boolean paramBoolean)
  {
    a(paramArrayOfF, paramBoolean, false);
  }
  
  public void a(com.codename1.f.f[] paramArrayOfF, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1) {
      try
      {
        CookieSyncManager localCookieSyncManager1 = CookieSyncManager.getInstance();
        localCookieManager = bp();
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z");
        localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        int i2 = paramArrayOfF.length;
        int i1 = 0;
        for (;;)
        {
          if (i1 >= i2) {
            break label358;
          }
          com.codename1.f.f localF = paramArrayOfF[i1];
          Object localObject = new StringBuilder().append(localF.a()).append("=").append(localF.g()).append("; Domain=").append(localF.h()).append("; Path=").append(localF.f()).append("; ");
          if (!localF.d()) {
            break;
          }
          str = "Secure;";
          localObject = ((StringBuilder)localObject).append(str);
          if (localF.i() == 0L) {
            break label334;
          }
          str = " Expires=" + localSimpleDateFormat.format(new Date(localF.i())) + ";";
          localObject = ((StringBuilder)localObject).append(str);
          if (!localF.e()) {
            break label342;
          }
          str = "httpOnly;";
          localObject = str;
          StringBuilder localStringBuilder = new StringBuilder().append("http");
          if (!localF.d()) {
            break label350;
          }
          str = "s";
          localCookieManager.setCookie(str + "://" + localF.h() + localF.f(), (String)localObject);
          i1 += 1;
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        CookieSyncManager localCookieSyncManager2;
        for (;;)
        {
          localCookieSyncManager2 = CookieSyncManager.createInstance(ba());
          CookieManager localCookieManager = bp();
          continue;
          String str = "";
          continue;
          label334:
          str = "";
          continue;
          label342:
          str = "";
          continue;
          label350:
          str = "";
        }
        label358:
        if (paramBoolean2) {
          localCookieSyncManager2.sync();
        }
      }
    }
    super.a(paramArrayOfF);
  }
  
  public void a(final String[] paramArrayOfString, String paramString, com.codename1.h.a paramA)
  {
    int i2 = 0;
    if (be()) {
      f(true);
    }
    String str = paramA.e();
    int i1;
    if (((str != null) && (str.length() > 0)) || (paramA.b().size() > 0)) {
      i1 = 1;
    }
    Object localObject;
    while ((paramA.c().equals("text/plain")) && (i1 == 0))
    {
      localObject = new StringBuilder();
      i1 = i2;
      for (;;)
      {
        if (i1 < paramArrayOfString.length)
        {
          ((StringBuilder)localObject).append(paramArrayOfString[i1]);
          ((StringBuilder)localObject).append(";");
          i1 += 1;
          continue;
          i1 = 0;
          break;
        }
      }
      paramString = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:" + ((StringBuilder)localObject).toString() + "?subject=" + Uri.encode(paramString) + "&body=" + Uri.encode(paramA.a())));
      paramArrayOfString = str;
    }
    for (;;)
    {
      AndroidNativeUtil.startActivityForResult(Intent.createChooser(paramString, "Send mail..."), new k()
      {
        public void a(int paramAnonymousInt1, int paramAnonymousInt2, Intent paramAnonymousIntent)
        {
          if ((paramArrayOfString != null) && (paramArrayOfString.length() > 0) && (paramArrayOfString.contains("tmp"))) {
            com.codename1.f.i.a().b(paramArrayOfString);
          }
        }
      });
      return;
      if (i1 != 0) {
        if (paramA.b().size() > 1)
        {
          localObject = new Intent("android.intent.action.SEND_MULTIPLE");
          ((Intent)localObject).putExtra("android.intent.extra.EMAIL", paramArrayOfString);
          ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", paramString);
          ((Intent)localObject).setType(paramA.c());
          paramArrayOfString = new ArrayList();
          paramString = paramA.b().keySet().iterator();
          while (paramString.hasNext()) {
            paramArrayOfString.add(Uri.parse(J((String)paramString.next())));
          }
          ((Intent)localObject).putParcelableArrayListExtra("android.intent.extra.STREAM", paramArrayOfString);
          paramArrayOfString = str;
          paramString = (String)localObject;
        }
      }
      for (;;)
      {
        if (!paramA.c().equals("text/html")) {
          break label502;
        }
        paramString.putExtra("android.intent.extra.TEXT", Html.fromHtml(paramA.a()));
        break;
        localObject = new Intent("android.intent.action.SEND");
        ((Intent)localObject).putExtra("android.intent.extra.EMAIL", paramArrayOfString);
        ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", paramString);
        ((Intent)localObject).setType(paramA.c());
        ((Intent)localObject).setType(paramA.d());
        paramArrayOfString = J(str);
        ((Intent)localObject).putExtra("android.intent.extra.STREAM", Uri.parse(paramArrayOfString));
        paramString = (String)localObject;
        continue;
        localObject = new Intent("android.intent.action.SEND");
        ((Intent)localObject).putExtra("android.intent.extra.EMAIL", paramArrayOfString);
        ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", paramString);
        ((Intent)localObject).setType(paramA.c());
        paramString = (String)localObject;
        paramArrayOfString = str;
      }
      label502:
      paramString.putExtra("android.intent.extra.TEXT", paramA.a());
    }
  }
  
  public boolean a()
  {
    return super.a();
  }
  
  public boolean a(au paramAu1, au paramAu2)
  {
    Object localObject = null;
    if (paramAu1 != null) {}
    for (paramAu1 = paramAu1.g();; paramAu1 = null)
    {
      if (paramAu2 != null) {
        localObject = paramAu2.g();
      }
      return b(paramAu1, localObject);
    }
  }
  
  public String[] aA()
  {
    if (al()) {
      return new String[] { "tablet", "android", "android-tab" };
    }
    return new String[] { "phone", "android", "android-phone" };
  }
  
  public com.codename1.g.d aD()
  {
    if (this.X == null)
    {
      Locale localLocale = Locale.getDefault();
      this.X = new com.codename1.g.d(localLocale.getLanguage(), localLocale.getCountry())
      {
        public double a(String paramAnonymousString)
        {
          try
          {
            double d = NumberFormat.getNumberInstance().parse(paramAnonymousString).doubleValue();
            return d;
          }
          catch (ParseException localParseException) {}
          return Double.parseDouble(paramAnonymousString);
        }
        
        public String a(double paramAnonymousDouble)
        {
          return NumberFormat.getNumberInstance().format(paramAnonymousDouble);
        }
        
        public String a(Date paramAnonymousDate)
        {
          return DateFormat.getDateInstance(3).format(paramAnonymousDate);
        }
        
        public String b(double paramAnonymousDouble)
        {
          return NumberFormat.getCurrencyInstance().format(paramAnonymousDouble);
        }
        
        public String b(Date paramAnonymousDate)
        {
          return DateFormat.getDateTimeInstance(3, 3).format(paramAnonymousDate);
        }
      };
    }
    return this.X;
  }
  
  public boolean aE()
  {
    return true;
  }
  
  public Object aF()
  {
    return f.a();
  }
  
  public com.codename1.p.k.c aH()
  {
    if (this.Y == null) {
      this.Y = new com.codename1.p.k.c()
      {
        protected void a(x paramAnonymousX, OutputStream paramAnonymousOutputStream, String paramAnonymousString, float paramAnonymousFloat)
          throws IOException
        {
          Bitmap.CompressFormat localCompressFormat = Bitmap.CompressFormat.PNG;
          if (paramAnonymousString == "jpeg") {}
          for (paramAnonymousString = Bitmap.CompressFormat.JPEG;; paramAnonymousString = localCompressFormat)
          {
            ((Bitmap)paramAnonymousX.c()).compress(paramAnonymousString, (int)(100.0F * paramAnonymousFloat), paramAnonymousOutputStream);
            return;
          }
        }
        
        public void a(InputStream paramAnonymousInputStream, OutputStream paramAnonymousOutputStream, String paramAnonymousString, int paramAnonymousInt1, int paramAnonymousInt2, float paramAnonymousFloat)
          throws IOException
        {
          Bitmap.CompressFormat localCompressFormat = Bitmap.CompressFormat.PNG;
          if (paramAnonymousString == "jpeg") {}
          for (paramAnonymousString = Bitmap.CompressFormat.JPEG;; paramAnonymousString = localCompressFormat)
          {
            ((Bitmap)x.b(paramAnonymousInputStream).b(paramAnonymousInt1, paramAnonymousInt2).c()).compress(paramAnonymousString, (int)(100.0F * paramAnonymousFloat), paramAnonymousOutputStream);
            return;
          }
        }
        
        public boolean a(String paramAnonymousString)
        {
          return (paramAnonymousString == "jpeg") || (paramAnonymousString == "png");
        }
      };
    }
    return this.Y;
  }
  
  public boolean aI()
  {
    boolean bool = true;
    if (at.n()) {
      return false;
    }
    v localV = D();
    int i1;
    if (localV != null) {
      if (localV.db().m() == 10)
      {
        i1 = 1;
        if ((!bl()) || (i1 == 0)) {
          break label70;
        }
      }
    }
    for (;;)
    {
      return bool;
      i1 = 0;
      break;
      if (aK() == 10)
      {
        i1 = 1;
        break;
      }
      i1 = 0;
      break;
      label70:
      bool = false;
    }
  }
  
  public void aJ()
  {
    if ((aW() == null) || (at.n())) {}
    v localV;
    do
    {
      return;
      localV = D();
    } while ((localV == null) || (!aI()) || ((localV instanceof p)));
    aW().runOnUiThread(new k(aW(), localV));
  }
  
  public com.codename1.j.a aL()
  {
    try
    {
      this.Z = ((com.codename1.j.a)n.class.newInstance());
      com.codename1.j.a localA = this.Z;
      return localA;
    }
    catch (Throwable localThrowable) {}
    return super.aL();
  }
  
  public String aM()
  {
    return H(ba().getFilesDir().getAbsolutePath() + "/");
  }
  
  public boolean aN()
  {
    return true;
  }
  
  public String aO()
  {
    return ba().getCacheDir().getAbsolutePath();
  }
  
  public int aR()
  {
    return 5;
  }
  
  public boolean aU()
  {
    return true;
  }
  
  public boolean aV()
  {
    return (!this.n) && (Build.VERSION.SDK_INT >= 11);
  }
  
  protected boolean aa()
  {
    return false;
  }
  
  public int af()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    if (aW() != null) {
      aW().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    }
    while (localDisplayMetrics.densityDpi < 160)
    {
      return 20;
      localDisplayMetrics = ba().getResources().getDisplayMetrics();
    }
    if (localDisplayMetrics.densityDpi < 213) {
      return 30;
    }
    if ((localDisplayMetrics.densityDpi >= 213) && (localDisplayMetrics.densityDpi <= 240)) {
      return 40;
    }
    if ((localDisplayMetrics.densityDpi > 240) && (localDisplayMetrics.densityDpi < 400)) {
      return 50;
    }
    if ((localDisplayMetrics.densityDpi >= 400) && (localDisplayMetrics.densityDpi < 560)) {
      return 60;
    }
    if ((localDisplayMetrics.densityDpi >= 560) && (localDisplayMetrics.densityDpi <= 640)) {
      return 70;
    }
    if (localDisplayMetrics.densityDpi > 640) {
      return 80;
    }
    return 30;
  }
  
  public boolean ah()
  {
    boolean bool = true;
    if (!this.O) {
      this.O = true;
    }
    for (;;)
    {
      try
      {
        if ((Build.VERSION.SDK_INT >= 14) || (al())) {
          continue;
        }
        localInputStream = a(getClass(), "/androidTheme.res");
      }
      catch (IOException localIOException)
      {
        InputStream localInputStream;
        localIOException.printStackTrace();
        continue;
        if (localIOException == null) {
          continue;
        }
        continue;
      }
      this.P = bool;
      if (localInputStream != null) {
        localInputStream.close();
      }
      return this.P;
      localInputStream = a(getClass(), "/android_holo_light.res");
      continue;
      bool = false;
    }
  }
  
  public void ai()
  {
    ah();
    if (this.P) {
      try
      {
        if (((Build.VERSION.SDK_INT < 14) && (!al())) || (q.c().a("and.hololight", "false").equals("true"))) {}
        for (InputStream localInputStream = a(getClass(), "/androidTheme.res");; localInputStream = a(getClass(), "/android_holo_light.res"))
        {
          Object localObject = com.codename1.p.k.d.c(localInputStream);
          localObject = ((com.codename1.p.k.d)localObject).g(localObject.b()[0]);
          ((Hashtable)localObject).put("@commandBehavior", "Native");
          com.codename1.p.g.i.a().a((Hashtable)localObject);
          localInputStream.close();
          q.c().i(10);
          return;
        }
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  public boolean aj()
  {
    boolean bool = true;
    int i1 = ba().getResources().getConfiguration().orientation;
    if ((i1 == 0) || (i1 == 3)) {
      bool = super.aj();
    }
    while (i1 == 1) {
      return bool;
    }
    return false;
  }
  
  public boolean ak()
  {
    return true;
  }
  
  public boolean al()
  {
    return (ba().getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public boolean ap()
  {
    if (this.c == null) {}
    while (this.c.c()) {
      return false;
    }
    return true;
  }
  
  public boolean aq()
  {
    return true;
  }
  
  public boolean ar()
  {
    return true;
  }
  
  public String[] as()
  {
    if (!d("android.permission.READ_EXTERNAL_STORAGE", "This is required to browse the file system")) {
      return new String[0];
    }
    String[] arrayOfString1 = bq();
    if (arrayOfString1 != null)
    {
      String[] arrayOfString2 = new String[arrayOfString1.length + 1];
      System.arraycopy(arrayOfString1, 0, arrayOfString2, 0, arrayOfString1.length);
      arrayOfString2[(arrayOfString2.length - 1)] = H(Environment.getRootDirectory().getAbsolutePath());
      return arrayOfString2;
    }
    return new String[] { H(Environment.getRootDirectory().getAbsolutePath()) };
  }
  
  public char at()
  {
    return File.separatorChar;
  }
  
  public String[] av()
  {
    int i2 = 0;
    Object localObject1;
    if (this.x == null)
    {
      this.x = new HashMap();
      localObject3 = ((ConnectivityManager)ba().getSystemService("connectivity")).getAllNetworkInfo();
      i1 = 0;
      while (i1 < localObject3.length)
      {
        localObject2 = localObject3[i1].getTypeName() + "_" + localObject3[i1].getSubtypeName();
        localObject1 = localObject2;
        if (localObject3[i1].getExtraInfo() != null) {
          localObject1 = (String)localObject2 + "_" + localObject3[i1].getExtraInfo();
        }
        this.x.put(localObject1, localObject3[i1]);
        i1 += 1;
      }
    }
    if (this.x.isEmpty())
    {
      localObject1 = null;
      return localObject1;
    }
    Object localObject2 = new String[this.x.size()];
    Object localObject3 = this.x.keySet().iterator();
    int i1 = i2;
    for (;;)
    {
      localObject1 = localObject2;
      if (!((Iterator)localObject3).hasNext()) {
        break;
      }
      localObject2[i1] = ((Iterator)localObject3).next().toString();
      i1 += 1;
    }
  }
  
  public boolean aw()
  {
    return true;
  }
  
  public com.codename1.location.f ay()
  {
    if (!d("android.permission.ACCESS_FINE_LOCATION", "This is required to get the location")) {
      return null;
    }
    if ((q.c().a("IncludeGPlayServices", "false").equals("true")) && (bs())) {
      try
      {
        com.codename1.location.f localF = (com.codename1.location.f)Class.forName("com.codename1.location.AndroidLocationPlayServiceManager").getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        return localF;
      }
      catch (Exception localException)
      {
        return com.codename1.location.a.a(ba());
      }
    }
    return com.codename1.location.a.a(ba());
  }
  
  public String az()
  {
    return "and";
  }
  
  public Object b(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    f localF = f.a();
    localF.c(paramFloat1, paramFloat2, paramFloat3);
    return localF;
  }
  
  public Object b(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return f.b(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public Object b(int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject;
    switch (paramInt1)
    {
    default: 
      localObject = Typeface.DEFAULT;
      if ((paramInt2 & 0x1) == 0) {
        break;
      }
    }
    for (int i1 = 1;; i1 = 0)
    {
      int i2 = i1;
      if ((paramInt2 & 0x2) != 0) {
        i2 = i1 | 0x2;
      }
      i1 = this.e;
      int i3 = i1 / 3;
      switch (paramInt3)
      {
      default: 
        label88:
        localObject = new h(Typeface.create((Typeface)localObject, i2));
        ((Paint)localObject).setAntiAlias(true);
        if ((paramInt2 & 0x4) == 0) {
          break;
        }
      }
      for (boolean bool = true;; bool = false)
      {
        ((Paint)localObject).setUnderlineText(bool);
        ((Paint)localObject).setTextSize(i1);
        return new h(paramInt1, paramInt2, paramInt3, localObject);
        localObject = Typeface.MONOSPACE;
        break;
        i1 -= i3;
        break label88;
        i1 += i3;
        break label88;
      }
    }
  }
  
  public Object b(String paramString)
    throws IOException
  {
    int i2 = 0;
    Object localObject2 = null;
    int i1 = 1;
    int i3 = e();
    Object localObject1;
    if (v(paramString)) {
      localObject1 = localObject2;
    }
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localObject1 = localObject2;
      localOptions.inJustDecodeBounds = true;
      localObject1 = localObject2;
      localOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
      localObject1 = localObject2;
      Object localObject3 = B(paramString);
      localObject1 = localObject2;
      BitmapFactory.decodeStream((InputStream)localObject3, null, localOptions);
      localObject1 = localObject2;
      ((InputStream)localObject3).close();
      localObject1 = localObject2;
      if (localOptions.outHeight <= i3)
      {
        localObject1 = localObject2;
        if (localOptions.outWidth <= i3) {}
      }
      else
      {
        localObject1 = localObject2;
        i1 = (int)Math.pow(2.0D, (int)Math.round(Math.log(i3 / Math.max(localOptions.outHeight, localOptions.outWidth)) / Math.log(0.5D)));
      }
      localObject1 = localObject2;
      localOptions = new BitmapFactory.Options();
      localObject1 = localObject2;
      localOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
      localObject1 = localObject2;
      if (this.N != -1)
      {
        localObject1 = localObject2;
        localOptions.inSampleSize = this.N;
        localObject1 = localObject2;
        localOptions.inPurgeable = true;
        localObject1 = localObject2;
        localOptions.inInputShareable = true;
        localObject1 = localObject2;
        localObject3 = B(paramString);
        localObject1 = localObject2;
        localObject2 = BitmapFactory.decodeStream((InputStream)localObject3, null, localOptions);
        localObject1 = localObject2;
        ((InputStream)localObject3).close();
        i1 = i2;
        localObject1 = localObject2;
        switch (new ExifInterface(paramString).getAttributeInt("Orientation", 1))
        {
        }
      }
      for (;;)
      {
        paramString = (String)localObject2;
        localObject1 = localObject2;
        if (this.N >= 0) {
          break label569;
        }
        paramString = (String)localObject2;
        if (i1 == 0) {
          break label569;
        }
        localObject1 = localObject2;
        paramString = new Matrix();
        localObject1 = localObject2;
        paramString.postRotate(i1);
        localObject1 = localObject2;
        paramString = Bitmap.createBitmap((Bitmap)localObject2, 0, 0, ((Bitmap)localObject2).getWidth(), ((Bitmap)localObject2).getHeight(), paramString, true);
        localObject1 = localObject2;
        ((Bitmap)localObject2).recycle();
        return paramString;
        localObject1 = localObject2;
        localObject3 = q.c().a("android.sampleSize", null);
        if (localObject3 != null)
        {
          localObject1 = localObject2;
          localOptions.inSampleSize = Integer.parseInt((String)localObject3);
          break;
        }
        localObject1 = localObject2;
        localOptions.inSampleSize = i1;
        break;
        i1 = 90;
        continue;
        i1 = 180;
        continue;
        i1 = 270;
        continue;
        localObject2 = a(getClass(), paramString);
        if (localObject2 == null) {
          throw new IOException("Resource not found. " + paramString);
        }
        try
        {
          localObject1 = a((InputStream)localObject2);
          paramString = localObject1;
          if (localObject2 == null) {
            break label569;
          }
          try
          {
            ((InputStream)localObject2).close();
            return localObject1;
          }
          catch (Exception paramString)
          {
            return localObject1;
          }
          i1 = i2;
        }
        finally
        {
          if (localObject2 != null) {}
          try
          {
            ((InputStream)localObject2).close();
            throw paramString;
          }
          catch (Exception localException)
          {
            for (;;) {}
          }
        }
      }
      label569:
      return paramString;
    }
    catch (IOException paramString) {}
    return localException;
  }
  
  public String b(String paramString1, String paramString2)
  {
    Object localObject = null;
    if (paramString1.equalsIgnoreCase("cn1_push_prefix"))
    {
      paramString1 = paramString2;
      if (bs()) {
        paramString1 = "gcm";
      }
    }
    do
    {
      do
      {
        for (;;)
        {
          return paramString1;
          if ("OS".equals(paramString1)) {
            return "Android";
          }
          if ("androidId".equals(paramString1)) {
            return Settings.Secure.getString(ba().getContentResolver(), "android_id");
          }
          if ("cellId".equals(paramString1)) {
            paramString1 = paramString2;
          }
          try
          {
            if (!d("android.permission.READ_PHONE_STATE", "This is required to get the cellId")) {
              continue;
            }
            i1 = ((GsmCellLocation)((TelephonyManager)ba().getSystemService("phone")).getCellLocation()).getCid();
            paramString1 = "" + i1;
            return paramString1;
          }
          catch (Throwable paramString1)
          {
            try
            {
              int i1;
              if (("IMEI".equals(paramString1)) || ("UDID".equals(paramString1)))
              {
                if (!d("android.permission.READ_PHONE_STATE", "This is required to get the device ID")) {
                  return "";
                }
                paramString1 = (TelephonyManager)ba().getSystemService("phone");
                if ((paramString1 != null) && (paramString1.getDeviceId() != null)) {
                  paramString1 = paramString1.getDeviceId();
                }
                for (;;)
                {
                  return paramString1;
                  try
                  {
                    paramString1 = Settings.Secure.getString(ba().getContentResolver(), "android_id");
                  }
                  catch (Throwable paramString1)
                  {
                    m.a(paramString1);
                    paramString1 = (String)localObject;
                  }
                }
              }
              if ("MSISDN".equals(paramString1))
              {
                if (!d("android.permission.READ_PHONE_STATE", "This is required to get the device ID")) {
                  return "";
                }
                paramString1 = ((TelephonyManager)ba().getSystemService("phone")).getLine1Number();
                return paramString1;
              }
              if (aW() != null)
              {
                localObject = aW().getIntent();
                if (localObject != null)
                {
                  localObject = ((Intent)localObject).getExtras();
                  if (localObject != null)
                  {
                    localObject = ((Bundle)localObject).getString(paramString1);
                    if (localObject != null) {
                      return localObject;
                    }
                  }
                }
              }
              try
              {
                i1 = ba().getResources().getIdentifier(paramString1, "string", ba().getApplicationInfo().packageName);
                if (i1 != 0)
                {
                  localObject = ba().getResources().getString(i1);
                  return localObject;
                }
              }
              catch (Exception localException) {}
              return System.getProperty(paramString1, super.b(paramString1, paramString2));
            }
            catch (Throwable paramString1) {}
            paramString1 = paramString1;
            return paramString2;
          }
          if ("AppName".equals(paramString1))
          {
            localObject = ba().getPackageManager();
            try
            {
              paramString1 = ((PackageManager)localObject).getApplicationInfo(ba().getPackageName(), 0);
              if (paramString1 != null)
              {
                paramString1 = ((PackageManager)localObject).getApplicationLabel(paramString1);
                localObject = (String)paramString1;
                paramString1 = paramString2;
                if (localObject == null) {
                  continue;
                }
                return localObject;
              }
            }
            catch (PackageManager.NameNotFoundException paramString1)
            {
              for (;;)
              {
                paramString1 = null;
                continue;
                paramString1 = null;
              }
            }
          }
        }
        if ("AppVersion".equals(paramString1)) {
          try
          {
            paramString1 = ba().getPackageManager().getPackageInfo(ba().getApplicationInfo().packageName, 0).versionName;
            return paramString1;
          }
          catch (PackageManager.NameNotFoundException paramString1)
          {
            paramString1.printStackTrace();
            return paramString2;
          }
        }
        if (!"Platform".equals(paramString1)) {
          break;
        }
        localObject = System.getProperty("platform");
        paramString1 = paramString2;
      } while (localObject == null);
      return localObject;
      if (!"User-Agent".equals(paramString1)) {
        break;
      }
      localObject = bo();
      paramString1 = paramString2;
    } while (localObject == null);
    return localObject;
    if ("OSVer".equals(paramString1)) {
      return "" + Build.VERSION.RELEASE;
    }
    if ("DeviceName".equals(paramString1)) {
      return "" + Build.MODEL;
    }
    return paramString2;
  }
  
  public void b(int paramInt)
  {
    if (!this.r) {}
    try
    {
      this.q = ((Vibrator)ba().getSystemService("vibrator"));
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        localThrowable1 = localThrowable1;
        Log.e("Codename One", "problem with virbrator(0)", localThrowable1);
        this.r = true;
      }
    }
    finally
    {
      this.r = true;
    }
    if (this.q != null) {}
  }
  
  protected void b(int paramInt1, int paramInt2)
  {
    super.b(paramInt1, paramInt2);
  }
  
  public void b(com.codename1.p.a.a paramA)
  {
    Object localObject = paramA;
    if (this.c != null)
    {
      localObject = paramA;
      if (this.c.c())
      {
        if (!(paramA instanceof l)) {
          break label74;
        }
        localObject = (l)paramA;
        ((l)localObject).b(null);
        if (((l)localObject).am() == null) {
          break label58;
        }
        localObject = ((l)localObject).aM();
      }
    }
    for (;;)
    {
      super.b((com.codename1.p.a.a)localObject);
      return;
      label58:
      v localV = D();
      localObject = paramA;
      if (localV != null)
      {
        localObject = localV;
        continue;
        label74:
        localV = D();
        localObject = paramA;
        if (localV != null)
        {
          super.b(localV);
          localObject = paramA;
        }
      }
    }
  }
  
  public void b(l paramL, int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    if ((paramInt3 > 0) && (O() == 2)) {
      new StringBuilder().append(paramString).append((char)paramInt3).toString();
    }
    InPlaceEditView.a(this, paramL, paramInt2);
  }
  
  public void b(v paramV)
  {
    if (aW() == null) {}
    do
    {
      return;
      if (D() == null) {
        t();
      }
      if (be()) {
        f(true);
      }
      super.b(paramV);
    } while ((!aI()) || ((paramV instanceof p)));
    aW().runOnUiThread(new k(aW(), paramV));
  }
  
  public void b(Object paramObject)
  {
    if ((paramObject instanceof CodenameOneActivity))
    {
      c(null);
      a((CodenameOneActivity)paramObject);
    }
    for (;;)
    {
      J = this;
      if ((aW() == null) || (!aW().m()) || (!bl())) {}
      try
      {
        aW().requestWindowFeature(1);
        for (;;)
        {
          if (this.F)
          {
            aW().getWindow().getDecorView().setSystemUiVisibility(1280);
            aW().getWindow().setStatusBarColor(0);
          }
          if (q.c().a("StatusbarHidden", "").equals("true")) {
            aW().getWindow().setFlags(1024, 1024);
          }
          if (q.c().a("KeepScreenOn", "").equals("true")) {
            aW().getWindow().addFlags(128);
          }
          if (q.c().a("DisableScreenshots", "").equals("true")) {
            aW().getWindow().addFlags(8192);
          }
          if ((paramObject instanceof CodenameOneActivity))
          {
            ((CodenameOneActivity)paramObject).b(this);
            ((CodenameOneActivity)paramObject).a(this);
          }
          this.e = j(16);
          this.d = ((h)((h)b(0, 0, 0)).d);
          q.c().e(-1);
          bm();
          e(1);
          paramObject = new d(this);
          q.c().a(paramObject);
          q.c().b(paramObject);
          InPlaceEditView.f();
          aW().getWindow().setSoftInputMode(3);
          if (this.h.size() > 0)
          {
            int i1 = 0;
            for (;;)
            {
              if (i1 < this.h.size())
              {
                ((e)this.h.elementAt(i1)).l();
                i1 += 1;
                continue;
                a(null);
                c((Context)paramObject);
                break;
                aW().invalidateOptionsMenu();
              }
            }
          }
          try
          {
            aW().requestWindowFeature(8);
            aW().requestWindowFeature(2);
            if (Build.VERSION.SDK_INT >= 21) {
              aW().getWindow().addFlags(Integer.MIN_VALUE);
            }
            new j(aW(), false).run();
            continue;
            this.e = j(16);
            this.d = ((h)((h)b(0, 0, 0)).d);
            HttpURLConnection.setFollowRedirects(false);
            CookieHandler.setDefault(null);
            return;
          }
          catch (Exception localException1)
          {
            for (;;) {}
          }
        }
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  public void b(Object paramObject, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    paramObject = (f)paramObject;
    paramObject.d();
    paramObject.c(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public void b(Object paramObject, int paramInt)
  {
    ((b)paramObject).b(h(paramObject) & 0xFF000000 | paramInt);
  }
  
  public void b(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ((b)paramObject).a(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void b(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    ((b)paramObject).a(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }
  
  public void b(Object paramObject, au paramAu)
  {
    paramObject = ((b)paramObject).h();
    if (paramObject == null)
    {
      paramAu.d();
      return;
    }
    paramAu.b(paramObject);
  }
  
  public void b(Object paramObject, com.codename1.p.c.i paramI)
  {
    ((b)paramObject).a(a(paramI));
  }
  
  public void b(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ((b)paramObject1).a(paramObject2, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void b(Object paramObject, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      ((HttpURLConnection)paramObject).setRequestMethod("POST");
      return;
    }
    catch (IOException paramObject)
    {
      paramObject.printStackTrace();
    }
    ((HttpURLConnection)paramObject).setRequestMethod("GET");
    return;
  }
  
  public boolean b(l paramL)
  {
    return (super.b(paramL)) && (!InPlaceEditView.c());
  }
  
  public boolean b(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 != null) {
      return ((f)paramObject1).a((f)paramObject2);
    }
    return paramObject2 == null;
  }
  
  public String[] b(String paramString, Object paramObject)
    throws IOException
  {
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramObject;
    paramObject = new ArrayList();
    Iterator localIterator = localHttpURLConnection.getHeaderFields().keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((str != null) && (str.equalsIgnoreCase(paramString))) {
        paramObject.addAll((Collection)localHttpURLConnection.getHeaderFields().get(str));
      }
    }
    if (paramObject.size() > 0)
    {
      paramString = new ArrayList();
      paramString.addAll(paramObject);
      Collections.reverse(paramString);
      paramObject = new String[paramString.size()];
      paramString.toArray(paramObject);
      return paramObject;
    }
    paramString = localHttpURLConnection.getHeaderField(paramString);
    if ((paramString != null) && (paramString.length() > 0)) {
      return new String[] { paramString };
    }
    return null;
  }
  
  public void bc()
  {
    super.M();
    p();
  }
  
  public void bd()
  {
    super.N();
  }
  
  protected boolean be()
  {
    return InPlaceEditView.e();
  }
  
  void bf()
  {
    super.k();
  }
  
  public Object bh()
  {
    return new h(0, 0, 0, new h(this.d));
  }
  
  com.codename1.a.a bi()
  {
    if ((aW() != null) && ((aW().a() instanceof com.codename1.a.a))) {
      return (com.codename1.a.a)aW().a();
    }
    if (m != null) {
      return m;
    }
    return null;
  }
  
  public Object c(String paramString)
  {
    int i1 = 1;
    try
    {
      String str2 = paramString.split(";")[0];
      paramString = str2.substring(0, str2.indexOf("-"));
      String str1 = str2.substring(str2.indexOf("-") + 1, str2.lastIndexOf("-"));
      str2 = str2.substring(str2.lastIndexOf("-") + 1, str2.length());
      if (str1.equals("bolditalic")) {
        i1 = 3;
      }
      for (;;)
      {
        paramString = new h(Typeface.create(paramString, i1));
        paramString.setAntiAlias(true);
        paramString.setTextSize(Integer.parseInt(str2));
        return new h(0, 0, 0, paramString);
        if (str1.equals("italic"))
        {
          i1 = 2;
        }
        else
        {
          boolean bool = str1.equals("bold");
          if (!bool) {
            i1 = 0;
          }
        }
      }
      return null;
    }
    catch (Exception paramString) {}
  }
  
  public void c()
  {
    super.c();
    if (aW() != null)
    {
      Runnable local18 = new Runnable()
      {
        public void run()
        {
          synchronized (c.this)
          {
            if (!c.bj()) {
              return;
            }
            c.i(false);
            if (c.this.h.size() > 0)
            {
              int i = 0;
              if (i < c.this.h.size())
              {
                ((c.e)c.this.h.elementAt(i)).k();
                i += 1;
              }
            }
          }
          if (c.this.g != null) {
            c.this.g.removeAllViews();
          }
          c.this.g = null;
          c.this.c = null;
        }
      };
      if (Looper.getMainLooper().getThread() == Thread.currentThread())
      {
        K = true;
        local18.run();
        return;
      }
      K = true;
      aW().runOnUiThread(local18);
      return;
    }
    K = false;
  }
  
  protected void c(int paramInt1, int paramInt2)
  {
    super.c(paramInt1, paramInt2);
  }
  
  public void c(Context paramContext)
  {
    u = paramContext;
  }
  
  public void c(Object paramObject, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    ((f)paramObject).b(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public void c(Object paramObject, int paramInt)
  {
    ((b)paramObject).a(paramInt);
  }
  
  public void c(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ((b)paramObject).e(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void c(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    ((b)paramObject).c(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }
  
  public void c(Object paramObject1, Object paramObject2)
  {
    paramObject1 = (f)paramObject1;
    ((f)paramObject2).a(paramObject1.e());
  }
  
  public void c(String paramString1, String paramString2)
  {
    if (paramString1.equals("platformHint.compatPaintMode")) {
      this.D = paramString2.equalsIgnoreCase("true");
    }
    while (!paramString1.equals("platformHint.legacyPaint")) {
      return;
    }
    a.a = paramString2.equalsIgnoreCase("true");
  }
  
  public void c(boolean paramBoolean)
  {
    if (aW() == null) {
      return;
    }
    if (paramBoolean)
    {
      aW().setRequestedOrientation(1);
      return;
    }
    aW().setRequestedOrientation(0);
  }
  
  protected void c(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    super.c(paramArrayOfInt1, paramArrayOfInt2);
  }
  
  public int[] c(int paramInt)
  {
    if (paramInt == 0) {
      return b;
    }
    return null;
  }
  
  public String[] c(Object paramObject, String paramString)
    throws IOException
  {
    if ((paramObject instanceof HttpsURLConnection))
    {
      paramObject = (HttpsURLConnection)paramObject;
      try
      {
        paramObject.connect();
        Certificate[] arrayOfCertificate = paramObject.getServerCertificates();
        paramString = new String[arrayOfCertificate.length];
        int i3 = arrayOfCertificate.length;
        int i2 = 0;
        int i1 = 0;
        for (;;)
        {
          paramObject = paramString;
          if (i2 >= i3) {
            break;
          }
          paramObject = arrayOfCertificate[i2];
          MessageDigest localMessageDigest = MessageDigest.getInstance("SHA1");
          localMessageDigest.update(paramObject.getEncoded());
          paramString[i1] = ("SHA1:" + a(localMessageDigest.digest()));
          i2 += 1;
          i1 += 1;
        }
        paramObject = new String[0];
      }
      catch (Exception paramObject)
      {
        paramObject.printStackTrace();
      }
    }
    return paramObject;
  }
  
  public int d()
  {
    if (this.c != null)
    {
      int i1 = this.c.getViewWidth();
      this.s = i1;
      return i1;
    }
    return this.s;
  }
  
  public int d(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case -23449: 
      return 6;
    case -23448: 
      return 1;
    case -23446: 
      return 2;
    case -23447: 
      return 5;
    }
    return 8;
  }
  
  public int d(Object paramObject)
  {
    return ((Bitmap)paramObject).getWidth();
  }
  
  public void d(Object paramObject, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    ((f)paramObject).c(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public void d(Object paramObject, int paramInt)
  {
    ((HttpURLConnection)paramObject).setChunkedStreamingMode(paramInt);
  }
  
  public void d(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ((b)paramObject).c(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void d(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    ((b)paramObject).d(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }
  
  public void d(Object paramObject1, Object paramObject2)
  {
    ((f)paramObject1).b((f)paramObject2);
  }
  
  public void d(Object paramObject, String paramString)
    throws IOException
  {
    if (paramString.equalsIgnoreCase("patch")) {
      a((HttpURLConnection)paramObject);
    }
    ((HttpURLConnection)paramObject).setRequestMethod(paramString);
  }
  
  protected void d(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    super.d(paramArrayOfInt1, paramArrayOfInt2);
  }
  
  public int e()
  {
    if (this.c != null)
    {
      int i1 = this.c.getViewHeight();
      this.t = i1;
      return i1;
    }
    return this.t;
  }
  
  public int e(Object paramObject)
  {
    return ((Bitmap)paramObject).getHeight();
  }
  
  public OutputStream e(Object paramObject, int paramInt)
    throws IOException
  {
    Object localObject = I((String)paramObject);
    paramObject = new RandomAccessFile((String)localObject, "rw");
    paramObject.seek(paramInt);
    localObject = new com.codename1.f.c(new FileOutputStream(paramObject.getFD()), (String)localObject);
    ((com.codename1.f.c)localObject).a(paramObject);
    return localObject;
  }
  
  public void e(String paramString)
  {
    a(paramString, null);
  }
  
  void e(boolean paramBoolean)
  {
    this.C = paramBoolean;
  }
  
  public int f()
  {
    return ba().getResources().getDisplayMetrics().heightPixels;
  }
  
  public void f(int paramInt)
  {
    if ((paramInt == 10) && ((aW() instanceof CodenameOneActivity))) {
      aW().a(true);
    }
  }
  
  public void f(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ((b)paramObject).d(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void f(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    ((b)paramObject).b(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }
  
  public void f(String paramString)
  {
    if ((aW() != null) && ("press" == paramString)) {
      aW().runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (c.this.c != null) {
            c.this.c.getAndroidView().playSoundEffect(0);
          }
        }
      });
    }
  }
  
  public void g()
  {
    p();
  }
  
  public void g(int paramInt)
  {
    this.w = paramInt;
  }
  
  public int h(Object paramObject)
  {
    return ((b)paramObject).b();
  }
  
  public int i(Object paramObject)
  {
    return ((b)paramObject).g();
  }
  
  public boolean i(int paramInt)
  {
    boolean bool = false;
    if (Build.VERSION.SDK_INT >= 11) {
      if ((paramInt == 1) || (paramInt == 2) || (paramInt == 4)) {
        bool = true;
      }
    }
    while ((paramInt != 1) && (paramInt != 2)) {
      return bool;
    }
    return true;
  }
  
  public int j(int paramInt)
  {
    return (int)TypedValue.applyDimension(2, paramInt, ba().getResources().getDisplayMetrics());
  }
  
  public int k(Object paramObject)
  {
    return ((b)paramObject).d();
  }
  
  public Vector k(String paramString)
  {
    int i1 = 0;
    if (aP()) {
      try
      {
        Object localObject2 = new URI(paramString);
        Object localObject1 = bp();
        ((CookieManager)localObject1).removeExpiredCookie();
        localObject2 = ((URI)localObject2).getHost();
        paramString = ((CookieManager)localObject1).getCookie(paramString);
        if (paramString != null)
        {
          String[] arrayOfString1 = paramString.split(";");
          int i2 = arrayOfString1.length;
          localObject1 = new Vector();
          paramString = (String)localObject1;
          if (i1 >= i2) {
            break label169;
          }
          paramString = new com.codename1.f.f();
          String[] arrayOfString2 = arrayOfString1[i1].split("=");
          paramString.a(arrayOfString2[0].trim());
          if (arrayOfString2.length > 1) {
            paramString.c(arrayOfString2[1].trim());
          }
          for (;;)
          {
            paramString.d((String)localObject2);
            ((Vector)localObject1).add(paramString);
            i1 += 1;
            break;
            paramString.c("");
          }
        }
        return paramString;
      }
      catch (Exception paramString)
      {
        m.a(paramString);
        paramString = new Vector();
      }
    }
    label169:
    return super.k(paramString);
  }
  
  public void k() {}
  
  public int l(Object paramObject)
  {
    return ((b)paramObject).c();
  }
  
  public OutputStream l(String paramString)
    throws IOException
  {
    paramString = I(paramString);
    try
    {
      OutputStream localOutputStream = C(paramString);
      return localOutputStream;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      if (localFileNotFoundException.getMessage().contains("Permission denied"))
      {
        if (!d("android.permission.WRITE_EXTERNAL_STORAGE", "This is required to access the file")) {
          return null;
        }
        return C(paramString);
      }
      throw localFileNotFoundException;
    }
  }
  
  /* Error */
  public String l()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 3473	com/codename1/impl/a:l	()Ljava/lang/String;
    //   4: ifnull +8 -> 12
    //   7: aload_0
    //   8: invokespecial 3473	com/codename1/impl/a:l	()Ljava/lang/String;
    //   11: areturn
    //   12: invokestatic 1242	com/codename1/impl/android/c:aW	()Lcom/codename1/impl/android/CodenameOneActivity;
    //   15: ifnonnull +5 -> 20
    //   18: aconst_null
    //   19: areturn
    //   20: invokestatic 1242	com/codename1/impl/android/c:aW	()Lcom/codename1/impl/android/CodenameOneActivity;
    //   23: invokevirtual 3113	com/codename1/impl/android/CodenameOneActivity:getIntent	()Landroid/content/Intent;
    //   26: astore 6
    //   28: aload 6
    //   30: ifnull +332 -> 362
    //   33: aload 6
    //   35: invokevirtual 2270	android/content/Intent:getData	()Landroid/net/Uri;
    //   38: astore 5
    //   40: aload 6
    //   42: invokevirtual 2296	android/content/Intent:getScheme	()Ljava/lang/String;
    //   45: astore_2
    //   46: aload 5
    //   48: astore_3
    //   49: aload_2
    //   50: astore 4
    //   52: aload 5
    //   54: ifnonnull +89 -> 143
    //   57: aload 5
    //   59: astore_3
    //   60: aload_2
    //   61: astore 4
    //   63: aload 6
    //   65: invokevirtual 1273	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   68: ifnull +75 -> 143
    //   71: aload 5
    //   73: astore_3
    //   74: aload_2
    //   75: astore 4
    //   77: aload 6
    //   79: invokevirtual 1273	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   82: invokevirtual 3474	android/os/Bundle:keySet	()Ljava/util/Set;
    //   85: ldc_w 2669
    //   88: invokeinterface 3476 2 0
    //   93: ifeq +50 -> 143
    //   96: aload 6
    //   98: ldc_w 2669
    //   101: invokevirtual 3480	android/content/Intent:getParcelableExtra	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   104: checkcast 432	android/net/Uri
    //   107: astore_3
    //   108: aload_3
    //   109: invokevirtual 845	android/net/Uri:getScheme	()Ljava/lang/String;
    //   112: astore 4
    //   114: aload 4
    //   116: astore_2
    //   117: getstatic 572	java/lang/System:out	Ljava/io/PrintStream;
    //   120: new 401	java/lang/StringBuilder
    //   123: dup
    //   124: invokespecial 402	java/lang/StringBuilder:<init>	()V
    //   127: ldc_w 3482
    //   130: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: aload_3
    //   134: invokevirtual 3485	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   137: invokevirtual 412	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   140: invokevirtual 586	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   143: aload_3
    //   144: ifnull +218 -> 362
    //   147: aload 6
    //   149: aconst_null
    //   150: invokevirtual 590	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   153: pop
    //   154: ldc_w 855
    //   157: aload 4
    //   159: invokevirtual 363	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   162: ifeq +187 -> 349
    //   165: invokestatic 1242	com/codename1/impl/android/c:aW	()Lcom/codename1/impl/android/CodenameOneActivity;
    //   168: invokevirtual 3486	com/codename1/impl/android/CodenameOneActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   171: aload_3
    //   172: invokevirtual 859	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   175: astore_2
    //   176: aload_2
    //   177: ifnull +185 -> 362
    //   180: aload_0
    //   181: invokestatic 1242	com/codename1/impl/android/c:aW	()Lcom/codename1/impl/android/CodenameOneActivity;
    //   184: invokevirtual 3486	com/codename1/impl/android/CodenameOneActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   187: aload_3
    //   188: invokespecial 2298	com/codename1/impl/android/c:a	(Landroid/content/ContentResolver;Landroid/net/Uri;)Ljava/lang/String;
    //   191: astore_3
    //   192: aload_3
    //   193: ifnull +169 -> 362
    //   196: new 401	java/lang/StringBuilder
    //   199: dup
    //   200: invokespecial 402	java/lang/StringBuilder:<init>	()V
    //   203: aload_0
    //   204: invokevirtual 863	com/codename1/impl/android/c:aM	()Ljava/lang/String;
    //   207: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: aload_0
    //   211: invokevirtual 872	com/codename1/impl/android/c:at	()C
    //   214: invokevirtual 875	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   217: aload_3
    //   218: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 412	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: astore_3
    //   225: aload_0
    //   226: new 487	java/io/File
    //   229: dup
    //   230: aload_3
    //   231: invokespecial 488	java/io/File:<init>	(Ljava/lang/String;)V
    //   234: invokevirtual 878	com/codename1/impl/android/c:a	(Ljava/io/File;)Ljava/io/OutputStream;
    //   237: astore 4
    //   239: sipush 1024
    //   242: newarray byte
    //   244: astore 5
    //   246: aload_2
    //   247: aload 5
    //   249: invokevirtual 678	java/io/InputStream:read	([B)I
    //   252: istore_1
    //   253: iload_1
    //   254: iconst_m1
    //   255: if_icmple +64 -> 319
    //   258: aload 4
    //   260: aload 5
    //   262: iconst_0
    //   263: iload_1
    //   264: invokevirtual 684	java/io/OutputStream:write	([BII)V
    //   267: goto -21 -> 246
    //   270: astore_2
    //   271: aload_2
    //   272: invokevirtual 3487	java/io/FileNotFoundException:printStackTrace	()V
    //   275: aconst_null
    //   276: areturn
    //   277: astore 4
    //   279: aload 5
    //   281: astore_3
    //   282: ldc_w 2053
    //   285: new 401	java/lang/StringBuilder
    //   288: dup
    //   289: invokespecial 402	java/lang/StringBuilder:<init>	()V
    //   292: ldc_w 3489
    //   295: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: aload 4
    //   300: invokevirtual 3490	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   303: invokevirtual 408	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: invokevirtual 412	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: invokestatic 632	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   312: pop
    //   313: aload_2
    //   314: astore 4
    //   316: goto -173 -> 143
    //   319: aload 4
    //   321: invokevirtual 710	java/io/OutputStream:close	()V
    //   324: aload_2
    //   325: invokevirtual 709	java/io/InputStream:close	()V
    //   328: aload_0
    //   329: aload_3
    //   330: invokevirtual 1412	com/codename1/impl/android/c:a	(Ljava/lang/String;)V
    //   333: aload_3
    //   334: areturn
    //   335: astore_2
    //   336: aload_2
    //   337: invokevirtual 2406	java/io/IOException:printStackTrace	()V
    //   340: aconst_null
    //   341: areturn
    //   342: astore_2
    //   343: aload_2
    //   344: invokevirtual 879	java/lang/Exception:printStackTrace	()V
    //   347: aconst_null
    //   348: areturn
    //   349: aload_0
    //   350: aload_3
    //   351: invokevirtual 706	android/net/Uri:toString	()Ljava/lang/String;
    //   354: invokevirtual 1412	com/codename1/impl/android/c:a	(Ljava/lang/String;)V
    //   357: aload_3
    //   358: invokevirtual 706	android/net/Uri:toString	()Ljava/lang/String;
    //   361: areturn
    //   362: aconst_null
    //   363: areturn
    //   364: astore 4
    //   366: goto -84 -> 282
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	369	0	this	c
    //   252	12	1	i1	int
    //   45	202	2	localObject1	Object
    //   270	55	2	localFileNotFoundException1	FileNotFoundException
    //   335	2	2	localIOException	IOException
    //   342	2	2	localException1	Exception
    //   48	310	3	localObject2	Object
    //   50	209	4	localObject3	Object
    //   277	22	4	localException2	Exception
    //   314	6	4	localFileNotFoundException2	FileNotFoundException
    //   364	1	4	localException3	Exception
    //   38	242	5	localObject4	Object
    //   26	122	6	localIntent	Intent
    // Exception table:
    //   from	to	target	type
    //   165	176	270	java/io/FileNotFoundException
    //   180	192	270	java/io/FileNotFoundException
    //   196	246	270	java/io/FileNotFoundException
    //   246	253	270	java/io/FileNotFoundException
    //   258	267	270	java/io/FileNotFoundException
    //   319	333	270	java/io/FileNotFoundException
    //   96	108	277	java/lang/Exception
    //   165	176	335	java/io/IOException
    //   180	192	335	java/io/IOException
    //   196	246	335	java/io/IOException
    //   246	253	335	java/io/IOException
    //   258	267	335	java/io/IOException
    //   319	333	335	java/io/IOException
    //   165	176	342	java/lang/Exception
    //   180	192	342	java/lang/Exception
    //   196	246	342	java/lang/Exception
    //   246	253	342	java/lang/Exception
    //   258	267	342	java/lang/Exception
    //   319	333	342	java/lang/Exception
    //   108	114	364	java/lang/Exception
    //   117	143	364	java/lang/Exception
  }
  
  public int m(Object paramObject)
  {
    return ((b)paramObject).e();
  }
  
  public InputStream m(String paramString)
    throws IOException
  {
    paramString = I(paramString);
    try
    {
      InputStream localInputStream = B(paramString);
      return localInputStream;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      if (localFileNotFoundException.getMessage().contains("Permission denied"))
      {
        if (!d("android.permission.WRITE_EXTERNAL_STORAGE", "This is required to access the file")) {
          return null;
        }
        return m(paramString);
      }
      throw localFileNotFoundException;
    }
  }
  
  public int n(Object paramObject)
  {
    return ((b)paramObject).f();
  }
  
  public void n(String paramString)
  {
    ba().deleteFile(paramString);
  }
  
  public boolean n()
  {
    return this.C;
  }
  
  public OutputStream o(String paramString)
    throws IOException
  {
    return ba().openFileOutput(paramString, 0);
  }
  
  public au p(Object paramObject)
  {
    paramObject = ((b)paramObject).h();
    if (paramObject == null) {
      return au.b();
    }
    au localAu = au.b();
    localAu.b(paramObject);
    return localAu;
  }
  
  public InputStream p(String paramString)
    throws IOException
  {
    return ba().openFileInput(paramString);
  }
  
  public void p()
  {
    f(true);
  }
  
  public boolean q()
  {
    if ((this.c != null) && (this.c.getAndroidView().getVisibility() != 0)) {
      return true;
    }
    return super.q();
  }
  
  public boolean q(Object paramObject)
  {
    return true;
  }
  
  public boolean q(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = ba().fileList();
    int i1 = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i1 < arrayOfString.length)
      {
        if (arrayOfString[i1].equals(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i1 += 1;
    }
  }
  
  public int r(String paramString)
  {
    return (int)new File(ba().getFilesDir(), paramString).length();
  }
  
  public boolean r(Object paramObject)
  {
    return Build.VERSION.SDK_INT > 11;
  }
  
  public Object s(Object paramObject)
  {
    return new b(this, new Canvas((Bitmap)paramObject), true);
  }
  
  public void s(String paramString)
  {
    new File(I(paramString)).mkdir();
  }
  
  public int t(Object paramObject)
  {
    if (paramObject == null) {}
    for (paramObject = this.d;; paramObject = (Paint)((h)paramObject).d) {
      return -Math.round(paramObject.getFontMetrics().ascent);
    }
  }
  
  public void t()
  {
    if (this.c != null) {
      this.c.b();
    }
  }
  
  public void t(String paramString)
  {
    new File(I(paramString)).delete();
  }
  
  public int u(Object paramObject)
  {
    if (paramObject == null) {}
    for (paramObject = this.d;; paramObject = (h)((h)paramObject).d)
    {
      if (paramObject.a < 0) {
        paramObject.a = paramObject.getFontMetricsInt(paramObject.getFontMetricsInt());
      }
      return paramObject.a;
    }
  }
  
  public long u(String paramString)
  {
    return new File(I(paramString)).length();
  }
  
  public int v(Object paramObject)
  {
    if (paramObject == null) {
      return 0;
    }
    return ((h)paramObject).a;
  }
  
  public boolean v(String paramString)
  {
    return new File(I(paramString)).exists();
  }
  
  public int w(Object paramObject)
  {
    if (paramObject == null) {
      return 0;
    }
    return ((h)paramObject).c;
  }
  
  public int w(String paramString)
  {
    if (this.x == null) {
      av();
    }
    paramString = (NetworkInfo)this.x.get(paramString);
    if (paramString == null) {
      return 1;
    }
    int i1 = paramString.getType();
    int i2 = paramString.getSubtype();
    if (i1 == 1) {
      return 2;
    }
    if (i1 == 0)
    {
      switch (i2)
      {
      default: 
        return 5;
      case 7: 
        return 5;
      case 4: 
        return 5;
      case 2: 
        return 5;
      case 5: 
        return 4;
      case 6: 
        return 4;
      case 1: 
        return 5;
      case 8: 
        return 4;
      case 10: 
        return 4;
      case 9: 
        return 4;
      case 3: 
        return 4;
      case 14: 
        return 4;
      case 12: 
        return 4;
      case 15: 
        return 4;
      case 11: 
        return 5;
      }
      return 4;
    }
    return 1;
  }
  
  public boolean w()
  {
    return true;
  }
  
  public int x()
  {
    return 1;
  }
  
  public int x(Object paramObject)
  {
    if (paramObject == null) {
      return 0;
    }
    return ((h)paramObject).b;
  }
  
  public void x(String paramString)
  {
    if (this.x == null) {
      av();
    }
    paramString = (NetworkInfo)this.x.get(paramString);
    if ((paramString == null) || (paramString.isConnectedOrConnecting())) {
      return;
    }
    ((ConnectivityManager)ba().getSystemService("connectivity")).setNetworkPreference(paramString.getType());
  }
  
  public int y()
  {
    return 42082;
  }
  
  public void y(Object paramObject)
  {
    ((b)paramObject).a();
  }
  
  public int z()
  {
    return 42083;
  }
  
  public void z(String paramString)
  {
    Log.d(q.c().a("AppName", "CodenameOne"), paramString);
  }
  
  class a
    implements DatePickerDialog.OnDateSetListener, DialogInterface.OnCancelListener, Runnable
  {
    Date a = (Date)paramObject1;
    
    a() {}
    
    public void onCancel(DialogInterface paramDialogInterface)
    {
      this.a = null;
      this.c[0] = true;
      arrayOfBoolean[0] = true;
      try
      {
        notify();
        return;
      }
      finally {}
    }
    
    public void onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
    {
      paramDatePicker = Calendar.getInstance();
      paramDatePicker.set(1, paramInt1);
      paramDatePicker.set(2, paramInt2);
      paramDatePicker.set(5, paramInt3);
      this.a = paramDatePicker.getTime();
      this.c[0] = true;
      try
      {
        notify();
        return;
      }
      finally {}
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 29	com/codename1/impl/android/c$a:c	[Z
      //   4: iconst_0
      //   5: baload
      //   6: ifne +26 -> 32
      //   9: aload_0
      //   10: monitorenter
      //   11: aload_0
      //   12: ldc2_w 65
      //   15: invokevirtual 70	java/lang/Object:wait	(J)V
      //   18: aload_0
      //   19: monitorexit
      //   20: goto -20 -> 0
      //   23: astore_1
      //   24: aload_0
      //   25: monitorexit
      //   26: aload_1
      //   27: athrow
      //   28: astore_1
      //   29: goto -11 -> 18
      //   32: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	33	0	this	a
      //   23	4	1	localObject	Object
      //   28	1	1	localInterruptedException	InterruptedException
      // Exception table:
      //   from	to	target	type
      //   11	18	23	finally
      //   18	20	23	finally
      //   24	26	23	finally
      //   11	18	28	java/lang/InterruptedException
    }
  }
  
  class b
    implements NumberPicker.OnValueChangeListener, Runnable
  {
    int a = -1;
    
    b() {}
    
    public void a()
    {
      this.b[0] = true;
      arrayOfBoolean[0] = true;
      try
      {
        notify();
        return;
      }
      finally {}
    }
    
    public void b()
    {
      arrayOfBoolean[0] = false;
      this.b[0] = true;
      try
      {
        notify();
        return;
      }
      finally {}
    }
    
    public void onValueChange(NumberPicker paramNumberPicker, int paramInt1, int paramInt2)
    {
      this.a = paramInt2;
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 23	com/codename1/impl/android/c$b:b	[Z
      //   4: iconst_0
      //   5: baload
      //   6: ifne +26 -> 32
      //   9: aload_0
      //   10: monitorenter
      //   11: aload_0
      //   12: ldc2_w 41
      //   15: invokevirtual 46	java/lang/Object:wait	(J)V
      //   18: aload_0
      //   19: monitorexit
      //   20: goto -20 -> 0
      //   23: astore_1
      //   24: aload_0
      //   25: monitorexit
      //   26: aload_1
      //   27: athrow
      //   28: astore_1
      //   29: goto -11 -> 18
      //   32: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	33	0	this	b
      //   23	4	1	localObject	Object
      //   28	1	1	localInterruptedException	InterruptedException
      // Exception table:
      //   from	to	target	type
      //   11	18	23	finally
      //   18	20	23	finally
      //   24	26	23	finally
      //   11	18	28	java/lang/InterruptedException
    }
  }
  
  class c
    implements TimePickerDialog.OnTimeSetListener, DialogInterface.OnCancelListener, Runnable
  {
    int a = ((Integer)paramObject1).intValue();
    
    c() {}
    
    public void onCancel(DialogInterface paramDialogInterface)
    {
      this.c[0] = true;
      arrayOfBoolean[0] = true;
      try
      {
        notify();
        return;
      }
      finally {}
    }
    
    public void onTimeSet(TimePicker paramTimePicker, int paramInt1, int paramInt2)
    {
      this.a = (paramInt1 * 60 + paramInt2);
      this.c[0] = true;
      try
      {
        notify();
        return;
      }
      finally {}
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 29	com/codename1/impl/android/c$c:c	[Z
      //   4: iconst_0
      //   5: baload
      //   6: ifne +26 -> 32
      //   9: aload_0
      //   10: monitorenter
      //   11: aload_0
      //   12: ldc2_w 55
      //   15: invokevirtual 60	java/lang/Object:wait	(J)V
      //   18: aload_0
      //   19: monitorexit
      //   20: goto -20 -> 0
      //   23: astore_1
      //   24: aload_0
      //   25: monitorexit
      //   26: aload_1
      //   27: athrow
      //   28: astore_1
      //   29: goto -11 -> 18
      //   32: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	33	0	this	c
      //   23	4	1	localObject	Object
      //   28	1	1	localInterruptedException	InterruptedException
      // Exception table:
      //   from	to	target	type
      //   11	18	23	finally
      //   18	20	23	finally
      //   24	26	23	finally
      //   11	18	28	java/lang/InterruptedException
    }
  }
  
  class d
    extends c.e
  {
    protected AndroidBrowserComponentCallback a;
    private Activity d;
    private WebView e;
    private com.codename1.p.c s;
    private boolean t = true;
    private boolean u = false;
    private ProgressDialog v;
    private boolean w;
    private int x;
    
    public d(WebView paramWebView, Activity paramActivity, Object paramObject)
    {
      super(paramWebView);
      if (!c.a(c.this)) {
        d(false);
      }
      this.s = ((com.codename1.p.c)paramObject);
      this.e = paramWebView;
      this.x = paramWebView.getLayerType();
      paramWebView.getSettings().setJavaScriptEnabled(true);
      paramWebView.getSettings().setSupportZoom(this.s.j());
      this.d = paramActivity;
      this.a = new AndroidBrowserComponentCallback();
      this.w = q.c().a("WebLoadingHidden", "false").equals("true");
      paramWebView.addJavascriptInterface(this.a, "com_codename1_impl_AndroidImplementation_AndroidBrowserComponent");
      paramWebView.addJavascriptInterface(new c.l(c.this, this.s), "cn1application");
      paramWebView.setWebViewClient(new WebViewClient()
      {
        private void a()
        {
          if ((c.d.d(c.d.this) != null) && (c.d.d(c.d.this).isShowing()))
          {
            c.d.d(c.d.this).dismiss();
            q.c().a(new Runnable()
            {
              public void run()
              {
                c.d.this.d_(true);
                c.d.this.aN();
              }
            });
          }
        }
        
        public void onLoadResource(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          int i = 0;
          Object localObject2;
          if (q.c().a("syncNativeCookies", "false").equals("true")) {
            try
            {
              Object localObject1 = new URI(paramAnonymousString);
              localObject2 = c.bk();
              ((CookieManager)localObject2).removeExpiredCookie();
              localObject1 = ((URI)localObject1).getHost();
              c.a(c.this, (String)localObject1);
              localObject2 = ((CookieManager)localObject2).getCookie(paramAnonymousString);
              if (localObject2 != null)
              {
                String[] arrayOfString1 = ((String)localObject2).split(";");
                int j = arrayOfString1.length;
                localObject2 = new ArrayList();
                if (i < j)
                {
                  com.codename1.f.f localF = new com.codename1.f.f();
                  String[] arrayOfString2 = arrayOfString1[i].split("=");
                  localF.a(arrayOfString2[0].trim());
                  if (arrayOfString2.length > 1) {
                    localF.c(arrayOfString2[1].trim());
                  }
                  for (;;)
                  {
                    localF.d((String)localObject1);
                    ((ArrayList)localObject2).add(localF);
                    i += 1;
                    break;
                    localF.c("");
                  }
                }
              }
              else
              {
                c.d.a(c.d.this).a("onLoadResource", new com.codename1.p.b.a(paramAnonymousString));
              }
            }
            catch (URISyntaxException localURISyntaxException) {}
          }
          for (;;)
          {
            super.onLoadResource(paramAnonymousWebView, paramAnonymousString);
            c.d.this.k(true);
            return;
            com.codename1.f.f[] arrayOfF = new com.codename1.f.f[((ArrayList)localObject2).size()];
            ((ArrayList)localObject2).toArray(arrayOfF);
            c.this.a(arrayOfF, false);
          }
        }
        
        public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          c.d.a(c.d.this).a("onLoad", new com.codename1.p.b.a(paramAnonymousString));
          super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
          c.d.this.k(true);
          a();
        }
        
        public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
        {
          if (c.aW() == null) {}
          do
          {
            return;
            c.d.a(c.d.this).a("onStart", new com.codename1.p.b.a(paramAnonymousString));
            super.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
            a();
          } while ((c.d.c(c.d.this)) || (c.this.aI()));
          c.d.a(c.d.this, ProgressDialog.show(c.aW(), null, "Loading..."));
          new Timer().schedule(new TimerTask()
          {
            public void run()
            {
              c.d.1.a(c.d.1.this);
            }
          }, 10000L);
        }
        
        public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
        {
          c.d.a(c.d.this).a("onError", new com.codename1.p.b.a(paramAnonymousString1, paramAnonymousInt));
          super.onReceivedError(paramAnonymousWebView, paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
          super.shouldOverrideKeyEvent(paramAnonymousWebView, null);
          a();
        }
        
        public boolean shouldOverrideKeyEvent(WebView paramAnonymousWebView, KeyEvent paramAnonymousKeyEvent)
        {
          int i = paramAnonymousKeyEvent.getKeyCode();
          if ((i == 4) || (i == 82)) {
            return true;
          }
          return super.shouldOverrideKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
        }
        
        public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          if (paramAnonymousString.startsWith("jar:")) {
            c.d.this.a(paramAnonymousString, null);
          }
          do
          {
            do
            {
              do
              {
                return true;
                if (!paramAnonymousString.startsWith("tel:")) {
                  break;
                }
              } while (!c.d.a(c.d.this).a(paramAnonymousString));
              try
              {
                paramAnonymousWebView = new Intent("android.intent.action.DIAL", Uri.parse(paramAnonymousString));
                c.ba().startActivity(paramAnonymousWebView);
                return true;
              }
              catch (Throwable paramAnonymousWebView)
              {
                return true;
              }
              if (!paramAnonymousString.startsWith("mailto:")) {
                break;
              }
            } while (!c.d.a(c.d.this).a(paramAnonymousString));
            try
            {
              paramAnonymousWebView = new Intent("android.intent.action.SENDTO", Uri.parse(paramAnonymousString));
              c.ba().startActivity(paramAnonymousWebView);
              return true;
            }
            catch (Throwable paramAnonymousWebView)
            {
              return true;
            }
          } while (!c.d.a(c.d.this).a(paramAnonymousString));
          return false;
        }
      });
      paramWebView.setWebChromeClient(new WebChromeClient()
      {
        public boolean onConsoleMessage(ConsoleMessage paramAnonymousConsoleMessage)
        {
          m.a("[" + paramAnonymousConsoleMessage.messageLevel() + "] " + paramAnonymousConsoleMessage.message() + " On line " + paramAnonymousConsoleMessage.lineNumber() + " of " + paramAnonymousConsoleMessage.sourceId());
          return true;
        }
        
        public void onGeolocationPermissionsShowPrompt(String paramAnonymousString, GeolocationPermissions.Callback paramAnonymousCallback)
        {
          paramAnonymousCallback.invoke(paramAnonymousString, true, false);
        }
        
        public void onPermissionRequest(final PermissionRequest paramAnonymousPermissionRequest)
        {
          Log.d("Codename One", "onPermissionRequest");
          c.aW().runOnUiThread(new Runnable()
          {
            @TargetApi(21)
            public void run()
            {
              int k = 0;
              Object localObject1 = q.c().a("android.WebView.grantPermissionsFrom", null);
              int m;
              int i;
              if (localObject1 != null)
              {
                localObject1 = u.b((String)localObject1, " ");
                m = localObject1.length;
                i = 0;
              }
              for (;;)
              {
                int j = k;
                if (i < m)
                {
                  Object localObject2 = localObject1[i];
                  if (paramAnonymousPermissionRequest.getOrigin().toString().equals(localObject2)) {
                    j = 1;
                  }
                }
                else
                {
                  if (j == 0) {
                    break;
                  }
                  Log.d("Codename One", "Allowing permission for " + Arrays.toString(paramAnonymousPermissionRequest.getResources()) + " in web view for origin " + paramAnonymousPermissionRequest.getOrigin());
                  paramAnonymousPermissionRequest.grant(paramAnonymousPermissionRequest.getResources());
                  return;
                }
                i += 1;
              }
              Log.d("Codename One", "Denying permission for " + Arrays.toString(paramAnonymousPermissionRequest.getResources()) + " in web view for origin " + paramAnonymousPermissionRequest.getOrigin());
              paramAnonymousPermissionRequest.deny();
            }
          });
        }
        
        public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
        {
          c.d.a(c.d.this).a("Progress", new com.codename1.p.b.a(c.d.a(c.d.this), a.a.l, paramAnonymousInt));
          if ((!c.d.c(c.d.this)) && (c.this.aI()) && (c.this.D() != null) && (c.this.D().s() != null) && (c.this.D().s().length() > 0) && (c.aW() != null)) {}
          try
          {
            c.aW().setProgressBarVisibility(true);
            c.aW().setProgress(paramAnonymousInt * 100);
            if (paramAnonymousInt == 100) {
              c.aW().setProgressBarVisibility(false);
            }
            return;
          }
          catch (Throwable paramAnonymousWebView) {}
        }
      });
    }
    
    public void a(final String paramString1, final String paramString2)
    {
      this.d.runOnUiThread(new Runnable()
      {
        public void run()
        {
          c.d.b(c.d.this).loadDataWithBaseURL(paramString2, paramString1, "text/html", "UTF-8", null);
        }
      });
    }
    
    public void a(final String paramString, final Map<String, String> paramMap)
    {
      this.d.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (paramMap != null)
          {
            c.d.b(c.d.this).loadUrl(paramString, paramMap);
            return;
          }
          c.d.b(c.d.this).loadUrl(paramString);
        }
      });
    }
    
    protected void c_(boolean paramBoolean)
    {
      if (!paramBoolean) {}
      for (boolean bool = true;; bool = false)
      {
        d(bool);
        if (this.u != paramBoolean) {
          break;
        }
        return;
      }
      this.u = paramBoolean;
    }
    
    protected void e()
    {
      if ((Build.VERSION.SDK_INT == 21) && (this.e.getLayerType() != this.x)) {
        this.d.runOnUiThread(new Runnable()
        {
          public void run()
          {
            c.d.b(c.d.this).setLayerType(c.d.e(c.d.this), null);
          }
        });
      }
      super.e();
      e(false);
      a(null);
    }
    
    protected void f()
    {
      this.d.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (Build.VERSION.SDK_INT == 21) {
            c.d.b(c.d.this).setLayerType(1, null);
          }
        }
      });
      super.f();
    }
    
    protected x h()
    {
      try
      {
        final Bitmap localBitmap = Bitmap.createBitmap(X(), Y(), Bitmap.Config.ARGB_8888);
        c.i localI = new c.i(c.this, localBitmap);
        c.aW().runOnUiThread(new Runnable()
        {
          public void run()
          {
            try
            {
              Canvas localCanvas = new Canvas(localBitmap);
              c.d.b(c.d.this).draw(localCanvas);
              return;
            }
            catch (Throwable localThrowable)
            {
              localThrowable.printStackTrace();
            }
          }
        });
        return localI;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
      return x.d(5, 5);
    }
    
    public void j()
    {
      this.d.runOnUiThread(new Runnable()
      {
        public void run()
        {
          c.d.b(c.d.this).stopLoading();
        }
      });
    }
    
    protected boolean m_()
    {
      return (this.u) || (!bs());
    }
  }
  
  class e
    extends ag
  {
    private View a;
    private c.f b = null;
    private int d = 4;
    private boolean e;
    private x s;
    
    public e(View paramView)
    {
      super();
      this.a = paramView;
      if (!c.a(c.this)) {
        this.a.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
      }
    }
    
    private void g(final boolean paramBoolean)
    {
      if (c.aW() == null) {
        return;
      }
      c.aW().runOnUiThread(new Runnable()
      {
        public void run()
        {
          c.e localE = c.e.this;
          if (paramBoolean) {}
          for (int i = 0;; i = 4)
          {
            c.e.a(localE, i);
            c.e.b(c.e.this).setVisibility(c.e.a(c.e.this));
            if (paramBoolean) {
              c.e.b(c.e.this).bringToFront();
            }
            return;
          }
        }
      });
    }
    
    protected com.codename1.p.c.a a()
    {
      int i = 1;
      Object localObject = this.a.getBackground();
      int j;
      if (localObject != null)
      {
        j = ((Drawable)localObject).getMinimumWidth();
        i = ((Drawable)localObject).getMinimumHeight();
      }
      for (;;)
      {
        j = Math.max(this.a.getMeasuredWidth(), j);
        i = Math.max(this.a.getMeasuredHeight(), i);
        if ((this.a instanceof TextView))
        {
          localObject = (TextView)this.a;
          j = (int)Layout.getDesiredWidth(((TextView)this.a).getText(), ((TextView)this.a).getPaint());
          ((TextView)localObject).measure(j, View.MeasureSpec.makeMeasureSpec(0, 0));
          i = Math.max(i, ((TextView)localObject).getMeasuredHeight());
        }
        for (;;)
        {
          return new com.codename1.p.c.a(j, i);
        }
        j = 1;
      }
    }
    
    public void a(w paramW)
    {
      if (c.a(c.this))
      {
        Object localObject2 = com.codename1.p.a.a(paramW);
        Object localObject1 = this.a.getLayoutParams();
        if ((localObject1 instanceof a.c))
        {
          localObject1 = (a.c)localObject1;
          if (localObject1 == null)
          {
            localObject1 = new a.c(S() + paramW.b(), T() + paramW.c(), X(), Y(), this);
            c.f.runOnUiThread(new Runnable()
            {
              public void run()
              {
                c.e.b(c.e.this).setLayoutParams(this.a);
              }
            });
            ((a.c)localObject1).f = true;
            if (localObject2.getClass() != b.class) {
              break label311;
            }
            if (this.s == null) {
              this.s = h();
            }
            paramW.a(this.s, S(), T());
          }
        }
        for (;;)
        {
          return;
          int i = S() + paramW.b();
          int j = T() + paramW.c();
          int k = X();
          int m = Y();
          if ((i != ((a.c)localObject1).b) || (j != ((a.c)localObject1).c) || (k != ((a.c)localObject1).d) || (m != ((a.c)localObject1).e))
          {
            ((a.c)localObject1).f = true;
            ((a.c)localObject1).b = i;
            ((a.c)localObject1).c = j;
            ((a.c)localObject1).d = k;
            ((a.c)localObject1).e = m;
          }
          break;
          localObject1 = new a.c(S() + paramW.b(), T() + paramW.c(), X(), Y(), this);
          c.f.runOnUiThread(new Runnable()
          {
            public void run()
            {
              c.e.b(c.e.this).setLayoutParams(this.a);
            }
          });
          ((a.c)localObject1).f = true;
          break;
          synchronized (c.l)
          {
            label311:
            c.l.put(this.a, this);
            ((b)localObject2).a(this.a, (a.c)localObject1);
            if ((this.e) && (this.s != null))
            {
              paramW.a(this.s, S(), T(), X(), Y());
              return;
            }
          }
        }
      }
      super.a(paramW);
    }
    
    protected void c_(boolean paramBoolean)
    {
      if (c.a(c.this))
      {
        if (paramBoolean != this.e)
        {
          this.e = paramBoolean;
          if (this.e)
          {
            x localX = h();
            if (localX != null) {
              this.s = localX;
            }
          }
        }
        return;
      }
      if (!paramBoolean) {}
      for (boolean bool = true;; bool = false)
      {
        d(bool);
        if (this.e == paramBoolean) {
          break;
        }
        this.e = paramBoolean;
        return;
      }
    }
    
    void d(final boolean paramBoolean)
    {
      if (c.aW() == null) {}
      do
      {
        return;
        c.aW().runOnUiThread(new Runnable()
        {
          public void run()
          {
            c.e localE = c.e.this;
            if (paramBoolean) {}
            for (int i = 0;; i = 4)
            {
              c.e.a(localE, i);
              c.e.b(c.e.this).setVisibility(c.e.a(c.e.this));
              if (paramBoolean) {
                c.e.b(c.e.this).bringToFront();
              }
              return;
            }
          }
        });
      } while (!paramBoolean);
      n_();
    }
    
    public void d_(boolean paramBoolean)
    {
      super.d_(paramBoolean);
      d(paramBoolean);
    }
    
    protected void e()
    {
      super.e();
      if (!c.a(c.this)) {}
      synchronized (c.this.h)
      {
        c.this.h.add(this);
        l();
        a(null);
        return;
      }
    }
    
    void e(boolean paramBoolean)
    {
      c.f localF;
      if (this.b != null)
      {
        localF = this.b;
        if (!paramBoolean) {
          break label26;
        }
      }
      label26:
      for (int i = 393216;; i = 262144)
      {
        localF.setDescendantFocusability(i);
        return;
      }
    }
    
    protected void f()
    {
      if (!c.a(c.this))
      {
        a(h());
        super.f();
        synchronized (c.this.h)
        {
          c.this.h.remove(this);
          k();
          return;
        }
      }
      ??? = h();
      if (??? != null) {
        this.s = ((x)???);
      }
      if ((c.this.c instanceof a)) {
        ((a)c.this.c).a(this.a);
      }
      super.f();
    }
    
    public void f(final boolean paramBoolean)
    {
      super.f(paramBoolean);
      if (c.aW() == null) {
        return;
      }
      c.aW().runOnUiThread(new Runnable()
      {
        public void run()
        {
          c.e.b(c.e.this).setFocusable(paramBoolean);
        }
      });
    }
    
    protected x h()
    {
      try
      {
        Object localObject = AndroidNativeUtil.renderViewOnBitmap(this.a, X(), Y());
        if (localObject == null) {
          return x.d(5, 5);
        }
        localObject = new c.i(c.this, (Bitmap)localObject);
        return localObject;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
      return x.d(5, 5);
    }
    
    public void k()
    {
      if (c.aW() == null) {}
      for (;;)
      {
        return;
        if (this.s == null) {
          this.s = h();
        }
        final boolean[] arrayOfBoolean = new boolean[1];
        c.aW().runOnUiThread(new Runnable()
        {
          public void run()
          {
            try
            {
              if ((c.e.c(c.e.this) != null) && (c.this.g != null))
              {
                c.this.g.removeView(c.e.c(c.e.this));
                c.this.g.requestLayout();
                c.e.a(c.e.this, null);
              }
              return;
            }
            finally
            {
              arrayOfBoolean[0] = true;
            }
          }
        });
        while (arrayOfBoolean[0] == 0) {
          q.c().e(new Runnable()
          {
            public void run()
            {
              if (arrayOfBoolean[0] == 0) {}
              try
              {
                Thread.sleep(5L);
                return;
              }
              catch (InterruptedException localInterruptedException) {}
            }
          });
        }
      }
    }
    
    public void l()
    {
      if ((c.a(c.this)) || (c.aW() == null)) {
        return;
      }
      c.a(new Runnable()
      {
        public void run()
        {
          if (c.e.c(c.e.this) == null)
          {
            c.e.a(c.e.this, new c.f(c.this, c.f, c.e.this, c.e.b(c.e.this)));
            c.e.c(c.e.this).setBackgroundDrawable(null);
            c.e.b(c.e.this).setVisibility(c.e.a(c.e.this));
            c.e.b(c.e.this).setFocusable(c.e.this.p());
            c.e.b(c.e.this).setFocusableInTouchMode(true);
            ArrayList localArrayList = new ArrayList();
            localArrayList.add(c.e.c(c.e.this));
            c.e.b(c.e.this).addFocusables(localArrayList, 130);
            c.e.b(c.e.this).addFocusables(localArrayList, 33);
            c.e.b(c.e.this).addFocusables(localArrayList, 17);
            c.e.b(c.e.this).addFocusables(localArrayList, 66);
            if ((c.e.b(c.e.this).isFocusable()) || (c.e.b(c.e.this).isFocusableInTouchMode()))
            {
              if (!c.e.d(c.e.this)) {
                break label378;
              }
              c.a(c.this, true);
              c.e.this.e(false);
              if (!c.e.b(c.e.this).hasFocus()) {
                c.e.b(c.e.this).requestFocus();
              }
            }
          }
          for (;;)
          {
            c.e.c(c.e.this).setOnKeyListener(new View.OnKeyListener()
            {
              public boolean onKey(View paramAnonymous2View, int paramAnonymous2Int, KeyEvent paramAnonymous2KeyEvent)
              {
                c.this.i = i.a(paramAnonymous2KeyEvent.getKeyCode());
                if (c.this.c == null) {
                  return false;
                }
                c.this.c.getAndroidView().requestFocus();
                return true;
              }
            });
            c.e.c(c.e.this).setOnFocusChangeListener(new View.OnFocusChangeListener()
            {
              public void onFocusChange(View paramAnonymous2View, boolean paramAnonymous2Boolean)
              {
                Log.d("Codename One", "on focus change. " + paramAnonymous2View.toString() + " focus:" + paramAnonymous2Boolean + " touchmode: " + c.e.b(c.e.this).isInTouchMode());
              }
            });
            c.e.c(c.e.this).setOnTouchListener(new View.OnTouchListener()
            {
              public boolean onTouch(View paramAnonymous2View, MotionEvent paramAnonymous2MotionEvent)
              {
                if (c.this.c == null) {
                  return false;
                }
                return c.this.c.getAndroidView().onTouchEvent(paramAnonymous2MotionEvent);
              }
            });
            if (c.this.g != null)
            {
              if (c.e.c(c.e.this).getParent() != null) {
                ((ViewGroup)c.e.c(c.e.this).getParent()).removeView(c.e.c(c.e.this));
              }
              c.this.g.addView(c.e.c(c.e.this));
            }
            return;
            label378:
            c.e.this.e(true);
          }
        }
      });
    }
    
    boolean m()
    {
      return bs();
    }
    
    protected boolean m_()
    {
      return (!c.a(c.this)) && ((this.e) || (!bs()));
    }
    
    protected void n()
    {
      if (!c.a(c.this))
      {
        v localV = aM();
        if ((this.a.getVisibility() == 4) && (localV != null) && (q.c().z() == localV)) {
          g(true);
        }
      }
      else
      {
        return;
      }
      n_();
    }
    
    protected void n_()
    {
      if (c.aW() == null) {}
      while (c.a(c.this)) {
        return;
      }
      c.f.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if ((c.e.c(c.e.this) != null) && (c.e.b(c.e.this).getVisibility() == 0))
          {
            RelativeLayout.LayoutParams localLayoutParams = c.f.a(c.e.c(c.e.this), c.e.this.at(), c.e.this.au(), c.e.this.X(), c.e.this.Y());
            c.e.c(c.e.this).setLayoutParams(localLayoutParams);
            if (c.this.g != null) {
              c.this.g.requestLayout();
            }
          }
        }
      });
    }
    
    public boolean p()
    {
      if (this.a != null) {
        return (this.a.isFocusableInTouchMode()) || (this.a.isFocusable());
      }
      return super.p();
    }
    
    protected void q()
    {
      Log.d("Codename One", "native focus gain");
      super.q();
      if (c.aW() == null) {
        return;
      }
      c.aW().runOnUiThread(new Runnable()
      {
        public void run()
        {
          c.e.this.e(false);
          if (!c.e.b(c.e.this).hasFocus())
          {
            if (c.e.b(c.e.this).isInTouchMode()) {
              c.e.b(c.e.this).requestFocusFromTouch();
            }
          }
          else {
            return;
          }
          c.e.b(c.e.this).requestFocus();
        }
      });
    }
    
    protected void r()
    {
      Log.d("Codename One", "native focus loss");
      super.r();
      if ((this.b != null) && (c.aW() != null)) {
        c.aW().runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (c.e.e(c.e.this)) {
              c.e.c(c.e.this).requestFocus();
            }
          }
        });
      }
    }
  }
  
  class f
    extends RelativeLayout
  {
    private c.e b;
    
    public f(Context paramContext, c.e paramE, View paramView)
    {
      super();
      this.b = paramE;
      setLayoutParams(a(paramE.at(), paramE.au(), paramE.X(), paramE.Y()));
      if (paramView.getParent() != null) {
        ((ViewGroup)paramView.getParent()).removeView(paramView);
      }
      addView(paramView, new RelativeLayout.LayoutParams(-1, -1));
      setDrawingCacheEnabled(false);
      setAlwaysDrawnWithCacheEnabled(false);
      setFocusable(true);
      setFocusableInTouchMode(false);
      setDescendantFocusability(262144);
    }
    
    private RelativeLayout.LayoutParams a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams.addRule(9);
      localLayoutParams.addRule(10);
      localLayoutParams.width = paramInt3;
      localLayoutParams.height = paramInt4;
      localLayoutParams.leftMargin = paramInt1;
      localLayoutParams.topMargin = paramInt2;
      return localLayoutParams;
    }
    
    public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
    {
      int i = i.a(paramKeyEvent.getKeyCode());
      if (i == 42084)
      {
        switch (paramKeyEvent.getAction())
        {
        }
        for (;;)
        {
          return true;
          q.c().f(i);
          continue;
          q.c().g(i);
        }
      }
      return super.dispatchKeyEvent(paramKeyEvent);
    }
  }
  
  private static class g
    implements Runnable
  {
    private Activity a;
    
    public g(Activity paramActivity)
    {
      this.a = paramActivity;
    }
    
    public void run()
    {
      this.a.invalidateOptionsMenu();
    }
  }
  
  public static class h
  {
    int a;
    int b;
    int c;
    public Object d;
    String e;
    float f;
    int g;
    
    public h(int paramInt1, int paramInt2, int paramInt3, Object paramObject)
    {
      this.a = paramInt1;
      this.b = paramInt2;
      this.c = paramInt3;
      this.d = paramObject;
    }
    
    public h(int paramInt1, int paramInt2, int paramInt3, Object paramObject, String paramString, float paramFloat, int paramInt4)
    {
      this(paramInt1, paramInt2, paramInt3, paramObject);
      this.e = paramString;
      this.f = paramFloat;
      this.g = paramInt4;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (paramObject == null) {
        bool = false;
      }
      do
      {
        do
        {
          return bool;
          paramObject = (h)paramObject;
          if (this.e == null) {
            break;
          }
        } while ((paramObject.e != null) && (this.e.equals(paramObject.e)) && (paramObject.f == this.f) && (paramObject.g == this.g));
        return false;
      } while ((paramObject.a == this.a) && (paramObject.b == this.b) && (paramObject.c == this.c) && (this.d.equals(paramObject.d)));
      return false;
    }
    
    public int hashCode()
    {
      return this.a | this.b | this.c;
    }
  }
  
  class i
    extends x
  {
    public i(Bitmap paramBitmap)
    {
      super();
    }
  }
  
  private static class j
    implements Runnable
  {
    private Activity a;
    private boolean b;
    
    public j(Activity paramActivity, boolean paramBoolean)
    {
      this.a = paramActivity;
      this.b = paramBoolean;
    }
    
    public void run()
    {
      this.a.invalidateOptionsMenu();
      if (this.b)
      {
        this.a.getActionBar().show();
        return;
      }
      this.a.getActionBar().hide();
    }
  }
  
  private static class k
    implements Runnable
  {
    private Activity a;
    private v b;
    
    public k(Activity paramActivity, v paramV)
    {
      this.a = paramActivity;
      this.b = paramV;
    }
    
    public void run()
    {
      if (at.n()) {
        return;
      }
      ActionBar localActionBar = this.a.getActionBar();
      Object localObject = this.b.s();
      if (Build.VERSION.SDK_INT >= 14) {}
      for (;;)
      {
        try
        {
          ViewConfiguration localViewConfiguration = ViewConfiguration.get(this.a);
          bool = ((Boolean)localViewConfiguration.getClass().getMethod("hasPermanentMenuKey", (Class[])null).invoke(localViewConfiguration, (Object[])null)).booleanValue();
          if (((localObject == null) || (((String)localObject).length() <= 0)) && ((this.b.cT() <= 0) || (bool))) {
            break label247;
          }
          this.a.runOnUiThread(new c.j(this.a, true));
          localActionBar.setTitle((CharSequence)localObject);
          if (this.b.cE() == null) {
            break label267;
          }
          bool = true;
          localActionBar.setDisplayHomeAsUpEnabled(bool);
          if (Build.VERSION.SDK_INT < 14) {
            break;
          }
          localObject = this.b.bU().bQ();
          if (localObject == null) {
            break label272;
          }
          try
          {
            localActionBar.getClass().getMethod("setIcon", new Class[] { Drawable.class }).invoke(localActionBar, new Object[] { new BitmapDrawable(this.a.getResources(), (Bitmap)((x)localObject).c()) });
            this.a.runOnUiThread(new c.g(this.a));
            return;
          }
          catch (Throwable localThrowable1)
          {
            localThrowable1.printStackTrace();
            return;
          }
          bool = false;
        }
        catch (Throwable localThrowable2)
        {
          localThrowable2.printStackTrace();
        }
        continue;
        label247:
        this.a.runOnUiThread(new c.j(this.a, false));
        return;
        label267:
        boolean bool = false;
        continue;
        label272:
        if (this.a.getApplicationInfo().icon != 0) {
          localThrowable1.getClass().getMethod("setIcon", new Class[] { Integer.TYPE }).invoke(localThrowable1, new Object[] { Integer.valueOf(this.a.getApplicationInfo().icon) });
        }
      }
    }
  }
  
  public class l
  {
    com.codename1.p.c a;
    
    l(com.codename1.p.c paramC)
    {
      this.a = paramC;
    }
    
    @JavascriptInterface
    public boolean shouldNavigate(String paramString)
    {
      return this.a.a(paramString);
    }
  }
}
