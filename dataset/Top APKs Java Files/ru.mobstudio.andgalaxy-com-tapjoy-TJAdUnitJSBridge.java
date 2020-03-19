package com.tapjoy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.moat.analytics.mobile.tjy.MoatAdEventType;
import com.moat.analytics.mobile.tjy.MoatFactory;
import com.moat.analytics.mobile.tjy.ReactiveVideoTracker;
import com.moat.analytics.mobile.tjy.ReactiveVideoTrackerPlugin;
import com.tapjoy.internal.ct;
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
  private ProgressDialog h;
  private LocationManager i;
  private LocationListener j;
  private View k = null;
  private boolean l;
  private ReactiveVideoTracker m;
  private HashMap n;
  private Handler o;
  public String otherActivityCallbackID = null;
  
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
  public void alert(JSONObject paramJSONObject, String paramString)
  {
    // Byte code:
    //   0: ldc 69
    //   2: ldc -105
    //   4: aload_1
    //   5: invokestatic 157	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   8: invokevirtual 161	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   11: invokestatic 163	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   14: aload_1
    //   15: ldc -91
    //   17: invokevirtual 170	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   20: astore 5
    //   22: aload_1
    //   23: ldc -84
    //   25: invokevirtual 170	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   28: astore 6
    //   30: aload_1
    //   31: ldc -82
    //   33: invokevirtual 178	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   36: astore 7
    //   38: aload 6
    //   40: astore_1
    //   41: aload 7
    //   43: astore 6
    //   45: goto +57 -> 102
    //   48: astore 7
    //   50: aload 6
    //   52: astore_1
    //   53: aload 7
    //   55: astore 6
    //   57: goto +22 -> 79
    //   60: astore_1
    //   61: goto +8 -> 69
    //   64: astore_1
    //   65: ldc -76
    //   67: astore 5
    //   69: ldc -76
    //   71: astore 7
    //   73: aload_1
    //   74: astore 6
    //   76: aload 7
    //   78: astore_1
    //   79: aload_0
    //   80: aload_2
    //   81: iconst_1
    //   82: anewarray 4	java/lang/Object
    //   85: dup
    //   86: iconst_0
    //   87: getstatic 186	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   90: aastore
    //   91: invokevirtual 190	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   94: aload 6
    //   96: invokevirtual 193	java/lang/Exception:printStackTrace	()V
    //   99: aconst_null
    //   100: astore 6
    //   102: aload_0
    //   103: getfield 142	com/tapjoy/TJAdUnitJSBridge:e	Lcom/tapjoy/TJAdUnitActivity;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +187 -> 297
    //   113: new 195	android/app/AlertDialog$Builder
    //   116: dup
    //   117: aload 7
    //   119: invokespecial 198	android/app/AlertDialog$Builder:<init>	(Landroid/content/Context;)V
    //   122: aload 5
    //   124: invokevirtual 202	android/app/AlertDialog$Builder:setTitle	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   127: aload_1
    //   128: invokevirtual 205	android/app/AlertDialog$Builder:setMessage	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   131: invokevirtual 209	android/app/AlertDialog$Builder:create	()Landroid/app/AlertDialog;
    //   134: astore_1
    //   135: aload 6
    //   137: ifnull +144 -> 281
    //   140: aload 6
    //   142: invokevirtual 215	org/json/JSONArray:length	()I
    //   145: ifne +6 -> 151
    //   148: goto +133 -> 281
    //   151: new 217	java/util/ArrayList
    //   154: dup
    //   155: invokespecial 218	java/util/ArrayList:<init>	()V
    //   158: astore 5
    //   160: iconst_0
    //   161: istore 4
    //   163: iload 4
    //   165: aload 6
    //   167: invokevirtual 215	org/json/JSONArray:length	()I
    //   170: if_icmpge +96 -> 266
    //   173: iload 4
    //   175: tableswitch	default:+21->196, 0:+32->207, 1:+26->201
    //   196: iconst_m1
    //   197: istore_3
    //   198: goto +12 -> 210
    //   201: bipush -3
    //   203: istore_3
    //   204: goto +6 -> 210
    //   207: bipush -2
    //   209: istore_3
    //   210: aload 5
    //   212: aload 6
    //   214: iload 4
    //   216: invokevirtual 221	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   219: invokevirtual 225	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   222: pop
    //   223: goto +10 -> 233
    //   226: astore 7
    //   228: aload 7
    //   230: invokevirtual 193	java/lang/Exception:printStackTrace	()V
    //   233: aload_1
    //   234: iload_3
    //   235: aload 5
    //   237: iload 4
    //   239: invokevirtual 229	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   242: checkcast 231	java/lang/CharSequence
    //   245: new 233	com/tapjoy/TJAdUnitJSBridge$1
    //   248: dup
    //   249: aload_0
    //   250: aload_2
    //   251: invokespecial 236	com/tapjoy/TJAdUnitJSBridge$1:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Ljava/lang/String;)V
    //   254: invokevirtual 242	android/app/AlertDialog:setButton	(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V
    //   257: iload 4
    //   259: iconst_1
    //   260: iadd
    //   261: istore 4
    //   263: goto -100 -> 163
    //   266: aload_1
    //   267: iconst_0
    //   268: invokevirtual 245	android/app/AlertDialog:setCancelable	(Z)V
    //   271: aload_1
    //   272: iconst_0
    //   273: invokevirtual 248	android/app/AlertDialog:setCanceledOnTouchOutside	(Z)V
    //   276: aload_1
    //   277: invokevirtual 251	android/app/AlertDialog:show	()V
    //   280: return
    //   281: aload_0
    //   282: aload_2
    //   283: iconst_1
    //   284: anewarray 4	java/lang/Object
    //   287: dup
    //   288: iconst_0
    //   289: getstatic 186	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   292: aastore
    //   293: invokevirtual 190	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   296: return
    //   297: ldc 69
    //   299: ldc -3
    //   301: invokestatic 163	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   304: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	305	0	this	TJAdUnitJSBridge
    //   0	305	1	paramJSONObject	JSONObject
    //   0	305	2	paramString	String
    //   197	38	3	i1	int
    //   161	101	4	i2	int
    //   20	216	5	localObject1	Object
    //   28	185	6	localObject2	Object
    //   36	6	7	localJSONArray	JSONArray
    //   48	6	7	localException1	Exception
    //   71	47	7	localObject3	Object
    //   226	3	7	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   30	38	48	java/lang/Exception
    //   22	30	60	java/lang/Exception
    //   14	22	64	java/lang/Exception
    //   210	223	226	java/lang/Exception
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
        invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
        return;
      }
      TapjoyLog.d("TJAdUnitJSBridge", "Invalid `interval` value passed to attachVolumeListener(): interval=".concat(String.valueOf(i1)));
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      StringBuilder localStringBuilder = new StringBuilder("attachVolumeListener exception ");
      localStringBuilder.append(paramJSONObject.toString());
      TapjoyLog.d("TJAdUnitJSBridge", localStringBuilder.toString());
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
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
  
  public void clearVideo(JSONObject paramJSONObject, String paramString)
  {
    if (this.f != null) {
      this.f.clearVideo(new TJAdUnitJSBridge.9(this, paramString));
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
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void destroy()
  {
    if ((this.j != null) && (this.i != null))
    {
      this.i.removeUpdates(this.j);
      this.i = null;
      this.j = null;
    }
  }
  
  public void dismiss(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject = this.e;
    if (paramJSONObject != null)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      paramJSONObject.finish();
      return;
    }
    TapjoyLog.d("TJAdUnitJSBridge", "Cannot dismiss -- TJAdUnitActivity is null");
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void display()
  {
    invokeJSAdunitMethod("display", new Object[0]);
  }
  
  public void displayRichMedia(JSONObject paramJSONObject, String paramString)
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
      ((Activity)this.d).runOnUiThread(new TJAdUnitJSBridge.5(this, paramJSONObject));
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
  
  public void displayURL(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("url");
      this.didLaunchOtherActivity = true;
      this.otherActivityCallbackID = paramString;
      paramJSONObject = new Intent("android.intent.action.VIEW", Uri.parse(paramJSONObject));
      ((Activity)this.d).startActivity(paramJSONObject);
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void displayVideo(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("url");
      if ((paramJSONObject.length() > 0) && (paramJSONObject != ""))
      {
        this.f.loadVideoUrl(paramJSONObject, new TJAdUnitJSBridge.8(this, paramString));
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
      String str = paramJSONObject.getString("name");
      if (str.isEmpty())
      {
        TapjoyLog.d("TJAdUnitJSBridge", "Empty name for endUsageTrackingEvent");
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        return;
      }
      if (this.f != null)
      {
        this.f.endAdContentTracking(str, paramJSONObject);
        invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
        return;
      }
    }
    catch (JSONException paramJSONObject)
    {
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to endUsageTrackingEvent. Invalid parameters: ".concat(String.valueOf(paramJSONObject)));
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
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
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
      HashMap localHashMap;
      String str;
      if ((((ApplicationInfo)localObject2).flags & 0x1) != 1)
      {
        localHashMap = new HashMap();
        str = ((ApplicationInfo)localObject2).packageName;
        localHashMap.put("packageName", str);
        localHashMap.put("targetSdk", Integer.valueOf(((ApplicationInfo)localObject2).targetSdkVersion));
      }
      try
      {
        localObject2 = paramJSONObject.getPackageInfo(str, 4096);
        localHashMap.put("installTime", new Date(((PackageInfo)localObject2).firstInstallTime));
        localHashMap.put("updateTime", new Date(((PackageInfo)localObject2).lastUpdateTime));
        localHashMap.put("versionName", ((PackageInfo)localObject2).versionName);
        localHashMap.put("versionCode", Integer.valueOf(((PackageInfo)localObject2).versionCode));
        localJSONArray.put(new JSONObject(localHashMap));
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    invokeJSCallback(paramString, new Object[] { localJSONArray });
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
    this.i = ((LocationManager)this.d.getSystemService("location"));
    paramJSONObject = new Criteria();
    paramJSONObject = this.i.getBestProvider(paramJSONObject, false);
    if (this.j == null) {
      this.j = new TJAdUnitJSBridge.10(this);
    }
    if (bool)
    {
      if (paramJSONObject != null) {
        this.i.requestLocationUpdates(paramJSONObject, 0L, f1, this.j);
      } else {
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      }
    }
    else if ((this.i != null) && (this.j != null)) {
      this.i.removeUpdates(this.j);
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
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
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
  
  public void initMoatVideoTracker(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = this.e;
    if (localObject == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "Error from initMoatVideoTracker -- TJAdUnitActivity is null");
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    try
    {
      paramJSONObject = paramJSONObject.getString("partnerCode");
      this.m = ((ReactiveVideoTracker)MoatFactory.create((Activity)localObject).createCustomTracker(new ReactiveVideoTrackerPlugin(paramJSONObject)));
      if (this.n == null)
      {
        TapjoyLog.d("TJAdUnitJSBridge", "Initializing MOAT tracking events map");
        this.n = new HashMap();
        this.n.put("firstQuartile", MoatAdEventType.AD_EVT_FIRST_QUARTILE);
        this.n.put("midpoint", MoatAdEventType.AD_EVT_MID_POINT);
        this.n.put("thirdQuartile", MoatAdEventType.AD_EVT_THIRD_QUARTILE);
        this.n.put("complete", MoatAdEventType.AD_EVT_COMPLETE);
        this.n.put("paused", MoatAdEventType.AD_EVT_PAUSED);
        this.n.put("playing", MoatAdEventType.AD_EVT_PLAYING);
        this.n.put("start", MoatAdEventType.AD_EVT_START);
        this.n.put("stopped", MoatAdEventType.AD_EVT_STOPPED);
        this.n.put("skipped", MoatAdEventType.AD_EVT_SKIPPED);
        this.n.put("volumeChanged", MoatAdEventType.AD_EVT_VOLUME_CHANGE);
        this.n.put("enterFullScreen", MoatAdEventType.AD_EVT_ENTER_FULLSCREEN);
        this.n.put("exitFullScreen", MoatAdEventType.AD_EVT_EXIT_FULLSCREEN);
      }
      this.o = new Handler(Looper.getMainLooper());
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      localObject = new StringBuilder("initMoatVideoTracker exception ");
      ((StringBuilder)localObject).append(paramJSONObject.toString());
      TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
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
  public void nativeEval(JSONObject paramJSONObject, String paramString)
  {
    TapjoyUtil.runOnMainThread(new TJAdUnitJSBridge.11(this, paramJSONObject, paramString));
  }
  
  public void onDispatchMethod(String paramString, JSONObject paramJSONObject)
  {
    if (this.l)
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
    //   0: getstatic 186	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   3: astore_3
    //   4: getstatic 186	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   7: astore_3
    //   8: aload_1
    //   9: ldc_w 894
    //   12: invokevirtual 170	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   15: invokestatic 606	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   18: astore 5
    //   20: aload_1
    //   21: ldc_w 896
    //   24: invokevirtual 170	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   27: invokestatic 606	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   30: astore 4
    //   32: aload 4
    //   34: astore_3
    //   35: aload_0
    //   36: aload_1
    //   37: ldc_w 897
    //   40: invokevirtual 170	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   43: invokestatic 606	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   46: invokevirtual 609	java/lang/Boolean:booleanValue	()Z
    //   49: putfield 60	com/tapjoy/TJAdUnitJSBridge:customClose	Z
    //   52: new 899	com/tapjoy/TJAdUnitJSBridge$a
    //   55: dup
    //   56: aload_0
    //   57: aload_0
    //   58: getfield 80	com/tapjoy/TJAdUnitJSBridge:g	Landroid/webkit/WebView;
    //   61: invokespecial 902	com/tapjoy/TJAdUnitJSBridge$a:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Landroid/webkit/WebView;)V
    //   64: iconst_2
    //   65: anewarray 182	java/lang/Boolean
    //   68: dup
    //   69: iconst_0
    //   70: aload 5
    //   72: aastore
    //   73: dup
    //   74: iconst_1
    //   75: aload_3
    //   76: aastore
    //   77: invokevirtual 906	com/tapjoy/TJAdUnitJSBridge$a:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   80: pop
    //   81: aload_0
    //   82: aload_2
    //   83: iconst_1
    //   84: anewarray 4	java/lang/Object
    //   87: dup
    //   88: iconst_0
    //   89: getstatic 272	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   92: aastore
    //   93: invokevirtual 190	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   96: return
    //   97: astore_1
    //   98: aload_0
    //   99: aload_2
    //   100: iconst_1
    //   101: anewarray 4	java/lang/Object
    //   104: dup
    //   105: iconst_0
    //   106: getstatic 186	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   109: aastore
    //   110: invokevirtual 190	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   113: aload_1
    //   114: invokevirtual 193	java/lang/Exception:printStackTrace	()V
    //   117: return
    //   118: astore 4
    //   120: goto -85 -> 35
    //   123: astore_1
    //   124: goto -72 -> 52
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	127	0	this	TJAdUnitJSBridge
    //   0	127	1	paramJSONObject	JSONObject
    //   0	127	2	paramString	String
    //   3	73	3	localObject	Object
    //   30	3	4	localBoolean1	Boolean
    //   118	1	4	localException	Exception
    //   18	53	5	localBoolean2	Boolean
    // Exception table:
    //   from	to	target	type
    //   0	20	97	java/lang/Exception
    //   52	96	97	java/lang/Exception
    //   20	32	118	java/lang/Exception
    //   35	52	123	java/lang/Exception
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
      String str = paramJSONObject.getString("name");
      if (str.isEmpty())
      {
        TapjoyLog.d("TJAdUnitJSBridge", "Empty name for sendUsageTrackingEvent");
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        return;
      }
      if (this.f != null)
      {
        this.f.sendAdContentTracking(str, paramJSONObject);
        invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
        return;
      }
    }
    catch (JSONException paramJSONObject)
    {
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to sendUsageTrackingEvent. Invalid parameters: ".concat(String.valueOf(paramJSONObject)));
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
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
  
  public void setBackgroundColor(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("backgroundColor");
      if (this.f != null)
      {
        this.f.setBackgroundColor(paramJSONObject, new TJAdUnitJSBridge.6(this, paramString));
        return;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;) {}
    }
    TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background color. Invalid parameters.");
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void setBackgroundWebViewContent(JSONObject paramJSONObject, String paramString)
  {
    TapjoyLog.d("TJAdUnitJSBridge", "setBackgroundWebViewContent");
    try
    {
      paramJSONObject = paramJSONObject.getString("backgroundContent");
      if (this.f != null)
      {
        this.f.setBackgroundContent(paramJSONObject, new TJAdUnitJSBridge.7(this, paramString));
        return;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;) {}
    }
    TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background content. Invalid parameters.");
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void setCloseButtonClickable(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      TapjoyUtil.runOnMainThread(new TJAdUnitJSBridge.2(this, paramJSONObject.optBoolean("clickable")));
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void setCloseButtonVisible(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      TapjoyUtil.runOnMainThread(new TJAdUnitJSBridge.12(this, paramJSONObject.getBoolean("visible")));
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    this.l = paramBoolean;
    if (this.l) {
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
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void setOrientation(JSONObject paramJSONObject, String paramString)
  {
    if (this.f == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    try
    {
      paramJSONObject = paramJSONObject.getString("orientation");
      if ((paramJSONObject.equals("landscape")) || (paramJSONObject.equals("landscapeLeft"))) {
        break label127;
      }
      if (!paramJSONObject.equals("landscapeRight")) {
        break label122;
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
    invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
    return;
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
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
          this.h = ProgressDialog.show(localTJAdUnitActivity, str, paramJSONObject);
        } else if (this.h != null) {
          this.h.dismiss();
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
  
  public void shouldClose(JSONObject paramJSONObject, String paramString)
  {
    TJAdUnitActivity localTJAdUnitActivity = this.e;
    try
    {
      Boolean localBoolean = Boolean.FALSE;
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
  
  public void startMoatVideoTracker(JSONObject paramJSONObject, String paramString)
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
      this.o.post(new TJAdUnitJSBridge.3(this, (Map)localObject, Integer.valueOf(i1), paramString));
      return;
    }
    catch (Exception paramJSONObject)
    {
      Object localObject = new StringBuilder("startMoatVideoTracker exception ");
      ((StringBuilder)localObject).append(paramJSONObject.toString());
      TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
    }
  }
  
  public void startUsageTrackingEvent(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      String str = paramJSONObject.getString("name");
      if (str.isEmpty())
      {
        TapjoyLog.d("TJAdUnitJSBridge", "Empty name for startUsageTrackingEvent");
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        return;
      }
      if (this.f != null)
      {
        this.f.startAdContentTracking(str, paramJSONObject);
        invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
        return;
      }
    }
    catch (JSONException paramJSONObject)
    {
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to startUsageTrackingEvent. Invalid parameters: ".concat(String.valueOf(paramJSONObject)));
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
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
  
  public void triggerMoatVideoEvent(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      int i1 = paramJSONObject.getInt("currentVideoTime");
      localObject = paramJSONObject.getString("eventName");
      paramJSONObject = null;
      if (this.n != null) {
        paramJSONObject = (MoatAdEventType)this.n.get(localObject);
      }
      if (paramJSONObject == null)
      {
        paramJSONObject = new StringBuilder("eventName:");
        paramJSONObject.append((String)localObject);
        paramJSONObject.append(" has no matching MOAT event");
        TapjoyLog.d("TJAdUnitJSBridge", paramJSONObject.toString());
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        return;
      }
      TapjoyLog.d("TJAdUnitJSBridge", "Sending MOAT event: ".concat(String.valueOf(paramJSONObject)));
      paramJSONObject = new MoatAdEvent(paramJSONObject, Integer.valueOf(i1));
      this.o.post(new TJAdUnitJSBridge.4(this, paramJSONObject));
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      Object localObject = new StringBuilder("triggerMoatVideoEvent exception ");
      ((StringBuilder)localObject).append(paramJSONObject.toString());
      TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
    }
  }
}
