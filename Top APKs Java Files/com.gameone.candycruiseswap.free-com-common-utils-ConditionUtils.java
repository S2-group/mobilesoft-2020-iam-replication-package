package com.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConditionUtils
{
  private static final String CDMA2000 = "CDMA2000";
  private static final String TD_SCDMA = "TD-SCDMA";
  private static final String WCDMA = "WCDMA";
  private static String age;
  private static String areacode;
  private static String city = "";
  private static Context context;
  private static int gameLevel;
  private static List<String> installPackageList = new ArrayList();
  private static String ip;
  private static String ipfeature = "";
  private static String language;
  private static String sex;
  private static String tags;
  
  static
  {
    ip = "";
    areacode = "";
    language = "";
    sex = "";
    age = "";
    tags = "";
  }
  
  public ConditionUtils() {}
  
  private static boolean age(Map<String, String> paramMap)
  {
    if (((!paramMap.containsKey("age_in")) && (!paramMap.containsKey("age_out"))) || (TextUtils.isEmpty(age))) {
      return true;
    }
    return stringType(paramMap, "age", age);
  }
  
  private static boolean areacode(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("areacode_in")) || (paramMap.containsKey("areacode_out"))) {
      return stringType(paramMap, "areacode", areacode);
    }
    return true;
  }
  
  private static boolean base(Map<String, String> paramMap)
  {
    return (vcode(paramMap)) && (oscode(paramMap)) && (language(paramMap)) && (date(paramMap)) && (time(paramMap)) && (geo(paramMap)) && (areacode(paramMap)) && (net(paramMap)) && (devicetype(paramMap)) && (dpi(paramMap)) && (level(paramMap)) && (devicemodel(paramMap)) && (ip(paramMap)) && (ipfeature(paramMap)) && (pkgname(paramMap)) && (age(paramMap)) && (sex(paramMap)) && (tag(paramMap));
  }
  
  public static boolean check(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return true;
    }
    paramString = getListFromCondition(paramString).iterator();
    while (paramString.hasNext()) {
      if (base(getMapFromCondition((String)paramString.next()))) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean date(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("date_in")) || (paramMap.containsKey("date_out"))) {
      return intType(paramMap, "date", Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date())));
    }
    return true;
  }
  
  private static boolean devicemodel(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("devicemodel_in")) || (paramMap.containsKey("devicemodel_out"))) {
      return stringFilterType(paramMap, "devicemodel", Build.MODEL);
    }
    return true;
  }
  
  private static boolean devicetype(Map<String, String> paramMap)
  {
    int i;
    if ((paramMap.containsKey("devicetype_in")) || (paramMap.containsKey("devicetype_out")))
    {
      if (!isPad()) {
        break label63;
      }
      i = 2;
      if (!paramMap.containsKey("devicetype_in")) {
        break label70;
      }
      if ((!getInt(paramMap, i, "devicetype_in")) && (!getInt(paramMap, 0, "devicetype_in"))) {
        break label68;
      }
    }
    label63:
    label68:
    label70:
    while ((!paramMap.containsKey("devicetype_out")) || ((!getInt(paramMap, 0, "devicetype_out")) && (!getInt(paramMap, i, "devicetype_out"))))
    {
      return true;
      i = 1;
      break;
      return false;
    }
    return false;
  }
  
  private static boolean dpi(Map<String, String> paramMap)
  {
    int j;
    int k;
    String[] arrayOfString;
    int m;
    int i;
    int n;
    int i1;
    if ((paramMap.containsKey("resolution_in")) || (paramMap.containsKey("resolution_out")))
    {
      j = getWidthPixels();
      k = getHeightPixels();
      if (!paramMap.containsKey("resolution_in")) {
        break label166;
      }
      arrayOfString = ((String)paramMap.get("resolution_in")).split(",");
      if ((arrayOfString == null) || (arrayOfString.length <= 0)) {
        break label166;
      }
      m = arrayOfString.length;
      i = 0;
      if (i >= m) {
        break label164;
      }
      paramMap = arrayOfString[i].split("x");
      if ((paramMap == null) || (paramMap.length != 2)) {
        break label157;
      }
      n = Integer.parseInt(paramMap[0]);
      i1 = Integer.parseInt(paramMap[1]);
      if ((j + 100 <= n) || (j - 100 >= n) || (k + 100 <= i1) || (k - 100 >= i1)) {
        break label157;
      }
    }
    for (;;)
    {
      return true;
      label157:
      i += 1;
      break;
      label164:
      return false;
      label166:
      if (paramMap.containsKey("resolution_out"))
      {
        paramMap = ((String)paramMap.get("resolution_out")).split(",");
        if ((paramMap != null) && (paramMap.length > 0))
        {
          m = paramMap.length;
          i = 0;
          while (i < m)
          {
            arrayOfString = paramMap[i].split("x");
            if ((arrayOfString != null) && (arrayOfString.length == 2))
            {
              n = Integer.parseInt(arrayOfString[0]);
              i1 = Integer.parseInt(arrayOfString[1]);
              if ((j + 100 > n) && (j - 100 < n) && (k + 100 > i1) && (k - 100 < i1)) {
                return false;
              }
            }
            i += 1;
          }
        }
      }
    }
  }
  
  private static boolean filterPkgname(Map<String, String> paramMap, String paramString)
  {
    boolean bool2 = false;
    paramMap = ((String)paramMap.get(paramString)).toLowerCase().split(",");
    int m = paramMap.length;
    int j = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (j < m)
      {
        paramString = paramMap[j].split("\\&");
        int i = 0;
        int n = paramString.length;
        int k = 0;
        while (k < n)
        {
          Object localObject = paramString[k];
          if (installPackageList.contains(localObject))
          {
            i = 1;
            k += 1;
          }
          else
          {
            i = 0;
          }
        }
        if (i != 0) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      j += 1;
    }
  }
  
  private static boolean filterString(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    paramMap = ((String)paramMap.get(paramString2)).toLowerCase();
    paramString1 = paramString1.toLowerCase();
    paramMap = paramMap.split(",");
    paramString1 = paramString1.split(",");
    int k = paramMap.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      int m;
      int j;
      if (i < k)
      {
        paramString2 = paramMap[i];
        m = paramString1.length;
        j = 0;
      }
      while (j < m)
      {
        CharSequence localCharSequence = paramString1[j];
        if ((!TextUtils.isEmpty(localCharSequence)) && (localCharSequence.contains(paramString2)))
        {
          bool1 = true;
          return bool1;
        }
        j += 1;
      }
      i += 1;
    }
  }
  
  private static boolean geo(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("geo_in")) || (paramMap.containsKey("geo_out"))) {
      return stringType(paramMap, "geo", city);
    }
    return true;
  }
  
  public static int getHeightPixels()
  {
    return context.getResources().getDisplayMetrics().heightPixels;
  }
  
  public static List<String> getInstalledPackages(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext()) {
        localArrayList.add(((PackageInfo)paramContext.next()).packageName);
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static boolean getInt(Map<String, String> paramMap, int paramInt, String paramString)
  {
    paramMap = ((String)paramMap.get(paramString)).split(",");
    int j = paramMap.length;
    int i = 0;
    while (i < j)
    {
      paramString = paramMap[i];
      int k;
      if (paramString.contains("-"))
      {
        paramString = paramString.split("-");
        try
        {
          k = Integer.parseInt(paramString[0]);
          int m = Integer.parseInt(paramString[1]);
          if ((paramInt >= k) && (paramInt <= m)) {
            label78:
            return true;
          }
        }
        catch (Exception paramMap)
        {
          return true;
        }
      }
      try
      {
        k = Integer.parseInt(paramString);
        if (paramInt == k) {
          break label78;
        }
        i += 1;
      }
      catch (Exception paramMap)
      {
        return true;
      }
    }
    return false;
  }
  
  private static boolean getIntByVTime(Map<String, String> paramMap, int paramInt, String paramString)
  {
    paramMap = ((String)paramMap.get(paramString)).split(",");
    int j = paramMap.length;
    int i = 0;
    while (i < j)
    {
      paramString = paramMap[i];
      int k;
      if (paramString.contains("-"))
      {
        paramString = paramString.split("-");
        try
        {
          k = Integer.parseInt(paramString[0]);
          int m = Integer.parseInt(paramString[1]);
          if ((k <= paramInt) && (m >= paramInt)) {
            label78:
            return true;
          }
        }
        catch (Exception paramMap)
        {
          return true;
        }
      }
      try
      {
        k = Integer.parseInt(paramString);
        if (paramInt < k) {
          break label78;
        }
        i += 1;
      }
      catch (Exception paramMap)
      {
        return true;
      }
    }
    return false;
  }
  
  private static List<String> getListFromCondition(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = paramString.split("\\|\\|");
    int j = paramString.length;
    int i = 0;
    if (i < j)
    {
      CharSequence localCharSequence = paramString[i];
      if (TextUtils.isEmpty(localCharSequence)) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(localCharSequence);
      }
    }
    return localArrayList;
  }
  
  private static Map<String, String> getMapFromCondition(String paramString)
  {
    HashMap localHashMap = new HashMap();
    paramString = paramString.split("\\$\\$");
    int j = paramString.length;
    int i = 0;
    if (i < j)
    {
      CharSequence localCharSequence = paramString[i];
      if (TextUtils.isEmpty(localCharSequence)) {}
      for (;;)
      {
        i += 1;
        break;
        int k = localCharSequence.indexOf(":");
        if ((k > 0) && (k < localCharSequence.length() - 1)) {
          localHashMap.put(localCharSequence.substring(0, k), localCharSequence.substring(k + 1));
        }
      }
    }
    return localHashMap;
  }
  
  public static int getNetType(Context paramContext)
  {
    if (!hasPermission(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {}
    NetworkInfo localNetworkInfo1;
    Object localObject;
    do
    {
      NetworkInfo localNetworkInfo2;
      do
      {
        do
        {
          do
          {
            return 0;
            paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
          } while (paramContext == null);
          localNetworkInfo1 = paramContext.getActiveNetworkInfo();
        } while ((localNetworkInfo1 == null) || (!localNetworkInfo1.isAvailable()));
        localObject = paramContext.getNetworkInfo(1);
        if (localObject != null)
        {
          localObject = ((NetworkInfo)localObject).getState();
          if ((localObject != null) && ((localObject == NetworkInfo.State.CONNECTED) || (localObject == NetworkInfo.State.CONNECTING))) {
            return 1;
          }
        }
        localNetworkInfo2 = paramContext.getNetworkInfo(0);
      } while (localNetworkInfo2 == null);
      localObject = localNetworkInfo2.getState();
      paramContext = null;
      if (Build.VERSION.SDK_INT >= 3) {
        paramContext = localNetworkInfo2.getSubtypeName();
      }
    } while ((localObject == null) || ((localObject != NetworkInfo.State.CONNECTED) && (localObject != NetworkInfo.State.CONNECTING)) || (Build.VERSION.SDK_INT < 3));
    switch (localNetworkInfo1.getSubtype())
    {
    default: 
      if (("TD-SCDMA".equalsIgnoreCase(paramContext)) || ("WCDMA".equalsIgnoreCase(paramContext)) || ("CDMA2000".equalsIgnoreCase(paramContext))) {
        return 3;
      }
      break;
    case 1: 
    case 2: 
    case 4: 
    case 7: 
    case 11: 
      return 2;
    case 3: 
    case 5: 
    case 6: 
    case 8: 
    case 9: 
    case 10: 
    case 12: 
    case 14: 
    case 15: 
      return 3;
    case 13: 
      return 4;
    }
    return 5;
  }
  
  public static String getNetType()
  {
    switch (getNetType(context.getApplicationContext()))
    {
    default: 
      return "";
    case 1: 
      return "wifi";
    case 2: 
      return "2g";
    case 3: 
      return "3g";
    case 4: 
      return "4g";
    }
    return "5g";
  }
  
  private static boolean getString(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    paramMap = ((String)paramMap.get(paramString2)).split(",");
    int j = paramMap.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramString1.equals(paramMap[i])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static int getVersionCode()
  {
    try
    {
      int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      Log.d("", "Get Version Code Error!!!");
    }
    return -1;
  }
  
  public static int getWidthPixels()
  {
    return context.getResources().getDisplayMetrics().widthPixels;
  }
  
  public static boolean hasPermission(Context paramContext, String paramString)
  {
    if (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0) {}
    for (boolean bool = true;; bool = false)
    {
      Log.d("", paramString + " - permission: " + bool);
      return bool;
    }
  }
  
  public static void init(Context paramContext)
  {
    context = paramContext;
    installPackageList = getInstalledPackages(paramContext);
  }
  
  private static boolean intType(Map<String, String> paramMap, String paramString, int paramInt)
  {
    boolean bool2 = true;
    String str = paramString + "_in";
    boolean bool1;
    if (paramMap.containsKey(str)) {
      bool1 = getInt(paramMap, paramInt, str);
    }
    do
    {
      do
      {
        return bool1;
        paramString = paramString + "_out";
        bool1 = bool2;
      } while (!paramMap.containsKey(paramString));
      bool1 = bool2;
    } while (!getInt(paramMap, paramInt, paramString));
    return false;
  }
  
  private static boolean intTypeByVTime(Map<String, String> paramMap, String paramString, int paramInt)
  {
    boolean bool2 = true;
    String str = paramString + "_in";
    boolean bool1;
    if (paramMap.containsKey(str)) {
      bool1 = getIntByVTime(paramMap, paramInt, str);
    }
    do
    {
      do
      {
        return bool1;
        paramString = paramString + "_out";
        bool1 = bool2;
      } while (!paramMap.containsKey(paramString));
      bool1 = bool2;
    } while (!getIntByVTime(paramMap, paramInt, paramString));
    return false;
  }
  
  private static boolean ip(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("ip_in")) || (paramMap.containsKey("ip_out"))) {
      return stringFilterType(paramMap, "ip", ip);
    }
    return true;
  }
  
  private static boolean ipfeature(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("ipfeature_in")) || (paramMap.containsKey("ipfeature_out"))) {
      return stringFilterType(paramMap, "ipfeature", ipfeature);
    }
    return true;
  }
  
  public static boolean isInstallPackage(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      Object localObject = null;
      try
      {
        paramString = context.getPackageManager().getPackageInfo(paramString, 0);
        if (paramString == null) {
          continue;
        }
        return true;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
          paramString = localObject;
        }
      }
    }
  }
  
  public static boolean isPad()
  {
    return (context.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  private static boolean language(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("language_in")) || (paramMap.containsKey("language_out"))) {
      return stringType(paramMap, "language", language);
    }
    return true;
  }
  
  private static boolean level(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("level_in")) || (paramMap.containsKey("level_out"))) {
      return intType(paramMap, "level", gameLevel);
    }
    return true;
  }
  
  private static boolean net(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("net_in")) || (paramMap.containsKey("net_out"))) {
      return stringType(paramMap, "net", getNetType());
    }
    return true;
  }
  
  private static boolean oscode(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("oscode_in")) || (paramMap.containsKey("oscode_out"))) {
      return intType(paramMap, "oscode", Build.VERSION.SDK_INT);
    }
    return true;
  }
  
  private static boolean pkgFilterType(Map<String, String> paramMap, String paramString)
  {
    String str = paramString + "_in";
    if (paramMap.containsKey(str)) {
      if (!TextUtils.isEmpty((String)paramMap.get(str))) {}
    }
    do
    {
      return true;
      return filterPkgname(paramMap, str);
      paramString = paramString + "_out";
    } while ((!paramMap.containsKey(paramString)) || (TextUtils.isEmpty((String)paramMap.get(paramString))) || (!filterPkgname(paramMap, paramString)));
    return false;
  }
  
  private static boolean pkgname(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("pkgname_in")) || (paramMap.containsKey("pkgname_out"))) {
      return pkgFilterType(paramMap, "pkgname");
    }
    return true;
  }
  
  public static void setAge(String paramString)
  {
    age = paramString;
  }
  
  public static void setAreacode(String paramString)
  {
    areacode = paramString;
  }
  
  public static void setCity(String paramString)
  {
    city = paramString;
  }
  
  public static void setGameLevel(int paramInt)
  {
    gameLevel = paramInt;
  }
  
  public static void setIp(String paramString)
  {
    ip = paramString;
  }
  
  public static void setIpfeature(String paramString)
  {
    ipfeature = paramString;
  }
  
  public static void setLanguage(String paramString)
  {
    language = paramString;
  }
  
  public static void setSex(String paramString)
  {
    sex = paramString;
  }
  
  public static void setTags(String paramString)
  {
    tags = paramString;
  }
  
  private static boolean sex(Map<String, String> paramMap)
  {
    if (((!paramMap.containsKey("sex_in")) && (!paramMap.containsKey("sex_out"))) || (TextUtils.isEmpty(sex))) {
      return true;
    }
    return stringType(paramMap, "sex", sex);
  }
  
  private static boolean stringFilterType(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    boolean bool2 = true;
    String str = paramString1 + "_in";
    boolean bool1;
    if (paramMap.containsKey(str)) {
      if (TextUtils.isEmpty(paramString2)) {
        bool1 = "ip".equals(paramString1);
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (TextUtils.isEmpty((String)paramMap.get(str)));
            return filterString(paramMap, paramString2, str);
            paramString1 = paramString1 + "_out";
            bool1 = bool2;
          } while (!paramMap.containsKey(paramString1));
          bool1 = bool2;
        } while (TextUtils.isEmpty(paramString2));
        bool1 = bool2;
      } while (TextUtils.isEmpty((String)paramMap.get(paramString1)));
      bool1 = bool2;
    } while (!filterString(paramMap, paramString2, paramString1));
    return false;
  }
  
  private static boolean stringType(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    boolean bool2 = true;
    String str = paramString1 + "_in";
    boolean bool1;
    if (paramMap.containsKey(str)) {
      if (TextUtils.isEmpty(paramString2)) {
        bool1 = false;
      }
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          return getString(paramMap, paramString2, str);
          paramString1 = paramString1 + "_out";
          bool1 = bool2;
        } while (!paramMap.containsKey(paramString1));
        bool1 = bool2;
      } while (TextUtils.isEmpty(paramString2));
      bool1 = bool2;
    } while (!getString(paramMap, paramString2, paramString1));
    return false;
  }
  
  private static boolean tag(Map<String, String> paramMap)
  {
    if (((!paramMap.containsKey("tag_in")) && (!paramMap.containsKey("tag_out"))) || (TextUtils.isEmpty(tags))) {
      return true;
    }
    return stringFilterType(paramMap, "tag", tags);
  }
  
  private static boolean time(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("time_in")) || (paramMap.containsKey("time_out"))) {
      return intType(paramMap, "time", Calendar.getInstance().get(11) + 1);
    }
    return true;
  }
  
  private static boolean vcode(Map<String, String> paramMap)
  {
    if ((paramMap.containsKey("vcode_in")) || (paramMap.containsKey("vcode_out"))) {
      return intType(paramMap, "vcode", getVersionCode());
    }
    return true;
  }
}
