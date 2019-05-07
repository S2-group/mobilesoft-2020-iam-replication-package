package com.kaltura.playersdk.actionHandlers.a;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public final class d
  extends g
{
  public d() {}
  
  public final void a(JSONObject paramJSONObject, Activity paramActivity)
  {
    Iterator localIterator = paramActivity.getPackageManager().getInstalledApplications(0).iterator();
    while ((localIterator.hasNext()) && (!((ApplicationInfo)localIterator.next()).packageName.equals("com.linkedin.android"))) {}
    super.a(paramJSONObject, paramActivity);
  }
}
