package cn.jpush.android.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class u
{
  private static final String[] z;
  
  static
  {
    String[] arrayOfString = new String[4];
    Object localObject1 = "\026,'D~\001$0";
    int i = -1;
    int j = 0;
    Object localObject3 = arrayOfString;
    localObject1 = ((String)localObject1).toCharArray();
    int k = localObject1.length;
    int m;
    int n;
    int i1;
    label42:
    Object localObject2;
    int i2;
    label91:
    label113:
    Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      n = j;
      i1 = i;
      j = m;
      for (;;)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default: 
          i = 16;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0) {
            break label113;
          }
          m = k;
        }
      }
      i = k;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    for (;;)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      k = i;
      m = j;
      if (i > j) {
        break label42;
      }
      localObject1 = new String(localObject2).intern();
      switch (i2)
      {
      default: 
        localObject4[i3] = localObject1;
        localObject1 = "\026,'Ds\017-0";
        j = 1;
        i = 0;
        break;
      case 0: 
        localObject4[i3] = localObject1;
        localObject1 = "\016(8~";
        j = 2;
        i = 1;
        break;
      case 1: 
        localObject4[i3] = localObject1;
        j = 3;
        localObject1 = "\020\"2";
        i = 2;
        break;
      case 2: 
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 96;
        break label91;
        i = 73;
        break label91;
        i = 85;
        break label91;
        i = 27;
        break label91;
        m = 0;
        i2 = i;
        localObject2 = localObject1;
        i3 = j;
        localObject4 = localObject3;
        i = k;
        j = m;
      }
    }
  }
  
  public static JSONArray a(Context paramContext)
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    Object localObject3;
    while (i < ((List)localObject2).size())
    {
      localObject3 = (PackageInfo)((List)localObject2).get(i);
      m localM = new m();
      localM.a = ((PackageInfo)localObject3).applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localM.b = ((PackageInfo)localObject3).packageName;
      localM.c = ((PackageInfo)localObject3).versionName;
      localM.d = ((PackageInfo)localObject3).versionCode;
      ((ArrayList)localObject1).add(localM);
      i += 1;
    }
    paramContext = new JSONArray();
    try
    {
      localObject1 = ((ArrayList)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (m)((Iterator)localObject1).next();
        localObject3 = new JSONObject();
        ((JSONObject)localObject3).put(z[2], ((m)localObject2).a);
        ((JSONObject)localObject3).put(z[3], ((m)localObject2).b);
        ((JSONObject)localObject3).put(z[0], ((m)localObject2).c);
        ((JSONObject)localObject3).put(z[1], ((m)localObject2).d);
        paramContext.put(localObject3);
      }
      return paramContext;
    }
    catch (JSONException localJSONException) {}
  }
}
