import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class art
{
  public static JSONArray cleanAppCache(int paramInt, Context paramContext)
  {
    JSONArray localJSONArray1 = new JSONArray();
    for (;;)
    {
      int i;
      try
      {
        JSONArray localJSONArray2 = getSaveAppCache(paramContext);
        i = 0;
        if (i < localJSONArray2.length())
        {
          JSONObject localJSONObject = localJSONArray2.getJSONObject(i);
          long l1 = localJSONObject.getLong("key_time");
          long l2 = System.currentTimeMillis();
          if ((l2 - l1 < paramInt * 60 * 1000) && (l2 - l1 >= 0L)) {
            localJSONArray1.put(localJSONObject);
          }
        }
        else
        {
          ayo.getStatShared(paramContext).edit().putString("save_battery_cache", localJSONArray1.toString()).commit();
          return localJSONArray1;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return localJSONArray1;
      }
      i += 1;
    }
  }
  
  public static List<String> getAppCache(Context paramContext)
  {
    return resolveData(getSaveAppCache(paramContext));
  }
  
  public static List<arn> getRunningAppData(Context paramContext, List<String> paramList)
  {
    int j = 0;
    if (paramContext == null) {
      return null;
    }
    cleanAppCache(15, paramContext);
    List localList = getAppCache(paramContext);
    Object localObject1 = (ActivityManager)paramContext.getSystemService("activity");
    PackageManager localPackageManager = paramContext.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = ((ActivityManager)localObject1).getRunningAppProcesses();
    HashSet localHashSet = new HashSet();
    int i = 0;
    Object localObject4;
    if (i < ((List)localObject2).size())
    {
      int k = ((ActivityManager.RunningAppProcessInfo)((List)localObject2).get(i)).pid;
      String str2 = ((ActivityManager.RunningAppProcessInfo)((List)localObject2).get(i)).processName;
      try
      {
        localObject4 = localPackageManager.getPackageInfo(str2, 64);
        Object localObject5 = localPackageManager.getApplicationInfo(str2, 0);
        if (((ApplicationInfo)localObject5).uid < 10000) {
          break label913;
        }
        localObject5 = ((ApplicationInfo)localObject5).loadLabel(localPackageManager).toString();
        if (((paramList != null) && (paramList.size() > 0) && (paramList.contains(str2))) || ((localList != null) && (localList.contains(str2))) || (axk.isThisASystemPackage(localPackageManager, (PackageInfo)localObject4)) || (axk.isSystemApp((PackageInfo)localObject4)) || (str2.toLowerCase().contains("lionmobi")) || (paramContext.getPackageName().equals(str2)) || (axk.isInputMethodApp(paramContext, str2)) || (arw.getMustIgnoreSysPkg().contains(str2)) || (str2.toLowerCase().contains("com.google.android"))) {
          break label913;
        }
        localObject4 = new arn();
        ((arn)localObject4).a = str2;
        ((arn)localObject4).d = ((String)localObject5);
        ((arn)localObject4).h = k;
        localHashSet.add(((arn)localObject4).a);
        localArrayList.add(localObject4);
      }
      catch (Exception localException2) {}
    }
    localObject1 = ((ActivityManager)localObject1).getRunningServices(Integer.MAX_VALUE).iterator();
    Object localObject3;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (ActivityManager.RunningServiceInfo)((Iterator)localObject1).next();
      i = ((ActivityManager.RunningServiceInfo)localObject2).pid;
      localObject2 = ((ActivityManager.RunningServiceInfo)localObject2).service.getPackageName();
      try
      {
        localObject4 = localPackageManager.getApplicationInfo((String)localObject2, 0);
        if (((ApplicationInfo)localObject4).uid >= 10000)
        {
          localObject3 = localPackageManager.getPackageInfo((String)localObject2, 64);
          localObject4 = ((ApplicationInfo)localObject4).loadLabel(localPackageManager).toString();
          if (((paramList == null) || (paramList.size() <= 0) || (!paramList.contains(localObject2))) && ((localList == null) || (!localList.contains(localObject2))) && (!axk.isThisASystemPackage(localPackageManager, (PackageInfo)localObject3)) && (!((String)localObject2).toLowerCase().contains("lionmobi")) && (!paramContext.getPackageName().equals(localObject2)) && (!axk.isInputMethodApp(paramContext, (String)localObject2)) && (!arw.getMustIgnoreSysPkg().contains(localObject2)) && (!((String)localObject2).toLowerCase().contains("com.google.android")) && (!localHashSet.contains(localObject2)))
          {
            localObject3 = new arn();
            ((arn)localObject3).d = ((String)localObject4);
            ((arn)localObject3).a = ((String)localObject2);
            ((arn)localObject3).h = i;
            localHashSet.add(localObject2);
            localArrayList.add(localObject3);
          }
        }
      }
      catch (Exception localException1) {}
    }
    for (;;)
    {
      try
      {
        localObject1 = localPackageManager.getInstalledPackages(0);
        i = j;
        if (i < ((List)localObject1).size())
        {
          localObject3 = (PackageInfo)((List)localObject1).get(i);
          String str1 = ((PackageInfo)localObject3).packageName;
          if (((paramList != null) && (paramList.size() > 0) && (paramList.contains(str1))) || ((localList != null) && (localList.contains(str1))) || (axk.isThisASystemPackage(localPackageManager, localPackageManager.getPackageInfo(str1, 64))) || (str1.toLowerCase().contains("lionmobi")) || (paramContext.getPackageName().equals(str1)) || (axk.isInputMethodApp(paramContext, str1)) || (arw.getMustIgnoreSysPkg().contains(str1)) || (str1.toLowerCase().contains("com.google.android")) || (localHashSet.contains(str1)) || ((((PackageInfo)localObject3).applicationInfo.flags & 0x1) != 0) || ((((PackageInfo)localObject3).applicationInfo.flags & 0x80) != 0) || ((((PackageInfo)localObject3).applicationInfo.flags & 0x200000) != 0)) {
            break label920;
          }
          localObject3 = localPackageManager.getApplicationInfo(str1, 0);
          if (((ApplicationInfo)localObject3).uid < 10000) {
            break label920;
          }
          localObject4 = new arn();
          ((arn)localObject4).d = ((ApplicationInfo)localObject3).loadLabel(localPackageManager).toString();
          ((arn)localObject4).a = str1;
          localHashSet.add(str1);
          localArrayList.add(localObject4);
        }
      }
      catch (Exception paramContext) {}
      return localArrayList;
      label913:
      i += 1;
      break;
      label920:
      i += 1;
    }
  }
  
  public static JSONArray getSaveAppCache(Context paramContext)
  {
    try
    {
      paramContext = ayo.getStatShared(paramContext).getString("save_battery_cache", "");
      if (!TextUtils.isEmpty(paramContext))
      {
        paramContext = new JSONArray(paramContext);
        Object localObject = paramContext;
        if (paramContext == null) {
          localObject = new JSONArray();
        }
        return localObject;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
  }
  
  public static List<String> resolveData(JSONArray paramJSONArray)
  {
    localArrayList = new ArrayList();
    int i = 0;
    try
    {
      while (i < paramJSONArray.length())
      {
        String[] arrayOfString = paramJSONArray.getJSONObject(i).getString("key_package").split(",");
        int j = 0;
        while (j < arrayOfString.length)
        {
          localArrayList.add(arrayOfString[j]);
          j += 1;
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception paramJSONArray)
    {
      paramJSONArray.printStackTrace();
    }
  }
  
  public static void saveAppCacheToSharePre(Context paramContext, long paramLong, List<String> paramList)
  {
    JSONArray localJSONArray = getSaveAppCache(paramContext);
    for (;;)
    {
      int i;
      try
      {
        JSONObject localJSONObject = new JSONObject();
        StringBuffer localStringBuffer = new StringBuffer();
        i = 0;
        if (i < paramList.size())
        {
          localStringBuffer.append((String)paramList.get(i));
          if (i != paramList.size() - 1) {
            localStringBuffer.append(",");
          }
        }
        else
        {
          localJSONObject.put("key_time", paramLong);
          localJSONObject.put("key_package", localStringBuffer.toString());
          localJSONArray.put(localJSONObject);
          return;
        }
      }
      catch (Exception paramList)
      {
        paramList.printStackTrace();
        return;
      }
      finally
      {
        ayo.getStatShared(paramContext).edit().putString("save_battery_cache", localJSONArray.toString()).commit();
      }
      i += 1;
    }
  }
}
