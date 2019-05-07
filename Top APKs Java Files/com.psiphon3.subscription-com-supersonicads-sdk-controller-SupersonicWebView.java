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
  private String PUB_TAG = "Supersonic";
  private String TAG = SupersonicWebView.class.getSimpleName();
  private DownloadManager downloadManager;
  private Boolean isKitkatAndAbove = null;
  private boolean isRemoveCloseEventHandler;
  private String mBCAppKey;
  private Map mBCExtraParameters;
  private String mBCUserId;
  private String mCacheDirectory;
  private OnWebViewControllerChangeListener mChangeListener;
  private CountDownTimer mCloseEventTimer;
  private BroadcastReceiver mConnectionReceiver = new SupersonicWebView.7(this);
  private String mControllerKeyPressed = "interrupt";
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
  private Map mISExtraParameters;
  private String mISUserId;
  private boolean mISmiss;
  private Boolean mIsInterstitialAvailable = null;
  private FrameLayout mLayout;
  private CountDownTimer mLoadControllerTimer;
  private String mOWAppKey;
  private String mOWCreditsAppKey;
  private boolean mOWCreditsMiss;
  private String mOWCreditsUserId;
  private Map mOWExtraParameters;
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
  
  private String flatMapToJsonAsString(Map paramMap)
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
    //   0: new 616	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 617	org/json/JSONObject:<init>	()V
    //   7: astore 7
    //   9: ldc_w 683
    //   12: astore 5
    //   14: ldc_w 683
    //   17: astore 4
    //   19: aconst_null
    //   20: astore 6
    //   22: aconst_null
    //   23: astore_3
    //   24: aload_1
    //   25: invokestatic 689	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   28: ifne +329 -> 357
    //   31: aload_1
    //   32: getstatic 537	com/supersonicads/sdk/data/SSAEnums$ProductType:BrandConnect	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   35: invokevirtual 690	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   38: invokevirtual 693	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   41: ifeq +219 -> 260
    //   44: aload_0
    //   45: getfield 340	com/supersonicads/sdk/controller/SupersonicWebView:mBCAppKey	Ljava/lang/String;
    //   48: astore 5
    //   50: aload_0
    //   51: getfield 343	com/supersonicads/sdk/controller/SupersonicWebView:mBCUserId	Ljava/lang/String;
    //   54: astore 4
    //   56: aload_0
    //   57: getfield 347	com/supersonicads/sdk/controller/SupersonicWebView:mBCExtraParameters	Ljava/util/Map;
    //   60: astore_3
    //   61: aload 7
    //   63: ldc_w 592
    //   66: aload_1
    //   67: invokevirtual 695	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   70: pop
    //   71: iconst_0
    //   72: istore_2
    //   73: aload 4
    //   75: invokestatic 689	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   78: ifne +316 -> 394
    //   81: aload 7
    //   83: ldc_w 562
    //   86: invokestatic 652	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   89: aload 4
    //   91: invokestatic 652	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   94: invokevirtual 695	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   97: pop
    //   98: aload 5
    //   100: invokestatic 689	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   103: ifne +325 -> 428
    //   106: aload 7
    //   108: ldc_w 554
    //   111: invokestatic 652	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   114: aload 5
    //   116: invokestatic 652	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   119: invokevirtual 695	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   122: pop
    //   123: aload_3
    //   124: ifnull +309 -> 433
    //   127: aload_3
    //   128: invokeinterface 697 1 0
    //   133: ifne +300 -> 433
    //   136: aload_3
    //   137: invokeinterface 621 1 0
    //   142: invokeinterface 627 1 0
    //   147: astore_1
    //   148: aload_1
    //   149: invokeinterface 633 1 0
    //   154: ifeq +279 -> 433
    //   157: aload_1
    //   158: invokeinterface 637 1 0
    //   163: checkcast 639	java/util/Map$Entry
    //   166: astore_3
    //   167: aload_3
    //   168: invokeinterface 642 1 0
    //   173: checkcast 644	java/lang/String
    //   176: ldc_w 699
    //   179: invokevirtual 693	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   182: ifeq +16 -> 198
    //   185: aload_0
    //   186: aload_3
    //   187: invokeinterface 647 1 0
    //   192: checkcast 644	java/lang/String
    //   195: invokespecial 702	com/supersonicads/sdk/controller/SupersonicWebView:setWebviewCache	(Ljava/lang/String;)V
    //   198: aload 7
    //   200: aload_3
    //   201: invokeinterface 642 1 0
    //   206: checkcast 644	java/lang/String
    //   209: invokestatic 652	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   212: aload_3
    //   213: invokeinterface 647 1 0
    //   218: checkcast 644	java/lang/String
    //   221: invokestatic 652	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   224: invokevirtual 695	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   227: pop
    //   228: goto -80 -> 148
    //   231: astore_3
    //   232: aload_3
    //   233: invokevirtual 705	org/json/JSONException:printStackTrace	()V
    //   236: new 707	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   239: dup
    //   240: invokespecial 708	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   243: iconst_1
    //   244: anewarray 644	java/lang/String
    //   247: dup
    //   248: iconst_0
    //   249: ldc_w 710
    //   252: aastore
    //   253: invokevirtual 714	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   256: pop
    //   257: goto -109 -> 148
    //   260: aload_1
    //   261: getstatic 549	com/supersonicads/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   264: invokevirtual 690	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   267: invokevirtual 693	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   270: ifeq +23 -> 293
    //   273: aload_0
    //   274: getfield 357	com/supersonicads/sdk/controller/SupersonicWebView:mISAppKey	Ljava/lang/String;
    //   277: astore 5
    //   279: aload_0
    //   280: getfield 360	com/supersonicads/sdk/controller/SupersonicWebView:mISUserId	Ljava/lang/String;
    //   283: astore 4
    //   285: aload_0
    //   286: getfield 363	com/supersonicads/sdk/controller/SupersonicWebView:mISExtraParameters	Ljava/util/Map;
    //   289: astore_3
    //   290: goto -229 -> 61
    //   293: aload_1
    //   294: getstatic 579	com/supersonicads/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   297: invokevirtual 690	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   300: invokevirtual 693	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   303: ifeq -242 -> 61
    //   306: aload_0
    //   307: getfield 374	com/supersonicads/sdk/controller/SupersonicWebView:mOWAppKey	Ljava/lang/String;
    //   310: astore 5
    //   312: aload_0
    //   313: getfield 377	com/supersonicads/sdk/controller/SupersonicWebView:mOWUserId	Ljava/lang/String;
    //   316: astore 4
    //   318: aload_0
    //   319: getfield 380	com/supersonicads/sdk/controller/SupersonicWebView:mOWExtraParameters	Ljava/util/Map;
    //   322: astore_3
    //   323: goto -262 -> 61
    //   326: astore_1
    //   327: aload_1
    //   328: invokevirtual 705	org/json/JSONException:printStackTrace	()V
    //   331: new 707	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   334: dup
    //   335: invokespecial 708	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   338: iconst_1
    //   339: anewarray 644	java/lang/String
    //   342: dup
    //   343: iconst_0
    //   344: ldc_w 716
    //   347: aastore
    //   348: invokevirtual 714	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   351: pop
    //   352: iconst_0
    //   353: istore_2
    //   354: goto -281 -> 73
    //   357: iconst_1
    //   358: istore_2
    //   359: aload 6
    //   361: astore_3
    //   362: goto -289 -> 73
    //   365: astore_1
    //   366: aload_1
    //   367: invokevirtual 705	org/json/JSONException:printStackTrace	()V
    //   370: new 707	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   373: dup
    //   374: invokespecial 708	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   377: iconst_1
    //   378: anewarray 644	java/lang/String
    //   381: dup
    //   382: iconst_0
    //   383: ldc_w 718
    //   386: aastore
    //   387: invokevirtual 714	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   390: pop
    //   391: goto -293 -> 98
    //   394: iconst_1
    //   395: istore_2
    //   396: goto -298 -> 98
    //   399: astore_1
    //   400: aload_1
    //   401: invokevirtual 705	org/json/JSONException:printStackTrace	()V
    //   404: new 707	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   407: dup
    //   408: invokespecial 708	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   411: iconst_1
    //   412: anewarray 644	java/lang/String
    //   415: dup
    //   416: iconst_0
    //   417: ldc_w 720
    //   420: aastore
    //   421: invokevirtual 714	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   424: pop
    //   425: goto -302 -> 123
    //   428: iconst_1
    //   429: istore_2
    //   430: goto -307 -> 123
    //   433: iconst_2
    //   434: anewarray 216	java/lang/Object
    //   437: dup
    //   438: iconst_0
    //   439: aload 7
    //   441: invokevirtual 673	org/json/JSONObject:toString	()Ljava/lang/String;
    //   444: aastore
    //   445: dup
    //   446: iconst_1
    //   447: iload_2
    //   448: invokestatic 726	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   451: aastore
    //   452: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	453	0	this	SupersonicWebView
    //   0	453	1	paramString	String
    //   72	376	2	bool	boolean
    //   23	190	3	localObject1	Object
    //   231	2	3	localJSONException	JSONException
    //   289	73	3	localObject2	Object
    //   17	300	4	str1	String
    //   12	299	5	str2	String
    //   20	340	6	localObject3	Object
    //   7	433	7	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   198	228	231	org/json/JSONException
    //   61	71	326	org/json/JSONException
    //   81	98	365	org/json/JSONException
    //   106	123	399	org/json/JSONException
  }
  
  private Object[] getAppsStatus(String paramString1, String paramString2)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      if ((TextUtils.isEmpty(paramString1)) || (paramString1.equalsIgnoreCase("null"))) {
        break label265;
      }
      if ((TextUtils.isEmpty(paramString2)) || (paramString2.equalsIgnoreCase("null"))) {
        break label247;
      }
      localList = getBaseContext().getPackageManager().getInstalledApplications(0);
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
        label247:
        label265:
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
  
  private Context getBaseContext()
  {
    return ((MutableContextWrapper)getContext()).getBaseContext();
  }
  
  private Object[] getDeviceParams(Context paramContext)
  {
    Object localObject = DeviceProperties.getInstance(paramContext);
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("appOrientation", "none");
        str = ((DeviceProperties)localObject).getDeviceOem();
        if (str != null) {
          localJSONObject.put(SDKUtils.encodeString("deviceOEM"), SDKUtils.encodeString(str));
        }
        str = ((DeviceProperties)localObject).getDeviceModel();
        if (str != null)
        {
          localJSONObject.put(SDKUtils.encodeString("deviceModel"), SDKUtils.encodeString(str));
          bool2 = false;
          bool1 = bool2;
        }
      }
      catch (JSONException paramContext)
      {
        String str;
        boolean bool3;
        bool1 = false;
      }
      try
      {
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
        if (str != null)
        {
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
        }
      }
      catch (JSONException paramContext)
      {
        for (;;) {}
      }
      try
      {
        paramContext = SDKUtils.getPackageName(getBaseContext());
        if (!TextUtils.isEmpty(paramContext)) {
          localJSONObject.put(SDKUtils.encodeString("bundleId"), SDKUtils.encodeString(paramContext));
        }
        paramContext = String.valueOf(SDKUtils.getDeviceScale());
        if (!TextUtils.isEmpty(paramContext)) {
          localJSONObject.put(SDKUtils.encodeString("deviceScreenScale"), SDKUtils.encodeString(paramContext));
        }
        paramContext = String.valueOf(DeviceStatus.isRootedDevice());
        bool2 = bool1;
        if (!TextUtils.isEmpty(paramContext))
        {
          localJSONObject.put(SDKUtils.encodeString("unLocked"), SDKUtils.encodeString(paramContext));
          bool2 = bool1;
        }
        return new Object[] { localJSONObject.toString(), Boolean.valueOf(bool2) };
      }
      catch (JSONException paramContext)
      {
        break label771;
      }
      boolean bool2 = true;
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
      boolean bool1 = true;
      continue;
      label771:
      paramContext.printStackTrace();
      new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
      bool2 = bool1;
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
    new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=contextIsNotActivity" });
  }
  
  private String mapToJson(Map paramMap)
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
  
  private void sendProductErrorMessage(SSAEnums.ProductType paramProductType)
  {
    String str = "";
    switch (8.$SwitchMap$com$supersonicads$sdk$data$SSAEnums$ProductType[paramProductType.ordinal()])
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
    boolean bool4 = true;
    boolean bool2 = true;
    boolean bool1 = true;
    boolean bool5 = false;
    boolean bool3 = false;
    if (paramString == null)
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
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString()))
      {
        if (this.mOnRewardedVideoListener != null) {}
        for (bool1 = bool4;; bool1 = false) {
          break;
        }
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
        break label152;
      }
      bool1 = bool5;
    } while (!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWallCredits.toString()));
    label152:
    if (this.mOnOfferWallListener != null) {}
    for (bool1 = bool2;; bool1 = false) {
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
  
  public void initBrandConnect(String paramString1, String paramString2, Map paramMap, OnRewardedVideoListener paramOnRewardedVideoListener)
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
  
  public void initInterstitial(String paramString1, String paramString2, Map paramMap, OnInterstitialListener paramOnInterstitialListener)
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
  
  public void initOfferWall(String paramString1, String paramString2, Map paramMap, OnOfferWallListener paramOnOfferWallListener)
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
      new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewResume" });
    }
  }
  
  public void runGenericFunction(String paramString, Map paramMap, OnGenericFunctionListener paramOnGenericFunctionListener)
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
  
  public void showOfferWall(Map paramMap)
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
      new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
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
      String str3 = null;
      boolean bool1 = false;
      Log.d(SupersonicWebView.this.PUB_TAG, "adCredited(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str1 = ((SSAObj)localObject).getString("credits");
      int i;
      String str5;
      int j;
      label91:
      String str4;
      String str2;
      if (str1 != null)
      {
        i = Integer.parseInt(str1);
        str5 = ((SSAObj)localObject).getString("total");
        if (str5 == null) {
          break label190;
        }
        j = Integer.parseInt(str5);
        str4 = ((SSAObj)localObject).getString("productType");
        if (!((SSAObj)localObject).getBoolean("externalPoll")) {
          break label195;
        }
        str1 = SupersonicWebView.this.mOWCreditsAppKey;
        str2 = SupersonicWebView.this.mOWCreditsUserId;
        label128:
        if (!str4.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
          break label354;
        }
        if ((!((SSAObj)localObject).isNull("signature")) && (!((SSAObj)localObject).isNull("timestamp")) && (!((SSAObj)localObject).isNull("totalCreditsFlag"))) {
          break label216;
        }
        SupersonicWebView.this.responseBack(paramString, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
      }
      label190:
      label195:
      label216:
      label260:
      label354:
      label361:
      for (;;)
      {
        return;
        i = 0;
        break;
        j = 0;
        break label91;
        str1 = SupersonicWebView.this.mOWAppKey;
        str2 = SupersonicWebView.this.mOWUserId;
        break label128;
        boolean bool2;
        if (((SSAObj)localObject).getString("signature").equalsIgnoreCase(SDKUtils.getMD5(str5 + str1 + str2)))
        {
          bool1 = true;
          bool2 = ((SSAObj)localObject).getBoolean("totalCreditsFlag");
          str3 = ((SSAObj)localObject).getString("timestamp");
        }
        for (;;)
        {
          if (!SupersonicWebView.this.shouldNotifyDeveloper(str4)) {
            break label361;
          }
          localObject = SupersonicWebView.this.getBaseContext();
          if (!(localObject instanceof Activity)) {
            break;
          }
          ((Activity)localObject).runOnUiThread(new SupersonicWebView.JSInterface.2(this, str4, i, bool1, j, bool2, str3, str1, str2, paramString));
          return;
          SupersonicWebView.this.responseBack(paramString, false, "Controller signature is not equal to SDK signature", null);
          break label260;
          bool1 = false;
          bool2 = false;
        }
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
      Object localObject = new SSAObj(paramString);
      paramString = ((SSAObj)localObject).getString(SupersonicWebView.APP_IDS);
      localObject = ((SSAObj)localObject).getString(SupersonicWebView.REQUEST_ID);
      paramString = SupersonicWebView.this.getAppsStatus(paramString, (String)localObject);
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
          paramString = SupersonicWebView.this.generateJSToInject(paramString, (String)localObject, "onCheckInstalledAppsSuccess", "onCheckInstalledAppsFail");
          SupersonicWebView.this.injectJavascript(paramString);
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
              paramString.putExtra("productType", SSAEnums.ProductType.BrandConnect.toString());
              SupersonicWebView.this.mSavedState.adOpened(SSAEnums.ProductType.BrandConnect.ordinal());
              i = 1;
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
      paramString = new SSAObj(paramString).getString("productType");
      Object localObject = new Object[2];
      paramString = SupersonicWebView.this.getApplicationParams(paramString);
      localObject = (String)paramString[0];
      if (((Boolean)paramString[1]).booleanValue())
      {
        if (TextUtils.isEmpty(str2)) {
          break label156;
        }
        paramString = str2;
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = SupersonicWebView.this.generateJSToInject(paramString, (String)localObject, "onGetApplicationInfoSuccess", "onGetApplicationInfoFail");
          SupersonicWebView.this.injectJavascript(paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        } else {
          label156:
          paramString = null;
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
      if (SupersonicWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = SupersonicWebView.this.getBaseContext();
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.JSInterface.5(this));
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
          SupersonicSharedPrefHelper.getSupersonicPrefHelper().setCampaignLastUpdate(paramString, str2);
        }
      }
      SupersonicWebView.this.downloadManager.downloadFile(localSSAFile);
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
        Logger.i(SupersonicWebView.this.TAG, "X:" + (int)f1 + " Y:" + (int)f2);
        j = SDKUtils.getDeviceWidth();
        i = SDKUtils.getDeviceHeight();
        Logger.i(SupersonicWebView.this.TAG, "Width:" + j + " Height:" + i);
        k = SDKUtils.dpToPx(SupersonicWebView.this.mHiddenForceCloseWidth);
        m = SDKUtils.dpToPx(SupersonicWebView.this.mHiddenForceCloseHeight);
        if (!"top-right".equalsIgnoreCase(SupersonicWebView.this.mHiddenForceCloseLocation)) {
          break label233;
        }
        j -= (int)f1;
        i = (int)f2;
      }
      for (;;)
      {
        if ((j <= k) && (i <= m))
        {
          SupersonicWebView.access$702(SupersonicWebView.this, false);
          if (SupersonicWebView.this.mCloseEventTimer != null) {
            SupersonicWebView.this.mCloseEventTimer.cancel();
          }
          SupersonicWebView.access$802(SupersonicWebView.this, new SupersonicWebView.SupersonicWebViewTouchListener.1(this, 2000L, 500L).start());
        }
        return false;
        label233:
        if ("top-left".equalsIgnoreCase(SupersonicWebView.this.mHiddenForceCloseLocation))
        {
          j = (int)f1;
          i = (int)f2;
        }
        else if ("bottom-right".equalsIgnoreCase(SupersonicWebView.this.mHiddenForceCloseLocation))
        {
          j -= (int)f1;
          i -= (int)f2;
        }
        else if ("bottom-left".equalsIgnoreCase(SupersonicWebView.this.mHiddenForceCloseLocation))
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
