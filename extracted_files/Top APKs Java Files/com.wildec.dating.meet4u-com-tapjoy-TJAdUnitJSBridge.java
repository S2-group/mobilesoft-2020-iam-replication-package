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
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import c.a.a;
import c.a.c;
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
import java.util.Locale;
import java.util.Map;

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
      public void dispatchMethod(String paramAnonymousString, c paramAnonymousC)
      {
        if (TJAdUnitJSBridge.this.enabled) {}
        try
        {
          str1 = paramAnonymousC.name("callbackId");
        }
        catch (Exception localException)
        {
          for (;;)
          {
            try
            {
              String str1;
              paramAnonymousC = paramAnonymousC.id("data");
              TJAdUnitJSBridge.class.getMethod(paramAnonymousString, new Class[] { c.class, String.class }).invoke(TJAdUnitJSBridge.this.self, new Object[] { paramAnonymousC, str1 });
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
  
  public void alert(c paramC, final String paramString)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "alert_method: " + paramC);
    Object localObject2 = "";
    for (;;)
    {
      try
      {
        localObject1 = paramC.name("title");
        localObject2 = localObject1;
        String str = paramC.name("message");
        localObject2 = str;
      }
      catch (Exception paramC)
      {
        try
        {
          paramC = paramC.jdMethod_abstract("buttons");
          localObject1 = new AlertDialog.Builder(this.context).setTitle((CharSequence)localObject1).setMessage((CharSequence)localObject2).create();
          if ((paramC != null) && (paramC.login() != 0)) {
            break;
          }
          invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
          return;
        }
        catch (Exception paramC)
        {
          Object localObject1;
          int j;
          for (;;) {}
        }
        paramC = paramC;
        str = "";
        localObject1 = localObject2;
        localObject2 = str;
      }
      tmp131_128[0] = Boolean.FALSE;
      invokeJSCallback(paramString, tmp131_128);
      paramC.printStackTrace();
      paramC = null;
    }
    localObject2 = new ArrayList();
    j = 0;
    for (;;)
    {
      if (j < paramC.login())
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
            ((ArrayList)localObject2).add(paramC.jdMethod_continue(j));
            ((AlertDialog)localObject1).setButton(i, (CharSequence)((ArrayList)localObject2).get(j), new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
              {
                int j = 0;
                int i = j;
                switch (paramAnonymousInt)
                {
                default: 
                  i = j;
                }
                for (;;)
                {
                  try
                  {
                    TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Integer.valueOf(i) });
                    return;
                  }
                  catch (Exception paramAnonymousDialogInterface)
                  {
                    paramAnonymousDialogInterface.printStackTrace();
                  }
                  i = 1;
                  continue;
                  i = 2;
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
  
  public void cacheAsset(c paramC, String paramString)
  {
    Object localObject = "";
    localLong = Long.valueOf(0L);
    try
    {
      str2 = paramC.name("url");
    }
    catch (Exception paramC)
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
        str1 = paramC.name("offerId");
        localObject = str1;
      }
      catch (Exception localException)
      {
        break;
      }
      try
      {
        l = paramC.jdMethod_continue("timeToLive");
        paramC = Long.valueOf(l);
      }
      catch (Exception paramC)
      {
        paramC = localLong;
        break label45;
      }
    }
    if (TapjoyCache.getInstance() != null)
    {
      invokeJSCallback(paramString, new Object[] { TapjoyCache.getInstance().cacheAssetFromURL(str2, (String)localObject, paramC.longValue()) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void cachePathForURL(c paramC, String paramString)
  {
    try
    {
      paramC = paramC.name("url");
      if (TapjoyCache.getInstance() != null)
      {
        invokeJSCallback(paramString, new Object[] { TapjoyCache.getInstance().getPathOfCachedURL(paramC) });
        return;
      }
    }
    catch (Exception paramC)
    {
      invokeJSCallback(paramString, new Object[] { "" });
      return;
    }
    invokeJSCallback(paramString, new Object[] { "" });
  }
  
  public void checkAppInstalled(c paramC, String paramString)
  {
    Object localObject = "";
    try
    {
      paramC = paramC.name("bundle");
      if ((paramC != null) && (paramC.length() > 0))
      {
        localObject = this.context.getPackageManager().getInstalledApplications(0).iterator();
        while (((Iterator)localObject).hasNext()) {
          if (((ApplicationInfo)((Iterator)localObject).next()).packageName.equals(paramC))
          {
            invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
            return;
          }
        }
      }
    }
    catch (Exception paramC)
    {
      for (;;)
      {
        paramC.printStackTrace();
        paramC = (c)localObject;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
    }
  }
  
  public void clearCache(c paramC, String paramString)
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
  
  public void dismiss(c paramC, String paramString)
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
  
  public void displayOffers(c paramC, String paramString)
  {
    try
    {
      paramC = paramC.name("currencyId");
      new TJCOffers(this.context).showOffersWithCurrencyID(paramC, false, this.eventData, paramString, null);
      return;
    }
    catch (Exception paramC)
    {
      for (;;)
      {
        TapjoyLog.w("TJAdUnitJSBridge", "no currencyID for showOfferWall");
        paramC = null;
      }
    }
  }
  
  public void displayRichMedia(final c paramC, String paramString)
  {
    try
    {
      bool = paramC.userId("inline");
    }
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          String str1 = paramC.name("html");
          if (str1 != null) {
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
        boolean bool;
        String str2;
        for (;;)
        {
          localException2.printStackTrace();
          str2 = null;
        }
        if (bool)
        {
          ((Activity)this.context).runOnUiThread(new Runnable()
          {
            public void run()
            {
              try
              {
                String str = paramC.name("html");
                if ((TJAdUnitJSBridge.this.bannerView != null) && (TJAdUnitJSBridge.this.bannerView.getParent() != null)) {
                  ((ViewGroup)TJAdUnitJSBridge.this.bannerView.getParent()).removeView(TJAdUnitJSBridge.this.bannerView);
                }
                MraidView localMraidView = new MraidView(TJAdUnitJSBridge.this.context);
                TJAdUnitJSBridge.this.webView.getSettings().setJavaScriptEnabled(true);
                localMraidView.setPlacementType(MraidView.PLACEMENT_TYPE.INLINE);
                localMraidView.setLayoutParams(new ViewGroup.LayoutParams(640, 100));
                localMraidView.setInitialScale(100);
                localMraidView.setBackgroundColor(0);
                localMraidView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
                int i = ((WindowManager)((Activity)TJAdUnitJSBridge.this.context).getSystemService("window")).getDefaultDisplay().getWidth();
                TJAdUnitJSBridge.access$202(TJAdUnitJSBridge.this, TapjoyUtil.scaleDisplayAd(localMraidView, i));
                ((Activity)TJAdUnitJSBridge.this.context).addContentView(TJAdUnitJSBridge.this.bannerView, new ViewGroup.LayoutParams(i, (int)(100.0D * (i / 640.0D))));
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
        paramC = new Intent(this.context, TJAdUnitView.class);
        paramC.putExtra("tjevent", this.eventData);
        paramC.putExtra("view_type", 3);
        paramC.putExtra("html", str2);
        paramC.putExtra("base_url", TapjoyConnectCore.getHostURL());
        paramC.putExtra("callback_id", paramString);
        ((Activity)this.context).startActivityForResult(paramC, 0);
      }
    }
  }
  
  public void displayStoreURL(c paramC, String paramString)
  {
    displayURL(paramC, paramString);
  }
  
  public void displayURL(c paramC, String paramString)
  {
    try
    {
      paramC = paramC.name("url");
      this.didLaunchOtherActivity = true;
      this.otherActivityCallbackID = paramString;
      paramC = new Intent("android.intent.action.VIEW", Uri.parse(paramC));
      ((Activity)this.context).startActivity(paramC);
      return;
    }
    catch (Exception paramC)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      paramC.printStackTrace();
    }
  }
  
  public void displayVideo(c paramC, String paramString)
  {
    boolean bool = paramC.login("allowBackButton", true);
    HashMap localHashMap = new HashMap();
    try
    {
      c localC = paramC.id("trackingUrls");
      Object localObject1;
      Object localObject2;
      if (localC != null)
      {
        localObject1 = localC.login();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (String)((Iterator)localObject1).next();
          try
          {
            localHashMap.put(localObject2, localC.name((String)localObject2));
          }
          catch (Exception localException3)
          {
            TapjoyLog.i("TJAdUnitJSBridge", "no tracking url for " + localC.name((String)localObject2));
          }
        }
      }
      String str1;
      TapjoyCachedAssetData localTapjoyCachedAssetData;
      String str2;
      return;
    }
    catch (Exception localException1)
    {
      TapjoyLog.i("TJAdUnitJSBridge", "no video tracking urls");
      try
      {
        str1 = paramC.name("cancelMessage");
        localObject1 = paramC.login("videoMessage", "");
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          try
          {
            paramC = paramC.name("url");
            localObject2 = new Intent(this.context, TapjoyVideoView.class);
            if (TapjoyCache.getInstance() != null)
            {
              localTapjoyCachedAssetData = TapjoyCache.getInstance().getCachedDataForURL(paramC);
              if (localTapjoyCachedAssetData != null) {
                ((Intent)localObject2).putExtra("CACHED_VIDEO_LOCATION", localTapjoyCachedAssetData.getLocalFilePath());
              }
            }
            ((Intent)localObject2).putExtra("VIDEO_URL", paramC);
            ((Intent)localObject2).putExtra("VIDEO_MESSAGE", (String)localObject1);
            ((Intent)localObject2).putExtra("VIDEO_CANCEL_MESSAGE", str1);
            ((Intent)localObject2).putExtra("VIDEO_SHOULD_DISMISS", true);
            ((Intent)localObject2).putExtra("callback_id", paramString);
            ((Intent)localObject2).putExtra("VIDEO_TRACKING_URLS", localHashMap);
            ((Intent)localObject2).putExtra("VIDEO_ALLOW_BACK_BUTTON", Boolean.valueOf(bool));
            ((Activity)this.context).startActivityForResult((Intent)localObject2, 0);
            return;
          }
          catch (Exception paramC)
          {
            invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
            paramC.printStackTrace();
          }
          localException2 = localException2;
          TapjoyLog.w("TJAdUnitJSBridge", "no cancelMessage");
          str2 = "";
        }
      }
    }
  }
  
  public void eventOptimizationCallback(c paramC, String paramString)
  {
    try
    {
      String str = paramC.name("token");
      boolean bool = paramC.userId("result");
      TJEventOptimizer.getInstance().eventOptimizationCallback(str, Boolean.valueOf(bool).booleanValue());
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramC)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramC.printStackTrace();
    }
  }
  
  public void getCachedAssets(c paramC, String paramString)
  {
    if (TapjoyCache.getInstance() != null)
    {
      invokeJSCallback(paramString, new Object[] { TapjoyCache.getInstance().cachedAssetsToJSON() });
      return;
    }
    invokeJSCallback(paramString, new Object[] { "" });
  }
  
  public void getLocation(c paramC, String paramString)
  {
    float f1 = 100.0F;
    for (;;)
    {
      try
      {
        float f2 = Float.valueOf(paramC.name("gpsAccuracy")).floatValue();
        f1 = f2;
      }
      catch (Exception localException)
      {
        boolean bool;
        continue;
      }
      try
      {
        bool = Boolean.valueOf(paramC.name("enabled")).booleanValue();
        this.locationManager = ((LocationManager)this.context.getSystemService("location"));
        paramC = new Criteria();
        paramC = this.locationManager.getBestProvider(paramC, false);
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
        if (bool) {
          if (paramC != null)
          {
            this.locationManager.requestLocationUpdates(paramC, 0L, f1, this.locationListener);
            invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
            return;
          }
        }
      }
      catch (Exception paramC)
      {
        paramC.printStackTrace();
        bool = false;
        continue;
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
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
  
  public void log(c paramC, String paramString)
  {
    try
    {
      TapjoyLog.i("TJAdUnitJSBridge", paramC.name("message"));
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramC)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramC.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public void nativeEval(final c paramC, final String paramString)
  {
    ((Activity)this.context).runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          if (Build.VERSION.SDK_INT >= 19) {
            TJAdUnitJSBridge.this.webView.evaluateJavascript(paramC.name("command"), null);
          }
          for (;;)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
            return;
            TJAdUnitJSBridge.this.webView.loadUrl("javascript:" + paramC.name("command"));
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
  
  public void openApp(c paramC, String paramString)
  {
    try
    {
      paramC = paramC.name("bundle");
      paramC = this.context.getPackageManager().getLaunchIntentForPackage(paramC);
      this.context.startActivity(paramC);
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramC)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramC.printStackTrace();
    }
  }
  
  /* Error */
  public void present(c paramC, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: invokestatic 537	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   4: astore_3
    //   5: aload_1
    //   6: ldc_w 629
    //   9: invokevirtual 218	c/a/c:name	(Ljava/lang/String;)Ljava/lang/String;
    //   12: invokestatic 576	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   15: astore 5
    //   17: aload_1
    //   18: ldc_w 631
    //   21: invokevirtual 218	c/a/c:name	(Ljava/lang/String;)Ljava/lang/String;
    //   24: invokestatic 576	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   27: astore 4
    //   29: aload 4
    //   31: astore_3
    //   32: aload_0
    //   33: aload_1
    //   34: ldc_w 632
    //   37: invokevirtual 218	c/a/c:name	(Ljava/lang/String;)Ljava/lang/String;
    //   40: invokestatic 576	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   43: invokevirtual 552	java/lang/Boolean:booleanValue	()Z
    //   46: putfield 65	com/tapjoy/TJAdUnitJSBridge:customClose	Z
    //   49: new 634	com/tapjoy/TJAdUnitJSBridge$ShowWebView
    //   52: dup
    //   53: aload_0
    //   54: aload_0
    //   55: getfield 83	com/tapjoy/TJAdUnitJSBridge:webView	Landroid/webkit/WebView;
    //   58: invokespecial 637	com/tapjoy/TJAdUnitJSBridge$ShowWebView:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Landroid/webkit/WebView;)V
    //   61: iconst_2
    //   62: anewarray 250	java/lang/Boolean
    //   65: dup
    //   66: iconst_0
    //   67: aload 5
    //   69: aastore
    //   70: dup
    //   71: iconst_1
    //   72: aload_3
    //   73: aastore
    //   74: invokevirtual 641	com/tapjoy/TJAdUnitJSBridge$ShowWebView:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   77: pop
    //   78: aload_0
    //   79: aload_2
    //   80: iconst_1
    //   81: anewarray 4	java/lang/Object
    //   84: dup
    //   85: iconst_0
    //   86: getstatic 368	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   89: aastore
    //   90: invokevirtual 258	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   93: return
    //   94: astore_1
    //   95: aload_0
    //   96: aload_2
    //   97: iconst_1
    //   98: anewarray 4	java/lang/Object
    //   101: dup
    //   102: iconst_0
    //   103: getstatic 254	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   106: aastore
    //   107: invokevirtual 258	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   110: aload_1
    //   111: invokevirtual 187	java/lang/Exception:printStackTrace	()V
    //   114: return
    //   115: astore_1
    //   116: goto -67 -> 49
    //   119: astore 4
    //   121: goto -89 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	this	TJAdUnitJSBridge
    //   0	124	1	paramC	c
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
  
  public void removeAssetFromCache(c paramC, String paramString)
  {
    try
    {
      paramC = paramC.name("url");
      if (TapjoyCache.getInstance() != null)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(TapjoyCache.getInstance().removeAssetFromCache(paramC)) });
        return;
      }
    }
    catch (Exception paramC)
    {
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void sendActionCallback(c paramC, final String paramString)
  {
    TJEventRequest localTJEventRequest = new TJEventRequest();
    Object localObject = null;
    try
    {
      String str = paramC.name("type");
      localObject = str;
      return;
    }
    catch (Exception localException2)
    {
      try
      {
        localTJEventRequest.quantity = Integer.valueOf(paramC.name("quantity")).intValue();
      }
      catch (Exception localException2)
      {
        try
        {
          for (;;)
          {
            localTJEventRequest.identifier = paramC.name("identifier");
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
        catch (Exception paramC)
        {
          do
          {
            for (;;)
            {
              paramC.printStackTrace();
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
            paramC = TJEventManager.get(this.eventData.guid);
          } while (paramC == null);
          paramC.getCallback().didRequestAction(paramC, localTJEventRequest);
        }
      }
    }
  }
  
  public void setAllowRedirect(c paramC, String paramString)
  {
    try
    {
      bool = paramC.userId("enabled");
      this.allowRedirect = bool;
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramC)
    {
      for (;;)
      {
        boolean bool = true;
      }
    }
  }
  
  public void setCloseButtonVisible(c paramC, String paramString)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "setCloseButtonVisible_method: " + paramC);
    try
    {
      final boolean bool = paramC.userId("visible");
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
    catch (Exception paramC)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramC.printStackTrace();
    }
  }
  
  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void setEventPreloadLimit(c paramC, String paramString)
  {
    if (TapjoyCache.getInstance() != null) {
      try
      {
        int i = paramC.contactId("eventPreloadLimit");
        TapjoyCache.getInstance().setEventPreloadLimit(i);
        invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
        return;
      }
      catch (Exception paramC)
      {
        TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy cache's event preload limit. Invalid parameters.");
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        return;
      }
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void setSpinnerVisible(c paramC, String paramString)
  {
    localObject1 = "Loading...";
    try
    {
      bool = paramC.userId("visible");
    }
    catch (Exception paramC)
    {
      boolean bool;
      label32:
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramC.printStackTrace();
      return;
    }
    try
    {
      localObject2 = paramC.name("title");
      localObject1 = localObject2;
      paramC = paramC.name("message");
    }
    catch (Exception paramC)
    {
      paramC = "";
      localObject2 = localObject1;
      break label32;
    }
    if (bool) {
      this.progressDialog = ProgressDialog.show(this.context, (CharSequence)localObject2, paramC);
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
  
  public void share(c paramC, String paramString)
  {
    Object localObject4 = null;
    for (;;)
    {
      Intent localIntent;
      Object localObject3;
      int i;
      Object localObject2;
      try
      {
        String str2 = paramC.name("network");
        String str1 = paramC.name("message");
        localIntent = new Intent("android.intent.action.SEND");
        try
        {
          localObject1 = paramC.name("imageURL");
        }
        catch (Exception localException)
        {
          try
          {
            Object localObject1;
            paramC = paramC.name("linkURL");
            localObject3 = localObject4;
            if (localObject1 != null)
            {
              File localFile = downloadFileFromURL((String)localObject1);
              localObject3 = localObject4;
              if (localFile != null) {
                localObject3 = "file://" + localFile.getAbsolutePath();
              }
            }
            localObject4 = str1;
            if (paramC != null) {
              localObject4 = str1 + "\n" + paramC;
            }
            localIntent.putExtra("android.intent.extra.TEXT", (String)localObject4);
            if (str2.equals("facebook")) {
              if ((localObject1 != null) && (localObject3 != null))
              {
                localIntent.setType("image/*");
                localIntent.putExtra("android.intent.extra.STREAM", Uri.parse((String)localObject3));
                paramC = this.context.getPackageManager().queryIntentActivities(localIntent, 0).iterator();
                if (!paramC.hasNext()) {
                  break label451;
                }
                localObject1 = (ResolveInfo)paramC.next();
                if ((!((ResolveInfo)localObject1).activityInfo.packageName.toLowerCase(Locale.ENGLISH).contains(str2)) && (!((ResolveInfo)localObject1).activityInfo.name.toLowerCase(Locale.ENGLISH).contains(str2))) {
                  continue;
                }
                localIntent.setPackage(((ResolveInfo)localObject1).activityInfo.packageName);
                i = 1;
                if (i != 0) {
                  break label420;
                }
                invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
                return;
                localException = localException;
                TapjoyLog.i("TJAdUnitJSBridge", "no imageURL");
                localObject2 = null;
                continue;
              }
            }
          }
          catch (Exception paramC)
          {
            TapjoyLog.i("TJAdUnitJSBridge", "no linkURL");
            paramC = null;
            continue;
            localIntent.setType("text/plain");
            continue;
          }
        }
        if (!str2.equals("twitter")) {
          continue;
        }
      }
      catch (Exception paramC)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        paramC.printStackTrace();
        return;
      }
      localIntent.setType("*/*");
      if ((localObject2 != null) && (localObject3 != null))
      {
        localIntent.putExtra("android.intent.extra.STREAM", Uri.parse((String)localObject3));
        continue;
        label420:
        this.didLaunchOtherActivity = true;
        this.otherActivityCallbackID = paramString;
        paramC = Intent.createChooser(localIntent, "Select");
        ((Activity)this.context).startActivity(paramC);
        return;
        label451:
        i = 0;
      }
    }
  }
  
  public void shouldClose(c paramC, String paramString)
  {
    try
    {
      if ((Boolean.valueOf(paramC.name("close")).booleanValue()) && ((this.context instanceof TJAdUnitView))) {
        ((Activity)this.context).finish();
      }
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
    }
    catch (Exception paramC)
    {
      for (;;)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        ((Activity)this.context).finish();
        paramC.printStackTrace();
      }
    }
    this.shouldClose = false;
  }
  
  public void triggerEvent(c paramC, String paramString)
  {
    do
    {
      try
      {
        paramC = paramC.name("eventName");
        if (paramC.equals("start"))
        {
          TapjoyVideo.videoNotifierStart();
          return;
        }
      }
      catch (Exception paramC)
      {
        TapjoyLog.w("TJAdUnitJSBridge", "Unable to triggerEvent. No event name.");
        return;
      }
      if (paramC.equals("complete"))
      {
        TapjoyVideo.videoNotifierComplete();
        return;
      }
    } while (!paramC.equals("error"));
    TapjoyVideo.videoNotifierError(3);
  }
}
