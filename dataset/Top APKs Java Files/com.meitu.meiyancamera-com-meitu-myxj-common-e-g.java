package com.meitu.myxj.common.e;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.meitu.library.util.Debug.Debug;
import com.meitu.myxj.common.bean.CameraPermission;
import com.meitu.myxj.common.bean.CameraPermission.PERMISSION_TYPE;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class g
{
  private static PackageInfo a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((!TextUtils.isEmpty(localPackageInfo.packageName)) && (localPackageInfo.packageName.contains(paramString))) {
        return localPackageInfo;
      }
    }
    return null;
  }
  
  public static ArrayList<CameraPermission> a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    CameraPermission localCameraPermission = c(paramContext);
    if (localCameraPermission != null) {
      localArrayList.add(localCameraPermission);
    }
    localArrayList.addAll(d(paramContext));
    return localArrayList;
  }
  
  public static String b(Context paramContext)
  {
    Object localObject2 = "[";
    Object localObject1 = localObject2;
    label496:
    for (;;)
    {
      try
      {
        Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(0).iterator();
        paramContext = (Context)localObject2;
        localObject1 = paramContext;
        localObject2 = paramContext;
        if (localIterator.hasNext())
        {
          localObject1 = paramContext;
          localObject2 = ((PackageInfo)localIterator.next()).packageName;
          localObject1 = paramContext;
          if (TextUtils.isEmpty((CharSequence)localObject2)) {
            break label496;
          }
          localObject1 = paramContext;
          if (!((String)localObject2).contains("com.qihoo360.mobilesafe"))
          {
            localObject1 = paramContext;
            if (!((String)localObject2).contains("com.qihoo.antivirus")) {}
          }
          else
          {
            localObject1 = paramContext;
            paramContext = paramContext + "1|";
            break label496;
          }
          localObject1 = paramContext;
          if (((String)localObject2).contains("com.tencent.qqpimsecure"))
          {
            localObject1 = paramContext;
            paramContext = paramContext + "2|";
          }
          else
          {
            localObject1 = paramContext;
            if (((String)localObject2).contains("com.lbe.security"))
            {
              localObject1 = paramContext;
              paramContext = paramContext + "3|";
            }
            else
            {
              localObject1 = paramContext;
              if (((String)localObject2).contains("com.lenovo.safecenter"))
              {
                localObject1 = paramContext;
                paramContext = paramContext + "4|";
              }
              else
              {
                localObject1 = paramContext;
                if (((String)localObject2).contains("com.kingroot.master"))
                {
                  localObject1 = paramContext;
                  paramContext = paramContext + "5|";
                }
                else
                {
                  localObject1 = paramContext;
                  if (((String)localObject2).contains("com.findsdk.apppermission"))
                  {
                    localObject1 = paramContext;
                    paramContext = paramContext + "6|";
                  }
                  else
                  {
                    localObject1 = paramContext;
                    if (((String)localObject2).contains("com.huawei.systemmanager"))
                    {
                      localObject1 = paramContext;
                      paramContext = paramContext + "7|";
                    }
                    else
                    {
                      localObject1 = paramContext;
                      if (!((String)localObject2).contains("com.yulong.android.security"))
                      {
                        localObject1 = paramContext;
                        if (!((String)localObject2).contains("com.yulong.android.seccenter")) {}
                      }
                      else
                      {
                        localObject1 = paramContext;
                        paramContext = paramContext + "8|";
                        break label496;
                      }
                      localObject1 = paramContext;
                      if (((String)localObject2).contains("com.iqoo.secure"))
                      {
                        localObject1 = paramContext;
                        paramContext = paramContext + "9|";
                      }
                      else
                      {
                        localObject1 = paramContext;
                        if (((String)localObject2).contains("com.zte.heartyservice"))
                        {
                          localObject1 = paramContext;
                          paramContext = paramContext + "10|";
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      catch (Exception paramContext)
      {
        Debug.c(paramContext);
        localObject2 = localObject1;
        if ((TextUtils.isEmpty((CharSequence)localObject2)) || ("[".equals(localObject2))) {
          return "";
        }
        return ((String)localObject2).substring(0, ((String)localObject2).length() - 1) + "]";
      }
    }
  }
  
  private static CameraPermission c(Context paramContext)
  {
    Object localObject2 = null;
    if ("Meizu".equalsIgnoreCase(Build.MANUFACTURER))
    {
      localObject1 = new CameraPermission();
      ((CameraPermission)localObject1).d = CameraPermission.PERMISSION_TYPE.system;
      ((CameraPermission)localObject1).a = "meizu";
      ((CameraPermission)localObject1).b = paramContext.getString(2131296835);
    }
    do
    {
      do
      {
        do
        {
          return localObject1;
          if ("Xiaomi".equalsIgnoreCase(Build.MANUFACTURER))
          {
            localObject1 = new CameraPermission();
            ((CameraPermission)localObject1).d = CameraPermission.PERMISSION_TYPE.system;
            ((CameraPermission)localObject1).a = "xiaomi";
            ((CameraPermission)localObject1).b = paramContext.getString(2131296838);
            return localObject1;
          }
          if ("huawei".equalsIgnoreCase(Build.MANUFACTURER))
          {
            localObject1 = new CameraPermission();
            ((CameraPermission)localObject1).d = CameraPermission.PERMISSION_TYPE.system;
            ((CameraPermission)localObject1).a = "huawei";
            ((CameraPermission)localObject1).b = paramContext.getString(2131296833);
            return localObject1;
          }
          if ("htc".equalsIgnoreCase(Build.MANUFACTURER))
          {
            localObject1 = new CameraPermission();
            ((CameraPermission)localObject1).d = CameraPermission.PERMISSION_TYPE.system;
            ((CameraPermission)localObject1).a = "htc";
            ((CameraPermission)localObject1).b = paramContext.getString(2131296832);
            return localObject1;
          }
          if ("samsung".equalsIgnoreCase(Build.MANUFACTURER))
          {
            localObject1 = new CameraPermission();
            ((CameraPermission)localObject1).d = CameraPermission.PERMISSION_TYPE.system;
            ((CameraPermission)localObject1).a = "samsung";
            ((CameraPermission)localObject1).b = paramContext.getString(2131296837);
            return localObject1;
          }
          if (!"Meitu".equalsIgnoreCase(Build.MANUFACTURER)) {
            break;
          }
          localObject1 = localObject2;
        } while (a(paramContext, "com.mediatek.security") == null);
        localObject1 = new CameraPermission();
        ((CameraPermission)localObject1).d = CameraPermission.PERMISSION_TYPE.system;
        ((CameraPermission)localObject1).a = "meitu";
        ((CameraPermission)localObject1).b = paramContext.getString(2131296834);
        return localObject1;
        localObject1 = localObject2;
      } while (!"OPPO".equalsIgnoreCase(Build.MANUFACTURER));
      localObject1 = localObject2;
    } while (a(paramContext, "com.coloros.safecenter") == null);
    Object localObject1 = new CameraPermission();
    ((CameraPermission)localObject1).d = CameraPermission.PERMISSION_TYPE.system;
    ((CameraPermission)localObject1).a = "oppo";
    ((CameraPermission)localObject1).b = paramContext.getString(2131296836);
    return localObject1;
  }
  
  private static ArrayList<CameraPermission> d(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageInfo localPackageInfo = a(paramContext, "com.qihoo360.mobilesafe");
    Object localObject;
    CameraPermission localCameraPermission;
    if (localPackageInfo != null)
    {
      localObject = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localCameraPermission = new CameraPermission();
      localCameraPermission.a = "qihoo";
      localCameraPermission.b = ((String)localObject);
      localCameraPermission.d = CameraPermission.PERMISSION_TYPE.app;
      localCameraPermission.c = localPackageInfo.versionCode;
      localArrayList.add(localCameraPermission);
    }
    localPackageInfo = a(paramContext, "com.qihoo.antivirus");
    if (localPackageInfo != null)
    {
      localObject = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localCameraPermission = new CameraPermission();
      localCameraPermission.a = "qihoo";
      localCameraPermission.b = ((String)localObject);
      localCameraPermission.d = CameraPermission.PERMISSION_TYPE.app;
      localCameraPermission.c = localPackageInfo.versionCode;
      localArrayList.add(localCameraPermission);
    }
    localPackageInfo = a(paramContext, "com.lbe.security");
    if (localPackageInfo != null)
    {
      localObject = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localCameraPermission = new CameraPermission();
      localCameraPermission.a = "lbe";
      localCameraPermission.b = ((String)localObject);
      localCameraPermission.d = CameraPermission.PERMISSION_TYPE.app;
      localCameraPermission.c = localPackageInfo.versionCode;
      localArrayList.add(localCameraPermission);
    }
    localPackageInfo = a(paramContext, "com.tencent.qqpimsecure");
    if (localPackageInfo != null)
    {
      localObject = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localCameraPermission = new CameraPermission();
      localCameraPermission.a = "sjgj";
      localCameraPermission.b = ((String)localObject);
      localCameraPermission.d = CameraPermission.PERMISSION_TYPE.app;
      localCameraPermission.c = localPackageInfo.versionCode;
      localArrayList.add(localCameraPermission);
    }
    localPackageInfo = a(paramContext, "com.lenovo.safecenter");
    if (localPackageInfo != null)
    {
      localObject = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localCameraPermission = new CameraPermission();
      localCameraPermission.a = "laq";
      localCameraPermission.b = ((String)localObject);
      localCameraPermission.d = CameraPermission.PERMISSION_TYPE.app;
      localCameraPermission.c = localPackageInfo.versionCode;
      localArrayList.add(localCameraPermission);
    }
    localPackageInfo = a(paramContext, "com.kingroot.master");
    if (localPackageInfo != null)
    {
      localObject = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localCameraPermission = new CameraPermission();
      localCameraPermission.a = "jhds";
      localCameraPermission.b = ((String)localObject);
      Log.e("TEST", "name=" + (String)localObject);
      localCameraPermission.d = CameraPermission.PERMISSION_TYPE.app;
      localCameraPermission.c = localPackageInfo.versionCode;
      localArrayList.add(localCameraPermission);
    }
    localPackageInfo = a(paramContext, "com.yulong.android.security");
    if (localPackageInfo != null)
    {
      localObject = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localCameraPermission = new CameraPermission();
      localCameraPermission.a = "kgj";
      localCameraPermission.b = ((String)localObject);
      localCameraPermission.d = CameraPermission.PERMISSION_TYPE.app;
      localCameraPermission.c = localPackageInfo.versionCode;
      localArrayList.add(localCameraPermission);
    }
    localPackageInfo = a(paramContext, "com.yulong.android.seccenter");
    if (localPackageInfo != null)
    {
      localObject = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localCameraPermission = new CameraPermission();
      localCameraPermission.a = "kgj";
      localCameraPermission.b = ((String)localObject);
      localCameraPermission.d = CameraPermission.PERMISSION_TYPE.app;
      localCameraPermission.c = localPackageInfo.versionCode;
      localArrayList.add(localCameraPermission);
    }
    localPackageInfo = a(paramContext, "com.zte.heartyservice");
    if (localPackageInfo != null)
    {
      localObject = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localCameraPermission = new CameraPermission();
      localCameraPermission.a = "zxgj";
      localCameraPermission.b = ((String)localObject);
      localCameraPermission.d = CameraPermission.PERMISSION_TYPE.app;
      localCameraPermission.c = localPackageInfo.versionCode;
      localArrayList.add(localCameraPermission);
    }
    localPackageInfo = a(paramContext, "com.iqoo.secure");
    if (localPackageInfo != null)
    {
      paramContext = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localObject = new CameraPermission();
      ((CameraPermission)localObject).a = "igj";
      ((CameraPermission)localObject).b = paramContext;
      ((CameraPermission)localObject).d = CameraPermission.PERMISSION_TYPE.app;
      ((CameraPermission)localObject).c = localPackageInfo.versionCode;
      localArrayList.add(localObject);
    }
    return localArrayList;
  }
}
