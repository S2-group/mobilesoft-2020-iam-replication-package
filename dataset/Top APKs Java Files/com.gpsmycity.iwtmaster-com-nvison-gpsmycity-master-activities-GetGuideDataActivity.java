package com.nvison.gpsmycity.master.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.UtteranceProgressListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.nvison.gpsmycity.data.DatabaseManager;
import com.nvison.gpsmycity.data.SessionManager;
import com.nvison.gpsmycity.master.common.AppConstants;
import com.nvison.gpsmycity.master.common.FileUtils;
import com.nvison.gpsmycity.master.common.FileUtils.DownloadListner;
import com.nvison.gpsmycity.master.map.MapCreate;
import com.nvison.gpsmycity.master.objects.BaseDo;
import com.nvison.gpsmycity.master.objects.GuideDataDo;
import com.nvison.gpsmycity.master.objects.GuidsDo;
import com.nvison.gpsmycity.master.utils.KeyValue;
import com.nvison.gpsmycity.master.utils.PreferenceUtils;
import com.nvison.gpsmycity.master.utils.SharedPrefUtils;
import com.nvison.gpsmycity.util.Utils;
import com.nvison.gpsmycity.web.WebManager;
import com.skobbler.ngx.SKDeveloperKeyException;
import com.skobbler.ngx.SKMaps;
import com.skobbler.ngx.SKMapsInitSettings;
import com.skobbler.ngx.map.SKMapViewStyle;
import com.skobbler.ngx.navigation.SKAdvisorSettings;
import com.skobbler.ngx.navigation.SKAdvisorSettings.SKAdvisorLanguage;
import com.skobbler.ngx.packages.SKPackage;
import com.skobbler.ngx.packages.SKPackageManager;
import com.skobbler.ngx.util.SKLogging;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class GetGuideDataActivity
  extends Activity
  implements TextToSpeech.OnInitListener, FileUtils.DownloadListner
{
  private static final String TAG = "Guide Frag";
  File Comfile = null;
  File FirstFile = null;
  private String GuideId;
  private ProgressDialog _dialog;
  private String applicationPath;
  public int callCount = 0;
  private SaveCustomDialog dialog;
  private Dialog dialogDownload;
  private TextView dialogMessageTextView;
  private File dir;
  private Document document;
  private String fileName;
  private Uri fileUri;
  public FragmentManager fm;
  private TravelArticleFragment frag;
  public GuideDataDo guide;
  public GuidsDo guideDo;
  private String html;
  private String htmlLocation;
  private String htmlText;
  private boolean isCatalog;
  private boolean isRedirected = false;
  private ImageView ivback;
  public FrameLayout llfooter;
  private String mapResourcesDir;
  public ProgressBar pbLoad;
  private PreferenceUtils preferenceUtils;
  private ProgressBar progressBar;
  public LinearLayout rlback;
  private SessionManager sessionmanger;
  String string = "";
  private String substring = "";
  private int substringlen = 1000;
  private int substringlenStart = 0;
  private String text;
  private TextToSpeech tts;
  private TextView tvProgress;
  private TextView tvsave;
  private WebView webview;
  
  public GetGuideDataActivity() {}
  
  private boolean checkOfflinePath()
  {
    new Gson();
    String str = SharedPrefUtils.getKeyValue(this, SharedPrefUtils.TRAVEL_ARTICLE, this.GuideId, "");
    this.guide = ((GuideDataDo)new Gson().fromJson(str, GuideDataDo.class));
    return this.guide != null;
  }
  
  private void deleteDir()
  {
    File[] arrayOfFile;
    int j;
    int i;
    if (this.dir.isDirectory())
    {
      arrayOfFile = this.dir.listFiles();
      j = arrayOfFile.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j)
      {
        this.dir.delete();
        return;
      }
      arrayOfFile[i].delete();
      i += 1;
    }
  }
  
  private void downloadSkm()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Object localObject = GetGuideDataActivity.this.guide.getskmfile();
        int i = GetGuideDataActivity.this.guide.getid();
        final String str = "SKM" + String.valueOf(i);
        GetGuideDataActivity.this.initializeLibrary(GetGuideDataActivity.this);
        SKPackage[] arrayOfSKPackage = SKPackageManager.getInstance().getInstalledPackages();
        int j = 0;
        i = 0;
        for (;;)
        {
          if (i >= arrayOfSKPackage.length) {}
          for (i = j;; i = 1)
          {
            if (i != 0) {
              break label165;
            }
            GetGuideDataActivity.this.showDownloadProgressBar();
            if (!GetGuideDataActivity.this.downloadskm((String)localObject, str, GetGuideDataActivity.this)) {
              break label149;
            }
            GetGuideDataActivity.this.runOnUiThread(new Runnable()
            {
              private void changeAppVersion() {}
              
              public void run()
              {
                SKPackageManager.getInstance().addOfflinePackage(AppConstants.DATABASE_PATH + "Travelarticles/", str);
                changeAppVersion();
                Object localObject = new KeyValue(GetGuideDataActivity.this.guide.getid(), "true");
                SharedPrefUtils.setValue(GetGuideDataActivity.this, SharedPrefUtils.TRAVEL_ARTICLE_PROMO, (KeyValue)localObject);
                localObject = new Intent(GetGuideDataActivity.this, MapCreate.class);
                ((Intent)localObject).putExtra("pageNo", false);
                ((Intent)localObject).putExtra("isfrommaster", false);
                ((Intent)localObject).putExtra("guide", GetGuideDataActivity.this.guide);
                ((Intent)localObject).putExtra("sightid", GetGuideDataActivity.this.string);
                ((Intent)localObject).putExtra("guidesdo", GetGuideDataActivity.this.guideDo);
                GetGuideDataActivity.this.startActivity((Intent)localObject);
              }
            });
            return;
            if (!str.equalsIgnoreCase(arrayOfSKPackage[i].getName())) {
              break;
            }
          }
          i += 1;
        }
        label149:
        GetGuideDataActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            Utils.showBasicOkDialog(null, "No Internet connection. Map is not available, please connect to the Internet to download the map", null, "Ok", GetGuideDataActivity.this);
          }
        });
        return;
        label165:
        localObject = new Intent(GetGuideDataActivity.this, MapCreate.class);
        ((Intent)localObject).putExtra("pageNo", false);
        ((Intent)localObject).putExtra("isfrommaster", false);
        ((Intent)localObject).putExtra("guide", GetGuideDataActivity.this.guide);
        ((Intent)localObject).putExtra("sightid", GetGuideDataActivity.this.string);
        ((Intent)localObject).putExtra("guidesdo", GetGuideDataActivity.this.guideDo);
        GetGuideDataActivity.this.startActivity((Intent)localObject);
      }
    }).start();
  }
  
  private void downloadSkmfile()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        String str1 = GetGuideDataActivity.this.guide.getskmfile();
        int i = GetGuideDataActivity.this.guide.getid();
        final String str2 = "SKM" + String.valueOf(i);
        GetGuideDataActivity.this.initializeLibrary(GetGuideDataActivity.this);
        SKPackage[] arrayOfSKPackage = SKPackageManager.getInstance().getInstalledPackages();
        int j = 0;
        i = 0;
        for (;;)
        {
          if (i >= arrayOfSKPackage.length) {}
          for (i = j;; i = 1)
          {
            if (i == 0)
            {
              GetGuideDataActivity.this.showDownloadProgressBar();
              if (!GetGuideDataActivity.this.downloadskm(str1, str2, GetGuideDataActivity.this)) {
                break label149;
              }
              GetGuideDataActivity.this.runOnUiThread(new Runnable()
              {
                private void changeAppVersion() {}
                
                public void run()
                {
                  SKPackageManager.getInstance().addOfflinePackage(AppConstants.DATABASE_PATH + "Travelarticles/", str2);
                  changeAppVersion();
                  KeyValue localKeyValue = new KeyValue(GetGuideDataActivity.this.guide.getid(), "true");
                  SharedPrefUtils.setValue(GetGuideDataActivity.this, SharedPrefUtils.TRAVEL_ARTICLE_PROMO, localKeyValue);
                }
              });
            }
            return;
            if (!str2.equalsIgnoreCase(arrayOfSKPackage[i].getName())) {
              break;
            }
          }
          i += 1;
        }
        label149:
        GetGuideDataActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            Utils.showBasicOkDialog(null, "No Internet connection. Map is not available, please connect to the Internet to download the map", null, "Ok", GetGuideDataActivity.this);
          }
        });
      }
    }).start();
  }
  
  private void goToNextActivity()
  {
    this.frag = new TravelArticleFragment(this, this.guide, this.guideDo);
    this.frag.setGuide(this.guide);
    FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
    localFragmentTransaction.add(2131492890, this.frag);
    localFragmentTransaction.commit();
  }
  
  private void loadData()
  {
    this.html = this.guide.gethtml();
    this.html = this.html.replace("Go There", "");
    this.webview.getSettings().setJavaScriptEnabled(false);
    this.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    this.webview.loadDataWithBaseURL(null, this.guide.gethtml(), "text/html", "utf-8", null);
    goToNextActivity();
  }
  
  private void showDialogForRecordingAudio()
  {
    this._dialog.show();
    this._dialog.setContentView(2130903111);
    this.dialogMessageTextView = ((TextView)this._dialog.findViewById(2131493237));
    this.dialogMessageTextView.setText("Preparing for audio file, please wait.");
    this.tts = new TextToSpeech(this, this);
    this.tts.setOnUtteranceProgressListener(new ttsUtteranceListener());
  }
  
  private void showDownloadProgressBar()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        View localView = LayoutInflater.from(GetGuideDataActivity.this).inflate(2130903173, null);
        GetGuideDataActivity.this.progressBar = ((ProgressBar)localView.findViewById(2131493286));
        GetGuideDataActivity.this.tvProgress = ((TextView)localView.findViewById(2131493279));
        if (GetGuideDataActivity.this.dialogDownload == null)
        {
          GetGuideDataActivity.this.dialogDownload = new Dialog(GetGuideDataActivity.this);
          GetGuideDataActivity.this.dialogDownload.getWindow().setBackgroundDrawable(new ColorDrawable(0));
          GetGuideDataActivity.this.dialogDownload.requestWindowFeature(1);
          GetGuideDataActivity.this.dialogDownload.setCancelable(false);
        }
        GetGuideDataActivity.this.dialogDownload.setContentView(localView, new ViewGroup.LayoutParams(700, -2));
        GetGuideDataActivity.this.dialogDownload.getWindow().setGravity(17);
        GetGuideDataActivity.this.progressBar.setMax(100);
        GetGuideDataActivity.this.progressBar.setProgress(0);
        GetGuideDataActivity.this.tvProgress.setText("0 %");
        GetGuideDataActivity.this.dialogDownload.show();
      }
    });
  }
  
  @SuppressLint({"NewApi"})
  private void speakText(String paramString)
  {
    String str = Environment.getExternalStorageState();
    if ("mounted".equals(str)) {}
    int i;
    for (;;)
    {
      this.dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/downloading");
      if (!this.dir.exists()) {
        this.dir.mkdirs();
      }
      this.FirstFile = new File(this.dir, AppConstants.NameOfFile + 0 + "a.mp3");
      AppConstants.fileNames = new ArrayList();
      i = 0;
      if (i < AppConstants.NumOfFiles + 1) {
        break;
      }
      return;
      if ("mounted_ro".equals(str)) {}
    }
    if (i == AppConstants.NumOfFiles) {}
    for (this.substring = paramString.substring(this.substringlenStart, paramString.length());; this.substring = paramString.substring(this.substringlenStart, this.substringlen))
    {
      this.substringlenStart = this.substringlen;
      this.substringlen += 1000;
      AppConstants.fileNames.add(AppConstants.NameOfFile + i + "a.mp3");
      this.Comfile = new File(this.dir, AppConstants.NameOfFile + i + "a.mp3");
      this.tts.synthesizeToFile(this.substring, null, this.Comfile, "tts");
      i += 1;
      break;
    }
  }
  
  public boolean downloadskm(String paramString1, String paramString2, FileUtils.DownloadListner paramDownloadListner)
  {
    if ((new File(AppConstants.DATABASE_PATH, paramString2).exists()) || (!TextUtils.isEmpty(FileUtils.downloadSQLITE(paramString1, AppConstants.DATABASE_PATH, this, paramString2, paramDownloadListner)))) {
      return true;
    }
    if (paramDownloadListner != null) {
      paramDownloadListner.onError();
    }
    return false;
  }
  
  public boolean initializeLibrary(Context paramContext)
  {
    SKLogging.enableLogs(true);
    this.mapResourcesDir = new PreferenceUtils(paramContext).getmapSettingsPath(PreferenceUtils.mapSettingsPath, "");
    if (this.mapResourcesDir.equals("")) {
      this.mapResourcesDir = (this.mapResourcesDir + File.separator + "PreinstalledMaps" + File.separator + "v1" + File.separator + "20150413" + File.separator + "package");
    }
    SKMapsInitSettings localSKMapsInitSettings = new SKMapsInitSettings();
    localSKMapsInitSettings.setMapResourcesPaths(this.mapResourcesDir, new SKMapViewStyle(this.mapResourcesDir + "daystyle/", "daystyle.json"));
    SKAdvisorSettings localSKAdvisorSettings = localSKMapsInitSettings.getAdvisorSettings();
    localSKAdvisorSettings.setAdvisorConfigPath(this.mapResourcesDir + "/Advisor");
    localSKAdvisorSettings.setResourcePath(this.mapResourcesDir + "/Advisor/Languages");
    localSKAdvisorSettings.setLanguage(SKAdvisorSettings.SKAdvisorLanguage.LANGUAGE_EN);
    localSKAdvisorSettings.setAdvisorVoice("en");
    localSKMapsInitSettings.setAdvisorSettings(localSKAdvisorSettings);
    localSKMapsInitSettings.setPreinstalledMapsPath(this.mapResourcesDir + "/PreinstalledMaps");
    localSKMapsInitSettings.setConnectivityMode(2);
    localSKMapsInitSettings.setMapDetailLevel("full/");
    try
    {
      SKMaps.getInstance().initializeSKMaps(paramContext, localSKMapsInitSettings);
      System.out.println("");
      return true;
    }
    catch (SKDeveloperKeyException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public boolean isUpgraded()
  {
    this.guide.getskmfile();
    int i = this.guide.getid();
    String str = "SKM" + String.valueOf(i);
    initializeLibrary(this);
    SKPackage[] arrayOfSKPackage = SKPackageManager.getInstance().getInstalledPackages();
    i = 0;
    for (;;)
    {
      if (i >= arrayOfSKPackage.length) {
        return false;
      }
      if (str.equalsIgnoreCase(arrayOfSKPackage[i].getName())) {
        return true;
      }
      i += 1;
    }
  }
  
  @SuppressLint({"NewApi"})
  public void loadfiles()
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
        showDialogForRecordingAudio();
      }
    }
    else {
      return;
    }
    requestPermissions(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 2);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 1) && (paramInt2 == 1)) {
      ((TextView)this.frag.getUpgradebtn()).setText("Map");
    }
  }
  
  public void onBackPressed()
  {
    if ((getFragmentManager().findFragmentById(2131492890) instanceof ArticlelistenFragment2))
    {
      AppConstants.isClosed = false;
      this.fm.popBackStack();
      return;
    }
    if (this.dir != null) {
      deleteDir();
    }
    AppConstants.fileNames = null;
    AppConstants.isDone = false;
    AppConstants.isClosed = true;
    if (this.tts != null)
    {
      this.tts.stop();
      this.tts.shutdown();
    }
    super.onBackPressed();
  }
  
  public void onComplete()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          if ((!GetGuideDataActivity.this.isFinishing()) && (GetGuideDataActivity.this.dialogDownload != null) && (GetGuideDataActivity.this.dialogDownload.isShowing())) {
            GetGuideDataActivity.this.dialogDownload.dismiss();
          }
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903058);
    this.ivback = ((ImageView)findViewById(2131492877));
    this.tvsave = ((TextView)findViewById(2131492964));
    this.GuideId = getIntent().getExtras().getString("id");
    this.guideDo = ((GuidsDo)getIntent().getSerializableExtra("guidedo"));
    this.rlback = ((LinearLayout)findViewById(2131492963));
    this.isCatalog = DatabaseManager.getInstance().checkarticle(this.GuideId);
    this.tvsave.setTypeface(AppConstants.Roboto_Medium);
    if (this.isCatalog)
    {
      this.tvsave.setEnabled(false);
      this.tvsave.setTextColor(getResources().getColor(2131296306));
      this.preferenceUtils = new PreferenceUtils(this);
      this.llfooter = ((FrameLayout)findViewById(2131492890));
      this.webview = ((WebView)findViewById(2131492965));
      this.sessionmanger = new SessionManager(this);
      this.fm = getFragmentManager();
      this.applicationPath = Utils.chooseStoragePath(this);
      this.htmlLocation = (this.applicationPath + "/" + "ArticleHtmlPath");
      this.fileName = this.GuideId;
      this.webview.setWebViewClient(new WebViewClient()
      {
        public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
        {
          super.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
        }
        
        public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          Log.d("url", paramAnonymousString);
          super.shouldOverrideUrlLoading(paramAnonymousWebView, paramAnonymousString);
          if ((paramAnonymousString != null) && (paramAnonymousString.length() >= 20)) {
            GetGuideDataActivity.this.string = paramAnonymousString.substring(0, 20);
          }
          if (GetGuideDataActivity.this.string != null)
          {
            if (GetGuideDataActivity.this.string.equals("http://app/#owner-id"))
            {
              paramAnonymousWebView = paramAnonymousString.split("-");
              paramAnonymousWebView = paramAnonymousWebView[(paramAnonymousWebView.length - 1)];
              paramAnonymousString = new Intent(GetGuideDataActivity.this, AuthorProfileActivity.class);
              paramAnonymousString.putExtra("ownerid", paramAnonymousWebView);
              GetGuideDataActivity.this.startActivity(paramAnonymousString);
            }
          }
          else {
            return true;
          }
          if (GetGuideDataActivity.this.string.contains("http://app/#sight-id"))
          {
            GetGuideDataActivity.this.string = paramAnonymousString.split("sight-id-")[1];
            if (GetGuideDataActivity.this.preferenceUtils.getUserPremium(PreferenceUtils.user_premium, 0) == 1)
            {
              GetGuideDataActivity.this.downloadSkm();
              return true;
            }
            if ((GetGuideDataActivity.this.preferenceUtils.getUserPremium(PreferenceUtils.user_premium, 0) == 0) && (!GetGuideDataActivity.this.isUpgraded()))
            {
              paramAnonymousWebView = new Intent(GetGuideDataActivity.this, UpgradActivity.class);
              paramAnonymousWebView.putExtra("guide", GetGuideDataActivity.this.guide);
              GetGuideDataActivity.this.startActivityForResult(paramAnonymousWebView, 1);
              return true;
            }
            paramAnonymousWebView = new Intent(GetGuideDataActivity.this, MapCreate.class);
            paramAnonymousWebView.putExtra("pageNo", false);
            paramAnonymousWebView.putExtra("isfrommaster", false);
            paramAnonymousWebView.putExtra("guide", GetGuideDataActivity.this.guide);
            paramAnonymousWebView.putExtra("sightid", GetGuideDataActivity.this.string);
            paramAnonymousWebView.putExtra("guidesdo", GetGuideDataActivity.this.guideDo);
            GetGuideDataActivity.this.startActivity(paramAnonymousWebView);
            return true;
          }
          try
          {
            paramAnonymousWebView = new Intent("android.intent.action.MAIN");
            paramAnonymousWebView.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
            paramAnonymousWebView.addCategory("android.intent.category.LAUNCHER");
            paramAnonymousWebView.setData(Uri.parse(paramAnonymousString));
            GetGuideDataActivity.this.startActivity(paramAnonymousWebView);
            return true;
          }
          catch (ActivityNotFoundException paramAnonymousWebView)
          {
            paramAnonymousWebView = new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousString));
            GetGuideDataActivity.this.startActivity(paramAnonymousWebView);
          }
          return true;
        }
      });
      if (!checkOfflinePath()) {
        break label378;
      }
      loadData();
    }
    for (;;)
    {
      this.ivback.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!GetGuideDataActivity.this._dialog.isShowing())
          {
            if (GetGuideDataActivity.this.dir != null) {
              GetGuideDataActivity.this.deleteDir();
            }
            AppConstants.fileNames = null;
            AppConstants.isDone = false;
            AppConstants.isClosed = true;
            GetGuideDataActivity.this.finish();
          }
        }
      });
      this.tvsave.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (DatabaseManager.getInstance().saveArticle(GetGuideDataActivity.this.guideDo, GetGuideDataActivity.this.guide))
          {
            GetGuideDataActivity.this.tvsave.setTextColor(GetGuideDataActivity.this.getResources().getColor(2131296306));
            GetGuideDataActivity.this.tvsave.setOnClickListener(null);
            GetGuideDataActivity.this.showcustomdialog("This article is now saved under \"Downloads\" for offline reading.", 2130903108);
            paramAnonymousView = new Gson().toJson(GetGuideDataActivity.this.guide);
            paramAnonymousView = new KeyValue(GetGuideDataActivity.this.GuideId, paramAnonymousView);
            SharedPrefUtils.setValue(GetGuideDataActivity.this, SharedPrefUtils.TRAVEL_ARTICLE, paramAnonymousView);
          }
        }
      });
      this._dialog = new ProgressDialog(this);
      this._dialog.setOnKeyListener(new DialogInterface.OnKeyListener()
      {
        public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          if ((paramAnonymousInt == 4) && (!paramAnonymousKeyEvent.isCanceled()) && (GetGuideDataActivity.this._dialog.isShowing()))
          {
            if (GetGuideDataActivity.this.dir != null) {
              GetGuideDataActivity.this.deleteDir();
            }
            GetGuideDataActivity.this.substringlenStart = 0;
            GetGuideDataActivity.this.substringlen = 1000;
            GetGuideDataActivity.this.tts.stop();
          }
          return false;
        }
      });
      return;
      this.tvsave.setEnabled(true);
      this.tvsave.setTextColor(getResources().getColor(2131296308));
      break;
      label378:
      if (Utils.isNetworkAvailable(this))
      {
        if (new SessionManager(this).getPersistSid().equals("")) {
          WebManager.getInstance().getauthenticate(this, null, 1, true);
        } else {
          WebManager.getInstance().get_guide_data(this, null, 21, this.GuideId);
        }
      }
      else {
        Toast.makeText(this, "Internet connection not available", 0).show();
      }
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onError()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          if ((!GetGuideDataActivity.this.isFinishing()) && (GetGuideDataActivity.this.dialogDownload != null) && (GetGuideDataActivity.this.dialogDownload.isShowing())) {
            GetGuideDataActivity.this.dialogDownload.dismiss();
          }
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }
  
  public void onInit(int paramInt)
  {
    if (paramInt == 0)
    {
      Log.d("Guide Frag", "Initilization : ");
      paramInt = this.tts.setLanguage(Locale.US);
      if ((paramInt == -1) || (paramInt == -2))
      {
        Log.e("TTS", "This Language is not supported");
        return;
      }
      if (Utils.isNetworkAvailable(this))
      {
        new LoadHtmlString(this, this.html).execute(new String[] { this.html });
        return;
      }
      new LoadHtmlString(this, this.html).execute(new String[] { this.html });
      return;
    }
    Log.e("TTS", "Initilization Failed!");
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  public void onProgrss(final int paramInt)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (GetGuideDataActivity.this.dialogDownload != null)
        {
          GetGuideDataActivity.this.progressBar.setProgress(paramInt);
          GetGuideDataActivity.this.tvProgress.setText(paramInt + " %");
        }
      }
    });
  }
  
  @SuppressLint({"NewApi"})
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    if ((paramInt == 2) && (paramArrayOfInt[0] == 0)) {
      showDialogForRecordingAudio();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public <T> void processResponse(String paramString, int paramInt)
  {
    if (paramInt == 1)
    {
      paramString = (BaseDo)new Gson().fromJson(paramString, BaseDo.class);
      if (!paramString.getSid().equals("")) {}
    }
    for (;;)
    {
      return;
      this.sessionmanger.persistSid(paramString.getSid());
      WebManager.getInstance().get_guide_data(this, null, 21, this.GuideId);
      return;
      if ((paramInt == 21) && (paramString != null) && (!paramString.equals(""))) {
        if (!paramString.equals("!auth or expired sid")) {
          try
          {
            Log.d("Guide Frag", "processResponse : ");
            this.guide = ((GuideDataDo)new Gson().fromJson(paramString, GuideDataDo.class));
            loadData();
            if ((getIntent().getExtras().getBoolean("isFromDownloads")) && (this.preferenceUtils.getUserPremium(PreferenceUtils.user_premium, 0) == 1))
            {
              downloadSkmfile();
              return;
            }
          }
          catch (Exception paramString)
          {
            paramString.printStackTrace();
            return;
          }
        }
      }
    }
    WebManager.getInstance().getauthenticate(this, null, 1, true);
  }
  
  public void showViews()
  {
    if (this._dialog.isShowing()) {
      this._dialog.dismiss();
    }
  }
  
  public void showcustomdialog(String paramString, int paramInt)
  {
    this.dialog = new SaveCustomDialog(this, paramString, paramInt, this.guide.getid());
    this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    if (paramInt == 2130903110)
    {
      paramString = this.dialog.getWindow().getAttributes();
      paramString.gravity = 48;
      paramString.width = -1;
      paramString.flags &= 0xFFFFFFFD;
      this.dialog.getWindow().setAttributes(paramString);
      this.dialog.getWindow().getAttributes().windowAnimations = 2131230732;
    }
    try
    {
      if (!this.dialog.isShowing()) {
        this.dialog.show();
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public class LoadHtmlString
    extends AsyncTask<String, Void, String>
  {
    public Context context;
    private String resString;
    private TextView textView;
    
    public LoadHtmlString(Context paramContext, String paramString)
    {
      this.context = paramContext;
      this.resString = paramString;
    }
    
    protected String doInBackground(String... paramVarArgs)
    {
      GetGuideDataActivity.this.document = Jsoup.parse(paramVarArgs[0]);
      GetGuideDataActivity.this.text = GetGuideDataActivity.this.document.body().text().toString();
      GetGuideDataActivity.this.text = GetGuideDataActivity.this.text.replaceAll("[^A-Za-z0-9\\s]", "");
      return GetGuideDataActivity.this.text;
    }
    
    protected void onPostExecute(String paramString)
    {
      GetGuideDataActivity.this.text = paramString;
      Log.d("Guide Frag", "onPostExecute : ");
      Log.d("Guide Frag", "Characters Count Original : " + GetGuideDataActivity.this.text.length());
      int i = GetGuideDataActivity.this.text.length();
      if (GetGuideDataActivity.this.substringlen > i)
      {
        GetGuideDataActivity.this.substringlen = 1000;
        if (GetGuideDataActivity.this.substringlen > i) {
          GetGuideDataActivity.this.substringlen = 500;
        }
      }
      AppConstants.NumOfFiles = i / GetGuideDataActivity.this.substringlen;
      AppConstants.NameOfFile = GetGuideDataActivity.this.GuideId;
      GetGuideDataActivity.this.speakText(GetGuideDataActivity.this.text);
      Log.d("Guide Frag", "Characters Count : " + GetGuideDataActivity.this.text.length());
    }
    
    protected void onPreExecute() {}
  }
  
  class SaveCustomDialog
    extends Dialog
    implements View.OnClickListener
  {
    public Activity ac;
    public Button btncancel;
    public Button btnok;
    public Dialog d;
    public EditText etmessage;
    public Fragment frag;
    public int resourceid;
    public String str = "";
    public String strtile;
    public TextView textView;
    public int tourid;
    public TextView tvcancel;
    
    public SaveCustomDialog(Activity paramActivity, String paramString, int paramInt1, int paramInt2)
    {
      super();
      this.ac = paramActivity;
      this.strtile = paramString;
      this.resourceid = paramInt1;
      this.tourid = paramInt2;
    }
    
    public void delay(int paramInt)
    {
      new Timer().scheduleAtFixedRate(new TimerTask()
      {
        public void run()
        {
          GetGuideDataActivity.SaveCustomDialog.this.dismiss();
        }
      }, paramInt, 2000L);
    }
    
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default: 
        dismiss();
      }
      for (;;)
      {
        dismiss();
        return;
        dismiss();
        if (GetGuideDataActivity.this.preferenceUtils.getUserPremium(PreferenceUtils.user_premium, 0) == 1)
        {
          GetGuideDataActivity.this.initializeLibrary(GetGuideDataActivity.this);
          GetGuideDataActivity.this.downloadSkmfile();
          continue;
          dismiss();
          continue;
          dismiss();
        }
      }
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      requestWindowFeature(1);
      if ((this.resourceid == 2130903110) || (this.resourceid == 2130903169))
      {
        paramBundle = ((LayoutInflater)this.ac.getSystemService("layout_inflater")).inflate(2130903110, null);
        paramBundle.setMinimumWidth(AppConstants.width);
        setContentView(paramBundle);
        delay(6000);
      }
      for (;;)
      {
        this.textView = ((TextView)findViewById(2131493233));
        this.textView.setText(this.strtile);
        this.textView.setTypeface(AppConstants.Roboto_Bold);
        this.textView.setOnClickListener(this);
        this.btnok = ((Button)findViewById(2131493235));
        this.btnok.setText("Ok");
        this.btnok.setOnClickListener(this);
        return;
        if (this.resourceid == 2130903109)
        {
          setContentView(this.resourceid);
          this.etmessage = ((EditText)findViewById(2131493236));
        }
        else if (this.resourceid == 2130903108)
        {
          setContentView(2130903108);
          this.btncancel = ((Button)findViewById(2131493234));
          this.btncancel.setOnClickListener(this);
          this.btncancel.setVisibility(8);
        }
      }
    }
  }
  
  class ttsUtteranceListener
    extends UtteranceProgressListener
  {
    ttsUtteranceListener() {}
    
    public void onDone(String paramString)
    {
      Log.d("Guide Frag", "DONE: " + GetGuideDataActivity.this.callCount + paramString);
      paramString = GetGuideDataActivity.this;
      paramString.callCount += 1;
      if (GetGuideDataActivity.this.callCount == 1)
      {
        GetGuideDataActivity.this.showViews();
        AppConstants.isDone = true;
        AppConstants.isClosed = false;
        GetGuideDataActivity.this.fileUri = Uri.fromFile(GetGuideDataActivity.this.FirstFile);
        AppConstants.mediaPlayer = MediaPlayer.create(GetGuideDataActivity.this, GetGuideDataActivity.this.fileUri);
        AppConstants.mediaPlayer.setAudioStreamType(3);
      }
    }
    
    public void onError(String paramString)
    {
      Log.d("Guide Frag", "onError: " + paramString);
    }
    
    public void onStart(String paramString)
    {
      Log.d("Guide Frag", "onStart: " + paramString);
    }
  }
}
