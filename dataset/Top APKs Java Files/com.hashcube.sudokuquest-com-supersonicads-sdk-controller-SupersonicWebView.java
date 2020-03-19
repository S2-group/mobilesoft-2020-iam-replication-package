package com.supersonicads.sdk.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.MutableContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.ConsoleMessage;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;
import com.supersonic.environment.DeviceStatus;
import com.supersonicads.sdk.data.AdUnitsReady;
import com.supersonicads.sdk.data.AdUnitsState;
import com.supersonicads.sdk.data.SSABCParameters;
import com.supersonicads.sdk.data.SSAEnums.ControllerState;
import com.supersonicads.sdk.data.SSAEnums.DebugMode;
import com.supersonicads.sdk.data.SSAEnums.ProductType;
import com.supersonicads.sdk.data.SSAFile;
import com.supersonicads.sdk.data.SSAObj;
import com.supersonicads.sdk.listeners.OnGenericFunctionListener;
import com.supersonicads.sdk.listeners.OnInterstitialListener;
import com.supersonicads.sdk.listeners.OnOfferWallListener;
import com.supersonicads.sdk.listeners.OnRewardedVideoListener;
import com.supersonicads.sdk.precache.DownloadManager;
import com.supersonicads.sdk.precache.DownloadManager.OnPreCacheCompletion;
import com.supersonicads.sdk.utils.DeviceProperties;
import com.supersonicads.sdk.utils.LocationHelper;
import com.supersonicads.sdk.utils.Logger;
import com.supersonicads.sdk.utils.SDKUtils;
import com.supersonicads.sdk.utils.SupersonicAsyncHttpRequestTask;
import com.supersonicads.sdk.utils.SupersonicSharedPrefHelper;
import com.supersonicads.sdk.utils.SupersonicStorageUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SupersonicWebView
  extends WebView
  implements DownloadManager.OnPreCacheCompletion, DownloadListener
{
  public static String APP_IDS;
  public static int DISPLAY_WEB_VIEW_INTENT;
  public static String EXTERNAL_URL;
  public static String IS_INSTALLED;
  public static String IS_STORE;
  public static String IS_STORE_CLOSE;
  private static String JSON_KEY_FAIL = "fail";
  private static String JSON_KEY_SUCCESS;
  public static int OPEN_URL_INTENT;
  public static String REQUEST_ID;
  public static String RESULT;
  public static String SECONDARY_WEB_VIEW;
  public static String WEBVIEW_TYPE;
  public static int mDebugMode = 0;
  private final String GENERIC_MESSAGE = "We're sorry, some error occurred. we will investigate it";
  private String PUB_TAG = "Supersonic";
  private String TAG = SupersonicWebView.class.getSimpleName();
  private DownloadManager downloadManager;
  private Boolean isKitkatAndAbove = null;
  private boolean isRemoveCloseEventHandler;
  private String mBCAppKey;
  private String mBCUserId;
  private String mCacheDirectory;
  private OnWebViewControllerChangeListener mChangeListener;
  private CountDownTimer mCloseEventTimer;
  private BroadcastReceiver mConnectionReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      boolean bool1 = false;
      boolean bool2 = false;
      paramAnonymousContext = (ConnectivityManager)paramAnonymousContext.getSystemService("connectivity");
      paramAnonymousIntent = paramAnonymousContext.getNetworkInfo(1);
      if (paramAnonymousIntent != null) {
        bool1 = paramAnonymousIntent.isConnected();
      }
      paramAnonymousContext = paramAnonymousContext.getNetworkInfo(0);
      if (paramAnonymousContext != null) {
        bool2 = paramAnonymousContext.isConnected();
      }
      if (SupersonicWebView.this.mControllerState == SSAEnums.ControllerState.Ready) {
        SupersonicWebView.this.deviceStatusChanged(bool1, bool2);
      }
    }
  };
  private String mControllerKeyPressed = "interrupt";
  private SSAEnums.ControllerState mControllerState = SSAEnums.ControllerState.None;
  private View mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private FrameLayout mCustomViewContainer;
  private Map<String, String> mExtraParameters;
  private boolean mGlobalControllerTimeFinish;
  private CountDownTimer mGlobalControllerTimer;
  private int mHiddenForceCloseHeight = 50;
  private String mHiddenForceCloseLocation = "top-right";
  private int mHiddenForceCloseWidth = 50;
  private String mISAppKey;
  private Map<String, String> mISExtraParameters;
  private String mISUserId;
  private boolean mISmiss;
  private Boolean mIsInterstitialAvailable = null;
  private FrameLayout mLayout;
  private CountDownTimer mLoadControllerTimer;
  private String mOWAppKey;
  private String mOWCreditsAppKey;
  private boolean mOWCreditsMiss;
  private String mOWCreditsUserId;
  private String mOWUserId;
  private boolean mOWmiss;
  private OnGenericFunctionListener mOnGenericFunctionListener;
  private OnInterstitialListener mOnInitInterstitialListener;
  private OnOfferWallListener mOnOfferWallListener;
  private OnRewardedVideoListener mOnRewardedVideoListener;
  private String mOrientationState;
  private boolean mRVmiss;
  private String mRequestParameters;
  private AdUnitsState mSavedState = new AdUnitsState();
  private Object mSavedStateLocker = new Object();
  private State mState;
  private Uri mUri;
  private VideoEventsListener mVideoEventsListener;
  private ChromeClient mWebChromeClient;
  
  static
  {
    IS_STORE = "is_store";
    IS_STORE_CLOSE = "is_store_close";
    WEBVIEW_TYPE = "webview_type";
    EXTERNAL_URL = "external_url";
    SECONDARY_WEB_VIEW = "secondary_web_view";
    DISPLAY_WEB_VIEW_INTENT = 0;
    OPEN_URL_INTENT = 1;
    APP_IDS = "appIds";
    REQUEST_ID = "requestId";
    IS_INSTALLED = "isInstalled";
    RESULT = "result";
    JSON_KEY_SUCCESS = "success";
  }
  
  public SupersonicWebView(Context paramContext)
  {
    super(paramContext);
    Logger.i(this.TAG, "C'tor");
    initLayout(paramContext);
    this.mCacheDirectory = SupersonicStorageUtils.initializeCacheDirectory(paramContext);
    this.downloadManager = DownloadManager.getInstance(this.mCacheDirectory);
    this.downloadManager.setOnPreCacheCompletion(this);
    this.mWebChromeClient = new ChromeClient(null);
    setWebViewClient(new ViewClient(null));
    setWebChromeClient(this.mWebChromeClient);
    setWebViewSettings();
    addJavascriptInterface(new JSInterface(paramContext), "Android");
    setDownloadListener(this);
    setOnTouchListener(new SupersonicWebViewTouchListener(null));
  }
  
  private void closeWebView()
  {
    if (this.mChangeListener != null) {
      this.mChangeListener.onHide();
    }
  }
  
  private void createInitProductJSMethod(SSAEnums.ProductType paramProductType)
  {
    String str = null;
    if (paramProductType == SSAEnums.ProductType.BrandConnect) {
      str = generateJSToInject("initBrandConnect", "onInitBrandConnectSuccess", "onInitBrandConnectFail");
    }
    for (;;)
    {
      if (str != null) {
        injectJavascript(str);
      }
      return;
      if (paramProductType == SSAEnums.ProductType.Interstitial)
      {
        paramProductType = new HashMap();
        paramProductType.put("applicationKey", this.mISAppKey);
        paramProductType.put("applicationUserId", this.mISUserId);
        if (this.mISExtraParameters != null) {
          paramProductType.putAll(this.mISExtraParameters);
        }
        str = generateJSToInject("initInterstitial", flatMapToJsonAsString(paramProductType), "onInitInterstitialSuccess", "onInitInterstitialFail");
      }
      else if (paramProductType == SSAEnums.ProductType.OfferWall)
      {
        str = generateJSToInject("showOfferWall", "onShowOfferWallSuccess", "onShowOfferWallFail");
      }
      else if (paramProductType == SSAEnums.ProductType.OfferWallCredits)
      {
        str = generateJSToInject("getUserCredits", parseToJson("productType", "OfferWall", "applicationKey", this.mOWCreditsAppKey, "applicationUserId", this.mOWCreditsUserId, null, null, null, false), "null", "onGetUserCreditsFail");
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  private void evaluateJavascriptKitKat(String paramString)
  {
    evaluateJavascript(paramString, null);
  }
  
  private String extractFailFunctionToCall(String paramString)
  {
    return new SSAObj(paramString).getString(JSON_KEY_FAIL);
  }
  
  private String extractSuccessFunctionToCall(String paramString)
  {
    return new SSAObj(paramString).getString(JSON_KEY_SUCCESS);
  }
  
  private String flatMapToJsonAsString(Map<String, String> paramMap)
  {
    JSONObject localJSONObject = new JSONObject();
    if (paramMap != null)
    {
      paramMap = paramMap.entrySet().iterator();
      for (;;)
      {
        if (paramMap.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)paramMap.next();
          try
          {
            localJSONObject.putOpt((String)localEntry.getKey(), SDKUtils.encodeString((String)localEntry.getValue()));
            paramMap.remove();
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              Logger.i(this.TAG, "flatMapToJsonAsStringfailed " + localJSONException.toString());
            }
          }
        }
      }
    }
    return localJSONObject.toString();
  }
  
  private String generateJSToInject(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString).append("');");
    return localStringBuilder.toString();
  }
  
  private String generateJSToInject(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString1).append("?parameters=").append(paramString2).append("');");
    return localStringBuilder.toString();
  }
  
  private String generateJSToInject(String paramString1, String paramString2, String paramString3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString1).append("','").append(paramString2).append("','").append(paramString3).append("');");
    return localStringBuilder.toString();
  }
  
  private String generateJSToInject(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString1).append("?parameters=").append(paramString2).append("','").append(paramString3).append("','").append(paramString4).append("');");
    return localStringBuilder.toString();
  }
  
  /* Error */
  private Object[] getApplicationParams(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 654	org/json/JSONObject
    //   5: dup
    //   6: invokespecial 655	org/json/JSONObject:<init>	()V
    //   9: astore 5
    //   11: ldc_w 723
    //   14: astore_3
    //   15: ldc_w 723
    //   18: astore 4
    //   20: aload_1
    //   21: invokestatic 729	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   24: ifne +314 -> 338
    //   27: aload_1
    //   28: getstatic 573	com/supersonicads/sdk/data/SSAEnums$ProductType:BrandConnect	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   31: invokevirtual 730	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   34: invokevirtual 733	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   37: ifeq +218 -> 255
    //   40: aload_0
    //   41: getfield 382	com/supersonicads/sdk/controller/SupersonicWebView:mBCAppKey	Ljava/lang/String;
    //   44: astore_3
    //   45: aload_0
    //   46: getfield 385	com/supersonicads/sdk/controller/SupersonicWebView:mBCUserId	Ljava/lang/String;
    //   49: astore 4
    //   51: aload 5
    //   53: ldc_w 630
    //   56: aload_1
    //   57: invokevirtual 735	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   60: pop
    //   61: aload 4
    //   63: invokestatic 729	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   66: ifne +306 -> 372
    //   69: aload 5
    //   71: ldc_w 598
    //   74: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   77: aload 4
    //   79: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   82: invokevirtual 735	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   85: pop
    //   86: aload_3
    //   87: invokestatic 729	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   90: ifne +316 -> 406
    //   93: aload 5
    //   95: ldc_w 590
    //   98: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   101: aload_3
    //   102: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   105: invokevirtual 735	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   108: pop
    //   109: aload_0
    //   110: getfield 389	com/supersonicads/sdk/controller/SupersonicWebView:mExtraParameters	Ljava/util/Map;
    //   113: ifnull +298 -> 411
    //   116: aload_0
    //   117: getfield 389	com/supersonicads/sdk/controller/SupersonicWebView:mExtraParameters	Ljava/util/Map;
    //   120: invokeinterface 737 1 0
    //   125: ifne +286 -> 411
    //   128: aload_0
    //   129: getfield 389	com/supersonicads/sdk/controller/SupersonicWebView:mExtraParameters	Ljava/util/Map;
    //   132: invokeinterface 659 1 0
    //   137: invokeinterface 665 1 0
    //   142: astore_1
    //   143: aload_1
    //   144: invokeinterface 671 1 0
    //   149: ifeq +262 -> 411
    //   152: aload_1
    //   153: invokeinterface 675 1 0
    //   158: checkcast 677	java/util/Map$Entry
    //   161: astore_3
    //   162: aload_3
    //   163: invokeinterface 680 1 0
    //   168: checkcast 682	java/lang/String
    //   171: ldc_w 739
    //   174: invokevirtual 733	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   177: ifeq +16 -> 193
    //   180: aload_0
    //   181: aload_3
    //   182: invokeinterface 685 1 0
    //   187: checkcast 682	java/lang/String
    //   190: invokespecial 742	com/supersonicads/sdk/controller/SupersonicWebView:setWebviewCache	(Ljava/lang/String;)V
    //   193: aload 5
    //   195: aload_3
    //   196: invokeinterface 680 1 0
    //   201: checkcast 682	java/lang/String
    //   204: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   207: aload_3
    //   208: invokeinterface 685 1 0
    //   213: checkcast 682	java/lang/String
    //   216: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   219: invokevirtual 735	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   222: pop
    //   223: goto -80 -> 143
    //   226: astore_3
    //   227: aload_3
    //   228: invokevirtual 745	org/json/JSONException:printStackTrace	()V
    //   231: new 747	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   234: dup
    //   235: invokespecial 748	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   238: iconst_1
    //   239: anewarray 682	java/lang/String
    //   242: dup
    //   243: iconst_0
    //   244: ldc_w 750
    //   247: aastore
    //   248: invokevirtual 754	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   251: pop
    //   252: goto -109 -> 143
    //   255: aload_1
    //   256: getstatic 585	com/supersonicads/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   259: invokevirtual 730	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   262: invokevirtual 733	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   265: ifeq +17 -> 282
    //   268: aload_0
    //   269: getfield 399	com/supersonicads/sdk/controller/SupersonicWebView:mISAppKey	Ljava/lang/String;
    //   272: astore_3
    //   273: aload_0
    //   274: getfield 402	com/supersonicads/sdk/controller/SupersonicWebView:mISUserId	Ljava/lang/String;
    //   277: astore 4
    //   279: goto -228 -> 51
    //   282: aload_1
    //   283: getstatic 617	com/supersonicads/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   286: invokevirtual 730	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   289: invokevirtual 733	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   292: ifeq -241 -> 51
    //   295: aload_0
    //   296: getfield 412	com/supersonicads/sdk/controller/SupersonicWebView:mOWAppKey	Ljava/lang/String;
    //   299: astore_3
    //   300: aload_0
    //   301: getfield 416	com/supersonicads/sdk/controller/SupersonicWebView:mOWUserId	Ljava/lang/String;
    //   304: astore 4
    //   306: goto -255 -> 51
    //   309: astore_1
    //   310: aload_1
    //   311: invokevirtual 745	org/json/JSONException:printStackTrace	()V
    //   314: new 747	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   317: dup
    //   318: invokespecial 748	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   321: iconst_1
    //   322: anewarray 682	java/lang/String
    //   325: dup
    //   326: iconst_0
    //   327: ldc_w 756
    //   330: aastore
    //   331: invokevirtual 754	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   334: pop
    //   335: goto -274 -> 61
    //   338: iconst_1
    //   339: istore_2
    //   340: goto -279 -> 61
    //   343: astore_1
    //   344: aload_1
    //   345: invokevirtual 745	org/json/JSONException:printStackTrace	()V
    //   348: new 747	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   351: dup
    //   352: invokespecial 748	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   355: iconst_1
    //   356: anewarray 682	java/lang/String
    //   359: dup
    //   360: iconst_0
    //   361: ldc_w 758
    //   364: aastore
    //   365: invokevirtual 754	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   368: pop
    //   369: goto -283 -> 86
    //   372: iconst_1
    //   373: istore_2
    //   374: goto -288 -> 86
    //   377: astore_1
    //   378: aload_1
    //   379: invokevirtual 745	org/json/JSONException:printStackTrace	()V
    //   382: new 747	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   385: dup
    //   386: invokespecial 748	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   389: iconst_1
    //   390: anewarray 682	java/lang/String
    //   393: dup
    //   394: iconst_0
    //   395: ldc_w 760
    //   398: aastore
    //   399: invokevirtual 754	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   402: pop
    //   403: goto -294 -> 109
    //   406: iconst_1
    //   407: istore_2
    //   408: goto -299 -> 109
    //   411: iconst_2
    //   412: anewarray 260	java/lang/Object
    //   415: dup
    //   416: iconst_0
    //   417: aload 5
    //   419: invokevirtual 711	org/json/JSONObject:toString	()Ljava/lang/String;
    //   422: aastore
    //   423: dup
    //   424: iconst_1
    //   425: iload_2
    //   426: invokestatic 766	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   429: aastore
    //   430: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	431	0	this	SupersonicWebView
    //   0	431	1	paramString	String
    //   1	425	2	bool	boolean
    //   14	194	3	localObject	Object
    //   226	2	3	localJSONException	JSONException
    //   272	28	3	str1	String
    //   18	287	4	str2	String
    //   9	409	5	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   193	223	226	org/json/JSONException
    //   51	61	309	org/json/JSONException
    //   69	86	343	org/json/JSONException
    //   93	109	377	org/json/JSONException
  }
  
  private Object[] getAppsStatus(String paramString1, String paramString2)
  {
    bool = false;
    JSONObject localJSONObject1 = new JSONObject();
    for (;;)
    {
      try
      {
        if ((TextUtils.isEmpty(paramString1)) || (paramString1.equalsIgnoreCase("null"))) {
          continue;
        }
        if ((TextUtils.isEmpty(paramString2)) || (paramString2.equalsIgnoreCase("null"))) {
          continue;
        }
        List localList = getBaseContext().getPackageManager().getInstalledApplications(0);
        paramString1 = new JSONArray(paramString1);
        JSONObject localJSONObject2 = new JSONObject();
        i = 0;
        if (i < paramString1.length())
        {
          String str = paramString1.getString(i).trim();
          if (TextUtils.isEmpty(str)) {
            continue;
          }
          JSONObject localJSONObject3 = new JSONObject();
          int k = 0;
          Iterator localIterator = localList.iterator();
          int j = k;
          if (localIterator.hasNext())
          {
            if (!str.equalsIgnoreCase(((ApplicationInfo)localIterator.next()).packageName)) {
              continue;
            }
            localJSONObject3.put(IS_INSTALLED, true);
            localJSONObject2.put(str, localJSONObject3);
            j = 1;
          }
          if (j != 0) {
            continue;
          }
          localJSONObject3.put(IS_INSTALLED, false);
          localJSONObject2.put(str, localJSONObject3);
          continue;
        }
        localJSONObject1.put(RESULT, localJSONObject2);
        localJSONObject1.put(REQUEST_ID, paramString2);
      }
      catch (Exception paramString1)
      {
        int i;
        bool = true;
        continue;
      }
      return new Object[] { localJSONObject1.toString(), Boolean.valueOf(bool) };
      bool = true;
      localJSONObject1.put("error", "requestId is null or empty");
      continue;
      bool = true;
      localJSONObject1.put("error", "appIds is null or empty");
      continue;
      i += 1;
    }
  }
  
  private Context getBaseContext()
  {
    return ((MutableContextWrapper)getContext()).getBaseContext();
  }
  
  private Object[] getDeviceParams(Context paramContext)
  {
    bool3 = false;
    boolean bool2 = false;
    Object localObject = DeviceProperties.getInstance(paramContext);
    JSONObject localJSONObject = new JSONObject();
    bool1 = bool3;
    for (;;)
    {
      try
      {
        localJSONObject.put("appOrientation", "none");
        bool1 = bool3;
        String str = ((DeviceProperties)localObject).getDeviceOem();
        if (str != null)
        {
          bool1 = bool3;
          localJSONObject.put(SDKUtils.encodeString("deviceOEM"), SDKUtils.encodeString(str));
        }
        bool1 = bool3;
        str = ((DeviceProperties)localObject).getDeviceModel();
        if (str == null) {
          continue;
        }
        bool1 = bool3;
        localJSONObject.put(SDKUtils.encodeString("deviceModel"), SDKUtils.encodeString(str));
        bool1 = bool2;
        SDKUtils.loadGoogleAdvertiserInfo(paramContext);
        bool1 = bool2;
        str = SDKUtils.getAdvertiserId();
        bool1 = bool2;
        bool3 = SDKUtils.isLimitAdTrackingEnabled();
        bool1 = bool2;
        if (!TextUtils.isEmpty(str))
        {
          bool1 = bool2;
          Logger.i(this.TAG, "add AID and LAT");
          bool1 = bool2;
          localJSONObject.put("isLimitAdTrackingEnabled", Boolean.valueOf(bool3));
          bool1 = bool2;
          localJSONObject.put("deviceIds" + "[" + "AID" + "]", SDKUtils.encodeString(str));
        }
        bool1 = bool2;
        str = ((DeviceProperties)localObject).getDeviceOsType();
        if (str == null) {
          continue;
        }
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("deviceOs"), SDKUtils.encodeString(str));
        bool1 = bool2;
        str = Integer.toString(((DeviceProperties)localObject).getDeviceOsVersion());
        if (str == null) {
          continue;
        }
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("deviceOSVersion"), str);
        bool1 = bool2;
        str = ((DeviceProperties)localObject).getSupersonicSdkVersion();
        if (str != null)
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("SDKVersion"), SDKUtils.encodeString(str));
        }
        bool1 = bool2;
        if (((DeviceProperties)localObject).getDeviceCarrier() != null)
        {
          bool1 = bool2;
          if (((DeviceProperties)localObject).getDeviceCarrier().length() > 0)
          {
            bool1 = bool2;
            localJSONObject.put(SDKUtils.encodeString("mobileCarrier"), SDKUtils.encodeString(((DeviceProperties)localObject).getDeviceCarrier()));
          }
        }
        bool1 = bool2;
        localObject = SDKUtils.getConnectionType(paramContext);
        bool1 = bool2;
        if (TextUtils.isEmpty((CharSequence)localObject)) {
          continue;
        }
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("connectionType"), SDKUtils.encodeString((String)localObject));
        bool1 = bool2;
        localObject = paramContext.getResources().getConfiguration().locale.getLanguage();
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("deviceLanguage"), SDKUtils.encodeString(((String)localObject).toUpperCase()));
        }
        bool1 = bool2;
        if (!SupersonicStorageUtils.isExternalStorageAvailable()) {
          continue;
        }
        bool1 = bool2;
        long l = SDKUtils.getAvailableSpaceInMB(paramContext, this.mCacheDirectory);
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("diskFreeSize"), SDKUtils.encodeString(String.valueOf(l)));
        bool1 = bool2;
        paramContext = String.valueOf(SDKUtils.getDeviceWidth());
        if (paramContext == null) {
          continue;
        }
        bool1 = bool2;
        localObject = new StringBuilder();
        bool1 = bool2;
        ((StringBuilder)localObject).append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("width")).append("]");
        bool1 = bool2;
        localJSONObject.put(((StringBuilder)localObject).toString(), SDKUtils.encodeString(paramContext));
        bool1 = bool2;
        paramContext = String.valueOf(SDKUtils.getDeviceHeight());
        if (paramContext == null) {
          continue;
        }
        bool1 = bool2;
        localObject = new StringBuilder();
        bool1 = bool2;
        ((StringBuilder)localObject).append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("height")).append("]");
        bool1 = bool2;
        localJSONObject.put(((StringBuilder)localObject).toString(), SDKUtils.encodeString(paramContext));
        bool1 = bool2;
        paramContext = SDKUtils.getPackageName(getBaseContext());
        bool1 = bool2;
        if (!TextUtils.isEmpty(paramContext))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("bundleId"), SDKUtils.encodeString(paramContext));
        }
        bool1 = bool2;
        paramContext = String.valueOf(SDKUtils.getDeviceScale());
        bool1 = bool2;
        if (!TextUtils.isEmpty(paramContext))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("deviceScreenScale"), SDKUtils.encodeString(paramContext));
        }
        bool1 = bool2;
        paramContext = String.valueOf(DeviceStatus.isRootedDevice());
        bool3 = bool2;
        bool1 = bool2;
        if (!TextUtils.isEmpty(paramContext))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("unLocked"), SDKUtils.encodeString(paramContext));
          bool3 = bool2;
        }
      }
      catch (JSONException paramContext)
      {
        paramContext.printStackTrace();
        new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
        bool3 = bool1;
        continue;
      }
      return new Object[] { localJSONObject.toString(), Boolean.valueOf(bool3) };
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
    }
  }
  
  private String getRequestParameters()
  {
    Object localObject = DeviceProperties.getInstance(getBaseContext());
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = ((DeviceProperties)localObject).getSupersonicSdkVersion();
    if (!TextUtils.isEmpty(str1)) {
      localStringBuilder.append("SDKVersion").append("=").append(str1).append("&");
    }
    localObject = ((DeviceProperties)localObject).getDeviceOsType();
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      localStringBuilder.append("deviceOs").append("=").append((String)localObject);
    }
    localObject = Uri.parse(SDKUtils.getControllerUrl());
    if (localObject != null)
    {
      String str2 = ((Uri)localObject).getScheme() + ":";
      str1 = ((Uri)localObject).getHost();
      int i = ((Uri)localObject).getPort();
      localObject = str1;
      if (i != -1) {
        localObject = str1 + ":" + i;
      }
      localStringBuilder.append("&").append("protocol").append("=").append(str2);
      localStringBuilder.append("&").append("domain").append("=").append((String)localObject);
      localObject = SDKUtils.getControllerConfig();
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        localStringBuilder.append("&").append("controllerConfig").append("=").append((String)localObject);
      }
      localStringBuilder.append("&").append("debug").append("=").append(getDebugMode());
    }
    return localStringBuilder.toString();
  }
  
  private void initLayout(Context paramContext)
  {
    FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-1, -1);
    this.mLayout = new FrameLayout(paramContext);
    this.mCustomViewContainer = new FrameLayout(paramContext);
    FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(-1, -1);
    this.mCustomViewContainer.setLayoutParams(localLayoutParams2);
    this.mCustomViewContainer.setVisibility(8);
    paramContext = new FrameLayout(paramContext);
    paramContext.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    paramContext.addView(this);
    this.mLayout.addView(this.mCustomViewContainer, localLayoutParams1);
    this.mLayout.addView(paramContext);
  }
  
  private void initProduct(String paramString1, String paramString2, Map<String, String> paramMap, SSAEnums.ProductType paramProductType, String paramString3)
  {
    if ((TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString1))) {
      triggerOnControllerInitProductFail("User id or Application key are missing", paramProductType);
    }
    do
    {
      return;
      if (this.mControllerState == SSAEnums.ControllerState.Ready)
      {
        SupersonicSharedPrefHelper.getSupersonicPrefHelper().setApplicationKey(paramString1, paramProductType);
        SupersonicSharedPrefHelper.getSupersonicPrefHelper().setUserID(paramString2, paramProductType);
        createInitProductJSMethod(paramProductType);
        return;
      }
      setMissProduct(paramProductType);
      if (this.mControllerState == SSAEnums.ControllerState.Failed)
      {
        triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(paramString3, "Initiating Controller"), paramProductType);
        return;
      }
    } while (!this.mGlobalControllerTimeFinish);
    downloadController();
  }
  
  private void injectJavascript(final String paramString)
  {
    Object localObject2 = "empty";
    Object localObject1;
    if (getDebugMode() == SSAEnums.DebugMode.MODE_0.getValue()) {
      localObject1 = "console.log(\"JS exeption: \" + JSON.stringify(e));";
    }
    for (;;)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("try{").append(paramString).append("}catch(e){").append((String)localObject1).append("}");
      paramString = "javascript:" + ((StringBuilder)localObject2).toString();
      localObject1 = getBaseContext();
      if (!(localObject1 instanceof Activity)) {
        break;
      }
      ((Activity)localObject1).runOnUiThread(new Runnable()
      {
        public void run()
        {
          Logger.i(SupersonicWebView.this.TAG, paramString);
          try
          {
            if (SupersonicWebView.this.isKitkatAndAbove != null)
            {
              if (SupersonicWebView.this.isKitkatAndAbove.booleanValue())
              {
                SupersonicWebView.this.evaluateJavascriptKitKat(this.val$scriptBuilder.toString());
                return;
              }
              SupersonicWebView.this.loadUrl(paramString);
              return;
            }
          }
          catch (Throwable localThrowable1)
          {
            Logger.e(SupersonicWebView.this.TAG, "injectJavascript: " + localThrowable1.toString());
            new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=injectJavaScript" });
            return;
          }
          int i = Build.VERSION.SDK_INT;
          if (i >= 19) {
            try
            {
              SupersonicWebView.this.evaluateJavascriptKitKat(this.val$scriptBuilder.toString());
              SupersonicWebView.access$5802(SupersonicWebView.this, Boolean.valueOf(true));
              return;
            }
            catch (NoSuchMethodError localNoSuchMethodError)
            {
              Logger.e(SupersonicWebView.this.TAG, "evaluateJavascrip NoSuchMethodError: SDK version=" + Build.VERSION.SDK_INT + " " + localNoSuchMethodError);
              SupersonicWebView.this.loadUrl(paramString);
              SupersonicWebView.access$5802(SupersonicWebView.this, Boolean.valueOf(false));
              return;
            }
            catch (Throwable localThrowable2)
            {
              Logger.e(SupersonicWebView.this.TAG, "evaluateJavascrip Exception: SDK version=" + Build.VERSION.SDK_INT + " " + localThrowable2);
              SupersonicWebView.this.loadUrl(paramString);
              SupersonicWebView.access$5802(SupersonicWebView.this, Boolean.valueOf(false));
              return;
            }
          }
          SupersonicWebView.this.loadUrl(paramString);
          SupersonicWebView.access$5802(SupersonicWebView.this, Boolean.valueOf(false));
        }
      });
      return;
      localObject1 = localObject2;
      if (getDebugMode() >= SSAEnums.DebugMode.MODE_1.getValue())
      {
        localObject1 = localObject2;
        if (getDebugMode() <= SSAEnums.DebugMode.MODE_3.getValue()) {
          localObject1 = "console.log(\"JS exeption: \" + JSON.stringify(e));";
        }
      }
    }
    new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=contextIsNotActivity" });
  }
  
  private String mapToJson(Map<String, String> paramMap)
  {
    JSONObject localJSONObject = new JSONObject();
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        String str2 = (String)paramMap.get(str1);
        try
        {
          localJSONObject.put(str1, SDKUtils.encodeString(str2));
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
      }
    }
    return localJSONObject.toString();
  }
  
  private String parseToJson(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2))) {
        localJSONObject.put(paramString1, SDKUtils.encodeString(paramString2));
      }
      if ((!TextUtils.isEmpty(paramString3)) && (!TextUtils.isEmpty(paramString4))) {
        localJSONObject.put(paramString3, SDKUtils.encodeString(paramString4));
      }
      if ((!TextUtils.isEmpty(paramString5)) && (!TextUtils.isEmpty(paramString6))) {
        localJSONObject.put(paramString5, SDKUtils.encodeString(paramString6));
      }
      if ((!TextUtils.isEmpty(paramString7)) && (!TextUtils.isEmpty(paramString8))) {
        localJSONObject.put(paramString7, SDKUtils.encodeString(paramString8));
      }
      if (!TextUtils.isEmpty(paramString9)) {
        localJSONObject.put(paramString9, paramBoolean);
      }
    }
    catch (JSONException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
        new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramString1.getStackTrace()[0].getMethodName() });
      }
    }
    return localJSONObject.toString();
  }
  
  private void resetMissProduct()
  {
    this.mRVmiss = false;
    this.mISmiss = false;
    this.mOWmiss = false;
    this.mOWCreditsMiss = false;
  }
  
  private void responseBack(String paramString1, boolean paramBoolean, String paramString2, String paramString3)
  {
    Object localObject = new SSAObj(paramString1);
    String str1 = ((SSAObj)localObject).getString(JSON_KEY_SUCCESS);
    String str2 = ((SSAObj)localObject).getString(JSON_KEY_FAIL);
    localObject = null;
    if (paramBoolean) {
      if (!TextUtils.isEmpty(str1)) {
        localObject = str1;
      }
    }
    for (;;)
    {
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        str1 = paramString1;
        if (TextUtils.isEmpty(paramString2)) {}
      }
      try
      {
        str1 = new JSONObject(paramString1).put("errMsg", paramString2).toString();
        paramString1 = str1;
        if (!TextUtils.isEmpty(paramString3)) {}
        try
        {
          paramString1 = new JSONObject(str1).put("errCode", paramString3).toString();
          injectJavascript(generateJSToInject((String)localObject, paramString1));
          return;
          if (TextUtils.isEmpty(str2)) {
            continue;
          }
          localObject = str2;
        }
        catch (JSONException paramString1)
        {
          for (;;)
          {
            paramString1 = str1;
          }
        }
      }
      catch (JSONException paramString2)
      {
        for (;;)
        {
          str1 = paramString1;
        }
      }
    }
  }
  
  private void sendProductErrorMessage(SSAEnums.ProductType paramProductType)
  {
    String str = "";
    switch (7.$SwitchMap$com$supersonicads$sdk$data$SSAEnums$ProductType[paramProductType.ordinal()])
    {
    }
    for (;;)
    {
      triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(str, "Initiating Controller"), paramProductType);
      return;
      str = "Init BC";
      continue;
      str = "Init IS";
      continue;
      str = "Show OW";
      continue;
      str = "Show OW Credits";
    }
  }
  
  private void setDisplayZoomControls(WebSettings paramWebSettings)
  {
    if (Build.VERSION.SDK_INT > 11) {
      paramWebSettings.setDisplayZoomControls(false);
    }
  }
  
  public static void setEXTERNAL_URL(String paramString)
  {
    EXTERNAL_URL = paramString;
  }
  
  @SuppressLint({"NewApi"})
  private void setMediaPlaybackJellyBean(WebSettings paramWebSettings)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      paramWebSettings.setMediaPlaybackRequiresUserGesture(false);
    }
  }
  
  private void setMissProduct(SSAEnums.ProductType paramProductType)
  {
    if (paramProductType == SSAEnums.ProductType.BrandConnect) {
      this.mRVmiss = true;
    }
    for (;;)
    {
      Logger.i(this.TAG, "setMissProduct(" + paramProductType + ")");
      return;
      if (paramProductType == SSAEnums.ProductType.Interstitial) {
        this.mISmiss = true;
      } else if (paramProductType == SSAEnums.ProductType.OfferWall) {
        this.mOWmiss = true;
      } else if (paramProductType == SSAEnums.ProductType.OfferWallCredits) {
        this.mOWCreditsMiss = true;
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  private void setWebDebuggingEnabled()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      setWebContentsDebuggingEnabled(true);
    }
  }
  
  private void setWebViewSettings()
  {
    WebSettings localWebSettings = getSettings();
    localWebSettings.setLoadWithOverviewMode(true);
    localWebSettings.setUseWideViewPort(true);
    setVerticalScrollBarEnabled(false);
    setHorizontalScrollBarEnabled(false);
    localWebSettings.setBuiltInZoomControls(false);
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setSupportMultipleWindows(true);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    localWebSettings.setGeolocationEnabled(true);
    localWebSettings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
    localWebSettings.setDomStorageEnabled(true);
    try
    {
      setDisplayZoomControls(localWebSettings);
      setMediaPlaybackJellyBean(localWebSettings);
      return;
    }
    catch (Throwable localThrowable)
    {
      Logger.e(this.TAG, "setWebSettings - " + localThrowable.toString());
      new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=setWebViewSettings" });
    }
  }
  
  private void setWebviewBackground(String paramString)
  {
    paramString = new SSAObj(paramString).getString("color");
    int i = 0;
    if (!"transparent".equalsIgnoreCase(paramString)) {
      i = Color.parseColor(paramString);
    }
    setBackgroundColor(i);
  }
  
  private void setWebviewCache(String paramString)
  {
    if (paramString.equalsIgnoreCase("0"))
    {
      getSettings().setCacheMode(2);
      return;
    }
    getSettings().setCacheMode(-1);
  }
  
  private boolean shouldNotifyDeveloper(String paramString)
  {
    boolean bool = false;
    if (paramString == null)
    {
      Logger.d(this.TAG, "Trying to trigger a listener - no product was found");
      return false;
    }
    if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
      if (this.mOnInitInterstitialListener != null) {
        bool = true;
      }
    }
    do
    {
      for (;;)
      {
        if (!bool) {
          Logger.d(this.TAG, "Trying to trigger a listener - no listener was found for product " + paramString);
        }
        return bool;
        bool = false;
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString()))
      {
        if (this.mOnRewardedVideoListener != null) {}
        for (bool = true;; bool = false) {
          break;
        }
      }
    } while ((!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) && (!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWallCredits.toString())));
    if (this.mOnOfferWallListener != null) {}
    for (bool = true;; bool = false) {
      break;
    }
  }
  
  private void toastingErrMsg(final String paramString1, final String paramString2)
  {
    paramString2 = new SSAObj(paramString2).getString("errMsg");
    if (!TextUtils.isEmpty(paramString2))
    {
      Context localContext = getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (SupersonicWebView.this.getDebugMode() == SSAEnums.DebugMode.MODE_3.getValue()) {
              Toast.makeText(SupersonicWebView.this.getBaseContext(), paramString1 + " : " + paramString2, 1).show();
            }
          }
        });
      }
    }
  }
  
  private void triggerOnControllerInitProductFail(final String paramString, final SSAEnums.ProductType paramProductType)
  {
    if (shouldNotifyDeveloper(paramProductType.toString()))
    {
      Context localContext = getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (SSAEnums.ProductType.BrandConnect == paramProductType)
            {
              SupersonicWebView.this.mSavedState.setRewardedVideoInitSuccess(false);
              Log.d(SupersonicWebView.this.TAG, "onRVInitFail(message:" + paramString + ")");
              SupersonicWebView.this.mOnRewardedVideoListener.onRVInitFail(paramString);
            }
            do
            {
              do
              {
                return;
                if (SSAEnums.ProductType.Interstitial != paramProductType) {
                  break;
                }
                SupersonicWebView.this.mSavedState.setInterstitialInitSuccess(false);
              } while (!SupersonicWebView.this.mSavedState.reportInitInterstitial());
              Log.d(SupersonicWebView.this.TAG, "onInterstitialInitFail(message:" + paramString + ")");
              SupersonicWebView.this.mOnInitInterstitialListener.onInterstitialInitFail(paramString);
              SupersonicWebView.this.mSavedState.setReportInitInterstitial(false);
              return;
              if (SSAEnums.ProductType.OfferWall == paramProductType)
              {
                SupersonicWebView.this.mOnOfferWallListener.onOWShowFail(paramString);
                return;
              }
            } while (SSAEnums.ProductType.OfferWallCredits != paramProductType);
            SupersonicWebView.this.mOnOfferWallListener.onGetOWCreditsFailed(paramString);
          }
        });
      }
    }
  }
  
  public void assetCached(String paramString1, String paramString2)
  {
    injectJavascript(generateJSToInject("assetCached", parseToJson("file", paramString1, "path", paramString2, null, null, null, null, null, false)));
  }
  
  public void assetCachedFailed(String paramString1, String paramString2, String paramString3)
  {
    injectJavascript(generateJSToInject("assetCachedFailed", parseToJson("file", paramString1, "path", paramString2, "errMsg", paramString3, null, null, null, false)));
  }
  
  public void destroy()
  {
    super.destroy();
    if (this.downloadManager != null) {
      this.downloadManager.release();
    }
    if (this.mConnectionReceiver != null) {
      this.mConnectionReceiver = null;
    }
  }
  
  public void deviceStatusChanged(boolean paramBoolean1, boolean paramBoolean2)
  {
    String str = "none";
    if (paramBoolean1) {
      str = "wifi";
    }
    for (;;)
    {
      injectJavascript(generateJSToInject("deviceStatusChanged", parseToJson("connectionType", str, null, null, null, null, null, null, null, false)));
      return;
      if (paramBoolean2) {
        str = "3g";
      }
    }
  }
  
  public void downloadController()
  {
    SupersonicStorageUtils.deleteFile(this.mCacheDirectory, "", "mobileController.html");
    String str = SDKUtils.getControllerUrl();
    SSAFile localSSAFile = new SSAFile(str, "");
    this.mGlobalControllerTimer = new CountDownTimer(40000L, 1000L)
    {
      public void onFinish()
      {
        Logger.i(SupersonicWebView.this.TAG, "Global Controller Timer Finish");
        SupersonicWebView.access$902(SupersonicWebView.this, true);
      }
      
      public void onTick(long paramAnonymousLong)
      {
        Logger.i(SupersonicWebView.this.TAG, "Global Controller Timer Tick " + paramAnonymousLong);
      }
    }.start();
    if (!this.downloadManager.isMobileControllerThreadLive())
    {
      Logger.i(this.TAG, "Download Mobile Controller: " + str);
      this.downloadManager.downloadMobileControllerFile(localSSAFile);
      return;
    }
    Logger.i(this.TAG, "Download Mobile Controller: already alive");
  }
  
  public void engageEnd(String paramString)
  {
    if (paramString.equals("forceClose")) {
      closeWebView();
    }
    injectJavascript(generateJSToInject("engageEnd", parseToJson("action", paramString, null, null, null, null, null, null, null, false)));
  }
  
  public void enterBackground()
  {
    if (this.mControllerState == SSAEnums.ControllerState.Ready) {
      injectJavascript(generateJSToInject("enterBackground"));
    }
  }
  
  public void enterForeground()
  {
    if (this.mControllerState == SSAEnums.ControllerState.Ready) {
      injectJavascript(generateJSToInject("enterForeground"));
    }
  }
  
  public void forceShowInterstitial()
  {
    injectJavascript(generateJSToInject("forceShowInterstitial", "onShowInterstitialSuccess", "onShowInterstitialFail"));
  }
  
  public String getControllerKeyPressed()
  {
    String str = this.mControllerKeyPressed;
    setControllerKeyPressed("interrupt");
    return str;
  }
  
  public int getDebugMode()
  {
    return mDebugMode;
  }
  
  public FrameLayout getLayout()
  {
    return this.mLayout;
  }
  
  public void getOfferWallCredits(String paramString1, String paramString2, OnOfferWallListener paramOnOfferWallListener)
  {
    this.mOWCreditsAppKey = paramString1;
    this.mOWCreditsUserId = paramString2;
    this.mOnOfferWallListener = paramOnOfferWallListener;
    initProduct(this.mOWCreditsAppKey, this.mOWCreditsUserId, null, SSAEnums.ProductType.OfferWallCredits, "Show OW Credits");
  }
  
  public String getOrientationState()
  {
    return this.mOrientationState;
  }
  
  public AdUnitsState getSavedState()
  {
    return this.mSavedState;
  }
  
  public State getState()
  {
    return this.mState;
  }
  
  public boolean handleSearchKeysURLs(String paramString)
  {
    Object localObject = SupersonicSharedPrefHelper.getSupersonicPrefHelper().getSearchKeys();
    if ((localObject != null) && (!((List)localObject).isEmpty()))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        if (paramString.contains((String)((Iterator)localObject).next()))
        {
          paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
          getBaseContext().startActivity(paramString);
          return true;
        }
      }
    }
    return false;
  }
  
  public void hideCustomView()
  {
    this.mWebChromeClient.onHideCustomView();
  }
  
  public boolean inCustomView()
  {
    return this.mCustomView != null;
  }
  
  public void initBrandConnect(String paramString1, String paramString2, Map<String, String> paramMap, OnRewardedVideoListener paramOnRewardedVideoListener)
  {
    this.mBCAppKey = paramString1;
    this.mBCUserId = paramString2;
    this.mExtraParameters = paramMap;
    this.mOnRewardedVideoListener = paramOnRewardedVideoListener;
    this.mSavedState.setRewardedVideoAppKey(this.mBCAppKey);
    this.mSavedState.setRewardedVideoUserId(this.mBCUserId);
    this.mSavedState.setRewardedVideoExtraParams(this.mExtraParameters);
    initProduct(this.mBCAppKey, this.mBCUserId, this.mExtraParameters, SSAEnums.ProductType.BrandConnect, "Init BC");
  }
  
  public void initInterstitial(String paramString1, String paramString2, Map<String, String> paramMap, OnInterstitialListener paramOnInterstitialListener)
  {
    this.mISAppKey = paramString1;
    this.mISUserId = paramString2;
    this.mISExtraParameters = paramMap;
    this.mOnInitInterstitialListener = paramOnInterstitialListener;
    this.mSavedState.setInterstitialAppKey(this.mISAppKey);
    this.mSavedState.setInterstitialUserId(this.mISUserId);
    this.mSavedState.setInterstitialExtraParams(this.mISExtraParameters);
    this.mSavedState.setReportInitInterstitial(true);
    initProduct(this.mISAppKey, this.mISUserId, this.mExtraParameters, SSAEnums.ProductType.Interstitial, "Init IS");
  }
  
  public void interceptedUrlToStore()
  {
    injectJavascript(generateJSToInject("interceptedUrlToStore"));
  }
  
  public boolean isInterstitialAdAvailable()
  {
    if (this.mIsInterstitialAvailable == null) {
      return false;
    }
    return this.mIsInterstitialAvailable.booleanValue();
  }
  
  public void load(int paramInt)
  {
    try
    {
      loadUrl("about:blank");
      str = "file://" + this.mCacheDirectory + File.separator + "mobileController.html";
      if (new File(this.mCacheDirectory + File.separator + "mobileController.html").exists())
      {
        this.mRequestParameters = getRequestParameters();
        str = str + "?" + this.mRequestParameters;
        this.mLoadControllerTimer = new CountDownTimer(10000L, 1000L)
        {
          public void onFinish()
          {
            Logger.i(SupersonicWebView.this.TAG, "Loading Controller Timer Finish");
            if (this.val$loadAttemp == 2)
            {
              SupersonicWebView.this.mGlobalControllerTimer.cancel();
              if (SupersonicWebView.this.mRVmiss) {
                SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.BrandConnect);
              }
              if (SupersonicWebView.this.mISmiss) {
                SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial);
              }
              if (SupersonicWebView.this.mOWmiss) {
                SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWall);
              }
              if (SupersonicWebView.this.mOWCreditsMiss) {
                SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits);
              }
              return;
            }
            SupersonicWebView.this.load(2);
          }
          
          public void onTick(long paramAnonymousLong)
          {
            Logger.i(SupersonicWebView.this.TAG, "Loading Controller Timer Tick " + paramAnonymousLong);
          }
        }.start();
      }
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        String str;
        loadUrl(str);
        Logger.i(this.TAG, "load(): " + str);
        return;
        localThrowable1 = localThrowable1;
        Logger.e(this.TAG, "WebViewController:: load: " + localThrowable1.toString());
        new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadBlank" });
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          Logger.e(this.TAG, "WebViewController:: load: " + localThrowable2.toString());
          new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadWithPath" });
        }
      }
      Logger.i(this.TAG, "load(): Mobile Controller HTML Does not exist");
      new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=htmlControllerDoesNotExistOnFileSystem" });
    }
  }
  
  public void nativeNavigationPressed(String paramString)
  {
    injectJavascript(generateJSToInject("nativeNavigationPressed", parseToJson("action", paramString, null, null, null, null, null, null, null, false)));
  }
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    Logger.i(this.TAG, paramString1 + " " + paramString4);
  }
  
  public void onFileDownloadFail(SSAFile paramSSAFile)
  {
    if (paramSSAFile.getFile().contains("mobileController.html"))
    {
      this.mGlobalControllerTimer.cancel();
      if (this.mRVmiss) {
        sendProductErrorMessage(SSAEnums.ProductType.BrandConnect);
      }
      if (this.mISmiss) {
        sendProductErrorMessage(SSAEnums.ProductType.Interstitial);
      }
      if (this.mOWmiss) {
        sendProductErrorMessage(SSAEnums.ProductType.OfferWall);
      }
      if (this.mOWCreditsMiss) {
        sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits);
      }
      return;
    }
    assetCachedFailed(paramSSAFile.getFile(), paramSSAFile.getPath(), paramSSAFile.getErrMsg());
  }
  
  public void onFileDownloadSuccess(SSAFile paramSSAFile)
  {
    if (paramSSAFile.getFile().contains("mobileController.html"))
    {
      load(1);
      return;
    }
    assetCached(paramSSAFile.getFile(), paramSSAFile.getPath());
  }
  
  public void pageFinished()
  {
    injectJavascript(generateJSToInject("pageFinished"));
  }
  
  public void pause()
  {
    if (Build.VERSION.SDK_INT > 10) {}
    try
    {
      onPause();
      return;
    }
    catch (Throwable localThrowable)
    {
      Logger.i(this.TAG, "WebViewController: pause() - " + localThrowable);
      new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewPause" });
    }
  }
  
  public void registerConnectionReceiver(Context paramContext)
  {
    paramContext.registerReceiver(this.mConnectionReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
  
  public void removeVideoEventsListener()
  {
    this.mVideoEventsListener = null;
  }
  
  public void restoreState(AdUnitsState paramAdUnitsState)
  {
    for (;;)
    {
      int i;
      synchronized (this.mSavedStateLocker)
      {
        if ((paramAdUnitsState.shouldRestore()) && (this.mControllerState.equals(SSAEnums.ControllerState.Ready)))
        {
          Log.d(this.TAG, "restoreState(state:" + paramAdUnitsState + ")");
          i = paramAdUnitsState.getDisplayedProduct();
          if (i == -1) {
            break label458;
          }
          if (i == SSAEnums.ProductType.BrandConnect.ordinal())
          {
            Log.d(this.TAG, "onRVAdClosed()");
            if (this.mOnRewardedVideoListener != null) {
              this.mOnRewardedVideoListener.onRVAdClosed();
            }
            paramAdUnitsState.adOpened(-1);
            String str1;
            String str2;
            Map localMap;
            if (paramAdUnitsState.isInterstitialInitSuccess())
            {
              Log.d(this.TAG, "onInterstitialAvailability(false)");
              if (this.mOnInitInterstitialListener != null) {
                this.mOnInitInterstitialListener.onInterstitialAvailability(false);
              }
              str1 = paramAdUnitsState.getInterstitialAppKey();
              str2 = paramAdUnitsState.getInterstitialUserId();
              localMap = paramAdUnitsState.getInterstitialExtraParams();
              Log.d(this.TAG, "initInterstitial(appKey:" + str1 + ", userId:" + str2 + ", extraParam:" + localMap + ")");
              initInterstitial(str1, str2, localMap, this.mOnInitInterstitialListener);
            }
            if (paramAdUnitsState.isRewardedVideoInitSuccess())
            {
              Log.d(this.TAG, "onRVNoMoreOffers()");
              if (this.mOnRewardedVideoListener != null) {
                this.mOnRewardedVideoListener.onRVNoMoreOffers();
              }
              str1 = paramAdUnitsState.getRewardedVideoAppKey();
              str2 = paramAdUnitsState.getRewardedVideoUserId();
              localMap = paramAdUnitsState.getRewardedVideoExtraParams();
              Log.d(this.TAG, "initRewardedVideo(appKey:" + str1 + ", userId:" + str2 + ", extraParam:" + localMap + ")");
              initBrandConnect(str1, str2, localMap, this.mOnRewardedVideoListener);
            }
            paramAdUnitsState.setShouldRestore(false);
          }
        }
        else
        {
          this.mSavedState = paramAdUnitsState;
          return;
        }
        if (i == SSAEnums.ProductType.Interstitial.ordinal())
        {
          Log.d(this.TAG, "onInterstitialAdClosed()");
          if (this.mOnInitInterstitialListener == null) {
            continue;
          }
          this.mOnInitInterstitialListener.onInterstitialAdClosed();
        }
      }
      if (i == SSAEnums.ProductType.OfferWall.ordinal())
      {
        Log.d(this.TAG, "onOWAdClosed()");
        if (this.mOnOfferWallListener != null)
        {
          this.mOnOfferWallListener.onOWAdClosed();
          continue;
          label458:
          Log.d(this.TAG, "No ad was opened");
        }
      }
    }
  }
  
  public void resume()
  {
    if (Build.VERSION.SDK_INT > 10) {}
    try
    {
      onResume();
      return;
    }
    catch (Throwable localThrowable)
    {
      Logger.i(this.TAG, "WebViewController: onResume() - " + localThrowable);
      new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewResume" });
    }
  }
  
  public void runGenericFunction(String paramString, Map<String, String> paramMap, OnGenericFunctionListener paramOnGenericFunctionListener)
  {
    this.mOnGenericFunctionListener = paramOnGenericFunctionListener;
    if ("initBrandConnect".equalsIgnoreCase(paramString))
    {
      initBrandConnect((String)paramMap.get("applicationUserId"), (String)paramMap.get("applicationKey"), paramMap, this.mOnRewardedVideoListener);
      return;
    }
    if ("showBrandConnect".equalsIgnoreCase(paramString))
    {
      showBrandConnect();
      return;
    }
    injectJavascript(generateJSToInject(paramString, mapToJson(paramMap), "onGenericFunctionSuccess", "onGenericFunctionFail"));
  }
  
  public WebBackForwardList saveState(Bundle paramBundle)
  {
    return super.saveState(paramBundle);
  }
  
  public void setControllerKeyPressed(String paramString)
  {
    this.mControllerKeyPressed = paramString;
  }
  
  public void setDebugMode(int paramInt)
  {
    mDebugMode = paramInt;
  }
  
  public void setOnWebViewControllerChangeListener(OnWebViewControllerChangeListener paramOnWebViewControllerChangeListener)
  {
    this.mChangeListener = paramOnWebViewControllerChangeListener;
  }
  
  public void setOrientationState(String paramString)
  {
    this.mOrientationState = paramString;
  }
  
  public void setState(State paramState)
  {
    this.mState = paramState;
  }
  
  public void setVideoEventsListener(VideoEventsListener paramVideoEventsListener)
  {
    this.mVideoEventsListener = paramVideoEventsListener;
  }
  
  public void showBrandConnect()
  {
    injectJavascript(generateJSToInject("showBrandConnect", "onShowBrandConnectSuccess", "onShowBrandConnectFail"));
  }
  
  public void showInterstitial()
  {
    injectJavascript(generateJSToInject("showInterstitial", "onShowInterstitialSuccess", "onShowInterstitialFail"));
  }
  
  public void showOfferWall(String paramString1, String paramString2, Map<String, String> paramMap, OnOfferWallListener paramOnOfferWallListener)
  {
    this.mOWAppKey = paramString1;
    this.mOWUserId = paramString2;
    this.mExtraParameters = paramMap;
    this.mOnOfferWallListener = paramOnOfferWallListener;
    initProduct(this.mOWAppKey, this.mOWUserId, this.mExtraParameters, SSAEnums.ProductType.OfferWall, "Show OW");
  }
  
  public void unregisterConnectionReceiver(Context paramContext)
  {
    try
    {
      paramContext.unregisterReceiver(this.mConnectionReceiver);
      return;
    }
    catch (IllegalArgumentException paramContext)
    {
      new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=unregisterConnectionReceiverIllegal" });
      return;
    }
    catch (Exception paramContext)
    {
      Log.e(this.TAG, "unregisterConnectionReceiver - " + paramContext);
      new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
    }
  }
  
  public void viewableChange(boolean paramBoolean, String paramString)
  {
    injectJavascript(generateJSToInject("viewableChange", parseToJson("webview", paramString, null, null, null, null, null, null, "isViewable", paramBoolean)));
  }
  
  private class ChromeClient
    extends WebChromeClient
  {
    private ChromeClient() {}
    
    public View getVideoLoadingProgressView()
    {
      FrameLayout localFrameLayout = new FrameLayout(SupersonicWebView.this.getBaseContext());
      localFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
      return localFrameLayout;
    }
    
    public boolean onConsoleMessage(ConsoleMessage paramConsoleMessage)
    {
      Logger.i("MyApplication", paramConsoleMessage.message() + " -- From line " + paramConsoleMessage.lineNumber() + " of " + paramConsoleMessage.sourceId());
      return true;
    }
    
    public boolean onCreateWindow(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
    {
      paramWebView = new WebView(paramWebView.getContext());
      paramWebView.setWebChromeClient(this);
      paramWebView.setWebViewClient(new SupersonicWebView.FrameBustWebViewClient(SupersonicWebView.this, null));
      ((WebView.WebViewTransport)paramMessage.obj).setWebView(paramWebView);
      paramMessage.sendToTarget();
      Logger.i("onCreateWindow", "onCreateWindow");
      return true;
    }
    
    public void onHideCustomView()
    {
      Logger.i("Test", "onHideCustomView");
      if (SupersonicWebView.this.mCustomView == null) {
        return;
      }
      SupersonicWebView.this.mCustomView.setVisibility(8);
      SupersonicWebView.this.mCustomViewContainer.removeView(SupersonicWebView.this.mCustomView);
      SupersonicWebView.access$1202(SupersonicWebView.this, null);
      SupersonicWebView.this.mCustomViewContainer.setVisibility(8);
      SupersonicWebView.this.mCustomViewCallback.onCustomViewHidden();
      SupersonicWebView.this.setVisibility(0);
    }
    
    public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
    {
      Logger.i("Test", "onShowCustomView");
      SupersonicWebView.this.setVisibility(8);
      if (SupersonicWebView.this.mCustomView != null)
      {
        Logger.i("Test", "mCustomView != null");
        paramCustomViewCallback.onCustomViewHidden();
        return;
      }
      Logger.i("Test", "mCustomView == null");
      SupersonicWebView.this.mCustomViewContainer.addView(paramView);
      SupersonicWebView.access$1202(SupersonicWebView.this, paramView);
      SupersonicWebView.access$1402(SupersonicWebView.this, paramCustomViewCallback);
      SupersonicWebView.this.mCustomViewContainer.setVisibility(0);
    }
  }
  
  private class FrameBustWebViewClient
    extends WebViewClient
  {
    private FrameBustWebViewClient() {}
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Intent localIntent = new Intent(paramWebView.getContext(), OpenUrlActivity.class);
      localIntent.putExtra(SupersonicWebView.EXTERNAL_URL, paramString);
      localIntent.putExtra(SupersonicWebView.SECONDARY_WEB_VIEW, false);
      paramWebView.getContext().startActivity(localIntent);
      return true;
    }
  }
  
  public class JSInterface
  {
    volatile int udiaResults = 0;
    
    public JSInterface(Context paramContext) {}
    
    private void injectGetUDIA(String paramString, JSONArray paramJSONArray)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = SupersonicWebView.this.generateJSToInject(paramString, paramJSONArray.toString(), "onGetUDIASuccess", "onGetUDIAFail");
        SupersonicWebView.this.injectJavascript(paramString);
      }
    }
    
    private void sendResults(String paramString, JSONArray paramJSONArray)
    {
      Logger.i(SupersonicWebView.this.TAG, "sendResults: " + this.udiaResults);
      if (this.udiaResults <= 0) {
        injectGetUDIA(paramString, paramJSONArray);
      }
    }
    
    @JavascriptInterface
    public void adClicked(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "adClicked(" + paramString + ")");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              SupersonicWebView.this.mOnInitInterstitialListener.onInterstitialAdClicked();
            }
          });
        }
        SupersonicWebView.this.toastingErrMsg("onInterstitialAdClicked", paramString);
      }
    }
    
    @JavascriptInterface
    public void adCredited(final String paramString)
    {
      Log.d(SupersonicWebView.this.PUB_TAG, "adCredited(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      final String str1 = ((SSAObj)localObject).getString("credits");
      final int i;
      String str5;
      final int j;
      label85:
      final String str4;
      final boolean bool2;
      final String str3;
      boolean bool3;
      final String str2;
      if (str1 != null)
      {
        i = Integer.parseInt(str1);
        str5 = ((SSAObj)localObject).getString("total");
        if (str5 == null) {
          break label200;
        }
        j = Integer.parseInt(str5);
        str4 = ((SSAObj)localObject).getString("productType");
        boolean bool4 = ((SSAObj)localObject).getBoolean("externalPoll");
        bool2 = false;
        str3 = null;
        bool1 = false;
        bool3 = false;
        if (!bool4) {
          break label205;
        }
        str1 = SupersonicWebView.this.mOWCreditsAppKey;
        str2 = SupersonicWebView.this.mOWCreditsUserId;
      }
      for (;;)
      {
        if (str4.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()))
        {
          if ((((SSAObj)localObject).isNull("signature")) || (((SSAObj)localObject).isNull("timestamp")) || (((SSAObj)localObject).isNull("totalCreditsFlag")))
          {
            SupersonicWebView.this.responseBack(paramString, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
            return;
            i = 0;
            break;
            label200:
            j = 0;
            break label85;
            label205:
            str1 = SupersonicWebView.this.mOWAppKey;
            str2 = SupersonicWebView.this.mOWUserId;
            continue;
          }
          if (!((SSAObj)localObject).getString("signature").equalsIgnoreCase(SDKUtils.getMD5(str5 + str1 + str2))) {
            break label349;
          }
        }
      }
      for (final boolean bool1 = true;; bool1 = bool3)
      {
        bool2 = ((SSAObj)localObject).getBoolean("totalCreditsFlag");
        str3 = ((SSAObj)localObject).getString("timestamp");
        if (!SupersonicWebView.this.shouldNotifyDeveloper(str4)) {
          break;
        }
        localObject = SupersonicWebView.this.getBaseContext();
        if (!(localObject instanceof Activity)) {
          break;
        }
        ((Activity)localObject).runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (str4.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString())) {
              SupersonicWebView.this.mOnRewardedVideoListener.onRVAdCredited(i);
            }
            while ((!str4.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) || (!bool1) || (!SupersonicWebView.this.mOnOfferWallListener.onOWAdCredited(i, j, bool2)) || (TextUtils.isEmpty(str3))) {
              return;
            }
            if (SupersonicSharedPrefHelper.getSupersonicPrefHelper().setLatestCompeltionsTime(str3, str1, str2))
            {
              SupersonicWebView.this.responseBack(paramString, true, null, null);
              return;
            }
            SupersonicWebView.this.responseBack(paramString, false, "Time Stamp could not be stored", null);
          }
        });
        return;
        label349:
        SupersonicWebView.this.responseBack(paramString, false, "Controller signature is not equal to SDK signature", null);
      }
    }
    
    @JavascriptInterface
    public void adUnitsReady(final String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "adUnitsReady(" + paramString + ")");
      final AdUnitsReady localAdUnitsReady = new AdUnitsReady(paramString);
      if (!localAdUnitsReady.isNumOfAdUnitsExist()) {
        SupersonicWebView.this.responseBack(paramString, false, "Num Of Ad Units Do Not Exist", null);
      }
      Context localContext;
      do
      {
        do
        {
          return;
          SupersonicWebView.this.responseBack(paramString, true, null, null);
          paramString = localAdUnitsReady.getProductType();
        } while (!SupersonicWebView.this.shouldNotifyDeveloper(paramString));
        localContext = SupersonicWebView.this.getBaseContext();
      } while (!(localContext instanceof Activity));
      ((Activity)localContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (Integer.parseInt(localAdUnitsReady.getNumOfAdUnits()) > 0) {}
          for (int i = 1;; i = 0)
          {
            if (paramString.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString()))
            {
              SupersonicWebView.this.mSavedState.setRewardedVideoInitSuccess(true);
              if (i == 0) {
                break;
              }
              Log.d(SupersonicWebView.this.TAG, "onRVInitSuccess()");
              SupersonicWebView.this.mOnRewardedVideoListener.onRVInitSuccess(localAdUnitsReady);
            }
            return;
          }
          SupersonicWebView.this.mOnRewardedVideoListener.onRVNoMoreOffers();
        }
      });
    }
    
    @JavascriptInterface
    public void alert(String paramString) {}
    
    /* Error */
    protected void changeInterstitialState(boolean paramBoolean, String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: iconst_0
      //   3: istore 4
      //   5: aload_0
      //   6: getfield 45	com/supersonicads/sdk/controller/SupersonicWebView$JSInterface:this$0	Lcom/supersonicads/sdk/controller/SupersonicWebView;
      //   9: invokestatic 249	com/supersonicads/sdk/controller/SupersonicWebView:access$5200	(Lcom/supersonicads/sdk/controller/SupersonicWebView;)Ljava/lang/Boolean;
      //   12: ifnonnull +71 -> 83
      //   15: aload_0
      //   16: getfield 45	com/supersonicads/sdk/controller/SupersonicWebView$JSInterface:this$0	Lcom/supersonicads/sdk/controller/SupersonicWebView;
      //   19: iload_1
      //   20: invokestatic 255	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   23: invokestatic 259	com/supersonicads/sdk/controller/SupersonicWebView:access$5202	(Lcom/supersonicads/sdk/controller/SupersonicWebView;Ljava/lang/Boolean;)Ljava/lang/Boolean;
      //   26: pop
      //   27: iconst_1
      //   28: istore_3
      //   29: iload_3
      //   30: ifeq +50 -> 80
      //   33: aload_0
      //   34: getfield 45	com/supersonicads/sdk/controller/SupersonicWebView$JSInterface:this$0	Lcom/supersonicads/sdk/controller/SupersonicWebView;
      //   37: aload_2
      //   38: invokestatic 121	com/supersonicads/sdk/controller/SupersonicWebView:access$4600	(Lcom/supersonicads/sdk/controller/SupersonicWebView;Ljava/lang/String;)Z
      //   41: ifeq +39 -> 80
      //   44: aload_0
      //   45: getfield 45	com/supersonicads/sdk/controller/SupersonicWebView$JSInterface:this$0	Lcom/supersonicads/sdk/controller/SupersonicWebView;
      //   48: invokestatic 263	com/supersonicads/sdk/controller/SupersonicWebView:access$2700	(Lcom/supersonicads/sdk/controller/SupersonicWebView;)Lcom/supersonicads/sdk/listeners/OnInterstitialListener;
      //   51: aload_0
      //   52: getfield 45	com/supersonicads/sdk/controller/SupersonicWebView$JSInterface:this$0	Lcom/supersonicads/sdk/controller/SupersonicWebView;
      //   55: invokestatic 249	com/supersonicads/sdk/controller/SupersonicWebView:access$5200	(Lcom/supersonicads/sdk/controller/SupersonicWebView;)Ljava/lang/Boolean;
      //   58: invokevirtual 266	java/lang/Boolean:booleanValue	()Z
      //   61: invokeinterface 272 2 0
      //   66: aload_0
      //   67: getfield 45	com/supersonicads/sdk/controller/SupersonicWebView$JSInterface:this$0	Lcom/supersonicads/sdk/controller/SupersonicWebView;
      //   70: ldc_w 273
      //   73: iload_1
      //   74: invokestatic 276	java/lang/String:valueOf	(Z)Ljava/lang/String;
      //   77: invokestatic 140	com/supersonicads/sdk/controller/SupersonicWebView:access$5100	(Lcom/supersonicads/sdk/controller/SupersonicWebView;Ljava/lang/String;Ljava/lang/String;)V
      //   80: aload_0
      //   81: monitorexit
      //   82: return
      //   83: iload_1
      //   84: ifeq +33 -> 117
      //   87: aload_0
      //   88: getfield 45	com/supersonicads/sdk/controller/SupersonicWebView$JSInterface:this$0	Lcom/supersonicads/sdk/controller/SupersonicWebView;
      //   91: invokestatic 249	com/supersonicads/sdk/controller/SupersonicWebView:access$5200	(Lcom/supersonicads/sdk/controller/SupersonicWebView;)Ljava/lang/Boolean;
      //   94: invokevirtual 266	java/lang/Boolean:booleanValue	()Z
      //   97: ifne +20 -> 117
      //   100: aload_0
      //   101: getfield 45	com/supersonicads/sdk/controller/SupersonicWebView$JSInterface:this$0	Lcom/supersonicads/sdk/controller/SupersonicWebView;
      //   104: iconst_1
      //   105: invokestatic 255	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   108: invokestatic 259	com/supersonicads/sdk/controller/SupersonicWebView:access$5202	(Lcom/supersonicads/sdk/controller/SupersonicWebView;Ljava/lang/Boolean;)Ljava/lang/Boolean;
      //   111: pop
      //   112: iconst_1
      //   113: istore_3
      //   114: goto -85 -> 29
      //   117: iload 4
      //   119: istore_3
      //   120: iload_1
      //   121: ifne -92 -> 29
      //   124: iload 4
      //   126: istore_3
      //   127: aload_0
      //   128: getfield 45	com/supersonicads/sdk/controller/SupersonicWebView$JSInterface:this$0	Lcom/supersonicads/sdk/controller/SupersonicWebView;
      //   131: invokestatic 249	com/supersonicads/sdk/controller/SupersonicWebView:access$5200	(Lcom/supersonicads/sdk/controller/SupersonicWebView;)Ljava/lang/Boolean;
      //   134: invokevirtual 266	java/lang/Boolean:booleanValue	()Z
      //   137: ifeq -108 -> 29
      //   140: iconst_1
      //   141: istore_3
      //   142: aload_0
      //   143: getfield 45	com/supersonicads/sdk/controller/SupersonicWebView$JSInterface:this$0	Lcom/supersonicads/sdk/controller/SupersonicWebView;
      //   146: iconst_0
      //   147: invokestatic 255	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   150: invokestatic 259	com/supersonicads/sdk/controller/SupersonicWebView:access$5202	(Lcom/supersonicads/sdk/controller/SupersonicWebView;Ljava/lang/Boolean;)Ljava/lang/Boolean;
      //   153: pop
      //   154: goto -125 -> 29
      //   157: astore_2
      //   158: aload_0
      //   159: monitorexit
      //   160: aload_2
      //   161: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	162	0	this	JSInterface
      //   0	162	1	paramBoolean	boolean
      //   0	162	2	paramString	String
      //   28	114	3	i	int
      //   3	122	4	j	int
      // Exception table:
      //   from	to	target	type
      //   5	27	157	finally
      //   33	80	157	finally
      //   87	112	157	finally
      //   127	140	157	finally
      //   142	154	157	finally
    }
    
    @JavascriptInterface
    public void checkInstalledApps(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "checkInstalledApps(" + paramString + ")");
      String str1 = SupersonicWebView.this.extractSuccessFunctionToCall(paramString);
      String str2 = SupersonicWebView.this.extractFailFunctionToCall(paramString);
      Object localObject1 = null;
      Object localObject2 = new SSAObj(paramString);
      paramString = ((SSAObj)localObject2).getString(SupersonicWebView.APP_IDS);
      localObject2 = ((SSAObj)localObject2).getString(SupersonicWebView.REQUEST_ID);
      paramString = SupersonicWebView.this.getAppsStatus(paramString, (String)localObject2);
      localObject2 = (String)paramString[0];
      if (((Boolean)paramString[1]).booleanValue())
      {
        paramString = localObject1;
        if (!TextUtils.isEmpty(str2)) {
          paramString = str2;
        }
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = SupersonicWebView.this.generateJSToInject(paramString, (String)localObject2, "onCheckInstalledAppsSuccess", "onCheckInstalledAppsFail");
          SupersonicWebView.this.injectJavascript(paramString);
        }
        return;
        paramString = localObject1;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void createCalendarEvent(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "createCalendarEvent(" + paramString + ")");
      try
      {
        paramString = new JSONObject();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("frequency", "weekly");
        paramString.put("id", "testevent723GDf84");
        paramString.put("description", "Watch this crazy show on cannel 5!");
        paramString.put("start", "2014-02-01T20:00:00-8:00");
        paramString.put("end", "2014-06-30T20:00:00-8:00");
        paramString.put("status", "pending");
        paramString.put("recurrence", localJSONObject.toString());
        paramString.put("reminder", "2014-02-01T19:50:00-8:00");
        return;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
    }
    
    @JavascriptInterface
    public void deleteFile(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "deleteFile(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (!SupersonicStorageUtils.isPathExist(SupersonicWebView.this.mCacheDirectory, localSSAFile.getPath()))
      {
        SupersonicWebView.this.responseBack(paramString, false, "File not exist", "1");
        return;
      }
      boolean bool = SupersonicStorageUtils.deleteFile(SupersonicWebView.this.mCacheDirectory, localSSAFile.getPath(), localSSAFile.getFile());
      SupersonicWebView.this.responseBack(paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void deleteFolder(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "deleteFolder(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (!SupersonicStorageUtils.isPathExist(SupersonicWebView.this.mCacheDirectory, localSSAFile.getPath()))
      {
        SupersonicWebView.this.responseBack(paramString, false, "Folder not exist", "1");
        return;
      }
      boolean bool = SupersonicStorageUtils.deleteFolder(SupersonicWebView.this.mCacheDirectory, localSSAFile.getPath());
      SupersonicWebView.this.responseBack(paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void displayWebView(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "displayWebView(" + paramString + ")");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      paramString = new SSAObj(paramString);
      boolean bool = ((Boolean)paramString.get("display")).booleanValue();
      String str2 = paramString.getString("productType");
      int i = 0;
      if (bool)
      {
        if (SupersonicWebView.this.getState() != SupersonicWebView.State.Display)
        {
          SupersonicWebView.this.setState(SupersonicWebView.State.Display);
          Logger.i(SupersonicWebView.this.TAG, "State: " + SupersonicWebView.this.mState);
          Context localContext = SupersonicWebView.this.getBaseContext();
          String str1 = SupersonicWebView.this.getOrientationState();
          int j = SDKUtils.getApplicationRotation(localContext);
          if (str2.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
            paramString = new Intent(localContext, InterstitialActivity.class);
          }
          for (;;)
          {
            if ((i != 0) && (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.BrandConnect.toString()))) {
              SupersonicWebView.this.mOnRewardedVideoListener.onRVAdOpened();
            }
            paramString.putExtra("orientation_set_flag", str1);
            paramString.putExtra("rotation_set_flag", j);
            localContext.startActivity(paramString);
            return;
            paramString = new Intent(localContext, ControllerActivity.class);
            if (SSAEnums.ProductType.BrandConnect.toString().equalsIgnoreCase(str2))
            {
              i = 1;
              paramString.putExtra("productType", SSAEnums.ProductType.BrandConnect.toString());
              SupersonicWebView.this.mSavedState.adOpened(SSAEnums.ProductType.BrandConnect.ordinal());
            }
            else
            {
              paramString.putExtra("productType", SSAEnums.ProductType.OfferWall.toString());
              SupersonicWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
            }
          }
        }
        Logger.i(SupersonicWebView.this.TAG, "State: " + SupersonicWebView.this.mState);
        return;
      }
      SupersonicWebView.this.setState(SupersonicWebView.State.Gone);
      SupersonicWebView.this.closeWebView();
    }
    
    @JavascriptInterface
    public void getApplicationInfo(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "getApplicationInfo(" + paramString + ")");
      String str1 = SupersonicWebView.this.extractSuccessFunctionToCall(paramString);
      String str2 = SupersonicWebView.this.extractFailFunctionToCall(paramString);
      String str3 = new SSAObj(paramString).getString("productType");
      paramString = null;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject = SupersonicWebView.this.getApplicationParams(str3);
      str3 = (String)arrayOfObject[0];
      if (((Boolean)arrayOfObject[1]).booleanValue()) {
        if (!TextUtils.isEmpty(str2)) {
          paramString = str2;
        }
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = SupersonicWebView.this.generateJSToInject(paramString, str3, "onGetApplicationInfoSuccess", "onGetApplicationInfoFail");
          SupersonicWebView.this.injectJavascript(paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void getCachedFilesMap(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "getCachedFilesMap(" + paramString + ")");
      String str = SupersonicWebView.this.extractSuccessFunctionToCall(paramString);
      if (!TextUtils.isEmpty(str))
      {
        localObject = new SSAObj(paramString);
        if (!((SSAObj)localObject).containsKey("path")) {
          SupersonicWebView.this.responseBack(paramString, false, "path key does not exist", null);
        }
      }
      else
      {
        return;
      }
      Object localObject = (String)((SSAObj)localObject).get("path");
      if (!SupersonicStorageUtils.isPathExist(SupersonicWebView.this.mCacheDirectory, (String)localObject))
      {
        SupersonicWebView.this.responseBack(paramString, false, "path file does not exist on disk", null);
        return;
      }
      paramString = SupersonicStorageUtils.getCachedFilesMap(SupersonicWebView.this.mCacheDirectory, (String)localObject);
      paramString = SupersonicWebView.this.generateJSToInject(str, paramString, "onGetCachedFilesMapSuccess", "onGetCachedFilesMapFail");
      SupersonicWebView.this.injectJavascript(paramString);
    }
    
    @JavascriptInterface
    public void getDeviceStatus(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "getDeviceStatus(" + paramString + ")");
      String str1 = SupersonicWebView.this.extractSuccessFunctionToCall(paramString);
      String str2 = SupersonicWebView.this.extractFailFunctionToCall(paramString);
      paramString = new Object[2];
      paramString = SupersonicWebView.this.getDeviceParams(SupersonicWebView.access$1500(SupersonicWebView.this));
      String str3 = (String)paramString[0];
      boolean bool = ((Boolean)paramString[1]).booleanValue();
      paramString = null;
      if (bool) {
        if (!TextUtils.isEmpty(str2)) {
          paramString = str2;
        }
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = SupersonicWebView.this.generateJSToInject(paramString, str3, "onGetDeviceStatusSuccess", "onGetDeviceStatusFail");
          SupersonicWebView.this.injectJavascript(paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void getOrientation(String paramString)
    {
      paramString = SupersonicWebView.this.extractSuccessFunctionToCall(paramString);
      String str = SDKUtils.getOrientation(SupersonicWebView.this.getBaseContext()).toString();
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = SupersonicWebView.this.generateJSToInject(paramString, str, "onGetOrientationSuccess", "onGetOrientationFail");
        SupersonicWebView.this.injectJavascript(paramString);
      }
    }
    
    @JavascriptInterface
    public void getUDIA(String paramString)
    {
      this.udiaResults = 0;
      Logger.i(SupersonicWebView.this.TAG, "getUDIA(" + paramString + ")");
      String str = SupersonicWebView.this.extractSuccessFunctionToCall(paramString);
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("getByFlag")) {
        SupersonicWebView.this.responseBack(paramString, false, "getByFlag key does not exist", null);
      }
      for (;;)
      {
        return;
        int i = Integer.parseInt(((SSAObj)localObject).getString("getByFlag"));
        if (i == 0) {
          continue;
        }
        localObject = Integer.toBinaryString(i);
        if (TextUtils.isEmpty((CharSequence)localObject))
        {
          SupersonicWebView.this.responseBack(paramString, false, "fialed to convert getByFlag", null);
          return;
        }
        localObject = new StringBuilder((String)localObject).reverse().toString().toCharArray();
        paramString = new JSONArray();
        JSONObject localJSONObject;
        if (localObject[3] == '0') {
          localJSONObject = new JSONObject();
        }
        try
        {
          localJSONObject.put("sessions", SupersonicSharedPrefHelper.getSupersonicPrefHelper().getSessions());
          SupersonicSharedPrefHelper.getSupersonicPrefHelper().deleteSessions();
          paramString.put(localJSONObject);
          if (localObject[2] != '1') {
            continue;
          }
          this.udiaResults += 1;
          localObject = LocationHelper.getLastLocation(SupersonicWebView.this.getBaseContext());
          if (localObject != null)
          {
            localJSONObject = new JSONObject();
            try
            {
              localJSONObject.put("latitude", ((Location)localObject).getLatitude());
              localJSONObject.put("longitude", ((Location)localObject).getLongitude());
              paramString.put(localJSONObject);
              this.udiaResults -= 1;
              sendResults(str, paramString);
              Logger.i(SupersonicWebView.this.TAG, "done location");
              return;
            }
            catch (JSONException paramString)
            {
              return;
            }
          }
          this.udiaResults -= 1;
          return;
        }
        catch (JSONException localJSONException)
        {
          for (;;) {}
        }
      }
    }
    
    @JavascriptInterface
    public void getUserData(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "getUserData(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("key"))
      {
        SupersonicWebView.this.responseBack(paramString, false, "key does not exist", null);
        return;
      }
      paramString = SupersonicWebView.this.extractSuccessFunctionToCall(paramString);
      localObject = ((SSAObj)localObject).getString("key");
      String str = SupersonicSharedPrefHelper.getSupersonicPrefHelper().getUserData((String)localObject);
      localObject = SupersonicWebView.this.parseToJson((String)localObject, str, null, null, null, null, null, null, null, false);
      paramString = SupersonicWebView.this.generateJSToInject(paramString, (String)localObject);
      SupersonicWebView.this.injectJavascript(paramString);
    }
    
    @JavascriptInterface
    public void getUserUniqueId(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "getUserUniqueId(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("productType")) {
        SupersonicWebView.this.responseBack(paramString, false, "productType does not exist", null);
      }
      do
      {
        return;
        paramString = SupersonicWebView.this.extractSuccessFunctionToCall(paramString);
      } while (TextUtils.isEmpty(paramString));
      localObject = ((SSAObj)localObject).getString("productType");
      String str = SupersonicSharedPrefHelper.getSupersonicPrefHelper().getUniqueId((String)localObject);
      localObject = SupersonicWebView.this.parseToJson("userUniqueId", str, "productType", (String)localObject, null, null, null, null, null, false);
      paramString = SupersonicWebView.this.generateJSToInject(paramString, (String)localObject, "onGetUserUniqueIdSuccess", "onGetUserUniqueIdFail");
      SupersonicWebView.this.injectJavascript(paramString);
    }
    
    @JavascriptInterface
    public void initController(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "initController(" + paramString + ")");
      paramString = new SSAObj(paramString);
      if (paramString.containsKey("stage"))
      {
        paramString = paramString.getString("stage");
        if (!"ready".equalsIgnoreCase(paramString)) {
          break label291;
        }
        SupersonicWebView.access$1602(SupersonicWebView.this, SSAEnums.ControllerState.Ready);
        SupersonicWebView.this.mGlobalControllerTimer.cancel();
        SupersonicWebView.this.mLoadControllerTimer.cancel();
        if (SupersonicWebView.this.mRVmiss) {
          SupersonicWebView.this.initBrandConnect(SupersonicWebView.this.mBCAppKey, SupersonicWebView.this.mBCUserId, SupersonicWebView.this.mExtraParameters, SupersonicWebView.this.mOnRewardedVideoListener);
        }
        if (SupersonicWebView.this.mISmiss) {
          SupersonicWebView.this.initInterstitial(SupersonicWebView.this.mISAppKey, SupersonicWebView.this.mISUserId, SupersonicWebView.this.mExtraParameters, SupersonicWebView.this.mOnInitInterstitialListener);
        }
        if (SupersonicWebView.this.mOWmiss) {
          SupersonicWebView.this.showOfferWall(SupersonicWebView.this.mOWAppKey, SupersonicWebView.this.mOWUserId, SupersonicWebView.this.mExtraParameters, SupersonicWebView.this.mOnOfferWallListener);
        }
        if (SupersonicWebView.this.mOWCreditsMiss) {
          SupersonicWebView.this.getOfferWallCredits(SupersonicWebView.this.mOWCreditsAppKey, SupersonicWebView.this.mOWCreditsUserId, SupersonicWebView.this.mOnOfferWallListener);
        }
        SupersonicWebView.this.restoreState(SupersonicWebView.this.mSavedState);
      }
      label291:
      do
      {
        return;
        if ("loaded".equalsIgnoreCase(paramString))
        {
          SupersonicWebView.access$1602(SupersonicWebView.this, SSAEnums.ControllerState.Loaded);
          return;
        }
        if (!"failed".equalsIgnoreCase(paramString)) {
          break;
        }
        SupersonicWebView.access$1602(SupersonicWebView.this, SSAEnums.ControllerState.Failed);
        if (SupersonicWebView.this.mRVmiss) {
          SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.BrandConnect);
        }
        if (SupersonicWebView.this.mISmiss) {
          SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial);
        }
        if (SupersonicWebView.this.mOWmiss) {
          SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWall);
        }
      } while (!SupersonicWebView.this.mOWCreditsMiss);
      SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits);
      return;
      Logger.i(SupersonicWebView.this.TAG, "No STAGE mentioned! Should not get here!");
    }
    
    @JavascriptInterface
    public void interstitialAvailability(String paramString)
    {
      Log.d(SupersonicWebView.this.TAG, "interstitialAvailability(" + paramString + ")");
      changeInterstitialState(Boolean.parseBoolean(new SSAObj(paramString).getString("available")), SSAEnums.ProductType.Interstitial.toString());
    }
    
    @JavascriptInterface
    public void onAdWindowsClosed(final String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onAdWindowsClosed(" + paramString + ")");
      SupersonicWebView.this.mSavedState.adClosed();
      paramString = new SSAObj(paramString).getString("productType");
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString())) {
        Log.d(SupersonicWebView.this.PUB_TAG, "onRVAdClosed()");
      }
      for (;;)
      {
        if ((SupersonicWebView.this.shouldNotifyDeveloper(paramString)) && (paramString != null))
        {
          Context localContext = SupersonicWebView.this.getBaseContext();
          if ((localContext instanceof Activity)) {
            ((Activity)localContext).runOnUiThread(new Runnable()
            {
              public void run()
              {
                if (paramString.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString())) {
                  SupersonicWebView.this.mOnRewardedVideoListener.onRVAdClosed();
                }
                do
                {
                  return;
                  if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString()))
                  {
                    SupersonicWebView.this.mOnInitInterstitialListener.onInterstitialAdClosed();
                    return;
                  }
                } while (!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()));
                SupersonicWebView.this.mOnOfferWallListener.onOWAdClosed();
              }
            });
          }
        }
        return;
        if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
          Log.d(SupersonicWebView.this.PUB_TAG, "onISAdClosed()");
        } else if (paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
          Log.d(SupersonicWebView.this.PUB_TAG, "onOWAdClosed()");
        }
      }
    }
    
    @JavascriptInterface
    public void onGenericFunctionFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGenericFunctionFail(" + paramString + ")");
      if (SupersonicWebView.this.mOnGenericFunctionListener == null)
      {
        Logger.d(SupersonicWebView.this.TAG, "genericFunctionListener was not found");
        return;
      }
      final String str = new SSAObj(paramString).getString("errMsg");
      Context localContext = SupersonicWebView.this.getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new Runnable()
        {
          public void run()
          {
            SupersonicWebView.this.mOnGenericFunctionListener.onGFFail(str);
          }
        });
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onGenericFunctionFail", paramString);
    }
    
    @JavascriptInterface
    public void onGenericFunctionSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGenericFunctionSuccess(" + paramString + ")");
      if (SupersonicWebView.this.mOnGenericFunctionListener == null)
      {
        Logger.d(SupersonicWebView.this.TAG, "genericFunctionListener was not found");
        return;
      }
      Context localContext = SupersonicWebView.this.getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new Runnable()
        {
          public void run()
          {
            SupersonicWebView.this.mOnGenericFunctionListener.onGFSuccess();
          }
        });
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
    }
    
    @JavascriptInterface
    public void onGetApplicationInfoFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGetApplicationInfoFail(" + paramString + ")");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onGetApplicationInfoFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetApplicationInfoSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGetApplicationInfoSuccess(" + paramString + ")");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onGetApplicationInfoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGetCachedFilesMapFail(" + paramString + ")");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onGetCachedFilesMapFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGetCachedFilesMapSuccess(" + paramString + ")");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onGetCachedFilesMapSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGetDeviceStatusFail(" + paramString + ")");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onGetDeviceStatusFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGetDeviceStatusSuccess(" + paramString + ")");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onGetDeviceStatusSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetUDIAFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGetUDIAFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUDIASuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGetUDIASuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUserCreditsFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGetUserCreditsFail(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("errMsg");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              String str2 = str;
              String str1 = str2;
              if (str2 == null) {
                str1 = "We're sorry, some error occurred. we will investigate it";
              }
              SupersonicWebView.this.mOnOfferWallListener.onGetOWCreditsFailed(str1);
            }
          });
        }
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onGetUserCreditsFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetUserUniqueIdFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGetUserUniqueIdFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUserUniqueIdSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onGetUserUniqueIdSuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onInitBrandConnectFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onInitBrandConnectFail(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("errMsg");
      SupersonicWebView.this.mSavedState.setRewardedVideoInitSuccess(false);
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.BrandConnect.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              String str2 = str;
              String str1 = str2;
              if (str2 == null) {
                str1 = "We're sorry, some error occurred. we will investigate it";
              }
              Log.d(SupersonicWebView.this.TAG, "onRVInitFail(message:" + str + ")");
              SupersonicWebView.this.mOnRewardedVideoListener.onRVInitFail(str1);
            }
          });
        }
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onInitBrandConnectFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitBrandConnectSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onInitBrandConnectSuccess(" + paramString + ")");
      SSABCParameters localSSABCParameters = new SSABCParameters(paramString);
      SupersonicSharedPrefHelper.getSupersonicPrefHelper().setSSABCParameters(localSSABCParameters);
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onInitBrandConnectSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onInitInterstitialFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onInitInterstitialFail(" + paramString + ")");
      SupersonicWebView.this.mSavedState.setInterstitialInitSuccess(false);
      final String str = new SSAObj(paramString).getString("errMsg");
      if (SupersonicWebView.this.mSavedState.reportInitInterstitial())
      {
        SupersonicWebView.this.mSavedState.setReportInitInterstitial(false);
        if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
        {
          Context localContext = SupersonicWebView.this.getBaseContext();
          if ((localContext instanceof Activity)) {
            ((Activity)localContext).runOnUiThread(new Runnable()
            {
              public void run()
              {
                String str2 = str;
                String str1 = str2;
                if (str2 == null) {
                  str1 = "We're sorry, some error occurred. we will investigate it";
                }
                Log.d(SupersonicWebView.this.TAG, "onInterstitialInitFail(message:" + str1 + ")");
                SupersonicWebView.this.mOnInitInterstitialListener.onInterstitialInitFail(str1);
              }
            });
          }
        }
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onInitInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitInterstitialSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onInitInterstitialSuccess()");
      SupersonicWebView.this.toastingErrMsg("onInitInterstitialSuccess", "true");
      SupersonicWebView.this.mSavedState.setInterstitialInitSuccess(true);
      if (SupersonicWebView.this.mSavedState.reportInitInterstitial())
      {
        SupersonicWebView.this.mSavedState.setReportInitInterstitial(false);
        if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
        {
          paramString = SupersonicWebView.this.getBaseContext();
          if ((paramString instanceof Activity)) {
            ((Activity)paramString).runOnUiThread(new Runnable()
            {
              public void run()
              {
                Log.d(SupersonicWebView.this.TAG, "onInterstitialInitSuccess()");
                SupersonicWebView.this.mOnInitInterstitialListener.onInterstitialInitSuccess();
              }
            });
          }
        }
      }
    }
    
    @JavascriptInterface
    public void onOfferWallGeneric(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onOfferWallGeneric(" + paramString + ")");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        SupersonicWebView.this.mOnOfferWallListener.onOWGeneric("", "");
      }
    }
    
    @JavascriptInterface
    public void onRewardedVideoGeneric(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onRewardedVideoGeneric(" + paramString + ")");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.BrandConnect.toString())) {
        SupersonicWebView.this.mOnRewardedVideoListener.onRVGeneric("", "");
      }
    }
    
    @JavascriptInterface
    public void onShowBrandConnectFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onShowBrandConnectFail(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("errMsg");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.BrandConnect.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              String str2 = str;
              String str1 = str2;
              if (str2 == null) {
                str1 = "We're sorry, some error occurred. we will investigate it";
              }
              Log.d(SupersonicWebView.this.TAG, "onRVShowFail(message:" + str + ")");
              SupersonicWebView.this.mOnRewardedVideoListener.onRVShowFail(str1);
            }
          });
        }
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onShowBrandConnectFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowBrandConnectSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onShowBrandConnectSuccess(" + paramString + ")");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onShowBrandConnectSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onShowInterstitialFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onShowInterstitialFail(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("errMsg");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              String str2 = str;
              String str1 = str2;
              if (str2 == null) {
                str1 = "We're sorry, some error occurred. we will investigate it";
              }
              SupersonicWebView.this.mOnInitInterstitialListener.onInterstitialShowFail(str1);
            }
          });
        }
      }
      SupersonicWebView.this.toastingErrMsg("onShowInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowInterstitialSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onShowInterstitialSuccess(" + paramString + ")");
      SupersonicWebView.this.mSavedState.adOpened(SSAEnums.ProductType.Interstitial.ordinal());
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              SupersonicWebView.this.mOnInitInterstitialListener.onInterstitialShowSuccess();
            }
          });
        }
        SupersonicWebView.this.toastingErrMsg("onShowInterstitialSuccess", paramString);
      }
    }
    
    @JavascriptInterface
    public void onShowOfferWallFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onShowOfferWallFail(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("errMsg");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              String str2 = str;
              String str1 = str2;
              if (str2 == null) {
                str1 = "We're sorry, some error occurred. we will investigate it";
              }
              SupersonicWebView.this.mOnOfferWallListener.onOWShowFail(str1);
            }
          });
        }
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onShowOfferWallFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowOfferWallSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onShowOfferWallSuccess(" + paramString + ")");
      SupersonicWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new Runnable()
          {
            public void run()
            {
              SupersonicWebView.this.mOnOfferWallListener.onOWShowSuccess();
            }
          });
        }
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onShowOfferWallSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onUDIAFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onUDIAFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onUDIASuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onUDIASuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onVideoStatusChanged(String paramString)
    {
      Log.d(SupersonicWebView.this.TAG, "onVideoStatusChanged(" + paramString + ")");
      paramString = new SSAObj(paramString);
      String str = paramString.getString("productType");
      if ((SupersonicWebView.this.mVideoEventsListener != null) && (!TextUtils.isEmpty(str)) && (SSAEnums.ProductType.BrandConnect.toString().equalsIgnoreCase(str)))
      {
        paramString = paramString.getString("status");
        if ("started".equalsIgnoreCase(paramString)) {
          SupersonicWebView.this.mVideoEventsListener.onVideoStarted();
        }
      }
      else
      {
        return;
      }
      if ("paused".equalsIgnoreCase(paramString))
      {
        SupersonicWebView.this.mVideoEventsListener.onVideoPaused();
        return;
      }
      if ("playing".equalsIgnoreCase(paramString))
      {
        SupersonicWebView.this.mVideoEventsListener.onVideoResumed();
        return;
      }
      if ("ended".equalsIgnoreCase(paramString))
      {
        SupersonicWebView.this.mVideoEventsListener.onVideoEnded();
        return;
      }
      if ("stopped".equalsIgnoreCase(paramString))
      {
        SupersonicWebView.this.mVideoEventsListener.onVideoStopped();
        return;
      }
      Logger.i(SupersonicWebView.this.TAG, "onVideoStatusChanged: unknown status: " + paramString);
    }
    
    @JavascriptInterface
    public void openUrl(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "openUrl(" + paramString + ")");
      Object localObject1 = new SSAObj(paramString);
      paramString = ((SSAObj)localObject1).getString("url");
      Object localObject2 = ((SSAObj)localObject1).getString("method");
      localObject1 = SupersonicWebView.this.getBaseContext();
      if (((String)localObject2).equalsIgnoreCase("external_browser")) {
        ((Context)localObject1).startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(paramString)));
      }
      do
      {
        return;
        if (((String)localObject2).equalsIgnoreCase("webview"))
        {
          localObject2 = new Intent((Context)localObject1, OpenUrlActivity.class);
          ((Intent)localObject2).putExtra(SupersonicWebView.EXTERNAL_URL, paramString);
          ((Intent)localObject2).putExtra(SupersonicWebView.SECONDARY_WEB_VIEW, true);
          ((Context)localObject1).startActivity((Intent)localObject2);
          return;
        }
      } while (!((String)localObject2).equalsIgnoreCase("store"));
      localObject2 = new Intent((Context)localObject1, OpenUrlActivity.class);
      ((Intent)localObject2).putExtra(SupersonicWebView.EXTERNAL_URL, paramString);
      ((Intent)localObject2).putExtra(SupersonicWebView.IS_STORE, true);
      ((Intent)localObject2).putExtra(SupersonicWebView.SECONDARY_WEB_VIEW, true);
      ((Context)localObject1).startActivity((Intent)localObject2);
    }
    
    @JavascriptInterface
    public void removeCloseEventHandler(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "removeCloseEventHandler(" + paramString + ")");
      if (SupersonicWebView.this.mCloseEventTimer != null) {
        SupersonicWebView.this.mCloseEventTimer.cancel();
      }
      SupersonicWebView.access$702(SupersonicWebView.this, true);
    }
    
    @JavascriptInterface
    public void saveFile(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "saveFile(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (SDKUtils.getAvailableSpaceInMB(SupersonicWebView.this.getBaseContext(), SupersonicWebView.this.mCacheDirectory) <= 0L)
      {
        SupersonicWebView.this.responseBack(paramString, false, "no_disk_space", null);
        return;
      }
      if (!SupersonicStorageUtils.isExternalStorageAvailable())
      {
        SupersonicWebView.this.responseBack(paramString, false, "sotrage_unavailable", null);
        return;
      }
      if (SupersonicStorageUtils.isFileCached(SupersonicWebView.this.mCacheDirectory, localSSAFile))
      {
        SupersonicWebView.this.responseBack(paramString, false, "file_already_exist", null);
        return;
      }
      if (!SDKUtils.isOnline(SupersonicWebView.this.getBaseContext()))
      {
        SupersonicWebView.this.responseBack(paramString, false, "no_network_connection", null);
        return;
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      paramString = localSSAFile.getLastUpdateTime();
      String str;
      if (paramString != null)
      {
        str = String.valueOf(paramString);
        if (!TextUtils.isEmpty(str))
        {
          paramString = localSSAFile.getPath();
          if (!paramString.contains("/")) {
            break label240;
          }
          paramString = localSSAFile.getPath().split("/");
          paramString = paramString[(paramString.length - 1)];
        }
      }
      label240:
      for (;;)
      {
        SupersonicSharedPrefHelper.getSupersonicPrefHelper().setCampaignLastUpdate(paramString, str);
        SupersonicWebView.this.downloadManager.downloadFile(localSSAFile);
        return;
      }
    }
    
    @JavascriptInterface
    public void setBackButtonState(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "setBackButtonState(" + paramString + ")");
      paramString = new SSAObj(paramString).getString("state");
      SupersonicSharedPrefHelper.getSupersonicPrefHelper().setBackButtonState(paramString);
    }
    
    @JavascriptInterface
    public void setForceClose(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "setForceClose(" + paramString + ")");
      paramString = new SSAObj(paramString);
      String str1 = paramString.getString("width");
      String str2 = paramString.getString("height");
      SupersonicWebView.access$402(SupersonicWebView.this, Integer.parseInt(str1));
      SupersonicWebView.access$502(SupersonicWebView.this, Integer.parseInt(str2));
      SupersonicWebView.access$602(SupersonicWebView.this, paramString.getString("position"));
    }
    
    @JavascriptInterface
    public void setOrientation(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "setOrientation(" + paramString + ")");
      paramString = new SSAObj(paramString).getString("orientation");
      SupersonicWebView.this.setOrientationState(paramString);
      int i = SDKUtils.getApplicationRotation(SupersonicWebView.this.getBaseContext());
      if (SupersonicWebView.this.mChangeListener != null) {
        SupersonicWebView.this.mChangeListener.onSetOrientationCalled(paramString, i);
      }
    }
    
    @JavascriptInterface
    public void setStoreSearchKeys(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "setStoreSearchKeys(" + paramString + ")");
      SupersonicSharedPrefHelper.getSupersonicPrefHelper().setSearchKeys(paramString);
    }
    
    @JavascriptInterface
    public void setUserData(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "setUserData(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("key"))
      {
        SupersonicWebView.this.responseBack(paramString, false, "key does not exist", null);
        return;
      }
      if (!((SSAObj)localObject).containsKey("value"))
      {
        SupersonicWebView.this.responseBack(paramString, false, "value does not exist", null);
        return;
      }
      String str = ((SSAObj)localObject).getString("key");
      localObject = ((SSAObj)localObject).getString("value");
      if (SupersonicSharedPrefHelper.getSupersonicPrefHelper().setUserData(str, (String)localObject))
      {
        paramString = SupersonicWebView.this.extractSuccessFunctionToCall(paramString);
        str = SupersonicWebView.this.parseToJson(str, (String)localObject, null, null, null, null, null, null, null, false);
        paramString = SupersonicWebView.this.generateJSToInject(paramString, str);
        SupersonicWebView.this.injectJavascript(paramString);
        return;
      }
      SupersonicWebView.this.responseBack(paramString, false, "SetUserData failed writing to shared preferences", null);
    }
    
    @JavascriptInterface
    public void setUserUniqueId(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "setUserUniqueId(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if ((!((SSAObj)localObject).containsKey("userUniqueId")) || (!((SSAObj)localObject).containsKey("productType")))
      {
        SupersonicWebView.this.responseBack(paramString, false, "uniqueId or productType does not exist", null);
        return;
      }
      String str = ((SSAObj)localObject).getString("userUniqueId");
      localObject = ((SSAObj)localObject).getString("productType");
      if (SupersonicSharedPrefHelper.getSupersonicPrefHelper().setUniqueId(str, (String)localObject))
      {
        SupersonicWebView.this.responseBack(paramString, true, null, null);
        return;
      }
      SupersonicWebView.this.responseBack(paramString, false, "setUserUniqueId failed", null);
    }
    
    @JavascriptInterface
    public void setWebviewBackgroundColor(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "setWebviewBackgroundColor(" + paramString + ")");
      SupersonicWebView.this.setWebviewBackground(paramString);
    }
    
    @JavascriptInterface
    public void toggleUDIA(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "toggleUDIA(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("toggle")) {
        SupersonicWebView.this.responseBack(paramString, false, "toggle key does not exist", null);
      }
      int i;
      do
      {
        return;
        i = Integer.parseInt(((SSAObj)localObject).getString("toggle"));
      } while (i == 0);
      localObject = Integer.toBinaryString(i);
      if (TextUtils.isEmpty((CharSequence)localObject))
      {
        SupersonicWebView.this.responseBack(paramString, false, "fialed to convert toggle", null);
        return;
      }
      if (localObject.toCharArray()[3] == '0')
      {
        SupersonicSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(true);
        return;
      }
      SupersonicSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(false);
    }
  }
  
  public static abstract interface OnWebViewControllerChangeListener
  {
    public abstract void onHide();
    
    public abstract void onSetOrientationCalled(String paramString, int paramInt);
  }
  
  public static enum State
  {
    Display,  Gone;
    
    private State() {}
  }
  
  private class SupersonicWebViewTouchListener
    implements View.OnTouchListener
  {
    private SupersonicWebViewTouchListener() {}
    
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      float f1;
      float f2;
      int i1;
      int n;
      int k;
      int m;
      int i;
      int j;
      if (paramMotionEvent.getAction() == 1)
      {
        f1 = paramMotionEvent.getX();
        f2 = paramMotionEvent.getY();
        Logger.i(SupersonicWebView.this.TAG, "X:" + (int)f1 + " Y:" + (int)f2);
        i1 = SDKUtils.getDeviceWidth();
        n = SDKUtils.getDeviceHeight();
        Logger.i(SupersonicWebView.this.TAG, "Width:" + i1 + " Height:" + n);
        k = SDKUtils.dpToPx(SupersonicWebView.this.mHiddenForceCloseWidth);
        m = SDKUtils.dpToPx(SupersonicWebView.this.mHiddenForceCloseHeight);
        i = 0;
        j = 0;
        if (!"top-right".equalsIgnoreCase(SupersonicWebView.this.mHiddenForceCloseLocation)) {
          break label239;
        }
        i = i1 - (int)f1;
        j = (int)f2;
      }
      for (;;)
      {
        if ((i <= k) && (j <= m))
        {
          SupersonicWebView.access$702(SupersonicWebView.this, false);
          if (SupersonicWebView.this.mCloseEventTimer != null) {
            SupersonicWebView.this.mCloseEventTimer.cancel();
          }
          SupersonicWebView.access$802(SupersonicWebView.this, new CountDownTimer(2000L, 500L)
          {
            public void onFinish()
            {
              Logger.i(SupersonicWebView.this.TAG, "Close Event Timer Finish");
              if (SupersonicWebView.this.isRemoveCloseEventHandler)
              {
                SupersonicWebView.access$702(SupersonicWebView.this, false);
                return;
              }
              SupersonicWebView.this.engageEnd("forceClose");
            }
            
            public void onTick(long paramAnonymousLong)
            {
              Logger.i(SupersonicWebView.this.TAG, "Close Event Timer Tick " + paramAnonymousLong);
            }
          }.start());
        }
        return false;
        label239:
        if ("top-left".equalsIgnoreCase(SupersonicWebView.this.mHiddenForceCloseLocation))
        {
          i = (int)f1;
          j = (int)f2;
        }
        else if ("bottom-right".equalsIgnoreCase(SupersonicWebView.this.mHiddenForceCloseLocation))
        {
          i = i1 - (int)f1;
          j = n - (int)f2;
        }
        else if ("bottom-left".equalsIgnoreCase(SupersonicWebView.this.mHiddenForceCloseLocation))
        {
          i = (int)f1;
          j = n - (int)f2;
        }
      }
    }
  }
  
  private class ViewClient
    extends WebViewClient
  {
    private ViewClient() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      Logger.i("onPageFinished", paramString);
      if ((paramString.contains("adUnit")) || (paramString.contains("index.html"))) {
        SupersonicWebView.this.pageFinished();
      }
      super.onPageFinished(paramWebView, paramString);
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      Logger.i("onPageStarted", paramString);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      Logger.i("onReceivedError", paramString2 + " " + paramString1);
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }
    
    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
    {
      Logger.i("shouldInterceptRequest", paramString);
      int i = 0;
      try
      {
        boolean bool = new URL(paramString).getFile().contains("mraid.js");
        if (bool) {
          i = 1;
        }
      }
      catch (MalformedURLException localMalformedURLException)
      {
        Object localObject;
        File localFile;
        for (;;) {}
      }
      if (i != 0)
      {
        localObject = "file://" + SupersonicWebView.this.mCacheDirectory + File.separator + "mraid.js";
        localFile = new File((String)localObject);
        try
        {
          new FileInputStream(localFile);
          localObject = new WebResourceResponse("text/javascript", "UTF-8", getClass().getResourceAsStream((String)localObject));
          return localObject;
        }
        catch (FileNotFoundException localFileNotFoundException) {}
      }
      return super.shouldInterceptRequest(paramWebView, paramString);
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Logger.i("shouldOverrideUrlLoading", paramString);
      if (SupersonicWebView.this.handleSearchKeysURLs(paramString))
      {
        SupersonicWebView.this.interceptedUrlToStore();
        return true;
      }
      return super.shouldOverrideUrlLoading(paramWebView, paramString);
    }
  }
}
