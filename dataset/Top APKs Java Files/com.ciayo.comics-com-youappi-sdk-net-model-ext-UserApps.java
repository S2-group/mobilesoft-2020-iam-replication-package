package com.youappi.sdk.net.model.ext;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserApps
{
  @SerializedName("accessToken")
  private String accessToken;
  @SerializedName("appIds")
  private List<String> apps;
  @SerializedName("deviceId")
  private String deviceId;
  @SerializedName("deviceOs")
  private String deviceOs;
  
  public UserApps(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    this.accessToken = paramString3;
    this.deviceId = paramString2;
    this.deviceOs = paramString1;
    this.apps = new ArrayList();
    paramString1 = paramContext.getPackageManager();
    if (paramString1 != null)
    {
      paramString1 = paramString1.getInstalledApplications(0);
      if ((paramString1 != null) && (paramString1.size() > 0))
      {
        paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
        while (paramContext.hasNext())
        {
          paramString1 = (ApplicationInfo)paramContext.next();
          if (paramString1 != null) {
            this.apps.add(paramString1.packageName);
          }
        }
      }
    }
  }
}
