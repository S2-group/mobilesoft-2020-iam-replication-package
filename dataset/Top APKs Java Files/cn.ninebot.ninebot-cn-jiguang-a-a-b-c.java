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
    Object localObject1 = new String[8];
    Object localObject2 = "nA_{bzPJ^`l@{SobELW)mEViqk^FCofDGbmWnJolT_[cg\004N@~gK";
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
          localObject2 = "@JXFmeHNVMyTG[ohPB]bz";
          i = 0;
          j = 1;
          break;
        case 6: 
          localObject2[n] = localObject3;
          z = (String[])localObject1;
          return;
        case 5: 
          localObject2[n] = localObject3;
          j = 7;
          localObject2 = "yOL";
          i = 6;
          break;
        case 4: 
          localObject2[n] = localObject3;
          localObject2 = "AYmbhIN";
          i = 5;
          j = 6;
          break;
        case 3: 
          localObject2[n] = localObject3;
          localObject2 = "AYmof@N";
          i = 4;
          j = 5;
          break;
        case 2: 
          localObject2[n] = localObject3;
          localObject2 = "gEFW";
          i = 3;
          j = 4;
          break;
        case 1: 
          localObject2[n] = localObject3;
          localObject2 = "nA_\022myT\013[boK\013W~{KY\b";
          i = 2;
          j = 3;
          break;
        case 0: 
          localObject2[n] = localObject3;
          localObject2 = "nA_{bzPJ^`l@{SobELW)AY@bf";
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
          i = 12;
          break;
        case 3: 
          i = 50;
          break;
        case 2: 
          i = 43;
          break;
        case 1: 
          i = 36;
          break;
        case 0: 
          i = 9;
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
  
  public static ArrayList<f> a(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      localObject1 = paramContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      for (;;)
      {
        int j = ((List)localObject1).size();
        if (i >= j) {
          break;
        }
        try
        {
          PackageInfo localPackageInfo = (PackageInfo)((List)localObject1).get(i);
          localObject2 = new f();
          ((f)localObject2).a = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
          ((f)localObject2).b = localPackageInfo.packageName;
          ((f)localObject2).c = localPackageInfo.versionName;
          ((f)localObject2).d = localPackageInfo.versionCode;
          localArrayList.add(localObject2);
        }
        catch (Exception localException)
        {
          Object localObject2 = z[1];
          StringBuilder localStringBuilder = new StringBuilder(z[3]);
          localStringBuilder.append(localException.getMessage());
          d.c((String)localObject2, localStringBuilder.toString());
        }
        i += 1;
      }
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      Object localObject1;
      for (;;) {}
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    localObject1 = z[1];
    paramContext = z[2];
    break label199;
    localObject1 = z[1];
    paramContext = z[0];
    label199:
    d.c((String)localObject1, paramContext);
    return localArrayList;
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
    return paramContext;
  }
}
