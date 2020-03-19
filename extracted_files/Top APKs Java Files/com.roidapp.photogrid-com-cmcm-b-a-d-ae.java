package com.cmcm.b.a.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.b.a.a.a;
import com.cmcm.b.a.a.ac;
import com.cmcm.b.a.a.ag;
import com.cmcm.b.a.a.j;
import com.cmcm.b.a.a.u;
import com.cmcm.b.a.e.p;
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

public final class ae
  implements j
{
  private HashMap a;
  private List b;
  private Set c;
  private BroadcastReceiver d;
  
  public ae() {}
  
  private l a(String paramString)
  {
    if (this.a == null) {
      paramString = null;
    }
    l localL;
    do
    {
      return paramString;
      localL = (l)this.a.get(paramString);
      if (localL == null) {
        break;
      }
      paramString = localL;
    } while (localL.f());
    return null;
  }
  
  private static void a(Set paramSet)
  {
    Object localObject = null;
    FileOutputStream localFileOutputStream = null;
    try
    {
      localA = new a(new File(u.e(), "receiver_history_list.dat"));
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
        if (!ac.a) {
          break label130;
        }
      }
      catch (Throwable paramSet) {}
    }
    catch (Throwable paramSet)
    {
      for (;;)
      {
        ObjectOutputStream localObjectOutputStream;
        label130:
        a localA = null;
      }
    }
    ag.a("ReceiverManager", "failed to writePackage" + paramSet.getMessage(), new Object[0]);
    if (localA != null) {
      localA.b(localObject);
    }
    return;
    localObject = localFileOutputStream;
    localObjectOutputStream.flush();
    localObject = localFileOutputStream;
    localA.a(localFileOutputStream);
  }
  
  private static boolean b(String paramString)
  {
    return u.c().getPackageManager().checkPermission(paramString, u.c().getPackageName()) == 0;
  }
  
  private void c()
  {
    Iterator localIterator = this.a.values().iterator();
    while (localIterator.hasNext()) {
      ((l)localIterator.next()).a(true);
    }
  }
  
  /* Error */
  private static Set d()
  {
    // Byte code:
    //   0: new 48	com/cmcm/b/a/a/a
    //   3: dup
    //   4: new 50	java/io/File
    //   7: dup
    //   8: invokestatic 56	com/cmcm/b/a/a/u:e	()Ljava/io/File;
    //   11: ldc 58
    //   13: invokespecial 61	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   16: invokespecial 64	com/cmcm/b/a/a/a:<init>	(Ljava/io/File;)V
    //   19: invokevirtual 173	com/cmcm/b/a/a/a:c	()Ljava/io/FileInputStream;
    //   22: astore_2
    //   23: new 175	java/io/ObjectInputStream
    //   26: dup
    //   27: aload_2
    //   28: invokespecial 178	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   31: astore 5
    //   33: aload 5
    //   35: invokevirtual 181	java/io/ObjectInputStream:readInt	()I
    //   38: istore_1
    //   39: iload_1
    //   40: ifle +84 -> 124
    //   43: new 183	java/util/HashSet
    //   46: dup
    //   47: iload_1
    //   48: invokespecial 185	java/util/HashSet:<init>	(I)V
    //   51: astore 4
    //   53: iconst_0
    //   54: istore_0
    //   55: aload 4
    //   57: astore_3
    //   58: iload_0
    //   59: iload_1
    //   60: if_icmpge +66 -> 126
    //   63: aload 4
    //   65: aload 5
    //   67: invokevirtual 188	java/io/ObjectInputStream:readUTF	()Ljava/lang/String;
    //   70: invokeinterface 192 2 0
    //   75: pop
    //   76: iload_0
    //   77: iconst_1
    //   78: iadd
    //   79: istore_0
    //   80: goto -25 -> 55
    //   83: astore_3
    //   84: aconst_null
    //   85: astore_2
    //   86: getstatic 106	com/cmcm/b/a/a/ac:a	Z
    //   89: ifeq +31 -> 120
    //   92: ldc 108
    //   94: new 110	java/lang/StringBuilder
    //   97: dup
    //   98: ldc -62
    //   100: invokespecial 114	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   103: aload_3
    //   104: invokevirtual 195	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   107: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: iconst_0
    //   114: anewarray 4	java/lang/Object
    //   117: invokestatic 130	com/cmcm/b/a/a/ag:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   120: aload_2
    //   121: invokestatic 200	com/cmcm/b/a/a/k:a	(Ljava/io/Closeable;)V
    //   124: aconst_null
    //   125: astore_3
    //   126: aload_3
    //   127: areturn
    //   128: astore_3
    //   129: goto -43 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   54	26	0	i	int
    //   38	23	1	j	int
    //   22	99	2	localFileInputStream	java.io.FileInputStream
    //   57	1	3	localHashSet1	HashSet
    //   83	21	3	localException1	Exception
    //   125	2	3	localSet	Set
    //   128	1	3	localException2	Exception
    //   51	13	4	localHashSet2	HashSet
    //   31	35	5	localObjectInputStream	java.io.ObjectInputStream
    // Exception table:
    //   from	to	target	type
    //   0	23	83	java/lang/Exception
    //   23	39	128	java/lang/Exception
    //   43	53	128	java/lang/Exception
    //   63	76	128	java/lang/Exception
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
        localObject2 = u.c().getPackageManager().getInstalledPackages(0);
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
        if (ac.a) {
          ag.a("ReceiverManager", "failed to processPackage" + localException.getMessage(), new Object[0]);
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
    if (((localObject5 != null) && (((l)localObject5).f())) || ((localObject4 != null) && (((l)localObject4).f())))
    {
      localObject2 = p.a();
      if ((localObject5 != null) && (((l)localObject5).f()))
      {
        localObject3 = ((List)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject5 = aa.b((String)((Iterator)localObject3).next());
          if (localObject5 != null) {
            ((p)localObject2).a(10, "inst_app2", (String)localObject5);
          }
        }
      }
      if ((localObject4 != null) && (((l)localObject4).f()))
      {
        localObject1 = ((Set)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject3 = k.b((String)((Iterator)localObject1).next());
          if (localObject3 != null) {
            ((p)localObject2).a(10, "uninst_app2", (String)localObject3);
          }
        }
      }
    }
    a(this.c);
  }
  
  public final void a()
  {
    c();
    Iterator localIterator = this.a.values().iterator();
    while (localIterator.hasNext()) {
      ((l)localIterator.next()).a();
    }
  }
  
  public final void b()
  {
    if (ac.a) {
      ag.a("ReceiverManager", "init()", new Object[0]);
    }
    this.b = com.cmcm.b.a.a.k.k(u.c());
    Object localObject = new HashMap();
    int i;
    String str;
    label106:
    int j;
    label121:
    label201:
    int k;
    boolean bool;
    try
    {
      com.cmcm.b.a.a.aa.a();
      localArrayList = new ArrayList();
      i = 0;
      if (i >= v.a.length) {
        break label672;
      }
      str = (String)v.a[i];
      if (("photo_stats".equals(str)) || ("music_stats".equals(str))) {
        break label693;
      }
      if (!"account".equals(str)) {
        break label688;
      }
    }
    catch (Exception localException)
    {
      ArrayList localArrayList;
      if (!ac.a) {
        break label371;
      }
      ag.a("ReceiverManager", "failed to create receiver : " + localException.getMessage(), new Object[0]);
      label371:
      this.a = ((HashMap)localObject);
      c();
      u.a(this);
      e();
      this.d = new af(this);
      localObject = new IntentFilter();
      ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_ADDED");
      ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_REMOVED");
      ((IntentFilter)localObject).addDataScheme("package");
      u.a(this.d, (IntentFilter)localObject);
      return;
    }
    if (u.a(str, "receiver_switch", j) == 1)
    {
      j = 1;
      if (j != 0) {
        if ((!str.equals("inst_app")) && (!str.equals("inst_app2")) && (!str.equals("uninst_app")) && (!str.equals("uninst_app2")) && (!str.equals("inst_app_list")) && (!str.equals("screen_state"))) {
          if (str.equals("headset_plug"))
          {
            break label698;
            if (k == 0) {
              break label704;
            }
            ((HashMap)localObject).put(str, (l)((Class)v.a[(i + 1)]).newInstance());
            localArrayList.add(str);
            break label704;
          }
          else if (this.b != null)
          {
            if (str.equals("location"))
            {
              if (!this.b.contains("android.permission.ACCESS_FINE_LOCATION")) {
                break label683;
              }
              bool = b("android.permission.ACCESS_FINE_LOCATION");
            }
          }
        }
      }
    }
    for (;;)
    {
      k = bool;
      if (bool) {
        break label201;
      }
      k = bool;
      if (!ac.a) {
        break label201;
      }
      ag.a("ReceiverManager", str + " have no permission", new Object[0]);
      k = bool;
      break label201;
      for (;;)
      {
        if (str.equals("bluetooth"))
        {
          if (!this.b.contains("android.permission.BLUETOOTH")) {
            break label683;
          }
          bool = b("android.permission.BLUETOOTH");
          break;
        }
        if (str.equals("running_with_duration"))
        {
          if ((!this.b.contains("android.permission.GET_TASKS")) && (!this.b.contains("android.permission.PACKAGE_USAGE_STATS"))) {
            break label683;
          }
          if (b("android.permission.GET_TASKS")) {
            break label716;
          }
          if (!b("android.permission.PACKAGE_USAGE_STATS")) {
            break label721;
          }
          break label716;
        }
        if ((str.equals("path")) || (str.equals("photo_stats")) || (str.equals("music_stats")))
        {
          if (!this.b.contains("android.permission.READ_EXTERNAL_STORAGE")) {
            break label683;
          }
          bool = b("android.permission.READ_EXTERNAL_STORAGE");
          break;
        }
        if (str.equals("available_network_list"))
        {
          if (!this.b.contains("android.permission.ACCESS_WIFI_STATE")) {
            break label683;
          }
          bool = b("android.permission.ACCESS_WIFI_STATE");
          break;
        }
        if ((!str.equals("account")) || (!this.b.contains("android.permission.GET_ACCOUNTS"))) {
          break label683;
        }
        bool = b("android.permission.GET_ACCOUNTS");
        break;
        label672:
        p.a().a(localException);
      }
      label683:
      bool = false;
      continue;
      label688:
      j = 1;
      break label106;
      label693:
      j = 0;
      break label106;
      label698:
      k = 1;
      break label201;
      label704:
      i += 2;
      break;
      j = 0;
      break label121;
      label716:
      bool = true;
      continue;
      label721:
      bool = false;
    }
  }
}
