package com.tencent.connect.auth;

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
import com.tencent.connect.a.a;
import com.tencent.open.a.f;
import com.tencent.open.utils.ApkExternalInfoTool;
import com.tencent.open.utils.Global;
import com.tencent.tauth.IUiListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class QQAuth
{
  private AuthAgent a;
  private QQToken b;
  
  private QQAuth(String paramString, Context paramContext)
  {
    f.c("openSDK_LOG.QQAuth", "new QQAuth() --start");
    this.b = new QQToken(paramString);
    this.a = new AuthAgent(this.b);
    a.c(paramContext, this.b);
    f.c("openSDK_LOG.QQAuth", "new QQAuth() --end");
  }
  
  private int a(Activity paramActivity, Fragment paramFragment, String paramString1, IUiListener paramIUiListener, String paramString2)
  {
    paramString2 = paramActivity.getApplicationContext().getPackageName();
    Iterator localIterator = paramActivity.getPackageManager().getInstalledApplications(128).iterator();
    if (!localIterator.hasNext())
    {
      paramString2 = null;
      label39:
      if (paramString2 != null) {
        break label104;
      }
    }
    for (;;)
    {
      f.b("openSDK_LOG.QQAuth", "-->login channelId is null ");
      com.tencent.connect.common.BaseApi.isOEM = false;
      return this.a.doLogin(paramActivity, paramString1, paramIUiListener, false, paramFragment);
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (!paramString2.equals(localApplicationInfo.packageName)) {
        break;
      }
      paramString2 = localApplicationInfo.sourceDir;
      break label39;
      try
      {
        label104:
        paramString2 = ApkExternalInfoTool.readChannelId(new File(paramString2));
        if (!TextUtils.isEmpty(paramString2))
        {
          f.a("openSDK_LOG.QQAuth", "-->login channelId: " + paramString2);
          int i = loginWithOEM(paramActivity, paramString1, paramIUiListener, paramString2, paramString2, "");
          return i;
        }
      }
      catch (IOException paramString2)
      {
        f.b("openSDK_LOG.QQAuth", "-->login get channel id exception.", paramString2);
        paramString2.printStackTrace();
      }
    }
  }
  
  public static QQAuth createInstance(String paramString, Context paramContext)
  {
    Global.setContext(paramContext.getApplicationContext());
    f.c("openSDK_LOG.QQAuth", "QQAuth -- createInstance() --start");
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
      paramString = new QQAuth(paramString, paramContext);
      f.c("openSDK_LOG.QQAuth", "QQAuth -- createInstance()  --end");
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      f.b("openSDK_LOG.QQAuth", "createInstance() error --end", paramString);
      Toast.makeText(paramContext.getApplicationContext(), "请参照文档在Androidmanifest.xml加上AuthActivity和AssitActivity的定义 ", 1).show();
    }
    return null;
  }
  
  public void checkLogin(IUiListener paramIUiListener)
  {
    this.a.b(paramIUiListener);
  }
  
  public QQToken getQQToken()
  {
    return this.b;
  }
  
  public boolean isSessionValid()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("isSessionValid(), result = ");
    if (!this.b.isSessionValid()) {}
    for (String str = "false";; str = "true")
    {
      f.a("openSDK_LOG.QQAuth", str + "");
      return this.b.isSessionValid();
    }
  }
  
  public int login(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QQAuth", "login()");
    return login(paramActivity, paramString, paramIUiListener, "");
  }
  
  public int login(Activity paramActivity, String paramString1, IUiListener paramIUiListener, String paramString2)
  {
    f.c("openSDK_LOG.QQAuth", "-->login activity: " + paramActivity);
    return a(paramActivity, null, paramString1, paramIUiListener, paramString2);
  }
  
  public int login(Fragment paramFragment, String paramString1, IUiListener paramIUiListener, String paramString2)
  {
    FragmentActivity localFragmentActivity = paramFragment.getActivity();
    f.c("openSDK_LOG.QQAuth", "-->login activity: " + localFragmentActivity);
    return a(localFragmentActivity, paramFragment, paramString1, paramIUiListener, paramString2);
  }
  
  @Deprecated
  public int loginWithOEM(Activity paramActivity, String paramString1, IUiListener paramIUiListener, String paramString2, String paramString3, String paramString4)
  {
    f.c("openSDK_LOG.QQAuth", "loginWithOEM");
    com.tencent.connect.common.BaseApi.isOEM = true;
    if (!paramString2.equals(""))
    {
      if (paramString3.equals("")) {
        break label74;
      }
      label31:
      if (paramString4.equals("")) {
        break label81;
      }
    }
    for (;;)
    {
      com.tencent.connect.common.BaseApi.installChannel = paramString3;
      com.tencent.connect.common.BaseApi.registerChannel = paramString2;
      com.tencent.connect.common.BaseApi.businessId = paramString4;
      return this.a.doLogin(paramActivity, paramString1, paramIUiListener);
      paramString2 = "null";
      break;
      label74:
      paramString3 = "null";
      break label31;
      label81:
      paramString4 = "null";
    }
  }
  
  public void logout(Context paramContext)
  {
    f.c("openSDK_LOG.QQAuth", "logout() --start");
    CookieSyncManager.createInstance(paramContext);
    setAccessToken(null, null);
    setOpenId(paramContext, null);
    f.c("openSDK_LOG.QQAuth", "logout() --end");
  }
  
  public boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    f.c("openSDK_LOG.QQAuth", "onActivityResult() ,resultCode = " + paramInt2 + "");
    return true;
  }
  
  public int reAuth(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QQAuth", "reAuth()");
    return this.a.doLogin(paramActivity, paramString, paramIUiListener, true, null);
  }
  
  public void reportDAU()
  {
    this.a.a(null);
  }
  
  public void setAccessToken(String paramString1, String paramString2)
  {
    f.a("openSDK_LOG.QQAuth", "setAccessToken(), validTimeInSecond = " + paramString2 + "");
    this.b.setAccessToken(paramString1, paramString2);
  }
  
  public void setOpenId(Context paramContext, String paramString)
  {
    f.a("openSDK_LOG.QQAuth", "setOpenId() --start");
    this.b.setOpenId(paramString);
    a.d(paramContext, this.b);
    f.a("openSDK_LOG.QQAuth", "setOpenId() --end");
  }
}
