package com.microsoft.omadm.platforms.afw.policy;

import android.annotation.TargetApi;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.UserManager;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.microsoft.intune.common.enrollment.datacomponent.implementation.EnrollmentSettings;
import com.microsoft.intune.omadm.afw.datacomponent.implementation.AfwSettingsRepository;
import com.microsoft.intune.omadm.afw.datacomponent.implementation.AfwSettingsRepository.Companion;
import com.microsoft.intune.omadm.afw.domain.IAfwSettingsRepository;
import com.microsoft.intune.omadm.safetynet.domain.SafetyNetSettingsManager;
import com.microsoft.omadm.OMADMSettings;
import com.microsoft.omadm.OMADMStatusCode;
import com.microsoft.omadm.exception.OMADMException;
import com.microsoft.omadm.exception.OMADMStatusException;
import com.microsoft.omadm.platforms.afw.AfwResetPasswordTokenStatus;
import com.microsoft.omadm.platforms.afw.wptokenrenew.IWPTokenRenewalStateMachine;
import com.microsoft.omadm.platforms.android.NativeSettings;
import com.microsoft.omadm.platforms.android.policy.NativePolicyManager;
import com.microsoft.omadm.utils.PackageUtils;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

public class AfwPolicyManager
  extends NativePolicyManager
{
  private static final String[] DANGEROUS_PERMISSION_LIST = { "android.permission.READ_PHONE_STATE", "android.permission.GET_ACCOUNTS" };
  public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
  private static final Logger LOGGER = Logger.getLogger(AfwPolicyManager.class.getName());
  private static final Charset UTF8 = Charset.forName("UTF-8");
  private final IAfwSettingsRepository afwSettingsRepository;
  private final UserManager userManager;
  private final IWPTokenRenewalStateMachine wpTokenRenewalStateMachine;
  
  public AfwPolicyManager(Context paramContext, OMADMSettings paramOMADMSettings, EnrollmentSettings paramEnrollmentSettings, NativeSettings paramNativeSettings, SafetyNetSettingsManager paramSafetyNetSettingsManager, IAfwSettingsRepository paramIAfwSettingsRepository, IWPTokenRenewalStateMachine paramIWPTokenRenewalStateMachine)
  {
    super(paramContext, paramOMADMSettings, paramEnrollmentSettings, paramNativeSettings, paramSafetyNetSettingsManager);
    this.afwSettingsRepository = paramIAfwSettingsRepository;
    this.userManager = ((UserManager)paramContext.getSystemService("user"));
    this.wpTokenRenewalStateMachine = paramIWPTokenRenewalStateMachine;
  }
  
  @TargetApi(23)
  private static int convertActionStringToInt(String paramString)
    throws OMADMException
  {
    int i = paramString.hashCode();
    if (i != -1895938684)
    {
      if (i != -651933555)
      {
        if ((i == 1502889083) && (paramString.equals("AutoDeny")))
        {
          i = 2;
          break label70;
        }
      }
      else if (paramString.equals("AutoGrant"))
      {
        i = 1;
        break label70;
      }
    }
    else if (paramString.equals("Prompt"))
    {
      i = 0;
      break label70;
    }
    i = -1;
    switch (i)
    {
    default: 
      throw new OMADMException("Unknown action string.");
    case 2: 
      return 2;
    case 1: 
      label70:
      return 1;
    }
    return 0;
  }
  
  private boolean getAlwaysOnVpnLockdownModeEnabledExpectedValue()
  {
    return this.afwSettingsRepository.getWorkAlwaysOnVpnLockDownModeEnabledExpectedValue();
  }
  
  private String[] getHiddenWorkApps()
  {
    return this.afwSettingsRepository.getAppsHiddenByCompanyPortal().split(";");
  }
  
  private void recordHiddenWorkApp(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(this.afwSettingsRepository.getAppsHiddenByCompanyPortal());
    if (!wasPackageHiddenByCompanyPortal(paramString))
    {
      if (StringUtils.isNotEmpty(this.afwSettingsRepository.getAppsHiddenByCompanyPortal())) {
        localStringBuilder.append(";");
      }
      localStringBuilder.append(paramString);
      this.afwSettingsRepository.setAppsHiddenByCompanyPortal(localStringBuilder.toString());
    }
  }
  
  private boolean wasPackageHiddenByCompanyPortal(String paramString)
  {
    return this.afwSettingsRepository.getAppsHiddenByCompanyPortal().toLowerCase(Locale.US).contains(paramString.toLowerCase(Locale.US));
  }
  
  @TargetApi(21)
  public void addCrossProfileIntentFilter(IntentFilter paramIntentFilter, int paramInt)
  {
    this.devicePolicyManager.addCrossProfileIntentFilter(this.componentName, paramIntentFilter, paramInt);
  }
  
  @TargetApi(21)
  public void addUserRestriction(String paramString)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 21) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on non AFW devices.");
    }
    this.devicePolicyManager.addUserRestriction(this.componentName, paramString);
  }
  
  @TargetApi(21)
  public void clearCrossProfileIntentFilters()
  {
    this.devicePolicyManager.clearCrossProfileIntentFilters(this.componentName);
  }
  
  @TargetApi(26)
  public void clearResetPasswordToken()
    throws OMADMException
  {
    try
    {
      this.devicePolicyManager.clearResetPasswordToken(this.componentName);
      this.afwSettingsRepository.setResetPasswordTokenStatus(AfwSettingsRepository.Companion.getDEFAULT_WORK_RESET_PASSWORD_TOKEN_STATUS());
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  @TargetApi(21)
  public void clearUserRestriction(String paramString)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 21) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on non AFW devices.");
    }
    this.devicePolicyManager.clearUserRestriction(this.componentName, paramString);
  }
  
  public void disable()
    throws OMADMException
  {
    if (isProfileOwner())
    {
      wipeDevice(true);
      return;
    }
    throw new OMADMException("Cannot remove managed profile, app is currently not a profile owner.");
  }
  
  public void enableManageApplicationRestrictions(String paramString)
    throws OMADMException
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 26)
      {
        this.devicePolicyManager.setDelegatedScopes(this.componentName, paramString, Arrays.asList(new String[] { "delegation-app-restrictions" }));
        return;
      }
      if (Build.VERSION.SDK_INT >= 24)
      {
        this.devicePolicyManager.setApplicationRestrictionsManagingPackage(this.componentName, paramString);
        return;
      }
      LOGGER.info("Calling enableManageApplicationRestrictions for pre-Android N is not supported; ignoring.");
      return;
    }
    catch (Exception paramString)
    {
      throw new OMADMException(paramString);
    }
  }
  
  @TargetApi(23)
  public boolean getAllowBluetoothContactSharing()
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 23) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to M.");
    }
    return this.devicePolicyManager.getBluetoothContactSharingDisabled(this.componentName) ^ true;
  }
  
  @TargetApi(21)
  public boolean getAllowCrossProfileCallerId()
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 21) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to Lollipop.");
    }
    return this.devicePolicyManager.getCrossProfileCallerIdDisabled(this.componentName) ^ true;
  }
  
  @TargetApi(21)
  public boolean getAllowFingerprintForUnlock()
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 23) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to M.");
    }
    return getKeyguardFeaturesEnabled(32, getDeviceWidePolicyManager());
  }
  
  @TargetApi(21)
  public boolean getAllowScreenShot()
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 21) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to Lollipop.");
    }
    return this.devicePolicyManager.getScreenCaptureDisabled(this.componentName) ^ true;
  }
  
  public boolean getAlwaysOnVpnLockdownModeEnabled()
  {
    return this.afwSettingsRepository.getWorkAlwaysOnVpnLockDownModeEnabledCurrent();
  }
  
  @TargetApi(24)
  public String getAlwaysOnVpnPackage()
    throws OMADMException
  {
    try
    {
      String str = this.devicePolicyManager.getAlwaysOnVpnPackage(this.componentName);
      return str;
    }
    catch (Exception localException)
    {
      LOGGER.severe(localException.getMessage());
      throw new OMADMException(localException);
    }
  }
  
  @TargetApi(24)
  public boolean getCrossProfileContactsSearchDisabled()
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24)
    {
      LOGGER.warning("AFW policy get CrossProfileContactsSearchDisabled called on API before N. Ignoring.");
      return false;
    }
    return this.devicePolicyManager.getCrossProfileContactsSearchDisabled(this.componentName);
  }
  
  @TargetApi(23)
  public int getDefaultAppPermissionPolicy()
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 23) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to M.");
    }
    try
    {
      int i = this.devicePolicyManager.getPermissionPolicy(this.componentName);
      return i;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  protected DevicePolicyManager getDeviceWidePolicyManager()
  {
    if ((Build.VERSION.SDK_INT >= 24) && (isProfileOwner())) {
      return this.devicePolicyManager.getParentProfileInstance(this.componentName);
    }
    return this.devicePolicyManager;
  }
  
  @TargetApi(21)
  public boolean getDisableUnredactedNotifications()
    throws OMADMException
  {
    return getKeyguardFeaturesEnabled(8, this.devicePolicyManager) ^ true;
  }
  
  @TargetApi(23)
  public void grantCompanyPortalPermissions()
  {
    if (Build.VERSION.SDK_INT >= 23)
    {
      String[] arrayOfString = DANGEROUS_PERMISSION_LIST;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = arrayOfString[i];
        if (!this.devicePolicyManager.setPermissionGrantState(this.componentName, this.context.getPackageName(), str, 1))
        {
          Logger localLogger = LOGGER;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Failed to grant Work Profile Company Portal the permission: ");
          localStringBuilder.append(str);
          localLogger.warning(localStringBuilder.toString());
        }
        i += 1;
      }
    }
    LOGGER.info("Calling setPermissionGrantState for Android before M is not possible.");
  }
  
  @TargetApi(21)
  public boolean hasUserRestriction(String paramString)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 21) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on non AFW devices.");
    }
    if (this.userManager == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("User manager is null. We can't get the user restriction information on ");
      localStringBuilder.append(paramString);
      throw new OMADMException(localStringBuilder.toString());
    }
    return this.userManager.hasUserRestriction(paramString);
  }
  
  @TargetApi(21)
  public void hideWorkApps()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.context.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      boolean bool1 = PackageUtils.isCompanyPortalPackage(this.context, localApplicationInfo.packageName);
      boolean bool2 = this.devicePolicyManager.isApplicationHidden(this.componentName, localApplicationInfo.packageName);
      int i;
      if (this.context.getPackageManager().getLaunchIntentForPackage(localApplicationInfo.packageName) != null) {
        i = 1;
      } else {
        i = 0;
      }
      if ((!bool1) && (!bool2) && (i != 0))
      {
        LOGGER.info(MessageFormat.format("Hiding work app: {0}", new Object[] { localApplicationInfo.packageName }));
        try
        {
          this.devicePolicyManager.setApplicationHidden(this.componentName, localApplicationInfo.packageName, true);
          recordHiddenWorkApp(localApplicationInfo.packageName);
        }
        catch (Exception localException)
        {
          LOGGER.severe(MessageFormat.format("Exception listing or hiding work app: {0} : {1}", new Object[] { localApplicationInfo.packageName, localException.getMessage() }));
          localStringBuilder.append(localApplicationInfo.packageName);
          localStringBuilder.append(";");
        }
      }
    }
    if (StringUtils.isNotEmpty(localStringBuilder.toString())) {
      LOGGER.log(Level.WARNING, MessageFormat.format("Failed to hide the following packages: {0}", new Object[] { localStringBuilder.toString() }));
    }
  }
  
  @TargetApi(21)
  public boolean isAppVerifyEnabled()
  {
    if (Build.VERSION.SDK_INT < 21)
    {
      LOGGER.warning("AFW policy isAppVerifyEnabled called on API before LOLLIPOP. Ignoring.");
      return false;
    }
    return this.userManager.hasUserRestriction("ensure_verify_apps");
  }
  
  public boolean isDeviceAdmin()
  {
    return false;
  }
  
  public boolean isEnabled()
  {
    return isProfileOwner();
  }
  
  public boolean isWorkPasswordCompliant()
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      long l1 = this.devicePolicyManager.getPasswordExpiration(this.componentName);
      if (this.devicePolicyManager.isActivePasswordSufficient()) {
        if (l1 != 0L)
        {
          long l2 = System.currentTimeMillis();
          if (l1 - l2 <= 0L) {}
        }
        else
        {
          return true;
        }
      }
      return false;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public boolean isWorkPasswordRequired()
  {
    int i = Build.VERSION.SDK_INT;
    boolean bool = false;
    if (i < 24) {
      return false;
    }
    if (this.afwSettingsRepository.getWorkPasswordEnabled() == 0) {
      bool = true;
    }
    return bool;
  }
  
  public boolean isWorkProfileTokenRequiredForRenewal()
  {
    if (this.wpTokenRenewalStateMachine != null) {
      return this.wpTokenRenewalStateMachine.isWorkProfileTokenRequiredForRenewal();
    }
    LOGGER.severe("Failed to get work profile token renewal status due to the null injected state machine.");
    return false;
  }
  
  @TargetApi(26)
  public void onPasswordChanged()
  {
    AfwPasswordPolicy.setRequireNewWorkProfilePassword(this.afwSettingsRepository, false);
  }
  
  @TargetApi(24)
  public boolean removeUserCertificate(String paramString)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMException("Remove user certificates not supported on devices prior to N.");
    }
    return this.devicePolicyManager.removeKeyPair(this.componentName, paramString);
  }
  
  public void renewWorkProfileToken(String paramString)
  {
    if (this.wpTokenRenewalStateMachine != null)
    {
      LOGGER.info("Start processing work profile token renewal.");
      this.wpTokenRenewalStateMachine.renewWorkProfileToken(paramString);
      return;
    }
    LOGGER.severe("Failed to renew work profile token due to the null injected state machine.");
  }
  
  @TargetApi(26)
  public void resetPassword(String paramString)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 26)
    {
      LOGGER.warning("AFW policy resetPassword called on API before O. Ignoring.");
      return;
    }
    try
    {
      paramString = (AfwPolicyManager.AfwResetPasswordPayload)new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create().fromJson(paramString, AfwPolicyManager.AfwResetPasswordPayload.class);
      byte[] arrayOfByte = paramString.token.getBytes(UTF8);
      if (!this.devicePolicyManager.resetPasswordWithToken(this.componentName, paramString.password, arrayOfByte, 1)) {
        throw new OMADMException("Failed to set new work profile password.");
      }
      this.devicePolicyManager.lockNow();
      AfwPasswordPolicy.setRequireNewWorkProfilePassword(this.afwSettingsRepository, true);
      return;
    }
    catch (Exception paramString)
    {
      throw new OMADMException(paramString);
    }
    catch (OMADMException paramString)
    {
      throw paramString;
    }
  }
  
  @TargetApi(26)
  public boolean resetPasswordTokenNeedsActivation()
    throws OMADMException
  {
    int i = Build.VERSION.SDK_INT;
    boolean bool = false;
    if (i < 26)
    {
      LOGGER.warning("AFW policy resetPasswordTokenNeedsActivation called on API before O. Ignoring.");
      return false;
    }
    try
    {
      AfwResetPasswordTokenStatus localAfwResetPasswordTokenStatus1 = AfwResetPasswordTokenStatus.Set;
      AfwResetPasswordTokenStatus localAfwResetPasswordTokenStatus2 = this.afwSettingsRepository.getResetPasswordTokenStatus();
      if (localAfwResetPasswordTokenStatus1 == localAfwResetPasswordTokenStatus2) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  @TargetApi(21)
  public void setAccountManagementDisabled(String paramString, boolean paramBoolean)
  {
    this.devicePolicyManager.setAccountManagementDisabled(this.componentName, paramString, paramBoolean);
  }
  
  @TargetApi(23)
  public void setAllowBluetoothContactSharing(boolean paramBoolean)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 23) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to M.");
    }
    this.devicePolicyManager.setBluetoothContactSharingDisabled(this.componentName, paramBoolean ^ true);
  }
  
  @TargetApi(21)
  public void setAllowCrossProfileCallerId(boolean paramBoolean)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 21) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to Lollipop.");
    }
    this.devicePolicyManager.setCrossProfileCallerIdDisabled(this.componentName, paramBoolean ^ true);
  }
  
  @TargetApi(21)
  public void setAllowFingerprintForUnlock(boolean paramBoolean)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 23) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to M.");
    }
    setKeyguardFeaturesEnabled(32, paramBoolean, getDeviceWidePolicyManager());
  }
  
  @TargetApi(21)
  public void setAllowScreenShot(boolean paramBoolean)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 21) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to Lollipop.");
    }
    this.devicePolicyManager.setScreenCaptureDisabled(this.componentName, paramBoolean ^ true);
  }
  
  @TargetApi(21)
  public void setAllowWorkFingerprintForUnlock(boolean paramBoolean)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    setKeyguardFeaturesEnabled(32, paramBoolean, this.devicePolicyManager);
  }
  
  @TargetApi(24)
  public void setAlwaysOnVpnLockdownModeEnabled(boolean paramBoolean)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    if (this.afwSettingsRepository.getWorkAlwaysOnVpnLockDownModeEnabledExpectedValue()) {
      LOGGER.info(MessageFormat.format("Current value of AFW settings lockdown mode enabled next set to: {0}, setting it to: {1}.", new Object[] { Boolean.valueOf(this.afwSettingsRepository.getWorkAlwaysOnVpnLockDownModeEnabledExpectedValue()), Boolean.valueOf(paramBoolean) }));
    }
    this.afwSettingsRepository.setWorkAlwaysOnVpnLockDownModeEnabledExpectedValue(paramBoolean);
    try
    {
      LOGGER.info(MessageFormat.format("Setting work profile always on vpn lockdown mode enabled to: {0}", new Object[] { Boolean.valueOf(paramBoolean) }));
      String str = getAlwaysOnVpnPackage();
      if (StringUtils.isNotBlank(str))
      {
        this.devicePolicyManager.setAlwaysOnVpnPackage(this.componentName, str, paramBoolean);
        this.afwSettingsRepository.setWorkAlwaysOnVpnLockDownModeEnabledCurrent(paramBoolean);
      }
      return;
    }
    catch (Exception localException)
    {
      LOGGER.severe(localException.getMessage());
      throw new OMADMException(localException);
    }
  }
  
  /* Error */
  @TargetApi(24)
  public void setAlwaysOnVpnPackage(String paramString)
    throws OMADMException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 191	android/os/Build$VERSION:SDK_INT	I
    //   5: bipush 24
    //   7: if_icmpge +17 -> 24
    //   10: new 193	com/microsoft/omadm/exception/OMADMStatusException
    //   13: dup
    //   14: getstatic 199	com/microsoft/omadm/OMADMStatusCode:STATUS_E_NOT_SUPPORTED	Lcom/microsoft/omadm/OMADMStatusCode;
    //   17: ldc_w 437
    //   20: invokespecial 204	com/microsoft/omadm/exception/OMADMStatusException:<init>	(Lcom/microsoft/omadm/OMADMStatusCode;Ljava/lang/String;)V
    //   23: athrow
    //   24: aload_0
    //   25: getfield 68	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:afwSettingsRepository	Lcom/microsoft/intune/omadm/afw/domain/IAfwSettingsRepository;
    //   28: aload_1
    //   29: invokeinterface 592 2 0
    //   34: aload_0
    //   35: getfield 334	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:context	Landroid/content/Context;
    //   38: aload_1
    //   39: invokestatic 595	com/microsoft/omadm/utils/PackageUtils:isPackageInstalled	(Landroid/content/Context;Ljava/lang/String;)Z
    //   42: istore_2
    //   43: iload_2
    //   44: ifeq +128 -> 172
    //   47: getstatic 42	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:LOGGER	Ljava/util/logging/Logger;
    //   50: ldc_w 597
    //   53: iconst_3
    //   54: anewarray 401	java/lang/Object
    //   57: dup
    //   58: iconst_0
    //   59: aload_0
    //   60: invokevirtual 578	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:getAlwaysOnVpnPackage	()Ljava/lang/String;
    //   63: aastore
    //   64: dup
    //   65: iconst_1
    //   66: aload_1
    //   67: aastore
    //   68: dup
    //   69: iconst_2
    //   70: aload_0
    //   71: invokespecial 599	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:getAlwaysOnVpnLockdownModeEnabledExpectedValue	()Z
    //   74: invokestatic 571	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   77: aastore
    //   78: invokestatic 407	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   81: invokevirtual 271	java/util/logging/Logger:info	(Ljava/lang/String;)V
    //   84: aload_0
    //   85: getfield 175	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:devicePolicyManager	Landroid/app/admin/DevicePolicyManager;
    //   88: aload_0
    //   89: getfield 179	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:componentName	Landroid/content/ComponentName;
    //   92: aload_1
    //   93: aload_0
    //   94: invokespecial 599	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:getAlwaysOnVpnLockdownModeEnabledExpectedValue	()Z
    //   97: invokevirtual 584	android/app/admin/DevicePolicyManager:setAlwaysOnVpnPackage	(Landroid/content/ComponentName;Ljava/lang/String;Z)V
    //   100: aload_0
    //   101: monitorexit
    //   102: return
    //   103: astore_1
    //   104: getstatic 42	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:LOGGER	Ljava/util/logging/Logger;
    //   107: aload_1
    //   108: invokevirtual 308	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   111: invokevirtual 311	java/util/logging/Logger:severe	(Ljava/lang/String;)V
    //   114: new 86	com/microsoft/omadm/exception/OMADMException
    //   117: dup
    //   118: aload_1
    //   119: invokespecial 237	com/microsoft/omadm/exception/OMADMException:<init>	(Ljava/lang/Throwable;)V
    //   122: athrow
    //   123: getstatic 42	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:LOGGER	Ljava/util/logging/Logger;
    //   126: ldc_w 601
    //   129: iconst_2
    //   130: anewarray 401	java/lang/Object
    //   133: dup
    //   134: iconst_0
    //   135: aload_0
    //   136: invokevirtual 578	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:getAlwaysOnVpnPackage	()Ljava/lang/String;
    //   139: aastore
    //   140: dup
    //   141: iconst_1
    //   142: aload_1
    //   143: aastore
    //   144: invokestatic 407	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   147: invokevirtual 317	java/util/logging/Logger:warning	(Ljava/lang/String;)V
    //   150: new 86	com/microsoft/omadm/exception/OMADMException
    //   153: dup
    //   154: ldc_w 603
    //   157: iconst_1
    //   158: anewarray 401	java/lang/Object
    //   161: dup
    //   162: iconst_0
    //   163: aload_1
    //   164: aastore
    //   165: invokestatic 407	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   168: invokespecial 111	com/microsoft/omadm/exception/OMADMException:<init>	(Ljava/lang/String;)V
    //   171: athrow
    //   172: getstatic 42	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:LOGGER	Ljava/util/logging/Logger;
    //   175: ldc_w 605
    //   178: iconst_2
    //   179: anewarray 401	java/lang/Object
    //   182: dup
    //   183: iconst_0
    //   184: aload_0
    //   185: invokevirtual 578	com/microsoft/omadm/platforms/afw/policy/AfwPolicyManager:getAlwaysOnVpnPackage	()Ljava/lang/String;
    //   188: aastore
    //   189: dup
    //   190: iconst_1
    //   191: aload_1
    //   192: aastore
    //   193: invokestatic 407	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   196: invokevirtual 317	java/util/logging/Logger:warning	(Ljava/lang/String;)V
    //   199: new 86	com/microsoft/omadm/exception/OMADMException
    //   202: dup
    //   203: ldc_w 607
    //   206: iconst_1
    //   207: anewarray 401	java/lang/Object
    //   210: dup
    //   211: iconst_0
    //   212: aload_1
    //   213: aastore
    //   214: invokestatic 407	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   217: invokespecial 111	com/microsoft/omadm/exception/OMADMException:<init>	(Ljava/lang/String;)V
    //   220: athrow
    //   221: astore_1
    //   222: aload_0
    //   223: monitorexit
    //   224: aload_1
    //   225: athrow
    //   226: astore_3
    //   227: goto -104 -> 123
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	230	0	this	AfwPolicyManager
    //   0	230	1	paramString	String
    //   42	2	2	bool	boolean
    //   226	1	3	localUnsupportedOperationException	UnsupportedOperationException
    // Exception table:
    //   from	to	target	type
    //   47	100	103	java/lang/Exception
    //   2	24	221	finally
    //   24	43	221	finally
    //   47	100	221	finally
    //   104	123	221	finally
    //   123	172	221	finally
    //   172	221	221	finally
    //   47	100	226	java/lang/UnsupportedOperationException
  }
  
  @TargetApi(24)
  public void setCrossProfileContactsSearchDisabled(boolean paramBoolean)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24)
    {
      LOGGER.warning("AFW policy CrossProfileContactsSearchDisabled called on API before N. Ignoring.");
      return;
    }
    this.devicePolicyManager.setCrossProfileContactsSearchDisabled(this.componentName, paramBoolean);
  }
  
  @TargetApi(23)
  public void setDefaultAppPermissionPolicy(int paramInt)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 23) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to M.");
    }
    try
    {
      this.devicePolicyManager.setPermissionPolicy(this.componentName, paramInt);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  @TargetApi(21)
  public void setDisableUnredactedNotifications(boolean paramBoolean)
    throws OMADMException
  {
    setKeyguardFeaturesEnabled(8, paramBoolean ^ true, this.devicePolicyManager);
  }
  
  @TargetApi(23)
  public void setPermissionGrantState(String paramString1, String paramString2)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 23) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on non AFW devices.");
    }
    try
    {
      paramString2 = (AfwPolicyManager.PermissionActions)new Gson().fromJson(paramString2, AfwPolicyManager.PermissionActions.class);
      if (paramString2 != null)
      {
        paramString2 = paramString2.getPermissionActions().iterator();
        while (paramString2.hasNext())
        {
          AfwPolicyManager.PermissionActionItem localPermissionActionItem = (AfwPolicyManager.PermissionActionItem)paramString2.next();
          this.devicePolicyManager.setPermissionGrantState(this.componentName, paramString1, localPermissionActionItem.getPermission(), convertActionStringToInt(localPermissionActionItem.getAction()));
        }
      }
      return;
    }
    catch (JsonSyntaxException paramString1)
    {
      for (;;) {}
    }
    throw new OMADMException("Failure while parsing the permission action list.");
  }
  
  @TargetApi(21)
  public void setProfileEnabled()
    throws OMADMException
  {
    try
    {
      this.devicePolicyManager.setProfileEnabled(this.componentName);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  @TargetApi(26)
  public void setResetPasswordToken(String paramString)
    throws OMADMException
  {
    try
    {
      if (AfwResetPasswordTokenStatus.Inactive == this.afwSettingsRepository.getResetPasswordTokenStatus())
      {
        paramString = paramString.getBytes(UTF8);
        this.devicePolicyManager.setResetPasswordToken(this.componentName, paramString);
        this.afwSettingsRepository.setResetPasswordTokenStatus(AfwResetPasswordTokenStatus.Set);
        return;
      }
      LOGGER.warning("Work Profile Reset Password Token is already set.");
      return;
    }
    catch (Exception paramString)
    {
      throw new OMADMException(paramString);
    }
  }
  
  @TargetApi(21)
  public void setUserRestriction(String paramString, boolean paramBoolean)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 21) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on non AFW devices.");
    }
    if (paramBoolean)
    {
      addUserRestriction(paramString);
      return;
    }
    clearUserRestriction(paramString);
  }
  
  public void setWorkMaximumFailedPasswordsForWipe(int paramInt)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setMaximumFailedPasswordsForWipe(this.componentName, paramInt);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public void setWorkMaximumTimeToLock(long paramLong)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setMaximumTimeToLock(this.componentName, paramLong);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public void setWorkPasswordExpiration(long paramLong)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setPasswordExpirationTimeout(this.componentName, paramLong);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public void setWorkPasswordHistoryLength(int paramInt)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setPasswordHistoryLength(this.componentName, paramInt);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public void setWorkPasswordMinimumLength(int paramInt)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setPasswordMinimumLength(this.componentName, paramInt);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public void setWorkPasswordMinimumLetter(int paramInt)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setPasswordMinimumLetters(this.componentName, paramInt);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public void setWorkPasswordMinimumLowerCase(int paramInt)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setPasswordMinimumLowerCase(this.componentName, paramInt);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public void setWorkPasswordMinimumNonLetter(int paramInt)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setPasswordMinimumNonLetter(this.componentName, paramInt);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public void setWorkPasswordMinimumNumeric(int paramInt)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setPasswordMinimumNumeric(this.componentName, paramInt);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public void setWorkPasswordMinimumSymbol(int paramInt)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setPasswordMinimumSymbols(this.componentName, paramInt);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public void setWorkPasswordMinimumUpperCase(int paramInt)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setPasswordMinimumUpperCase(this.componentName, paramInt);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  public void setWorkPasswordQuality(int paramInt)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    try
    {
      this.devicePolicyManager.setPasswordQuality(this.componentName, paramInt);
      return;
    }
    catch (Exception localException)
    {
      throw new OMADMException(localException);
    }
  }
  
  @TargetApi(21)
  public void setWorkTrustAgentsEnabled(boolean paramBoolean)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 24) {
      throw new OMADMStatusException(OMADMStatusCode.STATUS_E_NOT_SUPPORTED, "Not supported on AFW devices prior to N.");
    }
    setKeyguardFeaturesEnabled(16, paramBoolean, this.devicePolicyManager);
  }
  
  public boolean shouldStartEncryption()
  {
    return false;
  }
  
  @TargetApi(21)
  public void showHiddenWorkApps()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String[] arrayOfString = getHiddenWorkApps();
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      if (StringUtils.isNotEmpty(str)) {
        if (PackageUtils.isPackageInstalled(this.context, str))
        {
          LOGGER.info(MessageFormat.format("Unhiding work app: {0}", new Object[] { str }));
          try
          {
            this.devicePolicyManager.setApplicationHidden(this.componentName, str, false);
          }
          catch (Exception localException)
          {
            LOGGER.severe(MessageFormat.format("Exception unhiding work app: {0} : {1}", new Object[] { str, localException.getMessage() }));
            if (StringUtils.isNotEmpty(localStringBuilder)) {
              localStringBuilder.append(";");
            }
            localStringBuilder.append(str);
          }
        }
        else
        {
          LOGGER.info(MessageFormat.format("Previously hidden app has been uninstalled: {0}", new Object[] { str }));
        }
      }
      i += 1;
    }
    this.afwSettingsRepository.setAppsHiddenByCompanyPortal(localStringBuilder.toString());
    if (StringUtils.isNotEmpty(localStringBuilder.toString())) {
      LOGGER.log(Level.WARNING, MessageFormat.format("Failed to unhide the following work apps: {0}", new Object[] { localStringBuilder.toString() }));
    }
  }
  
  @TargetApi(21)
  public void toggleAppVerify(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 21)
    {
      LOGGER.warning("AFW policy toggleAppVerify called on API before LOLLIPOP. Ignoring.");
      return;
    }
    if (paramBoolean)
    {
      this.devicePolicyManager.addUserRestriction(this.componentName, "ensure_verify_apps");
      return;
    }
    this.devicePolicyManager.clearUserRestriction(this.componentName, "ensure_verify_apps");
  }
  
  @TargetApi(21)
  public void uninstallCaCert(byte[] paramArrayOfByte)
    throws OMADMException
  {
    if (Build.VERSION.SDK_INT < 21) {
      throw new OMADMException("Remove CA certificates not supported on devices prior to L.");
    }
    this.devicePolicyManager.uninstallCaCert(this.componentName, paramArrayOfByte);
  }
}
