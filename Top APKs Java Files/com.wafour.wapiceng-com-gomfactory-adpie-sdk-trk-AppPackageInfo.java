package com.gomfactory.adpie.sdk.trk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.gomfactory.adpie.sdk.AdPieSDK;
import com.gomfactory.adpie.sdk.common.AdRequest;
import com.gomfactory.adpie.sdk.common.Configuration;
import com.gomfactory.adpie.sdk.network.NetworkServiceManager;
import com.gomfactory.adpie.sdk.pref.Preference;
import com.gomfactory.adpie.sdk.security.Crypto;
import com.gomfactory.adpie.sdk.util.AdPieLog;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppPackageInfo
{
  public static final String TAG = AppPackageInfo.class.getSimpleName();
  private String mAccessDate;
  private Context mContext;
  private String mPkgUrl;
  
  public AppPackageInfo(Context paramContext, String paramString1, String paramString2)
  {
    this.mContext = paramContext;
    this.mPkgUrl = paramString1;
    this.mAccessDate = paramString2;
  }
  
  private void send(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (!TextUtils.isEmpty(this.mPkgUrl)))
    {
      AdRequest localAdRequest = (AdRequest)AdPieSDK.getInstance().getCommonHeader().clone();
      localAdRequest.setRequestURL(this.mPkgUrl);
      localAdRequest.setAppID(AdPieSDK.getInstance().getAppId());
      Uri.Builder localBuilder = Uri.parse(localAdRequest.toString()).buildUpon();
      localBuilder.appendQueryParameter("apps", paramString);
      localBuilder.appendQueryParameter("accessdate", this.mAccessDate);
      paramString = new Handler(Looper.getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          switch (paramAnonymousMessage.what)
          {
          }
          do
          {
            return;
            if (AdPieSDK.getInstance().getConfiguration().isAdpieSdkLog()) {
              AdPieLog.d(AppPackageInfo.TAG, "HTTP Connection : Success");
            }
            try
            {
              Preference.putString(AppPackageInfo.this.mContext, "PACKAGE_ACCESS_DATE", AppPackageInfo.this.mAccessDate);
              return;
            }
            catch (Exception paramAnonymousMessage)
            {
              AdPieLog.e(AppPackageInfo.TAG, paramAnonymousMessage);
              return;
            }
          } while (!AdPieSDK.getInstance().getConfiguration().isAdpieSdkLog());
          String str = AppPackageInfo.TAG;
          StringBuilder localStringBuilder = new StringBuilder().append("HTTP Connection : ERROR ");
          if (paramAnonymousMessage.obj != null) {}
          for (paramAnonymousMessage = paramAnonymousMessage.obj.toString();; paramAnonymousMessage = "")
          {
            AdPieLog.d(str, paramAnonymousMessage);
            return;
          }
        }
      };
      NetworkServiceManager.getInstance().post(localAdRequest.getRequestURL(), localBuilder.build().getQuery(), paramString);
    }
  }
  
  public void sendPackageInfo()
  {
    try
    {
      if (this.mContext == null) {
        return;
      }
      List localList = this.mContext.getPackageManager().getInstalledPackages(128);
      JSONArray localJSONArray = new JSONArray();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        Object localObject = localPackageInfo.applicationInfo;
        if ((((ApplicationInfo)localObject).flags & 0x1) != 0)
        {
          if ((((ApplicationInfo)localObject).flags & 0x80) == 0) {}
        }
        else
        {
          localObject = new JSONObject();
          ((JSONObject)localObject).put("bundleid", localPackageInfo.packageName);
          ((JSONObject)localObject).put("appversion", localPackageInfo.versionName);
          ((JSONObject)localObject).put("install_time", localPackageInfo.firstInstallTime);
          localJSONArray.put(localObject);
        }
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      if (AdPieSDK.getInstance().getConfiguration().isAdpieSdkLog())
      {
        AdPieLog.e(TAG, localJSONException);
        return;
        if (AdPieSDK.getInstance().getConfiguration().isAdpieSdkLog()) {
          AdPieLog.d(TAG, "package count : " + localJSONException.size() + ", appList count : " + localJSONArray.length());
        }
        if (localJSONArray.length() > 0)
        {
          send(Crypto.encrypt(localJSONArray.toString()));
          return;
        }
      }
    }
    catch (Exception localException)
    {
      if (AdPieSDK.getInstance().getConfiguration().isAdpieSdkLog()) {
        AdPieLog.e(TAG, localException);
      }
    }
  }
}
