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
    int i = 0;
    try
    {
      this.action = paramLuaState.checkString(1);
      Object localObject2 = CoronaEnvironment.getCoronaActivity();
      if (localObject2 == null) {
        return 0;
      }
      if (this.action.equals("get"))
      {
        Object localObject1 = "";
        List localList = ((CoronaActivity)localObject2).getPackageManager().getInstalledPackages(0);
        while (i < localList.size())
        {
          Object localObject3 = (PackageInfo)localList.get(i);
          localObject2 = localObject1;
          if ((((PackageInfo)localObject3).applicationInfo.flags & 0x1) == 0)
          {
            localObject3 = ((PackageInfo)localObject3).applicationInfo.packageName;
            if (!((String)localObject3).toLowerCase().contains("com.bubadu"))
            {
              localObject2 = localObject1;
              if (!((String)localObject3).toLowerCase().contains("si.pilcom")) {}
            }
            else
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append((String)localObject1);
              ((StringBuilder)localObject2).append((String)localObject3);
              ((StringBuilder)localObject2).append(";");
              localObject2 = ((StringBuilder)localObject2).toString();
            }
          }
          i += 1;
          localObject1 = localObject2;
        }
        paramLuaState.pushString((String)localObject1);
      }
      return 1;
    }
    catch (Exception paramLuaState)
    {
      paramLuaState.printStackTrace();
    }
    return 0;
  }
}
