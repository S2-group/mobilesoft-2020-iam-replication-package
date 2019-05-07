package com.lmi.rescue.util;

import agx;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import awq;
import awr;
import aws;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.lmi.rescue.app.RescueApplication;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class PackageMonitorImpl
  extends BroadcastReceiver
  implements awq
{
  private static final IntentFilter a;
  private static volatile boolean b;
  private Context c;
  private HashMap<String, agx> d = new HashMap();
  private PackageManager e;
  
  static
  {
    IntentFilter localIntentFilter = new IntentFilter();
    a = localIntentFilter;
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    a.addAction("android.intent.action.PACKAGE_REMOVED");
    a.addAction("android.intent.action.PACKAGE_CHANGED");
    a.addDataScheme("package");
  }
  
  @Inject
  public PackageMonitorImpl(PackageManager paramPackageManager)
  {
    this.e = paramPackageManager;
    RescueApplication.b().a(new awr(this), false);
  }
  
  private void d()
  {
    try
    {
      List localList = this.e.getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        agx localAgx = agx.a(this.e, localPackageInfo);
        if (localAgx != null) {
          this.d.put(localPackageInfo.packageName, localAgx);
        }
        i += 1;
      }
      return;
    }
    finally {}
  }
  
  public final agx a(String paramString)
  {
    try
    {
      paramString = (agx)this.d.get(paramString);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public final void a()
  {
    if (this.c == null) {
      throw new IllegalStateException("was not registered");
    }
    this.c.unregisterReceiver(this);
    this.c = null;
  }
  
  public final void a(Context paramContext)
  {
    this.c = paramContext;
    paramContext.registerReceiver(this, a);
  }
  
  public final Map<String, agx> b()
  {
    try
    {
      HashMap localHashMap = this.d;
      return localHashMap;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  public final void b(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore_2
    //   4: aload_0
    //   5: getfield 53	com/lmi/rescue/util/PackageMonitorImpl:e	Landroid/content/pm/PackageManager;
    //   8: aload_1
    //   9: iconst_0
    //   10: invokevirtual 136	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   13: astore_3
    //   14: aload_3
    //   15: astore_2
    //   16: aload_2
    //   17: ifnonnull +31 -> 48
    //   20: aload_0
    //   21: getfield 51	com/lmi/rescue/util/PackageMonitorImpl:d	Ljava/util/HashMap;
    //   24: aload_1
    //   25: invokevirtual 139	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   28: pop
    //   29: getstatic 144	ags:a	Lagq;
    //   32: ldc -110
    //   34: iconst_1
    //   35: anewarray 148	java/lang/Object
    //   38: dup
    //   39: iconst_0
    //   40: aload_1
    //   41: aastore
    //   42: invokevirtual 153	agq:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: aload_0
    //   49: getfield 51	com/lmi/rescue/util/PackageMonitorImpl:d	Ljava/util/HashMap;
    //   52: aload_2
    //   53: getfield 100	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   56: aload_0
    //   57: getfield 53	com/lmi/rescue/util/PackageMonitorImpl:e	Landroid/content/pm/PackageManager;
    //   60: aload_2
    //   61: invokestatic 96	agx:a	(Landroid/content/pm/PackageManager;Landroid/content/pm/PackageInfo;)Lagx;
    //   64: invokevirtual 104	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   67: pop
    //   68: getstatic 144	ags:a	Lagq;
    //   71: ldc -101
    //   73: iconst_1
    //   74: anewarray 148	java/lang/Object
    //   77: dup
    //   78: iconst_0
    //   79: aload_1
    //   80: aastore
    //   81: invokevirtual 153	agq:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   84: goto -39 -> 45
    //   87: astore_1
    //   88: aload_0
    //   89: monitorexit
    //   90: aload_1
    //   91: athrow
    //   92: astore_3
    //   93: goto -77 -> 16
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	this	PackageMonitorImpl
    //   0	96	1	paramString	String
    //   3	58	2	localObject	Object
    //   13	2	3	localPackageInfo	PackageInfo
    //   92	1	3	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    // Exception table:
    //   from	to	target	type
    //   4	14	87	finally
    //   20	45	87	finally
    //   48	84	87	finally
    //   4	14	92	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getData().getSchemeSpecificPart();
    RescueApplication.b().a(new aws(this, paramContext), false);
  }
}
