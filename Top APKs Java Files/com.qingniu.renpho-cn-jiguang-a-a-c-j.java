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
  private static final String a;
  private static final String[] z;
  
  static
  {
    String[] arrayOfString = new String[10];
    int j = 0;
    Object localObject2 = "T;2vlT;5\007\035W";
    int i = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject2 = arrayOfChar;
    int i4 = j;
    Object localObject3 = localObject1;
    int n = k;
    Object localObject4;
    int i2;
    if (k <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label68:
      n = m;
      label71:
      localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default: 
        i = 97;
      }
    }
    for (;;)
    {
      localObject2[m] = ((char)(i ^ i1));
      n += 1;
      if (k == 0)
      {
        m = k;
        break label71;
      }
      i1 = n;
      n = k;
      localObject3 = localObject4;
      i4 = j;
      localObject2 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject2;
      j = i4;
      localObject4 = localObject3;
      k = n;
      m = i1;
      if (n > i1) {
        break label68;
      }
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default: 
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "\013\023y'Y";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0: 
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "?$Kc\0160}Mo\02115KX\0240)Vd\006\027)Ye";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1: 
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "?$Kc\02773F";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2: 
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "9\"KX\0240)Vd\006\0377OyA;5Me\023d";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3: 
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = ".,XU\017?*Z";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4: 
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "2.Io>-\"\\e\017:4";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5: 
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "=+^y\022\001)^g\004";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6: 
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "?7OU\017?*Z";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7: 
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "-\"M|\b=\"`f\b-3";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8: 
        localObject3[i4] = localObject1;
        z = arrayOfString;
        a = j.class.getSimpleName();
        return;
        i = 94;
        continue;
        i = 71;
        continue;
        i = 63;
        continue;
        i = 10;
      }
    }
  }
  
  public j() {}
  
  public static String a(String paramString, int paramInt)
  {
    if (paramString != null)
    {
      String str = Pattern.compile(z[0]).matcher(paramString).replaceAll("");
      try
      {
        byte[] arrayOfByte = str.getBytes();
        paramString = str;
        if (arrayOfByte.length > 30) {
          paramString = str.substring(0, new String(arrayOfByte, 0, 30, z[1]).length());
        }
        return paramString;
      }
      catch (UnsupportedEncodingException paramString)
      {
        d.i(a, paramString.getMessage());
        return str;
      }
    }
    return paramString;
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
              localJSONObject2.put(z[7], localRunningServiceInfo.service.getShortClassName());
              localJSONObject2.put(z[6], l2);
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
        d.d(a, z[4] + paramActivityManager.getMessage());
      }
      try
      {
        localJSONObject1.put(z[8], str);
        localJSONObject1.put(z[5], localApplicationInfo.packageName);
        localJSONObject1.put(z[9], localJSONArray2);
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
    d.c(a, z[2]);
    JSONArray localJSONArray = a((ActivityManager)paramContext.getSystemService(z[3]), paramContext.getPackageManager());
    if ((localJSONArray == null) || (localJSONArray.length() == 0)) {
      return;
    }
    o.a(paramContext, localJSONArray);
  }
}
