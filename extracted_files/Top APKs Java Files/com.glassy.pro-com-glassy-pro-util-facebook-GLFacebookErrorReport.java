package com.glassy.pro.util.facebook;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.glassy.pro.MyApplication;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public class GLFacebookErrorReport
{
  private static final String FB_PACKAGE_NAME = "com.facebook";
  private String className = "";
  private String comment = "";
  private String exceptionMessage = "";
  private String exceptionStackTrace = "";
  private String facebookAppVersion = "";
  private String sessionDeclinedPermissions = "";
  private String sessionPermissions = "";
  private String sessionRequestedPermissions = "";
  private String sessionState = "";
  private String sessionToken = "";
  private String sessionTokenExpires;
  
  public GLFacebookErrorReport(Exception paramException, Context paramContext, String paramString)
  {
    addGlassyInfo(paramContext, paramString);
    addFacebookAppVersion();
    addExceptionParams(paramException);
    addSessionParams();
  }
  
  private void addExceptionParams(Exception paramException)
  {
    if (paramException != null)
    {
      this.exceptionMessage = paramException.getMessage();
      this.exceptionStackTrace = printStackTrace(paramException);
    }
  }
  
  private void addFacebookAppVersion()
  {
    Object localObject = MyApplication.getContext().getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    while (i < ((List)localObject).size())
    {
      PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
      if (localPackageInfo.packageName.contains("com.facebook"))
      {
        this.facebookAppVersion = localPackageInfo.versionName;
        return;
      }
      i += 1;
    }
    this.facebookAppVersion = "NO FACEBOOK APP FOUND";
  }
  
  private void addGlassyInfo(Context paramContext, String paramString)
  {
    this.className = paramContext.getClass().getCanonicalName();
    this.comment = paramString;
  }
  
  private void addSessionParams() {}
  
  private String printStackTrace(Exception paramException)
  {
    StringWriter localStringWriter = new StringWriter();
    paramException.printStackTrace(new PrintWriter(localStringWriter));
    return localStringWriter.toString();
  }
}
