package com.Splitwise.SplitwiseMobile.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.Splitwise.SplitwiseMobile.data.AdFeatureStatus;
import com.Splitwise.SplitwiseMobile.data.DataManager;
import com.Splitwise.SplitwiseMobile.data.PaymentOptionStatus;
import com.Splitwise.SplitwiseMobile.data.PaymentOptionStatus.Type;
import com.Splitwise.SplitwiseMobile.data.Person;
import com.Splitwise.SplitwiseMobile.data.Prefs_;
import com.Splitwise.SplitwiseMobile.data.StandardPaymentData;
import com.Splitwise.SplitwiseMobile.di.Injector;
import com.Splitwise.SplitwiseMobile.di.component.ApplicationComponent;
import com.Splitwise.SplitwiseMobile.web.Utils;
import com.crashlytics.android.Crashlytics;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import org.androidannotations.api.sharedpreferences.StringPrefField;

public class FeatureAvailability
{
  public static final String ADMIN = "admin";
  public static final String BILLERS = "billers";
  public static final String CHARTS = "charts";
  public static final String CURRENCY_CONVERSION = "currency_conversion";
  public static final String DEFAULT_SPLITS = "default_splits";
  public static final String EXPENSE_DETAILS_CHARTS = "expense_details_chart";
  public static final String GOOGLE_WALLET = "google_wallet";
  public static final String PAYPAL = "paypal";
  public static final String PAYTM = "paytm";
  @VisibleForTesting
  static final String PAYTM_6_4_1_OR_NEWER = "paytm_if_paytm_6.4.1_or_higher";
  private static final int PAYTM_6_4_1_VERSION_CODE = 137;
  @VisibleForTesting
  static final String PAYTM_6_6_1_OR_NEWER = "paytm_if_paytm_6.6.1_or_higher";
  private static final int PAYTM_6_6_1_VERSION_CODE = 100153;
  public static final String POST_ADD_BILL_AD = "post_add_bill_ad";
  public static final String PRO_SETTINGS_AD = "pro_settings_ad";
  public static final String PRO_SUBSCRIPTION = "pro_settings";
  public static final String RECEIPT_SCANNING = "receipt_scanning";
  public static final String RECEIPT_SCANNING_BADGED_ICON = "receipt_scanning_badged_icon";
  public static final String RECENT_ACTIVITY_AD = "recent_activity_ad";
  public static final String SEARCH = "search";
  public static final String VENMO = "venmo";
  private Prefs_ cache;
  private FeatureSupportProvider supportProvider;
  
  @Inject
  FeatureAvailability(Prefs_ paramPrefs_, FeatureSupportProvider paramFeatureSupportProvider)
  {
    this.cache = paramPrefs_;
    this.supportProvider = paramFeatureSupportProvider;
  }
  
  private void loadFeatureData(String paramString, FeatureStatus paramFeatureStatus)
  {
    String str = paramString;
    if (paramString.equals("paytm")) {
      if (this.supportProvider.systemSupportsFeature("paytm_if_paytm_6.6.1_or_higher"))
      {
        str = "paytm_if_paytm_6.6.1_or_higher";
      }
      else
      {
        str = paramString;
        if (this.supportProvider.systemSupportsFeature("paytm_if_paytm_6.4.1_or_higher")) {
          str = "paytm_if_paytm_6.4.1_or_higher";
        }
      }
    }
    paramFeatureStatus.setVisible(false);
    paramFeatureStatus.setEnabled(false);
    paramString = null;
    Object localObject1 = paramString;
    Object localObject2;
    if (this.cache.serializedMetadata().exists())
    {
      localObject2 = Utils.hashifyJSONString((String)this.cache.serializedMetadata().get());
      localObject1 = paramString;
      if (localObject2 != null)
      {
        localObject1 = paramString;
        if (((HashMap)localObject2).get("features") != null) {
          localObject1 = (HashMap)((HashMap)((HashMap)localObject2).get("features")).get(str);
        }
      }
    }
    if (localObject1 != null)
    {
      Boolean localBoolean = (Boolean)((HashMap)localObject1).get("enabled");
      localObject2 = (Boolean)((HashMap)localObject1).get("visible");
      paramString = (String)localObject2;
      if (localObject2 == null) {
        paramString = Boolean.valueOf(false);
      }
      localObject2 = localBoolean;
      if (localBoolean == null) {
        localObject2 = paramString;
      }
      boolean bool = paramFeatureStatus.parseBehaviorData((HashMap)((HashMap)localObject1).get("behavior"));
      localObject1 = paramString;
      if (!((Boolean)localObject2).booleanValue())
      {
        localObject1 = paramString;
        if (paramString.booleanValue()) {
          localObject1 = Boolean.valueOf(bool);
        }
      }
      paramFeatureStatus.setEnabled(((Boolean)localObject2).booleanValue());
      paramFeatureStatus.setVisible(((Boolean)localObject1).booleanValue());
    }
    if (!this.supportProvider.systemSupportsFeature(str)) {
      paramFeatureStatus.setVisible(false);
    }
  }
  
