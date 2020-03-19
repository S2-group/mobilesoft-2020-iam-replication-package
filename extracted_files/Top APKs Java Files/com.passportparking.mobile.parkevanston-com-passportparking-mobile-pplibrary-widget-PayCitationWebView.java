package com.passportparking.mobile.pplibrary.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.passportparking.mobile.pplibrary.MobileApp;
import com.passportparking.mobile.pplibrary.R.string;
import com.passportparking.mobile.pplibrary.utils.ViewUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.MissingResourceException;
import java.util.Set;

public class PayCitationWebView
  extends WebView
{
  private static final String[] ALTERNATIVE_BROWSERS = { "org.mozilla.firefox", "com.android.chrome", "com.opera.browser", "org.mozilla.firefox_beta", "com.chrome.beta", "com.opera.browser.beta" };
  private static final String CHARSET_DEFAULT = "UTF-8";
  private static final String DATABASES_SUB_FOLDER = "/databases";
  private static final String LANGUAGE_DEFAULT_ISO3 = "eng";
  private static final String PACKAGE_NAME_DOWNLOAD_MANAGER = "com.android.providers.downloads";
  private static final int REQUEST_CODE_FILE_PICKER = 51426;
  private WeakReference<Activity> mActivity;
  private WebChromeClient mCustomWebChromeClient;
  private WebViewClient mCustomWebViewClient;
  private ValueCallback<Uri> mFileUploadCallbackFirst;
  private ValueCallback<Uri[]> mFileUploadCallbackSecond;
  private WeakReference<Fragment> mFragment;
  private boolean mGeolocationEnabled;
  private final Map<String, String> mHttpHeaders = new HashMap();
  private String mLanguageIso3;
  private long mLastError;
  private Listener mListener;
  private final List<String> mPermittedHostnames = new LinkedList();
  private int mRequestCodeFilePicker = 51426;
  private String mUploadableFileTypes = "*/*";
  
  public PayCitationWebView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public PayCitationWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public PayCitationWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private static String decodeBase64(String paramString)
  {
    return new String(Base64.decode(paramString, 0), "UTF-8");
  }
  
  private String getFileUploadPromptLabel()
  {
    try
    {
      if (this.mLanguageIso3.equals("zho")) {
        return decodeBase64("6YCJ5oup5LiA5Liq5paH5Lu2");
      }
      if (this.mLanguageIso3.equals("spa")) {
        return decodeBase64("RWxpamEgdW4gYXJjaGl2bw==");
      }
      if (this.mLanguageIso3.equals("hin")) {
        return decodeBase64("4KSP4KSVIOCkq+CkvOCkvuCkh+CksiDgpJrgpYHgpKjgpYfgpII=");
      }
      if (this.mLanguageIso3.equals("ben")) {
        return decodeBase64("4KaP4KaV4Kaf4Ka/IOCmq+CmvuCmh+CmsiDgpqjgpr/gprDgp43gpqzgpr7gpprgpqg=");
      }
      if (this.mLanguageIso3.equals("ara")) {
        return decodeBase64("2KfYrtiq2YrYp9ixINmF2YTZgSDZiNin2K3Yrw==");
      }
      if (this.mLanguageIso3.equals("por")) {
        return decodeBase64("RXNjb2xoYSB1bSBhcnF1aXZv");
      }
      if (this.mLanguageIso3.equals("rus")) {
        return decodeBase64("0JLRi9Cx0LXRgNC40YLQtSDQvtC00LjQvSDRhNCw0LnQuw==");
      }
      if (this.mLanguageIso3.equals("jpn")) {
        return decodeBase64("MeODleOCoeOCpOODq+OCkumBuOaKnuOBl+OBpuOBj+OBoOOBleOBhA==");
      }
      if (this.mLanguageIso3.equals("pan")) {
        return decodeBase64("4KiH4Kmx4KiVIOCoq+CovuCoh+CosiDgqJrgqYHgqKPgqYs=");
      }
      if (this.mLanguageIso3.equals("deu")) {
        return decodeBase64("V8OkaGxlIGVpbmUgRGF0ZWk=");
      }
      if (this.mLanguageIso3.equals("jav")) {
        return decodeBase64("UGlsaWggc2lqaSBiZXJrYXM=");
      }
      if (this.mLanguageIso3.equals("msa")) {
        return decodeBase64("UGlsaWggc2F0dSBmYWls");
      }
      if (this.mLanguageIso3.equals("tel")) {
        return decodeBase64("4LCS4LCVIOCwq+CxhuCxluCwsuCxjeCwqOCxgSDgsI7gsILgsJrgsYHgsJXgsYvgsILgsKHgsL8=");
      }
      if (this.mLanguageIso3.equals("vie")) {
        return decodeBase64("Q2jhu41uIG3hu5l0IHThuq1wIHRpbg==");
      }
      if (this.mLanguageIso3.equals("kor")) {
        return decodeBase64("7ZWY64KY7J2YIO2MjOydvOydhCDshKDtg50=");
      }
      if (this.mLanguageIso3.equals("fra")) {
        return decodeBase64("Q2hvaXNpc3NleiB1biBmaWNoaWVy");
      }
      if (this.mLanguageIso3.equals("mar")) {
        return decodeBase64("4KSr4KS+4KSH4KSyIOCkqOCkv+CkteCkoeCkvg==");
      }
      if (this.mLanguageIso3.equals("tam")) {
        return decodeBase64("4K6S4K6w4K+BIOCuleCvh+CuvuCuquCvjeCuquCviCDgrqTgr4fgrrDgr43grrXgr4E=");
      }
      if (this.mLanguageIso3.equals("urd")) {
        return decodeBase64("2KfbjNqpINmB2KfYptmEINmF24zauiDYs9uSINin2YbYqtiu2KfYqCDaqdix24zaug==");
      }
      if (this.mLanguageIso3.equals("fas")) {
        return decodeBase64("2LHYpyDYp9mG2KrYrtin2Kgg2qnZhtuM2K8g24zaqSDZgdin24zZhA==");
      }
      if (this.mLanguageIso3.equals("tur")) {
        return decodeBase64("QmlyIGRvc3lhIHNlw6dpbg==");
      }
      if (this.mLanguageIso3.equals("ita")) {
        return decodeBase64("U2NlZ2xpIHVuIGZpbGU=");
      }
      if (this.mLanguageIso3.equals("tha")) {
        return decodeBase64("4LmA4Lil4Li34Lit4LiB4LmE4Lif4Lil4LmM4Lir4LiZ4Li24LmI4LiH");
      }
      if (this.mLanguageIso3.equals("guj"))
      {
        String str = decodeBase64("4KqP4KqVIOCqq+CqvuCqh+CqsuCqqOCrhyDgqqrgqrjgqoLgqqY=");
        return str;
      }
    }
    catch (Exception localException) {}
    return "Choose a file";
  }
  
  private static String getLanguageIso3()
  {
    try
    {
      String str = Locale.getDefault().getISO3Language().toLowerCase(Locale.US);
      return str;
    }
    catch (MissingResourceException localMissingResourceException) {}
    return "eng";
  }
  
  @SuppressLint({"NewApi"})
  public static boolean handleDownload(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = new DownloadManager.Request(Uri.parse(paramString1));
    paramString1.allowScanningByMediaScanner();
    paramString1.setNotificationVisibility(1);
    paramString1.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, paramString2);
    paramString2 = (DownloadManager)paramContext.getSystemService("download");
    try
    {
      paramString2.enqueue(paramString1);
      return true;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        paramString1.setNotificationVisibility(0);
        paramString2.enqueue(paramString1);
      }
    }
    catch (IllegalArgumentException paramString1)
    {
      openAppSettings(paramContext, "com.android.providers.downloads");
    }
    return false;
  }
  
  private boolean hasError()
  {
    return this.mLastError + 500L >= System.currentTimeMillis();
  }
  
  @SuppressLint({"SetJavaScriptEnabled", "NewApi", "InlinedApi"})
  private void init(Context paramContext)
  {
    if ((paramContext instanceof Activity)) {
      this.mActivity = new WeakReference((Activity)paramContext);
    }
    if (Build.VERSION.SDK_INT >= 19) {
      setLayerType(2, null);
    }
    for (;;)
    {
      this.mLanguageIso3 = getLanguageIso3();
      setFocusable(true);
      setFocusableInTouchMode(true);
      setSaveEnabled(true);
      paramContext = paramContext.getFilesDir().getPath();
      paramContext = paramContext.substring(0, paramContext.lastIndexOf("/")) + "/databases";
      WebSettings localWebSettings = getSettings();
      localWebSettings.setAllowFileAccess(true);
      setAllowAccessFromFileUrls(localWebSettings, false);
      localWebSettings.setBuiltInZoomControls(false);
      localWebSettings.setJavaScriptEnabled(true);
      localWebSettings.setDomStorageEnabled(true);
      if (Build.VERSION.SDK_INT < 18) {
        localWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
      }
      localWebSettings.setDatabaseEnabled(true);
      if (Build.VERSION.SDK_INT < 19) {
        localWebSettings.setDatabasePath(paramContext);
      }
      setMixedContentAllowed(localWebSettings, true);
      setThirdPartyCookiesEnabled(true);
      super.setWebViewClient(new WebViewClient()
      {
        public void doUpdateVisitedHistory(WebView paramAnonymousWebView, String paramAnonymousString, boolean paramAnonymousBoolean)
        {
          if (PayCitationWebView.this.mCustomWebViewClient != null)
          {
            PayCitationWebView.this.mCustomWebViewClient.doUpdateVisitedHistory(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
            return;
          }
          super.doUpdateVisitedHistory(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
        }
        
        public void onFormResubmission(WebView paramAnonymousWebView, Message paramAnonymousMessage1, Message paramAnonymousMessage2)
        {
          if (PayCitationWebView.this.mCustomWebViewClient != null)
          {
            PayCitationWebView.this.mCustomWebViewClient.onFormResubmission(paramAnonymousWebView, paramAnonymousMessage1, paramAnonymousMessage2);
            return;
          }
          super.onFormResubmission(paramAnonymousWebView, paramAnonymousMessage1, paramAnonymousMessage2);
        }
        
        public void onLoadResource(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          if (PayCitationWebView.this.mCustomWebViewClient != null)
          {
            PayCitationWebView.this.mCustomWebViewClient.onLoadResource(paramAnonymousWebView, paramAnonymousString);
            return;
          }
          super.onLoadResource(paramAnonymousWebView, paramAnonymousString);
        }
        
        public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          if ((!PayCitationWebView.this.hasError()) && (PayCitationWebView.this.mListener != null)) {
            PayCitationWebView.this.mListener.onPageFinished(paramAnonymousString);
          }
          if (PayCitationWebView.this.mCustomWebViewClient != null) {
            PayCitationWebView.this.mCustomWebViewClient.onPageFinished(paramAnonymousWebView, paramAnonymousString);
          }
        }
        
        public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
        {
          if ((!PayCitationWebView.this.hasError()) && (PayCitationWebView.this.mListener != null)) {
            PayCitationWebView.this.mListener.onPageStarted(paramAnonymousString, paramAnonymousBitmap);
          }
          if (PayCitationWebView.this.mCustomWebViewClient != null) {
            PayCitationWebView.this.mCustomWebViewClient.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
          }
        }
        
        @SuppressLint({"NewApi"})
        public void onReceivedClientCertRequest(WebView paramAnonymousWebView, ClientCertRequest paramAnonymousClientCertRequest)
        {
          if (Build.VERSION.SDK_INT >= 21)
          {
            if (PayCitationWebView.this.mCustomWebViewClient != null) {
              PayCitationWebView.this.mCustomWebViewClient.onReceivedClientCertRequest(paramAnonymousWebView, paramAnonymousClientCertRequest);
            }
          }
          else {
            return;
          }
          super.onReceivedClientCertRequest(paramAnonymousWebView, paramAnonymousClientCertRequest);
        }
        
        public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
        {
          PayCitationWebView.this.setLastError();
          if (PayCitationWebView.this.mListener != null) {
            PayCitationWebView.this.mListener.onPageError(paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
          }
          if (PayCitationWebView.this.mCustomWebViewClient != null) {
            PayCitationWebView.this.mCustomWebViewClient.onReceivedError(paramAnonymousWebView, paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
          }
        }
        
        public void onReceivedHttpAuthRequest(WebView paramAnonymousWebView, HttpAuthHandler paramAnonymousHttpAuthHandler, String paramAnonymousString1, String paramAnonymousString2)
        {
          if (PayCitationWebView.this.mCustomWebViewClient != null)
          {
            PayCitationWebView.this.mCustomWebViewClient.onReceivedHttpAuthRequest(paramAnonymousWebView, paramAnonymousHttpAuthHandler, paramAnonymousString1, paramAnonymousString2);
            return;
          }
          super.onReceivedHttpAuthRequest(paramAnonymousWebView, paramAnonymousHttpAuthHandler, paramAnonymousString1, paramAnonymousString2);
        }
        
        @SuppressLint({"NewApi"})
        public void onReceivedLoginRequest(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3)
        {
          if (Build.VERSION.SDK_INT >= 12)
          {
            if (PayCitationWebView.this.mCustomWebViewClient != null) {
              PayCitationWebView.this.mCustomWebViewClient.onReceivedLoginRequest(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3);
            }
          }
          else {
            return;
          }
          super.onReceivedLoginRequest(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3);
        }
        
        public void onReceivedSslError(WebView paramAnonymousWebView, SslErrorHandler paramAnonymousSslErrorHandler, SslError paramAnonymousSslError)
        {
          paramAnonymousWebView = MobileApp.getActivityContext();
          paramAnonymousSslError = PayCitationWebView.this.getResources().getString(R.string.ssl_error_title);
          String str = PayCitationWebView.this.getResources().getString(R.string.ssl_error_message);
          paramAnonymousSslErrorHandler.getClass();
          ViewUtils.alertOkCancel(paramAnonymousWebView, paramAnonymousSslError, str, PayCitationWebView.1..Lambda.1.lambdaFactory$(paramAnonymousSslErrorHandler), PayCitationWebView.1..Lambda.2.lambdaFactory$(paramAnonymousSslErrorHandler));
        }
        
        public void onScaleChanged(WebView paramAnonymousWebView, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          if (PayCitationWebView.this.mCustomWebViewClient != null)
          {
            PayCitationWebView.this.mCustomWebViewClient.onScaleChanged(paramAnonymousWebView, paramAnonymousFloat1, paramAnonymousFloat2);
            return;
          }
          super.onScaleChanged(paramAnonymousWebView, paramAnonymousFloat1, paramAnonymousFloat2);
        }
        
        public void onUnhandledKeyEvent(WebView paramAnonymousWebView, KeyEvent paramAnonymousKeyEvent)
        {
          if (PayCitationWebView.this.mCustomWebViewClient != null)
          {
            PayCitationWebView.this.mCustomWebViewClient.onUnhandledKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
            return;
          }
          super.onUnhandledKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
        }
        
        @SuppressLint({"NewApi"})
        public WebResourceResponse shouldInterceptRequest(WebView paramAnonymousWebView, WebResourceRequest paramAnonymousWebResourceRequest)
        {
          if (Build.VERSION.SDK_INT >= 21)
          {
            if (PayCitationWebView.this.mCustomWebViewClient != null) {
              return PayCitationWebView.this.mCustomWebViewClient.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousWebResourceRequest);
            }
            return super.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousWebResourceRequest);
          }
          return null;
        }
        
        @SuppressLint({"NewApi"})
        public WebResourceResponse shouldInterceptRequest(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          if (Build.VERSION.SDK_INT >= 11)
          {
            if (PayCitationWebView.this.mCustomWebViewClient != null) {
              return PayCitationWebView.this.mCustomWebViewClient.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousString);
            }
            return super.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousString);
          }
          return null;
        }
        
        public boolean shouldOverrideKeyEvent(WebView paramAnonymousWebView, KeyEvent paramAnonymousKeyEvent)
        {
          if (PayCitationWebView.this.mCustomWebViewClient != null) {
            return PayCitationWebView.this.mCustomWebViewClient.shouldOverrideKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
          }
          return super.shouldOverrideKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
        }
        
        public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          if (PayCitationWebView.this.isHostnameAllowed(paramAnonymousString)) {
            if ((PayCitationWebView.this.mCustomWebViewClient == null) || (!PayCitationWebView.this.mCustomWebViewClient.shouldOverrideUrlLoading(paramAnonymousWebView, paramAnonymousString))) {}
          }
          while (PayCitationWebView.this.mListener == null)
          {
            return true;
            return false;
          }
          PayCitationWebView.this.mListener.onExternalPageRequest(paramAnonymousString);
          return true;
        }
      });
      super.setWebChromeClient(new WebChromeClient()
      {
        public Bitmap getDefaultVideoPoster()
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null) {
            return PayCitationWebView.this.mCustomWebChromeClient.getDefaultVideoPoster();
          }
          return super.getDefaultVideoPoster();
        }
        
        public View getVideoLoadingProgressView()
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null) {
            return PayCitationWebView.this.mCustomWebChromeClient.getVideoLoadingProgressView();
          }
          return super.getVideoLoadingProgressView();
        }
        
        public void getVisitedHistory(ValueCallback<String[]> paramAnonymousValueCallback)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.getVisitedHistory(paramAnonymousValueCallback);
            return;
          }
          super.getVisitedHistory(paramAnonymousValueCallback);
        }
        
        public void onCloseWindow(WebView paramAnonymousWebView)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onCloseWindow(paramAnonymousWebView);
            return;
          }
          super.onCloseWindow(paramAnonymousWebView);
        }
        
        public void onConsoleMessage(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onConsoleMessage(paramAnonymousString1, paramAnonymousInt, paramAnonymousString2);
            return;
          }
          super.onConsoleMessage(paramAnonymousString1, paramAnonymousInt, paramAnonymousString2);
        }
        
        public boolean onConsoleMessage(ConsoleMessage paramAnonymousConsoleMessage)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null) {
            return PayCitationWebView.this.mCustomWebChromeClient.onConsoleMessage(paramAnonymousConsoleMessage);
          }
          return super.onConsoleMessage(paramAnonymousConsoleMessage);
        }
        
        public boolean onCreateWindow(WebView paramAnonymousWebView, boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2, Message paramAnonymousMessage)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null) {
            return PayCitationWebView.this.mCustomWebChromeClient.onCreateWindow(paramAnonymousWebView, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousMessage);
          }
          return super.onCreateWindow(paramAnonymousWebView, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousMessage);
        }
        
        public void onExceededDatabaseQuota(String paramAnonymousString1, String paramAnonymousString2, long paramAnonymousLong1, long paramAnonymousLong2, long paramAnonymousLong3, WebStorage.QuotaUpdater paramAnonymousQuotaUpdater)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onExceededDatabaseQuota(paramAnonymousString1, paramAnonymousString2, paramAnonymousLong1, paramAnonymousLong2, paramAnonymousLong3, paramAnonymousQuotaUpdater);
            return;
          }
          super.onExceededDatabaseQuota(paramAnonymousString1, paramAnonymousString2, paramAnonymousLong1, paramAnonymousLong2, paramAnonymousLong3, paramAnonymousQuotaUpdater);
        }
        
        public void onGeolocationPermissionsHidePrompt()
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onGeolocationPermissionsHidePrompt();
            return;
          }
          super.onGeolocationPermissionsHidePrompt();
        }
        
        public void onGeolocationPermissionsShowPrompt(String paramAnonymousString, GeolocationPermissions.Callback paramAnonymousCallback)
        {
          if (PayCitationWebView.this.mGeolocationEnabled)
          {
            paramAnonymousCallback.invoke(paramAnonymousString, true, false);
            return;
          }
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onGeolocationPermissionsShowPrompt(paramAnonymousString, paramAnonymousCallback);
            return;
          }
          super.onGeolocationPermissionsShowPrompt(paramAnonymousString, paramAnonymousCallback);
        }
        
        public void onHideCustomView()
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onHideCustomView();
            return;
          }
          super.onHideCustomView();
        }
        
        public boolean onJsAlert(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null) {
            return PayCitationWebView.this.mCustomWebChromeClient.onJsAlert(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
          }
          return super.onJsAlert(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        
        public boolean onJsBeforeUnload(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null) {
            return PayCitationWebView.this.mCustomWebChromeClient.onJsBeforeUnload(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
          }
          return super.onJsBeforeUnload(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        
        public boolean onJsConfirm(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null) {
            return PayCitationWebView.this.mCustomWebChromeClient.onJsConfirm(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
          }
          return super.onJsConfirm(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        
        public boolean onJsPrompt(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, JsPromptResult paramAnonymousJsPromptResult)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null) {
            return PayCitationWebView.this.mCustomWebChromeClient.onJsPrompt(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousJsPromptResult);
          }
          return super.onJsPrompt(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousJsPromptResult);
        }
        
        public boolean onJsTimeout()
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null) {
            return PayCitationWebView.this.mCustomWebChromeClient.onJsTimeout();
          }
          return super.onJsTimeout();
        }
        
        @SuppressLint({"NewApi"})
        public void onPermissionRequest(PermissionRequest paramAnonymousPermissionRequest)
        {
          if (Build.VERSION.SDK_INT >= 21)
          {
            if (PayCitationWebView.this.mCustomWebChromeClient != null) {
              PayCitationWebView.this.mCustomWebChromeClient.onPermissionRequest(paramAnonymousPermissionRequest);
            }
          }
          else {
            return;
          }
          super.onPermissionRequest(paramAnonymousPermissionRequest);
        }
        
        @SuppressLint({"NewApi"})
        public void onPermissionRequestCanceled(PermissionRequest paramAnonymousPermissionRequest)
        {
          if (Build.VERSION.SDK_INT >= 21)
          {
            if (PayCitationWebView.this.mCustomWebChromeClient != null) {
              PayCitationWebView.this.mCustomWebChromeClient.onPermissionRequestCanceled(paramAnonymousPermissionRequest);
            }
          }
          else {
            return;
          }
          super.onPermissionRequestCanceled(paramAnonymousPermissionRequest);
        }
        
        public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onProgressChanged(paramAnonymousWebView, paramAnonymousInt);
            return;
          }
          super.onProgressChanged(paramAnonymousWebView, paramAnonymousInt);
        }
        
        public void onReachedMaxAppCacheSize(long paramAnonymousLong1, long paramAnonymousLong2, WebStorage.QuotaUpdater paramAnonymousQuotaUpdater)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onReachedMaxAppCacheSize(paramAnonymousLong1, paramAnonymousLong2, paramAnonymousQuotaUpdater);
            return;
          }
          super.onReachedMaxAppCacheSize(paramAnonymousLong1, paramAnonymousLong2, paramAnonymousQuotaUpdater);
        }
        
        public void onReceivedIcon(WebView paramAnonymousWebView, Bitmap paramAnonymousBitmap)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onReceivedIcon(paramAnonymousWebView, paramAnonymousBitmap);
            return;
          }
          super.onReceivedIcon(paramAnonymousWebView, paramAnonymousBitmap);
        }
        
        public void onReceivedTitle(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
            return;
          }
          super.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
        }
        
        public void onReceivedTouchIconUrl(WebView paramAnonymousWebView, String paramAnonymousString, boolean paramAnonymousBoolean)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onReceivedTouchIconUrl(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
            return;
          }
          super.onReceivedTouchIconUrl(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
        }
        
        public void onRequestFocus(WebView paramAnonymousWebView)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onRequestFocus(paramAnonymousWebView);
            return;
          }
          super.onRequestFocus(paramAnonymousWebView);
        }
        
        @SuppressLint({"NewApi"})
        public void onShowCustomView(View paramAnonymousView, int paramAnonymousInt, WebChromeClient.CustomViewCallback paramAnonymousCustomViewCallback)
        {
          if (Build.VERSION.SDK_INT >= 14)
          {
            if (PayCitationWebView.this.mCustomWebChromeClient != null) {
              PayCitationWebView.this.mCustomWebChromeClient.onShowCustomView(paramAnonymousView, paramAnonymousInt, paramAnonymousCustomViewCallback);
            }
          }
          else {
            return;
          }
          super.onShowCustomView(paramAnonymousView, paramAnonymousInt, paramAnonymousCustomViewCallback);
        }
        
        public void onShowCustomView(View paramAnonymousView, WebChromeClient.CustomViewCallback paramAnonymousCustomViewCallback)
        {
          if (PayCitationWebView.this.mCustomWebChromeClient != null)
          {
            PayCitationWebView.this.mCustomWebChromeClient.onShowCustomView(paramAnonymousView, paramAnonymousCustomViewCallback);
            return;
          }
          super.onShowCustomView(paramAnonymousView, paramAnonymousCustomViewCallback);
        }
        
        public boolean onShowFileChooser(WebView paramAnonymousWebView, ValueCallback<Uri[]> paramAnonymousValueCallback, WebChromeClient.FileChooserParams paramAnonymousFileChooserParams)
        {
          PayCitationWebView.this.openFileInput(null, paramAnonymousValueCallback);
          return true;
        }
        
        public void openFileChooser(ValueCallback<Uri> paramAnonymousValueCallback)
        {
          openFileChooser(paramAnonymousValueCallback, null);
        }
        
        void openFileChooser(ValueCallback<Uri> paramAnonymousValueCallback, String paramAnonymousString)
        {
          openFileChooser(paramAnonymousValueCallback, paramAnonymousString, null);
        }
        
        void openFileChooser(ValueCallback<Uri> paramAnonymousValueCallback, String paramAnonymousString1, String paramAnonymousString2)
        {
          PayCitationWebView.this.openFileInput(paramAnonymousValueCallback, null);
        }
      });
      setDownloadListener(PayCitationWebView..Lambda.1.lambdaFactory$(this));
      return;
      setLayerType(1, null);
    }
  }
  
  public static boolean isFileUploadAvailable()
  {
    return isFileUploadAvailable(false);
  }
  
  private static boolean isFileUploadAvailable(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT == 19) {
      if (Build.VERSION.RELEASE != null) {
        break label44;
      }
    }
    label44:
    for (String str = ""; (!paramBoolean) && ((str.startsWith("4.4.3")) || (str.startsWith("4.4.4"))); str = Build.VERSION.RELEASE) {
      return true;
    }
    return false;
  }
  
  private boolean isHostnameAllowed(String paramString)
  {
    if (this.mPermittedHostnames.size() == 0) {
      return true;
    }
    paramString = paramString.replace("http://", "").replace("https://", "");
    Iterator localIterator = this.mPermittedHostnames.iterator();
    while (localIterator.hasNext()) {
      if (paramString.startsWith((String)localIterator.next())) {
        return true;
      }
    }
    return false;
  }
  
  private static String makeUrlUnique(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    if (paramString.contains("?")) {
      localStringBuilder.append('&');
    }
    for (;;)
    {
      localStringBuilder.append(System.currentTimeMillis());
      localStringBuilder.append('=');
      localStringBuilder.append(1);
      return localStringBuilder.toString();
      if (paramString.lastIndexOf('/') <= 7) {
        localStringBuilder.append('/');
      }
      localStringBuilder.append('?');
    }
  }
  
  @SuppressLint({"NewApi"})
  private static void openAppSettings(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(Uri.parse("package:" + paramString));
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  @SuppressLint({"NewApi"})
  private void openFileInput(ValueCallback<Uri> paramValueCallback, ValueCallback<Uri[]> paramValueCallback1)
  {
    if (this.mFileUploadCallbackFirst != null) {
      this.mFileUploadCallbackFirst.onReceiveValue(null);
    }
    this.mFileUploadCallbackFirst = paramValueCallback;
    if (this.mFileUploadCallbackSecond != null) {
      this.mFileUploadCallbackSecond.onReceiveValue(null);
    }
    this.mFileUploadCallbackSecond = paramValueCallback1;
    paramValueCallback = new Intent("android.intent.action.GET_CONTENT");
    paramValueCallback.addCategory("android.intent.category.OPENABLE");
    paramValueCallback.setType(this.mUploadableFileTypes);
    if ((this.mFragment != null) && (this.mFragment.get() != null)) {
      ((Fragment)this.mFragment.get()).startActivityForResult(Intent.createChooser(paramValueCallback, getFileUploadPromptLabel()), this.mRequestCodeFilePicker);
    }
    while ((this.mActivity == null) || (this.mActivity.get() == null)) {
      return;
    }
    ((Activity)this.mActivity.get()).startActivityForResult(Intent.createChooser(paramValueCallback, getFileUploadPromptLabel()), this.mRequestCodeFilePicker);
  }
  
  @SuppressLint({"NewApi"})
  private static void setAllowAccessFromFileUrls(WebSettings paramWebSettings, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramWebSettings.setAllowFileAccessFromFileURLs(paramBoolean);
      paramWebSettings.setAllowUniversalAccessFromFileURLs(paramBoolean);
    }
  }
  
  @SuppressLint({"NewApi"})
  private void setGeolocationDatabasePath()
  {
    if ((this.mFragment != null) && (this.mFragment.get() != null) && (((Fragment)this.mFragment.get()).getActivity() != null)) {}
    for (Activity localActivity = ((Fragment)this.mFragment.get()).getActivity();; localActivity = (Activity)this.mActivity.get())
    {
      getSettings().setGeolocationDatabasePath(localActivity.getFilesDir().getPath());
      do
      {
        return;
      } while ((this.mActivity == null) || (this.mActivity.get() == null));
    }
  }
  
  private void setLastError()
  {
    this.mLastError = System.currentTimeMillis();
  }
  
  private void setListener(Activity paramActivity, Listener paramListener, int paramInt)
  {
    if (paramActivity != null) {}
    for (this.mActivity = new WeakReference(paramActivity);; this.mActivity = null)
    {
      setListener(paramListener, paramInt);
      return;
    }
  }
  
  private void setListener(Fragment paramFragment, Listener paramListener, int paramInt)
  {
    if (paramFragment != null) {}
    for (this.mFragment = new WeakReference(paramFragment);; this.mFragment = null)
    {
      setListener(paramListener, paramInt);
      return;
    }
  }
  
  private void setListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mRequestCodeFilePicker = paramInt;
  }
  
  @SuppressLint({"NewApi"})
  private void setMixedContentAllowed(WebSettings paramWebSettings, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      if (!paramBoolean) {
        break label20;
      }
    }
    label20:
    for (int i = 0;; i = 1)
    {
      paramWebSettings.setMixedContentMode(i);
      return;
    }
  }
  
  public void addHttpHeader(String paramString1, String paramString2)
  {
    this.mHttpHeaders.put(paramString1, paramString2);
  }
  
  public void addPermittedHostname(String paramString)
  {
    this.mPermittedHostnames.add(paramString);
  }
  
  public void addPermittedHostnames(Collection<? extends String> paramCollection)
  {
    this.mPermittedHostnames.addAll(paramCollection);
  }
  
  public void clearPermittedHostnames()
  {
    this.mPermittedHostnames.clear();
  }
  
  public List<String> getPermittedHostnames()
  {
    return this.mPermittedHostnames;
  }
  
  public void loadUrl(String paramString)
  {
    if (this.mHttpHeaders.size() > 0)
    {
      super.loadUrl(paramString, this.mHttpHeaders);
      return;
    }
    super.loadUrl(paramString);
  }
  
  public void loadUrl(String paramString, Map<String, String> paramMap)
  {
    Object localObject;
    if (paramMap == null) {
      localObject = this.mHttpHeaders;
    }
    for (;;)
    {
      super.loadUrl(paramString, (Map)localObject);
      return;
      localObject = paramMap;
      if (this.mHttpHeaders.size() > 0)
      {
        paramMap.putAll(this.mHttpHeaders);
        localObject = paramMap;
      }
    }
  }
  
  public void loadUrl(String paramString, boolean paramBoolean)
  {
    String str = paramString;
    if (paramBoolean) {
      str = makeUrlUnique(paramString);
    }
    loadUrl(str);
  }
  
  public void loadUrl(String paramString, boolean paramBoolean, Map<String, String> paramMap)
  {
    String str = paramString;
    if (paramBoolean) {
      str = makeUrlUnique(paramString);
    }
    loadUrl(str, paramMap);
  }
  
  @SuppressLint({"NewApi"})
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == this.mRequestCodeFilePicker)
    {
      if (paramInt2 != -1) {
        break label92;
      }
      if (paramIntent != null)
      {
        if (this.mFileUploadCallbackFirst == null) {
          break label43;
        }
        this.mFileUploadCallbackFirst.onReceiveValue(paramIntent.getData());
        this.mFileUploadCallbackFirst = null;
      }
    }
    label43:
    label92:
    do
    {
      do
      {
        return;
      } while (this.mFileUploadCallbackSecond == null);
      try
      {
        Uri[] arrayOfUri = new Uri[1];
        arrayOfUri[0] = Uri.parse(paramIntent.getDataString());
        paramIntent = arrayOfUri;
      }
      catch (Exception paramIntent)
      {
        for (;;)
        {
          paramIntent = null;
        }
      }
      this.mFileUploadCallbackSecond.onReceiveValue(paramIntent);
      this.mFileUploadCallbackSecond = null;
      return;
      if (this.mFileUploadCallbackFirst != null)
      {
        this.mFileUploadCallbackFirst.onReceiveValue(null);
        this.mFileUploadCallbackFirst = null;
        return;
      }
    } while (this.mFileUploadCallbackSecond == null);
    this.mFileUploadCallbackSecond.onReceiveValue(null);
    this.mFileUploadCallbackSecond = null;
  }
  
  public boolean onBackPressed()
  {
    if (canGoBack())
    {
      goBack();
      return true;
    }
    return false;
  }
  
  public void onDestroy()
  {
    try
    {
      ((ViewGroup)getParent()).removeView(this);
      try
      {
        removeAllViews();
        destroy();
        return;
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  @SuppressLint({"NewApi"})
  public void onPause()
  {
    pauseTimers();
    if (Build.VERSION.SDK_INT >= 11) {
      super.onPause();
    }
  }
  
  @SuppressLint({"NewApi"})
  public void onResume()
  {
    if (Build.VERSION.SDK_INT >= 11) {
      super.onResume();
    }
    resumeTimers();
  }
  
  public void removeHttpHeader(String paramString)
  {
    this.mHttpHeaders.remove(paramString);
  }
  
  public void removePermittedHostname(String paramString)
  {
    this.mPermittedHostnames.remove(paramString);
  }
  
  public void setCookies(String paramString, HashMap<String, String> paramHashMap)
  {
    paramHashMap = paramHashMap.entrySet().iterator();
    while (paramHashMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramHashMap.next();
      CookieManager.getInstance().setCookie(paramString, (String)localEntry.getKey() + "=" + Uri.encode((String)localEntry.getValue()));
    }
  }
  
  public void setCookiesEnabled(boolean paramBoolean)
  {
    CookieManager.getInstance().setAcceptCookie(paramBoolean);
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  public void setGeolocationEnabled(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      getSettings().setJavaScriptEnabled(true);
      getSettings().setGeolocationEnabled(true);
      setGeolocationDatabasePath();
    }
    this.mGeolocationEnabled = paramBoolean;
  }
  
  public void setListener(Activity paramActivity, Listener paramListener)
  {
    setListener(paramActivity, paramListener, 51426);
  }
  
  public void setListener(Fragment paramFragment, Listener paramListener)
  {
    setListener(paramFragment, paramListener, 51426);
  }
  
  public void setMixedContentAllowed(boolean paramBoolean)
  {
    setMixedContentAllowed(getSettings(), paramBoolean);
  }
  
  @SuppressLint({"NewApi"})
  public void setThirdPartyCookiesEnabled(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      CookieManager.getInstance().setAcceptThirdPartyCookies(this, paramBoolean);
    }
  }
  
  public void setUploadableFileTypes(String paramString)
  {
    this.mUploadableFileTypes = paramString;
  }
  
  public void setWebChromeClient(WebChromeClient paramWebChromeClient)
  {
    this.mCustomWebChromeClient = paramWebChromeClient;
  }
  
  public void setWebViewClient(WebViewClient paramWebViewClient)
  {
    this.mCustomWebViewClient = paramWebViewClient;
  }
  
  public static class Browsers
  {
    private static String mAlternativePackage;
    
    public Browsers() {}
    
    static String getAlternative(Context paramContext)
    {
      if (mAlternativePackage != null) {
        return mAlternativePackage;
      }
      List localList = Arrays.asList(PayCitationWebView.ALTERNATIVE_BROWSERS);
      paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if ((localApplicationInfo.enabled) && (localList.contains(localApplicationInfo.packageName)))
        {
          mAlternativePackage = localApplicationInfo.packageName;
          return localApplicationInfo.packageName;
        }
      }
      return null;
    }
    
    public static boolean hasAlternative(Context paramContext)
    {
      return getAlternative(paramContext) != null;
    }
    
    public static void openUrl(Activity paramActivity, String paramString)
    {
      openUrl(paramActivity, paramString, false);
    }
    
    static void openUrl(Activity paramActivity, String paramString, boolean paramBoolean)
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.setPackage(getAlternative(paramActivity));
      paramString.addFlags(268435456);
      paramActivity.startActivity(paramString);
      if (paramBoolean) {
        paramActivity.overridePendingTransition(0, 0);
      }
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void onDownloadRequested(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong);
    
    public abstract void onExternalPageRequest(String paramString);
    
    public abstract void onPageError(int paramInt, String paramString1, String paramString2);
    
    public abstract void onPageFinished(String paramString);
    
    public abstract void onPageStarted(String paramString, Bitmap paramBitmap);
  }
}
