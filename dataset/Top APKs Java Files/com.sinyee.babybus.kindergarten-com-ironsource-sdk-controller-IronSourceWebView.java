package com.ironsource.sdk.controller;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.MutableContextWrapper;
import android.content.pm.ApplicationInfo;
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
import com.ironsource.environment.ConnectivityService;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.environment.LocationService;
import com.ironsource.environment.UrlHandler;
import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.data.AdUnitsState;
import com.ironsource.sdk.data.DemandSource;
import com.ironsource.sdk.data.SSABCParameters;
import com.ironsource.sdk.data.SSAEnums.ControllerState;
import com.ironsource.sdk.data.SSAEnums.DebugMode;
import com.ironsource.sdk.data.SSAEnums.ProductType;
import com.ironsource.sdk.data.SSAFile;
import com.ironsource.sdk.data.SSAObj;
import com.ironsource.sdk.listeners.DSRewardedVideoListener;
import com.ironsource.sdk.listeners.OnGenericFunctionListener;
import com.ironsource.sdk.listeners.OnInterstitialListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.sdk.listeners.OnWebViewChangeListener;
import com.ironsource.sdk.precache.DownloadManager;
import com.ironsource.sdk.precache.DownloadManager.OnPreCacheCompletion;
import com.ironsource.sdk.utils.DeviceProperties;
import com.ironsource.sdk.utils.IronSourceAsyncHttpRequestTask;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import com.ironsource.sdk.utils.IronSourceStorageUtils;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IronSourceWebView
  extends WebView
  implements DownloadListener, DownloadManager.OnPreCacheCompletion
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
  private String PUB_TAG = "IronSource";
  private String TAG = IronSourceWebView.class.getSimpleName();
  private DownloadManager downloadManager;
  private Boolean isKitkatAndAbove = null;
  private boolean isRemoveCloseEventHandler;
  private String mCacheDirectory;
  private OnWebViewChangeListener mChangeListener;
  private CountDownTimer mCloseEventTimer;
  private BroadcastReceiver mConnectionReceiver = new IronSourceWebView.7(this);
  private String mControllerKeyPressed = "interrupt";
  private FrameLayout mControllerLayout;
  private SSAEnums.ControllerState mControllerState = SSAEnums.ControllerState.None;
  Context mCurrentActivityContext;
  private View mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private FrameLayout mCustomViewContainer;
  private DemandSourceManager mDemandSourceManager;
  private boolean mGlobalControllerTimeFinish;
  private CountDownTimer mGlobalControllerTimer;
  private int mHiddenForceCloseHeight = 50;
  private String mHiddenForceCloseLocation = "top-right";
  private int mHiddenForceCloseWidth = 50;
  private String mISAppKey;
  private Map<String, String> mISExtraParameters;
  private String mISUserId;
  private boolean mISmiss;
  private boolean mIsActivityThemeTranslucent = false;
  private boolean mIsImmersive = false;
  private Boolean mIsInterstitialAvailable = null;
  private CountDownTimer mLoadControllerTimer;
  private MOATJSAdapter mMoatJsAdapter;
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
  private IronSourceWebView.ChromeClient mWebChromeClient;
  
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
  
  public IronSourceWebView(Context paramContext, DemandSourceManager paramDemandSourceManager)
  {
    super(paramContext.getApplicationContext());
    Logger.i(this.TAG, "C'tor");
    this.mCacheDirectory = initializeCacheDirectory(paramContext.getApplicationContext());
    this.mCurrentActivityContext = paramContext;
    this.mDemandSourceManager = paramDemandSourceManager;
    initLayout(this.mCurrentActivityContext);
    this.mSavedState = new AdUnitsState();
    this.downloadManager = getDownloadManager();
    this.downloadManager.setOnPreCacheCompletion(this);
    this.mWebChromeClient = new IronSourceWebView.ChromeClient(this, null);
    setWebViewClient(new IronSourceWebView.ViewClient(this, null));
    setWebChromeClient(this.mWebChromeClient);
    setWebViewSettings();
    addJavascriptInterface(createJSInterface(paramContext), "Android");
    setDownloadListener(this);
    setOnTouchListener(new IronSourceWebView.SupersonicWebViewTouchListener(this, null));
    this.mUiHandler = createMainThreadHandler();
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
      paramProductType = this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, paramString);
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
    //   0: new 664	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 665	org/json/JSONObject:<init>	()V
    //   7: astore 9
    //   9: ldc_w 734
    //   12: astore 5
    //   14: ldc_w 734
    //   17: astore 4
    //   19: aconst_null
    //   20: astore 6
    //   22: aconst_null
    //   23: astore 8
    //   25: aload_1
    //   26: invokestatic 603	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   29: ifne +370 -> 399
    //   32: aload_1
    //   33: getstatic 568	com/ironsource/sdk/data/SSAEnums$ProductType:RewardedVideo	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   36: invokevirtual 735	com/ironsource/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   39: invokevirtual 738	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   42: ifeq +257 -> 299
    //   45: aload_0
    //   46: getfield 354	com/ironsource/sdk/controller/IronSourceWebView:mRVAppKey	Ljava/lang/String;
    //   49: astore 6
    //   51: aload_0
    //   52: getfield 357	com/ironsource/sdk/controller/IronSourceWebView:mRVUserId	Ljava/lang/String;
    //   55: astore 7
    //   57: aload_0
    //   58: getfield 258	com/ironsource/sdk/controller/IronSourceWebView:mDemandSourceManager	Lcom/ironsource/sdk/controller/DemandSourceManager;
    //   61: getstatic 568	com/ironsource/sdk/data/SSAEnums$ProductType:RewardedVideo	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   64: aload_2
    //   65: invokevirtual 574	com/ironsource/sdk/controller/DemandSourceManager:getDemandSourceByName	(Lcom/ironsource/sdk/data/SSAEnums$ProductType;Ljava/lang/String;)Lcom/ironsource/sdk/data/DemandSource;
    //   68: astore 10
    //   70: aload 8
    //   72: astore_2
    //   73: aload 7
    //   75: astore 4
    //   77: aload 6
    //   79: astore 5
    //   81: aload 10
    //   83: ifnull +17 -> 100
    //   86: aload 10
    //   88: invokevirtual 593	com/ironsource/sdk/data/DemandSource:getExtraParams	()Ljava/util/Map;
    //   91: astore_2
    //   92: aload 6
    //   94: astore 5
    //   96: aload 7
    //   98: astore 4
    //   100: aload 9
    //   102: ldc_w 640
    //   105: aload_1
    //   106: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   109: pop
    //   110: iconst_0
    //   111: istore_3
    //   112: aload 4
    //   114: invokestatic 603	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   117: ifne +319 -> 436
    //   120: aload 9
    //   122: ldc_w 587
    //   125: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   128: aload 4
    //   130: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   133: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   136: pop
    //   137: aload 5
    //   139: invokestatic 603	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   142: ifne +328 -> 470
    //   145: aload 9
    //   147: ldc_w 579
    //   150: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   153: aload 5
    //   155: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   158: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   161: pop
    //   162: aload_2
    //   163: ifnull +312 -> 475
    //   166: aload_2
    //   167: invokeinterface 742 1 0
    //   172: ifne +303 -> 475
    //   175: aload_2
    //   176: invokeinterface 669 1 0
    //   181: invokeinterface 675 1 0
    //   186: astore_1
    //   187: aload_1
    //   188: invokeinterface 681 1 0
    //   193: ifeq +282 -> 475
    //   196: aload_1
    //   197: invokeinterface 685 1 0
    //   202: checkcast 687	java/util/Map$Entry
    //   205: astore_2
    //   206: aload_2
    //   207: invokeinterface 690 1 0
    //   212: checkcast 692	java/lang/String
    //   215: ldc_w 744
    //   218: invokevirtual 738	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   221: ifeq +16 -> 237
    //   224: aload_0
    //   225: aload_2
    //   226: invokeinterface 695 1 0
    //   231: checkcast 692	java/lang/String
    //   234: invokespecial 747	com/ironsource/sdk/controller/IronSourceWebView:setWebviewCache	(Ljava/lang/String;)V
    //   237: aload 9
    //   239: aload_2
    //   240: invokeinterface 690 1 0
    //   245: checkcast 692	java/lang/String
    //   248: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   251: aload_2
    //   252: invokeinterface 695 1 0
    //   257: checkcast 692	java/lang/String
    //   260: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   263: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   266: pop
    //   267: goto -80 -> 187
    //   270: astore_2
    //   271: aload_2
    //   272: invokevirtual 750	org/json/JSONException:printStackTrace	()V
    //   275: new 752	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   278: dup
    //   279: invokespecial 753	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   282: iconst_1
    //   283: anewarray 692	java/lang/String
    //   286: dup
    //   287: iconst_0
    //   288: ldc_w 755
    //   291: aastore
    //   292: invokevirtual 759	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   295: pop
    //   296: goto -109 -> 187
    //   299: aload_1
    //   300: getstatic 618	com/ironsource/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   303: invokevirtual 735	com/ironsource/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   306: invokevirtual 738	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   309: ifeq +23 -> 332
    //   312: aload_0
    //   313: getfield 368	com/ironsource/sdk/controller/IronSourceWebView:mISAppKey	Ljava/lang/String;
    //   316: astore 5
    //   318: aload_0
    //   319: getfield 371	com/ironsource/sdk/controller/IronSourceWebView:mISUserId	Ljava/lang/String;
    //   322: astore 4
    //   324: aload_0
    //   325: getfield 375	com/ironsource/sdk/controller/IronSourceWebView:mISExtraParameters	Ljava/util/Map;
    //   328: astore_2
    //   329: goto -229 -> 100
    //   332: aload 8
    //   334: astore_2
    //   335: aload_1
    //   336: getstatic 627	com/ironsource/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   339: invokevirtual 735	com/ironsource/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   342: invokevirtual 738	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   345: ifeq -245 -> 100
    //   348: aload_0
    //   349: getfield 385	com/ironsource/sdk/controller/IronSourceWebView:mOWAppKey	Ljava/lang/String;
    //   352: astore 5
    //   354: aload_0
    //   355: getfield 388	com/ironsource/sdk/controller/IronSourceWebView:mOWUserId	Ljava/lang/String;
    //   358: astore 4
    //   360: aload_0
    //   361: getfield 392	com/ironsource/sdk/controller/IronSourceWebView:mOWExtraParameters	Ljava/util/Map;
    //   364: astore_2
    //   365: goto -265 -> 100
    //   368: astore_1
    //   369: aload_1
    //   370: invokevirtual 750	org/json/JSONException:printStackTrace	()V
    //   373: new 752	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   376: dup
    //   377: invokespecial 753	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   380: iconst_1
    //   381: anewarray 692	java/lang/String
    //   384: dup
    //   385: iconst_0
    //   386: ldc_w 761
    //   389: aastore
    //   390: invokevirtual 759	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   393: pop
    //   394: iconst_0
    //   395: istore_3
    //   396: goto -284 -> 112
    //   399: iconst_1
    //   400: istore_3
    //   401: aload 6
    //   403: astore_2
    //   404: goto -292 -> 112
    //   407: astore_1
    //   408: aload_1
    //   409: invokevirtual 750	org/json/JSONException:printStackTrace	()V
    //   412: new 752	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   415: dup
    //   416: invokespecial 753	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   419: iconst_1
    //   420: anewarray 692	java/lang/String
    //   423: dup
    //   424: iconst_0
    //   425: ldc_w 763
    //   428: aastore
    //   429: invokevirtual 759	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   432: pop
    //   433: goto -296 -> 137
    //   436: iconst_1
    //   437: istore_3
    //   438: goto -301 -> 137
    //   441: astore_1
    //   442: aload_1
    //   443: invokevirtual 750	org/json/JSONException:printStackTrace	()V
    //   446: new 752	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   449: dup
    //   450: invokespecial 753	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   453: iconst_1
    //   454: anewarray 692	java/lang/String
    //   457: dup
    //   458: iconst_0
    //   459: ldc_w 765
    //   462: aastore
    //   463: invokevirtual 759	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   466: pop
    //   467: goto -305 -> 162
    //   470: iconst_1
    //   471: istore_3
    //   472: goto -310 -> 162
    //   475: iconst_2
    //   476: anewarray 225	java/lang/Object
    //   479: dup
    //   480: iconst_0
    //   481: aload 9
    //   483: invokevirtual 721	org/json/JSONObject:toString	()Ljava/lang/String;
    //   486: aastore
    //   487: dup
    //   488: iconst_1
    //   489: iload_3
    //   490: invokestatic 771	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   493: aastore
    //   494: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	495	0	this	IronSourceWebView
    //   0	495	1	paramString1	String
    //   0	495	2	paramString2	String
    //   111	379	3	bool	boolean
    //   17	342	4	localObject1	Object
    //   12	341	5	localObject2	Object
    //   20	382	6	str1	String
    //   55	42	7	str2	String
    //   23	310	8	localObject3	Object
    //   7	475	9	localJSONObject	JSONObject
    //   68	19	10	localDemandSource	DemandSource
    // Exception table:
    //   from	to	target	type
    //   237	267	270	org/json/JSONException
    //   100	110	368	org/json/JSONException
    //   120	137	407	org/json/JSONException
    //   145	162	441	org/json/JSONException
  }
  
  private Object[] getAppsStatus(String paramString1, String paramString2)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      if ((TextUtils.isEmpty(paramString1)) || (paramString1.equalsIgnoreCase("null"))) {
        break label261;
      }
      if ((TextUtils.isEmpty(paramString2)) || (paramString2.equalsIgnoreCase("null"))) {
        break label243;
      }
      localList = DeviceStatus.getInstalledApplications(getContext());
      paramString1 = new JSONArray(paramString1);
      localJSONObject2 = new JSONObject();
      i = 0;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        List localList;
        JSONObject localJSONObject2;
        int i;
        String str;
        JSONObject localJSONObject3;
        Iterator localIterator;
        label243:
        label261:
        boolean bool = true;
        continue;
        int j = 0;
        continue;
        i += 1;
      }
    }
    if (i < paramString1.length())
    {
      str = paramString1.getString(i).trim();
      if (!TextUtils.isEmpty(str))
      {
        localJSONObject3 = new JSONObject();
        localIterator = localList.iterator();
        do
        {
          if (!localIterator.hasNext()) {
            break;
          }
        } while (!str.equalsIgnoreCase(((ApplicationInfo)localIterator.next()).packageName));
        localJSONObject3.put(IS_INSTALLED, true);
        localJSONObject2.put(str, localJSONObject3);
        j = 1;
        if (j == 0)
        {
          localJSONObject3.put(IS_INSTALLED, false);
          localJSONObject2.put(str, localJSONObject3);
        }
      }
    }
    else
    {
      localJSONObject1.put(RESULT, localJSONObject2);
      localJSONObject1.put(REQUEST_ID, paramString2);
      bool = false;
      for (;;)
      {
        return new Object[] { localJSONObject1.toString(), Boolean.valueOf(bool) };
        localJSONObject1.put("error", "requestId is null or empty");
        bool = true;
        continue;
        localJSONObject1.put("error", "appIds is null or empty");
        bool = true;
      }
    }
  }
  
  /* Error */
  private Object[] getDeviceParams(Context paramContext)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 818	com/ironsource/sdk/utils/DeviceProperties:getInstance	(Landroid/content/Context;)Lcom/ironsource/sdk/utils/DeviceProperties;
    //   4: astore 10
    //   6: new 664	org/json/JSONObject
    //   9: dup
    //   10: invokespecial 665	org/json/JSONObject:<init>	()V
    //   13: astore 9
    //   15: aload 9
    //   17: ldc_w 820
    //   20: aload_0
    //   21: invokevirtual 823	com/ironsource/sdk/controller/IronSourceWebView:getCurrentActivityContext	()Landroid/content/Context;
    //   24: invokestatic 827	com/ironsource/environment/DeviceStatus:getActivityRequestedOrientation	(Landroid/content/Context;)I
    //   27: invokestatic 830	com/ironsource/sdk/utils/SDKUtils:translateRequestedOrientation	(I)Ljava/lang/String;
    //   30: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   33: pop
    //   34: aload 10
    //   36: invokevirtual 833	com/ironsource/sdk/utils/DeviceProperties:getDeviceOem	()Ljava/lang/String;
    //   39: astore 11
    //   41: aload 11
    //   43: ifnull +20 -> 63
    //   46: aload 9
    //   48: ldc_w 835
    //   51: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   54: aload 11
    //   56: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   59: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   62: pop
    //   63: aload 10
    //   65: invokevirtual 838	com/ironsource/sdk/utils/DeviceProperties:getDeviceModel	()Ljava/lang/String;
    //   68: astore 11
    //   70: aload 11
    //   72: ifnull +1046 -> 1118
    //   75: aload 9
    //   77: ldc_w 840
    //   80: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   83: aload 11
    //   85: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   88: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   91: pop
    //   92: iconst_0
    //   93: istore 5
    //   95: iload 5
    //   97: istore 4
    //   99: aload_1
    //   100: invokestatic 843	com/ironsource/sdk/utils/SDKUtils:loadGoogleAdvertiserInfo	(Landroid/content/Context;)V
    //   103: iload 5
    //   105: istore 4
    //   107: invokestatic 846	com/ironsource/sdk/utils/SDKUtils:getAdvertiserId	()Ljava/lang/String;
    //   110: astore 11
    //   112: iload 5
    //   114: istore 4
    //   116: invokestatic 849	com/ironsource/sdk/utils/SDKUtils:isLimitAdTrackingEnabled	()Z
    //   119: istore 6
    //   121: iload 5
    //   123: istore 4
    //   125: aload 11
    //   127: invokestatic 603	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   130: ifne +84 -> 214
    //   133: iload 5
    //   135: istore 4
    //   137: aload_0
    //   138: getfield 192	com/ironsource/sdk/controller/IronSourceWebView:TAG	Ljava/lang/String;
    //   141: ldc_w 851
    //   144: invokestatic 248	com/ironsource/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   147: iload 5
    //   149: istore 4
    //   151: aload 9
    //   153: ldc_w 852
    //   156: iload 6
    //   158: invokestatic 771	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   161: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   164: pop
    //   165: iload 5
    //   167: istore 4
    //   169: aload 9
    //   171: new 709	java/lang/StringBuilder
    //   174: dup
    //   175: invokespecial 710	java/lang/StringBuilder:<init>	()V
    //   178: ldc_w 854
    //   181: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: ldc_w 856
    //   187: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: ldc_w 858
    //   193: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: ldc_w 860
    //   199: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: invokevirtual 720	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   205: aload 11
    //   207: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   210: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   213: pop
    //   214: iload 5
    //   216: istore 4
    //   218: aload 10
    //   220: invokevirtual 863	com/ironsource/sdk/utils/DeviceProperties:getDeviceOsType	()Ljava/lang/String;
    //   223: astore 11
    //   225: aload 11
    //   227: ifnull +897 -> 1124
    //   230: iload 5
    //   232: istore 4
    //   234: aload 9
    //   236: ldc_w 865
    //   239: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   242: aload 11
    //   244: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   247: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   250: pop
    //   251: iload 5
    //   253: istore 4
    //   255: aload 10
    //   257: invokevirtual 868	com/ironsource/sdk/utils/DeviceProperties:getDeviceOsVersion	()I
    //   260: invokestatic 872	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   263: astore 11
    //   265: aload 11
    //   267: ifnull +863 -> 1130
    //   270: iload 5
    //   272: istore 4
    //   274: aload 9
    //   276: ldc_w 874
    //   279: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   282: aload 11
    //   284: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   287: pop
    //   288: iload 5
    //   290: istore 4
    //   292: invokestatic 877	com/ironsource/sdk/utils/DeviceProperties:getSupersonicSdkVersion	()Ljava/lang/String;
    //   295: astore 11
    //   297: aload 11
    //   299: ifnull +24 -> 323
    //   302: iload 5
    //   304: istore 4
    //   306: aload 9
    //   308: ldc_w 879
    //   311: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   314: aload 11
    //   316: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   319: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   322: pop
    //   323: iload 5
    //   325: istore 4
    //   327: aload 10
    //   329: invokevirtual 882	com/ironsource/sdk/utils/DeviceProperties:getDeviceCarrier	()Ljava/lang/String;
    //   332: ifnull +42 -> 374
    //   335: iload 5
    //   337: istore 4
    //   339: aload 10
    //   341: invokevirtual 882	com/ironsource/sdk/utils/DeviceProperties:getDeviceCarrier	()Ljava/lang/String;
    //   344: invokevirtual 883	java/lang/String:length	()I
    //   347: ifle +27 -> 374
    //   350: iload 5
    //   352: istore 4
    //   354: aload 9
    //   356: ldc_w 885
    //   359: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   362: aload 10
    //   364: invokevirtual 882	com/ironsource/sdk/utils/DeviceProperties:getDeviceCarrier	()Ljava/lang/String;
    //   367: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   370: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   373: pop
    //   374: iload 5
    //   376: istore 4
    //   378: aload_1
    //   379: invokestatic 890	com/ironsource/environment/ConnectivityService:getConnectionType	(Landroid/content/Context;)Ljava/lang/String;
    //   382: astore 10
    //   384: iload 5
    //   386: istore 4
    //   388: aload 10
    //   390: invokestatic 603	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   393: ifne +743 -> 1136
    //   396: iload 5
    //   398: istore 4
    //   400: aload 9
    //   402: ldc_w 892
    //   405: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   408: aload 10
    //   410: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   413: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   416: pop
    //   417: iload 5
    //   419: istore 4
    //   421: aload_1
    //   422: invokevirtual 896	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   425: invokevirtual 902	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   428: getfield 908	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   431: invokevirtual 913	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   434: astore 10
    //   436: iload 5
    //   438: istore 4
    //   440: aload 10
    //   442: invokestatic 603	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   445: ifne +27 -> 472
    //   448: iload 5
    //   450: istore 4
    //   452: aload 9
    //   454: ldc_w 915
    //   457: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   460: aload 10
    //   462: invokevirtual 918	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   465: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   468: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   471: pop
    //   472: iload 5
    //   474: istore 4
    //   476: invokestatic 921	com/ironsource/sdk/utils/SDKUtils:isExternalStorageAvailable	()Z
    //   479: ifeq +663 -> 1142
    //   482: iload 5
    //   484: istore 4
    //   486: aload_0
    //   487: getfield 254	com/ironsource/sdk/controller/IronSourceWebView:mCacheDirectory	Ljava/lang/String;
    //   490: invokestatic 925	com/ironsource/environment/DeviceStatus:getAvailableMemorySizeInMegaBytes	(Ljava/lang/String;)J
    //   493: lstore 7
    //   495: iload 5
    //   497: istore 4
    //   499: aload 9
    //   501: ldc_w 927
    //   504: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   507: lload 7
    //   509: invokestatic 930	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   512: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   515: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   518: pop
    //   519: iload 5
    //   521: istore 4
    //   523: invokestatic 933	com/ironsource/environment/DeviceStatus:getDeviceWidth	()I
    //   526: invokestatic 935	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   529: astore 10
    //   531: iload 5
    //   533: istore 4
    //   535: aload 10
    //   537: invokestatic 603	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   540: ifne +608 -> 1148
    //   543: iload 5
    //   545: istore 4
    //   547: new 709	java/lang/StringBuilder
    //   550: dup
    //   551: invokespecial 710	java/lang/StringBuilder:<init>	()V
    //   554: astore 11
    //   556: iload 5
    //   558: istore 4
    //   560: aload 11
    //   562: ldc_w 937
    //   565: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   568: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: ldc_w 856
    //   574: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   577: ldc_w 939
    //   580: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   583: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   586: ldc_w 860
    //   589: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   592: pop
    //   593: iload 5
    //   595: istore 4
    //   597: aload 9
    //   599: aload 11
    //   601: invokevirtual 720	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   604: aload 10
    //   606: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   609: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   612: pop
    //   613: iload 5
    //   615: istore 4
    //   617: invokestatic 942	com/ironsource/environment/DeviceStatus:getDeviceHeight	()I
    //   620: istore_3
    //   621: iload 5
    //   623: istore 4
    //   625: new 709	java/lang/StringBuilder
    //   628: dup
    //   629: invokespecial 710	java/lang/StringBuilder:<init>	()V
    //   632: astore 10
    //   634: iload 5
    //   636: istore 4
    //   638: aload 10
    //   640: ldc_w 937
    //   643: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   646: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   649: ldc_w 856
    //   652: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: ldc_w 944
    //   658: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   661: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: ldc_w 860
    //   667: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   670: pop
    //   671: iload 5
    //   673: istore 4
    //   675: aload 9
    //   677: aload 10
    //   679: invokevirtual 720	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   682: iload_3
    //   683: invokestatic 935	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   686: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   689: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   692: pop
    //   693: iload 5
    //   695: istore 4
    //   697: aload_0
    //   698: invokevirtual 776	com/ironsource/sdk/controller/IronSourceWebView:getContext	()Landroid/content/Context;
    //   701: invokestatic 949	com/ironsource/environment/ApplicationContext:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   704: astore 10
    //   706: iload 5
    //   708: istore 4
    //   710: aload 10
    //   712: invokestatic 603	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   715: ifne +24 -> 739
    //   718: iload 5
    //   720: istore 4
    //   722: aload 9
    //   724: ldc_w 951
    //   727: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   730: aload 10
    //   732: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   735: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   738: pop
    //   739: iload 5
    //   741: istore 4
    //   743: invokestatic 955	com/ironsource/environment/DeviceStatus:getDeviceDensity	()F
    //   746: invokestatic 958	java/lang/String:valueOf	(F)Ljava/lang/String;
    //   749: astore 10
    //   751: iload 5
    //   753: istore 4
    //   755: aload 10
    //   757: invokestatic 603	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   760: ifne +24 -> 784
    //   763: iload 5
    //   765: istore 4
    //   767: aload 9
    //   769: ldc_w 960
    //   772: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   775: aload 10
    //   777: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   780: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   783: pop
    //   784: iload 5
    //   786: istore 4
    //   788: invokestatic 963	com/ironsource/environment/DeviceStatus:isRootedDevice	()Z
    //   791: invokestatic 966	java/lang/String:valueOf	(Z)Ljava/lang/String;
    //   794: astore 10
    //   796: iload 5
    //   798: istore 4
    //   800: aload 10
    //   802: invokestatic 603	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   805: ifne +24 -> 829
    //   808: iload 5
    //   810: istore 4
    //   812: aload 9
    //   814: ldc_w 968
    //   817: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   820: aload 10
    //   822: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   825: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   828: pop
    //   829: iload 5
    //   831: istore 4
    //   833: aload_1
    //   834: invokestatic 818	com/ironsource/sdk/utils/DeviceProperties:getInstance	(Landroid/content/Context;)Lcom/ironsource/sdk/utils/DeviceProperties;
    //   837: aload_1
    //   838: invokevirtual 972	com/ironsource/sdk/utils/DeviceProperties:getDeviceVolume	(Landroid/content/Context;)F
    //   841: fstore_2
    //   842: iload 5
    //   844: istore 4
    //   846: aload 10
    //   848: invokestatic 603	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   851: ifne +21 -> 872
    //   854: iload 5
    //   856: istore 4
    //   858: aload 9
    //   860: ldc_w 974
    //   863: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   866: fload_2
    //   867: f2d
    //   868: invokevirtual 977	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   871: pop
    //   872: iload 5
    //   874: istore 4
    //   876: aload_0
    //   877: invokevirtual 823	com/ironsource/sdk/controller/IronSourceWebView:getCurrentActivityContext	()Landroid/content/Context;
    //   880: astore_1
    //   881: iload 5
    //   883: istore 4
    //   885: getstatic 982	android/os/Build$VERSION:SDK_INT	I
    //   888: bipush 19
    //   890: if_icmplt +37 -> 927
    //   893: iload 5
    //   895: istore 4
    //   897: aload_1
    //   898: instanceof 984
    //   901: ifeq +26 -> 927
    //   904: iload 5
    //   906: istore 4
    //   908: aload 9
    //   910: ldc_w 986
    //   913: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   916: aload_1
    //   917: checkcast 984	android/app/Activity
    //   920: invokestatic 990	com/ironsource/environment/DeviceStatus:isImmersiveSupported	(Landroid/app/Activity;)Z
    //   923: invokevirtual 806	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   926: pop
    //   927: iload 5
    //   929: istore 4
    //   931: aload 9
    //   933: ldc_w 992
    //   936: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   939: aload_1
    //   940: invokestatic 995	com/ironsource/environment/DeviceStatus:getBatteryLevel	(Landroid/content/Context;)I
    //   943: invokevirtual 998	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   946: pop
    //   947: iload 5
    //   949: istore 4
    //   951: aload 9
    //   953: ldc_w 1000
    //   956: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   959: aload_1
    //   960: invokestatic 1003	com/ironsource/environment/ConnectivityService:getNetworkMCC	(Landroid/content/Context;)I
    //   963: invokevirtual 998	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   966: pop
    //   967: iload 5
    //   969: istore 4
    //   971: aload 9
    //   973: ldc_w 1005
    //   976: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   979: aload_1
    //   980: invokestatic 1008	com/ironsource/environment/ConnectivityService:getNetworkMNC	(Landroid/content/Context;)I
    //   983: invokevirtual 998	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   986: pop
    //   987: iload 5
    //   989: istore 4
    //   991: aload 9
    //   993: ldc_w 1010
    //   996: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   999: aload_1
    //   1000: invokestatic 1013	com/ironsource/environment/ConnectivityService:getPhoneType	(Landroid/content/Context;)I
    //   1003: invokevirtual 998	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1006: pop
    //   1007: iload 5
    //   1009: istore 4
    //   1011: aload 9
    //   1013: ldc_w 1015
    //   1016: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1019: aload_1
    //   1020: invokestatic 1018	com/ironsource/environment/ConnectivityService:getSimOperator	(Landroid/content/Context;)Ljava/lang/String;
    //   1023: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1026: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1029: pop
    //   1030: iload 5
    //   1032: istore 4
    //   1034: aload 9
    //   1036: ldc_w 1020
    //   1039: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1042: aload_1
    //   1043: invokestatic 1024	com/ironsource/environment/ApplicationContext:getLastUpdateTime	(Landroid/content/Context;)J
    //   1046: invokevirtual 1027	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   1049: pop
    //   1050: iload 5
    //   1052: istore 4
    //   1054: aload 9
    //   1056: ldc_w 1029
    //   1059: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1062: aload_1
    //   1063: invokestatic 1032	com/ironsource/environment/ApplicationContext:getFirstInstallTime	(Landroid/content/Context;)J
    //   1066: invokevirtual 1027	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   1069: pop
    //   1070: iload 5
    //   1072: istore 4
    //   1074: aload 9
    //   1076: ldc_w 1034
    //   1079: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1082: aload_1
    //   1083: invokestatic 1037	com/ironsource/environment/ApplicationContext:getApplicationVersionName	(Landroid/content/Context;)Ljava/lang/String;
    //   1086: invokestatic 700	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1089: invokevirtual 740	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1092: pop
    //   1093: iload 5
    //   1095: istore 4
    //   1097: iconst_2
    //   1098: anewarray 225	java/lang/Object
    //   1101: dup
    //   1102: iconst_0
    //   1103: aload 9
    //   1105: invokevirtual 721	org/json/JSONObject:toString	()Ljava/lang/String;
    //   1108: aastore
    //   1109: dup
    //   1110: iconst_1
    //   1111: iload 4
    //   1113: invokestatic 771	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1116: aastore
    //   1117: areturn
    //   1118: iconst_1
    //   1119: istore 5
    //   1121: goto -1026 -> 95
    //   1124: iconst_1
    //   1125: istore 5
    //   1127: goto -876 -> 251
    //   1130: iconst_1
    //   1131: istore 5
    //   1133: goto -845 -> 288
    //   1136: iconst_1
    //   1137: istore 5
    //   1139: goto -722 -> 417
    //   1142: iconst_1
    //   1143: istore 5
    //   1145: goto -626 -> 519
    //   1148: iconst_1
    //   1149: istore 5
    //   1151: goto -538 -> 613
    //   1154: astore_1
    //   1155: iconst_0
    //   1156: istore 4
    //   1158: aload_1
    //   1159: invokevirtual 750	org/json/JSONException:printStackTrace	()V
    //   1162: new 752	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   1165: dup
    //   1166: invokespecial 753	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   1169: iconst_1
    //   1170: anewarray 692	java/lang/String
    //   1173: dup
    //   1174: iconst_0
    //   1175: new 709	java/lang/StringBuilder
    //   1178: dup
    //   1179: invokespecial 710	java/lang/StringBuilder:<init>	()V
    //   1182: ldc_w 1039
    //   1185: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1188: aload_1
    //   1189: invokevirtual 1043	org/json/JSONException:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   1192: iconst_0
    //   1193: aaload
    //   1194: invokevirtual 1048	java/lang/StackTraceElement:getMethodName	()Ljava/lang/String;
    //   1197: invokevirtual 716	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1200: invokevirtual 720	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1203: aastore
    //   1204: invokevirtual 759	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   1207: pop
    //   1208: goto -111 -> 1097
    //   1211: astore_1
    //   1212: goto -54 -> 1158
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1215	0	this	IronSourceWebView
    //   0	1215	1	paramContext	Context
    //   841	26	2	f	float
    //   620	63	3	i	int
    //   97	1060	4	bool1	boolean
    //   93	1057	5	bool2	boolean
    //   119	38	6	bool3	boolean
    //   493	15	7	l	long
    //   13	1091	9	localJSONObject	JSONObject
    //   4	843	10	localObject1	Object
    //   39	561	11	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   15	41	1154	org/json/JSONException
    //   46	63	1154	org/json/JSONException
    //   63	70	1154	org/json/JSONException
    //   75	92	1154	org/json/JSONException
    //   99	103	1211	org/json/JSONException
    //   107	112	1211	org/json/JSONException
    //   116	121	1211	org/json/JSONException
    //   125	133	1211	org/json/JSONException
    //   137	147	1211	org/json/JSONException
    //   151	165	1211	org/json/JSONException
    //   169	214	1211	org/json/JSONException
    //   218	225	1211	org/json/JSONException
    //   234	251	1211	org/json/JSONException
    //   255	265	1211	org/json/JSONException
    //   274	288	1211	org/json/JSONException
    //   292	297	1211	org/json/JSONException
    //   306	323	1211	org/json/JSONException
    //   327	335	1211	org/json/JSONException
    //   339	350	1211	org/json/JSONException
    //   354	374	1211	org/json/JSONException
    //   378	384	1211	org/json/JSONException
    //   388	396	1211	org/json/JSONException
    //   400	417	1211	org/json/JSONException
    //   421	436	1211	org/json/JSONException
    //   440	448	1211	org/json/JSONException
    //   452	472	1211	org/json/JSONException
    //   476	482	1211	org/json/JSONException
    //   486	495	1211	org/json/JSONException
    //   499	519	1211	org/json/JSONException
    //   523	531	1211	org/json/JSONException
    //   535	543	1211	org/json/JSONException
    //   547	556	1211	org/json/JSONException
    //   560	593	1211	org/json/JSONException
    //   597	613	1211	org/json/JSONException
    //   617	621	1211	org/json/JSONException
    //   625	634	1211	org/json/JSONException
    //   638	671	1211	org/json/JSONException
    //   675	693	1211	org/json/JSONException
    //   697	706	1211	org/json/JSONException
    //   710	718	1211	org/json/JSONException
    //   722	739	1211	org/json/JSONException
    //   743	751	1211	org/json/JSONException
    //   755	763	1211	org/json/JSONException
    //   767	784	1211	org/json/JSONException
    //   788	796	1211	org/json/JSONException
    //   800	808	1211	org/json/JSONException
    //   812	829	1211	org/json/JSONException
    //   833	842	1211	org/json/JSONException
    //   846	854	1211	org/json/JSONException
    //   858	872	1211	org/json/JSONException
    //   876	881	1211	org/json/JSONException
    //   885	893	1211	org/json/JSONException
    //   897	904	1211	org/json/JSONException
    //   908	927	1211	org/json/JSONException
    //   931	947	1211	org/json/JSONException
    //   951	967	1211	org/json/JSONException
    //   971	987	1211	org/json/JSONException
    //   991	1007	1211	org/json/JSONException
    //   1011	1030	1211	org/json/JSONException
    //   1034	1050	1211	org/json/JSONException
    //   1054	1070	1211	org/json/JSONException
    //   1074	1093	1211	org/json/JSONException
  }
  
  private String getRequestParameters(JSONObject paramJSONObject)
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
      if (!paramJSONObject.keys().hasNext()) {}
    }
    try
    {
      paramJSONObject = new JSONObject(paramJSONObject, new String[] { "isSecured", "applicationKey" }).toString();
      if (!TextUtils.isEmpty(paramJSONObject)) {
        localStringBuilder.append("&").append("controllerConfig").append("=").append(paramJSONObject);
      }
      localStringBuilder.append("&").append("debug").append("=").append(getDebugMode());
      return localStringBuilder.toString();
    }
    catch (JSONException paramJSONObject)
    {
      for (;;)
      {
        paramJSONObject.printStackTrace();
      }
    }
  }
  
  private WebView getWebview()
  {
    return this;
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
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setApplicationKey(paramString1, paramProductType);
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUserID(paramString2, paramProductType);
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
      runOnUiThread(new IronSourceWebView.5(this, "javascript:" + ((StringBuilder)localObject2).toString(), (StringBuilder)localObject2));
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
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramString1.getStackTrace()[0].getMethodName() });
      }
    }
    return localJSONObject.toString();
  }
  
  private void responseBack(String paramString1, boolean paramBoolean, String paramString2, String paramString3)
  {
    Object localObject2 = new SSAObj(paramString1);
    Object localObject1 = ((SSAObj)localObject2).getString(JSON_KEY_SUCCESS);
    localObject2 = ((SSAObj)localObject2).getString(JSON_KEY_FAIL);
    if (paramBoolean) {
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        break label153;
      }
    }
    for (;;)
    {
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject2 = paramString1;
        if (TextUtils.isEmpty(paramString2)) {}
      }
      try
      {
        localObject2 = new JSONObject(paramString1).put("errMsg", paramString2).toString();
        paramString1 = (String)localObject2;
        if (!TextUtils.isEmpty(paramString3)) {}
        try
        {
          paramString1 = new JSONObject((String)localObject2).put("errCode", paramString3).toString();
          injectJavascript(generateJSToInject((String)localObject1, paramString1));
          return;
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            localObject1 = localObject2;
          }
        }
        catch (JSONException paramString1)
        {
          for (;;)
          {
            paramString1 = (String)localObject2;
          }
        }
      }
      catch (JSONException paramString2)
      {
        for (;;)
        {
          localObject2 = paramString1;
        }
      }
      label153:
      localObject1 = null;
    }
  }
  
  private void sendProductErrorMessage(SSAEnums.ProductType paramProductType, String paramString)
  {
    String str = "";
    switch (IronSourceWebView.8.$SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[paramProductType.ordinal()])
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
  
  @SuppressLint({"NewApi"})
  private void setWebDebuggingEnabled()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      setWebContentsDebuggingEnabled(true);
    }
  }
  
  private void setWebDebuggingEnabled(JSONObject paramJSONObject)
  {
    if (paramJSONObject.optBoolean("inspectWebview")) {
      setWebDebuggingEnabled();
    }
  }
  
  private void setWebViewSettings()
  {
    WebSettings localWebSettings = getSettings();
    localWebSettings.setLoadWithOverviewMode(true);
    localWebSettings.setUseWideViewPort(true);
    setVerticalScrollBarEnabled(false);
    setHorizontalScrollBarEnabled(false);
    if (Build.VERSION.SDK_INT >= 16) {}
    try
    {
      getSettings().setAllowFileAccessFromFileURLs(true);
      localWebSettings.setBuiltInZoomControls(false);
      localWebSettings.setJavaScriptEnabled(true);
      localWebSettings.setSupportMultipleWindows(true);
      localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
      localWebSettings.setGeolocationEnabled(true);
      localWebSettings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
      localWebSettings.setDomStorageEnabled(true);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          setDisplayZoomControls(localWebSettings);
          setMediaPlaybackJellyBean(localWebSettings);
          return;
        }
        catch (Throwable localThrowable)
        {
          Logger.e(this.TAG, "setWebSettings - " + localThrowable.toString());
        }
        localException = localException;
        localException.printStackTrace();
      }
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
    boolean bool4 = true;
    boolean bool2 = true;
    boolean bool1 = true;
    boolean bool5 = false;
    boolean bool3 = false;
    if (TextUtils.isEmpty(paramString))
    {
      Logger.d(this.TAG, "Trying to trigger a listener - no product was found");
      bool2 = bool3;
      return bool2;
    }
    if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
      if (this.mOnInitInterstitialListener == null) {}
    }
    do
    {
      for (;;)
      {
        bool2 = bool1;
        if (bool1) {
          break;
        }
        Logger.d(this.TAG, "Trying to trigger a listener - no listener was found for product " + paramString);
        return bool1;
        bool1 = false;
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()))
      {
        if (this.mOnRewardedVideoListener != null) {}
        for (bool1 = bool4;; bool1 = false) {
          break;
        }
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
        break label155;
      }
      bool1 = bool5;
    } while (!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWallCredits.toString()));
    label155:
    if (this.mOnOfferWallListener != null) {}
    for (bool1 = bool2;; bool1 = false) {
      break;
    }
  }
  
  private void toastingErrMsg(String paramString1, String paramString2)
  {
    paramString2 = new SSAObj(paramString2).getString("errMsg");
    if (!TextUtils.isEmpty(paramString2)) {
      runOnUiThread(new IronSourceWebView.6(this, paramString1, paramString2));
    }
  }
  
  private void triggerOnControllerInitProductFail(String paramString1, SSAEnums.ProductType paramProductType, String paramString2)
  {
    if (shouldNotifyDeveloper(paramProductType.toString())) {
      runOnUiThread(new IronSourceWebView.4(this, paramProductType, paramString1, paramString2));
    }
  }
  
  public void addMoatJSInterface(MOATJSAdapter paramMOATJSAdapter)
  {
    this.mMoatJsAdapter = paramMOATJSAdapter;
  }
  
  public void assetCached(String paramString1, String paramString2)
  {
    injectJavascript(generateJSToInject("assetCached", parseToJson("file", paramString1, "path", paramString2, null, null, null, null, null, false)));
  }
  
  public void assetCachedFailed(String paramString1, String paramString2, String paramString3)
  {
    injectJavascript(generateJSToInject("assetCachedFailed", parseToJson("file", paramString1, "path", paramString2, "errMsg", paramString3, null, null, null, false)));
  }
  
  JSInterface createJSInterface(Context paramContext)
  {
    return new JSInterface(paramContext);
  }
  
  Handler createMainThreadHandler()
  {
    return new Handler(Looper.getMainLooper());
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
    IronSourceStorageUtils.deleteFile(this.mCacheDirectory, "", "mobileController.html");
    String str = SDKUtils.getControllerUrl();
    SSAFile localSSAFile = new SSAFile(str, "");
    this.mGlobalControllerTimer = new IronSourceWebView.1(this, 200000L, 1000L).start();
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
  
  DownloadManager getDownloadManager()
  {
    return DownloadManager.getInstance(this.mCacheDirectory);
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
    Object localObject = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSearchKeys();
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
  
  String initializeCacheDirectory(Context paramContext)
  {
    return IronSourceStorageUtils.initializeCacheDirectory(paramContext.getApplicationContext());
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
        JSONObject localJSONObject = SDKUtils.getControllerConfigAsJSONObject();
        setWebDebuggingEnabled(localJSONObject);
        this.mRequestParameters = getRequestParameters(localJSONObject);
        str = str + "?" + this.mRequestParameters;
        this.mLoadControllerTimer = new IronSourceWebView.2(this, 50000L, 1000L, paramInt).start();
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
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadBlank" });
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          Logger.e(this.TAG, "WebViewController:: load: " + localThrowable2.toString());
          new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadWithPath" });
        }
      }
      Logger.i(this.TAG, "load(): Mobile Controller HTML Does not exist");
      new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=htmlControllerDoesNotExistOnFileSystem" });
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
    runOnUiThread(new IronSourceWebView.3(this));
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
      paramSSAFile = this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo).iterator();
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
      new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewPause" });
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
          break label458;
        }
        Log.d(this.TAG, "restoreState(state:" + paramAdUnitsState + ")");
        i = paramAdUnitsState.getDisplayedProduct();
        if (i == -1) {
          break label439;
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
          Object localObject2 = this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo).iterator();
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
          label439:
          Log.d(this.TAG, "No ad was opened");
        }
      }
    }
    paramAdUnitsState.setShouldRestore(false);
    label458:
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
      new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewResume" });
    }
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
  
  void setMissProduct(SSAEnums.ProductType paramProductType, String paramString)
  {
    if (paramProductType == SSAEnums.ProductType.RewardedVideo)
    {
      paramString = this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, paramString);
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
  
  public void showInterstitial(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionDepth", Integer.toString(paramJSONObject.optInt("sessionDepth")));
    injectJavascript(generateJSToInject("showInterstitial", flatMapToJsonAsString(localHashMap), "onShowInterstitialSuccess", "onShowInterstitialFail"));
  }
  
  public void showOfferWall(Map<String, String> paramMap)
  {
    this.mOWExtraParameters = paramMap;
    injectJavascript(generateJSToInject("showOfferWall", "onShowOfferWallSuccess", "onShowOfferWallFail"));
  }
  
  public void showRewardedVideo(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    String str = paramJSONObject.optString("demandSourceName");
    paramJSONObject = Integer.toString(paramJSONObject.optInt("sessionDepth"));
    if (!TextUtils.isEmpty(str)) {
      localHashMap.put("demandSourceName", str);
    }
    localHashMap.put("sessionDepth", paramJSONObject);
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
      new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
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
    
    private void callJavaScriptFunction(String paramString1, String paramString2)
    {
      if (!TextUtils.isEmpty(paramString1))
      {
        paramString1 = IronSourceWebView.this.generateJSToInject(paramString1, paramString2);
        IronSourceWebView.this.injectJavascript(paramString1);
      }
    }
    
    private void injectGetUDIA(String paramString, JSONArray paramJSONArray)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = IronSourceWebView.this.generateJSToInject(paramString, paramJSONArray.toString(), "onGetUDIASuccess", "onGetUDIAFail");
        IronSourceWebView.this.injectJavascript(paramString);
      }
    }
    
    private void sendResults(String paramString, JSONArray paramJSONArray)
    {
      Logger.i(IronSourceWebView.this.TAG, "sendResults: " + this.udiaResults);
      if (this.udiaResults <= 0) {
        injectGetUDIA(paramString, paramJSONArray);
      }
    }
    
    private void setInterstitialAvailability(boolean paramBoolean)
    {
      IronSourceWebView.access$5702(IronSourceWebView.this, Boolean.valueOf(paramBoolean));
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.toastingErrMsg("onInterstitialAvailability", String.valueOf(IronSourceWebView.this.mIsInterstitialAvailable));
      }
    }
    
    @JavascriptInterface
    public void adClicked(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "adClicked(" + paramString + ")");
      paramString = new SSAObj(paramString);
      String str = paramString.getString("productType");
      if ((str.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) && (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.12(this));
      }
      while ((!str.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) || (!IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString()))) {
        return;
      }
      paramString = paramString.getString("demandSourceName");
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.13(this, paramString));
    }
    
    @JavascriptInterface
    public void adCredited(String paramString)
    {
      String str3 = null;
      boolean bool1 = false;
      Log.d(IronSourceWebView.this.PUB_TAG, "adCredited(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      String str1 = localSSAObj.getString("credits");
      int i;
      String str6;
      int j;
      label91:
      String str4;
      String str5;
      String str2;
      if (str1 != null)
      {
        i = Integer.parseInt(str1);
        str6 = localSSAObj.getString("total");
        if (str6 == null) {
          break label199;
        }
        j = Integer.parseInt(str6);
        str4 = localSSAObj.getString("demandSourceName");
        str5 = localSSAObj.getString("productType");
        if (!localSSAObj.getBoolean("externalPoll")) {
          break label204;
        }
        str1 = IronSourceWebView.this.mOWCreditsAppKey;
        str2 = IronSourceWebView.this.mOWCreditsUserId;
        label137:
        if (!str5.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
          break label347;
        }
        if ((!localSSAObj.isNull("signature")) && (!localSSAObj.isNull("timestamp")) && (!localSSAObj.isNull("totalCreditsFlag"))) {
          break label225;
        }
        IronSourceWebView.this.responseBack(paramString, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
      }
      label199:
      label204:
      label225:
      label347:
      label354:
      for (;;)
      {
        return;
        i = 0;
        break;
        j = 0;
        break label91;
        str1 = IronSourceWebView.this.mOWAppKey;
        str2 = IronSourceWebView.this.mOWUserId;
        break label137;
        boolean bool2;
        if (localSSAObj.getString("signature").equalsIgnoreCase(SDKUtils.getMD5(str6 + str1 + str2)))
        {
          bool1 = true;
          bool2 = localSSAObj.getBoolean("totalCreditsFlag");
          str3 = localSSAObj.getString("timestamp");
        }
        for (;;)
        {
          if (!IronSourceWebView.this.shouldNotifyDeveloper(str5)) {
            break label354;
          }
          IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.5(this, str5, i, str4, bool1, j, bool2, str3, str1, str2, paramString));
          return;
          IronSourceWebView.this.responseBack(paramString, false, "Controller signature is not equal to SDK signature", null);
          break;
          bool1 = false;
          bool2 = false;
        }
      }
    }
    
    @JavascriptInterface
    public void adUnitsReady(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "adUnitsReady(" + paramString + ")");
      String str = new SSAObj(paramString).getString("demandSourceName");
      AdUnitsReady localAdUnitsReady = new AdUnitsReady(paramString);
      if (!localAdUnitsReady.isNumOfAdUnitsExist()) {
        IronSourceWebView.this.responseBack(paramString, false, "Num Of Ad Units Do Not Exist", null);
      }
      do
      {
        return;
        IronSourceWebView.this.responseBack(paramString, true, null, null);
        paramString = localAdUnitsReady.getProductType();
      } while (!IronSourceWebView.this.shouldNotifyDeveloper(paramString));
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.4(this, localAdUnitsReady, paramString, str));
    }
    
    @JavascriptInterface
    public void alert(String paramString) {}
    
    @JavascriptInterface
    public void checkInstalledApps(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "checkInstalledApps(" + paramString + ")");
      String str1 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      String str2 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
      Object localObject = new SSAObj(paramString);
      paramString = ((SSAObj)localObject).getString(IronSourceWebView.APP_IDS);
      localObject = ((SSAObj)localObject).getString(IronSourceWebView.REQUEST_ID);
      paramString = IronSourceWebView.this.getAppsStatus(paramString, (String)localObject);
      localObject = (String)paramString[0];
      if (((Boolean)paramString[1]).booleanValue())
      {
        if (TextUtils.isEmpty(str2)) {
          break label166;
        }
        paramString = str2;
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject, "onCheckInstalledAppsSuccess", "onCheckInstalledAppsFail");
          IronSourceWebView.this.injectJavascript(paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        } else {
          label166:
          paramString = null;
        }
      }
    }
    
    @JavascriptInterface
    public void createCalendarEvent(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "createCalendarEvent(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void deleteFile(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "deleteFile(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, localSSAFile.getPath()))
      {
        IronSourceWebView.this.responseBack(paramString, false, "File not exist", "1");
        return;
      }
      boolean bool = IronSourceStorageUtils.deleteFile(IronSourceWebView.this.mCacheDirectory, localSSAFile.getPath(), localSSAFile.getFile());
      IronSourceWebView.this.responseBack(paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void deleteFolder(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "deleteFolder(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, localSSAFile.getPath()))
      {
        IronSourceWebView.this.responseBack(paramString, false, "Folder not exist", "1");
        return;
      }
      boolean bool = IronSourceStorageUtils.deleteFolder(IronSourceWebView.this.mCacheDirectory, localSSAFile.getPath());
      IronSourceWebView.this.responseBack(paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void displayWebView(String paramString)
    {
      int i = 1;
      Logger.i(IronSourceWebView.this.TAG, "displayWebView(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      Object localObject = new SSAObj(paramString);
      boolean bool1 = ((Boolean)((SSAObj)localObject).get("display")).booleanValue();
      paramString = ((SSAObj)localObject).getString("productType");
      boolean bool2 = ((SSAObj)localObject).getBoolean("standaloneView");
      String str2 = ((SSAObj)localObject).getString("demandSourceName");
      Context localContext;
      String str1;
      int j;
      if (bool1)
      {
        IronSourceWebView.access$4902(IronSourceWebView.this, ((SSAObj)localObject).getBoolean("immersive"));
        IronSourceWebView.access$5002(IronSourceWebView.this, ((SSAObj)localObject).getBoolean("activityThemeTranslucent"));
        if (IronSourceWebView.this.getState() != IronSourceWebView.State.Display)
        {
          IronSourceWebView.this.setState(IronSourceWebView.State.Display);
          Logger.i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
          localContext = IronSourceWebView.this.getCurrentActivityContext();
          str1 = IronSourceWebView.this.getOrientationState();
          j = DeviceStatus.getApplicationRotation(localContext);
          if (bool2)
          {
            paramString = new ControllerView(localContext);
            paramString.addView(IronSourceWebView.this.mControllerLayout);
            paramString.showInterstitial(IronSourceWebView.this);
            return;
          }
          if (IronSourceWebView.this.mIsActivityThemeTranslucent)
          {
            localObject = new Intent(localContext, InterstitialActivity.class);
            if (!SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(paramString)) {
              break label467;
            }
            paramString = str1;
            if ("application".equals(str1)) {
              paramString = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
            }
            ((Intent)localObject).putExtra("productType", SSAEnums.ProductType.RewardedVideo.toString());
            IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.RewardedVideo.ordinal());
            IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(str2);
          }
        }
      }
      for (;;)
      {
        if ((i != 0) && (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString()))) {
          IronSourceWebView.this.mOnRewardedVideoListener.onRVAdOpened(str2);
        }
        ((Intent)localObject).setFlags(536870912);
        ((Intent)localObject).putExtra("immersive", IronSourceWebView.this.mIsImmersive);
        ((Intent)localObject).putExtra("orientation_set_flag", paramString);
        ((Intent)localObject).putExtra("rotation_set_flag", j);
        localContext.startActivity((Intent)localObject);
        return;
        localObject = new Intent(localContext, ControllerActivity.class);
        break;
        label467:
        if (SSAEnums.ProductType.OfferWall.toString().equalsIgnoreCase(paramString))
        {
          ((Intent)localObject).putExtra("productType", SSAEnums.ProductType.OfferWall.toString());
          IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
          i = 0;
          paramString = str1;
        }
        else if ((SSAEnums.ProductType.Interstitial.toString().equalsIgnoreCase(paramString)) && ("application".equals(str1)))
        {
          paramString = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
          i = 0;
          continue;
          Logger.i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
          return;
          IronSourceWebView.this.setState(IronSourceWebView.State.Gone);
          IronSourceWebView.this.closeWebView();
        }
        else
        {
          i = 0;
          paramString = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void getApplicationInfo(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getApplicationInfo(" + paramString + ")");
      String str1 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      String str2 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
      Object localObject = new SSAObj(paramString);
      paramString = ((SSAObj)localObject).getString("productType");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      Object[] arrayOfObject = new Object[2];
      paramString = IronSourceWebView.this.getApplicationParams(paramString, (String)localObject);
      localObject = (String)paramString[0];
      if (((Boolean)paramString[1]).booleanValue())
      {
        if (TextUtils.isEmpty(str2)) {
          break label171;
        }
        paramString = str2;
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject, "onGetApplicationInfoSuccess", "onGetApplicationInfoFail");
          IronSourceWebView.this.injectJavascript(paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        } else {
          label171:
          paramString = null;
        }
      }
    }
    
    @JavascriptInterface
    public void getAppsInstallTime(String paramString)
    {
      Object localObject = new SSAObj(paramString);
      try
      {
        localObject = ((SSAObj)localObject).getString("systemApps");
        localObject = DeviceStatus.getAppsInstallTime(IronSourceWebView.this.getContext(), Boolean.parseBoolean((String)localObject)).toString();
        i = 0;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Logger.i(IronSourceWebView.this.TAG, "getAppsInstallTime failed(" + localException.getLocalizedMessage() + ")");
          str1 = localException.getLocalizedMessage();
          int i = 1;
          continue;
          str2 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
          paramString = str2;
          if (TextUtils.isEmpty(str2)) {
            paramString = null;
          }
        }
      }
      if (i != 0)
      {
        paramString = IronSourceWebView.this.extractFailFunctionToCall(paramString);
        if ((!TextUtils.isEmpty(paramString)) && (TextUtils.isEmpty(paramString))) {}
      }
      try
      {
        str2 = URLDecoder.decode((String)localObject, Charset.defaultCharset().name());
        localObject = str2;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        for (;;)
        {
          String str2;
          String str1;
          localUnsupportedEncodingException.printStackTrace();
        }
      }
      paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject);
      IronSourceWebView.this.injectJavascript(paramString);
    }
    
    @JavascriptInterface
    public void getCachedFilesMap(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getCachedFilesMap(" + paramString + ")");
      String str = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      if (!TextUtils.isEmpty(str))
      {
        localObject = new SSAObj(paramString);
        if (!((SSAObj)localObject).containsKey("path")) {
          IronSourceWebView.this.responseBack(paramString, false, "path key does not exist", null);
        }
      }
      else
      {
        return;
      }
      Object localObject = (String)((SSAObj)localObject).get("path");
      if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, (String)localObject))
      {
        IronSourceWebView.this.responseBack(paramString, false, "path file does not exist on disk", null);
        return;
      }
      paramString = IronSourceStorageUtils.getCachedFilesMap(IronSourceWebView.this.mCacheDirectory, (String)localObject);
      paramString = IronSourceWebView.this.generateJSToInject(str, paramString, "onGetCachedFilesMapSuccess", "onGetCachedFilesMapFail");
      IronSourceWebView.this.injectJavascript(paramString);
    }
    
    @JavascriptInterface
    public void getControllerConfig(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getControllerConfig(" + paramString + ")");
      paramString = new SSAObj(paramString).getString(IronSourceWebView.JSON_KEY_SUCCESS);
      if (!TextUtils.isEmpty(paramString))
      {
        String str = SDKUtils.getControllerConfig();
        paramString = IronSourceWebView.this.generateJSToInject(paramString, str);
        IronSourceWebView.this.injectJavascript(paramString);
      }
    }
    
    @JavascriptInterface
    public void getDemandSourceState(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getMediationState(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str1 = ((SSAObj)localObject).getString("demandSourceName");
      String str2 = ((SSAObj)localObject).getString("productType");
      if ((str2 != null) && (str1 != null)) {
        try
        {
          localObject = SDKUtils.getProductType(str2);
          if (localObject != null)
          {
            DemandSource localDemandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName((SSAEnums.ProductType)localObject, str1);
            localObject = new JSONObject();
            ((JSONObject)localObject).put("productType", str2);
            ((JSONObject)localObject).put("demandSourceName", str1);
            if ((localDemandSource != null) && (!localDemandSource.isMediationState(-1)))
            {
              str1 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
              ((JSONObject)localObject).put("state", localDemandSource.getMediationState());
            }
            for (;;)
            {
              callJavaScriptFunction(str1, ((JSONObject)localObject).toString());
              return;
              str1 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
            }
          }
          return;
        }
        catch (Exception localException)
        {
          IronSourceWebView.this.responseBack(paramString, false, localException.getMessage(), null);
          localException.printStackTrace();
        }
      }
    }
    
    @JavascriptInterface
    public void getDeviceStatus(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getDeviceStatus(" + paramString + ")");
      String str1 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      String str2 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
      paramString = new Object[2];
      paramString = IronSourceWebView.this.getDeviceParams(IronSourceWebView.this.getContext());
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
          paramString = IronSourceWebView.this.generateJSToInject(paramString, str3, "onGetDeviceStatusSuccess", "onGetDeviceStatusFail");
          IronSourceWebView.this.injectJavascript(paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void getDeviceVolume(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getDeviceVolume(" + paramString + ")");
      try
      {
        float f = DeviceProperties.getInstance(IronSourceWebView.this.getCurrentActivityContext()).getDeviceVolume(IronSourceWebView.this.getCurrentActivityContext());
        paramString = new SSAObj(paramString);
        paramString.put("deviceVolume", String.valueOf(f));
        IronSourceWebView.this.responseBack(paramString.toString(), true, null, null);
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    
    @JavascriptInterface
    public void getOrientation(String paramString)
    {
      paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      String str = SDKUtils.getOrientation(IronSourceWebView.this.getCurrentActivityContext()).toString();
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = IronSourceWebView.this.generateJSToInject(paramString, str, "onGetOrientationSuccess", "onGetOrientationFail");
        IronSourceWebView.this.injectJavascript(paramString);
      }
    }
    
    @JavascriptInterface
    public void getUDIA(String paramString)
    {
      this.udiaResults = 0;
      Logger.i(IronSourceWebView.this.TAG, "getUDIA(" + paramString + ")");
      String str = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("getByFlag")) {
        IronSourceWebView.this.responseBack(paramString, false, "getByFlag key does not exist", null);
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
          IronSourceWebView.this.responseBack(paramString, false, "fialed to convert getByFlag", null);
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
          localJSONObject.put("sessions", IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSessions());
          IronSourceSharedPrefHelper.getSupersonicPrefHelper().deleteSessions();
          paramString.put(localJSONObject);
          if (localObject[2] != '1') {
            continue;
          }
          this.udiaResults += 1;
          localObject = LocationService.getLastLocation(IronSourceWebView.this.getContext());
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
              Logger.i(IronSourceWebView.this.TAG, "done location");
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
      Logger.i(IronSourceWebView.this.TAG, "getUserData(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("key"))
      {
        IronSourceWebView.this.responseBack(paramString, false, "key does not exist", null);
        return;
      }
      paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      localObject = ((SSAObj)localObject).getString("key");
      String str = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUserData((String)localObject);
      localObject = IronSourceWebView.this.parseToJson((String)localObject, str, null, null, null, null, null, null, null, false);
      paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject);
      IronSourceWebView.this.injectJavascript(paramString);
    }
    
    @JavascriptInterface
    public void getUserUniqueId(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getUserUniqueId(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("productType")) {
        IronSourceWebView.this.responseBack(paramString, false, "productType does not exist", null);
      }
      do
      {
        return;
        paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      } while (TextUtils.isEmpty(paramString));
      localObject = ((SSAObj)localObject).getString("productType");
      String str = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUniqueId((String)localObject);
      localObject = IronSourceWebView.this.parseToJson("userUniqueId", str, "productType", (String)localObject, null, null, null, null, null, false);
      paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject, "onGetUserUniqueIdSuccess", "onGetUserUniqueIdFail");
      IronSourceWebView.this.injectJavascript(paramString);
    }
    
    void handleControllerStageFailed()
    {
      IronSourceWebView.access$1502(IronSourceWebView.this, SSAEnums.ControllerState.Failed);
      Iterator localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo).iterator();
      while (localIterator.hasNext())
      {
        DemandSource localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, localDemandSource.getDemandSourceName());
        }
      }
      if (IronSourceWebView.this.mISmiss) {
        IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial, null);
      }
      if (IronSourceWebView.this.mOWmiss) {
        IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWall, null);
      }
      if (IronSourceWebView.this.mOWCreditsMiss) {
        IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits, null);
      }
    }
    
    void handleControllerStageLoaded()
    {
      IronSourceWebView.access$1502(IronSourceWebView.this, SSAEnums.ControllerState.Loaded);
    }
    
    void handleControllerStageReady()
    {
      IronSourceWebView.access$1502(IronSourceWebView.this, SSAEnums.ControllerState.Ready);
      IronSourceWebView.this.mGlobalControllerTimer.cancel();
      IronSourceWebView.this.mLoadControllerTimer.cancel();
      Iterator localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo).iterator();
      while (localIterator.hasNext())
      {
        DemandSource localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.initRewardedVideo(IronSourceWebView.this.mRVAppKey, IronSourceWebView.this.mRVUserId, localDemandSource.getDemandSourceName(), IronSourceWebView.this.mOnRewardedVideoListener);
        }
      }
      if (IronSourceWebView.this.mISmiss) {
        IronSourceWebView.this.initInterstitial(IronSourceWebView.this.mISAppKey, IronSourceWebView.this.mISUserId, IronSourceWebView.this.mISExtraParameters, IronSourceWebView.this.mOnInitInterstitialListener);
      }
      if (IronSourceWebView.this.mOWmiss) {
        IronSourceWebView.this.initOfferWall(IronSourceWebView.this.mOWAppKey, IronSourceWebView.this.mOWUserId, IronSourceWebView.this.mOWExtraParameters, IronSourceWebView.this.mOnOfferWallListener);
      }
      if (IronSourceWebView.this.mOWCreditsMiss) {
        IronSourceWebView.this.getOfferWallCredits(IronSourceWebView.this.mOWCreditsAppKey, IronSourceWebView.this.mOWCreditsUserId, IronSourceWebView.this.mOnOfferWallListener);
      }
      IronSourceWebView.this.restoreState(IronSourceWebView.this.mSavedState);
    }
    
    @JavascriptInterface
    public void initController(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "initController(" + paramString + ")");
      paramString = new SSAObj(paramString);
      if (paramString.containsKey("stage"))
      {
        paramString = paramString.getString("stage");
        if (!"ready".equalsIgnoreCase(paramString)) {
          break label92;
        }
        handleControllerStageReady();
      }
      for (;;)
      {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.1(this));
        return;
        label92:
        if ("loaded".equalsIgnoreCase(paramString)) {
          handleControllerStageLoaded();
        } else if ("failed".equalsIgnoreCase(paramString)) {
          handleControllerStageFailed();
        } else {
          Logger.i(IronSourceWebView.this.TAG, "No STAGE mentioned! Should not get here!");
        }
      }
    }
    
    @JavascriptInterface
    public void moatAPI(String paramString)
    {
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.25(this, paramString));
    }
    
    @JavascriptInterface
    public void onAdWindowsClosed(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onAdWindowsClosed(" + paramString + ")");
      IronSourceWebView.this.mSavedState.adClosed();
      IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(null);
      Object localObject = new SSAObj(paramString);
      paramString = ((SSAObj)localObject).getString("productType");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
        Log.d(IronSourceWebView.this.PUB_TAG, "onRVAdClosed()");
      }
      for (;;)
      {
        if ((IronSourceWebView.this.shouldNotifyDeveloper(paramString)) && (paramString != null)) {
          IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.23(this, paramString, (String)localObject));
        }
        return;
        if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
          Log.d(IronSourceWebView.this.PUB_TAG, "onISAdClosed()");
        } else if (paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
          Log.d(IronSourceWebView.this.PUB_TAG, "onOWAdClosed()");
        }
      }
    }
    
    @JavascriptInterface
    public void onGenericFunctionFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGenericFunctionFail(" + paramString + ")");
      if (IronSourceWebView.this.mOnGenericFunctionListener == null)
      {
        Logger.d(IronSourceWebView.this.TAG, "genericFunctionListener was not found");
        return;
      }
      String str = new SSAObj(paramString).getString("errMsg");
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.21(this, str));
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGenericFunctionFail", paramString);
    }
    
    @JavascriptInterface
    public void onGenericFunctionSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGenericFunctionSuccess(" + paramString + ")");
      if (IronSourceWebView.this.mOnGenericFunctionListener == null)
      {
        Logger.d(IronSourceWebView.this.TAG, "genericFunctionListener was not found");
        return;
      }
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.20(this));
      IronSourceWebView.this.responseBack(paramString, true, null, null);
    }
    
    @JavascriptInterface
    public void onGetApplicationInfoFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetApplicationInfoFail(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetApplicationInfoFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetApplicationInfoSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetApplicationInfoSuccess(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetApplicationInfoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetCachedFilesMapFail(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetCachedFilesMapFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetCachedFilesMapSuccess(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetCachedFilesMapSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetDeviceStatusFail(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetDeviceStatusFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetDeviceStatusSuccess(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetDeviceStatusSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetUDIAFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetUDIAFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUDIASuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetUDIASuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUserCreditsFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetUserCreditsFail(" + paramString + ")");
      String str = new SSAObj(paramString).getString("errMsg");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.22(this, str));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetUserCreditsFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetUserUniqueIdFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetUserUniqueIdFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUserUniqueIdSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetUserUniqueIdSuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onInitInterstitialFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialFail(" + paramString + ")");
      IronSourceWebView.this.mSavedState.setInterstitialInitSuccess(false);
      String str = new SSAObj(paramString).getString("errMsg");
      if (IronSourceWebView.this.mSavedState.reportInitInterstitial())
      {
        IronSourceWebView.this.mSavedState.setReportInitInterstitial(false);
        if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
          IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.11(this, str));
        }
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onInitInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitInterstitialSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess()");
      IronSourceWebView.this.toastingErrMsg("onInitInterstitialSuccess", "true");
      IronSourceWebView.this.mSavedState.setInterstitialInitSuccess(true);
      if (IronSourceWebView.this.mSavedState.reportInitInterstitial())
      {
        IronSourceWebView.this.mSavedState.setReportInitInterstitial(false);
        if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
          IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.10(this));
        }
      }
    }
    
    @JavascriptInterface
    public void onInitOfferWallFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitOfferWallFail(" + paramString + ")");
      IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(false);
      String str = new SSAObj(paramString).getString("errMsg");
      if (IronSourceWebView.this.mSavedState.reportInitOfferwall())
      {
        IronSourceWebView.this.mSavedState.setOfferwallReportInit(false);
        if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
          IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.16(this, str));
        }
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onInitOfferWallFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitOfferWallSuccess(String paramString)
    {
      IronSourceWebView.this.toastingErrMsg("onInitOfferWallSuccess", "true");
      IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(true);
      if (IronSourceWebView.this.mSavedState.reportInitOfferwall())
      {
        IronSourceWebView.this.mSavedState.setOfferwallReportInit(false);
        if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
          IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.15(this));
        }
      }
    }
    
    @JavascriptInterface
    public void onInitRewardedVideoFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitRewardedVideoFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      DemandSource localDemandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, (String)localObject);
      if (localDemandSource != null) {
        localDemandSource.setDemandSourceInitState(3);
      }
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.6(this, str, (String)localObject));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onInitRewardedVideoFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitRewardedVideoSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitRewardedVideoSuccess(" + paramString + ")");
      SSABCParameters localSSABCParameters = new SSABCParameters(paramString);
      IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSSABCParameters(localSSABCParameters);
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onInitRewardedVideoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onLoadInterstitialFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onLoadInterstitialFail(" + paramString + ")");
      String str = new SSAObj(paramString).getString("errMsg");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.18(this, str));
      }
      IronSourceWebView.this.toastingErrMsg("onLoadInterstitialFail", "true");
    }
    
    @JavascriptInterface
    public void onLoadInterstitialSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onLoadInterstitialSuccess(" + paramString + ")");
      setInterstitialAvailability(true);
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.17(this));
      }
      IronSourceWebView.this.toastingErrMsg("onLoadInterstitialSuccess", "true");
    }
    
    @JavascriptInterface
    public void onOfferWallGeneric(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onOfferWallGeneric(" + paramString + ")");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.mOnOfferWallListener.onOWGeneric("", "");
      }
    }
    
    @JavascriptInterface
    public void onShowInterstitialFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialFail(" + paramString + ")");
      setInterstitialAvailability(false);
      String str = new SSAObj(paramString).getString("errMsg");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.19(this, str));
      }
      IronSourceWebView.this.toastingErrMsg("onShowInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowInterstitialSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialSuccess(" + paramString + ")");
      IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.Interstitial.ordinal());
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.14(this));
        IronSourceWebView.this.toastingErrMsg("onShowInterstitialSuccess", paramString);
      }
      setInterstitialAvailability(false);
    }
    
    @JavascriptInterface
    public void onShowOfferWallFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowOfferWallFail(" + paramString + ")");
      String str = new SSAObj(paramString).getString("errMsg");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.9(this, str));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onShowOfferWallFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowOfferWallSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowOfferWallSuccess(" + paramString + ")");
      IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
      String str = SDKUtils.getValueFromJsonObject(paramString, "placementId");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.8(this, str));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onShowOfferWallSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onShowRewardedVideoFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowRewardedVideoFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.7(this, str, (String)localObject));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onShowRewardedVideoFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowRewardedVideoSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowRewardedVideoSuccess(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onShowRewardedVideoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onUDIAFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onUDIAFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onUDIASuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onUDIASuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onVideoStatusChanged(String paramString)
    {
      Log.d(IronSourceWebView.this.TAG, "onVideoStatusChanged(" + paramString + ")");
      paramString = new SSAObj(paramString);
      String str = paramString.getString("productType");
      if ((IronSourceWebView.this.mVideoEventsListener != null) && (!TextUtils.isEmpty(str)) && (SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(str)))
      {
        paramString = paramString.getString("status");
        if ("started".equalsIgnoreCase(paramString)) {
          IronSourceWebView.this.mVideoEventsListener.onVideoStarted();
        }
      }
      else
      {
        return;
      }
      if ("paused".equalsIgnoreCase(paramString))
      {
        IronSourceWebView.this.mVideoEventsListener.onVideoPaused();
        return;
      }
      if ("playing".equalsIgnoreCase(paramString))
      {
        IronSourceWebView.this.mVideoEventsListener.onVideoResumed();
        return;
      }
      if ("ended".equalsIgnoreCase(paramString))
      {
        IronSourceWebView.this.mVideoEventsListener.onVideoEnded();
        return;
      }
      if ("stopped".equalsIgnoreCase(paramString))
      {
        IronSourceWebView.this.mVideoEventsListener.onVideoStopped();
        return;
      }
      Logger.i(IronSourceWebView.this.TAG, "onVideoStatusChanged: unknown status: " + paramString);
    }
    
    @JavascriptInterface
    public void openUrl(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "openUrl(" + paramString + ")");
      Object localObject1 = new SSAObj(paramString);
      String str = ((SSAObj)localObject1).getString("url");
      Object localObject2 = ((SSAObj)localObject1).getString("method");
      localObject1 = IronSourceWebView.this.getCurrentActivityContext();
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
          ((Intent)localObject2).putExtra(IronSourceWebView.EXTERNAL_URL, str);
          ((Intent)localObject2).putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, true);
          ((Intent)localObject2).putExtra("immersive", IronSourceWebView.this.mIsImmersive);
          ((Context)localObject1).startActivity((Intent)localObject2);
          return;
        }
      }
      catch (Exception localException)
      {
        IronSourceWebView.this.responseBack(paramString, false, localException.getMessage(), null);
        localException.printStackTrace();
        return;
      }
      if (((String)localObject2).equalsIgnoreCase("store"))
      {
        localObject2 = new Intent((Context)localObject1, OpenUrlActivity.class);
        ((Intent)localObject2).putExtra(IronSourceWebView.EXTERNAL_URL, localException);
        ((Intent)localObject2).putExtra(IronSourceWebView.IS_STORE, true);
        ((Intent)localObject2).putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, true);
        ((Context)localObject1).startActivity((Intent)localObject2);
      }
    }
    
    @JavascriptInterface
    public void postAdEventNotification(String paramString)
    {
      try
      {
        Logger.i(IronSourceWebView.this.TAG, "postAdEventNotification(" + paramString + ")");
        Object localObject = new SSAObj(paramString);
        String str1 = ((SSAObj)localObject).getString("eventName");
        if (TextUtils.isEmpty(str1))
        {
          IronSourceWebView.this.responseBack(paramString, false, "eventName does not exist", null);
          return;
        }
        String str2 = ((SSAObj)localObject).getString("dsName");
        JSONObject localJSONObject = (JSONObject)((SSAObj)localObject).get("extData");
        localObject = ((SSAObj)localObject).getString("productType");
        if (IronSourceWebView.this.shouldNotifyDeveloper((String)localObject))
        {
          paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
          if (!TextUtils.isEmpty(paramString))
          {
            String str3 = IronSourceWebView.this.parseToJson("productType", (String)localObject, "eventName", str1, null, null, null, null, null, false);
            paramString = IronSourceWebView.this.generateJSToInject(paramString, str3, "postAdEventNotificationSuccess", "postAdEventNotificationFail");
            IronSourceWebView.this.injectJavascript(paramString);
          }
          IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.24(this, (String)localObject, str1, localJSONObject, str2));
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      IronSourceWebView.this.responseBack(paramString, false, "productType does not exist", null);
    }
    
    @JavascriptInterface
    public void removeCloseEventHandler(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "removeCloseEventHandler(" + paramString + ")");
      if (IronSourceWebView.this.mCloseEventTimer != null) {
        IronSourceWebView.this.mCloseEventTimer.cancel();
      }
      IronSourceWebView.access$702(IronSourceWebView.this, true);
    }
    
    @JavascriptInterface
    public void saveFile(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "saveFile(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (DeviceStatus.getAvailableMemorySizeInMegaBytes(IronSourceWebView.this.mCacheDirectory) <= 0L)
      {
        IronSourceWebView.this.responseBack(paramString, false, "no_disk_space", null);
        return;
      }
      if (!SDKUtils.isExternalStorageAvailable())
      {
        IronSourceWebView.this.responseBack(paramString, false, "sotrage_unavailable", null);
        return;
      }
      if (IronSourceStorageUtils.isFileCached(IronSourceWebView.this.mCacheDirectory, localSSAFile))
      {
        IronSourceWebView.this.responseBack(paramString, false, "file_already_exist", null);
        return;
      }
      if (!ConnectivityService.isConnected(IronSourceWebView.this.getContext()))
      {
        IronSourceWebView.this.responseBack(paramString, false, "no_network_connection", null);
        return;
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      paramString = localSSAFile.getLastUpdateTime();
      if (paramString != null)
      {
        String str2 = String.valueOf(paramString);
        if (!TextUtils.isEmpty(str2))
        {
          String str1 = localSSAFile.getPath();
          paramString = str1;
          if (str1.contains("/"))
          {
            paramString = localSSAFile.getPath().split("/");
            paramString = paramString[(paramString.length - 1)];
          }
          IronSourceSharedPrefHelper.getSupersonicPrefHelper().setCampaignLastUpdate(paramString, str2);
        }
      }
      IronSourceWebView.this.downloadManager.downloadFile(localSSAFile);
    }
    
    @JavascriptInterface
    public void setAllowFileAccessFromFileURLs(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setAllowFileAccessFromFileURLs(" + paramString + ")");
      boolean bool = new SSAObj(paramString).getBoolean("allowFileAccess");
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.3(this, bool));
    }
    
    @JavascriptInterface
    public void setBackButtonState(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setBackButtonState(" + paramString + ")");
      paramString = new SSAObj(paramString).getString("state");
      IronSourceSharedPrefHelper.getSupersonicPrefHelper().setBackButtonState(paramString);
    }
    
    @JavascriptInterface
    public void setForceClose(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setForceClose(" + paramString + ")");
      paramString = new SSAObj(paramString);
      String str1 = paramString.getString("width");
      String str2 = paramString.getString("height");
      IronSourceWebView.access$402(IronSourceWebView.this, Integer.parseInt(str1));
      IronSourceWebView.access$502(IronSourceWebView.this, Integer.parseInt(str2));
      IronSourceWebView.access$602(IronSourceWebView.this, paramString.getString("position"));
    }
    
    @JavascriptInterface
    public void setMixedContentAlwaysAllow(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setMixedContentAlwaysAllow(" + paramString + ")");
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.2(this));
    }
    
    @JavascriptInterface
    public void setOrientation(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setOrientation(" + paramString + ")");
      paramString = new SSAObj(paramString).getString("orientation");
      IronSourceWebView.this.setOrientationState(paramString);
      int i = DeviceStatus.getApplicationRotation(IronSourceWebView.this.getCurrentActivityContext());
      if (IronSourceWebView.this.mChangeListener != null) {
        IronSourceWebView.this.mChangeListener.onOrientationChanged(paramString, i);
      }
    }
    
    @JavascriptInterface
    public void setStoreSearchKeys(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setStoreSearchKeys(" + paramString + ")");
      IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSearchKeys(paramString);
    }
    
    @JavascriptInterface
    public void setUserData(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setUserData(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("key"))
      {
        IronSourceWebView.this.responseBack(paramString, false, "key does not exist", null);
        return;
      }
      if (!((SSAObj)localObject).containsKey("value"))
      {
        IronSourceWebView.this.responseBack(paramString, false, "value does not exist", null);
        return;
      }
      String str = ((SSAObj)localObject).getString("key");
      localObject = ((SSAObj)localObject).getString("value");
      if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUserData(str, (String)localObject))
      {
        paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
        str = IronSourceWebView.this.parseToJson(str, (String)localObject, null, null, null, null, null, null, null, false);
        paramString = IronSourceWebView.this.generateJSToInject(paramString, str);
        IronSourceWebView.this.injectJavascript(paramString);
        return;
      }
      IronSourceWebView.this.responseBack(paramString, false, "SetUserData failed writing to shared preferences", null);
    }
    
    @JavascriptInterface
    public void setUserUniqueId(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setUserUniqueId(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if ((!((SSAObj)localObject).containsKey("userUniqueId")) || (!((SSAObj)localObject).containsKey("productType")))
      {
        IronSourceWebView.this.responseBack(paramString, false, "uniqueId or productType does not exist", null);
        return;
      }
      String str = ((SSAObj)localObject).getString("userUniqueId");
      localObject = ((SSAObj)localObject).getString("productType");
      if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUniqueId(str, (String)localObject))
      {
        IronSourceWebView.this.responseBack(paramString, true, null, null);
        return;
      }
      IronSourceWebView.this.responseBack(paramString, false, "setUserUniqueId failed", null);
    }
    
    @JavascriptInterface
    public void setWebviewBackgroundColor(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setWebviewBackgroundColor(" + paramString + ")");
      IronSourceWebView.this.setWebviewBackground(paramString);
    }
    
    @JavascriptInterface
    public void toggleUDIA(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "toggleUDIA(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("toggle")) {
        IronSourceWebView.this.responseBack(paramString, false, "toggle key does not exist", null);
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
        IronSourceWebView.this.responseBack(paramString, false, "fialed to convert toggle", null);
        return;
      }
      if (localObject.toCharArray()[3] == '0')
      {
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(true);
        return;
      }
      IronSourceSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(false);
    }
  }
  
  public static enum State
  {
    Display,  Gone;
    
    private State() {}
  }
}
