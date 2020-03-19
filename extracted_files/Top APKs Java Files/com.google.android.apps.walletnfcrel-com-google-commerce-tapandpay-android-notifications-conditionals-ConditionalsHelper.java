package com.google.commerce.tapandpay.android.notifications.conditionals;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.text.TextUtils;
import com.google.commerce.tapandpay.android.location.LocationSettings;
import com.google.commerce.tapandpay.android.location.SynchronizedLocationClient;
import com.google.commerce.tapandpay.android.location.TapAndPayGeocoder;
import com.google.commerce.tapandpay.android.logging.CLog;
import com.google.commerce.tapandpay.android.sharedpreferences.AccountPreferences;
import com.google.commerce.tapandpay.android.util.permission.PermissionUtil;
import com.google.commerce.tapandpay.android.version.Versions;
import com.google.internal.tapandpay.v1.Common.NfcStatus;
import com.google.wallet.googlepay.frontend.api.navigation.ClientConditionals;
import com.google.wallet.googlepay.frontend.api.navigation.ClientConditionals.Location;
import com.google.wallet.googlepay.frontend.api.navigation.ClientConditionals.LocationStatus;
import com.google.wallet.googlepay.frontend.api.navigation.ClientConditionals.RequiredApp;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

public class ConditionalsHelper
{
  private static final long MAX_LOCATION_AGE_MILLIS = TimeUnit.MINUTES.toMillis(30L);
  private static final long MAX_LOCATION_RESOLUTION_TIME_MILLIS = TimeUnit.SECONDS.toMillis(30L);
  private final AccountPreferences accountPreferences;
  private final Application application;
  private TapAndPayGeocoder geocoder;
  private final LocationSettings locationSettings;
  private final NfcManager nfcManager;
  private final PermissionUtil permissionUtil;
  private final SynchronizedLocationClient synchronizedLocationClient;
  
  @Inject
  ConditionalsHelper(Application paramApplication, SynchronizedLocationClient paramSynchronizedLocationClient, PermissionUtil paramPermissionUtil, AccountPreferences paramAccountPreferences, NfcManager paramNfcManager, LocationSettings paramLocationSettings)
  {
    this.application = paramApplication;
    this.synchronizedLocationClient = paramSynchronizedLocationClient;
    this.permissionUtil = paramPermissionUtil;
    this.geocoder = new TapAndPayGeocoder(new Geocoder(paramApplication, Locale.US));
    this.accountPreferences = paramAccountPreferences;
    this.nfcManager = paramNfcManager;
    this.locationSettings = paramLocationSettings;
  }
  
