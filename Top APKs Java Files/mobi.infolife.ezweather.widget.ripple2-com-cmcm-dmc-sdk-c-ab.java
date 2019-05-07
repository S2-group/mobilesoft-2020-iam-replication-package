package com.cmcm.dmc.sdk.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.dmc.sdk.a.a;
import com.cmcm.dmc.sdk.a.aa;
import com.cmcm.dmc.sdk.a.ag;
import com.cmcm.dmc.sdk.a.c;
import com.cmcm.dmc.sdk.a.p;
import com.cmcm.dmc.sdk.a.q;
import com.cmcm.dmc.sdk.report.j;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ab
  implements p
{
  private HashMap a;
  private List b;
  private Set c;
  private BroadcastReceiver d;
  
  public ab() {}
  
  private i a(String paramString)
  {
    if (this.a == null) {
      paramString = null;
    }
    i localI;
    do
    {
      return paramString;
      localI = (i)this.a.get(paramString);
      if (localI == null) {
        break;
      }
      paramString = localI;
    } while (localI.f());
    return null;
  }
  
  private void a(Set paramSet)
  {
    Object localObject = null;
    FileOutputStream localFileOutputStream = null;
    try
    {
      localA = new a(new File(aa.e(), "receiver_history_list.dat"));
      localObject = localFileOutputStream;
      try
      {
        localFileOutputStream = localA.b();
        localObject = localFileOutputStream;
        localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
        localObject = localFileOutputStream;
        localObjectOutputStream.writeInt(paramSet.size());
        localObject = localFileOutputStream;
        paramSet = paramSet.iterator();
        for (;;)
        {
          localObject = localFileOutputStream;
          if (!paramSet.hasNext()) {
            break;
          }
          localObject = localFileOutputStream;
          localObjectOutputStream.writeUTF((String)paramSet.next());
        }
        if (!c.a) {
          break label142;
        }
      }
      catch (Throwable paramSet) {}
    }
    catch (Throwable paramSet)
    {
      for (;;)
      {
        ObjectOutputStream localObjectOutputStream;
        label142:
        a localA = null;
      }
    }
    com.cmcm.dmc.sdk.a.g.a("ReceiverManager", "failed to writePackage" + paramSet.getMessage(), new Object[0]);
    if (localA != null) {
      localA.b(localObject);
    }
    return;
    localObject = localFileOutputStream;
    localObjectOutputStream.flush();
    localObject = localFileOutputStream;
    localA.a(localFileOutputStream);
  }
  
  private boolean b(String paramString)
  {
    boolean bool3 = true;
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (!paramString.equals("inst_app"))
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
                if (!paramString.equals("headset_plug")) {
                  break label84;
                }
                bool2 = bool1;
              }
            }
          }
        }
      }
    }
    return bool2;
    label84:
    if (this.b != null) {
      if (paramString.equals("location"))
      {
        if (!this.b.contains("android.permission.ACCESS_FINE_LOCATION")) {
          break label187;
        }
        bool1 = bool3;
      }
    }
    for (;;)
    {
      bool2 = bool1;
      if (bool1) {
        break;
      }
      bool2 = bool1;
      if (!c.a) {
        break;
      }
      com.cmcm.dmc.sdk.a.g.a("ReceiverManager", paramString + " have no permission", new Object[0]);
      return bool1;
      if (paramString.equals("bluetooth"))
      {
        bool1 = bool3;
        if (this.b.contains("android.permission.BLUETOOTH")) {}
      }
      else
      {
        label187:
        label238:
        do
        {
          do
          {
            bool1 = false;
            break;
            if (!paramString.equals("running_with_duration")) {
              break label238;
            }
            bool1 = bool3;
            if (this.b.contains("android.permission.GET_TASKS")) {
              break;
            }
          } while (!this.b.contains("android.permission.PACKAGE_USAGE_STATS"));
          bool1 = bool3;
          break;
        } while ((!paramString.equals("path")) || (!this.b.contains("android.permission.READ_EXTERNAL_STORAGE")));
        bool1 = bool3;
      }
    }
  }
  
  private void c()
  {
    Iterator localIterator = this.a.values().iterator();
    while (localIterator.hasNext())
    {
      i localI = (i)localIterator.next();
      localI.a(aa.a(localI.c(), "is_enabled", true));
    }
  }
  
  /* Error */
  private Set d()
  {
    // Byte code:
    //   0: new 48	com/cmcm/dmc/sdk/a/a
    //   3: dup
    //   4: new 50	java/io/File
    //   7: dup
    //   8: invokestatic 56	com/cmcm/dmc/sdk/a/aa:e	()Ljava/io/File;
    //   11: ldc 58
    //   13: invokespecial 61	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   16: invokespecial 64	com/cmcm/dmc/sdk/a/a:<init>	(Ljava/io/File;)V
    //   19: invokevirtual 206	com/cmcm/dmc/sdk/a/a:c	()Ljava/io/FileInputStream;
    //   22: astore_3
    //   23: new 208	java/io/ObjectInputStream
    //   26: dup
    //   27: aload_3
    //   28: invokespecial 211	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   31: astore 6
    //   33: aload 6
    //   35: invokevirtual 214	java/io/ObjectInputStream:readInt	()I
    //   38: istore_2
    //   39: iload_2
    //   40: ifle +90 -> 130
    //   43: new 216	java/util/HashSet
    //   46: dup
    //   47: iload_2
    //   48: invokespecial 218	java/util/HashSet:<init>	(I)V
    //   51: astore 5
    //   53: iconst_0
    //   54: istore_1
    //   55: aload 5
    //   57: astore 4
    //   59: iload_1
    //   60: iload_2
    //   61: if_icmpge +72 -> 133
    //   64: aload 5
    //   66: aload 6
    //   68: invokevirtual 221	java/io/ObjectInputStream:readUTF	()Ljava/lang/String;
    //   71: invokeinterface 224 2 0
    //   76: pop
    //   77: iload_1
    //   78: iconst_1
    //   79: iadd
    //   80: istore_1
    //   81: goto -26 -> 55
    //   84: astore 4
    //   86: aconst_null
    //   87: astore_3
    //   88: getstatic 106	com/cmcm/dmc/sdk/a/c:a	Z
    //   91: ifeq +35 -> 126
    //   94: ldc 108
    //   96: new 110	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 111	java/lang/StringBuilder:<init>	()V
    //   103: ldc -30
    //   105: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: aload 4
    //   110: invokevirtual 227	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   113: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: iconst_0
    //   120: anewarray 4	java/lang/Object
    //   123: invokestatic 129	com/cmcm/dmc/sdk/a/g:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   126: aload_3
    //   127: invokestatic 232	com/cmcm/dmc/sdk/a/q:a	(Ljava/io/Closeable;)V
    //   130: aconst_null
    //   131: astore 4
    //   133: aload 4
    //   135: areturn
    //   136: astore 4
    //   138: goto -50 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	this	ab
    //   54	27	1	i	int
    //   38	24	2	j	int
    //   22	105	3	localFileInputStream	java.io.FileInputStream
    //   57	1	4	localHashSet1	HashSet
    //   84	25	4	localException1	Exception
    //   131	3	4	localSet	Set
    //   136	1	4	localException2	Exception
    //   51	14	5	localHashSet2	HashSet
    //   31	36	6	localObjectInputStream	java.io.ObjectInputStream
    // Exception table:
    //   from	to	target	type
    //   0	23	84	java/lang/Exception
    //   23	39	136	java/lang/Exception
    //   43	53	136	java/lang/Exception
    //   64	77	136	java/lang/Exception
  }
  
  private void e()
  {
    Object localObject2;
    Object localObject3;
    Object localObject1;
    do
    {
      try
      {
        localObject2 = aa.c().getPackageManager().getInstalledPackages(0);
        this.c = new HashSet(((List)localObject2).size());
        Iterator localIterator = ((List)localObject2).iterator();
        while (localIterator.hasNext())
        {
          localObject3 = (PackageInfo)localIterator.next();
          this.c.add(((PackageInfo)localObject3).packageName);
        }
        localObject1 = d();
      }
      catch (Exception localException)
      {
        if (c.a) {
          com.cmcm.dmc.sdk.a.g.a("ReceiverManager", "failed to processPackage" + localException.getMessage(), new Object[0]);
        }
        return;
      }
      if ((localObject1 == null) || (((Set)localObject1).isEmpty()))
      {
        a(this.c);
        return;
      }
      localObject3 = new LinkedList();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject4 = (PackageInfo)((Iterator)localObject2).next();
        if (!((Set)localObject1).remove(((PackageInfo)localObject4).packageName)) {
          ((List)localObject3).add(((PackageInfo)localObject4).packageName);
        }
      }
    } while ((((Set)localObject1).isEmpty()) && (((List)localObject3).isEmpty()));
    Object localObject5 = a("inst_app");
    Object localObject4 = a("uninst_app");
    if (((localObject5 != null) && (((i)localObject5).f())) || ((localObject4 != null) && (((i)localObject4).f())))
    {
      localObject2 = j.a();
      if ((localObject5 != null) && (((i)localObject5).f()))
      {
        localObject3 = ((List)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject5 = w.b((String)((Iterator)localObject3).next());
          if (localObject5 != null) {
            ((j)localObject2).a(10, "inst_app2", (String)localObject5);
          }
        }
      }
      if ((localObject4 != null) && (((i)localObject4).f()))
      {
        localObject1 = ((Set)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject3 = g.b((String)((Iterator)localObject1).next());
          if (localObject3 != null) {
            ((j)localObject2).a(10, "uninst_app2", (String)localObject3);
          }
        }
      }
    }
    a(this.c);
  }
  
  public void a()
  {
    c();
    Iterator localIterator = this.a.values().iterator();
    while (localIterator.hasNext()) {
      ((i)localIterator.next()).a();
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
    if (c.a) {
      com.cmcm.dmc.sdk.a.g.a("ReceiverManager", "init()", new Object[0]);
    }
    this.b = q.l(aa.c());
    Object localObject1 = new HashMap();
    try
    {
      localAg = ag.a();
      if (!localAg.d()) {
        break label203;
      }
      localObject2 = new ArrayList();
      i = 0;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ag localAg;
        Object localObject2;
        int i;
        String str;
        label203:
        if (c.a)
        {
          com.cmcm.dmc.sdk.a.g.a("ReceiverManager", "failed to create receiver : " + localException.getMessage(), new Object[0]);
          continue;
          i += 2;
        }
      }
    }
    if (i < r.a.length)
    {
      str = (String)r.a[i];
      if ((localAg.a(str)) && (b(str)))
      {
        ((HashMap)localObject1).put(str, (i)((Class)r.a[(i + 1)]).newInstance());
        ((ArrayList)localObject2).add(str);
      }
    }
    else
    {
      j.a().a((List)localObject2);
      for (;;)
      {
        this.a = ((HashMap)localObject1);
        c();
        aa.a(this);
        e();
        this.d = new ac(this);
        localObject1 = new IntentFilter();
        ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_ADDED");
        ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_REMOVED");
        ((IntentFilter)localObject1).addDataScheme("package");
        aa.a(this.d, (IntentFilter)localObject1);
        return;
        i = 0;
        while (i < r.a.length)
        {
          localObject2 = (String)r.a[i];
          if (localAg.a((String)localObject2)) {
            ((HashMap)localObject1).put(localObject2, (i)((Class)r.a[(i + 1)]).newInstance());
          }
          i += 2;
        }
      }
    }
  }
}
