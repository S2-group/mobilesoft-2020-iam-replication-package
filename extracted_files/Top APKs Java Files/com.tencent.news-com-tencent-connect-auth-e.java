package com.tencent.connect.auth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.open.a.f;
import com.tencent.tauth.IUiListener;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class e
{
  private b jdField_ʻ_of_type_ComTencentConnectAuthB;
  private c jdField_ʻ_of_type_ComTencentConnectAuthC;
  
  private e(String paramString, Context paramContext)
  {
    f.c("openSDK_LOG.QQAuth", "new QQAuth() --start");
    this.jdField_ʻ_of_type_ComTencentConnectAuthC = new c(paramString);
    this.jdField_ʻ_of_type_ComTencentConnectAuthB = new b(this.jdField_ʻ_of_type_ComTencentConnectAuthC);
    com.tencent.connect.a.a.c(paramContext, this.jdField_ʻ_of_type_ComTencentConnectAuthC);
    f.c("openSDK_LOG.QQAuth", "new QQAuth() --end");
  }
  
  private int ʻ(Activity paramActivity, Fragment paramFragment, String paramString1, IUiListener paramIUiListener, String paramString2)
  {
    return ʻ(paramActivity, paramFragment, paramString1, paramIUiListener, paramString2, false);
  }
  
  private int ʻ(Activity paramActivity, Fragment paramFragment, String paramString1, IUiListener paramIUiListener, String paramString2, boolean paramBoolean)
  {
    paramString2 = paramActivity.getApplicationContext().getPackageName();
    Object localObject = paramActivity.getPackageManager();
    for (;;)
    {
      try
      {
        localObject = ((PackageManager)localObject).getInstalledApplications(128).iterator();
        if (((Iterator)localObject).hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
          if (!paramString2.equals(localApplicationInfo.packageName)) {
            continue;
          }
          paramString2 = localApplicationInfo.sourceDir;
          if (paramString2 != null)
          {
            paramString2 = com.tencent.open.utils.a.a(new File(paramString2));
            if (!TextUtils.isEmpty(paramString2))
            {
              f.a("openSDK_LOG.QQAuth", "-->login channelId: " + paramString2);
              int i = ʻ(paramActivity, paramString1, paramIUiListener, paramString2, paramString2, "");
              return i;
            }
          }
        }
      }
      catch (Throwable paramString2)
      {
        f.b("openSDK_LOG.QQAuth", "-->login get channel id exception.", paramString2);
        paramString2.printStackTrace();
        f.b("openSDK_LOG.QQAuth", "-->login channelId is null ");
        com.tencent.connect.common.BaseApi.isOEM = false;
        return this.jdField_ʻ_of_type_ComTencentConnectAuthB.ʻ(paramActivity, paramString1, paramIUiListener, false, paramFragment, paramBoolean);
      }
      paramString2 = null;
    }
  }
  
  public static e ʻ(String paramString, Context paramContext)
  {
    com.tencent.open.utils.e.a(paramContext.getApplicationContext());
    f.c("openSDK_LOG.QQAuth", "QQAuth -- createInstance() --start");
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
      paramString = new e(paramString, paramContext);
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
  
  public int ʻ(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QQAuth", "login()");
    return ʻ(paramActivity, paramString, paramIUiListener, "");
  }
  
  public int ʻ(Activity paramActivity, String paramString1, IUiListener paramIUiListener, String paramString2)
  {
    f.c("openSDK_LOG.QQAuth", "-->login activity: " + paramActivity);
    return ʻ(paramActivity, null, paramString1, paramIUiListener, paramString2);
  }
  
  @Deprecated
  public int ʻ(Activity paramActivity, String paramString1, IUiListener paramIUiListener, String paramString2, String paramString3, String paramString4)
  {
    f.c("openSDK_LOG.QQAuth", "loginWithOEM");
    com.tencent.connect.common.BaseApi.isOEM = true;
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
    com.tencent.connect.common.BaseApi.installChannel = paramString2;
    com.tencent.connect.common.BaseApi.registerChannel = str;
    com.tencent.connect.common.BaseApi.businessId = paramString3;
    return this.jdField_ʻ_of_type_ComTencentConnectAuthB.ʻ(paramActivity, paramString1, paramIUiListener);
  }
  
  public int ʻ(Activity paramActivity, String paramString, IUiListener paramIUiListener, boolean paramBoolean)
  {
    f.c("openSDK_LOG.QQAuth", "login()");
    return ʻ(paramActivity, null, paramString, paramIUiListener, "", paramBoolean);
  }
  
  public int ʻ(Fragment paramFragment, String paramString1, IUiListener paramIUiListener, String paramString2)
  {
    FragmentActivity localFragmentActivity = paramFragment.getActivity();
    f.c("openSDK_LOG.QQAuth", "-->login activity: " + localFragmentActivity);
    return ʻ(localFragmentActivity, paramFragment, paramString1, paramIUiListener, paramString2);
  }
  
  public int ʻ(Fragment paramFragment, String paramString1, IUiListener paramIUiListener, String paramString2, boolean paramBoolean)
  {
    FragmentActivity localFragmentActivity = paramFragment.getActivity();
    f.c("openSDK_LOG.QQAuth", "-->login activity: " + localFragmentActivity);
    return ʻ(localFragmentActivity, paramFragment, paramString1, paramIUiListener, paramString2, paramBoolean);
  }
  
  public c ʻ()
  {
    return this.jdField_ʻ_of_type_ComTencentConnectAuthC;
  }
  
  public void ʻ()
  {
    this.jdField_ʻ_of_type_ComTencentConnectAuthB.ʻ(null);
  }
  
  public void ʻ(Context paramContext, String paramString)
  {
    f.a("openSDK_LOG.QQAuth", "setOpenId() --start");
    this.jdField_ʻ_of_type_ComTencentConnectAuthC.ʻ(paramString);
    com.tencent.connect.a.a.d(paramContext, this.jdField_ʻ_of_type_ComTencentConnectAuthC);
    f.a("openSDK_LOG.QQAuth", "setOpenId() --end");
  }
  
  public void ʻ(IUiListener paramIUiListener)
  {
    this.jdField_ʻ_of_type_ComTencentConnectAuthB.ʼ(paramIUiListener);
  }
  
  public void ʻ(String paramString1, String paramString2)
  {
    f.a("openSDK_LOG.QQAuth", "setAccessToken(), validTimeInSecond = " + paramString2 + "");
    this.jdField_ʻ_of_type_ComTencentConnectAuthC.ʻ(paramString1, paramString2);
  }
  
  public boolean ʻ()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("isSessionValid(), result = ");
    if (this.jdField_ʻ_of_type_ComTencentConnectAuthC.ʻ()) {}
    for (String str = "true";; str = "false")
    {
      f.a("openSDK_LOG.QQAuth", str + "");
      return this.jdField_ʻ_of_type_ComTencentConnectAuthC.ʻ();
    }
  }
  
  public int ʼ(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QQAuth", "reAuth()");
    return this.jdField_ʻ_of_type_ComTencentConnectAuthB.ʻ(paramActivity, paramString, paramIUiListener, true, null);
  }
}
