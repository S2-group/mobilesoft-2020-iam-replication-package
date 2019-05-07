package com.flipps.fitetv;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.adjust.sdk.Adjust;
import com.android.volley.Request.Priority;
import com.flipps.fitetv.account.smartlock.AuthenticationManager;
import com.flipps.fitetv.account.smartlock.LoginListener;
import com.flipps.fitetv.cast.FlippsCastManager;
import com.flipps.fitetv.firetv.FlippsFiretvManager;
import com.flipps.fitetv.ftug.FirstTimeUserGuide;
import com.flipps.fitetv.ftug.FirstTimeUserGuideXLarge;
import com.flipps.fitetv.http.HTTP;
import com.flipps.fitetv.player.RemotePlayer;
import com.flipps.fitetv.receiver.NetworkStateReceiver;
import com.flipps.fitetv.service.FCMService;
import com.flipps.fitetv.service.RemoteGateway;
import com.flipps.fitetv.service.RemoteGateway.Config;
import com.flipps.fitetv.service.RemoteGateway.Config.TVWizardPosition;
import com.flipps.fitetv.service.SharingService;
import com.flipps.fitetv.service.UploadManager;
import com.flipps.fitetv.service.data.Channel;
import com.flipps.fitetv.service.data.FeedItem;
import com.flipps.fitetv.service.data.Layout;
import com.flipps.fitetv.ui.AmsActivity;
import com.flipps.fitetv.ui.NoInternetActivity;
import com.flipps.fitetv.ui.UpgradeToPremiumActivity;
import com.flipps.fitetv.ui.VideoListActivity;
import com.flipps.fitetv.ui.xlarge.NoInternetActivityXLarge;
import com.flipps.fitetv.ui.xlarge.VideoListActivityXLarge;
import com.flipps.fitetv.util.AmsProperties;
import com.flipps.fitetv.util.AppRater;
import com.flipps.fitetv.util.GoogleAnalyticsUtil;
import com.flipps.fitetv.util.IOUtils;
import com.flipps.fitetv.util.NetworkUtil;
import com.flipps.fitetv.util.VolleySingleton;
import com.google.android.gms.auth.api.credentials.Credential;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity
  extends AmsActivity
  implements LoginListener
{
  public static final int ACTIVITY_CODE_BUY_NOW = 101;
  private static final String TAG = "MainActivity";
  public static int badgeCount = 0;
  public static boolean created;
  private static boolean isStarting = false;
  private static boolean isStopping = false;
  private static boolean preventGlobalShutdown = false;
  private AuthenticationManager authenticationManager;
  private Handler iHandler = new Handler()
  {
    private void handleSharingServiceEvent(int paramAnonymousInt)
    {
      switch (paramAnonymousInt)
      {
      default: 
        return;
      }
      MainActivity.this.serverStartSucceeded();
    }
    
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default: 
        return;
      }
      handleSharingServiceEvent(paramAnonymousMessage.arg1);
    }
  };
  private boolean networkStateReceiverRegistered;
  private boolean startedFromDeepLink = false;
  
  static
  {
    created = false;
  }
  
  public MainActivity() {}
  
  private void initSignupImages()
  {
    String[] arrayOfString = AmsApplication.getApplication().getSharingService().getSignUpImageUrls();
    if (arrayOfString != null)
    {
      VolleySingleton localVolleySingleton = VolleySingleton.getInstance(AmsApplication.getContext());
      int i = 0;
      while (i < arrayOfString.length)
      {
        if (!localVolleySingleton.containsKey(arrayOfString[i])) {
          localVolleySingleton.loadImage(arrayOfString[i], Request.Priority.NORMAL, false, true);
        }
        i += 1;
      }
    }
  }
  
  private boolean isDiscontinued()
  {
    return !AmsApplication.isPremium();
  }
  
  private boolean isTVConnectWizardEnabled()
  {
    return (RemoteGateway.Config.showTVWizardInFTUGPosition != RemoteGateway.Config.TVWizardPosition.NONE) && (FirstTimeUserGuide.tvConnectWizardCounter < RemoteGateway.Config.numTimesToShowFTUG) && (!FirstTimeUserGuide.isUserActivated) && (!AmsApplication.isFite());
  }
  
  private void onLoginResult(boolean paramBoolean)
  {
    onLoginResult(paramBoolean, null);
  }
  
  private void openFTUGChannel(String paramString1, String paramString2)
  {
    if (AmsApplication.isXLarge()) {}
    for (Intent localIntent = new Intent(this, VideoListActivityXLarge.class);; localIntent = new Intent(this, VideoListActivity.class))
    {
      localIntent.putExtra("com.flipps.fitetv.channelTitle", paramString2);
      localIntent.putExtra("com.flipps.fitetv.supportsSearch", true);
      localIntent.putExtra("com.flipps.fitetv.link", paramString1);
      localIntent.putExtra("OPEN_FTUG_CHANNEL", true);
      startActivityForResult(localIntent, 100);
      return;
    }
  }
  
  private boolean openVideo(String paramString, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        String str;
        if (paramString.startsWith("http"))
        {
          str = IOUtils.getContentType(paramString);
          if ((str == null) || (!str.toLowerCase().startsWith("video")))
          {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
            shutdown();
            return true;
          }
        }
        paramString = AmsApplication.getApplication().getSharingService().createSingleItem(Uri.parse(paramString), getIntent().getType(), null);
        if (paramString != null)
        {
          if (RemoteGateway.Config.showVideoDetails)
          {
            openVideoDetails(paramString, paramBoolean);
            return true;
          }
          str = paramString.getLayout().getId();
          if (paramBoolean)
          {
            i = 100;
            startController(paramString, 0, str, i, false);
            return true;
          }
        }
        else
        {
          throw new Exception("Could not create feed item");
        }
      }
      catch (Exception paramString)
      {
        Log.e("MainActivity", "Error creating item.", paramString);
        return false;
      }
      int i = 1013;
    }
  }
  
  private void proceedToNextScreen()
  {
    int i = PreferenceManager.getDefaultSharedPreferences(AmsApplication.getContext()).getInt("APP_CURRENT_VERSION", 0);
    int j = AppRater.getAppVersion();
    if ((i > 0) && (j > i)) {
      AmsApplication.launchAfterUpdate = true;
    }
    AppRater.notifyAppStart();
    if (handleExternalLinksInBackground(false)) {}
    Object localObject;
    boolean bool;
    do
    {
      return;
      localObject = PreferenceManager.getDefaultSharedPreferences(AmsApplication.getContext());
      if (!((SharedPreferences)localObject).contains("APP_FIRST_LAUNCH")) {
        new Thread(new Runnable()
        {
          public void run()
          {
            SharedPreferences.Editor localEditor = this.val$prefs.edit();
            localEditor.putBoolean("APP_FIRST_LAUNCH", false);
            localEditor.commit();
          }
        }).start();
      }
      bool = isTVConnectWizardEnabled();
      if ((!FirstTimeUserGuide.isFTUGConsumed) || (RemoteGateway.Config.askLogin))
      {
        this.authenticationManager.loginWithSmartLock(false);
        return;
      }
    } while ((RemoteGateway.Config.startVideo != null) && (openVideo(RemoteGateway.Config.startVideo, RemoteGateway.Config.exitAfterAction)));
    if (bool)
    {
      if (AmsApplication.isXLarge()) {}
      for (localObject = new Intent(this, FirstTimeUserGuideXLarge.class);; localObject = new Intent(this, FirstTimeUserGuide.class))
      {
        ((Intent)localObject).putExtra("ENABLE_TV_CONNECT_WIZARD", true);
        ((Intent)localObject).putExtra("ENFORCE_TV_CONNECT_WIZARD", true);
        startActivityForResult((Intent)localObject, 100);
        return;
      }
    }
    if ((!AmsApplication.userHasGenres) && (AmsApplication.isFite()))
    {
      if (AmsApplication.isXLarge()) {}
      for (localObject = new Intent(this, FirstTimeUserGuideXLarge.class);; localObject = new Intent(this, FirstTimeUserGuide.class))
      {
        ((Intent)localObject).putExtra("ENFORCE_FAVORITE_GENRES", true);
        startActivityForResult((Intent)localObject, 100);
        return;
      }
    }
    new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        return null;
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        MainActivity.this.openChannel(RemoteGateway.Config.startChannelId, RemoteGateway.Config.startCategoryId, true, false);
      }
    }.execute(new Void[0]);
  }
  
  private void registerNetworkStateReceiver()
  {
    if (!this.networkStateReceiverRegistered)
    {
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.net.wifi.STATE_CHANGE");
      localIntentFilter.addAction("android.net.wifi.supplicant.CONNECTION_CHANGE");
      localIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
      localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
      AmsApplication.getContext().registerReceiver(NetworkStateReceiver.getInstance(), localIntentFilter);
      this.networkStateReceiverRegistered = true;
    }
  }
  
  private void resume()
  {
    new Thread()
    {
      public void run()
      {
        int i = 0;
        if (!MainActivity.isStarting)
        {
          if (!PreferenceManager.getDefaultSharedPreferences(AmsApplication.getContext()).contains("APP_FIRST_LAUNCH")) {
            i = 1;
          }
          if (i != 0) {
            AmsApplication.isFirstAppLaunch = true;
          }
        }
        if ((MainActivity.this.getResources().getConfiguration().orientation == 2) && (!AmsApplication.isXLarge())) {}
        do
        {
          for (;;)
          {
            return;
            if ((MainActivity.this.getResources().getConfiguration().orientation != 1) || (!AmsApplication.isXLarge()))
            {
              MainActivity.this.checkForSharedItem(null);
              if (AmsActivity.sharedUri != null) {
                AmsActivity.deepLinkStartUri = AmsActivity.sharedUri.toString();
              }
              Object localObject;
              if ((!AmsApplication.getApplication().startServiceIfNeed()) || (!NetworkUtil.isOnline()))
              {
                if (AmsApplication.isXLarge()) {}
                for (localObject = new Intent(MainActivity.this, NoInternetActivityXLarge.class);; localObject = new Intent(MainActivity.this, NoInternetActivity.class))
                {
                  MainActivity.this.startActivityForResult((Intent)localObject, 1011);
                  return;
                }
              }
              if (MainActivity.isStarting) {
                break;
              }
              MainActivity.access$002(true);
              try
              {
                localObject = AmsApplication.getApplication().getSharingService();
                while (((localObject == null) || (!((SharingService)localObject).isSharing())) && (!MainActivity.isStopping)) {
                  Thread.sleep(200L);
                }
                if (localException == null) {}
              }
              catch (Exception localException)
              {
                Log.e("MainActivity", "error: " + localException.getMessage(), localException);
                return;
              }
            }
          }
        } while (!localException.isSharing());
        Message.obtain(MainActivity.this.iHandler, 1001, 0, -1).sendToTarget();
      }
    }.start();
  }
  
  private void sendEndSession()
  {
    try
    {
      RemoteGateway.send(AmsProperties.getInstance("E"));
      return;
    }
    catch (Exception localException)
    {
      Log.e("MainActivity", localException.getMessage(), localException);
    }
  }
  
  private void serverStartSucceeded()
  {
    AmsApplication.getApplication().showNotification();
    registerNetworkStateReceiver();
    FirstTimeUserGuide.prepareFTUGActivity(this);
    initSignupImages();
    if (isStopping) {
      return;
    }
    proceedToNextScreen();
  }
  
  private void unregisterNetworkStateReceiver()
  {
    if (this.networkStateReceiverRegistered)
    {
      AmsApplication.getContext().unregisterReceiver(NetworkStateReceiver.getInstance());
      this.networkStateReceiverRegistered = false;
    }
  }
  
  protected void checkForSharedItem(String paramString)
  {
    Object localObject;
    if (getIntent().getExtras() != null)
    {
      localObject = getIntent().getExtras();
      paramString = null;
      if (!((Bundle)localObject).containsKey("android.intent.extra.STREAM")) {
        break label140;
      }
      paramString = ((Bundle)localObject).get("android.intent.extra.STREAM");
      if (!(paramString instanceof String)) {
        break label132;
      }
      paramString = Uri.parse((String)paramString);
      if (paramString != null) {
        sharedUri = paramString;
      }
    }
    if (getIntent().getData() != null)
    {
      localObject = getIntent().getData();
      paramString = (String)localObject;
      if (((Uri)localObject).toString().startsWith("http")) {
        paramString = HTTP.followRedirectsOf((Uri)localObject);
      }
      widgetMode = true;
      if (!AmsApplication.getApplicationScheme().equals(paramString.getScheme())) {
        break label273;
      }
      sharedUri = paramString;
    }
    for (;;)
    {
      if (sharedUri != null) {
        Adjust.appWillOpenUrl(sharedUri);
      }
      return;
      label132:
      paramString = (Uri)paramString;
      break;
      label140:
      if (((Bundle)localObject).containsKey("android.intent.extra.TEXT"))
      {
        localObject = ((Bundle)localObject).getString("android.intent.extra.TEXT");
        if (((String)localObject).indexOf("youtube.com") != -1)
        {
          paramString = (String)localObject;
          if (((String)localObject).startsWith("https")) {
            paramString = "http" + ((String)localObject).substring(5);
          }
          paramString = Uri.parse(paramString);
          break;
        }
        if (((String)localObject).indexOf("youtu.be") != -1)
        {
          paramString = Uri.parse(((String)localObject).substring(((String)localObject).indexOf("http")));
          break;
        }
        paramString = Uri.parse((String)localObject);
        break;
      }
      if (!((Bundle)localObject).containsKey("url")) {
        break;
      }
      paramString = Uri.parse(((Bundle)localObject).getString("url"));
      break;
      label273:
      if (paramString.toString().toLowerCase().indexOf(AmsApplication.getApplicationDomain()) != -1)
      {
        localObject = paramString.getQueryParameter("url");
        if ((localObject != null) && (((String)localObject).length() > 7) && (((String)localObject).startsWith("http"))) {}
        for (sharedUri = Uri.parse((String)localObject);; sharedUri = paramString)
        {
          widgetMode = true;
          break;
        }
      }
      sharedUri = paramString;
    }
  }
  
  protected boolean handleExternalLinksInBackground(boolean paramBoolean)
  {
    Object localObject;
    if ((paramBoolean) && (getIntent().getData() != null))
    {
      localObject = getIntent().getData();
      if ((!AmsApplication.getApplicationScheme().equals(((Uri)localObject).getScheme())) || (!RemotePlayer.isActive()) || (((Uri)localObject).getQueryParameter("screen") != null) || (((Uri)localObject).getQueryParameter("cid") != null)) {}
    }
    do
    {
      do
      {
        do
        {
          return false;
          sharedUri = null;
          checkForSharedItem(null);
          if (sharedUri == null) {
            break;
          }
          deepLinkStartUri = sharedUri.toString();
          if (paramBoolean) {
            RemoteGateway.restartSession(this);
          }
          if ((RemoteGateway.Config.askLogin) && (!paramBoolean))
          {
            if (AmsApplication.isXLarge()) {}
            for (localObject = new Intent(this, FirstTimeUserGuideXLarge.class);; localObject = new Intent(this, FirstTimeUserGuide.class))
            {
              ((Intent)localObject).putExtra("START_LOGIN", true);
              startActivityForResult((Intent)localObject, 100);
              return true;
            }
          }
        } while (!proceedToPushItem(paramBoolean));
        return true;
        localObject = getIntent();
      } while ((localObject == null) || (((Intent)localObject).getExtras() == null) || (!((Intent)localObject).getExtras().containsKey("com.flipps.fitetv.channelId")));
      int i = -1;
      localObject = ((Intent)localObject).getExtras().getString("com.flipps.fitetv.channelId");
      try
      {
        int j = Integer.valueOf((String)localObject).intValue();
        i = j;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    } while ((i <= -1) || (AmsApplication.getApplication().getSharingService().getChannelById(i, true) == null));
    openChannel(i, null, false, false);
    return true;
  }
  
  protected boolean handleServiceRestartRequest(Intent paramIntent)
  {
    return (paramIntent != null) && (paramIntent.hasExtra("RESTART_SERVICE"));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramIntent != null) && (paramIntent.hasExtra("SHUTDOWN"))) {
      if (RemotePlayer.isActive()) {
        paramIntent = RemotePlayer.getInstance();
      }
    }
    try
    {
      paramIntent.stop();
      paramIntent.release();
      sendEndSession();
      shutdown();
      label560:
      label586:
      label597:
      do
      {
        int j;
        boolean bool1;
        do
        {
          do
          {
            return;
          } while (handleServiceRestartRequest(paramIntent));
          if ((paramIntent != null) && (paramIntent.hasExtra("NO_INTERNET_CONNECTION")))
          {
            isStarting = false;
            if (AmsApplication.isXLarge()) {}
            for (paramIntent = new Intent(this, NoInternetActivityXLarge.class);; paramIntent = new Intent(this, NoInternetActivity.class))
            {
              startActivityForResult(paramIntent, 1011);
              return;
            }
          }
          if ((paramIntent != null) && (paramIntent.hasExtra("OPEN_FTUG_CHANNEL")))
          {
            openFTUGChannel(paramIntent.getStringExtra("com.flipps.fitetv.link"), paramIntent.getStringExtra("com.flipps.fitetv.channelTitle"));
            return;
          }
          j = 0;
          if (paramInt1 != 100) {
            break label560;
          }
          boolean bool4 = false;
          boolean bool5 = false;
          boolean bool6 = false;
          int k = 0;
          bool1 = bool5;
          boolean bool2 = bool6;
          boolean bool3 = bool4;
          i = k;
          if (paramIntent != null)
          {
            bool1 = bool5;
            bool2 = bool6;
            bool3 = bool4;
            i = k;
            if (paramIntent.getExtras() != null)
            {
              bool4 = paramIntent.getBooleanExtra("ARRIVE_FROM_FTUG", false);
              bool5 = paramIntent.getBooleanExtra("START_LOGIN", false);
              bool6 = paramIntent.getBooleanExtra("ENFORCE_TV_CONNECT_WIZARD", false);
              bool1 = bool5;
              bool2 = bool6;
              bool3 = bool4;
              i = k;
              if (paramIntent.getExtras().containsKey("EXIT_AFTER_ACTION"))
              {
                bool1 = bool5;
                bool2 = bool6;
                bool3 = bool4;
                i = k;
                if (!paramIntent.getBooleanExtra("EXIT_AFTER_ACTION", false))
                {
                  i = 1;
                  bool3 = bool4;
                  bool2 = bool6;
                  bool1 = bool5;
                }
              }
            }
          }
          if (((!this.startedFromDeepLink) || (VideoListActivity.isStarted) || (bool3) || (i == 0)) && ((!bool3) || ((!bool1) && (!bool2))) && (i == 0)) {
            break;
          }
        } while ((this.startedFromDeepLink) && (bool1) && (paramInt2 == 50436) && (proceedToPushItem(false)));
        String str = null;
        Object localObject = str;
        if (paramIntent != null)
        {
          localObject = str;
          if (paramIntent.getExtras() != null)
          {
            localObject = str;
            if (paramIntent.getExtras().containsKey("com.flipps.fitetv.channel"))
            {
              localObject = paramIntent.getExtras().getString("com.flipps.fitetv.channel");
              localObject = AmsApplication.getApplication().getSharingService().getChannelById((String)localObject);
            }
          }
        }
        if (localObject != null)
        {
          str = paramIntent.getExtras().getString("CATEGORY_ID");
          bool1 = paramIntent.getExtras().getBoolean("COMING_FROM_RELATED_FROM_DEEP_LINK");
          openChannel(((Channel)localObject).getChannelId(), str, true, bool1);
          return;
        }
        if (this.startedFromDeepLink) {
          RemoteGateway.Config.startChannelId = -1;
        }
        openChannel(RemoteGateway.Config.startChannelId, RemoteGateway.Config.startCategoryId, true, false);
        return;
        int i = j;
        if (paramIntent != null)
        {
          if ((paramIntent.getBooleanExtra("com.flipps.fitetv.singleItem", false)) || (VideoListActivity.isStarted)) {
            i = 1;
          }
        }
        else
        {
          if (paramInt2 == 50433) {
            i = 1;
          }
          if (i == 0) {
            break label586;
          }
          finish();
        }
        for (;;)
        {
          if (paramInt1 != 1013) {
            break label597;
          }
          openChannel(RemoteGateway.Config.startChannelId, RemoteGateway.Config.startCategoryId, true, false);
          return;
          i = 0;
          break;
          sendEndSession();
          shutdown();
        }
        if (paramInt1 == 101) {
          shutdown();
        }
        if ((paramInt1 == 1011) && (paramInt2 == 0))
        {
          sendEndSession();
          shutdown();
        }
      } while (paramInt1 != 1032);
      if (paramInt2 == -1)
      {
        paramIntent = (Credential)paramIntent.getParcelableExtra("com.google.android.gms.credentials.Credential");
        this.authenticationManager.onCredentialsRetrieved(paramIntent);
        return;
      }
      onLoginResult(false);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (isDiscontinued())
    {
      startActivity(new Intent(this, UpgradeToPremiumActivity.class));
      finish();
    }
    label137:
    label175:
    label258:
    label263:
    label268:
    label274:
    do
    {
      return;
      setContentView(2130903271);
      if (!AmsApplication.isFite())
      {
        paramBundle = findViewById(2131886971);
        if (paramBundle != null) {
          paramBundle.setVisibility(8);
        }
      }
      this.authenticationManager = new AuthenticationManager(this, this);
      created = true;
      paramBundle = getIntent();
      int i;
      int j;
      if ((paramBundle != null) && (paramBundle.getExtras() != null) && (paramBundle.getExtras().containsKey("STARTED_FROM_PUSH_NOTIFICATION")))
      {
        i = 1;
        if ((i == 0) && ((paramBundle.getExtras() == null) || (!paramBundle.getExtras().containsKey("google.message_id")))) {
          break label258;
        }
        i = 1;
        if ((paramBundle == null) || (paramBundle.getAction() == null) || ((!paramBundle.getAction().equals("android.intent.action.VIEW")) && (!paramBundle.getAction().equals("android.intent.action.SEND")))) {
          break label263;
        }
        j = 1;
        if ((!AmsApplication.getApplication().isSharingServiceStarted()) || (!AmsApplication.isInBackground)) {
          break label268;
        }
      }
      for (int k = 1;; k = 0)
      {
        badgeCount = FCMService.getBadgeCount(this);
        new Thread(new Runnable()
        {
          public void run()
          {
            FCMService.clearNotificationCounter(MainActivity.this);
          }
        }).start();
        if ((i == 0) && (j == 0)) {
          break label274;
        }
        this.startedFromDeepLink = true;
        if (k == 0) {
          break label274;
        }
        preventGlobalShutdown = true;
        if (!handleExternalLinksInBackground(true)) {
          break label274;
        }
        onBackPressed();
        return;
        i = 0;
        break;
        i = 0;
        break label137;
        j = 0;
        break label175;
      }
      if (((paramBundle.getBooleanExtra("STARTED_FROM_NOTIFICATION", false)) || ((paramBundle.getFlags() & 0x400000) == 4194304)) && ((VideoListActivity.isStarted) || (k != 0)))
      {
        preventGlobalShutdown = true;
        onBackPressed();
        return;
      }
      isStarting = false;
    } while (((getResources().getConfiguration().orientation == 2) && (!AmsApplication.isXLarge())) || (getResources().getConfiguration().orientation != 1) || (!AmsApplication.isXLarge()));
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (((VideoListActivity.isStarted) || (sharedUri != null)) && (!preventGlobalShutdown))
    {
      preventGlobalShutdown = false;
      sendEndSession();
      shutdown();
    }
  }
  
  public void onDialogCanceled()
  {
    shutdown();
  }
  
  public void onInnerDialogCanceled()
  {
    shutdown();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (!isStopping))
    {
      isStopping = true;
      new Thread(new Runnable()
      {
        public void run()
        {
          MainActivity.this.shutdown();
        }
      }).start();
      return true;
    }
    return false;
  }
  
  public void onLoginResult(boolean paramBoolean, String paramString)
  {
    if (AmsApplication.isXLarge())
    {
      paramString = new Intent(this, FirstTimeUserGuideXLarge.class);
      paramString.putExtra("ENABLE_TV_CONNECT_WIZARD", isTVConnectWizardEnabled());
      if (!paramBoolean) {
        break label66;
      }
      if (RemoteGateway.Config.askLogin) {
        RemoteGateway.Config.askLogin = false;
      }
    }
    for (;;)
    {
      startActivityForResult(paramString, 100);
      return;
      paramString = new Intent(this, FirstTimeUserGuide.class);
      break;
      label66:
      if ((FirstTimeUserGuide.isFTUGConsumed) && (RemoteGateway.Config.askLogin)) {
        paramString.putExtra("START_LOGIN", true);
      }
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    if ((getResources().getConfiguration().orientation == 2) && (!AmsApplication.isXLarge())) {}
    while ((getResources().getConfiguration().orientation != 1) || (!AmsApplication.isXLarge())) {
      return;
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    if (isDiscontinued()) {
      return;
    }
    resume();
  }
  
  protected void onStart()
  {
    super.onStart();
    HashMap localHashMap = new HashMap();
    String str;
    if (AmsApplication.isFite()) {
      str = "FITE";
    }
    for (;;)
    {
      localHashMap.put(Integer.valueOf(1), str);
      localHashMap.put(Integer.valueOf(2), RemoteGateway.getAppId(this));
      GoogleAnalyticsUtil.logAppView(this, "Loading Screen", localHashMap, getIntent().getData());
      return;
      if (AmsApplication.isPremium()) {
        str = "HD Edition";
      } else {
        str = "Lite Edition";
      }
    }
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if ((getResources().getConfiguration().orientation == 2) && (!AmsApplication.isXLarge())) {}
    while ((getResources().getConfiguration().orientation != 1) || (!AmsApplication.isXLarge())) {
      return;
    }
  }
  
  public void shutdown()
  {
    try
    {
      if (!AmsApplication.getApplication().isShuttingDown())
      {
        if (FlippsCastManager.getInstance().isActive()) {
          FlippsCastManager.getInstance().shutdown();
        }
        if (FlippsFiretvManager.getInstance().isActive()) {
          FlippsFiretvManager.getInstance().shutdown();
        }
        SharingService localSharingService = AmsApplication.getApplication().getSharingService();
        if (localSharingService != null) {
          localSharingService.stopSharing(true);
        }
        AmsApplication.getApplication().hideNotification();
        unregisterNetworkStateReceiver();
        UploadManager.getInstance().cancel();
        AmsApplication.getApplication().shutdown();
        finish();
        Process.killProcess(Process.myPid());
        System.exit(0);
      }
      return;
    }
    finally {}
  }
  
  public void startRemoteControl(Intent paramIntent)
  {
    startActivityForResult(paramIntent, 100);
  }
  
  private class LogInstalledApplicationsTask
    extends AsyncTask<Void, Void, Void>
  {
    private LogInstalledApplicationsTask() {}
    
    private JSONObject getInstalledPackages()
      throws Exception
    {
      Object localObject1 = MainActivity.this.getPackageManager();
      Object localObject2 = ((PackageManager)localObject1).getInstalledApplications(0);
      JSONArray localJSONArray = new JSONArray();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
        JSONObject localJSONObject1 = new JSONObject();
        Object localObject3 = localApplicationInfo.packageName;
        Object localObject4 = ((PackageManager)localObject1).getPackageInfo((String)localObject3, 4096);
        localJSONObject1.put("packageName", localObject3);
        localJSONObject1.put("firstInstallTime", ((PackageInfo)localObject4).firstInstallTime / 1000L);
        localJSONObject1.put("lastUpdateTime", ((PackageInfo)localObject4).lastUpdateTime / 1000L);
        localObject3 = new JSONArray();
        if (((PackageInfo)localObject4).permissions != null)
        {
          localObject4 = ((PackageInfo)localObject4).permissions;
          int j = localObject4.length;
          int i = 0;
          while (i < j)
          {
            Object localObject5 = localObject4[i];
            JSONObject localJSONObject2 = new JSONObject();
            localJSONObject2.put("permission", localObject5.name);
            ((JSONArray)localObject3).put(localJSONObject2);
            i += 1;
          }
        }
        localJSONObject1.put("permissions", localObject3);
        localObject3 = new JSONObject();
        ((JSONObject)localObject3).put("appflags", localApplicationInfo.flags);
        localJSONObject1.put("applicationInfo", localObject3);
        localJSONArray.put(localJSONObject1);
      }
      localObject1 = new JSONObject();
      ((JSONObject)localObject1).put("packages", localJSONArray);
      return localObject1;
    }
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      try
      {
        paramVarArgs = AmsProperties.getInstance("Cc");
        paramVarArgs.setProperty("1C", "log");
        paramVarArgs.setProperty("i", "pkg");
        RemoteGateway.doPostRequest(".info", paramVarArgs, getInstalledPackages().toString());
        return null;
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          Log.e("MainActivity", "Problem sending application packages to server.", paramVarArgs);
        }
      }
    }
  }
}
