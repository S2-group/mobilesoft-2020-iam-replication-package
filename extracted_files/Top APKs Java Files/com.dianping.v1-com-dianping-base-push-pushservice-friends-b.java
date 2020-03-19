package com.dianping.base.push.pushservice.friends;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.dianping.base.push.pushservice.util.c;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b
{
  public static ChangeQuickRedirect a;
  private static final String b = "b";
  private static List<a> c = new CopyOnWriteArrayList();
  private static List<String> d = new CopyOnWriteArrayList();
  private static String e = "";
  private static String f = "";
  private static String g = "";
  private static String h = "";
  private static Random i = new Random();
  private static String j = "";
  
  public b() {}
  
  public static void a(Context paramContext)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramContext;
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport(arrayOfObject, null, localChangeQuickRedirect, true, "7b585599629fbdddc0aa9e416ea04bf6", 4611686018427387904L))
    {
      PatchProxy.accessDispatch(arrayOfObject, null, localChangeQuickRedirect, true, "7b585599629fbdddc0aa9e416ea04bf6");
      return;
    }
    com.dianping.base.push.pushservice.b.b(b, "init FriendsWakeUpManager");
    if (paramContext == null) {
      return;
    }
    f(paramContext);
    e();
    com.sankuai.android.jarvis.b.a().execute(new Runnable()
    {
      public static ChangeQuickRedirect a;
      
      public void run()
      {
        Object[] arrayOfObject = new Object[0];
        ChangeQuickRedirect localChangeQuickRedirect = a;
        if (PatchProxy.isSupport(arrayOfObject, this, localChangeQuickRedirect, false, "ecdc8a4c784a5b217b106f18aa22d2d1", 4611686018427387904L))
        {
          PatchProxy.accessDispatch(arrayOfObject, this, localChangeQuickRedirect, false, "ecdc8a4c784a5b217b106f18aa22d2d1");
          return;
        }
        if (b.a().size() <= 0) {
          b.c(this.b);
        }
        if (b.b().size() <= 0)
        {
          b.d(this.b);
          b.e(this.b);
        }
      }
    });
  }
  
  public static void a(Context paramContext, Intent paramIntent)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramContext;
    arrayOfObject[1] = paramIntent;
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport(arrayOfObject, null, localChangeQuickRedirect, true, "119f9618dee10a427cf7d69dc398bf12", 4611686018427387904L))
    {
      PatchProxy.accessDispatch(arrayOfObject, null, localChangeQuickRedirect, true, "119f9618dee10a427cf7d69dc398bf12");
      return;
    }
    if (paramContext != null)
    {
      if (paramIntent == null) {
        return;
      }
      if ("com.dianping.push.START".equals(paramIntent.getAction()))
      {
        paramIntent = paramIntent.getStringExtra("source");
        if ((!TextUtils.isEmpty(paramIntent)) && (paramIntent.equals(paramContext.getPackageName())) && (c.a(paramContext)) && (c())) {
          i(paramContext);
        }
      }
      else if (("com.dianping.push.RECONNECT".equals(paramIntent.getAction())) && (c()))
      {
        j(paramContext);
      }
      return;
    }
  }
  
  private static boolean a(String paramString)
  {
    Object localObject = new Object[1];
    localObject[0] = paramString;
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport((Object[])localObject, null, localChangeQuickRedirect, true, "9504f0e63afce49ea79ea8069992f102", 4611686018427387904L)) {
      return ((Boolean)PatchProxy.accessDispatch((Object[])localObject, null, localChangeQuickRedirect, true, "9504f0e63afce49ea79ea8069992f102")).booleanValue();
    }
    if (!TextUtils.isEmpty(paramString))
    {
      if (d == null) {
        return false;
      }
      localObject = d.iterator();
      while (((Iterator)localObject).hasNext()) {
        if (paramString.equalsIgnoreCase((String)((Iterator)localObject).next())) {
          return true;
        }
      }
      return false;
    }
    return false;
  }
  
  public static void b(Context paramContext)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramContext;
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport(arrayOfObject, null, localChangeQuickRedirect, true, "a8059bd1489b145a713b1bf3627dee4a", 4611686018427387904L))
    {
      PatchProxy.accessDispatch(arrayOfObject, null, localChangeQuickRedirect, true, "a8059bd1489b145a713b1bf3627dee4a");
      return;
    }
    if (paramContext == null) {
      return;
    }
    com.dianping.base.push.pushservice.b.b(b, "reloadConfig");
    f(paramContext);
    c.clear();
    h(paramContext);
    k(paramContext);
  }
  
  private static boolean c()
  {
    Object[] arrayOfObject = new Object[0];
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport(arrayOfObject, null, localChangeQuickRedirect, true, "d38b2ca07e2594d90831c1a7a011fe1a", 4611686018427387904L)) {
      return ((Boolean)PatchProxy.accessDispatch(arrayOfObject, null, localChangeQuickRedirect, true, "d38b2ca07e2594d90831c1a7a011fe1a")).booleanValue();
    }
    if ((d.size() > 0) && (c.size() > 0))
    {
      if (d())
      {
        com.dianping.base.push.pushservice.b.b(b, "device is excluded, won't wake up");
        return false;
      }
      return true;
    }
    com.dianping.base.push.pushservice.b.b(b, "can't get installed pkgs or config is empty, won't wake up");
    return false;
  }
  
  private static boolean d()
  {
    Object localObject1 = new Object[0];
    Object localObject2 = a;
    if (PatchProxy.isSupport((Object[])localObject1, null, (ChangeQuickRedirect)localObject2, true, "6560f3e4c287e4757463b2b23ca6dcc3", 4611686018427387904L)) {
      return ((Boolean)PatchProxy.accessDispatch((Object[])localObject1, null, (ChangeQuickRedirect)localObject2, true, "6560f3e4c287e4757463b2b23ca6dcc3")).booleanValue();
    }
    if ((!TextUtils.isEmpty(e)) && (!TextUtils.isEmpty(Build.BRAND)) && (e.contains(Build.BRAND.toLowerCase())))
    {
      localObject1 = b;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("excludeBrand = ");
      ((StringBuilder)localObject2).append(Build.BRAND);
      com.dianping.base.push.pushservice.b.b((String)localObject1, ((StringBuilder)localObject2).toString());
      return true;
    }
    if ((!TextUtils.isEmpty(f)) && (!TextUtils.isEmpty(Build.VERSION.RELEASE)) && (f.contains(Build.VERSION.RELEASE.toLowerCase())))
    {
      localObject1 = b;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("excludeOS = ");
      ((StringBuilder)localObject2).append(Build.VERSION.RELEASE);
      com.dianping.base.push.pushservice.b.b((String)localObject1, ((StringBuilder)localObject2).toString());
      return true;
    }
    if ((!TextUtils.isEmpty(g)) && (!TextUtils.isEmpty(Build.MODEL)) && (g.contains(Build.MODEL.toLowerCase())))
    {
      localObject1 = b;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("excludeModel = ");
      ((StringBuilder)localObject2).append(Build.MODEL);
      com.dianping.base.push.pushservice.b.b((String)localObject1, ((StringBuilder)localObject2).toString());
      return true;
    }
    if ((!TextUtils.isEmpty(h)) && (!TextUtils.isEmpty(j)) && (h.contains(j.toLowerCase())))
    {
      localObject1 = b;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("excludeROM = ");
      ((StringBuilder)localObject2).append(j);
      com.dianping.base.push.pushservice.b.b((String)localObject1, ((StringBuilder)localObject2).toString());
      return true;
    }
    return false;
  }
  
  private static void e()
  {
    Object[] arrayOfObject = new Object[0];
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport(arrayOfObject, null, localChangeQuickRedirect, true, "c6b7ec4f28f26b7eb436b50205257872", 4611686018427387904L))
    {
      PatchProxy.accessDispatch(arrayOfObject, null, localChangeQuickRedirect, true, "c6b7ec4f28f26b7eb436b50205257872");
      return;
    }
    com.dianping.base.push.pushservice.b.b(b, "reading ROM Version");
    j = com.dianping.base.push.pushservice.util.d.b();
  }
  
  private static void f(Context paramContext)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramContext;
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport(arrayOfObject, null, localChangeQuickRedirect, true, "f0feeb82ff2a2661d395ef328c9aab69", 4611686018427387904L))
    {
      PatchProxy.accessDispatch(arrayOfObject, null, localChangeQuickRedirect, true, "f0feeb82ff2a2661d395ef328c9aab69");
      return;
    }
    com.dianping.base.push.pushservice.b.b(b, "reading Exclude Devices");
    try
    {
      e = com.dianping.base.push.pushservice.d.a(paramContext).a("wakeExcludeBrand", "").toLowerCase();
      f = com.dianping.base.push.pushservice.d.a(paramContext).a("wakeExcludeOS", "").toLowerCase();
      g = com.dianping.base.push.pushservice.d.a(paramContext).a("wakeExcludeModel", "").toLowerCase();
      h = com.dianping.base.push.pushservice.d.a(paramContext).a("wakeExcludeROM", "").toLowerCase();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void g(Context paramContext)
  {
    Object localObject = new Object[1];
    localObject[0] = paramContext;
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport((Object[])localObject, null, localChangeQuickRedirect, true, "6d2e300ca66a0a9a22bcb2851c70fc9f", 4611686018427387904L))
    {
      PatchProxy.accessDispatch((Object[])localObject, null, localChangeQuickRedirect, true, "6d2e300ca66a0a9a22bcb2851c70fc9f");
      return;
    }
    try
    {
      com.dianping.base.push.pushservice.b.b(b, "reading installed packages");
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        localObject = (PackageInfo)paramContext.next();
        d.add(((PackageInfo)localObject).packageName);
      }
    }
  }
  
  private static void h(Context paramContext)
  {
    Object localObject1 = new Object[1];
    int k = 0;
    localObject1[0] = paramContext;
    Object localObject4 = a;
    if (PatchProxy.isSupport((Object[])localObject1, null, (ChangeQuickRedirect)localObject4, true, "596be687a3f880059f8f249e66c34a9d", 4611686018427387904L))
    {
      PatchProxy.accessDispatch((Object[])localObject1, null, (ChangeQuickRedirect)localObject4, true, "596be687a3f880059f8f249e66c34a9d");
      return;
    }
    com.dianping.base.push.pushservice.b.b(b, "reading App Configs");
    Object localObject2;
    try
    {
      localObject1 = com.dianping.base.push.pushservice.d.a(paramContext).a("friendsConfig", "");
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      localObject2 = null;
    }
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      return;
    }
    Object localObject3;
    try
    {
      localObject2 = new JSONArray((String)localObject2);
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      localObject3 = null;
    }
    if (localObject3 != null)
    {
      if (localObject3.length() <= 0) {
        return;
      }
      while (k < localObject3.length())
      {
        try
        {
          Object localObject5 = localObject3.getJSONObject(k);
          localObject4 = ((JSONObject)localObject5).getString("pkg");
          if (a((String)localObject4))
          {
            localObject4 = new a(paramContext, (JSONObject)localObject5);
            c.add(localObject4);
          }
          else
          {
            localObject5 = b;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append((String)localObject4);
            localStringBuilder.append(" is not installed");
            com.dianping.base.push.pushservice.b.b((String)localObject5, localStringBuilder.toString());
          }
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
        }
        k += 1;
      }
      return;
    }
  }
  
  private static void i(Context paramContext)
  {
    Object[] arrayOfObject = new Object[1];
    int m = 0;
    arrayOfObject[0] = paramContext;
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport(arrayOfObject, null, localChangeQuickRedirect, true, "9b0aae0736b9e1f17a72202400b8eaa0", 4611686018427387904L))
    {
      PatchProxy.accessDispatch(arrayOfObject, null, localChangeQuickRedirect, true, "9b0aae0736b9e1f17a72202400b8eaa0");
      return;
    }
    com.dianping.base.push.pushservice.b.b(b, "start to wake up by app launch");
    if (paramContext != null) {
      if (c.isEmpty()) {
        return;
      }
    }
    for (;;)
    {
      int k;
      try
      {
        k = c.size() - 1;
        if (k >= 0)
        {
          if (!((a)c.get(k)).a(paramContext, i)) {
            break label129;
          }
          m = 1;
          break label129;
        }
        if (m != 0)
        {
          l(paramContext);
          return;
        }
      }
      catch (Throwable paramContext)
      {
        paramContext.printStackTrace();
      }
      return;
      return;
      label129:
      k -= 1;
    }
  }
  
  private static void j(Context paramContext)
  {
    Object[] arrayOfObject = new Object[1];
    int m = 0;
    arrayOfObject[0] = paramContext;
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport(arrayOfObject, null, localChangeQuickRedirect, true, "a3bb63fe9ab6ca6a30a852f3280f9b14", 4611686018427387904L))
    {
      PatchProxy.accessDispatch(arrayOfObject, null, localChangeQuickRedirect, true, "a3bb63fe9ab6ca6a30a852f3280f9b14");
      return;
    }
    com.dianping.base.push.pushservice.b.b(b, "start to wake up if app need");
    if (paramContext == null) {
      return;
    }
    int k = c.size() - 1;
    while (k >= 0)
    {
      if (((a)c.get(k)).b(paramContext, i)) {
        m = 1;
      }
      k -= 1;
    }
    if (m != 0) {
      l(paramContext);
    }
  }
  
  private static void k(Context paramContext)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramContext;
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport(arrayOfObject, null, localChangeQuickRedirect, true, "97b99d5a7be59e91ba4edd54f0c842fc", 4611686018427387904L))
    {
      PatchProxy.accessDispatch(arrayOfObject, null, localChangeQuickRedirect, true, "97b99d5a7be59e91ba4edd54f0c842fc");
      return;
    }
    if (paramContext == null) {
      return;
    }
    try
    {
      paramContext = com.dianping.base.push.pushservice.d.a(paramContext).a("friendsElapse", "");
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
    if (TextUtils.isEmpty(paramContext)) {
      return;
    }
    try
    {
      paramContext = new JSONObject(paramContext);
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
    if (paramContext != null)
    {
      int k = c.size() - 1;
      while (k >= 0)
      {
        try
        {
          ((a)c.get(k)).a(paramContext.optLong(((a)c.get(k)).a(), 0L));
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        k -= 1;
      }
    }
  }
  
  private static void l(Context paramContext)
  {
    Object localObject = new Object[1];
    localObject[0] = paramContext;
    ChangeQuickRedirect localChangeQuickRedirect = a;
    if (PatchProxy.isSupport((Object[])localObject, null, localChangeQuickRedirect, true, "fb0ab31481bf83d3f24c562e5289800f", 4611686018427387904L))
    {
      PatchProxy.accessDispatch((Object[])localObject, null, localChangeQuickRedirect, true, "fb0ab31481bf83d3f24c562e5289800f");
      return;
    }
    if (paramContext == null) {
      return;
    }
    localObject = new JSONObject();
    int k = c.size() - 1;
    while (k >= 0)
    {
      try
      {
        ((JSONObject)localObject).put(((a)c.get(k)).a(), ((a)c.get(k)).b());
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      k -= 1;
    }
    if (!TextUtils.isEmpty(((JSONObject)localObject).toString())) {
      try
      {
        com.dianping.base.push.pushservice.d.a(paramContext).b("friendsElapse", ((JSONObject)localObject).toString());
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
}
