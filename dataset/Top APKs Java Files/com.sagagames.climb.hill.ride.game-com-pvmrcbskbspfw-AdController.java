package com.pvmrcbskbspfw;

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
  public static final String H = "normal";
  public static final String hA = "fullscreen";
  public static final String p = "LBAdController";
  protected AdView GA;
  protected Context Z;
  
  public AdController(Context paramContext, String paramString)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
  }
  
  public AdController(Context paramContext, String paramString, WebView paramWebView)
  {
    this.Z = paramContext;
    this.V = paramString;
    this.i = paramWebView;
    this.MA = new RelativeLayout(this.Z);
    d();
  }
  
  public AdController(Context paramContext, String paramString, RelativeLayout paramRelativeLayout)
  {
    this.Z = paramContext;
    this.V = paramString;
    paramContext = paramRelativeLayout;
    if (paramRelativeLayout == null) {
      paramContext = new RelativeLayout(this.Z);
    }
    this.MA = paramContext;
    this.i = null;
    d();
  }
  
  public AdController(Context paramContext, String paramString, AdAudioListener paramAdAudioListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.KA = paramAdAudioListener;
  }
  
  public AdController(Context paramContext, String paramString, AdListener paramAdListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.HA = paramAdListener;
  }
  
  public AdController(AdView paramAdView, Context paramContext)
  {
    this.GA = paramAdView;
    this.Z = paramContext;
  }
  
  protected static Object D(JSONObject paramJSONObject, Class<?> paramClass)
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
          boolean bool = str1.startsWith(D("^"));
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
          if (!str2.equals("class com.pvmrcbskbspfw.AdNavigationStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdNavigationStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
          if (!str2.equals("class com.pvmrcbskbspfw.AdTransitionStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdTransitionStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
        }
        try
        {
          if (str1.startsWith(D("^`\005")))
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
  
  public static String D(String paramString)
  {
    int i1 = paramString.length();
    char[] arrayOfChar = new char[i1];
    int i2 = i1 - 1;
    for (i1 = i2; i2 >= 0; i1 = i2)
    {
      i2 = paramString.charAt(i1);
      int i3 = i1 - 1;
      arrayOfChar[i1] = ((char)(i2 ^ 0x7D));
      if (i3 < 0) {
        break;
      }
      i2 = i3 - 1;
      arrayOfChar[i3] = ((char)(paramString.charAt(i3) ^ 0x50));
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
    //   4: putfield 243	com/pvmrcbskbspfw/AdController:o	Z
    //   7: aload_0
    //   8: getfield 294	com/pvmrcbskbspfw/AdController:Z	Landroid/content/Context;
    //   11: ldc_w 318
    //   14: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   17: iconst_0
    //   18: invokevirtual 327	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   21: astore 4
    //   23: aload 4
    //   25: invokeinterface 1365 1 0
    //   30: astore 5
    //   32: aload_0
    //   33: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   36: ifnull +1085 -> 1121
    //   39: aload 5
    //   41: ldc_w 1696
    //   44: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   47: aload_0
    //   48: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   51: ldc_w 1696
    //   54: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   57: invokevirtual 1699	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   60: invokeinterface 1510 4 0
    //   65: pop
    //   66: aload 5
    //   68: invokestatic 1381	com/pvmrcbskbspfw/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   71: aload_0
    //   72: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   75: ldc_w 451
    //   78: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   81: invokevirtual 384	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   84: ldc_w 1022
    //   87: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   90: invokevirtual 398	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   93: ifeq +53 -> 146
    //   96: aload_0
    //   97: getfield 265	com/pvmrcbskbspfw/AdController:HA	Lcom/pvmrcbskbspfw/AdListener;
    //   100: ifnull +17 -> 117
    //   103: aload_0
    //   104: iconst_1
    //   105: putfield 251	com/pvmrcbskbspfw/AdController:g	Z
    //   108: aload_0
    //   109: getfield 265	com/pvmrcbskbspfw/AdController:HA	Lcom/pvmrcbskbspfw/AdListener;
    //   112: invokeinterface 738 1 0
    //   117: aload_0
    //   118: getfield 305	com/pvmrcbskbspfw/AdController:KA	Lcom/pvmrcbskbspfw/AdAudioListener;
    //   121: ifnull +17 -> 138
    //   124: aload_0
    //   125: iconst_1
    //   126: putfield 281	com/pvmrcbskbspfw/AdController:dA	Z
    //   129: aload_0
    //   130: getfield 305	com/pvmrcbskbspfw/AdController:KA	Lcom/pvmrcbskbspfw/AdAudioListener;
    //   133: invokeinterface 741 1 0
    //   138: return
    //   139: astore 6
    //   141: goto -70 -> 71
    //   144: astore 6
    //   146: aload_0
    //   147: getfield 294	com/pvmrcbskbspfw/AdController:Z	Landroid/content/Context;
    //   150: ifnull +194 -> 344
    //   153: aload_0
    //   154: getfield 294	com/pvmrcbskbspfw/AdController:Z	Landroid/content/Context;
    //   157: instanceof 711
    //   160: ifeq +184 -> 344
    //   163: new 1701	android/util/DisplayMetrics
    //   166: dup
    //   167: invokespecial 1702	android/util/DisplayMetrics:<init>	()V
    //   170: astore 6
    //   172: aload_0
    //   173: getfield 294	com/pvmrcbskbspfw/AdController:Z	Landroid/content/Context;
    //   176: checkcast 711	android/app/Activity
    //   179: invokevirtual 1706	android/app/Activity:getWindowManager	()Landroid/view/WindowManager;
    //   182: invokeinterface 1712 1 0
    //   187: aload 6
    //   189: invokevirtual 1718	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   192: new 1720	android/graphics/Rect
    //   195: dup
    //   196: invokespecial 1721	android/graphics/Rect:<init>	()V
    //   199: astore 7
    //   201: aload_0
    //   202: getfield 294	com/pvmrcbskbspfw/AdController:Z	Landroid/content/Context;
    //   205: checkcast 711	android/app/Activity
    //   208: invokevirtual 1725	android/app/Activity:getWindow	()Landroid/view/Window;
    //   211: astore 8
    //   213: aload 8
    //   215: invokevirtual 1731	android/view/Window:getDecorView	()Landroid/view/View;
    //   218: aload 7
    //   220: invokevirtual 1735	android/view/View:getWindowVisibleDisplayFrame	(Landroid/graphics/Rect;)V
    //   223: aload 7
    //   225: getfield 1738	android/graphics/Rect:top	I
    //   228: istore_3
    //   229: aload 8
    //   231: ldc_w 1551
    //   234: invokevirtual 1739	android/view/Window:findViewById	(I)Landroid/view/View;
    //   237: invokevirtual 1742	android/view/View:getTop	()I
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
    //   253: getfield 1745	android/util/DisplayMetrics:widthPixels	I
    //   256: putfield 707	com/pvmrcbskbspfw/AdController:h	I
    //   259: aload_0
    //   260: aload 6
    //   262: getfield 1748	android/util/DisplayMetrics:heightPixels	I
    //   265: iload_3
    //   266: isub
    //   267: iload_1
    //   268: isub
    //   269: putfield 709	com/pvmrcbskbspfw/AdController:c	I
    //   272: ldc 110
    //   274: new 329	java/lang/StringBuilder
    //   277: dup
    //   278: invokespecial 330	java/lang/StringBuilder:<init>	()V
    //   281: iconst_0
    //   282: ldc_w 1750
    //   285: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   288: invokevirtual 336	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   291: aload 6
    //   293: getfield 1748	android/util/DisplayMetrics:heightPixels	I
    //   296: invokevirtual 560	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   299: ldc_w 1752
    //   302: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   305: invokevirtual 340	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: iload_3
    //   309: invokevirtual 560	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   312: ldc_w 1752
    //   315: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   318: invokevirtual 340	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: iconst_0
    //   322: invokevirtual 560	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   325: ldc_w 1752
    //   328: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   331: invokevirtual 340	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: iload_1
    //   335: invokevirtual 560	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   338: invokevirtual 344	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: invokestatic 373	com/pvmrcbskbspfw/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   344: aload_0
    //   345: aload_0
    //   346: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   349: ldc_w 1754
    //   352: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   355: invokevirtual 543	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   358: putfield 257	com/pvmrcbskbspfw/AdController:y	I
    //   361: aload_0
    //   362: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   365: ldc_w 1756
    //   368: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   371: invokevirtual 543	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   374: istore_1
    //   375: aload_0
    //   376: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   379: ldc_w 1758
    //   382: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   385: invokevirtual 543	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   388: istore_3
    //   389: iload_3
    //   390: ifle +191 -> 581
    //   393: aload_0
    //   394: iload_1
    //   395: bipush 60
    //   397: imul
    //   398: iload_3
    //   399: idiv
    //   400: putfield 259	com/pvmrcbskbspfw/AdController:WA	I
    //   403: aload_0
    //   404: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   407: ldc_w 1760
    //   410: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   413: invokevirtual 455	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   416: ldc_w 434
    //   419: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   422: invokevirtual 456	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   425: ifeq +8 -> 433
    //   428: aload_0
    //   429: iconst_1
    //   430: putfield 241	com/pvmrcbskbspfw/AdController:S	Z
    //   433: aload 5
    //   435: new 329	java/lang/StringBuilder
    //   438: dup
    //   439: invokespecial 330	java/lang/StringBuilder:<init>	()V
    //   442: iconst_0
    //   443: ldc_w 1762
    //   446: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   449: invokevirtual 336	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   452: aload_0
    //   453: getfield 296	com/pvmrcbskbspfw/AdController:V	Ljava/lang/String;
    //   456: invokevirtual 340	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: invokevirtual 344	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   462: aload_0
    //   463: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   466: ldc_w 1764
    //   469: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   472: invokevirtual 384	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   475: invokeinterface 1514 3 0
    //   480: pop
    //   481: aload 5
    //   483: invokestatic 1381	com/pvmrcbskbspfw/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   486: aload_0
    //   487: getfield 279	com/pvmrcbskbspfw/AdController:bA	Z
    //   490: ifeq +229 -> 719
    //   493: aload_0
    //   494: invokevirtual 1767	com/pvmrcbskbspfw/AdController:retrieveAudioAd	()V
    //   497: aload 4
    //   499: new 329	java/lang/StringBuilder
    //   502: dup
    //   503: invokespecial 330	java/lang/StringBuilder:<init>	()V
    //   506: iconst_0
    //   507: ldc_w 1576
    //   510: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   513: invokevirtual 336	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   516: aload_0
    //   517: getfield 296	com/pvmrcbskbspfw/AdController:V	Ljava/lang/String;
    //   520: invokevirtual 340	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: invokevirtual 344	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   526: iconst_0
    //   527: invokeinterface 1438 3 0
    //   532: ifeq -394 -> 138
    //   535: aload_0
    //   536: getfield 1567	com/pvmrcbskbspfw/AdController:AA	Landroid/os/Handler;
    //   539: ifnull -401 -> 138
    //   542: aload_0
    //   543: getfield 1567	com/pvmrcbskbspfw/AdController:AA	Landroid/os/Handler;
    //   546: aload_0
    //   547: getfield 1569	com/pvmrcbskbspfw/AdController:I	Ljava/lang/Runnable;
    //   550: aload_0
    //   551: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   554: ldc_w 1769
    //   557: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   560: invokevirtual 543	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   563: sipush 1000
    //   566: imul
    //   567: i2l
    //   568: invokevirtual 1773	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   571: pop
    //   572: return
    //   573: astore 4
    //   575: return
    //   576: iconst_0
    //   577: istore_1
    //   578: goto -328 -> 250
    //   581: aload_0
    //   582: bipush 10
    //   584: putfield 259	com/pvmrcbskbspfw/AdController:WA	I
    //   587: goto -184 -> 403
    //   590: astore 6
    //   592: aload_0
    //   593: sipush 500
    //   596: putfield 257	com/pvmrcbskbspfw/AdController:y	I
    //   599: aload_0
    //   600: bipush 10
    //   602: putfield 259	com/pvmrcbskbspfw/AdController:WA	I
    //   605: goto -202 -> 403
    //   608: astore 6
    //   610: aload_0
    //   611: getfield 1775	com/pvmrcbskbspfw/AdController:cA	Ljava/lang/String;
    //   614: ifnull +61 -> 675
    //   617: aload_0
    //   618: getfield 1775	com/pvmrcbskbspfw/AdController:cA	Ljava/lang/String;
    //   621: ldc_w 1022
    //   624: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   627: invokevirtual 398	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   630: ifne +45 -> 675
    //   633: aload 5
    //   635: new 329	java/lang/StringBuilder
    //   638: dup
    //   639: invokespecial 330	java/lang/StringBuilder:<init>	()V
    //   642: iconst_0
    //   643: ldc_w 1762
    //   646: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   649: invokevirtual 336	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   652: aload_0
    //   653: getfield 296	com/pvmrcbskbspfw/AdController:V	Ljava/lang/String;
    //   656: invokevirtual 340	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   659: invokevirtual 344	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   662: aload_0
    //   663: getfield 1775	com/pvmrcbskbspfw/AdController:cA	Ljava/lang/String;
    //   666: invokeinterface 1514 3 0
    //   671: pop
    //   672: goto -191 -> 481
    //   675: aload 5
    //   677: new 329	java/lang/StringBuilder
    //   680: dup
    //   681: invokespecial 330	java/lang/StringBuilder:<init>	()V
    //   684: iconst_0
    //   685: ldc_w 1762
    //   688: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   691: invokevirtual 336	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   694: aload_0
    //   695: getfield 296	com/pvmrcbskbspfw/AdController:V	Ljava/lang/String;
    //   698: invokevirtual 340	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   701: invokevirtual 344	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   704: ldc_w 1022
    //   707: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   710: invokeinterface 1514 3 0
    //   715: pop
    //   716: goto -235 -> 481
    //   719: aload_0
    //   720: new 402	com/pvmrcbskbspfw/AdWebView
    //   723: dup
    //   724: aload_0
    //   725: getfield 294	com/pvmrcbskbspfw/AdController:Z	Landroid/content/Context;
    //   728: checkcast 711	android/app/Activity
    //   731: aload_0
    //   732: aload_0
    //   733: getfield 241	com/pvmrcbskbspfw/AdController:S	Z
    //   736: aload_0
    //   737: getfield 265	com/pvmrcbskbspfw/AdController:HA	Lcom/pvmrcbskbspfw/AdListener;
    //   740: aload_0
    //   741: getfield 300	com/pvmrcbskbspfw/AdController:MA	Landroid/widget/RelativeLayout;
    //   744: invokespecial 1778	com/pvmrcbskbspfw/AdWebView:<init>	(Landroid/content/Context;Lcom/pvmrcbskbspfw/AdController;ZLcom/pvmrcbskbspfw/AdListener;Landroid/widget/RelativeLayout;)V
    //   747: putfield 390	com/pvmrcbskbspfw/AdController:fA	Lcom/pvmrcbskbspfw/AdWebView;
    //   750: ldc 110
    //   752: ldc_w 1780
    //   755: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   758: invokestatic 373	com/pvmrcbskbspfw/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   761: ldc_w 1412
    //   764: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   767: invokestatic 1091	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   770: ldc_w 1782
    //   773: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   776: aconst_null
    //   777: checkcast 1416	[Ljava/lang/Class;
    //   780: invokevirtual 1420	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   783: aload_0
    //   784: getfield 390	com/pvmrcbskbspfw/AdController:fA	Lcom/pvmrcbskbspfw/AdWebView;
    //   787: aconst_null
    //   788: checkcast 1422	[Ljava/lang/Object;
    //   791: invokevirtual 1428	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   794: pop
    //   795: aload_0
    //   796: getfield 390	com/pvmrcbskbspfw/AdController:fA	Lcom/pvmrcbskbspfw/AdWebView;
    //   799: iconst_0
    //   800: invokevirtual 552	com/pvmrcbskbspfw/AdWebView:setBackgroundColor	(I)V
    //   803: aload_0
    //   804: getfield 390	com/pvmrcbskbspfw/AdController:fA	Lcom/pvmrcbskbspfw/AdWebView;
    //   807: aload_0
    //   808: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   811: invokevirtual 1786	com/pvmrcbskbspfw/AdWebView:setResults	(Lorg/json/JSONObject;)V
    //   814: aload_0
    //   815: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   818: ldc_w 1788
    //   821: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   824: invokevirtual 543	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   827: istore_1
    //   828: iload_1
    //   829: iconst_1
    //   830: if_icmpne +18 -> 848
    //   833: aload_0
    //   834: getfield 390	com/pvmrcbskbspfw/AdController:fA	Lcom/pvmrcbskbspfw/AdWebView;
    //   837: new 26	com/pvmrcbskbspfw/AdController$3
    //   840: dup
    //   841: aload_0
    //   842: invokespecial 1789	com/pvmrcbskbspfw/AdController$3:<init>	(Lcom/pvmrcbskbspfw/AdController;)V
    //   845: invokevirtual 1793	com/pvmrcbskbspfw/AdWebView:setOnKeyListener	(Landroid/view/View$OnKeyListener;)V
    //   848: aload_0
    //   849: getfield 309	com/pvmrcbskbspfw/AdController:GA	Lcom/pvmrcbskbspfw/AdView;
    //   852: ifnonnull +214 -> 1066
    //   855: aload_0
    //   856: new 554	com/pvmrcbskbspfw/AdView
    //   859: dup
    //   860: aload_0
    //   861: getfield 294	com/pvmrcbskbspfw/AdController:Z	Landroid/content/Context;
    //   864: checkcast 711	android/app/Activity
    //   867: aload_0
    //   868: aload_0
    //   869: getfield 265	com/pvmrcbskbspfw/AdController:HA	Lcom/pvmrcbskbspfw/AdListener;
    //   872: invokespecial 1796	com/pvmrcbskbspfw/AdView:<init>	(Landroid/content/Context;Lcom/pvmrcbskbspfw/AdController;Lcom/pvmrcbskbspfw/AdListener;)V
    //   875: putfield 309	com/pvmrcbskbspfw/AdController:GA	Lcom/pvmrcbskbspfw/AdView;
    //   878: aload_0
    //   879: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   882: ldc_w 1798
    //   885: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   888: invokevirtual 543	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   891: iconst_1
    //   892: if_icmpeq -754 -> 138
    //   895: aload_0
    //   896: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   899: ldc_w 1798
    //   902: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   905: invokevirtual 543	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   908: istore_1
    //   909: iload_1
    //   910: ifne -772 -> 138
    //   913: aload_0
    //   914: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   917: ldc_w 1800
    //   920: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   923: invokevirtual 543	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   926: ifle +190 -> 1116
    //   929: aload_0
    //   930: getfield 315	com/pvmrcbskbspfw/AdController:BA	Lorg/json/JSONObject;
    //   933: ldc_w 1800
    //   936: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   939: invokevirtual 543	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   942: sipush 1000
    //   945: imul
    //   946: istore_1
    //   947: ldc 110
    //   949: new 329	java/lang/StringBuilder
    //   952: dup
    //   953: invokespecial 330	java/lang/StringBuilder:<init>	()V
    //   956: iconst_0
    //   957: ldc_w 1802
    //   960: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   963: invokevirtual 336	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   966: iload_1
    //   967: invokevirtual 560	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   970: ldc_w 1804
    //   973: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   976: invokevirtual 340	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: invokevirtual 344	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   982: invokestatic 417	com/pvmrcbskbspfw/AdLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   985: new 1571	android/os/Handler
    //   988: dup
    //   989: invokespecial 1805	android/os/Handler:<init>	()V
    //   992: new 28	com/pvmrcbskbspfw/AdController$4
    //   995: dup
    //   996: aload_0
    //   997: invokespecial 1806	com/pvmrcbskbspfw/AdController$4:<init>	(Lcom/pvmrcbskbspfw/AdController;)V
    //   1000: iload_1
    //   1001: i2l
    //   1002: invokevirtual 1773	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   1005: pop
    //   1006: return
    //   1007: astore 4
    //   1009: aload_0
    //   1010: invokespecial 1578	com/pvmrcbskbspfw/AdController:C	()V
    //   1013: return
    //   1014: astore 4
    //   1016: aload_0
    //   1017: invokespecial 1578	com/pvmrcbskbspfw/AdController:C	()V
    //   1020: return
    //   1021: astore 4
    //   1023: ldc 110
    //   1025: new 329	java/lang/StringBuilder
    //   1028: dup
    //   1029: invokespecial 330	java/lang/StringBuilder:<init>	()V
    //   1032: iconst_0
    //   1033: ldc_w 1808
    //   1036: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   1039: invokevirtual 336	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   1042: aload 4
    //   1044: invokevirtual 447	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1047: invokevirtual 340	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1050: invokevirtual 344	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1053: invokestatic 377	com/pvmrcbskbspfw/AdLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1056: ldc 110
    //   1058: aload 4
    //   1060: invokestatic 442	com/pvmrcbskbspfw/AdLog:printStackTrace	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   1063: goto -260 -> 803
    //   1066: ldc_w 1412
    //   1069: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   1072: invokestatic 1091	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1075: ldc_w 1782
    //   1078: invokestatic 321	com/pvmrcbskbspfw/AdController:D	(Ljava/lang/String;)Ljava/lang/String;
    //   1081: aconst_null
    //   1082: checkcast 1416	[Ljava/lang/Class;
    //   1085: invokevirtual 1420	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1088: aload_0
    //   1089: getfield 309	com/pvmrcbskbspfw/AdController:GA	Lcom/pvmrcbskbspfw/AdView;
    //   1092: aconst_null
    //   1093: checkcast 1422	[Ljava/lang/Object;
    //   1096: invokevirtual 1428	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1099: pop
    //   1100: aload_0
    //   1101: getfield 309	com/pvmrcbskbspfw/AdController:GA	Lcom/pvmrcbskbspfw/AdView;
    //   1104: iconst_0
    //   1105: invokevirtual 555	com/pvmrcbskbspfw/AdView:setBackgroundColor	(I)V
    //   1108: goto -230 -> 878
    //   1111: astore 4
    //   1113: goto -235 -> 878
    //   1116: aload_0
    //   1117: invokespecial 1578	com/pvmrcbskbspfw/AdController:C	()V
    //   1120: return
    //   1121: aload_0
    //   1122: getfield 265	com/pvmrcbskbspfw/AdController:HA	Lcom/pvmrcbskbspfw/AdListener;
    //   1125: ifnull +17 -> 1142
    //   1128: aload_0
    //   1129: iconst_1
    //   1130: putfield 251	com/pvmrcbskbspfw/AdController:g	Z
    //   1133: aload_0
    //   1134: getfield 265	com/pvmrcbskbspfw/AdController:HA	Lcom/pvmrcbskbspfw/AdListener;
    //   1137: invokeinterface 738 1 0
    //   1142: aload_0
    //   1143: getfield 305	com/pvmrcbskbspfw/AdController:KA	Lcom/pvmrcbskbspfw/AdAudioListener;
    //   1146: ifnull -1008 -> 138
    //   1149: aload_0
    //   1150: iconst_1
    //   1151: putfield 281	com/pvmrcbskbspfw/AdController:dA	Z
    //   1154: aload_0
    //   1155: getfield 305	com/pvmrcbskbspfw/AdController:KA	Lcom/pvmrcbskbspfw/AdAudioListener;
    //   1158: invokeinterface 741 1 0
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
    return this.Z.getSharedPreferences(D("\000\0175\0335\0175\0233\030"), 0).getString(this.V, D("\0315\0331\b<\t"));
  }
  
  public void audioAdRetrieved(Integer paramInteger)
  {
    AdLog.d("LBAdController", D("1\b4\024?<4/5\t\"\0245\0135\031"));
    if ((this.e) || (this.PA))
    {
      AdLog.d("LBAdController", D("\032?\024>\032p\t?]\"\030$\b\"\023p\033\"\022=]1\b4\024?<4/5\t\"\0245\0135\031p\034#]#\t?\0179\0237]$\022p\0361\0368\030"));
      paramInteger = this.Z.getSharedPreferences(D("\000\0175\0335\0175\0233\030"), 0).edit();
      paramInteger.putLong(this.V, System.currentTimeMillis());
      AdUtils.apply(paramInteger);
      this.aA = true;
      if ((this.PA) && (this.KA != null)) {
        this.KA.onAdCached();
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
    } while (this.KA == null);
    this.KA.onAdFailed();
    this.dA = true;
  }
  
  public void checkForAudioAd(String paramString1, String paramString2)
  {
    if ((!this.C) && (paramString1 != null) && (!paramString1.equals(D(">\b<\021"))) && (!paramString1.equals("")))
    {
      if (this.BA.isNull(D("1\0311\b4\024?\b\"\021"))) {}
      for (;;)
      {
        try
        {
          this.BA.put(D("1\0311\b4\024?\b\"\021"), paramString1);
          this.BA.put(D("\0344\b\"\021"), paramString2);
          if ((!this.dA) && (!this.BA.isNull(D("1\0311\b4\024?\b\"\021"))))
          {
            if (this.n == null) {
              this.n = new AdShakeListener(true);
            }
            if (this.EA == null)
            {
              this.Q = ((AudioManager)this.Z.getSystemService(D("\034%\0319\022")));
              this.EA = ((SensorManager)this.Z.getSystemService(D("#\030>\016?\017")));
              this.x = 0.0F;
              this.N = 9.80665F;
              this.gA = 9.80665F;
            }
            retrieveAudioAd();
          }
          return;
        }
        catch (JSONException paramString1) {}
      }
    }
    AdLog.d("LBAdController", D("\036\022p\034%\0319\022p\036?\020 \022>\030>\t"));
  }
  
  public void destroyAd()
  {
    AdLog.i("LBAdController", D("4\030#\t\"\022)<4]3\034<\0215\031"));
    this.C = true;
    F();
    a();
  }
  
  public boolean getAdDestroyed()
  {
    return this.C;
  }
  
  public boolean getAdLoaded()
  {
    return this.g;
  }
  
  public boolean getLoadInBackground()
  {
    return this.QA;
  }
  
  public boolean getOnAdLoaded()
  {
    return this.a;
  }
  
  public void hideElements()
  {
    try
    {
      this.fA.setVisibility(8);
      this.m.setVisibility(8);
      this.K.setVisibility(8);
      this.qA.setVisibility(8);
      this.T.setVisibility(8);
      this.LA.setVisibility(8);
      this.v.setVisibility(8);
      this.R.setVisibility(8);
      this.eA.setVisibility(8);
      this.MA.setVisibility(8);
      this.f.setVisibility(8);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void loadAd()
  {
    if (!(this.Z instanceof Activity))
    {
      Log.e("LBAdController", D("\021?\0344<4Uy]6\0349\0215\031pPp<p+1\0219\031p<3\t9\0139\t)]>\022$] \034#\0165\031"));
      if (this.HA != null) {
        this.HA.onAdFailed();
      }
    }
    do
    {
      return;
      if ((this.yA == null) || (this.yA.getStatus() != AsyncTask.Status.RUNNING)) {
        break;
      }
    } while (!this.e);
    this.e = false;
    return;
    if ((this.FA) && (M()))
    {
      AdLog.i("LBAdController", D("\023\0343\0255]9\016p\0131\0219\031pPp\032?\024>\032p\t?]%\0165]$\0251\t"));
      this.QA = false;
      this.e = false;
      C();
      this.L = false;
      g();
      if (this.HA != null) {
        this.HA.onAdLoaded();
      }
      if (this.aA)
      {
        m();
        this.aA = false;
      }
      this.FA = false;
      SharedPreferences.Editor localEditor = this.Z.getSharedPreferences(D("\000\0175\0335\0175\0233\030"), 0).edit();
      localEditor.putLong(this.V, -1L);
      AdUtils.apply(localEditor);
      return;
    }
    this.e = false;
    this.L = false;
    this.FA = false;
    this.o = false;
    AdLog.i("LBAdController", D("\021?\0344<4]3\034<\0215\031"));
    this.a = false;
    if (!this.o)
    {
      this.nA = true;
      this.C = false;
      AdLog.i("LBAdController", D("3\034<\0219\0237]<\0221\031\021\031\031\0239\t9\034<\024*\030"));
      e();
    }
    for (;;)
    {
      long l1 = System.currentTimeMillis();
      AdLog.d("LBAdController", l1);
      return;
      AdLog.i("LBAdController", D("\0361\021<\024>\032p\0319\016 \0211\004\021\031"));
      C();
    }
  }
  
  public void loadAdToCache()
  {
    if (!(this.Z instanceof Activity))
    {
      Log.e("LBAdController", D("\021?\0344<4Uy]6\0349\0215\031pPp<p+1\0219\031p<3\t9\0139\t)]>\022$] \034#\0165\031"));
      if (this.HA != null) {
        this.HA.onAdFailed();
      }
      return;
    }
    AdLog.i("LBAdController", D("<\0221\031\021\031\004\022\023\0343\0255]3\034<\0215\031"));
    this.e = true;
    this.a = false;
    if (!this.o)
    {
      this.nA = true;
      this.C = false;
      e();
      return;
    }
    C();
  }
  
  public void loadAudioAd()
  {
    if (this.sA != null) {}
    label346:
    for (;;)
    {
      return;
      if ((this.yA != null) && (this.yA.getStatus() == AsyncTask.Status.RUNNING))
      {
        if (this.PA) {
          this.PA = false;
        }
      }
      else
      {
        if ((this.aA) && (B()))
        {
          AdLog.i("LBAdController", D("\023\0343\0255]9\016p\0131\0219\031pPp\032?\024>\032p\t?] \0211\004p\034%\0319\022p\033\"\022=]$\0251\t"));
          this.PA = false;
          m();
          this.aA = false;
          this.FA = false;
          SharedPreferences.Editor localEditor = this.Z.getSharedPreferences(D("\000\0175\0335\0175\0233\030"), 0).edit();
          localEditor.putLong(this.V, -1L);
          AdUtils.apply(localEditor);
          return;
        }
        this.PA = false;
        this.aA = false;
        this.o = false;
        AdLog.i("LBAdController", D("<\0221\031\021\b4\024?<4]3\034<\0215\031"));
        if (!this.o)
        {
          this.bA = true;
          if (this.EA == null)
          {
            this.n = new AdShakeListener(true);
            this.Q = ((AudioManager)this.Z.getSystemService(D("\034%\0319\022")));
            this.EA = ((SensorManager)this.Z.getSystemService(D("#\030>\016?\017")));
            this.x = 0.0F;
            this.N = 9.80665F;
            this.gA = 9.80665F;
          }
          e();
        }
        for (;;)
        {
          if ((this.KA == null) || (this.j <= 0)) {
            break label346;
          }
          if (this.r == null) {
            this.r = new Runnable()
            {
              public void run()
              {
                try
                {
                  if ((!AdController.f(AdController.this)) && (!AdController.H(AdController.this)))
                  {
                    AdLog.i("LBAdController", AdController.D("?\023\021\031\000\017?\032\"\030#\016p\t\"\0247\0325\0175\031"));
                    AdController.C(AdController.this).onAdProgress();
                    AdController.a(AdController.this).postDelayed(AdController.h(AdController.this), AdController.E(AdController.this) * 1000);
                  }
                  return;
                }
                catch (Exception localException)
                {
                  AdLog.e("LBAdController", AdWakeLock.D(" \0317\0047K2\003 \005e\004+*!;7\004\"\031 \0306K1\031,\f\"\0167\016!"));
                  AdLog.printStackTrace("LBAdController", localException);
                }
              }
            };
          }
          if (this.l != null) {
            break;
          }
          this.l = new Handler();
          this.l.postDelayed(this.r, this.j * 1000);
          return;
          m();
        }
      }
    }
  }
  
  public void loadAudioAdToCache()
  {
    if (this.sA != null) {
      return;
    }
    AdLog.i("LBAdController", D("\021?\0344<%\0319\022\021\031\004\022\023\0343\0255]3\034<\0215\031"));
    if (!this.o)
    {
      this.bA = true;
      this.PA = true;
      if (this.EA == null)
      {
        this.n = new AdShakeListener(true);
        this.Q = ((AudioManager)this.Z.getSystemService(D("\034%\0319\022")));
        this.EA = ((SensorManager)this.Z.getSystemService(D("#\030>\016?\017")));
        this.x = 0.0F;
        this.N = 9.80665F;
        this.gA = 9.80665F;
      }
      e();
      return;
    }
    m();
  }
  
  public void loadAudioTrack(long paramLong)
  {
    if (this.I == null) {
      this.I = new Runnable()
      {
        public void run()
        {
          SharedPreferences.Editor localEditor = AdController.this.Z.getSharedPreferences(AdWakeLock.D(";7\016#\0167\016+\b "), 0).edit();
          localEditor.putBoolean(AdController.F(AdController.this), true);
          AdUtils.apply(localEditor);
          AdController.this.loadAudioAd();
        }
      };
    }
    if (this.AA == null) {
      this.AA = new Handler();
    }
    for (;;)
    {
      this.AA.postDelayed(this.I, 60L * paramLong * 1000L);
      return;
      this.AA.removeCallbacks(this.I);
    }
  }
  
  public void loadReEngagement()
  {
    int i1;
    if (this.yA != null)
    {
      AdLog.i("LBAdController", this.yA.getStatus());
      if (this.yA.getStatus() == AsyncTask.Status.FINISHED) {
        i1 = 1;
      }
    }
    for (;;)
    {
      if (i1 != 0)
      {
        Object localObject = this.Z.getSharedPreferences(D("\000\0175\0335\0175\0233\030"), 0);
        Context localContext = this.Z;
        String str = this.V;
        if (((SharedPreferences)localObject).getBoolean(D("\0175\030>\0321\0325\0205\023$\034<\034\"\020"), false)) {}
        for (localObject = D("\"\0305\0237\0347\030=\030>\t\017\034<\034\"\020");; localObject = "reengagement")
        {
          this.yA = new AdTask(this, localContext, str, (String)localObject);
          this.yA.setSubId(this.W);
          this.yA.setTokens(this.Y);
          D(this.yA, new String[] { "" });
          return;
          i1 = 0;
          break;
        }
      }
      AdLog.i("LBAdController", D("3?]\"\030!\b5\016$]$\022p\0375]=\0344\030"));
      return;
      i1 = 1;
    }
  }
  
  public void loadStartAd(String paramString1, String paramString2)
  {
    loadAd();
    this.JA = new AdController(this.Z, paramString1);
    this.JA.loadAudioTrack(2L);
    new AdController(this.Z, paramString2).loadReEngagement();
  }
  
  public boolean onBackPressed()
  {
    if (this.mA)
    {
      loadAd();
      return true;
    }
    return false;
  }
  
  public void onLinkClicked()
  {
    I();
  }
  
  public void reEngagementInitialized()
  {
    if (this.BA != null) {
      l();
    }
    SharedPreferences localSharedPreferences = this.Z.getSharedPreferences(D("\000\0175\0335\0175\0233\030"), 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (localSharedPreferences.getBoolean(D("\0175\030>\0321\0325\0205\023$\034<\034\"\020"), false))
    {
      localEditor.putBoolean(D("\0175\030>\0321\0325\0205\023$\034<\034\"\020"), false);
      AdUtils.apply(localEditor);
      G();
    }
  }
  
  public void retrieveAudioAd()
  {
    int i2 = 1;
    AdLog.d("LBAdController", D("\0175\t\"\0245\0135<%\0319\022\021\031"));
    for (;;)
    {
      try
      {
        if (this.BA.get(D("#\025?\n")).equals(D("L")))
        {
          i1 = i2;
          if (this.wA != null)
          {
            if (this.wA.getStatus() != AsyncTask.Status.FINISHED) {
              break label161;
            }
            i1 = i2;
          }
          if (i1 != 0)
          {
            AdLog.d("LBAdController", D("7\0229\0237]$\022p\0201\0265]\"\030!\b5\016$]1\0234]6\030$\0368]1\b4\024?]1\031"));
            this.wA = new AdAudioTask(this, this.Z);
            D(this.wA, new String[] { this.BA.getString(D("1\0311\b4\024?\b\"\021")) });
          }
        }
        else
        {
          this.bA = false;
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
    this.C = paramBoolean;
  }
  
  public void setAdLoaded(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }
  
  public void setAdditionalDockingMargin(int paramInt)
  {
    this.xA = paramInt;
    AdLog.i("LBAdController", paramInt);
  }
  
  public void setCompleted(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }
  
  public void setHTML(String paramString)
  {
    if (this.GA != null)
    {
      paramString = paramString + D("A\025$\020<C");
      this.GA.loadHTMLWrap(paramString);
    }
  }
  
  public void setHTMLAds(boolean paramBoolean)
  {
    this.IA = paramBoolean;
  }
  
  public void setHomeLoaded(boolean paramBoolean)
  {
    this.lA = paramBoolean;
  }
  
  public void setLayout(RelativeLayout paramRelativeLayout)
  {
    this.MA = paramRelativeLayout;
  }
  
  public void setLoadInBackground(boolean paramBoolean)
  {
    this.QA = paramBoolean;
  }
  
  public void setLoading(boolean paramBoolean)
  {
    this.L = paramBoolean;
  }
  
  public void setOnAdLoaded(boolean paramBoolean)
  {
    this.a = paramBoolean;
    if ((this.QA) && (!this.C)) {
      ((Activity)this.Z).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (AdController.I(AdController.this))
          {
            SharedPreferences.Editor localEditor = AdController.this.Z.getSharedPreferences(AdDefines.D("zOkOOcIh"), 0).edit();
            localEditor.putLong(AdController.F(AdController.this), System.currentTimeMillis());
            AdUtils.apply(localEditor);
            AdController.J(AdController.this, true);
            if (AdController.D(AdController.this) != null) {
              AdController.D(AdController.this).onAdCached();
            }
            return;
          }
          if ((!AdController.I(AdController.this)) && (AdController.D(AdController.this) != null))
          {
            AdLog.i("LBAdController", AdDefines.D("^CjMhX-EckifbKiOi"));
            AdController.D(AdController.this).onAdLoaded();
          }
          AdController.D(AdController.this, false);
          AdController.k(AdController.this);
          AdController.k(AdController.this, false);
        }
      });
    }
  }
  
  public void setOnProgressInterval(int paramInt)
  {
    this.j = paramInt;
  }
  
  public void setResults(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.BA = paramJSONObject;
      paramJSONObject = this.Z.getSharedPreferences(D("\000\0175\0335\0175\0233\030"), 0).edit();
    }
    try
    {
      paramJSONObject.putLong(D("\0344\0175\0368\0303\026$\024=\030"), this.BA.getLong(D("\0344\0175\0368\0303\026$\024=\030")));
      AdUtils.apply(paramJSONObject);
      AdLog.d("LBAdController", this.BA.getLong(D("\0344\0175\0368\0303\026$\024=\030")));
      return;
    }
    catch (Exception paramJSONObject)
    {
      AdLog.d("LBAdController", paramJSONObject.getMessage());
    }
  }
  
  public void setSubId(String paramString)
  {
    this.W = paramString;
  }
  
  public void setTokens(List<NameValuePair> paramList)
  {
    this.Y = paramList;
  }
  
  public void showElements()
  {
    try
    {
      this.fA.setVisibility(0);
      this.m.setVisibility(0);
      this.K.setVisibility(0);
      this.qA.setVisibility(0);
      this.T.setVisibility(0);
      this.LA.setVisibility(0);
      this.v.setVisibility(0);
      this.R.setVisibility(0);
      this.eA.setVisibility(0);
      this.MA.setVisibility(0);
      this.f.setVisibility(0);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void showInternetDialog()
  {
    if ((this.Z != null) && ((this.Z instanceof Activity)))
    {
      if (this.s == null)
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.Z);
        localBuilder.setMessage(D("\031\023$\030\"\0235\tp\023?\tp\034&\0349\0211\037<\030")).setCancelable(false).setPositiveButton(D("><\022#\030"), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            AdController.d(AdController.this).dismiss();
          }
        });
        this.s = localBuilder.create();
      }
      this.s.show();
    }
  }
  
  public void stopAllListeners() {}
  
  public void triggerAdCompleted() {}
  
  public void triggerAdFailed()
  {
    AdLog.e("LBAdController", D("\036\022p4>\t5\017>\030$]3\022>\0235\036$\024?\023p\0315\t5\036$\0304Sp3?]\021\031#]<\0221\0315\031"));
    if (this.HA != null) {}
    for (;;)
    {
      try
      {
        AdLog.i("LBAdController", D("?\023\021\031\026\0349\0215\031p\t\"\0247\0325\0175\031"));
        this.HA.onAdFailed();
        this.g = true;
        if (this.KA != null)
        {
          this.KA.onAdFailed();
          this.dA = true;
        }
        return;
      }
      catch (Exception localException)
      {
        AdLog.i("LBAdController", D("\025\017\"\022\"]'\0259\0215]3\034<\0219\0237]?\023\021\031\026\0349\0215\031"));
        AdLog.printStackTrace("LBAdController", localException);
      }
    }
  }
  
  private class AdClientPixel
    extends AsyncTask<String, Void, Boolean>
  {
    protected Boolean D(String... paramVarArgs)
    {
      Object localObject = paramVarArgs[0];
      if ((localObject != null) && (!((String)localObject).equals("")))
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        localBasicHttpParams.setParameter(AdController.D("\025$\t S \017?\t?\036?\021~\0135\017#\024?\023"), HttpVersion.HTTP_1_1);
        paramVarArgs = new DefaultHttpClient(localBasicHttpParams);
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 4000);
        localObject = new HttpGet((String)localObject);
      }
      try
      {
        if (paramVarArgs.execute((HttpUriRequest)localObject).getStatusLine().getStatusCode() == 200) {
          AdController.F(this.i, true);
        }
        return Boolean.valueOf(false);
      }
      catch (Exception paramVarArgs)
      {
        for (;;) {}
      }
    }
    
    protected void D(boolean paramBoolean)
    {
      if (paramBoolean) {
        AdController.F(this.i, true);
      }
    }
  }
  
  private class AdShakeListener
    implements SensorEventListener
  {
    public AdShakeListener(boolean paramBoolean)
    {
      this.F = paramBoolean;
      if (this.F)
      {
        this.c = new Handler();
        this.A = new Runnable()
        {
          public void run()
          {
            AdLog.i("LBAdController", AdWakeLock.D("\030-\n.\016e\n!K!\0161\016&\037,\004+K \0235\0027\0166"));
            AdController.AdShakeListener.B(AdController.AdShakeListener.this);
            AdController.AdShakeListener.D(AdController.AdShakeListener.this);
          }
        };
      }
    }
    
    public void destroySensor()
    {
      if (this.I)
      {
        B();
        this.c.removeCallbacks(this.A);
      }
    }
    
    public void enableShakeDetection()
    {
      this.I = true;
      AdController.j(AdController.this).registerListener(this, AdController.j(AdController.this).getDefaultSensor(1), 1);
      AdLog.i("LBAdController", AdJSInterface.D("2G D$\017$Y$A5\017%J5J\"[(@/\017$A M-J%"));
    }
    
    public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
    
    public void onSensorChanged(SensorEvent paramSensorEvent)
    {
      float f1 = paramSensorEvent.values[0];
      float f2 = paramSensorEvent.values[1];
      float f3 = paramSensorEvent.values[2];
      AdController.D(AdController.this, AdController.J(AdController.this));
      AdController.F(AdController.this, FloatMath.sqrt(f1 * f1 + f2 * f2 + f3 * f3));
      f1 = AdController.J(AdController.this);
      f2 = AdController.i(AdController.this);
      AdController.B(AdController.this, f1 - f2 + AdController.G(AdController.this) * 0.9F);
      if (AdController.G(AdController.this) > 2.5F)
      {
        long l1 = System.currentTimeMillis();
        if (this.m == 0L)
        {
          this.m = l1;
          this.a = l1;
        }
        if (l1 - this.a < 300L)
        {
          this.a = l1;
          this.M += 1;
          if ((this.M >= this.j) && (l1 - this.m < 1500L))
          {
            AdLog.i("LBAdController", AdWakeLock.D("3\n)\002!K6\003$\000 "));
            D();
            B();
            if (this.F)
            {
              if ((AdController.l(AdController.this) != null) && (AdController.l(AdController.this).isPlaying()))
              {
                AdController.l(AdController.this).stop();
                AdController.D(AdController.this, null);
                if (AdController.C(AdController.this) != null) {
                  AdController.C(AdController.this).onAdFinished();
                }
                AdController.g(AdController.this).setStreamVolume(3, AdController.M(AdController.this), 8);
              }
              this.c.removeCallbacks(this.A);
              F();
            }
          }
        }
        return;
      }
      D();
    }
    
    public void setShakeTime(int paramInt)
    {
      this.j = paramInt;
    }
    
    public void setValidTimes(int paramInt)
    {
      this.K = paramInt;
    }
    
    public void setupAudioAdHandler()
    {
      this.c.postDelayed(this.A, this.K);
    }
  }
  
  private class ContextList
    extends AsyncTask<Void, Void, String>
  {
    public ContextList(Context paramContext)
    {
      this.M = paramContext;
      this.i = this.M.getSharedPreferences(AdJSInterface.D("3J'J3J/L$"), 0);
      this.g = this.i.edit();
    }
    
    protected String D(Void... paramVarArgs)
    {
      AdLog.i("LBAdController", AdJSInterface.D("\006@(A&\0175@a\\5N3[an2V/L\025N2Da[.\017&J/J3N5JaL.A5J9[2"));
      paramVarArgs = new StringBuilder();
      AdLog.d("LBAdController", System.currentTimeMillis());
      Object localObject1 = this.M.getPackageManager().getInstalledPackages(128);
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
      int k = 0;
      Object localObject2;
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (k <= 50) {}
      }
      for (;;)
      {
        localObject1 = (ActivityManager)this.M.getSystemService(AdJSInterface.D("N\"[(Y([8"));
        if (this.M.checkCallingOrSelfPermission(AdWakeLock.D("\n+\0177\004,\017k\033 \031(\0026\030,\004+E\002.\0214\021*\026 \026")) != 0) {
          break label644;
        }
        localObject1 = ((ActivityManager)localObject1).getRunningTasks(Integer.MAX_VALUE).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ActivityManager.RunningTaskInfo)((Iterator)localObject1).next();
          try
          {
            paramVarArgs.append(URLEncoder.encode(new StringBuilder().insert(0, AdWakeLock.D("\037,\037)\016x")).append(this.M.getPackageManager().getApplicationInfo(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName(), 0).loadLabel(this.M.getPackageManager()).toString()).append(AdJSInterface.D("g_ L*N&J|")).append(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName()).toString(), AdWakeLock.D("\020?\003F}")));
          }
          catch (Exception localException1) {}
        }
        if ((localException1.applicationInfo.flags & 0x1) != 0) {
          break;
        }
        StringBuilder localStringBuilder1 = new StringBuilder();
        k += 1;
        for (;;)
        {
          try
          {
            localStringBuilder1.append(localException1.applicationInfo.loadLabel(this.M.getPackageManager()).toString());
            localStringBuilder1.append(localException1.applicationInfo.packageName);
            StringBuilder localStringBuilder2 = new StringBuilder().insert(0, AdJSInterface.D("gF/\\5N-C|"));
            if (Build.VERSION.SDK_INT < 9) {
              break label631;
            }
            l = localException1.firstInstallTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder2 = new StringBuilder().insert(0, AdWakeLock.D("M0\033!\n1\016x"));
            if (Build.VERSION.SDK_INT < 9) {
              break label636;
            }
            l = localException1.lastUpdateTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder1.append(localException1.versionCode);
            localStringBuilder1.append(localException1.versionName);
            paramVarArgs.append(URLEncoder.encode(localStringBuilder1.toString(), AdWakeLock.D("\020?\003F}")));
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
    
    protected void D(String paramString)
    {
      this.g.putBoolean(AdWakeLock.D("8\0014\006$\013?\0003\0218\032\"\013;\027$\0029\0008\026"), false);
      AdUtils.apply(this.g);
      if ((paramString != null) && (!paramString.equals("")))
      {
        this.g.putString(AdJSInterface.D("\022k\036l\016a\025j\031{\022"), paramString);
        this.g.putLong(AdWakeLock.D("\026/\032(\n%\021.\035?\0264\020;\001*\021.\032?\f&\000"), System.currentTimeMillis());
        AdUtils.apply(this.g);
      }
    }
    
    protected void onPreExecute()
    {
      this.g.putBoolean(AdWakeLock.D("8\0014\006$\013?\0003\0218\032\"\013;\027$\0029\0008\026"), true);
      AdUtils.apply(this.g);
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
    public int M;
    public int g;
    public int i;
    public int j;
    
    public Dimensions()
    {
      this.M = -1;
      this.j = -1;
      this.i = -1;
      this.g = -1;
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
      this.k = paramContext;
      this.m = paramInt;
      this.j = paramCharSequence1;
      this.i = paramCharSequence2;
      this.c = paramCharSequence3;
      this.a = paramNotificationManager;
      this.L = paramPendingIntent;
      AdLog.i("LBAdController", AdWakeLock.D("\002\004,\005\"K1\004e- \037&\003e\"(\n\"\016d"));
    }
    
    protected Bitmap D(String... paramVarArgs)
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
    protected void D(Bitmap paramBitmap)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnull +465 -> 466
      //   4: ldc 80
      //   6: ldc -95
      //   8: invokestatic 105	com/pvmrcbskbspfw/AdDefines:D	(Ljava/lang/String;)Ljava/lang/String;
      //   11: invokestatic 93	com/pvmrcbskbspfw/AdLog:i	(Ljava/lang/String;Ljava/lang/String;)V
      //   14: new 99	java/lang/StringBuilder
      //   17: dup
      //   18: invokespecial 100	java/lang/StringBuilder:<init>	()V
      //   21: iconst_0
      //   22: aload_0
      //   23: getfield 66	com/pvmrcbskbspfw/AdController$FetchImage:k	Landroid/content/Context;
      //   26: invokevirtual 166	android/content/Context:getPackageName	()Ljava/lang/String;
      //   29: invokevirtual 109	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   32: aload_0
      //   33: getfield 40	com/pvmrcbskbspfw/AdController$FetchImage:M	Ljava/lang/String;
      //   36: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   39: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   42: invokestatic 172	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   45: astore 7
      //   47: aload 7
      //   49: aload_0
      //   50: getfield 48	com/pvmrcbskbspfw/AdController$FetchImage:A	Ljava/lang/String;
      //   53: invokevirtual 176	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   56: aload 7
      //   58: invokevirtual 182	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   61: istore_2
      //   62: new 99	java/lang/StringBuilder
      //   65: dup
      //   66: invokespecial 100	java/lang/StringBuilder:<init>	()V
      //   69: iconst_0
      //   70: aload_0
      //   71: getfield 66	com/pvmrcbskbspfw/AdController$FetchImage:k	Landroid/content/Context;
      //   74: invokevirtual 166	android/content/Context:getPackageName	()Ljava/lang/String;
      //   77: invokevirtual 109	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   80: aload_0
      //   81: getfield 44	com/pvmrcbskbspfw/AdController$FetchImage:g	Ljava/lang/String;
      //   84: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   87: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   90: invokestatic 172	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   93: astore 7
      //   95: aload 7
      //   97: aload_0
      //   98: getfield 52	com/pvmrcbskbspfw/AdController$FetchImage:F	Ljava/lang/String;
      //   101: invokevirtual 176	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   104: aload 7
      //   106: invokevirtual 182	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   109: istore_3
      //   110: aload 7
      //   112: aload_0
      //   113: getfield 56	com/pvmrcbskbspfw/AdController$FetchImage:b	Ljava/lang/String;
      //   116: invokevirtual 176	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   119: aload 7
      //   121: invokevirtual 182	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   124: istore 4
      //   126: aload 7
      //   128: aload_0
      //   129: getfield 60	com/pvmrcbskbspfw/AdController$FetchImage:I	Ljava/lang/String;
      //   132: invokevirtual 176	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   135: aload 7
      //   137: invokevirtual 182	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   140: istore 5
      //   142: aload 7
      //   144: aload_0
      //   145: getfield 64	com/pvmrcbskbspfw/AdController$FetchImage:K	Ljava/lang/String;
      //   148: invokevirtual 176	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   151: aload 7
      //   153: invokevirtual 182	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   156: istore 6
      //   158: iload 5
      //   160: iconst_m1
      //   161: if_icmpeq +175 -> 336
      //   164: iload 6
      //   166: iconst_m1
      //   167: if_icmpeq +169 -> 336
      //   170: new 184	android/widget/RemoteViews
      //   173: dup
      //   174: aload_0
      //   175: getfield 66	com/pvmrcbskbspfw/AdController$FetchImage:k	Landroid/content/Context;
      //   178: invokevirtual 166	android/content/Context:getPackageName	()Ljava/lang/String;
      //   181: iload_2
      //   182: invokespecial 187	android/widget/RemoteViews:<init>	(Ljava/lang/String;I)V
      //   185: astore 7
      //   187: aload 7
      //   189: iload_3
      //   190: ldc -67
      //   192: invokestatic 88	com/pvmrcbskbspfw/AdWakeLock:D	(Ljava/lang/String;)Ljava/lang/String;
      //   195: aload_0
      //   196: getfield 33	com/pvmrcbskbspfw/AdController$FetchImage:l	Lcom/pvmrcbskbspfw/AdController;
      //   199: invokestatic 193	com/pvmrcbskbspfw/AdController:B	(Lcom/pvmrcbskbspfw/AdController;)Lorg/json/JSONObject;
      //   202: ldc -61
      //   204: invokestatic 105	com/pvmrcbskbspfw/AdDefines:D	(Ljava/lang/String;)Ljava/lang/String;
      //   207: invokevirtual 200	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   210: invokestatic 206	android/graphics/Color:parseColor	(Ljava/lang/String;)I
      //   213: invokevirtual 210	android/widget/RemoteViews:setInt	(ILjava/lang/String;I)V
      //   216: aload 7
      //   218: iload 5
      //   220: aload_1
      //   221: invokevirtual 214	android/widget/RemoteViews:setImageViewBitmap	(ILandroid/graphics/Bitmap;)V
      //   224: aload 7
      //   226: iload 6
      //   228: aload_0
      //   229: getfield 33	com/pvmrcbskbspfw/AdController$FetchImage:l	Lcom/pvmrcbskbspfw/AdController;
      //   232: invokestatic 193	com/pvmrcbskbspfw/AdController:B	(Lcom/pvmrcbskbspfw/AdController;)Lorg/json/JSONObject;
      //   235: ldc -40
      //   237: invokestatic 88	com/pvmrcbskbspfw/AdWakeLock:D	(Ljava/lang/String;)Ljava/lang/String;
      //   240: invokevirtual 200	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   243: invokevirtual 220	android/widget/RemoteViews:setTextViewText	(ILjava/lang/CharSequence;)V
      //   246: aload 7
      //   248: iload 6
      //   250: aload_0
      //   251: getfield 33	com/pvmrcbskbspfw/AdController$FetchImage:l	Lcom/pvmrcbskbspfw/AdController;
      //   254: invokestatic 193	com/pvmrcbskbspfw/AdController:B	(Lcom/pvmrcbskbspfw/AdController;)Lorg/json/JSONObject;
      //   257: ldc -34
      //   259: invokestatic 105	com/pvmrcbskbspfw/AdDefines:D	(Ljava/lang/String;)Ljava/lang/String;
      //   262: invokevirtual 200	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   265: invokestatic 206	android/graphics/Color:parseColor	(Ljava/lang/String;)I
      //   268: invokevirtual 226	android/widget/RemoteViews:setTextColor	(II)V
      //   271: aload 7
      //   273: iload 4
      //   275: iconst_4
      //   276: invokevirtual 229	android/widget/RemoteViews:setViewVisibility	(II)V
      //   279: ldc 80
      //   281: ldc -25
      //   283: invokestatic 88	com/pvmrcbskbspfw/AdWakeLock:D	(Ljava/lang/String;)Ljava/lang/String;
      //   286: invokestatic 234	com/pvmrcbskbspfw/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   289: aload_0
      //   290: getfield 68	com/pvmrcbskbspfw/AdController$FetchImage:m	I
      //   293: iconst_m1
      //   294: if_icmpeq +217 -> 511
      //   297: aload_0
      //   298: getfield 33	com/pvmrcbskbspfw/AdController$FetchImage:l	Lcom/pvmrcbskbspfw/AdController;
      //   301: aload_0
      //   302: getfield 66	com/pvmrcbskbspfw/AdController$FetchImage:k	Landroid/content/Context;
      //   305: aload_0
      //   306: getfield 68	com/pvmrcbskbspfw/AdController$FetchImage:m	I
      //   309: aload_0
      //   310: getfield 70	com/pvmrcbskbspfw/AdController$FetchImage:j	Ljava/lang/CharSequence;
      //   313: aload_0
      //   314: getfield 72	com/pvmrcbskbspfw/AdController$FetchImage:i	Ljava/lang/CharSequence;
      //   317: aload_0
      //   318: getfield 74	com/pvmrcbskbspfw/AdController$FetchImage:c	Ljava/lang/CharSequence;
      //   321: aload_0
      //   322: getfield 76	com/pvmrcbskbspfw/AdController$FetchImage:a	Landroid/app/NotificationManager;
      //   325: aload_0
      //   326: getfield 78	com/pvmrcbskbspfw/AdController$FetchImage:L	Landroid/app/PendingIntent;
      //   329: aload 7
      //   331: invokestatic 237	com/pvmrcbskbspfw/AdController:D	(Lcom/pvmrcbskbspfw/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   334: pop
      //   335: return
      //   336: aload_0
      //   337: getfield 68	com/pvmrcbskbspfw/AdController$FetchImage:m	I
      //   340: iconst_m1
      //   341: if_icmpeq +170 -> 511
      //   344: aload_0
      //   345: getfield 33	com/pvmrcbskbspfw/AdController$FetchImage:l	Lcom/pvmrcbskbspfw/AdController;
      //   348: aload_0
      //   349: getfield 66	com/pvmrcbskbspfw/AdController$FetchImage:k	Landroid/content/Context;
      //   352: aload_0
      //   353: getfield 68	com/pvmrcbskbspfw/AdController$FetchImage:m	I
      //   356: aload_0
      //   357: getfield 70	com/pvmrcbskbspfw/AdController$FetchImage:j	Ljava/lang/CharSequence;
      //   360: aload_0
      //   361: getfield 72	com/pvmrcbskbspfw/AdController$FetchImage:i	Ljava/lang/CharSequence;
      //   364: aload_0
      //   365: getfield 74	com/pvmrcbskbspfw/AdController$FetchImage:c	Ljava/lang/CharSequence;
      //   368: aload_0
      //   369: getfield 76	com/pvmrcbskbspfw/AdController$FetchImage:a	Landroid/app/NotificationManager;
      //   372: aload_0
      //   373: getfield 78	com/pvmrcbskbspfw/AdController$FetchImage:L	Landroid/app/PendingIntent;
      //   376: aconst_null
      //   377: invokestatic 237	com/pvmrcbskbspfw/AdController:D	(Lcom/pvmrcbskbspfw/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   380: pop
      //   381: return
      //   382: astore_1
      //   383: ldc 80
      //   385: new 99	java/lang/StringBuilder
      //   388: dup
      //   389: invokespecial 100	java/lang/StringBuilder:<init>	()V
      //   392: iconst_0
      //   393: ldc -17
      //   395: invokestatic 105	com/pvmrcbskbspfw/AdDefines:D	(Ljava/lang/String;)Ljava/lang/String;
      //   398: invokevirtual 109	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   401: aload_1
      //   402: invokevirtual 151	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   405: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   408: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   411: invokestatic 154	com/pvmrcbskbspfw/AdLog:e	(Ljava/lang/String;Ljava/lang/String;)V
      //   414: ldc 80
      //   416: aload_1
      //   417: invokestatic 158	com/pvmrcbskbspfw/AdLog:printStackTrace	(Ljava/lang/String;Ljava/lang/Exception;)V
      //   420: aload_0
      //   421: getfield 68	com/pvmrcbskbspfw/AdController$FetchImage:m	I
      //   424: iconst_m1
      //   425: if_icmpeq +86 -> 511
      //   428: aload_0
      //   429: getfield 33	com/pvmrcbskbspfw/AdController$FetchImage:l	Lcom/pvmrcbskbspfw/AdController;
      //   432: aload_0
      //   433: getfield 66	com/pvmrcbskbspfw/AdController$FetchImage:k	Landroid/content/Context;
      //   436: aload_0
      //   437: getfield 68	com/pvmrcbskbspfw/AdController$FetchImage:m	I
      //   440: aload_0
      //   441: getfield 70	com/pvmrcbskbspfw/AdController$FetchImage:j	Ljava/lang/CharSequence;
      //   444: aload_0
      //   445: getfield 72	com/pvmrcbskbspfw/AdController$FetchImage:i	Ljava/lang/CharSequence;
      //   448: aload_0
      //   449: getfield 74	com/pvmrcbskbspfw/AdController$FetchImage:c	Ljava/lang/CharSequence;
      //   452: aload_0
      //   453: getfield 76	com/pvmrcbskbspfw/AdController$FetchImage:a	Landroid/app/NotificationManager;
      //   456: aload_0
      //   457: getfield 78	com/pvmrcbskbspfw/AdController$FetchImage:L	Landroid/app/PendingIntent;
      //   460: aconst_null
      //   461: invokestatic 237	com/pvmrcbskbspfw/AdController:D	(Lcom/pvmrcbskbspfw/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   464: pop
      //   465: return
      //   466: aload_0
      //   467: getfield 68	com/pvmrcbskbspfw/AdController$FetchImage:m	I
      //   470: iconst_m1
      //   471: if_icmpeq +40 -> 511
      //   474: aload_0
      //   475: getfield 33	com/pvmrcbskbspfw/AdController$FetchImage:l	Lcom/pvmrcbskbspfw/AdController;
      //   478: aload_0
      //   479: getfield 66	com/pvmrcbskbspfw/AdController$FetchImage:k	Landroid/content/Context;
      //   482: aload_0
      //   483: getfield 68	com/pvmrcbskbspfw/AdController$FetchImage:m	I
      //   486: aload_0
      //   487: getfield 70	com/pvmrcbskbspfw/AdController$FetchImage:j	Ljava/lang/CharSequence;
      //   490: aload_0
      //   491: getfield 72	com/pvmrcbskbspfw/AdController$FetchImage:i	Ljava/lang/CharSequence;
      //   494: aload_0
      //   495: getfield 74	com/pvmrcbskbspfw/AdController$FetchImage:c	Ljava/lang/CharSequence;
      //   498: aload_0
      //   499: getfield 76	com/pvmrcbskbspfw/AdController$FetchImage:a	Landroid/app/NotificationManager;
      //   502: aload_0
      //   503: getfield 78	com/pvmrcbskbspfw/AdController$FetchImage:L	Landroid/app/PendingIntent;
      //   506: aconst_null
      //   507: invokestatic 237	com/pvmrcbskbspfw/AdController:D	(Lcom/pvmrcbskbspfw/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
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
    public static final Parcelable.Creator<PlayerProperties> i = new Parcelable.Creator()
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
    public boolean K;
    public String M;
    public boolean a;
    public boolean c;
    public boolean g;
    public boolean j;
    public String m;
    
    public PlayerProperties()
    {
      this.j = true;
      this.g = true;
      this.K = false;
      this.c = false;
      this.M = "normal";
      this.m = "normal";
      this.a = false;
    }
    
    public PlayerProperties(Parcel paramParcel)
    {
      super();
    }
    
    public boolean doLoop()
    {
      return this.c;
    }
    
    public boolean doMute()
    {
      return this.K;
    }
    
    public boolean exitOnComplete()
    {
      return this.M.equalsIgnoreCase("exit");
    }
    
    public boolean isAutoPlay()
    {
      return this.g == true;
    }
    
    public boolean isFullScreen()
    {
      return this.m.equalsIgnoreCase("fullscreen");
    }
    
    public void muteAudio()
    {
      this.K = true;
    }
    
    public void setProperties(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String paramString1, String paramString2)
    {
      this.g = paramBoolean2;
      this.j = paramBoolean3;
      this.c = paramBoolean5;
      this.K = paramBoolean1;
      this.m = paramString1;
      this.M = paramString2;
      this.a = paramBoolean4;
    }
    
    public void setStopStyle(String paramString)
    {
      this.M = paramString;
    }
    
    public boolean showControl()
    {
      return this.j;
    }
  }
  
  public static class Properties
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<Properties> M = new Parcelable.Creator()
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
    public int g;
    public float i;
    public boolean j;
    
    public Properties()
    {
      this.j = false;
      this.g = 0;
      this.i = 0.0F;
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
            if (((String)localObject).equals("class com.pvmrcbskbspfw.AdNavigationStringEnum")) {
              localField.set(this, AdNavigationStringEnum.fromString(paramParcel.readString()));
            } else if (((String)localObject).equals("class com.pvmrcbskbspfw.AdTransitionStringEnum")) {
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
            if (((String)localObject2).equals("class com.pvmrcbskbspfw.AdNavigationStringEnum")) {
              paramParcel.writeString(((AdNavigationStringEnum)((Field)localObject1).get(this)).getText());
            } else if (((String)localObject2).equals("class com.pvmrcbskbspfw.AdTransitionStringEnum")) {
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
