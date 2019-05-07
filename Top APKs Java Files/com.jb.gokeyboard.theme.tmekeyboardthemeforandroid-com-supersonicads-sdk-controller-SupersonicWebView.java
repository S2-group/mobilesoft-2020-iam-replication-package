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
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.supersonic.environment.DeviceStatus;
import com.supersonicads.sdk.data.AdUnitsState;
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
  private Map<String, String> mBCExtraParameters;
  private String mBCUserId;
  private String mCacheDirectory;
  private SupersonicWebView.OnWebViewControllerChangeListener mChangeListener;
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
  private SupersonicWebView.State mState;
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
    addJavascriptInterface(new SupersonicWebView.JSInterface(this, paramContext), "Android");
    setDownloadListener(this);
    setOnTouchListener(new SupersonicWebView.SupersonicWebViewTouchListener(this, null));
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
    //   0: new 604	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 605	org/json/JSONObject:<init>	()V
    //   7: astore 7
    //   9: ldc_w 673
    //   12: astore 5
    //   14: ldc_w 673
    //   17: astore 4
    //   19: aconst_null
    //   20: astore 6
    //   22: aconst_null
    //   23: astore_3
    //   24: aload_1
    //   25: invokestatic 679	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   28: ifne +329 -> 357
    //   31: aload_1
    //   32: getstatic 525	com/supersonicads/sdk/data/SSAEnums$ProductType:BrandConnect	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   35: invokevirtual 680	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   38: invokevirtual 683	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   41: ifeq +219 -> 260
    //   44: aload_0
    //   45: getfield 326	com/supersonicads/sdk/controller/SupersonicWebView:mBCAppKey	Ljava/lang/String;
    //   48: astore 5
    //   50: aload_0
    //   51: getfield 329	com/supersonicads/sdk/controller/SupersonicWebView:mBCUserId	Ljava/lang/String;
    //   54: astore 4
    //   56: aload_0
    //   57: getfield 333	com/supersonicads/sdk/controller/SupersonicWebView:mBCExtraParameters	Ljava/util/Map;
    //   60: astore_3
    //   61: aload 7
    //   63: ldc_w 580
    //   66: aload_1
    //   67: invokevirtual 685	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   70: pop
    //   71: iconst_0
    //   72: istore_2
    //   73: aload 4
    //   75: invokestatic 679	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   78: ifne +316 -> 394
    //   81: aload 7
    //   83: ldc_w 550
    //   86: invokestatic 640	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   89: aload 4
    //   91: invokestatic 640	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   94: invokevirtual 685	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   97: pop
    //   98: aload 5
    //   100: invokestatic 679	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   103: ifne +325 -> 428
    //   106: aload 7
    //   108: ldc_w 542
    //   111: invokestatic 640	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   114: aload 5
    //   116: invokestatic 640	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   119: invokevirtual 685	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   122: pop
    //   123: aload_3
    //   124: ifnull +309 -> 433
    //   127: aload_3
    //   128: invokeinterface 687 1 0
    //   133: ifne +300 -> 433
    //   136: aload_3
    //   137: invokeinterface 609 1 0
    //   142: invokeinterface 615 1 0
    //   147: astore_1
    //   148: aload_1
    //   149: invokeinterface 621 1 0
    //   154: ifeq +279 -> 433
    //   157: aload_1
    //   158: invokeinterface 625 1 0
    //   163: checkcast 627	java/util/Map$Entry
    //   166: astore_3
    //   167: aload_3
    //   168: invokeinterface 630 1 0
    //   173: checkcast 632	java/lang/String
    //   176: ldc_w 689
    //   179: invokevirtual 683	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   182: ifeq +16 -> 198
    //   185: aload_0
    //   186: aload_3
    //   187: invokeinterface 635 1 0
    //   192: checkcast 632	java/lang/String
    //   195: invokespecial 692	com/supersonicads/sdk/controller/SupersonicWebView:setWebviewCache	(Ljava/lang/String;)V
    //   198: aload 7
    //   200: aload_3
    //   201: invokeinterface 630 1 0
    //   206: checkcast 632	java/lang/String
    //   209: invokestatic 640	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   212: aload_3
    //   213: invokeinterface 635 1 0
    //   218: checkcast 632	java/lang/String
    //   221: invokestatic 640	com/supersonicads/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   224: invokevirtual 685	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   227: pop
    //   228: goto -80 -> 148
    //   231: astore_3
    //   232: aload_3
    //   233: invokevirtual 695	org/json/JSONException:printStackTrace	()V
    //   236: new 697	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   239: dup
    //   240: invokespecial 698	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   243: iconst_1
    //   244: anewarray 632	java/lang/String
    //   247: dup
    //   248: iconst_0
    //   249: ldc_w 700
    //   252: aastore
    //   253: invokevirtual 704	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   256: pop
    //   257: goto -109 -> 148
    //   260: aload_1
    //   261: getstatic 537	com/supersonicads/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   264: invokevirtual 680	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   267: invokevirtual 683	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   270: ifeq +23 -> 293
    //   273: aload_0
    //   274: getfield 343	com/supersonicads/sdk/controller/SupersonicWebView:mISAppKey	Ljava/lang/String;
    //   277: astore 5
    //   279: aload_0
    //   280: getfield 346	com/supersonicads/sdk/controller/SupersonicWebView:mISUserId	Ljava/lang/String;
    //   283: astore 4
    //   285: aload_0
    //   286: getfield 349	com/supersonicads/sdk/controller/SupersonicWebView:mISExtraParameters	Ljava/util/Map;
    //   289: astore_3
    //   290: goto -229 -> 61
    //   293: aload_1
    //   294: getstatic 567	com/supersonicads/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   297: invokevirtual 680	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   300: invokevirtual 683	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   303: ifeq -242 -> 61
    //   306: aload_0
    //   307: getfield 360	com/supersonicads/sdk/controller/SupersonicWebView:mOWAppKey	Ljava/lang/String;
    //   310: astore 5
    //   312: aload_0
    //   313: getfield 363	com/supersonicads/sdk/controller/SupersonicWebView:mOWUserId	Ljava/lang/String;
    //   316: astore 4
    //   318: aload_0
    //   319: getfield 366	com/supersonicads/sdk/controller/SupersonicWebView:mOWExtraParameters	Ljava/util/Map;
    //   322: astore_3
    //   323: goto -262 -> 61
    //   326: astore_1
    //   327: aload_1
    //   328: invokevirtual 695	org/json/JSONException:printStackTrace	()V
    //   331: new 697	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   334: dup
    //   335: invokespecial 698	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   338: iconst_1
    //   339: anewarray 632	java/lang/String
    //   342: dup
    //   343: iconst_0
    //   344: ldc_w 706
    //   347: aastore
    //   348: invokevirtual 704	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
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
    //   367: invokevirtual 695	org/json/JSONException:printStackTrace	()V
    //   370: new 697	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   373: dup
    //   374: invokespecial 698	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   377: iconst_1
    //   378: anewarray 632	java/lang/String
    //   381: dup
    //   382: iconst_0
    //   383: ldc_w 708
    //   386: aastore
    //   387: invokevirtual 704	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   390: pop
    //   391: goto -293 -> 98
    //   394: iconst_1
    //   395: istore_2
    //   396: goto -298 -> 98
    //   399: astore_1
    //   400: aload_1
    //   401: invokevirtual 695	org/json/JSONException:printStackTrace	()V
    //   404: new 697	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask
    //   407: dup
    //   408: invokespecial 698	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:<init>	()V
    //   411: iconst_1
    //   412: anewarray 632	java/lang/String
    //   415: dup
    //   416: iconst_0
    //   417: ldc_w 710
    //   420: aastore
    //   421: invokevirtual 704	com/supersonicads/sdk/utils/SupersonicAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   424: pop
    //   425: goto -302 -> 123
    //   428: iconst_1
    //   429: istore_2
    //   430: goto -307 -> 123
    //   433: iconst_2
    //   434: anewarray 194	java/lang/Object
    //   437: dup
    //   438: iconst_0
    //   439: aload 7
    //   441: invokevirtual 661	org/json/JSONObject:toString	()Ljava/lang/String;
    //   444: aastore
    //   445: dup
    //   446: iconst_1
    //   447: iload_2
    //   448: invokestatic 716	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
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
  
  public SupersonicWebView.State getState()
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
  
  public void setOnWebViewControllerChangeListener(SupersonicWebView.OnWebViewControllerChangeListener paramOnWebViewControllerChangeListener)
  {
    this.mChangeListener = paramOnWebViewControllerChangeListener;
  }
  
  public void setOrientationState(String paramString)
  {
    this.mOrientationState = paramString;
  }
  
  public void setState(SupersonicWebView.State paramState)
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
      new SupersonicAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
      return;
    }
    catch (IllegalArgumentException paramContext) {}
  }
  
  public void viewableChange(boolean paramBoolean, String paramString)
  {
    injectJavascript(generateJSToInject("viewableChange", parseToJson("webview", paramString, null, null, null, null, null, null, "isViewable", paramBoolean)));
  }
}
