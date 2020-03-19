package com.kauf.marketing;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class UserInfos
{
  public static String googlePlayServicesAdId = "";
  public static boolean googlePlayServicesAdLimitTracking = false;
  
  public UserInfos() {}
  
  @SuppressLint({"NewApi"})
  public static StringBuilder UserParams(Activity paramActivity)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      localStringBuilder.append("&gps_adid=" + URLEncoder.encode(googlePlayServicesAdId, Charset.defaultCharset().name()));
      localStringBuilder.append("&gps_adlt=" + googlePlayServicesAdLimitTracking);
      try
      {
        localStringBuilder.append("&app_name=" + URLEncoder.encode(paramActivity.getTitle().toString().replace(" ", ""), Charset.defaultCharset().name()));
        try
        {
          localStringBuilder.append("&app_id=" + URLEncoder.encode(paramActivity.getPackageName(), Charset.defaultCharset().name()));
          try
          {
            localStringBuilder.append("&app_la=" + URLEncoder.encode(Locale.getDefault().getLanguage(), Charset.defaultCharset().name()));
            try
            {
              localStringBuilder.append("&app_ver=" + URLEncoder.encode(getVersionName(paramActivity), Charset.defaultCharset().name()));
              try
              {
                localStringBuilder.append("&app_code=" + paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 1).versionCode);
                Object localObject2 = Settings.Secure.getString(paramActivity.getContentResolver(), "android_id");
                Object localObject1 = localObject2;
                if (localObject2 == null) {
                  localObject1 = "";
                }
                try
                {
                  localStringBuilder.append("&user_id=" + URLEncoder.encode((String)localObject1, Charset.defaultCharset().name()));
                  try
                  {
                    localStringBuilder.append("&udid=" + URLEncoder.encode((String)localObject1, Charset.defaultCharset().name()));
                    try
                    {
                      localStringBuilder.append("&vers_incr=" + URLEncoder.encode(Build.VERSION.INCREMENTAL, Charset.defaultCharset().name()));
                      try
                      {
                        localStringBuilder.append("&vers_rel=" + URLEncoder.encode(Build.VERSION.RELEASE, Charset.defaultCharset().name()));
                        try
                        {
                          localStringBuilder.append("&vers_sdk=" + URLEncoder.encode(String.valueOf(Build.VERSION.SDK_INT), Charset.defaultCharset().name()));
                          try
                          {
                            localStringBuilder.append("&build_board=" + URLEncoder.encode(Build.BOARD, Charset.defaultCharset().name()));
                            try
                            {
                              localStringBuilder.append("&build_brand=" + URLEncoder.encode(Build.BRAND, Charset.defaultCharset().name()));
                              try
                              {
                                localStringBuilder.append("&build_dev=" + URLEncoder.encode(Build.DEVICE, Charset.defaultCharset().name()));
                                try
                                {
                                  localStringBuilder.append("&build_display=" + URLEncoder.encode(Build.DISPLAY, Charset.defaultCharset().name()));
                                  try
                                  {
                                    localStringBuilder.append("&build_fingerprint=" + URLEncoder.encode(Build.FINGERPRINT, Charset.defaultCharset().name()));
                                    try
                                    {
                                      localStringBuilder.append("&build_host=" + URLEncoder.encode(Build.HOST, Charset.defaultCharset().name()));
                                      try
                                      {
                                        localStringBuilder.append("&build_id=" + URLEncoder.encode(Build.ID, Charset.defaultCharset().name()));
                                        try
                                        {
                                          localStringBuilder.append("&build_model=" + URLEncoder.encode(Build.MODEL, Charset.defaultCharset().name()));
                                          try
                                          {
                                            localStringBuilder.append("&build_product=" + URLEncoder.encode(Build.PRODUCT, Charset.defaultCharset().name()));
                                            try
                                            {
                                              localStringBuilder.append("&build_tags=" + URLEncoder.encode(Build.TAGS, Charset.defaultCharset().name()));
                                              try
                                              {
                                                localStringBuilder.append("&build_time=" + URLEncoder.encode(String.valueOf(Build.TIME), Charset.defaultCharset().name()));
                                                try
                                                {
                                                  localStringBuilder.append("&build_type=" + URLEncoder.encode(Build.TYPE, Charset.defaultCharset().name()));
                                                  try
                                                  {
                                                    localStringBuilder.append("&build_user=" + URLEncoder.encode(Build.USER, Charset.defaultCharset().name()));
                                                    localStringBuilder.append("&disp_ver=2");
                                                    localObject1 = new DisplayMetrics();
                                                    paramActivity.getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject1);
                                                    localStringBuilder.append("&dev_width=" + ((DisplayMetrics)localObject1).widthPixels);
                                                    localStringBuilder.append("&dev_height=" + ((DisplayMetrics)localObject1).heightPixels);
                                                    if (Build.VERSION.SDK_INT >= 17)
                                                    {
                                                      localObject2 = new DisplayMetrics();
                                                      paramActivity.getWindowManager().getDefaultDisplay().getRealMetrics((DisplayMetrics)localObject2);
                                                      localStringBuilder.append("&dev_real_width=" + ((DisplayMetrics)localObject2).widthPixels);
                                                      localStringBuilder.append("&dev_real_height=" + ((DisplayMetrics)localObject2).heightPixels);
                                                    }
                                                    localStringBuilder.append("&dev_density=" + ((DisplayMetrics)localObject1).density);
                                                    paramActivity = localStringBuilder;
                                                    if (localStringBuilder.length() > 0) {
                                                      paramActivity = localStringBuilder.deleteCharAt(0);
                                                    }
                                                    return paramActivity;
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
                catch (UnsupportedEncodingException localUnsupportedEncodingException23)
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
        catch (UnsupportedEncodingException localUnsupportedEncodingException20)
        {
          for (;;) {}
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException21)
      {
        for (;;) {}
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException22)
    {
      for (;;) {}
    }
  }
  
  public static String getOwnAppsInstalled(Activity paramActivity)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      try
      {
        paramActivity = paramActivity.getPackageManager().getInstalledApplications(128).iterator();
        boolean bool = paramActivity.hasNext();
        if (bool) {
          continue;
        }
      }
      catch (Exception paramActivity)
      {
        ApplicationInfo localApplicationInfo;
        continue;
      }
      return localStringBuilder.toString();
      localApplicationInfo = (ApplicationInfo)paramActivity.next();
      if (localApplicationInfo.packageName.startsWith("com.wonderfulgames.")) {
        localStringBuilder.append(localApplicationInfo.packageName + "|");
      }
      if (localApplicationInfo.packageName.startsWith("com.kauf.")) {
        localStringBuilder.append(localApplicationInfo.packageName + "|");
      }
    }
  }
  
  public static String getUserInfo(Activity paramActivity)
  {
    return UserParams(paramActivity).toString();
  }
  
  public static String getVersionName(Activity paramActivity)
  {
    try
    {
      paramActivity = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0).versionName;
      return paramActivity;
    }
    catch (PackageManager.NameNotFoundException paramActivity) {}
    return "0.0";
  }
  
  public void setGooglePlayServicesAdId(String paramString)
  {
    googlePlayServicesAdId = paramString;
  }
  
  public void setGooglePlayServicesAdLimitTracking(boolean paramBoolean)
  {
    googlePlayServicesAdLimitTracking = paramBoolean;
  }
}