  public AdFeatureStatus getAdFeature(String paramString)
  {
    AdFeatureStatus localAdFeatureStatus = new AdFeatureStatus();
    loadFeatureData(paramString, localAdFeatureStatus);
    return localAdFeatureStatus;
  }
  
  /* Error */
  public PaymentOptionStatus getPaymentOption(StandardPaymentData paramStandardPaymentData, PaymentOptionStatus.Type paramType)
  {
    // Byte code:
    //   0: new 182	com/Splitwise/SplitwiseMobile/data/PaymentOptionStatus
    //   3: dup
    //   4: aload_2
    //   5: invokespecial 185	com/Splitwise/SplitwiseMobile/data/PaymentOptionStatus:<init>	(Lcom/Splitwise/SplitwiseMobile/data/PaymentOptionStatus$Type;)V
    //   8: astore 9
    //   10: aload_0
    //   11: aload_2
    //   12: invokevirtual 191	com/Splitwise/SplitwiseMobile/data/PaymentOptionStatus$Type:key	()Ljava/lang/String;
    //   15: aload 9
    //   17: invokespecial 178	com/Splitwise/SplitwiseMobile/utils/FeatureAvailability:loadFeatureData	(Ljava/lang/String;Lcom/Splitwise/SplitwiseMobile/utils/FeatureAvailability$FeatureStatus;)V
    //   20: aload_1
    //   21: invokevirtual 196	com/Splitwise/SplitwiseMobile/data/StandardPaymentData:isSenderIsCurrentUser	()Z
    //   24: istore 5
    //   26: aload_1
    //   27: invokevirtual 199	com/Splitwise/SplitwiseMobile/data/StandardPaymentData:getCurrencyCode	()Ljava/lang/String;
    //   30: ldc -55
    //   32: invokevirtual 112	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   35: istore 6
    //   37: aload_1
    //   38: invokevirtual 199	com/Splitwise/SplitwiseMobile/data/StandardPaymentData:getCurrencyCode	()Ljava/lang/String;
    //   41: ldc -53
    //   43: invokevirtual 112	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   46: istore 7
    //   48: aload_1
    //   49: invokevirtual 207	com/Splitwise/SplitwiseMobile/data/StandardPaymentData:getRecipient	()Lcom/Splitwise/SplitwiseMobile/data/Person;
    //   52: invokevirtual 212	com/Splitwise/SplitwiseMobile/data/Person:getEmail	()Ljava/lang/String;
    //   55: invokestatic 215	com/Splitwise/SplitwiseMobile/data/Person:isValidEmail	(Ljava/lang/String;)Z
    //   58: istore 8
    //   60: getstatic 219	com/Splitwise/SplitwiseMobile/utils/FeatureAvailability$1:$SwitchMap$com$Splitwise$SplitwiseMobile$data$PaymentOptionStatus$Type	[I
    //   63: aload_2
    //   64: invokevirtual 223	com/Splitwise/SplitwiseMobile/data/PaymentOptionStatus$Type:ordinal	()I
    //   67: iaload
    //   68: istore 4
    //   70: iconst_1
    //   71: istore_3
    //   72: iload 4
    //   74: tableswitch	default:+30->104, 1:+82->156, 2:+69->143, 3:+51->125, 4:+33->107
    //   104: goto +62 -> 166
    //   107: iload 5
    //   109: ifeq +11 -> 120
    //   112: iload 7
    //   114: ifeq +6 -> 120
    //   117: goto +49 -> 166
    //   120: iconst_0
    //   121: istore_3
    //   122: goto +44 -> 166
    //   125: iload 5
    //   127: ifeq -7 -> 120
    //   130: iload 6
    //   132: ifeq -12 -> 120
    //   135: iload 8
    //   137: ifeq -17 -> 120
    //   140: goto +26 -> 166
    //   143: iload 5
    //   145: ifeq -25 -> 120
    //   148: iload 6
    //   150: ifeq -30 -> 120
    //   153: goto +13 -> 166
    //   156: iload 5
    //   158: ifeq -38 -> 120
    //   161: iload 6
    //   163: ifeq -43 -> 120
    //   166: iload_3
    //   167: ifne +9 -> 176
    //   170: aload 9
    //   172: iconst_0
    //   173: invokevirtual 224	com/Splitwise/SplitwiseMobile/data/PaymentOptionStatus:setVisible	(Z)V
    //   176: aload 9
    //   178: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	179	0	this	FeatureAvailability
    //   0	179	1	paramStandardPaymentData	StandardPaymentData
    //   0	179	2	paramType	PaymentOptionStatus.Type
    //   71	96	3	i	int
    //   68	5	4	j	int
    //   24	133	5	bool1	boolean
    //   35	127	6	bool2	boolean
    //   46	67	7	bool3	boolean
    //   58	78	8	bool4	boolean
    //   8	169	9	localPaymentOptionStatus	PaymentOptionStatus
  }
  
