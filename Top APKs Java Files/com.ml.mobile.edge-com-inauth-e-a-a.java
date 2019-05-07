package com.inauth.e.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.inauth.ndk.InAuthNDK;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class a
{
  private static a a;
  
  private a() {}
  
  public static a a()
  {
    if (a == null) {
      a = new a();
    }
    return a;
  }
  
  private com.inauth.ndk.a a(String paramString1, String paramString2)
  {
    int i = 0;
    int j;
    if (paramString1 != null)
    {
      paramString1 = InAuthNDK.a().a(paramString1);
      j = InAuthNDK.a().b();
      i = j;
      if (paramString1 != null) {
        if ("GET_SIGFILE_VERSION".equals(paramString2))
        {
          paramString1 = b(paramString1);
          i = j;
        }
      }
    }
    for (;;)
    {
      paramString2 = new com.inauth.ndk.a();
      paramString2.a(paramString1);
      paramString2.a(i);
      return paramString2;
      i = j;
      if (!"GET_ROOT_LIST".equals(paramString2))
      {
        i = j;
        paramString1 = "COMPROMISED";
      }
    }
  }
  
  private List<String> a(Application paramApplication, String[] paramArrayOfString)
  {
    paramApplication = paramApplication.getApplicationContext().getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      int k = paramApplication.size();
      int j = 0;
      while (j < k)
      {
        if (((PackageInfo)paramApplication.get(j)).packageName.equals(paramArrayOfString[i])) {
          localArrayList.add(paramArrayOfString[i]);
        }
        j += 1;
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private boolean a(Application paramApplication, List<String> paramList)
  {
    paramApplication = ((ActivityManager)paramApplication.getSystemService("activity")).getRunningAppProcesses();
    int k = paramList.size();
    int i = 1;
    boolean bool = false;
    if (i < k)
    {
      String str = (String)paramList.get(i);
      int j = 0;
      label47:
      if (j < paramApplication.size()) {
        if (((ActivityManager.RunningAppProcessInfo)paramApplication.get(j)).processName.equals(str)) {
          bool = true;
        }
      }
      for (;;)
      {
        if (bool)
        {
          return bool;
          j += 1;
          break label47;
        }
        i += 1;
        break;
      }
    }
    return bool;
  }
  
  private boolean a(Application paramApplication, List<String> paramList1, List<String> paramList2, List<String> paramList3, boolean paramBoolean, String paramString)
  {
    boolean bool = false;
    int j = 0;
    com.a.a.a.a = false;
    paramString = paramString.split("#####");
    Object localObject1 = paramString[2].split("(\\r|\\n)");
    Object localObject3 = paramString[3].split("(\\r|\\n)");
    Object localObject2 = paramString[4].split("(\\r|\\n)");
    paramString = paramString[5].split("(\\r|\\n)");
    localObject1 = localObject1[1];
    localObject3 = localObject3[1];
    localObject2 = localObject2[1];
    String str = paramString[1];
    int i;
    if (paramBoolean)
    {
      if (!com.a.a.a.c())
      {
        paramBoolean = bool;
        if (!com.a.a.a.b()) {}
      }
      else
      {
        paramBoolean = true;
      }
      if (System.getProperty("os.version").indexOf("cyanogen") != -1) {
        paramBoolean = true;
      }
      if (System.getProperty("os.version").indexOf("-CM-") != -1) {
        paramBoolean = true;
      }
      if (Build.PRODUCT.contains("cm_")) {
        paramBoolean = true;
      }
      if (System.getProperty("os.version").indexOf("purity") != -1) {
        paramBoolean = true;
      }
      if (Build.USER.indexOf("paranoid") != -1) {
        paramBoolean = true;
      }
      if (Build.PRODUCT.indexOf("omni") != -1) {
        paramBoolean = true;
      }
      if (Build.PRODUCT.indexOf("du_") != -1) {
        paramBoolean = true;
      }
      if (Build.HOST.contains("nychitman")) {
        paramBoolean = true;
      }
      PackageManager localPackageManager = paramApplication.getPackageManager();
      if (localPackageManager.hasSystemFeature("com.cyanogenmod.android")) {
        paramBoolean = true;
      }
      if (localPackageManager.hasSystemFeature("com.carbon.android")) {
        paramBoolean = true;
      }
      if (System.getProperty("os.version").indexOf("qemu") != -1) {
        paramBoolean = true;
      }
      bool = paramBoolean;
      if (!paramBoolean)
      {
        if (!paramList2.isEmpty())
        {
          i = 0 + Integer.parseInt((String)localObject1);
          j = i;
          if (a(paramApplication, paramList2)) {
            j = i + 3;
          }
        }
        if (j <= 5) {
          break label360;
        }
        bool = true;
      }
    }
    label360:
    do
    {
      return bool;
      paramBoolean = bool;
      if (!b()) {
        break;
      }
      paramBoolean = bool;
      break;
      i = j;
      if (!paramList1.isEmpty())
      {
        j += Integer.parseInt((String)localObject3);
        i = j;
        if (a(paramApplication, paramList1)) {
          i = j + 3;
        }
      }
      if (i > 5) {
        return true;
      }
      j = i;
      if (!paramList3.isEmpty())
      {
        i += Integer.parseInt((String)localObject2);
        j = i;
        if (a(paramApplication, paramList3)) {
          j = i + 2;
        }
      }
      if (j > 5) {
        return true;
      }
      if (j > 5) {
        return true;
      }
      i = j;
      if (a(paramString)) {
        i = j + Integer.parseInt(str);
      }
      bool = paramBoolean;
    } while (i <= 5);
    return true;
  }
  
  private boolean a(String[] paramArrayOfString)
  {
    int i = 1;
    for (;;)
    {
      if (i < paramArrayOfString.length)
      {
        File localFile = new File(paramArrayOfString[i]);
        if ((!localFile.exists()) && ((localFile.list() == null) || (!localFile.canRead()) || (!localFile.canWrite()))) {}
      }
      else
      {
        return false;
      }
      i += 1;
    }
  }
  
  private String b(String paramString)
  {
    return paramString.split("#####")[0].split("(\\r|\\n)")[0];
  }
  
  private boolean b()
  {
    for (;;)
    {
      try
      {
        Object localObject = Class.forName("android.os.SystemProperties");
        Method localMethod = ((Class)localObject).getMethod("get", new Class[] { String.class });
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = new String("ro.product.manufacturer");
        arrayOfObject[1] = new String("ro.build.version.release");
        arrayOfObject[2] = new String("ro.build.version.sdk");
        String str = (String)localMethod.invoke(localObject, new Object[] { arrayOfObject[0] });
        localObject = (String)localMethod.invoke(localObject, new Object[] { arrayOfObject[2] });
        if (str.equalsIgnoreCase("zte"))
        {
          bool = ((String)localObject).equalsIgnoreCase("10");
          if (bool)
          {
            bool = true;
            return bool;
          }
        }
      }
      catch (Exception localException)
      {
        return false;
      }
      catch (SecurityException localSecurityException)
      {
        return false;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        return false;
      }
      boolean bool = false;
    }
  }
  
  private boolean c(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString != null)
    {
      paramString = new File(paramString);
      bool1 = bool2;
      if (paramString.exists())
      {
        bool1 = bool2;
        if (paramString.canRead()) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public b a(Application paramApplication, String paramString, boolean paramBoolean)
  {
    Object localObject1 = "COMPROMISED";
    ArrayList localArrayList = new ArrayList();
    if (paramApplication != null)
    {
      if (!c(paramString)) {
        break label275;
      }
      localObject1 = a(paramString, "GET_ROOT_LIST");
      if (localObject1 == null) {
        break label268;
      }
      paramString = ((com.inauth.ndk.a)localObject1).b();
      if ((((com.inauth.ndk.a)localObject1).a() == null) || ("COMPROMISED".equals(((com.inauth.ndk.a)localObject1).a()))) {
        break label262;
      }
      Object localObject3 = ((com.inauth.ndk.a)localObject1).a().split("#####");
      Object localObject2 = localObject3[2].split("(\\r|\\n)");
      Object localObject4 = localObject3[3].split("(\\r|\\n)");
      localObject3 = localObject3[4].split("(\\r|\\n)");
      localObject4 = a(paramApplication, (String[])localObject4);
      localObject2 = a(paramApplication, (String[])localObject2);
      localObject3 = a(paramApplication, (String[])localObject3);
      localArrayList.addAll((Collection)localObject3);
      localArrayList.addAll((Collection)localObject4);
      localArrayList.addAll((Collection)localObject2);
      if (a(paramApplication, (List)localObject4, (List)localObject2, (List)localObject3, paramBoolean, ((com.inauth.ndk.a)localObject1).a())) {
        paramApplication = "DEVICE_ROOTED";
      }
    }
    for (;;)
    {
      localObject1 = new b();
      ((b)localObject1).a(paramApplication);
      ((b)localObject1).b(paramString);
      ((b)localObject1).a(localArrayList);
      return localObject1;
      if ((localArrayList != null) && (!localArrayList.isEmpty()))
      {
        paramApplication = "ROOT_APPLICATIONS_INSTALLED";
      }
      else
      {
        paramApplication = "DEVICE_NOT_ROOTED";
        continue;
        paramString = "SDK_NOT_INITIALIZED";
        paramApplication = (Application)localObject1;
        continue;
        label262:
        paramApplication = "COMPROMISED";
        continue;
        label268:
        paramString = "MISSING_SIGFILE";
        break;
        label275:
        paramString = "MISSING_SIGFILE";
        paramApplication = (Application)localObject1;
      }
    }
  }
  
  public String a(String paramString)
  {
    String str2 = "MISSING_SIGFILE";
    String str1 = str2;
    if (c(paramString))
    {
      paramString = a(paramString, "GET_SIGFILE_VERSION");
      str1 = str2;
      if (paramString.a() != null) {
        str1 = paramString.a();
      }
    }
    return str1;
  }
}
