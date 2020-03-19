package com.chegal.alarm.rate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class IntentHelper
{
  private static final String AMAZON_APPSTORE_PACKAGE_NAME = "com.amazon.venezia";
  private static final String BLACKBERRY_WORLD_PACKAGE_NAME = "net.rim.bb.appworld";
  private static final String[] BROWSERS_PACKAGES_NAMES = { "com.android.chrome", "org.mozilla.firefox", "com.opera.browser", "com.opera.mini.native", "com.sec.android.app.sbrowser", "com.UCMobile.intl", "com.tencent.mtt", "com.android.browser" };
  private static final String CAFE_BAZAAR_PACKAGE_NAME = "com.farsitel.bazaar";
  private static final String[] CHINESE_STORES_PACKAGES_NAMES = { "com.tencent.android.qqdownloader", "com.qihoo.appstore", "com.xiaomi.market", "com.huawei.appmarket", "com.baidu.appsearch", "com.oppo.market", "zte.com.market", "com.bbk.appstore", "com.wandoujia.phoenix2", "com.pp.assistant", "com.hiapk.marketpho", "com.dragon.android.pandaspace", "com.yingyonghui.market", "com.tencent.qqpimsecure", "com.mappn.gfan", "cn.goapk.market", "com.yulong.android.coolmart", "com.lenovo.leos.appstore", "com.coolapk.market" };
  public static final String[] EMPTY_STRING_ARRAY = new String[0];
  private static final String GOOGLE_PLAY_PACKAGE_NAME = "com.android.vending";
  private static final String MI_PACKAGE_NAME = "com.xiaomi.market";
  private static final String SAMSUNG_GALAXY_APPS_PACKAGE_NAME = "com.sec.android.app.samsungapps";
  private static final String SLIDEME_MARKETPLACE_PACKAGE_NAME = "com.slideme.sam.manager";
  private static final String TENCENT_PACKAGE_NAME = "com.tencent.android.qqdownloader";
  private static final String YANDEX_STORE_PACKAGE_NAME = "com.yandex.store";
  
  private IntentHelper()
  {
    throw new UnsupportedOperationException("IntentHelper");
  }
  
  @NonNull
  static Intent[] createIntentsForStore(@NonNull Context paramContext, UriHelper.StoreType paramStoreType, @NonNull String paramString)
  {
    int i = 0;
    if ((paramContext != null) && (paramString != null))
    {
      boolean bool1 = getNeedStorePackageFlagForStore(paramStoreType);
      boolean bool2 = getHasWebUriIntentFlagForStore(paramStoreType);
      String[] arrayOfString2 = getPackagesNamesForStore(paramStoreType);
      String[] arrayOfString1 = isPackagesExists(paramContext, arrayOfString2);
      int j = (byte)arrayOfString1.length;
      if (j > 0)
      {
        if (bool2)
        {
          paramContext = new Intent[j + 1];
          paramContext[j] = new Intent("android.intent.action.VIEW", UriHelper.getStoreWebUri(paramStoreType, paramString));
        }
        else
        {
          paramContext = new Intent[j];
        }
        for (;;)
        {
          localObject = paramContext;
          if (i >= j) {
            break;
          }
          paramContext[i] = new Intent("android.intent.action.VIEW", UriHelper.getStoreUri(paramStoreType, paramString));
          setIntentForStore(paramContext[i]);
          paramContext[i].setPackage(arrayOfString1[i]);
          i = (byte)(i + 1);
        }
      }
      if (!bool1)
      {
        localObject = new Intent[1];
        localObject[0] = new Intent("android.intent.action.VIEW", UriHelper.getStoreWebUri(paramStoreType, paramString));
        if (paramStoreType == UriHelper.StoreType.APPLE)
        {
          paramContext = isPackagesExists(paramContext, BROWSERS_PACKAGES_NAMES);
          if (paramContext.length > 0) {
            localObject[0].setPackage(paramContext[0]);
          }
        }
        return localObject;
      }
      Object localObject = new Intent[0];
      if (bool2)
      {
        paramContext = new StringBuilder();
        paramContext.append(Arrays.toString(arrayOfString2));
        paramContext.append(" not exist on the user device and the user device can't start the app store (");
        paramContext.append(paramStoreType);
        paramContext.append(") web (http/https) uri activity without it.");
        Log.w("IntentHelper", paramContext.toString());
        return localObject;
      }
      paramContext = new StringBuilder();
      paramContext.append(Arrays.toString(arrayOfString2));
      paramContext.append(" not exist on the user device and the app store (");
      paramContext.append(paramStoreType);
      paramContext.append(") hasn't web (http/https) uri.");
      Log.w("IntentHelper", paramContext.toString());
      return localObject;
    }
    if (paramContext == null) {
      Log.w("IntentHelper", "can't check the availability of stores packages on the user device (context == null).");
    }
    if (paramString == null) {
      Log.w("IntentHelper", "can't get store Uri/WebUri (paramName == null).");
    }
    return new Intent[0];
  }
  
  private static boolean getHasWebUriIntentFlagForStore(UriHelper.StoreType paramStoreType)
  {
    return paramStoreType != UriHelper.StoreType.CHINESESTORES;
  }
  
  private static boolean getNeedStorePackageFlagForStore(UriHelper.StoreType paramStoreType)
  {
    switch (1.$SwitchMap$com$chegal$alarm$rate$UriHelper$StoreType[paramStoreType.ordinal()])
    {
    default: 
      return false;
    }
    return true;
  }
  
  @NonNull
  private static String[] getPackagesNamesForStore(UriHelper.StoreType paramStoreType)
  {
    switch (1.$SwitchMap$com$chegal$alarm$rate$UriHelper$StoreType[paramStoreType.ordinal()])
    {
    default: 
      return new String[] { "com.android.vending" };
    case 9: 
      return new String[] { "com.tencent.android.qqdownloader" };
    case 8: 
      return new String[] { "com.slideme.sam.manager" };
    case 7: 
      return new String[] { "com.xiaomi.market" };
    case 6: 
      return new String[] { "net.rim.bb.appworld" };
    case 5: 
      return new String[] { "com.farsitel.bazaar" };
    case 4: 
      return new String[] { "com.amazon.venezia" };
    case 3: 
      return new String[] { "com.yandex.store" };
    case 2: 
      return new String[] { "com.sec.android.app.samsungapps" };
    }
    return CHINESE_STORES_PACKAGES_NAMES;
  }
  
  static String[] isPackagesExists(@NonNull Context paramContext, @NonNull String[] paramArrayOfString)
  {
    if ((paramContext != null) && (paramArrayOfString != null) && (paramArrayOfString.length != 0))
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0);
      if (paramArrayOfString.length == 1)
      {
        if ((paramArrayOfString[0] != null) && (paramArrayOfString[0].hashCode() != "".hashCode()))
        {
          paramContext = paramContext.iterator();
          while (paramContext.hasNext())
          {
            localObject = (ApplicationInfo)paramContext.next();
            if (paramArrayOfString[0].equals(((ApplicationInfo)localObject).packageName)) {
              return new String[] { paramArrayOfString[0] };
            }
          }
        }
        return EMPTY_STRING_ARRAY;
      }
      Object localObject = new ArrayList();
      int j = paramArrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = paramArrayOfString[i];
        if ((str != null) && (str.hashCode() != "".hashCode()))
        {
          Iterator localIterator = paramContext.iterator();
          while (localIterator.hasNext()) {
            if (str.equals(((ApplicationInfo)localIterator.next()).packageName)) {
              ((ArrayList)localObject).add(str);
            }
          }
        }
        i += 1;
      }
      return (String[])((ArrayList)localObject).toArray(new String[0]);
    }
    if (paramContext == null) {
      Log.i("IntentHelper", "Failed to get installed applications.");
    }
    if (paramArrayOfString == null) {
      Log.i("IntentHelper", "Null pointer to an array of target packages.");
    }
    return EMPTY_STRING_ARRAY;
  }
  
  private static void setIntentForStore(Intent paramIntent)
  {
    paramIntent.addFlags(268435456);
    paramIntent.addFlags(2097152);
    paramIntent.addFlags(67108864);
  }
}
