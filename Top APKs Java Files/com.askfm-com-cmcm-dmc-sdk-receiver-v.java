package com.cmcm.dmc.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.dmc.sdk.base.a;
import com.cmcm.dmc.sdk.base.ad;
import com.cmcm.dmc.sdk.base.h;
import com.cmcm.dmc.sdk.base.s;
import com.cmcm.dmc.sdk.base.x;
import com.cmcm.dmc.sdk.base.z;
import com.cmcm.dmc.sdk.report.j;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class v
  implements h
{
  private HashMap a;
  private Set b;
  private BroadcastReceiver c;
  
  public v() {}
  
  private e a(String paramString)
  {
    if (this.a == null) {
      paramString = null;
    }
    e localE;
    do
    {
      return paramString;
      localE = (e)this.a.get(paramString);
      if (localE == null) {
        break;
      }
      paramString = localE;
    } while (localE.e());
    return null;
  }
  
  private void a(Set paramSet)
  {
    Object localObject = null;
    FileOutputStream localFileOutputStream = null;
    try
    {
      localA = new a(new File(s.e(), "receiver_history_list.dat"));
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
        if (!z.a) {
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
    ad.a("ReceiverManager", "failed to writePackage" + paramSet.getMessage(), new Object[0]);
    if (localA != null) {
      localA.b(localObject);
    }
    return;
    localObject = localFileOutputStream;
    localObjectOutputStream.flush();
    localObject = localFileOutputStream;
    localA.a(localFileOutputStream);
  }
  
  private void c()
  {
    Iterator localIterator = this.a.values().iterator();
    while (localIterator.hasNext())
    {
      e localE = (e)localIterator.next();
      localE.a(s.a(localE.a_(), "is_enabled", true));
    }
  }
  
  /* Error */
  private Set d()
  {
    // Byte code:
    //   0: new 46	com/cmcm/dmc/sdk/base/a
    //   3: dup
    //   4: new 48	java/io/File
    //   7: dup
    //   8: invokestatic 53	com/cmcm/dmc/sdk/base/s:e	()Ljava/io/File;
    //   11: ldc 55
    //   13: invokespecial 58	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   16: invokespecial 61	com/cmcm/dmc/sdk/base/a:<init>	(Ljava/io/File;)V
    //   19: invokevirtual 159	com/cmcm/dmc/sdk/base/a:c	()Ljava/io/FileInputStream;
    //   22: astore_3
    //   23: new 161	java/io/ObjectInputStream
    //   26: dup
    //   27: aload_3
    //   28: invokespecial 164	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   31: astore 6
    //   33: aload 6
    //   35: invokevirtual 167	java/io/ObjectInputStream:readInt	()I
    //   38: istore_2
    //   39: iload_2
    //   40: ifle +90 -> 130
    //   43: new 169	java/util/HashSet
    //   46: dup
    //   47: iload_2
    //   48: invokespecial 171	java/util/HashSet:<init>	(I)V
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
    //   68: invokevirtual 174	java/io/ObjectInputStream:readUTF	()Ljava/lang/String;
    //   71: invokeinterface 178 2 0
    //   76: pop
    //   77: iload_1
    //   78: iconst_1
    //   79: iadd
    //   80: istore_1
    //   81: goto -26 -> 55
    //   84: astore 4
    //   86: aconst_null
    //   87: astore_3
    //   88: getstatic 103	com/cmcm/dmc/sdk/base/z:a	Z
    //   91: ifeq +35 -> 126
    //   94: ldc 105
    //   96: new 107	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 108	java/lang/StringBuilder:<init>	()V
    //   103: ldc -76
    //   105: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: aload 4
    //   110: invokevirtual 181	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   113: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: iconst_0
    //   120: anewarray 4	java/lang/Object
    //   123: invokestatic 126	com/cmcm/dmc/sdk/base/ad:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   126: aload_3
    //   127: invokestatic 186	com/cmcm/dmc/sdk/base/i:a	(Ljava/io/Closeable;)V
    //   130: aconst_null
    //   131: astore 4
    //   133: aload 4
    //   135: areturn
    //   136: astore 4
    //   138: goto -50 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	this	v
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
        localObject2 = s.c().getPackageManager().getInstalledPackages(0);
        this.b = new HashSet(((List)localObject2).size());
        Iterator localIterator = ((List)localObject2).iterator();
        while (localIterator.hasNext())
        {
          localObject3 = (PackageInfo)localIterator.next();
          this.b.add(((PackageInfo)localObject3).packageName);
        }
        localObject1 = d();
      }
      catch (Exception localException)
      {
        if (z.a) {
          ad.a("ReceiverManager", "failed to processPackage" + localException.getMessage(), new Object[0]);
        }
        return;
      }
      if ((localObject1 == null) || (((Set)localObject1).isEmpty()))
      {
        a(this.b);
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
    if (((localObject5 != null) && (((e)localObject5).e())) || ((localObject4 != null) && (((e)localObject4).e())))
    {
      localObject2 = j.a();
      if ((localObject5 != null) && (((e)localObject5).e()))
      {
        localObject3 = ((List)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject5 = r.b((String)((Iterator)localObject3).next());
          if (localObject5 != null) {
            ((j)localObject2).a(10, "inst_app2", (String)localObject5);
          }
        }
      }
      if ((localObject4 != null) && (((e)localObject4).e()))
      {
        localObject1 = ((Set)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject3 = d.b((String)((Iterator)localObject1).next());
          if (localObject3 != null) {
            ((j)localObject2).a(10, "uninst_app2", (String)localObject3);
          }
        }
      }
    }
    a(this.b);
  }
  
  public void a()
  {
    c();
    Iterator localIterator = this.a.values().iterator();
    while (localIterator.hasNext()) {
      ((e)localIterator.next()).a();
    }
  }
  
  public void b()
  {
    if (z.a) {
      ad.a("ReceiverManager", "init()", new Object[0]);
    }
    Object localObject = new HashMap();
    try
    {
      x localX = x.a();
      int i = 0;
      while (i < m.a.length)
      {
        String str = (String)m.a[i];
        if (localX.a(str)) {
          ((HashMap)localObject).put(str, (e)((Class)m.a[(i + 1)]).newInstance());
        }
        i += 2;
      }
      return;
    }
    catch (Exception localException)
    {
      if (z.a) {
        ad.a("ReceiverManager", "failed to create receiver : " + localException.getMessage(), new Object[0]);
      }
      this.a = ((HashMap)localObject);
      c();
      s.a(this);
      e();
      this.c = new w(this);
      localObject = new IntentFilter();
      ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_ADDED");
      ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_REMOVED");
      ((IntentFilter)localObject).addDataScheme("package");
      s.a(this.c, (IntentFilter)localObject);
    }
  }
}
