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
import cn.jiguang.d.d.q;
import cn.jiguang.e.d;
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
    Object localObject1 = paramPackageManager;
    localJSONArray1 = new JSONArray();
    try
    {
      Set localSet = a(paramActivityManager);
      Object localObject2 = ((PackageManager)localObject1).getInstalledApplications(8192);
      paramActivityManager = paramActivityManager.getRunningServices(200);
      Collections.sort((List)localObject2, new ApplicationInfo.DisplayNameComparator((PackageManager)localObject1));
      long l1 = SystemClock.elapsedRealtime();
      localObject1 = ((List)localObject2).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
        String str = a(((ApplicationInfo)localObject2).loadLabel(paramPackageManager).toString(), 30);
        if (localSet.contains(((ApplicationInfo)localObject2).packageName))
        {
          JSONObject localJSONObject1 = new JSONObject();
          JSONArray localJSONArray2 = new JSONArray();
          Iterator localIterator = paramActivityManager.iterator();
          while (localIterator.hasNext())
          {
            ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localIterator.next();
            if (localRunningServiceInfo.service.getPackageName().equals(((ApplicationInfo)localObject2).packageName))
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
                localJSONException2.printStackTrace();
              }
            }
          }
          try
          {
            localJSONObject1.put("app_name", str);
            localJSONObject1.put("pkg_name", ((ApplicationInfo)localObject2).packageName);
            localJSONObject1.put("service_list", localJSONArray2);
            localJSONArray1.put(localJSONObject1);
          }
          catch (JSONException localJSONException1)
          {
            localJSONException1.printStackTrace();
          }
        }
      }
      return localJSONArray1;
    }
    catch (Throwable paramActivityManager)
    {
      paramPackageManager = a;
      localObject1 = new StringBuilder("getRunningApps error:");
      ((StringBuilder)localObject1).append(paramActivityManager.getMessage());
      d.a(paramPackageManager, ((StringBuilder)localObject1).toString());
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
      q.a(paramContext, localJSONArray);
    }
  }
}
