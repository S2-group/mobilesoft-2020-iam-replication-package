package cn.jiguang.a.a.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import cn.jiguang.d.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class c
{
  private static final String[] z;
  
  static
  {
    String[] arrayOfString = new String[8];
    int j = 0;
    Object localObject2 = "nA_{bzPJ^`l@{SobELW)mEViqk^FCofDGbmWnJolT_[cg\004N@~gK";
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
        i = 12;
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
        localObject2 = "@JXFmeHNVMyTG[ohPB]bz";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0: 
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "nA_{bzPJ^`l@{SobELW)AY@bf";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1: 
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "nA_\022myT\013[boK\013W~{KY\b";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2: 
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "gEFW";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3: 
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "AYmof@N";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4: 
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "AYmbhIN";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5: 
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "yOL";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6: 
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 9;
        continue;
        i = 36;
        continue;
        i = 43;
        continue;
        i = 50;
      }
    }
  }
  
  public static ArrayList<f> a(Context paramContext, boolean paramBoolean)
  {
    localArrayList = new ArrayList();
    try
    {
      List localList = paramContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      for (;;)
      {
        int j = localList.size();
        if (i < j) {
          try
          {
            PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
            f localF = new f();
            localF.a = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
            localF.b = localPackageInfo.packageName;
            localF.c = localPackageInfo.versionName;
            localF.d = localPackageInfo.versionCode;
            localArrayList.add(localF);
            i += 1;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              d.c(z[1], z[3] + localException.getMessage());
            }
          }
        }
      }
      return localArrayList;
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      d.c(z[1], z[0]);
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      d.c(z[1], z[2]);
    }
  }
  
  public static String[] a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 4096).requestedPermissions;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      d.e(z[1], "", paramContext);
    }
    return null;
  }
  
  public static JSONArray b(Context paramContext)
  {
    Object localObject = a(paramContext, true);
    paramContext = new JSONArray();
    try
    {
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        f localF = (f)((Iterator)localObject).next();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put(z[4], localF.a);
        localJSONObject.put(z[7], localF.b);
        localJSONObject.put(z[6], localF.c);
        localJSONObject.put(z[5], localF.d);
        paramContext.put(localJSONObject);
      }
      return paramContext;
    }
    catch (JSONException localJSONException) {}
  }
}
