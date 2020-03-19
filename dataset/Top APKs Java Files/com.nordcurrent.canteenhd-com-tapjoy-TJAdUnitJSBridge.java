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
import com.tapjoy.internal.cr;
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
  private TJAdUnit d;
  public boolean didLaunchOtherActivity = false;
  private WebView e;
  private ProgressDialog f;
  private LocationManager g;
  private LocationListener h;
  private View i = null;
  private boolean j;
  private ReactiveVideoTracker k;
  private HashMap l;
  private Handler m;
  public String otherActivityCallbackID = null;
  
  public TJAdUnitJSBridge(Context paramContext, WebView paramWebView)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "creating AdUnit/JS Bridge");
    this.c = paramContext;
    this.e = paramWebView;
    this.b = this;
    if (this.e == null)
    {
      TapjoyLog.e("TJAdUnitJSBridge", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Cannot create AdUnitJSBridge -- webview is NULL"));
      return;
    }
    this.a = new TJWebViewJSInterface(this.e, new TJWebViewJSInterfaceListener()
    {
      public final void onDispatchMethod(String paramAnonymousString, JSONObject paramAnonymousJSONObject)
      {
        if (TJAdUnitJSBridge.a(TJAdUnitJSBridge.this))
        {
          Object localObject1 = null;
          for (;;)
          {
            try
            {
              localObject2 = paramAnonymousJSONObject.getString("callbackId");
              localObject1 = localObject2;
            }
            catch (Exception localException)
            {
              Object localObject2;
              continue;
            }
            try
            {
              paramAnonymousJSONObject = paramAnonymousJSONObject.getJSONObject("data");
              paramAnonymousString = TJAdUnitJSBridge.class.getMethod(paramAnonymousString, new Class[] { JSONObject.class, String.class });
              localObject2 = new StringBuilder("Dispatching method with method name=");
              ((StringBuilder)localObject2).append(paramAnonymousString);
              ((StringBuilder)localObject2).append(";data=");
              ((StringBuilder)localObject2).append(paramAnonymousJSONObject);
              ((StringBuilder)localObject2).append(";callbackID=");
              ((StringBuilder)localObject2).append(localObject1);
              TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject2).toString());
              paramAnonymousString.invoke(TJAdUnitJSBridge.b(TJAdUnitJSBridge.this), new Object[] { paramAnonymousJSONObject, localObject1 });
              return;
            }
            catch (Exception paramAnonymousString)
            {
              paramAnonymousString.printStackTrace();
              TJAdUnitJSBridge.this.invokeJSCallback(localObject1, new Object[] { Boolean.FALSE });
            }
          }
        }
      }
    });
    this.e.addJavascriptInterface(this.a, "AndroidJavascriptInterface");
    this.j = true;
  }
  
  public TJAdUnitJSBridge(Context paramContext, TJAdUnit paramTJAdUnit)
  {
    this(paramContext, paramTJAdUnit.getWebView());
    this.d = paramTJAdUnit;
  }
  
  /* Error */
  public void alert(JSONObject paramJSONObject, final String paramString)
  {
    // Byte code:
    //   0: new 171	java/lang/StringBuilder
    //   3: dup
    //   4: ldc -83
    //   6: invokespecial 176	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   9: astore 5
    //   11: aload 5
    //   13: aload_1
    //   14: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   17: pop
    //   18: ldc 89
    //   20: aload 5
    //   22: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokestatic 186	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   28: aload_1
    //   29: ldc -68
    //   31: invokevirtual 194	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   34: astore 5
    //   36: aload_1
    //   37: ldc -60
    //   39: invokevirtual 194	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   42: astore 6
    //   44: aload_1
    //   45: ldc -58
    //   47: invokevirtual 202	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
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
    //   79: ldc -52
    //   81: astore 5
    //   83: ldc -52
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
    //   101: getstatic 210	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   104: aastore
    //   105: invokevirtual 214	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   108: aload 6
    //   110: invokevirtual 217	java/lang/Exception:printStackTrace	()V
    //   113: aconst_null
    //   114: astore 6
    //   116: new 219	android/app/AlertDialog$Builder
    //   119: dup
    //   120: aload_0
    //   121: getfield 98	com/tapjoy/TJAdUnitJSBridge:c	Landroid/content/Context;
    //   124: invokespecial 222	android/app/AlertDialog$Builder:<init>	(Landroid/content/Context;)V
    //   127: aload 5
    //   129: invokevirtual 226	android/app/AlertDialog$Builder:setTitle	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   132: aload_1
    //   133: invokevirtual 229	android/app/AlertDialog$Builder:setMessage	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   136: invokevirtual 233	android/app/AlertDialog$Builder:create	()Landroid/app/AlertDialog;
    //   139: astore_1
    //   140: aload 6
    //   142: ifnull +147 -> 289
    //   145: aload 6
    //   147: invokevirtual 239	org/json/JSONArray:length	()I
    //   150: ifne +6 -> 156
    //   153: goto +136 -> 289
    //   156: new 241	java/util/ArrayList
    //   159: dup
    //   160: invokespecial 242	java/util/ArrayList:<init>	()V
    //   163: astore 5
    //   165: iconst_0
    //   166: istore 4
    //   168: iload 4
    //   170: aload 6
    //   172: invokevirtual 239	org/json/JSONArray:length	()I
    //   175: if_icmpge +99 -> 274
    //   178: iload 4
    //   180: tableswitch	default:+24->204, 0:+35->215, 1:+29->209
    //   204: iconst_m1
    //   205: istore_3
    //   206: goto +12 -> 218
    //   209: bipush -3
    //   211: istore_3
    //   212: goto +6 -> 218
    //   215: bipush -2
    //   217: istore_3
    //   218: aload 5
    //   220: aload 6
    //   222: iload 4
    //   224: invokevirtual 245	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   227: invokevirtual 249	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   230: pop
    //   231: goto +10 -> 241
    //   234: astore 7
    //   236: aload 7
    //   238: invokevirtual 217	java/lang/Exception:printStackTrace	()V
    //   241: aload_1
    //   242: iload_3
    //   243: aload 5
    //   245: iload 4
    //   247: invokevirtual 253	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   250: checkcast 255	java/lang/CharSequence
    //   253: new 20	com/tapjoy/TJAdUnitJSBridge$5
    //   256: dup
    //   257: aload_0
    //   258: aload_2
    //   259: invokespecial 258	com/tapjoy/TJAdUnitJSBridge$5:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Ljava/lang/String;)V
    //   262: invokevirtual 264	android/app/AlertDialog:setButton	(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V
    //   265: iload 4
    //   267: iconst_1
    //   268: iadd
    //   269: istore 4
    //   271: goto -103 -> 168
    //   274: aload_1
    //   275: iconst_0
    //   276: invokevirtual 268	android/app/AlertDialog:setCancelable	(Z)V
    //   279: aload_1
    //   280: iconst_0
    //   281: invokevirtual 271	android/app/AlertDialog:setCanceledOnTouchOutside	(Z)V
    //   284: aload_1
    //   285: invokevirtual 274	android/app/AlertDialog:show	()V
    //   288: return
    //   289: aload_0
    //   290: aload_2
    //   291: iconst_1
    //   292: anewarray 4	java/lang/Object
    //   295: dup
    //   296: iconst_0
    //   297: getstatic 210	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   300: aastore
    //   301: invokevirtual 214	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   304: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	305	0	this	TJAdUnitJSBridge
    //   0	305	1	paramJSONObject	JSONObject
    //   0	305	2	paramString	String
    //   205	38	3	n	int
    //   166	104	4	i1	int
    //   9	235	5	localObject1	Object
    //   42	179	6	localObject2	Object
    //   50	6	7	localJSONArray	JSONArray
    //   62	6	7	localException1	Exception
    //   85	6	7	str	String
    //   234	3	7	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   44	52	62	java/lang/Exception
    //   36	44	74	java/lang/Exception
    //   28	36	78	java/lang/Exception
    //   218	231	234	java/lang/Exception
  }
  
  public void attachVolumeListener(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      boolean bool = paramJSONObject.getBoolean("attach");
      int n = paramJSONObject.optInt("interval", 500);
      if (n > 0)
      {
        this.d.attachVolumeListener(bool, n);
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
        return;
      }
      paramJSONObject = new StringBuilder("Invalid `interval` value passed to attachVolumeListener(): interval=");
      paramJSONObject.append(n);
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
      Iterator localIterator = this.c.getPackageManager().getInstalledApplications(0).iterator();
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
    if (this.d != null) {
      this.d.clearVideo(new AdUnitAsyncTaskListner()
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
    if (this.d != null)
    {
      this.d.fireContentReady();
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void destroy()
  {
    if ((this.h != null) && (this.g != null))
    {
      this.g.removeUpdates(this.h);
      this.g = null;
      this.h = null;
    }
  }
  
  public void disable()
  {
    this.j = false;
  }
  
  public void dismiss(JSONObject paramJSONObject, String paramString)
  {
    if ((this.c instanceof TJAdUnitActivity))
    {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      ((Activity)this.c).finish();
      return;
    }
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
    catch (Exception localException2)
    {
      boolean bool;
      String str2;
      for (;;) {}
    }
    bool = false;
    try
    {
      String str1 = paramJSONObject.getString("html");
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      str2 = null;
    }
    if (str2 == null)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    if (bool)
    {
      ((Activity)this.c).runOnUiThread(new Runnable()
      {
        public final void run()
        {
          String str2;
          try
          {
            String str1 = paramJSONObject.getString("html");
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            str2 = null;
          }
          if ((TJAdUnitJSBridge.c(TJAdUnitJSBridge.this) != null) && (TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).getParent() != null)) {
            ((ViewGroup)TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).getParent()).removeView(TJAdUnitJSBridge.c(TJAdUnitJSBridge.this));
          }
          MraidView localMraidView = new MraidView(TJAdUnitJSBridge.d(TJAdUnitJSBridge.this));
          TJAdUnitJSBridge.e(TJAdUnitJSBridge.this).getSettings().setJavaScriptEnabled(true);
          localMraidView.setPlacementType(MraidView.PLACEMENT_TYPE.INLINE);
          localMraidView.setLayoutParams(new ViewGroup.LayoutParams(640, 100));
          localMraidView.setInitialScale(100);
          localMraidView.setBackgroundColor(0);
          localMraidView.loadDataWithBaseURL(null, str2, "text/html", "utf-8", null);
          int i = ((WindowManager)((Activity)TJAdUnitJSBridge.d(TJAdUnitJSBridge.this)).getSystemService("window")).getDefaultDisplay().getWidth();
          TJAdUnitJSBridge.a(TJAdUnitJSBridge.this, TapjoyUtil.scaleDisplayAd(localMraidView, i));
          ((Activity)TJAdUnitJSBridge.d(TJAdUnitJSBridge.this)).addContentView(TJAdUnitJSBridge.c(TJAdUnitJSBridge.this), new ViewGroup.LayoutParams(i, (int)(100.0D * (i / 640.0D))));
        }
      });
      return;
    }
    paramJSONObject = new TJPlacementData(TapjoyConnectCore.getHostURL(), str2, paramString);
    paramString = new Intent(this.c, TJAdUnitActivity.class);
    paramString.putExtra("placement_data", paramJSONObject);
    ((Activity)this.c).startActivityForResult(paramString, 327);
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
        this.d.loadVideoUrl(paramJSONObject, new AdUnitAsyncTaskListner()
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
    try
    {
      f1 = Float.valueOf(paramJSONObject.getString("gpsAccuracy")).floatValue();
    }
    catch (Exception localException)
    {
      float f1;
      boolean bool;
      for (;;) {}
    }
    f1 = 100.0F;
    try
    {
      bool = Boolean.valueOf(paramJSONObject.getString("enabled")).booleanValue();
    }
    catch (Exception paramJSONObject)
    {
      paramJSONObject.printStackTrace();
      bool = false;
    }
    this.g = ((LocationManager)this.c.getSystemService("location"));
    paramJSONObject = new Criteria();
    paramJSONObject = this.g.getBestProvider(paramJSONObject, false);
    if (this.h == null) {
      this.h = new LocationListener()
      {
        public final void onLocationChanged(Location paramAnonymousLocation)
        {
          if ((TJAdUnitJSBridge.d(TJAdUnitJSBridge.this) != null) && (TJAdUnitJSBridge.e(TJAdUnitJSBridge.this) != null))
          {
            if (paramAnonymousLocation != null)
            {
              HashMap localHashMap = new HashMap();
              localHashMap.put("lat", Double.valueOf(paramAnonymousLocation.getLatitude()));
              localHashMap.put("long", Double.valueOf(paramAnonymousLocation.getLongitude()));
              localHashMap.put("altitude", Double.valueOf(paramAnonymousLocation.getAltitude()));
              localHashMap.put("timestamp", Long.valueOf(paramAnonymousLocation.getTime()));
              localHashMap.put("speed", Float.valueOf(paramAnonymousLocation.getSpeed()));
              localHashMap.put("course", Float.valueOf(paramAnonymousLocation.getBearing()));
              TJAdUnitJSBridge.this.invokeJSAdunitMethod("locationUpdated", localHashMap);
            }
          }
          else if ((TJAdUnitJSBridge.f(TJAdUnitJSBridge.this) != null) && (TJAdUnitJSBridge.g(TJAdUnitJSBridge.this) != null))
          {
            TapjoyLog.d("TJAdUnitJSBridge", "stopping updates");
            TJAdUnitJSBridge.f(TJAdUnitJSBridge.this).removeUpdates(TJAdUnitJSBridge.g(TJAdUnitJSBridge.this));
            return;
          }
        }
        
        public final void onProviderDisabled(String paramAnonymousString) {}
        
        public final void onProviderEnabled(String paramAnonymousString) {}
        
        public final void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {}
      };
    }
    if (bool)
    {
      if (paramJSONObject != null) {
        this.g.requestLocationUpdates(paramJSONObject, 0L, f1, this.h);
      } else {
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      }
    }
    else if ((this.g != null) && (this.h != null)) {
      this.g.removeUpdates(this.h);
    }
    invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
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
    if (this.d == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
      return null;
    }
    String str = this.d.getVolume();
    boolean bool = this.d.isMuted();
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
  
  public void initMoatVideoTracker(JSONObject paramJSONObject, String paramString)
  {
    if (!(this.c instanceof TJAdUnitActivity))
    {
      TapjoyLog.d("TJAdUnitJSBridge", "Error from initMoatVideoTracker -- not a TJAdUnitActivity");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    try
    {
      paramJSONObject = paramJSONObject.getString("partnerCode");
      this.k = ((ReactiveVideoTracker)MoatFactory.create((TJAdUnitActivity)this.c).createCustomTracker(new ReactiveVideoTrackerPlugin(paramJSONObject)));
      if (this.l == null)
      {
        TapjoyLog.d("TJAdUnitJSBridge", "Initializing MOAT tracking events map");
        this.l = new HashMap();
        this.l.put("firstQuartile", MoatAdEventType.AD_EVT_FIRST_QUARTILE);
        this.l.put("midpoint", MoatAdEventType.AD_EVT_MID_POINT);
        this.l.put("thirdQuartile", MoatAdEventType.AD_EVT_THIRD_QUARTILE);
        this.l.put("complete", MoatAdEventType.AD_EVT_COMPLETE);
        this.l.put("paused", MoatAdEventType.AD_EVT_PAUSED);
        this.l.put("playing", MoatAdEventType.AD_EVT_PLAYING);
        this.l.put("start", MoatAdEventType.AD_EVT_START);
        this.l.put("stopped", MoatAdEventType.AD_EVT_STOPPED);
        this.l.put("skipped", MoatAdEventType.AD_EVT_SKIPPED);
        this.l.put("volumeChanged", MoatAdEventType.AD_EVT_VOLUME_CHANGE);
        this.l.put("enterFullScreen", MoatAdEventType.AD_EVT_ENTER_FULLSCREEN);
        this.l.put("exitFullScreen", MoatAdEventType.AD_EVT_EXIT_FULLSCREEN);
      }
      this.m = new Handler(Looper.getMainLooper());
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      StringBuilder localStringBuilder = new StringBuilder("initMoatVideoTracker exception ");
      localStringBuilder.append(paramJSONObject.toString());
      TapjoyLog.d("TJAdUnitJSBridge", localStringBuilder.toString());
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
    if (cr.c(paramString))
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
    ((Activity)this.c).runOnUiThread(new Runnable()
    {
      public final void run()
      {
        try
        {
          if (Build.VERSION.SDK_INT >= 19)
          {
            TJAdUnitJSBridge.e(TJAdUnitJSBridge.this).evaluateJavascript(paramJSONObject.getString("command"), null);
          }
          else
          {
            WebView localWebView = TJAdUnitJSBridge.e(TJAdUnitJSBridge.this);
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
    if (this.d != null) {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(this.d.pauseVideo()) });
    }
  }
  
  public void playVideo(JSONObject paramJSONObject, String paramString)
  {
    if (this.d != null) {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(this.d.playVideo()) });
    }
  }
  
  /* Error */
  public void present(JSONObject paramJSONObject, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: invokestatic 294	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   4: astore_3
    //   5: aload_1
    //   6: ldc_w 816
    //   9: invokevirtual 194	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   12: invokestatic 585	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   15: astore 5
    //   17: aload_1
    //   18: ldc_w 818
    //   21: invokevirtual 194	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   24: invokestatic 585	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   27: astore 4
    //   29: aload 4
    //   31: astore_3
    //   32: aload_0
    //   33: aload_1
    //   34: ldc_w 819
    //   37: invokevirtual 194	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   40: invokestatic 585	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   43: invokevirtual 588	java/lang/Boolean:booleanValue	()Z
    //   46: putfield 85	com/tapjoy/TJAdUnitJSBridge:customClose	Z
    //   49: new 33	com/tapjoy/TJAdUnitJSBridge$a
    //   52: dup
    //   53: aload_0
    //   54: aload_0
    //   55: getfield 100	com/tapjoy/TJAdUnitJSBridge:e	Landroid/webkit/WebView;
    //   58: invokespecial 822	com/tapjoy/TJAdUnitJSBridge$a:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Landroid/webkit/WebView;)V
    //   61: iconst_2
    //   62: anewarray 206	java/lang/Boolean
    //   65: dup
    //   66: iconst_0
    //   67: aload 5
    //   69: aastore
    //   70: dup
    //   71: iconst_1
    //   72: aload_3
    //   73: aastore
    //   74: invokevirtual 826	com/tapjoy/TJAdUnitJSBridge$a:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   77: pop
    //   78: aload_0
    //   79: aload_2
    //   80: iconst_1
    //   81: anewarray 4	java/lang/Object
    //   84: dup
    //   85: iconst_0
    //   86: getstatic 389	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   89: aastore
    //   90: invokevirtual 214	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   93: return
    //   94: astore_1
    //   95: aload_0
    //   96: aload_2
    //   97: iconst_1
    //   98: anewarray 4	java/lang/Object
    //   101: dup
    //   102: iconst_0
    //   103: getstatic 210	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   106: aastore
    //   107: invokevirtual 214	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   110: aload_1
    //   111: invokevirtual 217	java/lang/Exception:printStackTrace	()V
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
      if (this.d != null)
      {
        this.d.setBackgroundColor(paramJSONObject, new AdUnitAsyncTaskListner()
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
      if (this.d != null)
      {
        this.d.setBackgroundContent(paramJSONObject, new AdUnitAsyncTaskListner()
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
  
  public void setCloseButtonVisible(JSONObject paramJSONObject, String paramString)
  {
    if (!(this.c instanceof TJAdUnitActivity))
    {
      TapjoyLog.d("TJAdUnitJSBridge", "Not a TJAdUnitActivity");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    try
    {
      final boolean bool = paramJSONObject.getBoolean("visible");
      ((Activity)this.c).runOnUiThread(new Runnable()
      {
        public final void run()
        {
          ((TJAdUnitActivity)TJAdUnitJSBridge.d(TJAdUnitJSBridge.this)).setCloseButtonVisibility(bool);
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
  
  public void setContext(Context paramContext)
  {
    this.c = paramContext;
  }
  
  public void setEventPreloadLimit(JSONObject paramJSONObject, String paramString)
  {
    if (TapjoyCache.getInstance() != null) {}
    try
    {
      int n = paramJSONObject.getInt("eventPreloadLimit");
      TJPlacementManager.setCachedPlacementLimit(n);
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
    if (this.d == null)
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
      n = 8;
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        continue;
        int n = 1;
        continue;
        n = 0;
      }
    }
    this.d.setOrientation(n);
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
    return;
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void setPrerenderLimit(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      int n = paramJSONObject.getInt("prerenderLimit");
      TJPlacementManager.setPreRenderedPlacementLimit(n);
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
    for (;;)
    {
      Object localObject;
      try
      {
        bool = paramJSONObject.getBoolean("visible");
      }
      catch (Exception paramJSONObject)
      {
        boolean bool;
        String str;
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        paramJSONObject.printStackTrace();
        return;
      }
      try
      {
        localObject = paramJSONObject.getString("title");
      }
      catch (Exception paramJSONObject)
      {
        continue;
      }
      try
      {
        str = paramJSONObject.getString("message");
        paramJSONObject = (JSONObject)localObject;
        localObject = str;
      }
      catch (Exception paramJSONObject)
      {
        paramJSONObject = (JSONObject)localObject;
      }
    }
    paramJSONObject = "Loading...";
    localObject = "";
    if (bool) {
      this.f = ProgressDialog.show(this.c, paramJSONObject, (CharSequence)localObject);
    } else if (this.f != null) {
      this.f.dismiss();
    }
    invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
  }
  
  public void shouldClose(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      if ((Boolean.valueOf(paramJSONObject.getString("close")).booleanValue()) && ((this.c instanceof Activity))) {
        ((Activity)this.c).finish();
      }
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      ((Activity)this.c).finish();
      paramJSONObject.printStackTrace();
    }
    this.closeRequested = false;
  }
  
  public void startMoatVideoTracker(JSONObject paramJSONObject, final String paramString)
  {
    try
    {
      int n = paramJSONObject.getInt("videoLength");
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
      this.m.post(new Runnable()
      {
        public final void run()
        {
          boolean bool;
          if (TJAdUnitJSBridge.h(TJAdUnitJSBridge.this) != null) {
            bool = TJAdUnitJSBridge.h(TJAdUnitJSBridge.this).trackVideoAd(this.a, this.b, TJAdUnitJSBridge.e(TJAdUnitJSBridge.this));
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
  
  public void triggerEvent(JSONObject paramJSONObject, String paramString)
  {
    if (this.d != null) {}
    try
    {
      paramJSONObject = paramJSONObject.getString("eventName");
      if (paramJSONObject.equals("start"))
      {
        this.d.fireOnVideoStart();
        return;
      }
      if (paramJSONObject.equals("complete"))
      {
        this.d.fireOnVideoComplete();
        return;
      }
      if (!paramJSONObject.equals("error")) {
        break label81;
      }
      this.d.fireOnVideoError("Error while trying to play video.");
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
      int n = paramJSONObject.getInt("currentVideoTime");
      localObject = paramJSONObject.getString("eventName");
      paramJSONObject = null;
      if (this.l != null) {
        paramJSONObject = (MoatAdEventType)this.l.get(localObject);
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
      paramJSONObject = new MoatAdEvent(paramJSONObject, Integer.valueOf(n));
      this.m.post(new Runnable()
      {
        public final void run()
        {
          if (TJAdUnitJSBridge.h(TJAdUnitJSBridge.this) != null) {
            TJAdUnitJSBridge.h(TJAdUnitJSBridge.this).dispatchEvent(paramJSONObject);
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
