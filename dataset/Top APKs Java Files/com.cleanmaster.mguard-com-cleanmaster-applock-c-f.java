package com.cleanmaster.applock.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.cleanmaster.applock.a.a;
import com.cleanmaster.applocklib.base.AppLockLib;
import com.cleanmaster.applocklib.core.service.c;
import com.cleanmaster.applocklib.interfaces.IAppLockLib;
import com.cleanmaster.base.util.system.o;
import com.keniu.security.d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public final class f
{
  b a;
  private c b;
  
  private f() {}
  
  public static PackageInfo a(String paramString, int paramInt)
  {
    boolean bool = a.a;
    PackageManager localPackageManager = d.a().getPackageManager();
    if (localPackageManager == null)
    {
      bool = a.a;
      return null;
    }
    paramString = localPackageManager.getPackageInfo(paramString, 0);
    bool = a.a;
    bool = a.a;
    return paramString;
  }
  
  public static String a(ResolveInfo paramResolveInfo)
  {
    if (paramResolveInfo == null) {
      paramResolveInfo = "";
    }
    String str;
    for (;;)
    {
      return paramResolveInfo;
      str = paramResolveInfo.activityInfo.packageName;
      PackageManager localPackageManager = d.a().getPackageManager();
      try
      {
        paramResolveInfo = (String)paramResolveInfo.loadLabel(localPackageManager);
        boolean bool = TextUtils.isEmpty(paramResolveInfo);
        if (bool) {
          return str;
        }
      }
      catch (Exception paramResolveInfo) {}
    }
    return str;
  }
  
  public static boolean a(String paramString)
  {
    Object localObject = d.a().getApplicationContext();
    if (localObject == null) {}
    do
    {
      return false;
      localObject = ((Context)localObject).getPackageManager();
    } while (localObject == null);
    try
    {
      ((PackageManager)localObject).getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  /* Error */
  private void b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 82	com/cleanmaster/applock/c/f:b	Lcom/cleanmaster/applock/c/f$c;
    //   6: ifnonnull +74 -> 80
    //   9: aload_0
    //   10: new 12	com/cleanmaster/applock/c/f$c
    //   13: dup
    //   14: aload_0
    //   15: iconst_0
    //   16: invokespecial 85	com/cleanmaster/applock/c/f$c:<init>	(Lcom/cleanmaster/applock/c/f;B)V
    //   19: putfield 82	com/cleanmaster/applock/c/f:b	Lcom/cleanmaster/applock/c/f$c;
    //   22: new 87	android/content/IntentFilter
    //   25: dup
    //   26: invokespecial 88	android/content/IntentFilter:<init>	()V
    //   29: astore_3
    //   30: aload_3
    //   31: ldc 90
    //   33: invokevirtual 94	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   36: aload_3
    //   37: ldc 96
    //   39: invokevirtual 94	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   42: aload_3
    //   43: ldc 98
    //   45: invokevirtual 94	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   48: aload_3
    //   49: ldc 100
    //   51: invokevirtual 94	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   54: aload_3
    //   55: ldc 102
    //   57: invokevirtual 105	android/content/IntentFilter:addDataScheme	(Ljava/lang/String;)V
    //   60: aload_3
    //   61: ldc 107
    //   63: invokevirtual 110	android/content/IntentFilter:addCategory	(Ljava/lang/String;)V
    //   66: getstatic 28	com/cleanmaster/applock/a/a:a	Z
    //   69: istore_2
    //   70: aload_1
    //   71: aload_0
    //   72: getfield 82	com/cleanmaster/applock/c/f:b	Lcom/cleanmaster/applock/c/f$c;
    //   75: aload_3
    //   76: invokevirtual 114	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   79: pop
    //   80: aload_0
    //   81: monitorexit
    //   82: return
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    //   88: astore_1
    //   89: goto -9 -> 80
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	f
    //   0	92	1	paramContext	Context
    //   69	1	2	bool	boolean
    //   29	47	3	localIntentFilter	android.content.IntentFilter
    // Exception table:
    //   from	to	target	type
    //   2	70	83	finally
    //   70	80	83	finally
    //   70	80	88	java/lang/Exception
  }
  
  public final List<PackageInfo> a(Context paramContext)
  {
    Object localObject = null;
    if (paramContext == null) {}
    for (;;)
    {
      return localObject;
      try
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        if (localPackageManager == null) {
          continue;
        }
        b(paramContext);
        localObject = new ArrayList();
        try
        {
          paramContext = localPackageManager.getInstalledPackages(0);
          localObject = paramContext;
        }
        catch (Exception paramContext) {}
      }
      finally {}
    }
  }
  
  public final List<ResolveInfo> a(Context paramContext, Intent paramIntent, int paramInt)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    if (paramContext == null) {}
    for (;;)
    {
      return localObject1;
      try
      {
        b(paramContext);
        new StringBuilder().append(paramIntent.getAction()).append(paramIntent.getCategories().toString()).append(0);
        Object localObject3 = paramContext.getPackageManager();
        if (localObject3 == null) {
          continue;
        }
        boolean bool = a.a;
        localObject3 = ((PackageManager)localObject3).queryIntentActivities(paramIntent, 0);
        paramIntent = localObject2;
        if (localObject3 != null)
        {
          paramIntent = new ArrayList();
          paramIntent.addAll((Collection)localObject3);
        }
        localObject1 = paramIntent;
        if (!o.g()) {
          continue;
        }
        localObject1 = paramIntent;
        if (this.a != null) {
          continue;
        }
        localObject1 = paramIntent;
        if (localObject3 == null) {
          continue;
        }
        this.a = new b(paramContext, (List)localObject3);
        this.a.start();
        localObject1 = paramIntent;
      }
      finally {}
    }
  }
  
  /* Error */
  public final void a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 82	com/cleanmaster/applock/c/f:b	Lcom/cleanmaster/applock/c/f$c;
    //   6: ifnull +22 -> 28
    //   9: getstatic 28	com/cleanmaster/applock/a/a:a	Z
    //   12: istore_1
    //   13: invokestatic 33	com/keniu/security/d:a	()Landroid/content/Context;
    //   16: aload_0
    //   17: getfield 82	com/cleanmaster/applock/c/f:b	Lcom/cleanmaster/applock/c/f$c;
    //   20: invokevirtual 179	android/content/Context:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   23: aload_0
    //   24: aconst_null
    //   25: putfield 82	com/cleanmaster/applock/c/f:b	Lcom/cleanmaster/applock/c/f$c;
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: astore_2
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_2
    //   35: athrow
    //   36: astore_2
    //   37: goto -9 -> 28
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	f
    //   12	1	1	bool	boolean
    //   31	4	2	localObject	Object
    //   36	1	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   2	13	31	finally
    //   13	28	31	finally
    //   13	28	36	java/lang/Exception
  }
  
  final void b() {}
  
  public static final class a
  {
    private static final f a = new f((byte)0);
  }
  
  final class b
    extends Thread
  {
    private Context a;
    private PackageManager b;
    private List<ResolveInfo> c;
    
    public b(List<ResolveInfo> paramList)
    {
      Collection localCollection;
      if (localCollection != null)
      {
        this.a = paramList;
        this.b = this.a.getPackageManager();
        this.c = new ArrayList(localCollection.size());
        this.c.addAll(localCollection);
      }
    }
    
    public final void run()
    {
      if (this.c != null)
      {
        Iterator localIterator = this.c.iterator();
        Object localObject1 = null;
        for (;;)
        {
          if (!localIterator.hasNext()) {
            break label64;
          }
          Object localObject2 = (ResolveInfo)localIterator.next();
          try
          {
            localObject2 = (String)((ResolveInfo)localObject2).loadLabel(this.b);
            localObject1 = localObject2;
          }
          catch (Exception localException)
          {
            for (;;) {}
          }
          TextUtils.isEmpty(localObject1);
        }
        label64:
        f.this.a = null;
      }
    }
  }
  
  final class c
    extends BroadcastReceiver
  {
    private c() {}
    
    public final void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getAction();
      boolean bool = a.a;
      if (("android.intent.action.PACKAGE_ADDED".equals(paramContext)) || ("android.intent.action.PACKAGE_REMOVED".equals(paramContext)) || ("android.intent.action.PACKAGE_REPLACED".equals(paramContext)) || ("android.intent.action.PACKAGE_CHANGED".equals(paramContext))) {
        f.this.b();
      }
      try
      {
        AppLockLib.getIns().getService();
        AppLockLib.getExecutor().execute(new c());
        return;
      }
      catch (Throwable paramContext) {}
    }
  }
}
