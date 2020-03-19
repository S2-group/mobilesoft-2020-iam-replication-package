package com.mpcore.d;

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
import com.mpcore.common.a.d;
import com.mpcore.common.c.g;
import com.mpcore.common.e.j;
import com.mpcore.common.f.f;
import com.mpcore.common.i.i;
import com.power.InnerBrowserActivity;
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
  
  private void a(final int paramInt)
  {
    Object localObject = (Context)this.b.get();
    if (localObject == null) {
      return;
    }
    com.mpcore.common.g.a localA = com.mpcore.common.g.b.a((Context)localObject).a(d.a().c());
    if (localA == null)
    {
      com.mpcore.common.i.e.b("stManager", "策略为空，结束");
      return;
    }
    int i = localA.bi();
    int j = com.mpcore.common.a.b.e;
    boolean bool2 = false;
    if (i == j) {
      i = 1;
    } else {
      i = 0;
    }
    long l = localA.bl();
    boolean bool1;
    if ((paramInt == 5) && (localA.bn() == com.mpcore.common.a.b.e)) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    StringBuilder localStringBuilder = new StringBuilder("策略获取--->是否无视间隔求新的sci(");
    localStringBuilder.append(bool1);
    localStringBuilder.append(")");
    com.mpcore.common.i.e.b("stManager", localStringBuilder.toString());
    if (i == 0)
    {
      localStringBuilder = new StringBuilder("广告位关闭(");
      localStringBuilder.append(i ^ 0x1);
      localStringBuilder.append("),结束流程。");
      com.mpcore.common.i.e.b("stManager", localStringBuilder.toString());
      if (localA.bi() == 0)
      {
        com.mpcore.common.i.e.b("stManager", "结束流程--根据策略删除当前short。");
        d((Context)localObject);
      }
      return;
    }
    if (System.currentTimeMillis() - b.a((Context)this.b.get()).longValue() > l) {
      bool2 = true;
    }
    localObject = new StringBuilder("策略获取--->是否间隔间隔(");
    ((StringBuilder)localObject).append(bool2);
    ((StringBuilder)localObject).append(")");
    com.mpcore.common.i.e.b("stManager", ((StringBuilder)localObject).toString());
    if ((!bool1) && (!bool2))
    {
      localObject = new StringBuilder("不满足请求逻辑，终止流程(间隔：");
      ((StringBuilder)localObject).append(bool1);
      ((StringBuilder)localObject).append(" || canRequest:");
      ((StringBuilder)localObject).append(bool2);
      ((StringBuilder)localObject).append("),结束流程。");
      com.mpcore.common.i.e.b("stManager", ((StringBuilder)localObject).toString());
      return;
    }
    localObject = new StringBuilder("开始请求广告(间隔：");
    ((StringBuilder)localObject).append(bool1);
    ((StringBuilder)localObject).append(" 或者 canRequest:");
    ((StringBuilder)localObject).append(bool2);
    ((StringBuilder)localObject).append(") 满足");
    com.mpcore.common.i.e.b("stManager", ((StringBuilder)localObject).toString());
    com.mpcore.common.i.b.a.a().a(new Runnable()
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
      if (c(paramA.c()) < paramInt) {
        paramInt = 1;
      } else {
        paramInt = 0;
      }
      if (paramInt != 0) {
        paramContext = "sci 删除成功";
      }
    }
    for (;;)
    {
      com.mpcore.common.i.e.b("stManager", paramContext);
      return;
      paramContext = "sci 删除失败(未知原因)";
      continue;
      paramContext = "sci 删除失败(无权限)";
    }
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
    //   9: iload 8
    //   11: istore 5
    //   13: aload_1
    //   14: ifnull +169 -> 183
    //   17: aload_2
    //   18: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   21: ifeq +5 -> 26
    //   24: iconst_0
    //   25: ireturn
    //   26: aconst_null
    //   27: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   30: ifeq +13 -> 43
    //   33: aload_0
    //   34: aload_1
    //   35: invokespecial 450	com/mpcore/d/e:b	(Landroid/content/Context;)Ljava/lang/String;
    //   38: astore 9
    //   40: goto +6 -> 46
    //   43: aconst_null
    //   44: astore 9
    //   46: iload 8
    //   48: istore 5
    //   50: aload 9
    //   52: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   55: ifne +128 -> 183
    //   58: aload 9
    //   60: aload_2
    //   61: aload_1
    //   62: invokestatic 452	com/mpcore/d/e:a	(Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)Landroid/database/Cursor;
    //   65: astore_1
    //   66: iload 6
    //   68: istore 4
    //   70: aload_1
    //   71: ifnull +28 -> 99
    //   74: aload_1
    //   75: invokeinterface 457 1 0
    //   80: istore_3
    //   81: iload 6
    //   83: istore 4
    //   85: iload_3
    //   86: ifle +13 -> 99
    //   89: iconst_1
    //   90: istore 4
    //   92: goto +7 -> 99
    //   95: astore_2
    //   96: goto +36 -> 132
    //   99: iload 4
    //   101: istore 5
    //   103: aload_1
    //   104: ifnull +79 -> 183
    //   107: iload 4
    //   109: istore 5
    //   111: aload_1
    //   112: invokeinterface 460 1 0
    //   117: ifne +66 -> 183
    //   120: aload_1
    //   121: invokeinterface 463 1 0
    //   126: iload 4
    //   128: ireturn
    //   129: astore_2
    //   130: aconst_null
    //   131: astore_1
    //   132: aload_1
    //   133: ifnull +18 -> 151
    //   136: aload_1
    //   137: invokeinterface 460 1 0
    //   142: ifne +9 -> 151
    //   145: aload_1
    //   146: invokeinterface 463 1 0
    //   151: aload_2
    //   152: athrow
    //   153: aconst_null
    //   154: astore_1
    //   155: iload 8
    //   157: istore 5
    //   159: aload_1
    //   160: ifnull +23 -> 183
    //   163: iload 8
    //   165: istore 5
    //   167: aload_1
    //   168: invokeinterface 460 1 0
    //   173: ifne +10 -> 183
    //   176: iload 7
    //   178: istore 4
    //   180: goto -60 -> 120
    //   183: iload 5
    //   185: ireturn
    //   186: astore_1
    //   187: goto -34 -> 153
    //   190: astore_2
    //   191: goto -36 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	194	0	this	e
    //   0	194	1	paramContext	Context
    //   0	194	2	paramString	String
    //   80	6	3	i	int
    //   68	111	4	bool1	boolean
    //   11	173	5	bool2	boolean
    //   7	75	6	bool3	boolean
    //   1	176	7	bool4	boolean
    //   4	160	8	bool5	boolean
    //   38	21	9	str	String
    // Exception table:
    //   from	to	target	type
    //   74	81	95	finally
    //   58	66	129	finally
    //   58	66	186	java/lang/Exception
    //   74	81	190	java/lang/Exception
  }
  
  private boolean b(String paramString)
  {
    Context localContext = (Context)this.b.get();
    return (localContext != null) && (localContext.getPackageManager().checkPermission(paramString, localContext.getPackageName()) == 0);
  }
  
  private int c(String paramString)
  {
    Context localContext = (Context)this.b.get();
    String str;
    if (localContext != null)
    {
      if (!g()) {
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
      if (i < ((List)localObject1).size())
      {
        localObject2 = (ProviderInfo)((List)localObject1).get(i);
        if ((((ProviderInfo)localObject2).readPermission != null) && (Pattern.matches(".*launcher.*READ_SETTINGS", ((ProviderInfo)localObject2).readPermission))) {
          paramContext = ((ProviderInfo)localObject2).authority;
        }
      }
    }
    for (localObject1 = new StringBuilder("content://");; localObject1 = new StringBuilder("content://"))
    {
      ((StringBuilder)localObject1).append(paramContext);
      ((StringBuilder)localObject1).append("/favorites?notify=true");
      return ((StringBuilder)localObject1).toString();
      i += 1;
      break;
      if (!TextUtils.isEmpty(paramContext)) {
        break label224;
      }
      i = Build.VERSION.SDK_INT;
      if (i < 9) {
        paramContext = "com.android.launcher.settings";
      } else if (i < 19) {
        paramContext = "com.android.launcher2.settings";
      } else {
        paramContext = "com.android.launcher3.settings";
      }
    }
    label224:
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
        Object localObject2;
        Object localObject3;
        int i;
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  if (!((Iterator)localObject1).hasNext()) {
                    break;
                  }
                  localObject2 = (j)((Iterator)localObject1).next();
                  localObject3 = new StringBuilder(" 删除ICON--->");
                  ((StringBuilder)localObject3).append(((j)localObject2).c);
                  com.mpcore.common.i.e.b("stManager", ((StringBuilder)localObject3).toString());
                  localObject3 = b.c(paramContext, ((j)localObject2).a);
                } while ((localObject3 == null) || (localObject2 == null));
                StringBuilder localStringBuilder = new StringBuilder("删除[handlerDeletedShortcut]");
                localStringBuilder.append(((com.mpcore.common.e.a)localObject3).c());
                com.mpcore.common.i.e.b("stManager", localStringBuilder.toString());
              } while ((localObject3 == null) || (!b("com.android.launcher.permission.UNINSTALL_SHORTCUT")));
              b.a(paramContext, (j)localObject2);
              i = c(((com.mpcore.common.e.a)localObject3).c());
            } while (localObject3 == null);
            localObject2 = (Context)this.b.get();
          } while (localObject2 == null);
          if (!g()) {
            break;
          }
        } while (!b((Context)localObject2, ((com.mpcore.common.e.a)localObject3).c()));
        for (;;)
        {
          a((Context)localObject2, (com.mpcore.common.e.a)localObject3, i);
          break;
          if (!b("com.android.launcher.permission.UNINSTALL_SHORTCUT")) {
            break;
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
    if (!b("com.android.launcher.permission.READ_SETTINGS"))
    {
      if (b("com.google.android.launcher.permission.READ_SETTINGS")) {
        return true;
      }
      Context localContext = (Context)this.b.get();
      return (localContext != null) && (!TextUtils.isEmpty(b(localContext)));
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
  
  public final void a(final String paramString, final Activity paramActivity, final c paramC)
  {
    com.mpcore.common.i.e.b("stManager", "点击[shortcutClick]");
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
    final com.mpcore.common.e.a localA = b.c(localContext, paramString);
    Object localObject1 = new StringBuilder("点击[adEx][");
    ((StringBuilder)localObject1).append(localA);
    ((StringBuilder)localObject1).append("]");
    com.mpcore.common.i.e.b("stManager", ((StringBuilder)localObject1).toString());
    if (localA == null) {
      return;
    }
    if (localA != null)
    {
      if (!TextUtils.isEmpty(localA.w())) {
        new f(localA.w(), 2, localA).a(null);
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
      ((j)localObject1).a = localA.a();
      ((j)localObject1).e = 0;
      ((j)localObject1).c = localA.c();
      ((j)localObject1).g = localA.b();
      ((j)localObject1).b = b.e(localContext);
      ((j)localObject1).d = System.currentTimeMillis();
      ((j)localObject1).h = Long.valueOf(l);
      ((j)localObject1).f = l;
      ((j)localObject1).k = "";
      ((j)localObject1).j = 0;
      b.b(paramActivity, (j)localObject1);
    }
    if (b.a(localA.b())) {
      d(localContext);
    }
    final boolean bool = com.mpcore.a.d.a.a(d.a().b());
    localObject1 = b.d(localContext, localA.a());
    Object localObject2 = new StringBuilder("点击之前需要跳转一下，获取下载地址[shortcut==null? ->");
    ((StringBuilder)localObject2).append(localObject1);
    ((StringBuilder)localObject2).append("]");
    com.mpcore.common.i.e.b("stManager", ((StringBuilder)localObject2).toString());
    if ((localObject1 != null) && (((j)localObject1).j == 1) && (!TextUtils.isEmpty(((j)localObject1).k)))
    {
      if (3 == localA.G())
      {
        if (bool)
        {
          paramString = new StringBuilder("已经跳转过 是下载地址，且具备安装权限 开始下载[");
          paramString.append(((j)localObject1).k);
          paramString.append("]");
          com.mpcore.common.i.e.b("stManager", paramString.toString());
          d.a().a(localA, ((j)localObject1).k, true, true);
        }
        else
        {
          paramString = new StringBuilder("已经跳转过 是下载地址，但是不具备安装权限，跳转到浏览器[");
          paramString.append(((j)localObject1).k);
          paramString.append("]");
          com.mpcore.common.i.e.b("stManager", paramString.toString());
          b.b(localContext, localA, ((j)localObject1).k);
        }
      }
      else
      {
        paramString = new StringBuilder("已经跳转过，直接获取下载[");
        paramString.append(((j)localObject1).k);
        paramString.append("] 打开GP 或者浏览器");
        com.mpcore.common.i.e.b("stManager", paramString.toString());
        b.a(localContext, localA, ((j)localObject1).k);
      }
      com.mpcore.common.i.e.b("stManager", "点击，[跳转过] ，进入新流程 [startShortcutFromClick]");
      b.a();
      f();
      return;
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
        paramAnonymousA = new StringBuilder("跳转成功，获取下载[");
        paramAnonymousA.append(paramAnonymousString);
        paramAnonymousA.append("],开始下载");
        com.mpcore.common.i.e.b("stManager", paramAnonymousA.toString());
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
        if (3 == localA.G())
        {
          if (bool)
          {
            paramAnonymousA = new StringBuilder("是下载地址，且具备安装权限 开始下载[");
            paramAnonymousA.append(paramAnonymousString);
            paramAnonymousA.append("]");
            com.mpcore.common.i.e.b("stManager", paramAnonymousA.toString());
            b.a(localA, paramAnonymousString, true, true);
          }
          else
          {
            paramAnonymousA = new StringBuilder("是下载地址，但是不具备安装权限，跳转到浏览器[");
            paramAnonymousA.append(paramAnonymousString);
            paramAnonymousA.append("]");
            com.mpcore.common.i.e.b("stManager", paramAnonymousA.toString());
            b.b(localContext, localA, this.f.k);
          }
        }
        else
        {
          paramAnonymousA = new StringBuilder("不是下载地址 [");
          paramAnonymousA.append(this.f.k);
          paramAnonymousA.append("] 打开GP 或者浏览器");
          com.mpcore.common.i.e.b("stManager", paramAnonymousA.toString());
          b.a(localContext, localA, this.f.k);
        }
        com.mpcore.common.i.e.b("stManager", "点击，[刚跳转] ，进入新流程 [startShortcutFromClick]");
        paramAnonymousA = e.this;
        b.a();
        paramAnonymousA.f();
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
    if (g()) {
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
    com.mpcore.common.i.e.b("stManager", localStringBuilder.toString());
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
    com.mpcore.common.i.e.b("stManager", "startShortcutFromTick------tick--->");
    a(4);
  }
  
  public final void f()
  {
    com.mpcore.common.i.e.b("stManager", "startShortcutFromClick-----点击---->");
    a(5);
  }
}
