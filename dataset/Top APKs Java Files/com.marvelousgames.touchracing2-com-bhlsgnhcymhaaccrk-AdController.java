package com.bhlsgnhcymhaaccrk;

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
import java.util.Collections;
import java.util.Comparator;
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
  public static final String FA = "normal";
  public static final String G = "LBAdController";
  public static final String Q = "exit";
  public static final String rA = "fullscreen";
  protected AdView S;
  protected Context r;
  
  public AdController(Context paramContext, String paramString)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
  }
  
  public AdController(Context paramContext, String paramString, WebView paramWebView)
  {
    this.r = paramContext;
    this.U = paramString;
    this.Z = paramWebView;
    this.B = new RelativeLayout(this.r);
    M();
  }
  
  public AdController(Context paramContext, String paramString, RelativeLayout paramRelativeLayout)
  {
    this.r = paramContext;
    this.U = paramString;
    paramContext = paramRelativeLayout;
    if (paramRelativeLayout == null) {
      paramContext = new RelativeLayout(this.r);
    }
    this.B = paramContext;
    this.Z = null;
    M();
  }
  
  public AdController(Context paramContext, String paramString, AdAudioListener paramAdAudioListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.xA = paramAdAudioListener;
  }
  
  public AdController(Context paramContext, String paramString, AdListener paramAdListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.AA = paramAdListener;
  }
  
  public AdController(AdView paramAdView, Context paramContext)
  {
    this.S = paramAdView;
    this.r = paramContext;
  }
  
  protected static Object F(JSONObject paramJSONObject, Class<?> paramClass)
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
          boolean bool = str1.startsWith(AdDefines.F("q"));
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
          if (!str2.equals("class com.bhlsgnhcymhaaccrk.AdNavigationStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdNavigationStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
          if (!str2.equals("class com.bhlsgnhcymhaaccrk.AdTransitionStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdTransitionStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
        }
        try
        {
          if (str1.startsWith(AdRequest.F(",\033w")))
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
  
  public static String F(String paramString)
  {
    int i1 = paramString.length();
    char[] arrayOfChar = new char[i1];
    int i2 = i1 - 1;
    for (i1 = i2; i2 >= 0; i1 = i2)
    {
      i2 = paramString.charAt(i1);
      int i3 = i1 - 1;
      arrayOfChar[i1] = ((char)(i2 ^ 0x60));
      if (i3 < 0) {
        break;
      }
      i2 = i3 - 1;
      arrayOfChar[i3] = ((char)(paramString.charAt(i3) ^ 0x79));
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
    //   4: putfield 240	com/bhlsgnhcymhaaccrk/AdController:p	Z
    //   7: aload_0
    //   8: getfield 291	com/bhlsgnhcymhaaccrk/AdController:r	Landroid/content/Context;
    //   11: ldc_w 882
    //   14: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   17: iconst_0
    //   18: invokevirtual 888	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   21: astore 4
    //   23: aload 4
    //   25: invokeinterface 894 1 0
    //   30: astore 5
    //   32: aload_0
    //   33: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   36: ifnull +1085 -> 1121
    //   39: aload 5
    //   41: ldc_w 1400
    //   44: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   47: aload_0
    //   48: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   51: ldc_w 1402
    //   54: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   57: invokevirtual 1405	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   60: invokeinterface 1409 4 0
    //   65: pop
    //   66: aload 5
    //   68: invokestatic 908	com/bhlsgnhcymhaaccrk/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   71: aload_0
    //   72: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   75: ldc_w 1119
    //   78: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   81: invokevirtual 437	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   84: ldc_w 1411
    //   87: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   90: invokevirtual 445	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   93: ifeq +53 -> 146
    //   96: aload_0
    //   97: getfield 262	com/bhlsgnhcymhaaccrk/AdController:AA	Lcom/bhlsgnhcymhaaccrk/AdListener;
    //   100: ifnull +17 -> 117
    //   103: aload_0
    //   104: iconst_1
    //   105: putfield 248	com/bhlsgnhcymhaaccrk/AdController:D	Z
    //   108: aload_0
    //   109: getfield 262	com/bhlsgnhcymhaaccrk/AdController:AA	Lcom/bhlsgnhcymhaaccrk/AdListener;
    //   112: invokeinterface 1261 1 0
    //   117: aload_0
    //   118: getfield 302	com/bhlsgnhcymhaaccrk/AdController:xA	Lcom/bhlsgnhcymhaaccrk/AdAudioListener;
    //   121: ifnull +17 -> 138
    //   124: aload_0
    //   125: iconst_1
    //   126: putfield 278	com/bhlsgnhcymhaaccrk/AdController:q	Z
    //   129: aload_0
    //   130: getfield 302	com/bhlsgnhcymhaaccrk/AdController:xA	Lcom/bhlsgnhcymhaaccrk/AdAudioListener;
    //   133: invokeinterface 1262 1 0
    //   138: return
    //   139: astore 6
    //   141: goto -70 -> 71
    //   144: astore 6
    //   146: aload_0
    //   147: getfield 291	com/bhlsgnhcymhaaccrk/AdController:r	Landroid/content/Context;
    //   150: ifnull +194 -> 344
    //   153: aload_0
    //   154: getfield 291	com/bhlsgnhcymhaaccrk/AdController:r	Landroid/content/Context;
    //   157: instanceof 572
    //   160: ifeq +184 -> 344
    //   163: new 1413	android/util/DisplayMetrics
    //   166: dup
    //   167: invokespecial 1414	android/util/DisplayMetrics:<init>	()V
    //   170: astore 6
    //   172: aload_0
    //   173: getfield 291	com/bhlsgnhcymhaaccrk/AdController:r	Landroid/content/Context;
    //   176: checkcast 572	android/app/Activity
    //   179: invokevirtual 1418	android/app/Activity:getWindowManager	()Landroid/view/WindowManager;
    //   182: invokeinterface 1424 1 0
    //   187: aload 6
    //   189: invokevirtual 1430	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   192: new 1432	android/graphics/Rect
    //   195: dup
    //   196: invokespecial 1433	android/graphics/Rect:<init>	()V
    //   199: astore 7
    //   201: aload_0
    //   202: getfield 291	com/bhlsgnhcymhaaccrk/AdController:r	Landroid/content/Context;
    //   205: checkcast 572	android/app/Activity
    //   208: invokevirtual 1437	android/app/Activity:getWindow	()Landroid/view/Window;
    //   211: astore 8
    //   213: aload 8
    //   215: invokevirtual 1443	android/view/Window:getDecorView	()Landroid/view/View;
    //   218: aload 7
    //   220: invokevirtual 1447	android/view/View:getWindowVisibleDisplayFrame	(Landroid/graphics/Rect;)V
    //   223: aload 7
    //   225: getfield 1450	android/graphics/Rect:top	I
    //   228: istore_3
    //   229: aload 8
    //   231: ldc_w 934
    //   234: invokevirtual 1451	android/view/Window:findViewById	(I)Landroid/view/View;
    //   237: invokevirtual 1454	android/view/View:getTop	()I
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
    //   253: getfield 1457	android/util/DisplayMetrics:widthPixels	I
    //   256: putfield 568	com/bhlsgnhcymhaaccrk/AdController:n	I
    //   259: aload_0
    //   260: aload 6
    //   262: getfield 1460	android/util/DisplayMetrics:heightPixels	I
    //   265: iload_3
    //   266: isub
    //   267: iload_1
    //   268: isub
    //   269: putfield 570	com/bhlsgnhcymhaaccrk/AdController:mA	I
    //   272: ldc 80
    //   274: new 331	java/lang/StringBuilder
    //   277: dup
    //   278: invokespecial 332	java/lang/StringBuilder:<init>	()V
    //   281: iconst_0
    //   282: ldc_w 1462
    //   285: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   288: invokevirtual 341	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   291: aload 6
    //   293: getfield 1460	android/util/DisplayMetrics:heightPixels	I
    //   296: invokevirtual 946	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   299: ldc_w 1464
    //   302: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   305: invokevirtual 595	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: iload_3
    //   309: invokevirtual 946	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   312: ldc_w 1466
    //   315: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   318: invokevirtual 595	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: iconst_0
    //   322: invokevirtual 946	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   325: ldc_w 1464
    //   328: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   331: invokevirtual 595	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: iload_1
    //   335: invokevirtual 946	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   338: invokevirtual 351	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: invokestatic 551	com/bhlsgnhcymhaaccrk/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   344: aload_0
    //   345: aload_0
    //   346: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   349: ldc_w 1468
    //   352: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   355: invokevirtual 392	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   358: putfield 254	com/bhlsgnhcymhaaccrk/AdController:iA	I
    //   361: aload_0
    //   362: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   365: ldc_w 1470
    //   368: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   371: invokevirtual 392	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   374: istore_1
    //   375: aload_0
    //   376: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   379: ldc_w 1472
    //   382: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   385: invokevirtual 392	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   388: istore_3
    //   389: iload_3
    //   390: ifle +191 -> 581
    //   393: aload_0
    //   394: iload_1
    //   395: bipush 60
    //   397: imul
    //   398: iload_3
    //   399: idiv
    //   400: putfield 256	com/bhlsgnhcymhaaccrk/AdController:BA	I
    //   403: aload_0
    //   404: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   407: ldc_w 1474
    //   410: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   413: invokevirtual 557	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   416: ldc_w 558
    //   419: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   422: invokevirtual 559	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   425: ifeq +8 -> 433
    //   428: aload_0
    //   429: iconst_1
    //   430: putfield 238	com/bhlsgnhcymhaaccrk/AdController:W	Z
    //   433: aload 5
    //   435: new 331	java/lang/StringBuilder
    //   438: dup
    //   439: invokespecial 332	java/lang/StringBuilder:<init>	()V
    //   442: iconst_0
    //   443: ldc_w 1476
    //   446: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   449: invokevirtual 341	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   452: aload_0
    //   453: getfield 293	com/bhlsgnhcymhaaccrk/AdController:U	Ljava/lang/String;
    //   456: invokevirtual 595	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: invokevirtual 351	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   462: aload_0
    //   463: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   466: ldc_w 1478
    //   469: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   472: invokevirtual 437	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   475: invokeinterface 1482 3 0
    //   480: pop
    //   481: aload 5
    //   483: invokestatic 908	com/bhlsgnhcymhaaccrk/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   486: aload_0
    //   487: getfield 276	com/bhlsgnhcymhaaccrk/AdController:E	Z
    //   490: ifeq +229 -> 719
    //   493: aload_0
    //   494: invokevirtual 1485	com/bhlsgnhcymhaaccrk/AdController:retrieveAudioAd	()V
    //   497: aload 4
    //   499: new 331	java/lang/StringBuilder
    //   502: dup
    //   503: invokespecial 332	java/lang/StringBuilder:<init>	()V
    //   506: iconst_0
    //   507: ldc_w 1487
    //   510: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   513: invokevirtual 341	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   516: aload_0
    //   517: getfield 293	com/bhlsgnhcymhaaccrk/AdController:U	Ljava/lang/String;
    //   520: invokevirtual 595	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: invokevirtual 351	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   526: iconst_0
    //   527: invokeinterface 998 3 0
    //   532: ifeq -394 -> 138
    //   535: aload_0
    //   536: getfield 872	com/bhlsgnhcymhaaccrk/AdController:w	Landroid/os/Handler;
    //   539: ifnull -401 -> 138
    //   542: aload_0
    //   543: getfield 872	com/bhlsgnhcymhaaccrk/AdController:w	Landroid/os/Handler;
    //   546: aload_0
    //   547: getfield 874	com/bhlsgnhcymhaaccrk/AdController:e	Ljava/lang/Runnable;
    //   550: aload_0
    //   551: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   554: ldc_w 1489
    //   557: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   560: invokevirtual 392	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   563: sipush 1000
    //   566: imul
    //   567: i2l
    //   568: invokevirtual 1493	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   571: pop
    //   572: return
    //   573: astore 4
    //   575: return
    //   576: iconst_0
    //   577: istore_1
    //   578: goto -328 -> 250
    //   581: aload_0
    //   582: bipush 10
    //   584: putfield 256	com/bhlsgnhcymhaaccrk/AdController:BA	I
    //   587: goto -184 -> 403
    //   590: astore 6
    //   592: aload_0
    //   593: sipush 500
    //   596: putfield 254	com/bhlsgnhcymhaaccrk/AdController:iA	I
    //   599: aload_0
    //   600: bipush 10
    //   602: putfield 256	com/bhlsgnhcymhaaccrk/AdController:BA	I
    //   605: goto -202 -> 403
    //   608: astore 6
    //   610: aload_0
    //   611: getfield 1495	com/bhlsgnhcymhaaccrk/AdController:LA	Ljava/lang/String;
    //   614: ifnull +61 -> 675
    //   617: aload_0
    //   618: getfield 1495	com/bhlsgnhcymhaaccrk/AdController:LA	Ljava/lang/String;
    //   621: ldc_w 1411
    //   624: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   627: invokevirtual 445	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   630: ifne +45 -> 675
    //   633: aload 5
    //   635: new 331	java/lang/StringBuilder
    //   638: dup
    //   639: invokespecial 332	java/lang/StringBuilder:<init>	()V
    //   642: iconst_0
    //   643: ldc_w 1497
    //   646: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   649: invokevirtual 341	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   652: aload_0
    //   653: getfield 293	com/bhlsgnhcymhaaccrk/AdController:U	Ljava/lang/String;
    //   656: invokevirtual 595	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   659: invokevirtual 351	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   662: aload_0
    //   663: getfield 1495	com/bhlsgnhcymhaaccrk/AdController:LA	Ljava/lang/String;
    //   666: invokeinterface 1482 3 0
    //   671: pop
    //   672: goto -191 -> 481
    //   675: aload 5
    //   677: new 331	java/lang/StringBuilder
    //   680: dup
    //   681: invokespecial 332	java/lang/StringBuilder:<init>	()V
    //   684: iconst_0
    //   685: ldc_w 1476
    //   688: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   691: invokevirtual 341	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   694: aload_0
    //   695: getfield 293	com/bhlsgnhcymhaaccrk/AdController:U	Ljava/lang/String;
    //   698: invokevirtual 595	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   701: invokevirtual 351	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   704: ldc_w 1498
    //   707: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   710: invokeinterface 1482 3 0
    //   715: pop
    //   716: goto -235 -> 481
    //   719: aload_0
    //   720: new 414	com/bhlsgnhcymhaaccrk/AdWebView
    //   723: dup
    //   724: aload_0
    //   725: getfield 291	com/bhlsgnhcymhaaccrk/AdController:r	Landroid/content/Context;
    //   728: checkcast 572	android/app/Activity
    //   731: aload_0
    //   732: aload_0
    //   733: getfield 238	com/bhlsgnhcymhaaccrk/AdController:W	Z
    //   736: aload_0
    //   737: getfield 262	com/bhlsgnhcymhaaccrk/AdController:AA	Lcom/bhlsgnhcymhaaccrk/AdListener;
    //   740: aload_0
    //   741: getfield 297	com/bhlsgnhcymhaaccrk/AdController:B	Landroid/widget/RelativeLayout;
    //   744: invokespecial 1501	com/bhlsgnhcymhaaccrk/AdWebView:<init>	(Landroid/content/Context;Lcom/bhlsgnhcymhaaccrk/AdController;ZLcom/bhlsgnhcymhaaccrk/AdListener;Landroid/widget/RelativeLayout;)V
    //   747: putfield 355	com/bhlsgnhcymhaaccrk/AdController:CA	Lcom/bhlsgnhcymhaaccrk/AdWebView;
    //   750: ldc 80
    //   752: ldc_w 1503
    //   755: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   758: invokestatic 551	com/bhlsgnhcymhaaccrk/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   761: ldc_w 1283
    //   764: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   767: invokestatic 1287	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   770: ldc_w 1505
    //   773: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   776: aconst_null
    //   777: checkcast 1291	[Ljava/lang/Class;
    //   780: invokevirtual 1295	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   783: aload_0
    //   784: getfield 355	com/bhlsgnhcymhaaccrk/AdController:CA	Lcom/bhlsgnhcymhaaccrk/AdWebView;
    //   787: aconst_null
    //   788: checkcast 1297	[Ljava/lang/Object;
    //   791: invokevirtual 1303	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   794: pop
    //   795: aload_0
    //   796: getfield 355	com/bhlsgnhcymhaaccrk/AdController:CA	Lcom/bhlsgnhcymhaaccrk/AdWebView;
    //   799: iconst_0
    //   800: invokevirtual 418	com/bhlsgnhcymhaaccrk/AdWebView:setBackgroundColor	(I)V
    //   803: aload_0
    //   804: getfield 355	com/bhlsgnhcymhaaccrk/AdController:CA	Lcom/bhlsgnhcymhaaccrk/AdWebView;
    //   807: aload_0
    //   808: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   811: invokevirtual 1509	com/bhlsgnhcymhaaccrk/AdWebView:setResults	(Lorg/json/JSONObject;)V
    //   814: aload_0
    //   815: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   818: ldc_w 1511
    //   821: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   824: invokevirtual 392	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   827: istore_1
    //   828: iload_1
    //   829: iconst_1
    //   830: if_icmpne +18 -> 848
    //   833: aload_0
    //   834: getfield 355	com/bhlsgnhcymhaaccrk/AdController:CA	Lcom/bhlsgnhcymhaaccrk/AdWebView;
    //   837: new 26	com/bhlsgnhcymhaaccrk/AdController$3
    //   840: dup
    //   841: aload_0
    //   842: invokespecial 1512	com/bhlsgnhcymhaaccrk/AdController$3:<init>	(Lcom/bhlsgnhcymhaaccrk/AdController;)V
    //   845: invokevirtual 1516	com/bhlsgnhcymhaaccrk/AdWebView:setOnKeyListener	(Landroid/view/View$OnKeyListener;)V
    //   848: aload_0
    //   849: getfield 306	com/bhlsgnhcymhaaccrk/AdController:S	Lcom/bhlsgnhcymhaaccrk/AdView;
    //   852: ifnonnull +214 -> 1066
    //   855: aload_0
    //   856: new 953	com/bhlsgnhcymhaaccrk/AdView
    //   859: dup
    //   860: aload_0
    //   861: getfield 291	com/bhlsgnhcymhaaccrk/AdController:r	Landroid/content/Context;
    //   864: checkcast 572	android/app/Activity
    //   867: aload_0
    //   868: aload_0
    //   869: getfield 262	com/bhlsgnhcymhaaccrk/AdController:AA	Lcom/bhlsgnhcymhaaccrk/AdListener;
    //   872: invokespecial 1519	com/bhlsgnhcymhaaccrk/AdView:<init>	(Landroid/content/Context;Lcom/bhlsgnhcymhaaccrk/AdController;Lcom/bhlsgnhcymhaaccrk/AdListener;)V
    //   875: putfield 306	com/bhlsgnhcymhaaccrk/AdController:S	Lcom/bhlsgnhcymhaaccrk/AdView;
    //   878: aload_0
    //   879: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   882: ldc_w 1521
    //   885: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   888: invokevirtual 392	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   891: iconst_1
    //   892: if_icmpeq -754 -> 138
    //   895: aload_0
    //   896: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   899: ldc_w 1523
    //   902: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   905: invokevirtual 392	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   908: istore_1
    //   909: iload_1
    //   910: ifne -772 -> 138
    //   913: aload_0
    //   914: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   917: ldc_w 1525
    //   920: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   923: invokevirtual 392	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   926: ifle +190 -> 1116
    //   929: aload_0
    //   930: getfield 384	com/bhlsgnhcymhaaccrk/AdController:u	Lorg/json/JSONObject;
    //   933: ldc_w 1527
    //   936: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   939: invokevirtual 392	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   942: sipush 1000
    //   945: imul
    //   946: istore_1
    //   947: ldc 80
    //   949: new 331	java/lang/StringBuilder
    //   952: dup
    //   953: invokespecial 332	java/lang/StringBuilder:<init>	()V
    //   956: iconst_0
    //   957: ldc_w 1529
    //   960: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   963: invokevirtual 341	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   966: iload_1
    //   967: invokevirtual 946	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   970: ldc_w 1531
    //   973: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   976: invokevirtual 595	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: invokevirtual 351	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   982: invokestatic 327	com/bhlsgnhcymhaaccrk/AdLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   985: new 876	android/os/Handler
    //   988: dup
    //   989: invokespecial 1532	android/os/Handler:<init>	()V
    //   992: new 28	com/bhlsgnhcymhaaccrk/AdController$4
    //   995: dup
    //   996: aload_0
    //   997: invokespecial 1533	com/bhlsgnhcymhaaccrk/AdController$4:<init>	(Lcom/bhlsgnhcymhaaccrk/AdController;)V
    //   1000: iload_1
    //   1001: i2l
    //   1002: invokevirtual 1493	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   1005: pop
    //   1006: return
    //   1007: astore 4
    //   1009: aload_0
    //   1010: invokespecial 912	com/bhlsgnhcymhaaccrk/AdController:g	()V
    //   1013: return
    //   1014: astore 4
    //   1016: aload_0
    //   1017: invokespecial 912	com/bhlsgnhcymhaaccrk/AdController:g	()V
    //   1020: return
    //   1021: astore 4
    //   1023: ldc 80
    //   1025: new 331	java/lang/StringBuilder
    //   1028: dup
    //   1029: invokespecial 332	java/lang/StringBuilder:<init>	()V
    //   1032: iconst_0
    //   1033: ldc_w 1535
    //   1036: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   1039: invokevirtual 341	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   1042: aload 4
    //   1044: invokevirtual 598	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1047: invokevirtual 595	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1050: invokevirtual 351	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1053: invokestatic 583	com/bhlsgnhcymhaaccrk/AdLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1056: ldc 80
    //   1058: aload 4
    //   1060: invokestatic 587	com/bhlsgnhcymhaaccrk/AdLog:printStackTrace	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   1063: goto -260 -> 803
    //   1066: ldc_w 1283
    //   1069: invokestatic 322	com/bhlsgnhcymhaaccrk/AdDefines:F	(Ljava/lang/String;)Ljava/lang/String;
    //   1072: invokestatic 1287	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1075: ldc_w 1505
    //   1078: invokestatic 337	com/bhlsgnhcymhaaccrk/AdRequest:F	(Ljava/lang/String;)Ljava/lang/String;
    //   1081: aconst_null
    //   1082: checkcast 1291	[Ljava/lang/Class;
    //   1085: invokevirtual 1295	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1088: aload_0
    //   1089: getfield 306	com/bhlsgnhcymhaaccrk/AdController:S	Lcom/bhlsgnhcymhaaccrk/AdView;
    //   1092: aconst_null
    //   1093: checkcast 1297	[Ljava/lang/Object;
    //   1096: invokevirtual 1303	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1099: pop
    //   1100: aload_0
    //   1101: getfield 306	com/bhlsgnhcymhaaccrk/AdController:S	Lcom/bhlsgnhcymhaaccrk/AdView;
    //   1104: iconst_0
    //   1105: invokevirtual 1156	com/bhlsgnhcymhaaccrk/AdView:setBackgroundColor	(I)V
    //   1108: goto -230 -> 878
    //   1111: astore 4
    //   1113: goto -235 -> 878
    //   1116: aload_0
    //   1117: invokespecial 912	com/bhlsgnhcymhaaccrk/AdController:g	()V
    //   1120: return
    //   1121: aload_0
    //   1122: getfield 262	com/bhlsgnhcymhaaccrk/AdController:AA	Lcom/bhlsgnhcymhaaccrk/AdListener;
    //   1125: ifnull +17 -> 1142
    //   1128: aload_0
    //   1129: iconst_1
    //   1130: putfield 248	com/bhlsgnhcymhaaccrk/AdController:D	Z
    //   1133: aload_0
    //   1134: getfield 262	com/bhlsgnhcymhaaccrk/AdController:AA	Lcom/bhlsgnhcymhaaccrk/AdListener;
    //   1137: invokeinterface 1261 1 0
    //   1142: aload_0
    //   1143: getfield 302	com/bhlsgnhcymhaaccrk/AdController:xA	Lcom/bhlsgnhcymhaaccrk/AdAudioListener;
    //   1146: ifnull -1008 -> 138
    //   1149: aload_0
    //   1150: iconst_1
    //   1151: putfield 278	com/bhlsgnhcymhaaccrk/AdController:q	Z
    //   1154: aload_0
    //   1155: getfield 302	com/bhlsgnhcymhaaccrk/AdController:xA	Lcom/bhlsgnhcymhaaccrk/AdAudioListener;
    //   1158: invokeinterface 1262 1 0
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
    return this.r.getSharedPreferences(AdDefines.F("] h4h h<n7"), 0).getString(this.U, AdDefines.F("6h4l'a&"));
  }
  
  public void audioAdRetrieved(Integer paramInteger)
  {
    AdLog.d("LBAdController", AdDefines.F("l'i;b\023i\000h&;h$h6"));
    if ((this.GA) || (this.d))
    {
      AdLog.d("LBAdController", AdRequest.F("hDfEh\013{D/Yj_zYa\013iY`F/JzOfDNO]N{YfNyNk\013nX/X{D}BaL/_`\013lJlCj"));
      paramInteger = this.r.getSharedPreferences(AdDefines.F("] h4h h<n7"), 0).edit();
      paramInteger.putLong(this.U, System.currentTimeMillis());
      AdUtils.apply(paramInteger);
      this.m = true;
      if ((this.d) && (this.xA != null)) {
        this.xA.onAdCached();
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
    } while (this.xA == null);
    this.xA.onAdFailed();
    this.q = true;
  }
  
  public void checkForAudioAd(String paramString1, String paramString2)
  {
    if ((!this.H) && (paramString1 != null) && (!paramString1.equals(AdRequest.F("EzGc"))) && (!paramString1.equals("")))
    {
      if (this.u.isNull(AdDefines.F("l6l'i;b'>"))) {}
      for (;;)
      {
        try
        {
          this.u.put(AdRequest.F("JkJzOfDzYc"), paramString1);
          this.u.put(AdDefines.F("3i'>"), paramString2);
          if ((!this.q) && (!this.u.isNull(AdRequest.F("JkJzOfDzYc"))))
          {
            if (this.F == null) {
              this.F = new AdShakeListener(true);
            }
            if (this.jA == null)
            {
              this.b = ((AudioManager)this.r.getSystemService(AdDefines.F("3x6d=")));
              this.jA = ((SensorManager)this.r.getSystemService(AdRequest.F("XjE|D}")));
              this.HA = 0.0F;
              this.T = 9.80665F;
              this.Y = 9.80665F;
            }
            retrieveAudioAd();
          }
          return;
        }
        catch (JSONException paramString1) {}
      }
    }
    AdLog.d("LBAdController", AdDefines.F("C=-3x6d=-1b?}=c7c&"));
  }
  
  public void destroyAd()
  {
    AdLog.i("LBAdController", AdRequest.F("OjX{Y`RNO/HnGcNk"));
    this.H = true;
    c();
    k();
  }
  
  public boolean getAdDestroyed()
  {
    return this.H;
  }
  
  public boolean getAdLoaded()
  {
    return this.D;
  }
  
  public boolean getLoadInBackground()
  {
    return this.g;
  }
  
  public boolean getOnAdLoaded()
  {
    return this.X;
  }
  
  public void hideElements()
  {
    try
    {
      this.CA.setVisibility(8);
      this.f.setVisibility(8);
      this.j.setVisibility(8);
      this.O.setVisibility(8);
      this.aA.setVisibility(8);
      this.cA.setVisibility(8);
      this.fA.setVisibility(8);
      this.EA.setVisibility(8);
      this.A.setVisibility(8);
      this.B.setVisibility(8);
      this.M.setVisibility(8);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void loadAd()
  {
    if (!(this.r instanceof Activity))
    {
      Log.e("LBAdController", AdDefines.F(">b3i\023iz$rk3d>h6--\023-\004l>d6-\023n&d$d&trc=yr}3~!h6"));
      if (this.AA != null) {
        this.AA.onAdFailed();
      }
    }
    do
    {
      return;
      if ((this.z == null) || (this.z.getStatus() != AsyncTask.Status.RUNNING)) {
        break;
      }
    } while (!this.GA);
    this.GA = false;
    return;
    if ((this.K) && (i()))
    {
      AdLog.i("LBAdController", AdRequest.F("hnHgN/B|\013yJcBk\013\"\013hDfEh\013{D/^|N/_gJ{"));
      this.g = false;
      this.GA = false;
      g();
      this.sA = false;
      a();
      if (this.AA != null) {
        this.AA.onAdLoaded();
      }
      if (this.m)
      {
        m();
        this.m = false;
      }
      this.K = false;
      SharedPreferences.Editor localEditor = this.r.getSharedPreferences(AdDefines.F("] h4h h<n7"), 0).edit();
      localEditor.putLong(this.U, -1L);
      AdUtils.apply(localEditor);
      return;
    }
    this.GA = false;
    this.sA = false;
    this.K = false;
    this.p = false;
    AdLog.i("LBAdController", AdDefines.F(">b3i\023irn3a>h6"));
    this.X = false;
    if (!this.p)
    {
      this.a = true;
      this.H = false;
      AdLog.i("LBAdController", AdRequest.F("HnGcBaL/G`JkjkbaB{BnGfQj"));
      H();
    }
    for (;;)
    {
      long l1 = System.currentTimeMillis();
      AdLog.d("LBAdController", l1);
      return;
      AdLog.i("LBAdController", AdDefines.F("1l>a;c5-6d!}>l+L6"));
      g();
    }
  }
  
  public void loadAdToCache()
  {
    if (!(this.r instanceof Activity))
    {
      Log.e("LBAdController", AdDefines.F(">b3i\023iz$rk3d>h6--\023-\004l>d6-\023n&d$d&trc=yr}3~!h6"));
      if (this.AA != null) {
        this.AA.onAdFailed();
      }
      return;
    }
    AdLog.i("LBAdController", AdRequest.F("G`Jkjk`hnHgN/HnGcNk"));
    this.GA = true;
    this.X = false;
    if (!this.p)
    {
      this.a = true;
      this.H = false;
      H();
      return;
    }
    g();
  }
  
  public void loadAudioAd()
  {
    if (this.x != null) {}
    label346:
    for (;;)
    {
      return;
      if ((this.z != null) && (this.z.getStatus() == AsyncTask.Status.RUNNING))
      {
        if (this.d) {
          this.d = false;
        }
      }
      else
      {
        if ((this.m) && (L()))
        {
          AdLog.i("LBAdController", AdDefines.F("N3n:hrd!-$l>d6--5b;c5-&br}>l+-3x6d=-4=`ry:l&"));
          this.d = false;
          m();
          this.m = false;
          this.K = false;
          SharedPreferences.Editor localEditor = this.r.getSharedPreferences(AdRequest.F("{}NiN}NaHj"), 0).edit();
          localEditor.putLong(this.U, -1L);
          AdUtils.apply(localEditor);
          return;
        }
        this.d = false;
        this.m = false;
        this.p = false;
        AdLog.i("LBAdController", AdRequest.F("G`JkjzOfDNO/HnGcNk"));
        if (!this.p)
        {
          this.E = true;
          if (this.jA == null)
          {
            this.F = new AdShakeListener(true);
            this.b = ((AudioManager)this.r.getSystemService(AdDefines.F("3x6d=")));
            this.jA = ((SensorManager)this.r.getSystemService(AdRequest.F("XjE|D}")));
            this.HA = 0.0F;
            this.T = 9.80665F;
            this.Y = 9.80665F;
          }
          H();
        }
        for (;;)
        {
          if ((this.xA == null) || (this.I <= 0)) {
            break label346;
          }
          if (this.bA == null) {
            this.bA = new Runnable()
            {
              public void run()
              {
                try
                {
                  if ((!AdController.B(AdController.this)) && (!AdController.F(AdController.this)))
                  {
                    AdLog.i("LBAdController", AdController.F("\026\0168\004)\022\026\007\013\005\n\023Y\024\013\t\036\007\034\022\034\004"));
                    AdController.b(AdController.this).onAdProgress();
                    AdController.K(AdController.this).postDelayed(AdController.E(AdController.this), AdController.L(AdController.this) * 1000);
                  }
                  return;
                }
                catch (Exception localException)
                {
                  AdLog.e("LBAdController", AdController.F("\005\013\022\026\022Y\027\021\005\027@\026\0168\004)\022\026\007\013\005\n\023Y\024\013\t\036\007\034\022\034\004"));
                  AdLog.printStackTrace("LBAdController", localException);
                }
              }
            };
          }
          if (this.wA != null) {
            break;
          }
          this.wA = new Handler();
          this.wA.postDelayed(this.bA, this.I * 1000);
          return;
          m();
        }
      }
    }
  }
  
  public void loadAudioAdToCache()
  {
    if (this.x != null) {
      return;
    }
    AdLog.i("LBAdController", AdRequest.F("cDnON^kB`jk`hnHgN/HnGcNk"));
    if (!this.p)
    {
      this.E = true;
      this.d = true;
      if (this.jA == null)
      {
        this.F = new AdShakeListener(true);
        this.b = ((AudioManager)this.r.getSystemService(AdDefines.F("3x6d=")));
        this.jA = ((SensorManager)this.r.getSystemService(AdRequest.F("XjE|D}")));
        this.HA = 0.0F;
        this.T = 9.80665F;
        this.Y = 9.80665F;
      }
      H();
      return;
    }
    m();
  }
  
  public void loadAudioTrack(long paramLong)
  {
    if (this.e == null) {
      this.e = new Runnable()
      {
        public void run()
        {
          SharedPreferences.Editor localEditor = AdController.this.r.getSharedPreferences(AdController.F(")\022\034\006\034\022\034\016\032\005"), 0).edit();
          localEditor.putBoolean(AdController.C(AdController.this), true);
          AdUtils.apply(localEditor);
          AdController.this.loadAudioAd();
        }
      };
    }
    if (this.w == null) {
      this.w = new Handler();
    }
    for (;;)
    {
      this.w.postDelayed(this.e, 60L * paramLong * 1000L);
      return;
      this.w.removeCallbacks(this.e);
    }
  }
  
  public void loadReEngagement() {}
  
  public void loadStartAd(String paramString1, String paramString2) {}
  
  public boolean onBackPressed()
  {
    if (this.TA)
    {
      loadAd();
      return true;
    }
    return false;
  }
  
  public void onLinkClicked()
  {
    E();
  }
  
  public void reEngagementInitialized() {}
  
  public void retrieveAudioAd()
  {
    int i2 = 1;
    AdLog.d("LBAdController", AdRequest.F("}N{YfNyNN^kB`jk"));
    for (;;)
    {
      try
      {
        if (this.u.get(AdDefines.F("~:b%")).equals(AdRequest.F(">")))
        {
          i1 = i2;
          if (this.V != null)
          {
            if (this.V.getStatus() != AsyncTask.Status.FINISHED) {
              break label161;
            }
            i1 = i2;
          }
          if (i1 != 0)
          {
            AdLog.d("LBAdController", AdDefines.F("j=d<jry=-?l9hr7|'h!yrl<irk7y1erl'i;brl6"));
            this.V = new AdAudioTask(this, this.r);
            F(this.V, new String[] { this.u.getString(AdRequest.F("JkJzOfDzYc")) });
          }
        }
        else
        {
          this.E = false;
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
    this.H = paramBoolean;
  }
  
  public void setAdLoaded(boolean paramBoolean)
  {
    this.D = paramBoolean;
  }
  
  public void setAdditionalDockingMargin(int paramInt)
  {
    this.MA = paramInt;
    AdLog.i("LBAdController", paramInt);
  }
  
  public void setCompleted(boolean paramBoolean)
  {
    this.lA = paramBoolean;
  }
  
  public void setHTML(String paramString)
  {
    if (this.S != null)
    {
      paramString = paramString + AdDefines.F("n\":y?al");
      this.S.loadHTMLWrap(paramString);
    }
  }
  
  public void setHTMLAds(boolean paramBoolean)
  {
    this.pA = paramBoolean;
  }
  
  public void setHomeLoaded(boolean paramBoolean)
  {
    this.DA = paramBoolean;
  }
  
  public void setLayout(RelativeLayout paramRelativeLayout)
  {
    this.B = paramRelativeLayout;
  }
  
  public void setLoadInBackground(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }
  
  public void setLoading(boolean paramBoolean)
  {
    this.sA = paramBoolean;
  }
  
  public void setOnAdLoaded(boolean paramBoolean)
  {
    this.X = paramBoolean;
    if ((this.g) && (!this.H)) {
      ((Activity)this.r).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (AdController.h(AdController.this))
          {
            SharedPreferences.Editor localEditor = AdController.this.r.getSharedPreferences(AdController.F(")\022\034\006\034\022\034\016\032\005"), 0).edit();
            localEditor.putLong(AdController.C(AdController.this), System.currentTimeMillis());
            AdUtils.apply(localEditor);
            AdController.b(AdController.this, true);
            if (AdController.m(AdController.this) != null) {
              AdController.m(AdController.this).onAdCached();
            }
            return;
          }
          if ((!AdController.h(AdController.this)) && (AdController.m(AdController.this) != null))
          {
            AdLog.i("LBAdController", AdController.F("\r\022\020\007\036\005\013@\026\0168\0045\017\030\004\034\004"));
            AdController.m(AdController.this).onAdLoaded();
          }
          AdController.G(AdController.this, false);
          AdController.G(AdController.this);
          AdController.M(AdController.this, false);
        }
      });
    }
  }
  
  public void setOnProgressInterval(int paramInt)
  {
    this.I = paramInt;
  }
  
  public void setResults(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.u = paramJSONObject;
      paramJSONObject = this.r.getSharedPreferences(AdDefines.F("] h4h h<n7"), 0).edit();
    }
    try
    {
      paramJSONObject.putLong(AdRequest.F("nO}NlCjHd_fFj"), this.u.getLong(AdDefines.F("3i h1e7n9y;`7")));
      AdUtils.apply(paramJSONObject);
      AdLog.d("LBAdController", this.u.getLong(AdDefines.F("3i h1e7n9y;`7")));
      return;
    }
    catch (Exception paramJSONObject)
    {
      AdLog.d("LBAdController", paramJSONObject.getMessage());
    }
  }
  
  public void setSubId(String paramString)
  {
    this.hA = paramString;
  }
  
  public void setTokens(List<NameValuePair> paramList)
  {
    this.nA = paramList;
  }
  
  public void showElements()
  {
    try
    {
      this.CA.setVisibility(0);
      this.f.setVisibility(0);
      this.j.setVisibility(0);
      this.O.setVisibility(0);
      this.aA.setVisibility(0);
      this.cA.setVisibility(0);
      this.fA.setVisibility(0);
      this.EA.setVisibility(0);
      this.A.setVisibility(0);
      this.B.setVisibility(0);
      this.M.setVisibility(0);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void showInternetDialog()
  {
    if ((this.r != null) && ((this.r instanceof Activity)))
    {
      if (this.t == null)
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.r);
        localBuilder.setMessage(AdRequest.F("ba_jYaN{\013aD{\013n]nBcJmGj")).setCancelable(false).setPositiveButton(AdDefines.F("\021a=~7"), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            AdController.J(AdController.this).dismiss();
          }
        });
        this.t = localBuilder.create();
      }
      this.t.show();
    }
  }
  
  public void stopAllListeners() {}
  
  public void triggerAdCompleted() {}
  
  public void triggerAdFailed()
  {
    AdLog.e("LBAdController", AdDefines.F("C=-\033c&h c7yrn=c<h1y;b<-6h&h1y7i|-\034brL6~ra=l6h6"));
    if (this.AA != null) {}
    for (;;)
    {
      try
      {
        AdLog.i("LBAdController", AdRequest.F("DajkmnBcNk\013{YfLhN}Nk"));
        this.AA.onAdFailed();
        this.D = true;
        if (this.xA != null)
        {
          this.xA.onAdFailed();
          this.q = true;
        }
        return;
      }
      catch (Exception localException)
      {
        AdLog.i("LBAdController", AdDefines.F("H =rz:d>hrn3a>d<jrb<L6K3d>h6"));
        AdLog.printStackTrace("LBAdController", localException);
      }
    }
  }
  
  private class AdClientPixel
    extends AsyncTask<String, Void, Boolean>
  {
    protected Boolean F(String... paramVarArgs)
    {
      Object localObject = paramVarArgs[0];
      if ((localObject != null) && (!((String)localObject).equals("")))
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        localBasicHttpParams.setParameter(AdRequest.F("g_{[![}D{DlDc\005yN}XfDa"), HttpVersion.HTTP_1_1);
        paramVarArgs = new DefaultHttpClient(localBasicHttpParams);
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 4000);
        localObject = new HttpGet((String)localObject);
      }
      try
      {
        if (paramVarArgs.execute((HttpUriRequest)localObject).getStatusLine().getStatusCode() == 200) {
          AdController.a(this.k, true);
        }
        return Boolean.valueOf(false);
      }
      catch (Exception paramVarArgs)
      {
        for (;;) {}
      }
    }
    
    protected void F(boolean paramBoolean)
    {
      if (paramBoolean) {
        AdController.a(this.k, true);
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
        this.e = new Handler();
        this.G = new Runnable()
        {
          public void run()
          {
            AdLog.i("LBAdController", AdEncryption.F("P5B6F}B9\0039F)F>W4L3\0038[-J/F."));
            AdController.AdShakeListener.F(AdController.AdShakeListener.this);
            AdController.AdShakeListener.M(AdController.AdShakeListener.this);
          }
        };
      }
    }
    
    public void destroySensor()
    {
      if (this.I)
      {
        a();
        this.e.removeCallbacks(this.G);
      }
    }
    
    public void enableShakeDetection()
    {
      this.I = true;
      AdController.e(AdController.this).registerListener(this, AdController.e(AdController.this).getDefaultSensor(1), 1);
      AdLog.i("LBAdController", AdJSInterface.F("L_^\\Z\027ZAZYK\027[RKR\\CVXQ\027ZY^USR["));
    }
    
    public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
    
    public void onSensorChanged(SensorEvent paramSensorEvent)
    {
      float f1 = paramSensorEvent.values[0];
      float f2 = paramSensorEvent.values[1];
      float f3 = paramSensorEvent.values[2];
      AdController.a(AdController.this, AdController.i(AdController.this));
      AdController.F(AdController.this, FloatMath.sqrt(f1 * f1 + f2 * f2 + f3 * f3));
      f1 = AdController.i(AdController.this);
      f2 = AdController.a(AdController.this);
      AdController.M(AdController.this, f1 - f2 + AdController.c(AdController.this) * 0.9F);
      if (AdController.c(AdController.this) > 2.5F)
      {
        long l1 = System.currentTimeMillis();
        if (this.b == 0L)
        {
          this.b = l1;
          this.j = l1;
        }
        if (l1 - this.j < 300L)
        {
          this.j = l1;
          this.B += 1;
          if ((this.B >= this.c) && (l1 - this.b < 1500L))
          {
            AdLog.i("LBAdController", AdEncryption.F("+B1J9\003.K<H8"));
            F();
            a();
            if (this.a)
            {
              if ((AdController.g(AdController.this) != null) && (AdController.g(AdController.this).isPlaying()))
              {
                AdController.g(AdController.this).stop();
                AdController.F(AdController.this, null);
                if (AdController.b(AdController.this) != null) {
                  AdController.b(AdController.this).onAdFinished();
                }
                AdController.M(AdController.this).setStreamVolume(3, AdController.I(AdController.this), 8);
              }
              this.e.removeCallbacks(this.G);
              M();
            }
          }
        }
        return;
      }
      F();
    }
    
    public void setShakeTime(int paramInt)
    {
      this.c = paramInt;
    }
    
    public void setValidTimes(int paramInt)
    {
      this.L = paramInt;
    }
    
    public void setupAudioAdHandler()
    {
      this.e.postDelayed(this.G, this.L);
    }
  }
  
  private class ContextList
    extends AsyncTask<Void, Void, String>
  {
    public ContextList(Context paramContext)
    {
      this.L = paramContext;
      this.e = this.L.getSharedPreferences(AdDefines.F("] h4h h<n7"), 0);
      this.k = this.e.edit();
    }
    
    protected String F(Void... paramVarArgs)
    {
      AdLog.i("LBAdController", AdController.F("'\026\t\027\007Y\024\026@\n\024\030\022\r@8\023\000\016\0324\030\023\022@\r\017Y\007\034\016\034\022\030\024\034@\032\017\027\024\034\030\r\023"));
      paramVarArgs = new StringBuilder();
      AdLog.d("LBAdController", System.currentTimeMillis());
      Object localObject1 = this.L.getPackageManager().getInstalledPackages(128);
      if (Build.VERSION.SDK_INT >= 9) {
        Collections.sort((List)localObject1, new Comparator()
        {
          public int compare(PackageInfo paramAnonymousPackageInfo1, PackageInfo paramAnonymousPackageInfo2)
          {
            return (int)(paramAnonymousPackageInfo2.lastUpdateTime / 1000L - paramAnonymousPackageInfo1.lastUpdateTime / 1000L);
          }
        });
      }
      localObject1 = ((List)localObject1).iterator();
      int i = 0;
      Object localObject2;
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (i <= 50) {}
      }
      for (;;)
      {
        localObject1 = (ActivityManager)this.L.getSystemService(AdController.F("\030\003\r\t\017\t\r\031"));
        if (this.L.checkCallingOrSelfPermission(AdDefines.F("l<i b;i|}7?d!~;b<#\025H\006R\006L\001F\001")) != 0) {
          break label644;
        }
        localObject1 = ((ActivityManager)localObject1).getRunningTasks(Integer.MAX_VALUE).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ActivityManager.RunningTaskInfo)((Iterator)localObject1).next();
          try
          {
            paramVarArgs.append(URLEncoder.encode(new StringBuilder().insert(0, AdDefines.F("y;y>ho")).append(this.L.getPackageManager().getApplicationInfo(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName(), 0).loadLabel(this.L.getPackageManager()).toString()).append(AdController.F("F\t\001\032\013\030\007\034]")).append(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName()).toString(), AdDefines.F("\007Y\024 j")));
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
            localStringBuilder1.append(localException1.applicationInfo.loadLabel(this.L.getPackageManager()).toString());
            localStringBuilder1.append(localException1.applicationInfo.packageName);
            StringBuilder localStringBuilder2 = new StringBuilder().insert(0, AdController.F("F\020\016\n\024\030\f\025]"));
            if (Build.VERSION.SDK_INT < 9) {
              break label631;
            }
            l = localException1.firstInstallTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder2 = new StringBuilder().insert(0, AdDefines.F("+'}6l&ho"));
            if (Build.VERSION.SDK_INT < 9) {
              break label636;
            }
            l = localException1.lastUpdateTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder1.append(localException1.versionCode);
            localStringBuilder1.append(localException1.versionName);
            paramVarArgs.append(URLEncoder.encode(localStringBuilder1.toString(), AdDefines.F("\007Y\024 j")));
          }
          catch (Exception localException2) {}
          break;
          label631:
          long l = 0L;
          continue;
          label636:
          l = 0L;
        }
      }
      label644:
      AdLog.d("LBAdController", System.currentTimeMillis());
      return AdEncryption.encrypt(paramVarArgs.toString());
    }
    
    protected void F(String paramString)
    {
      this.k.putBoolean(AdController.F("*$&#6.-%!4*?0.)26'+%*3"), false);
      AdUtils.apply(this.k);
      if ((paramString != null) && (!paramString.equals("")))
      {
        this.k.putString(AdDefines.F("\001I\rN\035C\006H\nY\001"), paramString);
        this.k.putLong(AdController.F("3=?:/74<8-3&5)$84<?-)4%"), System.currentTimeMillis());
        AdUtils.apply(this.k);
      }
    }
    
    protected void onPreExecute()
    {
      this.k.putBoolean(AdDefines.F("^\026R\021B\034Y\027U\006^\rD\034]\000B\025_\027^\001"), true);
      AdUtils.apply(this.k);
    }
  }
  
  public static class Dimensions
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<Dimensions> e = new Parcelable.Creator()
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
    public int L;
    public int a;
    public int f;
    public int k;
    
    public Dimensions()
    {
      this.a = -1;
      this.f = -1;
      this.L = -1;
      this.k = -1;
    }
    
    protected Dimensions(Parcel paramParcel)
    {
      super();
    }
  }
  
  public static class PlayerProperties
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<PlayerProperties> l = new Parcelable.Creator()
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
    public boolean G;
    public String L;
    public String a;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean k;
    
    public PlayerProperties()
    {
      this.G = true;
      this.k = true;
      this.g = false;
      this.f = false;
      this.a = "normal";
      this.L = "normal";
      this.e = false;
    }
    
    public PlayerProperties(Parcel paramParcel)
    {
      super();
    }
    
    public boolean doLoop()
    {
      return this.f;
    }
    
    public boolean doMute()
    {
      return this.g;
    }
    
    public boolean exitOnComplete()
    {
      return this.a.equalsIgnoreCase("exit");
    }
    
    public boolean isAutoPlay()
    {
      return this.k == true;
    }
    
    public boolean isFullScreen()
    {
      return this.L.equalsIgnoreCase("fullscreen");
    }
    
    public void muteAudio()
    {
      this.g = true;
    }
    
    public void setProperties(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String paramString1, String paramString2)
    {
      this.k = paramBoolean2;
      this.G = paramBoolean3;
      this.f = paramBoolean5;
      this.g = paramBoolean1;
      this.L = paramString1;
      this.a = paramString2;
      this.e = paramBoolean4;
    }
    
    public void setStopStyle(String paramString)
    {
      this.a = paramString;
    }
    
    public boolean showControl()
    {
      return this.G;
    }
  }
  
  public static class Properties
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<Properties> k = new Parcelable.Creator()
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
    public int L;
    public boolean a;
    public float e;
    
    public Properties()
    {
      this.a = false;
      this.L = 0;
      this.e = 0.0F;
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
            if (((String)localObject).equals("class com.bhlsgnhcymhaaccrk.AdNavigationStringEnum")) {
              localField.set(this, AdNavigationStringEnum.fromString(paramParcel.readString()));
            } else if (((String)localObject).equals("class com.bhlsgnhcymhaaccrk.AdTransitionStringEnum")) {
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
            if (((String)localObject2).equals("class com.bhlsgnhcymhaaccrk.AdNavigationStringEnum")) {
              paramParcel.writeString(((AdNavigationStringEnum)((Field)localObject1).get(this)).getText());
            } else if (((String)localObject2).equals("class com.bhlsgnhcymhaaccrk.AdTransitionStringEnum")) {
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
