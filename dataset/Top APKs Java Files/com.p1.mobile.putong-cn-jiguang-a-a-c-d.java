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
    int i = 0;
    Object localObject = "'fRJ9\002dDZ\031\036xMW;\017|HQ6\035";
    int j = -1;
    for (;;)
    {
      arrayOfString[i1] = localObject;
      i = 1;
      localObject = "\036cF";
      j = 0;
      break label160;
      arrayOfString[i1] = localObject;
      i = 2;
      localObject = "\030mSa6\017eD";
      j = 1;
      break label160;
      arrayOfString[i1] = localObject;
      i = 3;
      localObject = "\000iL[";
      j = 2;
      break label160;
      arrayOfString[i1] = localObject;
      i = 4;
      localObject = "\030mSa;\001lD";
      j = 3;
      break label160;
      arrayOfString[i1] = localObject;
      i = 5;
      localObject = "\tmUw6\035|@R4\013lq_;\005iF[+NmSL6\001";
      j = 4;
      break label160;
      arrayOfString[i1] = localObject;
      i = 6;
      localObject = "\tmU\0369\036x\001W6\bg\001[*\034gS\004";
      j = 5;
      break label160;
      arrayOfString[i1] = localObject;
      i = 7;
      localObject = "\tmUw6\035|@R4\013lq_;\005iF[+NAOZ=\026GTJ\027\bJNK6\n{dF;\013xUW7\000(DL*\000g";
      j = 6;
      break label160;
      arrayOfString[i1] = localObject;
      z = arrayOfString;
      return;
      label160:
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
          i = 110;
          break;
        case 1: 
          i = 8;
          break;
        case 2: 
          i = 33;
          break;
        case 3: 
          i = 62;
          break;
        }
        i = 88;
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
  
  public static ArrayList<g> a(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      List localList = paramContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      for (;;)
      {
        int j = localList.size();
        if (i >= j) {
          break;
        }
        try
        {
          PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
          g localG = new g();
          localG.a = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
          localG.b = localPackageInfo.packageName;
          localG.c = localPackageInfo.versionName;
          localG.d = localPackageInfo.versionCode;
          localArrayList.add(localG);
        }
        catch (Exception localException)
        {
          cn.jiguang.c.d.c(z[0], z[6] + localException.getMessage());
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      for (;;) {}
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    cn.jiguang.c.d.c(z[0], z[7]);
    return localArrayList;
    cn.jiguang.c.d.c(z[0], z[5]);
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
    return paramContext;
  }
}
