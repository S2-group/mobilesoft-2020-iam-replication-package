package com.kidoz.logic.login;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.kidoz.db.KidozDataRepository;
import com.kidoz.logic.AppStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AppsManager
{
  private final int FLAG_SYSTEM_APPLICATION = 1;
  private Context _context;
  KidozDataRepository _kidozDataRepository;
  
  public AppsManager(Context paramContext)
  {
    this._context = paramContext;
    this._kidozDataRepository = new KidozDataRepository(paramContext);
  }
  
  public void addAllowedApps(List<String> paramList, String paramString)
  {
    Object localObject;
    LinkedList localLinkedList;
    if ((this._context != null) && (this._kidozDataRepository != null))
    {
      localObject = this._kidozDataRepository.getAppsStatusByPackageNames(paramList, paramString);
      if ((localObject != null) && (((List)localObject).size() > 0))
      {
        paramList = new LinkedList();
        localLinkedList = new LinkedList();
        localObject = ((List)localObject).iterator();
      }
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        if (paramList.size() > 0) {
          this._kidozDataRepository.updateStatusForApps(paramList, AppStatus.ALLOWED, paramString);
        }
        if (localLinkedList.size() > 0) {
          this._kidozDataRepository.setKidApplication(localLinkedList, AppStatus.ALLOWED, paramString);
        }
        return;
      }
      PackageStatus localPackageStatus = (PackageStatus)((Iterator)localObject).next();
      if ((localPackageStatus.getStatus() == null) || (localPackageStatus.getStatus().equals(AppStatus.NONE))) {
        localLinkedList.add(localPackageStatus.getPackageName());
      } else if ((localPackageStatus.getStatus().equals(AppStatus.DISALLOWED)) || (localPackageStatus.getStatus().equals(AppStatus.APPROVED))) {
        paramList.add(localPackageStatus.getPackageName());
      }
    }
  }
  
  public void addDisallowedApps(List<String> paramList, String paramString)
  {
    Object localObject;
    LinkedList localLinkedList;
    if ((this._context != null) && (this._kidozDataRepository != null))
    {
      localObject = this._kidozDataRepository.getAppsStatusByPackageNames(paramList, paramString);
      if ((localObject != null) && (((List)localObject).size() > 0))
      {
        paramList = new LinkedList();
        localLinkedList = new LinkedList();
        localObject = ((List)localObject).iterator();
      }
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        if (paramList.size() > 0) {
          this._kidozDataRepository.updateStatusForApps(paramList, AppStatus.DISALLOWED, paramString);
        }
        if (localLinkedList.size() > 0) {
          this._kidozDataRepository.setKidApplication(localLinkedList, AppStatus.DISALLOWED, paramString);
        }
        return;
      }
      PackageStatus localPackageStatus = (PackageStatus)((Iterator)localObject).next();
      if ((localPackageStatus.getStatus() == null) || (localPackageStatus.getStatus().equals(AppStatus.NONE))) {
        localLinkedList.add(localPackageStatus.getPackageName());
      } else if ((localPackageStatus.getStatus().equals(AppStatus.ALLOWED)) || (localPackageStatus.getStatus().equals(AppStatus.BLOCKED))) {
        paramList.add(localPackageStatus.getPackageName());
      }
    }
  }
  
  public void addNewAppsToDB(ArrayList<AppsForKid.ApplicationData> paramArrayList, boolean paramBoolean)
  {
    this._kidozDataRepository.saveApplications(paramArrayList, paramBoolean);
  }
  
  protected List<String> getAllInstalledApps()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1;
    if ((this._context != null) && (this._kidozDataRepository != null))
    {
      localObject1 = this._context.getPackageManager();
      this._context.getPackageName();
      localObject1 = ((PackageManager)localObject1).getInstalledApplications(0).iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext()) {
        return localArrayList;
      }
      Object localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
      if (this._context.getPackageManager().getLaunchIntentForPackage(((ApplicationInfo)localObject2).packageName) != null)
      {
        localObject2 = ((ApplicationInfo)localObject2).packageName;
        if (localObject2 != null) {
          localArrayList.add(localObject2);
        }
      }
    }
  }
  
  protected List<String> getNewApps()
  {
    if (this._kidozDataRepository != null)
    {
      Object localObject1 = new ArrayList();
      Object localObject2;
      Object localObject3;
      if (this._context != null)
      {
        localObject2 = this._context.getPackageManager().getInstalledApplications(0).iterator();
        if (((Iterator)localObject2).hasNext()) {}
      }
      else
      {
        localObject2 = this._kidozDataRepository.getNativeApplications();
        localObject3 = new ArrayList();
        localObject1 = ((ArrayList)localObject1).iterator();
      }
      for (;;)
      {
        if (!((Iterator)localObject1).hasNext())
        {
          return localObject3;
          localObject3 = (ApplicationInfo)((Iterator)localObject2).next();
          if (this._context.getPackageManager().getLaunchIntentForPackage(((ApplicationInfo)localObject3).packageName) == null) {
            break;
          }
          localObject3 = ((ApplicationInfo)localObject3).packageName;
          if (localObject3 == null) {
            break;
          }
          ((ArrayList)localObject1).add(localObject3);
          break;
        }
        String str = (String)((Iterator)localObject1).next();
        if (!((ArrayList)localObject2).contains(str)) {
          ((ArrayList)localObject3).add(str);
        }
      }
    }
    return null;
  }
  
  public boolean removeNonExistingAppsFromDB()
  {
    List localList = getAllInstalledApps();
    Object localObject = this._kidozDataRepository.getNativeApplications();
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        this._kidozDataRepository.removeApplications(localArrayList);
        if (localArrayList.size() <= 0) {
          break;
        }
        return true;
      }
      String str = (String)((Iterator)localObject).next();
      if (!localList.contains(str)) {
        localArrayList.add(str);
      }
    }
    return false;
  }
  
  protected void updateStoredApp(String paramString1, String paramString2, AppStatus paramAppStatus)
  {
    if ((this._context != null) && (this._kidozDataRepository != null))
    {
      paramString1 = this._kidozDataRepository.getKidAppStatusByPackageName(paramString1, paramString2);
      if (paramString1 != null)
      {
        if (!paramAppStatus.equals(AppStatus.ALLOWED)) {
          break label57;
        }
        if (!paramString1.equals(AppStatus.DISALLOWED)) {
          paramString1.equals(AppStatus.APPROVED);
        }
      }
    }
    label57:
    while ((!paramAppStatus.equals(AppStatus.DISALLOWED)) || (paramString1.equals(AppStatus.ALLOWED))) {
      return;
    }
    paramString1.equals(AppStatus.BLOCKED);
  }
  
  public static class PackageStatus
  {
    private String _packageName;
    private AppStatus _status;
    
    public PackageStatus(String paramString, AppStatus paramAppStatus)
    {
      this._packageName = paramString;
      this._status = paramAppStatus;
    }
    
    public String getPackageName()
    {
      return this._packageName;
    }
    
    public AppStatus getStatus()
    {
      return this._status;
    }
    
    public void setPackageName(String paramString)
    {
      this._packageName = paramString;
    }
    
    public void setStatus(AppStatus paramAppStatus)
    {
      this._status = paramAppStatus;
    }
  }
}
