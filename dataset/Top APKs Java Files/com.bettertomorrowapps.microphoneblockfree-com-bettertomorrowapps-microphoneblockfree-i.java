package com.bettertomorrowapps.microphoneblockfree;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.NotificationCompat.Builder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class i
{
  public static NotificationCompat.Builder a;
  public static String b = "ca-app-pub-7394879093093087/5318564852";
  public static String c = "ds/CV9pBlr4YRhwPZS9xm5yWOvOq5xlRFjVHnk48TaGeU8p+QwTEp565J8Zclh5NvE/x2FNR8nYBYb5L4R/8z46rSQIDAQAB";
  private static Integer d = Integer.valueOf(20);
  private static Integer e = Integer.valueOf(22);
  private static Long f = Long.valueOf(864000000L);
  
  public static Boolean a(SharedPreferences paramSharedPreferences)
  {
    int i = Calendar.getInstance().get(11);
    if (paramSharedPreferences.getBoolean("l_pro", false)) {
      return Boolean.valueOf(false);
    }
    if ((i >= d.intValue()) && (i < e.intValue()) && (System.currentTimeMillis() > paramSharedPreferences.getLong("installedDate", 0L) + f.longValue())) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public static Long a(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    try
    {
      long l = localSimpleDateFormat.parse(paramString).getTime();
      return Long.valueOf(l);
    }
    catch (ParseException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static List<HashMap<String, String>> a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(4096).iterator();
    int i = 0;
    int m = 0;
    int k = 0;
    int j = 0;
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        paramContext = paramContext.getSharedPreferences("blockMicrophone", 0).edit();
        paramContext.putInt("numberOfMicrophoneApps", m + k);
        paramContext.putInt("numberOfMicrophoneInternetApps", k);
        paramContext.putInt("numberOfMicrophoneCallApps", i);
        paramContext.putInt("numberOfApps", j);
        paramContext.commit();
        return localArrayList;
      }
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      j += 1;
      String[] arrayOfString = localPackageInfo.requestedPermissions;
      HashMap localHashMap = new HashMap();
      if (arrayOfString != null)
      {
        Boolean localBoolean2 = Boolean.valueOf(false);
        Boolean localBoolean3 = Boolean.valueOf(false);
        Boolean localBoolean1 = Boolean.valueOf(false);
        int n = 0;
        label173:
        if (n >= arrayOfString.length)
        {
          if ((!localBoolean2.booleanValue()) || (!localBoolean1.booleanValue())) {
            break label442;
          }
          i += 1;
        }
        label442:
        for (;;)
        {
          if ((localBoolean2.booleanValue()) && (localBoolean3.booleanValue()))
          {
            localHashMap.put("nameOfApp", localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo));
            localHashMap.put("description", localPackageInfo.packageName);
            localHashMap.put("isInternet", paramContext.getString(2131427487));
            localArrayList.add(localHashMap);
            k += 1;
            break;
            if (arrayOfString[n].equals("android.permission.INTERNET")) {
              localBoolean3 = Boolean.valueOf(true);
            }
            if (arrayOfString[n].equals("android.permission.RECORD_AUDIO")) {
              localBoolean2 = Boolean.valueOf(true);
            }
            if (arrayOfString[n].equals("android.permission.READ_PHONE_STATE")) {
              localBoolean1 = Boolean.valueOf(true);
            }
            n += 1;
            break label173;
          }
          if (localBoolean2.booleanValue())
          {
            localHashMap.put("nameOfApp", localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo));
            localHashMap.put("description", localPackageInfo.packageName);
            localHashMap.put("isInternet", " ");
            localArrayList.add(localHashMap);
            m += 1;
            break;
          }
          break;
        }
      }
    }
  }
  
  public static Boolean b(SharedPreferences paramSharedPreferences)
  {
    if (System.currentTimeMillis() < paramSharedPreferences.getLong("installedDate", 0L) + Long.valueOf(432000000L).longValue()) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public static Boolean c(SharedPreferences paramSharedPreferences)
  {
    boolean bool = false;
    Calendar.getInstance().get(11);
    if (paramSharedPreferences.getBoolean("l_pro", false)) {
      return Boolean.valueOf(true);
    }
    if (System.currentTimeMillis() > paramSharedPreferences.getLong("installedDate", 0L) + f.longValue()) {}
    for (;;)
    {
      return Boolean.valueOf(bool);
      bool = true;
    }
  }
}
