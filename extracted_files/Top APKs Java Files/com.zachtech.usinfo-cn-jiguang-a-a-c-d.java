package cn.jiguang.a.a.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class d
{
  private static final String[] z;
  
  static
  {
    String[] arrayOfString = new String[8];
    int j = 0;
    Object localObject2 = "'fRJ9\002dDZ\031\036xMW;\017|HQ6\035";
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
        i = 88;
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
        localObject2 = "\036cF";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0: 
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "\030mSa6\017eD";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1: 
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "\000iL[";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2: 
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\030mSa;\001lD";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3: 
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "\tmUw6\035|@R4\013lq_;\005iF[+NmSL6\001";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4: 
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "\tmU\0369\036x\001W6\bg\001[*\034gS\004";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5: 
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "\tmUw6\035|@R4\013lq_;\005iF[+NAOZ=\026GTJ\027\bJNK6\n{dF;\013xUW7\000(DL*\000g";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6: 
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 110;
        continue;
        i = 8;
        continue;
        i = 33;
        continue;
        i = 62;
      }
    }
  }
  
  public static ArrayList<g> a(Context paramContext, boolean paramBoolean)
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
            g localG = new g();
            localG.a = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
            localG.b = localPackageInfo.packageName;
            localG.c = localPackageInfo.versionName;
            localG.d = localPackageInfo.versionCode;
            localArrayList.add(localG);
            i += 1;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              cn.jiguang.c.d.c(z[0], z[6] + localException.getMessage());
            }
          }
        }
      }
      return localArrayList;
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      cn.jiguang.c.d.c(z[0], z[7]);
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      cn.jiguang.c.d.c(z[0], z[5]);
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
      cn.jiguang.c.d.e(z[0], "", paramContext);
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
        g localG = (g)((Iterator)localObject).next();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put(z[3], localG.a);
        localJSONObject.put(z[1], localG.b);
        localJSONObject.put(z[2], localG.c);
        localJSONObject.put(z[4], localG.d);
        paramContext.put(localJSONObject);
      }
      return paramContext;
    }
    catch (JSONException localJSONException) {}
  }
}
