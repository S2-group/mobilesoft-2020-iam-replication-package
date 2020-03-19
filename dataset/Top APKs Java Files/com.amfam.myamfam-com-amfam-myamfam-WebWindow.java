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
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class WebWindow
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
  
  public WebWindow() {}
  
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
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
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
    if (this.usesFingerprint.booleanValue())
    {
      if ((!this.isFirstLoad.booleanValue()) || (!paramString.contains("/MyAccount/home/"))) {
        break label41;
      }
      manualLoginSuccess(paramString);
    }
    label41:
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
        Toast.makeText(this, "test", 1).show();
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
    if ((Build.VERSION.SDK_INT >= 23) && (initCipher()))
    {
      this.mFragment = new FingerprintAuthenticationDialogFragment();
      this.mFragment.setCryptoObject(new FingerprintManager.CryptoObject(this.mCipher));
      if (!this.hasFingerPrint.booleanValue()) {
        break label78;
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
        label78:
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
              WebWindow.this.usesFingerprint = Boolean.valueOf(true);
              Util.getDeviceId(WebWindow.this.getApplication());
              Util.LogIntoSiteCatalyst("MyAmfam:TouchID_Yes");
              WebWindow.this.sharedPrefEditor.putString("user", WebWindow.this.encryptUtil(WebWindow.userInput.getUser()));
              WebWindow.this.sharedPrefEditor.putString("pass", WebWindow.this.encryptUtil(WebWindow.userInput.getPass()));
              WebWindow.this.sharedPrefEditor.putBoolean("usesFingerprint", true);
              WebWindow.this.sharedPrefEditor.putBoolean("firstTimeMyAccount", false);
              WebWindow.this.sharedPrefEditor.commit();
              paramAnonymousDialogInterface.dismiss();
            }
          });
          localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              WebWindow.this.usesFingerprint = Boolean.valueOf(false);
              Util.LogIntoSiteCatalyst("MyAmfam:TouchID_No");
              WebWindow.this.sharedPrefEditor.putBoolean("usesFingerprint", false);
              WebWindow.this.sharedPrefEditor.putBoolean("firstTimeMyAccount", false);
              WebWindow.this.sharedPrefEditor.commit();
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
                    break label559;
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
                    if (WebWindow.this.usesFingerprint.booleanValue()) {
                      WebWindow.this.loginPath(paramAnonymousString);
                    }
                    if (paramAnonymousString.contains("/customer/confirmMakePayment")) {
                      WebWindow.this.showReviewPromptDialog();
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
                    if ((WebWindow.this.usesFingerprint.booleanValue()) && (WebWindow.this.isFirstLoad.booleanValue()) && ((paramAnonymousString.equals("https://my.amfam.com/MyAccount/home/home.do")) || (paramAnonymousString.equals("https://my.amfam.com/MyAccount/login/login.do?poiLogin=true")))) {
                      WebWindow.this.getCredentials();
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
                      WebWindow.this.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(paramAnonymousString)));
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
                break label631;
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
            WebWindow.access$102(WebWindow.this, paramAnonymousString3);
            WebWindow.access$202(WebWindow.this, paramAnonymousString4);
            new WebWindow.DownloadFileAsync(WebWindow.this).execute(new String[] { paramAnonymousString1 });
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
      ActivityCompat.requestPermissions(this, new String[] { "android.permission.USE_FINGERPRINT", "android.permission.INTERNET" }, 100);
      this.hasFingerPrint = Boolean.valueOf(true);
      break;
      this.hasFingerPrint = Boolean.valueOf(true);
      fingerprintManager = (FingerprintManager)getApplicationContext().getSystemService("fingerprint");
      break;
      this.usesFingerprint = Boolean.valueOf(false);
      break;
      label559:
      userInput = new UserInput();
      userInput.setPass(decryptUtil(this.sharedPreferences.getString("pass", "")));
      userInput.setUser(decryptUtil(this.sharedPreferences.getString("user", "")));
      this.isFirstLoad = Boolean.valueOf(false);
      break label268;
      label631:
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
            WebWindow.this.launchReview();
            return;
          case 1: 
            Util.LogIntoSiteCatalyst("MyAmfam:Improvement");
            WebWindow.webView.loadUrl("http://web.amfam.com/customer/forms/contact_amfamform.asp");
            WebWindow.this.remindMe();
            return;
          case 2: 
            Util.LogIntoSiteCatalyst("MyAmfam:FeedbackNoThanks");
            WebWindow.this.neverAgain();
            return;
          }
          Util.LogIntoSiteCatalyst("MyAmfam:FeedbackRemindLater");
          WebWindow.this.remindMe();
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
        Iterator localIterator = WebWindow.this.getPackageManager().getInstalledApplications(128).iterator();
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
      //   13: getfield 16	com/amfam/myamfam/WebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/WebWindow;
      //   16: invokestatic 121	com/amfam/myamfam/WebWindow:access$100	(Lcom/amfam/myamfam/WebWindow;)Ljava/lang/String;
      //   19: ldc 123
      //   21: invokevirtual 73	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   24: ifeq +456 -> 480
      //   27: aload_0
      //   28: getfield 16	com/amfam/myamfam/WebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/WebWindow;
      //   31: invokestatic 121	com/amfam/myamfam/WebWindow:access$100	(Lcom/amfam/myamfam/WebWindow;)Ljava/lang/String;
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
      //   89: astore 13
      //   91: aconst_null
      //   92: astore 7
      //   94: aconst_null
      //   95: astore 9
      //   97: aconst_null
      //   98: astore 8
      //   100: aconst_null
      //   101: astore 10
      //   103: aconst_null
      //   104: astore 11
      //   106: aconst_null
      //   107: astore 12
      //   109: aconst_null
      //   110: astore 5
      //   112: aconst_null
      //   113: astore 4
      //   115: new 145	java/net/URL
      //   118: dup
      //   119: aload_1
      //   120: iconst_0
      //   121: aaload
      //   122: invokespecial 146	java/net/URL:<init>	(Ljava/lang/String;)V
      //   125: invokevirtual 150	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   128: checkcast 152	java/net/HttpURLConnection
      //   131: astore_1
      //   132: aload_1
      //   133: astore 4
      //   135: aload_1
      //   136: astore 5
      //   138: aload_1
      //   139: ldc -102
      //   141: getstatic 159	com/amfam/myamfam/MainActivity:webSessionCookie	Ljava/lang/String;
      //   144: invokevirtual 163	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   147: aload_1
      //   148: astore 4
      //   150: aload_1
      //   151: astore 5
      //   153: aload_1
      //   154: invokevirtual 166	java/net/HttpURLConnection:connect	()V
      //   157: aload_1
      //   158: astore 4
      //   160: aload_1
      //   161: astore 5
      //   163: aload_0
      //   164: getstatic 171	android/os/Environment:DIRECTORY_DOWNLOADS	Ljava/lang/String;
      //   167: invokestatic 175	android/os/Environment:getExternalStoragePublicDirectory	(Ljava/lang/String;)Ljava/io/File;
      //   170: putfield 82	com/amfam/myamfam/WebWindow$DownloadFileAsync:rootDir	Ljava/io/File;
      //   173: aload_1
      //   174: astore 4
      //   176: aload_1
      //   177: astore 5
      //   179: new 177	java/io/FileOutputStream
      //   182: dup
      //   183: new 77	java/io/File
      //   186: dup
      //   187: new 79	java/lang/StringBuilder
      //   190: dup
      //   191: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   194: aload_0
      //   195: getfield 82	com/amfam/myamfam/WebWindow$DownloadFileAsync:rootDir	Ljava/io/File;
      //   198: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   201: ldc -77
      //   203: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   206: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   209: aload 13
      //   211: invokespecial 181	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   214: invokespecial 184	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   217: astore 6
      //   219: aload 12
      //   221: astore 5
      //   223: aload 11
      //   225: astore 4
      //   227: aload_1
      //   228: invokevirtual 188	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   231: astore 7
      //   233: aload 7
      //   235: astore 5
      //   237: aload 7
      //   239: astore 4
      //   241: sipush 1024
      //   244: newarray byte
      //   246: astore 8
      //   248: aload 7
      //   250: astore 5
      //   252: aload 7
      //   254: astore 4
      //   256: aload 7
      //   258: aload 8
      //   260: invokevirtual 194	java/io/InputStream:read	([B)I
      //   263: istore_2
      //   264: iload_2
      //   265: ifle +243 -> 508
      //   268: aload 7
      //   270: astore 5
      //   272: aload 7
      //   274: astore 4
      //   276: aload 6
      //   278: aload 8
      //   280: iconst_0
      //   281: iload_2
      //   282: invokevirtual 198	java/io/FileOutputStream:write	([BII)V
      //   285: goto -37 -> 248
      //   288: astore 4
      //   290: aload 6
      //   292: astore 4
      //   294: aload_1
      //   295: ifnull +7 -> 302
      //   298: aload_1
      //   299: invokevirtual 201	java/net/HttpURLConnection:disconnect	()V
      //   302: aload 4
      //   304: ifnull +8 -> 312
      //   307: aload 4
      //   309: invokevirtual 204	java/io/FileOutputStream:close	()V
      //   312: aload 5
      //   314: ifnull +8 -> 322
      //   317: aload 5
      //   319: invokevirtual 205	java/io/InputStream:close	()V
      //   322: iconst_1
      //   323: istore_3
      //   324: iload_3
      //   325: istore_2
      //   326: aload_0
      //   327: getfield 16	com/amfam/myamfam/WebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/WebWindow;
      //   330: invokestatic 208	com/amfam/myamfam/WebWindow:access$200	(Lcom/amfam/myamfam/WebWindow;)Ljava/lang/String;
      //   333: invokestatic 61	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   336: invokevirtual 67	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
      //   339: ldc -46
      //   341: invokevirtual 73	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   344: ifne +52 -> 396
      //   347: aload_0
      //   348: getfield 16	com/amfam/myamfam/WebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/WebWindow;
      //   351: aload_0
      //   352: invokespecial 212	com/amfam/myamfam/WebWindow$DownloadFileAsync:isPassWalletAppInstalled	()Z
      //   355: invokestatic 216	com/amfam/myamfam/WebWindow:access$402	(Lcom/amfam/myamfam/WebWindow;Z)Z
      //   358: pop
      //   359: iload_3
      //   360: istore_2
      //   361: aload_0
      //   362: getfield 16	com/amfam/myamfam/WebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/WebWindow;
      //   365: invokestatic 208	com/amfam/myamfam/WebWindow:access$200	(Lcom/amfam/myamfam/WebWindow;)Ljava/lang/String;
      //   368: invokestatic 61	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   371: invokevirtual 67	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
      //   374: ldc -38
      //   376: invokevirtual 73	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   379: ifeq +17 -> 396
      //   382: iload_3
      //   383: istore_2
      //   384: aload_0
      //   385: getfield 16	com/amfam/myamfam/WebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/WebWindow;
      //   388: invokestatic 222	com/amfam/myamfam/WebWindow:access$400	(Lcom/amfam/myamfam/WebWindow;)Z
      //   391: ifne +5 -> 396
      //   394: iconst_0
      //   395: istore_2
      //   396: iload_2
      //   397: ifeq +298 -> 695
      //   400: ldc -32
      //   402: iconst_1
      //   403: anewarray 139	java/lang/Object
      //   406: dup
      //   407: iconst_0
      //   408: new 79	java/lang/StringBuilder
      //   411: dup
      //   412: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   415: aload_0
      //   416: getfield 82	com/amfam/myamfam/WebWindow$DownloadFileAsync:rootDir	Ljava/io/File;
      //   419: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   422: ldc -77
      //   424: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   427: aload 13
      //   429: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   432: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   435: aastore
      //   436: invokestatic 143	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   439: astore_1
      //   440: new 226	android/content/Intent
      //   443: dup
      //   444: ldc -28
      //   446: invokespecial 229	android/content/Intent:<init>	(Ljava/lang/String;)V
      //   449: astore 4
      //   451: aload 4
      //   453: aload_1
      //   454: invokestatic 235	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
      //   457: aload_0
      //   458: getfield 16	com/amfam/myamfam/WebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/WebWindow;
      //   461: invokestatic 208	com/amfam/myamfam/WebWindow:access$200	(Lcom/amfam/myamfam/WebWindow;)Ljava/lang/String;
      //   464: invokevirtual 239	android/content/Intent:setDataAndType	(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;
      //   467: pop
      //   468: aload_0
      //   469: getfield 16	com/amfam/myamfam/WebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/WebWindow;
      //   472: aload 4
      //   474: invokevirtual 243	com/amfam/myamfam/WebWindow:startActivity	(Landroid/content/Intent;)V
      //   477: goto +218 -> 695
      //   480: aload_0
      //   481: getfield 16	com/amfam/myamfam/WebWindow$DownloadFileAsync:this$0	Lcom/amfam/myamfam/WebWindow;
      //   484: invokestatic 208	com/amfam/myamfam/WebWindow:access$200	(Lcom/amfam/myamfam/WebWindow;)Ljava/lang/String;
      //   487: invokestatic 61	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   490: invokevirtual 67	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
      //   493: ldc -46
      //   495: invokevirtual 73	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   498: ifeq -428 -> 70
      //   501: ldc -11
      //   503: astore 4
      //   505: goto -435 -> 70
      //   508: aload 7
      //   510: astore 5
      //   512: aload 7
      //   514: astore 4
      //   516: aload 6
      //   518: invokevirtual 204	java/io/FileOutputStream:close	()V
      //   521: aload 7
      //   523: astore 5
      //   525: aload 7
      //   527: astore 4
      //   529: aload 7
      //   531: invokevirtual 205	java/io/InputStream:close	()V
      //   534: aload 7
      //   536: astore 5
      //   538: aload 7
      //   540: astore 4
      //   542: aload_1
      //   543: invokevirtual 201	java/net/HttpURLConnection:disconnect	()V
      //   546: aload_1
      //   547: ifnull +7 -> 554
      //   550: aload_1
      //   551: invokevirtual 201	java/net/HttpURLConnection:disconnect	()V
      //   554: aload 6
      //   556: ifnull +8 -> 564
      //   559: aload 6
      //   561: invokevirtual 204	java/io/FileOutputStream:close	()V
      //   564: aload 7
      //   566: ifnull +8 -> 574
      //   569: aload 7
      //   571: invokevirtual 205	java/io/InputStream:close	()V
      //   574: goto -252 -> 322
      //   577: astore_1
      //   578: goto -256 -> 322
      //   581: astore_1
      //   582: aload 10
      //   584: astore 5
      //   586: aload 7
      //   588: astore 6
      //   590: aload 4
      //   592: astore 7
      //   594: aload 7
      //   596: ifnull +8 -> 604
      //   599: aload 7
      //   601: invokevirtual 201	java/net/HttpURLConnection:disconnect	()V
      //   604: aload 6
      //   606: ifnull +8 -> 614
      //   609: aload 6
      //   611: invokevirtual 204	java/io/FileOutputStream:close	()V
      //   614: aload 5
      //   616: ifnull +8 -> 624
      //   619: aload 5
      //   621: invokevirtual 205	java/io/InputStream:close	()V
      //   624: aload_1
      //   625: athrow
      //   626: astore_1
      //   627: goto +68 -> 695
      //   630: astore_1
      //   631: goto -77 -> 554
      //   634: astore_1
      //   635: goto -71 -> 564
      //   638: astore_1
      //   639: goto -337 -> 302
      //   642: astore_1
      //   643: goto -331 -> 312
      //   646: astore_1
      //   647: goto -325 -> 322
      //   650: astore 4
      //   652: goto -48 -> 604
      //   655: astore 4
      //   657: goto -43 -> 614
      //   660: astore 4
      //   662: goto -38 -> 624
      //   665: astore 8
      //   667: aload_1
      //   668: astore 7
      //   670: aload 4
      //   672: astore 5
      //   674: aload 8
      //   676: astore_1
      //   677: goto -83 -> 594
      //   680: astore_1
      //   681: aload 5
      //   683: astore_1
      //   684: aload 9
      //   686: astore 4
      //   688: aload 8
      //   690: astore 5
      //   692: goto -398 -> 294
      //   695: aconst_null
      //   696: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	697	0	this	DownloadFileAsync
      //   0	697	1	paramVarArgs	String[]
      //   263	134	2	i	int
      //   323	60	3	j	int
      //   2	273	4	localObject1	Object
      //   288	1	4	localException1	Exception
      //   292	299	4	localObject2	Object
      //   650	1	4	localException2	Exception
      //   655	1	4	localException3	Exception
      //   660	11	4	localException4	Exception
      //   686	1	4	localObject3	Object
      //   10	681	5	localObject4	Object
      //   217	393	6	localObject5	Object
      //   92	577	7	localObject6	Object
      //   98	181	8	arrayOfByte	byte[]
      //   665	24	8	localObject7	Object
      //   95	590	9	localObject8	Object
      //   101	482	10	localObject9	Object
      //   104	120	11	localObject10	Object
      //   107	113	12	localObject11	Object
      //   89	339	13	str	String
      // Exception table:
      //   from	to	target	type
      //   227	233	288	java/lang/Exception
      //   241	248	288	java/lang/Exception
      //   256	264	288	java/lang/Exception
      //   276	285	288	java/lang/Exception
      //   516	521	288	java/lang/Exception
      //   529	534	288	java/lang/Exception
      //   542	546	288	java/lang/Exception
      //   569	574	577	java/lang/Exception
      //   115	132	581	finally
      //   138	147	581	finally
      //   153	157	581	finally
      //   163	173	581	finally
      //   179	219	581	finally
      //   4	70	626	java/lang/Exception
      //   70	91	626	java/lang/Exception
      //   326	359	626	java/lang/Exception
      //   361	382	626	java/lang/Exception
      //   384	394	626	java/lang/Exception
      //   400	477	626	java/lang/Exception
      //   480	501	626	java/lang/Exception
      //   624	626	626	java/lang/Exception
      //   550	554	630	java/lang/Exception
      //   559	564	634	java/lang/Exception
      //   298	302	638	java/lang/Exception
      //   307	312	642	java/lang/Exception
      //   317	322	646	java/lang/Exception
      //   599	604	650	java/lang/Exception
      //   609	614	655	java/lang/Exception
      //   619	624	660	java/lang/Exception
      //   227	233	665	finally
      //   241	248	665	finally
      //   256	264	665	finally
      //   276	285	665	finally
      //   516	521	665	finally
      //   529	534	665	finally
      //   542	546	665	finally
      //   115	132	680	java/lang/Exception
      //   138	147	680	java/lang/Exception
      //   153	157	680	java/lang/Exception
      //   163	173	680	java/lang/Exception
      //   179	219	680	java/lang/Exception
    }
    
    protected void onPostExecute(String paramString)
    {
      if ((WebWindow.this.progressDialog != null) && (WebWindow.this.progressDialog.isShowing())) {
        WebWindow.this.progressDialog.dismiss();
      }
      if ((WebWindow.this.mMimeType.toLowerCase(Locale.getDefault()).contains("pass")) && (!WebWindow.this.isPassWalletAppInstalled)) {
        Util.MessageBox(WebWindow.this, "Unable to open.", "Please install PassWallet from Google Play to open Proof Of Insurance Card.", "OK");
      }
      WebWindow.this.showReviewPromptDialog();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      WebWindow.this.progressDialog.setProgressStyle(0);
      WebWindow.this.progressDialog.setIndeterminate(true);
      ProgressDialog localProgressDialog = WebWindow.this.progressDialog;
      if (WebWindow.this.mMimeType.toLowerCase(Locale.getDefault()).contains("pass")) {}
      for (String str = "POI card";; str = "")
      {
        localProgressDialog.setMessage(String.format("Downloading %s...", new Object[] { str }));
        WebWindow.this.progressDialog.setCancelable(false);
        WebWindow.this.progressDialog.show();
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
