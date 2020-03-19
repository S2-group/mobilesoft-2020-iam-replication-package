package com.dewmobile.kuaiya.ads;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.dewmobile.kuaiya.util.q;
import com.dewmobile.library.logging.DmLog;
import com.dewmobile.sdk.api.g;
import com.dewmobile.transfer.api.l;
import com.dewmobile.transfer.api.m;
import com.dewmobile.transfer.api.m.a;
import com.dewmobile.transfer.api.m.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class d
{
  public static boolean a = false;
  private static d b;
  private m c = m.a();
  private m.b d = new m.b()
  {
    public void a(int paramAnonymousInt, ContentValues paramAnonymousContentValues) {}
    
    public void a(l paramAnonymousL) {}
    
    public void a(m.a paramAnonymousA) {}
    
    public void a(List<l> paramAnonymousList) {}
    
    public void a(int[] paramAnonymousArrayOfInt) {}
    
    public void b(l paramAnonymousL) {}
    
    public void c(l paramAnonymousL) {}
    
    public void d(l paramAnonymousL) {}
    
    public void d_() {}
  };
  
  private d()
  {
    this.c.a(this.d);
    c();
  }
  
  public static d a()
  {
    return b;
  }
  
  public static void a(Context paramContext, Intent paramIntent)
  {
    if (e()) {}
    do
    {
      return;
      c();
    } while ((paramIntent == null) || ("android.intent.action.PACKAGE_ADDED".equals(paramIntent.getAction())) || ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramIntent.getAction())) || (!"android.intent.action.PACKAGE_REPLACED".equals(paramIntent.getAction())));
  }
  
  public static void a(g paramG, List<com.dewmobile.library.h.b> paramList)
  {
    Object localObject1 = com.dewmobile.library.h.c.c().a(paramG);
    if (localObject1 == null) {}
    for (;;)
    {
      return;
      Object localObject2 = ((com.dewmobile.library.h.a)localObject1).k();
      if ((localObject2 != null) && (((List)localObject2).size() > 0))
      {
        HashSet localHashSet2 = com.dewmobile.library.h.c.c().h();
        HashSet localHashSet1 = new HashSet();
        localObject1 = paramList;
        if (paramList == null) {
          localObject1 = new ArrayList();
        }
        paramList = localHashSet2.iterator();
        while (paramList.hasNext()) {
          localHashSet1.add(((com.dewmobile.library.h.b)paramList.next()).a);
        }
        paramList = ((List)localObject2).iterator();
        while (paramList.hasNext())
        {
          localObject2 = (com.dewmobile.library.h.b)paramList.next();
          if (!localHashSet1.contains(((com.dewmobile.library.h.b)localObject2).a))
          {
            ((com.dewmobile.library.h.b)localObject2).f = true;
            ((com.dewmobile.library.h.b)localObject2).h = ((com.dewmobile.library.h.b)localObject2).d(paramG.d().e());
            ((List)localObject1).add(localObject2);
          }
        }
      }
    }
  }
  
  public static void a(g paramG, List<com.dewmobile.library.h.b> paramList, boolean paramBoolean1, boolean paramBoolean2) {}
  
  public static void b()
  {
    try
    {
      if (b == null) {
        b = new d();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  public static void c()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 49	com/dewmobile/kuaiya/ads/d:e	()Z
    //   6: istore_0
    //   7: iload_0
    //   8: ifeq +7 -> 15
    //   11: ldc 2
    //   13: monitorexit
    //   14: return
    //   15: getstatic 20	com/dewmobile/kuaiya/ads/d:a	Z
    //   18: ifne -7 -> 11
    //   21: invokestatic 151	com/dewmobile/library/d/b:a	()Landroid/content/Context;
    //   24: ldc -103
    //   26: ldc -101
    //   28: invokestatic 160	com/workshop/a/d:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   31: iconst_1
    //   32: putstatic 20	com/dewmobile/kuaiya/ads/d:a	Z
    //   35: goto -24 -> 11
    //   38: astore_1
    //   39: ldc 2
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   6	2	0	bool	boolean
    //   38	5	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	7	38	finally
    //   15	35	38	finally
  }
  
  public static boolean e()
  {
    return !q.a("dis_mobovee_gp", "0").equals("0");
  }
  
  public static void f()
  {
    long l = System.currentTimeMillis();
    if (e()) {}
    for (;;)
    {
      return;
      c();
      try
      {
        List localList = com.dewmobile.library.d.b.a().getPackageManager().getInstalledPackages(128);
        if (localList == null) {
          continue;
        }
        String str = com.dewmobile.library.d.b.a().getPackageName();
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        for (;;)
        {
          if (i < localList.size())
          {
            ApplicationInfo localApplicationInfo = ((PackageInfo)localList.get(i)).applicationInfo;
            if ((localApplicationInfo != null) && (localApplicationInfo.sourceDir != null) && (((localApplicationInfo.flags & 0x1) == 0) || ((localApplicationInfo.flags & 0x80) != 0)) && (!localApplicationInfo.packageName.equals(str))) {
              localArrayList.add(localApplicationInfo.packageName);
            }
          }
          else
          {
            DmLog.d("xh", "mbj check ---- " + localArrayList.size());
            com.workshop.a.d.a(localArrayList, new com.workshop.a.b()
            {
              public void a(int paramAnonymousInt, com.workshop.a.a paramAnonymousA)
              {
                Object localObject = paramAnonymousA.a();
                DmLog.d("xh", "check result:" + ((List)localObject).size() + "   time:" + (System.currentTimeMillis() - this.a));
                paramAnonymousA = new JSONArray();
                localObject = ((List)localObject).iterator();
                for (;;)
                {
                  if (((Iterator)localObject).hasNext())
                  {
                    com.workshop.a.c localC = (com.workshop.a.c)((Iterator)localObject).next();
                    DmLog.d("xh", "check result:" + localC.b() + " " + localC.a());
                    JSONObject localJSONObject = new JSONObject();
                    try
                    {
                      localJSONObject.put("p", localC.b());
                      localJSONObject.put("m", localC.a());
                      paramAnonymousA.put(localJSONObject);
                    }
                    catch (JSONException localJSONException)
                    {
                      for (;;)
                      {
                        localJSONException.printStackTrace();
                      }
                    }
                  }
                }
                com.dewmobile.kuaiya.g.a.a(com.dewmobile.library.d.b.a(), "ZL_461_0004", paramAnonymousA.toString());
                if (paramAnonymousA.length() > 0)
                {
                  com.dewmobile.library.g.b.a().b("mobovee_materiels_infos", paramAnonymousA.toString());
                  DmLog.d("xh", "array:" + paramAnonymousA.toString());
                }
              }
              
              public void a(int paramAnonymousInt, String paramAnonymousString)
              {
                DmLog.e("xh", "check onFail:" + paramAnonymousInt + " s=" + paramAnonymousString + "   time:" + (System.currentTimeMillis() - this.a));
              }
            });
            return;
          }
          i += 1;
        }
        return;
      }
      catch (Exception localException) {}
    }
  }
  
  public void d()
  {
    if (this.c != null) {
      this.c.b(this.d);
    }
    a = false;
  }
}
