package com.tencent.connect.b;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.widget.Toast;
import com.tencent.open.utils.d;
import com.tencent.tauth.b;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class e
{
  private a a;
  private f b;
  
  private e(String paramString, Context paramContext)
  {
    com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "new QQAuth() --start");
    this.b = new f(paramString);
    this.a = new a(this.b);
    com.tencent.connect.a.a.c(paramContext, this.b);
    com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "new QQAuth() --end");
  }
  
  private int a(Activity paramActivity, Fragment paramFragment, String paramString1, b paramB, String paramString2)
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
            com.tencent.open.a.f.a("openSDK_LOG.QQAuth", "-->login channelId: " + paramString2);
            int i = a(paramActivity, paramString1, paramB, paramString2, paramString2, "");
            return i;
          }
        }
        catch (IOException paramString2)
        {
          com.tencent.open.a.f.b("openSDK_LOG.QQAuth", "-->login get channel id exception.", paramString2);
          paramString2.printStackTrace();
        }
      }
      com.tencent.open.a.f.b("openSDK_LOG.QQAuth", "-->login channelId is null ");
      com.tencent.connect.common.a.r = false;
      return this.a.a(paramActivity, paramString1, paramB, false, paramFragment);
    }
  }
  
  public static e a(String paramString, Context paramContext)
  {
    d.a(paramContext.getApplicationContext());
    com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "QQAuth -- createInstance() --start");
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
      paramString = new e(paramString, paramContext);
      com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "QQAuth -- createInstance()  --end");
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      com.tencent.open.a.f.b("openSDK_LOG.QQAuth", "createInstance() error --end", paramString);
      Toast.makeText(paramContext.getApplicationContext(), "请参照文档在Androidmanifest.xml加上AuthActivity和AssitActivity的定义 ", 1).show();
    }
    return null;
  }
  
  public int a(Activity paramActivity, String paramString, b paramB)
  {
    com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "login()");
    return a(paramActivity, paramString, paramB, "");
  }
  
  public int a(Activity paramActivity, String paramString1, b paramB, String paramString2)
  {
    com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "-->login activity: " + paramActivity);
    return a(paramActivity, null, paramString1, paramB, paramString2);
  }
  
  @Deprecated
  public int a(Activity paramActivity, String paramString1, b paramB, String paramString2, String paramString3, String paramString4)
  {
    com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "loginWithOEM");
    com.tencent.connect.common.a.r = true;
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
    com.tencent.connect.common.a.p = paramString2;
    com.tencent.connect.common.a.o = str;
    com.tencent.connect.common.a.q = paramString3;
    return this.a.a(paramActivity, paramString1, paramB);
  }
  
  public int a(Fragment paramFragment, String paramString1, b paramB, String paramString2)
  {
    FragmentActivity localFragmentActivity = paramFragment.getActivity();
    com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "-->login activity: " + localFragmentActivity);
    return a(localFragmentActivity, paramFragment, paramString1, paramB, paramString2);
  }
  
  public void a()
  {
    this.a.a(null);
  }
  
  public void a(Context paramContext)
  {
    com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "logout() --start");
    CookieSyncManager.createInstance(paramContext);
    a(null, null);
    a(paramContext, null);
    com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "logout() --end");
  }
  
  public void a(Context paramContext, String paramString)
  {
    com.tencent.open.a.f.a("openSDK_LOG.QQAuth", "setOpenId() --start");
    this.b.b(paramString);
    com.tencent.connect.a.a.d(paramContext, this.b);
    com.tencent.open.a.f.a("openSDK_LOG.QQAuth", "setOpenId() --end");
  }
  
  public void a(b paramB)
  {
    this.a.b(paramB);
  }
  
  public void a(String paramString1, String paramString2)
  {
    com.tencent.open.a.f.a("openSDK_LOG.QQAuth", "setAccessToken(), validTimeInSecond = " + paramString2 + "");
    this.b.a(paramString1, paramString2);
  }
  
  public boolean a(int paramInt1, int paramInt2, Intent paramIntent)
  {
    com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "onActivityResult() ,resultCode = " + paramInt2 + "");
    return true;
  }
  
  public int b(Activity paramActivity, String paramString, b paramB)
  {
    com.tencent.open.a.f.c("openSDK_LOG.QQAuth", "reAuth()");
    return this.a.a(paramActivity, paramString, paramB, true, null);
  }
  
  public f b()
  {
    return this.b;
  }
  
  public boolean c()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("isSessionValid(), result = ");
    if (this.b.a()) {}
    for (String str = "true";; str = "false")
    {
      com.tencent.open.a.f.a("openSDK_LOG.QQAuth", str + "");
      return this.b.a();
    }
  }
}
