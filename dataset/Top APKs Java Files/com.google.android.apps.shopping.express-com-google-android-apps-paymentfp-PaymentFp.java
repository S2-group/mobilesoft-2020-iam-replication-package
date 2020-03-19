package com.google.android.apps.paymentfp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.google.android.apps.paymentfp.encrypt.AndroidKeyczarReader;
import com.google.android.apps.paymentfp.encrypt.KeyczarEncryptionService;
import com.google.protos.paymentfraud.mobile.Mi12Mobile.DeviceFingerprinting.Parsed;
import com.google.protos.paymentfraud.mobile.Mi12Mobile.DeviceFingerprinting.Parsed.Builder;
import com.google.protos.paymentfraud.mobile.Mi12Mobile.DeviceFingerprinting.Parsed.Properties.Builder;
import com.google.protos.paymentfraud.mobile.Mi12Mobile.DeviceFingerprinting.Parsed.Properties.OperatingSystem;
import com.google.protos.paymentfraud.mobile.Mi12Mobile.DeviceFingerprinting.Parsed.State.Builder;
import com.google.protos.paymentfraud.mobile.Mi12Mobile.DeviceFingerprinting.Parsed.State.Location;
import com.google.protos.paymentfraud.mobile.Mi12Mobile.DeviceFingerprinting.Parsed.State.Location.Builder;
import com.google.protos.paymentfraud.mobile.Mi12Mobile.DeviceFingerprinting.Parsed.State.PackageInfo;
import com.google.protos.paymentfraud.mobile.Mi12Mobile.DeviceFingerprinting.Parsed.State.PackageInfo.Builder;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.keyczar.exceptions.KeyczarException;

public class PaymentFp
{
  public static Mi12Mobile.DeviceFingerprinting.Parsed a(Context paramContext)
  {
    boolean bool2 = true;
    Mi12Mobile.DeviceFingerprinting.Parsed localParsed = Mi12Mobile.DeviceFingerprinting.Parsed.j();
    Mi12Mobile.DeviceFingerprinting.Parsed.Builder localBuilder = Mi12Mobile.DeviceFingerprinting.Parsed.s();
    Mi12Mobile.DeviceFingerprinting.Parsed.State.Builder localBuilder1 = localBuilder.u();
    Mi12Mobile.DeviceFingerprinting.Parsed.Properties.Builder localBuilder2 = localBuilder.t();
    localBuilder2.a(Mi12Mobile.DeviceFingerprinting.Parsed.Properties.OperatingSystem.b);
    Object localObject1 = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      Mi12Mobile.DeviceFingerprinting.Parsed.State.PackageInfo.Builder localBuilder3 = Mi12Mobile.DeviceFingerprinting.Parsed.State.PackageInfo.v();
      localBuilder3.a(((PackageInfo)localObject2).packageName);
      localBuilder3.b(((PackageInfo)localObject2).versionCode);
      localBuilder1.a(localBuilder3.r());
    }
    localObject1 = (TelephonyManager)paramContext.getSystemService("phone");
    if (localObject1 != null)
    {
      localObject2 = ((TelephonyManager)localObject1).getDeviceId();
      if (localObject2 != null)
      {
        if (((TelephonyManager)localObject1).getPhoneType() != 2) {
          break label430;
        }
        localBuilder2.b((String)localObject2);
      }
      if (((TelephonyManager)localObject1).getLine1Number() != null) {
        localBuilder2.c(((TelephonyManager)localObject1).getLine1Number());
      }
    }
    localObject1 = ((LocationManager)paramContext.getSystemService("location")).getLastKnownLocation("gps");
    if (localObject1 != null)
    {
      localObject2 = Mi12Mobile.DeviceFingerprinting.Parsed.State.Location.y();
      if (((Location)localObject1).hasAccuracy()) {
        ((Mi12Mobile.DeviceFingerprinting.Parsed.State.Location.Builder)localObject2).a(((Location)localObject1).getAccuracy());
      }
      if (((Location)localObject1).hasAltitude()) {
        ((Mi12Mobile.DeviceFingerprinting.Parsed.State.Location.Builder)localObject2).a(((Location)localObject1).getAltitude());
      }
      ((Mi12Mobile.DeviceFingerprinting.Parsed.State.Location.Builder)localObject2).d(((Location)localObject1).getTime());
      ((Mi12Mobile.DeviceFingerprinting.Parsed.State.Location.Builder)localObject2).b(((Location)localObject1).getLatitude());
      ((Mi12Mobile.DeviceFingerprinting.Parsed.State.Location.Builder)localObject2).c(((Location)localObject1).getLongitude());
      localBuilder1.a((Mi12Mobile.DeviceFingerprinting.Parsed.State.Location.Builder)localObject2);
    }
    if (Settings.Secure.getInt(paramContext.getContentResolver(), "adb_enabled", 0) == 1)
    {
      bool1 = true;
      label313:
      localBuilder1.a(bool1);
      if (Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps", 0) != 1) {
        break label446;
      }
    }
    label430:
    label446:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      localBuilder1.b(bool1);
      localBuilder1.a(Locale.getDefault().getISO3Language());
      localBuilder1.a(TimeZone.getDefault().getRawOffset());
      localBuilder2.a(Settings.Secure.getLong(paramContext.getContentResolver(), "android_id", 0L));
      localBuilder2.e(Build.PRODUCT);
      localBuilder2.f(Build.MODEL);
      localBuilder2.d(Build.DEVICE);
      localBuilder2.g(Build.MANUFACTURER);
      return localBuilder.a(localParsed).r();
      localBuilder2.a((String)localObject2);
      break;
      bool1 = false;
      break label313;
    }
  }
  
  public static String a(Context paramContext, Mi12Mobile.DeviceFingerprinting.Parsed paramParsed)
  {
    try
    {
      paramContext = KeyczarEncryptionService.a(new AndroidKeyczarReader(paramContext.getResources(), "paymentfp.keys.public"), paramParsed.aQ());
      return paramContext;
    }
    catch (KeyczarException paramContext)
    {
      throw new PaymentFpException(paramContext);
    }
  }
}
