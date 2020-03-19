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
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
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
import com.supersonicads.sdk.listeners.OnWebViewChangeListener;
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
  private Map<String, String> mBCExtraParameters;
  private String mBCUserId;
  private String mCacheDirectory;
  private OnWebViewChangeListener mChangeListener;
  private CountDownTimer mCloseEventTimer;
  private BroadcastReceiver mConnectionReceiver = new SupersonicWebView.7(this);
  private String mControllerKeyPressed = "interrupt";
  private FrameLayout mControllerLayout;
  private SSAEnums.ControllerState mControllerState = SSAEnums.ControllerState.None;
  private View mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private FrameLayout mCustomViewContainer;
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
  private CountDownTimer mLoadControllerTimer;
  private String mOWAppKey;
  private String mOWCreditsAppKey;
  private boolean mOWCreditsMiss;
  private String mOWCreditsUserId;
  private Map<String, String> mOWExtraParameters;
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
  private SupersonicWebView.ChromeClient mWebChromeClient;
  
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
    this.mWebChromeClient = new SupersonicWebView.ChromeClient(this, null);
    setWebViewClient(new SupersonicWebView.ViewClient(this, null));
    setWebChromeClient(this.mWebChromeClient);
    setWebViewSettings();
    addJavascriptInterface(new JSInterface(paramContext), "Android");
    setDownloadListener(this);
    setOnTouchListener(new SupersonicWebView.SupersonicWebViewTouchListener(this, null));
  }
  
  private void closeWebView()
  {
    if (this.mChangeListener != null) {
      this.mChangeListener.onCloseRequested();
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
        paramProductType = new HashMap();
        paramProductType.put("applicationKey", this.mOWAppKey);
        paramProductType.put("applicationUserId", this.mOWUserId);
        if (this.mOWExtraParameters != null) {
          paramProductType.putAll(this.mOWExtraParameters);
        }
        str = generateJSToInject("initOfferWall", flatMapToJsonAsString(paramProductType), "onInitOfferWallSuccess", "onInitOfferWallFail");
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
    if (!(localJSONObject instanceof JSONObject)) {
      return localJSONObject.toString();
    }
    return JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
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
    //   2: new 609	org/json/JSONObject
    //   5: dup
    //   6: invokespecial 610	org/json/JSONObject:<init>	()V
    //   9: astore 7
    //   11: ldc_w 683
    //   14: astore_3
    //   15: ldc_w 683
    //   18: astore 5
    //   20: aconst_null
    //   21: astore 6
    //   23: aconst_null
    //   24: astore 4
    //   26: aload_1
    //   27: invokestatic 689	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   30: ifne +367 -> 397
    //   33: aload_1
    //   34: getstatic 530	com/supersonicads/sdk/data/SSAEnums$ProductType:BrandConnect	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   37: invokevirtual 690	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   40: invokevirtual 693	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   43: ifeq +232 -> 275
    //   46: aload_0
    //   47: getfield 328	com/supersonicads/sdk/controller/SupersonicWebView:mBCAppKey	Ljava/lang/String;
    //   50: astore_3
    //   51: aload_0
    //   52: getfield 331	com/supersonicads/sdk/controller/SupersonicWebView:mBCUserId	Ljava/lang/String;
    //   55: astore 5
    //   57: aload_0
    //   58: getfield 335	com/supersonicads/sdk/controller/SupersonicWebView:mBCExtraParameters	Ljava/util/Map;
    //   61: astore 4
    //   63: aload 7
    //   65: ldc_w 585
    //   68: aload_1
    //   69: invokevirtual 695	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   72: pop
    //   73: aload 5
    //   75: invokestatic 689	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   78: ifne +384 -> 462
    //   81: aload 7
    //   83: ldc_w 555
    //   86: invokestatic 645	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   89: aload 5
    //   91: invokestatic 645	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   94: invokevirtual 695	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   97: pop
    //   98: aload_3
    //   99: invokestatic 689	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   102: ifne +417 -> 519
    //   105: aload 7
    //   107: ldc_w 547
    //   110: invokestatic 645	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   113: aload_3
    //   114: invokestatic 645	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   117: invokevirtual 695	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   120: pop
    //   121: aload 4
    //   123: ifnull +414 -> 537
    //   126: aload 4
    //   128: invokeinterface 697 1 0
    //   133: ifne +404 -> 537
    //   136: aload 4
    //   138: invokeinterface 614 1 0
    //   143: invokeinterface 620 1 0
    //   148: astore_1
    //   149: aload_1
    //   150: invokeinterface 626 1 0
    //   155: ifeq +382 -> 537
    //   158: aload_1
    //   159: invokeinterface 630 1 0
    //   164: checkcast 632	java/util/Map$Entry
    //   167: astore_3
    //   168: aload_3
    //   169: invokeinterface 635 1 0
    //   174: checkcast 637	java/lang/String
    //   177: ldc_w 699
    //   180: invokevirtual 693	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   183: ifeq +16 -> 199
    //   186: aload_0
    //   187: aload_3
    //   188: invokeinterface 640 1 0
    //   193: checkcast 637	java/lang/String
    //   196: invokespecial 702	com/supersonicads/sdk/controller/SupersonicWebView:setWebviewCache	(Ljava/lang/String;)V
    //   199: aload 7
    //   201: aload_3
    //   202: invokeinterface 635 1 0
    //   207: checkcast 637	java/lang/String
    //   210: invokestatic 645	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   213: aload_3
    //   214: invokeinterface 640 1 0
    //   219: checkcast 637	java/lang/String
    //   222: invokestatic 645	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   225: invokevirtual 695	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   228: pop
    //   229: goto -80 -> 149
    //   232: astore_3
    //   233: aload_3
    //   234: invokevirtual 705	org/json/JSONException:printStackTrace	()V
    //   237: new 707	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   240: dup
    //   241: invokespecial 708	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   244: astore_3
    //   245: iconst_1
    //   246: anewarray 637	java/lang/String
    //   249: astore 4
    //   251: aload 4
    //   253: iconst_0
    //   254: ldc_w 710
    //   257: aastore
    //   258: aload_3
    //   259: instanceof 712
    //   262: ifne +262 -> 524
    //   265: aload_3
    //   266: aload 4
    //   268: invokevirtual 716	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   271: pop
    //   272: goto -123 -> 149
    //   275: aload_1
    //   276: getstatic 542	com/supersonicads/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   279: invokevirtual 690	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   282: invokevirtual 693	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   285: ifeq +23 -> 308
    //   288: aload_0
    //   289: getfield 345	com/supersonicads/sdk/controller/SupersonicWebView:mISAppKey	Ljava/lang/String;
    //   292: astore_3
    //   293: aload_0
    //   294: getfield 348	com/supersonicads/sdk/controller/SupersonicWebView:mISUserId	Ljava/lang/String;
    //   297: astore 5
    //   299: aload_0
    //   300: getfield 351	com/supersonicads/sdk/controller/SupersonicWebView:mISExtraParameters	Ljava/util/Map;
    //   303: astore 4
    //   305: goto -242 -> 63
    //   308: aload_1
    //   309: getstatic 572	com/supersonicads/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   312: invokevirtual 690	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   315: invokevirtual 693	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   318: ifeq -255 -> 63
    //   321: aload_0
    //   322: getfield 361	com/supersonicads/sdk/controller/SupersonicWebView:mOWAppKey	Ljava/lang/String;
    //   325: astore_3
    //   326: aload_0
    //   327: getfield 365	com/supersonicads/sdk/controller/SupersonicWebView:mOWUserId	Ljava/lang/String;
    //   330: astore 5
    //   332: aload_0
    //   333: getfield 368	com/supersonicads/sdk/controller/SupersonicWebView:mOWExtraParameters	Ljava/util/Map;
    //   336: astore 4
    //   338: goto -275 -> 63
    //   341: astore_1
    //   342: aload_1
    //   343: invokevirtual 705	org/json/JSONException:printStackTrace	()V
    //   346: new 707	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   349: dup
    //   350: invokespecial 708	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   353: astore_1
    //   354: iconst_1
    //   355: anewarray 637	java/lang/String
    //   358: astore 6
    //   360: aload 6
    //   362: iconst_0
    //   363: ldc_w 718
    //   366: aastore
    //   367: aload_1
    //   368: instanceof 712
    //   371: ifne +13 -> 384
    //   374: aload_1
    //   375: aload 6
    //   377: invokevirtual 716	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   380: pop
    //   381: goto -308 -> 73
    //   384: aload_1
    //   385: checkcast 712	android/os/AsyncTask
    //   388: aload 6
    //   390: invokestatic 723	com/newrelic/agent/android/instrumentation/AsyncTaskInstrumentation:execute	(Landroid/os/AsyncTask;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   393: pop
    //   394: goto -321 -> 73
    //   397: iconst_1
    //   398: istore_2
    //   399: aload 6
    //   401: astore 4
    //   403: goto -330 -> 73
    //   406: astore_1
    //   407: aload_1
    //   408: invokevirtual 705	org/json/JSONException:printStackTrace	()V
    //   411: new 707	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   414: dup
    //   415: invokespecial 708	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   418: astore_1
    //   419: iconst_1
    //   420: anewarray 637	java/lang/String
    //   423: astore 5
    //   425: aload 5
    //   427: iconst_0
    //   428: ldc_w 725
    //   431: aastore
    //   432: aload_1
    //   433: instanceof 712
    //   436: ifne +13 -> 449
    //   439: aload_1
    //   440: aload 5
    //   442: invokevirtual 716	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   445: pop
    //   446: goto -348 -> 98
    //   449: aload_1
    //   450: checkcast 712	android/os/AsyncTask
    //   453: aload 5
    //   455: invokestatic 723	com/newrelic/agent/android/instrumentation/AsyncTaskInstrumentation:execute	(Landroid/os/AsyncTask;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   458: pop
    //   459: goto -361 -> 98
    //   462: iconst_1
    //   463: istore_2
    //   464: goto -366 -> 98
    //   467: astore_1
    //   468: aload_1
    //   469: invokevirtual 705	org/json/JSONException:printStackTrace	()V
    //   472: new 707	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   475: dup
    //   476: invokespecial 708	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   479: astore_1
    //   480: iconst_1
    //   481: anewarray 637	java/lang/String
    //   484: astore_3
    //   485: aload_3
    //   486: iconst_0
    //   487: ldc_w 727
    //   490: aastore
    //   491: aload_1
    //   492: instanceof 712
    //   495: ifne +12 -> 507
    //   498: aload_1
    //   499: aload_3
    //   500: invokevirtual 716	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   503: pop
    //   504: goto -383 -> 121
    //   507: aload_1
    //   508: checkcast 712	android/os/AsyncTask
    //   511: aload_3
    //   512: invokestatic 723	com/newrelic/agent/android/instrumentation/AsyncTaskInstrumentation:execute	(Landroid/os/AsyncTask;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   515: pop
    //   516: goto -395 -> 121
    //   519: iconst_1
    //   520: istore_2
    //   521: goto -400 -> 121
    //   524: aload_3
    //   525: checkcast 712	android/os/AsyncTask
    //   528: aload 4
    //   530: invokestatic 723	com/newrelic/agent/android/instrumentation/AsyncTaskInstrumentation:execute	(Landroid/os/AsyncTask;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   533: pop
    //   534: goto -385 -> 149
    //   537: aload 7
    //   539: instanceof 609
    //   542: ifne +25 -> 567
    //   545: aload 7
    //   547: invokevirtual 666	org/json/JSONObject:toString	()Ljava/lang/String;
    //   550: astore_1
    //   551: iconst_2
    //   552: anewarray 210	java/lang/Object
    //   555: dup
    //   556: iconst_0
    //   557: aload_1
    //   558: aastore
    //   559: dup
    //   560: iconst_1
    //   561: iload_2
    //   562: invokestatic 733	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   565: aastore
    //   566: areturn
    //   567: aload 7
    //   569: checkcast 609	org/json/JSONObject
    //   572: invokestatic 671	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
    //   575: astore_1
    //   576: goto -25 -> 551
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	579	0	this	SupersonicWebView
    //   0	579	1	paramString	String
    //   1	561	2	bool	boolean
    //   14	200	3	localObject1	Object
    //   232	2	3	localJSONException	JSONException
    //   244	281	3	localObject2	Object
    //   24	505	4	localObject3	Object
    //   18	436	5	localObject4	Object
    //   21	379	6	arrayOfString	String[]
    //   9	559	7	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   199	229	232	org/json/JSONException
    //   63	73	341	org/json/JSONException
    //   81	98	406	org/json/JSONException
    //   105	121	467	org/json/JSONException
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
        paramString1 = JSONArrayInstrumentation.init(paramString1);
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
      if (!(localJSONObject1 instanceof JSONObject))
      {
        paramString1 = localJSONObject1.toString();
        return new Object[] { paramString1, Boolean.valueOf(bool) };
        bool = true;
        localJSONObject1.put("error", "requestId is null or empty");
        continue;
        bool = true;
        localJSONObject1.put("error", "appIds is null or empty");
      }
      else
      {
        paramString1 = JSONObjectInstrumentation.toString((JSONObject)localJSONObject1);
        continue;
        i += 1;
      }
    }
  }
  
  private Object[] getDeviceParams(Context paramContext)
  {
    bool3 = false;
    boolean bool2 = false;
    localObject1 = DeviceProperties.getInstance(paramContext);
    localJSONObject = new JSONObject();
    bool1 = bool3;
    for (;;)
    {
      try
      {
        localJSONObject.put("appOrientation", "none");
        bool1 = bool3;
        localObject2 = ((DeviceProperties)localObject1).getDeviceOem();
        if (localObject2 != null)
        {
          bool1 = bool3;
          localJSONObject.put(SDKUtils.encodeString("deviceOEM"), SDKUtils.encodeString((String)localObject2));
        }
        bool1 = bool3;
        localObject2 = ((DeviceProperties)localObject1).getDeviceModel();
        if (localObject2 == null) {
          continue;
        }
        bool1 = bool3;
        localJSONObject.put(SDKUtils.encodeString("deviceModel"), SDKUtils.encodeString((String)localObject2));
        bool1 = bool2;
        SDKUtils.loadGoogleAdvertiserInfo(paramContext);
        bool1 = bool2;
        localObject2 = SDKUtils.getAdvertiserId();
        bool1 = bool2;
        bool3 = SDKUtils.isLimitAdTrackingEnabled();
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject2))
        {
          bool1 = bool2;
          Logger.i(this.TAG, "add AID and LAT");
          bool1 = bool2;
          localJSONObject.put("isLimitAdTrackingEnabled", Boolean.valueOf(bool3));
          bool1 = bool2;
          localJSONObject.put("deviceIds" + "[" + "AID" + "]", SDKUtils.encodeString((String)localObject2));
        }
        bool1 = bool2;
        localObject2 = ((DeviceProperties)localObject1).getDeviceOsType();
        if (localObject2 == null) {
          continue;
        }
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("deviceOs"), SDKUtils.encodeString((String)localObject2));
        bool1 = bool2;
        localObject2 = Integer.toString(((DeviceProperties)localObject1).getDeviceOsVersion());
        if (localObject2 == null) {
          continue;
        }
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("deviceOSVersion"), localObject2);
        bool1 = bool2;
        localObject2 = ((DeviceProperties)localObject1).getSupersonicSdkVersion();
        if (localObject2 != null)
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("SDKVersion"), SDKUtils.encodeString((String)localObject2));
        }
        bool1 = bool2;
        if (((DeviceProperties)localObject1).getDeviceCarrier() != null)
        {
          bool1 = bool2;
          if (((DeviceProperties)localObject1).getDeviceCarrier().length() > 0)
          {
            bool1 = bool2;
            localJSONObject.put(SDKUtils.encodeString("mobileCarrier"), SDKUtils.encodeString(((DeviceProperties)localObject1).getDeviceCarrier()));
          }
        }
        bool1 = bool2;
        localObject1 = SDKUtils.getConnectionType(paramContext);
        bool1 = bool2;
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          continue;
        }
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("connectionType"), SDKUtils.encodeString((String)localObject1));
        bool1 = bool2;
        localObject1 = paramContext.getResources().getConfiguration().locale.getLanguage();
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("deviceLanguage"), SDKUtils.encodeString(((String)localObject1).toUpperCase()));
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
        localObject1 = String.valueOf(SDKUtils.getDeviceWidth());
        if (localObject1 == null) {
          continue;
        }
        bool1 = bool2;
        localObject2 = new StringBuilder();
        bool1 = bool2;
        ((StringBuilder)localObject2).append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("width")).append("]");
        bool1 = bool2;
        localJSONObject.put(((StringBuilder)localObject2).toString(), SDKUtils.encodeString((String)localObject1));
        bool1 = bool2;
        localObject1 = String.valueOf(SDKUtils.getDeviceHeight());
        if (localObject1 == null) {
          continue;
        }
        bool1 = bool2;
        localObject2 = new StringBuilder();
        bool1 = bool2;
        ((StringBuilder)localObject2).append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("height")).append("]");
        bool1 = bool2;
        localJSONObject.put(((StringBuilder)localObject2).toString(), SDKUtils.encodeString((String)localObject1));
        bool1 = bool2;
        localObject1 = SDKUtils.getPackageName(getBaseContext());
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("bundleId"), SDKUtils.encodeString((String)localObject1));
        }
        bool1 = bool2;
        localObject1 = String.valueOf(SDKUtils.getDeviceScale());
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("deviceScreenScale"), SDKUtils.encodeString((String)localObject1));
        }
        bool1 = bool2;
        localObject1 = String.valueOf(DeviceStatus.isRootedDevice());
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("unLocked"), SDKUtils.encodeString((String)localObject1));
        }
        bool1 = bool2;
        float f = DeviceProperties.getInstance(paramContext).getDeviceVolume(paramContext);
        bool3 = bool2;
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("deviceVolume"), f);
          bool3 = bool2;
        }
      }
      catch (JSONException paramContext)
      {
        paramContext.printStackTrace();
        localObject1 = new SupersonicAsyncHttpRequestTask();
        Object localObject2 = new String[1];
        localObject2[0] = ("https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName());
        if ((localObject1 instanceof AsyncTask)) {
          continue;
        }
        ((SupersonicAsyncHttpRequestTask)localObject1).execute((Object[])localObject2);
        bool3 = bool1;
        continue;
        AsyncTaskInstrumentation.execute((AsyncTask)localObject1, (Object[])localObject2);
        bool3 = bool1;
        continue;
        paramContext = JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
        continue;
      }
      if ((localJSONObject instanceof JSONObject)) {
        continue;
      }
      paramContext = localJSONObject.toString();
      return new Object[] { paramContext, Boolean.valueOf(bool3) };
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
    this.mControllerLayout = new FrameLayout(paramContext);
    this.mCustomViewContainer = new FrameLayout(paramContext);
    FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(-1, -1);
    this.mCustomViewContainer.setLayoutParams(localLayoutParams2);
    this.mCustomViewContainer.setVisibility(8);
    paramContext = new FrameLayout(paramContext);
    paramContext.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    paramContext.addView(this);
    this.mControllerLayout.addView(this.mCustomViewContainer, localLayoutParams1);
    this.mControllerLayout.addView(paramContext);
  }
  
  private void initProduct(String paramString1, String paramString2, SSAEnums.ProductType paramProductType, String paramString3)
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
  
  private void injectJavascript(String paramString)
  {
    Object localObject2 = "empty";
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
      ((Activity)localObject1).runOnUiThread(new SupersonicWebView.5(this, paramString, (StringBuilder)localObject2));
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
    paramString = new SupersonicAsyncHttpRequestTask();
    Object localObject1 = new String[1];
    localObject1[0] = "https://www.supersonicads.com/mobile/sdk5/log?method=contextIsNotActivity";
    if (!(paramString instanceof AsyncTask))
    {
      paramString.execute((Object[])localObject1);
      return;
    }
    AsyncTaskInstrumentation.execute((AsyncTask)paramString, (Object[])localObject1);
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
    if (!(localJSONObject instanceof JSONObject)) {
      return localJSONObject.toString();
    }
    return JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
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
        paramString2 = new SupersonicAsyncHttpRequestTask();
        paramString3 = new String[1];
        paramString3[0] = ("https://www.supersonicads.com/mobile/sdk5/log?method=" + paramString1.getStackTrace()[0].getMethodName());
        if (!(paramString2 instanceof AsyncTask)) {
          paramString2.execute(paramString3);
        } else {
          AsyncTaskInstrumentation.execute((AsyncTask)paramString2, paramString3);
        }
      }
    }
    if (!(localJSONObject instanceof JSONObject)) {
      return localJSONObject.toString();
    }
    return JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
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
        paramString2 = JSONObjectInstrumentation.init(paramString1).put("errMsg", paramString2);
        if (!(paramString2 instanceof JSONObject))
        {
          str1 = paramString2.toString();
          label92:
          paramString1 = str1;
          if (TextUtils.isEmpty(paramString3)) {}
        }
        try
        {
          paramString1 = JSONObjectInstrumentation.init(str1).put("errCode", paramString3);
          if (!(paramString1 instanceof JSONObject)) {}
          for (paramString1 = paramString1.toString();; paramString1 = JSONObjectInstrumentation.toString((JSONObject)paramString1))
          {
            injectJavascript(generateJSToInject((String)localObject, paramString1));
            return;
            if (TextUtils.isEmpty(str2)) {
              break;
            }
            localObject = str2;
            break;
            str1 = JSONObjectInstrumentation.toString((JSONObject)paramString2);
            break label92;
          }
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
    switch (SupersonicWebView.8.$SwitchMap$com$supersonicads$sdk$data$SSAEnums$ProductType[paramProductType.ordinal()])
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
      str = "Init OW";
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
      SupersonicAsyncHttpRequestTask localSupersonicAsyncHttpRequestTask = new SupersonicAsyncHttpRequestTask();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "https://www.supersonicads.com/mobile/sdk5/log?method=setWebViewSettings";
      if (!(localSupersonicAsyncHttpRequestTask instanceof AsyncTask))
      {
        localSupersonicAsyncHttpRequestTask.execute(arrayOfString);
        return;
      }
      AsyncTaskInstrumentation.execute((AsyncTask)localSupersonicAsyncHttpRequestTask, arrayOfString);
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
  
  private void toastingErrMsg(String paramString1, String paramString2)
  {
    paramString2 = new SSAObj(paramString2).getString("errMsg");
    if (!TextUtils.isEmpty(paramString2))
    {
      Context localContext = getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new SupersonicWebView.6(this, paramString1, paramString2));
      }
    }
  }
  
  private void triggerOnControllerInitProductFail(String paramString, SSAEnums.ProductType paramProductType)
  {
    if (shouldNotifyDeveloper(paramProductType.toString()))
    {
      Context localContext = getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new SupersonicWebView.4(this, paramProductType, paramString));
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
    this.mGlobalControllerTimer = new SupersonicWebView.1(this, 40000L, 1000L).start();
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
  
  public Context getBaseContext()
  {
    return ((MutableContextWrapper)getContext()).getBaseContext();
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
    return this.mControllerLayout;
  }
  
  public void getOfferWallCredits(String paramString1, String paramString2, OnOfferWallListener paramOnOfferWallListener)
  {
    this.mOWCreditsAppKey = paramString1;
    this.mOWCreditsUserId = paramString2;
    this.mOnOfferWallListener = paramOnOfferWallListener;
    initProduct(this.mOWCreditsAppKey, this.mOWCreditsUserId, SSAEnums.ProductType.OfferWallCredits, "Show OW Credits");
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
    this.mBCExtraParameters = paramMap;
    this.mOnRewardedVideoListener = paramOnRewardedVideoListener;
    this.mSavedState.setRewardedVideoAppKey(this.mBCAppKey);
    this.mSavedState.setRewardedVideoUserId(this.mBCUserId);
    this.mSavedState.setRewardedVideoExtraParams(this.mBCExtraParameters);
    initProduct(this.mBCAppKey, this.mBCUserId, SSAEnums.ProductType.BrandConnect, "Init BC");
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
    initProduct(this.mISAppKey, this.mISUserId, SSAEnums.ProductType.Interstitial, "Init IS");
  }
  
  public void initOfferWall(String paramString1, String paramString2, Map<String, String> paramMap, OnOfferWallListener paramOnOfferWallListener)
  {
    this.mOWAppKey = paramString1;
    this.mOWUserId = paramString2;
    this.mOWExtraParameters = paramMap;
    this.mOnOfferWallListener = paramOnOfferWallListener;
    this.mSavedState.setOfferWallExtraParams(this.mOWExtraParameters);
    this.mSavedState.setOfferwallReportInit(true);
    initProduct(this.mOWAppKey, this.mOWUserId, SSAEnums.ProductType.OfferWall, "Init OW");
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
        this.mLoadControllerTimer = new SupersonicWebView.2(this, 10000L, 1000L, paramInt).start();
      }
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        String[] arrayOfString1;
        for (;;)
        {
          String str;
          loadUrl(str);
          Logger.i(this.TAG, "load(): " + str);
          return;
          localThrowable1 = localThrowable1;
          Logger.e(this.TAG, "WebViewController:: load: " + localThrowable1.toString());
          localSupersonicAsyncHttpRequestTask = new SupersonicAsyncHttpRequestTask();
          arrayOfString1 = new String[1];
          arrayOfString1[0] = "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadBlank";
          if ((localSupersonicAsyncHttpRequestTask instanceof AsyncTask)) {
            break;
          }
          localSupersonicAsyncHttpRequestTask.execute(arrayOfString1);
        }
        AsyncTaskInstrumentation.execute((AsyncTask)localSupersonicAsyncHttpRequestTask, arrayOfString1);
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          Logger.e(this.TAG, "WebViewController:: load: " + localThrowable2.toString());
          localObject = new SupersonicAsyncHttpRequestTask();
          String[] arrayOfString2 = new String[1];
          arrayOfString2[0] = "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadWithPath";
          if (!(localObject instanceof AsyncTask)) {
            ((SupersonicAsyncHttpRequestTask)localObject).execute(arrayOfString2);
          } else {
            AsyncTaskInstrumentation.execute((AsyncTask)localObject, arrayOfString2);
          }
        }
      }
      Logger.i(this.TAG, "load(): Mobile Controller HTML Does not exist");
      SupersonicAsyncHttpRequestTask localSupersonicAsyncHttpRequestTask = new SupersonicAsyncHttpRequestTask();
      Object localObject = new String[1];
      localObject[0] = "https://www.supersonicads.com/mobile/sdk5/log?method=htmlControllerDoesNotExistOnFileSystem";
      if (!(localSupersonicAsyncHttpRequestTask instanceof AsyncTask))
      {
        localSupersonicAsyncHttpRequestTask.execute((Object[])localObject);
        return;
      }
      AsyncTaskInstrumentation.execute((AsyncTask)localSupersonicAsyncHttpRequestTask, (Object[])localObject);
    }
  }
  
  public void loadInterstitial()
  {
    if (!isInterstitialAdAvailable())
    {
      this.mSavedState.setReportLoadInterstitial(true);
      injectJavascript(generateJSToInject("loadInterstitial", "onLoadInterstitialSuccess", "onLoadInterstitialFail"));
    }
    Context localContext;
    do
    {
      do
      {
        return;
      } while (!shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()));
      localContext = getBaseContext();
    } while (!(localContext instanceof Activity));
    ((Activity)localContext).runOnUiThread(new SupersonicWebView.3(this));
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
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (!this.mChangeListener.onBackButtonPressed()) {
        return super.onKeyDown(paramInt, paramKeyEvent);
      }
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
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
      SupersonicAsyncHttpRequestTask localSupersonicAsyncHttpRequestTask = new SupersonicAsyncHttpRequestTask();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "https://www.supersonicads.com/mobile/sdk5/log?method=webviewPause";
      if (!(localSupersonicAsyncHttpRequestTask instanceof AsyncTask))
      {
        localSupersonicAsyncHttpRequestTask.execute(arrayOfString);
        return;
      }
      AsyncTaskInstrumentation.execute((AsyncTask)localSupersonicAsyncHttpRequestTask, arrayOfString);
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
            break label448;
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
              if (this.mOnInitInterstitialListener != null) {}
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
          this.mOnInitInterstitialListener.onInterstitialClose();
        }
      }
      if (i == SSAEnums.ProductType.OfferWall.ordinal())
      {
        Log.d(this.TAG, "onOWAdClosed()");
        if (this.mOnOfferWallListener != null)
        {
          this.mOnOfferWallListener.onOWAdClosed();
          continue;
          label448:
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
      SupersonicAsyncHttpRequestTask localSupersonicAsyncHttpRequestTask = new SupersonicAsyncHttpRequestTask();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "https://www.supersonicads.com/mobile/sdk5/log?method=webviewResume";
      if (!(localSupersonicAsyncHttpRequestTask instanceof AsyncTask))
      {
        localSupersonicAsyncHttpRequestTask.execute(arrayOfString);
        return;
      }
      AsyncTaskInstrumentation.execute((AsyncTask)localSupersonicAsyncHttpRequestTask, arrayOfString);
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
  
  public void setOnWebViewControllerChangeListener(OnWebViewChangeListener paramOnWebViewChangeListener)
  {
    this.mChangeListener = paramOnWebViewChangeListener;
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
  
  public void showOfferWall(Map<String, String> paramMap)
  {
    this.mOWExtraParameters = paramMap;
    injectJavascript(generateJSToInject("showOfferWall", "onShowOfferWallSuccess", "onShowOfferWallFail"));
  }
  
  public void unregisterConnectionReceiver(Context paramContext)
  {
    try
    {
      paramContext.unregisterReceiver(this.mConnectionReceiver);
      return;
    }
    catch (Exception paramContext)
    {
      Log.e(this.TAG, "unregisterConnectionReceiver - " + paramContext);
      SupersonicAsyncHttpRequestTask localSupersonicAsyncHttpRequestTask = new SupersonicAsyncHttpRequestTask();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = ("https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName());
      if (!(localSupersonicAsyncHttpRequestTask instanceof AsyncTask))
      {
        localSupersonicAsyncHttpRequestTask.execute(arrayOfString);
        return;
      }
      AsyncTaskInstrumentation.execute((AsyncTask)localSupersonicAsyncHttpRequestTask, arrayOfString);
      return;
    }
    catch (IllegalArgumentException paramContext) {}
  }
  
  public void viewableChange(boolean paramBoolean, String paramString)
  {
    injectJavascript(generateJSToInject("viewableChange", parseToJson("webview", paramString, null, null, null, null, null, null, "isViewable", paramBoolean)));
  }
  
  public class JSInterface
  {
    volatile int udiaResults = 0;
    
    public JSInterface(Context paramContext) {}
    
    private void injectGetUDIA(String paramString, JSONArray paramJSONArray)
    {
      SupersonicWebView localSupersonicWebView;
      if (!TextUtils.isEmpty(paramString))
      {
        localSupersonicWebView = SupersonicWebView.this;
        if ((paramJSONArray instanceof JSONArray)) {
          break label44;
        }
      }
      label44:
      for (paramJSONArray = paramJSONArray.toString();; paramJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramJSONArray))
      {
        paramString = localSupersonicWebView.generateJSToInject(paramString, paramJSONArray, "onGetUDIASuccess", "onGetUDIAFail");
        SupersonicWebView.this.injectJavascript(paramString);
        return;
      }
    }
    
    private void sendResults(String paramString, JSONArray paramJSONArray)
    {
      Logger.i(SupersonicWebView.this.TAG, "sendResults: " + this.udiaResults);
      if (this.udiaResults <= 0) {
        injectGetUDIA(paramString, paramJSONArray);
      }
    }
    
    private void setInterstitialAvailability(boolean paramBoolean)
    {
      SupersonicWebView.access$5402(SupersonicWebView.this, Boolean.valueOf(paramBoolean));
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        SupersonicWebView.this.toastingErrMsg("onInterstitialAvailability", String.valueOf(SupersonicWebView.this.mIsInterstitialAvailable));
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
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.9(this));
        }
        SupersonicWebView.this.toastingErrMsg("onInterstitialAdClicked", paramString);
      }
    }
    
    @JavascriptInterface
    public void adCredited(String paramString)
    {
      Log.d(SupersonicWebView.this.PUB_TAG, "adCredited(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str1 = ((SSAObj)localObject).getString("credits");
      int i;
      String str5;
      int j;
      label85:
      String str4;
      boolean bool2;
      String str3;
      boolean bool3;
      String str2;
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
      for (boolean bool1 = true;; bool1 = bool3)
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
        ((Activity)localObject).runOnUiThread(new SupersonicWebView.JSInterface.2(this, str4, i, bool1, j, bool2, str3, str1, str2, paramString));
        return;
        label349:
        SupersonicWebView.this.responseBack(paramString, false, "Controller signature is not equal to SDK signature", null);
      }
    }
    
    @JavascriptInterface
    public void adUnitsReady(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "adUnitsReady(" + paramString + ")");
      AdUnitsReady localAdUnitsReady = new AdUnitsReady(paramString);
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
      ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.1(this, localAdUnitsReady, paramString));
    }
    
    @JavascriptInterface
    public void alert(String paramString) {}
    
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
        JSONObject localJSONObject = new JSONObject();
        paramString = new JSONObject();
        paramString.put("frequency", "weekly");
        localJSONObject.put("id", "testevent723GDf84");
        localJSONObject.put("description", "Watch this crazy showInterstitial on cannel 5!");
        localJSONObject.put("start", "2014-02-01T20:00:00-8:00");
        localJSONObject.put("end", "2014-06-30T20:00:00-8:00");
        localJSONObject.put("status", "pending");
        if (!(paramString instanceof JSONObject)) {}
        for (paramString = paramString.toString();; paramString = JSONObjectInstrumentation.toString((JSONObject)paramString))
        {
          localJSONObject.put("recurrence", paramString);
          localJSONObject.put("reminder", "2014-02-01T19:50:00-8:00");
          return;
        }
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
      boolean bool1 = ((Boolean)paramString.get("display")).booleanValue();
      String str2 = paramString.getString("productType");
      boolean bool2 = paramString.getBoolean("standaloneView");
      int i = 0;
      if (bool1)
      {
        if (SupersonicWebView.this.getState() != SupersonicWebView.State.Display)
        {
          SupersonicWebView.this.setState(SupersonicWebView.State.Display);
          Logger.i(SupersonicWebView.this.TAG, "State: " + SupersonicWebView.this.mState);
          Context localContext = SupersonicWebView.this.getBaseContext();
          String str1 = SupersonicWebView.this.getOrientationState();
          int j = SDKUtils.getApplicationRotation(localContext);
          if (bool2)
          {
            paramString = new ControllerView(localContext);
            paramString.addView(SupersonicWebView.this.mControllerLayout);
            paramString.showInterstitial(SupersonicWebView.this);
            return;
          }
          if (str2.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
            paramString = new Intent(localContext, InterstitialActivity.class);
          }
          for (;;)
          {
            if ((i != 0) && (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.BrandConnect.toString()))) {
              SupersonicWebView.this.mOnRewardedVideoListener.onRVAdOpened();
            }
            paramString.setFlags(536870912);
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
      paramString = SupersonicWebView.this.getDeviceParams(SupersonicWebView.this.getBaseContext());
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
      String str = SupersonicWebView.this.extractSuccessFunctionToCall(paramString);
      paramString = SDKUtils.getOrientation(SupersonicWebView.this.getBaseContext());
      if (!(paramString instanceof JSONObject)) {}
      for (paramString = paramString.toString();; paramString = JSONObjectInstrumentation.toString((JSONObject)paramString))
      {
        if (!TextUtils.isEmpty(str))
        {
          paramString = SupersonicWebView.this.generateJSToInject(str, paramString, "onGetOrientationSuccess", "onGetOrientationFail");
          SupersonicWebView.this.injectJavascript(paramString);
        }
        return;
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
        SupersonicWebView.access$1502(SupersonicWebView.this, SSAEnums.ControllerState.Ready);
        SupersonicWebView.this.mGlobalControllerTimer.cancel();
        SupersonicWebView.this.mLoadControllerTimer.cancel();
        if (SupersonicWebView.this.mRVmiss) {
          SupersonicWebView.this.initBrandConnect(SupersonicWebView.this.mBCAppKey, SupersonicWebView.this.mBCUserId, SupersonicWebView.this.mBCExtraParameters, SupersonicWebView.this.mOnRewardedVideoListener);
        }
        if (SupersonicWebView.this.mISmiss) {
          SupersonicWebView.this.initInterstitial(SupersonicWebView.this.mISAppKey, SupersonicWebView.this.mISUserId, SupersonicWebView.this.mISExtraParameters, SupersonicWebView.this.mOnInitInterstitialListener);
        }
        if (SupersonicWebView.this.mOWmiss) {
          SupersonicWebView.this.initOfferWall(SupersonicWebView.this.mOWAppKey, SupersonicWebView.this.mOWUserId, SupersonicWebView.this.mOWExtraParameters, SupersonicWebView.this.mOnOfferWallListener);
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
          SupersonicWebView.access$1502(SupersonicWebView.this, SSAEnums.ControllerState.Loaded);
          return;
        }
        if (!"failed".equalsIgnoreCase(paramString)) {
          break;
        }
        SupersonicWebView.access$1502(SupersonicWebView.this, SSAEnums.ControllerState.Failed);
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
    public void onAdWindowsClosed(String paramString)
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
            ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.19(this, paramString));
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
      String str = new SSAObj(paramString).getString("errMsg");
      Context localContext = SupersonicWebView.this.getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.17(this, str));
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
        ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.16(this));
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
      String str = new SSAObj(paramString).getString("errMsg");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.18(this, str));
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
      String str = new SSAObj(paramString).getString("errMsg");
      SupersonicWebView.this.mSavedState.setRewardedVideoInitSuccess(false);
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.BrandConnect.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.3(this, str));
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
      String str = new SSAObj(paramString).getString("errMsg");
      if (SupersonicWebView.this.mSavedState.reportInitInterstitial())
      {
        SupersonicWebView.this.mSavedState.setReportInitInterstitial(false);
        if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
        {
          Context localContext = SupersonicWebView.this.getBaseContext();
          if ((localContext instanceof Activity)) {
            ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.8(this, str));
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
            ((Activity)paramString).runOnUiThread(new SupersonicWebView.JSInterface.7(this));
          }
        }
      }
    }
    
    @JavascriptInterface
    public void onInitOfferWallFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onInitOfferWallFail(" + paramString + ")");
      SupersonicWebView.this.mSavedState.setOfferwallInitSuccess(false);
      String str = new SSAObj(paramString).getString("errMsg");
      if (SupersonicWebView.this.mSavedState.reportInitOfferwall())
      {
        SupersonicWebView.this.mSavedState.setOfferwallReportInit(false);
        if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
        {
          Context localContext = SupersonicWebView.this.getBaseContext();
          if ((localContext instanceof Activity)) {
            ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.12(this, str));
          }
        }
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onInitOfferWallFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitOfferWallSuccess(String paramString)
    {
      SupersonicWebView.this.toastingErrMsg("onInitOfferWallSuccess", "true");
      SupersonicWebView.this.mSavedState.setOfferwallInitSuccess(true);
      if (SupersonicWebView.this.mSavedState.reportInitOfferwall())
      {
        SupersonicWebView.this.mSavedState.setOfferwallReportInit(false);
        if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
        {
          paramString = SupersonicWebView.this.getBaseContext();
          if ((paramString instanceof Activity)) {
            ((Activity)paramString).runOnUiThread(new SupersonicWebView.JSInterface.11(this));
          }
        }
      }
    }
    
    @JavascriptInterface
    public void onLoadInterstitialFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onLoadInterstitialFail(" + paramString + ")");
      String str = new SSAObj(paramString).getString("errMsg");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        paramString = SupersonicWebView.this.getBaseContext();
        if ((paramString instanceof Activity)) {
          ((Activity)paramString).runOnUiThread(new SupersonicWebView.JSInterface.14(this, str));
        }
      }
      SupersonicWebView.this.toastingErrMsg("onLoadInterstitialFail", "true");
    }
    
    @JavascriptInterface
    public void onLoadInterstitialSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onLoadInterstitialSuccess(" + paramString + ")");
      setInterstitialAvailability(true);
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        paramString = SupersonicWebView.this.getBaseContext();
        if ((paramString instanceof Activity)) {
          ((Activity)paramString).runOnUiThread(new SupersonicWebView.JSInterface.13(this));
        }
      }
      SupersonicWebView.this.toastingErrMsg("onLoadInterstitialSuccess", "true");
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
      String str = new SSAObj(paramString).getString("errMsg");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.BrandConnect.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.4(this, str));
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
      setInterstitialAvailability(false);
      String str = new SSAObj(paramString).getString("errMsg");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.15(this, str));
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
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.10(this));
        }
        SupersonicWebView.this.toastingErrMsg("onShowInterstitialSuccess", paramString);
      }
      setInterstitialAvailability(false);
    }
    
    @JavascriptInterface
    public void onShowOfferWallFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onShowOfferWallFail(" + paramString + ")");
      String str = new SSAObj(paramString).getString("errMsg");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.6(this, str));
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
      String str = SDKUtils.getValueFromJsonObject(paramString, "placementId");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.5(this, str));
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
        SupersonicWebView.this.mChangeListener.onOrientationChanged(paramString, i);
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
  
  public static enum State
  {
    Display,  Gone;
    
    private State() {}
  }
}
