package muneris.android.impl.method.handlers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;
import muneris.android.impl.method.MethodHandler;
import org.json.JSONObject;

public class OpenOrInstallExternalAppMethodHandler
  implements MethodHandler
{
  private final Context context;
  
  public OpenOrInstallExternalAppMethodHandler(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public String getMethod()
  {
    return "openOrInstallExternalApp";
  }
  
  public void handleEventInvoke(String paramString, JSONObject paramJSONObject)
  {
    handleInvoke(paramJSONObject);
  }
  
  public void handleInvoke(JSONObject paramJSONObject)
  {
    Object localObject2 = paramJSONObject.optString("packageId", null);
    paramJSONObject = paramJSONObject.optString("storeLink", null);
    Object localObject1 = paramJSONObject;
    if (paramJSONObject == null)
    {
      localObject1 = paramJSONObject;
      if (localObject2 != null) {
        localObject1 = "market://details?id=" + (String)localObject2;
      }
    }
    Object localObject3 = this.context.getPackageManager().getInstalledApplications(0);
    paramJSONObject = null;
    localObject3 = ((List)localObject3).iterator();
    while (((Iterator)localObject3).hasNext()) {
      if (((ApplicationInfo)((Iterator)localObject3).next()).packageName.equals(localObject2)) {
        paramJSONObject = this.context.getPackageManager().getLaunchIntentForPackage((String)localObject2);
      }
    }
    localObject2 = paramJSONObject;
    if (paramJSONObject == null)
    {
      localObject2 = paramJSONObject;
      if (localObject1 != null)
      {
        localObject2 = new Intent("android.intent.action.VIEW");
        ((Intent)localObject2).setData(Uri.parse((String)localObject1));
      }
    }
    if (localObject2 != null) {
      this.context.startActivity((Intent)localObject2);
    }
  }
  
  public void handlePushInvoke(JSONObject paramJSONObject)
  {
    handleInvoke(paramJSONObject);
  }
  
  public void handleServerInvoke(JSONObject paramJSONObject)
  {
    handleInvoke(paramJSONObject);
  }
  
  public void handleWebInvoke(JSONObject paramJSONObject, String paramString)
  {
    handleInvoke(paramJSONObject);
  }
}
