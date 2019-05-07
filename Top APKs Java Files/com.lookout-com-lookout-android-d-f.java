package com.lookout.android.d;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import com.lookout.LookoutApplication;
import com.lookout.androidsecurity.e.e;
import com.lookout.ao;
import com.lookout.ay;
import com.lookout.c.e.s;
import com.lookout.c.e.x;
import com.lookout.d.j;
import com.lookout.h.l;
import com.lookout.security.InstallReceiverService;
import com.lookout.security.warning.WarningActivity;
import com.lookout.services.NotificationService;
import com.lookout.u.d;
import com.lookout.u.g;
import com.lookout.ui.WarnOfAutorunActivity;
import com.lookout.ui.k;
import com.lookout.ui.v2.AppDetailActivity;
import com.lookout.ui.v2.SecurityListActivity;
import com.lookout.ui.v2.walk1st.KddiCreateAccountNewActivity;
import com.lookout.utils.aq;
import com.lookout.utils.dq;
import com.lookout.v;
import java.util.List;

public class f
  implements e
{
  private static final org.a.b a = org.a.c.a(f.class);
  
  public f() {}
  
  protected int a()
  {
    if (v.b().a() == com.lookout.types.a.a) {
      return 2131165847;
    }
    return 2131165846;
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    Context localContext = LookoutApplication.getContext();
    String str2 = localContext.getResources().getQuantityString(2131230723, paramInt2, new Object[] { Integer.valueOf(paramInt2) });
    String str1 = str2;
    if (!s.b(localContext)) {
      str1 = str2 + " ";
    }
    str1 = str1 + dq.a(paramInt1);
    if (paramInt1 > 0)
    {
      NotificationService.a(str1, 0, false, false, true, false, true, "threat_detected", 2);
      return;
    }
    NotificationService.a(str1, 0, false, false, true, false, true);
  }
  
  public void a(Context paramContext)
  {
    int i;
    if (g.a().al())
    {
      i = l.a().d(com.lookout.security.e.a.c.a);
      a.c("Finished autoscan; count " + i);
      if (i > 0) {
        break label113;
      }
    }
    label113:
    for (String str = paramContext.getString(2131165862, new Object[] { Integer.valueOf(paramContext.getPackageManager().getInstalledPackages(0).size()) }); (i <= 0) && (g.a().aM()); str = paramContext.getString(2131165863))
    {
      NotificationService.a(str, new Intent(paramContext, KddiCreateAccountNewActivity.class).addFlags(268468224));
      return;
    }
    NotificationService.a(str, new Intent(paramContext, SecurityListActivity.class));
  }
  
  public void a(Context paramContext, int paramInt)
  {
    NotificationService.a(paramContext.getString(2131165519), paramInt, true, false, false, false, false);
  }
  
  public void a(Context paramContext, String paramString)
  {
    NotificationService.a(paramContext.getString(2131165858, new Object[] { paramString }), 0, false, false, true, false, true, 2);
  }
  
  public void a(Context paramContext, String paramString1, String paramString2, com.lookout.security.e.a.a paramA)
  {
    if (g.a().al())
    {
      paramString2 = paramContext.getString(2131165861, new Object[] { paramString2 });
      paramContext = new Intent(paramContext, AppDetailActivity.class);
      paramContext.putExtra("com.lookout.PackageName", paramString1);
      NotificationService.a(paramString2, paramContext, 2);
      return;
    }
    paramString1 = new j(paramContext);
    NotificationService.a(paramContext.getString(a(), new Object[] { paramString2, dq.b(paramA), paramString1.a() }), 0, false, false, true, false, true);
  }
  
  public void a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    String str = null;
    if (g.a().al())
    {
      long l = InstallReceiverService.a() + 1L;
      if ((l == 25L) || (l == 50L)) {}
      for (paramContext = paramContext.getString(2131165859, new Object[] { new j(paramContext).a(), Long.valueOf(l) });; paramContext = paramContext.getString(2131165860, new Object[] { paramString2 }))
      {
        NotificationService.a(paramContext, null);
        return;
      }
    }
    int i;
    if (v.b().a() != com.lookout.types.a.a)
    {
      boolean bool1 = ay.a().b();
      boolean bool2 = ay.a().a(paramContext);
      if (com.lookout.u.b.a().d() == d.b)
      {
        i = 1;
        if ((!bool1) || (!bool2) || (i == 0)) {
          break label259;
        }
        if (!TextUtils.isEmpty(paramString1)) {
          str = "privacy_advisor_report," + paramString1;
        }
        NotificationService.a(paramContext.getString(2131165235, new Object[] { paramString2 }), 0, false, false, true, false, true, str);
      }
    }
    for (;;)
    {
      paramContext = com.lookout.l.b.a();
      if (!paramBoolean) {
        break label329;
      }
      try
      {
        paramContext.a(new com.lookout.l.a(2, aq.a(x.e(paramString1))));
        return;
      }
      catch (Exception paramContext)
      {
        a.d("Unable to save scan event", paramContext);
        return;
      }
      i = 0;
      break;
      label259:
      NotificationService.a(paramContext.getString(2131165233, new Object[] { paramString2 }), 0, false, false, true, false, true, null);
      continue;
      NotificationService.a(paramContext.getString(2131165234, new Object[] { paramString2, new j(paramContext).a() }), 0, false, false, true, false, true, null);
    }
    try
    {
      label329:
      paramContext.a(new com.lookout.l.a(1, aq.a(x.e(paramString1))));
      return;
    }
    catch (Exception paramContext)
    {
      a.d("Unable to save scan event", paramContext);
    }
  }
  
  public void a(String paramString) {}
  
  public void b(Context paramContext)
  {
    NotificationService.a(paramContext.getString(2131165519), 0, false, false, true, false, true);
    NotificationService.a(true);
  }
  
  public void b(Context paramContext, String paramString)
  {
    NotificationService.a(paramContext.getString(2131165236, new Object[] { v.a().a(), paramString }), 0, true, true, true, false, false);
  }
  
  public void b(String paramString) {}
  
  public void c(Context paramContext)
  {
    NotificationService.a(false);
  }
  
  public void d(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, WarningActivity.class);
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  public void e(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, WarnOfAutorunActivity.class);
    localIntent.addFlags(268435456);
    paramContext.startActivity(localIntent);
  }
}
