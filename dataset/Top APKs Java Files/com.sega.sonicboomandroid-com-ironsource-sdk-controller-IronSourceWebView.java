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
  public static String APP_IDS = "appIds";
  public static int DISPLAY_WEB_VIEW_INTENT = 0;
  public static String EXTERNAL_URL = "external_url";
  public static String IS_INSTALLED = "isInstalled";
  public static String IS_STORE = "is_store";
  public static String IS_STORE_CLOSE = "is_store_close";
  private static String JSON_KEY_FAIL = "fail";
  private static String JSON_KEY_SUCCESS = "success";
  public static int OPEN_URL_INTENT = 1;
  public static String REQUEST_ID = "requestId";
  public static String RESULT = "result";
  public static String SECONDARY_WEB_VIEW = "secondary_web_view";
  public static String WEBVIEW_TYPE = "webview_type";
  public static int mDebugMode;
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
        if (ConnectivityService.isConnectedWifi(paramAnonymousContext)) {
          paramAnonymousIntent = "wifi";
        } else if (ConnectivityService.isConnectedMobile(paramAnonymousContext)) {
          paramAnonymousIntent = "3g";
        }
        IronSourceWebView.this.deviceStatusChanged(paramAnonymousIntent);
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
    if (paramProductType == SSAEnums.ProductType.RewardedVideo)
    {
      paramProductType = this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, paramString);
      HashMap localHashMap = new HashMap();
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
      paramProductType = generateJSToInject("initRewardedVideo", flatMapToJsonAsString(localHashMap), "onInitRewardedVideoSuccess", "onInitRewardedVideoFail");
    }
    else if (paramProductType == SSAEnums.ProductType.Interstitial)
    {
      paramProductType = new HashMap();
      paramProductType.put("applicationKey", this.mISAppKey);
      paramProductType.put("applicationUserId", this.mISUserId);
      if (this.mISExtraParameters != null) {
        paramProductType.putAll(this.mISExtraParameters);
      }
      paramProductType = generateJSToInject("initInterstitial", flatMapToJsonAsString(paramProductType), "onInitInterstitialSuccess", "onInitInterstitialFail");
    }
    else if (paramProductType == SSAEnums.ProductType.OfferWall)
    {
      paramProductType = new HashMap();
      paramProductType.put("applicationKey", this.mOWAppKey);
      paramProductType.put("applicationUserId", this.mOWUserId);
      if (this.mOWExtraParameters != null) {
        paramProductType.putAll(this.mOWExtraParameters);
      }
      paramProductType = generateJSToInject("initOfferWall", flatMapToJsonAsString(paramProductType), "onInitOfferWallSuccess", "onInitOfferWallFail");
    }
    else if (paramProductType == SSAEnums.ProductType.OfferWallCredits)
    {
      paramProductType = generateJSToInject("getUserCredits", parseToJson("productType", "OfferWall", "applicationKey", this.mOWCreditsAppKey, "applicationUserId", this.mOWCreditsUserId, null, null, null, false), "null", "onGetUserCreditsFail");
    }
    else
    {
      paramProductType = null;
    }
    if (paramProductType != null) {
      injectJavascript(paramProductType);
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
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        try
        {
          localJSONObject.putOpt((String)localEntry.getKey(), SDKUtils.encodeString((String)localEntry.getValue()));
        }
        catch (JSONException localJSONException)
        {
          String str = this.TAG;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("flatMapToJsonAsStringfailed ");
          localStringBuilder.append(localJSONException.toString());
          Logger.i(str, localStringBuilder.toString());
        }
        paramMap.remove();
      }
    }
    return localJSONObject.toString();
  }
  
  private String generateJSToInject(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('");
    localStringBuilder.append(paramString);
    localStringBuilder.append("');");
    return localStringBuilder.toString();
  }
  
  private String generateJSToInject(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("?parameters=");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("');");
    return localStringBuilder.toString();
  }
  
  private String generateJSToInject(String paramString1, String paramString2, String paramString3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("','");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("','");
    localStringBuilder.append(paramString3);
    localStringBuilder.append("');");
    return localStringBuilder.toString();
  }
  
  private String generateJSToInject(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("?parameters=");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("','");
    localStringBuilder.append(paramString3);
    localStringBuilder.append("','");
    localStringBuilder.append(paramString4);
    localStringBuilder.append("');");
    return localStringBuilder.toString();
  }
  
  private Object[] getApplicationParams(String paramString1, String paramString2)
  {
    JSONObject localJSONObject = new JSONObject();
    Object localObject1 = "";
    Object localObject2 = "";
    boolean bool = TextUtils.isEmpty(paramString1);
    String str1 = null;
    Object localObject3 = null;
    if (!bool)
    {
      if (paramString1.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()))
      {
        str1 = this.mRVAppKey;
        String str2 = this.mRVUserId;
        DemandSource localDemandSource = this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, paramString2);
        paramString2 = str1;
        localObject1 = str2;
        localObject2 = localObject3;
        if (localDemandSource != null)
        {
          localObject2 = localDemandSource.getExtraParams();
          paramString2 = str1;
          localObject1 = str2;
        }
      }
      else
      {
        if (paramString1.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString()))
        {
          paramString2 = this.mISAppKey;
          localObject1 = this.mISUserId;
        }
        for (localObject2 = this.mISExtraParameters;; localObject2 = this.mOWExtraParameters)
        {
          break;
          paramString2 = (String)localObject1;
          localObject1 = localObject2;
          localObject2 = localObject3;
          if (!paramString1.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
            break;
          }
          paramString2 = this.mOWAppKey;
          localObject1 = this.mOWUserId;
        }
      }
      try
      {
        localJSONObject.put("productType", paramString1);
      }
      catch (JSONException paramString1)
      {
        paramString1.printStackTrace();
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=noProductType" });
      }
      bool = false;
      localObject3 = localObject1;
      paramString1 = (String)localObject2;
    }
    else
    {
      bool = true;
      paramString1 = str1;
      localObject3 = localObject2;
      paramString2 = (String)localObject1;
    }
    if (!TextUtils.isEmpty(localObject3)) {
      try
      {
        localJSONObject.put(SDKUtils.encodeString("applicationUserId"), SDKUtils.encodeString(localObject3));
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=encodeAppUserId" });
      }
    } else {
      bool = true;
    }
    if (!TextUtils.isEmpty(paramString2)) {
      try
      {
        localJSONObject.put(SDKUtils.encodeString("applicationKey"), SDKUtils.encodeString(paramString2));
      }
      catch (JSONException paramString2)
      {
        paramString2.printStackTrace();
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=encodeAppKey" });
      }
    } else {
      bool = true;
    }
    if ((paramString1 != null) && (!paramString1.isEmpty()))
    {
      paramString1 = paramString1.entrySet().iterator();
      while (paramString1.hasNext())
      {
        paramString2 = (Map.Entry)paramString1.next();
        if (((String)paramString2.getKey()).equalsIgnoreCase("sdkWebViewCache")) {
          setWebviewCache((String)paramString2.getValue());
        }
        try
        {
          localJSONObject.put(SDKUtils.encodeString((String)paramString2.getKey()), SDKUtils.encodeString((String)paramString2.getValue()));
        }
        catch (JSONException paramString2)
        {
          paramString2.printStackTrace();
          new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=extraParametersToJson" });
        }
      }
    }
    return new Object[] { localJSONObject.toString(), Boolean.valueOf(bool) };
  }
  
  private Object[] getAppsStatus(String paramString1, String paramString2)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      if ((TextUtils.isEmpty(paramString1)) || (paramString1.equalsIgnoreCase("null"))) {
        break label243;
      }
      if ((TextUtils.isEmpty(paramString2)) || (paramString2.equalsIgnoreCase("null"))) {
        break label228;
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
        boolean bool;
        label228:
        label243:
        label255:
        label258:
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
      break label258;
      localJSONObject1.put("error", "requestId is null or empty");
      break label255;
      localJSONObject1.put("error", "appIds is null or empty");
      bool = true;
      return new Object[] { localJSONObject1.toString(), Boolean.valueOf(bool) };
    }
  }
  
  private Object[] getDeviceParams(Context paramContext)
  {
    Object localObject1 = DeviceProperties.getInstance(paramContext);
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      boolean bool1;
      try
      {
        localJSONObject.put("appOrientation", SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(getCurrentActivityContext())));
        localObject2 = ((DeviceProperties)localObject1).getDeviceOem();
        if (localObject2 != null) {
          localJSONObject.put(SDKUtils.encodeString("deviceOEM"), SDKUtils.encodeString((String)localObject2));
        }
        localObject2 = ((DeviceProperties)localObject1).getDeviceModel();
        if (localObject2 != null)
        {
          localJSONObject.put(SDKUtils.encodeString("deviceModel"), SDKUtils.encodeString((String)localObject2));
          bool2 = false;
        }
        else
        {
          bool2 = true;
        }
        bool1 = bool2;
        try
        {
          SDKUtils.loadGoogleAdvertiserInfo(paramContext);
          bool1 = bool2;
          localObject2 = SDKUtils.getAdvertiserId();
          bool1 = bool2;
          boolean bool3 = SDKUtils.isLimitAdTrackingEnabled();
          bool1 = bool2;
          if (!TextUtils.isEmpty((CharSequence)localObject2))
          {
            bool1 = bool2;
            Logger.i(this.TAG, "add AID and LAT");
            bool1 = bool2;
            localJSONObject.put("isLimitAdTrackingEnabled", Boolean.valueOf(bool3));
            bool1 = bool2;
            StringBuilder localStringBuilder = new StringBuilder();
            bool1 = bool2;
            localStringBuilder.append("deviceIds");
            bool1 = bool2;
            localStringBuilder.append("[");
            bool1 = bool2;
            localStringBuilder.append("AID");
            bool1 = bool2;
            localStringBuilder.append("]");
            bool1 = bool2;
            localJSONObject.put(localStringBuilder.toString(), SDKUtils.encodeString((String)localObject2));
          }
          bool1 = bool2;
          localObject2 = ((DeviceProperties)localObject1).getDeviceOsType();
          if (localObject2 == null) {
            break label1292;
          }
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("deviceOs"), SDKUtils.encodeString((String)localObject2));
          bool1 = bool2;
          localObject2 = Integer.toString(((DeviceProperties)localObject1).getDeviceOsVersion());
          if (localObject2 == null) {
            break label1298;
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
            break label1304;
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
            break label1310;
          }
          bool1 = bool2;
          long l = DeviceStatus.getAvailableMemorySizeInMegaBytes(this.mCacheDirectory);
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("diskFreeSize"), SDKUtils.encodeString(String.valueOf(l)));
          bool1 = bool2;
          localObject1 = String.valueOf(DeviceStatus.getDeviceWidth());
          bool1 = bool2;
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            break label1316;
          }
          bool1 = bool2;
          localObject2 = new StringBuilder();
          bool1 = bool2;
          ((StringBuilder)localObject2).append(SDKUtils.encodeString("deviceScreenSize"));
          bool1 = bool2;
          ((StringBuilder)localObject2).append("[");
          bool1 = bool2;
          ((StringBuilder)localObject2).append(SDKUtils.encodeString("width"));
          bool1 = bool2;
          ((StringBuilder)localObject2).append("]");
          bool1 = bool2;
          localJSONObject.put(((StringBuilder)localObject2).toString(), SDKUtils.encodeString((String)localObject1));
          bool1 = bool2;
          int i = DeviceStatus.getDeviceHeight();
          bool1 = bool2;
          localObject1 = new StringBuilder();
          bool1 = bool2;
          ((StringBuilder)localObject1).append(SDKUtils.encodeString("deviceScreenSize"));
          bool1 = bool2;
          ((StringBuilder)localObject1).append("[");
          bool1 = bool2;
          ((StringBuilder)localObject1).append(SDKUtils.encodeString("height"));
          bool1 = bool2;
          ((StringBuilder)localObject1).append("]");
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
        catch (JSONException paramContext) {}
        paramContext.printStackTrace();
      }
      catch (JSONException paramContext)
      {
        bool1 = false;
      }
      localObject1 = new IronSourceAsyncHttpRequestTask();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("https://www.supersonicads.com/mobile/sdk5/log?method=");
      ((StringBuilder)localObject2).append(paramContext.getStackTrace()[0].getMethodName());
      ((IronSourceAsyncHttpRequestTask)localObject1).execute(new String[] { ((StringBuilder)localObject2).toString() });
      return new Object[] { localJSONObject.toString(), Boolean.valueOf(bool1) };
      label1292:
      boolean bool2 = true;
      continue;
      label1298:
      bool2 = true;
      continue;
      label1304:
      bool2 = true;
      continue;
      label1310:
      bool2 = true;
      continue;
      label1316:
      bool2 = true;
    }
  }
  
  private String getRequestParameters(JSONObject paramJSONObject)
  {
    Object localObject1 = DeviceProperties.getInstance(getContext());
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject2 = DeviceProperties.getSupersonicSdkVersion();
    if (!TextUtils.isEmpty((CharSequence)localObject2))
    {
      localStringBuilder.append("SDKVersion");
      localStringBuilder.append("=");
      localStringBuilder.append((String)localObject2);
      localStringBuilder.append("&");
    }
    localObject1 = ((DeviceProperties)localObject1).getDeviceOsType();
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      localStringBuilder.append("deviceOs");
      localStringBuilder.append("=");
      localStringBuilder.append((String)localObject1);
    }
    localObject1 = Uri.parse(SDKUtils.getControllerUrl());
    if (localObject1 != null)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((Uri)localObject1).getScheme());
      ((StringBuilder)localObject2).append(":");
      String str = ((StringBuilder)localObject2).toString();
      localObject2 = ((Uri)localObject1).getHost();
      int i = ((Uri)localObject1).getPort();
      localObject1 = localObject2;
      if (i != -1)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append(":");
        ((StringBuilder)localObject1).append(i);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      localStringBuilder.append("&");
      localStringBuilder.append("protocol");
      localStringBuilder.append("=");
      localStringBuilder.append(str);
      localStringBuilder.append("&");
      localStringBuilder.append("domain");
      localStringBuilder.append("=");
      localStringBuilder.append((String)localObject1);
      if (paramJSONObject.keys().hasNext()) {
        try
        {
          paramJSONObject = new JSONObject(paramJSONObject, new String[] { "isSecured", "applicationKey" }).toString();
          if (!TextUtils.isEmpty(paramJSONObject))
          {
            localStringBuilder.append("&");
            localStringBuilder.append("controllerConfig");
            localStringBuilder.append("=");
            localStringBuilder.append(paramJSONObject);
          }
        }
        catch (JSONException paramJSONObject)
        {
          paramJSONObject.printStackTrace();
        }
      }
      localStringBuilder.append("&");
      localStringBuilder.append("debug");
      localStringBuilder.append("=");
      localStringBuilder.append(getDebugMode());
    }
    return localStringBuilder.toString();
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
    if ((!TextUtils.isEmpty(paramString2)) && (!TextUtils.isEmpty(paramString1)))
    {
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
      if (this.mGlobalControllerTimeFinish) {
        downloadController();
      }
      return;
    }
    triggerOnControllerInitProductFail("User id or Application key are missing", paramProductType, paramString4);
  }
  
  private void injectJavascript(String paramString)
  {
    Object localObject2 = "empty";
    Object localObject1;
    if (getDebugMode() == SSAEnums.DebugMode.MODE_0.getValue())
    {
      localObject1 = "console.log(\"JS exeption: \" + JSON.stringify(e));";
    }
    else
    {
      localObject1 = localObject2;
      if (getDebugMode() >= SSAEnums.DebugMode.MODE_1.getValue())
      {
        localObject1 = localObject2;
        if (getDebugMode() <= SSAEnums.DebugMode.MODE_3.getValue()) {
          localObject1 = "console.log(\"JS exeption: \" + JSON.stringify(e));";
        }
      }
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("try{");
    ((StringBuilder)localObject2).append(paramString);
    ((StringBuilder)localObject2).append("}catch(e){");
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("}");
    paramString = new StringBuilder();
    paramString.append("javascript:");
    paramString.append(((StringBuilder)localObject2).toString());
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
          int i = Build.VERSION.SDK_INT;
          if (i >= 19) {
            try
            {
              IronSourceWebView.this.evaluateJavascriptKitKat(this.val$scriptBuilder.toString());
              IronSourceWebView.access$6502(IronSourceWebView.this, Boolean.valueOf(true));
              return;
            }
            catch (Throwable localThrowable1)
            {
              str = IronSourceWebView.this.TAG;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("evaluateJavascrip Exception: SDK version=");
              localStringBuilder.append(Build.VERSION.SDK_INT);
              localStringBuilder.append(" ");
              localStringBuilder.append(localThrowable1);
              Logger.e(str, localStringBuilder.toString());
              IronSourceWebView.this.loadUrl(this.val$url);
              IronSourceWebView.access$6502(IronSourceWebView.this, Boolean.valueOf(false));
              return;
            }
            catch (NoSuchMethodError localNoSuchMethodError)
            {
              str = IronSourceWebView.this.TAG;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("evaluateJavascrip NoSuchMethodError: SDK version=");
              localStringBuilder.append(Build.VERSION.SDK_INT);
              localStringBuilder.append(" ");
              localStringBuilder.append(localNoSuchMethodError);
              Logger.e(str, localStringBuilder.toString());
              IronSourceWebView.this.loadUrl(this.val$url);
              IronSourceWebView.access$6502(IronSourceWebView.this, Boolean.valueOf(false));
              return;
            }
          }
          IronSourceWebView.this.loadUrl(this.val$url);
          IronSourceWebView.access$6502(IronSourceWebView.this, Boolean.valueOf(false));
          return;
        }
        catch (Throwable localThrowable2)
        {
          String str = IronSourceWebView.this.TAG;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("injectJavascript: ");
          localStringBuilder.append(localThrowable2.toString());
          Logger.e(str, localStringBuilder.toString());
          new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=injectJavaScript" });
        }
      }
    });
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
      paramString1.printStackTrace();
      paramString2 = new IronSourceAsyncHttpRequestTask();
      paramString3 = new StringBuilder();
      paramString3.append("https://www.supersonicads.com/mobile/sdk5/log?method=");
      paramString3.append(paramString1.getStackTrace()[0].getMethodName());
      paramString2.execute(new String[] { paramString3.toString() });
    }
    return localJSONObject.toString();
  }
  
  private void responseBack(String paramString1, boolean paramBoolean, String paramString2, String paramString3)
  {
    Object localObject2 = new SSAObj(paramString1);
    Object localObject1 = ((SSAObj)localObject2).getString(JSON_KEY_SUCCESS);
    localObject2 = ((SSAObj)localObject2).getString(JSON_KEY_FAIL);
    if (paramBoolean)
    {
      if (!TextUtils.isEmpty((CharSequence)localObject1)) {
        break label63;
      }
    }
    else if (!TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject1 = localObject2;
      break label63;
    }
    localObject1 = null;
    label63:
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject2 = paramString1;
      if (TextUtils.isEmpty(paramString2)) {}
    }
    try
    {
      localObject2 = new JSONObject(paramString1).put("errMsg", paramString2).toString();
      paramString1 = (String)localObject2;
      if (TextUtils.isEmpty(paramString3)) {}
    }
    catch (JSONException paramString2)
    {
      try
      {
        paramString1 = new JSONObject((String)localObject2).put("errCode", paramString3).toString();
        injectJavascript(generateJSToInject((String)localObject1, paramString1));
        return;
        paramString2 = paramString2;
        localObject2 = paramString1;
      }
      catch (JSONException paramString1)
      {
        for (;;)
        {
          paramString1 = (String)localObject2;
        }
      }
    }
  }
  
  private void sendProductErrorMessage(SSAEnums.ProductType paramProductType, String paramString)
  {
    String str = "";
    switch (8.$SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[paramProductType.ordinal()])
    {
    default: 
      break;
    case 4: 
      str = "Show OW Credits";
      break;
    case 3: 
      str = "Init OW";
      break;
    case 2: 
      str = "Init IS";
      break;
    case 1: 
      str = "Init RV";
    }
    triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(str, "Initiating Controller"), paramProductType, paramString);
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
    if (Build.VERSION.SDK_INT >= 16) {
      try
      {
        getSettings().setAllowFileAccessFromFileURLs(true);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
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
      String str = this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("setWebSettings - ");
      localStringBuilder.append(localThrowable.toString());
      Logger.e(str, localStringBuilder.toString());
    }
  }
  
  private void setWebviewBackground(String paramString)
  {
    paramString = new SSAObj(paramString).getString("color");
    int i;
    if (!"transparent".equalsIgnoreCase(paramString)) {
      i = Color.parseColor(paramString);
    } else {
      i = 0;
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
    boolean bool1 = TextUtils.isEmpty(paramString);
    boolean bool2 = false;
    if (bool1)
    {
      Logger.d(this.TAG, "Trying to trigger a listener - no product was found");
      return false;
    }
    if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString()))
    {
      bool1 = bool2;
      if (this.mOnInitInterstitialListener == null) {}
    }
    else
    {
      do
      {
        for (;;)
        {
          bool1 = true;
          break label115;
          if (!paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
            break;
          }
          bool1 = bool2;
          if (this.mOnRewardedVideoListener == null) {
            break label115;
          }
        }
        if (!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()))
        {
          bool1 = bool2;
          if (!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWallCredits.toString())) {
            break;
          }
        }
        bool1 = bool2;
      } while (this.mOnOfferWallListener != null);
    }
    label115:
    if (!bool1)
    {
      String str = this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Trying to trigger a listener - no listener was found for product ");
      localStringBuilder.append(paramString);
      Logger.d(str, localStringBuilder.toString());
    }
    return bool1;
  }
  
  private void toastingErrMsg(final String paramString1, final String paramString2)
  {
    paramString2 = new SSAObj(paramString2).getString("errMsg");
    if (!TextUtils.isEmpty(paramString2)) {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (IronSourceWebView.this.getDebugMode() == SSAEnums.DebugMode.MODE_3.getValue())
          {
            Context localContext = IronSourceWebView.this.getCurrentActivityContext();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(paramString1);
            localStringBuilder.append(" : ");
            localStringBuilder.append(paramString2);
            Toast.makeText(localContext, localStringBuilder.toString(), 1).show();
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
          String str;
          StringBuilder localStringBuilder;
          if (SSAEnums.ProductType.RewardedVideo == paramProductType)
          {
            str = IronSourceWebView.this.TAG;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("onRVInitFail(message:");
            localStringBuilder.append(paramString1);
            localStringBuilder.append(")");
            Log.d(str, localStringBuilder.toString());
            IronSourceWebView.this.mOnRewardedVideoListener.onRVInitFail(paramString1, paramString2);
            return;
          }
          if (SSAEnums.ProductType.Interstitial == paramProductType)
          {
            IronSourceWebView.this.mSavedState.setInterstitialInitSuccess(false);
            if (IronSourceWebView.this.mSavedState.reportInitInterstitial())
            {
              str = IronSourceWebView.this.TAG;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("onInterstitialInitFail(message:");
              localStringBuilder.append(paramString1);
              localStringBuilder.append(")");
              Log.d(str, localStringBuilder.toString());
              IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialInitFailed(paramString1);
              IronSourceWebView.this.mSavedState.setReportInitInterstitial(false);
            }
          }
          else
          {
            if (SSAEnums.ProductType.OfferWall == paramProductType)
            {
              IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitFail(paramString1);
              return;
            }
            if (SSAEnums.ProductType.OfferWallCredits == paramProductType) {
              IronSourceWebView.this.mOnOfferWallListener.onGetOWCreditsFailed(paramString1);
            }
          }
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
    String str1 = SDKUtils.getControllerUrl();
    SSAFile localSSAFile = new SSAFile(str1, "");
    this.mGlobalControllerTimer = new CountDownTimer(200000L, 1000L)
    {
      public void onFinish()
      {
        Logger.i(IronSourceWebView.this.TAG, "Global Controller Timer Finish");
        IronSourceWebView.access$902(IronSourceWebView.this, true);
      }
      
      public void onTick(long paramAnonymousLong)
      {
        String str = IronSourceWebView.this.TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Global Controller Timer Tick ");
        localStringBuilder.append(paramAnonymousLong);
        Logger.i(str, localStringBuilder.toString());
      }
    }.start();
    if (!this.downloadManager.isMobileControllerThreadLive())
    {
      String str2 = this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Download Mobile Controller: ");
      localStringBuilder.append(str1);
      Logger.i(str2, localStringBuilder.toString());
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
    Object localObject3;
    try
    {
      loadUrl("about:blank");
    }
    catch (Throwable localThrowable1)
    {
      localObject2 = this.TAG;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("WebViewController:: load: ");
      ((StringBuilder)localObject3).append(localThrowable1.toString());
      Logger.e((String)localObject2, ((StringBuilder)localObject3).toString());
      new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadBlank" });
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("file://");
    ((StringBuilder)localObject1).append(this.mCacheDirectory);
    ((StringBuilder)localObject1).append(File.separator);
    ((StringBuilder)localObject1).append("mobileController.html");
    localObject1 = ((StringBuilder)localObject1).toString();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(this.mCacheDirectory);
    ((StringBuilder)localObject2).append(File.separator);
    ((StringBuilder)localObject2).append("mobileController.html");
    if (new File(((StringBuilder)localObject2).toString()).exists())
    {
      localObject2 = SDKUtils.getControllerConfigAsJSONObject();
      setWebDebuggingEnabled((JSONObject)localObject2);
      this.mRequestParameters = getRequestParameters((JSONObject)localObject2);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("?");
      ((StringBuilder)localObject2).append(this.mRequestParameters);
      localObject1 = ((StringBuilder)localObject2).toString();
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
          }
          else
          {
            IronSourceWebView.this.load(2);
          }
        }
        
        public void onTick(long paramAnonymousLong)
        {
          String str = IronSourceWebView.this.TAG;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Loading Controller Timer Tick ");
          localStringBuilder.append(paramAnonymousLong);
          Logger.i(str, localStringBuilder.toString());
        }
      }.start();
      try
      {
        loadUrl((String)localObject1);
      }
      catch (Throwable localThrowable2)
      {
        localObject3 = this.TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("WebViewController:: load: ");
        localStringBuilder.append(localThrowable2.toString());
        Logger.e((String)localObject3, localStringBuilder.toString());
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadWithPath" });
      }
      String str = this.TAG;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("load(): ");
      ((StringBuilder)localObject3).append((String)localObject1);
      Logger.i(str, ((StringBuilder)localObject3).toString());
      return;
    }
    Logger.i(this.TAG, "load(): Mobile Controller HTML Does not exist");
    new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=htmlControllerDoesNotExistOnFileSystem" });
  }
  
  public void loadInterstitial()
  {
    if (!isInterstitialAdAvailable())
    {
      this.mSavedState.setReportLoadInterstitial(true);
      injectJavascript(generateJSToInject("loadInterstitial", "onLoadInterstitialSuccess", "onLoadInterstitialFail"));
      return;
    }
    if (shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialLoadSuccess();
        }
      });
    }
  }
  
  public void nativeNavigationPressed(String paramString)
  {
    injectJavascript(generateJSToInject("nativeNavigationPressed", parseToJson("action", paramString, null, null, null, null, null, null, null, false)));
  }
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    paramString2 = this.TAG;
    paramString3 = new StringBuilder();
    paramString3.append(paramString1);
    paramString3.append(" ");
    paramString3.append(paramString4);
    Logger.i(paramString2, paramString3.toString());
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
    }
    else
    {
      assetCachedFailed(paramSSAFile.getFile(), paramSSAFile.getPath(), paramSSAFile.getErrMsg());
    }
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
    if (Build.VERSION.SDK_INT > 10) {
      try
      {
        onPause();
        return;
      }
      catch (Throwable localThrowable)
      {
        String str = this.TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("WebViewController: pause() - ");
        localStringBuilder.append(localThrowable);
        Logger.i(str, localStringBuilder.toString());
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewPause" });
      }
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
    synchronized (this.mSavedStateLocker)
    {
      if ((paramAdUnitsState.shouldRestore()) && (this.mControllerState.equals(SSAEnums.ControllerState.Ready)))
      {
        Object localObject2 = this.TAG;
        Object localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("restoreState(state:");
        ((StringBuilder)localObject3).append(paramAdUnitsState);
        ((StringBuilder)localObject3).append(")");
        Log.d((String)localObject2, ((StringBuilder)localObject3).toString());
        int i = paramAdUnitsState.getDisplayedProduct();
        if (i != -1)
        {
          if (i == SSAEnums.ProductType.RewardedVideo.ordinal())
          {
            Log.d(this.TAG, "onRVAdClosed()");
            localObject2 = paramAdUnitsState.getDisplayedDemandSourceName();
            if ((this.mOnRewardedVideoListener != null) && (!TextUtils.isEmpty((CharSequence)localObject2))) {
              this.mOnRewardedVideoListener.onRVAdClosed((String)localObject2);
            }
          }
          else if (i == SSAEnums.ProductType.Interstitial.ordinal())
          {
            Log.d(this.TAG, "onInterstitialAdClosed()");
            if (this.mOnInitInterstitialListener != null) {
              this.mOnInitInterstitialListener.onInterstitialClose();
            }
          }
          else if (i == SSAEnums.ProductType.OfferWall.ordinal())
          {
            Log.d(this.TAG, "onOWAdClosed()");
            if (this.mOnOfferWallListener != null) {
              this.mOnOfferWallListener.onOWAdClosed();
            }
          }
          paramAdUnitsState.adOpened(-1);
          paramAdUnitsState.setDisplayedDemandSourceName(null);
        }
        else
        {
          Log.d(this.TAG, "No ad was opened");
        }
        Object localObject5;
        if (paramAdUnitsState.isInterstitialInitSuccess())
        {
          Log.d(this.TAG, "onInterstitialAvailability(false)");
          localObject2 = this.mOnInitInterstitialListener;
          localObject2 = paramAdUnitsState.getInterstitialAppKey();
          localObject3 = paramAdUnitsState.getInterstitialUserId();
          localObject4 = paramAdUnitsState.getInterstitialExtraParams();
          localObject5 = this.TAG;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("initInterstitial(appKey:");
          localStringBuilder.append((String)localObject2);
          localStringBuilder.append(", userId:");
          localStringBuilder.append((String)localObject3);
          localStringBuilder.append(", extraParam:");
          localStringBuilder.append(localObject4);
          localStringBuilder.append(")");
          Log.d((String)localObject5, localStringBuilder.toString());
          initInterstitial((String)localObject2, (String)localObject3, (Map)localObject4, this.mOnInitInterstitialListener);
        }
        localObject2 = paramAdUnitsState.getRVAppKey();
        localObject3 = paramAdUnitsState.getRVUserId();
        Object localObject4 = this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo).iterator();
        while (((Iterator)localObject4).hasNext())
        {
          localObject5 = (DemandSource)((Iterator)localObject4).next();
          if (((DemandSource)localObject5).getDemandSourceInitState() == 2)
          {
            localObject5 = ((DemandSource)localObject5).getDemandSourceName();
            Log.d(this.TAG, "onRVNoMoreOffers()");
            this.mOnRewardedVideoListener.onRVNoMoreOffers((String)localObject5);
            initRewardedVideo((String)localObject2, (String)localObject3, (String)localObject5, this.mOnRewardedVideoListener);
          }
        }
        paramAdUnitsState.setShouldRestore(false);
      }
      this.mSavedState = paramAdUnitsState;
      return;
    }
  }
  
  public void resume()
  {
    if (Build.VERSION.SDK_INT > 10) {
      try
      {
        onResume();
        return;
      }
      catch (Throwable localThrowable)
      {
        String str = this.TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("WebViewController: onResume() - ");
        localStringBuilder.append(localThrowable);
        Logger.i(str, localStringBuilder.toString());
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewResume" });
      }
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
    else if (paramProductType == SSAEnums.ProductType.Interstitial)
    {
      this.mISmiss = true;
    }
    else if (paramProductType == SSAEnums.ProductType.OfferWall)
    {
      this.mOWmiss = true;
    }
    else if (paramProductType == SSAEnums.ProductType.OfferWallCredits)
    {
      this.mOWCreditsMiss = true;
    }
    paramString = this.TAG;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("setMissProduct(");
    localStringBuilder.append(paramProductType);
    localStringBuilder.append(")");
    Logger.i(paramString, localStringBuilder.toString());
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
      Object localObject = this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("unregisterConnectionReceiver - ");
      localStringBuilder.append(paramContext);
      Log.e((String)localObject, localStringBuilder.toString());
      localObject = new IronSourceAsyncHttpRequestTask();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("https://www.supersonicads.com/mobile/sdk5/log?method=");
      localStringBuilder.append(paramContext.getStackTrace()[0].getMethodName());
      ((IronSourceAsyncHttpRequestTask)localObject).execute(new String[] { localStringBuilder.toString() });
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramConsoleMessage.message());
      localStringBuilder.append(" -- From line ");
      localStringBuilder.append(paramConsoleMessage.lineNumber());
      localStringBuilder.append(" of ");
      localStringBuilder.append(paramConsoleMessage.sourceId());
      Logger.i("MyApplication", localStringBuilder.toString());
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("sendResults: ");
      localStringBuilder.append(this.udiaResults);
      Logger.i(str, localStringBuilder.toString());
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("adClicked(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      paramString = new SSAObj(paramString);
      str = paramString.getString("productType");
      if ((str.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) && (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())))
      {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialClick();
          }
        });
        return;
      }
      if ((str.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) && (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())))
      {
        paramString = paramString.getString("demandSourceName");
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            IronSourceWebView.this.mOnRewardedVideoListener.onRVAdClicked(paramString);
          }
        });
      }
    }
    
    @JavascriptInterface
    public void adCredited(final String paramString)
    {
      final String str1 = IronSourceWebView.this.PUB_TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("adCredited(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Log.d(str1, ((StringBuilder)localObject).toString());
      SSAObj localSSAObj = new SSAObj(paramString);
      str1 = localSSAObj.getString("credits");
      final boolean bool1 = false;
      final int i;
      if (str1 != null) {
        i = Integer.parseInt(str1);
      } else {
        i = 0;
      }
      String str5 = localSSAObj.getString("total");
      final int j;
      if (str5 != null) {
        j = Integer.parseInt(str5);
      } else {
        j = 0;
      }
      final String str3 = localSSAObj.getString("demandSourceName");
      final String str4 = localSSAObj.getString("productType");
      if (localSSAObj.getBoolean("externalPoll")) {
        str1 = IronSourceWebView.this.mOWCreditsAppKey;
      }
      for (localObject = IronSourceWebView.this.mOWCreditsUserId;; localObject = IronSourceWebView.this.mOWUserId)
      {
        break;
        str1 = IronSourceWebView.this.mOWAppKey;
      }
      final boolean bool2 = str4.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString());
      final String str2 = null;
      if (bool2)
      {
        if ((!localSSAObj.isNull("signature")) && (!localSSAObj.isNull("timestamp")) && (!localSSAObj.isNull("totalCreditsFlag")))
        {
          str2 = localSSAObj.getString("signature");
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(str5);
          localStringBuilder.append(str1);
          localStringBuilder.append((String)localObject);
          if (str2.equalsIgnoreCase(SDKUtils.getMD5(localStringBuilder.toString()))) {
            bool1 = true;
          } else {
            IronSourceWebView.this.responseBack(paramString, false, "Controller signature is not equal to SDK signature", null);
          }
          bool2 = localSSAObj.getBoolean("totalCreditsFlag");
          str2 = localSSAObj.getString("timestamp");
        }
        else
        {
          IronSourceWebView.this.responseBack(paramString, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
        }
      }
      else
      {
        bool2 = false;
        bool1 = false;
      }
      if (IronSourceWebView.this.shouldNotifyDeveloper(str4)) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (str4.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()))
            {
              IronSourceWebView.this.mOnRewardedVideoListener.onRVAdCredited(i, str3);
              return;
            }
            if ((str4.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) && (bool1) && (IronSourceWebView.this.mOnOfferWallListener.onOWAdCredited(i, j, bool2)) && (!TextUtils.isEmpty(str2)))
            {
              if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setLatestCompeltionsTime(str2, str1, this.val$userId))
              {
                IronSourceWebView.this.responseBack(paramString, true, null, null);
                return;
              }
              IronSourceWebView.this.responseBack(paramString, false, "Time Stamp could not be stored", null);
            }
          }
        });
      }
    }
    
    @JavascriptInterface
    public void adUnitsReady(final String paramString)
    {
      final String str = IronSourceWebView.this.TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("adUnitsReady(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Logger.i(str, ((StringBuilder)localObject).toString());
      str = new SSAObj(paramString).getString("demandSourceName");
      localObject = new AdUnitsReady(paramString);
      if (!((AdUnitsReady)localObject).isNumOfAdUnitsExist())
      {
        IronSourceWebView.this.responseBack(paramString, false, "Num Of Ad Units Do Not Exist", null);
        return;
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      paramString = ((AdUnitsReady)localObject).getProductType();
      if (IronSourceWebView.this.shouldNotifyDeveloper(paramString)) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            int i;
            if (Integer.parseInt(this.val$adUnitsReady.getNumOfAdUnits()) > 0) {
              i = 1;
            } else {
              i = 0;
            }
            if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()))
            {
              if (i != 0)
              {
                Log.d(IronSourceWebView.this.TAG, "onRVInitSuccess()");
                IronSourceWebView.this.mOnRewardedVideoListener.onRVInitSuccess(this.val$adUnitsReady, str);
                return;
              }
              IronSourceWebView.this.mOnRewardedVideoListener.onRVNoMoreOffers(str);
            }
          }
        });
      }
    }
    
    @JavascriptInterface
    public void alert(String paramString) {}
    
    @JavascriptInterface
    public void checkInstalledApps(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("checkInstalledApps(");
      ((StringBuilder)localObject1).append(paramString);
      ((StringBuilder)localObject1).append(")");
      Logger.i(str, ((StringBuilder)localObject1).toString());
      str = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      localObject1 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
      Object localObject2 = new SSAObj(paramString);
      paramString = ((SSAObj)localObject2).getString(IronSourceWebView.APP_IDS);
      localObject2 = ((SSAObj)localObject2).getString(IronSourceWebView.REQUEST_ID);
      paramString = IronSourceWebView.this.getAppsStatus(paramString, (String)localObject2);
      localObject2 = (String)paramString[0];
      if (((Boolean)paramString[1]).booleanValue())
      {
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          paramString = (String)localObject1;
          break label149;
        }
      }
      else if (!TextUtils.isEmpty(str))
      {
        paramString = str;
        break label149;
      }
      paramString = null;
      label149:
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject2, "onCheckInstalledAppsSuccess", "onCheckInstalledAppsFail");
        IronSourceWebView.this.injectJavascript(paramString);
      }
    }
    
    @JavascriptInterface
    public void createCalendarEvent(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("createCalendarEvent(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
    }
    
    @JavascriptInterface
    public void deleteFile(String paramString)
    {
      Object localObject = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("deleteFile(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i((String)localObject, localStringBuilder.toString());
      localObject = new SSAFile(paramString);
      if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, ((SSAFile)localObject).getPath()))
      {
        IronSourceWebView.this.responseBack(paramString, false, "File not exist", "1");
        return;
      }
      boolean bool = IronSourceStorageUtils.deleteFile(IronSourceWebView.this.mCacheDirectory, ((SSAFile)localObject).getPath(), ((SSAFile)localObject).getFile());
      IronSourceWebView.this.responseBack(paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void deleteFolder(String paramString)
    {
      Object localObject = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("deleteFolder(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i((String)localObject, localStringBuilder.toString());
      localObject = new SSAFile(paramString);
      if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, ((SSAFile)localObject).getPath()))
      {
        IronSourceWebView.this.responseBack(paramString, false, "Folder not exist", "1");
        return;
      }
      boolean bool = IronSourceStorageUtils.deleteFolder(IronSourceWebView.this.mCacheDirectory, ((SSAFile)localObject).getPath());
      IronSourceWebView.this.responseBack(paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void displayWebView(String paramString)
    {
      Object localObject1 = IronSourceWebView.this.TAG;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("displayWebView(");
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append(")");
      Logger.i((String)localObject1, ((StringBuilder)localObject2).toString());
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      paramString = new SSAObj(paramString);
      boolean bool1 = ((Boolean)paramString.get("display")).booleanValue();
      String str2 = paramString.getString("productType");
      boolean bool2 = paramString.getBoolean("standaloneView");
      String str1 = paramString.getString("demandSourceName");
      if (bool1)
      {
        IronSourceWebView.access$4902(IronSourceWebView.this, paramString.getBoolean("immersive"));
        IronSourceWebView.access$5002(IronSourceWebView.this, paramString.getBoolean("activityThemeTranslucent"));
        if (IronSourceWebView.this.getState() != IronSourceWebView.State.Display)
        {
          IronSourceWebView.this.setState(IronSourceWebView.State.Display);
          paramString = IronSourceWebView.this.TAG;
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("State: ");
          ((StringBuilder)localObject1).append(IronSourceWebView.this.mState);
          Logger.i(paramString, ((StringBuilder)localObject1).toString());
          Context localContext = IronSourceWebView.this.getCurrentActivityContext();
          localObject1 = IronSourceWebView.this.getOrientationState();
          int k = DeviceStatus.getApplicationRotation(localContext);
          if (bool2)
          {
            paramString = new ControllerView(localContext);
            paramString.addView(IronSourceWebView.this.mControllerLayout);
            paramString.showInterstitial(IronSourceWebView.this);
            return;
          }
          if (IronSourceWebView.this.mIsActivityThemeTranslucent) {
            localObject2 = new Intent(localContext, InterstitialActivity.class);
          } else {
            localObject2 = new Intent(localContext, ControllerActivity.class);
          }
          bool1 = SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(str2);
          int j = 0;
          int i;
          if (bool1)
          {
            paramString = (String)localObject1;
            if ("application".equals(localObject1)) {
              paramString = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
            }
            ((Intent)localObject2).putExtra("productType", SSAEnums.ProductType.RewardedVideo.toString());
            IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.RewardedVideo.ordinal());
            IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(str1);
            i = 1;
          }
          else if (SSAEnums.ProductType.OfferWall.toString().equalsIgnoreCase(str2))
          {
            ((Intent)localObject2).putExtra("productType", SSAEnums.ProductType.OfferWall.toString());
            IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
            paramString = (String)localObject1;
            i = j;
          }
          else
          {
            paramString = (String)localObject1;
            i = j;
            if (SSAEnums.ProductType.Interstitial.toString().equalsIgnoreCase(str2))
            {
              paramString = (String)localObject1;
              i = j;
              if ("application".equals(localObject1))
              {
                paramString = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
                i = j;
              }
            }
          }
          if ((i != 0) && (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString()))) {
            IronSourceWebView.this.mOnRewardedVideoListener.onRVAdOpened(str1);
          }
          ((Intent)localObject2).setFlags(536870912);
          ((Intent)localObject2).putExtra("immersive", IronSourceWebView.this.mIsImmersive);
          ((Intent)localObject2).putExtra("orientation_set_flag", paramString);
          ((Intent)localObject2).putExtra("rotation_set_flag", k);
          localContext.startActivity((Intent)localObject2);
          return;
        }
        paramString = IronSourceWebView.this.TAG;
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("State: ");
        ((StringBuilder)localObject1).append(IronSourceWebView.this.mState);
        Logger.i(paramString, ((StringBuilder)localObject1).toString());
        return;
      }
      IronSourceWebView.this.setState(IronSourceWebView.State.Gone);
      IronSourceWebView.this.closeWebView();
    }
    
    @JavascriptInterface
    public void getApplicationInfo(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("getApplicationInfo(");
      ((StringBuilder)localObject1).append(paramString);
      ((StringBuilder)localObject1).append(")");
      Logger.i(str, ((StringBuilder)localObject1).toString());
      str = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      localObject1 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
      Object localObject2 = new SSAObj(paramString);
      paramString = ((SSAObj)localObject2).getString("productType");
      localObject2 = ((SSAObj)localObject2).getString("demandSourceName");
      Object[] arrayOfObject = new Object[2];
      paramString = IronSourceWebView.this.getApplicationParams(paramString, (String)localObject2);
      localObject2 = (String)paramString[0];
      if (((Boolean)paramString[1]).booleanValue())
      {
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          paramString = (String)localObject1;
          break label153;
        }
      }
      else if (!TextUtils.isEmpty(str))
      {
        paramString = str;
        break label153;
      }
      paramString = null;
      label153:
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject2, "onGetApplicationInfoSuccess", "onGetApplicationInfoFail");
        IronSourceWebView.this.injectJavascript(paramString);
      }
    }
    
    @JavascriptInterface
    public void getAppsInstallTime(String paramString)
    {
      Object localObject1 = new SSAObj(paramString);
      int i;
      Object localObject3;
      Object localObject2;
      try
      {
        localObject1 = ((SSAObj)localObject1).getString("systemApps");
        localObject1 = DeviceStatus.getAppsInstallTime(IronSourceWebView.this.getContext(), Boolean.parseBoolean((String)localObject1)).toString();
        i = 0;
      }
      catch (Exception localException)
      {
        str = IronSourceWebView.this.TAG;
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("getAppsInstallTime failed(");
        ((StringBuilder)localObject3).append(localException.getLocalizedMessage());
        ((StringBuilder)localObject3).append(")");
        Logger.i(str, ((StringBuilder)localObject3).toString());
        localObject2 = localException.getLocalizedMessage();
        i = 1;
      }
      String str = null;
      if (i != 0)
      {
        localObject3 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
        paramString = str;
        if (!TextUtils.isEmpty((CharSequence)localObject3)) {
          paramString = (String)localObject3;
        }
      }
      else
      {
        localObject3 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
        paramString = str;
        if (!TextUtils.isEmpty((CharSequence)localObject3)) {
          paramString = (String)localObject3;
        }
      }
      if (!TextUtils.isEmpty(paramString))
      {
        try
        {
          str = URLDecoder.decode((String)localObject2, Charset.defaultCharset().name());
          localObject2 = str;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          localUnsupportedEncodingException.printStackTrace();
        }
        paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject2);
        IronSourceWebView.this.injectJavascript(paramString);
      }
    }
    
    @JavascriptInterface
    public void getCachedFilesMap(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("getCachedFilesMap(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Logger.i(str, ((StringBuilder)localObject).toString());
      str = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      if (!TextUtils.isEmpty(str))
      {
        localObject = new SSAObj(paramString);
        if (!((SSAObj)localObject).containsKey("path"))
        {
          IronSourceWebView.this.responseBack(paramString, false, "path key does not exist", null);
          return;
        }
        localObject = (String)((SSAObj)localObject).get("path");
        if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, (String)localObject))
        {
          IronSourceWebView.this.responseBack(paramString, false, "path file does not exist on disk", null);
          return;
        }
        paramString = IronSourceStorageUtils.getCachedFilesMap(IronSourceWebView.this.mCacheDirectory, (String)localObject);
        paramString = IronSourceWebView.this.generateJSToInject(str, paramString, "onGetCachedFilesMapSuccess", "onGetCachedFilesMapFail");
        IronSourceWebView.this.injectJavascript(paramString);
      }
    }
    
    @JavascriptInterface
    public void getControllerConfig(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getControllerConfig(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      paramString = new SSAObj(paramString).getString(IronSourceWebView.JSON_KEY_SUCCESS);
      if (!TextUtils.isEmpty(paramString))
      {
        str = SDKUtils.getControllerConfig();
        paramString = IronSourceWebView.this.generateJSToInject(paramString, str);
        IronSourceWebView.this.injectJavascript(paramString);
      }
    }
    
    @JavascriptInterface
    public void getDemandSourceState(String paramString)
    {
      String str1 = IronSourceWebView.this.TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("getMediationState(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Logger.i(str1, ((StringBuilder)localObject).toString());
      localObject = new SSAObj(paramString);
      str1 = ((SSAObj)localObject).getString("demandSourceName");
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
            else
            {
              str1 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
            }
            callJavaScriptFunction(str1, ((JSONObject)localObject).toString());
            return;
          }
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
      String str = IronSourceWebView.this.TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("getDeviceStatus(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Logger.i(str, ((StringBuilder)localObject).toString());
      str = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      paramString = IronSourceWebView.this.extractFailFunctionToCall(paramString);
      localObject = new Object[2];
      Object[] arrayOfObject = IronSourceWebView.this.getDeviceParams(IronSourceWebView.this.getContext());
      localObject = (String)arrayOfObject[0];
      if (((Boolean)arrayOfObject[1]).booleanValue())
      {
        if (!TextUtils.isEmpty(paramString)) {
          break label129;
        }
      }
      else if (!TextUtils.isEmpty(str))
      {
        paramString = str;
        break label129;
      }
      paramString = null;
      label129:
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject, "onGetDeviceStatusSuccess", "onGetDeviceStatusFail");
        IronSourceWebView.this.injectJavascript(paramString);
      }
    }
    
    @JavascriptInterface
    public void getDeviceVolume(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getDeviceVolume(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
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
      String str = IronSourceWebView.this.TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("getUDIA(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Logger.i(str, ((StringBuilder)localObject).toString());
      str = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("getByFlag"))
      {
        IronSourceWebView.this.responseBack(paramString, false, "getByFlag key does not exist", null);
        return;
      }
      int i = Integer.parseInt(((SSAObj)localObject).getString("getByFlag"));
      if (i == 0) {
        return;
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
        if (localObject[2] == '1')
        {
          this.udiaResults += 1;
          localObject = LocationService.getLastLocation(IronSourceWebView.this.getContext());
          if (localObject != null) {
            localJSONObject = new JSONObject();
          }
        }
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
        catch (JSONException paramString) {}
        this.udiaResults -= 1;
        return;
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
    }
    
    @JavascriptInterface
    public void getUserData(String paramString)
    {
      Object localObject1 = IronSourceWebView.this.TAG;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("getUserData(");
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append(")");
      Logger.i((String)localObject1, ((StringBuilder)localObject2).toString());
      localObject1 = new SSAObj(paramString);
      if (!((SSAObj)localObject1).containsKey("key"))
      {
        IronSourceWebView.this.responseBack(paramString, false, "key does not exist", null);
        return;
      }
      paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      localObject1 = ((SSAObj)localObject1).getString("key");
      localObject2 = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUserData((String)localObject1);
      localObject1 = IronSourceWebView.this.parseToJson((String)localObject1, (String)localObject2, null, null, null, null, null, null, null, false);
      paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject1);
      IronSourceWebView.this.injectJavascript(paramString);
    }
    
    @JavascriptInterface
    public void getUserUniqueId(String paramString)
    {
      Object localObject1 = IronSourceWebView.this.TAG;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("getUserUniqueId(");
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append(")");
      Logger.i((String)localObject1, ((StringBuilder)localObject2).toString());
      localObject1 = new SSAObj(paramString);
      if (!((SSAObj)localObject1).containsKey("productType"))
      {
        IronSourceWebView.this.responseBack(paramString, false, "productType does not exist", null);
        return;
      }
      paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      if (!TextUtils.isEmpty(paramString))
      {
        localObject1 = ((SSAObj)localObject1).getString("productType");
        localObject2 = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUniqueId((String)localObject1);
        localObject1 = IronSourceWebView.this.parseToJson("userUniqueId", (String)localObject2, "productType", (String)localObject1, null, null, null, null, null, false);
        paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject1, "onGetUserUniqueIdSuccess", "onGetUserUniqueIdFail");
        IronSourceWebView.this.injectJavascript(paramString);
      }
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("initController(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      paramString = new SSAObj(paramString);
      if (paramString.containsKey("stage"))
      {
        paramString = paramString.getString("stage");
        if ("ready".equalsIgnoreCase(paramString)) {
          handleControllerStageReady();
        } else if ("loaded".equalsIgnoreCase(paramString)) {
          handleControllerStageLoaded();
        } else if ("failed".equalsIgnoreCase(paramString)) {
          handleControllerStageFailed();
        } else {
          Logger.i(IronSourceWebView.this.TAG, "No STAGE mentioned! Should not get here!");
        }
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (Build.VERSION.SDK_INT >= 16) {
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
          }
        });
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
            Object localObject1 = IronSourceWebView.this.TAG;
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("moatAPI(");
            ((StringBuilder)localObject2).append(paramString);
            ((StringBuilder)localObject2).append(")");
            Logger.i((String)localObject1, ((StringBuilder)localObject2).toString());
            localObject1 = new SSAObj(paramString);
            IronSourceWebView.this.mMoatJsAdapter.call(((SSAObj)localObject1).toString(), new IronSourceWebView.JSInterface.JSCallbackTask(IronSourceWebView.JSInterface.this), IronSourceWebView.this.getWebview());
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            Object localObject2 = IronSourceWebView.this.TAG;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("moatAPI failed with exception ");
            localStringBuilder.append(localException.getMessage());
            Logger.i((String)localObject2, localStringBuilder.toString());
          }
        }
      });
    }
    
    @JavascriptInterface
    public void onAdWindowsClosed(final String paramString)
    {
      Object localObject = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onAdWindowsClosed(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i((String)localObject, localStringBuilder.toString());
      IronSourceWebView.this.mSavedState.adClosed();
      IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(null);
      localObject = new SSAObj(paramString);
      paramString = ((SSAObj)localObject).getString("productType");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
        Log.d(IronSourceWebView.this.PUB_TAG, "onRVAdClosed()");
      } else if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
        Log.d(IronSourceWebView.this.PUB_TAG, "onISAdClosed()");
      } else if (paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
        Log.d(IronSourceWebView.this.PUB_TAG, "onOWAdClosed()");
      }
      if ((IronSourceWebView.this.shouldNotifyDeveloper(paramString)) && (paramString != null)) {
        IronSourceWebView.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()))
            {
              IronSourceWebView.this.mOnRewardedVideoListener.onRVAdClosed(this.val$demandSourceName);
              return;
            }
            if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString()))
            {
              IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialClose();
              return;
            }
            if (paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
              IronSourceWebView.this.mOnOfferWallListener.onOWAdClosed();
            }
          }
        });
      }
    }
    
    @JavascriptInterface
    public void onGenericFunctionFail(String paramString)
    {
      final String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGenericFunctionFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      if (IronSourceWebView.this.mOnGenericFunctionListener == null)
      {
        Logger.d(IronSourceWebView.this.TAG, "genericFunctionListener was not found");
        return;
      }
      str = new SSAObj(paramString).getString("errMsg");
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGenericFunctionSuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGetApplicationInfoFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetApplicationInfoFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetApplicationInfoSuccess(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGetApplicationInfoSuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetApplicationInfoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapFail(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGetCachedFilesMapFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetCachedFilesMapFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapSuccess(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGetCachedFilesMapSuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetCachedFilesMapSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusFail(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGetDeviceStatusFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetDeviceStatusFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusSuccess(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGetDeviceStatusSuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetDeviceStatusSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetUDIAFail(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGetUDIAFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
    }
    
    @JavascriptInterface
    public void onGetUDIASuccess(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGetUDIASuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
    }
    
    @JavascriptInterface
    public void onGetUserCreditsFail(String paramString)
    {
      final String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGetUserCreditsFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      str = new SSAObj(paramString).getString("errMsg");
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGetUserUniqueIdFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
    }
    
    @JavascriptInterface
    public void onGetUserUniqueIdSuccess(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onGetUserUniqueIdSuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
    }
    
    @JavascriptInterface
    public void onInitInterstitialFail(String paramString)
    {
      final String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onInitInterstitialFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceWebView.this.mSavedState.setInterstitialInitSuccess(false);
      str = new SSAObj(paramString).getString("errMsg");
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
              str2 = IronSourceWebView.this.TAG;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("onInterstitialInitFail(message:");
              localStringBuilder.append(str1);
              localStringBuilder.append(")");
              Log.d(str2, localStringBuilder.toString());
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
      final String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onInitOfferWallFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(false);
      str = new SSAObj(paramString).getString("errMsg");
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
              str2 = IronSourceWebView.this.TAG;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("onOfferWallInitFail(message:");
              localStringBuilder.append(str1);
              localStringBuilder.append(")");
              Log.d(str2, localStringBuilder.toString());
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
      final String str = IronSourceWebView.this.TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("onInitRewardedVideoFail(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Logger.i(str, ((StringBuilder)localObject).toString());
      localObject = new SSAObj(paramString);
      str = ((SSAObj)localObject).getString("errMsg");
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
            str2 = IronSourceWebView.this.TAG;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("onRVInitFail(message:");
            localStringBuilder.append(str);
            localStringBuilder.append(")");
            Log.d(str2, localStringBuilder.toString());
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
      Object localObject = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onInitRewardedVideoSuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i((String)localObject, localStringBuilder.toString());
      localObject = new SSABCParameters(paramString);
      IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSSABCParameters((SSABCParameters)localObject);
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onInitRewardedVideoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onLoadInterstitialFail(String paramString)
    {
      final String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onLoadInterstitialFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      str = new SSAObj(paramString).getString("errMsg");
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onLoadInterstitialSuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onOfferWallGeneric(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.mOnOfferWallListener.onOWGeneric("", "");
      }
    }
    
    @JavascriptInterface
    public void onShowInterstitialFail(String paramString)
    {
      final String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onShowInterstitialFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      setInterstitialAvailability(false);
      str = new SSAObj(paramString).getString("errMsg");
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onShowInterstitialSuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
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
      final String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onShowOfferWallFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      str = new SSAObj(paramString).getString("errMsg");
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
      final String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onShowOfferWallSuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
      str = SDKUtils.getValueFromJsonObject(paramString, "placementId");
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
      final String str = IronSourceWebView.this.TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("onShowRewardedVideoFail(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Logger.i(str, ((StringBuilder)localObject).toString());
      localObject = new SSAObj(paramString);
      str = ((SSAObj)localObject).getString("errMsg");
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
            str2 = IronSourceWebView.this.TAG;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("onRVShowFail(message:");
            localStringBuilder.append(str);
            localStringBuilder.append(")");
            Log.d(str2, localStringBuilder.toString());
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onShowRewardedVideoSuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onShowRewardedVideoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onUDIAFail(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onUDIAFail(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
    }
    
    @JavascriptInterface
    public void onUDIASuccess(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onUDIASuccess(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
    }
    
    @JavascriptInterface
    public void onVideoStatusChanged(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onVideoStatusChanged(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Log.d(str, localStringBuilder.toString());
      paramString = new SSAObj(paramString);
      str = paramString.getString("productType");
      if ((IronSourceWebView.this.mVideoEventsListener != null) && (!TextUtils.isEmpty(str)) && (SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(str)))
      {
        paramString = paramString.getString("status");
        if ("started".equalsIgnoreCase(paramString))
        {
          IronSourceWebView.this.mVideoEventsListener.onVideoStarted();
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
        str = IronSourceWebView.this.TAG;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("onVideoStatusChanged: unknown status: ");
        localStringBuilder.append(paramString);
        Logger.i(str, localStringBuilder.toString());
      }
    }
    
    @JavascriptInterface
    public void openUrl(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("openUrl(");
      ((StringBuilder)localObject1).append(paramString);
      ((StringBuilder)localObject1).append(")");
      Logger.i(str, ((StringBuilder)localObject1).toString());
      localObject1 = new SSAObj(paramString);
      str = ((SSAObj)localObject1).getString("url");
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
        if (((String)localObject2).equalsIgnoreCase("store"))
        {
          localObject2 = new Intent((Context)localObject1, OpenUrlActivity.class);
          ((Intent)localObject2).putExtra(IronSourceWebView.EXTERNAL_URL, str);
          ((Intent)localObject2).putExtra(IronSourceWebView.IS_STORE, true);
          ((Intent)localObject2).putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, true);
          ((Context)localObject1).startActivity((Intent)localObject2);
          return;
        }
      }
      catch (Exception localException)
      {
        IronSourceWebView.this.responseBack(paramString, false, localException.getMessage(), null);
        localException.printStackTrace();
      }
    }
    
    @JavascriptInterface
    public void postAdEventNotification(String paramString)
    {
      try
      {
        final String str1 = IronSourceWebView.this.TAG;
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("postAdEventNotification(");
        ((StringBuilder)localObject1).append(paramString);
        ((StringBuilder)localObject1).append(")");
        Logger.i(str1, ((StringBuilder)localObject1).toString());
        Object localObject2 = new SSAObj(paramString);
        str1 = ((SSAObj)localObject2).getString("eventName");
        if (TextUtils.isEmpty(str1))
        {
          IronSourceWebView.this.responseBack(paramString, false, "eventName does not exist", null);
          return;
        }
        localObject1 = ((SSAObj)localObject2).getString("dsName");
        final JSONObject localJSONObject = (JSONObject)((SSAObj)localObject2).get("extData");
        localObject2 = ((SSAObj)localObject2).getString("productType");
        if (IronSourceWebView.this.shouldNotifyDeveloper((String)localObject2))
        {
          paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
          if (!TextUtils.isEmpty(paramString))
          {
            String str2 = IronSourceWebView.this.parseToJson("productType", (String)localObject2, "eventName", str1, null, null, null, null, null, false);
            paramString = IronSourceWebView.this.generateJSToInject(paramString, str2, "postAdEventNotificationSuccess", "postAdEventNotificationFail");
            IronSourceWebView.this.injectJavascript(paramString);
          }
          IronSourceWebView.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              if (this.val$productType.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString()))
              {
                IronSourceWebView.this.mOnInitInterstitialListener.onInterstitialEventNotificationReceived(str1, localJSONObject);
                return;
              }
              if (this.val$productType.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()))
              {
                IronSourceWebView.this.mOnRewardedVideoListener.onRVEventNotificationReceived(str1, this.val$demandSourceName, localJSONObject);
                return;
              }
              if (this.val$productType.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
                IronSourceWebView.this.mOnOfferWallListener.onOfferwallEventNotificationReceived(str1, localJSONObject);
              }
            }
          });
          return;
        }
        IronSourceWebView.this.responseBack(paramString, false, "productType does not exist", null);
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    
    @JavascriptInterface
    public void removeCloseEventHandler(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("removeCloseEventHandler(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      if (IronSourceWebView.this.mCloseEventTimer != null) {
        IronSourceWebView.this.mCloseEventTimer.cancel();
      }
      IronSourceWebView.access$702(IronSourceWebView.this, true);
    }
    
    @JavascriptInterface
    public void saveFile(String paramString)
    {
      String str1 = IronSourceWebView.this.TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("saveFile(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Logger.i(str1, ((StringBuilder)localObject).toString());
      localObject = new SSAFile(paramString);
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
      if (IronSourceStorageUtils.isFileCached(IronSourceWebView.this.mCacheDirectory, (SSAFile)localObject))
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
      paramString = ((SSAFile)localObject).getLastUpdateTime();
      if (paramString != null)
      {
        String str2 = String.valueOf(paramString);
        if (!TextUtils.isEmpty(str2))
        {
          str1 = ((SSAFile)localObject).getPath();
          paramString = str1;
          if (str1.contains("/"))
          {
            paramString = ((SSAFile)localObject).getPath().split("/");
            paramString = paramString[(paramString.length - 1)];
          }
          IronSourceSharedPrefHelper.getSupersonicPrefHelper().setCampaignLastUpdate(paramString, str2);
        }
      }
      IronSourceWebView.this.downloadManager.downloadFile((SSAFile)localObject);
    }
    
    @JavascriptInterface
    public void setAllowFileAccessFromFileURLs(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("setAllowFileAccessFromFileURLs(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      final boolean bool = new SSAObj(paramString).getBoolean("allowFileAccess");
      IronSourceWebView.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (Build.VERSION.SDK_INT >= 16) {
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
        }
      });
    }
    
    @JavascriptInterface
    public void setBackButtonState(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("setBackButtonState(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      paramString = new SSAObj(paramString).getString("state");
      IronSourceSharedPrefHelper.getSupersonicPrefHelper().setBackButtonState(paramString);
    }
    
    @JavascriptInterface
    public void setForceClose(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("setForceClose(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Logger.i(str, ((StringBuilder)localObject).toString());
      paramString = new SSAObj(paramString);
      str = paramString.getString("width");
      localObject = paramString.getString("height");
      IronSourceWebView.access$402(IronSourceWebView.this, Integer.parseInt(str));
      IronSourceWebView.access$502(IronSourceWebView.this, Integer.parseInt((String)localObject));
      IronSourceWebView.access$602(IronSourceWebView.this, paramString.getString("position"));
    }
    
    @JavascriptInterface
    public void setMixedContentAlwaysAllow(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("setMixedContentAlwaysAllow(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("setOrientation(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
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
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("setStoreSearchKeys(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSearchKeys(paramString);
    }
    
    @JavascriptInterface
    public void setUserData(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("setUserData(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Logger.i(str, ((StringBuilder)localObject).toString());
      localObject = new SSAObj(paramString);
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
      str = ((SSAObj)localObject).getString("key");
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
      String str = IronSourceWebView.this.TAG;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("setUserUniqueId(");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      Logger.i(str, ((StringBuilder)localObject).toString());
      localObject = new SSAObj(paramString);
      if ((((SSAObj)localObject).containsKey("userUniqueId")) && (((SSAObj)localObject).containsKey("productType")))
      {
        str = ((SSAObj)localObject).getString("userUniqueId");
        localObject = ((SSAObj)localObject).getString("productType");
        if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUniqueId(str, (String)localObject))
        {
          IronSourceWebView.this.responseBack(paramString, true, null, null);
          return;
        }
        IronSourceWebView.this.responseBack(paramString, false, "setUserUniqueId failed", null);
        return;
      }
      IronSourceWebView.this.responseBack(paramString, false, "uniqueId or productType does not exist", null);
    }
    
    @JavascriptInterface
    public void setWebviewBackgroundColor(String paramString)
    {
      String str = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("setWebviewBackgroundColor(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i(str, localStringBuilder.toString());
      IronSourceWebView.this.setWebviewBackground(paramString);
    }
    
    @JavascriptInterface
    public void toggleUDIA(String paramString)
    {
      Object localObject = IronSourceWebView.this.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("toggleUDIA(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      Logger.i((String)localObject, localStringBuilder.toString());
      localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("toggle"))
      {
        IronSourceWebView.this.responseBack(paramString, false, "toggle key does not exist", null);
        return;
      }
      int i = Integer.parseInt(((SSAObj)localObject).getString("toggle"));
      if (i == 0) {
        return;
      }
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
        String str;
        if (paramBoolean) {
          str = IronSourceWebView.JSON_KEY_SUCCESS;
        } else {
          str = IronSourceWebView.JSON_KEY_FAIL;
        }
        localSSAObj.put(str, paramString1);
        localSSAObj.put("data", paramString2);
        IronSourceWebView.this.responseBack(localSSAObj.toString(), paramBoolean, null, null);
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
      if (paramMotionEvent.getAction() == 1)
      {
        float f1 = paramMotionEvent.getX();
        float f2 = paramMotionEvent.getY();
        paramView = IronSourceWebView.this.TAG;
        paramMotionEvent = new StringBuilder();
        paramMotionEvent.append("X:");
        int i = (int)f1;
        paramMotionEvent.append(i);
        paramMotionEvent.append(" Y:");
        int j = (int)f2;
        paramMotionEvent.append(j);
        Logger.i(paramView, paramMotionEvent.toString());
        int i1 = DeviceStatus.getDeviceWidth();
        int n = DeviceStatus.getDeviceHeight();
        paramView = IronSourceWebView.this.TAG;
        paramMotionEvent = new StringBuilder();
        paramMotionEvent.append("Width:");
        paramMotionEvent.append(i1);
        paramMotionEvent.append(" Height:");
        paramMotionEvent.append(n);
        Logger.i(paramView, paramMotionEvent.toString());
        int k = SDKUtils.dpToPx(IronSourceWebView.this.mHiddenForceCloseWidth);
        int m = SDKUtils.dpToPx(IronSourceWebView.this.mHiddenForceCloseHeight);
        if ("top-right".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
          i = i1 - i;
        } else if (!"top-left".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
          if ("bottom-right".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation))
          {
            i = i1 - i;
            j = n - j;
          }
          else if ("bottom-left".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation))
          {
            j = n - j;
          }
          else
          {
            i = 0;
            j = 0;
          }
        }
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
              String str = IronSourceWebView.this.TAG;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Close Event Timer Tick ");
              localStringBuilder.append(paramAnonymousLong);
              Logger.i(str, localStringBuilder.toString());
            }
          }.start());
        }
      }
      return false;
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString2);
      localStringBuilder.append(" ");
      localStringBuilder.append(paramString1);
      Logger.i("onReceivedError", localStringBuilder.toString());
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }
    
    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
    {
      Logger.i("shouldInterceptRequest", paramString);
      try
      {
        bool = new URL(paramString).getFile().contains("mraid.js");
      }
      catch (MalformedURLException localMalformedURLException)
      {
        boolean bool;
        Object localObject;
        File localFile;
        for (;;) {}
      }
      bool = false;
      if (bool)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("file://");
        ((StringBuilder)localObject).append(IronSourceWebView.this.mCacheDirectory);
        ((StringBuilder)localObject).append(File.separator);
        ((StringBuilder)localObject).append("mraid.js");
        localObject = ((StringBuilder)localObject).toString();
        localFile = new File((String)localObject);
      }
      try
      {
        new FileInputStream(localFile);
        localObject = new WebResourceResponse("text/javascript", "UTF-8", getClass().getResourceAsStream((String)localObject));
        return localObject;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        for (;;) {}
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
