package com.sygic.aura.feature.system;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.appevents.AppEventsLogger;
import com.flurry.android.FlurryAgent;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.sygic.aura.CameraActivity;
import com.sygic.aura.RateWebActivity;
import com.sygic.aura.ShopWebActivity;
import com.sygic.aura.SygicMain;
import com.sygic.aura.SygicPreferences;
import com.sygic.aura.WebActivity;
import com.sygic.aura.analytics.InfinarioAnalyticsLogger;
import com.sygic.aura.c2dm.C2DMessaging;
import com.sygic.aura.clazz.ImageInfo;
import com.sygic.aura.data.DirectionStatus;
import com.sygic.aura.feature.Features;
import com.sygic.aura.feature.automotive.BoschMySpin;
import com.sygic.aura.feature.automotive.FuelMapper;
import com.sygic.aura.feature.automotive.sdl.SmartDeviceLinkService;
import com.sygic.aura.utils.GuiUtils.CustomDialogFragment;
import com.sygic.aura.utils.PictureData;
import com.sygic.aura.utils.UriUtils;
import com.sygic.aura.utils.Utils;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

class LowSystemFeatureBase
  extends LowSystemFeature
{
  protected LowSystemFeatureBase() {}
  
  protected LowSystemFeatureBase(Context paramContext)
  {
    super(paramContext);
  }
  
  private String getRefApp()
  {
    Object localObject2 = null;
    try
    {
      Object localObject1 = this.mCtx.getPackageManager().getInstalledPackages(0);
      Object localObject3 = (PackageInfo[])((List)localObject1).toArray(new PackageInfo[((List)localObject1).size()]);
      int i = 0;
      for (;;)
      {
        localObject1 = localObject2;
        if (i < localObject3.length)
        {
          localObject1 = localObject3[i];
          if (!((PackageInfo)localObject1).packageName.startsWith("com.sygic.aura.referral.")) {
            break label109;
          }
          localObject3 = ((PackageInfo)localObject1).packageName.substring("com.sygic.aura.referral.".length());
          localObject1 = localObject2;
          if (!TextUtils.isEmpty((CharSequence)localObject3)) {
            localObject1 = "utm_source=" + (String)localObject3;
          }
        }
        return localObject1;
        label109:
        i += 1;
      }
      return null;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private Map<String, Object> paramsToMap(String paramString1, String paramString2)
  {
    HashMap localHashMap2 = null;
    HashMap localHashMap1 = localHashMap2;
    if (paramString2 != null)
    {
      String[] arrayOfString = paramString2.split("\001");
      localHashMap1 = localHashMap2;
      if (arrayOfString.length > 0)
      {
        localHashMap2 = new HashMap();
        if (arrayOfString.length % 2 == 1)
        {
          localHashMap2.put("logevent_invalid_params", "name=" + paramString1 + " params=" + paramString2);
          InfinarioAnalyticsLogger.getInstance(this.mCtx).trackEvent("INTERNAL_ERROR", localHashMap2);
          return null;
        }
        int i = 0;
        for (;;)
        {
          localHashMap1 = localHashMap2;
          if (i >= arrayOfString.length / 2) {
            break;
          }
          localHashMap2.put(arrayOfString[(i * 2)], arrayOfString[(i * 2 + 1)]);
          i += 1;
        }
      }
    }
    return localHashMap1;
  }
  
  private Bundle parseFacebookParams(Map<String, Object> paramMap)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    paramMap = new Bundle(paramMap.size());
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramMap.putString((String)localEntry.getKey(), (String)localEntry.getValue());
    }
    return paramMap;
  }
  
  private void setArguments(String paramString)
  {
    if (paramString != null) {
      SygicMain.getInstance().SetArguments(paramString);
    }
  }
  
  private void showEmailDialog(final String paramString)
  {
    paramString = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        SygicPreferences.setEmailAsked(LowSystemFeatureBase.this.mCtx, true);
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case -1: 
          SygicPreferences.setEmailAllowed(LowSystemFeatureBase.this.mCtx, true);
          InfinarioAnalyticsLogger.getInstance(LowSystemFeatureBase.this.mCtx).identify(paramString);
          return;
        }
        SygicPreferences.setEmailAllowed(LowSystemFeatureBase.this.mCtx, false);
      }
    };
    Object localObject = SygicMain.getInstance();
    localObject = GuiUtils.CustomDialogFragment.newInstance(((SygicMain)localObject).getCoreString(this.mCtx, 2131165275), ((SygicMain)localObject).getCoreString(this.mCtx, 2131165274), ((SygicMain)localObject).getCoreString(this.mCtx, 2131165273), ((SygicMain)localObject).getCoreString(this.mCtx, 2131165272));
    ((GuiUtils.CustomDialogFragment)localObject).setCancelable(false);
    ((GuiUtils.CustomDialogFragment)localObject).setOnPositiveBtnClick(paramString);
    ((GuiUtils.CustomDialogFragment)localObject).setOnNegativeBtnClick(paramString);
    ((GuiUtils.CustomDialogFragment)localObject).showDialog("dialog_allow_email");
  }
  
  private void startEvents()
  {
    if ((this.mCtx instanceof Activity)) {
      EasyTracker.getInstance(this.mCtx).activityStart((Activity)this.mCtx);
    }
  }
  
  private void stopEvents()
  {
    if ((this.mCtx instanceof Activity)) {
      EasyTracker.getInstance(this.mCtx).activityStop((Activity)this.mCtx);
    }
  }
  
  private void waitForResult()
  {
    try
    {
      wait();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
      }
    }
    finally {}
  }
  
  public void browserClose()
  {
    Intent localIntent = new Intent(this.mCtx, WebActivity.class);
    localIntent.setFlags(537001984);
    localIntent.putExtra("close", true);
    Activity localActivity = SygicMain.getInstance().getFeature().getActivity();
    if (localActivity != null)
    {
      localActivity.startActivity(localIntent);
      return;
    }
    this.mCtx.startActivity(localIntent);
  }
  
  public void browserOpenUri(String paramString1, String paramString2, int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
    localIntent.setFlags(268435456);
    if (paramString2 != null)
    {
      if (paramString2.equals("pdf"))
      {
        paramString1 = new File(paramString1);
        if ((paramString1 != null) && (paramString1.exists())) {
          localIntent.setDataAndType(Uri.fromFile(paramString1), "application/pdf");
        }
      }
      label161:
      do
      {
        do
        {
          try
          {
            this.mCtx.startActivity(localIntent);
            return;
          }
          catch (Exception paramString1)
          {
            paramString1.printStackTrace();
            return;
          }
          if (!paramString2.equals("fb")) {
            break label161;
          }
          if (this.mFacebookConnect == null) {
            this.mFacebookConnect = new FacebookConnect();
          }
          if (!this.mFacebookConnect.isFBApplication(this.mCtx)) {
            break;
          }
        } while (this.mFacebookConnect.startSingleSignOn(this.mCtx, "105659562809354", 223));
        return;
        this.mCtx.startActivity(localIntent);
        return;
        if (paramString2.equals("db"))
        {
          if (this.mDropBoxLogin == null) {
            this.mDropBoxLogin = new DropBoxLogin(this.mCtx);
          }
          this.mDropBoxLogin.login();
          return;
        }
      } while (paramString2.indexOf("in") != 0);
      if (paramString2.indexOf("rate") > 0) {
        paramString2 = new Intent(this.mCtx, RateWebActivity.class);
      }
      for (;;)
      {
        paramString2.putExtra("category", paramInt);
        paramString2.setData(Uri.parse(paramString1));
        paramString1 = SygicMain.getInstance().getFeature().getActivity();
        if (paramString1 == null) {
          break;
        }
        paramString1.startActivityForResult(paramString2, 226);
        return;
        if (paramString2.indexOf("shop") > 0) {
          paramString2 = new Intent(this.mCtx, ShopWebActivity.class);
        } else {
          paramString2 = new Intent(this.mCtx, WebActivity.class);
        }
      }
      this.mCtx.startActivity(paramString2);
      return;
    }
    try
    {
      this.mCtx.startActivity(localIntent);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public void createShortcut(String paramString1, String paramString2)
  {
    paramString2 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramString2);
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString1);
    localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(this.mCtx, 2130837685));
    this.mCtx.sendBroadcast(localIntent);
  }
  
  public void enableEventLogging(boolean paramBoolean)
  {
    this.m_bEnableEvents = paramBoolean;
    if (paramBoolean)
    {
      startEvents();
      return;
    }
    stopEvents();
  }
  
  public String getCurrencyFormattedPrice(float paramFloat, String paramString, int paramInt)
  {
    NumberFormat localNumberFormat = NumberFormat.getCurrencyInstance();
    try
    {
      Currency localCurrency = Currency.getInstance(paramString);
      localNumberFormat.setCurrency(localCurrency);
      if ((localNumberFormat instanceof DecimalFormat))
      {
        paramString = (DecimalFormat)localNumberFormat;
        paramString.applyPattern("0.0¤");
        paramString.setMinimumFractionDigits(paramInt);
        return paramString.format(paramFloat);
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      return paramFloat + " " + paramString;
    }
    localIllegalArgumentException.setMinimumFractionDigits(paramInt);
    return localIllegalArgumentException.format(paramFloat);
  }
  
  public String getDeviceName()
  {
    return Build.MODEL;
  }
  
  public boolean getFullscreen()
  {
    return SygicPreferences.getFullscreen(this.mCtx);
  }
  
  public String getGUID()
  {
    String str = UUID.randomUUID().toString();
    if (str != null) {
      str.replace("-", "");
    }
    return str;
  }
  
  public Object getImage(int paramInt1, int paramInt2)
  {
    paramInt1 = Math.min(paramInt1, paramInt2);
    Object localObject = this.mCtx.getFilesDir() + "/avatar.jpg";
    if (this.mImageInfo == null) {
      this.mImageInfo = new ImageInfo();
    }
    this.mImageInfo.setImage((String)localObject, paramInt1);
    localObject = new Intent("android.intent.action.GET_CONTENT");
    ((Intent)localObject).setType("image/*");
    Activity localActivity = SygicMain.getInstance().getFeature().getActivity();
    if (localActivity != null) {
      localActivity.startActivityForResult((Intent)localObject, 215);
    }
    for (;;)
    {
      waitForResult();
      return this.mImageInfo;
      this.mCtx.startActivity((Intent)localObject);
    }
  }
  
  public String getOSVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public String getPackageName()
  {
    return this.mCtx.getPackageName();
  }
  
  public Object getPhoto(int paramInt1, int paramInt2)
  {
    paramInt1 = Math.min(paramInt1, paramInt2);
    Object localObject = this.mCtx.getFilesDir() + "/avatar.jpg";
    if (this.mImageInfo == null) {
      this.mImageInfo = new ImageInfo();
    }
    this.mImageInfo.setImage((String)localObject, paramInt1);
    localObject = new Intent(this.mCtx.getApplicationContext(), CameraActivity.class);
    Activity localActivity = SygicMain.getInstance().getFeature().getActivity();
    if (localActivity != null) {
      localActivity.startActivityForResult((Intent)localObject, 216);
    }
    for (;;)
    {
      waitForResult();
      return this.mImageInfo;
      this.mCtx.startActivity((Intent)localObject);
    }
  }
  
  public String getPushToken()
  {
    SygicPreferences.setResetPushToken(this.mCtx, false);
    return C2DMessaging.getRegistrationId(this.mCtx);
  }
  
  public String getReferral()
  {
    Object localObject1 = "";
    Object localObject2 = getRefApp();
    if (!TextUtils.isEmpty((CharSequence)localObject2)) {}
    do
    {
      do
      {
        return localObject2;
        localObject2 = localObject1;
      } while (!SygicPreferences.hasReferrer(this.mCtx));
      Map localMap = SygicPreferences.retrieveReferralParams(this.mCtx);
      String[] arrayOfString = SygicPreferences.PREFS_REFERRALS_EXPECTED_PARAMETERS;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = arrayOfString[i];
        localObject2 = localObject1;
        if (localMap.containsKey(str)) {
          localObject2 = ((String)localObject1).concat(str).concat("=").concat((String)localMap.get(str)).concat("&");
        }
        i += 1;
        localObject1 = localObject2;
      }
      localObject2 = localObject1;
    } while (!((String)localObject1).endsWith("&"));
    return ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
  }
  
  public int getRotationLock()
  {
    Activity localActivity = SygicMain.getInstance().getFeature().getActivity();
    if (localActivity != null) {
      return localActivity.getRequestedOrientation();
    }
    return -1;
  }
  
  public void identifyInfinarioPlayer(String paramString)
  {
    if (SygicPreferences.emailAsked(this.mCtx))
    {
      if (SygicPreferences.emailAllowed(this.mCtx)) {
        InfinarioAnalyticsLogger.getInstance(this.mCtx).identify(paramString);
      }
      return;
    }
    showEmailDialog(paramString);
  }
  
  public void logEvent(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    if (!this.m_bEnableEvents) {}
    do
    {
      return;
      paramString2 = paramsToMap(paramString1, paramString2);
    } while (paramString2 == null);
    logEvent(paramString1, paramString2, paramString3, LowSystemFeature.EEventType.createFromInt(paramInt));
  }
  
  public void logEvent(String paramString1, Map<String, Object> paramMap, String paramString2, LowSystemFeature.EEventType paramEEventType)
  {
    if (!this.m_bEnableEvents) {}
    label254:
    do
    {
      return;
      switch (3.$SwitchMap$com$sygic$aura$feature$system$LowSystemFeature$EEventType[paramEEventType.ordinal()])
      {
      default: 
        return;
      case 1: 
        FlurryAgent.logEvent(paramString1, paramMap);
        return;
      case 2: 
        if (paramMap != null)
        {
          FlurryAgent.logEvent(paramString1, paramMap, true);
          return;
        }
        FlurryAgent.logEvent(paramString1, true);
        return;
      case 3: 
        FlurryAgent.endTimedEvent(paramString1);
        return;
      case 4: 
        FlurryAgent.setUserId(paramString1);
        return;
      case 5: 
        paramString1 = MapBuilder.createEvent("marketplace", paramString1, paramString2, null);
        if ((paramMap != null) && (!paramMap.isEmpty()))
        {
          int i = 0;
          paramMap = paramMap.entrySet().iterator();
          while (paramMap.hasNext())
          {
            paramString2 = (Map.Entry)paramMap.next();
            i += 1;
            paramString1.set(Fields.customDimension(i), (String)paramString2.getValue());
          }
        }
        EasyTracker.getInstance(this.mCtx).send(paramString1.build());
        return;
      case 6: 
        if ("Fuel assistant".equals(paramString1))
        {
          paramString2 = null;
          paramEEventType = SygicMain.getSdlService();
          if ((paramEEventType == null) || (!paramEEventType.isConnected())) {
            break label254;
          }
          paramString2 = paramEEventType;
        }
        for (;;)
        {
          if (paramString2 != null) {
            paramString2.addFuelInfo(paramMap);
          }
          InfinarioAnalyticsLogger.getInstance(this.mCtx).trackEvent(paramString1, paramMap);
          return;
          if (BoschMySpin.INSTANCE.isConnected()) {
            paramString2 = BoschMySpin.INSTANCE;
          }
        }
      }
    } while (paramMap == null);
    if (!TextUtils.isEmpty(paramString2)) {
      try
      {
        double d = Double.parseDouble(paramString2);
        AppEventsLogger.newLogger(this.mCtx.getApplicationContext()).logEvent(paramString1, d, parseFacebookParams(paramMap));
        return;
      }
      catch (NumberFormatException paramString2)
      {
        Log.w("Facebook_log", "trying to log FB event with incorrect VALUE_TO_SUM parameter");
      }
    }
    AppEventsLogger.newLogger(this.mCtx.getApplicationContext()).logEvent(paramString1, parseFacebookParams(paramMap));
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    }
    do
    {
      do
      {
        do
        {
          return;
          if (paramIntent != null)
          {
            paramIntent = paramIntent.getExtras();
            if (paramIntent != null) {
              PictureData.makePicture(paramIntent.getByteArray("_data"), this.mImageInfo, this.mCtx.getContentResolver());
            }
          }
          for (;;)
          {
            try
            {
              notify();
              return;
            }
            finally {}
            this.mImageInfo = null;
          }
          try
          {
            notify();
            return;
          }
          finally {}
          if (paramIntent != null)
          {
            Bundle localBundle = paramIntent.getExtras();
            paramIntent = paramIntent.getData();
            if (localBundle != null) {
              PictureData.makePicture(localBundle.getByteArray("_data"), this.mImageInfo, this.mCtx.getContentResolver());
            }
          }
          for (;;)
          {
            try
            {
              notify();
              return;
            }
            finally {}
            if (paramIntent != null)
            {
              paramIntent = UriUtils.getPath(this.mCtx, paramIntent);
              if (paramIntent != null)
              {
                PictureData.makePicture(paramIntent, this.mImageInfo);
                continue;
                this.mImageInfo = null;
              }
            }
          }
        } while ((this.mFacebookConnect == null) || (!this.mFacebookConnect.startSingleSignOnCallback(paramInt1, paramInt2, paramIntent)));
        paramIntent = this.mFacebookConnect.getAccessToken();
      } while (TextUtils.isEmpty(paramIntent));
      setArguments("fb105659562809354://?access_token=" + paramIntent);
      return;
    } while ((paramIntent == null) || (!paramIntent.hasExtra("EXTRA_ACCESS_TOKEN")));
    paramIntent = paramIntent.getStringExtra("ACCESS_TOKEN");
    paramIntent = "https://accounts.google.com/o/oauth2/approval&response_code=" + paramIntent;
    SygicMain.getInstance().SetArguments(paramIntent);
  }
  
  public void onCreate() {}
  
  public void onDestroy() {}
  
  public void onDirectionChange(DirectionStatus paramDirectionStatus)
  {
    SmartDeviceLinkService localSmartDeviceLinkService = SygicMain.getSdlService();
    if (localSmartDeviceLinkService != null) {
      localSmartDeviceLinkService.onDirectionChange(paramDirectionStatus);
    }
  }
  
  public void onPause() {}
  
  public void onResume() {}
  
  public void onStart()
  {
    if (this.m_bEnableEvents) {
      startEvents();
    }
    if (SygicPreferences.getResetPushToken(this.mCtx))
    {
      SygicMain.getInstance().ResetPushToken();
      SygicPreferences.setResetPushToken(this.mCtx, false);
    }
  }
  
  public void onStop()
  {
    if (this.m_bEnableEvents) {
      stopEvents();
    }
  }
  
  public void sendEmail(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null) {
      return;
    }
    String str = paramString3;
    if (paramString3 == null) {
      str = "";
    }
    paramString3 = paramString2;
    if (paramString2 == null) {
      paramString3 = "";
    }
    paramString2 = new Intent("android.intent.action.SEND");
    paramString2.setType("text/html");
    paramString2.putExtra("android.intent.extra.EMAIL", paramString1.split(";"));
    paramString2.putExtra("android.intent.extra.SUBJECT", paramString3);
    paramString2.putExtra("android.intent.extra.TEXT", Html.fromHtml(str));
    paramString1 = SygicMain.getInstance().getFeature().getActivity();
    if (paramString1 != null) {
      paramString1.startActivityForResult(paramString2, 221);
    }
    for (;;)
    {
      waitForResult();
      return;
      this.mCtx.startActivity(paramString2);
    }
  }
  
  public void setFullscreen(boolean paramBoolean)
  {
    SygicPreferences.setFullscreen(this.mCtx, paramBoolean);
  }
  
  public void setRotationLock(boolean paramBoolean)
  {
    int j = 4;
    int i = j;
    Object localObject;
    if (paramBoolean)
    {
      localObject = new DisplayMetrics();
      ((WindowManager)this.mCtx.getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
      switch (((WindowManager)this.mCtx.getSystemService("window")).getDefaultDisplay().getOrientation())
      {
      default: 
        i = j;
      }
    }
    for (;;)
    {
      localObject = SygicMain.getInstance().getFeature().getActivity();
      if (localObject != null) {
        ((Activity)localObject).setRequestedOrientation(i);
      }
      return;
      i = 1;
      if (((DisplayMetrics)localObject).widthPixels > ((DisplayMetrics)localObject).heightPixels)
      {
        i = 0;
        continue;
        i = 0;
        if (((DisplayMetrics)localObject).widthPixels < ((DisplayMetrics)localObject).heightPixels)
        {
          i = 9;
          continue;
          i = 9;
          if (((DisplayMetrics)localObject).widthPixels > ((DisplayMetrics)localObject).heightPixels)
          {
            i = 8;
            continue;
            j = 8;
            i = j;
            if (((DisplayMetrics)localObject).widthPixels < ((DisplayMetrics)localObject).heightPixels)
            {
              i = j;
              if (Utils.getAndroidVersion() >= 9) {
                i = 1;
              }
            }
          }
        }
      }
    }
  }
  
  public Object takePhoto(long paramLong1, long paramLong2)
  {
    if (this.mImageInfo == null) {
      this.mImageInfo = new ImageInfo();
    }
    this.mImageInfo.setLatLong(paramLong2, paramLong1);
    Object localObject = new Intent(this.mCtx.getApplicationContext(), CameraActivity.class);
    Activity localActivity = SygicMain.getInstance().getFeature().getActivity();
    if (localActivity != null) {
      localActivity.startActivityForResult((Intent)localObject, 216);
    }
    for (;;)
    {
      waitForResult();
      if (this.mImageInfo != null)
      {
        localObject = new MediaScannerConnection.MediaScannerConnectionClient()
        {
          public void onMediaScannerConnected()
          {
            LowSystemFeatureBase.this.mMsc.scanFile(LowSystemFeatureBase.this.mImageInfo.getPath(), null);
          }
          
          public void onScanCompleted(String paramAnonymousString, Uri paramAnonymousUri)
          {
            if (paramAnonymousString.equals(LowSystemFeatureBase.this.mImageInfo.getPath())) {
              LowSystemFeatureBase.this.mMsc.disconnect();
            }
          }
        };
        this.mMsc = new MediaScannerConnection(this.mCtx, (MediaScannerConnection.MediaScannerConnectionClient)localObject);
        this.mMsc.connect();
      }
      return this.mImageInfo;
      this.mCtx.startActivity((Intent)localObject);
    }
  }
  
  public void updateInfinarioParameters(String paramString)
  {
    paramString = paramsToMap("Update parameters", paramString);
    if (paramString != null) {
      InfinarioAnalyticsLogger.getInstance(this.mCtx).update(paramString);
    }
  }
}
