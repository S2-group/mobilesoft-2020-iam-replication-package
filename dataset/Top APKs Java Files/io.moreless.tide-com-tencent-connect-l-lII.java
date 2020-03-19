package com.tencent.connect.l;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.open.I.lIl;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class lII
{
  private I I;
  private l l;
  
  private lII(String paramString, Context paramContext)
  {
    lIl.lI("openSDK_LOG.QQAuth", "new QQAuth() --start");
    this.l = new l(paramString);
    this.I = new I(this.l);
    com.tencent.connect.I.I.lI(paramContext, this.l);
    lIl.lI("openSDK_LOG.QQAuth", "new QQAuth() --end");
  }
  
  private int I(Activity paramActivity, androidx.fragment.I.ll paramLl, String paramString1, com.tencent.tauth.l paramL, String paramString2)
  {
    return I(paramActivity, paramLl, paramString1, paramL, paramString2, false);
  }
  
  private int I(Activity paramActivity, androidx.fragment.I.ll paramLl, String paramString1, com.tencent.tauth.l paramL, String paramString2, boolean paramBoolean)
  {
    String str = paramActivity.getApplicationContext().getPackageName();
    paramString2 = paramActivity.getPackageManager();
    StringBuilder localStringBuilder = null;
    try
    {
      Iterator localIterator = paramString2.getInstalledApplications(128).iterator();
      do
      {
        paramString2 = localStringBuilder;
        if (!localIterator.hasNext()) {
          break;
        }
        paramString2 = (ApplicationInfo)localIterator.next();
      } while (!str.equals(paramString2.packageName));
      paramString2 = paramString2.sourceDir;
      if (paramString2 != null)
      {
        paramString2 = com.tencent.open.ll.l.I(new File(paramString2));
        if (!TextUtils.isEmpty(paramString2))
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("-->login channelId: ");
          localStringBuilder.append(paramString2);
          lIl.I("openSDK_LOG.QQAuth", localStringBuilder.toString());
          int i = I(paramActivity, paramString1, paramL, paramString2, paramString2, "");
          return i;
        }
      }
    }
    catch (Throwable paramString2)
    {
      lIl.l("openSDK_LOG.QQAuth", "-->login get channel id exception.", paramString2);
      paramString2.printStackTrace();
      lIl.l("openSDK_LOG.QQAuth", "-->login channelId is null ");
      com.tencent.connect.common.I.lll = false;
    }
    return this.I.I(paramActivity, paramString1, paramL, false, paramLl, paramBoolean);
  }
  
  public static lII I(String paramString, Context paramContext)
  {
    com.tencent.open.ll.ll.I(paramContext.getApplicationContext());
    lIl.lI("openSDK_LOG.QQAuth", "QQAuth -- createInstance() --start");
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
      paramString = new lII(paramString, paramContext);
      lIl.lI("openSDK_LOG.QQAuth", "QQAuth -- createInstance()  --end");
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      lIl.l("openSDK_LOG.QQAuth", "createInstance() error --end", paramString);
      Toast.makeText(paramContext.getApplicationContext(), "请参照文档在Androidmanifest.xml加上AuthActivity和AssitActivity的定义 ", 1).show();
    }
    return null;
  }
  
  public int I(Activity paramActivity, String paramString, com.tencent.tauth.l paramL)
  {
    lIl.lI("openSDK_LOG.QQAuth", "login()");
    return I(paramActivity, paramString, paramL, "");
  }
  
  public int I(Activity paramActivity, String paramString1, com.tencent.tauth.l paramL, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("-->login activity: ");
    localStringBuilder.append(paramActivity);
    lIl.lI("openSDK_LOG.QQAuth", localStringBuilder.toString());
    return I(paramActivity, null, paramString1, paramL, paramString2);
  }
  
  @Deprecated
  public int I(Activity paramActivity, String paramString1, com.tencent.tauth.l paramL, String paramString2, String paramString3, String paramString4)
  {
    lIl.lI("openSDK_LOG.QQAuth", "loginWithOEM");
    com.tencent.connect.common.I.lll = true;
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
    com.tencent.connect.common.I.lIl = paramString2;
    com.tencent.connect.common.I.lII = str;
    com.tencent.connect.common.I.llI = paramString3;
    return this.I.I(paramActivity, paramString1, paramL);
  }
  
  public l I()
  {
    return this.l;
  }
  
  public void I(Context paramContext, String paramString)
  {
    lIl.I("openSDK_LOG.QQAuth", "setOpenId() --start");
    this.l.I(paramString);
    com.tencent.connect.I.I.ll(paramContext, this.l);
    lIl.I("openSDK_LOG.QQAuth", "setOpenId() --end");
  }
  
  public void I(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("setAccessToken(), validTimeInSecond = ");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("");
    lIl.I("openSDK_LOG.QQAuth", localStringBuilder.toString());
    this.l.I(paramString1, paramString2);
  }
  
  public boolean l()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("isSessionValid(), result = ");
    String str;
    if (this.l.I()) {
      str = "true";
    } else {
      str = "false";
    }
    localStringBuilder.append(str);
    localStringBuilder.append("");
    lIl.I("openSDK_LOG.QQAuth", localStringBuilder.toString());
    return this.l.I();
  }
}
