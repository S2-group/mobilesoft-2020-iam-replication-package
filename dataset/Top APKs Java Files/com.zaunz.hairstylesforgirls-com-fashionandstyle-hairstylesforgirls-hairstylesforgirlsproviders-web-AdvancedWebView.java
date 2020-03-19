package com.fashionandstyle.hairstylesforgirls.hairstylesforgirlsproviders.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipData.Item;
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
import android.util.AttributeSet;
import android.util.Base64;
import android.view.KeyEvent;
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
import android.webkit.URLUtil;
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
import java.io.File;
import java.io.UnsupportedEncodingException;
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

public class AdvancedWebView
  extends WebView
{
  protected static final String[] ALTERNATIVE_BROWSERS = { "org.mozilla.firefox", "com.android.chrome", "com.opera.browser", "org.mozilla.firefox_beta", "com.chrome.beta", "com.opera.browser.beta" };
  protected static final String CHARSET_DEFAULT = "UTF-8";
  protected static final String DATABASES_SUB_FOLDER = "/databases";
  protected static final String LANGUAGE_DEFAULT_ISO3 = "eng";
  public static final String PACKAGE_NAME_DOWNLOAD_MANAGER = "com.android.providers.downloads";
  protected static final int REQUEST_CODE_FILE_PICKER = 51426;
  protected WeakReference<Activity> mActivity;
  protected WebChromeClient mCustomWebChromeClient;
  protected WebViewClient mCustomWebViewClient;
  protected ValueCallback<Uri> mFileUploadCallbackFirst;
  protected ValueCallback<Uri[]> mFileUploadCallbackSecond;
  protected WeakReference<Fragment> mFragment;
  protected boolean mGeolocationEnabled;
  protected final Map<String, String> mHttpHeaders = new HashMap();
  protected String mLanguageIso3;
  protected long mLastError;
  protected Listener mListener;
  protected final List<String> mPermittedHostnames = new LinkedList();
  protected int mRequestCodeFilePicker = 51426;
  protected String mUploadableFileTypes = "*/*";
  
  public AdvancedWebView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public AdvancedWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public AdvancedWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  protected static String decodeBase64(String paramString)
    throws IllegalArgumentException, UnsupportedEncodingException
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
    catch (MissingResourceException localMissingResourceException)
    {
      for (;;) {}
    }
    return "eng";
  }
  
  @SuppressLint({"NewApi"})
  public static boolean handleDownload(Context paramContext, String paramString1, String paramString2)
  {
    if (Build.VERSION.SDK_INT >= 9)
    {
      paramString1 = new DownloadManager.Request(Uri.parse(paramString1));
      if (Build.VERSION.SDK_INT >= 11)
      {
        paramString1.allowScanningByMediaScanner();
        paramString1.setNotificationVisibility(1);
      }
      paramString1.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, paramString2);
      paramString2 = (DownloadManager)paramContext.getSystemService("download");
    }
    try
    {
      paramString2.enqueue(paramString1);
      return true;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
    catch (IllegalArgumentException paramString1)
    {
      for (;;) {}
    }
    if (Build.VERSION.SDK_INT >= 11) {
      paramString1.setNotificationVisibility(0);
    }
    paramString2.enqueue(paramString1);
    return true;
    openAppSettings(paramContext, "com.android.providers.downloads");
    return false;
    throw new RuntimeException("Method requires API level 9 or above");
  }
  
  public static boolean isFileUploadAvailable()
  {
    return isFileUploadAvailable(false);
  }
  
  public static boolean isFileUploadAvailable(boolean paramBoolean)
  {
    int i = Build.VERSION.SDK_INT;
    boolean bool = true;
    if (i == 19)
    {
      String str;
      if (Build.VERSION.RELEASE == null) {
        str = "";
      } else {
        str = Build.VERSION.RELEASE;
      }
      if (!paramBoolean)
      {
        paramBoolean = bool;
        if (str.startsWith("4.4.3")) {
          break label56;
        }
        if (str.startsWith("4.4.4")) {
          return true;
        }
      }
      paramBoolean = false;
      label56:
      return paramBoolean;
    }
    return true;
  }
  
  protected static String makeUrlUnique(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    if (paramString.contains("?"))
    {
      localStringBuilder.append('&');
    }
    else
    {
      if (paramString.lastIndexOf('/') <= 7) {
        localStringBuilder.append('/');
      }
      localStringBuilder.append('?');
    }
    localStringBuilder.append(System.currentTimeMillis());
    localStringBuilder.append('=');
    localStringBuilder.append(1);
    return localStringBuilder.toString();
  }
  
  @SuppressLint({"NewApi"})
  private static boolean openAppSettings(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 9) {}
    try
    {
      Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("package:");
      localStringBuilder.append(paramString);
      localIntent.setData(Uri.parse(localStringBuilder.toString()));
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return false;
    throw new RuntimeException("Method requires API level 9 or above");
  }
  
  @SuppressLint({"NewApi"})
  protected static void setAllowAccessFromFileUrls(WebSettings paramWebSettings, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramWebSettings.setAllowFileAccessFromFileURLs(paramBoolean);
      paramWebSettings.setAllowUniversalAccessFromFileURLs(paramBoolean);
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
  
  protected String getFileUploadPromptLabel()
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
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "Choose a file";
  }
  
  public List<String> getPermittedHostnames()
  {
    return this.mPermittedHostnames;
  }
  
  protected boolean hasError()
  {
    return this.mLastError + 500L >= System.currentTimeMillis();
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  protected void init(Context paramContext)
  {
    if (isInEditMode()) {
      return;
    }
    if ((paramContext instanceof Activity)) {
      this.mActivity = new WeakReference((Activity)paramContext);
    }
    this.mLanguageIso3 = getLanguageIso3();
    setFocusable(true);
    setFocusableInTouchMode(true);
    setSaveEnabled(true);
    paramContext = paramContext.getFilesDir().getPath();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramContext.substring(0, paramContext.lastIndexOf("/")));
    ((StringBuilder)localObject).append("/databases");
    paramContext = ((StringBuilder)localObject).toString();
    localObject = getSettings();
    ((WebSettings)localObject).setAllowFileAccess(false);
    setAllowAccessFromFileUrls((WebSettings)localObject, false);
    ((WebSettings)localObject).setBuiltInZoomControls(false);
    if (Build.VERSION.SDK_INT >= 21) {
      CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
    }
    ((WebSettings)localObject).setJavaScriptEnabled(true);
    ((WebSettings)localObject).setDomStorageEnabled(true);
    if (Build.VERSION.SDK_INT < 18) {
      ((WebSettings)localObject).setRenderPriority(WebSettings.RenderPriority.HIGH);
    }
    ((WebSettings)localObject).setDatabaseEnabled(true);
    if (Build.VERSION.SDK_INT < 19) {
      ((WebSettings)localObject).setDatabasePath(paramContext);
    }
    setMixedContentAllowed((WebSettings)localObject, true);
    setThirdPartyCookiesEnabled(true);
    super.setWebViewClient(new WebViewClient()
    {
      public void doUpdateVisitedHistory(WebView paramAnonymousWebView, String paramAnonymousString, boolean paramAnonymousBoolean)
      {
        if (AdvancedWebView.this.mCustomWebViewClient != null)
        {
          AdvancedWebView.this.mCustomWebViewClient.doUpdateVisitedHistory(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
          return;
        }
        super.doUpdateVisitedHistory(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
      }
      
      public void onFormResubmission(WebView paramAnonymousWebView, Message paramAnonymousMessage1, Message paramAnonymousMessage2)
      {
        if (AdvancedWebView.this.mCustomWebViewClient != null)
        {
          AdvancedWebView.this.mCustomWebViewClient.onFormResubmission(paramAnonymousWebView, paramAnonymousMessage1, paramAnonymousMessage2);
          return;
        }
        super.onFormResubmission(paramAnonymousWebView, paramAnonymousMessage1, paramAnonymousMessage2);
      }
      
      public void onLoadResource(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (AdvancedWebView.this.mCustomWebViewClient != null)
        {
          AdvancedWebView.this.mCustomWebViewClient.onLoadResource(paramAnonymousWebView, paramAnonymousString);
          return;
        }
        super.onLoadResource(paramAnonymousWebView, paramAnonymousString);
      }
      
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if ((!AdvancedWebView.this.hasError()) && (AdvancedWebView.this.mListener != null)) {
          AdvancedWebView.this.mListener.onPageFinished(paramAnonymousString);
        }
        if (AdvancedWebView.this.mCustomWebViewClient != null) {
          AdvancedWebView.this.mCustomWebViewClient.onPageFinished(paramAnonymousWebView, paramAnonymousString);
        }
      }
      
      public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        if ((!AdvancedWebView.this.hasError()) && (AdvancedWebView.this.mListener != null)) {
          AdvancedWebView.this.mListener.onPageStarted(paramAnonymousString, paramAnonymousBitmap);
        }
        if (AdvancedWebView.this.mCustomWebViewClient != null) {
          AdvancedWebView.this.mCustomWebViewClient.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
        }
      }
      
      @SuppressLint({"NewApi"})
      public void onReceivedClientCertRequest(WebView paramAnonymousWebView, ClientCertRequest paramAnonymousClientCertRequest)
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          if (AdvancedWebView.this.mCustomWebViewClient != null)
          {
            AdvancedWebView.this.mCustomWebViewClient.onReceivedClientCertRequest(paramAnonymousWebView, paramAnonymousClientCertRequest);
            return;
          }
          super.onReceivedClientCertRequest(paramAnonymousWebView, paramAnonymousClientCertRequest);
        }
      }
      
      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        AdvancedWebView.this.setLastError();
        if (AdvancedWebView.this.mListener != null) {
          AdvancedWebView.this.mListener.onPageError(paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
        }
        if (AdvancedWebView.this.mCustomWebViewClient != null) {
          AdvancedWebView.this.mCustomWebViewClient.onReceivedError(paramAnonymousWebView, paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
        }
      }
      
      public void onReceivedHttpAuthRequest(WebView paramAnonymousWebView, HttpAuthHandler paramAnonymousHttpAuthHandler, String paramAnonymousString1, String paramAnonymousString2)
      {
        if (AdvancedWebView.this.mCustomWebViewClient != null)
        {
          AdvancedWebView.this.mCustomWebViewClient.onReceivedHttpAuthRequest(paramAnonymousWebView, paramAnonymousHttpAuthHandler, paramAnonymousString1, paramAnonymousString2);
          return;
        }
        super.onReceivedHttpAuthRequest(paramAnonymousWebView, paramAnonymousHttpAuthHandler, paramAnonymousString1, paramAnonymousString2);
      }
      
      @SuppressLint({"NewApi"})
      public void onReceivedLoginRequest(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3)
      {
        if (Build.VERSION.SDK_INT >= 12)
        {
          if (AdvancedWebView.this.mCustomWebViewClient != null)
          {
            AdvancedWebView.this.mCustomWebViewClient.onReceivedLoginRequest(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3);
            return;
          }
          super.onReceivedLoginRequest(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3);
        }
      }
      
      public void onReceivedSslError(WebView paramAnonymousWebView, SslErrorHandler paramAnonymousSslErrorHandler, SslError paramAnonymousSslError)
      {
        if (AdvancedWebView.this.mCustomWebViewClient != null)
        {
          AdvancedWebView.this.mCustomWebViewClient.onReceivedSslError(paramAnonymousWebView, paramAnonymousSslErrorHandler, paramAnonymousSslError);
          return;
        }
        super.onReceivedSslError(paramAnonymousWebView, paramAnonymousSslErrorHandler, paramAnonymousSslError);
      }
      
      public void onScaleChanged(WebView paramAnonymousWebView, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        if (AdvancedWebView.this.mCustomWebViewClient != null)
        {
          AdvancedWebView.this.mCustomWebViewClient.onScaleChanged(paramAnonymousWebView, paramAnonymousFloat1, paramAnonymousFloat2);
          return;
        }
        super.onScaleChanged(paramAnonymousWebView, paramAnonymousFloat1, paramAnonymousFloat2);
      }
      
      public void onUnhandledKeyEvent(WebView paramAnonymousWebView, KeyEvent paramAnonymousKeyEvent)
      {
        if (AdvancedWebView.this.mCustomWebViewClient != null)
        {
          AdvancedWebView.this.mCustomWebViewClient.onUnhandledKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
          return;
        }
        super.onUnhandledKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
      }
      
      @SuppressLint({"NewApi"})
      public WebResourceResponse shouldInterceptRequest(WebView paramAnonymousWebView, WebResourceRequest paramAnonymousWebResourceRequest)
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          if (AdvancedWebView.this.mCustomWebViewClient != null) {
            return AdvancedWebView.this.mCustomWebViewClient.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousWebResourceRequest);
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
          if (AdvancedWebView.this.mCustomWebViewClient != null) {
            return AdvancedWebView.this.mCustomWebViewClient.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousString);
          }
          return super.shouldInterceptRequest(paramAnonymousWebView, paramAnonymousString);
        }
        return null;
      }
      
      public boolean shouldOverrideKeyEvent(WebView paramAnonymousWebView, KeyEvent paramAnonymousKeyEvent)
      {
        if (AdvancedWebView.this.mCustomWebViewClient != null) {
          return AdvancedWebView.this.mCustomWebViewClient.shouldOverrideKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
        }
        return super.shouldOverrideKeyEvent(paramAnonymousWebView, paramAnonymousKeyEvent);
      }
      
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (!AdvancedWebView.this.isHostnameAllowed(paramAnonymousString))
        {
          if (AdvancedWebView.this.mListener != null) {
            AdvancedWebView.this.mListener.onExternalPageRequest(paramAnonymousString);
          }
          return true;
        }
        if ((AdvancedWebView.this.mCustomWebViewClient != null) && (AdvancedWebView.this.mCustomWebViewClient.shouldOverrideUrlLoading(paramAnonymousWebView, paramAnonymousString))) {
          return true;
        }
        paramAnonymousWebView.loadUrl(paramAnonymousString);
        return true;
      }
    });
    super.setWebChromeClient(new WebChromeClient()
    {
      public Bitmap getDefaultVideoPoster()
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null) {
          return AdvancedWebView.this.mCustomWebChromeClient.getDefaultVideoPoster();
        }
        return super.getDefaultVideoPoster();
      }
      
      public View getVideoLoadingProgressView()
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null) {
          return AdvancedWebView.this.mCustomWebChromeClient.getVideoLoadingProgressView();
        }
        return super.getVideoLoadingProgressView();
      }
      
      public void getVisitedHistory(ValueCallback<String[]> paramAnonymousValueCallback)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.getVisitedHistory(paramAnonymousValueCallback);
          return;
        }
        super.getVisitedHistory(paramAnonymousValueCallback);
      }
      
      public void onCloseWindow(WebView paramAnonymousWebView)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onCloseWindow(paramAnonymousWebView);
          return;
        }
        super.onCloseWindow(paramAnonymousWebView);
      }
      
      public void onConsoleMessage(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onConsoleMessage(paramAnonymousString1, paramAnonymousInt, paramAnonymousString2);
          return;
        }
        super.onConsoleMessage(paramAnonymousString1, paramAnonymousInt, paramAnonymousString2);
      }
      
      public boolean onConsoleMessage(ConsoleMessage paramAnonymousConsoleMessage)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null) {
          return AdvancedWebView.this.mCustomWebChromeClient.onConsoleMessage(paramAnonymousConsoleMessage);
        }
        return super.onConsoleMessage(paramAnonymousConsoleMessage);
      }
      
      public boolean onCreateWindow(WebView paramAnonymousWebView, boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2, Message paramAnonymousMessage)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null) {
          return AdvancedWebView.this.mCustomWebChromeClient.onCreateWindow(paramAnonymousWebView, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousMessage);
        }
        return super.onCreateWindow(paramAnonymousWebView, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousMessage);
      }
      
      public void onExceededDatabaseQuota(String paramAnonymousString1, String paramAnonymousString2, long paramAnonymousLong1, long paramAnonymousLong2, long paramAnonymousLong3, WebStorage.QuotaUpdater paramAnonymousQuotaUpdater)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onExceededDatabaseQuota(paramAnonymousString1, paramAnonymousString2, paramAnonymousLong1, paramAnonymousLong2, paramAnonymousLong3, paramAnonymousQuotaUpdater);
          return;
        }
        super.onExceededDatabaseQuota(paramAnonymousString1, paramAnonymousString2, paramAnonymousLong1, paramAnonymousLong2, paramAnonymousLong3, paramAnonymousQuotaUpdater);
      }
      
      public void onGeolocationPermissionsHidePrompt()
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onGeolocationPermissionsHidePrompt();
          return;
        }
        super.onGeolocationPermissionsHidePrompt();
      }
      
      public void onGeolocationPermissionsShowPrompt(String paramAnonymousString, GeolocationPermissions.Callback paramAnonymousCallback)
      {
        if (AdvancedWebView.this.mGeolocationEnabled)
        {
          paramAnonymousCallback.invoke(paramAnonymousString, true, false);
          return;
        }
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onGeolocationPermissionsShowPrompt(paramAnonymousString, paramAnonymousCallback);
          return;
        }
        super.onGeolocationPermissionsShowPrompt(paramAnonymousString, paramAnonymousCallback);
      }
      
      public void onHideCustomView()
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onHideCustomView();
          return;
        }
        super.onHideCustomView();
      }
      
      public boolean onJsAlert(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null) {
          return AdvancedWebView.this.mCustomWebChromeClient.onJsAlert(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        return super.onJsAlert(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
      }
      
      public boolean onJsBeforeUnload(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null) {
          return AdvancedWebView.this.mCustomWebChromeClient.onJsBeforeUnload(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        return super.onJsBeforeUnload(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
      }
      
      public boolean onJsConfirm(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null) {
          return AdvancedWebView.this.mCustomWebChromeClient.onJsConfirm(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
        }
        return super.onJsConfirm(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
      }
      
      public boolean onJsPrompt(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, JsPromptResult paramAnonymousJsPromptResult)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null) {
          return AdvancedWebView.this.mCustomWebChromeClient.onJsPrompt(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousJsPromptResult);
        }
        return super.onJsPrompt(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousJsPromptResult);
      }
      
      public boolean onJsTimeout()
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null) {
          return AdvancedWebView.this.mCustomWebChromeClient.onJsTimeout();
        }
        return super.onJsTimeout();
      }
      
      @SuppressLint({"NewApi"})
      public void onPermissionRequest(PermissionRequest paramAnonymousPermissionRequest)
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          if (AdvancedWebView.this.mCustomWebChromeClient != null)
          {
            AdvancedWebView.this.mCustomWebChromeClient.onPermissionRequest(paramAnonymousPermissionRequest);
            return;
          }
          super.onPermissionRequest(paramAnonymousPermissionRequest);
        }
      }
      
      @SuppressLint({"NewApi"})
      public void onPermissionRequestCanceled(PermissionRequest paramAnonymousPermissionRequest)
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          if (AdvancedWebView.this.mCustomWebChromeClient != null)
          {
            AdvancedWebView.this.mCustomWebChromeClient.onPermissionRequestCanceled(paramAnonymousPermissionRequest);
            return;
          }
          super.onPermissionRequestCanceled(paramAnonymousPermissionRequest);
        }
      }
      
      public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onProgressChanged(paramAnonymousWebView, paramAnonymousInt);
          return;
        }
        super.onProgressChanged(paramAnonymousWebView, paramAnonymousInt);
      }
      
      public void onReachedMaxAppCacheSize(long paramAnonymousLong1, long paramAnonymousLong2, WebStorage.QuotaUpdater paramAnonymousQuotaUpdater)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onReachedMaxAppCacheSize(paramAnonymousLong1, paramAnonymousLong2, paramAnonymousQuotaUpdater);
          return;
        }
        super.onReachedMaxAppCacheSize(paramAnonymousLong1, paramAnonymousLong2, paramAnonymousQuotaUpdater);
      }
      
      public void onReceivedIcon(WebView paramAnonymousWebView, Bitmap paramAnonymousBitmap)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onReceivedIcon(paramAnonymousWebView, paramAnonymousBitmap);
          return;
        }
        super.onReceivedIcon(paramAnonymousWebView, paramAnonymousBitmap);
      }
      
      public void onReceivedTitle(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
          return;
        }
        super.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
      }
      
      public void onReceivedTouchIconUrl(WebView paramAnonymousWebView, String paramAnonymousString, boolean paramAnonymousBoolean)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onReceivedTouchIconUrl(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
          return;
        }
        super.onReceivedTouchIconUrl(paramAnonymousWebView, paramAnonymousString, paramAnonymousBoolean);
      }
      
      public void onRequestFocus(WebView paramAnonymousWebView)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onRequestFocus(paramAnonymousWebView);
          return;
        }
        super.onRequestFocus(paramAnonymousWebView);
      }
      
      @SuppressLint({"NewApi"})
      public void onShowCustomView(View paramAnonymousView, int paramAnonymousInt, WebChromeClient.CustomViewCallback paramAnonymousCustomViewCallback)
      {
        if (Build.VERSION.SDK_INT >= 14)
        {
          if (AdvancedWebView.this.mCustomWebChromeClient != null)
          {
            AdvancedWebView.this.mCustomWebChromeClient.onShowCustomView(paramAnonymousView, paramAnonymousInt, paramAnonymousCustomViewCallback);
            return;
          }
          super.onShowCustomView(paramAnonymousView, paramAnonymousInt, paramAnonymousCustomViewCallback);
        }
      }
      
      public void onShowCustomView(View paramAnonymousView, WebChromeClient.CustomViewCallback paramAnonymousCustomViewCallback)
      {
        if (AdvancedWebView.this.mCustomWebChromeClient != null)
        {
          AdvancedWebView.this.mCustomWebChromeClient.onShowCustomView(paramAnonymousView, paramAnonymousCustomViewCallback);
          return;
        }
        super.onShowCustomView(paramAnonymousView, paramAnonymousCustomViewCallback);
      }
      
      public boolean onShowFileChooser(WebView paramAnonymousWebView, ValueCallback<Uri[]> paramAnonymousValueCallback, WebChromeClient.FileChooserParams paramAnonymousFileChooserParams)
      {
        int i = Build.VERSION.SDK_INT;
        boolean bool = false;
        if (i >= 21)
        {
          if (paramAnonymousFileChooserParams.getMode() == 1) {
            bool = true;
          }
          AdvancedWebView.this.openFileInput(null, paramAnonymousValueCallback, bool);
          return true;
        }
        return false;
      }
      
      public void openFileChooser(ValueCallback<Uri> paramAnonymousValueCallback)
      {
        openFileChooser(paramAnonymousValueCallback, null);
      }
      
      public void openFileChooser(ValueCallback<Uri> paramAnonymousValueCallback, String paramAnonymousString)
      {
        openFileChooser(paramAnonymousValueCallback, paramAnonymousString, null);
      }
      
      public void openFileChooser(ValueCallback<Uri> paramAnonymousValueCallback, String paramAnonymousString1, String paramAnonymousString2)
      {
        AdvancedWebView.this.openFileInput(paramAnonymousValueCallback, null, false);
      }
    });
    setDownloadListener(new DownloadListener()
    {
      public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong)
      {
        String str = URLUtil.guessFileName(paramAnonymousString1, paramAnonymousString3, paramAnonymousString4);
        if (AdvancedWebView.this.mListener != null) {
          AdvancedWebView.this.mListener.onDownloadRequested(paramAnonymousString1, str, paramAnonymousString4, paramAnonymousLong, paramAnonymousString3, paramAnonymousString2);
        }
      }
    });
  }
  
  protected boolean isHostnameAllowed(String paramString)
  {
    if (this.mPermittedHostnames.size() == 0) {
      return true;
    }
    paramString = Uri.parse(paramString).getHost();
    Iterator localIterator = this.mPermittedHostnames.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!paramString.equals(str))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(".");
        localStringBuilder.append(str);
        if (!paramString.endsWith(localStringBuilder.toString())) {
          break;
        }
      }
      else
      {
        return true;
      }
    }
    return false;
  }
  
  public void loadHtml(String paramString)
  {
    loadHtml(paramString, null);
  }
  
  public void loadHtml(String paramString1, String paramString2)
  {
    loadHtml(paramString1, paramString2, null);
  }
  
  public void loadHtml(String paramString1, String paramString2, String paramString3)
  {
    loadHtml(paramString1, paramString2, paramString3, "utf-8");
  }
  
  public void loadHtml(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    loadDataWithBaseURL(paramString2, paramString1, "text/html", paramString4, paramString3);
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
    if (paramMap == null)
    {
      localObject = this.mHttpHeaders;
    }
    else
    {
      localObject = paramMap;
      if (this.mHttpHeaders.size() > 0)
      {
        paramMap.putAll(this.mHttpHeaders);
        localObject = paramMap;
      }
    }
    super.loadUrl(paramString, (Map)localObject);
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
    if (paramInt1 == this.mRequestCodeFilePicker) {
      if (paramInt2 == -1)
      {
        if (paramIntent == null) {
          break label211;
        }
        if (this.mFileUploadCallbackFirst != null)
        {
          this.mFileUploadCallbackFirst.onReceiveValue(paramIntent.getData());
          this.mFileUploadCallbackFirst = null;
          return;
        }
        if (this.mFileUploadCallbackSecond == null) {
          break label211;
        }
      }
    }
    for (;;)
    {
      try
      {
        localObject = paramIntent.getDataString();
        paramInt1 = 0;
        if (localObject != null)
        {
          localObject = new Uri[1];
          localObject[0] = Uri.parse(paramIntent.getDataString());
          paramIntent = (Intent)localObject;
        }
        else if ((Build.VERSION.SDK_INT >= 16) && (paramIntent.getClipData() != null))
        {
          paramInt2 = paramIntent.getClipData().getItemCount();
          localObject = new Uri[paramInt2];
          if (paramInt1 >= paramInt2) {}
        }
      }
      catch (Exception paramIntent)
      {
        Object localObject;
        continue;
      }
      try
      {
        localObject[paramInt1] = paramIntent.getClipData().getItemAt(paramInt1).getUri();
        paramInt1 += 1;
      }
      catch (Exception paramIntent) {}
    }
    paramIntent = (Intent)localObject;
    break label150;
    paramIntent = null;
    label150:
    this.mFileUploadCallbackSecond.onReceiveValue(paramIntent);
    this.mFileUploadCallbackSecond = null;
    return;
    if (this.mFileUploadCallbackFirst != null)
    {
      this.mFileUploadCallbackFirst.onReceiveValue(null);
      this.mFileUploadCallbackFirst = null;
      return;
    }
    if (this.mFileUploadCallbackSecond != null)
    {
      this.mFileUploadCallbackSecond.onReceiveValue(null);
      this.mFileUploadCallbackSecond = null;
    }
    label211:
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
    }
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          removeAllViews();
          destroy();
          return;
          localException1 = localException1;
        }
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  public void onPause()
  {
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
  
  @SuppressLint({"NewApi"})
  protected void openFileInput(ValueCallback<Uri> paramValueCallback, ValueCallback<Uri[]> paramValueCallback1, boolean paramBoolean)
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
    if ((paramBoolean) && (Build.VERSION.SDK_INT >= 18)) {
      paramValueCallback.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
    }
    paramValueCallback.setType(this.mUploadableFileTypes);
    if ((this.mFragment != null) && (this.mFragment.get() != null) && (Build.VERSION.SDK_INT >= 11))
    {
      ((Fragment)this.mFragment.get()).startActivityForResult(Intent.createChooser(paramValueCallback, getFileUploadPromptLabel()), this.mRequestCodeFilePicker);
      return;
    }
    if ((this.mActivity != null) && (this.mActivity.get() != null)) {
      ((Activity)this.mActivity.get()).startActivityForResult(Intent.createChooser(paramValueCallback, getFileUploadPromptLabel()), this.mRequestCodeFilePicker);
    }
  }
  
  public void removeHttpHeader(String paramString)
  {
    this.mHttpHeaders.remove(paramString);
  }
  
  public void removePermittedHostname(String paramString)
  {
    this.mPermittedHostnames.remove(paramString);
  }
  
  public void setCookiesEnabled(boolean paramBoolean)
  {
    CookieManager.getInstance().setAcceptCookie(paramBoolean);
  }
  
  public void setDesktopMode(boolean paramBoolean)
  {
    WebSettings localWebSettings = getSettings();
    String str;
    if (paramBoolean) {
      str = localWebSettings.getUserAgentString().replace("Mobile", "eliboM").replace("Android", "diordnA");
    } else {
      str = localWebSettings.getUserAgentString().replace("eliboM", "Mobile").replace("diordnA", "Android");
    }
    localWebSettings.setUserAgentString(str);
    localWebSettings.setUseWideViewPort(paramBoolean);
    localWebSettings.setLoadWithOverviewMode(paramBoolean);
    localWebSettings.setSupportZoom(paramBoolean);
    localWebSettings.setBuiltInZoomControls(paramBoolean);
  }
  
  @SuppressLint({"NewApi"})
  protected void setGeolocationDatabasePath()
  {
    Activity localActivity;
    if ((this.mFragment != null) && (this.mFragment.get() != null) && (Build.VERSION.SDK_INT >= 11) && (((Fragment)this.mFragment.get()).getActivity() != null))
    {
      localActivity = ((Fragment)this.mFragment.get()).getActivity();
    }
    else
    {
      if ((this.mActivity == null) || (this.mActivity.get() == null)) {
        return;
      }
      localActivity = (Activity)this.mActivity.get();
    }
    getSettings().setGeolocationDatabasePath(localActivity.getFilesDir().getPath());
    return;
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
  
  protected void setLastError()
  {
    this.mLastError = System.currentTimeMillis();
  }
  
  public void setListener(Activity paramActivity, Listener paramListener)
  {
    setListener(paramActivity, paramListener, 51426);
  }
  
  public void setListener(Activity paramActivity, Listener paramListener, int paramInt)
  {
    if (paramActivity != null) {
      this.mActivity = new WeakReference(paramActivity);
    } else {
      this.mActivity = null;
    }
    setListener(paramListener, paramInt);
  }
  
  public void setListener(Fragment paramFragment, Listener paramListener)
  {
    setListener(paramFragment, paramListener, 51426);
  }
  
  public void setListener(Fragment paramFragment, Listener paramListener, int paramInt)
  {
    if (paramFragment != null) {
      this.mFragment = new WeakReference(paramFragment);
    } else {
      this.mFragment = null;
    }
    setListener(paramListener, paramInt);
  }
  
  protected void setListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mRequestCodeFilePicker = paramInt;
  }
  
  @SuppressLint({"NewApi"})
  protected void setMixedContentAllowed(WebSettings paramWebSettings, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramWebSettings.setMixedContentMode(paramBoolean ^ true);
    }
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
    
    public static String getAlternative(Context paramContext)
    {
      if (mAlternativePackage != null) {
        return mAlternativePackage;
      }
      List localList = Arrays.asList(AdvancedWebView.ALTERNATIVE_BROWSERS);
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
    
    public static void openUrl(Activity paramActivity, String paramString, boolean paramBoolean)
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
    public abstract void onDownloadRequested(String paramString1, String paramString2, String paramString3, long paramLong, String paramString4, String paramString5);
    
    public abstract void onExternalPageRequest(String paramString);
    
    public abstract void onPageError(int paramInt, String paramString1, String paramString2);
    
    public abstract void onPageFinished(String paramString);
    
    public abstract void onPageStarted(String paramString, Bitmap paramBitmap);
  }
}
