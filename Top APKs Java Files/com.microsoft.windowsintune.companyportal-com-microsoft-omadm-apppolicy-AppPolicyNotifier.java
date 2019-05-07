package com.microsoft.omadm.apppolicy;

import android.content.Context;
import com.microsoft.intune.common.enrollment.datacomponent.implementation.EnrollmentSettings;
import com.microsoft.intune.mam.client.identity.MAMIdentity;
import com.microsoft.intune.mam.client.identity.MAMIdentityManager;
import com.microsoft.intune.mam.client.identity.MAMIdentityManagerImpl;
import com.microsoft.intune.mam.log.MAMLogScrubber;
import com.microsoft.intune.mam.policy.notification.AbstractAppPolicyNotifier;
import com.microsoft.omadm.apppolicy.appconfig.AppConfigHelper;
import com.microsoft.omadm.apppolicy.data.ApplicationConfigurationSettings;
import com.microsoft.omadm.apppolicy.data.CurrentApplicationPolicyProperty;
import com.microsoft.omadm.apppolicy.data.PendingApplicationPolicyProperty;
import com.microsoft.omadm.database.TableRepository;
import com.microsoft.omadm.utils.PackageUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public final class AppPolicyNotifier
  extends AbstractAppPolicyNotifier
{
  private static final Logger LOGGER = Logger.getLogger(AppPolicyNotifier.class.getName());
  private final Set<String> alwaysWipedPackages = new HashSet(Arrays.asList(new String[] { "com.microsoft.skydrive", "com.microsoft.exchange.mowa", "com.microsoft.office.outlook", "com.microsoft.office.outlook.sbx", "com.microsoft.office.outlook.dev", "com.microsoft.office.outlook.stg" }));
  private final AppConfigHelper appConfigHelper;
  private final EnrollmentSettings enrollmentSettings;
  private final TableRepository tableRepository;
  
  private AppPolicyNotifier(Context paramContext, EnrollmentSettings paramEnrollmentSettings, MAMIdentityManager paramMAMIdentityManager, MAMLogScrubber paramMAMLogScrubber)
  {
    this(paramContext, paramEnrollmentSettings, paramMAMIdentityManager, new AppConfigHelper(TableRepository.getInstance(paramContext, paramMAMIdentityManager), paramMAMIdentityManager, paramMAMLogScrubber), TableRepository.getInstance(paramContext, paramMAMIdentityManager));
  }
  
  public AppPolicyNotifier(Context paramContext, EnrollmentSettings paramEnrollmentSettings, MAMIdentityManager paramMAMIdentityManager, AppConfigHelper paramAppConfigHelper, TableRepository paramTableRepository)
  {
    super(paramContext, paramMAMIdentityManager);
    this.appConfigHelper = paramAppConfigHelper;
    this.enrollmentSettings = paramEnrollmentSettings;
    this.tableRepository = paramTableRepository;
  }
  
  public AppPolicyNotifier(Context paramContext, EnrollmentSettings paramEnrollmentSettings, MAMLogScrubber paramMAMLogScrubber)
  {
    this(paramContext, paramEnrollmentSettings, new MAMIdentityManagerImpl(null), paramMAMLogScrubber);
  }
  
  private Set<String> getInstalledPackagesWithPolicy()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.tableRepository.get(CurrentApplicationPolicyProperty.class, "PropertyName = 'IsManaged'", null).iterator();
    Object localObject;
    Logger localLogger;
    StringBuilder localStringBuilder;
    while (localIterator.hasNext())
    {
      localObject = (CurrentApplicationPolicyProperty)localIterator.next();
      if (PackageUtils.isPackageInstalled(this.mContext, ((CurrentApplicationPolicyProperty)localObject).fullPackageName))
      {
        localLogger = LOGGER;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Found ");
        localStringBuilder.append(((CurrentApplicationPolicyProperty)localObject).fullPackageName);
        localStringBuilder.append(" with current app policy.");
        localLogger.fine(localStringBuilder.toString());
        localHashSet.add(((CurrentApplicationPolicyProperty)localObject).fullPackageName);
      }
    }
    localIterator = this.tableRepository.get(PendingApplicationPolicyProperty.class, "PropertyName = 'IsManaged'", null).iterator();
    while (localIterator.hasNext())
    {
      localObject = (PendingApplicationPolicyProperty)localIterator.next();
      if (PackageUtils.isPackageInstalled(this.mContext, ((PendingApplicationPolicyProperty)localObject).fullPackageName))
      {
        localLogger = LOGGER;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Found ");
        localStringBuilder.append(((PendingApplicationPolicyProperty)localObject).fullPackageName);
        localStringBuilder.append(" with pending app policy.");
        localLogger.fine(localStringBuilder.toString());
        localHashSet.add(((PendingApplicationPolicyProperty)localObject).fullPackageName);
      }
    }
    localIterator = this.tableRepository.getAll(ApplicationConfigurationSettings.class).iterator();
    while (localIterator.hasNext())
    {
      localObject = (ApplicationConfigurationSettings)localIterator.next();
      if (PackageUtils.isPackageInstalled(this.mContext, ((ApplicationConfigurationSettings)localObject).fullPackageName))
      {
        localLogger = LOGGER;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Found ");
        localStringBuilder.append(((ApplicationConfigurationSettings)localObject).fullPackageName);
        localStringBuilder.append(" with app config.");
        localLogger.fine(localStringBuilder.toString());
        localHashSet.add(((ApplicationConfigurationSettings)localObject).fullPackageName);
      }
    }
    return localHashSet;
  }
  
  protected void clearPolicy(String paramString1, String paramString2)
  {
    this.tableRepository.beginTransaction();
    try
    {
      paramString2 = new String[1];
      paramString2[0] = paramString1;
      this.tableRepository.delete(CurrentApplicationPolicyProperty.class, "FullPackageName = ?", paramString2);
      this.tableRepository.delete(PendingApplicationPolicyProperty.class, "FullPackageName = ?", paramString2);
      this.tableRepository.setTransactionSuccessful();
      return;
    }
    finally
    {
      this.tableRepository.endTransaction();
    }
  }
  
  public String getAppConfigJson(String paramString, MAMIdentity paramMAMIdentity)
  {
    Logger localLogger = LOGGER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Getting app config for ");
    localStringBuilder.append(paramString);
    localLogger.info(localStringBuilder.toString());
    return this.appConfigHelper.getAppConfigJson(paramString, paramMAMIdentity);
  }
  
  protected MAMIdentity getIdentity(String paramString)
  {
    return EnrolledUserUtils.getEnrolledUser(paramString);
  }
  
  protected Set<String> getPackagesForMAMLogs()
  {
    return getInstalledPackagesWithPolicy();
  }
  
  public Set<String> getPackagesForRefresh()
  {
    return getInstalledPackagesWithPolicy();
  }
  
  public Set<String> getPackagesForWipe()
  {
    HashSet localHashSet = new HashSet();
    Set localSet = getInstalledPackagesWithPolicy();
    localSet.addAll(this.alwaysWipedPackages);
    Iterator localIterator = PackageUtils.getMAMPackages(this.mContext).iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Logger localLogger;
      StringBuilder localStringBuilder;
      if (localSet.contains(str))
      {
        localLogger = LOGGER;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(str);
        localStringBuilder.append(" should be wiped.");
        localLogger.fine(localStringBuilder.toString());
        localHashSet.add(str);
      }
      else
      {
        localLogger = LOGGER;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(str);
        localStringBuilder.append(" is not being wiped because it either doesn't have policy or isn't always wiped.");
        localLogger.fine(localStringBuilder.toString());
      }
    }
    LOGGER.info(String.format("Packages to wipe: %s", new Object[] { localHashSet }));
    return localHashSet;
  }
  
  public void notifyPrimaryUserRemoved(String paramString)
  {
    Iterator localIterator = PackageUtils.getMAMPackages(this.mContext).iterator();
    while (localIterator.hasNext()) {
      primaryUserRemoved((String)localIterator.next(), paramString);
    }
  }
  
  public void refreshPolicy(Set<String> paramSet)
  {
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      String str = (String)paramSet.next();
      refreshPolicy(str, EnrolledUserUtils.getEnrolledUser(str));
    }
  }
}
