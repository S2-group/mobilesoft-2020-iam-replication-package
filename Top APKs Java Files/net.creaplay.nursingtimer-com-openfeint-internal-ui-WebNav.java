package com.openfeint.internal.ui;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.openfeint.api.OpenFeintDelegate;
import com.openfeint.api.R.drawable;
import com.openfeint.api.R.id;
import com.openfeint.api.R.layout;
import com.openfeint.api.R.string;
import com.openfeint.api.R.style;
import com.openfeint.api.resource.CurrentUser;
import com.openfeint.api.resource.Score;
import com.openfeint.api.resource.Score.DownloadBlobCB;
import com.openfeint.api.resource.User;
import com.openfeint.api.ui.Dashboard;
import com.openfeint.internal.ImagePicker;
import com.openfeint.internal.JsonResourceParser;
import com.openfeint.internal.OpenFeintInternal;
import com.openfeint.internal.OpenFeintInternal.IUploadDelegate;
import com.openfeint.internal.Util;
import com.openfeint.internal.request.IRawRequestDelegate;
import com.openfeint.internal.resource.ScoreBlobDelegate;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.codehaus.jackson.JsonFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebNav
  extends NestedWindow
{
  protected static final int REQUEST_CODE_NATIVE_BROWSER = 25565;
  protected static final String TAG = "WebUI";
  ActionHandler mActionHandler;
  boolean mIsFrameworkLoaded = false;
  boolean mIsPageLoaded = false;
  Dialog mLaunchLoadingView;
  private Map<String, String> mNativeBrowserParameters = null;
  protected ArrayList<String> mPreloadConsoleOutput = new ArrayList();
  private boolean mShouldRefreshOnResume = true;
  private WebNavClient mWebViewClient;
  protected int pageStackCount;
  
  public WebNav() {}
  
  private void closeForDiskError()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        WebNav.this.dismissDialog();
        if (Util.sdcardReady(WebNav.this)) {}
        for (String str = OpenFeintInternal.getRString(R.string.of_sdcard);; str = OpenFeintInternal.getRString(R.string.of_device))
        {
          new AlertDialog.Builder(WebNav.this).setMessage(String.format(OpenFeintInternal.getRString(R.string.of_nodisk), new Object[] { str })).setPositiveButton(OpenFeintInternal.getRString(R.string.of_no), new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              WebNav.this.finish();
            }
          }).show();
          return;
        }
      }
    });
  }
  
  private void dismissDialog()
  {
    if (this.mLaunchLoadingView.isShowing()) {
      this.mLaunchLoadingView.dismiss();
    }
  }
  
  private static final String jsQuotedStringLiteral(String paramString)
  {
    if (paramString == null) {
      return "''";
    }
    return "'" + paramString.replace("\\", "\\\\").replace("'", "\\'") + "'";
  }
  
  private void showDialog()
  {
    if (!this.mLaunchLoadingView.isShowing()) {
      this.mLaunchLoadingView.show();
    }
  }
  
  protected ActionHandler createActionHandler(WebNav paramWebNav)
  {
    return new ActionHandler(paramWebNav);
  }
  
  protected WebNavClient createWebNavClient(ActionHandler paramActionHandler)
  {
    return new WebNavClient(paramActionHandler);
  }
  
  public void executeJavascript(String paramString)
  {
    if (this.mWebView != null) {
      this.mWebView.loadUrl("javascript:" + paramString);
    }
  }
  
  public ActionHandler getActionHandler()
  {
    return this.mActionHandler;
  }
  
  public Dialog getLaunchLoadingView()
  {
    return this.mLaunchLoadingView;
  }
  
  protected String initialContentPath()
  {
    String str = getIntent().getStringExtra("content_path");
    if (str == null) {
      throw new RuntimeException("WebNav intent requires extra value 'content_path'");
    }
    return str;
  }
  
  protected void load(final boolean paramBoolean)
  {
    this.mIsPageLoaded = false;
    WebViewCache.trackPath(rootPage(), new WebViewCacheCallback()
    {
      public void failLoaded()
      {
        WebNav.this.closeForDiskError();
      }
      
      public void pathLoaded(String paramAnonymousString)
      {
        if (WebNav.this.mWebView != null)
        {
          paramAnonymousString = WebViewCache.getItemUri(paramAnonymousString);
          OpenFeintInternal.log("WebUI", "Loading URL: " + paramAnonymousString);
          if (paramBoolean) {
            WebNav.this.mWebView.reload();
          }
        }
        else
        {
          return;
        }
        WebNav.this.mWebView.loadUrl(paramAnonymousString);
      }
    });
  }
  
  public void loadInitialContent()
  {
    Object localObject2 = initialContentPath();
    Object localObject1 = localObject2;
    if (((String)localObject2).contains("?")) {
      localObject1 = localObject2.split("\\?")[0];
    }
    localObject2 = localObject1;
    if (!((String)localObject1).endsWith(".json")) {
      localObject2 = (String)localObject1 + ".json";
    }
    WebViewCache.trackPath((String)localObject2, new WebViewCacheCallback()
    {
      public void failLoaded()
      {
        WebNav.this.closeForDiskError();
      }
      
      public void pathLoaded(String paramAnonymousString)
      {
        WebNav.this.executeJavascript("OF.navigateToUrl('" + WebNav.this.initialContentPath() + "')");
      }
    });
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    String str;
    if ((this.mNativeBrowserParameters != null) && (paramInt1 == 25565)) {
      if (paramInt2 != 0)
      {
        this.mShouldRefreshOnResume = false;
        if (paramIntent.getBooleanExtra("com.openfeint.internal.ui.NativeBrowser.argument.failed", false))
        {
          str = (String)this.mNativeBrowserParameters.get("on_failure");
          if (str != null) {
            executeJavascript(String.format("%s(%d, %s)", new Object[] { str, Integer.valueOf(paramIntent.getIntExtra("com.openfeint.internal.ui.NativeBrowser.argument.failure_code", 0)), jsQuotedStringLiteral(paramIntent.getStringExtra("com.openfeint.internal.ui.NativeBrowser.argument.failure_desc")) }));
          }
          this.mNativeBrowserParameters = null;
        }
      }
    }
    do
    {
      do
      {
        return;
        str = (String)this.mNativeBrowserParameters.get("callback");
        if (str == null) {
          break;
        }
        paramIntent = paramIntent.getStringExtra("com.openfeint.internal.ui.NativeBrowser.argument.result");
        if (paramIntent != null) {}
        for (;;)
        {
          executeJavascript(String.format("%s(%s)", new Object[] { str, paramIntent }));
          break;
          paramIntent = "";
        }
        paramIntent = (String)this.mNativeBrowserParameters.get("on_cancel");
        if (paramIntent == null) {
          break;
        }
        executeJavascript(String.format("%s()", new Object[] { paramIntent }));
        break;
      } while (!ImagePicker.isImagePickerActivityResult(paramInt1));
      paramIntent = ImagePicker.onImagePickerActivityResult(this, paramInt2, 152, paramIntent);
    } while (paramIntent == null);
    ImagePicker.compressAndUpload(paramIntent, "/xp/users/" + OpenFeintInternal.getInstance().getCurrentUser().resourceID() + "/profile_picture", new OpenFeintInternal.IUploadDelegate()
    {
      public void fileUploadedTo(String paramAnonymousString, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean) {
          WebNav.this.executeJavascript("try { OF.page.onProfilePictureChanged('" + paramAnonymousString + "'); } catch (e) {}");
        }
      }
    });
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2) {}
    for (paramConfiguration = "landscape";; paramConfiguration = "portrait")
    {
      executeJavascript(String.format("OF.setOrientation('%s');", new Object[] { paramConfiguration }));
      return;
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    OpenFeintInternal.log("WebUI", "--- WebUI Bootup ---");
    this.pageStackCount = 0;
    this.mWebView.getSettings().setJavaScriptEnabled(true);
    this.mWebView.getSettings().setPluginsEnabled(false);
    this.mWebView.setScrollBarStyle(33554432);
    this.mWebView.getSettings().setCacheMode(2);
    this.mLaunchLoadingView = new Dialog(this, R.style.OFLoading);
    this.mLaunchLoadingView.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        WebNav.this.finish();
      }
    });
    this.mLaunchLoadingView.setCancelable(true);
    this.mLaunchLoadingView.setContentView(R.layout.of_native_loader);
    paramBundle = (ProgressBar)this.mLaunchLoadingView.findViewById(R.id.progress);
    paramBundle.setIndeterminate(true);
    paramBundle.setIndeterminateDrawable(OpenFeintInternal.getInstance().getContext().getResources().getDrawable(R.drawable.of_native_loader_progress));
    this.mActionHandler = createActionHandler(this);
    this.mWebViewClient = createWebNavClient(this.mActionHandler);
    this.mWebView.setWebViewClient(this.mWebViewClient);
    this.mWebView.setWebChromeClient(new WebNavChromeClient(null));
    this.mWebView.addJavascriptInterface(new Object()
    {
      public void action(final String paramAnonymousString)
      {
        WebNav.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            WebNav.this.getActionHandler().dispatch(Uri.parse(paramAnonymousString));
          }
        });
      }
      
      public void frameworkLoaded()
      {
        WebNav.this.setFrameworkLoaded(true);
      }
    }, "NativeInterface");
    Object localObject = initialContentPath();
    paramBundle = (Bundle)localObject;
    if (((String)localObject).contains("?")) {
      paramBundle = localObject.split("\\?")[0];
    }
    localObject = paramBundle;
    if (!paramBundle.endsWith(".json")) {
      localObject = paramBundle + ".json";
    }
    WebViewCache.prioritize((String)localObject);
    load(false);
    this.mLaunchLoadingView.show();
  }
  
  public void onDestroy()
  {
    this.mWebView.destroy();
    this.mWebView = null;
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 84)
    {
      executeJavascript(String.format("OF.menu('%s')", new Object[] { "search" }));
      return true;
    }
    if ((paramInt == 4) && (this.pageStackCount > 1))
    {
      executeJavascript("OF.goBack()");
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    OpenFeintInternal.restoreInstanceState(paramBundle);
  }
  
  public void onResume()
  {
    super.onResume();
    CurrentUser localCurrentUser = OpenFeintInternal.getInstance().getCurrentUser();
    if ((localCurrentUser != null) && (this.mIsFrameworkLoaded))
    {
      executeJavascript(String.format("if (OF.user) { OF.user.name = %s; OF.user.id = '%s'; }", new Object[] { jsQuotedStringLiteral(localCurrentUser.name), localCurrentUser.resourceID() }));
      if (this.mShouldRefreshOnResume) {
        executeJavascript("if (OF.page) OF.refresh();");
      }
    }
    this.mShouldRefreshOnResume = true;
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    OpenFeintInternal.saveInstanceState(paramBundle);
  }
  
  public void onStop()
  {
    super.onStop();
    dismissDialog();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    OpenFeintDelegate localOpenFeintDelegate = OpenFeintInternal.getInstance().getDelegate();
    if (localOpenFeintDelegate != null)
    {
      if (paramBoolean) {
        localOpenFeintDelegate.onDashboardAppear();
      }
    }
    else {
      return;
    }
    localOpenFeintDelegate.onDashboardDisappear();
  }
  
  protected String rootPage()
  {
    return "index.html";
  }
  
  protected void setFrameworkLoaded(boolean paramBoolean)
  {
    this.mIsFrameworkLoaded = paramBoolean;
  }
  
  protected class ActionHandler
  {
    private static final String WEBUI_PREFS = "OFWebUI";
    private static final String WEBUI_SETTING_PREFIX = "OFWebUISetting_";
    List<String> mActionList;
    WebNav mWebNav;
    
    public ActionHandler(WebNav paramWebNav)
    {
      this.mWebNav = paramWebNav;
      this.mActionList = new ArrayList();
      populateActionList(this.mActionList);
    }
    
    private Map<String, Object> parseQueryString(Uri paramUri)
    {
      return parseQueryString(paramUri.getEncodedQuery());
    }
    
    private Map<String, Object> parseQueryString(String paramString)
    {
      HashMap localHashMap = new HashMap();
      if (paramString != null)
      {
        paramString = paramString.split("&");
        int j = paramString.length;
        int i = 0;
        if (i < j)
        {
          String[] arrayOfString = paramString[i].split("=");
          if (arrayOfString.length == 2) {
            localHashMap.put(arrayOfString[0], Uri.decode(arrayOfString[1]));
          }
          for (;;)
          {
            i += 1;
            break;
            localHashMap.put(arrayOfString[0], null);
          }
        }
      }
      return localHashMap;
    }
    
    public void alert(Map<String, String> paramMap)
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.mWebNav);
      localBuilder.setTitle((CharSequence)paramMap.get("title"));
      localBuilder.setMessage((CharSequence)paramMap.get("message"));
      localBuilder.setNegativeButton(OpenFeintInternal.getRString(R.string.of_ok), null);
      localBuilder.show();
    }
    
    public void apiRequest(Map<String, String> paramMap)
    {
      final String str = (String)paramMap.get("request_id");
      Map localMap1 = parseQueryString((String)paramMap.get("params"));
      Map localMap2 = parseQueryString((String)paramMap.get("httpParams"));
      OpenFeintInternal.genericRequest((String)paramMap.get("path"), (String)paramMap.get("method"), localMap1, localMap2, new IRawRequestDelegate()
      {
        public void onResponse(int paramAnonymousInt, String paramAnonymousString)
        {
          String str = paramAnonymousString.trim();
          paramAnonymousString = str;
          if (str.length() == 0) {
            paramAnonymousString = "{}";
          }
          paramAnonymousString = String.format("OF.api.completeRequest(\"%s\", \"%d\", %s)", new Object[] { str, Integer.valueOf(paramAnonymousInt), paramAnonymousString });
          WebNav.ActionHandler.this.mWebNav.executeJavascript(paramAnonymousString);
        }
      });
    }
    
    public void back(Map<String, String> paramMap)
    {
      this.mWebNav.fade(false);
      paramMap = (String)paramMap.get("root");
      if ((paramMap != null) && (!paramMap.equals("false"))) {
        this.mWebNav.pageStackCount = 1;
      }
      if (this.mWebNav.pageStackCount > 1)
      {
        paramMap = this.mWebNav;
        paramMap.pageStackCount -= 1;
      }
    }
    
    public void contentLoaded(Map<String, String> paramMap)
    {
      if ((paramMap.get("keepLoader") == null) || (!((String)paramMap.get("keepLoader")).equals("true")))
      {
        hideLoader(null);
        WebNav.this.setTitle((CharSequence)paramMap.get("title"));
      }
      this.mWebNav.fade(true);
      WebNav.this.dismissDialog();
    }
    
    public void dashboard(Map<String, String> paramMap) {}
    
    public void dismiss(Map<String, String> paramMap)
    {
      WebNav.this.finish();
    }
    
    public void dispatch(Uri paramUri)
    {
      if (paramUri.getHost().equals("action"))
      {
        Map localMap = parseQueryString(paramUri);
        paramUri = paramUri.getPath().replaceFirst("/", "");
        if (!paramUri.equals("log"))
        {
          HashMap localHashMap = new HashMap(localMap);
          String str = (String)localMap.get("params");
          if ((str != null) && (str.contains("password"))) {
            localHashMap.put("params", "---FILTERED---");
          }
          OpenFeintInternal.log("WebUI", "ACTION: " + paramUri + " " + localHashMap.toString());
        }
        if (this.mActionList.contains(paramUri)) {
          try
          {
            getClass().getMethod(paramUri, new Class[] { Map.class }).invoke(this, new Object[] { localMap });
            return;
          }
          catch (NoSuchMethodException localNoSuchMethodException)
          {
            OpenFeintInternal.log("WebUI", "mActionList contains this method, but it is not implemented: " + paramUri);
            return;
          }
          catch (Exception paramUri)
          {
            OpenFeintInternal.log("WebUI", "Unhandled Exception: " + paramUri.toString() + "   " + paramUri.getCause());
            return;
          }
        }
        OpenFeintInternal.log("WebUI", "UNHANDLED ACTION: " + paramUri);
        return;
      }
      OpenFeintInternal.log("WebUI", "UNHANDLED MESSAGE TYPE: " + paramUri.getHost());
    }
    
    public void downloadBlob(final Map<String, String> paramMap)
    {
      Object localObject = (String)paramMap.get("score");
      final String str = (String)paramMap.get("onError");
      paramMap = (String)paramMap.get("onSuccess");
      try
      {
        localObject = new JsonResourceParser(new JsonFactory().createJsonParser(new StringReader((String)localObject))).parse();
        if ((localObject != null) && ((localObject instanceof Score)))
        {
          localObject = (Score)localObject;
          ((Score)localObject).downloadBlob(new Score.DownloadBlobCB()
          {
            public void onFailure(String paramAnonymousString)
            {
              if (str != null) {
                WebNav.this.executeJavascript(String.format("%s(%s)", new Object[] { str, paramAnonymousString }));
              }
            }
            
            public void onSuccess()
            {
              if (paramMap != null) {
                WebNav.this.executeJavascript(String.format("%s()", new Object[] { paramMap }));
              }
              ScoreBlobDelegate.notifyBlobDownloaded(this.val$score);
            }
          });
        }
        return;
      }
      catch (Exception paramMap)
      {
        while (str == null) {}
        WebNav.this.executeJavascript(String.format("%s(%s)", new Object[] { str, paramMap.getLocalizedMessage() }));
      }
    }
    
    protected List<String> getActionList()
    {
      return this.mActionList;
    }
    
    public void hideLoader(Map<String, String> paramMap) {}
    
    public void isApplicationInstalled(Map<String, String> paramMap)
    {
      int i = 0;
      Object localObject2 = this.mWebNav.getPackageManager().getInstalledApplications(0);
      Object localObject1 = (String)paramMap.get("package_name");
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        if (((ApplicationInfo)((Iterator)localObject2).next()).packageName.equals(localObject1)) {
          i = 1;
        }
      }
      localObject1 = WebNav.this;
      localObject2 = paramMap.get("callback");
      if (i != 0) {}
      for (paramMap = "true";; paramMap = "false")
      {
        ((WebNav)localObject1).executeJavascript(String.format("%s(%s)", new Object[] { localObject2, paramMap }));
        return;
      }
    }
    
    public void log(Map<String, String> paramMap)
    {
      if ((String)paramMap.get("message") != null) {
        OpenFeintInternal.log("WebUI", "WEBLOG: " + (String)paramMap.get("message"));
      }
    }
    
    public void openBrowser(Map<String, String> paramMap)
    {
      Intent localIntent = new Intent(this.mWebNav, NativeBrowser.class);
      WebNav.access$402(WebNav.this, new HashMap());
      String[] arrayOfString = new String[5];
      arrayOfString[0] = "src";
      arrayOfString[1] = "callback";
      arrayOfString[2] = "on_cancel";
      arrayOfString[3] = "on_failure";
      arrayOfString[4] = "timeout";
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str1 = arrayOfString[i];
        String str2 = (String)paramMap.get(str1);
        if (str2 != null)
        {
          WebNav.this.mNativeBrowserParameters.put(str1, str2);
          localIntent.putExtra("com.openfeint.internal.ui.NativeBrowser.argument." + str1, str2);
        }
        i += 1;
      }
      WebNav.this.startActivityForResult(localIntent, 25565);
    }
    
    public void openMarket(Map<String, String> paramMap)
    {
      paramMap = (String)paramMap.get("package_name");
      paramMap = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramMap));
      this.mWebNav.startActivity(paramMap);
    }
    
    public void openYoutubePlayer(Map<String, String> paramMap)
    {
      paramMap = (String)paramMap.get("video_id");
      paramMap = new Intent("android.intent.action.VIEW", Uri.parse("vnd.youtube:" + paramMap));
      if (WebNav.this.getPackageManager().queryIntentActivities(paramMap, 65536).size() == 0)
      {
        Toast.makeText(this.mWebNav, OpenFeintInternal.getRString(R.string.of_no_video), 0).show();
        return;
      }
      this.mWebNav.startActivity(paramMap);
    }
    
    protected void populateActionList(List<String> paramList)
    {
      paramList.add("log");
      paramList.add("apiRequest");
      paramList.add("contentLoaded");
      paramList.add("startLoading");
      paramList.add("back");
      paramList.add("showLoader");
      paramList.add("hideLoader");
      paramList.add("alert");
      paramList.add("dismiss");
      paramList.add("openMarket");
      paramList.add("isApplicationInstalled");
      paramList.add("openYoutubePlayer");
      paramList.add("profilePicture");
      paramList.add("openBrowser");
      paramList.add("downloadBlob");
      paramList.add("dashboard");
      paramList.add("readSetting");
      paramList.add("writeSetting");
    }
    
    public final void profilePicture(Map<String, String> paramMap)
    {
      ImagePicker.show(WebNav.this);
    }
    
    public void readSetting(Map<String, String> paramMap)
    {
      Object localObject = (String)paramMap.get("key");
      String str = (String)paramMap.get("callback");
      if (str != null)
      {
        if (localObject == null) {
          break label129;
        }
        paramMap = "OFWebUISetting_" + (String)localObject;
        paramMap = OpenFeintInternal.getInstance().getContext().getSharedPreferences("OFWebUI", 0).getString(paramMap, null);
        OpenFeintInternal.log("WebUI", String.format("readSetting(%s) => %s", new Object[] { localObject, paramMap }));
        localObject = WebNav.this;
        if (paramMap == null) {
          break label134;
        }
      }
      for (;;)
      {
        ((WebNav)localObject).executeJavascript(String.format("%s(%s)", new Object[] { str, paramMap }));
        return;
        label129:
        paramMap = null;
        break;
        label134:
        paramMap = "null";
      }
    }
    
    public void showLoader(Map<String, String> paramMap) {}
    
    public void startLoading(Map<String, String> paramMap)
    {
      this.mWebNav.fade(false);
      showLoader(null);
      WebViewCache.trackPath((String)paramMap.get("path"), new WebViewCacheCallback()
      {
        public void failLoaded()
        {
          WebNav.this.closeForDiskError();
        }
        
        public void onTrackingNeeded()
        {
          WebNav.this.showDialog();
        }
        
        public void pathLoaded(String paramAnonymousString)
        {
          WebNav.this.executeJavascript("OF.navigateToUrlCallback()");
        }
      });
      paramMap = this.mWebNav;
      paramMap.pageStackCount += 1;
    }
    
    public void writeSetting(Map<String, String> paramMap)
    {
      String str = (String)paramMap.get("key");
      paramMap = (String)paramMap.get("value");
      if ((str != null) && (paramMap != null))
      {
        str = "OFWebUISetting_" + str;
        SharedPreferences.Editor localEditor = OpenFeintInternal.getInstance().getContext().getSharedPreferences("OFWebUI", 0).edit();
        localEditor.putString(str, paramMap);
        localEditor.commit();
      }
    }
  }
  
  private class WebNavChromeClient
    extends WebChromeClient
  {
    private WebNavChromeClient() {}
    
    public void onConsoleMessage(String paramString1, int paramInt, String paramString2)
    {
      if (!WebNav.this.mIsFrameworkLoaded)
      {
        paramString1 = String.format("%s at %s:%d)", new Object[] { paramString1, paramString2, Integer.valueOf(paramInt) });
        WebNav.this.mPreloadConsoleOutput.add(paramString1);
      }
    }
    
    public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, final JsResult paramJsResult)
    {
      new AlertDialog.Builder(paramWebView.getContext()).setMessage(paramString2).setNegativeButton(OpenFeintInternal.getRString(R.string.of_ok), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramJsResult.cancel();
        }
      }).setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramAnonymousDialogInterface)
        {
          paramJsResult.cancel();
        }
      }).show();
      return true;
    }
    
    public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, final JsResult paramJsResult)
    {
      new AlertDialog.Builder(paramWebView.getContext()).setMessage(paramString2).setPositiveButton(OpenFeintInternal.getRString(R.string.of_ok), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramJsResult.confirm();
        }
      }).setNegativeButton(OpenFeintInternal.getRString(R.string.of_cancel), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramJsResult.cancel();
        }
      }).setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramAnonymousDialogInterface)
        {
          paramJsResult.cancel();
        }
      }).show();
      return true;
    }
  }
  
  protected class WebNavClient
    extends WebViewClient
  {
    WebNav.ActionHandler mActionHandler;
    
    public WebNavClient(WebNav.ActionHandler paramActionHandler)
    {
      this.mActionHandler = paramActionHandler;
    }
    
    protected void attemptRecovery(WebView paramWebView, String paramString)
    {
      if (WebViewCache.recover())
      {
        WebNav.this.load(true);
        new AlertDialog.Builder(paramWebView.getContext()).setMessage(OpenFeintInternal.getRString(R.string.of_crash_report_query)).setNegativeButton(OpenFeintInternal.getRString(R.string.of_no), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            WebNav.this.finish();
          }
        }).setPositiveButton(OpenFeintInternal.getRString(R.string.of_yes), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            WebNav.WebNavClient.this.submitCrashReport();
          }
        }).show();
      }
      while (WebViewCache.isDiskError()) {
        return;
      }
      WebNav.this.finish();
    }
    
    public void loadInitialContent()
    {
      OpenFeintInternal localOpenFeintInternal = OpenFeintInternal.getInstance();
      Object localObject2 = localOpenFeintInternal.getCurrentUser();
      int i = WebNav.this.getResources().getConfiguration().orientation;
      Object localObject1 = new HashMap();
      if (localObject2 != null)
      {
        ((HashMap)localObject1).put("id", ((User)localObject2).resourceID());
        ((HashMap)localObject1).put("name", ((User)localObject2).name);
      }
      HashMap localHashMap = new HashMap();
      localHashMap.put("id", localOpenFeintInternal.getAppID());
      localHashMap.put("name", localOpenFeintInternal.getAppName());
      localHashMap.put("version", Integer.toString(localOpenFeintInternal.getAppVersion()));
      Map localMap = OpenFeintInternal.getInstance().getDeviceParams();
      localObject2 = new HashMap();
      ((HashMap)localObject2).put("platform", "android");
      ((HashMap)localObject2).put("clientVersion", localOpenFeintInternal.getOFVersion());
      ((HashMap)localObject2).put("hasNativeInterface", Boolean.valueOf(true));
      ((HashMap)localObject2).put("dpi", Util.getDpiName(WebNav.this));
      ((HashMap)localObject2).put("locale", WebNav.this.getResources().getConfiguration().locale.toString());
      ((HashMap)localObject2).put("user", new JSONObject((Map)localObject1));
      ((HashMap)localObject2).put("game", new JSONObject(localHashMap));
      ((HashMap)localObject2).put("device", new JSONObject(localMap));
      ((HashMap)localObject2).put("actions", new JSONArray(WebNav.this.getActionHandler().getActionList()));
      if (i == 2) {}
      for (localObject1 = "landscape";; localObject1 = "portrait")
      {
        ((HashMap)localObject2).put("orientation", localObject1);
        ((HashMap)localObject2).put("serverUrl", localOpenFeintInternal.getServerUrl());
        localObject1 = new JSONObject((Map)localObject2);
        WebNav.this.executeJavascript(String.format("OF.init.clientBoot(%s)", new Object[] { ((JSONObject)localObject1).toString() }));
        this.mActionHandler.mWebNav.loadInitialContent();
        return;
      }
    }
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      if (WebNav.this.mIsPageLoaded) {
        return;
      }
      WebNav.this.mIsPageLoaded = true;
      if (WebNav.this.mIsFrameworkLoaded)
      {
        loadInitialContent();
        return;
      }
      attemptRecovery(paramWebView, paramString);
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      this.mActionHandler.hideLoader(null);
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Uri localUri = Uri.parse(paramString);
      if ((localUri.getScheme().equals("http")) || (localUri.getScheme().equals("https"))) {
        paramWebView.loadUrl(paramString);
      }
      for (;;)
      {
        return true;
        if (localUri.getScheme().equals("openfeint")) {
          this.mActionHandler.dispatch(localUri);
        } else {
          OpenFeintInternal.log("WebUI", "UNHANDLED PROTOCOL: " + localUri.getScheme());
        }
      }
    }
    
    protected void submitCrashReport()
    {
      Object localObject = new HashMap();
      ((Map)localObject).put("console", new JSONArray(WebNav.this.mPreloadConsoleOutput));
      localObject = new JSONObject((Map)localObject);
      HashMap localHashMap = new HashMap();
      localHashMap.put("crash_report", ((JSONObject)localObject).toString());
      OpenFeintInternal.genericRequest("/webui/crash_report", "POST", localHashMap, null, null);
    }
  }
}
