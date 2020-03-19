package com.lafonapps.common.feedback;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.List;

public class JumpContactOperation
{
  private static final String QQ_PACKAGENAME = "com.tencent.mobileqq";
  private static String TARGET_EMAIL = "";
  private static String TARGET_QQ_NUM = "";
  private Context mContext;
  
  public JumpContactOperation(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public static void SetEmail(String paramString)
  {
    TARGET_EMAIL = paramString;
  }
  
  public static void SetQQ(String paramString)
  {
    TARGET_QQ_NUM = paramString;
  }
  
  public static boolean installQQ(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext == null) {
      return false;
    }
    int i = 0;
    while (i < paramContext.size())
    {
      if (((PackageInfo)paramContext.get(i)).packageName.equals("com.tencent.mobileqq")) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public void jumpEmail()
  {
    jumpEmail(TARGET_EMAIL);
  }
  
  public void jumpEmail(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:test@text.com"));
    localIntent.putExtra("android.intent.extra.CC", new String[] { paramString });
    paramString = KeyInformation.getInstance(this.mContext);
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString.getAppName() + "<" + paramString.getAppVersionCode() + ", " + paramString.getAppVersionName() + ">");
    this.mContext.startActivity(Intent.createChooser(localIntent, "请选择邮件类应用"));
  }
  
  public void jumpQQ()
  {
    jumpQQ(TARGET_QQ_NUM);
  }
  
  public void jumpQQ(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + paramString));
    this.mContext.startActivity(paramString);
  }
}
