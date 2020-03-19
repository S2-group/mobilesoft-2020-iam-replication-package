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
    //   0: new 818	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 859	org/json/JSONObject:<init>	()V
    //   7: astore 9
    //   9: ldc_w 654
    //   12: astore 6
    //   14: ldc_w 654
    //   17: astore 5
    //   19: aconst_null
    //   20: astore 7
    //   22: aconst_null
    //   23: astore 4
    //   25: aload_1
    //   26: invokestatic 842	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   29: ifne +363 -> 392
    //   32: aload_0
    //   33: aload_1
    //   34: invokespecial 578	com/ironsource/sdk/controller/IronSourceWebView:getStringProductTypeAsEnum	(Ljava/lang/String;)Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   37: astore 10
    //   39: aload 10
    //   41: getstatic 660	com/ironsource/sdk/data/SSAEnums$ProductType:RewardedVideo	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   44: if_acmpeq +11 -> 55
    //   47: aload 10
    //   49: getstatic 663	com/ironsource/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   52: if_acmpne +282 -> 334
    //   55: aload_0
    //   56: getfield 310	com/ironsource/sdk/controller/IronSourceWebView:mProductParametersCollection	Lcom/ironsource/sdk/controller/ProductParametersCollection;
    //   59: aload 10
    //   61: invokevirtual 673	com/ironsource/sdk/controller/ProductParametersCollection:getProductParameters	(Lcom/ironsource/sdk/data/SSAEnums$ProductType;)Lcom/ironsource/sdk/data/ProductParameters;
    //   64: astore 5
    //   66: aload 5
    //   68: getfield 680	com/ironsource/sdk/data/ProductParameters:appKey	Ljava/lang/String;
    //   71: astore 7
    //   73: aload 5
    //   75: getfield 691	com/ironsource/sdk/data/ProductParameters:userId	Ljava/lang/String;
    //   78: astore 8
    //   80: aload_0
    //   81: getfield 333	com/ironsource/sdk/controller/IronSourceWebView:mDemandSourceManager	Lcom/ironsource/sdk/controller/DemandSourceManager;
    //   84: aload 10
    //   86: aload_2
    //   87: invokevirtual 836	com/ironsource/sdk/controller/DemandSourceManager:getDemandSourceByName	(Lcom/ironsource/sdk/data/SSAEnums$ProductType;Ljava/lang/String;)Lcom/ironsource/sdk/data/DemandSource;
    //   90: astore 10
    //   92: aload 8
    //   94: astore 5
    //   96: aload 7
    //   98: astore 6
    //   100: aload 10
    //   102: ifnull +30 -> 132
    //   105: aload 10
    //   107: invokevirtual 697	com/ironsource/sdk/data/DemandSource:getExtraParams	()Ljava/util/Map;
    //   110: astore 4
    //   112: aload 4
    //   114: ldc_w 703
    //   117: aload_2
    //   118: invokeinterface 686 3 0
    //   123: pop
    //   124: aload 7
    //   126: astore 6
    //   128: aload 8
    //   130: astore 5
    //   132: aload 9
    //   134: ldc_w 736
    //   137: aload_1
    //   138: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   141: pop
    //   142: iconst_0
    //   143: istore_3
    //   144: aload 5
    //   146: invokestatic 842	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   149: ifne +281 -> 430
    //   152: aload 9
    //   154: ldc_w 688
    //   157: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   160: aload 5
    //   162: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   165: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   168: pop
    //   169: aload 6
    //   171: invokestatic 842	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   174: ifne +290 -> 464
    //   177: aload 9
    //   179: ldc_w 675
    //   182: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   185: aload 6
    //   187: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   190: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   193: pop
    //   194: aload 4
    //   196: ifnull +273 -> 469
    //   199: aload 4
    //   201: invokeinterface 929 1 0
    //   206: ifne +263 -> 469
    //   209: aload 4
    //   211: invokeinterface 863 1 0
    //   216: invokeinterface 869 1 0
    //   221: astore_1
    //   222: aload_1
    //   223: invokeinterface 875 1 0
    //   228: ifeq +241 -> 469
    //   231: aload_1
    //   232: invokeinterface 879 1 0
    //   237: checkcast 881	java/util/Map$Entry
    //   240: astore_2
    //   241: aload_2
    //   242: invokeinterface 884 1 0
    //   247: checkcast 886	java/lang/String
    //   250: ldc_w 931
    //   253: invokevirtual 934	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   256: ifeq +16 -> 272
    //   259: aload_0
    //   260: aload_2
    //   261: invokeinterface 889 1 0
    //   266: checkcast 886	java/lang/String
    //   269: invokespecial 937	com/ironsource/sdk/controller/IronSourceWebView:setWebviewCache	(Ljava/lang/String;)V
    //   272: aload 9
    //   274: aload_2
    //   275: invokeinterface 884 1 0
    //   280: checkcast 886	java/lang/String
    //   283: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   286: aload_2
    //   287: invokeinterface 889 1 0
    //   292: checkcast 886	java/lang/String
    //   295: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   298: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   301: pop
    //   302: goto -80 -> 222
    //   305: astore_2
    //   306: aload_2
    //   307: invokevirtual 940	org/json/JSONException:printStackTrace	()V
    //   310: new 942	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   313: dup
    //   314: invokespecial 943	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   317: iconst_1
    //   318: anewarray 886	java/lang/String
    //   321: dup
    //   322: iconst_0
    //   323: ldc_w 945
    //   326: aastore
    //   327: invokevirtual 949	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   330: pop
    //   331: goto -109 -> 222
    //   334: aload 10
    //   336: getstatic 666	com/ironsource/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   339: if_acmpne -207 -> 132
    //   342: aload_0
    //   343: getfield 453	com/ironsource/sdk/controller/IronSourceWebView:mOWAppKey	Ljava/lang/String;
    //   346: astore 6
    //   348: aload_0
    //   349: getfield 456	com/ironsource/sdk/controller/IronSourceWebView:mOWUserId	Ljava/lang/String;
    //   352: astore 5
    //   354: aload_0
    //   355: getfield 460	com/ironsource/sdk/controller/IronSourceWebView:mOWExtraParameters	Ljava/util/Map;
    //   358: astore 4
    //   360: goto -228 -> 132
    //   363: astore_1
    //   364: aload_1
    //   365: invokevirtual 940	org/json/JSONException:printStackTrace	()V
    //   368: new 942	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   371: dup
    //   372: invokespecial 943	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   375: iconst_1
    //   376: anewarray 886	java/lang/String
    //   379: dup
    //   380: iconst_0
    //   381: ldc_w 951
    //   384: aastore
    //   385: invokevirtual 949	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   388: pop
    //   389: goto -247 -> 142
    //   392: iconst_1
    //   393: istore_3
    //   394: aload 7
    //   396: astore 4
    //   398: goto -254 -> 144
    //   401: astore_1
    //   402: aload_1
    //   403: invokevirtual 940	org/json/JSONException:printStackTrace	()V
    //   406: new 942	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   409: dup
    //   410: invokespecial 943	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   413: iconst_1
    //   414: anewarray 886	java/lang/String
    //   417: dup
    //   418: iconst_0
    //   419: ldc_w 953
    //   422: aastore
    //   423: invokevirtual 949	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   426: pop
    //   427: goto -258 -> 169
    //   430: iconst_1
    //   431: istore_3
    //   432: goto -263 -> 169
    //   435: astore_1
    //   436: aload_1
    //   437: invokevirtual 940	org/json/JSONException:printStackTrace	()V
    //   440: new 942	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   443: dup
    //   444: invokespecial 943	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   447: iconst_1
    //   448: anewarray 886	java/lang/String
    //   451: dup
    //   452: iconst_0
    //   453: ldc_w 955
    //   456: aastore
    //   457: invokevirtual 949	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   460: pop
    //   461: goto -267 -> 194
    //   464: iconst_1
    //   465: istore_3
    //   466: goto -272 -> 194
    //   469: iconst_2
    //   470: anewarray 297	java/lang/Object
    //   473: dup
    //   474: iconst_0
    //   475: aload 9
    //   477: invokevirtual 914	org/json/JSONObject:toString	()Ljava/lang/String;
    //   480: aastore
    //   481: dup
    //   482: iconst_1
    //   483: iload_3
    //   484: invokestatic 961	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
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
    //   1: invokestatic 1005	com/ironsource/sdk/utils/DeviceProperties:getInstance	(Landroid/content/Context;)Lcom/ironsource/sdk/utils/DeviceProperties;
    //   4: astore 10
    //   6: new 818	org/json/JSONObject
    //   9: dup
    //   10: invokespecial 859	org/json/JSONObject:<init>	()V
    //   13: astore 9
    //   15: aload 9
    //   17: ldc_w 1007
    //   20: aload_0
    //   21: invokevirtual 1010	com/ironsource/sdk/controller/IronSourceWebView:getCurrentActivityContext	()Landroid/content/Context;
    //   24: invokestatic 1014	com/ironsource/environment/DeviceStatus:getActivityRequestedOrientation	(Landroid/content/Context;)I
    //   27: invokestatic 1017	com/ironsource/sdk/utils/SDKUtils:translateRequestedOrientation	(I)Ljava/lang/String;
    //   30: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   33: pop
    //   34: aload 10
    //   36: invokevirtual 1020	com/ironsource/sdk/utils/DeviceProperties:getDeviceOem	()Ljava/lang/String;
    //   39: astore 11
    //   41: aload 11
    //   43: ifnull +20 -> 63
    //   46: aload 9
    //   48: ldc_w 1022
    //   51: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   54: aload 11
    //   56: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   59: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   62: pop
    //   63: aload 10
    //   65: invokevirtual 1025	com/ironsource/sdk/utils/DeviceProperties:getDeviceModel	()Ljava/lang/String;
    //   68: astore 11
    //   70: aload 11
    //   72: ifnull +1046 -> 1118
    //   75: aload 9
    //   77: ldc_w 1027
    //   80: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   83: aload 11
    //   85: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   88: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   91: pop
    //   92: iconst_0
    //   93: istore 5
    //   95: iload 5
    //   97: istore 4
    //   99: aload_1
    //   100: invokestatic 1030	com/ironsource/sdk/utils/SDKUtils:loadGoogleAdvertiserInfo	(Landroid/content/Context;)V
    //   103: iload 5
    //   105: istore 4
    //   107: invokestatic 1033	com/ironsource/sdk/utils/SDKUtils:getAdvertiserId	()Ljava/lang/String;
    //   110: astore 11
    //   112: iload 5
    //   114: istore 4
    //   116: invokestatic 1036	com/ironsource/sdk/utils/SDKUtils:isLimitAdTrackingEnabled	()Z
    //   119: istore 6
    //   121: iload 5
    //   123: istore 4
    //   125: aload 11
    //   127: invokestatic 842	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   130: ifne +84 -> 214
    //   133: iload 5
    //   135: istore 4
    //   137: aload_0
    //   138: getfield 266	com/ironsource/sdk/controller/IronSourceWebView:TAG	Ljava/lang/String;
    //   141: ldc_w 1038
    //   144: invokestatic 323	com/ironsource/sdk/utils/Logger:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   147: iload 5
    //   149: istore 4
    //   151: aload 9
    //   153: ldc_w 1039
    //   156: iload 6
    //   158: invokestatic 961	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   161: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   164: pop
    //   165: iload 5
    //   167: istore 4
    //   169: aload 9
    //   171: new 903	java/lang/StringBuilder
    //   174: dup
    //   175: invokespecial 904	java/lang/StringBuilder:<init>	()V
    //   178: ldc_w 1041
    //   181: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: ldc_w 1043
    //   187: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: ldc_w 1045
    //   193: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: ldc_w 1047
    //   199: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: invokevirtual 913	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   205: aload 11
    //   207: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   210: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   213: pop
    //   214: iload 5
    //   216: istore 4
    //   218: aload 10
    //   220: invokevirtual 1050	com/ironsource/sdk/utils/DeviceProperties:getDeviceOsType	()Ljava/lang/String;
    //   223: astore 11
    //   225: aload 11
    //   227: ifnull +897 -> 1124
    //   230: iload 5
    //   232: istore 4
    //   234: aload 9
    //   236: ldc_w 1052
    //   239: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   242: aload 11
    //   244: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   247: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   250: pop
    //   251: iload 5
    //   253: istore 4
    //   255: aload 10
    //   257: invokevirtual 1055	com/ironsource/sdk/utils/DeviceProperties:getDeviceOsVersion	()I
    //   260: invokestatic 827	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   263: astore 11
    //   265: aload 11
    //   267: ifnull +863 -> 1130
    //   270: iload 5
    //   272: istore 4
    //   274: aload 9
    //   276: ldc_w 1057
    //   279: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   282: aload 11
    //   284: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   287: pop
    //   288: iload 5
    //   290: istore 4
    //   292: invokestatic 1060	com/ironsource/sdk/utils/DeviceProperties:getSupersonicSdkVersion	()Ljava/lang/String;
    //   295: astore 11
    //   297: aload 11
    //   299: ifnull +24 -> 323
    //   302: iload 5
    //   304: istore 4
    //   306: aload 9
    //   308: ldc_w 1062
    //   311: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   314: aload 11
    //   316: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   319: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   322: pop
    //   323: iload 5
    //   325: istore 4
    //   327: aload 10
    //   329: invokevirtual 1065	com/ironsource/sdk/utils/DeviceProperties:getDeviceCarrier	()Ljava/lang/String;
    //   332: ifnull +42 -> 374
    //   335: iload 5
    //   337: istore 4
    //   339: aload 10
    //   341: invokevirtual 1065	com/ironsource/sdk/utils/DeviceProperties:getDeviceCarrier	()Ljava/lang/String;
    //   344: invokevirtual 1066	java/lang/String:length	()I
    //   347: ifle +27 -> 374
    //   350: iload 5
    //   352: istore 4
    //   354: aload 9
    //   356: ldc_w 1068
    //   359: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   362: aload 10
    //   364: invokevirtual 1065	com/ironsource/sdk/utils/DeviceProperties:getDeviceCarrier	()Ljava/lang/String;
    //   367: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   370: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   373: pop
    //   374: iload 5
    //   376: istore 4
    //   378: aload_1
    //   379: invokestatic 1073	com/ironsource/environment/ConnectivityService:getConnectionType	(Landroid/content/Context;)Ljava/lang/String;
    //   382: astore 10
    //   384: iload 5
    //   386: istore 4
    //   388: aload 10
    //   390: invokestatic 842	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   393: ifne +743 -> 1136
    //   396: iload 5
    //   398: istore 4
    //   400: aload 9
    //   402: ldc_w 1075
    //   405: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   408: aload 10
    //   410: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   413: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   416: pop
    //   417: iload 5
    //   419: istore 4
    //   421: aload_1
    //   422: invokevirtual 1079	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   425: invokevirtual 1085	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   428: getfield 1091	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   431: invokevirtual 1096	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   434: astore 10
    //   436: iload 5
    //   438: istore 4
    //   440: aload 10
    //   442: invokestatic 842	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   445: ifne +27 -> 472
    //   448: iload 5
    //   450: istore 4
    //   452: aload 9
    //   454: ldc_w 1098
    //   457: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   460: aload 10
    //   462: invokevirtual 1101	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   465: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   468: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   471: pop
    //   472: iload 5
    //   474: istore 4
    //   476: invokestatic 1104	com/ironsource/sdk/utils/SDKUtils:isExternalStorageAvailable	()Z
    //   479: ifeq +663 -> 1142
    //   482: iload 5
    //   484: istore 4
    //   486: aload_0
    //   487: getfield 329	com/ironsource/sdk/controller/IronSourceWebView:mCacheDirectory	Ljava/lang/String;
    //   490: invokestatic 1108	com/ironsource/environment/DeviceStatus:getAvailableMemorySizeInMegaBytes	(Ljava/lang/String;)J
    //   493: lstore 7
    //   495: iload 5
    //   497: istore 4
    //   499: aload 9
    //   501: ldc_w 1110
    //   504: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   507: lload 7
    //   509: invokestatic 1112	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   512: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   515: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   518: pop
    //   519: iload 5
    //   521: istore 4
    //   523: invokestatic 1115	com/ironsource/environment/DeviceStatus:getDeviceWidth	()I
    //   526: invokestatic 1117	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   529: astore 10
    //   531: iload 5
    //   533: istore 4
    //   535: aload 10
    //   537: invokestatic 842	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   540: ifne +608 -> 1148
    //   543: iload 5
    //   545: istore 4
    //   547: new 903	java/lang/StringBuilder
    //   550: dup
    //   551: invokespecial 904	java/lang/StringBuilder:<init>	()V
    //   554: astore 11
    //   556: iload 5
    //   558: istore 4
    //   560: aload 11
    //   562: ldc_w 1119
    //   565: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   568: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: ldc_w 1043
    //   574: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   577: ldc_w 1121
    //   580: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   583: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   586: ldc_w 1047
    //   589: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   592: pop
    //   593: iload 5
    //   595: istore 4
    //   597: aload 9
    //   599: aload 11
    //   601: invokevirtual 913	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   604: aload 10
    //   606: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   609: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   612: pop
    //   613: iload 5
    //   615: istore 4
    //   617: invokestatic 1124	com/ironsource/environment/DeviceStatus:getDeviceHeight	()I
    //   620: istore_3
    //   621: iload 5
    //   623: istore 4
    //   625: new 903	java/lang/StringBuilder
    //   628: dup
    //   629: invokespecial 904	java/lang/StringBuilder:<init>	()V
    //   632: astore 10
    //   634: iload 5
    //   636: istore 4
    //   638: aload 10
    //   640: ldc_w 1119
    //   643: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   646: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   649: ldc_w 1043
    //   652: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: ldc_w 1126
    //   658: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   661: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: ldc_w 1047
    //   667: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   670: pop
    //   671: iload 5
    //   673: istore 4
    //   675: aload 9
    //   677: aload 10
    //   679: invokevirtual 913	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   682: iload_3
    //   683: invokestatic 1117	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   686: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   689: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   692: pop
    //   693: iload 5
    //   695: istore 4
    //   697: aload_0
    //   698: invokevirtual 966	com/ironsource/sdk/controller/IronSourceWebView:getContext	()Landroid/content/Context;
    //   701: invokestatic 1131	com/ironsource/environment/ApplicationContext:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   704: astore 10
    //   706: iload 5
    //   708: istore 4
    //   710: aload 10
    //   712: invokestatic 842	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   715: ifne +24 -> 739
    //   718: iload 5
    //   720: istore 4
    //   722: aload 9
    //   724: ldc_w 1133
    //   727: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   730: aload 10
    //   732: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   735: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   738: pop
    //   739: iload 5
    //   741: istore 4
    //   743: invokestatic 1136	com/ironsource/environment/DeviceStatus:getDeviceDensity	()F
    //   746: invokestatic 1138	java/lang/String:valueOf	(F)Ljava/lang/String;
    //   749: astore 10
    //   751: iload 5
    //   753: istore 4
    //   755: aload 10
    //   757: invokestatic 842	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   760: ifne +24 -> 784
    //   763: iload 5
    //   765: istore 4
    //   767: aload 9
    //   769: ldc_w 1140
    //   772: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   775: aload 10
    //   777: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   780: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   783: pop
    //   784: iload 5
    //   786: istore 4
    //   788: invokestatic 1143	com/ironsource/environment/DeviceStatus:isRootedDevice	()Z
    //   791: invokestatic 1146	java/lang/String:valueOf	(Z)Ljava/lang/String;
    //   794: astore 10
    //   796: iload 5
    //   798: istore 4
    //   800: aload 10
    //   802: invokestatic 842	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   805: ifne +24 -> 829
    //   808: iload 5
    //   810: istore 4
    //   812: aload 9
    //   814: ldc_w 1148
    //   817: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   820: aload 10
    //   822: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   825: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   828: pop
    //   829: iload 5
    //   831: istore 4
    //   833: aload_1
    //   834: invokestatic 1005	com/ironsource/sdk/utils/DeviceProperties:getInstance	(Landroid/content/Context;)Lcom/ironsource/sdk/utils/DeviceProperties;
    //   837: aload_1
    //   838: invokevirtual 1152	com/ironsource/sdk/utils/DeviceProperties:getDeviceVolume	(Landroid/content/Context;)F
    //   841: fstore_2
    //   842: iload 5
    //   844: istore 4
    //   846: aload 10
    //   848: invokestatic 842	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   851: ifne +21 -> 872
    //   854: iload 5
    //   856: istore 4
    //   858: aload 9
    //   860: ldc_w 1154
    //   863: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   866: fload_2
    //   867: f2d
    //   868: invokevirtual 1157	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   871: pop
    //   872: iload 5
    //   874: istore 4
    //   876: aload_0
    //   877: invokevirtual 1010	com/ironsource/sdk/controller/IronSourceWebView:getCurrentActivityContext	()Landroid/content/Context;
    //   880: astore_1
    //   881: iload 5
    //   883: istore 4
    //   885: getstatic 1162	android/os/Build$VERSION:SDK_INT	I
    //   888: bipush 19
    //   890: if_icmplt +37 -> 927
    //   893: iload 5
    //   895: istore 4
    //   897: aload_1
    //   898: instanceof 1164
    //   901: ifeq +26 -> 927
    //   904: iload 5
    //   906: istore 4
    //   908: aload 9
    //   910: ldc_w 1166
    //   913: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   916: aload_1
    //   917: checkcast 1164	android/app/Activity
    //   920: invokestatic 1170	com/ironsource/environment/DeviceStatus:isImmersiveSupported	(Landroid/app/Activity;)Z
    //   923: invokevirtual 995	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   926: pop
    //   927: iload 5
    //   929: istore 4
    //   931: aload 9
    //   933: ldc_w 1172
    //   936: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   939: aload_1
    //   940: invokestatic 1175	com/ironsource/environment/DeviceStatus:getBatteryLevel	(Landroid/content/Context;)I
    //   943: invokevirtual 1178	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   946: pop
    //   947: iload 5
    //   949: istore 4
    //   951: aload 9
    //   953: ldc_w 1180
    //   956: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   959: aload_1
    //   960: invokestatic 1183	com/ironsource/environment/ConnectivityService:getNetworkMCC	(Landroid/content/Context;)I
    //   963: invokevirtual 1178	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   966: pop
    //   967: iload 5
    //   969: istore 4
    //   971: aload 9
    //   973: ldc_w 1185
    //   976: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   979: aload_1
    //   980: invokestatic 1188	com/ironsource/environment/ConnectivityService:getNetworkMNC	(Landroid/content/Context;)I
    //   983: invokevirtual 1178	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   986: pop
    //   987: iload 5
    //   989: istore 4
    //   991: aload 9
    //   993: ldc_w 1190
    //   996: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   999: aload_1
    //   1000: invokestatic 1193	com/ironsource/environment/ConnectivityService:getPhoneType	(Landroid/content/Context;)I
    //   1003: invokevirtual 1178	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1006: pop
    //   1007: iload 5
    //   1009: istore 4
    //   1011: aload 9
    //   1013: ldc_w 1195
    //   1016: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1019: aload_1
    //   1020: invokestatic 1198	com/ironsource/environment/ConnectivityService:getSimOperator	(Landroid/content/Context;)Ljava/lang/String;
    //   1023: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1026: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1029: pop
    //   1030: iload 5
    //   1032: istore 4
    //   1034: aload 9
    //   1036: ldc_w 1200
    //   1039: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1042: aload_1
    //   1043: invokestatic 1204	com/ironsource/environment/ApplicationContext:getLastUpdateTime	(Landroid/content/Context;)J
    //   1046: invokevirtual 1207	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   1049: pop
    //   1050: iload 5
    //   1052: istore 4
    //   1054: aload 9
    //   1056: ldc_w 1209
    //   1059: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1062: aload_1
    //   1063: invokestatic 1212	com/ironsource/environment/ApplicationContext:getFirstInstallTime	(Landroid/content/Context;)J
    //   1066: invokevirtual 1207	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   1069: pop
    //   1070: iload 5
    //   1072: istore 4
    //   1074: aload 9
    //   1076: ldc_w 1214
    //   1079: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1082: aload_1
    //   1083: invokestatic 1217	com/ironsource/environment/ApplicationContext:getApplicationVersionName	(Landroid/content/Context;)Ljava/lang/String;
    //   1086: invokestatic 894	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   1089: invokevirtual 927	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1092: pop
    //   1093: iload 5
    //   1095: istore 4
    //   1097: iconst_2
    //   1098: anewarray 297	java/lang/Object
    //   1101: dup
    //   1102: iconst_0
    //   1103: aload 9
    //   1105: invokevirtual 914	org/json/JSONObject:toString	()Ljava/lang/String;
    //   1108: aastore
    //   1109: dup
    //   1110: iconst_1
    //   1111: iload 4
    //   1113: invokestatic 961	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
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
    //   1159: invokevirtual 940	org/json/JSONException:printStackTrace	()V
    //   1162: new 942	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   1165: dup
    //   1166: invokespecial 943	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   1169: iconst_1
    //   1170: anewarray 886	java/lang/String
    //   1173: dup
    //   1174: iconst_0
    //   1175: new 903	java/lang/StringBuilder
    //   1178: dup
    //   1179: invokespecial 904	java/lang/StringBuilder:<init>	()V
    //   1182: ldc_w 1219
    //   1185: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1188: aload_1
    //   1189: invokevirtual 1223	org/json/JSONException:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   1192: iconst_0
    //   1193: aaload
    //   1194: invokevirtual 1228	java/lang/StackTraceElement:getMethodName	()Ljava/lang/String;
    //   1197: invokevirtual 910	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1200: invokevirtual 913	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1203: aastore
    //   1204: invokevirtual 949	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
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
              IronSourceWebView.access$6602(IronSourceWebView.this, Boolean.valueOf(true));
              return;
            }
            catch (NoSuchMethodError localNoSuchMethodError)
            {
              Logger.e(IronSourceWebView.this.TAG, "evaluateJavascrip NoSuchMethodError: SDK version=" + Build.VERSION.SDK_INT + " " + localNoSuchMethodError);
              IronSourceWebView.this.loadUrl(this.val$url);
              IronSourceWebView.access$6602(IronSourceWebView.this, Boolean.valueOf(false));
              return;
            }
            catch (Throwable localThrowable2)
            {
              Logger.e(IronSourceWebView.this.TAG, "evaluateJavascrip Exception: SDK version=" + Build.VERSION.SDK_INT + " " + localThrowable2);
              IronSourceWebView.this.loadUrl(this.val$url);
              IronSourceWebView.access$6602(IronSourceWebView.this, Boolean.valueOf(false));
              return;
            }
          }
          IronSourceWebView.this.loadUrl(this.val$url);
          IronSourceWebView.access$6602(IronSourceWebView.this, Boolean.valueOf(false));
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
      throws JSONException
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
        IronSourceWebView.access$4702(IronSourceWebView.this, ((SSAObj)localObject).getBoolean("immersive"));
        IronSourceWebView.access$4802(IronSourceWebView.this, ((SSAObj)localObject).getBoolean("activityThemeTranslucent"));
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
