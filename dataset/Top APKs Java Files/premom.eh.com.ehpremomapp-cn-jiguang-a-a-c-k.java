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
  private static final String a = k.class.getSimpleName();
  
  public k() {}
  
  public static String a(String paramString, int paramInt)
  {
    if (paramString != null)
    {
      String str = Pattern.compile("\n|\r|\r\n|\n\r|\t").matcher(paramString).replaceAll("");
      try
      {
        byte[] arrayOfByte = str.getBytes();
        paramString = str;
        if (arrayOfByte.length > 30) {
          paramString = str.substring(0, new String(arrayOfByte, 0, 30, "UTF-8").length());
        }
        return paramString;
      }
      catch (UnsupportedEncodingException paramString)
      {
        return str;
      }
    }
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
    JSONArray localJSONArray1 = new JSONArray();
    for (;;)
    {
      ApplicationInfo localApplicationInfo;
      String str;
      JSONObject localJSONObject1;
      JSONArray localJSONArray2;
      try
      {
        Set localSet = a(paramActivityManager);
        Object localObject = paramPackageManager.getInstalledApplications(8192);
        paramActivityManager = paramActivityManager.getRunningServices(200);
        Collections.sort((List)localObject, new ApplicationInfo.DisplayNameComparator(paramPackageManager));
        long l1 = SystemClock.elapsedRealtime();
        localObject = ((List)localObject).iterator();
        if (((Iterator)localObject).hasNext())
        {
          localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
          str = a(localApplicationInfo.loadLabel(paramPackageManager).toString(), 30);
          if (!localSet.contains(localApplicationInfo.packageName)) {
            continue;
          }
          localJSONObject1 = new JSONObject();
          localJSONArray2 = new JSONArray();
          Iterator localIterator = paramActivityManager.iterator();
          if (localIterator.hasNext())
          {
            ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localIterator.next();
            if (!localRunningServiceInfo.service.getPackageName().equals(localApplicationInfo.packageName)) {
              continue;
            }
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
            continue;
          }
        }
        else
        {
          return localJSONArray1;
        }
      }
      catch (Throwable paramActivityManager)
      {
        d.a(a, "getRunningApps error:" + paramActivityManager.getMessage());
      }
      try
      {
        localJSONObject1.put("app_name", str);
        localJSONObject1.put("pkg_name", localApplicationInfo.packageName);
        localJSONObject1.put("service_list", localJSONArray2);
        localJSONArray1.put(localJSONObject1);
      }
      catch (JSONException localJSONException1)
      {
        localJSONException1.printStackTrace();
      }
    }
  }
  
  public static void a(Context paramContext)
  {
    JSONArray localJSONArray = a((ActivityManager)paramContext.getSystemService("activity"), paramContext.getPackageManager());
    if ((localJSONArray == null) || (localJSONArray.length() == 0)) {
      return;
    }
    q.a(paramContext, localJSONArray);
  }
}
