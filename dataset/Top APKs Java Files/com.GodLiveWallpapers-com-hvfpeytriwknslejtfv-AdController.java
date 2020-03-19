package com.hvfpeytriwknslejtfv;

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
  public static final String AA = "normal";
  public static final String C = "exit";
  public static final String Y = "LBAdController";
  public static final String y = "fullscreen";
  protected Context b;
  protected AdView oA;
  
  public AdController(Context paramContext, String paramString)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
  }
  
  public AdController(Context paramContext, String paramString, WebView paramWebView)
  {
    this.b = paramContext;
    this.DA = paramString;
    this.uA = paramWebView;
    this.R = new RelativeLayout(this.b);
    L();
  }
  
  public AdController(Context paramContext, String paramString, RelativeLayout paramRelativeLayout)
  {
    this.b = paramContext;
    this.DA = paramString;
    paramContext = paramRelativeLayout;
    if (paramRelativeLayout == null) {
      paramContext = new RelativeLayout(this.b);
    }
    this.R = paramContext;
    this.uA = null;
    L();
  }
  
  public AdController(Context paramContext, String paramString, AdAudioListener paramAdAudioListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.L = paramAdAudioListener;
  }
  
  public AdController(Context paramContext, String paramString, AdListener paramAdListener)
  {
    this(paramContext, paramString, new RelativeLayout(paramContext));
    this.D = paramAdListener;
  }
  
  public AdController(AdView paramAdView, Context paramContext)
  {
    this.oA = paramAdView;
    this.b = paramContext;
  }
  
  protected static Object k(JSONObject paramJSONObject, Class<?> paramClass)
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
          boolean bool = str1.startsWith(AdRequest.k(";"));
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
          if (!str2.equals("class com.hvfpeytriwknslejtfv.AdNavigationStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdNavigationStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
          if (!str2.equals("class com.hvfpeytriwknslejtfv.AdTransitionStringEnum")) {
            continue;
          }
          localJSONException.set(paramClass, AdTransitionStringEnum.fromString(paramJSONObject.getString(localNumberFormatException)));
          continue;
        }
        try
        {
          if (str1.startsWith(AdWakeLock.k("\003\"X")))
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
  
  public static String k(String paramString)
  {
    int i1 = paramString.length();
    char[] arrayOfChar = new char[i1];
    int i2 = i1 - 1;
    for (i1 = i2; i2 >= 0; i1 = i2)
    {
      i2 = paramString.charAt(i1);
      int i3 = i1 - 1;
      arrayOfChar[i1] = ((char)(i2 ^ 0x30));
      if (i3 < 0) {
        break;
      }
      i2 = i3 - 1;
      arrayOfChar[i3] = ((char)(paramString.charAt(i3) ^ 0x7B));
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
    //   4: putfield 243	com/hvfpeytriwknslejtfv/AdController:O	Z
    //   7: aload_0
    //   8: getfield 294	com/hvfpeytriwknslejtfv/AdController:b	Landroid/content/Context;
    //   11: ldc_w 332
    //   14: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   17: iconst_0
    //   18: invokevirtual 343	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   21: astore 4
    //   23: aload 4
    //   25: invokeinterface 404 1 0
    //   30: astore 5
    //   32: aload_0
    //   33: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   36: ifnull +1085 -> 1121
    //   39: aload 5
    //   41: ldc_w 1733
    //   44: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   47: aload_0
    //   48: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   51: ldc_w 1735
    //   54: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   57: invokevirtual 1738	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   60: invokeinterface 813 4 0
    //   65: pop
    //   66: aload 5
    //   68: invokestatic 424	com/hvfpeytriwknslejtfv/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   71: aload_0
    //   72: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   75: ldc_w 1193
    //   78: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   81: invokevirtual 436	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   84: ldc_w 817
    //   87: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   90: invokevirtual 444	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   93: ifeq +53 -> 146
    //   96: aload_0
    //   97: getfield 265	com/hvfpeytriwknslejtfv/AdController:D	Lcom/hvfpeytriwknslejtfv/AdListener;
    //   100: ifnull +17 -> 117
    //   103: aload_0
    //   104: iconst_1
    //   105: putfield 251	com/hvfpeytriwknslejtfv/AdController:G	Z
    //   108: aload_0
    //   109: getfield 265	com/hvfpeytriwknslejtfv/AdController:D	Lcom/hvfpeytriwknslejtfv/AdListener;
    //   112: invokeinterface 1358 1 0
    //   117: aload_0
    //   118: getfield 305	com/hvfpeytriwknslejtfv/AdController:L	Lcom/hvfpeytriwknslejtfv/AdAudioListener;
    //   121: ifnull +17 -> 138
    //   124: aload_0
    //   125: iconst_1
    //   126: putfield 281	com/hvfpeytriwknslejtfv/AdController:p	Z
    //   129: aload_0
    //   130: getfield 305	com/hvfpeytriwknslejtfv/AdController:L	Lcom/hvfpeytriwknslejtfv/AdAudioListener;
    //   133: invokeinterface 1359 1 0
    //   138: return
    //   139: astore 6
    //   141: goto -70 -> 71
    //   144: astore 6
    //   146: aload_0
    //   147: getfield 294	com/hvfpeytriwknslejtfv/AdController:b	Landroid/content/Context;
    //   150: ifnull +194 -> 344
    //   153: aload_0
    //   154: getfield 294	com/hvfpeytriwknslejtfv/AdController:b	Landroid/content/Context;
    //   157: instanceof 1116
    //   160: ifeq +184 -> 344
    //   163: new 1740	android/util/DisplayMetrics
    //   166: dup
    //   167: invokespecial 1741	android/util/DisplayMetrics:<init>	()V
    //   170: astore 6
    //   172: aload_0
    //   173: getfield 294	com/hvfpeytriwknslejtfv/AdController:b	Landroid/content/Context;
    //   176: checkcast 1116	android/app/Activity
    //   179: invokevirtual 1745	android/app/Activity:getWindowManager	()Landroid/view/WindowManager;
    //   182: invokeinterface 1751 1 0
    //   187: aload 6
    //   189: invokevirtual 1757	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   192: new 1759	android/graphics/Rect
    //   195: dup
    //   196: invokespecial 1760	android/graphics/Rect:<init>	()V
    //   199: astore 7
    //   201: aload_0
    //   202: getfield 294	com/hvfpeytriwknslejtfv/AdController:b	Landroid/content/Context;
    //   205: checkcast 1116	android/app/Activity
    //   208: invokevirtual 1764	android/app/Activity:getWindow	()Landroid/view/Window;
    //   211: astore 8
    //   213: aload 8
    //   215: invokevirtual 1770	android/view/Window:getDecorView	()Landroid/view/View;
    //   218: aload 7
    //   220: invokevirtual 1774	android/view/View:getWindowVisibleDisplayFrame	(Landroid/graphics/Rect;)V
    //   223: aload 7
    //   225: getfield 1777	android/graphics/Rect:top	I
    //   228: istore_3
    //   229: aload 8
    //   231: ldc_w 1437
    //   234: invokevirtual 1778	android/view/Window:findViewById	(I)Landroid/view/View;
    //   237: invokevirtual 1781	android/view/View:getTop	()I
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
    //   253: getfield 1784	android/util/DisplayMetrics:widthPixels	I
    //   256: putfield 1112	com/hvfpeytriwknslejtfv/AdController:YA	I
    //   259: aload_0
    //   260: aload 6
    //   262: getfield 1787	android/util/DisplayMetrics:heightPixels	I
    //   265: iload_3
    //   266: isub
    //   267: iload_1
    //   268: isub
    //   269: putfield 1114	com/hvfpeytriwknslejtfv/AdController:Q	I
    //   272: ldc 91
    //   274: new 345	java/lang/StringBuilder
    //   277: dup
    //   278: invokespecial 346	java/lang/StringBuilder:<init>	()V
    //   281: iconst_0
    //   282: ldc_w 1789
    //   285: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   288: invokevirtual 355	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   291: aload 6
    //   293: getfield 1787	android/util/DisplayMetrics:heightPixels	I
    //   296: invokevirtual 555	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   299: ldc_w 1791
    //   302: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   305: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: iload_3
    //   309: invokevirtual 555	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   312: ldc_w 1793
    //   315: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   318: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: iconst_0
    //   322: invokevirtual 555	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   325: ldc_w 1791
    //   328: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   331: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: iload_1
    //   335: invokevirtual 555	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   338: invokevirtual 363	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: invokestatic 389	com/hvfpeytriwknslejtfv/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   344: aload_0
    //   345: aload_0
    //   346: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   349: ldc_w 1795
    //   352: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   355: invokevirtual 734	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   358: putfield 257	com/hvfpeytriwknslejtfv/AdController:dA	I
    //   361: aload_0
    //   362: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   365: ldc_w 1797
    //   368: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   371: invokevirtual 734	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   374: istore_1
    //   375: aload_0
    //   376: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   379: ldc_w 1799
    //   382: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   385: invokevirtual 734	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   388: istore_3
    //   389: iload_3
    //   390: ifle +191 -> 581
    //   393: aload_0
    //   394: iload_1
    //   395: bipush 60
    //   397: imul
    //   398: iload_3
    //   399: idiv
    //   400: putfield 259	com/hvfpeytriwknslejtfv/AdController:gA	I
    //   403: aload_0
    //   404: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   407: ldc_w 1801
    //   410: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   413: invokevirtual 1100	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   416: ldc_w 1183
    //   419: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   422: invokevirtual 1103	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   425: ifeq +8 -> 433
    //   428: aload_0
    //   429: iconst_1
    //   430: putfield 241	com/hvfpeytriwknslejtfv/AdController:cA	Z
    //   433: aload 5
    //   435: new 345	java/lang/StringBuilder
    //   438: dup
    //   439: invokespecial 346	java/lang/StringBuilder:<init>	()V
    //   442: iconst_0
    //   443: ldc_w 1803
    //   446: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   449: invokevirtual 355	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   452: aload_0
    //   453: getfield 296	com/hvfpeytriwknslejtfv/AdController:DA	Ljava/lang/String;
    //   456: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: invokevirtual 363	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   462: aload_0
    //   463: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   466: ldc_w 1805
    //   469: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   472: invokevirtual 436	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   475: invokeinterface 821 3 0
    //   480: pop
    //   481: aload 5
    //   483: invokestatic 424	com/hvfpeytriwknslejtfv/AdUtils:apply	(Landroid/content/SharedPreferences$Editor;)V
    //   486: aload_0
    //   487: getfield 279	com/hvfpeytriwknslejtfv/AdController:g	Z
    //   490: ifeq +229 -> 719
    //   493: aload_0
    //   494: invokevirtual 1808	com/hvfpeytriwknslejtfv/AdController:retrieveAudioAd	()V
    //   497: aload 4
    //   499: new 345	java/lang/StringBuilder
    //   502: dup
    //   503: invokespecial 346	java/lang/StringBuilder:<init>	()V
    //   506: iconst_0
    //   507: ldc_w 1810
    //   510: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   513: invokevirtual 355	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   516: aload_0
    //   517: getfield 296	com/hvfpeytriwknslejtfv/AdController:DA	Ljava/lang/String;
    //   520: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: invokevirtual 363	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   526: iconst_0
    //   527: invokeinterface 909 3 0
    //   532: ifeq -394 -> 138
    //   535: aload_0
    //   536: getfield 1460	com/hvfpeytriwknslejtfv/AdController:d	Landroid/os/Handler;
    //   539: ifnull -401 -> 138
    //   542: aload_0
    //   543: getfield 1460	com/hvfpeytriwknslejtfv/AdController:d	Landroid/os/Handler;
    //   546: aload_0
    //   547: getfield 1462	com/hvfpeytriwknslejtfv/AdController:zA	Ljava/lang/Runnable;
    //   550: aload_0
    //   551: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   554: ldc_w 1812
    //   557: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   560: invokevirtual 734	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   563: sipush 1000
    //   566: imul
    //   567: i2l
    //   568: invokevirtual 1816	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   571: pop
    //   572: return
    //   573: astore 4
    //   575: return
    //   576: iconst_0
    //   577: istore_1
    //   578: goto -328 -> 250
    //   581: aload_0
    //   582: bipush 10
    //   584: putfield 259	com/hvfpeytriwknslejtfv/AdController:gA	I
    //   587: goto -184 -> 403
    //   590: astore 6
    //   592: aload_0
    //   593: sipush 500
    //   596: putfield 257	com/hvfpeytriwknslejtfv/AdController:dA	I
    //   599: aload_0
    //   600: bipush 10
    //   602: putfield 259	com/hvfpeytriwknslejtfv/AdController:gA	I
    //   605: goto -202 -> 403
    //   608: astore 6
    //   610: aload_0
    //   611: getfield 1818	com/hvfpeytriwknslejtfv/AdController:QA	Ljava/lang/String;
    //   614: ifnull +61 -> 675
    //   617: aload_0
    //   618: getfield 1818	com/hvfpeytriwknslejtfv/AdController:QA	Ljava/lang/String;
    //   621: ldc_w 817
    //   624: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   627: invokevirtual 444	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   630: ifne +45 -> 675
    //   633: aload 5
    //   635: new 345	java/lang/StringBuilder
    //   638: dup
    //   639: invokespecial 346	java/lang/StringBuilder:<init>	()V
    //   642: iconst_0
    //   643: ldc_w 1820
    //   646: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   649: invokevirtual 355	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   652: aload_0
    //   653: getfield 296	com/hvfpeytriwknslejtfv/AdController:DA	Ljava/lang/String;
    //   656: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   659: invokevirtual 363	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   662: aload_0
    //   663: getfield 1818	com/hvfpeytriwknslejtfv/AdController:QA	Ljava/lang/String;
    //   666: invokeinterface 821 3 0
    //   671: pop
    //   672: goto -191 -> 481
    //   675: aload 5
    //   677: new 345	java/lang/StringBuilder
    //   680: dup
    //   681: invokespecial 346	java/lang/StringBuilder:<init>	()V
    //   684: iconst_0
    //   685: ldc_w 1803
    //   688: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   691: invokevirtual 355	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   694: aload_0
    //   695: getfield 296	com/hvfpeytriwknslejtfv/AdController:DA	Ljava/lang/String;
    //   698: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   701: invokevirtual 363	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   704: ldc_w 438
    //   707: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   710: invokeinterface 821 3 0
    //   715: pop
    //   716: goto -235 -> 481
    //   719: aload_0
    //   720: new 326	com/hvfpeytriwknslejtfv/AdWebView
    //   723: dup
    //   724: aload_0
    //   725: getfield 294	com/hvfpeytriwknslejtfv/AdController:b	Landroid/content/Context;
    //   728: checkcast 1116	android/app/Activity
    //   731: aload_0
    //   732: aload_0
    //   733: getfield 241	com/hvfpeytriwknslejtfv/AdController:cA	Z
    //   736: aload_0
    //   737: getfield 265	com/hvfpeytriwknslejtfv/AdController:D	Lcom/hvfpeytriwknslejtfv/AdListener;
    //   740: aload_0
    //   741: getfield 300	com/hvfpeytriwknslejtfv/AdController:R	Landroid/widget/RelativeLayout;
    //   744: invokespecial 1823	com/hvfpeytriwknslejtfv/AdWebView:<init>	(Landroid/content/Context;Lcom/hvfpeytriwknslejtfv/AdController;ZLcom/hvfpeytriwknslejtfv/AdListener;Landroid/widget/RelativeLayout;)V
    //   747: putfield 315	com/hvfpeytriwknslejtfv/AdController:CA	Lcom/hvfpeytriwknslejtfv/AdWebView;
    //   750: ldc 91
    //   752: ldc_w 1825
    //   755: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   758: invokestatic 389	com/hvfpeytriwknslejtfv/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   761: ldc_w 1827
    //   764: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   767: invokestatic 519	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   770: ldc_w 1829
    //   773: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   776: aconst_null
    //   777: checkcast 1407	[Ljava/lang/Class;
    //   780: invokevirtual 1411	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   783: aload_0
    //   784: getfield 315	com/hvfpeytriwknslejtfv/AdController:CA	Lcom/hvfpeytriwknslejtfv/AdWebView;
    //   787: aconst_null
    //   788: checkcast 1413	[Ljava/lang/Object;
    //   791: invokevirtual 1419	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   794: pop
    //   795: aload_0
    //   796: getfield 315	com/hvfpeytriwknslejtfv/AdController:CA	Lcom/hvfpeytriwknslejtfv/AdWebView;
    //   799: iconst_0
    //   800: invokevirtual 982	com/hvfpeytriwknslejtfv/AdWebView:setBackgroundColor	(I)V
    //   803: aload_0
    //   804: getfield 315	com/hvfpeytriwknslejtfv/AdController:CA	Lcom/hvfpeytriwknslejtfv/AdWebView;
    //   807: aload_0
    //   808: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   811: invokevirtual 1833	com/hvfpeytriwknslejtfv/AdWebView:setResults	(Lorg/json/JSONObject;)V
    //   814: aload_0
    //   815: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   818: ldc_w 1835
    //   821: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   824: invokevirtual 734	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   827: istore_1
    //   828: iload_1
    //   829: iconst_1
    //   830: if_icmpne +18 -> 848
    //   833: aload_0
    //   834: getfield 315	com/hvfpeytriwknslejtfv/AdController:CA	Lcom/hvfpeytriwknslejtfv/AdWebView;
    //   837: new 26	com/hvfpeytriwknslejtfv/AdController$3
    //   840: dup
    //   841: aload_0
    //   842: invokespecial 1836	com/hvfpeytriwknslejtfv/AdController$3:<init>	(Lcom/hvfpeytriwknslejtfv/AdController;)V
    //   845: invokevirtual 1840	com/hvfpeytriwknslejtfv/AdWebView:setOnKeyListener	(Landroid/view/View$OnKeyListener;)V
    //   848: aload_0
    //   849: getfield 309	com/hvfpeytriwknslejtfv/AdController:oA	Lcom/hvfpeytriwknslejtfv/AdView;
    //   852: ifnonnull +214 -> 1066
    //   855: aload_0
    //   856: new 317	com/hvfpeytriwknslejtfv/AdView
    //   859: dup
    //   860: aload_0
    //   861: getfield 294	com/hvfpeytriwknslejtfv/AdController:b	Landroid/content/Context;
    //   864: checkcast 1116	android/app/Activity
    //   867: aload_0
    //   868: aload_0
    //   869: getfield 265	com/hvfpeytriwknslejtfv/AdController:D	Lcom/hvfpeytriwknslejtfv/AdListener;
    //   872: invokespecial 1843	com/hvfpeytriwknslejtfv/AdView:<init>	(Landroid/content/Context;Lcom/hvfpeytriwknslejtfv/AdController;Lcom/hvfpeytriwknslejtfv/AdListener;)V
    //   875: putfield 309	com/hvfpeytriwknslejtfv/AdController:oA	Lcom/hvfpeytriwknslejtfv/AdView;
    //   878: aload_0
    //   879: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   882: ldc_w 1845
    //   885: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   888: invokevirtual 734	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   891: iconst_1
    //   892: if_icmpeq -754 -> 138
    //   895: aload_0
    //   896: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   899: ldc_w 1847
    //   902: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   905: invokevirtual 734	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   908: istore_1
    //   909: iload_1
    //   910: ifne -772 -> 138
    //   913: aload_0
    //   914: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   917: ldc_w 1849
    //   920: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   923: invokevirtual 734	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   926: ifle +190 -> 1116
    //   929: aload_0
    //   930: getfield 394	com/hvfpeytriwknslejtfv/AdController:P	Lorg/json/JSONObject;
    //   933: ldc_w 1851
    //   936: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   939: invokevirtual 734	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   942: sipush 1000
    //   945: imul
    //   946: istore_1
    //   947: ldc 91
    //   949: new 345	java/lang/StringBuilder
    //   952: dup
    //   953: invokespecial 346	java/lang/StringBuilder:<init>	()V
    //   956: iconst_0
    //   957: ldc_w 1853
    //   960: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   963: invokevirtual 355	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   966: iload_1
    //   967: invokevirtual 555	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   970: ldc_w 1855
    //   973: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   976: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: invokevirtual 363	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   982: invokestatic 398	com/hvfpeytriwknslejtfv/AdLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   985: new 1464	android/os/Handler
    //   988: dup
    //   989: invokespecial 1856	android/os/Handler:<init>	()V
    //   992: new 28	com/hvfpeytriwknslejtfv/AdController$4
    //   995: dup
    //   996: aload_0
    //   997: invokespecial 1857	com/hvfpeytriwknslejtfv/AdController$4:<init>	(Lcom/hvfpeytriwknslejtfv/AdController;)V
    //   1000: iload_1
    //   1001: i2l
    //   1002: invokevirtual 1816	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   1005: pop
    //   1006: return
    //   1007: astore 4
    //   1009: aload_0
    //   1010: invokespecial 330	com/hvfpeytriwknslejtfv/AdController:b	()V
    //   1013: return
    //   1014: astore 4
    //   1016: aload_0
    //   1017: invokespecial 330	com/hvfpeytriwknslejtfv/AdController:b	()V
    //   1020: return
    //   1021: astore 4
    //   1023: ldc 91
    //   1025: new 345	java/lang/StringBuilder
    //   1028: dup
    //   1029: invokespecial 346	java/lang/StringBuilder:<init>	()V
    //   1032: iconst_0
    //   1033: ldc_w 1859
    //   1036: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   1039: invokevirtual 355	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   1042: aload 4
    //   1044: invokevirtual 654	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1047: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1050: invokevirtual 363	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1053: invokestatic 656	com/hvfpeytriwknslejtfv/AdLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1056: ldc 91
    //   1058: aload 4
    //   1060: invokestatic 676	com/hvfpeytriwknslejtfv/AdLog:printStackTrace	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   1063: goto -260 -> 803
    //   1066: ldc_w 1827
    //   1069: invokestatic 351	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
    //   1072: invokestatic 519	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   1075: ldc_w 1829
    //   1078: invokestatic 337	com/hvfpeytriwknslejtfv/AdRequest:k	(Ljava/lang/String;)Ljava/lang/String;
    //   1081: aconst_null
    //   1082: checkcast 1407	[Ljava/lang/Class;
    //   1085: invokevirtual 1411	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1088: aload_0
    //   1089: getfield 309	com/hvfpeytriwknslejtfv/AdController:oA	Lcom/hvfpeytriwknslejtfv/AdView;
    //   1092: aconst_null
    //   1093: checkcast 1413	[Ljava/lang/Object;
    //   1096: invokevirtual 1419	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1099: pop
    //   1100: aload_0
    //   1101: getfield 309	com/hvfpeytriwknslejtfv/AdController:oA	Lcom/hvfpeytriwknslejtfv/AdView;
    //   1104: iconst_0
    //   1105: invokevirtual 1232	com/hvfpeytriwknslejtfv/AdView:setBackgroundColor	(I)V
    //   1108: goto -230 -> 878
    //   1111: astore 4
    //   1113: goto -235 -> 878
    //   1116: aload_0
    //   1117: invokespecial 330	com/hvfpeytriwknslejtfv/AdController:b	()V
    //   1120: return
    //   1121: aload_0
    //   1122: getfield 265	com/hvfpeytriwknslejtfv/AdController:D	Lcom/hvfpeytriwknslejtfv/AdListener;
    //   1125: ifnull +17 -> 1142
    //   1128: aload_0
    //   1129: iconst_1
    //   1130: putfield 251	com/hvfpeytriwknslejtfv/AdController:G	Z
    //   1133: aload_0
    //   1134: getfield 265	com/hvfpeytriwknslejtfv/AdController:D	Lcom/hvfpeytriwknslejtfv/AdListener;
    //   1137: invokeinterface 1358 1 0
    //   1142: aload_0
    //   1143: getfield 305	com/hvfpeytriwknslejtfv/AdController:L	Lcom/hvfpeytriwknslejtfv/AdAudioListener;
    //   1146: ifnull -1008 -> 138
    //   1149: aload_0
    //   1150: iconst_1
    //   1151: putfield 281	com/hvfpeytriwknslejtfv/AdController:p	Z
    //   1154: aload_0
    //   1155: getfield 305	com/hvfpeytriwknslejtfv/AdController:L	Lcom/hvfpeytriwknslejtfv/AdAudioListener;
    //   1158: invokeinterface 1359 1 0
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
    return this.b.getSharedPreferences(AdWakeLock.k("BRwFwRwNqE"), 0).getString(this.DA, AdWakeLock.k("DwFsU~T"));
  }
  
  public void audioAdRetrieved(Integer paramInteger)
  {
    AdLog.d("LBAdController", AdWakeLock.k("sUvI}avrwT`IwVwD"));
    if ((this.FA) || (this.x))
    {
      AdLog.d("LBAdController", AdRequest.k("?q>pl?8\"}$m\"vp~\"w=81m4q?Y4J5l\"q5n5|py#8#l?j9v78$wp{1{8}"));
      paramInteger = this.b.getSharedPreferences(AdWakeLock.k("BRwFwRwNqE"), 0).edit();
      paramInteger.putLong(this.DA, System.currentTimeMillis());
      AdUtils.apply(paramInteger);
      this.iA = true;
      if ((this.x) && (this.L != null)) {
        this.L.onAdCached();
      }
    }
    do
    {
      return;
      if (paramInteger.intValue() == 0)
      {
        H();
        return;
      }
    } while (this.L == null);
    this.L.onAdFailed();
    this.p = true;
  }
  
  public void checkForAudioAd(String paramString1, String paramString2)
  {
    if ((!this.S) && (paramString1 != null) && (!paramString1.equals(AdWakeLock.k("|U~L"))) && (!paramString1.equals("")))
    {
      if (this.P.isNull(AdRequest.k("1|1m4q?m\"t"))) {}
      for (;;)
      {
        try
        {
          this.P.put(AdWakeLock.k("sDsUvI}U`L"), paramString1);
          this.P.put(AdRequest.k("y4m\"t"), paramString2);
          if ((!this.p) && (!this.P.isNull(AdWakeLock.k("sDsUvI}U`L"))))
          {
            if (this.A == null) {
              this.A = new AdShakeListener(true);
            }
            if (this.U == null)
            {
              this.B = ((AudioManager)this.b.getSystemService(AdRequest.k("y%|9w")));
              this.U = ((SensorManager)this.b.getSystemService(AdWakeLock.k("aE|S}R")));
              this.xA = 0.0F;
              this.u = 9.80665F;
              this.V = 9.80665F;
            }
            retrieveAudioAd();
          }
          return;
        }
        catch (JSONException paramString1) {}
      }
    }
    AdLog.d("LBAdController", AdRequest.k("\036wpy%|9wp{?u w>}>l"));
  }
  
  public void destroyAd()
  {
    AdLog.i("LBAdController", AdWakeLock.k("vEaT`Okav\000qA~LwD"));
    this.S = true;
    c();
    f();
  }
  
  public boolean getAdDestroyed()
  {
    return this.S;
  }
  
  public boolean getAdLoaded()
  {
    return this.G;
  }
  
  public boolean getLoadInBackground()
  {
    return this.W;
  }
  
  public boolean getOnAdLoaded()
  {
    return this.e;
  }
  
  public void hideElements()
  {
    try
    {
      this.CA.setVisibility(8);
      this.j.setVisibility(8);
      this.LA.setVisibility(8);
      this.K.setVisibility(8);
      this.k.setVisibility(8);
      this.eA.setVisibility(8);
      this.F.setVisibility(8);
      this.MA.setVisibility(8);
      this.kA.setVisibility(8);
      this.R.setVisibility(8);
      this.s.setVisibility(8);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void loadAd()
  {
    if (!(this.b instanceof Activity))
    {
      Log.e("LBAdController", AdWakeLock.k("L}Avav\b;\000tA{LwD2\r2a2vsL{D2aqT{V{Tk\000|Of\000bAaSwD"));
      if (this.D != null) {
        this.D.onAdFailed();
      }
    }
    do
    {
      return;
      if ((this.KA == null) || (this.KA.getStatus() != AsyncTask.Status.RUNNING)) {
        break;
      }
    } while (!this.FA);
    this.FA = false;
    return;
    if ((this.jA) && (C()))
    {
      AdLog.i("LBAdController", AdRequest.k("\023y3p589kpn1t9|p5p?q>pl?8%k58$p1l"));
      this.W = false;
      this.FA = false;
      b();
      this.I = false;
      h();
      if (this.D != null) {
        this.D.onAdLoaded();
      }
      if (this.iA)
      {
        H();
        this.iA = false;
      }
      this.jA = false;
      SharedPreferences.Editor localEditor = this.b.getSharedPreferences(AdWakeLock.k("BRwFwRwNqE"), 0).edit();
      localEditor.putLong(this.DA, -1L);
      AdUtils.apply(localEditor);
      return;
    }
    this.FA = false;
    this.I = false;
    this.jA = false;
    this.O = false;
    AdLog.i("LBAdController", AdWakeLock.k("L}Avav\000qA~LwD"));
    this.e = false;
    if (!this.O)
    {
      this.X = true;
      this.S = false;
      AdLog.i("LBAdController", AdRequest.k("3y<t9v78<w1|\021|\031v9l9y<q*}"));
      a();
    }
    for (;;)
    {
      long l1 = System.currentTimeMillis();
      AdLog.d("LBAdController", l1);
      return;
      AdLog.i("LBAdController", AdWakeLock.k("CsL~I|G2D{SbLsYSD"));
      b();
    }
  }
  
  public void loadAdToCache()
  {
    if (!(this.b instanceof Activity))
    {
      Log.e("LBAdController", AdRequest.k("t?y4Y40y86y9t5|p5pYpN1t9|pY3l9n9l)8>w$8 y#k5|"));
      if (this.D != null) {
        this.D.onAdFailed();
      }
      return;
    }
    AdLog.i("LBAdController", AdWakeLock.k("~OsDSDFOQAqHw\000qA~LwD"));
    this.FA = true;
    this.e = false;
    if (!this.O)
    {
      this.X = true;
      this.S = false;
      a();
      return;
    }
    b();
  }
  
  public void loadAudioAd()
  {
    if (this.w != null) {}
    label346:
    for (;;)
    {
      return;
      if ((this.KA != null) && (this.KA.getStatus() == AsyncTask.Status.RUNNING))
      {
        if (this.x) {
          this.x = false;
        }
      }
      else
      {
        if ((this.iA) && (J()))
        {
          AdLog.i("LBAdController", AdRequest.k("\023y3p589kpn1t9|p5p?q>pl?8 t1apy%|9wp~\"w=8$p1l"));
          this.x = false;
          H();
          this.iA = false;
          this.jA = false;
          SharedPreferences.Editor localEditor = this.b.getSharedPreferences(AdWakeLock.k("BRwFwRwNqE"), 0).edit();
          localEditor.putLong(this.DA, -1L);
          AdUtils.apply(localEditor);
          return;
        }
        this.x = false;
        this.iA = false;
        this.O = false;
        AdLog.i("LBAdController", AdWakeLock.k("~OsDSUvI}av\000qA~LwD"));
        if (!this.O)
        {
          this.g = true;
          if (this.U == null)
          {
            this.A = new AdShakeListener(true);
            this.B = ((AudioManager)this.b.getSystemService(AdRequest.k("y%|9w")));
            this.U = ((SensorManager)this.b.getSystemService(AdWakeLock.k("aE|S}R")));
            this.xA = 0.0F;
            this.u = 9.80665F;
            this.V = 9.80665F;
          }
          a();
        }
        for (;;)
        {
          if ((this.L == null) || (this.l <= 0)) {
            break label346;
          }
          if (this.i == null) {
            this.i = new Runnable()
            {
              public void run()
              {
                try
                {
                  if ((!AdController.H(AdController.this)) && (!AdController.l(AdController.this)))
                  {
                    AdLog.i("LBAdController", AdJSInterface.k("_,q&`0_%B'C1\0206B+W%U0U&"));
                    AdController.a(AdController.this).onAdProgress();
                    AdController.e(AdController.this).postDelayed(AdController.f(AdController.this), AdController.j(AdController.this) * 1000);
                  }
                  return;
                }
                catch (Exception localException)
                {
                  AdLog.e("LBAdController", AdRequest.k("}\"j?jpo8}>8?v\021|\000j?\"}#kpl\"q75j5|"));
                  AdLog.printStackTrace("LBAdController", localException);
                }
              }
            };
          }
          if (this.mA != null) {
            break;
          }
          this.mA = new Handler();
          this.mA.postDelayed(this.i, this.l * 1000);
          return;
          H();
        }
      }
    }
  }
  
  public void loadAudioAdToCache()
  {
    if (this.w != null) {
      return;
    }
    AdLog.i("LBAdController", AdWakeLock.k("L}AvagD{OSDFOQAqHw\000qA~LwD"));
    if (!this.O)
    {
      this.g = true;
      this.x = true;
      if (this.U == null)
      {
        this.A = new AdShakeListener(true);
        this.B = ((AudioManager)this.b.getSystemService(AdRequest.k("y%|9w")));
        this.U = ((SensorManager)this.b.getSystemService(AdWakeLock.k("aE|S}R")));
        this.xA = 0.0F;
        this.u = 9.80665F;
        this.V = 9.80665F;
      }
      a();
      return;
    }
    H();
  }
  
  public void loadAudioTrack(long paramLong)
  {
    if (this.zA == null) {
      this.zA = new Runnable()
      {
        public void run()
        {
          SharedPreferences.Editor localEditor = AdController.this.b.getSharedPreferences(AdEncryption.j(";-\0169\016-\0161\b:"), 0).edit();
          localEditor.putBoolean(AdController.m(AdController.this), true);
          AdUtils.apply(localEditor);
          AdController.this.loadAudioAd();
        }
      };
    }
    if (this.d == null) {
      this.d = new Handler();
    }
    for (;;)
    {
      this.d.postDelayed(this.zA, 60L * paramLong * 1000L);
      return;
      this.d.removeCallbacks(this.zA);
    }
  }
  
  public void loadReEngagement()
  {
    int i1;
    if (this.KA != null)
    {
      AdLog.i("LBAdController", this.KA.getStatus());
      if (this.KA.getStatus() == AsyncTask.Status.FINISHED) {
        i1 = 1;
      }
    }
    for (;;)
    {
      if (i1 != 0)
      {
        Object localObject = this.b.getSharedPreferences(AdWakeLock.k("BRwFwRwNqE"), 0);
        Context localContext = this.b;
        String str = this.DA;
        if (((SharedPreferences)localObject).getBoolean(AdRequest.k("j5}>15u5v$y<y\"u"), false)) {}
        for (localObject = AdWakeLock.k("`EwNuAuEE|TMA~A`M");; localObject = "reengagement")
        {
          this.KA = new AdTask(this, localContext, str, (String)localObject);
          this.KA.setSubId(this.t);
          this.KA.setTokens(this.hA);
          k(this.KA, new String[] { "" });
          return;
          i1 = 0;
          break;
        }
      }
      AdLog.i("LBAdController", AdRequest.k("V?8\"}!m5k$8$wpz58=y4}"));
      return;
      i1 = 1;
    }
  }
  
  public void loadStartAd(String paramString1, String paramString2)
  {
    loadAd();
    this.N = new AdController(this.b, paramString1);
    this.N.loadAudioTrack(2L);
    new AdController(this.b, paramString2).loadReEngagement();
  }
  
  public boolean onBackPressed()
  {
    if (this.m)
    {
      loadAd();
      return true;
    }
    return false;
  }
  
  public void onLinkClicked()
  {
    M();
  }
  
  public void reEngagementInitialized()
  {
    if (this.P != null) {
      K();
    }
    SharedPreferences localSharedPreferences = this.b.getSharedPreferences(AdWakeLock.k("BRwFwRwNqE"), 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (localSharedPreferences.getBoolean(AdRequest.k("j5}>15u5v$y<y\"u"), false))
    {
      localEditor.putBoolean(AdWakeLock.k("RwE|GsGwMwNfA~A`M"), false);
      AdUtils.apply(localEditor);
      F();
    }
  }
  
  public void retrieveAudioAd()
  {
    int i2 = 1;
    AdLog.d("LBAdController", AdRequest.k("j5l\"q5n5Y%|9w\021|"));
    for (;;)
    {
      try
      {
        if (this.P.get(AdWakeLock.k("aH}W")).equals(AdRequest.k(")")))
        {
          i1 = i2;
          if (this.E != null)
          {
            if (this.E.getStatus() != AsyncTask.Status.FINISHED) {
              break label161;
            }
            i1 = i2;
          }
          if (i1 != 0)
          {
            AdLog.d("LBAdController", AdWakeLock.k("uO{Nu\000fO2MsKw\000`EcUwSf\000sNv\000tEfCz\000sUvI}\000sD"));
            this.E = new AdAudioTask(this, this.b);
            k(this.E, new String[] { this.P.getString(AdRequest.k("1|1m4q?m\"t")) });
          }
        }
        else
        {
          this.g = false;
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
    this.S = paramBoolean;
  }
  
  public void setAdLoaded(boolean paramBoolean)
  {
    this.G = paramBoolean;
  }
  
  public void setAdditionalDockingMargin(int paramInt)
  {
    this.z = paramInt;
    AdLog.i("LBAdController", paramInt);
  }
  
  public void setCompleted(boolean paramBoolean)
  {
    this.rA = paramBoolean;
  }
  
  public void setHTML(String paramString)
  {
    if (this.oA != null)
    {
      paramString = paramString + AdRequest.k("$p$u<&");
      this.oA.loadHTMLWrap(paramString);
    }
  }
  
  public void setHTMLAds(boolean paramBoolean)
  {
    this.q = paramBoolean;
  }
  
  public void setHomeLoaded(boolean paramBoolean)
  {
    this.T = paramBoolean;
  }
  
  public void setLayout(RelativeLayout paramRelativeLayout)
  {
    this.R = paramRelativeLayout;
  }
  
  public void setLoadInBackground(boolean paramBoolean)
  {
    this.W = paramBoolean;
  }
  
  public void setLoading(boolean paramBoolean)
  {
    this.I = paramBoolean;
  }
  
  public void setOnAdLoaded(boolean paramBoolean)
  {
    this.e = paramBoolean;
    if ((this.W) && (!this.S)) {
      ((Activity)this.b).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (AdController.g(AdController.this))
          {
            SharedPreferences.Editor localEditor = AdController.this.b.getSharedPreferences(AdController.k("+B\036V\036B\036^\030U"), 0).edit();
            localEditor.putLong(AdController.m(AdController.this), System.currentTimeMillis());
            AdUtils.apply(localEditor);
            AdController.k(AdController.this, true);
            if (AdController.M(AdController.this) != null) {
              AdController.M(AdController.this).onAdCached();
            }
            return;
          }
          if ((!AdController.g(AdController.this)) && (AdController.M(AdController.this) != null))
          {
            AdLog.i("LBAdController", AdController.k("\017B\022W\034U\t\020\024^:T7_\032T\036T"));
            AdController.M(AdController.this).onAdLoaded();
          }
          AdController.e(AdController.this, false);
          AdController.C(AdController.this);
          AdController.j(AdController.this, false);
        }
      });
    }
  }
  
  public void setOnProgressInterval(int paramInt)
  {
    this.l = paramInt;
  }
  
  public void setResults(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.P = paramJSONObject;
      paramJSONObject = this.b.getSharedPreferences(AdRequest.k("\000j5~5j5v3}"), 0).edit();
    }
    try
    {
      paramJSONObject.putLong(AdWakeLock.k("AvRwCzEqKfIE"), this.P.getLong(AdRequest.k("y4j5{8}3s$q=}")));
      AdUtils.apply(paramJSONObject);
      AdLog.d("LBAdController", this.P.getLong(AdRequest.k("y4j5{8}3s$q=}")));
      return;
    }
    catch (Exception paramJSONObject)
    {
      AdLog.d("LBAdController", paramJSONObject.getMessage());
    }
  }
  
  public void setSubId(String paramString)
  {
    this.t = paramString;
  }
  
  public void setTokens(List<NameValuePair> paramList)
  {
    this.hA = paramList;
  }
  
  public void showElements()
  {
    try
    {
      this.CA.setVisibility(0);
      this.j.setVisibility(0);
      this.LA.setVisibility(0);
      this.K.setVisibility(0);
      this.k.setVisibility(0);
      this.eA.setVisibility(0);
      this.F.setVisibility(0);
      this.MA.setVisibility(0);
      this.kA.setVisibility(0);
      this.R.setVisibility(0);
      this.s.setVisibility(0);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void showInternetDialog()
  {
    if ((this.b != null) && ((this.b instanceof Activity)))
    {
      if (this.M == null)
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.b);
        localBuilder.setMessage(AdRequest.k("\031v$}\"v5lpv?lpy&y9t1z<}")).setCancelable(false).setPositiveButton(AdWakeLock.k("c~OaE"), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            AdController.h(AdController.this).dismiss();
          }
        });
        this.M = localBuilder.create();
      }
      this.M.show();
    }
  }
  
  public void stopAllListeners() {}
  
  public void triggerAdCompleted() {}
  
  public void triggerAdFailed()
  {
    AdLog.e("LBAdController", AdWakeLock.k("\\O2i|TwR|Ef\000qO|NwCfI}N2DwTwCfEv\0162n}\000SDa\000~OsDwD"));
    if (this.D != null) {}
    for (;;)
    {
      try
      {
        AdLog.i("LBAdController", AdRequest.k("?v\021|\026y9t5|pl\"q75j5|"));
        this.D.onAdFailed();
        this.G = true;
        if (this.L != null)
        {
          this.L.onAdFailed();
          this.p = true;
        }
        return;
      }
      catch (Exception localException)
      {
        AdLog.i("LBAdController", AdWakeLock.k("WR`O`\000eH{Lw\000qA~L{Nu\000}NSDTA{LwD"));
        AdLog.printStackTrace("LBAdController", localException);
      }
    }
  }
  
  private class AdClientPixel
    extends AsyncTask<String, Void, Boolean>
  {
    protected Boolean k(String... paramVarArgs)
    {
      Object localObject = paramVarArgs[0];
      if ((localObject != null) && (!((String)localObject).equals("")))
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        localBasicHttpParams.setParameter(AdRequest.k("p$l 6 j?l?{?t~n5j#q?v"), HttpVersion.HTTP_1_1);
        paramVarArgs = new DefaultHttpClient(localBasicHttpParams);
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 4000);
        localObject = new HttpGet((String)localObject);
      }
      try
      {
        if (paramVarArgs.execute((HttpUriRequest)localObject).getStatusLine().getStatusCode() == 200) {
          AdController.a(this.B, true);
        }
        return Boolean.valueOf(false);
      }
      catch (Exception paramVarArgs)
      {
        for (;;) {}
      }
    }
    
    protected void k(boolean paramBoolean)
    {
      if (paramBoolean) {
        AdController.a(this.B, true);
      }
    }
  }
  
  private class AdShakeListener
    implements SensorEventListener
  {
    public AdShakeListener(boolean paramBoolean)
    {
      this.D = paramBoolean;
      if (this.D)
      {
        this.E = new Handler();
        this.A = new Runnable()
        {
          public void run()
          {
            AdLog.i("LBAdController", AdDefines.k("QHCKG\000CD\002DGTGCVIMN\002EZPKRGS"));
            AdController.AdShakeListener.k(AdController.AdShakeListener.this);
            AdController.AdShakeListener.j(AdController.AdShakeListener.this);
          }
        };
      }
    }
    
    public void destroySensor()
    {
      if (this.H)
      {
        j();
        this.E.removeCallbacks(this.A);
      }
    }
    
    public void enableShakeDetection()
    {
      this.H = true;
      AdController.I(AdController.this).registerListener(this, AdController.I(AdController.this).getDefaultSensor(1), 1);
      AdLog.i("LBAdController", AdWakeLock.k("SzAyE2EdE|T2DwTwCfI}N2E|ApLwD"));
    }
    
    public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
    
    public void onSensorChanged(SensorEvent paramSensorEvent)
    {
      float f1 = paramSensorEvent.values[0];
      float f2 = paramSensorEvent.values[1];
      float f3 = paramSensorEvent.values[2];
      AdController.L(AdController.this, AdController.k(AdController.this));
      AdController.j(AdController.this, FloatMath.sqrt(f1 * f1 + f2 * f2 + f3 * f3));
      f1 = AdController.k(AdController.this);
      f2 = AdController.c(AdController.this);
      AdController.k(AdController.this, f1 - f2 + AdController.i(AdController.this) * 0.9F);
      if (AdController.i(AdController.this) > 2.5F)
      {
        long l1 = System.currentTimeMillis();
        if (this.i == 0L)
        {
          this.i = l1;
          this.c = l1;
        }
        if (l1 - this.c < 300L)
        {
          this.c = l1;
          this.l += 1;
          if ((this.l >= this.b) && (l1 - this.i < 1500L))
          {
            AdLog.i("LBAdController", AdWakeLock.k("VsL{D2SzAyE"));
            k();
            j();
            if (this.D)
            {
              if ((AdController.J(AdController.this) != null) && (AdController.J(AdController.this).isPlaying()))
              {
                AdController.J(AdController.this).stop();
                AdController.k(AdController.this, null);
                if (AdController.a(AdController.this) != null) {
                  AdController.a(AdController.this).onAdFinished();
                }
                AdController.G(AdController.this).setStreamVolume(3, AdController.F(AdController.this), 8);
              }
              this.E.removeCallbacks(this.A);
              L();
            }
          }
        }
        return;
      }
      k();
    }
    
    public void setShakeTime(int paramInt)
    {
      this.b = paramInt;
    }
    
    public void setValidTimes(int paramInt)
    {
      this.m = paramInt;
    }
    
    public void setupAudioAdHandler()
    {
      this.E.postDelayed(this.A, this.m);
    }
  }
  
  private class ContextList
    extends AsyncTask<Void, Void, String>
  {
    public ContextList(Context paramContext)
    {
      this.j = paramContext;
      this.D = this.j.getSharedPreferences(AdDefines.k("rRGFGRGNAE"), 0);
      this.B = this.D.edit();
    }
    
    protected String k(Void... paramVarArgs)
    {
      AdLog.i("LBAdController", AdEncryption.j("\030\0046\0058K+\004\030+\n-\037*,\0221\b\013\n,\000\0370K8\0161\016-\n+\016\b0\005+\016'\037,"));
      paramVarArgs = new StringBuilder();
      AdLog.d("LBAdController", System.currentTimeMillis());
      Object localObject1 = this.j.getPackageManager().getInstalledPackages(128);
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
        localObject1 = (ActivityManager)this.j.getSystemService(AdEncryption.j("\n<\0376\0356\037&"));
        if (this.j.checkCallingOrSelfPermission(AdDefines.k("CNFRMIF\016REPMKSQIMN\fggt}tcsis")) != 0) {
          break label644;
        }
        localObject1 = ((ActivityManager)localObject1).getRunningTasks(Integer.MAX_VALUE).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ActivityManager.RunningTaskInfo)((Iterator)localObject1).next();
          try
          {
            paramVarArgs.append(URLEncoder.encode(new StringBuilder().insert(0, AdDefines.k("VIVLG\035")).append(this.j.getPackageManager().getApplicationInfo(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName(), 0).loadLabel(this.j.getPackageManager()).toString()).append(AdEncryption.j("y\033>\b4\n8\016b")).append(((ActivityManager.RunningTaskInfo)localObject2).baseActivity.getPackageName()).toString(), AdDefines.k("uvf\017\030")));
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
            localStringBuilder1.append(localException1.applicationInfo.loadLabel(this.j.getPackageManager()).toString());
            localStringBuilder1.append(localException1.applicationInfo.packageName);
            StringBuilder localStringBuilder2 = new StringBuilder().insert(0, AdEncryption.j("y\0021\030+\n3\007b"));
            if (Build.VERSION.SDK_INT < 9) {
              break label631;
            }
            l = localException1.firstInstallTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder2 = new StringBuilder().insert(0, AdDefines.k("\004URDCTG\035"));
            if (Build.VERSION.SDK_INT < 9) {
              break label636;
            }
            l = localException1.lastUpdateTime / 1000L;
            localStringBuilder1.append(l);
            localStringBuilder1.append(localException1.versionCode);
            localStringBuilder1.append(localException1.versionName);
            paramVarArgs.append(URLEncoder.encode(localStringBuilder1.toString(), AdDefines.k("uvf\017\030")));
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
    
    protected void k(String paramString)
    {
      this.B.putBoolean(AdDefines.k("qd}cmnveztqknrrmgpeqs"), false);
      AdUtils.apply(this.B);
      if ((paramString != null) && (!paramString.equals("")))
      {
        this.B.putString(AdEncryption.j("\f/\000(\020%\013.\007?\f"), paramString);
        this.B.putLong(AdDefines.k("sfaoltgxvs}urdctgvioe"), System.currentTimeMillis());
        AdUtils.apply(this.B);
      }
    }
    
    protected void onPreExecute()
    {
      this.B.putBoolean(AdEncryption.j("8\0334\034$\021?\0323\0138\000\"\021;\r$\0309\0328\f"), true);
      AdUtils.apply(this.B);
    }
  }
  
  public static class Dimensions
    extends AdController.ReflectedParcelable
  {
    public static final Parcelable.Creator<Dimensions> m = new Parcelable.Creator()
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
    public int D;
    public int H;
    public int j;
    
    public Dimensions()
    {
      this.H = -1;
      this.j = -1;
      this.B = -1;
      this.D = -1;
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
      this.g = paramContext;
      this.i = paramInt;
      this.E = paramCharSequence1;
      this.K = paramCharSequence2;
      this.l = paramCharSequence3;
      this.D = paramNotificationManager;
      this.G = paramPendingIntent;
      AdLog.i("LBAdController", AdJSInterface.k("\005_+^%\0206_bv'D!Xby/Q%Uc"));
    }
    
    protected Bitmap k(String... paramVarArgs)
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
    protected void k(Bitmap paramBitmap)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnull +465 -> 466
      //   4: ldc 81
      //   6: ldc -88
      //   8: invokestatic 112	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
      //   11: invokestatic 94	com/hvfpeytriwknslejtfv/AdLog:i	(Ljava/lang/String;Ljava/lang/String;)V
      //   14: new 106	java/lang/StringBuilder
      //   17: dup
      //   18: invokespecial 107	java/lang/StringBuilder:<init>	()V
      //   21: iconst_0
      //   22: aload_0
      //   23: getfield 67	com/hvfpeytriwknslejtfv/AdController$FetchImage:g	Landroid/content/Context;
      //   26: invokevirtual 173	android/content/Context:getPackageName	()Ljava/lang/String;
      //   29: invokevirtual 116	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   32: aload_0
      //   33: getfield 41	com/hvfpeytriwknslejtfv/AdController$FetchImage:B	Ljava/lang/String;
      //   36: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   39: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   42: invokestatic 179	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   45: astore 7
      //   47: aload 7
      //   49: aload_0
      //   50: getfield 49	com/hvfpeytriwknslejtfv/AdController$FetchImage:c	Ljava/lang/String;
      //   53: invokevirtual 183	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   56: aload 7
      //   58: invokevirtual 189	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   61: istore_2
      //   62: new 106	java/lang/StringBuilder
      //   65: dup
      //   66: invokespecial 107	java/lang/StringBuilder:<init>	()V
      //   69: iconst_0
      //   70: aload_0
      //   71: getfield 67	com/hvfpeytriwknslejtfv/AdController$FetchImage:g	Landroid/content/Context;
      //   74: invokevirtual 173	android/content/Context:getPackageName	()Ljava/lang/String;
      //   77: invokevirtual 116	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   80: aload_0
      //   81: getfield 45	com/hvfpeytriwknslejtfv/AdController$FetchImage:m	Ljava/lang/String;
      //   84: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   87: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   90: invokestatic 179	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   93: astore 7
      //   95: aload 7
      //   97: aload_0
      //   98: getfield 53	com/hvfpeytriwknslejtfv/AdController$FetchImage:j	Ljava/lang/String;
      //   101: invokevirtual 183	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   104: aload 7
      //   106: invokevirtual 189	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   109: istore_3
      //   110: aload 7
      //   112: aload_0
      //   113: getfield 57	com/hvfpeytriwknslejtfv/AdController$FetchImage:H	Ljava/lang/String;
      //   116: invokevirtual 183	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   119: aload 7
      //   121: invokevirtual 189	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   124: istore 4
      //   126: aload 7
      //   128: aload_0
      //   129: getfield 61	com/hvfpeytriwknslejtfv/AdController$FetchImage:J	Ljava/lang/String;
      //   132: invokevirtual 183	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   135: aload 7
      //   137: invokevirtual 189	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   140: istore 5
      //   142: aload 7
      //   144: aload_0
      //   145: getfield 65	com/hvfpeytriwknslejtfv/AdController$FetchImage:A	Ljava/lang/String;
      //   148: invokevirtual 183	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
      //   151: aload 7
      //   153: invokevirtual 189	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
      //   156: istore 6
      //   158: iload 5
      //   160: iconst_m1
      //   161: if_icmpeq +175 -> 336
      //   164: iload 6
      //   166: iconst_m1
      //   167: if_icmpeq +169 -> 336
      //   170: new 191	android/widget/RemoteViews
      //   173: dup
      //   174: aload_0
      //   175: getfield 67	com/hvfpeytriwknslejtfv/AdController$FetchImage:g	Landroid/content/Context;
      //   178: invokevirtual 173	android/content/Context:getPackageName	()Ljava/lang/String;
      //   181: iload_2
      //   182: invokespecial 194	android/widget/RemoteViews:<init>	(Ljava/lang/String;I)V
      //   185: astore 7
      //   187: aload 7
      //   189: iload_3
      //   190: ldc -60
      //   192: invokestatic 89	com/hvfpeytriwknslejtfv/AdJSInterface:k	(Ljava/lang/String;)Ljava/lang/String;
      //   195: aload_0
      //   196: getfield 34	com/hvfpeytriwknslejtfv/AdController$FetchImage:b	Lcom/hvfpeytriwknslejtfv/AdController;
      //   199: invokestatic 199	com/hvfpeytriwknslejtfv/AdController:E	(Lcom/hvfpeytriwknslejtfv/AdController;)Lorg/json/JSONObject;
      //   202: ldc -55
      //   204: invokestatic 112	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
      //   207: invokevirtual 206	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   210: invokestatic 212	android/graphics/Color:parseColor	(Ljava/lang/String;)I
      //   213: invokevirtual 216	android/widget/RemoteViews:setInt	(ILjava/lang/String;I)V
      //   216: aload 7
      //   218: iload 5
      //   220: aload_1
      //   221: invokevirtual 220	android/widget/RemoteViews:setImageViewBitmap	(ILandroid/graphics/Bitmap;)V
      //   224: aload 7
      //   226: iload 6
      //   228: aload_0
      //   229: getfield 34	com/hvfpeytriwknslejtfv/AdController$FetchImage:b	Lcom/hvfpeytriwknslejtfv/AdController;
      //   232: invokestatic 199	com/hvfpeytriwknslejtfv/AdController:E	(Lcom/hvfpeytriwknslejtfv/AdController;)Lorg/json/JSONObject;
      //   235: ldc -34
      //   237: invokestatic 89	com/hvfpeytriwknslejtfv/AdJSInterface:k	(Ljava/lang/String;)Ljava/lang/String;
      //   240: invokevirtual 206	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   243: invokevirtual 226	android/widget/RemoteViews:setTextViewText	(ILjava/lang/CharSequence;)V
      //   246: aload 7
      //   248: iload 6
      //   250: aload_0
      //   251: getfield 34	com/hvfpeytriwknslejtfv/AdController$FetchImage:b	Lcom/hvfpeytriwknslejtfv/AdController;
      //   254: invokestatic 199	com/hvfpeytriwknslejtfv/AdController:E	(Lcom/hvfpeytriwknslejtfv/AdController;)Lorg/json/JSONObject;
      //   257: ldc -28
      //   259: invokestatic 112	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
      //   262: invokevirtual 206	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   265: invokestatic 212	android/graphics/Color:parseColor	(Ljava/lang/String;)I
      //   268: invokevirtual 232	android/widget/RemoteViews:setTextColor	(II)V
      //   271: aload 7
      //   273: iload 4
      //   275: iconst_4
      //   276: invokevirtual 235	android/widget/RemoteViews:setViewVisibility	(II)V
      //   279: ldc 81
      //   281: ldc -19
      //   283: invokestatic 89	com/hvfpeytriwknslejtfv/AdJSInterface:k	(Ljava/lang/String;)Ljava/lang/String;
      //   286: invokestatic 240	com/hvfpeytriwknslejtfv/AdLog:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   289: aload_0
      //   290: getfield 69	com/hvfpeytriwknslejtfv/AdController$FetchImage:i	I
      //   293: iconst_m1
      //   294: if_icmpeq +217 -> 511
      //   297: aload_0
      //   298: getfield 34	com/hvfpeytriwknslejtfv/AdController$FetchImage:b	Lcom/hvfpeytriwknslejtfv/AdController;
      //   301: aload_0
      //   302: getfield 67	com/hvfpeytriwknslejtfv/AdController$FetchImage:g	Landroid/content/Context;
      //   305: aload_0
      //   306: getfield 69	com/hvfpeytriwknslejtfv/AdController$FetchImage:i	I
      //   309: aload_0
      //   310: getfield 71	com/hvfpeytriwknslejtfv/AdController$FetchImage:E	Ljava/lang/CharSequence;
      //   313: aload_0
      //   314: getfield 73	com/hvfpeytriwknslejtfv/AdController$FetchImage:K	Ljava/lang/CharSequence;
      //   317: aload_0
      //   318: getfield 75	com/hvfpeytriwknslejtfv/AdController$FetchImage:l	Ljava/lang/CharSequence;
      //   321: aload_0
      //   322: getfield 77	com/hvfpeytriwknslejtfv/AdController$FetchImage:D	Landroid/app/NotificationManager;
      //   325: aload_0
      //   326: getfield 79	com/hvfpeytriwknslejtfv/AdController$FetchImage:G	Landroid/app/PendingIntent;
      //   329: aload 7
      //   331: invokestatic 243	com/hvfpeytriwknslejtfv/AdController:k	(Lcom/hvfpeytriwknslejtfv/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   334: pop
      //   335: return
      //   336: aload_0
      //   337: getfield 69	com/hvfpeytriwknslejtfv/AdController$FetchImage:i	I
      //   340: iconst_m1
      //   341: if_icmpeq +170 -> 511
      //   344: aload_0
      //   345: getfield 34	com/hvfpeytriwknslejtfv/AdController$FetchImage:b	Lcom/hvfpeytriwknslejtfv/AdController;
      //   348: aload_0
      //   349: getfield 67	com/hvfpeytriwknslejtfv/AdController$FetchImage:g	Landroid/content/Context;
      //   352: aload_0
      //   353: getfield 69	com/hvfpeytriwknslejtfv/AdController$FetchImage:i	I
      //   356: aload_0
      //   357: getfield 71	com/hvfpeytriwknslejtfv/AdController$FetchImage:E	Ljava/lang/CharSequence;
      //   360: aload_0
      //   361: getfield 73	com/hvfpeytriwknslejtfv/AdController$FetchImage:K	Ljava/lang/CharSequence;
      //   364: aload_0
      //   365: getfield 75	com/hvfpeytriwknslejtfv/AdController$FetchImage:l	Ljava/lang/CharSequence;
      //   368: aload_0
      //   369: getfield 77	com/hvfpeytriwknslejtfv/AdController$FetchImage:D	Landroid/app/NotificationManager;
      //   372: aload_0
      //   373: getfield 79	com/hvfpeytriwknslejtfv/AdController$FetchImage:G	Landroid/app/PendingIntent;
      //   376: aconst_null
      //   377: invokestatic 243	com/hvfpeytriwknslejtfv/AdController:k	(Lcom/hvfpeytriwknslejtfv/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   380: pop
      //   381: return
      //   382: astore_1
      //   383: ldc 81
      //   385: new 106	java/lang/StringBuilder
      //   388: dup
      //   389: invokespecial 107	java/lang/StringBuilder:<init>	()V
      //   392: iconst_0
      //   393: ldc -11
      //   395: invokestatic 112	com/hvfpeytriwknslejtfv/AdWakeLock:k	(Ljava/lang/String;)Ljava/lang/String;
      //   398: invokevirtual 116	java/lang/StringBuilder:insert	(ILjava/lang/String;)Ljava/lang/StringBuilder;
      //   401: aload_1
      //   402: invokevirtual 158	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   405: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   408: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   411: invokestatic 161	com/hvfpeytriwknslejtfv/AdLog:e	(Ljava/lang/String;Ljava/lang/String;)V
      //   414: ldc 81
      //   416: aload_1
      //   417: invokestatic 165	com/hvfpeytriwknslejtfv/AdLog:printStackTrace	(Ljava/lang/String;Ljava/lang/Exception;)V
      //   420: aload_0
      //   421: getfield 69	com/hvfpeytriwknslejtfv/AdController$FetchImage:i	I
      //   424: iconst_m1
      //   425: if_icmpeq +86 -> 511
      //   428: aload_0
      //   429: getfield 34	com/hvfpeytriwknslejtfv/AdController$FetchImage:b	Lcom/hvfpeytriwknslejtfv/AdController;
      //   432: aload_0
      //   433: getfield 67	com/hvfpeytriwknslejtfv/AdController$FetchImage:g	Landroid/content/Context;
      //   436: aload_0
      //   437: getfield 69	com/hvfpeytriwknslejtfv/AdController$FetchImage:i	I
      //   440: aload_0
      //   441: getfield 71	com/hvfpeytriwknslejtfv/AdController$FetchImage:E	Ljava/lang/CharSequence;
      //   444: aload_0
      //   445: getfield 73	com/hvfpeytriwknslejtfv/AdController$FetchImage:K	Ljava/lang/CharSequence;
      //   448: aload_0
      //   449: getfield 75	com/hvfpeytriwknslejtfv/AdController$FetchImage:l	Ljava/lang/CharSequence;
      //   452: aload_0
      //   453: getfield 77	com/hvfpeytriwknslejtfv/AdController$FetchImage:D	Landroid/app/NotificationManager;
      //   456: aload_0
      //   457: getfield 79	com/hvfpeytriwknslejtfv/AdController$FetchImage:G	Landroid/app/PendingIntent;
      //   460: aconst_null
      //   461: invokestatic 243	com/hvfpeytriwknslejtfv/AdController:k	(Lcom/hvfpeytriwknslejtfv/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
      //   464: pop
      //   465: return
      //   466: aload_0
      //   467: getfield 69	com/hvfpeytriwknslejtfv/AdController$FetchImage:i	I
      //   470: iconst_m1
      //   471: if_icmpeq +40 -> 511
      //   474: aload_0
      //   475: getfield 34	com/hvfpeytriwknslejtfv/AdController$FetchImage:b	Lcom/hvfpeytriwknslejtfv/AdController;
      //   478: aload_0
      //   479: getfield 67	com/hvfpeytriwknslejtfv/AdController$FetchImage:g	Landroid/content/Context;
      //   482: aload_0
      //   483: getfield 69	com/hvfpeytriwknslejtfv/AdController$FetchImage:i	I
      //   486: aload_0
      //   487: getfield 71	com/hvfpeytriwknslejtfv/AdController$FetchImage:E	Ljava/lang/CharSequence;
      //   490: aload_0
      //   491: getfield 73	com/hvfpeytriwknslejtfv/AdController$FetchImage:K	Ljava/lang/CharSequence;
      //   494: aload_0
      //   495: getfield 75	com/hvfpeytriwknslejtfv/AdController$FetchImage:l	Ljava/lang/CharSequence;
      //   498: aload_0
      //   499: getfield 77	com/hvfpeytriwknslejtfv/AdController$FetchImage:D	Landroid/app/NotificationManager;
      //   502: aload_0
      //   503: getfield 79	com/hvfpeytriwknslejtfv/AdController$FetchImage:G	Landroid/app/PendingIntent;
      //   506: aconst_null
      //   507: invokestatic 243	com/hvfpeytriwknslejtfv/AdController:k	(Lcom/hvfpeytriwknslejtfv/AdController;Landroid/content/Context;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/NotificationManager;Landroid/app/PendingIntent;Landroid/widget/RemoteViews;)Z
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
      //   61	121	2	k	int
      //   109	81	3	n	int
      //   124	150	4	i1	int
      //   140	79	5	i2	int
      //   156	93	6	i3	int
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
    public static final Parcelable.Creator<PlayerProperties> D = new Parcelable.Creator()
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
    public boolean B;
    public boolean G;
    public String H;
    public boolean g;
    public boolean j;
    public String l;
    public boolean m;
    
    public PlayerProperties()
    {
      this.m = true;
      this.j = true;
      this.g = false;
      this.G = false;
      this.l = "normal";
      this.H = "normal";
      this.B = false;
    }
    
    public PlayerProperties(Parcel paramParcel)
    {
      super();
    }
    
    public boolean doLoop()
    {
      return this.G;
    }
    
    public boolean doMute()
    {
      return this.g;
    }
    
    public boolean exitOnComplete()
    {
      return this.l.equalsIgnoreCase("exit");
    }
    
    public boolean isAutoPlay()
    {
      return this.j == true;
    }
    
    public boolean isFullScreen()
    {
      return this.H.equalsIgnoreCase("fullscreen");
    }
    
    public void muteAudio()
    {
      this.g = true;
    }
    
    public void setProperties(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String paramString1, String paramString2)
    {
      this.j = paramBoolean2;
      this.m = paramBoolean3;
      this.G = paramBoolean5;
      this.g = paramBoolean1;
      this.H = paramString1;
      this.l = paramString2;
      this.B = paramBoolean4;
    }
    
    public void setStopStyle(String paramString)
    {
      this.l = paramString;
    }
    
    public boolean showControl()
    {
      return this.m;
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
    public boolean D;
    public int j;
    public float m;
    
    public Properties()
    {
      this.D = false;
      this.j = 0;
      this.m = 0.0F;
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
            if (((String)localObject).equals("class com.hvfpeytriwknslejtfv.AdNavigationStringEnum")) {
              localField.set(this, AdNavigationStringEnum.fromString(paramParcel.readString()));
            } else if (((String)localObject).equals("class com.hvfpeytriwknslejtfv.AdTransitionStringEnum")) {
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
            if (((String)localObject2).equals("class com.hvfpeytriwknslejtfv.AdNavigationStringEnum")) {
              paramParcel.writeString(((AdNavigationStringEnum)((Field)localObject1).get(this)).getText());
            } else if (((String)localObject2).equals("class com.hvfpeytriwknslejtfv.AdTransitionStringEnum")) {
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
