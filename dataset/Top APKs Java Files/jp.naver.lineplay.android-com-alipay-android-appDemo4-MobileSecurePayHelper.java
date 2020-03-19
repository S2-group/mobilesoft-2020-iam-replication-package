package com.alipay.android.appDemo4;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import jp.naver.common.android.billing.commons.BillingLog;
import org.json.JSONException;
import org.json.JSONObject;

public class MobileSecurePayHelper
{
  static final String TAG = "MobileSecurePayHelper";
  private static final String TEMP_APK_FILE_NAME = "alipay_msp_update.apk";
  private static BillingLog log = new BillingLog("billing_plugin_alipay");
  Context mContext = null;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      switch (paramAnonymousMessage.what)
      {
      default: 
        return;
      case 2: 
        MobileSecurePayHelper.this.mOnUpdateCompleteListener.onReadyToInstall(new File(MobileSecurePayHelper.this.mContext.getFilesDir(), "alipay_msp_update.apk").getAbsolutePath());
        return;
      case 3: 
        MobileSecurePayHelper.this.mOnUpdateCompleteListener.onAlreadyUpdate();
        return;
      }
      MobileSecurePayHelper.this.mOnUpdateCompleteListener.onError(paramAnonymousMessage.what);
    }
  };
  private OnUpdateCompleteListener mOnUpdateCompleteListener;
  private ProgressDialog mProgress = null;
  
  public MobileSecurePayHelper(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  private static PackageInfo getApkInfo(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo("com.alipay.android.app", 0);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static PackageInfo getApkInfo(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getPackageArchiveInfo(paramString, 128);
  }
  
  public String checkNewUpdate(PackageInfo paramPackageInfo)
  {
    Object localObject = null;
    if (paramPackageInfo != null) {}
    for (paramPackageInfo = paramPackageInfo.versionName;; paramPackageInfo = "0")
    {
      JSONObject localJSONObject = sendCheckNewUpdate(paramPackageInfo);
      paramPackageInfo = localObject;
      try
      {
        if (localJSONObject.getString("needUpdate").equalsIgnoreCase("true")) {
          paramPackageInfo = localJSONObject.getString("updateUrl");
        }
        return paramPackageInfo;
      }
      catch (JSONException paramPackageInfo)
      {
        log.error(paramPackageInfo);
      }
    }
    return null;
  }
  
  public int checkNewUpdateAndDownload(Context paramContext, String paramString)
  {
    Object localObject1 = getApkInfo(paramContext);
    Object localObject2 = null;
    if (localObject1 != null) {}
    JSONObject localJSONObject;
    for (localObject1 = ((PackageInfo)localObject1).versionName;; localObject1 = "0")
    {
      localJSONObject = sendCheckNewUpdate((String)localObject1);
      if (localJSONObject != null) {
        break;
      }
      return 4;
    }
    localObject1 = localObject2;
    try
    {
      if (localJSONObject.getString("needUpdate").equalsIgnoreCase("true")) {
        localObject1 = localJSONObject.getString("updateUrl");
      }
      if (localObject1 == null) {
        break label91;
      }
      if (retrieveApkFromNet(paramContext, (String)localObject1, paramString)) {
        return 2;
      }
    }
    catch (JSONException paramContext)
    {
      log.error(paramContext);
      return 4;
    }
    return 5;
    label91:
    return 3;
  }
  
  public boolean detectMobile_sp()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Message localMessage = new Message();
        localMessage.what = MobileSecurePayHelper.this.checkNewUpdateAndDownload(MobileSecurePayHelper.this.mContext, "alipay_msp_update.apk");
        MobileSecurePayHelper.this.mHandler.sendMessage(localMessage);
      }
    }).start();
    return true;
  }
  
  public boolean isMobile_spExist()
  {
    boolean bool2 = false;
    List localList = this.mContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < localList.size())
      {
        if (((PackageInfo)localList.get(i)).packageName.equalsIgnoreCase("com.alipay.android.app")) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public boolean retrieveApkFromAssets(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getAssets().open(paramString1);
      paramString1 = new File(paramString2);
      paramString1.createNewFile();
      paramString1 = new FileOutputStream(paramString1);
      paramString2 = new byte['Ѐ'];
      for (;;)
      {
        int i = paramContext.read(paramString2);
        if (i <= 0) {
          break;
        }
        paramString1.write(paramString2, 0, i);
      }
      paramString1.close();
    }
    catch (IOException paramContext)
    {
      log.error(paramContext);
      return false;
    }
    paramContext.close();
    return true;
  }
  
  public boolean retrieveApkFromNet(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      NetworkManager localNetworkManager = new NetworkManager(this.mContext);
      bool1 = bool2;
      log.debug("download start");
      bool1 = bool2;
      bool2 = localNetworkManager.urlDownloadToFile(paramContext, paramString1, paramString2);
      bool1 = bool2;
      log.debug("download end");
      return bool2;
    }
    catch (Exception paramContext)
    {
      log.error(paramContext);
    }
    return bool1;
  }
  
  public JSONObject sendCheckNewUpdate(String paramString)
  {
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put("action", "update");
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("platform", "android");
      localJSONObject2.put("version", paramString);
      localJSONObject2.put("partner", "");
      localJSONObject1.put("data", localJSONObject2);
      paramString = sendRequest(localJSONObject1.toString());
      return paramString;
    }
    catch (JSONException paramString)
    {
      log.error(paramString);
    }
    return null;
  }
  
  public JSONObject sendRequest(String paramString)
  {
    NetworkManager localNetworkManager = new NetworkManager(this.mContext);
    localObject = null;
    for (;;)
    {
      try {}catch (Exception paramString)
      {
        log.error(paramString);
        paramString = localObject;
        continue;
      }
      try
      {
        paramString = localNetworkManager.SendAndWaitResponse(paramString, "https://msp.alipay.com/x.htm");
        paramString = new JSONObject(paramString);
        if (paramString != null) {
          log.info(paramString.toString());
        }
        return paramString;
      }
      finally {}
    }
  }
  
  public void setOnInstallCompleteListener(Context paramContext, OnUpdateCompleteListener paramOnUpdateCompleteListener)
  {
    this.mOnUpdateCompleteListener = paramOnUpdateCompleteListener;
  }
  
  public static abstract interface OnUpdateCompleteListener
  {
    public abstract void onAlreadyUpdate();
    
    public abstract void onError(int paramInt);
    
    public abstract void onReadyToInstall(String paramString);
  }
}
