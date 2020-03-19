package com.tabtale.publishingsdk.monetization.appshelf;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;
import com.tabtale.publishingsdk.core.Analytics;
import com.tabtale.publishingsdk.core.AppLifeCycleDelegate;
import com.tabtale.publishingsdk.core.AppLifeCycleMgr;
import com.tabtale.publishingsdk.core.AppLifeCycleResumeState;
import com.tabtale.publishingsdk.core.HttpConnector;
import com.tabtale.publishingsdk.core.Language;
import com.tabtale.publishingsdk.core.PublishingSDKAppInfo;
import com.tabtale.publishingsdk.core.PublishingSDKErrors;
import com.tabtale.publishingsdk.core.ServiceManager;
import com.tabtale.publishingsdk.core.utils.ConfigurationFetcherHelper;
import com.tabtale.publishingsdk.core.utils.LocalStorage;
import com.tabtale.publishingsdk.core.utils.LocationInternalDelegate;
import com.tabtale.publishingsdk.core.utils.PublishingSDKAppLogger;
import com.tabtale.publishingsdk.core.utils.PublishingSDKFileUtils;
import com.tabtale.publishingsdk.core.utils.Utils;
import com.tabtale.publishingsdk.core.utils.ZipDecompress;
import com.tabtale.publishingsdk.services.AppShelfService;
import com.tabtale.publishingsdk.services.AppshelfDelegate;
import com.tabtale.publishingsdk.services.ConfigurationFetcherDelegate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppShelfServiceImpl
  extends AppLifeCycleDelegate
  implements AppShelfService, LocationInternalDelegate, Language, ConfigurationFetcherDelegate
{
  private static final String DEFAULT_LOCATION_NAME = "default";
  private static final String ENABLE_APPSHELF = "enableAppShelf";
  private static final String FALLBACK_DOMAIN = "http://s3.ttpsdk.info";
  private static final String JSON_ADDITIONAL_RESOURCES = "additionalResources";
  private static final String JSON_ADDITIONAL_RESOURCE_URL = "url";
  private static final String JSON_APPLICATION_NAME = "applicationName";
  private static final String JSON_APPS = "apps";
  private static final String JSON_APP_URL = "appUrl";
  private static final String JSON_BUNDLE_ID = "bundleId";
  private static final String JSON_DESCRIPTION = "description";
  private static final String JSON_EXIST_ON_DISK = "existOnDisk";
  private static final String JSON_INSTALLED = "installed";
  private static final String JSON_IN_HOUSE_APPLICATION_NAME = "inHouseApplicationName";
  private static final String JSON_IN_HOUSE_DESCRIPTION = "inHouseDescription";
  private static final String JSON_IN_HOUSE_THUMBNAIL = "inHouseThumbnail";
  private static final String JSON_NUM_OF_APPS = "numOfApps";
  private static final String JSON_PROMOTION_BOXES = "promotionBoxes";
  private static final String JSON_SKIN_URL = "skinUrl";
  private static final String JSON_THUMBNAIL = "thumbnail";
  private static final String NONE_LOCATION_NAME = "nullLocation";
  private static final String SERVER_DOMAIN = "appshelf.ttpsdk.info";
  private static final String TAG = AppShelfServiceImpl.class.getSimpleName();
  private static Set<String> mReadyLocations = new HashSet();
  private PublishingSDKAppInfo mAppInfo;
  private String mAppShelfDir;
  protected List<AppShelfLocationData> mAppShelfLocationsData = new LinkedList();
  private AppShelfServer mAppShelfServer;
  private AppshelfDelegate mAppshelfdelegate;
  private boolean mClosedByBackButton;
  private ConfigurationFetcherHelper mConfigurationFetcher;
  private BroadcastReceiver mConnReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (Utils.isNetworkAvailable(AppShelfServiceImpl.this.mAppInfo.getActivity()))
      {
        AppShelfServiceImpl.this.mConnectivity = true;
        if (AppShelfServiceImpl.this.mPendingUpdate != null) {
          AppShelfServiceImpl.this.onResume(AppShelfServiceImpl.this.mPendingUpdate);
        }
        return;
      }
      AppShelfServiceImpl.this.mConnectivity = false;
    }
  };
  protected boolean mConnectivity;
  private boolean mDefaultAppShelfExist;
  private LocationInternalDelegate mDelegate;
  private boolean mDownloadDefaultAppShelf;
  private boolean mEnableAppShelf;
  private String mFallbackAppShelfDomain;
  private PublishingSDKFileUtils mFileUtils;
  private String mLanguage;
  private PublishingSDKErrors mLastError;
  protected Map<String, String> mLocations;
  protected boolean mOpenOnNewActivity;
  private String mOrientation;
  private List<PackageInfo> mPacks;
  protected AppLifeCycleResumeState mPendingUpdate;
  private boolean mProcessing;
  private String mServerDomain;
  private boolean mShown = false;
  private String mShownLocation;
  private LocalStorage mStorage;
  private String mStore;
  private String mTmpAppShelfDir;
  private WebViewService mWebViewService;
  
  protected AppShelfServiceImpl(AppLifeCycleMgr paramAppLifeCycleMgr, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, PublishingSDKAppInfo paramPublishingSDKAppInfo, LocationInternalDelegate paramLocationInternalDelegate, AppshelfDelegate paramAppshelfDelegate, boolean paramBoolean)
  {
    this.mStore = paramString1;
    this.mAppInfo = paramPublishingSDKAppInfo;
    this.mLanguage = paramString4;
    this.mDelegate = paramLocationInternalDelegate;
    this.mEnableAppShelf = true;
    this.mAppshelfdelegate = paramAppshelfDelegate;
    this.mOrientation = paramString5;
    this.mPendingUpdate = null;
    this.mOpenOnNewActivity = paramBoolean;
    if (paramString3 != null)
    {
      this.mServerDomain = paramString3;
      if ((paramString2 == null) || (paramString2.isEmpty())) {
        break label297;
      }
    }
    for (;;)
    {
      this.mFallbackAppShelfDomain = paramString2;
      this.mLastError = PublishingSDKErrors.PSDK_NO_ERROR;
      this.mAppShelfDir = (paramPublishingSDKAppInfo.getCacheDir() + "/ttpsdk/appshelf/");
      this.mTmpAppShelfDir = (paramPublishingSDKAppInfo.getCacheDir() + "/ttpsdk/appshelf_tmp/");
      this.mStorage = createLocalStorage();
      this.mAppShelfServer = createAppShelfServer();
      this.mFileUtils = createPublishingSDKFileUtils();
      this.mConfigurationFetcher = new ConfigurationFetcherHelper(null, "appshelf", null);
      paramAppLifeCycleMgr.register(this);
      paramPublishingSDKAppInfo.getActivity().registerReceiver(this.mConnReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
      this.mDefaultAppShelfExist = this.mFileUtils.isFileExist(this.mAppShelfDir + "default" + "/content.json");
      this.mConnectivity = Utils.isNetworkAvailable(paramPublishingSDKAppInfo.getActivity());
      return;
      paramString3 = "appshelf.ttpsdk.info";
      break;
      label297:
      paramString2 = "http://s3.ttpsdk.info";
    }
  }
  
  private void addInHouseAdImageToJson(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    try
    {
      paramJSONObject.put(paramString2, paramString1.replace("appshelf_tmp", "appshelf"));
      return;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }
  
  private void addReadyLocation(String paramString1, String paramString2)
  {
    mReadyLocations.add(paramString1);
    this.mStorage.setString(paramString1, paramString2);
  }
  
  private void calculateAndSendNumberOfInstalledApplications(Set<String> paramSet, JSONObject paramJSONObject, String paramString)
  {
    int k = 0;
    for (;;)
    {
      int i;
      try
      {
        paramJSONObject = paramJSONObject.getJSONObject("promotionBoxes");
        Iterator localIterator = paramJSONObject.keys();
        if (localIterator.hasNext())
        {
          JSONArray localJSONArray = paramJSONObject.getJSONObject((String)localIterator.next()).getJSONArray("apps");
          i = 0;
          j = k;
          k = j;
          if (i >= localJSONArray.length()) {
            continue;
          }
          k = j;
          if (paramSet.contains(localJSONArray.getJSONObject(i).getString("bundleId"))) {
            k = j + 1;
          }
        }
        else
        {
          logApplicationNumber(k, paramString);
          return;
        }
      }
      catch (JSONException paramSet)
      {
        Log.e(TAG, "failed to calculate the number of installed application");
        return;
      }
      i += 1;
      int j = k;
    }
  }
  
  private boolean checkIfToDownloadDefaultAppShelf(String paramString)
  {
    StringBuilder localStringBuilder;
    if ((!this.mDefaultAppShelfExist) && (!this.mDownloadDefaultAppShelf))
    {
      localStringBuilder = new StringBuilder().append(this.mAppShelfDir);
      if (paramString != null) {
        break label124;
      }
    }
    label124:
    for (String str = "nullLocation";; str = paramString)
    {
      str = str + "/content.json";
      if (!this.mFileUtils.isFileExist(str))
      {
        str = this.mAppShelfDir + "default" + "/content.json";
        if (!this.mFileUtils.isFileExist(str)) {
          this.mDownloadDefaultAppShelf = true;
        }
      }
      if (!this.mDownloadDefaultAppShelf) {
        onLocationLoaded(paramString);
      }
      return this.mDownloadDefaultAppShelf;
    }
  }
  
  private boolean downloadAdditionalResources(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      if (!paramJSONObject.has("additionalResources")) {
        return true;
      }
      paramJSONObject = paramJSONObject.getJSONObject("additionalResources");
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str1 = paramJSONObject.getJSONObject((String)localIterator.next()).getString("url");
        HttpConnector localHttpConnector = createHttpConnector();
        String str2 = paramString + "/apps/thumbnails";
        String str3 = str2 + "/" + str1.substring(str1.lastIndexOf('/') + 1);
        if ((!this.mFileUtils.isFileExist(str3)) && (!localHttpConnector.startDownload(str1, str2)))
        {
          logError("downloadAdditionalResources failed to download file: " + str1);
          this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_DOWNLOAD_ADDITIONAL_RESOURCES_ERROR;
          return false;
        }
      }
    }
    catch (JSONException paramJSONObject)
    {
      logError("downloadAdditionalResources " + PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_DOWNLOAD_ADDITIONAL_RESOURCES_ERROR.getDescription() + ", JSONException: " + paramJSONObject.getMessage());
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_DOWNLOAD_ADDITIONAL_RESOURCES_ERROR;
      return false;
    }
    catch (Exception paramJSONObject)
    {
      logError("downloadAdditionalResources " + PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_DOWNLOAD_ADDITIONAL_RESOURCES_ERROR.getDescription() + ", Exception: " + paramJSONObject.getMessage());
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_DOWNLOAD_ADDITIONAL_RESOURCES_ERROR;
      return false;
    }
    return true;
  }
  
  private boolean downloadAppImages(JSONObject paramJSONObject, Set<String> paramSet, String paramString, boolean paramBoolean)
  {
    try
    {
      paramJSONObject = paramJSONObject.getJSONObject("promotionBoxes");
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        Object localObject = paramJSONObject.getJSONObject((String)localIterator.next());
        int i = ((JSONObject)localObject).getInt("numOfApps");
        localObject = ((JSONObject)localObject).getJSONArray("apps");
        if (((JSONArray)localObject).length() <= 0)
        {
          logError("downloadAppImages invalid configuration contain zero applications");
          this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_CONFIGURATION_NUM_OF_APPS_ERROR;
          return false;
        }
        int j = downloadAppsImage((JSONArray)localObject, paramSet, false, paramString, i, paramBoolean);
        if (j == -1)
        {
          logError("downloadAppImages failed to download some resources");
          return false;
        }
        if ((j < i) && (downloadAppsImage((JSONArray)localObject, paramSet, true, paramString, i, paramBoolean) == -1))
        {
          logError("downloadAppImages failed to download some resources");
          return false;
        }
      }
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
    return true;
  }
  
  private boolean downloadAppShelfResources(AppShelfLocationData paramAppShelfLocationData, Set<String> paramSet)
  {
    Object localObject = this.mAppShelfServer.getAppShelfConfiguration(paramAppShelfLocationData.getConfigurationUrl());
    if ((localObject == null) || (((String)localObject).isEmpty()))
    {
      if (checkIfToDownloadDefaultAppShelf(paramAppShelfLocationData.getLocation())) {
        downloadDefaultAppShelf();
      }
      logError("downloadFullAppShelfResources failed to get AppShelf configuration for location: " + paramAppShelfLocationData.getLocationName() + ", configurationUrl: " + paramAppShelfLocationData.getConfigurationUrl());
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_GET_CONFIG_FOR_CONFIGURATION_URLS_ERROR;
    }
    do
    {
      return false;
      localObject = createJsonObject((String)localObject);
      if (localObject == null)
      {
        if (checkIfToDownloadDefaultAppShelf(paramAppShelfLocationData.getLocation())) {
          downloadDefaultAppShelf();
        }
        logError("downloadFullAppShelfResources failed to parse the file content.json: " + paramAppShelfLocationData.getLocationName() + ", configurationUrl: " + paramAppShelfLocationData.getConfigurationUrl());
        this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_PARSE_CONFIGURATION_ERROR;
        return false;
      }
      if (this.mFileUtils.isFileExist(this.mAppShelfDir + paramAppShelfLocationData.getLocationName() + "/content.json")) {
        return downloadDiffAppShelfResources(paramAppShelfLocationData, paramSet, (JSONObject)localObject);
      }
      if (downloadFullAppShelfResources(paramAppShelfLocationData, paramSet, (JSONObject)localObject)) {
        break;
      }
    } while (!checkIfToDownloadDefaultAppShelf(paramAppShelfLocationData.getLocation()));
    downloadDefaultAppShelf();
    return false;
    calculateAndSendNumberOfInstalledApplications(paramSet, (JSONObject)localObject, paramAppShelfLocationData.getLocationName());
    return true;
  }
  
  private void downloadAppShelfsResources()
  {
    int i = 1;
    Set localSet = refreshInstalledPackages();
    Iterator localIterator = this.mAppShelfLocationsData.iterator();
    while (localIterator.hasNext())
    {
      AppShelfLocationData localAppShelfLocationData = (AppShelfLocationData)localIterator.next();
      if (localAppShelfLocationData.isExist())
      {
        addReadyLocation(localAppShelfLocationData.getLocationName(), localAppShelfLocationData.getConfigurationUrl());
        int j = updateInstallationStatus(localAppShelfLocationData, localSet);
        if (j != -1) {
          ServiceManager.instance().getLogger().log("Appshelf", "downloadAppShelfsResources success", 3);
        }
        logApplicationNumber(j, localAppShelfLocationData.getLocationName());
        onLocationLoaded(localAppShelfLocationData.getLocation());
      }
      else if (downloadAppShelfResources(localAppShelfLocationData, localSet))
      {
        addReadyLocation(localAppShelfLocationData.getLocationName(), localAppShelfLocationData.getConfigurationUrl());
        ServiceManager.instance().getLogger().log("Appshelf", "downloadAppShelfsResources success", 3);
        onLocationLoaded(localAppShelfLocationData.getLocation());
        localAppShelfLocationData.setExist();
      }
      else
      {
        i = 0;
        onLocationFailed(localAppShelfLocationData.getLocation(), this.mLastError);
      }
    }
    if (i != 0) {
      this.mFileUtils.removeDir(this.mAppShelfDir + "default");
    }
  }
  
  private int downloadAppsImage(JSONArray paramJSONArray, Set<String> paramSet, boolean paramBoolean1, String paramString, int paramInt, boolean paramBoolean2)
    throws JSONException
  {
    int i = 0;
    int j = 0;
    int k = i;
    JSONObject localJSONObject;
    if (j < paramJSONArray.length())
    {
      k = i;
      if (i < paramInt)
      {
        localJSONObject = paramJSONArray.getJSONObject(j);
        boolean bool = paramSet.contains(localJSONObject.getString("bundleId"));
        localJSONObject.put("installed", bool);
        if (paramBoolean1)
        {
          k = i;
          if (!bool) {}
        }
        else
        {
          if ((paramBoolean1) || (!bool)) {
            break label102;
          }
          k = i;
        }
      }
    }
    for (;;)
    {
      j += 1;
      i = k;
      break;
      label102:
      String str1 = localJSONObject.getString("thumbnail");
      int m = 0;
      k = m;
      if (paramBoolean2)
      {
        String str2 = paramString + "/apps/thumbnails/" + str1.substring(str1.lastIndexOf('/') + 1);
        k = m;
        if (this.mFileUtils.isFileExist(str2))
        {
          addInHouseAdImageToJson(localJSONObject, str2, "thumbnail");
          k = 1;
        }
      }
      if ((k == 0) && (!downloadThumbnail(str1, paramString, localJSONObject)))
      {
        k = -1;
        return k;
      }
      if (!downloadAdditionalResources(localJSONObject, paramString)) {
        return -1;
      }
      if (localJSONObject.has("inHouseThumbnail")) {
        downloadInHouseThumbnail(localJSONObject.getString("inHouseThumbnail"), paramString, localJSONObject);
      }
      k = i + 1;
      localJSONObject.put("existOnDisk", true);
    }
  }
  
  private boolean downloadDefaultAppShelf()
  {
    Object localObject2 = this.mFallbackAppShelfDomain + "/appshelf/" + this.mStore + "/content.json";
    Object localObject1 = localObject2;
    if (((String)localObject2).indexOf("http") != 0) {
      localObject1 = "http://" + (String)localObject2;
    }
    localObject2 = createHttpConnector().startDownload((String)localObject1);
    if ((localObject2 == null) || (((String)localObject2).isEmpty()))
    {
      logError("downloadDefaultAppShelf failed to donwload content.json file for default appShelf");
      return false;
    }
    localObject2 = createJsonObject((String)localObject2);
    if (localObject2 == null)
    {
      logError("downloadDefaultAppShelf failed to parse the file content.json of default appShelf");
      return false;
    }
    this.mDefaultAppShelfExist = downloadFullAppShelfResources(new AppShelfLocationData("default", (String)localObject1, false), refreshInstalledPackages(), (JSONObject)localObject2);
    return this.mDefaultAppShelfExist;
  }
  
  private boolean downloadDiffAppShelfResources(AppShelfLocationData paramAppShelfLocationData, Set<String> paramSet, JSONObject paramJSONObject)
  {
    String str1 = this.mAppShelfDir + paramAppShelfLocationData.getLocationName();
    String str2 = this.mTmpAppShelfDir + paramAppShelfLocationData.getLocationName();
    String str3 = this.mFileUtils.getStringFromFile(str1 + "/content.json");
    if ((str3 == null) || (str3.isEmpty()))
    {
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_READ_LOCAL_CONFIG_FILE_ERROR;
      logError("downloadDiffAppShelfResources: " + this.mLastError.getDescription());
      return false;
    }
    Object localObject = createJsonObject(str3);
    if (paramJSONObject == null)
    {
      logError("downloadDiffAppShelfResources failed to parse the file content.json: " + paramAppShelfLocationData.getLocationName() + ", configurationUrl: " + paramAppShelfLocationData.getConfigurationUrl());
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_PARSE_LOCAL_CONFIGURATION_FILE_ERROR;
      return false;
    }
    str3 = jsonGetString(paramJSONObject, "skinUrl");
    localObject = jsonGetString((JSONObject)localObject, "skinUrl");
    int i = 0;
    if (str3.compareTo((String)localObject) != 0)
    {
      if (!downloadSkin(paramAppShelfLocationData, str2, str3)) {
        return false;
      }
      i = 1;
    }
    if (!downloadAdditionalResources(paramJSONObject, str2)) {
      return false;
    }
    if (!downloadAppImages(paramJSONObject, paramSet, str1, true))
    {
      logError("downloadFullAppShelfResources failed to download all images, location: " + paramAppShelfLocationData.getLocationName() + ", configurationUrl: " + paramAppShelfLocationData.getConfigurationUrl());
      return false;
    }
    if (i != 0)
    {
      this.mFileUtils.removeDir(str1 + "/skin");
      this.mFileUtils.rename(str2 + "/skin", str1 + "/skin");
    }
    if (!saveJsonObjectToFile(paramAppShelfLocationData, str1, paramJSONObject))
    {
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_SAVE_CONFIG_FILE_ERROR;
      return false;
    }
    return true;
  }
  
  private boolean downloadFullAppShelfResources(AppShelfLocationData paramAppShelfLocationData, Set<String> paramSet, JSONObject paramJSONObject)
  {
    long l = System.currentTimeMillis();
    String str1 = this.mTmpAppShelfDir + paramAppShelfLocationData.getLocationName();
    this.mFileUtils.makeDir(str1 + "/apps/thumbnails");
    String str2 = jsonGetString(paramJSONObject, "skinUrl");
    if ((str2 == null) || (str2.isEmpty()))
    {
      logError("downloadFullAppShelfResources the parameter 'skinUrl' is missing in configuration file for location: " + paramAppShelfLocationData.getLocationName() + ", configurationUrl: " + paramAppShelfLocationData.getConfigurationUrl());
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_MANDATORY_CONFIGURATION_MISSING_ERROR;
    }
    while ((!downloadSkin(paramAppShelfLocationData, str1, str2)) || (!downloadAdditionalResources(paramJSONObject, str1))) {
      return false;
    }
    if (!downloadAppImages(paramJSONObject, paramSet, str1, false))
    {
      logError("downloadFullAppShelfResources failed to download all images, location: " + paramAppShelfLocationData.getLocationName() + ", configurationUrl: " + paramAppShelfLocationData.getConfigurationUrl());
      return false;
    }
    paramSet = this.mAppShelfDir + paramAppShelfLocationData.getLocationName();
    this.mFileUtils.clearDir(paramSet);
    this.mFileUtils.rename(str1, paramSet);
    if (!saveJsonObjectToFile(paramAppShelfLocationData, paramSet, paramJSONObject))
    {
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_SAVE_CONFIG_FILE_ERROR;
      return false;
    }
    ServiceManager.instance().getLogger().log("Appshelf", "Download time " + (System.currentTimeMillis() - l) / 1000L + " for location " + paramAppShelfLocationData.getLocationName(), 3);
    return true;
  }
  
  private void downloadInHouseThumbnail(String paramString1, String paramString2, JSONObject paramJSONObject)
  {
    paramString2 = paramString2 + "/apps/thumbnails";
    String str = paramString2 + "/" + paramString1.substring(paramString1.lastIndexOf('/') + 1);
    if (this.mFileUtils.isFileExist(str)) {
      addInHouseAdImageToJson(paramJSONObject, str, "inHouseThumbnail");
    }
    while (!createHttpConnector().startDownload(paramString1, paramString2)) {
      return;
    }
    addInHouseAdImageToJson(paramJSONObject, str, "inHouseThumbnail");
  }
  
  private int downloadMissingThumbnails(String paramString, int paramInt, List<JSONObject> paramList)
    throws JSONException
  {
    int i = 0;
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      JSONObject localJSONObject = (JSONObject)paramList.next();
      boolean bool = false;
      if (localJSONObject.has("existOnDisk")) {
        bool = localJSONObject.getBoolean("existOnDisk");
      }
      if (!bool)
      {
        if (!downloadThumbnail(localJSONObject.getString("thumbnail"), paramString, localJSONObject)) {
          logError("downloadMissingThumbnails downloadThumbnail() failed");
        }
        do
        {
          return -1;
          localJSONObject.put("existOnDisk", true);
        } while (!downloadAdditionalResources(localJSONObject, paramString));
      }
      int j = i + 1;
      if (localJSONObject.has("inHouseThumbnail")) {
        downloadInHouseThumbnail(localJSONObject.getString("inHouseThumbnail"), paramString, localJSONObject);
      }
      i = j;
      if (j >= paramInt) {
        return j;
      }
    }
    return i;
  }
  
  private boolean downloadSkin(AppShelfLocationData paramAppShelfLocationData, String paramString1, String paramString2)
  {
    paramString1 = paramString1 + "/skin";
    this.mFileUtils.makeDir(paramString1);
    if (!createHttpConnector().startDownload(paramString2, paramString1))
    {
      logError("downloadSkin failed to download skinUrl: " + paramString2 + ", location: " + paramAppShelfLocationData.getLocationName() + ", configurationUrl: " + paramAppShelfLocationData.getConfigurationUrl());
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_DOWNLOAD_SKIN_URL_ERROR;
      return false;
    }
    String str = paramString1 + "/" + paramString2.substring(paramString2.lastIndexOf('/') + 1);
    boolean bool = createZipDecompress(str, paramString1).unzip();
    this.mFileUtils.removeFile(str);
    if (!bool)
    {
      logError("downloadSkin failed to unzip layout file: " + paramString2.substring(paramString2.lastIndexOf('/') + 1) + ", location: " + paramAppShelfLocationData.getLocationName() + ", configurationUrl: " + paramAppShelfLocationData.getConfigurationUrl());
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_UNZIP_LAYOUT_FILE_ERROR;
      return false;
    }
    return true;
  }
  
  private boolean downloadThumbnail(String paramString1, String paramString2, JSONObject paramJSONObject)
  {
    HttpConnector localHttpConnector = createHttpConnector();
    paramString2 = paramString2 + "/apps/thumbnails";
    String str = paramString2 + "/" + paramString1.substring(paramString1.lastIndexOf('/') + 1);
    if (localHttpConnector.startDownload(paramString1, paramString2))
    {
      addInHouseAdImageToJson(paramJSONObject, str, "thumbnail");
      return true;
    }
    logError("downloadThumbnail failed to download thumbnail file: " + paramString1 + ", will try again");
    if (localHttpConnector.startDownload(paramString1, paramString2))
    {
      addInHouseAdImageToJson(paramJSONObject, str, "thumbnail");
      return true;
    }
    logError("downloadThumbnail failed to download thumbnail file: " + paramString1);
    this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_DOWNLOAD_THUMBNAIL;
    return false;
  }
  
  private boolean getConfigurationUrlFromServer(String paramString1, String paramString2)
  {
    String str2 = TAG;
    StringBuilder localStringBuilder = new StringBuilder().append("getConfigurationUrlFronServer appId: ").append(paramString1).append(", store: ").append(this.mStore).append(", location: ");
    if (paramString2 == null) {}
    for (String str1 = "nullLocation";; str1 = paramString2)
    {
      Log.d(str2, str1);
      paramString1 = this.mAppShelfServer.getAppShelfConfigurationUrl(paramString2, paramString1, this.mStore, this.mLanguage, this.mServerDomain, this.mOrientation);
      if ((paramString1 == null) || (paramString1.isEmpty())) {
        break;
      }
      this.mAppShelfLocationsData.add(new AppShelfLocationData(paramString2, paramString1, isLocationExist(paramString2, paramString1)));
      return true;
    }
    logError("getConfigurationUrlFronServer failed to get haskkey from the server, application won't be download for location " + paramString2);
    checkIfToDownloadDefaultAppShelf(paramString2);
    return false;
  }
  
  private List<String> initConfigurationUrls()
  {
    String str = this.mAppInfo.getAppId();
    this.mAppShelfLocationsData.clear();
    this.mDownloadDefaultAppShelf = false;
    ArrayList localArrayList = new ArrayList();
    if ((this.mLocations == null) || (this.mLocations.isEmpty())) {
      if (!getConfigurationUrlFromServer(str, null)) {
        localArrayList.add(null);
      }
    }
    for (;;)
    {
      return localArrayList;
      HashSet localHashSet = new HashSet();
      Iterator localIterator = this.mLocations.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (!localHashSet.contains(localEntry.getValue()))
        {
          localHashSet.add(localEntry.getValue());
          if (!getConfigurationUrlFromServer(str, (String)localEntry.getValue())) {
            localArrayList.add(localEntry.getValue());
          }
        }
      }
    }
  }
  
  private boolean isLocationExist(String paramString1, String paramString2)
  {
    boolean bool = false;
    paramString1 = this.mStorage.getString(paramString1);
    if (paramString1 != null)
    {
      if (paramString2.compareTo(paramString1) == 0) {
        bool = true;
      }
    }
    else {
      return bool;
    }
    return false;
  }
  
  private String jsonGetString(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString(paramString);
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
    return null;
  }
  
  private String jsonToString(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = paramJSONObject.toString(4);
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
    return null;
  }
  
  private void logApplicationNumber(int paramInt, String paramString)
  {
    HashMap localHashMap = new HashMap(2);
    localHashMap.put("Location", paramString);
    localHashMap.put("Apps Num", Integer.toString(paramInt));
    ServiceManager.instance().getAnalytics().logEvent("Number Of Installed Apps", localHashMap, false);
  }
  
  private void logError(String paramString)
  {
    Log.e(TAG, paramString);
    ServiceManager.instance().getLogger().log("Appshelf", paramString, 1);
  }
  
  private void notifySessionStartReady(String paramString)
  {
    if (paramString.compareTo("sessionStart") == 0) {
      try
      {
        notify();
        return;
      }
      finally {}
    }
  }
  
  private void onLocationFailed(String paramString, PublishingSDKErrors paramPublishingSDKErrors)
  {
    Iterator localIterator = this.mLocations.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (paramString.compareTo((String)localEntry.getValue()) == 0)
      {
        this.mDelegate.onLocationFailed((String)localEntry.getKey(), paramPublishingSDKErrors, this);
        notifySessionStartReady((String)localEntry.getKey());
      }
    }
  }
  
  private void onLocationLoaded(String paramString)
  {
    Iterator localIterator = this.mLocations.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (paramString.compareTo((String)localEntry.getValue()) == 0)
      {
        if (this.mEnableAppShelf) {
          this.mDelegate.onLocationLoaded((String)localEntry.getKey(), this);
        }
        for (;;)
        {
          notifySessionStartReady((String)localEntry.getKey());
          break;
          this.mDelegate.onLocationFailed((String)localEntry.getKey(), PublishingSDKErrors.PSDK_APPSHELF_SERVICE_IS_DISABLE_ERROR, this);
        }
      }
    }
  }
  
  private Set<String> refreshInstalledPackages()
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
        Log.d(TAG, "refreshInstalledPackages Exception: " + localException.getMessage());
      }
      return localException;
    }
  }
  
  private boolean saveJsonObjectToFile(AppShelfLocationData paramAppShelfLocationData, String paramString, JSONObject paramJSONObject)
  {
    paramJSONObject = jsonToString(paramJSONObject);
    if ((paramJSONObject == null) || (paramJSONObject.isEmpty()))
    {
      logError("saveJsonObjectToFile failed to save content.json file, location: " + paramAppShelfLocationData.getLocationName() + ", configurationUrl: " + paramAppShelfLocationData.getConfigurationUrl());
      return false;
    }
    if (!this.mFileUtils.saveStringToFile(paramString + "/content.json", paramJSONObject))
    {
      logError("saveJsonObjectToFile failed to save content.json file, location: " + paramAppShelfLocationData.getLocationName() + ", configurationUrl: " + paramAppShelfLocationData.getConfigurationUrl());
      return false;
    }
    return true;
  }
  
  private void updateDelegate(List<String> paramList, boolean paramBoolean)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (paramBoolean) {
        onLocationLoaded(str);
      } else {
        onLocationFailed(str, PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_GET_CONFIGURATION_URLS_FROM_SERVER_ERROR);
      }
    }
  }
  
  private int updateInstallationStatus(AppShelfLocationData paramAppShelfLocationData, Set<String> paramSet)
  {
    int k = 0;
    int i = 0;
    String str = this.mAppShelfDir + paramAppShelfLocationData.getLocationName();
    Object localObject1 = this.mFileUtils.getStringFromFile(str + "/content.json");
    if ((localObject1 == null) || (((String)localObject1).isEmpty()))
    {
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_READ_LOCAL_CONFIG_FILE_ERROR;
      logError("updateInstallationStatus: " + this.mLastError.getDescription());
      return -1;
    }
    localObject1 = createJsonObject((String)localObject1);
    if (localObject1 == null)
    {
      logError("updateInstallationStatus failed to parse the file content.json: " + paramAppShelfLocationData.getLocationName());
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_PARSE_LOCAL_CONFIGURATION_FILE_ERROR;
      return -1;
    }
    int i1 = 0;
    int j = k;
    for (;;)
    {
      Object localObject2;
      int m;
      Object localObject3;
      boolean bool2;
      try
      {
        JSONObject localJSONObject1 = ((JSONObject)localObject1).getJSONObject("promotionBoxes");
        j = k;
        Iterator localIterator = localJSONObject1.keys();
        j = i;
        if (localIterator.hasNext())
        {
          j = i;
          localObject2 = localJSONObject1.getJSONObject((String)localIterator.next()).getJSONArray("apps");
          m = 0;
          k = i;
          int n = i1;
          i1 = n;
          i = k;
          j = k;
          if (m >= ((JSONArray)localObject2).length()) {
            continue;
          }
          j = k;
          localObject3 = ((JSONArray)localObject2).getJSONObject(m);
          j = k;
          bool2 = paramSet.contains(((JSONObject)localObject3).getString("bundleId"));
          boolean bool1 = false;
          j = k;
          if (((JSONObject)localObject3).has("installed"))
          {
            j = k;
            bool1 = ((JSONObject)localObject3).getBoolean("installed");
          }
          if (bool2 == bool1) {
            break label668;
          }
          j = k;
          ((JSONObject)localObject3).put("installed", bool2);
          n = 1;
          break label668;
        }
        if (i1 != 0)
        {
          j = i;
          if (!saveJsonObjectToFile(paramAppShelfLocationData, str, (JSONObject)localObject1)) {
            return i;
          }
        }
        else
        {
          j = i;
          Log.d(TAG, "updateInstallationStatus no app in the list was installed/unistalled no need to download nothing");
          return i;
        }
        j = i;
        localIterator = localJSONObject1.keys();
        j = i;
        localObject2 = new ArrayList();
        j = i;
        localObject3 = new ArrayList();
        j = i;
        if (!localIterator.hasNext()) {
          break label652;
        }
        j = i;
        Object localObject4 = localJSONObject1.getJSONObject((String)localIterator.next());
        j = i;
        m = ((JSONObject)localObject4).getInt("numOfApps");
        j = i;
        localObject4 = ((JSONObject)localObject4).getJSONArray("apps");
        j = i;
        ((List)localObject2).clear();
        j = i;
        ((List)localObject3).clear();
        k = 0;
        j = i;
        if (k < ((JSONArray)localObject4).length())
        {
          j = i;
          JSONObject localJSONObject2 = ((JSONArray)localObject4).getJSONObject(k);
          j = i;
          if (paramSet.contains(localJSONObject2.getString("bundleId")))
          {
            j = i;
            ((List)localObject2).add(localJSONObject2);
          }
          else
          {
            j = i;
            ((List)localObject3).add(localJSONObject2);
          }
        }
      }
      catch (JSONException paramAppShelfLocationData)
      {
        paramAppShelfLocationData.printStackTrace();
        i = j;
        return i;
      }
      j = i;
      k = downloadMissingThumbnails(str, m, (List)localObject3);
      if (k < 0)
      {
        j = i;
        logError("updateInstallationStatus failed to download thumbnails for uninstalled applications");
        return i;
      }
      if (k < m)
      {
        j = i;
        if (downloadMissingThumbnails(str, m, (List)localObject2) < 0)
        {
          j = i;
          logError("updateInstallationStatus failed to download thumbnails for installed applications");
          continue;
          label652:
          j = i;
          saveJsonObjectToFile(paramAppShelfLocationData, str, (JSONObject)localObject1);
          continue;
          label668:
          i = k;
          if (bool2) {
            i = k + 1;
          }
          m += 1;
          k = i;
          continue;
          k += 1;
        }
      }
    }
  }
  
  public void close()
  {
    if (this.mWebViewService != null)
    {
      this.mClosedByBackButton = this.mWebViewService.isClosedByBackButton();
      this.mWebViewService.closeWebView();
      this.mWebViewService = null;
    }
  }
  
  protected AppShelfServer createAppShelfServer()
  {
    return new AppShelfServer();
  }
  
  protected HttpConnector createHttpConnector()
  {
    return new HttpConnector(null);
  }
  
  protected JSONObject createJsonObject(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  protected LocalStorage createLocalStorage()
  {
    return new LocalStorage(this.mAppInfo.getActivity());
  }
  
  protected PublishingSDKFileUtils createPublishingSDKFileUtils()
  {
    return new PublishingSDKFileUtils();
  }
  
  protected ZipDecompress createZipDecompress(String paramString1, String paramString2)
  {
    return new ZipDecompress(paramString1, paramString2);
  }
  
  public String getHouseAdsConfiguration()
  {
    if (this.mLocations == null) {
      return null;
    }
    Object localObject2 = (String)this.mLocations.get("moreApps");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "default";
    }
    Object localObject3 = this.mAppShelfDir + "/" + (String)localObject1 + "/content.json";
    localObject2 = localObject3;
    if (!this.mFileUtils.isFileExist((String)localObject3)) {
      if (((String)localObject1).compareTo("default") != 0)
      {
        localObject1 = this.mAppShelfDir + "/" + "default" + "/content.json";
        localObject2 = localObject1;
        if (!this.mFileUtils.isFileExist((String)localObject1)) {
          return null;
        }
      }
      else
      {
        return null;
      }
    }
    localObject1 = this.mFileUtils.getStringFromFile((String)localObject2);
    if ((localObject1 == null) || (((String)localObject1).isEmpty())) {
      return null;
    }
    for (;;)
    {
      Object localObject4;
      int i;
      try
      {
        localObject2 = createJsonObject((String)localObject1);
        if (localObject2 == null) {
          return null;
        }
        localObject1 = new JSONArray();
        localObject2 = ((JSONObject)localObject2).getJSONObject("promotionBoxes");
        localObject3 = ((JSONObject)localObject2).keys();
        if (((Iterator)localObject3).hasNext())
        {
          localObject4 = (String)((Iterator)localObject3).next();
          if (((String)localObject4).compareTo("Slider List") != 0) {
            continue;
          }
          localObject2 = ((JSONObject)localObject2).getJSONObject((String)localObject4).getJSONArray("apps");
          i = 0;
          if (i >= ((JSONArray)localObject2).length()) {
            break label490;
          }
          localObject3 = ((JSONArray)localObject2).getJSONObject(i);
          if ((!((JSONObject)localObject3).has("existOnDisk")) || (!((JSONObject)localObject3).getBoolean("existOnDisk"))) {
            break label513;
          }
          localObject4 = new JSONObject();
          ((JSONObject)localObject4).put("appUrl", ((JSONObject)localObject3).getString("appUrl"));
          ((JSONObject)localObject4).put("bundleId", ((JSONObject)localObject3).getString("bundleId"));
          if (!((JSONObject)localObject3).has("inHouseApplicationName")) {
            break label416;
          }
          ((JSONObject)localObject4).put("applicationName", ((JSONObject)localObject3).getString("inHouseApplicationName"));
          if (!((JSONObject)localObject3).has("inHouseDescription")) {
            break label434;
          }
          ((JSONObject)localObject4).put("description", ((JSONObject)localObject3).getString("inHouseDescription"));
          if (!((JSONObject)localObject3).has("inHouseThumbnail")) {
            break label462;
          }
          ((JSONObject)localObject4).put("inHouseThumbnail", ((JSONObject)localObject3).getString("inHouseThumbnail"));
          ((JSONArray)localObject1).put(localObject4);
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
      return null;
      label416:
      ((JSONObject)localObject4).put("applicationName", ((JSONObject)localObject3).getString("applicationName"));
      continue;
      label434:
      if (((JSONObject)localObject3).has("description"))
      {
        ((JSONObject)localObject4).put("description", ((JSONObject)localObject3).getString("description"));
        continue;
        label462:
        if (((JSONObject)localObject3).has("thumbnail"))
        {
          ((JSONObject)localObject4).put("inHouseThumbnail", ((JSONObject)localObject3).getString("thumbnail"));
          continue;
          label490:
          localObject2 = new JSONObject();
          ((JSONObject)localObject2).put("apps", localJSONException);
          String str = ((JSONObject)localObject2).toString();
          return str;
          label513:
          i += 1;
        }
      }
    }
  }
  
  public PublishingSDKErrors getPSDKLastError()
  {
    return this.mLastError;
  }
  
  protected PackageManager getPackageManager()
  {
    return this.mAppInfo.getActivity().getPackageManager();
  }
  
  public void initLocations(Map<String, String> paramMap)
  {
    this.mLocations = paramMap;
    if (paramMap != null)
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        if (this.mStorage.hasKey((String)localEntry.getValue())) {
          mReadyLocations.add(localEntry.getValue());
        }
      }
    }
    if (this.mStorage.hasKey("nullLocation")) {
      mReadyLocations.add("nullLocation");
    }
  }
  
  public boolean isClosedByBackButton()
  {
    if (this.mWebViewService != null) {
      return this.mWebViewService.isClosedByBackButton();
    }
    return this.mClosedByBackButton;
  }
  
  public boolean isLocationConfigured(String paramString)
  {
    return this.mLocations.get(paramString) != null;
  }
  
  public boolean isLocationReady(String paramString)
  {
    if ((this.mLocations == null) || (this.mLocations.size() == 0) || (!this.mEnableAppShelf)) {}
    String str;
    do
    {
      do
      {
        return false;
        str = null;
        if (paramString != null) {
          str = (String)this.mLocations.get(paramString);
        }
        if (str != null) {
          break;
        }
      } while ((!this.mDefaultAppShelfExist) && (!mReadyLocations.contains("nullLocation")));
      return true;
    } while ((!this.mDefaultAppShelfExist) && (!mReadyLocations.contains(str)));
    return true;
  }
  
  public void onClosed(String paramString, Object paramObject)
  {
    this.mShown = false;
    this.mDelegate.onClosed(this.mShownLocation, this);
  }
  
  public void onConfigurationFetched(boolean paramBoolean)
  {
    ConfigurationFetcherHelper localConfigurationFetcherHelper = this.mConfigurationFetcher;
    boolean bool = true;
    for (;;)
    {
      try
      {
        Object localObject1 = this.mConfigurationFetcher.getString("enableAppShelf");
        paramBoolean = bool;
        if (localObject1 != null)
        {
          paramBoolean = bool;
          if (((String)localObject1).compareTo("no") == 0) {
            paramBoolean = false;
          }
        }
        if (paramBoolean == this.mEnableAppShelf) {
          break;
        }
        this.mEnableAppShelf = paramBoolean;
        localObject1 = this.mLocations.entrySet().iterator();
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject1).next();
        if (isLocationReady((String)localEntry.getKey())) {
          if (this.mEnableAppShelf) {
            this.mDelegate.onLocationLoaded((String)localEntry.getKey(), this);
          } else {
            this.mDelegate.onLocationFailed((String)localEntry.getKey(), PublishingSDKErrors.PSDK_APPSHELF_SERVICE_IS_DISABLE_ERROR, this);
          }
        }
      }
      finally {}
    }
  }
  
  public void onLocationFailed(String paramString, PublishingSDKErrors paramPublishingSDKErrors, Object paramObject) {}
  
  public void onLocationLoaded(String paramString, Object paramObject) {}
  
  public void onPaused()
  {
    Log.d(TAG, "onPaused");
    close();
  }
  
  public void onResume(AppLifeCycleResumeState paramAppLifeCycleResumeState)
  {
    Log.d(TAG, "AppShelfServiceImpl::onResume");
    if (this.mProcessing)
    {
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_PROCESSING_ERROR;
      Log.d(TAG, "onResume: " + this.mLastError.getDescription());
      ServiceManager.instance().getLogger().log("Appshelf", this.mLastError.getDescription(), 3);
      return;
    }
    Log.d(TAG, "AppShelfServiceImpl::onResume mConnectivity " + this.mConnectivity);
    Object localObject;
    if (this.mConnectivity)
    {
      this.mProcessing = true;
      this.mPendingUpdate = null;
      localObject = new OnResumeAsyncTask(this);
      if (paramAppLifeCycleResumeState == AppLifeCycleResumeState.ALCRS_RESUME)
      {
        ((OnResumeAsyncTask)localObject).execute(new Boolean[] { Boolean.valueOf(false), null });
        return;
      }
      ((OnResumeAsyncTask)localObject).execute(new Boolean[] { Boolean.valueOf(true), null });
      return;
    }
    if (this.mPendingUpdate == null) {
      this.mPendingUpdate = paramAppLifeCycleResumeState;
    }
    paramAppLifeCycleResumeState = this.mLocations.entrySet().iterator();
    while (paramAppLifeCycleResumeState.hasNext())
    {
      localObject = (Map.Entry)paramAppLifeCycleResumeState.next();
      if (isLocationReady((String)((Map.Entry)localObject).getKey()))
      {
        if (this.mEnableAppShelf)
        {
          Log.d(TAG, "AppShelfServiceImpl::onResume onLocationLoaded " + (String)((Map.Entry)localObject).getKey());
          this.mDelegate.onLocationLoaded((String)((Map.Entry)localObject).getKey(), this);
        }
        else
        {
          Log.d(TAG, "AppShelfServiceImpl::onResume onLocationFailed " + (String)((Map.Entry)localObject).getKey());
          this.mDelegate.onLocationFailed((String)((Map.Entry)localObject).getKey(), PublishingSDKErrors.PSDK_APPSHELF_SERVICE_IS_DISABLE_ERROR, this);
        }
      }
      else
      {
        Log.d(TAG, "AppShelfServiceImpl::onResume onLocationFailed " + (String)((Map.Entry)localObject).getKey());
        this.mDelegate.onLocationFailed((String)((Map.Entry)localObject).getKey(), PublishingSDKErrors.PSDK_APPSHELF_BASE_ERROR, this);
      }
    }
    try
    {
      notify();
      return;
    }
    finally {}
  }
  
  public void onShown(String paramString, Object paramObject)
  {
    this.mDelegate.onShown(this.mShownLocation, this);
  }
  
  protected void reloadAppShelf()
  {
    Log.d(TAG, "reloadAppShelf -->");
    try
    {
      this.mAppShelfLocationsData.clear();
      List localList = initConfigurationUrls();
      if (!localList.isEmpty())
      {
        if (this.mDownloadDefaultAppShelf) {
          updateDelegate(localList, downloadDefaultAppShelf());
        }
        while (this.mAppShelfLocationsData.isEmpty())
        {
          this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_FAILED_TO_GET_CONFIGURATION_URLS_FROM_SERVER_ERROR;
          logError("initializeAppShelf: " + this.mLastError.getDescription());
          return;
          updateDelegate(localList, true);
          continue;
          Log.d(TAG, "reloadAppShelf <--");
        }
      }
    }
    catch (Exception localException)
    {
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_UKNOWN_ERROR;
      logError("initializeAppShelf: " + this.mLastError.getDescription() + ", exception: " + localException.getMessage());
      localException.printStackTrace();
    }
    for (;;)
    {
      return;
      downloadAppShelfsResources();
    }
  }
  
  public void setLanguage(String paramString)
  {
    if (this.mLanguage.compareTo(paramString) == 0) {
      return;
    }
    this.mLanguage = paramString;
    new OnResumeAsyncTask(this).execute(new Boolean[] { Boolean.valueOf(true), null });
  }
  
  public boolean show(String paramString)
    throws Exception
  {
    if (!this.mEnableAppShelf)
    {
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_SERVICE_IS_DISABLE_ERROR;
      logError("show: " + this.mLastError.getDescription());
      return false;
    }
    if (this.mShown) {
      return true;
    }
    this.mShown = true;
    String str2 = (String)this.mLocations.get(paramString);
    if (str2 == null) {
      throw new Exception("unknown location '" + paramString + "'");
    }
    if (!isLocationReady(paramString))
    {
      this.mLastError = PublishingSDKErrors.PSDK_APPSHELF_LOCATION_IS_NOT_READ_ERROR;
      logError("show: " + this.mLastError.getDescription());
      return false;
    }
    final String str1 = "default";
    if (mReadyLocations.contains(str2)) {
      str1 = str2;
    }
    str1 = "file://" + this.mAppShelfDir + str1 + "/skin/index.html";
    this.mShownLocation = paramString;
    this.mWebViewService = new WebViewService(this, this.mAppshelfdelegate);
    this.mAppInfo.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        AppShelfServiceImpl.this.mWebViewService.openWebViewWithUrl(str1, AppShelfServiceImpl.this.mAppInfo.getActivity(), AppShelfServiceImpl.this.mStore, AppShelfServiceImpl.this.mOpenOnNewActivity);
      }
    });
    return true;
  }
  
  protected void updateInstallationStatus()
  {
    Log.d(TAG, "updateInstallationStatus -->");
    Set localSet = refreshInstalledPackages();
    Iterator localIterator = this.mAppShelfLocationsData.iterator();
    while (localIterator.hasNext())
    {
      AppShelfLocationData localAppShelfLocationData = (AppShelfLocationData)localIterator.next();
      if (localAppShelfLocationData.isExist())
      {
        int i = updateInstallationStatus(localAppShelfLocationData, localSet);
        if (i != -1) {
          ServiceManager.instance().getLogger().log("Appshelf", "updateInstllationStauts success", 3);
        }
        logApplicationNumber(i, localAppShelfLocationData.getLocationName());
      }
    }
    Log.d(TAG, "updateInstallationStatus <--");
  }
  
  protected class AppShelfLocationData
  {
    private final String mConfigurationUrl;
    private boolean mExist;
    private final String mLocation;
    
    public AppShelfLocationData(String paramString1, String paramString2, boolean paramBoolean)
    {
      this.mLocation = paramString1;
      this.mConfigurationUrl = paramString2;
      this.mExist = paramBoolean;
    }
    
    public String getConfigurationUrl()
    {
      return this.mConfigurationUrl;
    }
    
    public String getLocation()
    {
      return this.mLocation;
    }
    
    public String getLocationName()
    {
      if (this.mLocation == null) {
        return "nullLocation";
      }
      return this.mLocation;
    }
    
    public boolean isExist()
    {
      return this.mExist;
    }
    
    public void setExist()
    {
      this.mExist = true;
    }
  }
  
  private class OnResumeAsyncTask
    extends AsyncTask<Boolean, Void, Void>
  {
    AppShelfServiceImpl mAppShelf;
    
    OnResumeAsyncTask(AppShelfServiceImpl paramAppShelfServiceImpl)
    {
      this.mAppShelf = paramAppShelfServiceImpl;
    }
    
    protected Void doInBackground(Boolean... paramVarArgs)
    {
      synchronized (AppShelfServiceImpl.this.mConfigurationFetcher)
      {
        if (paramVarArgs[0].booleanValue())
        {
          this.mAppShelf.reloadAppShelf();
          AppShelfServiceImpl.access$102(AppShelfServiceImpl.this, false);
          return null;
        }
        this.mAppShelf.updateInstallationStatus();
      }
    }
  }
}
