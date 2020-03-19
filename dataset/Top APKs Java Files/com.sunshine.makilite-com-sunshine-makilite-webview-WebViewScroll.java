package com.sunshine.makilite.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
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
import com.sunshine.makilite.helpers.BadgeHelper;
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
import java.util.MissingResourceException;

public class WebViewScroll
  extends WebView
  implements NestedScrollingChild
{
  public static final String PACKAGE_NAME_DOWNLOAD_MANAGER = "com.android.providers.downloads";
  protected static final String[] a = { "org.mozilla.firefox", "com.android.chrome", "com.opera.browser", "org.mozilla.firefox_beta", "com.chrome.beta", "com.opera.browser.beta" };
  protected final List<String> b = new LinkedList();
  protected final Map<String, String> c = new HashMap();
  protected WeakReference<Activity> d;
  protected WeakReference<Fragment> e;
  protected Listener f;
  protected ValueCallback<Uri> g;
  protected ValueCallback<Uri[]> h;
  protected long i;
  protected String j;
  protected int k = 51426;
  protected WebViewClient l;
  protected WebChromeClient m;
  private NestedScrollingChildHelper mChildHelper;
  private int mLastY;
  private int mNestedOffsetY;
  private OnScrollChangedCallback mOnScrollChangedCallback;
  private final int[] mScrollConsumed = new int[2];
  private final int[] mScrollOffset = new int[2];
  protected boolean n;
  protected String o = "*/*";
  
  public WebViewScroll(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public WebViewScroll(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public WebViewScroll(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    try
    {
      if ((paramContext instanceof Activity)) {
        this.d = new WeakReference((Activity)paramContext);
      }
      this.j = getLanguageIso3();
      setFocusable(true);
      setFocusableInTouchMode(true);
      setSaveEnabled(true);
      paramContext = paramContext.getFilesDir().getPath();
      paramContext = paramContext.substring(0, paramContext.lastIndexOf("/")) + "/databases";
      paramAttributeSet = getSettings();
      paramAttributeSet.setAllowFileAccess(false);
      if (Build.VERSION.SDK_INT >= 16)
      {
        paramAttributeSet.setAllowFileAccessFromFileURLs(false);
        paramAttributeSet.setAllowUniversalAccessFromFileURLs(false);
      }
      paramAttributeSet.setBuiltInZoomControls(false);
      paramAttributeSet.setJavaScriptEnabled(true);
      paramAttributeSet.setDomStorageEnabled(true);
      if (Build.VERSION.SDK_INT < 18) {
        paramAttributeSet.setRenderPriority(WebSettings.RenderPriority.HIGH);
      }
      paramAttributeSet.setDatabaseEnabled(true);
      if (Build.VERSION.SDK_INT < 19) {
        paramAttributeSet.setDatabasePath(paramContext);
      }
      setMixedContentAllowed(paramAttributeSet, true);
      setThirdPartyCookiesEnabled(true);
      super.setWebViewClient(new WebViewClient()
      {
        public void doUpdateVisitedHistory(WebView paramAnonymousWebView, String paramAnonymousString, boolean paramAnonymousBoolean)
        {
          if (WebViewScroll.this.l != null)
          {
            WebViewScroll.this.l.doUpdateVisitedHistory(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
            return;
          }
          super.doUpdateVisitedHistory(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
        }
        
        public void onFormResubmission(WebView paramAnonymousWebView, Message paramAnonymousMessage1, Message paramAnonymousMessage2)
        {
          if (WebViewScroll.this.l != null)
          {
            WebViewScroll.this.l.onFormResubmission(paramAnonymousWebView, paramAnonymousMessage1, paramAnonymousMessage2);
            return;
          }
          super.onFormResubmission(paramAnonymousWebView, paramAnonymousMessage1, paramAnonymousMessage2);
        }
        
        public void onLoadResource(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          BadgeHelper.updateNumsService(paramAnonymousWebView);
          if (WebViewScroll.this.l != null)
          {
            WebViewScroll.this.l.onLoadResource(paramAnonymousWebView, paramAnonymousString);
            return;
          }
          super.onLoadResource(paramAnonymousWebView, paramAnonymousString);
        }
        
        public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          if ((!WebViewScroll.this.b()) && (WebViewScroll.this.f != null)) {
            WebViewScroll.this.f.onPageFinished(paramAnonymousString);
          }
          if (WebViewScroll.this.l != null) {
            WebViewScroll.this.l.onPageFinished(paramAnonymousWebView, paramAnonymousString);
          }
        }
        
        public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
        {
          if ((!WebViewScroll.this.b()) && (WebViewScroll.this.f != null)) {
            WebViewScroll.this.f.onPageStarted(paramAnonymousString, paramAnonymousBitmap);
          }
          if (WebViewScroll.this.l != null) {
            WebViewScroll.this.l.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
          }
        }
        
        @SuppressLint({"NewApi"})
        public void onReceivedClientCertRequest(WebView paramAnonymousWebView, ClientCertRequest paramAnonymousClientCertRequest)
        {
          if (Build.VERSION.SDK_INT >= 21)
          {
            if (WebViewScroll.this.l != null) {
              WebViewScroll.this.l.onReceivedClientCertRequest(paramAnonymousWebView, paramAnonymousClientCertRequest);
            }
          }
          else {
            return;
          }
          super.onReceivedClientCertRequest(paramAnonymousWebView, paramAnonymousClientCertRequest);
        }
        
        public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
        {
          WebViewScroll.this.a();
          if (WebViewScroll.this.f != null) {
            WebViewScroll.this.f.onPageError(paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
          }
          if (WebViewScroll.this.l != null) {
            WebViewScroll.this.l.onReceivedError(paramAnonymousWebView, paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
          }
        }
        
        public void onReceivedHttpAuthRequest(WebView paramAnonymousWebView, HttpAuthHandler paramAnonymousHttpAuthHandler, String paramAnonymousString1, String paramAnonymousString2)
        {
          if (WebViewScroll.this.l != null)
          {
            WebViewScroll.this.l.onReceivedHttpAuthRequest(paramAnonymousWebView, paramAnonymousHttpAuthHandler, paramAnonymousString1, paramAnonymousString2);
            return;
          }
          super.onReceivedHttpAuthRequest(paramAnonymousWebView, paramAnonymousHttpAuthHandler, paramAnonymousString1, paramAnonymousString2);
        }
        
        @SuppressLint({"NewApi"})
        public void onReceivedLoginRequest(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3)
        {
          if (Build.VERSION.SDK_INT >= 12)
          {
            if (WebViewScroll.this.l != null) {
              WebViewScroll.this.l.onReceivedLoginRequest(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3);
            }
          }
          else {
            return;
          }
          super.onReceivedLoginRequest(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3);
        }
        
        public void onReceivedSslError(WebView paramAnonymousWebView, SslErrorHandler paramAnonymousSslErrorHandler, SslError paramAnonymousSslError)
        {
          if (WebViewScroll.this.l != null)
          {
            WebViewScroll.this.l.onReceivedSslError(paramAnonymousWebView, paramAnonymousSslErrorHandler, paramAnonymousSslError);
            return;
          }
          super.onReceivedSslError(paramAnonymousWebView, paramAnonymousSslErrorHandler, paramAnonymousSslError);
        }
        
        public void onScaleChanged(WebView paramAnonymousWebView, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          if (WebViewScroll.this.l != null)
          {
            WebViewScroll.this.l.onScaleChanged(paramAnonymousWebView, paramAnonymousFloat1, paramAnonymousFloat2);
            return;
          }
          super.onScaleChanged(paramAnonymousWebView, paramAnonymousFloat1, paramAnonymousFloat2);
        }
        
        public void onUnhandledKeyEvent(WebView paramAnonymousWebView, KeyEvent paramAnonymousKeyEvent)
        {
          if (WebViewScroll.this.l != null)
          {
            WebViewScroll.this.l.onUnhandledKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
            return;
          }
          super.onUnhandledKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
        }
        
        @SuppressLint({"NewApi"})
        public WebResourceResponse shouldInterceptRequest(WebView paramAnonymousWebView, WebResourceRequest paramAnonymousWebResourceRequest)
        {
          if (Build.VERSION.SDK_INT >= 21)
          {
            if (WebViewScroll.this.l != null) {
              return WebViewScroll.this.l.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousWebResourceRequest);
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
            if (WebViewScroll.this.l != null) {
              return WebViewScroll.this.l.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousString);
            }
            return super.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousString);
          }
          return null;
        }
        
        public boolean shouldOverrideKeyEvent(WebView paramAnonymousWebView, KeyEvent paramAnonymousKeyEvent)
        {
          if (WebViewScroll.this.l != null) {
            return WebViewScroll.this.l.shouldOverrideKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
          }
          return super.shouldOverrideKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
        }
        
        public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          if (WebViewScroll.this.a(paramAnonymousString)) {
            if ((WebViewScroll.this.l == null) || (!WebViewScroll.this.l.shouldOverrideUrlLoading(paramAnonymousWebView, paramAnonymousString))) {}
          }
          while (WebViewScroll.this.f == null)
          {
            return true;
            return false;
          }
          WebViewScroll.this.f.onExternalPageRequest(paramAnonymousString);
          return true;
        }
      });
      super.setWebChromeClient(new WebChromeClient()
      {
        public Bitmap getDefaultVideoPoster()
        {
          if (WebViewScroll.this.m != null) {
            return WebViewScroll.this.m.getDefaultVideoPoster();
          }
          return super.getDefaultVideoPoster();
        }
        
        public View getVideoLoadingProgressView()
        {
          if (WebViewScroll.this.m != null) {
            return WebViewScroll.this.m.getVideoLoadingProgressView();
          }
          return super.getVideoLoadingProgressView();
        }
        
        public void getVisitedHistory(ValueCallback<String[]> paramAnonymousValueCallback)
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.getVisitedHistory(paramAnonymousValueCallback);
            return;
          }
          super.getVisitedHistory(paramAnonymousValueCallback);
        }
        
        public void onCloseWindow(WebView paramAnonymousWebView)
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onCloseWindow(paramAnonymousWebView);
            return;
          }
          super.onCloseWindow(paramAnonymousWebView);
        }
        
        public void onConsoleMessage(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2)
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onConsoleMessage(paramAnonymousString1, paramAnonymousInt, paramAnonymousString2);
            return;
          }
          super.onConsoleMessage(paramAnonymousString1, paramAnonymousInt, paramAnonymousString2);
        }
        
        public boolean onConsoleMessage(ConsoleMessage paramAnonymousConsoleMessage)
        {
          if (WebViewScroll.this.m != null) {
            return WebViewScroll.this.m.onConsoleMessage(paramAnonymousConsoleMessage);
          }
          return super.onConsoleMessage(paramAnonymousConsoleMessage);
        }
        
        public boolean onCreateWindow(WebView paramAnonymousWebView, boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2, Message paramAnonymousMessage)
        {
          if (WebViewScroll.this.m != null) {
            return WebViewScroll.this.m.onCreateWindow(paramAnonymousWebView, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousMessage);
          }
          return super.onCreateWindow(paramAnonymousWebView, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousMessage);
        }
        
        public void onExceededDatabaseQuota(String paramAnonymousString1, String paramAnonymousString2, long paramAnonymousLong1, long paramAnonymousLong2, long paramAnonymousLong3, WebStorage.QuotaUpdater paramAnonymousQuotaUpdater)
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onExceededDatabaseQuota(paramAnonymousString1, paramAnonymousString2, paramAnonymousLong1, paramAnonymousLong2, paramAnonymousLong3, paramAnonymousQuotaUpdater);
            return;
          }
          super.onExceededDatabaseQuota(paramAnonymousString1, paramAnonymousString2, paramAnonymousLong1, paramAnonymousLong2, paramAnonymousLong3, paramAnonymousQuotaUpdater);
        }
        
        public void onGeolocationPermissionsHidePrompt()
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onGeolocationPermissionsHidePrompt();
            return;
          }
          super.onGeolocationPermissionsHidePrompt();
        }
        
        public void onGeolocationPermissionsShowPrompt(String paramAnonymousString, GeolocationPermissions.Callback paramAnonymousCallback)
        {
          if (WebViewScroll.this.n)
          {
            paramAnonymousCallback.invoke(paramAnonymousString, true, false);
            return;
          }
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onGeolocationPermissionsShowPrompt(paramAnonymousString, paramAnonymousCallback);
            return;
          }
          super.onGeolocationPermissionsShowPrompt(paramAnonymousString, paramAnonymousCallback);
        }
        
        public void onHideCustomView()
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onHideCustomView();
            return;
          }
          super.onHideCustomView();
        }
        
        public boolean onJsAlert(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
        {
          if (WebViewScroll.this.m != null) {
            return WebViewScroll.this.m.onJsAlert(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
          }
          return super.onJsAlert(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        
        public boolean onJsBeforeUnload(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
        {
          if (WebViewScroll.this.m != null) {
            return WebViewScroll.this.m.onJsBeforeUnload(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
          }
          return super.onJsBeforeUnload(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        
        public boolean onJsConfirm(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
        {
          if (WebViewScroll.this.m != null) {
            return WebViewScroll.this.m.onJsConfirm(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
          }
          return super.onJsConfirm(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        
        public boolean onJsPrompt(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, JsPromptResult paramAnonymousJsPromptResult)
        {
          if (WebViewScroll.this.m != null) {
            return WebViewScroll.this.m.onJsPrompt(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousJsPromptResult);
          }
          return super.onJsPrompt(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousJsPromptResult);
        }
        
        public boolean onJsTimeout()
        {
          if (WebViewScroll.this.m != null) {
            return WebViewScroll.this.m.onJsTimeout();
          }
          return super.onJsTimeout();
        }
        
        @SuppressLint({"NewApi"})
        public void onPermissionRequest(PermissionRequest paramAnonymousPermissionRequest)
        {
          if (Build.VERSION.SDK_INT >= 21)
          {
            if (WebViewScroll.this.m != null) {
              WebViewScroll.this.m.onPermissionRequest(paramAnonymousPermissionRequest);
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
            if (WebViewScroll.this.m != null) {
              WebViewScroll.this.m.onPermissionRequestCanceled(paramAnonymousPermissionRequest);
            }
          }
          else {
            return;
          }
          super.onPermissionRequestCanceled(paramAnonymousPermissionRequest);
        }
        
        public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onProgressChanged(paramAnonymousWebView, paramAnonymousInt);
            return;
          }
          super.onProgressChanged(paramAnonymousWebView, paramAnonymousInt);
        }
        
        public void onReachedMaxAppCacheSize(long paramAnonymousLong1, long paramAnonymousLong2, WebStorage.QuotaUpdater paramAnonymousQuotaUpdater)
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onReachedMaxAppCacheSize(paramAnonymousLong1, paramAnonymousLong2, paramAnonymousQuotaUpdater);
            return;
          }
          super.onReachedMaxAppCacheSize(paramAnonymousLong1, paramAnonymousLong2, paramAnonymousQuotaUpdater);
        }
        
        public void onReceivedIcon(WebView paramAnonymousWebView, Bitmap paramAnonymousBitmap)
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onReceivedIcon(paramAnonymousWebView, paramAnonymousBitmap);
            return;
          }
          super.onReceivedIcon(paramAnonymousWebView, paramAnonymousBitmap);
        }
        
        public void onReceivedTitle(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
            return;
          }
          super.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
        }
        
        public void onReceivedTouchIconUrl(WebView paramAnonymousWebView, String paramAnonymousString, boolean paramAnonymousBoolean)
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onReceivedTouchIconUrl(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
            return;
          }
          super.onReceivedTouchIconUrl(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
        }
        
        public void onRequestFocus(WebView paramAnonymousWebView)
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onRequestFocus(paramAnonymousWebView);
            return;
          }
          super.onRequestFocus(paramAnonymousWebView);
        }
        
        @SuppressLint({"NewApi"})
        public void onShowCustomView(View paramAnonymousView, int paramAnonymousInt, WebChromeClient.CustomViewCallback paramAnonymousCustomViewCallback)
        {
          if (Build.VERSION.SDK_INT >= 14)
          {
            if (WebViewScroll.this.m != null) {
              WebViewScroll.this.m.onShowCustomView(paramAnonymousView, paramAnonymousInt, paramAnonymousCustomViewCallback);
            }
          }
          else {
            return;
          }
          super.onShowCustomView(paramAnonymousView, paramAnonymousInt, paramAnonymousCustomViewCallback);
        }
        
        public void onShowCustomView(View paramAnonymousView, WebChromeClient.CustomViewCallback paramAnonymousCustomViewCallback)
        {
          if (WebViewScroll.this.m != null)
          {
            WebViewScroll.this.m.onShowCustomView(paramAnonymousView, paramAnonymousCustomViewCallback);
            return;
          }
          super.onShowCustomView(paramAnonymousView, paramAnonymousCustomViewCallback);
        }
        
        public boolean onShowFileChooser(WebView paramAnonymousWebView, ValueCallback<Uri[]> paramAnonymousValueCallback, WebChromeClient.FileChooserParams paramAnonymousFileChooserParams)
        {
          WebViewScroll.this.a(null, paramAnonymousValueCallback);
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
          WebViewScroll.this.a(paramAnonymousValueCallback, null);
        }
      });
      setDownloadListener(new DownloadListener()
      {
        public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong)
        {
          if (WebViewScroll.this.f != null) {
            WebViewScroll.this.f.onDownloadRequested(paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousString4, paramAnonymousLong);
          }
        }
      });
      this.mChildHelper = new NestedScrollingChildHelper(this);
      setNestedScrollingEnabled(true);
      return;
    }
    catch (NullPointerException paramContext)
    {
      Log.e("onLoadResourceError", paramContext.getMessage());
      paramContext.printStackTrace();
    }
  }
  
  private static String decodeBase64(String paramString)
  {
    return new String(Base64.decode(paramString, 0), "UTF-8");
  }
  
  protected static String getLanguageIso3()
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
    if (Build.VERSION.SDK_INT < 9) {
      throw new RuntimeException("Method requires API level 9 or above");
    }
    paramString1 = new DownloadManager.Request(Uri.parse(paramString1));
    if (Build.VERSION.SDK_INT >= 11)
    {
      paramString1.allowScanningByMediaScanner();
      paramString1.setNotificationVisibility(1);
    }
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
        if (Build.VERSION.SDK_INT >= 11) {
          paramString1.setNotificationVisibility(0);
        }
        paramString2.enqueue(paramString1);
      }
    }
    catch (IllegalArgumentException paramString1)
    {
      openAppSettings(paramContext, "com.android.providers.downloads");
    }
    return false;
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void init(Context paramContext)
  {
    if ((paramContext instanceof Activity)) {
      this.d = new WeakReference((Activity)paramContext);
    }
    this.j = getLanguageIso3();
    setFocusable(true);
    setFocusableInTouchMode(true);
    setSaveEnabled(true);
    paramContext = paramContext.getFilesDir().getPath();
    paramContext = paramContext.substring(0, paramContext.lastIndexOf("/")) + "/databases";
    WebSettings localWebSettings = getSettings();
    localWebSettings.setAllowFileAccess(false);
    if (Build.VERSION.SDK_INT >= 16)
    {
      localWebSettings.setAllowFileAccessFromFileURLs(false);
      localWebSettings.setAllowUniversalAccessFromFileURLs(false);
    }
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
        if (WebViewScroll.this.l != null)
        {
          WebViewScroll.this.l.doUpdateVisitedHistory(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
          return;
        }
        super.doUpdateVisitedHistory(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
      }
      
      public void onFormResubmission(WebView paramAnonymousWebView, Message paramAnonymousMessage1, Message paramAnonymousMessage2)
      {
        if (WebViewScroll.this.l != null)
        {
          WebViewScroll.this.l.onFormResubmission(paramAnonymousWebView, paramAnonymousMessage1, paramAnonymousMessage2);
          return;
        }
        super.onFormResubmission(paramAnonymousWebView, paramAnonymousMessage1, paramAnonymousMessage2);
      }
      
      public void onLoadResource(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        BadgeHelper.updateNumsService(paramAnonymousWebView);
        if (WebViewScroll.this.l != null)
        {
          WebViewScroll.this.l.onLoadResource(paramAnonymousWebView, paramAnonymousString);
          return;
        }
        super.onLoadResource(paramAnonymousWebView, paramAnonymousString);
      }
      
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if ((!WebViewScroll.this.b()) && (WebViewScroll.this.f != null)) {
          WebViewScroll.this.f.onPageFinished(paramAnonymousString);
        }
        if (WebViewScroll.this.l != null) {
          WebViewScroll.this.l.onPageFinished(paramAnonymousWebView, paramAnonymousString);
        }
      }
      
      public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        if ((!WebViewScroll.this.b()) && (WebViewScroll.this.f != null)) {
          WebViewScroll.this.f.onPageStarted(paramAnonymousString, paramAnonymousBitmap);
        }
        if (WebViewScroll.this.l != null) {
          WebViewScroll.this.l.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
        }
      }
      
      @SuppressLint({"NewApi"})
      public void onReceivedClientCertRequest(WebView paramAnonymousWebView, ClientCertRequest paramAnonymousClientCertRequest)
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          if (WebViewScroll.this.l != null) {
            WebViewScroll.this.l.onReceivedClientCertRequest(paramAnonymousWebView, paramAnonymousClientCertRequest);
          }
        }
        else {
          return;
        }
        super.onReceivedClientCertRequest(paramAnonymousWebView, paramAnonymousClientCertRequest);
      }
      
      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        WebViewScroll.this.a();
        if (WebViewScroll.this.f != null) {
          WebViewScroll.this.f.onPageError(paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
        }
        if (WebViewScroll.this.l != null) {
          WebViewScroll.this.l.onReceivedError(paramAnonymousWebView, paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
        }
      }
      
      public void onReceivedHttpAuthRequest(WebView paramAnonymousWebView, HttpAuthHandler paramAnonymousHttpAuthHandler, String paramAnonymousString1, String paramAnonymousString2)
      {
        if (WebViewScroll.this.l != null)
        {
          WebViewScroll.this.l.onReceivedHttpAuthRequest(paramAnonymousWebView, paramAnonymousHttpAuthHandler, paramAnonymousString1, paramAnonymousString2);
          return;
        }
        super.onReceivedHttpAuthRequest(paramAnonymousWebView, paramAnonymousHttpAuthHandler, paramAnonymousString1, paramAnonymousString2);
      }
      
      @SuppressLint({"NewApi"})
      public void onReceivedLoginRequest(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3)
      {
        if (Build.VERSION.SDK_INT >= 12)
        {
          if (WebViewScroll.this.l != null) {
            WebViewScroll.this.l.onReceivedLoginRequest(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3);
          }
        }
        else {
          return;
        }
        super.onReceivedLoginRequest(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3);
      }
      
      public void onReceivedSslError(WebView paramAnonymousWebView, SslErrorHandler paramAnonymousSslErrorHandler, SslError paramAnonymousSslError)
      {
        if (WebViewScroll.this.l != null)
        {
          WebViewScroll.this.l.onReceivedSslError(paramAnonymousWebView, paramAnonymousSslErrorHandler, paramAnonymousSslError);
          return;
        }
        super.onReceivedSslError(paramAnonymousWebView, paramAnonymousSslErrorHandler, paramAnonymousSslError);
      }
      
      public void onScaleChanged(WebView paramAnonymousWebView, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        if (WebViewScroll.this.l != null)
        {
          WebViewScroll.this.l.onScaleChanged(paramAnonymousWebView, paramAnonymousFloat1, paramAnonymousFloat2);
          return;
        }
        super.onScaleChanged(paramAnonymousWebView, paramAnonymousFloat1, paramAnonymousFloat2);
      }
      
      public void onUnhandledKeyEvent(WebView paramAnonymousWebView, KeyEvent paramAnonymousKeyEvent)
      {
        if (WebViewScroll.this.l != null)
        {
          WebViewScroll.this.l.onUnhandledKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
          return;
        }
        super.onUnhandledKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
      }
      
      @SuppressLint({"NewApi"})
      public WebResourceResponse shouldInterceptRequest(WebView paramAnonymousWebView, WebResourceRequest paramAnonymousWebResourceRequest)
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          if (WebViewScroll.this.l != null) {
            return WebViewScroll.this.l.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousWebResourceRequest);
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
          if (WebViewScroll.this.l != null) {
            return WebViewScroll.this.l.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousString);
          }
          return super.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousString);
        }
        return null;
      }
      
      public boolean shouldOverrideKeyEvent(WebView paramAnonymousWebView, KeyEvent paramAnonymousKeyEvent)
      {
        if (WebViewScroll.this.l != null) {
          return WebViewScroll.this.l.shouldOverrideKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
        }
        return super.shouldOverrideKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
      }
      
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (WebViewScroll.this.a(paramAnonymousString)) {
          if ((WebViewScroll.this.l == null) || (!WebViewScroll.this.l.shouldOverrideUrlLoading(paramAnonymousWebView, paramAnonymousString))) {}
        }
        while (WebViewScroll.this.f == null)
        {
          return true;
          return false;
        }
        WebViewScroll.this.f.onExternalPageRequest(paramAnonymousString);
        return true;
      }
    });
    super.setWebChromeClient(new WebChromeClient()
    {
      public Bitmap getDefaultVideoPoster()
      {
        if (WebViewScroll.this.m != null) {
          return WebViewScroll.this.m.getDefaultVideoPoster();
        }
        return super.getDefaultVideoPoster();
      }
      
      public View getVideoLoadingProgressView()
      {
        if (WebViewScroll.this.m != null) {
          return WebViewScroll.this.m.getVideoLoadingProgressView();
        }
        return super.getVideoLoadingProgressView();
      }
      
      public void getVisitedHistory(ValueCallback<String[]> paramAnonymousValueCallback)
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.getVisitedHistory(paramAnonymousValueCallback);
          return;
        }
        super.getVisitedHistory(paramAnonymousValueCallback);
      }
      
      public void onCloseWindow(WebView paramAnonymousWebView)
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onCloseWindow(paramAnonymousWebView);
          return;
        }
        super.onCloseWindow(paramAnonymousWebView);
      }
      
      public void onConsoleMessage(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2)
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onConsoleMessage(paramAnonymousString1, paramAnonymousInt, paramAnonymousString2);
          return;
        }
        super.onConsoleMessage(paramAnonymousString1, paramAnonymousInt, paramAnonymousString2);
      }
      
      public boolean onConsoleMessage(ConsoleMessage paramAnonymousConsoleMessage)
      {
        if (WebViewScroll.this.m != null) {
          return WebViewScroll.this.m.onConsoleMessage(paramAnonymousConsoleMessage);
        }
        return super.onConsoleMessage(paramAnonymousConsoleMessage);
      }
      
      public boolean onCreateWindow(WebView paramAnonymousWebView, boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2, Message paramAnonymousMessage)
      {
        if (WebViewScroll.this.m != null) {
          return WebViewScroll.this.m.onCreateWindow(paramAnonymousWebView, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousMessage);
        }
        return super.onCreateWindow(paramAnonymousWebView, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousMessage);
      }
      
      public void onExceededDatabaseQuota(String paramAnonymousString1, String paramAnonymousString2, long paramAnonymousLong1, long paramAnonymousLong2, long paramAnonymousLong3, WebStorage.QuotaUpdater paramAnonymousQuotaUpdater)
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onExceededDatabaseQuota(paramAnonymousString1, paramAnonymousString2, paramAnonymousLong1, paramAnonymousLong2, paramAnonymousLong3, paramAnonymousQuotaUpdater);
          return;
        }
        super.onExceededDatabaseQuota(paramAnonymousString1, paramAnonymousString2, paramAnonymousLong1, paramAnonymousLong2, paramAnonymousLong3, paramAnonymousQuotaUpdater);
      }
      
      public void onGeolocationPermissionsHidePrompt()
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onGeolocationPermissionsHidePrompt();
          return;
        }
        super.onGeolocationPermissionsHidePrompt();
      }
      
      public void onGeolocationPermissionsShowPrompt(String paramAnonymousString, GeolocationPermissions.Callback paramAnonymousCallback)
      {
        if (WebViewScroll.this.n)
        {
          paramAnonymousCallback.invoke(paramAnonymousString, true, false);
          return;
        }
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onGeolocationPermissionsShowPrompt(paramAnonymousString, paramAnonymousCallback);
          return;
        }
        super.onGeolocationPermissionsShowPrompt(paramAnonymousString, paramAnonymousCallback);
      }
      
      public void onHideCustomView()
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onHideCustomView();
          return;
        }
        super.onHideCustomView();
      }
      
      public boolean onJsAlert(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
      {
        if (WebViewScroll.this.m != null) {
          return WebViewScroll.this.m.onJsAlert(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        return super.onJsAlert(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
      }
      
      public boolean onJsBeforeUnload(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
      {
        if (WebViewScroll.this.m != null) {
          return WebViewScroll.this.m.onJsBeforeUnload(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        return super.onJsBeforeUnload(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
      }
      
      public boolean onJsConfirm(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
      {
        if (WebViewScroll.this.m != null) {
          return WebViewScroll.this.m.onJsConfirm(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        return super.onJsConfirm(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
      }
      
      public boolean onJsPrompt(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, JsPromptResult paramAnonymousJsPromptResult)
      {
        if (WebViewScroll.this.m != null) {
          return WebViewScroll.this.m.onJsPrompt(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousJsPromptResult);
        }
        return super.onJsPrompt(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousJsPromptResult);
      }
      
      public boolean onJsTimeout()
      {
        if (WebViewScroll.this.m != null) {
          return WebViewScroll.this.m.onJsTimeout();
        }
        return super.onJsTimeout();
      }
      
      @SuppressLint({"NewApi"})
      public void onPermissionRequest(PermissionRequest paramAnonymousPermissionRequest)
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          if (WebViewScroll.this.m != null) {
            WebViewScroll.this.m.onPermissionRequest(paramAnonymousPermissionRequest);
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
          if (WebViewScroll.this.m != null) {
            WebViewScroll.this.m.onPermissionRequestCanceled(paramAnonymousPermissionRequest);
          }
        }
        else {
          return;
        }
        super.onPermissionRequestCanceled(paramAnonymousPermissionRequest);
      }
      
      public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onProgressChanged(paramAnonymousWebView, paramAnonymousInt);
          return;
        }
        super.onProgressChanged(paramAnonymousWebView, paramAnonymousInt);
      }
      
      public void onReachedMaxAppCacheSize(long paramAnonymousLong1, long paramAnonymousLong2, WebStorage.QuotaUpdater paramAnonymousQuotaUpdater)
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onReachedMaxAppCacheSize(paramAnonymousLong1, paramAnonymousLong2, paramAnonymousQuotaUpdater);
          return;
        }
        super.onReachedMaxAppCacheSize(paramAnonymousLong1, paramAnonymousLong2, paramAnonymousQuotaUpdater);
      }
      
      public void onReceivedIcon(WebView paramAnonymousWebView, Bitmap paramAnonymousBitmap)
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onReceivedIcon(paramAnonymousWebView, paramAnonymousBitmap);
          return;
        }
        super.onReceivedIcon(paramAnonymousWebView, paramAnonymousBitmap);
      }
      
      public void onReceivedTitle(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
          return;
        }
        super.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
      }
      
      public void onReceivedTouchIconUrl(WebView paramAnonymousWebView, String paramAnonymousString, boolean paramAnonymousBoolean)
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onReceivedTouchIconUrl(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
          return;
        }
        super.onReceivedTouchIconUrl(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
      }
      
      public void onRequestFocus(WebView paramAnonymousWebView)
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onRequestFocus(paramAnonymousWebView);
          return;
        }
        super.onRequestFocus(paramAnonymousWebView);
      }
      
      @SuppressLint({"NewApi"})
      public void onShowCustomView(View paramAnonymousView, int paramAnonymousInt, WebChromeClient.CustomViewCallback paramAnonymousCustomViewCallback)
      {
        if (Build.VERSION.SDK_INT >= 14)
        {
          if (WebViewScroll.this.m != null) {
            WebViewScroll.this.m.onShowCustomView(paramAnonymousView, paramAnonymousInt, paramAnonymousCustomViewCallback);
          }
        }
        else {
          return;
        }
        super.onShowCustomView(paramAnonymousView, paramAnonymousInt, paramAnonymousCustomViewCallback);
      }
      
      public void onShowCustomView(View paramAnonymousView, WebChromeClient.CustomViewCallback paramAnonymousCustomViewCallback)
      {
        if (WebViewScroll.this.m != null)
        {
          WebViewScroll.this.m.onShowCustomView(paramAnonymousView, paramAnonymousCustomViewCallback);
          return;
        }
        super.onShowCustomView(paramAnonymousView, paramAnonymousCustomViewCallback);
      }
      
      public boolean onShowFileChooser(WebView paramAnonymousWebView, ValueCallback<Uri[]> paramAnonymousValueCallback, WebChromeClient.FileChooserParams paramAnonymousFileChooserParams)
      {
        WebViewScroll.this.a(null, paramAnonymousValueCallback);
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
        WebViewScroll.this.a(paramAnonymousValueCallback, null);
      }
    });
    setDownloadListener(new DownloadListener()
    {
      public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong)
      {
        if (WebViewScroll.this.f != null) {
          WebViewScroll.this.f.onDownloadRequested(paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousString4, paramAnonymousLong);
        }
      }
    });
  }
  
  public static boolean isFileUploadAvailable()
  {
    return isFileUploadAvailable(false);
  }
  
  public static boolean isFileUploadAvailable(boolean paramBoolean)
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
  private static boolean openAppSettings(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT < 9) {
      throw new RuntimeException("Method requires API level 9 or above");
    }
    try
    {
      Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.parse("package:" + paramString));
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  @SuppressLint({"NewApi"})
  private static void setAllowAccessFromFileUrls(WebSettings paramWebSettings, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramWebSettings.setAllowFileAccessFromFileURLs(false);
      paramWebSettings.setAllowUniversalAccessFromFileURLs(false);
    }
  }
  
  private void setListener(Listener paramListener, int paramInt)
  {
    this.f = paramListener;
    this.k = paramInt;
  }
  
  @SuppressLint({"NewApi"})
  private static void setMixedContentAllowed(WebSettings paramWebSettings, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      if (!paramBoolean) {
        break label20;
      }
    }
    label20:
    for (int i1 = 0;; i1 = 1)
    {
      paramWebSettings.setMixedContentMode(i1);
      return;
    }
  }
  
  protected final void a()
  {
    this.i = System.currentTimeMillis();
  }
  
  @SuppressLint({"NewApi"})
  protected final void a(ValueCallback<Uri> paramValueCallback, ValueCallback<Uri[]> paramValueCallback1)
  {
    if (this.g != null) {
      this.g.onReceiveValue(null);
    }
    this.g = paramValueCallback;
    if (this.h != null) {
      this.h.onReceiveValue(null);
    }
    this.h = paramValueCallback1;
    paramValueCallback = new Intent("android.intent.action.GET_CONTENT");
    paramValueCallback.addCategory("android.intent.category.OPENABLE");
    paramValueCallback.setType(this.o);
    if ((this.e != null) && (this.e.get() != null) && (Build.VERSION.SDK_INT >= 11)) {
      ((Fragment)this.e.get()).startActivityForResult(Intent.createChooser(paramValueCallback, getFileUploadPromptLabel()), this.k);
    }
    while ((this.d == null) || (this.d.get() == null)) {
      return;
    }
    ((Activity)this.d.get()).startActivityForResult(Intent.createChooser(paramValueCallback, getFileUploadPromptLabel()), this.k);
  }
  
  protected final boolean a(String paramString)
  {
    if (this.b.size() == 0) {
      return true;
    }
    paramString = Uri.parse(paramString).getHost();
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (paramString.startsWith(str)) {
        return true;
      }
      if ((str.startsWith("*.")) && (paramString.endsWith(str.substring(2)))) {
        return true;
      }
    }
    return false;
  }
  
  public void addHttpHeader(String paramString1, String paramString2)
  {
    this.c.put(paramString1, paramString2);
  }
  
  public void addPermittedHostname(String paramString)
  {
    this.b.add(paramString);
  }
  
  public void addPermittedHostnames(Collection<? extends String> paramCollection)
  {
    this.b.addAll(paramCollection);
  }
  
  protected final boolean b()
  {
    return this.i + 500L >= System.currentTimeMillis();
  }
  
  public void clearPermittedHostnames()
  {
    this.b.clear();
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    return this.mChildHelper.dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    return this.mChildHelper.dispatchNestedPreFling(paramFloat1, paramFloat2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    return this.mChildHelper.dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    return this.mChildHelper.dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
  }
  
  protected String getFileUploadPromptLabel()
  {
    for (;;)
    {
      try
      {
        str = this.j;
        i1 = -1;
        int i2 = str.hashCode();
        switch (i2)
        {
        default: 
          switch (i1)
          {
          }
          break;
        }
      }
      catch (Exception localException)
      {
        String str;
        int i1;
        continue;
      }
      return "Choose a file";
      if (str.equals("zho"))
      {
        i1 = 0;
        continue;
        if (str.equals("spa"))
        {
          i1 = 1;
          continue;
          if (str.equals("hin"))
          {
            i1 = 2;
            continue;
            if (str.equals("ben"))
            {
              i1 = 3;
              continue;
              if (str.equals("ara"))
              {
                i1 = 4;
                continue;
                if (str.equals("por"))
                {
                  i1 = 5;
                  continue;
                  if (str.equals("rus"))
                  {
                    i1 = 6;
                    continue;
                    if (str.equals("jpn"))
                    {
                      i1 = 7;
                      continue;
                      if (str.equals("pan"))
                      {
                        i1 = 8;
                        continue;
                        if (str.equals("deu"))
                        {
                          i1 = 9;
                          continue;
                          if (str.equals("jav"))
                          {
                            i1 = 10;
                            continue;
                            if (str.equals("msa"))
                            {
                              i1 = 11;
                              continue;
                              if (str.equals("tel"))
                              {
                                i1 = 12;
                                continue;
                                if (str.equals("vie"))
                                {
                                  i1 = 13;
                                  continue;
                                  if (str.equals("kor"))
                                  {
                                    i1 = 14;
                                    continue;
                                    if (str.equals("fra"))
                                    {
                                      i1 = 15;
                                      continue;
                                      if (str.equals("mar"))
                                      {
                                        i1 = 16;
                                        continue;
                                        if (str.equals("tam"))
                                        {
                                          i1 = 17;
                                          continue;
                                          if (str.equals("urd"))
                                          {
                                            i1 = 18;
                                            continue;
                                            if (str.equals("fas"))
                                            {
                                              i1 = 19;
                                              continue;
                                              if (str.equals("tur"))
                                              {
                                                i1 = 20;
                                                continue;
                                                if (str.equals("ita"))
                                                {
                                                  i1 = 21;
                                                  continue;
                                                  if (str.equals("tha"))
                                                  {
                                                    i1 = 22;
                                                    continue;
                                                    if (str.equals("guj")) {
                                                      i1 = 23;
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return decodeBase64("6YCJ5oup5LiA5Liq5paH5Lu2");
    return decodeBase64("RWxpamEgdW4gYXJjaGl2bw==");
    return decodeBase64("4KSP4KSVIOCkq+CkvOCkvuCkh+CksiDgpJrgpYHgpKjgpYfgpII=");
    return decodeBase64("4KaP4KaV4Kaf4Ka/IOCmq+CmvuCmh+CmsiDgpqjgpr/gprDgp43gpqzgpr7gpprgpqg=");
    return decodeBase64("2KfYrtiq2YrYp9ixINmF2YTZgSDZiNin2K3Yrw==");
    return decodeBase64("RXNjb2xoYSB1bSBhcnF1aXZv");
    return decodeBase64("0JLRi9Cx0LXRgNC40YLQtSDQvtC00LjQvSDRhNCw0LnQuw==");
    return decodeBase64("MeODleOCoeOCpOODq+OCkumBuOaKnuOBl+OBpuOBj+OBoOOBleOBhA==");
    return decodeBase64("4KiH4Kmx4KiVIOCoq+CovuCoh+CosiDgqJrgqYHgqKPgqYs=");
    return decodeBase64("V8OkaGxlIGVpbmUgRGF0ZWk=");
    return decodeBase64("UGlsaWggc2lqaSBiZXJrYXM=");
    return decodeBase64("UGlsaWggc2F0dSBmYWls");
    return decodeBase64("4LCS4LCVIOCwq+CxhuCxluCwsuCxjeCwqOCxgSDgsI7gsILgsJrgsYHgsJXgsYvgsILgsKHgsL8=");
    return decodeBase64("Q2jhu41uIG3hu5l0IHThuq1wIHRpbg==");
    return decodeBase64("7ZWY64KY7J2YIO2MjOydvOydhCDshKDtg50=");
    return decodeBase64("Q2hvaXNpc3NleiB1biBmaWNoaWVy");
    return decodeBase64("4KSr4KS+4KSH4KSyIOCkqOCkv+CkteCkoeCkvg==");
    return decodeBase64("4K6S4K6w4K+BIOCuleCvh+CuvuCuquCvjeCuquCviCDgrqTgr4fgrrDgr43grrXgr4E=");
    return decodeBase64("2KfbjNqpINmB2KfYptmEINmF24zauiDYs9uSINin2YbYqtiu2KfYqCDaqdix24zaug==");
    return decodeBase64("2LHYpyDYp9mG2KrYrtin2Kgg2qnZhtuM2K8g24zaqSDZgdin24zZhA==");
    return decodeBase64("QmlyIGRvc3lhIHNlw6dpbg==");
    return decodeBase64("U2NlZ2xpIHVuIGZpbGU=");
    return decodeBase64("4LmA4Lil4Li34Lit4LiB4LmE4Lif4Lil4LmM4Lir4LiZ4Li24LmI4LiH");
    str = decodeBase64("4KqP4KqVIOCqq+CqvuCqh+CqsuCqqOCrhyDgqqrgqrjgqoLgqqY=");
    return str;
  }
  
  public OnScrollChangedCallback getOnScrollChangedCallback()
  {
    return this.mOnScrollChangedCallback;
  }
  
  public List<String> getPermittedHostnames()
  {
    return this.b;
  }
  
  public boolean hasNestedScrollingParent()
  {
    return this.mChildHelper.hasNestedScrollingParent();
  }
  
  public boolean isNestedScrollingEnabled()
  {
    return this.mChildHelper.isNestedScrollingEnabled();
  }
  
  public void loadUrl(String paramString)
  {
    if (this.c.size() > 0)
    {
      super.loadUrl(paramString, this.c);
      return;
    }
    super.loadUrl(paramString);
  }
  
  public void loadUrl(String paramString, Map<String, String> paramMap)
  {
    Object localObject;
    if (paramMap == null) {
      localObject = this.c;
    }
    for (;;)
    {
      super.loadUrl(paramString, (Map)localObject);
      return;
      localObject = paramMap;
      if (this.c.size() > 0)
      {
        paramMap.putAll(this.c);
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
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == this.k)
    {
      if (paramInt2 != -1) {
        break label92;
      }
      if (paramIntent != null)
      {
        if (this.g == null) {
          break label43;
        }
        this.g.onReceiveValue(paramIntent.getData());
        this.g = null;
      }
    }
    label43:
    label92:
    do
    {
      do
      {
        return;
      } while (this.h == null);
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
      this.h.onReceiveValue(paramIntent);
      this.h = null;
      return;
      if (this.g != null)
      {
        this.g.onReceiveValue(null);
        this.g = null;
        return;
      }
    } while (this.h == null);
    this.h.onReceiveValue(null);
    this.h = null;
  }
  
  public boolean onBackPressed()
  {
    if (canGoBack())
    {
      goBack();
      return false;
    }
    return true;
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
  
  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.f.onScrollChange(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mOnScrollChangedCallback != null) {
      this.mOnScrollChangedCallback.onScroll(paramInt1, paramInt2);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i1 = MotionEventCompat.getActionMasked(paramMotionEvent);
    if (i1 == 0) {
      this.mNestedOffsetY = 0;
    }
    int i3 = (int)paramMotionEvent.getY();
    paramMotionEvent.offsetLocation(0.0F, this.mNestedOffsetY);
    boolean bool;
    switch (i1)
    {
    default: 
      return false;
    case 0: 
      bool = super.onTouchEvent(paramMotionEvent);
      this.mLastY = i3;
      return bool;
    case 2: 
      int i2 = this.mLastY - i3;
      int i4 = getScrollY();
      startNestedScroll(2);
      i1 = i2;
      if (dispatchNestedPreScroll(0, i2, this.mScrollConsumed, this.mScrollOffset))
      {
        i1 = i2 - this.mScrollConsumed[1];
        paramMotionEvent.offsetLocation(0.0F, -this.mScrollOffset[1]);
        this.mNestedOffsetY += this.mScrollOffset[1];
      }
      bool = super.onTouchEvent(paramMotionEvent);
      this.mLastY = (i3 - this.mScrollOffset[1]);
      if (i1 < 0)
      {
        i2 = Math.max(0, i4 + i1);
        i1 -= i2 - i4;
        if (dispatchNestedScroll(0, i2 - i1, 0, i1, this.mScrollOffset))
        {
          paramMotionEvent.offsetLocation(0.0F, this.mScrollOffset[1]);
          this.mNestedOffsetY += this.mScrollOffset[1];
          this.mLastY -= this.mScrollOffset[1];
        }
        return bool;
      }
      break;
    case 1: 
    case 3: 
      bool = super.onTouchEvent(paramMotionEvent);
      stopNestedScroll();
      return bool;
    }
    return bool;
  }
  
  public void removeHttpHeader(String paramString)
  {
    this.c.remove(paramString);
  }
  
  public void removePermittedHostname(String paramString)
  {
    this.b.remove(paramString);
  }
  
  public void setCookiesEnabled(boolean paramBoolean)
  {
    CookieManager.getInstance().setAcceptCookie(paramBoolean);
  }
  
  public void setListener(Activity paramActivity, Listener paramListener)
  {
    setListener(paramActivity, paramListener, 51426);
  }
  
  public void setListener(Activity paramActivity, Listener paramListener, int paramInt)
  {
    if (paramActivity != null) {}
    for (this.d = new WeakReference(paramActivity);; this.d = null)
    {
      setListener(paramListener, paramInt);
      return;
    }
  }
  
  public void setListener(Fragment paramFragment, Listener paramListener)
  {
    setListener(paramFragment, paramListener, 51426);
  }
  
  public void setListener(Fragment paramFragment, Listener paramListener, int paramInt)
  {
    if (paramFragment != null) {}
    for (this.e = new WeakReference(paramFragment);; this.e = null)
    {
      setListener(paramListener, paramInt);
      return;
    }
  }
  
  public void setMixedContentAllowed(boolean paramBoolean)
  {
    setMixedContentAllowed(getSettings(), paramBoolean);
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    this.mChildHelper.setNestedScrollingEnabled(paramBoolean);
  }
  
  public void setOnScrollChangedCallback(OnScrollChangedCallback paramOnScrollChangedCallback)
  {
    this.mOnScrollChangedCallback = paramOnScrollChangedCallback;
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
    this.o = paramString;
  }
  
  public void setWebChromeClient(WebChromeClient paramWebChromeClient)
  {
    this.m = paramWebChromeClient;
  }
  
  public void setWebViewClient(WebViewClient paramWebViewClient)
  {
    this.l = paramWebViewClient;
  }
  
  public boolean startNestedScroll(int paramInt)
  {
    return this.mChildHelper.startNestedScroll(paramInt);
  }
  
  public void stopNestedScroll()
  {
    this.mChildHelper.stopNestedScroll();
  }
  
  public static class Browsers
  {
    private static String mAlternativePackage;
    
    public Browsers() {}
    
    private static String getAlternative(Context paramContext)
    {
      if (mAlternativePackage != null) {
        return mAlternativePackage;
      }
      List localList = Arrays.asList(WebViewScroll.a);
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
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.setPackage(getAlternative(paramActivity));
      paramString.addFlags(268435456);
      paramActivity.startActivity(paramString);
    }
    
    private static void openUrl(Activity paramActivity, String paramString, boolean paramBoolean)
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.setPackage(getAlternative(paramActivity));
      paramString.addFlags(268435456);
      paramActivity.startActivity(paramString);
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void onDownloadRequested(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong);
    
    public abstract void onExternalPageRequest(String paramString);
    
    public abstract void onPageError(int paramInt, String paramString1, String paramString2);
    
    public abstract void onPageFinished(String paramString);
    
    public abstract void onPageStarted(String paramString, Bitmap paramBitmap);
    
    public abstract void onScrollChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
  
  public static abstract interface OnScrollChangedCallback
  {
    public abstract void onScroll(int paramInt1, int paramInt2);
  }
}