  public boolean isSimpleFeatureEnabled(String paramString)
  {
    DefaultFeatureStatus localDefaultFeatureStatus = new DefaultFeatureStatus(null);
    loadFeatureData(paramString, localDefaultFeatureStatus);
    return (localDefaultFeatureStatus.isEnabled()) && (localDefaultFeatureStatus.isVisible());
  }
  
  public boolean shouldShowPaymentOptionScreen(StandardPaymentData paramStandardPaymentData)
  {
    PaymentOptionStatus.Type[] arrayOfType = PaymentOptionStatus.Type.values();
    int j = arrayOfType.length;
    int i = 0;
    while (i < j)
    {
      if (getPaymentOption(paramStandardPaymentData, arrayOfType[i]).isVisible()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private static class DefaultFeatureStatus
    implements FeatureAvailability.FeatureStatus
  {
    private boolean enabled;
    private boolean visible;
    
    private DefaultFeatureStatus() {}
    
    public boolean isEnabled()
    {
      return this.enabled;
    }
    
    public boolean isTrackingEnabled()
    {
      return false;
    }
    
    public boolean isVisible()
    {
      return this.visible;
    }
    
    public boolean parseBehaviorData(@Nullable HashMap<String, Object> paramHashMap)
    {
      return false;
    }
    
    public void setEnabled(boolean paramBoolean)
    {
      this.enabled = paramBoolean;
    }
    
    public void setVisible(boolean paramBoolean)
    {
      this.visible = paramBoolean;
    }
  }
  
  public static class DeviceFeatureSupportProvider
    implements FeatureAvailability.FeatureSupportProvider
  {
    private Context context;
    
    public DeviceFeatureSupportProvider(Context paramContext)
    {
      this.context = paramContext;
    }
    
    private boolean isBillingIncludedInManifest()
    {
      return manifestContainsPermission("com.android.vending.BILLING");
    }
    
    private boolean isGoogleWalletIntegrationAvailable()
    {
      return false;
    }
    
    private boolean isPayPalContractuallyAllowed()
    {
      String str = Injector.get().dataManager().getCurrentUser().getCountryCode();
      return (str != null) && (str.equals("US"));
    }
    
    private boolean isPaytmAppInstalled()
    {
      Intent localIntent = new Intent();
      localIntent.setComponent(new ComponentName("net.one97.paytm", "net.one97.paytm.AJRJarvisSplash"));
      PackageManager localPackageManager = this.context.getPackageManager();
      boolean bool = false;
      if (localPackageManager.queryIntentActivities(localIntent, 0).size() > 0) {
        bool = true;
      }
      return bool;
    }
    
    private boolean isPaytmVersionNewerThan(int paramInt)
    {
      Object localObject = this.context.getPackageManager();
      boolean bool2 = false;
      localObject = ((PackageManager)localObject).getInstalledPackages(0).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (localPackageInfo.packageName.equalsIgnoreCase("net.one97.paytm"))
        {
          boolean bool1 = bool2;
          if (localPackageInfo.versionCode >= paramInt)
          {
            bool1 = bool2;
            if (isPaytmAppInstalled()) {
              bool1 = true;
            }
          }
          return bool1;
        }
      }
      return false;
    }
    
    private boolean isVenmoAppInstalled()
    {
      PackageManager localPackageManager = this.context.getPackageManager();
      Intent localIntent = VenmoLibrary.openVenmoPayment("1213", "Splitwise", "dummy@email.com", "0.0", "splitwise.com", "pay");
      boolean bool = false;
      if (localPackageManager.queryIntentActivities(localIntent, 0).size() > 0) {
        bool = true;
      }
      return bool;
    }
    
    private boolean manifestContainsPermission(String paramString)
    {
      Object localObject = this.context.getPackageManager();
      try
      {
        localObject = ((PackageManager)localObject).getPackageInfo(this.context.getPackageName(), 4096);
        if (localObject != null)
        {
          boolean bool = Arrays.asList(((PackageInfo)localObject).requestedPermissions).contains(paramString);
          return bool;
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        Crashlytics.logException(paramString);
      }
      return false;
    }
    
    public boolean systemSupportsFeature(String paramString)
    {
      switch (paramString.hashCode())
      {
      default: 
        break;
      case 1957612361: 
        if (paramString.equals("paytm_if_paytm_6.4.1_or_higher")) {
          i = 3;
        }
        break;
      case 1370806347: 
        if (paramString.equals("paytm_if_paytm_6.6.1_or_higher")) {
          i = 4;
        }
        break;
      case 540291263: 
        if (paramString.equals("google_wallet")) {
          i = 5;
        }
        break;
      case 112093569: 
        if (paramString.equals("venmo")) {
          i = 0;
        }
        break;
      case 106444065: 
        if (paramString.equals("paytm")) {
          i = 2;
        }
        break;
      case -229642891: 
        if (paramString.equals("pro_settings")) {
          i = 6;
        }
        break;
      case -995205389: 
        if (paramString.equals("paypal")) {
          i = 1;
        }
        break;
      }
      int i = -1;
      switch (i)
      {
      default: 
        return true;
      case 6: 
        return isBillingIncludedInManifest();
      case 5: 
        return isGoogleWalletIntegrationAvailable();
      case 4: 
        return isPaytmVersionNewerThan(100153);
      case 3: 
        return isPaytmVersionNewerThan(137);
      case 2: 
        return isPaytmAppInstalled();
      case 1: 
        return isPayPalContractuallyAllowed();
      }
      return isVenmoAppInstalled();
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface FeatureName {}
  
  public static abstract interface FeatureStatus
  {
    public abstract boolean isEnabled();
    
    public abstract boolean isTrackingEnabled();
    
    public abstract boolean isVisible();
    
    public abstract boolean parseBehaviorData(@Nullable HashMap<String, Object> paramHashMap);
    
    public abstract void setEnabled(boolean paramBoolean);
    
    public abstract void setVisible(boolean paramBoolean);
  }
  
  public static abstract interface FeatureSupportProvider
  {
    public abstract boolean systemSupportsFeature(String paramString);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface SimpleFeatureName {}
}
