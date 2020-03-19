package com.igexin.push.core.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.igexin.push.core.a.e;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.g;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class b
  implements a
{
  public b() {}
  
  public com.igexin.push.core.b a(PushTaskBean paramPushTaskBean, BaseAction paramBaseAction)
  {
    return com.igexin.push.core.b.a;
  }
  
  public BaseAction a(JSONObject paramJSONObject)
  {
    try
    {
      if ((paramJSONObject.has("type")) && (paramJSONObject.has("actionid")))
      {
        com.igexin.push.core.bean.b localB = new com.igexin.push.core.bean.b();
        localB.setType("checkapp");
        localB.setActionId(paramJSONObject.getString("actionid"));
        if (paramJSONObject.has("appstartupid"))
        {
          JSONObject localJSONObject = paramJSONObject.getJSONObject("appstartupid");
          if (localJSONObject.has("android"))
          {
            localB.a(localJSONObject.getString("android"));
            if ((paramJSONObject.has("do_installed")) || (paramJSONObject.has("do_uninstalled")))
            {
              if (paramJSONObject.has("do_installed")) {
                localB.b(paramJSONObject.getString("do_installed"));
              }
              if (paramJSONObject.has("do_uninstalled")) {
                localB.c(paramJSONObject.getString("do_uninstalled"));
              }
              return localB;
            }
          }
        }
      }
    }
    catch (Exception paramJSONObject) {}
    return null;
  }
  
  public boolean a(String paramString)
  {
    Iterator localIterator = g.g.getPackageManager().getInstalledPackages(4096).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean b(PushTaskBean paramPushTaskBean, BaseAction paramBaseAction)
  {
    paramBaseAction = (com.igexin.push.core.bean.b)paramBaseAction;
    String str = paramPushTaskBean.getTaskId();
    paramPushTaskBean = paramPushTaskBean.getMessageId();
    if (a(paramBaseAction.a())) {
      if ((paramBaseAction.b() != null) && (!paramBaseAction.b().equals(""))) {
        e.a().a(str, paramPushTaskBean, paramBaseAction.b());
      }
    }
    for (;;)
    {
      return true;
      if ((paramBaseAction.c() != null) && (!paramBaseAction.c().equals(""))) {
        e.a().a(str, paramPushTaskBean, paramBaseAction.c());
      }
    }
  }
}
