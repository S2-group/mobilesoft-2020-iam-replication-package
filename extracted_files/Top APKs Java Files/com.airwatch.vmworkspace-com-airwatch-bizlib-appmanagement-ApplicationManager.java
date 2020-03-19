package com.airwatch.bizlib.appmanagement;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.airwatch.bizlib.d.a;
import com.airwatch.util.c;
import com.airwatch.util.k;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class ApplicationManager
{
  private static final HashMap<String, String> d = new HashMap();
  protected com.airwatch.bizlib.c.e a;
  protected Context b;
  private a c;
  
  public ApplicationManager(Context paramContext, com.airwatch.bizlib.c.e paramE)
  {
    this.b = paramContext;
    this.a = paramE;
    this.c = a.a(paramContext);
  }
  
  public static String[] h()
  {
    return c.a();
  }
  
  public static boolean m(String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {}
    for (;;)
    {
      return false;
      String[] arrayOfString = h();
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (arrayOfString[i].equalsIgnoreCase(paramString)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  private String o(String paramString)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    paramString = paramString.split("\\.");
    int i1 = paramString.length;
    int j = 0;
    int i = 0;
    if (j < i1)
    {
      byte[] arrayOfByte = paramString[j];
      i += 1;
      StringBuilder localStringBuilder2 = new StringBuilder();
      arrayOfByte = arrayOfByte.getBytes();
      int i2 = arrayOfByte.length;
      int k = 0;
      int n;
      for (int m = 0; k < i2; m = n)
      {
        int i3 = arrayOfByte[k];
        if ((i3 < 48) || (i3 > 57)) {
          break;
        }
        if (m == 0)
        {
          n = m;
          if (i3 < 49) {}
        }
        else
        {
          localStringBuilder2.append((char)i3);
          n = m + 1;
        }
        k += 1;
      }
      if (localStringBuilder1.length() != 0) {
        localStringBuilder1.append(".");
      }
      if (localStringBuilder2.length() != 0)
      {
        localStringBuilder1.append(localStringBuilder2.toString());
        label173:
        if (i != 3) {
          break label218;
        }
      }
    }
    for (;;)
    {
      if (i < 3)
      {
        j = 0;
        for (;;)
        {
          if (j < 3 - i)
          {
            localStringBuilder1.append(".0");
            j += 1;
            continue;
            localStringBuilder1.append("0");
            break label173;
            label218:
            j += 1;
            break;
          }
        }
      }
      return localStringBuilder1.toString();
    }
  }
  
  public String a(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString1.length() == 0))
    {
      k.d("Download failed!  Download URL is missing.");
      return "";
    }
    if ((paramString2 == null) || (paramString2.length() == 0))
    {
      k.d("Download failed!  Package identifier is missing.");
      return "";
    }
    if ((paramString3 == null) || (paramString3.length() == 0))
    {
      k.d("Download failed!  User agent is missing.");
      return "";
    }
    paramString1 = new e(paramString1, paramString2);
    k.a("Downloading " + paramString2 + "...");
    if (paramString1.a(this.b, paramString3))
    {
      if (paramString1.b() != 200) {
        return "";
      }
      if (a(paramString1.a(), paramString2))
      {
        k.a("Download complete. Preparing to install.");
        return paramString1.a();
      }
      k.a("Package already installed.  Removing apk.");
      paramString1 = new File(paramString1.a());
      if (paramString1.exists()) {
        paramString1.delete();
      }
      return "skip";
    }
    k.d("Download " + paramString2 + " failed!");
    return "";
  }
  
  public abstract void a(String[] paramArrayOfString);
  
  public abstract boolean a(String paramString);
  
  public boolean a(String paramString1, String paramString2)
  {
    boolean bool2 = false;
    boolean bool1;
    if ((paramString2 == null) || (paramString2.length() == 0)) {
      bool1 = true;
    }
    int i;
    int j;
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (paramString1 == null);
        bool1 = bool2;
      } while (paramString1.length() == 0);
      i = i(paramString2);
      j = h(paramString1);
      if (i < 0) {
        break;
      }
      bool1 = bool2;
    } while (j <= i);
    return true;
  }
  
  public boolean a(String paramString1, String paramString2, Context paramContext)
  {
    boolean bool2 = false;
    Object localObject = this.a.a(paramString1);
    boolean bool1 = bool2;
    if (localObject != null)
    {
      bool1 = bool2;
      if (((ApplicationInformation)localObject).k().equalsIgnoreCase(paramString2))
      {
        bool1 = bool2;
        if (((ApplicationInformation)localObject).b() != ApplicationInformation.ApplicationState.d) {}
      }
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager();
        localObject = paramContext.getInstalledApplications(128).iterator();
        if (((Iterator)localObject).hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
          if (!localApplicationInfo.packageName.equals(paramString1)) {
            continue;
          }
          bool1 = o(paramContext.getPackageInfo(localApplicationInfo.packageName, 0).versionName).equals(paramString2);
          if (bool1)
          {
            bool1 = true;
            return bool1;
          }
        }
      }
      catch (Exception paramString1)
      {
        k.d("Exception occurred while verifying applications with package manager", paramString1);
        return false;
      }
      bool1 = false;
    }
  }
  
  public abstract boolean a(List<String> paramList, boolean paramBoolean);
  
  public long b(String paramString1, String paramString2)
  {
    return this.a.b(paramString1, paramString2);
  }
  
  public abstract void b();
  
  public abstract boolean b(String paramString);
  
  public abstract void c();
  
  public abstract boolean c(String paramString);
  
  public boolean d()
  {
    return true;
  }
  
  public abstract boolean d(String paramString);
  
  public List<ApplicationInformation> e()
  {
    return this.a.a();
  }
  
  public boolean e(String paramString)
  {
    return true;
  }
  
  public List<ApplicationInformation> f()
  {
    return this.a.b();
  }
  
  public boolean f(String paramString)
  {
    k.f("Compare packageNAme with Managed Apps list");
    Object localObject = e();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        if (((ApplicationInformation)((Iterator)localObject).next()).f().equals(paramString)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public ApplicationInformation g(String paramString)
  {
    k.f("ApplicationManager queryAppData");
    return this.a.a(paramString);
  }
  
  public boolean g()
  {
    return false;
  }
  
  public int h(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    do
    {
      PackageManager localPackageManager;
      do
      {
        return -1;
        localPackageManager = this.b.getPackageManager();
        paramString = new File(paramString);
      } while (!paramString.exists());
      paramString = localPackageManager.getPackageArchiveInfo(paramString.getAbsolutePath(), 0);
    } while (paramString == null);
    return paramString.versionCode;
  }
  
  public int i(String paramString)
  {
    int i = -1;
    Object localObject = this.b.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(paramString, 0);
      if (localObject != null) {
        i = ((PackageInfo)localObject).versionCode;
      }
      return i;
    }
    catch (Exception localException)
    {
      k.a(paramString + " not installed.");
    }
    return -1;
  }
  
  public com.airwatch.bizlib.c.e i()
  {
    return this.a;
  }
  
  public boolean j(String paramString)
  {
    return false;
  }
  
  public boolean k(String paramString)
  {
    return false;
  }
  
  public boolean l(String paramString)
  {
    return true;
  }
  
  public List<String> n(String paramString)
  {
    return this.a.a("packagename", "appvpnuuid", paramString);
  }
}
