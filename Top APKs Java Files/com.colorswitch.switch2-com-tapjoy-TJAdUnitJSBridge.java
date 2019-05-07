package com.tapjoy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.moat.analytics.mobile.tjy.MoatAdEventType;
import com.moat.analytics.mobile.tjy.MoatFactory;
import com.moat.analytics.mobile.tjy.ReactiveVideoTracker;
import com.moat.analytics.mobile.tjy.ReactiveVideoTrackerPlugin;
import com.tapjoy.internal.ct;
import com.tapjoy.mraid.view.MraidView;
import com.tapjoy.mraid.view.MraidView.PLACEMENT_TYPE;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class TJAdUnitJSBridge
  implements TJWebViewJSInterfaceListener
{
  final ConcurrentLinkedQueue a = new ConcurrentLinkedQueue();
  public boolean allowRedirect = true;
  private TJWebViewJSInterface b;
  private TJAdUnitJSBridge c;
  public boolean closeRequested = false;
  public boolean customClose = false;
  private Context d;
  public boolean didLaunchOtherActivity = false;
  private TJAdUnitActivity e;
  private TJAdUnit f;
  private WebView g;
  private TJSplitWebView h;
  private ProgressDialog i;
  private View j = null;
  private boolean k;
  private ReactiveVideoTracker l;
  private HashMap m;
  private Handler n;
  public String otherActivityCallbackID = null;
  public String splitWebViewCallbackID = null;
  
  public TJAdUnitJSBridge(Context paramContext, WebView paramWebView)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "creating AdUnit/JS Bridge");
    this.d = paramContext;
    this.g = paramWebView;
    this.c = this;
    if (this.g == null)
    {
      TapjoyLog.e("TJAdUnitJSBridge", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Cannot create AdUnitJSBridge -- webview is NULL"));
      return;
    }
    this.b = new TJWebViewJSInterface(this.g, this);
    this.g.addJavascriptInterface(this.b, "AndroidJavascriptInterface");
    setEnabled(true);
  }
  
  public TJAdUnitJSBridge(Context paramContext, TJAdUnit paramTJAdUnit)
  {
    this(paramContext, paramTJAdUnit.getWebView());
    this.f = paramTJAdUnit;
  }
  
  /* Error */
  public void alert(JSONObject paramJSONObject, final String paramString)
  {
    // Byte code:
    //   0: new 187	java/lang/StringBuilder
    //   3: dup
    //   4: ldc -67
    //   6: invokespecial 192	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   9: astore 5
    //   11: aload 5
    //   13: aload_1
    //   14: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   17: pop
    //   18: ldc 107
    //   20: aload 5
    //   22: invokevirtual 200	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokestatic 202	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   28: aload_1
    //   29: ldc -52
    //   31: invokevirtual 210	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   34: astore 5
    //   36: aload_1
    //   37: ldc -44
    //   39: invokevirtual 210	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   42: astore 6
    //   44: aload_1
    //   45: ldc -42
    //   47: invokevirtual 218	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   50: astore 7
    //   52: aload 6
    //   54: astore_1
    //   55: aload 7
    //   57: astore 6
    //   59: goto +57 -> 116
    //   62: astore 7
    //   64: aload 6
    //   66: astore_1
    //   67: aload 7
    //   69: astore 6
    //   71: goto +22 -> 93
    //   74: astore_1
    //   75: goto +8 -> 83
    //   78: astore_1
    //   79: ldc -36
    //   81: astore 5
    //   83: ldc -36
    //   85: astore 7
    //   87: aload_1
    //   88: astore 6
    //   90: aload 7
    //   92: astore_1
    //   93: aload_0
    //   94: aload_2
    //   95: iconst_1
    //   96: anewarray 4	java/lang/Object
    //   99: dup
    //   100: iconst_0
    //   101: getstatic 226	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   104: aastore
    //   105: invokevirtual 230	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   108: aload 6
    //   110: invokevirtual 233	java/lang/Exception:printStackTrace	()V
    //   113: aconst_null
    //   114: astore 6
    //   116: aload_0
    //   117: getfield 178	com/tapjoy/TJAdUnitJSBridge:e	Lcom/tapjoy/TJAdUnitActivity;
    //   120: astore 7
    //   122: aload 7
    //   124: ifnull +189 -> 313
    //   127: new 235	android/app/AlertDialog$Builder
    //   130: dup
    //   131: aload 7
    //   133: invokespecial 238	android/app/AlertDialog$Builder:<init>	(Landroid/content/Context;)V
    //   136: aload 5
    //   138: invokevirtual 242	android/app/AlertDialog$Builder:setTitle	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   141: aload_1
    //   142: invokevirtual 245	android/app/AlertDialog$Builder:setMessage	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   145: invokevirtual 249	android/app/AlertDialog$Builder:create	()Landroid/app/AlertDialog;
    //   148: astore_1
    //   149: aload 6
    //   151: ifnull +146 -> 297
    //   154: aload 6
    //   156: invokevirtual 255	org/json/JSONArray:length	()I
    //   159: ifne +6 -> 165
    //   162: goto +135 -> 297
    //   165: new 257	java/util/ArrayList
    //   168: dup
    //   169: invokespecial 258	java/util/ArrayList:<init>	()V
    //   172: astore 5
    //   174: iconst_0
    //   175: istore 4
    //   177: iload 4
    //   179: aload 6
    //   181: invokevirtual 255	org/json/JSONArray:length	()I
    //   184: if_icmpge +98 -> 282
    //   187: iload 4
    //   189: tableswitch	default:+23->212, 0:+34->223, 1:+28->217
    //   212: iconst_m1
    //   213: istore_3
    //   214: goto +12 -> 226
    //   217: bipush -3
    //   219: istore_3
    //   220: goto +6 -> 226
    //   223: bipush -2
    //   225: istore_3
    //   226: aload 5
    //   228: aload 6
    //   230: iload 4
    //   232: invokevirtual 261	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   235: invokevirtual 265	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   238: pop
    //   239: goto +10 -> 249
    //   242: astore 7
    //   244: aload 7
    //   246: invokevirtual 233	java/lang/Exception:printStackTrace	()V
    //   249: aload_1
    //   250: iload_3
    //   251: aload 5
    //   253: iload 4
    //   255: invokevirtual 269	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   258: checkcast 271	java/lang/CharSequence
    //   261: new 8	com/tapjoy/TJAdUnitJSBridge$1
    //   264: dup
    //   265: aload_0
    //   266: aload_2
    //   267: invokespecial 274	com/tapjoy/TJAdUnitJSBridge$1:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Ljava/lang/String;)V
    //   270: invokevirtual 280	android/app/AlertDialog:setButton	(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V
    //   273: iload 4
    //   275: iconst_1
    //   276: iadd
    //   277: istore 4
    //   279: goto -102 -> 177
    //   282: aload_1
    //   283: iconst_0
    //   284: invokevirtual 283	android/app/AlertDialog:setCancelable	(Z)V
    //   287: aload_1
    //   288: iconst_0
    //   289: invokevirtual 286	android/app/AlertDialog:setCanceledOnTouchOutside	(Z)V
    //   292: aload_1
    //   293: invokevirtual 289	android/app/AlertDialog:show	()V
    //   296: return
    //   297: aload_0
    //   298: aload_2
    //   299: iconst_1
    //   300: anewarray 4	java/lang/Object
    //   303: dup
    //   304: iconst_0
    //   305: getstatic 226	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   308: aastore
    //   309: invokevirtual 230	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   312: return
    //   313: ldc 107
    //   315: ldc_w 291
    //   318: invokestatic 202	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   321: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	322	0	this	TJAdUnitJSBridge
    //   0	322	1	paramJSONObject	JSONObject
    //   0	322	2	paramString	String
    //   213	38	3	i1	int
    //   175	103	4	i2	int
    //   9	243	5	localObject1	Object
    //   42	187	6	localObject2	Object
    //   50	6	7	localJSONArray	JSONArray
    //   62	6	7	localException1	Exception
    //   85	47	7	localObject3	Object
    //   242	3	7	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   44	52	62	java/lang/Exception
    //   36	44	74	java/lang/Exception
    //   28	36	78	java/lang/Exception
    //   226	239	242	java/lang/Exception
  }
  
  public void attachVolumeListener(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      boolean bool = paramJSONObject.getBoolean("attach");
      int i1 = paramJSONObject.optInt("interval", 500);
      if (i1 > 0)
      {
        this.f.attachVolumeListener(bool, i1);
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
        return;
      }
      paramJSONObject = new StringBuilder("Invalid `interval` value passed to attachVolumeListener(): interval=");
      paramJSONObject.append(i1);
      TapjoyLog.d("TJAdUnitJSBridge", paramJSONObject.toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      StringBuilder localStringBuilder = new StringBuilder("attachVolumeListener exception ");
      localStringBuilder.append(paramJSONObject.toString());
      TapjoyLog.d("TJAdUnitJSBridge", localStringBuilder.toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void cacheAsset(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = "";
    for (;;)
    {
      try
      {
        str2 = paramJSONObject.getString("url");
      }
      catch (Exception paramJSONObject)
      {
        String str2;
        String str1;
        continue;
      }
      try
      {
        str1 = paramJSONObject.getString("offerId");
        localObject = str1;
      }
      catch (Exception localException)
      {
        continue;
      }
      try
      {
        paramJSONObject = Long.valueOf(paramJSONObject.getLong("timeToLive"));
      }
      catch (Exception paramJSONObject) {}
    }
    paramJSONObject = Long.valueOf(0L);
    if (TapjoyCache.getInstance() != null)
    {
      invokeJSCallback(paramString, new Object[] { TapjoyCache.getInstance().cacheAssetFromURL(str2, (String)localObject, paramJSONObject.longValue()) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
    return;
    TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void cachePathForURL(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("url");
      if (TapjoyCache.getInstance() != null)
      {
        invokeJSCallback(paramString, new Object[] { TapjoyCache.getInstance().getPathOfCachedURL(paramJSONObject) });
        return;
      }
      invokeJSCallback(paramString, new Object[] { "" });
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;) {}
    }
    invokeJSCallback(paramString, new Object[] { "" });
  }
  
  public void checkAppInstalled(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("bundle");
    }
    catch (Exception paramJSONObject)
    {
      paramJSONObject.printStackTrace();
      paramJSONObject = "";
    }
    if ((paramJSONObject != null) && (paramJSONObject.length() > 0))
    {
      Iterator localIterator = this.d.getPackageManager().getInstalledApplications(0).iterator();
      while (localIterator.hasNext()) {
        if (((ApplicationInfo)localIterator.next()).packageName.equals(paramJSONObject))
        {
          invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
          return;
        }
      }
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void clearCache(JSONObject paramJSONObject, String paramString)
  {
    if (TapjoyCache.getInstance() != null)
    {
      TapjoyCache.getInstance().clearTapjoyCache();
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void clearLoggingLevel(JSONObject paramJSONObject, String paramString)
  {
    TapjoyAppSettings.getInstance().clearLoggingLevel();
  }
  
  public void clearVideo(JSONObject paramJSONObject, final String paramString)
  {
    if (this.f != null) {
      this.f.clearVideo(new AdUnitAsyncTaskListner()
      {
        public final void onComplete(boolean paramAnonymousBoolean)
        {
          TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramAnonymousBoolean) });
        }
      });
    }
  }
  
  public void closeRequested(Boolean paramBoolean)
  {
    this.closeRequested = true;
    HashMap localHashMap = new HashMap();
    localHashMap.put("forceClose", paramBoolean);
    invokeJSAdunitMethod("closeRequested", localHashMap);
  }
  
  public void contentReady(JSONObject paramJSONObject, String paramString)
  {
    if (this.f != null)
    {
      this.f.fireContentReady();
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void destroy() {}
  
  public void dismiss(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject = this.e;
    if (paramJSONObject != null)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      paramJSONObject.finish();
      return;
    }
    TapjoyLog.d("TJAdUnitJSBridge", "Cannot dismiss -- TJAdUnitActivity is null");
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void dismissSplitView(JSONObject paramJSONObject, final String paramString)
  {
    TapjoyUtil.runOnMainThread(new Runnable()
    {
      public final void run()
      {
        if (TJAdUnitJSBridge.d(TJAdUnitJSBridge.this) != null)
        {
          if (paramString != null) {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
          }
          if (TJAdUnitJSBridge.this.splitWebViewCallbackID != null)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(TJAdUnitJSBridge.this.splitWebViewCallbackID, new Object[] { Boolean.TRUE });
            TJAdUnitJSBridge.this.splitWebViewCallbackID = null;
          }
          ((ViewGroup)TJAdUnitJSBridge.d(TJAdUnitJSBridge.this).getParent()).removeView(TJAdUnitJSBridge.d(TJAdUnitJSBridge.this));
          TJAdUnitJSBridge.a(TJAdUnitJSBridge.this, null);
          return;
        }
        if (paramString != null) {
          TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        }
      }
    });
  }
  
  public void display()
  {
    invokeJSAdunitMethod("display", new Object[0]);
  }
  
  public void displayRichMedia(final JSONObject paramJSONObject, String paramString)
  {
    try
    {
      bool = paramJSONObject.getBoolean("inline");
    }
    catch (Exception localException2)
    {
      boolean bool;
      Object localObject;
      for (;;) {}
    }
    bool = false;
    try
    {
      String str = paramJSONObject.getString("html");
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      localObject = null;
    }
    if (localObject == null)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    if (bool)
    {
      ((Activity)this.d).runOnUiThread(new Runnable()
      {
        public final void run()
        {
          try
          {
            String str = paramJSONObject.getString("html");
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            localObject1 = null;
          }
          if ((TJAdUnitJSBridge.a(TJAdUnitJSBridge.this) != null) && (TJAdUnitJSBridge.a(TJAdUnitJSBridge.this).getParent() != null)) {
            ((ViewGroup)TJAdUnitJSBridge.a(TJAdUnitJSBridge.this).getParent()).removeView(TJAdUnitJSBridge.a(TJAdUnitJSBridge.this));
          }
          Object localObject2 = new MraidView(TJAdUnitJSBridge.b(TJAdUnitJSBridge.this));
          TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).getSettings().setJavaScriptEnabled(true);
          ((MraidView)localObject2).setPlacementType(MraidView.PLACEMENT_TYPE.INLINE);
          ((MraidView)localObject2).setLayoutParams(new ViewGroup.LayoutParams(640, 100));
          ((MraidView)localObject2).setInitialScale(100);
          ((MraidView)localObject2).setBackgroundColor(0);
          ((MraidView)localObject2).loadDataWithBaseURL(null, (String)localObject1, "text/html", "utf-8", null);
          int i = ((WindowManager)((Activity)TJAdUnitJSBridge.b(TJAdUnitJSBridge.this)).getSystemService("window")).getDefaultDisplay().getWidth();
          TJAdUnitJSBridge.a(TJAdUnitJSBridge.this, TapjoyUtil.scaleDisplayAd((View)localObject2, i));
          Object localObject1 = (Activity)TJAdUnitJSBridge.b(TJAdUnitJSBridge.this);
          localObject2 = TJAdUnitJSBridge.a(TJAdUnitJSBridge.this);
          double d = i;
          Double.isNaN(d);
          ((Activity)localObject1).addContentView((View)localObject2, new ViewGroup.LayoutParams(i, (int)(d / 640.0D * 100.0D)));
        }
      });
      return;
    }
    paramJSONObject = new TJPlacementData(TapjoyConnectCore.getHostURL(), (String)localObject, paramString);
    paramString = this.e;
    if (paramString != null)
    {
      localObject = new Intent(paramString, TJAdUnitActivity.class);
      ((Intent)localObject).putExtra("placement_data", paramJSONObject);
      paramString.startActivityForResult((Intent)localObject, 327);
    }
  }
  
  public void displayStoreURL(JSONObject paramJSONObject, String paramString)
  {
    displayURL(paramJSONObject, paramString);
  }
  
  public void displayURL(final JSONObject paramJSONObject, final String paramString)
  {
    try
    {
      String str1 = paramJSONObject.optString("style");
      final String str2 = paramJSONObject.getString("url");
      final JSONObject localJSONObject = paramJSONObject.optJSONObject("splitViewLayout");
      paramJSONObject = paramJSONObject.optJSONArray("splitViewExitHosts");
      if ("split".equals(str1))
      {
        TapjoyUtil.runOnMainThread(new Runnable()
        {
          public final void run()
          {
            if (TJAdUnitJSBridge.c(TJAdUnitJSBridge.this) != null)
            {
              if (TJAdUnitJSBridge.d(TJAdUnitJSBridge.this) == null)
              {
                Object localObject = TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).getParent();
                if ((localObject instanceof ViewGroup))
                {
                  localObject = (ViewGroup)localObject;
                  TJAdUnitJSBridge.a(TJAdUnitJSBridge.this, new TJSplitWebView(TJAdUnitJSBridge.b(TJAdUnitJSBridge.this), localJSONObject, paramJSONObject, TJAdUnitJSBridge.this));
                  ((ViewGroup)localObject).addView(TJAdUnitJSBridge.d(TJAdUnitJSBridge.this), new RelativeLayout.LayoutParams(-1, -1));
                }
              }
              else
              {
                TJAdUnitJSBridge.d(TJAdUnitJSBridge.this).setExitHosts(paramJSONObject);
                TJAdUnitJSBridge.d(TJAdUnitJSBridge.this).applyLayoutOption(localJSONObject);
              }
              if (TJAdUnitJSBridge.d(TJAdUnitJSBridge.this) != null)
              {
                TJAdUnitJSBridge.this.splitWebViewCallbackID = paramString;
                TJAdUnitJSBridge.d(TJAdUnitJSBridge.this).loadUrl(str2);
                return;
              }
            }
            TJAdUnitJSBridge.a(TJAdUnitJSBridge.this, null);
            TJAdUnitJSBridge.this.splitWebViewCallbackID = null;
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
          }
        });
        return;
      }
      this.didLaunchOtherActivity = true;
      this.otherActivityCallbackID = paramString;
      paramJSONObject = new Intent("android.intent.action.VIEW", Uri.parse(str2));
      this.d.startActivity(paramJSONObject);
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void displayVideo(JSONObject paramJSONObject, final String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("url");
      if ((paramJSONObject.length() > 0) && (paramJSONObject != ""))
      {
        this.f.loadVideoUrl(paramJSONObject, new AdUnitAsyncTaskListner()
        {
          public final void onComplete(boolean paramAnonymousBoolean)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramAnonymousBoolean) });
          }
        });
        return;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void endUsageTrackingEvent(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      localObject = paramJSONObject.getString("name");
      if (((String)localObject).isEmpty())
      {
        TapjoyLog.d("TJAdUnitJSBridge", "Empty name for endUsageTrackingEvent");
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
        return;
      }
      if (this.f != null)
      {
        this.f.endAdContentTracking((String)localObject, paramJSONObject);
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
        return;
      }
    }
    catch (JSONException paramJSONObject)
    {
      Object localObject = new StringBuilder("Unable to endUsageTrackingEvent. Invalid parameters: ");
      ((StringBuilder)localObject).append(paramJSONObject);
      TapjoyLog.w("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
    }
  }
  
  public void flushBacklogMessageQueue()
  {
    for (;;)
    {
      Pair localPair = (Pair)this.a.poll();
      if (localPair == null) {
        break;
      }
      onDispatchMethod((String)localPair.first, (JSONObject)localPair.second);
    }
  }
  
  public void flushMessageQueue()
  {
    this.b.flushMessageQueue();
  }
  
  public void getCachedAssets(JSONObject paramJSONObject, String paramString)
  {
    if (TapjoyCache.getInstance() != null)
    {
      invokeJSCallback(paramString, new Object[] { TapjoyCache.getInstance().cachedAssetsToJSON() });
      return;
    }
    invokeJSCallback(paramString, new Object[] { "" });
  }
  
  public void getInstalledAppData(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject = this.d.getPackageManager();
    Object localObject1 = paramJSONObject.getInstalledApplications(0);
    JSONArray localJSONArray = new JSONArray();
    localObject1 = ((List)localObject1).iterator();
    for (;;)
    {
      Object localObject2;
      HashMap localHashMap;
      String str;
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
        if ((((ApplicationInfo)localObject2).flags & 0x1) != 1)
        {
          localHashMap = new HashMap();
          str = ((ApplicationInfo)localObject2).packageName;
          localHashMap.put("packageName", str);
          localHashMap.put("targetSdk", Integer.valueOf(((ApplicationInfo)localObject2).targetSdkVersion));
        }
      }
      else
      {
        try
        {
          localObject2 = paramJSONObject.getPackageInfo(str, 4096);
          localHashMap.put("installTime", new Date(((PackageInfo)localObject2).firstInstallTime));
          localHashMap.put("updateTime", new Date(((PackageInfo)localObject2).lastUpdateTime));
          localHashMap.put("versionName", ((PackageInfo)localObject2).versionName);
          localHashMap.put("versionCode", Integer.valueOf(((PackageInfo)localObject2).versionCode));
          localJSONArray.put(new JSONObject(localHashMap));
        }
        catch (Exception localException) {}
        invokeJSCallback(paramString, new Object[] { localJSONArray });
        return;
      }
    }
  }
  
  public void getSplitViewURL(JSONObject paramJSONObject, final String paramString)
  {
    TapjoyUtil.runOnMainThread(new Runnable()
    {
      public final void run()
      {
        if (TJAdUnitJSBridge.d(TJAdUnitJSBridge.this) != null)
        {
          TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { TJAdUnitJSBridge.d(TJAdUnitJSBridge.this).getLastUrl() });
          return;
        }
        TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { JSONObject.NULL });
      }
    });
  }
  
  public void getVolume(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject = getVolumeArgs();
    if (paramJSONObject != null)
    {
      invokeJSCallback(paramString, paramJSONObject);
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public HashMap getVolumeArgs()
  {
    if (this.f == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
      return null;
    }
    String str = this.f.getVolume();
    boolean bool = this.f.isMuted();
    Object localObject = new StringBuilder("getVolumeArgs: volume=");
    ((StringBuilder)localObject).append(str);
    ((StringBuilder)localObject).append("; isMuted=");
    ((StringBuilder)localObject).append(bool);
    TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
    localObject = new HashMap();
    ((HashMap)localObject).put("currentVolume", str);
    ((HashMap)localObject).put("isMuted", Boolean.valueOf(bool));
    return localObject;
  }
  
  public void hasSplitView(JSONObject paramJSONObject, final String paramString)
  {
    TapjoyUtil.runOnMainThread(new Runnable()
    {
      public final void run()
      {
        if (TJAdUnitJSBridge.d(TJAdUnitJSBridge.this) != null)
        {
          TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
          return;
        }
        TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      }
    });
  }
  
  public void initMoatVideoTracker(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = this.e;
    if (localObject == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "Error from initMoatVideoTracker -- TJAdUnitActivity is null");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    try
    {
      paramJSONObject = paramJSONObject.getString("partnerCode");
      this.l = ((ReactiveVideoTracker)MoatFactory.create((Activity)localObject).createCustomTracker(new ReactiveVideoTrackerPlugin(paramJSONObject)));
      if (this.m == null)
      {
        TapjoyLog.d("TJAdUnitJSBridge", "Initializing MOAT tracking events map");
        this.m = new HashMap();
        this.m.put("firstQuartile", MoatAdEventType.AD_EVT_FIRST_QUARTILE);
        this.m.put("midpoint", MoatAdEventType.AD_EVT_MID_POINT);
        this.m.put("thirdQuartile", MoatAdEventType.AD_EVT_THIRD_QUARTILE);
        this.m.put("complete", MoatAdEventType.AD_EVT_COMPLETE);
        this.m.put("paused", MoatAdEventType.AD_EVT_PAUSED);
        this.m.put("playing", MoatAdEventType.AD_EVT_PLAYING);
        this.m.put("start", MoatAdEventType.AD_EVT_START);
        this.m.put("stopped", MoatAdEventType.AD_EVT_STOPPED);
        this.m.put("skipped", MoatAdEventType.AD_EVT_SKIPPED);
        this.m.put("volumeChanged", MoatAdEventType.AD_EVT_VOLUME_CHANGE);
        this.m.put("enterFullScreen", MoatAdEventType.AD_EVT_ENTER_FULLSCREEN);
        this.m.put("exitFullScreen", MoatAdEventType.AD_EVT_EXIT_FULLSCREEN);
      }
      this.n = new Handler(Looper.getMainLooper());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      localObject = new StringBuilder("initMoatVideoTracker exception ");
      ((StringBuilder)localObject).append(paramJSONObject.toString());
      TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
    }
  }
  
  public void invokeJSAdunitMethod(String paramString, Map paramMap)
  {
    this.b.callback(paramMap, paramString, null);
  }
  
  public void invokeJSAdunitMethod(String paramString, Object... paramVarArgs)
  {
    paramVarArgs = new ArrayList(Arrays.asList(paramVarArgs));
    this.b.callback(paramVarArgs, paramString, null);
  }
  
  public void invokeJSCallback(String paramString, Map paramMap)
  {
    this.b.callback(paramMap, "", paramString);
  }
  
  public void invokeJSCallback(String paramString, Object... paramVarArgs)
  {
    if (ct.c(paramString))
    {
      TapjoyLog.d("TJAdUnitJSBridge", "invokeJSCallback -- no callbackID provided");
      return;
    }
    paramVarArgs = new ArrayList(Arrays.asList(paramVarArgs));
    this.b.callback(paramVarArgs, "", paramString);
  }
  
  public void log(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder("Logging message=");
      localStringBuilder.append(paramJSONObject.getString("message"));
      TapjoyLog.d("TJAdUnitJSBridge", localStringBuilder.toString());
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public void nativeEval(final JSONObject paramJSONObject, final String paramString)
  {
    TapjoyUtil.runOnMainThread(new Runnable()
    {
      public final void run()
      {
        try
        {
          if (Build.VERSION.SDK_INT >= 19)
          {
            TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).evaluateJavascript(paramJSONObject.getString("command"), null);
          }
          else
          {
            WebView localWebView = TJAdUnitJSBridge.c(TJAdUnitJSBridge.this);
            StringBuilder localStringBuilder = new StringBuilder("javascript:");
            localStringBuilder.append(paramJSONObject.getString("command"));
            localWebView.loadUrl(localStringBuilder.toString());
          }
          TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
        TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      }
    });
  }
  
  public void onDispatchMethod(String paramString, JSONObject paramJSONObject)
  {
    if (this.k)
    {
      try
      {
        localObject = paramJSONObject.optString("callbackId", null);
        try
        {
          paramJSONObject = paramJSONObject.getJSONObject("data");
          paramString = TJAdUnitJSBridge.class.getMethod(paramString, new Class[] { JSONObject.class, String.class });
          StringBuilder localStringBuilder = new StringBuilder("Dispatching method: ");
          localStringBuilder.append(paramString);
          localStringBuilder.append(" with data=");
          localStringBuilder.append(paramJSONObject);
          localStringBuilder.append("; callbackID=");
          localStringBuilder.append((String)localObject);
          TapjoyLog.d("TJAdUnitJSBridge", localStringBuilder.toString());
          paramString.invoke(this.c, new Object[] { paramJSONObject, localObject });
          return;
        }
        catch (Exception paramJSONObject)
        {
          paramString = (String)localObject;
        }
        paramJSONObject.printStackTrace();
      }
      catch (Exception paramJSONObject)
      {
        paramString = null;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    Object localObject = new StringBuilder("Bridge currently disabled. Adding ");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(" to message queue");
    TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
    this.a.add(new Pair(paramString, paramJSONObject));
  }
  
  public void onVideoCompletion()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoComplete");
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoError(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoError");
    localHashMap.put("error", paramString);
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoInfo(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoInfo");
    localHashMap.put("info", paramString);
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoPaused(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoPause");
    localHashMap.put("currentTime", Integer.valueOf(paramInt));
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoProgress(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoProgress");
    localHashMap.put("currentTime", Integer.valueOf(paramInt));
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoReady(int paramInt1, int paramInt2, int paramInt3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoReady");
    localHashMap.put("videoDuration", Integer.valueOf(paramInt1));
    localHashMap.put("videoWidth", Integer.valueOf(paramInt2));
    localHashMap.put("videoHeight", Integer.valueOf(paramInt3));
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoStarted(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoStart");
    localHashMap.put("currentTime", Integer.valueOf(paramInt));
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVolumeChanged()
  {
    invokeJSAdunitMethod("volumeChanged", getVolumeArgs());
  }
  
  public void openApp(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("bundle");
      paramJSONObject = this.d.getPackageManager().getLaunchIntentForPackage(paramJSONObject);
      this.d.startActivity(paramJSONObject);
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void pauseVideo(JSONObject paramJSONObject, String paramString)
  {
    if (this.f != null) {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(this.f.pauseVideo()) });
    }
  }
  
  public void playVideo(JSONObject paramJSONObject, String paramString)
  {
    if (this.f != null) {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(this.f.playVideo()) });
    }
  }
  
  /* Error */
  public void present(JSONObject paramJSONObject, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: invokestatic 311	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   4: astore_3
    //   5: aload_1
    //   6: ldc_w 892
    //   9: invokevirtual 210	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   12: invokestatic 895	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   15: astore 5
    //   17: aload_1
    //   18: ldc_w 897
    //   21: invokevirtual 210	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   24: invokestatic 895	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   27: astore 4
    //   29: aload 4
    //   31: astore_3
    //   32: aload_0
    //   33: aload_1
    //   34: ldc_w 898
    //   37: invokevirtual 210	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   40: invokestatic 895	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   43: invokevirtual 901	java/lang/Boolean:booleanValue	()Z
    //   46: putfield 96	com/tapjoy/TJAdUnitJSBridge:customClose	Z
    //   49: new 41	com/tapjoy/TJAdUnitJSBridge$a
    //   52: dup
    //   53: aload_0
    //   54: aload_0
    //   55: getfield 118	com/tapjoy/TJAdUnitJSBridge:g	Landroid/webkit/WebView;
    //   58: invokespecial 904	com/tapjoy/TJAdUnitJSBridge$a:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Landroid/webkit/WebView;)V
    //   61: iconst_2
    //   62: anewarray 222	java/lang/Boolean
    //   65: dup
    //   66: iconst_0
    //   67: aload 5
    //   69: aastore
    //   70: dup
    //   71: iconst_1
    //   72: aload_3
    //   73: aastore
    //   74: invokevirtual 908	com/tapjoy/TJAdUnitJSBridge$a:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   77: pop
    //   78: aload_0
    //   79: aload_2
    //   80: iconst_1
    //   81: anewarray 4	java/lang/Object
    //   84: dup
    //   85: iconst_0
    //   86: getstatic 406	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   89: aastore
    //   90: invokevirtual 230	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   93: return
    //   94: astore_1
    //   95: aload_0
    //   96: aload_2
    //   97: iconst_1
    //   98: anewarray 4	java/lang/Object
    //   101: dup
    //   102: iconst_0
    //   103: getstatic 226	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   106: aastore
    //   107: invokevirtual 230	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   110: aload_1
    //   111: invokevirtual 233	java/lang/Exception:printStackTrace	()V
    //   114: return
    //   115: astore 4
    //   117: goto -85 -> 32
    //   120: astore_1
    //   121: goto -72 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	this	TJAdUnitJSBridge
    //   0	124	1	paramJSONObject	JSONObject
    //   0	124	2	paramString	String
    //   4	69	3	localObject	Object
    //   27	3	4	localBoolean1	Boolean
    //   115	1	4	localException	Exception
    //   15	53	5	localBoolean2	Boolean
    // Exception table:
    //   from	to	target	type
    //   0	17	94	java/lang/Exception
    //   49	93	94	java/lang/Exception
    //   17	29	115	java/lang/Exception
    //   32	49	120	java/lang/Exception
  }
  
  public void removeAssetFromCache(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("url");
      if (TapjoyCache.getInstance() != null)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(TapjoyCache.getInstance().removeAssetFromCache(paramJSONObject)) });
        return;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;) {}
    }
    TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void sendUsageTrackingEvent(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      localObject = paramJSONObject.getString("name");
      if (((String)localObject).isEmpty())
      {
        TapjoyLog.d("TJAdUnitJSBridge", "Empty name for sendUsageTrackingEvent");
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
        return;
      }
      if (this.f != null)
      {
        this.f.sendAdContentTracking((String)localObject, paramJSONObject);
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
        return;
      }
    }
    catch (JSONException paramJSONObject)
    {
      Object localObject = new StringBuilder("Unable to sendUsageTrackingEvent. Invalid parameters: ");
      ((StringBuilder)localObject).append(paramJSONObject);
      TapjoyLog.w("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
    }
  }
  
  public void setAdUnitActivity(TJAdUnitActivity paramTJAdUnitActivity)
  {
    this.e = paramTJAdUnitActivity;
  }
  
  public void setAllowRedirect(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      bool = paramJSONObject.getBoolean("enabled");
    }
    catch (Exception paramJSONObject)
    {
      boolean bool;
      for (;;) {}
    }
    bool = true;
    this.allowRedirect = bool;
    invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
  }
  
  public void setBackgroundColor(JSONObject paramJSONObject, final String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("backgroundColor");
      if (this.f != null)
      {
        this.f.setBackgroundColor(paramJSONObject, new AdUnitAsyncTaskListner()
        {
          public final void onComplete(boolean paramAnonymousBoolean)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramAnonymousBoolean) });
          }
        });
        return;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;) {}
    }
    TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background color. Invalid parameters.");
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void setBackgroundWebViewContent(JSONObject paramJSONObject, final String paramString)
  {
    TapjoyLog.d("TJAdUnitJSBridge", "setBackgroundWebViewContent");
    try
    {
      paramJSONObject = paramJSONObject.getString("backgroundContent");
      if (this.f != null)
      {
        this.f.setBackgroundContent(paramJSONObject, new AdUnitAsyncTaskListner()
        {
          public final void onComplete(boolean paramAnonymousBoolean)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramAnonymousBoolean) });
          }
        });
        return;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;) {}
    }
    TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background content. Invalid parameters.");
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void setCloseButtonClickable(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      TapjoyUtil.runOnMainThread(new Runnable()
      {
        public final void run()
        {
          TJAdUnitActivity localTJAdUnitActivity = TJAdUnitJSBridge.e(TJAdUnitJSBridge.this);
          if (localTJAdUnitActivity != null)
          {
            localTJAdUnitActivity.setCloseButtonClickable(this.a);
            return;
          }
          TapjoyLog.d("TJAdUnitJSBridge", "Cannot setCloseButtonClickable -- TJAdUnitActivity is null");
        }
      });
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void setCloseButtonVisible(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      TapjoyUtil.runOnMainThread(new Runnable()
      {
        public final void run()
        {
          TJAdUnitActivity localTJAdUnitActivity = TJAdUnitJSBridge.e(TJAdUnitJSBridge.this);
          if (localTJAdUnitActivity != null)
          {
            localTJAdUnitActivity.setCloseButtonVisibility(this.a);
            return;
          }
          TapjoyLog.d("TJAdUnitJSBridge", "Cannot setCloseButtonVisible -- TJAdUnitActivity is null");
        }
      });
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    this.k = paramBoolean;
    if (this.k) {
      flushBacklogMessageQueue();
    }
  }
  
  public void setEventPreloadLimit(JSONObject paramJSONObject, String paramString)
  {
    if (TapjoyCache.getInstance() != null) {}
    try
    {
      int i1 = paramJSONObject.getInt("eventPreloadLimit");
      TJPlacementManager.setCachedPlacementLimit(i1);
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;) {}
    }
    TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy cache's event preload limit. Invalid parameters.");
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
    return;
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void setLoggingLevel(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = String.valueOf(paramJSONObject.getString("loggingLevel"));
      TapjoyAppSettings.getInstance().saveLoggingLevel(paramJSONObject);
      return;
    }
    catch (Exception paramJSONObject)
    {
      StringBuilder localStringBuilder = new StringBuilder("setLoggingLevel exception ");
      localStringBuilder.append(paramJSONObject.getLocalizedMessage());
      TapjoyLog.d("TJAdUnitJSBridge", localStringBuilder.toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void setOrientation(JSONObject paramJSONObject, String paramString)
  {
    if (this.f == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    try
    {
      paramJSONObject = paramJSONObject.getString("orientation");
      if ((paramJSONObject.equals("landscape")) || (paramJSONObject.equals("landscapeLeft"))) {
        break label130;
      }
      if (!paramJSONObject.equals("landscapeRight")) {
        break label125;
      }
      i1 = 8;
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        continue;
        int i1 = 1;
        continue;
        i1 = 0;
      }
    }
    this.f.setOrientation(i1);
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
    return;
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void setPrerenderLimit(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      int i1 = paramJSONObject.getInt("prerenderLimit");
      TJPlacementManager.setPreRenderedPlacementLimit(i1);
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;) {}
    }
    TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy placement pre-render limit. Invalid parameters.");
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void setSpinnerVisible(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      boolean bool = paramJSONObject.getBoolean("visible");
      String str = paramJSONObject.optString("title");
      paramJSONObject = paramJSONObject.optString("message");
      TJAdUnitActivity localTJAdUnitActivity = this.e;
      if (localTJAdUnitActivity != null)
      {
        if (bool) {
          this.i = ProgressDialog.show(localTJAdUnitActivity, str, paramJSONObject);
        } else if (this.i != null) {
          this.i.dismiss();
        }
        invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
        return;
      }
      TapjoyLog.d("TJAdUnitJSBridge", "Cannot setSpinnerVisible -- TJAdUnitActivity is null");
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void setVideoMute(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      boolean bool = paramJSONObject.getBoolean("enabled");
      this.f.a(bool);
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (JSONException paramJSONObject)
    {
      for (;;) {}
    }
    TapjoyLog.d("TJAdUnitJSBridge", "Failed to parse 'enabled' from json params.");
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void shouldClose(JSONObject paramJSONObject, String paramString)
  {
    TJAdUnitActivity localTJAdUnitActivity = this.e;
    try
    {
      if ((Boolean.valueOf(paramJSONObject.getString("close")).booleanValue()) && (localTJAdUnitActivity != null)) {
        localTJAdUnitActivity.finish();
      }
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      if (localTJAdUnitActivity != null) {
        localTJAdUnitActivity.finish();
      }
      paramJSONObject.printStackTrace();
    }
    this.closeRequested = false;
  }
  
  public void startMoatVideoTracker(JSONObject paramJSONObject, final String paramString)
  {
    try
    {
      int i1 = paramJSONObject.getInt("videoLength");
      localObject = new HashMap();
      paramJSONObject = paramJSONObject.getJSONObject("adIds");
      if (paramJSONObject != null)
      {
        Iterator localIterator = paramJSONObject.keys();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          ((Map)localObject).put(str, paramJSONObject.getString(str));
        }
      }
      this.n.post(new Runnable()
      {
        public final void run()
        {
          boolean bool;
          if (TJAdUnitJSBridge.f(TJAdUnitJSBridge.this) != null) {
            bool = TJAdUnitJSBridge.f(TJAdUnitJSBridge.this).trackVideoAd(this.a, this.b, TJAdUnitJSBridge.c(TJAdUnitJSBridge.this));
          } else {
            bool = false;
          }
          TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(bool) });
        }
      });
      return;
    }
    catch (Exception paramJSONObject)
    {
      Object localObject = new StringBuilder("startMoatVideoTracker exception ");
      ((StringBuilder)localObject).append(paramJSONObject.toString());
      TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
    }
  }
  
  public void startUsageTrackingEvent(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      localObject = paramJSONObject.getString("name");
      if (((String)localObject).isEmpty())
      {
        TapjoyLog.d("TJAdUnitJSBridge", "Empty name for startUsageTrackingEvent");
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
        return;
      }
      if (this.f != null)
      {
        this.f.startAdContentTracking((String)localObject, paramJSONObject);
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
        return;
      }
    }
    catch (JSONException paramJSONObject)
    {
      Object localObject = new StringBuilder("Unable to startUsageTrackingEvent. Invalid parameters: ");
      ((StringBuilder)localObject).append(paramJSONObject);
      TapjoyLog.w("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
    }
  }
  
  public void triggerEvent(JSONObject paramJSONObject, String paramString)
  {
    if (this.f != null) {}
    try
    {
      paramJSONObject = paramJSONObject.getString("eventName");
      if (paramJSONObject.equals("start"))
      {
        this.f.fireOnVideoStart();
        return;
      }
      if (paramJSONObject.equals("complete"))
      {
        this.f.fireOnVideoComplete();
        return;
      }
      if (!paramJSONObject.equals("error")) {
        break label81;
      }
      this.f.fireOnVideoError("Error while trying to play video.");
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;) {}
    }
    TapjoyLog.w("TJAdUnitJSBridge", "Unable to triggerEvent. No event name.");
    return;
    label81:
  }
  
  public void triggerMoatVideoEvent(final JSONObject paramJSONObject, String paramString)
  {
    try
    {
      int i1 = paramJSONObject.getInt("currentVideoTime");
      localObject = paramJSONObject.getString("eventName");
      paramJSONObject = null;
      if (this.m != null) {
        paramJSONObject = (MoatAdEventType)this.m.get(localObject);
      }
      if (paramJSONObject == null)
      {
        paramJSONObject = new StringBuilder("eventName:");
        paramJSONObject.append((String)localObject);
        paramJSONObject.append(" has no matching MOAT event");
        TapjoyLog.d("TJAdUnitJSBridge", paramJSONObject.toString());
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
        return;
      }
      localObject = new StringBuilder("Sending MOAT event: ");
      ((StringBuilder)localObject).append(paramJSONObject);
      TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
      paramJSONObject = new MoatAdEvent(paramJSONObject, Integer.valueOf(i1));
      this.n.post(new Runnable()
      {
        public final void run()
        {
          if (TJAdUnitJSBridge.f(TJAdUnitJSBridge.this) != null) {
            TJAdUnitJSBridge.f(TJAdUnitJSBridge.this).dispatchEvent(paramJSONObject);
          }
        }
      });
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      Object localObject = new StringBuilder("triggerMoatVideoEvent exception ");
      ((StringBuilder)localObject).append(paramJSONObject.toString());
      TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
    }
  }
  
  public void unsetOrientation(JSONObject paramJSONObject, String paramString)
  {
    if (this.f == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    try
    {
      this.f.unsetOrientation();
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;) {}
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public static abstract interface AdUnitAsyncTaskListner
  {
    public abstract void onComplete(boolean paramBoolean);
  }
  
  @TargetApi(11)
  final class a
    extends AsyncTask
  {
    WebView a;
    
    public a(WebView paramWebView)
    {
      this.a = paramWebView;
    }
  }
}
