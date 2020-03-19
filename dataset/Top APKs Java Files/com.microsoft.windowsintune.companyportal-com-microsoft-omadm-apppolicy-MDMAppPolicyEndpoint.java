package com.microsoft.omadm.apppolicy;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import com.microsoft.intune.common.database.DataObject;
import com.microsoft.intune.common.enrollment.datacomponent.implementation.EnrollmentSettings;
import com.microsoft.intune.common.enrollment.datacomponent.implementation.EnrollmentStateSettings;
import com.microsoft.intune.common.settings.DiagnosticSettings;
import com.microsoft.intune.common.settings.IDeploymentSettings;
import com.microsoft.intune.common.taskscheduling.AndroidTask;
import com.microsoft.intune.common.taskscheduling.AndroidTask.Builder;
import com.microsoft.intune.common.taskscheduling.TaskScheduler;
import com.microsoft.intune.mam.client.app.data.WipeAppDataStatus;
import com.microsoft.intune.mam.client.app.startup.ADALConnectionDetails;
import com.microsoft.intune.mam.client.fileencryption.MAMKeyRetrievalException;
import com.microsoft.intune.mam.client.identity.MAMIdentity;
import com.microsoft.intune.mam.client.identity.MAMIdentityImpl;
import com.microsoft.intune.mam.client.identity.MAMIdentityManager;
import com.microsoft.intune.mam.client.identity.MAMIdentityManagerImpl;
import com.microsoft.intune.mam.client.identity.MAMIdentityMetaData;
import com.microsoft.intune.mam.client.ipc.AbstractAppPolicyEndpoint;
import com.microsoft.intune.mam.client.ipc.PolicyUpdateType;
import com.microsoft.intune.mam.client.ipc.PrimaryUserInfo;
import com.microsoft.intune.mam.client.ipc.SettingEventTimer;
import com.microsoft.intune.mam.client.telemetry.TelemetryEvent;
import com.microsoft.intune.mam.client.util.PolicyVersionUtils;
import com.microsoft.intune.mam.log.MAMLogScrubber;
import com.microsoft.intune.mam.log.MAMLogScrubberImpl;
import com.microsoft.intune.mam.policy.BundleEncryptionKey;
import com.microsoft.intune.mam.policy.MAMEnrollmentManager.Result;
import com.microsoft.intune.mam.policy.WipeReason;
import com.microsoft.intune.mam.policy.notification.AbstractAppPolicyNotifier.RefreshType;
import com.microsoft.intune.omadm.diagnostics.domain.IOMADMTelemetry;
import com.microsoft.omadm.OMADMItem;
import com.microsoft.omadm.Services;
import com.microsoft.omadm.Services.OMADMComponents;
import com.microsoft.omadm.apppolicy.appconfig.AppConfigHelper;
import com.microsoft.omadm.apppolicy.data.CheckinAttemptResult;
import com.microsoft.omadm.apppolicy.data.CurrentApplicationPolicyProperty;
import com.microsoft.omadm.apppolicy.data.CurrentApplicationPolicyProperty.Key;
import com.microsoft.omadm.apppolicy.data.FileEncryptionKey;
import com.microsoft.omadm.apppolicy.data.MAMAdalConnectionDetails;
import com.microsoft.omadm.apppolicy.data.MAMAdalConnectionDetails.Key;
import com.microsoft.omadm.apppolicy.data.MAMServiceAutoEnrollmentDetails;
import com.microsoft.omadm.apppolicy.data.MAMServiceEnrollment;
import com.microsoft.omadm.apppolicy.data.MAMServiceEnrollment.Key;
import com.microsoft.omadm.apppolicy.data.PendingApplicationPolicyProperty;
import com.microsoft.omadm.apppolicy.data.PendingApplicationPolicyProperty.Key;
import com.microsoft.omadm.apppolicy.mamservice.MAMServiceEnrollmentTask;
import com.microsoft.omadm.apppolicy.mamservice.MAMServiceLicenseCheckTask;
import com.microsoft.omadm.apppolicy.mamservice.MAMServiceLookupDatabaseCache;
import com.microsoft.omadm.apppolicy.mamservice.MAMServiceTask;
import com.microsoft.omadm.apppolicy.mamservice.MAMServiceTokenManager;
import com.microsoft.omadm.apppolicy.mamservice.MAMServiceUnenrollTask;
import com.microsoft.omadm.apppolicy.mamservice.MAMServiceUtils;
import com.microsoft.omadm.client.OMADMClientService;
import com.microsoft.omadm.client.tasks.TaskType;
import com.microsoft.omadm.database.TableRepository;
import com.microsoft.omadm.exception.OMADMException;
import com.microsoft.omadm.exception.OMADMTypeMismatch;
import com.microsoft.omadm.origindetection.DeviceOrigin;
import com.microsoft.omadm.origindetection.XposedCloakInstalledTest;
import com.microsoft.omadm.platforms.android.appmgr.signatures.data.ApplicationSignature;
import com.microsoft.omadm.utils.DeviceInfo;
import com.microsoft.omadm.utils.PackageUtils;
import com.microsoft.omadm.utils.SSPUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MDMAppPolicyEndpoint
  extends AbstractAppPolicyEndpoint
{
  private static final int DEFAULT_PIN_RETRIES = 5;
  private static final Logger LOGGER = Logger.getLogger(MDMAppPolicyEndpoint.class.getName());
  private static Object sEnrollLock = new Object();
  private final AppConfigHelper appConfigHelper;
  private final AppPolicyNotifier appPolicyNotifier;
  private final Context context;
  private final IDeploymentSettings deploymentSettings;
  private final DeviceOrigin deviceOrigin;
  private final EnrollmentStateSettings enrollmentStateSettings;
  private final FileEncryptionKeyManager fileEncryptionKeyManager;
  private final MAMLogScrubber mLogScrubber;
  private final MAMIdentityManagerImpl mamIdentityManager;
  private final MDMPolicySettings policySettings;
  private final TableRepository tableRepository;
  private final MAMServiceTokenManager tokenManager;
  
  public MDMAppPolicyEndpoint(Context paramContext, TableRepository paramTableRepository, MDMPolicySettings paramMDMPolicySettings, AppPolicyNotifier paramAppPolicyNotifier, FileEncryptionKeyManager paramFileEncryptionKeyManager, MAMServiceTokenManager paramMAMServiceTokenManager, EnrollmentStateSettings paramEnrollmentStateSettings, IDeploymentSettings paramIDeploymentSettings, DeviceOrigin paramDeviceOrigin, AppConfigHelper paramAppConfigHelper, MAMIdentityManagerImpl paramMAMIdentityManagerImpl, MAMLogScrubber paramMAMLogScrubber)
  {
    super(paramContext, paramMDMPolicySettings);
    this.context = paramContext;
    this.tableRepository = paramTableRepository;
    this.policySettings = paramMDMPolicySettings;
    this.appPolicyNotifier = paramAppPolicyNotifier;
    this.fileEncryptionKeyManager = paramFileEncryptionKeyManager;
    this.tokenManager = paramMAMServiceTokenManager;
    this.enrollmentStateSettings = paramEnrollmentStateSettings;
    this.deploymentSettings = paramIDeploymentSettings;
    this.appConfigHelper = paramAppConfigHelper;
    this.deviceOrigin = paramDeviceOrigin;
    this.mamIdentityManager = paramMAMIdentityManagerImpl;
    this.mLogScrubber = paramMAMLogScrubber;
    this.mamIdentityManager.getAdditionalIdentityData();
  }
  
  private static boolean cleanupEnrollment(String paramString, MAMIdentity paramMAMIdentity, MAMServiceEnrollment paramMAMServiceEnrollment, boolean paramBoolean)
  {
    TableRepository localTableRepository = Services.get().getTableRepository();
    boolean bool;
    if ((MAMServiceUtils.isLastMAMEnrollment(paramString, localTableRepository)) && (EnrolledUserUtils.getDeviceOwnerUPN() == null))
    {
      if (Services.get().getEnrollmentSettings().getString("AADUserPrincipalName", "").isEmpty()) {
        SSPUtils.unenroll();
      }
      Services.get().getMDMPolicySettings().clear();
      Services.get().getFileEncryptionKeyManager().clearCache();
      bool = true;
    }
    else
    {
      bool = false;
    }
    Object localObject = LOGGER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Deleting enrollment record and policies from database for package: ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(", identity: ");
    localStringBuilder.append(Services.get().getMAMLogScrubber().scrubUPN(paramMAMIdentity.rawUPN()));
    ((Logger)localObject).info(localStringBuilder.toString());
    localTableRepository.beginTransaction();
    try
    {
      localObject = new String[1];
      localObject[0] = paramString;
      localTableRepository.delete(CurrentApplicationPolicyProperty.class, "FullPackageName = ?", (String[])localObject);
      localTableRepository.delete(PendingApplicationPolicyProperty.class, "FullPackageName = ?", (String[])localObject);
      localTableRepository.delete(paramMAMServiceEnrollment.getKey());
      if (bool) {
        localTableRepository.delete(FileEncryptionKey.class, null, null);
      }
      localTableRepository.insertOrReplace(new MAMServiceAutoEnrollmentDetails(paramString, Boolean.valueOf(true)));
      if (paramBoolean) {
        Services.get().getAppConfigHelper().removeAppConfig(paramString, paramMAMIdentity);
      }
      localTableRepository.setTransactionSuccessful();
      return bool;
    }
    finally
    {
      localTableRepository.endTransaction();
    }
  }
  
  private int getMinimumIntegerPolicyValue(String paramString, int paramInt)
  {
    Iterator localIterator = this.context.getPackageManager().getInstalledPackages(0).iterator();
    int i = Integer.MAX_VALUE;
    for (;;)
    {
      Object localObject;
      if (localIterator.hasNext()) {
        localObject = (PackageInfo)localIterator.next();
      }
      try
      {
        localObject = DatabaseAppPolicy.getItem(this.tableRepository, ((PackageInfo)localObject).packageName, paramString);
        if (localObject == null) {
          continue;
        }
        int j = ((OMADMItem)localObject).getIntValue();
        if (i <= j) {
          continue;
        }
        i = j;
      }
      catch (OMADMTypeMismatch localOMADMTypeMismatch) {}
      if (Integer.MAX_VALUE == i) {
        return paramInt;
      }
      return i;
    }
  }
  
  public static MAMEnrollmentManager.Result internalUnenrollPackageForMAM(String paramString, MAMIdentity paramMAMIdentity, AppPolicyNotifier paramAppPolicyNotifier, MAMIdentityManager paramMAMIdentityManager, WipeReason paramWipeReason)
  {
    Object localObject2 = Services.get().getTableRepository();
    Object localObject3 = Services.get().getContext();
    for (;;)
    {
      synchronized (sEnrollLock)
      {
        MAMServiceEnrollment localMAMServiceEnrollment = (MAMServiceEnrollment)((TableRepository)localObject2).get(new MAMServiceEnrollment.Key(paramString));
        if ((localMAMServiceEnrollment != null) && (localMAMServiceEnrollment.identity.equals(paramMAMIdentity)))
        {
          boolean bool2 = isManagedAppInternal(paramString, (TableRepository)localObject2);
          boolean bool3 = PackageUtils.isPackageInstalled((Context)localObject3, paramString);
          if (Services.get().getAppConfigHelper().getAppConfigJson(paramString, paramMAMIdentity) != null)
          {
            bool1 = true;
            if ((bool2) && (bool3))
            {
              localObject2 = LOGGER;
              localObject3 = new StringBuilder();
              ((StringBuilder)localObject3).append("Wiping user data for ");
              ((StringBuilder)localObject3).append(paramString);
              ((StringBuilder)localObject3).append(" because it is being unenrolled");
              ((Logger)localObject2).info(((StringBuilder)localObject3).toString());
              paramAppPolicyNotifier.wipeUserData(paramString, paramMAMIdentity, paramWipeReason);
            }
            else if (!bool2)
            {
              paramWipeReason = LOGGER;
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("Not wiping user data for ");
              ((StringBuilder)localObject2).append(paramString);
              ((StringBuilder)localObject2).append(" being unenrolled because it is not managed");
              paramWipeReason.info(((StringBuilder)localObject2).toString());
            }
            else
            {
              paramWipeReason = LOGGER;
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("Not wiping user data for ");
              ((StringBuilder)localObject2).append(paramString);
              ((StringBuilder)localObject2).append(" being unenrolled because it is not installed");
              paramWipeReason.info(((StringBuilder)localObject2).toString());
            }
            queueUnenrollTask(paramString, paramMAMIdentity, paramMAMIdentityManager, localMAMServiceEnrollment);
            bool3 = cleanupEnrollment(paramString, paramMAMIdentity, localMAMServiceEnrollment, bool1);
            if (bool2)
            {
              LOGGER.info("Sending policy refresh notification to all managed packages due to an unenrollment.");
              paramAppPolicyNotifier.refresh(AbstractAppPolicyNotifier.RefreshType.APP_POLICY);
            }
            if (bool1)
            {
              LOGGER.info("Sending app config refresh notification after unenrollment. App Config should be cleared.");
              paramAppPolicyNotifier.refreshAppConfig(paramString, paramMAMIdentity);
            }
            if (bool3)
            {
              LOGGER.info("Primary user was removed during MAM-WE unenroll.");
              paramAppPolicyNotifier.notifyPrimaryUserRemoved(paramMAMIdentity.toString());
            }
            if (EnrolledUserUtils.getDeviceOwnerUPN() != null) {
              Services.get().getTaskScheduler().schedule(AndroidTask.newBuilder().taskId(TaskType.UpdatePolicy.getValue()).taskReason("Update MDM-MAM policy after MAM unenrollment.").skipIfRunning(false).build());
            }
            return MAMEnrollmentManager.Result.UNENROLLMENT_SUCCEEDED;
          }
        }
        else
        {
          paramString = MAMEnrollmentManager.Result.UNENROLLMENT_SUCCEEDED;
          return paramString;
        }
      }
      boolean bool1 = false;
    }
  }
  
  public static boolean isManagedAppInternal(String paramString, TableRepository paramTableRepository)
  {
    return (paramTableRepository.get(new CurrentApplicationPolicyProperty.Key(paramString, "IsManaged")) != null) || (paramTableRepository.get(new PendingApplicationPolicyProperty.Key(paramString, "IsManaged")) != null);
  }
  
  private void queueCheckinIfDue(String paramString1, MAMServiceEnrollment paramMAMServiceEnrollment, String paramString2)
  {
    if (paramMAMServiceEnrollment == null) {
      return;
    }
    if ((paramMAMServiceEnrollment.lastCheckinAttemptResult == CheckinAttemptResult.NO_TOKEN) && (paramString2 != null))
    {
      MAMServiceUtils.queueCheckin(paramMAMServiceEnrollment, this.context, false, paramString2);
      return;
    }
    if ((!paramMAMServiceEnrollment.nextTriggeredCheckinDue()) && ((!paramMAMServiceEnrollment.offlineTimeoutExceeded()) || (paramString2 == null))) {
      return;
    }
    MAMServiceUtils.queueCheckin(paramMAMServiceEnrollment, this.context, false, paramString2);
  }
  
  private void queueLicenseCheck(String paramString1, String paramString2, String paramString3, String paramString4, MAMIdentity paramMAMIdentity, ADALConnectionDetails paramADALConnectionDetails)
  {
    paramString1 = new MAMServiceLicenseCheckTask(paramString1, paramMAMIdentity, paramString2, paramADALConnectionDetails);
    paramString1.setMamServiceToken(paramString3);
    paramString1.setOperationSessionGuid(paramString4);
    OMADMClientService.queueTask(this.context, paramString1, "MAMService license check");
  }
  
  private static void queueUnenrollTask(String paramString, MAMIdentity paramMAMIdentity, MAMIdentityManager paramMAMIdentityManager, MAMServiceEnrollment paramMAMServiceEnrollment)
  {
    Object localObject = Services.get().getTableRepository();
    MAMLogScrubberImpl localMAMLogScrubberImpl = new MAMLogScrubberImpl(paramMAMIdentityManager, Services.get().getIDeploymentSettings().isProductionBuild().booleanValue() ^ true);
    Context localContext = Services.get().getContext();
    paramMAMIdentityManager = new MAMServiceLookupDatabaseCache((TableRepository)localObject, paramMAMIdentityManager).getMAMServiceUrl(paramMAMIdentity.canonicalUPN());
    if (paramMAMIdentityManager == null)
    {
      paramMAMIdentityManager = LOGGER;
      paramMAMServiceEnrollment = new StringBuilder();
      paramMAMServiceEnrollment.append("Unable to queue MAMService unenroll task for package: ");
      paramMAMServiceEnrollment.append(paramString);
      paramMAMServiceEnrollment.append(", identity: ");
      paramMAMServiceEnrollment.append(localMAMLogScrubberImpl.scrubUPN(paramMAMIdentity.rawUPN()));
      paramMAMServiceEnrollment.append("; no valid service URI found in cache.");
      paramMAMIdentityManager.warning(paramMAMServiceEnrollment.toString());
      return;
    }
    paramMAMIdentityManager = new MAMServiceUnenrollTask(paramMAMServiceEnrollment.packageName, paramMAMServiceEnrollment.identity, paramMAMServiceEnrollment.refreshToken, paramMAMIdentityManager, paramMAMServiceEnrollment.enrollmentId, paramMAMServiceEnrollment.deviceId);
    paramMAMServiceEnrollment = LOGGER;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Queueing MAMService unenroll task for package: ");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(", identity: ");
    ((StringBuilder)localObject).append(localMAMLogScrubberImpl.scrubUPN(paramMAMIdentity.rawUPN()));
    paramMAMServiceEnrollment.info(((StringBuilder)localObject).toString());
    OMADMClientService.queueTask(localContext, paramMAMIdentityManager, "MAMService unenroll task");
  }
  
  private void recordADALConnectionDetails(String paramString, MAMIdentity paramMAMIdentity, ADALConnectionDetails paramADALConnectionDetails)
  {
    Object localObject = (MAMAdalConnectionDetails)this.tableRepository.get(new MAMAdalConnectionDetails.Key(paramString));
    if ((localObject == null) || (!((MAMAdalConnectionDetails)localObject).get().equals(paramADALConnectionDetails)))
    {
      localObject = LOGGER;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Recording ADAL connection details for ");
      localStringBuilder.append(this.mLogScrubber.scrubUPN(paramMAMIdentity.rawUPN()));
      localStringBuilder.append(" : ");
      localStringBuilder.append(paramADALConnectionDetails.toString());
      ((Logger)localObject).info(localStringBuilder.toString());
      paramString = new MAMAdalConnectionDetails(paramString, paramADALConnectionDetails.getClientId(), paramADALConnectionDetails.getAuthority(), paramADALConnectionDetails.getNonBrokerRedirectUri(), Boolean.valueOf(paramADALConnectionDetails.getSkipBroker()));
      this.tableRepository.insertOrReplace(paramString);
    }
  }
  
  private void replaceCurrentPolicyWithPendingPolicy(String paramString, boolean paramBoolean)
  {
    this.tableRepository.beginTransaction();
    try
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = paramString;
      if (this.tableRepository.get(PendingApplicationPolicyProperty.class, "FullPackageName = ?", arrayOfString).isEmpty())
      {
        LOGGER.log(Level.FINE, "No pending policy to apply.");
        return;
      }
      LOGGER.log(Level.FINE, "Attempting to update current policy with pending policy.");
      if (paramBoolean) {
        this.tableRepository.delete(CurrentApplicationPolicyProperty.class, "FullPackageName = ?", arrayOfString);
      }
      this.tableRepository.execSQL("INSERT OR REPLACE INTO CurrentApplicationPolicy SELECT NULL, FullPackageName,PropertyName,PropertyType,PropertyValue FROM PendingApplicationPolicy WHERE FullPackageName = ?", arrayOfString);
      this.tableRepository.delete(PendingApplicationPolicyProperty.class, "FullPackageName = ?", arrayOfString);
      this.tableRepository.setTransactionSuccessful();
      return;
    }
    finally
    {
      this.tableRepository.endTransaction();
    }
  }
  
  public boolean checkIsDeviceCompliant()
  {
    return this.deviceOrigin.getDeviceOriginInvalid() ^ true;
  }
  
  public void clearCachedMAMServiceUrl(String paramString1, String paramString2)
  {
    new MAMServiceLookupDatabaseCache(this.tableRepository, this.mamIdentityManager).clearMAMServiceUrl(paramString2);
  }
  
  public MAMEnrollmentManager.Result enrollPackageForMAM(String paramString1, String arg2, String paramString3, String paramString4, String paramString5, ADALConnectionDetails paramADALConnectionDetails)
  {
    MAMIdentityImpl localMAMIdentityImpl = this.mamIdentityManager.fromString(???);
    this.tokenManager.cacheToken(paramString4, localMAMIdentityImpl);
    synchronized (sEnrollLock)
    {
      Object localObject1 = EnrolledUserUtils.getDeviceOwnerIdentity();
      if ((localObject1 != null) && (!localObject1.equals(localMAMIdentityImpl)))
      {
        localObject2 = LOGGER;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("The device is MDM-enrolled for user ");
        localStringBuilder.append(this.mLogScrubber.scrubUPN(((MAMIdentity)localObject1).rawUPN()));
        localStringBuilder.append(" therefore user ");
        localStringBuilder.append(this.mLogScrubber.scrubUPN(localMAMIdentityImpl.rawUPN()));
        localStringBuilder.append(" cannot be enrolled in the MAM Service. Checking license status.");
        ((Logger)localObject2).warning(localStringBuilder.toString());
        queueLicenseCheck(paramString1, paramString3, paramString4, paramString5, localMAMIdentityImpl, paramADALConnectionDetails);
        paramString1 = MAMEnrollmentManager.Result.PENDING;
        return paramString1;
      }
      localObject1 = (MAMServiceEnrollment)this.tableRepository.get(new MAMServiceEnrollment.Key(paramString1));
      if (localObject1 == null)
      {
        localObject1 = this.tableRepository.getAll(MAMServiceEnrollment.class);
        if ((!((List)localObject1).isEmpty()) && (!((MAMServiceEnrollment)((List)localObject1).get(0)).identity.equals(localMAMIdentityImpl)))
        {
          localObject1 = LOGGER;
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Package ");
          ((StringBuilder)localObject2).append(paramString1);
          ((StringBuilder)localObject2).append(" cannot be enrolled for identity ");
          ((StringBuilder)localObject2).append(this.mLogScrubber.scrubUPN(localMAMIdentityImpl.rawUPN()));
          ((StringBuilder)localObject2).append("; other packages are already enrolled with a different identity. Checking license status.");
          ((Logger)localObject1).warning(((StringBuilder)localObject2).toString());
          queueLicenseCheck(paramString1, paramString3, paramString4, paramString5, localMAMIdentityImpl, paramADALConnectionDetails);
          paramString1 = MAMEnrollmentManager.Result.PENDING;
          return paramString1;
        }
        localObject1 = LOGGER;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("No MAM enrollment found for ");
        ((StringBuilder)localObject2).append(paramString1);
        ((StringBuilder)localObject2).append(" and ");
        ((StringBuilder)localObject2).append(this.mLogScrubber.scrubUPN(localMAMIdentityImpl.rawUPN()));
        ((StringBuilder)localObject2).append("; queuing enrollment task.");
        ((Logger)localObject1).info(((StringBuilder)localObject2).toString());
        recordADALConnectionDetails(paramString1, localMAMIdentityImpl, paramADALConnectionDetails);
        paramString1 = new MAMServiceEnrollmentTask(paramString1, localMAMIdentityImpl, paramString3);
        paramString1.setMamServiceToken(paramString4);
        paramString1.setOperationSessionGuid(paramString5);
        OMADMClientService.queueTask(this.context, paramString1, "MAMService enrollment", true);
        paramString1 = MAMEnrollmentManager.Result.PENDING;
        return paramString1;
      }
      if (!((MAMServiceEnrollment)localObject1).identity.equals(localMAMIdentityImpl))
      {
        localObject1 = LOGGER;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Package ");
        ((StringBuilder)localObject2).append(paramString1);
        ((StringBuilder)localObject2).append(" cannot be enrolled for identity ");
        ((StringBuilder)localObject2).append(this.mLogScrubber.scrubUPN(localMAMIdentityImpl.rawUPN()));
        ((StringBuilder)localObject2).append("; it is already enrolled with a different identity. Checking license status.");
        ((Logger)localObject1).warning(((StringBuilder)localObject2).toString());
        queueLicenseCheck(paramString1, paramString3, paramString4, paramString5, localMAMIdentityImpl, paramADALConnectionDetails);
        paramString1 = MAMEnrollmentManager.Result.PENDING;
        return paramString1;
      }
      paramString5 = LOGGER;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("MAM enrollment found for ");
      ((StringBuilder)localObject2).append(paramString1);
      ((StringBuilder)localObject2).append(" and ");
      ((StringBuilder)localObject2).append(this.mLogScrubber.scrubUPN(localMAMIdentityImpl.rawUPN()));
      ((StringBuilder)localObject2).append("; id = ");
      ((StringBuilder)localObject2).append(((MAMServiceEnrollment)localObject1).enrollmentId);
      paramString5.info(((StringBuilder)localObject2).toString());
      ((MAMServiceEnrollment)localObject1).refreshToken = paramString3;
      ((MAMServiceEnrollment)localObject1).isAutoEnrollment = Boolean.valueOf(false);
      this.tableRepository.insertOrReplace((DataObject)localObject1);
      this.tableRepository.insertOrReplace(new MAMServiceAutoEnrollmentDetails(paramString1, Boolean.valueOf(true)));
      recordADALConnectionDetails(paramString1, localMAMIdentityImpl, paramADALConnectionDetails);
      queueCheckinIfDue(paramString1, (MAMServiceEnrollment)localObject1, paramString4);
      paramString1 = MAMEnrollmentManager.Result.ENROLLMENT_SUCCEEDED;
      return paramString1;
    }
  }
  
  public String getAppConfigData(String paramString1, String paramString2)
  {
    return this.appConfigHelper.getAppConfigJson(paramString1, this.mamIdentityManager.fromString(paramString2));
  }
  
  public String getCachedMAMServiceUrl(String paramString1, String paramString2)
  {
    return new MAMServiceLookupDatabaseCache(this.tableRepository, this.mamIdentityManager).getMAMServiceUrl(paramString2);
  }
  
  public Bundle getCurrentFileEncryptionKey()
    throws MAMKeyRetrievalException
  {
    try
    {
      Bundle localBundle = this.fileEncryptionKeyManager.getCurrentKey().getBundle();
      return localBundle;
    }
    catch (OMADMException localOMADMException)
    {
      throw new MAMKeyRetrievalException("Failed to get current file encryption key.", localOMADMException);
    }
  }
  
  public String getEnrolledUserAnyPackage()
  {
    MAMIdentity localMAMIdentity = EnrolledUserUtils.getEnrolledUserAnyPackage();
    if (localMAMIdentity == null) {
      return null;
    }
    return localMAMIdentity.rawUPN();
  }
  
  public Bundle getFileEncryptionKey(UUID paramUUID)
    throws MAMKeyRetrievalException
  {
    try
    {
      Object localObject = LOGGER;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Getting file encryption key for key id: ");
      localStringBuilder.append(paramUUID);
      ((Logger)localObject).info(localStringBuilder.toString());
      localObject = this.fileEncryptionKeyManager.getKey(paramUUID).getBundle();
      return localObject;
    }
    catch (OMADMException localOMADMException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Failed to get encryption key with id: ");
      localStringBuilder.append(paramUUID);
      localStringBuilder.append(".");
      throw new MAMKeyRetrievalException(localStringBuilder.toString(), localOMADMException);
    }
  }
  
  public List<MAMIdentityImpl> getIdentities()
  {
    this.mamIdentityManager.getAdditionalIdentityData();
    return this.mamIdentityManager.getIdentities();
  }
  
  public List<MAMIdentityMetaData> getIdentityMetaData()
  {
    return this.mamIdentityManager.getIdentityMetaData();
  }
  
  public DatabaseAppPolicy getPolicyForPackage(String paramString, PolicyUpdateType paramPolicyUpdateType, int paramInt)
  {
    Object localObject1 = (MAMServiceEnrollment)this.tableRepository.get(new MAMServiceEnrollment.Key(paramString));
    boolean bool2 = false;
    int i;
    if (localObject1 != null) {
      i = 1;
    } else {
      i = 0;
    }
    if ((paramPolicyUpdateType == PolicyUpdateType.INITIAL_UPDATE) && (i != 0) && (((MAMServiceEnrollment)localObject1).checkinAtLaunch.booleanValue()))
    {
      localObject2 = LOGGER;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("App is configured to checkin at launch. Queueing checkin task for package: ");
      ((StringBuilder)localObject3).append(((MAMServiceEnrollment)localObject1).packageName);
      ((StringBuilder)localObject3).append(", identity: ");
      ((StringBuilder)localObject3).append(this.mLogScrubber.scrubUPN(((MAMServiceEnrollment)localObject1).identity.rawUPN()));
      ((Logger)localObject2).info(((StringBuilder)localObject3).toString());
      MAMServiceUtils.queueCheckin((MAMServiceEnrollment)localObject1, this.context, true, null);
    }
    boolean bool1 = bool2;
    if (i != 0)
    {
      bool1 = bool2;
      if (((MAMServiceEnrollment)localObject1).getHasPolicy()) {
        bool1 = true;
      }
    }
    if (!isManagedApp(paramString)) {
      return null;
    }
    Object localObject3 = new DatabaseAppPolicy(this.tableRepository, paramString, bool1);
    Object localObject2 = ((DatabaseAppPolicy)localObject3).getPendingPolicyTemplateVersion();
    localObject1 = localObject2;
    if (localObject2 == null)
    {
      LOGGER.log(Level.FINE, "There is no pending policy version. Falling back to the current policy version or default.");
      localObject1 = ((DatabaseAppPolicy)localObject3).getPolicyTemplateVersion();
    }
    localObject2 = LOGGER;
    Level localLevel = Level.FINE;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Effective pending policy version is: ");
    localStringBuilder.append((String)localObject1);
    ((Logger)localObject2).log(localLevel, localStringBuilder.toString());
    if ((paramPolicyUpdateType == PolicyUpdateType.INITIAL_UPDATE) || (PolicyVersionUtils.isSupportedPolicyVersion((String)localObject1, paramInt))) {
      replaceCurrentPolicyWithPendingPolicy(paramString, bool1);
    }
    return localObject3;
  }
  
  public PrimaryUserInfo getPrimaryUserInfo(String paramString)
  {
    paramString = EnrolledUserUtils.getEnrolledUser(paramString);
    if (paramString == null) {
      return new PrimaryUserInfo(null, null, null, null, null, null);
    }
    MAMIdentity localMAMIdentity = EnrolledUserUtils.getDeviceOwnerIdentity();
    String str3 = paramString.rawUPN();
    String str4 = paramString.aadId();
    String str5 = paramString.authority();
    String str2 = null;
    if (localMAMIdentity == null) {
      paramString = null;
    } else {
      paramString = localMAMIdentity.rawUPN();
    }
    String str1;
    if (localMAMIdentity == null) {
      str1 = null;
    } else {
      str1 = localMAMIdentity.aadId();
    }
    if (localMAMIdentity != null) {
      for (;;)
      {
        str2 = localMAMIdentity.authority();
      }
    }
    return new PrimaryUserInfo(str3, str4, str5, paramString, str1, str2);
  }
  
  public HashSet<Signature> getSignaturesForPackage(String paramString)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.tableRepository.getAll(ApplicationSignature.class).iterator();
    while (localIterator.hasNext())
    {
      ApplicationSignature localApplicationSignature = (ApplicationSignature)localIterator.next();
      if (paramString.equals(localApplicationSignature.packageName)) {
        localHashSet.add(new Signature(localApplicationSignature.signature));
      }
    }
    return localHashSet;
  }
  
  public String getUPNIdentifierForLogging(String paramString)
  {
    return this.mamIdentityManager.getUPNIdentifierForLogging(paramString);
  }
  
  public int getUserPINMaxRetries(String paramString)
  {
    return getMinimumIntegerPolicyValue("PINNumRetry", 5);
  }
  
  public boolean hasAppsWithAppConfig()
  {
    return this.appConfigHelper.deviceHasAppsWithAppConfig();
  }
  
  public boolean hasManagedApps()
  {
    return (!this.tableRepository.isTableEmpty(CurrentApplicationPolicyProperty.class)) || (!this.tableRepository.isTableEmpty(PendingApplicationPolicyProperty.class));
  }
  
  public void heartbeat(String paramString)
  {
    Logger localLogger = LOGGER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("received heartbeat for package: ");
    localStringBuilder.append(paramString);
    localLogger.info(localStringBuilder.toString());
    queueCheckinIfDue(paramString, (MAMServiceEnrollment)this.tableRepository.get(new MAMServiceEnrollment.Key(paramString)), null);
  }
  
  public boolean isARPFeatureAvailable()
  {
    return this.deploymentSettings.isARPFeatureAvailable().booleanValue();
  }
  
  public boolean isAutoEnrolledWithToken(String paramString)
  {
    paramString = (MAMServiceEnrollment)this.tableRepository.get(new MAMServiceEnrollment.Key(paramString));
    if ((paramString != null) && (paramString.getIsAutoEnrollment())) {
      return MAMServiceUtils.getEnrollmentsWithTokenOrBroker().isEmpty() ^ true;
    }
    return false;
  }
  
  public boolean isCheckinTimeoutExceeded(String paramString)
  {
    paramString = (MAMServiceEnrollment)this.tableRepository.get(new MAMServiceEnrollment.Key(paramString));
    if (paramString == null) {
      return false;
    }
    return paramString.offlineTimeoutExceeded();
  }
  
  public boolean isMDMPasswordPolicyCompliant()
  {
    return MAMDevicePolicyUtils.isDevicePasswordCompliant();
  }
  
  public boolean isManagedApp(String paramString)
  {
    return isManagedAppInternal(paramString, this.tableRepository);
  }
  
  public boolean isPackageEnrolledForMAM(String paramString1, String paramString2)
  {
    paramString1 = (MAMServiceEnrollment)this.tableRepository.get(new MAMServiceEnrollment.Key(paramString1));
    if (paramString1 != null) {
      return paramString1.identity.equals(this.mamIdentityManager.fromString(paramString2));
    }
    return false;
  }
  
  public boolean isProductionBuild()
  {
    return this.deploymentSettings.isProductionBuild().booleanValue();
  }
  
  public boolean isVerboseLoggingEnabled()
  {
    return Services.get().getDiagnosticSettings().getVerboseLoggingEnabled();
  }
  
  public boolean isWipeInProgress(String paramString)
  {
    paramString = this.appPolicyNotifier.getAppDataWipeStatus(paramString);
    return (paramString == WipeAppDataStatus.INITIATED_SELECTIVE_WIPE) || (paramString == WipeAppDataStatus.LOADING_INTERNAL_SELECTIVE_WIPE);
  }
  
  public boolean isXposeDetected()
  {
    return Services.get().getXposedCloakInstalledTest().execute() != 0;
  }
  
  public void logTelemetryEvent(TelemetryEvent paramTelemetryEvent)
  {
    if (paramTelemetryEvent == null)
    {
      LOGGER.warning("Ignoring null telemetry event.");
      return;
    }
    Services.get().getOMADMTelemetry().sendMAMTelemetryEvent(paramTelemetryEvent);
  }
  
  public void notifyAppDataWipeStatus(String paramString, WipeAppDataStatus paramWipeAppDataStatus)
  {
    AppPolicyNotifier localAppPolicyNotifier = this.appPolicyNotifier;
    AppPolicyNotifier.notifyAppDataWipeStatus(paramString, paramWipeAppDataStatus);
  }
  
  public void onMAMAppInstall(String paramString1, String paramString2)
  {
    Services.get().getAppInstallationReceiver().onAppInstalled(this.context, paramString1, paramString2);
  }
  
  public void persistIdentity(MAMIdentityImpl paramMAMIdentityImpl)
  {
    this.mamIdentityManager.trackIdentity(paramMAMIdentityImpl);
  }
  
  public void persistIdentityMetaData(MAMIdentityMetaData paramMAMIdentityMetaData)
  {
    this.mamIdentityManager.trackIdentityMetaData(paramMAMIdentityMetaData);
  }
  
  public Bundle prefetchCurrentFileEncryptionKey(String paramString)
    throws MAMKeyRetrievalException
  {
    try
    {
      paramString = DatabaseAppPolicy.getItem(this.tableRepository, paramString, "RequireFileEncryption");
      if ((paramString != null) && (paramString.getBooleanValue()))
      {
        paramString = this.fileEncryptionKeyManager.getCurrentKey().getBundle();
        return paramString;
      }
      return null;
    }
    catch (OMADMException paramString)
    {
      throw new MAMKeyRetrievalException("Failed to get current file encryption key.", paramString);
    }
  }
  
  public void setCachedMAMServiceUrl(String paramString1, String paramString2, String paramString3) {}
  
  public void startOfflineGracePeriodTimer(String paramString)
  {
    if ((MAMServiceEnrollment)this.tableRepository.get(new MAMServiceEnrollment.Key(paramString)) == null) {
      return;
    }
    if ((!DeviceInfo.isNetworkConnected(this.context)) && (!this.mOfflineGracePeriodTimer.isStarted())) {
      this.mOfflineGracePeriodTimer.restartTimer();
    }
  }
  
  public MAMEnrollmentManager.Result unenrollPackageForMAM(String paramString1, String paramString2, WipeReason paramWipeReason)
  {
    return internalUnenrollPackageForMAM(paramString1, this.mamIdentityManager.fromString(paramString2), this.appPolicyNotifier, this.mamIdentityManager, paramWipeReason);
  }
  
  public void updateMAMServiceToken(String paramString1, MAMIdentityImpl paramMAMIdentityImpl, String paramString2)
  {
    this.tokenManager.cacheToken(paramString2, paramMAMIdentityImpl);
    queueCheckinIfDue(paramString1, (MAMServiceEnrollment)this.tableRepository.get(new MAMServiceEnrollment.Key(paramString1)), paramString2);
  }
}
