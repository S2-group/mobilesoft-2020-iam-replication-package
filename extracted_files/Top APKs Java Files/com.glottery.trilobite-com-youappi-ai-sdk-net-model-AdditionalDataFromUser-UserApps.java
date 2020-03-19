package com.youappi.ai.sdk.net.model.AdditionalDataFromUser;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserApps
{
  @SerializedName("accessToken")
  String accessToken;
  @SerializedName("appIds")
  List<String> apps;
  @SerializedName("deviceId")
  String deviceId;
  @SerializedName("deviceOs")
  String deviceOs;
  
  public UserApps(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    this.accessToken = paramString3;
    this.deviceId = paramString2;
    this.deviceOs = paramString1;
    this.apps = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(8).iterator();
    while (paramContext.hasNext())
    {
      paramString1 = ((PackageInfo)paramContext.next()).providers;
      if (paramString1 != null)
      {
        int j = paramString1.length;
        int i = 0;
        while (i < j)
        {
          paramString2 = paramString1[i];
          if ((paramString2 != null) && (paramString2.authority != null)) {
            this.apps.add(paramString2.authority);
          }
          i += 1;
        }
      }
    }
  }
}
