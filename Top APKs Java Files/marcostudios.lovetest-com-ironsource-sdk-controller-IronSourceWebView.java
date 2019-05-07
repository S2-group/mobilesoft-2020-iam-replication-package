package com.ironsource.sdk.controller;

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
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
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
import com.ironsource.environment.ApplicationContext;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
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

public class IronSourceWebView
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
  private String PUB_TAG = "IronSource";
  private String TAG = IronSourceWebView.class.getSimpleName();
  private DownloadManager downloadManager;
  private Boolean isKitkatAndAbove = null;
  private boolean isRemoveCloseEventHandler;
  private String mCacheDirectory;
  private OnWebViewChangeListener mChangeListener;
  private CountDownTimer mCloseEventTimer;
  private BroadcastReceiver mConnectionReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (IronSourceWebView.this.mControllerState == SSAEnums.ControllerState.Ready)
      {
        paramAnonymousIntent = "none";
        if (!ConnectivityService.isConnectedWifi(paramAnonymousContext)) {
          break label35;
        }
        paramAnonymousIntent = "wifi";
      }
      for (;;)
      {
        IronSourceWebView.this.deviceStatusChanged(paramAnonymousIntent);
        return;
        label35:
        if (ConnectivityService.isConnectedMobile(paramAnonymousContext)) {
          paramAnonymousIntent = "3g";
        }
      }
    }
  };
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
    this.mWebChromeClient = new ChromeClient(null);
    setWebViewClient(new ViewClient(null));
    setWebChromeClient(this.mWebChromeClient);
    setWebViewSettings();
    addJavascriptInterface(createJSInterface(paramContext), "Android");
    setDownloadListener(this);
    setOnTouchListener(new SupersonicWebViewTouchListener(null));
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
    //   0: iconst_0
    //   1: istore_3
    //   2: new 735	org/json/JSONObject
    //   5: dup
    //   6: invokespecial 736	org/json/JSONObject:<init>	()V
    //   9: astore 9
    //   11: ldc_w 805
    //   14: astore 4
    //   16: ldc_w 805
    //   19: astore 5
    //   21: aconst_null
    //   22: astore 7
    //   24: aconst_null
    //   25: astore 6
    //   27: aload_1
    //   28: invokestatic 674	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   31: ifne +371 -> 402
    //   34: aload_1
    //   35: getstatic 639	com/ironsource/sdk/data/SSAEnums$ProductType:RewardedVideo	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   38: invokevirtual 806	com/ironsource/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   41: invokevirtual 809	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   44: ifeq +256 -> 300
    //   47: aload_0
    //   48: getfield 425	com/ironsource/sdk/controller/IronSourceWebView:mRVAppKey	Ljava/lang/String;
    //   51: astore 7
    //   53: aload_0
    //   54: getfield 428	com/ironsource/sdk/controller/IronSourceWebView:mRVUserId	Ljava/lang/String;
    //   57: astore 8
    //   59: aload_0
    //   60: getfield 329	com/ironsource/sdk/controller/IronSourceWebView:mDemandSourceManager	Lcom/ironsource/sdk/controller/DemandSourceManager;
    //   63: getstatic 639	com/ironsource/sdk/data/SSAEnums$ProductType:RewardedVideo	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   66: aload_2
    //   67: invokevirtual 645	com/ironsource/sdk/controller/DemandSourceManager:getDemandSourceByName	(Lcom/ironsource/sdk/data/SSAEnums$ProductType;Ljava/lang/String;)Lcom/ironsource/sdk/data/DemandSource;
    //   70: astore 10
    //   72: aload 7
    //   74: astore_2
    //   75: aload 6
    //   77: astore 4
    //   79: aload 8
    //   81: astore 5
    //   83: aload 10
    //   85: ifnull +17 -> 102
    //   88: aload 10
    //   90: invokevirtual 664	com/ironsource/sdk/data/DemandSource:getExtraParams	()Ljava/util/Map;
    //   93: astore 4
    //   95: aload 8
    //   97: astore 5
    //   99: aload 7
    //   101: astore_2
    //   102: aload 9
    //   104: ldc_w 711
    //   107: aload_1
    //   108: invokevirtual 811	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   111: pop
    //   112: aload 5
    //   114: invokestatic 674	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   117: ifne +326 -> 443
    //   120: aload 9
    //   122: ldc_w 658
    //   125: invokestatic 771	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   128: aload 5
    //   130: invokestatic 771	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   133: invokevirtual 811	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   136: pop
    //   137: aload_2
    //   138: invokestatic 674	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   141: ifne +336 -> 477
    //   144: aload 9
    //   146: ldc_w 650
    //   149: invokestatic 771	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   152: aload_2
    //   153: invokestatic 771	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   156: invokevirtual 811	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   159: pop
    //   160: aload 4
    //   162: ifnull +320 -> 482
    //   165: aload 4
    //   167: invokeinterface 813 1 0
    //   172: ifne +310 -> 482
    //   175: aload 4
    //   177: invokeinterface 740 1 0
    //   182: invokeinterface 746 1 0
    //   187: astore_1
    //   188: aload_1
    //   189: invokeinterface 752 1 0
    //   194: ifeq +288 -> 482
    //   197: aload_1
    //   198: invokeinterface 756 1 0
    //   203: checkcast 758	java/util/Map$Entry
    //   206: astore_2
    //   207: aload_2
    //   208: invokeinterface 761 1 0
    //   213: checkcast 763	java/lang/String
    //   216: ldc_w 815
    //   219: invokevirtual 809	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   222: ifeq +16 -> 238
    //   225: aload_0
    //   226: aload_2
    //   227: invokeinterface 766 1 0
    //   232: checkcast 763	java/lang/String
    //   235: invokespecial 818	com/ironsource/sdk/controller/IronSourceWebView:setWebviewCache	(Ljava/lang/String;)V
    //   238: aload 9
    //   240: aload_2
    //   241: invokeinterface 761 1 0
    //   246: checkcast 763	java/lang/String
    //   249: invokestatic 771	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   252: aload_2
    //   253: invokeinterface 766 1 0
    //   258: checkcast 763	java/lang/String
    //   261: invokestatic 771	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   264: invokevirtual 811	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   267: pop
    //   268: goto -80 -> 188
    //   271: astore_2
    //   272: aload_2
    //   273: invokevirtual 821	org/json/JSONException:printStackTrace	()V
    //   276: new 823	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   279: dup
    //   280: invokespecial 824	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   283: iconst_1
    //   284: anewarray 763	java/lang/String
    //   287: dup
    //   288: iconst_0
    //   289: ldc_w 826
    //   292: aastore
    //   293: invokevirtual 830	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   296: pop
    //   297: goto -109 -> 188
    //   300: aload_1
    //   301: getstatic 689	com/ironsource/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   304: invokevirtual 806	com/ironsource/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   307: invokevirtual 809	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   310: ifeq +23 -> 333
    //   313: aload_0
    //   314: getfield 439	com/ironsource/sdk/controller/IronSourceWebView:mISAppKey	Ljava/lang/String;
    //   317: astore_2
    //   318: aload_0
    //   319: getfield 442	com/ironsource/sdk/controller/IronSourceWebView:mISUserId	Ljava/lang/String;
    //   322: astore 5
    //   324: aload_0
    //   325: getfield 446	com/ironsource/sdk/controller/IronSourceWebView:mISExtraParameters	Ljava/util/Map;
    //   328: astore 4
    //   330: goto -228 -> 102
    //   333: aload 4
    //   335: astore_2
    //   336: aload 6
    //   338: astore 4
    //   340: aload_1
    //   341: getstatic 698	com/ironsource/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   344: invokevirtual 806	com/ironsource/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   347: invokevirtual 809	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   350: ifeq -248 -> 102
    //   353: aload_0
    //   354: getfield 456	com/ironsource/sdk/controller/IronSourceWebView:mOWAppKey	Ljava/lang/String;
    //   357: astore_2
    //   358: aload_0
    //   359: getfield 459	com/ironsource/sdk/controller/IronSourceWebView:mOWUserId	Ljava/lang/String;
    //   362: astore 5
    //   364: aload_0
    //   365: getfield 463	com/ironsource/sdk/controller/IronSourceWebView:mOWExtraParameters	Ljava/util/Map;
    //   368: astore 4
    //   370: goto -268 -> 102
    //   373: astore_1
    //   374: aload_1
    //   375: invokevirtual 821	org/json/JSONException:printStackTrace	()V
    //   378: new 823	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   381: dup
    //   382: invokespecial 824	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   385: iconst_1
    //   386: anewarray 763	java/lang/String
    //   389: dup
    //   390: iconst_0
    //   391: ldc_w 832
    //   394: aastore
    //   395: invokevirtual 830	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   398: pop
    //   399: goto -287 -> 112
    //   402: iconst_1
    //   403: istore_3
    //   404: aload 4
    //   406: astore_2
    //   407: aload 7
    //   409: astore 4
    //   411: goto -299 -> 112
    //   414: astore_1
    //   415: aload_1
    //   416: invokevirtual 821	org/json/JSONException:printStackTrace	()V
    //   419: new 823	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   422: dup
    //   423: invokespecial 824	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   426: iconst_1
    //   427: anewarray 763	java/lang/String
    //   430: dup
    //   431: iconst_0
    //   432: ldc_w 834
    //   435: aastore
    //   436: invokevirtual 830	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   439: pop
    //   440: goto -303 -> 137
    //   443: iconst_1
    //   444: istore_3
    //   445: goto -308 -> 137
    //   448: astore_1
    //   449: aload_1
    //   450: invokevirtual 821	org/json/JSONException:printStackTrace	()V
    //   453: new 823	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   456: dup
    //   457: invokespecial 824	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   460: iconst_1
    //   461: anewarray 763	java/lang/String
    //   464: dup
    //   465: iconst_0
    //   466: ldc_w 836
    //   469: aastore
    //   470: invokevirtual 830	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   473: pop
    //   474: goto -314 -> 160
    //   477: iconst_1
    //   478: istore_3
    //   479: goto -319 -> 160
    //   482: iconst_2
    //   483: anewarray 298	java/lang/Object
    //   486: dup
    //   487: iconst_0
    //   488: aload 9
    //   490: invokevirtual 792	org/json/JSONObject:toString	()Ljava/lang/String;
    //   493: aastore
    //   494: dup
    //   495: iconst_1
    //   496: iload_3
    //   497: invokestatic 842	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   500: aastore
    //   501: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	502	0	this	IronSourceWebView
    //   0	502	1	paramString1	String
    //   0	502	2	paramString2	String
    //   1	496	3	bool	boolean
    //   14	396	4	localObject1	Object
    //   19	344	5	localObject2	Object
    //   25	312	6	localObject3	Object
    //   22	386	7	str1	String
    //   57	39	8	str2	String
    //   9	480	9	localJSONObject	JSONObject
    //   70	19	10	localDemandSource	DemandSource
    // Exception table:
    //   from	to	target	type
    //   238	268	271	org/json/JSONException
    //   102	112	373	org/json/JSONException
    //   120	137	414	org/json/JSONException
    //   144	160	448	org/json/JSONException
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
    boolean bool3 = false;
    boolean bool2 = false;
    Object localObject1 = DeviceProperties.getInstance(paramContext);
    JSONObject localJSONObject = new JSONObject();
    boolean bool1 = bool3;
    for (;;)
    {
      try
      {
        localJSONObject.put("appOrientation", SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(getCurrentActivityContext())));
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
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("deviceVolume"), f);
        }
        bool1 = bool2;
        paramContext = getCurrentActivityContext();
        bool1 = bool2;
        if (Build.VERSION.SDK_INT >= 19)
        {
          bool1 = bool2;
          if ((paramContext instanceof Activity))
          {
            bool1 = bool2;
            localJSONObject.put(SDKUtils.encodeString("immersiveMode"), DeviceStatus.isImmersiveSupported((Activity)paramContext));
          }
        }
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("batteryLevel"), DeviceStatus.getBatteryLevel(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("mcc"), ConnectivityService.getNetworkMCC(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("mnc"), ConnectivityService.getNetworkMNC(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("phoneType"), ConnectivityService.getPhoneType(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("simOperator"), SDKUtils.encodeString(ConnectivityService.getSimOperator(paramContext)));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("lastUpdateTime"), ApplicationContext.getLastUpdateTime(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("firstInstallTime"), ApplicationContext.getFirstInstallTime(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("appVersion"), SDKUtils.encodeString(ApplicationContext.getApplicationVersionName(paramContext)));
        bool1 = bool2;
      }
      catch (JSONException paramContext)
      {
        paramContext.printStackTrace();
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
        continue;
      }
      return new Object[] { localJSONObject.toString(), Boolean.valueOf(bool1) };
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
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          Logger.i(IronSourceWebView.this.TAG, this.val$url);
          try
          {
            if (IronSourceWebView.this.isKitkatAndAbove != null)
            {
              if (IronSourceWebView.this.isKitkatAndAbove.booleanValue())
              {
                IronSourceWebView.this.evaluateJavascriptKitKat(this.val$scriptBuilder.toString());
                return;
              }
              IronSourceWebView.this.loadUrl(this.val$url);
              return;
            }
          }
          catch (Throwable localThrowable1)
          {
            Logger.e(IronSourceWebView.this.TAG, "injectJavascript: " + localThrowable1.toString());
            new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=injectJavaScript" });
            return;
          }
          int i = Build.VERSION.SDK_INT;
          if (i >= 19) {
            try
            {
              IronSourceWebView.this.evaluateJavascriptKitKat(this.val$scriptBuilder.toString());
              IronSourceWebView.access$6502(IronSourceWebView.this, Boolean.valueOf(true));
              return;
            }
            catch (NoSuchMethodError localNoSuchMethodError)
            {
              Logger.e(IronSourceWebView.this.TAG, "evaluateJavascrip NoSuchMethodError: SDK version=" + Build.VERSION.SDK_INT + " " + localNoSuchMethodError);
              IronSourceWebView.this.loadUrl(this.val$url);
              IronSourceWebView.access$6502(IronSourceWebView.this, Boolean.valueOf(false));
              return;
            }
            catch (Throwable localThrowable2)
            {
              Logger.e(IronSourceWebView.this.TAG, "evaluateJavascrip Exception: SDK version=" + Build.VERSION.SDK_INT + " " + localThrowable2);
              IronSourceWebView.this.loadUrl(this.val$url);
              IronSourceWebView.access$6502(IronSourceWebView.this, Boolean.valueOf(false));
              return;
            }
          }
          IronSourceWebView.this.loadUrl(this.val$url);
          IronSourceWebView.access$6502(IronSourceWebView.this, Boolean.valueOf(false));
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
    switch (8.$SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[paramProductType.ordinal()])
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
    boolean bool = false;
    if (TextUtils.isEmpty(paramString))
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
  
  private void toastingErrMsg(final String paramString1, final String paramString2)
  {
    paramString2 = new SSAObj(paramString2).getString("errMsg");
    if (!TextUtils.isEmpty(paramString2)) {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (IronSourceWebView.this.getDebugMode() == SSAEnums.DebugMode.MODE_3.getValue()) {
            Toast.makeText(IronSourceWebView.this.getCurrentActivityContext(), paramString1 + " : " + paramString2, 1).show();
          }
        }
      });
    }
  }
  
  private void triggerOnControllerInitProductFail(final String paramString1, final SSAEnums.ProductType paramProductType, final String paramString2)
  {
    if (shouldNotifyDeveloper(paramProductType.toString())) {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (SSAEnums.ProductType.RewardedVideo == paramProductType)
          {
            Log.d(IronSourceWebView.this.TAG, "onRVInitFail(message:" + paramString1 + ")");
            IronSourceWebView.this.mOnRewardedVideoListener.onRVInitFail(paramString1, paramString2);
          }
          do
          {
            do
            {
              return;
              if (SSAEnums.ProductType.Interstitial != paramProductType) {
                break;
              }
              IronSourceWebView.this.mSavedState.setInterstitialInitSuccess(false);
            } while (!IronSourceWebView.this.mSavedState.reportInitInterstitial());
            Log.d(IronSourceWebView.this.TAG, "onInterstitialInitFail(message:" + paramString1 + ")");
            IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialInitFailed(paramString1);
            IronSourceWebView.this.mSavedState.setReportInitInterstitial(false);
            return;
            if (SSAEnums.ProductType.OfferWall == paramProductType)
            {
              IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitFail(paramString1);
              return;
            }
          } while (SSAEnums.ProductType.OfferWallCredits != paramProductType);
          IronSourceWebView.this.mOnOfferWallListener.onGetOWCreditsFailed(paramString1);
        }
      });
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
    this.mGlobalControllerTimer = new CountDownTimer(200000L, 1000L)
    {
      public void onFinish()
      {
        Logger.i(IronSourceWebView.this.TAG, "Global Controller Timer Finish");
        IronSourceWebView.access$902(IronSourceWebView.this, true);
      }
      
      public void onTick(long paramAnonymousLong)
      {
        Logger.i(IronSourceWebView.this.TAG, "Global Controller Timer Tick " + paramAnonymousLong);
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
        this.mLoadControllerTimer = new CountDownTimer(50000L, 1000L)
        {
          public void onFinish()
          {
            Logger.i(IronSourceWebView.this.TAG, "Loading Controller Timer Finish");
            if (this.val$loadAttemp == 3)
            {
              IronSourceWebView.this.mGlobalControllerTimer.cancel();
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
              return;
            }
            IronSourceWebView.this.load(2);
          }
          
          public void onTick(long paramAnonymousLong)
          {
            Logger.i(IronSourceWebView.this.TAG, "Loading Controller Timer Tick " + paramAnonymousLong);
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
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialLoadSuccess();
      }
    });
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
  
  private class ChromeClient
    extends WebChromeClient
  {
    private ChromeClient() {}
    
    public View getVideoLoadingProgressView()
    {
      FrameLayout localFrameLayout = new FrameLayout(IronSourceWebView.this.getCurrentActivityContext());
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
      paramWebView.setWebViewClient(new IronSourceWebView.FrameBustWebViewClient(IronSourceWebView.this, null));
      ((WebView.WebViewTransport)paramMessage.obj).setWebView(paramWebView);
      paramMessage.sendToTarget();
      Logger.i("onCreateWindow", "onCreateWindow");
      return true;
    }
    
    public void onHideCustomView()
    {
      Logger.i("Test", "onHideCustomView");
      if (IronSourceWebView.this.mCustomView == null) {
        return;
      }
      IronSourceWebView.this.mCustomView.setVisibility(8);
      IronSourceWebView.this.mCustomViewContainer.removeView(IronSourceWebView.this.mCustomView);
      IronSourceWebView.access$1202(IronSourceWebView.this, null);
      IronSourceWebView.this.mCustomViewContainer.setVisibility(8);
      IronSourceWebView.this.mCustomViewCallback.onCustomViewHidden();
      IronSourceWebView.this.setVisibility(0);
    }
    
    public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
    {
      Logger.i("Test", "onShowCustomView");
      IronSourceWebView.this.setVisibility(8);
      if (IronSourceWebView.this.mCustomView != null)
      {
        Logger.i("Test", "mCustomView != null");
        paramCustomViewCallback.onCustomViewHidden();
        return;
      }
      Logger.i("Test", "mCustomView == null");
      IronSourceWebView.this.mCustomViewContainer.addView(paramView);
      IronSourceWebView.access$1202(IronSourceWebView.this, paramView);
      IronSourceWebView.access$1402(IronSourceWebView.this, paramCustomViewCallback);
      IronSourceWebView.this.mCustomViewContainer.setVisibility(0);
    }
  }
  
  private class FrameBustWebViewClient
    extends WebViewClient
  {
    private FrameBustWebViewClient() {}
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      paramWebView = IronSourceWebView.this.getCurrentActivityContext();
      Intent localIntent = new Intent(paramWebView, OpenUrlActivity.class);
      localIntent.putExtra(IronSourceWebView.EXTERNAL_URL, paramString);
      localIntent.putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, false);
      paramWebView.startActivity(localIntent);
      return true;
    }
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
    public void adClicked(final String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "adClicked(" + paramString + ")");
      paramString = new SSAObj(paramString);
      String str = paramString.getString("productType");
      if ((str.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) && (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialClick();
          }
        });
      }
      while ((!str.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) || (!IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString()))) {
        return;
      }
      paramString = paramString.getString("demandSourceName");
      IronSourceWebView.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          IronSourceWebView.this.mOnRewardedVideoListener.onRVAdClicked(paramString);
        }
      });
    }
    
    @JavascriptInterface
    public void adCredited(final String paramString)
    {
      Log.d(IronSourceWebView.this.PUB_TAG, "adCredited(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      final String str1 = localSSAObj.getString("credits");
      final int i;
      String str6;
      final int j;
      label85:
      final String str4;
      final String str5;
      final boolean bool2;
      final String str3;
      boolean bool3;
      final String str2;
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
        str1 = IronSourceWebView.this.mOWCreditsAppKey;
        str2 = IronSourceWebView.this.mOWCreditsUserId;
      }
      for (;;)
      {
        if (str5.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()))
        {
          if ((localSSAObj.isNull("signature")) || (localSSAObj.isNull("timestamp")) || (localSSAObj.isNull("totalCreditsFlag")))
          {
            IronSourceWebView.this.responseBack(paramString, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
            return;
            i = 0;
            break;
            label209:
            j = 0;
            break label85;
            label214:
            str1 = IronSourceWebView.this.mOWAppKey;
            str2 = IronSourceWebView.this.mOWUserId;
            continue;
          }
          if (!localSSAObj.getString("signature").equalsIgnoreCase(SDKUtils.getMD5(str6 + str1 + str2))) {
            break label342;
          }
        }
      }
      for (final boolean bool1 = true;; bool1 = bool3)
      {
        bool2 = localSSAObj.getBoolean("totalCreditsFlag");
        str3 = localSSAObj.getString("timestamp");
        if (!IronSourceWebView.this.shouldNotifyDeveloper(str5)) {
          break;
        }
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (str5.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
              IronSourceWebView.this.mOnRewardedVideoListener.onRVAdCredited(i, str4);
            }
            while ((!str5.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) || (!bool1) || (!IronSourceWebView.this.mOnOfferWallListener.onOWAdCredited(i, j, bool2)) || (TextUtils.isEmpty(str3))) {
              return;
            }
            if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setLatestCompeltionsTime(str3, str1, str2))
            {
              IronSourceWebView.this.responseBack(paramString, true, null, null);
              return;
            }
            IronSourceWebView.this.responseBack(paramString, false, "Time Stamp could not be stored", null);
          }
        });
        return;
        label342:
        IronSourceWebView.this.responseBack(paramString, false, "Controller signature is not equal to SDK signature", null);
      }
    }
    
    @JavascriptInterface
    public void adUnitsReady(final String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "adUnitsReady(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("demandSourceName");
      final AdUnitsReady localAdUnitsReady = new AdUnitsReady(paramString);
      if (!localAdUnitsReady.isNumOfAdUnitsExist()) {
        IronSourceWebView.this.responseBack(paramString, false, "Num Of Ad Units Do Not Exist", null);
      }
      do
      {
        return;
        IronSourceWebView.this.responseBack(paramString, true, null, null);
        paramString = localAdUnitsReady.getProductType();
      } while (!IronSourceWebView.this.shouldNotifyDeveloper(paramString));
      IronSourceWebView.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (Integer.parseInt(localAdUnitsReady.getNumOfAdUnits()) > 0) {}
          for (int i = 1;; i = 0)
          {
            if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()))
            {
              if (i == 0) {
                break;
              }
              Log.d(IronSourceWebView.this.TAG, "onRVInitSuccess()");
              IronSourceWebView.this.mOnRewardedVideoListener.onRVInitSuccess(localAdUnitsReady, str);
            }
            return;
          }
          IronSourceWebView.this.mOnRewardedVideoListener.onRVNoMoreOffers(str);
        }
      });
    }
    
    @JavascriptInterface
    public void alert(String paramString) {}
    
    @JavascriptInterface
    public void checkInstalledApps(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "checkInstalledApps(" + paramString + ")");
      String str1 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      String str2 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
      Object localObject1 = null;
      Object localObject2 = new SSAObj(paramString);
      paramString = ((SSAObj)localObject2).getString(IronSourceWebView.APP_IDS);
      localObject2 = ((SSAObj)localObject2).getString(IronSourceWebView.REQUEST_ID);
      paramString = IronSourceWebView.this.getAppsStatus(paramString, (String)localObject2);
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
          paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject2, "onCheckInstalledAppsSuccess", "onCheckInstalledAppsFail");
          IronSourceWebView.this.injectJavascript(paramString);
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
      Logger.i(IronSourceWebView.this.TAG, "displayWebView(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      paramString = new SSAObj(paramString);
      boolean bool1 = ((Boolean)paramString.get("display")).booleanValue();
      String str3 = paramString.getString("productType");
      boolean bool2 = paramString.getBoolean("standaloneView");
      String str2 = paramString.getString("demandSourceName");
      int j = 0;
      if (bool1)
      {
        IronSourceWebView.access$4902(IronSourceWebView.this, paramString.getBoolean("immersive"));
        IronSourceWebView.access$5002(IronSourceWebView.this, paramString.getBoolean("activityThemeTranslucent"));
        if (IronSourceWebView.this.getState() != IronSourceWebView.State.Display)
        {
          IronSourceWebView.this.setState(IronSourceWebView.State.Display);
          Logger.i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
          Context localContext = IronSourceWebView.this.getCurrentActivityContext();
          String str1 = IronSourceWebView.this.getOrientationState();
          int k = DeviceStatus.getApplicationRotation(localContext);
          if (bool2)
          {
            paramString = new ControllerView(localContext);
            paramString.addView(IronSourceWebView.this.mControllerLayout);
            paramString.showInterstitial(IronSourceWebView.this);
            return;
          }
          Intent localIntent;
          int i;
          if (IronSourceWebView.this.mIsActivityThemeTranslucent)
          {
            localIntent = new Intent(localContext, InterstitialActivity.class);
            if (!SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(str3)) {
              break label466;
            }
            paramString = str1;
            if ("application".equals(str1)) {
              paramString = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
            }
            i = 1;
            localIntent.putExtra("productType", SSAEnums.ProductType.RewardedVideo.toString());
            IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.RewardedVideo.ordinal());
            IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(str2);
          }
          for (;;)
          {
            if ((i != 0) && (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString()))) {
              IronSourceWebView.this.mOnRewardedVideoListener.onRVAdOpened(str2);
            }
            localIntent.setFlags(536870912);
            localIntent.putExtra("immersive", IronSourceWebView.this.mIsImmersive);
            localIntent.putExtra("orientation_set_flag", paramString);
            localIntent.putExtra("rotation_set_flag", k);
            localContext.startActivity(localIntent);
            return;
            localIntent = new Intent(localContext, ControllerActivity.class);
            break;
            label466:
            if (SSAEnums.ProductType.OfferWall.toString().equalsIgnoreCase(str3))
            {
              localIntent.putExtra("productType", SSAEnums.ProductType.OfferWall.toString());
              IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
              i = j;
              paramString = str1;
            }
            else
            {
              i = j;
              paramString = str1;
              if (SSAEnums.ProductType.Interstitial.toString().equalsIgnoreCase(str3))
              {
                i = j;
                paramString = str1;
                if ("application".equals(str1))
                {
                  paramString = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
                  i = j;
                }
              }
            }
          }
        }
        Logger.i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
        return;
      }
      IronSourceWebView.this.setState(IronSourceWebView.State.Gone);
      IronSourceWebView.this.closeWebView();
    }
    
    @JavascriptInterface
    public void getApplicationInfo(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getApplicationInfo(" + paramString + ")");
      String str1 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      String str2 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
      paramString = new SSAObj(paramString);
      String str3 = paramString.getString("productType");
      Object localObject = paramString.getString("demandSourceName");
      paramString = null;
      Object[] arrayOfObject = new Object[2];
      localObject = IronSourceWebView.this.getApplicationParams(str3, (String)localObject);
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
          paramString = IronSourceWebView.this.generateJSToInject(paramString, str3, "onGetApplicationInfoSuccess", "onGetApplicationInfoFail");
          IronSourceWebView.this.injectJavascript(paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void getAppsInstallTime(String paramString)
    {
      Object localObject = new SSAObj(paramString);
      int i = 1;
      str2 = null;
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
          continue;
          String str3 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
          paramString = str2;
          if (!TextUtils.isEmpty(str3)) {
            paramString = str3;
          }
        }
      }
      if (i != 0)
      {
        str3 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
        paramString = str2;
        if (!TextUtils.isEmpty(str3)) {
          paramString = str3;
        }
        if (TextUtils.isEmpty(paramString)) {}
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
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (Build.VERSION.SDK_INT >= 16) {}
            try
            {
              IronSourceWebView.this.getSettings().setAllowFileAccessFromFileURLs(false);
              return;
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
          }
        });
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
    public void moatAPI(final String paramString)
    {
      IronSourceWebView.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          try
          {
            Logger.i(IronSourceWebView.this.TAG, "moatAPI(" + paramString + ")");
            SSAObj localSSAObj = new SSAObj(paramString);
            IronSourceWebView.this.mMoatJsAdapter.call(localSSAObj.toString(), new IronSourceWebView.JSInterface.JSCallbackTask(IronSourceWebView.JSInterface.this), IronSourceWebView.this.getWebview());
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            Logger.i(IronSourceWebView.this.TAG, "moatAPI failed with exception " + localException.getMessage());
          }
        }
      });
    }
    
    @JavascriptInterface
    public void onAdWindowsClosed(final String paramString)
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
          IronSourceWebView.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
                IronSourceWebView.this.mOnRewardedVideoListener.onRVAdClosed(this.val$demandSourceName);
              }
              do
              {
                return;
                if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString()))
                {
                  IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialClose();
                  return;
                }
              } while (!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()));
              IronSourceWebView.this.mOnOfferWallListener.onOWAdClosed();
            }
          });
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
      final String str = new SSAObj(paramString).getString("errMsg");
      IronSourceWebView.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          IronSourceWebView.this.mOnGenericFunctionListener.onGFFail(str);
        }
      });
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
      IronSourceWebView.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          IronSourceWebView.this.mOnGenericFunctionListener.onGFSuccess();
        }
      });
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
      final String str = new SSAObj(paramString).getString("errMsg");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            String str2 = str;
            String str1 = str2;
            if (str2 == null) {
              str1 = "We're sorry, some error occurred. we will investigate it";
            }
            IronSourceWebView.this.mOnOfferWallListener.onGetOWCreditsFailed(str1);
          }
        });
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
      final String str = new SSAObj(paramString).getString("errMsg");
      if (IronSourceWebView.this.mSavedState.reportInitInterstitial())
      {
        IronSourceWebView.this.mSavedState.setReportInitInterstitial(false);
        if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
          IronSourceWebView.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              String str2 = str;
              String str1 = str2;
              if (str2 == null) {
                str1 = "We're sorry, some error occurred. we will investigate it";
              }
              Log.d(IronSourceWebView.this.TAG, "onInterstitialInitFail(message:" + str1 + ")");
              IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialInitFailed(str1);
            }
          });
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
          IronSourceWebView.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              Log.d(IronSourceWebView.this.TAG, "onInterstitialInitSuccess()");
              IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialInitSuccess();
            }
          });
        }
      }
    }
    
    @JavascriptInterface
    public void onInitOfferWallFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitOfferWallFail(" + paramString + ")");
      IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(false);
      final String str = new SSAObj(paramString).getString("errMsg");
      if (IronSourceWebView.this.mSavedState.reportInitOfferwall())
      {
        IronSourceWebView.this.mSavedState.setOfferwallReportInit(false);
        if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
          IronSourceWebView.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              String str2 = str;
              String str1 = str2;
              if (str2 == null) {
                str1 = "We're sorry, some error occurred. we will investigate it";
              }
              Log.d(IronSourceWebView.this.TAG, "onOfferWallInitFail(message:" + str1 + ")");
              IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitFail(str1);
            }
          });
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
          IronSourceWebView.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              Log.d(IronSourceWebView.this.TAG, "onOfferWallInitSuccess()");
              IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitSuccess();
            }
          });
        }
      }
    }
    
    @JavascriptInterface
    public void onInitRewardedVideoFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitRewardedVideoFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      final String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      DemandSource localDemandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, (String)localObject);
      if (localDemandSource != null) {
        localDemandSource.setDemandSourceInitState(3);
      }
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            String str2 = str;
            String str1 = str2;
            if (str2 == null) {
              str1 = "We're sorry, some error occurred. we will investigate it";
            }
            Log.d(IronSourceWebView.this.TAG, "onRVInitFail(message:" + str + ")");
            IronSourceWebView.this.mOnRewardedVideoListener.onRVInitFail(str1, this.val$demandSourceName);
          }
        });
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
      final String str = new SSAObj(paramString).getString("errMsg");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            String str2 = str;
            String str1 = str2;
            if (str2 == null) {
              str1 = "We're sorry, some error occurred. we will investigate it";
            }
            IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialLoadFailed(str1);
          }
        });
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
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialLoadSuccess();
          }
        });
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
      final String str = new SSAObj(paramString).getString("errMsg");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            String str2 = str;
            String str1 = str2;
            if (str2 == null) {
              str1 = "We're sorry, some error occurred. we will investigate it";
            }
            IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialShowFailed(str1);
          }
        });
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
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialOpen();
            IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialShowSuccess();
          }
        });
        IronSourceWebView.this.toastingErrMsg("onShowInterstitialSuccess", paramString);
      }
      setInterstitialAvailability(false);
    }
    
    @JavascriptInterface
    public void onShowOfferWallFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowOfferWallFail(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("errMsg");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            String str2 = str;
            String str1 = str2;
            if (str2 == null) {
              str1 = "We're sorry, some error occurred. we will investigate it";
            }
            IronSourceWebView.this.mOnOfferWallListener.onOWShowFail(str1);
          }
        });
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onShowOfferWallFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowOfferWallSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowOfferWallSuccess(" + paramString + ")");
      IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
      final String str = SDKUtils.getValueFromJsonObject(paramString, "placementId");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            IronSourceWebView.this.mOnOfferWallListener.onOWShowSuccess(str);
          }
        });
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onShowOfferWallSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onShowRewardedVideoFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowRewardedVideoFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      final String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            String str2 = str;
            String str1 = str2;
            if (str2 == null) {
              str1 = "We're sorry, some error occurred. we will investigate it";
            }
            Log.d(IronSourceWebView.this.TAG, "onRVShowFail(message:" + str + ")");
            IronSourceWebView.this.mOnRewardedVideoListener.onRVShowFail(str1, this.val$demandSourceName);
          }
        });
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
        final String str1 = ((SSAObj)localObject).getString("eventName");
        if (TextUtils.isEmpty(str1))
        {
          IronSourceWebView.this.responseBack(paramString, false, "eventName does not exist", null);
          return;
        }
        final String str2 = ((SSAObj)localObject).getString("dsName");
        final JSONObject localJSONObject = (JSONObject)((SSAObj)localObject).get("extData");
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
          IronSourceWebView.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              if (this.val$productType.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialEventNotificationReceived(str1, localJSONObject);
              }
              do
              {
                return;
                if (this.val$productType.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()))
                {
                  IronSourceWebView.this.mOnRewardedVideoListener.onRVEventNotificationReceived(str1, str2, localJSONObject);
                  return;
                }
              } while (!this.val$productType.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()));
              IronSourceWebView.this.mOnOfferWallListener.onOfferwallEventNotificationReceived(str1, localJSONObject);
            }
          });
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
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setCampaignLastUpdate(paramString, str);
        IronSourceWebView.this.downloadManager.downloadFile(localSSAFile);
        return;
      }
    }
    
    @JavascriptInterface
    public void setAllowFileAccessFromFileURLs(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setAllowFileAccessFromFileURLs(" + paramString + ")");
      final boolean bool = new SSAObj(paramString).getBoolean("allowFileAccess");
      IronSourceWebView.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (Build.VERSION.SDK_INT >= 16) {}
          try
          {
            IronSourceWebView.this.getSettings().setAllowFileAccessFromFileURLs(bool);
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      });
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
      IronSourceWebView.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (Build.VERSION.SDK_INT >= 21) {
            IronSourceWebView.this.getSettings().setMixedContentMode(0);
          }
        }
      });
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
    
    class JSCallbackTask
    {
      JSCallbackTask() {}
      
      void sendMessage(boolean paramBoolean, String paramString1, String paramString2)
      {
        SSAObj localSSAObj = new SSAObj();
        if (paramBoolean) {}
        for (String str = IronSourceWebView.JSON_KEY_SUCCESS;; str = IronSourceWebView.JSON_KEY_FAIL)
        {
          localSSAObj.put(str, paramString1);
          localSSAObj.put("data", paramString2);
          IronSourceWebView.this.responseBack(localSSAObj.toString(), paramBoolean, null, null);
          return;
        }
      }
    }
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
        Logger.i(IronSourceWebView.this.TAG, "X:" + (int)f1 + " Y:" + (int)f2);
        i1 = DeviceStatus.getDeviceWidth();
        n = DeviceStatus.getDeviceHeight();
        Logger.i(IronSourceWebView.this.TAG, "Width:" + i1 + " Height:" + n);
        k = SDKUtils.dpToPx(IronSourceWebView.this.mHiddenForceCloseWidth);
        m = SDKUtils.dpToPx(IronSourceWebView.this.mHiddenForceCloseHeight);
        i = 0;
        j = 0;
        if (!"top-right".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
          break label239;
        }
        i = i1 - (int)f1;
        j = (int)f2;
      }
      for (;;)
      {
        if ((i <= k) && (j <= m))
        {
          IronSourceWebView.access$702(IronSourceWebView.this, false);
          if (IronSourceWebView.this.mCloseEventTimer != null) {
            IronSourceWebView.this.mCloseEventTimer.cancel();
          }
          IronSourceWebView.access$802(IronSourceWebView.this, new CountDownTimer(2000L, 500L)
          {
            public void onFinish()
            {
              Logger.i(IronSourceWebView.this.TAG, "Close Event Timer Finish");
              if (IronSourceWebView.this.isRemoveCloseEventHandler)
              {
                IronSourceWebView.access$702(IronSourceWebView.this, false);
                return;
              }
              IronSourceWebView.this.engageEnd("forceClose");
            }
            
            public void onTick(long paramAnonymousLong)
            {
              Logger.i(IronSourceWebView.this.TAG, "Close Event Timer Tick " + paramAnonymousLong);
            }
          }.start());
        }
        return false;
        label239:
        if ("top-left".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation))
        {
          i = (int)f1;
          j = (int)f2;
        }
        else if ("bottom-right".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation))
        {
          i = i1 - (int)f1;
          j = n - (int)f2;
        }
        else if ("bottom-left".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation))
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
        IronSourceWebView.this.pageFinished();
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
        localObject = "file://" + IronSourceWebView.this.mCacheDirectory + File.separator + "mraid.js";
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
      try
      {
        if (IronSourceWebView.this.handleSearchKeysURLs(paramString))
        {
          IronSourceWebView.this.interceptedUrlToStore();
          return true;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return super.shouldOverrideUrlLoading(paramWebView, paramString);
    }
  }
}
