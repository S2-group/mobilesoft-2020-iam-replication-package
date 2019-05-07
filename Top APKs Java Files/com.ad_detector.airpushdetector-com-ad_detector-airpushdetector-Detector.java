package com.ad_detector.airpushdetector;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Detector
{
  private static final AdProvider[] AD_PROVIDERS = { new AdProvider("AirPush (version 4 or less) Notification Ads", "com.airpush."), new RegexAdProvider("Airpush Notification Ads", "com\\.[a-zA-Z]{8,9}\\.[a-zA-Z]{8,9}[\\d]{6,7}\\..*"), new AdProvider("LeadBolt Notification Ads", "com.LeadBolt."), new AdProvider("Appenda Notification Ads", "com.appenda."), new AdProvider("IAC Notification Ads", "com.iac.notification."), new AdProvider("TapIt (Old Version) Notification Ads", "com.tapit.adview.notif."), new AdProvider("Moolah Media Notification Ads", "com.adnotify."), new AdProvider("SendDroid Notification Ads", "com.senddroid."), new AdProvider("AppBucks Notification Ads", "com.appbucks.sdk."), new AdProvider("Kuguo Notification Ads", "cn.kuguo."), new AdProvider("Applovin' Notification Ads", "com.applovin."), new AdProvider("UnityAds Popup Ad Or Banner", "com.google.unity.ads."), new AdProvider("Startapp Popup Ad Or Banner", "com.startapp.") };
  private static final String[] PACKAGE_WHITELIST = { "alias.", "com.actionbarsherlock.", "com.android.vending", "com.google.android.gms", "com.amazon.", "com.android.", "com.clockworkmod.", "com.cooliris.", "com.crittercism", "com.facebook.", "com.google.", "com.moolahmedia.", "com.openfeint.", "com.paypal.", "com.phonegap.", "com.soundhound.", "org.acra.", "org.openintents." };
  private static final String TAG = "AirPushDetector";
  
  Detector() {}
  
  static AdSource detectAds(PackageInfo paramPackageInfo, PackageItemInfo[] paramArrayOfPackageItemInfo, String paramString, Set<String> paramSet)
  {
    if (paramArrayOfPackageItemInfo == null) {
      return null;
    }
    int k = paramArrayOfPackageItemInfo.length;
    int i = 0;
    while (i < k)
    {
      PackageItemInfo localPackageItemInfo = paramArrayOfPackageItemInfo[i];
      if (localPackageItemInfo != null)
      {
        AdProvider[] arrayOfAdProvider = AD_PROVIDERS;
        int m = arrayOfAdProvider.length;
        int j = 0;
        while (j < m)
        {
          AdProvider localAdProvider = arrayOfAdProvider[j];
          if (localAdProvider.matches(localPackageItemInfo))
          {
            paramArrayOfPackageItemInfo = new StringBuilder();
            paramArrayOfPackageItemInfo.append("Detected ad framework ");
            paramArrayOfPackageItemInfo.append(localAdProvider.friendlyName);
            paramArrayOfPackageItemInfo.append(" in package ");
            paramArrayOfPackageItemInfo.append(paramPackageInfo.packageName);
            paramArrayOfPackageItemInfo.append(" as ");
            paramArrayOfPackageItemInfo.append(paramString);
            paramArrayOfPackageItemInfo.append(" ");
            paramArrayOfPackageItemInfo.append(localPackageItemInfo.name);
            Log.i("AirPushDetector", paramArrayOfPackageItemInfo.toString());
            return new AdSource(paramPackageInfo, localPackageItemInfo, localAdProvider);
          }
          j += 1;
        }
        if (isSuspicious(localPackageItemInfo, paramPackageInfo)) {
          paramSet.add(getPackagePrefix(localPackageItemInfo.name));
        }
      }
      i += 1;
    }
    return null;
  }
  
  private static String getPackagePrefix(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    List localList = Arrays.asList(new String[] { "com", "org", "net" });
    int i = 0;
    int k;
    for (int j = 0; i < 2; j = k)
    {
      k = paramString.indexOf('.', j + 1);
      if (k == -1) {
        return paramString;
      }
      if (!localList.contains(paramString.substring(j, k))) {
        return paramString.substring(0, k + 1);
      }
      i += 1;
    }
    return paramString.substring(0, j + 1);
  }
  
  private static boolean isSuspicious(PackageItemInfo paramPackageItemInfo, PackageInfo paramPackageInfo)
  {
    if (paramPackageItemInfo.name.startsWith(getPackagePrefix(paramPackageInfo.packageName))) {
      return false;
    }
    paramPackageInfo = PACKAGE_WHITELIST;
    int j = paramPackageInfo.length;
    int i = 0;
    while (i < j)
    {
      String str = paramPackageInfo[i];
      if (paramPackageItemInfo.name.startsWith(str)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static class AdProvider
  {
    String friendlyName;
    String packagePrefix;
    
    AdProvider(String paramString1, String paramString2)
    {
      this.friendlyName = paramString1;
      this.packagePrefix = paramString2.toLowerCase(Locale.US);
    }
    
    public boolean matches(PackageItemInfo paramPackageItemInfo)
    {
      return paramPackageItemInfo.name.toLowerCase(Locale.US).startsWith(this.packagePrefix);
    }
  }
  
  static class AdSource
  {
    PackageItemInfo adComponentInfo;
    Detector.AdProvider adProvider;
    PackageInfo packageInfo;
    
    AdSource(PackageInfo paramPackageInfo, PackageItemInfo paramPackageItemInfo, Detector.AdProvider paramAdProvider)
    {
      this.packageInfo = paramPackageInfo;
      this.adComponentInfo = paramPackageItemInfo;
      this.adProvider = paramAdProvider;
    }
  }
  
  static class AdSourcesInfo
  {
    List<Detector.AdSource> adSources = new ArrayList();
    String detectionLog;
    
    AdSourcesInfo() {}
  }
  
  public static class DetectAsyncTask
    extends AsyncTask<Void, Integer, Detector.AdSourcesInfo>
  {
    private final Callbacks mCallbacks;
    private final PackageManager mPackageManager;
    
    DetectAsyncTask(PackageManager paramPackageManager, Callbacks paramCallbacks)
    {
      this.mPackageManager = paramPackageManager;
      this.mCallbacks = paramCallbacks;
    }
    
    protected Detector.AdSourcesInfo doInBackground(Void... paramVarArgs)
    {
      Detector.AdSourcesInfo localAdSourcesInfo = new Detector.AdSourcesInfo();
      StringBuilder localStringBuilder = new StringBuilder();
      List localList = this.mPackageManager.getInstalledApplications(0);
      int j = localList.size();
      int i = 0;
      while (i < j)
      {
        if (isCancelled()) {
          return null;
        }
        publishProgress(new Integer[] { Integer.valueOf(i), Integer.valueOf(j) });
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
        HashSet localHashSet = new HashSet();
        try
        {
          PackageInfo localPackageInfo = this.mPackageManager.getPackageInfo(localApplicationInfo.packageName, 7);
          paramVarArgs = new StringBuilder();
          paramVarArgs.append("Scanning package ");
          paramVarArgs.append(localPackageInfo.packageName);
          Log.v("AirPushDetector", paramVarArgs.toString());
          Object localObject = Detector.detectAds(localPackageInfo, localPackageInfo.activities, "ACTIVITY", localHashSet);
          paramVarArgs = (Void[])localObject;
          if (localObject == null) {
            paramVarArgs = Detector.detectAds(localPackageInfo, localPackageInfo.receivers, "RECEIVER", localHashSet);
          }
          localObject = paramVarArgs;
          if (paramVarArgs == null) {
            localObject = Detector.detectAds(localPackageInfo, localPackageInfo.services, "SERVICE", localHashSet);
          }
          if ((localObject == null) && (localHashSet.isEmpty())) {
            break label341;
          }
          localStringBuilder.append('[');
          localStringBuilder.append(localApplicationInfo.packageName);
          localStringBuilder.append(": ");
          if (localObject != null)
          {
            localAdSourcesInfo.adSources.add(localObject);
            localStringBuilder.append("MATCH=");
            localStringBuilder.append(((Detector.AdSource)localObject).adProvider.friendlyName);
            localStringBuilder.append(" ");
          }
          if (!localHashSet.isEmpty()) {
            localStringBuilder.append(TextUtils.join(", ", localHashSet));
          }
          localStringBuilder.append("]\n");
        }
        catch (PackageManager.NameNotFoundException paramVarArgs)
        {
          for (;;) {}
        }
        Log.e("AirPushDetector", "Managed to not find a package we know about");
        label341:
        i += 1;
      }
      localAdSourcesInfo.detectionLog = localStringBuilder.toString();
      return localAdSourcesInfo;
    }
    
    protected void onPostExecute(Detector.AdSourcesInfo paramAdSourcesInfo)
    {
      this.mCallbacks.onTaskFinished(paramAdSourcesInfo);
    }
    
    protected void onProgressUpdate(Integer... paramVarArgs)
    {
      this.mCallbacks.onProgressUpdate(paramVarArgs[0].intValue(), paramVarArgs[1].intValue());
    }
    
    public static abstract interface Callbacks
    {
      public abstract void onProgressUpdate(int paramInt1, int paramInt2);
      
      public abstract void onTaskFinished(Detector.AdSourcesInfo paramAdSourcesInfo);
    }
  }
  
  public static class RegexAdProvider
    extends Detector.AdProvider
  {
    private Pattern packageRegex;
    
    RegexAdProvider(String paramString1, String paramString2)
    {
      super("");
      this.packageRegex = Pattern.compile(paramString2);
    }
    
    public boolean matches(PackageItemInfo paramPackageItemInfo)
    {
      return this.packageRegex.matcher(paramPackageItemInfo.name).matches();
    }
  }
}
