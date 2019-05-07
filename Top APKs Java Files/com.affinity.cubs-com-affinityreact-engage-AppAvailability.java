package com.affinityreact.engage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import com.affinityreact.AffinityBaseModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import java.util.List;
import javax.annotation.Nullable;

public class AppAvailability
  extends AffinityBaseModule
{
  private static final String TAG = "AppAvailability";
  
  public AppAvailability(ReactApplicationContext paramReactApplicationContext, Activity paramActivity)
  {
    super(paramReactApplicationContext, paramActivity);
  }
  
  private Boolean appInstalled(String paramString)
  {
    PackageManager localPackageManager = getActivity().getApplicationContext().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return Boolean.valueOf(true);
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return Boolean.valueOf(false);
  }
  
  @ReactMethod
  public void checkInstalled(String paramString, Callback paramCallback1, @Nullable Callback paramCallback2)
  {
    if (appInstalled(paramString).booleanValue())
    {
      paramCallback1.invoke(new Object[0]);
      return;
    }
    invoke(paramCallback2, new Object[0]);
  }
  
  public String getName()
  {
    return "AppAvailabilityAndroid";
  }
  
  @ReactMethod
  public void launchApp(String paramString, @Nullable Callback paramCallback1, @Nullable Callback paramCallback2)
  {
    Context localContext = getActivity().getApplicationContext();
    Intent localIntent = localContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if ((appInstalled(paramString).booleanValue()) && (localIntent != null))
    {
      invoke(paramCallback1, new Object[0]);
      new Handler().postDelayed(new AppAvailability.1(this, localContext, localIntent), 250L);
      return;
    }
    invoke(paramCallback2, new Object[0]);
  }
  
  @ReactMethod
  public void listApps(Callback paramCallback)
  {
    PackageManager localPackageManager = getActivity().getApplicationContext().getPackageManager();
    WritableNativeArray localWritableNativeArray = new WritableNativeArray();
    List localList = localPackageManager.getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        WritableNativeMap localWritableNativeMap = new WritableNativeMap();
        localWritableNativeMap.putString("appName", localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
        localWritableNativeMap.putString("packageName", localPackageInfo.packageName);
        localWritableNativeMap.putString("versionName", localPackageInfo.versionName);
        localWritableNativeMap.putInt("versionCode", localPackageInfo.versionCode);
        localWritableNativeArray.pushMap(localWritableNativeMap);
      }
      i += 1;
    }
    paramCallback.invoke(new Object[] { localWritableNativeArray });
  }
}
