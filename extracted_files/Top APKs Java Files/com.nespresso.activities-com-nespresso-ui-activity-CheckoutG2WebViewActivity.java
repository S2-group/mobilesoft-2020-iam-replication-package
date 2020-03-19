package com.nespresso.ui.activity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.nespresso.cart.Cart;
import com.nespresso.cart.CartEventBus;
import com.nespresso.cart.CartEventBus.CartConstraintViolationEvent;
import com.nespresso.cart.CartEventBus.CartUploadErrorEvent;
import com.nespresso.cart.CartEventBus.CartUploadedEvent;
import com.nespresso.cart.CartEventBus.LatestCartRetrieveErrorEvent;
import com.nespresso.cart.CartEventBus.LatestCartRetrievedEvent;
import com.nespresso.cart.CheckoutConstraintViolationsHandler;
import com.nespresso.cart.model.CartConstraintViolation;
import com.nespresso.data.user.LoginEventBus;
import com.nespresso.data.user.LoginEventBus.LoginRequiredEvent;
import com.nespresso.data.user.LoginEventBus.NcsMobileErrorEvent;
import com.nespresso.data.user.LoginEventBus.NetworkErrorEvent;
import com.nespresso.data.user.LoginEventBus.ValidTokenEvent;
import com.nespresso.data.user.model.User;
import com.nespresso.global.ApplicationSharedPreferences;
import com.nespresso.global.CommonSharedPreferences;
import com.nespresso.global.NespressoActivity;
import com.nespresso.global.NespressoApplication;
import com.nespresso.global.enumeration.EnumCMSContentType;
import com.nespresso.global.enumeration.EnumCountries;
import com.nespresso.global.enumeration.EnumDesiredFlow;
import com.nespresso.global.prefs.AppPrefs;
import com.nespresso.global.prefs.WSAppPrefs;
import com.nespresso.global.util.CookieUtils;
import com.nespresso.global.util.HTTPUtils;
import com.nespresso.global.util.LocalizationUtils;
import com.nespresso.global.util.URLTagReplacer;
import com.nespresso.provider.CMSContentProvider;
import com.nespresso.ui.fragment.CheckoutConfirmationFragment;
import com.nespresso.ui.util.DialogUtils;
import com.nespresso.ui.util.WebViewUtils;
import com.nespresso.ui.webview.NespressoWebViewClient;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CheckoutG2WebViewActivity
  extends NespressoActivity
{
  private static final int ACTIVITY_REQUEST_CODE_INACTIVITY_LOGIN = 1;
  private static final int ACTIVITY_REQUEST_CODE_KOREA_PAYMENT_KFTC = 2;
  private static final int ACTIVITY_REQUEST_CODE_LOGIN = 0;
  private static final int ACTIVITY_REQUEST_PROMO_CAMPAIGN = 3;
  private static final String EXTRA_CHECKISTER = "EXTRA_CHECKISTER";
  private static final String EXTRA_CHECKOUT_STARTED = "EXTRA_CHECKOUT_STARTED";
  private static final String EXTRA_CONNECT = "EXTRA_CONNECT";
  private static final String LOG_TAG = CheckoutG2WebViewActivity.class.getSimpleName();
  private static final String MISSING_AMOUNT_QUERY_PARAM = "OrderMissingAmount";
  public static final int PROGRESS_DONE = 3;
  public static final int PROGRESS_STAT_IN = 2;
  public static final int PROGRESS_STAT_NOT_START = 1;
  public static final int SECONDS_TO_MILLIS = 1000;
  private boolean bankpay_auth_flag = false;
  private String bankpay_code = "";
  private String bankpay_value = "";
  private final Handler handler = new Handler();
  private boolean mActivityPaused = false;
  private boolean mBackFromNativeWelcomeOffer = false;
  private String mCheckoutConfirmationURL;
  private Date mCheckoutEndTime;
  private String mCheckoutEndURL;
  private Date mCheckoutStartTime;
  private boolean mCheckoutStarted = false;
  private WebView mCheckoutWebView;
  private ImageButton mCloseButton;
  private Context mContext;
  private Button mContinueButton;
  private String mCurrentCountry;
  private FrameLayout mFlContainer;
  private CountDownTimer mInactivityTimer;
  private boolean mIsCheckisterFlow;
  private boolean mIsConnectFlow;
  private String mKCPIspReturnUrlPath = "";
  private boolean mNativeConfirmationViewIsShown = false;
  private ImageButton mPreviousButton;
  private ProgressBar mProgressBar;
  private RelativeLayout mRlContainer;
  private long mTotalLoadingTime;
  private WebView mWaitingWebView;
  public int m_nStat = 1;
  
  public CheckoutG2WebViewActivity() {}
  
  private void KCPBackFromPayment()
  {
    try
    {
      Object localObject = (NespressoApplication)getApplication();
      if (((NespressoApplication)localObject).mKCPUriResult != null)
      {
        this.m_nStat = 3;
        localObject = ((NespressoApplication)localObject).mKCPUriResult.getQueryParameter("approval_key");
        if (((String)localObject).substring(((String)localObject).length() - 4).equals("0000"))
        {
          localObject = ((String)localObject).substring(0, ((String)localObject).length() - 4);
          this.mCheckoutWebView.loadUrl(this.mKCPIspReturnUrlPath + "/app.do?ActionResult=app&approval_key=" + (String)localObject);
        }
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private boolean KCPHandleUrlIntent(String paramString)
  {
    if (paramString.startsWith("intent"))
    {
      try
      {
        paramString = Intent.parseUri(paramString, 1);
        if (getPackageManager().resolveActivity(paramString, 0) == null)
        {
          String str = paramString.getPackage();
          if (str != null)
          {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pname:" + str)));
            return true;
          }
        }
      }
      catch (URISyntaxException paramString)
      {
        return false;
      }
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString.getDataString()));
      try
      {
        startActivity(paramString);
        return true;
      }
      catch (ActivityNotFoundException paramString)
      {
        return false;
      }
    }
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return true;
    }
    catch (Exception paramString) {}
    return true;
  }
  
  private void KCPcheckFromACNT()
  {
    try
    {
      this.mCheckoutWebView.loadUrl("javascript:KCP_App_script('" + this.bankpay_code + "','" + this.bankpay_value + "')");
      return;
    }
    catch (Exception localException) {}
  }
  
  public static Intent getIntent(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramContext = new Intent(paramContext, CheckoutG2WebViewActivity.class);
    paramContext.putExtra("EXTRA_CHECKISTER", paramBoolean1);
    paramContext.putExtra("EXTRA_CONNECT", paramBoolean2);
    return paramContext;
  }
  
  private boolean getPackageDownloadInstallState(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(8192).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.contains(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private void handleCartConstraintViolations(List<CartConstraintViolation> paramList)
  {
    if (!paramList.isEmpty()) {
      DialogUtils.showCartViolationsDialog(getApplicationContext(), getSupportFragmentManager());
    }
  }
  
  private void handleKoreaPayment()
  {
    if ((this.mCurrentCountry != null) && (this.mCurrentCountry.equals(EnumCountries.SOUTH_KOREA.getValue())))
    {
      if (this.bankpay_auth_flag)
      {
        this.bankpay_auth_flag = false;
        KCPcheckFromACNT();
      }
    }
    else {
      return;
    }
    NespressoApplication localNespressoApplication = (NespressoApplication)getApplication();
    if (this.m_nStat == 2)
    {
      KCPBackFromPayment();
      localNespressoApplication.mKCPBType = false;
    }
    localNespressoApplication.mKCPUriResult = null;
  }
  
  private void hideProgressBar()
  {
    this.mProgressBar.setVisibility(8);
  }
  
  private void initKoreaPaymentJavascriptInterfaces()
  {
    if ((this.mCurrentCountry != null) && (this.mCurrentCountry.equals(EnumCountries.SOUTH_KOREA.getValue())))
    {
      this.mCheckoutWebView.getSettings().setDomStorageEnabled(true);
      this.mCheckoutWebView.addJavascriptInterface(new KCPPayBridge(null), "KCPPayApp");
      this.mCheckoutWebView.addJavascriptInterface(new KCPPayPinInfoBridge(null), "KCPPayPinInfo");
      this.mCheckoutWebView.addJavascriptInterface(new KCPPayPinReturn(null), "KCPPayPinRet");
      this.mCheckoutWebView.addJavascriptInterface(new KCPPayBridgeACNT(null), "KCPPayApp");
      this.mCheckoutWebView.setWebChromeClient(new WebChromeClient());
    }
  }
  
  private void initializeViews()
  {
    this.mProgressBar = ((ProgressBar)findViewById(2131558630));
    this.mCheckoutWebView = ((WebView)findViewById(2131558628));
    this.mWaitingWebView = ((WebView)findViewById(2131558629));
    this.mPreviousButton = ((ImageButton)findViewById(2131558552));
    this.mCloseButton = ((ImageButton)findViewById(2131558554));
    this.mContinueButton = ((Button)findViewById(2131558553));
    this.mRlContainer = ((RelativeLayout)findViewById(2131558627));
    this.mFlContainer = ((FrameLayout)findViewById(2131558532));
    this.mFlContainer.setVisibility(8);
    this.mCloseButton.setVisibility(0);
    this.mCloseButton.setImageResource(2130837732);
    this.mPreviousButton.setVisibility(8);
    this.mContinueButton.setText(LocalizationUtils.getLocalizedString("cta_continue"));
    this.mContinueButton.setVisibility(8);
  }
  
  private void loadCheckoutWebView()
  {
    String str = URLTagReplacer.replaceTags(WSAppPrefs.getInstance().getAsString("WS_CHECKOUT_START_URL"), getApplicationContext());
    this.mCheckoutWebView.postUrl(str, HTTPUtils.getEncodedAuthenticationParam(User.getInstance().getAuthToken()));
  }
  
  private void quitWebView()
  {
    finish();
  }
  
  private void restartInactivityTimer()
  {
    if (this.mInactivityTimer != null) {
      this.mInactivityTimer.cancel();
    }
    long l = AppPrefs.getInstance().getAsInt("CHECKOUT_EXPRESS_TIMEOUT_IN_SECOND");
    if ((AppPrefs.getInstance().getAsBoolean("CHECKOUT_EXPRESS_ENABLED", false)) && (l > 0L))
    {
      this.mInactivityTimer = new CountDownTimer(l * 1000L, 1000L)
      {
        public void onFinish()
        {
          CheckoutG2WebViewActivity.this.showExpressLogin();
          CheckoutG2WebViewActivity.this.mInactivityTimer.cancel();
        }
        
        public void onTick(long paramAnonymousLong) {}
      };
      this.mInactivityTimer.start();
    }
  }
  
  private void retrieveLastUploadedCart()
  {
    Cart.getInstance().retrieveLastUploadedCart();
  }
  
  private boolean shouldShowWelcomeOffer()
  {
    return (!ApplicationSharedPreferences.getInstance(getApplicationContext()).isWelcomeOfferWebViewPresented()) && (!ApplicationSharedPreferences.getInstance(getApplicationContext()).isWelcomeOfferNativePresented()) && (CMSContentProvider.hasContent(getApplicationContext(), EnumCMSContentType.WELCOMEOFFER.getLibelle()));
  }
  
  private void showCheckoutConfirmationFragment(String paramString)
  {
    boolean bool = true;
    if (this.mInactivityTimer != null) {
      this.mInactivityTimer.cancel();
    }
    this.mNativeConfirmationViewIsShown = true;
    this.mCloseButton.setVisibility(8);
    this.mContinueButton.setVisibility(0);
    CheckoutConfirmationFragment localCheckoutConfirmationFragment = new CheckoutConfirmationFragment();
    if ((AppPrefs.getInstance().getAsBoolean("RANKING_ENABLED", false)) && (this.mTotalLoadingTime < AppPrefs.getInstance().getAsInt("RANKING_CHECKOUT_TIME")) && (paramString != null) && (!paramString.isEmpty())) {}
    for (;;)
    {
      Bundle localBundle = new Bundle();
      localBundle.putBoolean("SHOW_APP_RATING_BANNER", bool);
      localBundle.putString("ORDER_ID", paramString);
      localCheckoutConfirmationFragment.setArguments(localBundle);
      getSupportFragmentManager().beginTransaction().add(2131558532, localCheckoutConfirmationFragment).commit();
      this.mRlContainer.setVisibility(8);
      this.mFlContainer.setVisibility(0);
      CartEventBus.getInstance().unregister(this);
      return;
      bool = false;
    }
  }
  
  private void showCheckoutWebView()
  {
    Animation localAnimation1 = AnimationUtils.loadAnimation(getApplicationContext(), 2130968592);
    Animation localAnimation2 = AnimationUtils.loadAnimation(getApplicationContext(), 2130968594);
    this.mCheckoutWebView.setVisibility(0);
    this.mCheckoutWebView.startAnimation(localAnimation1);
    this.mWaitingWebView.startAnimation(localAnimation2);
    localAnimation2.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        CheckoutG2WebViewActivity.this.mWaitingWebView.setVisibility(8);
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
  }
  
  private void showExpressLogin()
  {
    startActivityForResult(new Intent(this, ExpressCheckoutActivity.class), 1);
  }
  
  private void showProgressBar()
  {
    if (!this.mActivityPaused) {
      this.mProgressBar.setVisibility(0);
    }
  }
  
  private void showWaitingCheckoutWebView()
  {
    this.mCheckoutWebView.setVisibility(8);
    this.mWaitingWebView.setVisibility(0);
    String str1 = WebViewUtils.buildWaitingPage(this.mContext);
    this.mWaitingWebView.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        return true;
      }
    });
    this.mWaitingWebView.getSettings().setAllowFileAccess(true);
    this.mWaitingWebView.getSettings().setJavaScriptEnabled(true);
    String str2 = getApplicationContext().getFilesDir().toURI().toASCIIString() + "checkout_page/";
    this.mWaitingWebView.loadDataWithBaseURL(str2, str1, "text/html", "utf-8", "");
  }
  
  private void showWelcomeOffer()
  {
    hideProgressBar();
    startActivityForResult(LandingPageActivity.getIntent(getApplicationContext(), this.mIsCheckisterFlow, this.mIsConnectFlow), 3);
  }
  
  private void startCheckoutFlow()
  {
    this.mCheckoutStartTime = new Date();
    restartInactivityTimer();
    if (!this.mCheckoutStarted)
    {
      this.mCheckoutStarted = true;
      showProgressBar();
      showWaitingCheckoutWebView();
      if (!this.mIsCheckisterFlow) {
        break label62;
      }
      retrieveLastUploadedCart();
      if (shouldShowWelcomeOffer()) {
        showWelcomeOffer();
      }
    }
    for (;;)
    {
      handleKoreaPayment();
      return;
      label62:
      uploadCartAndGoToCheckout();
    }
  }
  
  private void uploadCartAndGoToCheckout()
  {
    this.mTotalLoadingTime = 0L;
    Cart.getInstance().upload();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    }
    do
    {
      return;
      if (paramInt2 != -1)
      {
        quitWebView();
        return;
      }
      this.mCheckoutStarted = false;
      return;
      this.mBackFromNativeWelcomeOffer = true;
      this.mIsCheckisterFlow = false;
      this.mCheckoutStarted = false;
      return;
    } while ((this.mCurrentCountry == null) || (!this.mCurrentCountry.equals(EnumCountries.SOUTH_KOREA.getValue())) || (paramIntent == null));
    this.bankpay_code = paramIntent.getExtras().getString("bankpay_code");
    this.bankpay_value = paramIntent.getExtras().getString("bankpay_value");
  }
  
  public void onBackPressed()
  {
    if (this.mCheckoutWebView.canGoBack())
    {
      this.mCheckoutWebView.goBack();
      return;
    }
    super.onBackPressed();
  }
  
  @SuppressLint({"JavascriptInterface"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mContext = this;
    setContentView(2130903085);
    initializeViews();
    setButtonsClickListeners();
    this.mCheckoutEndURL = WSAppPrefs.getInstance().getAsString("WS_CHECKOUT_END_URL");
    this.mCheckoutConfirmationURL = WSAppPrefs.getInstance().getAsString("WS_CHECKOUT_ORDER_CONFIRM_URL");
    this.mCurrentCountry = CommonSharedPreferences.getInstance(getApplicationContext()).getCountry();
    this.mIsCheckisterFlow = getIntent().getBooleanExtra("EXTRA_CHECKISTER", false);
    this.mIsConnectFlow = getIntent().getBooleanExtra("EXTRA_CONNECT", false);
    this.mCheckoutWebView.getSettings().setJavaScriptEnabled(true);
    this.mCheckoutWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    this.mCheckoutWebView.getSettings().setBuiltInZoomControls(false);
    this.mCheckoutWebView.getSettings().setSupportZoom(false);
    this.mCheckoutWebView.setWebViewClient(new MyWebViewClient(getApplicationContext()));
    this.mCheckoutWebView.setWebChromeClient(new MyWebChromeClient(this));
    initKoreaPaymentJavascriptInterfaces();
    if (paramBundle == null)
    {
      this.mCheckoutStarted = false;
      CookieUtils.clearCookies(getApplicationContext());
      return;
    }
    this.mCheckoutStarted = paramBundle.getBoolean("EXTRA_CHECKOUT_STARTED", false);
    this.mCheckoutWebView.restoreState(paramBundle);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.mInactivityTimer != null) {
      this.mInactivityTimer.cancel();
    }
  }
  
  public void onEventMainThread(CartEventBus.CartConstraintViolationEvent paramCartConstraintViolationEvent)
  {
    handleCartConstraintViolations(paramCartConstraintViolationEvent.violations);
  }
  
  public void onEventMainThread(CartEventBus.CartUploadErrorEvent paramCartUploadErrorEvent)
  {
    CartEventBus.getEventBus().removeStickyEvent(paramCartUploadErrorEvent);
    Toast.makeText(this, LocalizationUtils.getLocalizedString("error_service"), 1).show();
    quitWebView();
  }
  
  public void onEventMainThread(CartEventBus.CartUploadedEvent paramCartUploadedEvent)
  {
    CartEventBus.getEventBus().removeStickyEvent(paramCartUploadedEvent);
    hideProgressBar();
    loadCheckoutWebView();
  }
  
  public void onEventMainThread(CartEventBus.LatestCartRetrieveErrorEvent paramLatestCartRetrieveErrorEvent)
  {
    CartEventBus.getEventBus().removeStickyEvent(paramLatestCartRetrieveErrorEvent);
    quitWebView();
  }
  
  public void onEventMainThread(CartEventBus.LatestCartRetrievedEvent paramLatestCartRetrievedEvent)
  {
    CartEventBus.getEventBus().removeStickyEvent(paramLatestCartRetrievedEvent);
    hideProgressBar();
    if ((!this.mBackFromNativeWelcomeOffer) && (!shouldShowWelcomeOffer()))
    {
      this.mIsCheckisterFlow = false;
      loadCheckoutWebView();
    }
  }
  
  public void onEventMainThread(LoginEventBus.LoginRequiredEvent paramLoginRequiredEvent)
  {
    startActivityForResult(LoginActivity.getIntent(this.mContext, EnumDesiredFlow.CHECKISTER), 0);
  }
  
  public void onEventMainThread(LoginEventBus.NcsMobileErrorEvent paramNcsMobileErrorEvent)
  {
    startActivityForResult(LoginActivity.getIntent(this.mContext, EnumDesiredFlow.CHECKISTER), 0);
  }
  
  public void onEventMainThread(LoginEventBus.NetworkErrorEvent paramNetworkErrorEvent)
  {
    startActivityForResult(LoginActivity.getIntent(this.mContext, EnumDesiredFlow.CHECKISTER), 0);
  }
  
  public void onEventMainThread(LoginEventBus.ValidTokenEvent paramValidTokenEvent)
  {
    startCheckoutFlow();
  }
  
  protected void onPause()
  {
    super.onPause();
    this.mActivityPaused = true;
    CartEventBus.getInstance().unregister(this);
    LoginEventBus.getInstance().unregister(this);
  }
  
  protected void onResume()
  {
    super.onResume();
    this.mActivityPaused = false;
    CartEventBus.getInstance().registerSticky(this);
    LoginEventBus.getInstance().register(this);
    User.getInstance().ensureLoggedIn();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBoolean("EXTRA_CHECKOUT_STARTED", this.mCheckoutStarted);
    this.mCheckoutWebView.saveState(paramBundle);
  }
  
  protected void onStop()
  {
    super.onStop();
    if (this.mInactivityTimer != null) {
      this.mInactivityTimer.cancel();
    }
  }
  
  public void onUserInteraction()
  {
    super.onUserInteraction();
    restartInactivityTimer();
  }
  
  public void setButtonsClickListeners()
  {
    this.mCloseButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CheckoutG2WebViewActivity.this.quitWebView();
      }
    });
    this.mContinueButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (CheckoutG2WebViewActivity.this.mNativeConfirmationViewIsShown) {
          CheckoutG2WebViewActivity.this.startActivity(HomeActivity.getIntent(CheckoutG2WebViewActivity.this, 2));
        }
        CheckoutG2WebViewActivity.this.quitWebView();
      }
    });
  }
  
  private class KCPPayBridge
  {
    private KCPPayBridge() {}
    
    @JavascriptInterface
    public void launchMISP(final String paramString)
    {
      CheckoutG2WebViewActivity.this.handler.post(new Runnable()
      {
        public void run()
        {
          Object localObject2 = paramString;
          Object localObject1 = localObject2;
          if (!paramString.equals("Install"))
          {
            localObject1 = localObject2;
            if (!CheckoutG2WebViewActivity.this.getPackageDownloadInstallState("kvp.jjy.MispAndroid")) {
              localObject1 = "Install";
            }
          }
          localObject2 = localObject1;
          if (((String)localObject1).equals("Install")) {
            localObject2 = "market://details?id=kvp.jjy.MispAndroid320";
          }
          localObject1 = new Intent("android.intent.action.VIEW", Uri.parse((String)localObject2));
          if (!paramString.equals("Install")) {
            CheckoutG2WebViewActivity.this.m_nStat = 2;
          }
          CheckoutG2WebViewActivity.this.startActivity((Intent)localObject1);
        }
      });
    }
  }
  
  private class KCPPayBridgeACNT
  {
    private KCPPayBridgeACNT() {}
    
    @JavascriptInterface
    public void launchAcnt(final String paramString)
    {
      CheckoutG2WebViewActivity.this.handler.post(new Runnable()
      {
        public void run()
        {
          if (CheckoutG2WebViewActivity.this.getPackageDownloadInstallState("com.kftc.bankpay.android"))
          {
            Intent localIntent = new Intent("android.intent.action.MAIN");
            localIntent.setComponent(new ComponentName("com.kftc.bankpay.android", "com.kftc.bankpay.android.activity.MainActivity"));
            localIntent.putExtra("requestInfo", paramString);
            CheckoutG2WebViewActivity.this.startActivityForResult(localIntent, 2);
            CheckoutG2WebViewActivity.access$2602(CheckoutG2WebViewActivity.this, true);
            return;
          }
          CheckoutG2WebViewActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.kftc.bankpay.android")));
          CheckoutG2WebViewActivity.this.mCheckoutWebView.loadUrl("javascript:submitPP_CLI_HUB()");
        }
      });
    }
  }
  
  private class KCPPayPinInfoBridge
  {
    private KCPPayPinInfoBridge() {}
    
    private void KCPShowDialogInstallPaypin()
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(CheckoutG2WebViewActivity.this);
      localBuilder.setTitle("Confirm");
      localBuilder.setMessage("PayPin app is not installed.\nPlease click install button to install it.\nPayment will be cancelled if you click cancel button.");
      localBuilder.setCancelable(false);
      localBuilder.setPositiveButton("Install", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
          if (!CheckoutG2WebViewActivity.this.KCPHandleUrlIntent("tstore://PRODUCT_VIEW/0000284061/0")) {
            CheckoutG2WebViewActivity.this.KCPHandleUrlIntent("market://details?id=com.skp.android.paypin&feature=search_result#?t=W251bGwsMSwxLDEsImNvbS5za3AuYW5kcm9pZC5wYXlwaW4iXQ.k");
          }
        }
      });
      localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      });
      localBuilder.create().show();
    }
    
    @JavascriptInterface
    public void getPaypinInfo(final String paramString)
    {
      CheckoutG2WebViewActivity.this.handler.post(new Runnable()
      {
        public void run()
        {
          if (!CheckoutG2WebViewActivity.this.getPackageDownloadInstallState("com.skp.android.paypin"))
          {
            CheckoutG2WebViewActivity.KCPPayPinInfoBridge.this.KCPShowDialogInstallPaypin();
            return;
          }
          CheckoutG2WebViewActivity.this.KCPHandleUrlIntent(paramString);
        }
      });
    }
  }
  
  private class KCPPayPinReturn
  {
    private KCPPayPinReturn() {}
    
    @JavascriptInterface
    public String getConfirm()
    {
      NespressoApplication localNespressoApplication = (NespressoApplication)CheckoutG2WebViewActivity.this.getApplication();
      if (localNespressoApplication.mKCPBType)
      {
        localNespressoApplication.mKCPBType = false;
        return "true";
      }
      return "false";
    }
  }
  
  private class MyWebChromeClient
    extends WebChromeClient
  {
    private Context mContext;
    
    public MyWebChromeClient(Context paramContext)
    {
      this.mContext = paramContext;
    }
    
    public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, final JsResult paramJsResult)
    {
      new AlertDialog.Builder(this.mContext).setTitle(CheckoutG2WebViewActivity.this.getApplicationContext().getResources().getString(2131165243)).setMessage(paramString2).setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramJsResult.confirm();
        }
      }).setCancelable(false).create().show();
      return true;
    }
  }
  
  private class MyWebViewClient
    extends NespressoWebViewClient
  {
    public MyWebViewClient(Context paramContext)
    {
      super(true, false);
    }
    
    public void finishWebView()
    {
      CheckoutG2WebViewActivity.this.quitWebView();
    }
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      CheckoutG2WebViewActivity.this.hideProgressBar();
      CheckoutG2WebViewActivity.access$1202(CheckoutG2WebViewActivity.this, new Date());
      long l1 = CheckoutG2WebViewActivity.this.mCheckoutEndTime.getTime();
      long l2 = CheckoutG2WebViewActivity.this.mCheckoutStartTime.getTime();
      CheckoutG2WebViewActivity.access$1502(CheckoutG2WebViewActivity.this, l1 - l2 + CheckoutG2WebViewActivity.this.mTotalLoadingTime);
      if (CheckoutG2WebViewActivity.this.mCheckoutWebView.getVisibility() == 8) {
        CheckoutG2WebViewActivity.this.showCheckoutWebView();
      }
      CheckoutG2WebViewActivity localCheckoutG2WebViewActivity;
      StringBuilder localStringBuilder;
      if ((CheckoutG2WebViewActivity.this.mCurrentCountry != null) && (CheckoutG2WebViewActivity.this.mCurrentCountry.equals(EnumCountries.SOUTH_KOREA.getValue())) && (paramString.contains("kcpmobile.do")))
      {
        paramString = Uri.parse(paramString);
        localCheckoutG2WebViewActivity = CheckoutG2WebViewActivity.this;
        localStringBuilder = new StringBuilder().append(paramString.getScheme()).append("://").append(paramString.getHost());
        if (paramString.getPort() <= 0) {
          break label249;
        }
      }
      label249:
      for (paramString = ":" + String.valueOf(paramString.getPort());; paramString = "")
      {
        CheckoutG2WebViewActivity.access$1802(localCheckoutG2WebViewActivity, paramString);
        paramWebView.loadUrl("javascript:(function(){ document.sfrm.AppUrl.value='" + CheckoutG2WebViewActivity.this.getString(2131165949) + "://card_pay'; })();");
        return;
      }
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      CheckoutG2WebViewActivity.this.showProgressBar();
      if ((CheckoutG2WebViewActivity.this.mCheckoutEndTime != null) && (CheckoutG2WebViewActivity.this.mCheckoutEndTime.getTime() > CheckoutG2WebViewActivity.this.mCheckoutStartTime.getTime())) {
        CheckoutG2WebViewActivity.access$1302(CheckoutG2WebViewActivity.this, new Date());
      }
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      DialogUtils.showDialogResourceNotFound(CheckoutG2WebViewActivity.this.getSupportFragmentManager(), true);
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      boolean bool2 = false;
      super.shouldOverrideUrlLoading(paramWebView, paramString);
      boolean bool1;
      if (((!TextUtils.isEmpty(CheckoutG2WebViewActivity.this.mCheckoutEndURL)) && (paramString.contains(CheckoutG2WebViewActivity.this.mCheckoutEndURL))) || (paramString.contains("emptyCart")))
      {
        paramWebView = Uri.parse(paramString).getQueryParameter("OrderMissingAmount");
        if (!TextUtils.isEmpty(paramWebView)) {
          CheckoutConstraintViolationsHandler.checkCheckoutOrderMinimumAmount(Double.valueOf(paramWebView).doubleValue());
        }
        CheckoutG2WebViewActivity.this.quitWebView();
        bool1 = true;
      }
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              if (paramString.contains(CheckoutG2WebViewActivity.this.mCheckoutConfirmationURL))
              {
                Cart.getInstance().removeAllProducts();
                paramWebView = paramString.substring(paramString.indexOf(CheckoutG2WebViewActivity.this.mCheckoutConfirmationURL) + CheckoutG2WebViewActivity.this.mCheckoutConfirmationURL.length() + 1);
                CheckoutG2WebViewActivity.this.showCheckoutConfirmationFragment(paramWebView);
                return false;
              }
              if (paramString.contains("authenticationFailed"))
              {
                User.getInstance().logout();
                CheckoutG2WebViewActivity.this.quitWebView();
              }
              CheckoutG2WebViewActivity.access$902(CheckoutG2WebViewActivity.this, CommonSharedPreferences.getInstance(CheckoutG2WebViewActivity.this.getApplicationContext()).getCountry());
              bool1 = bool2;
            } while (CheckoutG2WebViewActivity.this.mCurrentCountry == null);
            bool1 = bool2;
          } while (!CheckoutG2WebViewActivity.this.mCurrentCountry.equals(EnumCountries.SOUTH_KOREA.getValue()));
          bool1 = bool2;
        } while (paramString.equals("about:blank"));
        if (!paramString.startsWith("http")) {
          return CheckoutG2WebViewActivity.this.KCPHandleUrlIntent(paramString);
        }
        if ((paramString.contains("http://market.android.com")) || (paramString.contains("http://m.ahnlab.com/kr/site/download"))) {
          break;
        }
        bool1 = bool2;
      } while (!paramString.endsWith(".apk"));
      return CheckoutG2WebViewActivity.this.KCPHandleUrlIntent(paramString);
    }
  }
}
