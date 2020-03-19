package com.dianping.picassodpplatform.bridge;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Keep;
import android.text.TextUtils;
import com.dianping.jscore.Value;
import com.dianping.picassocontroller.annotation.PCSBMethod;
import com.dianping.picassocontroller.annotation.PCSBModule;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.util.List;
import org.json.JSONObject;

@PCSBModule(a="appConfig", b=true)
@Keep
public class AppConfigModule
{
  public static ChangeQuickRedirect changeQuickRedirect;
  
  public AppConfigModule() {}
  
  @PCSBMethod(a="checkNewVersion")
  @Keep
  public Value checkNewVersion(com.dianping.picassocontroller.vc.b paramB, JSONObject paramJSONObject, com.dianping.picassocontroller.bridge.b paramB1)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramB;
    arrayOfObject[1] = paramJSONObject;
    arrayOfObject[2] = paramB1;
    paramB = changeQuickRedirect;
    if (PatchProxy.isSupport(arrayOfObject, this, paramB, false, "5eaef4c62bf8cc3bb38c386ab3f0f1ea", 4611686018427387904L)) {
      return (Value)PatchProxy.accessDispatch(arrayOfObject, this, paramB, false, "5eaef4c62bf8cc3bb38c386ab3f0f1ea");
    }
    return new Value(com.dianping.update.core.b.a().c());
  }
  
  @PCSBMethod(a="hasInstalledWechat")
  public void hasInstalledWechat(com.dianping.picassocontroller.vc.b paramB, JSONObject paramJSONObject, com.dianping.picassocontroller.bridge.b paramB1)
  {
    Object[] arrayOfObject = new Object[3];
    int i = 0;
    arrayOfObject[0] = paramB;
    arrayOfObject[1] = paramJSONObject;
    arrayOfObject[2] = paramB1;
    paramJSONObject = changeQuickRedirect;
    if (PatchProxy.isSupport(arrayOfObject, this, paramJSONObject, false, "e83f795d6eab9be6ec393291d32b5a44", 4611686018427387904L))
    {
      PatchProxy.accessDispatch(arrayOfObject, this, paramJSONObject, false, "e83f795d6eab9be6ec393291d32b5a44");
      return;
    }
    paramB = paramB.getContext().getPackageManager().getInstalledPackages(0);
    if (paramB != null) {
      while (i < paramB.size())
      {
        if (TextUtils.equals(((PackageInfo)paramB.get(i)).packageName, "com.tencent.mm"))
        {
          paramB1.a(new JSONObject());
          return;
        }
        i += 1;
      }
    }
    paramB1.b(new JSONObject());
  }
}
