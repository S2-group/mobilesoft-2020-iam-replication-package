package com.nwvlwgbwdtkfcwuk;

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
  public static final String DA = "exit";
  public static final String S = "LBAdController";
  public static final String Y = "fullscreen";
  public static final String m = "normal";
  protected Context hA;
  protected AdView z;
  
  public AdController(Context paramContext, String paramString)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
  }
  
  public AdController(Context paramContext, String paramString, WebView paramWebView)
  {
    this.hA = paramContext;
    this.u = paramString;
    this.KA = paramWebView;
    this.g = new RelativeLayout(this.hA);
    L();
  }
  
  public AdController(Context paramContext, String paramString, RelativeLayout paramRelativeLayout)
  {
    this.hA = paramContext;
    this.u = paramString;
    paramContext = paramRelativeLayout;
    if (paramRelativeLayout == null) {
      paramContext = new RelativeLayout(this.hA);
    }
    this.g = paramContext;
    this.KA = null;
    L();
  }
  
  public AdController(Context paramContext, String paramString, AdAudioListener paramAdAudioListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.dA = paramAdAudioListener;
  }
  
  public AdController(Context paramContext, String paramString, AdListener paramAdListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.xA = paramAdListener;
  }
  
  public AdController(AdView paramAdView, Context paramContext)
  {
    this.z = paramAdView;
    this.hA = paramContext;
  }
  
  protected static Object h(JSONObject paramJSONObject, Class<?> paramClass)
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
          boolean bool = str1.startsWith(AdJSInterface.h("4"));
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
          if (!str2.equals("class com.nwvlwgbwdtkfcwuk.AdNavigationStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdNavigationStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
          if (!str2.equals("class com.nwvlwgbwdtkfcwuk.AdTransitionStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdTransitionStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
        }
        try
        {
          if (str1.startsWith(AdJSInterface.h("4!o")))
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
  
  public static String h(String paramString)
  {
    int i1 = paramString.length();
    char[] arrayOfChar = new char[i1];
    int i2 = i1 - 1;
    for (i1 = i2; i2 >= 0; i1 = i2)
    {
      i2 = paramString.charAt(i1);
      int i3 = i1 - 1;
      arrayOfChar[i1] = ((char)(i2 ^ 0x59));
      if (i3 < 0) {
        break;
      }
      i2 = i3 - 1;
      arrayOfChar[i3] = ((char)(paramString.charAt(i3) ^ 0x29));
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
    //   4: putfield 243	com/nwvlwgbwdtkfcwuk/AdController:d	Z
    //   7: aload_0
    //   8: getfield 294	com/nwvlwgbwdtkfcwuk/AdController:hA	Landroid/content/Context;
    //   11: ldc_w 722
    //   14: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   17: iconst_0
    //   18: invokevirtual 728	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   21: astore 4
    //   23: aload 4
    //   25: invokeinterface 734 1 0
    //   30: astore 5
    //   32: aload_0
    //   33: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   36: ifnull +1085 -> 1121
    //   39: aload 5
    //   41: ldc_w 1700
    //   44: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   47: aload_0
    //   48: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   51: ldc_w 1700
    //   54: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   57: invokevirtual 1703	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   60: invokeinterface 819 4 0
    //   65: pop
    //   66: aload 5
    //   68: invokestatic 829	com/nwvlwgbwdtkfcwuk/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   71: aload_0
    //   72: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   75: ldc_w 422
    //   78: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   81: invokevirtual 339	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   84: ldc_w 847
    //   87: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   90: invokevirtual 353	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   93: ifeq +53 -> 146
    //   96: aload_0
    //   97: getfield 265	com/nwvlwgbwdtkfcwuk/AdController:xA	Lcom/nwvlwgbwdtkfcwuk/AdListener;
    //   100: ifnull +17 -> 117
    //   103: aload_0
    //   104: iconst_1
    //   105: putfield 251	com/nwvlwgbwdtkfcwuk/AdController:PA	Z
    //   108: aload_0
    //   109: getfield 265	com/nwvlwgbwdtkfcwuk/AdController:xA	Lcom/nwvlwgbwdtkfcwuk/AdListener;
    //   112: invokeinterface 709 1 0
    //   117: aload_0
    //   118: getfield 305	com/nwvlwgbwdtkfcwuk/AdController:dA	Lcom/nwvlwgbwdtkfcwuk/AdAudioListener;
    //   121: ifnull +17 -> 138
    //   124: aload_0
    //   125: iconst_1
    //   126: putfield 281	com/nwvlwgbwdtkfcwuk/AdController:Z	Z
    //   129: aload_0
    //   130: getfield 305	com/nwvlwgbwdtkfcwuk/AdController:dA	Lcom/nwvlwgbwdtkfcwuk/AdAudioListener;
    //   133: invokeinterface 712 1 0
    //   138: return
    //   139: astore 6
    //   141: goto -70 -> 71
    //   144: astore 6
    //   146: aload_0
    //   147: getfield 294	com/nwvlwgbwdtkfcwuk/AdController:hA	Landroid/content/Context;
    //   150: ifnull +194 -> 344
    //   153: aload_0
    //   154: getfield 294	com/nwvlwgbwdtkfcwuk/AdController:hA	Landroid/content/Context;
    //   157: instanceof 682
    //   160: ifeq +184 -> 344
    //   163: new 1705	android/util/DisplayMetrics
    //   166: dup
    //   167: invokespecial 1706	android/util/DisplayMetrics:<init>	()V
    //   170: astore 6
    //   172: aload_0
    //   173: getfield 294	com/nwvlwgbwdtkfcwuk/AdController:hA	Landroid/content/Context;
    //   176: checkcast 682	android/app/Activity
    //   179: invokevirtual 1710	android/app/Activity:getWindowManager	()Landroid/view/WindowManager;
    //   182: invokeinterface 1716 1 0
    //   187: aload 6
    //   189: invokevirtual 1722	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   192: new 1724	android/graphics/Rect
    //   195: dup
    //   196: invokespecial 1725	android/graphics/Rect:<init>	()V
    //   199: astore 7
    //   201: aload_0
    //   202: getfield 294	com/nwvlwgbwdtkfcwuk/AdController:hA	Landroid/content/Context;
    //   205: checkcast 682	android/app/Activity
    //   208: invokevirtual 1729	android/app/Activity:getWindow	()Landroid/view/Window;
    //   211: astore 8
    //   213: aload 8
    //   215: invokevirtual 1735	android/view/Window:getDecorView	()Landroid/view/View;
    //   218: aload 7
    //   220: invokevirtual 1739	android/view/View:getWindowVisibleDisplayFrame	(Landroid/graphics/Rect;)V
    //   223: aload 7
    //   225: getfield 1742	android/graphics/Rect:top	I
    //   228: istore_3
    //   229: aload 8
    //   231: ldc_w 1056
    //   234: invokevirtual 1743	android/view/Window:findViewById	(I)Landroid/view/View;
    //   237: invokevirtual 1746	android/view/View:getTop	()I
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
    //   253: getfield 1749	android/util/DisplayMetrics:widthPixels	I
    //   256: putfield 678	com/nwvlwgbwdtkfcwuk/AdController:C	I
    //   259: aload_0
    //   260: aload 6
    //   262: getfield 1752	android/util/DisplayMetrics:heightPixels	I
    //   265: iload_3
    //   266: isub
    //   267: iload_1
    //   268: isub
    //   269: putfield 680	com/nwvlwgbwdtkfcwuk/AdController:R	I
    //   272: ldc 93
    //   274: new 355	java/lang/StringBuilder
    //   277: dup
    //   278: invokespecial 356	java/lang/StringBuilder:<init>	()V
    //   281: iconst_0
    //   282: ldc_w 1754
    //   285: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   288: invokevirtual 378	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   291: aload 6
    //   293: getfield 1752	android/util/DisplayMetrics:heightPixels	I
    //   296: invokevirtual 531	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   299: ldc_w 1756
    //   302: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   305: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: iload_3
    //   309: invokevirtual 531	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   312: ldc_w 1756
    //   315: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   318: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: iconst_0
    //   322: invokevirtual 531	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   325: ldc_w 1756
    //   328: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   331: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: iload_1
    //   335: invokevirtual 531	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   338: invokevirtual 364	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: invokestatic 328	com/nwvlwgbwdtkfcwuk/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   344: aload_0
    //   345: aload_0
    //   346: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   349: ldc_w 1758
    //   352: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   355: invokevirtual 514	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   358: putfield 257	com/nwvlwgbwdtkfcwuk/AdController:BA	I
    //   361: aload_0
    //   362: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   365: ldc_w 1760
    //   368: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   371: invokevirtual 514	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   374: istore_1
    //   375: aload_0
    //   376: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   379: ldc_w 1762
    //   382: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   385: invokevirtual 514	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   388: istore_3
    //   389: iload_3
    //   390: ifle +191 -> 581
    //   393: aload_0
    //   394: iload_1
    //   395: bipush 60
    //   397: imul
    //   398: iload_3
    //   399: idiv
    //   400: putfield 259	com/nwvlwgbwdtkfcwuk/AdController:P	I
    //   403: aload_0
    //   404: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   407: ldc_w 1764
    //   410: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   413: invokevirtual 426	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   416: ldc_w 405
    //   419: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   422: invokevirtual 427	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   425: ifeq +8 -> 433
    //   428: aload_0
    //   429: iconst_1
    //   430: putfield 241	com/nwvlwgbwdtkfcwuk/AdController:j	Z
    //   433: aload 5
    //   435: new 355	java/lang/StringBuilder
    //   438: dup
    //   439: invokespecial 356	java/lang/StringBuilder:<init>	()V
    //   442: iconst_0
    //   443: ldc_w 1766
    //   446: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   449: invokevirtual 378	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   452: aload_0
    //   453: getfield 296	com/nwvlwgbwdtkfcwuk/AdController:u	Ljava/lang/String;
    //   456: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: invokevirtual 364	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   462: aload_0
    //   463: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   466: ldc_w 1768
    //   469: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   472: invokevirtual 339	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   475: invokeinterface 1212 3 0
    //   480: pop
    //   481: aload 5
    //   483: invokestatic 829	com/nwvlwgbwdtkfcwuk/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   486: aload_0
    //   487: getfield 279	com/nwvlwgbwdtkfcwuk/AdController:EA	Z
    //   490: ifeq +229 -> 719
    //   493: aload_0
    //   494: invokevirtual 1771	com/nwvlwgbwdtkfcwuk/AdController:retrieveAudioAd	()V
    //   497: aload 4
    //   499: new 355	java/lang/StringBuilder
    //   502: dup
    //   503: invokespecial 356	java/lang/StringBuilder:<init>	()V
    //   506: iconst_0
    //   507: ldc_w 1285
    //   510: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   513: invokevirtual 378	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   516: aload_0
    //   517: getfield 296	com/nwvlwgbwdtkfcwuk/AdController:u	Ljava/lang/String;
    //   520: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: invokevirtual 364	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   526: iconst_0
    //   527: invokeinterface 1145 3 0
    //   532: ifeq -394 -> 138
    //   535: aload_0
    //   536: getfield 1276	com/nwvlwgbwdtkfcwuk/AdController:uA	Landroid/os/Handler;
    //   539: ifnull -401 -> 138
    //   542: aload_0
    //   543: getfield 1276	com/nwvlwgbwdtkfcwuk/AdController:uA	Landroid/os/Handler;
    //   546: aload_0
    //   547: getfield 1278	com/nwvlwgbwdtkfcwuk/AdController:MA	Ljava/lang/Runnable;
    //   550: aload_0
    //   551: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   554: ldc_w 1773
    //   557: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   560: invokevirtual 514	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   563: sipush 1000
    //   566: imul
    //   567: i2l
    //   568: invokevirtual 1777	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   571: pop
    //   572: return
    //   573: astore 4
    //   575: return
    //   576: iconst_0
    //   577: istore_1
    //   578: goto -328 -> 250
    //   581: aload_0
    //   582: bipush 10
    //   584: putfield 259	com/nwvlwgbwdtkfcwuk/AdController:P	I
    //   587: goto -184 -> 403
    //   590: astore 6
    //   592: aload_0
    //   593: sipush 500
    //   596: putfield 257	com/nwvlwgbwdtkfcwuk/AdController:BA	I
    //   599: aload_0
    //   600: bipush 10
    //   602: putfield 259	com/nwvlwgbwdtkfcwuk/AdController:P	I
    //   605: goto -202 -> 403
    //   608: astore 6
    //   610: aload_0
    //   611: getfield 1779	com/nwvlwgbwdtkfcwuk/AdController:Q	Ljava/lang/String;
    //   614: ifnull +61 -> 675
    //   617: aload_0
    //   618: getfield 1779	com/nwvlwgbwdtkfcwuk/AdController:Q	Ljava/lang/String;
    //   621: ldc_w 847
    //   624: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   627: invokevirtual 353	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   630: ifne +45 -> 675
    //   633: aload 5
    //   635: new 355	java/lang/StringBuilder
    //   638: dup
    //   639: invokespecial 356	java/lang/StringBuilder:<init>	()V
    //   642: iconst_0
    //   643: ldc_w 1766
    //   646: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   649: invokevirtual 378	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   652: aload_0
    //   653: getfield 296	com/nwvlwgbwdtkfcwuk/AdController:u	Ljava/lang/String;
    //   656: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   659: invokevirtual 364	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   662: aload_0
    //   663: getfield 1779	com/nwvlwgbwdtkfcwuk/AdController:Q	Ljava/lang/String;
    //   666: invokeinterface 1212 3 0
    //   671: pop
    //   672: goto -191 -> 481
    //   675: aload 5
    //   677: new 355	java/lang/StringBuilder
    //   680: dup
    //   681: invokespecial 356	java/lang/StringBuilder:<init>	()V
    //   684: iconst_0
    //   685: ldc_w 1766
    //   688: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   691: invokevirtual 378	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   694: aload_0
    //   695: getfield 296	com/nwvlwgbwdtkfcwuk/AdController:u	Ljava/lang/String;
    //   698: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   701: invokevirtual 364	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   704: ldc_w 847
    //   707: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   710: invokeinterface 1212 3 0
    //   715: pop
    //   716: goto -235 -> 481
    //   719: aload_0
    //   720: new 368	com/nwvlwgbwdtkfcwuk/AdWebView
    //   723: dup
    //   724: aload_0
    //   725: getfield 294	com/nwvlwgbwdtkfcwuk/AdController:hA	Landroid/content/Context;
    //   728: checkcast 682	android/app/Activity
    //   731: aload_0
    //   732: aload_0
    //   733: getfield 241	com/nwvlwgbwdtkfcwuk/AdController:j	Z
    //   736: aload_0
    //   737: getfield 265	com/nwvlwgbwdtkfcwuk/AdController:xA	Lcom/nwvlwgbwdtkfcwuk/AdListener;
    //   740: aload_0
    //   741: getfield 300	com/nwvlwgbwdtkfcwuk/AdController:g	Landroid/widget/RelativeLayout;
    //   744: invokespecial 1782	com/nwvlwgbwdtkfcwuk/AdWebView:<init>	(Landroid/content/Context;Lcom/nwvlwgbwdtkfcwuk/AdController;ZLcom/nwvlwgbwdtkfcwuk/AdListener;Landroid/widget/RelativeLayout;)V
    //   747: putfield 345	com/nwvlwgbwdtkfcwuk/AdController:F	Lcom/nwvlwgbwdtkfcwuk/AdWebView;
    //   750: ldc 93
    //   752: ldc_w 1784
    //   755: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   758: invokestatic 328	com/nwvlwgbwdtkfcwuk/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   761: ldc_w 1118
    //   764: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   767: invokestatic 916	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   770: ldc_w 1786
    //   773: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   776: aconst_null
    //   777: checkcast 1122	[Ljava/lang/Class;
    //   780: invokevirtual 1126	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   783: aload_0
    //   784: getfield 345	com/nwvlwgbwdtkfcwuk/AdController:F	Lcom/nwvlwgbwdtkfcwuk/AdWebView;
    //   787: aconst_null
    //   788: checkcast 1128	[Ljava/lang/Object;
    //   791: invokevirtual 1134	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   794: pop
    //   795: aload_0
    //   796: getfield 345	com/nwvlwgbwdtkfcwuk/AdController:F	Lcom/nwvlwgbwdtkfcwuk/AdWebView;
    //   799: iconst_0
    //   800: invokevirtual 523	com/nwvlwgbwdtkfcwuk/AdWebView:setBackgroundColor	(I)V
    //   803: aload_0
    //   804: getfield 345	com/nwvlwgbwdtkfcwuk/AdController:F	Lcom/nwvlwgbwdtkfcwuk/AdWebView;
    //   807: aload_0
    //   808: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   811: invokevirtual 1790	com/nwvlwgbwdtkfcwuk/AdWebView:setResults	(Lorg/json/JSONObject;)V
    //   814: aload_0
    //   815: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   818: ldc_w 1792
    //   821: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   824: invokevirtual 514	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   827: istore_1
    //   828: iload_1
    //   829: iconst_1
    //   830: if_icmpne +18 -> 848
    //   833: aload_0
    //   834: getfield 345	com/nwvlwgbwdtkfcwuk/AdController:F	Lcom/nwvlwgbwdtkfcwuk/AdWebView;
    //   837: new 26	com/nwvlwgbwdtkfcwuk/AdController$3
    //   840: dup
    //   841: aload_0
    //   842: invokespecial 1793	com/nwvlwgbwdtkfcwuk/AdController$3:<init>	(Lcom/nwvlwgbwdtkfcwuk/AdController;)V
    //   845: invokevirtual 1797	com/nwvlwgbwdtkfcwuk/AdWebView:setOnKeyListener	(Landroid/view/View$OnKeyListener;)V
    //   848: aload_0
    //   849: getfield 309	com/nwvlwgbwdtkfcwuk/AdController:z	Lcom/nwvlwgbwdtkfcwuk/AdView;
    //   852: ifnonnull +214 -> 1066
    //   855: aload_0
    //   856: new 525	com/nwvlwgbwdtkfcwuk/AdView
    //   859: dup
    //   860: aload_0
    //   861: getfield 294	com/nwvlwgbwdtkfcwuk/AdController:hA	Landroid/content/Context;
    //   864: checkcast 682	android/app/Activity
    //   867: aload_0
    //   868: aload_0
    //   869: getfield 265	com/nwvlwgbwdtkfcwuk/AdController:xA	Lcom/nwvlwgbwdtkfcwuk/AdListener;
    //   872: invokespecial 1800	com/nwvlwgbwdtkfcwuk/AdView:<init>	(Landroid/content/Context;Lcom/nwvlwgbwdtkfcwuk/AdController;Lcom/nwvlwgbwdtkfcwuk/AdListener;)V
    //   875: putfield 309	com/nwvlwgbwdtkfcwuk/AdController:z	Lcom/nwvlwgbwdtkfcwuk/AdView;
    //   878: aload_0
    //   879: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   882: ldc_w 1802
    //   885: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   888: invokevirtual 514	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   891: iconst_1
    //   892: if_icmpeq -754 -> 138
    //   895: aload_0
    //   896: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   899: ldc_w 1802
    //   902: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   905: invokevirtual 514	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   908: istore_1
    //   909: iload_1
    //   910: ifne -772 -> 138
    //   913: aload_0
    //   914: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   917: ldc_w 1804
    //   920: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   923: invokevirtual 514	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   926: ifle +190 -> 1116
    //   929: aload_0
    //   930: getfield 312	com/nwvlwgbwdtkfcwuk/AdController:CA	Lorg/json/JSONObject;
    //   933: ldc_w 1804
    //   936: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   939: invokevirtual 514	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   942: sipush 1000
    //   945: imul
    //   946: istore_1
    //   947: ldc 93
    //   949: new 355	java/lang/StringBuilder
    //   952: dup
    //   953: invokespecial 356	java/lang/StringBuilder:<init>	()V
    //   956: iconst_0
    //   957: ldc_w 1806
    //   960: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   963: invokevirtual 378	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   966: iload_1
    //   967: invokevirtual 531	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   970: ldc_w 1808
    //   973: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   976: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: invokevirtual 364	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   982: invokestatic 387	com/nwvlwgbwdtkfcwuk/AdLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   985: new 1280	android/os/Handler
    //   988: dup
    //   989: invokespecial 1809	android/os/Handler:<init>	()V
    //   992: new 28	com/nwvlwgbwdtkfcwuk/AdController$4
    //   995: dup
    //   996: aload_0
    //   997: invokespecial 1810	com/nwvlwgbwdtkfcwuk/AdController$4:<init>	(Lcom/nwvlwgbwdtkfcwuk/AdController;)V
    //   1000: iload_1
    //   1001: i2l
    //   1002: invokevirtual 1777	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   1005: pop
    //   1006: return
    //   1007: astore 4
    //   1009: aload_0
    //   1010: invokespecial 1047	com/nwvlwgbwdtkfcwuk/AdController:A	()V
    //   1013: return
    //   1014: astore 4
    //   1016: aload_0
    //   1017: invokespecial 1047	com/nwvlwgbwdtkfcwuk/AdController:A	()V
    //   1020: return
    //   1021: astore 4
    //   1023: ldc 93
    //   1025: new 355	java/lang/StringBuilder
    //   1028: dup
    //   1029: invokespecial 356	java/lang/StringBuilder:<init>	()V
    //   1032: iconst_0
    //   1033: ldc_w 1812
    //   1036: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   1039: invokevirtual 378	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   1042: aload 4
    //   1044: invokevirtual 418	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1047: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1050: invokevirtual 364	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1053: invokestatic 332	com/nwvlwgbwdtkfcwuk/AdLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1056: ldc 93
    //   1058: aload 4
    //   1060: invokestatic 413	com/nwvlwgbwdtkfcwuk/AdLog:printStackTrace	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   1063: goto -260 -> 803
    //   1066: ldc_w 1118
    //   1069: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   1072: invokestatic 916	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1075: ldc_w 1786
    //   1078: invokestatic 323	com/nwvlwgbwdtkfcwuk/AdJSInterface:h	(Ljava/lang/String;)Ljava/lang/String;
    //   1081: aconst_null
    //   1082: checkcast 1122	[Ljava/lang/Class;
    //   1085: invokevirtual 1126	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1088: aload_0
    //   1089: getfield 309	com/nwvlwgbwdtkfcwuk/AdController:z	Lcom/nwvlwgbwdtkfcwuk/AdView;
    //   1092: aconst_null
    //   1093: checkcast 1128	[Ljava/lang/Object;
    //   1096: invokevirtual 1134	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1099: pop
    //   1100: aload_0
    //   1101: getfield 309	com/nwvlwgbwdtkfcwuk/AdController:z	Lcom/nwvlwgbwdtkfcwuk/AdView;
    //   1104: iconst_0
    //   1105: invokevirtual 526	com/nwvlwgbwdtkfcwuk/AdView:setBackgroundColor	(I)V
    //   1108: goto -230 -> 878
    //   1111: astore 4
    //   1113: goto -235 -> 878
    //   1116: aload_0
    //   1117: invokespecial 1047	com/nwvlwgbwdtkfcwuk/AdController:A	()V
    //   1120: return
    //   1121: aload_0
    //   1122: getfield 265	com/nwvlwgbwdtkfcwuk/AdController:xA	Lcom/nwvlwgbwdtkfcwuk/AdListener;
    //   1125: ifnull +17 -> 1142
    //   1128: aload_0
    //   1129: iconst_1
    //   1130: putfield 251	com/nwvlwgbwdtkfcwuk/AdController:PA	Z
    //   1133: aload_0
    //   1134: getfield 265	com/nwvlwgbwdtkfcwuk/AdController:xA	Lcom/nwvlwgbwdtkfcwuk/AdListener;
    //   1137: invokeinterface 709 1 0
    //   1142: aload_0
    //   1143: getfield 305	com/nwvlwgbwdtkfcwuk/AdController:dA	Lcom/nwvlwgbwdtkfcwuk/AdAudioListener;
    //   1146: ifnull -1008 -> 138
    //   1149: aload_0
    //   1150: iconst_1
    //   1151: putfield 281	com/nwvlwgbwdtkfcwuk/AdController:Z	Z
    //   1154: aload_0
    //   1155: getfield 305	com/nwvlwgbwdtkfcwuk/AdController:dA	Lcom/nwvlwgbwdtkfcwuk/AdAudioListener;
    //   1158: invokeinterface 712 1 0
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
    return this.hA.getSharedPreferences(AdJSInterface.h("Aetqtetyrr"), 0).getString(this.u, AdJSInterface.h("stqpb}c"));
  }
  
  public void audioAdRetrieved(Integer paramInteger)
  {
    AdLog.d("LBAdController", AdJSInterface.h("pbu~~VuEtcc~tats"));
    if ((this.D) || (this.fA))
    {
      AdLog.d("LBAdController", AdJSInterface.h("p~~p1c~7crebcy1qcx|7pbu~~VuEtcc~tats1vb7bc~exyv7ex1tptyr"));
      paramInteger = this.hA.getSharedPreferences(AdJSInterface.h("Aetqtetyrr"), 0).edit();
      paramInteger.putLong(this.u, System.currentTimeMillis());
      AdUtils.apply(paramInteger);
      this.i = true;
      if ((this.fA) && (this.dA != null)) {
        this.dA.onAdCached();
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
    } while (this.dA == null);
    this.dA.onAdFailed();
    this.Z = true;
  }
  
  public void checkForAudioAd(String paramString1, String paramString2)
  {
    if ((!this.aA) && (paramString1 != null) && (!paramString1.equals(AdJSInterface.h("b}{"))) && (!paramString1.equals("")))
    {
      if (this.CA.isNull(AdJSInterface.h("pspbu~~bc{"))) {}
      for (;;)
      {
        try
        {
          this.CA.put(AdJSInterface.h("pspbu~~bc{"), paramString1);
          this.CA.put(AdJSInterface.h("vubc{"), paramString2);
          if ((!this.Z) && (!this.CA.isNull(AdJSInterface.h("pspbu~~bc{"))))
          {
            if (this.n == null) {
              this.n = new AdShakeListener(true);
            }
            if (this.s == null)
            {
              this.y = ((AudioManager)this.hA.getSystemService(AdJSInterface.h("vdsxx")));
              this.s = ((SensorManager)this.hA.getSystemService(AdJSInterface.h("brd~e")));
              this.jA = 0.0F;
              this.pA = 9.80665F;
              this.k = 9.80665F;
            }
            retrieveAudioAd();
          }
          return;
        }
        catch (JSONException paramString1) {}
      }
    }
    AdLog.d("LBAdController", AdJSInterface.h("_x1vdsxx1t~zaxrc"));
  }
  
  public void destroyAd()
  {
    AdLog.i("LBAdController", AdJSInterface.h("urbccxhVu7rv}{ts"));
    this.aA = true;
    h();
    K();
  }
  
  public boolean getAdDestroyed()
  {
    return this.aA;
  }
  
  public boolean getAdLoaded()
  {
    return this.PA;
  }
  
  public boolean getLoadInBackground()
  {
    return this.v;
  }
  
  public boolean getOnAdLoaded()
  {
    return this.rA;
  }
  
  public void hideElements()
  {
    try
    {
      this.F.setVisibility(8);
      this.w.setVisibility(8);
      this.LA.setVisibility(8);
      this.M.setVisibility(8);
      this.yA.setVisibility(8);
      this.JA.setVisibility(8);
      this.sA.setVisibility(8);
      this.AA.setVisibility(8);
      this.HA.setVisibility(8);
      this.g.setVisibility(8);
      this.x.setVisibility(8);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void loadAd()
  {
    if (!(this.hA instanceof Activity))
    {
      Log.e("LBAdController", AdJSInterface.h("{~vuVu?87wvx{ts1:1V1Ap{xs1Vrcxaxch7xe7avbdts"));
      if (this.xA != null) {
        this.xA.onAdFailed();
      }
    }
    do
    {
      return;
      if ((this.b == null) || (this.b.getStatus() != AsyncTask.Status.RUNNING)) {
        break;
      }
    } while (!this.D);
    this.D = false;
    return;
    if ((this.H) && (f()))
    {
      AdLog.i("LBAdController", AdJSInterface.h("Rvrt7xd1ap{xs1:1p~~p1c~7ddt7epc"));
      this.v = false;
      this.D = false;
      A();
      this.eA = false;
      j();
      if (this.xA != null) {
        this.xA.onAdLoaded();
      }
      if (this.i)
      {
        m();
        this.i = false;
      }
      this.H = false;
      SharedPreferences.Editor localEditor = this.hA.getSharedPreferences(AdJSInterface.h("Aetqtetyrr"), 0).edit();
      localEditor.putLong(this.u, -1L);
      AdUtils.apply(localEditor);
      return;
    }
    this.D = false;
    this.eA = false;
    this.H = false;
    this.d = false;
    AdLog.i("LBAdController", AdJSInterface.h("{~vuVu7rv}{ts"));
    this.rA = false;
    if (!this.d)
    {
      this.o = true;
      this.aA = false;
      AdLog.i("LBAdController", AdJSInterface.h("rv}{xyv7}xpsPsXyxcxv}~kr"));
      M();
    }
    for (;;)
    {
      long l1 = System.currentTimeMillis();
      AdLog.d("LBAdController", l1);
      return;
      AdLog.i("LBAdController", AdJSInterface.h("tp{}~p1sxda{pnPs"));
      A();
    }
  }
  
  public void loadAdToCache()
  {
    if (!(this.hA instanceof Activity))
    {
      Log.e("LBAdController", AdJSInterface.h("{~vuVu?87wvx{ts1:1V1Ap{xs1Vrcxaxch7xe7avbdts"));
      if (this.xA != null) {
        this.xA.onAdFailed();
      }
      return;
    }
    AdLog.i("LBAdController", AdJSInterface.h("}xpsPsExRvrt7rv}{ts"));
    this.D = true;
    this.rA = false;
    if (!this.d)
    {
      this.o = true;
      this.aA = false;
      M();
      return;
    }
    A();
  }
  
  public void loadAudioAd()
  {
    if (this.a != null) {}
    label346:
    for (;;)
    {
      return;
      if ((this.b != null) && (this.b.getStatus() == AsyncTask.Status.RUNNING))
      {
        if (this.fA) {
          this.fA = false;
        }
      }
      else
      {
        if ((this.i) && (H()))
        {
          AdLog.i("LBAdController", AdJSInterface.h("Rvrt7xd1ap{xs1:1p~~p1c~7a{pn1vdsxx1qcx|7epc"));
          this.fA = false;
          m();
          this.i = false;
          this.H = false;
          SharedPreferences.Editor localEditor = this.hA.getSharedPreferences(AdJSInterface.h("Aetqtetyrr"), 0).edit();
          localEditor.putLong(this.u, -1L);
          AdUtils.apply(localEditor);
          return;
        }
        this.fA = false;
        this.i = false;
        this.d = false;
        AdLog.i("LBAdController", AdJSInterface.h("}xpsPbu~~Vu7rv}{ts"));
        if (!this.d)
        {
          this.EA = true;
          if (this.s == null)
          {
            this.n = new AdShakeListener(true);
            this.y = ((AudioManager)this.hA.getSystemService(AdJSInterface.h("vdsxx")));
            this.s = ((SensorManager)this.hA.getSystemService(AdJSInterface.h("brd~e")));
            this.jA = 0.0F;
            this.pA = 9.80665F;
            this.k = 9.80665F;
          }
          M();
        }
        for (;;)
        {
          if ((this.dA == null) || (this.r <= 0)) {
            break label346;
          }
          if (this.A == null) {
            this.A = new Runnable()
            {
              public void run()
              {
                try
                {
                  if ((!AdController.a(AdController.this)) && (!AdController.k(AdController.this)))
                  {
                    AdLog.i("LBAdController", AdDefines.h("#k\ra\034w#b>`?vlq>l+b)w)a"));
                    AdController.L(AdController.this).onAdProgress();
                    AdController.M(AdController.this).postDelayed(AdController.e(AdController.this), AdController.I(AdController.this) * 1000);
                  }
                  return;
                }
                catch (Exception localException)
                {
                  AdLog.e("LBAdController", AdDefines.h("`>w#wlr$`\"%#k\ra\034w#b>`?vlq>l+b)w)a"));
                  AdLog.printStackTrace("LBAdController", localException);
                }
              }
            };
          }
          if (this.X != null) {
            break;
          }
          this.X = new Handler();
          this.X.postDelayed(this.A, this.r * 1000);
          return;
          m();
        }
      }
    }
  }
  
  public void loadAudioAdToCache()
  {
    if (this.a != null) {
      return;
    }
    AdLog.i("LBAdController", AdJSInterface.h("{~vuVdsxxPsExRvrt7rv}{ts"));
    if (!this.d)
    {
      this.EA = true;
      this.fA = true;
      if (this.s == null)
      {
        this.n = new AdShakeListener(true);
        this.y = ((AudioManager)this.hA.getSystemService(AdJSInterface.h("vdsxx")));
        this.s = ((SensorManager)this.hA.getSystemService(AdJSInterface.h("brd~e")));
        this.jA = 0.0F;
        this.pA = 9.80665F;
        this.k = 9.80665F;
      }
      M();
      return;
    }
    m();
  }
  
  public void loadAudioTrack(long paramLong)
  {
    if (this.MA == null) {
      this.MA = new Runnable()
      {
        public void run()
        {
          SharedPreferences.Editor localEditor = AdController.this.hA.getSharedPreferences(AdController.h("y+L?L+L7J<"), 0).edit();
          localEditor.putBoolean(AdController.i(AdController.this), true);
          AdUtils.apply(localEditor);
          AdController.this.loadAudioAd();
        }
      };
    }
    if (this.uA == null) {
      this.uA = new Handler();
    }
    for (;;)
    {
      this.uA.postDelayed(this.MA, 60L * paramLong * 1000L);
      return;
      this.uA.removeCallbacks(this.MA);
    }
  }
  
  public void loadReEngagement()
  {
    int i1;
    if (this.b != null)
    {
      AdLog.i("LBAdController", this.b.getStatus());
      if (this.b.getStatus() == AsyncTask.Status.FINISHED) {
        i1 = 1;
      }
    }
    for (;;)
    {
      if (i1 != 0)
      {
        Object localObject = this.hA.getSharedPreferences(AdJSInterface.h("Aetqtetyrr"), 0);
        Context localContext = this.hA;
        String str = this.u;
        if (((SharedPreferences)localObject).getBoolean(AdJSInterface.h("etrppptztyev}vcz"), false)) {}
        for (localObject = AdJSInterface.h("crtyvvvr|rcNv}vcz");; localObject = "reengagement")
        {
          this.b = new AdTask(this, localContext, str, (String)localObject);
          this.b.setSubId(this.l);
          this.b.setTokens(this.O);
          h(this.b, new String[] { "" });
          return;
          i1 = 0;
          break;
        }
      }
      AdLog.i("LBAdController", AdJSInterface.h("Y~7cr`btde7ex1ut7|vur"));
      return;
      i1 = 1;
    }
  }
  
  public void loadStartAd(String paramString1, String paramString2)
  {
    loadAd();
    this.T = new AdController(this.hA, paramString1);
    this.T.loadAudioTrack(2L);
    new AdController(this.hA, paramString2).loadReEngagement();
  }
  
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
    k();
  }
  
  public void reEngagementInitialized()
  {
    if (this.CA != null) {
      B();
    }
    SharedPreferences localSharedPreferences = this.hA.getSharedPreferences(AdJSInterface.h("Aetqtetyrr"), 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (localSharedPreferences.getBoolean(AdJSInterface.h("etrppptztyev}vcz"), false))
    {
      localEditor.putBoolean(AdJSInterface.h("etrppptztyev}vcz"), false);
      AdUtils.apply(localEditor);
      E();
    }
  }
  
  public void retrieveAudioAd()
  {
    int i2 = 1;
    AdLog.d("LBAdController", AdJSInterface.h("etcc~tatVdsxxPs"));
    for (;;)
    {
      try
      {
        if (this.CA.get(AdJSInterface.h("b~`")).equals(AdJSInterface.h("&")))
        {
          i1 = i2;
          if (this.K != null)
          {
            if (this.K.getStatus() != AsyncTask.Status.FINISHED) {
              break label161;
            }
            i1 = i2;
          }
          if (i1 != 0)
          {
            AdLog.d("LBAdController", AdJSInterface.h("vxxyv7ex1zp|t7cr`btde7pyu7wrety7pbu~~7ps"));
            this.K = new AdAudioTask(this, this.hA);
            h(this.K, new String[] { this.CA.getString(AdJSInterface.h("pspbu~~bc{")) });
          }
        }
        else
        {
          this.EA = false;
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
    this.aA = paramBoolean;
  }
  
  public void setAdLoaded(boolean paramBoolean)
  {
    this.PA = paramBoolean;
  }
  
  public void setAdditionalDockingMargin(int paramInt)
  {
    this.I = paramInt;
    AdLog.i("LBAdController", paramInt);
  }
  
  public void setCompleted(boolean paramBoolean)
  {
    this.mA = paramBoolean;
  }
  
  public void setHTML(String paramString)
  {
    if (this.z != null)
    {
      paramString = paramString + AdJSInterface.h("+>ez})");
      this.z.loadHTMLWrap(paramString);
    }
  }
  
  public void setHTMLAds(boolean paramBoolean)
  {
    this.h = paramBoolean;
  }
  
  public void setHomeLoaded(boolean paramBoolean)
  {
    this.iA = paramBoolean;
  }
  
  public void setLayout(RelativeLayout paramRelativeLayout)
  {
    this.g = paramRelativeLayout;
  }
  
  public void setLoadInBackground(boolean paramBoolean)
  {
    this.v = paramBoolean;
  }
  
  public void setLoading(boolean paramBoolean)
  {
    this.eA = paramBoolean;
  }
  
  public void setOnAdLoaded(boolean paramBoolean)
  {
    this.rA = paramBoolean;
    if ((this.v) && (!this.aA)) {
      ((Activity)this.hA).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (AdController.c(AdController.this))
          {
            SharedPreferences.Editor localEditor = AdController.this.hA.getSharedPreferences(AdRequest.h("%z\020n\020z\020f\026m"), 0).edit();
            localEditor.putLong(AdController.i(AdController.this), System.currentTimeMillis());
            AdUtils.apply(localEditor);
            AdController.c(AdController.this, true);
            if (AdController.G(AdController.this) != null) {
              AdController.G(AdController.this).onAdCached();
            }
            return;
          }
          if ((!AdController.c(AdController.this)) && (AdController.G(AdController.this) != null))
          {
            AdLog.i("LBAdController", AdRequest.h("\001z\034o\022m\007(\032f4l9g\024l\020l"));
            AdController.G(AdController.this).onAdLoaded();
          }
          AdController.I(AdController.this, false);
          AdController.E(AdController.this);
          AdController.B(AdController.this, false);
        }
      });
    }
  }
  
  public void setOnProgressInterval(int paramInt)
  {
    this.r = paramInt;
  }
  
  public void setResults(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.CA = paramJSONObject;
      paramJSONObject = this.hA.getSharedPreferences(AdJSInterface.h("Aetqtetyrr"), 0).edit();
    }
    try
    {
      paramJSONObject.putLong(AdJSInterface.h("vuettyrr|e~|r"), this.CA.getLong(AdJSInterface.h("vuettyrr|e~|r")));
      AdUtils.apply(paramJSONObject);
      AdLog.d("LBAdController", this.CA.getLong(AdJSInterface.h("vuettyrr|e~|r")));
      return;
    }
    catch (Exception paramJSONObject)
    {
      AdLog.d("LBAdController", paramJSONObject.getMessage());
    }
  }
  
  public void setSubId(String paramString)
  {
    this.l = paramString;
  }
  
  public void setTokens(List<NameValuePair> paramList)
  {
    this.O = paramList;
  }
  
  public void showElements()
  {
    try
    {
      this.F.setVisibility(0);
      this.w.setVisibility(0);
      this.LA.setVisibility(0);
      this.M.setVisibility(0);
      this.yA.setVisibility(0);
      this.JA.setVisibility(0);
      this.sA.setVisibility(0);
      this.AA.setVisibility(0);
      this.HA.setVisibility(0);
      this.g.setVisibility(0);
      this.x.setVisibility(0);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void showInternetDialog()
  {
    if ((this.hA != null) && ((this.hA instanceof Activity)))
    {
      if (this.E == null)
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.hA);
        localBuilder.setMessage(AdJSInterface.h("Xyercytc1y~c1vgvx{pu}r")).setCancelable(false).setPositiveButton(AdJSInterface.h("T}xbr"), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            AdController.B(AdController.this).dismiss();
          }
        });
        this.E = localBuilder.create();
      }
      this.E.show();
    }
  }
  
  public void stopAllListeners() {}
  
  public void triggerAdCompleted() {}
  
  public void triggerAdFailed()
  {
    AdLog.e("LBAdController", AdJSInterface.h("_x1^ctere7rxytte~~y1stctteru91Y~7Psb7}xpsts"));
    if (this.xA != null) {}
    for (;;)
    {
      try
      {
        AdLog.i("LBAdController", AdJSInterface.h("~yPsWvx{ts1cc~vptets"));
        this.xA.onAdFailed();
        this.PA = true;
        if (this.dA != null)
        {
          this.dA.onAdFailed();
          this.Z = true;
        }
        return;
      }
      catch (Exception localException)
      {
        AdLog.i("LBAdController", AdJSInterface.h("Tecxc7fx{t7rv}{xyv7~yPsWvx{ts"));
        AdLog.printStackTrace("LBAdController", localException);
      }
    }
  }
  
  private class AdClientPixel
    extends AsyncTask<String, Void, Boolean>
  {
    protected Boolean h(String... paramVarArgs)
    {
      Object localObject = paramVarArgs[0];
      if ((localObject != null) && (!((String)localObject).equals("")))
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        localBasicHttpParams.setParameter(AdController.h("1]-YwY+F-F:F5\007/L+Z0F7"), HttpVersion.HTTP_1_1);
        paramVarArgs = new DefaultHttpClient(localBasicHttpParams);
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 4000);
        localObject = new HttpGet((String)localObject);
      }
      try
      {
        if (paramVarArgs.execute((HttpUriRequest)localObject).getStatusLine().getStatusCode() == 200) {
          AdController.h(this.e, true);
        }
        return Boolean.valueOf(false);
      }
      catch (Exception paramVarArgs)
      {
        for (;;) {}
      }
    }
    
    protected void h(boolean paramBoolean)
    {
      if (paramBoolean) {
        AdController.h(this.e, true);
      }
    }
  }
  
  private class AdShakeListener
    implements SensorEventListener
  {
    public AdShakeListener(boolean paramBoolean)
    {
      this.M = paramBoolean;
      if (this.M)
      {
        this.B = new Handler();
        this.f = new Runnable()
        {
          public void run()
          {
            AdLog.i("LBAdController", AdWakeLock.h("p\005b\006fMb\t#\tf\031f\016w\004l\003#\b{\035j\037f\036"));
            AdController.AdShakeListener.I(AdController.AdShakeListener.this);
            AdController.AdShakeListener.h(AdController.AdShakeListener.this);
          }
        };
      }
    }
    
    public void destroySensor()
    {
      if (this.I)
      {
        h();
        this.B.removeCallbacks(this.f);
      }
    }
    
    public void enableShakeDetection()
    {
      this.I = true;
      AdController.H(AdController.this).registerListener(this, AdController.H(AdController.this).getDefaultSensor(1), 1);
      AdLog.i("LBAdController", AdEncryption.h("T\013F\bBCB\025B\rSCC\006S\006D\027N\fICB\rF\001K\006C"));
    }
    
    public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
    
    public void onSensorChanged(SensorEvent paramSensorEvent)
    {
      float f1 = paramSensorEvent.values[0];
      float f2 = paramSensorEvent.values[1];
      float f3 = paramSensorEvent.values[2];
      AdController.c(AdController.this, AdController.J(AdController.this));
      AdController.h(AdController.this, FloatMath.sqrt(f1 * f1 + f2 * f2 + f3 * f3));
      f1 = AdController.J(AdController.this);
      f2 = AdController.b(AdController.this);
      AdController.I(AdController.this, f1 - f2 + AdController.F(AdController.this) * 0.9F);
      if (AdController.F(AdController.this) > 2.5F)
      {
        long l = System.currentTimeMillis();
        if (this.C == 0L)
        {
          this.C = l;
          this.a = l;
        }
        if (l - this.a < 300L)
        {
          this.a = l;
          this.i += 1;
          if ((this.i >= this.g) && (l - this.C < 1500L))
          {
            AdLog.i("LBAdController", AdEncryption.h("Q\002K\nCCT\013F\bB"));
            c();
            h();
            if (this.M)
            {
              if ((AdController.m(AdController.this) != null) && (AdController.m(AdController.this).isPlaying()))
              {
                AdController.m(AdController.this).stop();
                AdController.h(AdController.this, null);
                if (AdController.L(AdController.this) != null) {
                  AdController.L(AdController.this).onAdFinished();
                }
                AdController.D(AdController.this).setStreamVolume(3, AdController.K(AdController.this), 8);
              }
              this.B.removeCallbacks(this.f);
              I();
            }
          }
        }
        return;
      }
      c();
    }
    
    public void setShakeTime(int paramInt)
    {
      this.g = paramInt;
    }
    
    public void setValidTimes(int paramInt)
    {
      this.e = paramInt;
    }
    
    public void setupAudioAdHandler()
    {
      this.B.postDelayed(this.f, this.e);
    }
  }
  
  private class ContextList
    extends AsyncTask<Void, Void, String>
  {
    public ContextList(Context paramContext)
    {
      this.f = paramContext;
      this.e = this.f.getSharedPreferences(AdJSInterface.h("Aetqtetyrr"), 0);
      this.M = this.e.edit();
    }
    
    protected String h(Void... paramVarArgs)
    {
      AdLog.i("LBAdController", AdJSInterface.h("P~~p1c~7bcpee7PdhyrCpdz7ex1ptytepct7rxctoed"));
      paramVarArgs = new StringBuilder();
      AdLog.d("LBAdController", System.currentTimeMillis());
      Object localObject1 = this.f.getPackageManager().getInstalledPackages(128);
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
        localObject1 = (ActivityManager)this.f.getSystemService(AdJSInterface.h("pte~g~en"));
        if (this.f.checkCallingOrSelfPermission(AdWakeLock.h("b\003g\037l\004gCs\bq\000j\036p\004l\003-*F9\\9B>H>")) != 0) {
          break label644;
        }
        localObject1 = ((ActivityManager)localObject1).getRunningTasks(Integer.MAX_VALUE).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ActivityManager.RunningTaskInfo)((Iterator)localObject1).next();
          try
          {
            paramVarArgs.append(URLEncoder.encode(new StringBuilder().insert(0, AdWakeLock.h("w\004w\001fP")).append(this.f.getPackageManager().getApplicationInfo(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName(), 0).loadLabel(this.f.getPackageManager()).toString()).append(AdJSInterface.h("1avr|ppt*")).append(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName()).toString(), AdWakeLock.h("8W+.U")));
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
            localStringBuilder1.append(localException1.applicationInfo.loadLabel(this.f.getPackageManager()).toString());
            localStringBuilder1.append(localException1.applicationInfo.packageName);
            StringBuilder localStringBuilder2 = new StringBuilder().insert(0, AdJSInterface.h("1xybcp{}*"));
            if (Build.VERSION.SDK_INT < 9) {
              break label631;
            }
            l = localException1.firstInstallTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder2 = new StringBuilder().insert(0, AdWakeLock.h("%\030s\tb\031fP"));
            if (Build.VERSION.SDK_INT < 9) {
              break label636;
            }
            l = localException1.lastUpdateTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder1.append(localException1.versionCode);
            localStringBuilder1.append(localException1.versionName);
            paramVarArgs.append(URLEncoder.encode(localStringBuilder1.toString(), AdWakeLock.h("8W+.U")));
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
    
    protected void h(String paramString)
    {
      this.M.putBoolean(AdWakeLock.h("P)\\.L#W([9P2J#S?L*Q(P>"), false);
      AdUtils.apply(this.M);
      if ((paramString != null) && (!paramString.equals("")))
      {
        this.M.putString(AdJSInterface.h("DUHRX_CTOED"), paramString);
        this.M.putLong(AdWakeLock.h(">G2@\"M9F5W>\\8S)B9F2W$N("), System.currentTimeMillis());
        AdUtils.apply(this.M);
      }
    }
    
    protected void onPreExecute()
    {
      this.M.putBoolean(AdWakeLock.h("P)\\.L#W([9P2J#S?L*Q(P>"), true);
      AdUtils.apply(this.M);
    }
  }
  
  public static class Dimensions
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<Dimensions> f = new Parcelable.Creator()
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
    public int M;
    public int e;
    public int g;
    public int i;
    
    public Dimensions()
    {
      this.i = -1;
      this.e = -1;
      this.g = -1;
      this.M = -1;
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
      this.H = paramContext;
      this.m = paramInt;
      this.M = paramCharSequence1;
      this.F = paramCharSequence2;
      this.g = paramCharSequence3;
      this.G = paramNotificationManager;
      this.I = paramPendingIntent;
      AdLog.i("LBAdController", AdDefines.h("B#l\"blq#%\n`8f$%\005h-b)$"));
    }
    
    protected Bitmap h(String... paramVarArgs)
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
    protected void h(Bitmap paramBitmap)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnull +465 -> 466
      //   4: ldc 80
      //   6: ldc -90
      //   8: invokestatic 111	com/nwvlwgbwdtkfcwuk/AdEncryption:h	(Ljava/lang/String;)Ljava/lang/String;
      //   11: invokestatic 93	com/nwvlwgbwdtkfcwuk/AdLog:i	(Ljava/lang/String;Ljava/lang/String;)V
      //   14: new 105	java/lang/StringBuilder
      //   17: dup
      //   18: invokespecial 106	java/lang/StringBuilder:<init>	()V
      //   21: iconst_0
      //   22: aload_0
      //   23: getfield 66	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:H	Landroid/content/Context;
      //   26: invokevirtual 171	android/content/Context:getPackageName	()Ljava/lang/String;
      //   29: invokevirtual 115	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   32: aload_0
      //   33: getfield 40	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:K	Ljava/lang/String;
      //   36: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   39: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   42: invokestatic 177	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   45: astore 7
      //   47: aload 7
      //   49: aload_0
      //   50: getfield 48	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:i	Ljava/lang/String;
      //   53: invokevirtual 181	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   56: aload 7
      //   58: invokevirtual 187	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   61: istore_2
      //   62: new 105	java/lang/StringBuilder
      //   65: dup
      //   66: invokespecial 106	java/lang/StringBuilder:<init>	()V
      //   69: iconst_0
      //   70: aload_0
      //   71: getfield 66	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:H	Landroid/content/Context;
      //   74: invokevirtual 171	android/content/Context:getPackageName	()Ljava/lang/String;
      //   77: invokevirtual 115	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   80: aload_0
      //   81: getfield 44	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:E	Ljava/lang/String;
      //   84: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   87: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   90: invokestatic 177	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   93: astore 7
      //   95: aload 7
      //   97: aload_0
      //   98: getfield 52	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:B	Ljava/lang/String;
      //   101: invokevirtual 181	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   104: aload 7
      //   106: invokevirtual 187	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   109: istore_3
      //   110: aload 7
      //   112: aload_0
      //   113: getfield 56	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:C	Ljava/lang/String;
      //   116: invokevirtual 181	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   119: aload 7
      //   121: invokevirtual 187	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   124: istore 4
      //   126: aload 7
      //   128: aload_0
      //   129: getfield 60	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:e	Ljava/lang/String;
      //   132: invokevirtual 181	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   135: aload 7
      //   137: invokevirtual 187	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   140: istore 5
      //   142: aload 7
      //   144: aload_0
      //   145: getfield 64	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:f	Ljava/lang/String;
      //   148: invokevirtual 181	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   151: aload 7
      //   153: invokevirtual 187	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   156: istore 6
      //   158: iload 5
      //   160: iconst_m1
      //   161: if_icmpeq +175 -> 336
      //   164: iload 6
      //   166: iconst_m1
      //   167: if_icmpeq +169 -> 336
      //   170: new 189	android/widget/RemoteViews
      //   173: dup
      //   174: aload_0
      //   175: getfield 66	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:H	Landroid/content/Context;
      //   178: invokevirtual 171	android/content/Context:getPackageName	()Ljava/lang/String;
      //   181: iload_2
      //   182: invokespecial 192	android/widget/RemoteViews:<init>	(Ljava/lang/String;I)V
      //   185: astore 7
      //   187: aload 7
      //   189: iload_3
      //   190: ldc -62
      //   192: invokestatic 88	com/nwvlwgbwdtkfcwuk/AdDefines:h	(Ljava/lang/String;)Ljava/lang/String;
      //   195: aload_0
      //   196: getfield 33	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:a	Lcom/nwvlwgbwdtkfcwuk/AdController;
      //   199: invokestatic 198	com/nwvlwgbwdtkfcwuk/AdController:A	(Lcom/nwvlwgbwdtkfcwuk/AdController;)Lorg/json/JSONObject;
      //   202: ldc -56
      //   204: invokestatic 111	com/nwvlwgbwdtkfcwuk/AdEncryption:h	(Ljava/lang/String;)Ljava/lang/String;
      //   207: invokevirtual 205	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   210: invokestatic 211	android/graphics/Color:parseColor	(Ljava/lang/String;)I
      //   213: invokevirtual 215	android/widget/RemoteViews:setInt	(ILjava/lang/String;I)V
      //   216: aload 7
      //   218: iload 5
      //   220: aload_1
      //   221: invokevirtual 219	android/widget/RemoteViews:setImageViewBitmap	(ILandroid/graphics/Bitmap;)V
      //   224: aload 7
      //   226: iload 6
      //   228: aload_0
      //   229: getfield 33	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:a	Lcom/nwvlwgbwdtkfcwuk/AdController;
      //   232: invokestatic 198	com/nwvlwgbwdtkfcwuk/AdController:A	(Lcom/nwvlwgbwdtkfcwuk/AdController;)Lorg/json/JSONObject;
      //   235: ldc -35
      //   237: invokestatic 88	com/nwvlwgbwdtkfcwuk/AdDefines:h	(Ljava/lang/String;)Ljava/lang/String;
      //   240: invokevirtual 205	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   243: invokevirtual 225	android/widget/RemoteViews:setTextViewText	(ILjava/lang/CharSequence;)V
      //   246: aload 7
      //   248: iload 6
      //   250: aload_0
      //   251: getfield 33	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:a	Lcom/nwvlwgbwdtkfcwuk/AdController;
      //   254: invokestatic 198	com/nwvlwgbwdtkfcwuk/AdController:A	(Lcom/nwvlwgbwdtkfcwuk/AdController;)Lorg/json/JSONObject;
      //   257: ldc -29
      //   259: invokestatic 111	com/nwvlwgbwdtkfcwuk/AdEncryption:h	(Ljava/lang/String;)Ljava/lang/String;
      //   262: invokevirtual 205	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   265: invokestatic 211	android/graphics/Color:parseColor	(Ljava/lang/String;)I
      //   268: invokevirtual 231	android/widget/RemoteViews:setTextColor	(II)V
      //   271: aload 7
      //   273: iload 4
      //   275: iconst_4
      //   276: invokevirtual 234	android/widget/RemoteViews:setViewVisibility	(II)V
      //   279: ldc 80
      //   281: ldc -20
      //   283: invokestatic 88	com/nwvlwgbwdtkfcwuk/AdDefines:h	(Ljava/lang/String;)Ljava/lang/String;
      //   286: invokestatic 239	com/nwvlwgbwdtkfcwuk/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   289: aload_0
      //   290: getfield 68	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:m	I
      //   293: iconst_m1
      //   294: if_icmpeq +217 -> 511
      //   297: aload_0
      //   298: getfield 33	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:a	Lcom/nwvlwgbwdtkfcwuk/AdController;
      //   301: aload_0
      //   302: getfield 66	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:H	Landroid/content/Context;
      //   305: aload_0
      //   306: getfield 68	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:m	I
      //   309: aload_0
      //   310: getfield 70	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:M	Ljava/lang/CharSequence;
      //   313: aload_0
      //   314: getfield 72	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:F	Ljava/lang/CharSequence;
      //   317: aload_0
      //   318: getfield 74	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:g	Ljava/lang/CharSequence;
      //   321: aload_0
      //   322: getfield 76	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:G	Landroid/app/NotificationManager;
      //   325: aload_0
      //   326: getfield 78	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:I	Landroid/app/PendingIntent;
      //   329: aload 7
      //   331: invokestatic 242	com/nwvlwgbwdtkfcwuk/AdController:h	(Lcom/nwvlwgbwdtkfcwuk/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   334: pop
      //   335: return
      //   336: aload_0
      //   337: getfield 68	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:m	I
      //   340: iconst_m1
      //   341: if_icmpeq +170 -> 511
      //   344: aload_0
      //   345: getfield 33	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:a	Lcom/nwvlwgbwdtkfcwuk/AdController;
      //   348: aload_0
      //   349: getfield 66	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:H	Landroid/content/Context;
      //   352: aload_0
      //   353: getfield 68	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:m	I
      //   356: aload_0
      //   357: getfield 70	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:M	Ljava/lang/CharSequence;
      //   360: aload_0
      //   361: getfield 72	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:F	Ljava/lang/CharSequence;
      //   364: aload_0
      //   365: getfield 74	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:g	Ljava/lang/CharSequence;
      //   368: aload_0
      //   369: getfield 76	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:G	Landroid/app/NotificationManager;
      //   372: aload_0
      //   373: getfield 78	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:I	Landroid/app/PendingIntent;
      //   376: aconst_null
      //   377: invokestatic 242	com/nwvlwgbwdtkfcwuk/AdController:h	(Lcom/nwvlwgbwdtkfcwuk/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   380: pop
      //   381: return
      //   382: astore_1
      //   383: ldc 80
      //   385: new 105	java/lang/StringBuilder
      //   388: dup
      //   389: invokespecial 106	java/lang/StringBuilder:<init>	()V
      //   392: iconst_0
      //   393: ldc -12
      //   395: invokestatic 111	com/nwvlwgbwdtkfcwuk/AdEncryption:h	(Ljava/lang/String;)Ljava/lang/String;
      //   398: invokevirtual 115	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   401: aload_1
      //   402: invokevirtual 157	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   405: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   408: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   411: invokestatic 159	com/nwvlwgbwdtkfcwuk/AdLog:e	(Ljava/lang/String;Ljava/lang/String;)V
      //   414: ldc 80
      //   416: aload_1
      //   417: invokestatic 163	com/nwvlwgbwdtkfcwuk/AdLog:printStackTrace	(Ljava/lang/String;Ljava/lang/Exception;)V
      //   420: aload_0
      //   421: getfield 68	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:m	I
      //   424: iconst_m1
      //   425: if_icmpeq +86 -> 511
      //   428: aload_0
      //   429: getfield 33	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:a	Lcom/nwvlwgbwdtkfcwuk/AdController;
      //   432: aload_0
      //   433: getfield 66	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:H	Landroid/content/Context;
      //   436: aload_0
      //   437: getfield 68	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:m	I
      //   440: aload_0
      //   441: getfield 70	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:M	Ljava/lang/CharSequence;
      //   444: aload_0
      //   445: getfield 72	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:F	Ljava/lang/CharSequence;
      //   448: aload_0
      //   449: getfield 74	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:g	Ljava/lang/CharSequence;
      //   452: aload_0
      //   453: getfield 76	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:G	Landroid/app/NotificationManager;
      //   456: aload_0
      //   457: getfield 78	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:I	Landroid/app/PendingIntent;
      //   460: aconst_null
      //   461: invokestatic 242	com/nwvlwgbwdtkfcwuk/AdController:h	(Lcom/nwvlwgbwdtkfcwuk/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   464: pop
      //   465: return
      //   466: aload_0
      //   467: getfield 68	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:m	I
      //   470: iconst_m1
      //   471: if_icmpeq +40 -> 511
      //   474: aload_0
      //   475: getfield 33	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:a	Lcom/nwvlwgbwdtkfcwuk/AdController;
      //   478: aload_0
      //   479: getfield 66	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:H	Landroid/content/Context;
      //   482: aload_0
      //   483: getfield 68	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:m	I
      //   486: aload_0
      //   487: getfield 70	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:M	Ljava/lang/CharSequence;
      //   490: aload_0
      //   491: getfield 72	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:F	Ljava/lang/CharSequence;
      //   494: aload_0
      //   495: getfield 74	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:g	Ljava/lang/CharSequence;
      //   498: aload_0
      //   499: getfield 76	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:G	Landroid/app/NotificationManager;
      //   502: aload_0
      //   503: getfield 78	com/nwvlwgbwdtkfcwuk/AdController$FetchImage:I	Landroid/app/PendingIntent;
      //   506: aconst_null
      //   507: invokestatic 242	com/nwvlwgbwdtkfcwuk/AdController:h	(Lcom/nwvlwgbwdtkfcwuk/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
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
      //   61	121	2	j	int
      //   109	81	3	k	int
      //   124	150	4	n	int
      //   140	79	5	i1	int
      //   156	93	6	i2	int
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
    public static final Parcelable.Creator<PlayerProperties> B = new Parcelable.Creator()
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
    public boolean I;
    public String K;
    public boolean M;
    public boolean e;
    public String f;
    public boolean g;
    public boolean i;
    
    public PlayerProperties()
    {
      this.M = true;
      this.I = true;
      this.g = false;
      this.e = false;
      this.f = "normal";
      this.K = "normal";
      this.i = false;
    }
    
    public PlayerProperties(Parcel paramParcel)
    {
      super();
    }
    
    public boolean doLoop()
    {
      return this.e;
    }
    
    public boolean doMute()
    {
      return this.g;
    }
    
    public boolean exitOnComplete()
    {
      return this.f.equalsIgnoreCase("exit");
    }
    
    public boolean isAutoPlay()
    {
      return this.I == true;
    }
    
    public boolean isFullScreen()
    {
      return this.K.equalsIgnoreCase("fullscreen");
    }
    
    public void muteAudio()
    {
      this.g = true;
    }
    
    public void setProperties(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String paramString1, String paramString2)
    {
      this.I = paramBoolean2;
      this.M = paramBoolean3;
      this.e = paramBoolean5;
      this.g = paramBoolean1;
      this.K = paramString1;
      this.f = paramString2;
      this.i = paramBoolean4;
    }
    
    public void setStopStyle(String paramString)
    {
      this.f = paramString;
    }
    
    public boolean showControl()
    {
      return this.M;
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
    public int M;
    public boolean e;
    public float f;
    
    public Properties()
    {
      this.e = false;
      this.M = 0;
      this.f = 0.0F;
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
            if (((String)localObject).equals("class com.nwvlwgbwdtkfcwuk.AdNavigationStringEnum")) {
              localField.set(this, AdNavigationStringEnum.fromString(paramParcel.readString()));
            } else if (((String)localObject).equals("class com.nwvlwgbwdtkfcwuk.AdTransitionStringEnum")) {
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
            if (((String)localObject2).equals("class com.nwvlwgbwdtkfcwuk.AdNavigationStringEnum")) {
              paramParcel.writeString(((AdNavigationStringEnum)((Field)localObject1).get(this)).getText());
            } else if (((String)localObject2).equals("class com.nwvlwgbwdtkfcwuk.AdTransitionStringEnum")) {
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
