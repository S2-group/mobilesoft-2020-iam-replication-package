package com.citrix.work.database.helpers;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.citrix.MAM.Android.ManagedAppHelper.Interface.MAMAppInfo.SubscriptionState;
import com.citrix.mdx.lib.PolicyParser;
import com.citrix.work.appmanagement.AppManagementController;
import com.citrix.work.common.WorkUtils;
import com.citrix.work.database.AndroidDatabaseHelper;
import com.citrix.work.database.Dao.Application;
import com.citrix.work.deliveryservices.DSClientExecutionContext;
import com.citrix.work.deliveryservices.storeservice.parser.Resource;
import com.citrix.work.deliveryservices.storeservice.parser.Resource.ApplicationType;
import com.citrix.work.deliveryservices.utilities.DeliveryServicesException;
import com.citrix.work.deliveryservices.utilities.StoreFrontUtilities;
import com.citrix.work.deliveryservices.webstore.AppJsonData;
import com.citrix.work.log.Logger;
import com.zenprise.monitor.Monitor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApplicationHelper
{
  public static final int APP_STATE_DISABLED = 0;
  public static final int APP_STATE_ENABLED = 1;
  private static Logger m_logger = Logger.getLogger(ApplicationHelper.class);
  
  public ApplicationHelper() {}
  
  public static String convertArrayListToString(ArrayList<String> paramArrayList, char paramChar)
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append(paramChar);
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      localStringBuilder.append((String)paramArrayList.next());
      localStringBuilder.append(paramChar);
    }
    return localStringBuilder.toString();
  }
  
  private static String convertEmptyStringToNull(String paramString)
  {
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = null;
    }
    return str;
  }
  
  public static ArrayList<String> convertStringToArrayList(String paramString, char paramChar)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = paramString.split(String.valueOf(paramChar));
    if (paramString != null)
    {
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        localArrayList.add(paramString[i]);
        i += 1;
      }
    }
    return localArrayList;
  }
  
  private static Cursor getAllInstalledApplications(AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt)
  {
    return paramAndroidDatabaseHelper.getDatabase().query("Applications", null, "profileId = ? AND isInstalled = ? ", new String[] { String.valueOf(paramInt), String.valueOf(1) }, null, null, null);
  }
  
  public static List<AppJsonData> getAllInstalledApplicationsList(AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = getAllInstalledApplications(paramAndroidDatabaseHelper, paramInt);
    if (localCursor.moveToFirst()) {
      if (!localCursor.isAfterLast())
      {
        AppJsonData localAppJsonData = new AppJsonData();
        localAppJsonData.m_uniqueAppName = localCursor.getString(localCursor.getColumnIndex("dsResourceId"));
        label80:
        String str;
        if (localCursor.getInt(localCursor.getColumnIndex("isMAMApp")) == 1)
        {
          paramInt = 1;
          if (paramInt != 0)
          {
            str = localCursor.getString(localCursor.getColumnIndex("mamPackage"));
            if (TextUtils.isEmpty(str)) {
              break label143;
            }
          }
        }
        label143:
        for (localAppJsonData.m_packageId = MobileAppManagementHelper.getPackageId(paramAndroidDatabaseHelper, str);; localAppJsonData.m_packageId = localCursor.getString(localCursor.getColumnIndex("mampkgId")))
        {
          localArrayList.add(localAppJsonData);
          localCursor.moveToNext();
          break;
          paramInt = 0;
          break label80;
        }
      }
    }
    localCursor.close();
    return localArrayList;
  }
  
  public static String getAppListState(AndroidDatabaseHelper paramAndroidDatabaseHelper, String paramString, long paramLong)
  {
    AndroidDatabaseHelper localAndroidDatabaseHelper = null;
    Object localObject = null;
    if (paramLong != -1L)
    {
      paramString = paramAndroidDatabaseHelper.getDatabase().query("Applications", null, "profileId = ? AND mamPackage = ?", new String[] { String.valueOf(paramLong), paramString }, null, null, null);
      paramAndroidDatabaseHelper = localObject;
      if (paramString.moveToFirst()) {
        paramAndroidDatabaseHelper = paramString.getString(paramString.getColumnIndex("dsSubscriptionStatus"));
      }
      paramString.close();
      localAndroidDatabaseHelper = paramAndroidDatabaseHelper;
    }
    return localAndroidDatabaseHelper;
  }
  
  public static String getAppListState(AndroidDatabaseHelper paramAndroidDatabaseHelper, String paramString1, long paramLong, String paramString2)
  {
    AndroidDatabaseHelper localAndroidDatabaseHelper = null;
    Object localObject = null;
    if (paramLong != -1L)
    {
      paramString1 = paramAndroidDatabaseHelper.getDatabase().query("Applications", null, "profileId = ? AND mamPackage = ? AND dsResourceId = ?", new String[] { String.valueOf(paramLong), paramString1, paramString2 }, null, null, null);
      paramAndroidDatabaseHelper = localObject;
      if (paramString1.moveToFirst()) {
        paramAndroidDatabaseHelper = paramString1.getString(paramString1.getColumnIndex("dsSubscriptionStatus"));
      }
      paramString1.close();
      localAndroidDatabaseHelper = paramAndroidDatabaseHelper;
    }
    return localAndroidDatabaseHelper;
  }
  
  public static MAMAppInfo.SubscriptionState getAppSubscriptionState(String paramString1, AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt, String paramString2)
  {
    paramString1 = getMAMApplication(paramAndroidDatabaseHelper, paramInt, paramString1, paramString2);
    if ((paramString1 != null) && (paramString1.m_id != -1))
    {
      if (paramString1.m_dsIsWorkFlowEnabled == 0)
      {
        if (paramString1.m_isInstalled) {
          return MAMAppInfo.SubscriptionState.SUBSCRIBED;
        }
        return MAMAppInfo.SubscriptionState.AVAILABLE;
      }
      if (StoreFrontUtilities.isSubscribedOrApproved(paramString1.m_dSSubscriptionStatus)) {
        return MAMAppInfo.SubscriptionState.SUBSCRIBED;
      }
      return MAMAppInfo.SubscriptionState.AVAILABLE;
    }
    return MAMAppInfo.SubscriptionState.NOT_AVAILABLE;
  }
  
  public static Application getApplication(AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt)
  {
    paramAndroidDatabaseHelper = new Application(paramAndroidDatabaseHelper);
    paramAndroidDatabaseHelper.m_id = paramInt;
    paramAndroidDatabaseHelper.refresh();
    if (paramAndroidDatabaseHelper.m_id != -1) {
      return paramAndroidDatabaseHelper;
    }
    return null;
  }
  
  public static Cursor getApplicationByMAMPackageName(AndroidDatabaseHelper paramAndroidDatabaseHelper, String paramString)
  {
    return paramAndroidDatabaseHelper.getDatabase().query("Applications", null, "isMAMApp = ? AND mamPackage = ?", new String[] { Integer.toString(1), paramString }, null, null, null);
  }
  
  public static Cursor getApplicationByMAMPackageName(AndroidDatabaseHelper paramAndroidDatabaseHelper, String paramString1, long paramLong, String paramString2)
  {
    return paramAndroidDatabaseHelper.getDatabase().query("Applications", null, "isMAMApp = ? AND profileId = ? AND mamPackage = ? AND dsResourceId = ?", new String[] { String.valueOf(1), String.valueOf(paramLong), paramString1, paramString2 }, null, null, null);
  }
  
  public static Application getApplicationByResourceId(AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt, String paramString)
  {
    paramString = paramAndroidDatabaseHelper.getDatabase().query("Applications", null, "profileId = ? AND dsResourceId = ?", new String[] { String.valueOf(paramInt), paramString }, null, null, null);
    if (paramString.moveToFirst())
    {
      paramAndroidDatabaseHelper = Application.createFromCursor(paramAndroidDatabaseHelper, paramString);
      paramString.close();
      return paramAndroidDatabaseHelper;
    }
    paramString.close();
    return null;
  }
  
  public static String getApplicationPkgIdByMAMPackageName(AndroidDatabaseHelper paramAndroidDatabaseHelper, String paramString1, long paramLong, String paramString2)
  {
    Object localObject = null;
    paramString1 = paramAndroidDatabaseHelper.getDatabase().query("Applications", new String[] { "mampkgId" }, "isMAMApp = ? AND profileId = ? AND mamPackage = ? AND dsResourceId = ?", new String[] { String.valueOf(1), String.valueOf(paramLong), paramString1, paramString2 }, null, null, null);
    paramAndroidDatabaseHelper = localObject;
    if (paramString1.moveToFirst()) {
      paramAndroidDatabaseHelper = paramString1.getString(paramString1.getColumnIndex("mampkgId"));
    }
    paramString1.close();
    return paramAndroidDatabaseHelper;
  }
  
  public static List<Application> getInstalledApplicationsList(AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = paramAndroidDatabaseHelper.getDatabase().query("Applications", null, "profileId = ? AND isInstalled = ? ", new String[] { String.valueOf(paramInt), String.valueOf(1) }, null, null, null);
    if (localCursor.moveToFirst()) {
      do
      {
        localArrayList.add(Application.createFromCursor(paramAndroidDatabaseHelper, localCursor));
      } while (localCursor.moveToNext());
    }
    localCursor.close();
    return localArrayList;
  }
  
  public static String getMAMAppName(AndroidDatabaseHelper paramAndroidDatabaseHelper, String paramString1, String paramString2)
  {
    AndroidDatabaseHelper localAndroidDatabaseHelper = null;
    Object localObject = null;
    if (paramString1 != null)
    {
      paramString1 = paramAndroidDatabaseHelper.getDatabase().query("Applications", new String[] { "fName" }, "mamPackage = ? AND dsResourceId = ?", new String[] { paramString1, paramString2 }, null, null, null);
      paramAndroidDatabaseHelper = localObject;
      if (paramString1.moveToFirst()) {
        paramAndroidDatabaseHelper = paramString1.getString(paramString1.getColumnIndex("fName"));
      }
      paramString1.close();
      localAndroidDatabaseHelper = paramAndroidDatabaseHelper;
    }
    return localAndroidDatabaseHelper;
  }
  
  public static Application getMAMApplication(AndroidDatabaseHelper paramAndroidDatabaseHelper, long paramLong, String paramString1, String paramString2)
  {
    Object localObject = null;
    paramString2 = getApplicationByMAMPackageName(paramAndroidDatabaseHelper, paramString1, paramLong, paramString2);
    paramString1 = localObject;
    if (paramString2.moveToFirst()) {
      paramString1 = Application.createFromCursor(paramAndroidDatabaseHelper, paramString2);
    }
    paramString2.close();
    return paramString1;
  }
  
  public static List<Application> getNotInstalledRequiredApps(AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = paramAndroidDatabaseHelper.getDatabase().query("Applications", null, "profileId = ? AND isInstalled = ? AND dsKeywords LIKE ?", new String[] { String.valueOf(paramInt), "0", "%REQUIRED%" }, null, null, null);
    if (localCursor.moveToFirst()) {
      do
      {
        localArrayList.add(Application.createFromCursor(paramAndroidDatabaseHelper, localCursor));
      } while (localCursor.moveToNext());
    }
    localCursor.close();
    return localArrayList;
  }
  
  public static boolean isAppsInstalled(AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt)
  {
    boolean bool = false;
    paramAndroidDatabaseHelper = getAllInstalledApplications(paramAndroidDatabaseHelper, paramInt);
    if (paramAndroidDatabaseHelper.moveToFirst()) {
      bool = true;
    }
    paramAndroidDatabaseHelper.close();
    return bool;
  }
  
  public static boolean isMAMAppInstalled(AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt, String paramString)
  {
    boolean bool = false;
    if (!TextUtils.isEmpty(paramString))
    {
      paramAndroidDatabaseHelper = paramAndroidDatabaseHelper.getDatabase().query("Applications", new String[] { "_id" }, "profileId = ? AND isInstalled = ? AND isMAMApp = ? AND mamPackage = ? ", new String[] { String.valueOf(paramInt), String.valueOf(1), String.valueOf(1), paramString }, null, null, null);
      bool = paramAndroidDatabaseHelper.moveToFirst();
      paramAndroidDatabaseHelper.close();
    }
    return bool;
  }
  
  public static boolean isPublicStoreApp(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = AndroidDatabaseHelper.getHelper(paramContext);
    paramString = getApplicationByMAMPackageName(paramContext, paramString);
    if (paramString.moveToFirst()) {
      bool = Application.createFromCursor(paramContext, paramString).isPublicStoreApp();
    }
    paramString.close();
    return bool;
  }
  
  public static boolean isSBPolicyEnabled(PolicyParser paramPolicyParser, AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt)
  {
    boolean bool = false;
    if (paramPolicyParser != null)
    {
      String str1 = paramPolicyParser.getString("NetworkAccess");
      bool = paramPolicyParser.getBoolean("EnableVPNModeSwitch");
      String str2 = paramPolicyParser.getString("PreferredVpnMode");
      if (("NetworkAccessTunneled".equals(str1)) && (("SecureBrowse".equals(str2)) || (bool)) && (!AccountServicesHelper.hasBackgroundServiceAndSTA(paramAndroidDatabaseHelper, paramInt, paramPolicyParser))) {
        bool = true;
      }
    }
    else
    {
      return bool;
    }
    return false;
  }
  
  public static boolean isVPNPolicyEnabled(PolicyParser paramPolicyParser, AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt)
  {
    boolean bool = false;
    if (paramPolicyParser != null)
    {
      String str1 = paramPolicyParser.getString("NetworkAccess");
      bool = paramPolicyParser.getBoolean("EnableVPNModeSwitch");
      String str2 = paramPolicyParser.getString("PreferredVpnMode");
      if (("NetworkAccessTunneled".equals(str1)) && ((TextUtils.isEmpty(str2)) || ("FullTunnel".equals(str2)) || (("SecureBrowse".equals(str2)) && (bool))) && (!AccountServicesHelper.hasBackgroundServiceAndSTA(paramAndroidDatabaseHelper, paramInt, paramPolicyParser))) {
        bool = true;
      }
    }
    else
    {
      return bool;
    }
    return false;
  }
  
  public static void onApplicationUninstalled(Context paramContext, AndroidDatabaseHelper paramAndroidDatabaseHelper, String paramString)
  {
    paramAndroidDatabaseHelper = new ContentValues();
    paramAndroidDatabaseHelper.put("isInstalled", Integer.valueOf(0));
    paramAndroidDatabaseHelper.put("isInstalling", Integer.valueOf(0));
    paramAndroidDatabaseHelper.put("isIconDownloaded", Integer.valueOf(0));
    paramContext.getContentResolver().update(Application.CONTENT_URI, paramAndroidDatabaseHelper, "mamPackage = ?", new String[] { paramString });
  }
  
  public static void onApplicationUninstalled(AndroidDatabaseHelper paramAndroidDatabaseHelper, String paramString)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("isInstalled", Integer.valueOf(0));
      paramAndroidDatabaseHelper.getDatabase().beginTransaction();
      paramAndroidDatabaseHelper.getDatabase().update("Applications", localContentValues, "mamPackage = ?", new String[] { paramString });
      paramAndroidDatabaseHelper.getDatabase().setTransactionSuccessful();
      return;
    }
    finally
    {
      paramAndroidDatabaseHelper.getDatabase().endTransaction();
    }
  }
  
  protected static void onDeleteProfile(SQLiteDatabase paramSQLiteDatabase, int paramInt)
  {
    paramSQLiteDatabase.delete("Applications", "profileId" + " = ?", new String[] { Long.toString(paramInt) });
  }
  
  public static void removeApp(AndroidDatabaseHelper paramAndroidDatabaseHelper, String paramString)
  {
    try
    {
      paramAndroidDatabaseHelper.getDatabase().beginTransaction();
      paramAndroidDatabaseHelper.getDatabase().delete("Applications", "mamPackage = ?", new String[] { paramString });
      paramAndroidDatabaseHelper.getDatabase().setTransactionSuccessful();
      return;
    }
    finally
    {
      paramAndroidDatabaseHelper.getDatabase().endTransaction();
    }
  }
  
  public static void removeNonMatchingApplicationsForProfile(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.delete("Applications", "profileId" + " = ? and " + "sequence" + " != ?", new String[] { Integer.toString(paramInt1), Integer.toString(paramInt2) });
  }
  
  public static void uninstallAllAppsForProfile(DSClientExecutionContext paramDSClientExecutionContext, AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt)
    throws DeliveryServicesException
  {
    Object localObject = getInstalledApplicationsList(paramAndroidDatabaseHelper, paramInt);
    paramAndroidDatabaseHelper = AppManagementController.getInstance(paramDSClientExecutionContext, Monitor.getAppContext(), paramInt);
    Context localContext = Monitor.getAppContext();
    boolean bool = WorkUtils.isMDMEnrolled(localContext);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Application localApplication = (Application)((Iterator)localObject).next();
      if ((!bool) && (!localApplication.isMAMApp()))
      {
        paramAndroidDatabaseHelper.unInstallApplication(paramDSClientExecutionContext, localContext, localApplication.m_id);
      }
      else
      {
        m_logger.d("uninstallAllAppsForProfile: Could not uninstall " + localApplication.m_fName);
        m_logger.d("uninstallAllAppsForProfile; MDMEnrolled: " + bool);
        m_logger.d("uninstallAllAppsForProfile: isMAMApp: " + localApplication.isMAMApp());
      }
    }
  }
  
  private static Application updateApplicationWithResourceInfo(Application paramApplication, Resource paramResource, Integer paramInteger)
  {
    int j = 0;
    int k = 1;
    Resource.ApplicationType localApplicationType = paramResource.getApplicationType();
    paramApplication.m_dsResourceId = paramResource.getId();
    paramApplication.m_isDsApp = 1;
    if (localApplicationType == Resource.ApplicationType.AppTypeContent)
    {
      paramApplication.m_isContent = 1;
      paramApplication.m_contentAddress = paramResource.getContentLocation().trim();
      if (!StoreFrontUtilities.isFavoriteBasedOnSubscriptionState(paramResource.getsubscriptionStatus())) {
        break label469;
      }
      i = 1;
      label61:
      paramApplication.m_favourite = i;
      if (!paramResource.isSSOEnabled()) {
        break label474;
      }
      i = 1;
      label75:
      paramApplication.m_isSSOEnabled = i;
      if (paramApplication.m_isSSOEnabled != 0) {
        paramApplication.m_preLaunchServiceURL = paramResource.getSSOPreLaunchServiceURL();
      }
      paramApplication.m_fName = paramResource.getTitle();
      paramApplication.m_description = paramResource.getSummary();
      paramApplication.m_folderPath = paramResource.getPNAgentEquivalentPath();
      if (!paramResource.isFeatured()) {
        break label479;
      }
      i = 1;
      label128:
      paramApplication.m_featured = i;
      paramApplication.m_dsLaunchUrl = paramResource.getLaunchICAURL();
      paramApplication.m_dsImageUrl = paramResource.getImageURL();
      paramApplication.m_dsSubscriptionUrl = paramResource.getSubscriptionActionsURL();
      paramApplication.m_dSSubscriptionStatus = convertEmptyStringToNull(paramResource.getsubscriptionStatus());
      paramApplication.m_dsSubscriptionIndex = convertEmptyStringToNull(paramResource.getSubscriptionPosition());
      paramApplication.m_dsSubscriptionId = convertEmptyStringToNull(paramResource.getSubscriptionId());
      if (!paramResource.isDesktop()) {
        break label484;
      }
      i = 1;
      label199:
      paramApplication.m_appIsDesktop = i;
      paramApplication.m_dsResourceDetailsURL = convertEmptyStringToNull(paramResource.getResourceDetailsUrl());
      paramApplication.m_resourcetype = paramResource.getResourceType();
      paramApplication.m_dsMachinePowerOffUrl = convertEmptyStringToNull(paramResource.getMachinePowerOffURL());
      paramApplication.m_dsClientTypes = convertArrayListToString(paramResource.getClientTypes(), ',');
      paramApplication.m_dsKeywords = convertArrayListToString(paramResource.getKeywords(), ',');
      if (!paramResource.getIsEnabled()) {
        break label489;
      }
      i = 1;
      label269:
      paramApplication.m_dsIsEnabled = i;
      if (!paramResource.isWorkFlowEnabled()) {
        break label494;
      }
      i = 1;
      label283:
      paramApplication.m_dsIsWorkFlowEnabled = i;
      if (!paramResource.isWorkFlowWithoutClientInteraction()) {
        break label499;
      }
      i = 1;
      label297:
      paramApplication.m_dsIsWorkFlowWithoutClientInteraction = i;
      paramApplication.m_dsSubscriptionQuestionUrl = convertEmptyStringToNull(paramResource.getSubscriptionQuestionURL());
      paramApplication.m_dsSubscriptionReason = convertEmptyStringToNull(paramResource.getSubscriptionReason());
      paramApplication.m_dsSubscriptionResponseReason = convertEmptyStringToNull(paramResource.getSubscriptionResponseReason());
      if (!paramResource.isMobile()) {
        break label504;
      }
      i = 1;
      label344:
      paramApplication.m_mobile = i;
      if (!paramResource.isUniKey()) {
        break label509;
      }
      i = 1;
      label358:
      paramApplication.m_unikey = i;
      if (!paramResource.isVPNRequired()) {
        break label514;
      }
      i = k;
      label373:
      paramApplication.m_isVPNRequired = i;
      if (paramInteger != null) {
        break label519;
      }
    }
    label469:
    label474:
    label479:
    label484:
    label489:
    label494:
    label499:
    label504:
    label509:
    label514:
    label519:
    for (int i = j;; i = paramInteger.intValue())
    {
      paramApplication.m_sequenceNum = i;
      paramApplication.m_mdxcertificatehash = paramResource.getMDXCertificateHash();
      paramApplication.m_mdxapplicationtype = paramResource.getMDXApplicationType();
      paramApplication.update();
      return paramApplication;
      if (localApplicationType != Resource.ApplicationType.AppTypeMobileAndroid) {
        break;
      }
      paramApplication.m_isMAMApp = 1;
      paramApplication.m_MAMAppDownloadUrl = paramResource.getPropertyDownloadURL();
      paramApplication.m_publicStoreUrl = paramResource.getMAMPublicStoreURL();
      paramApplication.m_mampolicies = paramResource.getPropertyMamPolicies();
      paramApplication.m_mamPackage = paramResource.getMAMAppPkg();
      paramApplication.m_mampkgId = paramResource.getMAMAppPkgID();
      break;
      i = 0;
      break label61;
      i = 0;
      break label75;
      i = 0;
      break label128;
      i = 0;
      break label199;
      i = 0;
      break label269;
      i = 0;
      break label283;
      i = 0;
      break label297;
      i = 0;
      break label344;
      i = 0;
      break label358;
      i = 0;
      break label373;
    }
  }
  
  public static void updateDsSubscriptionInfo(AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("dsSubscriptionId", paramString2);
    localContentValues.put("dsSubscriptionStatus", paramString3);
    localContentValues.put("dsResourceId", paramString1);
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      localContentValues.put("favorite", Integer.valueOf(i));
      paramString1 = new StringBuilder("_id").append(" = ?");
      paramAndroidDatabaseHelper.getDatabase().update("Applications", localContentValues, paramString1.toString(), new String[] { Integer.toString(paramInt) });
      return;
    }
  }
  
  public static void updateMAMPoliciesForPackageName(AndroidDatabaseHelper paramAndroidDatabaseHelper, String paramString1, int paramInt, String paramString2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("mampolicies", paramString2);
    paramString2 = new StringBuilder("mamPackage").append(" = ? AND ").append("profileId").append(" = ?");
    paramAndroidDatabaseHelper.getDatabase().update("Applications", localContentValues, paramString2.toString(), new String[] { paramString1, Integer.toString(paramInt) });
  }
  
  public static Application updateOrInsertResource(AndroidDatabaseHelper paramAndroidDatabaseHelper, int paramInt, Resource paramResource, Integer paramInteger)
  {
    Application localApplication2 = getApplicationByResourceId(paramAndroidDatabaseHelper, paramInt, paramResource.getId());
    int i = 0;
    Application localApplication1 = localApplication2;
    if (localApplication2 == null)
    {
      localApplication1 = new Application(paramAndroidDatabaseHelper);
      localApplication1.m_id = ((int)localApplication1.create(paramInt));
      localApplication1.m_profileId = paramInt;
      i = 1;
    }
    paramResource = updateApplicationWithResourceInfo(localApplication1, paramResource, paramInteger);
    if (i == 0) {
      MobileAppManagementHelper.onApplicationUpdated(paramAndroidDatabaseHelper, paramResource);
    }
    return paramResource;
  }
}
