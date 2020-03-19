package cn.jiguang.a.a.c;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import cn.jiguang.d.d.w;
import cn.jiguang.e.c;
import com.google.a.a.a.a.a.a;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class k
{
  private static final String a = "k";
  
  public k() {}
  
  public static String a(String paramString, int paramInt)
  {
    String str = paramString;
    if (paramString != null) {
      paramString = Pattern.compile("\n|\r|\r\n|\n\r|\t").matcher(paramString).replaceAll("");
    }
    try
    {
      byte[] arrayOfByte = paramString.getBytes();
      str = paramString;
      if (arrayOfByte.length > 30) {
        str = paramString.substring(0, new String(arrayOfByte, 0, 30, "UTF-8").length());
      }
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return paramString;
  }
  
  private static Set<String> a(ActivityManager paramActivityManager)
  {
    HashSet localHashSet = new HashSet();
    paramActivityManager = paramActivityManager.getRunningAppProcesses().iterator();
    while (paramActivityManager.hasNext()) {
      Collections.addAll(localHashSet, ((ActivityManager.RunningAppProcessInfo)paramActivityManager.next()).pkgList);
    }
    return localHashSet;
  }
  
  private static JSONArray a(ActivityManager paramActivityManager, PackageManager paramPackageManager)
  {
    localJSONArray1 = new JSONArray();
    try
    {
      Object localObject3 = a(paramActivityManager);
      Object localObject1 = paramPackageManager.getInstalledApplications(8192);
      List localList = paramActivityManager.getRunningServices(200);
      Collections.sort((List)localObject1, new ApplicationInfo.DisplayNameComparator(paramPackageManager));
      long l1 = SystemClock.elapsedRealtime();
      localObject1 = ((List)localObject1).iterator();
      paramActivityManager = (ActivityManager)localObject3;
      Object localObject2;
      while (((Iterator)localObject1).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject1).next();
        String str = a(localApplicationInfo.loadLabel(paramPackageManager).toString(), 30);
        if (paramActivityManager.contains(localApplicationInfo.packageName))
        {
          JSONObject localJSONObject1 = new JSONObject();
          JSONArray localJSONArray2 = new JSONArray();
          localObject3 = localList.iterator();
          while (((Iterator)localObject3).hasNext())
          {
            ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)((Iterator)localObject3).next();
            if (localRunningServiceInfo.service.getPackageName().equals(localApplicationInfo.packageName))
            {
              JSONObject localJSONObject2 = new JSONObject();
              int i = Math.round((float)((l1 - localRunningServiceInfo.activeSince) / 1000L));
              long l2 = i;
              try
              {
                localJSONObject2.put("class_name", localRunningServiceInfo.service.getShortClassName());
                localJSONObject2.put("live_seconds", l2);
                localJSONArray2.put(localJSONObject2);
              }
              catch (JSONException localJSONException2)
              {
                a.h(localJSONException2);
              }
            }
          }
          localObject3 = localObject1;
          try
          {
            localJSONObject1.put("app_name", str);
            localJSONObject1.put("pkg_name", localApplicationInfo.packageName);
            localJSONObject1.put("service_list", localJSONArray2);
            localJSONArray1.put(localJSONObject1);
            localObject1 = paramActivityManager;
            paramActivityManager = (ActivityManager)localObject3;
          }
          catch (JSONException localJSONException1)
          {
            a.h(localJSONException1);
            localObject2 = paramActivityManager;
            paramActivityManager = (ActivityManager)localObject3;
          }
        }
        else
        {
          localObject3 = paramActivityManager;
          paramActivityManager = (ActivityManager)localObject2;
          localObject2 = localObject3;
        }
        localObject3 = paramActivityManager;
        paramActivityManager = (ActivityManager)localObject2;
        localObject2 = localObject3;
      }
      return localJSONArray1;
    }
    catch (Throwable paramActivityManager)
    {
      paramPackageManager = a;
      localObject2 = new StringBuilder("getRunningApps error:");
      ((StringBuilder)localObject2).append(paramActivityManager.getMessage());
      c.a(paramPackageManager, ((StringBuilder)localObject2).toString());
    }
  }
  
  public static void a(Context paramContext)
  {
    JSONArray localJSONArray = a((ActivityManager)paramContext.getSystemService("activity"), paramContext.getPackageManager());
    if (localJSONArray != null)
    {
      if (localJSONArray.length() == 0) {
        return;
      }
      w.a(paramContext, localJSONArray);
    }
  }
}
