package com.jvzsmgumfkqcconsjdc;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.net.HttpURLConnection;
import java.net.URL;
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
  public static final String G = "fullscreen";
  public static final String JA = "LBAdController";
  public static final String MA = "normal";
  public static final String a = "exit";
  protected Context CA;
  protected AdView l;
  
  public AdController(Context paramContext, String paramString)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
  }
  
  public AdController(Context paramContext, String paramString, WebView paramWebView)
  {
    this.CA = paramContext;
    this.BA = paramString;
    this.i = paramWebView;
    this.YA = new RelativeLayout(this.CA);
    M();
  }
  
  public AdController(Context paramContext, String paramString, RelativeLayout paramRelativeLayout)
  {
    this.CA = paramContext;
    this.BA = paramString;
    paramContext = paramRelativeLayout;
    if (paramRelativeLayout == null) {
      paramContext = new RelativeLayout(this.CA);
    }
    this.YA = paramContext;
    this.i = null;
    M();
  }
  
  public AdController(Context paramContext, String paramString, AdAudioListener paramAdAudioListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.RA = paramAdAudioListener;
  }
  
  public AdController(Context paramContext, String paramString, AdListener paramAdListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.k = paramAdListener;
  }
  
  public AdController(AdView paramAdView, Context paramContext)
  {
    this.l = paramAdView;
    this.CA = paramContext;
  }
  
  protected static Object j(JSONObject paramJSONObject, Class<?> paramClass)
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
          boolean bool = str1.startsWith(AdWakeLock.j("k"));
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
          if (!str2.equals("class com.jvzsmgumfkqcconsjdc.AdNavigationStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdNavigationStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
          if (!str2.equals("class com.jvzsmgumfkqcconsjdc.AdTransitionStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdTransitionStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
        }
        try
        {
          if (str1.startsWith(AdDefines.j(";K`")))
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
  
  public static String j(String paramString)
  {
    int i1 = paramString.length();
    char[] arrayOfChar = new char[i1];
    int i2 = i1 - 1;
    for (i1 = i2; i2 >= 0; i1 = i2)
    {
      i2 = paramString.charAt(i1);
      int i3 = i1 - 1;
      arrayOfChar[i1] = ((char)(i2 ^ 0x3E));
      if (i3 < 0) {
        break;
      }
      i2 = i3 - 1;
      arrayOfChar[i3] = ((char)(paramString.charAt(i3) ^ 0x3B));
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
    //   4: putfield 241	com/jvzsmgumfkqcconsjdc/AdController:B	Z
    //   7: aload_0
    //   8: getfield 292	com/jvzsmgumfkqcconsjdc/AdController:CA	Landroid/content/Context;
    //   11: ldc_w 339
    //   14: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   17: iconst_0
    //   18: invokevirtual 345	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   21: astore 4
    //   23: aload 4
    //   25: invokeinterface 900 1 0
    //   30: astore 5
    //   32: aload_0
    //   33: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   36: ifnull +1085 -> 1121
    //   39: aload 5
    //   41: ldc_w 1732
    //   44: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   47: aload_0
    //   48: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   51: ldc_w 1734
    //   54: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   57: invokevirtual 1737	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   60: invokeinterface 1371 4 0
    //   65: pop
    //   66: aload 5
    //   68: invokestatic 919	com/jvzsmgumfkqcconsjdc/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   71: aload_0
    //   72: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   75: ldc_w 1739
    //   78: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   81: invokevirtual 326	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   84: ldc_w 1344
    //   87: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   90: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   93: ifeq +53 -> 146
    //   96: aload_0
    //   97: getfield 263	com/jvzsmgumfkqcconsjdc/AdController:k	Lcom/jvzsmgumfkqcconsjdc/AdListener;
    //   100: ifnull +17 -> 117
    //   103: aload_0
    //   104: iconst_1
    //   105: putfield 249	com/jvzsmgumfkqcconsjdc/AdController:aA	Z
    //   108: aload_0
    //   109: getfield 263	com/jvzsmgumfkqcconsjdc/AdController:k	Lcom/jvzsmgumfkqcconsjdc/AdListener;
    //   112: invokeinterface 1334 1 0
    //   117: aload_0
    //   118: getfield 303	com/jvzsmgumfkqcconsjdc/AdController:RA	Lcom/jvzsmgumfkqcconsjdc/AdAudioListener;
    //   121: ifnull +17 -> 138
    //   124: aload_0
    //   125: iconst_1
    //   126: putfield 279	com/jvzsmgumfkqcconsjdc/AdController:dA	Z
    //   129: aload_0
    //   130: getfield 303	com/jvzsmgumfkqcconsjdc/AdController:RA	Lcom/jvzsmgumfkqcconsjdc/AdAudioListener;
    //   133: invokeinterface 1335 1 0
    //   138: return
    //   139: astore 6
    //   141: goto -70 -> 71
    //   144: astore 6
    //   146: aload_0
    //   147: getfield 292	com/jvzsmgumfkqcconsjdc/AdController:CA	Landroid/content/Context;
    //   150: ifnull +194 -> 344
    //   153: aload_0
    //   154: getfield 292	com/jvzsmgumfkqcconsjdc/AdController:CA	Landroid/content/Context;
    //   157: instanceof 815
    //   160: ifeq +184 -> 344
    //   163: new 1741	android/util/DisplayMetrics
    //   166: dup
    //   167: invokespecial 1742	android/util/DisplayMetrics:<init>	()V
    //   170: astore 6
    //   172: aload_0
    //   173: getfield 292	com/jvzsmgumfkqcconsjdc/AdController:CA	Landroid/content/Context;
    //   176: checkcast 815	android/app/Activity
    //   179: invokevirtual 1746	android/app/Activity:getWindowManager	()Landroid/view/WindowManager;
    //   182: invokeinterface 1752 1 0
    //   187: aload 6
    //   189: invokevirtual 1758	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   192: new 1760	android/graphics/Rect
    //   195: dup
    //   196: invokespecial 1761	android/graphics/Rect:<init>	()V
    //   199: astore 7
    //   201: aload_0
    //   202: getfield 292	com/jvzsmgumfkqcconsjdc/AdController:CA	Landroid/content/Context;
    //   205: checkcast 815	android/app/Activity
    //   208: invokevirtual 1765	android/app/Activity:getWindow	()Landroid/view/Window;
    //   211: astore 8
    //   213: aload 8
    //   215: invokevirtual 1771	android/view/Window:getDecorView	()Landroid/view/View;
    //   218: aload 7
    //   220: invokevirtual 1775	android/view/View:getWindowVisibleDisplayFrame	(Landroid/graphics/Rect;)V
    //   223: aload 7
    //   225: getfield 1778	android/graphics/Rect:top	I
    //   228: istore_3
    //   229: aload 8
    //   231: ldc_w 832
    //   234: invokevirtual 1779	android/view/Window:findViewById	(I)Landroid/view/View;
    //   237: invokevirtual 1782	android/view/View:getTop	()I
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
    //   253: getfield 1785	android/util/DisplayMetrics:widthPixels	I
    //   256: putfield 811	com/jvzsmgumfkqcconsjdc/AdController:bA	I
    //   259: aload_0
    //   260: aload 6
    //   262: getfield 1788	android/util/DisplayMetrics:heightPixels	I
    //   265: iload_3
    //   266: isub
    //   267: iload_1
    //   268: isub
    //   269: putfield 813	com/jvzsmgumfkqcconsjdc/AdController:y	I
    //   272: ldc 83
    //   274: new 424	java/lang/StringBuilder
    //   277: dup
    //   278: invokespecial 425	java/lang/StringBuilder:<init>	()V
    //   281: iconst_0
    //   282: ldc_w 1790
    //   285: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   288: invokevirtual 431	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   291: aload 6
    //   293: getfield 1788	android/util/DisplayMetrics:heightPixels	I
    //   296: invokevirtual 477	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   299: ldc_w 1792
    //   302: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   305: invokevirtual 435	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: iload_3
    //   309: invokevirtual 477	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   312: ldc_w 1794
    //   315: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   318: invokevirtual 435	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: iconst_0
    //   322: invokevirtual 477	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   325: ldc_w 1792
    //   328: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   331: invokevirtual 435	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: iload_1
    //   335: invokevirtual 477	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   338: invokevirtual 438	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: invokestatic 443	com/jvzsmgumfkqcconsjdc/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   344: aload_0
    //   345: aload_0
    //   346: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   349: ldc_w 1796
    //   352: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   355: invokevirtual 654	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   358: putfield 255	com/jvzsmgumfkqcconsjdc/AdController:AA	I
    //   361: aload_0
    //   362: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   365: ldc_w 1798
    //   368: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   371: invokevirtual 654	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   374: istore_1
    //   375: aload_0
    //   376: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   379: ldc_w 1800
    //   382: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   385: invokevirtual 654	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   388: istore_3
    //   389: iload_3
    //   390: ifle +191 -> 581
    //   393: aload_0
    //   394: iload_1
    //   395: bipush 60
    //   397: imul
    //   398: iload_3
    //   399: idiv
    //   400: putfield 257	com/jvzsmgumfkqcconsjdc/AdController:lA	I
    //   403: aload_0
    //   404: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   407: ldc_w 1802
    //   410: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   413: invokevirtual 800	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   416: ldc_w 1180
    //   419: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   422: invokevirtual 802	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   425: ifeq +8 -> 433
    //   428: aload_0
    //   429: iconst_1
    //   430: putfield 239	com/jvzsmgumfkqcconsjdc/AdController:F	Z
    //   433: aload 5
    //   435: new 424	java/lang/StringBuilder
    //   438: dup
    //   439: invokespecial 425	java/lang/StringBuilder:<init>	()V
    //   442: iconst_0
    //   443: ldc_w 1804
    //   446: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   449: invokevirtual 431	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   452: aload_0
    //   453: getfield 294	com/jvzsmgumfkqcconsjdc/AdController:BA	Ljava/lang/String;
    //   456: invokevirtual 435	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: invokevirtual 438	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   462: aload_0
    //   463: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   466: ldc_w 1806
    //   469: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   472: invokevirtual 326	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   475: invokeinterface 1377 3 0
    //   480: pop
    //   481: aload 5
    //   483: invokestatic 919	com/jvzsmgumfkqcconsjdc/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   486: aload_0
    //   487: getfield 277	com/jvzsmgumfkqcconsjdc/AdController:P	Z
    //   490: ifeq +229 -> 719
    //   493: aload_0
    //   494: invokevirtual 1809	com/jvzsmgumfkqcconsjdc/AdController:retrieveAudioAd	()V
    //   497: aload 4
    //   499: new 424	java/lang/StringBuilder
    //   502: dup
    //   503: invokespecial 425	java/lang/StringBuilder:<init>	()V
    //   506: iconst_0
    //   507: ldc_w 1811
    //   510: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   513: invokevirtual 431	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   516: aload_0
    //   517: getfield 294	com/jvzsmgumfkqcconsjdc/AdController:BA	Ljava/lang/String;
    //   520: invokevirtual 435	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: invokevirtual 438	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   526: iconst_0
    //   527: invokeinterface 1115 3 0
    //   532: ifeq -394 -> 138
    //   535: aload_0
    //   536: getfield 921	com/jvzsmgumfkqcconsjdc/AdController:mA	Landroid/os/Handler;
    //   539: ifnull -401 -> 138
    //   542: aload_0
    //   543: getfield 921	com/jvzsmgumfkqcconsjdc/AdController:mA	Landroid/os/Handler;
    //   546: aload_0
    //   547: getfield 923	com/jvzsmgumfkqcconsjdc/AdController:UA	Ljava/lang/Runnable;
    //   550: aload_0
    //   551: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   554: ldc_w 1813
    //   557: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   560: invokevirtual 654	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   563: sipush 1000
    //   566: imul
    //   567: i2l
    //   568: invokevirtual 1817	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   571: pop
    //   572: return
    //   573: astore 4
    //   575: return
    //   576: iconst_0
    //   577: istore_1
    //   578: goto -328 -> 250
    //   581: aload_0
    //   582: bipush 10
    //   584: putfield 257	com/jvzsmgumfkqcconsjdc/AdController:lA	I
    //   587: goto -184 -> 403
    //   590: astore 6
    //   592: aload_0
    //   593: sipush 500
    //   596: putfield 255	com/jvzsmgumfkqcconsjdc/AdController:AA	I
    //   599: aload_0
    //   600: bipush 10
    //   602: putfield 257	com/jvzsmgumfkqcconsjdc/AdController:lA	I
    //   605: goto -202 -> 403
    //   608: astore 6
    //   610: aload_0
    //   611: getfield 1819	com/jvzsmgumfkqcconsjdc/AdController:n	Ljava/lang/String;
    //   614: ifnull +61 -> 675
    //   617: aload_0
    //   618: getfield 1819	com/jvzsmgumfkqcconsjdc/AdController:n	Ljava/lang/String;
    //   621: ldc_w 1344
    //   624: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   627: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   630: ifne +45 -> 675
    //   633: aload 5
    //   635: new 424	java/lang/StringBuilder
    //   638: dup
    //   639: invokespecial 425	java/lang/StringBuilder:<init>	()V
    //   642: iconst_0
    //   643: ldc_w 1821
    //   646: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   649: invokevirtual 431	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   652: aload_0
    //   653: getfield 294	com/jvzsmgumfkqcconsjdc/AdController:BA	Ljava/lang/String;
    //   656: invokevirtual 435	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   659: invokevirtual 438	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   662: aload_0
    //   663: getfield 1819	com/jvzsmgumfkqcconsjdc/AdController:n	Ljava/lang/String;
    //   666: invokeinterface 1377 3 0
    //   671: pop
    //   672: goto -191 -> 481
    //   675: aload 5
    //   677: new 424	java/lang/StringBuilder
    //   680: dup
    //   681: invokespecial 425	java/lang/StringBuilder:<init>	()V
    //   684: iconst_0
    //   685: ldc_w 1804
    //   688: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   691: invokevirtual 431	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   694: aload_0
    //   695: getfield 294	com/jvzsmgumfkqcconsjdc/AdController:BA	Ljava/lang/String;
    //   698: invokevirtual 435	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   701: invokevirtual 438	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   704: ldc_w 328
    //   707: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   710: invokeinterface 1377 3 0
    //   715: pop
    //   716: goto -235 -> 481
    //   719: aload_0
    //   720: new 675	com/jvzsmgumfkqcconsjdc/AdWebView
    //   723: dup
    //   724: aload_0
    //   725: getfield 292	com/jvzsmgumfkqcconsjdc/AdController:CA	Landroid/content/Context;
    //   728: checkcast 815	android/app/Activity
    //   731: aload_0
    //   732: aload_0
    //   733: getfield 239	com/jvzsmgumfkqcconsjdc/AdController:F	Z
    //   736: aload_0
    //   737: getfield 263	com/jvzsmgumfkqcconsjdc/AdController:k	Lcom/jvzsmgumfkqcconsjdc/AdListener;
    //   740: aload_0
    //   741: getfield 298	com/jvzsmgumfkqcconsjdc/AdController:YA	Landroid/widget/RelativeLayout;
    //   744: invokespecial 1824	com/jvzsmgumfkqcconsjdc/AdWebView:<init>	(Landroid/content/Context;Lcom/jvzsmgumfkqcconsjdc/AdController;ZLcom/jvzsmgumfkqcconsjdc/AdListener;Landroid/widget/RelativeLayout;)V
    //   747: putfield 623	com/jvzsmgumfkqcconsjdc/AdController:M	Lcom/jvzsmgumfkqcconsjdc/AdWebView;
    //   750: ldc 83
    //   752: ldc_w 1826
    //   755: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   758: invokestatic 443	com/jvzsmgumfkqcconsjdc/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   761: ldc_w 1457
    //   764: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   767: invokestatic 422	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   770: ldc_w 1828
    //   773: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   776: aconst_null
    //   777: checkcast 1461	[Ljava/lang/Class;
    //   780: invokevirtual 1465	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   783: aload_0
    //   784: getfield 623	com/jvzsmgumfkqcconsjdc/AdController:M	Lcom/jvzsmgumfkqcconsjdc/AdWebView;
    //   787: aconst_null
    //   788: checkcast 1467	[Ljava/lang/Object;
    //   791: invokevirtual 1473	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   794: pop
    //   795: aload_0
    //   796: getfield 623	com/jvzsmgumfkqcconsjdc/AdController:M	Lcom/jvzsmgumfkqcconsjdc/AdWebView;
    //   799: iconst_0
    //   800: invokevirtual 679	com/jvzsmgumfkqcconsjdc/AdWebView:setBackgroundColor	(I)V
    //   803: aload_0
    //   804: getfield 623	com/jvzsmgumfkqcconsjdc/AdController:M	Lcom/jvzsmgumfkqcconsjdc/AdWebView;
    //   807: aload_0
    //   808: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   811: invokevirtual 1832	com/jvzsmgumfkqcconsjdc/AdWebView:setResults	(Lorg/json/JSONObject;)V
    //   814: aload_0
    //   815: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   818: ldc_w 1834
    //   821: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   824: invokevirtual 654	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   827: istore_1
    //   828: iload_1
    //   829: iconst_1
    //   830: if_icmpne +18 -> 848
    //   833: aload_0
    //   834: getfield 623	com/jvzsmgumfkqcconsjdc/AdController:M	Lcom/jvzsmgumfkqcconsjdc/AdWebView;
    //   837: new 26	com/jvzsmgumfkqcconsjdc/AdController$3
    //   840: dup
    //   841: aload_0
    //   842: invokespecial 1835	com/jvzsmgumfkqcconsjdc/AdController$3:<init>	(Lcom/jvzsmgumfkqcconsjdc/AdController;)V
    //   845: invokevirtual 1839	com/jvzsmgumfkqcconsjdc/AdWebView:setOnKeyListener	(Landroid/view/View$OnKeyListener;)V
    //   848: aload_0
    //   849: getfield 307	com/jvzsmgumfkqcconsjdc/AdController:l	Lcom/jvzsmgumfkqcconsjdc/AdView;
    //   852: ifnonnull +214 -> 1066
    //   855: aload_0
    //   856: new 1065	com/jvzsmgumfkqcconsjdc/AdView
    //   859: dup
    //   860: aload_0
    //   861: getfield 292	com/jvzsmgumfkqcconsjdc/AdController:CA	Landroid/content/Context;
    //   864: checkcast 815	android/app/Activity
    //   867: aload_0
    //   868: aload_0
    //   869: getfield 263	com/jvzsmgumfkqcconsjdc/AdController:k	Lcom/jvzsmgumfkqcconsjdc/AdListener;
    //   872: invokespecial 1842	com/jvzsmgumfkqcconsjdc/AdView:<init>	(Landroid/content/Context;Lcom/jvzsmgumfkqcconsjdc/AdController;Lcom/jvzsmgumfkqcconsjdc/AdListener;)V
    //   875: putfield 307	com/jvzsmgumfkqcconsjdc/AdController:l	Lcom/jvzsmgumfkqcconsjdc/AdView;
    //   878: aload_0
    //   879: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   882: ldc_w 1844
    //   885: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   888: invokevirtual 654	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   891: iconst_1
    //   892: if_icmpeq -754 -> 138
    //   895: aload_0
    //   896: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   899: ldc_w 1846
    //   902: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   905: invokevirtual 654	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   908: istore_1
    //   909: iload_1
    //   910: ifne -772 -> 138
    //   913: aload_0
    //   914: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   917: ldc_w 1848
    //   920: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   923: invokevirtual 654	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   926: ifle +190 -> 1116
    //   929: aload_0
    //   930: getfield 314	com/jvzsmgumfkqcconsjdc/AdController:v	Lorg/json/JSONObject;
    //   933: ldc_w 1850
    //   936: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   939: invokevirtual 654	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   942: sipush 1000
    //   945: imul
    //   946: istore_1
    //   947: ldc 83
    //   949: new 424	java/lang/StringBuilder
    //   952: dup
    //   953: invokespecial 425	java/lang/StringBuilder:<init>	()V
    //   956: iconst_0
    //   957: ldc_w 1852
    //   960: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   963: invokevirtual 431	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   966: iload_1
    //   967: invokevirtual 477	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   970: ldc_w 1854
    //   973: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   976: invokevirtual 435	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: invokevirtual 438	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   982: invokestatic 610	com/jvzsmgumfkqcconsjdc/AdLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   985: new 925	android/os/Handler
    //   988: dup
    //   989: invokespecial 1855	android/os/Handler:<init>	()V
    //   992: new 28	com/jvzsmgumfkqcconsjdc/AdController$4
    //   995: dup
    //   996: aload_0
    //   997: invokespecial 1856	com/jvzsmgumfkqcconsjdc/AdController$4:<init>	(Lcom/jvzsmgumfkqcconsjdc/AdController;)V
    //   1000: iload_1
    //   1001: i2l
    //   1002: invokevirtual 1817	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   1005: pop
    //   1006: return
    //   1007: astore 4
    //   1009: aload_0
    //   1010: invokespecial 1432	com/jvzsmgumfkqcconsjdc/AdController:c	()V
    //   1013: return
    //   1014: astore 4
    //   1016: aload_0
    //   1017: invokespecial 1432	com/jvzsmgumfkqcconsjdc/AdController:c	()V
    //   1020: return
    //   1021: astore 4
    //   1023: ldc 83
    //   1025: new 424	java/lang/StringBuilder
    //   1028: dup
    //   1029: invokespecial 425	java/lang/StringBuilder:<init>	()V
    //   1032: iconst_0
    //   1033: ldc_w 1858
    //   1036: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   1039: invokevirtual 431	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   1042: aload 4
    //   1044: invokevirtual 577	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1047: invokevirtual 435	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1050: invokevirtual 438	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1053: invokestatic 579	com/jvzsmgumfkqcconsjdc/AdLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1056: ldc 83
    //   1058: aload 4
    //   1060: invokestatic 599	com/jvzsmgumfkqcconsjdc/AdLog:printStackTrace	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   1063: goto -260 -> 803
    //   1066: ldc_w 1457
    //   1069: invokestatic 331	com/jvzsmgumfkqcconsjdc/AdDefines:j	(Ljava/lang/String;)Ljava/lang/String;
    //   1072: invokestatic 422	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1075: ldc_w 1828
    //   1078: invokestatic 321	com/jvzsmgumfkqcconsjdc/AdWakeLock:j	(Ljava/lang/String;)Ljava/lang/String;
    //   1081: aconst_null
    //   1082: checkcast 1461	[Ljava/lang/Class;
    //   1085: invokevirtual 1465	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1088: aload_0
    //   1089: getfield 307	com/jvzsmgumfkqcconsjdc/AdController:l	Lcom/jvzsmgumfkqcconsjdc/AdView;
    //   1092: aconst_null
    //   1093: checkcast 1467	[Ljava/lang/Object;
    //   1096: invokevirtual 1473	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1099: pop
    //   1100: aload_0
    //   1101: getfield 307	com/jvzsmgumfkqcconsjdc/AdController:l	Lcom/jvzsmgumfkqcconsjdc/AdView;
    //   1104: iconst_0
    //   1105: invokevirtual 1219	com/jvzsmgumfkqcconsjdc/AdView:setBackgroundColor	(I)V
    //   1108: goto -230 -> 878
    //   1111: astore 4
    //   1113: goto -235 -> 878
    //   1116: aload_0
    //   1117: invokespecial 1432	com/jvzsmgumfkqcconsjdc/AdController:c	()V
    //   1120: return
    //   1121: aload_0
    //   1122: getfield 263	com/jvzsmgumfkqcconsjdc/AdController:k	Lcom/jvzsmgumfkqcconsjdc/AdListener;
    //   1125: ifnull +17 -> 1142
    //   1128: aload_0
    //   1129: iconst_1
    //   1130: putfield 249	com/jvzsmgumfkqcconsjdc/AdController:aA	Z
    //   1133: aload_0
    //   1134: getfield 263	com/jvzsmgumfkqcconsjdc/AdController:k	Lcom/jvzsmgumfkqcconsjdc/AdListener;
    //   1137: invokeinterface 1334 1 0
    //   1142: aload_0
    //   1143: getfield 303	com/jvzsmgumfkqcconsjdc/AdController:RA	Lcom/jvzsmgumfkqcconsjdc/AdAudioListener;
    //   1146: ifnull -1008 -> 138
    //   1149: aload_0
    //   1150: iconst_1
    //   1151: putfield 279	com/jvzsmgumfkqcconsjdc/AdController:dA	Z
    //   1154: aload_0
    //   1155: getfield 303	com/jvzsmgumfkqcconsjdc/AdController:RA	Lcom/jvzsmgumfkqcconsjdc/AdAudioListener;
    //   1158: invokeinterface 1335 1 0
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
    return this.CA.getSharedPreferences(AdWakeLock.j("&:\023.\023:\023&\025-"), 0).getString(this.BA, AdWakeLock.j(",\023.\027=\032<"));
  }
  
  public void audioAdRetrieved(Integer paramInteger)
  {
    AdLog.d("LBAdController", AdWakeLock.j("\027=\022!\031\t\022\032\023<\004!\023>\023,"));
    if ((this.cA) || (this.KA))
    {
      AdLog.d("LBAdController", AdDefines.j("\024q\025[l\0248\t}\017m\tv[~\tw\0268\032m\037q\024Y\037J\036l\tq\036n\036|[y\b8\bl\024j\022v\0348\017w[{\032{\023}"));
      paramInteger = this.CA.getSharedPreferences(AdWakeLock.j("&:\023.\023:\023&\025-"), 0).edit();
      paramInteger.putLong(this.BA, System.currentTimeMillis());
      AdUtils.apply(paramInteger);
      this.r = true;
      if ((this.KA) && (this.RA != null)) {
        this.RA.onAdCached();
      }
    }
    do
    {
      return;
      if (paramInteger.intValue() == 0)
      {
        G();
        return;
      }
    } while (this.RA == null);
    this.RA.onAdFailed();
    this.dA = true;
  }
  
  public void checkForAudioAd(String paramString1, String paramString2)
  {
    if ((!this.Y) && (paramString1 != null) && (!paramString1.equals(AdWakeLock.j("\030=\032$"))) && (!paramString1.equals("")))
    {
      if (this.v.isNull(AdDefines.j("\032|\032m\037q\024m\tt"))) {}
      for (;;)
      {
        try
        {
          this.v.put(AdWakeLock.j("\027,\027=\022!\031=\004$"), paramString1);
          this.v.put(AdDefines.j("y\037m\tt"), paramString2);
          if ((!this.dA) && (!this.v.isNull(AdWakeLock.j("\027,\027=\022!\031=\004$"))))
          {
            if (this.HA == null) {
              this.HA = new AdShakeListener(true);
            }
            if (this.LA == null)
            {
              this.V = ((AudioManager)this.CA.getSystemService(AdDefines.j("y\016|\022w")));
              this.LA = ((SensorManager)this.CA.getSystemService(AdWakeLock.j("\005-\030;\031:")));
              this.QA = 0.0F;
              this.u = 9.80665F;
              this.E = 9.80665F;
            }
            retrieveAudioAd();
          }
          return;
        }
        catch (JSONException paramString1) {}
      }
    }
    AdLog.d("LBAdController", AdDefines.j("5w[y\016|\022w[{\024u\013w\025}\025l"));
  }
  
  public void destroyAd()
  {
    AdLog.i("LBAdController", AdDefines.j("\037}\bl\tw\002Y\0378\030y\027t\036|"));
    this.Y = true;
    f();
    i();
  }
  
  public boolean getAdDestroyed()
  {
    return this.Y;
  }
  
  public boolean getAdLoaded()
  {
    return this.aA;
  }
  
  public boolean getLoadInBackground()
  {
    return this.H;
  }
  
  public boolean getOnAdLoaded()
  {
    return this.qA;
  }
  
  public void hideElements()
  {
    try
    {
      this.M.setVisibility(8);
      this.t.setVisibility(8);
      this.z.setVisibility(8);
      this.O.setVisibility(8);
      this.W.setVisibility(8);
      this.A.setVisibility(8);
      this.FA.setVisibility(8);
      this.iA.setVisibility(8);
      this.R.setVisibility(8);
      this.YA.setVisibility(8);
      this.jA.setVisibility(8);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void loadAd()
  {
    if (!(this.CA instanceof Activity))
    {
      Log.e("LBAdController", AdDefines.j("t\024y\037Y\0370R8\035y\022t\036|[5[Y[N\032t\022|[Y\030l\022n\022l\0028\025w\0178\013y\bk\036|"));
      if (this.k != null) {
        this.k.onAdFailed();
      }
    }
    do
    {
      return;
      if ((this.Z == null) || (this.Z.getStatus() != AsyncTask.Status.RUNNING)) {
        break;
      }
    } while (!this.cA);
    this.cA = false;
    return;
    if ((this.s) && (g()))
    {
      AdLog.i("LBAdController", AdWakeLock.j("5)\025 \023h\037;V>\027$\037,VeV/\031!\030/V<\031h\003;\023h\002 \027<"));
      this.H = false;
      this.cA = false;
      c();
      this.EA = false;
      h();
      if (this.k != null) {
        this.k.onAdLoaded();
      }
      if (this.r)
      {
        G();
        this.r = false;
      }
      this.s = false;
      SharedPreferences.Editor localEditor = this.CA.getSharedPreferences(AdDefines.j("+j\036~\036j\036v\030}"), 0).edit();
      localEditor.putLong(this.BA, -1L);
      AdUtils.apply(localEditor);
      return;
    }
    this.cA = false;
    this.EA = false;
    this.s = false;
    this.B = false;
    AdLog.i("LBAdController", AdDefines.j("t\024y\037Y\0378\030y\027t\036|"));
    this.qA = false;
    if (!this.B)
    {
      this.c = true;
      this.Y = false;
      AdLog.i("LBAdController", AdWakeLock.j("\025)\032$\037&\021h\032'\027,7,?&\037<\037)\032!\f-"));
      a();
    }
    for (;;)
    {
      long l1 = System.currentTimeMillis();
      AdLog.d("LBAdController", l1);
      return;
      AdLog.i("LBAdController", AdDefines.j("{\032t\027q\025[|\022k\013t\032a:|"));
      c();
    }
  }
  
  public void loadAdToCache()
  {
    if (!(this.CA instanceof Activity))
    {
      Log.e("LBAdController", AdWakeLock.j("$\031)\022\t\022`_h\020)\037$\023,VeV\tV\036\027$\037,V\t\025<\037>\037<\017h\030'\002h\006)\005;\023,"));
      if (this.k != null) {
        this.k.onAdFailed();
      }
      return;
    }
    AdLog.i("LBAdController", AdDefines.j("\027w\032|:|/w8y\030p\0368\030y\027t\036|"));
    this.cA = true;
    this.qA = false;
    if (!this.B)
    {
      this.c = true;
      this.Y = false;
      a();
      return;
    }
    c();
  }
  
  public void loadAudioAd()
  {
    if (this.m != null) {}
    label346:
    for (;;)
    {
      return;
      if ((this.Z != null) && (this.Z.getStatus() == AsyncTask.Status.RUNNING))
      {
        if (this.KA) {
          this.KA = false;
        }
      }
      else
      {
        if ((this.r) && (J()))
        {
          AdLog.i("LBAdController", AdWakeLock.j("5)\025 \023h\037;V>\027$\037,VeV/\031!\030/V<\031h\006$\0271V)\003,\037'V.\004'\033h\002 \027<"));
          this.KA = false;
          G();
          this.r = false;
          this.s = false;
          SharedPreferences.Editor localEditor = this.CA.getSharedPreferences(AdDefines.j("+j\036~\036j\036v\030}"), 0).edit();
          localEditor.putLong(this.BA, -1L);
          AdUtils.apply(localEditor);
          return;
        }
        this.KA = false;
        this.r = false;
        this.B = false;
        AdLog.i("LBAdController", AdDefines.j("\027w\032|:m\037q\024Y\0378\030y\027t\036|"));
        if (!this.B)
        {
          this.P = true;
          if (this.LA == null)
          {
            this.HA = new AdShakeListener(true);
            this.V = ((AudioManager)this.CA.getSystemService(AdWakeLock.j(")\003,\037'")));
            this.LA = ((SensorManager)this.CA.getSystemService(AdDefines.j("\b}\025k\024j")));
            this.QA = 0.0F;
            this.u = 9.80665F;
            this.E = 9.80665F;
          }
          a();
        }
        for (;;)
        {
          if ((this.RA == null) || (this.Q <= 0)) {
            break label346;
          }
          if (this.J == null) {
            this.J = new Runnable()
            {
              public void run()
              {
                try
                {
                  if ((!AdController.g(AdController.this)) && (!AdController.F(AdController.this)))
                  {
                    AdLog.i("LBAdController", AdRequest.j("D}jw{aDtYvX`\013gYzLtNaNw"));
                    AdController.B(AdController.this).onAdProgress();
                    AdController.i(AdController.this).postDelayed(AdController.G(AdController.this), AdController.E(AdController.this) * 1000);
                  }
                  return;
                }
                catch (Exception localException)
                {
                  AdLog.e("LBAdController", AdJSInterface.j("H+_6_yZ1H7\r6C\030I\t_6J+H*^yY+D>J<_<I"));
                  AdLog.printStackTrace("LBAdController", localException);
                }
              }
            };
          }
          if (this.kA != null) {
            break;
          }
          this.kA = new Handler();
          this.kA.postDelayed(this.J, this.Q * 1000);
          return;
          G();
        }
      }
    }
  }
  
  public void loadAudioAdToCache()
  {
    if (this.m != null) {
      return;
    }
    AdLog.i("LBAdController", AdDefines.j("t\024y\037Y\016|\022w:|/w8y\030p\0368\030y\027t\036|"));
    if (!this.B)
    {
      this.P = true;
      this.KA = true;
      if (this.LA == null)
      {
        this.HA = new AdShakeListener(true);
        this.V = ((AudioManager)this.CA.getSystemService(AdWakeLock.j(")\003,\037'")));
        this.LA = ((SensorManager)this.CA.getSystemService(AdDefines.j("\b}\025k\024j")));
        this.QA = 0.0F;
        this.u = 9.80665F;
        this.E = 9.80665F;
      }
      a();
      return;
    }
    G();
  }
  
  public void loadAudioTrack(long paramLong)
  {
    if (this.UA == null) {
      this.UA = new Runnable()
      {
        public void run()
        {
          SharedPreferences.Editor localEditor = AdController.this.CA.getSharedPreferences(AdDefines.j("+j\036~\036j\036v\030}"), 0).edit();
          localEditor.putBoolean(AdController.H(AdController.this), true);
          AdUtils.apply(localEditor);
          AdController.this.loadAudioAd();
        }
      };
    }
    if (this.mA == null) {
      this.mA = new Handler();
    }
    for (;;)
    {
      this.mA.postDelayed(this.UA, 60L * paramLong * 1000L);
      return;
      this.mA.removeCallbacks(this.UA);
    }
  }
  
  public void loadReEngagement()
  {
    int i1;
    if (this.Z != null)
    {
      AdLog.i("LBAdController", this.Z.getStatus());
      if (this.Z.getStatus() == AsyncTask.Status.FINISHED) {
        i1 = 1;
      }
    }
    for (;;)
    {
      if (i1 != 0)
      {
        Object localObject = this.CA.getSharedPreferences(AdWakeLock.j("&:\023.\023:\023&\025-"), 0);
        Context localContext = this.CA;
        String str = this.BA;
        if (((SharedPreferences)localObject).getBoolean(AdDefines.j("j\036}\025\032\036u\036v\017y\027y\tu"), false)) {}
        for (localObject = AdWakeLock.j("\004-\023&\021)\021-\033-\030<))\032)\004%");; localObject = "reengagement")
        {
          this.Z = new AdTask(this, localContext, str, (String)localObject);
          this.Z.setSubId(this.o);
          this.Z.setTokens(this.NA);
          M(this.Z, new String[] { "" });
          return;
          i1 = 0;
          break;
        }
      }
      AdLog.i("LBAdController", AdDefines.j("V\0248\t}\nm\036k\0178\017w[z\0368\026y\037}"));
      return;
      i1 = 1;
    }
  }
  
  public void loadStartAd(String paramString1, String paramString2)
  {
    loadAd();
    this.K = new AdController(this.CA, paramString1);
    this.K.loadAudioTrack(2L);
    new AdController(this.CA, paramString2).loadReEngagement();
  }
  
  public boolean onBackPressed()
  {
    if (this.GA)
    {
      loadAd();
      return true;
    }
    return false;
  }
  
  public void onLinkClicked()
  {
    B();
  }
  
  public void reEngagementInitialized()
  {
    if (this.v != null) {
      m();
    }
    SharedPreferences localSharedPreferences = this.CA.getSharedPreferences(AdDefines.j("+j\036~\036j\036v\030}"), 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (localSharedPreferences.getBoolean(AdWakeLock.j(":\023-\030/\027/\023%\023&\002)\032)\004%"), false))
    {
      localEditor.putBoolean(AdDefines.j("j\036}\025\032\036u\036v\017y\027y\tu"), false);
      AdUtils.apply(localEditor);
      A();
    }
  }
  
  public void retrieveAudioAd()
  {
    int i2 = 1;
    AdLog.d("LBAdController", AdDefines.j("j\036l\tq\036n\036Y\016|\022w:|"));
    for (;;)
    {
      try
      {
        if (this.v.get(AdWakeLock.j("\005 \031?")).equals(AdDefines.j(")")))
        {
          i1 = i2;
          if (this.d != null)
          {
            if (this.d.getStatus() != AsyncTask.Status.FINISHED) {
              break label162;
            }
            i1 = i2;
          }
          if (i1 != 0)
          {
            AdLog.d("LBAdController", AdWakeLock.j("\021'\037&\021h\002'V%\027#\023h\004-\007=\023;\002h\027&\022h\020-\002+\036h\027=\022!\031h\027,"));
            this.d = new AdAudioTask(this, this.CA);
            c(this.d, new String[] { this.v.getString(AdDefines.j("\032|\032m\037q\024m\tt")) });
          }
        }
        else
        {
          this.P = false;
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        AdLog.d("LBAdController", localJSONException.getMessage());
      }
      return;
      label162:
      int i1 = 0;
    }
  }
  
  public void setAdDestroyed(boolean paramBoolean)
  {
    this.Y = paramBoolean;
  }
  
  public void setAdLoaded(boolean paramBoolean)
  {
    this.aA = paramBoolean;
  }
  
  public void setAdditionalDockingMargin(int paramInt)
  {
    this.N = paramInt;
    AdLog.i("LBAdController", paramInt);
  }
  
  public void setCompleted(boolean paramBoolean)
  {
    this.f = paramBoolean;
  }
  
  public void setHTML(String paramString)
  {
    if (this.l != null)
    {
      paramString = paramString + AdDefines.j("$Tp\017u\027&");
      this.l.loadHTMLWrap(paramString);
    }
  }
  
  public void setHTMLAds(boolean paramBoolean)
  {
    this.xA = paramBoolean;
  }
  
  public void setHomeLoaded(boolean paramBoolean)
  {
    this.j = paramBoolean;
  }
  
  public void setLayout(RelativeLayout paramRelativeLayout)
  {
    this.YA = paramRelativeLayout;
  }
  
  public void setLoadInBackground(boolean paramBoolean)
  {
    this.H = paramBoolean;
  }
  
  public void setLoading(boolean paramBoolean)
  {
    this.EA = paramBoolean;
  }
  
  public void setOnAdLoaded(boolean paramBoolean)
  {
    this.qA = paramBoolean;
    if ((this.H) && (!this.Y)) {
      ((Activity)this.CA).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (AdController.a(AdController.this))
          {
            SharedPreferences.Editor localEditor = AdController.this.CA.getSharedPreferences(AdRequest.j("{aNuNaN}Hv"), 0).edit();
            localEditor.putLong(AdController.H(AdController.this), System.currentTimeMillis());
            AdUtils.apply(localEditor);
            AdController.G(AdController.this, true);
            if (AdController.h(AdController.this) != null) {
              AdController.h(AdController.this).onAdCached();
            }
            return;
          }
          if ((!AdController.a(AdController.this)) && (AdController.h(AdController.this) != null))
          {
            AdLog.i("LBAdController", AdRequest.j("_aBtLvY3D}jwg|JwNw"));
            AdController.h(AdController.this).onAdLoaded();
          }
          AdController.h(AdController.this, false);
          AdController.f(AdController.this);
          AdController.j(AdController.this, false);
        }
      });
    }
  }
  
  public void setOnProgressInterval(int paramInt)
  {
    this.Q = paramInt;
  }
  
  public void setResults(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.v = paramJSONObject;
      paramJSONObject = this.CA.getSharedPreferences(AdDefines.j("+j\036~\036j\036v\030}"), 0).edit();
    }
    try
    {
      paramJSONObject.putLong(AdWakeLock.j(")\022:\023+\036-\025#\002!\033-"), this.v.getLong(AdDefines.j("y\037j\036{\023}\030s\017q\026}")));
      AdUtils.apply(paramJSONObject);
      AdLog.d("LBAdController", this.v.getLong(AdDefines.j("y\037j\036{\023}\030s\017q\026}")));
      return;
    }
    catch (Exception paramJSONObject)
    {
      AdLog.d("LBAdController", paramJSONObject.getMessage());
    }
  }
  
  public void setSubId(String paramString)
  {
    this.o = paramString;
  }
  
  public void setTokens(List<NameValuePair> paramList)
  {
    this.NA = paramList;
  }
  
  public void showElements()
  {
    try
    {
      this.M.setVisibility(0);
      this.t.setVisibility(0);
      this.z.setVisibility(0);
      this.O.setVisibility(0);
      this.W.setVisibility(0);
      this.A.setVisibility(0);
      this.FA.setVisibility(0);
      this.iA.setVisibility(0);
      this.R.setVisibility(0);
      this.YA.setVisibility(0);
      this.jA.setVisibility(0);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void showInternetDialog()
  {
    if ((this.CA != null) && ((this.CA instanceof Activity)))
    {
      if (this.IA == null)
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.CA);
        localBuilder.setMessage(AdDefines.j("2v\017}\tv\036l[v\024l[y\ry\022t\032z\027}")).setCancelable(false).setPositiveButton(AdWakeLock.j("\013\032'\005-"), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            AdController.M(AdController.this).dismiss();
          }
        });
        this.IA = localBuilder.create();
      }
      this.IA.show();
    }
  }
  
  public void stopAllListeners() {}
  
  public void triggerAdCompleted() {}
  
  public void triggerAdFailed()
  {
    AdLog.e("LBAdController", AdWakeLock.j("8'V\001\030<\023:\030-\002h\025'\030&\023+\002!\031&V,\023<\023+\002-\022fV\006\031h7,\005h\032'\027,\023,"));
    if (this.k != null) {}
    for (;;)
    {
      try
      {
        AdLog.i("LBAdController", AdDefines.j("\024v:|=y\022t\036|[l\tq\034\036j\036|"));
        this.k.onAdFailed();
        this.aA = true;
        if (this.RA != null)
        {
          this.RA.onAdFailed();
          this.dA = true;
        }
        return;
      }
      catch (Exception localException)
      {
        AdLog.i("LBAdController", AdWakeLock.j("3:\004'\004h\001 \037$\023h\025)\032$\037&\021h\031&7,0)\037$\023,"));
        AdLog.printStackTrace("LBAdController", localException);
      }
    }
  }
  
  private class AdClientPixel
    extends AsyncTask<String, Void, Boolean>
  {
    protected Boolean j(String... paramVarArgs)
    {
      Object localObject = paramVarArgs[0];
      if ((localObject != null) && (!((String)localObject).equals("")))
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        localBasicHttpParams.setParameter(AdWakeLock.j(" \002<\006f\006:\031<\031+\031$X>\023:\005!\031&"), HttpVersion.HTTP_1_1);
        paramVarArgs = new DefaultHttpClient(localBasicHttpParams);
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 4000);
        localObject = new HttpGet((String)localObject);
      }
      try
      {
        if (paramVarArgs.execute((HttpUriRequest)localObject).getStatusLine().getStatusCode() == 200) {
          AdController.c(this.F, true);
        }
        return Boolean.valueOf(false);
      }
      catch (Exception paramVarArgs)
      {
        for (;;) {}
      }
    }
    
    protected void j(boolean paramBoolean)
    {
      if (paramBoolean) {
        AdController.c(this.F, true);
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
        this.E = new Handler();
        this.i = new Runnable()
        {
          public void run()
          {
            AdLog.i("LBAdController", AdRequest.j("X{JxN3Jw\013wNgNp_zD}\013vScBaN`"));
            AdController.AdShakeListener.M(AdController.AdShakeListener.this);
            AdController.AdShakeListener.j(AdController.AdShakeListener.this);
          }
        };
      }
    }
    
    public void destroySensor()
    {
      if (this.j)
      {
        M();
        this.E.removeCallbacks(this.i);
      }
    }
    
    public void enableShakeDetection()
    {
      this.j = true;
      AdController.d(AdController.this).registerListener(this, AdController.d(AdController.this).getDefaultSensor(1), 1);
      AdLog.i("LBAdController", AdEncryption.M("Y\001K\002OIO\037O\007^IN\f^\fI\035C\006DIO\007K\013F\fN"));
    }
    
    public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
    
    public void onSensorChanged(SensorEvent paramSensorEvent)
    {
      float f1 = paramSensorEvent.values[0];
      float f2 = paramSensorEvent.values[1];
      float f3 = paramSensorEvent.values[2];
      AdController.G(AdController.this, AdController.e(AdController.this));
      AdController.M(AdController.this, FloatMath.sqrt(f1 * f1 + f2 * f2 + f3 * f3));
      f1 = AdController.e(AdController.this);
      f2 = AdController.l(AdController.this);
      AdController.j(AdController.this, f1 - f2 + AdController.j(AdController.this) * 0.9F);
      if (AdController.j(AdController.this) > 2.5F)
      {
        long l = System.currentTimeMillis();
        if (this.K == 0L)
        {
          this.K = l;
          this.A = l;
        }
        if (l - this.A < 300L)
        {
          this.A = l;
          this.d += 1;
          if ((this.d >= this.H) && (l - this.K < 1500L))
          {
            AdLog.i("LBAdController", AdDefines.j("n\032t\022|[k\023y\020}"));
            G();
            M();
            if (this.a)
            {
              if ((AdController.A(AdController.this) != null) && (AdController.A(AdController.this).isPlaying()))
              {
                AdController.A(AdController.this).stop();
                AdController.j(AdController.this, null);
                if (AdController.B(AdController.this) != null) {
                  AdController.B(AdController.this).onAdFinished();
                }
                AdController.c(AdController.this).setStreamVolume(3, AdController.K(AdController.this), 8);
              }
              this.E.removeCallbacks(this.i);
              j();
            }
          }
        }
        return;
      }
      G();
    }
    
    public void setShakeTime(int paramInt)
    {
      this.H = paramInt;
    }
    
    public void setValidTimes(int paramInt)
    {
      this.b = paramInt;
    }
    
    public void setupAudioAdHandler()
    {
      this.E.postDelayed(this.i, this.b);
    }
  }
  
  private class ContextList
    extends AsyncTask<Void, Void, String>
  {
    public ContextList(Context paramContext)
    {
      this.F = paramContext;
      this.f = this.F.getSharedPreferences(AdController.j("kL^X^L^PX["), 0);
      this.g = this.f.edit();
    }
    
    protected String j(Void... paramVarArgs)
    {
      AdLog.i("LBAdController", AdDefines.j("_\024q\025[l\0248\bl\032j\0178:k\002v\030L\032k\0208\017w[\036v\036j\032l\0368\030w\025l\036`\017k"));
      paramVarArgs = new StringBuilder();
      Object localObject1 = this.F.getPackageManager().getInstalledPackages(128).iterator();
      int i = 0;
      Object localObject2;
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (i <= 50) {}
      }
      for (;;)
      {
        localObject1 = (ActivityManager)this.F.getSystemService(AdDefines.j("\032{\017q\rq\017a"));
        if (this.F.checkCallingOrSelfPermission(AdController.j("ZP_LTW_\020K[ISRMHWTP\025y~jdjzmpm")) != 0) {
          break label594;
        }
        localObject1 = ((ActivityManager)localObject1).getRunningTasks(Integer.MAX_VALUE).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ActivityManager.RunningTaskInfo)((Iterator)localObject1).next();
          try
          {
            paramVarArgs.append(URLEncoder.encode(new StringBuilder().insert(0, AdController.j("PZS^\003")).append(this.F.getPackageManager().getApplicationInfo(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName(), 0).loadLabel(this.F.getPackageManager()).toString()).append(AdDefines.j(">\013y\030s\032\036%")).append(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName()).toString(), AdController.j("kox\026\006")));
          }
          catch (Exception localException1) {}
        }
        if ((localException1.applicationInfo.flags & 0x1) != 0) {
          break;
        }
        i += 1;
        StringBuilder localStringBuilder1 = new StringBuilder(AdController.j("OWOR^\003"));
        for (;;)
        {
          try
          {
            localStringBuilder1.append(localException1.applicationInfo.loadLabel(this.F.getPackageManager()).toString());
            localStringBuilder1.append(localException1.applicationInfo.packageName);
            StringBuilder localStringBuilder2 = new StringBuilder().insert(0, AdDefines.j(">\022v\bl\032t\027%"));
            if (Build.VERSION.SDK_INT < 9) {
              break label581;
            }
            l = localException1.firstInstallTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder2 = new StringBuilder().insert(0, AdController.j("\035KKZZJ^\003"));
            if (Build.VERSION.SDK_INT < 9) {
              break label586;
            }
            l = localException1.lastUpdateTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder1.append(localException1.versionCode);
            localStringBuilder1.append(localException1.versionName);
            paramVarArgs.append(URLEncoder.encode(localStringBuilder1.toString(), AdController.j("kox\026\006")));
          }
          catch (Exception localException2) {}
          break;
          label581:
          long l = 0L;
          continue;
          label586:
          l = 0L;
        }
      }
      label594:
      return AdEncryption.encrypt(paramVarArgs.toString());
    }
    
    protected void j(String paramString)
    {
      this.g.putBoolean(AdDefines.j("(\\$[4V/]#L(G2V+J4_)](K"), false);
      AdUtils.apply(this.g);
      if ((paramString != null) && (!paramString.equals("")))
      {
        this.g.putString(AdController.j("maxquj~fom"), paramString);
        this.g.putLong(AdDefines.j("K?G8W5L>@/K$M+\\:L>G/Q6]"), System.currentTimeMillis());
        AdUtils.apply(this.g);
      }
    }
    
    protected void onPreExecute()
    {
      this.g.putBoolean(AdController.j("hzd}tpo{cjharpkltyi{hm"), true);
      AdUtils.apply(this.g);
    }
  }
  
  public static class Dimensions
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<Dimensions> F = new Parcelable.Creator()
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
    public int b;
    public int f;
    public int g;
    public int j;
    
    public Dimensions()
    {
      this.f = -1;
      this.g = -1;
      this.j = -1;
      this.b = -1;
    }
    
    protected Dimensions(Parcel paramParcel)
    {
      super();
    }
  }
  
  private class FetchImage
    extends AsyncTask<String, Void, Bitmap>
  {
    public FetchImage(Context paramContext, int paramInt, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, NotificationManager paramNotificationManager, PendingIntent paramPendingIntent)
    {
      this.K = paramContext;
      this.g = paramInt;
      this.E = paramCharSequence1;
      this.L = paramCharSequence2;
      this.F = paramCharSequence3;
      this.a = paramNotificationManager;
      this.i = paramPendingIntent;
      AdLog.i("LBAdController", AdController.j("yTWUY\033JT\036}[O]S\036rSZY^\037"));
    }
    
    protected Bitmap j(String... paramVarArgs)
    {
      paramVarArgs = paramVarArgs[0];
      AdLog.i("LBAdController", paramVarArgs);
      if ((paramVarArgs != null) && (!paramVarArgs.equals(""))) {
        try
        {
          paramVarArgs = BitmapFactory.decodeStream(((HttpURLConnection)new URL(paramVarArgs).openConnection()).getInputStream());
          return paramVarArgs;
        }
        catch (Exception paramVarArgs)
        {
          AdLog.e("LBAdController", paramVarArgs.getMessage());
          AdLog.printStackTrace("LBAdController", paramVarArgs);
          return null;
        }
      }
      return null;
    }
    
    /* Error */
    protected void j(Bitmap paramBitmap)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnull +465 -> 466
      //   4: ldc 81
      //   6: ldc -91
      //   8: invokestatic 109	com/jvzsmgumfkqcconsjdc/AdJSInterface:j	(Ljava/lang/String;)Ljava/lang/String;
      //   11: invokestatic 91	com/jvzsmgumfkqcconsjdc/AdLog:i	(Ljava/lang/String;Ljava/lang/String;)V
      //   14: new 103	java/lang/StringBuilder
      //   17: dup
      //   18: invokespecial 104	java/lang/StringBuilder:<init>	()V
      //   21: iconst_0
      //   22: aload_0
      //   23: getfield 67	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:K	Landroid/content/Context;
      //   26: invokevirtual 170	android/content/Context:getPackageName	()Ljava/lang/String;
      //   29: invokevirtual 113	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   32: aload_0
      //   33: getfield 41	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:j	Ljava/lang/String;
      //   36: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   39: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   42: invokestatic 176	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   45: astore 7
      //   47: aload 7
      //   49: aload_0
      //   50: getfield 49	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:f	Ljava/lang/String;
      //   53: invokevirtual 180	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   56: aload 7
      //   58: invokevirtual 186	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   61: istore_2
      //   62: new 103	java/lang/StringBuilder
      //   65: dup
      //   66: invokespecial 104	java/lang/StringBuilder:<init>	()V
      //   69: iconst_0
      //   70: aload_0
      //   71: getfield 67	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:K	Landroid/content/Context;
      //   74: invokevirtual 170	android/content/Context:getPackageName	()Ljava/lang/String;
      //   77: invokevirtual 113	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   80: aload_0
      //   81: getfield 45	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:k	Ljava/lang/String;
      //   84: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   87: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   90: invokestatic 176	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   93: astore 7
      //   95: aload 7
      //   97: aload_0
      //   98: getfield 53	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:m	Ljava/lang/String;
      //   101: invokevirtual 180	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   104: aload 7
      //   106: invokevirtual 186	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   109: istore_3
      //   110: aload 7
      //   112: aload_0
      //   113: getfield 57	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:H	Ljava/lang/String;
      //   116: invokevirtual 180	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   119: aload 7
      //   121: invokevirtual 186	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   124: istore 4
      //   126: aload 7
      //   128: aload_0
      //   129: getfield 61	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:A	Ljava/lang/String;
      //   132: invokevirtual 180	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   135: aload 7
      //   137: invokevirtual 186	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   140: istore 5
      //   142: aload 7
      //   144: aload_0
      //   145: getfield 65	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:d	Ljava/lang/String;
      //   148: invokevirtual 180	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   151: aload 7
      //   153: invokevirtual 186	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   156: istore 6
      //   158: iload 5
      //   160: iconst_m1
      //   161: if_icmpeq +175 -> 336
      //   164: iload 6
      //   166: iconst_m1
      //   167: if_icmpeq +169 -> 336
      //   170: new 188	android/widget/RemoteViews
      //   173: dup
      //   174: aload_0
      //   175: getfield 67	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:K	Landroid/content/Context;
      //   178: invokevirtual 170	android/content/Context:getPackageName	()Ljava/lang/String;
      //   181: iload_2
      //   182: invokespecial 191	android/widget/RemoteViews:<init>	(Ljava/lang/String;I)V
      //   185: astore 7
      //   187: aload 7
      //   189: iload_3
      //   190: ldc -63
      //   192: invokestatic 86	com/jvzsmgumfkqcconsjdc/AdController:j	(Ljava/lang/String;)Ljava/lang/String;
      //   195: aload_0
      //   196: getfield 34	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:b	Lcom/jvzsmgumfkqcconsjdc/AdController;
      //   199: invokestatic 196	com/jvzsmgumfkqcconsjdc/AdController:k	(Lcom/jvzsmgumfkqcconsjdc/AdController;)Lorg/json/JSONObject;
      //   202: ldc -58
      //   204: invokestatic 109	com/jvzsmgumfkqcconsjdc/AdJSInterface:j	(Ljava/lang/String;)Ljava/lang/String;
      //   207: invokevirtual 203	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   210: invokestatic 209	android/graphics/Color:parseColor	(Ljava/lang/String;)I
      //   213: invokevirtual 213	android/widget/RemoteViews:setInt	(ILjava/lang/String;I)V
      //   216: aload 7
      //   218: iload 5
      //   220: aload_1
      //   221: invokevirtual 217	android/widget/RemoteViews:setImageViewBitmap	(ILandroid/graphics/Bitmap;)V
      //   224: aload 7
      //   226: iload 6
      //   228: aload_0
      //   229: getfield 34	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:b	Lcom/jvzsmgumfkqcconsjdc/AdController;
      //   232: invokestatic 196	com/jvzsmgumfkqcconsjdc/AdController:k	(Lcom/jvzsmgumfkqcconsjdc/AdController;)Lorg/json/JSONObject;
      //   235: ldc -37
      //   237: invokestatic 86	com/jvzsmgumfkqcconsjdc/AdController:j	(Ljava/lang/String;)Ljava/lang/String;
      //   240: invokevirtual 203	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   243: invokevirtual 223	android/widget/RemoteViews:setTextViewText	(ILjava/lang/CharSequence;)V
      //   246: aload 7
      //   248: iload 6
      //   250: aload_0
      //   251: getfield 34	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:b	Lcom/jvzsmgumfkqcconsjdc/AdController;
      //   254: invokestatic 196	com/jvzsmgumfkqcconsjdc/AdController:k	(Lcom/jvzsmgumfkqcconsjdc/AdController;)Lorg/json/JSONObject;
      //   257: ldc -31
      //   259: invokestatic 109	com/jvzsmgumfkqcconsjdc/AdJSInterface:j	(Ljava/lang/String;)Ljava/lang/String;
      //   262: invokevirtual 203	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   265: invokestatic 209	android/graphics/Color:parseColor	(Ljava/lang/String;)I
      //   268: invokevirtual 229	android/widget/RemoteViews:setTextColor	(II)V
      //   271: aload 7
      //   273: iload 4
      //   275: iconst_4
      //   276: invokevirtual 232	android/widget/RemoteViews:setViewVisibility	(II)V
      //   279: ldc 81
      //   281: ldc -22
      //   283: invokestatic 86	com/jvzsmgumfkqcconsjdc/AdController:j	(Ljava/lang/String;)Ljava/lang/String;
      //   286: invokestatic 236	com/jvzsmgumfkqcconsjdc/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   289: aload_0
      //   290: getfield 69	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:g	I
      //   293: iconst_m1
      //   294: if_icmpeq +217 -> 511
      //   297: aload_0
      //   298: getfield 34	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:b	Lcom/jvzsmgumfkqcconsjdc/AdController;
      //   301: aload_0
      //   302: getfield 67	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:K	Landroid/content/Context;
      //   305: aload_0
      //   306: getfield 69	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:g	I
      //   309: aload_0
      //   310: getfield 71	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:E	Ljava/lang/CharSequence;
      //   313: aload_0
      //   314: getfield 73	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:L	Ljava/lang/CharSequence;
      //   317: aload_0
      //   318: getfield 75	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:F	Ljava/lang/CharSequence;
      //   321: aload_0
      //   322: getfield 77	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:a	Landroid/app/NotificationManager;
      //   325: aload_0
      //   326: getfield 79	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:i	Landroid/app/PendingIntent;
      //   329: aload 7
      //   331: invokestatic 239	com/jvzsmgumfkqcconsjdc/AdController:j	(Lcom/jvzsmgumfkqcconsjdc/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   334: pop
      //   335: return
      //   336: aload_0
      //   337: getfield 69	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:g	I
      //   340: iconst_m1
      //   341: if_icmpeq +170 -> 511
      //   344: aload_0
      //   345: getfield 34	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:b	Lcom/jvzsmgumfkqcconsjdc/AdController;
      //   348: aload_0
      //   349: getfield 67	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:K	Landroid/content/Context;
      //   352: aload_0
      //   353: getfield 69	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:g	I
      //   356: aload_0
      //   357: getfield 71	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:E	Ljava/lang/CharSequence;
      //   360: aload_0
      //   361: getfield 73	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:L	Ljava/lang/CharSequence;
      //   364: aload_0
      //   365: getfield 75	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:F	Ljava/lang/CharSequence;
      //   368: aload_0
      //   369: getfield 77	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:a	Landroid/app/NotificationManager;
      //   372: aload_0
      //   373: getfield 79	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:i	Landroid/app/PendingIntent;
      //   376: aconst_null
      //   377: invokestatic 239	com/jvzsmgumfkqcconsjdc/AdController:j	(Lcom/jvzsmgumfkqcconsjdc/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   380: pop
      //   381: return
      //   382: astore_1
      //   383: ldc 81
      //   385: new 103	java/lang/StringBuilder
      //   388: dup
      //   389: invokespecial 104	java/lang/StringBuilder:<init>	()V
      //   392: iconst_0
      //   393: ldc -15
      //   395: invokestatic 109	com/jvzsmgumfkqcconsjdc/AdJSInterface:j	(Ljava/lang/String;)Ljava/lang/String;
      //   398: invokevirtual 113	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   401: aload_1
      //   402: invokevirtual 155	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   405: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   408: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   411: invokestatic 158	com/jvzsmgumfkqcconsjdc/AdLog:e	(Ljava/lang/String;Ljava/lang/String;)V
      //   414: ldc 81
      //   416: aload_1
      //   417: invokestatic 162	com/jvzsmgumfkqcconsjdc/AdLog:printStackTrace	(Ljava/lang/String;Ljava/lang/Exception;)V
      //   420: aload_0
      //   421: getfield 69	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:g	I
      //   424: iconst_m1
      //   425: if_icmpeq +86 -> 511
      //   428: aload_0
      //   429: getfield 34	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:b	Lcom/jvzsmgumfkqcconsjdc/AdController;
      //   432: aload_0
      //   433: getfield 67	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:K	Landroid/content/Context;
      //   436: aload_0
      //   437: getfield 69	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:g	I
      //   440: aload_0
      //   441: getfield 71	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:E	Ljava/lang/CharSequence;
      //   444: aload_0
      //   445: getfield 73	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:L	Ljava/lang/CharSequence;
      //   448: aload_0
      //   449: getfield 75	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:F	Ljava/lang/CharSequence;
      //   452: aload_0
      //   453: getfield 77	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:a	Landroid/app/NotificationManager;
      //   456: aload_0
      //   457: getfield 79	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:i	Landroid/app/PendingIntent;
      //   460: aconst_null
      //   461: invokestatic 239	com/jvzsmgumfkqcconsjdc/AdController:j	(Lcom/jvzsmgumfkqcconsjdc/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   464: pop
      //   465: return
      //   466: aload_0
      //   467: getfield 69	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:g	I
      //   470: iconst_m1
      //   471: if_icmpeq +40 -> 511
      //   474: aload_0
      //   475: getfield 34	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:b	Lcom/jvzsmgumfkqcconsjdc/AdController;
      //   478: aload_0
      //   479: getfield 67	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:K	Landroid/content/Context;
      //   482: aload_0
      //   483: getfield 69	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:g	I
      //   486: aload_0
      //   487: getfield 71	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:E	Ljava/lang/CharSequence;
      //   490: aload_0
      //   491: getfield 73	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:L	Ljava/lang/CharSequence;
      //   494: aload_0
      //   495: getfield 75	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:F	Ljava/lang/CharSequence;
      //   498: aload_0
      //   499: getfield 77	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:a	Landroid/app/NotificationManager;
      //   502: aload_0
      //   503: getfield 79	com/jvzsmgumfkqcconsjdc/AdController$FetchImage:i	Landroid/app/PendingIntent;
      //   506: aconst_null
      //   507: invokestatic 239	com/jvzsmgumfkqcconsjdc/AdController:j	(Lcom/jvzsmgumfkqcconsjdc/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   510: pop
      //   511: return
      //   512: astore 8
      //   514: goto -298 -> 216
      //   517: astore_1
      //   518: goto -247 -> 271
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	521	0	this	FetchImage
      //   0	521	1	paramBitmap	Bitmap
      //   61	121	2	n	int
      //   109	81	3	i1	int
      //   124	150	4	i2	int
      //   140	79	5	i3	int
      //   156	93	6	i4	int
      //   45	285	7	localObject	Object
      //   512	1	8	localException	Exception
      // Exception table:
      //   from	to	target	type
      //   14	158	382	java/lang/Exception
      //   170	187	382	java/lang/Exception
      //   216	246	382	java/lang/Exception
      //   271	335	382	java/lang/Exception
      //   336	381	382	java/lang/Exception
      //   187	216	512	java/lang/Exception
      //   246	271	517	java/lang/Exception
    }
  }
  
  public static class PlayerProperties
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<PlayerProperties> d = new Parcelable.Creator()
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
    public String A;
    public boolean F;
    public boolean b;
    public String f;
    public boolean g;
    public boolean i;
    public boolean j;
    
    public PlayerProperties()
    {
      this.i = true;
      this.j = true;
      this.g = false;
      this.F = false;
      this.A = "normal";
      this.f = "normal";
      this.b = false;
    }
    
    public PlayerProperties(Parcel paramParcel)
    {
      super();
    }
    
    public boolean doLoop()
    {
      return this.F;
    }
    
    public boolean doMute()
    {
      return this.g;
    }
    
    public boolean exitOnComplete()
    {
      return this.A.equalsIgnoreCase("exit");
    }
    
    public boolean isAutoPlay()
    {
      return this.j == true;
    }
    
    public boolean isFullScreen()
    {
      return this.f.equalsIgnoreCase("fullscreen");
    }
    
    public void muteAudio()
    {
      this.g = true;
    }
    
    public void setProperties(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String paramString1, String paramString2)
    {
      this.j = paramBoolean2;
      this.i = paramBoolean3;
      this.F = paramBoolean5;
      this.g = paramBoolean1;
      this.f = paramString1;
      this.A = paramString2;
      this.b = paramBoolean4;
    }
    
    public void setStopStyle(String paramString)
    {
      this.A = paramString;
    }
    
    public boolean showControl()
    {
      return this.i;
    }
  }
  
  public static class Properties
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<Properties> g = new Parcelable.Creator()
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
    public float F;
    public int f;
    public boolean j;
    
    public Properties()
    {
      this.j = false;
      this.f = 0;
      this.F = 0.0F;
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
            if (((String)localObject).equals("class com.jvzsmgumfkqcconsjdc.AdNavigationStringEnum")) {
              localField.set(this, AdNavigationStringEnum.fromString(paramParcel.readString()));
            } else if (((String)localObject).equals("class com.jvzsmgumfkqcconsjdc.AdTransitionStringEnum")) {
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
            if (((String)localObject2).equals("class com.jvzsmgumfkqcconsjdc.AdNavigationStringEnum")) {
              paramParcel.writeString(((AdNavigationStringEnum)((Field)localObject1).get(this)).getText());
            } else if (((String)localObject2).equals("class com.jvzsmgumfkqcconsjdc.AdTransitionStringEnum")) {
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
