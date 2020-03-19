package com.avast.android.adc.b;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class a
{
  private final Context a;
  
  @Inject
  public a(Context paramContext)
  {
    this.a = paramContext;
  }
  
  public String a()
  {
    return Locale.getDefault().getDisplayLanguage();
  }
  
  public String a(String paramString)
  {
    try
    {
      paramString = this.a.getPackageManager().getPackageInfo(paramString, 0).versionName;
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      com.avast.android.adc.c.a.a.e(paramString, "getAppVersionName failed", new Object[0]);
    }
    return null;
  }
  
  public List<String> a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if (Build.VERSION.SDK_INT >= 22) {}
    for (paramContext = new e(paramContext);; paramContext = new f(paramContext))
    {
      int j = paramContext.a();
      int i = 0;
      while (i < j)
      {
        String str = paramContext.a(i);
        if (str != null) {
          localArrayList.add(str);
        }
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public Integer b(String paramString)
  {
    try
    {
      int i = this.a.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return Integer.valueOf(i);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      com.avast.android.adc.c.a.a.e(paramString, "getAppVersionCode failed", new Object[0]);
    }
    return null;
  }
  
  public List<String> b()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = this.a.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageManager.checkSignatures(this.a.getPackageName(), localPackageInfo.packageName) == 0) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public List<Pair<String, String>> b(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    int i;
    if (Build.VERSION.SDK_INT >= 22)
    {
      paramContext = new e(paramContext);
      int j = paramContext.a();
      i = 0;
      label35:
      if (i >= j) {
        break label101;
      }
      if (paramContext.c(i) != null) {
        break label69;
      }
    }
    for (;;)
    {
      i += 1;
      break label35;
      paramContext = new f(paramContext);
      break;
      label69:
      localArrayList.add(new Pair(paramContext.c(i), paramContext.b(i)));
    }
    label101:
    return localArrayList;
  }
  
  public String c()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.a.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null) {
      return null;
    }
    return localNetworkInfo.getTypeName();
  }
  
  public String d()
  {
    return ((TelephonyManager)this.a.getSystemService("phone")).getNetworkOperatorName();
  }
  
  public String e()
  {
    return ((TelephonyManager)this.a.getSystemService("phone")).getNetworkOperator();
  }
  
  public String f()
  {
    return ((TelephonyManager)this.a.getSystemService("phone")).getNetworkCountryIso();
  }
  
  public String g()
  {
    CellLocation localCellLocation = ((TelephonyManager)this.a.getSystemService("phone")).getCellLocation();
    if ((localCellLocation == null) || (!(localCellLocation instanceof GsmCellLocation))) {
      return null;
    }
    return Integer.toString(((GsmCellLocation)localCellLocation).getCid() & 0xFFFF);
  }
  
  public String h()
  {
    CellLocation localCellLocation = ((TelephonyManager)this.a.getSystemService("phone")).getCellLocation();
    if ((localCellLocation == null) || (!(localCellLocation instanceof GsmCellLocation))) {
      return null;
    }
    return Integer.toString(((GsmCellLocation)localCellLocation).getLac() & 0xFFFF);
  }
  
  public boolean i()
  {
    return ((TelephonyManager)this.a.getSystemService("phone")).getDataState() == 2;
  }
  
  public String j()
  {
    return Build.MANUFACTURER + " " + Build.MODEL;
  }
  
  public Integer k()
  {
    Integer localInteger = null;
    Intent localIntent = this.a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    if (localIntent != null)
    {
      int i = localIntent.getIntExtra("level", -1);
      int j = localIntent.getIntExtra("scale", -1);
      localInteger = Integer.valueOf(Math.round(i * 100 / j));
    }
    return localInteger;
  }
  
  public List<Integer> l()
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = Build.VERSION.RELEASE.split("\\.");
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      String str;
      if (i < j) {
        str = arrayOfString[i];
      }
      try
      {
        localArrayList.add(Integer.valueOf(Integer.parseInt(str)));
        i += 1;
        continue;
        return localArrayList;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        for (;;) {}
      }
    }
  }
}
