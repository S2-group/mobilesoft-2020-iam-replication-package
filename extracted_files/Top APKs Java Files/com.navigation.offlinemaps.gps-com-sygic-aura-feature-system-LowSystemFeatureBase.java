package com.sygic.aura.feature.system;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Tracker;
import com.sygic.aura.SygicMain;
import com.sygic.aura.SygicPreferences;
import com.sygic.aura.WebActivity;
import com.sygic.aura.analytics.DrivingEventCreator;
import com.sygic.aura.analytics.GoogleAnalyticsLogger;
import com.sygic.aura.analytics.InfinarioAnalyticsLogger;
import com.sygic.aura.analytics.InfinarioAnalyticsLogger.AttributeProvider;
import com.sygic.aura.analytics.InfinarioLoggerInterface;
import com.sygic.aura.c2dm.C2DMessaging;
import com.sygic.aura.clazz.ImageInfo;
import com.sygic.aura.events.CoreEventsListener;
import com.sygic.aura.feature.Features;
import com.sygic.aura.generated.features.SygicFeatures;
import com.sygic.aura.utils.PictureData;
import java.io.File;
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
  
  private String duplicateFacebookEvent(String paramString)
  {
    if ("fb_mobile_add_to_cart".equals(paramString)) {
      return "product_priceClicked";
    }
    if ("fb_mobile_add_to_wishlist".equals(paramString)) {
      return "product_show";
    }
    return null;
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
  
  private void logInfinarioEvent(final Map<String, String> paramMap, String paramString)
  {
    InfinarioAnalyticsLogger.getInstance(this.mCtx).track(paramString, new InfinarioAnalyticsLogger.AttributeProvider()
    {
      public void fillAttributes(Map<String, Object> paramAnonymousMap)
      {
        if (paramMap != null) {
          paramAnonymousMap.putAll(paramMap);
        }
      }
    });
  }
  
  private Bundle parseFacebookParams(Map<String, String> paramMap)
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
  
  private void sendFacebookEvent(String paramString, double paramDouble, Bundle paramBundle)
  {
    String str = duplicateFacebookEvent(paramString);
    if (str != null) {
      sendFacebookEvent(str, paramDouble, paramBundle);
    }
    AppEventsLogger.newLogger(this.mCtx.getApplicationContext()).logEvent(paramString, paramDouble, paramBundle);
  }
  
  private void sendFacebookEvent(String paramString, Bundle paramBundle)
  {
    String str = duplicateFacebookEvent(paramString);
    if (str != null) {
      sendFacebookEvent(str, paramBundle);
    }
    AppEventsLogger.newLogger(this.mCtx.getApplicationContext()).logEvent(paramString, paramBundle);
  }
  
  private void setArguments(String paramString)
  {
    if (paramString != null) {
      SygicMain.getInstance().setArguments(paramString);
    }
  }
  
  private void startEvents()
  {
    FlurryAgent.onStartSession(this.mCtx);
  }
  
  private void stopEvents()
  {
    FlurryAgent.onEndSession(this.mCtx);
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
  
  public void browserOpenUri(String paramString1, String paramString2, String paramString3)
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
      for (;;)
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
        if (paramString2.equals("fb"))
        {
          if (this.mFacebookConnect == null) {
            this.mFacebookConnect = new FacebookConnect();
          }
          if (this.mFacebookConnect.isFBApplication(this.mCtx))
          {
            if (this.mFacebookConnect.startSingleSignOn(this.mCtx, "105659562809354", 223)) {}
          }
          else {
            this.mCtx.startActivity(localIntent);
          }
        }
        else
        {
          if (paramString2.equals("db"))
          {
            if (this.mDropBoxLogin == null) {
              this.mDropBoxLogin = new DropBoxLogin(this.mCtx);
            }
            this.mDropBoxLogin.login(paramString1);
            return;
          }
          if (paramString2.equals("in"))
          {
            if ((SygicMain.getCoreEventsListener() != null) && (SygicMain.getCoreEventsListener().onWebViewOpenUri(paramString1, paramString3))) {}
            for (int i = 1; i == 0; i = 0)
            {
              paramString2 = new Intent(this.mCtx, WebActivity.class);
              paramString2.setData(Uri.parse(paramString1));
              paramString1 = SygicMain.getInstance().getFeature().getActivity();
              if (paramString1 == null) {
                break label288;
              }
              paramString1.startActivityForResult(paramString2, 226);
              return;
            }
          }
        }
      }
      label288:
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
  
  public void createShortcut(String paramString1, String paramString2, int paramInt)
  {
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", new Intent("android.intent.action.VIEW", Uri.parse(paramString2)));
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString1);
    paramString1 = this.mCtx;
    if (paramInt > 0) {}
    for (;;)
    {
      localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramString1, paramInt));
      this.mCtx.sendBroadcast(localIntent);
      return;
      paramInt = 2130903041;
    }
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
      localNumberFormat.setMinimumFractionDigits(paramInt);
      return localNumberFormat.format(paramFloat);
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
    return paramFloat + " " + paramString;
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
  
  public void logEvent(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    if (!this.m_bEnableEvents) {}
    do
    {
      do
      {
        return;
        Object localObject = null;
        String[] arrayOfString = null;
        if (paramString2 != null) {
          arrayOfString = paramString2.split(":");
        }
        paramString2 = (String)localObject;
        if (arrayOfString != null)
        {
          paramString2 = (String)localObject;
          if (arrayOfString.length > 0)
          {
            localObject = new HashMap();
            int i = 0;
            for (;;)
            {
              paramString2 = (String)localObject;
              if (i >= arrayOfString.length / 2) {
                break;
              }
              ((Map)localObject).put(arrayOfString[(i * 2)], arrayOfString[(i * 2 + 1)]);
              i += 1;
            }
          }
        }
        localObject = LowSystemFeature.EEventType.createFromInt(paramInt);
        switch (2.$SwitchMap$com$sygic$aura$feature$system$LowSystemFeature$EEventType[localObject.ordinal()])
        {
        default: 
          return;
        case 1: 
          FlurryAgent.logEvent(paramString1, paramString2);
          return;
        case 2: 
          if (paramString2 != null)
          {
            FlurryAgent.logEvent(paramString1, paramString2, true);
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
          paramString1 = new HitBuilders.EventBuilder().setCategory("marketplace").setAction(paramString1).setLabel(paramString3);
          if ((arrayOfString != null) && (arrayOfString.length > 0))
          {
            paramInt = 0;
            while (paramInt < arrayOfString.length / 2)
            {
              paramString1.setCustomDimension(paramInt + 1, arrayOfString[(paramInt * 2 + 1)]);
              paramInt += 1;
            }
          }
          GoogleAnalyticsLogger.getInstance(this.mCtx).getTracker().send(paramString1.build());
          return;
        }
      } while (paramString2 == null);
      if (!TextUtils.isEmpty(paramString3)) {
        try
        {
          sendFacebookEvent(paramString1, Double.parseDouble(paramString3), parseFacebookParams(paramString2));
          return;
        }
        catch (NumberFormatException paramString3)
        {
          Log.w("Facebook_log", "trying to log FB event with incorrect VALUE_TO_SUM parameter");
        }
      }
      sendFacebookEvent(paramString1, parseFacebookParams(paramString2));
      return;
      logInfinarioEvent(paramString2, paramString1);
    } while ((paramString2 == null) || (!SygicFeatures.FEATURE_SEND_DRIVING_EVENT_TO_PS));
    DrivingEventCreator.create(paramString2);
    return;
    logInfinarioEvent(paramString2, "Monetization error");
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
        Object localObject;
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
              localObject = paramIntent.getExtras();
              paramIntent = paramIntent.getData();
              if (localObject != null) {
                PictureData.makePicture(((Bundle)localObject).getByteArray("_data"), this.mImageInfo, this.mCtx.getContentResolver());
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
                paramIntent = this.mCtx.getContentResolver().query(paramIntent, new String[] { "_data" }, null, null, null);
                if ((paramIntent != null) && (paramIntent.getCount() == 1))
                {
                  paramIntent.moveToFirst();
                  paramInt1 = paramIntent.getColumnIndex("_data");
                  if (paramInt1 >= 0)
                  {
                    PictureData.makePicture(paramIntent.getString(paramInt1), this.mImageInfo);
                    continue;
                    this.mImageInfo = null;
                  }
                }
              }
            }
          } while ((this.mFacebookConnect == null) || (!this.mFacebookConnect.startSingleSignOnCallback(paramInt1, paramInt2, paramIntent)));
          paramIntent = this.mFacebookConnect.getAccessToken();
        } while (TextUtils.isEmpty(paramIntent));
        setArguments("fb105659562809354://?access_token=" + paramIntent);
        return;
        if ((paramIntent != null) && (paramIntent.hasExtra("ACCESS_TOKEN")))
        {
          localObject = paramIntent.getStringExtra("ACCESS_TOKEN");
          String str = paramIntent.getStringExtra("ACCESS_SECRET");
          paramIntent = paramIntent.getStringExtra("UID");
          paramIntent = "db-://oauth_token_secret=" + str + "&oauth_token=" + (String)localObject + "&uid=" + paramIntent;
          SygicMain.getInstance().setArguments(paramIntent);
        }
        try
        {
          notify();
          return;
        }
        finally {}
      } while ((paramIntent == null) || (!paramIntent.hasExtra("EXTRA_URL")));
      paramIntent = paramIntent.getStringExtra("EXTRA_URL");
      SygicMain.getInstance().setArguments(paramIntent);
      return;
    } while ((paramIntent == null) || (!paramIntent.hasExtra("EXTRA_ACCESS_TOKEN")));
    paramIntent = paramIntent.getStringExtra("ACCESS_TOKEN");
    paramIntent = "https://accounts.google.com/o/oauth2/approval&response_code=" + paramIntent;
    SygicMain.getInstance().setArguments(paramIntent);
  }
  
  public void onCreate()
  {
    FlurryAgent.setContinueSessionMillis(10800000L);
    String str = "MTWH88QG99VM2F7GCYRM";
    if (TextUtils.isEmpty("MTWH88QG99VM2F7GCYRM")) {
      str = "6MBK65TP988BV479PBWS";
    }
    FlurryAgent.init(this.mCtx, str);
  }
  
  public void onDestroy()
  {
    FlurryAgent.onEndSession(this.mCtx);
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
            i = 8;
            if (((DisplayMetrics)localObject).widthPixels < ((DisplayMetrics)localObject).heightPixels) {
              i = 1;
            }
          }
        }
      }
    }
  }
}
