package com.amfam.myamfam;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.CryptoObject;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec.Builder;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.adobe.mobile.Config;
import com.amfam.myamfam.fingerprint.FingerprintAuthenticationDialogFragment;
import com.amfam.myamfam.fingerprint.FingerprintAuthenticationDialogFragment.Stage;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class FingerWebWindow
  extends AppCompatActivity
{
  private static final String DIALOG_FRAGMENT_TAG = "myFragment";
  private static final String KEY_NAME = "my_key";
  private static final String SECRET_MESSAGE = "Very secret message";
  private static final String TAG = MainActivity.class.getSimpleName();
  private static boolean dialogIsOpen;
  public static FingerprintManager fingerprintManager;
  public static Boolean isDialogLaunched = Boolean.valueOf(false);
  public static UserInput userInput;
  private static WebView webView;
  private String currURL;
  Boolean hasFingerPrint;
  Boolean isFirstLoad = Boolean.valueOf(true);
  private boolean isPassWalletAppInstalled;
  Cipher mCipher;
  private String mDisposition;
  FingerprintAuthenticationDialogFragment mFragment;
  KeyGenerator mKeyGenerator;
  KeyStore mKeyStore;
  private String mMimeType;
  private ProgressDialog progressDialog;
  Boolean resetPageVisit = Boolean.valueOf(false);
  SharedPreferences.Editor sharedPrefEditor;
  SharedPreferences sharedPreferences;
  public Boolean usesFingerprint;
  
  public FingerWebWindow() {}
  
  @TargetApi(23)
  private boolean initCipher()
  {
    try
    {
      if (this.mKeyStore == null) {
        createKey();
      }
      this.mKeyStore.load(null);
      SecretKey localSecretKey = (SecretKey)this.mKeyStore.getKey("my_key", null);
      this.mCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
      this.mCipher.init(1, localSecretKey);
      return true;
    }
    catch (KeyPermanentlyInvalidatedException localKeyPermanentlyInvalidatedException)
    {
      return false;
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      throw new RuntimeException("Failed to init Cipher", localNoSuchPaddingException);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return false;
    }
    catch (CertificateException localCertificateException)
    {
      for (;;) {}
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;) {}
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
    catch (UnrecoverableKeyException localUnrecoverableKeyException)
    {
      for (;;) {}
    }
    catch (KeyStoreException localKeyStoreException)
    {
      for (;;) {}
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      for (;;) {}
    }
  }
  
  @TargetApi(23)
  public static void injectJavaScript(String paramString1, String paramString2)
  {
    webView.loadUrl("javascript:var temp1 = document.Login.USER.value = '" + paramString1 + "';" + "var temp2 = document.Login.PASSWORD.value = '" + paramString2 + "';" + "var frms = document.getElementsByName('Login');" + "frms[0].submit()");
  }
  
  private void launchReview()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName()));
    SharedPreferences.Editor localEditor = getSharedPreferences("MyAmfamPrefs", 0).edit();
    localEditor.putString("VERSION_NUMBER", Util.getVersionNumber(this));
    localEditor.commit();
    try
    {
      startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Toast.makeText(this, " unable to find market app", 1).show();
    }
  }
  
  private void loginPath(String paramString)
  {
    this.currURL = paramString;
    if ((this.hasFingerPrint.booleanValue()) && (this.usesFingerprint.booleanValue()))
    {
      if ((!this.isFirstLoad.booleanValue()) || (!paramString.contains("/MyAccount/home/"))) {
        break label51;
      }
      manualLoginSuccess(paramString);
    }
    label51:
    do
    {
      return;
      if ((!this.isFirstLoad.booleanValue()) && ((paramString.equals("https://my.amfam.com/MyAccount/login/")) || (paramString.contains("https://my.amfam.com/MyAccount/forgottenPassword/cancel.do")) || (paramString.equals("https://my.amfam.com/MyAccount/login/login.do?poiLogin=true"))))
      {
        subsequentLogin();
        return;
      }
      if ((!this.isFirstLoad.booleanValue()) && (paramString.contains("/login/?TYPE=")))
      {
        Log.d("LOGIN", "FAILURE");
        this.isFirstLoad = Boolean.valueOf(true);
        return;
      }
      if ((!this.isFirstLoad.booleanValue()) && (paramString.equals("https://my.amfam.com/MyAccount/forgottenPassword/forgotPassQuestionOne.do")))
      {
        this.isFirstLoad = Boolean.valueOf(true);
        return;
      }
      if ((this.isFirstLoad.booleanValue()) && (paramString.contains("https://my.amfam.com/siteminderagent/forms/smpwservices.fcc?")))
      {
        this.resetPageVisit = Boolean.valueOf(true);
        this.isFirstLoad = Boolean.valueOf(false);
        return;
      }
    } while (!paramString.equals("https://my.amfam.com/MyAccount/forgottenUserID/forgottenUserIDPassword.do"));
    isDialogLaunched = Boolean.valueOf(false);
  }
  
  private void neverAgain()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("MyAmfamPrefs", 0).edit();
    localEditor.putString("VERSION_NUMBER", Util.getVersionNumber(this));
    localEditor.commit();
  }
  
  private boolean neverShow()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("MyAmfamPrefs", 0);
    String str = localSharedPreferences.getString("VERSION_NUMBER", "0");
    long l = localSharedPreferences.getLong("REMIND_LATER", 0L);
    if (str.equals(Util.getVersionNumber(this)))
    {
      if (l == 0L) {}
      while (!showMeLater()) {
        return true;
      }
      return false;
    }
    return false;
  }
  
  private void remindMe()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("MyAmfamPrefs", 0).edit();
    localEditor.putLong("REMIND_LATER", System.currentTimeMillis());
    localEditor.putString("VERSION_NUMBER", Util.getVersionNumber(this));
    localEditor.commit();
  }
  
  private boolean showMeLater()
  {
    long l = getSharedPreferences("MyAmfamPrefs", 0).getLong("REMIND_LATER", 0L);
    if (l == 0L) {}
    while (System.currentTimeMillis() >= 7776000000L + l) {
      return true;
    }
    return false;
  }
  
  private void subsequentLogin()
  {
    Log.d("LOGIN", "subsequent logins");
    Log.d("resetPageVisit", this.resetPageVisit.toString());
    Log.d("isDialogLaunched", isDialogLaunched.toString());
    if (!this.resetPageVisit.booleanValue())
    {
      if (!isDialogLaunched.booleanValue())
      {
        isDialogLaunched = Boolean.valueOf(true);
        launchDialog();
      }
      return;
    }
    android.app.AlertDialog.Builder localBuilder = new android.app.AlertDialog.Builder(this);
    localBuilder.setTitle("Password Change");
    localBuilder.setMessage("We've detected you've changed your password. Please login manually.");
    localBuilder.setNegativeButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.show();
    this.resetPageVisit = Boolean.valueOf(false);
    this.isFirstLoad = Boolean.valueOf(true);
  }
  
  @TargetApi(23)
  public void createKey()
  {
    try
    {
      this.mKeyStore = KeyStore.getInstance("AndroidKeyStore");
      this.mKeyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
      this.mKeyStore.load(null);
      this.mKeyGenerator.init(new KeyGenParameterSpec.Builder("my_key", 3).setBlockModes(new String[] { "CBC" }).setUserAuthenticationRequired(true).setEncryptionPaddings(new String[] { "PKCS7Padding" }).build());
      this.mKeyGenerator.generateKey();
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new RuntimeException(localNoSuchAlgorithmException);
    }
    catch (CertificateException localCertificateException)
    {
      for (;;) {}
    }
    catch (NoSuchProviderException localNoSuchProviderException)
    {
      for (;;) {}
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
    catch (InvalidAlgorithmParameterException localInvalidAlgorithmParameterException)
    {
      for (;;) {}
    }
    catch (KeyStoreException localKeyStoreException)
    {
      for (;;) {}
    }
  }
  
  public String decryptUtil(String paramString)
  {
    return EncryptionUtil.decrypt(paramString, Util.getDeviceId(getApplication()));
  }
  
  public String encryptUtil(String paramString)
  {
    return EncryptionUtil.encrypt(paramString, Util.getDeviceId(getApplication()));
  }
  
  public void getCredentials()
  {
    webView.loadUrl("javascript:Android.setUser(document.Login.USER.value);Android.setPass(document.Login.PASSWORD.value);");
  }
  
  @TargetApi(23)
  public void launchDialog()
  {
    if ((Build.VERSION.SDK_INT >= 23) && (this.hasFingerPrint.booleanValue()) && (initCipher()))
    {
      this.mFragment = new FingerprintAuthenticationDialogFragment();
      this.mFragment.setCryptoObject(new FingerprintManager.CryptoObject(this.mCipher));
      if (!this.hasFingerPrint.booleanValue()) {
        break label88;
      }
      this.mFragment.setStage(FingerprintAuthenticationDialogFragment.Stage.FINGERPRINT);
    }
    for (;;)
    {
      try
      {
        this.mFragment.show(getFragmentManager(), "myFragment");
        return;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        label88:
        isDialogLaunched = Boolean.valueOf(false);
        localIllegalStateException.printStackTrace();
      }
      this.mFragment.setStage(FingerprintAuthenticationDialogFragment.Stage.PASSWORD);
    }
  }
  
  public void manualLoginSuccess(String paramString)
  {
    Log.d("LOGIN", "SUCCESS");
    paramString = CookieManager.getInstance().getCookie(paramString);
    if (paramString != null)
    {
      paramString = paramString.split(";");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        android.app.AlertDialog.Builder localBuilder = paramString[i];
        if ((localBuilder.contains("SMSESSION")) && (!localBuilder.split("=")[1].equals("LOGGEDOFF")) && (userInput.getUser() != null))
        {
          Log.d("USER", userInput.getUser());
          localBuilder = new android.app.AlertDialog.Builder(this);
          localBuilder.setTitle("Fingerprint Login");
          localBuilder.setMessage("Would you like to use fingerprint authentication for future logins?");
          localBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              FingerWebWindow.this.usesFingerprint = Boolean.valueOf(true);
              Util.getDeviceId(FingerWebWindow.this.getApplication());
              Util.LogIntoSiteCatalyst("MyAmfam:TouchID_Yes");
              FingerWebWindow.this.sharedPrefEditor.putString("user", FingerWebWindow.this.encryptUtil(FingerWebWindow.userInput.getUser()));
              FingerWebWindow.this.sharedPrefEditor.putString("pass", FingerWebWindow.this.encryptUtil(FingerWebWindow.userInput.getPass()));
              FingerWebWindow.this.sharedPrefEditor.putBoolean("usesFingerprint", true);
              FingerWebWindow.this.sharedPrefEditor.putBoolean("firstTimeMyAccount", false);
              FingerWebWindow.this.sharedPrefEditor.commit();
              paramAnonymousDialogInterface.dismiss();
            }
          });
          localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              FingerWebWindow.this.usesFingerprint = Boolean.valueOf(false);
              Util.LogIntoSiteCatalyst("MyAmfam:TouchID_No");
              FingerWebWindow.this.sharedPrefEditor.putBoolean("usesFingerprint", false);
              FingerWebWindow.this.sharedPrefEditor.putBoolean("firstTimeMyAccount", false);
              FingerWebWindow.this.sharedPrefEditor.commit();
              paramAnonymousDialogInterface.cancel();
            }
          });
          localBuilder.show();
          this.isFirstLoad = Boolean.valueOf(false);
        }
        i += 1;
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903090);
    Config.setContext(getApplicationContext());
    dialogIsOpen = false;
    setSupportActionBar((Toolbar)findViewById(2131689616));
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    Util.LogIntoSiteCatalyst("MyAmfam:MyAccountLogin");
    paramBundle = getIntent().getStringExtra("url");
    this.sharedPreferences = getSharedPreferences("MyAmFamFingerprint", 0);
    this.usesFingerprint = Boolean.valueOf(this.sharedPreferences.getBoolean("usesFingerprint", true));
    String str;
    if (Build.VERSION.SDK_INT >= 23) {
      if (ContextCompat.checkSelfPermission(this, "android.permission.USE_FINGERPRINT") != 0) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.USE_FINGERPRINT"))
        {
          this.hasFingerPrint = Boolean.valueOf(false);
          fingerprintManager = (FingerprintManager)getApplicationContext().getSystemService("fingerprint");
          if (fingerprintManager.isHardwareDetected()) {
            if (fingerprintManager.hasEnrolledFingerprints())
            {
              this.hasFingerPrint = Boolean.valueOf(true);
              if (paramBundle != null)
              {
                str = getIntent().getStringExtra("title");
                this.sharedPrefEditor = this.sharedPreferences.edit();
                if (this.usesFingerprint.booleanValue())
                {
                  isDialogLaunched = Boolean.valueOf(false);
                  if (!this.sharedPreferences.getBoolean("firstTimeMyAccount", true)) {
                    break label604;
                  }
                  userInput = new UserInput();
                  this.isFirstLoad = Boolean.valueOf(true);
                }
                label268:
                if (str != null) {
                  setTitle(str);
                }
                webView = (WebView)findViewById(2131689731);
                webView.setWebViewClient(new WebViewClient()
                {
                  public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
                  {
                    super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
                    if (FingerWebWindow.this.usesFingerprint.booleanValue()) {
                      FingerWebWindow.this.loginPath(paramAnonymousString);
                    }
                    if (paramAnonymousString.contains("/customer/confirmMakePayment")) {
                      FingerWebWindow.this.showReviewPromptDialog();
                    }
                    paramAnonymousWebView = CookieManager.getInstance();
                    if (((paramAnonymousString.toLowerCase(Locale.getDefault()).contains("home.do")) || (paramAnonymousString.toLowerCase(Locale.getDefault()).contains("registeraccount.do"))) && (MainActivity.webSessionCookie.length() < 1)) {
                      MainActivity.webSessionCookie = paramAnonymousWebView.getCookie(paramAnonymousString);
                    }
                    if (paramAnonymousString.toLowerCase().contains("forgottenuserid"))
                    {
                      paramAnonymousWebView.removeAllCookie();
                      paramAnonymousWebView.removeSessionCookie();
                    }
                  }
                  
                  public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
                  {
                    super.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
                    if ((FingerWebWindow.this.usesFingerprint.booleanValue()) && (FingerWebWindow.this.isFirstLoad.booleanValue()) && ((paramAnonymousString.equals("https://my.amfam.com/MyAccount/home/home.do")) || (paramAnonymousString.equals("https://my.amfam.com/MyAccount/login/login.do?poiLogin=true")))) {
                      FingerWebWindow.this.getCredentials();
                    }
                  }
                  
                  public void onReceivedSslError(WebView paramAnonymousWebView, SslErrorHandler paramAnonymousSslErrorHandler, SslError paramAnonymousSslError)
                  {
                    paramAnonymousSslErrorHandler.cancel();
                  }
                  
                  public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
                  {
                    if (paramAnonymousString.indexOf("tel:") > -1)
                    {
                      FingerWebWindow.this.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(paramAnonymousString)));
                      return true;
                    }
                    return false;
                  }
                });
              }
              webView.getSettings().setJavaScriptEnabled(true);
              webView.getSettings().setDomStorageEnabled(true);
              webView.getSettings().setUseWideViewPort(true);
              webView.getSettings().setDisplayZoomControls(true);
              webView.getSettings().setSupportZoom(true);
              webView.getSettings().setBuiltInZoomControls(true);
              if (this.usesFingerprint.booleanValue()) {
                webView.addJavascriptInterface(userInput, "Android");
              }
              if (!paramBundle.toLowerCase(Locale.getDefault()).contains("paynow")) {
                break label676;
              }
              webView.setInitialScale(60);
            }
          }
        }
      }
    }
    for (;;)
    {
      webView.loadUrl(paramBundle);
      this.progressDialog = new ProgressDialog(this);
      webView.setDownloadListener(new DownloadListener()
      {
        public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong)
        {
          try
          {
            FingerWebWindow.access$102(FingerWebWindow.this, paramAnonymousString3);
            FingerWebWindow.access$202(FingerWebWindow.this, paramAnonymousString4);
            new FingerWebWindow.DownloadFileAsync(FingerWebWindow.this).execute(new String[] { paramAnonymousString1 });
            return;
          }
          catch (Exception paramAnonymousString1) {}
        }
      });
      return;
      Toast.makeText(this, "No fingerprint found on device", 1).show();
      break;
      this.hasFingerPrint = Boolean.valueOf(false);
      this.usesFingerprint = Boolean.valueOf(false);
      Toast.makeText(this, "Fingerprint hardware not detected", 1).show();
      break;
      fingerprintManager = (FingerprintManager)getApplicationContext().getSystemService("fingerprint");
      ActivityCompat.requestPermissions(this, new String[] { "android.permission.USE_FINGERPRINT", "android.permission.INTERNET" }, 100);
      this.hasFingerPrint = Boolean.valueOf(true);
      break;
      fingerprintManager = (FingerprintManager)getApplicationContext().getSystemService("fingerprint");
      if ((fingerprintManager.isHardwareDetected()) && (fingerprintManager.hasEnrolledFingerprints()))
      {
        this.hasFingerPrint = Boolean.valueOf(true);
        break;
      }
      this.hasFingerPrint = Boolean.valueOf(false);
      break;
      this.usesFingerprint = Boolean.valueOf(false);
      break;
      label604:
      userInput = new UserInput();
      userInput.setPass(decryptUtil(this.sharedPreferences.getString("pass", "")));
      userInput.setUser(decryptUtil(this.sharedPreferences.getString("user", "")));
      this.isFirstLoad = Boolean.valueOf(false);
      break label268;
      label676:
      if (paramBundle.toLowerCase(Locale.getDefault()).contains("poi"))
      {
        Util.LogIntoSiteCatalyst("MyAmfam:POILogin");
        str = webView.getContext().getDir("databases", 0).getPath();
        webView.getSettings().setDatabasePath(str);
        webView.loadUrl("javascript:(localStorage.setItem(\"selectedPage\", \"poiSummaryTab\");)");
      }
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131755009, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    new ToolbarSetup(this).onOptionsItemSelected(i);
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    if ((this.usesFingerprint.booleanValue()) && (this.currURL != null))
    {
      if (!this.currURL.contains("/MyAccount/home"))
      {
        CookieManager localCookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= 23) {
          localCookieManager.removeAllCookies(null);
        }
        Log.d("onPause", "cookies cleared");
      }
      this.sharedPrefEditor.putString("user", encryptUtil(userInput.getUser()));
      this.sharedPrefEditor.putString("pass", encryptUtil(userInput.getPass()));
      this.sharedPrefEditor.putBoolean("firstTimeMyAccount", this.isFirstLoad.booleanValue());
      this.sharedPrefEditor.commit();
    }
    Config.pauseCollectingLifecycleData();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    new ToolbarSetup(this).onPrepareOptionsMenu(paramMenu);
    return super.onPrepareOptionsMenu(paramMenu);
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    }
    if ((paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0))
    {
      this.hasFingerPrint = Boolean.valueOf(true);
      return;
    }
    this.hasFingerPrint = Boolean.valueOf(false);
  }
  
  protected void onResume()
  {
    super.onResume();
    Config.collectLifecycleData(this);
  }
  
  public void showReviewPromptDialog()
  {
    if ((!neverShow()) && (!dialogIsOpen))
    {
      dialogIsOpen = true;
      Util.LogIntoSiteCatalyst("MyAmfam:FeedbackPrompt");
      android.support.v7.app.AlertDialog.Builder localBuilder = new android.support.v7.app.AlertDialog.Builder(this);
      localBuilder.setTitle("We want to hear your feedback.  Please rate us in the App Store.");
      localBuilder.setCancelable(false);
      DialogInterface.OnClickListener local6 = new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          switch (paramAnonymousInt)
          {
          default: 
            return;
          case 0: 
            Util.LogIntoSiteCatalyst("MyAmfam:RateApp");
            FingerWebWindow.this.launchReview();
            return;
          case 1: 
            Util.LogIntoSiteCatalyst("MyAmfam:Improvement");
            FingerWebWindow.webView.loadUrl("http://web.amfam.com/customer/forms/contact_amfamform.asp");
            FingerWebWindow.this.remindMe();
            return;
          case 2: 
            Util.LogIntoSiteCatalyst("MyAmfam:FeedbackNoThanks");
            FingerWebWindow.this.neverAgain();
            return;
          }
          Util.LogIntoSiteCatalyst("MyAmfam:FeedbackRemindLater");
          FingerWebWindow.this.remindMe();
        }
      };
      localBuilder.setItems(new CharSequence[] { "Rate My Amfam", "Room for improvement", "No, thanks", "Remind me later" }, local6);
      localBuilder.show();
    }
  }
  
  @TargetApi(23)
  public void tryEncrypt()
  {
    try
    {
      this.mCipher.doFinal("Very secret message".getBytes());
      return;
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      Toast.makeText(this, "Failed to encrypt the data with the generated key. Retry the purchase", 1).show();
      Log.e(TAG, "Failed to encrypt the data with the generated key." + localIllegalBlockSizeException.getMessage());
      return;
    }
    catch (BadPaddingException localBadPaddingException)
    {
      for (;;) {}
    }
  }
  
  public class DownloadFileAsync
    extends AsyncTask<String, Void, String>
  {
    private File rootDir;
    
    public DownloadFileAsync() {}
    
    private boolean isPassWalletAppInstalled()
    {
      boolean bool2 = false;
      boolean bool1 = false;
      try
      {
        Iterator localIterator = FingerWebWindow.this.getPackageManager().getInstalledApplications(128).iterator();
        boolean bool3;
        for (;;)
        {
          bool2 = bool1;
          bool3 = bool1;
          if (!localIterator.hasNext()) {
            break;
          }
          bool2 = bool1;
          bool3 = ((ApplicationInfo)localIterator.next()).packageName.toLowerCase(Locale.getDefault()).contains("passwallet");
          if (bool3) {
            bool1 = true;
          }
        }
        return bool3;
      }
      catch (Exception localException)
      {
        bool3 = bool2;
      }
    }
    
    public void checkAndCreateDirectory(String paramString)
    {
      paramString = new File(this.rootDir + paramString);
      if (!paramString.exists()) {
        paramString.mkdirs();
      }
    }
    
    /* Error */
    protected String doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: ldc 110
      //   2: astore 4
      //   4: invokestatic 116	java/util/UUID:randomUUID	()Ljava/util/UUID;
      //   7: invokevirtual 117	java/util/UUID:toString	()Ljava/lang/String;
      //   10: astore 5
      //   12: aload_0
      //   13: getfield 16	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/FingerWebWindow;
      //   16: invokestatic 121	com/amfam/myamfam/FingerWebWindow:access$100	(Lcom/amfam/myamfam/FingerWebWindow;)Ljava/lang/String;
      //   19: ldc 123
      //   21: invokevirtual 73	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   24: ifeq +514 -> 538
      //   27: aload_0
      //   28: getfield 16	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/FingerWebWindow;
      //   31: invokestatic 121	com/amfam/myamfam/FingerWebWindow:access$100	(Lcom/amfam/myamfam/FingerWebWindow;)Ljava/lang/String;
      //   34: ldc 123
      //   36: invokevirtual 127	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   39: iconst_1
      //   40: aaload
      //   41: invokestatic 61	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   44: invokevirtual 67	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
      //   47: ldc -127
      //   49: ldc 110
      //   51: invokevirtual 133	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   54: ldc -121
      //   56: ldc 110
      //   58: invokevirtual 133	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   61: ldc -121
      //   63: ldc 110
      //   65: invokevirtual 133	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   68: astore 4
      //   70: ldc -119
      //   72: iconst_2
      //   73: anewarray 139	java/lang/Object
      //   76: dup
      //   77: iconst_0
      //   78: aload 5
      //   80: aastore
      //   81: dup
      //   82: iconst_1
      //   83: aload 4
      //   85: aastore
      //   86: invokestatic 143	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   89: astore 14
      //   91: aconst_null
      //   92: astore 8
      //   94: aconst_null
      //   95: astore 11
      //   97: aconst_null
      //   98: astore 10
      //   100: aconst_null
      //   101: astore 13
      //   103: aconst_null
      //   104: astore 9
      //   106: aconst_null
      //   107: astore 12
      //   109: aconst_null
      //   110: astore 7
      //   112: aconst_null
      //   113: astore 4
      //   115: aload 8
      //   117: astore 5
      //   119: aload 13
      //   121: astore 6
      //   123: new 145	java/net/URL
      //   126: dup
      //   127: aload_1
      //   128: iconst_0
      //   129: aaload
      //   130: invokespecial 146	java/net/URL:<init>	(Ljava/lang/String;)V
      //   133: invokevirtual 150	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   136: checkcast 152	java/net/HttpURLConnection
      //   139: astore_1
      //   140: aload_1
      //   141: astore 4
      //   143: aload 8
      //   145: astore 5
      //   147: aload 13
      //   149: astore 6
      //   151: aload_1
      //   152: astore 7
      //   154: aload_1
      //   155: ldc -102
      //   157: getstatic 159	com/amfam/myamfam/MainActivity:webSessionCookie	Ljava/lang/String;
      //   160: invokevirtual 163	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   163: aload_1
      //   164: astore 4
      //   166: aload 8
      //   168: astore 5
      //   170: aload 13
      //   172: astore 6
      //   174: aload_1
      //   175: astore 7
      //   177: aload_1
      //   178: invokevirtual 166	java/net/HttpURLConnection:connect	()V
      //   181: aload_1
      //   182: astore 4
      //   184: aload 8
      //   186: astore 5
      //   188: aload 13
      //   190: astore 6
      //   192: aload_1
      //   193: astore 7
      //   195: aload_0
      //   196: getstatic 171	android/os/Environment:DIRECTORY_DOWNLOADS	Ljava/lang/String;
      //   199: invokestatic 175	android/os/Environment:getExternalStoragePublicDirectory	(Ljava/lang/String;)Ljava/io/File;
      //   202: putfield 82	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:rootDir	Ljava/io/File;
      //   205: aload_1
      //   206: astore 4
      //   208: aload 8
      //   210: astore 5
      //   212: aload 13
      //   214: astore 6
      //   216: aload_1
      //   217: astore 7
      //   219: new 177	java/io/FileOutputStream
      //   222: dup
      //   223: new 77	java/io/File
      //   226: dup
      //   227: new 79	java/lang/StringBuilder
      //   230: dup
      //   231: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   234: aload_0
      //   235: getfield 82	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:rootDir	Ljava/io/File;
      //   238: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   241: ldc -77
      //   243: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   246: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   249: aload 14
      //   251: invokespecial 181	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   254: invokespecial 184	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   257: astore 8
      //   259: aload 12
      //   261: astore 7
      //   263: aload 9
      //   265: astore 4
      //   267: aload_1
      //   268: invokevirtual 188	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   271: astore 5
      //   273: aload 5
      //   275: astore 7
      //   277: aload 5
      //   279: astore 4
      //   281: sipush 1024
      //   284: newarray byte
      //   286: astore 6
      //   288: aload 5
      //   290: astore 7
      //   292: aload 5
      //   294: astore 4
      //   296: aload 5
      //   298: aload 6
      //   300: invokevirtual 194	java/io/InputStream:read	([B)I
      //   303: istore_2
      //   304: iload_2
      //   305: ifle +262 -> 567
      //   308: aload 5
      //   310: astore 7
      //   312: aload 5
      //   314: astore 4
      //   316: aload 8
      //   318: aload 6
      //   320: iconst_0
      //   321: iload_2
      //   322: invokevirtual 198	java/io/FileOutputStream:write	([BII)V
      //   325: goto -37 -> 288
      //   328: astore 9
      //   330: aload_1
      //   331: astore 4
      //   333: aload 8
      //   335: astore 5
      //   337: aload 7
      //   339: astore 6
      //   341: ldc -56
      //   343: aload 9
      //   345: invokevirtual 203	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   348: invokestatic 209	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   351: pop
      //   352: aload_1
      //   353: ifnull +7 -> 360
      //   356: aload_1
      //   357: invokevirtual 212	java/net/HttpURLConnection:disconnect	()V
      //   360: aload 8
      //   362: ifnull +8 -> 370
      //   365: aload 8
      //   367: invokevirtual 215	java/io/FileOutputStream:close	()V
      //   370: aload 7
      //   372: ifnull +8 -> 380
      //   375: aload 7
      //   377: invokevirtual 216	java/io/InputStream:close	()V
      //   380: iconst_1
      //   381: istore_3
      //   382: iload_3
      //   383: istore_2
      //   384: aload_0
      //   385: getfield 16	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/FingerWebWindow;
      //   388: invokestatic 219	com/amfam/myamfam/FingerWebWindow:access$200	(Lcom/amfam/myamfam/FingerWebWindow;)Ljava/lang/String;
      //   391: invokestatic 61	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   394: invokevirtual 67	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
      //   397: ldc -35
      //   399: invokevirtual 73	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   402: ifne +52 -> 454
      //   405: aload_0
      //   406: getfield 16	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/FingerWebWindow;
      //   409: aload_0
      //   410: invokespecial 223	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:isPassWalletAppInstalled	()Z
      //   413: invokestatic 227	com/amfam/myamfam/FingerWebWindow:access$402	(Lcom/amfam/myamfam/FingerWebWindow;Z)Z
      //   416: pop
      //   417: iload_3
      //   418: istore_2
      //   419: aload_0
      //   420: getfield 16	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/FingerWebWindow;
      //   423: invokestatic 219	com/amfam/myamfam/FingerWebWindow:access$200	(Lcom/amfam/myamfam/FingerWebWindow;)Ljava/lang/String;
      //   426: invokestatic 61	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   429: invokevirtual 67	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
      //   432: ldc -27
      //   434: invokevirtual 73	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   437: ifeq +17 -> 454
      //   440: iload_3
      //   441: istore_2
      //   442: aload_0
      //   443: getfield 16	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/FingerWebWindow;
      //   446: invokestatic 233	com/amfam/myamfam/FingerWebWindow:access$400	(Lcom/amfam/myamfam/FingerWebWindow;)Z
      //   449: ifne +5 -> 454
      //   452: iconst_0
      //   453: istore_2
      //   454: iload_2
      //   455: ifeq +296 -> 751
      //   458: ldc -21
      //   460: iconst_1
      //   461: anewarray 139	java/lang/Object
      //   464: dup
      //   465: iconst_0
      //   466: new 79	java/lang/StringBuilder
      //   469: dup
      //   470: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   473: aload_0
      //   474: getfield 82	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:rootDir	Ljava/io/File;
      //   477: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   480: ldc -77
      //   482: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   485: aload 14
      //   487: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   490: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   493: aastore
      //   494: invokestatic 143	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   497: astore_1
      //   498: new 237	android/content/Intent
      //   501: dup
      //   502: ldc -17
      //   504: invokespecial 240	android/content/Intent:<init>	(Ljava/lang/String;)V
      //   507: astore 4
      //   509: aload 4
      //   511: aload_1
      //   512: invokestatic 246	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
      //   515: aload_0
      //   516: getfield 16	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/FingerWebWindow;
      //   519: invokestatic 219	com/amfam/myamfam/FingerWebWindow:access$200	(Lcom/amfam/myamfam/FingerWebWindow;)Ljava/lang/String;
      //   522: invokevirtual 250	android/content/Intent:setDataAndType	(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;
      //   525: pop
      //   526: aload_0
      //   527: getfield 16	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/FingerWebWindow;
      //   530: aload 4
      //   532: invokevirtual 254	com/amfam/myamfam/FingerWebWindow:startActivity	(Landroid/content/Intent;)V
      //   535: goto +216 -> 751
      //   538: aload_0
      //   539: getfield 16	com/amfam/myamfam/FingerWebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/FingerWebWindow;
      //   542: invokestatic 219	com/amfam/myamfam/FingerWebWindow:access$200	(Lcom/amfam/myamfam/FingerWebWindow;)Ljava/lang/String;
      //   545: invokestatic 61	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   548: invokevirtual 67	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
      //   551: ldc -35
      //   553: invokevirtual 73	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   556: ifeq -486 -> 70
      //   559: ldc_w 256
      //   562: astore 4
      //   564: goto -494 -> 70
      //   567: aload 5
      //   569: astore 7
      //   571: aload 5
      //   573: astore 4
      //   575: aload 8
      //   577: invokevirtual 215	java/io/FileOutputStream:close	()V
      //   580: aload 5
      //   582: astore 7
      //   584: aload 5
      //   586: astore 4
      //   588: aload 5
      //   590: invokevirtual 216	java/io/InputStream:close	()V
      //   593: aload 5
      //   595: astore 7
      //   597: aload 5
      //   599: astore 4
      //   601: aload_1
      //   602: invokevirtual 212	java/net/HttpURLConnection:disconnect	()V
      //   605: aload_1
      //   606: ifnull +7 -> 613
      //   609: aload_1
      //   610: invokevirtual 212	java/net/HttpURLConnection:disconnect	()V
      //   613: aload 8
      //   615: ifnull +8 -> 623
      //   618: aload 8
      //   620: invokevirtual 215	java/io/FileOutputStream:close	()V
      //   623: aload 5
      //   625: ifnull +8 -> 633
      //   628: aload 5
      //   630: invokevirtual 216	java/io/InputStream:close	()V
      //   633: goto -253 -> 380
      //   636: astore_1
      //   637: goto -257 -> 380
      //   640: astore_1
      //   641: aload 4
      //   643: astore 7
      //   645: aload 7
      //   647: ifnull +8 -> 655
      //   650: aload 7
      //   652: invokevirtual 212	java/net/HttpURLConnection:disconnect	()V
      //   655: aload 5
      //   657: ifnull +8 -> 665
      //   660: aload 5
      //   662: invokevirtual 215	java/io/FileOutputStream:close	()V
      //   665: aload 6
      //   667: ifnull +8 -> 675
      //   670: aload 6
      //   672: invokevirtual 216	java/io/InputStream:close	()V
      //   675: aload_1
      //   676: athrow
      //   677: astore_1
      //   678: goto +73 -> 751
      //   681: astore_1
      //   682: goto -69 -> 613
      //   685: astore_1
      //   686: goto -63 -> 623
      //   689: astore_1
      //   690: goto -330 -> 360
      //   693: astore_1
      //   694: goto -324 -> 370
      //   697: astore_1
      //   698: goto -318 -> 380
      //   701: astore 4
      //   703: goto -48 -> 655
      //   706: astore 4
      //   708: goto -43 -> 665
      //   711: astore 4
      //   713: goto -38 -> 675
      //   716: astore 9
      //   718: aload 8
      //   720: astore 5
      //   722: aload_1
      //   723: astore 7
      //   725: aload 4
      //   727: astore 6
      //   729: aload 9
      //   731: astore_1
      //   732: goto -87 -> 645
      //   735: astore 9
      //   737: aload 7
      //   739: astore_1
      //   740: aload 11
      //   742: astore 8
      //   744: aload 10
      //   746: astore 7
      //   748: goto -418 -> 330
      //   751: aconst_null
      //   752: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	753	0	this	DownloadFileAsync
      //   0	753	1	paramVarArgs	String[]
      //   303	152	2	i	int
      //   381	60	3	j	int
      //   2	640	4	localObject1	Object
      //   701	1	4	localException1	Exception
      //   706	1	4	localException2	Exception
      //   711	15	4	localException3	Exception
      //   10	711	5	localObject2	Object
      //   121	607	6	localObject3	Object
      //   110	637	7	localObject4	Object
      //   92	651	8	localObject5	Object
      //   104	160	9	localObject6	Object
      //   328	16	9	localException4	Exception
      //   716	14	9	localObject7	Object
      //   735	1	9	localException5	Exception
      //   98	647	10	localObject8	Object
      //   95	646	11	localObject9	Object
      //   107	153	12	localObject10	Object
      //   101	112	13	localObject11	Object
      //   89	397	14	str	String
      // Exception table:
      //   from	to	target	type
      //   267	273	328	java/lang/Exception
      //   281	288	328	java/lang/Exception
      //   296	304	328	java/lang/Exception
      //   316	325	328	java/lang/Exception
      //   575	580	328	java/lang/Exception
      //   588	593	328	java/lang/Exception
      //   601	605	328	java/lang/Exception
      //   628	633	636	java/lang/Exception
      //   123	140	640	finally
      //   154	163	640	finally
      //   177	181	640	finally
      //   195	205	640	finally
      //   219	259	640	finally
      //   341	352	640	finally
      //   4	70	677	java/lang/Exception
      //   70	91	677	java/lang/Exception
      //   384	417	677	java/lang/Exception
      //   419	440	677	java/lang/Exception
      //   442	452	677	java/lang/Exception
      //   458	535	677	java/lang/Exception
      //   538	559	677	java/lang/Exception
      //   675	677	677	java/lang/Exception
      //   609	613	681	java/lang/Exception
      //   618	623	685	java/lang/Exception
      //   356	360	689	java/lang/Exception
      //   365	370	693	java/lang/Exception
      //   375	380	697	java/lang/Exception
      //   650	655	701	java/lang/Exception
      //   660	665	706	java/lang/Exception
      //   670	675	711	java/lang/Exception
      //   267	273	716	finally
      //   281	288	716	finally
      //   296	304	716	finally
      //   316	325	716	finally
      //   575	580	716	finally
      //   588	593	716	finally
      //   601	605	716	finally
      //   123	140	735	java/lang/Exception
      //   154	163	735	java/lang/Exception
      //   177	181	735	java/lang/Exception
      //   195	205	735	java/lang/Exception
      //   219	259	735	java/lang/Exception
    }
    
    protected void onPostExecute(String paramString)
    {
      if ((FingerWebWindow.this.progressDialog != null) && (FingerWebWindow.this.progressDialog.isShowing())) {
        FingerWebWindow.this.progressDialog.dismiss();
      }
      if ((FingerWebWindow.this.mMimeType.toLowerCase(Locale.getDefault()).contains("pass")) && (!FingerWebWindow.this.isPassWalletAppInstalled)) {
        Util.MessageBox(FingerWebWindow.this, "Unable to open.", "Please install PassWallet from Google Play to open Proof Of Insurance Card.", "OK");
      }
      FingerWebWindow.this.showReviewPromptDialog();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      FingerWebWindow.this.progressDialog.setProgressStyle(0);
      FingerWebWindow.this.progressDialog.setIndeterminate(true);
      ProgressDialog localProgressDialog = FingerWebWindow.this.progressDialog;
      if (FingerWebWindow.this.mMimeType.toLowerCase(Locale.getDefault()).contains("pass")) {}
      for (String str = "POI card";; str = "")
      {
        localProgressDialog.setMessage(String.format("Downloading %s...", new Object[] { str }));
        FingerWebWindow.this.progressDialog.setCancelable(false);
        FingerWebWindow.this.progressDialog.show();
        return;
      }
    }
    
    protected void onProgressUpdate(String... paramVarArgs) {}
  }
  
  final class MyWebChromeClient
    extends WebChromeClient
  {
    MyWebChromeClient() {}
    
    public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
      Log.d("LogTag", paramString2);
      paramJsResult.confirm();
      return true;
    }
  }
  
  public static class PlaceholderFragment
    extends Fragment
  {
    public PlaceholderFragment() {}
    
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      return paramLayoutInflater.inflate(2130903098, paramViewGroup, false);
    }
  }
  
  public class UserInput
  {
    public String pass;
    public String user;
    
    public UserInput() {}
    
    public String getPass()
    {
      return this.pass;
    }
    
    public String getUser()
    {
      return this.user;
    }
    
    @JavascriptInterface
    public void setPass(String paramString)
    {
      this.pass = paramString;
    }
    
    @JavascriptInterface
    public void setUser(String paramString)
    {
      this.user = paramString;
    }
  }
}
