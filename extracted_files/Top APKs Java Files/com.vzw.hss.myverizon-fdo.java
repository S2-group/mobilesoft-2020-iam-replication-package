import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class fdo
{
  private static String crZ = "";
  
  private static int cF(String paramString)
  {
    paramString = paramString.split("\\s+");
    int i = 0;
    while (i < paramString.length)
    {
      if (paramString[i].startsWith("CPU")) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static String d(Context paramContext, String paramString, boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("xid", paramString);
      paramString = t(paramContext, paramBoolean);
      glw.d(paramString.length() + " application info added");
      localJSONObject.put("Application", paramString);
      localJSONObject.put("DeviceCPUUsage", crZ);
      localJSONObject.put("DataVer", "10");
      localJSONObject.put("AppVer", fgl.cD(paramContext));
      localJSONObject.put("MVSVersion", fgl.cC(paramContext));
      localJSONObject.put("OSFirmware", fgl.Oc());
      localJSONObject.put("Features", fgl.jb(paramContext));
      localJSONObject.put("SDKINT", "" + Build.VERSION.SDK_INT);
      localJSONObject.put("Make", Build.MANUFACTURER);
      localJSONObject.put("Model", Build.MODEL);
      paramContext = localJSONObject.toString();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      glw.d("Exception in getApplicationDiagJson: " + paramContext.getMessage());
    }
    return null;
  }
  
  private static HashMap<String, fdj> gV(Context paramContext)
  {
    Object localObject = new fdk(paramContext);
    paramContext = null;
    try
    {
      localObject = ((fdk)localObject).cI(false);
      paramContext = (Context)localObject;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        glw.d("Exception e: getBatterySippersList(false)");
      }
      return localThrowable;
    }
    localObject = new HashMap();
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        fdj localFdj = (fdj)paramContext.next();
        if ((localFdj.cqq != null) && (localFdj.cqq.length == 1)) {
          ((HashMap)localObject).put(localFdj.cqq[0], localFdj);
        }
      }
    }
  }
  
  public static JSONArray t(Context paramContext, boolean paramBoolean)
  {
    JSONArray localJSONArray = new JSONArray();
    Object localObject1 = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localObject1 = ((List)localObject1).iterator();
    Object localObject2;
    Object localObject3;
    int j;
    int i;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject1).next();
      localObject3 = ((ActivityManager.RunningAppProcessInfo)localObject2).pkgList;
      j = localObject3.length;
      i = 0;
      while (i < j)
      {
        localHashMap1.put(localObject3[i], null);
        i += 1;
      }
      if ((((ActivityManager.RunningAppProcessInfo)localObject2).pkgList != null) && (((ActivityManager.RunningAppProcessInfo)localObject2).pkgList.length == 1)) {
        localHashMap2.put(localObject2.pkgList[0], Integer.valueOf(((ActivityManager.RunningAppProcessInfo)localObject2).pid));
      }
    }
    Object localObject5;
    Object localObject4;
    if (paramBoolean)
    {
      localObject5 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("top -n 1 -d 5").getInputStream()));
      localObject3 = gV(paramContext);
      localObject4 = new HashMap();
      i = 1;
      do
      {
        localObject1 = ((BufferedReader)localObject5).readLine();
        if (localObject1 == null) {
          break;
        }
        glw.d((String)localObject1);
        localObject1 = ((String)localObject1).trim();
        j = i;
        if (((String)localObject1).startsWith("User"))
        {
          j = i;
          if (i != 0)
          {
            crZ = ((String)localObject1).trim();
            j = 0;
            glw.d("DeviceCPUUsage: " + crZ);
          }
        }
        i = j;
      } while (!((String)localObject1).startsWith("PID"));
      glw.d("Label line starts with PID found");
    }
    for (;;)
    {
      i = cF((String)localObject1);
      glw.d("CPU% col index: " + i);
      for (;;)
      {
        localObject6 = ((BufferedReader)localObject5).readLine();
        localObject2 = localObject3;
        localObject1 = localObject4;
        if (localObject6 == null) {
          break;
        }
        localObject2 = ((String)localObject6).trim().split("\\s+");
        j = Integer.valueOf(localObject2[0].trim()).intValue();
        localObject1 = null;
        if (i != -1) {
          localObject1 = localObject2[i];
        }
        ((HashMap)localObject4).put(Integer.valueOf(j), localObject1);
      }
      localObject2 = gV(paramContext);
      localObject1 = null;
      Object localObject6 = paramContext.getPackageManager();
      localObject3 = ((PackageManager)localObject6).getInstalledPackages(0);
      if (localObject3 != null)
      {
        Iterator localIterator = ((List)localObject3).iterator();
        if (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          JSONObject localJSONObject = new JSONObject();
          if ((localPackageInfo.packageName != null) && (localObject2 != null))
          {
            localObject3 = (fdj)((HashMap)localObject2).get(localPackageInfo.packageName);
            if (localObject3 != null)
            {
              localJSONObject.put("cl", ((fdj)localObject3).btJ);
              localJSONObject.put("fc", ((fdj)localObject3).btK);
              localJSONObject.put("wt", ((fdj)localObject3).cqk);
              if (Build.VERSION.SDK_INT >= 21)
              {
                glw.d(((fdj)localObject3).name + " " + ((fdj)localObject3).cqv);
                localJSONObject.put("tc", ((fdj)localObject3).cqv);
                label594:
                localObject4 = "NOT RETRIEVABLE";
                localObject3 = localObject4;
                if (localPackageInfo.applicationInfo.loadLabel((PackageManager)localObject6) != null)
                {
                  localObject3 = localObject4;
                  if (localPackageInfo.applicationInfo.loadLabel((PackageManager)localObject6).length() > 0) {
                    localObject3 = localPackageInfo.applicationInfo.loadLabel((PackageManager)localObject6).toString();
                  }
                }
                if (localPackageInfo.packageName == null) {
                  break label1012;
                }
                localObject4 = localPackageInfo.packageName;
                label670:
                if (localPackageInfo.versionName == null) {
                  break label1020;
                }
                localObject5 = localPackageInfo.versionName;
                label685:
                localJSONObject.put("n", localObject3);
                localJSONObject.put("p", localObject4);
                localJSONObject.put("vn", localObject5);
                localJSONObject.put("vc", "" + localPackageInfo.versionCode);
                localJSONObject.put("l", "" + localPackageInfo.lastUpdateTime);
                localJSONObject.put("s", fgl.aI(paramContext, (String)localObject4));
                if ((localPackageInfo.applicationInfo == null) || (!localHashMap1.containsKey(localObject4))) {
                  break label1028;
                }
                localObject3 = "Running";
                label820:
                localJSONObject.put("rs", localObject3);
                if (paramBoolean)
                {
                  if (localHashMap2.get(localObject4) == null) {
                    break label1051;
                  }
                  i = ((Integer)localHashMap2.get(localObject4)).intValue();
                  if (!((HashMap)localObject1).containsKey(Integer.valueOf(i))) {
                    break label1036;
                  }
                  localJSONObject.put("cu", (String)((HashMap)localObject1).get(Integer.valueOf(i)));
                }
              }
            }
          }
          for (;;)
          {
            localJSONArray.put(localJSONObject);
            break;
            localJSONObject.put("tc", "n/a");
            break label594;
            localJSONObject.put("cl", 0);
            localJSONObject.put("fc", 0);
            localJSONObject.put("tc", 0);
            localJSONObject.put("wt", 0);
            break label594;
            localJSONObject.put("cl", "n/a");
            localJSONObject.put("fc", "n/a");
            localJSONObject.put("tc", "n/a");
            localJSONObject.put("wt", "n/a");
            break label594;
            label1012:
            localObject4 = "NOT RETRIEVABLE";
            break label670;
            label1020:
            localObject5 = "NOT RETRIEVABLE";
            break label685;
            label1028:
            localObject3 = "Not Running";
            break label820;
            label1036:
            localJSONObject.put("cu", "0%");
            continue;
            label1051:
            if (localHashMap1.containsKey(localObject4)) {
              localJSONObject.put("cu", "n/a");
            } else {
              localJSONObject.put("cu", "0%");
            }
          }
        }
      }
      return localJSONArray;
    }
  }
}
