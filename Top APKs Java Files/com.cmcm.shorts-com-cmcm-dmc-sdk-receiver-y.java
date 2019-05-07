package com.cmcm.dmc.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.dmc.sdk.base.a;
import com.cmcm.dmc.sdk.base.ai;
import com.cmcm.dmc.sdk.base.aj;
import com.cmcm.dmc.sdk.base.k;
import com.cmcm.dmc.sdk.base.s;
import com.cmcm.dmc.sdk.base.u;
import com.cmcm.dmc.sdk.report.q;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class y
  implements ai
{
  private static final String a = "ReceiverManager";
  private static final String b = "receiver_history_list.dat";
  private static final String g = "path";
  private HashMap c;
  private List d;
  private Set e;
  private BroadcastReceiver f;
  
  public y() {}
  
  private c a(String paramString)
  {
    if (this.c != null)
    {
      c localC = (c)this.c.get(paramString);
      if (localC != null)
      {
        paramString = localC;
        if (localC.e()) {
          return paramString;
        }
      }
    }
    paramString = null;
    return paramString;
  }
  
  private void a(Set paramSet)
  {
    for (;;)
    {
      try
      {
        localObject1 = new a(new File(k.f(), "receiver_history_list.dat"));
      }
      catch (Throwable localThrowable1)
      {
        Object localObject1;
        Object localObject2;
        Object localObject3;
        paramSet = null;
        continue;
      }
      try
      {
        localObject2 = ((a)localObject1).b();
      }
      catch (Throwable localThrowable2)
      {
        paramSet = (Set)localObject1;
        localObject1 = localThrowable2;
        continue;
      }
      try
      {
        localObject3 = new ObjectOutputStream((OutputStream)localObject2);
        ((ObjectOutputStream)localObject3).writeInt(paramSet.size());
        paramSet = paramSet.iterator();
        if (paramSet.hasNext())
        {
          ((ObjectOutputStream)localObject3).writeUTF((String)paramSet.next());
        }
        else
        {
          ((ObjectOutputStream)localObject3).flush();
          ((a)localObject1).a((FileOutputStream)localObject2);
          return;
        }
      }
      catch (Throwable localThrowable3)
      {
        paramSet = (Set)localObject1;
        localObject1 = localObject2;
        localObject2 = localThrowable3;
      }
    }
    localObject3 = null;
    localObject2 = localObject1;
    localObject1 = localObject3;
    if (u.a)
    {
      localObject3 = new StringBuilder("failed to writePackage");
      ((StringBuilder)localObject3).append(((Throwable)localObject2).getMessage());
      com.cmcm.dmc.sdk.base.y.a("ReceiverManager", ((StringBuilder)localObject3).toString());
    }
    if (paramSet != null) {
      paramSet.b((FileOutputStream)localObject1);
    }
  }
  
  private boolean b(String paramString)
  {
    boolean bool4 = paramString.equals("inst_app");
    boolean bool1 = true;
    boolean bool3 = true;
    boolean bool2 = bool1;
    if (!bool4)
    {
      bool2 = bool1;
      if (!paramString.equals("inst_app2"))
      {
        bool2 = bool1;
        if (!paramString.equals("uninst_app"))
        {
          bool2 = bool1;
          if (!paramString.equals("uninst_app2"))
          {
            bool2 = bool1;
            if (!paramString.equals("inst_app_list"))
            {
              bool2 = bool1;
              if (!paramString.equals("screen_state"))
              {
                bool2 = bool1;
                if (!paramString.equals("headset_plug"))
                {
                  if (paramString.equals("notification_bar")) {
                    return true;
                  }
                  Object localObject;
                  if (this.d != null) {
                    if (paramString.equals("location"))
                    {
                      if (this.d.contains("android.permission.ACCESS_FINE_LOCATION")) {
                        localObject = "android.permission.ACCESS_FINE_LOCATION";
                      }
                    }
                    else {
                      for (;;)
                      {
                        bool1 = c((String)localObject);
                        break label348;
                        if (paramString.equals("bluetooth"))
                        {
                          if (!this.d.contains("android.permission.BLUETOOTH")) {
                            break;
                          }
                          localObject = "android.permission.BLUETOOTH";
                        }
                        else
                        {
                          if (paramString.equals("running_with_duration"))
                          {
                            if ((!this.d.contains("android.permission.GET_TASKS")) && (!this.d.contains("android.permission.PACKAGE_USAGE_STATS"))) {
                              break;
                            }
                            bool1 = bool3;
                            if (c("android.permission.GET_TASKS")) {
                              break label348;
                            }
                            if (!c("android.permission.PACKAGE_USAGE_STATS")) {
                              break;
                            }
                            bool1 = bool3;
                            break label348;
                          }
                          if ((!paramString.equals("path")) && (!paramString.equals("photo_stats")) && (!paramString.equals("music_stats")))
                          {
                            if (paramString.equals("available_network_list"))
                            {
                              if (!this.d.contains("android.permission.ACCESS_WIFI_STATE")) {
                                break;
                              }
                              localObject = "android.permission.ACCESS_WIFI_STATE";
                            }
                            else
                            {
                              if ((!paramString.equals("account")) || (!this.d.contains("android.permission.GET_ACCOUNTS"))) {
                                break;
                              }
                              localObject = "android.permission.GET_ACCOUNTS";
                            }
                          }
                          else
                          {
                            if (!this.d.contains("android.permission.READ_EXTERNAL_STORAGE")) {
                              break;
                            }
                            localObject = "android.permission.READ_EXTERNAL_STORAGE";
                          }
                        }
                      }
                    }
                  }
                  bool1 = false;
                  label348:
                  bool2 = bool1;
                  if (!bool1)
                  {
                    bool2 = bool1;
                    if (u.a)
                    {
                      localObject = new StringBuilder();
                      ((StringBuilder)localObject).append(paramString);
                      ((StringBuilder)localObject).append(" have no permission");
                      com.cmcm.dmc.sdk.base.y.a("ReceiverManager", ((StringBuilder)localObject).toString());
                      bool2 = bool1;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return bool2;
  }
  
  private boolean c(String paramString)
  {
    int i = k.d().getPackageManager().checkPermission(paramString, k.d().getPackageName());
    boolean bool = false;
    if (i == 0) {
      bool = true;
    }
    return bool;
  }
  
  private void d()
  {
    Iterator localIterator = this.c.values().iterator();
    while (localIterator.hasNext())
    {
      c localC = (c)localIterator.next();
      localC.f();
      localC.a(true);
    }
  }
  
  /* Error */
  private Set e()
  {
    // Byte code:
    //   0: new 57	com/cmcm/dmc/sdk/base/a
    //   3: dup
    //   4: new 59	java/io/File
    //   7: dup
    //   8: invokestatic 64	com/cmcm/dmc/sdk/base/k:f	()Ljava/io/File;
    //   11: ldc 13
    //   13: invokespecial 67	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   16: invokespecial 70	com/cmcm/dmc/sdk/base/a:<init>	(Ljava/io/File;)V
    //   19: invokevirtual 239	com/cmcm/dmc/sdk/base/a:c	()Ljava/io/FileInputStream;
    //   22: astore_3
    //   23: new 241	java/io/ObjectInputStream
    //   26: dup
    //   27: aload_3
    //   28: invokespecial 244	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   31: astore 4
    //   33: aload 4
    //   35: invokevirtual 247	java/io/ObjectInputStream:readInt	()I
    //   38: istore_2
    //   39: iload_2
    //   40: ifle +87 -> 127
    //   43: new 249	java/util/HashSet
    //   46: dup
    //   47: iload_2
    //   48: invokespecial 251	java/util/HashSet:<init>	(I)V
    //   51: astore 5
    //   53: iconst_0
    //   54: istore_1
    //   55: iload_1
    //   56: iload_2
    //   57: if_icmpge +72 -> 129
    //   60: aload 5
    //   62: aload 4
    //   64: invokevirtual 254	java/io/ObjectInputStream:readUTF	()Ljava/lang/String;
    //   67: invokeinterface 257 2 0
    //   72: pop
    //   73: iload_1
    //   74: iconst_1
    //   75: iadd
    //   76: istore_1
    //   77: goto -22 -> 55
    //   80: astore 4
    //   82: aconst_null
    //   83: astore_3
    //   84: getstatic 118	com/cmcm/dmc/sdk/base/u:a	Z
    //   87: ifeq +36 -> 123
    //   90: new 120	java/lang/StringBuilder
    //   93: dup
    //   94: ldc_w 259
    //   97: invokespecial 124	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   100: astore 5
    //   102: aload 5
    //   104: aload 4
    //   106: invokevirtual 260	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   109: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: ldc 10
    //   115: aload 5
    //   117: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: invokestatic 140	com/cmcm/dmc/sdk/base/y:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   123: aload_3
    //   124: invokestatic 265	com/cmcm/dmc/sdk/base/aj:a	(Ljava/io/Closeable;)V
    //   127: aconst_null
    //   128: areturn
    //   129: aload 5
    //   131: areturn
    //   132: astore 4
    //   134: goto -50 -> 84
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	137	0	this	y
    //   54	23	1	i	int
    //   38	20	2	j	int
    //   22	102	3	localFileInputStream	java.io.FileInputStream
    //   31	32	4	localObjectInputStream	java.io.ObjectInputStream
    //   80	25	4	localException1	Exception
    //   132	1	4	localException2	Exception
    //   51	79	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	23	80	java/lang/Exception
    //   23	39	132	java/lang/Exception
    //   43	53	132	java/lang/Exception
    //   60	73	132	java/lang/Exception
  }
  
  private void f()
  {
    try
    {
      Object localObject2 = k.d().getPackageManager().getInstalledPackages(0);
      this.e = new HashSet(((List)localObject2).size());
      Object localObject1 = ((List)localObject2).iterator();
      Object localObject3;
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (PackageInfo)((Iterator)localObject1).next();
        this.e.add(((PackageInfo)localObject3).packageName);
      }
      localObject1 = e();
      if ((localObject1 != null) && (!((Set)localObject1).isEmpty()))
      {
        localObject3 = new LinkedList();
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject4 = (PackageInfo)((Iterator)localObject2).next();
          if (!((Set)localObject1).remove(((PackageInfo)localObject4).packageName)) {
            ((List)localObject3).add(((PackageInfo)localObject4).packageName);
          }
        }
        if ((((Set)localObject1).isEmpty()) && (((List)localObject3).isEmpty())) {
          return;
        }
        Object localObject5 = a("inst_app");
        Object localObject4 = a("uninst_app");
        if (((localObject5 != null) && (((c)localObject5).e())) || ((localObject4 != null) && (((c)localObject4).e())))
        {
          localObject2 = q.a();
          if ((localObject5 != null) && (((c)localObject5).e()))
          {
            localObject3 = ((List)localObject3).iterator();
            while (((Iterator)localObject3).hasNext())
            {
              localObject5 = t.c((String)((Iterator)localObject3).next());
              if (localObject5 != null) {
                ((q)localObject2).a(10, "inst_app2", (String)localObject5);
              }
            }
          }
          if ((localObject4 != null) && (((c)localObject4).e()))
          {
            localObject1 = ((Set)localObject1).iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject3 = am.c((String)((Iterator)localObject1).next());
              if (localObject3 != null) {
                ((q)localObject2).a(10, "uninst_app2", (String)localObject3);
              }
            }
          }
        }
      }
      for (localObject1 = this.e;; localObject1 = this.e)
      {
        a((Set)localObject1);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      if (u.a)
      {
        localObject2 = new StringBuilder("failed to processPackage");
        ((StringBuilder)localObject2).append(localException.getMessage());
        com.cmcm.dmc.sdk.base.y.a("ReceiverManager", ((StringBuilder)localObject2).toString());
      }
    }
  }
  
  public void a()
  {
    d();
    Iterator localIterator = this.c.values().iterator();
    while (localIterator.hasNext()) {
      ((c)localIterator.next()).a();
    }
  }
  
  public void a(String paramString, Object... paramVarArgs)
  {
    paramString = a(paramString);
    if (paramString != null) {
      paramString.a(paramVarArgs);
    }
  }
  
  public void b()
  {
    if (u.a) {
      com.cmcm.dmc.sdk.base.y.a("ReceiverManager", "init()");
    }
    this.d = aj.m(k.d());
    Object localObject1 = new HashMap();
    for (;;)
    {
      int i;
      try
      {
        s localS = s.a();
        localObject2 = new ArrayList();
        i = 0;
        if (i < o.s.length)
        {
          String str = (String)o.s[i];
          if ((!localS.a(str)) || (!b(str))) {
            break label234;
          }
          ((HashMap)localObject1).put(str, (c)((Class)o.s[(i + 1)]).newInstance());
          ((ArrayList)localObject2).add(str);
          break label234;
        }
        q.a().a((List)localObject2);
      }
      catch (Exception localException)
      {
        Object localObject2;
        if (u.a)
        {
          localObject2 = new StringBuilder("failed to create receiver : ");
          ((StringBuilder)localObject2).append(localException.getMessage());
          com.cmcm.dmc.sdk.base.y.a("ReceiverManager", ((StringBuilder)localObject2).toString());
        }
      }
      this.c = ((HashMap)localObject1);
      d();
      k.a(this);
      f();
      this.f = new z(this);
      localObject1 = new IntentFilter();
      ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_ADDED");
      ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_REMOVED");
      ((IntentFilter)localObject1).addDataScheme("package");
      k.a(this.f, (IntentFilter)localObject1);
      return;
      label234:
      i += 2;
    }
  }
  
  public void c()
  {
    k.b(this);
    k.a(this.f);
    Object localObject = this.c;
    this.c = null;
    localObject = ((HashMap)localObject).values().iterator();
    while (((Iterator)localObject).hasNext()) {
      ((c)((Iterator)localObject).next()).a(false);
    }
  }
}
