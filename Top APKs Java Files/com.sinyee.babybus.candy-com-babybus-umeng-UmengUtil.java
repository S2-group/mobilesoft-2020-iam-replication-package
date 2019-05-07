package com.babybus.umeng;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.babybus.app.App;
import java.util.List;

public class UmengUtil
{
  public UmengUtil() {}
  
  public static void sendEvent4BabybusApkCount()
  {
    Object localObject = App.get().getPackageManager();
    if (localObject == null) {
      return;
    }
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    int k;
    for (int j = 0; i < ((List)localObject).size(); j = k)
    {
      k = j;
      if (((PackageInfo)((List)localObject).get(i)).packageName.contains("com.sinyee.babybus"))
      {
        k = j;
        if (!TextUtils.equals("com.sinyee.babybus.recommendapp", App.get().packName))
        {
          k = j;
          if (!TextUtils.equals("com.sinyee.babybus.chants", App.get().packName))
          {
            k = j;
            if (!TextUtils.equals("com.sinyee.babybus.bbtime.android", App.get().packName)) {
              k = j + 1;
            }
          }
        }
      }
      i += 1;
    }
    localObject = UmengAnalytics.get();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(j);
    localStringBuilder.append("");
    ((UmengAnalytics)localObject).sendEvent("2143F24D68D47C232CC89B67B610EA89", localStringBuilder.toString());
  }
}
