package com.bytedance.sdk.openadsdk.i;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.core.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;

public class i
{
  private static String a(List<String> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 0;
      while (i < paramList.size())
      {
        localStringBuilder.append((String)paramList.get(i));
        if (i != paramList.size() - 1) {
          localStringBuilder.append(",");
        }
        i += 1;
      }
      return localStringBuilder.toString().trim();
    }
    return null;
  }
  
  private static List<String> a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    return Arrays.asList(paramString.split(","));
  }
  
  @MainThread
  @Nullable
  public static JSONArray a(@NonNull Context paramContext, @NonNull ExecutorService paramExecutorService)
  {
    if (!e(paramContext)) {
      return null;
    }
    paramExecutorService.submit(new Runnable()
    {
      public void run()
      {
        i.a(this.a);
      }
    });
    return c(paramContext);
  }
  
  @WorkerThread
  private static void a(Context paramContext, String paramString)
  {
    c.a(paramContext).a("install_app_string", paramString);
  }
  
  @Nullable
  private static List<String> b(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getInstalledPackages(8192);
    }
    catch (Throwable paramContext)
    {
      ArrayList localArrayList;
      for (;;) {}
    }
    paramContext = null;
    if (k.a(paramContext)) {
      return null;
    }
    localArrayList = new ArrayList();
    if (k.b(paramContext))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
        {
          String str = localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString();
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(str);
          localStringBuilder.append(":");
          localStringBuilder.append(localPackageInfo.packageName);
          localArrayList.add(localStringBuilder.toString());
        }
      }
    }
    return localArrayList;
  }
  
  @WorkerThread
  private static void b(Context paramContext, String paramString)
  {
    paramContext = c.a(paramContext);
    paramContext.a("install_app_incremental_string", paramString);
    paramContext.a("apptime", System.currentTimeMillis());
  }
  
  @Nullable
  private static JSONArray c(Context paramContext)
  {
    paramContext = c.a(paramContext).b("install_app_incremental_string", null);
    if (!TextUtils.isEmpty(paramContext)) {
      return new JSONArray(a(paramContext));
    }
    return null;
  }
  
  @WorkerThread
  private static void d(Context paramContext)
  {
    List localList1 = b(paramContext);
    if (localList1 != null)
    {
      if (localList1.isEmpty()) {
        return;
      }
      List localList2 = a(c.a(paramContext).b("install_app_string", null));
      a(paramContext, a(localList1));
      if ((localList2 != null) && (!localList2.isEmpty())) {
        localList1.removeAll(localList2);
      }
      b(paramContext, a(localList1));
      return;
    }
  }
  
  private static boolean e(Context paramContext)
  {
    long l = c.a(paramContext).b("apptime", -1L).longValue();
    return (l == -1L) || (System.currentTimeMillis() - l > 43200000L);
  }
}
