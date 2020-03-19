package com.embrace_2redbeans;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class DeviceUtilModule
  extends ReactContextBaseJavaModule
{
  private static final String BUILD_FLAVOR = "ANDROID_BUILD_FLAVOR";
  private static final String SDK_INT = "ANDROID_SDK_INT";
  private static final String WECHAT_PACKAGE_NAME = "com.tencent.mm";
  public static SortedMap<Currency, Locale> currencyLocaleMap = new TreeMap(new Comparator()
  {
    public int compare(Currency paramAnonymousCurrency1, Currency paramAnonymousCurrency2)
    {
      return paramAnonymousCurrency1.getCurrencyCode().compareTo(paramAnonymousCurrency2.getCurrencyCode());
    }
  });
  
  static
  {
    Locale[] arrayOfLocale = Locale.getAvailableLocales();
    int i = 0;
    int j = arrayOfLocale.length;
    for (;;)
    {
      Locale localLocale;
      if (i < j) {
        localLocale = arrayOfLocale[i];
      }
      try
      {
        Currency localCurrency = Currency.getInstance(localLocale);
        currencyLocaleMap.put(localCurrency, localLocale);
        i += 1;
        continue;
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public DeviceUtilModule(ReactApplicationContext paramReactApplicationContext)
  {
    super(paramReactApplicationContext);
  }
  
  @ReactMethod
  public void getAPIVersion(Callback paramCallback)
  {
    paramCallback.invoke(new Object[] { Integer.valueOf(Build.VERSION.SDK_INT) });
  }
  
  public Map<String, Object> getConstants()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("ANDROID_SDK_INT", Integer.valueOf(Build.VERSION.SDK_INT));
    localHashMap.put("ANDROID_BUILD_FLAVOR", "".replaceAll("_", ""));
    return localHashMap;
  }
  
  @ReactMethod
  public void getCurrencySymbol(String paramString, Callback paramCallback)
  {
    paramString = Currency.getInstance(Locale.US);
    paramCallback.invoke(new Object[] { paramString.getSymbol((Locale)currencyLocaleMap.get(paramString)) });
  }
  
  public String getName()
  {
    return "DeviceUtilAndroid";
  }
  
  @ReactMethod
  public void isWechatInstalled(Callback paramCallback)
  {
    List localList = MainApplication.getContext().getPackageManager().getInstalledPackages(0);
    if (localList != null)
    {
      int i = 0;
      while (i < localList.size())
      {
        if (((PackageInfo)localList.get(i)).packageName.equals("com.tencent.mm")) {
          paramCallback.invoke(new Object[] { Boolean.valueOf(true) });
        }
        i += 1;
      }
    }
    paramCallback.invoke(new Object[] { Boolean.valueOf(false) });
  }
}
