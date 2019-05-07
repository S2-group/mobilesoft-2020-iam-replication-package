package com.meituan.uuid;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class e
{
  public static ChangeQuickRedirect a;
  public static List<String> b;
  public static final String c;
  private static final List<String> d = Arrays.asList(new String[] { "Swx5MhmpsUWrzFF8cxvhVsr/9+LU12ie1BlGJO01/Fs=", "Swx5MhmpsUVxI5kRwsr9uzh8GVShuc4R", "9KS4FZP/RfAQ/zKFPcCF4A==", "9KS4FZP/RfAx+n+5o26Pys0KrjexB3XU", "sa0WpZpDt+uchVVhi36Os9hUbqWqhzQk4fRwhChyT48=", "sa0WpZpDt+uchVVhi36Os489I/EbaX2L" });
  
  static
  {
    b = new ArrayList();
    c = j.c("Swx5MhmpsUWrzFF8cxvhVr5m5JEuFJXZ");
    b.add(c);
    if (d != null)
    {
      Iterator localIterator = d.iterator();
      while (localIterator.hasNext())
      {
        String str = j.c((String)localIterator.next());
        if (!TextUtils.isEmpty(str)) {
          b.add(str);
        }
      }
    }
  }
  
  public e() {}
  
  public static List<String> a(Context paramContext)
  {
    localObject1 = new Object[1];
    localObject1[0] = paramContext;
    Object localObject2 = a;
    if (PatchProxy.isSupport((Object[])localObject1, null, (ChangeQuickRedirect)localObject2, true, "a59ee4c1172c248c263642f5c2c8b0c1", 4611686018427387904L)) {
      return (List)PatchProxy.accessDispatch((Object[])localObject1, null, (ChangeQuickRedirect)localObject2, true, "a59ee4c1172c248c263642f5c2c8b0c1");
    }
    localObject1 = new ArrayList();
    try
    {
      localObject2 = paramContext.getPackageManager();
      Iterator localIterator = ((PackageManager)localObject2).getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        String str = ((PackageInfo)localIterator.next()).packageName;
        if (((TextUtils.equals(str, paramContext.getPackageName())) || (f.b(paramContext, str))) && (a(((PackageManager)localObject2).getPackageInfo(str, 8), str))) {
          ((List)localObject1).add(str);
        }
      }
      return localObject1;
    }
    catch (Exception paramContext)
    {
      j.b().a().a(paramContext);
    }
  }
  
  public static List<String> a(Context paramContext, List<String> paramList)
  {
    localObject1 = new Object[2];
    localObject1[0] = paramContext;
    localObject1[1] = paramList;
    Object localObject2 = a;
    if (PatchProxy.isSupport((Object[])localObject1, null, (ChangeQuickRedirect)localObject2, true, "09a1c5b8e4b46c3308005d5f84a96fe3", 4611686018427387904L)) {
      return (List)PatchProxy.accessDispatch((Object[])localObject1, null, (ChangeQuickRedirect)localObject2, true, "09a1c5b8e4b46c3308005d5f84a96fe3");
    }
    localObject1 = new ArrayList();
    if ((paramList != null) && (paramList.size() > 0))
    {
      localObject2 = new ArrayList();
      try
      {
        paramContext = paramContext.getPackageManager();
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          String str = (String)paramList.next();
          Bundle localBundle = paramContext.getApplicationInfo(str, 128).metaData;
          if ((localBundle != null) && (localBundle.containsKey("uuid_priority"))) {
            ((List)localObject2).add(new Pair(str, Float.valueOf(localBundle.getFloat("uuid_priority"))));
          }
        }
        Collections.sort((List)localObject2, new e.1());
        paramContext = ((List)localObject2).iterator();
        while (paramContext.hasNext()) {
          ((List)localObject1).add(((Pair)paramContext.next()).first);
        }
        return localObject1;
      }
      catch (Exception paramContext)
      {
        j.b().a().a(paramContext);
      }
    }
  }
  
  private static boolean a(PackageInfo paramPackageInfo, String paramString)
  {
    Object localObject1 = new Object[2];
    localObject1[0] = paramPackageInfo;
    localObject1[1] = paramString;
    Object localObject2 = a;
    if (PatchProxy.isSupport((Object[])localObject1, null, (ChangeQuickRedirect)localObject2, true, "eb0c4b5dd83291b4048daf80467726f9", 4611686018427387904L)) {
      return ((Boolean)PatchProxy.accessDispatch((Object[])localObject1, null, (ChangeQuickRedirect)localObject2, true, "eb0c4b5dd83291b4048daf80467726f9")).booleanValue();
    }
    if (paramPackageInfo == null) {
      return false;
    }
    paramPackageInfo = paramPackageInfo.providers;
    if (paramPackageInfo != null)
    {
      int j = paramPackageInfo.length;
      int i = 0;
      while (i < j)
      {
        localObject1 = paramPackageInfo[i];
        if (!TextUtils.isEmpty(((ProviderInfo)localObject1).authority))
        {
          localObject1 = ((ProviderInfo)localObject1).authority;
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(paramString);
          ((StringBuilder)localObject2).append(".UUIDprovider");
          if (((String)localObject1).contains(((StringBuilder)localObject2).toString())) {
            return true;
          }
        }
        i += 1;
      }
    }
    return false;
  }
}
