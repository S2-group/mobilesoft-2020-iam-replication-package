package cn.jiguang.a.a.b;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import cn.jiguang.c.d.l;
import cn.jiguang.d.d;
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

public class i
{
  private static final String a = "i";
  private static final String[] z;
  
  static
  {
    Object localObject1 = new String[9];
    Object localObject2 = "\035Qxx{\002]mrJ\025K";
    int i = -1;
    int j = 0;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int i2 = arrayOfChar.length;
    int m;
    Object localObject3;
    int k;
    Object localObject4;
    int n;
    int i1;
    if (i2 <= 1)
    {
      m = j;
      localObject3 = localObject1;
      j = 0;
      k = i;
      localObject4 = localObject1;
    }
    else
    {
      localObject2 = localObject1;
      k = 0;
      n = j;
      i1 = i;
      i = k;
    }
    for (;;)
    {
      j = i;
      k = i1;
      localObject3 = localObject2;
      m = n;
      localObject4 = localObject1;
      if (i2 <= i)
      {
        localObject3 = new String(arrayOfChar).intern();
        switch (i1)
        {
        default: 
          localObject2[n] = localObject3;
          localObject2 = "\002]|kM\022]QqM\002L";
          i = 0;
          j = 1;
          break;
        case 7: 
          localObject2[n] = localObject3;
          z = (String[])localObject1;
          return;
        case 6: 
          localObject2[n] = localObject3;
          j = 8;
          localObject2 = "$lH0\034";
          i = 7;
          break;
        case 5: 
          localObject2[n] = localObject3;
          localObject2 = "{D\003a){D\004\020Xx";
          i = 6;
          j = 7;
          break;
        case 4: 
          localObject2[n] = localObject3;
          localObject2 = "\020[ztR\030Lw";
          i = 5;
          j = 6;
          break;
        case 3: 
          localObject2[n] = localObject3;
          localObject2 = "\020[ztK\037\002|xT\036JzOQ\037VgsC8Vhr";
          i = 4;
          j = 5;
          break;
        case 2: 
          localObject2[n] = localObject3;
          localObject2 = "\001SiBJ\020Uk";
          i = 3;
          j = 4;
          break;
        case 1: 
          localObject2[n] = localObject3;
          localObject2 = "\022TonW.VopA";
          i = 2;
          j = 3;
          break;
        case 0: 
          localObject2[n] = localObject3;
          localObject2 = "\020H~BJ\020Uk";
          i = 1;
          j = 2;
          break;
        }
      }
      n = j;
      for (;;)
      {
        i1 = arrayOfChar[j];
        switch (n % 5)
        {
        default: 
          i = 36;
          break;
        case 3: 
          i = 29;
          break;
        case 2: 
          i = 14;
          break;
        case 1: 
          i = 56;
          break;
        case 0: 
          i = 113;
        }
        arrayOfChar[j] = ((char)(i1 ^ i));
        n += 1;
        if (i2 != 0) {
          break;
        }
        j = i2;
      }
      i = n;
      i1 = k;
      localObject2 = localObject3;
      n = m;
      localObject1 = localObject4;
    }
  }
  
  public i() {}
  
  private static String a(String paramString, int paramInt)
  {
    String str1 = paramString;
    String str2;
    if (paramString != null)
    {
      paramString = Pattern.compile(z[7]).matcher(paramString).replaceAll("");
      try
      {
        byte[] arrayOfByte = paramString.getBytes();
        str1 = paramString;
        if (arrayOfByte.length > 30)
        {
          str1 = paramString.substring(0, new String(arrayOfByte, 0, 30, z[8]).length());
          return str1;
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        d.i(a, localUnsupportedEncodingException.getMessage());
        str2 = paramString;
      }
    }
    return str2;
  }
  
  private static Set<String> a(ActivityManager paramActivityManager)
  {
    HashSet localHashSet = new HashSet();
    paramActivityManager = paramActivityManager.getRunningAppProcesses().iterator();
    while (paramActivityManager.hasNext())
    {
      String[] arrayOfString = ((ActivityManager.RunningAppProcessInfo)paramActivityManager.next()).pkgList;
      int i = 0;
      while (i < arrayOfString.length)
      {
        localHashSet.add(arrayOfString[i]);
        i += 1;
      }
    }
    return localHashSet;
  }
  
  private static JSONArray a(ActivityManager paramActivityManager, PackageManager paramPackageManager)
  {
    JSONArray localJSONArray1 = new JSONArray();
    Object localObject3 = a(paramActivityManager);
    Object localObject1 = paramPackageManager.getInstalledApplications(8192);
    List localList = paramActivityManager.getRunningServices(200);
    Collections.sort((List)localObject1, new ApplicationInfo.DisplayNameComparator(paramPackageManager));
    long l1 = SystemClock.elapsedRealtime();
    localObject1 = ((List)localObject1).iterator();
    paramActivityManager = (ActivityManager)localObject3;
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
            long l2 = Math.round((float)((l1 - localRunningServiceInfo.activeSince) / 1000L));
            try
            {
              localJSONObject2.put(z[3], localRunningServiceInfo.service.getShortClassName());
              localJSONObject2.put(z[0], l2);
              localJSONArray2.put(localJSONObject2);
            }
            catch (JSONException localJSONException2)
            {
              localJSONException2.printStackTrace();
            }
          }
        }
        localObject3 = localObject1;
        try
        {
          localJSONObject1.put(z[2], str);
          localJSONObject1.put(z[4], localApplicationInfo.packageName);
          localJSONObject1.put(z[1], localJSONArray2);
          localJSONArray1.put(localJSONObject1);
          localObject1 = paramActivityManager;
          paramActivityManager = (ActivityManager)localObject3;
        }
        catch (JSONException localJSONException1)
        {
          localJSONException1.printStackTrace();
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
      Object localObject2 = localObject3;
    }
    return localJSONArray1;
  }
  
  public static void a(Context paramContext)
  {
    d.c(a, z[5]);
    JSONArray localJSONArray = a((ActivityManager)paramContext.getSystemService(z[6]), paramContext.getPackageManager());
    if (localJSONArray != null)
    {
      if (localJSONArray.length() == 0) {
        return;
      }
      l.a(paramContext, localJSONArray);
    }
  }
}
