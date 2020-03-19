package com.fhcore.b;

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
import com.fhcore.common.a.d;
import com.fhcore.common.c.g;
import com.fhcore.common.e.j;
import com.fhcore.common.f.f;
import com.fhcore.common.i.i;
import com.funheroic.core.api.InnerBrowserActivity;
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
    if (a == null) {
      try
      {
        if (a == null) {
          a = new e(paramContext);
        }
      }
      finally {}
    }
    return a;
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
      while (localIterator.hasNext())
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)localIterator.next()).providers;
        if (arrayOfProviderInfo != null)
        {
          int j = arrayOfProviderInfo.length;
          int i = 0;
          paramContext = paramString;
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
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  private void a(final int paramInt, c paramC)
  {
    Context localContext = (Context)this.b.get();
    if (localContext == null) {
      return;
    }
    com.fhcore.common.g.a localA = com.fhcore.common.g.b.a(localContext).a(d.a().c());
    if (localA == null)
    {
      com.fhcore.common.i.e.b("stManager", "策略为空，结束");
      return;
    }
    int i = localA.bi();
    int j = com.fhcore.common.a.b.f;
    boolean bool2 = false;
    if (i == j) {
      i = 1;
    } else {
      i = 0;
    }
    long l = localA.bl();
    boolean bool1;
    if ((paramInt == 5) && (localA.bn() == com.fhcore.common.a.b.f)) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    StringBuilder localStringBuilder = new StringBuilder("策略获取--->是否无视间隔求新的sci(");
    localStringBuilder.append(bool1);
    localStringBuilder.append(")");
    com.fhcore.common.i.e.b("stManager", localStringBuilder.toString());
    if (i == 0)
    {
      if (paramC != null) {
        paramC.a("shortcut is off! place check the app setting!");
      }
      paramC = new StringBuilder("广告位关闭(");
      paramC.append(i ^ 0x1);
      paramC.append("),结束流程。");
      com.fhcore.common.i.e.b("stManager", paramC.toString());
      if (localA.bi() == 0)
      {
        com.fhcore.common.i.e.b("stManager", "结束流程--根据策略删除当前short。");
        d(localContext);
      }
      return;
    }
    if (System.currentTimeMillis() - b.a((Context)this.b.get()).longValue() > l) {
      bool2 = true;
    }
    paramC = new StringBuilder("策略获取--->是否间隔间隔(");
    paramC.append(bool2);
    paramC.append(")");
    com.fhcore.common.i.e.b("stManager", paramC.toString());
    if ((!bool1) && (!bool2))
    {
      paramC = new StringBuilder("不满足请求逻辑，终止流程(间隔：");
      paramC.append(bool1);
      paramC.append(" || canRequest:");
      paramC.append(bool2);
      paramC.append("),结束流程。");
      com.fhcore.common.i.e.b("stManager", paramC.toString());
      return;
    }
    paramC = new StringBuilder("开始请求广告(间隔：");
    paramC.append(bool1);
    paramC.append(" 或者 canRequest:");
    paramC.append(bool2);
    paramC.append(") 满足");
    com.fhcore.common.i.e.b("stManager", paramC.toString());
    com.fhcore.common.i.b.a.a().a(new Runnable()
    {
      public final void run()
      {
        e.a(e.this, paramInt);
      }
    }, 1000L);
  }
  
  private static void a(Activity paramActivity)
  {
    if (paramActivity != null) {
      paramActivity.finish();
    }
  }
  
  private void a(Context paramContext, com.fhcore.common.e.a paramA, int paramInt)
  {
    if (paramA == null) {
      return;
    }
    Intent localIntent1 = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    localIntent1.putExtra("android.intent.extra.shortcut.NAME", paramA.P());
    Intent localIntent2 = new Intent(paramContext, InnerBrowserActivity.class);
    localIntent2.setAction("android.intent.action.VIEW");
    localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
    paramContext.sendBroadcast(localIntent1);
    if (f())
    {
      if (c(paramA.P()) < paramInt) {
        paramInt = 1;
      } else {
        paramInt = 0;
      }
      if (paramInt != 0)
      {
        com.fhcore.common.i.e.b("stManager", "sci 删除成功");
        return;
      }
      com.fhcore.common.i.e.b("stManager", "sci 删除失败(未知原因)");
      return;
    }
    com.fhcore.common.i.e.b("stManager", "sci 删除失败(无权限)");
  }
  
  private String b(Context paramContext)
  {
    Object localObject2 = a(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject1 = c();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(".permission.READ_SETTINGS");
      localObject1 = a(paramContext, ((StringBuilder)localObject2).toString());
    }
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      int i = Build.VERSION.SDK_INT;
    }
    return c(paramContext);
  }
  
  /* Error */
  private boolean b(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 7
    //   3: iconst_0
    //   4: istore 8
    //   6: iconst_0
    //   7: istore 6
    //   9: aload_1
    //   10: ifnull +172 -> 182
    //   13: aload_2
    //   14: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   17: ifeq +5 -> 22
    //   20: iconst_0
    //   21: ireturn
    //   22: aconst_null
    //   23: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   26: ifeq +13 -> 39
    //   29: aload_0
    //   30: aload_1
    //   31: invokespecial 463	com/fhcore/b/e:b	(Landroid/content/Context;)Ljava/lang/String;
    //   34: astore 9
    //   36: goto +6 -> 42
    //   39: aconst_null
    //   40: astore 9
    //   42: iload 8
    //   44: istore 5
    //   46: aload 9
    //   48: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   51: ifne +128 -> 179
    //   54: aload 9
    //   56: aload_2
    //   57: aload_1
    //   58: invokestatic 465	com/fhcore/b/e:a	(Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)Landroid/database/Cursor;
    //   61: astore_1
    //   62: iload 6
    //   64: istore 4
    //   66: aload_1
    //   67: ifnull +28 -> 95
    //   70: aload_1
    //   71: invokeinterface 470 1 0
    //   76: istore_3
    //   77: iload 6
    //   79: istore 4
    //   81: iload_3
    //   82: ifle +13 -> 95
    //   85: iconst_1
    //   86: istore 4
    //   88: goto +7 -> 95
    //   91: astore_2
    //   92: goto +36 -> 128
    //   95: iload 4
    //   97: istore 5
    //   99: aload_1
    //   100: ifnull +79 -> 179
    //   103: iload 4
    //   105: istore 5
    //   107: aload_1
    //   108: invokeinterface 473 1 0
    //   113: ifne +66 -> 179
    //   116: aload_1
    //   117: invokeinterface 476 1 0
    //   122: iload 4
    //   124: ireturn
    //   125: astore_2
    //   126: aconst_null
    //   127: astore_1
    //   128: aload_1
    //   129: ifnull +18 -> 147
    //   132: aload_1
    //   133: invokeinterface 473 1 0
    //   138: ifne +9 -> 147
    //   141: aload_1
    //   142: invokeinterface 476 1 0
    //   147: aload_2
    //   148: athrow
    //   149: aconst_null
    //   150: astore_1
    //   151: iload 8
    //   153: istore 5
    //   155: aload_1
    //   156: ifnull +23 -> 179
    //   159: iload 8
    //   161: istore 5
    //   163: aload_1
    //   164: invokeinterface 473 1 0
    //   169: ifne +10 -> 179
    //   172: iload 7
    //   174: istore 4
    //   176: goto -60 -> 116
    //   179: iload 5
    //   181: ireturn
    //   182: iconst_0
    //   183: ireturn
    //   184: astore_1
    //   185: goto -36 -> 149
    //   188: astore_2
    //   189: goto -38 -> 151
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	192	0	this	e
    //   0	192	1	paramContext	Context
    //   0	192	2	paramString	String
    //   76	6	3	i	int
    //   64	111	4	bool1	boolean
    //   44	136	5	bool2	boolean
    //   7	71	6	bool3	boolean
    //   1	172	7	bool4	boolean
    //   4	156	8	bool5	boolean
    //   34	21	9	str	String
    // Exception table:
    //   from	to	target	type
    //   70	77	91	finally
    //   54	62	125	finally
    //   54	62	184	java/lang/Exception
    //   70	77	188	java/lang/Exception
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
    String str;
    if (localContext != null)
    {
      if (!f()) {
        return 0;
      }
      str = b(localContext);
      if (TextUtils.isEmpty(str)) {}
    }
    try
    {
      paramString = a(str, paramString, localContext);
      if ((paramString != null) && (paramString.getCount() > 0)) {
        return paramString.getCount();
      }
      if ((paramString != null) && (!paramString.isClosed())) {
        paramString.close();
      }
      return 0;
    }
    catch (Exception paramString) {}
    return 0;
    return 0;
  }
  
  private String c(Context paramContext)
  {
    Object localObject2 = new Intent("android.intent.action.MAIN");
    ((Intent)localObject2).addCategory("android.intent.category.HOME");
    Object localObject1 = paramContext.getPackageManager();
    int i = 0;
    localObject2 = ((PackageManager)localObject1).resolveActivity((Intent)localObject2, 0);
    if (localObject2 == null) {
      return null;
    }
    paramContext = a(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    localObject1 = ((PackageManager)localObject1).queryContentProviders(((ResolveInfo)localObject2).activityInfo.packageName, ((ResolveInfo)localObject2).activityInfo.applicationInfo.uid, 8);
    if (localObject1 != null) {
      while (i < ((List)localObject1).size())
      {
        localObject2 = (ProviderInfo)((List)localObject1).get(i);
        if ((((ProviderInfo)localObject2).readPermission != null) && (Pattern.matches(".*launcher.*READ_SETTINGS", ((ProviderInfo)localObject2).readPermission)))
        {
          paramContext = ((ProviderInfo)localObject2).authority;
          localObject1 = new StringBuilder("content://");
          ((StringBuilder)localObject1).append(paramContext);
          ((StringBuilder)localObject1).append("/favorites?notify=true");
          return ((StringBuilder)localObject1).toString();
        }
        i += 1;
      }
    }
    if (TextUtils.isEmpty(paramContext))
    {
      i = Build.VERSION.SDK_INT;
      if (i < 9) {
        paramContext = "com.android.launcher.settings";
      } else if (i < 19) {
        paramContext = "com.android.launcher2.settings";
      } else {
        paramContext = "com.android.launcher3.settings";
      }
      localObject1 = new StringBuilder("content://");
      ((StringBuilder)localObject1).append(paramContext);
      ((StringBuilder)localObject1).append("/favorites?notify=true");
      return ((StringBuilder)localObject1).toString();
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
          Object localObject3 = new StringBuilder(" 删除ICON--->");
          ((StringBuilder)localObject3).append(((j)localObject2).c);
          com.fhcore.common.i.e.b("stManager", ((StringBuilder)localObject3).toString());
          localObject3 = b.c(paramContext, ((j)localObject2).a);
          if ((localObject3 != null) && (localObject2 != null))
          {
            StringBuilder localStringBuilder = new StringBuilder("删除[handlerDeletedShortcut]");
            localStringBuilder.append(((com.fhcore.common.e.a)localObject3).P());
            com.fhcore.common.i.e.b("stManager", localStringBuilder.toString());
            if ((localObject3 != null) && (b("com.android.launcher.permission.UNINSTALL_SHORTCUT")))
            {
              b.a(paramContext, (j)localObject2);
              int i = c(((com.fhcore.common.e.a)localObject3).P());
              if (localObject3 != null)
              {
                localObject2 = (Context)this.b.get();
                if (localObject2 != null) {
                  if (f())
                  {
                    if (b((Context)localObject2, ((com.fhcore.common.e.a)localObject3).P())) {
                      a((Context)localObject2, (com.fhcore.common.e.a)localObject3, i);
                    }
                  }
                  else if (b("com.android.launcher.permission.UNINSTALL_SHORTCUT")) {
                    a((Context)localObject2, (com.fhcore.common.e.a)localObject3, i);
                  }
                }
              }
            }
          }
        }
      }
      com.fhcore.common.i.e.b("stManager", " 删除ICON--->无数据");
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  private boolean f()
  {
    if (!b("com.android.launcher.permission.READ_SETTINGS"))
    {
      if (b("com.google.android.launcher.permission.READ_SETTINGS")) {
        return true;
      }
      Context localContext = (Context)this.b.get();
      if (localContext != null) {
        return !TextUtils.isEmpty(b(localContext));
      }
      return false;
    }
    return true;
  }
  
  public final int a()
  {
    Context localContext = (Context)this.b.get();
    if (localContext == null) {
      return 0;
    }
    return b.c(localContext);
  }
  
  public final void a(c paramC)
  {
    com.fhcore.common.i.e.b("stManager", "startShortcutFromClick-----点击---->");
    a(5, paramC);
  }
  
  public final void a(final String paramString, final Activity paramActivity, final c paramC)
  {
    com.fhcore.common.i.e.b("stManager", "点击[shortcutClick]");
    if ((Context)this.b.get() == null) {
      if (paramActivity != null)
      {
        this.b = new WeakReference(paramActivity.getApplication());
      }
      else
      {
        a(paramActivity);
        return;
      }
    }
    final Context localContext = (Context)this.b.get();
    if ((localContext == null) && (TextUtils.isEmpty(paramString)))
    {
      a(paramActivity);
      return;
    }
    a(paramActivity);
    final com.fhcore.common.e.a localA = b.c(localContext, paramString);
    Object localObject1 = new StringBuilder("点击[adEx][");
    ((StringBuilder)localObject1).append(localA);
    ((StringBuilder)localObject1).append("]");
    com.fhcore.common.i.e.b("stManager", ((StringBuilder)localObject1).toString());
    if (localA == null) {
      return;
    }
    if (localA != null)
    {
      if (!TextUtils.isEmpty(localA.t())) {
        new f(localA.t(), 2, localA).a(null);
      }
      localObject1 = b.b(localContext, paramString);
      long l = System.currentTimeMillis();
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (j)((Iterator)localObject1).next();
          ((j)localObject2).i += 1;
          ((j)localObject2).f = l;
          b.b(paramActivity, (j)localObject2);
        }
      }
      localObject1 = new j();
      ((j)localObject1).i = 1;
      ((j)localObject1).f = System.currentTimeMillis();
      ((j)localObject1).a = localA.N();
      ((j)localObject1).e = 0;
      ((j)localObject1).c = localA.P();
      ((j)localObject1).g = localA.O();
      ((j)localObject1).b = b.e(localContext);
      ((j)localObject1).d = System.currentTimeMillis();
      ((j)localObject1).h = Long.valueOf(l);
      ((j)localObject1).f = l;
      ((j)localObject1).k = "";
      ((j)localObject1).j = 0;
      b.b(paramActivity, (j)localObject1);
    }
    if (b.a(localA.O())) {
      d(localContext);
    }
    final boolean bool = com.funheroic.core.a.d.a.a(d.a().b());
    localObject1 = b.d(localContext, localA.N());
    Object localObject2 = new StringBuilder("点击之前需要跳转一下，获取下载地址[shortcut==null? ->");
    ((StringBuilder)localObject2).append(localObject1);
    ((StringBuilder)localObject2).append("]");
    com.fhcore.common.i.e.b("stManager", ((StringBuilder)localObject2).toString());
    if ((localObject1 != null) && (((j)localObject1).j == 1) && (!TextUtils.isEmpty(((j)localObject1).k)))
    {
      if (3 == localA.D())
      {
        if (bool)
        {
          paramString = new StringBuilder("已经跳转过 是下载地址，且具备安装权限 开始下载[");
          paramString.append(((j)localObject1).k);
          paramString.append("]");
          com.fhcore.common.i.e.b("stManager", paramString.toString());
          d.a().a(localA, ((j)localObject1).k, true, true);
        }
        else
        {
          paramString = new StringBuilder("已经跳转过 是下载地址，但是不具备安装权限，跳转到浏览器[");
          paramString.append(((j)localObject1).k);
          paramString.append("]");
          com.fhcore.common.i.e.b("stManager", paramString.toString());
          b.b(localContext, localA, ((j)localObject1).k);
        }
      }
      else
      {
        paramString = new StringBuilder("已经跳转过，直接获取下载[");
        paramString.append(((j)localObject1).k);
        paramString.append("] 打开GP 或者浏览器");
        com.fhcore.common.i.e.b("stManager", paramString.toString());
        b.a(localContext, localA, ((j)localObject1).k);
      }
      com.fhcore.common.i.e.b("stManager", "点击，[跳转过] ，进入新流程 [startShortcutFromClick]");
      b.a();
      a(paramC);
      return;
    }
    com.fhcore.common.i.e.b("stManager", "没有跳转过，点击跳转 获取下载[shortcutClick]");
    new a(localContext, b.e(localContext)).a(localA, new a.a()
    {
      public final void a()
      {
        com.fhcore.common.i.e.b("stManager", "下载之前需要跳转一下，获取下载地址[jumpTryDownloadApkFile] 失败");
      }
      
      public final void a(com.fhcore.common.e.a paramAnonymousA, String paramAnonymousString)
      {
        com.fhcore.common.e.a localA = paramAnonymousA;
        if (paramAnonymousA == null) {
          localA = localA;
        }
        paramAnonymousA = new StringBuilder("跳转成功，获取下载[");
        paramAnonymousA.append(paramAnonymousString);
        paramAnonymousA.append("],开始下载");
        com.fhcore.common.i.e.b("stManager", paramAnonymousA.toString());
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
        if (3 == localA.D())
        {
          if (bool)
          {
            paramAnonymousA = new StringBuilder("是下载地址，且具备安装权限 开始下载[");
            paramAnonymousA.append(paramAnonymousString);
            paramAnonymousA.append("]");
            com.fhcore.common.i.e.b("stManager", paramAnonymousA.toString());
            b.a(localA, paramAnonymousString, true, true);
          }
          else
          {
            paramAnonymousA = new StringBuilder("是下载地址，但是不具备安装权限，跳转到浏览器[");
            paramAnonymousA.append(paramAnonymousString);
            paramAnonymousA.append("]");
            com.fhcore.common.i.e.b("stManager", paramAnonymousA.toString());
            b.b(localContext, localA, this.f.k);
          }
        }
        else
        {
          paramAnonymousA = new StringBuilder("不是下载地址 [");
          paramAnonymousA.append(this.f.k);
          paramAnonymousA.append("] 打开GP 或者浏览器");
          com.fhcore.common.i.e.b("stManager", paramAnonymousA.toString());
          b.a(localContext, localA, this.f.k);
        }
        com.fhcore.common.i.e.b("stManager", "点击，[刚跳转] ，进入新流程 [startShortcutFromClick]");
        paramAnonymousA = e.this;
        b.a();
        paramAnonymousA.a(paramC);
      }
    });
  }
  
  public final boolean a(String paramString)
  {
    Context localContext = (Context)this.b.get();
    if (f()) {
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
    if ((localObject != null) && (((ResolveInfo)localObject).activityInfo != null))
    {
      if ("android".equals(((ResolveInfo)localObject).activityInfo.packageName)) {
        return "";
      }
      return ((ResolveInfo)localObject).activityInfo.packageName;
    }
    return "";
  }
  
  public final String d()
  {
    String str1;
    if (b("com.android.launcher.permission.INSTALL_SHORTCUT")) {
      str1 = "1";
    } else {
      str1 = "0";
    }
    String str2;
    if (b("com.android.launcher.permission.UNINSTALL_SHORTCUT")) {
      str2 = "1";
    } else {
      str2 = "0";
    }
    String str3;
    if (f()) {
      str3 = "1";
    } else {
      str3 = "0";
    }
    StringBuilder localStringBuilder = new StringBuilder("[");
    localStringBuilder.append(str1);
    localStringBuilder.append(",");
    localStringBuilder.append(str2);
    localStringBuilder.append(",");
    localStringBuilder.append(str3);
    localStringBuilder.append("]");
    com.fhcore.common.i.e.b("stManager", localStringBuilder.toString());
    localStringBuilder = new StringBuilder("[");
    localStringBuilder.append(str1);
    localStringBuilder.append(",");
    localStringBuilder.append(str2);
    localStringBuilder.append(",");
    localStringBuilder.append(str3);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public final void e()
  {
    com.fhcore.common.i.e.b("stManager", "startShortcutFromTick------tick--->");
    a(4, null);
  }
}
