package com.abercrombie.abercrombie.data.brand;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

public class SocialMediaCommon
{
  static final String PACKAGE_NAME_FACEBOOK = "com.facebook.katana";
  static final String PACKAGE_NAME_INSTAGRAM = "com.instagram.android";
  private final PackageManager packageManager;
  
  @Inject
  public SocialMediaCommon(PackageManager paramPackageManager)
  {
    this.packageManager = paramPackageManager;
  }
  
  private boolean isPackageInstalled(String paramString)
  {
    boolean bool2 = false;
    Object localObject = this.packageManager.getInstalledApplications(0);
    boolean bool1 = bool2;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      do
      {
        bool1 = bool2;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (!((ApplicationInfo)((Iterator)localObject).next()).packageName.equals(paramString));
      bool1 = true;
    }
    return bool1;
  }
  
  public boolean isFacebookInstalled()
  {
    return isPackageInstalled("com.facebook.katana");
  }
  
  public boolean isInstagramInstalled()
  {
    return isPackageInstalled("com.instagram.android");
  }
}
