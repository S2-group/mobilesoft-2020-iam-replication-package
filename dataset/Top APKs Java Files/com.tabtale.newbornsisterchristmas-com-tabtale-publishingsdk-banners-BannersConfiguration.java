package com.tabtale.publishingsdk.banners;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.util.Log;
import com.tabtale.publishingsdk.core.AppLifeCycleDelegate;
import com.tabtale.publishingsdk.core.AppLifeCycleResumeState;
import com.tabtale.publishingsdk.core.HttpConnector;
import com.tabtale.publishingsdk.core.PublishingSDKAppInfo;
import com.tabtale.publishingsdk.core.utils.ConfigurationFetcherHelper;
import com.tabtale.publishingsdk.core.utils.PublishingSDKFileUtils;
import com.tabtale.publishingsdk.services.ConfigurationFetcherDelegate;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BannersConfiguration
  extends AppLifeCycleDelegate
  implements ConfigurationFetcherDelegate
{
  public static final String BANNERS_CONFIG_ADS_FOR_CHILD_DIRECTED_TREATMENT = "adsForChildDirectedTreatment";
  public static final String BANNERS_CONFIG_AD_DISPLAY_TIME = "adDisplayTime";
  public static final String BANNERS_CONFIG_BANNERS_PROVIDERS = "bannersProviders";
  public static final String BANNERS_CONFIG_BANNERS_PROVIDERS_PROVIDER = "provider";
  public static final String BANNERS_CONFIG_BANNER_KEY = "adKey";
  public static final String BANNERS_CONFIG_BLOCKING_ADS_VIEW_COLOR = "blockingAdsViewColor";
  public static final String BANNERS_CONFIG_BLOCKING_ADS_VIEW_MARGIN = "blockingAdsViewMargin";
  public static final String BANNERS_CONFIG_BUCKET = "bucket";
  public static final String BANNERS_CONFIG_CONFIG_PATH = "configPath";
  public static final String BANNERS_CONFIG_DEFAULT_HOUSE_ADS_SERVER_DOMAIN = "houseads.ttpsdk.info";
  public static final String BANNERS_CONFIG_FILE_NAME = "banners.json";
  public static final String BANNERS_CONFIG_HOUSE_ADS_CRC = "houseAdsCrc32.txt";
  public static final String BANNERS_CONFIG_HOUSE_ADS_FILE_NAME = "houseAds.json";
  public static final String BANNERS_CONFIG_HOUSE_ADS_FILE_NEW_NAME = "houseAds_new.json";
  public static final String BANNERS_CONFIG_HOUSE_ADS_MODE = "houseAdsMode";
  public static final String BANNERS_CONFIG_HOUSE_ADS_SERVER_DOMAIN = "houseAdsServerDomain";
  public static final String BANNERS_CONFIG_JSON_APPLICATION_ID = "applicationId";
  public static final String BANNERS_CONFIG_JSON_APPLICATION_NAME = "applicationName";
  public static final String BANNERS_CONFIG_JSON_APP_URL = "appUrl";
  public static final String BANNERS_CONFIG_JSON_BUNDLE_ID = "bundleId";
  public static final String BANNERS_CONFIG_JSON_INSTALLED = "installed";
  public static final String BANNERS_CONFIG_JSON_THUMBNAIL = "thumbnail";
  public static final String BANNERS_CONFIG_LANDSCAPE = "landscape";
  public static final String BANNERS_CONFIG_SCALE_SCENE = "scaleScene";
  public static final String BANNERS_CONFIG_SERVER_DOMAIN = "serverDomain";
  public static final String BANNERS_CONFIG_SHOW_ADS_AT_TOP = "showAdsAtTop";
  public static final String BANNERS_CONFIG_SHOW_BANNER_ADS = "showBannerAds";
  public static final String BANNERS_CONFIG_SHOW_BLOCKING_ADS_VIEW = "showBlockingAdsView";
  public static final String BANNERS_CONFIG_SPECIFIC_CONFIG_PATH = "specificConfigPath";
  private final String TAG = BannersConfiguration.class.getSimpleName();
  private PublishingSDKAppInfo mAppInfo;
  private ConfigurationFetcherHelper mConfigurationFetcher;
  private boolean mConnectivity;
  private BannersConfigurationDelegate mDelegate;
  private PublishingSDKFileUtils mFileUtils;
  private String mLanguage;
  private String mOrientation;
  private List<PackageInfo> mPacks;
  private AppLifeCycleResumeState mPendingUpdate;
  private boolean mStartup = true;
  private String mStore;
  
  public BannersConfiguration(Map<String, String> paramMap, PublishingSDKAppInfo paramPublishingSDKAppInfo, String paramString1, String paramString2, String paramString3, BannersConfigurationDelegate paramBannersConfigurationDelegate)
  {
    this.mAppInfo = paramPublishingSDKAppInfo;
    this.mDelegate = paramBannersConfigurationDelegate;
    this.mStore = paramString1;
    this.mLanguage = paramString2;
    this.mOrientation = paramString3;
    this.mFileUtils = new PublishingSDKFileUtils();
    this.mConfigurationFetcher = new ConfigurationFetcherHelper(paramMap, "banners", this);
    prepareDefaultHouseAds();
  }
  
  private JSONArray createJsonArray(String paramString)
  {
    try
    {
      paramString = new JSONArray(paramString);
      return paramString;
    }
    catch (JSONException paramString)
    {
      Log.e(this.TAG, "failed to parse json, exception: " + paramString.getMessage());
    }
    return null;
  }
  
  private JSONObject createJsonObject(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      return paramString;
    }
    catch (JSONException paramString)
    {
      Log.e(this.TAG, "failed to parse json, exception: " + paramString.getMessage());
    }
    return null;
  }
  
  private Set<String> getInstalledPackages()
  {
    try
    {
      this.mPacks = getPackageManager().getInstalledPackages(0);
      HashSet localHashSet = new HashSet();
      int i = 0;
      while (i < this.mPacks.size())
      {
        localHashSet.add(((PackageInfo)this.mPacks.get(i)).packageName);
        i += 1;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.d(this.TAG, "refreshInstalledPackages Exception: " + localException.getMessage());
      }
      return localException;
    }
  }
  
  private boolean getThumbnail(HttpConnector paramHttpConnector, String paramString1, JSONObject paramJSONObject, String paramString2)
  {
    paramJSONObject = paramString2 + "/" + paramString1.substring(paramString1.lastIndexOf('/') + 1);
    if (this.mFileUtils.isFileExist(paramJSONObject)) {}
    while (paramHttpConnector.startDownload(paramString1, paramString2)) {
      return true;
    }
    Log.e(this.TAG, "BannersConfiguration getThumbnail failed to download thumbnail: " + paramString1);
    return false;
  }
  
  private boolean loadHouseAdsConfigurationFromServer()
  {
    Object localObject2 = getString("houseAdsServerDomain");
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!((String)localObject2).isEmpty()) {}
    }
    else
    {
      localObject1 = "houseads.ttpsdk.info";
    }
    Object localObject4 = this.mAppInfo.getAppId();
    localObject2 = new HttpConnector(null);
    Object localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append("http://").append((String)localObject1).append("/houseads/?store=").append(this.mStore).append("&orientation=").append(this.mOrientation).append("&language=").append(this.mLanguage).append("&bundleId=").append((String)localObject4);
    localObject4 = ((HttpConnector)localObject2).startDownload(((StringBuilder)localObject3).toString());
    if (localObject4 == null)
    {
      Log.e(this.TAG, "BannersConfiguration loadHouseAdsConfigurationFromServer failed to download house ads configuration from server, url:" + localObject3);
      return false;
    }
    long l = this.mFileUtils.calcCRC((String)localObject4);
    Object localObject1 = this.mAppInfo.getCacheDir() + "/ttpsdk/houseAds";
    localObject3 = (String)localObject1 + "/" + "houseAdsCrc32.txt";
    if (this.mFileUtils.isFileExist((String)localObject3))
    {
      localObject5 = this.mFileUtils.getStringFromFile((String)localObject3);
      if ((localObject5 != null) && (!((String)localObject5).isEmpty()) && (l == Long.parseLong((String)localObject5)))
      {
        Log.d(this.TAG, "BannersConfiguration loadHouseAdsConfigurationFromServer house ads configuration wasn't changed, no need to update");
        return false;
      }
    }
    Object localObject5 = createJsonArray((String)localObject4);
    if (localObject5 == null)
    {
      Log.e(this.TAG, "BannersConfiguration loadHouseAdsConfigurationFromServer failed to parse configuration house ads from server");
      return false;
    }
    for (;;)
    {
      int i;
      try
      {
        localObject4 = new JSONArray();
        Set localSet = getInstalledPackages();
        i = 0;
        if (i < ((JSONArray)localObject5).length())
        {
          JSONObject localJSONObject1 = ((JSONArray)localObject5).getJSONObject(i);
          String str1 = localJSONObject1.getString("thumbnail");
          if (!getThumbnail((HttpConnector)localObject2, str1, localJSONObject1, (String)localObject1)) {
            break label683;
          }
          JSONObject localJSONObject2 = new JSONObject();
          String str2 = localJSONObject1.getString("bundleId");
          localJSONObject2.put("bundleId", str2);
          localJSONObject2.put("installed", localSet.contains(str2));
          localJSONObject2.put("thumbnail", str1.substring(str1.lastIndexOf('/') + 1));
          localJSONObject2.put("appUrl", localJSONObject1.getString("appUrl"));
          localJSONObject2.put("applicationId", localJSONObject1.getString("applicationId"));
          localJSONObject2.put("applicationName", localJSONObject1.getString("applicationName"));
          ((JSONArray)localObject4).put(localJSONObject2);
        }
      }
      catch (JSONException localJSONException1)
      {
        Log.e(this.TAG, "BannersConfiguration loadHouseAdsConfigurationFromServer failed to format house ads json, exception: " + localJSONException1.getMessage());
        return false;
      }
      if (((JSONArray)localObject4).length() <= 0)
      {
        Log.e(this.TAG, "BannersConfiguration loadHouseAdsConfigurationFromServer failed to download all images");
        return false;
      }
      localObject2 = getJsonData();
      if (localObject2 == null) {
        return false;
      }
      ((JSONObject)localObject2).remove("apps");
      try
      {
        ((JSONObject)localObject2).put("apps", localObject4);
        this.mFileUtils.saveStringToFile(localJSONException1 + "/" + "houseAds_new.json", ((JSONObject)localObject2).toString());
        this.mFileUtils.saveStringToFile((String)localObject3, Long.toString(l));
        return true;
      }
      catch (JSONException localJSONException2)
      {
        Log.e(this.TAG, "BannersConfiguration loadHouseAdsConfigurationFromServer failed to format house ads json, exception: " + localJSONException2.getMessage());
        return false;
      }
      label683:
      i += 1;
    }
  }
  
  private void prepareDefaultHouseAds()
  {
    int i = 0;
    String str2 = this.mAppInfo.getCacheDir() + "/ttpsdk/houseAds";
    if (this.mFileUtils.isFileExist(str2)) {
      return;
    }
    AssetManager localAssetManager = this.mAppInfo.getActivity().getAssets();
    Object localObject1 = null;
    try
    {
      String[] arrayOfString = localAssetManager.list("houseAds");
      localObject1 = arrayOfString;
    }
    catch (IOException localIOException1)
    {
      for (;;)
      {
        Log.e(this.TAG, "BannersConfiguration prepareDefaultHouseAds faild to get AssetManager, exception: " + localIOException1.getMessage());
      }
      this.mFileUtils.makeDir(str2);
      j = localObject1.length;
    }
    if (localObject1.length <= 0)
    {
      Log.e(this.TAG, "BannersConfiguration prepareDefaultHouseAds the directory 'houseAds' doens not exist in the bundle's resources");
      return;
    }
    int j;
    label140:
    Object localObject3;
    if (i < j)
    {
      String str1 = localObject1[i];
      Object localObject2 = "houseAds/" + str1;
      localObject3 = str2 + "/" + str1;
      try
      {
        localObject2 = localAssetManager.open((String)localObject2);
        localObject3 = new FileOutputStream((String)localObject3);
        byte[] arrayOfByte = new byte['က'];
        for (;;)
        {
          int k = ((InputStream)localObject2).read(arrayOfByte);
          if (k == -1) {
            break;
          }
          ((OutputStream)localObject3).write(arrayOfByte, 0, k);
        }
        i += 1;
      }
      catch (IOException localIOException2)
      {
        Log.e(this.TAG, "BannersConfiguration prepareDefaultHouseAds faild to copy the file '" + str1 + "'from resources to houseAds directory, exception: " + localIOException2.getMessage());
      }
    }
    for (;;)
    {
      break label140;
      break;
      ((OutputStream)localObject3).close();
    }
  }
  
  private void updateInstallationStatusForHouseAdsConfiguration()
  {
    int j = 0;
    JSONObject localJSONObject1 = getJsonData();
    if (localJSONObject1 == null) {
      return;
    }
    Object localObject = getInstalledPackages();
    JSONArray localJSONArray;
    try
    {
      localJSONArray = localJSONObject1.getJSONArray("apps");
      if (!localJSONObject1.has("apps"))
      {
        Log.e(this.TAG, "PSDKBannersConfiguration updateInstallationStatusForHouseAdsConfiguration failed - app list is empty.");
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      Log.e(this.TAG, "BannersConfiguration updateInstallationStatusForHouseAdsConfiguration failed to save modified house ads configuration");
      return;
    }
    int i = 0;
    for (;;)
    {
      if (i < localJSONArray.length())
      {
        JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
        boolean bool = ((Set)localObject).contains(localJSONObject2.getString("bundleId"));
        if (bool != localJSONObject2.getBoolean("installed"))
        {
          localJSONObject2.put("installed", bool);
          j = 1;
        }
      }
      else
      {
        if (j == 0) {
          break;
        }
        localObject = String.format("%s/ttpsdk/houseAds/%s", new Object[] { this.mAppInfo.getCacheDir(), "houseAds.json" });
        this.mFileUtils.saveStringToFile((String)localObject, localJSONException.toString());
        return;
      }
      i += 1;
    }
  }
  
  public boolean getBool(String paramString, boolean paramBoolean)
  {
    paramString = getString(paramString);
    if (paramString != null)
    {
      if (paramString.equalsIgnoreCase("yes")) {}
      do
      {
        return true;
        if (!paramString.matches("[0-9]+")) {
          break;
        }
      } while (Integer.parseInt(paramString) != 0);
      return false;
      return false;
    }
    return paramBoolean;
  }
  
  public float getFloat(String paramString, float paramFloat)
  {
    paramString = getString(paramString);
    if (paramString != null) {
      paramFloat = Float.parseFloat(paramString);
    }
    return paramFloat;
  }
  
  public int getInt(String paramString, int paramInt)
  {
    paramString = getString(paramString);
    if (paramString != null) {
      paramInt = Integer.parseInt(paramString);
    }
    return paramInt;
  }
  
  public JSONObject getJsonData()
  {
    Object localObject = String.format("%s/ttpsdk/houseAds/%s", new Object[] { this.mAppInfo.getCacheDir(), "houseAds.json" });
    if (!this.mFileUtils.isFileExist((String)localObject))
    {
      Log.e(this.TAG, "PSDKBannersConfiguration getJsonData failed, file: " + (String)localObject + " does not exist");
      return null;
    }
    localObject = this.mFileUtils.getStringFromFile((String)localObject);
    if ((localObject == null) || (((String)localObject).isEmpty()))
    {
      Log.e(this.TAG, "BannersConfiguration getJsonData failed to read house ads configuration");
      return null;
    }
    localObject = createJsonObject((String)localObject);
    if (localObject == null)
    {
      Log.e(this.TAG, "BannersConfiguration getJsonData failed to parse house ads configuration");
      return null;
    }
    return localObject;
  }
  
  protected PackageManager getPackageManager()
  {
    return this.mAppInfo.getActivity().getPackageManager();
  }
  
  public String getString(String paramString)
  {
    return this.mConfigurationFetcher.getString(paramString);
  }
  
  public String getString(String paramString1, String paramString2)
  {
    paramString1 = getString(paramString1);
    if (paramString1 != null) {
      paramString2 = paramString1;
    }
    return paramString2;
  }
  
  public void onConfigurationFetched(boolean paramBoolean)
  {
    if ((paramBoolean) || (this.mStartup))
    {
      this.mDelegate.onConfigurationUpdate(this.mStartup);
      this.mStartup = false;
    }
  }
  
  public void onConnectivityStateChanged(boolean paramBoolean)
  {
    this.mConnectivity = paramBoolean;
    if ((this.mConnectivity) && (this.mPendingUpdate != null)) {
      new Thread(new Runnable()
      {
        public void run()
        {
          BannersConfiguration.this.onResume(BannersConfiguration.this.mPendingUpdate);
          BannersConfiguration.access$002(BannersConfiguration.this, null);
        }
      }).start();
    }
  }
  
  public void onResume(AppLifeCycleResumeState paramAppLifeCycleResumeState)
  {
    if (this.mStartup)
    {
      this.mDelegate.onConfigurationUpdate(true);
      this.mStartup = false;
    }
    if ((paramAppLifeCycleResumeState == AppLifeCycleResumeState.ALCRS_NEW_SESSION) || (paramAppLifeCycleResumeState == AppLifeCycleResumeState.ALCRS_RESTART_APP)) {
      if (this.mConnectivity)
      {
        updateInstallationStatusForHouseAdsConfiguration();
        loadHouseAdsConfigurationFromServer();
      }
    }
    do
    {
      return;
      this.mPendingUpdate = paramAppLifeCycleResumeState;
      return;
      if (this.mConnectivity)
      {
        updateInstallationStatusForHouseAdsConfiguration();
        return;
      }
    } while (this.mPendingUpdate != null);
    this.mPendingUpdate = paramAppLifeCycleResumeState;
  }
  
  public void setLanguage(String paramString)
  {
    this.mLanguage = paramString;
    if (this.mConnectivity) {
      new Thread(new Runnable()
      {
        public void run()
        {
          BannersConfiguration.this.onResume(AppLifeCycleResumeState.ALCRS_NEW_SESSION);
        }
      }).start();
    }
  }
}
