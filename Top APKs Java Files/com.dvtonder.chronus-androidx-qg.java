package androidx;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class qg
{
  public static final ComponentName akB = new ComponentName("net.nurik.roman.dashclock", "com.google.android.apps.dashclock.DashClockService");
  public static final ComponentName akC = new ComponentName("com.dvtonder.extensionhost", "com.dvtonder.extensionhost.HostService");
  protected static final String[] akD = { "net.nurik.roman.dashclock", "com.dvtonder.extensionhost" };
  private static final ComponentName[] akE = { akB, akC };
  private vo akF;
  private List<vn> akG;
  private boolean akH;
  private final Map<ComponentName, vm> akI;
  private Set<ComponentName> akJ;
  private boolean akK = true;
  private ServiceConnection akL = new ServiceConnection()
  {
    public void onNullBinding(ComponentName paramAnonymousComponentName)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onNullBinding() for ");
      localStringBuilder.append(paramAnonymousComponentName);
      Log.w("ExtensionHost", localStringBuilder.toString());
      qg.a(qg.this, null);
    }
    
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      qg.a(qg.this, vo.a.h(paramAnonymousIBinder));
      for (;;)
      {
        try
        {
          if (qg.a(qg.this) != null) {
            qg.this.a(qg.a(qg.this));
          }
          paramAnonymousComponentName = qg.c(qg.this);
          if (qg.b(qg.this).pM())
          {
            i = 0;
            paramAnonymousComponentName.obtainMessage(1, i, 0, qg.b(qg.this).AQ()).sendToTarget();
            return;
          }
        }
        catch (RemoteException paramAnonymousComponentName)
        {
          Log.e("ExtensionHost", "RemoteException getting extensions: ", paramAnonymousComponentName);
          qg.a(qg.this, null);
          return;
        }
        int i = 1;
      }
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      qg.a(qg.this, false);
      qg.d(qg.this).clear();
      qg.a(qg.this, null);
      if (!qg.e(qg.this)) {
        qg.c(qg.this).sendEmptyMessageDelayed(3, 5000L);
      }
    }
  };
  private final vp.a akM = new vp.a()
  {
    public void a(ComponentName paramAnonymousComponentName, vm paramAnonymousVm)
    {
      synchronized (qg.f(qg.this))
      {
        qg.f(qg.this).put(paramAnonymousComponentName, paramAnonymousVm);
        qg.c(qg.this).obtainMessage(2, paramAnonymousComponentName).sendToTarget();
        return;
      }
    }
    
    public void a(List<vn> paramAnonymousList, boolean paramAnonymousBoolean)
    {
      qg.c(qg.this).obtainMessage(1, paramAnonymousBoolean ^ true, 0, paramAnonymousList).sendToTarget();
    }
  };
  private final Handler.Callback akN = new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      int i = paramAnonymousMessage.what;
      boolean bool = false;
      switch (i)
      {
      default: 
        return false;
      case 4: 
        qg.this.aF(false);
        qg.b(qg.this, false);
        return true;
      }
      try
      {
        if (!qg.g(qg.this)) {
          qg.c(qg.this).sendEmptyMessageDelayed(3, 5000L);
        }
        return true;
      }
      catch (qg.a|SecurityException paramAnonymousMessage) {}
      qg.this.g((ComponentName)paramAnonymousMessage.obj);
      return true;
      Object localObject = new ArrayList((List)paramAnonymousMessage.obj);
      i = ((List)localObject).size() - 1;
      while (i >= 0)
      {
        if (((List)localObject).get(i) == null) {
          ((List)localObject).remove(i);
        }
        i -= 1;
      }
      qg.d(qg.this).clear();
      qg.d(qg.this).addAll((Collection)localObject);
      localObject = qg.this;
      if (paramAnonymousMessage.arg1 == 0) {
        bool = true;
      }
      qg.a((qg)localObject, bool);
      qg.this.pP();
      return true;
      return true;
    }
  };
  private Context mContext;
  private boolean mDestroyed;
  private final Handler mHandler;
  
  protected qg(Context paramContext)
  {
    this.mContext = paramContext;
    this.mHandler = new Handler(Looper.getMainLooper(), this.akN);
    this.akI = new HashMap();
    this.akG = new ArrayList();
    try
    {
      if (connect()) {
        break label121;
      }
      this.mHandler.sendEmptyMessageDelayed(3, 5000L);
      return;
    }
    catch (a|SecurityException paramContext)
    {
      for (;;) {}
    }
    this.mHandler.obtainMessage(4).sendToTarget();
    label121:
  }
  
  private static boolean Y(Context paramContext)
  {
    return aa(paramContext) != null;
  }
  
  private static List<String> Z(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if (Build.VERSION.SDK_INT < 21) {
      return localArrayList;
    }
    paramContext = paramContext.getPackageManager();
    Iterator localIterator = paramContext.getInstalledApplications(128).iterator();
    for (;;)
    {
      ApplicationInfo localApplicationInfo;
      if (localIterator.hasNext()) {
        localApplicationInfo = (ApplicationInfo)localIterator.next();
      }
      try
      {
        if (localApplicationInfo.packageName.equals(akC.getPackageName())) {
          return localArrayList;
        }
        PermissionInfo[] arrayOfPermissionInfo = paramContext.getPackageInfo(localApplicationInfo.packageName, 4096).permissions;
        if (arrayOfPermissionInfo != null)
        {
          int j = arrayOfPermissionInfo.length;
          int i = 0;
          while (i < j)
          {
            if ((arrayOfPermissionInfo[i].name.equals("com.google.android.apps.dashclock.permission.READ_EXTENSION_DATA")) && (!localApplicationInfo.packageName.equals(akB.getPackageName())))
            {
              localArrayList.add(localApplicationInfo.packageName);
              break;
            }
            i += 1;
          }
          return localArrayList;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    }
  }
  
  private static ComponentName aa(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    ComponentName[] arrayOfComponentName = akE;
    int m = arrayOfComponentName.length;
    int i = 0;
    while (i < m)
    {
      ComponentName localComponentName = arrayOfComponentName[i];
      try
      {
        PackageInfo localPackageInfo = paramContext.getPackageInfo(localComponentName.getPackageName(), 68);
        if (localPackageInfo.applicationInfo.enabled)
        {
          ServiceInfo[] arrayOfServiceInfo = localPackageInfo.services;
          int n = arrayOfServiceInfo.length;
          int j = 0;
          while (j < n)
          {
            Object localObject = arrayOfServiceInfo[j];
            if ((localComponentName.getClassName().equals(((ServiceInfo)localObject).name)) && (((ServiceInfo)localObject).enabled) && (localPackageInfo.signatures != null) && (localPackageInfo.signatures.length == 1))
            {
              localObject = vl.aRc;
              int i1 = localObject.length;
              int k = 0;
              while (k < i1)
              {
                boolean bool = localObject[k].equals(localPackageInfo.signatures[0]);
                if (bool) {
                  return localComponentName;
                }
                k += 1;
              }
            }
            j += 1;
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;) {}
      }
      i += 1;
    }
    return null;
  }
  
  private boolean connect()
  {
    ComponentName localComponentName = aa(this.mContext);
    Intent localIntent;
    if (localComponentName != null)
    {
      h(this.mContext, "com.google.android.apps.dashclock.permission.BIND_DATA_CONSUMER");
      localIntent = new Intent();
      localIntent.setComponent(localComponentName);
    }
    try
    {
      boolean bool = this.mContext.bindService(localIntent, this.akL, 1);
      return bool;
    }
    catch (ReceiverCallNotAllowedException localReceiverCallNotAllowedException)
    {
      for (;;) {}
    }
    return false;
    throw new a("Multiplexer service not installed", null);
  }
  
  private static void h(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    for (;;)
    {
      try
      {
        paramContext = ((PackageManager)localObject).getPackageInfo(paramContext, 4096);
        localObject = paramContext.requestedPermissions;
        j = 0;
        if (localObject == null) {
          continue;
        }
        i = paramContext.requestedPermissions.length;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        int j;
        boolean bool;
        continue;
        int i = 0;
        continue;
      }
      if (j >= i) {
        continue;
      }
      bool = paramContext.requestedPermissions[j].equals(paramString);
      if (bool) {
        return;
      }
      j += 1;
    }
    paramContext = new StringBuilder();
    paramContext.append("Caller didn't request the permission \"");
    paramContext.append(paramString);
    paramContext.append("\"");
    throw new SecurityException(paramContext.toString());
  }
  
  protected void a(Set<ComponentName> paramSet)
  {
    this.akJ = paramSet;
    if (paramSet == null) {
      paramSet = null;
    } else {
      paramSet = new ArrayList(paramSet);
    }
    try
    {
      if (this.akF != null) {
        this.akF.a(paramSet, this.akM);
      }
      return;
    }
    catch (RemoteException paramSet) {}
  }
  
  public boolean a(vn paramVn)
  {
    if ((this.akF != null) && (paramVn.AP() != null)) {
      try
      {
        this.akF.a(paramVn.AJ(), this.akM);
        return true;
      }
      catch (RemoteException paramVn)
      {
        Log.e("ExtensionHost", "Error starting settings activity", paramVn);
      }
    }
    return false;
  }
  
  public List<vn> aE(boolean paramBoolean)
  {
    if (!paramBoolean) {
      return new ArrayList(this.akG);
    }
    ArrayList localArrayList = new ArrayList(this.akG);
    int i = localArrayList.size() - 1;
    while (i >= 0)
    {
      if (!((vn)localArrayList.get(i)).AM()) {
        localArrayList.remove(i);
      }
      i -= 1;
    }
    return localArrayList;
  }
  
  protected void aF(boolean paramBoolean) {}
  
  protected vm e(ComponentName paramComponentName)
  {
    synchronized (this.akI)
    {
      paramComponentName = (vm)this.akI.get(paramComponentName);
      return paramComponentName;
    }
  }
  
  public boolean f(ComponentName paramComponentName)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramComponentName);
    return i(localArrayList);
  }
  
  protected void g(ComponentName paramComponentName) {}
  
  public boolean i(List<ComponentName> paramList)
  {
    try
    {
      if (this.akF != null) {
        this.akF.b(paramList, this.akM);
      }
      return true;
    }
    catch (RemoteException paramList)
    {
      for (;;) {}
    }
    return false;
  }
  
  public boolean pM()
  {
    return this.akH;
  }
  
  public Intent pN()
  {
    return new Intent("com.google.android.apps.dashclock.action.ASK_ENABLE_FORCE_WORLD_READABLE");
  }
  
  protected List<vn> pO()
  {
    try
    {
      if (this.akF != null) {
        this.akG = this.akF.AQ();
      }
      return this.akG;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  protected void pP() {}
  
  protected final Intent pQ()
  {
    if (!Z(this.mContext).isEmpty()) {
      return null;
    }
    String str = akC.getPackageName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(str);
    return new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString()));
  }
  
  public void pR()
  {
    boolean bool = Y(this.mContext);
    if (this.akK != bool)
    {
      if ((bool) && (this.akF == null)) {
        this.mHandler.obtainMessage(3).sendToTarget();
      } else if ((!bool) && (this.akF != null)) {
        this.mContext.unbindService(this.akL);
      }
      aF(bool);
      this.akK = bool;
    }
  }
  
  static class a
    extends RuntimeException
  {
    private a(String paramString)
    {
      super();
    }
  }
}
