package com.tapjoy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
    this.jsBridge = new TJWebViewJSInterface(this.webView, new TJAdUnitJSBridge.1(this));
    this.webView.addJavascriptInterface(this.jsBridge, "AndroidJavascriptInterface");
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
  
  public void alert(JSONObject paramJSONObject, String paramString)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "alert_method: " + paramJSONObject);
    Object localObject2 = "";
    for (;;)
    {
      try
      {
        localObject1 = paramJSONObject.getString("title");
        localObject2 = localObject1;
        String str = paramJSONObject.getString("message");
        localObject2 = str;
      }
      catch (Exception paramJSONObject)
      {
        try
        {
          paramJSONObject = paramJSONObject.getJSONArray("buttons");
          localObject1 = new AlertDialog.Builder(this.context).setTitle((CharSequence)localObject1).setMessage((CharSequence)localObject2).create();
          if ((paramJSONObject != null) && (paramJSONObject.length() != 0)) {
            break;
          }
          invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
          return;
        }
        catch (Exception paramJSONObject)
        {
          Object localObject1;
          int j;
          for (;;) {}
        }
        paramJSONObject = paramJSONObject;
        str = "";
        localObject1 = localObject2;
        localObject2 = str;
      }
      tmp131_128[0] = Boolean.FALSE;
      invokeJSCallback(paramString, tmp131_128);
      paramJSONObject.printStackTrace();
      paramJSONObject = null;
    }
    localObject2 = new ArrayList();
    j = 0;
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
            ((AlertDialog)localObject1).setButton(i, (CharSequence)((ArrayList)localObject2).get(j), new TJAdUnitJSBridge.2(this, paramString));
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
        paramJSONObject = null;
      }
    }
  }
  
  public void displayRichMedia(JSONObject paramJSONObject, String paramString)
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
          String str1 = paramJSONObject.getString("html");
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
          ((Activity)this.context).runOnUiThread(new TJAdUnitJSBridge.3(this, paramJSONObject));
          return;
        }
        paramJSONObject = new Intent(this.context, TJAdUnitView.class);
        paramJSONObject.putExtra("tjevent", this.eventData);
        paramJSONObject.putExtra("view_type", 3);
        paramJSONObject.putExtra("html", str2);
        paramJSONObject.putExtra("base_url", TapjoyConnectCore.getHostURL());
        paramJSONObject.putExtra("callback_id", paramString);
        ((Activity)this.context).startActivityForResult(paramJSONObject, 0);
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
    HashMap localHashMap = new HashMap();
    try
    {
      JSONObject localJSONObject = paramJSONObject.getJSONObject("trackingUrls");
      Object localObject;
      if (localJSONObject != null)
      {
        localObject = localJSONObject.keys();
        while (((Iterator)localObject).hasNext())
        {
          String str3 = (String)((Iterator)localObject).next();
          try
          {
            localHashMap.put(str3, localJSONObject.getString(str3));
          }
          catch (Exception localException3)
          {
            TapjoyLog.i("TJAdUnitJSBridge", "no tracking url for " + localJSONObject.getString(str3));
          }
        }
      }
      String str1;
      String str2;
      return;
    }
    catch (Exception localException1)
    {
      TapjoyLog.i("TJAdUnitJSBridge", "no video tracking urls");
      try
      {
        str1 = paramJSONObject.getString("cancelMessage");
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          try
          {
            paramJSONObject = paramJSONObject.getString("url");
            localObject = new Intent(this.context, TapjoyVideoView.class);
            ((Intent)localObject).putExtra("VIDEO_URL", paramJSONObject);
            ((Intent)localObject).putExtra("VIDEO_CANCEL_MESSAGE", str1);
            ((Intent)localObject).putExtra("VIDEO_SHOULD_DISMISS", true);
            ((Intent)localObject).putExtra("callback_id", paramString);
            ((Intent)localObject).putExtra("VIDEO_TRACKING_URLS", localHashMap);
            ((Activity)this.context).startActivityForResult((Intent)localObject, 0);
            return;
          }
          catch (Exception paramJSONObject)
          {
            invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
            paramJSONObject.printStackTrace();
          }
          localException2 = localException2;
          TapjoyLog.w("TJAdUnitJSBridge", "no cancelMessage");
          str2 = "";
        }
      }
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
        this.locationManager = ((LocationManager)this.context.getSystemService("location"));
        paramJSONObject = new Criteria();
        paramJSONObject = this.locationManager.getBestProvider(paramJSONObject, false);
        if (this.locationListener == null) {
          this.locationListener = new TJAdUnitJSBridge.4(this);
        }
        if (bool) {
          if (paramJSONObject != null)
          {
            this.locationManager.requestLocationUpdates(paramJSONObject, 0L, f1, this.locationListener);
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
  
  public void nativeEval(JSONObject paramJSONObject, String paramString)
  {
    ((Activity)this.context).runOnUiThread(new TJAdUnitJSBridge.5(this, paramJSONObject, paramString));
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
    //   1: invokestatic 472	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   4: astore_3
    //   5: aload_1
    //   6: ldc_w 551
    //   9: invokevirtual 201	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   12: invokestatic 496	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   15: astore 5
    //   17: aload_1
    //   18: ldc_w 553
    //   21: invokevirtual 201	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   24: invokestatic 496	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   27: astore 4
    //   29: aload 4
    //   31: astore_3
    //   32: aload_0
    //   33: aload_1
    //   34: ldc_w 554
    //   37: invokevirtual 201	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   40: invokestatic 496	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   43: invokevirtual 475	java/lang/Boolean:booleanValue	()Z
    //   46: putfield 50	com/tapjoy/TJAdUnitJSBridge:customClose	Z
    //   49: new 556	com/tapjoy/TJAdUnitJSBridge$ShowWebView
    //   52: dup
    //   53: aload_0
    //   54: aload_0
    //   55: getfield 68	com/tapjoy/TJAdUnitJSBridge:webView	Landroid/webkit/WebView;
    //   58: invokespecial 559	com/tapjoy/TJAdUnitJSBridge$ShowWebView:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Landroid/webkit/WebView;)V
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
    //   74: invokevirtual 563	com/tapjoy/TJAdUnitJSBridge$ShowWebView:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   77: pop
    //   78: aload_0
    //   79: aload_2
    //   80: iconst_1
    //   81: anewarray 4	java/lang/Object
    //   84: dup
    //   85: iconst_0
    //   86: getstatic 311	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
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
    //   111: invokevirtual 170	java/lang/Exception:printStackTrace	()V
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
  
  public void sendActionCallback(JSONObject paramJSONObject, String paramString)
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
            localTJEventRequest.callback = new TJAdUnitJSBridge.6(this, paramString);
            paramJSONObject = TJEventManager.get(this.eventData.guid);
          } while (paramJSONObject == null);
          paramJSONObject.getCallback().didRequestAction(paramJSONObject, localTJEventRequest);
        }
      }
    }
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
  
  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void setSpinnerVisible(JSONObject paramJSONObject, String paramString)
  {
    localObject1 = "Loading...";
    try
    {
      bool = paramJSONObject.getBoolean("visible");
    }
    catch (Exception paramJSONObject)
    {
      boolean bool;
      label32:
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
      return;
    }
    try
    {
      localObject2 = paramJSONObject.getString("title");
      localObject1 = localObject2;
      paramJSONObject = paramJSONObject.getString("message");
    }
    catch (Exception paramJSONObject)
    {
      paramJSONObject = "";
      localObject2 = localObject1;
      break label32;
    }
    if (bool) {
      this.progressDialog = ProgressDialog.show(this.context, (CharSequence)localObject2, paramJSONObject);
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
  
  public void share(JSONObject paramJSONObject, String paramString)
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
        String str2 = paramJSONObject.getString("network");
        String str1 = paramJSONObject.getString("message");
        localIntent = new Intent("android.intent.action.SEND");
        try
        {
          localObject1 = paramJSONObject.getString("imageURL");
        }
        catch (Exception localException)
        {
          try
          {
            Object localObject1;
            paramJSONObject = paramJSONObject.getString("linkURL");
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
            if (paramJSONObject != null) {
              localObject4 = str1 + "\n" + paramJSONObject;
            }
            localIntent.putExtra("android.intent.extra.TEXT", (String)localObject4);
            if (str2.equals("facebook")) {
              if ((localObject1 != null) && (localObject3 != null))
              {
                localIntent.setType("image/*");
                localIntent.putExtra("android.intent.extra.STREAM", Uri.parse((String)localObject3));
                paramJSONObject = this.context.getPackageManager().queryIntentActivities(localIntent, 0).iterator();
                if (!paramJSONObject.hasNext()) {
                  break label451;
                }
                localObject1 = (ResolveInfo)paramJSONObject.next();
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
          catch (Exception paramJSONObject)
          {
            TapjoyLog.i("TJAdUnitJSBridge", "no linkURL");
            paramJSONObject = null;
            continue;
            localIntent.setType("text/plain");
            continue;
          }
        }
        if (!str2.equals("twitter")) {
          continue;
        }
      }
      catch (Exception paramJSONObject)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        paramJSONObject.printStackTrace();
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
        paramJSONObject = Intent.createChooser(localIntent, "Select");
        ((Activity)this.context).startActivity(paramJSONObject);
        return;
        label451:
        i = 0;
      }
    }
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
}
