package com.dvtonder.chronus.extensions;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import androidx.lp;
import androidx.qg;
import androidx.qm;
import androidx.qu;
import androidx.rf;
import androidx.ro;
import androidx.ro.a;
import androidx.tb;
import androidx.vm;
import androidx.vn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExtensionManager
  extends qg
{
  private static ArrayList<String> akP;
  private static final List<String> akR = Arrays.asList(new String[] { "com.google.android.apps.dashclock", "net.nurik.roman.dashclock" });
  @SuppressLint({"StaticFieldLeak"})
  private static ExtensionManager akV;
  private final BroadcastReceiver akQ = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      ExtensionManager.a(ExtensionManager.this, paramAnonymousContext);
      ExtensionManager.this.pR();
    }
  };
  private final Set<ComponentName> akS;
  private final List<c> akT;
  private b akU;
  private final Context mContext;
  private final Handler mMainThreadHandler;
  
  private ExtensionManager(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    this.mMainThreadHandler = new Handler(Looper.getMainLooper());
    this.akS = new HashSet();
    this.akT = new ArrayList();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.dvtonder.chronus.extensions.MULTIPLEXER_PACKAGE_CHANGED");
    lp.t(paramContext).a(this.akQ, localIntentFilter);
    af(paramContext);
  }
  
  private boolean a(List<vn> paramList, ComponentName paramComponentName)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      if (paramComponentName.equals(((vn)paramList.next()).AJ())) {
        return true;
      }
    }
    return false;
  }
  
  private void aG(boolean paramBoolean)
  {
    Handler localHandler = this.mMainThreadHandler;
    if ((localHandler != null) && (this.akT != null)) {
      localHandler.post(new -..Lambda.ExtensionManager.jIzObQxiXkW9UndAtyr5qnJgaw8(this, paramBoolean));
    }
  }
  
  public static boolean ab(Context paramContext)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(akB.getPackageName(), 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean ac(Context paramContext)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(akC.getPackageName(), 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static void ad(Context paramContext)
  {
    if (ro.sj())
    {
      Intent localIntent = new Intent("com.dvtonder.chronus.extensions.MULTIPLEXER_PACKAGE_CHANGED");
      lp.t(paramContext).f(localIntent);
    }
  }
  
  public static ExtensionManager ae(Context paramContext)
  {
    if (akV == null) {
      akV = new ExtensionManager(paramContext);
    }
    return akV;
  }
  
  private void af(Context paramContext)
  {
    if (pW() == null)
    {
      this.akU = b.akZ;
      return;
    }
    String[] arrayOfString = akD;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      try
      {
        if (paramContext.getPackageManager().getPackageInfo(str, 0).versionCode <= 30)
        {
          this.akU = b.alb;
          return;
        }
        this.akU = b.alc;
        return;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;) {}
      }
      i += 1;
    }
    this.akU = b.ala;
  }
  
  public static ArrayList<String> ag(Context paramContext)
  {
    if (akP == null)
    {
      akP = new ArrayList();
      if (Build.VERSION.SDK_INT >= 21)
      {
        ArrayList localArrayList = rf.bz(paramContext);
        if (localArrayList != null)
        {
          if (qu.amu) {
            Log.i("ExtensionManager", "Using the cached Mux-apps list");
          }
          akP = localArrayList;
          new Thread(new -..Lambda.ExtensionManager.qqaOAGxmpAdi3MrffSqAy6nVdYw(paramContext)).start();
        }
        else
        {
          if (qu.amu) {
            Log.i("ExtensionManager", "No cached Mux-apps list, checking for Mux-apps...");
          }
          akP = ah(paramContext);
        }
      }
    }
    return akP;
  }
  
  private static ArrayList<String> ah(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    for (;;)
    {
      Object localObject1;
      if (localIterator.hasNext()) {
        localObject1 = (ApplicationInfo)localIterator.next();
      }
      try
      {
        localObject1 = ((ApplicationInfo)localObject1).packageName;
        if ("com.dvtonder.extensionhost".equals(localObject1))
        {
          Log.i("ExtensionManager", "Found the Chronus DashClock Host app");
        }
        else
        {
          Object localObject2 = localPackageManager.getPackageInfo((String)localObject1, 4096).permissions;
          if (localObject2 == null) {
            continue;
          }
          int j = localObject2.length;
          int i = 0;
          while (i < j)
          {
            if (("com.google.android.apps.dashclock.permission.READ_EXTENSION_DATA".equals(localObject2[i].name)) && (!"net.nurik.roman.dashclock".equals(localObject1)))
            {
              localArrayList.add(localObject1);
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("Found a 3rd party DashClock host app: ");
              ((StringBuilder)localObject2).append((String)localObject1);
              Log.i("ExtensionManager", ((StringBuilder)localObject2).toString());
              break;
            }
            i += 1;
          }
        }
        if (qu.amu) {
          Log.i("ExtensionManager", "Caching available host apps list");
        }
        rf.a(paramContext, localArrayList);
        return localArrayList;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    }
  }
  
  private void b(Set<ComponentName> paramSet)
  {
    int i;
    if (!this.akS.equals(paramSet))
    {
      i = 1;
      this.akS.clear();
      this.akS.addAll(paramSet);
    }
    else
    {
      i = 0;
    }
    if (i != 0)
    {
      if (qu.amu) {
        Log.i("ExtensionManager", "List of active extensions has changed, update the 'Listen to' list");
      }
      a(paramSet);
    }
  }
  
  private void h(ComponentName paramComponentName)
  {
    if (paramComponentName != null)
    {
      Handler localHandler = this.mMainThreadHandler;
      if ((localHandler != null) && (this.akT != null)) {
        localHandler.post(new -..Lambda.ExtensionManager.UHjg_mblS8F9bjsrLElmeOFSwWo(this, paramComponentName));
      }
    }
  }
  
  private Intent pW()
  {
    if (!ag(this.mContext).isEmpty()) {
      return null;
    }
    String str = akC.getPackageName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(str);
    return new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString()));
  }
  
  public List<a> C(Context paramContext, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = rf.dj(paramContext, paramInt);
    paramContext = new HashMap();
    int j = pS();
    Object localObject2 = aE(pM() ^ true).iterator();
    Object localObject3;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (vn)((Iterator)localObject2).next();
      paramContext.put(((vn)localObject3).AJ(), localObject3);
    }
    localObject1 = ((List)localObject1).iterator();
    int i = 0;
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (ComponentName)((Iterator)localObject1).next();
      if (i >= j) {
        break;
      }
      localObject2 = (vn)paramContext.get(localObject3);
      if (localObject2 != null)
      {
        localObject3 = e((ComponentName)localObject3);
        if ((localObject3 != null) && (((vm)localObject3).AA()))
        {
          a localA = new a();
          localA.akX = ((vn)localObject2);
          localA.akY = ((vm)localObject3);
          localArrayList.add(localA);
          i += 1;
        }
      }
    }
    if (qu.amv)
    {
      paramContext = new StringBuilder();
      paramContext.append("getVisibleExtensions for widget ");
      paramContext.append(paramInt);
      paramContext.append(": count = ");
      paramContext.append(localArrayList.size());
      Log.i("ExtensionManager", paramContext.toString());
    }
    return localArrayList;
  }
  
  public void a(c paramC)
  {
    List localList = this.akT;
    if (localList != null) {
      localList.add(paramC);
    }
  }
  
  public List<vn> aE(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = super.aE(paramBoolean).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      vn localVn = (vn)((Iterator)localObject1).next();
      if ((localVn != null) && (localVn.AJ() != null))
      {
        Object localObject2 = localVn.AJ();
        String str = ((ComponentName)localObject2).getPackageName();
        if (((!"com.dvtonder.chronus".equals(str)) || (!"com.dvtonder.chronus.extensions.gmail.GmailExtension".equals(((ComponentName)localObject2).getClassName())) || (qm.b(this.mContext, "com.google.android.gm", "com.google.android.gm.permission.READ_CONTENT_PROVIDER"))) && (!akR.contains(str)))
        {
          localArrayList.add(localVn);
          if (qu.amu)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Adding extension ");
            ((StringBuilder)localObject2).append(localVn.AN());
            ((StringBuilder)localObject2).append(" to available list");
            Log.i("ExtensionManager", ((StringBuilder)localObject2).toString());
          }
        }
      }
    }
    if (qu.amu)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Returning list with ");
      ((StringBuilder)localObject1).append(localArrayList.size());
      ((StringBuilder)localObject1).append(" available extensions");
      Log.i("ExtensionManager", ((StringBuilder)localObject1).toString());
    }
    return localArrayList;
  }
  
  public void aF(boolean paramBoolean)
  {
    super.aF(paramBoolean);
    aG(pU());
  }
  
  public void ai(Context paramContext)
  {
    try
    {
      paramContext.startActivity(pQ());
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    Toast.makeText(paramContext, 2131951950, 0).show();
  }
  
  public void b(c paramC)
  {
    List localList = this.akT;
    if (localList != null) {
      localList.remove(paramC);
    }
  }
  
  protected void finalize()
  {
    super.finalize();
    lp.t(this.mContext).unregisterReceiver(this.akQ);
  }
  
  public void g(ComponentName paramComponentName)
  {
    super.g(paramComponentName);
    h(paramComponentName);
  }
  
  public void pP()
  {
    super.pP();
    pT();
  }
  
  public int pS()
  {
    if (tb.cP(this.mContext).xR()) {
      return Integer.MAX_VALUE;
    }
    return 2;
  }
  
  public void pT()
  {
    HashSet localHashSet = new HashSet();
    Object localObject1 = aE(pM());
    int k = pS();
    Iterator localIterator = ro.bR(this.mContext).iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (ro.a)localIterator.next();
      if ((localObject2 != null) && ((((ro.a)localObject2).flags & 0x400) != 0))
      {
        Object localObject3 = ro.c(this.mContext, ((ro.a)localObject2).aqu);
        int m = localObject3.length;
        int i = 0;
        while (i < m)
        {
          int j = localObject3[i];
          Object localObject4;
          Object localObject5;
          if ((localObject2 != null) && (((((ro.a)localObject2).flags & 0x800) != 0) || (rf.cL(this.mContext, j))))
          {
            localObject4 = rf.dj(this.mContext, j);
            if (qu.amu)
            {
              localObject5 = new StringBuilder();
              ((StringBuilder)localObject5).append("updateActiveExtensionList: Extensions for widget ");
              ((StringBuilder)localObject5).append(j);
              ((StringBuilder)localObject5).append(": ");
              ((StringBuilder)localObject5).append(localObject4);
              Log.i("ExtensionManager", ((StringBuilder)localObject5).toString());
            }
            localObject4 = ((List)localObject4).iterator();
            j = 0;
          }
          while (((Iterator)localObject4).hasNext())
          {
            localObject5 = (ComponentName)((Iterator)localObject4).next();
            if ((j < k) && (a((List)localObject1, (ComponentName)localObject5)))
            {
              localHashSet.add(localObject5);
              j += 1;
              continue;
              if (qu.amu)
              {
                localObject4 = new StringBuilder();
                ((StringBuilder)localObject4).append("updateActiveExtensionList: extensions disabled for widget ");
                ((StringBuilder)localObject4).append(j);
                ((StringBuilder)localObject4).append(", skipping");
                Log.i("ExtensionManager", ((StringBuilder)localObject4).toString());
              }
            }
          }
          i += 1;
        }
        i = localHashSet.size();
        if ((i < k) && (rf.cL(this.mContext, Integer.MAX_VALUE)))
        {
          localObject2 = rf.dj(this.mContext, Integer.MAX_VALUE);
          if (qu.amu)
          {
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("updateActiveExtensionList: Extensions for DAYDREAM: ");
            ((StringBuilder)localObject3).append(localObject2);
            Log.i("ExtensionManager", ((StringBuilder)localObject3).toString());
          }
          localObject2 = ((List)localObject2).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (ComponentName)((Iterator)localObject2).next();
            if ((i < k) && (a((List)localObject1, (ComponentName)localObject3)))
            {
              localHashSet.add(localObject3);
              i += 1;
            }
          }
        }
      }
    }
    if (qu.amu)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("updateActiveExtensionsList: Final list = ");
      ((StringBuilder)localObject1).append(localHashSet);
      Log.i("ExtensionManager", ((StringBuilder)localObject1).toString());
    }
    b(localHashSet);
  }
  
  public boolean pU()
  {
    return pV() == b.alc;
  }
  
  public b pV()
  {
    return this.akU;
  }
  
  public boolean pX()
  {
    HashSet localHashSet = new HashSet();
    Object localObject1 = pO().iterator();
    Object localObject3;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (vn)((Iterator)localObject1).next();
      if (!((vn)localObject2).AL())
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Extension '");
        ((StringBuilder)localObject3).append(((vn)localObject2).AN());
        ((StringBuilder)localObject3).append("' using unsupported protocol v");
        ((StringBuilder)localObject3).append(((vn)localObject2).AK());
        Log.w("ExtensionManager", ((StringBuilder)localObject3).toString());
      }
      else
      {
        localHashSet.add(((vn)localObject2).AJ());
      }
    }
    localObject1 = new HashSet();
    Object localObject2 = this.akS.iterator();
    int i = 0;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (ComponentName)((Iterator)localObject2).next();
      if (localHashSet.contains(localObject3)) {
        ((Set)localObject1).add(localObject3);
      } else {
        i = 1;
      }
    }
    if (i != 0)
    {
      b((Set)localObject1);
      return true;
    }
    return false;
  }
  
  public List<ComponentName> pY()
  {
    return new ArrayList(this.akS);
  }
  
  public boolean pZ()
  {
    return super.i(new ArrayList(this.akS));
  }
  
  public static class MultiplexerPackageChangeReceiver
    extends BroadcastReceiver
  {
    public MultiplexerPackageChangeReceiver() {}
    
    @SuppressLint({"UnsafeProtectedBroadcastReceiver"})
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramIntent = new Intent("com.dvtonder.chronus.extensions.MULTIPLEXER_PACKAGE_CHANGED");
      lp.t(paramContext).f(paramIntent);
    }
  }
  
  public static class a
  {
    public vn akX;
    public vm akY;
    
    public a() {}
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ExtensionWithData[cn=");
      localStringBuilder.append(this.akX.AJ());
      localStringBuilder.append(", data=");
      localStringBuilder.append(this.akY);
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  public static enum b
  {
    private b() {}
  }
  
  public static abstract interface c
  {
    public abstract void ay(boolean paramBoolean);
    
    public abstract void d(ComponentName paramComponentName);
  }
}
