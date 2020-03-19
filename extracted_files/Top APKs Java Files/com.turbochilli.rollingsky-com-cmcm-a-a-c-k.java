package com.cmcm.a.a.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import com.cmcm.a.a.a.d;
import com.cmcm.a.a.a.l;
import com.cmcm.a.a.d.j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class k
  implements l
{
  private HashMap<String, b> a;
  private List<String> b;
  private Set<String> c;
  private BroadcastReceiver d;
  private ArrayList<String> e;
  
  public k() {}
  
  private b a(String paramString)
  {
    try
    {
      if (this.a == null) {
        return null;
      }
      paramString = (b)this.a.get(paramString);
      if (paramString != null)
      {
        boolean bool = paramString.d();
        if (bool) {}
      }
      else
      {
        return null;
      }
    }
    catch (Exception paramString)
    {
      paramString = null;
    }
    return paramString;
  }
  
  /* Error */
  private void a(Set<String> paramSet)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: aload_0
    //   6: monitorenter
    //   7: new 59	com/cmcm/a/a/a/a
    //   10: dup
    //   11: new 61	java/io/File
    //   14: dup
    //   15: invokestatic 66	com/cmcm/a/a/a/c:d	()Ljava/io/File;
    //   18: ldc 68
    //   20: invokespecial 71	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   23: invokespecial 74	com/cmcm/a/a/a/a:<init>	(Ljava/io/File;)V
    //   26: astore_3
    //   27: aload 4
    //   29: astore_2
    //   30: aload_3
    //   31: invokevirtual 77	com/cmcm/a/a/a/a:b	()Ljava/io/FileOutputStream;
    //   34: astore 4
    //   36: aload 4
    //   38: astore_2
    //   39: new 79	java/io/ObjectOutputStream
    //   42: dup
    //   43: aload 4
    //   45: invokespecial 82	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   48: astore 5
    //   50: aload 4
    //   52: astore_2
    //   53: aload 5
    //   55: aload_1
    //   56: invokeinterface 88 1 0
    //   61: invokevirtual 92	java/io/ObjectOutputStream:writeInt	(I)V
    //   64: aload 4
    //   66: astore_2
    //   67: aload_1
    //   68: invokeinterface 96 1 0
    //   73: astore_1
    //   74: aload 4
    //   76: astore_2
    //   77: aload_1
    //   78: invokeinterface 101 1 0
    //   83: ifeq +60 -> 143
    //   86: aload 4
    //   88: astore_2
    //   89: aload 5
    //   91: aload_1
    //   92: invokeinterface 105 1 0
    //   97: checkcast 107	java/lang/String
    //   100: invokevirtual 111	java/io/ObjectOutputStream:writeUTF	(Ljava/lang/String;)V
    //   103: goto -29 -> 74
    //   106: astore_1
    //   107: ldc 113
    //   109: new 115	java/lang/StringBuilder
    //   112: dup
    //   113: ldc 117
    //   115: invokespecial 119	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   118: aload_1
    //   119: invokevirtual 123	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   122: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: invokevirtual 130	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: invokestatic 135	com/cmcm/a/a/d/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   131: aload_3
    //   132: ifnull +8 -> 140
    //   135: aload_3
    //   136: aload_2
    //   137: invokevirtual 138	com/cmcm/a/a/a/a:b	(Ljava/io/FileOutputStream;)V
    //   140: aload_0
    //   141: monitorexit
    //   142: return
    //   143: aload 4
    //   145: astore_2
    //   146: aload 5
    //   148: invokevirtual 141	java/io/ObjectOutputStream:flush	()V
    //   151: aload 4
    //   153: astore_2
    //   154: aload_3
    //   155: aload 4
    //   157: invokevirtual 143	com/cmcm/a/a/a/a:a	(Ljava/io/FileOutputStream;)V
    //   160: goto -20 -> 140
    //   163: astore_1
    //   164: aload_0
    //   165: monitorexit
    //   166: aload_1
    //   167: athrow
    //   168: astore_1
    //   169: aconst_null
    //   170: astore_3
    //   171: goto -64 -> 107
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	174	0	this	k
    //   0	174	1	paramSet	Set<String>
    //   1	153	2	localObject	Object
    //   26	145	3	localA	com.cmcm.a.a.a.a
    //   3	153	4	localFileOutputStream	java.io.FileOutputStream
    //   48	99	5	localObjectOutputStream	java.io.ObjectOutputStream
    // Exception table:
    //   from	to	target	type
    //   30	36	106	java/lang/Throwable
    //   39	50	106	java/lang/Throwable
    //   53	64	106	java/lang/Throwable
    //   67	74	106	java/lang/Throwable
    //   77	86	106	java/lang/Throwable
    //   89	103	106	java/lang/Throwable
    //   146	151	106	java/lang/Throwable
    //   154	160	106	java/lang/Throwable
    //   7	27	163	finally
    //   30	36	163	finally
    //   39	50	163	finally
    //   53	64	163	finally
    //   67	74	163	finally
    //   77	86	163	finally
    //   89	103	163	finally
    //   107	131	163	finally
    //   135	140	163	finally
    //   146	151	163	finally
    //   154	160	163	finally
    //   7	27	168	java/lang/Throwable
  }
  
  private static boolean b(String paramString)
  {
    return com.cmcm.a.a.a.c.b().getPackageManager().checkPermission(paramString, com.cmcm.a.a.a.c.b().getPackageName()) == 0;
  }
  
  private void c()
  {
    for (;;)
    {
      try
      {
        d.a();
        this.e = new ArrayList();
        Iterator localIterator = this.a.values().iterator();
        if (localIterator.hasNext())
        {
          b localB = (b)localIterator.next();
          String str = localB.e();
          if ("notification_bar".equals(str))
          {
            i = 0;
            if (com.cmcm.a.a.a.c.a(str, "receiver_switch", i) != 1) {
              break label452;
            }
            i = 1;
            if (i != 0)
            {
              if ((str.equals("inst_app")) || (str.equals("inst_app2")) || (str.equals("uninst_app")) || (str.equals("uninst_app2")) || (str.equals("inst_app_list")) || (str.equals("screen_state")) || (str.equals("headset_plug"))) {
                continue;
              }
              if (str.equals("notification_bar"))
              {
                continue;
                if (bool2)
                {
                  localB.a(true);
                  this.e.add(str);
                  j.a().a(this.e);
                }
              }
              else
              {
                if (this.b == null) {
                  continue;
                }
                if ((str.equals("location")) || (str.equals("available_network_list")))
                {
                  if (!this.b.contains("android.permission.ACCESS_FINE_LOCATION")) {
                    continue;
                  }
                  bool2 = b("android.permission.ACCESS_FINE_LOCATION");
                  bool1 = bool2;
                  if (this.b.contains("android.permission.ACCESS_COARSE_LOCATION"))
                  {
                    if (bool2) {
                      break label457;
                    }
                    if (!b("android.permission.ACCESS_COARSE_LOCATION")) {
                      break label462;
                    }
                    break label457;
                  }
                  bool2 = bool1;
                  if (bool1) {
                    continue;
                  }
                  com.cmcm.a.a.d.c.c("ReceiverManager", str + " have no permission");
                  bool2 = bool1;
                  continue;
                }
                if (str.equals("bluetooth"))
                {
                  if (!this.b.contains("android.permission.BLUETOOTH")) {
                    continue;
                  }
                  bool1 = b("android.permission.BLUETOOTH");
                  continue;
                }
                if (((!str.equals("running_with_duration")) && (!str.equals("running_app_list2"))) || ((!this.b.contains("android.permission.GET_TASKS")) && (!this.b.contains("android.permission.PACKAGE_USAGE_STATS")))) {
                  continue;
                }
                if (b("android.permission.GET_TASKS")) {
                  break label467;
                }
                if (!b("android.permission.PACKAGE_USAGE_STATS")) {
                  break label472;
                }
                break label467;
              }
            }
            localB.a(false);
            continue;
            bool1 = false;
            continue;
            bool2 = false;
            continue;
          }
          else
          {
            i = 1;
            continue;
          }
          boolean bool2 = true;
          continue;
        }
        else
        {
          return;
        }
      }
      catch (Exception localException) {}
      label452:
      int i = 0;
      continue;
      label457:
      boolean bool1 = true;
      continue;
      label462:
      bool1 = false;
      continue;
      label467:
      bool1 = true;
      continue;
      label472:
      bool1 = false;
    }
  }
  
  /* Error */
  private static Set<String> d()
  {
    // Byte code:
    //   0: new 59	com/cmcm/a/a/a/a
    //   3: dup
    //   4: new 61	java/io/File
    //   7: dup
    //   8: invokestatic 66	com/cmcm/a/a/a/c:d	()Ljava/io/File;
    //   11: ldc 68
    //   13: invokespecial 71	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   16: invokespecial 74	com/cmcm/a/a/a/a:<init>	(Ljava/io/File;)V
    //   19: invokevirtual 258	com/cmcm/a/a/a/a:c	()Ljava/io/FileInputStream;
    //   22: astore_2
    //   23: new 260	java/io/ObjectInputStream
    //   26: dup
    //   27: aload_2
    //   28: invokespecial 263	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   31: astore 5
    //   33: aload 5
    //   35: invokevirtual 266	java/io/ObjectInputStream:readInt	()I
    //   38: istore_1
    //   39: iload_1
    //   40: ifle +75 -> 115
    //   43: new 268	java/util/HashSet
    //   46: dup
    //   47: iload_1
    //   48: invokespecial 270	java/util/HashSet:<init>	(I)V
    //   51: astore 4
    //   53: iconst_0
    //   54: istore_0
    //   55: aload 4
    //   57: astore_3
    //   58: iload_0
    //   59: iload_1
    //   60: if_icmpge +57 -> 117
    //   63: aload 4
    //   65: aload 5
    //   67: invokevirtual 273	java/io/ObjectInputStream:readUTF	()Ljava/lang/String;
    //   70: invokeinterface 274 2 0
    //   75: pop
    //   76: iload_0
    //   77: iconst_1
    //   78: iadd
    //   79: istore_0
    //   80: goto -25 -> 55
    //   83: astore_3
    //   84: aconst_null
    //   85: astore_2
    //   86: ldc 113
    //   88: new 115	java/lang/StringBuilder
    //   91: dup
    //   92: ldc_w 276
    //   95: invokespecial 119	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   98: aload_3
    //   99: invokevirtual 277	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   102: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: invokevirtual 130	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: invokestatic 135	com/cmcm/a/a/d/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   111: aload_2
    //   112: invokestatic 282	com/cmcm/a/a/a/m:a	(Ljava/io/Closeable;)V
    //   115: aconst_null
    //   116: astore_3
    //   117: aload_3
    //   118: areturn
    //   119: astore_3
    //   120: goto -34 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   54	26	0	i	int
    //   38	23	1	j	int
    //   22	90	2	localFileInputStream	java.io.FileInputStream
    //   57	1	3	localHashSet1	HashSet
    //   83	16	3	localException1	Exception
    //   116	2	3	localSet	Set<String>
    //   119	1	3	localException2	Exception
    //   51	13	4	localHashSet2	HashSet
    //   31	35	5	localObjectInputStream	java.io.ObjectInputStream
    // Exception table:
    //   from	to	target	type
    //   0	23	83	java/lang/Exception
    //   23	39	119	java/lang/Exception
    //   43	53	119	java/lang/Exception
    //   63	76	119	java/lang/Exception
  }
  
  private void e()
  {
    Object localObject2;
    Object localObject1;
    do
    {
      try
      {
        localObject3 = com.cmcm.a.a.a.c.b().getPackageManager().getInstalledPackages(0);
        this.c = new HashSet(((List)localObject3).size());
        Iterator localIterator = ((List)localObject3).iterator();
        while (localIterator.hasNext())
        {
          localObject2 = (PackageInfo)localIterator.next();
          this.c.add(((PackageInfo)localObject2).packageName);
        }
        localObject1 = d();
      }
      catch (Exception localException)
      {
        com.cmcm.a.a.d.c.c("ReceiverManager", "failed to processPackage" + localException.getMessage());
        return;
      }
      if ((localObject1 == null) || (((Set)localObject1).isEmpty()))
      {
        a(this.c);
        return;
      }
      localObject2 = new LinkedList();
      localObject3 = ((List)localObject3).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (PackageInfo)((Iterator)localObject3).next();
        if (!((Set)localObject1).remove(((PackageInfo)localObject4).packageName)) {
          ((List)localObject2).add(((PackageInfo)localObject4).packageName);
        }
      }
    } while ((((Set)localObject1).isEmpty()) && (((List)localObject2).isEmpty()));
    Object localObject4 = a("inst_app");
    Object localObject3 = a("uninst_app");
    if (((localObject4 != null) && (((b)localObject4).d())) || ((localObject3 != null) && (((b)localObject3).d())))
    {
      if ((localObject4 != null) && (((b)localObject4).d()))
      {
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject4 = h.c((String)((Iterator)localObject2).next());
          if (localObject4 != null) {
            j.a().a(10, "inst_app2", (String)localObject4);
          }
        }
      }
      if ((localObject3 != null) && (((b)localObject3).d()))
      {
        localObject1 = ((Set)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = n.c((String)((Iterator)localObject1).next());
          if (localObject2 != null) {
            j.a().a(10, "uninst_app2", (String)localObject2);
          }
        }
      }
    }
    a(this.c);
  }
  
  public final void a()
  {
    try
    {
      c();
      Iterator localIterator = this.a.values().iterator();
      while (localIterator.hasNext()) {
        ((b)localIterator.next()).a();
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  /* Error */
  public final void b()
  {
    // Byte code:
    //   0: ldc 113
    //   2: ldc_w 327
    //   5: invokestatic 135	com/cmcm/a/a/d/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   8: aload_0
    //   9: invokestatic 149	com/cmcm/a/a/a/c:b	()Landroid/content/Context;
    //   12: invokestatic 331	com/cmcm/a/a/a/m:k	(Landroid/content/Context;)Ljava/util/List;
    //   15: putfield 224	com/cmcm/a/a/c/k:b	Ljava/util/List;
    //   18: new 39	java/util/HashMap
    //   21: dup
    //   22: invokespecial 332	java/util/HashMap:<init>	()V
    //   25: astore_1
    //   26: getstatic 337	com/cmcm/a/a/c/f:a	Ljava/util/Map;
    //   29: invokeinterface 342 1 0
    //   34: invokeinterface 96 1 0
    //   39: astore_2
    //   40: aload_2
    //   41: invokeinterface 101 1 0
    //   46: ifeq +94 -> 140
    //   49: aload_2
    //   50: invokeinterface 105 1 0
    //   55: checkcast 107	java/lang/String
    //   58: astore_3
    //   59: ldc 113
    //   61: new 115	java/lang/StringBuilder
    //   64: dup
    //   65: ldc_w 344
    //   68: invokespecial 119	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   71: aload_3
    //   72: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: ldc_w 346
    //   78: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 130	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: invokestatic 135	com/cmcm/a/a/d/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   87: aload_1
    //   88: aload_3
    //   89: getstatic 337	com/cmcm/a/a/c/f:a	Ljava/util/Map;
    //   92: aload_3
    //   93: invokeinterface 347 2 0
    //   98: checkcast 349	java/lang/Class
    //   101: invokevirtual 352	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   104: checkcast 45	com/cmcm/a/a/c/b
    //   107: invokevirtual 356	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   110: pop
    //   111: goto -71 -> 40
    //   114: astore_2
    //   115: ldc 113
    //   117: new 115	java/lang/StringBuilder
    //   120: dup
    //   121: ldc_w 358
    //   124: invokespecial 119	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   127: aload_2
    //   128: invokevirtual 277	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   131: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: invokevirtual 130	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   137: invokestatic 135	com/cmcm/a/a/d/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   140: aload_0
    //   141: aload_1
    //   142: putfield 37	com/cmcm/a/a/c/k:a	Ljava/util/HashMap;
    //   145: aload_0
    //   146: invokespecial 323	com/cmcm/a/a/c/k:c	()V
    //   149: aload_0
    //   150: invokestatic 361	com/cmcm/a/a/a/c:a	(Lcom/cmcm/a/a/a/l;)V
    //   153: aload_0
    //   154: invokespecial 363	com/cmcm/a/a/c/k:e	()V
    //   157: aload_0
    //   158: new 8	com/cmcm/a/a/c/k$1
    //   161: dup
    //   162: aload_0
    //   163: invokespecial 366	com/cmcm/a/a/c/k$1:<init>	(Lcom/cmcm/a/a/c/k;)V
    //   166: putfield 368	com/cmcm/a/a/c/k:d	Landroid/content/BroadcastReceiver;
    //   169: new 370	android/content/IntentFilter
    //   172: dup
    //   173: invokespecial 371	android/content/IntentFilter:<init>	()V
    //   176: astore_1
    //   177: aload_1
    //   178: ldc_w 373
    //   181: invokevirtual 376	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   184: aload_1
    //   185: ldc_w 378
    //   188: invokevirtual 376	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   191: aload_1
    //   192: ldc_w 380
    //   195: invokevirtual 383	android/content/IntentFilter:addDataScheme	(Ljava/lang/String;)V
    //   198: aload_0
    //   199: getfield 368	com/cmcm/a/a/c/k:d	Landroid/content/BroadcastReceiver;
    //   202: aload_1
    //   203: invokestatic 386	com/cmcm/a/a/a/c:a	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)V
    //   206: return
    //   207: astore_1
    //   208: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	209	0	this	k
    //   25	178	1	localObject	Object
    //   207	1	1	localException1	Exception
    //   39	11	2	localIterator	Iterator
    //   114	14	2	localException2	Exception
    //   58	35	3	str	String
    // Exception table:
    //   from	to	target	type
    //   26	40	114	java/lang/Exception
    //   40	111	114	java/lang/Exception
    //   0	26	207	java/lang/Exception
    //   115	140	207	java/lang/Exception
    //   140	206	207	java/lang/Exception
  }
}
