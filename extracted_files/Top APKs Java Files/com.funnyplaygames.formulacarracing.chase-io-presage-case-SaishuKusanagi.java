package io.presage.case;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import for.ChangKoehan;
import for.LeonaHeidern;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

public class SaishuKusanagi
{
  public SaishuKusanagi() {}
  
  public static String a()
    throws IOException
  {
    ChangKoehan localChangKoehan = LeonaHeidern.a(LeonaHeidern.a(new File("/sys/class/net/wlan0/address")));
    String str = localChangKoehan.o();
    if (localChangKoehan != null) {
      localChangKoehan.close();
    }
    return str;
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      String str = a();
      if ((str != null) && (!str.isEmpty()))
      {
        str = UUID.nameUUIDFromBytes(str.getBytes()).toString();
        return str;
      }
    }
    catch (Exception localException)
    {
      ChoiBounge.a("generateFakeAAID", "mac", localException);
      ApplicationInfo localApplicationInfo;
      try
      {
        localApplicationInfo = b(paramContext);
        if (localApplicationInfo == null) {
          return "00000000-0000-0000-0000-000000000000";
        }
      }
      catch (Exception paramContext)
      {
        ChoiBounge.a("generateFakeAAID", "firstSystemApp", paramContext);
        return "00000000-0000-0000-0000-000000000000";
      }
      try
      {
        long l = paramContext.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 128).firstInstallTime;
        return UUID.nameUUIDFromBytes((l + "").getBytes()).toString();
      }
      catch (Exception paramContext)
      {
        ChoiBounge.a("generateFakeAAID", "firstInstallTime", paramContext);
      }
    }
    return "00000000-0000-0000-0000-000000000000";
  }
  
  public static String a(String[] paramArrayOfString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    int i = 0;
    if (i < paramArrayOfString.length)
    {
      if (i == 0) {
        localStringBuilder.append(paramArrayOfString[i]);
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append("," + paramArrayOfString[i]);
      }
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static boolean a(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    int j = paramString.length();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label43;
      }
      int k = paramString.charAt(i);
      if ((k <= 31) || (k >= 127)) {
        break;
      }
      i += 1;
    }
    label43:
    return true;
  }
  
  private static ApplicationInfo b(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getInstalledApplications(128);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramContext.iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (((localApplicationInfo.flags & 0x1) != 0) && (localApplicationInfo.packageName != null)) {
        localArrayList.add(localApplicationInfo);
      }
    }
    if (paramContext.size() == 0) {
      return null;
    }
    Collections.sort(localArrayList, new Comparator()
    {
      public int a(ApplicationInfo paramAnonymousApplicationInfo1, ApplicationInfo paramAnonymousApplicationInfo2)
      {
        return paramAnonymousApplicationInfo1.packageName.compareTo(paramAnonymousApplicationInfo2.packageName);
      }
    });
    return (ApplicationInfo)localArrayList.get(0);
  }
  
  public static String b()
  {
    try
    {
      Object localObject = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime();
      String str = new SimpleDateFormat("Z").format((Date)localObject);
      localObject = str;
      if (!a(str)) {
        localObject = "";
      }
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
}
