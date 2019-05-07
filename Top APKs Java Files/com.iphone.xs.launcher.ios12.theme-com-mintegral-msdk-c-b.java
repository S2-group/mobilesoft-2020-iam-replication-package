package com.mintegral.msdk.c;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import com.mintegral.msdk.activity.MTGCommonActivity;
import com.mintegral.msdk.base.b.f;
import com.mintegral.msdk.base.b.i;
import com.mintegral.msdk.base.entity.CampaignEx;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

public class b
{
  private static volatile b b;
  private WeakReference<Context> a;
  
  private b(Context paramContext)
  {
    this.a = new WeakReference(paramContext);
  }
  
  private static Cursor a(String paramString1, String paramString2, Context paramContext)
  {
    return paramContext.getContentResolver().query(Uri.parse(paramString1), new String[] { "title" }, "title=?", new String[] { paramString2 }, null);
  }
  
  public static b a(Context paramContext)
  {
    if (b == null) {}
    try
    {
      if (b == null) {
        b = new b(paramContext);
      }
      return b;
    }
    finally {}
  }
  
  private void a(Context paramContext, com.mintegral.msdk.b.a paramA)
  {
    if (!TextUtils.isEmpty(paramA.f()))
    {
      paramContext = f.a(i.a(paramContext)).g(paramA.f());
      if ((paramContext != null) && (paramContext.size() > 0) && (b("com.android.launcher.permission.UNINSTALL_SHORTCUT")))
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          paramA = (CampaignEx)paramContext.next();
          int i = c(paramA.getAppName());
          Context localContext = (Context)this.a.get();
          if (localContext != null) {
            if ((b("com.android.launcher.permission.READ_SETTINGS")) || (b("com.google.android.launcher.permission.READ_SETTINGS")))
            {
              if (a(localContext, paramA.getAppName())) {
                a(localContext, paramA, i);
              }
            }
            else if (b("com.android.launcher.permission.UNINSTALL_SHORTCUT")) {
              a(localContext, paramA, i);
            }
          }
        }
      }
    }
  }
  
  private void a(Context paramContext, CampaignEx paramCampaignEx, int paramInt)
  {
    Object localObject1 = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    ((Intent)localObject1).putExtra("android.intent.extra.shortcut.NAME", paramCampaignEx.getAppName());
    Object localObject2 = new Intent(paramContext, MTGCommonActivity.class);
    ((Intent)localObject2).setAction("android.intent.action.VIEW");
    ((Intent)localObject1).putExtra("android.intent.extra.shortcut.INTENT", (Parcelable)localObject2);
    paramContext.sendBroadcast((Intent)localObject1);
    localObject2 = f.a(i.a(paramContext));
    com.mintegral.msdk.b.b.a();
    localObject1 = com.mintegral.msdk.b.b.b(com.mintegral.msdk.base.controller.a.d().k());
    paramContext = (Context)localObject1;
    if (localObject1 == null)
    {
      com.mintegral.msdk.b.b.a();
      paramContext = com.mintegral.msdk.b.b.b();
    }
    paramContext = paramContext.f();
    if ((b("com.android.launcher.permission.READ_SETTINGS")) || (b("com.google.android.launcher.permission.READ_SETTINGS"))) {
      if (c(paramCampaignEx.getAppName()) < paramInt)
      {
        paramInt = 1;
        if (paramInt == 0) {
          break label195;
        }
        a(paramCampaignEx, 2, 1);
        paramCampaignEx.setIsDeleted(1);
        localObject1 = new ContentValues();
        ((ContentValues)localObject1).put("is_deleted", Integer.valueOf(1));
        ((f)localObject2).a(paramCampaignEx.getId(), (ContentValues)localObject1);
      }
    }
    for (;;)
    {
      ((f)localObject2).c(paramContext);
      return;
      paramInt = 0;
      break;
      label195:
      a(paramCampaignEx, 2, 0);
      continue;
      a(paramCampaignEx, 2, -1);
      localObject1 = new ContentValues();
      ((ContentValues)localObject1).put("is_deleted", Integer.valueOf(1));
      ((f)localObject2).a(paramCampaignEx.getId(), (ContentValues)localObject1);
    }
  }
  
  private void a(CampaignEx paramCampaignEx, int paramInt1, int paramInt2)
  {
    if ((this.a.get() != null) && (paramCampaignEx.getIsDeleted() != 1)) {
      com.mintegral.msdk.base.common.d.b.a((Context)this.a.get(), paramCampaignEx, paramInt1, paramInt2);
    }
  }
  
  private boolean a(Context paramContext, String paramString)
  {
    String str = null;
    boolean bool2;
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      bool2 = false;
    }
    for (;;)
    {
      return bool2;
      if (TextUtils.isEmpty(null)) {
        str = b(paramContext);
      }
      if (TextUtils.isEmpty(str)) {
        break label119;
      }
      try
      {
        paramContext = a(str, paramString, paramContext);
        if (paramContext == null) {
          break label113;
        }
        int i = paramContext.getCount();
        if (i <= 0) {
          break label113;
        }
        bool1 = true;
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          boolean bool1 = false;
          continue;
          bool1 = false;
        }
      }
      bool2 = bool1;
      if (paramContext != null)
      {
        bool2 = bool1;
        try
        {
          if (!paramContext.isClosed())
          {
            paramContext.close();
            return bool1;
          }
        }
        catch (Exception paramContext) {}
      }
    }
    paramContext.printStackTrace();
    return bool1;
    label113:
    label119:
    return false;
  }
  
  private String b(Context paramContext)
  {
    String str2 = b(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!str2.trim().equals("")) {}
    }
    else
    {
      str1 = b();
      str1 = b(paramContext, str1 + ".permission.READ_SETTINGS");
    }
    paramContext = str1;
    int i;
    if (TextUtils.isEmpty(str1))
    {
      i = Build.VERSION.SDK_INT;
      if (i >= 8) {
        break label109;
      }
      paramContext = "com.android.launcher.settings";
    }
    for (;;)
    {
      return "content://" + paramContext + "/favorites?notify=true";
      label109:
      if (i < 19) {
        paramContext = "com.android.launcher2.settings";
      } else {
        paramContext = "com.android.launcher3.settings";
      }
    }
  }
  
  private String b(Context paramContext, String paramString)
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
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void b(Activity paramActivity)
  {
    if (paramActivity != null) {
      paramActivity.finish();
    }
  }
  
  private static boolean b(com.mintegral.msdk.b.a paramA)
  {
    int i = paramA.b();
    long l2 = System.currentTimeMillis();
    long l1 = 0L;
    if (!com.mintegral.msdk.base.a.a.a.a().a("mtg_shortcuts_ctime").equals("")) {
      l1 = Long.parseLong(com.mintegral.msdk.base.a.a.a.a().a("mtg_shortcuts_ctime"));
    }
    return (l2 - l1) / 1000L > i;
  }
  
  private boolean b(String paramString)
  {
    Context localContext = (Context)this.a.get();
    if (localContext != null) {
      return localContext.getPackageManager().checkPermission(paramString, localContext.getPackageName()) == 0;
    }
    return false;
  }
  
  private int c(String paramString)
  {
    Context localContext = (Context)this.a.get();
    if ((localContext == null) || ((!b("com.android.launcher.permission.READ_SETTINGS")) && (!b("com.google.android.launcher.permission.READ_SETTINGS")))) {
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
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    return 0;
  }
  
  public final int a()
  {
    Object localObject = (Context)this.a.get();
    if (localObject == null) {
      return 0;
    }
    f localF = f.a(i.a((Context)localObject));
    com.mintegral.msdk.b.b.a();
    com.mintegral.msdk.b.a localA = com.mintegral.msdk.b.b.b(com.mintegral.msdk.base.controller.a.d().k());
    localObject = localA;
    if (localA == null)
    {
      com.mintegral.msdk.b.b.a();
      localObject = com.mintegral.msdk.b.b.b();
    }
    localObject = localF.g(((com.mintegral.msdk.b.a)localObject).f());
    if ((localObject != null) && (((List)localObject).size() > 0)) {
      return ((List)localObject).size();
    }
    return 0;
  }
  
  public final void a(com.mintegral.msdk.b.a paramA)
  {
    for (;;)
    {
      try
      {
        Object localObject = (Context)this.a.get();
        if (localObject == null) {
          return;
        }
        localObject = f.a(i.a((Context)localObject));
        paramA = ((f)localObject).g(paramA.f());
        if ((paramA == null) || (paramA.size() <= 0)) {
          break;
        }
        paramA = paramA.iterator();
        if (!paramA.hasNext()) {
          break;
        }
        CampaignEx localCampaignEx = (CampaignEx)paramA.next();
        if ((localCampaignEx.getIsAddSuccesful() == 0) && (localCampaignEx.getIsDeleted() == 0)) {
          if (c(localCampaignEx.getAppName()) != 0)
          {
            a(localCampaignEx, 1, 1);
            com.mintegral.msdk.base.a.a.a.a().a("mtg_shortcuts_ctime", String.valueOf(System.currentTimeMillis()));
            localCampaignEx.setIsAddSuccesful(-1);
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("is_add_sucesful", Integer.valueOf(localCampaignEx.getIsAddSuccesful()));
            ((f)localObject).a(localCampaignEx.getId(), localContentValues);
          }
          else
          {
            a(localCampaignEx, 1, 0);
          }
        }
      }
      catch (Exception paramA)
      {
        paramA.printStackTrace();
        return;
      }
    }
  }
  
  public final void a(CampaignEx paramCampaignEx, Activity paramActivity)
  {
    Context localContext = (Context)this.a.get();
    if (localContext == null) {
      return;
    }
    if (localContext != null)
    {
      f localF = f.a(i.a(localContext));
      com.mintegral.msdk.b.b.a();
      Object localObject2 = com.mintegral.msdk.b.b.b(com.mintegral.msdk.base.controller.a.d().k());
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        com.mintegral.msdk.b.b.a();
        localObject1 = com.mintegral.msdk.b.b.b();
      }
      localObject1 = localF.g(((com.mintegral.msdk.b.a)localObject1).f());
      if ((localObject1 != null) && (((List)localObject1).size() > 0))
      {
        localObject1 = ((List)localObject1).iterator();
        do
        {
          if (!((Iterator)localObject1).hasNext()) {
            break;
          }
          localObject2 = (CampaignEx)((Iterator)localObject1).next();
        } while ((!((CampaignEx)localObject2).getId().equals(paramCampaignEx.getId())) || (!((CampaignEx)localObject2).getAppName().equals(paramCampaignEx.getAppName())));
      }
    }
    for (int i = 1; i != 0; i = 0)
    {
      b(paramActivity);
      return;
    }
    if (!TextUtils.isEmpty(paramCampaignEx.getIconUrl()))
    {
      com.mintegral.msdk.base.common.c.b.a(localContext).a(paramCampaignEx.getIconUrl(), new b.1(this, paramActivity, localContext, paramCampaignEx));
      return;
    }
    b(paramActivity);
  }
  
  public final void a(String paramString)
  {
    Context localContext = (Context)this.a.get();
    if (localContext == null) {}
    do
    {
      return;
      com.mintegral.msdk.b.a localA = null;
      if (paramString != null)
      {
        com.mintegral.msdk.b.b.a();
        localA = com.mintegral.msdk.b.b.b(paramString);
      }
      paramString = localA;
      if (localA == null)
      {
        com.mintegral.msdk.b.b.a();
        paramString = com.mintegral.msdk.b.b.b();
      }
      a(paramString);
    } while (!b(paramString));
    new Handler().postDelayed(new b.2(this, localContext), 30000L);
  }
  
  public final String b()
  {
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    Context localContext = (Context)this.a.get();
    if (localContext == null) {
      return "";
    }
    localObject = localContext.getPackageManager().resolveActivity((Intent)localObject, 0);
    if ((localObject == null) || (((ResolveInfo)localObject).activityInfo == null)) {
      return "";
    }
    if (((ResolveInfo)localObject).activityInfo.packageName.equals("android")) {
      return "";
    }
    return ((ResolveInfo)localObject).activityInfo.packageName;
  }
  
  public final void b(CampaignEx paramCampaignEx, Activity paramActivity)
  {
    Context localContext;
    if (Build.VERSION.SDK_INT < 26)
    {
      localContext = (Context)this.a.get();
      if (localContext == null) {
        return;
      }
      com.mintegral.msdk.b.b.a();
      com.mintegral.msdk.b.a localA2 = com.mintegral.msdk.b.b.b(com.mintegral.msdk.base.controller.a.d().k());
      com.mintegral.msdk.b.a localA1 = localA2;
      if (localA2 == null)
      {
        com.mintegral.msdk.b.b.a();
        localA1 = com.mintegral.msdk.b.b.b();
      }
      if (b(localA1))
      {
        if (localA1.d() == 1)
        {
          a.a().a(localContext, paramCampaignEx, new b.3(this, paramActivity, localContext, localA1));
          return;
        }
        a.a().a(localContext, paramCampaignEx, 1);
      }
    }
    else
    {
      b(paramActivity);
      return;
    }
    a.a().a(localContext, paramCampaignEx, 1);
    b(paramActivity);
  }
  
  public final String c()
  {
    String str1;
    String str2;
    if (b("com.android.launcher.permission.INSTALL_SHORTCUT"))
    {
      str1 = "1";
      if (!b("com.android.launcher.permission.UNINSTALL_SHORTCUT")) {
        break label100;
      }
      str2 = "1";
      label27:
      if ((!b("com.android.launcher.permission.READ_SETTINGS")) && (!b("com.google.android.launcher.permission.READ_SETTINGS"))) {
        break label107;
      }
    }
    label100:
    label107:
    for (String str3 = "1";; str3 = "0")
    {
      return "[" + str1 + "," + str2 + "," + str3 + "]";
      str1 = "0";
      break;
      str2 = "0";
      break label27;
    }
  }
}
