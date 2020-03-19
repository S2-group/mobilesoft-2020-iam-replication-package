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
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
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

public class ae
{
  private static final String a = "ae";
  private static final String[] z;
  
  static
  {
    Object localObject2 = new String[8];
    Object localObject1 = "\006@p=\037\026@]'\037\006Q";
    int k = -1;
    int j = 0;
    char[] arrayOfChar = ((String)localObject1).toCharArray();
    int i2 = arrayOfChar.length;
    Object localObject3;
    int i;
    int m;
    Object localObject4;
    int n;
    int i1;
    if (i2 <= 1)
    {
      localObject3 = localObject2;
      i = 0;
      m = j;
      localObject4 = localObject2;
    }
    else
    {
      localObject1 = localObject2;
      i = 0;
      n = j;
      i1 = k;
      j = i;
    }
    for (;;)
    {
      i = j;
      k = i1;
      localObject3 = localObject1;
      m = n;
      localObject4 = localObject2;
      if (i2 <= j)
      {
        localObject3 = new String(arrayOfChar).intern();
        switch (i1)
        {
        default: 
          localObject1[n] = localObject3;
          localObject1 = "\024Ur\024\030\024Hg";
          i = 0;
          j = 1;
        }
        for (;;)
        {
          k = i;
          break;
          localObject1[n] = localObject3;
          z = (String[])localObject2;
          return;
          localObject1[n] = localObject3;
          j = 7;
          localObject1 = " qDfN";
          i = 6;
          continue;
          localObject1[n] = localObject3;
          localObject1 = "Y\0177{Y\bF\n|";
          j = 6;
          i = 5;
          continue;
          localObject1[n] = localObject3;
          localObject1 = "\024Fv\"\000\034Q{";
          i = 4;
          j = 5;
          continue;
          localObject1[n] = localObject3;
          localObject1 = "\005Ne\024\030\024Hg";
          i = 3;
          j = 4;
          continue;
          localObject1[n] = localObject3;
          localObject1 = "\026Ic8\005*Kc&\023";
          j = 3;
          i = 2;
          continue;
          localObject1[n] = localObject3;
          localObject1 = "\031Lt.)\006@a$\030\021V";
          j = 2;
          i = 1;
        }
      }
      n = i;
      for (j = i;; j = i2)
      {
        i1 = arrayOfChar[j];
        switch (n % 5)
        {
        default: 
          i = 118;
          break;
        case 3: 
          i = 75;
          break;
        case 2: 
          i = 2;
          break;
        case 1: 
          i = 37;
          break;
        case 0: 
          i = 117;
        }
        arrayOfChar[j] = ((char)(i1 ^ i));
        n += 1;
        if (i2 != 0) {
          break;
        }
      }
      j = n;
      i1 = k;
      localObject1 = localObject3;
      n = m;
      localObject2 = localObject4;
    }
  }
  
  public ae() {}
  
  private static String a(String paramString, int paramInt)
  {
    String str1 = paramString;
    String str2;
    if (paramString != null)
    {
      paramString = Pattern.compile(z[6]).matcher(paramString).replaceAll("");
      try
      {
        byte[] arrayOfByte = paramString.getBytes();
        str1 = paramString;
        if (arrayOfByte.length > 30)
        {
          str1 = paramString.substring(0, new String(arrayOfByte, 0, 30, z[7]).length());
          return str1;
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        localUnsupportedEncodingException.getMessage();
        z.e();
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
              localJSONObject2.put(z[2], l2);
              localJSONArray2.put(localJSONObject2);
            }
            catch (JSONException localJSONException2)
            {
              ThrowableExtension.printStackTrace(localJSONException2);
            }
          }
        }
        localObject3 = localObject1;
        try
        {
          localJSONObject1.put(z[1], str);
          localJSONObject1.put(z[4], localApplicationInfo.packageName);
          localJSONObject1.put(z[0], localJSONArray2);
          localJSONArray1.put(localJSONObject1);
          localObject1 = paramActivityManager;
          paramActivityManager = (ActivityManager)localObject3;
        }
        catch (JSONException localJSONException1)
        {
          ThrowableExtension.printStackTrace(localJSONException1);
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
    z.b();
    JSONArray localJSONArray = a((ActivityManager)paramContext.getSystemService(z[5]), paramContext.getPackageManager());
    if (localJSONArray != null)
    {
      if (localJSONArray.length() == 0) {
        return;
      }
      ac.a(paramContext, localJSONArray);
    }
  }
}
