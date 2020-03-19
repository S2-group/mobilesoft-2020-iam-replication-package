package com.bubadu.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.ansca.corona.CoronaActivity;
import com.ansca.corona.CoronaEnvironment;
import com.naef.jnlua.LuaState;
import com.naef.jnlua.NamedJavaFunction;
import java.util.List;

public class PackagesFunction
  implements NamedJavaFunction
{
  private String action;
  
  public PackagesFunction() {}
  
  public String getName()
  {
    return "Packages";
  }
  
  public int invoke(LuaState paramLuaState)
  {
    Object localObject2;
    try
    {
      this.action = paramLuaState.checkString(1);
      localObject2 = CoronaEnvironment.getCoronaActivity();
      if (localObject2 == null) {
        return 0;
      }
    }
    catch (Exception paramLuaState)
    {
      paramLuaState.printStackTrace();
      return 0;
    }
    if (this.action.equals("get"))
    {
      Object localObject1 = "";
      int j = 1;
      List localList = ((CoronaActivity)localObject2).getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        Object localObject3 = (PackageInfo)localList.get(i);
        localObject2 = localObject1;
        int k = j;
        if ((((PackageInfo)localObject3).applicationInfo.flags & 0x1) == 0)
        {
          localObject3 = ((PackageInfo)localObject3).applicationInfo.packageName;
          if (!((String)localObject3).toLowerCase().contains("com.bubadu"))
          {
            localObject2 = localObject1;
            k = j;
            if (!((String)localObject3).toLowerCase().contains("si.pilcom")) {}
          }
          else
          {
            localObject2 = (String)localObject1 + (String)localObject3 + ";";
            k = j + 1;
          }
        }
        i += 1;
        localObject1 = localObject2;
        j = k;
      }
      paramLuaState.pushString((String)localObject1);
    }
    return 1;
  }
}
