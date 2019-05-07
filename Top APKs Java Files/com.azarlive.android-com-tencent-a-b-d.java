package com.tencent.a.b;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.Fragment;
import android.support.v4.app.n;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.widget.Toast;
import com.tencent.b.b;
import com.tencent.open.a.f;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class d
{
  private a a;
  private e b;
  
  private d(String paramString, Context paramContext)
  {
    f.c("openSDK_LOG", "new QQAuth() --start");
    this.b = new e(paramString);
    this.a = new a(this.b);
    com.tencent.a.a.a.c(paramContext, this.b);
    f.c("openSDK_LOG", "new QQAuth() --end");
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
          paramString2 = com.tencent.open.d.a.readChannelId(new File(paramString2));
          if (!TextUtils.isEmpty(paramString2))
          {
            f.b("openSDK_LOG", "-->login channelId: " + paramString2);
            int i = loginWithOEM(paramActivity, paramString1, paramB, paramString2, paramString2, "");
            return i;
          }
        }
        catch (IOException paramString2)
        {
          f.b("openSDK_LOG", "-->login get channel id exception." + paramString2.getMessage());
          paramString2.printStackTrace();
        }
      }
      f.b("openSDK_LOG", "-->login channelId is null ");
      com.tencent.a.c.b.isOEM = false;
      return this.a.doLogin(paramActivity, paramString1, paramB, false, paramFragment);
    }
  }
  
  public static d createInstance(String paramString, Context paramContext)
  {
    com.tencent.open.d.d.setContext(paramContext.getApplicationContext());
    f.c("openSDK_LOG", "QQAuth -- createInstance() --start");
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
      paramString = new d(paramString, paramContext);
      f.c("openSDK_LOG", "QQAuth -- createInstance()  --end");
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      f.b("openSDK_LOG", "createInstance() error --end", paramString);
      Toast.makeText(paramContext.getApplicationContext(), "请参照文档在Androidmanifest.xml加上AuthActivity和AssitActivity的定义 ", 1).show();
    }
    return null;
  }
  
  public void checkLogin(b paramB)
  {
    this.a.b(paramB);
  }
  
  public e getQQToken()
  {
    return this.b;
  }
  
  public boolean isSessionValid()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("isSessionValid(), result = ");
    if (this.b.isSessionValid()) {}
    for (String str = "true";; str = "false")
    {
      f.a("openSDK_LOG", str + "");
      return this.b.isSessionValid();
    }
  }
  
  public int login(Activity paramActivity, String paramString, b paramB)
  {
    f.c("openSDK_LOG", "login()");
    return login(paramActivity, paramString, paramB, "");
  }
  
  public int login(Activity paramActivity, String paramString1, b paramB, String paramString2)
  {
    f.c("openSDK_LOG", "-->login activity: " + paramActivity);
    return a(paramActivity, null, paramString1, paramB, paramString2);
  }
  
  public int login(Fragment paramFragment, String paramString1, b paramB, String paramString2)
  {
    n localN = paramFragment.getActivity();
    f.c("openSDK_LOG", "-->login activity: " + localN);
    return a(localN, paramFragment, paramString1, paramB, paramString2);
  }
  
  @Deprecated
  public int loginWithOEM(Activity paramActivity, String paramString1, b paramB, String paramString2, String paramString3, String paramString4)
  {
    f.c("openSDK_LOG", "loginWithOEM");
    com.tencent.a.c.b.isOEM = true;
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
    com.tencent.a.c.b.installChannel = paramString2;
    com.tencent.a.c.b.registerChannel = str;
    com.tencent.a.c.b.businessId = paramString3;
    return this.a.doLogin(paramActivity, paramString1, paramB);
  }
  
  public void logout(Context paramContext)
  {
    f.c("openSDK_LOG", "logout() --start");
    CookieSyncManager.createInstance(paramContext);
    setAccessToken(null, null);
    setOpenId(paramContext, null);
    f.c("openSDK_LOG", "logout() --end");
  }
  
  public boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    f.c("openSDK_LOG", "onActivityResult() ,resultCode = " + paramInt2 + "");
    return true;
  }
  
  public int reAuth(Activity paramActivity, String paramString, b paramB)
  {
    f.c("openSDK_LOG", "reAuth()");
    return this.a.doLogin(paramActivity, paramString, paramB, true, null);
  }
  
  public void reportDAU()
  {
    this.a.a(null);
  }
  
  public void setAccessToken(String paramString1, String paramString2)
  {
    f.a("openSDK_LOG", "setAccessToken(), validTimeInSecond = " + paramString2 + "");
    this.b.setAccessToken(paramString1, paramString2);
  }
  
  public void setOpenId(Context paramContext, String paramString)
  {
    f.a("openSDK_LOG", "setOpenId() --start");
    this.b.setOpenId(paramString);
    com.tencent.a.a.a.d(paramContext, this.b);
    f.a("openSDK_LOG", "setOpenId() --end");
  }
}
