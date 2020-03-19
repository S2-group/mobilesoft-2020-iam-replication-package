package com.ksmobile.launcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.ksmobile.business.sdk.utils.x;
import com.ksmobile.launcher.customitem.FolderAppShortcutInfo;
import com.ksmobile.launcher.customitem.j;
import com.ksmobile.launcher.customitem.k;
import com.ksmobile.launcher.customitem.m;
import com.ksmobile.launcher.folder.i;
import com.ksmobile.launcher.userbehavior.h;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ci
{
  private Map<String, Integer> a = new HashMap();
  
  public ci()
  {
    this.a.put("C0", Integer.valueOf(0));
    this.a.put("C1", Integer.valueOf(1));
    this.a.put("C2", Integer.valueOf(2));
    this.a.put("C3", Integer.valueOf(3));
    this.a.put("C4", Integer.valueOf(4));
    this.a.put("C5", Integer.valueOf(5));
    this.a.put("C6", Integer.valueOf(6));
    this.a.put("C7", Integer.valueOf(7));
    this.a.put("C8", Integer.valueOf(8));
    this.a.put("C9", Integer.valueOf(9));
    this.a.put("C10", Integer.valueOf(10));
    this.a.put("C11", Integer.valueOf(11));
    this.a.put("C12", Integer.valueOf(12));
    this.a.put("C13", Integer.valueOf(13));
    this.a.put("C14", Integer.valueOf(14));
    this.a.put("CM_TOOL", Integer.valueOf(200));
  }
  
  private static boolean b(m paramM)
  {
    Object localObject = ds.a().h();
    if (localObject != null)
    {
      localObject = ((Launcher)localObject).getPackageManager().getInstalledApplications(8192).iterator();
      while (((Iterator)localObject).hasNext()) {
        if (((ApplicationInfo)((Iterator)localObject).next()).packageName.equals(paramM.d)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void a(final m paramM)
  {
    final cj localCj = i.a().a(paramM.e);
    if ((localCj == null) || (b(paramM))) {
      return;
    }
    if ((ds.a().h() == null) || (ds.a().h().W()))
    {
      x.a(new Runnable()
      {
        public void run()
        {
          ci.this.a(paramM);
        }
      }, 3000L);
      return;
    }
    dx.b(new Runnable()
    {
      public void run()
      {
        x.b(new Runnable()
        {
          public void run()
          {
            Object localObject1 = ds.a().f();
            Object localObject2 = ds.a().h();
            if ((localObject2 == null) || (((Launcher)localObject2).isDestroyed()) || (((Launcher)localObject2).ae() == null)) {
              return;
            }
            Intent localIntent;
            if (this.a != null)
            {
              this.a.b(ci.2.this.a.h);
              localIntent = new Intent();
              localIntent.setComponent(new ComponentName((Context)localObject2, FolderAppShortcutInfo.a(ci.2.this.a.a)));
              localIntent.putExtra("custome_class_name", FolderAppShortcutInfo.a(ci.2.this.a.a));
              localIntent.putExtra("pushId", ci.2.this.a.a);
              localIntent.putExtra("title", ci.2.this.a.b);
              localIntent.putExtra("url", ci.2.this.a.c);
              localIntent.putExtra("pkg", ci.2.this.a.d);
              this.a.a((Context)localObject2, localIntent, (cw)localObject1);
              dx.a((Context)localObject2, this.a);
              localObject2 = this.a.p();
              if (localObject2 != null)
              {
                ((BubbleTextView)localObject2).a(this.a, (cw)localObject1, true);
                ((BubbleTextView)localObject2).a_(ci.2.this.a.b);
              }
            }
            for (;;)
            {
              h.b(false, "launcher_app_pushicon", new String[] { "iconid", String.valueOf(Integer.valueOf(ci.2.this.a.a)), "appname", ci.2.this.a.d, "class", String.valueOf(ci.a(ci.this).get(ci.2.this.a.e.toUpperCase())) });
              return;
              localIntent = new Intent();
              localIntent.setComponent(new ComponentName((Context)localObject2, FolderAppShortcutInfo.a(ci.2.this.a.a)));
              localIntent.putExtra("custome_class_name", FolderAppShortcutInfo.a(ci.2.this.a.a));
              localIntent.putExtra("pushId", ci.2.this.a.a);
              localIntent.putExtra("url", ci.2.this.a.c);
              localIntent.putExtra("title", ci.2.this.a.b);
              localIntent.putExtra("pkg", ci.2.this.a.d);
              localObject1 = j.b((Context)localObject2, localIntent.toUri(0), (cw)localObject1, ci.2.this.a.h);
              if (localObject1 != null) {
                ci.2.this.b.a((ge)localObject1);
              }
            }
          }
        });
      }
    });
  }
}
