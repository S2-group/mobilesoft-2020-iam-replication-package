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
import com.moat.analytics.mobile.tjy.a;
import com.moat.analytics.mobile.tjy.b;
import com.moat.analytics.mobile.tjy.c;
import com.moat.analytics.mobile.tjy.d;
import com.moat.analytics.mobile.tjy.e;
import com.tapjoy.internal.ao;
import com.tapjoy.internal.eu;
import com.tapjoy.internal.eu.1;
import com.tapjoy.internal.eu.2;
import com.tapjoy.internal.eu.3;
import com.tapjoy.internal.ju;
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
  public TJAdUnit a;
  public boolean allowRedirect = true;
  public WebView b;
  final ConcurrentLinkedQueue c = new ConcurrentLinkedQueue();
  public boolean closeRequested = false;
  public boolean customClose = false;
  private TJWebViewJSInterface d;
  public boolean didLaunchOtherActivity = false;
  private TJAdUnitJSBridge e;
  private Context f;
  private TJAdUnitActivity g;
  private TJSplitWebView h;
  private ProgressDialog i;
  private View j = null;
  private boolean k;
  private d l;
  private HashMap m;
  private Handler n;
  private eu o = new eu(this);
  public String otherActivityCallbackID = null;
  public String splitWebViewCallbackID = null;
  
  public TJAdUnitJSBridge(Context paramContext, WebView paramWebView)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "creating AdUnit/JS Bridge");
    this.f = paramContext;
    this.b = paramWebView;
    this.e = this;
    paramContext = this.b;
    if (paramContext == null)
    {
      TapjoyLog.e("TJAdUnitJSBridge", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Cannot create AdUnitJSBridge -- webview is NULL"));
      return;
    }
    this.d = new TJWebViewJSInterface(paramContext, this);
    this.b.addJavascriptInterface(this.d, "AndroidJavascriptInterface");
    setEnabled(true);
  }
  
  public TJAdUnitJSBridge(Context paramContext, TJAdUnit paramTJAdUnit)
  {
    this(paramContext, paramTJAdUnit.getWebView());
    this.a = paramTJAdUnit;
  }
  
  public void alert(JSONObject paramJSONObject, final String paramString)
  {
    Object localObject1 = new StringBuilder("alert_method: ");
    ((StringBuilder)localObject1).append(paramJSONObject);
    TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject1).toString());
    localObject1 = "";
    String str = "";
    Object localObject2 = str;
    try
    {
      localObject3 = paramJSONObject.getString("title");
      localObject1 = localObject3;
      localObject2 = str;
      str = paramJSONObject.getString("message");
      localObject1 = localObject3;
      localObject2 = str;
      paramJSONObject = paramJSONObject.getJSONArray("buttons");
      localObject1 = localObject3;
      localObject2 = str;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
      paramJSONObject = null;
    }
    Object localObject3 = this.g;
    if (localObject3 != null)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        localObject1 = new AlertDialog.Builder((Context)localObject3, 16974394).setTitle((CharSequence)localObject1).setMessage((CharSequence)localObject2).create();
      } else {
        localObject1 = new AlertDialog.Builder((Context)localObject3).setTitle((CharSequence)localObject1).setMessage((CharSequence)localObject2).create();
      }
      if ((paramJSONObject != null) && (paramJSONObject.length() != 0))
      {
        localObject2 = new ArrayList();
        int i2 = 0;
        while (i2 < paramJSONObject.length())
        {
          int i1;
          switch (i2)
          {
          default: 
            i1 = -1;
            break;
          case 1: 
            i1 = -3;
            break;
          case 0: 
            i1 = -2;
          }
          try
          {
            ((ArrayList)localObject2).add(paramJSONObject.getString(i2));
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
          ((AlertDialog)localObject1).setButton(i1, (CharSequence)((ArrayList)localObject2).get(i2), new DialogInterface.OnClickListener()
          {
            public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              switch (paramAnonymousInt)
              {
              default: 
                paramAnonymousInt = 0;
                break;
              case -1: 
                paramAnonymousInt = 2;
                break;
              case -2: 
                paramAnonymousInt = 0;
                break;
              case -3: 
                paramAnonymousInt = 1;
              }
              try
              {
                TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Integer.valueOf(paramAnonymousInt) });
                return;
              }
              catch (Exception paramAnonymousDialogInterface)
              {
                paramAnonymousDialogInterface.printStackTrace();
              }
            }
          });
          i2 += 1;
        }
        ((AlertDialog)localObject1).setCancelable(false);
        ((AlertDialog)localObject1).setCanceledOnTouchOutside(false);
        ((AlertDialog)localObject1).show();
        return;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    TapjoyLog.d("TJAdUnitJSBridge", "Cannot alert -- TJAdUnitActivity is null");
  }
  
  public void attachVolumeListener(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      boolean bool = paramJSONObject.getBoolean("attach");
      int i1 = paramJSONObject.optInt("interval", 500);
      if (i1 > 0)
      {
        this.a.attachVolumeListener(bool, i1);
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
  
  /* Error */
  public void cacheAsset(JSONObject paramJSONObject, String paramString)
  {
    // Byte code:
    //   0: ldc -44
    //   2: astore_3
    //   3: lconst_0
    //   4: invokestatic 346	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   7: astore 4
    //   9: aload_1
    //   10: ldc_w 348
    //   13: invokevirtual 220	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   16: astore 6
    //   18: aload_1
    //   19: ldc_w 350
    //   22: invokevirtual 220	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   25: astore 5
    //   27: aload 5
    //   29: astore_3
    //   30: aload_1
    //   31: ldc_w 352
    //   34: invokevirtual 356	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   37: invokestatic 346	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   40: astore_1
    //   41: invokestatic 362	com/tapjoy/TapjoyCache:getInstance	()Lcom/tapjoy/TapjoyCache;
    //   44: ifnull +29 -> 73
    //   47: aload_0
    //   48: aload_2
    //   49: iconst_1
    //   50: anewarray 4	java/lang/Object
    //   53: dup
    //   54: iconst_0
    //   55: invokestatic 362	com/tapjoy/TapjoyCache:getInstance	()Lcom/tapjoy/TapjoyCache;
    //   58: aload 6
    //   60: aload_3
    //   61: aload_1
    //   62: invokevirtual 366	java/lang/Long:longValue	()J
    //   65: invokevirtual 370	com/tapjoy/TapjoyCache:cacheAssetFromURL	(Ljava/lang/String;Ljava/lang/String;J)Ljava/util/concurrent/Future;
    //   68: aastore
    //   69: invokevirtual 238	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   72: return
    //   73: aload_0
    //   74: aload_2
    //   75: iconst_1
    //   76: anewarray 4	java/lang/Object
    //   79: dup
    //   80: iconst_0
    //   81: getstatic 234	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   84: aastore
    //   85: invokevirtual 238	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   88: return
    //   89: ldc 116
    //   91: ldc_w 372
    //   94: invokestatic 375	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   97: aload_0
    //   98: aload_2
    //   99: iconst_1
    //   100: anewarray 4	java/lang/Object
    //   103: dup
    //   104: iconst_0
    //   105: getstatic 234	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   108: aastore
    //   109: invokevirtual 238	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   112: return
    //   113: astore_1
    //   114: goto -25 -> 89
    //   117: astore 5
    //   119: goto -89 -> 30
    //   122: astore_1
    //   123: aload 4
    //   125: astore_1
    //   126: goto -85 -> 41
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	129	0	this	TJAdUnitJSBridge
    //   0	129	1	paramJSONObject	JSONObject
    //   0	129	2	paramString	String
    //   2	59	3	localObject	Object
    //   7	117	4	localLong	Long
    //   25	3	5	str1	String
    //   117	1	5	localException	Exception
    //   16	43	6	str2	String
    // Exception table:
    //   from	to	target	type
    //   9	18	113	java/lang/Exception
    //   18	27	117	java/lang/Exception
    //   30	41	122	java/lang/Exception
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
    Object localObject = "";
    try
    {
      paramJSONObject = paramJSONObject.getString("bundle");
    }
    catch (Exception paramJSONObject)
    {
      paramJSONObject.printStackTrace();
      paramJSONObject = (JSONObject)localObject;
    }
    if ((paramJSONObject != null) && (paramJSONObject.length() > 0))
    {
      localObject = this.f.getPackageManager().getInstalledApplications(0).iterator();
      while (((Iterator)localObject).hasNext()) {
        if (((ApplicationInfo)((Iterator)localObject).next()).packageName.equals(paramJSONObject))
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
    paramJSONObject = this.a;
    if (paramJSONObject != null) {
      paramJSONObject.clearVideo(new AdUnitAsyncTaskListner()
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
    paramJSONObject = this.a;
    if (paramJSONObject != null)
    {
      paramJSONObject.fireContentReady();
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void destroy() {}
  
  public void dismiss(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject = this.g;
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
        if (TJAdUnitJSBridge.c(TJAdUnitJSBridge.this) != null)
        {
          localObject = paramString;
          if (localObject != null) {
            TJAdUnitJSBridge.this.invokeJSCallback((String)localObject, new Object[] { Boolean.TRUE });
          }
          if (TJAdUnitJSBridge.this.splitWebViewCallbackID != null)
          {
            localObject = TJAdUnitJSBridge.this;
            ((TJAdUnitJSBridge)localObject).invokeJSCallback(((TJAdUnitJSBridge)localObject).splitWebViewCallbackID, new Object[] { Boolean.TRUE });
            TJAdUnitJSBridge.this.splitWebViewCallbackID = null;
          }
          ((ViewGroup)TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).getParent()).removeView(TJAdUnitJSBridge.c(TJAdUnitJSBridge.this));
          TJAdUnitJSBridge.a(TJAdUnitJSBridge.this, null);
          return;
        }
        Object localObject = paramString;
        if (localObject != null) {
          TJAdUnitJSBridge.this.invokeJSCallback((String)localObject, new Object[] { Boolean.FALSE });
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
      ((Activity)this.f).runOnUiThread(new Runnable()
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
          TJAdUnitJSBridge.this.b.getSettings().setJavaScriptEnabled(true);
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
    paramString = this.g;
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
            if (TJAdUnitJSBridge.this.b != null)
            {
              if (TJAdUnitJSBridge.c(TJAdUnitJSBridge.this) == null)
              {
                localObject = TJAdUnitJSBridge.this.b.getParent();
                if ((localObject instanceof ViewGroup))
                {
                  localObject = (ViewGroup)localObject;
                  TJAdUnitJSBridge localTJAdUnitJSBridge = TJAdUnitJSBridge.this;
                  TJAdUnitJSBridge.a(localTJAdUnitJSBridge, new TJSplitWebView(TJAdUnitJSBridge.b(localTJAdUnitJSBridge), localJSONObject, paramJSONObject, TJAdUnitJSBridge.this));
                  ((ViewGroup)localObject).addView(TJAdUnitJSBridge.c(TJAdUnitJSBridge.this), new RelativeLayout.LayoutParams(-1, -1));
                }
              }
              else
              {
                TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).setExitHosts(paramJSONObject);
                TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).applyLayoutOption(localJSONObject);
              }
              if (TJAdUnitJSBridge.c(TJAdUnitJSBridge.this) != null)
              {
                localObject = TJAdUnitJSBridge.this;
                ((TJAdUnitJSBridge)localObject).splitWebViewCallbackID = paramString;
                TJAdUnitJSBridge.c((TJAdUnitJSBridge)localObject).loadUrl(str2);
                return;
              }
            }
            TJAdUnitJSBridge.a(TJAdUnitJSBridge.this, null);
            Object localObject = TJAdUnitJSBridge.this;
            ((TJAdUnitJSBridge)localObject).splitWebViewCallbackID = null;
            ((TJAdUnitJSBridge)localObject).invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
          }
        });
        return;
      }
      this.didLaunchOtherActivity = true;
      this.otherActivityCallbackID = paramString;
      paramJSONObject = new Intent("android.intent.action.VIEW", Uri.parse(str2));
      this.f.startActivity(paramJSONObject);
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
        this.a.loadVideoUrl(paramJSONObject, new AdUnitAsyncTaskListner()
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
      if (this.a != null)
      {
        this.a.endAdContentTracking((String)localObject, paramJSONObject);
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
      Pair localPair = (Pair)this.c.poll();
      if (localPair == null) {
        break;
      }
      onDispatchMethod((String)localPair.first, (JSONObject)localPair.second);
    }
  }
  
  public void flushMessageQueue()
  {
    this.d.flushMessageQueue();
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
    paramJSONObject = this.f.getPackageManager();
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
  
  public void getSplitViewURL(JSONObject paramJSONObject, final String paramString)
  {
    TapjoyUtil.runOnMainThread(new Runnable()
    {
      public final void run()
      {
        if (TJAdUnitJSBridge.c(TJAdUnitJSBridge.this) != null)
        {
          TJAdUnitJSBridge localTJAdUnitJSBridge = TJAdUnitJSBridge.this;
          localTJAdUnitJSBridge.invokeJSCallback(paramString, new Object[] { TJAdUnitJSBridge.c(localTJAdUnitJSBridge).getLastUrl() });
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
    Object localObject1 = this.a;
    if (localObject1 == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
      return null;
    }
    localObject1 = String.format("%.2f", new Object[] { Float.valueOf(((TJAdUnit)localObject1).getVolume()) });
    boolean bool = this.a.isMuted();
    Object localObject2 = new StringBuilder("getVolumeArgs: volume=");
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("; isMuted=");
    ((StringBuilder)localObject2).append(bool);
    TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject2).toString());
    localObject2 = new HashMap();
    ((HashMap)localObject2).put("currentVolume", localObject1);
    ((HashMap)localObject2).put("isMuted", Boolean.valueOf(bool));
    return localObject2;
  }
  
  public void hasSplitView(JSONObject paramJSONObject, final String paramString)
  {
    TapjoyUtil.runOnMainThread(new Runnable()
    {
      public final void run()
      {
        if (TJAdUnitJSBridge.c(TJAdUnitJSBridge.this) != null)
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
    Object localObject = this.g;
    if (localObject == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "Error from initMoatVideoTracker -- TJAdUnitActivity is null");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    try
    {
      paramJSONObject = paramJSONObject.getString("partnerCode");
      this.l = ((d)c.a((Activity)localObject).a(new e(paramJSONObject)));
      if (this.m == null)
      {
        TapjoyLog.d("TJAdUnitJSBridge", "Initializing MOAT tracking events map");
        this.m = new HashMap();
        this.m.put("firstQuartile", b.a);
        this.m.put("midpoint", b.b);
        this.m.put("thirdQuartile", b.c);
        this.m.put("complete", b.d);
        this.m.put("paused", b.e);
        this.m.put("playing", b.f);
        this.m.put("start", b.g);
        this.m.put("stopped", b.h);
        this.m.put("skipped", b.i);
        this.m.put("volumeChanged", b.j);
        this.m.put("enterFullScreen", b.k);
        this.m.put("exitFullScreen", b.l);
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
  
  public void initViewabilityTracker(JSONObject paramJSONObject, String paramString)
  {
    eu localEu = this.o;
    if (!localEu.a(paramJSONObject))
    {
      localEu.a.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    eu.b(paramJSONObject);
    if (ao.a(eu.b))
    {
      localEu.a.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    TapjoyUtil.runOnMainThread(new eu.1(localEu, paramJSONObject, paramString));
  }
  
  public void invokeJSAdunitMethod(String paramString, Map paramMap)
  {
    this.d.callback(paramMap, paramString, null);
  }
  
  public void invokeJSAdunitMethod(String paramString, Object... paramVarArgs)
  {
    paramVarArgs = new ArrayList(Arrays.asList(paramVarArgs));
    this.d.callback(paramVarArgs, paramString, null);
  }
  
  public void invokeJSCallback(String paramString, Map paramMap)
  {
    this.d.callback(paramMap, "", paramString);
  }
  
  public void invokeJSCallback(String paramString, Object... paramVarArgs)
  {
    if (ju.c(paramString))
    {
      TapjoyLog.d("TJAdUnitJSBridge", "invokeJSCallback -- no callbackID provided");
      return;
    }
    paramVarArgs = new ArrayList(Arrays.asList(paramVarArgs));
    this.d.callback(paramVarArgs, "", paramString);
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
            TJAdUnitJSBridge.this.b.evaluateJavascript(paramJSONObject.getString("command"), null);
          }
          else
          {
            WebView localWebView = TJAdUnitJSBridge.this.b;
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
      localObject = null;
      try
      {
        String str = paramJSONObject.optString("callbackId", null);
        localObject = str;
        paramJSONObject = paramJSONObject.getJSONObject("data");
        localObject = str;
        paramString = TJAdUnitJSBridge.class.getMethod(paramString, new Class[] { JSONObject.class, String.class });
        localObject = str;
        StringBuilder localStringBuilder = new StringBuilder("Dispatching method: ");
        localObject = str;
        localStringBuilder.append(paramString);
        localObject = str;
        localStringBuilder.append(" with data=");
        localObject = str;
        localStringBuilder.append(paramJSONObject);
        localObject = str;
        localStringBuilder.append("; callbackID=");
        localObject = str;
        localStringBuilder.append(str);
        localObject = str;
        TapjoyLog.d("TJAdUnitJSBridge", localStringBuilder.toString());
        localObject = str;
        paramString.invoke(this.e, new Object[] { paramJSONObject, str });
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        invokeJSCallback((String)localObject, new Object[] { Boolean.FALSE });
        return;
      }
    }
    Object localObject = new StringBuilder("Bridge currently disabled. Adding ");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(" to message queue");
    TapjoyLog.d("TJAdUnitJSBridge", ((StringBuilder)localObject).toString());
    this.c.add(new Pair(paramString, paramJSONObject));
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
      paramJSONObject = this.f.getPackageManager().getLaunchIntentForPackage(paramJSONObject);
      this.f.startActivity(paramJSONObject);
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
    paramJSONObject = this.a;
    if (paramJSONObject != null) {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramJSONObject.pauseVideo()) });
    }
  }
  
  public void playVideo(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject = this.a;
    if (paramJSONObject != null) {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramJSONObject.playVideo()) });
    }
  }
  
  /* Error */
  public void present(JSONObject paramJSONObject, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: invokestatic 329	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   4: astore_3
    //   5: aload_1
    //   6: ldc_w 929
    //   9: invokevirtual 220	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   12: invokestatic 932	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   15: astore 5
    //   17: aload_1
    //   18: ldc_w 934
    //   21: invokevirtual 220	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   24: invokestatic 932	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   27: astore 4
    //   29: aload 4
    //   31: astore_3
    //   32: aload_0
    //   33: aload_1
    //   34: ldc_w 935
    //   37: invokevirtual 220	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   40: invokestatic 932	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   43: invokevirtual 938	java/lang/Boolean:booleanValue	()Z
    //   46: putfield 98	com/tapjoy/TJAdUnitJSBridge:customClose	Z
    //   49: new 41	com/tapjoy/TJAdUnitJSBridge$a
    //   52: dup
    //   53: aload_0
    //   54: aload_0
    //   55: getfield 127	com/tapjoy/TJAdUnitJSBridge:b	Landroid/webkit/WebView;
    //   58: invokespecial 941	com/tapjoy/TJAdUnitJSBridge$a:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Landroid/webkit/WebView;)V
    //   61: iconst_2
    //   62: anewarray 230	java/lang/Boolean
    //   65: dup
    //   66: iconst_0
    //   67: aload 5
    //   69: aastore
    //   70: dup
    //   71: iconst_1
    //   72: aload_3
    //   73: aastore
    //   74: invokevirtual 945	com/tapjoy/TJAdUnitJSBridge$a:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   77: pop
    //   78: aload_0
    //   79: aload_2
    //   80: iconst_1
    //   81: anewarray 4	java/lang/Object
    //   84: dup
    //   85: iconst_0
    //   86: getstatic 424	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   89: aastore
    //   90: invokevirtual 238	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   93: return
    //   94: astore_1
    //   95: aload_0
    //   96: aload_2
    //   97: iconst_1
    //   98: anewarray 4	java/lang/Object
    //   101: dup
    //   102: iconst_0
    //   103: getstatic 234	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   106: aastore
    //   107: invokevirtual 238	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   110: aload_1
    //   111: invokevirtual 241	java/lang/Exception:printStackTrace	()V
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
      if (this.a != null)
      {
        this.a.sendAdContentTracking((String)localObject, paramJSONObject);
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
    this.g = paramTJAdUnitActivity;
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
      TJAdUnit localTJAdUnit = this.a;
      if (localTJAdUnit != null)
      {
        localTJAdUnit.setBackgroundColor(paramJSONObject, new AdUnitAsyncTaskListner()
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
      TJAdUnit localTJAdUnit = this.a;
      if (localTJAdUnit != null)
      {
        localTJAdUnit.setBackgroundContent(paramJSONObject, new AdUnitAsyncTaskListner()
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
          TJAdUnitActivity localTJAdUnitActivity = TJAdUnitJSBridge.d(TJAdUnitJSBridge.this);
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
          TJAdUnitActivity localTJAdUnitActivity = TJAdUnitJSBridge.d(TJAdUnitJSBridge.this);
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
    if (this.a == null)
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
    this.a.setOrientation(i1);
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
      TJAdUnitActivity localTJAdUnitActivity = this.g;
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
      this.a.a(bool);
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
    TJAdUnitActivity localTJAdUnitActivity = this.g;
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
          if (TJAdUnitJSBridge.e(TJAdUnitJSBridge.this) != null) {
            bool = TJAdUnitJSBridge.e(TJAdUnitJSBridge.this).a(this.a, this.b, TJAdUnitJSBridge.this.b);
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
      if (this.a != null)
      {
        this.a.startAdContentTracking((String)localObject, paramJSONObject);
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
  
  public void startViewabilityTracker(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject = this.o;
    if (!paramJSONObject.c)
    {
      TapjoyLog.d("TJOMViewabilityAgent", "Can not start -- TJOMViewabilityAgent is not initialized");
      paramJSONObject.a.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    paramJSONObject.a.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
    TapjoyUtil.runOnMainThread(new eu.2(paramJSONObject));
  }
  
  public void triggerEvent(JSONObject paramJSONObject, String paramString)
  {
    if (this.a != null) {}
    try
    {
      paramJSONObject = paramJSONObject.getString("eventName");
      if (paramJSONObject.equals("start"))
      {
        this.a.fireOnVideoStart();
        return;
      }
      if (paramJSONObject.equals("complete"))
      {
        this.a.fireOnVideoComplete();
        return;
      }
      if (!paramJSONObject.equals("error")) {
        break label81;
      }
      this.a.fireOnVideoError("Error while trying to play video.");
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
        paramJSONObject = (b)this.m.get(localObject);
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
      paramJSONObject = new a(paramJSONObject, Integer.valueOf(i1));
      this.n.post(new Runnable()
      {
        public final void run()
        {
          if (TJAdUnitJSBridge.e(TJAdUnitJSBridge.this) != null) {
            TJAdUnitJSBridge.e(TJAdUnitJSBridge.this).a(paramJSONObject);
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
  
  public void triggerViewabilityEvent(JSONObject paramJSONObject, String paramString)
  {
    eu localEu = this.o;
    if (!localEu.c)
    {
      TapjoyLog.d("TJOMViewabilityAgent", "Can not triggerEvent -- TJOMViewabilityAgent is not initialized");
      localEu.a.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    if (paramJSONObject == null)
    {
      TapjoyLog.d("TJOMViewabilityAgent", "Can not triggerEvent -- json parameter is null");
      localEu.a.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    paramJSONObject = paramJSONObject.optString("eventName", null);
    if (paramJSONObject == null)
    {
      TapjoyLog.d("TJOMViewabilityAgent", "triggerEvent: params json did not contain 'eventName'");
      localEu.a.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    TapjoyUtil.runOnMainThread(new eu.3(localEu, paramJSONObject, paramString));
  }
  
  public void unsetOrientation(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject = this.a;
    if (paramJSONObject == null)
    {
      TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    try
    {
      paramJSONObject.unsetOrientation();
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
