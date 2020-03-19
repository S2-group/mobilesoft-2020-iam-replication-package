package cn.jiguang.a.a.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class d
{
  private static final String[] z;
  
  static
  {
    String[] arrayOfString = new String[9];
    int j = 0;
    Object localObject2 = "\n\r[PT\035\005L";
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
        i = 58;
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
        localObject2 = "\f\003N";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0: 
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "\n\r[PY\023\fL";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1: 
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "\025\006Z{[\020\004v{C\f\r";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2: 
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\022\tDj";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3: 
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "\033\r]/[\f\030\tfT\032\007\tjH\016\007[5";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4: 
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "5\006Z{[\020\004Lk{\f\030EfY\035\034@`T\017";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5: 
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "\033\r]FT\017\034HcV\031\fynY\027\tNjI\\!Gk_\004'\\{u\032*FzT\030\033lwY\031\030]fU\022HL}H\022\007";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6: 
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "\033\r]FT\017\034HcV\031\fynY\027\tNjI\\\r[}T\023";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7: 
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 124;
        continue;
        i = 104;
        continue;
        i = 41;
        continue;
        i = 15;
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
            localG.e = cn.jiguang.e.d.a(localPackageInfo.applicationInfo);
            localArrayList.add(localG);
            i += 1;
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              cn.jiguang.c.d.c(z[6], z[5] + localThrowable.getMessage());
            }
          }
        }
      }
      return localArrayList;
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      cn.jiguang.c.d.c(z[6], z[7]);
      return localArrayList;
    }
    catch (Throwable paramContext)
    {
      cn.jiguang.c.d.c(z[6], z[8]);
    }
  }
  
  public static String[] a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 4096).requestedPermissions;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      cn.jiguang.c.d.e(z[6], "", paramContext);
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
        localJSONObject.put(z[4], localG.a);
        localJSONObject.put(z[1], localG.b);
        localJSONObject.put(z[0], localG.c);
        localJSONObject.put(z[2], localG.d);
        localJSONObject.put(z[3], localG.e);
        paramContext.put(localJSONObject);
      }
      return paramContext;
    }
    catch (Throwable localThrowable) {}
  }
}
