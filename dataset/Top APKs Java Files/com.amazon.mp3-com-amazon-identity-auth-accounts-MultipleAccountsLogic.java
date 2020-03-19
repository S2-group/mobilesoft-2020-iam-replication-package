package com.amazon.identity.auth.accounts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.MultipleAccountManager.AccountMappingType;
import com.amazon.identity.auth.device.api.MultipleAccountManager.CustomKeyMappingType;
import com.amazon.identity.auth.device.api.MultipleAccountManager.PackageMappingType;
import com.amazon.identity.auth.device.api.MultipleAccountManager.PrimaryUserMappingType;
import com.amazon.identity.auth.device.api.MultipleAccountManager.SessionUserMappingType;
import com.amazon.identity.auth.device.framework.PlatformWrapper;
import com.amazon.identity.auth.device.framework.ServiceWrappingContext;
import com.amazon.identity.auth.device.framework.TrustedPackageManager;
import com.amazon.identity.auth.device.framework.Value;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.storage.DataStorage;
import com.amazon.identity.auth.device.utils.ArrayUtil;
import com.amazon.identity.auth.device.utils.MAPLog;
import com.amazon.identity.auth.device.utils.StringConversionHelpers;
import com.amazon.identity.auth.device.utils.UnitTestUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class MultipleAccountsLogic
  implements MultipleAccountDefinition
{
  private static final String KEY_DELIMITER = ",";
  private static final String TAG = MultipleAccountsLogic.class.getName();
  private static MultipleAccountsLogic sTheOneAndOnly;
  private final Map<List<MultipleAccountManager.AccountMappingType>, Value<String>> mAccountMappingsToAccountCache;
  private final AmazonAccountManager mAmznAcctMan;
  private final Context mContext;
  private final PlatformWrapper mPlatform;
  private final TrustedPackageManager mTrustedPackageManager;
  
  MultipleAccountsLogic(Context paramContext)
  {
    this.mContext = ServiceWrappingContext.create(paramContext);
    this.mAmznAcctMan = ((AmazonAccountManager)this.mContext.getSystemService("dcp_amazon_account_man"));
    this.mPlatform = ((PlatformWrapper)this.mContext.getSystemService("sso_platform"));
    this.mTrustedPackageManager = new TrustedPackageManager(this.mContext);
    this.mAccountMappingsToAccountCache = new HashMap();
  }
  
  private String calculatetAccountForMapping(MultipleAccountManager.AccountMappingType... paramVarArgs)
  {
    Object localObject = filterOutInvalidMappingsAndCreateMapplingLogic(paramVarArgs);
    paramVarArgs = this.mAmznAcctMan.getNonDeregisteringAmazonAccounts();
    AccountMappingLogic localAccountMappingLogic;
    String str;
    do
    {
      localObject = ((List)localObject).iterator();
      Iterator localIterator;
      while (!localIterator.hasNext())
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        localAccountMappingLogic = (AccountMappingLogic)((Iterator)localObject).next();
        localIterator = paramVarArgs.iterator();
      }
      str = (String)localIterator.next();
    } while (!localAccountMappingLogic.isMappingCurrentlySetForAccount(str));
    return str;
    return null;
  }
  
  private AccountMappingLogic constructMappingLogic(MultipleAccountManager.AccountMappingType paramAccountMappingType)
  {
    if (paramAccountMappingType == null)
    {
      MAPLog.e(TAG, "Account Mapping Type given was null. Ignoring");
      return null;
    }
    String str = paramAccountMappingType.getAccountMappingType();
    if ("com.amazon.dcp.sso.property.sessionuser".equals(str)) {
      return new SessionUserMappingLogic(this.mContext, paramAccountMappingType);
    }
    if ("com.amazon.dcp.sso.property.account.extratokens.account_profiles".equals(str)) {
      return constructPrimaryMappingLogicForThisPlatform(paramAccountMappingType);
    }
    if ("com.amazon.dcp.sso.property.account.extratokens.account_packages".equals(str)) {
      return new PackageMappingLogic(this.mContext, paramAccountMappingType);
    }
    if ("com.amazon.dcp.sso.property.account.extratokens.custom_keys".equals(str)) {
      return new CustomKeyMappingLogic(this.mContext, paramAccountMappingType);
    }
    if ("primary_account_type".equals(str)) {
      return new DevicePrimaryUserMappingLogic(this.mContext, paramAccountMappingType);
    }
    MAPLog.e(TAG, "Account mapping type %s was not recongized", new String[] { str });
    return null;
  }
  
  private AccountMappingLogic constructPrimaryMappingLogicForThisPlatform(MultipleAccountManager.AccountMappingType paramAccountMappingType)
  {
    if ((isProfilePrimaryPlatform(this.mContext)) || (!isDefaultDeviceUser(paramAccountMappingType))) {
      return new ProfilePrimaryMappingLogic(this.mContext, paramAccountMappingType);
    }
    return new DevicePrimaryUserMappingLogic(this.mContext, paramAccountMappingType);
  }
  
  private List<AccountMappingLogic> filterOutInvalidMappingsAndCreateMapplingLogic(MultipleAccountManager.AccountMappingType... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramVarArgs == null) {
      return localArrayList;
    }
    int j = paramVarArgs.length;
    int i = 0;
    label21:
    MultipleAccountManager.AccountMappingType localAccountMappingType;
    AccountMappingLogic localAccountMappingLogic;
    if (i < j)
    {
      localAccountMappingType = paramVarArgs[i];
      localAccountMappingLogic = constructMappingLogic(localAccountMappingType);
      if (localAccountMappingLogic != null) {
        break label51;
      }
    }
    for (;;)
    {
      i += 1;
      break label21;
      break;
      label51:
      if (!localAccountMappingLogic.isSupportedOnThisPlatform()) {
        MAPLog.d(TAG, "Mapping Type %s is not supported on this platform. Ignoring", new String[] { localAccountMappingType.getAccountMappingType() });
      } else {
        localArrayList.add(localAccountMappingLogic);
      }
    }
  }
  
  private List<MultipleAccountManager.AccountMappingType> getAccountMappingTypeKey(MultipleAccountManager.AccountMappingType... paramVarArgs)
  {
    if (paramVarArgs == null) {
      return null;
    }
    return Arrays.asList(paramVarArgs);
  }
  
  private Set<String> getAllAmazonPackageNames()
  {
    Object localObject = this.mTrustedPackageManager.getInstalledPackages();
    HashSet localHashSet = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localHashSet.add(((PackageInfo)((Iterator)localObject).next()).packageName);
    }
    return localHashSet;
  }
  
  public static MultipleAccountsLogic getInstance(Context paramContext)
  {
    try
    {
      if ((sTheOneAndOnly == null) || (UnitTestUtils.isRunningInUnitTest())) {
        sTheOneAndOnly = new MultipleAccountsLogic(paramContext.getApplicationContext());
      }
      paramContext = sTheOneAndOnly;
      return paramContext;
    }
    finally {}
  }
  
  private Set<String> getPackagesToSendTo(String paramString, Set<String> paramSet)
  {
    if (paramString == null) {
      return paramSet;
    }
    paramSet = new HashSet();
    paramSet.add(paramString);
    return paramSet;
  }
  
  private void invalidateCache()
  {
    synchronized (this.mAccountMappingsToAccountCache)
    {
      this.mAccountMappingsToAccountCache.clear();
      return;
    }
  }
  
  private boolean isDefaultDeviceUser(MultipleAccountManager.AccountMappingType paramAccountMappingType)
  {
    return Integer.toString(0).equals(paramAccountMappingType.getAccountMappingValue());
  }
  
  public static boolean isProfilePrimaryPlatform(Context paramContext)
  {
    return ((PlatformWrapper)ServiceWrappingContext.create(paramContext).getSystemService("sso_platform")).shouldUseProfiles();
  }
  
  private void notifyOnAccountChanged(List<NotificationInfo> paramList)
  {
    if (paramList.size() == 0) {}
    for (;;)
    {
      return;
      Object localObject2 = getAllAmazonPackageNames();
      HashMap localHashMap = new HashMap();
      Iterator localIterator = paramList.iterator();
      Object localObject1;
      while (localIterator.hasNext())
      {
        NotificationInfo localNotificationInfo = (NotificationInfo)localIterator.next();
        if (localNotificationInfo.extras != null)
        {
          paramList = new Intent(localNotificationInfo.action);
          paramList.putExtras(localNotificationInfo.extras);
          sendBroadcastToPackages(paramList, getPackagesToSendTo(localNotificationInfo.packageName, (Set)localObject2));
        }
        else
        {
          localObject1 = (Set)localHashMap.get(localNotificationInfo.action);
          paramList = (List<NotificationInfo>)localObject1;
          if (localObject1 == null)
          {
            paramList = new HashSet();
            localHashMap.put(localNotificationInfo.action, paramList);
          }
          paramList.addAll(getPackagesToSendTo(localNotificationInfo.packageName, (Set)localObject2));
        }
      }
      paramList = localHashMap.entrySet().iterator();
      while (paramList.hasNext())
      {
        localObject2 = (Map.Entry)paramList.next();
        localObject1 = (String)((Map.Entry)localObject2).getKey();
        localObject2 = (Set)((Map.Entry)localObject2).getValue();
        sendBroadcastToPackages(new Intent((String)localObject1), (Set)localObject2);
      }
    }
  }
  
  private void sendBroadcastToPackages(Intent paramIntent, Set<String> paramSet)
  {
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      String str = (String)paramSet.next();
      Intent localIntent = new Intent(paramIntent);
      localIntent.setPackage(str);
      this.mContext.sendBroadcast(localIntent, "com.amazon.dcp.sso.permission.account.changed");
    }
  }
  
  private boolean shouldCacheGetAccountForMapping()
  {
    return this.mPlatform.isRunningInCentralApk();
  }
  
  private void throwIfCannotWriteMultipleProfile()
  {
    if ((this.mPlatform.isNewIdentiyMobilePlatformSSO()) && (!shouldCacheGetAccountForMapping())) {
      throw new IllegalStateException("getAccount write call cannot be called from this app on this platform");
    }
  }
  
  public boolean doesAccountHaveMapping(String paramString, MultipleAccountManager.AccountMappingType paramAccountMappingType)
  {
    AccountMappingLogic localAccountMappingLogic = constructMappingLogic(paramAccountMappingType);
    if (!localAccountMappingLogic.isSupportedOnThisPlatform())
    {
      MAPLog.e(TAG, "Mapping Type %s is not supported on this platform. Ignoring", new String[] { paramAccountMappingType.getAccountMappingType() });
      return false;
    }
    return localAccountMappingLogic.isMappingCurrentlySetForAccount(paramString);
  }
  
  public String getAccountForMapping(MultipleAccountManager.AccountMappingType... paramVarArgs)
  {
    if (!shouldCacheGetAccountForMapping()) {
      return calculatetAccountForMapping(paramVarArgs);
    }
    List localList = getAccountMappingTypeKey(paramVarArgs);
    Object localObject = (Value)this.mAccountMappingsToAccountCache.get(localList);
    if (localObject != null) {
      return (String)((Value)localObject).getValue();
    }
    synchronized (this.mAccountMappingsToAccountCache)
    {
      Value localValue = (Value)this.mAccountMappingsToAccountCache.get(localList);
      localObject = localValue;
      if (localValue == null)
      {
        localObject = new Value(calculatetAccountForMapping(paramVarArgs));
        this.mAccountMappingsToAccountCache.put(localList, localObject);
      }
      paramVarArgs = (String)((Value)localObject).getValue();
      return paramVarArgs;
    }
  }
  
  public Set<String> getListOfProfilesWhereTheAccountIsPrimary(Context paramContext, String paramString)
  {
    return MetadataMappingClass.getMetadataForAccount(new BackwardsCompatiableDataStorage(ServiceWrappingContext.create(paramContext)), paramString, "com.amazon.dcp.sso.property.account.extratokens.account_profiles");
  }
  
  public boolean isAPrimaryAccount(String paramString)
  {
    if (!this.mAmznAcctMan.doesAccountExist(paramString)) {
      MAPLog.e(TAG, "The account does not exist so it cannot be a primary");
    }
    while ((ProfilePrimaryMappingLogic.constructProfileMappingForAccount(this.mContext, paramString).size() == 0) && (!DevicePrimaryUserMappingLogic.isDevicePlatformMappingSet(this.mAmznAcctMan, paramString))) {
      return false;
    }
    return true;
  }
  
  public void onAccountAdded()
  {
    invalidateCache();
  }
  
  public void onDeregister(String paramString)
  {
    throwIfCannotWriteMultipleProfile();
    ArrayList localArrayList;
    synchronized (this.mAccountMappingsToAccountCache)
    {
      if (!this.mAmznAcctMan.doesAccountExist(paramString))
      {
        MAPLog.e(TAG, "Cannot remove all account mappings since the account doesn't exist");
        return;
      }
      Object localObject = new ArrayList();
      ((List)localObject).addAll(SessionUserMappingLogic.constructSessionUserMappingsForAccount(this.mContext, paramString));
      ((List)localObject).addAll(ProfilePrimaryMappingLogic.constructProfileMappingForAccount(this.mContext, paramString));
      ((List)localObject).addAll(PackageMappingLogic.constructPackageMappingsForAccount(this.mContext, paramString));
      ((List)localObject).addAll(CustomKeyMappingLogic.constructCustomKeyMappingsForAccount(this.mContext, paramString));
      invalidateCache();
      localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      if (((Iterator)localObject).hasNext()) {
        localArrayList.addAll(((AccountMappingLogic)((Iterator)localObject).next()).onDeregister(paramString));
      }
    }
    notifyOnAccountChanged(localArrayList);
  }
  
  public boolean removeAccountMappings(String paramString, MultipleAccountManager.AccountMappingType... paramVarArgs)
  {
    boolean bool = false;
    throwIfCannotWriteMultipleProfile();
    synchronized (this.mAccountMappingsToAccountCache)
    {
      Object localObject = filterOutInvalidMappingsAndCreateMapplingLogic(paramVarArgs);
      if (!this.mAmznAcctMan.doesAccountExist(paramString))
      {
        MAPLog.e(TAG, "Cannot remove account mappings since it doesn't exist");
        return false;
      }
      invalidateCache();
      paramVarArgs = new ArrayList();
      localObject = ((List)localObject).iterator();
      if (((Iterator)localObject).hasNext()) {
        paramVarArgs.addAll(((AccountMappingLogic)((Iterator)localObject).next()).removeAndNotify(paramString));
      }
    }
    notifyOnAccountChanged(paramVarArgs);
    if (paramVarArgs.size() > 0) {
      bool = true;
    }
    return bool;
  }
  
  public boolean setAccountMappings(String paramString, MultipleAccountManager.AccountMappingType... paramVarArgs)
  {
    boolean bool = false;
    throwIfCannotWriteMultipleProfile();
    synchronized (this.mAccountMappingsToAccountCache)
    {
      Object localObject = filterOutInvalidMappingsAndCreateMapplingLogic(paramVarArgs);
      if ((!this.mAmznAcctMan.doesAccountExist(paramString)) || (this.mAmznAcctMan.isAccountDeregistering(paramString)))
      {
        MAPLog.e(TAG, "Cannot set account mappings since it doesn't exist or is deregistering");
        return false;
      }
      invalidateCache();
      paramVarArgs = new ArrayList();
      localObject = ((List)localObject).iterator();
      if (((Iterator)localObject).hasNext()) {
        paramVarArgs.addAll(((AccountMappingLogic)((Iterator)localObject).next()).setAndNotify(paramString));
      }
    }
    notifyOnAccountChanged(paramVarArgs);
    if (paramVarArgs.size() > 0) {
      bool = true;
    }
    return bool;
  }
  
  private static abstract interface AccountMappingLogic
  {
    public abstract boolean isMappingCurrentlySetForAccount(String paramString);
    
    public abstract boolean isSupportedOnThisPlatform();
    
    public abstract List<MultipleAccountsLogic.NotificationInfo> onDeregister(String paramString);
    
    public abstract List<MultipleAccountsLogic.NotificationInfo> removeAndNotify(String paramString);
    
    public abstract List<MultipleAccountsLogic.NotificationInfo> setAndNotify(String paramString);
  }
  
  private static class CustomKeyMappingLogic
    extends MultipleAccountsLogic.MetadataMappingClass
  {
    private final Context mContext;
    private final MultipleAccountManager.AccountMappingType mMapping;
    
    public CustomKeyMappingLogic(Context paramContext, MultipleAccountManager.AccountMappingType paramAccountMappingType)
    {
      super(paramAccountMappingType);
      this.mContext = paramContext;
      this.mMapping = paramAccountMappingType;
    }
    
    public static List<CustomKeyMappingLogic> constructCustomKeyMappingsForAccount(Context paramContext, String paramString)
    {
      paramContext = ServiceWrappingContext.create(paramContext);
      Object localObject = getMetadataForAccount(new BackwardsCompatiableDataStorage(paramContext), paramString, "com.amazon.dcp.sso.property.account.extratokens.custom_keys");
      paramString = new ArrayList();
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramString.add(new CustomKeyMappingLogic(paramContext, new MultipleAccountManager.CustomKeyMappingType((String)((Iterator)localObject).next())));
      }
      return paramString;
    }
    
    public boolean isSupportedOnThisPlatform()
    {
      return MultipleAccountManager.CustomKeyMappingType.isSupportedOnThisPlatform(this.mContext);
    }
    
    public List<MultipleAccountsLogic.NotificationInfo> notifyOfChange()
    {
      String str1 = this.mMapping.getAccountMappingValue();
      String str2 = "com.amazon.identity.action.ACCOUNT_FOR_KEY." + str1;
      Bundle localBundle = new Bundle();
      localBundle.putString("account_key", str1);
      return Arrays.asList(new MultipleAccountsLogic.NotificationInfo[] { new MultipleAccountsLogic.NotificationInfo(str2, null, localBundle), new MultipleAccountsLogic.NotificationInfo("com.amazon.identity.action.ACCOUNT_FOR_KEY", null, localBundle) });
    }
  }
  
  private static class DevicePrimaryUserMappingLogic
    implements MultipleAccountsLogic.AccountMappingLogic
  {
    private final AmazonAccountManager mAmznAcctMan;
    private final Context mContext;
    private final MultipleAccountManager.AccountMappingType mMapping;
    
    public DevicePrimaryUserMappingLogic(Context paramContext, MultipleAccountManager.AccountMappingType paramAccountMappingType)
    {
      this.mContext = ServiceWrappingContext.create(paramContext);
      this.mAmznAcctMan = ((AmazonAccountManager)this.mContext.getSystemService("dcp_amazon_account_man"));
      this.mMapping = paramAccountMappingType;
    }
    
    public static boolean isDevicePlatformMappingSet(AmazonAccountManager paramAmazonAccountManager, String paramString)
    {
      return paramAmazonAccountManager.isDevicePrimaryAmazonAccount(paramString);
    }
    
    public boolean isMappingCurrentlySetForAccount(String paramString)
    {
      return isDevicePlatformMappingSet(this.mAmznAcctMan, paramString);
    }
    
    public boolean isSupportedOnThisPlatform()
    {
      return MultipleAccountManager.PrimaryUserMappingType.isSupportedOnThisPlatform(this.mContext);
    }
    
    public List<MultipleAccountsLogic.NotificationInfo> onDeregister(String paramString)
    {
      MAPLog.d(MultipleAccountsLogic.TAG, "Nothing to do to primary user mapping on deregister");
      return new ArrayList();
    }
    
    public List<MultipleAccountsLogic.NotificationInfo> removeAndNotify(String paramString)
    {
      MAPLog.e(MultipleAccountsLogic.TAG, "Primary user mapping cannot be removed");
      return new ArrayList();
    }
    
    public List<MultipleAccountsLogic.NotificationInfo> setAndNotify(String paramString)
    {
      MAPLog.e(MultipleAccountsLogic.TAG, "Primary user mapping cannot be changed");
      return new ArrayList();
    }
  }
  
  private static abstract class MetadataMappingClass
    implements MultipleAccountsLogic.AccountMappingLogic
  {
    private final AmazonAccountManager mAmznAcctMan;
    private final Context mContext;
    private final BackwardsCompatiableDataStorage mDataStorage;
    private final MultipleAccountManager.AccountMappingType mMapping;
    
    public MetadataMappingClass(Context paramContext, MultipleAccountManager.AccountMappingType paramAccountMappingType)
    {
      this.mContext = ServiceWrappingContext.create(paramContext);
      this.mAmznAcctMan = ((AmazonAccountManager)this.mContext.getSystemService("dcp_amazon_account_man"));
      this.mDataStorage = new BackwardsCompatiableDataStorage(this.mContext);
      this.mMapping = paramAccountMappingType;
    }
    
    private boolean associateMappingToAccount(String paramString)
    {
      String str1 = this.mMapping.getAccountMappingType();
      String str2 = this.mMapping.getAccountMappingValue();
      Set localSet = getMetadataForAccount(this.mDataStorage, paramString, str1);
      MAPLog.d(MultipleAccountsLogic.TAG, "Current values for type %s before add are %s", new String[] { str1, localSet.toString() });
      if (localSet.contains(str2))
      {
        MAPLog.w(MultipleAccountsLogic.TAG, "Cannot create mapping of type with value %s to account", new String[] { str1, str2 });
        return false;
      }
      localSet.add(str2);
      MAPLog.d(MultipleAccountsLogic.TAG, "Current values for %s after add are %s", new String[] { str1, localSet.toString() });
      this.mDataStorage.setUserData(paramString, str1, seralizeValues(localSet));
      return true;
    }
    
    public static Set<String> getMetadataForAccount(DataStorage paramDataStorage, String paramString1, String paramString2)
    {
      paramDataStorage = paramDataStorage.getUserData(paramString1, paramString2);
      paramString1 = new HashSet();
      if (paramDataStorage == null) {
        return paramString1;
      }
      paramString1.addAll(Arrays.asList(paramDataStorage.split(",")));
      return paramString1;
    }
    
    private boolean removeMappingFromAccount(String paramString)
    {
      String str1 = this.mMapping.getAccountMappingType();
      String str2 = this.mMapping.getAccountMappingValue();
      Set localSet = getMetadataForAccount(this.mDataStorage, paramString, str1);
      MAPLog.d(MultipleAccountsLogic.TAG, "Current values of %s before remove are %s", new String[] { str1, localSet.toString() });
      if (!localSet.contains(str2))
      {
        MAPLog.w(MultipleAccountsLogic.TAG, "Cannot remove %s for type %s from account", new String[] { str2, str1 });
        return false;
      }
      localSet.remove(str2);
      MAPLog.d(MultipleAccountsLogic.TAG, "Current values of %s after remove are %s", new String[] { str1, localSet.toString() });
      this.mDataStorage.setUserData(paramString, str1, seralizeValues(localSet));
      return true;
    }
    
    private String seralizeValues(Set<String> paramSet)
    {
      if (paramSet == null) {
        return null;
      }
      return TextUtils.join(",", paramSet);
    }
    
    private boolean setMappingToUseAccount(String paramString)
    {
      Object localObject = this.mAmznAcctMan.getAmazonAccounts();
      boolean bool = false;
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Iterator)localObject).next();
        if (str.equals(paramString)) {
          bool |= associateMappingToAccount(str);
        } else {
          removeMappingFromAccount(str);
        }
      }
      return bool;
    }
    
    public boolean isMappingCurrentlySetForAccount(String paramString)
    {
      return getMetadataForAccount(this.mDataStorage, paramString, this.mMapping.getAccountMappingType()).contains(this.mMapping.getAccountMappingValue());
    }
    
    public abstract boolean isSupportedOnThisPlatform();
    
    protected abstract List<MultipleAccountsLogic.NotificationInfo> notifyOfChange();
    
    public List<MultipleAccountsLogic.NotificationInfo> onDeregister(String paramString)
    {
      return removeAndNotify(paramString);
    }
    
    public List<MultipleAccountsLogic.NotificationInfo> removeAndNotify(String paramString)
    {
      if (removeMappingFromAccount(paramString))
      {
        MAPLog.i(MultipleAccountsLogic.TAG, "Notifying of user change of type %s removed. Account for profile %s changed.", new String[] { this.mMapping.getAccountMappingType(), this.mMapping.getAccountMappingValue() });
        return notifyOfChange();
      }
      MAPLog.i(MultipleAccountsLogic.TAG, "Cannot remove mapping type %s for key %s did not change. Not notifing.", new String[] { this.mMapping.getAccountMappingType(), this.mMapping.getAccountMappingValue() });
      return new ArrayList();
    }
    
    public List<MultipleAccountsLogic.NotificationInfo> setAndNotify(String paramString)
    {
      if (setMappingToUseAccount(paramString))
      {
        MAPLog.i(MultipleAccountsLogic.TAG, "Notifying of user change of type %s set. Account for profile %s changed.", new String[] { this.mMapping.getAccountMappingType(), this.mMapping.getAccountMappingValue() });
        return notifyOfChange();
      }
      MAPLog.i(MultipleAccountsLogic.TAG, "Setting mapping type %s for key %s did not change. Not notifing.", new String[] { this.mMapping.getAccountMappingType(), this.mMapping.getAccountMappingValue() });
      return new ArrayList();
    }
  }
  
  private static class NotificationInfo
  {
    public final String action;
    public final Bundle extras;
    public final String packageName;
    
    public NotificationInfo(String paramString)
    {
      this(paramString, null, null);
    }
    
    public NotificationInfo(String paramString1, String paramString2, Bundle paramBundle)
    {
      this.action = paramString1;
      this.packageName = paramString2;
      this.extras = paramBundle;
    }
  }
  
  private static class PackageMappingLogic
    extends MultipleAccountsLogic.MetadataMappingClass
  {
    private final Context mContext;
    private final MultipleAccountManager.AccountMappingType mMapping;
    
    public PackageMappingLogic(Context paramContext, MultipleAccountManager.AccountMappingType paramAccountMappingType)
    {
      super(paramAccountMappingType);
      this.mContext = paramContext;
      this.mMapping = paramAccountMappingType;
    }
    
    public static List<PackageMappingLogic> constructPackageMappingsForAccount(Context paramContext, String paramString)
    {
      paramContext = ServiceWrappingContext.create(paramContext);
      Object localObject = getMetadataForAccount(new BackwardsCompatiableDataStorage(paramContext), paramString, "com.amazon.dcp.sso.property.account.extratokens.account_packages");
      paramString = new ArrayList();
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramString.add(new PackageMappingLogic(paramContext, new MultipleAccountManager.PackageMappingType((String)((Iterator)localObject).next())));
      }
      return paramString;
    }
    
    public boolean isSupportedOnThisPlatform()
    {
      return MultipleAccountManager.PackageMappingType.isSupportedOnThisPlatform(this.mContext);
    }
    
    public List<MultipleAccountsLogic.NotificationInfo> notifyOfChange()
    {
      return Arrays.asList(new MultipleAccountsLogic.NotificationInfo[] { new MultipleAccountsLogic.NotificationInfo("com.amazon.identity.action.ACCOUNT_FOR_PACKAGE_CHANGED", this.mMapping.getAccountMappingValue(), null) });
    }
  }
  
  private static class ProfilePrimaryMappingLogic
    extends MultipleAccountsLogic.MetadataMappingClass
  {
    private final Context mContext;
    private final MultipleAccountManager.AccountMappingType mMapping;
    
    public ProfilePrimaryMappingLogic(Context paramContext, MultipleAccountManager.AccountMappingType paramAccountMappingType)
    {
      super(paramAccountMappingType);
      this.mContext = paramContext;
      this.mMapping = paramAccountMappingType;
    }
    
    public static List<ProfilePrimaryMappingLogic> constructProfileMappingForAccount(Context paramContext, String paramString)
    {
      paramContext = ServiceWrappingContext.create(paramContext);
      Object localObject = getMetadataForAccount(new BackwardsCompatiableDataStorage(paramContext), paramString, "com.amazon.dcp.sso.property.account.extratokens.account_profiles");
      paramString = new ArrayList();
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Iterator)localObject).next();
        Integer localInteger = StringConversionHelpers.toInteger(str);
        if (localInteger == null) {
          MAPLog.e(MultipleAccountsLogic.TAG, "%s is not a valid profile id", new String[] { str });
        } else {
          paramString.add(new ProfilePrimaryMappingLogic(paramContext, MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(localInteger.intValue())));
        }
      }
      return paramString;
    }
    
    public boolean isSupportedOnThisPlatform()
    {
      return MultipleAccountsLogic.isProfilePrimaryPlatform(this.mContext);
    }
    
    public List<MultipleAccountsLogic.NotificationInfo> notifyOfChange()
    {
      return Arrays.asList(new MultipleAccountsLogic.NotificationInfo[] { new MultipleAccountsLogic.NotificationInfo("com.amazon.identity.action.ACCOUNT_FOR_PACKAGE_CHANGED") });
    }
  }
  
  private static class SessionUserMappingLogic
    implements MultipleAccountsLogic.AccountMappingLogic
  {
    private final AmazonAccountManager mAmznAcctMan;
    private final Context mContext;
    private final MultipleAccountManager.AccountMappingType mMapping;
    private final SessionUserChanger mSessionUserChanger;
    
    public SessionUserMappingLogic(Context paramContext, MultipleAccountManager.AccountMappingType paramAccountMappingType)
    {
      this.mContext = ServiceWrappingContext.create(paramContext);
      this.mAmznAcctMan = ((AmazonAccountManager)this.mContext.getSystemService("dcp_amazon_account_man"));
      this.mSessionUserChanger = new SessionUserChanger(this.mContext);
      this.mMapping = paramAccountMappingType;
    }
    
    public static List<SessionUserMappingLogic> constructSessionUserMappingsForAccount(Context paramContext, String paramString)
    {
      paramContext = ServiceWrappingContext.create(paramContext);
      AmazonAccountManager localAmazonAccountManager = (AmazonAccountManager)paramContext.getSystemService("dcp_amazon_account_man");
      ArrayList localArrayList = new ArrayList();
      if (localAmazonAccountManager.isSessionUserAmazonAccount(paramString)) {
        localArrayList.add(new SessionUserMappingLogic(paramContext, new MultipleAccountManager.SessionUserMappingType()));
      }
      return localArrayList;
    }
    
    private List<MultipleAccountsLogic.NotificationInfo> createChangedNotification(boolean paramBoolean)
    {
      if (paramBoolean) {
        return Arrays.asList(new MultipleAccountsLogic.NotificationInfo[] { new MultipleAccountsLogic.NotificationInfo("com.amazon.identity.action.ACCOUNT_FOR_PACKAGE_CHANGED") });
      }
      return new ArrayList();
    }
    
    public boolean isMappingCurrentlySetForAccount(String paramString)
    {
      return this.mAmznAcctMan.isSessionUserAmazonAccount(paramString);
    }
    
    public boolean isSupportedOnThisPlatform()
    {
      return MultipleAccountManager.SessionUserMappingType.isSupportedOnThisPlatform(this.mContext);
    }
    
    public List<MultipleAccountsLogic.NotificationInfo> onDeregister(String paramString)
    {
      if (!this.mAmznAcctMan.isSessionUserAmazonAccount(paramString))
      {
        MAPLog.w(MultipleAccountsLogic.TAG, "Account is not a session user, so cannot remove");
        return new ArrayList();
      }
      MAPLog.d(MultipleAccountsLogic.TAG, "Deregister the session user means changing to the primary session user if it is not deregistering");
      this.mSessionUserChanger.onSessionUserDeregistered(paramString);
      if (!this.mAmznAcctMan.isSessionUserAmazonAccount(paramString)) {}
      for (boolean bool = true;; bool = false) {
        return createChangedNotification(bool);
      }
    }
    
    public List<MultipleAccountsLogic.NotificationInfo> removeAndNotify(String paramString)
    {
      if (!this.mAmznAcctMan.isSessionUserAmazonAccount(paramString))
      {
        MAPLog.w(MultipleAccountsLogic.TAG, "Account is not a session user, so cannot remove");
        return new ArrayList();
      }
      MAPLog.d(MultipleAccountsLogic.TAG, "Removing the session user means changing to the primary session user");
      return setAndNotify(this.mAmznAcctMan.getDevicePrimaryAmazonAccount());
    }
    
    public List<MultipleAccountsLogic.NotificationInfo> setAndNotify(String paramString)
    {
      if (this.mAmznAcctMan.isSessionUserAmazonAccount(paramString))
      {
        MAPLog.w(MultipleAccountsLogic.TAG, "Account is already a session user");
        return new ArrayList();
      }
      this.mSessionUserChanger.changeSessionUsers(ArrayUtil.asSet(new String[] { paramString }));
      return createChangedNotification(this.mAmznAcctMan.isSessionUserAmazonAccount(paramString));
    }
  }
}
