package io.presage.case;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import g.c;
import g.s;
import g.y;
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
    c localC = s.a(s.a(new File("/sys/class/net/wlan0/address")));
    String str = localC.q();
    if (localC != null) {
      localC.close();
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
      try
      {
        ApplicationInfo localApplicationInfo = b(paramContext);
        if (localApplicationInfo == null) {
          return "00000000-0000-0000-0000-000000000000";
        }
        try
        {
          long l = paramContext.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 128).firstInstallTime;
          paramContext = new StringBuilder();
          paramContext.append(l);
          paramContext.append("");
          return UUID.nameUUIDFromBytes(paramContext.toString().getBytes()).toString();
        }
        catch (Exception paramContext)
        {
          ChoiBounge.a("generateFakeAAID", "firstInstallTime", paramContext);
          return "00000000-0000-0000-0000-000000000000";
        }
        return "00000000-0000-0000-0000-000000000000";
      }
      catch (Exception paramContext)
      {
        ChoiBounge.a("generateFakeAAID", "firstSystemApp", paramContext);
      }
    }
  }
  
  public static String a(String[] paramArrayOfString)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("[");
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      if (i == 0)
      {
        localStringBuilder1.append(paramArrayOfString[i]);
      }
      else
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(",");
        localStringBuilder2.append(paramArrayOfString[i]);
        localStringBuilder1.append(localStringBuilder2.toString());
      }
      i += 1;
    }
    localStringBuilder1.append("]");
    return localStringBuilder1.toString();
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
    while (i < j)
    {
      int k = paramString.charAt(i);
      if (k > 31)
      {
        if (k >= 127) {
          return false;
        }
        i += 1;
      }
      else
      {
        return false;
      }
    }
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
      String str2 = new SimpleDateFormat("Z").format((Date)localObject);
      localObject = str2;
      if (!a(str2)) {
        return "";
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      String str1 = "";
      return str1;
    }
  }
}
