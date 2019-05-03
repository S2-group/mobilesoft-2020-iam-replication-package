package com.sjetayupdpuwdr;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.FloatMath;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

public class AdController
{
  public static final String A = "LBAdController";
  public static final String H = "normal";
  public static final String fA = "fullscreen";
  public static final String hA = "exit";
  protected Context XA;
  protected AdView nA;
  
  public AdController(Context paramContext, String paramString)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
  }
  
  public AdController(Context paramContext, String paramString, WebView paramWebView)
  {
    this.XA = paramContext;
    this.S = paramString;
    this.jA = paramWebView;
    this.b = new RelativeLayout(this.XA);
    A();
  }
  
  public AdController(Context paramContext, String paramString, RelativeLayout paramRelativeLayout)
  {
    this.XA = paramContext;
    this.S = paramString;
    paramContext = paramRelativeLayout;
    if (paramRelativeLayout == null) {
      paramContext = new RelativeLayout(this.XA);
    }
    this.b = paramContext;
    this.jA = null;
    A();
  }
  
  public AdController(Context paramContext, String paramString, AdAudioListener paramAdAudioListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.g = paramAdAudioListener;
  }
  
  public AdController(Context paramContext, String paramString, AdListener paramAdListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.w = paramAdListener;
  }
  
  public AdController(AdView paramAdView, Context paramContext)
  {
    this.nA = paramAdView;
    this.XA = paramContext;
  }
  
  protected static Object M(JSONObject paramJSONObject, Class<?> paramClass)
    throws IllegalAccessException, InstantiationException, NumberFormatException, NullPointerException
  {
    Field[] arrayOfField = paramClass.getDeclaredFields();
    paramClass = paramClass.newInstance();
    int i3 = arrayOfField.length;
    int i1 = 0;
    int i2 = 0;
    String str2;
    if (i1 < i3)
    {
      Field localField = arrayOfField[i2];
      String str1 = localField.getName().replace('_', '-');
      str2 = localField.getType().toString();
      for (;;)
      {
        try
        {
          if (!str2.equals("int")) {
            continue;
          }
          str1 = paramJSONObject.getString(str1).toLowerCase();
          boolean bool = str1.startsWith(AdJSInterface.M("G"));
          if (!bool) {
            continue;
          }
        }
        catch (JSONException localJSONException)
        {
          AdLog.printStackTrace("LBAdController", localJSONException);
          continue;
          if (!str2.equals("boolean")) {
            continue;
          }
          localJSONException.set(paramClass, Boolean.valueOf(paramJSONObject.getBoolean(localNumberFormatException)));
          continue;
          if (!str2.equals("float")) {
            continue;
          }
          localJSONException.set(paramClass, Float.valueOf(Float.parseFloat(paramJSONObject.getString(localNumberFormatException))));
          continue;
          if (!str2.equals("class com.sjetayupdpuwdr.AdNavigationStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdNavigationStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
          if (!str2.equals("class com.sjetayupdpuwdr.AdTransitionStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdTransitionStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
        }
        try
        {
          if (str1.startsWith(AdRequest.M("7Il")))
          {
            i1 = Integer.decode(str1.substring(1)).intValue();
            localField.set(paramClass, Integer.valueOf(i1));
            i1 = i2 + 1;
            i2 = i1;
            break;
          }
          i1 = Integer.parseInt(str1.substring(1), 16);
          continue;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          AdLog.printStackTrace("LBAdController", localNumberFormatException);
          i1 = -1;
          continue;
        }
        i1 = Integer.parseInt(localNumberFormatException);
        continue;
        if (!str2.equals("class java.lang.String")) {
          continue;
        }
        localField.set(paramClass, paramJSONObject.getString(localNumberFormatException));
      }
    }
    return paramClass;
  }
  
  public static String M(String paramString)
  {
    int i1 = paramString.length();
    char[] arrayOfChar = new char[i1];
    int i2 = i1 - 1;
    for (i1 = i2; i2 >= 0; i1 = i2)
    {
      i2 = paramString.charAt(i1);
      int i3 = i1 - 1;
      arrayOfChar[i1] = ((char)(i2 ^ 0x5A));
      if (i3 < 0) {
        break;
      }
      i2 = i3 - 1;
      arrayOfChar[i3] = ((char)(paramString.charAt(i3) ^ 0x38));
    }
    return new String(arrayOfChar);
  }
  
  /* Error */
  public void adInitialized()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield 238	com/sjetayupdpuwdr/AdController:B	Z
    //   7: aload_0
    //   8: getfield 289	com/sjetayupdpuwdr/AdController:XA	Landroid/content/Context;
    //   11: ldc_w 471
    //   14: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   17: iconst_0
    //   18: invokevirtual 320	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   21: astore 4
    //   23: aload 4
    //   25: invokeinterface 475 1 0
    //   30: astore 5
    //   32: aload_0
    //   33: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   36: ifnull +1085 -> 1121
    //   39: aload 5
    //   41: ldc_w 1398
    //   44: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   47: aload_0
    //   48: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   51: ldc_w 1400
    //   54: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   57: invokevirtual 1403	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   60: invokeinterface 1407 4 0
    //   65: pop
    //   66: aload 5
    //   68: invokestatic 489	com/sjetayupdpuwdr/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   71: aload_0
    //   72: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   75: ldc_w 570
    //   78: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   81: invokevirtual 514	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   84: ldc_w 1409
    //   87: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   90: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   93: ifeq +53 -> 146
    //   96: aload_0
    //   97: getfield 260	com/sjetayupdpuwdr/AdController:w	Lcom/sjetayupdpuwdr/AdListener;
    //   100: ifnull +17 -> 117
    //   103: aload_0
    //   104: iconst_1
    //   105: putfield 246	com/sjetayupdpuwdr/AdController:s	Z
    //   108: aload_0
    //   109: getfield 260	com/sjetayupdpuwdr/AdController:w	Lcom/sjetayupdpuwdr/AdListener;
    //   112: invokeinterface 865 1 0
    //   117: aload_0
    //   118: getfield 300	com/sjetayupdpuwdr/AdController:g	Lcom/sjetayupdpuwdr/AdAudioListener;
    //   121: ifnull +17 -> 138
    //   124: aload_0
    //   125: iconst_1
    //   126: putfield 276	com/sjetayupdpuwdr/AdController:LA	Z
    //   129: aload_0
    //   130: getfield 300	com/sjetayupdpuwdr/AdController:g	Lcom/sjetayupdpuwdr/AdAudioListener;
    //   133: invokeinterface 868 1 0
    //   138: return
    //   139: astore 6
    //   141: goto -70 -> 71
    //   144: astore 6
    //   146: aload_0
    //   147: getfield 289	com/sjetayupdpuwdr/AdController:XA	Landroid/content/Context;
    //   150: ifnull +194 -> 344
    //   153: aload_0
    //   154: getfield 289	com/sjetayupdpuwdr/AdController:XA	Landroid/content/Context;
    //   157: instanceof 838
    //   160: ifeq +184 -> 344
    //   163: new 1411	android/util/DisplayMetrics
    //   166: dup
    //   167: invokespecial 1412	android/util/DisplayMetrics:<init>	()V
    //   170: astore 6
    //   172: aload_0
    //   173: getfield 289	com/sjetayupdpuwdr/AdController:XA	Landroid/content/Context;
    //   176: checkcast 838	android/app/Activity
    //   179: invokevirtual 1416	android/app/Activity:getWindowManager	()Landroid/view/WindowManager;
    //   182: invokeinterface 1422 1 0
    //   187: aload 6
    //   189: invokevirtual 1428	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   192: new 1430	android/graphics/Rect
    //   195: dup
    //   196: invokespecial 1431	android/graphics/Rect:<init>	()V
    //   199: astore 7
    //   201: aload_0
    //   202: getfield 289	com/sjetayupdpuwdr/AdController:XA	Landroid/content/Context;
    //   205: checkcast 838	android/app/Activity
    //   208: invokevirtual 1435	android/app/Activity:getWindow	()Landroid/view/Window;
    //   211: astore 8
    //   213: aload 8
    //   215: invokevirtual 1441	android/view/Window:getDecorView	()Landroid/view/View;
    //   218: aload 7
    //   220: invokevirtual 1445	android/view/View:getWindowVisibleDisplayFrame	(Landroid/graphics/Rect;)V
    //   223: aload 7
    //   225: getfield 1448	android/graphics/Rect:top	I
    //   228: istore_3
    //   229: aload 8
    //   231: ldc_w 1280
    //   234: invokevirtual 1449	android/view/Window:findViewById	(I)Landroid/view/View;
    //   237: invokevirtual 1452	android/view/View:getTop	()I
    //   240: istore_1
    //   241: iload_1
    //   242: iload_3
    //   243: if_icmple +333 -> 576
    //   246: iload_1
    //   247: iload_3
    //   248: isub
    //   249: istore_1
    //   250: aload_0
    //   251: aload 6
    //   253: getfield 1455	android/util/DisplayMetrics:widthPixels	I
    //   256: putfield 834	com/sjetayupdpuwdr/AdController:X	I
    //   259: aload_0
    //   260: aload 6
    //   262: getfield 1458	android/util/DisplayMetrics:heightPixels	I
    //   265: iload_3
    //   266: isub
    //   267: iload_1
    //   268: isub
    //   269: putfield 836	com/sjetayupdpuwdr/AdController:ZA	I
    //   272: ldc 71
    //   274: new 398	java/lang/StringBuilder
    //   277: dup
    //   278: invokespecial 399	java/lang/StringBuilder:<init>	()V
    //   281: iconst_0
    //   282: ldc_w 1460
    //   285: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   288: invokevirtual 405	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   291: aload 6
    //   293: getfield 1458	android/util/DisplayMetrics:heightPixels	I
    //   296: invokevirtual 681	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   299: ldc_w 1462
    //   302: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   305: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: iload_3
    //   309: invokevirtual 681	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   312: ldc_w 1464
    //   315: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   318: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: iconst_0
    //   322: invokevirtual 681	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   325: ldc_w 1462
    //   328: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   331: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: iload_1
    //   335: invokevirtual 681	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   338: invokevirtual 419	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: invokestatic 502	com/sjetayupdpuwdr/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   344: aload_0
    //   345: aload_0
    //   346: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   349: ldc_w 1466
    //   352: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   355: invokevirtual 664	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   358: putfield 252	com/sjetayupdpuwdr/AdController:Z	I
    //   361: aload_0
    //   362: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   365: ldc_w 1468
    //   368: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   371: invokevirtual 664	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   374: istore_1
    //   375: aload_0
    //   376: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   379: ldc_w 1470
    //   382: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   385: invokevirtual 664	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   388: istore_3
    //   389: iload_3
    //   390: ifle +191 -> 581
    //   393: aload_0
    //   394: iload_1
    //   395: bipush 60
    //   397: imul
    //   398: iload_3
    //   399: idiv
    //   400: putfield 254	com/sjetayupdpuwdr/AdController:c	I
    //   403: aload_0
    //   404: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   407: ldc_w 1472
    //   410: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   413: invokevirtual 574	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   416: ldc_w 553
    //   419: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   422: invokevirtual 577	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   425: ifeq +8 -> 433
    //   428: aload_0
    //   429: iconst_1
    //   430: putfield 236	com/sjetayupdpuwdr/AdController:P	Z
    //   433: aload 5
    //   435: new 398	java/lang/StringBuilder
    //   438: dup
    //   439: invokespecial 399	java/lang/StringBuilder:<init>	()V
    //   442: iconst_0
    //   443: ldc_w 1474
    //   446: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   449: invokevirtual 405	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   452: aload_0
    //   453: getfield 291	com/sjetayupdpuwdr/AdController:S	Ljava/lang/String;
    //   456: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: invokevirtual 419	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   462: aload_0
    //   463: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   466: ldc_w 1476
    //   469: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   472: invokevirtual 514	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   475: invokeinterface 1480 3 0
    //   480: pop
    //   481: aload 5
    //   483: invokestatic 489	com/sjetayupdpuwdr/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   486: aload_0
    //   487: getfield 274	com/sjetayupdpuwdr/AdController:n	Z
    //   490: ifeq +229 -> 719
    //   493: aload_0
    //   494: invokevirtual 1483	com/sjetayupdpuwdr/AdController:retrieveAudioAd	()V
    //   497: aload 4
    //   499: new 398	java/lang/StringBuilder
    //   502: dup
    //   503: invokespecial 399	java/lang/StringBuilder:<init>	()V
    //   506: iconst_0
    //   507: ldc_w 1485
    //   510: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   513: invokevirtual 405	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   516: aload_0
    //   517: getfield 291	com/sjetayupdpuwdr/AdController:S	Ljava/lang/String;
    //   520: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: invokevirtual 419	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   526: iconst_0
    //   527: invokeinterface 357 3 0
    //   532: ifeq -394 -> 138
    //   535: aload_0
    //   536: getfield 461	com/sjetayupdpuwdr/AdController:PA	Landroid/os/Handler;
    //   539: ifnull -401 -> 138
    //   542: aload_0
    //   543: getfield 461	com/sjetayupdpuwdr/AdController:PA	Landroid/os/Handler;
    //   546: aload_0
    //   547: getfield 463	com/sjetayupdpuwdr/AdController:p	Ljava/lang/Runnable;
    //   550: aload_0
    //   551: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   554: ldc_w 1487
    //   557: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   560: invokevirtual 664	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   563: sipush 1000
    //   566: imul
    //   567: i2l
    //   568: invokevirtual 1491	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   571: pop
    //   572: return
    //   573: astore 4
    //   575: return
    //   576: iconst_0
    //   577: istore_1
    //   578: goto -328 -> 250
    //   581: aload_0
    //   582: bipush 10
    //   584: putfield 254	com/sjetayupdpuwdr/AdController:c	I
    //   587: goto -184 -> 403
    //   590: astore 6
    //   592: aload_0
    //   593: sipush 500
    //   596: putfield 252	com/sjetayupdpuwdr/AdController:Z	I
    //   599: aload_0
    //   600: bipush 10
    //   602: putfield 254	com/sjetayupdpuwdr/AdController:c	I
    //   605: goto -202 -> 403
    //   608: astore 6
    //   610: aload_0
    //   611: getfield 1493	com/sjetayupdpuwdr/AdController:j	Ljava/lang/String;
    //   614: ifnull +61 -> 675
    //   617: aload_0
    //   618: getfield 1493	com/sjetayupdpuwdr/AdController:j	Ljava/lang/String;
    //   621: ldc_w 1409
    //   624: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   627: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   630: ifne +45 -> 675
    //   633: aload 5
    //   635: new 398	java/lang/StringBuilder
    //   638: dup
    //   639: invokespecial 399	java/lang/StringBuilder:<init>	()V
    //   642: iconst_0
    //   643: ldc_w 1495
    //   646: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   649: invokevirtual 405	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   652: aload_0
    //   653: getfield 291	com/sjetayupdpuwdr/AdController:S	Ljava/lang/String;
    //   656: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   659: invokevirtual 419	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   662: aload_0
    //   663: getfield 1493	com/sjetayupdpuwdr/AdController:j	Ljava/lang/String;
    //   666: invokeinterface 1480 3 0
    //   671: pop
    //   672: goto -191 -> 481
    //   675: aload 5
    //   677: new 398	java/lang/StringBuilder
    //   680: dup
    //   681: invokespecial 399	java/lang/StringBuilder:<init>	()V
    //   684: iconst_0
    //   685: ldc_w 1474
    //   688: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   691: invokevirtual 405	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   694: aload_0
    //   695: getfield 291	com/sjetayupdpuwdr/AdController:S	Ljava/lang/String;
    //   698: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   701: invokevirtual 419	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   704: ldc_w 1496
    //   707: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   710: invokeinterface 1480 3 0
    //   715: pop
    //   716: goto -235 -> 481
    //   719: aload_0
    //   720: new 384	com/sjetayupdpuwdr/AdWebView
    //   723: dup
    //   724: aload_0
    //   725: getfield 289	com/sjetayupdpuwdr/AdController:XA	Landroid/content/Context;
    //   728: checkcast 838	android/app/Activity
    //   731: aload_0
    //   732: aload_0
    //   733: getfield 236	com/sjetayupdpuwdr/AdController:P	Z
    //   736: aload_0
    //   737: getfield 260	com/sjetayupdpuwdr/AdController:w	Lcom/sjetayupdpuwdr/AdListener;
    //   740: aload_0
    //   741: getfield 295	com/sjetayupdpuwdr/AdController:b	Landroid/widget/RelativeLayout;
    //   744: invokespecial 1499	com/sjetayupdpuwdr/AdWebView:<init>	(Landroid/content/Context;Lcom/sjetayupdpuwdr/AdController;ZLcom/sjetayupdpuwdr/AdListener;Landroid/widget/RelativeLayout;)V
    //   747: putfield 382	com/sjetayupdpuwdr/AdController:e	Lcom/sjetayupdpuwdr/AdWebView;
    //   750: ldc 71
    //   752: ldc_w 1501
    //   755: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   758: invokestatic 502	com/sjetayupdpuwdr/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   761: ldc_w 1239
    //   764: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   767: invokestatic 1243	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   770: ldc_w 1503
    //   773: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   776: aconst_null
    //   777: checkcast 1247	[Ljava/lang/Class;
    //   780: invokevirtual 1251	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   783: aload_0
    //   784: getfield 382	com/sjetayupdpuwdr/AdController:e	Lcom/sjetayupdpuwdr/AdWebView;
    //   787: aconst_null
    //   788: checkcast 1253	[Ljava/lang/Object;
    //   791: invokevirtual 1259	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   794: pop
    //   795: aload_0
    //   796: getfield 382	com/sjetayupdpuwdr/AdController:e	Lcom/sjetayupdpuwdr/AdWebView;
    //   799: iconst_0
    //   800: invokevirtual 673	com/sjetayupdpuwdr/AdWebView:setBackgroundColor	(I)V
    //   803: aload_0
    //   804: getfield 382	com/sjetayupdpuwdr/AdController:e	Lcom/sjetayupdpuwdr/AdWebView;
    //   807: aload_0
    //   808: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   811: invokevirtual 1507	com/sjetayupdpuwdr/AdWebView:setResults	(Lorg/json/JSONObject;)V
    //   814: aload_0
    //   815: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   818: ldc_w 1509
    //   821: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   824: invokevirtual 664	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   827: istore_1
    //   828: iload_1
    //   829: iconst_1
    //   830: if_icmpne +18 -> 848
    //   833: aload_0
    //   834: getfield 382	com/sjetayupdpuwdr/AdController:e	Lcom/sjetayupdpuwdr/AdWebView;
    //   837: new 26	com/sjetayupdpuwdr/AdController$3
    //   840: dup
    //   841: aload_0
    //   842: invokespecial 1510	com/sjetayupdpuwdr/AdController$3:<init>	(Lcom/sjetayupdpuwdr/AdController;)V
    //   845: invokevirtual 1514	com/sjetayupdpuwdr/AdWebView:setOnKeyListener	(Landroid/view/View$OnKeyListener;)V
    //   848: aload_0
    //   849: getfield 304	com/sjetayupdpuwdr/AdController:nA	Lcom/sjetayupdpuwdr/AdView;
    //   852: ifnonnull +214 -> 1066
    //   855: aload_0
    //   856: new 373	com/sjetayupdpuwdr/AdView
    //   859: dup
    //   860: aload_0
    //   861: getfield 289	com/sjetayupdpuwdr/AdController:XA	Landroid/content/Context;
    //   864: checkcast 838	android/app/Activity
    //   867: aload_0
    //   868: aload_0
    //   869: getfield 260	com/sjetayupdpuwdr/AdController:w	Lcom/sjetayupdpuwdr/AdListener;
    //   872: invokespecial 1517	com/sjetayupdpuwdr/AdView:<init>	(Landroid/content/Context;Lcom/sjetayupdpuwdr/AdController;Lcom/sjetayupdpuwdr/AdListener;)V
    //   875: putfield 304	com/sjetayupdpuwdr/AdController:nA	Lcom/sjetayupdpuwdr/AdView;
    //   878: aload_0
    //   879: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   882: ldc_w 1519
    //   885: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   888: invokevirtual 664	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   891: iconst_1
    //   892: if_icmpeq -754 -> 138
    //   895: aload_0
    //   896: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   899: ldc_w 1521
    //   902: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   905: invokevirtual 664	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   908: istore_1
    //   909: iload_1
    //   910: ifne -772 -> 138
    //   913: aload_0
    //   914: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   917: ldc_w 1523
    //   920: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   923: invokevirtual 664	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   926: ifle +190 -> 1116
    //   929: aload_0
    //   930: getfield 504	com/sjetayupdpuwdr/AdController:KA	Lorg/json/JSONObject;
    //   933: ldc_w 1525
    //   936: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   939: invokevirtual 664	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   942: sipush 1000
    //   945: imul
    //   946: istore_1
    //   947: ldc 71
    //   949: new 398	java/lang/StringBuilder
    //   952: dup
    //   953: invokespecial 399	java/lang/StringBuilder:<init>	()V
    //   956: iconst_0
    //   957: ldc_w 1527
    //   960: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   963: invokevirtual 405	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   966: iload_1
    //   967: invokevirtual 681	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   970: ldc_w 1529
    //   973: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   976: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: invokevirtual 419	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   982: invokestatic 396	com/sjetayupdpuwdr/AdLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   985: new 465	android/os/Handler
    //   988: dup
    //   989: invokespecial 1530	android/os/Handler:<init>	()V
    //   992: new 28	com/sjetayupdpuwdr/AdController$4
    //   995: dup
    //   996: aload_0
    //   997: invokespecial 1531	com/sjetayupdpuwdr/AdController$4:<init>	(Lcom/sjetayupdpuwdr/AdController;)V
    //   1000: iload_1
    //   1001: i2l
    //   1002: invokevirtual 1491	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   1005: pop
    //   1006: return
    //   1007: astore 4
    //   1009: aload_0
    //   1010: invokespecial 1279	com/sjetayupdpuwdr/AdController:G	()V
    //   1013: return
    //   1014: astore 4
    //   1016: aload_0
    //   1017: invokespecial 1279	com/sjetayupdpuwdr/AdController:G	()V
    //   1020: return
    //   1021: astore 4
    //   1023: ldc 71
    //   1025: new 398	java/lang/StringBuilder
    //   1028: dup
    //   1029: invokespecial 399	java/lang/StringBuilder:<init>	()V
    //   1032: iconst_0
    //   1033: ldc_w 1533
    //   1036: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   1039: invokevirtual 405	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   1042: aload 4
    //   1044: invokevirtual 566	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1047: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1050: invokevirtual 419	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1053: invokestatic 508	com/sjetayupdpuwdr/AdLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1056: ldc 71
    //   1058: aload 4
    //   1060: invokestatic 561	com/sjetayupdpuwdr/AdLog:printStackTrace	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   1063: goto -260 -> 803
    //   1066: ldc_w 1239
    //   1069: invokestatic 314	com/sjetayupdpuwdr/AdJSInterface:M	(Ljava/lang/String;)Ljava/lang/String;
    //   1072: invokestatic 1243	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1075: ldc_w 1503
    //   1078: invokestatic 325	com/sjetayupdpuwdr/AdRequest:M	(Ljava/lang/String;)Ljava/lang/String;
    //   1081: aconst_null
    //   1082: checkcast 1247	[Ljava/lang/Class;
    //   1085: invokevirtual 1251	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1088: aload_0
    //   1089: getfield 304	com/sjetayupdpuwdr/AdController:nA	Lcom/sjetayupdpuwdr/AdView;
    //   1092: aconst_null
    //   1093: checkcast 1253	[Ljava/lang/Object;
    //   1096: invokevirtual 1259	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1099: pop
    //   1100: aload_0
    //   1101: getfield 304	com/sjetayupdpuwdr/AdController:nA	Lcom/sjetayupdpuwdr/AdView;
    //   1104: iconst_0
    //   1105: invokevirtual 674	com/sjetayupdpuwdr/AdView:setBackgroundColor	(I)V
    //   1108: goto -230 -> 878
    //   1111: astore 4
    //   1113: goto -235 -> 878
    //   1116: aload_0
    //   1117: invokespecial 1279	com/sjetayupdpuwdr/AdController:G	()V
    //   1120: return
    //   1121: aload_0
    //   1122: getfield 260	com/sjetayupdpuwdr/AdController:w	Lcom/sjetayupdpuwdr/AdListener;
    //   1125: ifnull +17 -> 1142
    //   1128: aload_0
    //   1129: iconst_1
    //   1130: putfield 246	com/sjetayupdpuwdr/AdController:s	Z
    //   1133: aload_0
    //   1134: getfield 260	com/sjetayupdpuwdr/AdController:w	Lcom/sjetayupdpuwdr/AdListener;
    //   1137: invokeinterface 865 1 0
    //   1142: aload_0
    //   1143: getfield 300	com/sjetayupdpuwdr/AdController:g	Lcom/sjetayupdpuwdr/AdAudioListener;
    //   1146: ifnull -1008 -> 138
    //   1149: aload_0
    //   1150: iconst_1
    //   1151: putfield 276	com/sjetayupdpuwdr/AdController:LA	Z
    //   1154: aload_0
    //   1155: getfield 300	com/sjetayupdpuwdr/AdController:g	Lcom/sjetayupdpuwdr/AdAudioListener;
    //   1158: invokeinterface 868 1 0
    //   1163: return
    //   1164: astore 4
    //   1166: iload_2
    //   1167: istore_1
    //   1168: goto -340 -> 828
    //   1171: astore 6
    //   1173: goto -740 -> 433
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1176	0	this	AdController
    //   240	928	1	i1	int
    //   1	1166	2	i2	int
    //   228	172	3	i3	int
    //   21	477	4	localSharedPreferences	SharedPreferences
    //   573	1	4	localException1	Exception
    //   1007	1	4	localException2	Exception
    //   1014	1	4	localException3	Exception
    //   1021	38	4	localException4	Exception
    //   1111	1	4	localException5	Exception
    //   1164	1	4	localException6	Exception
    //   30	646	5	localEditor	SharedPreferences.Editor
    //   139	1	6	localException7	Exception
    //   144	1	6	localException8	Exception
    //   170	122	6	localDisplayMetrics	android.util.DisplayMetrics
    //   590	1	6	localException9	Exception
    //   608	1	6	localException10	Exception
    //   1171	1	6	localJSONException	JSONException
    //   199	25	7	localRect	android.graphics.Rect
    //   211	19	8	localWindow	android.view.Window
    // Exception table:
    //   from	to	target	type
    //   39	71	139	java/lang/Exception
    //   71	117	144	java/lang/Exception
    //   117	138	144	java/lang/Exception
    //   542	572	573	java/lang/Exception
    //   344	389	590	java/lang/Exception
    //   393	403	590	java/lang/Exception
    //   581	587	590	java/lang/Exception
    //   433	481	608	java/lang/Exception
    //   913	1006	1007	java/lang/Exception
    //   1116	1120	1007	java/lang/Exception
    //   878	909	1014	java/lang/Exception
    //   1009	1013	1014	java/lang/Exception
    //   761	803	1021	java/lang/Exception
    //   1066	1108	1111	java/lang/Exception
    //   814	828	1164	java/lang/Exception
    //   403	433	1171	org/json/JSONException
  }
  
  public String adShowStatus()
  {
    return this.XA.getSharedPreferences(AdRequest.M(")f\034r\034f\034z\032q"), 0).getString(this.S, AdRequest.M("p\034r\030a\025`"));
  }
  
  public void audioAdRetrieved(Integer paramInteger)
  {
    AdLog.d("LBAdController", AdJSInterface.M("2\0217\r<%766\020!\r6\0226\000"));
    if ((this.BA) || (this.h))
    {
      AdLog.d("LBAdController", AdRequest.M("s\026}\027sY`\0264\013q\ra\013zYr\013{\0244\030a\035}\026U\035F\034`\013}\034b\034pYu\n4\n`\026f\020z\0364\r{Yw\030w\021q"));
      paramInteger = this.XA.getSharedPreferences(AdJSInterface.M("\003\0266\0026\0266\n0\001"), 0).edit();
      paramInteger.putLong(this.S, System.currentTimeMillis());
      AdUtils.apply(paramInteger);
      this.C = true;
      if ((this.h) && (this.g != null)) {
        this.g.onAdCached();
      }
    }
    do
    {
      return;
      if (paramInteger.intValue() == 0)
      {
        m();
        return;
      }
    } while (this.g == null);
    this.g.onAdFailed();
    this.LA = true;
  }
  
  public void checkForAudioAd(String paramString1, String paramString2)
  {
    if ((!this.V) && (paramString1 != null) && (!paramString1.equals(AdJSInterface.M("=\021?\b"))) && (!paramString1.equals("")))
    {
      if (this.KA.isNull(AdRequest.M("\030p\030a\035}\026a\013x"))) {}
      for (;;)
      {
        try
        {
          this.KA.put(AdJSInterface.M("2\0002\0217\r<\021!\b"), paramString1);
          this.KA.put(AdRequest.M("u\035a\013x"), paramString2);
          if ((!this.LA) && (!this.KA.isNull(AdJSInterface.M("2\0002\0217\r<\021!\b"))))
          {
            if (this.L == null) {
              this.L = new AdShakeListener(true);
            }
            if (this.lA == null)
            {
              this.u = ((AudioManager)this.XA.getSystemService(AdRequest.M("u\fp\020{")));
              this.lA = ((SensorManager)this.XA.getSystemService(AdJSInterface.M(" \001=\027<\026")));
              this.rA = 0.0F;
              this.GA = 9.80665F;
              this.O = 9.80665F;
            }
            retrieveAudioAd();
          }
          return;
        }
        catch (JSONException paramString1) {}
      }
    }
    AdLog.d("LBAdController", AdRequest.M("7{Yu\fp\020{Yw\026y\t{\027q\027`"));
  }
  
  public void destroyAd()
  {
    AdLog.i("LBAdController", AdRequest.M("\035q\n`\013{\000U\0354\032u\025x\034p"));
    this.V = true;
    H();
    g();
  }
  
  public boolean getAdDestroyed()
  {
    return this.V;
  }
  
  public boolean getAdLoaded()
  {
    return this.s;
  }
  
  public boolean getLoadInBackground()
  {
    return this.eA;
  }
  
  public boolean getOnAdLoaded()
  {
    return this.yA;
  }
  
  public void hideElements()
  {
    try
    {
      this.e.setVisibility(8);
      this.l.setVisibility(8);
      this.oA.setVisibility(8);
      this.bA.setVisibility(8);
      this.m.setVisibility(8);
      this.mA.setVisibility(8);
      this.T.setVisibility(8);
      this.kA.setVisibility(8);
      this.G.setVisibility(8);
      this.b.setVisibility(8);
      this.y.setVisibility(8);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void loadAd()
  {
    if (!(this.XA instanceof Activity))
    {
      Log.e("LBAdController", AdJSInterface.M("\b<\0057%7LzD5\005:\b6\000sIs%s22\b:\000s%0\020:\022:\020*D=\013'D#\005 \0276\000"));
      if (this.w != null) {
        this.w.onAdFailed();
      }
    }
    do
    {
      return;
      if ((this.M == null) || (this.M.getStatus() != AsyncTask.Status.RUNNING)) {
        break;
      }
    } while (!this.BA);
    this.BA = false;
    return;
    if ((this.VA) && (l()))
    {
      AdLog.i("LBAdController", AdRequest.M(":u\032|\0344\020gYb\030x\020pY9Ys\026}\027sY`\0264\fg\0344\r|\030`"));
      this.eA = false;
      this.BA = false;
      G();
      this.iA = false;
      b();
      if (this.w != null) {
        this.w.onAdLoaded();
      }
      if (this.C)
      {
        m();
        this.C = false;
      }
      this.VA = false;
      SharedPreferences.Editor localEditor = this.XA.getSharedPreferences(AdJSInterface.M("\003\0266\0026\0266\n0\001"), 0).edit();
      localEditor.putLong(this.S, -1L);
      AdUtils.apply(localEditor);
      return;
    }
    this.BA = false;
    this.iA = false;
    this.VA = false;
    this.B = false;
    AdLog.i("LBAdController", AdJSInterface.M("\b<\0057%7D0\005?\b6\000"));
    this.yA = false;
    if (!this.B)
    {
      this.D = true;
      this.V = false;
      AdLog.i("LBAdController", AdRequest.M("\032u\025x\020z\0364\025{\030p8p0z\020`\020u\025}\003q"));
      M();
    }
    for (;;)
    {
      long l1 = System.currentTimeMillis();
      AdLog.d("LBAdController", l1);
      return;
      AdLog.i("LBAdController", AdJSInterface.M("\0072\b?\r=\003s\000:\027#\b2\035\022\000"));
      G();
    }
  }
  
  public void loadAdToCache()
  {
    if (!(this.XA instanceof Activity))
    {
      Log.e("LBAdController", AdRequest.M("x\026u\035U\035<P4\037u\020x\034pY9YUYB\030x\020pYU\032`\020b\020`\0004\027{\r4\tu\ng\034p"));
      if (this.w != null) {
        this.w.onAdFailed();
      }
      return;
    }
    AdLog.i("LBAdController", AdJSInterface.M("?\0132\000\022\000\007\013\020\0050\f6D0\005?\b6\000"));
    this.BA = true;
    this.yA = false;
    if (!this.B)
    {
      this.D = true;
      this.V = false;
      M();
      return;
    }
    G();
  }
  
  public void loadAudioAd()
  {
    if (this.f != null) {}
    label346:
    for (;;)
    {
      return;
      if ((this.M != null) && (this.M.getStatus() == AsyncTask.Status.RUNNING))
      {
        if (this.h) {
          this.h = false;
        }
      }
      else
      {
        if ((this.C) && (j()))
        {
          AdLog.i("LBAdController", AdRequest.M(":u\032|\0344\020gYb\030x\020pY9Ys\026}\027sY`\0264\tx\030mYu\fp\020{Yr\013{\0244\r|\030`"));
          this.h = false;
          m();
          this.C = false;
          this.VA = false;
          SharedPreferences.Editor localEditor = this.XA.getSharedPreferences(AdJSInterface.M("\003\0266\0026\0266\n0\001"), 0).edit();
          localEditor.putLong(this.S, -1L);
          AdUtils.apply(localEditor);
          return;
        }
        this.h = false;
        this.C = false;
        this.B = false;
        AdLog.i("LBAdController", AdJSInterface.M("?\0132\000\022\0217\r<%7D0\005?\b6\000"));
        if (!this.B)
        {
          this.n = true;
          if (this.lA == null)
          {
            this.L = new AdShakeListener(true);
            this.u = ((AudioManager)this.XA.getSystemService(AdRequest.M("u\fp\020{")));
            this.lA = ((SensorManager)this.XA.getSystemService(AdJSInterface.M(" \001=\027<\026")));
            this.rA = 0.0F;
            this.GA = 9.80665F;
            this.O = 9.80665F;
          }
          M();
        }
        for (;;)
        {
          if ((this.g == null) || (this.N <= 0)) {
            break label346;
          }
          if (this.DA == null) {
            this.DA = new Runnable()
            {
              public void run()
              {
                try
                {
                  if ((!AdController.J(AdController.this)) && (!AdController.D(AdController.this)))
                  {
                    AdLog.i("LBAdController", AdJSInterface.M("<\n\022\000\003\026<\003!\001 \027s\020!\r4\0036\0266\000"));
                    AdController.G(AdController.this).onAdProgress();
                    AdController.k(AdController.this).postDelayed(AdController.B(AdController.this), AdController.K(AdController.this) * 1000);
                  }
                  return;
                }
                catch (Exception localException)
                {
                  AdLog.e("LBAdController", AdController.M("?J(W(\030-P?VzW4y>h(W=J?K)\030.J3_=](]>"));
                  AdLog.printStackTrace("LBAdController", localException);
                }
              }
            };
          }
          if (this.z != null) {
            break;
          }
          this.z = new Handler();
          this.z.postDelayed(this.DA, this.N * 1000);
          return;
          m();
        }
      }
    }
  }
  
  public void loadAudioAdToCache()
  {
    if (this.f != null) {
      return;
    }
    AdLog.i("LBAdController", AdJSInterface.M("\b<\0057%&\000:\013\022\000\007\013\020\0050\f6D0\005?\b6\000"));
    if (!this.B)
    {
      this.n = true;
      this.h = true;
      if (this.lA == null)
      {
        this.L = new AdShakeListener(true);
        this.u = ((AudioManager)this.XA.getSystemService(AdRequest.M("u\fp\020{")));
        this.lA = ((SensorManager)this.XA.getSystemService(AdJSInterface.M(" \001=\027<\026")));
        this.rA = 0.0F;
        this.GA = 9.80665F;
        this.O = 9.80665F;
      }
      M();
      return;
    }
    m();
  }
  
  public void loadAudioTrack(long paramLong)
  {
    if (this.p == null) {
      this.p = new Runnable()
      {
        public void run()
        {
          SharedPreferences.Editor localEditor = AdController.this.XA.getSharedPreferences(AdJSInterface.M("\003\0266\0026\0266\n0\001"), 0).edit();
          localEditor.putBoolean(AdController.j(AdController.this), true);
          AdUtils.apply(localEditor);
          AdController.this.loadAudioAd();
        }
      };
    }
    if (this.PA == null) {
      this.PA = new Handler();
    }
    for (;;)
    {
      this.PA.postDelayed(this.p, 60L * paramLong * 1000L);
      return;
      this.PA.removeCallbacks(this.p);
    }
  }
  
  public void loadReEngagement() {}
  
  public void loadStartAd(String paramString1, String paramString2) {}
  
  public boolean onBackPressed()
  {
    if (this.v)
    {
      loadAd();
      return true;
    }
    return false;
  }
  
  public void onLinkClicked()
  {
    e();
  }
  
  public void reEngagementInitialized() {}
  
  public void retrieveAudioAd()
  {
    int i2 = 1;
    AdLog.d("LBAdController", AdRequest.M("f\034`\013}\034b\034U\fp\020{8p"));
    for (;;)
    {
      try
      {
        if (this.KA.get(AdJSInterface.M(" \f<\023")).equals(AdRequest.M("%")))
        {
          i1 = i2;
          if (this.CA != null)
          {
            if (this.CA.getStatus() != AsyncTask.Status.FINISHED) {
              break label161;
            }
            i1 = i2;
          }
          if (i1 != 0)
          {
            AdLog.d("LBAdController", AdJSInterface.M("4\013:\n4D'\013s\t2\0176D!\001\"\0216\027'D2\n7D5\001'\007;D2\0217\r<D2\000"));
            this.CA = new AdAudioTask(this, this.XA);
            M(this.CA, new String[] { this.KA.getString(AdRequest.M("\030p\030a\035}\026a\013x")) });
          }
        }
        else
        {
          this.n = false;
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        AdLog.d("LBAdController", localJSONException.getMessage());
      }
      return;
      label161:
      int i1 = 0;
    }
  }
  
  public void setAdDestroyed(boolean paramBoolean)
  {
    this.V = paramBoolean;
  }
  
  public void setAdLoaded(boolean paramBoolean)
  {
    this.s = paramBoolean;
  }
  
  public void setAdditionalDockingMargin(int paramInt)
  {
    this.r = paramInt;
    AdLog.i("LBAdController", paramInt);
  }
  
  public void setCompleted(boolean paramBoolean)
  {
    this.FA = paramBoolean;
  }
  
  public void setHTML(String paramString)
  {
    if (this.nA != null)
    {
      paramString = paramString + AdJSInterface.M("X|\f'\t?Z");
      this.nA.loadHTMLWrap(paramString);
    }
  }
  
  public void setHTMLAds(boolean paramBoolean)
  {
    this.AA = paramBoolean;
  }
  
  public void setHomeLoaded(boolean paramBoolean)
  {
    this.Y = paramBoolean;
  }
  
  public void setLayout(RelativeLayout paramRelativeLayout)
  {
    this.b = paramRelativeLayout;
  }
  
  public void setLoadInBackground(boolean paramBoolean)
  {
    this.eA = paramBoolean;
  }
  
  public void setLoading(boolean paramBoolean)
  {
    this.iA = paramBoolean;
  }
  
  public void setOnAdLoaded(boolean paramBoolean)
  {
    this.yA = paramBoolean;
    if ((this.eA) && (!this.V)) {
      ((Activity)this.XA).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (AdController.h(AdController.this))
          {
            SharedPreferences.Editor localEditor = AdController.this.XA.getSharedPreferences(AdJSInterface.M("\003\0266\0026\0266\n0\001"), 0).edit();
            localEditor.putLong(AdController.j(AdController.this), System.currentTimeMillis());
            AdUtils.apply(localEditor);
            AdController.J(AdController.this, true);
            if (AdController.b(AdController.this) != null) {
              AdController.b(AdController.this).onAdCached();
            }
            return;
          }
          if ((!AdController.h(AdController.this)) && (AdController.b(AdController.this) != null))
          {
            AdLog.i("LBAdController", AdJSInterface.M("'\026:\0034\001!D<\n\022\000\037\0132\0006\000"));
            AdController.b(AdController.this).onAdLoaded();
          }
          AdController.M(AdController.this, false);
          AdController.i(AdController.this);
          AdController.B(AdController.this, false);
        }
      });
    }
  }
  
  public void setOnProgressInterval(int paramInt)
  {
    this.N = paramInt;
  }
  
  public void setResults(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.KA = paramJSONObject;
      paramJSONObject = this.XA.getSharedPreferences(AdRequest.M(")f\034r\034f\034z\032q"), 0).edit();
    }
    try
    {
      paramJSONObject.putLong(AdJSInterface.M("\0057\0266\007;\0010\017'\r>\001"), this.KA.getLong(AdRequest.M("u\035f\034w\021q\032\r}\024q")));
      AdUtils.apply(paramJSONObject);
      AdLog.d("LBAdController", this.KA.getLong(AdRequest.M("u\035f\034w\021q\032\r}\024q")));
      return;
    }
    catch (Exception paramJSONObject)
    {
      AdLog.d("LBAdController", paramJSONObject.getMessage());
    }
  }
  
  public void setSubId(String paramString)
  {
    this.k = paramString;
  }
  
  public void setTokens(List<NameValuePair> paramList)
  {
    this.o = paramList;
  }
  
  public void showElements()
  {
    try
    {
      this.e.setVisibility(0);
      this.l.setVisibility(0);
      this.oA.setVisibility(0);
      this.bA.setVisibility(0);
      this.m.setVisibility(0);
      this.mA.setVisibility(0);
      this.T.setVisibility(0);
      this.kA.setVisibility(0);
      this.G.setVisibility(0);
      this.b.setVisibility(0);
      this.y.setVisibility(0);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void showInternetDialog()
  {
    if ((this.XA != null) && ((this.XA instanceof Activity)))
    {
      if (this.gA == null)
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.XA);
        localBuilder.setMessage(AdRequest.M("0z\rq\013z\034`Yz\026`Yu\017u\020x\030v\025q")).setCancelable(false).setPositiveButton(AdJSInterface.M("'?\013 \001"), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            AdController.m(AdController.this).dismiss();
          }
        });
        this.gA = localBuilder.create();
      }
      this.gA.show();
    }
  }
  
  public void stopAllListeners() {}
  
  public void triggerAdCompleted() {}
  
  public void triggerAdFailed()
  {
    AdLog.e("LBAdController", AdJSInterface.M("\035\013s-=\0206\026=\001'D0\013=\n6\007'\r<\ns\0006\0206\007'\0017Js*<D\022\000 D?\0132\0006\000"));
    if (this.w != null) {}
    for (;;)
    {
      try
      {
        AdLog.i("LBAdController", AdRequest.M("\026z8p?u\020x\034pY`\013}\036s\034f\034p"));
        this.w.onAdFailed();
        this.s = true;
        if (this.g != null)
        {
          this.g.onAdFailed();
          this.LA = true;
        }
        return;
      }
      catch (Exception localException)
      {
        AdLog.i("LBAdController", AdJSInterface.M("\026\026!\013!D$\f:\b6D0\005?\b:\n4D<\n\022\000\025\005:\b6\000"));
        AdLog.printStackTrace("LBAdController", localException);
      }
    }
  }
  
  private class AdClientPixel
    extends AsyncTask<String, Void, Boolean>
  {
    protected Boolean M(String... paramVarArgs)
    {
      Object localObject = paramVarArgs[0];
      if ((localObject != null) && (!((String)localObject).equals("")))
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        localBasicHttpParams.setParameter(AdRequest.M("|\r`\t:\tf\026`\026w\026xWb\034f\n}\026z"), HttpVersion.HTTP_1_1);
        paramVarArgs = new DefaultHttpClient(localBasicHttpParams);
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 4000);
        localObject = new HttpGet((String)localObject);
      }
      try
      {
        if (paramVarArgs.execute((HttpUriRequest)localObject).getStatusLine().getStatusCode() == 200) {
          AdController.b(this.b, true);
        }
        return Boolean.valueOf(false);
      }
      catch (Exception paramVarArgs)
      {
        for (;;) {}
      }
    }
    
    protected void M(boolean paramBoolean)
    {
      if (paramBoolean) {
        AdController.b(this.b, true);
      }
    }
  }
  
  private class AdShakeListener
    implements SensorEventListener
  {
    public AdShakeListener(boolean paramBoolean)
    {
      this.a = paramBoolean;
      if (this.a)
      {
        this.f = new Handler();
        this.d = new Runnable()
        {
          public void run()
          {
            AdLog.i("LBAdController", AdRequest.M("\n|\030\0344\030pYp\034`\034w\r}\026zYq\001d\020f\034g"));
            AdController.AdShakeListener.B(AdController.AdShakeListener.this);
            AdController.AdShakeListener.M(AdController.AdShakeListener.this);
          }
        };
      }
    }
    
    public void destroySensor()
    {
      if (this.L)
      {
        B();
        this.f.removeCallbacks(this.d);
      }
    }
    
    public void enableShakeDetection()
    {
      this.L = true;
      AdController.g(AdController.this).registerListener(this, AdController.g(AdController.this).getDefaultSensor(1), 1);
      AdLog.i("LBAdController", AdDefines.M("K\022Y\021]Z]\f]\024LZ\\\037L\037[\016Q\025VZ]\024Y\030T\037\\"));
    }
    
    public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
    
    public void onSensorChanged(SensorEvent paramSensorEvent)
    {
      float f1 = paramSensorEvent.values[0];
      float f2 = paramSensorEvent.values[1];
      float f3 = paramSensorEvent.values[2];
      AdController.b(AdController.this, AdController.H(AdController.this));
      AdController.M(AdController.this, FloatMath.sqrt(f1 * f1 + f2 * f2 + f3 * f3));
      f1 = AdController.H(AdController.this);
      f2 = AdController.A(AdController.this);
      AdController.B(AdController.this, f1 - f2 + AdController.C(AdController.this) * 0.9F);
      if (AdController.C(AdController.this) > 2.5F)
      {
        long l = System.currentTimeMillis();
        if (this.j == 0L)
        {
          this.j = l;
          this.b = l;
        }
        if (l - this.b < 300L)
        {
          this.b = l;
          this.k += 1;
          if ((this.k >= this.K) && (l - this.j < 1500L))
          {
            AdLog.i("LBAdController", AdJSInterface.M("\0222\b:\000s\027;\0058\001"));
            M();
            B();
            if (this.a)
            {
              if ((AdController.f(AdController.this) != null) && (AdController.f(AdController.this).isPlaying()))
              {
                AdController.f(AdController.this).stop();
                AdController.M(AdController.this, null);
                if (AdController.G(AdController.this) != null) {
                  AdController.G(AdController.this).onAdFinished();
                }
                AdController.L(AdController.this).setStreamVolume(3, AdController.e(AdController.this), 8);
              }
              this.f.removeCallbacks(this.d);
              b();
            }
          }
        }
        return;
      }
      M();
    }
    
    public void setShakeTime(int paramInt)
    {
      this.K = paramInt;
    }
    
    public void setValidTimes(int paramInt)
    {
      this.J = paramInt;
    }
    
    public void setupAudioAdHandler()
    {
      this.f.postDelayed(this.d, this.J);
    }
  }
  
  private class ContextList
    extends AsyncTask<Void, Void, String>
  {
    public ContextList(Context paramContext)
    {
      this.b = paramContext;
      this.B = this.b.getSharedPreferences(AdDefines.M("*J\037^\037J\037V\031]"), 0);
      this.a = this.B.edit();
    }
    
    protected String M(Void... paramVarArgs)
    {
      AdLog.i("LBAdController", AdDefines.M("\025Q\024_ZL\025\030\tL\033J\016\030;K\003V\031l\033K\021\030\016WZ_\037V\037J\033L\037\030\031W\024L\037@\016K"));
      paramVarArgs = new StringBuilder();
      Object localObject1 = this.b.getPackageManager().getInstalledPackages(128).iterator();
      int i = 0;
      Object localObject2;
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (i <= 50) {}
      }
      for (;;)
      {
        localObject1 = (ActivityManager)this.b.getSystemService(AdDefines.M("\033[\016Q\fQ\016A"));
        if (this.b.checkCallingOrSelfPermission(AdDefines.M("\033V\036J\025Q\036\026\n]\bU\023K\tQ\025VT?l%l;k1k")) != 0) {
          break label589;
        }
        localObject1 = ((ActivityManager)localObject1).getRunningTasks(Integer.MAX_VALUE).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ActivityManager.RunningTaskInfo)((Iterator)localObject1).next();
          try
          {
            paramVarArgs.append(URLEncoder.encode(new StringBuilder().insert(0, AdDefines.M("\016Q\016T\037\005")).append(this.b.getPackageManager().getApplicationInfo(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName(), 0).loadLabel(this.b.getPackageManager()).toString()).append(AdDefines.M("\036\nY\031S\033_\037\005")).append(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName()).toString(), AdDefines.M("m.~W\000")));
          }
          catch (Exception localException1) {}
        }
        if ((localException1.applicationInfo.flags & 0x1) != 0) {
          break;
        }
        StringBuilder localStringBuilder1 = new StringBuilder();
        i += 1;
        for (;;)
        {
          try
          {
            localStringBuilder1.append(localException1.applicationInfo.loadLabel(this.b.getPackageManager()).toString());
            localStringBuilder1.append(localException1.applicationInfo.packageName);
            StringBuilder localStringBuilder2 = new StringBuilder().insert(0, AdDefines.M("\036\023V\tL\033T\026\005"));
            if (Build.VERSION.SDK_INT < 9) {
              break label576;
            }
            l = localException1.firstInstallTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder2 = new StringBuilder().insert(0, AdDefines.M("\\M\n\\\033L\037\005"));
            if (Build.VERSION.SDK_INT < 9) {
              break label581;
            }
            l = localException1.lastUpdateTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder1.append(localException1.versionCode);
            localStringBuilder1.append(localException1.versionName);
            paramVarArgs.append(URLEncoder.encode(localStringBuilder1.toString(), AdDefines.M("m.~W\000")));
          }
          catch (Exception localException2) {}
          break;
          label576:
          long l = 0L;
          continue;
          label581:
          l = 0L;
        }
      }
      label589:
      return AdEncryption.encrypt(paramVarArgs.toString());
    }
    
    protected void M(String paramString)
    {
      this.a.putBoolean(AdDefines.M(")|%{5v.}\"l)g3v*j5(})k"), false);
      AdUtils.apply(this.a);
      if ((paramString != null) && (!paramString.equals("")))
      {
        this.a.putString(AdDefines.M("k>g9w4l?`.k"), paramString);
        this.a.putLong(AdDefines.M("k>g9w4l?`.k%m*|;l?g.q7}"), System.currentTimeMillis());
        AdUtils.apply(this.a);
      }
    }
    
    protected void onPreExecute()
    {
      this.a.putBoolean(AdDefines.M(")|%{5v.}\"l)g3v*j5(})k"), true);
      AdUtils.apply(this.a);
    }
  }
  
  public static class Dimensions
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<Dimensions> a = new Parcelable.Creator()
    {
      public AdController.Dimensions createFromParcel(Parcel paramAnonymousParcel)
      {
        return new AdController.Dimensions(paramAnonymousParcel);
      }
      
      public AdController.Dimensions[] newArray(int paramAnonymousInt)
      {
        return new AdController.Dimensions[paramAnonymousInt];
      }
    };
    public int B;
    public int b;
    public int g;
    public int j;
    
    public Dimensions()
    {
      this.j = -1;
      this.g = -1;
      this.B = -1;
      this.b = -1;
    }
    
    protected Dimensions(Parcel paramParcel)
    {
      super();
    }
  }
  
  public static class PlayerProperties
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<PlayerProperties> b = new Parcelable.Creator()
    {
      public AdController.PlayerProperties createFromParcel(Parcel paramAnonymousParcel)
      {
        return new AdController.PlayerProperties(paramAnonymousParcel);
      }
      
      public AdController.PlayerProperties[] newArray(int paramAnonymousInt)
      {
        return new AdController.PlayerProperties[paramAnonymousInt];
      }
    };
    public String B;
    public boolean a;
    public boolean d;
    public boolean f;
    public boolean g;
    public String j;
    public boolean k;
    
    public PlayerProperties()
    {
      this.a = true;
      this.f = true;
      this.d = false;
      this.g = false;
      this.B = "normal";
      this.j = "normal";
      this.k = false;
    }
    
    public PlayerProperties(Parcel paramParcel)
    {
      super();
    }
    
    public boolean doLoop()
    {
      return this.g;
    }
    
    public boolean doMute()
    {
      return this.d;
    }
    
    public boolean exitOnComplete()
    {
      return this.B.equalsIgnoreCase("exit");
    }
    
    public boolean isAutoPlay()
    {
      return this.f == true;
    }
    
    public boolean isFullScreen()
    {
      return this.j.equalsIgnoreCase("fullscreen");
    }
    
    public void muteAudio()
    {
      this.d = true;
    }
    
    public void setProperties(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String paramString1, String paramString2)
    {
      this.f = paramBoolean2;
      this.a = paramBoolean3;
      this.g = paramBoolean5;
      this.d = paramBoolean1;
      this.j = paramString1;
      this.B = paramString2;
      this.k = paramBoolean4;
    }
    
    public void setStopStyle(String paramString)
    {
      this.B = paramString;
    }
    
    public boolean showControl()
    {
      return this.a;
    }
  }
  
  public static class Properties
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<Properties> B = new Parcelable.Creator()
    {
      public AdController.Properties createFromParcel(Parcel paramAnonymousParcel)
      {
        return new AdController.Properties(paramAnonymousParcel);
      }
      
      public AdController.Properties[] newArray(int paramAnonymousInt)
      {
        return new AdController.Properties[paramAnonymousInt];
      }
    };
    public int a;
    public boolean b;
    public float j;
    
    public Properties()
    {
      this.b = false;
      this.a = 0;
      this.j = 0.0F;
    }
    
    protected Properties(Parcel paramParcel)
    {
      super();
    }
  }
  
  public static class ReflectedParcelable
    implements Parcelable
  {
    public ReflectedParcelable() {}
    
    protected ReflectedParcelable(Parcel paramParcel)
    {
      Field[] arrayOfField = getClass().getDeclaredFields();
      for (;;)
      {
        int i;
        try
        {
          int j = arrayOfField.length;
          i = 0;
          if (i < j)
          {
            localField = arrayOfField[i];
            Object localObject = localField.getType();
            if (!((Class)localObject).isEnum()) {
              continue;
            }
            localObject = ((Class)localObject).toString();
            if (((String)localObject).equals("class com.sjetayupdpuwdr.AdNavigationStringEnum")) {
              localField.set(this, AdNavigationStringEnum.fromString(paramParcel.readString()));
            } else if (((String)localObject).equals("class com.sjetayupdpuwdr.AdTransitionStringEnum")) {
              localField.set(this, AdTransitionStringEnum.fromString(paramParcel.readString()));
            }
          }
        }
        catch (IllegalArgumentException paramParcel)
        {
          Field localField;
          AdLog.printStackTrace("LBAdController", paramParcel);
          return;
          if (!(localField.get(this) instanceof Parcelable.Creator)) {
            localField.set(this, paramParcel.readValue(null));
          }
        }
        catch (IllegalAccessException paramParcel)
        {
          AdLog.printStackTrace("LBAdController", paramParcel);
          return;
        }
        i += 1;
      }
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      Field[] arrayOfField = getClass().getDeclaredFields();
      for (;;)
      {
        try
        {
          int i = arrayOfField.length;
          paramInt = 0;
          if (paramInt < i)
          {
            localObject1 = arrayOfField[paramInt];
            Object localObject2 = ((Field)localObject1).getType();
            if (!((Class)localObject2).isEnum()) {
              continue;
            }
            localObject2 = ((Class)localObject2).toString();
            if (((String)localObject2).equals("class com.sjetayupdpuwdr.AdNavigationStringEnum")) {
              paramParcel.writeString(((AdNavigationStringEnum)((Field)localObject1).get(this)).getText());
            } else if (((String)localObject2).equals("class com.sjetayupdpuwdr.AdTransitionStringEnum")) {
              paramParcel.writeString(((AdTransitionStringEnum)((Field)localObject1).get(this)).getText());
            }
          }
        }
        catch (IllegalArgumentException paramParcel)
        {
          AdLog.printStackTrace("LBAdController", paramParcel);
          return;
          Object localObject1 = ((Field)localObject1).get(this);
          if (!(localObject1 instanceof Parcelable.Creator)) {
            paramParcel.writeValue(localObject1);
          }
        }
        catch (IllegalAccessException paramParcel)
        {
          AdLog.printStackTrace("LBAdController", paramParcel);
          return;
        }
        paramInt += 1;
      }
    }
  }
}
