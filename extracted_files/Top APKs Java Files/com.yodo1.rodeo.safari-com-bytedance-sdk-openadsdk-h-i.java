package com.bytedance.sdk.openadsdk.h;

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
    if ((paramList == null) || (paramList.isEmpty())) {
      return null;
    }
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
    Object localObject = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    ArrayList localArrayList;
    try
    {
      paramContext = localPackageManager.getInstalledPackages(8192);
      if (k.a(paramContext)) {
        return localObject;
      }
    }
    catch (Throwable paramContext)
    {
      do
      {
        for (;;)
        {
          paramContext = null;
        }
        localArrayList = new ArrayList();
        localObject = localArrayList;
      } while (!k.b(paramContext));
      paramContext = paramContext.iterator();
    }
    for (;;)
    {
      localObject = localArrayList;
      if (!paramContext.hasNext()) {
        break;
      }
      localObject = (PackageInfo)paramContext.next();
      if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) == 0)
      {
        String str = ((PackageInfo)localObject).applicationInfo.loadLabel(localPackageManager).toString();
        localArrayList.add(str + ":" + ((PackageInfo)localObject).packageName);
      }
    }
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
    Object localObject = null;
    String str = c.a(paramContext).b("install_app_incremental_string", null);
    paramContext = localObject;
    if (!TextUtils.isEmpty(str)) {
      paramContext = new JSONArray(a(str));
    }
    return paramContext;
  }
  
  @WorkerThread
  private static void d(Context paramContext)
  {
    List localList1 = b(paramContext);
    if ((localList1 == null) || (localList1.isEmpty())) {
      return;
    }
    List localList2 = a(c.a(paramContext).b("install_app_string", null));
    a(paramContext, a(localList1));
    if ((localList2 != null) && (!localList2.isEmpty())) {
      localList1.removeAll(localList2);
    }
    b(paramContext, a(localList1));
  }
  
  private static boolean e(Context paramContext)
  {
    long l = c.a(paramContext).b("apptime", -1L).longValue();
    return (l == -1L) || (System.currentTimeMillis() - l > 43200000L);
  }
}
