package cn.jpush.android.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.os.SystemClock;
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

public class aj
{
  private static final String a;
  private static final String[] z;
  
  static
  {
    Object localObject1 = new String[8];
    Object localObject2 = "\022\017S\013j";
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
          localObject2 = "M'\030Z_M'\037+.N";
          i = 0;
          j = 1;
          break;
        case 6: 
          localObject2[n] = localObject3;
          z = (String[])localObject1;
          a = aj.class.getSimpleName();
          return;
        case 5: 
          localObject2[n] = localObject3;
          j = 7;
          localObject2 = "&8aO$./l";
          i = 6;
          break;
        case 4: 
          localObject2[n] = localObject3;
          localObject2 = "$7tU!\0305tK7";
          i = 5;
          j = 6;
          break;
        case 3: 
          localObject2[n] = localObject3;
          localObject2 = "+2cC\r4>vI<#(";
          i = 4;
          j = 5;
          break;
        case 2: 
          localObject2[n] = localObject3;
          localObject2 = "4>gP;$>JJ;4/";
          i = 3;
          j = 4;
          break;
        case 1: 
          localObject2[n] = localObject3;
          localObject2 = "&+ey<&6p";
          i = 2;
          j = 3;
          break;
        case 0: 
          localObject2[n] = localObject3;
          localObject2 = "70ry<&6p";
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
          i = 82;
          break;
        case 3: 
          i = 38;
          break;
        case 2: 
          i = 21;
          break;
        case 1: 
          i = 91;
          break;
        case 0: 
          i = 71;
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
  
  public aj() {}
  
  private static String a(String paramString, int paramInt)
  {
    String str1 = paramString;
    String str2;
    if (paramString != null)
    {
      paramString = Pattern.compile(z[1]).matcher(paramString).replaceAll("");
      try
      {
        byte[] arrayOfByte = paramString.getBytes();
        str1 = paramString;
        if (arrayOfByte.length > 30)
        {
          str1 = paramString.substring(0, new String(arrayOfByte, 0, 30, z[0]).length());
          return str1;
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        localUnsupportedEncodingException.getMessage();
        ac.e();
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
    Object localObject1 = paramPackageManager;
    JSONArray localJSONArray1 = new JSONArray();
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
            long l2 = Math.round((float)((l1 - localRunningServiceInfo.activeSince) / 1000L));
            try
            {
              localJSONObject2.put(z[6], localRunningServiceInfo.service.getShortClassName());
              localJSONObject2.put(z[5], l2);
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
          localJSONObject1.put(z[3], str);
          localJSONObject1.put(z[2], ((ApplicationInfo)localObject2).packageName);
          localJSONObject1.put(z[4], localJSONArray2);
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
  
  public static void a(Context paramContext)
  {
    ac.b();
    JSONArray localJSONArray = a((ActivityManager)paramContext.getSystemService(z[7]), paramContext.getPackageManager());
    if (localJSONArray != null)
    {
      if (localJSONArray.length() == 0) {
        return;
      }
      ah.a(paramContext, localJSONArray);
    }
  }
}
