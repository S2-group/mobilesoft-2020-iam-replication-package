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
import cn.jiguang.b.d.o;
import cn.jiguang.c.d;
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

public class j
{
  private static final String a = "j";
  private static final String[] z;
  
  static
  {
    Object localObject1 = new String[10];
    Object localObject2 = "T;2vlT;5\007\035W";
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
          localObject2 = "\013\023y'Y";
          i = 0;
          j = 1;
          break;
        case 8: 
          localObject2[n] = localObject3;
          z = (String[])localObject1;
          return;
        case 7: 
          localObject2[n] = localObject3;
          j = 9;
          localObject2 = "-\"M|\b=\"`f\b-3";
          i = 8;
          break;
        case 6: 
          localObject2[n] = localObject3;
          localObject2 = "?7OU\017?*Z";
          i = 7;
          j = 8;
          break;
        case 5: 
          localObject2[n] = localObject3;
          localObject2 = "=+^y\022\001)^g\004";
          i = 6;
          j = 7;
          break;
        case 4: 
          localObject2[n] = localObject3;
          localObject2 = "2.Io>-\"\\e\017:4";
          i = 5;
          j = 6;
          break;
        case 3: 
          localObject2[n] = localObject3;
          localObject2 = ".,XU\017?*Z";
          i = 4;
          j = 5;
          break;
        case 2: 
          localObject2[n] = localObject3;
          localObject2 = "9\"KX\0240)Vd\006\0377OyA;5Me\023d";
          i = 3;
          j = 4;
          break;
        case 1: 
          localObject2[n] = localObject3;
          localObject2 = "?$Kc\02773F";
          i = 2;
          j = 3;
          break;
        case 0: 
          localObject2[n] = localObject3;
          localObject2 = "?$Kc\0160}Mo\02115KX\0240)Vd\006\027)Ye";
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
          i = 97;
          break;
        case 3: 
          i = 10;
          break;
        case 2: 
          i = 63;
          break;
        case 1: 
          i = 71;
          break;
        case 0: 
          i = 94;
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
  
  public j() {}
  
  public static String a(String paramString, int paramInt)
  {
    String str1 = paramString;
    String str2;
    if (paramString != null)
    {
      paramString = Pattern.compile(z[0]).matcher(paramString).replaceAll("");
      try
      {
        byte[] arrayOfByte = paramString.getBytes();
        str1 = paramString;
        if (arrayOfByte.length > 30)
        {
          str1 = paramString.substring(0, new String(arrayOfByte, 0, 30, z[1]).length());
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
                localJSONObject2.put(z[7], localRunningServiceInfo.service.getShortClassName());
                localJSONObject2.put(z[6], l2);
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
            localJSONObject1.put(z[8], str);
            localJSONObject1.put(z[5], localApplicationInfo.packageName);
            localJSONObject1.put(z[9], localJSONArray2);
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
        localObject2 = localObject3;
      }
      return localJSONArray1;
    }
    catch (Throwable paramActivityManager)
    {
      paramPackageManager = a;
      localObject2 = new StringBuilder(z[4]);
      ((StringBuilder)localObject2).append(paramActivityManager.getMessage());
      d.d(paramPackageManager, ((StringBuilder)localObject2).toString());
    }
  }
  
  public static void a(Context paramContext)
  {
    d.c(a, z[2]);
    JSONArray localJSONArray = a((ActivityManager)paramContext.getSystemService(z[3]), paramContext.getPackageManager());
    if (localJSONArray != null)
    {
      if (localJSONArray.length() == 0) {
        return;
      }
      o.a(paramContext, localJSONArray);
    }
  }
}
