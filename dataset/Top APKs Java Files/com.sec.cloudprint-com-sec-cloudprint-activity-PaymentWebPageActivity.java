package com.sec.cloudprint.activity;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.sec.cloudprint.common.Constants;
import com.sec.cloudprint.ui.JobHistory;
import com.sec.cloudprint.ui.JobHistoryTablet;
import com.sec.cloudprint.ui.MobilePrintBasicActivity;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentWebPageActivity
  extends MobilePrintBasicActivity
{
  public static final String EXTERNAL_SERVICE = "externalservice";
  public static final String EXTERNAL_SERVICE_COMPLETED = "externalservice_completed";
  public static final String EXTERNAL_SERVICE_NUMBER_OF_PHOTOS = "externalservice_number_of_photos";
  public static final String EXTERNAL_SERVICE_SAMSUNG_KEY = "externalservice_samsung_key";
  public static final String EXTERNAL_SERVICE_ZZIXX_UNIQUEID = "externalservice_zzixx_uniqueid";
  public static final String PAID_BROWSING_URL = "PAIDURL";
  private String externalServiceSamsungKey = null;
  private int externalServiceWebPage = 0;
  private String externalServiceZzixxUniqueid = null;
  private WebView mWebView;
  private FrameLayout mWebViewContainer = null;
  private final String urlExternalServiceForCloseWebView = "externalserviceclose";
  private final String urlForCloseWebView = "scppaymentclose";
  private final String urlPostOfficeForCloseWebView = "scppostofficeclose";
  
  public PaymentWebPageActivity() {}
  
  private void closeAllWindow()
  {
    for (;;)
    {
      if (this.mWebViewContainer.getChildCount() <= 1)
      {
        finish();
        return;
      }
      this.mWebViewContainer.removeViewAt(this.mWebViewContainer.getChildCount() - 1);
    }
  }
  
  private void closeWindow()
  {
    if (this.mWebViewContainer.getChildCount() > 1)
    {
      this.mWebViewContainer.removeViewAt(this.mWebViewContainer.getChildCount() - 1);
      return;
    }
    if (this.externalServiceWebPage == 1)
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("externalservice_zzixx_uniqueid", this.externalServiceZzixxUniqueid);
      localIntent.putExtra("externalservice_samsung_key", this.externalServiceSamsungKey);
      setResult(-1, localIntent);
    }
    finish();
  }
  
  private void openJobHistory()
  {
    if (!Constants.IS_TABLET)
    {
      localIntent = new Intent(this, JobHistory.class);
      localIntent.putExtra("SET_CURRENT_TAB", 1);
      startActivity(localIntent);
      return;
    }
    Intent localIntent = new Intent(this, JobHistoryTablet.class);
    localIntent.putExtra("SET_CURRENT_TAB", 1);
    startActivity(localIntent);
  }
  
  public void onBackPressed()
  {
    if (this.mWebViewContainer.getChildCount() > 0)
    {
      int i = this.mWebViewContainer.getChildCount();
      WebView localWebView = (WebView)this.mWebViewContainer.getChildAt(i - 1);
      if (localWebView.canGoBack())
      {
        localWebView.goBack();
        return;
      }
      closeWindow();
      return;
    }
    closeWindow();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903201);
    this.mWebViewContainer = ((FrameLayout)findViewById(2131559133));
    Object localObject = getIntent();
    if (localObject != null)
    {
      paramBundle = ((Intent)localObject).getStringExtra("PAIDURL");
      this.externalServiceWebPage = ((Intent)localObject).getIntExtra("externalservice", 0);
      if ((this.externalServiceWebPage == 1) || (this.externalServiceWebPage == 2))
      {
        this.externalServiceZzixxUniqueid = ((Intent)localObject).getStringExtra("externalservice_zzixx_uniqueid");
        this.externalServiceSamsungKey = ((Intent)localObject).getStringExtra("externalservice_samsung_key");
        localObject = getActionBar();
        if (localObject != null) {
          ((ActionBar)localObject).hide();
        }
      }
      this.mWebView = ((WebView)findViewById(2131559134));
      this.mWebView.getSettings().setJavaScriptEnabled(true);
      this.mWebView.getSettings().setSupportMultipleWindows(true);
      this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
      this.mWebView.setWebViewClient(new CustomWebViewClient(null));
      this.mWebView.getSettings().setSupportZoom(true);
      this.mWebView.getSettings().setBuiltInZoomControls(true);
      this.mWebView.getSettings().setDisplayZoomControls(false);
      this.mWebView.setWebChromeClient(new CustomWebChromeClient(null));
      this.mWebView.addJavascriptInterface(new WebAppInterface(this), "SCPMobile");
      this.mWebView.setTag(new webViewData(null));
      if (Build.VERSION.SDK_INT >= 21) {
        this.mWebView.getSettings().setMixedContentMode(0);
      }
      this.mWebView.loadUrl(paramBundle);
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    boolean bool = super.onCreateOptionsMenu(paramMenu);
    getMenuInflater().inflate(2131623938, paramMenu);
    return bool;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.mWebView != null)
    {
      this.mWebView.stopLoading();
      this.mWebView.destroy();
      this.mWebView = null;
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((this.externalServiceWebPage == 1) && (this.mWebViewContainer.getChildCount() > 0))
    {
      int i = this.mWebViewContainer.getChildCount();
      WebView localWebView = (WebView)this.mWebViewContainer.getChildAt(i - 1);
      if (paramInt == 4)
      {
        localWebView.loadUrl("javascript:app_back()");
        return true;
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      closeWindow();
    }
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  private class CustomWebChromeClient
    extends WebChromeClient
  {
    private CustomWebChromeClient() {}
    
    public void onCloseWindow(WebView paramWebView)
    {
      super.onCloseWindow(paramWebView);
      PaymentWebPageActivity.this.closeWindow();
    }
    
    public boolean onCreateWindow(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
    {
      paramWebView = new WebView(PaymentWebPageActivity.this);
      paramWebView.getSettings().setJavaScriptEnabled(true);
      paramWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
      paramWebView.getSettings().setSupportMultipleWindows(true);
      paramWebView.getSettings().setSupportZoom(true);
      paramWebView.getSettings().setBuiltInZoomControls(true);
      paramWebView.getSettings().setDisplayZoomControls(false);
      paramWebView.setWebViewClient(new PaymentWebPageActivity.CustomWebViewClient(PaymentWebPageActivity.this, null));
      paramWebView.setWebChromeClient(new CustomWebChromeClient(PaymentWebPageActivity.this));
      paramWebView.addJavascriptInterface(new PaymentWebPageActivity.WebAppInterface(PaymentWebPageActivity.this, PaymentWebPageActivity.this), "SCPMobile");
      paramWebView.setTag(new PaymentWebPageActivity.webViewData(PaymentWebPageActivity.this, null));
      if (Build.VERSION.SDK_INT >= 21) {
        paramWebView.getSettings().setMixedContentMode(0);
      }
      PaymentWebPageActivity.this.mWebViewContainer.addView(paramWebView);
      ((WebView.WebViewTransport)paramMessage.obj).setWebView(paramWebView);
      paramMessage.sendToTarget();
      return true;
    }
  }
  
  private class CustomWebViewClient
    extends WebViewClient
  {
    private CustomWebViewClient() {}
    
    private boolean handleUrl(WebView paramWebView, String paramString)
    {
      if ((paramString != null) && ((paramString.startsWith("http")) || (paramString.startsWith("https")) || (paramString.startsWith("about")))) {
        return false;
      }
      boolean bool;
      if ((paramString != null) && (paramString.startsWith("intent://"))) {
        try
        {
          paramWebView = (PaymentWebPageActivity.webViewData)paramWebView.getTag();
          paramString = Intent.parseUri(paramString, 1);
          if (isInstalledApp(PaymentWebPageActivity.this, paramString.getPackage())) {
            PaymentWebPageActivity.this.startActivity(paramString);
          }
          while (paramWebView.getLastLoadingUrl() == null)
          {
            PaymentWebPageActivity.this.closeWindow();
            break;
            Intent localIntent = new Intent("android.intent.action.VIEW");
            localIntent.setData(Uri.parse("market://details?id=" + paramString.getPackage()));
            PaymentWebPageActivity.this.startActivity(localIntent);
            continue;
            try
            {
              PaymentWebPageActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
              if (((PaymentWebPageActivity.webViewData)paramWebView.getTag()).getLastLoadingUrl() == null) {
                PaymentWebPageActivity.this.closeWindow();
              }
              bool = true;
            }
            catch (ActivityNotFoundException paramWebView)
            {
              bool = false;
            }
          }
        }
        catch (URISyntaxException paramWebView)
        {
          bool = false;
        }
        catch (ActivityNotFoundException paramWebView)
        {
          bool = false;
        }
      } else {
        bool = true;
      }
      return bool;
    }
    
    private boolean isInstalledApp(Context paramContext, String paramString)
    {
      if ((paramContext == null) || (paramString == null)) {}
      do
      {
        while (!paramContext.hasNext())
        {
          do
          {
            return false;
          } while (paramContext.getPackageManager() == null);
          paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
        }
      } while (!((ApplicationInfo)paramContext.next()).packageName.equals(paramString));
      return true;
    }
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      if ((paramString != null) && ((paramString.startsWith("http")) || (paramString.startsWith("https"))))
      {
        PaymentWebPageActivity.webViewData localWebViewData = (PaymentWebPageActivity.webViewData)paramWebView.getTag();
        localWebViewData.setLastLoadingUrl(paramString);
        paramWebView.setTag(localWebViewData);
      }
      if ((paramString != null) && ((paramString.startsWith("http")) || (paramString.startsWith("https"))) && (paramString.contains("scppaymentclose"))) {
        PaymentWebPageActivity.this.closeAllWindow();
      }
      if ((paramString != null) && ((paramString.startsWith("http")) || (paramString.startsWith("https"))) && (paramString.contains("externalserviceclose"))) {
        PaymentWebPageActivity.this.closeAllWindow();
      }
      if ((paramString != null) && ((paramString.startsWith("http")) || (paramString.startsWith("https"))) && (paramString.contains("scppostofficeclose")))
      {
        PaymentWebPageActivity.this.openJobHistory();
        PaymentWebPageActivity.this.closeAllWindow();
      }
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      boolean bool = super.shouldOverrideUrlLoading(paramWebView, paramString);
      if (handleUrl(paramWebView, paramString)) {
        bool = true;
      }
      return bool;
    }
  }
  
  private class WebAppInterface
  {
    public WebAppInterface(Context paramContext) {}
    
    @JavascriptInterface
    public void SendPayResult(String paramString)
    {
      try
      {
        paramString = new JSONObject(paramString);
        if (!Boolean.valueOf(paramString.getBoolean("Success")).booleanValue())
        {
          paramString.getInt("errorCode");
          paramString.getString("errorReason");
        }
        paramString.getString("paymentMethod");
        return;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
        return;
      }
      finally
      {
        PaymentWebPageActivity.this.finish();
      }
    }
    
    @JavascriptInterface
    public void requestClose()
    {
      PaymentWebPageActivity.this.finish();
    }
    
    @JavascriptInterface
    public void requestClose(boolean paramBoolean)
    {
      Intent localIntent;
      if (!paramBoolean)
      {
        localIntent = new Intent();
        localIntent.putExtra("externalservice_zzixx_uniqueid", PaymentWebPageActivity.this.externalServiceZzixxUniqueid);
        localIntent.putExtra("externalservice_samsung_key", PaymentWebPageActivity.this.externalServiceSamsungKey);
        localIntent.putExtra("externalservice_completed", false);
        PaymentWebPageActivity.this.setResult(-1, localIntent);
      }
      for (;;)
      {
        PaymentWebPageActivity.this.finish();
        return;
        localIntent = new Intent();
        localIntent.putExtra("externalservice_zzixx_uniqueid", "");
        localIntent.putExtra("externalservice_samsung_key", "");
        localIntent.putExtra("externalservice_completed", true);
        PaymentWebPageActivity.this.setResult(-1, localIntent);
      }
    }
    
    @JavascriptInterface
    public void requestClose(boolean paramBoolean, int paramInt)
    {
      Intent localIntent;
      if (!paramBoolean)
      {
        localIntent = new Intent();
        localIntent.putExtra("externalservice_zzixx_uniqueid", PaymentWebPageActivity.this.externalServiceZzixxUniqueid);
        localIntent.putExtra("externalservice_samsung_key", PaymentWebPageActivity.this.externalServiceSamsungKey);
        localIntent.putExtra("externalservice_number_of_photos", paramInt);
        PaymentWebPageActivity.this.setResult(-1, localIntent);
      }
      for (;;)
      {
        PaymentWebPageActivity.this.finish();
        return;
        localIntent = new Intent();
        localIntent.putExtra("externalservice_zzixx_uniqueid", "");
        localIntent.putExtra("externalservice_samsung_key", "");
        localIntent.putExtra("externalservice_number_of_photos", 0);
        localIntent.putExtra("externalservice_completed", true);
        PaymentWebPageActivity.this.setResult(-1, localIntent);
      }
    }
  }
  
  private class webViewData
  {
    private String lastLoadingUrl = null;
    
    private webViewData() {}
    
    public String getLastLoadingUrl()
    {
      return this.lastLoadingUrl;
    }
    
    public void setLastLoadingUrl(String paramString)
    {
      this.lastLoadingUrl = paramString;
    }
  }
}
