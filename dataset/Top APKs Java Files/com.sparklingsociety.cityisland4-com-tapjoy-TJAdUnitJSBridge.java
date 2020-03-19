package com.tapjoy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
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
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.tapjoy.mraid.view.MraidView;
import com.tapjoy.mraid.view.MraidView.PLACEMENT_TYPE;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class TJAdUnitJSBridge
{
  private static final String TAG = "TJAdUnitJSBridge";
  public boolean allowRedirect = true;
  private View bannerView = null;
  private Context context;
  public boolean customClose = false;
  public boolean didLaunchOtherActivity = false;
  private boolean enabled;
  private TJEventData eventData;
  private TJWebViewJSInterface jsBridge;
  private LocationListener locationListener;
  private LocationManager locationManager;
  public String otherActivityCallbackID = null;
  private ProgressDialog progressDialog;
  private TJAdUnitJSBridge self;
  public boolean shouldClose = false;
  private WebView webView;
  
  public TJAdUnitJSBridge(Context paramContext, WebView paramWebView, TJEventData paramTJEventData)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "creating AdUnit/JS Bridge");
    this.context = paramContext;
    this.eventData = paramTJEventData;
    this.self = this;
    this.webView = paramWebView;
    if (this.webView == null)
    {
      TapjoyLog.e("TJAdUnitJSBridge", "Error: webView is NULL");
      return;
    }
    this.jsBridge = new TJWebViewJSInterface(this.webView, new TJWebViewJSInterfaceNotifier()
    {
      public void dispatchMethod(String paramAnonymousString, JSONObject paramAnonymousJSONObject)
      {
        Object localObject;
        if (TJAdUnitJSBridge.this.enabled) {
          localObject = null;
        }
        for (;;)
        {
          try
          {
            String str = paramAnonymousJSONObject.getString("callbackId");
            localObject = str;
          }
          catch (Exception localException)
          {
            continue;
          }
          try
          {
            paramAnonymousJSONObject = paramAnonymousJSONObject.getJSONObject("data");
            TJAdUnitJSBridge.class.getMethod(paramAnonymousString, new Class[] { JSONObject.class, String.class }).invoke(TJAdUnitJSBridge.this.self, new Object[] { paramAnonymousJSONObject, localObject });
            return;
          }
          catch (Exception paramAnonymousString)
          {
            paramAnonymousString.printStackTrace();
            TJAdUnitJSBridge.this.invokeJSCallback(localObject, new Object[] { Boolean.FALSE });
            return;
          }
        }
      }
    });
    this.webView.addJavascriptInterface(this.jsBridge, "AndroidJavascriptInterface");
    this.enabled = true;
  }
  
  @SuppressLint({"WorldReadableFiles"})
  private File downloadFileFromURL(String paramString)
  {
    String str = paramString.substring(paramString.lastIndexOf('.'));
    FileOutputStream localFileOutputStream;
    try
    {
      paramString = new URL(paramString).openStream();
      localFileOutputStream = this.context.openFileOutput("share_temp" + str, 1);
      byte[] arrayOfByte = new byte['က'];
      for (;;)
      {
        int i = paramString.read(arrayOfByte, 0, arrayOfByte.length);
        if (i < 0) {
          break;
        }
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
      return new File(this.context.getFilesDir(), "share_temp" + str);
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    for (;;)
    {
      try
      {
        localFileOutputStream.close();
        paramString.close();
      }
      catch (Exception paramString) {}
    }
  }
  
  public void alert(JSONObject paramJSONObject, final String paramString)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "alert_method: " + paramJSONObject);
    Object localObject2 = "";
    String str2 = "";
    Object localObject3 = null;
    Object localObject1 = str2;
    try
    {
      String str1 = paramJSONObject.getString("title");
      localObject1 = str2;
      localObject2 = str1;
      str2 = paramJSONObject.getString("message");
      localObject1 = str2;
      localObject2 = str1;
      paramJSONObject = paramJSONObject.getJSONArray("buttons");
      localObject2 = str1;
      localObject1 = str2;
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        paramJSONObject.printStackTrace();
        paramJSONObject = localObject3;
      }
      localObject2 = new ArrayList();
      int j = 0;
      for (;;)
      {
        if (j < paramJSONObject.length())
        {
          int i;
          switch (j)
          {
          default: 
            i = -1;
          }
          try
          {
            for (;;)
            {
              ((ArrayList)localObject2).add(paramJSONObject.getString(j));
              ((AlertDialog)localObject1).setButton(i, (CharSequence)((ArrayList)localObject2).get(j), new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                {
                  int i = 0;
                  switch (paramAnonymousInt)
                  {
                  default: 
                    paramAnonymousInt = i;
                  }
                  for (;;)
                  {
                    try
                    {
                      TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Integer.valueOf(paramAnonymousInt) });
                      return;
                    }
                    catch (Exception paramAnonymousDialogInterface)
                    {
                      paramAnonymousDialogInterface.printStackTrace();
                    }
                    paramAnonymousInt = 0;
                    continue;
                    paramAnonymousInt = 1;
                    continue;
                    paramAnonymousInt = 2;
                  }
                }
              });
              j += 1;
              break;
              i = -2;
              continue;
              i = -3;
            }
          }
          catch (Exception localException)
          {
            for (;;)
            {
              localException.printStackTrace();
            }
          }
        }
      }
      ((AlertDialog)localObject1).show();
    }
    localObject1 = new AlertDialog.Builder(this.context).setTitle((CharSequence)localObject2).setMessage((CharSequence)localObject1).create();
    if ((paramJSONObject == null) || (paramJSONObject.length() == 0))
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
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
      long l;
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
        l = paramJSONObject.getLong("timeToLive");
        paramJSONObject = Long.valueOf(l);
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
        localObject = this.context.getPackageManager().getInstalledApplications(0).iterator();
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
  
  public void closeRequested()
  {
    this.shouldClose = true;
    invokeJSAdunitMethod("closeRequested", new Object[0]);
  }
  
  public void destroy()
  {
    if ((this.locationListener != null) && (this.locationManager != null))
    {
      this.locationManager.removeUpdates(this.locationListener);
      this.locationManager = null;
      this.locationListener = null;
    }
  }
  
  public void disable()
  {
    this.enabled = false;
  }
  
  public void dismiss(JSONObject paramJSONObject, String paramString)
  {
    if ((this.context instanceof TJAdUnitView))
    {
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      ((Activity)this.context).finish();
    }
  }
  
  public void display()
  {
    invokeJSAdunitMethod("display", new Object[0]);
  }
  
  public void displayOffers(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = null;
    try
    {
      paramJSONObject = paramJSONObject.getString("currencyId");
      new TJCOffers(this.context).showOffersWithCurrencyID(paramJSONObject, false, this.eventData, paramString, null);
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        TapjoyLog.w("TJAdUnitJSBridge", "no currencyID for showOfferWall");
        paramJSONObject = localObject;
      }
    }
  }
  
  public void displayRichMedia(final JSONObject paramJSONObject, String paramString)
  {
    i = 0;
    localObject = null;
    for (;;)
    {
      try
      {
        boolean bool = paramJSONObject.getBoolean("inline");
        i = bool;
      }
      catch (Exception localException2)
      {
        String str;
        continue;
      }
      try
      {
        str = paramJSONObject.getString("html");
        localObject = str;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        continue;
        if (i == 0) {
          continue;
        }
        ((Activity)this.context).runOnUiThread(new Runnable()
        {
          public void run()
          {
            Object localObject1 = null;
            try
            {
              localObject2 = paramJSONObject.getString("html");
              localObject1 = localObject2;
            }
            catch (Exception localException)
            {
              for (;;)
              {
                Object localObject2;
                int i;
                localException.printStackTrace();
              }
            }
            if ((TJAdUnitJSBridge.this.bannerView != null) && (TJAdUnitJSBridge.this.bannerView.getParent() != null)) {
              ((ViewGroup)TJAdUnitJSBridge.this.bannerView.getParent()).removeView(TJAdUnitJSBridge.this.bannerView);
            }
            localObject2 = new MraidView(TJAdUnitJSBridge.this.context);
            TJAdUnitJSBridge.this.webView.getSettings().setJavaScriptEnabled(true);
            ((MraidView)localObject2).setPlacementType(MraidView.PLACEMENT_TYPE.INLINE);
            ((MraidView)localObject2).setLayoutParams(new ViewGroup.LayoutParams(640, 100));
            ((MraidView)localObject2).setInitialScale(100);
            ((MraidView)localObject2).setBackgroundColor(0);
            ((MraidView)localObject2).loadDataWithBaseURL(null, localObject1, "text/html", "utf-8", null);
            i = ((WindowManager)((Activity)TJAdUnitJSBridge.this.context).getSystemService("window")).getDefaultDisplay().getWidth();
            TJAdUnitJSBridge.access$202(TJAdUnitJSBridge.this, TapjoyUtil.scaleDisplayAd((View)localObject2, i));
            ((Activity)TJAdUnitJSBridge.this.context).addContentView(TJAdUnitJSBridge.this.bannerView, new ViewGroup.LayoutParams(i, (int)(100.0D * (i / 640.0D))));
          }
        });
        return;
        paramJSONObject = new Intent(this.context, TJAdUnitView.class);
        paramJSONObject.putExtra("tjevent", this.eventData);
        paramJSONObject.putExtra("view_type", 3);
        paramJSONObject.putExtra("html", localObject);
        paramJSONObject.putExtra("base_url", TapjoyConnectCore.getHostURL());
        paramJSONObject.putExtra("callback_id", paramString);
        ((Activity)this.context).startActivityForResult(paramJSONObject, 0);
        return;
      }
    }
    if (localObject == null)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
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
      ((Activity)this.context).startActivity(paramJSONObject);
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
    localObject1 = "";
    bool = paramJSONObject.optBoolean("allowBackButton", true);
    localHashMap = new HashMap();
    try
    {
      JSONObject localJSONObject = paramJSONObject.getJSONObject("trackingUrls");
      Object localObject2;
      Object localObject3;
      if (localJSONObject != null)
      {
        localObject2 = localJSONObject.keys();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (String)((Iterator)localObject2).next();
          try
          {
            localHashMap.put(localObject3, localJSONObject.getString((String)localObject3));
          }
          catch (Exception localException3)
          {
            TapjoyLog.i("TJAdUnitJSBridge", "no tracking url for " + localJSONObject.getString((String)localObject3));
          }
        }
      }
      String str;
      return;
    }
    catch (Exception localException1)
    {
      TapjoyLog.i("TJAdUnitJSBridge", "no video tracking urls");
      try
      {
        str = paramJSONObject.getString("cancelMessage");
        localObject1 = str;
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          try
          {
            paramJSONObject = paramJSONObject.getString("url");
            localObject2 = new Intent(this.context, TapjoyVideoView.class);
            if (TapjoyCache.getInstance() != null)
            {
              localObject3 = TapjoyCache.getInstance().getCachedDataForURL(paramJSONObject);
              if (localObject3 != null) {
                ((Intent)localObject2).putExtra("CACHED_VIDEO_LOCATION", ((TapjoyCachedAssetData)localObject3).getLocalFilePath());
              }
            }
            ((Intent)localObject2).putExtra("VIDEO_URL", paramJSONObject);
            ((Intent)localObject2).putExtra("VIDEO_MESSAGE", str);
            ((Intent)localObject2).putExtra("VIDEO_CANCEL_MESSAGE", (String)localObject1);
            ((Intent)localObject2).putExtra("VIDEO_SHOULD_DISMISS", true);
            ((Intent)localObject2).putExtra("callback_id", paramString);
            ((Intent)localObject2).putExtra("VIDEO_TRACKING_URLS", localHashMap);
            ((Intent)localObject2).putExtra("VIDEO_ALLOW_BACK_BUTTON", Boolean.valueOf(bool));
            ((Activity)this.context).startActivityForResult((Intent)localObject2, 0);
            return;
          }
          catch (Exception paramJSONObject)
          {
            invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
            paramJSONObject.printStackTrace();
          }
          localException2 = localException2;
          TapjoyLog.w("TJAdUnitJSBridge", "no cancelMessage");
        }
      }
      str = paramJSONObject.optString("videoMessage", "");
    }
  }
  
  public void eventOptimizationCallback(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      String str = paramJSONObject.getString("token");
      boolean bool = paramJSONObject.getBoolean("result");
      TJEventOptimizer.getInstance().eventOptimizationCallback(str, Boolean.valueOf(bool).booleanValue());
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
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
  
  public void getLocation(JSONObject paramJSONObject, String paramString)
  {
    float f1 = 100.0F;
    int i = 0;
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
        i = bool;
      }
      catch (Exception paramJSONObject)
      {
        paramJSONObject.printStackTrace();
        continue;
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        return;
      }
    }
    this.locationManager = ((LocationManager)this.context.getSystemService("location"));
    paramJSONObject = new Criteria();
    paramJSONObject = this.locationManager.getBestProvider(paramJSONObject, false);
    if (this.locationListener == null) {
      this.locationListener = new LocationListener()
      {
        public void onLocationChanged(Location paramAnonymousLocation)
        {
          if ((TJAdUnitJSBridge.this.context == null) || (TJAdUnitJSBridge.this.webView == null)) {
            if ((TJAdUnitJSBridge.this.locationManager != null) && (TJAdUnitJSBridge.this.locationListener != null))
            {
              TapjoyLog.i("TJAdUnitJSBridge", "stopping updates");
              TJAdUnitJSBridge.this.locationManager.removeUpdates(TJAdUnitJSBridge.this.locationListener);
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
        
        public void onProviderDisabled(String paramAnonymousString) {}
        
        public void onProviderEnabled(String paramAnonymousString) {}
        
        public void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {}
      };
    }
    if (i != 0) {
      if (paramJSONObject != null)
      {
        this.locationManager.requestLocationUpdates(paramJSONObject, 0L, f1, this.locationListener);
        invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
        return;
      }
    }
    if ((this.locationManager != null) && (this.locationListener != null)) {
      this.locationManager.removeUpdates(this.locationListener);
    }
    invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
  }
  
  public void invokeJSAdunitMethod(String paramString, Map<String, Object> paramMap)
  {
    this.jsBridge.callback(paramMap, paramString, null);
  }
  
  public void invokeJSAdunitMethod(String paramString, Object... paramVarArgs)
  {
    paramVarArgs = new ArrayList(Arrays.asList(paramVarArgs));
    this.jsBridge.callback(paramVarArgs, paramString, null);
  }
  
  public void invokeJSCallback(String paramString, Map<String, Object> paramMap)
  {
    this.jsBridge.callback(paramMap, "", paramString);
  }
  
  public void invokeJSCallback(String paramString, Object... paramVarArgs)
  {
    paramVarArgs = new ArrayList(Arrays.asList(paramVarArgs));
    this.jsBridge.callback(paramVarArgs, "", paramString);
  }
  
  public void log(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      TapjoyLog.i("TJAdUnitJSBridge", paramJSONObject.getString("message"));
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
    ((Activity)this.context).runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          if (Build.VERSION.SDK_INT >= 19) {
            TJAdUnitJSBridge.this.webView.evaluateJavascript(paramJSONObject.getString("command"), null);
          }
          for (;;)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
            return;
            TJAdUnitJSBridge.this.webView.loadUrl("javascript:" + paramJSONObject.getString("command"));
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
  
  public void openApp(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("bundle");
      paramJSONObject = this.context.getPackageManager().getLaunchIntentForPackage(paramJSONObject);
      this.context.startActivity(paramJSONObject);
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  /* Error */
  public void present(JSONObject paramJSONObject, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: invokestatic 543	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   4: astore_3
    //   5: aload_1
    //   6: ldc_w 635
    //   9: invokevirtual 223	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   12: invokestatic 582	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   15: astore 5
    //   17: aload_1
    //   18: ldc_w 637
    //   21: invokevirtual 223	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   24: invokestatic 582	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   27: astore 4
    //   29: aload 4
    //   31: astore_3
    //   32: aload_0
    //   33: aload_1
    //   34: ldc_w 638
    //   37: invokevirtual 223	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   40: invokestatic 582	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   43: invokevirtual 558	java/lang/Boolean:booleanValue	()Z
    //   46: putfield 70	com/tapjoy/TJAdUnitJSBridge:customClose	Z
    //   49: new 20	com/tapjoy/TJAdUnitJSBridge$ShowWebView
    //   52: dup
    //   53: aload_0
    //   54: aload_0
    //   55: getfield 88	com/tapjoy/TJAdUnitJSBridge:webView	Landroid/webkit/WebView;
    //   58: invokespecial 641	com/tapjoy/TJAdUnitJSBridge$ShowWebView:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Landroid/webkit/WebView;)V
    //   61: iconst_2
    //   62: anewarray 255	java/lang/Boolean
    //   65: dup
    //   66: iconst_0
    //   67: aload 5
    //   69: aastore
    //   70: dup
    //   71: iconst_1
    //   72: aload_3
    //   73: aastore
    //   74: invokevirtual 645	com/tapjoy/TJAdUnitJSBridge$ShowWebView:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   77: pop
    //   78: aload_0
    //   79: aload_2
    //   80: iconst_1
    //   81: anewarray 4	java/lang/Object
    //   84: dup
    //   85: iconst_0
    //   86: getstatic 371	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   89: aastore
    //   90: invokevirtual 263	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   93: return
    //   94: astore_1
    //   95: aload_0
    //   96: aload_2
    //   97: iconst_1
    //   98: anewarray 4	java/lang/Object
    //   101: dup
    //   102: iconst_0
    //   103: getstatic 259	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   106: aastore
    //   107: invokevirtual 263	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   110: aload_1
    //   111: invokevirtual 192	java/lang/Exception:printStackTrace	()V
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
  
  public void sendActionCallback(JSONObject paramJSONObject, final String paramString)
  {
    TJEventRequest localTJEventRequest = new TJEventRequest();
    Object localObject = null;
    try
    {
      String str = paramJSONObject.getString("type");
      localObject = str;
      return;
    }
    catch (Exception localException2)
    {
      try
      {
        localTJEventRequest.quantity = Integer.valueOf(paramJSONObject.getString("quantity")).intValue();
      }
      catch (Exception localException2)
      {
        try
        {
          for (;;)
          {
            localTJEventRequest.identifier = paramJSONObject.getString("identifier");
            if ((localObject != null) && (localTJEventRequest.identifier != null)) {
              break;
            }
            TapjoyLog.i("TJAdUnitJSBridge", "sendActionCallback: null type or identifier");
            invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
            return;
            localException1 = localException1;
            localException1.printStackTrace();
            continue;
            localException2 = localException2;
            localException2.printStackTrace();
          }
        }
        catch (Exception paramJSONObject)
        {
          do
          {
            for (;;)
            {
              paramJSONObject.printStackTrace();
            }
            if (localObject.equals("currency")) {
              localTJEventRequest.type = 3;
            }
            while (localTJEventRequest.type == 0)
            {
              TapjoyLog.i("TJAdUnitJSBridge", "unknown type: " + localObject);
              invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
              return;
              if (localObject.equals("inAppPurchase")) {
                localTJEventRequest.type = 1;
              } else if (localObject.equals("navigation")) {
                localTJEventRequest.type = 4;
              } else if (localObject.equals("virtualGood")) {
                localTJEventRequest.type = 2;
              }
            }
            localTJEventRequest.callback = new TJEventRequestCallback()
            {
              public void cancelled()
              {
                TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
              }
              
              public void completed()
              {
                TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
              }
            };
            paramJSONObject = TJEventManager.get(this.eventData.guid);
          } while (paramJSONObject == null);
          paramJSONObject.getCallback().didRequestAction(paramJSONObject, localTJEventRequest);
        }
      }
    }
  }
  
  public void setAllowRedirect(JSONObject paramJSONObject, String paramString)
  {
    boolean bool1 = true;
    try
    {
      boolean bool2 = paramJSONObject.getBoolean("enabled");
      bool1 = bool2;
    }
    catch (Exception paramJSONObject)
    {
      for (;;) {}
    }
    this.allowRedirect = bool1;
    invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
  }
  
  public void setCloseButtonVisible(JSONObject paramJSONObject, String paramString)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "setCloseButtonVisible_method: " + paramJSONObject);
    try
    {
      final boolean bool = paramJSONObject.getBoolean("visible");
      ((Activity)this.context).runOnUiThread(new Runnable()
      {
        public void run()
        {
          ((TJAdUnitView)TJAdUnitJSBridge.this.context).setCloseButtonVisibility(bool);
        }
      });
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void setEventPreloadLimit(JSONObject paramJSONObject, String paramString)
  {
    if (TapjoyCache.getInstance() != null) {
      try
      {
        int i = paramJSONObject.getInt("eventPreloadLimit");
        TapjoyCache.getInstance().setEventPreloadLimit(i);
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
  
  public void setSpinnerVisible(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = "Loading...";
    str2 = "";
    try
    {
      bool = paramJSONObject.getBoolean("visible");
    }
    catch (Exception paramJSONObject)
    {
      boolean bool;
      String str1;
      label40:
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
      return;
    }
    try
    {
      str1 = paramJSONObject.getString("title");
      localObject = str1;
      paramJSONObject = paramJSONObject.getString("message");
      localObject = str1;
    }
    catch (Exception paramJSONObject)
    {
      paramJSONObject = str2;
      break label40;
    }
    if (bool) {
      this.progressDialog = ProgressDialog.show(this.context, (CharSequence)localObject, paramJSONObject);
    }
    for (;;)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
      if (this.progressDialog != null) {
        this.progressDialog.dismiss();
      }
    }
  }
  
  /* Error */
  public void share(JSONObject paramJSONObject, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 746
    //   4: invokevirtual 223	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   7: astore 10
    //   9: aload_1
    //   10: ldc -31
    //   12: invokevirtual 223	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   15: astore 8
    //   17: aconst_null
    //   18: astore 5
    //   20: aconst_null
    //   21: astore 6
    //   23: aconst_null
    //   24: astore 7
    //   26: iconst_0
    //   27: istore 4
    //   29: new 428	android/content/Intent
    //   32: dup
    //   33: ldc_w 748
    //   36: invokespecial 749	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   39: astore 11
    //   41: aload_1
    //   42: ldc_w 751
    //   45: invokevirtual 223	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   48: astore 9
    //   50: aload 9
    //   52: astore 5
    //   54: aload_1
    //   55: ldc_w 753
    //   58: invokevirtual 223	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   61: astore_1
    //   62: aload 7
    //   64: astore 6
    //   66: aload 5
    //   68: ifnull +46 -> 114
    //   71: aload_0
    //   72: aload 5
    //   74: invokespecial 755	com/tapjoy/TJAdUnitJSBridge:downloadFileFromURL	(Ljava/lang/String;)Ljava/io/File;
    //   77: astore 9
    //   79: aload 7
    //   81: astore 6
    //   83: aload 9
    //   85: ifnull +29 -> 114
    //   88: new 160	java/lang/StringBuilder
    //   91: dup
    //   92: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   95: ldc_w 757
    //   98: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: aload 9
    //   103: invokevirtual 760	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   106: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: invokevirtual 171	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: astore 6
    //   114: aload 8
    //   116: astore 7
    //   118: aload_1
    //   119: ifnull +30 -> 149
    //   122: new 160	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   129: aload 8
    //   131: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: ldc_w 762
    //   137: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload_1
    //   141: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 171	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: astore 7
    //   149: aload 11
    //   151: ldc_w 764
    //   154: aload 7
    //   156: invokevirtual 445	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   159: pop
    //   160: aload 10
    //   162: ldc_w 766
    //   165: invokevirtual 368	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   168: ifeq +219 -> 387
    //   171: aload 5
    //   173: ifnull +202 -> 375
    //   176: aload 6
    //   178: ifnull +197 -> 375
    //   181: aload 11
    //   183: ldc_w 768
    //   186: invokevirtual 771	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   189: pop
    //   190: aload 11
    //   192: ldc_w 773
    //   195: aload 6
    //   197: invokestatic 470	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   200: invokevirtual 776	android/content/Intent:putExtra	(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   203: pop
    //   204: aload_0
    //   205: getfield 82	com/tapjoy/TJAdUnitJSBridge:context	Landroid/content/Context;
    //   208: invokevirtual 338	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   211: aload 11
    //   213: iconst_0
    //   214: invokevirtual 780	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   217: invokeinterface 350 1 0
    //   222: astore_1
    //   223: iload 4
    //   225: istore_3
    //   226: aload_1
    //   227: invokeinterface 356 1 0
    //   232: ifeq +74 -> 306
    //   235: aload_1
    //   236: invokeinterface 360 1 0
    //   241: checkcast 782	android/content/pm/ResolveInfo
    //   244: astore 5
    //   246: aload 5
    //   248: getfield 786	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   251: getfield 789	android/content/pm/ActivityInfo:packageName	Ljava/lang/String;
    //   254: getstatic 795	java/util/Locale:ENGLISH	Ljava/util/Locale;
    //   257: invokevirtual 799	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   260: aload 10
    //   262: invokevirtual 803	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   265: ifne +25 -> 290
    //   268: aload 5
    //   270: getfield 786	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   273: getfield 806	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   276: getstatic 795	java/util/Locale:ENGLISH	Ljava/util/Locale;
    //   279: invokevirtual 799	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   282: aload 10
    //   284: invokevirtual 803	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   287: ifeq -64 -> 223
    //   290: aload 11
    //   292: aload 5
    //   294: getfield 786	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   297: getfield 789	android/content/pm/ActivityInfo:packageName	Ljava/lang/String;
    //   300: invokevirtual 809	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   303: pop
    //   304: iconst_1
    //   305: istore_3
    //   306: iload_3
    //   307: ifne +127 -> 434
    //   310: aload_0
    //   311: aload_2
    //   312: iconst_1
    //   313: anewarray 4	java/lang/Object
    //   316: dup
    //   317: iconst_0
    //   318: getstatic 259	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   321: aastore
    //   322: invokevirtual 263	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   325: return
    //   326: astore 9
    //   328: ldc 30
    //   330: ldc_w 811
    //   333: invokestatic 80	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   336: goto -282 -> 54
    //   339: astore_1
    //   340: aload_0
    //   341: aload_2
    //   342: iconst_1
    //   343: anewarray 4	java/lang/Object
    //   346: dup
    //   347: iconst_0
    //   348: getstatic 259	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   351: aastore
    //   352: invokevirtual 263	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   355: aload_1
    //   356: invokevirtual 192	java/lang/Exception:printStackTrace	()V
    //   359: return
    //   360: astore_1
    //   361: ldc 30
    //   363: ldc_w 813
    //   366: invokestatic 80	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   369: aload 6
    //   371: astore_1
    //   372: goto -310 -> 62
    //   375: aload 11
    //   377: ldc_w 815
    //   380: invokevirtual 771	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   383: pop
    //   384: goto -180 -> 204
    //   387: aload 10
    //   389: ldc_w 817
    //   392: invokevirtual 368	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   395: ifeq -191 -> 204
    //   398: aload 11
    //   400: ldc_w 819
    //   403: invokevirtual 771	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   406: pop
    //   407: aload 5
    //   409: ifnull -205 -> 204
    //   412: aload 6
    //   414: ifnull -210 -> 204
    //   417: aload 11
    //   419: ldc_w 773
    //   422: aload 6
    //   424: invokestatic 470	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   427: invokevirtual 776	android/content/Intent:putExtra	(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   430: pop
    //   431: goto -227 -> 204
    //   434: aload_0
    //   435: iconst_1
    //   436: putfield 64	com/tapjoy/TJAdUnitJSBridge:didLaunchOtherActivity	Z
    //   439: aload_0
    //   440: aload_2
    //   441: putfield 68	com/tapjoy/TJAdUnitJSBridge:otherActivityCallbackID	Ljava/lang/String;
    //   444: aload 11
    //   446: ldc_w 821
    //   449: invokestatic 825	android/content/Intent:createChooser	(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;
    //   452: astore_1
    //   453: aload_0
    //   454: getfield 82	com/tapjoy/TJAdUnitJSBridge:context	Landroid/content/Context;
    //   457: checkcast 393	android/app/Activity
    //   460: aload_1
    //   461: invokevirtual 477	android/app/Activity:startActivity	(Landroid/content/Intent;)V
    //   464: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	465	0	this	TJAdUnitJSBridge
    //   0	465	1	paramJSONObject	JSONObject
    //   0	465	2	paramString	String
    //   225	82	3	i	int
    //   27	197	4	j	int
    //   18	390	5	localObject1	Object
    //   21	402	6	localObject2	Object
    //   24	131	7	str1	String
    //   15	115	8	str2	String
    //   48	54	9	localObject3	Object
    //   326	1	9	localException	Exception
    //   7	381	10	str3	String
    //   39	406	11	localIntent	Intent
    // Exception table:
    //   from	to	target	type
    //   41	50	326	java/lang/Exception
    //   0	17	339	java/lang/Exception
    //   29	41	339	java/lang/Exception
    //   71	79	339	java/lang/Exception
    //   88	114	339	java/lang/Exception
    //   122	149	339	java/lang/Exception
    //   149	171	339	java/lang/Exception
    //   181	204	339	java/lang/Exception
    //   204	223	339	java/lang/Exception
    //   226	290	339	java/lang/Exception
    //   290	304	339	java/lang/Exception
    //   310	325	339	java/lang/Exception
    //   328	336	339	java/lang/Exception
    //   361	369	339	java/lang/Exception
    //   375	384	339	java/lang/Exception
    //   387	407	339	java/lang/Exception
    //   417	431	339	java/lang/Exception
    //   434	464	339	java/lang/Exception
    //   54	62	360	java/lang/Exception
  }
  
  public void shouldClose(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      if ((Boolean.valueOf(paramJSONObject.getString("close")).booleanValue()) && ((this.context instanceof TJAdUnitView))) {
        ((Activity)this.context).finish();
      }
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        ((Activity)this.context).finish();
        paramJSONObject.printStackTrace();
      }
    }
    this.shouldClose = false;
  }
  
  public void triggerEvent(JSONObject paramJSONObject, String paramString)
  {
    do
    {
      try
      {
        paramJSONObject = paramJSONObject.getString("eventName");
        if (paramJSONObject.equals("start"))
        {
          TapjoyVideo.videoNotifierStart();
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
        TapjoyVideo.videoNotifierComplete();
        return;
      }
    } while (!paramJSONObject.equals("error"));
    TapjoyVideo.videoNotifierError(3);
  }
  
  @TargetApi(11)
  private class ShowWebView
    extends AsyncTask<Boolean, Void, Boolean[]>
  {
    WebView webView;
    
    public ShowWebView(WebView paramWebView)
    {
      this.webView = paramWebView;
    }
    
    protected Boolean[] doInBackground(Boolean... paramVarArgs)
    {
      return paramVarArgs;
    }
    
    protected void onPostExecute(Boolean[] paramArrayOfBoolean)
    {
      final boolean bool1 = paramArrayOfBoolean[0].booleanValue();
      final boolean bool2 = paramArrayOfBoolean[1].booleanValue();
      ((Activity)TJAdUnitJSBridge.this.context).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (bool1)
          {
            TJAdUnitJSBridge.ShowWebView.this.webView.setVisibility(0);
            if (bool2)
            {
              if ((TJAdUnitJSBridge.ShowWebView.this.webView.getParent() instanceof RelativeLayout))
              {
                ((RelativeLayout)TJAdUnitJSBridge.ShowWebView.this.webView.getParent()).getBackground().setAlpha(0);
                ((RelativeLayout)TJAdUnitJSBridge.ShowWebView.this.webView.getParent()).setBackgroundColor(0);
              }
              if (Build.VERSION.SDK_INT >= 11) {
                TJAdUnitJSBridge.ShowWebView.this.webView.setLayerType(1, null);
              }
            }
          }
          do
          {
            do
            {
              return;
              if ((TJAdUnitJSBridge.ShowWebView.this.webView.getParent() instanceof RelativeLayout))
              {
                ((RelativeLayout)TJAdUnitJSBridge.ShowWebView.this.webView.getParent()).getBackground().setAlpha(255);
                ((RelativeLayout)TJAdUnitJSBridge.ShowWebView.this.webView.getParent()).setBackgroundColor(-1);
              }
            } while (Build.VERSION.SDK_INT < 11);
            TJAdUnitJSBridge.ShowWebView.this.webView.setLayerType(0, null);
            return;
            TJAdUnitJSBridge.ShowWebView.this.webView.setVisibility(4);
          } while (!(TJAdUnitJSBridge.ShowWebView.this.webView.getParent() instanceof RelativeLayout));
          ((RelativeLayout)TJAdUnitJSBridge.ShowWebView.this.webView.getParent()).getBackground().setAlpha(0);
          ((RelativeLayout)TJAdUnitJSBridge.ShowWebView.this.webView.getParent()).setBackgroundColor(0);
        }
      });
    }
  }
}
