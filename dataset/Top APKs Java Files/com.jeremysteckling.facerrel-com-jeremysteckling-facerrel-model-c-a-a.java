package com.jeremysteckling.facerrel.model.c.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.google.a.a.c;
import com.google.a.e;
import com.jeremysteckling.facerrel.model.b.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
  extends f
{
  private static a d;
  private Context c;
  
  public a(com.jeremysteckling.facerrel.model.b.a paramA)
  {
    super(paramA);
    d = (a)new e().a(this.b, a.class);
  }
  
  private List<String> a()
  {
    return a.a(d);
  }
  
  public static List<String> a(String paramString)
  {
    return a.a((a)new e().a(paramString, a.class));
  }
  
  private boolean a(List<String> paramList)
  {
    Object localObject1 = this.c.getPackageManager();
    new ArrayList();
    try
    {
      Object localObject2 = ((PackageManager)localObject1).getInstalledApplications(128);
      localObject1 = new ArrayList();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((List)localObject1).add(((ApplicationInfo)((Iterator)localObject2).next()).packageName);
      }
      if (paramList == null) {
        break label133;
      }
    }
    catch (Exception paramList)
    {
      return false;
    }
    if ((paramList.size() > 0) && (((List)localObject1).size() > 0))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        if (!((List)localObject1).contains((String)paramList.next())) {
          return false;
        }
      }
      return true;
    }
    label133:
    return false;
  }
  
  public static String b(String paramString)
  {
    return a.b((a)new e().a(paramString, a.class));
  }
  
  public b a(Context paramContext)
  {
    this.c = paramContext;
    if (a(a())) {
      return b.UNOPENED;
    }
    return b.LOCKED;
  }
  
  private class a
  {
    @c(a="required_apps")
    private final List<String> a;
    @c(a="app_name")
    private final String b;
  }
}
