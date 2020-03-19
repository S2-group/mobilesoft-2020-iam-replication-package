package com.babybus.bbmodule.plugin.webview.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.babybus.bbmodule.plugin.base.Plugin;
import com.babybus.bbmodule.plugin.base.umeng.BaseUmengPo;
import com.babybus.bbmodule.plugin.webview.PluginWebView;
import com.babybus.bbmodule.utils.BBNetUtil;
import com.babybus.bbmodule.utils.BBResources;
import com.babybus.bbmodule.utils.ReflectUtil;
import com.babybus.bbmodule.utils.common.BBApplication;
import com.babybus.common.wiget.BBThirdNoFrameworkActivity;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

@SuppressLint({"SetJavaScriptEnabled"})
public class WebActivity
  extends BBThirdNoFrameworkActivity
{
  private static int REQUESTCODE = 2;
  public static boolean isCreated;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (WebActivity.this.mProgressDialog.isShowing()) {
        WebActivity.this.mProgressDialog.dismiss();
      }
      Object localObject = paramAnonymousMessage.getData();
      String str = ((Bundle)localObject).getString("url");
      if (((Bundle)localObject).getString("contentType").contains("application/")) {}
      try
      {
        localObject = (Plugin)ReflectUtil.newInstance("com.babybus.bbmodule.plugin.download.PluginDownload");
        ((Plugin)localObject).setActivity(WebActivity.this);
        ReflectUtil.invokeMethod(localObject, "downloadApp", new Object[] { "logo", str });
        super.handleMessage(paramAnonymousMessage);
        return;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
    }
  };
  private ProgressDialog mProgressDialog;
  private WebView mWebView;
  private RelativeLayout noNetworkLayout;
  private RelativeLayout progress;
  private LinearLayout rootView;
  private BaseUmengPo umengPo;
  private String url;
  
  public WebActivity() {}
  
  private void addNoNetwork()
  {
    this.noNetworkLayout = new RelativeLayout(this);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
    Button localButton = new Button(this);
    localButton.setBackgroundResource(BBResources.getIdentifier(this, "babybus_web_no_network", "drawable"));
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams1.addRule(13);
    this.noNetworkLayout.addView(localButton, localLayoutParams1);
    this.rootView.addView(this.noNetworkLayout, localLayoutParams);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        WebActivity.this.startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), WebActivity.REQUESTCODE);
      }
    });
  }
  
  private void addWebPage()
  {
    this.mWebView = new WebView(this);
    Object localObject = new ViewGroup.LayoutParams(-1, -1);
    this.mWebView.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.rootView.addView(this.mWebView);
    localObject = this.mWebView.getSettings();
    ((WebSettings)localObject).setJavaScriptEnabled(true);
    ((WebSettings)localObject).setAppCacheEnabled(true);
    ((WebSettings)localObject).setUseWideViewPort(true);
    if (PluginWebView.cleanCache)
    {
      ((WebSettings)localObject).setCacheMode(2);
      this.mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
      this.mWebView.clearCache(true);
      this.mWebView.destroyDrawingCache();
    }
    this.mWebView.addJavascriptInterface(new JsOperation(this), "activity");
    this.mWebView.setWebChromeClient(new WebChromeClient()
    {
      public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt) {}
    });
    this.mWebView.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        WebActivity.this.rootView.removeView(WebActivity.this.progress);
        WebActivity.this.progress = null;
        super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
      }
      
      public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        WebActivity.this.progress = ((RelativeLayout)RelativeLayout.inflate(WebActivity.this, BBResources.getIdentifier(WebActivity.this, "bb_progress_flower", "layout"), null));
        FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
        WebActivity.this.rootView.addView(WebActivity.this.progress, localLayoutParams);
        super.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
      }
      
      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        WebActivity.this.rootView.removeView(WebActivity.this.progress);
        WebActivity.this.progress = null;
        Toast.makeText(WebActivity.this, "Oh~ The page is not work!", 0).show();
        super.onPageFinished(paramAnonymousWebView, WebActivity.this.url);
      }
      
      public void onReceivedSslError(WebView paramAnonymousWebView, final SslErrorHandler paramAnonymousSslErrorHandler, SslError paramAnonymousSslError)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("There are problems with the security certificate for this site.");
        localStringBuilder.append("\n\n");
        if (paramAnonymousSslError.hasError(3))
        {
          localStringBuilder.append(" - ");
          localStringBuilder.append("This certificate is not from a trusted authority.");
          localStringBuilder.append("\n");
        }
        if (paramAnonymousSslError.hasError(2))
        {
          localStringBuilder.append(" - ");
          localStringBuilder.append("The name of the site does not match the name on the certificate.");
          localStringBuilder.append("\n");
        }
        if (paramAnonymousSslError.hasError(1))
        {
          localStringBuilder.append(" - ");
          localStringBuilder.append("This certificate has expired.");
          localStringBuilder.append("\n");
        }
        if (paramAnonymousSslError.hasError(0))
        {
          localStringBuilder.append(" - ");
          localStringBuilder.append("This certificate is not valid yet.");
          localStringBuilder.append("\n");
        }
        BBApplication.getInstance().showContinueCancelDialog(paramAnonymousWebView.getContext(), 17301659, "Security warning", localStringBuilder.toString(), new DialogInterface.OnClickListener()new DialogInterface.OnClickListener
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
            paramAnonymousSslErrorHandler.proceed();
          }
        }, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
            paramAnonymousSslErrorHandler.cancel();
          }
        });
      }
      
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        paramAnonymousWebView.loadUrl(paramAnonymousString);
        return true;
      }
    });
  }
  
  private void configActivityFlags()
  {
    getWindow().addFlags(128);
  }
  
  public View getContentView(LinearLayout.LayoutParams paramLayoutParams)
  {
    this.rootView = new LinearLayout(this);
    this.rootView.setLayoutParams(paramLayoutParams);
    if (BBNetUtil.getInstance().isWiFiActive(this)) {
      addWebPage();
    }
    for (;;)
    {
      return this.rootView;
      addNoNetwork();
    }
  }
  
  public void handlerAfter()
  {
    configActivityFlags();
  }
  
  public void handlerBefore() {}
  
  public void handlerExit()
  {
    if (this.mWebView != null)
    {
      this.mWebView.pauseTimers();
      this.mWebView.stopLoading();
      this.mWebView.loadData("<a></a>", "text/html", "utf-8");
    }
  }
  
  public boolean isAppInstalled(String paramString)
  {
    List localList;
    int i;
    if ((paramString != null) && (!"".equals(paramString)))
    {
      localList = getPackageManager().getInstalledPackages(0);
      i = 0;
    }
    for (;;)
    {
      if (i >= localList.size()) {
        return false;
      }
      if (paramString.endsWith(((PackageInfo)localList.get(i)).packageName)) {
        return true;
      }
      i += 1;
    }
  }
  
  public void launchApp(String paramString)
  {
    launchApp(paramString, true);
  }
  
  public void launchApp(String paramString, boolean paramBoolean)
  {
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).setPackage(paramString);
    localObject = (ResolveInfo)getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator().next();
    if (localObject != null)
    {
      paramString = ((ResolveInfo)localObject).activityInfo.packageName;
      localObject = ((ResolveInfo)localObject).activityInfo.name;
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setComponent(new ComponentName(paramString, (String)localObject));
      localIntent.putExtra("launchFlag", true);
      startActivity(localIntent);
      if (paramBoolean) {
        finish();
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.umengPo = new BaseUmengPo();
    this.umengPo.setActivity(this);
    this.url = getIntent().getStringExtra("url");
    BBApplication.getInstance().addActivity(this);
  }
  
  protected void onPause()
  {
    if (this.mWebView != null) {
      this.mWebView.onPause();
    }
    this.umengPo.pausePluginActivity();
    super.onPause();
  }
  
  protected void onResume()
  {
    jumpEnable = PluginWebView.jumpEnable;
    if (this.mWebView != null) {
      this.mWebView.onResume();
    }
    if ((this.url != null) && (!"".equals(this.url))) {
      this.mWebView.loadUrl(this.url);
    }
    for (;;)
    {
      this.umengPo.resumePluginActivity();
      isCreated = true;
      super.onResume();
      return;
      this.mWebView.loadUrl("http://www.babybus.com");
    }
  }
  
  protected void onStop()
  {
    isCreated = false;
    super.onStop();
  }
  
  public void openMarketToDownload(String paramString)
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString.trim())));
      return;
    }
    catch (Exception paramString)
    {
      do
      {
        System.out.println("==================================================");
        System.out.println("[WebActivity] we use browser to open the market!");
        System.out.println("==================================================");
      } while (!StringUtils.isNotEmpty(this.url));
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.url)));
    }
  }
  
  class JsOperation
  {
    Activity mActivity;
    
    public JsOperation(Activity paramActivity)
    {
      this.mActivity = paramActivity;
    }
    
    @JavascriptInterface
    public void adPaidCallback(String paramString) {}
    
    @JavascriptInterface
    public void backToGame()
    {
      this.mActivity.finish();
    }
    
    @JavascriptInterface
    public boolean hasApp(String paramString)
    {
      return WebActivity.this.isAppInstalled(paramString);
    }
    
    @JavascriptInterface
    public void openApp(final String paramString1, String paramString2)
    {
      if (WebActivity.this.isAppInstalled(paramString2))
      {
        WebActivity.this.launchApp(paramString2, false);
        return;
      }
      if (BBNetUtil.getInstance().isWiFiActive(WebActivity.this))
      {
        WebActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            WebActivity.this.mProgressDialog = ProgressDialog.show(WebActivity.this, "Wait", "Opening network...", false, true);
          }
        });
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              String str = new URL(paramString1).openConnection().getContentType();
              Message localMessage = new Message();
              Bundle localBundle = new Bundle();
              localBundle.putString("url", paramString1);
              localBundle.putString("contentType", str);
              localMessage.setData(localBundle);
              WebActivity.this.mHandler.sendMessage(localMessage);
              return;
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
          }
        }).start();
        return;
      }
      Toast.makeText(WebActivity.this, "Please open wifi...", 0).show();
    }
  }
}