  public final boolean meetsClientConditionals(ClientConditionals paramClientConditionals)
  {
    if (paramClientConditionals == null) {}
    for (;;)
    {
      return true;
      Object localObject4 = paramClientConditionals.location_;
      Object localObject5;
      Object localObject3;
      long l;
      if (!((List)localObject4).isEmpty())
      {
        if (!this.permissionUtil.isPermissionGranted(new String[] { "android.permission.ACCESS_FINE_LOCATION" }))
        {
          CLog.d("ConditionalsHelper", "Location permission is not granted; do not show bulletin with location requirements.");
          return false;
        }
        Object localObject2 = this.synchronizedLocationClient.getCurrentLocation(MAX_LOCATION_RESOLUTION_TIME_MILLIS, MAX_LOCATION_AGE_MILLIS);
        localObject1 = null;
        if (localObject2 != null) {
          try
          {
            localObject5 = this.geocoder;
            double d1 = ((Location)localObject2).getLatitude();
            double d2 = ((Location)localObject2).getLongitude();
            localObject2 = ((TapAndPayGeocoder)localObject5).geocoder.getFromLocation(d1, d2, 1);
            if ((localObject2 != null) && (!((List)localObject2).isEmpty()))
            {
              localObject2 = (Address)((List)localObject2).get(0);
              localObject1 = localObject2;
            }
          }
          catch (IOException localIOException)
          {
            CLog.e("ConditionalsHelper", "Unable to retrieve user location.", localIOException);
          }
        }
        if (localObject1 != null)
        {
          localObject3 = ((List)localObject4).iterator();
          do
          {
            if (!((Iterator)localObject3).hasNext()) {
              break;
            }
            localObject4 = (ClientConditionals.Location)((Iterator)localObject3).next();
          } while (((!((ClientConditionals.Location)localObject4).countryCode_.isEmpty()) && (!((ClientConditionals.Location)localObject4).countryCode_.equals(((Address)localObject1).getCountryCode()))) || ((!((ClientConditionals.Location)localObject4).state_.isEmpty()) && (!((ClientConditionals.Location)localObject4).state_.equals(((Address)localObject1).getAdminArea()))) || ((!((ClientConditionals.Location)localObject4).city_.isEmpty()) && (!((ClientConditionals.Location)localObject4).city_.equals(((Address)localObject1).getLocality())) && (!((ClientConditionals.Location)localObject4).city_.equals(((Address)localObject1).getSubAdminArea()))));
        }
      }
      else
      {
        l = paramClientConditionals.maxVersionNumber_;
        if ((l == 0L) || (Versions.getVersionCode(this.application) <= l)) {
          break label326;
        }
      }
      label326:
      int i;
      label568:
      do
      {
        return false;
        localObject3 = paramClientConditionals.requiredApps_;
        if (!((List)localObject3).isEmpty())
        {
          localObject1 = this.application.getPackageManager().getInstalledApplications(128);
          localObject3 = ((List)localObject3).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (ClientConditionals.RequiredApp)((Iterator)localObject3).next();
            if ((localObject1 != null) && (!((List)localObject1).isEmpty()))
            {
              if (!((ClientConditionals.RequiredApp)localObject4).packageName_.isEmpty())
              {
                localObject5 = ((List)localObject1).iterator();
                do
                {
                  if (!((Iterator)localObject5).hasNext()) {
                    break;
                  }
                  ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject5).next();
                  if (TextUtils.equals(((ClientConditionals.RequiredApp)localObject4).packageName_, localApplicationInfo.packageName))
                  {
                    if ((((ClientConditionals.RequiredApp)localObject4).minAppVersionNumber_ == 0L) && (((ClientConditionals.RequiredApp)localObject4).maxAppVersionNumber_ == 0L)) {}
                    for (;;)
                    {
                      i = 1;
                      break label568;
                      try
                      {
                        i = this.application.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 0).versionCode;
                        l = ((ClientConditionals.RequiredApp)localObject4).minAppVersionNumber_;
                        if ((l == 0L) || (l <= i))
                        {
                          l = ((ClientConditionals.RequiredApp)localObject4).maxAppVersionNumber_;
                          if ((l == 0L) || (l >= i)) {
                            continue;
                          }
                        }
                      }
                      catch (PackageManager.NameNotFoundException localNameNotFoundException)
                      {
                        i = 0;
                        break label568;
                      }
                    }
                  }
                  i = 0;
                } while (i == 0);
              }
              i = 1;
            }
            else
            {
              i = 0;
            }
            if (i == 0) {
              return false;
            }
          }
        }
        localObject1 = paramClientConditionals.unelectedLadderPromotionId_;
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          break;
        }
      } while (this.accountPreferences.isLadderPromotionIdOptedIn((String)localObject1));
      if (this.accountPreferences.isLadderPromotionIdOptedOut((String)localObject1)) {
        return false;
      }
      Object localObject1 = Common.NfcStatus.forNumber(paramClientConditionals.nfcStatus_);
      if (localObject1 == null) {
        localObject1 = Common.NfcStatus.UNRECOGNIZED;
      }
      if (localObject1 != Common.NfcStatus.UNSPECIFIED)
      {
        localObject3 = this.nfcManager.getDefaultAdapter();
        if ((localObject3 != null) || (localObject1 != Common.NfcStatus.NFC_UNAVAILABLE))
        {
          if (((NfcAdapter)localObject3).isEnabled()) {
            localObject3 = Common.NfcStatus.NFC_ON;
          } else {
            localObject3 = Common.NfcStatus.NFC_OFF;
          }
          if (localObject1 != localObject3) {
            return false;
          }
        }
      }
      paramClientConditionals = ClientConditionals.LocationStatus.forNumber(paramClientConditionals.locationStatus_);
      if (paramClientConditionals == null) {
        paramClientConditionals = ClientConditionals.LocationStatus.UNRECOGNIZED;
      }
      if (paramClientConditionals != ClientConditionals.LocationStatus.LOCATION_UNSPECIFIED)
      {
        i = paramClientConditionals.ordinal();
        boolean bool;
        if (i != 1)
        {
          if (i != 2)
          {
            if (i != 3)
            {
              paramClientConditionals = String.valueOf(paramClientConditionals);
              localObject1 = new StringBuilder(String.valueOf(paramClientConditionals).length() + 28);
              ((StringBuilder)localObject1).append("Unexpected location status: ");
              ((StringBuilder)localObject1).append(paramClientConditionals);
              CLog.e("ConditionalsHelper", ((StringBuilder)localObject1).toString());
              return true;
            }
            bool = this.locationSettings.isSystemPreferencesNetworkLocationEnabled(this.application);
          }
          else
          {
            if ((!this.locationSettings.isSystemPreferencesLocationEnabled()) || (this.locationSettings.isSystemPreferencesNetworkLocationEnabled(this.application))) {
              break label875;
            }
          }
        }
        else {
          bool = this.locationSettings.isSystemPreferencesLocationEnabled() ^ true;
        }
        if (!bool) {
          label875:
          return false;
        }
      }
    }
  }
}
