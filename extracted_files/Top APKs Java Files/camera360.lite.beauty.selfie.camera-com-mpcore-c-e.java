package com.mpcore.c;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.mobpower.core.activity.InnerBrowserActivity;
import com.mpcore.common.a.d;
import com.mpcore.common.c.g;
import com.mpcore.common.e.j;
import com.mpcore.common.f.f;
import com.mpcore.common.i.i;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class e
{
  private static volatile e a;
  private WeakReference<Context> b;
  
  private e(Context paramContext)
  {
    this.b = new WeakReference(paramContext);
  }
  
  private static Cursor a(String paramString1, String paramString2, Context paramContext)
  {
    return paramContext.getContentResolver().query(Uri.parse(paramString1), new String[] { "title" }, "title=?", new String[] { paramString2 }, null);
  }
  
  public static e a(Context paramContext)
  {
    if (a == null) {}
    try
    {
      if (a == null) {
        a = new e(paramContext);
      }
      return a;
    }
    finally {}
  }
  
  private String a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
      if (paramContext == null) {
        return "";
      }
      Iterator localIterator = paramContext.iterator();
      for (paramContext = paramString; localIterator.hasNext(); paramContext = paramString)
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)localIterator.next()).providers;
        if (arrayOfProviderInfo != null)
        {
          int j = arrayOfProviderInfo.length;
          int i = 0;
          for (;;)
          {
            paramString = paramContext;
            if (i >= j) {
              break;
            }
            paramString = arrayOfProviderInfo[i];
            if (!b("com.android.launcher.permission.READ_SETTINGS")) {
              paramContext = "com.google.android.launcher.permission.READ_SETTINGS";
            }
            if (((paramContext.equals(paramString.readPermission)) || (paramContext.equals(paramString.writePermission))) && (!TextUtils.isEmpty(paramString.authority)) && (paramString.authority.contains(".launcher.settings")))
            {
              paramContext = paramString.authority;
              return paramContext;
            }
            i += 1;
          }
        }
        paramString = paramContext;
      }
      return "";
    }
    catch (Exception paramContext) {}
  }
  
  private void a(final int paramInt)
  {
    boolean bool3 = true;
    boolean bool2 = true;
    Context localContext = (Context)this.b.get();
    if (localContext == null) {
      return;
    }
    com.mpcore.common.g.a localA = com.mpcore.common.g.b.a(localContext).a(d.a().c());
    if (localA == null)
    {
      com.mpcore.common.i.e.b("stManager", "策略为空，结束");
      return;
    }
    int i;
    label66:
    long l;
    boolean bool1;
    if (localA.aY() == com.mpcore.common.a.b.e)
    {
      i = 1;
      l = localA.bb();
      if (paramInt != 5) {
        break label361;
      }
      if (localA.bd() != com.mpcore.common.a.b.e) {
        break label186;
      }
      bool1 = true;
    }
    for (;;)
    {
      label92:
      com.mpcore.common.i.e.b("stManager", "策略获取--->是否无视间隔求新的sci(" + bool1 + ")");
      if (i == 0)
      {
        StringBuilder localStringBuilder = new StringBuilder("广告位关闭(");
        if (i == 0) {}
        for (bool1 = bool2;; bool1 = false)
        {
          com.mpcore.common.i.e.b("stManager", bool1 + "),结束流程。");
          if (localA.aY() != 0) {
            break;
          }
          com.mpcore.common.i.e.b("stManager", "结束流程--根据策略删除当前short。");
          d(localContext);
          return;
          i = 0;
          break label66;
          label186:
          bool1 = false;
          break label92;
        }
      }
      if (System.currentTimeMillis() - b.a((Context)this.b.get()).longValue() > l) {}
      for (bool2 = bool3;; bool2 = false)
      {
        com.mpcore.common.i.e.b("stManager", "策略获取--->是否间隔间隔(" + bool2 + ")");
        if ((!bool1) && (!bool2)) {
          break;
        }
        com.mpcore.common.i.e.b("stManager", "开始请求广告(间隔：" + bool1 + " 或者 canRequest:" + bool2 + ") 满足");
        com.mpcore.common.i.b.a.a().a(new Runnable()
        {
          public final void run()
          {
            e.a(e.this, paramInt);
          }
        }, 1000L);
        return;
      }
      com.mpcore.common.i.e.b("stManager", "不满足请求逻辑，终止流程(间隔：" + bool1 + " || canRequest:" + bool2 + "),结束流程。");
      return;
      label361:
      bool1 = false;
    }
  }
  
  private static void a(Activity paramActivity)
  {
    if (paramActivity != null) {
      paramActivity.finish();
    }
  }
  
  private void a(Context paramContext, com.mpcore.common.e.a paramA, int paramInt)
  {
    if (paramA == null) {
      return;
    }
    Intent localIntent1 = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    localIntent1.putExtra("android.intent.extra.shortcut.NAME", paramA.c());
    Intent localIntent2 = new Intent(paramContext, InnerBrowserActivity.class);
    localIntent2.setAction("android.intent.action.VIEW");
    localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
    paramContext.sendBroadcast(localIntent1);
    if (g())
    {
      if (c(paramA.c()) < paramInt) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        com.mpcore.common.i.e.b("stManager", "sci 删除成功");
        return;
      }
      com.mpcore.common.i.e.b("stManager", "sci 删除失败(未知原因)");
      return;
    }
    com.mpcore.common.i.e.b("stManager", "sci 删除失败(无权限)");
  }
  
  private String b(Context paramContext)
  {
    String str2 = a(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      str1 = c();
      str1 = a(paramContext, str1 + ".permission.READ_SETTINGS");
    }
    if (TextUtils.isEmpty(str1)) {
      int i = Build.VERSION.SDK_INT;
    }
    return c(paramContext);
  }
  
  private boolean b(Context paramContext, String paramString)
  {
    Context localContext2 = null;
    Context localContext1 = null;
    boolean bool2;
    if ((paramContext == null) || (TextUtils.isEmpty(paramString)))
    {
      bool2 = false;
      return bool2;
    }
    if (TextUtils.isEmpty(null)) {}
    for (String str = b(paramContext);; str = null)
    {
      if (!TextUtils.isEmpty(str)) {}
      for (;;)
      {
        try
        {
          paramContext = a(str, paramString, paramContext);
          if (paramContext == null) {
            break label159;
          }
          localContext1 = paramContext;
          localContext2 = paramContext;
          int i = paramContext.getCount();
          if (i <= 0) {
            break label159;
          }
          bool1 = true;
          bool2 = bool1;
          if (paramContext == null) {
            break;
          }
          bool2 = bool1;
          if (paramContext.isClosed()) {
            break;
          }
          paramContext.close();
          return bool1;
        }
        catch (Exception paramContext)
        {
          return false;
        }
        finally
        {
          if ((localContext2 != null) && (!localContext2.isClosed())) {
            localContext2.close();
          }
        }
        return false;
        label159:
        boolean bool1 = false;
      }
    }
  }
  
  private boolean b(String paramString)
  {
    Context localContext = (Context)this.b.get();
    if (localContext != null) {
      return localContext.getPackageManager().checkPermission(paramString, localContext.getPackageName()) == 0;
    }
    return false;
  }
  
  private int c(String paramString)
  {
    Context localContext = (Context)this.b.get();
    if ((localContext == null) || (!g())) {
      return 0;
    }
    String str = b(localContext);
    if (!TextUtils.isEmpty(str)) {}
    try
    {
      paramString = a(str, paramString, localContext);
      if ((paramString != null) && (paramString.getCount() > 0)) {
        return paramString.getCount();
      }
      if ((paramString != null) && (!paramString.isClosed())) {
        paramString.close();
      }
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return 0;
  }
  
  private String c(Context paramContext)
  {
    Object localObject2 = new Intent("android.intent.action.MAIN");
    ((Intent)localObject2).addCategory("android.intent.category.HOME");
    Object localObject1 = paramContext.getPackageManager();
    localObject2 = ((PackageManager)localObject1).resolveActivity((Intent)localObject2, 0);
    if (localObject2 == null) {
      return null;
    }
    paramContext = a(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    localObject1 = ((PackageManager)localObject1).queryContentProviders(((ResolveInfo)localObject2).activityInfo.packageName, ((ResolveInfo)localObject2).activityInfo.applicationInfo.uid, 8);
    int i;
    if (localObject1 != null)
    {
      i = 0;
      while (i < ((List)localObject1).size())
      {
        localObject2 = (ProviderInfo)((List)localObject1).get(i);
        if ((((ProviderInfo)localObject2).readPermission != null) && (Pattern.matches(".*launcher.*READ_SETTINGS", ((ProviderInfo)localObject2).readPermission)))
        {
          paramContext = ((ProviderInfo)localObject2).authority;
          return "content://" + paramContext + "/favorites?notify=true";
        }
        i += 1;
      }
    }
    if (TextUtils.isEmpty(paramContext))
    {
      i = Build.VERSION.SDK_INT;
      if (i < 9) {
        paramContext = "com.android.launcher.settings";
      }
      for (;;)
      {
        return "content://" + paramContext + "/favorites?notify=true";
        if (i < 19) {
          paramContext = "com.android.launcher2.settings";
        } else {
          paramContext = "com.android.launcher3.settings";
        }
      }
    }
    return null;
  }
  
  private void d(Context paramContext)
  {
    try
    {
      b.b(paramContext);
      Object localObject1 = b.d(paramContext);
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (j)((Iterator)localObject1).next();
          com.mpcore.common.i.e.b("stManager", " 删除ICON--->" + ((j)localObject2).c);
          com.mpcore.common.e.a localA = b.c(paramContext, ((j)localObject2).a);
          if ((localA != null) && (localObject2 != null))
          {
            com.mpcore.common.i.e.b("stManager", "删除[handlerDeletedShortcut]" + localA.c());
            if ((localA != null) && (b("com.android.launcher.permission.UNINSTALL_SHORTCUT")))
            {
              b.a(paramContext, (j)localObject2);
              int i = c(localA.c());
              if (localA != null)
              {
                localObject2 = (Context)this.b.get();
                if (localObject2 != null) {
                  if (g())
                  {
                    if (b((Context)localObject2, localA.c())) {
                      a((Context)localObject2, localA, i);
                    }
                  }
                  else if (b("com.android.launcher.permission.UNINSTALL_SHORTCUT")) {
                    a((Context)localObject2, localA, i);
                  }
                }
              }
            }
          }
        }
      }
      com.mpcore.common.i.e.b("stManager", " 删除ICON--->无数据");
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  private boolean g()
  {
    if ((b("com.android.launcher.permission.READ_SETTINGS")) || (b("com.google.android.launcher.permission.READ_SETTINGS"))) {
      return true;
    }
    Context localContext = (Context)this.b.get();
    if (localContext != null) {
      return !TextUtils.isEmpty(b(localContext));
    }
    return false;
  }
  
  public final int a()
  {
    Context localContext = (Context)this.b.get();
    if (localContext == null) {
      return 0;
    }
    return b.c(localContext);
  }
  
  public final void a(final String paramString, final Activity paramActivity, final c paramC)
  {
    com.mpcore.common.i.e.b("stManager", "点击[shortcutClick]");
    final Context localContext;
    if ((Context)this.b.get() == null)
    {
      if (paramActivity != null) {
        this.b = new WeakReference(paramActivity.getApplication());
      }
    }
    else
    {
      localContext = (Context)this.b.get();
      if ((localContext != null) || (!TextUtils.isEmpty(paramString))) {
        break label74;
      }
      a(paramActivity);
    }
    label74:
    final com.mpcore.common.e.a localA;
    do
    {
      return;
      a(paramActivity);
      return;
      a(paramActivity);
      localA = b.c(localContext, paramString);
      com.mpcore.common.i.e.b("stManager", "点击[adEx][" + localA + "]");
    } while (localA == null);
    if (localA != null)
    {
      if (!TextUtils.isEmpty(localA.t())) {
        new f(localA.t(), 2, localA).a(null);
      }
      localObject = b.b(localContext, paramString);
      long l = System.currentTimeMillis();
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          j localJ = (j)((Iterator)localObject).next();
          localJ.i += 1;
          localJ.f = l;
          b.b(paramActivity, localJ);
        }
      }
      localObject = new j();
      ((j)localObject).i = 1;
      ((j)localObject).f = System.currentTimeMillis();
      ((j)localObject).a = localA.a();
      ((j)localObject).e = 0;
      ((j)localObject).c = localA.c();
      ((j)localObject).g = localA.b();
      ((j)localObject).b = b.e(localContext);
      ((j)localObject).d = System.currentTimeMillis();
      ((j)localObject).h = Long.valueOf(l);
      ((j)localObject).f = l;
      ((j)localObject).k = "";
      ((j)localObject).j = 0;
      b.b(paramActivity, (j)localObject);
    }
    if (b.a(localA.b())) {
      d(localContext);
    }
    final boolean bool = com.mpcore.a.d.a.a(d.a().b());
    Object localObject = b.d(localContext, localA.a());
    com.mpcore.common.i.e.b("stManager", "点击之前需要跳转一下，获取下载地址[shortcut==null? ->" + localObject + "]");
    if ((localObject != null) && (((j)localObject).j == 1) && (!TextUtils.isEmpty(((j)localObject).k)))
    {
      if (3 == localA.D()) {
        if (bool)
        {
          com.mpcore.common.i.e.b("stManager", "已经跳转过 是下载地址，且具备安装权限 开始下载[" + ((j)localObject).k + "]");
          d.a().a(localA, ((j)localObject).k, true, true);
        }
      }
      for (;;)
      {
        com.mpcore.common.i.e.b("stManager", "点击，[跳转过] ，进入新流程 [startShortcutFromClick]");
        b.a();
        f();
        return;
        com.mpcore.common.i.e.b("stManager", "已经跳转过 是下载地址，但是不具备安装权限，跳转到浏览器[" + ((j)localObject).k + "]");
        b.b(localContext, localA, ((j)localObject).k);
        continue;
        com.mpcore.common.i.e.b("stManager", "已经跳转过，直接获取下载[" + ((j)localObject).k + "] 打开GP 或者浏览器");
        b.a(localContext, localA, ((j)localObject).k);
      }
    }
    com.mpcore.common.i.e.b("stManager", "没有跳转过，点击跳转 获取下载[shortcutClick]");
    new a(localContext, b.e(localContext)).a(localA, new a.a()
    {
      public final void a()
      {
        com.mpcore.common.i.e.b("stManager", "下载之前需要跳转一下，获取下载地址[jumpTryDownloadApkFile] 失败");
      }
      
      public final void a(com.mpcore.common.e.a paramAnonymousA, String paramAnonymousString)
      {
        com.mpcore.common.e.a localA = paramAnonymousA;
        if (paramAnonymousA == null) {
          localA = localA;
        }
        com.mpcore.common.i.e.b("stManager", "跳转成功，获取下载[" + paramAnonymousString + "],开始下载");
        paramAnonymousA = b.b(localContext, paramString);
        if (paramAnonymousA != null)
        {
          paramAnonymousA = paramAnonymousA.iterator();
          while (paramAnonymousA.hasNext())
          {
            j localJ = (j)paramAnonymousA.next();
            localJ.j = 1;
            localJ.k = paramAnonymousString;
            b.b(paramActivity, localJ);
          }
        }
        if (3 == localA.D()) {
          if (bool)
          {
            com.mpcore.common.i.e.b("stManager", "是下载地址，且具备安装权限 开始下载[" + paramAnonymousString + "]");
            b.a(localA, paramAnonymousString, true, true);
          }
        }
        for (;;)
        {
          com.mpcore.common.i.e.b("stManager", "点击，[刚跳转] ，进入新流程 [startShortcutFromClick]");
          paramAnonymousA = e.this;
          b.a();
          paramAnonymousA.f();
          return;
          com.mpcore.common.i.e.b("stManager", "是下载地址，但是不具备安装权限，跳转到浏览器[" + paramAnonymousString + "]");
          b.b(localContext, localA, this.f.k);
          continue;
          com.mpcore.common.i.e.b("stManager", "不是下载地址 [" + this.f.k + "] 打开GP 或者浏览器");
          b.a(localContext, localA, this.f.k);
        }
      }
    });
  }
  
  public final boolean a(String paramString)
  {
    Context localContext = (Context)this.b.get();
    if (g()) {
      return b(localContext, paramString);
    }
    return false;
  }
  
  public final List<j> b()
  {
    Context localContext = (Context)this.b.get();
    if (localContext == null) {
      return null;
    }
    return b.d(localContext);
  }
  
  public final String c()
  {
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    Context localContext = (Context)this.b.get();
    if (localContext == null) {
      return "";
    }
    localObject = localContext.getPackageManager().resolveActivity((Intent)localObject, 0);
    if ((localObject == null) || (((ResolveInfo)localObject).activityInfo == null)) {
      return "";
    }
    if ("android".equals(((ResolveInfo)localObject).activityInfo.packageName)) {
      return "";
    }
    return ((ResolveInfo)localObject).activityInfo.packageName;
  }
  
  public final String d()
  {
    String str1;
    String str2;
    if (b("com.android.launcher.permission.INSTALL_SHORTCUT"))
    {
      str1 = "1";
      if (!b("com.android.launcher.permission.UNINSTALL_SHORTCUT")) {
        break label138;
      }
      str2 = "1";
      label28:
      if (!g()) {
        break label145;
      }
    }
    label138:
    label145:
    for (String str3 = "1";; str3 = "0")
    {
      com.mpcore.common.i.e.b("stManager", "[" + str1 + "," + str2 + "," + str3 + "]");
      return "[" + str1 + "," + str2 + "," + str3 + "]";
      str1 = "0";
      break;
      str2 = "0";
      break label28;
    }
  }
  
  public final void e()
  {
    com.mpcore.common.i.e.b("stManager", "startShortcutFromTick------tick--->");
    a(4);
  }
  
  public final void f()
  {
    com.mpcore.common.i.e.b("stManager", "startShortcutFromClick-----点击---->");
    a(5);
  }
}
