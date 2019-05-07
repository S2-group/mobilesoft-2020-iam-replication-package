package com.mologiq.analytics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@SuppressLint({"NewApi"})
class m
  extends AsyncTask<Object, Object, Object>
{
  private final WeakReference<Context> a;
  
  m(Context paramContext)
  {
    this.a = new WeakReference(paramContext);
  }
  
  @SuppressLint({"NewApi"})
  private void a(y paramY, Context paramContext)
  {
    Object localObject4;
    Object localObject2;
    label1291:
    for (;;)
    {
      x localX;
      Object localObject5;
      Object localObject6;
      Object localObject7;
      try
      {
        localX = x.d(paramContext);
        if (localX.i())
        {
          localObject1 = paramContext.getPackageManager().getInstalledApplications(128);
          localObject5 = localX.s();
          localObject6 = ((List)localObject1).iterator();
          localObject1 = null;
          localObject3 = null;
          if (((Iterator)localObject6).hasNext())
          {
            localObject4 = (ApplicationInfo)((Iterator)localObject6).next();
            if (!((Map)localObject5).containsKey(((ApplicationInfo)localObject4).packageName)) {
              break;
            }
            if (localObject3 != null) {
              break label1291;
            }
            localObject3 = new ArrayList();
            localObject7 = (e)((Map)localObject5).get(((ApplicationInfo)localObject4).packageName);
            ((List)localObject3).add(Integer.valueOf(((e)localObject7).c()));
            localObject4 = localObject1;
            if (((e)localObject7).a() <= 0) {
              break label1297;
            }
            localObject4 = localObject1;
            if (localObject1 == null) {
              localObject4 = new HashMap();
            }
            if (((Map)localObject4).containsKey(Integer.valueOf(((e)localObject7).a())))
            {
              int i = ((Integer)((Map)localObject4).get(Integer.valueOf(((e)localObject7).a()))).intValue();
              ((Map)localObject4).put(Integer.valueOf(((e)localObject7).a()), Integer.valueOf(i + 1));
              localObject1 = localObject4;
              break;
            }
            ((Map)localObject4).put(Integer.valueOf(((e)localObject7).a()), Integer.valueOf(1));
            break label1297;
          }
          paramY.a((List)localObject3);
          paramY.a((Map)localObject1);
        }
        localObject1 = localX.q();
        if (localObject1 != null)
        {
          localObject4 = ((Map)localObject1).values().iterator();
          localObject1 = null;
          if (((Iterator)localObject4).hasNext())
          {
            localObject5 = (h)((Iterator)localObject4).next();
            localObject3 = localObject1;
            if (localObject1 == null) {
              localObject3 = new ArrayList();
            }
            localObject1 = localObject3;
            if (!z.b(paramContext, ((h)localObject5).b())) {
              continue;
            }
            ((List)localObject3).add(Integer.valueOf(((h)localObject5).a()));
            localObject1 = localObject3;
            continue;
          }
          paramY.b((List)localObject1);
        }
      }
      catch (Exception paramY)
      {
        z.a(z.a(paramY));
        return;
      }
      for (;;)
      {
        try
        {
          localObject1 = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0);
          localObject3 = paramContext.getApplicationContext().getPackageManager();
          if ((localObject1 != null) && (localObject3 != null)) {
            paramY.a(((PackageManager)localObject3).getApplicationLabel((ApplicationInfo)localObject1).toString());
          }
        }
        catch (Exception localException1)
        {
          boolean bool;
          z.a("Application name not found");
          continue;
          paramY.i(Settings.Secure.getString(paramContext.getContentResolver(), "android_id"));
          continue;
        }
        try
        {
          localObject1 = d.a(paramContext);
          localObject3 = ((d)localObject1).a();
          bool = ((d)localObject1).b();
          if ((localObject3 == null) || (((String)localObject3).length() <= 0)) {
            continue;
          }
          paramY.h((String)localObject3);
          paramY.a(bool);
        }
        catch (Exception localException2)
        {
          continue;
          localException2.a((List)localObject4);
          localObject3 = ((WifiManager)localObject3).getConnectionInfo();
          localObject4 = new aa();
          ((aa)localObject4).a(((WifiInfo)localObject3).getSSID());
          localException2.a((aa)localObject4);
          paramY.a(localException2);
          paramY.o(((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName());
          paramY.e(String.valueOf(Version.a()));
        }
      }
      paramY.b(Build.VERSION.RELEASE);
      paramY.c(Build.MODEL);
      paramY.d(Build.DEVICE);
      paramY.j(Build.MANUFACTURER);
      paramY.f(Build.BRAND);
      Object localObject1 = TimeZone.getDefault();
      Object localObject3 = ((TimeZone)localObject1).getDisplayName(Locale.getDefault());
      if (localObject3 != null) {
        paramY.k((String)localObject3);
      }
      localObject1 = ((TimeZone)localObject1).getID();
      if (localObject1 != null) {
        paramY.l((String)localObject1);
      }
      paramY.m(Locale.getDefault().getCountry());
      if ((paramContext instanceof Activity))
      {
        localObject1 = paramContext.getApplicationContext().getResources().getDisplayMetrics();
        if (localObject1 != null) {
          paramY.n(((DisplayMetrics)localObject1).widthPixels + "*" + ((DisplayMetrics)localObject1).heightPixels);
        }
      }
      paramY.g(Locale.getDefault().getDisplayLanguage());
      if ((z.a(paramContext, "android.permission.ACCESS_WIFI_STATE")) && (localX.j()))
      {
        paramY.getClass();
        localObject1 = new y.b(paramY);
        localObject3 = (WifiManager)paramContext.getSystemService("wifi");
        localObject5 = ((WifiManager)localObject3).getScanResults();
        localObject4 = new ArrayList();
        if (localObject5 != null)
        {
          localObject5 = ((List)localObject5).iterator();
          while (((Iterator)localObject5).hasNext())
          {
            localObject6 = (ScanResult)((Iterator)localObject5).next();
            if ((localObject6 != null) && (((ScanResult)localObject6).SSID != null))
            {
              localObject7 = new aa();
              ((aa)localObject7).a(((ScanResult)localObject6).SSID);
              ((List)localObject4).add(localObject7);
            }
          }
        }
      }
      if (localX.h())
      {
        localObject3 = (LocationManager)paramContext.getSystemService("location");
        if (z.a(paramContext, "android.permission.ACCESS_FINE_LOCATION")) {}
        for (localObject2 = ((LocationManager)localObject3).getLastKnownLocation("gps");; localObject2 = null)
        {
          double d1;
          double d2;
          if (localObject2 != null)
          {
            d1 = ((Location)localObject2).getLatitude();
            d2 = ((Location)localObject2).getLongitude();
            paramContext = "" + (int)(localX.o() * d1) + (int)(localX.o() * d2);
            if (!("" + (int)(paramY.l() * localX.o()) + (int)(paramY.m() * localX.o())).equals(paramContext))
            {
              paramY.a(d1);
              paramY.b(d2);
            }
            paramY.d(((Location)localObject2).getAccuracy());
            paramY.c(((Location)localObject2).getAltitude());
            paramY.e(((Location)localObject2).getSpeed());
            paramY.a(((Location)localObject2).getTime());
            return;
          }
          if ((z.a(paramContext, "android.permission.ACCESS_FINE_LOCATION")) || (z.a(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))) {}
          for (paramContext = ((LocationManager)localObject3).getLastKnownLocation("network"); paramContext != null; paramContext = null)
          {
            d1 = paramContext.getLatitude();
            d2 = paramContext.getLongitude();
            localObject2 = "" + (int)(localX.o() * d1) + (int)(localX.o() * d2);
            if (!("" + (int)(paramY.l() * localX.o()) + (int)(paramY.m() * localX.o())).equals(localObject2))
            {
              paramY.a(d1);
              paramY.b(d2);
            }
            paramY.d(paramContext.getAccuracy());
            paramY.c(paramContext.getAltitude());
            paramY.e(paramContext.getSpeed());
            paramY.a(paramContext.getTime());
            return;
          }
          break;
        }
      }
    }
    for (;;)
    {
      break;
      label1297:
      localObject2 = localObject4;
    }
  }
  
  protected Object doInBackground(Object... paramVarArgs)
  {
    label268:
    do
    {
      try
      {
        if (this.a == null) {
          break label268;
        }
        paramVarArgs = (Context)this.a.get();
      }
      catch (Exception paramVarArgs)
      {
        z.a(z.a(paramVarArgs));
        return null;
      }
      x localX = x.d(paramVarArgs);
      if (localX.b()) {
        break;
      }
      y localY = y.c();
      localY.a(paramVarArgs);
      a(localY, paramVarArgs);
      if ((!localY.b(paramVarArgs)) && (System.currentTimeMillis() - localX.n() <= localX.m())) {
        break;
      }
      z localZ = new z(paramVarArgs);
      if (localX.v() == 0)
      {
        str1 = localZ.a(localX.c(), "", paramVarArgs, 500, 1000, false);
        if ((str1 == null) || (str1.length() <= 0)) {
          break;
        }
        localX.a(Integer.parseInt(str1));
        localX.b(paramVarArgs);
        return null;
      }
      String str2 = localX.f();
      k localK = new k();
      localK.a("1.3.2");
      localK.b("2014-08-26");
      if (paramVarArgs.getPackageName() == null) {}
      for (String str1 = "";; str1 = paramVarArgs.getPackageName())
      {
        localK.c(str1);
        localK.a(localY);
        str1 = localZ.a(str2, localK.a(paramVarArgs), paramVarArgs, 500, 1000, true);
        if ((str1 == null) || (str1.length() <= 0)) {
          break;
        }
        localX.b(str1, paramVarArgs);
        localX.a(System.currentTimeMillis());
        localX.b(paramVarArgs);
        return null;
      }
      paramVarArgs = null;
    } while (paramVarArgs != null);
    return null;
  }
}
