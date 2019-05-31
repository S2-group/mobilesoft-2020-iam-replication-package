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
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
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
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class TJAdUnitJSBridge
{
  private TJWebViewJSInterface a;
  public boolean allowRedirect = true;
  private TJAdUnitJSBridge b;
  private Context c;
  public boolean closeRequested = false;
  public boolean customClose = false;
  private TJAdUnitActivity d;
  public boolean didLaunchOtherActivity = false;
  private TJAdUnit e;
  private WebView f;
  private ProgressDialog g;
  private LocationManager h;
  private LocationListener i;
  private View j = null;
  private boolean k;
  private ReactiveVideoTracker l;
  private HashMap m;
  private Handler n;
  public String otherActivityCallbackID = null;
  
  public TJAdUnitJSBridge(Context paramContext, WebView paramWebView)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "creating AdUnit/JS Bridge");
    this.c = paramContext;
    this.f = paramWebView;
    this.b = this;
    if (this.f == null)
    {
      TapjoyLog.e("TJAdUnitJSBridge", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Cannot create AdUnitJSBridge -- webview is NULL"));
      return;
    }
    this.a = new TJWebViewJSInterface(this.f, new TJWebViewJSInterfaceListener()
    {
      public final void onDispatchMethod(String paramAnonymousString, JSONObject paramAnonymousJSONObject)
      {
        if (TJAdUnitJSBridge.a(TJAdUnitJSBridge.this)) {}
        try
        {
          str1 = paramAnonymousJSONObject.getString("callbackId");
        }
        catch (Exception localException)
        {
          for (;;)
          {
            try
            {
              String str1;
              paramAnonymousJSONObject = paramAnonymousJSONObject.getJSONObject("data");
              paramAnonymousString = TJAdUnitJSBridge.class.getMethod(paramAnonymousString, new Class[] { JSONObject.class, String.class });
              TapjoyLog.d("TJAdUnitJSBridge", "Dispatching method with method name=" + paramAnonymousString + ";data=" + paramAnonymousJSONObject + ";callbackID=" + str1);
              paramAnonymousString.invoke(TJAdUnitJSBridge.b(TJAdUnitJSBridge.this), new Object[] { paramAnonymousJSONObject, str1 });
              return;
            }
            catch (Exception paramAnonymousString)
            {
              String str2;
              paramAnonymousString.printStackTrace();
              TJAdUnitJSBridge.this.invokeJSCallback(str2, new Object[] { Boolean.FALSE });
            }
            localException = localException;
            str2 = null;
          }
        }
      }
    });
    this.f.addJavascriptInterface(this.a, "AndroidJavascriptInterface");
    this.k = true;
  }
  
  public TJAdUnitJSBridge(Context paramContext, TJAdUnit paramTJAdUnit)
  {
    this(paramContext, paramTJAdUnit.getWebView());
    this.e = paramTJAdUnit;
  }
  
  /* Error */
  public void alert(JSONObject paramJSONObject, final String paramString)
  {
    // Byte code:
    //   0: ldc 91
    //   2: new 176	java/lang/StringBuilder
    //   5: dup
    //   6: ldc -78
    //   8: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   11: aload_1
    //   12: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   15: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   18: invokestatic 191	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   21: ldc -63
    //   23: astore 6
    //   25: aload_1
    //   26: ldc -61
    //   28: invokevirtual 201	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   31: astore 5
    //   33: aload 5
    //   35: astore 6
    //   37: aload_1
    //   38: ldc -53
    //   40: invokevirtual 201	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   43: astore 7
    //   45: aload 7
    //   47: astore 6
    //   49: aload_1
    //   50: ldc -51
    //   52: invokevirtual 209	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   55: astore_1
    //   56: aload_0
    //   57: getfield 167	com/tapjoy/TJAdUnitJSBridge:d	Lcom/tapjoy/TJAdUnitActivity;
    //   60: astore 7
    //   62: aload 7
    //   64: ifnull +224 -> 288
    //   67: new 211	android/app/AlertDialog$Builder
    //   70: dup
    //   71: aload 7
    //   73: invokespecial 214	android/app/AlertDialog$Builder:<init>	(Landroid/content/Context;)V
    //   76: aload 5
    //   78: invokevirtual 218	android/app/AlertDialog$Builder:setTitle	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   81: aload 6
    //   83: invokevirtual 221	android/app/AlertDialog$Builder:setMessage	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   86: invokevirtual 225	android/app/AlertDialog$Builder:create	()Landroid/app/AlertDialog;
    //   89: astore 5
    //   91: aload_1
    //   92: ifnull +10 -> 102
    //   95: aload_1
    //   96: invokevirtual 231	org/json/JSONArray:length	()I
    //   99: ifne +56 -> 155
    //   102: aload_0
    //   103: aload_2
    //   104: iconst_1
    //   105: anewarray 4	java/lang/Object
    //   108: dup
    //   109: iconst_0
    //   110: getstatic 237	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   113: aastore
    //   114: invokevirtual 241	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   117: return
    //   118: astore_1
    //   119: ldc -63
    //   121: astore 7
    //   123: aload 6
    //   125: astore 5
    //   127: aload 7
    //   129: astore 6
    //   131: aload_0
    //   132: aload_2
    //   133: iconst_1
    //   134: anewarray 4	java/lang/Object
    //   137: dup
    //   138: iconst_0
    //   139: getstatic 237	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   142: aastore
    //   143: invokevirtual 241	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   146: aload_1
    //   147: invokevirtual 244	java/lang/Exception:printStackTrace	()V
    //   150: aconst_null
    //   151: astore_1
    //   152: goto -96 -> 56
    //   155: new 246	java/util/ArrayList
    //   158: dup
    //   159: invokespecial 247	java/util/ArrayList:<init>	()V
    //   162: astore 6
    //   164: iconst_0
    //   165: istore 4
    //   167: iload 4
    //   169: aload_1
    //   170: invokevirtual 231	org/json/JSONArray:length	()I
    //   173: if_icmpge +97 -> 270
    //   176: iload 4
    //   178: tableswitch	default:+22->200, 0:+70->248, 1:+76->254
    //   200: iconst_m1
    //   201: istore_3
    //   202: aload 6
    //   204: aload_1
    //   205: iload 4
    //   207: invokevirtual 250	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   210: invokevirtual 254	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   213: pop
    //   214: aload 5
    //   216: iload_3
    //   217: aload 6
    //   219: iload 4
    //   221: invokevirtual 258	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   224: checkcast 260	java/lang/CharSequence
    //   227: new 20	com/tapjoy/TJAdUnitJSBridge$5
    //   230: dup
    //   231: aload_0
    //   232: aload_2
    //   233: invokespecial 263	com/tapjoy/TJAdUnitJSBridge$5:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Ljava/lang/String;)V
    //   236: invokevirtual 269	android/app/AlertDialog:setButton	(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V
    //   239: iload 4
    //   241: iconst_1
    //   242: iadd
    //   243: istore 4
    //   245: goto -78 -> 167
    //   248: bipush -2
    //   250: istore_3
    //   251: goto -49 -> 202
    //   254: bipush -3
    //   256: istore_3
    //   257: goto -55 -> 202
    //   260: astore 7
    //   262: aload 7
    //   264: invokevirtual 244	java/lang/Exception:printStackTrace	()V
    //   267: goto -53 -> 214
    //   270: aload 5
    //   272: iconst_0
    //   273: invokevirtual 273	android/app/AlertDialog:setCancelable	(Z)V
    //   276: aload 5
    //   278: iconst_0
    //   279: invokevirtual 276	android/app/AlertDialog:setCanceledOnTouchOutside	(Z)V
    //   282: aload 5
    //   284: invokevirtual 279	android/app/AlertDialog:show	()V
    //   287: return
    //   288: ldc 91
    //   290: ldc_w 281
    //   293: invokestatic 191	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   296: return
    //   297: astore_1
    //   298: goto -167 -> 131
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	301	0	this	TJAdUnitJSBridge
    //   0	301	1	paramJSONObject	JSONObject
    //   0	301	2	paramString	String
    //   201	56	3	i1	int
    //   165	79	4	i2	int
    //   31	252	5	localObject1	Object
    //   23	195	6	localObject2	Object
    //   43	85	7	localObject3	Object
    //   260	3	7	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   25	33	118	java/lang/Exception
    //   37	45	118	java/lang/Exception
    //   202	214	260	java/lang/Exception
    //   49	56	297	java/lang/Exception
  }
  
  public void attachVolumeListener(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      boolean bool = paramJSONObject.getBoolean("attach");
      int i1 = paramJSONObject.optInt("interval", 500);
      if (i1 > 0)
      {
        this.e.attachVolumeListener(bool, i1);
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
        return;
      }
      TapjoyLog.d("TJAdUnitJSBridge", "Invalid `interval` value passed to attachVolumeListener(): interval=" + i1);
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "attachVolumeListener exception " + paramJSONObject.toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void cacheAsset(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = "";
    localLong = Long.valueOf(0L);
    try
    {
      str2 = paramJSONObject.getString("url");
    }
    catch (Exception paramJSONObject)
    {
      String str2;
      String str1;
      long l1;
      label45:
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    for (;;)
    {
      try
      {
        str1 = paramJSONObject.getString("offerId");
        localObject = str1;
      }
      catch (Exception localException)
      {
        break;
      }
      try
      {
        l1 = paramJSONObject.getLong("timeToLive");
        paramJSONObject = Long.valueOf(l1);
      }
      catch (Exception paramJSONObject)
      {
        paramJSONObject = localLong;
        break label45;
      }
    }
    if (TapjoyCache.getInstance() != null)
    {
      invokeJSCallback(paramString, new Object[] { TapjoyCache.getInstance().cacheAssetFromURL(str2, (String)localObject, paramJSONObject.longValue()) });
      return;
    }
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
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { "" });
      return;
    }
    invokeJSCallback(paramString, new Object[] { "" });
  }
  
  public void checkAppInstalled(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = "";
    try
    {
      paramJSONObject = paramJSONObject.getString("bundle");
      if ((paramJSONObject != null) && (paramJSONObject.length() > 0))
      {
        localObject = this.c.getPackageManager().getInstalledApplications(0).iterator();
        while (((Iterator)localObject).hasNext()) {
          if (((ApplicationInfo)((Iterator)localObject).next()).packageName.equals(paramJSONObject))
          {
            invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
            return;
          }
        }
      }
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        paramJSONObject.printStackTrace();
        paramJSONObject = (JSONObject)localObject;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
    }
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
    if (this.e != null) {
      this.e.clearVideo(new AdUnitAsyncTaskListner()
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
    if (this.e != null)
    {
      this.e.fireContentReady();
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void destroy()
  {
    if ((this.i != null) && (this.h != null))
    {
      this.h.removeUpdates(this.i);
      this.h = null;
      this.i = null;
    }
  }
  
  public void disable()
  {
    this.k = false;
  }
  
  public void dismiss(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject = this.d;
    if (paramJSONObject != null)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      paramJSONObject.finish();
      return;
    }
    TapjoyLog.d("TJAdUnitJSBridge", "Cannot dismiss -- TJAdUnitActivity is null");
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
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
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          String str = paramJSONObject.getString("html");
          if (str != null) {
            break;
          }
          invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
          return;
          localException1 = localException1;
          bool = false;
        }
      }
      catch (Exception localException2)
      {
        do
        {
          boolean bool;
          for (;;)
          {
            localException2.printStackTrace();
            localObject = null;
          }
          if (bool)
          {
            ((Activity)this.c).runOnUiThread(new Runnable()
            {
              public final void run()
              {
                try
                {
                  String str = paramJSONObject.getString("html");
                  if ((TJAdUnitJSBridge.c(TJAdUnitJSBridge.this) != null) && (TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).getParent() != null)) {
                    ((ViewGroup)TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).getParent()).removeView(TJAdUnitJSBridge.c(TJAdUnitJSBridge.this));
                  }
                  MraidView localMraidView = new MraidView(TJAdUnitJSBridge.d(TJAdUnitJSBridge.this));
                  TJAdUnitJSBridge.e(TJAdUnitJSBridge.this).getSettings().setJavaScriptEnabled(true);
                  localMraidView.setPlacementType(MraidView.PLACEMENT_TYPE.INLINE);
                  localMraidView.setLayoutParams(new ViewGroup.LayoutParams(640, 100));
                  localMraidView.setInitialScale(100);
                  localMraidView.setBackgroundColor(0);
                  localMraidView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
                  int i = ((WindowManager)((Activity)TJAdUnitJSBridge.d(TJAdUnitJSBridge.this)).getSystemService("window")).getDefaultDisplay().getWidth();
                  TJAdUnitJSBridge.a(TJAdUnitJSBridge.this, TapjoyUtil.scaleDisplayAd(localMraidView, i));
                  ((Activity)TJAdUnitJSBridge.d(TJAdUnitJSBridge.this)).addContentView(TJAdUnitJSBridge.c(TJAdUnitJSBridge.this), new ViewGroup.LayoutParams(i, (int)(100.0D * (i / 640.0D))));
                  return;
                }
                catch (Exception localException)
                {
                  for (;;)
                  {
                    localException.printStackTrace();
                    Object localObject = null;
                  }
                }
              }
            });
            return;
          }
          paramJSONObject = new TJPlacementData(TapjoyConnectCore.getHostURL(), (String)localObject, paramString);
          paramString = this.d;
        } while (paramString == null);
        Object localObject = new Intent(paramString, TJAdUnitActivity.class);
        ((Intent)localObject).putExtra("placement_data", paramJSONObject);
        paramString.startActivityForResult((Intent)localObject, 327);
      }
    }
  }
  
  public void displayStoreURL(JSONObject paramJSONObject, String paramString)
  {
    displayURL(paramJSONObject, paramString);
  }
  
  public void displayURL(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("url");
      this.didLaunchOtherActivity = true;
      this.otherActivityCallbackID = paramString;
      paramJSONObject = new Intent("android.intent.action.VIEW", Uri.parse(paramJSONObject));
      ((Activity)this.c).startActivity(paramJSONObject);
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
        this.e.loadVideoUrl(paramJSONObject, new AdUnitAsyncTaskListner()
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
  
  public void flushMessageQueue()
  {
    this.a.flushMessageQueue();
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
    paramJSONObject = this.c.getPackageManager();
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
  
  public void getLocation(JSONObject paramJSONObject, String paramString)
  {
    float f1 = 100.0F;
    for (;;)
    {
      try
      {
        float f2 = Float.valueOf(paramJSONObject.getString("gpsAccuracy")).floatValue();
        f1 = f2;
      }
      catch (Exception localException)
      {
        boolean bool;
        continue;
      }
      try
      {
        bool = Boolean.valueOf(paramJSONObject.getString("enabled")).booleanValue();
        this.h = ((LocationManager)this.c.getSystemService("location"));
        paramJSONObject = new Criteria();
        paramJSONObject = this.h.getBestProvider(paramJSONObject, false);
        if (this.i == null) {
          this.i = new LocationListener()
          {
            public final void onLocationChanged(Location paramAnonymousLocation)
            {
              if ((TJAdUnitJSBridge.d(TJAdUnitJSBridge.this) == null) || (TJAdUnitJSBridge.e(TJAdUnitJSBridge.this) == null)) {
                if ((TJAdUnitJSBridge.f(TJAdUnitJSBridge.this) != null) && (TJAdUnitJSBridge.g(TJAdUnitJSBridge.this) != null))
                {
                  TapjoyLog.d("TJAdUnitJSBridge", "stopping updates");
                  TJAdUnitJSBridge.f(TJAdUnitJSBridge.this).removeUpdates(TJAdUnitJSBridge.g(TJAdUnitJSBridge.this));
                }
              }
              while (paramAnonymousLocation == null) {
                return;
              }
              HashMap localHashMap = new HashMap();
              localHashMap.put("lat", Double.valueOf(paramAnonymousLocation.getLatitude()));
              localHashMap.put("long", Double.valueOf(paramAnonymousLocation.getLongitude()));
              localHashMap.put("altitude", Double.valueOf(paramAnonymousLocation.getAltitude()));
              localHashMap.put("timestamp", Long.valueOf(paramAnonymousLocation.getTime()));
              localHashMap.put("speed", Float.valueOf(paramAnonymousLocation.getSpeed()));
              localHashMap.put("course", Float.valueOf(paramAnonymousLocation.getBearing()));
              TJAdUnitJSBridge.this.invokeJSAdunitMethod("locationUpdated", localHashMap);
            }
            
            public final void onProviderDisabled(String paramAnonymousString) {}
            
            public final void onProviderEnabled(String paramAnonymousString) {}
            
            public final void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {}
          };
        }
        if (bool) {
          if (paramJSONObject != null)
          {
            this.h.requestLocationUpdates(paramJSONObject, 0L, f1, this.i);
            invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
            return;
          }
        }
      }
      catch (Exception paramJSONObject)
      {
        paramJSONObject.printStackTrace();
        bool = false;
        continue;
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        return;
      }
      if ((this.h != null) && (this.i != null)) {
        this.h.removeUpdates(this.i);
      }
    }
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
    if (this.e == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
      return null;
    }
    String str = this.e.getVolume();
    boolean bool = this.e.isMuted();
    TapjoyLog.d("TJAdUnitJSBridge", "getVolumeArgs: volume=" + str + "; isMuted=" + bool);
    HashMap localHashMap = new HashMap();
    localHashMap.put("currentVolume", str);
    localHashMap.put("isMuted", Boolean.valueOf(bool));
    return localHashMap;
  }
  
  public void initMoatVideoTracker(JSONObject paramJSONObject, String paramString)
  {
    TJAdUnitActivity localTJAdUnitActivity = this.d;
    if (localTJAdUnitActivity == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "Error from initMoatVideoTracker -- TJAdUnitActivity is null");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    try
    {
      paramJSONObject = paramJSONObject.getString("partnerCode");
      this.l = ((ReactiveVideoTracker)MoatFactory.create(localTJAdUnitActivity).createCustomTracker(new ReactiveVideoTrackerPlugin(paramJSONObject)));
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
      TapjoyLog.d("TJAdUnitJSBridge", "initMoatVideoTracker exception " + paramJSONObject.toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
    }
  }
  
  public void invokeJSAdunitMethod(String paramString, Map paramMap)
  {
    this.a.callback(paramMap, paramString, null);
  }
  
  public void invokeJSAdunitMethod(String paramString, Object... paramVarArgs)
  {
    paramVarArgs = new ArrayList(Arrays.asList(paramVarArgs));
    this.a.callback(paramVarArgs, paramString, null);
  }
  
  public void invokeJSCallback(String paramString, Map paramMap)
  {
    this.a.callback(paramMap, "", paramString);
  }
  
  public void invokeJSCallback(String paramString, Object... paramVarArgs)
  {
    if (ct.c(paramString))
    {
      TapjoyLog.d("TJAdUnitJSBridge", "invokeJSCallback -- no callbackID provided");
      return;
    }
    paramVarArgs = new ArrayList(Arrays.asList(paramVarArgs));
    this.a.callback(paramVarArgs, "", paramString);
  }
  
  public void log(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      TapjoyLog.d("TJAdUnitJSBridge", "Logging message=" + paramJSONObject.getString("message"));
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
          if (Build.VERSION.SDK_INT >= 19) {
            TJAdUnitJSBridge.e(TJAdUnitJSBridge.this).evaluateJavascript(paramJSONObject.getString("command"), null);
          }
          for (;;)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
            return;
            TJAdUnitJSBridge.e(TJAdUnitJSBridge.this).loadUrl("javascript:" + paramJSONObject.getString("command"));
          }
          return;
        }
        catch (Exception localException)
        {
          TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        }
      }
    });
  }
  
  public void onVideoCompletion()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoComplete");
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoError(String paramString)
  {
    paramString = new HashMap();
    paramString.put("videoEventName", "videoError");
    invokeJSAdunitMethod("videoEvent", paramString);
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
      paramJSONObject = this.c.getPackageManager().getLaunchIntentForPackage(paramJSONObject);
      this.c.startActivity(paramJSONObject);
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
    if (this.e != null) {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(this.e.pauseVideo()) });
    }
  }
  
  public void playVideo(JSONObject paramJSONObject, String paramString)
  {
    if (this.e != null) {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(this.e.playVideo()) });
    }
  }
  
  /* Error */
  public void present(JSONObject paramJSONObject, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: invokestatic 301	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   4: astore_3
    //   5: aload_1
    //   6: ldc_w 830
    //   9: invokevirtual 201	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   12: invokestatic 594	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   15: astore 5
    //   17: aload_1
    //   18: ldc_w 832
    //   21: invokevirtual 201	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   24: invokestatic 594	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   27: astore 4
    //   29: aload 4
    //   31: astore_3
    //   32: aload_0
    //   33: aload_1
    //   34: ldc_w 833
    //   37: invokevirtual 201	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   40: invokestatic 594	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   43: invokevirtual 597	java/lang/Boolean:booleanValue	()Z
    //   46: putfield 87	com/tapjoy/TJAdUnitJSBridge:customClose	Z
    //   49: new 33	com/tapjoy/TJAdUnitJSBridge$a
    //   52: dup
    //   53: aload_0
    //   54: aload_0
    //   55: getfield 102	com/tapjoy/TJAdUnitJSBridge:f	Landroid/webkit/WebView;
    //   58: invokespecial 836	com/tapjoy/TJAdUnitJSBridge$a:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Landroid/webkit/WebView;)V
    //   61: iconst_2
    //   62: anewarray 233	java/lang/Boolean
    //   65: dup
    //   66: iconst_0
    //   67: aload 5
    //   69: aastore
    //   70: dup
    //   71: iconst_1
    //   72: aload_3
    //   73: aastore
    //   74: invokevirtual 840	com/tapjoy/TJAdUnitJSBridge$a:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   77: pop
    //   78: aload_0
    //   79: aload_2
    //   80: iconst_1
    //   81: anewarray 4	java/lang/Object
    //   84: dup
    //   85: iconst_0
    //   86: getstatic 396	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   89: aastore
    //   90: invokevirtual 241	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   93: return
    //   94: astore_1
    //   95: aload_0
    //   96: aload_2
    //   97: iconst_1
    //   98: anewarray 4	java/lang/Object
    //   101: dup
    //   102: iconst_0
    //   103: getstatic 237	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   106: aastore
    //   107: invokevirtual 241	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   110: aload_1
    //   111: invokevirtual 244	java/lang/Exception:printStackTrace	()V
    //   114: return
    //   115: astore_1
    //   116: goto -67 -> 49
    //   119: astore 4
    //   121: goto -89 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	this	TJAdUnitJSBridge
    //   0	124	1	paramJSONObject	JSONObject
    //   0	124	2	paramString	String
    //   4	69	3	localObject	Object
    //   27	3	4	localBoolean1	Boolean
    //   119	1	4	localException	Exception
    //   15	53	5	localBoolean2	Boolean
    // Exception table:
    //   from	to	target	type
    //   0	17	94	java/lang/Exception
    //   49	93	94	java/lang/Exception
    //   32	49	115	java/lang/Exception
    //   17	29	119	java/lang/Exception
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
    }
    catch (Exception paramJSONObject)
    {
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void setAdUnitActivity(TJAdUnitActivity paramTJAdUnitActivity)
  {
    this.d = paramTJAdUnitActivity;
  }
  
  public void setAllowRedirect(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      bool = paramJSONObject.getBoolean("enabled");
      this.allowRedirect = bool;
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        boolean bool = true;
      }
    }
  }
  
  public void setBackgroundColor(JSONObject paramJSONObject, final String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("backgroundColor");
      if (this.e != null)
      {
        this.e.setBackgroundColor(paramJSONObject, new AdUnitAsyncTaskListner()
        {
          public final void onComplete(boolean paramAnonymousBoolean)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramAnonymousBoolean) });
          }
        });
        return;
      }
    }
    catch (Exception paramJSONObject)
    {
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background color. Invalid parameters.");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void setBackgroundWebViewContent(JSONObject paramJSONObject, final String paramString)
  {
    TapjoyLog.d("TJAdUnitJSBridge", "setBackgroundWebViewContent");
    try
    {
      paramJSONObject = paramJSONObject.getString("backgroundContent");
      if (this.e != null)
      {
        this.e.setBackgroundContent(paramJSONObject, new AdUnitAsyncTaskListner()
        {
          public final void onComplete(boolean paramAnonymousBoolean)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramAnonymousBoolean) });
          }
        });
        return;
      }
    }
    catch (Exception paramJSONObject)
    {
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background content. Invalid parameters.");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void setCloseButtonVisible(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      TapjoyUtil.runOnMainThread(new Runnable()
      {
        public final void run()
        {
          TJAdUnitActivity localTJAdUnitActivity = TJAdUnitJSBridge.h(TJAdUnitJSBridge.this);
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
  
  public void setEventPreloadLimit(JSONObject paramJSONObject, String paramString)
  {
    if (TapjoyCache.getInstance() != null) {
      try
      {
        int i1 = paramJSONObject.getInt("eventPreloadLimit");
        TJPlacementManager.setCachedPlacementLimit(i1);
        invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
        return;
      }
      catch (Exception paramJSONObject)
      {
        TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy cache's event preload limit. Invalid parameters.");
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        return;
      }
    }
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
      TapjoyLog.d("TJAdUnitJSBridge", "setLoggingLevel exception " + paramJSONObject.getLocalizedMessage());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void setOrientation(JSONObject paramJSONObject, String paramString)
  {
    if (this.e == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    for (;;)
    {
      int i1;
      try
      {
        paramJSONObject = paramJSONObject.getString("orientation");
        if (paramJSONObject.equals("landscape")) {
          break label131;
        }
        if (!paramJSONObject.equals("landscapeLeft")) {
          break label106;
        }
      }
      catch (Exception paramJSONObject)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
        return;
      }
      this.e.setOrientation(i1);
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
      label106:
      boolean bool = paramJSONObject.equals("landscapeRight");
      if (bool)
      {
        i1 = 8;
      }
      else
      {
        i1 = 1;
        continue;
        label131:
        i1 = 0;
      }
    }
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
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy placement pre-render limit. Invalid parameters.");
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
    }
  }
  
  public void setSpinnerVisible(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      boolean bool = paramJSONObject.getBoolean("visible");
      String str = paramJSONObject.optString("title");
      paramJSONObject = paramJSONObject.optString("message");
      TJAdUnitActivity localTJAdUnitActivity = this.d;
      if (localTJAdUnitActivity != null)
      {
        if (bool) {
          this.g = ProgressDialog.show(localTJAdUnitActivity, str, paramJSONObject);
        }
        for (;;)
        {
          invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
          return;
          if (this.g != null) {
            this.g.dismiss();
          }
        }
      }
      TapjoyLog.d("TJAdUnitJSBridge", "Cannot setSpinnerVisible -- TJAdUnitActivity is null");
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void shouldClose(JSONObject paramJSONObject, String paramString)
  {
    localTJAdUnitActivity = this.d;
    try
    {
      if ((Boolean.valueOf(paramJSONObject.getString("close")).booleanValue()) && (localTJAdUnitActivity != null)) {
        localTJAdUnitActivity.finish();
      }
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        if (localTJAdUnitActivity != null) {
          localTJAdUnitActivity.finish();
        }
        paramJSONObject.printStackTrace();
      }
    }
    this.closeRequested = false;
  }
  
  public void startMoatVideoTracker(JSONObject paramJSONObject, final String paramString)
  {
    try
    {
      int i1 = paramJSONObject.getInt("videoLength");
      final HashMap localHashMap = new HashMap();
      paramJSONObject = paramJSONObject.getJSONObject("adIds");
      if (paramJSONObject != null)
      {
        Iterator localIterator = paramJSONObject.keys();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localHashMap.put(str, paramJSONObject.getString(str));
        }
      }
      this.n.post(new Runnable()
      {
        public final void run()
        {
          if (TJAdUnitJSBridge.i(TJAdUnitJSBridge.this) != null) {}
          for (boolean bool = TJAdUnitJSBridge.i(TJAdUnitJSBridge.this).trackVideoAd(localHashMap, this.b, TJAdUnitJSBridge.e(TJAdUnitJSBridge.this));; bool = false)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(bool) });
            return;
          }
        }
      });
    }
    catch (Exception paramJSONObject)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "startMoatVideoTracker exception " + paramJSONObject.toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
  }
  
  public void triggerEvent(JSONObject paramJSONObject, String paramString)
  {
    if (this.e != null) {}
    do
    {
      try
      {
        paramJSONObject = paramJSONObject.getString("eventName");
        if (paramJSONObject.equals("start"))
        {
          this.e.fireOnVideoStart();
          return;
        }
      }
      catch (Exception paramJSONObject)
      {
        TapjoyLog.w("TJAdUnitJSBridge", "Unable to triggerEvent. No event name.");
        return;
      }
      if (paramJSONObject.equals("complete"))
      {
        this.e.fireOnVideoComplete();
        return;
      }
    } while (!paramJSONObject.equals("error"));
    this.e.fireOnVideoError("Error while trying to play video.");
  }
  
  public void triggerMoatVideoEvent(final JSONObject paramJSONObject, String paramString)
  {
    try
    {
      int i1 = paramJSONObject.getInt("currentVideoTime");
      String str = paramJSONObject.getString("eventName");
      paramJSONObject = null;
      if (this.m != null) {
        paramJSONObject = (MoatAdEventType)this.m.get(str);
      }
      if (paramJSONObject == null)
      {
        TapjoyLog.d("TJAdUnitJSBridge", "eventName:" + str + " has no matching MOAT event");
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
        return;
      }
      TapjoyLog.d("TJAdUnitJSBridge", "Sending MOAT event: " + paramJSONObject);
      paramJSONObject = new MoatAdEvent(paramJSONObject, Integer.valueOf(i1));
      this.n.post(new Runnable()
      {
        public final void run()
        {
          if (TJAdUnitJSBridge.i(TJAdUnitJSBridge.this) != null) {
            TJAdUnitJSBridge.i(TJAdUnitJSBridge.this).dispatchEvent(paramJSONObject);
          }
        }
      });
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "triggerMoatVideoEvent exception " + paramJSONObject.toString());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
    }
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