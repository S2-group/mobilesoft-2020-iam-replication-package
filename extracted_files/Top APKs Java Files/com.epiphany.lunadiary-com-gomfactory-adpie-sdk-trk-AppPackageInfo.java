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
import com.gomfactory.adpie.sdk.common.DataKeys;
import com.gomfactory.adpie.sdk.network.NetworkServiceManager;
import com.gomfactory.adpie.sdk.pref.Preference;
import com.gomfactory.adpie.sdk.util.AdPieLog;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppPackageInfo
{
  public static final String TAG = "AppPackageInfo";
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
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("https://");
      ((StringBuilder)localObject).append(this.mPkgUrl);
      localAdRequest.setRequestURL(((StringBuilder)localObject).toString());
      localAdRequest.setAppID(AdPieSDK.getInstance().getAppId());
      localObject = Uri.parse(localAdRequest.toString()).buildUpon();
      ((Uri.Builder)localObject).appendQueryParameter("apps", paramString);
      ((Uri.Builder)localObject).appendQueryParameter("accessdate", this.mAccessDate);
      paramString = new Handler(Looper.getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          int i = paramAnonymousMessage.what;
          if (i != -1)
          {
            if (i != 200) {
              return;
            }
            if (AdPieSDK.getInstance().getConfiguration().isAdpieSdkLog()) {
              AdPieLog.d(AppPackageInfo.TAG, "HTTP Connection : Success");
            }
            try
            {
              Preference.putString(AppPackageInfo.this.mContext, DataKeys.getKeyWithMedia("PACKAGE_ACCESS_DATE", AdPieSDK.getInstance().getAppId()), AppPackageInfo.this.mAccessDate);
              return;
            }
            catch (Exception paramAnonymousMessage)
            {
              AdPieLog.e(AppPackageInfo.TAG, paramAnonymousMessage);
              return;
            }
          }
          if (AdPieSDK.getInstance().getConfiguration().isAdpieSdkLog())
          {
            String str = AppPackageInfo.TAG;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("HTTP Connection : ERROR ");
            if (paramAnonymousMessage.obj != null) {
              paramAnonymousMessage = paramAnonymousMessage.obj.toString();
            } else {
              paramAnonymousMessage = "";
            }
            localStringBuilder.append(paramAnonymousMessage);
            AdPieLog.d(str, localStringBuilder.toString());
          }
        }
      };
      NetworkServiceManager.getInstance().post(localAdRequest.getRequestURL(), ((Uri.Builder)localObject).build().getEncodedQuery(), paramString);
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
      Object localObject1 = localList.iterator();
      Object localObject2;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        Object localObject3 = ((PackageInfo)localObject2).applicationInfo;
        if ((((ApplicationInfo)localObject3).flags & 0x1) != 0)
        {
          int i = ((ApplicationInfo)localObject3).flags;
        }
        else
        {
          localObject3 = new JSONObject();
          ((JSONObject)localObject3).put("bundleid", ((PackageInfo)localObject2).packageName);
          ((JSONObject)localObject3).put("appversion", ((PackageInfo)localObject2).versionName);
          ((JSONObject)localObject3).put("install_time", ((PackageInfo)localObject2).firstInstallTime);
          localJSONArray.put(localObject3);
        }
      }
      if (AdPieSDK.getInstance().getConfiguration().isAdpieSdkLog())
      {
        localObject1 = TAG;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("package count : ");
        ((StringBuilder)localObject2).append(localList.size());
        ((StringBuilder)localObject2).append(", appList count : ");
        ((StringBuilder)localObject2).append(localJSONArray.length());
        AdPieLog.d((String)localObject1, ((StringBuilder)localObject2).toString());
      }
      if (localJSONArray.length() > 0)
      {
        send(localJSONArray.toString());
        return;
      }
    }
    catch (Exception localException)
    {
      if (AdPieSDK.getInstance().getConfiguration().isAdpieSdkLog())
      {
        AdPieLog.e(TAG, localException);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      if (AdPieSDK.getInstance().getConfiguration().isAdpieSdkLog()) {
        AdPieLog.e(TAG, localJSONException);
      }
    }
  }
}
