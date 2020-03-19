import android.app.enterprise.ApplicationPolicy;
import android.app.enterprise.EnterpriseDeviceManager;
import com.fiberlink.maas360.android.control.ControlApplication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class cdn
{
  private static final String a = cnr.a(cdn.class);
  private EnterpriseDeviceManager b;
  private ControlApplication c;
  
  public cdn(EnterpriseDeviceManager paramEnterpriseDeviceManager)
  {
    this.b = paramEnterpriseDeviceManager;
    this.c = ControlApplication.b();
  }
  
  public ArrayList<String> a(boolean paramBoolean)
  {
    localArrayList = new ArrayList();
    Object localObject = this.b.getApplicationPolicy();
    try
    {
      localObject = ((ApplicationPolicy)localObject).getApplicationStateList(paramBoolean);
      if ((localObject != null) && (localObject.length > 0))
      {
        localObject = Arrays.asList((Object[])localObject);
        if (localObject != null)
        {
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext()) {
            localArrayList.add((String)((Iterator)localObject).next());
          }
        }
      }
      return localArrayList;
    }
    catch (SecurityException localSecurityException)
    {
      dho.e(a, localSecurityException, new String[] { "SecurityException: " });
    }
  }
  
  public boolean a(String paramString)
  {
    ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
    try
    {
      boolean bool = localApplicationPolicy.addAppPackageNameToBlackList(paramString);
      return bool;
    }
    catch (SecurityException paramString)
    {
      dho.e(a, paramString, new String[] { "SecurityException: " });
    }
    return false;
  }
  
  public boolean a(String paramString1, String paramString2)
  {
    try
    {
      bool = this.b.getApplicationPolicy().startApp(paramString1, paramString2);
      if (!bool) {}
      try
      {
        dho.d(a, new String[] { "Unable to start application : ", paramString1 });
        return bool;
      }
      catch (SecurityException paramString2) {}
      dho.b(a, new String[] { "Successfully started application : ", paramString1 });
      return bool;
    }
    catch (SecurityException paramString2)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    dho.e(a, paramString2, new String[] { "SecurityException while startApp :  " + paramString1 });
    return bool;
  }
  
  public boolean a(String paramString, boolean paramBoolean)
  {
    ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
    try
    {
      paramBoolean = localApplicationPolicy.installApplication(paramString, paramBoolean);
      if (!paramBoolean) {}
      try
      {
        dho.d(a, new String[] { "Install Application package Failed" });
        return paramBoolean;
      }
      catch (SecurityException paramString) {}
      dho.d(a, new String[] { "Install Application package Succeeded" });
      return paramBoolean;
    }
    catch (SecurityException paramString)
    {
      for (;;)
      {
        paramBoolean = false;
      }
    }
    dho.e(a, paramString, new String[] { "SecurityException: " });
    return paramBoolean;
  }
  
  public boolean a(List<String> paramList)
  {
    try
    {
      bool = this.b.getApplicationPolicy().addPackagesToForceStopBlackList(paramList);
      if (!bool) {}
      try
      {
        dho.d(a, new String[] { "Adding Application package to forceStop blacklist failed" });
        return bool;
      }
      catch (SecurityException paramList) {}
      dho.d(a, new String[] { "Adding Application package to forceStop blacklist succeeded" });
      return bool;
    }
    catch (SecurityException paramList)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    dho.e(a, paramList, new String[] { "SecurityException: " });
    return bool;
  }
  
  public String[] a()
  {
    Object localObject = this.b.getApplicationPolicy();
    try
    {
      localObject = ((ApplicationPolicy)localObject).getInstalledApplicationsIDList();
      return localObject;
    }
    catch (SecurityException localSecurityException)
    {
      dho.e(a, localSecurityException, new String[] { "SecurityException: " });
    }
    return null;
  }
  
  public ArrayList<String> b()
  {
    localArrayList = new ArrayList();
    ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
    try
    {
      if (cnr.d(this.c.aF().a(), cdg.b.p) >= 0) {
        return a(false);
      }
      String[] arrayOfString = a();
      if ((arrayOfString != null) && (arrayOfString.length > 0))
      {
        int j = arrayOfString.length;
        int i = 0;
        while (i < j)
        {
          String str = arrayOfString[i];
          if (!localApplicationPolicy.getApplicationStateEnabled(str)) {
            localArrayList.add(str);
          }
          i += 1;
        }
      }
      return localArrayList;
    }
    catch (SecurityException localSecurityException)
    {
      dho.e(a, localSecurityException, new String[] { "SecurityException: " });
    }
  }
  
  public boolean b(String paramString)
  {
    ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
    try
    {
      boolean bool = localApplicationPolicy.removeAppPackageNameFromBlackList(paramString);
      return bool;
    }
    catch (SecurityException paramString)
    {
      dho.e(a, paramString, new String[] { "SecurityException: " });
    }
    return false;
  }
  
  public List<String> c()
  {
    try
    {
      localList = this.b.getApplicationPolicy().getPackagesFromForceStopBlackList();
      if (localList == null) {}
      try
      {
        dho.d(a, new String[] { "Getting packages from forceStop blacklist failed" });
        return new ArrayList();
      }
      catch (SecurityException localSecurityException1) {}
      dho.d(a, new String[] { "Getting packages from forceStop blacklist succeeded" });
      return localList;
    }
    catch (SecurityException localSecurityException2)
    {
      for (;;)
      {
        List localList = null;
      }
    }
    dho.e(a, localSecurityException1, new String[] { "SecurityException: " });
    return localList;
  }
  
  public void c(String paramString)
  {
    ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
    try
    {
      localApplicationPolicy.setApplicationUninstallationDisabled(paramString);
      return;
    }
    catch (SecurityException paramString)
    {
      dho.e(a, paramString, new String[] { "SecurityException: " });
    }
  }
  
  public void d(String paramString)
  {
    ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
    try
    {
      localApplicationPolicy.setApplicationUninstallationEnabled(paramString);
      return;
    }
    catch (SecurityException paramString)
    {
      dho.e(a, paramString, new String[] { "SecurityException: " });
    }
  }
  
  public boolean e(String paramString)
  {
    ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
    boolean bool;
    try
    {
      if (localApplicationPolicy.getApplicationStateEnabled(paramString)) {
        bool = localApplicationPolicy.setDisableApplication(paramString);
      }
    }
    catch (SecurityException paramString)
    {
      bool = false;
    }
    try
    {
      dho.b(a, new String[] { "Disabling Application " + paramString + " status: " + bool });
      return bool;
    }
    catch (SecurityException paramString)
    {
      for (;;) {}
    }
    dho.a(a, new String[] { "Package name is already blocked, so skipping the disabling part " + paramString });
    return false;
    dho.e(a, paramString, new String[] { "SecurityException: " });
    return bool;
  }
  
  public boolean f(String paramString)
  {
    ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
    try
    {
      boolean bool = localApplicationPolicy.setEnableApplication(paramString);
      return bool;
    }
    catch (SecurityException paramString)
    {
      dho.e(a, paramString, new String[] { "SecurityException: " });
    }
    return false;
  }
  
  public boolean g(String paramString)
  {
    ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
    try
    {
      bool = localApplicationPolicy.uninstallApplication(paramString, false);
      if (!bool) {}
      try
      {
        dho.d(a, new String[] { "Un Install Application package Failed" });
        return bool;
      }
      catch (SecurityException paramString) {}
      dho.d(a, new String[] { "Un Install Application package Succeeded" });
      return bool;
    }
    catch (SecurityException paramString)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    dho.e(a, paramString, new String[] { "SecurityException: " });
    return bool;
  }
  
  public boolean h(String paramString)
  {
    try
    {
      ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramString);
      bool = localApplicationPolicy.addPackagesToForceStopBlackList(localArrayList);
      if (!bool) {}
      try
      {
        dho.d(a, new String[] { "Adding Application package to ForceStop Blacklist Failed" });
        return bool;
      }
      catch (SecurityException paramString) {}
      dho.d(a, new String[] { "Adding Application package to ForceStop Blacklist Succeeded" });
      return bool;
    }
    catch (SecurityException paramString)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    dho.e(a, paramString, new String[] { "SecurityException: " });
    return bool;
  }
  
  public boolean i(String paramString)
  {
    try
    {
      ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramString);
      bool = localApplicationPolicy.removePackagesFromForceStopBlackList(localArrayList);
      if (!bool) {}
      try
      {
        dho.d(a, new String[] { "Removing Application package to ForceStop Blacklist Failed" });
        return bool;
      }
      catch (SecurityException paramString) {}
      dho.d(a, new String[] { "Removing Application package to ForceStop Blacklist Succeeded" });
      return bool;
    }
    catch (SecurityException paramString)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    dho.e(a, paramString, new String[] { "SecurityException: " });
    return bool;
  }
  
  public boolean j(String paramString)
  {
    try
    {
      ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramString);
      bool = localApplicationPolicy.removePackagesFromClearCacheBlackList(localArrayList);
      if (!bool) {}
      try
      {
        dho.d(a, new String[] { "Removing Application package to ForceStop Blacklist Failed" });
        return bool;
      }
      catch (SecurityException paramString) {}
      dho.d(a, new String[] { "Removing Application package to ForceStop Blacklist Succeeded" });
      return bool;
    }
    catch (SecurityException paramString)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    dho.e(a, paramString, new String[] { "SecurityException: " });
    return bool;
  }
  
  public boolean k(String paramString)
  {
    try
    {
      ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramString);
      bool = localApplicationPolicy.addPackagesToClearCacheBlackList(localArrayList);
      if (!bool) {}
      try
      {
        dho.d(a, new String[] { "Adding Application package to ForceStop Blacklist Failed" });
        return bool;
      }
      catch (SecurityException paramString) {}
      dho.d(a, new String[] { "Adding Application package to ForceStop Blacklist Succeeded" });
      return bool;
    }
    catch (SecurityException paramString)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    dho.e(a, paramString, new String[] { "SecurityException: " });
    return bool;
  }
  
  public boolean l(String paramString)
  {
    try
    {
      ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramString);
      bool = localApplicationPolicy.removePackagesFromClearDataBlackList(localArrayList);
      if (!bool) {}
      try
      {
        dho.d(a, new String[] { "Removing Application package to ForceStop Blacklist Failed" });
        return bool;
      }
      catch (SecurityException paramString) {}
      dho.d(a, new String[] { "Removing Application package to ForceStop Blacklist Succeeded" });
      return bool;
    }
    catch (SecurityException paramString)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    dho.e(a, paramString, new String[] { "SecurityException: " });
    return bool;
  }
  
  public boolean m(String paramString)
  {
    try
    {
      ApplicationPolicy localApplicationPolicy = this.b.getApplicationPolicy();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramString);
      bool = localApplicationPolicy.addPackagesToClearDataBlackList(localArrayList);
      if (!bool) {}
      try
      {
        dho.d(a, new String[] { "Adding Application package to ForceStop Blacklist Failed" });
        return bool;
      }
      catch (SecurityException paramString) {}
      dho.d(a, new String[] { "Adding Application package to ForceStop Blacklist Succeeded" });
      return bool;
    }
    catch (SecurityException paramString)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    dho.e(a, paramString, new String[] { "SecurityException: " });
    return bool;
  }
}
