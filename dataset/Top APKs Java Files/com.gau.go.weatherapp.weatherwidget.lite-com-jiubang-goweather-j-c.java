package com.jiubang.goweather.j;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.jiubang.goweather.m.a;
import java.util.Iterator;
import java.util.List;

public class c
  extends h
{
  private Context mContext;
  
  public c(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  private String D(List<ApplicationInfo> paramList)
  {
    if ((paramList == null) || (paramList.size() <= 0)) {
      return "";
    }
    StringBuffer localStringBuffer;
    for (;;)
    {
      try
      {
        localStringBuffer = new StringBuffer();
        Iterator localIterator = paramList.iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        Object localObject = (ApplicationInfo)localIterator.next();
        localStringBuffer.append(((ApplicationInfo)localObject).packageName);
        localStringBuffer.append(",");
        if ((((ApplicationInfo)localObject).flags & 0x1) != 0)
        {
          localObject = "true";
          localStringBuffer.append((String)localObject);
          localStringBuffer.append(",");
          localStringBuffer.append("1.48");
          localStringBuffer.append(",");
          localStringBuffer.append(a.Em());
          localStringBuffer.append("#");
        }
        else
        {
          str = "false";
        }
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        return E(paramList);
      }
    }
    String str = localStringBuffer.toString();
    return str;
  }
  
  private String E(List<ApplicationInfo> paramList)
  {
    if ((paramList == null) || (paramList.size() <= 0)) {
      return "";
    }
    Object localObject2 = paramList.subList(0, paramList.size() / 2);
    Object localObject1 = paramList.subList(paramList.size() / 2, paramList.size());
    StringBuffer localStringBuffer1 = new StringBuffer();
    StringBuffer localStringBuffer2 = new StringBuffer();
    for (;;)
    {
      try
      {
        localObject2 = ((List)localObject2).iterator();
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        paramList = (ApplicationInfo)((Iterator)localObject2).next();
        localStringBuffer1.append(paramList.packageName);
        localStringBuffer1.append(",");
        if ((paramList.flags & 0x1) != 0)
        {
          paramList = "true";
          localStringBuffer1.append(paramList);
          localStringBuffer1.append(",");
          localStringBuffer1.append("1.48");
          localStringBuffer1.append(",");
          localStringBuffer1.append(a.Em());
          localStringBuffer1.append("#");
          continue;
          return localStringBuffer1.toString() + localStringBuffer2.toString();
        }
      }
      catch (Exception paramList) {}
      paramList = "false";
    }
    localObject1 = ((List)localObject1).iterator();
    label215:
    if (((Iterator)localObject1).hasNext())
    {
      paramList = (ApplicationInfo)((Iterator)localObject1).next();
      localStringBuffer2.append(paramList.packageName);
      localStringBuffer2.append(",");
      if ((paramList.flags & 0x1) == 0) {
        break label309;
      }
    }
    label309:
    for (paramList = "true";; paramList = "false")
    {
      localStringBuffer2.append(paramList);
      localStringBuffer2.append(",");
      localStringBuffer2.append("1.48");
      localStringBuffer2.append(",");
      localStringBuffer2.append(a.Em());
      localStringBuffer2.append("#");
      break label215;
      break;
    }
  }
  
  public static List<ApplicationInfo> getInstallAppInfo(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledApplications(0);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  private void zu()
  {
    String str = D(getInstallAppInfo(this.mContext));
    a(this.mContext, 449, "", str);
  }
  
  public void uploadAllData()
  {
    zu();
  }
}
