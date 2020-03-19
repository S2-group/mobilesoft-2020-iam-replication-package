package com.kauf.marketing.ScriptBridge;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class JavaClass
{
  private static final String APP_NAME = "Talking";
  private static final String APP_VERSION = "1.0";
  private Activity mActivity;
  
  public JavaClass(Activity paramActivity)
  {
    this.mActivity = paramActivity;
  }
  
  public String UserParams()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      localStringBuilder.append("&app_id=" + URLEncoder.encode(this.mActivity.getPackageName(), "UTF-8"));
      try
      {
        localStringBuilder.append("&app_la=" + URLEncoder.encode(Locale.getDefault().getLanguage(), "UTF-8"));
        try
        {
          localStringBuilder.append("&app_code=" + this.mActivity.getPackageManager().getPackageInfo(this.mActivity.getPackageName(), 1).versionCode);
          String str = Settings.Secure.getString(this.mActivity.getContentResolver(), "android_id");
          Object localObject = str;
          if (str == null) {
            localObject = "";
          }
          try
          {
            localStringBuilder.append("&user_id=" + URLEncoder.encode((String)localObject, "UTF-8"));
            try
            {
              localStringBuilder.append("&udid=" + URLEncoder.encode((String)localObject, "UTF-8"));
              try
              {
                localStringBuilder.append("&vers_incr=" + URLEncoder.encode(Build.VERSION.INCREMENTAL, "UTF-8"));
                try
                {
                  localStringBuilder.append("&vers_rel=" + URLEncoder.encode(Build.VERSION.RELEASE, "UTF-8"));
                  try
                  {
                    localStringBuilder.append("&vers_sdk=" + URLEncoder.encode(String.valueOf(Build.VERSION.SDK_INT), "UTF-8"));
                    try
                    {
                      localStringBuilder.append("&build_board=" + URLEncoder.encode(Build.BOARD, "UTF-8"));
                      try
                      {
                        localStringBuilder.append("&build_brand=" + URLEncoder.encode(Build.BRAND, "UTF-8"));
                        try
                        {
                          localStringBuilder.append("&build_dev=" + URLEncoder.encode(Build.DEVICE, "UTF-8"));
                          try
                          {
                            localStringBuilder.append("&build_display=" + URLEncoder.encode(Build.DISPLAY, "UTF-8"));
                            try
                            {
                              localStringBuilder.append("&build_fingerprint=" + URLEncoder.encode(Build.FINGERPRINT, "UTF-8"));
                              try
                              {
                                localStringBuilder.append("&build_host=" + URLEncoder.encode(Build.HOST, "UTF-8"));
                                try
                                {
                                  localStringBuilder.append("&build_id=" + URLEncoder.encode(Build.ID, "UTF-8"));
                                  try
                                  {
                                    localStringBuilder.append("&build_model=" + URLEncoder.encode(Build.MODEL, "UTF-8"));
                                    try
                                    {
                                      localStringBuilder.append("&build_product=" + URLEncoder.encode(Build.PRODUCT, "UTF-8"));
                                      try
                                      {
                                        localStringBuilder.append("&build_tags=" + URLEncoder.encode(Build.TAGS, "UTF-8"));
                                        try
                                        {
                                          localStringBuilder.append("&build_time=" + URLEncoder.encode(String.valueOf(Build.TIME), "UTF-8"));
                                          try
                                          {
                                            localStringBuilder.append("&build_type=" + URLEncoder.encode(Build.TYPE, "UTF-8"));
                                            try
                                            {
                                              localStringBuilder.append("&build_user=" + URLEncoder.encode(Build.USER, "UTF-8"));
                                              localStringBuilder.append("&disp_ver=2");
                                              localObject = new DisplayMetrics();
                                              this.mActivity.getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
                                              localStringBuilder.append("&dev_width=" + ((DisplayMetrics)localObject).widthPixels);
                                              localStringBuilder.append("&dev_height=" + ((DisplayMetrics)localObject).heightPixels);
                                              localStringBuilder.append("&dev_density=" + ((DisplayMetrics)localObject).density);
                                              return localStringBuilder.toString();
                                            }
                                            catch (UnsupportedEncodingException localUnsupportedEncodingException1)
                                            {
                                              for (;;) {}
                                            }
                                          }
                                          catch (UnsupportedEncodingException localUnsupportedEncodingException2)
                                          {
                                            for (;;) {}
                                          }
                                        }
                                        catch (UnsupportedEncodingException localUnsupportedEncodingException3)
                                        {
                                          for (;;) {}
                                        }
                                      }
                                      catch (UnsupportedEncodingException localUnsupportedEncodingException4)
                                      {
                                        for (;;) {}
                                      }
                                    }
                                    catch (UnsupportedEncodingException localUnsupportedEncodingException5)
                                    {
                                      for (;;) {}
                                    }
                                  }
                                  catch (UnsupportedEncodingException localUnsupportedEncodingException6)
                                  {
                                    for (;;) {}
                                  }
                                }
                                catch (UnsupportedEncodingException localUnsupportedEncodingException7)
                                {
                                  for (;;) {}
                                }
                              }
                              catch (UnsupportedEncodingException localUnsupportedEncodingException8)
                              {
                                for (;;) {}
                              }
                            }
                            catch (UnsupportedEncodingException localUnsupportedEncodingException9)
                            {
                              for (;;) {}
                            }
                          }
                          catch (UnsupportedEncodingException localUnsupportedEncodingException10)
                          {
                            for (;;) {}
                          }
                        }
                        catch (UnsupportedEncodingException localUnsupportedEncodingException11)
                        {
                          for (;;) {}
                        }
                      }
                      catch (UnsupportedEncodingException localUnsupportedEncodingException12)
                      {
                        for (;;) {}
                      }
                    }
                    catch (UnsupportedEncodingException localUnsupportedEncodingException13)
                    {
                      for (;;) {}
                    }
                  }
                  catch (UnsupportedEncodingException localUnsupportedEncodingException14)
                  {
                    for (;;) {}
                  }
                }
                catch (UnsupportedEncodingException localUnsupportedEncodingException15)
                {
                  for (;;) {}
                }
              }
              catch (UnsupportedEncodingException localUnsupportedEncodingException16)
              {
                for (;;) {}
              }
            }
            catch (UnsupportedEncodingException localUnsupportedEncodingException17)
            {
              for (;;) {}
            }
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException20)
          {
            for (;;) {}
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;) {}
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException18)
      {
        for (;;) {}
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException19)
    {
      for (;;) {}
    }
  }
  
  public String getActivityCacheDir()
  {
    return this.mActivity.getCacheDir().getPath();
  }
  
  public String getOwnAppsInstalled()
  {
    Object localObject = this.mActivity.getBaseContext();
    StringBuilder localStringBuilder = new StringBuilder();
    localObject = ((Context)localObject).getPackageManager().getInstalledApplications(128).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (localApplicationInfo.packageName.startsWith("com.kauf.")) {
        localStringBuilder.append(localApplicationInfo.packageName + "|");
      }
    }
    return localStringBuilder.toString();
  }
}
