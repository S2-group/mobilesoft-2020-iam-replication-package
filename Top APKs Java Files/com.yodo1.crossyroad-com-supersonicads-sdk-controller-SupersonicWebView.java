package com.supersonicads.sdk.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.MutableContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
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
import com.supersonic.environment.ApplicationContext;
import com.supersonic.environment.ConnectivityService;
import com.supersonic.environment.DeviceStatus;
import com.supersonic.environment.LocationService;
import com.supersonic.environment.UrlHandler;
import com.supersonicads.sdk.agent.SupersonicAdsPublisherAgent;
import com.supersonicads.sdk.data.AdUnitsReady;
import com.supersonicads.sdk.data.AdUnitsState;
import com.supersonicads.sdk.data.DemandSource;
import com.supersonicads.sdk.data.SSABCParameters;
import com.supersonicads.sdk.data.SSAEnums.ControllerState;
import com.supersonicads.sdk.data.SSAEnums.DebugMode;
import com.supersonicads.sdk.data.SSAEnums.ProductType;
import com.supersonicads.sdk.data.SSAFile;
import com.supersonicads.sdk.data.SSAObj;
import com.supersonicads.sdk.listeners.DSRewardedVideoListener;
import com.supersonicads.sdk.listeners.OnGenericFunctionListener;
import com.supersonicads.sdk.listeners.OnInterstitialListener;
import com.supersonicads.sdk.listeners.OnOfferWallListener;
import com.supersonicads.sdk.listeners.OnWebViewChangeListener;
import com.supersonicads.sdk.precache.DownloadManager;
import com.supersonicads.sdk.precache.DownloadManager.OnPreCacheCompletion;
import com.supersonicads.sdk.utils.DeviceProperties;
import com.supersonicads.sdk.utils.Logger;
import com.supersonicads.sdk.utils.SDKUtils;
import com.supersonicads.sdk.utils.SupersonicAsyncHttpRequestTask;
import com.supersonicads.sdk.utils.SupersonicSharedPrefHelper;
import com.supersonicads.sdk.utils.SupersonicStorageUtils;
import java.io.File;
import java.util.Collection;
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
  private String mCacheDirectory;
  private OnWebViewChangeListener mChangeListener;
  private CountDownTimer mCloseEventTimer;
  private BroadcastReceiver mConnectionReceiver = new SupersonicWebView.7(this);
  private String mControllerKeyPressed = "interrupt";
  private FrameLayout mControllerLayout;
  private SSAEnums.ControllerState mControllerState = SSAEnums.ControllerState.None;
  Context mCurrentActivityContext;
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
  private boolean mIsImmersive = false;
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
  private DSRewardedVideoListener mOnRewardedVideoListener;
  private String mOrientationState;
  private String mRVAppKey;
  private String mRVUserId;
  private String mRequestParameters;
  private AdUnitsState mSavedState;
  private Object mSavedStateLocker = new Object();
  private State mState;
  Handler mUiHandler;
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
    super(paramContext.getApplicationContext());
    Logger.i(this.TAG, "C'tor");
    this.mCacheDirectory = SupersonicStorageUtils.initializeCacheDirectory(paramContext.getApplicationContext());
    this.mCurrentActivityContext = paramContext;
    initLayout(this.mCurrentActivityContext);
    this.mSavedState = new AdUnitsState();
    this.downloadManager = DownloadManager.getInstance(this.mCacheDirectory);
    this.downloadManager.setOnPreCacheCompletion(this);
    this.mWebChromeClient = new SupersonicWebView.ChromeClient(this, null);
    setWebViewClient(new SupersonicWebView.ViewClient(this, null));
    setWebChromeClient(this.mWebChromeClient);
    setWebViewSettings();
    addJavascriptInterface(new JSInterface(paramContext), "Android");
    setDownloadListener(this);
    setOnTouchListener(new SupersonicWebView.SupersonicWebViewTouchListener(this, null));
    this.mUiHandler = new Handler(Looper.getMainLooper());
  }
  
  private void closeWebView()
  {
    if (this.mChangeListener != null) {
      this.mChangeListener.onCloseRequested();
    }
  }
  
  private void createInitProductJSMethod(SSAEnums.ProductType paramProductType, String paramString)
  {
    HashMap localHashMap = null;
    if (paramProductType == SSAEnums.ProductType.RewardedVideo)
    {
      paramProductType = SupersonicAdsPublisherAgent.getInstance((Activity)getCurrentActivityContext()).getDemandSourceByName(paramString);
      localHashMap = new HashMap();
      localHashMap.put("applicationKey", this.mRVAppKey);
      localHashMap.put("applicationUserId", this.mRVUserId);
      if (paramProductType != null)
      {
        if (paramProductType.getExtraParams() != null) {
          localHashMap.putAll(paramProductType.getExtraParams());
        }
        if (!TextUtils.isEmpty(paramString)) {
          localHashMap.put("demandSourceName", paramString);
        }
      }
      paramString = generateJSToInject("initRewardedVideo", flatMapToJsonAsString(localHashMap), "onInitRewardedVideoSuccess", "onInitRewardedVideoFail");
    }
    for (;;)
    {
      if (paramString != null) {
        injectJavascript(paramString);
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
        paramString = generateJSToInject("initInterstitial", flatMapToJsonAsString(paramProductType), "onInitInterstitialSuccess", "onInitInterstitialFail");
      }
      else if (paramProductType == SSAEnums.ProductType.OfferWall)
      {
        paramProductType = new HashMap();
        paramProductType.put("applicationKey", this.mOWAppKey);
        paramProductType.put("applicationUserId", this.mOWUserId);
        if (this.mOWExtraParameters != null) {
          paramProductType.putAll(this.mOWExtraParameters);
        }
        paramString = generateJSToInject("initOfferWall", flatMapToJsonAsString(paramProductType), "onInitOfferWallSuccess", "onInitOfferWallFail");
      }
      else
      {
        paramString = localHashMap;
        if (paramProductType == SSAEnums.ProductType.OfferWallCredits) {
          paramString = generateJSToInject("getUserCredits", parseToJson("productType", "OfferWall", "applicationKey", this.mOWCreditsAppKey, "applicationUserId", this.mOWCreditsUserId, null, null, null, false), "null", "onGetUserCreditsFail");
        }
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
  private Object[] getApplicationParams(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: new 654	org/json/JSONObject
    //   5: dup
    //   6: invokespecial 655	org/json/JSONObject:<init>	()V
    //   9: astore 9
    //   11: ldc_w 724
    //   14: astore 4
    //   16: ldc_w 724
    //   19: astore 5
    //   21: aconst_null
    //   22: astore 7
    //   24: aconst_null
    //   25: astore 6
    //   27: aload_1
    //   28: invokestatic 593	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   31: ifne +374 -> 405
    //   34: aload_1
    //   35: getstatic 550	com/supersonicads/sdk/data/SSAEnums$ProductType:RewardedVideo	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   38: invokevirtual 725	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   41: invokevirtual 728	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   44: ifeq +259 -> 303
    //   47: aload_0
    //   48: getfield 350	com/supersonicads/sdk/controller/SupersonicWebView:mRVAppKey	Ljava/lang/String;
    //   51: astore 7
    //   53: aload_0
    //   54: getfield 353	com/supersonicads/sdk/controller/SupersonicWebView:mRVUserId	Ljava/lang/String;
    //   57: astore 8
    //   59: aload_0
    //   60: invokevirtual 553	com/supersonicads/sdk/controller/SupersonicWebView:getCurrentActivityContext	()Landroid/content/Context;
    //   63: checkcast 555	android/app/Activity
    //   66: invokestatic 560	com/supersonicads/sdk/agent/SupersonicAdsPublisherAgent:getInstance	(Landroid/app/Activity;)Lcom/supersonicads/sdk/agent/SupersonicAdsPublisherAgent;
    //   69: aload_2
    //   70: invokevirtual 564	com/supersonicads/sdk/agent/SupersonicAdsPublisherAgent:getDemandSourceByName	(Ljava/lang/String;)Lcom/supersonicads/sdk/data/DemandSource;
    //   73: astore 10
    //   75: aload 7
    //   77: astore_2
    //   78: aload 6
    //   80: astore 4
    //   82: aload 8
    //   84: astore 5
    //   86: aload 10
    //   88: ifnull +17 -> 105
    //   91: aload 10
    //   93: invokevirtual 583	com/supersonicads/sdk/data/DemandSource:getExtraParams	()Ljava/util/Map;
    //   96: astore 4
    //   98: aload 8
    //   100: astore 5
    //   102: aload 7
    //   104: astore_2
    //   105: aload 9
    //   107: ldc_w 630
    //   110: aload_1
    //   111: invokevirtual 730	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   114: pop
    //   115: aload 5
    //   117: invokestatic 593	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   120: ifne +326 -> 446
    //   123: aload 9
    //   125: ldc_w 577
    //   128: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   131: aload 5
    //   133: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   136: invokevirtual 730	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   139: pop
    //   140: aload_2
    //   141: invokestatic 593	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   144: ifne +336 -> 480
    //   147: aload 9
    //   149: ldc_w 569
    //   152: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   155: aload_2
    //   156: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   159: invokevirtual 730	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   162: pop
    //   163: aload 4
    //   165: ifnull +320 -> 485
    //   168: aload 4
    //   170: invokeinterface 732 1 0
    //   175: ifne +310 -> 485
    //   178: aload 4
    //   180: invokeinterface 659 1 0
    //   185: invokeinterface 665 1 0
    //   190: astore_1
    //   191: aload_1
    //   192: invokeinterface 671 1 0
    //   197: ifeq +288 -> 485
    //   200: aload_1
    //   201: invokeinterface 675 1 0
    //   206: checkcast 677	java/util/Map$Entry
    //   209: astore_2
    //   210: aload_2
    //   211: invokeinterface 680 1 0
    //   216: checkcast 682	java/lang/String
    //   219: ldc_w 734
    //   222: invokevirtual 728	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   225: ifeq +16 -> 241
    //   228: aload_0
    //   229: aload_2
    //   230: invokeinterface 685 1 0
    //   235: checkcast 682	java/lang/String
    //   238: invokespecial 737	com/supersonicads/sdk/controller/SupersonicWebView:setWebviewCache	(Ljava/lang/String;)V
    //   241: aload 9
    //   243: aload_2
    //   244: invokeinterface 680 1 0
    //   249: checkcast 682	java/lang/String
    //   252: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   255: aload_2
    //   256: invokeinterface 685 1 0
    //   261: checkcast 682	java/lang/String
    //   264: invokestatic 690	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   267: invokevirtual 730	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   270: pop
    //   271: goto -80 -> 191
    //   274: astore_2
    //   275: aload_2
    //   276: invokevirtual 740	org/json/JSONException:printStackTrace	()V
    //   279: new 742	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   282: dup
    //   283: invokespecial 743	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   286: iconst_1
    //   287: anewarray 682	java/lang/String
    //   290: dup
    //   291: iconst_0
    //   292: ldc_w 745
    //   295: aastore
    //   296: invokevirtual 749	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   299: pop
    //   300: goto -109 -> 191
    //   303: aload_1
    //   304: getstatic 608	com/supersonicads/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   307: invokevirtual 725	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   310: invokevirtual 728	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   313: ifeq +23 -> 336
    //   316: aload_0
    //   317: getfield 364	com/supersonicads/sdk/controller/SupersonicWebView:mISAppKey	Ljava/lang/String;
    //   320: astore_2
    //   321: aload_0
    //   322: getfield 367	com/supersonicads/sdk/controller/SupersonicWebView:mISUserId	Ljava/lang/String;
    //   325: astore 5
    //   327: aload_0
    //   328: getfield 371	com/supersonicads/sdk/controller/SupersonicWebView:mISExtraParameters	Ljava/util/Map;
    //   331: astore 4
    //   333: goto -228 -> 105
    //   336: aload 4
    //   338: astore_2
    //   339: aload 6
    //   341: astore 4
    //   343: aload_1
    //   344: getstatic 617	com/supersonicads/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   347: invokevirtual 725	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   350: invokevirtual 728	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   353: ifeq -248 -> 105
    //   356: aload_0
    //   357: getfield 381	com/supersonicads/sdk/controller/SupersonicWebView:mOWAppKey	Ljava/lang/String;
    //   360: astore_2
    //   361: aload_0
    //   362: getfield 384	com/supersonicads/sdk/controller/SupersonicWebView:mOWUserId	Ljava/lang/String;
    //   365: astore 5
    //   367: aload_0
    //   368: getfield 387	com/supersonicads/sdk/controller/SupersonicWebView:mOWExtraParameters	Ljava/util/Map;
    //   371: astore 4
    //   373: goto -268 -> 105
    //   376: astore_1
    //   377: aload_1
    //   378: invokevirtual 740	org/json/JSONException:printStackTrace	()V
    //   381: new 742	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   384: dup
    //   385: invokespecial 743	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   388: iconst_1
    //   389: anewarray 682	java/lang/String
    //   392: dup
    //   393: iconst_0
    //   394: ldc_w 751
    //   397: aastore
    //   398: invokevirtual 749	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   401: pop
    //   402: goto -287 -> 115
    //   405: iconst_1
    //   406: istore_3
    //   407: aload 4
    //   409: astore_2
    //   410: aload 7
    //   412: astore 4
    //   414: goto -299 -> 115
    //   417: astore_1
    //   418: aload_1
    //   419: invokevirtual 740	org/json/JSONException:printStackTrace	()V
    //   422: new 742	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   425: dup
    //   426: invokespecial 743	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   429: iconst_1
    //   430: anewarray 682	java/lang/String
    //   433: dup
    //   434: iconst_0
    //   435: ldc_w 753
    //   438: aastore
    //   439: invokevirtual 749	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   442: pop
    //   443: goto -303 -> 140
    //   446: iconst_1
    //   447: istore_3
    //   448: goto -308 -> 140
    //   451: astore_1
    //   452: aload_1
    //   453: invokevirtual 740	org/json/JSONException:printStackTrace	()V
    //   456: new 742	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   459: dup
    //   460: invokespecial 743	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   463: iconst_1
    //   464: anewarray 682	java/lang/String
    //   467: dup
    //   468: iconst_0
    //   469: ldc_w 755
    //   472: aastore
    //   473: invokevirtual 749	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   476: pop
    //   477: goto -314 -> 163
    //   480: iconst_1
    //   481: istore_3
    //   482: goto -319 -> 163
    //   485: iconst_2
    //   486: anewarray 219	java/lang/Object
    //   489: dup
    //   490: iconst_0
    //   491: aload 9
    //   493: invokevirtual 711	org/json/JSONObject:toString	()Ljava/lang/String;
    //   496: aastore
    //   497: dup
    //   498: iconst_1
    //   499: iload_3
    //   500: invokestatic 761	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   503: aastore
    //   504: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	505	0	this	SupersonicWebView
    //   0	505	1	paramString1	String
    //   0	505	2	paramString2	String
    //   1	499	3	bool	boolean
    //   14	399	4	localObject1	Object
    //   19	347	5	localObject2	Object
    //   25	315	6	localObject3	Object
    //   22	389	7	str1	String
    //   57	42	8	str2	String
    //   9	483	9	localJSONObject	JSONObject
    //   73	19	10	localDemandSource	DemandSource
    // Exception table:
    //   from	to	target	type
    //   241	271	274	org/json/JSONException
    //   105	115	376	org/json/JSONException
    //   123	140	417	org/json/JSONException
    //   147	163	451	org/json/JSONException
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
        List localList = DeviceStatus.getInstalledApplications(getContext());
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
  
  private Object[] getDeviceParams(Context paramContext)
  {
    bool3 = false;
    boolean bool2 = false;
    Object localObject1 = DeviceProperties.getInstance(paramContext);
    JSONObject localJSONObject = new JSONObject();
    bool1 = bool3;
    for (;;)
    {
      try
      {
        localJSONObject.put("appOrientation", "none");
        bool1 = bool3;
        Object localObject2 = ((DeviceProperties)localObject1).getDeviceOem();
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
        localObject2 = DeviceProperties.getSupersonicSdkVersion();
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
        localObject1 = ConnectivityService.getConnectionType(paramContext);
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
        if (!SDKUtils.isExternalStorageAvailable()) {
          continue;
        }
        bool1 = bool2;
        long l = DeviceStatus.getAvailableMemorySizeInMegaBytes(this.mCacheDirectory);
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("diskFreeSize"), SDKUtils.encodeString(String.valueOf(l)));
        bool1 = bool2;
        localObject1 = String.valueOf(DeviceStatus.getDeviceWidth());
        bool1 = bool2;
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          continue;
        }
        bool1 = bool2;
        localObject2 = new StringBuilder();
        bool1 = bool2;
        ((StringBuilder)localObject2).append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("width")).append("]");
        bool1 = bool2;
        localJSONObject.put(((StringBuilder)localObject2).toString(), SDKUtils.encodeString((String)localObject1));
        bool1 = bool2;
        int i = DeviceStatus.getDeviceHeight();
        bool1 = bool2;
        localObject1 = new StringBuilder();
        bool1 = bool2;
        ((StringBuilder)localObject1).append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("height")).append("]");
        bool1 = bool2;
        localJSONObject.put(((StringBuilder)localObject1).toString(), SDKUtils.encodeString(String.valueOf(i)));
        bool1 = bool2;
        localObject1 = ApplicationContext.getPackageName(getContext());
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("bundleId"), SDKUtils.encodeString((String)localObject1));
        }
        bool1 = bool2;
        localObject1 = String.valueOf(DeviceStatus.getDeviceDensity());
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
    }
  }
  
  private String getRequestParameters()
  {
    Object localObject = DeviceProperties.getInstance(getContext());
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = DeviceProperties.getSupersonicSdkVersion();
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
  
  private void initProduct(String paramString1, String paramString2, SSAEnums.ProductType paramProductType, String paramString3, String paramString4)
  {
    if ((TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString1))) {
      triggerOnControllerInitProductFail("User id or Application key are missing", paramProductType, paramString4);
    }
    do
    {
      return;
      if (this.mControllerState == SSAEnums.ControllerState.Ready)
      {
        SupersonicSharedPrefHelper.getSupersonicPrefHelper().setApplicationKey(paramString1, paramProductType);
        SupersonicSharedPrefHelper.getSupersonicPrefHelper().setUserID(paramString2, paramProductType);
        createInitProductJSMethod(paramProductType, paramString4);
        return;
      }
      setMissProduct(paramProductType, paramString4);
      if (this.mControllerState == SSAEnums.ControllerState.Failed)
      {
        triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(paramString3, "Initiating Controller"), paramProductType, paramString4);
        return;
      }
    } while (!this.mGlobalControllerTimeFinish);
    downloadController();
  }
  
  private void injectJavascript(String paramString)
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
      runOnUiThread(new SupersonicWebView.5(this, "javascript:" + ((StringBuilder)localObject2).toString(), (StringBuilder)localObject2));
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
  
  private void sendProductErrorMessage(SSAEnums.ProductType paramProductType, String paramString)
  {
    String str = "";
    switch (SupersonicWebView.8.$SwitchMap$com$supersonicads$sdk$data$SSAEnums$ProductType[paramProductType.ordinal()])
    {
    }
    for (;;)
    {
      triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(str, "Initiating Controller"), paramProductType, paramString);
      return;
      str = "Init RV";
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
  
  private void setMissProduct(SSAEnums.ProductType paramProductType, String paramString)
  {
    if (paramProductType == SSAEnums.ProductType.RewardedVideo)
    {
      paramString = SupersonicAdsPublisherAgent.getInstance((Activity)getCurrentActivityContext()).getDemandSourceByName(paramString);
      if (paramString != null) {
        paramString.setDemandSourceInitState(1);
      }
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
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()))
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
    if (!TextUtils.isEmpty(paramString2)) {
      runOnUiThread(new SupersonicWebView.6(this, paramString1, paramString2));
    }
  }
  
  private void triggerOnControllerInitProductFail(String paramString1, SSAEnums.ProductType paramProductType, String paramString2)
  {
    if (shouldNotifyDeveloper(paramProductType.toString())) {
      runOnUiThread(new SupersonicWebView.4(this, paramProductType, paramString1, paramString2));
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
    this.mUiHandler = null;
    this.mCurrentActivityContext = null;
  }
  
  public void deviceStatusChanged(String paramString)
  {
    injectJavascript(generateJSToInject("deviceStatusChanged", parseToJson("connectionType", paramString, null, null, null, null, null, null, null, false)));
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
  
  public String getControllerKeyPressed()
  {
    String str = this.mControllerKeyPressed;
    setControllerKeyPressed("interrupt");
    return str;
  }
  
  public Context getCurrentActivityContext()
  {
    return ((MutableContextWrapper)this.mCurrentActivityContext).getBaseContext();
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
    initProduct(this.mOWCreditsAppKey, this.mOWCreditsUserId, SSAEnums.ProductType.OfferWallCredits, "Show OW Credits", null);
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
    throws Exception
  {
    Object localObject = SupersonicSharedPrefHelper.getSupersonicPrefHelper().getSearchKeys();
    if (localObject != null) {
      try
      {
        if (!((List)localObject).isEmpty())
        {
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext()) {
            if (paramString.contains((String)((Iterator)localObject).next()))
            {
              UrlHandler.openUrl(getCurrentActivityContext(), paramString);
              return true;
            }
          }
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
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
    initProduct(this.mISAppKey, this.mISUserId, SSAEnums.ProductType.Interstitial, "Init IS", null);
  }
  
  public void initOfferWall(String paramString1, String paramString2, Map<String, String> paramMap, OnOfferWallListener paramOnOfferWallListener)
  {
    this.mOWAppKey = paramString1;
    this.mOWUserId = paramString2;
    this.mOWExtraParameters = paramMap;
    this.mOnOfferWallListener = paramOnOfferWallListener;
    this.mSavedState.setOfferWallExtraParams(this.mOWExtraParameters);
    this.mSavedState.setOfferwallReportInit(true);
    initProduct(this.mOWAppKey, this.mOWUserId, SSAEnums.ProductType.OfferWall, "Init OW", null);
  }
  
  public void initRewardedVideo(String paramString1, String paramString2, String paramString3, DSRewardedVideoListener paramDSRewardedVideoListener)
  {
    this.mRVAppKey = paramString1;
    this.mRVUserId = paramString2;
    this.mOnRewardedVideoListener = paramDSRewardedVideoListener;
    this.mSavedState.setRVAppKey(paramString1);
    this.mSavedState.setRVUserId(paramString2);
    initProduct(paramString1, paramString2, SSAEnums.ProductType.RewardedVideo, "Init RV", paramString3);
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
  
  public void loadInterstitial()
  {
    if (!isInterstitialAdAvailable())
    {
      this.mSavedState.setReportLoadInterstitial(true);
      injectJavascript(generateJSToInject("loadInterstitial", "onLoadInterstitialSuccess", "onLoadInterstitialFail"));
    }
    while (!shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
      return;
    }
    runOnUiThread(new SupersonicWebView.3(this));
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
      paramSSAFile = SupersonicAdsPublisherAgent.getInstance((Activity)getCurrentActivityContext()).getDemandSources().iterator();
      while (paramSSAFile.hasNext())
      {
        DemandSource localDemandSource = (DemandSource)paramSSAFile.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, localDemandSource.getDemandSourceName());
        }
      }
      if (this.mISmiss) {
        sendProductErrorMessage(SSAEnums.ProductType.Interstitial, null);
      }
      if (this.mOWmiss) {
        sendProductErrorMessage(SSAEnums.ProductType.OfferWall, null);
      }
      if (this.mOWCreditsMiss) {
        sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits, null);
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
        if ((!paramAdUnitsState.shouldRestore()) || (!this.mControllerState.equals(SSAEnums.ControllerState.Ready))) {
          break label461;
        }
        Log.d(this.TAG, "restoreState(state:" + paramAdUnitsState + ")");
        i = paramAdUnitsState.getDisplayedProduct();
        if (i == -1) {
          break label442;
        }
        if (i == SSAEnums.ProductType.RewardedVideo.ordinal())
        {
          Log.d(this.TAG, "onRVAdClosed()");
          String str1 = paramAdUnitsState.getDisplayedDemandSourceName();
          if ((this.mOnRewardedVideoListener != null) && (!TextUtils.isEmpty(str1))) {
            this.mOnRewardedVideoListener.onRVAdClosed(str1);
          }
          paramAdUnitsState.adOpened(-1);
          paramAdUnitsState.setDisplayedDemandSourceName(null);
          if (paramAdUnitsState.isInterstitialInitSuccess())
          {
            Log.d(this.TAG, "onInterstitialAvailability(false)");
            if (this.mOnInitInterstitialListener != null) {}
            str1 = paramAdUnitsState.getInterstitialAppKey();
            str2 = paramAdUnitsState.getInterstitialUserId();
            localObject2 = paramAdUnitsState.getInterstitialExtraParams();
            Log.d(this.TAG, "initInterstitial(appKey:" + str1 + ", userId:" + str2 + ", extraParam:" + localObject2 + ")");
            initInterstitial(str1, str2, (Map)localObject2, this.mOnInitInterstitialListener);
          }
          str1 = paramAdUnitsState.getRVAppKey();
          String str2 = paramAdUnitsState.getRVUserId();
          Object localObject2 = SupersonicAdsPublisherAgent.getInstance((Activity)getCurrentActivityContext()).getDemandSources().iterator();
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          Object localObject3 = (DemandSource)((Iterator)localObject2).next();
          if (((DemandSource)localObject3).getDemandSourceInitState() != 2) {
            continue;
          }
          localObject3 = ((DemandSource)localObject3).getDemandSourceName();
          Log.d(this.TAG, "onRVNoMoreOffers()");
          this.mOnRewardedVideoListener.onRVNoMoreOffers((String)localObject3);
          initRewardedVideo(str1, str2, (String)localObject3, this.mOnRewardedVideoListener);
        }
      }
      if (i == SSAEnums.ProductType.Interstitial.ordinal())
      {
        Log.d(this.TAG, "onInterstitialAdClosed()");
        if (this.mOnInitInterstitialListener != null) {
          this.mOnInitInterstitialListener.onInterstitialClose();
        }
      }
      else if (i == SSAEnums.ProductType.OfferWall.ordinal())
      {
        Log.d(this.TAG, "onOWAdClosed()");
        if (this.mOnOfferWallListener != null)
        {
          this.mOnOfferWallListener.onOWAdClosed();
          continue;
          label442:
          Log.d(this.TAG, "No ad was opened");
        }
      }
    }
    paramAdUnitsState.setShouldRestore(false);
    label461:
    this.mSavedState = paramAdUnitsState;
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
    if ("initRewardedVideo".equalsIgnoreCase(paramString))
    {
      paramString = (String)paramMap.get("demandSourceName");
      initRewardedVideo((String)paramMap.get("applicationUserId"), (String)paramMap.get("applicationKey"), paramString, this.mOnRewardedVideoListener);
      return;
    }
    if ("showRewardedVideo".equalsIgnoreCase(paramString))
    {
      showRewardedVideo((String)paramMap.get("demandSourceName"));
      return;
    }
    injectJavascript(generateJSToInject(paramString, mapToJson(paramMap), "onGenericFunctionSuccess", "onGenericFunctionFail"));
  }
  
  void runOnUiThread(Runnable paramRunnable)
  {
    this.mUiHandler.post(paramRunnable);
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
  
  public void showInterstitial()
  {
    injectJavascript(generateJSToInject("showInterstitial", "onShowInterstitialSuccess", "onShowInterstitialFail"));
  }
  
  public void showOfferWall(Map<String, String> paramMap)
  {
    this.mOWExtraParameters = paramMap;
    injectJavascript(generateJSToInject("showOfferWall", "onShowOfferWallSuccess", "onShowOfferWallFail"));
  }
  
  public void showRewardedVideo(String paramString)
  {
    HashMap localHashMap = new HashMap();
    if (!TextUtils.isEmpty(paramString)) {
      localHashMap.put("demandSourceName", paramString);
    }
    injectJavascript(generateJSToInject("showRewardedVideo", flatMapToJsonAsString(localHashMap), "onShowRewardedVideoSuccess", "onShowRewardedVideoFail"));
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
      new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
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
    
    private void setInterstitialAvailability(boolean paramBoolean)
    {
      SupersonicWebView.access$5302(SupersonicWebView.this, Boolean.valueOf(paramBoolean));
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
        SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.9(this));
        SupersonicWebView.this.toastingErrMsg("onInterstitialAdClicked", paramString);
      }
    }
    
    @JavascriptInterface
    public void adCredited(String paramString)
    {
      Log.d(SupersonicWebView.this.PUB_TAG, "adCredited(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      String str1 = localSSAObj.getString("credits");
      int i;
      String str6;
      int j;
      label85:
      String str4;
      String str5;
      boolean bool2;
      String str3;
      boolean bool3;
      String str2;
      if (str1 != null)
      {
        i = Integer.parseInt(str1);
        str6 = localSSAObj.getString("total");
        if (str6 == null) {
          break label209;
        }
        j = Integer.parseInt(str6);
        str4 = localSSAObj.getString("demandSourceName");
        str5 = localSSAObj.getString("productType");
        boolean bool4 = localSSAObj.getBoolean("externalPoll");
        bool2 = false;
        str3 = null;
        bool1 = false;
        bool3 = false;
        if (!bool4) {
          break label214;
        }
        str1 = SupersonicWebView.this.mOWCreditsAppKey;
        str2 = SupersonicWebView.this.mOWCreditsUserId;
      }
      for (;;)
      {
        if (str5.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()))
        {
          if ((localSSAObj.isNull("signature")) || (localSSAObj.isNull("timestamp")) || (localSSAObj.isNull("totalCreditsFlag")))
          {
            SupersonicWebView.this.responseBack(paramString, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
            return;
            i = 0;
            break;
            label209:
            j = 0;
            break label85;
            label214:
            str1 = SupersonicWebView.this.mOWAppKey;
            str2 = SupersonicWebView.this.mOWUserId;
            continue;
          }
          if (!localSSAObj.getString("signature").equalsIgnoreCase(SDKUtils.getMD5(str6 + str1 + str2))) {
            break label342;
          }
        }
      }
      for (boolean bool1 = true;; bool1 = bool3)
      {
        bool2 = localSSAObj.getBoolean("totalCreditsFlag");
        str3 = localSSAObj.getString("timestamp");
        if (!SupersonicWebView.this.shouldNotifyDeveloper(str5)) {
          break;
        }
        SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.2(this, str5, i, str4, bool1, j, bool2, str3, str1, str2, paramString));
        return;
        label342:
        SupersonicWebView.this.responseBack(paramString, false, "Controller signature is not equal to SDK signature", null);
      }
    }
    
    @JavascriptInterface
    public void adUnitsReady(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "adUnitsReady(" + paramString + ")");
      String str = new SSAObj(paramString).getString("demandSourceName");
      AdUnitsReady localAdUnitsReady = new AdUnitsReady(paramString);
      if (!localAdUnitsReady.isNumOfAdUnitsExist()) {
        SupersonicWebView.this.responseBack(paramString, false, "Num Of Ad Units Do Not Exist", null);
      }
      do
      {
        return;
        SupersonicWebView.this.responseBack(paramString, true, null, null);
        paramString = localAdUnitsReady.getProductType();
      } while (!SupersonicWebView.this.shouldNotifyDeveloper(paramString));
      SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.1(this, localAdUnitsReady, paramString, str));
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
        paramString = new JSONObject();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("frequency", "weekly");
        paramString.put("id", "testevent723GDf84");
        paramString.put("description", "Watch this crazy showInterstitial on cannel 5!");
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
      boolean bool1 = ((Boolean)paramString.get("display")).booleanValue();
      String str3 = paramString.getString("productType");
      boolean bool2 = paramString.getBoolean("standaloneView");
      String str1 = paramString.getString("demandSourceName");
      int i = 0;
      if (bool1)
      {
        SupersonicWebView.access$4602(SupersonicWebView.this, paramString.getBoolean("immersive"));
        if (SupersonicWebView.this.getState() != SupersonicWebView.State.Display)
        {
          SupersonicWebView.this.setState(SupersonicWebView.State.Display);
          Logger.i(SupersonicWebView.this.TAG, "State: " + SupersonicWebView.this.mState);
          Context localContext = SupersonicWebView.this.getCurrentActivityContext();
          String str2 = SupersonicWebView.this.getOrientationState();
          int j = DeviceStatus.getApplicationRotation(localContext);
          if (bool2)
          {
            paramString = new ControllerView(localContext);
            paramString.addView(SupersonicWebView.this.mControllerLayout);
            paramString.showInterstitial(SupersonicWebView.this);
            return;
          }
          if (str3.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
            paramString = new Intent(localContext, InterstitialActivity.class);
          }
          for (;;)
          {
            if ((i != 0) && (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString()))) {
              SupersonicWebView.this.mOnRewardedVideoListener.onRVAdOpened(str1);
            }
            paramString.setFlags(536870912);
            paramString.putExtra("immersive", SupersonicWebView.this.mIsImmersive);
            paramString.putExtra("orientation_set_flag", str2);
            paramString.putExtra("rotation_set_flag", j);
            localContext.startActivity(paramString);
            return;
            paramString = new Intent(localContext, ControllerActivity.class);
            if (SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(str3))
            {
              i = 1;
              paramString.putExtra("productType", SSAEnums.ProductType.RewardedVideo.toString());
              SupersonicWebView.this.mSavedState.adOpened(SSAEnums.ProductType.RewardedVideo.ordinal());
              SupersonicWebView.this.mSavedState.setDisplayedDemandSourceName(str1);
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
      paramString = new SSAObj(paramString);
      String str3 = paramString.getString("productType");
      Object localObject = paramString.getString("demandSourceName");
      paramString = null;
      Object[] arrayOfObject = new Object[2];
      localObject = SupersonicWebView.this.getApplicationParams(str3, (String)localObject);
      str3 = (String)localObject[0];
      if (((Boolean)localObject[1]).booleanValue()) {
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
      paramString = SupersonicWebView.this.getDeviceParams(SupersonicWebView.this.getContext());
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
      String str = SDKUtils.getOrientation(SupersonicWebView.this.getCurrentActivityContext()).toString();
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
          localObject = LocationService.getLastLocation(SupersonicWebView.this.getContext());
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
      DemandSource localDemandSource;
      if (paramString.containsKey("stage"))
      {
        paramString = paramString.getString("stage");
        if (!"ready".equalsIgnoreCase(paramString)) {
          break label330;
        }
        SupersonicWebView.access$1502(SupersonicWebView.this, SSAEnums.ControllerState.Ready);
        SupersonicWebView.this.mGlobalControllerTimer.cancel();
        SupersonicWebView.this.mLoadControllerTimer.cancel();
        paramString = SupersonicAdsPublisherAgent.getInstance((Activity)SupersonicWebView.this.getCurrentActivityContext()).getDemandSources().iterator();
        while (paramString.hasNext())
        {
          localDemandSource = (DemandSource)paramString.next();
          if (localDemandSource.getDemandSourceInitState() == 1) {
            SupersonicWebView.this.initRewardedVideo(SupersonicWebView.this.mRVAppKey, SupersonicWebView.this.mRVUserId, localDemandSource.getDemandSourceName(), SupersonicWebView.this.mOnRewardedVideoListener);
          }
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
      label330:
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
        paramString = SupersonicAdsPublisherAgent.getInstance((Activity)SupersonicWebView.this.getCurrentActivityContext()).getDemandSources().iterator();
        while (paramString.hasNext())
        {
          localDemandSource = (DemandSource)paramString.next();
          if (localDemandSource.getDemandSourceInitState() == 1) {
            SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, localDemandSource.getDemandSourceName());
          }
        }
        if (SupersonicWebView.this.mISmiss) {
          SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial, null);
        }
        if (SupersonicWebView.this.mOWmiss) {
          SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWall, null);
        }
      } while (!SupersonicWebView.this.mOWCreditsMiss);
      SupersonicWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits, null);
      return;
      Logger.i(SupersonicWebView.this.TAG, "No STAGE mentioned! Should not get here!");
    }
    
    @JavascriptInterface
    public void onAdWindowsClosed(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onAdWindowsClosed(" + paramString + ")");
      SupersonicWebView.this.mSavedState.adClosed();
      SupersonicWebView.this.mSavedState.setDisplayedDemandSourceName(null);
      Object localObject = new SSAObj(paramString);
      paramString = ((SSAObj)localObject).getString("productType");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
        Log.d(SupersonicWebView.this.PUB_TAG, "onRVAdClosed()");
      }
      for (;;)
      {
        if ((SupersonicWebView.this.shouldNotifyDeveloper(paramString)) && (paramString != null)) {
          SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.19(this, paramString, (String)localObject));
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
      SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.17(this, str));
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
      SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.16(this));
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
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.18(this, str));
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
    public void onInitInterstitialFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onInitInterstitialFail(" + paramString + ")");
      SupersonicWebView.this.mSavedState.setInterstitialInitSuccess(false);
      String str = new SSAObj(paramString).getString("errMsg");
      if (SupersonicWebView.this.mSavedState.reportInitInterstitial())
      {
        SupersonicWebView.this.mSavedState.setReportInitInterstitial(false);
        if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
          SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.8(this, str));
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
        if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
          SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.7(this));
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
        if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
          SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.12(this, str));
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
        if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
          SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.11(this));
        }
      }
    }
    
    @JavascriptInterface
    public void onInitRewardedVideoFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onInitRewardedVideoFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      DemandSource localDemandSource = SupersonicAdsPublisherAgent.getInstance((Activity)SupersonicWebView.this.getCurrentActivityContext()).getDemandSourceByName((String)localObject);
      if (localDemandSource != null) {
        localDemandSource.setDemandSourceInitState(3);
      }
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
        SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.3(this, str, (String)localObject));
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onInitRewardedVideoFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitRewardedVideoSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onInitRewardedVideoSuccess(" + paramString + ")");
      SSABCParameters localSSABCParameters = new SSABCParameters(paramString);
      SupersonicSharedPrefHelper.getSupersonicPrefHelper().setSSABCParameters(localSSABCParameters);
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onInitRewardedVideoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onLoadInterstitialFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onLoadInterstitialFail(" + paramString + ")");
      String str = new SSAObj(paramString).getString("errMsg");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.14(this, str));
      }
      SupersonicWebView.this.toastingErrMsg("onLoadInterstitialFail", "true");
    }
    
    @JavascriptInterface
    public void onLoadInterstitialSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onLoadInterstitialSuccess(" + paramString + ")");
      setInterstitialAvailability(true);
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.13(this));
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
    public void onShowInterstitialFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onShowInterstitialFail(" + paramString + ")");
      setInterstitialAvailability(false);
      String str = new SSAObj(paramString).getString("errMsg");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.15(this, str));
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
        SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.10(this));
        SupersonicWebView.this.toastingErrMsg("onShowInterstitialSuccess", paramString);
      }
      setInterstitialAvailability(false);
    }
    
    @JavascriptInterface
    public void onShowOfferWallFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onShowOfferWallFail(" + paramString + ")");
      String str = new SSAObj(paramString).getString("errMsg");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.6(this, str));
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
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.5(this, str));
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onShowOfferWallSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onShowRewardedVideoFail(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onShowRewardedVideoFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
        SupersonicWebView.this.runOnUiThread(new SupersonicWebView.JSInterface.4(this, str, (String)localObject));
      }
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onShowRewardedVideoFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowRewardedVideoSuccess(String paramString)
    {
      Logger.i(SupersonicWebView.this.TAG, "onShowRewardedVideoSuccess(" + paramString + ")");
      SupersonicWebView.this.responseBack(paramString, true, null, null);
      SupersonicWebView.this.toastingErrMsg("onShowRewardedVideoSuccess", paramString);
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
      if ((SupersonicWebView.this.mVideoEventsListener != null) && (!TextUtils.isEmpty(str)) && (SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(str)))
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
      String str = ((SSAObj)localObject1).getString("url");
      Object localObject2 = ((SSAObj)localObject1).getString("method");
      localObject1 = SupersonicWebView.this.getCurrentActivityContext();
      try
      {
        if (((String)localObject2).equalsIgnoreCase("external_browser"))
        {
          UrlHandler.openUrl((Context)localObject1, str);
          return;
        }
        if (((String)localObject2).equalsIgnoreCase("webview"))
        {
          localObject2 = new Intent((Context)localObject1, OpenUrlActivity.class);
          ((Intent)localObject2).putExtra(SupersonicWebView.EXTERNAL_URL, str);
          ((Intent)localObject2).putExtra(SupersonicWebView.SECONDARY_WEB_VIEW, true);
          ((Intent)localObject2).putExtra("immersive", SupersonicWebView.this.mIsImmersive);
          ((Context)localObject1).startActivity((Intent)localObject2);
          return;
        }
      }
      catch (Exception localException)
      {
        SupersonicWebView.this.responseBack(paramString, false, localException.getMessage(), null);
        localException.printStackTrace();
        return;
      }
      if (((String)localObject2).equalsIgnoreCase("store"))
      {
        localObject2 = new Intent((Context)localObject1, OpenUrlActivity.class);
        ((Intent)localObject2).putExtra(SupersonicWebView.EXTERNAL_URL, localException);
        ((Intent)localObject2).putExtra(SupersonicWebView.IS_STORE, true);
        ((Intent)localObject2).putExtra(SupersonicWebView.SECONDARY_WEB_VIEW, true);
        ((Context)localObject1).startActivity((Intent)localObject2);
      }
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
      if (DeviceStatus.getAvailableMemorySizeInMegaBytes(SupersonicWebView.this.mCacheDirectory) <= 0L)
      {
        SupersonicWebView.this.responseBack(paramString, false, "no_disk_space", null);
        return;
      }
      if (!SDKUtils.isExternalStorageAvailable())
      {
        SupersonicWebView.this.responseBack(paramString, false, "sotrage_unavailable", null);
        return;
      }
      if (SupersonicStorageUtils.isFileCached(SupersonicWebView.this.mCacheDirectory, localSSAFile))
      {
        SupersonicWebView.this.responseBack(paramString, false, "file_already_exist", null);
        return;
      }
      if (!ConnectivityService.isConnected(SupersonicWebView.this.getContext()))
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
            break label233;
          }
          paramString = localSSAFile.getPath().split("/");
          paramString = paramString[(paramString.length - 1)];
        }
      }
      label233:
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
      int i = DeviceStatus.getApplicationRotation(SupersonicWebView.this.getCurrentActivityContext());
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
