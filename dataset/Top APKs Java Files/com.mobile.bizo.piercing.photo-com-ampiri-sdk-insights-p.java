package com.ampiri.sdk.insights;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.hardware.display.DisplayManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.ampiri.sdk.Ampiri;
import com.ampiri.sdk.device.a;
import com.ampiri.sdk.device.b;
import com.ampiri.sdk.device.c;
import com.ampiri.sdk.device.d;
import com.ampiri.sdk.device.g;
import com.ampiri.sdk.device.h;
import com.ampiri.sdk.device.i;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

final class p
{
  public static o a(String paramString)
  {
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return new o();
        if (paramString.equals("261DBACE78C09AFDFBCDFC12CF7F8978"))
        {
          i = 0;
          continue;
          if (paramString.equals("D8E64AA16A20BEAE77E42794CD230D19"))
          {
            i = 1;
            continue;
            if (paramString.equals("1DBF36EBBDBF37B55F66E60AA3A7ED06"))
            {
              i = 2;
              continue;
              if (paramString.equals("80A4ACF674CB9F681A5896F6F8271370"))
              {
                i = 3;
                continue;
                if (paramString.equals("C855D22DAFE2C6A311E0FFEE9889A3BD"))
                {
                  i = 4;
                  continue;
                  if (paramString.equals("8B1657403513A6898D1CF845C771BA44"))
                  {
                    i = 5;
                    continue;
                    if (paramString.equals("A1CAD2060B55E2FB7743C4AC50E5611A"))
                    {
                      i = 6;
                      continue;
                      if (paramString.equals("F5B5DC1151BC78B4990E5E6D12CCF5A8"))
                      {
                        i = 7;
                        continue;
                        if (paramString.equals("579DA300D42A7E0AF4D352AC941EBD3E"))
                        {
                          i = 8;
                          continue;
                          if (paramString.equals("34CE490EC9B4EBF1B7D8A6F4D4065BBE"))
                          {
                            i = 9;
                            continue;
                            if (paramString.equals("E8B1F4593BCF98EA03E5B697C1D8224E"))
                            {
                              i = 10;
                              continue;
                              if (paramString.equals("F403380E5B6F93C1AD2A7368456F2272"))
                              {
                                i = 11;
                                continue;
                                if (paramString.equals("40CA06F7892FB6810EE29A0631776E59"))
                                {
                                  i = 12;
                                  continue;
                                  if (paramString.equals("C0DA26B01718FED11029796709BDEB22"))
                                  {
                                    i = 13;
                                    continue;
                                    if (paramString.equals("1ECE491BA4B1E9F7A363011AB7156F05"))
                                    {
                                      i = 14;
                                      continue;
                                      if (paramString.equals("3B7890A8C5B12A933455BE3F6D94D7F5"))
                                      {
                                        i = 15;
                                        continue;
                                        if (paramString.equals("66173C19D38C42EA12894BB82504D760"))
                                        {
                                          i = 16;
                                          continue;
                                          if (paramString.equals("241EDF38D17915241B03C8E732BAD30A"))
                                          {
                                            i = 17;
                                            continue;
                                            if (paramString.equals("3D4F2D4784C1D852EBD5BDB709D26488"))
                                            {
                                              i = 18;
                                              continue;
                                              if (paramString.equals("1953572556DE950CF9868F213324C2FE"))
                                              {
                                                i = 19;
                                                continue;
                                                if (paramString.equals("B59C34DAD5A21E224DB0513E24B45EE7"))
                                                {
                                                  i = 20;
                                                  continue;
                                                  if (paramString.equals("E8AE3BD51B2AF6524B057CBA2840A074"))
                                                  {
                                                    i = 21;
                                                    continue;
                                                    if (paramString.equals("3C9786F947FE856D6E2A8C0E67D958F6"))
                                                    {
                                                      i = 22;
                                                      continue;
                                                      if (paramString.equals("9F63C2D02C491F1824CD358818494E7E"))
                                                      {
                                                        i = 23;
                                                        continue;
                                                        if (paramString.equals("723C4050F6D0CA1C2E9EBF8307B38ACA"))
                                                        {
                                                          i = 24;
                                                          continue;
                                                          if (paramString.equals("00D85FF6E565263A7D4136D9963ECD77"))
                                                          {
                                                            i = 25;
                                                            continue;
                                                            if (paramString.equals("1776CAF5E7117F2D92481596B26445C5"))
                                                            {
                                                              i = 26;
                                                              continue;
                                                              if (paramString.equals("910EA6DADE2CB1C8935F957DBBBEEF65"))
                                                              {
                                                                i = 27;
                                                                continue;
                                                                if (paramString.equals("18006AACF691A0A4BC72432496A94274"))
                                                                {
                                                                  i = 28;
                                                                  continue;
                                                                  if (paramString.equals("F8ECC8990AD1F11EDD2F7FA3731AF7BB"))
                                                                  {
                                                                    i = 29;
                                                                    continue;
                                                                    if (paramString.equals("1DA9FFFE749486CF02742749A60FE480"))
                                                                    {
                                                                      i = 30;
                                                                      continue;
                                                                      if (paramString.equals("9a2289d2d964376b7563fa920b2679d1"))
                                                                      {
                                                                        i = 31;
                                                                        continue;
                                                                        if (paramString.equals("FA4033F357AA552B225AEA6792D37B4E"))
                                                                        {
                                                                          i = 32;
                                                                          continue;
                                                                          if (paramString.equals("79EB3813341A97746B9698D7731587B5"))
                                                                          {
                                                                            i = 33;
                                                                            continue;
                                                                            if (paramString.equals("23FD07612592BDF2730D5634255B3E3E"))
                                                                            {
                                                                              i = 34;
                                                                              continue;
                                                                              if (paramString.equals("C6E5648CD45D835521238587A14C8B61"))
                                                                              {
                                                                                i = 35;
                                                                                continue;
                                                                                if (paramString.equals("FBC8D9CCF7C4DB1C988DC51D8D8C6553"))
                                                                                {
                                                                                  i = 36;
                                                                                  continue;
                                                                                  if (paramString.equals("61E07C73F43F285B7C246EDB42C32345")) {
                                                                                    i = 37;
                                                                                  }
                                                                                }
                                                                              }
                                                                            }
                                                                          }
                                                                        }
                                                                      }
                                                                    }
                                                                  }
                                                                }
                                                              }
                                                            }
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        break;
      }
    }
    return new ac();
    return new ae();
    return new m();
    return new b();
    return new g();
    return new ab();
    return new al();
    return new x();
    return new q();
    return new a();
    return new ak();
    return new an();
    return new aa();
    return new u();
    return new z();
    return new d();
    return new h();
    return new n();
    return new ah();
    return new k();
    return new ad();
    return new ag();
    return new c(null);
    return new w();
    return new t();
    return new y();
    return new l();
    return new ai();
    return new af();
    return new aj();
    return new v();
    return new r();
    return new i();
    return new am();
    return new e();
    return new ao();
    return new p();
    return new j();
  }
  
  static class a
    implements o
  {
    a() {}
    
    public q a(Context paramContext, String paramString)
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "accessibility_enabled");
      if (TextUtils.isEmpty(paramContext)) {
        return new q.c();
      }
      if (!"0".equals(paramContext)) {}
      for (boolean bool = true;; bool = false) {
        return new q.b(Boolean.valueOf(bool));
      }
    }
  }
  
  static class aa
    implements o
  {
    aa() {}
    
    public q a(Context paramContext, String paramString)
    {
      paramContext = i.a(paramContext);
      if (paramContext == null) {
        return new q.c();
      }
      return new q.b(paramContext);
    }
  }
  
  static class ab
    implements o
  {
    ab() {}
    
    public q a(Context paramContext, String paramString)
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        paramContext = (PowerManager)paramContext.getSystemService("power");
        if (paramContext != null) {
          return new q.b(Boolean.valueOf(paramContext.isPowerSaveMode()));
        }
      }
      return new q.c();
    }
  }
  
  static class ac
    implements o
  {
    ac() {}
    
    public q a(Context paramContext, String paramString)
    {
      return new q.i(Build.MANUFACTURER);
    }
  }
  
  static class ad
    implements o
  {
    ad() {}
    
    public q a(Context paramContext, String paramString)
    {
      paramContext = com.ampiri.sdk.device.k.a(paramContext);
      if (paramContext != null) {
        return new q.i(paramContext.a());
      }
      return new q.c();
    }
  }
  
  static class ae
    implements o
  {
    ae() {}
    
    public q a(Context paramContext, String paramString)
    {
      return new q.i(Build.MODEL);
    }
  }
  
  static class af
    implements o
  {
    af() {}
    
    @SuppressLint({"WifiManagerPotentialLeak"})
    public q a(Context paramContext, String paramString)
    {
      if (!com.ampiri.sdk.utils.k.a(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
        return new q.g();
      }
      paramContext = (WifiManager)paramContext.getSystemService("wifi");
      if (paramContext != null)
      {
        paramString = paramContext.getConnectionInfo();
        if ((paramString != null) && (paramString.getSupplicantState() == SupplicantState.COMPLETED))
        {
          paramString = paramContext.getConnectionInfo().getSSID();
          if (paramString != null)
          {
            paramContext = paramString;
            if (paramString.startsWith("\""))
            {
              paramContext = paramString;
              if (paramString.endsWith("\"")) {
                paramContext = paramString.replace("\"", "");
              }
            }
            return new q.i(paramContext);
          }
        }
      }
      return new q.c();
    }
  }
  
  static class ag
    implements o
  {
    ag() {}
    
    public q a(Context paramContext, String paramString)
    {
      return new q.i(Build.VERSION.RELEASE);
    }
  }
  
  static class ah
    implements o
  {
    ah() {}
    
    public q a(Context paramContext, String paramString)
    {
      paramContext = (AudioManager)paramContext.getSystemService("audio");
      if (paramContext != null)
      {
        int i = paramContext.getRingerMode();
        if (i == 2) {
          return new q.i("normal");
        }
        if (i == 0) {
          return new q.i("silent");
        }
        if (i == 1) {
          return new q.i("vibrate");
        }
      }
      return new q.c();
    }
  }
  
  static class ai
    implements o
  {
    ai() {}
    
    public q a(Context paramContext, String paramString)
    {
      boolean bool2 = true;
      boolean bool1 = true;
      for (;;)
      {
        try
        {
          if (Build.VERSION.SDK_INT >= 17)
          {
            if (Settings.Secure.getInt(paramContext.getContentResolver(), "data_roaming") == 1)
            {
              paramContext = Boolean.valueOf(bool1);
              return new q.b(paramContext);
            }
          }
          else
          {
            if (Settings.Secure.getInt(paramContext.getContentResolver(), "data_roaming") == 1)
            {
              bool1 = bool2;
              paramContext = Boolean.valueOf(bool1);
              continue;
            }
            bool1 = false;
            continue;
          }
          bool1 = false;
        }
        catch (Settings.SettingNotFoundException paramContext)
        {
          return new q.c();
        }
      }
    }
  }
  
  static class aj
    implements o
  {
    aj() {}
    
    public q a(Context paramContext, String paramString)
    {
      if (!com.ampiri.sdk.utils.k.a(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {
        return new q.g();
      }
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext != null)
      {
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext != null) {
          return new q.b(Boolean.valueOf(paramContext.isRoaming()));
        }
      }
      return new q.c();
    }
  }
  
  @SuppressFBWarnings({"DMI_HARDCODED_ABSOLUTE_FILENAME"})
  static class ak
    implements o
  {
    ak() {}
    
    public q a(Context paramContext, String paramString)
    {
      if (((TextUtils.isEmpty(Build.TAGS)) && (Build.TAGS.contains("test-keys"))) || (new File("/system/app/Superuser.apk").exists()) || (new File("/system/xbin/su").exists())) {}
      for (boolean bool = true;; bool = false) {
        return new q.b(Boolean.valueOf(bool));
      }
    }
  }
  
  static class al
    implements o
  {
    al() {}
    
    public q a(Context paramContext, String paramString)
    {
      if (Build.VERSION.SDK_INT < 20) {
        return new q.c();
      }
      paramContext = ((DisplayManager)paramContext.getSystemService("display")).getDisplays();
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        if (paramContext[i].getState() == 2) {
          return new q.b(Boolean.valueOf(true));
        }
        i += 1;
      }
      return new q.b(Boolean.valueOf(false));
    }
  }
  
  static class am
    implements o
  {
    am() {}
    
    public q a(Context paramContext, String paramString)
    {
      return new q.i(Ampiri.sdkVersion());
    }
  }
  
  static class an
    implements o
  {
    an() {}
    
    public q a(Context paramContext, String paramString)
    {
      paramContext = TimeZone.getDefault();
      long l1 = TimeUnit.MILLISECONDS.toHours(paramContext.getRawOffset());
      long l2 = Math.abs(TimeUnit.MILLISECONDS.toMinutes(paramContext.getRawOffset()) - TimeUnit.HOURS.toMinutes(l1));
      return new q.i(String.format(Locale.US, "%+d.%02d", new Object[] { Long.valueOf(l1), Long.valueOf(l2) }));
    }
  }
  
  static class ao
    implements o
  {
    ao() {}
    
    public q a(Context paramContext, String paramString)
    {
      paramContext = new JSONObject();
      try
      {
        paramContext.put("java_version", System.getProperty("java.specification.version"));
        paramContext.put("cpu_architecture", System.getProperty("os.arch"));
        return new q.d(Collections.singletonList(paramContext));
      }
      catch (JSONException paramString)
      {
        for (;;) {}
      }
    }
  }
  
  static class b
    implements o
  {
    b() {}
    
    public q a(Context paramContext, String paramString)
    {
      if (!com.ampiri.sdk.utils.k.a(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {
        return new q.g();
      }
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext != null)
      {
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext == null) {
          return new q.c();
        }
        if (paramContext.getType() == 1) {
          return new q.i("wifi");
        }
        if (paramContext.getType() == 0) {
          return new q.i("mobile");
        }
      }
      return new q.c();
    }
  }
  
  static class c
    implements o
  {
    private c() {}
    
    public q a(Context paramContext, String paramString)
    {
      return new q.i(d.a(paramContext).a);
    }
  }
  
  static class d
    implements o
  {
    d() {}
    
    public q a(Context paramContext, String paramString)
    {
      return new q.b(Boolean.valueOf(a.a(paramContext)));
    }
  }
  
  static class e
    implements o
  {
    e() {}
    
    public q a(Context paramContext, String paramString)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
        if (paramContext != null)
        {
          paramContext = paramContext.versionName;
          if (!TextUtils.isEmpty(paramContext))
          {
            paramContext = new q.i(paramContext);
            return paramContext;
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramContext) {}
      return new q.c();
    }
  }
  
  static abstract class f
    implements o
  {
    f() {}
    
    static JSONObject a(Location paramLocation)
      throws JSONException
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("lat", paramLocation.getLatitude());
      localJSONObject.put("lon", paramLocation.getLongitude());
      localJSONObject.put("horizontalAcc", paramLocation.getAccuracy());
      localJSONObject.put("speed", paramLocation.getSpeed());
      localJSONObject.put("altitude", paramLocation.getAltitude());
      localJSONObject.put("fixtime", paramLocation.getTime());
      return localJSONObject;
    }
    
    abstract Location a(LocationManager paramLocationManager);
    
    public q a(Context paramContext, String paramString)
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      if (localLocationManager == null) {
        return new q.c();
      }
      paramString = null;
      if (com.ampiri.sdk.utils.k.a(paramContext, "android.permission.ACCESS_FINE_LOCATION")) {
        paramString = b(localLocationManager);
      }
      Object localObject = paramString;
      if (paramString == null)
      {
        if (com.ampiri.sdk.utils.k.a(paramContext, "android.permission.ACCESS_COARSE_LOCATION")) {
          localObject = a(localLocationManager);
        }
      }
      else
      {
        if (localObject != null) {
          break label84;
        }
        return new q.c();
      }
      return new q.g();
      try
      {
        label84:
        paramContext = new q.d(Collections.singletonList(a((Location)localObject)));
        return paramContext;
      }
      catch (JSONException paramContext) {}
      return new q.c();
    }
    
    abstract Location b(LocationManager paramLocationManager);
  }
  
  static class g
    implements o
  {
    g() {}
    
    public q a(Context paramContext, String paramString)
    {
      paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (paramContext == null) {
        return new q.c();
      }
      int i = paramContext.getIntExtra("plugged", -1);
      if (i == 1) {
        return new q.i("socket");
      }
      if (i == 2) {
        return new q.i("usb");
      }
      if ((Build.VERSION.SDK_INT >= 17) && (i == 4)) {
        return new q.i("wireless");
      }
      return new q.c();
    }
  }
  
  static class h
    implements o
  {
    h() {}
    
    public q a(Context paramContext, String paramString)
    {
      if (!com.ampiri.sdk.utils.k.a(paramContext, "android.permission.BLUETOOTH")) {
        return new q.g();
      }
      paramContext = BluetoothAdapter.getDefaultAdapter();
      if (paramContext == null) {
        return new q.c();
      }
      return new q.b(Boolean.valueOf(paramContext.isEnabled()));
    }
  }
  
  static class i
    implements o
  {
    i() {}
    
    public q a(Context paramContext, String paramString)
    {
      if (!com.ampiri.sdk.utils.k.a(paramContext, "com.android.browser.permission.READ_HISTORY_BOOKMARKS")) {
        return new q.g();
      }
      if (Build.VERSION.SDK_INT <= 22)
      {
        paramContext = paramContext.getContentResolver().query(Uri.parse("content://browser/bookmarks"), new String[] { "title", "url" }, "bookmark = 0", null, null);
        if (paramContext != null)
        {
          paramString = new ArrayList();
          if ((paramContext.moveToFirst()) && (paramContext.getCount() > 0)) {
            while (!paramContext.isAfterLast())
            {
              paramString.add(paramContext.getString(paramContext.getColumnIndex("url")));
              paramContext.moveToNext();
            }
          }
          paramContext.close();
          return new q.h(paramString);
        }
      }
      return new q.c();
    }
  }
  
  static class j
    implements o
  {
    j() {}
    
    public q a(Context paramContext, String paramString)
    {
      boolean bool = true;
      if ((Build.VERSION.SDK_INT >= 17) && (Build.VERSION.SDK_INT < 25))
      {
        if (Settings.Secure.getInt(paramContext.getContentResolver(), "show_processes", 0) == 1) {}
        for (;;)
        {
          return new q.b(Boolean.valueOf(bool));
          bool = false;
        }
      }
      return new q.c();
    }
  }
  
  static class k
    implements o
  {
    k() {}
    
    public q a(Context paramContext, String paramString)
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if ((paramContext != null) && (!TextUtils.isEmpty(paramContext.getNetworkOperatorName()))) {
        return new q.i(paramContext.getNetworkOperatorName());
      }
      return new q.c();
    }
  }
  
  static class l
    extends p.f
  {
    l() {}
    
    private static Location a(LocationManager paramLocationManager, String paramString)
    {
      if (!paramLocationManager.getAllProviders().contains(paramString)) {}
      for (;;)
      {
        return null;
        CountDownLatch localCountDownLatch = new CountDownLatch(1);
        a localA = new a(localCountDownLatch);
        paramLocationManager.requestSingleUpdate(paramString, localA, Looper.getMainLooper());
        try
        {
          if (localCountDownLatch.await(10L, TimeUnit.SECONDS))
          {
            paramLocationManager = localA.a();
            return paramLocationManager;
          }
        }
        catch (InterruptedException paramLocationManager) {}
      }
      return null;
    }
    
    Location a(LocationManager paramLocationManager)
    {
      return a(paramLocationManager, "network");
    }
    
    Location b(LocationManager paramLocationManager)
    {
      return a(paramLocationManager, "gps");
    }
    
    static class a
      implements LocationListener
    {
      final CountDownLatch a;
      Location b;
      
      a(CountDownLatch paramCountDownLatch)
      {
        this.a = paramCountDownLatch;
      }
      
      Location a()
      {
        return this.b;
      }
      
      public void onLocationChanged(Location paramLocation)
      {
        this.b = paramLocation;
        this.a.countDown();
      }
      
      public void onProviderDisabled(String paramString) {}
      
      public void onProviderEnabled(String paramString) {}
      
      public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
    }
  }
  
  static class m
    extends p.s
  {
    m()
    {
      super();
    }
    
    q a(Context paramContext, InputMethodManager paramInputMethodManager)
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "default_input_method");
      if (TextUtils.isEmpty(paramContext)) {
        return new q.c();
      }
      Object localObject = paramInputMethodManager.getEnabledInputMethodList();
      if (localObject == null) {
        return new q.c();
      }
      ArrayList localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        InputMethodInfo localInputMethodInfo = (InputMethodInfo)((Iterator)localObject).next();
        if (paramContext.equals(localInputMethodInfo.getId())) {
          localArrayList.addAll(g.a(localInputMethodInfo, paramInputMethodManager));
        }
      }
      if (localArrayList.isEmpty()) {
        return new q.c();
      }
      return new q.d(localArrayList);
    }
  }
  
  static class n
    implements o
  {
    n() {}
    
    public q a(Context paramContext, String paramString)
    {
      return new q.b(Boolean.valueOf(c.a(paramContext)));
    }
  }
  
  static class o
    implements o
  {
    o() {}
    
    public q a(Context paramContext, String paramString)
    {
      return new q.c();
    }
  }
  
  static class p
    implements o
  {
    p() {}
    
    public q a(Context paramContext, String paramString)
    {
      if (Build.VERSION.SDK_INT >= 14)
      {
        paramString = new HashSet();
        paramContext = (AccessibilityManager)paramContext.getSystemService("accessibility");
        if (paramContext != null)
        {
          paramContext = paramContext.getEnabledAccessibilityServiceList(-1).iterator();
          while (paramContext.hasNext()) {
            paramString.add(((AccessibilityServiceInfo)paramContext.next()).getId());
          }
          return new q.h(paramString);
        }
      }
      return new q.c();
    }
  }
  
  static class q
    implements o
  {
    q() {}
    
    public q a(Context paramContext, String paramString)
    {
      return new q.f(b.a(paramContext, Environment.getExternalStorageDirectory().getAbsolutePath()));
    }
  }
  
  static class r
    implements o
  {
    r() {}
    
    public q a(Context paramContext, String paramString)
    {
      if (Build.VERSION.SDK_INT < 21)
      {
        if (!com.ampiri.sdk.utils.k.a(paramContext, "android.permission.GET_TASKS")) {
          return new q.g();
        }
        paramString = (ActivityManager)paramContext.getSystemService("activity");
        if (paramString != null)
        {
          paramContext = new ArrayList();
          paramString = paramString.getRunningTasks(Integer.MAX_VALUE).iterator();
          while (paramString.hasNext()) {
            paramContext.add(((ActivityManager.RunningTaskInfo)paramString.next()).topActivity.getPackageName());
          }
          return new q.h(paramContext);
        }
      }
      return new q.c();
    }
  }
  
  static abstract class s
    implements o
  {
    private s() {}
    
    abstract q a(Context paramContext, InputMethodManager paramInputMethodManager);
    
    public q a(Context paramContext, String paramString)
    {
      paramString = (InputMethodManager)paramContext.getSystemService("input_method");
      if (paramString == null) {
        return new q.c();
      }
      return a(paramContext, paramString);
    }
  }
  
  static class t
    extends p.s
  {
    t()
    {
      super();
    }
    
    q a(Context paramContext, InputMethodManager paramInputMethodManager)
    {
      Object localObject = paramInputMethodManager.getEnabledInputMethodList();
      if (localObject == null) {
        return new q.c();
      }
      paramContext = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramContext.addAll(g.a((InputMethodInfo)((Iterator)localObject).next(), paramInputMethodManager));
      }
      if (paramContext.isEmpty()) {
        return new q.c();
      }
      return new q.d(paramContext);
    }
  }
  
  static class u
    implements o
  {
    u() {}
    
    public q a(Context paramContext, String paramString)
    {
      boolean bool = true;
      try
      {
        if (Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps") == 1) {}
        for (;;)
        {
          paramContext = new q.b(Boolean.valueOf(bool));
          return paramContext;
          bool = false;
        }
        return new q.c();
      }
      catch (Settings.SettingNotFoundException paramContext) {}
    }
  }
  
  static class v
    implements o
  {
    v() {}
    
    public q a(Context paramContext, String paramString)
    {
      paramString = paramContext.getPackageManager().getInstalledApplications(128);
      paramContext = new ArrayList();
      paramString = paramString.iterator();
      while (paramString.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramString.next();
        if (!TextUtils.isEmpty(localApplicationInfo.packageName)) {
          paramContext.add(localApplicationInfo.packageName);
        }
      }
      return new q.h(paramContext);
    }
  }
  
  static class w
    implements o
  {
    w() {}
    
    public q a(Context paramContext, String paramString)
    {
      paramContext = paramContext.getResources().getConfiguration();
      if ((paramContext != null) && (paramContext.locale != null)) {
        return new q.i(paramContext.locale.getLanguage());
      }
      return new q.c();
    }
  }
  
  static class x
    implements o
  {
    x() {}
    
    public q a(Context paramContext, String paramString)
    {
      return new q.f(b.a(paramContext, Environment.getDataDirectory().getAbsolutePath()));
    }
  }
  
  static class y
    extends p.f
  {
    y() {}
    
    Location a(LocationManager paramLocationManager)
    {
      String str = paramLocationManager.getBestProvider(new Criteria(), true);
      if (TextUtils.isEmpty(str)) {
        return null;
      }
      return paramLocationManager.getLastKnownLocation(str);
    }
    
    Location b(LocationManager paramLocationManager)
    {
      return paramLocationManager.getLastKnownLocation("gps");
    }
  }
  
  static class z
    implements o
  {
    z() {}
    
    public q a(Context paramContext, String paramString)
    {
      paramContext = h.a(paramContext);
      if (paramContext == null) {
        return new q.c();
      }
      return new q.i(paramContext);
    }
  }
}
