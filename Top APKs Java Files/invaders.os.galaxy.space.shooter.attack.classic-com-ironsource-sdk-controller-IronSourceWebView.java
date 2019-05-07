package com.ironsource.sdk.controller;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.MutableContextWrapper;
import android.content.pm.ApplicationInfo;
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
import com.ironsource.environment.ConnectivityService;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.environment.LocationService;
import com.ironsource.environment.LocationService.ISLocationListener;
import com.ironsource.environment.UrlHandler;
import com.ironsource.sdk.constants.Constants.JSMethods;
import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.data.AdUnitsState;
import com.ironsource.sdk.data.DemandSource;
import com.ironsource.sdk.data.ProductParameters;
import com.ironsource.sdk.data.SSABCParameters;
import com.ironsource.sdk.data.SSAEnums.ControllerState;
import com.ironsource.sdk.data.SSAEnums.DebugMode;
import com.ironsource.sdk.data.SSAEnums.ProductType;
import com.ironsource.sdk.data.SSAFile;
import com.ironsource.sdk.data.SSAObj;
import com.ironsource.sdk.listeners.OnGenericFunctionListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.sdk.listeners.OnWebViewChangeListener;
import com.ironsource.sdk.listeners.internals.DSAdProductListener;
import com.ironsource.sdk.listeners.internals.DSInterstitialListener;
import com.ironsource.sdk.listeners.internals.DSRewardedVideoListener;
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
import java.util.ArrayList;
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
  private ArrayList<String> mControllerCommandsQueue;
  private String mControllerKeyPressed = "interrupt";
  private FrameLayout mControllerLayout;
  private SSAEnums.ControllerState mControllerState = SSAEnums.ControllerState.None;
  Context mCurrentActivityContext;
  private View mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private FrameLayout mCustomViewContainer;
  private DSInterstitialListener mDSInterstitialListener;
  private DSRewardedVideoListener mDSRewardedVideoListener;
  private DemandSourceManager mDemandSourceManager;
  private boolean mGlobalControllerTimeFinish;
  private CountDownTimer mGlobalControllerTimer;
  private int mHiddenForceCloseHeight = 50;
  private String mHiddenForceCloseLocation = "top-right";
  private int mHiddenForceCloseWidth = 50;
  private String mISAppKey;
  private String mISUserId;
  private boolean mIsActivityThemeTranslucent = false;
  private boolean mIsImmersive = false;
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
  private OnOfferWallListener mOnOfferWallListener;
  private String mOrientationState;
  private PermissionsJSAdapter mPermissionsJsAdapter;
  private ProductParametersCollection mProductParametersCollection = new ProductParametersCollection();
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
    this.mControllerCommandsQueue = new ArrayList();
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
  
  private boolean controllerCommandSupportsQueue(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("updateConsentInfo");
    return localArrayList.contains(paramString);
  }
  
  private String createInitProductJSMethod(SSAEnums.ProductType paramProductType, DemandSource paramDemandSource)
  {
    Object localObject = "";
    if ((paramProductType == SSAEnums.ProductType.RewardedVideo) || (paramProductType == SSAEnums.ProductType.Interstitial) || (paramProductType == SSAEnums.ProductType.OfferWall))
    {
      localObject = new HashMap();
      ProductParameters localProductParameters = this.mProductParametersCollection.getProductParameters(paramProductType);
      if (localProductParameters != null)
      {
        ((Map)localObject).put("applicationKey", localProductParameters.appKey);
        ((Map)localObject).put("applicationUserId", localProductParameters.userId);
      }
      if (paramDemandSource != null)
      {
        if (paramDemandSource.getExtraParams() != null) {
          ((Map)localObject).putAll(paramDemandSource.getExtraParams());
        }
        ((Map)localObject).put("demandSourceName", paramDemandSource.getDemandSourceName());
        paramDemandSource = flatMapToJsonAsString((Map)localObject);
        paramProductType = Constants.JSMethods.getInitMethodByProduct(paramProductType);
        paramDemandSource = generateJSToInject(paramProductType.methodName, paramDemandSource, paramProductType.successCallbackName, paramProductType.failureCallbackName);
      }
    }
    do
    {
      return paramDemandSource;
      if (getExtraParamsByProduct(paramProductType) == null) {
        break;
      }
      ((Map)localObject).putAll(getExtraParamsByProduct(paramProductType));
      break;
      paramDemandSource = (DemandSource)localObject;
    } while (paramProductType != SSAEnums.ProductType.OfferWallCredits);
    return generateJSToInject("getUserCredits", parseToJson("productType", "OfferWall", "applicationKey", this.mOWCreditsAppKey, "applicationUserId", this.mOWCreditsUserId, null, null, null, false), "null", "onGetUserCreditsFail");
  }
  
  private SSAObj createLocationObject(String paramString, Location paramLocation)
  {
    paramString = new SSAObj(paramString);
    if (paramLocation != null)
    {
      paramString.put("provider", paramLocation.getProvider());
      paramString.put("latitude", Double.toString(paramLocation.getLatitude()));
      paramString.put("longitude", Double.toString(paramLocation.getLongitude()));
      paramString.put("altitude", Double.toString(paramLocation.getAltitude()));
      paramString.put("time", Long.toString(paramLocation.getTime()));
      paramString.put("accuracy", Float.toString(paramLocation.getAccuracy()));
      paramString.put("bearing", Float.toString(paramLocation.getBearing()));
      paramString.put("speed", Float.toString(paramLocation.getSpeed()));
      return paramString;
    }
    paramString.put("error", "location data is not available");
    return paramString;
  }
  
  private String createShowProductJSMethod(SSAEnums.ProductType paramProductType, JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionDepth", Integer.toString(paramJSONObject.optInt("sessionDepth")));
    paramJSONObject = paramJSONObject.optString("demandSourceName");
    DemandSource localDemandSource = this.mDemandSourceManager.getDemandSourceByName(paramProductType, paramJSONObject);
    if (localDemandSource != null)
    {
      if (localDemandSource.getExtraParams() != null) {
        localHashMap.putAll(localDemandSource.getExtraParams());
      }
      if (!TextUtils.isEmpty(paramJSONObject)) {
        localHashMap.put("demandSourceName", paramJSONObject);
      }
    }
    for (;;)
    {
      paramJSONObject = flatMapToJsonAsString(localHashMap);
      paramProductType = Constants.JSMethods.getShowMethodByProduct(paramProductType);
      return generateJSToInject(paramProductType.methodName, paramJSONObject, paramProductType.successCallbackName, paramProductType.failureCallbackName);
      if (getExtraParamsByProduct(paramProductType) != null) {
        localHashMap.putAll(getExtraParamsByProduct(paramProductType));
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
  
  private DSAdProductListener getAdProductListenerByProductType(SSAEnums.ProductType paramProductType)
  {
    if (paramProductType == SSAEnums.ProductType.Interstitial) {
      return this.mDSInterstitialListener;
    }
    if (paramProductType == SSAEnums.ProductType.RewardedVideo) {
      return this.mDSRewardedVideoListener;
    }
    return null;
  }
  
  /* Error */
  private Object[] getApplicationParams(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 840	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 881	org/json/JSONObject:<init>	()V
    //   7: astore 9
    //   9: ldc_w 676
    //   12: astore 6
    //   14: ldc_w 676
    //   17: astore 5
    //   19: aconst_null
    //   20: astore 7
    //   22: aconst_null
    //   23: astore 4
    //   25: aload_1
    //   26: invokestatic 864	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   29: ifne +363 -> 392
    //   32: aload_0
    //   33: aload_1
    //   34: invokespecial 590	com/ironsource/sdk/controller/IronSourceWebView:getStringProductTypeAsEnum	(Ljava/lang/String;)Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   37: astore 10
    //   39: aload 10
    //   41: getstatic 682	com/ironsource/sdk/data/SSAEnums$ProductType:RewardedVideo	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   44: if_acmpeq +11 -> 55
    //   47: aload 10
    //   49: getstatic 685	com/ironsource/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   52: if_acmpne +282 -> 334
    //   55: aload_0
    //   56: getfield 313	com/ironsource/sdk/controller/IronSourceWebView:mProductParametersCollection	Lcom/ironsource/sdk/controller/ProductParametersCollection;
    //   59: aload 10
    //   61: invokevirtual 695	com/ironsource/sdk/controller/ProductParametersCollection:getProductParameters	(Lcom/ironsource/sdk/data/SSAEnums$ProductType;)Lcom/ironsource/sdk/data/ProductParameters;
    //   64: astore 5
    //   66: aload 5
    //   68: getfield 702	com/ironsource/sdk/data/ProductParameters:appKey	Ljava/lang/String;
    //   71: astore 7
    //   73: aload 5
    //   75: getfield 713	com/ironsource/sdk/data/ProductParameters:userId	Ljava/lang/String;
    //   78: astore 8
    //   80: aload_0
    //   81: getfield 341	com/ironsource/sdk/controller/IronSourceWebView:mDemandSourceManager	Lcom/ironsource/sdk/controller/DemandSourceManager;
    //   84: aload 10
    //   86: aload_2
    //   87: invokevirtual 858	com/ironsource/sdk/controller/DemandSourceManager:getDemandSourceByName	(Lcom/ironsource/sdk/data/SSAEnums$ProductType;Ljava/lang/String;)Lcom/ironsource/sdk/data/DemandSource;
    //   90: astore 10
    //   92: aload 8
    //   94: astore 5
    //   96: aload 7
    //   98: astore 6
    //   100: aload 10
    //   102: ifnull +30 -> 132
    //   105: aload 10
    //   107: invokevirtual 719	com/ironsource/sdk/data/DemandSource:getExtraParams	()Ljava/util/Map;
    //   110: astore 4
    //   112: aload 4
    //   114: ldc_w 725
    //   117: aload_2
    //   118: invokeinterface 708 3 0
    //   123: pop
    //   124: aload 7
    //   126: astore 6
    //   128: aload 8
    //   130: astore 5
    //   132: aload 9
    //   134: ldc_w 758
    //   137: aload_1
    //   138: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   141: pop
    //   142: iconst_0
    //   143: istore_3
    //   144: aload 5
    //   146: invokestatic 864	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   149: ifne +281 -> 430
    //   152: aload 9
    //   154: ldc_w 710
    //   157: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   160: aload 5
    //   162: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   165: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   168: pop
    //   169: aload 6
    //   171: invokestatic 864	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   174: ifne +290 -> 464
    //   177: aload 9
    //   179: ldc_w 697
    //   182: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   185: aload 6
    //   187: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   190: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   193: pop
    //   194: aload 4
    //   196: ifnull +273 -> 469
    //   199: aload 4
    //   201: invokeinterface 951 1 0
    //   206: ifne +263 -> 469
    //   209: aload 4
    //   211: invokeinterface 885 1 0
    //   216: invokeinterface 891 1 0
    //   221: astore_1
    //   222: aload_1
    //   223: invokeinterface 897 1 0
    //   228: ifeq +241 -> 469
    //   231: aload_1
    //   232: invokeinterface 901 1 0
    //   237: checkcast 903	java/util/Map$Entry
    //   240: astore_2
    //   241: aload_2
    //   242: invokeinterface 906 1 0
    //   247: checkcast 908	java/lang/String
    //   250: ldc_w 953
    //   253: invokevirtual 956	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   256: ifeq +16 -> 272
    //   259: aload_0
    //   260: aload_2
    //   261: invokeinterface 911 1 0
    //   266: checkcast 908	java/lang/String
    //   269: invokespecial 959	com/ironsource/sdk/controller/IronSourceWebView:setWebviewCache	(Ljava/lang/String;)V
    //   272: aload 9
    //   274: aload_2
    //   275: invokeinterface 906 1 0
    //   280: checkcast 908	java/lang/String
    //   283: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   286: aload_2
    //   287: invokeinterface 911 1 0
    //   292: checkcast 908	java/lang/String
    //   295: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   298: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   301: pop
    //   302: goto -80 -> 222
    //   305: astore_2
    //   306: aload_2
    //   307: invokevirtual 962	org/json/JSONException:printStackTrace	()V
    //   310: new 964	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   313: dup
    //   314: invokespecial 965	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   317: iconst_1
    //   318: anewarray 908	java/lang/String
    //   321: dup
    //   322: iconst_0
    //   323: ldc_w 967
    //   326: aastore
    //   327: invokevirtual 971	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   330: pop
    //   331: goto -109 -> 222
    //   334: aload 10
    //   336: getstatic 688	com/ironsource/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   339: if_acmpne -207 -> 132
    //   342: aload_0
    //   343: getfield 465	com/ironsource/sdk/controller/IronSourceWebView:mOWAppKey	Ljava/lang/String;
    //   346: astore 6
    //   348: aload_0
    //   349: getfield 468	com/ironsource/sdk/controller/IronSourceWebView:mOWUserId	Ljava/lang/String;
    //   352: astore 5
    //   354: aload_0
    //   355: getfield 472	com/ironsource/sdk/controller/IronSourceWebView:mOWExtraParameters	Ljava/util/Map;
    //   358: astore 4
    //   360: goto -228 -> 132
    //   363: astore_1
    //   364: aload_1
    //   365: invokevirtual 962	org/json/JSONException:printStackTrace	()V
    //   368: new 964	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   371: dup
    //   372: invokespecial 965	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   375: iconst_1
    //   376: anewarray 908	java/lang/String
    //   379: dup
    //   380: iconst_0
    //   381: ldc_w 973
    //   384: aastore
    //   385: invokevirtual 971	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   388: pop
    //   389: goto -247 -> 142
    //   392: iconst_1
    //   393: istore_3
    //   394: aload 7
    //   396: astore 4
    //   398: goto -254 -> 144
    //   401: astore_1
    //   402: aload_1
    //   403: invokevirtual 962	org/json/JSONException:printStackTrace	()V
    //   406: new 964	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   409: dup
    //   410: invokespecial 965	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   413: iconst_1
    //   414: anewarray 908	java/lang/String
    //   417: dup
    //   418: iconst_0
    //   419: ldc_w 975
    //   422: aastore
    //   423: invokevirtual 971	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   426: pop
    //   427: goto -258 -> 169
    //   430: iconst_1
    //   431: istore_3
    //   432: goto -263 -> 169
    //   435: astore_1
    //   436: aload_1
    //   437: invokevirtual 962	org/json/JSONException:printStackTrace	()V
    //   440: new 964	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   443: dup
    //   444: invokespecial 965	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   447: iconst_1
    //   448: anewarray 908	java/lang/String
    //   451: dup
    //   452: iconst_0
    //   453: ldc_w 977
    //   456: aastore
    //   457: invokevirtual 971	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   460: pop
    //   461: goto -267 -> 194
    //   464: iconst_1
    //   465: istore_3
    //   466: goto -272 -> 194
    //   469: iconst_2
    //   470: anewarray 300	java/lang/Object
    //   473: dup
    //   474: iconst_0
    //   475: aload 9
    //   477: invokevirtual 936	org/json/JSONObject:toString	()Ljava/lang/String;
    //   480: aastore
    //   481: dup
    //   482: iconst_1
    //   483: iload_3
    //   484: invokestatic 983	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   487: aastore
    //   488: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	489	0	this	IronSourceWebView
    //   0	489	1	paramString1	String
    //   0	489	2	paramString2	String
    //   143	341	3	bool	boolean
    //   23	374	4	localObject1	Object
    //   17	336	5	localObject2	Object
    //   12	335	6	localObject3	Object
    //   20	375	7	str1	String
    //   78	51	8	str2	String
    //   7	469	9	localJSONObject	JSONObject
    //   37	298	10	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   272	302	305	org/json/JSONException
    //   132	142	363	org/json/JSONException
    //   152	169	401	org/json/JSONException
    //   177	194	435	org/json/JSONException
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
    //   1: invokestatic 1027	com/ironsource/sdk/utils/DeviceProperties:getInstance	(Landroid/content/Context;)Lcom/ironsource/sdk/utils/DeviceProperties;
    //   4: astore 10
    //   6: new 840	org/json/JSONObject
    //   9: dup
    //   10: invokespecial 881	org/json/JSONObject:<init>	()V
    //   13: astore 9
    //   15: aload 9
    //   17: ldc_w 1029
    //   20: aload_0
    //   21: invokevirtual 1032	com/ironsource/sdk/controller/IronSourceWebView:getCurrentActivityContext	()Landroid/content/Context;
    //   24: invokestatic 1036	com/ironsource/environment/DeviceStatus:getActivityRequestedOrientation	(Landroid/content/Context;)I
    //   27: invokestatic 1039	com/ironsource/sdk/utils/SDKUtils:translateRequestedOrientation	(I)Ljava/lang/String;
    //   30: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   33: pop
    //   34: aload 10
    //   36: invokevirtual 1042	com/ironsource/sdk/utils/DeviceProperties:getDeviceOem	()Ljava/lang/String;
    //   39: astore 11
    //   41: aload 11
    //   43: ifnull +20 -> 63
    //   46: aload 9
    //   48: ldc_w 1044
    //   51: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   54: aload 11
    //   56: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   59: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   62: pop
    //   63: aload 10
    //   65: invokevirtual 1047	com/ironsource/sdk/utils/DeviceProperties:getDeviceModel	()Ljava/lang/String;
    //   68: astore 11
    //   70: aload 11
    //   72: ifnull +1097 -> 1169
    //   75: aload 9
    //   77: ldc_w 1049
    //   80: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   83: aload 11
    //   85: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   88: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   91: pop
    //   92: iconst_0
    //   93: istore 5
    //   95: iload 5
    //   97: istore 4
    //   99: aload_1
    //   100: invokestatic 1052	com/ironsource/sdk/utils/SDKUtils:loadGoogleAdvertiserInfo	(Landroid/content/Context;)V
    //   103: iload 5
    //   105: istore 4
    //   107: invokestatic 1055	com/ironsource/sdk/utils/SDKUtils:getAdvertiserId	()Ljava/lang/String;
    //   110: astore 11
    //   112: iload 5
    //   114: istore 4
    //   116: invokestatic 1058	com/ironsource/sdk/utils/SDKUtils:isLimitAdTrackingEnabled	()Z
    //   119: istore 6
    //   121: iload 5
    //   123: istore 4
    //   125: aload 11
    //   127: invokestatic 864	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   130: ifne +84 -> 214
    //   133: iload 5
    //   135: istore 4
    //   137: aload_0
    //   138: getfield 269	com/ironsource/sdk/controller/IronSourceWebView:TAG	Ljava/lang/String;
    //   141: ldc_w 1060
    //   144: invokestatic 326	com/ironsource/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   147: iload 5
    //   149: istore 4
    //   151: aload 9
    //   153: ldc_w 1061
    //   156: iload 6
    //   158: invokestatic 983	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   161: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   164: pop
    //   165: iload 5
    //   167: istore 4
    //   169: aload 9
    //   171: new 925	java/lang/StringBuilder
    //   174: dup
    //   175: invokespecial 926	java/lang/StringBuilder:<init>	()V
    //   178: ldc_w 1063
    //   181: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: ldc_w 1065
    //   187: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: ldc_w 1067
    //   193: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: ldc_w 1069
    //   199: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: invokevirtual 935	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   205: aload 11
    //   207: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   210: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   213: pop
    //   214: iload 5
    //   216: istore 4
    //   218: aload 10
    //   220: invokevirtual 1072	com/ironsource/sdk/utils/DeviceProperties:getDeviceOsType	()Ljava/lang/String;
    //   223: astore 11
    //   225: aload 11
    //   227: ifnull +948 -> 1175
    //   230: iload 5
    //   232: istore 4
    //   234: aload 9
    //   236: ldc_w 1074
    //   239: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   242: aload 11
    //   244: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   247: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   250: pop
    //   251: iload 5
    //   253: istore 4
    //   255: aload 10
    //   257: invokevirtual 1077	com/ironsource/sdk/utils/DeviceProperties:getDeviceOsVersion	()Ljava/lang/String;
    //   260: astore 11
    //   262: aload 11
    //   264: ifnull +917 -> 1181
    //   267: iload 5
    //   269: istore 4
    //   271: aload 11
    //   273: ldc_w 1079
    //   276: ldc_w 676
    //   279: invokevirtual 1082	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   282: astore 11
    //   284: iload 5
    //   286: istore 4
    //   288: aload 9
    //   290: ldc_w 1084
    //   293: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   296: aload 11
    //   298: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   301: pop
    //   302: iload 5
    //   304: istore 4
    //   306: aload 10
    //   308: invokevirtual 1087	com/ironsource/sdk/utils/DeviceProperties:getDeviceApiLevel	()I
    //   311: invokestatic 1089	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   314: astore 11
    //   316: aload 11
    //   318: ifnull +869 -> 1187
    //   321: iload 5
    //   323: istore 4
    //   325: aload 9
    //   327: ldc_w 1091
    //   330: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   333: aload 11
    //   335: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   338: pop
    //   339: iload 5
    //   341: istore 4
    //   343: invokestatic 1094	com/ironsource/sdk/utils/DeviceProperties:getSupersonicSdkVersion	()Ljava/lang/String;
    //   346: astore 11
    //   348: aload 11
    //   350: ifnull +24 -> 374
    //   353: iload 5
    //   355: istore 4
    //   357: aload 9
    //   359: ldc_w 1096
    //   362: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   365: aload 11
    //   367: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   370: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   373: pop
    //   374: iload 5
    //   376: istore 4
    //   378: aload 10
    //   380: invokevirtual 1099	com/ironsource/sdk/utils/DeviceProperties:getDeviceCarrier	()Ljava/lang/String;
    //   383: ifnull +42 -> 425
    //   386: iload 5
    //   388: istore 4
    //   390: aload 10
    //   392: invokevirtual 1099	com/ironsource/sdk/utils/DeviceProperties:getDeviceCarrier	()Ljava/lang/String;
    //   395: invokevirtual 1100	java/lang/String:length	()I
    //   398: ifle +27 -> 425
    //   401: iload 5
    //   403: istore 4
    //   405: aload 9
    //   407: ldc_w 1102
    //   410: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   413: aload 10
    //   415: invokevirtual 1099	com/ironsource/sdk/utils/DeviceProperties:getDeviceCarrier	()Ljava/lang/String;
    //   418: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   421: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   424: pop
    //   425: iload 5
    //   427: istore 4
    //   429: aload_1
    //   430: invokestatic 1107	com/ironsource/environment/ConnectivityService:getConnectionType	(Landroid/content/Context;)Ljava/lang/String;
    //   433: astore 10
    //   435: iload 5
    //   437: istore 4
    //   439: aload 10
    //   441: invokestatic 864	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   444: ifne +749 -> 1193
    //   447: iload 5
    //   449: istore 4
    //   451: aload 9
    //   453: ldc_w 1109
    //   456: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   459: aload 10
    //   461: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   464: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   467: pop
    //   468: iload 5
    //   470: istore 4
    //   472: aload_1
    //   473: invokevirtual 1113	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   476: invokevirtual 1119	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   479: getfield 1125	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   482: invokevirtual 1130	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   485: astore 10
    //   487: iload 5
    //   489: istore 4
    //   491: aload 10
    //   493: invokestatic 864	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   496: ifne +27 -> 523
    //   499: iload 5
    //   501: istore 4
    //   503: aload 9
    //   505: ldc_w 1132
    //   508: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   511: aload 10
    //   513: invokevirtual 1135	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   516: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   519: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   522: pop
    //   523: iload 5
    //   525: istore 4
    //   527: invokestatic 1138	com/ironsource/sdk/utils/SDKUtils:isExternalStorageAvailable	()Z
    //   530: ifeq +669 -> 1199
    //   533: iload 5
    //   535: istore 4
    //   537: aload_0
    //   538: getfield 337	com/ironsource/sdk/controller/IronSourceWebView:mCacheDirectory	Ljava/lang/String;
    //   541: invokestatic 1142	com/ironsource/environment/DeviceStatus:getAvailableMemorySizeInMegaBytes	(Ljava/lang/String;)J
    //   544: lstore 7
    //   546: iload 5
    //   548: istore 4
    //   550: aload 9
    //   552: ldc_w 1144
    //   555: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   558: lload 7
    //   560: invokestatic 1146	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   563: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   566: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   569: pop
    //   570: iload 5
    //   572: istore 4
    //   574: invokestatic 1149	com/ironsource/environment/DeviceStatus:getDeviceWidth	()I
    //   577: invokestatic 1089	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   580: astore 10
    //   582: iload 5
    //   584: istore 4
    //   586: aload 10
    //   588: invokestatic 864	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   591: ifne +614 -> 1205
    //   594: iload 5
    //   596: istore 4
    //   598: new 925	java/lang/StringBuilder
    //   601: dup
    //   602: invokespecial 926	java/lang/StringBuilder:<init>	()V
    //   605: astore 11
    //   607: iload 5
    //   609: istore 4
    //   611: aload 11
    //   613: ldc_w 1151
    //   616: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   619: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   622: ldc_w 1065
    //   625: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   628: ldc_w 1153
    //   631: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   634: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   637: ldc_w 1069
    //   640: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   643: pop
    //   644: iload 5
    //   646: istore 4
    //   648: aload 9
    //   650: aload 11
    //   652: invokevirtual 935	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   655: aload 10
    //   657: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   660: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   663: pop
    //   664: iload 5
    //   666: istore 4
    //   668: invokestatic 1156	com/ironsource/environment/DeviceStatus:getDeviceHeight	()I
    //   671: istore_3
    //   672: iload 5
    //   674: istore 4
    //   676: new 925	java/lang/StringBuilder
    //   679: dup
    //   680: invokespecial 926	java/lang/StringBuilder:<init>	()V
    //   683: astore 10
    //   685: iload 5
    //   687: istore 4
    //   689: aload 10
    //   691: ldc_w 1151
    //   694: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   697: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   700: ldc_w 1065
    //   703: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   706: ldc_w 1158
    //   709: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   712: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   715: ldc_w 1069
    //   718: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   721: pop
    //   722: iload 5
    //   724: istore 4
    //   726: aload 9
    //   728: aload 10
    //   730: invokevirtual 935	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   733: iload_3
    //   734: invokestatic 1089	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   737: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   740: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   743: pop
    //   744: iload 5
    //   746: istore 4
    //   748: aload_0
    //   749: invokevirtual 988	com/ironsource/sdk/controller/IronSourceWebView:getContext	()Landroid/content/Context;
    //   752: invokestatic 1163	com/ironsource/environment/ApplicationContext:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   755: astore 10
    //   757: iload 5
    //   759: istore 4
    //   761: aload 10
    //   763: invokestatic 864	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   766: ifne +24 -> 790
    //   769: iload 5
    //   771: istore 4
    //   773: aload 9
    //   775: ldc_w 1165
    //   778: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   781: aload 10
    //   783: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   786: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   789: pop
    //   790: iload 5
    //   792: istore 4
    //   794: invokestatic 1168	com/ironsource/environment/DeviceStatus:getDeviceDensity	()F
    //   797: invokestatic 1170	java/lang/String:valueOf	(F)Ljava/lang/String;
    //   800: astore 10
    //   802: iload 5
    //   804: istore 4
    //   806: aload 10
    //   808: invokestatic 864	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   811: ifne +24 -> 835
    //   814: iload 5
    //   816: istore 4
    //   818: aload 9
    //   820: ldc_w 1172
    //   823: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   826: aload 10
    //   828: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   831: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   834: pop
    //   835: iload 5
    //   837: istore 4
    //   839: invokestatic 1175	com/ironsource/environment/DeviceStatus:isRootedDevice	()Z
    //   842: invokestatic 1178	java/lang/String:valueOf	(Z)Ljava/lang/String;
    //   845: astore 10
    //   847: iload 5
    //   849: istore 4
    //   851: aload 10
    //   853: invokestatic 864	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   856: ifne +24 -> 880
    //   859: iload 5
    //   861: istore 4
    //   863: aload 9
    //   865: ldc_w 1180
    //   868: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   871: aload 10
    //   873: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   876: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   879: pop
    //   880: iload 5
    //   882: istore 4
    //   884: aload_1
    //   885: invokestatic 1027	com/ironsource/sdk/utils/DeviceProperties:getInstance	(Landroid/content/Context;)Lcom/ironsource/sdk/utils/DeviceProperties;
    //   888: aload_1
    //   889: invokevirtual 1184	com/ironsource/sdk/utils/DeviceProperties:getDeviceVolume	(Landroid/content/Context;)F
    //   892: fstore_2
    //   893: iload 5
    //   895: istore 4
    //   897: aload 10
    //   899: invokestatic 864	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   902: ifne +21 -> 923
    //   905: iload 5
    //   907: istore 4
    //   909: aload 9
    //   911: ldc_w 1186
    //   914: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   917: fload_2
    //   918: f2d
    //   919: invokevirtual 1189	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   922: pop
    //   923: iload 5
    //   925: istore 4
    //   927: aload_0
    //   928: invokevirtual 1032	com/ironsource/sdk/controller/IronSourceWebView:getCurrentActivityContext	()Landroid/content/Context;
    //   931: astore_1
    //   932: iload 5
    //   934: istore 4
    //   936: getstatic 1194	android/os/Build$VERSION:SDK_INT	I
    //   939: bipush 19
    //   941: if_icmplt +37 -> 978
    //   944: iload 5
    //   946: istore 4
    //   948: aload_1
    //   949: instanceof 1196
    //   952: ifeq +26 -> 978
    //   955: iload 5
    //   957: istore 4
    //   959: aload 9
    //   961: ldc_w 1198
    //   964: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   967: aload_1
    //   968: checkcast 1196	android/app/Activity
    //   971: invokestatic 1202	com/ironsource/environment/DeviceStatus:isImmersiveSupported	(Landroid/app/Activity;)Z
    //   974: invokevirtual 1017	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   977: pop
    //   978: iload 5
    //   980: istore 4
    //   982: aload 9
    //   984: ldc_w 1204
    //   987: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   990: aload_1
    //   991: invokestatic 1207	com/ironsource/environment/DeviceStatus:getBatteryLevel	(Landroid/content/Context;)I
    //   994: invokevirtual 1210	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   997: pop
    //   998: iload 5
    //   1000: istore 4
    //   1002: aload 9
    //   1004: ldc_w 1212
    //   1007: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1010: aload_1
    //   1011: invokestatic 1215	com/ironsource/environment/ConnectivityService:getNetworkMCC	(Landroid/content/Context;)I
    //   1014: invokevirtual 1210	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1017: pop
    //   1018: iload 5
    //   1020: istore 4
    //   1022: aload 9
    //   1024: ldc_w 1217
    //   1027: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1030: aload_1
    //   1031: invokestatic 1220	com/ironsource/environment/ConnectivityService:getNetworkMNC	(Landroid/content/Context;)I
    //   1034: invokevirtual 1210	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1037: pop
    //   1038: iload 5
    //   1040: istore 4
    //   1042: aload 9
    //   1044: ldc_w 1222
    //   1047: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1050: aload_1
    //   1051: invokestatic 1225	com/ironsource/environment/ConnectivityService:getPhoneType	(Landroid/content/Context;)I
    //   1054: invokevirtual 1210	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1057: pop
    //   1058: iload 5
    //   1060: istore 4
    //   1062: aload 9
    //   1064: ldc_w 1227
    //   1067: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1070: aload_1
    //   1071: invokestatic 1230	com/ironsource/environment/ConnectivityService:getSimOperator	(Landroid/content/Context;)Ljava/lang/String;
    //   1074: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1077: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1080: pop
    //   1081: iload 5
    //   1083: istore 4
    //   1085: aload 9
    //   1087: ldc_w 1232
    //   1090: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1093: aload_1
    //   1094: invokestatic 1236	com/ironsource/environment/ApplicationContext:getLastUpdateTime	(Landroid/content/Context;)J
    //   1097: invokevirtual 1239	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   1100: pop
    //   1101: iload 5
    //   1103: istore 4
    //   1105: aload 9
    //   1107: ldc_w 1241
    //   1110: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1113: aload_1
    //   1114: invokestatic 1244	com/ironsource/environment/ApplicationContext:getFirstInstallTime	(Landroid/content/Context;)J
    //   1117: invokevirtual 1239	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   1120: pop
    //   1121: iload 5
    //   1123: istore 4
    //   1125: aload 9
    //   1127: ldc_w 1246
    //   1130: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1133: aload_1
    //   1134: invokestatic 1249	com/ironsource/environment/ApplicationContext:getApplicationVersionName	(Landroid/content/Context;)Ljava/lang/String;
    //   1137: invokestatic 916	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1140: invokevirtual 949	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1143: pop
    //   1144: iload 5
    //   1146: istore 4
    //   1148: iconst_2
    //   1149: anewarray 300	java/lang/Object
    //   1152: dup
    //   1153: iconst_0
    //   1154: aload 9
    //   1156: invokevirtual 936	org/json/JSONObject:toString	()Ljava/lang/String;
    //   1159: aastore
    //   1160: dup
    //   1161: iconst_1
    //   1162: iload 4
    //   1164: invokestatic 983	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1167: aastore
    //   1168: areturn
    //   1169: iconst_1
    //   1170: istore 5
    //   1172: goto -1077 -> 95
    //   1175: iconst_1
    //   1176: istore 5
    //   1178: goto -927 -> 251
    //   1181: iconst_1
    //   1182: istore 5
    //   1184: goto -882 -> 302
    //   1187: iconst_1
    //   1188: istore 5
    //   1190: goto -851 -> 339
    //   1193: iconst_1
    //   1194: istore 5
    //   1196: goto -728 -> 468
    //   1199: iconst_1
    //   1200: istore 5
    //   1202: goto -632 -> 570
    //   1205: iconst_1
    //   1206: istore 5
    //   1208: goto -544 -> 664
    //   1211: astore_1
    //   1212: iconst_0
    //   1213: istore 4
    //   1215: aload_1
    //   1216: invokevirtual 962	org/json/JSONException:printStackTrace	()V
    //   1219: new 964	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   1222: dup
    //   1223: invokespecial 965	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   1226: iconst_1
    //   1227: anewarray 908	java/lang/String
    //   1230: dup
    //   1231: iconst_0
    //   1232: new 925	java/lang/StringBuilder
    //   1235: dup
    //   1236: invokespecial 926	java/lang/StringBuilder:<init>	()V
    //   1239: ldc_w 1251
    //   1242: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1245: aload_1
    //   1246: invokevirtual 1255	org/json/JSONException:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   1249: iconst_0
    //   1250: aaload
    //   1251: invokevirtual 1260	java/lang/StackTraceElement:getMethodName	()Ljava/lang/String;
    //   1254: invokevirtual 932	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1257: invokevirtual 935	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1260: aastore
    //   1261: invokevirtual 971	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   1264: pop
    //   1265: goto -117 -> 1148
    //   1268: astore_1
    //   1269: goto -54 -> 1215
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1272	0	this	IronSourceWebView
    //   0	1272	1	paramContext	Context
    //   892	26	2	f	float
    //   671	63	3	i	int
    //   97	1117	4	bool1	boolean
    //   93	1114	5	bool2	boolean
    //   119	38	6	bool3	boolean
    //   544	15	7	l	long
    //   13	1142	9	localJSONObject	JSONObject
    //   4	894	10	localObject1	Object
    //   39	612	11	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   15	41	1211	org/json/JSONException
    //   46	63	1211	org/json/JSONException
    //   63	70	1211	org/json/JSONException
    //   75	92	1211	org/json/JSONException
    //   99	103	1268	org/json/JSONException
    //   107	112	1268	org/json/JSONException
    //   116	121	1268	org/json/JSONException
    //   125	133	1268	org/json/JSONException
    //   137	147	1268	org/json/JSONException
    //   151	165	1268	org/json/JSONException
    //   169	214	1268	org/json/JSONException
    //   218	225	1268	org/json/JSONException
    //   234	251	1268	org/json/JSONException
    //   255	262	1268	org/json/JSONException
    //   271	284	1268	org/json/JSONException
    //   288	302	1268	org/json/JSONException
    //   306	316	1268	org/json/JSONException
    //   325	339	1268	org/json/JSONException
    //   343	348	1268	org/json/JSONException
    //   357	374	1268	org/json/JSONException
    //   378	386	1268	org/json/JSONException
    //   390	401	1268	org/json/JSONException
    //   405	425	1268	org/json/JSONException
    //   429	435	1268	org/json/JSONException
    //   439	447	1268	org/json/JSONException
    //   451	468	1268	org/json/JSONException
    //   472	487	1268	org/json/JSONException
    //   491	499	1268	org/json/JSONException
    //   503	523	1268	org/json/JSONException
    //   527	533	1268	org/json/JSONException
    //   537	546	1268	org/json/JSONException
    //   550	570	1268	org/json/JSONException
    //   574	582	1268	org/json/JSONException
    //   586	594	1268	org/json/JSONException
    //   598	607	1268	org/json/JSONException
    //   611	644	1268	org/json/JSONException
    //   648	664	1268	org/json/JSONException
    //   668	672	1268	org/json/JSONException
    //   676	685	1268	org/json/JSONException
    //   689	722	1268	org/json/JSONException
    //   726	744	1268	org/json/JSONException
    //   748	757	1268	org/json/JSONException
    //   761	769	1268	org/json/JSONException
    //   773	790	1268	org/json/JSONException
    //   794	802	1268	org/json/JSONException
    //   806	814	1268	org/json/JSONException
    //   818	835	1268	org/json/JSONException
    //   839	847	1268	org/json/JSONException
    //   851	859	1268	org/json/JSONException
    //   863	880	1268	org/json/JSONException
    //   884	893	1268	org/json/JSONException
    //   897	905	1268	org/json/JSONException
    //   909	923	1268	org/json/JSONException
    //   927	932	1268	org/json/JSONException
    //   936	944	1268	org/json/JSONException
    //   948	955	1268	org/json/JSONException
    //   959	978	1268	org/json/JSONException
    //   982	998	1268	org/json/JSONException
    //   1002	1018	1268	org/json/JSONException
    //   1022	1038	1268	org/json/JSONException
    //   1042	1058	1268	org/json/JSONException
    //   1062	1081	1268	org/json/JSONException
    //   1085	1101	1268	org/json/JSONException
    //   1105	1121	1268	org/json/JSONException
    //   1125	1144	1268	org/json/JSONException
  }
  
  private Map<String, String> getExtraParamsByProduct(SSAEnums.ProductType paramProductType)
  {
    if (paramProductType == SSAEnums.ProductType.OfferWall) {
      return this.mOWExtraParameters;
    }
    return null;
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
  
  private SSAEnums.ProductType getStringProductTypeAsEnum(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
        return SSAEnums.ProductType.Interstitial;
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
        return SSAEnums.ProductType.RewardedVideo;
      }
    } while (!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()));
    return SSAEnums.ProductType.OfferWall;
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
  
  private void initProduct(String paramString1, String paramString2, SSAEnums.ProductType paramProductType, DemandSource paramDemandSource, String paramString3)
  {
    if ((TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString1))) {
      triggerOnControllerInitProductFail("User id or Application key are missing", paramProductType, paramDemandSource.getDemandSourceName());
    }
    do
    {
      return;
      if (this.mControllerState == SSAEnums.ControllerState.Ready)
      {
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setApplicationKey(paramString1, paramProductType);
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUserID(paramString2, paramProductType);
        injectJavascript(createInitProductJSMethod(paramProductType, paramDemandSource));
        return;
      }
      setMissProduct(paramProductType, paramDemandSource);
      if (this.mControllerState == SSAEnums.ControllerState.Failed)
      {
        triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(paramString3, "Initiating Controller"), paramProductType, paramDemandSource.getDemandSourceName());
        return;
      }
    } while (!this.mGlobalControllerTimeFinish);
    downloadController();
  }
  
  private void injectJavascript(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
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
              IronSourceWebView.access$6702(IronSourceWebView.this, Boolean.valueOf(true));
              return;
            }
            catch (NoSuchMethodError localNoSuchMethodError)
            {
              Logger.e(IronSourceWebView.this.TAG, "evaluateJavascrip NoSuchMethodError: SDK version=" + Build.VERSION.SDK_INT + " " + localNoSuchMethodError);
              IronSourceWebView.this.loadUrl(this.val$url);
              IronSourceWebView.access$6702(IronSourceWebView.this, Boolean.valueOf(false));
              return;
            }
            catch (Throwable localThrowable2)
            {
              Logger.e(IronSourceWebView.this.TAG, "evaluateJavascrip Exception: SDK version=" + Build.VERSION.SDK_INT + " " + localThrowable2);
              IronSourceWebView.this.loadUrl(this.val$url);
              IronSourceWebView.access$6702(IronSourceWebView.this, Boolean.valueOf(false));
              return;
            }
          }
          IronSourceWebView.this.loadUrl(this.val$url);
          IronSourceWebView.access$6702(IronSourceWebView.this, Boolean.valueOf(false));
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
  
  private void injectJavascript(String paramString1, String paramString2)
  {
    if ((!isControllerStateReady()) && (controllerCommandSupportsQueue(paramString1)))
    {
      this.mControllerCommandsQueue.add(paramString2);
      return;
    }
    injectJavascript(paramString2);
  }
  
  private void invokePendingCommands()
  {
    while (this.mControllerCommandsQueue.size() > 0)
    {
      injectJavascript((String)this.mControllerCommandsQueue.get(0));
      this.mControllerCommandsQueue.remove(0);
    }
  }
  
  private boolean isControllerStateReady()
  {
    return SSAEnums.ControllerState.Ready.equals(this.mControllerState);
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
      if (this.mDSInterstitialListener == null) {}
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
        if (this.mDSRewardedVideoListener != null) {}
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
          if ((SSAEnums.ProductType.RewardedVideo == paramProductType) || (SSAEnums.ProductType.Interstitial == paramProductType)) {
            if (!TextUtils.isEmpty(paramString2)) {}
          }
          do
          {
            DSAdProductListener localDSAdProductListener;
            do
            {
              return;
              localDSAdProductListener = IronSourceWebView.this.getAdProductListenerByProductType(paramProductType);
              Log.d(IronSourceWebView.this.TAG, "onAdProductInitFailed (message:" + paramString1 + ")(" + paramProductType + ")");
            } while (localDSAdProductListener == null);
            localDSAdProductListener.onAdProductInitFailed(paramProductType, paramString2, paramString1);
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
  
  public void addPermissionsJSInterface(PermissionsJSAdapter paramPermissionsJSAdapter)
  {
    this.mPermissionsJsAdapter = paramPermissionsJSAdapter;
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
    this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.OfferWallCredits, paramString1, paramString2);
    this.mOnOfferWallListener = paramOnOfferWallListener;
    initProduct(this.mOWCreditsAppKey, this.mOWCreditsUserId, SSAEnums.ProductType.OfferWallCredits, null, "Show OW Credits");
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
  
  public void initInterstitial(String paramString1, String paramString2, DemandSource paramDemandSource, DSInterstitialListener paramDSInterstitialListener)
  {
    this.mISAppKey = paramString1;
    this.mISUserId = paramString2;
    this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.Interstitial, paramString1, paramString2);
    this.mDSInterstitialListener = paramDSInterstitialListener;
    this.mSavedState.setInterstitialAppKey(this.mISAppKey);
    this.mSavedState.setInterstitialUserId(this.mISUserId);
    initProduct(this.mISAppKey, this.mISUserId, SSAEnums.ProductType.Interstitial, paramDemandSource, "Init IS");
  }
  
  public void initOfferWall(String paramString1, String paramString2, Map<String, String> paramMap, OnOfferWallListener paramOnOfferWallListener)
  {
    this.mOWAppKey = paramString1;
    this.mOWUserId = paramString2;
    this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.OfferWall, paramString1, paramString2);
    this.mOWExtraParameters = paramMap;
    this.mOnOfferWallListener = paramOnOfferWallListener;
    this.mSavedState.setOfferWallExtraParams(this.mOWExtraParameters);
    this.mSavedState.setOfferwallReportInit(true);
    initProduct(this.mOWAppKey, this.mOWUserId, SSAEnums.ProductType.OfferWall, null, "Init OW");
  }
  
  public void initRewardedVideo(String paramString1, String paramString2, DemandSource paramDemandSource, DSRewardedVideoListener paramDSRewardedVideoListener)
  {
    this.mRVAppKey = paramString1;
    this.mRVUserId = paramString2;
    this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.RewardedVideo, paramString1, paramString2);
    this.mDSRewardedVideoListener = paramDSRewardedVideoListener;
    this.mSavedState.setRVAppKey(paramString1);
    this.mSavedState.setRVUserId(paramString2);
    initProduct(paramString1, paramString2, SSAEnums.ProductType.RewardedVideo, paramDemandSource, "Init RV");
  }
  
  String initializeCacheDirectory(Context paramContext)
  {
    return IronSourceStorageUtils.initializeCacheDirectory(paramContext.getApplicationContext());
  }
  
  public void interceptedUrlToStore()
  {
    injectJavascript(generateJSToInject("interceptedUrlToStore"));
  }
  
  public boolean isInterstitialAdAvailable(String paramString)
  {
    paramString = this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.Interstitial, paramString);
    return (paramString != null) && (paramString.getAvailabilityState());
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
              DemandSource localDemandSource;
              while (localIterator.hasNext())
              {
                localDemandSource = (DemandSource)localIterator.next();
                if (localDemandSource.getDemandSourceInitState() == 1) {
                  IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, localDemandSource.getDemandSourceName());
                }
              }
              localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial).iterator();
              while (localIterator.hasNext())
              {
                localDemandSource = (DemandSource)localIterator.next();
                if (localDemandSource.getDemandSourceInitState() == 1) {
                  IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial, localDemandSource.getDemandSourceName());
                }
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
  
  public void loadInterstitial(final String paramString)
  {
    Object localObject = new HashMap();
    if (!TextUtils.isEmpty(paramString)) {
      ((Map)localObject).put("demandSourceName", paramString);
    }
    localObject = flatMapToJsonAsString((Map)localObject);
    if (!isInterstitialAdAvailable(paramString))
    {
      this.mSavedState.setReportLoadInterstitial(paramString, true);
      injectJavascript(generateJSToInject("loadInterstitial", (String)localObject, "onLoadInterstitialSuccess", "onLoadInterstitialFail"));
    }
    while (!shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
      return;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadSuccess(paramString);
      }
    });
  }
  
  public void nativeNavigationPressed(String paramString)
  {
    injectJavascript(generateJSToInject("nativeNavigationPressed", parseToJson("action", paramString, null, null, null, null, null, null, null, false)));
  }
  
  public void notifyLifeCycle(String paramString1, String paramString2)
  {
    injectJavascript(generateJSToInject("onNativeLifeCycleEvent", parseToJson("lifeCycleEvent", paramString2, "productType", paramString1, null, null, null, null, null, false)));
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
      DemandSource localDemandSource;
      while (paramSSAFile.hasNext())
      {
        localDemandSource = (DemandSource)paramSSAFile.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, localDemandSource.getDemandSourceName());
        }
      }
      paramSSAFile = this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial).iterator();
      while (paramSSAFile.hasNext())
      {
        localDemandSource = (DemandSource)paramSSAFile.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          sendProductErrorMessage(SSAEnums.ProductType.Interstitial, localDemandSource.getDemandSourceName());
        }
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
    DemandSource localDemandSource;
    for (;;)
    {
      int i;
      synchronized (this.mSavedStateLocker)
      {
        if ((!paramAdUnitsState.shouldRestore()) || (!this.mControllerState.equals(SSAEnums.ControllerState.Ready))) {
          break label576;
        }
        Log.d(this.TAG, "restoreState(state:" + paramAdUnitsState + ")");
        i = paramAdUnitsState.getDisplayedProduct();
        if (i == -1) {
          break label394;
        }
        if (i == SSAEnums.ProductType.RewardedVideo.ordinal())
        {
          Log.d(this.TAG, "onRVAdClosed()");
          localObject2 = SSAEnums.ProductType.RewardedVideo;
          str1 = paramAdUnitsState.getDisplayedDemandSourceName();
          localObject3 = getAdProductListenerByProductType((SSAEnums.ProductType)localObject2);
          if ((localObject3 != null) && (!TextUtils.isEmpty(str1))) {
            ((DSAdProductListener)localObject3).onAdProductClose((SSAEnums.ProductType)localObject2, str1);
          }
          paramAdUnitsState.adOpened(-1);
          paramAdUnitsState.setDisplayedDemandSourceName(null);
          localObject2 = paramAdUnitsState.getInterstitialAppKey();
          str1 = paramAdUnitsState.getInterstitialUserId();
          localObject3 = this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial).iterator();
          if (!((Iterator)localObject3).hasNext()) {
            break;
          }
          localDemandSource = (DemandSource)((Iterator)localObject3).next();
          if (localDemandSource.getDemandSourceInitState() != 2) {
            continue;
          }
          Log.d(this.TAG, "initInterstitial(appKey:" + (String)localObject2 + ", userId:" + str1 + ", demandSource:" + localDemandSource.getDemandSourceName() + ")");
          initInterstitial((String)localObject2, str1, localDemandSource, this.mDSInterstitialListener);
        }
      }
      if (i == SSAEnums.ProductType.Interstitial.ordinal())
      {
        Log.d(this.TAG, "onInterstitialAdClosed()");
        localObject2 = SSAEnums.ProductType.Interstitial;
        str1 = paramAdUnitsState.getDisplayedDemandSourceName();
        localObject3 = getAdProductListenerByProductType((SSAEnums.ProductType)localObject2);
        if ((localObject3 != null) && (!TextUtils.isEmpty(str1))) {
          ((DSAdProductListener)localObject3).onAdProductClose((SSAEnums.ProductType)localObject2, str1);
        }
      }
      else if (i == SSAEnums.ProductType.OfferWall.ordinal())
      {
        Log.d(this.TAG, "onOWAdClosed()");
        if (this.mOnOfferWallListener != null)
        {
          this.mOnOfferWallListener.onOWAdClosed();
          continue;
          label394:
          Log.d(this.TAG, "No ad was opened");
        }
      }
    }
    Object localObject2 = paramAdUnitsState.getRVAppKey();
    String str1 = paramAdUnitsState.getRVUserId();
    Object localObject3 = this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localDemandSource = (DemandSource)((Iterator)localObject3).next();
      if (localDemandSource.getDemandSourceInitState() == 2)
      {
        String str2 = localDemandSource.getDemandSourceName();
        Log.d(this.TAG, "onRVNoMoreOffers()");
        this.mDSRewardedVideoListener.onRVNoMoreOffers(str2);
        Log.d(this.TAG, "initRewardedVideo(appKey:" + (String)localObject2 + ", userId:" + str1 + ", demandSource:" + str2 + ")");
        initRewardedVideo((String)localObject2, str1, localDemandSource, this.mDSRewardedVideoListener);
      }
    }
    paramAdUnitsState.setShouldRestore(false);
    label576:
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
  
  void setMissProduct(SSAEnums.ProductType paramProductType, DemandSource paramDemandSource)
  {
    if ((paramProductType == SSAEnums.ProductType.RewardedVideo) || (paramProductType == SSAEnums.ProductType.Interstitial)) {
      if (paramDemandSource != null) {
        paramDemandSource.setDemandSourceInitState(1);
      }
    }
    for (;;)
    {
      Logger.i(this.TAG, "setMissProduct(" + paramProductType + ")");
      return;
      if (paramProductType == SSAEnums.ProductType.OfferWall) {
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
    injectJavascript(createShowProductJSMethod(SSAEnums.ProductType.Interstitial, paramJSONObject));
  }
  
  public void showOfferWall(Map<String, String> paramMap)
  {
    this.mOWExtraParameters = paramMap;
    injectJavascript(generateJSToInject("showOfferWall", "onShowOfferWallSuccess", "onShowOfferWallFail"));
  }
  
  public void showRewardedVideo(JSONObject paramJSONObject)
  {
    injectJavascript(createShowProductJSMethod(SSAEnums.ProductType.RewardedVideo, paramJSONObject));
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
  
  public void updateConsentInfo(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {}
    for (paramJSONObject = paramJSONObject.toString();; paramJSONObject = null)
    {
      injectJavascript("updateConsentInfo", generateJSToInject("updateConsentInfo", paramJSONObject));
      return;
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
    
    private void setInterstitialAvailability(String paramString, boolean paramBoolean)
    {
      DemandSource localDemandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.Interstitial, paramString);
      if (localDemandSource != null) {
        localDemandSource.setAvailabilityState(paramBoolean);
      }
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.toastingErrMsg("onInterstitialAvailability", String.valueOf(paramBoolean + " with demand " + paramString));
      }
    }
    
    @JavascriptInterface
    public void adClicked(final String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "adClicked(" + paramString + ")");
      paramString = new SSAObj(paramString);
      Object localObject = paramString.getString("productType");
      paramString = paramString.getString("demandSourceName");
      if (TextUtils.isEmpty(paramString)) {}
      final DSAdProductListener localDSAdProductListener;
      do
      {
        return;
        localObject = IronSourceWebView.this.getStringProductTypeAsEnum((String)localObject);
        localDSAdProductListener = IronSourceWebView.this.getAdProductListenerByProductType((SSAEnums.ProductType)localObject);
      } while ((localObject == null) || (localDSAdProductListener == null));
      IronSourceWebView.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          localDSAdProductListener.onAdProductClick(this.val$eProductType, paramString);
        }
      });
    }
    
    @JavascriptInterface
    public void adCredited(final String paramString)
    {
      final String str3 = null;
      final boolean bool1 = false;
      Log.d(IronSourceWebView.this.PUB_TAG, "adCredited(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      final String str1 = localSSAObj.getString("credits");
      final int i;
      String str6;
      final int j;
      label91:
      final String str4;
      final String str5;
      final String str2;
      if (str1 != null)
      {
        i = Integer.parseInt(str1);
        str6 = localSSAObj.getString("total");
        if (str6 == null) {
          break label202;
        }
        j = Integer.parseInt(str6);
        str4 = localSSAObj.getString("demandSourceName");
        str5 = localSSAObj.getString("productType");
        if (!localSSAObj.getBoolean("externalPoll")) {
          break label207;
        }
        str1 = IronSourceWebView.this.mOWCreditsAppKey;
        str2 = IronSourceWebView.this.mOWCreditsUserId;
        label137:
        if (!str5.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
          break label353;
        }
        if ((!localSSAObj.isNull("signature")) && (!localSSAObj.isNull("timestamp")) && (!localSSAObj.isNull("totalCreditsFlag"))) {
          break label228;
        }
        IronSourceWebView.this.responseBack(paramString, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
      }
      label202:
      label207:
      label228:
      label353:
      label360:
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
        final boolean bool2;
        if (localSSAObj.getString("signature").equalsIgnoreCase(SDKUtils.getMD5(str6 + str1 + str2)))
        {
          bool1 = true;
          bool2 = localSSAObj.getBoolean("totalCreditsFlag");
          str3 = localSSAObj.getString("timestamp");
        }
        for (;;)
        {
          if (!IronSourceWebView.this.shouldNotifyDeveloper(str5)) {
            break label360;
          }
          IronSourceWebView.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              if (str5.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
                IronSourceWebView.this.mDSRewardedVideoListener.onRVAdCredited(str4, i);
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
          IronSourceWebView.this.responseBack(paramString, false, "Controller signature is not equal to SDK signature", null);
          break;
          bool1 = false;
          bool2 = false;
        }
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
          int i = Integer.parseInt(localAdUnitsReady.getNumOfAdUnits());
          if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()))
          {
            if (i > 0)
            {
              Log.d(IronSourceWebView.this.TAG, "onRVInitSuccess()");
              IronSourceWebView.this.mDSRewardedVideoListener.onAdProductInitSuccess(SSAEnums.ProductType.RewardedVideo, str, localAdUnitsReady);
            }
          }
          else {
            return;
          }
          IronSourceWebView.this.mDSRewardedVideoListener.onRVNoMoreOffers(str);
        }
      });
    }
    
    @JavascriptInterface
    public String addTesterParametersToConfig(String paramString1, String paramString2)
    {
      paramString1 = new JSONObject(paramString1);
      paramString2 = new JSONObject(paramString2);
      paramString1.putOpt("testerABGroup", paramString2.get("testerABGroup"));
      paramString1.putOpt("testFriendlyName", paramString2.get("testFriendlyName"));
      return paramString1.toString();
    }
    
    @JavascriptInterface
    public void alert(String paramString) {}
    
    @JavascriptInterface
    public boolean areTesterParametersValid(String paramString)
    {
      if ((!TextUtils.isEmpty(paramString)) && (!paramString.contains("-1"))) {
        try
        {
          paramString = new JSONObject(paramString);
          if (!paramString.getString("testerABGroup").isEmpty())
          {
            boolean bool = paramString.getString("testFriendlyName").isEmpty();
            if (!bool) {
              return true;
            }
          }
        }
        catch (JSONException paramString)
        {
          paramString.printStackTrace();
        }
      }
      return false;
    }
    
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
          break label167;
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
          label167:
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
        IronSourceWebView.access$4802(IronSourceWebView.this, ((SSAObj)localObject).getBoolean("immersive"));
        IronSourceWebView.access$4902(IronSourceWebView.this, ((SSAObj)localObject).getBoolean("activityThemeTranslucent"));
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
              break label470;
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
          IronSourceWebView.this.mDSRewardedVideoListener.onAdProductOpen(SSAEnums.ProductType.RewardedVideo, str2);
        }
        ((Intent)localObject).setFlags(536870912);
        ((Intent)localObject).putExtra("immersive", IronSourceWebView.this.mIsImmersive);
        ((Intent)localObject).putExtra("orientation_set_flag", paramString);
        ((Intent)localObject).putExtra("rotation_set_flag", j);
        localContext.startActivity((Intent)localObject);
        return;
        localObject = new Intent(localContext, ControllerActivity.class);
        break;
        label470:
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
      String str2 = new SSAObj(paramString).getString(IronSourceWebView.JSON_KEY_SUCCESS);
      String str1;
      String str3;
      if (!TextUtils.isEmpty(str2))
      {
        str1 = SDKUtils.getControllerConfig();
        str3 = SDKUtils.getTesterParameters();
        paramString = str1;
        if (!areTesterParametersValid(str3)) {}
      }
      try
      {
        paramString = addTesterParametersToConfig(str1, str3);
        paramString = IronSourceWebView.this.generateJSToInject(str2, paramString);
        IronSourceWebView.this.injectJavascript(paramString);
        return;
      }
      catch (JSONException paramString)
      {
        for (;;)
        {
          Logger.d(IronSourceWebView.this.TAG, "getControllerConfig Error while parsing Tester AB Group parameters");
          paramString = str1;
        }
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
    public void getDeviceLocation(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getDeviceLocation(" + paramString + ")");
      try
      {
        paramString = IronSourceWebView.this.createLocationObject(paramString, LocationService.getLastLocation(IronSourceWebView.this.getContext()));
        IronSourceWebView.this.responseBack(paramString.toString(), true, null, null);
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    
    @JavascriptInterface
    public void getDevicePreciseLocation(final String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getDevicePreciseLocation(" + paramString + ")");
      try
      {
        LocationService.getPreciseLocation(IronSourceWebView.this.getContext(), new LocationService.ISLocationListener()
        {
          public void onLocationChanged(Location paramAnonymousLocation)
          {
            paramAnonymousLocation = IronSourceWebView.this.createLocationObject(paramString, paramAnonymousLocation);
            IronSourceWebView.this.responseBack(paramAnonymousLocation.toString(), true, null, null);
          }
        });
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
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
      DemandSource localDemandSource;
      while (localIterator.hasNext())
      {
        localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, localDemandSource.getDemandSourceName());
        }
      }
      localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial).iterator();
      while (localIterator.hasNext())
      {
        localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial, localDemandSource.getDemandSourceName());
        }
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
      IronSourceWebView.this.invokePendingCommands();
      Iterator localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo).iterator();
      DemandSource localDemandSource;
      while (localIterator.hasNext())
      {
        localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.initRewardedVideo(IronSourceWebView.this.mRVAppKey, IronSourceWebView.this.mRVUserId, localDemandSource, IronSourceWebView.this.mDSRewardedVideoListener);
        }
      }
      localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial).iterator();
      while (localIterator.hasNext())
      {
        localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.initInterstitial(IronSourceWebView.this.mISAppKey, IronSourceWebView.this.mISUserId, localDemandSource, IronSourceWebView.this.mDSInterstitialListener);
        }
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
    public void locationServicesEnabled(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "locationServicesEnabled(" + paramString + ")");
      try
      {
        boolean bool = LocationService.locationServicesEnabled(IronSourceWebView.this.getContext());
        paramString = new SSAObj(paramString);
        paramString.put("status", String.valueOf(bool));
        IronSourceWebView.this.responseBack(paramString.toString(), true, null, null);
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
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
    public void onAdWindowsClosed(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onAdWindowsClosed(" + paramString + ")");
      IronSourceWebView.this.mSavedState.adClosed();
      IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(null);
      Object localObject = new SSAObj(paramString);
      paramString = ((SSAObj)localObject).getString("productType");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      final SSAEnums.ProductType localProductType = IronSourceWebView.this.getStringProductTypeAsEnum(paramString);
      Log.d(IronSourceWebView.this.PUB_TAG, "onAdClosed() with type " + localProductType);
      if ((IronSourceWebView.this.shouldNotifyDeveloper(paramString)) && (paramString != null)) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if ((localProductType == SSAEnums.ProductType.RewardedVideo) || (localProductType == SSAEnums.ProductType.Interstitial))
            {
              localDSAdProductListener = IronSourceWebView.this.getAdProductListenerByProductType(localProductType);
              if (localDSAdProductListener != null) {
                localDSAdProductListener.onAdProductClose(localProductType, this.val$demandSourceName);
              }
            }
            while (localProductType != SSAEnums.ProductType.OfferWall)
            {
              DSAdProductListener localDSAdProductListener;
              return;
            }
            IronSourceWebView.this.mOnOfferWallListener.onOWAdClosed();
          }
        });
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
      Object localObject = new SSAObj(paramString);
      final String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      if (TextUtils.isEmpty((CharSequence)localObject))
      {
        Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess failed with no demand source");
        return;
      }
      DemandSource localDemandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.Interstitial, (String)localObject);
      if (localDemandSource != null) {
        localDemandSource.setDemandSourceInitState(3);
      }
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
            IronSourceWebView.this.mDSInterstitialListener.onAdProductInitFailed(SSAEnums.ProductType.Interstitial, this.val$demandSourceName, str1);
          }
        });
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onInitInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitInterstitialSuccess(final String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess()");
      IronSourceWebView.this.toastingErrMsg("onInitInterstitialSuccess", "true");
      paramString = new SSAObj(paramString).getString("demandSourceName");
      if (TextUtils.isEmpty(paramString)) {
        Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess failed with no demand source");
      }
      while (!IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        return;
      }
      IronSourceWebView.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          Log.d(IronSourceWebView.this.TAG, "onInterstitialInitSuccess()");
          IronSourceWebView.this.mDSInterstitialListener.onAdProductInitSuccess(SSAEnums.ProductType.Interstitial, paramString, null);
        }
      });
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
            Log.d(IronSourceWebView.this.TAG, "onRVInitFail(message:" + str1 + ")");
            IronSourceWebView.this.mDSRewardedVideoListener.onAdProductInitFailed(SSAEnums.ProductType.RewardedVideo, this.val$demandSourceName, str1);
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
      Object localObject = new SSAObj(paramString);
      final String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (TextUtils.isEmpty((CharSequence)localObject)) {
        return;
      }
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
            IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadFailed(this.val$demandSourceName, str1);
          }
        });
      }
      IronSourceWebView.this.toastingErrMsg("onLoadInterstitialFail", "true");
    }
    
    @JavascriptInterface
    public void onLoadInterstitialSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onLoadInterstitialSuccess(" + paramString + ")");
      final String str = new SSAObj(paramString).getString("demandSourceName");
      setInterstitialAvailability(str, true);
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadSuccess(str);
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
      Object localObject = new SSAObj(paramString);
      final String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (TextUtils.isEmpty((CharSequence)localObject)) {
        return;
      }
      setInterstitialAvailability((String)localObject, false);
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
            IronSourceWebView.this.mDSInterstitialListener.onInterstitialShowFailed(this.val$demandSourceName, str1);
          }
        });
      }
      IronSourceWebView.this.toastingErrMsg("onShowInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowInterstitialSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialSuccess(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      final String str = new SSAObj(paramString).getString("demandSourceName");
      if (TextUtils.isEmpty(str))
      {
        Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialSuccess called with no demand");
        return;
      }
      IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.Interstitial.ordinal());
      IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(str);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            IronSourceWebView.this.mDSInterstitialListener.onAdProductOpen(SSAEnums.ProductType.Interstitial, str);
            IronSourceWebView.this.mDSInterstitialListener.onInterstitialShowSuccess(str);
          }
        });
        IronSourceWebView.this.toastingErrMsg("onShowInterstitialSuccess", paramString);
      }
      setInterstitialAvailability(str, false);
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
            IronSourceWebView.this.mDSRewardedVideoListener.onRVShowFail(this.val$demandSourceName, str1);
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
    public void permissionsAPI(String paramString)
    {
      try
      {
        Logger.i(IronSourceWebView.this.TAG, "permissionsAPI(" + paramString + ")");
        paramString = new SSAObj(paramString);
        IronSourceWebView.this.mPermissionsJsAdapter.call(paramString.toString(), new JSCallbackTask());
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        Logger.i(IronSourceWebView.this.TAG, "permissionsAPI failed with exception " + paramString.getMessage());
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
        String str3 = ((SSAObj)localObject).getString("productType");
        localObject = IronSourceWebView.this.getStringProductTypeAsEnum(str3);
        if (IronSourceWebView.this.shouldNotifyDeveloper(str3))
        {
          paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
          if (!TextUtils.isEmpty(paramString))
          {
            str3 = IronSourceWebView.this.parseToJson("productType", str3, "eventName", str1, "demandSourceName", str2, null, null, null, false);
            paramString = IronSourceWebView.this.generateJSToInject(paramString, str3, "postAdEventNotificationSuccess", "postAdEventNotificationFail");
            IronSourceWebView.this.injectJavascript(paramString);
          }
          IronSourceWebView.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              if ((this.val$type == SSAEnums.ProductType.Interstitial) || (this.val$type == SSAEnums.ProductType.RewardedVideo))
              {
                localDSAdProductListener = IronSourceWebView.this.getAdProductListenerByProductType(this.val$type);
                if (localDSAdProductListener != null) {
                  localDSAdProductListener.onAdProductEventNotificationReceived(this.val$type, str2, str1, localJSONObject);
                }
              }
              while (this.val$type != SSAEnums.ProductType.OfferWall)
              {
                DSAdProductListener localDSAdProductListener;
                return;
              }
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
      
      void sendMessage(boolean paramBoolean, String paramString, SSAObj paramSSAObj)
      {
        if (paramBoolean) {}
        for (String str = IronSourceWebView.JSON_KEY_SUCCESS;; str = IronSourceWebView.JSON_KEY_FAIL)
        {
          paramSSAObj.put(str, paramString);
          IronSourceWebView.this.responseBack(paramSSAObj.toString(), paramBoolean, null, null);
          return;
        }
      }
      
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
      int j;
      int i;
      int k;
      int m;
      if (paramMotionEvent.getAction() == 1)
      {
        f1 = paramMotionEvent.getX();
        f2 = paramMotionEvent.getY();
        Logger.i(IronSourceWebView.this.TAG, "X:" + (int)f1 + " Y:" + (int)f2);
        j = DeviceStatus.getDeviceWidth();
        i = DeviceStatus.getDeviceHeight();
        Logger.i(IronSourceWebView.this.TAG, "Width:" + j + " Height:" + i);
        k = SDKUtils.dpToPx(IronSourceWebView.this.mHiddenForceCloseWidth);
        m = SDKUtils.dpToPx(IronSourceWebView.this.mHiddenForceCloseHeight);
        if (!"top-right".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
          break label233;
        }
        j -= (int)f1;
        i = (int)f2;
      }
      for (;;)
      {
        if ((j <= k) && (i <= m))
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
        label233:
        if ("top-left".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation))
        {
          j = (int)f1;
          i = (int)f2;
        }
        else if ("bottom-right".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation))
        {
          j -= (int)f1;
          i -= (int)f2;
        }
        else if ("bottom-left".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation))
        {
          j = (int)f1;
          i -= (int)f2;
        }
        else
        {
          i = 0;
          j = 0;
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
