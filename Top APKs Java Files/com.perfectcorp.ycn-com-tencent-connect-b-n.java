package com.tencent.connect.b;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.open.a.i;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class n
{
  private a a;
  private o b;
  
  private n(String paramString, Context paramContext)
  {
    i.c("openSDK_LOG", "new QQAuth() --start");
    this.b = new o(paramString);
    this.a = new a(this.b);
    com.tencent.connect.a.a.c(paramContext, this.b);
    i.c("openSDK_LOG", "new QQAuth() --end");
  }
  
  private int a(Activity paramActivity, Fragment paramFragment, String paramString1, com.tencent.tauth.b paramB, String paramString2)
  {
    paramString2 = paramActivity.getApplicationContext().getPackageName();
    Iterator localIterator = paramActivity.getPackageManager().getInstalledApplications(128).iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localApplicationInfo = (ApplicationInfo)localIterator.next();
    } while (!paramString2.equals(localApplicationInfo.packageName));
    for (paramString2 = localApplicationInfo.sourceDir;; paramString2 = null)
    {
      if (paramString2 != null) {
        try
        {
          paramString2 = com.tencent.open.utils.a.a(new File(paramString2));
          if (!TextUtils.isEmpty(paramString2))
          {
            i.b("openSDK_LOG", "-->login channelId: " + paramString2);
            int i = a(paramActivity, paramString1, paramB, paramString2, paramString2, "");
            return i;
          }
        }
        catch (IOException paramString2)
        {
          i.b("openSDK_LOG", "-->login get channel id exception." + paramString2.getMessage());
          paramString2.printStackTrace();
        }
      }
      i.b("openSDK_LOG", "-->login channelId is null ");
      com.tencent.connect.common.a.j = false;
      return this.a.a(paramActivity, paramString1, paramB, false, paramFragment);
    }
  }
  
  public static n a(String paramString, Context paramContext)
  {
    com.tencent.open.utils.b.a(paramContext.getApplicationContext());
    i.c("openSDK_LOG", "QQAuth -- createInstance() --start");
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
      paramString = new n(paramString, paramContext);
      i.c("openSDK_LOG", "QQAuth -- createInstance()  --end");
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      i.b("openSDK_LOG", "createInstance() error --end", paramString);
      Toast.makeText(paramContext.getApplicationContext(), "请参照文档在Androidmanifest.xml加上AuthActivity和AssitActivity的定义 ", 1).show();
    }
    return null;
  }
  
  public int a(Activity paramActivity, String paramString, com.tencent.tauth.b paramB)
  {
    i.c("openSDK_LOG", "login()");
    return a(paramActivity, paramString, paramB, "");
  }
  
  public int a(Activity paramActivity, String paramString1, com.tencent.tauth.b paramB, String paramString2)
  {
    i.c("openSDK_LOG", "-->login activity: " + paramActivity);
    return a(paramActivity, null, paramString1, paramB, paramString2);
  }
  
  @Deprecated
  public int a(Activity paramActivity, String paramString1, com.tencent.tauth.b paramB, String paramString2, String paramString3, String paramString4)
  {
    i.c("openSDK_LOG", "loginWithOEM");
    com.tencent.connect.common.a.j = true;
    String str = paramString2;
    if (paramString2.equals("")) {
      str = "null";
    }
    paramString2 = paramString3;
    if (paramString3.equals("")) {
      paramString2 = "null";
    }
    paramString3 = paramString4;
    if (paramString4.equals("")) {
      paramString3 = "null";
    }
    com.tencent.connect.common.a.h = paramString2;
    com.tencent.connect.common.a.g = str;
    com.tencent.connect.common.a.i = paramString3;
    return this.a.a(paramActivity, paramString1, paramB);
  }
  
  public o a()
  {
    return this.b;
  }
  
  public boolean b()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("isSessionValid(), result = ");
    if (this.b.a()) {}
    for (String str = "true";; str = "false")
    {
      i.a("openSDK_LOG", str + "");
      return this.b.a();
    }
  }
}
