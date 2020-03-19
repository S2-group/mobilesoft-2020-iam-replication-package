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
  private static final String a = j.class.getSimpleName();
  private static final String[] z;
  
  static
  {
    String[] arrayOfString = new String[9];
    int i = 0;
    Object localObject = "\037\020D\021\003\027\007I";
    int j = -1;
    for (;;)
    {
      arrayOfString[i1] = localObject;
      i = 1;
      localObject = "\037\020D\021\032\020IB\035\005\021\001D*\000\020\035Y\026\0227\035V\027";
      j = 0;
      break label182;
      arrayOfString[i1] = localObject;
      i = 2;
      localObject = "\035\037Q\013\006!\035Q\025\020";
      j = 1;
      break label182;
      arrayOfString[i1] = localObject;
      i = 3;
      localObject = "\037\003@'\033\037\036U";
      j = 2;
      break label182;
      arrayOfString[i1] = localObject;
      i = 4;
      localObject = "\016\030W'\033\037\036U";
      j = 3;
      break label182;
      arrayOfString[i1] = localObject;
      i = 5;
      localObject = "\r\026B\016\034\035\026o\024\034\r\007";
      j = 4;
      break label182;
      arrayOfString[i1] = localObject;
      i = 6;
      localObject = "\022\032F\035*\r\026S\027\033\032\000";
      j = 5;
      break label182;
      arrayOfString[i1] = localObject;
      i = 7;
      localObject = "+'vUM";
      j = 6;
      break label182;
      arrayOfString[i1] = localObject;
      i = 8;
      localObject = "t\017=\004xt\017:u\tw";
      j = 7;
      break label182;
      arrayOfString[i1] = localObject;
      z = arrayOfString;
      break;
      label182:
      localObject = ((String)localObject).toCharArray();
      int i3 = localObject.length;
      int k = 0;
      int m = 0;
      int i1 = i;
      int i2 = j;
      int n;
      if (i3 <= 1) {
        n = i;
      }
      do
      {
        k = localObject[m];
        switch (m % 5)
        {
        default: 
          break;
        case 0: 
          i = 126;
          break;
        case 1: 
          i = 115;
          break;
        case 2: 
          i = 48;
          break;
        case 3: 
          i = 120;
          break;
        }
        i = 117;
        localObject[m] = ((char)(k ^ i));
        k = m + 1;
        i2 = j;
        i1 = n;
        n = i1;
        j = i2;
        m = k;
      } while (i3 > k);
      localObject = new String((char[])localObject).intern();
      switch (i2)
      {
      }
    }
  }
  
  public j() {}
  
  private static String a(String paramString, int paramInt)
  {
    String str1 = paramString;
    String str2;
    if (paramString != null)
    {
      paramString = Pattern.compile(z[8]).matcher(paramString).replaceAll("");
      try
      {
        byte[] arrayOfByte = paramString.getBytes();
        str1 = paramString;
        if (arrayOfByte.length > 30) {
          str1 = paramString.substring(0, new String(arrayOfByte, 0, 30, z[7]).length());
        }
        return str1;
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
              localJSONObject2.put(z[2], localRunningServiceInfo.service.getShortClassName());
              localJSONObject2.put(z[6], l2);
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
          localJSONObject1.put(z[4], localApplicationInfo.packageName);
          localJSONObject1.put(z[5], localJSONArray2);
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
    d.c(a, z[1]);
    JSONArray localJSONArray = a((ActivityManager)paramContext.getSystemService(z[0]), paramContext.getPackageManager());
    if ((localJSONArray == null) || (localJSONArray.length() == 0)) {
      return;
    }
    o.a(paramContext, localJSONArray);
  }
}
