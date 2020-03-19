package com.djit.android.sdk.end;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Patterns;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class z
{
  static com.djit.android.sdk.end.a.a.b a(Context paramContext)
  {
    return new com.djit.android.sdk.end.a.a.b((TelephonyManager)paramContext.getSystemService("phone"));
  }
  
  static String a()
  {
    return TimeZone.getDefault().getID();
  }
  
  private static String a(PackageManager paramPackageManager, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      paramPackageManager = paramPackageManager.getInstallerPackageName(paramString);
      return paramPackageManager;
    }
    catch (IllegalArgumentException paramPackageManager) {}
    return null;
  }
  
  static String a(String paramString)
  {
    if (Patterns.EMAIL_ADDRESS.matcher(paramString).matches()) {
      return paramString;
    }
    if (paramString.contains("@"))
    {
      paramString = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(paramString);
      while (paramString.find())
      {
        String str = paramString.group();
        if (Patterns.EMAIL_ADDRESS.matcher(str).matches()) {
          return str;
        }
      }
    }
    return null;
  }
  
  static List<String> a(Context paramContext, int paramInt)
  {
    ac.a(paramContext);
    ArrayList localArrayList = new ArrayList();
    Object localObject = paramContext.getPackageManager().getInstalledPackages(4096).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo != null) && (localPackageInfo.packageName != null) && (localPackageInfo.packageName.equals(paramContext.getPackageName())))
      {
        localObject = localPackageInfo.requestedPermissions;
        if (localObject != null)
        {
          int j = localObject.length;
          int i = 0;
          while (i < j)
          {
            localPackageInfo = localObject[i];
            if (localPackageInfo != null) {
              if ((Build.VERSION.SDK_INT >= 23) && (paramInt == 0))
              {
                if (android.support.v4.a.b.a(paramContext, localPackageInfo) == 0) {
                  localArrayList.add(localPackageInfo);
                }
              }
              else {
                localArrayList.add(localPackageInfo);
              }
            }
            i += 1;
          }
        }
        return localArrayList;
      }
    }
    return localArrayList;
  }
  
  static List<b> a(PackageManager paramPackageManager, SimpleDateFormat paramSimpleDateFormat)
  {
    if (paramPackageManager == null) {
      return new ArrayList();
    }
    Object localObject1 = null;
    try
    {
      localObject2 = paramPackageManager.getInstalledPackages(8320);
      localObject1 = localObject2;
    }
    catch (RuntimeException localRuntimeException)
    {
      Object localObject2;
      int i;
      for (;;) {}
    }
    if ((localObject1 != null) && (!localObject1.isEmpty()))
    {
      localObject2 = new ArrayList();
      i = localObject1.size() - 1;
      while (i >= 0)
      {
        PackageInfo localPackageInfo = (PackageInfo)localObject1.get(i);
        if (paramPackageManager.getLaunchIntentForPackage(localPackageInfo.packageName) == null) {
          localObject1.remove(localPackageInfo);
        }
        if (((localPackageInfo.applicationInfo.flags & 0x1) != 0) || ((localPackageInfo.applicationInfo.flags & 0x80) != 0))
        {
          ((List)localObject2).add(localPackageInfo);
          localObject1.remove(localPackageInfo);
        }
        i -= 1;
      }
      return a(paramPackageManager, (List)localObject2, localObject1, paramSimpleDateFormat);
    }
    return new ArrayList();
  }
  
  private static List<b> a(PackageManager paramPackageManager, List<PackageInfo> paramList1, List<PackageInfo> paramList2, SimpleDateFormat paramSimpleDateFormat)
  {
    paramList1 = new ArrayList();
    paramList2 = paramList2.iterator();
    while (paramList2.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramList2.next();
      paramList1.add(new b("user_install", localPackageInfo.packageName, paramSimpleDateFormat, localPackageInfo.firstInstallTime, localPackageInfo.lastUpdateTime, a(paramPackageManager, localPackageInfo.packageName)));
    }
    return paramList1;
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    ac.a(paramContext);
    if (Build.VERSION.SDK_INT >= 23) {
      return android.support.v4.a.b.a(paramContext, paramString) == 0;
    }
    return a(paramContext, 1).contains(paramString);
  }
  
  @SuppressLint({"HardwareIds"})
  static String b(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  static Set<aa> c(Context paramContext)
  {
    if (!a(paramContext, "android.permission.GET_ACCOUNTS")) {
      return null;
    }
    paramContext = AccountManager.get(paramContext).getAccounts();
    if (paramContext.length == 0) {
      return null;
    }
    HashSet localHashSet = new HashSet();
    long l = System.currentTimeMillis();
    int j = paramContext.length;
    int i = 0;
    while (i < j)
    {
      String str1 = paramContext[i];
      if ((str1.name != null) && (!TextUtils.isEmpty(str1.name)))
      {
        str1 = a(str1.name);
        if (str1 != null)
        {
          String str3 = str1.replaceAll(" ", "").toLowerCase(Locale.US);
          str1 = ae.a(str3);
          String str2 = ae.c(str3);
          str3 = ae.b(str3);
          if ((str1 != null) || (str2 != null) || (str3 != null)) {
            localHashSet.add(new aa(str1, str2, str3, l));
          }
        }
      }
      i += 1;
    }
    return localHashSet;
  }
  
  public static class a
  {
    public static String a(Context paramContext)
    {
      String str = z.b(paramContext);
      paramContext = b(paramContext);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(a(str));
      localStringBuilder.append(a(paramContext));
      return a(localStringBuilder.toString());
    }
    
    public static String a(String paramString)
    {
      Object localObject = ae.b(paramString);
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        paramString = new StringBuilder();
        paramString.append("SHA256_");
        paramString.append((String)localObject);
        return paramString.toString();
      }
      localObject = ae.c(paramString);
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        paramString = new StringBuilder();
        paramString.append("SHA1___");
        paramString.append((String)localObject);
        return paramString.toString();
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("NOTHING");
      ((StringBuilder)localObject).append(paramString);
      return ((StringBuilder)localObject).toString();
    }
    
    @Deprecated
    private static String b(Context paramContext)
    {
      return ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }
  }
  
  public static class b
  {
    @SuppressLint({"HardwareIds"})
    public static String a(Context paramContext)
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      if (("02:00:00:00:00:00".equalsIgnoreCase(paramContext)) || (TextUtils.isEmpty(paramContext))) {}
      try
      {
        Object localObject1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (NetworkInterface)((Iterator)localObject1).next();
          if (("wlan0".equals(((NetworkInterface)localObject2).getDisplayName())) || ("wlan0".equals(((NetworkInterface)localObject2).getName())))
          {
            localObject2 = ((NetworkInterface)localObject2).getHardwareAddress();
            if (localObject2 != null)
            {
              localObject1 = new StringBuilder();
              int j = localObject2.length;
              int i = 0;
              while (i < j)
              {
                ((StringBuilder)localObject1).append(String.format("%02X:", new Object[] { Byte.valueOf(localObject2[i]) }));
                i += 1;
              }
              if (((StringBuilder)localObject1).length() > 0) {
                ((StringBuilder)localObject1).deleteCharAt(((StringBuilder)localObject1).length() - 1);
              }
              localObject1 = ((StringBuilder)localObject1).toString();
              return localObject1;
            }
          }
        }
        return paramContext;
      }
      catch (SocketException localSocketException) {}
      return paramContext;
    }
    
    public static String a(String paramString)
    {
      Object localObject = ae.b(paramString);
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        paramString = new StringBuilder();
        paramString.append("SHA256_");
        paramString.append((String)localObject);
        return paramString.toString();
      }
      localObject = ae.c(paramString);
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        paramString = new StringBuilder();
        paramString.append("SHA1___");
        paramString.append((String)localObject);
        return paramString.toString();
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("NOTHING");
      ((StringBuilder)localObject).append(paramString);
      return ((StringBuilder)localObject).toString();
    }
    
    public static String b(Context paramContext)
    {
      String str = z.b(paramContext);
      paramContext = a(paramContext);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(a(str));
      localStringBuilder.append(a(paramContext));
      return a(localStringBuilder.toString());
    }
  }
}
