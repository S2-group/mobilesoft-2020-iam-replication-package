package com.sygic.aura.feature.system;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.sygic.aura.CameraActivity;
import com.sygic.aura.ProjectsConsts;
import com.sygic.aura.SygicMain;
import com.sygic.aura.SygicPreferences;
import com.sygic.aura.WebActivity;
import com.sygic.aura.c2dm.C2DMessaging;
import com.sygic.aura.clazz.ImageInfo;
import com.sygic.aura.feature.Features;
import com.sygic.aura.settings.SettingsShared;
import com.sygic.aura.settings.SettingsShared.KeyName;
import com.sygic.aura.utils.PictureData;
import com.sygic.aura.utils.Utils;
import java.io.File;
import java.util.List;
import java.util.Map;
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
  
  private void setArguments(String paramString)
  {
    if (paramString != null) {
      SygicMain.getInstance().SetArguments(paramString);
    }
  }
  
  private void startEvents()
  {
    if (!this.mbIsLabrary) {
      FlurryLogging.start(this.mCtx);
    }
  }
  
  private void startMirrorLink()
  {
    if (((this.m_iMirrorLink & 0x1) != 1) || ((this.m_iMirrorLink & 0x2) == 2)) {}
  }
  
  private void stopEvents()
  {
    if (!this.mbIsLabrary) {
      FlurryLogging.stop(this.mCtx);
    }
  }
  
  private void stopMirrorLink()
  {
    if (((this.m_iMirrorLink & 0x1) != 0) || ((this.m_iMirrorLink & 0x2) == 0)) {}
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
  
  public void browserOpenUri(String paramString1, String paramString2)
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
        if (paramString2.equals("fb"))
        {
          if (this.mFacebookConnect == null) {
            this.mFacebookConnect = new FacebookConnect();
          }
          if (this.mFacebookConnect.isFBApplication(this.mCtx))
          {
            paramString1 = null;
            boolean bool = this.mFacebookConnect.startSingleSignOn(this.mCtx, "105659562809354", 223);
            waitForResult();
            if (bool) {
              paramString1 = this.mFacebookConnect.getAccessToken();
            }
            if (paramString1 == null) {}
            for (paramString1 = "#error#";; paramString1 = "fb105659562809354://?access_token=" + paramString1)
            {
              setArguments(paramString1);
              return;
            }
          }
          this.mCtx.startActivity(localIntent);
          return;
        }
        if (paramString2.equals("db"))
        {
          if (this.mDropBoxLogin == null) {
            this.mDropBoxLogin = new DropBoxLogin(this.mCtx);
          }
          this.mDropBoxLogin.login(paramString1);
          return;
        }
      } while (!paramString2.equals("in"));
      paramString2 = new Intent(this.mCtx, WebActivity.class);
      paramString2.setData(Uri.parse(paramString1));
      SygicMain.getInstance();
      paramString1 = SygicMain.getFeature().getActivity();
      if (paramString1 != null)
      {
        paramString1.startActivityForResult(paramString2, 226);
        return;
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
  
  public boolean copyToClipboard(final String paramString)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return false;
    }
    SygicMain.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (Build.VERSION.SDK_INT >= 11) {
          ((ClipboardManager)LowSystemFeatureBase.this.mCtx.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(paramString, paramString));
        }
      }
    });
    return true;
  }
  
  public void createShortcut(String paramString1, String paramString2)
  {
    paramString2 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramString2);
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString1);
    localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(this.mCtx, Utils.getResId(this.mCtx, "drawable", "sygic")));
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
  
  public void enableMirrorLink(int paramInt)
  {
    this.m_iMirrorLink = paramInt;
    stopMirrorLink();
    startMirrorLink();
  }
  
  public boolean execute(String paramString)
  {
    boolean bool = false;
    Object localObject = paramString.split("\\|");
    if (localObject.length == 1)
    {
      localObject = paramString;
      if (paramString.endsWith("://")) {
        localObject = paramString.substring(0, paramString.length() - 3);
      }
      paramString = this.mCtx.getPackageManager().getLaunchIntentForPackage((String)localObject);
    }
    for (;;)
    {
      try
      {
        this.mCtx.startActivity(paramString);
        bool = true;
        return bool;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      if (localObject.length == 2)
      {
        paramString = new Intent();
        paramString.setComponent(new ComponentName(localObject[0], localObject[1]));
      }
    }
    return false;
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
    SygicMain.getInstance();
    Activity localActivity = SygicMain.getFeature().getActivity();
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
    SygicMain.getInstance();
    Activity localActivity = SygicMain.getFeature().getActivity();
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
    Object localObject2 = ProjectsConsts.getString(14);
    Object localObject3 = getRefApp();
    if (localObject2 != null) {}
    do
    {
      do
      {
        return localObject2;
        if (!TextUtils.isEmpty((CharSequence)localObject3)) {
          return localObject3;
        }
        localObject2 = localObject1;
      } while (!SygicPreferences.hasReferrer(this.mCtx));
      localObject3 = SygicPreferences.retrieveReferralParams(this.mCtx);
      String[] arrayOfString = SygicPreferences.PREFS_REFERRALS_EXPECTED_PARAMETERS;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = arrayOfString[i];
        localObject2 = localObject1;
        if (((Map)localObject3).containsKey(str)) {
          localObject2 = ((String)localObject1).concat(str).concat("=").concat((String)((Map)localObject3).get(str)).concat("&");
        }
        i += 1;
        localObject1 = localObject2;
      }
      localObject2 = localObject1;
    } while (!((String)localObject1).endsWith("&"));
    return ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
  }
  
  public void logEvent(String paramString1, String paramString2, int paramInt)
  {
    if ((!this.mbIsLabrary) && (this.m_bEnableEvents)) {
      FlurryLogging.logEvent(paramString1, paramString2, paramInt);
    }
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
        Object localObject;
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
        if (this.mFacebookConnect != null) {
          this.mFacebookConnect.startSingleSignOnCallback(paramInt1, paramInt2, paramIntent);
        }
        try
        {
          notify();
          return;
        }
        finally {}
        if ((paramIntent != null) && (paramIntent.hasExtra("ACCESS_TOKEN")))
        {
          localObject = paramIntent.getStringExtra("ACCESS_TOKEN");
          String str = paramIntent.getStringExtra("ACCESS_SECRET");
          paramIntent = paramIntent.getStringExtra("UID");
          paramIntent = "db-://oauth_token_secret=" + str + "&oauth_token=" + (String)localObject + "&uid=" + paramIntent;
          SygicMain.getInstance().SetArguments(paramIntent);
        }
        try
        {
          notify();
          return;
        }
        finally {}
      } while ((paramIntent == null) || (!paramIntent.hasExtra("EXTRA_URL")));
      paramIntent = paramIntent.getStringExtra("EXTRA_URL");
      SygicMain.getInstance().SetArguments(paramIntent);
      return;
    } while ((paramIntent == null) || (!paramIntent.hasExtra("EXTRA_ACCESS_TOKEN")));
    paramIntent = paramIntent.getStringExtra("ACCESS_TOKEN");
    paramIntent = "https://accounts.google.com/o/oauth2/approval&response_code=" + paramIntent;
    SygicMain.getInstance().SetArguments(paramIntent);
  }
  
  public void onCreate()
  {
    if (!this.mbIsLabrary) {
      FlurryLogging.onCreate();
    }
    startMirrorLink();
  }
  
  public void onDestroy()
  {
    if (!this.mbIsLabrary) {
      FlurryLogging.onDestroy(this.mCtx);
    }
    stopMirrorLink();
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
    SygicMain.getInstance();
    paramString1 = SygicMain.getFeature().getActivity();
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
  
  public void setBluetoothSettings(int paramInt)
  {
    SettingsShared.getInstance().setInt(SettingsShared.KeyName.BluetoothHfpDelay, paramInt);
  }
  
  public void setFullscreen(boolean paramBoolean)
  {
    SygicPreferences.setFullscreen(this.mCtx, paramBoolean);
  }
  
  public void setRotationLock(boolean paramBoolean)
  {
    int j = -1;
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
      SygicMain.getInstance();
      localObject = SygicMain.getFeature().getActivity();
      if (localObject != null) {
        ((Activity)localObject).setRequestedOrientation(i);
      }
      return;
      i = 1;
      if (((DisplayMetrics)localObject).widthPixels > ((DisplayMetrics)localObject).heightPixels)
      {
        i = 0;
        continue;
        j = 0;
        i = j;
        if (((DisplayMetrics)localObject).widthPixels < ((DisplayMetrics)localObject).heightPixels)
        {
          i = j;
          if (Utils.getAndroidVersion() >= 9)
          {
            i = 9;
            continue;
            i = j;
            if (Utils.getAndroidVersion() >= 9)
            {
              i = 9;
              if (((DisplayMetrics)localObject).widthPixels > ((DisplayMetrics)localObject).heightPixels)
              {
                i = 8;
                continue;
                i = j;
                if (Utils.getAndroidVersion() >= 9)
                {
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
    SygicMain.getInstance();
    Activity localActivity = SygicMain.getFeature().getActivity();
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
}
