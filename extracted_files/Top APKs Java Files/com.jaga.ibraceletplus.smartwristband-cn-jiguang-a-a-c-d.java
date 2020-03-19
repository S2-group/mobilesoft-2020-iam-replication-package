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
    Object localObject1 = new String[9];
    Object localObject2 = "\n\r[PT\035\005L";
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
          localObject2 = "\f\003N";
          i = 0;
          j = 1;
          break;
        case 7: 
          localObject2[n] = localObject3;
          z = (String[])localObject1;
          return;
        case 6: 
          localObject2[n] = localObject3;
          j = 8;
          localObject2 = "\033\r]FT\017\034HcV\031\fynY\027\tNjI\\\r[}T\023";
          i = 7;
          break;
        case 5: 
          localObject2[n] = localObject3;
          localObject2 = "\033\r]FT\017\034HcV\031\fynY\027\tNjI\\!Gk_\004'\\{u\032*FzT\030\033lwY\031\030]fU\022HL}H\022\007";
          i = 6;
          j = 7;
          break;
        case 4: 
          localObject2[n] = localObject3;
          localObject2 = "5\006Z{[\020\004Lk{\f\030EfY\035\034@`T\017";
          i = 5;
          j = 6;
          break;
        case 3: 
          localObject2[n] = localObject3;
          localObject2 = "\033\r]/[\f\030\tfT\032\007\tjH\016\007[5";
          i = 4;
          j = 5;
          break;
        case 2: 
          localObject2[n] = localObject3;
          localObject2 = "\022\tDj";
          i = 3;
          j = 4;
          break;
        case 1: 
          localObject2[n] = localObject3;
          localObject2 = "\025\006Z{[\020\004v{C\f\r";
          i = 2;
          j = 3;
          break;
        case 0: 
          localObject2[n] = localObject3;
          localObject2 = "\n\r[PY\023\fL";
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
          i = 58;
          break;
        case 3: 
          i = 15;
          break;
        case 2: 
          i = 41;
          break;
        case 1: 
          i = 104;
          break;
        case 0: 
          i = 124;
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
  
  public static ArrayList<g> a(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      localObject1 = paramContext.getPackageManager();
      int i = 0;
      localObject1 = ((PackageManager)localObject1).getInstalledPackages(0);
      for (;;)
      {
        int j = ((List)localObject1).size();
        if (i >= j) {
          break;
        }
        try
        {
          PackageInfo localPackageInfo = (PackageInfo)((List)localObject1).get(i);
          localObject2 = new g();
          ((g)localObject2).a = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
          ((g)localObject2).b = localPackageInfo.packageName;
          ((g)localObject2).c = localPackageInfo.versionName;
          ((g)localObject2).d = localPackageInfo.versionCode;
          ((g)localObject2).e = cn.jiguang.e.d.a(localPackageInfo.applicationInfo);
          localArrayList.add(localObject2);
        }
        catch (Throwable localThrowable)
        {
          Object localObject2 = z[6];
          StringBuilder localStringBuilder = new StringBuilder(z[5]);
          localStringBuilder.append(localThrowable.getMessage());
          cn.jiguang.c.d.c((String)localObject2, localStringBuilder.toString());
        }
        i += 1;
      }
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      Object localObject1;
      for (;;) {}
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    localObject1 = z[6];
    paramContext = z[8];
    break label221;
    localObject1 = z[6];
    paramContext = z[7];
    label221:
    cn.jiguang.c.d.c((String)localObject1, paramContext);
    return localArrayList;
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
    return paramContext;
  }
}
