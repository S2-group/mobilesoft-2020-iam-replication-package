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

public class ag
{
  private static final String a;
  private static final String[] z;
  
  static
  {
    String[] arrayOfString = new String[8];
    int j = 0;
    Object localObject2 = "\b'8#B\030'\0259B\b6";
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
        i = 43;
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
        localObject2 = "\013)-\nE\032//";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0: 
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "\0322:\nE\032//";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1: 
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "\030.+&X$,+8N";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2: 
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\027+<0t\b'):E\0371";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3: 
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = ".\026\fx\023";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4: 
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "q>G)&q>@XWr";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5: 
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "\032!><]\02263";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6: 
        localObject3[i4] = localObject1;
        z = arrayOfString;
        a = ag.class.getSimpleName();
        return;
        i = 123;
        continue;
        i = 66;
        continue;
        i = 74;
        continue;
        i = 85;
      }
    }
  }
  
  public ag() {}
  
  private static String a(String paramString, int paramInt)
  {
    if (paramString != null)
    {
      String str = Pattern.compile(z[6]).matcher(paramString).replaceAll("");
      try
      {
        byte[] arrayOfByte = str.getBytes();
        paramString = str;
        if (arrayOfByte.length > 30) {
          paramString = str.substring(0, new String(arrayOfByte, 0, 30, z[5]).length());
        }
        return paramString;
      }
      catch (UnsupportedEncodingException paramString)
      {
        paramString.getMessage();
        ab.e();
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
    Set localSet = a(paramActivityManager);
    Object localObject = paramPackageManager.getInstalledApplications(8192);
    paramActivityManager = paramActivityManager.getRunningServices(200);
    Collections.sort((List)localObject, new ApplicationInfo.DisplayNameComparator(paramPackageManager));
    long l1 = SystemClock.elapsedRealtime();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      String str = a(localApplicationInfo.loadLabel(paramPackageManager).toString(), 30);
      if (localSet.contains(localApplicationInfo.packageName))
      {
        JSONObject localJSONObject1 = new JSONObject();
        JSONArray localJSONArray2 = new JSONArray();
        Iterator localIterator = paramActivityManager.iterator();
        while (localIterator.hasNext())
        {
          ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localIterator.next();
          if (localRunningServiceInfo.service.getPackageName().equals(localApplicationInfo.packageName))
          {
            JSONObject localJSONObject2 = new JSONObject();
            long l2 = Math.round((float)((l1 - localRunningServiceInfo.activeSince) / 1000L));
            try
            {
              localJSONObject2.put(z[3], localRunningServiceInfo.service.getShortClassName());
              localJSONObject2.put(z[4], l2);
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
          localJSONObject1.put(z[2], str);
          localJSONObject1.put(z[1], localApplicationInfo.packageName);
          localJSONObject1.put(z[0], localJSONArray2);
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
    ab.b();
    JSONArray localJSONArray = a((ActivityManager)paramContext.getSystemService(z[7]), paramContext.getPackageManager());
    if ((localJSONArray == null) || (localJSONArray.length() == 0)) {
      return;
    }
    ae.a(paramContext, localJSONArray);
  }
}
