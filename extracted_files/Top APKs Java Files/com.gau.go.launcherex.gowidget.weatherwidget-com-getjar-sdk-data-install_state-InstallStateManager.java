package com.getjar.sdk.data.install_state;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.getjar.sdk.comm.CommContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InstallStateManager
{
  private static volatile InstallStateManager _Instance = null;
  private final Context _context;
  private volatile Object _installStateLock = new Object();
  
  private InstallStateManager(Context paramContext)
  {
    this._context = paramContext.getApplicationContext();
    InstallStateDatabase.initialize(paramContext);
  }
  
  public static InstallStateManager getInstance(Context paramContext)
  {
    if (paramContext == null) {
      try
      {
        throw new IllegalArgumentException("'context' cannot be NULL");
      }
      finally {}
    }
    if (_Instance == null) {
      _Instance = new InstallStateManager(paramContext);
    }
    paramContext = _Instance;
    return paramContext;
  }
  
  public void sendCurrentStateDeltas(CommContext paramCommContext)
  {
    if (paramCommContext == null) {
      throw new IllegalArgumentException("'commContext' cannot be NULL");
    }
    synchronized (this._installStateLock)
    {
      InstallStateReporter.getInstance(paramCommContext).sendUnsyncedData();
      return;
    }
  }
  
  public void updateCurrentState()
  {
    synchronized (this._installStateLock)
    {
      ArrayList localArrayList = new ArrayList();
      localObject3 = this._context.getPackageManager().getInstalledApplications(128).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (ApplicationInfo)((Iterator)localObject3).next();
        if (!localArrayList.contains(((ApplicationInfo)localObject4).packageName)) {
          localArrayList.add(((ApplicationInfo)localObject4).packageName);
        }
      }
    }
    Object localObject3 = new HashMap();
    Object localObject4 = InstallStateDatabase.getInstance().loadAllRecords().iterator();
    Object localObject5;
    while (((Iterator)localObject4).hasNext())
    {
      localObject5 = (InstallStateRecord)((Iterator)localObject4).next();
      if (!((Map)localObject3).containsKey(((InstallStateRecord)localObject5).getPackageName())) {
        ((Map)localObject3).put(((InstallStateRecord)localObject5).getPackageName(), localObject5);
      }
    }
    localObject4 = localObject2.iterator();
    while (((Iterator)localObject4).hasNext())
    {
      localObject5 = (String)((Iterator)localObject4).next();
      if (!((Map)localObject3).containsKey(localObject5)) {
        InstallStateDatabase.getInstance().addRecord((String)localObject5);
      }
    }
    localObject3 = ((Map)localObject3).values().iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (InstallStateRecord)((Iterator)localObject3).next();
      if ((InstallState.FOUND_INSTALLED.equals(((InstallStateRecord)localObject4).getStatus())) && (!localObject2.contains(((InstallStateRecord)localObject4).getPackageName()))) {
        InstallStateDatabase.getInstance().updateState(((InstallStateRecord)localObject4).getId(), InstallState.FOUND_UNINSTALLED);
      }
    }
  }
  
  public static enum InstallState
  {
    FOUND_INSTALLED,  FOUND_UNINSTALLED;
    
    private InstallState() {}
  }
}
